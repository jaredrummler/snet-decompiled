package com.google.apps.tacotown.proto;

import com.google.apps.tacotown.socialgraph.proto.Data.data.CircleMemberId;
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

public interface Sharingroster {

    public interface ApplicationId {
        public static final int DEPRECATED_GAMES_SERVICES_GAME_ACLS = 16;
        public static final int DEPRECATED_GAMES_SERVICES_REQUEST_ACLS = 17;
        public static final int DESTINATION_STREAM_BANNED = 23;
        public static final int DESTINATION_STREAM_MODERATOR = 21;
        public static final int DESTINATION_STREAM_PREAPPROVED = 22;
        public static final int ES_BIRTHDAY_DELIVERY = 18;
        public static final int ES_COLLEXIONS = 26;
        public static final int ES_DESTINATION_STREAM = 24;
        public static final int ES_EVENTS = 1;
        public static final int ES_FRAMESERVICE_SETTINGS = 2;
        public static final int ES_PHOTOS_ALBUMS = 3;
        public static final int ES_PHOTOS_PROFILES = 4;
        public static final int ES_PLUSONE = 5;
        public static final int ES_PROFILES = 6;
        public static final int ES_SETTINGS_COMMENT = 8;
        public static final int ES_SETTINGS_GAMES_NOTIFY = 19;
        public static final int ES_SETTINGS_NOTIFY = 7;
        public static final int ES_STREAM = 9;
        public static final int GCOMM = 10;
        public static final int GCOMM_BROADCAST = 11;
        public static final int GCOMM_BUSINESS = 12;
        public static final int LATITUDE = 13;
        public static final int MAPS_BEEN_HERE = 27;
        public static final int MUSIC_PLAYLISTS = 20;
        public static final int PLAY_GAMES_SERVICES_NOTIFICATIONS_DEV = 28;
        public static final int PLAY_GAMES_SERVICES_NOTIFICATIONS_PROD = 29;
        public static final int PLAY_GAMES_SERVICES_NOTIFICATIONS_STG = 30;
        public static final int PLAY_GAMES_SERVICES_NOTIFICATIONS_TEST = 31;
        public static final int SCHEMER = 14;
        public static final int YOUTUBE_GPLUS = 15;
        public static final int YOUTUBE_GPLUS_WITH_ORG = 25;
        public static final int YOUTUBE_PLAYLIST = 32;
    }

    public static final class ApplicationSharingPolicy extends ExtendableMessageNano<ApplicationSharingPolicy> {
        private static volatile ApplicationSharingPolicy[] _emptyArray;
        public Boolean allowSquares;
        public int[] allowedGroupType;
        public Integer applicationId;
        public Boolean showUnderageWarnings;

