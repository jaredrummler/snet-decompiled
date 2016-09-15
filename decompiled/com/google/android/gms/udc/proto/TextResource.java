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

public final class TextResource extends ExtendableMessageNano<TextResource> {
    private static volatile TextResource[] _emptyArray;
    public ResourceMapping mapping;
    public byte[] resourceKey;
    public String styledText;

    public static TextResource[] emptyArray() {
        if (_emptyArray == null) {
            synchronized (InternalNano.LAZY_INIT_LOCK) {
                if (_emptyArray == null) {
                    _emptyArray = new TextResource[0];
                }
            }
        }
        return _emptyArray;
    }

    public TextResource() {
        clear();
    }

    public TextResource clear() {
        this.resourceKey = WireFormatNano.EMPTY_BYTES;
        this.styledText = BuildConfig.VERSION_NAME;
        this.mapping = null;
        this.unknownFieldData = null;
        this.cachedSize = -1;
        return this;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof TextResource)) {
            return false;
        }
        TextResource other = (TextResource) o;
        if (!Arrays.equals(this.resourceKey, other.resourceKey)) {
            return false;
        }
        if (this.styledText == null) {
            if (other.styledText != null) {
                return false;
            }
        } else if (!this.styledText.equals(other.styledText)) {
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
        int hashCode = (((((((getClass().getName().hashCode() + 527) * 31) + Arrays.hashCode(this.resourceKey)) * 31) + (this.styledText == null ? 0 : this.styledText.hashCode())) * 31) + (this.mapping == null ? 0 : this.mapping.hashCode())) * 31;
        if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
            i = this.unknownFieldData.hashCode();
        }
        return hashCode + i;
    }

    public void writeTo(CodedOutputByteBufferNano output) throws IOException {
        if (!Arrays.equals(this.resourceKey, WireFormatNano.EMPTY_BYTES)) {
            output.writeBytes(1, this.resourceKey);
        }
        if (!this.styledText.equals(BuildConfig.VERSION_NAME)) {
            output.writeString(2, this.styledText);
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
        if (!this.styledText.equals(BuildConfig.VERSION_NAME)) {
            size += CodedOutputByteBufferNano.computeStringSize(2, this.styledText);
        }
        if (this.mapping != null) {
            return size + CodedOutputByteBufferNano.computeMessageSize(3, this.mapping);
        }
        return size;
    }

    public TextResource mergeFrom(CodedInputByteBufferNano input) throws IOException {
        while (true) {
            int tag = input.readTag();
            switch (tag) {
                case Action.UNKNOWN /*0*/:
                    break;
                case Type.TAP_ABOUT /*10*/:
                    this.resourceKey = input.readBytes();
                    continue;
                case LogSource.GOOGLE_ANALYTICS /*18*/:
                    this.styledText = input.readString();
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

    public static TextResource parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
        return (TextResource) MessageNano.mergeFrom(new TextResource(), data);
    }

    public static TextResource parseFrom(CodedInputByteBufferNano input) throws IOException {
        return new TextResource().mergeFrom(input);
    }
}
