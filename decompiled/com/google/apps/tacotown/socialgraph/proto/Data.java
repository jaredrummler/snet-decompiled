package com.google.apps.tacotown.socialgraph.proto;

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

public interface Data {

    public static final class data extends ExtendableMessageNano<data> {
        private static volatile data[] _emptyArray;

        public static final class CircleData extends ExtendableMessageNano<CircleData> {
            private static volatile CircleData[] _emptyArray;
            public CircleId circleId;
            public CircleProperties circleProperties;
            public ContinuationToken continuationToken;
            public boolean deleted;

            public static CircleData[] emptyArray() {
                if (_emptyArray == null) {
                    synchronized (InternalNano.LAZY_INIT_LOCK) {
                        if (_emptyArray == null) {
                            _emptyArray = new CircleData[0];
                        }
                    }
                }
                return _emptyArray;
            }

            public CircleData() {
                clear();
            }

            public CircleData clear() {
                this.circleId = null;
                this.circleProperties = null;
                this.deleted = false;
                this.continuationToken = null;
                this.unknownFieldData = null;
                this.cachedSize = -1;
                return this;
            }

            public boolean equals(Object o) {
                if (o == this) {
                    return true;
                }
                if (!(o instanceof CircleData)) {
                    return false;
                }
                CircleData other = (CircleData) o;
                if (this.circleId == null) {
                    if (other.circleId != null) {
                        return false;
                    }
                } else if (!this.circleId.equals(other.circleId)) {
                    return false;
                }
                if (this.circleProperties == null) {
                    if (other.circleProperties != null) {
                        return false;
                    }
                } else if (!this.circleProperties.equals(other.circleProperties)) {
                    return false;
                }
                if (this.deleted != other.deleted) {
                    return false;
                }
                if (this.continuationToken == null) {
                    if (other.continuationToken != null) {
                        return false;
                    }
                } else if (!this.continuationToken.equals(other.continuationToken)) {
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
                int hashCode = (((((((((getClass().getName().hashCode() + 527) * 31) + (this.circleId == null ? 0 : this.circleId.hashCode())) * 31) + (this.circleProperties == null ? 0 : this.circleProperties.hashCode())) * 31) + (this.deleted ? 1231 : 1237)) * 31) + (this.continuationToken == null ? 0 : this.continuationToken.hashCode())) * 31;
                if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                    i = this.unknownFieldData.hashCode();
                }
                return hashCode + i;
            }

            public void writeTo(CodedOutputByteBufferNano output) throws IOException {
                if (this.circleId != null) {
                    output.writeMessage(1, this.circleId);
                }
                if (this.circleProperties != null) {
                    output.writeMessage(2, this.circleProperties);
                }
                if (this.deleted) {
                    output.writeBool(3, this.deleted);
                }
                if (this.continuationToken != null) {
                    output.writeMessage(5, this.continuationToken);
                }
                super.writeTo(output);
            }

            protected int computeSerializedSize() {
                int size = super.computeSerializedSize();
                if (this.circleId != null) {
                    size += CodedOutputByteBufferNano.computeMessageSize(1, this.circleId);
                }
                if (this.circleProperties != null) {
                    size += CodedOutputByteBufferNano.computeMessageSize(2, this.circleProperties);
                }
                if (this.deleted) {
                    size += CodedOutputByteBufferNano.computeBoolSize(3, this.deleted);
                }
                if (this.continuationToken != null) {
                    return size + CodedOutputByteBufferNano.computeMessageSize(5, this.continuationToken);
                }
                return size;
            }

