package com.google.android.gms.people.internal;

import android.app.PendingIntent;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.IBinder;
import android.os.Looper;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import android.text.TextUtils;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation.ResultHolder;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.ListenerHolder.Notifier;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.GmsClient;
import com.google.android.gms.common.internal.GmsClientSupervisor;
import com.google.android.gms.common.internal.ICancelToken;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.server.FavaDiagnosticsEntity;
import com.google.android.gms.common.util.ArrayUtils;
import com.google.android.gms.common.util.IOUtils;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.people.Autocomplete.AutocompleteOptions;
import com.google.android.gms.people.Autocomplete.AutocompleteResult;
import com.google.android.gms.people.Autocomplete.PreferredFieldsResult;
import com.google.android.gms.people.Graph.LoadAggregatedPeopleResult;
import com.google.android.gms.people.Graph.LoadCirclesResult;
import com.google.android.gms.people.Graph.LoadContactsGaiaIdsResult;
import com.google.android.gms.people.Graph.LoadMeResult;
import com.google.android.gms.people.Graph.LoadOwnersResult;
import com.google.android.gms.people.Graph.LoadPeopleForAggregationResult;
import com.google.android.gms.people.Graph.LoadPeopleOptions;
import com.google.android.gms.people.Graph.LoadPeopleResult;
import com.google.android.gms.people.Graph.LoadPhoneNumbersResult;
import com.google.android.gms.people.GraphUpdate.AddCircleResult;
import com.google.android.gms.people.GraphUpdate.AddPeopleToCircleResult;
import com.google.android.gms.people.GraphUpdate.LoadAddToCircleConsentResult;
import com.google.android.gms.people.GraphUpdate.SetMeResult;
import com.google.android.gms.people.GraphUpdate.UpdatePersonCircleResult;
import com.google.android.gms.people.Images.LoadImageOptions;
import com.google.android.gms.people.Images.LoadImageResult;
import com.google.android.gms.people.Images.SetAvatarResult;
import com.google.android.gms.people.InternalApi.LoadPeopleForAspenOptions;
import com.google.android.gms.people.InternalApi.LoadPeopleForAspenResult;
import com.google.android.gms.people.Notifications.OnDataChanged;
import com.google.android.gms.people.People.BundleResult;
import com.google.android.gms.people.People.ReleasableResult;
import com.google.android.gms.people.PeopleConstants;
import com.google.android.gms.people.PeopleConstants.BundleKeys;
import com.google.android.gms.people.PeopleConstants.Circles;
import com.google.android.gms.people.exp.ContactGaiaIdRawBuffer;
import com.google.android.gms.people.exp.PersonForAggregationRawBuffer;
import com.google.android.gms.people.identity.IdentityApi.GetOptions;
import com.google.android.gms.people.identity.IdentityApi.ListOptions;
import com.google.android.gms.people.identity.internal.AccountToken;
import com.google.android.gms.people.identity.internal.ParcelableGetOptions;
import com.google.android.gms.people.identity.internal.ParcelableListOptions;
import com.google.android.gms.people.identity.internal.models.DefaultPersonImpl.CoverPhotos;
import com.google.android.gms.people.internal.IPeopleService.Stub;
import com.google.android.gms.people.internal.agg.PeopleAggregator;
import com.google.android.gms.people.internal.agg.PeopleAggregator.Listener;
import com.google.android.gms.people.internal.agg.PhoneEmailDecoder.EmailDecoder;
import com.google.android.gms.people.internal.agg.PhoneEmailDecoder.PhoneDecoder;
import com.google.android.gms.people.internal.autocomplete.AutocompletionImpl;
import com.google.android.gms.people.internal.autocomplete.ParcelableLoadAutocompleteResultsOptions;
import com.google.android.gms.people.internal.autocomplete.ParcelableLoadContactGroupFieldsOptions;
import com.google.android.gms.people.model.AggregatedPersonBuffer;
import com.google.android.gms.people.model.AutocompleteBuffer;
import com.google.android.gms.people.model.AvatarReference;
import com.google.android.gms.people.model.CircleBuffer;
import com.google.android.gms.people.model.ContactGaiaIdBuffer;
import com.google.android.gms.people.model.ContactGroupPreferredFieldsBuffer;
import com.google.android.gms.people.model.OwnerBuffer;
import com.google.android.gms.people.model.PersonBuffer;
import com.google.android.gms.people.model.PhoneNumberBuffer;
import com.google.android.gsf.TalkContract.OutgoingRmqColumns;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

@VisibleForTesting
public class PeopleClientImpl extends GmsClient<IPeopleService> {
    private static final String SERVICE_DESCRIPTOR = "com.google.android.gms.people.internal.IPeopleService";
    public static final String SERVICE_START_ACTION = "com.google.android.gms.people.service.START";
    private static final String TAG = "PeopleClient";
    private static volatile Bundle sEmailTypeMap;
    private static volatile Bundle sPhoneTypeMap;
    private Long mAutocompleteFeedbackAutoGeneratedSessionId;
    @VisibleForTesting
    public final String mClientApplicationId;
    public final Context mContext;
    private final HashMap<OnDataChanged, OnDataChangedBinderCallback> mDataChangedListeners;
    @VisibleForTesting
    public final String mRealClientPackageName;

    public interface GetByIdListener {
        void onBundleLoaded(int i, Bundle bundle, Bundle bundle2);
    }

    public interface IdentityListListener {
        void onBundleLoaded(int i, Bundle bundle, Bundle bundle2);
    }

    private static final class AggregatedPeopleLoadedResult implements LoadAggregatedPeopleResult {
        private final AggregatedPersonBuffer mPeople;
        private final Status mStatus;

        public AggregatedPeopleLoadedResult(Status status, AggregatedPersonBuffer people) {
            this.mStatus = status;
            this.mPeople = people;
        }

        public AggregatedPersonBuffer getAggregatedPeople() {
            return this.mPeople;
        }

        public Status getStatus() {
            return this.mStatus;
        }

        public void release() {
            if (this.mPeople != null) {
                this.mPeople.close();
            }
        }
    }

    private static final class AutocompleteCallback extends AbstractPeopleCallbacks {
        private final AutocompleteResultListener mListener;

        public AutocompleteCallback(AutocompleteResultListener listener) {
            this.mListener = listener;
        }

        public void onBundleLoaded(int statusCode, Bundle resolution, Bundle content) {
            Status status = PeopleClientImpl.getStatus(statusCode, null, resolution);
            if (content != null) {
                content.setClassLoader(getClass().getClassLoader());
                this.mListener.resultsAvailable(new LoadAutocompleteResultImpl(content.getParcelableArrayList(BundleKeys.AUTOCOMPLETE_AUTOCOMPLETIONS), status, content.getInt(BundleKeys.AUTOCOMPLETE_CALLBACK_NUMBER), content.getInt(BundleKeys.AUTOCOMPLETE_CALLBACK_TOTAL)));
                return;
            }
            this.mListener.failed(status);
        }
    }

    private static final class AutocompleteListLoadedResult implements AutocompleteResult {
        private final AutocompleteBuffer mBuffer;
        private final Status mStatus;

        public AutocompleteListLoadedResult(Status status, AutocompleteBuffer buffer) {
            this.mStatus = status;
            this.mBuffer = buffer;
        }

        public AutocompleteBuffer getAutocompleteEntries() {
            return this.mBuffer;
        }

        public Status getStatus() {
            return this.mStatus;
        }

        public void release() {
            if (this.mBuffer != null) {
                this.mBuffer.close();
            }
        }
    }

    public interface AutocompleteResultListener {
        void failed(Status status);

        void resultsAvailable(LoadAutocompleteResult loadAutocompleteResult);
    }

    private static final class CirclesLoadedResult implements LoadCirclesResult {
        private final CircleBuffer mCircles;
        private final Status mStatus;

        public CirclesLoadedResult(Status status, CircleBuffer circles) {
            this.mStatus = status;
            this.mCircles = circles;
        }

        public CircleBuffer getCircles() {
            return this.mCircles;
        }

        public Status getStatus() {
            return this.mStatus;
        }

        public void release() {
            if (this.mCircles != null) {
                this.mCircles.close();
            }
        }
    }

    private static final class ContactsGaiaIdsLoadedResult implements LoadContactsGaiaIdsResult {
        private final ContactGaiaIdBuffer mContactGaiaId;
        private final Status mStatus;

        public ContactsGaiaIdsLoadedResult(Status status, ContactGaiaIdBuffer contactGaiaId) {
            this.mStatus = status;
            this.mContactGaiaId = contactGaiaId;
        }

        public ContactGaiaIdBuffer getContactsGaiaIds() {
            return this.mContactGaiaId;
        }

        public Status getStatus() {
            return this.mStatus;
        }

        public void release() {
            if (this.mContactGaiaId != null) {
                this.mContactGaiaId.close();
            }
        }
    }

    private static final class DataChangedNotifier implements Notifier<OnDataChanged> {
        private final String mAccount;
        private final String mPageId;
        private final int mScopes;

        public DataChangedNotifier(String account, String pageId, int scopes) {
            this.mAccount = account;
            this.mPageId = pageId;
            this.mScopes = scopes;
        }

