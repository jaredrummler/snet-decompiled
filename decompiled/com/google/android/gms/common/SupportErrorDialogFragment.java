package com.google.android.gms.common;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import com.google.android.gms.common.internal.Preconditions;

public class SupportErrorDialogFragment extends DialogFragment {
    private static final String TAG = "SupportErrorDialogFragment";
    private OnCancelListener mCancelListener;
    private Dialog mDialog;

    public SupportErrorDialogFragment() {
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

    public static SupportErrorDialogFragment newInstance(Dialog dialog) {
        return newInstance(dialog, null);
    }

    public static SupportErrorDialogFragment newInstance(Dialog dialog, OnCancelListener cancelListener) {
        SupportErrorDialogFragment fragment = new SupportErrorDialogFragment();
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
