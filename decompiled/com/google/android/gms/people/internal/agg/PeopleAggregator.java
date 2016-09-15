package com.google.android.gms.people.internal.agg;

import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.os.Bundle;
import android.text.TextUtils;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.people.identity.internal.ContactDataUtil;
import com.google.android.gms.people.internal.AutocompleteEntryRef.AcHolderColumns;
import com.google.android.gms.people.internal.IntToStringsMap;
import com.google.android.gms.people.internal.PeopleServiceLog;
import com.google.android.gms.people.internal.Stopwatch;
import com.google.android.gms.people.internal.StringToIntsMap;
import com.google.android.gms.people.model.AggregatedPersonBuffer;
import com.google.android.gsf.TalkContract.AccountSettingsColumns;
import java.text.Collator;
import java.util.ArrayList;
import java.util.HashMap;

@VisibleForTesting
public abstract class PeopleAggregator {
    static final boolean DEBUG = false;
    static final String TAG = "PeopleAggregator";
    static volatile boolean sUseContactablesApi;
    private final Collator mCollator;
    private Cursor mContactsCursor;
    private Exception mContactsException;
    private boolean mContactsQueryDone;
    protected final Context mContext;
    protected final Bundle mEmailTypeMap;
    protected final int mExtraColumns;
    private boolean mFailureReported;
    protected final String mFilterGaiaId;
    protected final boolean mFilteringByGaiaId;
    private DataHolder mGaiaMapHolder;
    protected final boolean mIncludeInvisible;
    private final Listener mListener;
    private final Object mLock;
    protected final Bundle mPhoneTypeMap;
    private DataHolder mPlusHolder;
    private ConnectionResult mPlusLoadedStatus;
    private boolean mPlusQueryDone;
    protected final Stopwatch mStopwatch;

    public interface Listener {
        void onLoaded(int i, Bundle bundle, AggregatedPersonBuffer aggregatedPersonBuffer);
    }

    private class AggregatorThread extends Thread {
        public AggregatorThread() {
            super("PeopleAggregator-aggregator");
        }

        public final void run() {
            try {
                PeopleAggregator.this.doAggregation();
            } catch (Exception e) {
                PeopleServiceLog.e(PeopleAggregator.TAG, "Unknown exception during aggregation", e);
                PeopleAggregator.this.reportFailure();
            }
        }
    }

    private class ContactsLoadThread extends Thread {
        public ContactsLoadThread() {
            super("PeopleAggregator-contacts");
        }

        public final void run() {
            PeopleAggregator.this.mStopwatch.lap("contacts query start");
            try {
                PeopleAggregator.this.onContactsLoaded(PeopleAggregator.this.queryContacts(), null);
            } catch (Exception e) {
                PeopleServiceLog.e(PeopleAggregator.TAG, "Error while quering contacts", e);
                PeopleAggregator.this.onContactsLoaded(null, e);
            }
        }
    }

    protected static class DataHolderWrapper {
        public final DataHolder holder;
        private final int mCount;
        private int mPosition;

        public DataHolderWrapper(DataHolder holder) {
            this.mPosition = -1;
            this.holder = holder;
            this.mCount = holder.getCount();
        }

        public int getCount() {
            return this.mCount;
        }

        public String getString(String column) {
            return this.holder.getString(column, this.mPosition, this.holder.getWindowIndex(this.mPosition));
        }

        public int getPosition() {
            return this.mPosition;
        }

        public void moveToPosition(int pos) {
            this.mPosition = pos;
        }

        public boolean moveToNext() {
            this.mPosition++;
            return (this.mPosition < 0 || this.mPosition >= this.mCount) ? PeopleAggregator.DEBUG : true;
        }

        public boolean isAfterLast() {
            return this.mPosition >= this.mCount ? true : PeopleAggregator.DEBUG;
        }
    }

    protected abstract AggregatedPersonBufferImpl aggregateInner(DataHolderWrapper dataHolderWrapper, DataHolderWrapper dataHolderWrapper2, Cursor cursor);

    protected abstract Cursor queryContacts();

    static {
        sUseContactablesApi = true;
    }

