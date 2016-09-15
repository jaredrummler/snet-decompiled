package com.google.android.gms.lockbox.smslog;

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

public interface SmsLog {

    public static final class SmsLogEvent extends ExtendableMessageNano<SmsLogEvent> {
        private static volatile SmsLogEvent[] _emptyArray;
        public String address;
        public String body;
        public String devicePhoneNumber;
        public int errorCode;
        public long localEventId;
        public boolean locked;
        public int messageProtocol;
        public int messageStatus;
        public int messageType;
        public int person;
        public boolean read;
        public boolean replyPathPresent;
        public boolean seen;
        public String serviceCenter;
        public String subject;
        public int threadId;
        public long timeMillis;
        public long timeSentMillis;

        public interface ErrorCode {
            public static final int ERR_TYPE_GENERIC = 1;
            public static final int ERR_TYPE_GENERIC_PERMANENT = 10;
            public static final int ERR_TYPE_MMS_PERMANENT = 12;
            public static final int ERR_TYPE_MMS_TRANSIENT = 3;
            public static final int ERR_TYPE_SMS_PERMANENT = 11;
            public static final int ERR_TYPE_SMS_TRANSIENT = 2;
            public static final int ERR_TYPE_TRANSPORT_FAILURE = 4;
            public static final int NO_ERROR = 0;
        }

        public interface MessageProtocol {
            public static final int MESSAGE_PROTOCOL_MMS = 1;
            public static final int MESSAGE_PROTOCOL_SMS = 0;
            public static final int MESSAGE_PROTOCOL_UNKNOWN = -1;
        }

        public interface MessageStatus {
            public static final int STATUS_COMPLETE = 0;
            public static final int STATUS_FAILED = 64;
            public static final int STATUS_NONE = -1;
            public static final int STATUS_PENDING = 32;
        }

        public interface MessageType {
            public static final int MESSAGE_TYPE_ALL = 0;
            public static final int MESSAGE_TYPE_DRAFT = 3;
            public static final int MESSAGE_TYPE_FAILED = 5;
            public static final int MESSAGE_TYPE_INBOX = 1;
            public static final int MESSAGE_TYPE_OUTBOX = 4;
            public static final int MESSAGE_TYPE_QUEUED = 6;
            public static final int MESSAGE_TYPE_SENT = 2;
        }

