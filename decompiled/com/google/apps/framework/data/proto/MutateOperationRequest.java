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

public final class MutateOperationRequest extends ExtendableMessageNano<MutateOperationRequest> {
    private static volatile MutateOperationRequest[] _emptyArray;
    public MutateOperation[] mutate;

    public static MutateOperationRequest[] emptyArray() {
        if (_emptyArray == null) {
            synchronized (InternalNano.LAZY_INIT_LOCK) {
                if (_emptyArray == null) {
                    _emptyArray = new MutateOperationRequest[0];
                }
            }
        }
        return _emptyArray;
    }

    public MutateOperationRequest() {
        clear();
    }

    public MutateOperationRequest clear() {
        this.mutate = MutateOperation.emptyArray();
        this.unknownFieldData = null;
        this.cachedSize = -1;
        return this;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof MutateOperationRequest)) {
            return false;
        }
        MutateOperationRequest other = (MutateOperationRequest) o;
        if (!InternalNano.equals(this.mutate, other.mutate)) {
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
        int hashCode = (((getClass().getName().hashCode() + 527) * 31) + InternalNano.hashCode(this.mutate)) * 31;
        int hashCode2 = (this.unknownFieldData == null || this.unknownFieldData.isEmpty()) ? 0 : this.unknownFieldData.hashCode();
        return hashCode + hashCode2;
    }

    public void writeTo(CodedOutputByteBufferNano output) throws IOException {
        if (this.mutate != null && this.mutate.length > 0) {
            for (MutateOperation element : this.mutate) {
                if (element != null) {
                    output.writeMessage(1, element);
                }
            }
        }
        super.writeTo(output);
    }

    protected int computeSerializedSize() {
        int size = super.computeSerializedSize();
        if (this.mutate != null && this.mutate.length > 0) {
            for (MutateOperation element : this.mutate) {
                if (element != null) {
                    size += CodedOutputByteBufferNano.computeMessageSize(1, element);
                }
            }
        }
        return size;
    }

    public MutateOperationRequest mergeFrom(CodedInputByteBufferNano input) throws IOException {
        while (true) {
            int tag = input.readTag();
            switch (tag) {
                case Action.UNKNOWN /*0*/:
                    break;
                case Type.TAP_ABOUT /*10*/:
                    int i;
                    int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 10);
                    if (this.mutate == null) {
                        i = 0;
                    } else {
                        i = this.mutate.length;
                    }
                    MutateOperation[] newArray = new MutateOperation[(i + arrayLength)];
                    if (i != 0) {
                        System.arraycopy(this.mutate, 0, newArray, 0, i);
                    }
                    while (i < newArray.length - 1) {
                        newArray[i] = new MutateOperation();
                        input.readMessage(newArray[i]);
                        input.readTag();
                        i++;
                    }
                    newArray[i] = new MutateOperation();
                    input.readMessage(newArray[i]);
                    this.mutate = newArray;
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

    public static MutateOperationRequest parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
        return (MutateOperationRequest) MessageNano.mergeFrom(new MutateOperationRequest(), data);
    }

    public static MutateOperationRequest parseFrom(CodedInputByteBufferNano input) throws IOException {
        return new MutateOperationRequest().mergeFrom(input);
    }
}
