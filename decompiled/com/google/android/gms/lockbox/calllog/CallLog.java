package com.google.android.gms.lockbox.calllog;

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

public interface CallLog {

    public static final class CallLogEvent extends ExtendableMessageNano<CallLogEvent> {
        private static volatile CallLogEvent[] _emptyArray;
        public boolean acknowledged;
        public String cachedFormattedNumber;
        public String cachedLookupUri;
        public String cachedMatchedNumber;
        public String cachedName;
        public String cachedNormalizedNumber;
        public String cachedNumberLabel;
        public int cachedNumberType;
        public long cachedPhotoId;
        public int callType;
        public String countryIso;
        public String devicePhoneNumber;
        public long durationMillis;
        public String geocodedLocation;
        public boolean inProgress;
        public long localEventId;
        public String phoneNumber;
        public boolean read;
        public long timestampMillis;
        public String voicemailUri;

        public interface CallType {
            public static final int INCOMING_TYPE = 1;
            public static final int MISSED_TYPE = 3;
            public static final int OUTGOING_TYPE = 2;
            public static final int VOICEMAIL_TYPE = 4;
        }

        public static CallLogEvent[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new CallLogEvent[0];
                    }
                }
            }
            return _emptyArray;
        }

        public CallLogEvent() {
            clear();
        }

        public CallLogEvent clear() {
            this.localEventId = 0;
            this.callType = 1;
            this.phoneNumber = BuildConfig.VERSION_NAME;
            this.durationMillis = 0;
            this.acknowledged = false;
            this.countryIso = BuildConfig.VERSION_NAME;
            this.geocodedLocation = BuildConfig.VERSION_NAME;
            this.cachedName = BuildConfig.VERSION_NAME;
            this.cachedNumberType = 0;
            this.cachedNumberLabel = BuildConfig.VERSION_NAME;
            this.cachedLookupUri = BuildConfig.VERSION_NAME;
            this.cachedMatchedNumber = BuildConfig.VERSION_NAME;
            this.cachedNormalizedNumber = BuildConfig.VERSION_NAME;
            this.cachedPhotoId = 0;
            this.cachedFormattedNumber = BuildConfig.VERSION_NAME;
            this.voicemailUri = BuildConfig.VERSION_NAME;
            this.read = false;
            this.timestampMillis = 0;
            this.devicePhoneNumber = BuildConfig.VERSION_NAME;
            this.inProgress = false;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof CallLogEvent)) {
                return false;
            }
            CallLogEvent other = (CallLogEvent) o;
            if (this.localEventId != other.localEventId || this.callType != other.callType) {
                return false;
            }
            if (this.phoneNumber == null) {
                if (other.phoneNumber != null) {
                    return false;
                }
            } else if (!this.phoneNumber.equals(other.phoneNumber)) {
                return false;
            }
            if (this.durationMillis != other.durationMillis || this.acknowledged != other.acknowledged) {
                return false;
            }
            if (this.countryIso == null) {
                if (other.countryIso != null) {
                    return false;
                }
            } else if (!this.countryIso.equals(other.countryIso)) {
                return false;
            }
            if (this.geocodedLocation == null) {
                if (other.geocodedLocation != null) {
                    return false;
                }
            } else if (!this.geocodedLocation.equals(other.geocodedLocation)) {
                return false;
            }
            if (this.cachedName == null) {
                if (other.cachedName != null) {
                    return false;
                }
            } else if (!this.cachedName.equals(other.cachedName)) {
                return false;
            }
            if (this.cachedNumberType != other.cachedNumberType) {
                return false;
            }
            if (this.cachedNumberLabel == null) {
                if (other.cachedNumberLabel != null) {
                    return false;
                }
            } else if (!this.cachedNumberLabel.equals(other.cachedNumberLabel)) {
                return false;
            }
            if (this.cachedLookupUri == null) {
                if (other.cachedLookupUri != null) {
                    return false;
                }
            } else if (!this.cachedLookupUri.equals(other.cachedLookupUri)) {
                return false;
            }
            if (this.cachedMatchedNumber == null) {
                if (other.cachedMatchedNumber != null) {
                    return false;
                }
            } else if (!this.cachedMatchedNumber.equals(other.cachedMatchedNumber)) {
                return false;
            }
            if (this.cachedNormalizedNumber == null) {
                if (other.cachedNormalizedNumber != null) {
                    return false;
                }
            } else if (!this.cachedNormalizedNumber.equals(other.cachedNormalizedNumber)) {
                return false;
            }
            if (this.cachedPhotoId != other.cachedPhotoId) {
                return false;
            }
            if (this.cachedFormattedNumber == null) {
                if (other.cachedFormattedNumber != null) {
                    return false;
                }
            } else if (!this.cachedFormattedNumber.equals(other.cachedFormattedNumber)) {
                return false;
            }
            if (this.voicemailUri == null) {
                if (other.voicemailUri != null) {
                    return false;
                }
            } else if (!this.voicemailUri.equals(other.voicemailUri)) {
                return false;
            }
            if (this.read != other.read || this.timestampMillis != other.timestampMillis) {
                return false;
            }
            if (this.devicePhoneNumber == null) {
                if (other.devicePhoneNumber != null) {
                    return false;
                }
            } else if (!this.devicePhoneNumber.equals(other.devicePhoneNumber)) {
                return false;
            }
            if (this.inProgress != other.inProgress) {
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
            int i3 = 0;
            int hashCode = (((((((((getClass().getName().hashCode() + 527) * 31) + ((int) (this.localEventId ^ (this.localEventId >>> 32)))) * 31) + this.callType) * 31) + (this.phoneNumber == null ? 0 : this.phoneNumber.hashCode())) * 31) + ((int) (this.durationMillis ^ (this.durationMillis >>> 32)))) * 31;
            if (this.acknowledged) {
                i = 1231;
            } else {
                i = 1237;
            }
            hashCode = (((((((((((((((((((((((hashCode + i) * 31) + (this.countryIso == null ? 0 : this.countryIso.hashCode())) * 31) + (this.geocodedLocation == null ? 0 : this.geocodedLocation.hashCode())) * 31) + (this.cachedName == null ? 0 : this.cachedName.hashCode())) * 31) + this.cachedNumberType) * 31) + (this.cachedNumberLabel == null ? 0 : this.cachedNumberLabel.hashCode())) * 31) + (this.cachedLookupUri == null ? 0 : this.cachedLookupUri.hashCode())) * 31) + (this.cachedMatchedNumber == null ? 0 : this.cachedMatchedNumber.hashCode())) * 31) + (this.cachedNormalizedNumber == null ? 0 : this.cachedNormalizedNumber.hashCode())) * 31) + ((int) (this.cachedPhotoId ^ (this.cachedPhotoId >>> 32)))) * 31) + (this.cachedFormattedNumber == null ? 0 : this.cachedFormattedNumber.hashCode())) * 31) + (this.voicemailUri == null ? 0 : this.voicemailUri.hashCode())) * 31;
            if (this.read) {
                i = 1231;
            } else {
                i = 1237;
            }
            i = (((((hashCode + i) * 31) + ((int) (this.timestampMillis ^ (this.timestampMillis >>> 32)))) * 31) + (this.devicePhoneNumber == null ? 0 : this.devicePhoneNumber.hashCode())) * 31;
            if (!this.inProgress) {
                i2 = 1237;
            }
            i = (i + i2) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i3 = this.unknownFieldData.hashCode();
            }
            return i + i3;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.localEventId != 0) {
                output.writeInt64(1, this.localEventId);
            }
            if (this.callType != 1) {
                output.writeInt32(2, this.callType);
            }
            if (!this.phoneNumber.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(3, this.phoneNumber);
            }
            if (this.durationMillis != 0) {
                output.writeInt64(4, this.durationMillis);
            }
            if (this.acknowledged) {
                output.writeBool(5, this.acknowledged);
            }
            if (!this.countryIso.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(6, this.countryIso);
            }
            if (!this.geocodedLocation.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(7, this.geocodedLocation);
            }
            if (!this.cachedName.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(8, this.cachedName);
            }
            if (this.cachedNumberType != 0) {
                output.writeInt32(9, this.cachedNumberType);
            }
            if (!this.cachedNumberLabel.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(10, this.cachedNumberLabel);
            }
            if (!this.cachedLookupUri.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(11, this.cachedLookupUri);
            }
            if (!this.cachedMatchedNumber.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(12, this.cachedMatchedNumber);
            }
            if (!this.cachedNormalizedNumber.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(13, this.cachedNormalizedNumber);
            }
            if (this.cachedPhotoId != 0) {
                output.writeInt64(14, this.cachedPhotoId);
            }
            if (!this.cachedFormattedNumber.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(15, this.cachedFormattedNumber);
            }
            if (!this.voicemailUri.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(16, this.voicemailUri);
            }
            if (this.read) {
                output.writeBool(17, this.read);
            }
            if (this.timestampMillis != 0) {
                output.writeInt64(18, this.timestampMillis);
            }
            if (!this.devicePhoneNumber.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(19, this.devicePhoneNumber);
            }
            if (this.inProgress) {
                output.writeBool(20, this.inProgress);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.localEventId != 0) {
                size += CodedOutputByteBufferNano.computeInt64Size(1, this.localEventId);
            }
            if (this.callType != 1) {
                size += CodedOutputByteBufferNano.computeInt32Size(2, this.callType);
            }
            if (!this.phoneNumber.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(3, this.phoneNumber);
            }
            if (this.durationMillis != 0) {
                size += CodedOutputByteBufferNano.computeInt64Size(4, this.durationMillis);
            }
            if (this.acknowledged) {
                size += CodedOutputByteBufferNano.computeBoolSize(5, this.acknowledged);
            }
            if (!this.countryIso.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(6, this.countryIso);
            }
            if (!this.geocodedLocation.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(7, this.geocodedLocation);
            }
            if (!this.cachedName.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(8, this.cachedName);
            }
            if (this.cachedNumberType != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(9, this.cachedNumberType);
            }
            if (!this.cachedNumberLabel.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(10, this.cachedNumberLabel);
            }
            if (!this.cachedLookupUri.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(11, this.cachedLookupUri);
            }
            if (!this.cachedMatchedNumber.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(12, this.cachedMatchedNumber);
            }
            if (!this.cachedNormalizedNumber.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(13, this.cachedNormalizedNumber);
            }
            if (this.cachedPhotoId != 0) {
                size += CodedOutputByteBufferNano.computeInt64Size(14, this.cachedPhotoId);
            }
            if (!this.cachedFormattedNumber.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(15, this.cachedFormattedNumber);
            }
            if (!this.voicemailUri.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(16, this.voicemailUri);
            }
            if (this.read) {
                size += CodedOutputByteBufferNano.computeBoolSize(17, this.read);
            }
            if (this.timestampMillis != 0) {
                size += CodedOutputByteBufferNano.computeInt64Size(18, this.timestampMillis);
            }
            if (!this.devicePhoneNumber.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(19, this.devicePhoneNumber);
            }
            if (this.inProgress) {
                return size + CodedOutputByteBufferNano.computeBoolSize(20, this.inProgress);
            }
            return size;
        }

        public CallLogEvent mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_GET_LINK /*8*/:
                        this.localEventId = input.readInt64();
                        continue;
                    case LogSource.GMS_CORE_PEOPLE /*16*/:
                        int value = input.readInt32();
                        switch (value) {
                            case TimeSelection.Type.TEMPORARY /*1*/:
                            case TimeSelection.Type.INDEFINITELY /*2*/:
                            case TimeSelection.Type.CUSTOM /*3*/:
                            case OvenFreshResult.NO_ACCOUNTS /*4*/:
                                this.callType = value;
                                break;
                            default:
                                continue;
                        }
                    case LogSource.ANDROID_CAMERA /*26*/:
                        this.phoneNumber = input.readString();
                        continue;
                    case LogSource.SOCIAL /*32*/:
                        this.durationMillis = input.readInt64();
                        continue;
                    case LogSource.COPRESENCE /*40*/:
                        this.acknowledged = input.readBool();
                        continue;
                    case LogSource.HANGOUT_LOG_REQUEST /*50*/:
                        this.countryIso = input.readString();
                        continue;
                    case LogSource.SLIDES /*58*/:
                        this.geocodedLocation = input.readString();
                        continue;
                    case LogSource.WIFI_ASSISTANT /*66*/:
                        this.cachedName = input.readString();
                        continue;
                    case LogSource.JUSTSPEAK /*72*/:
                        this.cachedNumberType = input.readInt32();
                        continue;
                    case LogSource.GOOGLE_EXPRESS /*82*/:
                        this.cachedNumberLabel = input.readString();
                        continue;
                    case LogSource.TRON_COUNTERS /*90*/:
                        this.cachedLookupUri = input.readString();
                        continue;
                    case LogSource.TACHYON_LOG_REQUEST /*98*/:
                        this.cachedMatchedNumber = input.readString();
                        continue;
                    case LogSource.ADSHIELD /*106*/:
                        this.cachedNormalizedNumber = input.readString();
                        continue;
                    case LogSource.ENDER /*112*/:
                        this.cachedPhotoId = input.readInt64();
                        continue;
                    case LogSource.ANDROID_SNET_GCORE /*122*/:
                        this.cachedFormattedNumber = input.readString();
                        continue;
                    case LogSource.CHROMECAST_APP_LOG /*130*/:
                        this.voicemailUri = input.readString();
                        continue;
                    case LogSource.SAMPLE_SHM /*136*/:
                        this.read = input.readBool();
                        continue;
                    case 144:
                        this.timestampMillis = input.readInt64();
                        continue;
                    case 154:
                        this.devicePhoneNumber = input.readString();
                        continue;
                    case 160:
                        this.inProgress = input.readBool();
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

        public static CallLogEvent parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (CallLogEvent) MessageNano.mergeFrom(new CallLogEvent(), data);
        }

        public static CallLogEvent parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new CallLogEvent().mergeFrom(input);
        }
    }
}
