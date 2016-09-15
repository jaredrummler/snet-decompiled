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

public interface FamiliesCommonProto {

    public static final class Family extends ExtendableMessageNano<Family> {
        private static volatile Family[] _emptyArray;
        public String familyId;
        public FamilyProfile profile;

        public interface FamilyState {
            public static final int ACTIVE = 1;
            public static final int INACTIVE = 2;
            public static final int UNKNOWN_FAMILY_STATE = 0;
        }

        public static Family[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new Family[0];
                    }
                }
            }
            return _emptyArray;
        }

        public Family() {
            clear();
        }

        public Family clear() {
            this.familyId = BuildConfig.VERSION_NAME;
            this.profile = null;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof Family)) {
                return false;
            }
            Family other = (Family) o;
            if (this.familyId == null) {
                if (other.familyId != null) {
                    return false;
                }
            } else if (!this.familyId.equals(other.familyId)) {
                return false;
            }
            if (this.profile == null) {
                if (other.profile != null) {
                    return false;
                }
            } else if (!this.profile.equals(other.profile)) {
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
            int hashCode = (((((getClass().getName().hashCode() + 527) * 31) + (this.familyId == null ? 0 : this.familyId.hashCode())) * 31) + (this.profile == null ? 0 : this.profile.hashCode())) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (!this.familyId.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(1, this.familyId);
            }
            if (this.profile != null) {
                output.writeMessage(2, this.profile);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (!this.familyId.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.familyId);
            }
            if (this.profile != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(2, this.profile);
            }
            return size;
        }

        public Family mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        this.familyId = input.readString();
                        continue;
                    case LogSource.GOOGLE_ANALYTICS /*18*/:
                        if (this.profile == null) {
                            this.profile = new FamilyProfile();
                        }
                        input.readMessage(this.profile);
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

        public static Family parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (Family) MessageNano.mergeFrom(new Family(), data);
        }

        public static Family parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new Family().mergeFrom(input);
        }
    }

    public static final class FamilyMember extends ExtendableMessageNano<FamilyMember> {
        private static volatile FamilyMember[] _emptyArray;
        public String ageBandLabel;
        public long joinedTimestampMillis;
        public FamilyMemberProfile profile;
        public int role;
        public int state;
        public String userId;

        public interface State {
            public static final int DELETED = 2;
            public static final int REGULAR = 1;
            public static final int UNKNOWN_STATE = 0;
        }

        public static FamilyMember[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new FamilyMember[0];
                    }
                }
            }
            return _emptyArray;
        }

        public FamilyMember() {
            clear();
        }

        public FamilyMember clear() {
            this.userId = BuildConfig.VERSION_NAME;
            this.joinedTimestampMillis = 0;
            this.role = 0;
            this.profile = null;
            this.state = 0;
            this.ageBandLabel = BuildConfig.VERSION_NAME;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof FamilyMember)) {
                return false;
            }
            FamilyMember other = (FamilyMember) o;
            if (this.userId == null) {
                if (other.userId != null) {
                    return false;
                }
            } else if (!this.userId.equals(other.userId)) {
                return false;
            }
            if (this.joinedTimestampMillis != other.joinedTimestampMillis || this.role != other.role) {
                return false;
            }
            if (this.profile == null) {
                if (other.profile != null) {
                    return false;
                }
            } else if (!this.profile.equals(other.profile)) {
                return false;
            }
            if (this.state != other.state) {
                return false;
            }
            if (this.ageBandLabel == null) {
                if (other.ageBandLabel != null) {
                    return false;
                }
            } else if (!this.ageBandLabel.equals(other.ageBandLabel)) {
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
            int hashCode = (((((((((((((getClass().getName().hashCode() + 527) * 31) + (this.userId == null ? 0 : this.userId.hashCode())) * 31) + ((int) (this.joinedTimestampMillis ^ (this.joinedTimestampMillis >>> 32)))) * 31) + this.role) * 31) + (this.profile == null ? 0 : this.profile.hashCode())) * 31) + this.state) * 31) + (this.ageBandLabel == null ? 0 : this.ageBandLabel.hashCode())) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (!this.userId.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(1, this.userId);
            }
            if (this.joinedTimestampMillis != 0) {
                output.writeInt64(2, this.joinedTimestampMillis);
            }
            if (this.role != 0) {
                output.writeInt32(3, this.role);
            }
            if (this.profile != null) {
                output.writeMessage(4, this.profile);
            }
            if (this.state != 0) {
                output.writeInt32(5, this.state);
            }
            if (!this.ageBandLabel.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(6, this.ageBandLabel);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (!this.userId.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.userId);
            }
            if (this.joinedTimestampMillis != 0) {
                size += CodedOutputByteBufferNano.computeInt64Size(2, this.joinedTimestampMillis);
            }
            if (this.role != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(3, this.role);
            }
            if (this.profile != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(4, this.profile);
            }
            if (this.state != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(5, this.state);
            }
            if (this.ageBandLabel.equals(BuildConfig.VERSION_NAME)) {
                return size;
            }
            return size + CodedOutputByteBufferNano.computeStringSize(6, this.ageBandLabel);
        }

        public FamilyMember mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                int value;
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        this.userId = input.readString();
                        continue;
                    case LogSource.GMS_CORE_PEOPLE /*16*/:
                        this.joinedTimestampMillis = input.readInt64();
                        continue;
                    case LogSource.LB_C /*24*/:
                        value = input.readInt32();
                        switch (value) {
                            case Action.UNKNOWN /*0*/:
                            case TimeSelection.Type.TEMPORARY /*1*/:
                            case TimeSelection.Type.INDEFINITELY /*2*/:
                            case TimeSelection.Type.CUSTOM /*3*/:
                            case OvenFreshResult.NO_ACCOUNTS /*4*/:
                                this.role = value;
                                break;
                            default:
                                continue;
                        }
                    case LogSource.NOVA /*34*/:
                        if (this.profile == null) {
                            this.profile = new FamilyMemberProfile();
                        }
                        input.readMessage(this.profile);
                        continue;
                    case LogSource.COPRESENCE /*40*/:
                        value = input.readInt32();
                        switch (value) {
                            case Action.UNKNOWN /*0*/:
                            case TimeSelection.Type.TEMPORARY /*1*/:
                            case TimeSelection.Type.INDEFINITELY /*2*/:
                                this.state = value;
                                break;
                            default:
                                continue;
                        }
                    case LogSource.HANGOUT_LOG_REQUEST /*50*/:
                        this.ageBandLabel = input.readString();
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

        public static FamilyMember parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (FamilyMember) MessageNano.mergeFrom(new FamilyMember(), data);
        }

        public static FamilyMember parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new FamilyMember().mergeFrom(input);
        }
    }

    public static final class FamilyMemberProfile extends ExtendableMessageNano<FamilyMemberProfile> {
        private static volatile FamilyMemberProfile[] _emptyArray;
        public Birthday birthday;
        public String defaultProfileImageUrl;
        public String displayName;
        public String email;
        public String familyName;
        public String givenName;
        public String profileImageUrl;
        public String profileUrl;
        public int standardGender;

        public static final class Birthday extends ExtendableMessageNano<Birthday> {
            private static volatile Birthday[] _emptyArray;
            public int day;
            public int month;
            public int year;

            public static Birthday[] emptyArray() {
                if (_emptyArray == null) {
                    synchronized (InternalNano.LAZY_INIT_LOCK) {
                        if (_emptyArray == null) {
                            _emptyArray = new Birthday[0];
                        }
                    }
                }
                return _emptyArray;
            }

            public Birthday() {
                clear();
            }

            public Birthday clear() {
                this.day = 0;
                this.month = 0;
                this.year = 0;
                this.unknownFieldData = null;
                this.cachedSize = -1;
                return this;
            }

            public boolean equals(Object o) {
                if (o == this) {
                    return true;
                }
                if (!(o instanceof Birthday)) {
                    return false;
                }
                Birthday other = (Birthday) o;
                if (this.day != other.day || this.month != other.month || this.year != other.year) {
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
                int hashCode = (((((((getClass().getName().hashCode() + 527) * 31) + this.day) * 31) + this.month) * 31) + this.year) * 31;
                int hashCode2 = (this.unknownFieldData == null || this.unknownFieldData.isEmpty()) ? 0 : this.unknownFieldData.hashCode();
                return hashCode + hashCode2;
            }

            public void writeTo(CodedOutputByteBufferNano output) throws IOException {
                if (this.day != 0) {
                    output.writeInt32(1, this.day);
                }
                if (this.month != 0) {
                    output.writeInt32(2, this.month);
                }
                if (this.year != 0) {
                    output.writeInt32(3, this.year);
                }
                super.writeTo(output);
            }

            protected int computeSerializedSize() {
                int size = super.computeSerializedSize();
                if (this.day != 0) {
                    size += CodedOutputByteBufferNano.computeInt32Size(1, this.day);
                }
                if (this.month != 0) {
                    size += CodedOutputByteBufferNano.computeInt32Size(2, this.month);
                }
                if (this.year != 0) {
                    return size + CodedOutputByteBufferNano.computeInt32Size(3, this.year);
                }
                return size;
            }

            public Birthday mergeFrom(CodedInputByteBufferNano input) throws IOException {
                while (true) {
                    int tag = input.readTag();
                    switch (tag) {
                        case Action.UNKNOWN /*0*/:
                            break;
                        case Type.TAP_GET_LINK /*8*/:
                            this.day = input.readInt32();
                            continue;
                        case LogSource.GMS_CORE_PEOPLE /*16*/:
                            this.month = input.readInt32();
                            continue;
                        case LogSource.LB_C /*24*/:
                            this.year = input.readInt32();
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

            public static Birthday parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
                return (Birthday) MessageNano.mergeFrom(new Birthday(), data);
            }

            public static Birthday parseFrom(CodedInputByteBufferNano input) throws IOException {
                return new Birthday().mergeFrom(input);
            }
        }

        public interface StandardGenderEnum {
            public static final int FEMALE = 2;
            public static final int MALE = 1;
            public static final int OTHER = 3;
            public static final int UNKNOWN_GENDER = 0;
        }

        public static FamilyMemberProfile[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new FamilyMemberProfile[0];
                    }
                }
            }
            return _emptyArray;
        }

        public FamilyMemberProfile() {
            clear();
        }

        public FamilyMemberProfile clear() {
            this.displayName = BuildConfig.VERSION_NAME;
            this.familyName = BuildConfig.VERSION_NAME;
            this.givenName = BuildConfig.VERSION_NAME;
            this.profileUrl = BuildConfig.VERSION_NAME;
            this.profileImageUrl = BuildConfig.VERSION_NAME;
            this.defaultProfileImageUrl = BuildConfig.VERSION_NAME;
            this.email = BuildConfig.VERSION_NAME;
            this.standardGender = 0;
            this.birthday = null;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof FamilyMemberProfile)) {
                return false;
            }
            FamilyMemberProfile other = (FamilyMemberProfile) o;
            if (this.displayName == null) {
                if (other.displayName != null) {
                    return false;
                }
            } else if (!this.displayName.equals(other.displayName)) {
                return false;
            }
            if (this.familyName == null) {
                if (other.familyName != null) {
                    return false;
                }
            } else if (!this.familyName.equals(other.familyName)) {
                return false;
            }
            if (this.givenName == null) {
                if (other.givenName != null) {
                    return false;
                }
            } else if (!this.givenName.equals(other.givenName)) {
                return false;
            }
            if (this.profileUrl == null) {
                if (other.profileUrl != null) {
                    return false;
                }
            } else if (!this.profileUrl.equals(other.profileUrl)) {
                return false;
            }
            if (this.profileImageUrl == null) {
                if (other.profileImageUrl != null) {
                    return false;
                }
            } else if (!this.profileImageUrl.equals(other.profileImageUrl)) {
                return false;
            }
            if (this.defaultProfileImageUrl == null) {
                if (other.defaultProfileImageUrl != null) {
                    return false;
                }
            } else if (!this.defaultProfileImageUrl.equals(other.defaultProfileImageUrl)) {
                return false;
            }
            if (this.email == null) {
                if (other.email != null) {
                    return false;
                }
            } else if (!this.email.equals(other.email)) {
                return false;
            }
            if (this.standardGender != other.standardGender) {
                return false;
            }
            if (this.birthday == null) {
                if (other.birthday != null) {
                    return false;
                }
            } else if (!this.birthday.equals(other.birthday)) {
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
            int hashCode = (((((((((((((((((((getClass().getName().hashCode() + 527) * 31) + (this.displayName == null ? 0 : this.displayName.hashCode())) * 31) + (this.familyName == null ? 0 : this.familyName.hashCode())) * 31) + (this.givenName == null ? 0 : this.givenName.hashCode())) * 31) + (this.profileUrl == null ? 0 : this.profileUrl.hashCode())) * 31) + (this.profileImageUrl == null ? 0 : this.profileImageUrl.hashCode())) * 31) + (this.defaultProfileImageUrl == null ? 0 : this.defaultProfileImageUrl.hashCode())) * 31) + (this.email == null ? 0 : this.email.hashCode())) * 31) + this.standardGender) * 31) + (this.birthday == null ? 0 : this.birthday.hashCode())) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (!this.displayName.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(1, this.displayName);
            }
            if (!this.profileUrl.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(2, this.profileUrl);
            }
            if (!this.profileImageUrl.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(3, this.profileImageUrl);
            }
            if (!this.email.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(4, this.email);
            }
            if (!this.familyName.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(5, this.familyName);
            }
            if (!this.givenName.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(6, this.givenName);
            }
            if (this.standardGender != 0) {
                output.writeInt32(7, this.standardGender);
            }
            if (this.birthday != null) {
                output.writeMessage(8, this.birthday);
            }
            if (!this.defaultProfileImageUrl.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(9, this.defaultProfileImageUrl);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (!this.displayName.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.displayName);
            }
            if (!this.profileUrl.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(2, this.profileUrl);
            }
            if (!this.profileImageUrl.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(3, this.profileImageUrl);
            }
            if (!this.email.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(4, this.email);
            }
            if (!this.familyName.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(5, this.familyName);
            }
            if (!this.givenName.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(6, this.givenName);
            }
            if (this.standardGender != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(7, this.standardGender);
            }
            if (this.birthday != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(8, this.birthday);
            }
            if (this.defaultProfileImageUrl.equals(BuildConfig.VERSION_NAME)) {
                return size;
            }
            return size + CodedOutputByteBufferNano.computeStringSize(9, this.defaultProfileImageUrl);
        }

        public FamilyMemberProfile mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        this.displayName = input.readString();
                        continue;
                    case LogSource.GOOGLE_ANALYTICS /*18*/:
                        this.profileUrl = input.readString();
                        continue;
                    case LogSource.ANDROID_CAMERA /*26*/:
                        this.profileImageUrl = input.readString();
                        continue;
                    case LogSource.NOVA /*34*/:
                        this.email = input.readString();
                        continue;
                    case LogSource.PHOTOS /*42*/:
                        this.familyName = input.readString();
                        continue;
                    case LogSource.HANGOUT_LOG_REQUEST /*50*/:
                        this.givenName = input.readString();
                        continue;
                    case LogSource.DOCS /*56*/:
                        int value = input.readInt32();
                        switch (value) {
                            case Action.UNKNOWN /*0*/:
                            case TimeSelection.Type.TEMPORARY /*1*/:
                            case TimeSelection.Type.INDEFINITELY /*2*/:
                            case TimeSelection.Type.CUSTOM /*3*/:
                                this.standardGender = value;
                                break;
                            default:
                                continue;
                        }
                    case LogSource.WIFI_ASSISTANT /*66*/:
                        if (this.birthday == null) {
                            this.birthday = new Birthday();
                        }
                        input.readMessage(this.birthday);
                        continue;
                    case LogSource.MOVIES /*74*/:
                        this.defaultProfileImageUrl = input.readString();
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

        public static FamilyMemberProfile parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (FamilyMemberProfile) MessageNano.mergeFrom(new FamilyMemberProfile(), data);
        }

        public static FamilyMemberProfile parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new FamilyMemberProfile().mergeFrom(input);
        }
    }

    public static final class FamilyProductCorrelationId extends ExtendableMessageNano<FamilyProductCorrelationId> {
        private static volatile FamilyProductCorrelationId[] _emptyArray;

        public interface State {
            public static final int INVALID = 4;
            public static final int NOT_SET_UP = 1;
            public static final int PENDING = 2;
            public static final int UNKNOWN_STATE = 0;
            public static final int VALID = 3;
        }

        public interface Type {
            public static final int FAMILY_SHARING_PLAY = 1;
            public static final int UNKNOWN_TYPE = 0;
        }

        public static FamilyProductCorrelationId[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new FamilyProductCorrelationId[0];
                    }
                }
            }
            return _emptyArray;
        }

        public FamilyProductCorrelationId() {
            clear();
        }

        public FamilyProductCorrelationId clear() {
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof FamilyProductCorrelationId)) {
                return false;
            }
            FamilyProductCorrelationId other = (FamilyProductCorrelationId) o;
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

        public FamilyProductCorrelationId mergeFrom(CodedInputByteBufferNano input) throws IOException {
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

        public static FamilyProductCorrelationId parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (FamilyProductCorrelationId) MessageNano.mergeFrom(new FamilyProductCorrelationId(), data);
        }

        public static FamilyProductCorrelationId parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new FamilyProductCorrelationId().mergeFrom(input);
        }
    }

    public static final class FamilyProfile extends ExtendableMessageNano<FamilyProfile> {
        private static volatile FamilyProfile[] _emptyArray;
        public String name;

        public static FamilyProfile[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new FamilyProfile[0];
                    }
                }
            }
            return _emptyArray;
        }

        public FamilyProfile() {
            clear();
        }

        public FamilyProfile clear() {
            this.name = BuildConfig.VERSION_NAME;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof FamilyProfile)) {
                return false;
            }
            FamilyProfile other = (FamilyProfile) o;
            if (this.name == null) {
                if (other.name != null) {
                    return false;
                }
            } else if (!this.name.equals(other.name)) {
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
            int hashCode = (((getClass().getName().hashCode() + 527) * 31) + (this.name == null ? 0 : this.name.hashCode())) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (!this.name.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(1, this.name);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.name.equals(BuildConfig.VERSION_NAME)) {
                return size;
            }
            return size + CodedOutputByteBufferNano.computeStringSize(1, this.name);
        }

        public FamilyProfile mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        this.name = input.readString();
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

        public static FamilyProfile parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (FamilyProfile) MessageNano.mergeFrom(new FamilyProfile(), data);
        }

        public static FamilyProfile parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new FamilyProfile().mergeFrom(input);
        }
    }

    public interface FamilyRole {
        public static final int CHILD = 4;
        public static final int HEAD_OF_HOUSEHOLD = 1;
        public static final int MEMBER = 3;
        public static final int PARENT = 2;
        public static final int UNKNOWN_FAMILY_ROLE = 0;
    }

    public static final class FixitState extends ExtendableMessageNano<FixitState> {
        private static volatile FixitState[] _emptyArray;
        public int[] fixitNeeded;

        public interface Fixit {
            public static final int INVITES = 2;
            public static final int UNKNOWN_FIXIT = 0;
            public static final int WALLET_SETUP = 1;
        }

        public static FixitState[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new FixitState[0];
                    }
                }
            }
            return _emptyArray;
        }

        public FixitState() {
            clear();
        }

        public FixitState clear() {
            this.fixitNeeded = WireFormatNano.EMPTY_INT_ARRAY;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof FixitState)) {
                return false;
            }
            FixitState other = (FixitState) o;
            if (!InternalNano.equals(this.fixitNeeded, other.fixitNeeded)) {
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
            int hashCode = (((getClass().getName().hashCode() + 527) * 31) + InternalNano.hashCode(this.fixitNeeded)) * 31;
            int hashCode2 = (this.unknownFieldData == null || this.unknownFieldData.isEmpty()) ? 0 : this.unknownFieldData.hashCode();
            return hashCode + hashCode2;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.fixitNeeded != null && this.fixitNeeded.length > 0) {
                for (int writeInt32 : this.fixitNeeded) {
                    output.writeInt32(1, writeInt32);
                }
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.fixitNeeded == null || this.fixitNeeded.length <= 0) {
                return size;
            }
            int dataSize = 0;
            for (int element : this.fixitNeeded) {
                dataSize += CodedOutputByteBufferNano.computeInt32SizeNoTag(element);
            }
            return (size + dataSize) + (this.fixitNeeded.length * 1);
        }

        public FixitState mergeFrom(CodedInputByteBufferNano input) throws IOException {
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
                            i = this.fixitNeeded == null ? 0 : this.fixitNeeded.length;
                            if (i != 0 || validCount != validValues.length) {
                                newArray = new int[(i + validCount)];
                                if (i != 0) {
                                    System.arraycopy(this.fixitNeeded, 0, newArray, 0, i);
                                }
                                System.arraycopy(validValues, 0, newArray, i, validCount);
                                this.fixitNeeded = newArray;
                                break;
                            }
                            this.fixitNeeded = validValues;
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
                                    arrayLength++;
                                    break;
                                default:
                                    break;
                            }
                        }
                        if (arrayLength != 0) {
                            input.rewindToPosition(startPos);
                            if (this.fixitNeeded == null) {
                                i = 0;
                            } else {
                                i = this.fixitNeeded.length;
                            }
                            newArray = new int[(i + arrayLength)];
                            if (i != 0) {
                                System.arraycopy(this.fixitNeeded, 0, newArray, 0, i);
                            }
                            while (input.getBytesUntilLimit() > 0) {
                                value = input.readInt32();
                                switch (value) {
                                    case Action.UNKNOWN /*0*/:
                                    case TimeSelection.Type.TEMPORARY /*1*/:
                                    case TimeSelection.Type.INDEFINITELY /*2*/:
                                        int i2 = i + 1;
                                        newArray[i] = value;
                                        i = i2;
                                        break;
                                    default:
                                        break;
                                }
                            }
                            this.fixitNeeded = newArray;
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

        public static FixitState parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (FixitState) MessageNano.mergeFrom(new FixitState(), data);
        }

        public static FixitState parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new FixitState().mergeFrom(input);
        }
    }

    public static final class ManagementCapabilities extends ExtendableMessageNano<ManagementCapabilities> {
        private static volatile ManagementCapabilities[] _emptyArray;
        public int[] manageFamilyCapability;
        public ManageMemberCapabilities[] manageMemberCapabilities;

        public interface ManageFamilyCapability {
            public static final int CAN_DISSOLVE_FAMILY = 1;
            public static final int CAN_INVITE_MEMBERS = 2;
            public static final int CAN_REMOVE_INVITATIONS = 3;
            public static final int UNKNOWN_MANAGE_FAMILY_CAPABILITY = 0;
        }

        public static final class ManageMemberCapabilities extends ExtendableMessageNano<ManageMemberCapabilities> {
            private static volatile ManageMemberCapabilities[] _emptyArray;
            public int[] capability;
            public String memberId;

            public interface Capability {
                public static final int CAN_REMOVE_MEMBER = 2;
                public static final int UNKNOWN_CAPABILITY = 1;
            }

            public static ManageMemberCapabilities[] emptyArray() {
                if (_emptyArray == null) {
                    synchronized (InternalNano.LAZY_INIT_LOCK) {
                        if (_emptyArray == null) {
                            _emptyArray = new ManageMemberCapabilities[0];
                        }
                    }
                }
                return _emptyArray;
            }

            public ManageMemberCapabilities() {
                clear();
            }

            public ManageMemberCapabilities clear() {
                this.memberId = BuildConfig.VERSION_NAME;
                this.capability = WireFormatNano.EMPTY_INT_ARRAY;
                this.unknownFieldData = null;
                this.cachedSize = -1;
                return this;
            }

            public boolean equals(Object o) {
                if (o == this) {
                    return true;
                }
                if (!(o instanceof ManageMemberCapabilities)) {
                    return false;
                }
                ManageMemberCapabilities other = (ManageMemberCapabilities) o;
                if (this.memberId == null) {
                    if (other.memberId != null) {
                        return false;
                    }
                } else if (!this.memberId.equals(other.memberId)) {
                    return false;
                }
                if (!InternalNano.equals(this.capability, other.capability)) {
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
                int hashCode = (((((getClass().getName().hashCode() + 527) * 31) + (this.memberId == null ? 0 : this.memberId.hashCode())) * 31) + InternalNano.hashCode(this.capability)) * 31;
                if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                    i = this.unknownFieldData.hashCode();
                }
                return hashCode + i;
            }

            public void writeTo(CodedOutputByteBufferNano output) throws IOException {
                if (!this.memberId.equals(BuildConfig.VERSION_NAME)) {
                    output.writeString(1, this.memberId);
                }
                if (this.capability != null && this.capability.length > 0) {
                    for (int writeInt32 : this.capability) {
                        output.writeInt32(2, writeInt32);
                    }
                }
                super.writeTo(output);
            }

            protected int computeSerializedSize() {
                int size = super.computeSerializedSize();
                if (!this.memberId.equals(BuildConfig.VERSION_NAME)) {
                    size += CodedOutputByteBufferNano.computeStringSize(1, this.memberId);
                }
                if (this.capability == null || this.capability.length <= 0) {
                    return size;
                }
                int dataSize = 0;
                for (int element : this.capability) {
                    dataSize += CodedOutputByteBufferNano.computeInt32SizeNoTag(element);
                }
                return (size + dataSize) + (this.capability.length * 1);
            }

            public ManageMemberCapabilities mergeFrom(CodedInputByteBufferNano input) throws IOException {
                while (true) {
                    int tag = input.readTag();
                    int i;
                    int value;
                    int[] newArray;
                    switch (tag) {
                        case Action.UNKNOWN /*0*/:
                            break;
                        case Type.TAP_ABOUT /*10*/:
                            this.memberId = input.readString();
                            continue;
                        case LogSource.GMS_CORE_PEOPLE /*16*/:
                            int length = WireFormatNano.getRepeatedFieldArrayLength(input, 16);
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
                                    case TimeSelection.Type.TEMPORARY /*1*/:
                                    case TimeSelection.Type.INDEFINITELY /*2*/:
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
                                i = this.capability == null ? 0 : this.capability.length;
                                if (i != 0 || validCount != validValues.length) {
                                    newArray = new int[(i + validCount)];
                                    if (i != 0) {
                                        System.arraycopy(this.capability, 0, newArray, 0, i);
                                    }
                                    System.arraycopy(validValues, 0, newArray, i, validCount);
                                    this.capability = newArray;
                                    break;
                                }
                                this.capability = validValues;
                                break;
                            }
                            continue;
                        case LogSource.GOOGLE_ANALYTICS /*18*/:
                            int limit = input.pushLimit(input.readRawVarint32());
                            int arrayLength = 0;
                            int startPos = input.getPosition();
                            while (input.getBytesUntilLimit() > 0) {
                                switch (input.readInt32()) {
                                    case TimeSelection.Type.TEMPORARY /*1*/:
                                    case TimeSelection.Type.INDEFINITELY /*2*/:
                                        arrayLength++;
                                        break;
                                    default:
                                        break;
                                }
                            }
                            if (arrayLength != 0) {
                                input.rewindToPosition(startPos);
                                if (this.capability == null) {
                                    i = 0;
                                } else {
                                    i = this.capability.length;
                                }
                                newArray = new int[(i + arrayLength)];
                                if (i != 0) {
                                    System.arraycopy(this.capability, 0, newArray, 0, i);
                                }
                                while (input.getBytesUntilLimit() > 0) {
                                    value = input.readInt32();
                                    switch (value) {
                                        case TimeSelection.Type.TEMPORARY /*1*/:
                                        case TimeSelection.Type.INDEFINITELY /*2*/:
                                            int i2 = i + 1;
                                            newArray[i] = value;
                                            i = i2;
                                            break;
                                        default:
                                            break;
                                    }
                                }
                                this.capability = newArray;
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

            public static ManageMemberCapabilities parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
                return (ManageMemberCapabilities) MessageNano.mergeFrom(new ManageMemberCapabilities(), data);
            }

            public static ManageMemberCapabilities parseFrom(CodedInputByteBufferNano input) throws IOException {
                return new ManageMemberCapabilities().mergeFrom(input);
            }
        }

        public static ManagementCapabilities[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new ManagementCapabilities[0];
                    }
                }
            }
            return _emptyArray;
        }

        public ManagementCapabilities() {
            clear();
        }

        public ManagementCapabilities clear() {
            this.manageFamilyCapability = WireFormatNano.EMPTY_INT_ARRAY;
            this.manageMemberCapabilities = ManageMemberCapabilities.emptyArray();
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof ManagementCapabilities)) {
                return false;
            }
            ManagementCapabilities other = (ManagementCapabilities) o;
            if (!InternalNano.equals(this.manageFamilyCapability, other.manageFamilyCapability) || !InternalNano.equals(this.manageMemberCapabilities, other.manageMemberCapabilities)) {
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
            int hashCode = (((((getClass().getName().hashCode() + 527) * 31) + InternalNano.hashCode(this.manageFamilyCapability)) * 31) + InternalNano.hashCode(this.manageMemberCapabilities)) * 31;
            int hashCode2 = (this.unknownFieldData == null || this.unknownFieldData.isEmpty()) ? 0 : this.unknownFieldData.hashCode();
            return hashCode + hashCode2;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.manageFamilyCapability != null && this.manageFamilyCapability.length > 0) {
                for (int writeInt32 : this.manageFamilyCapability) {
                    output.writeInt32(1, writeInt32);
                }
            }
            if (this.manageMemberCapabilities != null && this.manageMemberCapabilities.length > 0) {
                for (ManageMemberCapabilities element : this.manageMemberCapabilities) {
                    if (element != null) {
                        output.writeMessage(2, element);
                    }
                }
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.manageFamilyCapability != null && this.manageFamilyCapability.length > 0) {
                int dataSize = 0;
                for (int element : this.manageFamilyCapability) {
                    dataSize += CodedOutputByteBufferNano.computeInt32SizeNoTag(element);
                }
                size = (size + dataSize) + (this.manageFamilyCapability.length * 1);
            }
            if (this.manageMemberCapabilities != null && this.manageMemberCapabilities.length > 0) {
                for (ManageMemberCapabilities element2 : this.manageMemberCapabilities) {
                    if (element2 != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(2, element2);
                    }
                }
            }
            return size;
        }

        public ManagementCapabilities mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                int i;
                int value;
                int[] newArray;
                int arrayLength;
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
                            i = this.manageFamilyCapability == null ? 0 : this.manageFamilyCapability.length;
                            if (i != 0 || validCount != validValues.length) {
                                newArray = new int[(i + validCount)];
                                if (i != 0) {
                                    System.arraycopy(this.manageFamilyCapability, 0, newArray, 0, i);
                                }
                                System.arraycopy(validValues, 0, newArray, i, validCount);
                                this.manageFamilyCapability = newArray;
                                break;
                            }
                            this.manageFamilyCapability = validValues;
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
                                    arrayLength++;
                                    break;
                                default:
                                    break;
                            }
                        }
                        if (arrayLength != 0) {
                            input.rewindToPosition(startPos);
                            if (this.manageFamilyCapability == null) {
                                i = 0;
                            } else {
                                i = this.manageFamilyCapability.length;
                            }
                            newArray = new int[(i + arrayLength)];
                            if (i != 0) {
                                System.arraycopy(this.manageFamilyCapability, 0, newArray, 0, i);
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
                            this.manageFamilyCapability = newArray;
                        }
                        input.popLimit(limit);
                        continue;
                    case LogSource.GOOGLE_ANALYTICS /*18*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 18);
                        if (this.manageMemberCapabilities == null) {
                            i = 0;
                        } else {
                            i = this.manageMemberCapabilities.length;
                        }
                        ManageMemberCapabilities[] newArray2 = new ManageMemberCapabilities[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.manageMemberCapabilities, 0, newArray2, 0, i);
                        }
                        while (i < newArray2.length - 1) {
                            newArray2[i] = new ManageMemberCapabilities();
                            input.readMessage(newArray2[i]);
                            input.readTag();
                            i++;
                        }
                        newArray2[i] = new ManageMemberCapabilities();
                        input.readMessage(newArray2[i]);
                        this.manageMemberCapabilities = newArray2;
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

        public static ManagementCapabilities parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (ManagementCapabilities) MessageNano.mergeFrom(new ManagementCapabilities(), data);
        }

        public static ManagementCapabilities parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new ManagementCapabilities().mergeFrom(input);
        }
    }
}
