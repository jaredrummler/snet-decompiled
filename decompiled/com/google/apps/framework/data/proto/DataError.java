package com.google.apps.framework.data.proto;

import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.ExtendableMessageNano;
import com.google.protobuf.nano.Extension;
import com.google.protobuf.nano.InvalidProtocolBufferNanoException;
import com.google.protobuf.nano.MessageNano;
import com.google.wireless.android.play.playlog.proto.UserLocation.LaunchSettingsEvent.Action;
import java.io.IOException;
import proto2.bridge.MessageSet;

public final class DataError extends ExtendableMessageNano<DataError> {
    private static final DataError[] EMPTY_ARRAY;
    public static final Extension<MessageSet, DataError> messageSetExtension;

    static {
        messageSetExtension = Extension.createMessageTyped(11, DataError.class, 796867074);
        EMPTY_ARRAY = new DataError[0];
    }

    public static DataError[] emptyArray() {
        return EMPTY_ARRAY;
    }

    public DataError() {
        clear();
    }

    public DataError clear() {
        this.unknownFieldData = null;
        this.cachedSize = -1;
        return this;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof DataError)) {
            return false;
        }
        DataError other = (DataError) o;
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

    public DataError mergeFrom(CodedInputByteBufferNano input) throws IOException {
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

    public static DataError parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
        return (DataError) MessageNano.mergeFrom(new DataError(), data);
    }

    public static DataError parseFrom(CodedInputByteBufferNano input) throws IOException {
        return new DataError().mergeFrom(input);
    }
}
