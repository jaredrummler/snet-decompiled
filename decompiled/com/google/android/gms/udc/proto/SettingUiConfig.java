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

public final class SettingUiConfig extends ExtendableMessageNano<SettingUiConfig> {
    private static volatile SettingUiConfig[] _emptyArray;
    public TextResource description;
    public ImageResource icon;
    public TextResource[] infoTexts;
    public TextResource name;
    public SettingState settingState;

    public static SettingUiConfig[] emptyArray() {
        if (_emptyArray == null) {
            synchronized (InternalNano.LAZY_INIT_LOCK) {
                if (_emptyArray == null) {
                    _emptyArray = new SettingUiConfig[0];
                }
            }
        }
        return _emptyArray;
    }

    public SettingUiConfig() {
        clear();
    }

    public SettingUiConfig clear() {
        this.settingState = null;
        this.name = null;
        this.icon = null;
        this.description = null;
        this.infoTexts = TextResource.emptyArray();
        this.unknownFieldData = null;
        this.cachedSize = -1;
        return this;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof SettingUiConfig)) {
            return false;
        }
        SettingUiConfig other = (SettingUiConfig) o;
        if (this.settingState == null) {
            if (other.settingState != null) {
                return false;
            }
        } else if (!this.settingState.equals(other.settingState)) {
            return false;
        }
        if (this.name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!this.name.equals(other.name)) {
            return false;
        }
        if (this.icon == null) {
            if (other.icon != null) {
                return false;
            }
        } else if (!this.icon.equals(other.icon)) {
            return false;
        }
        if (this.description == null) {
            if (other.description != null) {
                return false;
            }
        } else if (!this.description.equals(other.description)) {
            return false;
        }
        if (!InternalNano.equals(this.infoTexts, other.infoTexts)) {
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
        int hashCode = (((((((((((getClass().getName().hashCode() + 527) * 31) + (this.settingState == null ? 0 : this.settingState.hashCode())) * 31) + (this.name == null ? 0 : this.name.hashCode())) * 31) + (this.icon == null ? 0 : this.icon.hashCode())) * 31) + (this.description == null ? 0 : this.description.hashCode())) * 31) + InternalNano.hashCode(this.infoTexts)) * 31;
        if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
            i = this.unknownFieldData.hashCode();
        }
        return hashCode + i;
    }

    public void writeTo(CodedOutputByteBufferNano output) throws IOException {
        if (this.settingState != null) {
            output.writeMessage(1, this.settingState);
        }
        if (this.name != null) {
            output.writeMessage(3, this.name);
        }
        if (this.icon != null) {
            output.writeMessage(4, this.icon);
        }
        if (this.description != null) {
            output.writeMessage(5, this.description);
        }
        if (this.infoTexts != null && this.infoTexts.length > 0) {
            for (TextResource element : this.infoTexts) {
                if (element != null) {
                    output.writeMessage(6, element);
                }
            }
        }
        super.writeTo(output);
    }

    protected int computeSerializedSize() {
        int size = super.computeSerializedSize();
        if (this.settingState != null) {
            size += CodedOutputByteBufferNano.computeMessageSize(1, this.settingState);
        }
        if (this.name != null) {
            size += CodedOutputByteBufferNano.computeMessageSize(3, this.name);
        }
        if (this.icon != null) {
            size += CodedOutputByteBufferNano.computeMessageSize(4, this.icon);
        }
        if (this.description != null) {
            size += CodedOutputByteBufferNano.computeMessageSize(5, this.description);
        }
        if (this.infoTexts != null && this.infoTexts.length > 0) {
            for (TextResource element : this.infoTexts) {
                if (element != null) {
                    size += CodedOutputByteBufferNano.computeMessageSize(6, element);
                }
            }
        }
        return size;
    }

    public SettingUiConfig mergeFrom(CodedInputByteBufferNano input) throws IOException {
        while (true) {
            int tag = input.readTag();
            switch (tag) {
                case Action.UNKNOWN /*0*/:
                    break;
                case Type.TAP_ABOUT /*10*/:
                    if (this.settingState == null) {
                        this.settingState = new SettingState();
                    }
                    input.readMessage(this.settingState);
                    continue;
                case LogSource.ANDROID_CAMERA /*26*/:
                    if (this.name == null) {
                        this.name = new TextResource();
                    }
                    input.readMessage(this.name);
                    continue;
                case LogSource.NOVA /*34*/:
                    if (this.icon == null) {
                        this.icon = new ImageResource();
                    }
                    input.readMessage(this.icon);
                    continue;
                case LogSource.PHOTOS /*42*/:
                    if (this.description == null) {
                        this.description = new TextResource();
                    }
                    input.readMessage(this.description);
                    continue;
                case LogSource.HANGOUT_LOG_REQUEST /*50*/:
                    int i;
                    int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 50);
                    if (this.infoTexts == null) {
                        i = 0;
                    } else {
                        i = this.infoTexts.length;
                    }
                    TextResource[] newArray = new TextResource[(i + arrayLength)];
                    if (i != 0) {
                        System.arraycopy(this.infoTexts, 0, newArray, 0, i);
                    }
                    while (i < newArray.length - 1) {
                        newArray[i] = new TextResource();
                        input.readMessage(newArray[i]);
                        input.readTag();
                        i++;
                    }
                    newArray[i] = new TextResource();
                    input.readMessage(newArray[i]);
                    this.infoTexts = newArray;
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

    public static SettingUiConfig parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
        return (SettingUiConfig) MessageNano.mergeFrom(new SettingUiConfig(), data);
    }

    public static SettingUiConfig parseFrom(CodedInputByteBufferNano input) throws IOException {
        return new SettingUiConfig().mergeFrom(input);
    }
}
