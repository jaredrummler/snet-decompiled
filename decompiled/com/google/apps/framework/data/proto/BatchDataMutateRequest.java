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

public final class BatchDataMutateRequest extends ExtendableMessageNano<BatchDataMutateRequest> {
    private static volatile BatchDataMutateRequest[] _emptyArray;
    public BatchDataRequestHeader header;
    public MutateOperation mutateOperation;

    public static BatchDataMutateRequest[] emptyArray() {
        if (_emptyArray == null) {
            synchronized (InternalNano.LAZY_INIT_LOCK) {
                if (_emptyArray == null) {
                    _emptyArray = new BatchDataMutateRequest[0];
                }
            }
        }
        return _emptyArray;
    }

    public BatchDataMutateRequest() {
        clear();
    }

    public BatchDataMutateRequest clear() {
        this.header = null;
        this.mutateOperation = null;
        this.unknownFieldData = null;
        this.cachedSize = -1;
        return this;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof BatchDataMutateRequest)) {
            return false;
        }
        BatchDataMutateRequest other = (BatchDataMutateRequest) o;
        if (this.header == null) {
            if (other.header != null) {
                return false;
            }
        } else if (!this.header.equals(other.header)) {
            return false;
        }
        if (this.mutateOperation == null) {
            if (other.mutateOperation != null) {
                return false;
            }
        } else if (!this.mutateOperation.equals(other.mutateOperation)) {
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
        int hashCode = (((((getClass().getName().hashCode() + 527) * 31) + (this.header == null ? 0 : this.header.hashCode())) * 31) + (this.mutateOperation == null ? 0 : this.mutateOperation.hashCode())) * 31;
        if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
            i = this.unknownFieldData.hashCode();
        }
        return hashCode + i;
    }

    public void writeTo(CodedOutputByteBufferNano output) throws IOException {
        if (this.header != null) {
            output.writeMessage(1, this.header);
        }
        if (this.mutateOperation != null) {
            output.writeMessage(2, this.mutateOperation);
        }
        super.writeTo(output);
    }

    protected int computeSerializedSize() {
        int size = super.computeSerializedSize();
        if (this.header != null) {
            size += CodedOutputByteBufferNano.computeMessageSize(1, this.header);
        }
        if (this.mutateOperation != null) {
            return size + CodedOutputByteBufferNano.computeMessageSize(2, this.mutateOperation);
        }
        return size;
    }

    public BatchDataMutateRequest mergeFrom(CodedInputByteBufferNano input) throws IOException {
        while (true) {
            int tag = input.readTag();
            switch (tag) {
                case Action.UNKNOWN /*0*/:
                    break;
                case Type.TAP_ABOUT /*10*/:
                    if (this.header == null) {
                        this.header = new BatchDataRequestHeader();
                    }
                    input.readMessage(this.header);
                    continue;
                case LogSource.GOOGLE_ANALYTICS /*18*/:
                    if (this.mutateOperation == null) {
                        this.mutateOperation = new MutateOperation();
                    }
                    input.readMessage(this.mutateOperation);
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

    public static BatchDataMutateRequest parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
        return (BatchDataMutateRequest) MessageNano.mergeFrom(new BatchDataMutateRequest(), data);
    }

    public static BatchDataMutateRequest parseFrom(CodedInputByteBufferNano input) throws IOException {
        return new BatchDataMutateRequest().mergeFrom(input);
    }
}
