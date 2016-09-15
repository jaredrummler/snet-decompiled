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

public final class BatchDataMutateResponse extends ExtendableMessageNano<BatchDataMutateResponse> {
    private static volatile BatchDataMutateResponse[] _emptyArray;
    public BatchDataResponseHeader header;
    public MutateDataResponseWithError mutateDataResponseWithError;

    public static BatchDataMutateResponse[] emptyArray() {
        if (_emptyArray == null) {
            synchronized (InternalNano.LAZY_INIT_LOCK) {
                if (_emptyArray == null) {
                    _emptyArray = new BatchDataMutateResponse[0];
                }
            }
        }
        return _emptyArray;
    }

    public BatchDataMutateResponse() {
        clear();
    }

    public BatchDataMutateResponse clear() {
        this.mutateDataResponseWithError = null;
        this.header = null;
        this.unknownFieldData = null;
        this.cachedSize = -1;
        return this;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof BatchDataMutateResponse)) {
            return false;
        }
        BatchDataMutateResponse other = (BatchDataMutateResponse) o;
        if (this.mutateDataResponseWithError == null) {
            if (other.mutateDataResponseWithError != null) {
                return false;
            }
        } else if (!this.mutateDataResponseWithError.equals(other.mutateDataResponseWithError)) {
            return false;
        }
        if (this.header == null) {
            if (other.header != null) {
                return false;
            }
        } else if (!this.header.equals(other.header)) {
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
        int hashCode = (((((getClass().getName().hashCode() + 527) * 31) + (this.mutateDataResponseWithError == null ? 0 : this.mutateDataResponseWithError.hashCode())) * 31) + (this.header == null ? 0 : this.header.hashCode())) * 31;
        if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
            i = this.unknownFieldData.hashCode();
        }
        return hashCode + i;
    }

    public void writeTo(CodedOutputByteBufferNano output) throws IOException {
        if (this.mutateDataResponseWithError != null) {
            output.writeMessage(1, this.mutateDataResponseWithError);
        }
        if (this.header != null) {
            output.writeMessage(2, this.header);
        }
        super.writeTo(output);
    }

    protected int computeSerializedSize() {
        int size = super.computeSerializedSize();
        if (this.mutateDataResponseWithError != null) {
            size += CodedOutputByteBufferNano.computeMessageSize(1, this.mutateDataResponseWithError);
        }
        if (this.header != null) {
            return size + CodedOutputByteBufferNano.computeMessageSize(2, this.header);
        }
        return size;
    }

    public BatchDataMutateResponse mergeFrom(CodedInputByteBufferNano input) throws IOException {
        while (true) {
            int tag = input.readTag();
            switch (tag) {
                case Action.UNKNOWN /*0*/:
                    break;
                case Type.TAP_ABOUT /*10*/:
                    if (this.mutateDataResponseWithError == null) {
                        this.mutateDataResponseWithError = new MutateDataResponseWithError();
                    }
                    input.readMessage(this.mutateDataResponseWithError);
                    continue;
                case LogSource.GOOGLE_ANALYTICS /*18*/:
                    if (this.header == null) {
                        this.header = new BatchDataResponseHeader();
                    }
                    input.readMessage(this.header);
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

    public static BatchDataMutateResponse parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
        return (BatchDataMutateResponse) MessageNano.mergeFrom(new BatchDataMutateResponse(), data);
    }

    public static BatchDataMutateResponse parseFrom(CodedInputByteBufferNano input) throws IOException {
        return new BatchDataMutateResponse().mergeFrom(input);
    }
}
