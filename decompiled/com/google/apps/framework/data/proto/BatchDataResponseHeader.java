package com.google.apps.framework.data.proto;

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

public final class BatchDataResponseHeader extends ExtendableMessageNano<BatchDataResponseHeader> {
    private static volatile BatchDataResponseHeader[] _emptyArray;
    public ApiaryConsistencyTokenResponseHeader consistencyHeader;

    public static BatchDataResponseHeader[] emptyArray() {
        if (_emptyArray == null) {
            synchronized (InternalNano.LAZY_INIT_LOCK) {
                if (_emptyArray == null) {
                    _emptyArray = new BatchDataResponseHeader[0];
                }
            }
        }
        return _emptyArray;
    }

    public BatchDataResponseHeader() {
        clear();
    }

    public BatchDataResponseHeader clear() {
        this.consistencyHeader = null;
        this.unknownFieldData = null;
        this.cachedSize = -1;
        return this;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof BatchDataResponseHeader)) {
            return false;
        }
        BatchDataResponseHeader other = (BatchDataResponseHeader) o;
        if (this.consistencyHeader == null) {
            if (other.consistencyHeader != null) {
                return false;
            }
        } else if (!this.consistencyHeader.equals(other.consistencyHeader)) {
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
        int hashCode = (((getClass().getName().hashCode() + 527) * 31) + (this.consistencyHeader == null ? 0 : this.consistencyHeader.hashCode())) * 31;
        if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
            i = this.unknownFieldData.hashCode();
        }
        return hashCode + i;
    }

    public void writeTo(CodedOutputByteBufferNano output) throws IOException {
        if (this.consistencyHeader != null) {
            output.writeMessage(1, this.consistencyHeader);
        }
        super.writeTo(output);
    }

    protected int computeSerializedSize() {
        int size = super.computeSerializedSize();
        if (this.consistencyHeader != null) {
            return size + CodedOutputByteBufferNano.computeMessageSize(1, this.consistencyHeader);
        }
        return size;
    }

    public BatchDataResponseHeader mergeFrom(CodedInputByteBufferNano input) throws IOException {
        while (true) {
            int tag = input.readTag();
            switch (tag) {
                case Action.UNKNOWN /*0*/:
                    break;
                case Type.TAP_ABOUT /*10*/:
                    if (this.consistencyHeader == null) {
                        this.consistencyHeader = new ApiaryConsistencyTokenResponseHeader();
                    }
                    input.readMessage(this.consistencyHeader);
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

    public static BatchDataResponseHeader parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
        return (BatchDataResponseHeader) MessageNano.mergeFrom(new BatchDataResponseHeader(), data);
    }

    public static BatchDataResponseHeader parseFrom(CodedInputByteBufferNano input) throws IOException {
        return new BatchDataResponseHeader().mergeFrom(input);
    }
}
