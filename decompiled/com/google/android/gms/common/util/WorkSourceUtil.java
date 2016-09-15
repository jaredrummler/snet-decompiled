package com.google.android.gms.common.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.WorkSource;
import android.util.Log;
import com.google.android.gms.lint.BuildConfig;
import com.google.android.gms.people.PeopleConstants.Endpoints;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;

public class WorkSourceUtil {
    private static final Method ADD_METHOD;
    private static final Method ADD_WITH_NAME_METHOD;
    private static final Method GET_METHOD;
    private static final Method GET_NAME_METHOD;
    private static final Method SIZE_METHOD;
    public static final String TAG = "WorkSourceUtil";

    static {
        ADD_METHOD = getAddMethod();
        ADD_WITH_NAME_METHOD = getAddWithNameMethod();
        SIZE_METHOD = getSizeMethod();
        GET_METHOD = getGetMethod();
        GET_NAME_METHOD = getGetNameMethod();
    }

    private WorkSourceUtil() {
    }

    public static WorkSource fromUidAndPackage(int uid, String packageName) {
        WorkSource workSource = new WorkSource();
        add(workSource, uid, packageName);
        return workSource;
    }

    public static WorkSource fromPackage(Context context, String packageName) {
        if (context == null || context.getPackageManager() == null) {
            return null;
        }
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(packageName, 0);
            if (applicationInfo != null) {
                return fromUidAndPackage(applicationInfo.uid, packageName);
            }
            Log.e(TAG, "Could not get applicationInfo from package: " + packageName);
            return null;
        } catch (NameNotFoundException e) {
            Log.e(TAG, "Could not find package: " + packageName);
            return null;
        }
    }

    public static void add(WorkSource workSource, int uid, @Nullable String packageName) {
        if (ADD_WITH_NAME_METHOD != null) {
            if (packageName == null) {
                packageName = BuildConfig.VERSION_NAME;
            }
            try {
                ADD_WITH_NAME_METHOD.invoke(workSource, new Object[]{Integer.valueOf(uid), packageName});
            } catch (Exception e) {
                Log.wtf(TAG, "Unable to assign blame through WorkSource", e);
            }
        } else if (ADD_METHOD != null) {
            try {
                ADD_METHOD.invoke(workSource, new Object[]{Integer.valueOf(uid)});
            } catch (Exception e2) {
                Log.wtf(TAG, "Unable to assign blame through WorkSource", e2);
            }
        }
    }

    public static int size(WorkSource workSource) {
        if (SIZE_METHOD != null) {
            try {
                return ((Integer) SIZE_METHOD.invoke(workSource, new Object[0])).intValue();
            } catch (Exception e) {
                Log.wtf(TAG, "Unable to assign blame through WorkSource", e);
            }
        }
        return 0;
    }

    public static int get(WorkSource workSource, int i) {
        if (GET_METHOD != null) {
            try {
                return ((Integer) GET_METHOD.invoke(workSource, new Object[]{Integer.valueOf(i)})).intValue();
            } catch (Exception e) {
                Log.wtf(TAG, "Unable to assign blame through WorkSource", e);
            }
        }
        return 0;
    }

    @Nullable
    public static String getName(WorkSource workSource, int i) {
        if (GET_NAME_METHOD != null) {
            try {
                return (String) GET_NAME_METHOD.invoke(workSource, new Object[]{Integer.valueOf(i)});
            } catch (Exception e) {
                Log.wtf(TAG, "Unable to assign blame through WorkSource", e);
            }
        }
        return null;
    }

    public static List<String> getNames(WorkSource workSource) {
        int size = workSource == null ? 0 : size(workSource);
        if (size == 0) {
            return Collections.EMPTY_LIST;
        }
        List<String> names = new ArrayList();
        for (int i = 0; i < size; i++) {
            String name = getName(workSource, i);
            if (!Strings.isEmptyOrWhitespace(name)) {
                names.add(name);
            }
        }
        return names;
    }

    public static boolean hasWorkSourcePermission(Context context) {
        if (context == null) {
            return false;
        }
        PackageManager packageManager = context.getPackageManager();
        if (packageManager == null || packageManager.checkPermission("android.permission.UPDATE_DEVICE_STATS", context.getPackageName()) != 0) {
            return false;
        }
        return true;
    }

    private static Method getAddMethod() {
        Method addMethod = null;
        try {
            addMethod = WorkSource.class.getMethod("add", new Class[]{Integer.TYPE});
        } catch (Exception e) {
        }
        return addMethod;
    }

    private static Method getAddWithNameMethod() {
        Method addMethod = null;
        if (PlatformVersion.isAtLeastJellyBeanMR2()) {
            try {
                addMethod = WorkSource.class.getMethod("add", new Class[]{Integer.TYPE, String.class});
            } catch (Exception e) {
            }
        }
        return addMethod;
    }

    private static Method getSizeMethod() {
        Method sizeMethod = null;
        try {
            sizeMethod = WorkSource.class.getMethod("size", new Class[0]);
        } catch (Exception e) {
        }
        return sizeMethod;
    }

    private static Method getGetMethod() {
        Method getMethod = null;
        try {
            getMethod = WorkSource.class.getMethod(Endpoints.ENDPOINT_GET, new Class[]{Integer.TYPE});
        } catch (Exception e) {
        }
        return getMethod;
    }

    private static Method getGetNameMethod() {
        Method getNameMethod = null;
        if (PlatformVersion.isAtLeastJellyBeanMR2()) {
            try {
                getNameMethod = WorkSource.class.getMethod("getName", new Class[]{Integer.TYPE});
            } catch (Exception e) {
            }
        }
        return getNameMethod;
    }
}
