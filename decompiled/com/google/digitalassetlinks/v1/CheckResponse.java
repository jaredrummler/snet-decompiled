package com.google.digitalassetlinks.v1;

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
import java.io.IOException;

public final class CheckResponse extends ExtendableMessageNano<CheckResponse> {
    private static volatile CheckResponse[] _emptyArray;
    public String debugString;
    public boolean linked;
    public Duration maxAge;

    public static CheckResponse[] emptyArray() {
        if (_emptyArray == null) {
            synchronized (InternalNano.LAZY_INIT_LOCK) {
                if (_emptyArray == null) {
                    _emptyArray = new CheckResponse[0];
                }
            }
        }
        return _emptyArray;
    }

    public CheckResponse() {
        clear();
    }

    public CheckResponse clear() {
        this.linked = false;
        this.maxAge = null;
        this.debugString = BuildConfig.VERSION_NAME;
        this.unknownFieldData = null;
        this.cachedSize = -1;
        return this;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof CheckResponse)) {
            return false;
        }
        CheckResponse other = (CheckResponse) o;
        if (this.linked != other.linked) {
            return false;
        }
        if (this.maxAge == null) {
            if (other.maxAge != null) {
                return false;
            }
        } else if (!this.maxAge.equals(other.maxAge)) {
            return false;
        }
        if (this.debugString == null) {
            if (other.debugString != null) {
                return false;
            }
        } else if (!this.debugString.equals(other.debugString)) {
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
        int hashCode = (((((((getClass().getName().hashCode() + 527) * 31) + (this.linked ? 1231 : 1237)) * 31) + (this.maxAge == null ? 0 : this.maxAge.hashCode())) * 31) + (this.debugString == null ? 0 : this.debugString.hashCode())) * 31;
        if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
            i = this.unknownFieldData.hashCode();
        }
        return hashCode + i;
    }

    public void writeTo(CodedOutputByteBufferNano output) throws IOException {
        if (this.linked) {
            output.writeBool(1, this.linked);
        }
        if (this.maxAge != null) {
            output.writeMessage(2, this.maxAge);
        }
        if (!this.debugString.equals(BuildConfig.VERSION_NAME)) {
            output.writeString(3, this.debugString);
        }
        super.writeTo(output);
    }

    protected int computeSerializedSize() {
        int size = super.computeSerializedSize();
        if (this.linked) {
            size += CodedOutputByteBufferNano.computeBoolSize(1, this.linked);
        }
        if (this.maxAge != null) {
            size += CodedOutputByteBufferNano.computeMessageSize(2, this.maxAge);
        }
        if (this.debugString.equals(BuildConfig.VERSION_NAME)) {
            return size;
        }
        return size + CodedOutputByteBufferNano.computeStringSize(3, this.debugString);
    }

    public CheckResponse mergeFrom(CodedInputByteBufferNano input) throws IOException {
        while (true) {
            int tag = input.readTag();
            switch (tag) {
                case Action.UNKNOWN /*0*/:
                    break;
                case Type.TAP_GET_LINK /*8*/:
                    this.linked = input.readBool();
                    continue;
                case LogSource.GOOGLE_ANALYTICS /*18*/:
                    if (this.maxAge == null) {
                        this.maxAge = new Duration();
                    }
                    input.readMessage(this.maxAge);
                    continue;
                case LogSource.ANDROID_CAMERA /*26*/:
                    this.debugString = input.readString();
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

    public static CheckResponse parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
        return (CheckResponse) MessageNano.mergeFrom(new CheckResponse(), data);
    }

    public static CheckResponse parseFrom(CodedInputByteBufferNano input) throws IOException {
        return new CheckResponse().mergeFrom(input);
    }
}
