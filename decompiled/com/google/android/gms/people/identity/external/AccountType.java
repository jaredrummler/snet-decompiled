package com.google.android.gms.people.identity.external;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Xml;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.wireless.android.play.playlog.proto.ClientAnalytics.LogRequest.LogSource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

@VisibleForTesting
public class AccountType {
    private static final String ATTR_ACCOUNT_ICON = "accountTypeIcon";
    private static final String ATTR_ACCOUNT_LABEL = "accountTypeLabel";
    private static final String ATTR_ACCOUNT_TYPE = "accountType";
    private static final String ATTR_CREATE_CONTACT_ACTIVITY = "createContactActivity";
    private static final String ATTR_DATA_SET = "dataSet";
    private static final String ATTR_EDIT_CONTACT_ACTIVITY = "editContactActivity";
    private static final String ATTR_EXTENSION_PACKAGE_NAMES = "extensionPackageNames";
    private static final String ATTR_INVITE_CONTACT_ACTION_LABEL = "inviteContactActionLabel";
    private static final String ATTR_INVITE_CONTACT_ACTIVITY = "inviteContactActivity";
    private static final String ATTR_READ_ONLY = "readOnly";
    private static final String ATTR_VIEW_CONTACT_NOTIFY_SERVICE = "viewContactNotifyService";
    private static final String ATTR_VIEW_GROUP_ACTION_LABEL = "viewGroupActionLabel";
    private static final String ATTR_VIEW_GROUP_ACTIVITY = "viewGroupActivity";
    private static final String ATTR_VIEW_STREAM_ITEM_ACTIVITY = "viewStreamItemActivity";
    private static final String ATTR_VIEW_STREAM_ITEM_PHOTO_ACTIVITY = "viewStreamItemPhotoActivity";
    private static final String[] METADATA_CONTACTS_NAMES;
    private static final String SYNC_META_DATA = "android.content.SyncAdapter";
    private static final String TAG = "ExAccountType";
    private static final String TAG_CONTACTS_ACCOUNT_TYPE = "ContactsAccountType";
    private static final String TAG_CONTACTS_DATA_KIND = "ContactsDataKind";
    private static final String TAG_CONTACTS_SOURCE_LEGACY = "ContactsSource";
    public String accountType;
    public String dataSet;
    public int iconRes;
    private String mAccountTypeIconAttribute;
    private String mAccountTypeLabelAttribute;
    private String mCreateContactActivityClassName;
    private String mEditContactActivityClassName;
    private List<String> mExtensionPackageNames;
    private boolean mHasContactsMetadata;
    private String mInviteActionLabelAttribute;
    private int mInviteActionLabelResId;
    private String mInviteContactActivity;
    private final boolean mIsExtension;
    private boolean mIsInitialized;
    private ArrayList<DataKind> mKinds;
    private HashMap<String, DataKind> mMimeKinds;
    private String mReadOnly;
    private String mViewContactNotifyService;
    private String mViewGroupActivity;
    private String mViewGroupLabelAttribute;
    private int mViewGroupLabelResId;
    private String mViewStreamItemActivity;
    private String mViewStreamItemPhotoActivity;
    public String resourcePackageName;
    public int titleRes;

    private static class DefinitionException extends Exception {
        public DefinitionException(String message) {
            super(message);
        }

        public DefinitionException(String message, Exception inner) {
            super(message, inner);
        }
    }

    static {
        METADATA_CONTACTS_NAMES = new String[]{"android.provider.ALTERNATE_CONTACTS_STRUCTURE", "android.provider.CONTACTS_STRUCTURE"};
    }

    public AccountType(Context context, String resourcePackageName, boolean isExtension) {
        this(context, resourcePackageName, isExtension, null);
    }

