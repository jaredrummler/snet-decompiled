package com.google.android.gms.common.api.internal;

import android.app.Dialog;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.annotation.KeepName;

@KeepName
public class SupportLifecycleFragmentImpl extends SupportLifecycleFragment {

    /* renamed from: com.google.android.gms.common.api.internal.SupportLifecycleFragmentImpl.1 */
    class AnonymousClass1 extends GooglePlayServicesUpdatedReceiver {
        final /* synthetic */ Dialog val$updatingDialog;

        AnonymousClass1(Dialog dialog) {
            this.val$updatingDialog = dialog;
        }

        protected void onUpdated() {
            SupportLifecycleFragmentImpl.this.markErrorsResolved();
            this.val$updatingDialog.dismiss();
        }
    }

    protected GoogleApiAvailability getGoogleApiAvailibility() {
        return GoogleApiAvailability.getInstance();
    }

    protected void onUserResolvableError(int clientId, ConnectionResult result) {
        GooglePlayServicesUtil.showErrorDialogFragment(result.getErrorCode(), getActivity(), this, 2, this);
    }

    protected void onServiceUpdating(int clientId, ConnectionResult result) {
        this.mGmsUpdatedReceiver = GooglePlayServicesUpdatedReceiver.register(getActivity().getApplicationContext(), new AnonymousClass1(getGoogleApiAvailibility().showUpdatingDialog(getActivity(), this)));
    }
}