        public void notifyListener(OnDataChanged listener) {
            listener.onDataChanged(this.mAccount, this.mPageId, this.mScopes);
        }

        public void onNotifyListenerFailed() {
        }
    }

    public interface LoadAutocompleteResult extends ReleasableResult {
        ArrayList<AutocompletionImpl> getAutocompletions();

        int getCallbackNumber();

        int getCallbackTotal();
    }

    private static final class LoadAutocompleteResultImpl implements LoadAutocompleteResult {
        private final ArrayList<AutocompletionImpl> mAutocompletions;
        private final int mCallbackNumber;
        private final int mCallbackTotal;
        private final Status mStatus;

        public LoadAutocompleteResultImpl(ArrayList<AutocompletionImpl> autocompletions, Status status, int callbackNumber, int callbackTotal) {
            this.mAutocompletions = autocompletions;
            this.mStatus = status;
            this.mCallbackNumber = callbackNumber;
            this.mCallbackTotal = callbackTotal;
        }

        public ArrayList<AutocompletionImpl> getAutocompletions() {
            return this.mAutocompletions;
        }

        public Status getStatus() {
            return this.mStatus;
        }

        public int getCallbackNumber() {
            return this.mCallbackNumber;
        }

        public int getCallbackTotal() {
            return this.mCallbackTotal;
        }

        public void release() {
        }
    }

    private static final class MeLoadedResult implements LoadMeResult {
        private final Bundle mBundle;
        private final Status mStatus;

        public MeLoadedResult(Status status, Bundle bundle) {
            this.mStatus = status;
            this.mBundle = bundle;
        }

        public Status getStatus() {
            return this.mStatus;
        }

        public Bundle getBundle() {
            return this.mBundle;
        }
    }

    private static final class MeSetResult implements SetMeResult {
        private final Bundle mBundle;
        private final Status mStatus;

        public MeSetResult(Status status, Bundle bundle) {
            this.mStatus = status;
            this.mBundle = bundle;
        }

        public Status getStatus() {
            return this.mStatus;
        }

        public Bundle getBundle() {
            return this.mBundle;
        }
    }

    private static final class OnAddCircleFinishedBinderCallback extends AbstractPeopleCallbacks {
        private final ResultHolder<AddCircleResult> mListener;

        public OnAddCircleFinishedBinderCallback(ResultHolder<AddCircleResult> listener) {
            this.mListener = listener;
        }

        public void onBundleLoaded(int statusCode, Bundle resolution, Bundle content) {
            String circleName = null;
            if (PeopleServiceLog.canDebugLog()) {
                PeopleServiceLog.d(PeopleClientImpl.TAG, "Bundle callback: status=" + statusCode + "\nresolution=" + resolution + "\nbundle=" + content);
            }
            Status status = PeopleClientImpl.getStatus(statusCode, null, resolution);
            String circleId = content == null ? null : content.getString(Circles.CIRCLE_ID);
            if (content != null) {
                circleName = content.getString(BundleKeys.CIRCLE_NAME);
            }
            this.mListener.setResult(new OnAddCircleFinishedResult(status, circleId, circleName));
        }
    }

    private static final class OnAddCircleFinishedResult implements AddCircleResult {
        private final String mCircleId;
        private final String mCircleName;
        private final Status mStatus;

        public OnAddCircleFinishedResult(Status status, String circleId, String circleName) {
            this.mStatus = status;
            this.mCircleId = circleId;
            this.mCircleName = circleName;
        }

        public String getCircleId() {
            return this.mCircleId;
        }

        public String getCircleName() {
            return this.mCircleName;
        }

        public Status getStatus() {
            return this.mStatus;
        }
    }

    private static final class OnAddPeopleToCircleFinishedBinderCallback extends AbstractPeopleCallbacks {
        private final ResultHolder<AddPeopleToCircleResult> mListener;

        public OnAddPeopleToCircleFinishedBinderCallback(ResultHolder<AddPeopleToCircleResult> listener) {
            this.mListener = listener;
        }

        public void onBundleLoaded(int statusCode, Bundle resolution, Bundle content) {
            String[] addedPeople = null;
            if (PeopleServiceLog.canDebugLog()) {
                PeopleServiceLog.d(PeopleClientImpl.TAG, "Bundle callback: status=" + statusCode + "\nresolution=" + resolution + "\nbundle=" + content);
            }
            Status status = PeopleClientImpl.getStatus(statusCode, null, resolution);
            String circleId = content == null ? null : content.getString(Circles.CIRCLE_ID);
            String circleName = content == null ? null : content.getString(BundleKeys.CIRCLE_NAME);
            if (content != null) {
                addedPeople = content.getStringArray(BundleKeys.ADDED_PEOPLE);
            }
            this.mListener.setResult(new OnAddPeopleToCircleFinishedResult(status, circleId, circleName, addedPeople));
        }
    }

    private static final class OnAddPeopleToCircleFinishedResult implements AddPeopleToCircleResult {
        private final String[] mAddedPeople;
        private final String mCircleId;
        private final String mCircleName;
        private final Status mStatus;

        public OnAddPeopleToCircleFinishedResult(Status status, String circleId, String circleName, String[] addedPeople) {
            this.mStatus = status;
            this.mCircleId = circleId;
            this.mCircleName = circleName;
            this.mAddedPeople = addedPeople;
        }

        public String getCircleId() {
            return this.mCircleId;
        }

        public String getCircleName() {
            return this.mCircleName;
        }

        public String[] getPeopleQualifiedIds() {
            return this.mAddedPeople;
        }

        public Status getStatus() {
            return this.mStatus;
        }
    }

    private static final class OnAddToCircleConsentLoadedBinderCallback extends AbstractPeopleCallbacks {
        private final ResultHolder<LoadAddToCircleConsentResult> mListener;

        public OnAddToCircleConsentLoadedBinderCallback(ResultHolder<LoadAddToCircleConsentResult> listener) {
            this.mListener = listener;
        }

        public void onBundleLoaded(int statusCode, Bundle resolution, Bundle content) {
            boolean needConsent;
            String consentText;
            String titleText;
            String okText;
            if (PeopleServiceLog.canDebugLog()) {
                PeopleServiceLog.d(PeopleClientImpl.TAG, "Bundle callback: status=" + statusCode + "\nresolution=" + resolution + "\nbundle=" + content);
            }
            Status status = PeopleClientImpl.getStatus(statusCode, null, resolution);
            if (content != null) {
                needConsent = content.getBoolean(BundleKeys.CIRCLES_FIRST_TIME_ADD_NEED_CONSENT);
                consentText = content.getString(BundleKeys.CIRCLES_FIRST_TIME_ADD_TEXT);
                titleText = content.getString(BundleKeys.CIRCLES_FIRST_TIME_ADD_TITLE_TEXT);
                okText = content.getString(BundleKeys.CIRCLES_FIRST_TIME_ADD_OK_TEXT);
            } else {
                needConsent = false;
                consentText = null;
                titleText = null;
                okText = null;
            }
            this.mListener.setResult(new OnAddToCircleConsentLoadedResult(status, needConsent, consentText, titleText, okText));
        }
    }

    private static final class OnAddToCircleConsentLoadedResult implements LoadAddToCircleConsentResult {
        private final String mConsentText;
        private final boolean mNeedConsent;
        private final String mOkText;
        private final Status mStatus;
        private final String mTitleText;

        public OnAddToCircleConsentLoadedResult(Status status, boolean needConsent, String consentText, String titleText, String okText) {
            this.mStatus = status;
            this.mNeedConsent = needConsent;
            this.mConsentText = consentText;
            this.mTitleText = titleText;
            this.mOkText = okText;
        }

        public boolean getShowConsent() {
            return this.mNeedConsent;
        }

        public String getConsentHtml() {
            return this.mConsentText;
        }

        public String getConsentTitleText() {
            return this.mTitleText;
        }

        public String getConsentButtonText() {
            return this.mOkText;
        }

        public Status getStatus() {
            return this.mStatus;
        }
    }

    private static final class OnAggregatedPeopleLoadedBinderCallback extends AbstractPeopleCallbacks {
        private final PeopleAggregator mAggregator;

        public OnAggregatedPeopleLoadedBinderCallback(PeopleAggregator aggregator) {
            this.mAggregator = aggregator;
        }

        public void onDataHoldersLoaded(int statusCode, Bundle resolution, DataHolder[] holders) {
            if (PeopleServiceLog.canDebugLog()) {
                PeopleServiceLog.d(PeopleClientImpl.TAG, "People callback: status=" + statusCode + "\nresolution=" + resolution + "\nholders=" + Arrays.toString(holders));
            }
            this.mAggregator.onPlusPeopleLoaded(PeopleClientImpl.getConnectionResult(statusCode, resolution), holders);
        }
    }

    private static final class OnAutocompleteListLoadedBinderCallback extends AbstractPeopleCallbacks {
        private final ResultHolder<AutocompleteResult> mListener;

        public OnAutocompleteListLoadedBinderCallback(ResultHolder<AutocompleteResult> listener) {
            this.mListener = listener;
        }

