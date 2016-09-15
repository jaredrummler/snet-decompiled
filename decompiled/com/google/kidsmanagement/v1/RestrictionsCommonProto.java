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

public interface RestrictionsCommonProto {

    public static final class AndroidAppMetadata extends ExtendableMessageNano<AndroidAppMetadata> {
        private static volatile AndroidAppMetadata[] _emptyArray;
        public String packageName;

        public static AndroidAppMetadata[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new AndroidAppMetadata[0];
                    }
                }
            }
            return _emptyArray;
        }

        public AndroidAppMetadata() {
            clear();
        }

        public AndroidAppMetadata clear() {
            this.packageName = BuildConfig.VERSION_NAME;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof AndroidAppMetadata)) {
                return false;
            }
            AndroidAppMetadata other = (AndroidAppMetadata) o;
            if (this.packageName == null) {
                if (other.packageName != null) {
                    return false;
                }
            } else if (!this.packageName.equals(other.packageName)) {
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
            int hashCode = (((getClass().getName().hashCode() + 527) * 31) + (this.packageName == null ? 0 : this.packageName.hashCode())) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (!this.packageName.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(1, this.packageName);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.packageName.equals(BuildConfig.VERSION_NAME)) {
                return size;
            }
            return size + CodedOutputByteBufferNano.computeStringSize(1, this.packageName);
        }

        public AndroidAppMetadata mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        this.packageName = input.readString();
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

        public static AndroidAppMetadata parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (AndroidAppMetadata) MessageNano.mergeFrom(new AndroidAppMetadata(), data);
        }

        public static AndroidAppMetadata parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new AndroidAppMetadata().mergeFrom(input);
        }
    }

    public static final class AndroidAppRestrictionKey extends ExtendableMessageNano<AndroidAppRestrictionKey> {
        private static volatile AndroidAppRestrictionKey[] _emptyArray;
        public String packageName;
        public String restrictionName;
        public int type;

        public static AndroidAppRestrictionKey[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new AndroidAppRestrictionKey[0];
                    }
                }
            }
            return _emptyArray;
        }

        public AndroidAppRestrictionKey() {
            clear();
        }

        public AndroidAppRestrictionKey clear() {
            this.packageName = BuildConfig.VERSION_NAME;
            this.type = 0;
            this.restrictionName = BuildConfig.VERSION_NAME;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof AndroidAppRestrictionKey)) {
                return false;
            }
            AndroidAppRestrictionKey other = (AndroidAppRestrictionKey) o;
            if (this.packageName == null) {
                if (other.packageName != null) {
                    return false;
                }
            } else if (!this.packageName.equals(other.packageName)) {
                return false;
            }
            if (this.type != other.type) {
                return false;
            }
            if (this.restrictionName == null) {
                if (other.restrictionName != null) {
                    return false;
                }
            } else if (!this.restrictionName.equals(other.restrictionName)) {
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
            int hashCode = (((((((getClass().getName().hashCode() + 527) * 31) + (this.packageName == null ? 0 : this.packageName.hashCode())) * 31) + this.type) * 31) + (this.restrictionName == null ? 0 : this.restrictionName.hashCode())) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (!this.packageName.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(1, this.packageName);
            }
            if (this.type != 0) {
                output.writeInt32(2, this.type);
            }
            if (!this.restrictionName.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(3, this.restrictionName);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (!this.packageName.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.packageName);
            }
            if (this.type != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(2, this.type);
            }
            if (this.restrictionName.equals(BuildConfig.VERSION_NAME)) {
                return size;
            }
            return size + CodedOutputByteBufferNano.computeStringSize(3, this.restrictionName);
        }

        public AndroidAppRestrictionKey mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        this.packageName = input.readString();
                        continue;
                    case LogSource.GMS_CORE_PEOPLE /*16*/:
                        int value = input.readInt32();
                        switch (value) {
                            case Action.UNKNOWN /*0*/:
                            case TimeSelection.Type.TEMPORARY /*1*/:
                            case TimeSelection.Type.INDEFINITELY /*2*/:
                                this.type = value;
                                break;
                            default:
                                continue;
                        }
                    case LogSource.ANDROID_CAMERA /*26*/:
                        this.restrictionName = input.readString();
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

        public static AndroidAppRestrictionKey parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (AndroidAppRestrictionKey) MessageNano.mergeFrom(new AndroidAppRestrictionKey(), data);
        }

        public static AndroidAppRestrictionKey parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new AndroidAppRestrictionKey().mergeFrom(input);
        }
    }

    public interface AndroidAppRestrictionType {
        public static final int ANDROID_APP_RESTRICTION_TYPE_UNSPECIFIED = 0;
        public static final int APP_SUPPLIED = 2;
        public static final int ENABLED = 1;
    }

    public static final class GoogleServiceMetadata extends ExtendableMessageNano<GoogleServiceMetadata> {
        private static volatile GoogleServiceMetadata[] _emptyArray;
        public String description;
        public String enableRestrictionId;
        public String iconUrl;
        public String id;
        public String name;

        public static GoogleServiceMetadata[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new GoogleServiceMetadata[0];
                    }
                }
            }
            return _emptyArray;
        }

        public GoogleServiceMetadata() {
            clear();
        }

        public GoogleServiceMetadata clear() {
            this.id = BuildConfig.VERSION_NAME;
            this.enableRestrictionId = BuildConfig.VERSION_NAME;
            this.name = BuildConfig.VERSION_NAME;
            this.description = BuildConfig.VERSION_NAME;
            this.iconUrl = BuildConfig.VERSION_NAME;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof GoogleServiceMetadata)) {
                return false;
            }
            GoogleServiceMetadata other = (GoogleServiceMetadata) o;
            if (this.id == null) {
                if (other.id != null) {
                    return false;
                }
            } else if (!this.id.equals(other.id)) {
                return false;
            }
            if (this.enableRestrictionId == null) {
                if (other.enableRestrictionId != null) {
                    return false;
                }
            } else if (!this.enableRestrictionId.equals(other.enableRestrictionId)) {
                return false;
            }
            if (this.name == null) {
                if (other.name != null) {
                    return false;
                }
            } else if (!this.name.equals(other.name)) {
                return false;
            }
            if (this.description == null) {
                if (other.description != null) {
                    return false;
                }
            } else if (!this.description.equals(other.description)) {
                return false;
            }
            if (this.iconUrl == null) {
                if (other.iconUrl != null) {
                    return false;
                }
            } else if (!this.iconUrl.equals(other.iconUrl)) {
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
            int hashCode = (((((((((((getClass().getName().hashCode() + 527) * 31) + (this.id == null ? 0 : this.id.hashCode())) * 31) + (this.enableRestrictionId == null ? 0 : this.enableRestrictionId.hashCode())) * 31) + (this.name == null ? 0 : this.name.hashCode())) * 31) + (this.description == null ? 0 : this.description.hashCode())) * 31) + (this.iconUrl == null ? 0 : this.iconUrl.hashCode())) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (!this.enableRestrictionId.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(1, this.enableRestrictionId);
            }
            if (!this.name.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(2, this.name);
            }
            if (!this.description.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(3, this.description);
            }
            if (!this.iconUrl.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(4, this.iconUrl);
            }
            if (!this.id.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(5, this.id);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (!this.enableRestrictionId.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.enableRestrictionId);
            }
            if (!this.name.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(2, this.name);
            }
            if (!this.description.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(3, this.description);
            }
            if (!this.iconUrl.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(4, this.iconUrl);
            }
            if (this.id.equals(BuildConfig.VERSION_NAME)) {
                return size;
            }
            return size + CodedOutputByteBufferNano.computeStringSize(5, this.id);
        }

        public GoogleServiceMetadata mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        this.enableRestrictionId = input.readString();
                        continue;
                    case LogSource.GOOGLE_ANALYTICS /*18*/:
                        this.name = input.readString();
                        continue;
                    case LogSource.ANDROID_CAMERA /*26*/:
                        this.description = input.readString();
                        continue;
                    case LogSource.NOVA /*34*/:
                        this.iconUrl = input.readString();
                        continue;
                    case LogSource.PHOTOS /*42*/:
                        this.id = input.readString();
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

        public static GoogleServiceMetadata parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (GoogleServiceMetadata) MessageNano.mergeFrom(new GoogleServiceMetadata(), data);
        }

        public static GoogleServiceMetadata parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new GoogleServiceMetadata().mergeFrom(input);
        }
    }

    public static final class GoogleServiceRestrictionKey extends ExtendableMessageNano<GoogleServiceRestrictionKey> {
        private static volatile GoogleServiceRestrictionKey[] _emptyArray;
        public int setting;

        public static GoogleServiceRestrictionKey[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new GoogleServiceRestrictionKey[0];
                    }
                }
            }
            return _emptyArray;
        }

        public GoogleServiceRestrictionKey() {
            clear();
        }

        public GoogleServiceRestrictionKey clear() {
            this.setting = 0;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof GoogleServiceRestrictionKey)) {
                return false;
            }
            GoogleServiceRestrictionKey other = (GoogleServiceRestrictionKey) o;
            if (this.setting != other.setting) {
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
            int hashCode = (((getClass().getName().hashCode() + 527) * 31) + this.setting) * 31;
            int hashCode2 = (this.unknownFieldData == null || this.unknownFieldData.isEmpty()) ? 0 : this.unknownFieldData.hashCode();
            return hashCode + hashCode2;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.setting != 0) {
                output.writeInt32(1, this.setting);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.setting != 0) {
                return size + CodedOutputByteBufferNano.computeInt32Size(1, this.setting);
            }
            return size;
        }

        public GoogleServiceRestrictionKey mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_GET_LINK /*8*/:
                        this.setting = input.readInt32();
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

        public static GoogleServiceRestrictionKey parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (GoogleServiceRestrictionKey) MessageNano.mergeFrom(new GoogleServiceRestrictionKey(), data);
        }

        public static GoogleServiceRestrictionKey parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new GoogleServiceRestrictionKey().mergeFrom(input);
        }
    }

    public interface GoogleServiceSetting {
        public static final int CHROME_BROWSING_MODE_SETTING = 4;
        public static final int GMAIL_ENABLED = 2;
        public static final int GOOGLE_SERVICE_SETTING_UNSPECIFIED = 0;
        public static final int HANGOUT_SETTING = 3;
        public static final int SAFE_SEARCH_SETTING = 1;
    }

    public interface LocationControlMode {
        public static final int LOCATION_CONTROL_MODE_UNSPECIFIED = 0;
        public static final int PARENT_CONTROL = 2;
        public static final int SHARED_CONTROL = 1;
    }

    public static final class LocationMetadata extends ExtendableMessageNano<LocationMetadata> {
        private static volatile LocationMetadata[] _emptyArray;
        public String deviceId;
        public SynthesizedDeviceId synthesizedDeviceId;

        public static LocationMetadata[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new LocationMetadata[0];
                    }
                }
            }
            return _emptyArray;
        }

        public LocationMetadata() {
            clear();
        }

        public LocationMetadata clear() {
            this.deviceId = BuildConfig.VERSION_NAME;
            this.synthesizedDeviceId = null;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof LocationMetadata)) {
                return false;
            }
            LocationMetadata other = (LocationMetadata) o;
            if (this.deviceId == null) {
                if (other.deviceId != null) {
                    return false;
                }
            } else if (!this.deviceId.equals(other.deviceId)) {
                return false;
            }
            if (this.synthesizedDeviceId == null) {
                if (other.synthesizedDeviceId != null) {
                    return false;
                }
            } else if (!this.synthesizedDeviceId.equals(other.synthesizedDeviceId)) {
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
            int hashCode = (((((getClass().getName().hashCode() + 527) * 31) + (this.deviceId == null ? 0 : this.deviceId.hashCode())) * 31) + (this.synthesizedDeviceId == null ? 0 : this.synthesizedDeviceId.hashCode())) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (!this.deviceId.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(1, this.deviceId);
            }
            if (this.synthesizedDeviceId != null) {
                output.writeMessage(2, this.synthesizedDeviceId);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (!this.deviceId.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.deviceId);
            }
            if (this.synthesizedDeviceId != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(2, this.synthesizedDeviceId);
            }
            return size;
        }

        public LocationMetadata mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        this.deviceId = input.readString();
                        continue;
                    case LogSource.GOOGLE_ANALYTICS /*18*/:
                        if (this.synthesizedDeviceId == null) {
                            this.synthesizedDeviceId = new SynthesizedDeviceId();
                        }
                        input.readMessage(this.synthesizedDeviceId);
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

        public static LocationMetadata parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (LocationMetadata) MessageNano.mergeFrom(new LocationMetadata(), data);
        }

        public static LocationMetadata parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new LocationMetadata().mergeFrom(input);
        }
    }

    public interface LocationMode {
        public static final int BATTERY_SAVING = 3;
        public static final int DEVICE_ONLY = 4;
        public static final int HIGH_ACCURACY = 2;
        public static final int LOCATION_MODE_UNSPECIFIED = 0;
        public static final int OFF = 1;
    }

    public static final class LocationRestrictionKey extends ExtendableMessageNano<LocationRestrictionKey> {
        private static volatile LocationRestrictionKey[] _emptyArray;
        public int type;

        public static LocationRestrictionKey[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new LocationRestrictionKey[0];
                    }
                }
            }
            return _emptyArray;
        }

        public LocationRestrictionKey() {
            clear();
        }

        public LocationRestrictionKey clear() {
            this.type = 0;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof LocationRestrictionKey)) {
                return false;
            }
            LocationRestrictionKey other = (LocationRestrictionKey) o;
            if (this.type != other.type) {
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
            int hashCode = (((getClass().getName().hashCode() + 527) * 31) + this.type) * 31;
            int hashCode2 = (this.unknownFieldData == null || this.unknownFieldData.isEmpty()) ? 0 : this.unknownFieldData.hashCode();
            return hashCode + hashCode2;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.type != 0) {
                output.writeInt32(1, this.type);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.type != 0) {
                return size + CodedOutputByteBufferNano.computeInt32Size(1, this.type);
            }
            return size;
        }

        public LocationRestrictionKey mergeFrom(CodedInputByteBufferNano input) throws IOException {
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
                                this.type = value;
                                break;
                            default:
                                continue;
                        }
                    default:
                        if (!storeUnknownField(input, tag)) {
                            break;
                        }
                        continue;
                }
                return this;
            }
        }

        public static LocationRestrictionKey parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (LocationRestrictionKey) MessageNano.mergeFrom(new LocationRestrictionKey(), data);
        }

        public static LocationRestrictionKey parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new LocationRestrictionKey().mergeFrom(input);
        }
    }

    public interface LocationSettingType {
        public static final int LOCATION_CONTROL_MODE = 1;
        public static final int LOCATION_HISTORY_ENABLED = 4;
        public static final int LOCATION_MODE = 2;
        public static final int LOCATION_REPORT_ENABLED = 3;
        public static final int LOCATION_SETTING_TYPE_UNSPECIFIED = 0;
    }

    public static final class LockdownMetadata extends ExtendableMessageNano<LockdownMetadata> {
        private static volatile LockdownMetadata[] _emptyArray;
        public String deviceId;

        public static LockdownMetadata[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new LockdownMetadata[0];
                    }
                }
            }
            return _emptyArray;
        }

        public LockdownMetadata() {
            clear();
        }

        public LockdownMetadata clear() {
            this.deviceId = BuildConfig.VERSION_NAME;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof LockdownMetadata)) {
                return false;
            }
            LockdownMetadata other = (LockdownMetadata) o;
            if (this.deviceId == null) {
                if (other.deviceId != null) {
                    return false;
                }
            } else if (!this.deviceId.equals(other.deviceId)) {
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
            int hashCode = (((getClass().getName().hashCode() + 527) * 31) + (this.deviceId == null ? 0 : this.deviceId.hashCode())) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (!this.deviceId.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(1, this.deviceId);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.deviceId.equals(BuildConfig.VERSION_NAME)) {
                return size;
            }
            return size + CodedOutputByteBufferNano.computeStringSize(1, this.deviceId);
        }

        public LockdownMetadata mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        this.deviceId = input.readString();
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

        public static LockdownMetadata parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (LockdownMetadata) MessageNano.mergeFrom(new LockdownMetadata(), data);
        }

        public static LockdownMetadata parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new LockdownMetadata().mergeFrom(input);
        }
    }

    public static final class LockdownRestrictionKey extends ExtendableMessageNano<LockdownRestrictionKey> {
        private static volatile LockdownRestrictionKey[] _emptyArray;
        public String restrictionName;
        public int type;

        public static LockdownRestrictionKey[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new LockdownRestrictionKey[0];
                    }
                }
            }
            return _emptyArray;
        }

        public LockdownRestrictionKey() {
            clear();
        }

        public LockdownRestrictionKey clear() {
            this.type = 0;
            this.restrictionName = BuildConfig.VERSION_NAME;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof LockdownRestrictionKey)) {
                return false;
            }
            LockdownRestrictionKey other = (LockdownRestrictionKey) o;
            if (this.type != other.type) {
                return false;
            }
            if (this.restrictionName == null) {
                if (other.restrictionName != null) {
                    return false;
                }
            } else if (!this.restrictionName.equals(other.restrictionName)) {
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
            int hashCode = (((((getClass().getName().hashCode() + 527) * 31) + this.type) * 31) + (this.restrictionName == null ? 0 : this.restrictionName.hashCode())) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (!this.restrictionName.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(1, this.restrictionName);
            }
            if (this.type != 0) {
                output.writeInt32(2, this.type);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (!this.restrictionName.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.restrictionName);
            }
            if (this.type != 0) {
                return size + CodedOutputByteBufferNano.computeInt32Size(2, this.type);
            }
            return size;
        }

        public LockdownRestrictionKey mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        this.restrictionName = input.readString();
                        continue;
                    case LogSource.GMS_CORE_PEOPLE /*16*/:
                        int value = input.readInt32();
                        switch (value) {
                            case Action.UNKNOWN /*0*/:
                            case TimeSelection.Type.TEMPORARY /*1*/:
                            case TimeSelection.Type.INDEFINITELY /*2*/:
                            case TimeSelection.Type.CUSTOM /*3*/:
                            case OvenFreshResult.NO_ACCOUNTS /*4*/:
                                this.type = value;
                                break;
                            default:
                                continue;
                        }
                    default:
                        if (!storeUnknownField(input, tag)) {
                            break;
                        }
                        continue;
                }
                return this;
            }
        }

        public static LockdownRestrictionKey parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (LockdownRestrictionKey) MessageNano.mergeFrom(new LockdownRestrictionKey(), data);
        }

        public static LockdownRestrictionKey parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new LockdownRestrictionKey().mergeFrom(input);
        }
    }

    public interface LockdownRestrictionType {
        public static final int ENABLE_ACCOUNT_MANAGEMENT = 4;
        public static final int FORCE_ENABLE_GLS = 2;
        public static final int FORCE_ENABLE_LOCKBOX = 3;
        public static final int LOCKDOWN_RESTRICTION_TYPE_UNSPECIFIED = 0;
        public static final int USER_MANAGER_RESTRICTION = 1;
    }

    public static final class Restriction extends ExtendableMessageNano<Restriction> {
        private static volatile Restriction[] _emptyArray;
        public long deprecatedTag6;
        public String id;
        public RestrictionKey key;
        public RestrictionMetadata metadata;
        public int status;
        public RestrictionValue value;

        public interface Status {
            public static final int DEFAULT = 0;
            public static final int INVISIBLE = 1;
            public static final int UNSUPPORTED = 2;
        }

        public static Restriction[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new Restriction[0];
                    }
                }
            }
            return _emptyArray;
        }

        public Restriction() {
            clear();
        }

        public Restriction clear() {
            this.id = BuildConfig.VERSION_NAME;
            this.key = null;
            this.metadata = null;
            this.value = null;
            this.status = 0;
            this.deprecatedTag6 = 0;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof Restriction)) {
                return false;
            }
            Restriction other = (Restriction) o;
            if (this.id == null) {
                if (other.id != null) {
                    return false;
                }
            } else if (!this.id.equals(other.id)) {
                return false;
            }
            if (this.key == null) {
                if (other.key != null) {
                    return false;
                }
            } else if (!this.key.equals(other.key)) {
                return false;
            }
            if (this.metadata == null) {
                if (other.metadata != null) {
                    return false;
                }
            } else if (!this.metadata.equals(other.metadata)) {
                return false;
            }
            if (this.value == null) {
                if (other.value != null) {
                    return false;
                }
            } else if (!this.value.equals(other.value)) {
                return false;
            }
            if (this.status != other.status || this.deprecatedTag6 != other.deprecatedTag6) {
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
            int hashCode = (((((((((((((getClass().getName().hashCode() + 527) * 31) + (this.id == null ? 0 : this.id.hashCode())) * 31) + (this.key == null ? 0 : this.key.hashCode())) * 31) + (this.metadata == null ? 0 : this.metadata.hashCode())) * 31) + (this.value == null ? 0 : this.value.hashCode())) * 31) + this.status) * 31) + ((int) (this.deprecatedTag6 ^ (this.deprecatedTag6 >>> 32)))) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.key != null) {
                output.writeMessage(1, this.key);
            }
            if (this.metadata != null) {
                output.writeMessage(2, this.metadata);
            }
            if (this.value != null) {
                output.writeMessage(3, this.value);
            }
            if (!this.id.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(4, this.id);
            }
            if (this.status != 0) {
                output.writeInt32(5, this.status);
            }
            if (this.deprecatedTag6 != 0) {
                output.writeInt64(6, this.deprecatedTag6);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.key != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(1, this.key);
            }
            if (this.metadata != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(2, this.metadata);
            }
            if (this.value != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(3, this.value);
            }
            if (!this.id.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(4, this.id);
            }
            if (this.status != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(5, this.status);
            }
            if (this.deprecatedTag6 != 0) {
                return size + CodedOutputByteBufferNano.computeInt64Size(6, this.deprecatedTag6);
            }
            return size;
        }

        public Restriction mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        if (this.key == null) {
                            this.key = new RestrictionKey();
                        }
                        input.readMessage(this.key);
                        continue;
                    case LogSource.GOOGLE_ANALYTICS /*18*/:
                        if (this.metadata == null) {
                            this.metadata = new RestrictionMetadata();
                        }
                        input.readMessage(this.metadata);
                        continue;
                    case LogSource.ANDROID_CAMERA /*26*/:
                        if (this.value == null) {
                            this.value = new RestrictionValue();
                        }
                        input.readMessage(this.value);
                        continue;
                    case LogSource.NOVA /*34*/:
                        this.id = input.readString();
                        continue;
                    case LogSource.COPRESENCE /*40*/:
                        int value = input.readInt32();
                        switch (value) {
                            case Action.UNKNOWN /*0*/:
                            case TimeSelection.Type.TEMPORARY /*1*/:
                            case TimeSelection.Type.INDEFINITELY /*2*/:
                                this.status = value;
                                break;
                            default:
                                continue;
                        }
                    case LogSource.BACKDROP /*48*/:
                        this.deprecatedTag6 = input.readInt64();
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

        public static Restriction parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (Restriction) MessageNano.mergeFrom(new Restriction(), data);
        }

        public static Restriction parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new Restriction().mergeFrom(input);
        }
    }

    public interface RestrictionDomain {
        public static final int ANDROID_APP = 1;
        public static final int GOOGLE_SERVICE = 3;
        public static final int LOCATION = 4;
        public static final int LOCKDOWN = 2;
        public static final int RESTRICTION_DOMAIN_UNSPECIFIED = 0;
    }

    public static final class RestrictionFilter extends ExtendableMessageNano<RestrictionFilter> {
        private static volatile RestrictionFilter[] _emptyArray;
        public String[] androidApps;
        public String[] deviceIds;
        public int[] domains;
        public boolean includeInvisibleLockdowns;
        public boolean includeSynthesizedDevices;
        public boolean includeUnsupportedLockdowns;
        public boolean stripRestrictionMetadata;

        public static RestrictionFilter[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new RestrictionFilter[0];
                    }
                }
            }
            return _emptyArray;
        }

        public RestrictionFilter() {
            clear();
        }

        public RestrictionFilter clear() {
            this.domains = WireFormatNano.EMPTY_INT_ARRAY;
            this.stripRestrictionMetadata = false;
            this.includeInvisibleLockdowns = false;
            this.includeUnsupportedLockdowns = false;
            this.includeSynthesizedDevices = false;
            this.androidApps = WireFormatNano.EMPTY_STRING_ARRAY;
            this.deviceIds = WireFormatNano.EMPTY_STRING_ARRAY;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof RestrictionFilter)) {
                return false;
            }
            RestrictionFilter other = (RestrictionFilter) o;
            if (!InternalNano.equals(this.domains, other.domains) || this.stripRestrictionMetadata != other.stripRestrictionMetadata || this.includeInvisibleLockdowns != other.includeInvisibleLockdowns || this.includeUnsupportedLockdowns != other.includeUnsupportedLockdowns || this.includeSynthesizedDevices != other.includeSynthesizedDevices || !InternalNano.equals(this.androidApps, other.androidApps) || !InternalNano.equals(this.deviceIds, other.deviceIds)) {
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
            int hashCode = (((((getClass().getName().hashCode() + 527) * 31) + InternalNano.hashCode(this.domains)) * 31) + (this.stripRestrictionMetadata ? 1231 : 1237)) * 31;
            if (this.includeInvisibleLockdowns) {
                i = 1231;
            } else {
                i = 1237;
            }
            hashCode = (hashCode + i) * 31;
            if (this.includeUnsupportedLockdowns) {
                i = 1231;
            } else {
                i = 1237;
            }
            i = (hashCode + i) * 31;
            if (!this.includeSynthesizedDevices) {
                i2 = 1237;
            }
            i2 = (((((i + i2) * 31) + InternalNano.hashCode(this.androidApps)) * 31) + InternalNano.hashCode(this.deviceIds)) * 31;
            i = (this.unknownFieldData == null || this.unknownFieldData.isEmpty()) ? 0 : this.unknownFieldData.hashCode();
            return i2 + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.domains != null && this.domains.length > 0) {
                for (int writeInt32 : this.domains) {
                    output.writeInt32(1, writeInt32);
                }
            }
            if (this.stripRestrictionMetadata) {
                output.writeBool(2, this.stripRestrictionMetadata);
            }
            if (this.includeInvisibleLockdowns) {
                output.writeBool(3, this.includeInvisibleLockdowns);
            }
            if (this.androidApps != null && this.androidApps.length > 0) {
                for (String element : this.androidApps) {
                    if (element != null) {
                        output.writeString(4, element);
                    }
                }
            }
            if (this.deviceIds != null && this.deviceIds.length > 0) {
                for (String element2 : this.deviceIds) {
                    if (element2 != null) {
                        output.writeString(5, element2);
                    }
                }
            }
            if (this.includeUnsupportedLockdowns) {
                output.writeBool(6, this.includeUnsupportedLockdowns);
            }
            if (this.includeSynthesizedDevices) {
                output.writeBool(7, this.includeSynthesizedDevices);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int dataSize;
            int dataCount;
            int size = super.computeSerializedSize();
            if (this.domains != null && this.domains.length > 0) {
                dataSize = 0;
                for (int element : this.domains) {
                    dataSize += CodedOutputByteBufferNano.computeInt32SizeNoTag(element);
                }
                size = (size + dataSize) + (this.domains.length * 1);
            }
            if (this.stripRestrictionMetadata) {
                size += CodedOutputByteBufferNano.computeBoolSize(2, this.stripRestrictionMetadata);
            }
            if (this.includeInvisibleLockdowns) {
                size += CodedOutputByteBufferNano.computeBoolSize(3, this.includeInvisibleLockdowns);
            }
            if (this.androidApps != null && this.androidApps.length > 0) {
                dataCount = 0;
                dataSize = 0;
                for (String element2 : this.androidApps) {
                    if (element2 != null) {
                        dataCount++;
                        dataSize += CodedOutputByteBufferNano.computeStringSizeNoTag(element2);
                    }
                }
                size = (size + dataSize) + (dataCount * 1);
            }
            if (this.deviceIds != null && this.deviceIds.length > 0) {
                dataCount = 0;
                dataSize = 0;
                for (String element22 : this.deviceIds) {
                    if (element22 != null) {
                        dataCount++;
                        dataSize += CodedOutputByteBufferNano.computeStringSizeNoTag(element22);
                    }
                }
                size = (size + dataSize) + (dataCount * 1);
            }
            if (this.includeUnsupportedLockdowns) {
                size += CodedOutputByteBufferNano.computeBoolSize(6, this.includeUnsupportedLockdowns);
            }
            if (this.includeSynthesizedDevices) {
                return size + CodedOutputByteBufferNano.computeBoolSize(7, this.includeSynthesizedDevices);
            }
            return size;
        }

        public RestrictionFilter mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                int i;
                int value;
                int[] newArray;
                int arrayLength;
                String[] newArray2;
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
                            i = this.domains == null ? 0 : this.domains.length;
                            if (i != 0 || validCount != validValues.length) {
                                newArray = new int[(i + validCount)];
                                if (i != 0) {
                                    System.arraycopy(this.domains, 0, newArray, 0, i);
                                }
                                System.arraycopy(validValues, 0, newArray, i, validCount);
                                this.domains = newArray;
                                break;
                            }
                            this.domains = validValues;
                            break;
                        }
                        continue;
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        int limit = input.pushLimit(input.readRawVarint32());
                        arrayLength = 0;
                        int startPos = input.getPosition();
                        while (input.getBytesUntilLimit() > 0) {
                            switch (input.readInt32()) {
                                case Action.UNKNOWN /*0*/:
                                case TimeSelection.Type.TEMPORARY /*1*/:
                                case TimeSelection.Type.INDEFINITELY /*2*/:
                                case TimeSelection.Type.CUSTOM /*3*/:
                                case OvenFreshResult.NO_ACCOUNTS /*4*/:
                                    arrayLength++;
                                    break;
                                default:
                                    break;
                            }
                        }
                        if (arrayLength != 0) {
                            input.rewindToPosition(startPos);
                            if (this.domains == null) {
                                i = 0;
                            } else {
                                i = this.domains.length;
                            }
                            newArray = new int[(i + arrayLength)];
                            if (i != 0) {
                                System.arraycopy(this.domains, 0, newArray, 0, i);
                            }
                            while (input.getBytesUntilLimit() > 0) {
                                value = input.readInt32();
                                switch (value) {
                                    case Action.UNKNOWN /*0*/:
                                    case TimeSelection.Type.TEMPORARY /*1*/:
                                    case TimeSelection.Type.INDEFINITELY /*2*/:
                                    case TimeSelection.Type.CUSTOM /*3*/:
                                    case OvenFreshResult.NO_ACCOUNTS /*4*/:
                                        int i2 = i + 1;
                                        newArray[i] = value;
                                        i = i2;
                                        break;
                                    default:
                                        break;
                                }
                            }
                            this.domains = newArray;
                        }
                        input.popLimit(limit);
                        continue;
                    case LogSource.GMS_CORE_PEOPLE /*16*/:
                        this.stripRestrictionMetadata = input.readBool();
                        continue;
                    case LogSource.LB_C /*24*/:
                        this.includeInvisibleLockdowns = input.readBool();
                        continue;
                    case LogSource.NOVA /*34*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 34);
                        i = this.androidApps == null ? 0 : this.androidApps.length;
                        newArray2 = new String[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.androidApps, 0, newArray2, 0, i);
                        }
                        while (i < newArray2.length - 1) {
                            newArray2[i] = input.readString();
                            input.readTag();
                            i++;
                        }
                        newArray2[i] = input.readString();
                        this.androidApps = newArray2;
                        continue;
                    case LogSource.PHOTOS /*42*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 42);
                        i = this.deviceIds == null ? 0 : this.deviceIds.length;
                        newArray2 = new String[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.deviceIds, 0, newArray2, 0, i);
                        }
                        while (i < newArray2.length - 1) {
                            newArray2[i] = input.readString();
                            input.readTag();
                            i++;
                        }
                        newArray2[i] = input.readString();
                        this.deviceIds = newArray2;
                        continue;
                    case LogSource.BACKDROP /*48*/:
                        this.includeUnsupportedLockdowns = input.readBool();
                        continue;
                    case LogSource.DOCS /*56*/:
                        this.includeSynthesizedDevices = input.readBool();
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

        public static RestrictionFilter parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (RestrictionFilter) MessageNano.mergeFrom(new RestrictionFilter(), data);
        }

        public static RestrictionFilter parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new RestrictionFilter().mergeFrom(input);
        }
    }

    public static final class RestrictionFilterToken extends ExtendableMessageNano<RestrictionFilterToken> {
        private static volatile RestrictionFilterToken[] _emptyArray;
        public byte[] zookie;

        public static RestrictionFilterToken[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new RestrictionFilterToken[0];
                    }
                }
            }
            return _emptyArray;
        }

        public RestrictionFilterToken() {
            clear();
        }

        public RestrictionFilterToken clear() {
            this.zookie = WireFormatNano.EMPTY_BYTES;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof RestrictionFilterToken)) {
                return false;
            }
            RestrictionFilterToken other = (RestrictionFilterToken) o;
            if (!Arrays.equals(this.zookie, other.zookie)) {
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
            int hashCode = (((getClass().getName().hashCode() + 527) * 31) + Arrays.hashCode(this.zookie)) * 31;
            int hashCode2 = (this.unknownFieldData == null || this.unknownFieldData.isEmpty()) ? 0 : this.unknownFieldData.hashCode();
            return hashCode + hashCode2;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (!Arrays.equals(this.zookie, WireFormatNano.EMPTY_BYTES)) {
                output.writeBytes(1, this.zookie);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (Arrays.equals(this.zookie, WireFormatNano.EMPTY_BYTES)) {
                return size;
            }
            return size + CodedOutputByteBufferNano.computeBytesSize(1, this.zookie);
        }

        public RestrictionFilterToken mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        this.zookie = input.readBytes();
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

        public static RestrictionFilterToken parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (RestrictionFilterToken) MessageNano.mergeFrom(new RestrictionFilterToken(), data);
        }

        public static RestrictionFilterToken parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new RestrictionFilterToken().mergeFrom(input);
        }
    }

    public static final class RestrictionGroup extends ExtendableMessageNano<RestrictionGroup> {
        private static volatile RestrictionGroup[] _emptyArray;
        public AndroidAppMetadata androidAppMetadata;
        public byte[] deprecatedTag2;
        public int domain;
        public GoogleServiceMetadata googleServiceMetadata;
        public LocationMetadata locationMetadata;
        public LockdownMetadata lockdownMetadata;
        public Restriction[] restrictions;

        public static RestrictionGroup[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new RestrictionGroup[0];
                    }
                }
            }
            return _emptyArray;
        }

        public RestrictionGroup() {
            clear();
        }

        public RestrictionGroup clear() {
            this.domain = 0;
            this.androidAppMetadata = null;
            this.googleServiceMetadata = null;
            this.lockdownMetadata = null;
            this.locationMetadata = null;
            this.restrictions = Restriction.emptyArray();
            this.deprecatedTag2 = WireFormatNano.EMPTY_BYTES;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof RestrictionGroup)) {
                return false;
            }
            RestrictionGroup other = (RestrictionGroup) o;
            if (this.domain != other.domain) {
                return false;
            }
            if (this.androidAppMetadata == null) {
                if (other.androidAppMetadata != null) {
                    return false;
                }
            } else if (!this.androidAppMetadata.equals(other.androidAppMetadata)) {
                return false;
            }
            if (this.googleServiceMetadata == null) {
                if (other.googleServiceMetadata != null) {
                    return false;
                }
            } else if (!this.googleServiceMetadata.equals(other.googleServiceMetadata)) {
                return false;
            }
            if (this.lockdownMetadata == null) {
                if (other.lockdownMetadata != null) {
                    return false;
                }
            } else if (!this.lockdownMetadata.equals(other.lockdownMetadata)) {
                return false;
            }
            if (this.locationMetadata == null) {
                if (other.locationMetadata != null) {
                    return false;
                }
            } else if (!this.locationMetadata.equals(other.locationMetadata)) {
                return false;
            }
            if (!InternalNano.equals(this.restrictions, other.restrictions) || !Arrays.equals(this.deprecatedTag2, other.deprecatedTag2)) {
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
            int hashCode = (((((((((((((((getClass().getName().hashCode() + 527) * 31) + this.domain) * 31) + (this.androidAppMetadata == null ? 0 : this.androidAppMetadata.hashCode())) * 31) + (this.googleServiceMetadata == null ? 0 : this.googleServiceMetadata.hashCode())) * 31) + (this.lockdownMetadata == null ? 0 : this.lockdownMetadata.hashCode())) * 31) + (this.locationMetadata == null ? 0 : this.locationMetadata.hashCode())) * 31) + InternalNano.hashCode(this.restrictions)) * 31) + Arrays.hashCode(this.deprecatedTag2)) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.domain != 0) {
                output.writeInt32(1, this.domain);
            }
            if (!Arrays.equals(this.deprecatedTag2, WireFormatNano.EMPTY_BYTES)) {
                output.writeBytes(2, this.deprecatedTag2);
            }
            if (this.restrictions != null && this.restrictions.length > 0) {
                for (Restriction element : this.restrictions) {
                    if (element != null) {
                        output.writeMessage(3, element);
                    }
                }
            }
            if (this.googleServiceMetadata != null) {
                output.writeMessage(4, this.googleServiceMetadata);
            }
            if (this.androidAppMetadata != null) {
                output.writeMessage(5, this.androidAppMetadata);
            }
            if (this.lockdownMetadata != null) {
                output.writeMessage(6, this.lockdownMetadata);
            }
            if (this.locationMetadata != null) {
                output.writeMessage(7, this.locationMetadata);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.domain != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(1, this.domain);
            }
            if (!Arrays.equals(this.deprecatedTag2, WireFormatNano.EMPTY_BYTES)) {
                size += CodedOutputByteBufferNano.computeBytesSize(2, this.deprecatedTag2);
            }
            if (this.restrictions != null && this.restrictions.length > 0) {
                for (Restriction element : this.restrictions) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(3, element);
                    }
                }
            }
            if (this.googleServiceMetadata != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(4, this.googleServiceMetadata);
            }
            if (this.androidAppMetadata != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(5, this.androidAppMetadata);
            }
            if (this.lockdownMetadata != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(6, this.lockdownMetadata);
            }
            if (this.locationMetadata != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(7, this.locationMetadata);
            }
            return size;
        }

        public RestrictionGroup mergeFrom(CodedInputByteBufferNano input) throws IOException {
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
                                this.domain = value;
                                break;
                            default:
                                continue;
                        }
                    case LogSource.GOOGLE_ANALYTICS /*18*/:
                        this.deprecatedTag2 = input.readBytes();
                        continue;
                    case LogSource.ANDROID_CAMERA /*26*/:
                        int i;
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 26);
                        if (this.restrictions == null) {
                            i = 0;
                        } else {
                            i = this.restrictions.length;
                        }
                        Restriction[] newArray = new Restriction[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.restrictions, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new Restriction();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i++;
                        }
                        newArray[i] = new Restriction();
                        input.readMessage(newArray[i]);
                        this.restrictions = newArray;
                        continue;
                    case LogSource.NOVA /*34*/:
                        if (this.googleServiceMetadata == null) {
                            this.googleServiceMetadata = new GoogleServiceMetadata();
                        }
                        input.readMessage(this.googleServiceMetadata);
                        continue;
                    case LogSource.PHOTOS /*42*/:
                        if (this.androidAppMetadata == null) {
                            this.androidAppMetadata = new AndroidAppMetadata();
                        }
                        input.readMessage(this.androidAppMetadata);
                        continue;
                    case LogSource.HANGOUT_LOG_REQUEST /*50*/:
                        if (this.lockdownMetadata == null) {
                            this.lockdownMetadata = new LockdownMetadata();
                        }
                        input.readMessage(this.lockdownMetadata);
                        continue;
                    case LogSource.SLIDES /*58*/:
                        if (this.locationMetadata == null) {
                            this.locationMetadata = new LocationMetadata();
                        }
                        input.readMessage(this.locationMetadata);
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

        public static RestrictionGroup parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (RestrictionGroup) MessageNano.mergeFrom(new RestrictionGroup(), data);
        }

        public static RestrictionGroup parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new RestrictionGroup().mergeFrom(input);
        }
    }

    public static final class RestrictionKey extends ExtendableMessageNano<RestrictionKey> {
        private static volatile RestrictionKey[] _emptyArray;
        public AndroidAppRestrictionKey androidAppKey;
        public String deviceId;
        public int domain;
        public GoogleServiceRestrictionKey googleServiceKey;
        public LocationRestrictionKey locationKey;
        public LockdownRestrictionKey lockdownKey;

        public static RestrictionKey[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new RestrictionKey[0];
                    }
                }
            }
            return _emptyArray;
        }

        public RestrictionKey() {
            clear();
        }

        public RestrictionKey clear() {
            this.domain = 0;
            this.androidAppKey = null;
            this.lockdownKey = null;
            this.googleServiceKey = null;
            this.locationKey = null;
            this.deviceId = BuildConfig.VERSION_NAME;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof RestrictionKey)) {
                return false;
            }
            RestrictionKey other = (RestrictionKey) o;
            if (this.domain != other.domain) {
                return false;
            }
            if (this.androidAppKey == null) {
                if (other.androidAppKey != null) {
                    return false;
                }
            } else if (!this.androidAppKey.equals(other.androidAppKey)) {
                return false;
            }
            if (this.lockdownKey == null) {
                if (other.lockdownKey != null) {
                    return false;
                }
            } else if (!this.lockdownKey.equals(other.lockdownKey)) {
                return false;
            }
            if (this.googleServiceKey == null) {
                if (other.googleServiceKey != null) {
                    return false;
                }
            } else if (!this.googleServiceKey.equals(other.googleServiceKey)) {
                return false;
            }
            if (this.locationKey == null) {
                if (other.locationKey != null) {
                    return false;
                }
            } else if (!this.locationKey.equals(other.locationKey)) {
                return false;
            }
            if (this.deviceId == null) {
                if (other.deviceId != null) {
                    return false;
                }
            } else if (!this.deviceId.equals(other.deviceId)) {
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
            int hashCode = (((((((((((((getClass().getName().hashCode() + 527) * 31) + this.domain) * 31) + (this.androidAppKey == null ? 0 : this.androidAppKey.hashCode())) * 31) + (this.lockdownKey == null ? 0 : this.lockdownKey.hashCode())) * 31) + (this.googleServiceKey == null ? 0 : this.googleServiceKey.hashCode())) * 31) + (this.locationKey == null ? 0 : this.locationKey.hashCode())) * 31) + (this.deviceId == null ? 0 : this.deviceId.hashCode())) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.androidAppKey != null) {
                output.writeMessage(3, this.androidAppKey);
            }
            if (this.lockdownKey != null) {
                output.writeMessage(5, this.lockdownKey);
            }
            if (this.domain != 0) {
                output.writeInt32(6, this.domain);
            }
            if (this.googleServiceKey != null) {
                output.writeMessage(7, this.googleServiceKey);
            }
            if (this.locationKey != null) {
                output.writeMessage(8, this.locationKey);
            }
            if (!this.deviceId.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(9, this.deviceId);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.androidAppKey != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(3, this.androidAppKey);
            }
            if (this.lockdownKey != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(5, this.lockdownKey);
            }
            if (this.domain != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(6, this.domain);
            }
            if (this.googleServiceKey != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(7, this.googleServiceKey);
            }
            if (this.locationKey != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(8, this.locationKey);
            }
            if (this.deviceId.equals(BuildConfig.VERSION_NAME)) {
                return size;
            }
            return size + CodedOutputByteBufferNano.computeStringSize(9, this.deviceId);
        }

        public RestrictionKey mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case LogSource.ANDROID_CAMERA /*26*/:
                        if (this.androidAppKey == null) {
                            this.androidAppKey = new AndroidAppRestrictionKey();
                        }
                        input.readMessage(this.androidAppKey);
                        continue;
                    case LogSource.PHOTOS /*42*/:
                        if (this.lockdownKey == null) {
                            this.lockdownKey = new LockdownRestrictionKey();
                        }
                        input.readMessage(this.lockdownKey);
                        continue;
                    case LogSource.BACKDROP /*48*/:
                        int value = input.readInt32();
                        switch (value) {
                            case Action.UNKNOWN /*0*/:
                            case TimeSelection.Type.TEMPORARY /*1*/:
                            case TimeSelection.Type.INDEFINITELY /*2*/:
                            case TimeSelection.Type.CUSTOM /*3*/:
                            case OvenFreshResult.NO_ACCOUNTS /*4*/:
                                this.domain = value;
                                break;
                            default:
                                continue;
                        }
                    case LogSource.SLIDES /*58*/:
                        if (this.googleServiceKey == null) {
                            this.googleServiceKey = new GoogleServiceRestrictionKey();
                        }
                        input.readMessage(this.googleServiceKey);
                        continue;
                    case LogSource.WIFI_ASSISTANT /*66*/:
                        if (this.locationKey == null) {
                            this.locationKey = new LocationRestrictionKey();
                        }
                        input.readMessage(this.locationKey);
                        continue;
                    case LogSource.MOVIES /*74*/:
                        this.deviceId = input.readString();
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

        public static RestrictionKey parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (RestrictionKey) MessageNano.mergeFrom(new RestrictionKey(), data);
        }

        public static RestrictionKey parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new RestrictionKey().mergeFrom(input);
        }
    }

    public static final class RestrictionMetadata extends ExtendableMessageNano<RestrictionMetadata> {
        private static volatile RestrictionMetadata[] _emptyArray;
        public Choice[] choices;
        public RestrictionValue defaultValue;
        public String description;
        public String title;
        public int type;

        public static final class Choice extends ExtendableMessageNano<Choice> {
            private static volatile Choice[] _emptyArray;
            public String description;
            public String value;

            public static Choice[] emptyArray() {
                if (_emptyArray == null) {
                    synchronized (InternalNano.LAZY_INIT_LOCK) {
                        if (_emptyArray == null) {
                            _emptyArray = new Choice[0];
                        }
                    }
                }
                return _emptyArray;
            }

            public Choice() {
                clear();
            }

            public Choice clear() {
                this.description = BuildConfig.VERSION_NAME;
                this.value = BuildConfig.VERSION_NAME;
                this.unknownFieldData = null;
                this.cachedSize = -1;
                return this;
            }

            public boolean equals(Object o) {
                if (o == this) {
                    return true;
                }
                if (!(o instanceof Choice)) {
                    return false;
                }
                Choice other = (Choice) o;
                if (this.description == null) {
                    if (other.description != null) {
                        return false;
                    }
                } else if (!this.description.equals(other.description)) {
                    return false;
                }
                if (this.value == null) {
                    if (other.value != null) {
                        return false;
                    }
                } else if (!this.value.equals(other.value)) {
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
                int hashCode = (((((getClass().getName().hashCode() + 527) * 31) + (this.description == null ? 0 : this.description.hashCode())) * 31) + (this.value == null ? 0 : this.value.hashCode())) * 31;
                if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                    i = this.unknownFieldData.hashCode();
                }
                return hashCode + i;
            }

            public void writeTo(CodedOutputByteBufferNano output) throws IOException {
                if (!this.description.equals(BuildConfig.VERSION_NAME)) {
                    output.writeString(1, this.description);
                }
                if (!this.value.equals(BuildConfig.VERSION_NAME)) {
                    output.writeString(2, this.value);
                }
                super.writeTo(output);
            }

            protected int computeSerializedSize() {
                int size = super.computeSerializedSize();
                if (!this.description.equals(BuildConfig.VERSION_NAME)) {
                    size += CodedOutputByteBufferNano.computeStringSize(1, this.description);
                }
                if (this.value.equals(BuildConfig.VERSION_NAME)) {
                    return size;
                }
                return size + CodedOutputByteBufferNano.computeStringSize(2, this.value);
            }

            public Choice mergeFrom(CodedInputByteBufferNano input) throws IOException {
                while (true) {
                    int tag = input.readTag();
                    switch (tag) {
                        case Action.UNKNOWN /*0*/:
                            break;
                        case Type.TAP_ABOUT /*10*/:
                            this.description = input.readString();
                            continue;
                        case LogSource.GOOGLE_ANALYTICS /*18*/:
                            this.value = input.readString();
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

            public static Choice parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
                return (Choice) MessageNano.mergeFrom(new Choice(), data);
            }

            public static Choice parseFrom(CodedInputByteBufferNano input) throws IOException {
                return new Choice().mergeFrom(input);
            }
        }

        public interface ValueType {
            public static final int BOOL = 1;
            public static final int CHOICE = 5;
            public static final int INTEGER = 4;
            public static final int MULTISELECT = 3;
            public static final int STRING = 2;
            public static final int UNSPECIFIED = 0;
        }

        public static RestrictionMetadata[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new RestrictionMetadata[0];
                    }
                }
            }
            return _emptyArray;
        }

        public RestrictionMetadata() {
            clear();
        }

        public RestrictionMetadata clear() {
            this.title = BuildConfig.VERSION_NAME;
            this.description = BuildConfig.VERSION_NAME;
            this.type = 0;
            this.defaultValue = null;
            this.choices = Choice.emptyArray();
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof RestrictionMetadata)) {
                return false;
            }
            RestrictionMetadata other = (RestrictionMetadata) o;
            if (this.title == null) {
                if (other.title != null) {
                    return false;
                }
            } else if (!this.title.equals(other.title)) {
                return false;
            }
            if (this.description == null) {
                if (other.description != null) {
                    return false;
                }
            } else if (!this.description.equals(other.description)) {
                return false;
            }
            if (this.type != other.type) {
                return false;
            }
            if (this.defaultValue == null) {
                if (other.defaultValue != null) {
                    return false;
                }
            } else if (!this.defaultValue.equals(other.defaultValue)) {
                return false;
            }
            if (!InternalNano.equals(this.choices, other.choices)) {
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
            int hashCode = (((((((((((getClass().getName().hashCode() + 527) * 31) + (this.title == null ? 0 : this.title.hashCode())) * 31) + (this.description == null ? 0 : this.description.hashCode())) * 31) + this.type) * 31) + (this.defaultValue == null ? 0 : this.defaultValue.hashCode())) * 31) + InternalNano.hashCode(this.choices)) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (!this.title.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(1, this.title);
            }
            if (!this.description.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(2, this.description);
            }
            if (this.type != 0) {
                output.writeInt32(3, this.type);
            }
            if (this.defaultValue != null) {
                output.writeMessage(4, this.defaultValue);
            }
            if (this.choices != null && this.choices.length > 0) {
                for (Choice element : this.choices) {
                    if (element != null) {
                        output.writeMessage(5, element);
                    }
                }
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (!this.title.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.title);
            }
            if (!this.description.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(2, this.description);
            }
            if (this.type != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(3, this.type);
            }
            if (this.defaultValue != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(4, this.defaultValue);
            }
            if (this.choices != null && this.choices.length > 0) {
                for (Choice element : this.choices) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(5, element);
                    }
                }
            }
            return size;
        }

        public RestrictionMetadata mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        this.title = input.readString();
                        continue;
                    case LogSource.GOOGLE_ANALYTICS /*18*/:
                        this.description = input.readString();
                        continue;
                    case LogSource.LB_C /*24*/:
                        int value = input.readInt32();
                        switch (value) {
                            case Action.UNKNOWN /*0*/:
                            case TimeSelection.Type.TEMPORARY /*1*/:
                            case TimeSelection.Type.INDEFINITELY /*2*/:
                            case TimeSelection.Type.CUSTOM /*3*/:
                            case OvenFreshResult.NO_ACCOUNTS /*4*/:
                            case Type.ADD_NEW_SHARES /*5*/:
                                this.type = value;
                                break;
                            default:
                                continue;
                        }
                    case LogSource.NOVA /*34*/:
                        if (this.defaultValue == null) {
                            this.defaultValue = new RestrictionValue();
                        }
                        input.readMessage(this.defaultValue);
                        continue;
                    case LogSource.PHOTOS /*42*/:
                        int i;
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 42);
                        if (this.choices == null) {
                            i = 0;
                        } else {
                            i = this.choices.length;
                        }
                        Choice[] newArray = new Choice[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.choices, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new Choice();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i++;
                        }
                        newArray[i] = new Choice();
                        input.readMessage(newArray[i]);
                        this.choices = newArray;
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

        public static RestrictionMetadata parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (RestrictionMetadata) MessageNano.mergeFrom(new RestrictionMetadata(), data);
        }

        public static RestrictionMetadata parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new RestrictionMetadata().mergeFrom(input);
        }
    }

    public static final class RestrictionValue extends ExtendableMessageNano<RestrictionValue> {
        private static volatile RestrictionValue[] _emptyArray;
        public boolean boolValue;
        public int integerValue;
        public MutationInfo mutationInfo;
        public StringList stringListValue;
        public String stringValue;

        public static final class MutationInfo extends ExtendableMessageNano<MutationInfo> {
            private static volatile MutationInfo[] _emptyArray;
            public long mutatedTimestampMillis;

            public static MutationInfo[] emptyArray() {
                if (_emptyArray == null) {
                    synchronized (InternalNano.LAZY_INIT_LOCK) {
                        if (_emptyArray == null) {
                            _emptyArray = new MutationInfo[0];
                        }
                    }
                }
                return _emptyArray;
            }

            public MutationInfo() {
                clear();
            }

            public MutationInfo clear() {
                this.mutatedTimestampMillis = 0;
                this.unknownFieldData = null;
                this.cachedSize = -1;
                return this;
            }

            public boolean equals(Object o) {
                if (o == this) {
                    return true;
                }
                if (!(o instanceof MutationInfo)) {
                    return false;
                }
                MutationInfo other = (MutationInfo) o;
                if (this.mutatedTimestampMillis != other.mutatedTimestampMillis) {
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
                int hashCode = (((getClass().getName().hashCode() + 527) * 31) + ((int) (this.mutatedTimestampMillis ^ (this.mutatedTimestampMillis >>> 32)))) * 31;
                int hashCode2 = (this.unknownFieldData == null || this.unknownFieldData.isEmpty()) ? 0 : this.unknownFieldData.hashCode();
                return hashCode + hashCode2;
            }

            public void writeTo(CodedOutputByteBufferNano output) throws IOException {
                if (this.mutatedTimestampMillis != 0) {
                    output.writeInt64(2, this.mutatedTimestampMillis);
                }
                super.writeTo(output);
            }

            protected int computeSerializedSize() {
                int size = super.computeSerializedSize();
                if (this.mutatedTimestampMillis != 0) {
                    return size + CodedOutputByteBufferNano.computeInt64Size(2, this.mutatedTimestampMillis);
                }
                return size;
            }

            public MutationInfo mergeFrom(CodedInputByteBufferNano input) throws IOException {
                while (true) {
                    int tag = input.readTag();
                    switch (tag) {
                        case Action.UNKNOWN /*0*/:
                            break;
                        case LogSource.GMS_CORE_PEOPLE /*16*/:
                            this.mutatedTimestampMillis = input.readInt64();
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

            public static MutationInfo parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
                return (MutationInfo) MessageNano.mergeFrom(new MutationInfo(), data);
            }

            public static MutationInfo parseFrom(CodedInputByteBufferNano input) throws IOException {
                return new MutationInfo().mergeFrom(input);
            }
        }

        public static final class StringList extends ExtendableMessageNano<StringList> {
            private static volatile StringList[] _emptyArray;
            public String[] entries;

            public static StringList[] emptyArray() {
                if (_emptyArray == null) {
                    synchronized (InternalNano.LAZY_INIT_LOCK) {
                        if (_emptyArray == null) {
                            _emptyArray = new StringList[0];
                        }
                    }
                }
                return _emptyArray;
            }

            public StringList() {
                clear();
            }

            public StringList clear() {
                this.entries = WireFormatNano.EMPTY_STRING_ARRAY;
                this.unknownFieldData = null;
                this.cachedSize = -1;
                return this;
            }

            public boolean equals(Object o) {
                if (o == this) {
                    return true;
                }
                if (!(o instanceof StringList)) {
                    return false;
                }
                StringList other = (StringList) o;
                if (!InternalNano.equals(this.entries, other.entries)) {
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
                int hashCode = (((getClass().getName().hashCode() + 527) * 31) + InternalNano.hashCode(this.entries)) * 31;
                int hashCode2 = (this.unknownFieldData == null || this.unknownFieldData.isEmpty()) ? 0 : this.unknownFieldData.hashCode();
                return hashCode + hashCode2;
            }

            public void writeTo(CodedOutputByteBufferNano output) throws IOException {
                if (this.entries != null && this.entries.length > 0) {
                    for (String element : this.entries) {
                        if (element != null) {
                            output.writeString(1, element);
                        }
                    }
                }
                super.writeTo(output);
            }

            protected int computeSerializedSize() {
                int size = super.computeSerializedSize();
                if (this.entries == null || this.entries.length <= 0) {
                    return size;
                }
                int dataCount = 0;
                int dataSize = 0;
                for (String element : this.entries) {
                    if (element != null) {
                        dataCount++;
                        dataSize += CodedOutputByteBufferNano.computeStringSizeNoTag(element);
                    }
                }
                return (size + dataSize) + (dataCount * 1);
            }

            public StringList mergeFrom(CodedInputByteBufferNano input) throws IOException {
                while (true) {
                    int tag = input.readTag();
                    switch (tag) {
                        case Action.UNKNOWN /*0*/:
                            break;
                        case Type.TAP_ABOUT /*10*/:
                            int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 10);
                            int i = this.entries == null ? 0 : this.entries.length;
                            String[] newArray = new String[(i + arrayLength)];
                            if (i != 0) {
                                System.arraycopy(this.entries, 0, newArray, 0, i);
                            }
                            while (i < newArray.length - 1) {
                                newArray[i] = input.readString();
                                input.readTag();
                                i++;
                            }
                            newArray[i] = input.readString();
                            this.entries = newArray;
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

            public static StringList parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
                return (StringList) MessageNano.mergeFrom(new StringList(), data);
            }

            public static StringList parseFrom(CodedInputByteBufferNano input) throws IOException {
                return new StringList().mergeFrom(input);
            }
        }

        public static RestrictionValue[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new RestrictionValue[0];
                    }
                }
            }
            return _emptyArray;
        }

        public RestrictionValue() {
            clear();
        }

        public RestrictionValue clear() {
            this.boolValue = false;
            this.stringValue = BuildConfig.VERSION_NAME;
            this.stringListValue = null;
            this.integerValue = 0;
            this.mutationInfo = null;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof RestrictionValue)) {
                return false;
            }
            RestrictionValue other = (RestrictionValue) o;
            if (this.boolValue != other.boolValue) {
                return false;
            }
            if (this.stringValue == null) {
                if (other.stringValue != null) {
                    return false;
                }
            } else if (!this.stringValue.equals(other.stringValue)) {
                return false;
            }
            if (this.stringListValue == null) {
                if (other.stringListValue != null) {
                    return false;
                }
            } else if (!this.stringListValue.equals(other.stringListValue)) {
                return false;
            }
            if (this.integerValue != other.integerValue) {
                return false;
            }
            if (this.mutationInfo == null) {
                if (other.mutationInfo != null) {
                    return false;
                }
            } else if (!this.mutationInfo.equals(other.mutationInfo)) {
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
            int hashCode = (((((((((((getClass().getName().hashCode() + 527) * 31) + (this.boolValue ? 1231 : 1237)) * 31) + (this.stringValue == null ? 0 : this.stringValue.hashCode())) * 31) + (this.stringListValue == null ? 0 : this.stringListValue.hashCode())) * 31) + this.integerValue) * 31) + (this.mutationInfo == null ? 0 : this.mutationInfo.hashCode())) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.boolValue) {
                output.writeBool(1, this.boolValue);
            }
            if (!this.stringValue.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(2, this.stringValue);
            }
            if (this.stringListValue != null) {
                output.writeMessage(3, this.stringListValue);
            }
            if (this.integerValue != 0) {
                output.writeInt32(4, this.integerValue);
            }
            if (this.mutationInfo != null) {
                output.writeMessage(5, this.mutationInfo);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.boolValue) {
                size += CodedOutputByteBufferNano.computeBoolSize(1, this.boolValue);
            }
            if (!this.stringValue.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(2, this.stringValue);
            }
            if (this.stringListValue != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(3, this.stringListValue);
            }
            if (this.integerValue != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(4, this.integerValue);
            }
            if (this.mutationInfo != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(5, this.mutationInfo);
            }
            return size;
        }

        public RestrictionValue mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_GET_LINK /*8*/:
                        this.boolValue = input.readBool();
                        continue;
                    case LogSource.GOOGLE_ANALYTICS /*18*/:
                        this.stringValue = input.readString();
                        continue;
                    case LogSource.ANDROID_CAMERA /*26*/:
                        if (this.stringListValue == null) {
                            this.stringListValue = new StringList();
                        }
                        input.readMessage(this.stringListValue);
                        continue;
                    case LogSource.SOCIAL /*32*/:
                        this.integerValue = input.readInt32();
                        continue;
                    case LogSource.PHOTOS /*42*/:
                        if (this.mutationInfo == null) {
                            this.mutationInfo = new MutationInfo();
                        }
                        input.readMessage(this.mutationInfo);
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

        public static RestrictionValue parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (RestrictionValue) MessageNano.mergeFrom(new RestrictionValue(), data);
        }

        public static RestrictionValue parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new RestrictionValue().mergeFrom(input);
        }
    }

    public static final class SynthesizedDeviceId extends ExtendableMessageNano<SynthesizedDeviceId> {
        private static volatile SynthesizedDeviceId[] _emptyArray;
        public int deviceTag;

        public static SynthesizedDeviceId[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new SynthesizedDeviceId[0];
                    }
                }
            }
            return _emptyArray;
        }

        public SynthesizedDeviceId() {
            clear();
        }

        public SynthesizedDeviceId clear() {
            this.deviceTag = 0;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof SynthesizedDeviceId)) {
                return false;
            }
            SynthesizedDeviceId other = (SynthesizedDeviceId) o;
            if (this.deviceTag != other.deviceTag) {
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
            int hashCode = (((getClass().getName().hashCode() + 527) * 31) + this.deviceTag) * 31;
            int hashCode2 = (this.unknownFieldData == null || this.unknownFieldData.isEmpty()) ? 0 : this.unknownFieldData.hashCode();
            return hashCode + hashCode2;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.deviceTag != 0) {
                output.writeInt32(1, this.deviceTag);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.deviceTag != 0) {
                return size + CodedOutputByteBufferNano.computeInt32Size(1, this.deviceTag);
            }
            return size;
        }

        public SynthesizedDeviceId mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_GET_LINK /*8*/:
                        this.deviceTag = input.readInt32();
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

        public static SynthesizedDeviceId parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (SynthesizedDeviceId) MessageNano.mergeFrom(new SynthesizedDeviceId(), data);
        }

        public static SynthesizedDeviceId parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new SynthesizedDeviceId().mergeFrom(input);
        }
    }

    public interface ViewType {
        public static final int FULL = 0;
        public static final int VALUE_ONLY = 1;
    }
}
