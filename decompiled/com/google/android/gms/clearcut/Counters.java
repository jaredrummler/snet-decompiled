package com.google.android.gms.clearcut;

import android.util.Log;
import com.google.android.gms.clearcut.ClearcutLogger.LogEventBuilder;
import com.google.android.gms.clearcut.ClearcutLogger.MessageProducer;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.DefaultClock;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.snet.Csv;
import com.google.protobuf.nano.MessageNano;
import com.google.wireless.android.play.playlog.proto.CountersProto.Bucket;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@VisibleForTesting
public class Counters {
    private static final Comparator BYTE_ARRAY_COMPARATOR;
    public static final Alias IDENTITY;
    private static final ResultCallback<Status> NULL_CALLBACK;
    private static final String TAG = "Counters";
    private static final Charset UTF_8;
    @VisibleForTesting
    GoogleApiClient mApiClient;
    private boolean mAutoLogAsync;
    private final ClearcutLogger mClearcutLogger;
    private final Clock mClock;
    private Map<String, AbstractCounter> mCounters;
    private byte[] mDimensionsInstance;
    private Integer mDimensionsInstanceIndex;
    @VisibleForTesting
    TreeMap<byte[], Integer> mDimensionsIntern;
    private final String mLogSourceName;
    private final int mMaxSamplesPerCounter;
    private long mPreSampleTimeMillis;
    private final ReentrantReadWriteLock mReadWriteLock;

    private abstract class AbstractCounter {
        private int mAutoLogAsyncSamplesThreshold;
        Map<Integer, Map<Long, long[]>> mByDimensions;
        private Object mLock;
        private final String mName;
        private int mNumSamples;

        protected AbstractCounter(Counters counters, AbstractCounter counter, boolean swap) {
            this(counter.mName);
            synchronized (counter.mLock) {
                this.mNumSamples = counter.mNumSamples;
                if (swap) {
                    Map<Integer, Map<Long, long[]>> byDimensions = this.mByDimensions;
                    this.mByDimensions = counter.mByDimensions;
                    counter.mByDimensions = byDimensions;
                    counter.mNumSamples = 0;
                    return;
                }
                this.mByDimensions = new HashMap(counter.mByDimensions.size());
                for (Entry<Integer, Map<Long, long[]>> entry : counter.mByDimensions.entrySet()) {
                    Map<Long, long[]> deepCopy = new HashMap(((Map) entry.getValue()).size());
                    for (Entry<Long, long[]> innerEntry : ((Map) entry.getValue()).entrySet()) {
                        deepCopy.put(innerEntry.getKey(), new long[]{((long[]) innerEntry.getValue())[0]});
                    }
                    this.mByDimensions.put(entry.getKey(), deepCopy);
                }
            }
        }

        @VisibleForTesting
        boolean isEmpty() {
            boolean z;
            synchronized (this.mLock) {
                z = this.mNumSamples == 0;
            }
            return z;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("AbstractCounter");
            sb.append("(");
            sb.append(this.mName);
            sb.append(")[");
            synchronized (this.mLock) {
                for (Entry<Integer, Map<Long, long[]>> entry : this.mByDimensions.entrySet()) {
                    sb.append(entry.getKey());
                    sb.append(" -> [");
                    for (Entry<Long, long[]> innerEntry : ((Map) entry.getValue()).entrySet()) {
                        sb.append(innerEntry.getKey());
                        sb.append(" = ");
                        sb.append(((long[]) innerEntry.getValue())[0]);
                        sb.append(", ");
                    }
                    sb.append("], ");
                }
            }
            sb.append("]");
            return sb.toString();
        }

        protected AbstractCounter(String name) {
            this.mAutoLogAsyncSamplesThreshold = Counters.this.mMaxSamplesPerCounter;
            this.mByDimensions = new HashMap();
            this.mLock = new Object();
            if (Counters.this.mCounters.containsKey(name)) {
                throw new IllegalStateException("counter/histogram already exists: " + name);
            }
            Counters.this.mCounters.put(name, this);
            this.mName = name;
        }

