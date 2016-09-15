package com.google.android.gms.common.stats;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.os.Debug;
import android.os.Process;
import android.os.SystemClock;
import android.util.Log;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.internal.BuildConstants;
import com.google.android.gms.common.stats.G.connections;
import com.google.android.gms.common.util.ClientLibraryUtils;
import com.google.android.gms.common.util.ProcessUtils;
import com.google.android.snet.Csv;
import com.google.wireless.android.play.playlog.proto.ClientAnalytics.LogRequest.LogSource;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Nullable;

public class ConnectionTracker {
    static final boolean DEBUG = false;
    private static final int MAP_SIZE = 1024;
    static final String TAG = "ConnectionTracker";
    private static ConnectionTracker sInstance;
    private static Integer sLogLevel;
    private static final Object sSingletonLock;
    private final List<String> mIgnoredCallingProcessList;
    private final List<String> mIgnoredCallingServiceList;
    private final List<String> mIgnoredTargetProcessList;
    private final List<String> mIgnoredTargetServiceList;
    private PassiveTimedConnectionMatcher mSentOpenConns;
    private PassiveTimedConnectionMatcher mSentStartedServices;

    static {
        sSingletonLock = new Object();
    }

    public static ConnectionTracker getInstance() {
        synchronized (sSingletonLock) {
            if (sInstance == null) {
                sInstance = new ConnectionTracker();
            }
        }
        return sInstance;
    }

    private ConnectionTracker() {
        if (getLogLevel() == LoggingConstants.LOG_LEVEL_OFF) {
            this.mIgnoredCallingProcessList = Collections.EMPTY_LIST;
            this.mIgnoredCallingServiceList = Collections.EMPTY_LIST;
            this.mIgnoredTargetProcessList = Collections.EMPTY_LIST;
            this.mIgnoredTargetServiceList = Collections.EMPTY_LIST;
            return;
        }
        String tmpString = (String) connections.ignoredCallingProcesses.get();
        this.mIgnoredCallingProcessList = tmpString == null ? Collections.EMPTY_LIST : Arrays.asList(tmpString.split(Csv.COMMA));
        tmpString = (String) connections.ignoredCallingServices.get();
        this.mIgnoredCallingServiceList = tmpString == null ? Collections.EMPTY_LIST : Arrays.asList(tmpString.split(Csv.COMMA));
        tmpString = (String) connections.ignoredTargetProcesses.get();
        this.mIgnoredTargetProcessList = tmpString == null ? Collections.EMPTY_LIST : Arrays.asList(tmpString.split(Csv.COMMA));
        tmpString = (String) connections.ignoredTargetServices.get();
        this.mIgnoredTargetServiceList = tmpString == null ? Collections.EMPTY_LIST : Arrays.asList(tmpString.split(Csv.COMMA));
        this.mSentOpenConns = new PassiveTimedConnectionMatcher(MAP_SIZE, ((Long) connections.timeOutDurationMillis.get()).longValue());
        this.mSentStartedServices = new PassiveTimedConnectionMatcher(MAP_SIZE, ((Long) connections.timeOutDurationMillis.get()).longValue());
    }

    private boolean bindingToStoppedPackage(Context context, Intent intent) {
        ComponentName component = intent.getComponent();
        if (component == null || (BuildConstants.IS_PACKAGE_SIDE && GooglePlayServicesUtil.GOOGLE_PLAY_SERVICES_PACKAGE.equals(component.getPackageName()))) {
            return DEBUG;
        }
        return ClientLibraryUtils.isPackageStopped(context, component.getPackageName());
    }

    @SuppressLint({"UntrackedBindService"})
    public boolean bindService(Context context, String realClientName, Intent intent, ServiceConnection conn, int flags) {
        if (bindingToStoppedPackage(context, intent)) {
            Log.w(TAG, "Attempted to bind to a service in a STOPPED package.");
            return DEBUG;
        }
        boolean bound = context.bindService(intent, conn, flags);
        if (!bound) {
            return bound;
        }
        registerBindingEvent(context, getConnectionKey(conn), realClientName, intent, 2);
        return bound;
    }

    public boolean bindService(Context context, Intent intent, ServiceConnection conn, int flags) {
        return bindService(context, context.getClass().getName(), intent, conn, flags);
    }

    @SuppressLint({"UntrackedBindService"})
    public void unbindService(Context context, ServiceConnection conn) {
        context.unbindService(conn);
        registerBindingEvent(context, getConnectionKey(conn), null, null, 1);
    }

    public void logConnectService(Context context, ServiceConnection conn, String realClientName, Intent queryIntent) {
        registerBindingEvent(context, getConnectionKey(conn), realClientName, queryIntent, 3);
    }

    public void logDisconnectService(Context context, ServiceConnection conn) {
        registerBindingEvent(context, getConnectionKey(conn), null, null, 4);
    }

    public void logStartService(Service service, Intent intent, int startId) {
        registerStartedService(service, intent, startId, 13);
    }

    public void logStopService(Service service, int startId) {
        registerStartedService(service, null, startId, 14);
    }

    private void registerBindingEvent(Context context, String connKey, String callingService, Intent queryIntent, int eventType) {
        if (logLevelOn() && this.mSentOpenConns != null) {
            String targetProcess;
            String targetName;
            String callingProcess;
            if (eventType != 4 && eventType != 1) {
                ServiceInfo target = getTargetServiceInfo(context, queryIntent);
                if (target == null) {
                    Log.w(TAG, String.format("Client %s made an invalid request %s", new Object[]{callingService, queryIntent.toUri(0)}));
                    return;
                }
                targetProcess = target.processName;
                targetName = target.name;
                callingProcess = ProcessUtils.getCallingProcessName(context);
                if (validated(callingProcess, callingService, targetProcess, targetName)) {
                    this.mSentOpenConns.put(connKey);
                } else {
                    return;
                }
            } else if (this.mSentOpenConns.remove(connKey)) {
                targetProcess = null;
                targetName = null;
                callingProcess = null;
            } else {
                return;
            }
            registerEvent(context, connKey, eventType, callingProcess, callingService, targetProcess, targetName);
        }
    }

