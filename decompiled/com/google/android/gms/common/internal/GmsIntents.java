package com.google.android.gms.common.internal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.net.Uri.Builder;
import android.support.annotation.Nullable;
import android.support.v4.app.ShareCompat.IntentBuilder;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.AccountPicker;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import com.google.android.gms.common.GoogleSignatureVerifier;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.people.PeopleConstants.PeopleColumnBitmask;
import com.google.android.gms.people.identity.internal.models.DefaultPersonImpl.Metadata;
import com.google.android.gsf.GoogleLoginServiceConstants;

public class GmsIntents {
    public static final String ACTION_FITNESS_APP_DISCONNECTED = "com.google.android.gms.fitness.app_disconnected";
    public static final String ACTION_SET_GMS_ACCOUNT = "com.google.android.gms.common.SET_GMS_ACCOUNT";
    public static final String ACTION_UDC_SETTING_CHANGED = "com.google.android.gms.udc.action.SETTING_CHANGED";
    private static final String ANDROID_WEAR_PACKAGE = "com.google.android.wearable.app";
    public static final String BROADCAST_CIRCLES_CHANGED = "com.google.android.gms.people.BROADCAST_CIRCLES_CHANGED";
    private static final String COMMON_SIGN_IN_ACTION = "com.google.android.gms.signin.action.SIGN_IN";
    public static final String COMMON_SIGN_IN_EXTRA_PACKAGE_NAME = "SIGN_IN_PACKAGE_NAME";
    public static final String COMMON_SIGN_IN_EXTRA_SAVE_DEFAULT_ACCOUNT = "SIGN_IN_SAVE_DEFAULT_ACCOUNT";
    public static final String COMMON_SIGN_IN_EXTRA_SCOPE_ARRAY = "SIGN_IN_SCOPE_ARRAY";
    public static final String EXTRA_ACCOUNT = "com.google.android.gms.fitness.disconnected_account";
    public static final String EXTRA_APP = "com.google.android.gms.fitness.disconnected_app";
    public static final String EXTRA_SET_GMS_ACCOUNT_NAME = "ACCOUNT_NAME";
    public static final String EXTRA_SET_GMS_ACCOUNT_PACKAGE_NAME = "PACKAGE_NAME";
    public static final String EXTRA_UDC_ACCOUNT_NAME = "com.google.android.gms.udc.extra.accountName";
    public static final String EXTRA_UDC_SETTING_ID_LIST = "com.google.android.gms.udc.extra.settingIdList";
    private static final Uri FIND_PEOPLE_URI;
    public static final String GOOGLE_NOW_PACKAGE_NAME = "com.google.android.googlequicksearchbox";
    private static final String MARKET_URI_BASE_INTERNAL = "market://details";
    private static final String MARKET_URI_BASE_PUBLIC = "https://play.google.com/store/apps/details";
    private static final String MARKET_URI_PARAM_CAMPAIGN_ID = "pcampaignid";
    private static final String MARKET_URI_PARAM_ID = "id";
    public static final String MIME_ACTIVITY_DISCONNECT_TYPE = "vnd.google.android.fitness/app_disconnect";
    private static final String PACKAGE_SCHEME = "package";
    public static final String PERMISSION_GMS_INTERNAL_BROADCAST = "com.google.android.gms.permission.INTERNAL_BROADCAST";
    private static final Uri PLUS_BASE_URI;
    private static final String PLUS_PACKAGE = "com.google.android.apps.plus";
    private static final String TAG = "GmsIntents";
    private static final String UPDATE_ANDROID_WEAR_ACTION = "com.google.android.clockwork.home.UPDATE_ANDROID_WEAR_ACTION";
    private static final String VIEW_PROFILE_URI_STRING = "http://plus.google.com/%s/about";

    static {
        PLUS_BASE_URI = Uri.parse("http://plus.google.com/");
        FIND_PEOPLE_URI = PLUS_BASE_URI.buildUpon().appendPath(Metadata.CIRCLES).appendPath("find").build();
    }

    private GmsIntents() {
    }