        protected final void incrementBase(long key, long increment) {
            boolean switchToWriteLocked = false;
            boolean autoLog = false;
            Counters.this.mReadWriteLock.readLock().lock();
            try {
                if (Counters.this.mDimensionsInstanceIndex == null) {
                    switchToWriteLocked = true;
                } else {
                    autoLog = incrementBaseInternal(key, increment);
                }
                Counters.this.mReadWriteLock.readLock().unlock();
                if (switchToWriteLocked) {
                    autoLog = incrementBaseWriteLocked(key, increment);
                }
                if (autoLog) {
                    PendingResult<Status> result = Counters.this.logAllAsync(Counters.this.mApiClient);
                    if (result != null) {
                        result.setResultCallback(Counters.NULL_CALLBACK);
                    }
                }
            } catch (Throwable th) {
                Counters.this.mReadWriteLock.readLock().unlock();
                if (null != null) {
                    autoLog = incrementBaseWriteLocked(key, increment);
                }
            }
        }

        private boolean incrementBaseWriteLocked(long key, long increment) {
            Lock lock = Counters.this.mReadWriteLock.writeLock();
            lock.lock();
            try {
                Counters.this.mDimensionsInstanceIndex = Counters.this.intern(Counters.this.mDimensionsInstance);
                Counters.this.mReadWriteLock.readLock().lock();
                lock.unlock();
                lock = Counters.this.mReadWriteLock.readLock();
                boolean incrementBaseInternal = incrementBaseInternal(key, increment);
                return incrementBaseInternal;
            } finally {
                lock.unlock();
            }
        }

        private boolean incrementBaseInternal(long key, long increment) {
            boolean z = false;
            synchronized (this.mLock) {
                Map<Long, long[]> countMap = (Map) this.mByDimensions.get(Counters.this.mDimensionsInstanceIndex);
                if (countMap == null) {
                    countMap = new HashMap();
                    this.mByDimensions.put(Counters.this.mDimensionsInstanceIndex, countMap);
                }
                if (this.mNumSamples < Counters.this.mMaxSamplesPerCounter || Counters.this.mAutoLogAsync) {
                    this.mNumSamples++;
                    long[] count = (long[]) countMap.get(Long.valueOf(key));
                    if (count == null) {
                        count = new long[]{0};
                        countMap.put(Long.valueOf(key), count);
                    }
                    count[0] = count[0] + increment;
                    if (Counters.this.mAutoLogAsync && this.mNumSamples >= this.mAutoLogAsyncSamplesThreshold) {
                        z = true;
                    }
                } else {
                    if (this.mNumSamples == Counters.this.mMaxSamplesPerCounter) {
                        Log.i(Counters.TAG, "exceeded sample count in " + this.mName);
                    }
                }
            }
            return z;
        }

        protected void incrementBase(long key) {
            incrementBase(key, 1);
        }

        protected long getCountBase(long key) {
            long j = 0;
            Counters.this.mReadWriteLock.readLock().lock();
            try {
                synchronized (this.mLock) {
                    if (Counters.this.mDimensionsInstanceIndex == null) {
                    } else {
                        Map<Long, long[]> counts = (Map) this.mByDimensions.get(Counters.this.mDimensionsInstanceIndex);
                        if (counts == null) {
                            Counters.this.mReadWriteLock.readLock().unlock();
                        } else {
                            long[] count = (long[]) counts.get(Long.valueOf(key));
                            if (count == null) {
                                Counters.this.mReadWriteLock.readLock().unlock();
                            } else {
                                j = count[0];
                                Counters.this.mReadWriteLock.readLock().unlock();
                            }
                        }
                    }
                }
                return j;
            } finally {
                Counters.this.mReadWriteLock.readLock().unlock();
            }
        }

        public String getName() {
            return this.mName;
        }

        public void setAutoLogAsyncThreshold(int autoLogAsyncSamplesThreshold) {
            boolean z;
            boolean z2 = true;
            if (autoLogAsyncSamplesThreshold > 0) {
                z = true;
            } else {
                z = false;
            }
            Preconditions.checkArgument(z);
            if (autoLogAsyncSamplesThreshold > Counters.this.mMaxSamplesPerCounter) {
                z2 = false;
            }
            Preconditions.checkArgument(z2);
            this.mAutoLogAsyncSamplesThreshold = autoLogAsyncSamplesThreshold;
        }
    }

    private final class AbstractCounterComp implements Comparator<AbstractCounter> {
        private AbstractCounterComp() {
        }

        public int compare(AbstractCounter o1, AbstractCounter o2) {
            return o1.mName.compareTo(o2.mName);
        }