            public CircleData mergeFrom(CodedInputByteBufferNano input) throws IOException {
                while (true) {
                    int tag = input.readTag();
                    switch (tag) {
                        case Action.UNKNOWN /*0*/:
                            break;
                        case Type.TAP_ABOUT /*10*/:
                            if (this.circleId == null) {
                                this.circleId = new CircleId();
                            }
                            input.readMessage(this.circleId);
                            continue;
                        case LogSource.GOOGLE_ANALYTICS /*18*/:
                            if (this.circleProperties == null) {
                                this.circleProperties = new CircleProperties();
                            }
                            input.readMessage(this.circleProperties);
                            continue;
                        case LogSource.LB_C /*24*/:
                            this.deleted = input.readBool();
                            continue;
                        case LogSource.PHOTOS /*42*/:
                            if (this.continuationToken == null) {
                                this.continuationToken = new ContinuationToken();
                            }
                            input.readMessage(this.continuationToken);
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

            public static CircleData parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
                return (CircleData) MessageNano.mergeFrom(new CircleData(), data);
            }

            public static CircleData parseFrom(CodedInputByteBufferNano input) throws IOException {
                return new CircleData().mergeFrom(input);
            }
        }

        public static final class CircleId extends ExtendableMessageNano<CircleId> {
            private static volatile CircleId[] _emptyArray;
            public String focusId;
            public String obfuscatedGaiaId;

            public static CircleId[] emptyArray() {
                if (_emptyArray == null) {
                    synchronized (InternalNano.LAZY_INIT_LOCK) {
                        if (_emptyArray == null) {
                            _emptyArray = new CircleId[0];
                        }
                    }
                }
                return _emptyArray;
            }

            public CircleId() {
                clear();
            }

            public CircleId clear() {
                this.focusId = BuildConfig.VERSION_NAME;
                this.obfuscatedGaiaId = BuildConfig.VERSION_NAME;
                this.unknownFieldData = null;
                this.cachedSize = -1;
                return this;
            }

            public boolean equals(Object o) {
                if (o == this) {
                    return true;
                }
                if (!(o instanceof CircleId)) {
                    return false;
                }
                CircleId other = (CircleId) o;
                if (this.focusId == null) {
                    if (other.focusId != null) {
                        return false;
                    }
                } else if (!this.focusId.equals(other.focusId)) {
                    return false;
                }
                if (this.obfuscatedGaiaId == null) {
                    if (other.obfuscatedGaiaId != null) {
                        return false;
                    }
                } else if (!this.obfuscatedGaiaId.equals(other.obfuscatedGaiaId)) {
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
                int hashCode = (((((getClass().getName().hashCode() + 527) * 31) + (this.focusId == null ? 0 : this.focusId.hashCode())) * 31) + (this.obfuscatedGaiaId == null ? 0 : this.obfuscatedGaiaId.hashCode())) * 31;
                if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                    i = this.unknownFieldData.hashCode();
                }
                return hashCode + i;
            }

            public void writeTo(CodedOutputByteBufferNano output) throws IOException {
                if (!this.focusId.equals(BuildConfig.VERSION_NAME)) {
                    output.writeString(1, this.focusId);
                }
                if (!this.obfuscatedGaiaId.equals(BuildConfig.VERSION_NAME)) {
                    output.writeString(2, this.obfuscatedGaiaId);
                }
                super.writeTo(output);
            }

            protected int computeSerializedSize() {
                int size = super.computeSerializedSize();
                if (!this.focusId.equals(BuildConfig.VERSION_NAME)) {
                    size += CodedOutputByteBufferNano.computeStringSize(1, this.focusId);
                }
                if (this.obfuscatedGaiaId.equals(BuildConfig.VERSION_NAME)) {
                    return size;
                }
                return size + CodedOutputByteBufferNano.computeStringSize(2, this.obfuscatedGaiaId);
            }

            public CircleId mergeFrom(CodedInputByteBufferNano input) throws IOException {
                while (true) {
                    int tag = input.readTag();
                    switch (tag) {
                        case Action.UNKNOWN /*0*/:
                            break;
                        case Type.TAP_ABOUT /*10*/:
                            this.focusId = input.readString();
                            continue;
                        case LogSource.GOOGLE_ANALYTICS /*18*/:
                            this.obfuscatedGaiaId = input.readString();
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

            public static CircleId parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
                return (CircleId) MessageNano.mergeFrom(new CircleId(), data);
            }

            public static CircleId parseFrom(CodedInputByteBufferNano input) throws IOException {
                return new CircleId().mergeFrom(input);
            }
        }

        public static final class CircleMemberId extends ExtendableMessageNano<CircleMemberId> {
            private static volatile CircleMemberId[] _emptyArray;
            public String contactId;
            public String email;
            public String obfuscatedGaiaId;
            public String phone;
            public String syntheticContactId;
            public String url;

            public static CircleMemberId[] emptyArray() {
                if (_emptyArray == null) {
                    synchronized (InternalNano.LAZY_INIT_LOCK) {
                        if (_emptyArray == null) {
                            _emptyArray = new CircleMemberId[0];
                        }
                    }
                }
                return _emptyArray;
            }

            public CircleMemberId() {
                clear();
            }

            public CircleMemberId clear() {
                this.email = BuildConfig.VERSION_NAME;
                this.contactId = BuildConfig.VERSION_NAME;
                this.obfuscatedGaiaId = BuildConfig.VERSION_NAME;
                this.phone = BuildConfig.VERSION_NAME;
                this.url = BuildConfig.VERSION_NAME;
                this.syntheticContactId = BuildConfig.VERSION_NAME;
                this.unknownFieldData = null;
                this.cachedSize = -1;
                return this;
            }

            public boolean equals(Object o) {
                if (o == this) {
                    return true;
                }
                if (!(o instanceof CircleMemberId)) {
                    return false;
                }
                CircleMemberId other = (CircleMemberId) o;
                if (this.email == null) {
                    if (other.email != null) {
                        return false;
                    }
                } else if (!this.email.equals(other.email)) {
                    return false;
                }
                if (this.contactId == null) {
                    if (other.contactId != null) {
                        return false;
                    }
                } else if (!this.contactId.equals(other.contactId)) {
                    return false;
                }
                if (this.obfuscatedGaiaId == null) {
                    if (other.obfuscatedGaiaId != null) {
                        return false;
                    }
                } else if (!this.obfuscatedGaiaId.equals(other.obfuscatedGaiaId)) {
                    return false;
                }
                if (this.phone == null) {
                    if (other.phone != null) {
                        return false;
                    }
                } else if (!this.phone.equals(other.phone)) {
                    return false;
                }
                if (this.url == null) {
                    if (other.url != null) {
                        return false;
                    }
                } else if (!this.url.equals(other.url)) {
                    return false;
                }
                if (this.syntheticContactId == null) {
                    if (other.syntheticContactId != null) {
                        return false;
                    }
                } else if (!this.syntheticContactId.equals(other.syntheticContactId)) {
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
                int hashCode = (((((((((((((getClass().getName().hashCode() + 527) * 31) + (this.email == null ? 0 : this.email.hashCode())) * 31) + (this.contactId == null ? 0 : this.contactId.hashCode())) * 31) + (this.obfuscatedGaiaId == null ? 0 : this.obfuscatedGaiaId.hashCode())) * 31) + (this.phone == null ? 0 : this.phone.hashCode())) * 31) + (this.url == null ? 0 : this.url.hashCode())) * 31) + (this.syntheticContactId == null ? 0 : this.syntheticContactId.hashCode())) * 31;
                if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                    i = this.unknownFieldData.hashCode();
                }
                return hashCode + i;
            }

            public void writeTo(CodedOutputByteBufferNano output) throws IOException {
                if (!this.email.equals(BuildConfig.VERSION_NAME)) {
                    output.writeString(1, this.email);
                }
                if (!this.contactId.equals(BuildConfig.VERSION_NAME)) {
                    output.writeString(2, this.contactId);
                }
                if (!this.obfuscatedGaiaId.equals(BuildConfig.VERSION_NAME)) {
                    output.writeString(3, this.obfuscatedGaiaId);
                }
                if (!this.phone.equals(BuildConfig.VERSION_NAME)) {
                    output.writeString(4, this.phone);
                }
                if (!this.url.equals(BuildConfig.VERSION_NAME)) {
                    output.writeString(5, this.url);
                }
                if (!this.syntheticContactId.equals(BuildConfig.VERSION_NAME)) {
                    output.writeString(6, this.syntheticContactId);
                }
                super.writeTo(output);
            }

            protected int computeSerializedSize() {
                int size = super.computeSerializedSize();
                if (!this.email.equals(BuildConfig.VERSION_NAME)) {
                    size += CodedOutputByteBufferNano.computeStringSize(1, this.email);
                }
                if (!this.contactId.equals(BuildConfig.VERSION_NAME)) {
                    size += CodedOutputByteBufferNano.computeStringSize(2, this.contactId);
                }
                if (!this.obfuscatedGaiaId.equals(BuildConfig.VERSION_NAME)) {
                    size += CodedOutputByteBufferNano.computeStringSize(3, this.obfuscatedGaiaId);
                }
                if (!this.phone.equals(BuildConfig.VERSION_NAME)) {
                    size += CodedOutputByteBufferNano.computeStringSize(4, this.phone);
                }
                if (!this.url.equals(BuildConfig.VERSION_NAME)) {
                    size += CodedOutputByteBufferNano.computeStringSize(5, this.url);
                }
                if (this.syntheticContactId.equals(BuildConfig.VERSION_NAME)) {
                    return size;
                }
                return size + CodedOutputByteBufferNano.computeStringSize(6, this.syntheticContactId);
            }

            public CircleMemberId mergeFrom(CodedInputByteBufferNano input) throws IOException {
                while (true) {
                    int tag = input.readTag();
                    switch (tag) {
                        case Action.UNKNOWN /*0*/:
                            break;
                        case Type.TAP_ABOUT /*10*/:
                            this.email = input.readString();
                            continue;
                        case LogSource.GOOGLE_ANALYTICS /*18*/:
                            this.contactId = input.readString();
                            continue;
                        case LogSource.ANDROID_CAMERA /*26*/:
                            this.obfuscatedGaiaId = input.readString();
                            continue;
                        case LogSource.NOVA /*34*/:
                            this.phone = input.readString();
                            continue;
                        case LogSource.PHOTOS /*42*/:
                            this.url = input.readString();
                            continue;
                        case LogSource.HANGOUT_LOG_REQUEST /*50*/:
                            this.syntheticContactId = input.readString();
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

            public static CircleMemberId parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
                return (CircleMemberId) MessageNano.mergeFrom(new CircleMemberId(), data);
            }

            public static CircleMemberId parseFrom(CodedInputByteBufferNano input) throws IOException {
                return new CircleMemberId().mergeFrom(input);
            }
        }

        public static final class CircleMemberProperties extends ExtendableMessageNano<CircleMemberProperties> {
            private static volatile CircleMemberProperties[] _emptyArray;
            public Address[] address;
            public HovercardBannerInfo bannerInfo;
            public String company;
            public boolean contact;
            public String[] contactId;
            public int[] disallowedInteractions;
            public String displayName;
            public Email[] email;
            public EntityInfo entityInfo;
            public boolean esUser;
            public String firstName;
            public String firstNameSortKey;
            public String focusPhotoUrl;
            public int gender;
            public boolean inIncomingCircle;
            public boolean inSameVisibilityGroup;
            public double interactionsRank;
            public String interactionsRankSortKey;
            public boolean inviter;
            public boolean isNameResolvedContact;
            public String lastNameSortKey;
            public String lastUpdateTime;
            public String location;
            public String occupation;
            public Phone[] phone;
            public String photoUrl;
            public int profileType;
            public String role;
            public String school;
            public String suggestionId;
            public String tagLine;
            public boolean verified;
            public String[] verifiedPhone;

            public static final class Address extends ExtendableMessageNano<Address> {
                private static volatile Address[] _emptyArray;
                public String country;
                public String customTag;
                public String extendedAddress;
                public String locality;
                public String poBox;
                public String postalCode;
                public String region;
                public int standardTag;
                public String streetAddress;
                public String value;

                public static Address[] emptyArray() {
                    if (_emptyArray == null) {
                        synchronized (InternalNano.LAZY_INIT_LOCK) {
                            if (_emptyArray == null) {
                                _emptyArray = new Address[0];
                            }
                        }
                    }
                    return _emptyArray;
                }

                public Address() {
                    clear();
                }

                public Address clear() {
                    this.value = BuildConfig.VERSION_NAME;
                    this.poBox = BuildConfig.VERSION_NAME;
                    this.extendedAddress = BuildConfig.VERSION_NAME;
                    this.streetAddress = BuildConfig.VERSION_NAME;
                    this.locality = BuildConfig.VERSION_NAME;
                    this.region = BuildConfig.VERSION_NAME;
                    this.postalCode = BuildConfig.VERSION_NAME;
                    this.country = BuildConfig.VERSION_NAME;
                    this.standardTag = 1;
                    this.customTag = BuildConfig.VERSION_NAME;
                    this.unknownFieldData = null;
                    this.cachedSize = -1;
                    return this;
                }

                public boolean equals(Object o) {
                    if (o == this) {
                        return true;
                    }
                    if (!(o instanceof Address)) {
                        return false;
                    }
                    Address other = (Address) o;
                    if (this.value == null) {
                        if (other.value != null) {
                            return false;
                        }
                    } else if (!this.value.equals(other.value)) {
                        return false;
                    }
                    if (this.poBox == null) {
                        if (other.poBox != null) {
                            return false;
                        }
                    } else if (!this.poBox.equals(other.poBox)) {
                        return false;
                    }
                    if (this.extendedAddress == null) {
                        if (other.extendedAddress != null) {
                            return false;
                        }
                    } else if (!this.extendedAddress.equals(other.extendedAddress)) {
                        return false;
                    }
                    if (this.streetAddress == null) {
                        if (other.streetAddress != null) {
                            return false;
                        }
                    } else if (!this.streetAddress.equals(other.streetAddress)) {
                        return false;
                    }
                    if (this.locality == null) {
                        if (other.locality != null) {
                            return false;
                        }
                    } else if (!this.locality.equals(other.locality)) {
                        return false;
                    }
                    if (this.region == null) {
                        if (other.region != null) {
                            return false;
                        }
                    } else if (!this.region.equals(other.region)) {
                        return false;
                    }
                    if (this.postalCode == null) {
                        if (other.postalCode != null) {
                            return false;
                        }
                    } else if (!this.postalCode.equals(other.postalCode)) {
                        return false;
                    }
                    if (this.country == null) {
                        if (other.country != null) {
                            return false;
                        }
                    } else if (!this.country.equals(other.country)) {
                        return false;
                    }
                    if (this.standardTag != other.standardTag) {
                        return false;
                    }
                    if (this.customTag == null) {
                        if (other.customTag != null) {
                            return false;
                        }
                    } else if (!this.customTag.equals(other.customTag)) {
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
                    int hashCode = (((((((((((((((((((((getClass().getName().hashCode() + 527) * 31) + (this.value == null ? 0 : this.value.hashCode())) * 31) + (this.poBox == null ? 0 : this.poBox.hashCode())) * 31) + (this.extendedAddress == null ? 0 : this.extendedAddress.hashCode())) * 31) + (this.streetAddress == null ? 0 : this.streetAddress.hashCode())) * 31) + (this.locality == null ? 0 : this.locality.hashCode())) * 31) + (this.region == null ? 0 : this.region.hashCode())) * 31) + (this.postalCode == null ? 0 : this.postalCode.hashCode())) * 31) + (this.country == null ? 0 : this.country.hashCode())) * 31) + this.standardTag) * 31) + (this.customTag == null ? 0 : this.customTag.hashCode())) * 31;
                    if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                        i = this.unknownFieldData.hashCode();
                    }
                    return hashCode + i;
                }

                public void writeTo(CodedOutputByteBufferNano output) throws IOException {
                    if (!this.value.equals(BuildConfig.VERSION_NAME)) {
                        output.writeString(1, this.value);
                    }
                    if (!this.poBox.equals(BuildConfig.VERSION_NAME)) {
                        output.writeString(2, this.poBox);
                    }
                    if (!this.extendedAddress.equals(BuildConfig.VERSION_NAME)) {
                        output.writeString(3, this.extendedAddress);
                    }
                    if (!this.streetAddress.equals(BuildConfig.VERSION_NAME)) {
                        output.writeString(4, this.streetAddress);
                    }
                    if (!this.locality.equals(BuildConfig.VERSION_NAME)) {
                        output.writeString(5, this.locality);
                    }
                    if (!this.region.equals(BuildConfig.VERSION_NAME)) {
                        output.writeString(6, this.region);
                    }
                    if (!this.postalCode.equals(BuildConfig.VERSION_NAME)) {
                        output.writeString(7, this.postalCode);
                    }
                    if (!this.country.equals(BuildConfig.VERSION_NAME)) {
                        output.writeString(8, this.country);
                    }
                    if (this.standardTag != 1) {
                        output.writeInt32(9, this.standardTag);
                    }
                    if (!this.customTag.equals(BuildConfig.VERSION_NAME)) {
                        output.writeString(10, this.customTag);
                    }
                    super.writeTo(output);
                }

                protected int computeSerializedSize() {
                    int size = super.computeSerializedSize();
                    if (!this.value.equals(BuildConfig.VERSION_NAME)) {
                        size += CodedOutputByteBufferNano.computeStringSize(1, this.value);
                    }
                    if (!this.poBox.equals(BuildConfig.VERSION_NAME)) {
                        size += CodedOutputByteBufferNano.computeStringSize(2, this.poBox);
                    }
                    if (!this.extendedAddress.equals(BuildConfig.VERSION_NAME)) {
                        size += CodedOutputByteBufferNano.computeStringSize(3, this.extendedAddress);
                    }
                    if (!this.streetAddress.equals(BuildConfig.VERSION_NAME)) {
                        size += CodedOutputByteBufferNano.computeStringSize(4, this.streetAddress);
                    }
                    if (!this.locality.equals(BuildConfig.VERSION_NAME)) {
                        size += CodedOutputByteBufferNano.computeStringSize(5, this.locality);
                    }
                    if (!this.region.equals(BuildConfig.VERSION_NAME)) {
                        size += CodedOutputByteBufferNano.computeStringSize(6, this.region);
                    }
                    if (!this.postalCode.equals(BuildConfig.VERSION_NAME)) {
                        size += CodedOutputByteBufferNano.computeStringSize(7, this.postalCode);
                    }
                    if (!this.country.equals(BuildConfig.VERSION_NAME)) {
                        size += CodedOutputByteBufferNano.computeStringSize(8, this.country);
                    }
                    if (this.standardTag != 1) {
                        size += CodedOutputByteBufferNano.computeInt32Size(9, this.standardTag);
                    }
                    if (this.customTag.equals(BuildConfig.VERSION_NAME)) {
                        return size;
                    }
                    return size + CodedOutputByteBufferNano.computeStringSize(10, this.customTag);
                }

                public Address mergeFrom(CodedInputByteBufferNano input) throws IOException {
                    while (true) {
                        int tag = input.readTag();
                        switch (tag) {
                            case Action.UNKNOWN /*0*/:
                                break;
                            case Type.TAP_ABOUT /*10*/:
                                this.value = input.readString();
                                continue;
                            case LogSource.GOOGLE_ANALYTICS /*18*/:
                                this.poBox = input.readString();
                                continue;
                            case LogSource.ANDROID_CAMERA /*26*/:
                                this.extendedAddress = input.readString();
                                continue;
                            case LogSource.NOVA /*34*/:
                                this.streetAddress = input.readString();
                                continue;
                            case LogSource.PHOTOS /*42*/:
                                this.locality = input.readString();
                                continue;
                            case LogSource.HANGOUT_LOG_REQUEST /*50*/:
                                this.region = input.readString();
                                continue;
                            case LogSource.SLIDES /*58*/:
                                this.postalCode = input.readString();
                                continue;
                            case LogSource.WIFI_ASSISTANT /*66*/:
                                this.country = input.readString();
                                continue;
                            case LogSource.JUSTSPEAK /*72*/:
                                this.standardTag = input.readInt32();
                                continue;
                            case LogSource.GOOGLE_EXPRESS /*82*/:
                                this.customTag = input.readString();
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

                public static Address parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
                    return (Address) MessageNano.mergeFrom(new Address(), data);
                }

                public static Address parseFrom(CodedInputByteBufferNano input) throws IOException {
                    return new Address().mergeFrom(input);
                }
            }

            public static final class EntityInfo extends ExtendableMessageNano<EntityInfo> {
                private static volatile EntityInfo[] _emptyArray;
                public int plusPageType;
                public String primaryLink;
                public String primaryPhone;
                public int type;

                public static EntityInfo[] emptyArray() {
                    if (_emptyArray == null) {
                        synchronized (InternalNano.LAZY_INIT_LOCK) {
                            if (_emptyArray == null) {
                                _emptyArray = new EntityInfo[0];
                            }
                        }
                    }
                    return _emptyArray;
                }

                public EntityInfo() {
                    clear();
                }

                public EntityInfo clear() {
                    this.type = 0;
                    this.primaryLink = BuildConfig.VERSION_NAME;
                    this.primaryPhone = BuildConfig.VERSION_NAME;
                    this.plusPageType = 0;
                    this.unknownFieldData = null;
                    this.cachedSize = -1;
                    return this;
                }

                public boolean equals(Object o) {
                    if (o == this) {
                        return true;
                    }
                    if (!(o instanceof EntityInfo)) {
                        return false;
                    }
                    EntityInfo other = (EntityInfo) o;
                    if (this.type != other.type) {
                        return false;
                    }
                    if (this.primaryLink == null) {
                        if (other.primaryLink != null) {
                            return false;
                        }
                    } else if (!this.primaryLink.equals(other.primaryLink)) {
                        return false;
                    }
                    if (this.primaryPhone == null) {
                        if (other.primaryPhone != null) {
                            return false;
                        }
                    } else if (!this.primaryPhone.equals(other.primaryPhone)) {
                        return false;
                    }
                    if (this.plusPageType != other.plusPageType) {
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
                    int hashCode = (((((((((getClass().getName().hashCode() + 527) * 31) + this.type) * 31) + (this.primaryLink == null ? 0 : this.primaryLink.hashCode())) * 31) + (this.primaryPhone == null ? 0 : this.primaryPhone.hashCode())) * 31) + this.plusPageType) * 31;
                    if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                        i = this.unknownFieldData.hashCode();
                    }
                    return hashCode + i;
                }

                public void writeTo(CodedOutputByteBufferNano output) throws IOException {
                    if (this.type != 0) {
                        output.writeInt32(1, this.type);
                    }
                    if (!this.primaryLink.equals(BuildConfig.VERSION_NAME)) {
                        output.writeString(2, this.primaryLink);
                    }
                    if (!this.primaryPhone.equals(BuildConfig.VERSION_NAME)) {
                        output.writeString(3, this.primaryPhone);
                    }
                    if (this.plusPageType != 0) {
                        output.writeInt32(5, this.plusPageType);
                    }
                    super.writeTo(output);
                }

                protected int computeSerializedSize() {
                    int size = super.computeSerializedSize();
                    if (this.type != 0) {
                        size += CodedOutputByteBufferNano.computeInt32Size(1, this.type);
                    }
                    if (!this.primaryLink.equals(BuildConfig.VERSION_NAME)) {
                        size += CodedOutputByteBufferNano.computeStringSize(2, this.primaryLink);
                    }
                    if (!this.primaryPhone.equals(BuildConfig.VERSION_NAME)) {
                        size += CodedOutputByteBufferNano.computeStringSize(3, this.primaryPhone);
                    }
                    if (this.plusPageType != 0) {
                        return size + CodedOutputByteBufferNano.computeInt32Size(5, this.plusPageType);
                    }
                    return size;
                }

                public EntityInfo mergeFrom(CodedInputByteBufferNano input) throws IOException {
                    while (true) {
                        int tag = input.readTag();
                        switch (tag) {
                            case Action.UNKNOWN /*0*/:
                                break;
                            case Type.TAP_GET_LINK /*8*/:
                                this.type = input.readInt32();
                                continue;
                            case LogSource.GOOGLE_ANALYTICS /*18*/:
                                this.primaryLink = input.readString();
                                continue;
                            case LogSource.ANDROID_CAMERA /*26*/:
                                this.primaryPhone = input.readString();
                                continue;
                            case LogSource.COPRESENCE /*40*/:
                                this.plusPageType = input.readInt32();
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

                public static EntityInfo parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
                    return (EntityInfo) MessageNano.mergeFrom(new EntityInfo(), data);
                }

                public static EntityInfo parseFrom(CodedInputByteBufferNano input) throws IOException {
                    return new EntityInfo().mergeFrom(input);
                }
            }

            public interface Gender {
                public static final int FEMALE = 2;
                public static final int MALE = 1;
                public static final int UNKNOWN = 0;
            }

            public interface Interaction {
                public static final int INCOMING_CIRCLE_MEMBERSHIP = 1;
                public static final int INCOMING_SOCIAL_EDGE = 2;
            }

            public static CircleMemberProperties[] emptyArray() {
                if (_emptyArray == null) {
                    synchronized (InternalNano.LAZY_INIT_LOCK) {
                        if (_emptyArray == null) {
                            _emptyArray = new CircleMemberProperties[0];
                        }
                    }
                }
                return _emptyArray;
            }

            public CircleMemberProperties() {
                clear();
            }

            public CircleMemberProperties clear() {
                this.displayName = BuildConfig.VERSION_NAME;
                this.firstName = BuildConfig.VERSION_NAME;
                this.firstNameSortKey = BuildConfig.VERSION_NAME;
                this.lastNameSortKey = BuildConfig.VERSION_NAME;
                this.photoUrl = BuildConfig.VERSION_NAME;
                this.focusPhotoUrl = BuildConfig.VERSION_NAME;
                this.interactionsRank = 0.0d;
                this.interactionsRankSortKey = BuildConfig.VERSION_NAME;
                this.lastUpdateTime = BuildConfig.VERSION_NAME;
                this.contact = false;
                this.isNameResolvedContact = false;
                this.esUser = false;
                this.inSameVisibilityGroup = false;
                this.profileType = 0;
                this.inIncomingCircle = false;
                this.verified = false;
                this.inviter = false;
                this.location = BuildConfig.VERSION_NAME;
                this.school = BuildConfig.VERSION_NAME;
                this.company = BuildConfig.VERSION_NAME;
                this.role = BuildConfig.VERSION_NAME;
                this.occupation = BuildConfig.VERSION_NAME;
                this.tagLine = BuildConfig.VERSION_NAME;
                this.gender = 0;
                this.contactId = WireFormatNano.EMPTY_STRING_ARRAY;
                this.entityInfo = null;
                this.disallowedInteractions = WireFormatNano.EMPTY_INT_ARRAY;
                this.bannerInfo = null;
                this.email = Email.emptyArray();
                this.phone = Phone.emptyArray();
                this.address = Address.emptyArray();
                this.verifiedPhone = WireFormatNano.EMPTY_STRING_ARRAY;
                this.suggestionId = BuildConfig.VERSION_NAME;
                this.unknownFieldData = null;
                this.cachedSize = -1;
                return this;
            }

            public boolean equals(Object o) {
                if (o == this) {
                    return true;
                }
                if (!(o instanceof CircleMemberProperties)) {
                    return false;
                }
                CircleMemberProperties other = (CircleMemberProperties) o;
                if (this.displayName == null) {
                    if (other.displayName != null) {
                        return false;
                    }
                } else if (!this.displayName.equals(other.displayName)) {
                    return false;
                }
                if (this.firstName == null) {
                    if (other.firstName != null) {
                        return false;
                    }
                } else if (!this.firstName.equals(other.firstName)) {
                    return false;
                }
                if (this.firstNameSortKey == null) {
                    if (other.firstNameSortKey != null) {
                        return false;
                    }
                } else if (!this.firstNameSortKey.equals(other.firstNameSortKey)) {
                    return false;
                }
                if (this.lastNameSortKey == null) {
                    if (other.lastNameSortKey != null) {
                        return false;
                    }
                } else if (!this.lastNameSortKey.equals(other.lastNameSortKey)) {
                    return false;
                }
                if (this.photoUrl == null) {
                    if (other.photoUrl != null) {
                        return false;
                    }
                } else if (!this.photoUrl.equals(other.photoUrl)) {
                    return false;
                }
                if (this.focusPhotoUrl == null) {
                    if (other.focusPhotoUrl != null) {
                        return false;
                    }
                } else if (!this.focusPhotoUrl.equals(other.focusPhotoUrl)) {
                    return false;
                }
                if (Double.doubleToLongBits(this.interactionsRank) != Double.doubleToLongBits(other.interactionsRank)) {
                    return false;
                }
                if (this.interactionsRankSortKey == null) {
                    if (other.interactionsRankSortKey != null) {
                        return false;
                    }
                } else if (!this.interactionsRankSortKey.equals(other.interactionsRankSortKey)) {
                    return false;
                }
                if (this.lastUpdateTime == null) {
                    if (other.lastUpdateTime != null) {
                        return false;
                    }
                } else if (!this.lastUpdateTime.equals(other.lastUpdateTime)) {
                    return false;
                }
                if (this.contact != other.contact || this.isNameResolvedContact != other.isNameResolvedContact || this.esUser != other.esUser || this.inSameVisibilityGroup != other.inSameVisibilityGroup || this.profileType != other.profileType || this.inIncomingCircle != other.inIncomingCircle || this.verified != other.verified || this.inviter != other.inviter) {
                    return false;
                }
                if (this.location == null) {
                    if (other.location != null) {
                        return false;
                    }
                } else if (!this.location.equals(other.location)) {
                    return false;
                }
                if (this.school == null) {
                    if (other.school != null) {
                        return false;
                    }
                } else if (!this.school.equals(other.school)) {
                    return false;
                }
                if (this.company == null) {
                    if (other.company != null) {
                        return false;
                    }
                } else if (!this.company.equals(other.company)) {
                    return false;
                }
                if (this.role == null) {
                    if (other.role != null) {
                        return false;
                    }
                } else if (!this.role.equals(other.role)) {
                    return false;
                }
                if (this.occupation == null) {
                    if (other.occupation != null) {
                        return false;
                    }
                } else if (!this.occupation.equals(other.occupation)) {
                    return false;
                }
                if (this.tagLine == null) {
                    if (other.tagLine != null) {
                        return false;
                    }
                } else if (!this.tagLine.equals(other.tagLine)) {
                    return false;
                }
                if (this.gender != other.gender || !InternalNano.equals(this.contactId, other.contactId)) {
                    return false;
                }
                if (this.entityInfo == null) {
                    if (other.entityInfo != null) {
                        return false;
                    }
                } else if (!this.entityInfo.equals(other.entityInfo)) {
                    return false;
                }
                if (!InternalNano.equals(this.disallowedInteractions, other.disallowedInteractions)) {
                    return false;
                }
                if (this.bannerInfo == null) {
                    if (other.bannerInfo != null) {
                        return false;
                    }
                } else if (!this.bannerInfo.equals(other.bannerInfo)) {
                    return false;
                }
                if (!InternalNano.equals(this.email, other.email) || !InternalNano.equals(this.phone, other.phone) || !InternalNano.equals(this.address, other.address) || !InternalNano.equals(this.verifiedPhone, other.verifiedPhone)) {
                    return false;
                }
                if (this.suggestionId == null) {
                    if (other.suggestionId != null) {
                        return false;
                    }
                } else if (!this.suggestionId.equals(other.suggestionId)) {
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
                int result = ((((((((((((getClass().getName().hashCode() + 527) * 31) + (this.displayName == null ? 0 : this.displayName.hashCode())) * 31) + (this.firstName == null ? 0 : this.firstName.hashCode())) * 31) + (this.firstNameSortKey == null ? 0 : this.firstNameSortKey.hashCode())) * 31) + (this.lastNameSortKey == null ? 0 : this.lastNameSortKey.hashCode())) * 31) + (this.photoUrl == null ? 0 : this.photoUrl.hashCode())) * 31) + (this.focusPhotoUrl == null ? 0 : this.focusPhotoUrl.hashCode());
                long v = Double.doubleToLongBits(this.interactionsRank);
                int hashCode = ((((((result * 31) + ((int) ((v >>> 32) ^ v))) * 31) + (this.interactionsRankSortKey == null ? 0 : this.interactionsRankSortKey.hashCode())) * 31) + (this.lastUpdateTime == null ? 0 : this.lastUpdateTime.hashCode())) * 31;
                if (this.contact) {
                    i = 1231;
                } else {
                    i = 1237;
                }
                hashCode = (hashCode + i) * 31;
                if (this.isNameResolvedContact) {
                    i = 1231;
                } else {
                    i = 1237;
                }
                hashCode = (hashCode + i) * 31;
                if (this.esUser) {
                    i = 1231;
                } else {
                    i = 1237;
                }
                hashCode = (hashCode + i) * 31;
                if (this.inSameVisibilityGroup) {
                    i = 1231;
                } else {
                    i = 1237;
                }
                hashCode = (((hashCode + i) * 31) + this.profileType) * 31;
                if (this.inIncomingCircle) {
                    i = 1231;
                } else {
                    i = 1237;
                }
                hashCode = (hashCode + i) * 31;
                if (this.verified) {
                    i = 1231;
                } else {
                    i = 1237;
                }
                i = (hashCode + i) * 31;
                if (!this.inviter) {
                    i2 = 1237;
                }
                i = (((((((((((((((((((((((((((((((((i + i2) * 31) + (this.location == null ? 0 : this.location.hashCode())) * 31) + (this.school == null ? 0 : this.school.hashCode())) * 31) + (this.company == null ? 0 : this.company.hashCode())) * 31) + (this.role == null ? 0 : this.role.hashCode())) * 31) + (this.occupation == null ? 0 : this.occupation.hashCode())) * 31) + (this.tagLine == null ? 0 : this.tagLine.hashCode())) * 31) + this.gender) * 31) + InternalNano.hashCode(this.contactId)) * 31) + (this.entityInfo == null ? 0 : this.entityInfo.hashCode())) * 31) + InternalNano.hashCode(this.disallowedInteractions)) * 31) + (this.bannerInfo == null ? 0 : this.bannerInfo.hashCode())) * 31) + InternalNano.hashCode(this.email)) * 31) + InternalNano.hashCode(this.phone)) * 31) + InternalNano.hashCode(this.address)) * 31) + InternalNano.hashCode(this.verifiedPhone)) * 31) + (this.suggestionId == null ? 0 : this.suggestionId.hashCode())) * 31;
                if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                    i3 = this.unknownFieldData.hashCode();
                }
                return i + i3;
            }

            public void writeTo(CodedOutputByteBufferNano output) throws IOException {
                if (!this.displayName.equals(BuildConfig.VERSION_NAME)) {
                    output.writeString(1, this.displayName);
                }
                if (Double.doubleToLongBits(this.interactionsRank) != Double.doubleToLongBits(0.0d)) {
                    output.writeDouble(4, this.interactionsRank);
                }
                if (!this.lastUpdateTime.equals(BuildConfig.VERSION_NAME)) {
                    output.writeString(5, this.lastUpdateTime);
                }
                if (!this.firstNameSortKey.equals(BuildConfig.VERSION_NAME)) {
                    output.writeString(6, this.firstNameSortKey);
                }
                if (!this.lastNameSortKey.equals(BuildConfig.VERSION_NAME)) {
                    output.writeString(7, this.lastNameSortKey);
                }
                if (this.contact) {
                    output.writeBool(8, this.contact);
                }
                if (!this.photoUrl.equals(BuildConfig.VERSION_NAME)) {
                    output.writeString(9, this.photoUrl);
                }
                if (this.esUser) {
                    output.writeBool(11, this.esUser);
                }
                if (!this.location.equals(BuildConfig.VERSION_NAME)) {
                    output.writeString(12, this.location);
                }
                if (!this.school.equals(BuildConfig.VERSION_NAME)) {
                    output.writeString(13, this.school);
                }
                if (!this.company.equals(BuildConfig.VERSION_NAME)) {
                    output.writeString(14, this.company);
                }
                if (!this.occupation.equals(BuildConfig.VERSION_NAME)) {
                    output.writeString(15, this.occupation);
                }
                if (this.gender != 0) {
                    output.writeInt32(16, this.gender);
                }
                if (this.inIncomingCircle) {
                    output.writeBool(17, this.inIncomingCircle);
                }
                if (this.contactId != null && this.contactId.length > 0) {
                    for (String element : this.contactId) {
                        if (element != null) {
                            output.writeString(18, element);
                        }
                    }
                }
                if (this.entityInfo != null) {
                    output.writeMessage(19, this.entityInfo);
                }
                if (!this.interactionsRankSortKey.equals(BuildConfig.VERSION_NAME)) {
                    output.writeString(20, this.interactionsRankSortKey);
                }
                if (this.verified) {
                    output.writeBool(21, this.verified);
                }
                if (!this.tagLine.equals(BuildConfig.VERSION_NAME)) {
                    output.writeString(22, this.tagLine);
                }
                if (this.disallowedInteractions != null && this.disallowedInteractions.length > 0) {
                    for (int writeInt32 : this.disallowedInteractions) {
                        output.writeInt32(23, writeInt32);
                    }
                }
                if (this.bannerInfo != null) {
                    output.writeMessage(24, this.bannerInfo);
                }
                if (!this.focusPhotoUrl.equals(BuildConfig.VERSION_NAME)) {
                    output.writeString(25, this.focusPhotoUrl);
                }
                if (this.email != null && this.email.length > 0) {
                    for (Email element2 : this.email) {
                        if (element2 != null) {
                            output.writeMessage(26, element2);
                        }
                    }
                }
                if (this.phone != null && this.phone.length > 0) {
                    for (Phone element3 : this.phone) {
                        if (element3 != null) {
                            output.writeMessage(27, element3);
                        }
                    }
                }
                if (this.inviter) {
                    output.writeBool(28, this.inviter);
                }
                if (this.profileType != 0) {
                    output.writeInt32(29, this.profileType);
                }
                if (this.inSameVisibilityGroup) {
                    output.writeBool(30, this.inSameVisibilityGroup);
                }
                if (!this.firstName.equals(BuildConfig.VERSION_NAME)) {
                    output.writeString(31, this.firstName);
                }
                if (this.address != null && this.address.length > 0) {
                    for (Address element4 : this.address) {
                        if (element4 != null) {
                            output.writeMessage(32, element4);
                        }
                    }
                }
                if (this.verifiedPhone != null && this.verifiedPhone.length > 0) {
                    for (String element5 : this.verifiedPhone) {
                        if (element5 != null) {
                            output.writeString(34, element5);
                        }
                    }
                }
                if (!this.suggestionId.equals(BuildConfig.VERSION_NAME)) {
                    output.writeString(35, this.suggestionId);
                }
                if (this.isNameResolvedContact) {
                    output.writeBool(36, this.isNameResolvedContact);
                }
                if (!this.role.equals(BuildConfig.VERSION_NAME)) {
                    output.writeString(37, this.role);
                }
                super.writeTo(output);
            }

            protected int computeSerializedSize() {
                int dataCount;
                int dataSize;
                int size = super.computeSerializedSize();
                if (!this.displayName.equals(BuildConfig.VERSION_NAME)) {
                    size += CodedOutputByteBufferNano.computeStringSize(1, this.displayName);
                }
                if (Double.doubleToLongBits(this.interactionsRank) != Double.doubleToLongBits(0.0d)) {
                    size += CodedOutputByteBufferNano.computeDoubleSize(4, this.interactionsRank);
                }
                if (!this.lastUpdateTime.equals(BuildConfig.VERSION_NAME)) {
                    size += CodedOutputByteBufferNano.computeStringSize(5, this.lastUpdateTime);
                }
                if (!this.firstNameSortKey.equals(BuildConfig.VERSION_NAME)) {
                    size += CodedOutputByteBufferNano.computeStringSize(6, this.firstNameSortKey);
                }
                if (!this.lastNameSortKey.equals(BuildConfig.VERSION_NAME)) {
                    size += CodedOutputByteBufferNano.computeStringSize(7, this.lastNameSortKey);
                }
                if (this.contact) {
                    size += CodedOutputByteBufferNano.computeBoolSize(8, this.contact);
                }
                if (!this.photoUrl.equals(BuildConfig.VERSION_NAME)) {
                    size += CodedOutputByteBufferNano.computeStringSize(9, this.photoUrl);
                }
                if (this.esUser) {
                    size += CodedOutputByteBufferNano.computeBoolSize(11, this.esUser);
                }
                if (!this.location.equals(BuildConfig.VERSION_NAME)) {
                    size += CodedOutputByteBufferNano.computeStringSize(12, this.location);
                }
                if (!this.school.equals(BuildConfig.VERSION_NAME)) {
                    size += CodedOutputByteBufferNano.computeStringSize(13, this.school);
                }
                if (!this.company.equals(BuildConfig.VERSION_NAME)) {
                    size += CodedOutputByteBufferNano.computeStringSize(14, this.company);
                }
                if (!this.occupation.equals(BuildConfig.VERSION_NAME)) {
                    size += CodedOutputByteBufferNano.computeStringSize(15, this.occupation);
                }
                if (this.gender != 0) {
                    size += CodedOutputByteBufferNano.computeInt32Size(16, this.gender);
                }
                if (this.inIncomingCircle) {
                    size += CodedOutputByteBufferNano.computeBoolSize(17, this.inIncomingCircle);
                }
                if (this.contactId != null && this.contactId.length > 0) {
                    dataCount = 0;
                    dataSize = 0;
                    for (String element : this.contactId) {
                        if (element != null) {
                            dataCount++;
                            dataSize += CodedOutputByteBufferNano.computeStringSizeNoTag(element);
                        }
                    }
                    size = (size + dataSize) + (dataCount * 2);
                }
                if (this.entityInfo != null) {
                    size += CodedOutputByteBufferNano.computeMessageSize(19, this.entityInfo);
                }
                if (!this.interactionsRankSortKey.equals(BuildConfig.VERSION_NAME)) {
                    size += CodedOutputByteBufferNano.computeStringSize(20, this.interactionsRankSortKey);
                }
                if (this.verified) {
                    size += CodedOutputByteBufferNano.computeBoolSize(21, this.verified);
                }
                if (!this.tagLine.equals(BuildConfig.VERSION_NAME)) {
                    size += CodedOutputByteBufferNano.computeStringSize(22, this.tagLine);
                }
                if (this.disallowedInteractions != null && this.disallowedInteractions.length > 0) {
                    dataSize = 0;
                    for (int element2 : this.disallowedInteractions) {
                        dataSize += CodedOutputByteBufferNano.computeInt32SizeNoTag(element2);
                    }
                    size = (size + dataSize) + (this.disallowedInteractions.length * 2);
                }
                if (this.bannerInfo != null) {
                    size += CodedOutputByteBufferNano.computeMessageSize(24, this.bannerInfo);
                }
                if (!this.focusPhotoUrl.equals(BuildConfig.VERSION_NAME)) {
                    size += CodedOutputByteBufferNano.computeStringSize(25, this.focusPhotoUrl);
                }
                if (this.email != null && this.email.length > 0) {
                    for (Email element3 : this.email) {
                        if (element3 != null) {
                            size += CodedOutputByteBufferNano.computeMessageSize(26, element3);
                        }
                    }
                }
                if (this.phone != null && this.phone.length > 0) {
                    for (Phone element4 : this.phone) {
                        if (element4 != null) {
                            size += CodedOutputByteBufferNano.computeMessageSize(27, element4);
                        }
                    }
                }
                if (this.inviter) {
                    size += CodedOutputByteBufferNano.computeBoolSize(28, this.inviter);
                }
                if (this.profileType != 0) {
                    size += CodedOutputByteBufferNano.computeInt32Size(29, this.profileType);
                }
                if (this.inSameVisibilityGroup) {
                    size += CodedOutputByteBufferNano.computeBoolSize(30, this.inSameVisibilityGroup);
                }
                if (!this.firstName.equals(BuildConfig.VERSION_NAME)) {
                    size += CodedOutputByteBufferNano.computeStringSize(31, this.firstName);
                }
                if (this.address != null && this.address.length > 0) {
                    for (Address element5 : this.address) {
                        if (element5 != null) {
                            size += CodedOutputByteBufferNano.computeMessageSize(32, element5);
                        }
                    }
                }
                if (this.verifiedPhone != null && this.verifiedPhone.length > 0) {
                    dataCount = 0;
                    dataSize = 0;
                    for (String element6 : this.verifiedPhone) {
                        if (element6 != null) {
                            dataCount++;
                            dataSize += CodedOutputByteBufferNano.computeStringSizeNoTag(element6);
                        }
                    }
                    size = (size + dataSize) + (dataCount * 2);
                }
                if (!this.suggestionId.equals(BuildConfig.VERSION_NAME)) {
                    size += CodedOutputByteBufferNano.computeStringSize(35, this.suggestionId);
                }
                if (this.isNameResolvedContact) {
                    size += CodedOutputByteBufferNano.computeBoolSize(36, this.isNameResolvedContact);
                }
                if (this.role.equals(BuildConfig.VERSION_NAME)) {
                    return size;
                }
                return size + CodedOutputByteBufferNano.computeStringSize(37, this.role);
            }

            public CircleMemberProperties mergeFrom(CodedInputByteBufferNano input) throws IOException {
                while (true) {
                    int tag = input.readTag();
                    int value;
                    int arrayLength;
                    int i;
                    String[] newArray;
                    int[] newArray2;
                    switch (tag) {
                        case Action.UNKNOWN /*0*/:
                            break;
                        case Type.TAP_ABOUT /*10*/:
                            this.displayName = input.readString();
                            continue;
                        case LogSource.INSTORE_WALLET /*33*/:
                            this.interactionsRank = input.readDouble();
                            continue;
                        case LogSource.PHOTOS /*42*/:
                            this.lastUpdateTime = input.readString();
                            continue;
                        case LogSource.HANGOUT_LOG_REQUEST /*50*/:
                            this.firstNameSortKey = input.readString();
                            continue;
                        case LogSource.SLIDES /*58*/:
                            this.lastNameSortKey = input.readString();
                            continue;
                        case LogSource.KIDS_COMMUNICATOR /*64*/:
                            this.contact = input.readBool();
                            continue;
                        case LogSource.MOVIES /*74*/:
                            this.photoUrl = input.readString();
                            continue;
                        case LogSource.EMERGENCY_ASSIST /*88*/:
                            this.esUser = input.readBool();
                            continue;
                        case LogSource.TACHYON_LOG_REQUEST /*98*/:
                            this.location = input.readString();
                            continue;
                        case LogSource.ADSHIELD /*106*/:
                            this.school = input.readString();
                            continue;
                        case LogSource.TRANSOM /*114*/:
                            this.company = input.readString();
                            continue;
                        case LogSource.ANDROID_SNET_GCORE /*122*/:
                            this.occupation = input.readString();
                            continue;
                        case LogSource.KEEP /*128*/:
                            value = input.readInt32();
                            switch (value) {
                                case Action.UNKNOWN /*0*/:
                                case TimeSelection.Type.TEMPORARY /*1*/:
                                case TimeSelection.Type.INDEFINITELY /*2*/:
                                    this.gender = value;
                                    break;
                                default:
                                    continue;
                            }
                        case LogSource.SAMPLE_SHM /*136*/:
                            this.inIncomingCircle = input.readBool();
                            continue;
                        case 146:
                            arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 146);
                            if (this.contactId == null) {
                                i = 0;
                            } else {
                                i = this.contactId.length;
                            }
                            newArray = new String[(i + arrayLength)];
                            if (i != 0) {
                                System.arraycopy(this.contactId, 0, newArray, 0, i);
                            }
                            while (true) {
                                if (i < newArray.length - 1) {
                                    newArray[i] = input.readString();
                                    input.readTag();
                                    i++;
                                } else {
                                    newArray[i] = input.readString();
                                    this.contactId = newArray;
                                    continue;
                                }
                            }
                        case 154:
                            if (this.entityInfo == null) {
                                this.entityInfo = new EntityInfo();
                            }
                            input.readMessage(this.entityInfo);
                            continue;
                        case 162:
                            this.interactionsRankSortKey = input.readString();
                            continue;
                        case 168:
                            this.verified = input.readBool();
                            continue;
                        case 178:
                            this.tagLine = input.readString();
                            continue;
                        case 184:
                            int length = WireFormatNano.getRepeatedFieldArrayLength(input, 184);
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
                                if (this.disallowedInteractions == null) {
                                    i = 0;
                                } else {
                                    i = this.disallowedInteractions.length;
                                }
                                if (i == 0) {
                                    int length2 = validValues.length;
                                    if (validCount == r0) {
                                        this.disallowedInteractions = validValues;
                                        break;
                                    }
                                }
                                newArray2 = new int[(i + validCount)];
                                if (i != 0) {
                                    System.arraycopy(this.disallowedInteractions, 0, newArray2, 0, i);
                                }
                                System.arraycopy(validValues, 0, newArray2, i, validCount);
                                this.disallowedInteractions = newArray2;
                                break;
                            }
                            continue;
                        case 186:
                            int limit = input.pushLimit(input.readRawVarint32());
                            arrayLength = 0;
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
                                if (this.disallowedInteractions == null) {
                                    i = 0;
                                } else {
                                    i = this.disallowedInteractions.length;
                                }
                                newArray2 = new int[(i + arrayLength)];
                                if (i != 0) {
                                    System.arraycopy(this.disallowedInteractions, 0, newArray2, 0, i);
                                }
                                while (input.getBytesUntilLimit() > 0) {
                                    value = input.readInt32();
                                    switch (value) {
                                        case TimeSelection.Type.TEMPORARY /*1*/:
                                        case TimeSelection.Type.INDEFINITELY /*2*/:
                                            int i2 = i + 1;
                                            newArray2[i] = value;
                                            i = i2;
                                            break;
                                        default:
                                            break;
                                    }
                                }
                                this.disallowedInteractions = newArray2;
                            }
                            input.popLimit(limit);
                            continue;
                        case 194:
                            if (this.bannerInfo == null) {
                                this.bannerInfo = new HovercardBannerInfo();
                            }
                            input.readMessage(this.bannerInfo);
                            continue;
                        case 202:
                            this.focusPhotoUrl = input.readString();
                            continue;
                        case 210:
                            arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 210);
                            if (this.email == null) {
                                i = 0;
                            } else {
                                i = this.email.length;
                            }
                            Email[] newArray3 = new Email[(i + arrayLength)];
                            if (i != 0) {
                                System.arraycopy(this.email, 0, newArray3, 0, i);
                            }
                            while (true) {
                                if (i < newArray3.length - 1) {
                                    newArray3[i] = new Email();
                                    input.readMessage(newArray3[i]);
                                    input.readTag();
                                    i++;
                                } else {
                                    newArray3[i] = new Email();
                                    input.readMessage(newArray3[i]);
                                    this.email = newArray3;
                                    continue;
                                }
                            }
                        case 218:
                            arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 218);
                            if (this.phone == null) {
                                i = 0;
                            } else {
                                i = this.phone.length;
                            }
                            Phone[] newArray4 = new Phone[(i + arrayLength)];
                            if (i != 0) {
                                System.arraycopy(this.phone, 0, newArray4, 0, i);
                            }
                            while (true) {
                                if (i < newArray4.length - 1) {
                                    newArray4[i] = new Phone();
                                    input.readMessage(newArray4[i]);
                                    input.readTag();
                                    i++;
                                } else {
                                    newArray4[i] = new Phone();
                                    input.readMessage(newArray4[i]);
                                    this.phone = newArray4;
                                    continue;
                                }
                            }
                        case 224:
                            this.inviter = input.readBool();
                            continue;
                        case 232:
                            value = input.readInt32();
                            switch (value) {
                                case Action.UNKNOWN /*0*/:
                                case TimeSelection.Type.TEMPORARY /*1*/:
                                case TimeSelection.Type.INDEFINITELY /*2*/:
                                case TimeSelection.Type.CUSTOM /*3*/:
                                case OvenFreshResult.NO_ACCOUNTS /*4*/:
                                    this.profileType = value;
                                    break;
                                default:
                                    continue;
                            }
                        case 240:
                            this.inSameVisibilityGroup = input.readBool();
                            continue;
                        case 250:
                            this.firstName = input.readString();
                            continue;
                        case 258:
                            arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 258);
                            if (this.address == null) {
                                i = 0;
                            } else {
                                i = this.address.length;
                            }
                            Address[] newArray5 = new Address[(i + arrayLength)];
                            if (i != 0) {
                                System.arraycopy(this.address, 0, newArray5, 0, i);
                            }
                            while (true) {
                                if (i < newArray5.length - 1) {
                                    newArray5[i] = new Address();
                                    input.readMessage(newArray5[i]);
                                    input.readTag();
                                    i++;
                                } else {
                                    newArray5[i] = new Address();
                                    input.readMessage(newArray5[i]);
                                    this.address = newArray5;
                                    continue;
                                }
                            }
                        case 274:
                            arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 274);
                            if (this.verifiedPhone == null) {
                                i = 0;
                            } else {
                                i = this.verifiedPhone.length;
                            }
                            newArray = new String[(i + arrayLength)];
                            if (i != 0) {
                                System.arraycopy(this.verifiedPhone, 0, newArray, 0, i);
                            }
                            while (true) {
                                if (i < newArray.length - 1) {
                                    newArray[i] = input.readString();
                                    input.readTag();
                                    i++;
                                } else {
                                    newArray[i] = input.readString();
                                    this.verifiedPhone = newArray;
                                    continue;
                                }
                            }
                        case 282:
                            this.suggestionId = input.readString();
                            continue;
                        case 288:
                            this.isNameResolvedContact = input.readBool();
                            continue;
                        case 298:
                            this.role = input.readString();
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

            public static CircleMemberProperties parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
                return (CircleMemberProperties) MessageNano.mergeFrom(new CircleMemberProperties(), data);
            }

            public static CircleMemberProperties parseFrom(CodedInputByteBufferNano input) throws IOException {
                return new CircleMemberProperties().mergeFrom(input);
            }
        }

        public interface CircleMemberType {
            public static final int BANNED = 5;
            public static final int INVITED = 3;
            public static final int MEMBER = 2;
            public static final int OWNER = 1;
            public static final int PENDING = 4;
        }

        public static final class CirclePerson extends ExtendableMessageNano<CirclePerson> {
            private static volatile CirclePerson[] _emptyArray;
            public CircleMemberId[] joinKey;
            public CircleMemberId memberId;
            public CircleMemberProperties memberProperties;
            public Membership[] membership;
            public Contact primaryContact;

            public static CirclePerson[] emptyArray() {
                if (_emptyArray == null) {
                    synchronized (InternalNano.LAZY_INIT_LOCK) {
                        if (_emptyArray == null) {
                            _emptyArray = new CirclePerson[0];
                        }
                    }
                }
                return _emptyArray;
            }

            public CirclePerson() {
                clear();
            }

            public CirclePerson clear() {
                this.memberId = null;
                this.joinKey = CircleMemberId.emptyArray();
                this.memberProperties = null;
                this.membership = Membership.emptyArray();
                this.primaryContact = null;
                this.unknownFieldData = null;
                this.cachedSize = -1;
                return this;
            }

            public boolean equals(Object o) {
                if (o == this) {
                    return true;
                }
                if (!(o instanceof CirclePerson)) {
                    return false;
                }
                CirclePerson other = (CirclePerson) o;
                if (this.memberId == null) {
                    if (other.memberId != null) {
                        return false;
                    }
                } else if (!this.memberId.equals(other.memberId)) {
                    return false;
                }
                if (!InternalNano.equals(this.joinKey, other.joinKey)) {
                    return false;
                }
                if (this.memberProperties == null) {
                    if (other.memberProperties != null) {
                        return false;
                    }
                } else if (!this.memberProperties.equals(other.memberProperties)) {
                    return false;
                }
                if (!InternalNano.equals(this.membership, other.membership)) {
                    return false;
                }
                if (this.primaryContact == null) {
                    if (other.primaryContact != null) {
                        return false;
                    }
                } else if (!this.primaryContact.equals(other.primaryContact)) {
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
                int hashCode = (((((((((((getClass().getName().hashCode() + 527) * 31) + (this.memberId == null ? 0 : this.memberId.hashCode())) * 31) + InternalNano.hashCode(this.joinKey)) * 31) + (this.memberProperties == null ? 0 : this.memberProperties.hashCode())) * 31) + InternalNano.hashCode(this.membership)) * 31) + (this.primaryContact == null ? 0 : this.primaryContact.hashCode())) * 31;
                if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                    i = this.unknownFieldData.hashCode();
                }
                return hashCode + i;
            }

            public void writeTo(CodedOutputByteBufferNano output) throws IOException {
                if (this.memberId != null) {
                    output.writeMessage(1, this.memberId);
                }
                if (this.joinKey != null && this.joinKey.length > 0) {
                    for (CircleMemberId element : this.joinKey) {
                        if (element != null) {
                            output.writeMessage(2, element);
                        }
                    }
                }
                if (this.memberProperties != null) {
                    output.writeMessage(3, this.memberProperties);
                }
                if (this.membership != null && this.membership.length > 0) {
                    for (Membership element2 : this.membership) {
                        if (element2 != null) {
                            output.writeMessage(4, element2);
                        }
                    }
                }
                if (this.primaryContact != null) {
                    output.writeMessage(5, this.primaryContact);
                }
                super.writeTo(output);
            }

            protected int computeSerializedSize() {
                int size = super.computeSerializedSize();
                if (this.memberId != null) {
                    size += CodedOutputByteBufferNano.computeMessageSize(1, this.memberId);
                }
                if (this.joinKey != null && this.joinKey.length > 0) {
                    for (CircleMemberId element : this.joinKey) {
                        if (element != null) {
                            size += CodedOutputByteBufferNano.computeMessageSize(2, element);
                        }
                    }
                }
                if (this.memberProperties != null) {
                    size += CodedOutputByteBufferNano.computeMessageSize(3, this.memberProperties);
                }
                if (this.membership != null && this.membership.length > 0) {
                    for (Membership element2 : this.membership) {
                        if (element2 != null) {
                            size += CodedOutputByteBufferNano.computeMessageSize(4, element2);
                        }
                    }
                }
                if (this.primaryContact != null) {
                    return size + CodedOutputByteBufferNano.computeMessageSize(5, this.primaryContact);
                }
                return size;
            }

            public CirclePerson mergeFrom(CodedInputByteBufferNano input) throws IOException {
                while (true) {
                    int tag = input.readTag();
                    int arrayLength;
                    int i;
                    switch (tag) {
                        case Action.UNKNOWN /*0*/:
                            break;
                        case Type.TAP_ABOUT /*10*/:
                            if (this.memberId == null) {
                                this.memberId = new CircleMemberId();
                            }
                            input.readMessage(this.memberId);
                            continue;
                        case LogSource.GOOGLE_ANALYTICS /*18*/:
                            arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 18);
                            if (this.joinKey == null) {
                                i = 0;
                            } else {
                                i = this.joinKey.length;
                            }
                            CircleMemberId[] newArray = new CircleMemberId[(i + arrayLength)];
                            if (i != 0) {
                                System.arraycopy(this.joinKey, 0, newArray, 0, i);
                            }
                            while (i < newArray.length - 1) {
                                newArray[i] = new CircleMemberId();
                                input.readMessage(newArray[i]);
                                input.readTag();
                                i++;
                            }
                            newArray[i] = new CircleMemberId();
                            input.readMessage(newArray[i]);
                            this.joinKey = newArray;
                            continue;
                        case LogSource.ANDROID_CAMERA /*26*/:
                            if (this.memberProperties == null) {
                                this.memberProperties = new CircleMemberProperties();
                            }
                            input.readMessage(this.memberProperties);
                            continue;
                        case LogSource.NOVA /*34*/:
                            arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 34);
                            if (this.membership == null) {
                                i = 0;
                            } else {
                                i = this.membership.length;
                            }
                            Membership[] newArray2 = new Membership[(i + arrayLength)];
                            if (i != 0) {
                                System.arraycopy(this.membership, 0, newArray2, 0, i);
                            }
                            while (i < newArray2.length - 1) {
                                newArray2[i] = new Membership();
                                input.readMessage(newArray2[i]);
                                input.readTag();
                                i++;
                            }
                            newArray2[i] = new Membership();
                            input.readMessage(newArray2[i]);
                            this.membership = newArray2;
                            continue;
                        case LogSource.PHOTOS /*42*/:
                            if (this.primaryContact == null) {
                                this.primaryContact = new Contact();
                            }
                            input.readMessage(this.primaryContact);
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

            public static CirclePerson parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
                return (CirclePerson) MessageNano.mergeFrom(new CirclePerson(), data);
            }

            public static CirclePerson parseFrom(CodedInputByteBufferNano input) throws IOException {
                return new CirclePerson().mergeFrom(input);
            }
        }

        public static final class CircleProperties extends ExtendableMessageNano<CircleProperties> {
            private static volatile CircleProperties[] _emptyArray;
            public String backgroundImageUrl;
            public boolean canHaveOutsideMembers;
            public int category;
            public int circleMode;
            public int circleType;
            public String description;
            public String email;
            public boolean forFollowing;
            public boolean forSharing;
            public double interactionsRank;
            public String lastUpdateTime;
            public int memberCount;
            public int memberCountOutsideDomain;
            public String name;
            public String nameSortKey;
            public String photoUrl;
            public boolean selectedForChat;

            public static CircleProperties[] emptyArray() {
                if (_emptyArray == null) {
                    synchronized (InternalNano.LAZY_INIT_LOCK) {
                        if (_emptyArray == null) {
                            _emptyArray = new CircleProperties[0];
                        }
                    }
                }
                return _emptyArray;
            }

            public CircleProperties() {
                clear();
            }

            public CircleProperties clear() {
                this.circleType = 2;
                this.circleMode = 1;
                this.name = BuildConfig.VERSION_NAME;
                this.email = BuildConfig.VERSION_NAME;
                this.description = BuildConfig.VERSION_NAME;
                this.lastUpdateTime = BuildConfig.VERSION_NAME;
                this.interactionsRank = 0.0d;
                this.photoUrl = BuildConfig.VERSION_NAME;
                this.memberCount = 0;
                this.memberCountOutsideDomain = 0;
                this.canHaveOutsideMembers = false;
                this.selectedForChat = false;
                this.nameSortKey = BuildConfig.VERSION_NAME;
                this.category = 0;
                this.forSharing = true;
                this.forFollowing = true;
                this.backgroundImageUrl = BuildConfig.VERSION_NAME;
                this.unknownFieldData = null;
                this.cachedSize = -1;
                return this;
            }

            public boolean equals(Object o) {
                if (o == this) {
                    return true;
                }
                if (!(o instanceof CircleProperties)) {
                    return false;
                }
                CircleProperties other = (CircleProperties) o;
                if (this.circleType != other.circleType || this.circleMode != other.circleMode) {
                    return false;
                }
                if (this.name == null) {
                    if (other.name != null) {
                        return false;
                    }
                } else if (!this.name.equals(other.name)) {
                    return false;
                }
                if (this.email == null) {
                    if (other.email != null) {
                        return false;
                    }
                } else if (!this.email.equals(other.email)) {
                    return false;
                }
                if (this.description == null) {
                    if (other.description != null) {
                        return false;
                    }
                } else if (!this.description.equals(other.description)) {
                    return false;
                }
                if (this.lastUpdateTime == null) {
                    if (other.lastUpdateTime != null) {
                        return false;
                    }
                } else if (!this.lastUpdateTime.equals(other.lastUpdateTime)) {
                    return false;
                }
                if (Double.doubleToLongBits(this.interactionsRank) != Double.doubleToLongBits(other.interactionsRank)) {
                    return false;
                }
                if (this.photoUrl == null) {
                    if (other.photoUrl != null) {
                        return false;
                    }
                } else if (!this.photoUrl.equals(other.photoUrl)) {
                    return false;
                }
                if (this.memberCount != other.memberCount || this.memberCountOutsideDomain != other.memberCountOutsideDomain || this.canHaveOutsideMembers != other.canHaveOutsideMembers || this.selectedForChat != other.selectedForChat) {
                    return false;
                }
                if (this.nameSortKey == null) {
                    if (other.nameSortKey != null) {
                        return false;
                    }
                } else if (!this.nameSortKey.equals(other.nameSortKey)) {
                    return false;
                }
                if (this.category != other.category || this.forSharing != other.forSharing || this.forFollowing != other.forFollowing) {
                    return false;
                }
                if (this.backgroundImageUrl == null) {
                    if (other.backgroundImageUrl != null) {
                        return false;
                    }
                } else if (!this.backgroundImageUrl.equals(other.backgroundImageUrl)) {
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
                int result = ((((((((((((getClass().getName().hashCode() + 527) * 31) + this.circleType) * 31) + this.circleMode) * 31) + (this.name == null ? 0 : this.name.hashCode())) * 31) + (this.email == null ? 0 : this.email.hashCode())) * 31) + (this.description == null ? 0 : this.description.hashCode())) * 31) + (this.lastUpdateTime == null ? 0 : this.lastUpdateTime.hashCode());
                long v = Double.doubleToLongBits(this.interactionsRank);
                int hashCode = ((((((((result * 31) + ((int) ((v >>> 32) ^ v))) * 31) + (this.photoUrl == null ? 0 : this.photoUrl.hashCode())) * 31) + this.memberCount) * 31) + this.memberCountOutsideDomain) * 31;
                if (this.canHaveOutsideMembers) {
                    i = 1231;
                } else {
                    i = 1237;
                }
                hashCode = (hashCode + i) * 31;
                if (this.selectedForChat) {
                    i = 1231;
                } else {
                    i = 1237;
                }
                hashCode = (((((hashCode + i) * 31) + (this.nameSortKey == null ? 0 : this.nameSortKey.hashCode())) * 31) + this.category) * 31;
                if (this.forSharing) {
                    i = 1231;
                } else {
                    i = 1237;
                }
                i = (hashCode + i) * 31;
                if (!this.forFollowing) {
                    i2 = 1237;
                }
                i = (((i + i2) * 31) + (this.backgroundImageUrl == null ? 0 : this.backgroundImageUrl.hashCode())) * 31;
                if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                    i3 = this.unknownFieldData.hashCode();
                }
                return i + i3;
            }

            public void writeTo(CodedOutputByteBufferNano output) throws IOException {
                if (!this.name.equals(BuildConfig.VERSION_NAME)) {
                    output.writeString(1, this.name);
                }
                if (!this.email.equals(BuildConfig.VERSION_NAME)) {
                    output.writeString(2, this.email);
                }
                if (!this.description.equals(BuildConfig.VERSION_NAME)) {
                    output.writeString(3, this.description);
                }
                if (!this.lastUpdateTime.equals(BuildConfig.VERSION_NAME)) {
                    output.writeString(4, this.lastUpdateTime);
                }
                if (Double.doubleToLongBits(this.interactionsRank) != Double.doubleToLongBits(0.0d)) {
                    output.writeDouble(5, this.interactionsRank);
                }
                if (!this.photoUrl.equals(BuildConfig.VERSION_NAME)) {
                    output.writeString(6, this.photoUrl);
                }
                if (this.memberCount != 0) {
                    output.writeInt32(7, this.memberCount);
                }
                if (this.memberCountOutsideDomain != 0) {
                    output.writeInt32(8, this.memberCountOutsideDomain);
                }
                if (this.canHaveOutsideMembers) {
                    output.writeBool(9, this.canHaveOutsideMembers);
                }
                if (this.circleType != 2) {
                    output.writeInt32(10, this.circleType);
                }
                if (this.circleMode != 1) {
                    output.writeInt32(11, this.circleMode);
                }
                if (this.selectedForChat) {
                    output.writeBool(12, this.selectedForChat);
                }
                if (!this.nameSortKey.equals(BuildConfig.VERSION_NAME)) {
                    output.writeString(13, this.nameSortKey);
                }
                if (this.category != 0) {
                    output.writeInt32(14, this.category);
                }
                if (!this.forSharing) {
                    output.writeBool(15, this.forSharing);
                }
                if (!this.forFollowing) {
                    output.writeBool(16, this.forFollowing);
                }
                if (!this.backgroundImageUrl.equals(BuildConfig.VERSION_NAME)) {
                    output.writeString(17, this.backgroundImageUrl);
                }
                super.writeTo(output);
            }

            protected int computeSerializedSize() {
                int size = super.computeSerializedSize();
                if (!this.name.equals(BuildConfig.VERSION_NAME)) {
                    size += CodedOutputByteBufferNano.computeStringSize(1, this.name);
                }
                if (!this.email.equals(BuildConfig.VERSION_NAME)) {
                    size += CodedOutputByteBufferNano.computeStringSize(2, this.email);
                }
                if (!this.description.equals(BuildConfig.VERSION_NAME)) {
                    size += CodedOutputByteBufferNano.computeStringSize(3, this.description);
                }
                if (!this.lastUpdateTime.equals(BuildConfig.VERSION_NAME)) {
                    size += CodedOutputByteBufferNano.computeStringSize(4, this.lastUpdateTime);
                }
                if (Double.doubleToLongBits(this.interactionsRank) != Double.doubleToLongBits(0.0d)) {
                    size += CodedOutputByteBufferNano.computeDoubleSize(5, this.interactionsRank);
                }
                if (!this.photoUrl.equals(BuildConfig.VERSION_NAME)) {
                    size += CodedOutputByteBufferNano.computeStringSize(6, this.photoUrl);
                }
                if (this.memberCount != 0) {
                    size += CodedOutputByteBufferNano.computeInt32Size(7, this.memberCount);
                }
                if (this.memberCountOutsideDomain != 0) {
                    size += CodedOutputByteBufferNano.computeInt32Size(8, this.memberCountOutsideDomain);
                }
                if (this.canHaveOutsideMembers) {
                    size += CodedOutputByteBufferNano.computeBoolSize(9, this.canHaveOutsideMembers);
                }
                if (this.circleType != 2) {
                    size += CodedOutputByteBufferNano.computeInt32Size(10, this.circleType);
                }
                if (this.circleMode != 1) {
                    size += CodedOutputByteBufferNano.computeInt32Size(11, this.circleMode);
                }
                if (this.selectedForChat) {
                    size += CodedOutputByteBufferNano.computeBoolSize(12, this.selectedForChat);
                }
                if (!this.nameSortKey.equals(BuildConfig.VERSION_NAME)) {
                    size += CodedOutputByteBufferNano.computeStringSize(13, this.nameSortKey);
                }
                if (this.category != 0) {
                    size += CodedOutputByteBufferNano.computeInt32Size(14, this.category);
                }
                if (!this.forSharing) {
                    size += CodedOutputByteBufferNano.computeBoolSize(15, this.forSharing);
                }
                if (!this.forFollowing) {
                    size += CodedOutputByteBufferNano.computeBoolSize(16, this.forFollowing);
                }
                if (this.backgroundImageUrl.equals(BuildConfig.VERSION_NAME)) {
                    return size;
                }
                return size + CodedOutputByteBufferNano.computeStringSize(17, this.backgroundImageUrl);
            }

            public CircleProperties mergeFrom(CodedInputByteBufferNano input) throws IOException {
                while (true) {
                    int tag = input.readTag();
                    switch (tag) {
                        case Action.UNKNOWN /*0*/:
                            break;
                        case Type.TAP_ABOUT /*10*/:
                            this.name = input.readString();
                            continue;
                        case LogSource.GOOGLE_ANALYTICS /*18*/:
                            this.email = input.readString();
                            continue;
                        case LogSource.ANDROID_CAMERA /*26*/:
                            this.description = input.readString();
                            continue;
                        case LogSource.NOVA /*34*/:
                            this.lastUpdateTime = input.readString();
                            continue;
                        case LogSource.SOCIAL_AFFINITY /*41*/:
                            this.interactionsRank = input.readDouble();
                            continue;
                        case LogSource.HANGOUT_LOG_REQUEST /*50*/:
                            this.photoUrl = input.readString();
                            continue;
                        case LogSource.DOCS /*56*/:
                            this.memberCount = input.readInt32();
                            continue;
                        case LogSource.KIDS_COMMUNICATOR /*64*/:
                            this.memberCountOutsideDomain = input.readInt32();
                            continue;
                        case LogSource.JUSTSPEAK /*72*/:
                            this.canHaveOutsideMembers = input.readBool();
                            continue;
                        case LogSource.CRONET_ANDROID_GSA /*80*/:
                            int value = input.readInt32();
                            switch (value) {
                                case TimeSelection.Type.TEMPORARY /*1*/:
                                case TimeSelection.Type.INDEFINITELY /*2*/:
                                case TimeSelection.Type.CUSTOM /*3*/:
                                    this.circleType = value;
                                    break;
                                default:
                                    continue;
                            }
                        case LogSource.EMERGENCY_ASSIST /*88*/:
                            this.circleMode = input.readInt32();
                            continue;
                        case LogSource.GCM_COUNTERS /*96*/:
                            this.selectedForChat = input.readBool();
                            continue;
                        case LogSource.ADSHIELD /*106*/:
                            this.nameSortKey = input.readString();
                            continue;
                        case LogSource.ENDER /*112*/:
                            this.category = input.readInt32();
                            continue;
                        case LogSource.FLYDROID /*120*/:
                            this.forSharing = input.readBool();
                            continue;
                        case LogSource.KEEP /*128*/:
                            this.forFollowing = input.readBool();
                            continue;
                        case LogSource.PANCETTA_MOBILE_HOST /*138*/:
                            this.backgroundImageUrl = input.readString();
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

            public static CircleProperties parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
                return (CircleProperties) MessageNano.mergeFrom(new CircleProperties(), data);
            }

            public static CircleProperties parseFrom(CodedInputByteBufferNano input) throws IOException {
                return new CircleProperties().mergeFrom(input);
            }
        }

        public interface CircleType {
            public static final int PERSONAL = 2;
            public static final int SHARED = 1;
            public static final int UNVERIFIED_PUBLIC = 3;
        }

        public static final class Contact extends ExtendableMessageNano<Contact> {
            private static volatile Contact[] _emptyArray;
            public ContactGroupMembership[] contactGroup;
            public String id;

            public static Contact[] emptyArray() {
                if (_emptyArray == null) {
                    synchronized (InternalNano.LAZY_INIT_LOCK) {
                        if (_emptyArray == null) {
                            _emptyArray = new Contact[0];
                        }
                    }
                }
                return _emptyArray;
            }

            public Contact() {
                clear();
            }

            public Contact clear() {
                this.id = BuildConfig.VERSION_NAME;
                this.contactGroup = ContactGroupMembership.emptyArray();
                this.unknownFieldData = null;
                this.cachedSize = -1;
                return this;
            }

            public boolean equals(Object o) {
                if (o == this) {
                    return true;
                }
                if (!(o instanceof Contact)) {
                    return false;
                }
                Contact other = (Contact) o;
                if (this.id == null) {
                    if (other.id != null) {
                        return false;
                    }
                } else if (!this.id.equals(other.id)) {
                    return false;
                }
                if (!InternalNano.equals(this.contactGroup, other.contactGroup)) {
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
                int hashCode = (((((getClass().getName().hashCode() + 527) * 31) + (this.id == null ? 0 : this.id.hashCode())) * 31) + InternalNano.hashCode(this.contactGroup)) * 31;
                if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                    i = this.unknownFieldData.hashCode();
                }
                return hashCode + i;
            }

            public void writeTo(CodedOutputByteBufferNano output) throws IOException {
                if (!this.id.equals(BuildConfig.VERSION_NAME)) {
                    output.writeString(1, this.id);
                }
                if (this.contactGroup != null && this.contactGroup.length > 0) {
                    for (ContactGroupMembership element : this.contactGroup) {
                        if (element != null) {
                            output.writeMessage(2, element);
                        }
                    }
                }
                super.writeTo(output);
            }

            protected int computeSerializedSize() {
                int size = super.computeSerializedSize();
                if (!this.id.equals(BuildConfig.VERSION_NAME)) {
                    size += CodedOutputByteBufferNano.computeStringSize(1, this.id);
                }
                if (this.contactGroup != null && this.contactGroup.length > 0) {
                    for (ContactGroupMembership element : this.contactGroup) {
                        if (element != null) {
                            size += CodedOutputByteBufferNano.computeMessageSize(2, element);
                        }
                    }
                }
                return size;
            }

            public Contact mergeFrom(CodedInputByteBufferNano input) throws IOException {
                while (true) {
                    int tag = input.readTag();
                    switch (tag) {
                        case Action.UNKNOWN /*0*/:
                            break;
                        case Type.TAP_ABOUT /*10*/:
                            this.id = input.readString();
                            continue;
                        case LogSource.GOOGLE_ANALYTICS /*18*/:
                            int i;
                            int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 18);
                            if (this.contactGroup == null) {
                                i = 0;
                            } else {
                                i = this.contactGroup.length;
                            }
                            ContactGroupMembership[] newArray = new ContactGroupMembership[(i + arrayLength)];
                            if (i != 0) {
                                System.arraycopy(this.contactGroup, 0, newArray, 0, i);
                            }
                            while (i < newArray.length - 1) {
                                newArray[i] = new ContactGroupMembership();
                                input.readMessage(newArray[i]);
                                input.readTag();
                                i++;
                            }
                            newArray[i] = new ContactGroupMembership();
                            input.readMessage(newArray[i]);
                            this.contactGroup = newArray;
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

            public static Contact parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
                return (Contact) MessageNano.mergeFrom(new Contact(), data);
            }

            public static Contact parseFrom(CodedInputByteBufferNano input) throws IOException {
                return new Contact().mergeFrom(input);
            }
        }

        public static final class ContactGroupMembership extends ExtendableMessageNano<ContactGroupMembership> {
            private static volatile ContactGroupMembership[] _emptyArray;
            public String groupId;

            public static ContactGroupMembership[] emptyArray() {
                if (_emptyArray == null) {
                    synchronized (InternalNano.LAZY_INIT_LOCK) {
                        if (_emptyArray == null) {
                            _emptyArray = new ContactGroupMembership[0];
                        }
                    }
                }
                return _emptyArray;
            }

            public ContactGroupMembership() {
                clear();
            }

            public ContactGroupMembership clear() {
                this.groupId = BuildConfig.VERSION_NAME;
                this.unknownFieldData = null;
                this.cachedSize = -1;
                return this;
            }

            public boolean equals(Object o) {
                if (o == this) {
                    return true;
                }
                if (!(o instanceof ContactGroupMembership)) {
                    return false;
                }
                ContactGroupMembership other = (ContactGroupMembership) o;
                if (this.groupId == null) {
                    if (other.groupId != null) {
                        return false;
                    }
                } else if (!this.groupId.equals(other.groupId)) {
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
                int hashCode = (((getClass().getName().hashCode() + 527) * 31) + (this.groupId == null ? 0 : this.groupId.hashCode())) * 31;
                if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                    i = this.unknownFieldData.hashCode();
                }
                return hashCode + i;
            }

            public void writeTo(CodedOutputByteBufferNano output) throws IOException {
                if (!this.groupId.equals(BuildConfig.VERSION_NAME)) {
                    output.writeString(1, this.groupId);
                }
                super.writeTo(output);
            }

            protected int computeSerializedSize() {
                int size = super.computeSerializedSize();
                if (this.groupId.equals(BuildConfig.VERSION_NAME)) {
                    return size;
                }
                return size + CodedOutputByteBufferNano.computeStringSize(1, this.groupId);
            }

            public ContactGroupMembership mergeFrom(CodedInputByteBufferNano input) throws IOException {
                while (true) {
                    int tag = input.readTag();
                    switch (tag) {
                        case Action.UNKNOWN /*0*/:
                            break;
                        case Type.TAP_ABOUT /*10*/:
                            this.groupId = input.readString();
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

            public static ContactGroupMembership parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
                return (ContactGroupMembership) MessageNano.mergeFrom(new ContactGroupMembership(), data);
            }

            public static ContactGroupMembership parseFrom(CodedInputByteBufferNano input) throws IOException {
                return new ContactGroupMembership().mergeFrom(input);
            }
        }

        public static final class ContinuationToken extends ExtendableMessageNano<ContinuationToken> {
            private static volatile ContinuationToken[] _emptyArray;
            public String focusIncomingEdgesToken;
            public String gggStartToken;
            public int personListStartIndex;

            public static ContinuationToken[] emptyArray() {
                if (_emptyArray == null) {
                    synchronized (InternalNano.LAZY_INIT_LOCK) {
                        if (_emptyArray == null) {
                            _emptyArray = new ContinuationToken[0];
                        }
                    }
                }
                return _emptyArray;
            }

            public ContinuationToken() {
                clear();
            }

            public ContinuationToken clear() {
                this.personListStartIndex = 0;
                this.gggStartToken = BuildConfig.VERSION_NAME;
                this.focusIncomingEdgesToken = BuildConfig.VERSION_NAME;
                this.unknownFieldData = null;
                this.cachedSize = -1;
                return this;
            }

            public boolean equals(Object o) {
                if (o == this) {
                    return true;
                }
                if (!(o instanceof ContinuationToken)) {
                    return false;
                }
                ContinuationToken other = (ContinuationToken) o;
                if (this.personListStartIndex != other.personListStartIndex) {
                    return false;
                }
                if (this.gggStartToken == null) {
                    if (other.gggStartToken != null) {
                        return false;
                    }
                } else if (!this.gggStartToken.equals(other.gggStartToken)) {
                    return false;
                }
                if (this.focusIncomingEdgesToken == null) {
                    if (other.focusIncomingEdgesToken != null) {
                        return false;
                    }
                } else if (!this.focusIncomingEdgesToken.equals(other.focusIncomingEdgesToken)) {
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
                int hashCode = (((((((getClass().getName().hashCode() + 527) * 31) + this.personListStartIndex) * 31) + (this.gggStartToken == null ? 0 : this.gggStartToken.hashCode())) * 31) + (this.focusIncomingEdgesToken == null ? 0 : this.focusIncomingEdgesToken.hashCode())) * 31;
                if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                    i = this.unknownFieldData.hashCode();
                }
                return hashCode + i;
            }

            public void writeTo(CodedOutputByteBufferNano output) throws IOException {
                if (this.personListStartIndex != 0) {
                    output.writeInt32(1, this.personListStartIndex);
                }
                if (!this.gggStartToken.equals(BuildConfig.VERSION_NAME)) {
                    output.writeString(2, this.gggStartToken);
                }
                if (!this.focusIncomingEdgesToken.equals(BuildConfig.VERSION_NAME)) {
                    output.writeString(3, this.focusIncomingEdgesToken);
                }
                super.writeTo(output);
            }

            protected int computeSerializedSize() {
                int size = super.computeSerializedSize();
                if (this.personListStartIndex != 0) {
                    size += CodedOutputByteBufferNano.computeInt32Size(1, this.personListStartIndex);
                }
                if (!this.gggStartToken.equals(BuildConfig.VERSION_NAME)) {
                    size += CodedOutputByteBufferNano.computeStringSize(2, this.gggStartToken);
                }
                if (this.focusIncomingEdgesToken.equals(BuildConfig.VERSION_NAME)) {
                    return size;
                }
                return size + CodedOutputByteBufferNano.computeStringSize(3, this.focusIncomingEdgesToken);
            }

            public ContinuationToken mergeFrom(CodedInputByteBufferNano input) throws IOException {
                while (true) {
                    int tag = input.readTag();
                    switch (tag) {
                        case Action.UNKNOWN /*0*/:
                            break;
                        case Type.TAP_GET_LINK /*8*/:
                            this.personListStartIndex = input.readInt32();
                            continue;
                        case LogSource.GOOGLE_ANALYTICS /*18*/:
                            this.gggStartToken = input.readString();
                            continue;
                        case LogSource.ANDROID_CAMERA /*26*/:
                            this.focusIncomingEdgesToken = input.readString();
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

            public static ContinuationToken parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
                return (ContinuationToken) MessageNano.mergeFrom(new ContinuationToken(), data);
            }

            public static ContinuationToken parseFrom(CodedInputByteBufferNano input) throws IOException {
                return new ContinuationToken().mergeFrom(input);
            }
        }

        public static final class Email extends ExtendableMessageNano<Email> {
            private static volatile Email[] _emptyArray;
            public String customTag;
            public boolean primary;
            public int standardTag;
            public String value;

            public static Email[] emptyArray() {
                if (_emptyArray == null) {
                    synchronized (InternalNano.LAZY_INIT_LOCK) {
                        if (_emptyArray == null) {
                            _emptyArray = new Email[0];
                        }
                    }
                }
                return _emptyArray;
            }

            public Email() {
                clear();
            }

            public Email clear() {
                this.standardTag = 1;
                this.customTag = BuildConfig.VERSION_NAME;
                this.value = BuildConfig.VERSION_NAME;
                this.primary = false;
                this.unknownFieldData = null;
                this.cachedSize = -1;
                return this;
            }

            public boolean equals(Object o) {
                if (o == this) {
                    return true;
                }
                if (!(o instanceof Email)) {
                    return false;
                }
                Email other = (Email) o;
                if (this.standardTag != other.standardTag) {
                    return false;
                }
                if (this.customTag == null) {
                    if (other.customTag != null) {
                        return false;
                    }
                } else if (!this.customTag.equals(other.customTag)) {
                    return false;
                }
                if (this.value == null) {
                    if (other.value != null) {
                        return false;
                    }
                } else if (!this.value.equals(other.value)) {
                    return false;
                }
                if (this.primary != other.primary) {
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
                int hashCode = (((((((((getClass().getName().hashCode() + 527) * 31) + this.standardTag) * 31) + (this.customTag == null ? 0 : this.customTag.hashCode())) * 31) + (this.value == null ? 0 : this.value.hashCode())) * 31) + (this.primary ? 1231 : 1237)) * 31;
                if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                    i = this.unknownFieldData.hashCode();
                }
                return hashCode + i;
            }

            public void writeTo(CodedOutputByteBufferNano output) throws IOException {
                if (this.standardTag != 1) {
                    output.writeInt32(1, this.standardTag);
                }
                if (!this.customTag.equals(BuildConfig.VERSION_NAME)) {
                    output.writeString(2, this.customTag);
                }
                output.writeString(3, this.value);
                if (this.primary) {
                    output.writeBool(4, this.primary);
                }
                super.writeTo(output);
            }

            protected int computeSerializedSize() {
                int size = super.computeSerializedSize();
                if (this.standardTag != 1) {
                    size += CodedOutputByteBufferNano.computeInt32Size(1, this.standardTag);
                }
                if (!this.customTag.equals(BuildConfig.VERSION_NAME)) {
                    size += CodedOutputByteBufferNano.computeStringSize(2, this.customTag);
                }
                size += CodedOutputByteBufferNano.computeStringSize(3, this.value);
                if (this.primary) {
                    return size + CodedOutputByteBufferNano.computeBoolSize(4, this.primary);
                }
                return size;
            }

            public Email mergeFrom(CodedInputByteBufferNano input) throws IOException {
                while (true) {
                    int tag = input.readTag();
                    switch (tag) {
                        case Action.UNKNOWN /*0*/:
                            break;
                        case Type.TAP_GET_LINK /*8*/:
                            this.standardTag = input.readInt32();
                            continue;
                        case LogSource.GOOGLE_ANALYTICS /*18*/:
                            this.customTag = input.readString();
                            continue;
                        case LogSource.ANDROID_CAMERA /*26*/:
                            this.value = input.readString();
                            continue;
                        case LogSource.SOCIAL /*32*/:
                            this.primary = input.readBool();
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

            public static Email parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
                return (Email) MessageNano.mergeFrom(new Email(), data);
            }

            public static Email parseFrom(CodedInputByteBufferNano input) throws IOException {
                return new Email().mergeFrom(input);
            }
        }

        public interface GoogleProfile {
            public static final int EMERALD_SEA = 2;
            public static final int ENTITY = 3;
            public static final int ES_USER = 4;
            public static final int NONE = 0;
            public static final int OLD = 1;
            public static final int PUBLIC_NAME = 2;
        }

        public static final class HovercardBannerInfo extends ExtendableMessageNano<HovercardBannerInfo> {
            private static volatile HovercardBannerInfo[] _emptyArray;
            public String fullBleedPhotoUrl;
            public Image origFullBleedImage;
            public ScrapbookInfo scrapbookInfo;
            public String[] scrapbookPhotoUrl;

            public static final class Image extends ExtendableMessageNano<Image> {
                private static volatile Image[] _emptyArray;
                public int height;
                public String url;
                public int width;

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
                    this.url = BuildConfig.VERSION_NAME;
                    this.width = 0;
                    this.height = 0;
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
                    if (this.url == null) {
                        if (other.url != null) {
                            return false;
                        }
                    } else if (!this.url.equals(other.url)) {
                        return false;
                    }
                    if (this.width != other.width || this.height != other.height) {
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
                    int hashCode = (((((((getClass().getName().hashCode() + 527) * 31) + (this.url == null ? 0 : this.url.hashCode())) * 31) + this.width) * 31) + this.height) * 31;
                    if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                        i = this.unknownFieldData.hashCode();
                    }
                    return hashCode + i;
                }

                public void writeTo(CodedOutputByteBufferNano output) throws IOException {
                    output.writeString(1, this.url);
                    if (this.width != 0) {
                        output.writeInt32(2, this.width);
                    }
                    if (this.height != 0) {
                        output.writeInt32(3, this.height);
                    }
                    super.writeTo(output);
                }

                protected int computeSerializedSize() {
                    int size = super.computeSerializedSize() + CodedOutputByteBufferNano.computeStringSize(1, this.url);
                    if (this.width != 0) {
                        size += CodedOutputByteBufferNano.computeInt32Size(2, this.width);
                    }
                    if (this.height != 0) {
                        return size + CodedOutputByteBufferNano.computeInt32Size(3, this.height);
                    }
                    return size;
                }

                public Image mergeFrom(CodedInputByteBufferNano input) throws IOException {
                    while (true) {
                        int tag = input.readTag();
                        switch (tag) {
                            case Action.UNKNOWN /*0*/:
                                break;
                            case Type.TAP_ABOUT /*10*/:
                                this.url = input.readString();
                                continue;
                            case LogSource.GMS_CORE_PEOPLE /*16*/:
                                this.width = input.readInt32();
                                continue;
                            case LogSource.LB_C /*24*/:
                                this.height = input.readInt32();
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

            public static HovercardBannerInfo[] emptyArray() {
                if (_emptyArray == null) {
                    synchronized (InternalNano.LAZY_INIT_LOCK) {
                        if (_emptyArray == null) {
                            _emptyArray = new HovercardBannerInfo[0];
                        }
                    }
                }
                return _emptyArray;
            }

            public HovercardBannerInfo() {
                clear();
            }

            public HovercardBannerInfo clear() {
                this.scrapbookInfo = null;
                this.fullBleedPhotoUrl = BuildConfig.VERSION_NAME;
                this.scrapbookPhotoUrl = WireFormatNano.EMPTY_STRING_ARRAY;
                this.origFullBleedImage = null;
                this.unknownFieldData = null;
                this.cachedSize = -1;
                return this;
            }

            public boolean equals(Object o) {
                if (o == this) {
                    return true;
                }
                if (!(o instanceof HovercardBannerInfo)) {
                    return false;
                }
                HovercardBannerInfo other = (HovercardBannerInfo) o;
                if (this.scrapbookInfo == null) {
                    if (other.scrapbookInfo != null) {
                        return false;
                    }
                } else if (!this.scrapbookInfo.equals(other.scrapbookInfo)) {
                    return false;
                }
                if (this.fullBleedPhotoUrl == null) {
                    if (other.fullBleedPhotoUrl != null) {
                        return false;
                    }
                } else if (!this.fullBleedPhotoUrl.equals(other.fullBleedPhotoUrl)) {
                    return false;
                }
                if (!InternalNano.equals(this.scrapbookPhotoUrl, other.scrapbookPhotoUrl)) {
                    return false;
                }
                if (this.origFullBleedImage == null) {
                    if (other.origFullBleedImage != null) {
                        return false;
                    }
                } else if (!this.origFullBleedImage.equals(other.origFullBleedImage)) {
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
                int hashCode = (((((((((getClass().getName().hashCode() + 527) * 31) + (this.scrapbookInfo == null ? 0 : this.scrapbookInfo.hashCode())) * 31) + (this.fullBleedPhotoUrl == null ? 0 : this.fullBleedPhotoUrl.hashCode())) * 31) + InternalNano.hashCode(this.scrapbookPhotoUrl)) * 31) + (this.origFullBleedImage == null ? 0 : this.origFullBleedImage.hashCode())) * 31;
                if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                    i = this.unknownFieldData.hashCode();
                }
                return hashCode + i;
            }

            public void writeTo(CodedOutputByteBufferNano output) throws IOException {
                if (this.scrapbookInfo != null) {
                    output.writeMessage(1, this.scrapbookInfo);
                }
                if (!this.fullBleedPhotoUrl.equals(BuildConfig.VERSION_NAME)) {
                    output.writeString(2, this.fullBleedPhotoUrl);
                }
                if (this.scrapbookPhotoUrl != null && this.scrapbookPhotoUrl.length > 0) {
                    for (String element : this.scrapbookPhotoUrl) {
                        if (element != null) {
                            output.writeString(3, element);
                        }
                    }
                }
                if (this.origFullBleedImage != null) {
                    output.writeMessage(4, this.origFullBleedImage);
                }
                super.writeTo(output);
            }

            protected int computeSerializedSize() {
                int size = super.computeSerializedSize();
                if (this.scrapbookInfo != null) {
                    size += CodedOutputByteBufferNano.computeMessageSize(1, this.scrapbookInfo);
                }
                if (!this.fullBleedPhotoUrl.equals(BuildConfig.VERSION_NAME)) {
                    size += CodedOutputByteBufferNano.computeStringSize(2, this.fullBleedPhotoUrl);
                }
                if (this.scrapbookPhotoUrl != null && this.scrapbookPhotoUrl.length > 0) {
                    int dataCount = 0;
                    int dataSize = 0;
                    for (String element : this.scrapbookPhotoUrl) {
                        if (element != null) {
                            dataCount++;
                            dataSize += CodedOutputByteBufferNano.computeStringSizeNoTag(element);
                        }
                    }
                    size = (size + dataSize) + (dataCount * 1);
                }
                if (this.origFullBleedImage != null) {
                    return size + CodedOutputByteBufferNano.computeMessageSize(4, this.origFullBleedImage);
                }
                return size;
            }

            public HovercardBannerInfo mergeFrom(CodedInputByteBufferNano input) throws IOException {
                while (true) {
                    int tag = input.readTag();
                    switch (tag) {
                        case Action.UNKNOWN /*0*/:
                            break;
                        case Type.TAP_ABOUT /*10*/:
                            if (this.scrapbookInfo == null) {
                                this.scrapbookInfo = new ScrapbookInfo();
                            }
                            input.readMessage(this.scrapbookInfo);
                            continue;
                        case LogSource.GOOGLE_ANALYTICS /*18*/:
                            this.fullBleedPhotoUrl = input.readString();
                            continue;
                        case LogSource.ANDROID_CAMERA /*26*/:
                            int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 26);
                            int i = this.scrapbookPhotoUrl == null ? 0 : this.scrapbookPhotoUrl.length;
                            String[] newArray = new String[(i + arrayLength)];
                            if (i != 0) {
                                System.arraycopy(this.scrapbookPhotoUrl, 0, newArray, 0, i);
                            }
                            while (i < newArray.length - 1) {
                                newArray[i] = input.readString();
                                input.readTag();
                                i++;
                            }
                            newArray[i] = input.readString();
                            this.scrapbookPhotoUrl = newArray;
                            continue;
                        case LogSource.NOVA /*34*/:
                            if (this.origFullBleedImage == null) {
                                this.origFullBleedImage = new Image();
                            }
                            input.readMessage(this.origFullBleedImage);
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

            public static HovercardBannerInfo parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
                return (HovercardBannerInfo) MessageNano.mergeFrom(new HovercardBannerInfo(), data);
            }

            public static HovercardBannerInfo parseFrom(CodedInputByteBufferNano input) throws IOException {
                return new HovercardBannerInfo().mergeFrom(input);
            }
        }

        public static final class Membership extends ExtendableMessageNano<Membership> {
            private static volatile Membership[] _emptyArray;
            public CircleId circleId;
            public boolean deleted;
            public int memberType;
            public String obfuscatedInviterGaiaId;

            public static Membership[] emptyArray() {
                if (_emptyArray == null) {
                    synchronized (InternalNano.LAZY_INIT_LOCK) {
                        if (_emptyArray == null) {
                            _emptyArray = new Membership[0];
                        }
                    }
                }
                return _emptyArray;
            }

            public Membership() {
                clear();
            }

            public Membership clear() {
                this.circleId = null;
                this.memberType = 2;
                this.obfuscatedInviterGaiaId = BuildConfig.VERSION_NAME;
                this.deleted = false;
                this.unknownFieldData = null;
                this.cachedSize = -1;
                return this;
            }

            public boolean equals(Object o) {
                if (o == this) {
                    return true;
                }
                if (!(o instanceof Membership)) {
                    return false;
                }
                Membership other = (Membership) o;
                if (this.circleId == null) {
                    if (other.circleId != null) {
                        return false;
                    }
                } else if (!this.circleId.equals(other.circleId)) {
                    return false;
                }
                if (this.memberType != other.memberType) {
                    return false;
                }
                if (this.obfuscatedInviterGaiaId == null) {
                    if (other.obfuscatedInviterGaiaId != null) {
                        return false;
                    }
                } else if (!this.obfuscatedInviterGaiaId.equals(other.obfuscatedInviterGaiaId)) {
                    return false;
                }
                if (this.deleted != other.deleted) {
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
                int hashCode = (((((((((getClass().getName().hashCode() + 527) * 31) + (this.circleId == null ? 0 : this.circleId.hashCode())) * 31) + this.memberType) * 31) + (this.obfuscatedInviterGaiaId == null ? 0 : this.obfuscatedInviterGaiaId.hashCode())) * 31) + (this.deleted ? 1231 : 1237)) * 31;
                if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                    i = this.unknownFieldData.hashCode();
                }
                return hashCode + i;
            }

            public void writeTo(CodedOutputByteBufferNano output) throws IOException {
                if (this.memberType != 2) {
                    output.writeInt32(1, this.memberType);
                }
                if (!this.obfuscatedInviterGaiaId.equals(BuildConfig.VERSION_NAME)) {
                    output.writeString(2, this.obfuscatedInviterGaiaId);
                }
                if (this.circleId != null) {
                    output.writeMessage(3, this.circleId);
                }
                if (this.deleted) {
                    output.writeBool(4, this.deleted);
                }
                super.writeTo(output);
            }

            protected int computeSerializedSize() {
                int size = super.computeSerializedSize();
                if (this.memberType != 2) {
                    size += CodedOutputByteBufferNano.computeInt32Size(1, this.memberType);
                }
                if (!this.obfuscatedInviterGaiaId.equals(BuildConfig.VERSION_NAME)) {
                    size += CodedOutputByteBufferNano.computeStringSize(2, this.obfuscatedInviterGaiaId);
                }
                if (this.circleId != null) {
                    size += CodedOutputByteBufferNano.computeMessageSize(3, this.circleId);
                }
                if (this.deleted) {
                    return size + CodedOutputByteBufferNano.computeBoolSize(4, this.deleted);
                }
                return size;
            }

            public Membership mergeFrom(CodedInputByteBufferNano input) throws IOException {
                while (true) {
                    int tag = input.readTag();
                    switch (tag) {
                        case Action.UNKNOWN /*0*/:
                            break;
                        case Type.TAP_GET_LINK /*8*/:
                            int value = input.readInt32();
                            switch (value) {
                                case TimeSelection.Type.TEMPORARY /*1*/:
                                case TimeSelection.Type.INDEFINITELY /*2*/:
                                case TimeSelection.Type.CUSTOM /*3*/:
                                case OvenFreshResult.NO_ACCOUNTS /*4*/:
                                case Type.ADD_NEW_SHARES /*5*/:
                                    this.memberType = value;
                                    break;
                                default:
                                    continue;
                            }
                        case LogSource.GOOGLE_ANALYTICS /*18*/:
                            this.obfuscatedInviterGaiaId = input.readString();
                            continue;
                        case LogSource.ANDROID_CAMERA /*26*/:
                            if (this.circleId == null) {
                                this.circleId = new CircleId();
                            }
                            input.readMessage(this.circleId);
                            continue;
                        case LogSource.SOCIAL /*32*/:
                            this.deleted = input.readBool();
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

            public static Membership parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
                return (Membership) MessageNano.mergeFrom(new Membership(), data);
            }

            public static Membership parseFrom(CodedInputByteBufferNano input) throws IOException {
                return new Membership().mergeFrom(input);
            }
        }

        public static final class Phone extends ExtendableMessageNano<Phone> {
            private static volatile Phone[] _emptyArray;
            public String canonicalizedForm;
            public String customTag;
            public boolean primary;
            public int standardTag;
            public String uri;
            public String value;

            public static Phone[] emptyArray() {
                if (_emptyArray == null) {
                    synchronized (InternalNano.LAZY_INIT_LOCK) {
                        if (_emptyArray == null) {
                            _emptyArray = new Phone[0];
                        }
                    }
                }
                return _emptyArray;
            }

            public Phone() {
                clear();
            }

            public Phone clear() {
                this.standardTag = 1;
                this.customTag = BuildConfig.VERSION_NAME;
                this.value = BuildConfig.VERSION_NAME;
                this.primary = false;
                this.canonicalizedForm = BuildConfig.VERSION_NAME;
                this.uri = BuildConfig.VERSION_NAME;
                this.unknownFieldData = null;
                this.cachedSize = -1;
                return this;
            }

            public boolean equals(Object o) {
                if (o == this) {
                    return true;
                }
                if (!(o instanceof Phone)) {
                    return false;
                }
                Phone other = (Phone) o;
                if (this.standardTag != other.standardTag) {
                    return false;
                }
                if (this.customTag == null) {
                    if (other.customTag != null) {
                        return false;
                    }
                } else if (!this.customTag.equals(other.customTag)) {
                    return false;
                }
                if (this.value == null) {
                    if (other.value != null) {
                        return false;
                    }
                } else if (!this.value.equals(other.value)) {
                    return false;
                }
                if (this.primary != other.primary) {
                    return false;
                }
                if (this.canonicalizedForm == null) {
                    if (other.canonicalizedForm != null) {
                        return false;
                    }
                } else if (!this.canonicalizedForm.equals(other.canonicalizedForm)) {
                    return false;
                }
                if (this.uri == null) {
                    if (other.uri != null) {
                        return false;
                    }
                } else if (!this.uri.equals(other.uri)) {
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
                int hashCode = (((((((((((((getClass().getName().hashCode() + 527) * 31) + this.standardTag) * 31) + (this.customTag == null ? 0 : this.customTag.hashCode())) * 31) + (this.value == null ? 0 : this.value.hashCode())) * 31) + (this.primary ? 1231 : 1237)) * 31) + (this.canonicalizedForm == null ? 0 : this.canonicalizedForm.hashCode())) * 31) + (this.uri == null ? 0 : this.uri.hashCode())) * 31;
                if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                    i = this.unknownFieldData.hashCode();
                }
                return hashCode + i;
            }

            public void writeTo(CodedOutputByteBufferNano output) throws IOException {
                if (this.standardTag != 1) {
                    output.writeInt32(1, this.standardTag);
                }
                if (!this.customTag.equals(BuildConfig.VERSION_NAME)) {
                    output.writeString(2, this.customTag);
                }
                output.writeString(3, this.value);
                if (this.primary) {
                    output.writeBool(4, this.primary);
                }
                if (!this.canonicalizedForm.equals(BuildConfig.VERSION_NAME)) {
                    output.writeString(5, this.canonicalizedForm);
                }
                if (!this.uri.equals(BuildConfig.VERSION_NAME)) {
                    output.writeString(6, this.uri);
                }
                super.writeTo(output);
            }

            protected int computeSerializedSize() {
                int size = super.computeSerializedSize();
                if (this.standardTag != 1) {
                    size += CodedOutputByteBufferNano.computeInt32Size(1, this.standardTag);
                }
                if (!this.customTag.equals(BuildConfig.VERSION_NAME)) {
                    size += CodedOutputByteBufferNano.computeStringSize(2, this.customTag);
                }
                size += CodedOutputByteBufferNano.computeStringSize(3, this.value);
                if (this.primary) {
                    size += CodedOutputByteBufferNano.computeBoolSize(4, this.primary);
                }
                if (!this.canonicalizedForm.equals(BuildConfig.VERSION_NAME)) {
                    size += CodedOutputByteBufferNano.computeStringSize(5, this.canonicalizedForm);
                }
                if (this.uri.equals(BuildConfig.VERSION_NAME)) {
                    return size;
                }
                return size + CodedOutputByteBufferNano.computeStringSize(6, this.uri);
            }

            public Phone mergeFrom(CodedInputByteBufferNano input) throws IOException {
                while (true) {
                    int tag = input.readTag();
                    switch (tag) {
                        case Action.UNKNOWN /*0*/:
                            break;
                        case Type.TAP_GET_LINK /*8*/:
                            this.standardTag = input.readInt32();
                            continue;
                        case LogSource.GOOGLE_ANALYTICS /*18*/:
                            this.customTag = input.readString();
                            continue;
                        case LogSource.ANDROID_CAMERA /*26*/:
                            this.value = input.readString();
                            continue;
                        case LogSource.SOCIAL /*32*/:
                            this.primary = input.readBool();
                            continue;
                        case LogSource.PHOTOS /*42*/:
                            this.canonicalizedForm = input.readString();
                            continue;
                        case LogSource.HANGOUT_LOG_REQUEST /*50*/:
                            this.uri = input.readString();
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

            public static Phone parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
                return (Phone) MessageNano.mergeFrom(new Phone(), data);
            }

            public static Phone parseFrom(CodedInputByteBufferNano input) throws IOException {
                return new Phone().mergeFrom(input);
            }
        }

        public static final class ScrapbookInfo extends ExtendableMessageNano<ScrapbookInfo> {
            private static volatile ScrapbookInfo[] _emptyArray;
            public FullBleedPhoto fullBleedPhoto;
            public String[] fullBleedPhotoId;
            public int layout;

            public static final class CoverLayoutCoordinate extends ExtendableMessageNano<CoverLayoutCoordinate> {
                private static volatile CoverLayoutCoordinate[] _emptyArray;
                public float bottom;
                public float left;
                public float right;
                public float top;

                public static CoverLayoutCoordinate[] emptyArray() {
                    if (_emptyArray == null) {
                        synchronized (InternalNano.LAZY_INIT_LOCK) {
                            if (_emptyArray == null) {
                                _emptyArray = new CoverLayoutCoordinate[0];
                            }
                        }
                    }
                    return _emptyArray;
                }

                public CoverLayoutCoordinate() {
                    clear();
                }

                public CoverLayoutCoordinate clear() {
                    this.top = 0.0f;
                    this.left = 0.0f;
                    this.bottom = 0.0f;
                    this.right = 0.0f;
                    this.unknownFieldData = null;
                    this.cachedSize = -1;
                    return this;
                }

                public boolean equals(Object o) {
                    if (o == this) {
                        return true;
                    }
                    if (!(o instanceof CoverLayoutCoordinate)) {
                        return false;
                    }
                    CoverLayoutCoordinate other = (CoverLayoutCoordinate) o;
                    if (Float.floatToIntBits(this.top) != Float.floatToIntBits(other.top) || Float.floatToIntBits(this.left) != Float.floatToIntBits(other.left) || Float.floatToIntBits(this.bottom) != Float.floatToIntBits(other.bottom) || Float.floatToIntBits(this.right) != Float.floatToIntBits(other.right)) {
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
                    int hashCode = (((((((((getClass().getName().hashCode() + 527) * 31) + Float.floatToIntBits(this.top)) * 31) + Float.floatToIntBits(this.left)) * 31) + Float.floatToIntBits(this.bottom)) * 31) + Float.floatToIntBits(this.right)) * 31;
                    int hashCode2 = (this.unknownFieldData == null || this.unknownFieldData.isEmpty()) ? 0 : this.unknownFieldData.hashCode();
                    return hashCode + hashCode2;
                }

                public void writeTo(CodedOutputByteBufferNano output) throws IOException {
                    if (Float.floatToIntBits(this.top) != Float.floatToIntBits(0.0f)) {
                        output.writeFloat(1, this.top);
                    }
                    if (Float.floatToIntBits(this.left) != Float.floatToIntBits(0.0f)) {
                        output.writeFloat(2, this.left);
                    }
                    if (Float.floatToIntBits(this.bottom) != Float.floatToIntBits(0.0f)) {
                        output.writeFloat(3, this.bottom);
                    }
                    if (Float.floatToIntBits(this.right) != Float.floatToIntBits(0.0f)) {
                        output.writeFloat(4, this.right);
                    }
                    super.writeTo(output);
                }

                protected int computeSerializedSize() {
                    int size = super.computeSerializedSize();
                    if (Float.floatToIntBits(this.top) != Float.floatToIntBits(0.0f)) {
                        size += CodedOutputByteBufferNano.computeFloatSize(1, this.top);
                    }
                    if (Float.floatToIntBits(this.left) != Float.floatToIntBits(0.0f)) {
                        size += CodedOutputByteBufferNano.computeFloatSize(2, this.left);
                    }
                    if (Float.floatToIntBits(this.bottom) != Float.floatToIntBits(0.0f)) {
                        size += CodedOutputByteBufferNano.computeFloatSize(3, this.bottom);
                    }
                    if (Float.floatToIntBits(this.right) != Float.floatToIntBits(0.0f)) {
                        return size + CodedOutputByteBufferNano.computeFloatSize(4, this.right);
                    }
                    return size;
                }

                public CoverLayoutCoordinate mergeFrom(CodedInputByteBufferNano input) throws IOException {
                    while (true) {
                        int tag = input.readTag();
                        switch (tag) {
                            case Action.UNKNOWN /*0*/:
                                break;
                            case Type.DISPLAY_ERROR /*13*/:
                                this.top = input.readFloat();
                                continue;
                            case LogSource.LB_T /*21*/:
                                this.left = input.readFloat();
                                continue;
                            case LogSource.DNA_PROBER /*29*/:
                                this.bottom = input.readFloat();
                                continue;
                            case LogSource.PERSONAL_BROWSER_LOGGER /*37*/:
                                this.right = input.readFloat();
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

                public static CoverLayoutCoordinate parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
                    return (CoverLayoutCoordinate) MessageNano.mergeFrom(new CoverLayoutCoordinate(), data);
                }

                public static CoverLayoutCoordinate parseFrom(CodedInputByteBufferNano input) throws IOException {
                    return new CoverLayoutCoordinate().mergeFrom(input);
                }
            }

            public static final class FullBleedPhoto extends ExtendableMessageNano<FullBleedPhoto> {
                private static volatile FullBleedPhoto[] _emptyArray;
                public CoverLayoutCoordinate coordinate;
                public String id;
                public Offset offset;
                public int photoOwnerType;

                public static FullBleedPhoto[] emptyArray() {
                    if (_emptyArray == null) {
                        synchronized (InternalNano.LAZY_INIT_LOCK) {
                            if (_emptyArray == null) {
                                _emptyArray = new FullBleedPhoto[0];
                            }
                        }
                    }
                    return _emptyArray;
                }

                public FullBleedPhoto() {
                    clear();
                }

                public FullBleedPhoto clear() {
                    this.id = BuildConfig.VERSION_NAME;
                    this.offset = null;
                    this.photoOwnerType = 1;
                    this.coordinate = null;
                    this.unknownFieldData = null;
                    this.cachedSize = -1;
                    return this;
                }

                public boolean equals(Object o) {
                    if (o == this) {
                        return true;
                    }
                    if (!(o instanceof FullBleedPhoto)) {
                        return false;
                    }
                    FullBleedPhoto other = (FullBleedPhoto) o;
                    if (this.id == null) {
                        if (other.id != null) {
                            return false;
                        }
                    } else if (!this.id.equals(other.id)) {
                        return false;
                    }
                    if (this.offset == null) {
                        if (other.offset != null) {
                            return false;
                        }
                    } else if (!this.offset.equals(other.offset)) {
                        return false;
                    }
                    if (this.photoOwnerType != other.photoOwnerType) {
                        return false;
                    }
                    if (this.coordinate == null) {
                        if (other.coordinate != null) {
                            return false;
                        }
                    } else if (!this.coordinate.equals(other.coordinate)) {
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
                    int hashCode = (((((((((getClass().getName().hashCode() + 527) * 31) + (this.id == null ? 0 : this.id.hashCode())) * 31) + (this.offset == null ? 0 : this.offset.hashCode())) * 31) + this.photoOwnerType) * 31) + (this.coordinate == null ? 0 : this.coordinate.hashCode())) * 31;
                    if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                        i = this.unknownFieldData.hashCode();
                    }
                    return hashCode + i;
                }

                public void writeTo(CodedOutputByteBufferNano output) throws IOException {
                    if (!this.id.equals(BuildConfig.VERSION_NAME)) {
                        output.writeString(1, this.id);
                    }
                    if (this.offset != null) {
                        output.writeMessage(2, this.offset);
                    }
                    if (this.photoOwnerType != 1) {
                        output.writeInt32(3, this.photoOwnerType);
                    }
                    if (this.coordinate != null) {
                        output.writeMessage(4, this.coordinate);
                    }
                    super.writeTo(output);
                }

                protected int computeSerializedSize() {
                    int size = super.computeSerializedSize();
                    if (!this.id.equals(BuildConfig.VERSION_NAME)) {
                        size += CodedOutputByteBufferNano.computeStringSize(1, this.id);
                    }
                    if (this.offset != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(2, this.offset);
                    }
                    if (this.photoOwnerType != 1) {
                        size += CodedOutputByteBufferNano.computeInt32Size(3, this.photoOwnerType);
                    }
                    if (this.coordinate != null) {
                        return size + CodedOutputByteBufferNano.computeMessageSize(4, this.coordinate);
                    }
                    return size;
                }

                public FullBleedPhoto mergeFrom(CodedInputByteBufferNano input) throws IOException {
                    while (true) {
                        int tag = input.readTag();
                        switch (tag) {
                            case Action.UNKNOWN /*0*/:
                                break;
                            case Type.TAP_ABOUT /*10*/:
                                this.id = input.readString();
                                continue;
                            case LogSource.GOOGLE_ANALYTICS /*18*/:
                                if (this.offset == null) {
                                    this.offset = new Offset();
                                }
                                input.readMessage(this.offset);
                                continue;
                            case LogSource.LB_C /*24*/:
                                int value = input.readInt32();
                                switch (value) {
                                    case TimeSelection.Type.TEMPORARY /*1*/:
                                    case TimeSelection.Type.INDEFINITELY /*2*/:
                                        this.photoOwnerType = value;
                                        break;
                                    default:
                                        continue;
                                }
                            case LogSource.NOVA /*34*/:
                                if (this.coordinate == null) {
                                    this.coordinate = new CoverLayoutCoordinate();
                                }
                                input.readMessage(this.coordinate);
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

                public static FullBleedPhoto parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
                    return (FullBleedPhoto) MessageNano.mergeFrom(new FullBleedPhoto(), data);
                }

                public static FullBleedPhoto parseFrom(CodedInputByteBufferNano input) throws IOException {
                    return new FullBleedPhoto().mergeFrom(input);
                }
            }

            public static final class Offset extends ExtendableMessageNano<Offset> {
                private static volatile Offset[] _emptyArray;
                public int left;
                public int top;

                public static Offset[] emptyArray() {
                    if (_emptyArray == null) {
                        synchronized (InternalNano.LAZY_INIT_LOCK) {
                            if (_emptyArray == null) {
                                _emptyArray = new Offset[0];
                            }
                        }
                    }
                    return _emptyArray;
                }

                public Offset() {
                    clear();
                }

                public Offset clear() {
                    this.top = 0;
                    this.left = 0;
                    this.unknownFieldData = null;
                    this.cachedSize = -1;
                    return this;
                }

                public boolean equals(Object o) {
                    if (o == this) {
                        return true;
                    }
                    if (!(o instanceof Offset)) {
                        return false;
                    }
                    Offset other = (Offset) o;
                    if (this.top != other.top || this.left != other.left) {
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
                    int hashCode = (((((getClass().getName().hashCode() + 527) * 31) + this.top) * 31) + this.left) * 31;
                    int hashCode2 = (this.unknownFieldData == null || this.unknownFieldData.isEmpty()) ? 0 : this.unknownFieldData.hashCode();
                    return hashCode + hashCode2;
                }

                public void writeTo(CodedOutputByteBufferNano output) throws IOException {
                    if (this.top != 0) {
                        output.writeInt32(1, this.top);
                    }
                    if (this.left != 0) {
                        output.writeInt32(2, this.left);
                    }
                    super.writeTo(output);
                }

                protected int computeSerializedSize() {
                    int size = super.computeSerializedSize();
                    if (this.top != 0) {
                        size += CodedOutputByteBufferNano.computeInt32Size(1, this.top);
                    }
                    if (this.left != 0) {
                        return size + CodedOutputByteBufferNano.computeInt32Size(2, this.left);
                    }
                    return size;
                }

                public Offset mergeFrom(CodedInputByteBufferNano input) throws IOException {
                    while (true) {
                        int tag = input.readTag();
                        switch (tag) {
                            case Action.UNKNOWN /*0*/:
                                break;
                            case Type.TAP_GET_LINK /*8*/:
                                this.top = input.readInt32();
                                continue;
                            case LogSource.GMS_CORE_PEOPLE /*16*/:
                                this.left = input.readInt32();
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

                public static Offset parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
                    return (Offset) MessageNano.mergeFrom(new Offset(), data);
                }

                public static Offset parseFrom(CodedInputByteBufferNano input) throws IOException {
                    return new Offset().mergeFrom(input);
                }
            }

            public interface PhotoOwnerType {
                public static final int GALLERY = 2;
                public static final int SELF = 1;
            }

            public interface ScrapbookLayout {
                public static final int COVER = 3;
                public static final int FULL_BLEED = 2;
                public static final int NORMAL = 1;
            }

            public static ScrapbookInfo[] emptyArray() {
                if (_emptyArray == null) {
                    synchronized (InternalNano.LAZY_INIT_LOCK) {
                        if (_emptyArray == null) {
                            _emptyArray = new ScrapbookInfo[0];
                        }
                    }
                }
                return _emptyArray;
            }

            public ScrapbookInfo() {
                clear();
            }

            public ScrapbookInfo clear() {
                this.layout = 1;
                this.fullBleedPhotoId = WireFormatNano.EMPTY_STRING_ARRAY;
                this.fullBleedPhoto = null;
                this.unknownFieldData = null;
                this.cachedSize = -1;
                return this;
            }

            public boolean equals(Object o) {
                if (o == this) {
                    return true;
                }
                if (!(o instanceof ScrapbookInfo)) {
                    return false;
                }
                ScrapbookInfo other = (ScrapbookInfo) o;
                if (this.layout != other.layout || !InternalNano.equals(this.fullBleedPhotoId, other.fullBleedPhotoId)) {
                    return false;
                }
                if (this.fullBleedPhoto == null) {
                    if (other.fullBleedPhoto != null) {
                        return false;
                    }
                } else if (!this.fullBleedPhoto.equals(other.fullBleedPhoto)) {
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
                int hashCode = (((((((getClass().getName().hashCode() + 527) * 31) + this.layout) * 31) + InternalNano.hashCode(this.fullBleedPhotoId)) * 31) + (this.fullBleedPhoto == null ? 0 : this.fullBleedPhoto.hashCode())) * 31;
                if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                    i = this.unknownFieldData.hashCode();
                }
                return hashCode + i;
            }

            public void writeTo(CodedOutputByteBufferNano output) throws IOException {
                if (this.layout != 1) {
                    output.writeInt32(1, this.layout);
                }
                if (this.fullBleedPhotoId != null && this.fullBleedPhotoId.length > 0) {
                    for (String element : this.fullBleedPhotoId) {
                        if (element != null) {
                            output.writeString(2, element);
                        }
                    }
                }
                if (this.fullBleedPhoto != null) {
                    output.writeMessage(3, this.fullBleedPhoto);
                }
                super.writeTo(output);
            }

            protected int computeSerializedSize() {
                int size = super.computeSerializedSize();
                if (this.layout != 1) {
                    size += CodedOutputByteBufferNano.computeInt32Size(1, this.layout);
                }
                if (this.fullBleedPhotoId != null && this.fullBleedPhotoId.length > 0) {
                    int dataCount = 0;
                    int dataSize = 0;
                    for (String element : this.fullBleedPhotoId) {
                        if (element != null) {
                            dataCount++;
                            dataSize += CodedOutputByteBufferNano.computeStringSizeNoTag(element);
                        }
                    }
                    size = (size + dataSize) + (dataCount * 1);
                }
                if (this.fullBleedPhoto != null) {
                    return size + CodedOutputByteBufferNano.computeMessageSize(3, this.fullBleedPhoto);
                }
                return size;
            }

            public ScrapbookInfo mergeFrom(CodedInputByteBufferNano input) throws IOException {
                while (true) {
                    int tag = input.readTag();
                    switch (tag) {
                        case Action.UNKNOWN /*0*/:
                            break;
                        case Type.TAP_GET_LINK /*8*/:
                            int value = input.readInt32();
                            switch (value) {
                                case TimeSelection.Type.TEMPORARY /*1*/:
                                case TimeSelection.Type.INDEFINITELY /*2*/:
                                case TimeSelection.Type.CUSTOM /*3*/:
                                    this.layout = value;
                                    break;
                                default:
                                    continue;
                            }
                        case LogSource.GOOGLE_ANALYTICS /*18*/:
                            int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 18);
                            int i = this.fullBleedPhotoId == null ? 0 : this.fullBleedPhotoId.length;
                            String[] newArray = new String[(i + arrayLength)];
                            if (i != 0) {
                                System.arraycopy(this.fullBleedPhotoId, 0, newArray, 0, i);
                            }
                            while (i < newArray.length - 1) {
                                newArray[i] = input.readString();
                                input.readTag();
                                i++;
                            }
                            newArray[i] = input.readString();
                            this.fullBleedPhotoId = newArray;
                            continue;
                        case LogSource.ANDROID_CAMERA /*26*/:
                            if (this.fullBleedPhoto == null) {
                                this.fullBleedPhoto = new FullBleedPhoto();
                            }
                            input.readMessage(this.fullBleedPhoto);
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

            public static ScrapbookInfo parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
                return (ScrapbookInfo) MessageNano.mergeFrom(new ScrapbookInfo(), data);
            }

            public static ScrapbookInfo parseFrom(CodedInputByteBufferNano input) throws IOException {
                return new ScrapbookInfo().mergeFrom(input);
            }
        }

        public static final class SystemGroup extends ExtendableMessageNano<SystemGroup> {
            private static volatile SystemGroup[] _emptyArray;

            public interface Type {
                public static final int ALL_CIRCLE_MEMBERS = 7;
                public static final int BLOCKED = 5;
                public static final int DASHER_DOMAIN = 2;
                public static final int EXTENDED_CIRCLES = 4;
                public static final int IGNORED = 6;
                public static final int PUBLIC = 1;
                public static final int YOUR_CIRCLES = 3;
            }

            public static SystemGroup[] emptyArray() {
                if (_emptyArray == null) {
                    synchronized (InternalNano.LAZY_INIT_LOCK) {
                        if (_emptyArray == null) {
                            _emptyArray = new SystemGroup[0];
                        }
                    }
                }
                return _emptyArray;
            }

            public SystemGroup() {
                clear();
            }

            public SystemGroup clear() {
                this.unknownFieldData = null;
                this.cachedSize = -1;
                return this;
            }

            public boolean equals(Object o) {
                if (o == this) {
                    return true;
                }
                if (!(o instanceof SystemGroup)) {
                    return false;
                }
                SystemGroup other = (SystemGroup) o;
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

            public SystemGroup mergeFrom(CodedInputByteBufferNano input) throws IOException {
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

            public static SystemGroup parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
                return (SystemGroup) MessageNano.mergeFrom(new SystemGroup(), data);
            }

            public static SystemGroup parseFrom(CodedInputByteBufferNano input) throws IOException {
                return new SystemGroup().mergeFrom(input);
            }
        }

        public static data[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new data[0];
                    }
                }
            }
            return _emptyArray;
        }

        public data() {
            clear();
        }

        public data clear() {
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof data)) {
                return false;
            }
            data other = (data) o;
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

        public data mergeFrom(CodedInputByteBufferNano input) throws IOException {
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

        public static data parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (data) MessageNano.mergeFrom(new data(), data);
        }

        public static data parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new data().mergeFrom(input);
        }
    }
}
