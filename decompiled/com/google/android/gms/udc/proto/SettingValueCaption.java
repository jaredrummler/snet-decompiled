package com.google.android.gms.udc.proto;

import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.ExtendableMessageNano;
import com.google.protobuf.nano.InternalNano;
import com.google.protobuf.nano.InvalidProtocolBufferNanoException;
import com.google.protobuf.nano.MessageNano;
import com.google.wireless.android.play.playlog.proto.ClientAnalytics.LogRequest.LogSource;
import com.google.wireless.android.play.playlog.proto.UserLocation.LaunchSettingsEvent.Action;
import com.google.wireless.android.play.playlog.proto.UserLocation.LocationSharingEvent.Type;
import com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection;
import java.io.IOException;

public final class SettingValueCaption extends ExtendableMessageNano<SettingValueCaption> {
    private static volatile SettingValueCaption[] _emptyArray;
    public TextResource settingText;
    public int settingValue;

    public static SettingValueCaption[] emptyArray() {
        if (_emptyArray == null) {
            synchronized (InternalNano.LAZY_INIT_LOCK) {
                if (_emptyArray == null) {
                    _emptyArray = new SettingValueCaption[0];
                }
            }
        }
        return _emptyArray;
    }

    public SettingValueCaption() {
        clear();
    }

    public SettingValueCaption clear() {
        this.settingValue = 0;
        this.settingText = null;
        this.unknownFieldData = null;
        this.cachedSize = -1;
        return this;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof SettingValueCaption)) {
            return false;
        }
        SettingValueCaption other = (SettingValueCaption) o;
        if (this.settingValue != other.settingValue) {
            return false;
        }
        if (this.settingText == null) {
            if (other.settingText != null) {
                return false;
            }
        } else if (!this.settingText.equals(other.settingText)) {
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
        int hashCode = (((((getClass().getName().hashCode() + 527) * 31) + this.settingValue) * 31) + (this.settingText == null ? 0 : this.settingText.hashCode())) * 31;
        if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
            i = this.unknownFieldData.hashCode();
        }
        return hashCode + i;
    }

    public void writeTo(CodedOutputByteBufferNano output) throws IOException {
        if (this.settingValue != 0) {
            output.writeInt32(1, this.settingValue);
        }
        if (this.settingText != null) {
            output.writeMessage(2, this.settingText);
        }
        super.writeTo(output);
    }

    protected int computeSerializedSize() {
        int size = super.computeSerializedSize();
        if (this.settingValue != 0) {
            size += CodedOutputByteBufferNano.computeInt32Size(1, this.settingValue);
        }
        if (this.settingText != null) {
            return size + CodedOutputByteBufferNano.computeMessageSize(2, this.settingText);
        }
        return size;
    }

    public SettingValueCaption mergeFrom(CodedInputByteBufferNano input) throws IOException {
        while (true) {
            int tag = input.readTag();
            switch (tag) {
                case Action.UNKNOWN /*0*/:
                    break;
                case Type.TAP_GET_LINK /*8*/:
                    int value = input.readInt32();
                    switch (value) {
                        case Action.UNKNOWN /*0*/:
                        case TimeSelection.Type.TEMPORARY /*1*/:
                        case TimeSelection.Type.INDEFINITELY /*2*/:
                        case TimeSelection.Type.CUSTOM /*3*/:
                            this.settingValue = value;
                            break;
                        default:
                            continue;
                    }
                case LogSource.GOOGLE_ANALYTICS /*18*/:
                    if (this.settingText == null) {
                        this.settingText = new TextResource();
                    }
                    input.readMessage(this.settingText);
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

    public static SettingValueCaption parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
        return (SettingValueCaption) MessageNano.mergeFrom(new SettingValueCaption(), data);
    }

    public static SettingValueCaption parseFrom(CodedInputByteBufferNano input) throws IOException {
        return new SettingValueCaption().mergeFrom(input);
    }
}
