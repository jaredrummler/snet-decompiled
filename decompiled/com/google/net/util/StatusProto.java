package com.google.net.util;

import com.google.android.gms.lint.BuildConfig;
import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.ExtendableMessageNano;
import com.google.protobuf.nano.Extension;
import com.google.protobuf.nano.InvalidProtocolBufferNanoException;
import com.google.protobuf.nano.MessageNano;
import com.google.wireless.android.play.playlog.proto.ClientAnalytics.LogRequest.LogSource;
import com.google.wireless.android.play.playlog.proto.UserLocation.LaunchSettingsEvent.Action;
import com.google.wireless.android.play.playlog.proto.UserLocation.LocationSharingEvent.Type;
import java.io.IOException;
import proto.TypedMessage;
import proto2.bridge.MessageSet;

public final class StatusProto extends ExtendableMessageNano<StatusProto> {
    private static final StatusProto[] EMPTY_ARRAY;
    public static final Extension<MessageSet, StatusProto> messageSetExtension;
    public int canonicalCode;
    public int code;
    public String message;
    public MessageSet messageSet;
    public TypedMessage payload;
    public String space;

    static {
        messageSetExtension = Extension.createMessageTyped(11, StatusProto.class, 80570);
        EMPTY_ARRAY = new StatusProto[0];
    }

    public static StatusProto[] emptyArray() {
        return EMPTY_ARRAY;
    }

    public StatusProto() {
        clear();
    }

    public StatusProto clear() {
        this.code = 0;
        this.space = BuildConfig.VERSION_NAME;
        this.message = BuildConfig.VERSION_NAME;
        this.canonicalCode = 0;
        this.payload = null;
        this.messageSet = null;
        this.unknownFieldData = null;
        this.cachedSize = -1;
        return this;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof StatusProto)) {
            return false;
        }
        StatusProto other = (StatusProto) o;
        if (this.code != other.code) {
            return false;
        }
        if (this.space == null) {
            if (other.space != null) {
                return false;
            }
        } else if (!this.space.equals(other.space)) {
            return false;
        }
        if (this.message == null) {
            if (other.message != null) {
                return false;
            }
        } else if (!this.message.equals(other.message)) {
            return false;
        }
        if (this.canonicalCode != other.canonicalCode) {
            return false;
        }
        if (this.payload == null) {
            if (other.payload != null) {
                return false;
            }
        } else if (!this.payload.equals(other.payload)) {
            return false;
        }
        if (this.messageSet == null) {
            if (other.messageSet != null) {
                return false;
            }
        } else if (!this.messageSet.equals(other.messageSet)) {
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
        int hashCode = (((((((((((((getClass().getName().hashCode() + 527) * 31) + this.code) * 31) + (this.space == null ? 0 : this.space.hashCode())) * 31) + (this.message == null ? 0 : this.message.hashCode())) * 31) + this.canonicalCode) * 31) + (this.payload == null ? 0 : this.payload.hashCode())) * 31) + (this.messageSet == null ? 0 : this.messageSet.hashCode())) * 31;
        if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
            i = this.unknownFieldData.hashCode();
        }
        return hashCode + i;
    }

    public void writeTo(CodedOutputByteBufferNano output) throws IOException {
        if (this.code != 0) {
            output.writeInt32(1, this.code);
        }
        if (!this.space.equals(BuildConfig.VERSION_NAME)) {
            output.writeString(2, this.space);
        }
        if (!this.message.equals(BuildConfig.VERSION_NAME)) {
            output.writeString(3, this.message);
        }
        if (this.payload != null) {
            output.writeMessage(4, this.payload);
        }
        if (this.messageSet != null) {
            output.writeMessage(5, this.messageSet);
        }
        if (this.canonicalCode != 0) {
            output.writeInt32(6, this.canonicalCode);
        }
        super.writeTo(output);
    }

    protected int computeSerializedSize() {
        int size = super.computeSerializedSize();
        if (this.code != 0) {
            size += CodedOutputByteBufferNano.computeInt32Size(1, this.code);
        }
        if (!this.space.equals(BuildConfig.VERSION_NAME)) {
            size += CodedOutputByteBufferNano.computeStringSize(2, this.space);
        }
        if (!this.message.equals(BuildConfig.VERSION_NAME)) {
            size += CodedOutputByteBufferNano.computeStringSize(3, this.message);
        }
        if (this.payload != null) {
            size += CodedOutputByteBufferNano.computeMessageSize(4, this.payload);
        }
        if (this.messageSet != null) {
            size += CodedOutputByteBufferNano.computeMessageSize(5, this.messageSet);
        }
        if (this.canonicalCode != 0) {
            return size + CodedOutputByteBufferNano.computeInt32Size(6, this.canonicalCode);
        }
        return size;
    }

    public StatusProto mergeFrom(CodedInputByteBufferNano input) throws IOException {
        while (true) {
            int tag = input.readTag();
            switch (tag) {
                case Action.UNKNOWN /*0*/:
                    break;
                case Type.TAP_GET_LINK /*8*/:
                    this.code = input.readInt32();
                    continue;
                case LogSource.GOOGLE_ANALYTICS /*18*/:
                    this.space = input.readString();
                    continue;
                case LogSource.ANDROID_CAMERA /*26*/:
                    this.message = input.readString();
                    continue;
                case LogSource.NOVA /*34*/:
                    if (this.payload == null) {
                        this.payload = new TypedMessage();
                    }
                    input.readMessage(this.payload);
                    continue;
                case LogSource.PHOTOS /*42*/:
                    if (this.messageSet == null) {
                        this.messageSet = new MessageSet();
                    }
                    input.readMessage(this.messageSet);
                    continue;
                case LogSource.BACKDROP /*48*/:
                    this.canonicalCode = input.readInt32();
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

    public static StatusProto parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
        return (StatusProto) MessageNano.mergeFrom(new StatusProto(), data);
    }

    public static StatusProto parseFrom(CodedInputByteBufferNano input) throws IOException {
        return new StatusProto().mergeFrom(input);
    }
}
