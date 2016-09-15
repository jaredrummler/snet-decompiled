package com.google.android.gms.udc.proto;

import com.google.android.gms.lint.BuildConfig;
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

public final class ImageResource extends ExtendableMessageNano<ImageResource> {
    private static volatile ImageResource[] _emptyArray;
    public ResourceMapping mapping;
    public byte[] resourceKey;
    public String uri;

    public static ImageResource[] emptyArray() {
        if (_emptyArray == null) {
            synchronized (InternalNano.LAZY_INIT_LOCK) {
                if (_emptyArray == null) {
                    _emptyArray = new ImageResource[0];
                }
            }
        }
        return _emptyArray;
    }

    public ImageResource() {
        clear();
    }

    public ImageResource clear() {
        this.resourceKey = WireFormatNano.EMPTY_BYTES;
        this.uri = BuildConfig.VERSION_NAME;
        this.mapping = null;
        this.unknownFieldData = null;
        this.cachedSize = -1;
        return this;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ImageResource)) {
            return false;
        }
        ImageResource other = (ImageResource) o;
        if (!Arrays.equals(this.resourceKey, other.resourceKey)) {
            return false;
        }
        if (this.uri == null) {
            if (other.uri != null) {
                return false;
            }
        } else if (!this.uri.equals(other.uri)) {
            return false;
        }
        if (this.mapping == null) {
            if (other.mapping != null) {
                return false;
            }
        } else if (!this.mapping.equals(other.mapping)) {
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
        int hashCode = (((((((getClass().getName().hashCode() + 527) * 31) + Arrays.hashCode(this.resourceKey)) * 31) + (this.uri == null ? 0 : this.uri.hashCode())) * 31) + (this.mapping == null ? 0 : this.mapping.hashCode())) * 31;
        if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
            i = this.unknownFieldData.hashCode();
        }
        return hashCode + i;
    }

    public void writeTo(CodedOutputByteBufferNano output) throws IOException {
        if (!Arrays.equals(this.resourceKey, WireFormatNano.EMPTY_BYTES)) {
            output.writeBytes(1, this.resourceKey);
        }
        if (!this.uri.equals(BuildConfig.VERSION_NAME)) {
            output.writeString(2, this.uri);
        }
        if (this.mapping != null) {
            output.writeMessage(3, this.mapping);
        }
        super.writeTo(output);
    }

    protected int computeSerializedSize() {
        int size = super.computeSerializedSize();
        if (!Arrays.equals(this.resourceKey, WireFormatNano.EMPTY_BYTES)) {
            size += CodedOutputByteBufferNano.computeBytesSize(1, this.resourceKey);
        }
        if (!this.uri.equals(BuildConfig.VERSION_NAME)) {
            size += CodedOutputByteBufferNano.computeStringSize(2, this.uri);
        }
        if (this.mapping != null) {
            return size + CodedOutputByteBufferNano.computeMessageSize(3, this.mapping);
        }
        return size;
    }

    public ImageResource mergeFrom(CodedInputByteBufferNano input) throws IOException {
        while (true) {
            int tag = input.readTag();
            switch (tag) {
                case Action.UNKNOWN /*0*/:
                    break;
                case Type.TAP_ABOUT /*10*/:
                    this.resourceKey = input.readBytes();
                    continue;
                case LogSource.GOOGLE_ANALYTICS /*18*/:
                    this.uri = input.readString();
                    continue;
                case LogSource.ANDROID_CAMERA /*26*/:
                    if (this.mapping == null) {
                        this.mapping = new ResourceMapping();
                    }
                    input.readMessage(this.mapping);
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

    public static ImageResource parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
        return (ImageResource) MessageNano.mergeFrom(new ImageResource(), data);
    }

    public static ImageResource parseFrom(CodedInputByteBufferNano input) throws IOException {
        return new ImageResource().mergeFrom(input);
    }
}
