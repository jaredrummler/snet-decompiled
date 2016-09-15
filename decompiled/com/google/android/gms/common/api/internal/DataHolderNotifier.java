package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.internal.ListenerHolder.Notifier;
import com.google.android.gms.common.data.DataHolder;

public abstract class DataHolderNotifier<L> implements Notifier<L> {
    private final DataHolder mDataHolder;

    protected abstract void notifyListener(L l, DataHolder dataHolder);

    protected DataHolderNotifier(DataHolder dataHolder) {
        this.mDataHolder = dataHolder;
    }

    public final void notifyListener(L listener) {
        notifyListener(listener, this.mDataHolder);
    }

    public void onNotifyListenerFailed() {
        if (this.mDataHolder != null) {
            this.mDataHolder.close();
        }
    }
}
