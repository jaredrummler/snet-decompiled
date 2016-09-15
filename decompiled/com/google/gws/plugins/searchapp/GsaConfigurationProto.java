package com.google.gws.plugins.searchapp;

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
import java.io.IOException;

public interface GsaConfigurationProto {

    public static final class GsaExperiments extends ExtendableMessageNano<GsaExperiments> {
        private static volatile GsaExperiments[] _emptyArray;
        public int[] clientExperimentIds;
        public long eventTimestamp;
        public KeyValuePair[] keyValuePair;

        public static GsaExperiments[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new GsaExperiments[0];
                    }
                }
            }
            return _emptyArray;
        }

        public GsaExperiments() {
            clear();
        }

        public GsaExperiments clear() {
            this.keyValuePair = KeyValuePair.emptyArray();
            this.eventTimestamp = 0;
            this.clientExperimentIds = WireFormatNano.EMPTY_INT_ARRAY;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof GsaExperiments)) {
                return false;
            }
            GsaExperiments other = (GsaExperiments) o;
            if (!InternalNano.equals(this.keyValuePair, other.keyValuePair) || this.eventTimestamp != other.eventTimestamp || !InternalNano.equals(this.clientExperimentIds, other.clientExperimentIds)) {
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
            int hashCode = (((((((getClass().getName().hashCode() + 527) * 31) + InternalNano.hashCode(this.keyValuePair)) * 31) + ((int) (this.eventTimestamp ^ (this.eventTimestamp >>> 32)))) * 31) + InternalNano.hashCode(this.clientExperimentIds)) * 31;
            int hashCode2 = (this.unknownFieldData == null || this.unknownFieldData.isEmpty()) ? 0 : this.unknownFieldData.hashCode();
            return hashCode + hashCode2;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.keyValuePair != null && this.keyValuePair.length > 0) {
                for (KeyValuePair element : this.keyValuePair) {
                    if (element != null) {
                        output.writeMessage(1, element);
                    }
                }
            }
            if (this.eventTimestamp != 0) {
                output.writeInt64(2, this.eventTimestamp);
            }
            if (this.clientExperimentIds != null && this.clientExperimentIds.length > 0) {
                for (int writeInt32 : this.clientExperimentIds) {
                    output.writeInt32(3, writeInt32);
                }
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.keyValuePair != null && this.keyValuePair.length > 0) {
                for (KeyValuePair element : this.keyValuePair) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(1, element);
                    }
                }
            }
            if (this.eventTimestamp != 0) {
                size += CodedOutputByteBufferNano.computeInt64Size(2, this.eventTimestamp);
            }
            if (this.clientExperimentIds == null || this.clientExperimentIds.length <= 0) {
                return size;
            }
            int dataSize = 0;
            for (int element2 : this.clientExperimentIds) {
                dataSize += CodedOutputByteBufferNano.computeInt32SizeNoTag(element2);
            }
            return (size + dataSize) + (this.clientExperimentIds.length * 1);
        }

        public GsaExperiments mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                int arrayLength;
                int i;
                int[] newArray;
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 10);
                        if (this.keyValuePair == null) {
                            i = 0;
                        } else {
                            i = this.keyValuePair.length;
                        }
                        KeyValuePair[] newArray2 = new KeyValuePair[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.keyValuePair, 0, newArray2, 0, i);
                        }
                        while (i < newArray2.length - 1) {
                            newArray2[i] = new KeyValuePair();
                            input.readMessage(newArray2[i]);
                            input.readTag();
                            i++;
                        }
                        newArray2[i] = new KeyValuePair();
                        input.readMessage(newArray2[i]);
                        this.keyValuePair = newArray2;
                        continue;
                    case LogSource.GMS_CORE_PEOPLE /*16*/:
                        this.eventTimestamp = input.readInt64();
                        continue;
                    case LogSource.LB_C /*24*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 24);
                        i = this.clientExperimentIds == null ? 0 : this.clientExperimentIds.length;
                        newArray = new int[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.clientExperimentIds, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = input.readInt32();
                            input.readTag();
                            i++;
                        }
                        newArray[i] = input.readInt32();
                        this.clientExperimentIds = newArray;
                        continue;
                    case LogSource.ANDROID_CAMERA /*26*/:
                        int limit = input.pushLimit(input.readRawVarint32());
                        arrayLength = 0;
                        int startPos = input.getPosition();
                        while (input.getBytesUntilLimit() > 0) {
                            input.readInt32();
                            arrayLength++;
                        }
                        input.rewindToPosition(startPos);
                        i = this.clientExperimentIds == null ? 0 : this.clientExperimentIds.length;
                        newArray = new int[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.clientExperimentIds, 0, newArray, 0, i);
                        }
                        while (i < newArray.length) {
                            newArray[i] = input.readInt32();
                            i++;
                        }
                        this.clientExperimentIds = newArray;
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

        public static GsaExperiments parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (GsaExperiments) MessageNano.mergeFrom(new GsaExperiments(), data);
        }

        public static GsaExperiments parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new GsaExperiments().mergeFrom(input);
        }
    }

    public static final class KeyValuePair extends ExtendableMessageNano<KeyValuePair> {
        private static volatile KeyValuePair[] _emptyArray;
        public boolean boolValue;
        public int id;
        public int intValue;
        public String key;
        public String stringValue;

        public static KeyValuePair[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new KeyValuePair[0];
                    }
                }
            }
            return _emptyArray;
        }

        public KeyValuePair() {
            clear();
        }

        public KeyValuePair clear() {
            this.key = BuildConfig.VERSION_NAME;
            this.id = 0;
            this.boolValue = false;
            this.stringValue = BuildConfig.VERSION_NAME;
            this.intValue = 0;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof KeyValuePair)) {
                return false;
            }
            KeyValuePair other = (KeyValuePair) o;
            if (this.key == null) {
                if (other.key != null) {
                    return false;
                }
            } else if (!this.key.equals(other.key)) {
                return false;
            }
            if (this.id != other.id || this.boolValue != other.boolValue) {
                return false;
            }
            if (this.stringValue == null) {
                if (other.stringValue != null) {
                    return false;
                }
            } else if (!this.stringValue.equals(other.stringValue)) {
                return false;
            }
            if (this.intValue != other.intValue) {
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
            int hashCode = (((((((((((getClass().getName().hashCode() + 527) * 31) + (this.key == null ? 0 : this.key.hashCode())) * 31) + this.id) * 31) + (this.boolValue ? 1231 : 1237)) * 31) + (this.stringValue == null ? 0 : this.stringValue.hashCode())) * 31) + this.intValue) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (!this.key.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(1, this.key);
            }
            if (this.boolValue) {
                output.writeBool(2, this.boolValue);
            }
            if (!this.stringValue.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(3, this.stringValue);
            }
            if (this.intValue != 0) {
                output.writeInt32(4, this.intValue);
            }
            if (this.id != 0) {
                output.writeInt32(5, this.id);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (!this.key.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.key);
            }
            if (this.boolValue) {
                size += CodedOutputByteBufferNano.computeBoolSize(2, this.boolValue);
            }
            if (!this.stringValue.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(3, this.stringValue);
            }
            if (this.intValue != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(4, this.intValue);
            }
            if (this.id != 0) {
                return size + CodedOutputByteBufferNano.computeInt32Size(5, this.id);
            }
            return size;
        }

        public KeyValuePair mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        this.key = input.readString();
                        continue;
                    case LogSource.GMS_CORE_PEOPLE /*16*/:
                        this.boolValue = input.readBool();
                        continue;
                    case LogSource.ANDROID_CAMERA /*26*/:
                        this.stringValue = input.readString();
                        continue;
                    case LogSource.SOCIAL /*32*/:
                        this.intValue = input.readInt32();
                        continue;
                    case LogSource.COPRESENCE /*40*/:
                        this.id = input.readInt32();
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

        public static KeyValuePair parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (KeyValuePair) MessageNano.mergeFrom(new KeyValuePair(), data);
        }

        public static KeyValuePair parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new KeyValuePair().mergeFrom(input);
        }
    }
}
