package com.google.android.gms.common.api.internal;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.os.Bundle;
import com.google.android.gms.common.api.ResultStore;

@TargetApi(11)
public class LifecycleTrackingFragment extends Fragment {
    public static final String TAG = "GmsResultStoreFragment";
    private ResultStoreImpl mResultStoreImpl;

    public LifecycleTrackingFragment() {
        this.mResultStoreImpl = new ResultStoreImpl();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        this.mResultStoreImpl.onSaveInstanceState();
    }

    public void onDestroy() {
        super.onDestroy();
        this.mResultStoreImpl.onDestroy(getActivity());
    }

    public ResultStore getPendingResultStore() {
        return this.mResultStoreImpl;
    }
}
