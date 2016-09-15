package com.google.android.gms.udc.proto;

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

public final class UiFingerprint extends ExtendableMessageNano<UiFingerprint> {
    private static volatile UiFingerprint[] _emptyArray;
    public UserEnvironment originalUserEnvironment;
    public byte[][] shownResourceKeys;

    public static UiFingerprint[] emptyArray() {
        if (_emptyArray == null) {
            synchronized (InternalNano.LAZY_INIT_LOCK) {
                if (_emptyArray == null) {
                    _emptyArray = new UiFingerprint[0];
                }
            }
        }
        return _emptyArray;
    }

    public UiFingerprint() {
        clear();
    }

    public UiFingerprint clear() {
        this.shownResourceKeys = WireFormatNano.EMPTY_BYTES_ARRAY;
        this.originalUserEnvironment = null;
        this.unknownFieldData = null;
        this.cachedSize = -1;
        return this;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof UiFingerprint)) {
            return false;
        }
        UiFingerprint other = (UiFingerprint) o;
        if (!InternalNano.equals(this.shownResourceKeys, other.shownResourceKeys)) {
            return false;
        }
        if (this.originalUserEnvironment == null) {
            if (other.originalUserEnvironment != null) {
                return false;
            }
        } else if (!this.originalUserEnvironment.equals(other.originalUserEnvironment)) {
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
        int hashCode = (((((getClass().getName().hashCode() + 527) * 31) + InternalNano.hashCode(this.shownResourceKeys)) * 31) + (this.originalUserEnvironment == null ? 0 : this.originalUserEnvironment.hashCode())) * 31;
        if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
            i = this.unknownFieldData.hashCode();
        }
        return hashCode + i;
    }

    public void writeTo(CodedOutputByteBufferNano output) throws IOException {
        if (this.shownResourceKeys != null && this.shownResourceKeys.length > 0) {
            for (byte[] element : this.shownResourceKeys) {
                if (element != null) {
                    output.writeBytes(1, element);
                }
            }
        }
        if (this.originalUserEnvironment != null) {
            output.writeMessage(2, this.originalUserEnvironment);
        }
        super.writeTo(output);
    }

    protected int computeSerializedSize() {
        int size = super.computeSerializedSize();
        if (this.shownResourceKeys != null && this.shownResourceKeys.length > 0) {
            int dataCount = 0;
            int dataSize = 0;
            for (byte[] element : this.shownResourceKeys) {
                if (element != null) {
                    dataCount++;
                    dataSize += CodedOutputByteBufferNano.computeBytesSizeNoTag(element);
                }
            }
            size = (size + dataSize) + (dataCount * 1);
        }
        if (this.originalUserEnvironment != null) {
            return size + CodedOutputByteBufferNano.computeMessageSize(2, this.originalUserEnvironment);
        }
        return size;
    }

    public UiFingerprint mergeFrom(CodedInputByteBufferNano input) throws IOException {
        while (true) {
            int tag = input.readTag();
            switch (tag) {
                case Action.UNKNOWN /*0*/:
                    break;
                case Type.TAP_ABOUT /*10*/:
                    int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 10);
                    int i = this.shownResourceKeys == null ? 0 : this.shownResourceKeys.length;
                    byte[][] newArray = new byte[(i + arrayLength)][];
                    if (i != 0) {
                        System.arraycopy(this.shownResourceKeys, 0, newArray, 0, i);
                    }
                    while (i < newArray.length - 1) {
                        newArray[i] = input.readBytes();
                        input.readTag();
                        i++;
                    }
                    newArray[i] = input.readBytes();
                    this.shownResourceKeys = newArray;
                    continue;
                case LogSource.GOOGLE_ANALYTICS /*18*/:
                    if (this.originalUserEnvironment == null) {
                        this.originalUserEnvironment = new UserEnvironment();
                    }
                    input.readMessage(this.originalUserEnvironment);
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

    public static UiFingerprint parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
        return (UiFingerprint) MessageNano.mergeFrom(new UiFingerprint(), data);
    }

    public static UiFingerprint parseFrom(CodedInputByteBufferNano input) throws IOException {
        return new UiFingerprint().mergeFrom(input);
    }
}
