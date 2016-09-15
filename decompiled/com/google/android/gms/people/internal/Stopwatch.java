package com.google.android.gms.people.internal;

import com.google.android.gms.lint.BuildConfig;
import com.google.android.snet.Csv;
import java.util.ArrayList;

public class Stopwatch {
    private final String mLabel;
    private final ArrayList<String> mLapLabels;
    private final ArrayList<Long> mTimes;

    private static class NullStopwatch extends Stopwatch {
        public static final NullStopwatch INSTANCE;

        static {
            INSTANCE = new NullStopwatch();
        }

        public NullStopwatch() {
            super(null);
        }

        public void lap(String lapLabel) {
        }

        public void stopAndLog(String TAG, int timeThresholdToLog) {
        }
    }

    private Stopwatch(String label) {
        this.mTimes = new ArrayList();
        this.mLapLabels = new ArrayList();
        this.mLabel = label;
        lap(BuildConfig.VERSION_NAME);
    }

    public static Stopwatch start(String label) {
        return new Stopwatch(label);
    }

    public synchronized void lap(String lapLabel) {
        this.mTimes.add(Long.valueOf(System.currentTimeMillis()));
        this.mLapLabels.add(lapLabel);
    }

    public synchronized void stopAndLog(String TAG, int timeThresholdToLog) {
        lap(BuildConfig.VERSION_NAME);
        long start = ((Long) this.mTimes.get(0)).longValue();
        long total = ((Long) this.mTimes.get(this.mTimes.size() - 1)).longValue() - start;
        if (total >= ((long) timeThresholdToLog)) {
            StringBuilder sb = PeopleUtils.getTemporaryStringBuilder();
            sb.append(this.mLabel);
            sb.append(Csv.COMMA);
            sb.append(total);
            sb.append("ms: ");
            long last = start;
            for (int i = 1; i < this.mTimes.size(); i++) {
                long current = ((Long) this.mTimes.get(i)).longValue();
                sb.append((String) this.mLapLabels.get(i));
                sb.append(Csv.COMMA);
                sb.append(current - last);
                sb.append("ms ");
                last = current;
            }
            PeopleServiceLog.v(TAG, sb.toString());
        }
    }

    public static Stopwatch getNullStopWatch() {
        return NullStopwatch.INSTANCE;
    }
}
