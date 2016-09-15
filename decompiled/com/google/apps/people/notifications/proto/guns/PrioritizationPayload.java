package com.google.apps.people.notifications.proto.guns;

import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.ExtendableMessageNano;
import com.google.protobuf.nano.InternalNano;
import com.google.protobuf.nano.InvalidProtocolBufferNanoException;
import com.google.protobuf.nano.MessageNano;
import com.google.wireless.android.play.playlog.proto.UserLocation.LaunchSettingsEvent.Action;
import java.io.IOException;

public final class PrioritizationPayload extends ExtendableMessageNano<PrioritizationPayload> {
    private static volatile PrioritizationPayload[] _emptyArray;

    public static PrioritizationPayload[] emptyArray() {
        if (_emptyArray == null) {
            synchronized (InternalNano.LAZY_INIT_LOCK) {
                if (_emptyArray == null) {
                    _emptyArray = new PrioritizationPayload[0];
                }
            }
        }
        return _emptyArray;
    }

    public PrioritizationPayload() {
        clear();
    }

    public PrioritizationPayload clear() {
        this.unknownFieldData = null;
        this.cachedSize = -1;
        return this;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof PrioritizationPayload)) {
            return false;
        }
        PrioritizationPayload other = (PrioritizationPayload) o;
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

    public PrioritizationPayload mergeFrom(CodedInputByteBufferNano input) throws IOException {
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

    public static PrioritizationPayload parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
        return (PrioritizationPayload) MessageNano.mergeFrom(new PrioritizationPayload(), data);
    }

    public static PrioritizationPayload parseFrom(CodedInputByteBufferNano input) throws IOException {
        return new PrioritizationPayload().mergeFrom(input);
    }
}
