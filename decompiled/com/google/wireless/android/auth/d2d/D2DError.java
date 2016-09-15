package com.google.wireless.android.auth.d2d;

import com.google.android.gms.lint.BuildConfig;
import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.ExtendableMessageNano;
import com.google.protobuf.nano.InternalNano;
import com.google.protobuf.nano.InvalidProtocolBufferNanoException;
import com.google.protobuf.nano.MessageNano;
import com.google.wireless.android.play.playlog.proto.ClientAnalytics.LogRequest.LogSource;
import com.google.wireless.android.play.playlog.proto.UserLocation.LaunchSettingsEvent.Action;
import com.google.wireless.android.play.playlog.proto.UserLocation.LocationSharingEvent.Type;
import com.google.wireless.android.play.playlog.proto.UserLocation.OvenFreshEvent.OvenFreshResult;
import com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection;
import java.io.IOException;

public final class D2DError extends ExtendableMessageNano<D2DError> {
    private static volatile D2DError[] _emptyArray;
    public int code;
    public String msg;

    public interface Codes {
        public static final int BAD_REQUEST = 4;
        public static final int INVALID_RESPONSE_PROTO = 2;
        public static final int NETWORK_ERROR = 3;
        public static final int UNKNOWN = 1;
    }

    public static D2DError[] emptyArray() {
        if (_emptyArray == null) {
            synchronized (InternalNano.LAZY_INIT_LOCK) {
                if (_emptyArray == null) {
                    _emptyArray = new D2DError[0];
                }
            }
        }
        return _emptyArray;
    }

    public D2DError() {
        clear();
    }

    public D2DError clear() {
        this.code = 1;
        this.msg = BuildConfig.VERSION_NAME;
        this.unknownFieldData = null;
        this.cachedSize = -1;
        return this;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof D2DError)) {
            return false;
        }
        D2DError other = (D2DError) o;
        if (this.code != other.code) {
            return false;
        }
        if (this.msg == null) {
            if (other.msg != null) {
                return false;
            }
        } else if (!this.msg.equals(other.msg)) {
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
        int hashCode = (((((getClass().getName().hashCode() + 527) * 31) + this.code) * 31) + (this.msg == null ? 0 : this.msg.hashCode())) * 31;
        if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
            i = this.unknownFieldData.hashCode();
        }
        return hashCode + i;
    }

    public void writeTo(CodedOutputByteBufferNano output) throws IOException {
        if (this.code != 1) {
            output.writeInt32(1, this.code);
        }
        if (!this.msg.equals(BuildConfig.VERSION_NAME)) {
            output.writeString(2, this.msg);
        }
        super.writeTo(output);
    }

    protected int computeSerializedSize() {
        int size = super.computeSerializedSize();
        if (this.code != 1) {
            size += CodedOutputByteBufferNano.computeInt32Size(1, this.code);
        }
        if (this.msg.equals(BuildConfig.VERSION_NAME)) {
            return size;
        }
        return size + CodedOutputByteBufferNano.computeStringSize(2, this.msg);
    }

    public D2DError mergeFrom(CodedInputByteBufferNano input) throws IOException {
        while (true) {
            int tag = input.readTag();
            switch (tag) {
                case Action.UNKNOWN /*0*/:
                    break;
                case Type.TAP_GET_LINK /*8*/:
                    int value = input.readInt32();
                    switch (value) {
                        case TimeSelection.Type.TEMPORARY /*1*/:
                        case TimeSelection.Type.INDEFINITELY /*2*/:
                        case TimeSelection.Type.CUSTOM /*3*/:
                        case OvenFreshResult.NO_ACCOUNTS /*4*/:
                            this.code = value;
                            break;
                        default:
                            continue;
                    }
                case LogSource.GOOGLE_ANALYTICS /*18*/:
                    this.msg = input.readString();
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

    public static D2DError parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
        return (D2DError) MessageNano.mergeFrom(new D2DError(), data);
    }

    public static D2DError parseFrom(CodedInputByteBufferNano input) throws IOException {
        return new D2DError().mergeFrom(input);
    }
}