        public boolean equals(Object obj) {
            throw new UnsupportedOperationException("what are you doing?");
        }
    }

    public interface Alias {
        long alias(long j);
    }

    protected class AliasedCounter extends AbstractCounter {
        final Alias mAlias;

        protected AliasedCounter(String name, int alias) {
            super(name);
            if (alias < 1) {
                throw new IllegalArgumentException("bad alias: " + alias);
            }
            this.mAlias = new BucketAlias(alias);
        }

        protected AliasedCounter(String name, Alias alias) {
            super(name);
            this.mAlias = alias;
        }

        private final long alias(long rawKey) {
            return this.mAlias.alias(rawKey);
        }

        protected AliasedCounter(AliasedCounter counter, boolean swap) {
            super(Counters.this, counter, swap);
            this.mAlias = counter.mAlias;
        }

        protected void increment(long key) {
            incrementBase(alias(key), 1);
        }

        protected void incrementBy(long key, long increment) {
            incrementBase(alias(key), increment);
        }

        protected long getCount(long key) {
            return getCountBase(alias(key));
        }
    }

    @VisibleForTesting
    public class BooleanHistogram extends AbstractCounter {
        public /* bridge */ /* synthetic */ String getName() {
            return super.getName();
        }

        public /* bridge */ /* synthetic */ void setAutoLogAsyncThreshold(int i) {
            super.setAutoLogAsyncThreshold(i);
        }

        public /* bridge */ /* synthetic */ String toString() {
            return super.toString();
        }

        private BooleanHistogram(String name) {
            super(name);
        }

        private BooleanHistogram(BooleanHistogram histogram, boolean swap) {
            super(Counters.this, histogram, swap);
        }

        public void increment(boolean key) {
            incrementBase(key ? 1 : 0);
        }

        public long getCount(boolean key) {
            return getCountBase(key ? 1 : 0);
        }
    }

    public static class BucketAlias implements Alias {
        protected final int mAlias;

        public BucketAlias(int alias) {
            if (alias < 1) {
                throw new IllegalArgumentException("bad alias: " + alias);
            }
            this.mAlias = alias;
        }