        public void onDataHolderLoaded(int statusCode, Bundle resolution, DataHolder holder) {
            AutocompleteBuffer result = null;
            if (PeopleServiceLog.canDebugLog()) {
                PeopleServiceLog.d(PeopleClientImpl.TAG, "Autocomplete callback: status=" + statusCode + "\nresolution=" + resolution + "\nholder=" + holder);
            }
            Status status = PeopleClientImpl.getStatus(statusCode, null, resolution);
            if (holder != null) {
                result = new AutocompleteBuffer(holder);
            }
            this.mListener.setResult(new AutocompleteListLoadedResult(status, result));
        }
    }

    private static final class OnAvatarSetBinderCallback extends AbstractPeopleCallbacks {
        private final ResultHolder<SetAvatarResult> mListener;

        public OnAvatarSetBinderCallback(ResultHolder<SetAvatarResult> listener) {
            this.mListener = listener;
        }

        public void onBundleLoaded(int statusCode, Bundle resolution, Bundle content) {
            String url = null;
            if (PeopleServiceLog.canDebugLog()) {
                PeopleServiceLog.d(PeopleClientImpl.TAG, "Bundle callback: status=" + statusCode + "\nresolution=" + resolution + "\nbundle=" + content);
            }
            Status status = PeopleClientImpl.getStatus(statusCode, null, resolution);
            if (content != null) {
                url = content.getString(BundleKeys.AVATAR_URL);
            }
            this.mListener.setResult(new OnAvatarSetResult(status, url));
        }
    }

    private static final class OnAvatarSetResult implements SetAvatarResult {
        private final Status mStatus;
        private final String mUrl;

        public OnAvatarSetResult(Status status, String url) {
            this.mStatus = status;
            this.mUrl = url;
        }

        public Status getStatus() {
            return this.mStatus;
        }

        public String getUrl() {
            return this.mUrl;
        }
    }

    private static final class OnCirclesLoadedBinderCallback extends AbstractPeopleCallbacks {
        private final ResultHolder<LoadCirclesResult> mListener;

        public OnCirclesLoadedBinderCallback(ResultHolder<LoadCirclesResult> listener) {
            this.mListener = listener;
        }

        public void onDataHolderLoaded(int statusCode, Bundle resolution, DataHolder holder) {
            CircleBuffer result = null;
            if (PeopleServiceLog.canDebugLog()) {
                PeopleServiceLog.d(PeopleClientImpl.TAG, "Circles callback: status=" + statusCode + "\nresolution=" + resolution + "\nholder=" + holder);
            }
            Status status = PeopleClientImpl.getStatus(statusCode, null, resolution);
            if (holder != null) {
                result = new CircleBuffer(holder);
            }
            this.mListener.setResult(new CirclesLoadedResult(status, result));
        }
    }

    private static final class OnContactGroupPreferredFieldsLoadedCallback extends AbstractPeopleCallbacks {
        private final ResultHolder<PreferredFieldsResult> mListener;

        public OnContactGroupPreferredFieldsLoadedCallback(ResultHolder<PreferredFieldsResult> listener) {
            this.mListener = listener;
        }

        public void onDataHolderLoaded(int statusCode, Bundle resolution, DataHolder holder) {
            ContactGroupPreferredFieldsBuffer result = null;
            if (PeopleServiceLog.canDebugLog()) {
                PeopleServiceLog.d(PeopleClientImpl.TAG, "Contact group preferred field callback: status=" + statusCode + "\nresolution=" + resolution + "\nholder=" + holder);
            }
            Status status = PeopleClientImpl.getStatus(statusCode, null, resolution);
            if (holder != null) {
                result = new ContactGroupPreferredFieldsBuffer(holder);
            }
            this.mListener.setResult(new PreferredFieldsLoadedResult(status, result));
        }
    }

    private static final class OnContactsGaiaIdsLoadedBinderCallback extends AbstractPeopleCallbacks {
        private final ResultHolder<LoadContactsGaiaIdsResult> mListener;

        public OnContactsGaiaIdsLoadedBinderCallback(ResultHolder<LoadContactsGaiaIdsResult> listener) {
            this.mListener = listener;
        }

        public void onDataHolderLoaded(int statusCode, Bundle resolution, DataHolder holder) {
            ContactGaiaIdBuffer result = null;
            if (PeopleServiceLog.canDebugLog()) {
                PeopleServiceLog.d(PeopleClientImpl.TAG, "GaiaId callback: status=" + statusCode + "\nresolution=" + resolution + "\nholder=" + holder);
            }
            Status status = PeopleClientImpl.getStatus(statusCode, null, resolution);
            if (holder != null) {
                result = new ContactGaiaIdBuffer(holder);
            }
            this.mListener.setResult(new ContactsGaiaIdsLoadedResult(status, result));
        }
    }

    public static final class OnDataChangedBinderCallback extends AbstractPeopleCallbacks {
        private final ListenerHolder<OnDataChanged> mHolder;

        public OnDataChangedBinderCallback(ListenerHolder<OnDataChanged> holder) {
            this.mHolder = holder;
        }

        public void release() {
            this.mHolder.clear();
        }

        public void onBundleLoaded(int statusCode, Bundle resolution, Bundle content) {
            if (PeopleServiceLog.canDebugLog()) {
                PeopleServiceLog.d(PeopleClientImpl.TAG, "Bundle callback: status=" + statusCode + "\nresolution=" + resolution + "\nbundle=" + content);
            }
            if (statusCode != 0) {
                PeopleServiceLog.w(PeopleClientImpl.TAG, "Non-success data changed callback received.");
            } else {
                this.mHolder.notifyListener(new DataChangedNotifier(content.getString(OutgoingRmqColumns.ACCOUNT_ID), content.getString(BundleKeys.PAGE_GAIA_ID), content.getInt(BundleKeys.SCOPE)));
            }
        }
    }

    private static final class OnGetByIdBinderCallback extends AbstractPeopleCallbacks {
        private final GetByIdListener mListener;

        public OnGetByIdBinderCallback(GetByIdListener listener) {
            this.mListener = listener;
        }

        public synchronized void onBundleLoaded(int statusCode, Bundle resolution, Bundle content) {
            if (PeopleServiceLog.canDebugLog()) {
                PeopleServiceLog.d(PeopleClientImpl.TAG, "GetById callback: status=" + statusCode + "\nresolution=" + resolution + "\ncontent=" + content);
            }
            this.mListener.onBundleLoaded(statusCode, resolution, content);
        }
    }

    private static final class OnIdentityListBinderCallback extends AbstractPeopleCallbacks {
        private final IdentityListListener mListener;

        public OnIdentityListBinderCallback(IdentityListListener listener) {
            this.mListener = listener;
        }

        public synchronized void onBundleLoaded(int statusCode, Bundle resolution, Bundle content) {
            if (PeopleServiceLog.canDebugLog()) {
                PeopleServiceLog.d(PeopleClientImpl.TAG, "identityList callback: status=" + statusCode + "\nresolution=" + resolution + "\ncontent=" + content);
            }
            this.mListener.onBundleLoaded(statusCode, resolution, content);
        }
    }

    private static final class OnInternalCallBinderCallback extends AbstractPeopleCallbacks {
        private final ResultHolder<BundleResult> mListener;

        public OnInternalCallBinderCallback(ResultHolder<BundleResult> listener) {
            this.mListener = listener;
        }

        public void onBundleLoaded(int statusCode, Bundle resolution, Bundle content) {
            if (PeopleServiceLog.canDebugLog()) {
                PeopleServiceLog.d(PeopleClientImpl.TAG, "Bundle callback: status=" + statusCode + "\nresolution=" + resolution + "\nbundle=" + content);
            }
            this.mListener.setResult(new OnInternalCallResult(PeopleClientImpl.getStatus(statusCode, null, resolution), content));
        }
    }

    private static final class OnInternalCallResult implements BundleResult {
        private final Bundle mResult;
        private final Status mStatus;

        public OnInternalCallResult(Status status, Bundle result) {
            this.mStatus = status;
            this.mResult = result;
        }

        public Status getStatus() {
            return this.mStatus;
        }

        public void release() {
        }

        public Bundle getBundle() {
            return this.mResult;
        }
    }

    private static final class OnLoadMeBinderCallback extends AbstractPeopleCallbacks {
        private final ResultHolder<LoadMeResult> mListener;

        public OnLoadMeBinderCallback(ResultHolder<LoadMeResult> listener) {
            this.mListener = listener;
        }

        public void onBundleLoaded(int statusCode, Bundle resolution, Bundle content) {
            if (PeopleServiceLog.canDebugLog()) {
                PeopleServiceLog.d(PeopleClientImpl.TAG, "Bundle callback: status=" + statusCode + "\nresolution=" + resolution + "\nbundle=" + content);
            }
            this.mListener.setResult(new MeLoadedResult(PeopleClientImpl.getStatus(statusCode, null, resolution), content));
        }
    }

    private static final class OnOperationFinishedBinderCallback extends AbstractPeopleCallbacks {
        private final ResultHolder<Result> mListener;

        public OnOperationFinishedBinderCallback(ResultHolder<Result> listener) {
            this.mListener = listener;
        }

