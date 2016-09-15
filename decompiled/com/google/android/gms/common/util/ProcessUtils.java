package com.google.android.gms.common.util;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.os.Binder;
import com.google.android.gms.auth.firstparty.recovery.RecoveryParamConstants;
import java.util.List;
import javax.annotation.Nullable;

public class ProcessUtils {
    private ProcessUtils() {
    }

    @Nullable
    public static String getCallingProcessName(Context context) {
        return getProcessName(context, Binder.getCallingPid());
    }

    @Nullable
    public static String getProcessName(Context context, int pid) {
        List<RunningAppProcessInfo> runningAppProcessInfos = ((ActivityManager) context.getSystemService(RecoveryParamConstants.VALUE_ACTIVITY)).getRunningAppProcesses();
        if (runningAppProcessInfos != null) {
            for (RunningAppProcessInfo runningAppProcessInfo : runningAppProcessInfos) {
                if (runningAppProcessInfo.pid == pid) {
                    return runningAppProcessInfo.processName;
                }
            }
        }
        return null;
    }

    public static String getCallers(int start, int depth) {
        StackTraceElement[] callStack = Thread.currentThread().getStackTrace();
        StringBuffer sb = new StringBuffer();
        depth += start;
        for (int i = start; i < depth; i++) {
            sb.append(getCaller(callStack, i)).append(" ");
        }
        return sb.toString();
    }

    private static String getCaller(StackTraceElement[] callStack, int depth) {
        if (depth + 4 >= callStack.length) {
            return "<bottom of call stack>";
        }
        StackTraceElement caller = callStack[depth + 4];
        return caller.getClassName() + "." + caller.getMethodName() + ":" + caller.getLineNumber();
    }
}