    private void registerStartedService(Service service, Intent intent, int startId, int eventType) {
        String caller = null;
        if (logLevelOn() && this.mSentStartedServices != null) {
            String targetService = service.getClass().getName();
            String callingProcess = intent == null ? null : intent.getStringExtra(LoggingConstants.EXTRA_STARTED_BY_PROCESS);
            if (intent != null) {
                caller = intent.getStringExtra(LoggingConstants.EXTRA_STARTED_BY);
            }
            String keyPrefix = targetService + "#";
            String key = keyPrefix + startId;
            int finalEventType = eventType;
            if (eventType == 14) {
                if (startId == -1) {
                    this.mSentStartedServices.removeByPrefix(keyPrefix);
                    finalEventType = 15;
                    key = keyPrefix;
                } else if (!this.mSentStartedServices.remove(key)) {
                    return;
                }
            } else if (eventType != 13) {
                Log.e(TAG, "Invalid eventType: " + eventType + " for " + targetService);
            } else if (this.mSentStartedServices.get(key) != null) {
                this.mSentStartedServices.put(key);
            } else {
                this.mSentStartedServices.put(key);
            }
            registerEvent(service, key, finalEventType, callingProcess, caller, ProcessUtils.getCallingProcessName(service), targetService);
        }
    }

    private void registerEvent(Context context, String connKey, int eventType, String callingProcess, String callingService, String targetProcess, String targetService) {
        ConnectionEvent event;
        long currentTime = System.currentTimeMillis();
        String stacktrace = null;
        if (!((getLogLevel() & LoggingConstants.LOG_VERBOSE) == 0 || eventType == 13)) {
            stacktrace = ProcessUtils.getCallers(3, 5);
        }
        long heapAlloc = 0;
        if ((getLogLevel() & LoggingConstants.LOG_MEM_INFO) != 0) {
            heapAlloc = Debug.getNativeHeapAllocatedSize();
        }
        if (eventType == 1 || eventType == 4 || eventType == 14) {
            event = new ConnectionEvent(currentTime, eventType, null, null, null, null, stacktrace, connKey, SystemClock.elapsedRealtime(), heapAlloc);
        } else {
            event = new ConnectionEvent(currentTime, eventType, callingProcess, callingService, targetProcess, targetService, stacktrace, connKey, SystemClock.elapsedRealtime(), heapAlloc);
        }
        context.startService(new Intent().setComponent(LoggingConstants.STATS_SERVICE_COMPONENT_NAME).putExtra(LoggingConstants.EXTRA_LOG_EVENT, event));
    }

    private boolean logLevelOn() {
        if (BuildConstants.IS_PACKAGE_SIDE && getLogLevel() != LoggingConstants.LOG_LEVEL_OFF) {
            return true;
        }
        return DEBUG;
    }

    private boolean validated(String callingProcess, String callingService, String targetProcess, String targetService) {
        int logLevel = getLogLevel();
        if (this.mIgnoredCallingProcessList.contains(callingProcess) || this.mIgnoredCallingServiceList.contains(callingService) || this.mIgnoredTargetProcessList.contains(targetProcess) || this.mIgnoredTargetServiceList.contains(targetService) || (targetProcess.equals(callingProcess) && (LoggingConstants.IGNORE_INTRA_PROCESS & logLevel) != 0)) {
            return DEBUG;
        }
        return true;
    }

    @Nullable
    private static ServiceInfo getTargetServiceInfo(Context context, Intent queryIntent) {
        List<ResolveInfo> resolveInfoList = context.getPackageManager().queryIntentServices(queryIntent, LogSource.KEEP);
        if (resolveInfoList == null || resolveInfoList.size() == 0) {
            Log.w(TAG, String.format("There are no handler of this intent: %s\n Stack trace: %s", new Object[]{queryIntent.toUri(0), ProcessUtils.getCallers(3, 20)}));
            return null;
        }
        if (resolveInfoList.size() > 1) {
            Log.w(TAG, String.format("Multiple handlers found for this intent: %s\n Stack trace: %s", new Object[]{queryIntent.toUri(0), ProcessUtils.getCallers(3, 20)}));
            Iterator i$ = resolveInfoList.iterator();
            if (i$.hasNext()) {
                Log.w(TAG, ((ResolveInfo) i$.next()).serviceInfo.name);
                return null;
            }
        }
        return ((ResolveInfo) resolveInfoList.get(0)).serviceInfo;
    }

    private String getConnectionKey(ServiceConnection conn) {
        return String.valueOf((((long) Process.myPid()) << 32) | ((long) System.identityHashCode(conn)));
    }

    private static int getLogLevel() {
        if (sLogLevel == null) {
            try {
                sLogLevel = Integer.valueOf(ClientLibraryUtils.isPackageSide() ? ((Integer) connections.level.get()).intValue() : LoggingConstants.LOG_LEVEL_OFF);
            } catch (SecurityException e) {
                sLogLevel = Integer.valueOf(LoggingConstants.LOG_LEVEL_OFF);
            }
        }
        return sLogLevel.intValue();
    }
}