        public static ApplicationSharingPolicy[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new ApplicationSharingPolicy[0];
                    }
                }
            }
            return _emptyArray;
        }

        public ApplicationSharingPolicy() {
            clear();
        }

        public ApplicationSharingPolicy clear() {
            this.applicationId = null;
            this.allowedGroupType = WireFormatNano.EMPTY_INT_ARRAY;
            this.showUnderageWarnings = null;
            this.allowSquares = null;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof ApplicationSharingPolicy)) {
                return false;
            }
            ApplicationSharingPolicy other = (ApplicationSharingPolicy) o;
            if (this.applicationId == null) {
                if (other.applicationId != null) {
                    return false;
                }
            } else if (!this.applicationId.equals(other.applicationId)) {
                return false;
            }
            if (!InternalNano.equals(this.allowedGroupType, other.allowedGroupType)) {
                return false;
            }
            if (this.showUnderageWarnings == null) {
                if (other.showUnderageWarnings != null) {
                    return false;
                }
            } else if (!this.showUnderageWarnings.equals(other.showUnderageWarnings)) {
                return false;
            }
            if (this.allowSquares == null) {
                if (other.allowSquares != null) {
                    return false;
                }
            } else if (!this.allowSquares.equals(other.allowSquares)) {
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
            int hashCode = (((((((((getClass().getName().hashCode() + 527) * 31) + (this.applicationId == null ? 0 : this.applicationId.intValue())) * 31) + InternalNano.hashCode(this.allowedGroupType)) * 31) + (this.showUnderageWarnings == null ? 0 : this.showUnderageWarnings.hashCode())) * 31) + (this.allowSquares == null ? 0 : this.allowSquares.hashCode())) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.allowedGroupType != null && this.allowedGroupType.length > 0) {
                for (int writeInt32 : this.allowedGroupType) {
                    output.writeInt32(1, writeInt32);
                }
            }
            if (this.showUnderageWarnings != null) {
                output.writeBool(2, this.showUnderageWarnings.booleanValue());
            }
            if (this.allowSquares != null) {
                output.writeBool(3, this.allowSquares.booleanValue());
            }
            if (this.applicationId != null) {
                output.writeInt32(4, this.applicationId.intValue());
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.allowedGroupType != null && this.allowedGroupType.length > 0) {
                int dataSize = 0;
                for (int element : this.allowedGroupType) {
                    dataSize += CodedOutputByteBufferNano.computeInt32SizeNoTag(element);
                }
                size = (size + dataSize) + (this.allowedGroupType.length * 1);
            }
            if (this.showUnderageWarnings != null) {
                size += CodedOutputByteBufferNano.computeBoolSize(2, this.showUnderageWarnings.booleanValue());
            }
            if (this.allowSquares != null) {
                size += CodedOutputByteBufferNano.computeBoolSize(3, this.allowSquares.booleanValue());
            }
            if (this.applicationId != null) {
                return size + CodedOutputByteBufferNano.computeInt32Size(4, this.applicationId.intValue());
            }
            return size;
        }

        public ApplicationSharingPolicy mergeFrom(CodedInputByteBufferNano input) throws IOException {
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
                            i = this.allowedGroupType == null ? 0 : this.allowedGroupType.length;
                            if (i != 0 || validCount != validValues.length) {
                                newArray = new int[(i + validCount)];
                                if (i != 0) {
                                    System.arraycopy(this.allowedGroupType, 0, newArray, 0, i);
                                }
                                System.arraycopy(validValues, 0, newArray, i, validCount);
                                this.allowedGroupType = newArray;
                                break;
                            }
                            this.allowedGroupType = validValues;
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
                            if (this.allowedGroupType == null) {
                                i = 0;
                            } else {
                                i = this.allowedGroupType.length;
                            }
                            newArray = new int[(i + arrayLength)];
                            if (i != 0) {
                                System.arraycopy(this.allowedGroupType, 0, newArray, 0, i);
                            }
                            while (input.getBytesUntilLimit() > 0) {
                                value = input.readInt32();
                                switch (value) {
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
                            this.allowedGroupType = newArray;
                        }
                        input.popLimit(limit);
                        continue;
                    case LogSource.GMS_CORE_PEOPLE /*16*/:
                        this.showUnderageWarnings = Boolean.valueOf(input.readBool());
                        continue;
                    case LogSource.LB_C /*24*/:
                        this.allowSquares = Boolean.valueOf(input.readBool());
                        continue;
                    case LogSource.SOCIAL /*32*/:
                        value = input.readInt32();
                        switch (value) {
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
                            case LogSource.GMS_CORE_PEOPLE /*16*/:
                            case LogSource.LE /*17*/:
                            case LogSource.GOOGLE_ANALYTICS /*18*/:
                            case LogSource.LB_D /*19*/:
                            case LogSource.ANDROID_GSA /*20*/:
                            case LogSource.LB_T /*21*/:
                            case LogSource.PERSONAL_LOGGER /*22*/:
                            case LogSource.GMS_CORE_WALLET_MERCHANT_ERROR /*23*/:
                            case LogSource.LB_C /*24*/:
                            case LogSource.ANDROID_AUTH /*25*/:
                            case LogSource.ANDROID_CAMERA /*26*/:
                            case LogSource.CW /*27*/:
                            case LogSource.GEL /*28*/:
                            case LogSource.DNA_PROBER /*29*/:
                            case LogSource.UDR /*30*/:
                            case LogSource.GMS_CORE_WALLET /*31*/:
                            case LogSource.SOCIAL /*32*/:
                                this.applicationId = Integer.valueOf(value);
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

        public static ApplicationSharingPolicy parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (ApplicationSharingPolicy) MessageNano.mergeFrom(new ApplicationSharingPolicy(), data);
        }

        public static ApplicationSharingPolicy parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new ApplicationSharingPolicy().mergeFrom(input);
        }
    }

    public static final class CircleMemberTarget extends ExtendableMessageNano<CircleMemberTarget> {
        private static volatile CircleMemberTarget[] _emptyArray;
        public String photoUrl;

        public static CircleMemberTarget[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new CircleMemberTarget[0];
                    }
                }
            }
            return _emptyArray;
        }

        public CircleMemberTarget() {
            clear();
        }

        public CircleMemberTarget clear() {
            this.photoUrl = null;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof CircleMemberTarget)) {
                return false;
            }
            CircleMemberTarget other = (CircleMemberTarget) o;
            if (this.photoUrl == null) {
                if (other.photoUrl != null) {
                    return false;
                }
            } else if (!this.photoUrl.equals(other.photoUrl)) {
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
            int hashCode = (((getClass().getName().hashCode() + 527) * 31) + (this.photoUrl == null ? 0 : this.photoUrl.hashCode())) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.photoUrl != null) {
                output.writeString(1, this.photoUrl);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.photoUrl != null) {
                return size + CodedOutputByteBufferNano.computeStringSize(1, this.photoUrl);
            }
            return size;
        }

        public CircleMemberTarget mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        this.photoUrl = input.readString();
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

        public static CircleMemberTarget parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (CircleMemberTarget) MessageNano.mergeFrom(new CircleMemberTarget(), data);
        }

        public static CircleMemberTarget parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new CircleMemberTarget().mergeFrom(input);
        }
    }

    public static final class DasherDomain extends ExtendableMessageNano<DasherDomain> {
        private static volatile DasherDomain[] _emptyArray;
        public Boolean canChangeShareOut;
        public Boolean canShareOut;
        public String displayName;
        public String domainName;
        public String obfuscatedId;

        public static DasherDomain[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new DasherDomain[0];
                    }
                }
            }
            return _emptyArray;
        }

        public DasherDomain() {
            clear();
        }

        public DasherDomain clear() {
            this.obfuscatedId = null;
            this.displayName = null;
            this.domainName = null;
            this.canShareOut = null;
            this.canChangeShareOut = null;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof DasherDomain)) {
                return false;
            }
            DasherDomain other = (DasherDomain) o;
            if (this.obfuscatedId == null) {
                if (other.obfuscatedId != null) {
                    return false;
                }
            } else if (!this.obfuscatedId.equals(other.obfuscatedId)) {
                return false;
            }
            if (this.displayName == null) {
                if (other.displayName != null) {
                    return false;
                }
            } else if (!this.displayName.equals(other.displayName)) {
                return false;
            }
            if (this.domainName == null) {
                if (other.domainName != null) {
                    return false;
                }
            } else if (!this.domainName.equals(other.domainName)) {
                return false;
            }
            if (this.canShareOut == null) {
                if (other.canShareOut != null) {
                    return false;
                }
            } else if (!this.canShareOut.equals(other.canShareOut)) {
                return false;
            }
            if (this.canChangeShareOut == null) {
                if (other.canChangeShareOut != null) {
                    return false;
                }
            } else if (!this.canChangeShareOut.equals(other.canChangeShareOut)) {
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
            int hashCode = (((((((((((getClass().getName().hashCode() + 527) * 31) + (this.obfuscatedId == null ? 0 : this.obfuscatedId.hashCode())) * 31) + (this.displayName == null ? 0 : this.displayName.hashCode())) * 31) + (this.domainName == null ? 0 : this.domainName.hashCode())) * 31) + (this.canShareOut == null ? 0 : this.canShareOut.hashCode())) * 31) + (this.canChangeShareOut == null ? 0 : this.canChangeShareOut.hashCode())) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.obfuscatedId != null) {
                output.writeString(1, this.obfuscatedId);
            }
            if (this.displayName != null) {
                output.writeString(2, this.displayName);
            }
            if (this.domainName != null) {
                output.writeString(3, this.domainName);
            }
            if (this.canShareOut != null) {
                output.writeBool(5, this.canShareOut.booleanValue());
            }
            if (this.canChangeShareOut != null) {
                output.writeBool(6, this.canChangeShareOut.booleanValue());
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.obfuscatedId != null) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.obfuscatedId);
            }
            if (this.displayName != null) {
                size += CodedOutputByteBufferNano.computeStringSize(2, this.displayName);
            }
            if (this.domainName != null) {
                size += CodedOutputByteBufferNano.computeStringSize(3, this.domainName);
            }
            if (this.canShareOut != null) {
                size += CodedOutputByteBufferNano.computeBoolSize(5, this.canShareOut.booleanValue());
            }
            if (this.canChangeShareOut != null) {
                return size + CodedOutputByteBufferNano.computeBoolSize(6, this.canChangeShareOut.booleanValue());
            }
            return size;
        }

        public DasherDomain mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        this.obfuscatedId = input.readString();
                        continue;
                    case LogSource.GOOGLE_ANALYTICS /*18*/:
                        this.displayName = input.readString();
                        continue;
                    case LogSource.ANDROID_CAMERA /*26*/:
                        this.domainName = input.readString();
                        continue;
                    case LogSource.COPRESENCE /*40*/:
                        this.canShareOut = Boolean.valueOf(input.readBool());
                        continue;
                    case LogSource.BACKDROP /*48*/:
                        this.canChangeShareOut = Boolean.valueOf(input.readBool());
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

        public static DasherDomain parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (DasherDomain) MessageNano.mergeFrom(new DasherDomain(), data);
        }

        public static DasherDomain parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new DasherDomain().mergeFrom(input);
        }
    }

    public static final class EventTargetId extends ExtendableMessageNano<EventTargetId> {
        private static volatile EventTargetId[] _emptyArray;
        public String id;

        public static EventTargetId[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new EventTargetId[0];
                    }
                }
            }
            return _emptyArray;
        }

        public EventTargetId() {
            clear();
        }

        public EventTargetId clear() {
            this.id = null;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof EventTargetId)) {
                return false;
            }
            EventTargetId other = (EventTargetId) o;
            if (this.id == null) {
                if (other.id != null) {
                    return false;
                }
            } else if (!this.id.equals(other.id)) {
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
            int hashCode = (((getClass().getName().hashCode() + 527) * 31) + (this.id == null ? 0 : this.id.hashCode())) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.id != null) {
                output.writeString(1, this.id);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.id != null) {
                return size + CodedOutputByteBufferNano.computeStringSize(1, this.id);
            }
            return size;
        }

        public EventTargetId mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
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

        public static EventTargetId parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (EventTargetId) MessageNano.mergeFrom(new EventTargetId(), data);
        }

        public static EventTargetId parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new EventTargetId().mergeFrom(input);
        }
    }

    public static final class RenderedSharingRosters extends ExtendableMessageNano<RenderedSharingRosters> {
        private static volatile RenderedSharingRosters[] _emptyArray;
        public ApplicationSharingPolicy[] applicationPolicies;
        public DasherDomain domain;
        public ResourceSharingRoster[] resourceSharingRosters;
        public SharingTarget[] targets;

        public static RenderedSharingRosters[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new RenderedSharingRosters[0];
                    }
                }
            }
            return _emptyArray;
        }

        public RenderedSharingRosters() {
            clear();
        }

        public RenderedSharingRosters clear() {
            this.resourceSharingRosters = ResourceSharingRoster.emptyArray();
            this.applicationPolicies = ApplicationSharingPolicy.emptyArray();
            this.targets = SharingTarget.emptyArray();
            this.domain = null;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof RenderedSharingRosters)) {
                return false;
            }
            RenderedSharingRosters other = (RenderedSharingRosters) o;
            if (!InternalNano.equals(this.resourceSharingRosters, other.resourceSharingRosters) || !InternalNano.equals(this.applicationPolicies, other.applicationPolicies) || !InternalNano.equals(this.targets, other.targets)) {
                return false;
            }
            if (this.domain == null) {
                if (other.domain != null) {
                    return false;
                }
            } else if (!this.domain.equals(other.domain)) {
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
            int hashCode = (((((((((getClass().getName().hashCode() + 527) * 31) + InternalNano.hashCode(this.resourceSharingRosters)) * 31) + InternalNano.hashCode(this.applicationPolicies)) * 31) + InternalNano.hashCode(this.targets)) * 31) + (this.domain == null ? 0 : this.domain.hashCode())) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.resourceSharingRosters != null && this.resourceSharingRosters.length > 0) {
                for (ResourceSharingRoster element : this.resourceSharingRosters) {
                    if (element != null) {
                        output.writeMessage(1, element);
                    }
                }
            }
            if (this.applicationPolicies != null && this.applicationPolicies.length > 0) {
                for (ApplicationSharingPolicy element2 : this.applicationPolicies) {
                    if (element2 != null) {
                        output.writeMessage(2, element2);
                    }
                }
            }
            if (this.targets != null && this.targets.length > 0) {
                for (SharingTarget element3 : this.targets) {
                    if (element3 != null) {
                        output.writeMessage(3, element3);
                    }
                }
            }
            if (this.domain != null) {
                output.writeMessage(4, this.domain);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.resourceSharingRosters != null && this.resourceSharingRosters.length > 0) {
                for (ResourceSharingRoster element : this.resourceSharingRosters) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(1, element);
                    }
                }
            }
            if (this.applicationPolicies != null && this.applicationPolicies.length > 0) {
                for (ApplicationSharingPolicy element2 : this.applicationPolicies) {
                    if (element2 != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(2, element2);
                    }
                }
            }
            if (this.targets != null && this.targets.length > 0) {
                for (SharingTarget element3 : this.targets) {
                    if (element3 != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(3, element3);
                    }
                }
            }
            if (this.domain != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(4, this.domain);
            }
            return size;
        }

        public RenderedSharingRosters mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                int arrayLength;
                int i;
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 10);
                        if (this.resourceSharingRosters == null) {
                            i = 0;
                        } else {
                            i = this.resourceSharingRosters.length;
                        }
                        ResourceSharingRoster[] newArray = new ResourceSharingRoster[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.resourceSharingRosters, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new ResourceSharingRoster();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i++;
                        }
                        newArray[i] = new ResourceSharingRoster();
                        input.readMessage(newArray[i]);
                        this.resourceSharingRosters = newArray;
                        continue;
                    case LogSource.GOOGLE_ANALYTICS /*18*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 18);
                        if (this.applicationPolicies == null) {
                            i = 0;
                        } else {
                            i = this.applicationPolicies.length;
                        }
                        ApplicationSharingPolicy[] newArray2 = new ApplicationSharingPolicy[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.applicationPolicies, 0, newArray2, 0, i);
                        }
                        while (i < newArray2.length - 1) {
                            newArray2[i] = new ApplicationSharingPolicy();
                            input.readMessage(newArray2[i]);
                            input.readTag();
                            i++;
                        }
                        newArray2[i] = new ApplicationSharingPolicy();
                        input.readMessage(newArray2[i]);
                        this.applicationPolicies = newArray2;
                        continue;
                    case LogSource.ANDROID_CAMERA /*26*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 26);
                        if (this.targets == null) {
                            i = 0;
                        } else {
                            i = this.targets.length;
                        }
                        SharingTarget[] newArray3 = new SharingTarget[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.targets, 0, newArray3, 0, i);
                        }
                        while (i < newArray3.length - 1) {
                            newArray3[i] = new SharingTarget();
                            input.readMessage(newArray3[i]);
                            input.readTag();
                            i++;
                        }
                        newArray3[i] = new SharingTarget();
                        input.readMessage(newArray3[i]);
                        this.targets = newArray3;
                        continue;
                    case LogSource.NOVA /*34*/:
                        if (this.domain == null) {
                            this.domain = new DasherDomain();
                        }
                        input.readMessage(this.domain);
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

        public static RenderedSharingRosters parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (RenderedSharingRosters) MessageNano.mergeFrom(new RenderedSharingRosters(), data);
        }

        public static RenderedSharingRosters parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new RenderedSharingRosters().mergeFrom(input);
        }
    }

    public static final class ResourceSharingRoster extends ExtendableMessageNano<ResourceSharingRoster> {
        private static volatile ResourceSharingRoster[] _emptyArray;
        public Role[] defaultRoles;
        public SharedResourceId id;
        public RolesSharingRoster[] otherRolesSharingRosters;
        public SharingRoster sharingRoster;

        public static ResourceSharingRoster[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new ResourceSharingRoster[0];
                    }
                }
            }
            return _emptyArray;
        }

        public ResourceSharingRoster() {
            clear();
        }

        public ResourceSharingRoster clear() {
            this.id = null;
            this.sharingRoster = null;
            this.defaultRoles = Role.emptyArray();
            this.otherRolesSharingRosters = RolesSharingRoster.emptyArray();
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof ResourceSharingRoster)) {
                return false;
            }
            ResourceSharingRoster other = (ResourceSharingRoster) o;
            if (this.id == null) {
                if (other.id != null) {
                    return false;
                }
            } else if (!this.id.equals(other.id)) {
                return false;
            }
            if (this.sharingRoster == null) {
                if (other.sharingRoster != null) {
                    return false;
                }
            } else if (!this.sharingRoster.equals(other.sharingRoster)) {
                return false;
            }
            if (!InternalNano.equals(this.defaultRoles, other.defaultRoles) || !InternalNano.equals(this.otherRolesSharingRosters, other.otherRolesSharingRosters)) {
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
            int hashCode = (((((((((getClass().getName().hashCode() + 527) * 31) + (this.id == null ? 0 : this.id.hashCode())) * 31) + (this.sharingRoster == null ? 0 : this.sharingRoster.hashCode())) * 31) + InternalNano.hashCode(this.defaultRoles)) * 31) + InternalNano.hashCode(this.otherRolesSharingRosters)) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.id != null) {
                output.writeMessage(1, this.id);
            }
            if (this.sharingRoster != null) {
                output.writeMessage(2, this.sharingRoster);
            }
            if (this.defaultRoles != null && this.defaultRoles.length > 0) {
                for (Role element : this.defaultRoles) {
                    if (element != null) {
                        output.writeMessage(3, element);
                    }
                }
            }
            if (this.otherRolesSharingRosters != null && this.otherRolesSharingRosters.length > 0) {
                for (RolesSharingRoster element2 : this.otherRolesSharingRosters) {
                    if (element2 != null) {
                        output.writeMessage(4, element2);
                    }
                }
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.id != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(1, this.id);
            }
            if (this.sharingRoster != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(2, this.sharingRoster);
            }
            if (this.defaultRoles != null && this.defaultRoles.length > 0) {
                for (Role element : this.defaultRoles) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(3, element);
                    }
                }
            }
            if (this.otherRolesSharingRosters != null && this.otherRolesSharingRosters.length > 0) {
                for (RolesSharingRoster element2 : this.otherRolesSharingRosters) {
                    if (element2 != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(4, element2);
                    }
                }
            }
            return size;
        }

        public ResourceSharingRoster mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                int arrayLength;
                int i;
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        if (this.id == null) {
                            this.id = new SharedResourceId();
                        }
                        input.readMessage(this.id);
                        continue;
                    case LogSource.GOOGLE_ANALYTICS /*18*/:
                        if (this.sharingRoster == null) {
                            this.sharingRoster = new SharingRoster();
                        }
                        input.readMessage(this.sharingRoster);
                        continue;
                    case LogSource.ANDROID_CAMERA /*26*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 26);
                        if (this.defaultRoles == null) {
                            i = 0;
                        } else {
                            i = this.defaultRoles.length;
                        }
                        Role[] newArray = new Role[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.defaultRoles, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new Role();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i++;
                        }
                        newArray[i] = new Role();
                        input.readMessage(newArray[i]);
                        this.defaultRoles = newArray;
                        continue;
                    case LogSource.NOVA /*34*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 34);
                        if (this.otherRolesSharingRosters == null) {
                            i = 0;
                        } else {
                            i = this.otherRolesSharingRosters.length;
                        }
                        RolesSharingRoster[] newArray2 = new RolesSharingRoster[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.otherRolesSharingRosters, 0, newArray2, 0, i);
                        }
                        while (i < newArray2.length - 1) {
                            newArray2[i] = new RolesSharingRoster();
                            input.readMessage(newArray2[i]);
                            input.readTag();
                            i++;
                        }
                        newArray2[i] = new RolesSharingRoster();
                        input.readMessage(newArray2[i]);
                        this.otherRolesSharingRosters = newArray2;
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

        public static ResourceSharingRoster parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (ResourceSharingRoster) MessageNano.mergeFrom(new ResourceSharingRoster(), data);
        }

        public static ResourceSharingRoster parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new ResourceSharingRoster().mergeFrom(input);
        }
    }

    public static final class Role extends ExtendableMessageNano<Role> {
        private static volatile Role[] _emptyArray;
        public Integer value;

        public static Role[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new Role[0];
                    }
                }
            }
            return _emptyArray;
        }

        public Role() {
            clear();
        }

        public Role clear() {
            this.value = null;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof Role)) {
                return false;
            }
            Role other = (Role) o;
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
            int hashCode = (((getClass().getName().hashCode() + 527) * 31) + (this.value == null ? 0 : this.value.hashCode())) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.value != null) {
                output.writeInt32(1, this.value.intValue());
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.value != null) {
                return size + CodedOutputByteBufferNano.computeInt32Size(1, this.value.intValue());
            }
            return size;
        }

        public Role mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_GET_LINK /*8*/:
                        this.value = Integer.valueOf(input.readInt32());
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

        public static Role parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (Role) MessageNano.mergeFrom(new Role(), data);
        }

        public static Role parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new Role().mergeFrom(input);
        }
    }

    public static final class RolesSharingRoster extends ExtendableMessageNano<RolesSharingRoster> {
        private static volatile RolesSharingRoster[] _emptyArray;
        public Role[] roles;
        public SharingRoster sharingRoster;

        public static RolesSharingRoster[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new RolesSharingRoster[0];
                    }
                }
            }
            return _emptyArray;
        }

        public RolesSharingRoster() {
            clear();
        }

        public RolesSharingRoster clear() {
            this.roles = Role.emptyArray();
            this.sharingRoster = null;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof RolesSharingRoster)) {
                return false;
            }
            RolesSharingRoster other = (RolesSharingRoster) o;
            if (!InternalNano.equals(this.roles, other.roles)) {
                return false;
            }
            if (this.sharingRoster == null) {
                if (other.sharingRoster != null) {
                    return false;
                }
            } else if (!this.sharingRoster.equals(other.sharingRoster)) {
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
            int hashCode = (((((getClass().getName().hashCode() + 527) * 31) + InternalNano.hashCode(this.roles)) * 31) + (this.sharingRoster == null ? 0 : this.sharingRoster.hashCode())) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.roles != null && this.roles.length > 0) {
                for (Role element : this.roles) {
                    if (element != null) {
                        output.writeMessage(1, element);
                    }
                }
            }
            if (this.sharingRoster != null) {
                output.writeMessage(2, this.sharingRoster);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.roles != null && this.roles.length > 0) {
                for (Role element : this.roles) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(1, element);
                    }
                }
            }
            if (this.sharingRoster != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(2, this.sharingRoster);
            }
            return size;
        }

        public RolesSharingRoster mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        int i;
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 10);
                        if (this.roles == null) {
                            i = 0;
                        } else {
                            i = this.roles.length;
                        }
                        Role[] newArray = new Role[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.roles, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new Role();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i++;
                        }
                        newArray[i] = new Role();
                        input.readMessage(newArray[i]);
                        this.roles = newArray;
                        continue;
                    case LogSource.GOOGLE_ANALYTICS /*18*/:
                        if (this.sharingRoster == null) {
                            this.sharingRoster = new SharingRoster();
                        }
                        input.readMessage(this.sharingRoster);
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

        public static RolesSharingRoster parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (RolesSharingRoster) MessageNano.mergeFrom(new RolesSharingRoster(), data);
        }

        public static RolesSharingRoster parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new RolesSharingRoster().mergeFrom(input);
        }
    }

    public static final class SharedResourceId extends ExtendableMessageNano<SharedResourceId> {
        private static volatile SharedResourceId[] _emptyArray;
        public Integer applicationId;
        public String itemId;

        public static SharedResourceId[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new SharedResourceId[0];
                    }
                }
            }
            return _emptyArray;
        }

        public SharedResourceId() {
            clear();
        }

        public SharedResourceId clear() {
            this.applicationId = null;
            this.itemId = null;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof SharedResourceId)) {
                return false;
            }
            SharedResourceId other = (SharedResourceId) o;
            if (this.applicationId == null) {
                if (other.applicationId != null) {
                    return false;
                }
            } else if (!this.applicationId.equals(other.applicationId)) {
                return false;
            }
            if (this.itemId == null) {
                if (other.itemId != null) {
                    return false;
                }
            } else if (!this.itemId.equals(other.itemId)) {
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
            int hashCode = (((((getClass().getName().hashCode() + 527) * 31) + (this.applicationId == null ? 0 : this.applicationId.intValue())) * 31) + (this.itemId == null ? 0 : this.itemId.hashCode())) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.applicationId != null) {
                output.writeInt32(1, this.applicationId.intValue());
            }
            if (this.itemId != null) {
                output.writeString(2, this.itemId);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.applicationId != null) {
                size += CodedOutputByteBufferNano.computeInt32Size(1, this.applicationId.intValue());
            }
            if (this.itemId != null) {
                return size + CodedOutputByteBufferNano.computeStringSize(2, this.itemId);
            }
            return size;
        }

        public SharedResourceId mergeFrom(CodedInputByteBufferNano input) throws IOException {
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
                            case LogSource.GMS_CORE_PEOPLE /*16*/:
                            case LogSource.LE /*17*/:
                            case LogSource.GOOGLE_ANALYTICS /*18*/:
                            case LogSource.LB_D /*19*/:
                            case LogSource.ANDROID_GSA /*20*/:
                            case LogSource.LB_T /*21*/:
                            case LogSource.PERSONAL_LOGGER /*22*/:
                            case LogSource.GMS_CORE_WALLET_MERCHANT_ERROR /*23*/:
                            case LogSource.LB_C /*24*/:
                            case LogSource.ANDROID_AUTH /*25*/:
                            case LogSource.ANDROID_CAMERA /*26*/:
                            case LogSource.CW /*27*/:
                            case LogSource.GEL /*28*/:
                            case LogSource.DNA_PROBER /*29*/:
                            case LogSource.UDR /*30*/:
                            case LogSource.GMS_CORE_WALLET /*31*/:
                            case LogSource.SOCIAL /*32*/:
                                this.applicationId = Integer.valueOf(value);
                                break;
                            default:
                                continue;
                        }
                    case LogSource.GOOGLE_ANALYTICS /*18*/:
                        this.itemId = input.readString();
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

        public static SharedResourceId parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (SharedResourceId) MessageNano.mergeFrom(new SharedResourceId(), data);
        }

        public static SharedResourceId parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new SharedResourceId().mergeFrom(input);
        }
    }

    public static final class SharingRoster extends ExtendableMessageNano<SharingRoster> {
        private static volatile SharingRoster[] _emptyArray;
        public SharingTargetId requiredScopeId;
        public SharingTargetId[] sharingTargetId;

        public static SharingRoster[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new SharingRoster[0];
                    }
                }
            }
            return _emptyArray;
        }

        public SharingRoster() {
            clear();
        }

        public SharingRoster clear() {
            this.sharingTargetId = SharingTargetId.emptyArray();
            this.requiredScopeId = null;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof SharingRoster)) {
                return false;
            }
            SharingRoster other = (SharingRoster) o;
            if (!InternalNano.equals(this.sharingTargetId, other.sharingTargetId)) {
                return false;
            }
            if (this.requiredScopeId == null) {
                if (other.requiredScopeId != null) {
                    return false;
                }
            } else if (!this.requiredScopeId.equals(other.requiredScopeId)) {
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
            int hashCode = (((((getClass().getName().hashCode() + 527) * 31) + InternalNano.hashCode(this.sharingTargetId)) * 31) + (this.requiredScopeId == null ? 0 : this.requiredScopeId.hashCode())) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.sharingTargetId != null && this.sharingTargetId.length > 0) {
                for (SharingTargetId element : this.sharingTargetId) {
                    if (element != null) {
                        output.writeMessage(1, element);
                    }
                }
            }
            if (this.requiredScopeId != null) {
                output.writeMessage(2, this.requiredScopeId);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.sharingTargetId != null && this.sharingTargetId.length > 0) {
                for (SharingTargetId element : this.sharingTargetId) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(1, element);
                    }
                }
            }
            if (this.requiredScopeId != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(2, this.requiredScopeId);
            }
            return size;
        }

        public SharingRoster mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        int i;
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 10);
                        if (this.sharingTargetId == null) {
                            i = 0;
                        } else {
                            i = this.sharingTargetId.length;
                        }
                        SharingTargetId[] newArray = new SharingTargetId[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.sharingTargetId, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new SharingTargetId();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i++;
                        }
                        newArray[i] = new SharingTargetId();
                        input.readMessage(newArray[i]);
                        this.sharingTargetId = newArray;
                        continue;
                    case LogSource.GOOGLE_ANALYTICS /*18*/:
                        if (this.requiredScopeId == null) {
                            this.requiredScopeId = new SharingTargetId();
                        }
                        input.readMessage(this.requiredScopeId);
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

        public static SharingRoster parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (SharingRoster) MessageNano.mergeFrom(new SharingRoster(), data);
        }

        public static SharingRoster parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new SharingRoster().mergeFrom(input);
        }
    }

    public static final class SharingTarget extends ExtendableMessageNano<SharingTarget> {
        private static volatile SharingTarget[] _emptyArray;
        public String displayName;
        public String email;
        public SharingTargetId id;
        public CircleMemberTarget personTarget;

        public static SharingTarget[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new SharingTarget[0];
                    }
                }
            }
            return _emptyArray;
        }

        public SharingTarget() {
            clear();
        }

        public SharingTarget clear() {
            this.id = null;
            this.displayName = null;
            this.email = null;
            this.personTarget = null;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof SharingTarget)) {
                return false;
            }
            SharingTarget other = (SharingTarget) o;
            if (this.id == null) {
                if (other.id != null) {
                    return false;
                }
            } else if (!this.id.equals(other.id)) {
                return false;
            }
            if (this.displayName == null) {
                if (other.displayName != null) {
                    return false;
                }
            } else if (!this.displayName.equals(other.displayName)) {
                return false;
            }
            if (this.email == null) {
                if (other.email != null) {
                    return false;
                }
            } else if (!this.email.equals(other.email)) {
                return false;
            }
            if (this.personTarget == null) {
                if (other.personTarget != null) {
                    return false;
                }
            } else if (!this.personTarget.equals(other.personTarget)) {
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
            int hashCode = (((((((((getClass().getName().hashCode() + 527) * 31) + (this.id == null ? 0 : this.id.hashCode())) * 31) + (this.displayName == null ? 0 : this.displayName.hashCode())) * 31) + (this.email == null ? 0 : this.email.hashCode())) * 31) + (this.personTarget == null ? 0 : this.personTarget.hashCode())) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.id != null) {
                output.writeMessage(1, this.id);
            }
            if (this.displayName != null) {
                output.writeString(2, this.displayName);
            }
            if (this.email != null) {
                output.writeString(3, this.email);
            }
            if (this.personTarget != null) {
                output.writeMessage(4, this.personTarget);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.id != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(1, this.id);
            }
            if (this.displayName != null) {
                size += CodedOutputByteBufferNano.computeStringSize(2, this.displayName);
            }
            if (this.email != null) {
                size += CodedOutputByteBufferNano.computeStringSize(3, this.email);
            }
            if (this.personTarget != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(4, this.personTarget);
            }
            return size;
        }

        public SharingTarget mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        if (this.id == null) {
                            this.id = new SharingTargetId();
                        }
                        input.readMessage(this.id);
                        continue;
                    case LogSource.GOOGLE_ANALYTICS /*18*/:
                        this.displayName = input.readString();
                        continue;
                    case LogSource.ANDROID_CAMERA /*26*/:
                        this.email = input.readString();
                        continue;
                    case LogSource.NOVA /*34*/:
                        if (this.personTarget == null) {
                            this.personTarget = new CircleMemberTarget();
                        }
                        input.readMessage(this.personTarget);
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

        public static SharingTarget parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (SharingTarget) MessageNano.mergeFrom(new SharingTarget(), data);
        }

        public static SharingTarget parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new SharingTarget().mergeFrom(input);
        }
    }

    public static final class SharingTargetId extends ExtendableMessageNano<SharingTargetId> {
        private static volatile SharingTargetId[] _emptyArray;
        public String circleId;
        public EventTargetId eventId;
        public Integer groupType;
        public CircleMemberId personId;
        public SquareTargetId squareId;
        public ZanzibarTargetId zanzibarId;

        public static SharingTargetId[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new SharingTargetId[0];
                    }
                }
            }
            return _emptyArray;
        }

        public SharingTargetId() {
            clear();
        }

        public SharingTargetId clear() {
            this.personId = null;
            this.circleId = null;
            this.groupType = null;
            this.squareId = null;
            this.eventId = null;
            this.zanzibarId = null;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof SharingTargetId)) {
                return false;
            }
            SharingTargetId other = (SharingTargetId) o;
            if (this.personId == null) {
                if (other.personId != null) {
                    return false;
                }
            } else if (!this.personId.equals(other.personId)) {
                return false;
            }
            if (this.circleId == null) {
                if (other.circleId != null) {
                    return false;
                }
            } else if (!this.circleId.equals(other.circleId)) {
                return false;
            }
            if (this.groupType == null) {
                if (other.groupType != null) {
                    return false;
                }
            } else if (!this.groupType.equals(other.groupType)) {
                return false;
            }
            if (this.squareId == null) {
                if (other.squareId != null) {
                    return false;
                }
            } else if (!this.squareId.equals(other.squareId)) {
                return false;
            }
            if (this.eventId == null) {
                if (other.eventId != null) {
                    return false;
                }
            } else if (!this.eventId.equals(other.eventId)) {
                return false;
            }
            if (this.zanzibarId == null) {
                if (other.zanzibarId != null) {
                    return false;
                }
            } else if (!this.zanzibarId.equals(other.zanzibarId)) {
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
            int hashCode = (((((((((((((getClass().getName().hashCode() + 527) * 31) + (this.personId == null ? 0 : this.personId.hashCode())) * 31) + (this.circleId == null ? 0 : this.circleId.hashCode())) * 31) + (this.groupType == null ? 0 : this.groupType.intValue())) * 31) + (this.squareId == null ? 0 : this.squareId.hashCode())) * 31) + (this.eventId == null ? 0 : this.eventId.hashCode())) * 31) + (this.zanzibarId == null ? 0 : this.zanzibarId.hashCode())) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.personId != null) {
                output.writeMessage(1, this.personId);
            }
            if (this.circleId != null) {
                output.writeString(2, this.circleId);
            }
            if (this.groupType != null) {
                output.writeInt32(3, this.groupType.intValue());
            }
            if (this.squareId != null) {
                output.writeMessage(4, this.squareId);
            }
            if (this.eventId != null) {
                output.writeMessage(5, this.eventId);
            }
            if (this.zanzibarId != null) {
                output.writeMessage(6, this.zanzibarId);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.personId != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(1, this.personId);
            }
            if (this.circleId != null) {
                size += CodedOutputByteBufferNano.computeStringSize(2, this.circleId);
            }
            if (this.groupType != null) {
                size += CodedOutputByteBufferNano.computeInt32Size(3, this.groupType.intValue());
            }
            if (this.squareId != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(4, this.squareId);
            }
            if (this.eventId != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(5, this.eventId);
            }
            if (this.zanzibarId != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(6, this.zanzibarId);
            }
            return size;
        }

        public SharingTargetId mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        if (this.personId == null) {
                            this.personId = new CircleMemberId();
                        }
                        input.readMessage(this.personId);
                        continue;
                    case LogSource.GOOGLE_ANALYTICS /*18*/:
                        this.circleId = input.readString();
                        continue;
                    case LogSource.LB_C /*24*/:
                        int value = input.readInt32();
                        switch (value) {
                            case TimeSelection.Type.TEMPORARY /*1*/:
                            case TimeSelection.Type.INDEFINITELY /*2*/:
                            case TimeSelection.Type.CUSTOM /*3*/:
                            case OvenFreshResult.NO_ACCOUNTS /*4*/:
                            case Type.ADD_NEW_SHARES /*5*/:
                            case Type.REMOVE_SHARE /*6*/:
                            case Type.RESET_TIME /*7*/:
                                this.groupType = Integer.valueOf(value);
                                break;
                            default:
                                continue;
                        }
                    case LogSource.NOVA /*34*/:
                        if (this.squareId == null) {
                            this.squareId = new SquareTargetId();
                        }
                        input.readMessage(this.squareId);
                        continue;
                    case LogSource.PHOTOS /*42*/:
                        if (this.eventId == null) {
                            this.eventId = new EventTargetId();
                        }
                        input.readMessage(this.eventId);
                        continue;
                    case LogSource.HANGOUT_LOG_REQUEST /*50*/:
                        if (this.zanzibarId == null) {
                            this.zanzibarId = new ZanzibarTargetId();
                        }
                        input.readMessage(this.zanzibarId);
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

        public static SharingTargetId parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (SharingTargetId) MessageNano.mergeFrom(new SharingTargetId(), data);
        }

        public static SharingTargetId parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new SharingTargetId().mergeFrom(input);
        }
    }

    public static final class SquareTargetId extends ExtendableMessageNano<SquareTargetId> {
        private static volatile SquareTargetId[] _emptyArray;
        public String obfuscatedSquareId;
        public Boolean overrideApplicationToAclListV2;

        public static SquareTargetId[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new SquareTargetId[0];
                    }
                }
            }
            return _emptyArray;
        }

        public SquareTargetId() {
            clear();
        }

        public SquareTargetId clear() {
            this.obfuscatedSquareId = null;
            this.overrideApplicationToAclListV2 = null;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof SquareTargetId)) {
                return false;
            }
            SquareTargetId other = (SquareTargetId) o;
            if (this.obfuscatedSquareId == null) {
                if (other.obfuscatedSquareId != null) {
                    return false;
                }
            } else if (!this.obfuscatedSquareId.equals(other.obfuscatedSquareId)) {
                return false;
            }
            if (this.overrideApplicationToAclListV2 == null) {
                if (other.overrideApplicationToAclListV2 != null) {
                    return false;
                }
            } else if (!this.overrideApplicationToAclListV2.equals(other.overrideApplicationToAclListV2)) {
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
            int hashCode = (((((getClass().getName().hashCode() + 527) * 31) + (this.obfuscatedSquareId == null ? 0 : this.obfuscatedSquareId.hashCode())) * 31) + (this.overrideApplicationToAclListV2 == null ? 0 : this.overrideApplicationToAclListV2.hashCode())) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.obfuscatedSquareId != null) {
                output.writeString(1, this.obfuscatedSquareId);
            }
            if (this.overrideApplicationToAclListV2 != null) {
                output.writeBool(2, this.overrideApplicationToAclListV2.booleanValue());
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.obfuscatedSquareId != null) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.obfuscatedSquareId);
            }
            if (this.overrideApplicationToAclListV2 != null) {
                return size + CodedOutputByteBufferNano.computeBoolSize(2, this.overrideApplicationToAclListV2.booleanValue());
            }
            return size;
        }

        public SquareTargetId mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        this.obfuscatedSquareId = input.readString();
                        continue;
                    case LogSource.GMS_CORE_PEOPLE /*16*/:
                        this.overrideApplicationToAclListV2 = Boolean.valueOf(input.readBool());
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

        public static SquareTargetId parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (SquareTargetId) MessageNano.mergeFrom(new SquareTargetId(), data);
        }

        public static SquareTargetId parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new SquareTargetId().mergeFrom(input);
        }
    }

    public static final class ZanzibarTargetId extends ExtendableMessageNano<ZanzibarTargetId> {
        private static volatile ZanzibarTargetId[] _emptyArray;
        public String applicationId;
        public String id;

        public static ZanzibarTargetId[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new ZanzibarTargetId[0];
                    }
                }
            }
            return _emptyArray;
        }

        public ZanzibarTargetId() {
            clear();
        }

        public ZanzibarTargetId clear() {
            this.id = null;
            this.applicationId = null;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof ZanzibarTargetId)) {
                return false;
            }
            ZanzibarTargetId other = (ZanzibarTargetId) o;
            if (this.id == null) {
                if (other.id != null) {
                    return false;
                }
            } else if (!this.id.equals(other.id)) {
                return false;
            }
            if (this.applicationId == null) {
                if (other.applicationId != null) {
                    return false;
                }
            } else if (!this.applicationId.equals(other.applicationId)) {
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
            int hashCode = (((((getClass().getName().hashCode() + 527) * 31) + (this.id == null ? 0 : this.id.hashCode())) * 31) + (this.applicationId == null ? 0 : this.applicationId.hashCode())) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.id != null) {
                output.writeString(1, this.id);
            }
            if (this.applicationId != null) {
                output.writeString(2, this.applicationId);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.id != null) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.id);
            }
            if (this.applicationId != null) {
                return size + CodedOutputByteBufferNano.computeStringSize(2, this.applicationId);
            }
            return size;
        }

        public ZanzibarTargetId mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        this.id = input.readString();
                        continue;
                    case LogSource.GOOGLE_ANALYTICS /*18*/:
                        this.applicationId = input.readString();
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

        public static ZanzibarTargetId parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (ZanzibarTargetId) MessageNano.mergeFrom(new ZanzibarTargetId(), data);
        }

        public static ZanzibarTargetId parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new ZanzibarTargetId().mergeFrom(input);
        }
    }
}