        public void onBundleLoaded(int statusCode, Bundle resolution, Bundle content) {
            if (PeopleServiceLog.canDebugLog()) {
                PeopleServiceLog.d(PeopleClientImpl.TAG, "Bundle callback: status=" + statusCode + "\nresolution=" + resolution + "\nbundle=" + content);
            }
            this.mListener.setResult(new OnOperationFinishedResult(PeopleClientImpl.getStatus(statusCode, null, resolution)));
        }
    }

    private static final class OnOperationFinishedResult implements Result {
        private final Status mStatus;

        public OnOperationFinishedResult(Status status) {
            this.mStatus = status;
        }

        public Status getStatus() {
            return this.mStatus;
        }
    }

    private static final class OnOwnersLoadedBinderCallback extends AbstractPeopleCallbacks {
        private final ResultHolder<LoadOwnersResult> mListener;

        public OnOwnersLoadedBinderCallback(ResultHolder<LoadOwnersResult> listener) {
            this.mListener = listener;
        }

        public void onDataHolderLoaded(int statusCode, Bundle resolution, DataHolder holder) {
            OwnerBuffer result = null;
            if (PeopleServiceLog.canDebugLog()) {
                PeopleServiceLog.d(PeopleClientImpl.TAG, "Owner callback: status=" + statusCode + "\nresolution=" + resolution + "\nholder=" + holder);
            }
            Status status = PeopleClientImpl.getStatus(statusCode, null, resolution);
            if (holder != null) {
                result = new OwnerBuffer(holder);
            }
            this.mListener.setResult(new OwnersLoadedResult(status, result));
        }
    }

    private static final class OnParcelFileDescriptorBinderCallback extends AbstractPeopleCallbacks {
        private final ResultHolder<LoadImageResult> mListener;

        public OnParcelFileDescriptorBinderCallback(ResultHolder<LoadImageResult> listener) {
            this.mListener = listener;
        }

        public void onParcelFileDescriptorLoaded(int statusCode, Bundle resolution, ParcelFileDescriptor pfd, Bundle metadata) {
            if (PeopleServiceLog.canDebugLog()) {
                PeopleServiceLog.d(PeopleClientImpl.TAG, "Avatar callback: status=" + statusCode + " resolution=" + resolution + " pfd=" + pfd);
            }
            Status status = PeopleClientImpl.getStatus(statusCode, null, resolution);
            boolean rewindable = false;
            int width = 0;
            int height = 0;
            if (metadata != null) {
                rewindable = metadata.getBoolean(BundleKeys.REWINDABLE);
                width = metadata.getInt(CoverPhotos.WIDTH);
                height = metadata.getInt(CoverPhotos.HEIGHT);
            }
            this.mListener.setResult(new ParcelFileDescriptorLoadedResult(status, pfd, rewindable, width, height));
        }
    }

    private static final class OnPeopleLoadedBinderCallback extends AbstractPeopleCallbacks {
        private final ResultHolder<LoadPeopleResult> mListener;

        public OnPeopleLoadedBinderCallback(ResultHolder<LoadPeopleResult> listener) {
            this.mListener = listener;
        }

        public void onDataHolderLoaded(int statusCode, Bundle resolution, DataHolder holder) {
            if (PeopleServiceLog.canDebugLog()) {
                PeopleServiceLog.d(PeopleClientImpl.TAG, "People callback: status=" + statusCode + "\nresolution=" + resolution + "\nholder=" + holder);
            }
            this.mListener.setResult(new PeopleLoadedResult(PeopleClientImpl.getStatus(statusCode, null, resolution), PeopleClientImpl.newPersonBufferFromHolder(holder)));
        }
    }

    private static final class OnPeopleLoadedLiveBinderCallback extends AbstractPeopleCallbacks {
        private final ResultHolder<LoadPeopleForAspenResult> mListener;

        public OnPeopleLoadedLiveBinderCallback(ResultHolder<LoadPeopleForAspenResult> listener) {
            this.mListener = listener;
        }

        public void onDataHolderLoaded(int statusCode, Bundle resolution, DataHolder holder) {
            String nextPageToken = null;
            if (PeopleServiceLog.canDebugLog()) {
                PeopleServiceLog.d(PeopleClientImpl.TAG, "People callback: status=" + statusCode + "\nresolution=" + resolution + "\nholder=" + holder);
            }
            Status status = PeopleClientImpl.getStatus(statusCode, null, resolution);
            PersonBuffer result = PeopleClientImpl.newPersonBufferFromHolder(holder);
            if (holder != null) {
                nextPageToken = holder.getMetadata().getString(BundleKeys.PAGE_TOKEN);
            }
            this.mListener.setResult(new PeopleLoadedLiveResult(status, result, nextPageToken));
        }
    }

    private static final class OnPhoneNumbersLoadedBinderCallback extends AbstractPeopleCallbacks {
        private final Context mCallbackContext;
        private final ResultHolder<LoadPhoneNumbersResult> mListener;

        public OnPhoneNumbersLoadedBinderCallback(ResultHolder<LoadPhoneNumbersResult> listener, Context context) {
            this.mListener = listener;
            this.mCallbackContext = context;
        }

        public void onDataHolderLoaded(int statusCode, Bundle resolution, DataHolder holder) {
            PhoneNumberBuffer result = null;
            if (PeopleServiceLog.canDebugLog()) {
                PeopleServiceLog.d(PeopleClientImpl.TAG, "Phone number callback: status=" + statusCode + "\nresolution=" + resolution + "\nholder=" + holder);
            }
            Status status = PeopleClientImpl.getStatus(statusCode, null, resolution);
            if (holder != null) {
                result = new PhoneNumberBuffer(holder, this.mCallbackContext);
            }
            this.mListener.setResult(new PhoneNumberLoadedResult(status, result));
        }
    }

    private static final class OnSetMeBinderCallback extends AbstractPeopleCallbacks {
        private final ResultHolder<SetMeResult> mListener;

        public OnSetMeBinderCallback(ResultHolder<SetMeResult> listener) {
            this.mListener = listener;
        }

        public void onBundleLoaded(int statusCode, Bundle resolution, Bundle content) {
            if (PeopleServiceLog.canDebugLog()) {
                PeopleServiceLog.d(PeopleClientImpl.TAG, "Bundle callback: status=" + statusCode + "\nresolution=" + resolution + "\nbundle=" + content);
            }
            this.mListener.setResult(new MeSetResult(PeopleClientImpl.getStatus(statusCode, null, resolution), content));
        }
    }

    private static final class OnUpdatePersonCirclesFinishedBinderCallback extends AbstractPeopleCallbacks {
        private final ResultHolder<UpdatePersonCircleResult> mListener;

        public OnUpdatePersonCirclesFinishedBinderCallback(ResultHolder<UpdatePersonCircleResult> listener) {
            this.mListener = listener;
        }

        public void onBundleLoaded(int statusCode, Bundle resolution, Bundle content) {
            if (PeopleServiceLog.canDebugLog()) {
                PeopleServiceLog.d(PeopleClientImpl.TAG, "Bundle callback: status=" + statusCode + "\nresolution=" + resolution + "\nbundle=" + content);
            }
            Status status = PeopleClientImpl.getStatus(statusCode, null, resolution);
            List<String> added = null;
            List<String> removed = null;
            if (status.isSuccess()) {
                added = content.getStringArrayList(BundleKeys.ADDED_CIRCLES);
                removed = content.getStringArrayList(BundleKeys.REMOVED_CIRCLES);
            }
            this.mListener.setResult(new OnUpdatePersonCirclesFinishedResult(status, added, removed));
        }
    }

    private static final class OnUpdatePersonCirclesFinishedResult implements UpdatePersonCircleResult {
        private final List<String> mAddedCircles;
        private final List<String> mRemovedCircles;
        private final Status mStatus;

        public OnUpdatePersonCirclesFinishedResult(Status status, List<String> addedCircles, List<String> removedCircles) {
            this.mStatus = status;
            this.mAddedCircles = addedCircles;
            this.mRemovedCircles = removedCircles;
        }

        public List<String> getAddedCircles() {
            return this.mAddedCircles;
        }

        public List<String> getRemovedCircles() {
            return this.mRemovedCircles;
        }

        public Status getStatus() {
            return this.mStatus;
        }
    }

    private static final class OwnersLoadedResult implements LoadOwnersResult {
        private final OwnerBuffer mOwners;
        private final Status mStatus;

        public OwnersLoadedResult(Status status, OwnerBuffer owners) {
            this.mStatus = status;
            this.mOwners = owners;
        }

        public OwnerBuffer getOwners() {
            return this.mOwners;
        }

        public Status getStatus() {
            return this.mStatus;
        }

        public void release() {
            if (this.mOwners != null) {
                this.mOwners.close();
            }
        }
    }

    private static final class ParcelFileDescriptorLoadedResult implements LoadImageResult {
        private final int mHeight;
        private final ParcelFileDescriptor mPfd;
        private final boolean mRewindable;
        private final Status mStatus;
        private final int mWidth;

        public ParcelFileDescriptorLoadedResult(Status status, ParcelFileDescriptor pfd, boolean rewindable, int width, int height) {
            this.mStatus = status;
            this.mPfd = pfd;
            this.mRewindable = rewindable;
            this.mWidth = width;
            this.mHeight = height;
        }

