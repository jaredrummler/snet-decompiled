package com.google.android.gms.people.identity.internal;

import android.annotation.TargetApi;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract.CommonDataKinds.Email;
import android.provider.ContactsContract.Contacts;
import android.provider.ContactsContract.Data;
import android.provider.ContactsContract.PhoneLookup;
import android.support.annotation.RequiresPermission;
import android.text.TextUtils;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.people.PeopleConstants.PeoplePostalAddress;
import com.google.android.gms.people.cp2.PeopleCp2Helper;
import com.google.android.gms.people.identity.PersonFactory.ContactData;
import com.google.android.gms.people.identity.PersonFactory.ExternalContactData;
import com.google.android.gms.people.identity.PersonFactory.RawContactData;
import com.google.android.gms.people.identity.external.AccountType;
import com.google.android.gms.people.identity.external.AccountTypeManager;
import com.google.android.gms.people.identity.external.DataKind;
import com.google.android.gms.people.internal.PeopleServiceLog;
import com.google.android.gms.people.internal.PeopleUtils;
import com.google.android.gms.people.internal.PhoneNumberEntryRef.PhoneNumberHolderColumns;
import com.google.android.gsf.TalkContract.PresenceColumns;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ContactDataLoader {
    private static final String TAG = "ContactsDataLoader";

    public interface ContactDataLoaderCallback {
        void onContactResults(Status status, ContactData[] contactDataArr);
    }

    private interface EmailLookupProjection {
        public static final int COLUMN_CONTACT_ID = 0;
        public static final String[] PROJECTION;

        static {
            PROJECTION = new String[]{PresenceColumns.CONTACT_ID};
        }
    }

    private static class LoaderByQualifiedIds implements Runnable {
        private static final String[] RAW_CONTACT_LOOKUP_PROJECTION;
        private final ContactDataLoaderCallback mCallback;
        private final Context mContext;
        private final String mFocusAccount;
        private final Set<String>[] mQualifiedIdClusters;

        private interface RawContactLookupProjection {
            public static final int COLUMN_ACCOUNT_TYPE = 19;
            public static final int COLUMN_CONTACT_ID = 18;
            public static final int COLUMN_DATA1 = 2;
            public static final int COLUMN_DATA10 = 11;
            public static final int COLUMN_DATA11 = 12;
            public static final int COLUMN_DATA12 = 13;
            public static final int COLUMN_DATA13 = 14;
            public static final int COLUMN_DATA14 = 15;
            public static final int COLUMN_DATA15 = 16;
            public static final int COLUMN_DATA2 = 3;
            public static final int COLUMN_DATA3 = 4;
            public static final int COLUMN_DATA4 = 5;
            public static final int COLUMN_DATA5 = 6;
            public static final int COLUMN_DATA6 = 7;
            public static final int COLUMN_DATA7 = 8;
            public static final int COLUMN_DATA8 = 9;
            public static final int COLUMN_DATA9 = 10;
            public static final int COLUMN_DATA_ID = 0;
            public static final int COLUMN_DATA_SET = 20;
            public static final int COLUMN_MIMETYPE = 1;
            public static final int COLUMN_PRIMARY = 17;
            public static final int COLUMN_TIMES_USED = 21;
        }

        static {
            ArrayList<String> columns = new ArrayList();
            columns.add("data_id");
            columns.add("mimetype");
            columns.add("data1");
            columns.add("data2");
            columns.add("data3");
            columns.add("data4");
            columns.add("data5");
            columns.add("data6");
            columns.add("data7");
            columns.add("data8");
            columns.add("data9");
            columns.add("data10");
            columns.add("data11");
            columns.add("data12");
            columns.add("data13");
            columns.add("data14");
            columns.add("data15");
            columns.add("is_primary");
            columns.add(PresenceColumns.CONTACT_ID);
            columns.add("account_type");
            if (PlatformVersion.isAtLeastIceCreamSandwich()) {
                columns.add("data_set");
            }
            if (PlatformVersion.isAtLeastLollipop()) {
                columns.add("times_used");
            }
            RAW_CONTACT_LOOKUP_PROJECTION = (String[]) columns.toArray(new String[0]);
        }

        public LoaderByQualifiedIds(ContactDataLoaderCallback callback, Context context, String focusAccount, Set<String>[] qualifiedIdClusters) {
            this.mCallback = callback;
            this.mContext = context;
            this.mFocusAccount = focusAccount;
            this.mQualifiedIdClusters = qualifiedIdClusters;
        }

        @RequiresPermission("android.permission.READ_CONTACTS")
        public void run() {
            List<AccountType> accountTypes = AccountTypeManager.getInstance(this.mContext).getAccountTypes();
            ContactData[] contactDataList = new ContactData[this.mQualifiedIdClusters.length];
            int i = 0;
            while (i < contactDataList.length) {
                try {
                    Set<String> contactIds = new HashSet();
                    for (String qualifiedId : this.mQualifiedIdClusters[i]) {
                        if (PeopleUtils.isQualifiedIdFromEmail(qualifiedId)) {
                            contactIds.addAll(ContactDataLoader.retrieveContactsFromEmailId(this.mContext, PeopleUtils.peopleQualifiedIdToEmailAddress(qualifiedId)));
                        } else if (PeopleUtils.isQualifiedIdFromGaia(qualifiedId)) {
                            contactIds.addAll(ContactDataLoader.retrieveContactsFromGaiaId(PeopleUtils.peopleQualifiedIdToGaiaId(qualifiedId)));
                        } else if (ContactDataUtil.isQualifiedIdFromPhoneNumber(qualifiedId)) {
                            contactIds.addAll(ContactDataLoader.retrieveContactsFromPhoneNumberId(this.mContext, ContactDataUtil.peopleQualifiedIdToPhoneNumber(qualifiedId)));
                        } else if (ContactDataUtil.isQualifiedIdFromFocus(qualifiedId)) {
                            contactIds.addAll(ContactDataLoader.retrieveContactsFromFocusId(this.mContext, this.mFocusAccount, ContactDataUtil.peopleQualifiedIdToFocusId(qualifiedId)));
                        } else if (ContactDataUtil.isQualifiedIdFromCP2(qualifiedId)) {
                            contactIds.addAll(ContactDataUtil.retrieveContactsFromContactId(ContactDataUtil.peopleQualifiedIdToCP2ContactId(qualifiedId)));
                        } else if (PeopleUtils.isQualifiedId(qualifiedId)) {
                            PeopleServiceLog.w(ContactDataLoader.TAG, "Unknown qualified ID type");
                        } else {
                            PeopleServiceLog.w(ContactDataLoader.TAG, "Invalid qualified ID");
                        }
                    }
                    contactDataList[i] = buildContactDataFromContactIds(contactIds, accountTypes);
                    i++;
                } catch (SecurityException e) {
                    PeopleServiceLog.e(ContactDataLoader.TAG, "Error querying contact data:", e);
                } finally {
                    this.mCallback.onContactResults(Status.RESULT_SUCCESS, contactDataList);
                }
            }
        }

        private ContactData buildContactDataFromContactIds(Set<String> contactIds, List<AccountType> accountTypes) {
            List rawData = new ArrayList();
            if (contactIds != null) {
                for (String contactId : contactIds) {
                    addRawContactData(rawData, contactId, accountTypes);
                }
            }
            return rawData.isEmpty() ? null : new ContactData(rawData);
        }

        @RequiresPermission("android.permission.READ_CONTACTS")
        private void addRawContactData(List<RawContactData> rawData, String contactId, List<AccountType> accountTypes) {
            if (PlatformVersion.isAtLeastHoneycomb()) {
                Cursor cursor = this.mContext.getContentResolver().query(Contacts.CONTENT_URI.buildUpon().appendEncodedPath(contactId).appendEncodedPath("entities").build(), RAW_CONTACT_LOOKUP_PROJECTION, null, null, null);
                if (cursor != null) {
                    while (cursor.moveToNext()) {
                        try {
                            String dataId = cursor.getString(0);
                            if (dataId != null) {
                                int timesUsed;
                                String mimeType = cursor.getString(1);
                                String[] data = new String[15];
                                int i = 2;
                                int dataIndex = 0;
                                while (i <= 16) {
                                    int dataIndex2 = dataIndex + 1;
                                    data[dataIndex] = getColumnValue(i, cursor);
                                    i++;
                                    dataIndex = dataIndex2;
                                }
                                boolean primary = cursor.getInt(17) == 1;
                                String accountType = cursor.getString(19);
                                String dataSet = PlatformVersion.isAtLeastIceCreamSandwich() ? cursor.getString(20) : null;
                                if (PlatformVersion.isAtLeastLollipop()) {
                                    timesUsed = cursor.getInt(21);
                                } else {
                                    timesUsed = getTimesUsed(dataId);
                                }
                                rawData.add(new RawContactData(contactId, contactId, mimeType, timesUsed, data, false, primary, toExternalContactData(accountTypes, mimeType, accountType, dataSet, cursor)));
                            }
                        } finally {
                            try {
                                cursor.close();
                            } catch (Exception e) {
                            }
                        }
                    }
                }
            }
        }

        @TargetApi(18)
        @RequiresPermission("android.permission.READ_CONTACTS")
        private int getTimesUsed(String dataId) {
            if (!PlatformVersion.isAtLeastJellyBeanMR2()) {
                return 0;
            }
            Uri dataUri = ContentUris.withAppendedId(Data.CONTENT_URI, Long.parseLong(dataId));
            Cursor cursor = this.mContext.getContentResolver().query(dataUri, new String[]{"times_used"}, null, null, null);
            if (cursor == null) {
                PeopleServiceLog.w(ContactDataLoader.TAG, "null getTimesUsed cursor");
                return 0;
            }
            try {
                if (cursor.moveToFirst()) {
                    int i = cursor.getInt(0);
                    return i;
                }
                cursor.close();
                return 0;
            } finally {
                cursor.close();
            }
        }

        @TargetApi(14)
        private static String getColumnValue(int columnIndex, Cursor cursor) {
            if (PlatformVersion.isAtLeastIceCreamSandwich()) {
                if (cursor.getType(columnIndex) == 0) {
                    return null;
                }
                if (cursor.getType(columnIndex) == 4) {
                    return new String(cursor.getBlob(columnIndex));
                }
            } else if (cursor.isNull(columnIndex)) {
                return null;
            } else {
                try {
                    if (cursor.getBlob(columnIndex) != null) {
                        return null;
                    }
                } catch (Exception e) {
                }
            }
            return cursor.getString(columnIndex);
        }

        private ExternalContactData toExternalContactData(List<AccountType> accountTypes, String mimeType, String accountTypeEntity, String dataSetEntity, Cursor cursor) {
            for (AccountType accountType : accountTypes) {
                if (Objects.equal(accountType.accountType, accountTypeEntity) && Objects.equal(accountType.dataSet, dataSetEntity)) {
                    DataKind dataKind = accountType.getKindForMimetype(mimeType);
                    if (dataKind != null) {
                        return new ExternalContactData(ContentUris.withAppendedId(Data.CONTENT_URI, cursor.getLong(0)), cursor.getString(cursor.getColumnIndex(dataKind.summaryColumn)), accountType.iconRes, cursor.getString(cursor.getColumnIndex(dataKind.detailColumn)), dataKind.resourcePackageName, dataKind.mimeType, accountType.titleRes, accountType.accountType);
                    }
                }
            }
            return null;
        }
    }

    private static class LoaderListAll implements Runnable {
        private final ContactDataLoaderCallback mCallback;
        private final Context mContext;
        private final String mFocusAccount;
        private final Set<String> mIgnoredContacts;

        private interface ListProjection {
            public static final int COLUMN_CONTACT_ID = 0;
            public static final String[] PROJECTION;

            static {
                PROJECTION = new String[]{PeoplePostalAddress._ID, PhoneNumberHolderColumns.CONTACT_NAME, "photo_id"};
            }
        }

        public LoaderListAll(ContactDataLoaderCallback callback, Context context, String focusAccount, Set<String> ignoredContacts) {
            this.mCallback = callback;
            this.mContext = context;
            this.mFocusAccount = focusAccount;
            this.mIgnoredContacts = ignoredContacts;
        }

        @RequiresPermission("android.permission.READ_CONTACTS")
        public void run() {
            ArrayList<ContactData> contactDataList = new ArrayList();
            Set<String> ignoredContactIds = new HashSet();
            for (String qualifiedId : this.mIgnoredContacts) {
                if (PeopleUtils.isQualifiedIdFromEmail(qualifiedId)) {
                    ignoredContactIds.addAll(ContactDataLoader.retrieveContactsFromEmailId(this.mContext, PeopleUtils.peopleQualifiedIdToEmailAddress(qualifiedId)));
                } else if (PeopleUtils.isQualifiedIdFromGaia(qualifiedId)) {
                    ignoredContactIds.addAll(ContactDataLoader.retrieveContactsFromGaiaId(PeopleUtils.peopleQualifiedIdToGaiaId(qualifiedId)));
                } else {
                    try {
                        if (ContactDataUtil.isQualifiedIdFromPhoneNumber(qualifiedId)) {
                            ignoredContactIds.addAll(ContactDataLoader.retrieveContactsFromPhoneNumberId(this.mContext, ContactDataUtil.peopleQualifiedIdToPhoneNumber(qualifiedId)));
                        } else if (ContactDataUtil.isQualifiedIdFromFocus(qualifiedId)) {
                            ignoredContactIds.addAll(ContactDataLoader.retrieveContactsFromFocusId(this.mContext, this.mFocusAccount, ContactDataUtil.peopleQualifiedIdToFocusId(qualifiedId)));
                        } else if (ContactDataUtil.isQualifiedIdFromCP2(qualifiedId)) {
                            ignoredContactIds.addAll(ContactDataUtil.retrieveContactsFromContactId(ContactDataUtil.peopleQualifiedIdToCP2ContactId(qualifiedId)));
                        } else if (PeopleUtils.isQualifiedId(qualifiedId)) {
                            PeopleServiceLog.w(ContactDataLoader.TAG, "Unknown qualified ID type");
                        } else {
                            PeopleServiceLog.w(ContactDataLoader.TAG, "Invalid qualified ID");
                        }
                    } catch (SecurityException e) {
                        PeopleServiceLog.e(ContactDataLoader.TAG, "Error querying contact data:", e);
                        this.mCallback.onContactResults(Status.RESULT_SUCCESS, (ContactData[]) contactDataList.toArray(new ContactData[contactDataList.size()]));
                        return;
                    } catch (Throwable th) {
                        Throwable th2 = th;
                        this.mCallback.onContactResults(Status.RESULT_SUCCESS, (ContactData[]) contactDataList.toArray(new ContactData[contactDataList.size()]));
                    }
                }
            }
            Cursor cur = this.mContext.getContentResolver().query(Contacts.CONTENT_URI, ListProjection.PROJECTION, null, null, null);
            cur.move(-1);
            while (cur.moveToNext()) {
                if (!ignoredContactIds.contains(cur.getString(0))) {
                    String[] data = new String[ListProjection.PROJECTION.length];
                    for (int i = 0; i < ListProjection.PROJECTION.length; i++) {
                        data[i] = cur.getString(i);
                    }
                    contactDataList.add(new ContactData(new RawContactData(cur.getString(0), null, null, 0, data, true, true, null)));
                }
            }
            cur.close();
            this.mCallback.onContactResults(Status.RESULT_SUCCESS, (ContactData[]) contactDataList.toArray(new ContactData[contactDataList.size()]));
        }
    }

    private interface PhoneLookupProjection {
        public static final int COLUMN_CONTACT_ID = 0;
        public static final String[] PROJECTION;

        static {
            PROJECTION = new String[]{PeoplePostalAddress._ID};
        }
    }

    private interface RawContactIdLookupProjection {
        public static final String[] PROJECTION;
        public static final int RAW_COLUMN_CONTACT_ID = 0;
        public static final String SELECTION = "contact_id=?";

        static {
            PROJECTION = new String[]{PeoplePostalAddress._ID};
        }
    }

    private ContactDataLoader() {
    }

    public static void queryByQualifiedIds(ContactDataLoaderCallback callback, Context context, String focusAccount, Set<String>[] qualifiedIdClusters) {
        new Thread(new LoaderByQualifiedIds(callback, context, focusAccount, qualifiedIdClusters)).start();
    }

    public static void listAll(ContactDataLoaderCallback callback, Context context, String focusAccount, Set<String> ignoredQualifiedIds) {
        new Thread(new LoaderListAll(callback, context, focusAccount, ignoredQualifiedIds)).start();
    }

    @RequiresPermission("android.permission.READ_CONTACTS")
    private static Set<String> retrieveContactsFromEmailId(Context context, String email) {
        if (TextUtils.isEmpty(email)) {
            PeopleServiceLog.w(TAG, "empty email address");
            return Collections.emptySet();
        }
        Cursor cursor = context.getContentResolver().query(Uri.withAppendedPath(Email.CONTENT_LOOKUP_URI, Uri.encode(email)), EmailLookupProjection.PROJECTION, null, null, null);
        if (cursor == null) {
            PeopleServiceLog.w(TAG, "null retrieveContactsFromEmailId cursor");
            return Collections.emptySet();
        }
        try {
            Set<String> contactIds = new HashSet();
            while (cursor.moveToNext()) {
                contactIds.add(cursor.getString(0));
            }
            return contactIds;
        } finally {
            cursor.close();
        }
    }

    @RequiresPermission("android.permission.READ_CONTACTS")
    private static Set<String> retrieveContactsFromPhoneNumberId(Context context, String phoneNumber) {
        if (TextUtils.isEmpty(phoneNumber)) {
            PeopleServiceLog.w(TAG, "empty phone number");
            return Collections.emptySet();
        }
        Cursor cursor = context.getContentResolver().query(Uri.withAppendedPath(PhoneLookup.CONTENT_FILTER_URI, Uri.encode(phoneNumber)), PhoneLookupProjection.PROJECTION, null, null, null);
        if (cursor == null) {
            PeopleServiceLog.w(TAG, "null retrieveContactsFromPhoneNumberId cursor");
            return Collections.emptySet();
        }
        try {
            Set<String> contactIds = new HashSet();
            while (cursor.moveToNext()) {
                contactIds.add(cursor.getString(0));
            }
            return contactIds;
        } finally {
            cursor.close();
        }
    }

    private static Set<String> retrieveContactsFromFocusId(Context context, String focusAccount, String focusId) {
        if (TextUtils.isEmpty(focusId)) {
            PeopleServiceLog.w(TAG, "empty focus ID");
            return Collections.emptySet();
        }
        long contactId = PeopleCp2Helper.findContactByFocusId(context, focusAccount, focusId);
        return contactId >= 0 ? Collections.singleton(String.valueOf(contactId)) : Collections.emptySet();
    }

    private static Set<String> retrieveContactsFromGaiaId(String gaiaId) {
        return Collections.emptySet();
    }
}
