package com.google.apps.framework.data.proto;

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

public final class BatchDataFetchRequest extends ExtendableMessageNano<BatchDataFetchRequest> {
    private static volatile BatchDataFetchRequest[] _emptyArray;
    public DataFetch[] dataFetch;
    public BatchDataRequestHeader header;

    public static BatchDataFetchRequest[] emptyArray() {
        if (_emptyArray == null) {
            synchronized (InternalNano.LAZY_INIT_LOCK) {
                if (_emptyArray == null) {
                    _emptyArray = new BatchDataFetchRequest[0];
                }
            }
        }
        return _emptyArray;
    }

    public BatchDataFetchRequest() {
        clear();
    }

    public BatchDataFetchRequest clear() {
        this.header = null;
        this.dataFetch = DataFetch.emptyArray();
        this.unknownFieldData = null;
        this.cachedSize = -1;
        return this;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof BatchDataFetchRequest)) {
            return false;
        }
        BatchDataFetchRequest other = (BatchDataFetchRequest) o;
        if (this.header == null) {
            if (other.header != null) {
                return false;
            }
        } else if (!this.header.equals(other.header)) {
            return false;
        }
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
        int i = 0;
        int hashCode = (((((getClass().getName().hashCode() + 527) * 31) + (this.header == null ? 0 : this.header.hashCode())) * 31) + InternalNano.hashCode(this.dataFetch)) * 31;
        if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
            i = this.unknownFieldData.hashCode();
        }
        return hashCode + i;
    }

    public void writeTo(CodedOutputByteBufferNano output) throws IOException {
        if (this.header != null) {
            output.writeMessage(1, this.header);
        }
        if (this.dataFetch != null && this.dataFetch.length > 0) {
            for (DataFetch element : this.dataFetch) {
                if (element != null) {
                    output.writeMessage(2, element);
                }
            }
        }
        super.writeTo(output);
    }

    protected int computeSerializedSize() {
        int size = super.computeSerializedSize();
        if (this.header != null) {
            size += CodedOutputByteBufferNano.computeMessageSize(1, this.header);
        }
        if (this.dataFetch != null && this.dataFetch.length > 0) {
            for (DataFetch element : this.dataFetch) {
                if (element != null) {
                    size += CodedOutputByteBufferNano.computeMessageSize(2, element);
                }
            }
        }
        return size;
    }

    public BatchDataFetchRequest mergeFrom(CodedInputByteBufferNano input) throws IOException {
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
                    int i;
                    int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 18);
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

    public static BatchDataFetchRequest parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
        return (BatchDataFetchRequest) MessageNano.mergeFrom(new BatchDataFetchRequest(), data);
    }

    public static BatchDataFetchRequest parseFrom(CodedInputByteBufferNano input) throws IOException {
        return new BatchDataFetchRequest().mergeFrom(input);
    }
}
