package com.google.android.gms.common;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.os.Bundle;
import com.google.android.gms.common.internal.Preconditions;

@TargetApi(11)
public class ErrorDialogFragment extends DialogFragment {
    private static final String TAG = "ErrorDialogFragment";
    private OnCancelListener mCancelListener;
    private Dialog mDialog;

    public ErrorDialogFragment() {
        this.mDialog = null;
        this.mCancelListener = null;
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        if (this.mDialog == null) {
            setShowsDialog(false);
        }
        return this.mDialog;
    }

    public void onCancel(DialogInterface dialog) {
        if (this.mCancelListener != null) {
            this.mCancelListener.onCancel(dialog);
        }
    }

    public static ErrorDialogFragment newInstance(Dialog dialog) {
        return newInstance(dialog, null);
    }

    public static ErrorDialogFragment newInstance(Dialog dialog, OnCancelListener cancelListener) {
        ErrorDialogFragment fragment = new ErrorDialogFragment();
        Dialog errorDialog = (Dialog) Preconditions.checkNotNull(dialog, "Cannot display null dialog");
        errorDialog.setOnCancelListener(null);
        errorDialog.setOnDismissListener(null);
        fragment.mDialog = errorDialog;
        if (cancelListener != null) {
            fragment.mCancelListener = cancelListener;
        }
        return fragment;
    }

    public void show(FragmentManager manager, String tag) {
        super.show(manager, tag);
    }
}
