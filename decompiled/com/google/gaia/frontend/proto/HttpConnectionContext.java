package com.google.gaia.frontend.proto;

import com.google.android.gms.lint.BuildConfig;
import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.ExtendableMessageNano;
import com.google.protobuf.nano.InternalNano;
import com.google.protobuf.nano.InvalidProtocolBufferNanoException;
import com.google.protobuf.nano.MessageNano;
import com.google.wireless.android.play.playlog.proto.ClientAnalytics.LogRequest.LogSource;
import com.google.wireless.android.play.playlog.proto.UserLocation.LaunchSettingsEvent.Action;
import java.io.IOException;

public final class HttpConnectionContext extends ExtendableMessageNano<HttpConnectionContext> {
    private static volatile HttpConnectionContext[] _emptyArray;
    public String userIpAddress;
    public String useragent;

    public static HttpConnectionContext[] emptyArray() {
        if (_emptyArray == null) {
            synchronized (InternalNano.LAZY_INIT_LOCK) {
                if (_emptyArray == null) {
                    _emptyArray = new HttpConnectionContext[0];
                }
            }
        }
        return _emptyArray;
    }

    public HttpConnectionContext() {
        clear();
    }

    public HttpConnectionContext clear() {
        this.useragent = BuildConfig.VERSION_NAME;
        this.userIpAddress = BuildConfig.VERSION_NAME;
        this.unknownFieldData = null;
        this.cachedSize = -1;
        return this;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof HttpConnectionContext)) {
            return false;
        }
        HttpConnectionContext other = (HttpConnectionContext) o;
        if (this.useragent == null) {
            if (other.useragent != null) {
                return false;
            }
        } else if (!this.useragent.equals(other.useragent)) {
            return false;
        }
        if (this.userIpAddress == null) {
            if (other.userIpAddress != null) {
                return false;
            }
        } else if (!this.userIpAddress.equals(other.userIpAddress)) {
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
        int hashCode = (((((getClass().getName().hashCode() + 527) * 31) + (this.useragent == null ? 0 : this.useragent.hashCode())) * 31) + (this.userIpAddress == null ? 0 : this.userIpAddress.hashCode())) * 31;
        if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
            i = this.unknownFieldData.hashCode();
        }
        return hashCode + i;
    }

    public void writeTo(CodedOutputByteBufferNano output) throws IOException {
        if (!this.useragent.equals(BuildConfig.VERSION_NAME)) {
            output.writeString(2, this.useragent);
        }
        if (!this.userIpAddress.equals(BuildConfig.VERSION_NAME)) {
            output.writeString(3, this.userIpAddress);
        }
        super.writeTo(output);
    }

    protected int computeSerializedSize() {
        int size = super.computeSerializedSize();
        if (!this.useragent.equals(BuildConfig.VERSION_NAME)) {
            size += CodedOutputByteBufferNano.computeStringSize(2, this.useragent);
        }
        if (this.userIpAddress.equals(BuildConfig.VERSION_NAME)) {
            return size;
        }
        return size + CodedOutputByteBufferNano.computeStringSize(3, this.userIpAddress);
    }

    public HttpConnectionContext mergeFrom(CodedInputByteBufferNano input) throws IOException {
        while (true) {
            int tag = input.readTag();
            switch (tag) {
                case Action.UNKNOWN /*0*/:
                    break;
                case LogSource.GOOGLE_ANALYTICS /*18*/:
                    this.useragent = input.readString();
                    continue;
                case LogSource.ANDROID_CAMERA /*26*/:
                    this.userIpAddress = input.readString();
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

    public static HttpConnectionContext parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
        return (HttpConnectionContext) MessageNano.mergeFrom(new HttpConnectionContext(), data);
    }

    public static HttpConnectionContext parseFrom(CodedInputByteBufferNano input) throws IOException {
        return new HttpConnectionContext().mergeFrom(input);
    }
}
