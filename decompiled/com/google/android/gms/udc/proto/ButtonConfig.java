package com.google.android.gms.udc.proto;

import com.google.android.gms.lint.BuildConfig;
import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.ExtendableMessageNano;
import com.google.protobuf.nano.InternalNano;
import com.google.protobuf.nano.InvalidProtocolBufferNanoException;
import com.google.protobuf.nano.MessageNano;
import com.google.wireless.android.play.playlog.proto.ClientAnalytics.LogRequest.LogSource;
import com.google.wireless.android.play.playlog.proto.UserLocation.LaunchSettingsEvent.Action;
import com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection.Type;
import java.io.IOException;

public final class ButtonConfig extends ExtendableMessageNano<ButtonConfig> {
    private static volatile ButtonConfig[] _emptyArray;
    public TextResource caption;
    public int forNewValue;
    public ImageResource icon;
    public TextResource internalUriResource;
    public boolean shouldAuthorize;
    public String uri;

    public static ButtonConfig[] emptyArray() {
        if (_emptyArray == null) {
            synchronized (InternalNano.LAZY_INIT_LOCK) {
                if (_emptyArray == null) {
                    _emptyArray = new ButtonConfig[0];
                }
            }
        }
        return _emptyArray;
    }

    public ButtonConfig() {
        clear();
    }

    public ButtonConfig clear() {
        this.caption = null;
        this.icon = null;
        this.uri = BuildConfig.VERSION_NAME;
        this.internalUriResource = null;
        this.shouldAuthorize = false;
        this.forNewValue = 0;
        this.unknownFieldData = null;
        this.cachedSize = -1;
        return this;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ButtonConfig)) {
            return false;
        }
        ButtonConfig other = (ButtonConfig) o;
        if (this.caption == null) {
            if (other.caption != null) {
                return false;
            }
        } else if (!this.caption.equals(other.caption)) {
            return false;
        }
        if (this.icon == null) {
            if (other.icon != null) {
                return false;
            }
        } else if (!this.icon.equals(other.icon)) {
            return false;
        }
        if (this.uri == null) {
            if (other.uri != null) {
                return false;
            }
        } else if (!this.uri.equals(other.uri)) {
            return false;
        }
        if (this.internalUriResource == null) {
            if (other.internalUriResource != null) {
                return false;
            }
        } else if (!this.internalUriResource.equals(other.internalUriResource)) {
            return false;
        }
        if (this.shouldAuthorize != other.shouldAuthorize || this.forNewValue != other.forNewValue) {
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
        int hashCode = (((((((((((((getClass().getName().hashCode() + 527) * 31) + (this.caption == null ? 0 : this.caption.hashCode())) * 31) + (this.icon == null ? 0 : this.icon.hashCode())) * 31) + (this.uri == null ? 0 : this.uri.hashCode())) * 31) + (this.internalUriResource == null ? 0 : this.internalUriResource.hashCode())) * 31) + (this.shouldAuthorize ? 1231 : 1237)) * 31) + this.forNewValue) * 31;
        if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
            i = this.unknownFieldData.hashCode();
        }
        return hashCode + i;
    }

    public void writeTo(CodedOutputByteBufferNano output) throws IOException {
        if (this.caption != null) {
            output.writeMessage(2, this.caption);
        }
        if (this.icon != null) {
            output.writeMessage(3, this.icon);
        }
        if (!this.uri.equals(BuildConfig.VERSION_NAME)) {
            output.writeString(4, this.uri);
        }
        if (this.forNewValue != 0) {
            output.writeInt32(5, this.forNewValue);
        }
        if (this.shouldAuthorize) {
            output.writeBool(6, this.shouldAuthorize);
        }
        if (this.internalUriResource != null) {
            output.writeMessage(7, this.internalUriResource);
        }
        super.writeTo(output);
    }

    protected int computeSerializedSize() {
        int size = super.computeSerializedSize();
        if (this.caption != null) {
            size += CodedOutputByteBufferNano.computeMessageSize(2, this.caption);
        }
        if (this.icon != null) {
            size += CodedOutputByteBufferNano.computeMessageSize(3, this.icon);
        }
        if (!this.uri.equals(BuildConfig.VERSION_NAME)) {
            size += CodedOutputByteBufferNano.computeStringSize(4, this.uri);
        }
        if (this.forNewValue != 0) {
            size += CodedOutputByteBufferNano.computeInt32Size(5, this.forNewValue);
        }
        if (this.shouldAuthorize) {
            size += CodedOutputByteBufferNano.computeBoolSize(6, this.shouldAuthorize);
        }
        if (this.internalUriResource != null) {
            return size + CodedOutputByteBufferNano.computeMessageSize(7, this.internalUriResource);
        }
        return size;
    }

    public ButtonConfig mergeFrom(CodedInputByteBufferNano input) throws IOException {
        while (true) {
            int tag = input.readTag();
            switch (tag) {
                case Action.UNKNOWN /*0*/:
                    break;
                case LogSource.GOOGLE_ANALYTICS /*18*/:
                    if (this.caption == null) {
                        this.caption = new TextResource();
                    }
                    input.readMessage(this.caption);
                    continue;
                case LogSource.ANDROID_CAMERA /*26*/:
                    if (this.icon == null) {
                        this.icon = new ImageResource();
                    }
                    input.readMessage(this.icon);
                    continue;
                case LogSource.NOVA /*34*/:
                    this.uri = input.readString();
                    continue;
                case LogSource.COPRESENCE /*40*/:
                    int value = input.readInt32();
                    switch (value) {
                        case Action.UNKNOWN /*0*/:
                        case Type.TEMPORARY /*1*/:
                        case Type.INDEFINITELY /*2*/:
                        case Type.CUSTOM /*3*/:
                            this.forNewValue = value;
                            break;
                        default:
                            continue;
                    }
                case LogSource.BACKDROP /*48*/:
                    this.shouldAuthorize = input.readBool();
                    continue;
                case LogSource.SLIDES /*58*/:
                    if (this.internalUriResource == null) {
                        this.internalUriResource = new TextResource();
                    }
                    input.readMessage(this.internalUriResource);
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

    public static ButtonConfig parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
        return (ButtonConfig) MessageNano.mergeFrom(new ButtonConfig(), data);
    }

    public static ButtonConfig parseFrom(CodedInputByteBufferNano input) throws IOException {
        return new ButtonConfig().mergeFrom(input);
    }
}
