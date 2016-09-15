package com.google.android.gms.common.internal;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.util.Log;

public class DialogRedirect implements OnClickListener {
    private static final String TAG = "SettingsRedirect";
    private final Activity mActivity;
    private final Fragment mFragment;
    private final Intent mIntent;
    private final int mRequestCode;

    public DialogRedirect(Activity activity, Intent intent, int requestCode) {
        this.mActivity = activity;
        this.mFragment = null;
        this.mIntent = intent;
        this.mRequestCode = requestCode;
    }

    public DialogRedirect(Fragment fragment, Intent intent, int requestCode) {
        this.mActivity = null;
        this.mFragment = fragment;
        this.mIntent = intent;
        this.mRequestCode = requestCode;
    }

    public void onClick(DialogInterface dialog, int which) {
        try {
            if (this.mIntent != null && this.mFragment != null) {
                this.mFragment.startActivityForResult(this.mIntent, this.mRequestCode);
            } else if (this.mIntent != null) {
                this.mActivity.startActivityForResult(this.mIntent, this.mRequestCode);
            }
            dialog.dismiss();
        } catch (ActivityNotFoundException e) {
            Log.e(TAG, "Can't redirect to app settings for Google Play services");
        }
    }
}
