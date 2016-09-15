package com.google.common.logging;

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
import java.io.IOException;

public interface DeviceContacts {

    public static final class Contact extends ExtendableMessageNano<Contact> {
        private static volatile Contact[] _emptyArray;
        public double clientScore;
        public long clientScoreDeprecated;
        public long contactId;
        public String displayName;
        public EmailAddress[] emailAddress;
        public long lastTimeContactedMs;
        public String[] nickname;
        public PhoneNumber[] phoneNumber;
        public PostalAddress[] postalAddress;
        public long timesContacted;

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
            this.contactId = 0;
            this.displayName = BuildConfig.VERSION_NAME;
            this.nickname = WireFormatNano.EMPTY_STRING_ARRAY;
            this.clientScoreDeprecated = 0;
            this.clientScore = 0.0d;
            this.phoneNumber = PhoneNumber.emptyArray();
            this.emailAddress = EmailAddress.emptyArray();
            this.postalAddress = PostalAddress.emptyArray();
            this.timesContacted = 0;
            this.lastTimeContactedMs = 0;
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
            if (this.contactId != other.contactId) {
                return false;
            }
            if (this.displayName == null) {
                if (other.displayName != null) {
                    return false;
                }
            } else if (!this.displayName.equals(other.displayName)) {
                return false;
            }
            if (!InternalNano.equals(this.nickname, other.nickname) || this.clientScoreDeprecated != other.clientScoreDeprecated || Double.doubleToLongBits(this.clientScore) != Double.doubleToLongBits(other.clientScore) || !InternalNano.equals(this.phoneNumber, other.phoneNumber) || !InternalNano.equals(this.emailAddress, other.emailAddress) || !InternalNano.equals(this.postalAddress, other.postalAddress) || this.timesContacted != other.timesContacted || this.lastTimeContactedMs != other.lastTimeContactedMs) {
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
            int hashCode = (((getClass().getName().hashCode() + 527) * 31) + ((int) (this.contactId ^ (this.contactId >>> 32)))) * 31;
            if (this.displayName == null) {
                i = 0;
            } else {
                i = this.displayName.hashCode();
            }
            int result = ((((hashCode + i) * 31) + InternalNano.hashCode(this.nickname)) * 31) + ((int) (this.clientScoreDeprecated ^ (this.clientScoreDeprecated >>> 32)));
            long v = Double.doubleToLongBits(this.clientScore);
            i = ((((((((((((result * 31) + ((int) ((v >>> 32) ^ v))) * 31) + InternalNano.hashCode(this.phoneNumber)) * 31) + InternalNano.hashCode(this.emailAddress)) * 31) + InternalNano.hashCode(this.postalAddress)) * 31) + ((int) (this.timesContacted ^ (this.timesContacted >>> 32)))) * 31) + ((int) (this.lastTimeContactedMs ^ (this.lastTimeContactedMs >>> 32)))) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i2 = this.unknownFieldData.hashCode();
            }
            return i + i2;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.contactId != 0) {
                output.writeInt64(1, this.contactId);
            }
            if (!this.displayName.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(2, this.displayName);
            }
            if (this.nickname != null && this.nickname.length > 0) {
                for (String element : this.nickname) {
                    if (element != null) {
                        output.writeString(3, element);
                    }
                }
            }
            if (this.clientScoreDeprecated != 0) {
                output.writeInt64(4, this.clientScoreDeprecated);
            }
            if (this.phoneNumber != null && this.phoneNumber.length > 0) {
                for (PhoneNumber element2 : this.phoneNumber) {
                    if (element2 != null) {
                        output.writeMessage(5, element2);
                    }
                }
            }
            if (this.emailAddress != null && this.emailAddress.length > 0) {
                for (EmailAddress element3 : this.emailAddress) {
                    if (element3 != null) {
                        output.writeMessage(6, element3);
                    }
                }
            }
            if (this.postalAddress != null && this.postalAddress.length > 0) {
                for (PostalAddress element4 : this.postalAddress) {
                    if (element4 != null) {
                        output.writeMessage(7, element4);
                    }
                }
            }
            if (Double.doubleToLongBits(this.clientScore) != Double.doubleToLongBits(0.0d)) {
                output.writeDouble(8, this.clientScore);
            }
            if (this.timesContacted != 0) {
                output.writeUInt64(9, this.timesContacted);
            }
            if (this.lastTimeContactedMs != 0) {
                output.writeUInt64(10, this.lastTimeContactedMs);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.contactId != 0) {
                size += CodedOutputByteBufferNano.computeInt64Size(1, this.contactId);
            }
            if (!this.displayName.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(2, this.displayName);
            }
            if (this.nickname != null && this.nickname.length > 0) {
                int dataCount = 0;
                int dataSize = 0;
                for (String element : this.nickname) {
                    if (element != null) {
                        dataCount++;
                        dataSize += CodedOutputByteBufferNano.computeStringSizeNoTag(element);
                    }
                }
                size = (size + dataSize) + (dataCount * 1);
            }
            if (this.clientScoreDeprecated != 0) {
                size += CodedOutputByteBufferNano.computeInt64Size(4, this.clientScoreDeprecated);
            }
            if (this.phoneNumber != null && this.phoneNumber.length > 0) {
                for (PhoneNumber element2 : this.phoneNumber) {
                    if (element2 != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(5, element2);
                    }
                }
            }
            if (this.emailAddress != null && this.emailAddress.length > 0) {
                for (EmailAddress element3 : this.emailAddress) {
                    if (element3 != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(6, element3);
                    }
                }
            }
            if (this.postalAddress != null && this.postalAddress.length > 0) {
                for (PostalAddress element4 : this.postalAddress) {
                    if (element4 != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(7, element4);
                    }
                }
            }
            if (Double.doubleToLongBits(this.clientScore) != Double.doubleToLongBits(0.0d)) {
                size += CodedOutputByteBufferNano.computeDoubleSize(8, this.clientScore);
            }
            if (this.timesContacted != 0) {
                size += CodedOutputByteBufferNano.computeUInt64Size(9, this.timesContacted);
            }
            if (this.lastTimeContactedMs != 0) {
                return size + CodedOutputByteBufferNano.computeUInt64Size(10, this.lastTimeContactedMs);
            }
            return size;
        }

        public Contact mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                int arrayLength;
                int i;
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_GET_LINK /*8*/:
                        this.contactId = input.readInt64();
                        continue;
                    case LogSource.GOOGLE_ANALYTICS /*18*/:
                        this.displayName = input.readString();
                        continue;
                    case LogSource.ANDROID_CAMERA /*26*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 26);
                        i = this.nickname == null ? 0 : this.nickname.length;
                        String[] newArray = new String[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.nickname, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = input.readString();
                            input.readTag();
                            i++;
                        }
                        newArray[i] = input.readString();
                        this.nickname = newArray;
                        continue;
                    case LogSource.SOCIAL /*32*/:
                        this.clientScoreDeprecated = input.readInt64();
                        continue;
                    case LogSource.PHOTOS /*42*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 42);
                        if (this.phoneNumber == null) {
                            i = 0;
                        } else {
                            i = this.phoneNumber.length;
                        }
                        PhoneNumber[] newArray2 = new PhoneNumber[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.phoneNumber, 0, newArray2, 0, i);
                        }
                        while (i < newArray2.length - 1) {
                            newArray2[i] = new PhoneNumber();
                            input.readMessage(newArray2[i]);
                            input.readTag();
                            i++;
                        }
                        newArray2[i] = new PhoneNumber();
                        input.readMessage(newArray2[i]);
                        this.phoneNumber = newArray2;
                        continue;
                    case LogSource.HANGOUT_LOG_REQUEST /*50*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 50);
                        if (this.emailAddress == null) {
                            i = 0;
                        } else {
                            i = this.emailAddress.length;
                        }
                        EmailAddress[] newArray3 = new EmailAddress[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.emailAddress, 0, newArray3, 0, i);
                        }
                        while (i < newArray3.length - 1) {
                            newArray3[i] = new EmailAddress();
                            input.readMessage(newArray3[i]);
                            input.readTag();
                            i++;
                        }
                        newArray3[i] = new EmailAddress();
                        input.readMessage(newArray3[i]);
                        this.emailAddress = newArray3;
                        continue;
                    case LogSource.SLIDES /*58*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 58);
                        if (this.postalAddress == null) {
                            i = 0;
                        } else {
                            i = this.postalAddress.length;
                        }
                        PostalAddress[] newArray4 = new PostalAddress[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.postalAddress, 0, newArray4, 0, i);
                        }
                        while (i < newArray4.length - 1) {
                            newArray4[i] = new PostalAddress();
                            input.readMessage(newArray4[i]);
                            input.readTag();
                            i++;
                        }
                        newArray4[i] = new PostalAddress();
                        input.readMessage(newArray4[i]);
                        this.postalAddress = newArray4;
                        continue;
                    case LogSource.WEB_STORE /*65*/:
                        this.clientScore = input.readDouble();
                        continue;
                    case LogSource.JUSTSPEAK /*72*/:
                        this.timesContacted = input.readUInt64();
                        continue;
                    case LogSource.CRONET_ANDROID_GSA /*80*/:
                        this.lastTimeContactedMs = input.readUInt64();
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

    public static final class ContactList extends ExtendableMessageNano<ContactList> {
        private static volatile ContactList[] _emptyArray;
        public String clientInstanceId;
        public Contact[] contact;
        public String contactIncarnation;
        public long[] deletedContactId;
        public boolean isIncrementalUpload;

        public static ContactList[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new ContactList[0];
                    }
                }
            }
            return _emptyArray;
        }

        public ContactList() {
            clear();
        }

        public ContactList clear() {
            this.contact = Contact.emptyArray();
            this.clientInstanceId = BuildConfig.VERSION_NAME;
            this.deletedContactId = WireFormatNano.EMPTY_LONG_ARRAY;
            this.isIncrementalUpload = false;
            this.contactIncarnation = BuildConfig.VERSION_NAME;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof ContactList)) {
                return false;
            }
            ContactList other = (ContactList) o;
            if (!InternalNano.equals(this.contact, other.contact)) {
                return false;
            }
            if (this.clientInstanceId == null) {
                if (other.clientInstanceId != null) {
                    return false;
                }
            } else if (!this.clientInstanceId.equals(other.clientInstanceId)) {
                return false;
            }
            if (!InternalNano.equals(this.deletedContactId, other.deletedContactId) || this.isIncrementalUpload != other.isIncrementalUpload) {
                return false;
            }
            if (this.contactIncarnation == null) {
                if (other.contactIncarnation != null) {
                    return false;
                }
            } else if (!this.contactIncarnation.equals(other.contactIncarnation)) {
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
            int hashCode = (((((((((((getClass().getName().hashCode() + 527) * 31) + InternalNano.hashCode(this.contact)) * 31) + (this.clientInstanceId == null ? 0 : this.clientInstanceId.hashCode())) * 31) + InternalNano.hashCode(this.deletedContactId)) * 31) + (this.isIncrementalUpload ? 1231 : 1237)) * 31) + (this.contactIncarnation == null ? 0 : this.contactIncarnation.hashCode())) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.contact != null && this.contact.length > 0) {
                for (Contact element : this.contact) {
                    if (element != null) {
                        output.writeMessage(1, element);
                    }
                }
            }
            if (!this.clientInstanceId.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(2, this.clientInstanceId);
            }
            if (this.deletedContactId != null && this.deletedContactId.length > 0) {
                for (long writeInt64 : this.deletedContactId) {
                    output.writeInt64(3, writeInt64);
                }
            }
            if (this.isIncrementalUpload) {
                output.writeBool(4, this.isIncrementalUpload);
            }
            if (!this.contactIncarnation.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(6, this.contactIncarnation);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.contact != null && this.contact.length > 0) {
                for (Contact element : this.contact) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(1, element);
                    }
                }
            }
            if (!this.clientInstanceId.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(2, this.clientInstanceId);
            }
            if (this.deletedContactId != null && this.deletedContactId.length > 0) {
                int dataSize = 0;
                for (long element2 : this.deletedContactId) {
                    dataSize += CodedOutputByteBufferNano.computeInt64SizeNoTag(element2);
                }
                size = (size + dataSize) + (this.deletedContactId.length * 1);
            }
            if (this.isIncrementalUpload) {
                size += CodedOutputByteBufferNano.computeBoolSize(4, this.isIncrementalUpload);
            }
            if (this.contactIncarnation.equals(BuildConfig.VERSION_NAME)) {
                return size;
            }
            return size + CodedOutputByteBufferNano.computeStringSize(6, this.contactIncarnation);
        }

        public ContactList mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                int arrayLength;
                int i;
                long[] newArray;
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 10);
                        if (this.contact == null) {
                            i = 0;
                        } else {
                            i = this.contact.length;
                        }
                        Contact[] newArray2 = new Contact[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.contact, 0, newArray2, 0, i);
                        }
                        while (i < newArray2.length - 1) {
                            newArray2[i] = new Contact();
                            input.readMessage(newArray2[i]);
                            input.readTag();
                            i++;
                        }
                        newArray2[i] = new Contact();
                        input.readMessage(newArray2[i]);
                        this.contact = newArray2;
                        continue;
                    case LogSource.GOOGLE_ANALYTICS /*18*/:
                        this.clientInstanceId = input.readString();
                        continue;
                    case LogSource.LB_C /*24*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 24);
                        i = this.deletedContactId == null ? 0 : this.deletedContactId.length;
                        newArray = new long[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.deletedContactId, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = input.readInt64();
                            input.readTag();
                            i++;
                        }
                        newArray[i] = input.readInt64();
                        this.deletedContactId = newArray;
                        continue;
                    case LogSource.ANDROID_CAMERA /*26*/:
                        int limit = input.pushLimit(input.readRawVarint32());
                        arrayLength = 0;
                        int startPos = input.getPosition();
                        while (input.getBytesUntilLimit() > 0) {
                            input.readInt64();
                            arrayLength++;
                        }
                        input.rewindToPosition(startPos);
                        i = this.deletedContactId == null ? 0 : this.deletedContactId.length;
                        newArray = new long[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.deletedContactId, 0, newArray, 0, i);
                        }
                        while (i < newArray.length) {
                            newArray[i] = input.readInt64();
                            i++;
                        }
                        this.deletedContactId = newArray;
                        input.popLimit(limit);
                        continue;
                    case LogSource.SOCIAL /*32*/:
                        this.isIncrementalUpload = input.readBool();
                        continue;
                    case LogSource.HANGOUT_LOG_REQUEST /*50*/:
                        this.contactIncarnation = input.readString();
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

        public static ContactList parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (ContactList) MessageNano.mergeFrom(new ContactList(), data);
        }

        public static ContactList parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new ContactList().mergeFrom(input);
        }
    }

    public static final class EmailAddress extends ExtendableMessageNano<EmailAddress> {
        private static volatile EmailAddress[] _emptyArray;
        public String label;
        public String value;

        public static EmailAddress[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new EmailAddress[0];
                    }
                }
            }
            return _emptyArray;
        }

        public EmailAddress() {
            clear();
        }

        public EmailAddress clear() {
            this.value = BuildConfig.VERSION_NAME;
            this.label = BuildConfig.VERSION_NAME;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof EmailAddress)) {
                return false;
            }
            EmailAddress other = (EmailAddress) o;
            if (this.value == null) {
                if (other.value != null) {
                    return false;
                }
            } else if (!this.value.equals(other.value)) {
                return false;
            }
            if (this.label == null) {
                if (other.label != null) {
                    return false;
                }
            } else if (!this.label.equals(other.label)) {
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
            int hashCode = (((((getClass().getName().hashCode() + 527) * 31) + (this.value == null ? 0 : this.value.hashCode())) * 31) + (this.label == null ? 0 : this.label.hashCode())) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (!this.value.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(1, this.value);
            }
            if (!this.label.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(2, this.label);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (!this.value.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.value);
            }
            if (this.label.equals(BuildConfig.VERSION_NAME)) {
                return size;
            }
            return size + CodedOutputByteBufferNano.computeStringSize(2, this.label);
        }

        public EmailAddress mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        this.value = input.readString();
                        continue;
                    case LogSource.GOOGLE_ANALYTICS /*18*/:
                        this.label = input.readString();
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

        public static EmailAddress parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (EmailAddress) MessageNano.mergeFrom(new EmailAddress(), data);
        }

        public static EmailAddress parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new EmailAddress().mergeFrom(input);
        }
    }

    public static final class PhoneNumber extends ExtendableMessageNano<PhoneNumber> {
        private static volatile PhoneNumber[] _emptyArray;
        public String label;
        public String value;

        public static PhoneNumber[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new PhoneNumber[0];
                    }
                }
            }
            return _emptyArray;
        }

        public PhoneNumber() {
            clear();
        }

        public PhoneNumber clear() {
            this.value = BuildConfig.VERSION_NAME;
            this.label = BuildConfig.VERSION_NAME;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof PhoneNumber)) {
                return false;
            }
            PhoneNumber other = (PhoneNumber) o;
            if (this.value == null) {
                if (other.value != null) {
                    return false;
                }
            } else if (!this.value.equals(other.value)) {
                return false;
            }
            if (this.label == null) {
                if (other.label != null) {
                    return false;
                }
            } else if (!this.label.equals(other.label)) {
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
            int hashCode = (((((getClass().getName().hashCode() + 527) * 31) + (this.value == null ? 0 : this.value.hashCode())) * 31) + (this.label == null ? 0 : this.label.hashCode())) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (!this.value.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(1, this.value);
            }
            if (!this.label.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(2, this.label);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (!this.value.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.value);
            }
            if (this.label.equals(BuildConfig.VERSION_NAME)) {
                return size;
            }
            return size + CodedOutputByteBufferNano.computeStringSize(2, this.label);
        }

        public PhoneNumber mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        this.value = input.readString();
                        continue;
                    case LogSource.GOOGLE_ANALYTICS /*18*/:
                        this.label = input.readString();
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

        public static PhoneNumber parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (PhoneNumber) MessageNano.mergeFrom(new PhoneNumber(), data);
        }

        public static PhoneNumber parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new PhoneNumber().mergeFrom(input);
        }
    }

    public static final class PostalAddress extends ExtendableMessageNano<PostalAddress> {
        private static volatile PostalAddress[] _emptyArray;
        public String label;
        public String value;

        public static PostalAddress[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new PostalAddress[0];
                    }
                }
            }
            return _emptyArray;
        }

        public PostalAddress() {
            clear();
        }

        public PostalAddress clear() {
            this.value = BuildConfig.VERSION_NAME;
            this.label = BuildConfig.VERSION_NAME;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof PostalAddress)) {
                return false;
            }
            PostalAddress other = (PostalAddress) o;
            if (this.value == null) {
                if (other.value != null) {
                    return false;
                }
            } else if (!this.value.equals(other.value)) {
                return false;
            }
            if (this.label == null) {
                if (other.label != null) {
                    return false;
                }
            } else if (!this.label.equals(other.label)) {
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
            int hashCode = (((((getClass().getName().hashCode() + 527) * 31) + (this.value == null ? 0 : this.value.hashCode())) * 31) + (this.label == null ? 0 : this.label.hashCode())) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (!this.value.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(1, this.value);
            }
            if (!this.label.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(2, this.label);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (!this.value.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.value);
            }
            if (this.label.equals(BuildConfig.VERSION_NAME)) {
                return size;
            }
            return size + CodedOutputByteBufferNano.computeStringSize(2, this.label);
        }

        public PostalAddress mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        this.value = input.readString();
                        continue;
                    case LogSource.GOOGLE_ANALYTICS /*18*/:
                        this.label = input.readString();
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

        public static PostalAddress parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (PostalAddress) MessageNano.mergeFrom(new PostalAddress(), data);
        }

        public static PostalAddress parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new PostalAddress().mergeFrom(input);
        }
    }
}
