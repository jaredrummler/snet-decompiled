package com.google.common.logging;

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
import java.util.Arrays;

public interface PhenotypeLogProto {

    public static final class ApplicationInfo extends ExtendableMessageNano<ApplicationInfo> {
        private static volatile ApplicationInfo[] _emptyArray;
        public String commitToken;
        public String[] logSourceName;
        public String packageName;
        public int packageVersion;
        public String user;
        public int[] weakExperimentId;

        public static ApplicationInfo[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new ApplicationInfo[0];
                    }
                }
            }
            return _emptyArray;
        }

        public ApplicationInfo() {
            clear();
        }

        public ApplicationInfo clear() {
            this.packageName = BuildConfig.VERSION_NAME;
            this.packageVersion = 0;
            this.logSourceName = WireFormatNano.EMPTY_STRING_ARRAY;
            this.weakExperimentId = WireFormatNano.EMPTY_INT_ARRAY;
            this.user = BuildConfig.VERSION_NAME;
            this.commitToken = BuildConfig.VERSION_NAME;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof ApplicationInfo)) {
                return false;
            }
            ApplicationInfo other = (ApplicationInfo) o;
            if (this.packageName == null) {
                if (other.packageName != null) {
                    return false;
                }
            } else if (!this.packageName.equals(other.packageName)) {
                return false;
            }
            if (this.packageVersion != other.packageVersion || !InternalNano.equals(this.logSourceName, other.logSourceName) || !InternalNano.equals(this.weakExperimentId, other.weakExperimentId)) {
                return false;
            }
            if (this.user == null) {
                if (other.user != null) {
                    return false;
                }
            } else if (!this.user.equals(other.user)) {
                return false;
            }
            if (this.commitToken == null) {
                if (other.commitToken != null) {
                    return false;
                }
            } else if (!this.commitToken.equals(other.commitToken)) {
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
            int hashCode = (((((((((((((getClass().getName().hashCode() + 527) * 31) + (this.packageName == null ? 0 : this.packageName.hashCode())) * 31) + this.packageVersion) * 31) + InternalNano.hashCode(this.logSourceName)) * 31) + InternalNano.hashCode(this.weakExperimentId)) * 31) + (this.user == null ? 0 : this.user.hashCode())) * 31) + (this.commitToken == null ? 0 : this.commitToken.hashCode())) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (!this.packageName.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(1, this.packageName);
            }
            if (this.packageVersion != 0) {
                output.writeInt32(2, this.packageVersion);
            }
            if (this.logSourceName != null && this.logSourceName.length > 0) {
                for (String element : this.logSourceName) {
                    if (element != null) {
                        output.writeString(3, element);
                    }
                }
            }
            if (this.weakExperimentId != null && this.weakExperimentId.length > 0) {
                for (int writeInt32 : this.weakExperimentId) {
                    output.writeInt32(5, writeInt32);
                }
            }
            if (!this.user.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(6, this.user);
            }
            if (!this.commitToken.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(7, this.commitToken);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int dataSize;
            int size = super.computeSerializedSize();
            if (!this.packageName.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.packageName);
            }
            if (this.packageVersion != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(2, this.packageVersion);
            }
            if (this.logSourceName != null && this.logSourceName.length > 0) {
                int dataCount = 0;
                dataSize = 0;
                for (String element : this.logSourceName) {
                    if (element != null) {
                        dataCount++;
                        dataSize += CodedOutputByteBufferNano.computeStringSizeNoTag(element);
                    }
                }
                size = (size + dataSize) + (dataCount * 1);
            }
            if (this.weakExperimentId != null && this.weakExperimentId.length > 0) {
                dataSize = 0;
                for (int element2 : this.weakExperimentId) {
                    dataSize += CodedOutputByteBufferNano.computeInt32SizeNoTag(element2);
                }
                size = (size + dataSize) + (this.weakExperimentId.length * 1);
            }
            if (!this.user.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(6, this.user);
            }
            if (this.commitToken.equals(BuildConfig.VERSION_NAME)) {
                return size;
            }
            return size + CodedOutputByteBufferNano.computeStringSize(7, this.commitToken);
        }

        public ApplicationInfo mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                int arrayLength;
                int i;
                int[] newArray;
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        this.packageName = input.readString();
                        continue;
                    case LogSource.GMS_CORE_PEOPLE /*16*/:
                        this.packageVersion = input.readInt32();
                        continue;
                    case LogSource.ANDROID_CAMERA /*26*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 26);
                        i = this.logSourceName == null ? 0 : this.logSourceName.length;
                        String[] newArray2 = new String[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.logSourceName, 0, newArray2, 0, i);
                        }
                        while (i < newArray2.length - 1) {
                            newArray2[i] = input.readString();
                            input.readTag();
                            i++;
                        }
                        newArray2[i] = input.readString();
                        this.logSourceName = newArray2;
                        continue;
                    case LogSource.COPRESENCE /*40*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 40);
                        i = this.weakExperimentId == null ? 0 : this.weakExperimentId.length;
                        newArray = new int[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.weakExperimentId, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = input.readInt32();
                            input.readTag();
                            i++;
                        }
                        newArray[i] = input.readInt32();
                        this.weakExperimentId = newArray;
                        continue;
                    case LogSource.PHOTOS /*42*/:
                        int limit = input.pushLimit(input.readRawVarint32());
                        arrayLength = 0;
                        int startPos = input.getPosition();
                        while (input.getBytesUntilLimit() > 0) {
                            input.readInt32();
                            arrayLength++;
                        }
                        input.rewindToPosition(startPos);
                        i = this.weakExperimentId == null ? 0 : this.weakExperimentId.length;
                        newArray = new int[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.weakExperimentId, 0, newArray, 0, i);
                        }
                        while (i < newArray.length) {
                            newArray[i] = input.readInt32();
                            i++;
                        }
                        this.weakExperimentId = newArray;
                        input.popLimit(limit);
                        continue;
                    case LogSource.HANGOUT_LOG_REQUEST /*50*/:
                        this.user = input.readString();
                        continue;
                    case LogSource.SLIDES /*58*/:
                        this.commitToken = input.readString();
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

        public static ApplicationInfo parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (ApplicationInfo) MessageNano.mergeFrom(new ApplicationInfo(), data);
        }

        public static ApplicationInfo parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new ApplicationInfo().mergeFrom(input);
        }
    }

    public static final class ExperimentInfo extends ExtendableMessageNano<ExperimentInfo> {
        private static volatile ExperimentInfo[] _emptyArray;
        public int[] alwaysExperimentId;
        public int[] experimentId;
        public byte[] experimentToken;
        public int[] gaiaExperimentId;
        public int[] othersExperimentId;
        public String packageName;
        public int[] pseudonymousExperimentId;
        public long version;

        public static ExperimentInfo[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new ExperimentInfo[0];
                    }
                }
            }
            return _emptyArray;
        }

        public ExperimentInfo() {
            clear();
        }

        public ExperimentInfo clear() {
            this.packageName = BuildConfig.VERSION_NAME;
            this.version = 0;
            this.experimentToken = WireFormatNano.EMPTY_BYTES;
            this.experimentId = WireFormatNano.EMPTY_INT_ARRAY;
            this.gaiaExperimentId = WireFormatNano.EMPTY_INT_ARRAY;
            this.pseudonymousExperimentId = WireFormatNano.EMPTY_INT_ARRAY;
            this.alwaysExperimentId = WireFormatNano.EMPTY_INT_ARRAY;
            this.othersExperimentId = WireFormatNano.EMPTY_INT_ARRAY;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof ExperimentInfo)) {
                return false;
            }
            ExperimentInfo other = (ExperimentInfo) o;
            if (this.packageName == null) {
                if (other.packageName != null) {
                    return false;
                }
            } else if (!this.packageName.equals(other.packageName)) {
                return false;
            }
            if (this.version != other.version || !Arrays.equals(this.experimentToken, other.experimentToken) || !InternalNano.equals(this.experimentId, other.experimentId) || !InternalNano.equals(this.gaiaExperimentId, other.gaiaExperimentId) || !InternalNano.equals(this.pseudonymousExperimentId, other.pseudonymousExperimentId) || !InternalNano.equals(this.alwaysExperimentId, other.alwaysExperimentId) || !InternalNano.equals(this.othersExperimentId, other.othersExperimentId)) {
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
            int hashCode = (getClass().getName().hashCode() + 527) * 31;
            if (this.packageName == null) {
                i = 0;
            } else {
                i = this.packageName.hashCode();
            }
            i = (((((((((((((((hashCode + i) * 31) + ((int) (this.version ^ (this.version >>> 32)))) * 31) + Arrays.hashCode(this.experimentToken)) * 31) + InternalNano.hashCode(this.experimentId)) * 31) + InternalNano.hashCode(this.gaiaExperimentId)) * 31) + InternalNano.hashCode(this.pseudonymousExperimentId)) * 31) + InternalNano.hashCode(this.alwaysExperimentId)) * 31) + InternalNano.hashCode(this.othersExperimentId)) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i2 = this.unknownFieldData.hashCode();
            }
            return i + i2;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.version != 0) {
                output.writeInt64(1, this.version);
            }
            if (!Arrays.equals(this.experimentToken, WireFormatNano.EMPTY_BYTES)) {
                output.writeBytes(2, this.experimentToken);
            }
            if (this.experimentId != null && this.experimentId.length > 0) {
                for (int writeInt32 : this.experimentId) {
                    output.writeInt32(3, writeInt32);
                }
            }
            if (!this.packageName.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(4, this.packageName);
            }
            if (this.gaiaExperimentId != null && this.gaiaExperimentId.length > 0) {
                for (int writeInt322 : this.gaiaExperimentId) {
                    output.writeInt32(5, writeInt322);
                }
            }
            if (this.pseudonymousExperimentId != null && this.pseudonymousExperimentId.length > 0) {
                for (int writeInt3222 : this.pseudonymousExperimentId) {
                    output.writeInt32(6, writeInt3222);
                }
            }
            if (this.alwaysExperimentId != null && this.alwaysExperimentId.length > 0) {
                for (int writeInt32222 : this.alwaysExperimentId) {
                    output.writeInt32(7, writeInt32222);
                }
            }
            if (this.othersExperimentId != null && this.othersExperimentId.length > 0) {
                for (int writeInt322222 : this.othersExperimentId) {
                    output.writeInt32(8, writeInt322222);
                }
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int dataSize;
            int size = super.computeSerializedSize();
            if (this.version != 0) {
                size += CodedOutputByteBufferNano.computeInt64Size(1, this.version);
            }
            if (!Arrays.equals(this.experimentToken, WireFormatNano.EMPTY_BYTES)) {
                size += CodedOutputByteBufferNano.computeBytesSize(2, this.experimentToken);
            }
            if (this.experimentId != null && this.experimentId.length > 0) {
                dataSize = 0;
                for (int element : this.experimentId) {
                    dataSize += CodedOutputByteBufferNano.computeInt32SizeNoTag(element);
                }
                size = (size + dataSize) + (this.experimentId.length * 1);
            }
            if (!this.packageName.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(4, this.packageName);
            }
            if (this.gaiaExperimentId != null && this.gaiaExperimentId.length > 0) {
                dataSize = 0;
                for (int element2 : this.gaiaExperimentId) {
                    dataSize += CodedOutputByteBufferNano.computeInt32SizeNoTag(element2);
                }
                size = (size + dataSize) + (this.gaiaExperimentId.length * 1);
            }
            if (this.pseudonymousExperimentId != null && this.pseudonymousExperimentId.length > 0) {
                dataSize = 0;
                for (int element22 : this.pseudonymousExperimentId) {
                    dataSize += CodedOutputByteBufferNano.computeInt32SizeNoTag(element22);
                }
                size = (size + dataSize) + (this.pseudonymousExperimentId.length * 1);
            }
            if (this.alwaysExperimentId != null && this.alwaysExperimentId.length > 0) {
                dataSize = 0;
                for (int element222 : this.alwaysExperimentId) {
                    dataSize += CodedOutputByteBufferNano.computeInt32SizeNoTag(element222);
                }
                size = (size + dataSize) + (this.alwaysExperimentId.length * 1);
            }
            if (this.othersExperimentId == null || this.othersExperimentId.length <= 0) {
                return size;
            }
            dataSize = 0;
            for (int element2222 : this.othersExperimentId) {
                dataSize += CodedOutputByteBufferNano.computeInt32SizeNoTag(element2222);
            }
            return (size + dataSize) + (this.othersExperimentId.length * 1);
        }

        public ExperimentInfo mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                int arrayLength;
                int i;
                int[] newArray;
                int limit;
                int startPos;
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_GET_LINK /*8*/:
                        this.version = input.readInt64();
                        continue;
                    case LogSource.GOOGLE_ANALYTICS /*18*/:
                        this.experimentToken = input.readBytes();
                        continue;
                    case LogSource.LB_C /*24*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 24);
                        i = this.experimentId == null ? 0 : this.experimentId.length;
                        newArray = new int[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.experimentId, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = input.readInt32();
                            input.readTag();
                            i++;
                        }
                        newArray[i] = input.readInt32();
                        this.experimentId = newArray;
                        continue;
                    case LogSource.ANDROID_CAMERA /*26*/:
                        limit = input.pushLimit(input.readRawVarint32());
                        arrayLength = 0;
                        startPos = input.getPosition();
                        while (input.getBytesUntilLimit() > 0) {
                            input.readInt32();
                            arrayLength++;
                        }
                        input.rewindToPosition(startPos);
                        i = this.experimentId == null ? 0 : this.experimentId.length;
                        newArray = new int[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.experimentId, 0, newArray, 0, i);
                        }
                        while (i < newArray.length) {
                            newArray[i] = input.readInt32();
                            i++;
                        }
                        this.experimentId = newArray;
                        input.popLimit(limit);
                        continue;
                    case LogSource.NOVA /*34*/:
                        this.packageName = input.readString();
                        continue;
                    case LogSource.COPRESENCE /*40*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 40);
                        i = this.gaiaExperimentId == null ? 0 : this.gaiaExperimentId.length;
                        newArray = new int[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.gaiaExperimentId, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = input.readInt32();
                            input.readTag();
                            i++;
                        }
                        newArray[i] = input.readInt32();
                        this.gaiaExperimentId = newArray;
                        continue;
                    case LogSource.PHOTOS /*42*/:
                        limit = input.pushLimit(input.readRawVarint32());
                        arrayLength = 0;
                        startPos = input.getPosition();
                        while (input.getBytesUntilLimit() > 0) {
                            input.readInt32();
                            arrayLength++;
                        }
                        input.rewindToPosition(startPos);
                        i = this.gaiaExperimentId == null ? 0 : this.gaiaExperimentId.length;
                        newArray = new int[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.gaiaExperimentId, 0, newArray, 0, i);
                        }
                        while (i < newArray.length) {
                            newArray[i] = input.readInt32();
                            i++;
                        }
                        this.gaiaExperimentId = newArray;
                        input.popLimit(limit);
                        continue;
                    case LogSource.BACKDROP /*48*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 48);
                        i = this.pseudonymousExperimentId == null ? 0 : this.pseudonymousExperimentId.length;
                        newArray = new int[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.pseudonymousExperimentId, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = input.readInt32();
                            input.readTag();
                            i++;
                        }
                        newArray[i] = input.readInt32();
                        this.pseudonymousExperimentId = newArray;
                        continue;
                    case LogSource.HANGOUT_LOG_REQUEST /*50*/:
                        limit = input.pushLimit(input.readRawVarint32());
                        arrayLength = 0;
                        startPos = input.getPosition();
                        while (input.getBytesUntilLimit() > 0) {
                            input.readInt32();
                            arrayLength++;
                        }
                        input.rewindToPosition(startPos);
                        i = this.pseudonymousExperimentId == null ? 0 : this.pseudonymousExperimentId.length;
                        newArray = new int[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.pseudonymousExperimentId, 0, newArray, 0, i);
                        }
                        while (i < newArray.length) {
                            newArray[i] = input.readInt32();
                            i++;
                        }
                        this.pseudonymousExperimentId = newArray;
                        input.popLimit(limit);
                        continue;
                    case LogSource.DOCS /*56*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 56);
                        i = this.alwaysExperimentId == null ? 0 : this.alwaysExperimentId.length;
                        newArray = new int[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.alwaysExperimentId, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = input.readInt32();
                            input.readTag();
                            i++;
                        }
                        newArray[i] = input.readInt32();
                        this.alwaysExperimentId = newArray;
                        continue;
                    case LogSource.SLIDES /*58*/:
                        limit = input.pushLimit(input.readRawVarint32());
                        arrayLength = 0;
                        startPos = input.getPosition();
                        while (input.getBytesUntilLimit() > 0) {
                            input.readInt32();
                            arrayLength++;
                        }
                        input.rewindToPosition(startPos);
                        i = this.alwaysExperimentId == null ? 0 : this.alwaysExperimentId.length;
                        newArray = new int[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.alwaysExperimentId, 0, newArray, 0, i);
                        }
                        while (i < newArray.length) {
                            newArray[i] = input.readInt32();
                            i++;
                        }
                        this.alwaysExperimentId = newArray;
                        input.popLimit(limit);
                        continue;
                    case LogSource.KIDS_COMMUNICATOR /*64*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 64);
                        i = this.othersExperimentId == null ? 0 : this.othersExperimentId.length;
                        newArray = new int[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.othersExperimentId, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = input.readInt32();
                            input.readTag();
                            i++;
                        }
                        newArray[i] = input.readInt32();
                        this.othersExperimentId = newArray;
                        continue;
                    case LogSource.WIFI_ASSISTANT /*66*/:
                        limit = input.pushLimit(input.readRawVarint32());
                        arrayLength = 0;
                        startPos = input.getPosition();
                        while (input.getBytesUntilLimit() > 0) {
                            input.readInt32();
                            arrayLength++;
                        }
                        input.rewindToPosition(startPos);
                        i = this.othersExperimentId == null ? 0 : this.othersExperimentId.length;
                        newArray = new int[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.othersExperimentId, 0, newArray, 0, i);
                        }
                        while (i < newArray.length) {
                            newArray[i] = input.readInt32();
                            i++;
                        }
                        this.othersExperimentId = newArray;
                        input.popLimit(limit);
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

        public static ExperimentInfo parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (ExperimentInfo) MessageNano.mergeFrom(new ExperimentInfo(), data);
        }

        public static ExperimentInfo parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new ExperimentInfo().mergeFrom(input);
        }
    }

    public static final class PhenotypeInfo extends ExtendableMessageNano<PhenotypeInfo> {
        private static volatile PhenotypeInfo[] _emptyArray;
        public int commitSource;
        public boolean committedDummyBooleanFlag;
        public int committedDummyIntegerFlag;
        public String committedDummyStringFlag;
        public boolean dummyBooleanFlag;
        public int dummyIntegerFlag;
        public String dummyStringFlag;
        public ExperimentInfo[] uncommittedExperimentInfo;

        public interface PhenotypeCommitSource {
            public static final int BROADCAST = 1;
            public static final int PERIODICAL = 2;
            public static final int UNKNOWN = 0;
        }

        public static PhenotypeInfo[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new PhenotypeInfo[0];
                    }
                }
            }
            return _emptyArray;
        }

        public PhenotypeInfo() {
            clear();
        }

        public PhenotypeInfo clear() {
            this.dummyBooleanFlag = false;
            this.dummyIntegerFlag = 0;
            this.dummyStringFlag = BuildConfig.VERSION_NAME;
            this.committedDummyBooleanFlag = false;
            this.committedDummyIntegerFlag = 0;
            this.committedDummyStringFlag = BuildConfig.VERSION_NAME;
            this.uncommittedExperimentInfo = ExperimentInfo.emptyArray();
            this.commitSource = 0;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof PhenotypeInfo)) {
                return false;
            }
            PhenotypeInfo other = (PhenotypeInfo) o;
            if (this.dummyBooleanFlag != other.dummyBooleanFlag || this.dummyIntegerFlag != other.dummyIntegerFlag) {
                return false;
            }
            if (this.dummyStringFlag == null) {
                if (other.dummyStringFlag != null) {
                    return false;
                }
            } else if (!this.dummyStringFlag.equals(other.dummyStringFlag)) {
                return false;
            }
            if (this.committedDummyBooleanFlag != other.committedDummyBooleanFlag || this.committedDummyIntegerFlag != other.committedDummyIntegerFlag) {
                return false;
            }
            if (this.committedDummyStringFlag == null) {
                if (other.committedDummyStringFlag != null) {
                    return false;
                }
            } else if (!this.committedDummyStringFlag.equals(other.committedDummyStringFlag)) {
                return false;
            }
            if (!InternalNano.equals(this.uncommittedExperimentInfo, other.uncommittedExperimentInfo) || this.commitSource != other.commitSource) {
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
            int i = 1231;
            int i2 = 0;
            int hashCode = (((((((getClass().getName().hashCode() + 527) * 31) + (this.dummyBooleanFlag ? 1231 : 1237)) * 31) + this.dummyIntegerFlag) * 31) + (this.dummyStringFlag == null ? 0 : this.dummyStringFlag.hashCode())) * 31;
            if (!this.committedDummyBooleanFlag) {
                i = 1237;
            }
            hashCode = (((((((((hashCode + i) * 31) + this.committedDummyIntegerFlag) * 31) + (this.committedDummyStringFlag == null ? 0 : this.committedDummyStringFlag.hashCode())) * 31) + InternalNano.hashCode(this.uncommittedExperimentInfo)) * 31) + this.commitSource) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i2 = this.unknownFieldData.hashCode();
            }
            return hashCode + i2;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.dummyBooleanFlag) {
                output.writeBool(1, this.dummyBooleanFlag);
            }
            if (this.dummyIntegerFlag != 0) {
                output.writeInt32(2, this.dummyIntegerFlag);
            }
            if (!this.dummyStringFlag.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(3, this.dummyStringFlag);
            }
            if (this.uncommittedExperimentInfo != null && this.uncommittedExperimentInfo.length > 0) {
                for (ExperimentInfo element : this.uncommittedExperimentInfo) {
                    if (element != null) {
                        output.writeMessage(4, element);
                    }
                }
            }
            if (this.committedDummyBooleanFlag) {
                output.writeBool(5, this.committedDummyBooleanFlag);
            }
            if (this.committedDummyIntegerFlag != 0) {
                output.writeInt32(6, this.committedDummyIntegerFlag);
            }
            if (!this.committedDummyStringFlag.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(7, this.committedDummyStringFlag);
            }
            if (this.commitSource != 0) {
                output.writeInt32(8, this.commitSource);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.dummyBooleanFlag) {
                size += CodedOutputByteBufferNano.computeBoolSize(1, this.dummyBooleanFlag);
            }
            if (this.dummyIntegerFlag != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(2, this.dummyIntegerFlag);
            }
            if (!this.dummyStringFlag.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(3, this.dummyStringFlag);
            }
            if (this.uncommittedExperimentInfo != null && this.uncommittedExperimentInfo.length > 0) {
                for (ExperimentInfo element : this.uncommittedExperimentInfo) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(4, element);
                    }
                }
            }
            if (this.committedDummyBooleanFlag) {
                size += CodedOutputByteBufferNano.computeBoolSize(5, this.committedDummyBooleanFlag);
            }
            if (this.committedDummyIntegerFlag != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(6, this.committedDummyIntegerFlag);
            }
            if (!this.committedDummyStringFlag.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(7, this.committedDummyStringFlag);
            }
            if (this.commitSource != 0) {
                return size + CodedOutputByteBufferNano.computeInt32Size(8, this.commitSource);
            }
            return size;
        }

        public PhenotypeInfo mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_GET_LINK /*8*/:
                        this.dummyBooleanFlag = input.readBool();
                        continue;
                    case LogSource.GMS_CORE_PEOPLE /*16*/:
                        this.dummyIntegerFlag = input.readInt32();
                        continue;
                    case LogSource.ANDROID_CAMERA /*26*/:
                        this.dummyStringFlag = input.readString();
                        continue;
                    case LogSource.NOVA /*34*/:
                        int i;
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 34);
                        if (this.uncommittedExperimentInfo == null) {
                            i = 0;
                        } else {
                            i = this.uncommittedExperimentInfo.length;
                        }
                        ExperimentInfo[] newArray = new ExperimentInfo[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.uncommittedExperimentInfo, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new ExperimentInfo();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i++;
                        }
                        newArray[i] = new ExperimentInfo();
                        input.readMessage(newArray[i]);
                        this.uncommittedExperimentInfo = newArray;
                        continue;
                    case LogSource.COPRESENCE /*40*/:
                        this.committedDummyBooleanFlag = input.readBool();
                        continue;
                    case LogSource.BACKDROP /*48*/:
                        this.committedDummyIntegerFlag = input.readInt32();
                        continue;
                    case LogSource.SLIDES /*58*/:
                        this.committedDummyStringFlag = input.readString();
                        continue;
                    case LogSource.KIDS_COMMUNICATOR /*64*/:
                        int value = input.readInt32();
                        switch (value) {
                            case Action.UNKNOWN /*0*/:
                            case TimeSelection.Type.TEMPORARY /*1*/:
                            case TimeSelection.Type.INDEFINITELY /*2*/:
                                this.commitSource = value;
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

        public static PhenotypeInfo parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (PhenotypeInfo) MessageNano.mergeFrom(new PhenotypeInfo(), data);
        }

        public static PhenotypeInfo parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new PhenotypeInfo().mergeFrom(input);
        }
    }

    public static final class PhenotypeLogEvent extends ExtendableMessageNano<PhenotypeLogEvent> {
        private static volatile PhenotypeLogEvent[] _emptyArray;
        public ApplicationInfo applicationInfo;
        public ExperimentInfo[] experimentInfo;
        public PhenotypeInfo phenotypeInfo;

        public interface PhenotypeEventType {
            public static final int COMMIT_CONFIG = 5;
            public static final int EXPERIMENT_TOKEN = 13;
            public static final int GET_COMMITTED_CONFIGURATION = 12;
            public static final int GET_CONFIG_SNAPSHOT = 4;
            public static final int GET_DOGFOODS_TOKEN = 9;
            public static final int GET_EXP = 7;
            public static final int GET_EXP_FOR_LOGGING = 6;
            public static final int GET_FLAG = 11;
            public static final int PHENOTYPE_COMMIT = 14;
            public static final int PHENOTYPE_INFO = 8;
            public static final int REGULAR_REGISTER = 1;
            public static final int SET_DOGFOODS_TOKEN = 10;
            public static final int UNKNOWN = 0;
            public static final int UNREGISTER = 3;
            public static final int WEAK_REGISTER = 2;
        }

        public static PhenotypeLogEvent[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new PhenotypeLogEvent[0];
                    }
                }
            }
            return _emptyArray;
        }

        public PhenotypeLogEvent() {
            clear();
        }

        public PhenotypeLogEvent clear() {
            this.applicationInfo = null;
            this.phenotypeInfo = null;
            this.experimentInfo = ExperimentInfo.emptyArray();
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof PhenotypeLogEvent)) {
                return false;
            }
            PhenotypeLogEvent other = (PhenotypeLogEvent) o;
            if (this.applicationInfo == null) {
                if (other.applicationInfo != null) {
                    return false;
                }
            } else if (!this.applicationInfo.equals(other.applicationInfo)) {
                return false;
            }
            if (this.phenotypeInfo == null) {
                if (other.phenotypeInfo != null) {
                    return false;
                }
            } else if (!this.phenotypeInfo.equals(other.phenotypeInfo)) {
                return false;
            }
            if (!InternalNano.equals(this.experimentInfo, other.experimentInfo)) {
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
            int hashCode = (((((((getClass().getName().hashCode() + 527) * 31) + (this.applicationInfo == null ? 0 : this.applicationInfo.hashCode())) * 31) + (this.phenotypeInfo == null ? 0 : this.phenotypeInfo.hashCode())) * 31) + InternalNano.hashCode(this.experimentInfo)) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.applicationInfo != null) {
                output.writeMessage(1, this.applicationInfo);
            }
            if (this.phenotypeInfo != null) {
                output.writeMessage(2, this.phenotypeInfo);
            }
            if (this.experimentInfo != null && this.experimentInfo.length > 0) {
                for (ExperimentInfo element : this.experimentInfo) {
                    if (element != null) {
                        output.writeMessage(3, element);
                    }
                }
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.applicationInfo != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(1, this.applicationInfo);
            }
            if (this.phenotypeInfo != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(2, this.phenotypeInfo);
            }
            if (this.experimentInfo != null && this.experimentInfo.length > 0) {
                for (ExperimentInfo element : this.experimentInfo) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(3, element);
                    }
                }
            }
            return size;
        }

        public PhenotypeLogEvent mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        if (this.applicationInfo == null) {
                            this.applicationInfo = new ApplicationInfo();
                        }
                        input.readMessage(this.applicationInfo);
                        continue;
                    case LogSource.GOOGLE_ANALYTICS /*18*/:
                        if (this.phenotypeInfo == null) {
                            this.phenotypeInfo = new PhenotypeInfo();
                        }
                        input.readMessage(this.phenotypeInfo);
                        continue;
                    case LogSource.ANDROID_CAMERA /*26*/:
                        int i;
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 26);
                        if (this.experimentInfo == null) {
                            i = 0;
                        } else {
                            i = this.experimentInfo.length;
                        }
                        ExperimentInfo[] newArray = new ExperimentInfo[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.experimentInfo, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new ExperimentInfo();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i++;
                        }
                        newArray[i] = new ExperimentInfo();
                        input.readMessage(newArray[i]);
                        this.experimentInfo = newArray;
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

        public static PhenotypeLogEvent parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (PhenotypeLogEvent) MessageNano.mergeFrom(new PhenotypeLogEvent(), data);
        }

        public static PhenotypeLogEvent parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new PhenotypeLogEvent().mergeFrom(input);
        }
    }
}
