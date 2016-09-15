package com.google.android.gms.lockbox.appusage;

import com.google.android.gms.lint.BuildConfig;
import com.google.android.gms.lockbox.OptInState.LockboxOptInState;
import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.ExtendableMessageNano;
import com.google.protobuf.nano.InternalNano;
import com.google.protobuf.nano.InvalidProtocolBufferNanoException;
import com.google.protobuf.nano.MessageNano;
import com.google.protobuf.nano.WireFormatNano;
import com.google.wireless.android.play.playlog.proto.ClientAnalytics.LogRequest.LogSource;
import com.google.wireless.android.play.playlog.proto.UserLocation.LaunchSettingsEvent.Action;
import com.google.wireless.android.play.playlog.proto.UserLocation.LocationSharingEvent.Type;
import com.google.wireless.android.play.playlog.proto.UserLocation.OvenFreshEvent.OvenFreshResult;
import com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection;
import java.io.IOException;

public interface AppUsage {

    public static final class AppUsageEvent extends ExtendableMessageNano<AppUsageEvent> {
        private static volatile AppUsageEvent[] _emptyArray;
        public int deviceLocalUid;
        public long durationMillis;
        public int importance;
        public int importanceReasonCode;
        public ComponentName importanceReasonComponent;
        public int importanceReasonPid;
        public int[] lastTrimLevel;
        public LockboxOptInState lockboxOptInState;
        public int[] lru;
        public int pid;
        public String[] pkg;
        public String processName;
        public boolean stateChange;
        public long timestampMillis;

        public interface Importance {
            public static final int IMPORTANCE_BACKGROUND = 400;
            public static final int IMPORTANCE_EMPTY = 500;
            public static final int IMPORTANCE_FOREGROUND = 100;
            public static final int IMPORTANCE_PERCEPTIBLE = 130;
            public static final int IMPORTANCE_SERVICE = 300;
            public static final int IMPORTANCE_UNKNOWN = 0;
            public static final int IMPORTANCE_VISIBLE = 200;
        }

        public interface ImportanceReasonCode {
            public static final int REASON_PROVIDER_IN_USE = 1;
            public static final int REASON_SERVICE_IN_USE = 2;
            public static final int REASON_UNKNOWN = 0;
        }

        public interface MemoryTrimLevel {
            public static final int TRIM_MEMORY_BACKGROUND = 40;
            public static final int TRIM_MEMORY_COMPLETE = 80;
            public static final int TRIM_MEMORY_MODERATE = 60;
            public static final int TRIM_MEMORY_RUNNING_CRITICAL = 15;
            public static final int TRIM_MEMORY_RUNNING_LOW = 10;
            public static final int TRIM_MEMORY_RUNNING_MODERATE = 5;
            public static final int TRIM_MEMORY_UI_HIDDEN = 20;
            public static final int TRIM_MEMORY_UNKNOWN = 0;
        }

