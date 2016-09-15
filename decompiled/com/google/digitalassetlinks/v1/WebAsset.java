package com.google.digitalassetlinks.v1;

import com.google.android.gms.lint.BuildConfig;
import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.ExtendableMessageNano;
import com.google.protobuf.nano.InternalNano;
import com.google.protobuf.nano.InvalidProtocolBufferNanoException;
import com.google.protobuf.nano.MessageNano;
import com.google.wireless.android.play.playlog.proto.UserLocation.LaunchSettingsEvent.Action;
import com.google.wireless.android.play.playlog.proto.UserLocation.LocationSharingEvent.Type;
import java.io.IOException;

public final class WebAsset extends ExtendableMessageNano<WebAsset> {
    private static volatile WebAsset[] _emptyArray;
    public String site;

    public static WebAsset[] emptyArray() {
        if (_emptyArray == null) {
            synchronized (InternalNano.LAZY_INIT_LOCK) {
                if (_emptyArray == null) {
                    _emptyArray = new WebAsset[0];
                }
            }
        }
        return _emptyArray;
    }

    public WebAsset() {
        clear();
    }

    public WebAsset clear() {
        this.site = BuildConfig.VERSION_NAME;
        this.unknownFieldData = null;
        this.cachedSize = -1;
        return this;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof WebAsset)) {
            return false;
        }
        WebAsset other = (WebAsset) o;
        if (this.site == null) {
            if (other.site != null) {
                return false;
            }
        } else if (!this.site.equals(other.site)) {
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
        int hashCode = (((getClass().getName().hashCode() + 527) * 31) + (this.site == null ? 0 : this.site.hashCode())) * 31;
        if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
            i = this.unknownFieldData.hashCode();
        }
        return hashCode + i;
    }

    public void writeTo(CodedOutputByteBufferNano output) throws IOException {
        if (!this.site.equals(BuildConfig.VERSION_NAME)) {
            output.writeString(1, this.site);
        }
        super.writeTo(output);
    }

    protected int computeSerializedSize() {
        int size = super.computeSerializedSize();
        if (this.site.equals(BuildConfig.VERSION_NAME)) {
            return size;
        }
        return size + CodedOutputByteBufferNano.computeStringSize(1, this.site);
    }

    public WebAsset mergeFrom(CodedInputByteBufferNano input) throws IOException {
        while (true) {
            int tag = input.readTag();
            switch (tag) {
                case Action.UNKNOWN /*0*/:
                    break;
                case Type.TAP_ABOUT /*10*/:
                    this.site = input.readString();
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

    public static WebAsset parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
        return (WebAsset) MessageNano.mergeFrom(new WebAsset(), data);
    }

    public static WebAsset parseFrom(CodedInputByteBufferNano input) throws IOException {
        return new WebAsset().mergeFrom(input);
    }
}