        public Status getStatus() {
            return this.mStatus;
        }

        public ParcelFileDescriptor getParcelFileDescriptor() {
            return this.mPfd;
        }

        public boolean isRewindable() {
            return this.mRewindable;
        }

        public int getWidth() {
            return this.mWidth;
        }

        public int getHeight() {
            return this.mHeight;
        }

        public void release() {
            if (this.mPfd != null) {
                IOUtils.closeQuietly(this.mPfd);
            }
        }
    }

    private static final class PeopleAggregatorListener implements Listener {
        private final ResultHolder<LoadAggregatedPeopleResult> mListener;

        public PeopleAggregatorListener(ResultHolder<LoadAggregatedPeopleResult> listener) {
            this.mListener = listener;
        }

        public void onLoaded(int statusCode, Bundle resolution, AggregatedPersonBuffer people) {
            this.mListener.setResult(new AggregatedPeopleLoadedResult(PeopleClientImpl.getStatus(statusCode, null, resolution), people));
        }
    }

    private static final class PeopleForAggregationLoadedBinderCallback extends AbstractPeopleCallbacks {
        private final ResultHolder<LoadPeopleForAggregationResult> mListener;

        public PeopleForAggregationLoadedBinderCallback(ResultHolder<LoadPeopleForAggregationResult> listener) {
            this.mListener = listener;
        }

        public void onDataHoldersLoaded(int statusCode, Bundle resolution, DataHolder[] holders) {
            if (PeopleServiceLog.canDebugLog()) {
                PeopleServiceLog.d(PeopleClientImpl.TAG, "People callback: status=" + statusCode + "\nresolution=" + resolution + "\nholders=" + Arrays.toString(holders));
            }
            Status status = PeopleClientImpl.getStatus(statusCode, null, resolution);
            if (holders != null) {
                this.mListener.setResult(new PeopleForAggregationLoadedResult(status, new PersonForAggregationRawBuffer(holders[0], new PhoneDecoder(PeopleClientImpl.sPhoneTypeMap), new EmailDecoder(PeopleClientImpl.sEmailTypeMap)), new ContactGaiaIdRawBuffer(holders[1])));
            } else {
                this.mListener.setResult(new PeopleForAggregationLoadedResult(status, null, null));
            }
        }
    }

    private static final class PeopleForAggregationLoadedResult implements LoadPeopleForAggregationResult {
        private final ContactGaiaIdRawBuffer mGaiaMap;
        private final PersonForAggregationRawBuffer mPeople;
        private final Status mStatus;

        public PeopleForAggregationLoadedResult(Status status, PersonForAggregationRawBuffer people, ContactGaiaIdRawBuffer gaiaMap) {
            this.mStatus = status;
            this.mPeople = people;
            this.mGaiaMap = gaiaMap;
        }

        public PersonForAggregationRawBuffer getPeople() {
            return this.mPeople;
        }

        public ContactGaiaIdRawBuffer getGaiaMap() {
            return this.mGaiaMap;
        }

        public Bundle getPhoneTypeMapBundle() {
            return PeopleClientImpl.sPhoneTypeMap;
        }

        public Bundle getEmailTypeMapBundle() {
            return PeopleClientImpl.sEmailTypeMap;
        }

        public Status getStatus() {
            return this.mStatus;
        }

        public void release() {
            if (this.mPeople != null) {
                this.mPeople.close();
            }
            if (this.mGaiaMap != null) {
                this.mGaiaMap.close();
            }
        }
    }

    private static final class PeopleLoadedLiveResult implements LoadPeopleForAspenResult {
        private final String mNextPageToken;
        private final PersonBuffer mPeople;
        private final Status mStatus;

        public PeopleLoadedLiveResult(Status status, PersonBuffer people, String nextPageToken) {
            this.mStatus = status;
            this.mPeople = people;
            this.mNextPageToken = nextPageToken;
        }

        public String getNextPageToken() {
            return this.mNextPageToken;
        }

        public PersonBuffer getPeople() {
            return this.mPeople;
        }

        public Status getStatus() {
            return this.mStatus;
        }

        public void release() {
            if (this.mPeople != null) {
                this.mPeople.close();
            }
        }
    }

    private static final class PeopleLoadedResult implements LoadPeopleResult {
        private final PersonBuffer mPeople;
        private final Status mStatus;

        public PeopleLoadedResult(Status status, PersonBuffer people) {
            this.mStatus = status;
            this.mPeople = people;
        }

        public PersonBuffer getPeople() {
            return this.mPeople;
        }

        public Status getStatus() {
            return this.mStatus;
        }

        public void release() {
            if (this.mPeople != null) {
                this.mPeople.close();
            }
        }
    }

    private static final class PhoneNumberLoadedResult implements LoadPhoneNumbersResult {
        private final PhoneNumberBuffer mBuffer;
        private final Status mStatus;

        public PhoneNumberLoadedResult(Status status, PhoneNumberBuffer buffer) {
            this.mStatus = status;
            this.mBuffer = buffer;
        }

        public PhoneNumberBuffer getPhoneNumbers() {
            return this.mBuffer;
        }

        public Status getStatus() {
            return this.mStatus;
        }

        public void release() {
            if (this.mBuffer != null) {
                this.mBuffer.close();
            }
        }
    }

    private static class PreferredFieldsLoadedResult implements PreferredFieldsResult {
        private final ContactGroupPreferredFieldsBuffer mBuffer;
        private final Status mStatus;

        public PreferredFieldsLoadedResult(Status status, ContactGroupPreferredFieldsBuffer buffer) {
            this.mStatus = status;
            this.mBuffer = buffer;
        }

        public ContactGroupPreferredFieldsBuffer getPreferredFields() {
            return this.mBuffer;
        }

        public Status getStatus() {
            return this.mStatus;
        }

        public void release() {
            if (this.mBuffer != null) {
                this.mBuffer.close();
            }
        }
    }

    public PeopleClientImpl(Context context, Looper looper, ConnectionCallbacks connectedListener, OnConnectionFailedListener connectionFailedListener, String clientApplicationId, ClientSettings clientSettings) {
        super(context.getApplicationContext(), looper, 5, clientSettings, connectedListener, connectionFailedListener);
        this.mDataChangedListeners = new HashMap();
        this.mAutocompleteFeedbackAutoGeneratedSessionId = null;
        this.mContext = context;
        this.mClientApplicationId = clientApplicationId;
        this.mRealClientPackageName = clientSettings.getRealClientPackageName();
    }

    public PeopleClientImpl(Context context) {
        super(context.getApplicationContext(), context.getMainLooper(), 5, ClientSettings.createDefault(context));
        this.mDataChangedListeners = new HashMap();
        this.mAutocompleteFeedbackAutoGeneratedSessionId = null;
        this.mContext = context;
        this.mClientApplicationId = null;
        this.mRealClientPackageName = null;
    }

    protected PeopleClientImpl(Context context, Looper looper, GmsClientSupervisor supervisor, GoogleApiAvailability apiAvailability, String clientApplicationId, ClientSettings clientSettings, ConnectionCallbacks connectedListener, OnConnectionFailedListener connectionFailedListener) {
        super(context, looper, supervisor, (GoogleApiAvailabilityLight) apiAvailability, 5, clientSettings, connectedListener, connectionFailedListener);
        this.mDataChangedListeners = new HashMap();
        this.mAutocompleteFeedbackAutoGeneratedSessionId = null;
        this.mContext = context;
        this.mClientApplicationId = clientApplicationId;
        this.mRealClientPackageName = clientSettings.getRealClientPackageName();
    }

    protected IPeopleService getServiceInjected() throws DeadObjectException {
        return (IPeopleService) super.getService();
    }

    protected void checkConnectedInjected() {
        super.checkConnected();
    }

    protected String getStartServiceAction() {
        return SERVICE_START_ACTION;
    }

    protected String getServiceDescriptor() {
        return SERVICE_DESCRIPTOR;
    }

    protected IPeopleService createServiceInterface(IBinder binder) {
        return Stub.asInterface(binder);
    }

    protected Bundle getGetServiceRequestExtraArgs() {
        Bundle extras = new Bundle();
        extras.putString(BundleKeys.SOCIAL_CLIENT_APPLICATION_ID, this.mClientApplicationId);
        extras.putString(BundleKeys.REAL_CLIENT_PACKAGE, this.mRealClientPackageName);
        extras.putBoolean(BundleKeys.SUPPORT_NEW_IMAGE_CALLBACK, true);
        return extras;
    }

    protected void onPostInitHandler(int statusCode, IBinder service, Bundle response, int disconnectCount) {
        if (statusCode == 0 && response != null) {
            setConfiguration(response.getBundle(BundleKeys.POST_INIT_CONFIGURATION));
        }
        super.onPostInitHandler(statusCode, service, response == null ? null : response.getBundle(BundleKeys.POST_INIT_RESOLUTION), disconnectCount);
    }

