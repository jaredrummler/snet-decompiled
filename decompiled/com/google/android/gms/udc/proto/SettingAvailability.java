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
import java.io.IOException;

public final class SettingAvailability extends ExtendableMessageNano<SettingAvailability> {
    private static volatile SettingAvailability[] _emptyArray;
    public boolean canDisable;
    public boolean canEnable;
    public boolean valueVisible;

    public static SettingAvailability[] emptyArray() {
        if (_emptyArray == null) {
            synchronized (InternalNano.LAZY_INIT_LOCK) {
                if (_emptyArray == null) {
                    _emptyArray = new SettingAvailability[0];
                }
            }
        }
        return _emptyArray;
    }

    public SettingAvailability() {
        clear();
    }

    public SettingAvailability clear() {
        this.canEnable = false;
        this.canDisable = false;
        this.valueVisible = false;
        this.unknownFieldData = null;
        this.cachedSize = -1;
        return this;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof SettingAvailability)) {
            return false;
        }
        SettingAvailability other = (SettingAvailability) o;
        if (this.canEnable != other.canEnable || this.canDisable != other.canDisable || this.valueVisible != other.valueVisible) {
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
        int i;
        int i2 = 1231;
        int hashCode = (((getClass().getName().hashCode() + 527) * 31) + (this.canEnable ? 1231 : 1237)) * 31;
        if (this.canDisable) {
            i = 1231;
        } else {
            i = 1237;
        }
        i = (hashCode + i) * 31;
        if (!this.valueVisible) {
            i2 = 1237;
        }
        i2 = (i + i2) * 31;
        i = (this.unknownFieldData == null || this.unknownFieldData.isEmpty()) ? 0 : this.unknownFieldData.hashCode();
        return i2 + i;
    }

    public void writeTo(CodedOutputByteBufferNano output) throws IOException {
        if (this.canEnable) {
            output.writeBool(1, this.canEnable);
        }
        if (this.canDisable) {
            output.writeBool(2, this.canDisable);
        }
        if (this.valueVisible) {
            output.writeBool(3, this.valueVisible);
        }
        super.writeTo(output);
    }

    protected int computeSerializedSize() {
        int size = super.computeSerializedSize();
        if (this.canEnable) {
            size += CodedOutputByteBufferNano.computeBoolSize(1, this.canEnable);
        }
        if (this.canDisable) {
            size += CodedOutputByteBufferNano.computeBoolSize(2, this.canDisable);
        }
        if (this.valueVisible) {
            return size + CodedOutputByteBufferNano.computeBoolSize(3, this.valueVisible);
        }
        return size;
    }

    public SettingAvailability mergeFrom(CodedInputByteBufferNano input) throws IOException {
        while (true) {
            int tag = input.readTag();
            switch (tag) {
                case Action.UNKNOWN /*0*/:
                    break;
                case Type.TAP_GET_LINK /*8*/:
                    this.canEnable = input.readBool();
                    continue;
                case LogSource.GMS_CORE_PEOPLE /*16*/:
                    this.canDisable = input.readBool();
                    continue;
                case LogSource.LB_C /*24*/:
                    this.valueVisible = input.readBool();
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

    public static SettingAvailability parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
        return (SettingAvailability) MessageNano.mergeFrom(new SettingAvailability(), data);
    }

    public static SettingAvailability parseFrom(CodedInputByteBufferNano input) throws IOException {
        return new SettingAvailability().mergeFrom(input);
    }
}
