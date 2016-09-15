package proto;

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
import java.util.Arrays;

public final class TypedMessage extends ExtendableMessageNano<TypedMessage> {
    private static volatile TypedMessage[] _emptyArray;
    public byte[] message;
    public int typeId;

    public static TypedMessage[] emptyArray() {
        if (_emptyArray == null) {
            synchronized (InternalNano.LAZY_INIT_LOCK) {
                if (_emptyArray == null) {
                    _emptyArray = new TypedMessage[0];
                }
            }
        }
        return _emptyArray;
    }

    public TypedMessage() {
        clear();
    }

    public TypedMessage clear() {
        this.typeId = 0;
        this.message = WireFormatNano.EMPTY_BYTES;
        this.unknownFieldData = null;
        this.cachedSize = -1;
        return this;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof TypedMessage)) {
            return false;
        }
        TypedMessage other = (TypedMessage) o;
        if (this.typeId != other.typeId || !Arrays.equals(this.message, other.message)) {
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
        int hashCode = (((((getClass().getName().hashCode() + 527) * 31) + this.typeId) * 31) + Arrays.hashCode(this.message)) * 31;
        int hashCode2 = (this.unknownFieldData == null || this.unknownFieldData.isEmpty()) ? 0 : this.unknownFieldData.hashCode();
        return hashCode + hashCode2;
    }

    public void writeTo(CodedOutputByteBufferNano output) throws IOException {
        if (this.typeId != 0) {
            output.writeInt32(1, this.typeId);
        }
        output.writeBytes(8, this.message);
        super.writeTo(output);
    }

    protected int computeSerializedSize() {
        int size = super.computeSerializedSize();
        if (this.typeId != 0) {
            size += CodedOutputByteBufferNano.computeInt32Size(1, this.typeId);
        }
        return size + CodedOutputByteBufferNano.computeBytesSize(8, this.message);
    }

    public TypedMessage mergeFrom(CodedInputByteBufferNano input) throws IOException {
        while (true) {
            int tag = input.readTag();
            switch (tag) {
                case Action.UNKNOWN /*0*/:
                    break;
                case Type.TAP_GET_LINK /*8*/:
                    this.typeId = input.readInt32();
                    continue;
                case LogSource.WIFI_ASSISTANT /*66*/:
                    this.message = input.readBytes();
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

    public static TypedMessage parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
        return (TypedMessage) MessageNano.mergeFrom(new TypedMessage(), data);
    }

    public static TypedMessage parseFrom(CodedInputByteBufferNano input) throws IOException {
        return new TypedMessage().mergeFrom(input);
    }
}