    public void disconnect() {
        synchronized (this.mDataChangedListeners) {
            if (isConnected()) {
                for (OnDataChangedBinderCallback l : this.mDataChangedListeners.values()) {
                    l.release();
                    try {
                        getServiceInjected().registerDataChangedListener(l, false, null, null, 0);
                    } catch (RemoteException e) {
                        PeopleServiceLog.w(TAG, "Failed to unregister listener", e);
                    } catch (IllegalStateException e2) {
                        PeopleServiceLog.w(TAG, "PeopleService is in unexpected state", e2);
                    }
                }
            }
            this.mDataChangedListeners.clear();
        }
        super.disconnect();
    }

    @VisibleForTesting
    public synchronized void setConfiguration(Bundle b) {
        if (b != null) {
            PeopleAggregator.setUseContactablesApi(b.getBoolean(BundleKeys.USE_CONTACTABLES_API, true));
            PeopleClientFifeImageUrlDecompressor.INSTANCE.configureFromServiceConfigurations(b);
            sEmailTypeMap = b.getBundle(BundleKeys.CONFIG_EMAIL_TYPE_MAP);
            sPhoneTypeMap = b.getBundle(BundleKeys.CONFIG_PHONE_TYPE_MAP);
        }
    }

    private static PendingIntent getResolutionIntent(Bundle resolution) {
        return resolution == null ? null : (PendingIntent) resolution.getParcelable(GmsClient.KEY_PENDING_INTENT);
    }

    private static Status getStatus(int statusCode, String statusMessage, Bundle resolution) {
        return new Status(statusCode, statusMessage, getResolutionIntent(resolution));
    }

    private static ConnectionResult getConnectionResult(int statusCode, Bundle resolution) {
        return new ConnectionResult(statusCode, getResolutionIntent(resolution));
    }

    public OnDataChangedBinderCallback getDataChangedBinderCallback(GoogleApiClient googleApiClient, OnDataChanged l) {
        synchronized (this.mDataChangedListeners) {
            if (this.mDataChangedListeners.containsKey(l)) {
                OnDataChangedBinderCallback onDataChangedBinderCallback = (OnDataChangedBinderCallback) this.mDataChangedListeners.get(l);
                return onDataChangedBinderCallback;
            }
            OnDataChangedBinderCallback c = new OnDataChangedBinderCallback(googleApiClient.registerListener(l));
            this.mDataChangedListeners.put(l, c);
            return c;
        }
    }

    public void registerOnDataChangedListener(OnDataChangedBinderCallback callbackProxy, String account, String pageId, int scopes) throws RemoteException {
        checkConnectedInjected();
        synchronized (this.mDataChangedListeners) {
            getServiceInjected().registerDataChangedListener(callbackProxy, true, account, pageId, scopes);
        }
    }

    public void unregisterOnDataChangedListener(OnDataChanged listener) throws RemoteException {
        synchronized (this.mDataChangedListeners) {
            try {
                checkConnectedInjected();
                if (this.mDataChangedListeners.containsKey(listener)) {
                    OnDataChangedBinderCallback callbackProxy = (OnDataChangedBinderCallback) this.mDataChangedListeners.get(listener);
                    callbackProxy.release();
                    getServiceInjected().registerDataChangedListener(callbackProxy, false, null, null, 0);
                    this.mDataChangedListeners.remove(listener);
                    return;
                }
            } finally {
                this.mDataChangedListeners.remove(listener);
            }
        }
    }

    public void loadOwners(ResultHolder<LoadOwnersResult> listener, boolean loadSingleOwner, boolean includePlusPages, String account, String pageId, int sortOrder) {
        checkConnectedInjected();
        OnOwnersLoadedBinderCallback callbackProxy = new OnOwnersLoadedBinderCallback(listener);
        try {
            getServiceInjected().loadOwners(callbackProxy, loadSingleOwner, includePlusPages, account, pageId, sortOrder);
        } catch (RemoteException e) {
            callbackProxy.onDataHolderLoaded(8, null, null);
        }
    }

    public void loadCircles(ResultHolder<LoadCirclesResult> listener, String account, String pageId, String circleId, int circleType, String circleNamePrefix, boolean getVisibility) {
        checkConnectedInjected();
        OnCirclesLoadedBinderCallback callbackProxy = new OnCirclesLoadedBinderCallback(listener);
        try {
            getServiceInjected().loadCircles(callbackProxy, account, pageId, circleId, circleType, circleNamePrefix, getVisibility);
        } catch (RemoteException e) {
            callbackProxy.onDataHolderLoaded(8, null, null);
        }
    }

    public void loadPeople(ResultHolder<LoadPeopleResult> listener, String account, String pageId, LoadPeopleOptions options) {
        if (options == null) {
            options = LoadPeopleOptions.DEFAULT;
        }
        loadPeople(listener, account, pageId, options.getCircleId(), options.getQualifiedIds(), options.getProjection(), options.isPeopleOnly(), options.getChangedSince(), options.getQuery(), options.getSearchFields(), options.getSortOrder(), options.getExtraColumns());
    }

    private void loadPeople(ResultHolder<LoadPeopleResult> listener, String account, String pageId, String circleId, Collection<String> qualifiedIds, int projection, boolean peopleOnly, long changedSince, String query, int searchFields, int sortOrder, int extraColumns) {
        checkConnectedInjected();
        OnPeopleLoadedBinderCallback callbackProxy = new OnPeopleLoadedBinderCallback(listener);
        try {
            getServiceInjected().loadPeople(callbackProxy, account, pageId, circleId, ArrayUtils.toArrayList((Collection) qualifiedIds), projection, peopleOnly, changedSince, query, searchFields, sortOrder, extraColumns);
        } catch (RemoteException e) {
            callbackProxy.onDataHolderLoaded(8, null, null);
        }
    }

    private static PersonBuffer newPersonBufferFromHolder(DataHolder holder) {
        if (holder == null) {
            return null;
        }
        return new PersonBuffer(holder, new PhoneDecoder(sPhoneTypeMap), new EmailDecoder(sEmailTypeMap));
    }

    public void loadAutocompleteList(ResultHolder<AutocompleteResult> listener, String query, AutocompleteOptions options) {
        checkConnectedInjected();
        OnAutocompleteListLoadedBinderCallback callbackProxy = new OnAutocompleteListLoadedBinderCallback(listener);
        try {
            getServiceInjected().loadAutocompleteList(callbackProxy, options.account, options.pageId, options.isDirectorySearch, options.directoryAccountType, query, options.autocompleteType, options.searchOptions, options.numberOfResults, options.useAndroidContactFallback);
        } catch (RemoteException e) {
            callbackProxy.onDataHolderLoaded(8, null, null);
        }
    }

    public void loadPeopleForAspen(ResultHolder<LoadPeopleForAspenResult> listener, String account, String pageId, LoadPeopleForAspenOptions options) {
        if (options == null) {
            options = LoadPeopleForAspenOptions.DEFAULT;
        }
        loadPeopleForAspen(listener, account, pageId, options.getQuery(), options.getPageSize(), options.getPageToken());
    }

    private void loadPeopleForAspen(ResultHolder<LoadPeopleForAspenResult> listener, String account, String pageId, String query, int pageSize, String pageToken) {
        checkConnectedInjected();
        OnPeopleLoadedLiveBinderCallback callbackProxy = new OnPeopleLoadedLiveBinderCallback(listener);
        try {
            getServiceInjected().loadPeopleLive(callbackProxy, account, pageId, query, pageSize, pageToken);
        } catch (RemoteException e) {
            callbackProxy.onDataHolderLoaded(8, null, null);
        }
    }

    public ICancelToken loadOwnerAvatar(ResultHolder<LoadImageResult> listener, String account, String pageId, int avatarSize, int avatarOptions) {
        OnParcelFileDescriptorBinderCallback callbackProxy = new OnParcelFileDescriptorBinderCallback(listener);
        try {
            return getServiceInjected().loadOwnerAvatar(callbackProxy, account, pageId, avatarSize, avatarOptions);
        } catch (RemoteException e) {
            callbackProxy.onParcelFileDescriptorLoaded(8, null, null, null);
            return null;
        }
    }

    public ICancelToken loadAvatarByUrl(ResultHolder<LoadImageResult> listener, String avatarUrl, int avatarSize, int options) {
        ICancelToken iCancelToken = null;
        checkConnectedInjected();
        OnParcelFileDescriptorBinderCallback callbackProxy = new OnParcelFileDescriptorBinderCallback(listener);
        try {
            iCancelToken = getServiceInjected().loadAvatar(callbackProxy, avatarUrl, avatarSize, options);
        } catch (RemoteException e) {
            callbackProxy.onParcelFileDescriptorLoaded(8, iCancelToken, iCancelToken, iCancelToken);
        }
        return iCancelToken;
    }

    public ICancelToken loadOwnerCoverPhoto(ResultHolder<LoadImageResult> listener, String account, String pageId, int minimumWidth) {
        ICancelToken iCancelToken = null;
        OnParcelFileDescriptorBinderCallback callbackProxy = new OnParcelFileDescriptorBinderCallback(listener);
        try {
            iCancelToken = getServiceInjected().loadOwnerCoverPhoto(callbackProxy, account, pageId, minimumWidth);
        } catch (RemoteException e) {
            callbackProxy.onParcelFileDescriptorLoaded(8, iCancelToken, iCancelToken, iCancelToken);
        }
        return iCancelToken;
    }

