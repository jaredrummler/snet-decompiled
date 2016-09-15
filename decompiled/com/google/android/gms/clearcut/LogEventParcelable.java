package com.google.android.gms.clearcut;

import android.os.Parcel;
import com.google.android.gms.clearcut.ClearcutLogger.MessageProducer;
import com.google.android.gms.common.internal.Joiner;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;
import com.google.android.gms.playlog.internal.PlayLoggerContext;
import com.google.wireless.android.play.playlog.proto.ClientAnalytics.LogEvent;
import java.util.Arrays;

@Class(creator = "LogEventParcelableCreator")
public class LogEventParcelable implements SafeParcelable {
    private static final String COMMA = ", ";
    public static final LogEventParcelableCreator CREATOR;
    private static final int VERSION_CODE = 1;
    public final MessageProducer clientVisualElementsProducer;
    public final MessageProducer extensionProducer;
    public final LogEvent logEvent;
    @Field(id = 3)
    public byte[] logEventBytes;
    @Field(id = 2)
    public PlayLoggerContext playLoggerContext;
    @Field(id = 4)
    public int[] testCodes;
    @VersionField(id = 1)
    public final int versionCode;

    static {
        CREATOR = new LogEventParcelableCreator();
    }

    public LogEventParcelable(PlayLoggerContext playLoggerContext, LogEvent logEvent, MessageProducer extensionProducer) {
        this(playLoggerContext, logEvent, extensionProducer, null);
    }

    public LogEventParcelable(PlayLoggerContext playLoggerContext, LogEvent logEvent, MessageProducer extensionProducer, MessageProducer clientVisualElementsProducer) {
        this.versionCode = VERSION_CODE;
        this.playLoggerContext = playLoggerContext;
        this.logEvent = logEvent;
        this.extensionProducer = extensionProducer;
        this.clientVisualElementsProducer = clientVisualElementsProducer;
    }

    public LogEventParcelable(PlayLoggerContext playLoggerContext, LogEvent logEvent, MessageProducer extensionProducer, MessageProducer clientVisualElementsProducer, int[] testCodes) {
        this.versionCode = VERSION_CODE;
        this.playLoggerContext = playLoggerContext;
        this.logEvent = logEvent;
        this.extensionProducer = extensionProducer;
        this.clientVisualElementsProducer = clientVisualElementsProducer;
        this.testCodes = testCodes;
    }

    @Constructor
    LogEventParcelable(@Param(id = 1) int versionCode, @Param(id = 2) PlayLoggerContext playLoggerContext, @Param(id = 3) byte[] logEventBytes, @Param(id = 4) int[] testCodes) {
        this.versionCode = versionCode;
        this.playLoggerContext = playLoggerContext;
        this.logEventBytes = logEventBytes;
        this.testCodes = testCodes;
        this.logEvent = null;
        this.extensionProducer = null;
        this.clientVisualElementsProducer = null;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof LogEventParcelable)) {
            return false;
        }
        LogEventParcelable rhs = (LogEventParcelable) other;
        if (this.versionCode == rhs.versionCode && Objects.equal(this.playLoggerContext, rhs.playLoggerContext) && Arrays.equals(this.logEventBytes, rhs.logEventBytes) && Arrays.equals(this.testCodes, rhs.testCodes) && Objects.equal(this.logEvent, rhs.logEvent) && Objects.equal(this.extensionProducer, rhs.extensionProducer) && Objects.equal(this.clientVisualElementsProducer, rhs.clientVisualElementsProducer)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.versionCode), this.playLoggerContext, this.logEventBytes, this.testCodes, this.logEvent, this.extensionProducer, this.clientVisualElementsProducer);
    }

    public String toString() {
        String str;
        StringBuilder sb = new StringBuilder("LogEventParcelable[");
        sb.append(this.versionCode);
        sb.append(COMMA);
        sb.append(this.playLoggerContext);
        sb.append(COMMA);
        sb.append(this.logEventBytes == null ? null : new String(this.logEventBytes));
        sb.append(COMMA);
        if (this.testCodes == null) {
            str = (String) null;
        } else {
            Joiner on = Joiner.on(COMMA);
            Object[] objArr = new int[VERSION_CODE][];
            objArr[0] = this.testCodes;
            str = on.join(Arrays.asList(objArr));
        }
        sb.append(str);
        sb.append(COMMA);
        sb.append(this.logEvent);
        sb.append(COMMA);
        sb.append(this.extensionProducer);
        sb.append(COMMA);
        sb.append(this.clientVisualElementsProducer);
        sb.append("]");
        return sb.toString();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        LogEventParcelableCreator.writeToParcel(this, out, flags);
    }
}
