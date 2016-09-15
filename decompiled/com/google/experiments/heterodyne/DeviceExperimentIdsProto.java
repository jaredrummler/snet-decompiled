package com.google.experiments.heterodyne;

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

public interface DeviceExperimentIdsProto {

    public static final class DeviceExperimentIds extends ExtendableMessageNano<DeviceExperimentIds> {
        private static volatile DeviceExperimentIds[] _emptyArray;
        public int[] always;
        public int[] byGaia;
        public int[] byOther;
        public int[] byPseudonymous;

        public static DeviceExperimentIds[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new DeviceExperimentIds[0];
                    }
                }
            }
            return _emptyArray;
        }

        public DeviceExperimentIds() {
            clear();
        }

        public DeviceExperimentIds clear() {
            this.byGaia = WireFormatNano.EMPTY_INT_ARRAY;
            this.byPseudonymous = WireFormatNano.EMPTY_INT_ARRAY;
            this.always = WireFormatNano.EMPTY_INT_ARRAY;
            this.byOther = WireFormatNano.EMPTY_INT_ARRAY;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof DeviceExperimentIds)) {
                return false;
            }
            DeviceExperimentIds other = (DeviceExperimentIds) o;
            if (!InternalNano.equals(this.byGaia, other.byGaia) || !InternalNano.equals(this.byPseudonymous, other.byPseudonymous) || !InternalNano.equals(this.always, other.always) || !InternalNano.equals(this.byOther, other.byOther)) {
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
            int hashCode = (((((((((getClass().getName().hashCode() + 527) * 31) + InternalNano.hashCode(this.byGaia)) * 31) + InternalNano.hashCode(this.byPseudonymous)) * 31) + InternalNano.hashCode(this.always)) * 31) + InternalNano.hashCode(this.byOther)) * 31;
            int hashCode2 = (this.unknownFieldData == null || this.unknownFieldData.isEmpty()) ? 0 : this.unknownFieldData.hashCode();
            return hashCode + hashCode2;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.byGaia != null && this.byGaia.length > 0) {
                for (int writeInt32 : this.byGaia) {
                    output.writeInt32(1, writeInt32);
                }
            }
            if (this.byPseudonymous != null && this.byPseudonymous.length > 0) {
                for (int writeInt322 : this.byPseudonymous) {
                    output.writeInt32(2, writeInt322);
                }
            }
            if (this.always != null && this.always.length > 0) {
                for (int writeInt3222 : this.always) {
                    output.writeInt32(3, writeInt3222);
                }
            }
            if (this.byOther != null && this.byOther.length > 0) {
                for (int writeInt32222 : this.byOther) {
                    output.writeInt32(4, writeInt32222);
                }
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int dataSize;
            int size = super.computeSerializedSize();
            if (this.byGaia != null && this.byGaia.length > 0) {
                dataSize = 0;
                for (int element : this.byGaia) {
                    dataSize += CodedOutputByteBufferNano.computeInt32SizeNoTag(element);
                }
                size = (size + dataSize) + (this.byGaia.length * 1);
            }
            if (this.byPseudonymous != null && this.byPseudonymous.length > 0) {
                dataSize = 0;
                for (int element2 : this.byPseudonymous) {
                    dataSize += CodedOutputByteBufferNano.computeInt32SizeNoTag(element2);
                }
                size = (size + dataSize) + (this.byPseudonymous.length * 1);
            }
            if (this.always != null && this.always.length > 0) {
                dataSize = 0;
                for (int element22 : this.always) {
                    dataSize += CodedOutputByteBufferNano.computeInt32SizeNoTag(element22);
                }
                size = (size + dataSize) + (this.always.length * 1);
            }
            if (this.byOther == null || this.byOther.length <= 0) {
                return size;
            }
            dataSize = 0;
            for (int element222 : this.byOther) {
                dataSize += CodedOutputByteBufferNano.computeInt32SizeNoTag(element222);
            }
            return (size + dataSize) + (this.byOther.length * 1);
        }

        public DeviceExperimentIds mergeFrom(CodedInputByteBufferNano input) throws IOException {
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
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 8);
                        i = this.byGaia == null ? 0 : this.byGaia.length;
                        newArray = new int[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.byGaia, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = input.readInt32();
                            input.readTag();
                            i++;
                        }
                        newArray[i] = input.readInt32();
                        this.byGaia = newArray;
                        continue;
                    case Type.TAP_ABOUT /*10*/:
                        limit = input.pushLimit(input.readRawVarint32());
                        arrayLength = 0;
                        startPos = input.getPosition();
                        while (input.getBytesUntilLimit() > 0) {
                            input.readInt32();
                            arrayLength++;
                        }
                        input.rewindToPosition(startPos);
                        i = this.byGaia == null ? 0 : this.byGaia.length;
                        newArray = new int[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.byGaia, 0, newArray, 0, i);
                        }
                        while (i < newArray.length) {
                            newArray[i] = input.readInt32();
                            i++;
                        }
                        this.byGaia = newArray;
                        input.popLimit(limit);
                        continue;
                    case LogSource.GMS_CORE_PEOPLE /*16*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 16);
                        i = this.byPseudonymous == null ? 0 : this.byPseudonymous.length;
                        newArray = new int[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.byPseudonymous, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = input.readInt32();
                            input.readTag();
                            i++;
                        }
                        newArray[i] = input.readInt32();
                        this.byPseudonymous = newArray;
                        continue;
                    case LogSource.GOOGLE_ANALYTICS /*18*/:
                        limit = input.pushLimit(input.readRawVarint32());
                        arrayLength = 0;
                        startPos = input.getPosition();
                        while (input.getBytesUntilLimit() > 0) {
                            input.readInt32();
                            arrayLength++;
                        }
                        input.rewindToPosition(startPos);
                        i = this.byPseudonymous == null ? 0 : this.byPseudonymous.length;
                        newArray = new int[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.byPseudonymous, 0, newArray, 0, i);
                        }
                        while (i < newArray.length) {
                            newArray[i] = input.readInt32();
                            i++;
                        }
                        this.byPseudonymous = newArray;
                        input.popLimit(limit);
                        continue;
                    case LogSource.LB_C /*24*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 24);
                        i = this.always == null ? 0 : this.always.length;
                        newArray = new int[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.always, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = input.readInt32();
                            input.readTag();
                            i++;
                        }
                        newArray[i] = input.readInt32();
                        this.always = newArray;
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
                        i = this.always == null ? 0 : this.always.length;
                        newArray = new int[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.always, 0, newArray, 0, i);
                        }
                        while (i < newArray.length) {
                            newArray[i] = input.readInt32();
                            i++;
                        }
                        this.always = newArray;
                        input.popLimit(limit);
                        continue;
                    case LogSource.SOCIAL /*32*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 32);
                        i = this.byOther == null ? 0 : this.byOther.length;
                        newArray = new int[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.byOther, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = input.readInt32();
                            input.readTag();
                            i++;
                        }
                        newArray[i] = input.readInt32();
                        this.byOther = newArray;
                        continue;
                    case LogSource.NOVA /*34*/:
                        limit = input.pushLimit(input.readRawVarint32());
                        arrayLength = 0;
                        startPos = input.getPosition();
                        while (input.getBytesUntilLimit() > 0) {
                            input.readInt32();
                            arrayLength++;
                        }
                        input.rewindToPosition(startPos);
                        i = this.byOther == null ? 0 : this.byOther.length;
                        newArray = new int[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.byOther, 0, newArray, 0, i);
                        }
                        while (i < newArray.length) {
                            newArray[i] = input.readInt32();
                            i++;
                        }
                        this.byOther = newArray;
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

        public static DeviceExperimentIds parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (DeviceExperimentIds) MessageNano.mergeFrom(new DeviceExperimentIds(), data);
        }

        public static DeviceExperimentIds parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new DeviceExperimentIds().mergeFrom(input);
        }
    }
}
