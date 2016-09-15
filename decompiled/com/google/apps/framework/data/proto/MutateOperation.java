package com.google.apps.framework.data.proto;

import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.ExtendableMessageNano;
import com.google.protobuf.nano.InternalNano;
import com.google.protobuf.nano.InvalidProtocolBufferNanoException;
import com.google.protobuf.nano.MessageNano;
import com.google.wireless.android.play.playlog.proto.ClientAnalytics.LogRequest.LogSource;
import com.google.wireless.android.play.playlog.proto.UserLocation.LaunchSettingsEvent.Action;
import com.google.wireless.android.play.playlog.proto.UserLocation.LocationSharingEvent.Type;
import java.io.IOException;

public final class MutateOperation extends ExtendableMessageNano<MutateOperation> {
    private static volatile MutateOperation[] _emptyArray;
    public int id;
    public MutateDataRequest mutateRequest;

    public static MutateOperation[] emptyArray() {
        if (_emptyArray == null) {
            synchronized (InternalNano.LAZY_INIT_LOCK) {
                if (_emptyArray == null) {
                    _emptyArray = new MutateOperation[0];
                }
            }
        }
        return _emptyArray;
    }

    public MutateOperation() {
        clear();
    }

    public MutateOperation clear() {
        this.id = 0;
        this.mutateRequest = null;
        this.unknownFieldData = null;
        this.cachedSize = -1;
        return this;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof MutateOperation)) {
            return false;
        }
        MutateOperation other = (MutateOperation) o;
        if (this.id != other.id) {
            return false;
        }
        if (this.mutateRequest == null) {
            if (other.mutateRequest != null) {
                return false;
            }
        } else if (!this.mutateRequest.equals(other.mutateRequest)) {
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
        int hashCode = (((((getClass().getName().hashCode() + 527) * 31) + this.id) * 31) + (this.mutateRequest == null ? 0 : this.mutateRequest.hashCode())) * 31;
        if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
            i = this.unknownFieldData.hashCode();
        }
        return hashCode + i;
    }

    public void writeTo(CodedOutputByteBufferNano output) throws IOException {
        if (this.id != 0) {
            output.writeInt32(1, this.id);
        }
        if (this.mutateRequest != null) {
            output.writeMessage(2, this.mutateRequest);
        }
        super.writeTo(output);
    }

    protected int computeSerializedSize() {
        int size = super.computeSerializedSize();
        if (this.id != 0) {
            size += CodedOutputByteBufferNano.computeInt32Size(1, this.id);
        }
        if (this.mutateRequest != null) {
            return size + CodedOutputByteBufferNano.computeMessageSize(2, this.mutateRequest);
        }
        return size;
    }

    public MutateOperation mergeFrom(CodedInputByteBufferNano input) throws IOException {
        while (true) {
            int tag = input.readTag();
            switch (tag) {
                case Action.UNKNOWN /*0*/:
                    break;
                case Type.TAP_GET_LINK /*8*/:
                    this.id = input.readInt32();
                    continue;
                case LogSource.GOOGLE_ANALYTICS /*18*/:
                    if (this.mutateRequest == null) {
                        this.mutateRequest = new MutateDataRequest();
                    }
                    input.readMessage(this.mutateRequest);
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

    public static MutateOperation parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
        return (MutateOperation) MessageNano.mergeFrom(new MutateOperation(), data);
    }

    public static MutateOperation parseFrom(CodedInputByteBufferNano input) throws IOException {
        return new MutateOperation().mergeFrom(input);
    }
}