    protected PeopleAggregator(Context context, Listener listener, boolean includeInvisible, int extraColumns, Bundle emailTypeMap, Bundle phoneTypeMap, String filterGaiaId) {
        this.mLock = new Object();
        this.mCollator = Collator.getInstance();
        this.mContext = context;
        this.mListener = listener;
        this.mIncludeInvisible = includeInvisible;
        this.mExtraColumns = extraColumns;
        this.mEmailTypeMap = emailTypeMap;
        this.mPhoneTypeMap = phoneTypeMap;
        this.mFilteringByGaiaId = !TextUtils.isEmpty(filterGaiaId) ? true : DEBUG;
        if (!this.mFilteringByGaiaId) {
            filterGaiaId = null;
        }
        this.mFilterGaiaId = filterGaiaId;
        this.mStopwatch = PeopleServiceLog.canDebugLog() ? Stopwatch.start("aggregator") : Stopwatch.getNullStopWatch();
    }

    public static PeopleAggregator newAggregator(Context context, Listener listener, boolean includeInvisible, int extraColumns, Bundle emailTypeMap, Bundle phoneTypeMap, String query, String filterGaiaId) {
        if (TextUtils.isEmpty(query)) {
            return new PeopleAllContactsAggregator(context, listener, includeInvisible, extraColumns, emailTypeMap, phoneTypeMap, filterGaiaId);
        }
        if (TextUtils.isEmpty(filterGaiaId)) {
            return new PeopleSearchAggregator(context, listener, includeInvisible, extraColumns, emailTypeMap, phoneTypeMap, query);
        }
        throw new IllegalArgumentException("Search aggregation doesn't support filtering by gaia-id");
    }

    public static void setUseContactablesApi(boolean value) {
        sUseContactablesApi = value;
    }

    protected DataHolder getGaiaMapHolder() {
        return this.mGaiaMapHolder;
    }

    public void startContactsQueryWhenReady() {
        if (!this.mFilteringByGaiaId) {
            startContactsQuery();
        }
    }

    private void startContactsQuery() {
        try {
            new ContactsLoadThread().start();
        } catch (Exception e) {
            PeopleServiceLog.e(TAG, "Unable to start thread", e);
            onContactsLoaded(null, e);
        }
    }

    public void onPlusPeopleLoaded(ConnectionResult status, DataHolder[] dataHolders) {
        if (status.isSuccess()) {
            this.mStopwatch.lap("people loaded");
        } else {
            this.mStopwatch.lap("people load failure");
        }
        if (PeopleServiceLog.canDebugLog()) {
            int i;
            String str = TAG;
            StringBuilder append = new StringBuilder().append("People loaded.  status=").append(status).append("  size=");
            if (dataHolders == null || dataHolders.length < 2 || dataHolders[0] == null) {
                i = -1;
            } else {
                i = dataHolders[0].getCount();
            }
            PeopleServiceLog.d(str, append.append(i).toString());
        }
        synchronized (this.mLock) {
            this.mPlusQueryDone = true;
            this.mPlusLoadedStatus = status;
            if (this.mPlusLoadedStatus.isSuccess()) {
                this.mPlusHolder = dataHolders[0];
                this.mGaiaMapHolder = dataHolders[1];
            }
        }
        if (!this.mFilteringByGaiaId) {
            onDataLoaded();
        } else if (this.mPlusLoadedStatus.isSuccess()) {
            startContactsQuery();
        } else {
            synchronized (this.mLock) {
                this.mContactsQueryDone = true;
            }
            reportFailure();
        }
    }

    @VisibleForTesting
    void onContactsLoaded(Cursor c, Exception e) {
        if (c != null) {
            this.mStopwatch.lap("contacts loaded");
        } else {
            this.mStopwatch.lap("contacts load failure");
        }
        if (PeopleServiceLog.canDebugLog()) {
            PeopleServiceLog.d(TAG, "Contacts loaded.  exception=" + e + "  size=" + (c == null ? -1 : c.getCount()));
        }
        synchronized (this.mLock) {
            this.mContactsQueryDone = true;
            this.mContactsCursor = c;
            this.mContactsException = e;
        }
        onDataLoaded();
    }

    private void reportFailure() {
        synchronized (this.mLock) {
            Preconditions.checkArgument(this.mPlusQueryDone);
            Preconditions.checkArgument(this.mContactsQueryDone);
            if (this.mPlusHolder != null) {
                this.mPlusHolder.close();
            }
            if (this.mGaiaMapHolder != null) {
                this.mGaiaMapHolder.close();
            }
            if (this.mContactsCursor != null) {
                this.mContactsCursor.close();
            }
            if (this.mFailureReported) {
                return;
            }
            this.mFailureReported = true;
            this.mListener.onLoaded(8, null, null);
        }
    }

