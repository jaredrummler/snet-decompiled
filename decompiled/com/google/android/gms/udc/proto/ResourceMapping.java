package com.google.android.gms.udc.proto;

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

public final class ResourceMapping extends ExtendableMessageNano<ResourceMapping> {
    private static volatile ResourceMapping[] _emptyArray;
    public boolean optionalFromClient;
    public boolean optionalFromPsb;
    public int resourceId;
    public int[] targetSettingIds;

    public static ResourceMapping[] emptyArray() {
        if (_emptyArray == null) {
            synchronized (InternalNano.LAZY_INIT_LOCK) {
                if (_emptyArray == null) {
                    _emptyArray = new ResourceMapping[0];
                }
            }
        }
        return _emptyArray;
    }

    public ResourceMapping() {
        clear();
    }

    public ResourceMapping clear() {
        this.resourceId = 0;
        this.targetSettingIds = WireFormatNano.EMPTY_INT_ARRAY;
        this.optionalFromPsb = false;
        this.optionalFromClient = false;
        this.unknownFieldData = null;
        this.cachedSize = -1;
        return this;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ResourceMapping)) {
            return false;
        }
        ResourceMapping other = (ResourceMapping) o;
        if (this.resourceId != other.resourceId || !InternalNano.equals(this.targetSettingIds, other.targetSettingIds) || this.optionalFromPsb != other.optionalFromPsb || this.optionalFromClient != other.optionalFromClient) {
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
        int hashCode = (((((((getClass().getName().hashCode() + 527) * 31) + this.resourceId) * 31) + InternalNano.hashCode(this.targetSettingIds)) * 31) + (this.optionalFromPsb ? 1231 : 1237)) * 31;
        if (!this.optionalFromClient) {
            i = 1237;
        }
        i = (hashCode + i) * 31;
        hashCode = (this.unknownFieldData == null || this.unknownFieldData.isEmpty()) ? 0 : this.unknownFieldData.hashCode();
        return i + hashCode;
    }

    public void writeTo(CodedOutputByteBufferNano output) throws IOException {
        if (this.resourceId != 0) {
            output.writeInt32(1, this.resourceId);
        }
        if (this.targetSettingIds != null && this.targetSettingIds.length > 0) {
            for (int writeInt32 : this.targetSettingIds) {
                output.writeInt32(2, writeInt32);
            }
        }
        if (this.optionalFromPsb) {
            output.writeBool(3, this.optionalFromPsb);
        }
        if (this.optionalFromClient) {
            output.writeBool(4, this.optionalFromClient);
        }
        super.writeTo(output);
    }

    protected int computeSerializedSize() {
        int size = super.computeSerializedSize();
        if (this.resourceId != 0) {
            size += CodedOutputByteBufferNano.computeInt32Size(1, this.resourceId);
        }
        if (this.targetSettingIds != null && this.targetSettingIds.length > 0) {
            int dataSize = 0;
            for (int element : this.targetSettingIds) {
                dataSize += CodedOutputByteBufferNano.computeInt32SizeNoTag(element);
            }
            size = (size + dataSize) + (this.targetSettingIds.length * 1);
        }
        if (this.optionalFromPsb) {
            size += CodedOutputByteBufferNano.computeBoolSize(3, this.optionalFromPsb);
        }
        if (this.optionalFromClient) {
            return size + CodedOutputByteBufferNano.computeBoolSize(4, this.optionalFromClient);
        }
        return size;
    }

    public ResourceMapping mergeFrom(CodedInputByteBufferNano input) throws IOException {
        while (true) {
            int tag = input.readTag();
            int arrayLength;
            int i;
            int[] newArray;
            switch (tag) {
                case Action.UNKNOWN /*0*/:
                    break;
                case Type.TAP_GET_LINK /*8*/:
                    this.resourceId = input.readInt32();
                    continue;
                case LogSource.GMS_CORE_PEOPLE /*16*/:
                    arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 16);
                    i = this.targetSettingIds == null ? 0 : this.targetSettingIds.length;
                    newArray = new int[(i + arrayLength)];
                    if (i != 0) {
                        System.arraycopy(this.targetSettingIds, 0, newArray, 0, i);
                    }
                    while (i < newArray.length - 1) {
                        newArray[i] = input.readInt32();
                        input.readTag();
                        i++;
                    }
                    newArray[i] = input.readInt32();
                    this.targetSettingIds = newArray;
                    continue;
                case LogSource.GOOGLE_ANALYTICS /*18*/:
                    int limit = input.pushLimit(input.readRawVarint32());
                    arrayLength = 0;
                    int startPos = input.getPosition();
                    while (input.getBytesUntilLimit() > 0) {
                        input.readInt32();
                        arrayLength++;
                    }
                    input.rewindToPosition(startPos);
                    i = this.targetSettingIds == null ? 0 : this.targetSettingIds.length;
                    newArray = new int[(i + arrayLength)];
                    if (i != 0) {
                        System.arraycopy(this.targetSettingIds, 0, newArray, 0, i);
                    }
                    while (i < newArray.length) {
                        newArray[i] = input.readInt32();
                        i++;
                    }
                    this.targetSettingIds = newArray;
                    input.popLimit(limit);
                    continue;
                case LogSource.LB_C /*24*/:
                    this.optionalFromPsb = input.readBool();
                    continue;
                case LogSource.SOCIAL /*32*/:
                    this.optionalFromClient = input.readBool();
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

    public static ResourceMapping parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
        return (ResourceMapping) MessageNano.mergeFrom(new ResourceMapping(), data);
    }

    public static ResourceMapping parseFrom(CodedInputByteBufferNano input) throws IOException {
        return new ResourceMapping().mergeFrom(input);
    }
}