    public ICancelToken loadAvatarByReference(ResultHolder<LoadImageResult> listener, AvatarReference avatarReference, LoadImageOptions options) {
        ICancelToken iCancelToken = null;
        checkConnectedInjected();
        OnParcelFileDescriptorBinderCallback callbackProxy = new OnParcelFileDescriptorBinderCallback(listener);
        try {
            iCancelToken = getServiceInjected().loadAvatarByReference(callbackProxy, avatarReference, ParcelableLoadImageOptions.build(options));
        } catch (RemoteException e) {
            callbackProxy.onParcelFileDescriptorLoaded(8, iCancelToken, iCancelToken, iCancelToken);
        }
        return iCancelToken;
    }

    public ICancelToken loadRemoteImage(ResultHolder<LoadImageResult> listener, String avatarUrl) {
        ICancelToken iCancelToken = null;
        checkConnectedInjected();
        OnParcelFileDescriptorBinderCallback callbackProxy = new OnParcelFileDescriptorBinderCallback(listener);
        try {
            iCancelToken = getServiceInjected().loadRemoteImage(callbackProxy, avatarUrl);
        } catch (RemoteException e) {
            callbackProxy.onParcelFileDescriptorLoaded(8, iCancelToken, iCancelToken, iCancelToken);
        }
        return iCancelToken;
    }

    public ICancelToken loadContactThumbnailByContactId(ResultHolder<LoadImageResult> listener, long contactId) {
        ICancelToken iCancelToken = null;
        checkConnectedInjected();
        OnParcelFileDescriptorBinderCallback callbackProxy = new OnParcelFileDescriptorBinderCallback(listener);
        try {
            iCancelToken = getServiceInjected().loadContactImage(callbackProxy, contactId, true);
        } catch (RemoteException e) {
            callbackProxy.onParcelFileDescriptorLoaded(8, iCancelToken, iCancelToken, iCancelToken);
        }
        return iCancelToken;
    }

    public void blockPerson(ResultHolder<Result> listener, String account, String pageId, String gaiaId, boolean blockOrUnblock) {
        checkConnectedInjected();
        OnOperationFinishedBinderCallback callbackProxy = new OnOperationFinishedBinderCallback(listener);
        try {
            getServiceInjected().blockPerson(callbackProxy, account, pageId, gaiaId, blockOrUnblock);
        } catch (RemoteException e) {
            callbackProxy.onBundleLoaded(8, null, null);
        }
    }

    public void starPerson(ResultHolder<Result> listener, String account, String peopleV2PersonId, boolean starOrUnstar) {
        checkConnectedInjected();
        OnOperationFinishedBinderCallback callbackProxy = new OnOperationFinishedBinderCallback(listener);
        try {
            getServiceInjected().starPerson(callbackProxy, account, null, peopleV2PersonId, starOrUnstar);
        } catch (RemoteException e) {
            callbackProxy.onBundleLoaded(8, null, null);
        }
    }

    public void updatePersonCircles(ResultHolder<UpdatePersonCircleResult> listener, String account, String pageId, String qualifiedId, List<String> circleIdsToAdd, List<String> circleIdsToRemove, FavaDiagnosticsEntity startView) {
        checkConnectedInjected();
        OnUpdatePersonCirclesFinishedBinderCallback callbackProxy = new OnUpdatePersonCirclesFinishedBinderCallback(listener);
        try {
            getServiceInjected().updatePersonCircles(callbackProxy, account, pageId, qualifiedId, circleIdsToAdd, circleIdsToRemove, startView);
        } catch (RemoteException e) {
            callbackProxy.onBundleLoaded(8, null, null);
        }
    }

    public void addPeopleToCircle(ResultHolder<AddPeopleToCircleResult> listener, String account, String pageId, String circleId, List<String> qualifiedPeopleIds) {
        checkConnectedInjected();
        OnAddPeopleToCircleFinishedBinderCallback callbackProxy = new OnAddPeopleToCircleFinishedBinderCallback(listener);
        try {
            getServiceInjected().addPeopleToCircle(callbackProxy, account, pageId, circleId, qualifiedPeopleIds);
        } catch (RemoteException e) {
            callbackProxy.onBundleLoaded(8, null, null);
        }
    }

    public void removeCircle(ResultHolder<Result> listener, String account, String pageId, String circleId) {
        checkConnectedInjected();
        OnOperationFinishedBinderCallback callbackProxy = new OnOperationFinishedBinderCallback(listener);
        try {
            getServiceInjected().removeCircle(callbackProxy, account, pageId, circleId);
        } catch (RemoteException e) {
            callbackProxy.onBundleLoaded(8, null, null);
        }
    }

    public void updateCircle(ResultHolder<Result> listener, String account, String pageId, String circleId, String newName, Boolean newEnabledForSharing, String newCircleDescription) {
        checkConnectedInjected();
        OnOperationFinishedBinderCallback callbackProxy = new OnOperationFinishedBinderCallback(listener);
        try {
            getServiceInjected().updateCircle(callbackProxy, account, pageId, circleId, newName, PeopleConstants.booleanToTriState(newEnabledForSharing), newCircleDescription);
        } catch (RemoteException e) {
            callbackProxy.onBundleLoaded(8, null, null);
        }
    }

    public void addCircle(ResultHolder<AddCircleResult> listener, String account, String pageId, String circleName, String circleDescription, boolean enableForSharing) {
        checkConnectedInjected();
        OnAddCircleFinishedBinderCallback callbackProxy = new OnAddCircleFinishedBinderCallback(listener);
        try {
            getServiceInjected().addCircle(callbackProxy, account, pageId, circleName, circleDescription, enableForSharing);
        } catch (RemoteException e) {
            callbackProxy.onBundleLoaded(8, null, null);
        }
    }

    public void loadAddToCircleConsent(ResultHolder<LoadAddToCircleConsentResult> listener, String account, String pageId) {
        checkConnectedInjected();
        OnAddToCircleConsentLoadedBinderCallback callbackProxy = new OnAddToCircleConsentLoadedBinderCallback(listener);
        try {
            getServiceInjected().loadAddToCircleConsent(callbackProxy, account, pageId);
        } catch (RemoteException e) {
            callbackProxy.onBundleLoaded(8, null, null);
        }
    }

    public void setHasShownAddToCircleConsent(ResultHolder<Result> listener, String account, String pageId) {
        checkConnectedInjected();
        OnOperationFinishedBinderCallback callbackProxy = new OnOperationFinishedBinderCallback(listener);
        try {
            getServiceInjected().setHasShownAddToCircleConsent(callbackProxy, account, pageId);
        } catch (RemoteException e) {
            callbackProxy.onBundleLoaded(8, null, null);
        }
    }

    public void setAvatar(ResultHolder<SetAvatarResult> listener, String account, String pageId, Uri uri, boolean insertCameraImage) {
        checkConnectedInjected();
        OnAvatarSetBinderCallback callbackProxy = new OnAvatarSetBinderCallback(listener);
        try {
            getServiceInjected().setAvatar(callbackProxy, account, pageId, uri, insertCameraImage);
        } catch (RemoteException e) {
            callbackProxy.onBundleLoaded(8, null, null);
        }
    }

    public void syncRawContact(Uri rawContactUri) throws RemoteException {
        checkConnectedInjected();
        getServiceInjected().syncRawContact(rawContactUri);
    }

    public void setSyncToContactsEnabled(boolean enable) throws RemoteException {
        checkConnectedInjected();
        getServiceInjected().setSyncToContactsEnabled(enable);
    }

    public boolean isSyncToContactsEnabled() throws RemoteException {
        checkConnectedInjected();
        return getServiceInjected().isSyncToContactsEnabled();
    }

    public void setSyncToContactsSettings(ResultHolder<Result> listener, String account, boolean enableForAccount, String[] syncTarget) {
        checkConnectedInjected();
        OnOperationFinishedBinderCallback callbackProxy = new OnOperationFinishedBinderCallback(listener);
        try {
            getServiceInjected().setSyncToContactsSettings(callbackProxy, account, enableForAccount, syncTarget);
        } catch (RemoteException e) {
            callbackProxy.onBundleLoaded(8, null, null);
        }
    }

    public void requestSync(String account, String pageId, long allowanceSecond, boolean byUserAction, boolean isDisabledByBackgroundSync) throws RemoteException {
        checkConnectedInjected();
        getServiceInjected().requestSync(account, pageId, allowanceSecond, byUserAction, isDisabledByBackgroundSync);
    }