    AccountType(Context context, String resourcePackageName, boolean isExtension, XmlResourceParser injectedMetadata) {
        XmlResourceParser parser;
        this.accountType = null;
        this.dataSet = null;
        this.titleRes = -1;
        this.iconRes = -1;
        this.mKinds = new ArrayList();
        this.mMimeKinds = new HashMap();
        this.mIsExtension = isExtension;
        this.resourcePackageName = resourcePackageName;
        PackageManager pm = context.getPackageManager();
        if (injectedMetadata == null) {
            parser = loadContactsXml(context, resourcePackageName);
        } else {
            parser = injectedMetadata;
        }
        if (parser != null) {
            try {
                inflate(context, parser);
            } catch (DefinitionException e) {
                StringBuilder error = new StringBuilder();
                error.append("Problem reading XML");
                if (true && parser != null) {
                    error.append(" in line ");
                    error.append(parser.getLineNumber());
                }
                error.append(" for external package ");
                error.append(resourcePackageName);
                Log.e(TAG, error.toString(), e);
                if (parser != null) {
                    parser.close();
                    return;
                }
                return;
            } catch (Throwable th) {
                if (parser != null) {
                    parser.close();
                }
            }
        }
        if (parser != null) {
            parser.close();
        }
        this.mExtensionPackageNames = new ArrayList();
        this.mInviteActionLabelResId = resolveExternalResId(context, this.mInviteActionLabelAttribute, resourcePackageName, ATTR_INVITE_CONTACT_ACTION_LABEL);
        this.mViewGroupLabelResId = resolveExternalResId(context, this.mViewGroupLabelAttribute, resourcePackageName, ATTR_VIEW_GROUP_ACTION_LABEL);
        this.titleRes = resolveExternalResId(context, this.mAccountTypeLabelAttribute, resourcePackageName, ATTR_ACCOUNT_LABEL);
        this.iconRes = resolveExternalResId(context, this.mAccountTypeIconAttribute, resourcePackageName, ATTR_ACCOUNT_ICON);
        this.mIsInitialized = true;
    }

    private XmlResourceParser loadContactsXml(Context context, String resPackageName) {
        PackageManager pm = context.getPackageManager();
        List<ResolveInfo> intentServices = pm.queryIntentServices(new Intent(SYNC_META_DATA).setPackage(resPackageName), LogSource.WORK_STORE);
        if (intentServices != null) {
            for (ResolveInfo resolveInfo : intentServices) {
                ServiceInfo serviceInfo = resolveInfo.serviceInfo;
                if (serviceInfo != null) {
                    String[] arr$ = METADATA_CONTACTS_NAMES;
                    int len$ = arr$.length;
                    int i$ = 0;
                    while (i$ < len$) {
                        String metadataName = arr$[i$];
                        XmlResourceParser parser = serviceInfo.loadXmlMetaData(pm, metadataName);
                        if (parser == null) {
                            i$++;
                        } else if (!Log.isLoggable(TAG, 3)) {
                            return parser;
                        } else {
                            String str = TAG;
                            Object[] objArr = new Object[3];
                            objArr[0] = serviceInfo.packageName;
                            objArr[1] = serviceInfo.name;
                            objArr[2] = metadataName;
                            Log.d(str, String.format("Metadata loaded from: %s, %s, %s", objArr));
                            return parser;
                        }
                    }
                    continue;
                }
            }
        }
        return null;
    }

    public final boolean isInitialized() {
        return this.mIsInitialized;
    }

    public boolean isExtension() {
        return this.mIsExtension;
    }

    public boolean hasContactsMetadata() {
        return this.mHasContactsMetadata;
    }

    public String getEditContactActivityClassName() {
        return this.mEditContactActivityClassName;
    }

    public String getCreateContactActivityClassName() {
        return this.mCreateContactActivityClassName;
    }

    public String getInviteContactActivityClassName() {
        return this.mInviteContactActivity;
    }

    public int getInviteContactActionResId() {
        return this.mInviteActionLabelResId;
    }

    public String getViewContactNotifyServiceClassName() {
        return this.mViewContactNotifyService;
    }

    public String getViewGroupActivity() {
        return this.mViewGroupActivity;
    }

