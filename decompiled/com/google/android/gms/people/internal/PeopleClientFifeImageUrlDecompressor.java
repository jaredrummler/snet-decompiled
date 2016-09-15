package com.google.android.gms.people.internal;

import android.os.Bundle;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.people.PeopleConstants.BundleKeys;
import java.util.regex.Pattern;

public class PeopleClientFifeImageUrlDecompressor {
    public static final PeopleClientFifeImageUrlDecompressor INSTANCE;
    private Pattern[] mPatterns;
    private String[] mReplacements;

    static {
        INSTANCE = new PeopleClientFifeImageUrlDecompressor();
    }

    private PeopleClientFifeImageUrlDecompressor() {
        this.mPatterns = new Pattern[0];
        this.mReplacements = new String[0];
    }

    public static void setPatternsToServiceConfigurations(Bundle configurations, String[] patterns, String[] replacements) {
        configurations.putStringArray(BundleKeys.CONFIG_URL_UNCOMPRESS_PATTERNS, patterns);
        configurations.putStringArray(BundleKeys.CONFIG_URL_UNCOMPRESS_REPLACEMENTS, replacements);
    }

    public void configureFromServiceConfigurations(Bundle configurations) {
        setPatterns(configurations.getStringArray(BundleKeys.CONFIG_URL_UNCOMPRESS_PATTERNS), configurations.getStringArray(BundleKeys.CONFIG_URL_UNCOMPRESS_REPLACEMENTS));
    }

    public synchronized void setPatterns(String[] patterns, String[] replacements) {
        Preconditions.checkArgument(patterns.length == replacements.length);
        this.mPatterns = new Pattern[patterns.length];
        this.mReplacements = replacements;
        for (int i = 0; i < patterns.length; i++) {
            this.mPatterns[i] = Pattern.compile(patterns[i]);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized java.lang.String decompress(java.lang.String r5) {
        /*
        r4 = this;
        monitor-enter(r4);
        r2 = android.text.TextUtils.isEmpty(r5);	 Catch:{ all -> 0x0024 }
        if (r2 == 0) goto L_0x000a;
    L_0x0007:
        r1 = 0;
    L_0x0008:
        monitor-exit(r4);
        return r1;
    L_0x000a:
        r1 = r5;
        r0 = 0;
    L_0x000c:
        r2 = r4.mPatterns;	 Catch:{ all -> 0x0024 }
        r2 = r2.length;	 Catch:{ all -> 0x0024 }
        if (r0 >= r2) goto L_0x0008;
    L_0x0011:
        r2 = r4.mPatterns;	 Catch:{ all -> 0x0024 }
        r2 = r2[r0];	 Catch:{ all -> 0x0024 }
        r2 = r2.matcher(r1);	 Catch:{ all -> 0x0024 }
        r3 = r4.mReplacements;	 Catch:{ all -> 0x0024 }
        r3 = r3[r0];	 Catch:{ all -> 0x0024 }
        r1 = r2.replaceAll(r3);	 Catch:{ all -> 0x0024 }
        r0 = r0 + 1;
        goto L_0x000c;
    L_0x0024:
        r2 = move-exception;
        monitor-exit(r4);
        throw r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.people.internal.PeopleClientFifeImageUrlDecompressor.decompress(java.lang.String):java.lang.String");
    }
}
