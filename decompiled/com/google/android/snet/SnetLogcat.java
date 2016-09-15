package com.google.android.snet;

import android.content.Context;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.lint.BuildConfig;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class SnetLogcat {
    private static final int LOG_APP_OPERATION = 1;
    private static final int LOG_APP_USING_INLINED_UID_OPERATION = 2;
    private static final int LOG_LINE_WITH_REGEX = 3;
    private static final int NO_OPERATION = 0;
    private static String SNET_LOGCAT_SCRAPER_MARKER;
    private static final Set<Integer> SUPPORTED_OPERATIONS;
    private static final String TAG;
    private final Context mContext;
    private final GBundle mGBundle;
    private PackageManager mPackageManager;
    private Map<String, SearchSettings> mStringToTagOpMap;

    public static class LogcatInfo {
        public int numLogcatLines;
        public Map<String, List<Value>> results;

        LogcatInfo() {
            this.results = new HashMap();
        }

        void reset() {
            this.results = new HashMap();
        }
    }

    private static class SearchSettings {
        int op;
        Pattern pattern;
        String tag;

        SearchSettings(String regEx, String t, int o) {
            this.tag = t;
            this.op = o;
            if (this.op == SnetLogcat.LOG_APP_USING_INLINED_UID_OPERATION || this.op == SnetLogcat.LOG_LINE_WITH_REGEX) {
                this.pattern = Pattern.compile(regEx);
            }
        }
    }

    public static class Value {
        public List<AppInfo> appInfoList;
        public String line;
    }

    static {
        TAG = SnetLogcat.class.getCanonicalName();
        SNET_LOGCAT_SCRAPER_MARKER = "snet-mark";
        SUPPORTED_OPERATIONS = new HashSet(Arrays.asList(new Integer[]{Integer.valueOf(0), Integer.valueOf(LOG_APP_OPERATION), Integer.valueOf(LOG_APP_USING_INLINED_UID_OPERATION), Integer.valueOf(LOG_LINE_WITH_REGEX)}));
    }

    SnetLogcat(Context context, GBundle gBundle) {
        this.mContext = context;
        this.mGBundle = gBundle;
        String logcatTags = this.mGBundle.getLogcatTags();
        if (!TextUtils.isEmpty(logcatTags)) {
            parseGservicesFlag(logcatTags);
        }
        this.mPackageManager = this.mContext.getPackageManager();
    }

    private void parseGservicesFlag(String gservicesFlag) {
        List<String> tagStringOpList = parseIntoParts(gservicesFlag, ',');
        this.mStringToTagOpMap = new HashMap();
        for (String tagsOpString : tagStringOpList) {
            String tag = null;
            String stringOfInterest = null;
            int operation = 0;
            try {
                List<String> parts = parseIntoParts(tagsOpString, ':');
                if (parts.size() == LOG_LINE_WITH_REGEX) {
                    tag = ((String) parts.get(0)).trim();
                    stringOfInterest = removeEscapeCharacters((String) parts.get(LOG_APP_OPERATION));
                    operation = Integer.parseInt((String) parts.get(LOG_APP_USING_INLINED_UID_OPERATION));
                    if (!SUPPORTED_OPERATIONS.contains(Integer.valueOf(operation))) {
                        operation = 0;
                    }
                    if (!(TextUtils.isEmpty(tag) || stringOfInterest == null || TextUtils.isEmpty(stringOfInterest.trim()))) {
                        this.mStringToTagOpMap.put(stringOfInterest, new SearchSettings(stringOfInterest, tag, operation));
                    }
                }
            } catch (NumberFormatException e) {
            }
        }
    }

    LogcatInfo scrape() {
        if (this.mStringToTagOpMap != null) {
            if (!this.mStringToTagOpMap.isEmpty()) {
                LogcatInfo info = new LogcatInfo();
                try {
                    Process process;
                    info.numLogcatLines = this.mGBundle.getNumLogcatLines();
                    if (info.numLogcatLines <= 0) {
                        process = Runtime.getRuntime().exec("logcat -d");
                    } else {
                        Runtime runtime = Runtime.getRuntime();
                        Object[] objArr = new Object[LOG_APP_OPERATION];
                        objArr[0] = Integer.valueOf(info.numLogcatLines);
                        process = runtime.exec(String.format("logcat -t %d", objArr));
                    }
                    if (process == null) {
                        return null;
                    }
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                    String line = BuildConfig.VERSION_NAME;
                    ApplicationInfoUtils appInfoUtils = new ApplicationInfoUtils(this.mContext, this.mGBundle);
                    while (true) {
                        line = bufferedReader.readLine();
                        if (line != null) {
                            String processName = getProcessNameFromLogcatLine(line);
                            int pid = -1;
                            int uid = -1;
                            if (TAG.equals(processName)) {
                                if (line.contains(SNET_LOGCAT_SCRAPER_MARKER)) {
                                    info.reset();
                                }
                            }
                            for (Entry<String, SearchSettings> mapEntry : this.mStringToTagOpMap.entrySet()) {
                                String stringOfInterest = (String) mapEntry.getKey();
                                SearchSettings searchSettings = (SearchSettings) mapEntry.getValue();
                                if (!searchSettings.tag.equals("*")) {
                                    if (processName != null) {
                                        if (!processName.equals(searchSettings.tag)) {
                                            continue;
                                        }
                                    } else {
                                        continue;
                                    }
                                }
                                boolean logLine = false;
                                if (searchSettings.op == 0) {
                                    logLine = line.contains(stringOfInterest);
                                    pid = -1;
                                    uid = -1;
                                } else {
                                    int i = searchSettings.op;
                                    if (r0 == LOG_APP_OPERATION) {
                                        logLine = line.contains(stringOfInterest);
                                        pid = getPidFromLogcatLine(line);
                                        uid = -1;
                                    } else {
                                        i = searchSettings.op;
                                        if (r0 != LOG_APP_USING_INLINED_UID_OPERATION) {
                                            i = searchSettings.op;
                                            if (r0 == LOG_LINE_WITH_REGEX && searchSettings.pattern != null) {
                                                logLine = searchSettings.pattern.matcher(line).matches();
                                                pid = -1;
                                                uid = -1;
                                            }
                                        } else if (searchSettings.pattern != null) {
                                            Matcher matcher = searchSettings.pattern.matcher(line);
                                            logLine = matcher.matches();
                                            pid = -1;
                                            if (logLine) {
                                                try {
                                                    uid = Integer.valueOf(matcher.group(LOG_APP_OPERATION)).intValue();
                                                } catch (IllegalStateException e) {
                                                    uid = -1;
                                                } catch (NumberFormatException e2) {
                                                    uid = -1;
                                                }
                                            } else {
                                                uid = -1;
                                            }
                                        }
                                    }
                                }
                                if (logLine) {
                                    List<Value> valuesList = (List) info.results.get(stringOfInterest);
                                    Value valueToAdd = new Value();
                                    valueToAdd.line = line;
                                    List<AppInfo> appInfoList;
                                    if (uid != -1) {
                                        if (uid >= 10000) {
                                            appInfoList = appInfoUtils.appsForUid(uid);
                                            if (!(appInfoList == null || appInfoList.isEmpty())) {
                                                valueToAdd.appInfoList = appInfoList;
                                            }
                                        }
                                    } else if (pid != -1) {
                                        appInfoList = appInfoUtils.appsForPid(pid);
                                        if (!(appInfoList == null || appInfoList.isEmpty())) {
                                            valueToAdd.appInfoList = appInfoList;
                                        }
                                    }
                                    if (valuesList == null) {
                                        List<Value> list = new ArrayList();
                                        list.add(valueToAdd);
                                        info.results.put(stringOfInterest, list);
                                    } else {
                                        valuesList.add(valueToAdd);
                                    }
                                }
                            }
                            continue;
                        } else {
                            Log.d(TAG, SNET_LOGCAT_SCRAPER_MARKER);
                            return info;
                        }
                    }
                } catch (IOException e3) {
                    return null;
                }
            }
        }
        return null;
    }

    private static int getPidFromLogcatLine(String logcatLine) {
        int i = -1;
        try {
            int firstBracket = logcatLine.indexOf("(");
            i = Integer.valueOf(logcatLine.substring(firstBracket + LOG_APP_OPERATION, logcatLine.indexOf(")")).trim()).intValue();
        } catch (IndexOutOfBoundsException e) {
        } catch (NumberFormatException e2) {
        }
        return i;
    }

    private static String getProcessNameFromLogcatLine(String logcatLine) {
        try {
            return logcatLine.substring(LOG_APP_USING_INLINED_UID_OPERATION, logcatLine.indexOf("(")).trim();
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    private static List<String> parseIntoParts(String text, char separator) {
        List<String> parts = new ArrayList();
        int start = 0;
        int numBackslashes = 0;
        for (int i = 0; i < text.length(); i += LOG_APP_OPERATION) {
            char character = text.charAt(i);
            if (character == '\\') {
                numBackslashes += LOG_APP_OPERATION;
            } else {
                if (character == separator && numBackslashes % LOG_APP_USING_INLINED_UID_OPERATION == 0) {
                    parts.add(text.substring(start, i));
                    start = i + LOG_APP_OPERATION;
                }
                numBackslashes = 0;
            }
        }
        parts.add(text.substring(start));
        return parts;
    }

    private static String removeEscapeCharacters(String text) {
        return text.replace("\\\\", "\\").replace("\\,", Csv.COMMA).replace("\\:", ":");
    }
}
