package com.google.wireless.android.auth.d2d;

import com.google.gaia.frontend.proto.ExchangeCheckpointRequest;
import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.ExtendableMessageNano;
import com.google.protobuf.nano.InternalNano;
import com.google.protobuf.nano.InvalidProtocolBufferNanoException;
import com.google.protobuf.nano.MessageNano;
import com.google.wireless.android.play.playlog.proto.UserLocation.LaunchSettingsEvent.Action;
import com.google.wireless.android.play.playlog.proto.UserLocation.LocationSharingEvent.Type;
import java.io.IOException;

public final class ExchangeSessionCheckpointsRequest extends ExtendableMessageNano<ExchangeSessionCheckpointsRequest> {
    private static volatile ExchangeSessionCheckpointsRequest[] _emptyArray;
    public ExchangeCheckpointRequest request;

    public static ExchangeSessionCheckpointsRequest[] emptyArray() {
        if (_emptyArray == null) {
            synchronized (InternalNano.LAZY_INIT_LOCK) {
                if (_emptyArray == null) {
                    _emptyArray = new ExchangeSessionCheckpointsRequest[0];
                }
            }
        }
        return _emptyArray;
    }

    public ExchangeSessionCheckpointsRequest() {
        clear();
    }

    public ExchangeSessionCheckpointsRequest clear() {
        this.request = null;
        this.unknownFieldData = null;
        this.cachedSize = -1;
        return this;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ExchangeSessionCheckpointsRequest)) {
            return false;
        }
        ExchangeSessionCheckpointsRequest other = (ExchangeSessionCheckpointsRequest) o;
        if (this.request == null) {
            if (other.request != null) {
                return false;
            }
        } else if (!this.request.equals(other.request)) {
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
        int hashCode = (((getClass().getName().hashCode() + 527) * 31) + (this.request == null ? 0 : this.request.hashCode())) * 31;
        if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
            i = this.unknownFieldData.hashCode();
        }
        return hashCode + i;
    }

    public void writeTo(CodedOutputByteBufferNano output) throws IOException {
        if (this.request != null) {
            output.writeMessage(1, this.request);
        }
        super.writeTo(output);
    }

    protected int computeSerializedSize() {
        int size = super.computeSerializedSize();
        if (this.request != null) {
            return size + CodedOutputByteBufferNano.computeMessageSize(1, this.request);
        }
        return size;
    }

    public ExchangeSessionCheckpointsRequest mergeFrom(CodedInputByteBufferNano input) throws IOException {
        while (true) {
            int tag = input.readTag();
            switch (tag) {
                case Action.UNKNOWN /*0*/:
                    break;
                case Type.TAP_ABOUT /*10*/:
                    if (this.request == null) {
                        this.request = new ExchangeCheckpointRequest();
                    }
                    input.readMessage(this.request);
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

    public static ExchangeSessionCheckpointsRequest parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
        return (ExchangeSessionCheckpointsRequest) MessageNano.mergeFrom(new ExchangeSessionCheckpointsRequest(), data);
    }

    public static ExchangeSessionCheckpointsRequest parseFrom(CodedInputByteBufferNano input) throws IOException {
        return new ExchangeSessionCheckpointsRequest().mergeFrom(input);
    }
}
