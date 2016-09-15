package com.google.apps.framework.data.proto;

import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.ExtendableMessageNano;
import com.google.protobuf.nano.InternalNano;
import com.google.protobuf.nano.InvalidProtocolBufferNanoException;
import com.google.protobuf.nano.MessageNano;
import com.google.protobuf.nano.WireFormatNano;
import com.google.wireless.android.play.playlog.proto.UserLocation.LaunchSettingsEvent.Action;
import com.google.wireless.android.play.playlog.proto.UserLocation.LocationSharingEvent.Type;
import java.io.IOException;

public final class BatchDataRequest extends ExtendableMessageNano<BatchDataRequest> {
    private static volatile BatchDataRequest[] _emptyArray;
    public DataFetch[] dataFetch;

    public static BatchDataRequest[] emptyArray() {
        if (_emptyArray == null) {
            synchronized (InternalNano.LAZY_INIT_LOCK) {
                if (_emptyArray == null) {
                    _emptyArray = new BatchDataRequest[0];
                }
            }
        }
        return _emptyArray;
    }

    public BatchDataRequest() {
        clear();
    }

    public BatchDataRequest clear() {
        this.dataFetch = DataFetch.emptyArray();
        this.unknownFieldData = null;
        this.cachedSize = -1;
        return this;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof BatchDataRequest)) {
            return false;
        }
        BatchDataRequest other = (BatchDataRequest) o;
        if (!InternalNano.equals(this.dataFetch, other.dataFetch)) {
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
        int hashCode = (((getClass().getName().hashCode() + 527) * 31) + InternalNano.hashCode(this.dataFetch)) * 31;
        int hashCode2 = (this.unknownFieldData == null || this.unknownFieldData.isEmpty()) ? 0 : this.unknownFieldData.hashCode();
        return hashCode + hashCode2;
    }

    public void writeTo(CodedOutputByteBufferNano output) throws IOException {
        if (this.dataFetch != null && this.dataFetch.length > 0) {
            for (DataFetch element : this.dataFetch) {
                if (element != null) {
                    output.writeMessage(1, element);
                }
            }
        }
        super.writeTo(output);
    }

    protected int computeSerializedSize() {
        int size = super.computeSerializedSize();
        if (this.dataFetch != null && this.dataFetch.length > 0) {
            for (DataFetch element : this.dataFetch) {
                if (element != null) {
                    size += CodedOutputByteBufferNano.computeMessageSize(1, element);
                }
            }
        }
        return size;
    }

    public BatchDataRequest mergeFrom(CodedInputByteBufferNano input) throws IOException {
        while (true) {
            int tag = input.readTag();
            switch (tag) {
                case Action.UNKNOWN /*0*/:
                    break;
                case Type.TAP_ABOUT /*10*/:
                    int i;
                    int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 10);
                    if (this.dataFetch == null) {
                        i = 0;
                    } else {
                        i = this.dataFetch.length;
                    }
                    DataFetch[] newArray = new DataFetch[(i + arrayLength)];
                    if (i != 0) {
                        System.arraycopy(this.dataFetch, 0, newArray, 0, i);
                    }
                    while (i < newArray.length - 1) {
                        newArray[i] = new DataFetch();
                        input.readMessage(newArray[i]);
                        input.readTag();
                        i++;
                    }
                    newArray[i] = new DataFetch();
                    input.readMessage(newArray[i]);
                    this.dataFetch = newArray;
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

    public static BatchDataRequest parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
        return (BatchDataRequest) MessageNano.mergeFrom(new BatchDataRequest(), data);
    }

    public static BatchDataRequest parseFrom(CodedInputByteBufferNano input) throws IOException {
        return new BatchDataRequest().mergeFrom(input);
    }
}
