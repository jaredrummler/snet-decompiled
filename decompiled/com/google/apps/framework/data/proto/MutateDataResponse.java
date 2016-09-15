package com.google.apps.framework.data.proto;

import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.ExtendableMessageNano;
import com.google.protobuf.nano.InternalNano;
import com.google.protobuf.nano.InvalidProtocolBufferNanoException;
import com.google.protobuf.nano.MessageNano;
import com.google.wireless.android.play.playlog.proto.UserLocation.LaunchSettingsEvent.Action;
import java.io.IOException;

public final class MutateDataResponse extends ExtendableMessageNano<MutateDataResponse> {
    private static volatile MutateDataResponse[] _emptyArray;

    public static MutateDataResponse[] emptyArray() {
        if (_emptyArray == null) {
            synchronized (InternalNano.LAZY_INIT_LOCK) {
                if (_emptyArray == null) {
                    _emptyArray = new MutateDataResponse[0];
                }
            }
        }
        return _emptyArray;
    }

    public MutateDataResponse() {
        clear();
    }

    public MutateDataResponse clear() {
        this.unknownFieldData = null;
        this.cachedSize = -1;
        return this;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof MutateDataResponse)) {
            return false;
        }
        MutateDataResponse other = (MutateDataResponse) o;
        if (this.unknownFieldData != null && !this.unknownFieldData.isEmpty()) {
            return this.unknownFieldData.equals(other.unknownFieldData);
        }
        if (other.unknownFieldData == null || other.unknownFieldData.isEmpty()) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int hashCode = (getClass().getName().hashCode() + 527) * 31;
        int hashCode2 = (this.unknownFieldData == null || this.unknownFieldData.isEmpty()) ? 0 : this.unknownFieldData.hashCode();
        return hashCode + hashCode2;
    }

    public MutateDataResponse mergeFrom(CodedInputByteBufferNano input) throws IOException {
        int tag;
        do {
            tag = input.readTag();
            switch (tag) {
                case Action.UNKNOWN /*0*/:
                    break;
                default:
                    break;
            }
            return this;
        } while (storeUnknownField(input, tag));
        return this;
    }

    public static MutateDataResponse parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
        return (MutateDataResponse) MessageNano.mergeFrom(new MutateDataResponse(), data);
    }

    public static MutateDataResponse parseFrom(CodedInputByteBufferNano input) throws IOException {
        return new MutateDataResponse().mergeFrom(input);
    }
}