    public static Intent createSettingsIntent(String packageName) {
        Uri uri = Uri.fromParts(PACKAGE_SCHEME, packageName, null);
        Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.setData(uri);
        return intent;
    }

    public static Intent createDateSettingsIntent() {
        return new Intent("android.settings.DATE_SETTINGS");
    }

    public static Uri getPlayStoreUri(String packageName) {
        return Uri.parse(MARKET_URI_BASE_PUBLIC).buildUpon().appendQueryParameter(MARKET_URI_PARAM_ID, packageName).build();
    }

    private static Uri getInternalPlayStoreUri(String packageName, @Nullable String campaignId) {
        Builder builder = Uri.parse(MARKET_URI_BASE_INTERNAL).buildUpon().appendQueryParameter(MARKET_URI_PARAM_ID, packageName);
        if (!TextUtils.isEmpty(campaignId)) {
            builder.appendQueryParameter(MARKET_URI_PARAM_CAMPAIGN_ID, campaignId);
        }
        return builder.build();
    }

    public static Intent createPlayStoreIntent(String packageName) {
        return createPlayStoreIntent(packageName, null);
    }

    public static Intent createPlayStoreIntent(String packageName, @Nullable String campaignId) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(getInternalPlayStoreUri(packageName, campaignId));
        intent.setPackage(GooglePlayServicesUtil.GOOGLE_PLAY_STORE_PACKAGE);
        intent.addFlags(PeopleColumnBitmask.AFFINITY_5);
        return intent;
    }

    public static Intent createPlayStoreLightPurchaseFlowIntent(Context context, @Nullable String accountName, String packageName) {
        Intent intent = new Intent("com.android.vending.billing.PURCHASE");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.setPackage(GooglePlayServicesUtil.GOOGLE_PLAY_STORE_PACKAGE);
        if (!TextUtils.isEmpty(accountName)) {
            intent.putExtra(GoogleLoginServiceConstants.AUTH_ACCOUNT_KEY, accountName);
        }
        intent.putExtra("backend", 3);
        intent.putExtra("document_type", 1);
        intent.putExtra("full_docid", packageName);
        intent.putExtra("backend_docid", packageName);
        intent.putExtra("offer_type", 1);
        if (isIntentResolvable(context.getPackageManager(), intent)) {
            return intent;
        }
        intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse(String.format("https://play.google.com/store/apps/details?id=%1$s&rdid=%1$s&rdot=%2$d", new Object[]{packageName, Integer.valueOf(1)})));
        intent.setPackage(GooglePlayServicesUtil.GOOGLE_PLAY_STORE_PACKAGE);
        intent.putExtra("use_direct_purchase", true);
        return intent;
    }

    public static Intent createPlayStoreGamesIntent(Context context) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse(GooglePlayServicesUtilLight.GOOGLE_PLAY_STORE_GAMES_URI_STRING));
        intent.addFlags(PeopleColumnBitmask.AFFINITY_5);
        intent.setPackage(GooglePlayServicesUtil.GOOGLE_PLAY_STORE_PACKAGE);
        return makeResolvableIntent(context.getPackageManager(), intent);
    }

    public static Intent createAndroidWearUpdateIntent() {
        Intent intent = new Intent(UPDATE_ANDROID_WEAR_ACTION);
        intent.setPackage(ANDROID_WEAR_PACKAGE);
        return intent;
    }

    public static void sendUdcSettingsChangedBroadcast(Context context, String accountName, int[] settingIds) {
        sendUdcChangeBroadcastForPackage(GooglePlayServicesUtil.GOOGLE_PLAY_SERVICES_PACKAGE, context, accountName, settingIds);
        if (GoogleSignatureVerifier.getInstance().isPackageGoogleSigned(context.getPackageManager(), GOOGLE_NOW_PACKAGE_NAME)) {
            sendUdcChangeBroadcastForPackage(GOOGLE_NOW_PACKAGE_NAME, context, accountName, settingIds);
        } else if (Log.isLoggable(TAG, 5)) {
            Log.w(TAG, "Google now certificate not valid. UDC settings broadcast will not be sent.");
        }
    }

    private static void sendUdcChangeBroadcastForPackage(String packageName, Context context, String accountName, int[] settingIds) {
        Intent intent = new Intent(ACTION_UDC_SETTING_CHANGED).setPackage(packageName).putExtra(EXTRA_UDC_ACCOUNT_NAME, accountName).putExtra(EXTRA_UDC_SETTING_ID_LIST, settingIds);
        if (Log.isLoggable(TAG, 3)) {
            Log.d(TAG, "UDC settings changed, sending broadcast to package " + packageName + " with intent action: " + intent.getAction());
        }
        context.sendBroadcast(intent);
    }

    public static Intent createFindPeopleIntent(Context context) {
        return createPlusIntent(context, FIND_PEOPLE_URI);
    }

    public static Intent createShowProfileIntent(Context context, String profileId) {
        return createPlusIntent(context, Uri.parse(String.format(VIEW_PROFILE_URI_STRING, new Object[]{profileId})));
    }

    private static Intent createPlusIntent(Context context, Uri data) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(data);
        intent.setPackage(PLUS_PACKAGE);
        return isIntentResolvable(context.getPackageManager(), intent) ? intent : createPlayStoreIntent(PLUS_PACKAGE);
    }

    public static Intent createShareOnPlusIntent(Activity activity, String subjectText, String bodyText) {
        Intent intent = IntentBuilder.from(activity).setSubject(subjectText).setText(bodyText).setType("text/plain").getIntent();
        intent.setPackage(PLUS_PACKAGE);
        return isIntentResolvable(activity.getPackageManager(), intent) ? intent : createPlayStoreIntent(PLUS_PACKAGE);
    }

    public static Intent createChooseGmsAccountIntent() {
        return AccountPicker.newChooseAccountIntent(null, null, new String[]{GoogleLoginServiceConstants.ACCOUNT_TYPE}, true, null, null, null, null, true);
    }

    public static Intent createChooseGmsAccountWithConsentIntent(String callingPackageName, Scope[] scopes, boolean saveDefaultAccount) {
        Intent intent = new Intent(COMMON_SIGN_IN_ACTION);
        intent.putExtra(COMMON_SIGN_IN_EXTRA_PACKAGE_NAME, callingPackageName);
        intent.putExtra(COMMON_SIGN_IN_EXTRA_SCOPE_ARRAY, scopes);
        intent.putExtra(COMMON_SIGN_IN_EXTRA_SAVE_DEFAULT_ACCOUNT, saveDefaultAccount);
        return intent;
    }

    public static void sendSetGmsAccountIntent(Context context, String accountName, String packageName) {
        Intent intent = new Intent(ACTION_SET_GMS_ACCOUNT);
        intent.putExtra(EXTRA_SET_GMS_ACCOUNT_NAME, accountName);
        intent.putExtra(EXTRA_SET_GMS_ACCOUNT_PACKAGE_NAME, packageName);
        intent.setPackage(GooglePlayServicesUtil.GOOGLE_PLAY_SERVICES_PACKAGE);
        context.sendBroadcast(intent, PERMISSION_GMS_INTERNAL_BROADCAST);
    }

    private static Intent makeResolvableIntent(PackageManager pm, Intent intent) {
        if (pm.resolveActivity(intent, PeopleColumnBitmask.AFFINITY_2) != null) {
            return intent;
        }
        Intent fallback = new Intent(intent.getAction(), intent.getData());
        fallback.setFlags(intent.getFlags());
        return fallback;
    }

    public static boolean isIntentResolvable(PackageManager pm, Intent intent) {
        return pm.resolveActivity(intent, PeopleColumnBitmask.AFFINITY_2) != null;
    }

    public static Intent getFitnessAppDisconnectedIntent(String packageName, String accountName) {
        Intent intent = new Intent();
        intent.setPackage(GooglePlayServicesUtil.GOOGLE_PLAY_SERVICES_PACKAGE);
        intent.setAction(ACTION_FITNESS_APP_DISCONNECTED);
        intent.setType(MIME_ACTIVITY_DISCONNECT_TYPE);
        intent.putExtra(EXTRA_APP, packageName);
        intent.putExtra(EXTRA_ACCOUNT, accountName);
        return intent;
    }
}
