package com.google.android.gms.common.internal;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public abstract class DowngradeableSafeParcel implements SafeParcelable {
    private static final String SAFE_PARCELABLE_FIELD_NAME = "NULL";
    private static final Object UNPARCEL_PARAMETER_LOCK;
    private static ClassLoader sUnparcelClassLoader;
    private static Integer sUnparcelClientVersion;
    private boolean mShouldDowngrade;

    public static final class DowngradeableSafeParcelHelper {
        public static <T extends Parcelable> T getParcelable(Intent intent, String key, Context callingContext, Integer clientVersion) {
            T parcelable;
            synchronized (DowngradeableSafeParcel.UNPARCEL_PARAMETER_LOCK) {
                parcelable = DowngradeableSafeParcel.getParcelable(intent, key, callingContext, clientVersion);
            }
            return parcelable;
        }

        public static boolean putParcelable(Bundle bundle, String key, DowngradeableSafeParcel parcelable, Context callingContext, Integer clientVersion) {
            return DowngradeableSafeParcel.putParcelable(bundle, key, parcelable, callingContext, clientVersion);
        }

        public static Bundle getExtras(Intent intent, Context callingContext, Integer clientVersion) {
            Bundle extras;
            synchronized (DowngradeableSafeParcel.UNPARCEL_PARAMETER_LOCK) {
                extras = DowngradeableSafeParcel.getExtras(intent, callingContext, clientVersion);
            }
            return extras;
        }

        public static <T extends Parcelable> T getParcelable(Bundle bundle, String key, Context callingContext, Integer clientVersion) {
            T parcelable;
            synchronized (DowngradeableSafeParcel.UNPARCEL_PARAMETER_LOCK) {
                parcelable = DowngradeableSafeParcel.getParcelable(bundle, key, callingContext, clientVersion);
            }
            return parcelable;
        }
    }

    protected abstract boolean prepareForClientVersion(int i);

    public DowngradeableSafeParcel() {
        this.mShouldDowngrade = false;
    }

    static {
        UNPARCEL_PARAMETER_LOCK = new Object();
        sUnparcelClassLoader = null;
        sUnparcelClientVersion = null;
    }

    static <T extends Parcelable> T getParcelable(Intent intent, String key, Context callingContext, Integer clientVersion) {
        T parcelable = null;
        if (callingContext != null) {
            try {
                setUnparcelParams(callingContext.getClassLoader(), clientVersion);
                parcelable = intent.getParcelableExtra(key);
            } catch (Throwable th) {
                setUnparcelParams(null, null);
            }
        }
        setUnparcelParams(null, null);
        return parcelable;
    }

    static Bundle getExtras(Intent intent, Context callingContext, Integer clientVersion) {
        Throwable th;
        Bundle extras = null;
        if (callingContext != null) {
            try {
                setUnparcelParams(callingContext.getClassLoader(), clientVersion);
                if (intent.getExtras() != null) {
                    Bundle extras2 = new Bundle();
                    try {
                        extras2.putAll(intent.getExtras());
                        extras = extras2;
                    } catch (Throwable th2) {
                        th = th2;
                        extras = extras2;
                        setUnparcelParams(null, null);
                        throw th;
                    }
                }
            } catch (Throwable th3) {
                th = th3;
                setUnparcelParams(null, null);
                throw th;
            }
        }
        setUnparcelParams(null, null);
        return extras;
    }

    static <T extends Parcelable> T getParcelable(Bundle bundle, String key, Context callingContext, Integer clientVersion) {
        T parcelable = null;
        if (callingContext != null) {
            try {
                setUnparcelParams(callingContext.getClassLoader(), clientVersion);
                parcelable = bundle.getParcelable(key);
            } catch (Throwable th) {
                setUnparcelParams(null, null);
            }
        }
        setUnparcelParams(null, null);
        return parcelable;
    }

    static boolean putParcelable(Bundle bundle, String key, DowngradeableSafeParcel parcelable, Context callingContext, Integer clientVersion) {
        if (callingContext == null && clientVersion == null) {
            return false;
        }
        if (!parcelable.prepareForClient(callingContext, clientVersion)) {
            return false;
        }
        bundle.putParcelable(key, parcelable);
        return true;
    }

    private static void setUnparcelParams(ClassLoader loader, Integer clientVersion) {
        synchronized (UNPARCEL_PARAMETER_LOCK) {
            sUnparcelClassLoader = loader;
            sUnparcelClientVersion = clientVersion;
        }
    }

    protected static ClassLoader getUnparcelClassLoader() {
        ClassLoader classLoader;
        synchronized (UNPARCEL_PARAMETER_LOCK) {
            classLoader = sUnparcelClassLoader;
        }
        return classLoader;
    }

    protected static Integer getUnparcelClientVersion() {
        Integer num;
        synchronized (UNPARCEL_PARAMETER_LOCK) {
            num = sUnparcelClientVersion;
        }
        return num;
    }

    private boolean prepareForClient(Context callingContext, Integer clientVersion) {
        if (clientVersion != null) {
            return prepareForClientVersion(clientVersion.intValue());
        }
        try {
            boolean z;
            if (isClientClassSafe(callingContext.getClassLoader().loadClass(getClass().getCanonicalName()))) {
                z = false;
            } else {
                z = true;
            }
            setShouldDowngrade(z);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    protected boolean shouldDowngrade() {
        return this.mShouldDowngrade;
    }

    public void setShouldDowngrade(boolean shouldDowngrade) {
        this.mShouldDowngrade = shouldDowngrade;
    }

    protected static boolean canUnparcelSafely(String className) {
        ClassLoader loader = getUnparcelClassLoader();
        if (loader == null) {
            return true;
        }
        try {
            return isClientClassSafe(loader.loadClass(className));
        } catch (Exception e) {
            return false;
        }
    }

    private static boolean isClientClassSafe(Class<?> clazz) {
        boolean z = false;
        try {
            z = SafeParcelable.NULL.equals(clazz.getField(SAFE_PARCELABLE_FIELD_NAME).get(null));
        } catch (NoSuchFieldException e) {
        } catch (IllegalAccessException e2) {
        }
        return z;
    }
}
