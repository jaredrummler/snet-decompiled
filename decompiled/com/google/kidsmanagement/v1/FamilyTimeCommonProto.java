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

public interface FamilyTimeCommonProto {

    public static final class ActivityGuess extends ExtendableMessageNano<ActivityGuess> {
        private static volatile ActivityGuess[] _emptyArray;
        public float score;
        public int type;

        public interface Type {
            public static final int IN_VEHICLE = 1;
            public static final int ON_BICYCLE = 2;
            public static final int ON_FOOT = 3;
            public static final int STILL = 4;
            public static final int TILTING = 5;
            public static final int UNKNOWN_ACTIVITY_TYPE = 0;
        }

        public static ActivityGuess[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new ActivityGuess[0];
                    }
                }
            }
            return _emptyArray;
        }

        public ActivityGuess() {
            clear();
        }

        public ActivityGuess clear() {
            this.type = 0;
            this.score = 0.0f;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof ActivityGuess)) {
                return false;
            }
            ActivityGuess other = (ActivityGuess) o;
            if (this.type != other.type || Float.floatToIntBits(this.score) != Float.floatToIntBits(other.score)) {
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
            int hashCode = (((((getClass().getName().hashCode() + 527) * 31) + this.type) * 31) + Float.floatToIntBits(this.score)) * 31;
            int hashCode2 = (this.unknownFieldData == null || this.unknownFieldData.isEmpty()) ? 0 : this.unknownFieldData.hashCode();
            return hashCode + hashCode2;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.type != 0) {
                output.writeInt32(1, this.type);
            }
            if (Float.floatToIntBits(this.score) != Float.floatToIntBits(0.0f)) {
                output.writeFloat(2, this.score);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.type != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(1, this.type);
            }
            if (Float.floatToIntBits(this.score) != Float.floatToIntBits(0.0f)) {
                return size + CodedOutputByteBufferNano.computeFloatSize(2, this.score);
            }
            return size;
        }

        public ActivityGuess mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case com.google.wireless.android.play.playlog.proto.UserLocation.LocationSharingEvent.Type.TAP_GET_LINK /*8*/:
                        int value = input.readInt32();
                        switch (value) {
                            case Action.UNKNOWN /*0*/:
                            case com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection.Type.TEMPORARY /*1*/:
                            case com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection.Type.INDEFINITELY /*2*/:
                            case com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection.Type.CUSTOM /*3*/:
                            case OvenFreshResult.NO_ACCOUNTS /*4*/:
                            case com.google.wireless.android.play.playlog.proto.UserLocation.LocationSharingEvent.Type.ADD_NEW_SHARES /*5*/:
                                this.type = value;
                                break;
                            default:
                                continue;
                        }
                    case LogSource.LB_T /*21*/:
                        this.score = input.readFloat();
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

        public static ActivityGuess parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (ActivityGuess) MessageNano.mergeFrom(new ActivityGuess(), data);
        }

        public static ActivityGuess parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new ActivityGuess().mergeFrom(input);
        }
    }

    public static final class Coordinates extends ExtendableMessageNano<Coordinates> {
        private static volatile Coordinates[] _emptyArray;
        public double latitude;
        public double longitude;

        public static Coordinates[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new Coordinates[0];
                    }
                }
            }
            return _emptyArray;
        }

        public Coordinates() {
            clear();
        }

        public Coordinates clear() {
            this.latitude = 0.0d;
            this.longitude = 0.0d;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof Coordinates)) {
                return false;
            }
            Coordinates other = (Coordinates) o;
            if (Double.doubleToLongBits(this.latitude) != Double.doubleToLongBits(other.latitude) || Double.doubleToLongBits(this.longitude) != Double.doubleToLongBits(other.longitude)) {
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
            int result = getClass().getName().hashCode() + 527;
            long v = Double.doubleToLongBits(this.latitude);
            result = (result * 31) + ((int) ((v >>> 32) ^ v));
            v = Double.doubleToLongBits(this.longitude);
            int i = ((result * 31) + ((int) ((v >>> 32) ^ v))) * 31;
            int hashCode = (this.unknownFieldData == null || this.unknownFieldData.isEmpty()) ? 0 : this.unknownFieldData.hashCode();
            return i + hashCode;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (Double.doubleToLongBits(this.latitude) != Double.doubleToLongBits(0.0d)) {
                output.writeDouble(1, this.latitude);
            }
            if (Double.doubleToLongBits(this.longitude) != Double.doubleToLongBits(0.0d)) {
                output.writeDouble(2, this.longitude);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (Double.doubleToLongBits(this.latitude) != Double.doubleToLongBits(0.0d)) {
                size += CodedOutputByteBufferNano.computeDoubleSize(1, this.latitude);
            }
            if (Double.doubleToLongBits(this.longitude) != Double.doubleToLongBits(0.0d)) {
                return size + CodedOutputByteBufferNano.computeDoubleSize(2, this.longitude);
            }
            return size;
        }

        public Coordinates mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.CREATE_LINK /*9*/:
                        this.latitude = input.readDouble();
                        continue;
                    case LogSource.LE /*17*/:
                        this.longitude = input.readDouble();
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

        public static Coordinates parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (Coordinates) MessageNano.mergeFrom(new Coordinates(), data);
        }

        public static Coordinates parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new Coordinates().mergeFrom(input);
        }
    }

    public static final class FamilyMemberLocation extends ExtendableMessageNano<FamilyMemberLocation> {
        private static volatile FamilyMemberLocation[] _emptyArray;
        public int accuracyMeters;
        public Coordinates coordinates;
        public LocationReportingDeviceDetails deviceDetails;
        public ActivityGuess[] guessedActivity;
        public String locationDisplayName;
        public long updateTimestampMillis;

        public static FamilyMemberLocation[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new FamilyMemberLocation[0];
                    }
                }
            }
            return _emptyArray;
        }

        public FamilyMemberLocation() {
            clear();
        }

        public FamilyMemberLocation clear() {
            this.coordinates = null;
            this.locationDisplayName = BuildConfig.VERSION_NAME;
            this.updateTimestampMillis = 0;
            this.accuracyMeters = 0;
            this.guessedActivity = ActivityGuess.emptyArray();
            this.deviceDetails = null;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof FamilyMemberLocation)) {
                return false;
            }
            FamilyMemberLocation other = (FamilyMemberLocation) o;
            if (this.coordinates == null) {
                if (other.coordinates != null) {
                    return false;
                }
            } else if (!this.coordinates.equals(other.coordinates)) {
                return false;
            }
            if (this.locationDisplayName == null) {
                if (other.locationDisplayName != null) {
                    return false;
                }
            } else if (!this.locationDisplayName.equals(other.locationDisplayName)) {
                return false;
            }
            if (this.updateTimestampMillis != other.updateTimestampMillis || this.accuracyMeters != other.accuracyMeters || !InternalNano.equals(this.guessedActivity, other.guessedActivity)) {
                return false;
            }
            if (this.deviceDetails == null) {
                if (other.deviceDetails != null) {
                    return false;
                }
            } else if (!this.deviceDetails.equals(other.deviceDetails)) {
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
            int hashCode = (((((((((((((getClass().getName().hashCode() + 527) * 31) + (this.coordinates == null ? 0 : this.coordinates.hashCode())) * 31) + (this.locationDisplayName == null ? 0 : this.locationDisplayName.hashCode())) * 31) + ((int) (this.updateTimestampMillis ^ (this.updateTimestampMillis >>> 32)))) * 31) + this.accuracyMeters) * 31) + InternalNano.hashCode(this.guessedActivity)) * 31) + (this.deviceDetails == null ? 0 : this.deviceDetails.hashCode())) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.updateTimestampMillis != 0) {
                output.writeInt64(3, this.updateTimestampMillis);
            }
            if (!this.locationDisplayName.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(5, this.locationDisplayName);
            }
            if (this.coordinates != null) {
                output.writeMessage(6, this.coordinates);
            }
            if (this.accuracyMeters != 0) {
                output.writeInt32(7, this.accuracyMeters);
            }
            if (this.guessedActivity != null && this.guessedActivity.length > 0) {
                for (ActivityGuess element : this.guessedActivity) {
                    if (element != null) {
                        output.writeMessage(8, element);
                    }
                }
            }
            if (this.deviceDetails != null) {
                output.writeMessage(9, this.deviceDetails);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.updateTimestampMillis != 0) {
                size += CodedOutputByteBufferNano.computeInt64Size(3, this.updateTimestampMillis);
            }
            if (!this.locationDisplayName.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(5, this.locationDisplayName);
            }
            if (this.coordinates != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(6, this.coordinates);
            }
            if (this.accuracyMeters != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(7, this.accuracyMeters);
            }
            if (this.guessedActivity != null && this.guessedActivity.length > 0) {
                for (ActivityGuess element : this.guessedActivity) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(8, element);
                    }
                }
            }
            if (this.deviceDetails != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(9, this.deviceDetails);
            }
            return size;
        }

        public FamilyMemberLocation mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case LogSource.LB_C /*24*/:
                        this.updateTimestampMillis = input.readInt64();
                        continue;
                    case LogSource.PHOTOS /*42*/:
                        this.locationDisplayName = input.readString();
                        continue;
                    case LogSource.HANGOUT_LOG_REQUEST /*50*/:
                        if (this.coordinates == null) {
                            this.coordinates = new Coordinates();
                        }
                        input.readMessage(this.coordinates);
                        continue;
                    case LogSource.DOCS /*56*/:
                        this.accuracyMeters = input.readInt32();
                        continue;
                    case LogSource.WIFI_ASSISTANT /*66*/:
                        int i;
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 66);
                        if (this.guessedActivity == null) {
                            i = 0;
                        } else {
                            i = this.guessedActivity.length;
                        }
                        ActivityGuess[] newArray = new ActivityGuess[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.guessedActivity, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new ActivityGuess();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i++;
                        }
                        newArray[i] = new ActivityGuess();
                        input.readMessage(newArray[i]);
                        this.guessedActivity = newArray;
                        continue;
                    case LogSource.MOVIES /*74*/:
                        if (this.deviceDetails == null) {
                            this.deviceDetails = new LocationReportingDeviceDetails();
                        }
                        input.readMessage(this.deviceDetails);
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

        public static FamilyMemberLocation parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (FamilyMemberLocation) MessageNano.mergeFrom(new FamilyMemberLocation(), data);
        }

        public static FamilyMemberLocation parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new FamilyMemberLocation().mergeFrom(input);
        }
    }

    public static final class FamilyMemberWithLocation extends ExtendableMessageNano<FamilyMemberWithLocation> {
        private static volatile FamilyMemberWithLocation[] _emptyArray;
        public FamilyMemberLocation currentLocation;
        public String email;
        public String givenName;
        public boolean isChild;
        public boolean isInvited;
        public String profileImageUrl;
        public String userId;

        public static FamilyMemberWithLocation[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new FamilyMemberWithLocation[0];
                    }
                }
            }
            return _emptyArray;
        }

        public FamilyMemberWithLocation() {
            clear();
        }

        public FamilyMemberWithLocation clear() {
            this.userId = BuildConfig.VERSION_NAME;
            this.isChild = false;
            this.givenName = BuildConfig.VERSION_NAME;
            this.profileImageUrl = BuildConfig.VERSION_NAME;
            this.email = BuildConfig.VERSION_NAME;
            this.currentLocation = null;
            this.isInvited = false;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof FamilyMemberWithLocation)) {
                return false;
            }
            FamilyMemberWithLocation other = (FamilyMemberWithLocation) o;
            if (this.userId == null) {
                if (other.userId != null) {
                    return false;
                }
            } else if (!this.userId.equals(other.userId)) {
                return false;
            }
            if (this.isChild != other.isChild) {
                return false;
            }
            if (this.givenName == null) {
                if (other.givenName != null) {
                    return false;
                }
            } else if (!this.givenName.equals(other.givenName)) {
                return false;
            }
            if (this.profileImageUrl == null) {
                if (other.profileImageUrl != null) {
                    return false;
                }
            } else if (!this.profileImageUrl.equals(other.profileImageUrl)) {
                return false;
            }
            if (this.email == null) {
                if (other.email != null) {
                    return false;
                }
            } else if (!this.email.equals(other.email)) {
                return false;
            }
            if (this.currentLocation == null) {
                if (other.currentLocation != null) {
                    return false;
                }
            } else if (!this.currentLocation.equals(other.currentLocation)) {
                return false;
            }
            if (this.isInvited != other.isInvited) {
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
            int hashCode = (((getClass().getName().hashCode() + 527) * 31) + (this.userId == null ? 0 : this.userId.hashCode())) * 31;
            if (this.isChild) {
                i = 1231;
            } else {
                i = 1237;
            }
            i = (((((((((hashCode + i) * 31) + (this.givenName == null ? 0 : this.givenName.hashCode())) * 31) + (this.profileImageUrl == null ? 0 : this.profileImageUrl.hashCode())) * 31) + (this.email == null ? 0 : this.email.hashCode())) * 31) + (this.currentLocation == null ? 0 : this.currentLocation.hashCode())) * 31;
            if (!this.isInvited) {
                i2 = 1237;
            }
            i = (i + i2) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i3 = this.unknownFieldData.hashCode();
            }
            return i + i3;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (!this.userId.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(1, this.userId);
            }
            if (this.isChild) {
                output.writeBool(2, this.isChild);
            }
            if (!this.givenName.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(5, this.givenName);
            }
            if (!this.profileImageUrl.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(6, this.profileImageUrl);
            }
            if (!this.email.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(7, this.email);
            }
            if (this.currentLocation != null) {
                output.writeMessage(8, this.currentLocation);
            }
            if (this.isInvited) {
                output.writeBool(9, this.isInvited);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (!this.userId.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.userId);
            }
            if (this.isChild) {
                size += CodedOutputByteBufferNano.computeBoolSize(2, this.isChild);
            }
            if (!this.givenName.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(5, this.givenName);
            }
            if (!this.profileImageUrl.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(6, this.profileImageUrl);
            }
            if (!this.email.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(7, this.email);
            }
            if (this.currentLocation != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(8, this.currentLocation);
            }
            if (this.isInvited) {
                return size + CodedOutputByteBufferNano.computeBoolSize(9, this.isInvited);
            }
            return size;
        }

        public FamilyMemberWithLocation mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        this.userId = input.readString();
                        continue;
                    case LogSource.GMS_CORE_PEOPLE /*16*/:
                        this.isChild = input.readBool();
                        continue;
                    case LogSource.PHOTOS /*42*/:
                        this.givenName = input.readString();
                        continue;
                    case LogSource.HANGOUT_LOG_REQUEST /*50*/:
                        this.profileImageUrl = input.readString();
                        continue;
                    case LogSource.SLIDES /*58*/:
                        this.email = input.readString();
                        continue;
                    case LogSource.WIFI_ASSISTANT /*66*/:
                        if (this.currentLocation == null) {
                            this.currentLocation = new FamilyMemberLocation();
                        }
                        input.readMessage(this.currentLocation);
                        continue;
                    case LogSource.JUSTSPEAK /*72*/:
                        this.isInvited = input.readBool();
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

        public static FamilyMemberWithLocation parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (FamilyMemberWithLocation) MessageNano.mergeFrom(new FamilyMemberWithLocation(), data);
        }

        public static FamilyMemberWithLocation parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new FamilyMemberWithLocation().mergeFrom(input);
        }
    }

    public static final class FamilyPlace extends ExtendableMessageNano<FamilyPlace> {
        private static volatile FamilyPlace[] _emptyArray;
        public int category;
        public Coordinates coordinates;
        public String displayName;
        public String familyPlaceId;
        public String fullAddress;
        public String placesApiId;
        public long updateTimestampMillis;

        public static FamilyPlace[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new FamilyPlace[0];
                    }
                }
            }
            return _emptyArray;
        }

        public FamilyPlace() {
            clear();
        }

        public FamilyPlace clear() {
            this.familyPlaceId = BuildConfig.VERSION_NAME;
            this.displayName = BuildConfig.VERSION_NAME;
            this.fullAddress = BuildConfig.VERSION_NAME;
            this.category = 0;
            this.coordinates = null;
            this.placesApiId = BuildConfig.VERSION_NAME;
            this.updateTimestampMillis = 0;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof FamilyPlace)) {
                return false;
            }
            FamilyPlace other = (FamilyPlace) o;
            if (this.familyPlaceId == null) {
                if (other.familyPlaceId != null) {
                    return false;
                }
            } else if (!this.familyPlaceId.equals(other.familyPlaceId)) {
                return false;
            }
            if (this.displayName == null) {
                if (other.displayName != null) {
                    return false;
                }
            } else if (!this.displayName.equals(other.displayName)) {
                return false;
            }
            if (this.fullAddress == null) {
                if (other.fullAddress != null) {
                    return false;
                }
            } else if (!this.fullAddress.equals(other.fullAddress)) {
                return false;
            }
            if (this.category != other.category) {
                return false;
            }
            if (this.coordinates == null) {
                if (other.coordinates != null) {
                    return false;
                }
            } else if (!this.coordinates.equals(other.coordinates)) {
                return false;
            }
            if (this.placesApiId == null) {
                if (other.placesApiId != null) {
                    return false;
                }
            } else if (!this.placesApiId.equals(other.placesApiId)) {
                return false;
            }
            if (this.updateTimestampMillis != other.updateTimestampMillis) {
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
            int hashCode = (((((((((((((((getClass().getName().hashCode() + 527) * 31) + (this.familyPlaceId == null ? 0 : this.familyPlaceId.hashCode())) * 31) + (this.displayName == null ? 0 : this.displayName.hashCode())) * 31) + (this.fullAddress == null ? 0 : this.fullAddress.hashCode())) * 31) + this.category) * 31) + (this.coordinates == null ? 0 : this.coordinates.hashCode())) * 31) + (this.placesApiId == null ? 0 : this.placesApiId.hashCode())) * 31) + ((int) (this.updateTimestampMillis ^ (this.updateTimestampMillis >>> 32)))) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (!this.familyPlaceId.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(1, this.familyPlaceId);
            }
            if (!this.displayName.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(2, this.displayName);
            }
            if (!this.fullAddress.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(3, this.fullAddress);
            }
            if (this.category != 0) {
                output.writeInt32(4, this.category);
            }
            if (this.coordinates != null) {
                output.writeMessage(5, this.coordinates);
            }
            if (!this.placesApiId.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(6, this.placesApiId);
            }
            if (this.updateTimestampMillis != 0) {
                output.writeUInt64(7, this.updateTimestampMillis);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (!this.familyPlaceId.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.familyPlaceId);
            }
            if (!this.displayName.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(2, this.displayName);
            }
            if (!this.fullAddress.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(3, this.fullAddress);
            }
            if (this.category != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(4, this.category);
            }
            if (this.coordinates != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(5, this.coordinates);
            }
            if (!this.placesApiId.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(6, this.placesApiId);
            }
            if (this.updateTimestampMillis != 0) {
                return size + CodedOutputByteBufferNano.computeUInt64Size(7, this.updateTimestampMillis);
            }
            return size;
        }

        public FamilyPlace mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        this.familyPlaceId = input.readString();
                        continue;
                    case LogSource.GOOGLE_ANALYTICS /*18*/:
                        this.displayName = input.readString();
                        continue;
                    case LogSource.ANDROID_CAMERA /*26*/:
                        this.fullAddress = input.readString();
                        continue;
                    case LogSource.SOCIAL /*32*/:
                        int value = input.readInt32();
                        switch (value) {
                            case Action.UNKNOWN /*0*/:
                            case TimeSelection.Type.TEMPORARY /*1*/:
                            case TimeSelection.Type.INDEFINITELY /*2*/:
                            case TimeSelection.Type.CUSTOM /*3*/:
                            case OvenFreshResult.NO_ACCOUNTS /*4*/:
                            case Type.ADD_NEW_SHARES /*5*/:
                            case Type.REMOVE_SHARE /*6*/:
                            case Type.RESET_TIME /*7*/:
                            case Type.TAP_GET_LINK /*8*/:
                            case Type.CREATE_LINK /*9*/:
                            case Type.TAP_ABOUT /*10*/:
                            case Type.TAP_LEARN_MORE /*11*/:
                            case Type.SWITCH_ACCOUNT /*12*/:
                            case Type.DISPLAY_ERROR /*13*/:
                            case Type.LAUNCH_SETTINGS /*14*/:
                            case Type.OVEN_FRESH /*15*/:
                                this.category = value;
                                break;
                            default:
                                continue;
                        }
                    case LogSource.PHOTOS /*42*/:
                        if (this.coordinates == null) {
                            this.coordinates = new Coordinates();
                        }
                        input.readMessage(this.coordinates);
                        continue;
                    case LogSource.HANGOUT_LOG_REQUEST /*50*/:
                        this.placesApiId = input.readString();
                        continue;
                    case LogSource.DOCS /*56*/:
                        this.updateTimestampMillis = input.readUInt64();
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

        public static FamilyPlace parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (FamilyPlace) MessageNano.mergeFrom(new FamilyPlace(), data);
        }

        public static FamilyPlace parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new FamilyPlace().mergeFrom(input);
        }
    }

    public interface FamilyPlaceCategory {
        public static final int AFTER_SCHOOL_ACTIVITY = 4;
        public static final int COMMUNITY = 10;
        public static final int ENTERTAINMENT = 6;
        public static final int FRIENDS_AND_FAMILY = 5;
        public static final int HOME = 1;
        public static final int HOTEL = 14;
        public static final int LANDMARK = 15;
        public static final int MEDICAL = 11;
        public static final int RECREATION = 9;
        public static final int RESTAURANT = 7;
        public static final int SCHOOL = 3;
        public static final int SERVICES = 12;
        public static final int STORE = 8;
        public static final int TRAVEL = 13;
        public static final int UNDEFINED_PLACE_CATEGORY = 0;
        public static final int WORK = 2;
    }

    public static final class LocationReportingDeviceDetails extends ExtendableMessageNano<LocationReportingDeviceDetails> {
        private static volatile LocationReportingDeviceDetails[] _emptyArray;
        public String deviceFriendlyName;

        public static LocationReportingDeviceDetails[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new LocationReportingDeviceDetails[0];
                    }
                }
            }
            return _emptyArray;
        }

        public LocationReportingDeviceDetails() {
            clear();
        }

        public LocationReportingDeviceDetails clear() {
            this.deviceFriendlyName = BuildConfig.VERSION_NAME;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof LocationReportingDeviceDetails)) {
                return false;
            }
            LocationReportingDeviceDetails other = (LocationReportingDeviceDetails) o;
            if (this.deviceFriendlyName == null) {
                if (other.deviceFriendlyName != null) {
                    return false;
                }
            } else if (!this.deviceFriendlyName.equals(other.deviceFriendlyName)) {
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
            int hashCode = (((getClass().getName().hashCode() + 527) * 31) + (this.deviceFriendlyName == null ? 0 : this.deviceFriendlyName.hashCode())) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (!this.deviceFriendlyName.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(1, this.deviceFriendlyName);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.deviceFriendlyName.equals(BuildConfig.VERSION_NAME)) {
                return size;
            }
            return size + CodedOutputByteBufferNano.computeStringSize(1, this.deviceFriendlyName);
        }

        public LocationReportingDeviceDetails mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        this.deviceFriendlyName = input.readString();
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

        public static LocationReportingDeviceDetails parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (LocationReportingDeviceDetails) MessageNano.mergeFrom(new LocationReportingDeviceDetails(), data);
        }

        public static LocationReportingDeviceDetails parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new LocationReportingDeviceDetails().mergeFrom(input);
        }
    }

    public static final class LocationSharingAcl extends ExtendableMessageNano<LocationSharingAcl> {
        private static volatile LocationSharingAcl[] _emptyArray;
        public Entry[] entry;
        public boolean sharingEnabled;

        public static final class Entry extends ExtendableMessageNano<Entry> {
            private static volatile Entry[] _emptyArray;
            public int type;
            public String userId;

            public interface Type {
                public static final int BEST = 1;
                public static final int CITY = 2;
                public static final int UNKNOWN_LOCATION_TYPE = 0;
            }

            public static Entry[] emptyArray() {
                if (_emptyArray == null) {
                    synchronized (InternalNano.LAZY_INIT_LOCK) {
                        if (_emptyArray == null) {
                            _emptyArray = new Entry[0];
                        }
                    }
                }
                return _emptyArray;
            }

            public Entry() {
                clear();
            }

            public Entry clear() {
                this.type = 0;
                this.userId = BuildConfig.VERSION_NAME;
                this.unknownFieldData = null;
                this.cachedSize = -1;
                return this;
            }

            public boolean equals(Object o) {
                if (o == this) {
                    return true;
                }
                if (!(o instanceof Entry)) {
                    return false;
                }
                Entry other = (Entry) o;
                if (this.type != other.type) {
                    return false;
                }
                if (this.userId == null) {
                    if (other.userId != null) {
                        return false;
                    }
                } else if (!this.userId.equals(other.userId)) {
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
                int hashCode = (((((getClass().getName().hashCode() + 527) * 31) + this.type) * 31) + (this.userId == null ? 0 : this.userId.hashCode())) * 31;
                if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                    i = this.unknownFieldData.hashCode();
                }
                return hashCode + i;
            }

            public void writeTo(CodedOutputByteBufferNano output) throws IOException {
                if (this.type != 0) {
                    output.writeInt32(1, this.type);
                }
                if (!this.userId.equals(BuildConfig.VERSION_NAME)) {
                    output.writeString(2, this.userId);
                }
                super.writeTo(output);
            }

            protected int computeSerializedSize() {
                int size = super.computeSerializedSize();
                if (this.type != 0) {
                    size += CodedOutputByteBufferNano.computeInt32Size(1, this.type);
                }
                if (this.userId.equals(BuildConfig.VERSION_NAME)) {
                    return size;
                }
                return size + CodedOutputByteBufferNano.computeStringSize(2, this.userId);
            }

            public Entry mergeFrom(CodedInputByteBufferNano input) throws IOException {
                while (true) {
                    int tag = input.readTag();
                    switch (tag) {
                        case Action.UNKNOWN /*0*/:
                            break;
                        case com.google.wireless.android.play.playlog.proto.UserLocation.LocationSharingEvent.Type.TAP_GET_LINK /*8*/:
                            int value = input.readInt32();
                            switch (value) {
                                case Action.UNKNOWN /*0*/:
                                case com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection.Type.TEMPORARY /*1*/:
                                case com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection.Type.INDEFINITELY /*2*/:
                                    this.type = value;
                                    break;
                                default:
                                    continue;
                            }
                        case LogSource.GOOGLE_ANALYTICS /*18*/:
                            this.userId = input.readString();
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

            public static Entry parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
                return (Entry) MessageNano.mergeFrom(new Entry(), data);
            }

            public static Entry parseFrom(CodedInputByteBufferNano input) throws IOException {
                return new Entry().mergeFrom(input);
            }
        }

        public static LocationSharingAcl[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new LocationSharingAcl[0];
                    }
                }
            }
            return _emptyArray;
        }

        public LocationSharingAcl() {
            clear();
        }

        public LocationSharingAcl clear() {
            this.sharingEnabled = false;
            this.entry = Entry.emptyArray();
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof LocationSharingAcl)) {
                return false;
            }
            LocationSharingAcl other = (LocationSharingAcl) o;
            if (this.sharingEnabled != other.sharingEnabled || !InternalNano.equals(this.entry, other.entry)) {
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
            int hashCode = (((((getClass().getName().hashCode() + 527) * 31) + (this.sharingEnabled ? 1231 : 1237)) * 31) + InternalNano.hashCode(this.entry)) * 31;
            int hashCode2 = (this.unknownFieldData == null || this.unknownFieldData.isEmpty()) ? 0 : this.unknownFieldData.hashCode();
            return hashCode + hashCode2;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.entry != null && this.entry.length > 0) {
                for (Entry element : this.entry) {
                    if (element != null) {
                        output.writeMessage(1, element);
                    }
                }
            }
            if (this.sharingEnabled) {
                output.writeBool(2, this.sharingEnabled);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.entry != null && this.entry.length > 0) {
                for (Entry element : this.entry) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(1, element);
                    }
                }
            }
            if (this.sharingEnabled) {
                return size + CodedOutputByteBufferNano.computeBoolSize(2, this.sharingEnabled);
            }
            return size;
        }

        public LocationSharingAcl mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        int i;
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 10);
                        if (this.entry == null) {
                            i = 0;
                        } else {
                            i = this.entry.length;
                        }
                        Entry[] newArray = new Entry[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.entry, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new Entry();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i++;
                        }
                        newArray[i] = new Entry();
                        input.readMessage(newArray[i]);
                        this.entry = newArray;
                        continue;
                    case LogSource.GMS_CORE_PEOPLE /*16*/:
                        this.sharingEnabled = input.readBool();
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

        public static LocationSharingAcl parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (LocationSharingAcl) MessageNano.mergeFrom(new LocationSharingAcl(), data);
        }

        public static LocationSharingAcl parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new LocationSharingAcl().mergeFrom(input);
        }
    }

    public static final class LocationSharingAclUpdate extends ExtendableMessageNano<LocationSharingAclUpdate> {
        private static volatile LocationSharingAclUpdate[] _emptyArray;
        public int sharingToggle;
        public String[] startSharingWith;
        public String[] stopSharingWith;

        public interface SharingToggle {
            public static final int AUTO = 0;
            public static final int OFF = 2;
            public static final int ON = 1;
            public static final int UNCHANGED = 3;
        }

        public static LocationSharingAclUpdate[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new LocationSharingAclUpdate[0];
                    }
                }
            }
            return _emptyArray;
        }

        public LocationSharingAclUpdate() {
            clear();
        }

        public LocationSharingAclUpdate clear() {
            this.sharingToggle = 0;
            this.stopSharingWith = WireFormatNano.EMPTY_STRING_ARRAY;
            this.startSharingWith = WireFormatNano.EMPTY_STRING_ARRAY;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof LocationSharingAclUpdate)) {
                return false;
            }
            LocationSharingAclUpdate other = (LocationSharingAclUpdate) o;
            if (this.sharingToggle != other.sharingToggle || !InternalNano.equals(this.stopSharingWith, other.stopSharingWith) || !InternalNano.equals(this.startSharingWith, other.startSharingWith)) {
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
            int hashCode = (((((((getClass().getName().hashCode() + 527) * 31) + this.sharingToggle) * 31) + InternalNano.hashCode(this.stopSharingWith)) * 31) + InternalNano.hashCode(this.startSharingWith)) * 31;
            int hashCode2 = (this.unknownFieldData == null || this.unknownFieldData.isEmpty()) ? 0 : this.unknownFieldData.hashCode();
            return hashCode + hashCode2;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.stopSharingWith != null && this.stopSharingWith.length > 0) {
                for (String element : this.stopSharingWith) {
                    if (element != null) {
                        output.writeString(1, element);
                    }
                }
            }
            if (this.startSharingWith != null && this.startSharingWith.length > 0) {
                for (String element2 : this.startSharingWith) {
                    if (element2 != null) {
                        output.writeString(2, element2);
                    }
                }
            }
            if (this.sharingToggle != 0) {
                output.writeInt32(3, this.sharingToggle);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int dataCount;
            int dataSize;
            int size = super.computeSerializedSize();
            if (this.stopSharingWith != null && this.stopSharingWith.length > 0) {
                dataCount = 0;
                dataSize = 0;
                for (String element : this.stopSharingWith) {
                    if (element != null) {
                        dataCount++;
                        dataSize += CodedOutputByteBufferNano.computeStringSizeNoTag(element);
                    }
                }
                size = (size + dataSize) + (dataCount * 1);
            }
            if (this.startSharingWith != null && this.startSharingWith.length > 0) {
                dataCount = 0;
                dataSize = 0;
                for (String element2 : this.startSharingWith) {
                    if (element2 != null) {
                        dataCount++;
                        dataSize += CodedOutputByteBufferNano.computeStringSizeNoTag(element2);
                    }
                }
                size = (size + dataSize) + (dataCount * 1);
            }
            if (this.sharingToggle != 0) {
                return size + CodedOutputByteBufferNano.computeInt32Size(3, this.sharingToggle);
            }
            return size;
        }

        public LocationSharingAclUpdate mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                int arrayLength;
                int i;
                String[] newArray;
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 10);
                        i = this.stopSharingWith == null ? 0 : this.stopSharingWith.length;
                        newArray = new String[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.stopSharingWith, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = input.readString();
                            input.readTag();
                            i++;
                        }
                        newArray[i] = input.readString();
                        this.stopSharingWith = newArray;
                        continue;
                    case LogSource.GOOGLE_ANALYTICS /*18*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 18);
                        i = this.startSharingWith == null ? 0 : this.startSharingWith.length;
                        newArray = new String[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.startSharingWith, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = input.readString();
                            input.readTag();
                            i++;
                        }
                        newArray[i] = input.readString();
                        this.startSharingWith = newArray;
                        continue;
                    case LogSource.LB_C /*24*/:
                        int value = input.readInt32();
                        switch (value) {
                            case Action.UNKNOWN /*0*/:
                            case TimeSelection.Type.TEMPORARY /*1*/:
                            case TimeSelection.Type.INDEFINITELY /*2*/:
                            case TimeSelection.Type.CUSTOM /*3*/:
                                this.sharingToggle = value;
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

        public static LocationSharingAclUpdate parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (LocationSharingAclUpdate) MessageNano.mergeFrom(new LocationSharingAclUpdate(), data);
        }

        public static LocationSharingAclUpdate parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new LocationSharingAclUpdate().mergeFrom(input);
        }
    }
}
