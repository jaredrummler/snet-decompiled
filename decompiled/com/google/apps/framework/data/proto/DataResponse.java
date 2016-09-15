package com.google.apps.framework.data.proto;

import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.ExtendableMessageNano;
import com.google.protobuf.nano.InternalNano;
import com.google.protobuf.nano.InvalidProtocolBufferNanoException;
import com.google.protobuf.nano.MessageNano;
import com.google.wireless.android.play.playlog.proto.UserLocation.LaunchSettingsEvent.Action;
import com.google.wireless.android.play.playlog.proto.UserLocation.LocationSharingEvent.Type;
import java.io.IOException;

public final class DataResponse extends ExtendableMessageNano<DataResponse> {
    private static volatile DataResponse[] _emptyArray;
    public int serial;

    public static DataResponse[] emptyArray() {
        if (_emptyArray == null) {
            synchronized (InternalNano.LAZY_INIT_LOCK) {
                if (_emptyArray == null) {
                    _emptyArray = new DataResponse[0];
                }
            }
        }
        return _emptyArray;
    }

    public DataResponse() {
        clear();
    }

    public DataResponse clear() {
        this.serial = 0;
        this.unknownFieldData = null;
        this.cachedSize = -1;
        return this;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof DataResponse)) {
            return false;
        }
        DataResponse other = (DataResponse) o;
        if (this.serial != other.serial) {
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
        int hashCode = (((getClass().getName().hashCode() + 527) * 31) + this.serial) * 31;
        int hashCode2 = (this.unknownFieldData == null || this.unknownFieldData.isEmpty()) ? 0 : this.unknownFieldData.hashCode();
        return hashCode + hashCode2;
    }

    public void writeTo(CodedOutputByteBufferNano output) throws IOException {
        if (this.serial != 0) {
            output.writeInt32(1, this.serial);
        }
        super.writeTo(output);
    }

    protected int computeSerializedSize() {
        int size = super.computeSerializedSize();
        if (this.serial != 0) {
            return size + CodedOutputByteBufferNano.computeInt32Size(1, this.serial);
        }
        return size;
    }

    public DataResponse mergeFrom(CodedInputByteBufferNano input) throws IOException {
        while (true) {
            int tag = input.readTag();
            switch (tag) {
                case Action.UNKNOWN /*0*/:
                    break;
                case Type.TAP_GET_LINK /*8*/:
                    this.serial = input.readInt32();
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

    public static DataResponse parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
        return (DataResponse) MessageNano.mergeFrom(new DataResponse(), data);
    }

    public static DataResponse parseFrom(CodedInputByteBufferNano input) throws IOException {
        return new DataResponse().mergeFrom(input);
    }
}