    public void loadAggregatedPeople(ResultHolder<LoadAggregatedPeopleResult> listener, String account, String pageId, boolean includeInvisible, String query, boolean peopleOnly, int projection, int extraColumns, String filterGaiaId, boolean includeEvergreenPeople, int sortOrder) {
        checkConnectedInjected();
        if (sortOrder != 0 && TextUtils.isEmpty(query)) {
            PeopleServiceLog.w(TAG, "Ignoring custom sort order for all aggregation.");
            sortOrder = 0;
        }
        PeopleAggregator aggregator = PeopleAggregator.newAggregator(getContext(), new PeopleAggregatorListener(listener), includeInvisible, extraColumns, sEmailTypeMap, sPhoneTypeMap, query, filterGaiaId);
        OnAggregatedPeopleLoadedBinderCallback callbackProxy = new OnAggregatedPeopleLoadedBinderCallback(aggregator);
        try {
            getServiceInjected().loadPeopleForAggregation(callbackProxy, account, pageId, query, 7, peopleOnly, projection, extraColumns, filterGaiaId, includeEvergreenPeople, sortOrder, 3);
        } catch (RemoteException e) {
            callbackProxy.onDataHoldersLoaded(8, null, null);
        }
        aggregator.startContactsQueryWhenReady();
    }

    public void loadContactsGaiaIds(ResultHolder<LoadContactsGaiaIdsResult> listener, String filterContactInfo, String filterGaiaId, int filterGaiaEdgeTypes) {
        checkConnectedInjected();
        OnContactsGaiaIdsLoadedBinderCallback callbackProxy = new OnContactsGaiaIdsLoadedBinderCallback(listener);
        try {
            getServiceInjected().loadContactsGaiaIds(callbackProxy, filterContactInfo, filterGaiaId, filterGaiaEdgeTypes);
        } catch (RemoteException e) {
            callbackProxy.onDataHolderLoaded(8, null, null);
        }
    }

    public void loadMe(ResultHolder<LoadMeResult> listener, String account, String pageId) {
        checkConnectedInjected();
        OnLoadMeBinderCallback callbackProxy = new OnLoadMeBinderCallback(listener);
        try {
            getServiceInjected().loadMe(callbackProxy, account, pageId);
        } catch (RemoteException e) {
            callbackProxy.onBundleLoaded(8, null, null);
        }
    }

    public void setMe(ResultHolder<SetMeResult> listener, String account, String pageId, Bundle bundle) {
        checkConnectedInjected();
        OnSetMeBinderCallback callbackProxy = new OnSetMeBinderCallback(listener);
        try {
            getServiceInjected().setMe(callbackProxy, account, pageId, bundle);
        } catch (RemoteException e) {
            callbackProxy.onBundleLoaded(8, null, null);
        }
    }

    public void internalCall(ResultHolder<BundleResult> listener, Bundle options) {
        checkConnectedInjected();
        OnInternalCallBinderCallback callbackProxy = new OnInternalCallBinderCallback(listener);
        try {
            getServiceInjected().internalCall(callbackProxy, options);
        } catch (RemoteException e) {
            callbackProxy.onBundleLoaded(8, null, null);
        }
    }

    public Bundle expGetPhoneTypeMapBundle() {
        checkConnectedInjected();
        return sPhoneTypeMap;
    }

    public Bundle expGetEmailTypeMapBundle() {
        checkConnectedInjected();
        return sEmailTypeMap;
    }

    public void expLoadPeopleForAggregation(@Nonnull ResultHolder<LoadPeopleForAggregationResult> listener, @Nonnull String account, @Nullable String pageId, @Nullable String query, int searchFields, boolean peopleOnly, int projection, int extraColumns, @Nullable String filterGaiaId, boolean includeEvergreenPeople, int sortOrder, int filterGaiaEdgeTypes) {
        checkConnectedInjected();
        PeopleForAggregationLoadedBinderCallback callbackProxy = new PeopleForAggregationLoadedBinderCallback(listener);
        try {
            getServiceInjected().loadPeopleForAggregation(callbackProxy, account, pageId, query, searchFields, peopleOnly, projection, extraColumns, filterGaiaId, includeEvergreenPeople, sortOrder, filterGaiaEdgeTypes);
        } catch (RemoteException e) {
            callbackProxy.onDataHoldersLoaded(8, null, null);
        }
    }

    public <PersonType> void getById(GetByIdListener listener, GetOptions options, String... qualifiedIds) {
        Preconditions.checkNotNull(qualifiedIds);
        checkConnectedInjected();
        OnGetByIdBinderCallback callbackProxy = new OnGetByIdBinderCallback(listener);
        try {
            getServiceInjected().identityGetByIds(callbackProxy, new AccountToken(options.firstPartyOptions.accountName, options.firstPartyOptions.pageId), Arrays.asList(qualifiedIds), new ParcelableGetOptions(options));
        } catch (RemoteException e) {
            callbackProxy.onBundleLoaded(8, null, new Bundle());
        }
    }

    public ICancelToken loadAutocompleteResults(@Nonnull AutocompleteResultListener listener, @Nonnull String account, @Nonnull String query, long sessionId, int clientId) {
        ICancelToken iCancelToken = null;
        checkConnectedInjected();
        AutocompleteCallback callbackProxy = new AutocompleteCallback(listener);
        try {
            iCancelToken = getServiceInjected().loadAutocompleteResults(callbackProxy, account, new ParcelableLoadAutocompleteResultsOptions(clientId, sessionId, query));
        } catch (RemoteException e) {
            callbackProxy.onBundleLoaded(8, iCancelToken, iCancelToken);
        }
        return iCancelToken;
    }

    public <PersonType> void identityList(IdentityListListener listener, ListOptions options) {
        checkConnectedInjected();
        OnIdentityListBinderCallback callbackProxy = new OnIdentityListBinderCallback(listener);
        try {
            getServiceInjected().identityList(callbackProxy, new AccountToken(options.firstPartyOptions.accountName, options.firstPartyOptions.pageId), new ParcelableListOptions(options));
        } catch (RemoteException e) {
            callbackProxy.onBundleLoaded(8, null, new Bundle());
        }
    }

    public void sendInteractionFeedback(ResultHolder<Result> listener, String contactItem, int interactionType) {
        checkConnectedInjected();
        OnOperationFinishedBinderCallback callbackProxy = new OnOperationFinishedBinderCallback(listener);
        try {
            getServiceInjected().sendInteractionFeedback(callbackProxy, contactItem, interactionType);
        } catch (RemoteException e) {
            callbackProxy.onBundleLoaded(8, null, null);
        }
    }

    private synchronized long getAutocompleteFeedbackAutoGeneratedSessionId() {
        if (this.mAutocompleteFeedbackAutoGeneratedSessionId == null) {
            initializeNextAutocompleteFeedbackAutoGeneratedSessionId();
        }
        return this.mAutocompleteFeedbackAutoGeneratedSessionId.longValue();
    }

    private synchronized void initializeNextAutocompleteFeedbackAutoGeneratedSessionId() {
        this.mAutocompleteFeedbackAutoGeneratedSessionId = Long.valueOf(PeopleUtils.getRandom(getContext()).nextLong());
    }

    public void sendAutocompleteFeedback(ResultHolder<Result> listener, AutocompleteBuffer autocompleteBuffer, int selectedIndex, int interactionType, long sessionId) {
        checkConnectedInjected();
        Preconditions.checkArgument(!autocompleteBuffer.isClosed(), "AutocompleteBuffer is released.");
        if (sessionId == 0) {
            sessionId = getAutocompleteFeedbackAutoGeneratedSessionId();
        }
        OnOperationFinishedBinderCallback callbackProxy = new OnOperationFinishedBinderCallback(listener);
        try {
            getServiceInjected().sendAutocompleteFeedback(callbackProxy, autocompleteBuffer.getDataHolder(), selectedIndex, interactionType, sessionId);
            if (selectedIndex >= 0) {
                initializeNextAutocompleteFeedbackAutoGeneratedSessionId();
            }
        } catch (RemoteException e) {
            callbackProxy.onBundleLoaded(8, null, null);
            if (selectedIndex >= 0) {
                initializeNextAutocompleteFeedbackAutoGeneratedSessionId();
            }
        } catch (Throwable th) {
            if (selectedIndex >= 0) {
                initializeNextAutocompleteFeedbackAutoGeneratedSessionId();
            }
        }
    }

    public ICancelToken loadPhoneNumbers(ResultHolder<LoadPhoneNumbersResult> listener, String account, Bundle options) {
        ICancelToken iCancelToken = null;
        checkConnectedInjected();
        OnPhoneNumbersLoadedBinderCallback callbackProxy = new OnPhoneNumbersLoadedBinderCallback(listener, this.mContext);
        try {
            iCancelToken = getServiceInjected().loadPhoneNumbers(callbackProxy, account, null, options);
        } catch (RemoteException e) {
            callbackProxy.onBundleLoaded(8, iCancelToken, iCancelToken);
        }
        return iCancelToken;
    }

    public ICancelToken loadContactGroupPreferredFields(ResultHolder<PreferredFieldsResult> listener, String account, String contactGroupId) {
        ICancelToken iCancelToken = null;
        checkConnectedInjected();
        OnContactGroupPreferredFieldsLoadedCallback callbackProxy = new OnContactGroupPreferredFieldsLoadedCallback(listener);
        try {
            iCancelToken = getServiceInjected().loadContactGroupPreferredFields(callbackProxy, account, new ParcelableLoadContactGroupFieldsOptions(contactGroupId));
        } catch (RemoteException e) {
            callbackProxy.onDataHolderLoaded(8, iCancelToken, iCancelToken);
        }
        return iCancelToken;
    }
}
