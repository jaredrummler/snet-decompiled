package com.google.kidsmanagement.v1;

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
import com.google.wireless.android.play.playlog.proto.UserLocation.OvenFreshEvent.OvenFreshResult;
import com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection;
import java.io.IOException;
import java.util.Arrays;

public interface DevicesCommonProto {

    public static final class AndroidBuildInfo extends ExtendableMessageNano<AndroidBuildInfo> {
        private static volatile AndroidBuildInfo[] _emptyArray;
        public String fingerprint;
        public String id;
        public String type;

        public static AndroidBuildInfo[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new AndroidBuildInfo[0];
                    }
                }
            }
            return _emptyArray;
        }

        public AndroidBuildInfo() {
            clear();
        }

        public AndroidBuildInfo clear() {
            this.type = BuildConfig.VERSION_NAME;
            this.id = BuildConfig.VERSION_NAME;
            this.fingerprint = BuildConfig.VERSION_NAME;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof AndroidBuildInfo)) {
                return false;
            }
            AndroidBuildInfo other = (AndroidBuildInfo) o;
            if (this.type == null) {
                if (other.type != null) {
                    return false;
                }
            } else if (!this.type.equals(other.type)) {
                return false;
            }
            if (this.id == null) {
                if (other.id != null) {
                    return false;
                }
            } else if (!this.id.equals(other.id)) {
                return false;
            }
            if (this.fingerprint == null) {
                if (other.fingerprint != null) {
                    return false;
                }
            } else if (!this.fingerprint.equals(other.fingerprint)) {
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
            int hashCode = (((((((getClass().getName().hashCode() + 527) * 31) + (this.type == null ? 0 : this.type.hashCode())) * 31) + (this.id == null ? 0 : this.id.hashCode())) * 31) + (this.fingerprint == null ? 0 : this.fingerprint.hashCode())) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (!this.type.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(1, this.type);
            }
            if (!this.id.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(2, this.id);
            }
            if (!this.fingerprint.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(3, this.fingerprint);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (!this.type.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.type);
            }
            if (!this.id.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(2, this.id);
            }
            if (this.fingerprint.equals(BuildConfig.VERSION_NAME)) {
                return size;
            }
            return size + CodedOutputByteBufferNano.computeStringSize(3, this.fingerprint);
        }

        public AndroidBuildInfo mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        this.type = input.readString();
                        continue;
                    case LogSource.GOOGLE_ANALYTICS /*18*/:
                        this.id = input.readString();
                        continue;
                    case LogSource.ANDROID_CAMERA /*26*/:
                        this.fingerprint = input.readString();
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

        public static AndroidBuildInfo parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (AndroidBuildInfo) MessageNano.mergeFrom(new AndroidBuildInfo(), data);
        }

        public static AndroidBuildInfo parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new AndroidBuildInfo().mergeFrom(input);
        }
    }

    public interface Capability {
        public static final int CAPABILITY_ACCOUNT_MANAGEMENT = 6;
        public static final int CAPABILITY_BEDTIME = 1;
        public static final int CAPABILITY_DISABLE_APP = 3;
        public static final int CAPABILITY_LOCKBOX = 5;
        public static final int CAPABILITY_SMS = 102;
        public static final int CAPABILITY_TIMEOUT = 2;
        public static final int CAPABILITY_USERMANAGER_LOCKDOWN = 4;
        public static final int CAPABILITY_VOICE = 101;
        public static final int UNSPECIFIED_CAPABILITY = 0;
    }

    public static final class CapabilityInfo extends ExtendableMessageNano<CapabilityInfo> {
        private static volatile CapabilityInfo[] _emptyArray;
        public int[] capabilities;

        public static CapabilityInfo[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new CapabilityInfo[0];
                    }
                }
            }
            return _emptyArray;
        }

        public CapabilityInfo() {
            clear();
        }

        public CapabilityInfo clear() {
            this.capabilities = WireFormatNano.EMPTY_INT_ARRAY;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof CapabilityInfo)) {
                return false;
            }
            CapabilityInfo other = (CapabilityInfo) o;
            if (!InternalNano.equals(this.capabilities, other.capabilities)) {
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
            int hashCode = (((getClass().getName().hashCode() + 527) * 31) + InternalNano.hashCode(this.capabilities)) * 31;
            int hashCode2 = (this.unknownFieldData == null || this.unknownFieldData.isEmpty()) ? 0 : this.unknownFieldData.hashCode();
            return hashCode + hashCode2;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.capabilities != null && this.capabilities.length > 0) {
                for (int writeInt32 : this.capabilities) {
                    output.writeInt32(1, writeInt32);
                }
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.capabilities == null || this.capabilities.length <= 0) {
                return size;
            }
            int dataSize = 0;
            for (int element : this.capabilities) {
                dataSize += CodedOutputByteBufferNano.computeInt32SizeNoTag(element);
            }
            return (size + dataSize) + (this.capabilities.length * 1);
        }

        public CapabilityInfo mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                int i;
                int value;
                int[] newArray;
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_GET_LINK /*8*/:
                        int length = WireFormatNano.getRepeatedFieldArrayLength(input, 8);
                        int[] validValues = new int[length];
                        i = 0;
                        int validCount = 0;
                        while (i < length) {
                            int validCount2;
                            if (i != 0) {
                                input.readTag();
                            }
                            value = input.readInt32();
                            switch (value) {
                                case Action.UNKNOWN /*0*/:
                                case TimeSelection.Type.TEMPORARY /*1*/:
                                case TimeSelection.Type.INDEFINITELY /*2*/:
                                case TimeSelection.Type.CUSTOM /*3*/:
                                case OvenFreshResult.NO_ACCOUNTS /*4*/:
                                case Type.ADD_NEW_SHARES /*5*/:
                                case Type.REMOVE_SHARE /*6*/:
                                case LogSource.SOCIAL_USER_LOCATION /*101*/:
                                case LogSource.LAUNCHPAD_TOYS /*102*/:
                                    validCount2 = validCount + 1;
                                    validValues[validCount] = value;
                                    break;
                                default:
                                    validCount2 = validCount;
                                    break;
                            }
                            i++;
                            validCount = validCount2;
                        }
                        if (validCount != 0) {
                            i = this.capabilities == null ? 0 : this.capabilities.length;
                            if (i != 0 || validCount != validValues.length) {
                                newArray = new int[(i + validCount)];
                                if (i != 0) {
                                    System.arraycopy(this.capabilities, 0, newArray, 0, i);
                                }
                                System.arraycopy(validValues, 0, newArray, i, validCount);
                                this.capabilities = newArray;
                                break;
                            }
                            this.capabilities = validValues;
                            break;
                        }
                        continue;
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        int limit = input.pushLimit(input.readRawVarint32());
                        int arrayLength = 0;
                        int startPos = input.getPosition();
                        while (input.getBytesUntilLimit() > 0) {
                            switch (input.readInt32()) {
                                case Action.UNKNOWN /*0*/:
                                case TimeSelection.Type.TEMPORARY /*1*/:
                                case TimeSelection.Type.INDEFINITELY /*2*/:
                                case TimeSelection.Type.CUSTOM /*3*/:
                                case OvenFreshResult.NO_ACCOUNTS /*4*/:
                                case Type.ADD_NEW_SHARES /*5*/:
                                case Type.REMOVE_SHARE /*6*/:
                                case LogSource.SOCIAL_USER_LOCATION /*101*/:
                                case LogSource.LAUNCHPAD_TOYS /*102*/:
                                    arrayLength++;
                                    break;
                                default:
                                    break;
                            }
                        }
                        if (arrayLength != 0) {
                            input.rewindToPosition(startPos);
                            if (this.capabilities == null) {
                                i = 0;
                            } else {
                                i = this.capabilities.length;
                            }
                            newArray = new int[(i + arrayLength)];
                            if (i != 0) {
                                System.arraycopy(this.capabilities, 0, newArray, 0, i);
                            }
                            while (input.getBytesUntilLimit() > 0) {
                                value = input.readInt32();
                                switch (value) {
                                    case Action.UNKNOWN /*0*/:
                                    case TimeSelection.Type.TEMPORARY /*1*/:
                                    case TimeSelection.Type.INDEFINITELY /*2*/:
                                    case TimeSelection.Type.CUSTOM /*3*/:
                                    case OvenFreshResult.NO_ACCOUNTS /*4*/:
                                    case Type.ADD_NEW_SHARES /*5*/:
                                    case Type.REMOVE_SHARE /*6*/:
                                    case LogSource.SOCIAL_USER_LOCATION /*101*/:
                                    case LogSource.LAUNCHPAD_TOYS /*102*/:
                                        int i2 = i + 1;
                                        newArray[i] = value;
                                        i = i2;
                                        break;
                                    default:
                                        break;
                                }
                            }
                            this.capabilities = newArray;
                        }
                        input.popLimit(limit);
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

        public static CapabilityInfo parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (CapabilityInfo) MessageNano.mergeFrom(new CapabilityInfo(), data);
        }

        public static CapabilityInfo parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new CapabilityInfo().mergeFrom(input);
        }
    }

    public static final class DecodedGcmRegistration extends ExtendableMessageNano<DecodedGcmRegistration> {
        private static volatile DecodedGcmRegistration[] _emptyArray;
        public String deprecatedTag2;
        public String deviceId;
        public long deviceUserId;
        public long senderId;
        public String subscription;

        public static DecodedGcmRegistration[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new DecodedGcmRegistration[0];
                    }
                }
            }
            return _emptyArray;
        }

        public DecodedGcmRegistration() {
            clear();
        }

        public DecodedGcmRegistration clear() {
            this.deviceId = BuildConfig.VERSION_NAME;
            this.senderId = 0;
            this.subscription = BuildConfig.VERSION_NAME;
            this.deviceUserId = 0;
            this.deprecatedTag2 = BuildConfig.VERSION_NAME;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof DecodedGcmRegistration)) {
                return false;
            }
            DecodedGcmRegistration other = (DecodedGcmRegistration) o;
            if (this.deviceId == null) {
                if (other.deviceId != null) {
                    return false;
                }
            } else if (!this.deviceId.equals(other.deviceId)) {
                return false;
            }
            if (this.senderId != other.senderId) {
                return false;
            }
            if (this.subscription == null) {
                if (other.subscription != null) {
                    return false;
                }
            } else if (!this.subscription.equals(other.subscription)) {
                return false;
            }
            if (this.deviceUserId != other.deviceUserId) {
                return false;
            }
            if (this.deprecatedTag2 == null) {
                if (other.deprecatedTag2 != null) {
                    return false;
                }
            } else if (!this.deprecatedTag2.equals(other.deprecatedTag2)) {
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
            int hashCode = (((((((((((getClass().getName().hashCode() + 527) * 31) + (this.deviceId == null ? 0 : this.deviceId.hashCode())) * 31) + ((int) (this.senderId ^ (this.senderId >>> 32)))) * 31) + (this.subscription == null ? 0 : this.subscription.hashCode())) * 31) + ((int) (this.deviceUserId ^ (this.deviceUserId >>> 32)))) * 31) + (this.deprecatedTag2 == null ? 0 : this.deprecatedTag2.hashCode())) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (!this.deviceId.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(1, this.deviceId);
            }
            if (!this.deprecatedTag2.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(2, this.deprecatedTag2);
            }
            if (this.senderId != 0) {
                output.writeInt64(3, this.senderId);
            }
            if (!this.subscription.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(4, this.subscription);
            }
            if (this.deviceUserId != 0) {
                output.writeInt64(5, this.deviceUserId);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (!this.deviceId.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.deviceId);
            }
            if (!this.deprecatedTag2.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(2, this.deprecatedTag2);
            }
            if (this.senderId != 0) {
                size += CodedOutputByteBufferNano.computeInt64Size(3, this.senderId);
            }
            if (!this.subscription.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(4, this.subscription);
            }
            if (this.deviceUserId != 0) {
                return size + CodedOutputByteBufferNano.computeInt64Size(5, this.deviceUserId);
            }
            return size;
        }

        public DecodedGcmRegistration mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        this.deviceId = input.readString();
                        continue;
                    case LogSource.GOOGLE_ANALYTICS /*18*/:
                        this.deprecatedTag2 = input.readString();
                        continue;
                    case LogSource.LB_C /*24*/:
                        this.senderId = input.readInt64();
                        continue;
                    case LogSource.NOVA /*34*/:
                        this.subscription = input.readString();
                        continue;
                    case LogSource.COPRESENCE /*40*/:
                        this.deviceUserId = input.readInt64();
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

        public static DecodedGcmRegistration parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (DecodedGcmRegistration) MessageNano.mergeFrom(new DecodedGcmRegistration(), data);
        }

        public static DecodedGcmRegistration parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new DecodedGcmRegistration().mergeFrom(input);
        }
    }

    public static final class Device extends ExtendableMessageNano<Device> {
        private static volatile Device[] _emptyArray;
        public CapabilityInfo capabilityInfo;
        public String deprecatedTag2;
        public String deprecatedTag3;
        public String deprecatedTag4;
        public String deprecatedTag5;
        public byte[] deprecatedTag6;
        public byte[] deprecatedTag7;
        public long deprecatedTag8;
        public String deviceId;
        public DeviceSettingsInfo deviceSettingsInfo;
        public DisplayInfo displayInfo;
        public long firstReportTimestampMillis;
        public InstalledAppInfo installedAppInfo;
        public PlatformInfo platformInfo;
        public long reportTimestampMillis;

        public static Device[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new Device[0];
                    }
                }
            }
            return _emptyArray;
        }

        public Device() {
            clear();
        }

        public Device clear() {
            this.deviceId = BuildConfig.VERSION_NAME;
            this.reportTimestampMillis = 0;
            this.firstReportTimestampMillis = 0;
            this.displayInfo = null;
            this.platformInfo = null;
            this.installedAppInfo = null;
            this.deviceSettingsInfo = null;
            this.capabilityInfo = null;
            this.deprecatedTag2 = BuildConfig.VERSION_NAME;
            this.deprecatedTag3 = BuildConfig.VERSION_NAME;
            this.deprecatedTag4 = BuildConfig.VERSION_NAME;
            this.deprecatedTag5 = BuildConfig.VERSION_NAME;
            this.deprecatedTag6 = WireFormatNano.EMPTY_BYTES;
            this.deprecatedTag7 = WireFormatNano.EMPTY_BYTES;
            this.deprecatedTag8 = 0;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof Device)) {
                return false;
            }
            Device other = (Device) o;
            if (this.deviceId == null) {
                if (other.deviceId != null) {
                    return false;
                }
            } else if (!this.deviceId.equals(other.deviceId)) {
                return false;
            }
            if (this.reportTimestampMillis != other.reportTimestampMillis || this.firstReportTimestampMillis != other.firstReportTimestampMillis) {
                return false;
            }
            if (this.displayInfo == null) {
                if (other.displayInfo != null) {
                    return false;
                }
            } else if (!this.displayInfo.equals(other.displayInfo)) {
                return false;
            }
            if (this.platformInfo == null) {
                if (other.platformInfo != null) {
                    return false;
                }
            } else if (!this.platformInfo.equals(other.platformInfo)) {
                return false;
            }
            if (this.installedAppInfo == null) {
                if (other.installedAppInfo != null) {
                    return false;
                }
            } else if (!this.installedAppInfo.equals(other.installedAppInfo)) {
                return false;
            }
            if (this.deviceSettingsInfo == null) {
                if (other.deviceSettingsInfo != null) {
                    return false;
                }
            } else if (!this.deviceSettingsInfo.equals(other.deviceSettingsInfo)) {
                return false;
            }
            if (this.capabilityInfo == null) {
                if (other.capabilityInfo != null) {
                    return false;
                }
            } else if (!this.capabilityInfo.equals(other.capabilityInfo)) {
                return false;
            }
            if (this.deprecatedTag2 == null) {
                if (other.deprecatedTag2 != null) {
                    return false;
                }
            } else if (!this.deprecatedTag2.equals(other.deprecatedTag2)) {
                return false;
            }
            if (this.deprecatedTag3 == null) {
                if (other.deprecatedTag3 != null) {
                    return false;
                }
            } else if (!this.deprecatedTag3.equals(other.deprecatedTag3)) {
                return false;
            }
            if (this.deprecatedTag4 == null) {
                if (other.deprecatedTag4 != null) {
                    return false;
                }
            } else if (!this.deprecatedTag4.equals(other.deprecatedTag4)) {
                return false;
            }
            if (this.deprecatedTag5 == null) {
                if (other.deprecatedTag5 != null) {
                    return false;
                }
            } else if (!this.deprecatedTag5.equals(other.deprecatedTag5)) {
                return false;
            }
            if (!Arrays.equals(this.deprecatedTag6, other.deprecatedTag6) || !Arrays.equals(this.deprecatedTag7, other.deprecatedTag7) || this.deprecatedTag8 != other.deprecatedTag8) {
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
            int hashCode = (((((((((((((((((((((((((((((((getClass().getName().hashCode() + 527) * 31) + (this.deviceId == null ? 0 : this.deviceId.hashCode())) * 31) + ((int) (this.reportTimestampMillis ^ (this.reportTimestampMillis >>> 32)))) * 31) + ((int) (this.firstReportTimestampMillis ^ (this.firstReportTimestampMillis >>> 32)))) * 31) + (this.displayInfo == null ? 0 : this.displayInfo.hashCode())) * 31) + (this.platformInfo == null ? 0 : this.platformInfo.hashCode())) * 31) + (this.installedAppInfo == null ? 0 : this.installedAppInfo.hashCode())) * 31) + (this.deviceSettingsInfo == null ? 0 : this.deviceSettingsInfo.hashCode())) * 31) + (this.capabilityInfo == null ? 0 : this.capabilityInfo.hashCode())) * 31) + (this.deprecatedTag2 == null ? 0 : this.deprecatedTag2.hashCode())) * 31) + (this.deprecatedTag3 == null ? 0 : this.deprecatedTag3.hashCode())) * 31) + (this.deprecatedTag4 == null ? 0 : this.deprecatedTag4.hashCode())) * 31) + (this.deprecatedTag5 == null ? 0 : this.deprecatedTag5.hashCode())) * 31) + Arrays.hashCode(this.deprecatedTag6)) * 31) + Arrays.hashCode(this.deprecatedTag7)) * 31) + ((int) (this.deprecatedTag8 ^ (this.deprecatedTag8 >>> 32)))) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (!this.deviceId.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(1, this.deviceId);
            }
            if (!this.deprecatedTag2.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(2, this.deprecatedTag2);
            }
            if (!this.deprecatedTag3.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(3, this.deprecatedTag3);
            }
            if (!this.deprecatedTag4.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(4, this.deprecatedTag4);
            }
            if (!this.deprecatedTag5.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(5, this.deprecatedTag5);
            }
            if (!Arrays.equals(this.deprecatedTag6, WireFormatNano.EMPTY_BYTES)) {
                output.writeBytes(6, this.deprecatedTag6);
            }
            if (!Arrays.equals(this.deprecatedTag7, WireFormatNano.EMPTY_BYTES)) {
                output.writeBytes(7, this.deprecatedTag7);
            }
            if (this.deprecatedTag8 != 0) {
                output.writeInt64(8, this.deprecatedTag8);
            }
            if (this.displayInfo != null) {
                output.writeMessage(9, this.displayInfo);
            }
            if (this.platformInfo != null) {
                output.writeMessage(10, this.platformInfo);
            }
            if (this.installedAppInfo != null) {
                output.writeMessage(11, this.installedAppInfo);
            }
            if (this.deviceSettingsInfo != null) {
                output.writeMessage(12, this.deviceSettingsInfo);
            }
            if (this.capabilityInfo != null) {
                output.writeMessage(13, this.capabilityInfo);
            }
            if (this.reportTimestampMillis != 0) {
                output.writeInt64(14, this.reportTimestampMillis);
            }
            if (this.firstReportTimestampMillis != 0) {
                output.writeInt64(15, this.firstReportTimestampMillis);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (!this.deviceId.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.deviceId);
            }
            if (!this.deprecatedTag2.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(2, this.deprecatedTag2);
            }
            if (!this.deprecatedTag3.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(3, this.deprecatedTag3);
            }
            if (!this.deprecatedTag4.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(4, this.deprecatedTag4);
            }
            if (!this.deprecatedTag5.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(5, this.deprecatedTag5);
            }
            if (!Arrays.equals(this.deprecatedTag6, WireFormatNano.EMPTY_BYTES)) {
                size += CodedOutputByteBufferNano.computeBytesSize(6, this.deprecatedTag6);
            }
            if (!Arrays.equals(this.deprecatedTag7, WireFormatNano.EMPTY_BYTES)) {
                size += CodedOutputByteBufferNano.computeBytesSize(7, this.deprecatedTag7);
            }
            if (this.deprecatedTag8 != 0) {
                size += CodedOutputByteBufferNano.computeInt64Size(8, this.deprecatedTag8);
            }
            if (this.displayInfo != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(9, this.displayInfo);
            }
            if (this.platformInfo != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(10, this.platformInfo);
            }
            if (this.installedAppInfo != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(11, this.installedAppInfo);
            }
            if (this.deviceSettingsInfo != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(12, this.deviceSettingsInfo);
            }
            if (this.capabilityInfo != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(13, this.capabilityInfo);
            }
            if (this.reportTimestampMillis != 0) {
                size += CodedOutputByteBufferNano.computeInt64Size(14, this.reportTimestampMillis);
            }
            if (this.firstReportTimestampMillis != 0) {
                return size + CodedOutputByteBufferNano.computeInt64Size(15, this.firstReportTimestampMillis);
            }
            return size;
        }

        public Device mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        this.deviceId = input.readString();
                        continue;
                    case LogSource.GOOGLE_ANALYTICS /*18*/:
                        this.deprecatedTag2 = input.readString();
                        continue;
                    case LogSource.ANDROID_CAMERA /*26*/:
                        this.deprecatedTag3 = input.readString();
                        continue;
                    case LogSource.NOVA /*34*/:
                        this.deprecatedTag4 = input.readString();
                        continue;
                    case LogSource.PHOTOS /*42*/:
                        this.deprecatedTag5 = input.readString();
                        continue;
                    case LogSource.HANGOUT_LOG_REQUEST /*50*/:
                        this.deprecatedTag6 = input.readBytes();
                        continue;
                    case LogSource.SLIDES /*58*/:
                        this.deprecatedTag7 = input.readBytes();
                        continue;
                    case LogSource.KIDS_COMMUNICATOR /*64*/:
                        this.deprecatedTag8 = input.readInt64();
                        continue;
                    case LogSource.MOVIES /*74*/:
                        if (this.displayInfo == null) {
                            this.displayInfo = new DisplayInfo();
                        }
                        input.readMessage(this.displayInfo);
                        continue;
                    case LogSource.GOOGLE_EXPRESS /*82*/:
                        if (this.platformInfo == null) {
                            this.platformInfo = new PlatformInfo();
                        }
                        input.readMessage(this.platformInfo);
                        continue;
                    case LogSource.TRON_COUNTERS /*90*/:
                        if (this.installedAppInfo == null) {
                            this.installedAppInfo = new InstalledAppInfo();
                        }
                        input.readMessage(this.installedAppInfo);
                        continue;
                    case LogSource.TACHYON_LOG_REQUEST /*98*/:
                        if (this.deviceSettingsInfo == null) {
                            this.deviceSettingsInfo = new DeviceSettingsInfo();
                        }
                        input.readMessage(this.deviceSettingsInfo);
                        continue;
                    case LogSource.ADSHIELD /*106*/:
                        if (this.capabilityInfo == null) {
                            this.capabilityInfo = new CapabilityInfo();
                        }
                        input.readMessage(this.capabilityInfo);
                        continue;
                    case LogSource.ENDER /*112*/:
                        this.reportTimestampMillis = input.readInt64();
                        continue;
                    case LogSource.FLYDROID /*120*/:
                        this.firstReportTimestampMillis = input.readInt64();
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

        public static Device parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (Device) MessageNano.mergeFrom(new Device(), data);
        }

        public static Device parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new Device().mergeFrom(input);
        }
    }

    public static final class DeviceLocationSettings extends ExtendableMessageNano<DeviceLocationSettings> {
        private static volatile DeviceLocationSettings[] _emptyArray;
        public boolean deprecatedTag1;
        public boolean deprecatedTag3;
        public boolean deprecatedTag4;
        public int locationMode;
        public long locationModeTimestampMillis;

        public static DeviceLocationSettings[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new DeviceLocationSettings[0];
                    }
                }
            }
            return _emptyArray;
        }

        public DeviceLocationSettings() {
            clear();
        }

        public DeviceLocationSettings clear() {
            this.locationMode = 0;
            this.locationModeTimestampMillis = 0;
            this.deprecatedTag1 = false;
            this.deprecatedTag3 = false;
            this.deprecatedTag4 = false;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof DeviceLocationSettings)) {
                return false;
            }
            DeviceLocationSettings other = (DeviceLocationSettings) o;
            if (this.locationMode != other.locationMode || this.locationModeTimestampMillis != other.locationModeTimestampMillis || this.deprecatedTag1 != other.deprecatedTag1 || this.deprecatedTag3 != other.deprecatedTag3 || this.deprecatedTag4 != other.deprecatedTag4) {
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
            int hashCode = (((((((getClass().getName().hashCode() + 527) * 31) + this.locationMode) * 31) + ((int) (this.locationModeTimestampMillis ^ (this.locationModeTimestampMillis >>> 32)))) * 31) + (this.deprecatedTag1 ? 1231 : 1237)) * 31;
            if (this.deprecatedTag3) {
                i = 1231;
            } else {
                i = 1237;
            }
            i = (hashCode + i) * 31;
            if (!this.deprecatedTag4) {
                i2 = 1237;
            }
            i2 = (i + i2) * 31;
            i = (this.unknownFieldData == null || this.unknownFieldData.isEmpty()) ? 0 : this.unknownFieldData.hashCode();
            return i2 + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.deprecatedTag1) {
                output.writeBool(1, this.deprecatedTag1);
            }
            if (this.locationMode != 0) {
                output.writeInt32(2, this.locationMode);
            }
            if (this.deprecatedTag3) {
                output.writeBool(3, this.deprecatedTag3);
            }
            if (this.deprecatedTag4) {
                output.writeBool(4, this.deprecatedTag4);
            }
            if (this.locationModeTimestampMillis != 0) {
                output.writeInt64(5, this.locationModeTimestampMillis);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.deprecatedTag1) {
                size += CodedOutputByteBufferNano.computeBoolSize(1, this.deprecatedTag1);
            }
            if (this.locationMode != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(2, this.locationMode);
            }
            if (this.deprecatedTag3) {
                size += CodedOutputByteBufferNano.computeBoolSize(3, this.deprecatedTag3);
            }
            if (this.deprecatedTag4) {
                size += CodedOutputByteBufferNano.computeBoolSize(4, this.deprecatedTag4);
            }
            if (this.locationModeTimestampMillis != 0) {
                return size + CodedOutputByteBufferNano.computeInt64Size(5, this.locationModeTimestampMillis);
            }
            return size;
        }

        public DeviceLocationSettings mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_GET_LINK /*8*/:
                        this.deprecatedTag1 = input.readBool();
                        continue;
                    case LogSource.GMS_CORE_PEOPLE /*16*/:
                        this.locationMode = input.readInt32();
                        continue;
                    case LogSource.LB_C /*24*/:
                        this.deprecatedTag3 = input.readBool();
                        continue;
                    case LogSource.SOCIAL /*32*/:
                        this.deprecatedTag4 = input.readBool();
                        continue;
                    case LogSource.COPRESENCE /*40*/:
                        this.locationModeTimestampMillis = input.readInt64();
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

        public static DeviceLocationSettings parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (DeviceLocationSettings) MessageNano.mergeFrom(new DeviceLocationSettings(), data);
        }

        public static DeviceLocationSettings parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new DeviceLocationSettings().mergeFrom(input);
        }
    }

    public static final class DeviceSettingsInfo extends ExtendableMessageNano<DeviceSettingsInfo> {
        private static volatile DeviceSettingsInfo[] _emptyArray;
        public DeviceLocationSettings deviceLocationSettings;
        public GcmInfo gcmInfo;
        public String[] googleAccountNames;
        public String timezone;

        public static DeviceSettingsInfo[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new DeviceSettingsInfo[0];
                    }
                }
            }
            return _emptyArray;
        }

        public DeviceSettingsInfo() {
            clear();
        }

        public DeviceSettingsInfo clear() {
            this.timezone = BuildConfig.VERSION_NAME;
            this.googleAccountNames = WireFormatNano.EMPTY_STRING_ARRAY;
            this.deviceLocationSettings = null;
            this.gcmInfo = null;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof DeviceSettingsInfo)) {
                return false;
            }
            DeviceSettingsInfo other = (DeviceSettingsInfo) o;
            if (this.timezone == null) {
                if (other.timezone != null) {
                    return false;
                }
            } else if (!this.timezone.equals(other.timezone)) {
                return false;
            }
            if (!InternalNano.equals(this.googleAccountNames, other.googleAccountNames)) {
                return false;
            }
            if (this.deviceLocationSettings == null) {
                if (other.deviceLocationSettings != null) {
                    return false;
                }
            } else if (!this.deviceLocationSettings.equals(other.deviceLocationSettings)) {
                return false;
            }
            if (this.gcmInfo == null) {
                if (other.gcmInfo != null) {
                    return false;
                }
            } else if (!this.gcmInfo.equals(other.gcmInfo)) {
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
            int hashCode = (((((((((getClass().getName().hashCode() + 527) * 31) + (this.timezone == null ? 0 : this.timezone.hashCode())) * 31) + InternalNano.hashCode(this.googleAccountNames)) * 31) + (this.deviceLocationSettings == null ? 0 : this.deviceLocationSettings.hashCode())) * 31) + (this.gcmInfo == null ? 0 : this.gcmInfo.hashCode())) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (!this.timezone.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(1, this.timezone);
            }
            if (this.googleAccountNames != null && this.googleAccountNames.length > 0) {
                for (String element : this.googleAccountNames) {
                    if (element != null) {
                        output.writeString(2, element);
                    }
                }
            }
            if (this.deviceLocationSettings != null) {
                output.writeMessage(3, this.deviceLocationSettings);
            }
            if (this.gcmInfo != null) {
                output.writeMessage(4, this.gcmInfo);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (!this.timezone.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.timezone);
            }
            if (this.googleAccountNames != null && this.googleAccountNames.length > 0) {
                int dataCount = 0;
                int dataSize = 0;
                for (String element : this.googleAccountNames) {
                    if (element != null) {
                        dataCount++;
                        dataSize += CodedOutputByteBufferNano.computeStringSizeNoTag(element);
                    }
                }
                size = (size + dataSize) + (dataCount * 1);
            }
            if (this.deviceLocationSettings != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(3, this.deviceLocationSettings);
            }
            if (this.gcmInfo != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(4, this.gcmInfo);
            }
            return size;
        }

        public DeviceSettingsInfo mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        this.timezone = input.readString();
                        continue;
                    case LogSource.GOOGLE_ANALYTICS /*18*/:
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 18);
                        int i = this.googleAccountNames == null ? 0 : this.googleAccountNames.length;
                        String[] newArray = new String[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.googleAccountNames, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = input.readString();
                            input.readTag();
                            i++;
                        }
                        newArray[i] = input.readString();
                        this.googleAccountNames = newArray;
                        continue;
                    case LogSource.ANDROID_CAMERA /*26*/:
                        if (this.deviceLocationSettings == null) {
                            this.deviceLocationSettings = new DeviceLocationSettings();
                        }
                        input.readMessage(this.deviceLocationSettings);
                        continue;
                    case LogSource.NOVA /*34*/:
                        if (this.gcmInfo == null) {
                            this.gcmInfo = new GcmInfo();
                        }
                        input.readMessage(this.gcmInfo);
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

        public static DeviceSettingsInfo parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (DeviceSettingsInfo) MessageNano.mergeFrom(new DeviceSettingsInfo(), data);
        }

        public static DeviceSettingsInfo parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new DeviceSettingsInfo().mergeFrom(input);
        }
    }

    public static final class DisplayInfo extends ExtendableMessageNano<DisplayInfo> {
        private static volatile DisplayInfo[] _emptyArray;
        public String carrier;
        public String friendlyName;
        public long lastActivityTimeMillis;
        public String manufacturer;
        public String model;
        public long stableHardwareId;
        public Image thumbnail;

        public static DisplayInfo[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new DisplayInfo[0];
                    }
                }
            }
            return _emptyArray;
        }

        public DisplayInfo() {
            clear();
        }

        public DisplayInfo clear() {
            this.manufacturer = BuildConfig.VERSION_NAME;
            this.carrier = BuildConfig.VERSION_NAME;
            this.model = BuildConfig.VERSION_NAME;
            this.friendlyName = BuildConfig.VERSION_NAME;
            this.stableHardwareId = 0;
            this.thumbnail = null;
            this.lastActivityTimeMillis = 0;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof DisplayInfo)) {
                return false;
            }
            DisplayInfo other = (DisplayInfo) o;
            if (this.manufacturer == null) {
                if (other.manufacturer != null) {
                    return false;
                }
            } else if (!this.manufacturer.equals(other.manufacturer)) {
                return false;
            }
            if (this.carrier == null) {
                if (other.carrier != null) {
                    return false;
                }
            } else if (!this.carrier.equals(other.carrier)) {
                return false;
            }
            if (this.model == null) {
                if (other.model != null) {
                    return false;
                }
            } else if (!this.model.equals(other.model)) {
                return false;
            }
            if (this.friendlyName == null) {
                if (other.friendlyName != null) {
                    return false;
                }
            } else if (!this.friendlyName.equals(other.friendlyName)) {
                return false;
            }
            if (this.stableHardwareId != other.stableHardwareId) {
                return false;
            }
            if (this.thumbnail == null) {
                if (other.thumbnail != null) {
                    return false;
                }
            } else if (!this.thumbnail.equals(other.thumbnail)) {
                return false;
            }
            if (this.lastActivityTimeMillis != other.lastActivityTimeMillis) {
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
            int hashCode = (((((((((((((((getClass().getName().hashCode() + 527) * 31) + (this.manufacturer == null ? 0 : this.manufacturer.hashCode())) * 31) + (this.carrier == null ? 0 : this.carrier.hashCode())) * 31) + (this.model == null ? 0 : this.model.hashCode())) * 31) + (this.friendlyName == null ? 0 : this.friendlyName.hashCode())) * 31) + ((int) (this.stableHardwareId ^ (this.stableHardwareId >>> 32)))) * 31) + (this.thumbnail == null ? 0 : this.thumbnail.hashCode())) * 31) + ((int) (this.lastActivityTimeMillis ^ (this.lastActivityTimeMillis >>> 32)))) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (!this.manufacturer.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(1, this.manufacturer);
            }
            if (!this.carrier.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(2, this.carrier);
            }
            if (!this.model.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(3, this.model);
            }
            if (!this.friendlyName.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(4, this.friendlyName);
            }
            if (this.stableHardwareId != 0) {
                output.writeInt64(5, this.stableHardwareId);
            }
            if (this.thumbnail != null) {
                output.writeMessage(6, this.thumbnail);
            }
            if (this.lastActivityTimeMillis != 0) {
                output.writeInt64(7, this.lastActivityTimeMillis);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (!this.manufacturer.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.manufacturer);
            }
            if (!this.carrier.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(2, this.carrier);
            }
            if (!this.model.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(3, this.model);
            }
            if (!this.friendlyName.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(4, this.friendlyName);
            }
            if (this.stableHardwareId != 0) {
                size += CodedOutputByteBufferNano.computeInt64Size(5, this.stableHardwareId);
            }
            if (this.thumbnail != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(6, this.thumbnail);
            }
            if (this.lastActivityTimeMillis != 0) {
                return size + CodedOutputByteBufferNano.computeInt64Size(7, this.lastActivityTimeMillis);
            }
            return size;
        }

        public DisplayInfo mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        this.manufacturer = input.readString();
                        continue;
                    case LogSource.GOOGLE_ANALYTICS /*18*/:
                        this.carrier = input.readString();
                        continue;
                    case LogSource.ANDROID_CAMERA /*26*/:
                        this.model = input.readString();
                        continue;
                    case LogSource.NOVA /*34*/:
                        this.friendlyName = input.readString();
                        continue;
                    case LogSource.COPRESENCE /*40*/:
                        this.stableHardwareId = input.readInt64();
                        continue;
                    case LogSource.HANGOUT_LOG_REQUEST /*50*/:
                        if (this.thumbnail == null) {
                            this.thumbnail = new Image();
                        }
                        input.readMessage(this.thumbnail);
                        continue;
                    case LogSource.DOCS /*56*/:
                        this.lastActivityTimeMillis = input.readInt64();
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

        public static DisplayInfo parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (DisplayInfo) MessageNano.mergeFrom(new DisplayInfo(), data);
        }

        public static DisplayInfo parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new DisplayInfo().mergeFrom(input);
        }
    }

    public static final class GcmInfo extends ExtendableMessageNano<GcmInfo> {
        private static volatile GcmInfo[] _emptyArray;
        public DecodedGcmRegistration decodedGcmRegistration;
        public String gcmRegistrationId;
        public boolean valid;

        public static GcmInfo[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new GcmInfo[0];
                    }
                }
            }
            return _emptyArray;
        }

        public GcmInfo() {
            clear();
        }

        public GcmInfo clear() {
            this.gcmRegistrationId = BuildConfig.VERSION_NAME;
            this.decodedGcmRegistration = null;
            this.valid = false;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof GcmInfo)) {
                return false;
            }
            GcmInfo other = (GcmInfo) o;
            if (this.gcmRegistrationId == null) {
                if (other.gcmRegistrationId != null) {
                    return false;
                }
            } else if (!this.gcmRegistrationId.equals(other.gcmRegistrationId)) {
                return false;
            }
            if (this.decodedGcmRegistration == null) {
                if (other.decodedGcmRegistration != null) {
                    return false;
                }
            } else if (!this.decodedGcmRegistration.equals(other.decodedGcmRegistration)) {
                return false;
            }
            if (this.valid != other.valid) {
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
            int hashCode = (((((((getClass().getName().hashCode() + 527) * 31) + (this.gcmRegistrationId == null ? 0 : this.gcmRegistrationId.hashCode())) * 31) + (this.decodedGcmRegistration == null ? 0 : this.decodedGcmRegistration.hashCode())) * 31) + (this.valid ? 1231 : 1237)) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (!this.gcmRegistrationId.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(1, this.gcmRegistrationId);
            }
            if (this.valid) {
                output.writeBool(2, this.valid);
            }
            if (this.decodedGcmRegistration != null) {
                output.writeMessage(3, this.decodedGcmRegistration);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (!this.gcmRegistrationId.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.gcmRegistrationId);
            }
            if (this.valid) {
                size += CodedOutputByteBufferNano.computeBoolSize(2, this.valid);
            }
            if (this.decodedGcmRegistration != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(3, this.decodedGcmRegistration);
            }
            return size;
        }

        public GcmInfo mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        this.gcmRegistrationId = input.readString();
                        continue;
                    case LogSource.GMS_CORE_PEOPLE /*16*/:
                        this.valid = input.readBool();
                        continue;
                    case LogSource.ANDROID_CAMERA /*26*/:
                        if (this.decodedGcmRegistration == null) {
                            this.decodedGcmRegistration = new DecodedGcmRegistration();
                        }
                        input.readMessage(this.decodedGcmRegistration);
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

        public static GcmInfo parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (GcmInfo) MessageNano.mergeFrom(new GcmInfo(), data);
        }

        public static GcmInfo parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new GcmInfo().mergeFrom(input);
        }
    }

    public static final class GcoreInfo extends ExtendableMessageNano<GcoreInfo> {
        private static volatile GcoreInfo[] _emptyArray;
        public long buildNumber;
        public int buildType;
        public int versionCode;
        public String versionName;

        public static GcoreInfo[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new GcoreInfo[0];
                    }
                }
            }
            return _emptyArray;
        }

        public GcoreInfo() {
            clear();
        }

        public GcoreInfo clear() {
            this.versionName = BuildConfig.VERSION_NAME;
            this.versionCode = 0;
            this.buildNumber = 0;
            this.buildType = 0;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof GcoreInfo)) {
                return false;
            }
            GcoreInfo other = (GcoreInfo) o;
            if (this.versionName == null) {
                if (other.versionName != null) {
                    return false;
                }
            } else if (!this.versionName.equals(other.versionName)) {
                return false;
            }
            if (this.versionCode != other.versionCode || this.buildNumber != other.buildNumber || this.buildType != other.buildType) {
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
            int hashCode = (((((((((getClass().getName().hashCode() + 527) * 31) + (this.versionName == null ? 0 : this.versionName.hashCode())) * 31) + this.versionCode) * 31) + ((int) (this.buildNumber ^ (this.buildNumber >>> 32)))) * 31) + this.buildType) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (!this.versionName.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(1, this.versionName);
            }
            if (this.buildNumber != 0) {
                output.writeInt64(2, this.buildNumber);
            }
            if (this.buildType != 0) {
                output.writeInt32(3, this.buildType);
            }
            if (this.versionCode != 0) {
                output.writeInt32(4, this.versionCode);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (!this.versionName.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.versionName);
            }
            if (this.buildNumber != 0) {
                size += CodedOutputByteBufferNano.computeInt64Size(2, this.buildNumber);
            }
            if (this.buildType != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(3, this.buildType);
            }
            if (this.versionCode != 0) {
                return size + CodedOutputByteBufferNano.computeInt32Size(4, this.versionCode);
            }
            return size;
        }

        public GcoreInfo mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        this.versionName = input.readString();
                        continue;
                    case LogSource.GMS_CORE_PEOPLE /*16*/:
                        this.buildNumber = input.readInt64();
                        continue;
                    case LogSource.LB_C /*24*/:
                        this.buildType = input.readInt32();
                        continue;
                    case LogSource.SOCIAL /*32*/:
                        this.versionCode = input.readInt32();
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

        public static GcoreInfo parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (GcoreInfo) MessageNano.mergeFrom(new GcoreInfo(), data);
        }

        public static GcoreInfo parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new GcoreInfo().mergeFrom(input);
        }
    }

    public static final class Image extends ExtendableMessageNano<Image> {
        private static volatile Image[] _emptyArray;
        public String imageUrl;

        public static Image[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new Image[0];
                    }
                }
            }
            return _emptyArray;
        }

        public Image() {
            clear();
        }

        public Image clear() {
            this.imageUrl = BuildConfig.VERSION_NAME;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof Image)) {
                return false;
            }
            Image other = (Image) o;
            if (this.imageUrl == null) {
                if (other.imageUrl != null) {
                    return false;
                }
            } else if (!this.imageUrl.equals(other.imageUrl)) {
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
            int hashCode = (((getClass().getName().hashCode() + 527) * 31) + (this.imageUrl == null ? 0 : this.imageUrl.hashCode())) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (!this.imageUrl.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(1, this.imageUrl);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.imageUrl.equals(BuildConfig.VERSION_NAME)) {
                return size;
            }
            return size + CodedOutputByteBufferNano.computeStringSize(1, this.imageUrl);
        }

        public Image mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        this.imageUrl = input.readString();
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

        public static Image parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (Image) MessageNano.mergeFrom(new Image(), data);
        }

        public static Image parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new Image().mergeFrom(input);
        }
    }

    public static final class InstalledApp extends ExtendableMessageNano<InstalledApp> {
        private static volatile InstalledApp[] _emptyArray;
        public byte[] deprecatedTag2;
        public int deprecatedTag3;
        public int deprecatedTag4;
        public int deprecatedTag5;
        public int[] flags;
        public long installTimestampMillis;
        public String packageName;
        public byte[][] sha1CertificateHashes;

        public static InstalledApp[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new InstalledApp[0];
                    }
                }
            }
            return _emptyArray;
        }

        public InstalledApp() {
            clear();
        }

        public InstalledApp clear() {
            this.packageName = BuildConfig.VERSION_NAME;
            this.sha1CertificateHashes = WireFormatNano.EMPTY_BYTES_ARRAY;
            this.flags = WireFormatNano.EMPTY_INT_ARRAY;
            this.installTimestampMillis = 0;
            this.deprecatedTag2 = WireFormatNano.EMPTY_BYTES;
            this.deprecatedTag3 = 0;
            this.deprecatedTag4 = 0;
            this.deprecatedTag5 = 0;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof InstalledApp)) {
                return false;
            }
            InstalledApp other = (InstalledApp) o;
            if (this.packageName == null) {
                if (other.packageName != null) {
                    return false;
                }
            } else if (!this.packageName.equals(other.packageName)) {
                return false;
            }
            if (!InternalNano.equals(this.sha1CertificateHashes, other.sha1CertificateHashes) || !InternalNano.equals(this.flags, other.flags) || this.installTimestampMillis != other.installTimestampMillis || !Arrays.equals(this.deprecatedTag2, other.deprecatedTag2) || this.deprecatedTag3 != other.deprecatedTag3 || this.deprecatedTag4 != other.deprecatedTag4 || this.deprecatedTag5 != other.deprecatedTag5) {
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
            int i2 = 0;
            int hashCode = (getClass().getName().hashCode() + 527) * 31;
            if (this.packageName == null) {
                i = 0;
            } else {
                i = this.packageName.hashCode();
            }
            i = (((((((((((((((hashCode + i) * 31) + InternalNano.hashCode(this.sha1CertificateHashes)) * 31) + InternalNano.hashCode(this.flags)) * 31) + ((int) (this.installTimestampMillis ^ (this.installTimestampMillis >>> 32)))) * 31) + Arrays.hashCode(this.deprecatedTag2)) * 31) + this.deprecatedTag3) * 31) + this.deprecatedTag4) * 31) + this.deprecatedTag5) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i2 = this.unknownFieldData.hashCode();
            }
            return i + i2;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (!this.packageName.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(1, this.packageName);
            }
            if (!Arrays.equals(this.deprecatedTag2, WireFormatNano.EMPTY_BYTES)) {
                output.writeBytes(2, this.deprecatedTag2);
            }
            if (this.deprecatedTag3 != 0) {
                output.writeInt32(3, this.deprecatedTag3);
            }
            if (this.deprecatedTag4 != 0) {
                output.writeInt32(4, this.deprecatedTag4);
            }
            if (this.deprecatedTag5 != 0) {
                output.writeInt32(5, this.deprecatedTag5);
            }
            if (this.sha1CertificateHashes != null && this.sha1CertificateHashes.length > 0) {
                for (byte[] element : this.sha1CertificateHashes) {
                    if (element != null) {
                        output.writeBytes(6, element);
                    }
                }
            }
            if (this.flags != null && this.flags.length > 0) {
                for (int writeInt32 : this.flags) {
                    output.writeInt32(7, writeInt32);
                }
            }
            if (this.installTimestampMillis != 0) {
                output.writeInt64(8, this.installTimestampMillis);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int dataSize;
            int size = super.computeSerializedSize();
            if (!this.packageName.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.packageName);
            }
            if (!Arrays.equals(this.deprecatedTag2, WireFormatNano.EMPTY_BYTES)) {
                size += CodedOutputByteBufferNano.computeBytesSize(2, this.deprecatedTag2);
            }
            if (this.deprecatedTag3 != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(3, this.deprecatedTag3);
            }
            if (this.deprecatedTag4 != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(4, this.deprecatedTag4);
            }
            if (this.deprecatedTag5 != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(5, this.deprecatedTag5);
            }
            if (this.sha1CertificateHashes != null && this.sha1CertificateHashes.length > 0) {
                int dataCount = 0;
                dataSize = 0;
                for (byte[] element : this.sha1CertificateHashes) {
                    if (element != null) {
                        dataCount++;
                        dataSize += CodedOutputByteBufferNano.computeBytesSizeNoTag(element);
                    }
                }
                size = (size + dataSize) + (dataCount * 1);
            }
            if (this.flags != null && this.flags.length > 0) {
                dataSize = 0;
                for (int element2 : this.flags) {
                    dataSize += CodedOutputByteBufferNano.computeInt32SizeNoTag(element2);
                }
                size = (size + dataSize) + (this.flags.length * 1);
            }
            if (this.installTimestampMillis != 0) {
                return size + CodedOutputByteBufferNano.computeInt64Size(8, this.installTimestampMillis);
            }
            return size;
        }

        public InstalledApp mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                int arrayLength;
                int i;
                int value;
                int[] newArray;
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        this.packageName = input.readString();
                        continue;
                    case LogSource.GOOGLE_ANALYTICS /*18*/:
                        this.deprecatedTag2 = input.readBytes();
                        continue;
                    case LogSource.LB_C /*24*/:
                        this.deprecatedTag3 = input.readInt32();
                        continue;
                    case LogSource.SOCIAL /*32*/:
                        this.deprecatedTag4 = input.readInt32();
                        continue;
                    case LogSource.COPRESENCE /*40*/:
                        this.deprecatedTag5 = input.readInt32();
                        continue;
                    case LogSource.HANGOUT_LOG_REQUEST /*50*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 50);
                        if (this.sha1CertificateHashes == null) {
                            i = 0;
                        } else {
                            i = this.sha1CertificateHashes.length;
                        }
                        byte[][] newArray2 = new byte[(i + arrayLength)][];
                        if (i != 0) {
                            System.arraycopy(this.sha1CertificateHashes, 0, newArray2, 0, i);
                        }
                        while (true) {
                            if (i < newArray2.length - 1) {
                                newArray2[i] = input.readBytes();
                                input.readTag();
                                i++;
                            } else {
                                newArray2[i] = input.readBytes();
                                this.sha1CertificateHashes = newArray2;
                                continue;
                            }
                        }
                    case LogSource.DOCS /*56*/:
                        int length = WireFormatNano.getRepeatedFieldArrayLength(input, 56);
                        int[] validValues = new int[length];
                        i = 0;
                        int validCount = 0;
                        while (i < length) {
                            int validCount2;
                            if (i != 0) {
                                input.readTag();
                            }
                            value = input.readInt32();
                            switch (value) {
                                case Action.UNKNOWN /*0*/:
                                case TimeSelection.Type.TEMPORARY /*1*/:
                                case TimeSelection.Type.INDEFINITELY /*2*/:
                                case TimeSelection.Type.CUSTOM /*3*/:
                                    validCount2 = validCount + 1;
                                    validValues[validCount] = value;
                                    break;
                                default:
                                    validCount2 = validCount;
                                    break;
                            }
                            i++;
                            validCount = validCount2;
                        }
                        if (validCount != 0) {
                            if (this.flags == null) {
                                i = 0;
                            } else {
                                i = this.flags.length;
                            }
                            if (i == 0) {
                                int length2 = validValues.length;
                                if (validCount == r0) {
                                    this.flags = validValues;
                                    break;
                                }
                            }
                            newArray = new int[(i + validCount)];
                            if (i != 0) {
                                System.arraycopy(this.flags, 0, newArray, 0, i);
                            }
                            System.arraycopy(validValues, 0, newArray, i, validCount);
                            this.flags = newArray;
                            break;
                        }
                        continue;
                    case LogSource.SLIDES /*58*/:
                        int limit = input.pushLimit(input.readRawVarint32());
                        arrayLength = 0;
                        int startPos = input.getPosition();
                        while (input.getBytesUntilLimit() > 0) {
                            switch (input.readInt32()) {
                                case Action.UNKNOWN /*0*/:
                                case TimeSelection.Type.TEMPORARY /*1*/:
                                case TimeSelection.Type.INDEFINITELY /*2*/:
                                case TimeSelection.Type.CUSTOM /*3*/:
                                    arrayLength++;
                                    break;
                                default:
                                    break;
                            }
                        }
                        if (arrayLength != 0) {
                            input.rewindToPosition(startPos);
                            if (this.flags == null) {
                                i = 0;
                            } else {
                                i = this.flags.length;
                            }
                            newArray = new int[(i + arrayLength)];
                            if (i != 0) {
                                System.arraycopy(this.flags, 0, newArray, 0, i);
                            }
                            while (input.getBytesUntilLimit() > 0) {
                                value = input.readInt32();
                                switch (value) {
                                    case Action.UNKNOWN /*0*/:
                                    case TimeSelection.Type.TEMPORARY /*1*/:
                                    case TimeSelection.Type.INDEFINITELY /*2*/:
                                    case TimeSelection.Type.CUSTOM /*3*/:
                                        int i2 = i + 1;
                                        newArray[i] = value;
                                        i = i2;
                                        break;
                                    default:
                                        break;
                                }
                            }
                            this.flags = newArray;
                        }
                        input.popLimit(limit);
                        continue;
                    case LogSource.KIDS_COMMUNICATOR /*64*/:
                        this.installTimestampMillis = input.readInt64();
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

        public static InstalledApp parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (InstalledApp) MessageNano.mergeFrom(new InstalledApp(), data);
        }

        public static InstalledApp parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new InstalledApp().mergeFrom(input);
        }
    }

    public interface InstalledAppFlag {
        public static final int FLAG_HAS_LAUNCHER_ACTIVITY = 3;
        public static final int FLAG_SYSTEM_APP = 1;
        public static final int FLAG_UPDATED_SYSTEM_APP = 2;
        public static final int UNSPECIFIED_INSTALLED_APP_FLAG = 0;
    }

    public static final class InstalledAppInfo extends ExtendableMessageNano<InstalledAppInfo> {
        private static volatile InstalledAppInfo[] _emptyArray;
        public InstalledApp[] apps;

        public static InstalledAppInfo[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new InstalledAppInfo[0];
                    }
                }
            }
            return _emptyArray;
        }

        public InstalledAppInfo() {
            clear();
        }

        public InstalledAppInfo clear() {
            this.apps = InstalledApp.emptyArray();
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof InstalledAppInfo)) {
                return false;
            }
            InstalledAppInfo other = (InstalledAppInfo) o;
            if (!InternalNano.equals(this.apps, other.apps)) {
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
            int hashCode = (((getClass().getName().hashCode() + 527) * 31) + InternalNano.hashCode(this.apps)) * 31;
            int hashCode2 = (this.unknownFieldData == null || this.unknownFieldData.isEmpty()) ? 0 : this.unknownFieldData.hashCode();
            return hashCode + hashCode2;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.apps != null && this.apps.length > 0) {
                for (InstalledApp element : this.apps) {
                    if (element != null) {
                        output.writeMessage(1, element);
                    }
                }
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.apps != null && this.apps.length > 0) {
                for (InstalledApp element : this.apps) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(1, element);
                    }
                }
            }
            return size;
        }

        public InstalledAppInfo mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        int i;
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 10);
                        if (this.apps == null) {
                            i = 0;
                        } else {
                            i = this.apps.length;
                        }
                        InstalledApp[] newArray = new InstalledApp[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.apps, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new InstalledApp();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i++;
                        }
                        newArray[i] = new InstalledApp();
                        input.readMessage(newArray[i]);
                        this.apps = newArray;
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

        public static InstalledAppInfo parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (InstalledAppInfo) MessageNano.mergeFrom(new InstalledAppInfo(), data);
        }

        public static InstalledAppInfo parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new InstalledAppInfo().mergeFrom(input);
        }
    }

    public static final class PlatformInfo extends ExtendableMessageNano<PlatformInfo> {
        private static volatile PlatformInfo[] _emptyArray;
        public AndroidBuildInfo buildInfo;
        public GcoreInfo gcoreInfo;
        public int platformApiVersion;
        public int[] supportedFeatures;

        public static PlatformInfo[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new PlatformInfo[0];
                    }
                }
            }
            return _emptyArray;
        }

        public PlatformInfo() {
            clear();
        }

        public PlatformInfo clear() {
            this.buildInfo = null;
            this.platformApiVersion = 0;
            this.gcoreInfo = null;
            this.supportedFeatures = WireFormatNano.EMPTY_INT_ARRAY;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof PlatformInfo)) {
                return false;
            }
            PlatformInfo other = (PlatformInfo) o;
            if (this.buildInfo == null) {
                if (other.buildInfo != null) {
                    return false;
                }
            } else if (!this.buildInfo.equals(other.buildInfo)) {
                return false;
            }
            if (this.platformApiVersion != other.platformApiVersion) {
                return false;
            }
            if (this.gcoreInfo == null) {
                if (other.gcoreInfo != null) {
                    return false;
                }
            } else if (!this.gcoreInfo.equals(other.gcoreInfo)) {
                return false;
            }
            if (!InternalNano.equals(this.supportedFeatures, other.supportedFeatures)) {
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
            int hashCode = (((((((((getClass().getName().hashCode() + 527) * 31) + (this.buildInfo == null ? 0 : this.buildInfo.hashCode())) * 31) + this.platformApiVersion) * 31) + (this.gcoreInfo == null ? 0 : this.gcoreInfo.hashCode())) * 31) + InternalNano.hashCode(this.supportedFeatures)) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.buildInfo != null) {
                output.writeMessage(1, this.buildInfo);
            }
            if (this.platformApiVersion != 0) {
                output.writeInt32(2, this.platformApiVersion);
            }
            if (this.gcoreInfo != null) {
                output.writeMessage(3, this.gcoreInfo);
            }
            if (this.supportedFeatures != null && this.supportedFeatures.length > 0) {
                for (int writeInt32 : this.supportedFeatures) {
                    output.writeInt32(4, writeInt32);
                }
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.buildInfo != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(1, this.buildInfo);
            }
            if (this.platformApiVersion != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(2, this.platformApiVersion);
            }
            if (this.gcoreInfo != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(3, this.gcoreInfo);
            }
            if (this.supportedFeatures == null || this.supportedFeatures.length <= 0) {
                return size;
            }
            int dataSize = 0;
            for (int element : this.supportedFeatures) {
                dataSize += CodedOutputByteBufferNano.computeInt32SizeNoTag(element);
            }
            return (size + dataSize) + (this.supportedFeatures.length * 1);
        }

        public PlatformInfo mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                int i;
                int value;
                int[] newArray;
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        if (this.buildInfo == null) {
                            this.buildInfo = new AndroidBuildInfo();
                        }
                        input.readMessage(this.buildInfo);
                        continue;
                    case LogSource.GMS_CORE_PEOPLE /*16*/:
                        this.platformApiVersion = input.readInt32();
                        continue;
                    case LogSource.ANDROID_CAMERA /*26*/:
                        if (this.gcoreInfo == null) {
                            this.gcoreInfo = new GcoreInfo();
                        }
                        input.readMessage(this.gcoreInfo);
                        continue;
                    case LogSource.SOCIAL /*32*/:
                        int length = WireFormatNano.getRepeatedFieldArrayLength(input, 32);
                        int[] validValues = new int[length];
                        i = 0;
                        int validCount = 0;
                        while (i < length) {
                            int validCount2;
                            if (i != 0) {
                                input.readTag();
                            }
                            value = input.readInt32();
                            switch (value) {
                                case Action.UNKNOWN /*0*/:
                                case TimeSelection.Type.TEMPORARY /*1*/:
                                case TimeSelection.Type.INDEFINITELY /*2*/:
                                case TimeSelection.Type.CUSTOM /*3*/:
                                case OvenFreshResult.NO_ACCOUNTS /*4*/:
                                case Type.ADD_NEW_SHARES /*5*/:
                                case Type.REMOVE_SHARE /*6*/:
                                case Type.RESET_TIME /*7*/:
                                    validCount2 = validCount + 1;
                                    validValues[validCount] = value;
                                    break;
                                default:
                                    validCount2 = validCount;
                                    break;
                            }
                            i++;
                            validCount = validCount2;
                        }
                        if (validCount != 0) {
                            i = this.supportedFeatures == null ? 0 : this.supportedFeatures.length;
                            if (i != 0 || validCount != validValues.length) {
                                newArray = new int[(i + validCount)];
                                if (i != 0) {
                                    System.arraycopy(this.supportedFeatures, 0, newArray, 0, i);
                                }
                                System.arraycopy(validValues, 0, newArray, i, validCount);
                                this.supportedFeatures = newArray;
                                break;
                            }
                            this.supportedFeatures = validValues;
                            break;
                        }
                        continue;
                    case LogSource.NOVA /*34*/:
                        int limit = input.pushLimit(input.readRawVarint32());
                        int arrayLength = 0;
                        int startPos = input.getPosition();
                        while (input.getBytesUntilLimit() > 0) {
                            switch (input.readInt32()) {
                                case Action.UNKNOWN /*0*/:
                                case TimeSelection.Type.TEMPORARY /*1*/:
                                case TimeSelection.Type.INDEFINITELY /*2*/:
                                case TimeSelection.Type.CUSTOM /*3*/:
                                case OvenFreshResult.NO_ACCOUNTS /*4*/:
                                case Type.ADD_NEW_SHARES /*5*/:
                                case Type.REMOVE_SHARE /*6*/:
                                case Type.RESET_TIME /*7*/:
                                    arrayLength++;
                                    break;
                                default:
                                    break;
                            }
                        }
                        if (arrayLength != 0) {
                            input.rewindToPosition(startPos);
                            if (this.supportedFeatures == null) {
                                i = 0;
                            } else {
                                i = this.supportedFeatures.length;
                            }
                            newArray = new int[(i + arrayLength)];
                            if (i != 0) {
                                System.arraycopy(this.supportedFeatures, 0, newArray, 0, i);
                            }
                            while (input.getBytesUntilLimit() > 0) {
                                value = input.readInt32();
                                switch (value) {
                                    case Action.UNKNOWN /*0*/:
                                    case TimeSelection.Type.TEMPORARY /*1*/:
                                    case TimeSelection.Type.INDEFINITELY /*2*/:
                                    case TimeSelection.Type.CUSTOM /*3*/:
                                    case OvenFreshResult.NO_ACCOUNTS /*4*/:
                                    case Type.ADD_NEW_SHARES /*5*/:
                                    case Type.REMOVE_SHARE /*6*/:
                                    case Type.RESET_TIME /*7*/:
                                        int i2 = i + 1;
                                        newArray[i] = value;
                                        i = i2;
                                        break;
                                    default:
                                        break;
                                }
                            }
                            this.supportedFeatures = newArray;
                        }
                        input.popLimit(limit);
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

        public static PlatformInfo parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (PlatformInfo) MessageNano.mergeFrom(new PlatformInfo(), data);
        }

        public static PlatformInfo parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new PlatformInfo().mergeFrom(input);
        }
    }

    public interface ReadMask {
        public static final int CAPABILITY_INFO = 4;
        public static final int DEVICE_SETTING = 5;
        public static final int DISPLAY_INFO = 2;
        public static final int INSTALLED_APP = 1;
        public static final int PLATFORM_INFO = 3;
        public static final int UNKNOWN_READ_MASK = 0;
    }

    public static final class Receipt extends ExtendableMessageNano<Receipt> {
        private static volatile Receipt[] _emptyArray;
        public long eventTimestampMillis;
        public ReceiptHeader receiptHeader;
        public ReceiptPayload receiptPayload;
        public String sourceId;
        public long uploadTimestampMillis;

        public static Receipt[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new Receipt[0];
                    }
                }
            }
            return _emptyArray;
        }

        public Receipt() {
            clear();
        }

        public Receipt clear() {
            this.sourceId = BuildConfig.VERSION_NAME;
            this.receiptHeader = null;
            this.eventTimestampMillis = 0;
            this.uploadTimestampMillis = 0;
            this.receiptPayload = null;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof Receipt)) {
                return false;
            }
            Receipt other = (Receipt) o;
            if (this.sourceId == null) {
                if (other.sourceId != null) {
                    return false;
                }
            } else if (!this.sourceId.equals(other.sourceId)) {
                return false;
            }
            if (this.receiptHeader == null) {
                if (other.receiptHeader != null) {
                    return false;
                }
            } else if (!this.receiptHeader.equals(other.receiptHeader)) {
                return false;
            }
            if (this.eventTimestampMillis != other.eventTimestampMillis || this.uploadTimestampMillis != other.uploadTimestampMillis) {
                return false;
            }
            if (this.receiptPayload == null) {
                if (other.receiptPayload != null) {
                    return false;
                }
            } else if (!this.receiptPayload.equals(other.receiptPayload)) {
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
            int hashCode = (((((((((((getClass().getName().hashCode() + 527) * 31) + (this.sourceId == null ? 0 : this.sourceId.hashCode())) * 31) + (this.receiptHeader == null ? 0 : this.receiptHeader.hashCode())) * 31) + ((int) (this.eventTimestampMillis ^ (this.eventTimestampMillis >>> 32)))) * 31) + ((int) (this.uploadTimestampMillis ^ (this.uploadTimestampMillis >>> 32)))) * 31) + (this.receiptPayload == null ? 0 : this.receiptPayload.hashCode())) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (!this.sourceId.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(1, this.sourceId);
            }
            if (this.receiptHeader != null) {
                output.writeMessage(2, this.receiptHeader);
            }
            if (this.eventTimestampMillis != 0) {
                output.writeInt64(3, this.eventTimestampMillis);
            }
            if (this.uploadTimestampMillis != 0) {
                output.writeInt64(4, this.uploadTimestampMillis);
            }
            if (this.receiptPayload != null) {
                output.writeMessage(5, this.receiptPayload);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (!this.sourceId.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.sourceId);
            }
            if (this.receiptHeader != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(2, this.receiptHeader);
            }
            if (this.eventTimestampMillis != 0) {
                size += CodedOutputByteBufferNano.computeInt64Size(3, this.eventTimestampMillis);
            }
            if (this.uploadTimestampMillis != 0) {
                size += CodedOutputByteBufferNano.computeInt64Size(4, this.uploadTimestampMillis);
            }
            if (this.receiptPayload != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(5, this.receiptPayload);
            }
            return size;
        }

        public Receipt mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        this.sourceId = input.readString();
                        continue;
                    case LogSource.GOOGLE_ANALYTICS /*18*/:
                        if (this.receiptHeader == null) {
                            this.receiptHeader = new ReceiptHeader();
                        }
                        input.readMessage(this.receiptHeader);
                        continue;
                    case LogSource.LB_C /*24*/:
                        this.eventTimestampMillis = input.readInt64();
                        continue;
                    case LogSource.SOCIAL /*32*/:
                        this.uploadTimestampMillis = input.readInt64();
                        continue;
                    case LogSource.PHOTOS /*42*/:
                        if (this.receiptPayload == null) {
                            this.receiptPayload = new ReceiptPayload();
                        }
                        input.readMessage(this.receiptPayload);
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

        public static Receipt parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (Receipt) MessageNano.mergeFrom(new Receipt(), data);
        }

        public static Receipt parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new Receipt().mergeFrom(input);
        }
    }

    public static final class ReceiptHeader extends ExtendableMessageNano<ReceiptHeader> {
        private static volatile ReceiptHeader[] _emptyArray;
        public String key;
        public int namespace;
        public String subtype;

        public interface Namespace {
            public static final int BEDTIME = 3;
            public static final int MUD_COMMAND = 1;
            public static final int TIMEOUT = 2;
            public static final int UNSPECIFIED_NAMESPACE = 0;
        }

        public static ReceiptHeader[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new ReceiptHeader[0];
                    }
                }
            }
            return _emptyArray;
        }

        public ReceiptHeader() {
            clear();
        }

        public ReceiptHeader clear() {
            this.namespace = 0;
            this.key = BuildConfig.VERSION_NAME;
            this.subtype = BuildConfig.VERSION_NAME;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof ReceiptHeader)) {
                return false;
            }
            ReceiptHeader other = (ReceiptHeader) o;
            if (this.namespace != other.namespace) {
                return false;
            }
            if (this.key == null) {
                if (other.key != null) {
                    return false;
                }
            } else if (!this.key.equals(other.key)) {
                return false;
            }
            if (this.subtype == null) {
                if (other.subtype != null) {
                    return false;
                }
            } else if (!this.subtype.equals(other.subtype)) {
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
            int hashCode = (((((((getClass().getName().hashCode() + 527) * 31) + this.namespace) * 31) + (this.key == null ? 0 : this.key.hashCode())) * 31) + (this.subtype == null ? 0 : this.subtype.hashCode())) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.namespace != 0) {
                output.writeInt32(1, this.namespace);
            }
            if (!this.key.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(2, this.key);
            }
            if (!this.subtype.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(3, this.subtype);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.namespace != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(1, this.namespace);
            }
            if (!this.key.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(2, this.key);
            }
            if (this.subtype.equals(BuildConfig.VERSION_NAME)) {
                return size;
            }
            return size + CodedOutputByteBufferNano.computeStringSize(3, this.subtype);
        }

        public ReceiptHeader mergeFrom(CodedInputByteBufferNano input) throws IOException {
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
                                this.namespace = value;
                                break;
                            default:
                                continue;
                        }
                    case LogSource.GOOGLE_ANALYTICS /*18*/:
                        this.key = input.readString();
                        continue;
                    case LogSource.ANDROID_CAMERA /*26*/:
                        this.subtype = input.readString();
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

        public static ReceiptHeader parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (ReceiptHeader) MessageNano.mergeFrom(new ReceiptHeader(), data);
        }

        public static ReceiptHeader parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new ReceiptHeader().mergeFrom(input);
        }
    }

    public static final class ReceiptPayload extends ExtendableMessageNano<ReceiptPayload> {
        private static volatile ReceiptPayload[] _emptyArray;

        public static ReceiptPayload[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new ReceiptPayload[0];
                    }
                }
            }
            return _emptyArray;
        }

        public ReceiptPayload() {
            clear();
        }

        public ReceiptPayload clear() {
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof ReceiptPayload)) {
                return false;
            }
            ReceiptPayload other = (ReceiptPayload) o;
            if (this.unknownFieldData != null && !this.unknownFieldData.isEmpty()) {
                return this.unknownFieldData.equals(other.unknownFieldData);
            }
            if (other.unknownFieldData == null || other.unknownFieldData.isEmpty()) {
                return true;
            }
            return false;
        }

        public int hashCode() {
            int hashCode = (getClass().getName().hashCode() + 527) * 31;
            int hashCode2 = (this.unknownFieldData == null || this.unknownFieldData.isEmpty()) ? 0 : this.unknownFieldData.hashCode();
            return hashCode + hashCode2;
        }

        public ReceiptPayload mergeFrom(CodedInputByteBufferNano input) throws IOException {
            int tag;
            do {
                tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    default:
                        break;
                }
                return this;
            } while (storeUnknownField(input, tag));
            return this;
        }

        public static ReceiptPayload parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (ReceiptPayload) MessageNano.mergeFrom(new ReceiptPayload(), data);
        }

        public static ReceiptPayload parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new ReceiptPayload().mergeFrom(input);
        }
    }

    public static final class RemoteInstruction extends ExtendableMessageNano<RemoteInstruction> {
        private static volatile RemoteInstruction[] _emptyArray;
        public int action;
        public long createdTimestampMillis;
        public String deviceId;
        public String id;
        public boolean neverExpire;
        public NotifyLocationSettingChangeData notifyLocationSettingChangeData;
        public String payload;
        public long timeToLiveMillis;

        public interface Action {
            public static final int DEPRECATED_TAG_3 = 3;
            public static final int DEPRECATED_TAG_5 = 5;
            public static final int FIX_PROFILE = 4;
            public static final int NOTIFY_LOCATION_SETTING_CHANGE = 6;
            public static final int RING = 2;
            public static final int UNSPECIFIED_ACTION = 0;
            public static final int WIPE = 1;
        }

        public static final class NotifyLocationSettingChangeData extends ExtendableMessageNano<NotifyLocationSettingChangeData> {
            private static volatile NotifyLocationSettingChangeData[] _emptyArray;
            public String coalesceKey;
            public String notificationMessage;

            public static NotifyLocationSettingChangeData[] emptyArray() {
                if (_emptyArray == null) {
                    synchronized (InternalNano.LAZY_INIT_LOCK) {
                        if (_emptyArray == null) {
                            _emptyArray = new NotifyLocationSettingChangeData[0];
                        }
                    }
                }
                return _emptyArray;
            }

            public NotifyLocationSettingChangeData() {
                clear();
            }

            public NotifyLocationSettingChangeData clear() {
                this.coalesceKey = BuildConfig.VERSION_NAME;
                this.notificationMessage = BuildConfig.VERSION_NAME;
                this.unknownFieldData = null;
                this.cachedSize = -1;
                return this;
            }

            public boolean equals(Object o) {
                if (o == this) {
                    return true;
                }
                if (!(o instanceof NotifyLocationSettingChangeData)) {
                    return false;
                }
                NotifyLocationSettingChangeData other = (NotifyLocationSettingChangeData) o;
                if (this.coalesceKey == null) {
                    if (other.coalesceKey != null) {
                        return false;
                    }
                } else if (!this.coalesceKey.equals(other.coalesceKey)) {
                    return false;
                }
                if (this.notificationMessage == null) {
                    if (other.notificationMessage != null) {
                        return false;
                    }
                } else if (!this.notificationMessage.equals(other.notificationMessage)) {
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
                int hashCode = (((((getClass().getName().hashCode() + 527) * 31) + (this.coalesceKey == null ? 0 : this.coalesceKey.hashCode())) * 31) + (this.notificationMessage == null ? 0 : this.notificationMessage.hashCode())) * 31;
                if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                    i = this.unknownFieldData.hashCode();
                }
                return hashCode + i;
            }

            public void writeTo(CodedOutputByteBufferNano output) throws IOException {
                if (!this.coalesceKey.equals(BuildConfig.VERSION_NAME)) {
                    output.writeString(1, this.coalesceKey);
                }
                if (!this.notificationMessage.equals(BuildConfig.VERSION_NAME)) {
                    output.writeString(2, this.notificationMessage);
                }
                super.writeTo(output);
            }

            protected int computeSerializedSize() {
                int size = super.computeSerializedSize();
                if (!this.coalesceKey.equals(BuildConfig.VERSION_NAME)) {
                    size += CodedOutputByteBufferNano.computeStringSize(1, this.coalesceKey);
                }
                if (this.notificationMessage.equals(BuildConfig.VERSION_NAME)) {
                    return size;
                }
                return size + CodedOutputByteBufferNano.computeStringSize(2, this.notificationMessage);
            }

            public NotifyLocationSettingChangeData mergeFrom(CodedInputByteBufferNano input) throws IOException {
                while (true) {
                    int tag = input.readTag();
                    switch (tag) {
                        case com.google.wireless.android.play.playlog.proto.UserLocation.LaunchSettingsEvent.Action.UNKNOWN /*0*/:
                            break;
                        case Type.TAP_ABOUT /*10*/:
                            this.coalesceKey = input.readString();
                            continue;
                        case LogSource.GOOGLE_ANALYTICS /*18*/:
                            this.notificationMessage = input.readString();
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

            public static NotifyLocationSettingChangeData parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
                return (NotifyLocationSettingChangeData) MessageNano.mergeFrom(new NotifyLocationSettingChangeData(), data);
            }

            public static NotifyLocationSettingChangeData parseFrom(CodedInputByteBufferNano input) throws IOException {
                return new NotifyLocationSettingChangeData().mergeFrom(input);
            }
        }

        public static RemoteInstruction[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new RemoteInstruction[0];
                    }
                }
            }
            return _emptyArray;
        }

        public RemoteInstruction() {
            clear();
        }

        public RemoteInstruction clear() {
            this.action = 0;
            this.id = BuildConfig.VERSION_NAME;
            this.deviceId = BuildConfig.VERSION_NAME;
            this.neverExpire = false;
            this.timeToLiveMillis = 0;
            this.createdTimestampMillis = 0;
            this.payload = BuildConfig.VERSION_NAME;
            this.notifyLocationSettingChangeData = null;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof RemoteInstruction)) {
                return false;
            }
            RemoteInstruction other = (RemoteInstruction) o;
            if (this.action != other.action) {
                return false;
            }
            if (this.id == null) {
                if (other.id != null) {
                    return false;
                }
            } else if (!this.id.equals(other.id)) {
                return false;
            }
            if (this.deviceId == null) {
                if (other.deviceId != null) {
                    return false;
                }
            } else if (!this.deviceId.equals(other.deviceId)) {
                return false;
            }
            if (this.neverExpire != other.neverExpire || this.timeToLiveMillis != other.timeToLiveMillis || this.createdTimestampMillis != other.createdTimestampMillis) {
                return false;
            }
            if (this.payload == null) {
                if (other.payload != null) {
                    return false;
                }
            } else if (!this.payload.equals(other.payload)) {
                return false;
            }
            if (this.notifyLocationSettingChangeData == null) {
                if (other.notifyLocationSettingChangeData != null) {
                    return false;
                }
            } else if (!this.notifyLocationSettingChangeData.equals(other.notifyLocationSettingChangeData)) {
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
            int hashCode = (((((((((((((((((getClass().getName().hashCode() + 527) * 31) + this.action) * 31) + (this.id == null ? 0 : this.id.hashCode())) * 31) + (this.deviceId == null ? 0 : this.deviceId.hashCode())) * 31) + (this.neverExpire ? 1231 : 1237)) * 31) + ((int) (this.timeToLiveMillis ^ (this.timeToLiveMillis >>> 32)))) * 31) + ((int) (this.createdTimestampMillis ^ (this.createdTimestampMillis >>> 32)))) * 31) + (this.payload == null ? 0 : this.payload.hashCode())) * 31) + (this.notifyLocationSettingChangeData == null ? 0 : this.notifyLocationSettingChangeData.hashCode())) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.action != 0) {
                output.writeInt32(1, this.action);
            }
            if (!this.id.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(2, this.id);
            }
            if (!this.deviceId.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(3, this.deviceId);
            }
            if (this.neverExpire) {
                output.writeBool(4, this.neverExpire);
            }
            if (this.timeToLiveMillis != 0) {
                output.writeInt64(5, this.timeToLiveMillis);
            }
            if (this.createdTimestampMillis != 0) {
                output.writeInt64(6, this.createdTimestampMillis);
            }
            if (!this.payload.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(7, this.payload);
            }
            if (this.notifyLocationSettingChangeData != null) {
                output.writeMessage(8, this.notifyLocationSettingChangeData);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.action != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(1, this.action);
            }
            if (!this.id.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(2, this.id);
            }
            if (!this.deviceId.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(3, this.deviceId);
            }
            if (this.neverExpire) {
                size += CodedOutputByteBufferNano.computeBoolSize(4, this.neverExpire);
            }
            if (this.timeToLiveMillis != 0) {
                size += CodedOutputByteBufferNano.computeInt64Size(5, this.timeToLiveMillis);
            }
            if (this.createdTimestampMillis != 0) {
                size += CodedOutputByteBufferNano.computeInt64Size(6, this.createdTimestampMillis);
            }
            if (!this.payload.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(7, this.payload);
            }
            if (this.notifyLocationSettingChangeData != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(8, this.notifyLocationSettingChangeData);
            }
            return size;
        }

        public RemoteInstruction mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case com.google.wireless.android.play.playlog.proto.UserLocation.LaunchSettingsEvent.Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_GET_LINK /*8*/:
                        int value = input.readInt32();
                        switch (value) {
                            case com.google.wireless.android.play.playlog.proto.UserLocation.LaunchSettingsEvent.Action.UNKNOWN /*0*/:
                            case TimeSelection.Type.TEMPORARY /*1*/:
                            case TimeSelection.Type.INDEFINITELY /*2*/:
                            case TimeSelection.Type.CUSTOM /*3*/:
                            case OvenFreshResult.NO_ACCOUNTS /*4*/:
                            case Type.ADD_NEW_SHARES /*5*/:
                            case Type.REMOVE_SHARE /*6*/:
                                this.action = value;
                                break;
                            default:
                                continue;
                        }
                    case LogSource.GOOGLE_ANALYTICS /*18*/:
                        this.id = input.readString();
                        continue;
                    case LogSource.ANDROID_CAMERA /*26*/:
                        this.deviceId = input.readString();
                        continue;
                    case LogSource.SOCIAL /*32*/:
                        this.neverExpire = input.readBool();
                        continue;
                    case LogSource.COPRESENCE /*40*/:
                        this.timeToLiveMillis = input.readInt64();
                        continue;
                    case LogSource.BACKDROP /*48*/:
                        this.createdTimestampMillis = input.readInt64();
                        continue;
                    case LogSource.SLIDES /*58*/:
                        this.payload = input.readString();
                        continue;
                    case LogSource.WIFI_ASSISTANT /*66*/:
                        if (this.notifyLocationSettingChangeData == null) {
                            this.notifyLocationSettingChangeData = new NotifyLocationSettingChangeData();
                        }
                        input.readMessage(this.notifyLocationSettingChangeData);
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

        public static RemoteInstruction parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (RemoteInstruction) MessageNano.mergeFrom(new RemoteInstruction(), data);
        }

        public static RemoteInstruction parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new RemoteInstruction().mergeFrom(input);
        }
    }

    public static final class RemoteInstructionQueue extends ExtendableMessageNano<RemoteInstructionQueue> {
        private static volatile RemoteInstructionQueue[] _emptyArray;
        public RemoteInstruction[] instructions;

        public static RemoteInstructionQueue[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new RemoteInstructionQueue[0];
                    }
                }
            }
            return _emptyArray;
        }

        public RemoteInstructionQueue() {
            clear();
        }

        public RemoteInstructionQueue clear() {
            this.instructions = RemoteInstruction.emptyArray();
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof RemoteInstructionQueue)) {
                return false;
            }
            RemoteInstructionQueue other = (RemoteInstructionQueue) o;
            if (!InternalNano.equals(this.instructions, other.instructions)) {
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
            int hashCode = (((getClass().getName().hashCode() + 527) * 31) + InternalNano.hashCode(this.instructions)) * 31;
            int hashCode2 = (this.unknownFieldData == null || this.unknownFieldData.isEmpty()) ? 0 : this.unknownFieldData.hashCode();
            return hashCode + hashCode2;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.instructions != null && this.instructions.length > 0) {
                for (RemoteInstruction element : this.instructions) {
                    if (element != null) {
                        output.writeMessage(1, element);
                    }
                }
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.instructions != null && this.instructions.length > 0) {
                for (RemoteInstruction element : this.instructions) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(1, element);
                    }
                }
            }
            return size;
        }

        public RemoteInstructionQueue mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        int i;
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 10);
                        if (this.instructions == null) {
                            i = 0;
                        } else {
                            i = this.instructions.length;
                        }
                        RemoteInstruction[] newArray = new RemoteInstruction[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.instructions, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new RemoteInstruction();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i++;
                        }
                        newArray[i] = new RemoteInstruction();
                        input.readMessage(newArray[i]);
                        this.instructions = newArray;
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

        public static RemoteInstructionQueue parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (RemoteInstructionQueue) MessageNano.mergeFrom(new RemoteInstructionQueue(), data);
        }

        public static RemoteInstructionQueue parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new RemoteInstructionQueue().mergeFrom(input);
        }
    }

    public interface SupportedFeature {
        public static final int SUPPORT_BEDTIME = 4;
        public static final int SUPPORT_GLS_LOCKDOWN = 3;
        public static final int SUPPORT_LOCKBOX_APPDATA_LOCKDOWN = 5;
        public static final int SUPPORT_SMS = 6;
        public static final int SUPPORT_TIMEOUTS = 1;
        public static final int SUPPORT_USERMANAGER_LOCKDOWN = 2;
        public static final int SUPPORT_VOICE = 7;
        public static final int UNSPECIFIED_SUPPORTED_FEATURE = 0;
    }
}