    private void onDataLoaded() {
        synchronized (this.mLock) {
            if (this.mPlusQueryDone && this.mContactsQueryDone) {
                if (this.mPlusLoadedStatus.isSuccess()) {
                    try {
                        new AggregatorThread().start();
                        return;
                    } catch (Exception e) {
                        PeopleServiceLog.e(TAG, "Unable to start thread", e);
                        reportFailure();
                        return;
                    }
                }
                reportFailure();
                return;
            }
        }
    }

    private void doAggregation() {
        Preconditions.checkArgument(this.mPlusLoadedStatus.isSuccess());
        this.mStopwatch.lap("agg start");
        AggregatedPersonBufferImpl buffer = aggregateInner(new DataHolderWrapper(this.mPlusHolder), new DataHolderWrapper(this.mGaiaMapHolder), this.mContactsCursor != null ? this.mContactsCursor : new MatrixCursor(AggregationQueries.DATA_PROJECTION));
        this.mStopwatch.lap("agg finish");
        this.mStopwatch.stopAndLog(TAG, 0);
        this.mListener.onLoaded(0, null, buffer);
    }

    protected int compareLocalized(String text1, String text2) {
        if (TextUtils.isEmpty(text1)) {
            if (TextUtils.isEmpty(text2)) {
                return 0;
            }
            return -1;
        } else if (TextUtils.isEmpty(text2)) {
            return 1;
        } else {
            return this.mCollator.compare(text1, text2);
        }
    }

    protected static void buildPeopleGaiaIdMap(DataHolderWrapper people, HashMap<String, Integer> peopleGaiaToPos) {
        people.moveToPosition(-1);
        while (people.moveToNext()) {
            String gaiaId = people.getString(AcHolderColumns.GAIA_ID);
            if (!TextUtils.isEmpty(gaiaId)) {
                peopleGaiaToPos.put(gaiaId, Integer.valueOf(people.getPosition()));
            }
        }
    }

    protected int contactBuildGaiaIdMap(Cursor contacts, StringToIntsMap contactGaiaToPos, IntToStringsMap contactPosToGaiaIds, HashMap<String, String> contactInfoToGaiaMap) {
        int contactsCount = 0;
        long lastContactId = -1;
        int contactStartPos = -1;
        contacts.moveToPosition(-1);
        ArrayList<String> dupeGaiaInContact = new ArrayList(3);
        ArrayList<String> dupeInfoInContact = new ArrayList(6);
        while (contacts.moveToNext()) {
            long cid = contacts.getLong(0);
            if (cid != lastContactId) {
                dupeGaiaInContact.clear();
                dupeInfoInContact.clear();
                lastContactId = cid;
                contactStartPos = contacts.getPosition();
                contactsCount++;
            }
            String mimetype = contacts.getString(2);
            if (ContactDataUtil.MIMETYPE_EMAIL.equals(mimetype) || ContactDataUtil.MIMETYPE_PHONE.equals(mimetype)) {
                String contactInfo = contacts.getString(3);
                if (!(TextUtils.isEmpty(contactInfo) || dupeInfoInContact.contains(contactInfo))) {
                    dupeInfoInContact.add(contactInfo);
                    String gaia = (String) contactInfoToGaiaMap.get(contactInfo);
                    if (!(TextUtils.isEmpty(gaia) || dupeGaiaInContact.contains(gaia))) {
                        dupeGaiaInContact.add(gaia);
                        contactGaiaToPos.put(gaia, contactStartPos);
                        contactPosToGaiaIds.put(Integer.valueOf(contactStartPos), gaia);
                    }
                }
            }
        }
        return contactsCount;
    }

    protected void buildInfoToGaiaIdMap(DataHolderWrapper holder, HashMap<String, String> contactInfoToGaiaMap) {
        holder.moveToPosition(-1);
        while (holder.moveToNext()) {
            contactInfoToGaiaMap.put(holder.getString(AccountSettingsColumns.VALUE), holder.getString(AcHolderColumns.GAIA_ID));
        }
    }
}