    public int getViewGroupLabelResId() {
        return this.mViewGroupLabelResId;
    }

    public String getViewStreamItemActivity() {
        return this.mViewStreamItemActivity;
    }

    public String getViewStreamItemPhotoActivity() {
        return this.mViewStreamItemPhotoActivity;
    }

    public List<String> getExtensionPackageNames() {
        return this.mExtensionPackageNames;
    }

    public String getReadOnly() {
        return this.mReadOnly;
    }

    public Drawable getDisplayIcon(Context context) {
        if (this.iconRes == -1) {
            return null;
        }
        if (this.resourcePackageName != null) {
            return context.getPackageManager().getDrawable(this.resourcePackageName, this.iconRes, null);
        }
        return context.getResources().getDrawable(this.iconRes);
    }

    public CharSequence getDisplayLabel(Context context) {
        return getResourceText(context, this.resourcePackageName, this.titleRes, this.accountType);
    }

    public DataKind getKindForMimetype(String mimeType) {
        return (DataKind) this.mMimeKinds.get(mimeType);
    }

    public boolean containsMimeType(String mimeType) {
        return this.mMimeKinds.containsKey(mimeType);
    }

    private void inflate(Context context, XmlPullParser parser) throws DefinitionException {
        int type;
        AttributeSet attrs = Xml.asAttributeSet(parser);
        do {
            try {
                type = parser.next();
                if (type == 2) {
                    break;
                }
            } catch (XmlPullParserException e) {
                throw new DefinitionException("Problem reading XML", e);
            } catch (IOException e2) {
                throw new DefinitionException("Problem reading XML", e2);
            }
        } while (type != 1);
        if (type != 2) {
            throw new IllegalStateException("No start tag found");
        }
        String rootTag = parser.getName();
        if (!TAG_CONTACTS_ACCOUNT_TYPE.equals(rootTag)) {
            if (!TAG_CONTACTS_SOURCE_LEGACY.equals(rootTag)) {
                throw new IllegalStateException("Top level element must be ContactsAccountType, not " + rootTag);
            }
        }
        this.mHasContactsMetadata = true;
        int attributeCount = parser.getAttributeCount();
        for (int i = 0; i < attributeCount; i++) {
            String attr = parser.getAttributeName(i);
            String value = parser.getAttributeValue(i);
            if (Log.isLoggable(TAG, 3)) {
                Log.d(TAG, attr + "=" + value);
            }
            if (ATTR_EDIT_CONTACT_ACTIVITY.equals(attr)) {
                this.mEditContactActivityClassName = value;
            } else {
                if (ATTR_CREATE_CONTACT_ACTIVITY.equals(attr)) {
                    this.mCreateContactActivityClassName = value;
                } else {
                    if (ATTR_INVITE_CONTACT_ACTIVITY.equals(attr)) {
                        this.mInviteContactActivity = value;
                    } else {
                        if (ATTR_INVITE_CONTACT_ACTION_LABEL.equals(attr)) {
                            this.mInviteActionLabelAttribute = value;
                        } else {
                            if (ATTR_VIEW_CONTACT_NOTIFY_SERVICE.equals(attr)) {
                                this.mViewContactNotifyService = value;
                            } else {
                                if (ATTR_VIEW_GROUP_ACTIVITY.equals(attr)) {
                                    this.mViewGroupActivity = value;
                                } else {
                                    if (ATTR_VIEW_GROUP_ACTION_LABEL.equals(attr)) {
                                        this.mViewGroupLabelAttribute = value;
                                    } else {
                                        if (ATTR_VIEW_STREAM_ITEM_ACTIVITY.equals(attr)) {
                                            this.mViewStreamItemActivity = value;
                                        } else {
                                            if (ATTR_VIEW_STREAM_ITEM_PHOTO_ACTIVITY.equals(attr)) {
                                                this.mViewStreamItemPhotoActivity = value;
                                            } else {
                                                if (ATTR_DATA_SET.equals(attr)) {
                                                    this.dataSet = value;
                                                } else {
                                                    if (ATTR_EXTENSION_PACKAGE_NAMES.equals(attr)) {
                                                        this.mExtensionPackageNames.add(value);
                                                    } else {
                                                        if (ATTR_ACCOUNT_TYPE.equals(attr)) {
                                                            this.accountType = value;
                                                        } else {
                                                            if (ATTR_ACCOUNT_LABEL.equals(attr)) {
                                                                this.mAccountTypeLabelAttribute = value;
                                                            } else {
                                                                if (ATTR_ACCOUNT_ICON.equals(attr)) {
                                                                    this.mAccountTypeIconAttribute = value;
                                                                } else {
                                                                    if (ATTR_READ_ONLY.equals(attr)) {
                                                                        this.mReadOnly = value;
                                                                    } else {
                                                                        Log.e(TAG, "Unsupported attribute " + attr);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        int startDepth = parser.getDepth();
        while (true) {
            type = parser.next();
            if ((type == 3 && parser.getDepth() <= startDepth) || type == 1) {
                return;
            }
            if (type == 2 && parser.getDepth() == startDepth + 1) {
                String tag = parser.getName();
                if (TAG_CONTACTS_DATA_KIND.equals(tag)) {
                    int i2 = 3;
                    TypedArray a = context.obtainStyledAttributes(attrs, new int[]{16842790, 16843426, 16843427});
                    if (a == null) {
                        Log.e(TAG, "Failed to obtain ContactsDataKind styled attributes");
                    } else {
                        String mimeType = a.getString(0);
                        if (mimeType == null) {
                            Log.e(TAG, "Failed to obtain mimeType from ContactsDataKind styled attributes");
                        } else {
                            DataKind kind = new DataKind();
                            kind.mimeType = mimeType;
                            String summaryColumn = a.getString(1);
                            if (summaryColumn != null) {
                                kind.summaryColumn = summaryColumn;
                            }
                            String detailColumn = a.getString(2);
                            if (detailColumn != null) {
                                kind.detailColumn = detailColumn;
                            }
                            a.recycle();
                            addKind(kind);
                        }
                    }
                }
            }
        }
    }

    private DataKind addKind(DataKind kind) throws DefinitionException {
        if (kind.mimeType == null) {
            throw new DefinitionException("null is not a valid mime type");
        } else if (this.mMimeKinds.get(kind.mimeType) != null) {
            throw new DefinitionException("mime type '" + kind.mimeType + "' is already registered");
        } else {
            kind.resourcePackageName = this.resourcePackageName;
            this.mKinds.add(kind);
            this.mMimeKinds.put(kind.mimeType, kind);
            return kind;
        }
    }

    @VisibleForTesting
    static CharSequence getResourceText(Context context, String packageName, int resId, String defaultValue) {
        if (resId != -1 && packageName != null) {
            return context.getPackageManager().getText(packageName, resId, null);
        }
        if (resId != -1) {
            return context.getText(resId);
        }
        return defaultValue;
    }

    @VisibleForTesting
    static int resolveExternalResId(Context context, String resourceName, String packageName, String xmlAttributeName) {
        if (TextUtils.isEmpty(resourceName)) {
            return -1;
        }
        if (resourceName.charAt(0) != '@') {
            Log.e(TAG, xmlAttributeName + " must be a resource name beginning with '@'");
            return -1;
        }
        try {
            int resId = context.getPackageManager().getResourcesForApplication(packageName).getIdentifier(resourceName.substring(1), null, packageName);
            if (resId != 0) {
                return resId;
            }
            Log.e(TAG, "Unable to load " + resourceName + " from package " + packageName);
            return -1;
        } catch (NameNotFoundException e) {
            Log.e(TAG, "Unable to load package " + packageName);
            return -1;
        }
    }

    public String toString() {
        return String.format("AccountType<accountType=%s, dataSet=%s, resourcePackgeName=%s>", new Object[]{this.accountType, this.dataSet, this.resourcePackageName});
    }
}
