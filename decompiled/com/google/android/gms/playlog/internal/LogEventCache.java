package com.google.android.gms.playlog.internal;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.wireless.android.play.playlog.proto.ClientAnalytics.LogEvent;
import java.util.ArrayList;

@VisibleForTesting
public class LogEventCache {
    private static final int DEFAULT_MAX_NUM_LOGS = 100;
    private static final String TAG = "LogEventCache";
    private final ArrayList<Item> mCache;
    private int mCapacity;

    public static class Item {
        public final LogEvent event;
        public final PlayLoggerContext loggerContext;
        public final LogEvent protoEvent;

        private Item(PlayLoggerContext loggerContext, LogEvent event) {
            this.loggerContext = (PlayLoggerContext) Preconditions.checkNotNull(loggerContext);
            this.event = (LogEvent) Preconditions.checkNotNull(event);
            this.protoEvent = null;
        }

        private Item(PlayLoggerContext loggerContext, LogEvent protoEvent) {
            this.loggerContext = (PlayLoggerContext) Preconditions.checkNotNull(loggerContext);
            this.event = null;
            this.protoEvent = (LogEvent) Preconditions.checkNotNull(protoEvent);
        }
    }

    public LogEventCache() {
        this(DEFAULT_MAX_NUM_LOGS);
    }

    public LogEventCache(int capacity) {
        this.mCache = new ArrayList();
        this.mCapacity = capacity;
    }

    public void add(PlayLoggerContext loggerContext, LogEvent event) {
        this.mCache.add(new Item(event, null));
        pruneToCapacity();
    }

    public void add(PlayLoggerContext loggerContext, LogEvent protoEvent) {
        this.mCache.add(new Item(protoEvent, null));
        pruneToCapacity();
    }

    public ArrayList<Item> get() {
        return this.mCache;
    }

    public void clear() {
        this.mCache.clear();
    }

    public int getSize() {
        return this.mCache.size();
    }

    public int getCapacity() {
        return this.mCapacity;
    }

    public void setCapacity(int capacity) {
        this.mCapacity = capacity;
        pruneToCapacity();
    }

    public boolean isEmpty() {
        return this.mCache.isEmpty();
    }

    public boolean isFull() {
        return getSize() >= getCapacity();
    }

    private void pruneToCapacity() {
        while (getSize() > getCapacity()) {
            this.mCache.remove(0);
        }
    }
}