        public long alias(long rawKey) {
            return ((long) this.mAlias) * (rawKey / ((long) this.mAlias));
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof BucketAlias)) {
                return false;
            }
            if (this.mAlias != ((BucketAlias) other).mAlias) {
                return false;
            }
            return true;
        }
    }

    public static class ClippedBucketAlias extends BucketAlias {
        private final long mMax;
        private final long mMin;

        public ClippedBucketAlias(int alias, int min, int max) {
            super(alias);
            this.mMin = (long) min;
            this.mMax = (long) max;
        }

        public long alias(long rawKey) {
            return super.alias(Math.max(Math.min(rawKey, this.mMax), this.mMin));
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ClippedBucketAlias)) {
                return false;
            }
            if (this.mAlias != ((ClippedBucketAlias) other).mAlias) {
                return false;
            }
            return true;
        }
    }

    @VisibleForTesting
    public class Counter extends AbstractCounter {
        public /* bridge */ /* synthetic */ String getName() {
            return super.getName();
        }

        public /* bridge */ /* synthetic */ void setAutoLogAsyncThreshold(int i) {
            super.setAutoLogAsyncThreshold(i);
        }

        public /* bridge */ /* synthetic */ String toString() {
            return super.toString();
        }

        private Counter(String name) {
            super(name);
        }

        private Counter(Counter counter, boolean swap) {
            super(Counters.this, counter, swap);
        }

        public void increment() {
            incrementBy(1);
        }

        public void incrementBy(long increment) {
            incrementBase(0, increment);
        }

        public long getCount() {
            return getCountBase(0);
        }
    }

    class CountersProducer implements MessageProducer {
        private final Integer mDimensionsIndex;
        private final byte[] mDimensionsInstance;
        private final ArrayList<AbstractCounter> mPopulatedCounters;

        CountersProducer(byte[] dimensionInstance) {
            this.mDimensionsInstance = dimensionInstance;
            this.mDimensionsIndex = (Integer) Counters.this.mDimensionsIntern.get(this.mDimensionsInstance);
            this.mPopulatedCounters = determineCounters(this.mDimensionsIndex);
        }

        public com.google.wireless.android.play.playlog.proto.CountersProto.Counter toProto(AbstractCounter counter) {
            Map<Long, long[]> counters = (Map) counter.mByDimensions.get(this.mDimensionsIndex);
            com.google.wireless.android.play.playlog.proto.CountersProto.Counter protoCounter = new com.google.wireless.android.play.playlog.proto.CountersProto.Counter();
            protoCounter.hashedName = umaMetricHash(counter.mName);
            protoCounter.bucket = new Bucket[counters.size()];
            int i = 0;
            for (Entry<Long, long[]> entry : counters.entrySet()) {
                Bucket protoBucket = new Bucket();
                protoBucket.key = ((Long) entry.getKey()).longValue();
                protoBucket.count = ((long[]) entry.getValue())[0];
                int i2 = i + 1;
                protoCounter.bucket[i] = protoBucket;
                i = i2;
            }
            return protoCounter;
        }

        private ArrayList<AbstractCounter> determineCounters(Integer dimensionIndex) {
            ArrayList<AbstractCounter> result = new ArrayList(Counters.this.mCounters.size());
            for (AbstractCounter counter : Counters.this.mCounters.values()) {
                if (counter.mByDimensions.containsKey(dimensionIndex)) {
                    result.add(counter);
                }
            }
            return result;
        }

        public long umaMetricHash(String name) {
            try {
                MessageDigest md = MessageDigest.getInstance("MD5");
                md.update(name.getBytes(Counters.UTF_8));
                return ByteBuffer.wrap(md.digest()).getLong();
            } catch (NoSuchAlgorithmException impossible) {
                throw new RuntimeException(impossible);
            }
        }

        public com.google.wireless.android.play.playlog.proto.CountersProto.Counters toProto() {
            com.google.wireless.android.play.playlog.proto.CountersProto.Counters protoCounters = new com.google.wireless.android.play.playlog.proto.CountersProto.Counters();
            protoCounters.uptimeMillis = Counters.this.mPreSampleTimeMillis;
            if (this.mDimensionsInstance != null) {
                protoCounters.dimensionsInstance = this.mDimensionsInstance;
            }
            protoCounters.counter = new com.google.wireless.android.play.playlog.proto.CountersProto.Counter[this.mPopulatedCounters.size()];
            int i = 0;
            Iterator i$ = this.mPopulatedCounters.iterator();
            while (i$.hasNext()) {
                protoCounters.counter[i] = toProto((AbstractCounter) i$.next());
                i++;
            }
            return protoCounters;
        }

        public byte[] toProtoBytes() {
            return MessageNano.toByteArray(toProto());
        }

        public String toString() {
            return toProto().toString();
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof CountersProducer)) {
                return false;
            }
            return toProto().equals(((CountersProducer) other).toProto());
        }

        public int hashCode() {
            return 1;
        }
    }

    @VisibleForTesting
    public class IntegerHistogram extends AbstractCounter {
        public /* bridge */ /* synthetic */ String getName() {
            return super.getName();
        }

        public /* bridge */ /* synthetic */ void setAutoLogAsyncThreshold(int i) {
            super.setAutoLogAsyncThreshold(i);
        }

        public /* bridge */ /* synthetic */ String toString() {
            return super.toString();
        }

        private IntegerHistogram(String name) {
            super(name);
        }

        private IntegerHistogram(IntegerHistogram histogram, boolean swap) {
            super(Counters.this, histogram, swap);
        }

        public void increment(int key) {
            incrementBase((long) key);
        }

        public long getCount(int key) {
            return getCountBase((long) key);
        }
    }

    @VisibleForTesting
    public class LongHistogram extends AliasedCounter {
        public /* bridge */ /* synthetic */ String getName() {
            return super.getName();
        }

        public /* bridge */ /* synthetic */ void setAutoLogAsyncThreshold(int i) {
            super.setAutoLogAsyncThreshold(i);
        }

        public /* bridge */ /* synthetic */ String toString() {
            return super.toString();
        }

        private LongHistogram(String name, Alias alias) {
            super(name, alias);
        }

        private LongHistogram(LongHistogram histogram, boolean swap) {
            super((AliasedCounter) histogram, swap);
        }

        public void increment(long key) {
            super.increment(key);
        }

        public void incrementBy(long key, long increment) {
            super.incrementBy(key, increment);
        }

        public long getCount(long key) {
            return super.getCount(key);
        }
    }

    @VisibleForTesting
    public final class Timer {
        private long mStartTimeMillis;

        public Timer() {
            this.mStartTimeMillis = Counters.this.mClock.elapsedRealtime();
        }

        public long reset() {
            long oldValue = this.mStartTimeMillis;
            this.mStartTimeMillis = Counters.this.mClock.elapsedRealtime();
            return oldValue;
        }

        public long getMilliseconds() {
            return Counters.this.mClock.elapsedRealtime() - this.mStartTimeMillis;
        }

        public void incrementTo(TimerHistogram timerHistogram) {
            timerHistogram.increment(getMilliseconds());
        }
    }

    @VisibleForTesting
    public class TimerHistogram extends AliasedCounter {

        @VisibleForTesting
        public class BoundTimer {
            private final TimerHistogram mBound;
            private long mStartTimeMillis;

            private BoundTimer(TimerHistogram timerHistogram) {
                this.mBound = timerHistogram;
                reset();
            }

            public void reset() {
                this.mStartTimeMillis = Counters.this.mClock.elapsedRealtime();
            }

            public long getMilliseconds() {
                return Counters.this.mClock.elapsedRealtime() - this.mStartTimeMillis;
            }

            public void incrementTo() {
                this.mBound.increment(getMilliseconds());
            }
        }

        public /* bridge */ /* synthetic */ String getName() {
            return super.getName();
        }

        public /* bridge */ /* synthetic */ void setAutoLogAsyncThreshold(int i) {
            super.setAutoLogAsyncThreshold(i);
        }

        public /* bridge */ /* synthetic */ String toString() {
            return super.toString();
        }

        private TimerHistogram(String name, Alias alias) {
            super(name, alias);
        }

        private TimerHistogram(TimerHistogram histogram, boolean swap) {
            super((AliasedCounter) histogram, swap);
        }

        public long getCount(long key) {
            return super.getCount(key);
        }

        public BoundTimer newTimer() {
            return new BoundTimer(this, null);
        }
    }

    static {
        UTF_8 = Charset.forName("UTF-8");
        NULL_CALLBACK = new ResultCallback<Status>() {
            public void onResult(Status result) {
            }
        };
        BYTE_ARRAY_COMPARATOR = new Comparator<byte[]>() {
            public int compare(byte[] a, byte[] b) {
                if (a == null && b == null) {
                    return 0;
                }
                if (a == null) {
                    return -1;
                }
                if (b == null) {
                    return 1;
                }
                int l = Math.min(a.length, b.length);
                for (int i = 0; i < l; i++) {
                    if (a[i] != b[i]) {
                        return a[i] - b[i];
                    }
                }
                return a.length - b.length;
            }

            public boolean equals(Object rhs) {
                throw new UnsupportedOperationException("what are you doing?");
            }
        };
        IDENTITY = new BucketAlias(1);
    }

    public Counters(ClearcutLogger logger, String logSourceName, int maxSamplesPerCounter) {
        this(logger, logSourceName, maxSamplesPerCounter, DefaultClock.getInstance());
    }

    private Counters(Counters from, boolean swap) {
        Lock lock;
        this(from.mClearcutLogger, from.mLogSourceName, from.mMaxSamplesPerCounter, from.mClock);
        if (swap) {
            lock = from.mReadWriteLock.writeLock();
        } else {
            lock = from.mReadWriteLock.readLock();
        }
        lock = lock;
        lock.lock();
        try {
            this.mDimensionsInstance = from.mDimensionsInstance;
            this.mDimensionsInstanceIndex = from.mDimensionsInstanceIndex;
            this.mPreSampleTimeMillis = from.mPreSampleTimeMillis;
            this.mCounters = new TreeMap();
            if (swap) {
                for (Entry<String, AbstractCounter> entry : from.mCounters.entrySet()) {
                    this.mCounters.put(entry.getKey(), copy((AbstractCounter) entry.getValue(), swap));
                }
                TreeMap<byte[], Integer> tmp = this.mDimensionsIntern;
                this.mDimensionsIntern = from.mDimensionsIntern;
                from.mDimensionsIntern = tmp;
                from.mDimensionsInstanceIndex = null;
                from.mPreSampleTimeMillis = this.mClock.elapsedRealtime();
            } else {
                for (Entry<String, AbstractCounter> entry2 : from.mCounters.entrySet()) {
                    this.mCounters.put(entry2.getKey(), copy((AbstractCounter) entry2.getValue(), swap));
                }
                this.mDimensionsIntern.putAll(from.mDimensionsIntern);
            }
            lock.unlock();
        } catch (Throwable th) {
            lock.unlock();
        }
    }

    public void setAutoLogAsync(GoogleApiClient apiClient) {
        this.mReadWriteLock.writeLock().lock();
        if (apiClient == null) {
            try {
                this.mAutoLogAsync = false;
            } catch (Throwable th) {
                this.mReadWriteLock.writeLock().unlock();
            }
        } else {
            this.mAutoLogAsync = true;
        }
        this.mApiClient = apiClient;
        this.mReadWriteLock.writeLock().unlock();
    }

    @VisibleForTesting
    boolean isEmpty() {
        this.mReadWriteLock.readLock().lock();
        try {
            for (AbstractCounter counter : this.mCounters.values()) {
                if (!counter.isEmpty()) {
                    return false;
                }
            }
            this.mReadWriteLock.readLock().unlock();
            return true;
        } finally {
            this.mReadWriteLock.readLock().unlock();
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        this.mReadWriteLock.readLock().lock();
        try {
            sb.append("{");
            for (Entry<byte[], Integer> entry : this.mDimensionsIntern.entrySet()) {
                sb.append(entry.getKey() == null ? "null" : new String((byte[]) entry.getKey()));
                sb.append(", ");
            }
            sb.append("}\n");
            for (AbstractCounter counter : this.mCounters.values()) {
                sb.append(counter.toString());
                sb.append(Csv.NEWLINE);
            }
            return sb.toString();
        } finally {
            this.mReadWriteLock.readLock().unlock();
        }
    }

    @VisibleForTesting
    AbstractCounter copy(AbstractCounter counter, boolean swap) {
        if (counter instanceof Counter) {
            return new Counter((Counter) counter, swap, null);
        }
        if (counter instanceof TimerHistogram) {
            return new TimerHistogram((TimerHistogram) counter, swap, null);
        }
        if (counter instanceof IntegerHistogram) {
            return new IntegerHistogram((IntegerHistogram) counter, swap, null);
        }
        if (counter instanceof LongHistogram) {
            return new LongHistogram((LongHistogram) counter, swap, null);
        }
        if (counter instanceof BooleanHistogram) {
            return new BooleanHistogram((BooleanHistogram) counter, swap, null);
        }
        throw new IllegalArgumentException("Unkown counter type: " + counter);
    }

    @VisibleForTesting
    public Counters(ClearcutLogger logger, String logSourceName, int maxSamplesPerCounter, Clock clock) {
        boolean z = true;
        this.mAutoLogAsync = false;
        this.mApiClient = null;
        this.mReadWriteLock = new ReentrantReadWriteLock();
        this.mCounters = new TreeMap();
        this.mDimensionsInstance = null;
        this.mDimensionsInstanceIndex = null;
        this.mDimensionsIntern = new TreeMap(BYTE_ARRAY_COMPARATOR);
        Preconditions.checkNotNull(logger);
        Preconditions.checkNotNull(logSourceName);
        if (maxSamplesPerCounter <= 1) {
            z = false;
        }
        Preconditions.checkArgument(z);
        Preconditions.checkNotNull(clock);
        this.mClearcutLogger = logger;
        this.mLogSourceName = logSourceName;
        this.mMaxSamplesPerCounter = maxSamplesPerCounter;
        this.mClock = clock;
        this.mPreSampleTimeMillis = this.mClock.elapsedRealtime();
    }

    public void setDimensionsInstance(byte[] serializedDimensionsProto) {
        this.mReadWriteLock.writeLock().lock();
        try {
            this.mDimensionsInstance = serializedDimensionsProto;
            this.mDimensionsInstanceIndex = (Integer) this.mDimensionsIntern.get(this.mDimensionsInstance);
        } finally {
            this.mReadWriteLock.writeLock().unlock();
        }
    }

    @VisibleForTesting
    public Collection<byte[]> getDimensionsInstances() {
        this.mReadWriteLock.readLock().lock();
        try {
            Collection<byte[]> arrayList = new ArrayList(this.mDimensionsIntern.keySet());
            return arrayList;
        } finally {
            this.mReadWriteLock.readLock().unlock();
        }
    }

    @VisibleForTesting
    Integer intern(byte[] bytes) {
        Integer result = (Integer) this.mDimensionsIntern.get(bytes);
        if (result != null) {
            return result;
        }
        result = Integer.valueOf(this.mDimensionsIntern.size());
        this.mDimensionsIntern.put(bytes, result);
        return result;
    }

    public Counters snapshotAndReset() {
        return new Counters(this, true);
    }

    @VisibleForTesting
    public Counters snapshot() {
        return new Counters(this, false);
    }

    public Timer newTimer() {
        return new Timer();
    }

    public Counter newCounter(String name) {
        this.mReadWriteLock.writeLock().lock();
        try {
            Counter counter = new Counter(name, null);
            return counter;
        } finally {
            this.mReadWriteLock.writeLock().unlock();
        }
    }

    public BooleanHistogram newBooleanHistogram(String name) {
        this.mReadWriteLock.writeLock().lock();
        try {
            BooleanHistogram booleanHistogram = new BooleanHistogram(name, null);
            return booleanHistogram;
        } finally {
            this.mReadWriteLock.writeLock().unlock();
        }
    }

    public IntegerHistogram newIntegerHistogram(String name) {
        this.mReadWriteLock.writeLock().lock();
        try {
            IntegerHistogram integerHistogram = new IntegerHistogram(name, null);
            return integerHistogram;
        } finally {
            this.mReadWriteLock.writeLock().unlock();
        }
    }

    public LongHistogram newLongHistogram(String name) {
        return newLongHistogram(name, IDENTITY);
    }

    public LongHistogram newLongHistogram(String name, Alias alias) {
        this.mReadWriteLock.writeLock().lock();
        try {
            LongHistogram longHistogram = new LongHistogram(name, alias, null);
            return longHistogram;
        } finally {
            this.mReadWriteLock.writeLock().unlock();
        }
    }

    public TimerHistogram newTimerHistogram(String name) {
        return new TimerHistogram(name, IDENTITY, null);
    }

    public TimerHistogram newTimerHistogram(String name, Alias alias) {
        this.mReadWriteLock.writeLock().lock();
        try {
            TimerHistogram timerHistogram = new TimerHistogram(name, alias, null);
            return timerHistogram;
        } finally {
            this.mReadWriteLock.writeLock().unlock();
        }
    }

    public Counter getCounter(String name) {
        this.mReadWriteLock.writeLock().lock();
        try {
            Counter newCounter;
            AbstractCounter counter = (AbstractCounter) this.mCounters.get(name);
            if (counter == null) {
                newCounter = newCounter(name);
                this.mReadWriteLock.writeLock().unlock();
            } else {
                newCounter = (Counter) counter;
                this.mReadWriteLock.writeLock().unlock();
            }
            return newCounter;
        } catch (ClassCastException e) {
            throw new IllegalArgumentException("another type of counter exists with name: " + name);
        } catch (Throwable th) {
            this.mReadWriteLock.writeLock().unlock();
        }
    }

    public BooleanHistogram getBooleanHistogram(String name) {
        this.mReadWriteLock.writeLock().lock();
        try {
            BooleanHistogram newBooleanHistogram;
            AbstractCounter counter = (AbstractCounter) this.mCounters.get(name);
            if (counter == null) {
                newBooleanHistogram = newBooleanHistogram(name);
                this.mReadWriteLock.writeLock().unlock();
            } else {
                newBooleanHistogram = (BooleanHistogram) counter;
                this.mReadWriteLock.writeLock().unlock();
            }
            return newBooleanHistogram;
        } catch (ClassCastException e) {
            throw new IllegalArgumentException("another type of counter exists with name: " + name);
        } catch (Throwable th) {
            this.mReadWriteLock.writeLock().unlock();
        }
    }

    public IntegerHistogram getIntegerHistogram(String name) {
        this.mReadWriteLock.writeLock().lock();
        try {
            IntegerHistogram newIntegerHistogram;
            AbstractCounter counter = (AbstractCounter) this.mCounters.get(name);
            if (counter == null) {
                newIntegerHistogram = newIntegerHistogram(name);
                this.mReadWriteLock.writeLock().unlock();
            } else {
                newIntegerHistogram = (IntegerHistogram) counter;
                this.mReadWriteLock.writeLock().unlock();
            }
            return newIntegerHistogram;
        } catch (ClassCastException e) {
            throw new IllegalArgumentException("another type of counter exists with name: " + name);
        } catch (Throwable th) {
            this.mReadWriteLock.writeLock().unlock();
        }
    }

    public LongHistogram getLongHistogram(String name) {
        return getLongHistogram(name, IDENTITY);
    }

    public LongHistogram getLongHistogram(String name, Alias alias) {
        this.mReadWriteLock.writeLock().lock();
        try {
            LongHistogram newLongHistogram;
            AbstractCounter counter = (AbstractCounter) this.mCounters.get(name);
            if (counter == null) {
                newLongHistogram = newLongHistogram(name, alias);
                this.mReadWriteLock.writeLock().unlock();
            } else {
                newLongHistogram = (LongHistogram) counter;
                if (alias.equals(newLongHistogram.mAlias)) {
                    this.mReadWriteLock.writeLock().unlock();
                } else {
                    throw new IllegalArgumentException("alias mismatch: " + name);
                }
            }
            return newLongHistogram;
        } catch (ClassCastException e) {
            throw new IllegalArgumentException("another type of counter exists with name: " + name);
        } catch (Throwable th) {
            this.mReadWriteLock.writeLock().unlock();
        }
    }

    public TimerHistogram getTimerHistogram(String name) {
        return getTimerHistogram(name, IDENTITY);
    }

    public TimerHistogram getTimerHistogram(String name, Alias alias) {
        this.mReadWriteLock.writeLock().lock();
        try {
            TimerHistogram newTimerHistogram;
            AbstractCounter counter = (AbstractCounter) this.mCounters.get(name);
            if (counter == null) {
                newTimerHistogram = newTimerHistogram(name, alias);
                this.mReadWriteLock.writeLock().unlock();
            } else {
                newTimerHistogram = (TimerHistogram) counter;
                if (alias.equals(newTimerHistogram.mAlias)) {
                    this.mReadWriteLock.writeLock().unlock();
                } else {
                    throw new IllegalArgumentException("alias mismatch: " + name);
                }
            }
            return newTimerHistogram;
        } catch (ClassCastException e) {
            throw new IllegalArgumentException("another type of counter exists with name: " + name);
        } catch (Throwable th) {
            this.mReadWriteLock.writeLock().unlock();
        }
    }

    public MessageProducer makeProducer(byte[] dimensionInstance) {
        return makeProducerInternal(dimensionInstance);
    }

    @VisibleForTesting
    CountersProducer makeProducerInternal(byte[] dimensionInstance) {
        return new CountersProducer(dimensionInstance);
    }

    public PendingResult<Status> logAll(GoogleApiClient apiClient) {
        return snapshotAndReset().logAllInternal(apiClient);
    }

    private PendingResult<Status> logAllInternal(GoogleApiClient apiClient) {
        PendingResult<Status> result = null;
        for (byte[] value : this.mDimensionsIntern.keySet()) {
            MessageProducer producer = makeProducer(value);
            if (result != null) {
                result.setResultCallback(NULL_CALLBACK);
            }
            result = this.mClearcutLogger.newEvent(producer.toProtoBytes()).setLogSourceName(this.mLogSourceName).log(apiClient);
        }
        return result;
    }

    public PendingResult<Status> logAllAsync(GoogleApiClient apiClient) {
        return snapshotAndReset().logAllAsyncInternal(apiClient);
    }

    public PendingResult<Status> logAllAsync() {
        return snapshotAndReset().logAllAsyncInternal(null);
    }

    private PendingResult<Status> logAllAsyncInternal(GoogleApiClient apiClient) {
        PendingResult<Status> result = null;
        for (byte[] value : this.mDimensionsIntern.keySet()) {
            LogEventBuilder eventBuilder = this.mClearcutLogger.newEvent(makeProducer(value)).setLogSourceName(this.mLogSourceName);
            if (result != null) {
                result.setResultCallback(NULL_CALLBACK);
            }
            result = apiClient != null ? eventBuilder.logAsync(apiClient) : eventBuilder.logAsync();
        }
        return result;
    }
}
