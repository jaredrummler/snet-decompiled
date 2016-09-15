package com.google.android.gms.common.audience.widgets;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.RemoteException;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.google.android.gms.common.audience.dynamite.IAudienceView;
import com.google.android.gms.common.audience.dynamite.IAudienceView.Stub;
import com.google.android.gms.common.audience.dynamite.IAudienceViewCallbacks;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.people.data.Audience;
import com.google.android.gms.common.people.data.AudienceMember;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.lint.BuildConfig;

public final class AudienceView extends FrameLayout {
    public static final int EDIT_MODE_CLICK_TO_EDIT = 3;
    public static final int EDIT_MODE_CLICK_TO_REMOVE = 2;
    public static final int EDIT_MODE_READ_ONLY = 1;
    protected static final String PACKAGE_IMPLEMENTATION_CLASS_NAME = "com.google.android.gms.plus.audience.widgets.AudienceViewImpl$DynamiteHost";
    private static final String STATE_IMPLEMENTATION = "impl";
    private static final String STATE_PARENT = "parent";
    private static final String TAG = "AudienceView";
    private static Context sRemoteContext;
    private EditAudienceCallback mEditCallback;
    private final IAudienceView mImplementation;
    private final Context mImplementationContext;
    private RemoveAudienceMemberCallback mRemoveCallback;

    public interface EditAudienceCallback {
        void editAudience();
    }

    private static class LocalImplementation extends Stub {
        private static final String STATE_AUDIENCE = "audience";
        private Audience mAudience;
        private TextView mView;

        private LocalImplementation() {
        }

        public IObjectWrapper getView() {
            return ObjectWrapper.wrap(this.mView);
        }

        public void initialize(IObjectWrapper localContext, IObjectWrapper remoteContext, IAudienceViewCallbacks callbacks) {
            this.mView = new TextView((Context) ObjectWrapper.unwrap(localContext));
        }

        public void onRestoreInstanceState(Bundle state) {
            setAudience((Audience) state.getParcelable(STATE_AUDIENCE));
        }

        public Bundle onSaveInstanceState() {
            Bundle bundle = new Bundle();
            bundle.putParcelable(STATE_AUDIENCE, this.mAudience);
            return bundle;
        }

        public void setAudience(Audience audience) {
            this.mAudience = audience;
            if (this.mAudience == null) {
                this.mView.setText(BuildConfig.VERSION_NAME);
                return;
            }
            String text = null;
            for (AudienceMember member : audience.getAudienceMemberList()) {
                text = (text == null ? BuildConfig.VERSION_NAME : text + ", ") + member.getDisplayName();
            }
            this.mView.setText(text);
        }

        public void setEditMode(int editMode) {
        }

        public void setIsUnderageAccount(boolean isUnderageAccount) {
        }

        public void setShowEmptyText(boolean showEmptyText) {
        }
    }

    public interface RemoveAudienceMemberCallback {
        void removeAudienceMember(AudienceMember audienceMember);
    }

    public AudienceView(Context context) {
        this(context, null, 0);
    }

