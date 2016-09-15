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

public final class SettingChangeResult extends ExtendableMessageNano<SettingChangeResult> {
    private static volatile SettingChangeResult[] _emptyArray;
    public SettingAvailability availability;
    public int settingId;
    public int settingValue;

    public static SettingChangeResult[] emptyArray() {
        if (_emptyArray == null) {
            synchronized (InternalNano.LAZY_INIT_LOCK) {
                if (_emptyArray == null) {
                    _emptyArray = new SettingChangeResult[0];
                }
            }
        }
        return _emptyArray;
    }

    public SettingChangeResult() {
        clear();
    }

    public SettingChangeResult clear() {
        this.settingId = 0;
        this.settingValue = 0;
        this.availability = null;
        this.unknownFieldData = null;
        this.cachedSize = -1;
        return this;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof SettingChangeResult)) {
            return false;
        }
        SettingChangeResult other = (SettingChangeResult) o;
        if (this.settingId != other.settingId || this.settingValue != other.settingValue) {
            return false;
        }
        if (this.availability == null) {
            if (other.availability != null) {
                return false;
            }
        } else if (!this.availability.equals(other.availability)) {
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
        int hashCode = (((((((getClass().getName().hashCode() + 527) * 31) + this.settingId) * 31) + this.settingValue) * 31) + (this.availability == null ? 0 : this.availability.hashCode())) * 31;
        if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
            i = this.unknownFieldData.hashCode();
        }
        return hashCode + i;
    }

    public void writeTo(CodedOutputByteBufferNano output) throws IOException {
        if (this.settingId != 0) {
            output.writeInt32(1, this.settingId);
        }
        if (this.settingValue != 0) {
            output.writeInt32(2, this.settingValue);
        }
        if (this.availability != null) {
            output.writeMessage(3, this.availability);
        }
        super.writeTo(output);
    }

    protected int computeSerializedSize() {
        int size = super.computeSerializedSize();
        if (this.settingId != 0) {
            size += CodedOutputByteBufferNano.computeInt32Size(1, this.settingId);
        }
        if (this.settingValue != 0) {
            size += CodedOutputByteBufferNano.computeInt32Size(2, this.settingValue);
        }
        if (this.availability != null) {
            return size + CodedOutputByteBufferNano.computeMessageSize(3, this.availability);
        }
        return size;
    }

    public SettingChangeResult mergeFrom(CodedInputByteBufferNano input) throws IOException {
        while (true) {
            int tag = input.readTag();
            switch (tag) {
                case Action.UNKNOWN /*0*/:
                    break;
                case Type.TAP_GET_LINK /*8*/:
                    this.settingId = input.readInt32();
                    continue;
                case LogSource.GMS_CORE_PEOPLE /*16*/:
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
                case LogSource.ANDROID_CAMERA /*26*/:
                    if (this.availability == null) {
                        this.availability = new SettingAvailability();
                    }
                    input.readMessage(this.availability);
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

    public static SettingChangeResult parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
        return (SettingChangeResult) MessageNano.mergeFrom(new SettingChangeResult(), data);
    }

    public static SettingChangeResult parseFrom(CodedInputByteBufferNano input) throws IOException {
        return new SettingChangeResult().mergeFrom(input);
    }
}
