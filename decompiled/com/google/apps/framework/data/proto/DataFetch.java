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

public final class DataFetch extends ExtendableMessageNano<DataFetch> {
    private static volatile DataFetch[] _emptyArray;
    public int id;
    public DataRequest request;
    public int serial;

    public static DataFetch[] emptyArray() {
        if (_emptyArray == null) {
            synchronized (InternalNano.LAZY_INIT_LOCK) {
                if (_emptyArray == null) {
                    _emptyArray = new DataFetch[0];
                }
            }
        }
        return _emptyArray;
    }

    public DataFetch() {
        clear();
    }

    public DataFetch clear() {
        this.id = 0;
        this.request = null;
        this.serial = 0;
        this.unknownFieldData = null;
        this.cachedSize = -1;
        return this;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof DataFetch)) {
            return false;
        }
        DataFetch other = (DataFetch) o;
        if (this.id != other.id) {
            return false;
        }
        if (this.request == null) {
            if (other.request != null) {
                return false;
            }
        } else if (!this.request.equals(other.request)) {
            return false;
        }
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
        int i = 0;
        int hashCode = (((((((getClass().getName().hashCode() + 527) * 31) + this.id) * 31) + (this.request == null ? 0 : this.request.hashCode())) * 31) + this.serial) * 31;
        if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
            i = this.unknownFieldData.hashCode();
        }
        return hashCode + i;
    }

    public void writeTo(CodedOutputByteBufferNano output) throws IOException {
        if (this.id != 0) {
            output.writeInt32(1, this.id);
        }
        if (this.request != null) {
            output.writeMessage(2, this.request);
        }
        if (this.serial != 0) {
            output.writeInt32(5, this.serial);
        }
        super.writeTo(output);
    }

    protected int computeSerializedSize() {
        int size = super.computeSerializedSize();
        if (this.id != 0) {
            size += CodedOutputByteBufferNano.computeInt32Size(1, this.id);
        }
        if (this.request != null) {
            size += CodedOutputByteBufferNano.computeMessageSize(2, this.request);
        }
        if (this.serial != 0) {
            return size + CodedOutputByteBufferNano.computeInt32Size(5, this.serial);
        }
        return size;
    }

    public DataFetch mergeFrom(CodedInputByteBufferNano input) throws IOException {
        while (true) {
            int tag = input.readTag();
            switch (tag) {
                case Action.UNKNOWN /*0*/:
                    break;
                case Type.TAP_GET_LINK /*8*/:
                    this.id = input.readInt32();
                    continue;
                case LogSource.GOOGLE_ANALYTICS /*18*/:
                    if (this.request == null) {
                        this.request = new DataRequest();
                    }
                    input.readMessage(this.request);
                    continue;
                case LogSource.COPRESENCE /*40*/:
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

    public static DataFetch parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
        return (DataFetch) MessageNano.mergeFrom(new DataFetch(), data);
    }

    public static DataFetch parseFrom(CodedInputByteBufferNano input) throws IOException {
        return new DataFetch().mergeFrom(input);
    }
}
