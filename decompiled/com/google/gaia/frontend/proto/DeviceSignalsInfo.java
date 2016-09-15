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
import com.google.wireless.android.play.playlog.proto.UserLocation.LocationSharingEvent.Type;
import java.io.IOException;

public final class DeviceSignalsInfo extends ExtendableMessageNano<DeviceSignalsInfo> {
    private static volatile DeviceSignalsInfo[] _emptyArray;
    public String deviceId;
    public String deviceModel;
    public String deviceName;
    public String droidguardResults;
    public String googlePlayServicesVersion;
    public String sdkVersion;
    public UserPresenceInfo userPresenceInfo;

    public static DeviceSignalsInfo[] emptyArray() {
        if (_emptyArray == null) {
            synchronized (InternalNano.LAZY_INIT_LOCK) {
                if (_emptyArray == null) {
                    _emptyArray = new DeviceSignalsInfo[0];
                }
            }
        }
        return _emptyArray;
    }

    public DeviceSignalsInfo() {
        clear();
    }

    public DeviceSignalsInfo clear() {
        this.deviceId = BuildConfig.VERSION_NAME;
        this.deviceName = BuildConfig.VERSION_NAME;
        this.deviceModel = BuildConfig.VERSION_NAME;
        this.sdkVersion = BuildConfig.VERSION_NAME;
        this.googlePlayServicesVersion = BuildConfig.VERSION_NAME;
        this.droidguardResults = BuildConfig.VERSION_NAME;
        this.userPresenceInfo = null;
        this.unknownFieldData = null;
        this.cachedSize = -1;
        return this;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof DeviceSignalsInfo)) {
            return false;
        }
        DeviceSignalsInfo other = (DeviceSignalsInfo) o;
        if (this.deviceId == null) {
            if (other.deviceId != null) {
                return false;
            }
        } else if (!this.deviceId.equals(other.deviceId)) {
            return false;
        }
        if (this.deviceName == null) {
            if (other.deviceName != null) {
                return false;
            }
        } else if (!this.deviceName.equals(other.deviceName)) {
            return false;
        }
        if (this.deviceModel == null) {
            if (other.deviceModel != null) {
                return false;
            }
        } else if (!this.deviceModel.equals(other.deviceModel)) {
            return false;
        }
        if (this.sdkVersion == null) {
            if (other.sdkVersion != null) {
                return false;
            }
        } else if (!this.sdkVersion.equals(other.sdkVersion)) {
            return false;
        }
        if (this.googlePlayServicesVersion == null) {
            if (other.googlePlayServicesVersion != null) {
                return false;
            }
        } else if (!this.googlePlayServicesVersion.equals(other.googlePlayServicesVersion)) {
            return false;
        }
        if (this.droidguardResults == null) {
            if (other.droidguardResults != null) {
                return false;
            }
        } else if (!this.droidguardResults.equals(other.droidguardResults)) {
            return false;
        }
        if (this.userPresenceInfo == null) {
            if (other.userPresenceInfo != null) {
                return false;
            }
        } else if (!this.userPresenceInfo.equals(other.userPresenceInfo)) {
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
        int hashCode = (((((((((((((((getClass().getName().hashCode() + 527) * 31) + (this.deviceId == null ? 0 : this.deviceId.hashCode())) * 31) + (this.deviceName == null ? 0 : this.deviceName.hashCode())) * 31) + (this.deviceModel == null ? 0 : this.deviceModel.hashCode())) * 31) + (this.sdkVersion == null ? 0 : this.sdkVersion.hashCode())) * 31) + (this.googlePlayServicesVersion == null ? 0 : this.googlePlayServicesVersion.hashCode())) * 31) + (this.droidguardResults == null ? 0 : this.droidguardResults.hashCode())) * 31) + (this.userPresenceInfo == null ? 0 : this.userPresenceInfo.hashCode())) * 31;
        if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
            i = this.unknownFieldData.hashCode();
        }
        return hashCode + i;
    }

    public void writeTo(CodedOutputByteBufferNano output) throws IOException {
        if (!this.deviceId.equals(BuildConfig.VERSION_NAME)) {
            output.writeString(1, this.deviceId);
        }
        if (!this.deviceName.equals(BuildConfig.VERSION_NAME)) {
            output.writeString(2, this.deviceName);
        }
        if (!this.deviceModel.equals(BuildConfig.VERSION_NAME)) {
            output.writeString(3, this.deviceModel);
        }
        if (!this.sdkVersion.equals(BuildConfig.VERSION_NAME)) {
            output.writeString(4, this.sdkVersion);
        }
        if (!this.googlePlayServicesVersion.equals(BuildConfig.VERSION_NAME)) {
            output.writeString(5, this.googlePlayServicesVersion);
        }
        if (!this.droidguardResults.equals(BuildConfig.VERSION_NAME)) {
            output.writeString(6, this.droidguardResults);
        }
        if (this.userPresenceInfo != null) {
            output.writeMessage(7, this.userPresenceInfo);
        }
        super.writeTo(output);
    }

    protected int computeSerializedSize() {
        int size = super.computeSerializedSize();
        if (!this.deviceId.equals(BuildConfig.VERSION_NAME)) {
            size += CodedOutputByteBufferNano.computeStringSize(1, this.deviceId);
        }
        if (!this.deviceName.equals(BuildConfig.VERSION_NAME)) {
            size += CodedOutputByteBufferNano.computeStringSize(2, this.deviceName);
        }
        if (!this.deviceModel.equals(BuildConfig.VERSION_NAME)) {
            size += CodedOutputByteBufferNano.computeStringSize(3, this.deviceModel);
        }
        if (!this.sdkVersion.equals(BuildConfig.VERSION_NAME)) {
            size += CodedOutputByteBufferNano.computeStringSize(4, this.sdkVersion);
        }
        if (!this.googlePlayServicesVersion.equals(BuildConfig.VERSION_NAME)) {
            size += CodedOutputByteBufferNano.computeStringSize(5, this.googlePlayServicesVersion);
        }
        if (!this.droidguardResults.equals(BuildConfig.VERSION_NAME)) {
            size += CodedOutputByteBufferNano.computeStringSize(6, this.droidguardResults);
        }
        if (this.userPresenceInfo != null) {
            return size + CodedOutputByteBufferNano.computeMessageSize(7, this.userPresenceInfo);
        }
        return size;
    }

    public DeviceSignalsInfo mergeFrom(CodedInputByteBufferNano input) throws IOException {
        while (true) {
            int tag = input.readTag();
            switch (tag) {
                case Action.UNKNOWN /*0*/:
                    break;
                case Type.TAP_ABOUT /*10*/:
                    this.deviceId = input.readString();
                    continue;
                case LogSource.GOOGLE_ANALYTICS /*18*/:
                    this.deviceName = input.readString();
                    continue;
                case LogSource.ANDROID_CAMERA /*26*/:
                    this.deviceModel = input.readString();
                    continue;
                case LogSource.NOVA /*34*/:
                    this.sdkVersion = input.readString();
                    continue;
                case LogSource.PHOTOS /*42*/:
                    this.googlePlayServicesVersion = input.readString();
                    continue;
                case LogSource.HANGOUT_LOG_REQUEST /*50*/:
                    this.droidguardResults = input.readString();
                    continue;
                case LogSource.SLIDES /*58*/:
                    if (this.userPresenceInfo == null) {
                        this.userPresenceInfo = new UserPresenceInfo();
                    }
                    input.readMessage(this.userPresenceInfo);
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

    public static DeviceSignalsInfo parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
        return (DeviceSignalsInfo) MessageNano.mergeFrom(new DeviceSignalsInfo(), data);
    }

    public static DeviceSignalsInfo parseFrom(CodedInputByteBufferNano input) throws IOException {
        return new DeviceSignalsInfo().mergeFrom(input);
    }
}
