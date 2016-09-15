package com.google.security.cryptauth.lib.risk;

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
import com.google.wireless.android.play.playlog.proto.UserLocation.OvenFreshEvent.OvenFreshResult;
import com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection;
import java.io.IOException;

public final class DeviceProperties extends ExtendableMessageNano<DeviceProperties> {
    private static volatile DeviceProperties[] _emptyArray;
    public long androidDeviceId;
    public String deviceManufacturer;
    public String deviceModel;
    public int deviceType;
    public long osMajorVersionCode;
    public long softwareMajorVersionCode;

    public static DeviceProperties[] emptyArray() {
        if (_emptyArray == null) {
            synchronized (InternalNano.LAZY_INIT_LOCK) {
                if (_emptyArray == null) {
                    _emptyArray = new DeviceProperties[0];
                }
            }
        }
        return _emptyArray;
    }

    public DeviceProperties() {
        clear();
    }

    public DeviceProperties clear() {
        this.deviceType = 0;
        this.androidDeviceId = 0;
        this.deviceModel = BuildConfig.VERSION_NAME;
        this.deviceManufacturer = BuildConfig.VERSION_NAME;
        this.osMajorVersionCode = 0;
        this.softwareMajorVersionCode = 0;
        this.unknownFieldData = null;
        this.cachedSize = -1;
        return this;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof DeviceProperties)) {
            return false;
        }
        DeviceProperties other = (DeviceProperties) o;
        if (this.deviceType != other.deviceType || this.androidDeviceId != other.androidDeviceId) {
            return false;
        }
        if (this.deviceModel == null) {
            if (other.deviceModel != null) {
                return false;
            }
        } else if (!this.deviceModel.equals(other.deviceModel)) {
            return false;
        }
        if (this.deviceManufacturer == null) {
            if (other.deviceManufacturer != null) {
                return false;
            }
        } else if (!this.deviceManufacturer.equals(other.deviceManufacturer)) {
            return false;
        }
        if (this.osMajorVersionCode != other.osMajorVersionCode || this.softwareMajorVersionCode != other.softwareMajorVersionCode) {
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
        int hashCode = (((((((((((((getClass().getName().hashCode() + 527) * 31) + this.deviceType) * 31) + ((int) (this.androidDeviceId ^ (this.androidDeviceId >>> 32)))) * 31) + (this.deviceModel == null ? 0 : this.deviceModel.hashCode())) * 31) + (this.deviceManufacturer == null ? 0 : this.deviceManufacturer.hashCode())) * 31) + ((int) (this.osMajorVersionCode ^ (this.osMajorVersionCode >>> 32)))) * 31) + ((int) (this.softwareMajorVersionCode ^ (this.softwareMajorVersionCode >>> 32)))) * 31;
        if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
            i = this.unknownFieldData.hashCode();
        }
        return hashCode + i;
    }

    public void writeTo(CodedOutputByteBufferNano output) throws IOException {
        if (this.deviceType != 0) {
            output.writeInt32(1, this.deviceType);
        }
        if (this.androidDeviceId != 0) {
            output.writeFixed64(2, this.androidDeviceId);
        }
        if (!this.deviceModel.equals(BuildConfig.VERSION_NAME)) {
            output.writeString(8, this.deviceModel);
        }
        if (!this.deviceManufacturer.equals(BuildConfig.VERSION_NAME)) {
            output.writeString(9, this.deviceManufacturer);
        }
        if (this.osMajorVersionCode != 0) {
            output.writeInt64(10, this.osMajorVersionCode);
        }
        if (this.softwareMajorVersionCode != 0) {
            output.writeInt64(16, this.softwareMajorVersionCode);
        }
        super.writeTo(output);
    }

    protected int computeSerializedSize() {
        int size = super.computeSerializedSize();
        if (this.deviceType != 0) {
            size += CodedOutputByteBufferNano.computeInt32Size(1, this.deviceType);
        }
        if (this.androidDeviceId != 0) {
            size += CodedOutputByteBufferNano.computeFixed64Size(2, this.androidDeviceId);
        }
        if (!this.deviceModel.equals(BuildConfig.VERSION_NAME)) {
            size += CodedOutputByteBufferNano.computeStringSize(8, this.deviceModel);
        }
        if (!this.deviceManufacturer.equals(BuildConfig.VERSION_NAME)) {
            size += CodedOutputByteBufferNano.computeStringSize(9, this.deviceManufacturer);
        }
        if (this.osMajorVersionCode != 0) {
            size += CodedOutputByteBufferNano.computeInt64Size(10, this.osMajorVersionCode);
        }
        if (this.softwareMajorVersionCode != 0) {
            return size + CodedOutputByteBufferNano.computeInt64Size(16, this.softwareMajorVersionCode);
        }
        return size;
    }

    public DeviceProperties mergeFrom(CodedInputByteBufferNano input) throws IOException {
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
                        case OvenFreshResult.NO_ACCOUNTS /*4*/:
                            this.deviceType = value;
                            break;
                        default:
                            continue;
                    }
                case LogSource.LE /*17*/:
                    this.androidDeviceId = input.readFixed64();
                    continue;
                case LogSource.WIFI_ASSISTANT /*66*/:
                    this.deviceModel = input.readString();
                    continue;
                case LogSource.MOVIES /*74*/:
                    this.deviceManufacturer = input.readString();
                    continue;
                case LogSource.CRONET_ANDROID_GSA /*80*/:
                    this.osMajorVersionCode = input.readInt64();
                    continue;
                case LogSource.KEEP /*128*/:
                    this.softwareMajorVersionCode = input.readInt64();
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

    public static DeviceProperties parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
        return (DeviceProperties) MessageNano.mergeFrom(new DeviceProperties(), data);
    }

    public static DeviceProperties parseFrom(CodedInputByteBufferNano input) throws IOException {
        return new DeviceProperties().mergeFrom(input);
    }
}
