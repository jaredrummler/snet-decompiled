package com.google.security.cryptauth.lib.risk;

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

public final class DeviceRiskSignals extends ExtendableMessageNano<DeviceRiskSignals> {
    private static volatile DeviceRiskSignals[] _emptyArray;
    public DeviceProperties deviceProperties;
    public ScreenlockState screenlockState;
    public StarguardData starguardData;

    public static DeviceRiskSignals[] emptyArray() {
        if (_emptyArray == null) {
            synchronized (InternalNano.LAZY_INIT_LOCK) {
                if (_emptyArray == null) {
                    _emptyArray = new DeviceRiskSignals[0];
                }
            }
        }
        return _emptyArray;
    }

    public DeviceRiskSignals() {
        clear();
    }

    public DeviceRiskSignals clear() {
        this.deviceProperties = null;
        this.screenlockState = null;
        this.starguardData = null;
        this.unknownFieldData = null;
        this.cachedSize = -1;
        return this;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof DeviceRiskSignals)) {
            return false;
        }
        DeviceRiskSignals other = (DeviceRiskSignals) o;
        if (this.deviceProperties == null) {
            if (other.deviceProperties != null) {
                return false;
            }
        } else if (!this.deviceProperties.equals(other.deviceProperties)) {
            return false;
        }
        if (this.screenlockState == null) {
            if (other.screenlockState != null) {
                return false;
            }
        } else if (!this.screenlockState.equals(other.screenlockState)) {
            return false;
        }
        if (this.starguardData == null) {
            if (other.starguardData != null) {
                return false;
            }
        } else if (!this.starguardData.equals(other.starguardData)) {
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
        int hashCode = (((((((getClass().getName().hashCode() + 527) * 31) + (this.deviceProperties == null ? 0 : this.deviceProperties.hashCode())) * 31) + (this.screenlockState == null ? 0 : this.screenlockState.hashCode())) * 31) + (this.starguardData == null ? 0 : this.starguardData.hashCode())) * 31;
        if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
            i = this.unknownFieldData.hashCode();
        }
        return hashCode + i;
    }

    public void writeTo(CodedOutputByteBufferNano output) throws IOException {
        if (this.deviceProperties != null) {
            output.writeMessage(1, this.deviceProperties);
        }
        if (this.screenlockState != null) {
            output.writeMessage(2, this.screenlockState);
        }
        if (this.starguardData != null) {
            output.writeMessage(3, this.starguardData);
        }
        super.writeTo(output);
    }

    protected int computeSerializedSize() {
        int size = super.computeSerializedSize();
        if (this.deviceProperties != null) {
            size += CodedOutputByteBufferNano.computeMessageSize(1, this.deviceProperties);
        }
        if (this.screenlockState != null) {
            size += CodedOutputByteBufferNano.computeMessageSize(2, this.screenlockState);
        }
        if (this.starguardData != null) {
            return size + CodedOutputByteBufferNano.computeMessageSize(3, this.starguardData);
        }
        return size;
    }

    public DeviceRiskSignals mergeFrom(CodedInputByteBufferNano input) throws IOException {
        while (true) {
            int tag = input.readTag();
            switch (tag) {
                case Action.UNKNOWN /*0*/:
                    break;
                case Type.TAP_ABOUT /*10*/:
                    if (this.deviceProperties == null) {
                        this.deviceProperties = new DeviceProperties();
                    }
                    input.readMessage(this.deviceProperties);
                    continue;
                case LogSource.GOOGLE_ANALYTICS /*18*/:
                    if (this.screenlockState == null) {
                        this.screenlockState = new ScreenlockState();
                    }
                    input.readMessage(this.screenlockState);
                    continue;
                case LogSource.ANDROID_CAMERA /*26*/:
                    if (this.starguardData == null) {
                        this.starguardData = new StarguardData();
                    }
                    input.readMessage(this.starguardData);
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

    public static DeviceRiskSignals parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
        return (DeviceRiskSignals) MessageNano.mergeFrom(new DeviceRiskSignals(), data);
    }

    public static DeviceRiskSignals parseFrom(CodedInputByteBufferNano input) throws IOException {
        return new DeviceRiskSignals().mergeFrom(input);
    }
}
