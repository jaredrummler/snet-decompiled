package com.google.kidsmanagement.v1;

import com.google.android.gms.lint.BuildConfig;
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
import com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection;
import java.io.IOException;

public interface TimeoutsCommonProto {

    public static final class AppTimeoutMetadata extends ExtendableMessageNano<AppTimeoutMetadata> {
        private static volatile AppTimeoutMetadata[] _emptyArray;
        public String[] packageNames;

        public static AppTimeoutMetadata[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new AppTimeoutMetadata[0];
                    }
                }
            }
            return _emptyArray;
        }

        public AppTimeoutMetadata() {
            clear();
        }

        public AppTimeoutMetadata clear() {
            this.packageNames = WireFormatNano.EMPTY_STRING_ARRAY;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof AppTimeoutMetadata)) {
                return false;
            }
            AppTimeoutMetadata other = (AppTimeoutMetadata) o;
            if (!InternalNano.equals(this.packageNames, other.packageNames)) {
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
            int hashCode = (((getClass().getName().hashCode() + 527) * 31) + InternalNano.hashCode(this.packageNames)) * 31;
            int hashCode2 = (this.unknownFieldData == null || this.unknownFieldData.isEmpty()) ? 0 : this.unknownFieldData.hashCode();
            return hashCode + hashCode2;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.packageNames != null && this.packageNames.length > 0) {
                for (String element : this.packageNames) {
                    if (element != null) {
                        output.writeString(1, element);
                    }
                }
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.packageNames == null || this.packageNames.length <= 0) {
                return size;
            }
            int dataCount = 0;
            int dataSize = 0;
            for (String element : this.packageNames) {
                if (element != null) {
                    dataCount++;
                    dataSize += CodedOutputByteBufferNano.computeStringSizeNoTag(element);
                }
            }
            return (size + dataSize) + (dataCount * 1);
        }

        public AppTimeoutMetadata mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 10);
                        int i = this.packageNames == null ? 0 : this.packageNames.length;
                        String[] newArray = new String[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.packageNames, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = input.readString();
                            input.readTag();
                            i++;
                        }
                        newArray[i] = input.readString();
                        this.packageNames = newArray;
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

        public static AppTimeoutMetadata parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (AppTimeoutMetadata) MessageNano.mergeFrom(new AppTimeoutMetadata(), data);
        }

        public static AppTimeoutMetadata parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new AppTimeoutMetadata().mergeFrom(input);
        }
    }

    public static final class Timeout extends ExtendableMessageNano<Timeout> {
        private static volatile Timeout[] _emptyArray;
        public AppTimeoutMetadata appTimeoutMetadata;
        public long durationMillis;
        public String id;
        public long ignoreTimestampMillis;
        public boolean isDisabled;
        public int[] repeatOnDayOfWeek;
        public long startTimestampMillis;
        public int theme;
        public int type;

        public static Timeout[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new Timeout[0];
                    }
                }
            }
            return _emptyArray;
        }

        public Timeout() {
            clear();
        }

        public Timeout clear() {
            this.id = BuildConfig.VERSION_NAME;
            this.type = 0;
            this.theme = 0;
            this.appTimeoutMetadata = null;
            this.startTimestampMillis = 0;
            this.durationMillis = 0;
            this.repeatOnDayOfWeek = WireFormatNano.EMPTY_INT_ARRAY;
            this.isDisabled = false;
            this.ignoreTimestampMillis = 0;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof Timeout)) {
                return false;
            }
            Timeout other = (Timeout) o;
            if (this.id == null) {
                if (other.id != null) {
                    return false;
                }
            } else if (!this.id.equals(other.id)) {
                return false;
            }
            if (this.type != other.type || this.theme != other.theme) {
                return false;
            }
            if (this.appTimeoutMetadata == null) {
                if (other.appTimeoutMetadata != null) {
                    return false;
                }
            } else if (!this.appTimeoutMetadata.equals(other.appTimeoutMetadata)) {
                return false;
            }
            if (this.startTimestampMillis != other.startTimestampMillis || this.durationMillis != other.durationMillis || !InternalNano.equals(this.repeatOnDayOfWeek, other.repeatOnDayOfWeek) || this.isDisabled != other.isDisabled || this.ignoreTimestampMillis != other.ignoreTimestampMillis) {
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
            int hashCode = (((((((((((((((((((getClass().getName().hashCode() + 527) * 31) + (this.id == null ? 0 : this.id.hashCode())) * 31) + this.type) * 31) + this.theme) * 31) + (this.appTimeoutMetadata == null ? 0 : this.appTimeoutMetadata.hashCode())) * 31) + ((int) (this.startTimestampMillis ^ (this.startTimestampMillis >>> 32)))) * 31) + ((int) (this.durationMillis ^ (this.durationMillis >>> 32)))) * 31) + InternalNano.hashCode(this.repeatOnDayOfWeek)) * 31) + (this.isDisabled ? 1231 : 1237)) * 31) + ((int) (this.ignoreTimestampMillis ^ (this.ignoreTimestampMillis >>> 32)))) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (!this.id.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(1, this.id);
            }
            if (this.type != 0) {
                output.writeInt32(2, this.type);
            }
            if (this.theme != 0) {
                output.writeInt32(3, this.theme);
            }
            if (this.appTimeoutMetadata != null) {
                output.writeMessage(4, this.appTimeoutMetadata);
            }
            if (this.startTimestampMillis != 0) {
                output.writeUInt64(5, this.startTimestampMillis);
            }
            if (this.durationMillis != 0) {
                output.writeUInt64(6, this.durationMillis);
            }
            if (this.repeatOnDayOfWeek != null && this.repeatOnDayOfWeek.length > 0) {
                for (int writeUInt32 : this.repeatOnDayOfWeek) {
                    output.writeUInt32(7, writeUInt32);
                }
            }
            if (this.isDisabled) {
                output.writeBool(8, this.isDisabled);
            }
            if (this.ignoreTimestampMillis != 0) {
                output.writeUInt64(9, this.ignoreTimestampMillis);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (!this.id.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.id);
            }
            if (this.type != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(2, this.type);
            }
            if (this.theme != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(3, this.theme);
            }
            if (this.appTimeoutMetadata != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(4, this.appTimeoutMetadata);
            }
            if (this.startTimestampMillis != 0) {
                size += CodedOutputByteBufferNano.computeUInt64Size(5, this.startTimestampMillis);
            }
            if (this.durationMillis != 0) {
                size += CodedOutputByteBufferNano.computeUInt64Size(6, this.durationMillis);
            }
            if (this.repeatOnDayOfWeek != null && this.repeatOnDayOfWeek.length > 0) {
                int dataSize = 0;
                for (int element : this.repeatOnDayOfWeek) {
                    dataSize += CodedOutputByteBufferNano.computeUInt32SizeNoTag(element);
                }
                size = (size + dataSize) + (this.repeatOnDayOfWeek.length * 1);
            }
            if (this.isDisabled) {
                size += CodedOutputByteBufferNano.computeBoolSize(8, this.isDisabled);
            }
            if (this.ignoreTimestampMillis != 0) {
                return size + CodedOutputByteBufferNano.computeUInt64Size(9, this.ignoreTimestampMillis);
            }
            return size;
        }

        public Timeout mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                int value;
                int arrayLength;
                int i;
                int[] newArray;
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        this.id = input.readString();
                        continue;
                    case LogSource.GMS_CORE_PEOPLE /*16*/:
                        value = input.readInt32();
                        switch (value) {
                            case Action.UNKNOWN /*0*/:
                            case TimeSelection.Type.TEMPORARY /*1*/:
                            case TimeSelection.Type.INDEFINITELY /*2*/:
                                this.type = value;
                                break;
                            default:
                                continue;
                        }
                    case LogSource.LB_C /*24*/:
                        value = input.readInt32();
                        switch (value) {
                            case Action.UNKNOWN /*0*/:
                            case TimeSelection.Type.TEMPORARY /*1*/:
                                this.theme = value;
                                break;
                            default:
                                continue;
                        }
                    case LogSource.NOVA /*34*/:
                        if (this.appTimeoutMetadata == null) {
                            this.appTimeoutMetadata = new AppTimeoutMetadata();
                        }
                        input.readMessage(this.appTimeoutMetadata);
                        continue;
                    case LogSource.COPRESENCE /*40*/:
                        this.startTimestampMillis = input.readUInt64();
                        continue;
                    case LogSource.BACKDROP /*48*/:
                        this.durationMillis = input.readUInt64();
                        continue;
                    case LogSource.DOCS /*56*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 56);
                        i = this.repeatOnDayOfWeek == null ? 0 : this.repeatOnDayOfWeek.length;
                        newArray = new int[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.repeatOnDayOfWeek, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = input.readUInt32();
                            input.readTag();
                            i++;
                        }
                        newArray[i] = input.readUInt32();
                        this.repeatOnDayOfWeek = newArray;
                        continue;
                    case LogSource.SLIDES /*58*/:
                        int limit = input.pushLimit(input.readRawVarint32());
                        arrayLength = 0;
                        int startPos = input.getPosition();
                        while (input.getBytesUntilLimit() > 0) {
                            input.readUInt32();
                            arrayLength++;
                        }
                        input.rewindToPosition(startPos);
                        i = this.repeatOnDayOfWeek == null ? 0 : this.repeatOnDayOfWeek.length;
                        newArray = new int[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.repeatOnDayOfWeek, 0, newArray, 0, i);
                        }
                        while (i < newArray.length) {
                            newArray[i] = input.readUInt32();
                            i++;
                        }
                        this.repeatOnDayOfWeek = newArray;
                        input.popLimit(limit);
                        continue;
                    case LogSource.KIDS_COMMUNICATOR /*64*/:
                        this.isDisabled = input.readBool();
                        continue;
                    case LogSource.JUSTSPEAK /*72*/:
                        this.ignoreTimestampMillis = input.readUInt64();
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

        public static Timeout parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (Timeout) MessageNano.mergeFrom(new Timeout(), data);
        }

        public static Timeout parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new Timeout().mergeFrom(input);
        }
    }

    public interface TimeoutState {
        public static final int TIMEOUT_ACTIVE = 1;
        public static final int TIMEOUT_IGNORED = 3;
        public static final int TIMEOUT_INACTIVE = 2;
        public static final int UNKNOWN_TIMEOUT_STATE = 0;
    }

    public interface TimeoutTheme {
        public static final int BED_TIME_THEME = 1;
        public static final int UNSPEFICIED_TIMEOUT_THEME = 0;
    }

    public interface TimeoutType {
        public static final int APP_TIMEOUT = 1;
        public static final int FULL_TIMEOUT = 2;
        public static final int UNSPECIFIED_TIMEOUT = 0;
    }
}
