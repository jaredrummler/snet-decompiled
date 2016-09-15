package com.google.android.gms.playlog.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.internal.Asserts;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.GmsClient;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.playlog.internal.IPlayLogService.Stub;
import com.google.android.gms.playlog.internal.LogEventCache.Item;
import com.google.protobuf.nano.MessageNano;
import com.google.wireless.android.play.playlog.proto.ClientAnalytics.LogEvent;
import java.util.ArrayList;
import java.util.Iterator;

public class PlayLoggerImpl extends GmsClient<IPlayLogService> {
    private static final boolean DEBUG = false;
    public static final String SERVICE_ACTION = "com.google.android.gms.playlog.service.START";
    private static final String SERVICE_DESCRIPTOR = "com.google.android.gms.playlog.internal.IPlayLogService";
    private static final String TAG = "PlayLoggerImpl";
    private boolean mIsCaching;
    private final Object mLock;
    private final LogEventCache mLogEventCache;
    private final LoggerConnectionCallbacks mLoggerConnectionCallbacks;
    private final String mPackageName;

    public PlayLoggerImpl(Context context, Looper looper, LoggerConnectionCallbacks loggerConnectionCallbacks, ClientSettings clientSettings) {
        super(context, looper, 24, clientSettings, loggerConnectionCallbacks, loggerConnectionCallbacks);
        this.mPackageName = context.getPackageName();
        this.mLoggerConnectionCallbacks = (LoggerConnectionCallbacks) Preconditions.checkNotNull(loggerConnectionCallbacks);
        this.mLoggerConnectionCallbacks.setLoggerImpl(this);
        this.mLogEventCache = new LogEventCache();
        this.mLock = new Object();
        this.mIsCaching = true;
    }

    public LogEventCache getLogEventCache() {
        return this.mLogEventCache;
    }

    protected String getStartServiceAction() {
        return SERVICE_ACTION;
    }

    protected String getServiceDescriptor() {
        return SERVICE_DESCRIPTOR;
    }

    protected IPlayLogService createServiceInterface(IBinder binder) {
        return Stub.asInterface(binder);
    }

    public void start() {
        synchronized (this.mLock) {
            if (isConnecting() || isConnected()) {
                return;
            }
            this.mLoggerConnectionCallbacks.setSendCallbackToListener(true);
            checkAvailabilityAndConnect();
        }
    }

    public void stop() {
        synchronized (this.mLock) {
            this.mLoggerConnectionCallbacks.setSendCallbackToListener(DEBUG);
            disconnect();
        }
    }

    void setIsCaching(boolean isCaching) {
        synchronized (this.mLock) {
            boolean wasCaching = this.mIsCaching;
            this.mIsCaching = isCaching;
            if (wasCaching && !this.mIsCaching) {
                flushCacheToService();
            }
        }
    }

    public void logEvent(PlayLoggerContext loggerContext, LogEvent event) {
        synchronized (this.mLock) {
            if (this.mIsCaching) {
                saveToMemoryCache(loggerContext, event);
            } else {
                sendToService(loggerContext, event);
            }
        }
    }

    public void logEvent(PlayLoggerContext loggerContext, LogEvent protoEvent) {
        synchronized (this.mLock) {
            if (this.mIsCaching) {
                saveToMemoryCache(loggerContext, protoEvent);
            } else {
                sendToService(loggerContext, protoEvent);
            }
        }
    }

    private void saveToMemoryCache(PlayLoggerContext loggerContext, LogEvent event) {
        this.mLogEventCache.add(loggerContext, event);
    }

    private void saveToMemoryCache(PlayLoggerContext loggerContext, LogEvent protoEvent) {
        this.mLogEventCache.add(loggerContext, protoEvent);
    }

    private void sendToService(PlayLoggerContext loggerContext, LogEvent event) {
        try {
            flushCacheToService();
            ((IPlayLogService) getService()).sendLogEvent(this.mPackageName, loggerContext, event);
        } catch (RemoteException e) {
            Log.e(TAG, "Couldn't send log event.  Will try caching.");
            saveToMemoryCache(loggerContext, event);
        } catch (IllegalStateException e2) {
            Log.e(TAG, "Service was disconnected.  Will try caching.");
            saveToMemoryCache(loggerContext, event);
        }
    }

    private void sendToService(PlayLoggerContext loggerContext, LogEvent protoEvent) {
        try {
            flushCacheToService();
            ((IPlayLogService) getService()).sendSerializedLogEvent(this.mPackageName, loggerContext, MessageNano.toByteArray(protoEvent));
        } catch (RemoteException e) {
            Log.e(TAG, "Couldn't send log event.  Will try caching.");
            saveToMemoryCache(loggerContext, protoEvent);
        } catch (IllegalStateException e2) {
            Log.e(TAG, "Service was disconnected.  Will try caching.");
            saveToMemoryCache(loggerContext, protoEvent);
        }
    }

    private void flushCacheToService() {
        Asserts.checkState(!this.mIsCaching ? true : DEBUG);
        if (!this.mLogEventCache.isEmpty()) {
            PlayLoggerContext currentContext = null;
            try {
                ArrayList<LogEvent> eventsToSend = new ArrayList();
                Iterator i$ = this.mLogEventCache.get().iterator();
                while (i$.hasNext()) {
                    Item item = (Item) i$.next();
                    if (item.protoEvent != null) {
                        ((IPlayLogService) getService()).sendSerializedLogEvent(this.mPackageName, item.loggerContext, MessageNano.toByteArray(item.protoEvent));
                    } else if (item.loggerContext.equals(currentContext)) {
                        eventsToSend.add(item.event);
                    } else {
                        if (!eventsToSend.isEmpty()) {
                            ((IPlayLogService) getService()).sendLogEvents(this.mPackageName, currentContext, eventsToSend);
                            eventsToSend.clear();
                        }
                        currentContext = item.loggerContext;
                        eventsToSend.add(item.event);
                    }
                }
                if (!eventsToSend.isEmpty()) {
                    ((IPlayLogService) getService()).sendLogEvents(this.mPackageName, currentContext, eventsToSend);
                }
                this.mLogEventCache.clear();
            } catch (RemoteException e) {
                Log.e(TAG, "Couldn't send cached log events to AndroidLog service.  Retaining in memory cache.");
            }
        }
    }

    public int getCacheSize() {
        int size;
        synchronized (this.mLock) {
            size = this.mLogEventCache.getSize();
        }
        return size;
    }

    public int getCacheCapacity() {
        int capacity;
        synchronized (this.mLock) {
            capacity = this.mLogEventCache.getCapacity();
        }
        return capacity;
    }

    public void setCacheCapacity(int capacity) {
        synchronized (this.mLock) {
            this.mLogEventCache.setCapacity(capacity);
        }
    }

    public boolean isCacheEmpty() {
        boolean isEmpty;
        synchronized (this.mLock) {
            isEmpty = this.mLogEventCache.isEmpty();
        }
        return isEmpty;
    }

    public boolean isCacheFull() {
        boolean isFull;
        synchronized (this.mLock) {
            isFull = this.mLogEventCache.isFull();
        }
        return isFull;
    }
}
