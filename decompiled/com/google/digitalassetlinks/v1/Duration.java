package com.google.digitalassetlinks.v1;

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

public final class Duration extends ExtendableMessageNano<Duration> {
    private static volatile Duration[] _emptyArray;
    public int nanos;
    public long seconds;

    public static Duration[] emptyArray() {
        if (_emptyArray == null) {
            synchronized (InternalNano.LAZY_INIT_LOCK) {
                if (_emptyArray == null) {
                    _emptyArray = new Duration[0];
                }
            }
        }
        return _emptyArray;
    }

    public Duration() {
        clear();
    }

    public Duration clear() {
        this.seconds = 0;
        this.nanos = 0;
        this.unknownFieldData = null;
        this.cachedSize = -1;
        return this;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Duration)) {
            return false;
        }
        Duration other = (Duration) o;
        if (this.seconds != other.seconds || this.nanos != other.nanos) {
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
        int hashCode = (((((getClass().getName().hashCode() + 527) * 31) + ((int) (this.seconds ^ (this.seconds >>> 32)))) * 31) + this.nanos) * 31;
        int hashCode2 = (this.unknownFieldData == null || this.unknownFieldData.isEmpty()) ? 0 : this.unknownFieldData.hashCode();
        return hashCode + hashCode2;
    }

    public void writeTo(CodedOutputByteBufferNano output) throws IOException {
        if (this.seconds != 0) {
            output.writeInt64(1, this.seconds);
        }
        if (this.nanos != 0) {
            output.writeInt32(2, this.nanos);
        }
        super.writeTo(output);
    }

    protected int computeSerializedSize() {
        int size = super.computeSerializedSize();
        if (this.seconds != 0) {
            size += CodedOutputByteBufferNano.computeInt64Size(1, this.seconds);
        }
        if (this.nanos != 0) {
            return size + CodedOutputByteBufferNano.computeInt32Size(2, this.nanos);
        }
        return size;
    }

    public Duration mergeFrom(CodedInputByteBufferNano input) throws IOException {
        while (true) {
            int tag = input.readTag();
            switch (tag) {
                case Action.UNKNOWN /*0*/:
                    break;
                case Type.TAP_GET_LINK /*8*/:
                    this.seconds = input.readInt64();
                    continue;
                case LogSource.GMS_CORE_PEOPLE /*16*/:
                    this.nanos = input.readInt32();
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

    public static Duration parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
        return (Duration) MessageNano.mergeFrom(new Duration(), data);
    }

    public static Duration parseFrom(CodedInputByteBufferNano input) throws IOException {
        return new Duration().mergeFrom(input);
    }
}
