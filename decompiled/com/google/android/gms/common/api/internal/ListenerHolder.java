package com.google.android.gms.common.api.internal;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.google.android.gms.common.internal.Preconditions;

public final class ListenerHolder<L> {
    private final CallbackHandler mHandler;
    private volatile L mListener;

    public interface Notifier<L> {
        void notifyListener(L l);

        void onNotifyListenerFailed();
    }

    private final class CallbackHandler extends Handler {
        public static final int MSG_NOTIFY = 1;

        public CallbackHandler(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message msg) {
            boolean z = true;
            if (msg.what != MSG_NOTIFY) {
                z = false;
            }
            Preconditions.checkArgument(z);
            ListenerHolder.this.notifyListenerInternal((Notifier) msg.obj);
        }
    }

    ListenerHolder(Looper looper, L listener) {
        this.mHandler = new CallbackHandler(looper);
        this.mListener = Preconditions.checkNotNull(listener, "Listener must not be null");
    }

    public void notifyListener(Notifier<? super L> notifier) {
        Preconditions.checkNotNull(notifier, "Notifier must not be null");
        this.mHandler.sendMessage(this.mHandler.obtainMessage(1, notifier));
    }

    public boolean hasListener() {
        return this.mListener != null;
    }

    public void clear() {
        this.mListener = null;
    }

    void notifyListenerInternal(Notifier<? super L> notifier) {
        L listener = this.mListener;
        if (listener == null) {
            notifier.onNotifyListenerFailed();
            return;
        }
        try {
            notifier.notifyListener(listener);
        } catch (RuntimeException e) {
            notifier.onNotifyListenerFailed();
            throw e;
        }
    }
}