        public static SmsLogEvent[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new SmsLogEvent[0];
                    }
                }
            }
            return _emptyArray;
        }

        public SmsLogEvent() {
            clear();
        }

        public SmsLogEvent clear() {
            this.localEventId = 0;
            this.threadId = 0;
            this.address = BuildConfig.VERSION_NAME;
            this.person = 0;
            this.timeMillis = 0;
            this.timeSentMillis = 0;
            this.messageProtocol = -1;
            this.read = false;
            this.messageStatus = -1;
            this.messageType = 0;
            this.replyPathPresent = false;
            this.subject = BuildConfig.VERSION_NAME;
            this.body = BuildConfig.VERSION_NAME;
            this.serviceCenter = BuildConfig.VERSION_NAME;
            this.locked = false;
            this.errorCode = 0;
            this.seen = false;
            this.devicePhoneNumber = BuildConfig.VERSION_NAME;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof SmsLogEvent)) {
                return false;
            }
            SmsLogEvent other = (SmsLogEvent) o;
            if (this.localEventId != other.localEventId || this.threadId != other.threadId) {
                return false;
            }
            if (this.address == null) {
                if (other.address != null) {
                    return false;
                }
            } else if (!this.address.equals(other.address)) {
                return false;
            }
            if (this.person != other.person || this.timeMillis != other.timeMillis || this.timeSentMillis != other.timeSentMillis || this.messageProtocol != other.messageProtocol || this.read != other.read || this.messageStatus != other.messageStatus || this.messageType != other.messageType || this.replyPathPresent != other.replyPathPresent) {
                return false;
            }
            if (this.subject == null) {
                if (other.subject != null) {
                    return false;
                }
            } else if (!this.subject.equals(other.subject)) {
                return false;
            }
            if (this.body == null) {
                if (other.body != null) {
                    return false;
                }
            } else if (!this.body.equals(other.body)) {
                return false;
            }
            if (this.serviceCenter == null) {
                if (other.serviceCenter != null) {
                    return false;
                }
            } else if (!this.serviceCenter.equals(other.serviceCenter)) {
                return false;
            }
            if (this.locked != other.locked || this.errorCode != other.errorCode || this.seen != other.seen) {
                return false;
            }
            if (this.devicePhoneNumber == null) {
                if (other.devicePhoneNumber != null) {
                    return false;
                }
            } else if (!this.devicePhoneNumber.equals(other.devicePhoneNumber)) {
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
            int hashCode = (((((((((((((((getClass().getName().hashCode() + 527) * 31) + ((int) (this.localEventId ^ (this.localEventId >>> 32)))) * 31) + this.threadId) * 31) + (this.address == null ? 0 : this.address.hashCode())) * 31) + this.person) * 31) + ((int) (this.timeMillis ^ (this.timeMillis >>> 32)))) * 31) + ((int) (this.timeSentMillis ^ (this.timeSentMillis >>> 32)))) * 31) + this.messageProtocol) * 31;
            if (this.read) {
                i = 1231;
            } else {
                i = 1237;
            }
            hashCode = (((((hashCode + i) * 31) + this.messageStatus) * 31) + this.messageType) * 31;
            if (this.replyPathPresent) {
                i = 1231;
            } else {
                i = 1237;
            }
            hashCode = (((((((hashCode + i) * 31) + (this.subject == null ? 0 : this.subject.hashCode())) * 31) + (this.body == null ? 0 : this.body.hashCode())) * 31) + (this.serviceCenter == null ? 0 : this.serviceCenter.hashCode())) * 31;
            if (this.locked) {
                i = 1231;
            } else {
                i = 1237;
            }
            i = (((hashCode + i) * 31) + this.errorCode) * 31;
            if (!this.seen) {
                i2 = 1237;
            }
            i = (((i + i2) * 31) + (this.devicePhoneNumber == null ? 0 : this.devicePhoneNumber.hashCode())) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i3 = this.unknownFieldData.hashCode();
            }
            return i + i3;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.localEventId != 0) {
                output.writeInt64(1, this.localEventId);
            }
            if (this.threadId != 0) {
                output.writeInt32(2, this.threadId);
            }
            if (!this.address.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(3, this.address);
            }
            if (this.person != 0) {
                output.writeInt32(4, this.person);
            }
            if (this.timeMillis != 0) {
                output.writeInt64(5, this.timeMillis);
            }
            if (this.timeSentMillis != 0) {
                output.writeInt64(6, this.timeSentMillis);
            }
            if (this.messageProtocol != -1) {
                output.writeInt32(7, this.messageProtocol);
            }
            if (this.read) {
                output.writeBool(8, this.read);
            }
            if (this.messageStatus != -1) {
                output.writeInt32(9, this.messageStatus);
            }
            if (this.messageType != 0) {
                output.writeInt32(10, this.messageType);
            }
            if (this.replyPathPresent) {
                output.writeBool(11, this.replyPathPresent);
            }
            if (!this.subject.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(12, this.subject);
            }
            if (!this.body.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(13, this.body);
            }
            if (!this.serviceCenter.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(14, this.serviceCenter);
            }
            if (this.locked) {
                output.writeBool(15, this.locked);
            }
            if (this.errorCode != 0) {
                output.writeInt32(16, this.errorCode);
            }
            if (this.seen) {
                output.writeBool(17, this.seen);
            }
            if (!this.devicePhoneNumber.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(18, this.devicePhoneNumber);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.localEventId != 0) {
                size += CodedOutputByteBufferNano.computeInt64Size(1, this.localEventId);
            }
            if (this.threadId != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(2, this.threadId);
            }
            if (!this.address.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(3, this.address);
            }
            if (this.person != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(4, this.person);
            }
            if (this.timeMillis != 0) {
                size += CodedOutputByteBufferNano.computeInt64Size(5, this.timeMillis);
            }
            if (this.timeSentMillis != 0) {
                size += CodedOutputByteBufferNano.computeInt64Size(6, this.timeSentMillis);
            }
            if (this.messageProtocol != -1) {
                size += CodedOutputByteBufferNano.computeInt32Size(7, this.messageProtocol);
            }
            if (this.read) {
                size += CodedOutputByteBufferNano.computeBoolSize(8, this.read);
            }
            if (this.messageStatus != -1) {
                size += CodedOutputByteBufferNano.computeInt32Size(9, this.messageStatus);
            }
            if (this.messageType != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(10, this.messageType);
            }
            if (this.replyPathPresent) {
                size += CodedOutputByteBufferNano.computeBoolSize(11, this.replyPathPresent);
            }
            if (!this.subject.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(12, this.subject);
            }
            if (!this.body.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(13, this.body);
            }
            if (!this.serviceCenter.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(14, this.serviceCenter);
            }
            if (this.locked) {
                size += CodedOutputByteBufferNano.computeBoolSize(15, this.locked);
            }
            if (this.errorCode != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(16, this.errorCode);
            }
            if (this.seen) {
                size += CodedOutputByteBufferNano.computeBoolSize(17, this.seen);
            }
            if (this.devicePhoneNumber.equals(BuildConfig.VERSION_NAME)) {
                return size;
            }
            return size + CodedOutputByteBufferNano.computeStringSize(18, this.devicePhoneNumber);
        }

        public SmsLogEvent mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                int value;
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_GET_LINK /*8*/:
                        this.localEventId = input.readInt64();
                        continue;
                    case LogSource.GMS_CORE_PEOPLE /*16*/:
                        this.threadId = input.readInt32();
                        continue;
                    case LogSource.ANDROID_CAMERA /*26*/:
                        this.address = input.readString();
                        continue;
                    case LogSource.SOCIAL /*32*/:
                        this.person = input.readInt32();
                        continue;
                    case LogSource.COPRESENCE /*40*/:
                        this.timeMillis = input.readInt64();
                        continue;
                    case LogSource.BACKDROP /*48*/:
                        this.timeSentMillis = input.readInt64();
                        continue;
                    case LogSource.DOCS /*56*/:
                        value = input.readInt32();
                        switch (value) {
                            case LogSource.UNKNOWN /*-1*/:
                            case Action.UNKNOWN /*0*/:
                            case TimeSelection.Type.TEMPORARY /*1*/:
                                this.messageProtocol = value;
                                break;
                            default:
                                continue;
                        }
                    case LogSource.KIDS_COMMUNICATOR /*64*/:
                        this.read = input.readBool();
                        continue;
                    case LogSource.JUSTSPEAK /*72*/:
                        value = input.readInt32();
                        switch (value) {
                            case LogSource.UNKNOWN /*-1*/:
                            case Action.UNKNOWN /*0*/:
                            case LogSource.SOCIAL /*32*/:
                            case LogSource.KIDS_COMMUNICATOR /*64*/:
                                this.messageStatus = value;
                                break;
                            default:
                                continue;
                        }
                    case LogSource.CRONET_ANDROID_GSA /*80*/:
                        value = input.readInt32();
                        switch (value) {
                            case Action.UNKNOWN /*0*/:
                            case TimeSelection.Type.TEMPORARY /*1*/:
                            case TimeSelection.Type.INDEFINITELY /*2*/:
                            case TimeSelection.Type.CUSTOM /*3*/:
                            case OvenFreshResult.NO_ACCOUNTS /*4*/:
                            case Type.ADD_NEW_SHARES /*5*/:
                            case Type.REMOVE_SHARE /*6*/:
                                this.messageType = value;
                                break;
                            default:
                                continue;
                        }
                    case LogSource.EMERGENCY_ASSIST /*88*/:
                        this.replyPathPresent = input.readBool();
                        continue;
                    case LogSource.TACHYON_LOG_REQUEST /*98*/:
                        this.subject = input.readString();
                        continue;
                    case LogSource.ADSHIELD /*106*/:
                        this.body = input.readString();
                        continue;
                    case LogSource.TRANSOM /*114*/:
                        this.serviceCenter = input.readString();
                        continue;
                    case LogSource.FLYDROID /*120*/:
                        this.locked = input.readBool();
                        continue;
                    case LogSource.KEEP /*128*/:
                        value = input.readInt32();
                        switch (value) {
                            case Action.UNKNOWN /*0*/:
                            case TimeSelection.Type.TEMPORARY /*1*/:
                            case TimeSelection.Type.INDEFINITELY /*2*/:
                            case TimeSelection.Type.CUSTOM /*3*/:
                            case OvenFreshResult.NO_ACCOUNTS /*4*/:
                            case Type.TAP_ABOUT /*10*/:
                            case Type.TAP_LEARN_MORE /*11*/:
                            case Type.SWITCH_ACCOUNT /*12*/:
                                this.errorCode = value;
                                break;
                            default:
                                continue;
                        }
                    case LogSource.SAMPLE_SHM /*136*/:
                        this.seen = input.readBool();
                        continue;
                    case 146:
                        this.devicePhoneNumber = input.readString();
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

        public static SmsLogEvent parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (SmsLogEvent) MessageNano.mergeFrom(new SmsLogEvent(), data);
        }

        public static SmsLogEvent parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new SmsLogEvent().mergeFrom(input);
        }
    }
}
