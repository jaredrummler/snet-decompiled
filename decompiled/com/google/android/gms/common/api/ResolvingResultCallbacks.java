package com.google.android.gms.common.api;

import android.app.Activity;
import android.content.IntentSender.SendIntentException;
import android.support.annotation.NonNull;
import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;

public abstract class ResolvingResultCallbacks<R extends Result> extends ResultCallbacks<R> {
    private static final String TAG = "ResolvingResultCallback";
    private final Activity mActivity;
    private final int mRequestCode;

    public abstract void onSuccess(@NonNull R r);

    public abstract void onUnresolvableFailure(@NonNull Status status);

    protected ResolvingResultCallbacks(@NonNull Activity activity, int requestCode) {
        this.mActivity = (Activity) Preconditions.checkNotNull(activity, "Activity must not be null");
        this.mRequestCode = requestCode;
    }

    public final void onFailure(@NonNull Status result) {
        if (result.hasResolution()) {
            try {
                result.startResolutionForResult(this.mActivity, this.mRequestCode);
                return;
            } catch (SendIntentException e) {
                Log.e(TAG, "Failed to start resolution", e);
                onUnresolvableFailure(new Status(8));
                return;
            }
        }
        onUnresolvableFailure(result);
    }
}
