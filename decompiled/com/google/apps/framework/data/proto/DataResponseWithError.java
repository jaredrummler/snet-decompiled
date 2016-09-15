package com.google.apps.framework.data.proto;

import com.google.net.util.StatusProto;
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

public final class DataResponseWithError extends ExtendableMessageNano<DataResponseWithError> {
    private static volatile DataResponseWithError[] _emptyArray;
    public DataResponse dataResponse;
    public StatusProto errorStatus;

    public static DataResponseWithError[] emptyArray() {
        if (_emptyArray == null) {
            synchronized (InternalNano.LAZY_INIT_LOCK) {
                if (_emptyArray == null) {
                    _emptyArray = new DataResponseWithError[0];
                }
            }
        }
        return _emptyArray;
    }

    public DataResponseWithError() {
        clear();
    }

    public DataResponseWithError clear() {
        this.dataResponse = null;
        this.errorStatus = null;
        this.unknownFieldData = null;
        this.cachedSize = -1;
        return this;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof DataResponseWithError)) {
            return false;
        }
        DataResponseWithError other = (DataResponseWithError) o;
        if (this.dataResponse == null) {
            if (other.dataResponse != null) {
                return false;
            }
        } else if (!this.dataResponse.equals(other.dataResponse)) {
            return false;
        }
        if (this.errorStatus == null) {
            if (other.errorStatus != null) {
                return false;
            }
        } else if (!this.errorStatus.equals(other.errorStatus)) {
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
        int hashCode = (((((getClass().getName().hashCode() + 527) * 31) + (this.dataResponse == null ? 0 : this.dataResponse.hashCode())) * 31) + (this.errorStatus == null ? 0 : this.errorStatus.hashCode())) * 31;
        if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
            i = this.unknownFieldData.hashCode();
        }
        return hashCode + i;
    }

    public void writeTo(CodedOutputByteBufferNano output) throws IOException {
        if (this.dataResponse != null) {
            output.writeMessage(1, this.dataResponse);
        }
        if (this.errorStatus != null) {
            output.writeMessage(2, this.errorStatus);
        }
        super.writeTo(output);
    }

    protected int computeSerializedSize() {
        int size = super.computeSerializedSize();
        if (this.dataResponse != null) {
            size += CodedOutputByteBufferNano.computeMessageSize(1, this.dataResponse);
        }
        if (this.errorStatus != null) {
            return size + CodedOutputByteBufferNano.computeMessageSize(2, this.errorStatus);
        }
        return size;
    }

    public DataResponseWithError mergeFrom(CodedInputByteBufferNano input) throws IOException {
        while (true) {
            int tag = input.readTag();
            switch (tag) {
                case Action.UNKNOWN /*0*/:
                    break;
                case Type.TAP_ABOUT /*10*/:
                    if (this.dataResponse == null) {
                        this.dataResponse = new DataResponse();
                    }
                    input.readMessage(this.dataResponse);
                    continue;
                case LogSource.GOOGLE_ANALYTICS /*18*/:
                    if (this.errorStatus == null) {
                        this.errorStatus = new StatusProto();
                    }
                    input.readMessage(this.errorStatus);
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

    public static DataResponseWithError parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
        return (DataResponseWithError) MessageNano.mergeFrom(new DataResponseWithError(), data);
    }

    public static DataResponseWithError parseFrom(CodedInputByteBufferNano input) throws IOException {
        return new DataResponseWithError().mergeFrom(input);
    }
}