        public static AppUsageEvent[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new AppUsageEvent[0];
                    }
                }
            }
            return _emptyArray;
        }

        public AppUsageEvent() {
            clear();
        }

        public AppUsageEvent clear() {
            this.lockboxOptInState = null;
            this.processName = BuildConfig.VERSION_NAME;
            this.importance = 0;
            this.importanceReasonCode = 0;
            this.importanceReasonComponent = null;
            this.importanceReasonPid = 0;
            this.lastTrimLevel = WireFormatNano.EMPTY_INT_ARRAY;
            this.lru = WireFormatNano.EMPTY_INT_ARRAY;
            this.pid = 0;
            this.pkg = WireFormatNano.EMPTY_STRING_ARRAY;
            this.deviceLocalUid = 0;
            this.timestampMillis = 0;
            this.durationMillis = 0;
            this.stateChange = false;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof AppUsageEvent)) {
                return false;
            }
            AppUsageEvent other = (AppUsageEvent) o;
            if (this.lockboxOptInState == null) {
                if (other.lockboxOptInState != null) {
                    return false;
                }
            } else if (!this.lockboxOptInState.equals(other.lockboxOptInState)) {
                return false;
            }
            if (this.processName == null) {
                if (other.processName != null) {
                    return false;
                }
            } else if (!this.processName.equals(other.processName)) {
                return false;
            }
            if (this.importance != other.importance || this.importanceReasonCode != other.importanceReasonCode) {
                return false;
            }
            if (this.importanceReasonComponent == null) {
                if (other.importanceReasonComponent != null) {
                    return false;
                }
            } else if (!this.importanceReasonComponent.equals(other.importanceReasonComponent)) {
                return false;
            }
            if (this.importanceReasonPid != other.importanceReasonPid || !InternalNano.equals(this.lastTrimLevel, other.lastTrimLevel) || !InternalNano.equals(this.lru, other.lru) || this.pid != other.pid || !InternalNano.equals(this.pkg, other.pkg) || this.deviceLocalUid != other.deviceLocalUid || this.timestampMillis != other.timestampMillis || this.durationMillis != other.durationMillis || this.stateChange != other.stateChange) {
                return false;
            }
            if (this.unknownFieldData != null && !this.unknownFieldData.isEmpty()) {
                return this.unknownFieldData.equals(other.unknownFieldData);
            }
            if (other.unknownFieldData == null || other.unknownFieldData.isEmpty()) {
                return true;
            }
            return false;
        }

        public int hashCode() {
            int i;
            int i2 = 0;
            int hashCode = (((((((((getClass().getName().hashCode() + 527) * 31) + (this.lockboxOptInState == null ? 0 : this.lockboxOptInState.hashCode())) * 31) + (this.processName == null ? 0 : this.processName.hashCode())) * 31) + this.importance) * 31) + this.importanceReasonCode) * 31;
            if (this.importanceReasonComponent == null) {
                i = 0;
            } else {
                i = this.importanceReasonComponent.hashCode();
            }
            i = (((((((((((((((((((hashCode + i) * 31) + this.importanceReasonPid) * 31) + InternalNano.hashCode(this.lastTrimLevel)) * 31) + InternalNano.hashCode(this.lru)) * 31) + this.pid) * 31) + InternalNano.hashCode(this.pkg)) * 31) + this.deviceLocalUid) * 31) + ((int) (this.timestampMillis ^ (this.timestampMillis >>> 32)))) * 31) + ((int) (this.durationMillis ^ (this.durationMillis >>> 32)))) * 31) + (this.stateChange ? 1231 : 1237)) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i2 = this.unknownFieldData.hashCode();
            }
            return i + i2;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (!this.processName.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(1, this.processName);
            }
            if (this.importance != 0) {
                output.writeInt32(2, this.importance);
            }
            if (this.importanceReasonCode != 0) {
                output.writeInt32(3, this.importanceReasonCode);
            }
            if (this.importanceReasonComponent != null) {
                output.writeMessage(4, this.importanceReasonComponent);
            }
            if (this.importanceReasonPid != 0) {
                output.writeInt32(5, this.importanceReasonPid);
            }
            if (this.lastTrimLevel != null && this.lastTrimLevel.length > 0) {
                for (int writeInt32 : this.lastTrimLevel) {
                    output.writeInt32(6, writeInt32);
                }
            }
            if (this.lru != null && this.lru.length > 0) {
                for (int writeInt322 : this.lru) {
                    output.writeInt32(7, writeInt322);
                }
            }
            if (this.pid != 0) {
                output.writeInt32(8, this.pid);
            }
            if (this.pkg != null && this.pkg.length > 0) {
                for (String element : this.pkg) {
                    if (element != null) {
                        output.writeString(9, element);
                    }
                }
            }
            if (this.deviceLocalUid != 0) {
                output.writeInt32(10, this.deviceLocalUid);
            }
            if (this.durationMillis != 0) {
                output.writeInt64(11, this.durationMillis);
            }
            if (this.stateChange) {
                output.writeBool(12, this.stateChange);
            }
            if (this.timestampMillis != 0) {
                output.writeInt64(13, this.timestampMillis);
            }
            if (this.lockboxOptInState != null) {
                output.writeMessage(14, this.lockboxOptInState);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int dataSize;
            int size = super.computeSerializedSize();
            if (!this.processName.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.processName);
            }
            if (this.importance != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(2, this.importance);
            }
            if (this.importanceReasonCode != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(3, this.importanceReasonCode);
            }
            if (this.importanceReasonComponent != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(4, this.importanceReasonComponent);
            }
            if (this.importanceReasonPid != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(5, this.importanceReasonPid);
            }
            if (this.lastTrimLevel != null && this.lastTrimLevel.length > 0) {
                dataSize = 0;
                for (int element : this.lastTrimLevel) {
                    dataSize += CodedOutputByteBufferNano.computeInt32SizeNoTag(element);
                }
                size = (size + dataSize) + (this.lastTrimLevel.length * 1);
            }
            if (this.lru != null && this.lru.length > 0) {
                dataSize = 0;
                for (int element2 : this.lru) {
                    dataSize += CodedOutputByteBufferNano.computeInt32SizeNoTag(element2);
                }
                size = (size + dataSize) + (this.lru.length * 1);
            }
            if (this.pid != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(8, this.pid);
            }
            if (this.pkg != null && this.pkg.length > 0) {
                int dataCount = 0;
                dataSize = 0;
                for (String element3 : this.pkg) {
                    if (element3 != null) {
                        dataCount++;
                        dataSize += CodedOutputByteBufferNano.computeStringSizeNoTag(element3);
                    }
                }
                size = (size + dataSize) + (dataCount * 1);
            }
            if (this.deviceLocalUid != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(10, this.deviceLocalUid);
            }
            if (this.durationMillis != 0) {
                size += CodedOutputByteBufferNano.computeInt64Size(11, this.durationMillis);
            }
            if (this.stateChange) {
                size += CodedOutputByteBufferNano.computeBoolSize(12, this.stateChange);
            }
            if (this.timestampMillis != 0) {
                size += CodedOutputByteBufferNano.computeInt64Size(13, this.timestampMillis);
            }
            if (this.lockboxOptInState != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(14, this.lockboxOptInState);
            }
            return size;
        }

        public AppUsageEvent mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                int value;
                int i;
                int length;
                int[] newArray;
                int limit;
                int arrayLength;
                int startPos;
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        this.processName = input.readString();
                        continue;
                    case LogSource.GMS_CORE_PEOPLE /*16*/:
                        value = input.readInt32();
                        switch (value) {
                            case Action.UNKNOWN /*0*/:
                            case LogSource.VISION /*100*/:
                            case LogSource.CHROMECAST_APP_LOG /*130*/:
                            case Importance.IMPORTANCE_VISIBLE /*200*/:
                            case Importance.IMPORTANCE_SERVICE /*300*/:
                            case Importance.IMPORTANCE_BACKGROUND /*400*/:
                            case Importance.IMPORTANCE_EMPTY /*500*/:
                                this.importance = value;
                                break;
                            default:
                                continue;
                        }
                    case LogSource.LB_C /*24*/:
                        value = input.readInt32();
                        switch (value) {
                            case Action.UNKNOWN /*0*/:
                            case TimeSelection.Type.TEMPORARY /*1*/:
                            case TimeSelection.Type.INDEFINITELY /*2*/:
                                this.importanceReasonCode = value;
                                break;
                            default:
                                continue;
                        }
                    case LogSource.NOVA /*34*/:
                        if (this.importanceReasonComponent == null) {
                            this.importanceReasonComponent = new ComponentName();
                        }
                        input.readMessage(this.importanceReasonComponent);
                        continue;
                    case LogSource.COPRESENCE /*40*/:
                        this.importanceReasonPid = input.readInt32();
                        continue;
                    case LogSource.BACKDROP /*48*/:
                        int length2 = WireFormatNano.getRepeatedFieldArrayLength(input, 48);
                        int[] validValues = new int[length2];
                        i = 0;
                        int validCount = 0;
                        while (i < length2) {
                            int validCount2;
                            if (i != 0) {
                                input.readTag();
                            }
                            value = input.readInt32();
                            switch (value) {
                                case Action.UNKNOWN /*0*/:
                                case Type.ADD_NEW_SHARES /*5*/:
                                case Type.TAP_ABOUT /*10*/:
                                case Type.OVEN_FRESH /*15*/:
                                case LogSource.ANDROID_GSA /*20*/:
                                case LogSource.COPRESENCE /*40*/:
                                case LogSource.WARP /*60*/:
                                case LogSource.CRONET_ANDROID_GSA /*80*/:
                                    validCount2 = validCount + 1;
                                    validValues[validCount] = value;
                                    break;
                                default:
                                    validCount2 = validCount;
                                    break;
                            }
                            i++;
                            validCount = validCount2;
                        }
                        if (validCount != 0) {
                            if (this.lastTrimLevel == null) {
                                i = 0;
                            } else {
                                i = this.lastTrimLevel.length;
                            }
                            if (i == 0) {
                                length = validValues.length;
                                if (validCount == r0) {
                                    this.lastTrimLevel = validValues;
                                    break;
                                }
                            }
                            newArray = new int[(i + validCount)];
                            if (i != 0) {
                                System.arraycopy(this.lastTrimLevel, 0, newArray, 0, i);
                            }
                            System.arraycopy(validValues, 0, newArray, i, validCount);
                            this.lastTrimLevel = newArray;
                            break;
                        }
                        continue;
                    case LogSource.HANGOUT_LOG_REQUEST /*50*/:
                        limit = input.pushLimit(input.readRawVarint32());
                        arrayLength = 0;
                        startPos = input.getPosition();
                        while (input.getBytesUntilLimit() > 0) {
                            switch (input.readInt32()) {
                                case Action.UNKNOWN /*0*/:
                                case Type.ADD_NEW_SHARES /*5*/:
                                case Type.TAP_ABOUT /*10*/:
                                case Type.OVEN_FRESH /*15*/:
                                case LogSource.ANDROID_GSA /*20*/:
                                case LogSource.COPRESENCE /*40*/:
                                case LogSource.WARP /*60*/:
                                case LogSource.CRONET_ANDROID_GSA /*80*/:
                                    arrayLength++;
                                    break;
                                default:
                                    break;
                            }
                        }
                        if (arrayLength != 0) {
                            input.rewindToPosition(startPos);
                            if (this.lastTrimLevel == null) {
                                i = 0;
                            } else {
                                i = this.lastTrimLevel.length;
                            }
                            newArray = new int[(i + arrayLength)];
                            if (i != 0) {
                                System.arraycopy(this.lastTrimLevel, 0, newArray, 0, i);
                            }
                            while (input.getBytesUntilLimit() > 0) {
                                value = input.readInt32();
                                switch (value) {
                                    case Action.UNKNOWN /*0*/:
                                    case Type.ADD_NEW_SHARES /*5*/:
                                    case Type.TAP_ABOUT /*10*/:
                                    case Type.OVEN_FRESH /*15*/:
                                    case LogSource.ANDROID_GSA /*20*/:
                                    case LogSource.COPRESENCE /*40*/:
                                    case LogSource.WARP /*60*/:
                                    case LogSource.CRONET_ANDROID_GSA /*80*/:
                                        int i2 = i + 1;
                                        newArray[i] = value;
                                        i = i2;
                                        break;
                                    default:
                                        break;
                                }
                            }
                            this.lastTrimLevel = newArray;
                        }
                        input.popLimit(limit);
                        continue;
                    case LogSource.DOCS /*56*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 56);
                        if (this.lru == null) {
                            i = 0;
                        } else {
                            i = this.lru.length;
                        }
                        newArray = new int[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.lru, 0, newArray, 0, i);
                        }
                        while (true) {
                            if (i < newArray.length - 1) {
                                newArray[i] = input.readInt32();
                                input.readTag();
                                i++;
                            } else {
                                newArray[i] = input.readInt32();
                                this.lru = newArray;
                                continue;
                            }
                        }
                    case LogSource.SLIDES /*58*/:
                        limit = input.pushLimit(input.readRawVarint32());
                        arrayLength = 0;
                        startPos = input.getPosition();
                        while (input.getBytesUntilLimit() > 0) {
                            input.readInt32();
                            arrayLength++;
                        }
                        input.rewindToPosition(startPos);
                        if (this.lru == null) {
                            i = 0;
                        } else {
                            i = this.lru.length;
                        }
                        newArray = new int[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.lru, 0, newArray, 0, i);
                        }
                        while (true) {
                            length = newArray.length;
                            if (i < r0) {
                                newArray[i] = input.readInt32();
                                i++;
                            } else {
                                this.lru = newArray;
                                input.popLimit(limit);
                                continue;
                            }
                        }
                    case LogSource.KIDS_COMMUNICATOR /*64*/:
                        this.pid = input.readInt32();
                        continue;
                    case LogSource.MOVIES /*74*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 74);
                        if (this.pkg == null) {
                            i = 0;
                        } else {
                            i = this.pkg.length;
                        }
                        String[] newArray2 = new String[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.pkg, 0, newArray2, 0, i);
                        }
                        while (true) {
                            if (i < newArray2.length - 1) {
                                newArray2[i] = input.readString();
                                input.readTag();
                                i++;
                            } else {
                                newArray2[i] = input.readString();
                                this.pkg = newArray2;
                                continue;
                            }
                        }
                    case LogSource.CRONET_ANDROID_GSA /*80*/:
                        this.deviceLocalUid = input.readInt32();
                        continue;
                    case LogSource.EMERGENCY_ASSIST /*88*/:
                        this.durationMillis = input.readInt64();
                        continue;
                    case LogSource.GCM_COUNTERS /*96*/:
                        this.stateChange = input.readBool();
                        continue;
                    case LogSource.MOBILESDK_CLIENT /*104*/:
                        this.timestampMillis = input.readInt64();
                        continue;
                    case LogSource.TRANSOM /*114*/:
                        if (this.lockboxOptInState == null) {
                            this.lockboxOptInState = new LockboxOptInState();
                        }
                        input.readMessage(this.lockboxOptInState);
                        continue;
                    default:
                        if (!storeUnknownField(input, tag)) {
                            break;
                        }
                        continue;
                }
                return this;
            }
        }

        public static AppUsageEvent parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (AppUsageEvent) MessageNano.mergeFrom(new AppUsageEvent(), data);
        }

        public static AppUsageEvent parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new AppUsageEvent().mergeFrom(input);
        }
    }

    public static final class AppUsageStatsEvent extends ExtendableMessageNano<AppUsageStatsEvent> {
        private static volatile AppUsageStatsEvent[] _emptyArray;
        public String className;
        public Configuration configuration;
        public int eventType;
        public LockboxOptInState lockboxOptInState;
        public String packageName;
        public long timestampMillis;

        public interface EventType {
            public static final int CONFIGURATION_CHANGE = 5;
            public static final int MOVE_TO_BACKGROUND = 2;
            public static final int MOVE_TO_FOREGROUND = 1;
            public static final int NONE = 0;
        }

        public static AppUsageStatsEvent[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new AppUsageStatsEvent[0];
                    }
                }
            }
            return _emptyArray;
        }

        public AppUsageStatsEvent() {
            clear();
        }

        public AppUsageStatsEvent clear() {
            this.eventType = 0;
            this.timestampMillis = 0;
            this.packageName = BuildConfig.VERSION_NAME;
            this.className = BuildConfig.VERSION_NAME;
            this.configuration = null;
            this.lockboxOptInState = null;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof AppUsageStatsEvent)) {
                return false;
            }
            AppUsageStatsEvent other = (AppUsageStatsEvent) o;
            if (this.eventType != other.eventType || this.timestampMillis != other.timestampMillis) {
                return false;
            }
            if (this.packageName == null) {
                if (other.packageName != null) {
                    return false;
                }
            } else if (!this.packageName.equals(other.packageName)) {
                return false;
            }
            if (this.className == null) {
                if (other.className != null) {
                    return false;
                }
            } else if (!this.className.equals(other.className)) {
                return false;
            }
            if (this.configuration == null) {
                if (other.configuration != null) {
                    return false;
                }
            } else if (!this.configuration.equals(other.configuration)) {
                return false;
            }
            if (this.lockboxOptInState == null) {
                if (other.lockboxOptInState != null) {
                    return false;
                }
            } else if (!this.lockboxOptInState.equals(other.lockboxOptInState)) {
                return false;
            }
            if (this.unknownFieldData != null && !this.unknownFieldData.isEmpty()) {
                return this.unknownFieldData.equals(other.unknownFieldData);
            }
            if (other.unknownFieldData == null || other.unknownFieldData.isEmpty()) {
                return true;
            }
            return false;
        }

        public int hashCode() {
            int i = 0;
            int hashCode = (((((((((((((getClass().getName().hashCode() + 527) * 31) + this.eventType) * 31) + ((int) (this.timestampMillis ^ (this.timestampMillis >>> 32)))) * 31) + (this.packageName == null ? 0 : this.packageName.hashCode())) * 31) + (this.className == null ? 0 : this.className.hashCode())) * 31) + (this.configuration == null ? 0 : this.configuration.hashCode())) * 31) + (this.lockboxOptInState == null ? 0 : this.lockboxOptInState.hashCode())) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.eventType != 0) {
                output.writeInt32(1, this.eventType);
            }
            if (this.timestampMillis != 0) {
                output.writeInt64(2, this.timestampMillis);
            }
            if (!this.packageName.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(3, this.packageName);
            }
            if (!this.className.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(4, this.className);
            }
            if (this.configuration != null) {
                output.writeMessage(5, this.configuration);
            }
            if (this.lockboxOptInState != null) {
                output.writeMessage(6, this.lockboxOptInState);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.eventType != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(1, this.eventType);
            }
            if (this.timestampMillis != 0) {
                size += CodedOutputByteBufferNano.computeInt64Size(2, this.timestampMillis);
            }
            if (!this.packageName.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(3, this.packageName);
            }
            if (!this.className.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(4, this.className);
            }
            if (this.configuration != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(5, this.configuration);
            }
            if (this.lockboxOptInState != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(6, this.lockboxOptInState);
            }
            return size;
        }

        public AppUsageStatsEvent mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_GET_LINK /*8*/:
                        int value = input.readInt32();
                        switch (value) {
                            case Action.UNKNOWN /*0*/:
                            case TimeSelection.Type.TEMPORARY /*1*/:
                            case TimeSelection.Type.INDEFINITELY /*2*/:
                            case Type.ADD_NEW_SHARES /*5*/:
                                this.eventType = value;
                                break;
                            default:
                                continue;
                        }
                    case LogSource.GMS_CORE_PEOPLE /*16*/:
                        this.timestampMillis = input.readInt64();
                        continue;
                    case LogSource.ANDROID_CAMERA /*26*/:
                        this.packageName = input.readString();
                        continue;
                    case LogSource.NOVA /*34*/:
                        this.className = input.readString();
                        continue;
                    case LogSource.PHOTOS /*42*/:
                        if (this.configuration == null) {
                            this.configuration = new Configuration();
                        }
                        input.readMessage(this.configuration);
                        continue;
                    case LogSource.HANGOUT_LOG_REQUEST /*50*/:
                        if (this.lockboxOptInState == null) {
                            this.lockboxOptInState = new LockboxOptInState();
                        }
                        input.readMessage(this.lockboxOptInState);
                        continue;
                    default:
                        if (!storeUnknownField(input, tag)) {
                            break;
                        }
                        continue;
                }
                return this;
            }
        }

        public static AppUsageStatsEvent parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (AppUsageStatsEvent) MessageNano.mergeFrom(new AppUsageStatsEvent(), data);
        }

        public static AppUsageStatsEvent parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new AppUsageStatsEvent().mergeFrom(input);
        }
    }

    public static final class ComponentName extends ExtendableMessageNano<ComponentName> {
        private static volatile ComponentName[] _emptyArray;
        public String className;
        public String packageName;

        public static ComponentName[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new ComponentName[0];
                    }
                }
            }
            return _emptyArray;
        }

        public ComponentName() {
            clear();
        }

        public ComponentName clear() {
            this.packageName = BuildConfig.VERSION_NAME;
            this.className = BuildConfig.VERSION_NAME;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof ComponentName)) {
                return false;
            }
            ComponentName other = (ComponentName) o;
            if (this.packageName == null) {
                if (other.packageName != null) {
                    return false;
                }
            } else if (!this.packageName.equals(other.packageName)) {
                return false;
            }
            if (this.className == null) {
                if (other.className != null) {
                    return false;
                }
            } else if (!this.className.equals(other.className)) {
                return false;
            }
            if (this.unknownFieldData != null && !this.unknownFieldData.isEmpty()) {
                return this.unknownFieldData.equals(other.unknownFieldData);
            }
            if (other.unknownFieldData == null || other.unknownFieldData.isEmpty()) {
                return true;
            }
            return false;
        }

        public int hashCode() {
            int i = 0;
            int hashCode = (((((getClass().getName().hashCode() + 527) * 31) + (this.packageName == null ? 0 : this.packageName.hashCode())) * 31) + (this.className == null ? 0 : this.className.hashCode())) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (!this.packageName.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(1, this.packageName);
            }
            if (!this.className.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(2, this.className);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (!this.packageName.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.packageName);
            }
            if (this.className.equals(BuildConfig.VERSION_NAME)) {
                return size;
            }
            return size + CodedOutputByteBufferNano.computeStringSize(2, this.className);
        }

        public ComponentName mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        this.packageName = input.readString();
                        continue;
                    case LogSource.GOOGLE_ANALYTICS /*18*/:
                        this.className = input.readString();
                        continue;
                    default:
                        if (!storeUnknownField(input, tag)) {
                            break;
                        }
                        continue;
                }
                return this;
            }
        }

        public static ComponentName parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (ComponentName) MessageNano.mergeFrom(new ComponentName(), data);
        }

        public static ComponentName parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new ComponentName().mergeFrom(input);
        }
    }

    public static final class Configuration extends ExtendableMessageNano<Configuration> {
        private static volatile Configuration[] _emptyArray;
        public float fontScale;
        public int hardKeyboardHidden;
        public int keyboardHidden;
        public String locale;
        public int navigationHidden;
        public int screenLayoutDir;
        public int screenOrientation;
        public int uiModeNight;
        public int uiModeType;

        public interface HardKeyboardHidden {
            public static final int HARD_KEY_HID_NO = 1;
            public static final int HARD_KEY_HID_UNDEFINED = 0;
            public static final int HARD_KEY_HID_YES = 2;
        }

        public interface KeyboardHidden {
            public static final int KEY_HID_NO = 1;
            public static final int KEY_HID_UNDEFINED = 0;
            public static final int KEY_HID_YES = 2;
        }

        public interface NavigationHidden {
            public static final int NAV_HID_NO = 1;
            public static final int NAV_HID_UNDEFINED = 0;
            public static final int NAV_HID_YES = 2;
        }

        public interface ScreenLayoutDir {
            public static final int DIR_LTR = 1;
            public static final int DIR_RTL = 2;
            public static final int DIR_UNDEFINED = 0;
        }

        public interface ScreenOrientation {
            public static final int ORI_LANDSCAPE = 2;
            public static final int ORI_PORTRAIT = 1;
            public static final int ORI_UNDEFINED = 0;
        }

        public interface UiModeNight {
            public static final int UI_NIGHT_NO = 1;
            public static final int UI_NIGHT_UNDEFINED = 0;
            public static final int UI_NIGHT_YES = 2;
        }

        public interface UiModeType {
            public static final int UI_TYPE_APPLIANCE = 5;
            public static final int UI_TYPE_CAR = 3;
            public static final int UI_TYPE_DESK = 2;
            public static final int UI_TYPE_NORMAL = 1;
            public static final int UI_TYPE_TELEVISION = 4;
            public static final int UI_TYPE_UNDEFINED = 0;
            public static final int UI_TYPE_WATCH = 6;
        }

        public static Configuration[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new Configuration[0];
                    }
                }
            }
            return _emptyArray;
        }

        public Configuration() {
            clear();
        }

        public Configuration clear() {
            this.fontScale = 0.0f;
            this.locale = BuildConfig.VERSION_NAME;
            this.screenLayoutDir = 0;
            this.screenOrientation = 0;
            this.uiModeType = 0;
            this.uiModeNight = 0;
            this.keyboardHidden = 0;
            this.hardKeyboardHidden = 0;
            this.navigationHidden = 0;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof Configuration)) {
                return false;
            }
            Configuration other = (Configuration) o;
            if (Float.floatToIntBits(this.fontScale) != Float.floatToIntBits(other.fontScale)) {
                return false;
            }
            if (this.locale == null) {
                if (other.locale != null) {
                    return false;
                }
            } else if (!this.locale.equals(other.locale)) {
                return false;
            }
            if (this.screenLayoutDir != other.screenLayoutDir || this.screenOrientation != other.screenOrientation || this.uiModeType != other.uiModeType || this.uiModeNight != other.uiModeNight || this.keyboardHidden != other.keyboardHidden || this.hardKeyboardHidden != other.hardKeyboardHidden || this.navigationHidden != other.navigationHidden) {
                return false;
            }
            if (this.unknownFieldData != null && !this.unknownFieldData.isEmpty()) {
                return this.unknownFieldData.equals(other.unknownFieldData);
            }
            if (other.unknownFieldData == null || other.unknownFieldData.isEmpty()) {
                return true;
            }
            return false;
        }

        public int hashCode() {
            int i = 0;
            int hashCode = (((((((((((((((((((getClass().getName().hashCode() + 527) * 31) + Float.floatToIntBits(this.fontScale)) * 31) + (this.locale == null ? 0 : this.locale.hashCode())) * 31) + this.screenLayoutDir) * 31) + this.screenOrientation) * 31) + this.uiModeType) * 31) + this.uiModeNight) * 31) + this.keyboardHidden) * 31) + this.hardKeyboardHidden) * 31) + this.navigationHidden) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (Float.floatToIntBits(this.fontScale) != Float.floatToIntBits(0.0f)) {
                output.writeFloat(1, this.fontScale);
            }
            if (!this.locale.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(2, this.locale);
            }
            if (this.screenLayoutDir != 0) {
                output.writeInt32(3, this.screenLayoutDir);
            }
            if (this.screenOrientation != 0) {
                output.writeInt32(4, this.screenOrientation);
            }
            if (this.uiModeType != 0) {
                output.writeInt32(5, this.uiModeType);
            }
            if (this.uiModeNight != 0) {
                output.writeInt32(6, this.uiModeNight);
            }
            if (this.keyboardHidden != 0) {
                output.writeInt32(7, this.keyboardHidden);
            }
            if (this.hardKeyboardHidden != 0) {
                output.writeInt32(8, this.hardKeyboardHidden);
            }
            if (this.navigationHidden != 0) {
                output.writeInt32(9, this.navigationHidden);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (Float.floatToIntBits(this.fontScale) != Float.floatToIntBits(0.0f)) {
                size += CodedOutputByteBufferNano.computeFloatSize(1, this.fontScale);
            }
            if (!this.locale.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(2, this.locale);
            }
            if (this.screenLayoutDir != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(3, this.screenLayoutDir);
            }
            if (this.screenOrientation != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(4, this.screenOrientation);
            }
            if (this.uiModeType != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(5, this.uiModeType);
            }
            if (this.uiModeNight != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(6, this.uiModeNight);
            }
            if (this.keyboardHidden != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(7, this.keyboardHidden);
            }
            if (this.hardKeyboardHidden != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(8, this.hardKeyboardHidden);
            }
            if (this.navigationHidden != 0) {
                return size + CodedOutputByteBufferNano.computeInt32Size(9, this.navigationHidden);
            }
            return size;
        }

        public Configuration mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                int value;
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.DISPLAY_ERROR /*13*/:
                        this.fontScale = input.readFloat();
                        continue;
                    case LogSource.GOOGLE_ANALYTICS /*18*/:
                        this.locale = input.readString();
                        continue;
                    case LogSource.LB_C /*24*/:
                        value = input.readInt32();
                        switch (value) {
                            case Action.UNKNOWN /*0*/:
                            case TimeSelection.Type.TEMPORARY /*1*/:
                            case TimeSelection.Type.INDEFINITELY /*2*/:
                                this.screenLayoutDir = value;
                                break;
                            default:
                                continue;
                        }
                    case LogSource.SOCIAL /*32*/:
                        value = input.readInt32();
                        switch (value) {
                            case Action.UNKNOWN /*0*/:
                            case TimeSelection.Type.TEMPORARY /*1*/:
                            case TimeSelection.Type.INDEFINITELY /*2*/:
                                this.screenOrientation = value;
                                break;
                            default:
                                continue;
                        }
                    case LogSource.COPRESENCE /*40*/:
                        value = input.readInt32();
                        switch (value) {
                            case Action.UNKNOWN /*0*/:
                            case TimeSelection.Type.TEMPORARY /*1*/:
                            case TimeSelection.Type.INDEFINITELY /*2*/:
                            case TimeSelection.Type.CUSTOM /*3*/:
                            case OvenFreshResult.NO_ACCOUNTS /*4*/:
                            case Type.ADD_NEW_SHARES /*5*/:
                            case Type.REMOVE_SHARE /*6*/:
                                this.uiModeType = value;
                                break;
                            default:
                                continue;
                        }
                    case LogSource.BACKDROP /*48*/:
                        value = input.readInt32();
                        switch (value) {
                            case Action.UNKNOWN /*0*/:
                            case TimeSelection.Type.TEMPORARY /*1*/:
                            case TimeSelection.Type.INDEFINITELY /*2*/:
                                this.uiModeNight = value;
                                break;
                            default:
                                continue;
                        }
                    case LogSource.DOCS /*56*/:
                        value = input.readInt32();
                        switch (value) {
                            case Action.UNKNOWN /*0*/:
                            case TimeSelection.Type.TEMPORARY /*1*/:
                            case TimeSelection.Type.INDEFINITELY /*2*/:
                                this.keyboardHidden = value;
                                break;
                            default:
                                continue;
                        }
                    case LogSource.KIDS_COMMUNICATOR /*64*/:
                        value = input.readInt32();
                        switch (value) {
                            case Action.UNKNOWN /*0*/:
                            case TimeSelection.Type.TEMPORARY /*1*/:
                            case TimeSelection.Type.INDEFINITELY /*2*/:
                                this.hardKeyboardHidden = value;
                                break;
                            default:
                                continue;
                        }
                    case LogSource.JUSTSPEAK /*72*/:
                        value = input.readInt32();
                        switch (value) {
                            case Action.UNKNOWN /*0*/:
                            case TimeSelection.Type.TEMPORARY /*1*/:
                            case TimeSelection.Type.INDEFINITELY /*2*/:
                                this.navigationHidden = value;
                                break;
                            default:
                                continue;
                        }
                    default:
                        if (!storeUnknownField(input, tag)) {
                            break;
                        }
                        continue;
                }
                return this;
            }
        }

        public static Configuration parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (Configuration) MessageNano.mergeFrom(new Configuration(), data);
        }

        public static Configuration parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new Configuration().mergeFrom(input);
        }
    }

    public static final class TaskInfoEvent extends ExtendableMessageNano<TaskInfoEvent> {
        private static volatile TaskInfoEvent[] _emptyArray;
        public ComponentName baseActivity;
        public String description;
        public long durationMillis;
        public int id;
        public int index;
        public LockboxOptInState lockboxOptInState;
        public int numActivities;
        public int numRunning;
        public boolean stateChange;
        public long timestampMillis;
        public ComponentName topActivity;

        public static TaskInfoEvent[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new TaskInfoEvent[0];
                    }
                }
            }
            return _emptyArray;
        }

        public TaskInfoEvent() {
            clear();
        }

        public TaskInfoEvent clear() {
            this.lockboxOptInState = null;
            this.timestampMillis = 0;
            this.stateChange = false;
            this.durationMillis = 0;
            this.index = 0;
            this.baseActivity = null;
            this.description = BuildConfig.VERSION_NAME;
            this.id = 0;
            this.numActivities = 0;
            this.numRunning = 0;
            this.topActivity = null;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof TaskInfoEvent)) {
                return false;
            }
            TaskInfoEvent other = (TaskInfoEvent) o;
            if (this.lockboxOptInState == null) {
                if (other.lockboxOptInState != null) {
                    return false;
                }
            } else if (!this.lockboxOptInState.equals(other.lockboxOptInState)) {
                return false;
            }
            if (this.timestampMillis != other.timestampMillis || this.stateChange != other.stateChange || this.durationMillis != other.durationMillis || this.index != other.index) {
                return false;
            }
            if (this.baseActivity == null) {
                if (other.baseActivity != null) {
                    return false;
                }
            } else if (!this.baseActivity.equals(other.baseActivity)) {
                return false;
            }
            if (this.description == null) {
                if (other.description != null) {
                    return false;
                }
            } else if (!this.description.equals(other.description)) {
                return false;
            }
            if (this.id != other.id || this.numActivities != other.numActivities || this.numRunning != other.numRunning) {
                return false;
            }
            if (this.topActivity == null) {
                if (other.topActivity != null) {
                    return false;
                }
            } else if (!this.topActivity.equals(other.topActivity)) {
                return false;
            }
            if (this.unknownFieldData != null && !this.unknownFieldData.isEmpty()) {
                return this.unknownFieldData.equals(other.unknownFieldData);
            }
            if (other.unknownFieldData == null || other.unknownFieldData.isEmpty()) {
                return true;
            }
            return false;
        }

        public int hashCode() {
            int i = 0;
            int hashCode = (((((((((((((((((((((((getClass().getName().hashCode() + 527) * 31) + (this.lockboxOptInState == null ? 0 : this.lockboxOptInState.hashCode())) * 31) + ((int) (this.timestampMillis ^ (this.timestampMillis >>> 32)))) * 31) + (this.stateChange ? 1231 : 1237)) * 31) + ((int) (this.durationMillis ^ (this.durationMillis >>> 32)))) * 31) + this.index) * 31) + (this.baseActivity == null ? 0 : this.baseActivity.hashCode())) * 31) + (this.description == null ? 0 : this.description.hashCode())) * 31) + this.id) * 31) + this.numActivities) * 31) + this.numRunning) * 31) + (this.topActivity == null ? 0 : this.topActivity.hashCode())) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.timestampMillis != 0) {
                output.writeInt64(1, this.timestampMillis);
            }
            if (this.stateChange) {
                output.writeBool(2, this.stateChange);
            }
            if (this.durationMillis != 0) {
                output.writeInt64(3, this.durationMillis);
            }
            if (this.index != 0) {
                output.writeInt32(4, this.index);
            }
            if (this.baseActivity != null) {
                output.writeMessage(5, this.baseActivity);
            }
            if (!this.description.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(6, this.description);
            }
            if (this.id != 0) {
                output.writeInt32(7, this.id);
            }
            if (this.numActivities != 0) {
                output.writeInt32(8, this.numActivities);
            }
            if (this.numRunning != 0) {
                output.writeInt32(9, this.numRunning);
            }
            if (this.topActivity != null) {
                output.writeMessage(10, this.topActivity);
            }
            if (this.lockboxOptInState != null) {
                output.writeMessage(11, this.lockboxOptInState);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.timestampMillis != 0) {
                size += CodedOutputByteBufferNano.computeInt64Size(1, this.timestampMillis);
            }
            if (this.stateChange) {
                size += CodedOutputByteBufferNano.computeBoolSize(2, this.stateChange);
            }
            if (this.durationMillis != 0) {
                size += CodedOutputByteBufferNano.computeInt64Size(3, this.durationMillis);
            }
            if (this.index != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(4, this.index);
            }
            if (this.baseActivity != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(5, this.baseActivity);
            }
            if (!this.description.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(6, this.description);
            }
            if (this.id != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(7, this.id);
            }
            if (this.numActivities != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(8, this.numActivities);
            }
            if (this.numRunning != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(9, this.numRunning);
            }
            if (this.topActivity != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(10, this.topActivity);
            }
            if (this.lockboxOptInState != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(11, this.lockboxOptInState);
            }
            return size;
        }

        public TaskInfoEvent mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_GET_LINK /*8*/:
                        this.timestampMillis = input.readInt64();
                        continue;
                    case LogSource.GMS_CORE_PEOPLE /*16*/:
                        this.stateChange = input.readBool();
                        continue;
                    case LogSource.LB_C /*24*/:
                        this.durationMillis = input.readInt64();
                        continue;
                    case LogSource.SOCIAL /*32*/:
                        this.index = input.readInt32();
                        continue;
                    case LogSource.PHOTOS /*42*/:
                        if (this.baseActivity == null) {
                            this.baseActivity = new ComponentName();
                        }
                        input.readMessage(this.baseActivity);
                        continue;
                    case LogSource.HANGOUT_LOG_REQUEST /*50*/:
                        this.description = input.readString();
                        continue;
                    case LogSource.DOCS /*56*/:
                        this.id = input.readInt32();
                        continue;
                    case LogSource.KIDS_COMMUNICATOR /*64*/:
                        this.numActivities = input.readInt32();
                        continue;
                    case LogSource.JUSTSPEAK /*72*/:
                        this.numRunning = input.readInt32();
                        continue;
                    case LogSource.GOOGLE_EXPRESS /*82*/:
                        if (this.topActivity == null) {
                            this.topActivity = new ComponentName();
                        }
                        input.readMessage(this.topActivity);
                        continue;
                    case LogSource.TRON_COUNTERS /*90*/:
                        if (this.lockboxOptInState == null) {
                            this.lockboxOptInState = new LockboxOptInState();
                        }
                        input.readMessage(this.lockboxOptInState);
                        continue;
                    default:
                        if (!storeUnknownField(input, tag)) {
                            break;
                        }
                        continue;
                }
                return this;
            }
        }

        public static TaskInfoEvent parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (TaskInfoEvent) MessageNano.mergeFrom(new TaskInfoEvent(), data);
        }

        public static TaskInfoEvent parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new TaskInfoEvent().mergeFrom(input);
        }
    }
}
