package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import com.google.android.gms.common.api.ResultStore;

public class SupportLifecycleTrackingFragment extends Fragment {
    public static final String TAG = "GmsResultStoreFragment";
    private ResultStoreImpl mResultStoreImpl;

    public SupportLifecycleTrackingFragment() {
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
