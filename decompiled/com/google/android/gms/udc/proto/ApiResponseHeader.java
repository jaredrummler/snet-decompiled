package com.google.android.gms.udc.proto;

import com.google.apps.framework.consistency.proto.ApiaryConsistencyTokenResponseHeader;
import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.ExtendableMessageNano;
import com.google.protobuf.nano.InternalNano;
import com.google.protobuf.nano.InvalidProtocolBufferNanoException;
import com.google.protobuf.nano.MessageNano;
import com.google.wireless.android.play.playlog.proto.UserLocation.LaunchSettingsEvent.Action;
import com.google.wireless.android.play.playlog.proto.UserLocation.LocationSharingEvent.Type;
import java.io.IOException;

public final class ApiResponseHeader extends ExtendableMessageNano<ApiResponseHeader> {
    private static volatile ApiResponseHeader[] _emptyArray;
    public ApiaryConsistencyTokenResponseHeader consistency;

    public static ApiResponseHeader[] emptyArray() {
        if (_emptyArray == null) {
            synchronized (InternalNano.LAZY_INIT_LOCK) {
                if (_emptyArray == null) {
                    _emptyArray = new ApiResponseHeader[0];
                }
            }
        }
        return _emptyArray;
    }

    public ApiResponseHeader() {
        clear();
    }

    public ApiResponseHeader clear() {
        this.consistency = null;
        this.unknownFieldData = null;
        this.cachedSize = -1;
        return this;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ApiResponseHeader)) {
            return false;
        }
        ApiResponseHeader other = (ApiResponseHeader) o;
        if (this.consistency == null) {
            if (other.consistency != null) {
                return false;
            }
        } else if (!this.consistency.equals(other.consistency)) {
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
        int hashCode = (((getClass().getName().hashCode() + 527) * 31) + (this.consistency == null ? 0 : this.consistency.hashCode())) * 31;
        if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
            i = this.unknownFieldData.hashCode();
        }
        return hashCode + i;
    }

    public void writeTo(CodedOutputByteBufferNano output) throws IOException {
        if (this.consistency != null) {
            output.writeMessage(1, this.consistency);
        }
        super.writeTo(output);
    }

    protected int computeSerializedSize() {
        int size = super.computeSerializedSize();
        if (this.consistency != null) {
            return size + CodedOutputByteBufferNano.computeMessageSize(1, this.consistency);
        }
        return size;
    }

    public ApiResponseHeader mergeFrom(CodedInputByteBufferNano input) throws IOException {
        while (true) {
            int tag = input.readTag();
            switch (tag) {
                case Action.UNKNOWN /*0*/:
                    break;
                case Type.TAP_ABOUT /*10*/:
                    if (this.consistency == null) {
                        this.consistency = new ApiaryConsistencyTokenResponseHeader();
                    }
                    input.readMessage(this.consistency);
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

    public static ApiResponseHeader parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
        return (ApiResponseHeader) MessageNano.mergeFrom(new ApiResponseHeader(), data);
    }

    public static ApiResponseHeader parseFrom(CodedInputByteBufferNano input) throws IOException {
        return new ApiResponseHeader().mergeFrom(input);
    }
}