    public AudienceView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AudienceView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        Pair<IAudienceView, Context> implementation = getImplementation(context);
        this.mImplementation = (IAudienceView) implementation.first;
        this.mImplementationContext = (Context) implementation.second;
        try {
            this.mImplementation.initialize(ObjectWrapper.wrap(getContext()), ObjectWrapper.wrap(this.mImplementationContext), new IAudienceViewCallbacks.Stub() {
                public void removeAudienceMember(AudienceMember audienceMember) {
                    AudienceView.this.mRemoveCallback.removeAudienceMember(audienceMember);
                }

                public void editAudience() {
                    AudienceView.this.mEditCallback.editAudience();
                }
            });
            addView((View) ObjectWrapper.unwrap(this.mImplementation.getView()));
        } catch (RemoteException e) {
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static android.util.Pair<com.google.android.gms.common.audience.dynamite.IAudienceView, android.content.Context> getImplementation(android.content.Context r8) {
        /*
        r5 = sRemoteContext;
        if (r5 != 0) goto L_0x000a;
    L_0x0004:
        r5 = com.google.android.gms.common.GooglePlayServicesUtil.getRemoteContext(r8);
        sRemoteContext = r5;
    L_0x000a:
        r5 = sRemoteContext;
        if (r5 == 0) goto L_0x0041;
    L_0x000e:
        r5 = sRemoteContext;
        r1 = r5.getClassLoader();
        r4 = 0;
        r5 = "com.google.android.gms.plus.audience.widgets.AudienceViewImpl$DynamiteHost";
        r2 = r1.loadClass(r5);	 Catch:{ ClassNotFoundException -> 0x002d, InstantiationException -> 0x004d, IllegalAccessException -> 0x0050 }
        r0 = r2.newInstance();	 Catch:{ ClassNotFoundException -> 0x002d, InstantiationException -> 0x004d, IllegalAccessException -> 0x0050 }
        r0 = (android.os.IBinder) r0;	 Catch:{ ClassNotFoundException -> 0x002d, InstantiationException -> 0x004d, IllegalAccessException -> 0x0050 }
        r5 = new android.util.Pair;	 Catch:{ ClassNotFoundException -> 0x002d, InstantiationException -> 0x004d, IllegalAccessException -> 0x0050 }
        r6 = com.google.android.gms.common.audience.dynamite.IAudienceView.Stub.asInterface(r0);	 Catch:{ ClassNotFoundException -> 0x002d, InstantiationException -> 0x004d, IllegalAccessException -> 0x0050 }
        r7 = sRemoteContext;	 Catch:{ ClassNotFoundException -> 0x002d, InstantiationException -> 0x004d, IllegalAccessException -> 0x0050 }
        r5.<init>(r6, r7);	 Catch:{ ClassNotFoundException -> 0x002d, InstantiationException -> 0x004d, IllegalAccessException -> 0x0050 }
    L_0x002c:
        return r5;
    L_0x002d:
        r3 = move-exception;
        r4 = r3;
    L_0x002f:
        if (r4 == 0) goto L_0x0041;
    L_0x0031:
        r5 = "AudienceView";
        r6 = 3;
        r5 = android.util.Log.isLoggable(r5, r6);
        if (r5 == 0) goto L_0x0041;
    L_0x003a:
        r5 = "AudienceView";
        r6 = "Can't load com.google.android.gms.plus.audience.widgets.AudienceViewImpl$DynamiteHost";
        android.util.Log.d(r5, r6, r4);
    L_0x0041:
        r5 = new android.util.Pair;
        r6 = new com.google.android.gms.common.audience.widgets.AudienceView$LocalImplementation;
        r7 = 0;
        r6.<init>();
        r5.<init>(r6, r8);
        goto L_0x002c;
    L_0x004d:
        r3 = move-exception;
        r4 = r3;
        goto L_0x002f;
    L_0x0050:
        r3 = move-exception;
        r4 = r3;
        goto L_0x002f;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.audience.widgets.AudienceView.getImplementation(android.content.Context):android.util.Pair<com.google.android.gms.common.audience.dynamite.IAudienceView, android.content.Context>");
    }

    public void setAudience(Audience audience) {
        try {
            this.mImplementation.setAudience(audience);
        } catch (RemoteException e) {
        }
    }

    public void setIsUnderageAccount(boolean isUnderageAccount) {
        try {
            this.mImplementation.setIsUnderageAccount(isUnderageAccount);
        } catch (RemoteException e) {
        }
    }

    private void setEditMode(int editMode, EditAudienceCallback editCallback, RemoveAudienceMemberCallback removeCallback) {
        this.mEditCallback = editCallback;
        this.mRemoveCallback = removeCallback;
        try {
            this.mImplementation.setEditMode(editMode);
        } catch (RemoteException e) {
        }
    }

    public void setModeReadonly() {
        setEditMode(EDIT_MODE_READ_ONLY, null, null);
    }

    public void setModeClickToRemove(RemoveAudienceMemberCallback callback) {
        setEditMode(EDIT_MODE_CLICK_TO_REMOVE, null, (RemoveAudienceMemberCallback) Preconditions.checkNotNull(callback));
    }

    public void setModeClickToEdit(EditAudienceCallback callback) {
        setEditMode(EDIT_MODE_CLICK_TO_EDIT, (EditAudienceCallback) Preconditions.checkNotNull(callback), null);
    }

    public void setShowEmptyText(boolean showEmptyText) {
        try {
            this.mImplementation.setShowEmptyText(showEmptyText);
        } catch (RemoteException e) {
        }
    }

    protected void onRestoreInstanceState(Parcelable state) {
        Bundle bundle = (Bundle) state;
        super.onRestoreInstanceState(bundle.getParcelable(STATE_PARENT));
        try {
            this.mImplementation.onRestoreInstanceState(bundle.getBundle(STATE_IMPLEMENTATION));
        } catch (RemoteException e) {
        }
    }

    protected Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable(STATE_PARENT, super.onSaveInstanceState());
        try {
            bundle.putBundle(STATE_IMPLEMENTATION, this.mImplementation.onSaveInstanceState());
        } catch (RemoteException e) {
        }
        return bundle;
    }
}
