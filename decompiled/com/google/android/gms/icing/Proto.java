package com.google.android.gms.icing;

import com.google.android.gms.icing.IcingDocument.Document;
import com.google.android.gms.icing.IcingDocument.SectionConfig;
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
import com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection;
import java.io.IOException;
import java.util.Arrays;

public interface Proto {

    public static final class Accounts extends ExtendableMessageNano<Accounts> {
        private static volatile Accounts[] _emptyArray;
        public Account[] account;

        public static final class Account extends ExtendableMessageNano<Account> {
            private static volatile Account[] _emptyArray;
            public String name;
            public String type;

            public static Account[] emptyArray() {
                if (_emptyArray == null) {
                    synchronized (InternalNano.LAZY_INIT_LOCK) {
                        if (_emptyArray == null) {
                            _emptyArray = new Account[0];
                        }
                    }
                }
                return _emptyArray;
            }

            public Account() {
                clear();
            }

            public Account clear() {
                this.name = BuildConfig.VERSION_NAME;
                this.type = BuildConfig.VERSION_NAME;
                this.unknownFieldData = null;
                this.cachedSize = -1;
                return this;
            }

            public boolean equals(Object o) {
                if (o == this) {
                    return true;
                }
                if (!(o instanceof Account)) {
                    return false;
                }
                Account other = (Account) o;
                if (this.name == null) {
                    if (other.name != null) {
                        return false;
                    }
                } else if (!this.name.equals(other.name)) {
                    return false;
                }
                if (this.type == null) {
                    if (other.type != null) {
                        return false;
                    }
                } else if (!this.type.equals(other.type)) {
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
                int hashCode = (((((getClass().getName().hashCode() + 527) * 31) + (this.name == null ? 0 : this.name.hashCode())) * 31) + (this.type == null ? 0 : this.type.hashCode())) * 31;
                if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                    i = this.unknownFieldData.hashCode();
                }
                return hashCode + i;
            }

            public void writeTo(CodedOutputByteBufferNano output) throws IOException {
                if (!this.name.equals(BuildConfig.VERSION_NAME)) {
                    output.writeString(1, this.name);
                }
                if (!this.type.equals(BuildConfig.VERSION_NAME)) {
                    output.writeString(2, this.type);
                }
                super.writeTo(output);
            }

            protected int computeSerializedSize() {
                int size = super.computeSerializedSize();
                if (!this.name.equals(BuildConfig.VERSION_NAME)) {
                    size += CodedOutputByteBufferNano.computeStringSize(1, this.name);
                }
                if (this.type.equals(BuildConfig.VERSION_NAME)) {
                    return size;
                }
                return size + CodedOutputByteBufferNano.computeStringSize(2, this.type);
            }

            public Account mergeFrom(CodedInputByteBufferNano input) throws IOException {
                while (true) {
                    int tag = input.readTag();
                    switch (tag) {
                        case Action.UNKNOWN /*0*/:
                            break;
                        case Type.TAP_ABOUT /*10*/:
                            this.name = input.readString();
                            continue;
                        case LogSource.GOOGLE_ANALYTICS /*18*/:
                            this.type = input.readString();
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

            public static Account parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
                return (Account) MessageNano.mergeFrom(new Account(), data);
            }

            public static Account parseFrom(CodedInputByteBufferNano input) throws IOException {
                return new Account().mergeFrom(input);
            }
        }

        public static Accounts[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new Accounts[0];
                    }
                }
            }
            return _emptyArray;
        }

        public Accounts() {
            clear();
        }

        public Accounts clear() {
            this.account = Account.emptyArray();
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof Accounts)) {
                return false;
            }
            Accounts other = (Accounts) o;
            if (!InternalNano.equals(this.account, other.account)) {
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
            int hashCode = (((getClass().getName().hashCode() + 527) * 31) + InternalNano.hashCode(this.account)) * 31;
            int hashCode2 = (this.unknownFieldData == null || this.unknownFieldData.isEmpty()) ? 0 : this.unknownFieldData.hashCode();
            return hashCode + hashCode2;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.account != null && this.account.length > 0) {
                for (Account element : this.account) {
                    if (element != null) {
                        output.writeMessage(1, element);
                    }
                }
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.account != null && this.account.length > 0) {
                for (Account element : this.account) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(1, element);
                    }
                }
            }
            return size;
        }

        public Accounts mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        int i;
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 10);
                        if (this.account == null) {
                            i = 0;
                        } else {
                            i = this.account.length;
                        }
                        Account[] newArray = new Account[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.account, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new Account();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i++;
                        }
                        newArray[i] = new Account();
                        input.readMessage(newArray[i]);
                        this.account = newArray;
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

        public static Accounts parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (Accounts) MessageNano.mergeFrom(new Accounts(), data);
        }

        public static Accounts parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new Accounts().mergeFrom(input);
        }
    }

    public static final class AppHistoryUploadStatus extends ExtendableMessageNano<AppHistoryUploadStatus> {
        private static volatile AppHistoryUploadStatus[] _emptyArray;
        public long epoch;
        public long lastSeqno;
        public long nextLocation;

        public static AppHistoryUploadStatus[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new AppHistoryUploadStatus[0];
                    }
                }
            }
            return _emptyArray;
        }

        public AppHistoryUploadStatus() {
            clear();
        }

        public AppHistoryUploadStatus clear() {
            this.epoch = 0;
            this.nextLocation = 0;
            this.lastSeqno = 0;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof AppHistoryUploadStatus)) {
                return false;
            }
            AppHistoryUploadStatus other = (AppHistoryUploadStatus) o;
            if (this.epoch != other.epoch || this.nextLocation != other.nextLocation || this.lastSeqno != other.lastSeqno) {
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
            int hashCode = (((((((getClass().getName().hashCode() + 527) * 31) + ((int) (this.epoch ^ (this.epoch >>> 32)))) * 31) + ((int) (this.nextLocation ^ (this.nextLocation >>> 32)))) * 31) + ((int) (this.lastSeqno ^ (this.lastSeqno >>> 32)))) * 31;
            int hashCode2 = (this.unknownFieldData == null || this.unknownFieldData.isEmpty()) ? 0 : this.unknownFieldData.hashCode();
            return hashCode + hashCode2;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.epoch != 0) {
                output.writeUInt64(1, this.epoch);
            }
            if (this.nextLocation != 0) {
                output.writeUInt64(2, this.nextLocation);
            }
            if (this.lastSeqno != 0) {
                output.writeInt64(3, this.lastSeqno);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.epoch != 0) {
                size += CodedOutputByteBufferNano.computeUInt64Size(1, this.epoch);
            }
            if (this.nextLocation != 0) {
                size += CodedOutputByteBufferNano.computeUInt64Size(2, this.nextLocation);
            }
            if (this.lastSeqno != 0) {
                return size + CodedOutputByteBufferNano.computeInt64Size(3, this.lastSeqno);
            }
            return size;
        }

        public AppHistoryUploadStatus mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_GET_LINK /*8*/:
                        this.epoch = input.readUInt64();
                        continue;
                    case LogSource.GMS_CORE_PEOPLE /*16*/:
                        this.nextLocation = input.readUInt64();
                        continue;
                    case LogSource.LB_C /*24*/:
                        this.lastSeqno = input.readInt64();
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

        public static AppHistoryUploadStatus parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (AppHistoryUploadStatus) MessageNano.mergeFrom(new AppHistoryUploadStatus(), data);
        }

        public static AppHistoryUploadStatus parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new AppHistoryUploadStatus().mergeFrom(input);
        }
    }

    public static final class CompactStatus extends ExtendableMessageNano<CompactStatus> {
        private static volatile CompactStatus[] _emptyArray;
        public int errorNumDocuments;
        public int numDocuments;
        public int oldNumDocuments;
        public long timestampSecs;
        public int trimmedNumDocuments;

        public static CompactStatus[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new CompactStatus[0];
                    }
                }
            }
            return _emptyArray;
        }

        public CompactStatus() {
            clear();
        }

        public CompactStatus clear() {
            this.timestampSecs = 0;
            this.numDocuments = 0;
            this.oldNumDocuments = 0;
            this.trimmedNumDocuments = 0;
            this.errorNumDocuments = 0;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof CompactStatus)) {
                return false;
            }
            CompactStatus other = (CompactStatus) o;
            if (this.timestampSecs != other.timestampSecs || this.numDocuments != other.numDocuments || this.oldNumDocuments != other.oldNumDocuments || this.trimmedNumDocuments != other.trimmedNumDocuments || this.errorNumDocuments != other.errorNumDocuments) {
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
            int hashCode = (((((((((((getClass().getName().hashCode() + 527) * 31) + ((int) (this.timestampSecs ^ (this.timestampSecs >>> 32)))) * 31) + this.numDocuments) * 31) + this.oldNumDocuments) * 31) + this.trimmedNumDocuments) * 31) + this.errorNumDocuments) * 31;
            int hashCode2 = (this.unknownFieldData == null || this.unknownFieldData.isEmpty()) ? 0 : this.unknownFieldData.hashCode();
            return hashCode + hashCode2;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.timestampSecs != 0) {
                output.writeUInt64(1, this.timestampSecs);
            }
            if (this.numDocuments != 0) {
                output.writeUInt32(2, this.numDocuments);
            }
            if (this.oldNumDocuments != 0) {
                output.writeUInt32(3, this.oldNumDocuments);
            }
            if (this.trimmedNumDocuments != 0) {
                output.writeUInt32(4, this.trimmedNumDocuments);
            }
            if (this.errorNumDocuments != 0) {
                output.writeUInt32(5, this.errorNumDocuments);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.timestampSecs != 0) {
                size += CodedOutputByteBufferNano.computeUInt64Size(1, this.timestampSecs);
            }
            if (this.numDocuments != 0) {
                size += CodedOutputByteBufferNano.computeUInt32Size(2, this.numDocuments);
            }
            if (this.oldNumDocuments != 0) {
                size += CodedOutputByteBufferNano.computeUInt32Size(3, this.oldNumDocuments);
            }
            if (this.trimmedNumDocuments != 0) {
                size += CodedOutputByteBufferNano.computeUInt32Size(4, this.trimmedNumDocuments);
            }
            if (this.errorNumDocuments != 0) {
                return size + CodedOutputByteBufferNano.computeUInt32Size(5, this.errorNumDocuments);
            }
            return size;
        }

        public CompactStatus mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_GET_LINK /*8*/:
                        this.timestampSecs = input.readUInt64();
                        continue;
                    case LogSource.GMS_CORE_PEOPLE /*16*/:
                        this.numDocuments = input.readUInt32();
                        continue;
                    case LogSource.LB_C /*24*/:
                        this.oldNumDocuments = input.readUInt32();
                        continue;
                    case LogSource.SOCIAL /*32*/:
                        this.trimmedNumDocuments = input.readUInt32();
                        continue;
                    case LogSource.COPRESENCE /*40*/:
                        this.errorNumDocuments = input.readUInt32();
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

        public static CompactStatus parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (CompactStatus) MessageNano.mergeFrom(new CompactStatus(), data);
        }

        public static CompactStatus parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new CompactStatus().mergeFrom(input);
        }
    }

    public static final class CorpusConfig extends ExtendableMessageNano<CorpusConfig> {
        private static volatile CorpusConfig[] _emptyArray;
        public String account;
        public String accountType;
        public boolean allowShortcuts;
        public String contentProviderUri;
        public int id;
        public IMEConfig imeConfig;
        public long incarnation;
        public String name;
        public String packageName;
        public int queryLimit;
        public SectionConfig[] sections;
        public String semanticType;
        public boolean semanticallySearchable;
        public int source;
        public long sourcedTime;
        public boolean trimmable;
        public UniversalSectionMapping[] universalSectionMappings;
        public long usageReportId;
        public String version;

        public static final class IMEConfig extends ExtendableMessageNano<IMEConfig> {
            private static volatile IMEConfig[] _emptyArray;
            public String dEPRECATEDIsUserInputSectionValue;
            public String isUserInputSectionName;
            public String[] isUserInputSectionValues;
            public String isUserInputTag;
            public String[] sections;
            public int sourceClass;
            public String toAddressesSectionName;

            public static IMEConfig[] emptyArray() {
                if (_emptyArray == null) {
                    synchronized (InternalNano.LAZY_INIT_LOCK) {
                        if (_emptyArray == null) {
                            _emptyArray = new IMEConfig[0];
                        }
                    }
                }
                return _emptyArray;
            }

            public IMEConfig() {
                clear();
            }

            public IMEConfig clear() {
                this.sourceClass = 0;
                this.sections = WireFormatNano.EMPTY_STRING_ARRAY;
                this.isUserInputTag = BuildConfig.VERSION_NAME;
                this.isUserInputSectionName = BuildConfig.VERSION_NAME;
                this.dEPRECATEDIsUserInputSectionValue = BuildConfig.VERSION_NAME;
                this.isUserInputSectionValues = WireFormatNano.EMPTY_STRING_ARRAY;
                this.toAddressesSectionName = BuildConfig.VERSION_NAME;
                this.unknownFieldData = null;
                this.cachedSize = -1;
                return this;
            }

            public boolean equals(Object o) {
                if (o == this) {
                    return true;
                }
                if (!(o instanceof IMEConfig)) {
                    return false;
                }
                IMEConfig other = (IMEConfig) o;
                if (this.sourceClass != other.sourceClass || !InternalNano.equals(this.sections, other.sections)) {
                    return false;
                }
                if (this.isUserInputTag == null) {
                    if (other.isUserInputTag != null) {
                        return false;
                    }
                } else if (!this.isUserInputTag.equals(other.isUserInputTag)) {
                    return false;
                }
                if (this.isUserInputSectionName == null) {
                    if (other.isUserInputSectionName != null) {
                        return false;
                    }
                } else if (!this.isUserInputSectionName.equals(other.isUserInputSectionName)) {
                    return false;
                }
                if (this.dEPRECATEDIsUserInputSectionValue == null) {
                    if (other.dEPRECATEDIsUserInputSectionValue != null) {
                        return false;
                    }
                } else if (!this.dEPRECATEDIsUserInputSectionValue.equals(other.dEPRECATEDIsUserInputSectionValue)) {
                    return false;
                }
                if (!InternalNano.equals(this.isUserInputSectionValues, other.isUserInputSectionValues)) {
                    return false;
                }
                if (this.toAddressesSectionName == null) {
                    if (other.toAddressesSectionName != null) {
                        return false;
                    }
                } else if (!this.toAddressesSectionName.equals(other.toAddressesSectionName)) {
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
                int hashCode = (((((((((((((((getClass().getName().hashCode() + 527) * 31) + this.sourceClass) * 31) + InternalNano.hashCode(this.sections)) * 31) + (this.isUserInputTag == null ? 0 : this.isUserInputTag.hashCode())) * 31) + (this.isUserInputSectionName == null ? 0 : this.isUserInputSectionName.hashCode())) * 31) + (this.dEPRECATEDIsUserInputSectionValue == null ? 0 : this.dEPRECATEDIsUserInputSectionValue.hashCode())) * 31) + InternalNano.hashCode(this.isUserInputSectionValues)) * 31) + (this.toAddressesSectionName == null ? 0 : this.toAddressesSectionName.hashCode())) * 31;
                if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                    i = this.unknownFieldData.hashCode();
                }
                return hashCode + i;
            }

            public void writeTo(CodedOutputByteBufferNano output) throws IOException {
                if (this.sourceClass != 0) {
                    output.writeInt32(1, this.sourceClass);
                }
                if (this.sections != null && this.sections.length > 0) {
                    for (String element : this.sections) {
                        if (element != null) {
                            output.writeString(2, element);
                        }
                    }
                }
                if (!this.isUserInputTag.equals(BuildConfig.VERSION_NAME)) {
                    output.writeString(3, this.isUserInputTag);
                }
                if (!this.isUserInputSectionName.equals(BuildConfig.VERSION_NAME)) {
                    output.writeString(4, this.isUserInputSectionName);
                }
                if (!this.dEPRECATEDIsUserInputSectionValue.equals(BuildConfig.VERSION_NAME)) {
                    output.writeString(5, this.dEPRECATEDIsUserInputSectionValue);
                }
                if (!this.toAddressesSectionName.equals(BuildConfig.VERSION_NAME)) {
                    output.writeString(6, this.toAddressesSectionName);
                }
                if (this.isUserInputSectionValues != null && this.isUserInputSectionValues.length > 0) {
                    for (String element2 : this.isUserInputSectionValues) {
                        if (element2 != null) {
                            output.writeString(7, element2);
                        }
                    }
                }
                super.writeTo(output);
            }

            protected int computeSerializedSize() {
                int dataCount;
                int dataSize;
                int size = super.computeSerializedSize();
                if (this.sourceClass != 0) {
                    size += CodedOutputByteBufferNano.computeInt32Size(1, this.sourceClass);
                }
                if (this.sections != null && this.sections.length > 0) {
                    dataCount = 0;
                    dataSize = 0;
                    for (String element : this.sections) {
                        if (element != null) {
                            dataCount++;
                            dataSize += CodedOutputByteBufferNano.computeStringSizeNoTag(element);
                        }
                    }
                    size = (size + dataSize) + (dataCount * 1);
                }
                if (!this.isUserInputTag.equals(BuildConfig.VERSION_NAME)) {
                    size += CodedOutputByteBufferNano.computeStringSize(3, this.isUserInputTag);
                }
                if (!this.isUserInputSectionName.equals(BuildConfig.VERSION_NAME)) {
                    size += CodedOutputByteBufferNano.computeStringSize(4, this.isUserInputSectionName);
                }
                if (!this.dEPRECATEDIsUserInputSectionValue.equals(BuildConfig.VERSION_NAME)) {
                    size += CodedOutputByteBufferNano.computeStringSize(5, this.dEPRECATEDIsUserInputSectionValue);
                }
                if (!this.toAddressesSectionName.equals(BuildConfig.VERSION_NAME)) {
                    size += CodedOutputByteBufferNano.computeStringSize(6, this.toAddressesSectionName);
                }
                if (this.isUserInputSectionValues == null || this.isUserInputSectionValues.length <= 0) {
                    return size;
                }
                dataCount = 0;
                dataSize = 0;
                for (String element2 : this.isUserInputSectionValues) {
                    if (element2 != null) {
                        dataCount++;
                        dataSize += CodedOutputByteBufferNano.computeStringSizeNoTag(element2);
                    }
                }
                return (size + dataSize) + (dataCount * 1);
            }

            public IMEConfig mergeFrom(CodedInputByteBufferNano input) throws IOException {
                while (true) {
                    int tag = input.readTag();
                    int arrayLength;
                    int i;
                    String[] newArray;
                    switch (tag) {
                        case Action.UNKNOWN /*0*/:
                            break;
                        case Type.TAP_GET_LINK /*8*/:
                            this.sourceClass = input.readInt32();
                            continue;
                        case LogSource.GOOGLE_ANALYTICS /*18*/:
                            arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 18);
                            i = this.sections == null ? 0 : this.sections.length;
                            newArray = new String[(i + arrayLength)];
                            if (i != 0) {
                                System.arraycopy(this.sections, 0, newArray, 0, i);
                            }
                            while (i < newArray.length - 1) {
                                newArray[i] = input.readString();
                                input.readTag();
                                i++;
                            }
                            newArray[i] = input.readString();
                            this.sections = newArray;
                            continue;
                        case LogSource.ANDROID_CAMERA /*26*/:
                            this.isUserInputTag = input.readString();
                            continue;
                        case LogSource.NOVA /*34*/:
                            this.isUserInputSectionName = input.readString();
                            continue;
                        case LogSource.PHOTOS /*42*/:
                            this.dEPRECATEDIsUserInputSectionValue = input.readString();
                            continue;
                        case LogSource.HANGOUT_LOG_REQUEST /*50*/:
                            this.toAddressesSectionName = input.readString();
                            continue;
                        case LogSource.SLIDES /*58*/:
                            arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 58);
                            i = this.isUserInputSectionValues == null ? 0 : this.isUserInputSectionValues.length;
                            newArray = new String[(i + arrayLength)];
                            if (i != 0) {
                                System.arraycopy(this.isUserInputSectionValues, 0, newArray, 0, i);
                            }
                            while (i < newArray.length - 1) {
                                newArray[i] = input.readString();
                                input.readTag();
                                i++;
                            }
                            newArray[i] = input.readString();
                            this.isUserInputSectionValues = newArray;
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

            public static IMEConfig parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
                return (IMEConfig) MessageNano.mergeFrom(new IMEConfig(), data);
            }

            public static IMEConfig parseFrom(CodedInputByteBufferNano input) throws IOException {
                return new IMEConfig().mergeFrom(input);
            }
        }

        public static CorpusConfig[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new CorpusConfig[0];
                    }
                }
            }
            return _emptyArray;
        }

        public CorpusConfig() {
            clear();
        }

        public CorpusConfig clear() {
            this.id = 0;
            this.name = BuildConfig.VERSION_NAME;
            this.version = BuildConfig.VERSION_NAME;
            this.packageName = BuildConfig.VERSION_NAME;
            this.contentProviderUri = BuildConfig.VERSION_NAME;
            this.queryLimit = 0;
            this.trimmable = true;
            this.incarnation = 0;
            this.usageReportId = 0;
            this.sections = SectionConfig.emptyArray();
            this.universalSectionMappings = UniversalSectionMapping.emptyArray();
            this.source = 1;
            this.allowShortcuts = false;
            this.sourcedTime = 0;
            this.accountType = BuildConfig.VERSION_NAME;
            this.account = BuildConfig.VERSION_NAME;
            this.imeConfig = null;
            this.semanticType = BuildConfig.VERSION_NAME;
            this.semanticallySearchable = false;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof CorpusConfig)) {
                return false;
            }
            CorpusConfig other = (CorpusConfig) o;
            if (this.id != other.id) {
                return false;
            }
            if (this.name == null) {
                if (other.name != null) {
                    return false;
                }
            } else if (!this.name.equals(other.name)) {
                return false;
            }
            if (this.version == null) {
                if (other.version != null) {
                    return false;
                }
            } else if (!this.version.equals(other.version)) {
                return false;
            }
            if (this.packageName == null) {
                if (other.packageName != null) {
                    return false;
                }
            } else if (!this.packageName.equals(other.packageName)) {
                return false;
            }
            if (this.contentProviderUri == null) {
                if (other.contentProviderUri != null) {
                    return false;
                }
            } else if (!this.contentProviderUri.equals(other.contentProviderUri)) {
                return false;
            }
            if (this.queryLimit != other.queryLimit || this.trimmable != other.trimmable || this.incarnation != other.incarnation || this.usageReportId != other.usageReportId || !InternalNano.equals(this.sections, other.sections) || !InternalNano.equals(this.universalSectionMappings, other.universalSectionMappings) || this.source != other.source || this.allowShortcuts != other.allowShortcuts || this.sourcedTime != other.sourcedTime) {
                return false;
            }
            if (this.accountType == null) {
                if (other.accountType != null) {
                    return false;
                }
            } else if (!this.accountType.equals(other.accountType)) {
                return false;
            }
            if (this.account == null) {
                if (other.account != null) {
                    return false;
                }
            } else if (!this.account.equals(other.account)) {
                return false;
            }
            if (this.imeConfig == null) {
                if (other.imeConfig != null) {
                    return false;
                }
            } else if (!this.imeConfig.equals(other.imeConfig)) {
                return false;
            }
            if (this.semanticType == null) {
                if (other.semanticType != null) {
                    return false;
                }
            } else if (!this.semanticType.equals(other.semanticType)) {
                return false;
            }
            if (this.semanticallySearchable != other.semanticallySearchable) {
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
            int hashCode = (((((((((((((getClass().getName().hashCode() + 527) * 31) + this.id) * 31) + (this.name == null ? 0 : this.name.hashCode())) * 31) + (this.version == null ? 0 : this.version.hashCode())) * 31) + (this.packageName == null ? 0 : this.packageName.hashCode())) * 31) + (this.contentProviderUri == null ? 0 : this.contentProviderUri.hashCode())) * 31) + this.queryLimit) * 31;
            if (this.trimmable) {
                i = 1231;
            } else {
                i = 1237;
            }
            hashCode = (((((((((((hashCode + i) * 31) + ((int) (this.incarnation ^ (this.incarnation >>> 32)))) * 31) + ((int) (this.usageReportId ^ (this.usageReportId >>> 32)))) * 31) + InternalNano.hashCode(this.sections)) * 31) + InternalNano.hashCode(this.universalSectionMappings)) * 31) + this.source) * 31;
            if (this.allowShortcuts) {
                i = 1231;
            } else {
                i = 1237;
            }
            i = (((((((((((hashCode + i) * 31) + ((int) (this.sourcedTime ^ (this.sourcedTime >>> 32)))) * 31) + (this.accountType == null ? 0 : this.accountType.hashCode())) * 31) + (this.account == null ? 0 : this.account.hashCode())) * 31) + (this.imeConfig == null ? 0 : this.imeConfig.hashCode())) * 31) + (this.semanticType == null ? 0 : this.semanticType.hashCode())) * 31;
            if (!this.semanticallySearchable) {
                i2 = 1237;
            }
            i = (i + i2) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i3 = this.unknownFieldData.hashCode();
            }
            return i + i3;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (!this.name.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(1, this.name);
            }
            if (!this.packageName.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(2, this.packageName);
            }
            if (!this.contentProviderUri.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(4, this.contentProviderUri);
            }
            if (this.queryLimit != 0) {
                output.writeInt32(5, this.queryLimit);
            }
            if (this.sections != null && this.sections.length > 0) {
                for (SectionConfig element : this.sections) {
                    if (element != null) {
                        output.writeMessage(6, element);
                    }
                }
            }
            if (this.universalSectionMappings != null && this.universalSectionMappings.length > 0) {
                for (UniversalSectionMapping element2 : this.universalSectionMappings) {
                    if (element2 != null) {
                        output.writeMessage(7, element2);
                    }
                }
            }
            if (this.id != 0) {
                output.writeInt32(8, this.id);
            }
            if (!this.version.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(9, this.version);
            }
            if (!this.trimmable) {
                output.writeBool(10, this.trimmable);
            }
            if (this.incarnation != 0) {
                output.writeUInt64(11, this.incarnation);
            }
            if (this.source != 1) {
                output.writeInt32(12, this.source);
            }
            if (this.allowShortcuts) {
                output.writeBool(13, this.allowShortcuts);
            }
            if (this.sourcedTime != 0) {
                output.writeInt64(14, this.sourcedTime);
            }
            if (this.usageReportId != 0) {
                output.writeUInt64(15, this.usageReportId);
            }
            if (!this.accountType.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(16, this.accountType);
            }
            if (!this.account.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(17, this.account);
            }
            if (this.imeConfig != null) {
                output.writeMessage(18, this.imeConfig);
            }
            if (!this.semanticType.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(19, this.semanticType);
            }
            if (this.semanticallySearchable) {
                output.writeBool(20, this.semanticallySearchable);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (!this.name.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.name);
            }
            if (!this.packageName.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(2, this.packageName);
            }
            if (!this.contentProviderUri.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(4, this.contentProviderUri);
            }
            if (this.queryLimit != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(5, this.queryLimit);
            }
            if (this.sections != null && this.sections.length > 0) {
                for (SectionConfig element : this.sections) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(6, element);
                    }
                }
            }
            if (this.universalSectionMappings != null && this.universalSectionMappings.length > 0) {
                for (UniversalSectionMapping element2 : this.universalSectionMappings) {
                    if (element2 != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(7, element2);
                    }
                }
            }
            if (this.id != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(8, this.id);
            }
            if (!this.version.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(9, this.version);
            }
            if (!this.trimmable) {
                size += CodedOutputByteBufferNano.computeBoolSize(10, this.trimmable);
            }
            if (this.incarnation != 0) {
                size += CodedOutputByteBufferNano.computeUInt64Size(11, this.incarnation);
            }
            if (this.source != 1) {
                size += CodedOutputByteBufferNano.computeInt32Size(12, this.source);
            }
            if (this.allowShortcuts) {
                size += CodedOutputByteBufferNano.computeBoolSize(13, this.allowShortcuts);
            }
            if (this.sourcedTime != 0) {
                size += CodedOutputByteBufferNano.computeInt64Size(14, this.sourcedTime);
            }
            if (this.usageReportId != 0) {
                size += CodedOutputByteBufferNano.computeUInt64Size(15, this.usageReportId);
            }
            if (!this.accountType.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(16, this.accountType);
            }
            if (!this.account.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(17, this.account);
            }
            if (this.imeConfig != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(18, this.imeConfig);
            }
            if (!this.semanticType.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(19, this.semanticType);
            }
            if (this.semanticallySearchable) {
                return size + CodedOutputByteBufferNano.computeBoolSize(20, this.semanticallySearchable);
            }
            return size;
        }

        public CorpusConfig mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                int arrayLength;
                int i;
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        this.name = input.readString();
                        continue;
                    case LogSource.GOOGLE_ANALYTICS /*18*/:
                        this.packageName = input.readString();
                        continue;
                    case LogSource.NOVA /*34*/:
                        this.contentProviderUri = input.readString();
                        continue;
                    case LogSource.COPRESENCE /*40*/:
                        this.queryLimit = input.readInt32();
                        continue;
                    case LogSource.HANGOUT_LOG_REQUEST /*50*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 50);
                        if (this.sections == null) {
                            i = 0;
                        } else {
                            i = this.sections.length;
                        }
                        SectionConfig[] newArray = new SectionConfig[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.sections, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new SectionConfig();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i++;
                        }
                        newArray[i] = new SectionConfig();
                        input.readMessage(newArray[i]);
                        this.sections = newArray;
                        continue;
                    case LogSource.SLIDES /*58*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 58);
                        if (this.universalSectionMappings == null) {
                            i = 0;
                        } else {
                            i = this.universalSectionMappings.length;
                        }
                        UniversalSectionMapping[] newArray2 = new UniversalSectionMapping[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.universalSectionMappings, 0, newArray2, 0, i);
                        }
                        while (i < newArray2.length - 1) {
                            newArray2[i] = new UniversalSectionMapping();
                            input.readMessage(newArray2[i]);
                            input.readTag();
                            i++;
                        }
                        newArray2[i] = new UniversalSectionMapping();
                        input.readMessage(newArray2[i]);
                        this.universalSectionMappings = newArray2;
                        continue;
                    case LogSource.KIDS_COMMUNICATOR /*64*/:
                        this.id = input.readInt32();
                        continue;
                    case LogSource.MOVIES /*74*/:
                        this.version = input.readString();
                        continue;
                    case LogSource.CRONET_ANDROID_GSA /*80*/:
                        this.trimmable = input.readBool();
                        continue;
                    case LogSource.EMERGENCY_ASSIST /*88*/:
                        this.incarnation = input.readUInt64();
                        continue;
                    case LogSource.GCM_COUNTERS /*96*/:
                        this.source = input.readInt32();
                        continue;
                    case LogSource.MOBILESDK_CLIENT /*104*/:
                        this.allowShortcuts = input.readBool();
                        continue;
                    case LogSource.ENDER /*112*/:
                        this.sourcedTime = input.readInt64();
                        continue;
                    case LogSource.FLYDROID /*120*/:
                        this.usageReportId = input.readUInt64();
                        continue;
                    case LogSource.CHROMECAST_APP_LOG /*130*/:
                        this.accountType = input.readString();
                        continue;
                    case LogSource.PANCETTA_MOBILE_HOST /*138*/:
                        this.account = input.readString();
                        continue;
                    case 146:
                        if (this.imeConfig == null) {
                            this.imeConfig = new IMEConfig();
                        }
                        input.readMessage(this.imeConfig);
                        continue;
                    case 154:
                        this.semanticType = input.readString();
                        continue;
                    case 160:
                        this.semanticallySearchable = input.readBool();
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

        public static CorpusConfig parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (CorpusConfig) MessageNano.mergeFrom(new CorpusConfig(), data);
        }

        public static CorpusConfig parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new CorpusConfig().mergeFrom(input);
        }
    }

    public static final class CorpusData extends ExtendableMessageNano<CorpusData> {
        private static volatile CorpusData[] _emptyArray;
        public CorpusConfig config;
        public CorpusStatusProto status;

        public static CorpusData[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new CorpusData[0];
                    }
                }
            }
            return _emptyArray;
        }

        public CorpusData() {
            clear();
        }

        public CorpusData clear() {
            this.config = null;
            this.status = null;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof CorpusData)) {
                return false;
            }
            CorpusData other = (CorpusData) o;
            if (this.config == null) {
                if (other.config != null) {
                    return false;
                }
            } else if (!this.config.equals(other.config)) {
                return false;
            }
            if (this.status == null) {
                if (other.status != null) {
                    return false;
                }
            } else if (!this.status.equals(other.status)) {
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
            int hashCode = (((((getClass().getName().hashCode() + 527) * 31) + (this.config == null ? 0 : this.config.hashCode())) * 31) + (this.status == null ? 0 : this.status.hashCode())) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.config != null) {
                output.writeMessage(1, this.config);
            }
            if (this.status != null) {
                output.writeMessage(2, this.status);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.config != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(1, this.config);
            }
            if (this.status != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(2, this.status);
            }
            return size;
        }

        public CorpusData mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        if (this.config == null) {
                            this.config = new CorpusConfig();
                        }
                        input.readMessage(this.config);
                        continue;
                    case LogSource.GOOGLE_ANALYTICS /*18*/:
                        if (this.status == null) {
                            this.status = new CorpusStatusProto();
                        }
                        input.readMessage(this.status);
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

        public static CorpusData parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (CorpusData) MessageNano.mergeFrom(new CorpusData(), data);
        }

        public static CorpusData parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new CorpusData().mergeFrom(input);
        }
    }

    public static final class CorpusStatusProto extends ExtendableMessageNano<CorpusStatusProto> {
        private static volatile CorpusStatusProto[] _emptyArray;
        public String contentIncarnation;
        public Counter[] counter;
        public long deprecatedLastCommittedSeqno;
        public long deprecatedLastSeqno;
        public int indexingRetries;
        public DocumentsSizeSnapshot lastSizeSnapshot;
        public boolean pausedForLowStorage;
        public int state;

        public static final class Counter extends ExtendableMessageNano<Counter> {
            private static volatile Counter[] _emptyArray;
            public int count;
            public String name;

            public static Counter[] emptyArray() {
                if (_emptyArray == null) {
                    synchronized (InternalNano.LAZY_INIT_LOCK) {
                        if (_emptyArray == null) {
                            _emptyArray = new Counter[0];
                        }
                    }
                }
                return _emptyArray;
            }

            public Counter() {
                clear();
            }

            public Counter clear() {
                this.name = BuildConfig.VERSION_NAME;
                this.count = 0;
                this.unknownFieldData = null;
                this.cachedSize = -1;
                return this;
            }

            public boolean equals(Object o) {
                if (o == this) {
                    return true;
                }
                if (!(o instanceof Counter)) {
                    return false;
                }
                Counter other = (Counter) o;
                if (this.name == null) {
                    if (other.name != null) {
                        return false;
                    }
                } else if (!this.name.equals(other.name)) {
                    return false;
                }
                if (this.count != other.count) {
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
                int hashCode = (((((getClass().getName().hashCode() + 527) * 31) + (this.name == null ? 0 : this.name.hashCode())) * 31) + this.count) * 31;
                if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                    i = this.unknownFieldData.hashCode();
                }
                return hashCode + i;
            }

            public void writeTo(CodedOutputByteBufferNano output) throws IOException {
                if (!this.name.equals(BuildConfig.VERSION_NAME)) {
                    output.writeString(1, this.name);
                }
                if (this.count != 0) {
                    output.writeInt32(2, this.count);
                }
                super.writeTo(output);
            }

            protected int computeSerializedSize() {
                int size = super.computeSerializedSize();
                if (!this.name.equals(BuildConfig.VERSION_NAME)) {
                    size += CodedOutputByteBufferNano.computeStringSize(1, this.name);
                }
                if (this.count != 0) {
                    return size + CodedOutputByteBufferNano.computeInt32Size(2, this.count);
                }
                return size;
            }

            public Counter mergeFrom(CodedInputByteBufferNano input) throws IOException {
                while (true) {
                    int tag = input.readTag();
                    switch (tag) {
                        case Action.UNKNOWN /*0*/:
                            break;
                        case Type.TAP_ABOUT /*10*/:
                            this.name = input.readString();
                            continue;
                        case LogSource.GMS_CORE_PEOPLE /*16*/:
                            this.count = input.readInt32();
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

            public static Counter parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
                return (Counter) MessageNano.mergeFrom(new Counter(), data);
            }

            public static Counter parseFrom(CodedInputByteBufferNano input) throws IOException {
                return new Counter().mergeFrom(input);
            }
        }

        public static final class DocumentsSizeSnapshot extends ExtendableMessageNano<DocumentsSizeSnapshot> {
            private static volatile DocumentsSizeSnapshot[] _emptyArray;
            public long documentsSize;
            public long timestampMs;

            public static DocumentsSizeSnapshot[] emptyArray() {
                if (_emptyArray == null) {
                    synchronized (InternalNano.LAZY_INIT_LOCK) {
                        if (_emptyArray == null) {
                            _emptyArray = new DocumentsSizeSnapshot[0];
                        }
                    }
                }
                return _emptyArray;
            }

            public DocumentsSizeSnapshot() {
                clear();
            }

            public DocumentsSizeSnapshot clear() {
                this.timestampMs = 0;
                this.documentsSize = 0;
                this.unknownFieldData = null;
                this.cachedSize = -1;
                return this;
            }

            public boolean equals(Object o) {
                if (o == this) {
                    return true;
                }
                if (!(o instanceof DocumentsSizeSnapshot)) {
                    return false;
                }
                DocumentsSizeSnapshot other = (DocumentsSizeSnapshot) o;
                if (this.timestampMs != other.timestampMs || this.documentsSize != other.documentsSize) {
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
                int hashCode = (((((getClass().getName().hashCode() + 527) * 31) + ((int) (this.timestampMs ^ (this.timestampMs >>> 32)))) * 31) + ((int) (this.documentsSize ^ (this.documentsSize >>> 32)))) * 31;
                int hashCode2 = (this.unknownFieldData == null || this.unknownFieldData.isEmpty()) ? 0 : this.unknownFieldData.hashCode();
                return hashCode + hashCode2;
            }

            public void writeTo(CodedOutputByteBufferNano output) throws IOException {
                if (this.timestampMs != 0) {
                    output.writeUInt64(1, this.timestampMs);
                }
                if (this.documentsSize != 0) {
                    output.writeUInt64(2, this.documentsSize);
                }
                super.writeTo(output);
            }

            protected int computeSerializedSize() {
                int size = super.computeSerializedSize();
                if (this.timestampMs != 0) {
                    size += CodedOutputByteBufferNano.computeUInt64Size(1, this.timestampMs);
                }
                if (this.documentsSize != 0) {
                    return size + CodedOutputByteBufferNano.computeUInt64Size(2, this.documentsSize);
                }
                return size;
            }

            public DocumentsSizeSnapshot mergeFrom(CodedInputByteBufferNano input) throws IOException {
                while (true) {
                    int tag = input.readTag();
                    switch (tag) {
                        case Action.UNKNOWN /*0*/:
                            break;
                        case Type.TAP_GET_LINK /*8*/:
                            this.timestampMs = input.readUInt64();
                            continue;
                        case LogSource.GMS_CORE_PEOPLE /*16*/:
                            this.documentsSize = input.readUInt64();
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

            public static DocumentsSizeSnapshot parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
                return (DocumentsSizeSnapshot) MessageNano.mergeFrom(new DocumentsSizeSnapshot(), data);
            }

            public static DocumentsSizeSnapshot parseFrom(CodedInputByteBufferNano input) throws IOException {
                return new DocumentsSizeSnapshot().mergeFrom(input);
            }
        }

        public interface State {
            public static final int ACTIVE = 0;
            public static final int INACTIVE = 2;
            public static final int LIMBO = 1;
        }

        public static CorpusStatusProto[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new CorpusStatusProto[0];
                    }
                }
            }
            return _emptyArray;
        }

        public CorpusStatusProto() {
            clear();
        }

        public CorpusStatusProto clear() {
            this.deprecatedLastSeqno = 0;
            this.deprecatedLastCommittedSeqno = 0;
            this.counter = Counter.emptyArray();
            this.state = 0;
            this.pausedForLowStorage = false;
            this.indexingRetries = 0;
            this.lastSizeSnapshot = null;
            this.contentIncarnation = BuildConfig.VERSION_NAME;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof CorpusStatusProto)) {
                return false;
            }
            CorpusStatusProto other = (CorpusStatusProto) o;
            if (this.deprecatedLastSeqno != other.deprecatedLastSeqno || this.deprecatedLastCommittedSeqno != other.deprecatedLastCommittedSeqno || !InternalNano.equals(this.counter, other.counter) || this.state != other.state || this.pausedForLowStorage != other.pausedForLowStorage || this.indexingRetries != other.indexingRetries) {
                return false;
            }
            if (this.lastSizeSnapshot == null) {
                if (other.lastSizeSnapshot != null) {
                    return false;
                }
            } else if (!this.lastSizeSnapshot.equals(other.lastSizeSnapshot)) {
                return false;
            }
            if (this.contentIncarnation == null) {
                if (other.contentIncarnation != null) {
                    return false;
                }
            } else if (!this.contentIncarnation.equals(other.contentIncarnation)) {
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
            int hashCode = (((((((((((((((((getClass().getName().hashCode() + 527) * 31) + ((int) (this.deprecatedLastSeqno ^ (this.deprecatedLastSeqno >>> 32)))) * 31) + ((int) (this.deprecatedLastCommittedSeqno ^ (this.deprecatedLastCommittedSeqno >>> 32)))) * 31) + InternalNano.hashCode(this.counter)) * 31) + this.state) * 31) + (this.pausedForLowStorage ? 1231 : 1237)) * 31) + this.indexingRetries) * 31) + (this.lastSizeSnapshot == null ? 0 : this.lastSizeSnapshot.hashCode())) * 31) + (this.contentIncarnation == null ? 0 : this.contentIncarnation.hashCode())) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.deprecatedLastSeqno != 0) {
                output.writeInt64(1, this.deprecatedLastSeqno);
            }
            if (this.deprecatedLastCommittedSeqno != 0) {
                output.writeInt64(2, this.deprecatedLastCommittedSeqno);
            }
            if (this.counter != null && this.counter.length > 0) {
                for (Counter element : this.counter) {
                    if (element != null) {
                        output.writeMessage(3, element);
                    }
                }
            }
            if (this.state != 0) {
                output.writeInt32(4, this.state);
            }
            if (this.pausedForLowStorage) {
                output.writeBool(5, this.pausedForLowStorage);
            }
            if (this.indexingRetries != 0) {
                output.writeInt32(6, this.indexingRetries);
            }
            if (this.lastSizeSnapshot != null) {
                output.writeMessage(7, this.lastSizeSnapshot);
            }
            if (!this.contentIncarnation.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(8, this.contentIncarnation);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.deprecatedLastSeqno != 0) {
                size += CodedOutputByteBufferNano.computeInt64Size(1, this.deprecatedLastSeqno);
            }
            if (this.deprecatedLastCommittedSeqno != 0) {
                size += CodedOutputByteBufferNano.computeInt64Size(2, this.deprecatedLastCommittedSeqno);
            }
            if (this.counter != null && this.counter.length > 0) {
                for (Counter element : this.counter) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(3, element);
                    }
                }
            }
            if (this.state != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(4, this.state);
            }
            if (this.pausedForLowStorage) {
                size += CodedOutputByteBufferNano.computeBoolSize(5, this.pausedForLowStorage);
            }
            if (this.indexingRetries != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(6, this.indexingRetries);
            }
            if (this.lastSizeSnapshot != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(7, this.lastSizeSnapshot);
            }
            if (this.contentIncarnation.equals(BuildConfig.VERSION_NAME)) {
                return size;
            }
            return size + CodedOutputByteBufferNano.computeStringSize(8, this.contentIncarnation);
        }

        public CorpusStatusProto mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_GET_LINK /*8*/:
                        this.deprecatedLastSeqno = input.readInt64();
                        continue;
                    case LogSource.GMS_CORE_PEOPLE /*16*/:
                        this.deprecatedLastCommittedSeqno = input.readInt64();
                        continue;
                    case LogSource.ANDROID_CAMERA /*26*/:
                        int i;
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 26);
                        if (this.counter == null) {
                            i = 0;
                        } else {
                            i = this.counter.length;
                        }
                        Counter[] newArray = new Counter[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.counter, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new Counter();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i++;
                        }
                        newArray[i] = new Counter();
                        input.readMessage(newArray[i]);
                        this.counter = newArray;
                        continue;
                    case LogSource.SOCIAL /*32*/:
                        int value = input.readInt32();
                        switch (value) {
                            case Action.UNKNOWN /*0*/:
                            case TimeSelection.Type.TEMPORARY /*1*/:
                            case TimeSelection.Type.INDEFINITELY /*2*/:
                                this.state = value;
                                break;
                            default:
                                continue;
                        }
                    case LogSource.COPRESENCE /*40*/:
                        this.pausedForLowStorage = input.readBool();
                        continue;
                    case LogSource.BACKDROP /*48*/:
                        this.indexingRetries = input.readInt32();
                        continue;
                    case LogSource.SLIDES /*58*/:
                        if (this.lastSizeSnapshot == null) {
                            this.lastSizeSnapshot = new DocumentsSizeSnapshot();
                        }
                        input.readMessage(this.lastSizeSnapshot);
                        continue;
                    case LogSource.WIFI_ASSISTANT /*66*/:
                        this.contentIncarnation = input.readString();
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

        public static CorpusStatusProto parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (CorpusStatusProto) MessageNano.mergeFrom(new CorpusStatusProto(), data);
        }

        public static CorpusStatusProto parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new CorpusStatusProto().mergeFrom(input);
        }
    }

    public static final class CorpusVolatileData extends ExtendableMessageNano<CorpusVolatileData> {
        private static volatile CorpusVolatileData[] _emptyArray;
        public int corpusId;
        public Counter[] counter;
        public int indexingRetries;

        public static CorpusVolatileData[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new CorpusVolatileData[0];
                    }
                }
            }
            return _emptyArray;
        }

        public CorpusVolatileData() {
            clear();
        }

        public CorpusVolatileData clear() {
            this.corpusId = 0;
            this.indexingRetries = 0;
            this.counter = Counter.emptyArray();
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof CorpusVolatileData)) {
                return false;
            }
            CorpusVolatileData other = (CorpusVolatileData) o;
            if (this.corpusId != other.corpusId || this.indexingRetries != other.indexingRetries || !InternalNano.equals(this.counter, other.counter)) {
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
            int hashCode = (((((((getClass().getName().hashCode() + 527) * 31) + this.corpusId) * 31) + this.indexingRetries) * 31) + InternalNano.hashCode(this.counter)) * 31;
            int hashCode2 = (this.unknownFieldData == null || this.unknownFieldData.isEmpty()) ? 0 : this.unknownFieldData.hashCode();
            return hashCode + hashCode2;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.corpusId != 0) {
                output.writeInt32(1, this.corpusId);
            }
            if (this.indexingRetries != 0) {
                output.writeInt32(2, this.indexingRetries);
            }
            if (this.counter != null && this.counter.length > 0) {
                for (Counter element : this.counter) {
                    if (element != null) {
                        output.writeMessage(3, element);
                    }
                }
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.corpusId != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(1, this.corpusId);
            }
            if (this.indexingRetries != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(2, this.indexingRetries);
            }
            if (this.counter != null && this.counter.length > 0) {
                for (Counter element : this.counter) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(3, element);
                    }
                }
            }
            return size;
        }

        public CorpusVolatileData mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_GET_LINK /*8*/:
                        this.corpusId = input.readInt32();
                        continue;
                    case LogSource.GMS_CORE_PEOPLE /*16*/:
                        this.indexingRetries = input.readInt32();
                        continue;
                    case LogSource.ANDROID_CAMERA /*26*/:
                        int i;
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 26);
                        if (this.counter == null) {
                            i = 0;
                        } else {
                            i = this.counter.length;
                        }
                        Counter[] newArray = new Counter[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.counter, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new Counter();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i++;
                        }
                        newArray[i] = new Counter();
                        input.readMessage(newArray[i]);
                        this.counter = newArray;
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

        public static CorpusVolatileData parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (CorpusVolatileData) MessageNano.mergeFrom(new CorpusVolatileData(), data);
        }

        public static CorpusVolatileData parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new CorpusVolatileData().mergeFrom(input);
        }
    }

    public static final class DEPRECATED_FlushStatus extends ExtendableMessageNano<DEPRECATED_FlushStatus> {
        private static volatile DEPRECATED_FlushStatus[] _emptyArray;
        public long commitTimestampSecs;
        public long deprecatedOplogCommittedLen;
        public long docstoreSize;
        public int numDocuments;
        public int numUris;
        public long timestampSecs;

        public static DEPRECATED_FlushStatus[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new DEPRECATED_FlushStatus[0];
                    }
                }
            }
            return _emptyArray;
        }

        public DEPRECATED_FlushStatus() {
            clear();
        }

        public DEPRECATED_FlushStatus clear() {
            this.numDocuments = 0;
            this.docstoreSize = 0;
            this.numUris = 0;
            this.timestampSecs = 0;
            this.commitTimestampSecs = 0;
            this.deprecatedOplogCommittedLen = 0;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof DEPRECATED_FlushStatus)) {
                return false;
            }
            DEPRECATED_FlushStatus other = (DEPRECATED_FlushStatus) o;
            if (this.numDocuments != other.numDocuments || this.docstoreSize != other.docstoreSize || this.numUris != other.numUris || this.timestampSecs != other.timestampSecs || this.commitTimestampSecs != other.commitTimestampSecs || this.deprecatedOplogCommittedLen != other.deprecatedOplogCommittedLen) {
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
            int hashCode = (((((((((((((getClass().getName().hashCode() + 527) * 31) + this.numDocuments) * 31) + ((int) (this.docstoreSize ^ (this.docstoreSize >>> 32)))) * 31) + this.numUris) * 31) + ((int) (this.timestampSecs ^ (this.timestampSecs >>> 32)))) * 31) + ((int) (this.commitTimestampSecs ^ (this.commitTimestampSecs >>> 32)))) * 31) + ((int) (this.deprecatedOplogCommittedLen ^ (this.deprecatedOplogCommittedLen >>> 32)))) * 31;
            int hashCode2 = (this.unknownFieldData == null || this.unknownFieldData.isEmpty()) ? 0 : this.unknownFieldData.hashCode();
            return hashCode + hashCode2;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.numDocuments != 0) {
                output.writeUInt32(1, this.numDocuments);
            }
            if (this.docstoreSize != 0) {
                output.writeUInt64(2, this.docstoreSize);
            }
            if (this.numUris != 0) {
                output.writeUInt32(3, this.numUris);
            }
            if (this.timestampSecs != 0) {
                output.writeUInt64(4, this.timestampSecs);
            }
            if (this.commitTimestampSecs != 0) {
                output.writeUInt64(5, this.commitTimestampSecs);
            }
            if (this.deprecatedOplogCommittedLen != 0) {
                output.writeUInt64(6, this.deprecatedOplogCommittedLen);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.numDocuments != 0) {
                size += CodedOutputByteBufferNano.computeUInt32Size(1, this.numDocuments);
            }
            if (this.docstoreSize != 0) {
                size += CodedOutputByteBufferNano.computeUInt64Size(2, this.docstoreSize);
            }
            if (this.numUris != 0) {
                size += CodedOutputByteBufferNano.computeUInt32Size(3, this.numUris);
            }
            if (this.timestampSecs != 0) {
                size += CodedOutputByteBufferNano.computeUInt64Size(4, this.timestampSecs);
            }
            if (this.commitTimestampSecs != 0) {
                size += CodedOutputByteBufferNano.computeUInt64Size(5, this.commitTimestampSecs);
            }
            if (this.deprecatedOplogCommittedLen != 0) {
                return size + CodedOutputByteBufferNano.computeUInt64Size(6, this.deprecatedOplogCommittedLen);
            }
            return size;
        }

        public DEPRECATED_FlushStatus mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_GET_LINK /*8*/:
                        this.numDocuments = input.readUInt32();
                        continue;
                    case LogSource.GMS_CORE_PEOPLE /*16*/:
                        this.docstoreSize = input.readUInt64();
                        continue;
                    case LogSource.LB_C /*24*/:
                        this.numUris = input.readUInt32();
                        continue;
                    case LogSource.SOCIAL /*32*/:
                        this.timestampSecs = input.readUInt64();
                        continue;
                    case LogSource.COPRESENCE /*40*/:
                        this.commitTimestampSecs = input.readUInt64();
                        continue;
                    case LogSource.BACKDROP /*48*/:
                        this.deprecatedOplogCommittedLen = input.readUInt64();
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

        public static DEPRECATED_FlushStatus parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (DEPRECATED_FlushStatus) MessageNano.mergeFrom(new DEPRECATED_FlushStatus(), data);
        }

        public static DEPRECATED_FlushStatus parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new DEPRECATED_FlushStatus().mergeFrom(input);
        }
    }

    public static final class DocumentStoreStatusProto extends ExtendableMessageNano<DocumentStoreStatusProto> {
        private static volatile DocumentStoreStatusProto[] _emptyArray;
        public int allCrc;
        public CorpusInfo[] corpusInfo;
        public long docStoreSize;
        public long lastFlushedTimestampSecs;
        public long minUsageReportsCurSeqno;
        public int numDocuments;
        public long usageReportsEpoch;

        public static final class CorpusInfo extends ExtendableMessageNano<CorpusInfo> {
            private static volatile CorpusInfo[] _emptyArray;
            public long documentsSize;
            public boolean isDeleted;
            public long lastSeqno;
            public IndexCorpusScoringConfig scoringConfig;
            public long usageCorpusId;

            public static CorpusInfo[] emptyArray() {
                if (_emptyArray == null) {
                    synchronized (InternalNano.LAZY_INIT_LOCK) {
                        if (_emptyArray == null) {
                            _emptyArray = new CorpusInfo[0];
                        }
                    }
                }
                return _emptyArray;
            }

            public CorpusInfo() {
                clear();
            }

            public CorpusInfo clear() {
                this.lastSeqno = 0;
                this.usageCorpusId = 0;
                this.documentsSize = 0;
                this.isDeleted = false;
                this.scoringConfig = null;
                this.unknownFieldData = null;
                this.cachedSize = -1;
                return this;
            }

            public boolean equals(Object o) {
                if (o == this) {
                    return true;
                }
                if (!(o instanceof CorpusInfo)) {
                    return false;
                }
                CorpusInfo other = (CorpusInfo) o;
                if (this.lastSeqno != other.lastSeqno || this.usageCorpusId != other.usageCorpusId || this.documentsSize != other.documentsSize || this.isDeleted != other.isDeleted) {
                    return false;
                }
                if (this.scoringConfig == null) {
                    if (other.scoringConfig != null) {
                        return false;
                    }
                } else if (!this.scoringConfig.equals(other.scoringConfig)) {
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
                int hashCode = (((((((((((getClass().getName().hashCode() + 527) * 31) + ((int) (this.lastSeqno ^ (this.lastSeqno >>> 32)))) * 31) + ((int) (this.usageCorpusId ^ (this.usageCorpusId >>> 32)))) * 31) + ((int) (this.documentsSize ^ (this.documentsSize >>> 32)))) * 31) + (this.isDeleted ? 1231 : 1237)) * 31) + (this.scoringConfig == null ? 0 : this.scoringConfig.hashCode())) * 31;
                if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                    i = this.unknownFieldData.hashCode();
                }
                return hashCode + i;
            }

            public void writeTo(CodedOutputByteBufferNano output) throws IOException {
                if (this.lastSeqno != 0) {
                    output.writeInt64(1, this.lastSeqno);
                }
                if (this.usageCorpusId != 0) {
                    output.writeUInt64(2, this.usageCorpusId);
                }
                if (this.documentsSize != 0) {
                    output.writeUInt64(3, this.documentsSize);
                }
                if (this.isDeleted) {
                    output.writeBool(4, this.isDeleted);
                }
                if (this.scoringConfig != null) {
                    output.writeMessage(5, this.scoringConfig);
                }
                super.writeTo(output);
            }

            protected int computeSerializedSize() {
                int size = super.computeSerializedSize();
                if (this.lastSeqno != 0) {
                    size += CodedOutputByteBufferNano.computeInt64Size(1, this.lastSeqno);
                }
                if (this.usageCorpusId != 0) {
                    size += CodedOutputByteBufferNano.computeUInt64Size(2, this.usageCorpusId);
                }
                if (this.documentsSize != 0) {
                    size += CodedOutputByteBufferNano.computeUInt64Size(3, this.documentsSize);
                }
                if (this.isDeleted) {
                    size += CodedOutputByteBufferNano.computeBoolSize(4, this.isDeleted);
                }
                if (this.scoringConfig != null) {
                    return size + CodedOutputByteBufferNano.computeMessageSize(5, this.scoringConfig);
                }
                return size;
            }

            public CorpusInfo mergeFrom(CodedInputByteBufferNano input) throws IOException {
                while (true) {
                    int tag = input.readTag();
                    switch (tag) {
                        case Action.UNKNOWN /*0*/:
                            break;
                        case Type.TAP_GET_LINK /*8*/:
                            this.lastSeqno = input.readInt64();
                            continue;
                        case LogSource.GMS_CORE_PEOPLE /*16*/:
                            this.usageCorpusId = input.readUInt64();
                            continue;
                        case LogSource.LB_C /*24*/:
                            this.documentsSize = input.readUInt64();
                            continue;
                        case LogSource.SOCIAL /*32*/:
                            this.isDeleted = input.readBool();
                            continue;
                        case LogSource.PHOTOS /*42*/:
                            if (this.scoringConfig == null) {
                                this.scoringConfig = new IndexCorpusScoringConfig();
                            }
                            input.readMessage(this.scoringConfig);
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

            public static CorpusInfo parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
                return (CorpusInfo) MessageNano.mergeFrom(new CorpusInfo(), data);
            }

            public static CorpusInfo parseFrom(CodedInputByteBufferNano input) throws IOException {
                return new CorpusInfo().mergeFrom(input);
            }
        }

        public static DocumentStoreStatusProto[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new DocumentStoreStatusProto[0];
                    }
                }
            }
            return _emptyArray;
        }

        public DocumentStoreStatusProto() {
            clear();
        }

        public DocumentStoreStatusProto clear() {
            this.lastFlushedTimestampSecs = 0;
            this.corpusInfo = CorpusInfo.emptyArray();
            this.allCrc = 0;
            this.numDocuments = 0;
            this.docStoreSize = 0;
            this.minUsageReportsCurSeqno = 0;
            this.usageReportsEpoch = 0;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof DocumentStoreStatusProto)) {
                return false;
            }
            DocumentStoreStatusProto other = (DocumentStoreStatusProto) o;
            if (this.lastFlushedTimestampSecs != other.lastFlushedTimestampSecs || !InternalNano.equals(this.corpusInfo, other.corpusInfo) || this.allCrc != other.allCrc || this.numDocuments != other.numDocuments || this.docStoreSize != other.docStoreSize || this.minUsageReportsCurSeqno != other.minUsageReportsCurSeqno || this.usageReportsEpoch != other.usageReportsEpoch) {
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
            int hashCode = (((((((((((((((getClass().getName().hashCode() + 527) * 31) + ((int) (this.lastFlushedTimestampSecs ^ (this.lastFlushedTimestampSecs >>> 32)))) * 31) + InternalNano.hashCode(this.corpusInfo)) * 31) + this.allCrc) * 31) + this.numDocuments) * 31) + ((int) (this.docStoreSize ^ (this.docStoreSize >>> 32)))) * 31) + ((int) (this.minUsageReportsCurSeqno ^ (this.minUsageReportsCurSeqno >>> 32)))) * 31) + ((int) (this.usageReportsEpoch ^ (this.usageReportsEpoch >>> 32)))) * 31;
            int hashCode2 = (this.unknownFieldData == null || this.unknownFieldData.isEmpty()) ? 0 : this.unknownFieldData.hashCode();
            return hashCode + hashCode2;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.lastFlushedTimestampSecs != 0) {
                output.writeUInt64(2, this.lastFlushedTimestampSecs);
            }
            if (this.corpusInfo != null && this.corpusInfo.length > 0) {
                for (CorpusInfo element : this.corpusInfo) {
                    if (element != null) {
                        output.writeMessage(3, element);
                    }
                }
            }
            if (this.allCrc != 0) {
                output.writeFixed32(4, this.allCrc);
            }
            if (this.numDocuments != 0) {
                output.writeUInt32(5, this.numDocuments);
            }
            if (this.docStoreSize != 0) {
                output.writeUInt64(6, this.docStoreSize);
            }
            if (this.minUsageReportsCurSeqno != 0) {
                output.writeInt64(7, this.minUsageReportsCurSeqno);
            }
            if (this.usageReportsEpoch != 0) {
                output.writeUInt64(8, this.usageReportsEpoch);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.lastFlushedTimestampSecs != 0) {
                size += CodedOutputByteBufferNano.computeUInt64Size(2, this.lastFlushedTimestampSecs);
            }
            if (this.corpusInfo != null && this.corpusInfo.length > 0) {
                for (CorpusInfo element : this.corpusInfo) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(3, element);
                    }
                }
            }
            if (this.allCrc != 0) {
                size += CodedOutputByteBufferNano.computeFixed32Size(4, this.allCrc);
            }
            if (this.numDocuments != 0) {
                size += CodedOutputByteBufferNano.computeUInt32Size(5, this.numDocuments);
            }
            if (this.docStoreSize != 0) {
                size += CodedOutputByteBufferNano.computeUInt64Size(6, this.docStoreSize);
            }
            if (this.minUsageReportsCurSeqno != 0) {
                size += CodedOutputByteBufferNano.computeInt64Size(7, this.minUsageReportsCurSeqno);
            }
            if (this.usageReportsEpoch != 0) {
                return size + CodedOutputByteBufferNano.computeUInt64Size(8, this.usageReportsEpoch);
            }
            return size;
        }

        public DocumentStoreStatusProto mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case LogSource.GMS_CORE_PEOPLE /*16*/:
                        this.lastFlushedTimestampSecs = input.readUInt64();
                        continue;
                    case LogSource.ANDROID_CAMERA /*26*/:
                        int i;
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 26);
                        if (this.corpusInfo == null) {
                            i = 0;
                        } else {
                            i = this.corpusInfo.length;
                        }
                        CorpusInfo[] newArray = new CorpusInfo[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.corpusInfo, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new CorpusInfo();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i++;
                        }
                        newArray[i] = new CorpusInfo();
                        input.readMessage(newArray[i]);
                        this.corpusInfo = newArray;
                        continue;
                    case LogSource.PERSONAL_BROWSER_LOGGER /*37*/:
                        this.allCrc = input.readFixed32();
                        continue;
                    case LogSource.COPRESENCE /*40*/:
                        this.numDocuments = input.readUInt32();
                        continue;
                    case LogSource.BACKDROP /*48*/:
                        this.docStoreSize = input.readUInt64();
                        continue;
                    case LogSource.DOCS /*56*/:
                        this.minUsageReportsCurSeqno = input.readInt64();
                        continue;
                    case LogSource.KIDS_COMMUNICATOR /*64*/:
                        this.usageReportsEpoch = input.readUInt64();
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

        public static DocumentStoreStatusProto parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (DocumentStoreStatusProto) MessageNano.mergeFrom(new DocumentStoreStatusProto(), data);
        }

        public static DocumentStoreStatusProto parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new DocumentStoreStatusProto().mergeFrom(input);
        }
    }

    public static final class DynamicTrieHeader extends ExtendableMessageNano<DynamicTrieHeader> {
        private static volatile DynamicTrieHeader[] _emptyArray;
        public boolean deprecatedIsFlushing;
        public int[] freeLists;
        public int maxNexts;
        public int maxNodes;
        public int maxSuffixesSize;
        public int numKeys;
        public int numNexts;
        public int numNodes;
        public int suffixesSize;
        public int valueSize;
        public int version;

        public static DynamicTrieHeader[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new DynamicTrieHeader[0];
                    }
                }
            }
            return _emptyArray;
        }

        public DynamicTrieHeader() {
            clear();
        }

        public DynamicTrieHeader clear() {
            this.version = 0;
            this.valueSize = 0;
            this.maxNodes = 0;
            this.maxNexts = 0;
            this.maxSuffixesSize = 0;
            this.numNodes = 0;
            this.numNexts = 0;
            this.suffixesSize = 0;
            this.freeLists = WireFormatNano.EMPTY_INT_ARRAY;
            this.numKeys = 0;
            this.deprecatedIsFlushing = false;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof DynamicTrieHeader)) {
                return false;
            }
            DynamicTrieHeader other = (DynamicTrieHeader) o;
            if (this.version != other.version || this.valueSize != other.valueSize || this.maxNodes != other.maxNodes || this.maxNexts != other.maxNexts || this.maxSuffixesSize != other.maxSuffixesSize || this.numNodes != other.numNodes || this.numNexts != other.numNexts || this.suffixesSize != other.suffixesSize || !InternalNano.equals(this.freeLists, other.freeLists) || this.numKeys != other.numKeys || this.deprecatedIsFlushing != other.deprecatedIsFlushing) {
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
            int hashCode = (((((((((((((((((((((((getClass().getName().hashCode() + 527) * 31) + this.version) * 31) + this.valueSize) * 31) + this.maxNodes) * 31) + this.maxNexts) * 31) + this.maxSuffixesSize) * 31) + this.numNodes) * 31) + this.numNexts) * 31) + this.suffixesSize) * 31) + InternalNano.hashCode(this.freeLists)) * 31) + this.numKeys) * 31) + (this.deprecatedIsFlushing ? 1231 : 1237)) * 31;
            int hashCode2 = (this.unknownFieldData == null || this.unknownFieldData.isEmpty()) ? 0 : this.unknownFieldData.hashCode();
            return hashCode + hashCode2;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.version != 0) {
                output.writeUInt32(1, this.version);
            }
            if (this.valueSize != 0) {
                output.writeUInt32(2, this.valueSize);
            }
            if (this.maxNodes != 0) {
                output.writeUInt32(3, this.maxNodes);
            }
            if (this.maxNexts != 0) {
                output.writeUInt32(4, this.maxNexts);
            }
            if (this.maxSuffixesSize != 0) {
                output.writeUInt32(5, this.maxSuffixesSize);
            }
            if (this.numNodes != 0) {
                output.writeUInt32(6, this.numNodes);
            }
            if (this.numNexts != 0) {
                output.writeUInt32(7, this.numNexts);
            }
            if (this.suffixesSize != 0) {
                output.writeUInt32(8, this.suffixesSize);
            }
            if (this.freeLists != null && this.freeLists.length > 0) {
                for (int writeUInt32 : this.freeLists) {
                    output.writeUInt32(9, writeUInt32);
                }
            }
            if (this.numKeys != 0) {
                output.writeUInt32(10, this.numKeys);
            }
            if (this.deprecatedIsFlushing) {
                output.writeBool(11, this.deprecatedIsFlushing);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.version != 0) {
                size += CodedOutputByteBufferNano.computeUInt32Size(1, this.version);
            }
            if (this.valueSize != 0) {
                size += CodedOutputByteBufferNano.computeUInt32Size(2, this.valueSize);
            }
            if (this.maxNodes != 0) {
                size += CodedOutputByteBufferNano.computeUInt32Size(3, this.maxNodes);
            }
            if (this.maxNexts != 0) {
                size += CodedOutputByteBufferNano.computeUInt32Size(4, this.maxNexts);
            }
            if (this.maxSuffixesSize != 0) {
                size += CodedOutputByteBufferNano.computeUInt32Size(5, this.maxSuffixesSize);
            }
            if (this.numNodes != 0) {
                size += CodedOutputByteBufferNano.computeUInt32Size(6, this.numNodes);
            }
            if (this.numNexts != 0) {
                size += CodedOutputByteBufferNano.computeUInt32Size(7, this.numNexts);
            }
            if (this.suffixesSize != 0) {
                size += CodedOutputByteBufferNano.computeUInt32Size(8, this.suffixesSize);
            }
            if (this.freeLists != null && this.freeLists.length > 0) {
                int dataSize = 0;
                for (int element : this.freeLists) {
                    dataSize += CodedOutputByteBufferNano.computeUInt32SizeNoTag(element);
                }
                size = (size + dataSize) + (this.freeLists.length * 1);
            }
            if (this.numKeys != 0) {
                size += CodedOutputByteBufferNano.computeUInt32Size(10, this.numKeys);
            }
            if (this.deprecatedIsFlushing) {
                return size + CodedOutputByteBufferNano.computeBoolSize(11, this.deprecatedIsFlushing);
            }
            return size;
        }

        public DynamicTrieHeader mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                int arrayLength;
                int i;
                int[] newArray;
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_GET_LINK /*8*/:
                        this.version = input.readUInt32();
                        continue;
                    case LogSource.GMS_CORE_PEOPLE /*16*/:
                        this.valueSize = input.readUInt32();
                        continue;
                    case LogSource.LB_C /*24*/:
                        this.maxNodes = input.readUInt32();
                        continue;
                    case LogSource.SOCIAL /*32*/:
                        this.maxNexts = input.readUInt32();
                        continue;
                    case LogSource.COPRESENCE /*40*/:
                        this.maxSuffixesSize = input.readUInt32();
                        continue;
                    case LogSource.BACKDROP /*48*/:
                        this.numNodes = input.readUInt32();
                        continue;
                    case LogSource.DOCS /*56*/:
                        this.numNexts = input.readUInt32();
                        continue;
                    case LogSource.KIDS_COMMUNICATOR /*64*/:
                        this.suffixesSize = input.readUInt32();
                        continue;
                    case LogSource.JUSTSPEAK /*72*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 72);
                        i = this.freeLists == null ? 0 : this.freeLists.length;
                        newArray = new int[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.freeLists, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = input.readUInt32();
                            input.readTag();
                            i++;
                        }
                        newArray[i] = input.readUInt32();
                        this.freeLists = newArray;
                        continue;
                    case LogSource.MOVIES /*74*/:
                        int limit = input.pushLimit(input.readRawVarint32());
                        arrayLength = 0;
                        int startPos = input.getPosition();
                        while (input.getBytesUntilLimit() > 0) {
                            input.readUInt32();
                            arrayLength++;
                        }
                        input.rewindToPosition(startPos);
                        i = this.freeLists == null ? 0 : this.freeLists.length;
                        newArray = new int[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.freeLists, 0, newArray, 0, i);
                        }
                        while (i < newArray.length) {
                            newArray[i] = input.readUInt32();
                            i++;
                        }
                        this.freeLists = newArray;
                        input.popLimit(limit);
                        continue;
                    case LogSource.CRONET_ANDROID_GSA /*80*/:
                        this.numKeys = input.readUInt32();
                        continue;
                    case LogSource.EMERGENCY_ASSIST /*88*/:
                        this.deprecatedIsFlushing = input.readBool();
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

        public static DynamicTrieHeader parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (DynamicTrieHeader) MessageNano.mergeFrom(new DynamicTrieHeader(), data);
        }

        public static DynamicTrieHeader parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new DynamicTrieHeader().mergeFrom(input);
        }
    }

    public static final class Enums extends ExtendableMessageNano<Enums> {
        private static volatile Enums[] _emptyArray;

        public interface NativeIndexResult {
            public static final int NATIVE_INDEX_RESULT_DOCUMENT_TRIMMED = 1;
            public static final int NATIVE_INDEX_RESULT_ERROR_DOCSTORE = 21;
            public static final int NATIVE_INDEX_RESULT_ERROR_INDEX_LEXICON_FULL = 30;
            public static final int NATIVE_INDEX_RESULT_ERROR_TAG_CAP_EXCEEDED = 22;
            public static final int NATIVE_INDEX_RESULT_ERROR_URI_NOT_FOUND = 20;
            public static final int NATIVE_INDEX_RESULT_INDEX_TOKENS_CLIPPED = 2;
            public static final int NATIVE_INDEX_RESULT_OK = 0;
            public static final int NATIVE_INDEX_RESULT_PROTO_PARSE_ERROR = 10;
            public static final int NATIVE_INDEX_RESULT_URI_REPLACED = 3;
        }

        public static Enums[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new Enums[0];
                    }
                }
            }
            return _emptyArray;
        }

        public Enums() {
            clear();
        }

        public Enums clear() {
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof Enums)) {
                return false;
            }
            Enums other = (Enums) o;
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

        public Enums mergeFrom(CodedInputByteBufferNano input) throws IOException {
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

        public static Enums parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (Enums) MessageNano.mergeFrom(new Enums(), data);
        }

        public static Enums parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new Enums().mergeFrom(input);
        }
    }

    public interface EventStatus {
        public static final int ENDED = 2;
        public static final int STARTED = 1;
        public static final int UNKNOWN = 0;
    }

    public static final class GlobalSearchApplicationInfoProto extends ExtendableMessageNano<GlobalSearchApplicationInfoProto> {
        private static volatile GlobalSearchApplicationInfoProto[] _emptyArray;
        public String defaultIntentAction;
        public String defaultIntentActivity;
        public String defaultIntentData;
        public int iconResId;
        public int labelResId;
        public String packagename;
        public int settingDescriptionResId;
        public String sourcename;

        public static GlobalSearchApplicationInfoProto[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new GlobalSearchApplicationInfoProto[0];
                    }
                }
            }
            return _emptyArray;
        }

        public GlobalSearchApplicationInfoProto() {
            clear();
        }

        public GlobalSearchApplicationInfoProto clear() {
            this.packagename = BuildConfig.VERSION_NAME;
            this.sourcename = BuildConfig.VERSION_NAME;
            this.labelResId = 0;
            this.settingDescriptionResId = 0;
            this.iconResId = 0;
            this.defaultIntentAction = BuildConfig.VERSION_NAME;
            this.defaultIntentData = BuildConfig.VERSION_NAME;
            this.defaultIntentActivity = BuildConfig.VERSION_NAME;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof GlobalSearchApplicationInfoProto)) {
                return false;
            }
            GlobalSearchApplicationInfoProto other = (GlobalSearchApplicationInfoProto) o;
            if (this.packagename == null) {
                if (other.packagename != null) {
                    return false;
                }
            } else if (!this.packagename.equals(other.packagename)) {
                return false;
            }
            if (this.sourcename == null) {
                if (other.sourcename != null) {
                    return false;
                }
            } else if (!this.sourcename.equals(other.sourcename)) {
                return false;
            }
            if (this.labelResId != other.labelResId || this.settingDescriptionResId != other.settingDescriptionResId || this.iconResId != other.iconResId) {
                return false;
            }
            if (this.defaultIntentAction == null) {
                if (other.defaultIntentAction != null) {
                    return false;
                }
            } else if (!this.defaultIntentAction.equals(other.defaultIntentAction)) {
                return false;
            }
            if (this.defaultIntentData == null) {
                if (other.defaultIntentData != null) {
                    return false;
                }
            } else if (!this.defaultIntentData.equals(other.defaultIntentData)) {
                return false;
            }
            if (this.defaultIntentActivity == null) {
                if (other.defaultIntentActivity != null) {
                    return false;
                }
            } else if (!this.defaultIntentActivity.equals(other.defaultIntentActivity)) {
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
            int hashCode = (((((((((((((((((getClass().getName().hashCode() + 527) * 31) + (this.packagename == null ? 0 : this.packagename.hashCode())) * 31) + (this.sourcename == null ? 0 : this.sourcename.hashCode())) * 31) + this.labelResId) * 31) + this.settingDescriptionResId) * 31) + this.iconResId) * 31) + (this.defaultIntentAction == null ? 0 : this.defaultIntentAction.hashCode())) * 31) + (this.defaultIntentData == null ? 0 : this.defaultIntentData.hashCode())) * 31) + (this.defaultIntentActivity == null ? 0 : this.defaultIntentActivity.hashCode())) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (!this.packagename.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(1, this.packagename);
            }
            if (this.labelResId != 0) {
                output.writeInt32(2, this.labelResId);
            }
            if (this.settingDescriptionResId != 0) {
                output.writeInt32(3, this.settingDescriptionResId);
            }
            if (this.iconResId != 0) {
                output.writeInt32(4, this.iconResId);
            }
            if (!this.defaultIntentAction.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(5, this.defaultIntentAction);
            }
            if (!this.defaultIntentData.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(6, this.defaultIntentData);
            }
            if (!this.defaultIntentActivity.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(7, this.defaultIntentActivity);
            }
            if (!this.sourcename.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(8, this.sourcename);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (!this.packagename.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.packagename);
            }
            if (this.labelResId != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(2, this.labelResId);
            }
            if (this.settingDescriptionResId != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(3, this.settingDescriptionResId);
            }
            if (this.iconResId != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(4, this.iconResId);
            }
            if (!this.defaultIntentAction.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(5, this.defaultIntentAction);
            }
            if (!this.defaultIntentData.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(6, this.defaultIntentData);
            }
            if (!this.defaultIntentActivity.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(7, this.defaultIntentActivity);
            }
            if (this.sourcename.equals(BuildConfig.VERSION_NAME)) {
                return size;
            }
            return size + CodedOutputByteBufferNano.computeStringSize(8, this.sourcename);
        }

        public GlobalSearchApplicationInfoProto mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        this.packagename = input.readString();
                        continue;
                    case LogSource.GMS_CORE_PEOPLE /*16*/:
                        this.labelResId = input.readInt32();
                        continue;
                    case LogSource.LB_C /*24*/:
                        this.settingDescriptionResId = input.readInt32();
                        continue;
                    case LogSource.SOCIAL /*32*/:
                        this.iconResId = input.readInt32();
                        continue;
                    case LogSource.PHOTOS /*42*/:
                        this.defaultIntentAction = input.readString();
                        continue;
                    case LogSource.HANGOUT_LOG_REQUEST /*50*/:
                        this.defaultIntentData = input.readString();
                        continue;
                    case LogSource.SLIDES /*58*/:
                        this.defaultIntentActivity = input.readString();
                        continue;
                    case LogSource.WIFI_ASSISTANT /*66*/:
                        this.sourcename = input.readString();
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

        public static GlobalSearchApplicationInfoProto parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (GlobalSearchApplicationInfoProto) MessageNano.mergeFrom(new GlobalSearchApplicationInfoProto(), data);
        }

        public static GlobalSearchApplicationInfoProto parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new GlobalSearchApplicationInfoProto().mergeFrom(input);
        }
    }

    public interface GlobalSearchUserEnabled {
        public static final int DISABLED = 1;
        public static final int ENABLED = 2;
        public static final int UNSET = 0;
    }

    public static final class IMEIterToken extends ExtendableMessageNano<IMEIterToken> {
        private static volatile IMEIterToken[] _emptyArray;
        public long compactTimestampSecs;
        public int docid;

        public static IMEIterToken[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new IMEIterToken[0];
                    }
                }
            }
            return _emptyArray;
        }

        public IMEIterToken() {
            clear();
        }

        public IMEIterToken clear() {
            this.compactTimestampSecs = 0;
            this.docid = 0;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof IMEIterToken)) {
                return false;
            }
            IMEIterToken other = (IMEIterToken) o;
            if (this.compactTimestampSecs != other.compactTimestampSecs || this.docid != other.docid) {
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
            int hashCode = (((((getClass().getName().hashCode() + 527) * 31) + ((int) (this.compactTimestampSecs ^ (this.compactTimestampSecs >>> 32)))) * 31) + this.docid) * 31;
            int hashCode2 = (this.unknownFieldData == null || this.unknownFieldData.isEmpty()) ? 0 : this.unknownFieldData.hashCode();
            return hashCode + hashCode2;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.compactTimestampSecs != 0) {
                output.writeUInt64(1, this.compactTimestampSecs);
            }
            if (this.docid != 0) {
                output.writeUInt32(2, this.docid);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.compactTimestampSecs != 0) {
                size += CodedOutputByteBufferNano.computeUInt64Size(1, this.compactTimestampSecs);
            }
            if (this.docid != 0) {
                return size + CodedOutputByteBufferNano.computeUInt32Size(2, this.docid);
            }
            return size;
        }

        public IMEIterToken mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_GET_LINK /*8*/:
                        this.compactTimestampSecs = input.readUInt64();
                        continue;
                    case LogSource.GMS_CORE_PEOPLE /*16*/:
                        this.docid = input.readUInt32();
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

        public static IMEIterToken parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (IMEIterToken) MessageNano.mergeFrom(new IMEIterToken(), data);
        }

        public static IMEIterToken parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new IMEIterToken().mergeFrom(input);
        }
    }

    public static final class IMEUpdate extends ExtendableMessageNano<IMEUpdate> {
        private static volatile IMEUpdate[] _emptyArray;
        public byte[] contents;
        public int corpusId;
        public long docCreatedTimestampMs;
        public long docScore;
        public boolean isUserInput;
        public Language[] language;
        public byte[] varIntLengths;

        public static final class Language extends ExtendableMessageNano<Language> {
            private static volatile Language[] _emptyArray;
            public String languageIso639Code;
            public double score;

            public static Language[] emptyArray() {
                if (_emptyArray == null) {
                    synchronized (InternalNano.LAZY_INIT_LOCK) {
                        if (_emptyArray == null) {
                            _emptyArray = new Language[0];
                        }
                    }
                }
                return _emptyArray;
            }

            public Language() {
                clear();
            }

            public Language clear() {
                this.languageIso639Code = BuildConfig.VERSION_NAME;
                this.score = 0.0d;
                this.unknownFieldData = null;
                this.cachedSize = -1;
                return this;
            }

            public boolean equals(Object o) {
                if (o == this) {
                    return true;
                }
                if (!(o instanceof Language)) {
                    return false;
                }
                Language other = (Language) o;
                if (this.languageIso639Code == null) {
                    if (other.languageIso639Code != null) {
                        return false;
                    }
                } else if (!this.languageIso639Code.equals(other.languageIso639Code)) {
                    return false;
                }
                if (Double.doubleToLongBits(this.score) != Double.doubleToLongBits(other.score)) {
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
                int result = ((getClass().getName().hashCode() + 527) * 31) + (this.languageIso639Code == null ? 0 : this.languageIso639Code.hashCode());
                long v = Double.doubleToLongBits(this.score);
                int i2 = ((result * 31) + ((int) ((v >>> 32) ^ v))) * 31;
                if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                    i = this.unknownFieldData.hashCode();
                }
                return i2 + i;
            }

            public void writeTo(CodedOutputByteBufferNano output) throws IOException {
                if (!this.languageIso639Code.equals(BuildConfig.VERSION_NAME)) {
                    output.writeString(1, this.languageIso639Code);
                }
                if (Double.doubleToLongBits(this.score) != Double.doubleToLongBits(0.0d)) {
                    output.writeDouble(2, this.score);
                }
                super.writeTo(output);
            }

            protected int computeSerializedSize() {
                int size = super.computeSerializedSize();
                if (!this.languageIso639Code.equals(BuildConfig.VERSION_NAME)) {
                    size += CodedOutputByteBufferNano.computeStringSize(1, this.languageIso639Code);
                }
                if (Double.doubleToLongBits(this.score) != Double.doubleToLongBits(0.0d)) {
                    return size + CodedOutputByteBufferNano.computeDoubleSize(2, this.score);
                }
                return size;
            }

            public Language mergeFrom(CodedInputByteBufferNano input) throws IOException {
                while (true) {
                    int tag = input.readTag();
                    switch (tag) {
                        case Action.UNKNOWN /*0*/:
                            break;
                        case Type.TAP_ABOUT /*10*/:
                            this.languageIso639Code = input.readString();
                            continue;
                        case LogSource.LE /*17*/:
                            this.score = input.readDouble();
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

            public static Language parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
                return (Language) MessageNano.mergeFrom(new Language(), data);
            }

            public static Language parseFrom(CodedInputByteBufferNano input) throws IOException {
                return new Language().mergeFrom(input);
            }
        }

        public static IMEUpdate[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new IMEUpdate[0];
                    }
                }
            }
            return _emptyArray;
        }

        public IMEUpdate() {
            clear();
        }

        public IMEUpdate clear() {
            this.corpusId = 0;
            this.contents = WireFormatNano.EMPTY_BYTES;
            this.varIntLengths = WireFormatNano.EMPTY_BYTES;
            this.isUserInput = false;
            this.docScore = 0;
            this.docCreatedTimestampMs = 0;
            this.language = Language.emptyArray();
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof IMEUpdate)) {
                return false;
            }
            IMEUpdate other = (IMEUpdate) o;
            if (this.corpusId != other.corpusId || !Arrays.equals(this.contents, other.contents) || !Arrays.equals(this.varIntLengths, other.varIntLengths) || this.isUserInput != other.isUserInput || this.docScore != other.docScore || this.docCreatedTimestampMs != other.docCreatedTimestampMs || !InternalNano.equals(this.language, other.language)) {
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
            int hashCode = (((((((((((((((getClass().getName().hashCode() + 527) * 31) + this.corpusId) * 31) + Arrays.hashCode(this.contents)) * 31) + Arrays.hashCode(this.varIntLengths)) * 31) + (this.isUserInput ? 1231 : 1237)) * 31) + ((int) (this.docScore ^ (this.docScore >>> 32)))) * 31) + ((int) (this.docCreatedTimestampMs ^ (this.docCreatedTimestampMs >>> 32)))) * 31) + InternalNano.hashCode(this.language)) * 31;
            int hashCode2 = (this.unknownFieldData == null || this.unknownFieldData.isEmpty()) ? 0 : this.unknownFieldData.hashCode();
            return hashCode + hashCode2;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.corpusId != 0) {
                output.writeInt32(1, this.corpusId);
            }
            if (!Arrays.equals(this.contents, WireFormatNano.EMPTY_BYTES)) {
                output.writeBytes(2, this.contents);
            }
            if (!Arrays.equals(this.varIntLengths, WireFormatNano.EMPTY_BYTES)) {
                output.writeBytes(3, this.varIntLengths);
            }
            if (this.isUserInput) {
                output.writeBool(4, this.isUserInput);
            }
            if (this.docScore != 0) {
                output.writeInt64(5, this.docScore);
            }
            if (this.docCreatedTimestampMs != 0) {
                output.writeUInt64(6, this.docCreatedTimestampMs);
            }
            if (this.language != null && this.language.length > 0) {
                for (Language element : this.language) {
                    if (element != null) {
                        output.writeMessage(7, element);
                    }
                }
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.corpusId != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(1, this.corpusId);
            }
            if (!Arrays.equals(this.contents, WireFormatNano.EMPTY_BYTES)) {
                size += CodedOutputByteBufferNano.computeBytesSize(2, this.contents);
            }
            if (!Arrays.equals(this.varIntLengths, WireFormatNano.EMPTY_BYTES)) {
                size += CodedOutputByteBufferNano.computeBytesSize(3, this.varIntLengths);
            }
            if (this.isUserInput) {
                size += CodedOutputByteBufferNano.computeBoolSize(4, this.isUserInput);
            }
            if (this.docScore != 0) {
                size += CodedOutputByteBufferNano.computeInt64Size(5, this.docScore);
            }
            if (this.docCreatedTimestampMs != 0) {
                size += CodedOutputByteBufferNano.computeUInt64Size(6, this.docCreatedTimestampMs);
            }
            if (this.language != null && this.language.length > 0) {
                for (Language element : this.language) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(7, element);
                    }
                }
            }
            return size;
        }

        public IMEUpdate mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_GET_LINK /*8*/:
                        this.corpusId = input.readInt32();
                        continue;
                    case LogSource.GOOGLE_ANALYTICS /*18*/:
                        this.contents = input.readBytes();
                        continue;
                    case LogSource.ANDROID_CAMERA /*26*/:
                        this.varIntLengths = input.readBytes();
                        continue;
                    case LogSource.SOCIAL /*32*/:
                        this.isUserInput = input.readBool();
                        continue;
                    case LogSource.COPRESENCE /*40*/:
                        this.docScore = input.readInt64();
                        continue;
                    case LogSource.BACKDROP /*48*/:
                        this.docCreatedTimestampMs = input.readUInt64();
                        continue;
                    case LogSource.SLIDES /*58*/:
                        int i;
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 58);
                        if (this.language == null) {
                            i = 0;
                        } else {
                            i = this.language.length;
                        }
                        Language[] newArray = new Language[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.language, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new Language();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i++;
                        }
                        newArray[i] = new Language();
                        input.readMessage(newArray[i]);
                        this.language = newArray;
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

        public static IMEUpdate parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (IMEUpdate) MessageNano.mergeFrom(new IMEUpdate(), data);
        }

        public static IMEUpdate parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new IMEUpdate().mergeFrom(input);
        }
    }

    public static final class IMEUpdateCorpusSpec extends ExtendableMessageNano<IMEUpdateCorpusSpec> {
        private static volatile IMEUpdateCorpusSpec[] _emptyArray;
        public int corpusId;
        public String isUserInputTag;
        public SectionSpec[] sections;
        public boolean useDocScoreAsCreatedTimestamp;
        public int userInputSectionId;
        public String[] userInputSectionValues;

        public static final class SectionSpec extends ExtendableMessageNano<SectionSpec> {
            private static volatile SectionSpec[] _emptyArray;
            public String[] documentContentProcessors;
            public int id;

            public static SectionSpec[] emptyArray() {
                if (_emptyArray == null) {
                    synchronized (InternalNano.LAZY_INIT_LOCK) {
                        if (_emptyArray == null) {
                            _emptyArray = new SectionSpec[0];
                        }
                    }
                }
                return _emptyArray;
            }

            public SectionSpec() {
                clear();
            }

            public SectionSpec clear() {
                this.id = 0;
                this.documentContentProcessors = WireFormatNano.EMPTY_STRING_ARRAY;
                this.unknownFieldData = null;
                this.cachedSize = -1;
                return this;
            }

            public boolean equals(Object o) {
                if (o == this) {
                    return true;
                }
                if (!(o instanceof SectionSpec)) {
                    return false;
                }
                SectionSpec other = (SectionSpec) o;
                if (this.id != other.id || !InternalNano.equals(this.documentContentProcessors, other.documentContentProcessors)) {
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
                int hashCode = (((((getClass().getName().hashCode() + 527) * 31) + this.id) * 31) + InternalNano.hashCode(this.documentContentProcessors)) * 31;
                int hashCode2 = (this.unknownFieldData == null || this.unknownFieldData.isEmpty()) ? 0 : this.unknownFieldData.hashCode();
                return hashCode + hashCode2;
            }

            public void writeTo(CodedOutputByteBufferNano output) throws IOException {
                if (this.id != 0) {
                    output.writeUInt32(1, this.id);
                }
                if (this.documentContentProcessors != null && this.documentContentProcessors.length > 0) {
                    for (String element : this.documentContentProcessors) {
                        if (element != null) {
                            output.writeString(2, element);
                        }
                    }
                }
                super.writeTo(output);
            }

            protected int computeSerializedSize() {
                int size = super.computeSerializedSize();
                if (this.id != 0) {
                    size += CodedOutputByteBufferNano.computeUInt32Size(1, this.id);
                }
                if (this.documentContentProcessors == null || this.documentContentProcessors.length <= 0) {
                    return size;
                }
                int dataCount = 0;
                int dataSize = 0;
                for (String element : this.documentContentProcessors) {
                    if (element != null) {
                        dataCount++;
                        dataSize += CodedOutputByteBufferNano.computeStringSizeNoTag(element);
                    }
                }
                return (size + dataSize) + (dataCount * 1);
            }

            public SectionSpec mergeFrom(CodedInputByteBufferNano input) throws IOException {
                while (true) {
                    int tag = input.readTag();
                    switch (tag) {
                        case Action.UNKNOWN /*0*/:
                            break;
                        case Type.TAP_GET_LINK /*8*/:
                            this.id = input.readUInt32();
                            continue;
                        case LogSource.GOOGLE_ANALYTICS /*18*/:
                            int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 18);
                            int i = this.documentContentProcessors == null ? 0 : this.documentContentProcessors.length;
                            String[] newArray = new String[(i + arrayLength)];
                            if (i != 0) {
                                System.arraycopy(this.documentContentProcessors, 0, newArray, 0, i);
                            }
                            while (i < newArray.length - 1) {
                                newArray[i] = input.readString();
                                input.readTag();
                                i++;
                            }
                            newArray[i] = input.readString();
                            this.documentContentProcessors = newArray;
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

            public static SectionSpec parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
                return (SectionSpec) MessageNano.mergeFrom(new SectionSpec(), data);
            }

            public static SectionSpec parseFrom(CodedInputByteBufferNano input) throws IOException {
                return new SectionSpec().mergeFrom(input);
            }
        }

        public static IMEUpdateCorpusSpec[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new IMEUpdateCorpusSpec[0];
                    }
                }
            }
            return _emptyArray;
        }

        public IMEUpdateCorpusSpec() {
            clear();
        }

        public IMEUpdateCorpusSpec clear() {
            this.corpusId = 0;
            this.isUserInputTag = BuildConfig.VERSION_NAME;
            this.userInputSectionId = -1;
            this.userInputSectionValues = WireFormatNano.EMPTY_STRING_ARRAY;
            this.sections = SectionSpec.emptyArray();
            this.useDocScoreAsCreatedTimestamp = false;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof IMEUpdateCorpusSpec)) {
                return false;
            }
            IMEUpdateCorpusSpec other = (IMEUpdateCorpusSpec) o;
            if (this.corpusId != other.corpusId) {
                return false;
            }
            if (this.isUserInputTag == null) {
                if (other.isUserInputTag != null) {
                    return false;
                }
            } else if (!this.isUserInputTag.equals(other.isUserInputTag)) {
                return false;
            }
            if (this.userInputSectionId != other.userInputSectionId || !InternalNano.equals(this.userInputSectionValues, other.userInputSectionValues) || !InternalNano.equals(this.sections, other.sections) || this.useDocScoreAsCreatedTimestamp != other.useDocScoreAsCreatedTimestamp) {
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
            int hashCode = (((((((((((((getClass().getName().hashCode() + 527) * 31) + this.corpusId) * 31) + (this.isUserInputTag == null ? 0 : this.isUserInputTag.hashCode())) * 31) + this.userInputSectionId) * 31) + InternalNano.hashCode(this.userInputSectionValues)) * 31) + InternalNano.hashCode(this.sections)) * 31) + (this.useDocScoreAsCreatedTimestamp ? 1231 : 1237)) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.corpusId != 0) {
                output.writeInt32(1, this.corpusId);
            }
            if (!this.isUserInputTag.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(2, this.isUserInputTag);
            }
            if (this.sections != null && this.sections.length > 0) {
                for (SectionSpec element : this.sections) {
                    if (element != null) {
                        output.writeMessage(3, element);
                    }
                }
            }
            if (this.useDocScoreAsCreatedTimestamp) {
                output.writeBool(4, this.useDocScoreAsCreatedTimestamp);
            }
            if (this.userInputSectionId != -1) {
                output.writeInt32(5, this.userInputSectionId);
            }
            if (this.userInputSectionValues != null && this.userInputSectionValues.length > 0) {
                for (String element2 : this.userInputSectionValues) {
                    if (element2 != null) {
                        output.writeString(6, element2);
                    }
                }
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.corpusId != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(1, this.corpusId);
            }
            if (!this.isUserInputTag.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(2, this.isUserInputTag);
            }
            if (this.sections != null && this.sections.length > 0) {
                for (SectionSpec element : this.sections) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(3, element);
                    }
                }
            }
            if (this.useDocScoreAsCreatedTimestamp) {
                size += CodedOutputByteBufferNano.computeBoolSize(4, this.useDocScoreAsCreatedTimestamp);
            }
            if (this.userInputSectionId != -1) {
                size += CodedOutputByteBufferNano.computeInt32Size(5, this.userInputSectionId);
            }
            if (this.userInputSectionValues == null || this.userInputSectionValues.length <= 0) {
                return size;
            }
            int dataCount = 0;
            int dataSize = 0;
            for (String element2 : this.userInputSectionValues) {
                if (element2 != null) {
                    dataCount++;
                    dataSize += CodedOutputByteBufferNano.computeStringSizeNoTag(element2);
                }
            }
            return (size + dataSize) + (dataCount * 1);
        }

        public IMEUpdateCorpusSpec mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                int arrayLength;
                int i;
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_GET_LINK /*8*/:
                        this.corpusId = input.readInt32();
                        continue;
                    case LogSource.GOOGLE_ANALYTICS /*18*/:
                        this.isUserInputTag = input.readString();
                        continue;
                    case LogSource.ANDROID_CAMERA /*26*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 26);
                        if (this.sections == null) {
                            i = 0;
                        } else {
                            i = this.sections.length;
                        }
                        SectionSpec[] newArray = new SectionSpec[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.sections, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new SectionSpec();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i++;
                        }
                        newArray[i] = new SectionSpec();
                        input.readMessage(newArray[i]);
                        this.sections = newArray;
                        continue;
                    case LogSource.SOCIAL /*32*/:
                        this.useDocScoreAsCreatedTimestamp = input.readBool();
                        continue;
                    case LogSource.COPRESENCE /*40*/:
                        this.userInputSectionId = input.readInt32();
                        continue;
                    case LogSource.HANGOUT_LOG_REQUEST /*50*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 50);
                        i = this.userInputSectionValues == null ? 0 : this.userInputSectionValues.length;
                        String[] newArray2 = new String[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.userInputSectionValues, 0, newArray2, 0, i);
                        }
                        while (i < newArray2.length - 1) {
                            newArray2[i] = input.readString();
                            input.readTag();
                            i++;
                        }
                        newArray2[i] = input.readString();
                        this.userInputSectionValues = newArray2;
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

        public static IMEUpdateCorpusSpec parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (IMEUpdateCorpusSpec) MessageNano.mergeFrom(new IMEUpdateCorpusSpec(), data);
        }

        public static IMEUpdateCorpusSpec parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new IMEUpdateCorpusSpec().mergeFrom(input);
        }
    }

    public static final class IMEUpdateResponse extends ExtendableMessageNano<IMEUpdateResponse> {
        private static volatile IMEUpdateResponse[] _emptyArray;
        public IMEIterToken nextIterToken;
        public IMEUpdate[] updates;

        public static IMEUpdateResponse[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new IMEUpdateResponse[0];
                    }
                }
            }
            return _emptyArray;
        }

        public IMEUpdateResponse() {
            clear();
        }

        public IMEUpdateResponse clear() {
            this.nextIterToken = null;
            this.updates = IMEUpdate.emptyArray();
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof IMEUpdateResponse)) {
                return false;
            }
            IMEUpdateResponse other = (IMEUpdateResponse) o;
            if (this.nextIterToken == null) {
                if (other.nextIterToken != null) {
                    return false;
                }
            } else if (!this.nextIterToken.equals(other.nextIterToken)) {
                return false;
            }
            if (!InternalNano.equals(this.updates, other.updates)) {
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
            int hashCode = (((((getClass().getName().hashCode() + 527) * 31) + (this.nextIterToken == null ? 0 : this.nextIterToken.hashCode())) * 31) + InternalNano.hashCode(this.updates)) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.nextIterToken != null) {
                output.writeMessage(1, this.nextIterToken);
            }
            if (this.updates != null && this.updates.length > 0) {
                for (IMEUpdate element : this.updates) {
                    if (element != null) {
                        output.writeMessage(2, element);
                    }
                }
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.nextIterToken != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(1, this.nextIterToken);
            }
            if (this.updates != null && this.updates.length > 0) {
                for (IMEUpdate element : this.updates) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(2, element);
                    }
                }
            }
            return size;
        }

        public IMEUpdateResponse mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        if (this.nextIterToken == null) {
                            this.nextIterToken = new IMEIterToken();
                        }
                        input.readMessage(this.nextIterToken);
                        continue;
                    case LogSource.GOOGLE_ANALYTICS /*18*/:
                        int i;
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 18);
                        if (this.updates == null) {
                            i = 0;
                        } else {
                            i = this.updates.length;
                        }
                        IMEUpdate[] newArray = new IMEUpdate[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.updates, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new IMEUpdate();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i++;
                        }
                        newArray[i] = new IMEUpdate();
                        input.readMessage(newArray[i]);
                        this.updates = newArray;
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

        public static IMEUpdateResponse parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (IMEUpdateResponse) MessageNano.mergeFrom(new IMEUpdateResponse(), data);
        }

        public static IMEUpdateResponse parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new IMEUpdateResponse().mergeFrom(input);
        }
    }

    public static final class IndexCorpusScoringConfig extends ExtendableMessageNano<IndexCorpusScoringConfig> {
        private static volatile IndexCorpusScoringConfig[] _emptyArray;
        public int generalUseScoreMultiplier;
        public int globalSearchUseScoreMultiplier;
        public int halfTimeSecs;
        public int searchedUseScoreMultiplier;
        public Section[] section;

        public static final class Section extends ExtendableMessageNano<Section> {
            private static volatile Section[] _emptyArray;
            public int id;
            public int subsequentTokenLegacyHitScore;

            public static Section[] emptyArray() {
                if (_emptyArray == null) {
                    synchronized (InternalNano.LAZY_INIT_LOCK) {
                        if (_emptyArray == null) {
                            _emptyArray = new Section[0];
                        }
                    }
                }
                return _emptyArray;
            }

            public Section() {
                clear();
            }

            public Section clear() {
                this.id = 0;
                this.subsequentTokenLegacyHitScore = 0;
                this.unknownFieldData = null;
                this.cachedSize = -1;
                return this;
            }

            public boolean equals(Object o) {
                if (o == this) {
                    return true;
                }
                if (!(o instanceof Section)) {
                    return false;
                }
                Section other = (Section) o;
                if (this.id != other.id || this.subsequentTokenLegacyHitScore != other.subsequentTokenLegacyHitScore) {
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
                int hashCode = (((((getClass().getName().hashCode() + 527) * 31) + this.id) * 31) + this.subsequentTokenLegacyHitScore) * 31;
                int hashCode2 = (this.unknownFieldData == null || this.unknownFieldData.isEmpty()) ? 0 : this.unknownFieldData.hashCode();
                return hashCode + hashCode2;
            }

            public void writeTo(CodedOutputByteBufferNano output) throws IOException {
                if (this.id != 0) {
                    output.writeUInt32(1, this.id);
                }
                if (this.subsequentTokenLegacyHitScore != 0) {
                    output.writeInt32(2, this.subsequentTokenLegacyHitScore);
                }
                super.writeTo(output);
            }

            protected int computeSerializedSize() {
                int size = super.computeSerializedSize();
                if (this.id != 0) {
                    size += CodedOutputByteBufferNano.computeUInt32Size(1, this.id);
                }
                if (this.subsequentTokenLegacyHitScore != 0) {
                    return size + CodedOutputByteBufferNano.computeInt32Size(2, this.subsequentTokenLegacyHitScore);
                }
                return size;
            }

            public Section mergeFrom(CodedInputByteBufferNano input) throws IOException {
                while (true) {
                    int tag = input.readTag();
                    switch (tag) {
                        case Action.UNKNOWN /*0*/:
                            break;
                        case Type.TAP_GET_LINK /*8*/:
                            this.id = input.readUInt32();
                            continue;
                        case LogSource.GMS_CORE_PEOPLE /*16*/:
                            this.subsequentTokenLegacyHitScore = input.readInt32();
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

            public static Section parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
                return (Section) MessageNano.mergeFrom(new Section(), data);
            }

            public static Section parseFrom(CodedInputByteBufferNano input) throws IOException {
                return new Section().mergeFrom(input);
            }
        }

        public static IndexCorpusScoringConfig[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new IndexCorpusScoringConfig[0];
                    }
                }
            }
            return _emptyArray;
        }

        public IndexCorpusScoringConfig() {
            clear();
        }

        public IndexCorpusScoringConfig clear() {
            this.halfTimeSecs = 604800;
            this.generalUseScoreMultiplier = 1;
            this.searchedUseScoreMultiplier = 2;
            this.globalSearchUseScoreMultiplier = 2;
            this.section = Section.emptyArray();
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof IndexCorpusScoringConfig)) {
                return false;
            }
            IndexCorpusScoringConfig other = (IndexCorpusScoringConfig) o;
            if (this.halfTimeSecs != other.halfTimeSecs || this.generalUseScoreMultiplier != other.generalUseScoreMultiplier || this.searchedUseScoreMultiplier != other.searchedUseScoreMultiplier || this.globalSearchUseScoreMultiplier != other.globalSearchUseScoreMultiplier || !InternalNano.equals(this.section, other.section)) {
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
            int hashCode = (((((((((((getClass().getName().hashCode() + 527) * 31) + this.halfTimeSecs) * 31) + this.generalUseScoreMultiplier) * 31) + this.searchedUseScoreMultiplier) * 31) + this.globalSearchUseScoreMultiplier) * 31) + InternalNano.hashCode(this.section)) * 31;
            int hashCode2 = (this.unknownFieldData == null || this.unknownFieldData.isEmpty()) ? 0 : this.unknownFieldData.hashCode();
            return hashCode + hashCode2;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.halfTimeSecs != 604800) {
                output.writeUInt32(1, this.halfTimeSecs);
            }
            if (this.generalUseScoreMultiplier != 1) {
                output.writeUInt32(2, this.generalUseScoreMultiplier);
            }
            if (this.searchedUseScoreMultiplier != 2) {
                output.writeUInt32(3, this.searchedUseScoreMultiplier);
            }
            if (this.globalSearchUseScoreMultiplier != 2) {
                output.writeUInt32(4, this.globalSearchUseScoreMultiplier);
            }
            if (this.section != null && this.section.length > 0) {
                for (Section element : this.section) {
                    if (element != null) {
                        output.writeMessage(5, element);
                    }
                }
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.halfTimeSecs != 604800) {
                size += CodedOutputByteBufferNano.computeUInt32Size(1, this.halfTimeSecs);
            }
            if (this.generalUseScoreMultiplier != 1) {
                size += CodedOutputByteBufferNano.computeUInt32Size(2, this.generalUseScoreMultiplier);
            }
            if (this.searchedUseScoreMultiplier != 2) {
                size += CodedOutputByteBufferNano.computeUInt32Size(3, this.searchedUseScoreMultiplier);
            }
            if (this.globalSearchUseScoreMultiplier != 2) {
                size += CodedOutputByteBufferNano.computeUInt32Size(4, this.globalSearchUseScoreMultiplier);
            }
            if (this.section != null && this.section.length > 0) {
                for (Section element : this.section) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(5, element);
                    }
                }
            }
            return size;
        }

        public IndexCorpusScoringConfig mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_GET_LINK /*8*/:
                        this.halfTimeSecs = input.readUInt32();
                        continue;
                    case LogSource.GMS_CORE_PEOPLE /*16*/:
                        this.generalUseScoreMultiplier = input.readUInt32();
                        continue;
                    case LogSource.LB_C /*24*/:
                        this.searchedUseScoreMultiplier = input.readUInt32();
                        continue;
                    case LogSource.SOCIAL /*32*/:
                        this.globalSearchUseScoreMultiplier = input.readUInt32();
                        continue;
                    case LogSource.PHOTOS /*42*/:
                        int i;
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 42);
                        if (this.section == null) {
                            i = 0;
                        } else {
                            i = this.section.length;
                        }
                        Section[] newArray = new Section[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.section, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new Section();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i++;
                        }
                        newArray[i] = new Section();
                        input.readMessage(newArray[i]);
                        this.section = newArray;
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

        public static IndexCorpusScoringConfig parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (IndexCorpusScoringConfig) MessageNano.mergeFrom(new IndexCorpusScoringConfig(), data);
        }

        public static IndexCorpusScoringConfig parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new IndexCorpusScoringConfig().mergeFrom(input);
        }
    }

    public static final class IndexScoringConfig extends ExtendableMessageNano<IndexScoringConfig> {
        private static volatile IndexScoringConfig[] _emptyArray;
        public int omniboxNumTitleTokensIndexed;
        public int omniboxTitleLegacyHitScore;
        public int omniboxTitleStopwordLegacyHitScore;
        public boolean scaleParams;
        public int urlCommonDomainTokenLegacyHitScore;
        public int urlNumPathTokensIndexed;
        public int urlPathLegacyHitScore;

        public static IndexScoringConfig[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new IndexScoringConfig[0];
                    }
                }
            }
            return _emptyArray;
        }

        public IndexScoringConfig() {
            clear();
        }

        public IndexScoringConfig clear() {
            this.urlPathLegacyHitScore = LogSource.KEEP;
            this.urlCommonDomainTokenLegacyHitScore = 16;
            this.omniboxNumTitleTokensIndexed = 8;
            this.omniboxTitleLegacyHitScore = 192;
            this.omniboxTitleStopwordLegacyHitScore = 64;
            this.urlNumPathTokensIndexed = 1000;
            this.scaleParams = false;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof IndexScoringConfig)) {
                return false;
            }
            IndexScoringConfig other = (IndexScoringConfig) o;
            if (this.urlPathLegacyHitScore != other.urlPathLegacyHitScore || this.urlCommonDomainTokenLegacyHitScore != other.urlCommonDomainTokenLegacyHitScore || this.omniboxNumTitleTokensIndexed != other.omniboxNumTitleTokensIndexed || this.omniboxTitleLegacyHitScore != other.omniboxTitleLegacyHitScore || this.omniboxTitleStopwordLegacyHitScore != other.omniboxTitleStopwordLegacyHitScore || this.urlNumPathTokensIndexed != other.urlNumPathTokensIndexed || this.scaleParams != other.scaleParams) {
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
            int hashCode = (((((((((((((((getClass().getName().hashCode() + 527) * 31) + this.urlPathLegacyHitScore) * 31) + this.urlCommonDomainTokenLegacyHitScore) * 31) + this.omniboxNumTitleTokensIndexed) * 31) + this.omniboxTitleLegacyHitScore) * 31) + this.omniboxTitleStopwordLegacyHitScore) * 31) + this.urlNumPathTokensIndexed) * 31) + (this.scaleParams ? 1231 : 1237)) * 31;
            int hashCode2 = (this.unknownFieldData == null || this.unknownFieldData.isEmpty()) ? 0 : this.unknownFieldData.hashCode();
            return hashCode + hashCode2;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.urlPathLegacyHitScore != LogSource.KEEP) {
                output.writeUInt32(1, this.urlPathLegacyHitScore);
            }
            if (this.urlCommonDomainTokenLegacyHitScore != 16) {
                output.writeUInt32(2, this.urlCommonDomainTokenLegacyHitScore);
            }
            if (this.omniboxNumTitleTokensIndexed != 8) {
                output.writeUInt32(3, this.omniboxNumTitleTokensIndexed);
            }
            if (this.omniboxTitleLegacyHitScore != 192) {
                output.writeUInt32(4, this.omniboxTitleLegacyHitScore);
            }
            if (this.omniboxTitleStopwordLegacyHitScore != 64) {
                output.writeUInt32(5, this.omniboxTitleStopwordLegacyHitScore);
            }
            if (this.urlNumPathTokensIndexed != 1000) {
                output.writeUInt32(6, this.urlNumPathTokensIndexed);
            }
            if (this.scaleParams) {
                output.writeBool(7, this.scaleParams);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.urlPathLegacyHitScore != LogSource.KEEP) {
                size += CodedOutputByteBufferNano.computeUInt32Size(1, this.urlPathLegacyHitScore);
            }
            if (this.urlCommonDomainTokenLegacyHitScore != 16) {
                size += CodedOutputByteBufferNano.computeUInt32Size(2, this.urlCommonDomainTokenLegacyHitScore);
            }
            if (this.omniboxNumTitleTokensIndexed != 8) {
                size += CodedOutputByteBufferNano.computeUInt32Size(3, this.omniboxNumTitleTokensIndexed);
            }
            if (this.omniboxTitleLegacyHitScore != 192) {
                size += CodedOutputByteBufferNano.computeUInt32Size(4, this.omniboxTitleLegacyHitScore);
            }
            if (this.omniboxTitleStopwordLegacyHitScore != 64) {
                size += CodedOutputByteBufferNano.computeUInt32Size(5, this.omniboxTitleStopwordLegacyHitScore);
            }
            if (this.urlNumPathTokensIndexed != 1000) {
                size += CodedOutputByteBufferNano.computeUInt32Size(6, this.urlNumPathTokensIndexed);
            }
            if (this.scaleParams) {
                return size + CodedOutputByteBufferNano.computeBoolSize(7, this.scaleParams);
            }
            return size;
        }

        public IndexScoringConfig mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_GET_LINK /*8*/:
                        this.urlPathLegacyHitScore = input.readUInt32();
                        continue;
                    case LogSource.GMS_CORE_PEOPLE /*16*/:
                        this.urlCommonDomainTokenLegacyHitScore = input.readUInt32();
                        continue;
                    case LogSource.LB_C /*24*/:
                        this.omniboxNumTitleTokensIndexed = input.readUInt32();
                        continue;
                    case LogSource.SOCIAL /*32*/:
                        this.omniboxTitleLegacyHitScore = input.readUInt32();
                        continue;
                    case LogSource.COPRESENCE /*40*/:
                        this.omniboxTitleStopwordLegacyHitScore = input.readUInt32();
                        continue;
                    case LogSource.BACKDROP /*48*/:
                        this.urlNumPathTokensIndexed = input.readUInt32();
                        continue;
                    case LogSource.DOCS /*56*/:
                        this.scaleParams = input.readBool();
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

        public static IndexScoringConfig parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (IndexScoringConfig) MessageNano.mergeFrom(new IndexScoringConfig(), data);
        }

        public static IndexScoringConfig parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new IndexScoringConfig().mergeFrom(input);
        }
    }

    public static final class InitStatus extends ExtendableMessageNano<InitStatus> {
        private static volatile InitStatus[] _emptyArray;
        public CorpusInitInfo[] corpusInfo;
        public boolean docstoreRecovery;
        public int indexRecovery;

        public static final class CorpusInitInfo extends ExtendableMessageNano<CorpusInitInfo> {
            private static volatile CorpusInitInfo[] _emptyArray;
            public int corpusId;
            public long lastSeqno;

            public static CorpusInitInfo[] emptyArray() {
                if (_emptyArray == null) {
                    synchronized (InternalNano.LAZY_INIT_LOCK) {
                        if (_emptyArray == null) {
                            _emptyArray = new CorpusInitInfo[0];
                        }
                    }
                }
                return _emptyArray;
            }

            public CorpusInitInfo() {
                clear();
            }

            public CorpusInitInfo clear() {
                this.corpusId = 0;
                this.lastSeqno = 0;
                this.unknownFieldData = null;
                this.cachedSize = -1;
                return this;
            }

            public boolean equals(Object o) {
                if (o == this) {
                    return true;
                }
                if (!(o instanceof CorpusInitInfo)) {
                    return false;
                }
                CorpusInitInfo other = (CorpusInitInfo) o;
                if (this.corpusId != other.corpusId || this.lastSeqno != other.lastSeqno) {
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
                int hashCode = (((((getClass().getName().hashCode() + 527) * 31) + this.corpusId) * 31) + ((int) (this.lastSeqno ^ (this.lastSeqno >>> 32)))) * 31;
                int hashCode2 = (this.unknownFieldData == null || this.unknownFieldData.isEmpty()) ? 0 : this.unknownFieldData.hashCode();
                return hashCode + hashCode2;
            }

            public void writeTo(CodedOutputByteBufferNano output) throws IOException {
                if (this.corpusId != 0) {
                    output.writeInt32(1, this.corpusId);
                }
                if (this.lastSeqno != 0) {
                    output.writeInt64(2, this.lastSeqno);
                }
                super.writeTo(output);
            }

            protected int computeSerializedSize() {
                int size = super.computeSerializedSize();
                if (this.corpusId != 0) {
                    size += CodedOutputByteBufferNano.computeInt32Size(1, this.corpusId);
                }
                if (this.lastSeqno != 0) {
                    return size + CodedOutputByteBufferNano.computeInt64Size(2, this.lastSeqno);
                }
                return size;
            }

            public CorpusInitInfo mergeFrom(CodedInputByteBufferNano input) throws IOException {
                while (true) {
                    int tag = input.readTag();
                    switch (tag) {
                        case Action.UNKNOWN /*0*/:
                            break;
                        case Type.TAP_GET_LINK /*8*/:
                            this.corpusId = input.readInt32();
                            continue;
                        case LogSource.GMS_CORE_PEOPLE /*16*/:
                            this.lastSeqno = input.readInt64();
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

            public static CorpusInitInfo parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
                return (CorpusInitInfo) MessageNano.mergeFrom(new CorpusInitInfo(), data);
            }

            public static CorpusInitInfo parseFrom(CodedInputByteBufferNano input) throws IOException {
                return new CorpusInitInfo().mergeFrom(input);
            }
        }

        public interface IndexRecovery {
            public static final int FULL = 2;
            public static final int LITE = 1;
            public static final int NONE = 0;
        }

        public static InitStatus[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new InitStatus[0];
                    }
                }
            }
            return _emptyArray;
        }

        public InitStatus() {
            clear();
        }

        public InitStatus clear() {
            this.corpusInfo = CorpusInitInfo.emptyArray();
            this.indexRecovery = 0;
            this.docstoreRecovery = false;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof InitStatus)) {
                return false;
            }
            InitStatus other = (InitStatus) o;
            if (!InternalNano.equals(this.corpusInfo, other.corpusInfo) || this.indexRecovery != other.indexRecovery || this.docstoreRecovery != other.docstoreRecovery) {
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
            int hashCode = (((((((getClass().getName().hashCode() + 527) * 31) + InternalNano.hashCode(this.corpusInfo)) * 31) + this.indexRecovery) * 31) + (this.docstoreRecovery ? 1231 : 1237)) * 31;
            int hashCode2 = (this.unknownFieldData == null || this.unknownFieldData.isEmpty()) ? 0 : this.unknownFieldData.hashCode();
            return hashCode + hashCode2;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.corpusInfo != null && this.corpusInfo.length > 0) {
                for (CorpusInitInfo element : this.corpusInfo) {
                    if (element != null) {
                        output.writeMessage(1, element);
                    }
                }
            }
            if (this.indexRecovery != 0) {
                output.writeInt32(2, this.indexRecovery);
            }
            if (this.docstoreRecovery) {
                output.writeBool(3, this.docstoreRecovery);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.corpusInfo != null && this.corpusInfo.length > 0) {
                for (CorpusInitInfo element : this.corpusInfo) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(1, element);
                    }
                }
            }
            if (this.indexRecovery != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(2, this.indexRecovery);
            }
            if (this.docstoreRecovery) {
                return size + CodedOutputByteBufferNano.computeBoolSize(3, this.docstoreRecovery);
            }
            return size;
        }

        public InitStatus mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        int i;
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 10);
                        if (this.corpusInfo == null) {
                            i = 0;
                        } else {
                            i = this.corpusInfo.length;
                        }
                        CorpusInitInfo[] newArray = new CorpusInitInfo[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.corpusInfo, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new CorpusInitInfo();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i++;
                        }
                        newArray[i] = new CorpusInitInfo();
                        input.readMessage(newArray[i]);
                        this.corpusInfo = newArray;
                        continue;
                    case LogSource.GMS_CORE_PEOPLE /*16*/:
                        int value = input.readInt32();
                        switch (value) {
                            case Action.UNKNOWN /*0*/:
                            case TimeSelection.Type.TEMPORARY /*1*/:
                            case TimeSelection.Type.INDEFINITELY /*2*/:
                                this.indexRecovery = value;
                                break;
                            default:
                                continue;
                        }
                    case LogSource.LB_C /*24*/:
                        this.docstoreRecovery = input.readBool();
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

        public static InitStatus parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (InitStatus) MessageNano.mergeFrom(new InitStatus(), data);
        }

        public static InitStatus parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new InitStatus().mergeFrom(input);
        }
    }

    public static final class NativeConfig extends ExtendableMessageNano<NativeConfig> {
        private static volatile NativeConfig[] _emptyArray;
        public String defaultLangIso639;
        public IndexScoringConfig indexScoringConfig;

        public static NativeConfig[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new NativeConfig[0];
                    }
                }
            }
            return _emptyArray;
        }

        public NativeConfig() {
            clear();
        }

        public NativeConfig clear() {
            this.indexScoringConfig = null;
            this.defaultLangIso639 = "en";
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof NativeConfig)) {
                return false;
            }
            NativeConfig other = (NativeConfig) o;
            if (this.indexScoringConfig == null) {
                if (other.indexScoringConfig != null) {
                    return false;
                }
            } else if (!this.indexScoringConfig.equals(other.indexScoringConfig)) {
                return false;
            }
            if (this.defaultLangIso639 == null) {
                if (other.defaultLangIso639 != null) {
                    return false;
                }
            } else if (!this.defaultLangIso639.equals(other.defaultLangIso639)) {
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
            int hashCode = (((((getClass().getName().hashCode() + 527) * 31) + (this.indexScoringConfig == null ? 0 : this.indexScoringConfig.hashCode())) * 31) + (this.defaultLangIso639 == null ? 0 : this.defaultLangIso639.hashCode())) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.indexScoringConfig != null) {
                output.writeMessage(1, this.indexScoringConfig);
            }
            if (!this.defaultLangIso639.equals("en")) {
                output.writeString(2, this.defaultLangIso639);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.indexScoringConfig != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(1, this.indexScoringConfig);
            }
            if (this.defaultLangIso639.equals("en")) {
                return size;
            }
            return size + CodedOutputByteBufferNano.computeStringSize(2, this.defaultLangIso639);
        }

        public NativeConfig mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        if (this.indexScoringConfig == null) {
                            this.indexScoringConfig = new IndexScoringConfig();
                        }
                        input.readMessage(this.indexScoringConfig);
                        continue;
                    case LogSource.GOOGLE_ANALYTICS /*18*/:
                        this.defaultLangIso639 = input.readString();
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

        public static NativeConfig parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (NativeConfig) MessageNano.mergeFrom(new NativeConfig(), data);
        }

        public static NativeConfig parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new NativeConfig().mergeFrom(input);
        }
    }

    public static final class NativeUsageReport extends ExtendableMessageNano<NativeUsageReport> {
        private static volatile NativeUsageReport[] _emptyArray;
        public int eventStatus;
        public boolean isDeviceOnly;
        public long seqno;
        public int taskPos;
        public String uri;
        public long uriFingerprint;
        public long usageCorpusId;
        public int usageTimestampSecs;
        public int usageType;

        public static NativeUsageReport[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new NativeUsageReport[0];
                    }
                }
            }
            return _emptyArray;
        }

        public NativeUsageReport() {
            clear();
        }

        public NativeUsageReport clear() {
            this.usageCorpusId = 0;
            this.uri = BuildConfig.VERSION_NAME;
            this.uriFingerprint = 0;
            this.usageTimestampSecs = 0;
            this.usageType = 0;
            this.seqno = 0;
            this.taskPos = -1;
            this.isDeviceOnly = false;
            this.eventStatus = 0;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof NativeUsageReport)) {
                return false;
            }
            NativeUsageReport other = (NativeUsageReport) o;
            if (this.usageCorpusId != other.usageCorpusId) {
                return false;
            }
            if (this.uri == null) {
                if (other.uri != null) {
                    return false;
                }
            } else if (!this.uri.equals(other.uri)) {
                return false;
            }
            if (this.uriFingerprint != other.uriFingerprint || this.usageTimestampSecs != other.usageTimestampSecs || this.usageType != other.usageType || this.seqno != other.seqno || this.taskPos != other.taskPos || this.isDeviceOnly != other.isDeviceOnly || this.eventStatus != other.eventStatus) {
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
            int hashCode = (((((((((((((((((((getClass().getName().hashCode() + 527) * 31) + ((int) (this.usageCorpusId ^ (this.usageCorpusId >>> 32)))) * 31) + (this.uri == null ? 0 : this.uri.hashCode())) * 31) + ((int) (this.uriFingerprint ^ (this.uriFingerprint >>> 32)))) * 31) + this.usageTimestampSecs) * 31) + this.usageType) * 31) + ((int) (this.seqno ^ (this.seqno >>> 32)))) * 31) + this.taskPos) * 31) + (this.isDeviceOnly ? 1231 : 1237)) * 31) + this.eventStatus) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.usageCorpusId != 0) {
                output.writeUInt64(1, this.usageCorpusId);
            }
            if (!this.uri.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(2, this.uri);
            }
            if (this.uriFingerprint != 0) {
                output.writeUInt64(3, this.uriFingerprint);
            }
            if (this.usageTimestampSecs != 0) {
                output.writeUInt32(4, this.usageTimestampSecs);
            }
            if (this.usageType != 0) {
                output.writeInt32(5, this.usageType);
            }
            if (this.seqno != 0) {
                output.writeInt64(6, this.seqno);
            }
            if (this.taskPos != -1) {
                output.writeInt32(7, this.taskPos);
            }
            if (this.isDeviceOnly) {
                output.writeBool(8, this.isDeviceOnly);
            }
            if (this.eventStatus != 0) {
                output.writeInt32(9, this.eventStatus);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.usageCorpusId != 0) {
                size += CodedOutputByteBufferNano.computeUInt64Size(1, this.usageCorpusId);
            }
            if (!this.uri.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(2, this.uri);
            }
            if (this.uriFingerprint != 0) {
                size += CodedOutputByteBufferNano.computeUInt64Size(3, this.uriFingerprint);
            }
            if (this.usageTimestampSecs != 0) {
                size += CodedOutputByteBufferNano.computeUInt32Size(4, this.usageTimestampSecs);
            }
            if (this.usageType != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(5, this.usageType);
            }
            if (this.seqno != 0) {
                size += CodedOutputByteBufferNano.computeInt64Size(6, this.seqno);
            }
            if (this.taskPos != -1) {
                size += CodedOutputByteBufferNano.computeInt32Size(7, this.taskPos);
            }
            if (this.isDeviceOnly) {
                size += CodedOutputByteBufferNano.computeBoolSize(8, this.isDeviceOnly);
            }
            if (this.eventStatus != 0) {
                return size + CodedOutputByteBufferNano.computeInt32Size(9, this.eventStatus);
            }
            return size;
        }

        public NativeUsageReport mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                int value;
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_GET_LINK /*8*/:
                        this.usageCorpusId = input.readUInt64();
                        continue;
                    case LogSource.GOOGLE_ANALYTICS /*18*/:
                        this.uri = input.readString();
                        continue;
                    case LogSource.LB_C /*24*/:
                        this.uriFingerprint = input.readUInt64();
                        continue;
                    case LogSource.SOCIAL /*32*/:
                        this.usageTimestampSecs = input.readUInt32();
                        continue;
                    case LogSource.COPRESENCE /*40*/:
                        value = input.readInt32();
                        switch (value) {
                            case Action.UNKNOWN /*0*/:
                            case TimeSelection.Type.TEMPORARY /*1*/:
                            case TimeSelection.Type.INDEFINITELY /*2*/:
                                this.usageType = value;
                                break;
                            default:
                                continue;
                        }
                    case LogSource.BACKDROP /*48*/:
                        this.seqno = input.readInt64();
                        continue;
                    case LogSource.DOCS /*56*/:
                        this.taskPos = input.readInt32();
                        continue;
                    case LogSource.KIDS_COMMUNICATOR /*64*/:
                        this.isDeviceOnly = input.readBool();
                        continue;
                    case LogSource.JUSTSPEAK /*72*/:
                        value = input.readInt32();
                        switch (value) {
                            case Action.UNKNOWN /*0*/:
                            case TimeSelection.Type.TEMPORARY /*1*/:
                            case TimeSelection.Type.INDEFINITELY /*2*/:
                                this.eventStatus = value;
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

        public static NativeUsageReport parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (NativeUsageReport) MessageNano.mergeFrom(new NativeUsageReport(), data);
        }

        public static NativeUsageReport parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new NativeUsageReport().mergeFrom(input);
        }
    }

    public static final class Operation extends ExtendableMessageNano<Operation> {
        private static volatile Operation[] _emptyArray;
        public int corpusId;
        public int crc;
        public int docid;
        public int index;
        public long len;
        public long location;
        public String name;
        public long seqno;
        public int type;

        public interface Type {
            public static final int DOC_ADD = 0;
            public static final int DOC_DEL = 1;
            public static final int TAG_ADD = 2;
            public static final int TAG_DEL = 3;
        }

        public static Operation[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new Operation[0];
                    }
                }
            }
            return _emptyArray;
        }

        public Operation() {
            clear();
        }

        public Operation clear() {
            this.docid = 0;
            this.corpusId = 0;
            this.seqno = 0;
            this.type = 0;
            this.index = 0;
            this.name = BuildConfig.VERSION_NAME;
            this.location = 0;
            this.len = 0;
            this.crc = 0;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof Operation)) {
                return false;
            }
            Operation other = (Operation) o;
            if (this.docid != other.docid || this.corpusId != other.corpusId || this.seqno != other.seqno || this.type != other.type || this.index != other.index) {
                return false;
            }
            if (this.name == null) {
                if (other.name != null) {
                    return false;
                }
            } else if (!this.name.equals(other.name)) {
                return false;
            }
            if (this.location != other.location || this.len != other.len || this.crc != other.crc) {
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
            int hashCode = (((((((((((((((((((getClass().getName().hashCode() + 527) * 31) + this.docid) * 31) + this.corpusId) * 31) + ((int) (this.seqno ^ (this.seqno >>> 32)))) * 31) + this.type) * 31) + this.index) * 31) + (this.name == null ? 0 : this.name.hashCode())) * 31) + ((int) (this.location ^ (this.location >>> 32)))) * 31) + ((int) (this.len ^ (this.len >>> 32)))) * 31) + this.crc) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.docid != 0) {
                output.writeUInt32(1, this.docid);
            }
            if (this.corpusId != 0) {
                output.writeInt32(2, this.corpusId);
            }
            if (this.seqno != 0) {
                output.writeInt64(3, this.seqno);
            }
            if (this.type != 0) {
                output.writeInt32(4, this.type);
            }
            if (this.index != 0) {
                output.writeUInt32(5, this.index);
            }
            if (!this.name.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(6, this.name);
            }
            if (this.location != 0) {
                output.writeUInt64(7, this.location);
            }
            if (this.len != 0) {
                output.writeUInt64(8, this.len);
            }
            if (this.crc != 0) {
                output.writeFixed32(9, this.crc);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.docid != 0) {
                size += CodedOutputByteBufferNano.computeUInt32Size(1, this.docid);
            }
            if (this.corpusId != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(2, this.corpusId);
            }
            if (this.seqno != 0) {
                size += CodedOutputByteBufferNano.computeInt64Size(3, this.seqno);
            }
            if (this.type != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(4, this.type);
            }
            if (this.index != 0) {
                size += CodedOutputByteBufferNano.computeUInt32Size(5, this.index);
            }
            if (!this.name.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(6, this.name);
            }
            if (this.location != 0) {
                size += CodedOutputByteBufferNano.computeUInt64Size(7, this.location);
            }
            if (this.len != 0) {
                size += CodedOutputByteBufferNano.computeUInt64Size(8, this.len);
            }
            if (this.crc != 0) {
                return size + CodedOutputByteBufferNano.computeFixed32Size(9, this.crc);
            }
            return size;
        }

        public Operation mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case com.google.wireless.android.play.playlog.proto.UserLocation.LocationSharingEvent.Type.TAP_GET_LINK /*8*/:
                        this.docid = input.readUInt32();
                        continue;
                    case LogSource.GMS_CORE_PEOPLE /*16*/:
                        this.corpusId = input.readInt32();
                        continue;
                    case LogSource.LB_C /*24*/:
                        this.seqno = input.readInt64();
                        continue;
                    case LogSource.SOCIAL /*32*/:
                        int value = input.readInt32();
                        switch (value) {
                            case Action.UNKNOWN /*0*/:
                            case com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection.Type.TEMPORARY /*1*/:
                            case com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection.Type.INDEFINITELY /*2*/:
                            case com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection.Type.CUSTOM /*3*/:
                                this.type = value;
                                break;
                            default:
                                continue;
                        }
                    case LogSource.COPRESENCE /*40*/:
                        this.index = input.readUInt32();
                        continue;
                    case LogSource.HANGOUT_LOG_REQUEST /*50*/:
                        this.name = input.readString();
                        continue;
                    case LogSource.DOCS /*56*/:
                        this.location = input.readUInt64();
                        continue;
                    case LogSource.KIDS_COMMUNICATOR /*64*/:
                        this.len = input.readUInt64();
                        continue;
                    case LogSource.GMM_COUNTERS /*77*/:
                        this.crc = input.readFixed32();
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

        public static Operation parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (Operation) MessageNano.mergeFrom(new Operation(), data);
        }

        public static Operation parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new Operation().mergeFrom(input);
        }
    }

    public static final class PendingDeleteUsageReport extends ExtendableMessageNano<PendingDeleteUsageReport> {
        private static volatile PendingDeleteUsageReport[] _emptyArray;
        public long uriFingerprint;
        public long usageCorpusId;
        public int usageTimestampEndSecs;
        public int usageTimestampStartSecs;

        public static PendingDeleteUsageReport[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new PendingDeleteUsageReport[0];
                    }
                }
            }
            return _emptyArray;
        }

        public PendingDeleteUsageReport() {
            clear();
        }

        public PendingDeleteUsageReport clear() {
            this.usageCorpusId = 0;
            this.uriFingerprint = 0;
            this.usageTimestampStartSecs = 0;
            this.usageTimestampEndSecs = 0;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof PendingDeleteUsageReport)) {
                return false;
            }
            PendingDeleteUsageReport other = (PendingDeleteUsageReport) o;
            if (this.usageCorpusId != other.usageCorpusId || this.uriFingerprint != other.uriFingerprint || this.usageTimestampStartSecs != other.usageTimestampStartSecs || this.usageTimestampEndSecs != other.usageTimestampEndSecs) {
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
            int hashCode = (((((((((getClass().getName().hashCode() + 527) * 31) + ((int) (this.usageCorpusId ^ (this.usageCorpusId >>> 32)))) * 31) + ((int) (this.uriFingerprint ^ (this.uriFingerprint >>> 32)))) * 31) + this.usageTimestampStartSecs) * 31) + this.usageTimestampEndSecs) * 31;
            int hashCode2 = (this.unknownFieldData == null || this.unknownFieldData.isEmpty()) ? 0 : this.unknownFieldData.hashCode();
            return hashCode + hashCode2;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.usageCorpusId != 0) {
                output.writeUInt64(1, this.usageCorpusId);
            }
            if (this.uriFingerprint != 0) {
                output.writeUInt64(2, this.uriFingerprint);
            }
            if (this.usageTimestampStartSecs != 0) {
                output.writeUInt32(3, this.usageTimestampStartSecs);
            }
            if (this.usageTimestampEndSecs != 0) {
                output.writeUInt32(4, this.usageTimestampEndSecs);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.usageCorpusId != 0) {
                size += CodedOutputByteBufferNano.computeUInt64Size(1, this.usageCorpusId);
            }
            if (this.uriFingerprint != 0) {
                size += CodedOutputByteBufferNano.computeUInt64Size(2, this.uriFingerprint);
            }
            if (this.usageTimestampStartSecs != 0) {
                size += CodedOutputByteBufferNano.computeUInt32Size(3, this.usageTimestampStartSecs);
            }
            if (this.usageTimestampEndSecs != 0) {
                return size + CodedOutputByteBufferNano.computeUInt32Size(4, this.usageTimestampEndSecs);
            }
            return size;
        }

        public PendingDeleteUsageReport mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_GET_LINK /*8*/:
                        this.usageCorpusId = input.readUInt64();
                        continue;
                    case LogSource.GMS_CORE_PEOPLE /*16*/:
                        this.uriFingerprint = input.readUInt64();
                        continue;
                    case LogSource.LB_C /*24*/:
                        this.usageTimestampStartSecs = input.readUInt32();
                        continue;
                    case LogSource.SOCIAL /*32*/:
                        this.usageTimestampEndSecs = input.readUInt32();
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

        public static PendingDeleteUsageReport parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (PendingDeleteUsageReport) MessageNano.mergeFrom(new PendingDeleteUsageReport(), data);
        }

        public static PendingDeleteUsageReport parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new PendingDeleteUsageReport().mergeFrom(input);
        }
    }

    public static final class PhraseAffinityRequest extends ExtendableMessageNano<PhraseAffinityRequest> {
        private static volatile PhraseAffinityRequest[] _emptyArray;
        public Group[] groups;
        public String[] phrases;
        public QueryRequestSpec querySpec;

        public static final class Group extends ExtendableMessageNano<Group> {
            private static volatile Group[] _emptyArray;
            public int[] corpusIds;

            public static Group[] emptyArray() {
                if (_emptyArray == null) {
                    synchronized (InternalNano.LAZY_INIT_LOCK) {
                        if (_emptyArray == null) {
                            _emptyArray = new Group[0];
                        }
                    }
                }
                return _emptyArray;
            }

            public Group() {
                clear();
            }

            public Group clear() {
                this.corpusIds = WireFormatNano.EMPTY_INT_ARRAY;
                this.unknownFieldData = null;
                this.cachedSize = -1;
                return this;
            }

            public boolean equals(Object o) {
                if (o == this) {
                    return true;
                }
                if (!(o instanceof Group)) {
                    return false;
                }
                Group other = (Group) o;
                if (!InternalNano.equals(this.corpusIds, other.corpusIds)) {
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
                int hashCode = (((getClass().getName().hashCode() + 527) * 31) + InternalNano.hashCode(this.corpusIds)) * 31;
                int hashCode2 = (this.unknownFieldData == null || this.unknownFieldData.isEmpty()) ? 0 : this.unknownFieldData.hashCode();
                return hashCode + hashCode2;
            }

            public void writeTo(CodedOutputByteBufferNano output) throws IOException {
                if (this.corpusIds != null && this.corpusIds.length > 0) {
                    for (int writeUInt32 : this.corpusIds) {
                        output.writeUInt32(1, writeUInt32);
                    }
                }
                super.writeTo(output);
            }

            protected int computeSerializedSize() {
                int size = super.computeSerializedSize();
                if (this.corpusIds == null || this.corpusIds.length <= 0) {
                    return size;
                }
                int dataSize = 0;
                for (int element : this.corpusIds) {
                    dataSize += CodedOutputByteBufferNano.computeUInt32SizeNoTag(element);
                }
                return (size + dataSize) + (this.corpusIds.length * 1);
            }

            public Group mergeFrom(CodedInputByteBufferNano input) throws IOException {
                while (true) {
                    int tag = input.readTag();
                    int arrayLength;
                    int i;
                    int[] newArray;
                    switch (tag) {
                        case Action.UNKNOWN /*0*/:
                            break;
                        case Type.TAP_GET_LINK /*8*/:
                            arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 8);
                            i = this.corpusIds == null ? 0 : this.corpusIds.length;
                            newArray = new int[(i + arrayLength)];
                            if (i != 0) {
                                System.arraycopy(this.corpusIds, 0, newArray, 0, i);
                            }
                            while (i < newArray.length - 1) {
                                newArray[i] = input.readUInt32();
                                input.readTag();
                                i++;
                            }
                            newArray[i] = input.readUInt32();
                            this.corpusIds = newArray;
                            continue;
                        case Type.TAP_ABOUT /*10*/:
                            int limit = input.pushLimit(input.readRawVarint32());
                            arrayLength = 0;
                            int startPos = input.getPosition();
                            while (input.getBytesUntilLimit() > 0) {
                                input.readUInt32();
                                arrayLength++;
                            }
                            input.rewindToPosition(startPos);
                            i = this.corpusIds == null ? 0 : this.corpusIds.length;
                            newArray = new int[(i + arrayLength)];
                            if (i != 0) {
                                System.arraycopy(this.corpusIds, 0, newArray, 0, i);
                            }
                            while (i < newArray.length) {
                                newArray[i] = input.readUInt32();
                                i++;
                            }
                            this.corpusIds = newArray;
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

            public static Group parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
                return (Group) MessageNano.mergeFrom(new Group(), data);
            }

            public static Group parseFrom(CodedInputByteBufferNano input) throws IOException {
                return new Group().mergeFrom(input);
            }
        }

        public static PhraseAffinityRequest[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new PhraseAffinityRequest[0];
                    }
                }
            }
            return _emptyArray;
        }

        public PhraseAffinityRequest() {
            clear();
        }

        public PhraseAffinityRequest clear() {
            this.querySpec = null;
            this.phrases = WireFormatNano.EMPTY_STRING_ARRAY;
            this.groups = Group.emptyArray();
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof PhraseAffinityRequest)) {
                return false;
            }
            PhraseAffinityRequest other = (PhraseAffinityRequest) o;
            if (this.querySpec == null) {
                if (other.querySpec != null) {
                    return false;
                }
            } else if (!this.querySpec.equals(other.querySpec)) {
                return false;
            }
            if (!InternalNano.equals(this.phrases, other.phrases) || !InternalNano.equals(this.groups, other.groups)) {
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
            int hashCode = (((((((getClass().getName().hashCode() + 527) * 31) + (this.querySpec == null ? 0 : this.querySpec.hashCode())) * 31) + InternalNano.hashCode(this.phrases)) * 31) + InternalNano.hashCode(this.groups)) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.querySpec != null) {
                output.writeMessage(1, this.querySpec);
            }
            if (this.phrases != null && this.phrases.length > 0) {
                for (String element : this.phrases) {
                    if (element != null) {
                        output.writeString(2, element);
                    }
                }
            }
            if (this.groups != null && this.groups.length > 0) {
                for (Group element2 : this.groups) {
                    if (element2 != null) {
                        output.writeMessage(3, element2);
                    }
                }
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.querySpec != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(1, this.querySpec);
            }
            if (this.phrases != null && this.phrases.length > 0) {
                int dataCount = 0;
                int dataSize = 0;
                for (String element : this.phrases) {
                    if (element != null) {
                        dataCount++;
                        dataSize += CodedOutputByteBufferNano.computeStringSizeNoTag(element);
                    }
                }
                size = (size + dataSize) + (dataCount * 1);
            }
            if (this.groups != null && this.groups.length > 0) {
                for (Group element2 : this.groups) {
                    if (element2 != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(3, element2);
                    }
                }
            }
            return size;
        }

        public PhraseAffinityRequest mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                int arrayLength;
                int i;
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        if (this.querySpec == null) {
                            this.querySpec = new QueryRequestSpec();
                        }
                        input.readMessage(this.querySpec);
                        continue;
                    case LogSource.GOOGLE_ANALYTICS /*18*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 18);
                        i = this.phrases == null ? 0 : this.phrases.length;
                        String[] newArray = new String[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.phrases, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = input.readString();
                            input.readTag();
                            i++;
                        }
                        newArray[i] = input.readString();
                        this.phrases = newArray;
                        continue;
                    case LogSource.ANDROID_CAMERA /*26*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 26);
                        if (this.groups == null) {
                            i = 0;
                        } else {
                            i = this.groups.length;
                        }
                        Group[] newArray2 = new Group[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.groups, 0, newArray2, 0, i);
                        }
                        while (i < newArray2.length - 1) {
                            newArray2[i] = new Group();
                            input.readMessage(newArray2[i]);
                            input.readTag();
                            i++;
                        }
                        newArray2[i] = new Group();
                        input.readMessage(newArray2[i]);
                        this.groups = newArray2;
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

        public static PhraseAffinityRequest parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (PhraseAffinityRequest) MessageNano.mergeFrom(new PhraseAffinityRequest(), data);
        }

        public static PhraseAffinityRequest parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new PhraseAffinityRequest().mergeFrom(input);
        }
    }

    public static final class QueryReplayRecord extends ExtendableMessageNano<QueryReplayRecord> {
        private static volatile QueryReplayRecord[] _emptyArray;
        public GlobalRecord globalQueryRecord;
        public NonGlobalRecord nonGlobalQueryRecord;
        public SuggestRecord suggestRecord;

        public static final class GlobalRecord extends ExtendableMessageNano<GlobalRecord> {
            private static volatile GlobalRecord[] _emptyArray;
            public byte[] marshalledGlobalQueryCallRequest;
            public byte[] marshalledSearchResults;

            public static GlobalRecord[] emptyArray() {
                if (_emptyArray == null) {
                    synchronized (InternalNano.LAZY_INIT_LOCK) {
                        if (_emptyArray == null) {
                            _emptyArray = new GlobalRecord[0];
                        }
                    }
                }
                return _emptyArray;
            }

            public GlobalRecord() {
                clear();
            }

            public GlobalRecord clear() {
                this.marshalledGlobalQueryCallRequest = WireFormatNano.EMPTY_BYTES;
                this.marshalledSearchResults = WireFormatNano.EMPTY_BYTES;
                this.unknownFieldData = null;
                this.cachedSize = -1;
                return this;
            }

            public boolean equals(Object o) {
                if (o == this) {
                    return true;
                }
                if (!(o instanceof GlobalRecord)) {
                    return false;
                }
                GlobalRecord other = (GlobalRecord) o;
                if (!Arrays.equals(this.marshalledGlobalQueryCallRequest, other.marshalledGlobalQueryCallRequest) || !Arrays.equals(this.marshalledSearchResults, other.marshalledSearchResults)) {
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
                int hashCode = (((((getClass().getName().hashCode() + 527) * 31) + Arrays.hashCode(this.marshalledGlobalQueryCallRequest)) * 31) + Arrays.hashCode(this.marshalledSearchResults)) * 31;
                int hashCode2 = (this.unknownFieldData == null || this.unknownFieldData.isEmpty()) ? 0 : this.unknownFieldData.hashCode();
                return hashCode + hashCode2;
            }

            public void writeTo(CodedOutputByteBufferNano output) throws IOException {
                if (!Arrays.equals(this.marshalledGlobalQueryCallRequest, WireFormatNano.EMPTY_BYTES)) {
                    output.writeBytes(1, this.marshalledGlobalQueryCallRequest);
                }
                if (!Arrays.equals(this.marshalledSearchResults, WireFormatNano.EMPTY_BYTES)) {
                    output.writeBytes(2, this.marshalledSearchResults);
                }
                super.writeTo(output);
            }

            protected int computeSerializedSize() {
                int size = super.computeSerializedSize();
                if (!Arrays.equals(this.marshalledGlobalQueryCallRequest, WireFormatNano.EMPTY_BYTES)) {
                    size += CodedOutputByteBufferNano.computeBytesSize(1, this.marshalledGlobalQueryCallRequest);
                }
                if (Arrays.equals(this.marshalledSearchResults, WireFormatNano.EMPTY_BYTES)) {
                    return size;
                }
                return size + CodedOutputByteBufferNano.computeBytesSize(2, this.marshalledSearchResults);
            }

            public GlobalRecord mergeFrom(CodedInputByteBufferNano input) throws IOException {
                while (true) {
                    int tag = input.readTag();
                    switch (tag) {
                        case Action.UNKNOWN /*0*/:
                            break;
                        case Type.TAP_ABOUT /*10*/:
                            this.marshalledGlobalQueryCallRequest = input.readBytes();
                            continue;
                        case LogSource.GOOGLE_ANALYTICS /*18*/:
                            this.marshalledSearchResults = input.readBytes();
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

            public static GlobalRecord parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
                return (GlobalRecord) MessageNano.mergeFrom(new GlobalRecord(), data);
            }

            public static GlobalRecord parseFrom(CodedInputByteBufferNano input) throws IOException {
                return new GlobalRecord().mergeFrom(input);
            }
        }

        public static final class NonGlobalRecord extends ExtendableMessageNano<NonGlobalRecord> {
            private static volatile NonGlobalRecord[] _emptyArray;
            public byte[] marshalledQueryCallRequest;
            public byte[] marshalledSearchResults;

            public static NonGlobalRecord[] emptyArray() {
                if (_emptyArray == null) {
                    synchronized (InternalNano.LAZY_INIT_LOCK) {
                        if (_emptyArray == null) {
                            _emptyArray = new NonGlobalRecord[0];
                        }
                    }
                }
                return _emptyArray;
            }

            public NonGlobalRecord() {
                clear();
            }

            public NonGlobalRecord clear() {
                this.marshalledQueryCallRequest = WireFormatNano.EMPTY_BYTES;
                this.marshalledSearchResults = WireFormatNano.EMPTY_BYTES;
                this.unknownFieldData = null;
                this.cachedSize = -1;
                return this;
            }

            public boolean equals(Object o) {
                if (o == this) {
                    return true;
                }
                if (!(o instanceof NonGlobalRecord)) {
                    return false;
                }
                NonGlobalRecord other = (NonGlobalRecord) o;
                if (!Arrays.equals(this.marshalledQueryCallRequest, other.marshalledQueryCallRequest) || !Arrays.equals(this.marshalledSearchResults, other.marshalledSearchResults)) {
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
                int hashCode = (((((getClass().getName().hashCode() + 527) * 31) + Arrays.hashCode(this.marshalledQueryCallRequest)) * 31) + Arrays.hashCode(this.marshalledSearchResults)) * 31;
                int hashCode2 = (this.unknownFieldData == null || this.unknownFieldData.isEmpty()) ? 0 : this.unknownFieldData.hashCode();
                return hashCode + hashCode2;
            }

            public void writeTo(CodedOutputByteBufferNano output) throws IOException {
                if (!Arrays.equals(this.marshalledQueryCallRequest, WireFormatNano.EMPTY_BYTES)) {
                    output.writeBytes(1, this.marshalledQueryCallRequest);
                }
                if (!Arrays.equals(this.marshalledSearchResults, WireFormatNano.EMPTY_BYTES)) {
                    output.writeBytes(2, this.marshalledSearchResults);
                }
                super.writeTo(output);
            }

            protected int computeSerializedSize() {
                int size = super.computeSerializedSize();
                if (!Arrays.equals(this.marshalledQueryCallRequest, WireFormatNano.EMPTY_BYTES)) {
                    size += CodedOutputByteBufferNano.computeBytesSize(1, this.marshalledQueryCallRequest);
                }
                if (Arrays.equals(this.marshalledSearchResults, WireFormatNano.EMPTY_BYTES)) {
                    return size;
                }
                return size + CodedOutputByteBufferNano.computeBytesSize(2, this.marshalledSearchResults);
            }

            public NonGlobalRecord mergeFrom(CodedInputByteBufferNano input) throws IOException {
                while (true) {
                    int tag = input.readTag();
                    switch (tag) {
                        case Action.UNKNOWN /*0*/:
                            break;
                        case Type.TAP_ABOUT /*10*/:
                            this.marshalledQueryCallRequest = input.readBytes();
                            continue;
                        case LogSource.GOOGLE_ANALYTICS /*18*/:
                            this.marshalledSearchResults = input.readBytes();
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

            public static NonGlobalRecord parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
                return (NonGlobalRecord) MessageNano.mergeFrom(new NonGlobalRecord(), data);
            }

            public static NonGlobalRecord parseFrom(CodedInputByteBufferNano input) throws IOException {
                return new NonGlobalRecord().mergeFrom(input);
            }
        }

        public static final class SuggestRecord extends ExtendableMessageNano<SuggestRecord> {
            private static volatile SuggestRecord[] _emptyArray;
            public byte[] marshalledQuerySuggestCallRequest;
            public byte[] marshalledSuggestionResults;

            public static SuggestRecord[] emptyArray() {
                if (_emptyArray == null) {
                    synchronized (InternalNano.LAZY_INIT_LOCK) {
                        if (_emptyArray == null) {
                            _emptyArray = new SuggestRecord[0];
                        }
                    }
                }
                return _emptyArray;
            }

            public SuggestRecord() {
                clear();
            }

            public SuggestRecord clear() {
                this.marshalledQuerySuggestCallRequest = WireFormatNano.EMPTY_BYTES;
                this.marshalledSuggestionResults = WireFormatNano.EMPTY_BYTES;
                this.unknownFieldData = null;
                this.cachedSize = -1;
                return this;
            }

            public boolean equals(Object o) {
                if (o == this) {
                    return true;
                }
                if (!(o instanceof SuggestRecord)) {
                    return false;
                }
                SuggestRecord other = (SuggestRecord) o;
                if (!Arrays.equals(this.marshalledQuerySuggestCallRequest, other.marshalledQuerySuggestCallRequest) || !Arrays.equals(this.marshalledSuggestionResults, other.marshalledSuggestionResults)) {
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
                int hashCode = (((((getClass().getName().hashCode() + 527) * 31) + Arrays.hashCode(this.marshalledQuerySuggestCallRequest)) * 31) + Arrays.hashCode(this.marshalledSuggestionResults)) * 31;
                int hashCode2 = (this.unknownFieldData == null || this.unknownFieldData.isEmpty()) ? 0 : this.unknownFieldData.hashCode();
                return hashCode + hashCode2;
            }

            public void writeTo(CodedOutputByteBufferNano output) throws IOException {
                if (!Arrays.equals(this.marshalledQuerySuggestCallRequest, WireFormatNano.EMPTY_BYTES)) {
                    output.writeBytes(1, this.marshalledQuerySuggestCallRequest);
                }
                if (!Arrays.equals(this.marshalledSuggestionResults, WireFormatNano.EMPTY_BYTES)) {
                    output.writeBytes(2, this.marshalledSuggestionResults);
                }
                super.writeTo(output);
            }

            protected int computeSerializedSize() {
                int size = super.computeSerializedSize();
                if (!Arrays.equals(this.marshalledQuerySuggestCallRequest, WireFormatNano.EMPTY_BYTES)) {
                    size += CodedOutputByteBufferNano.computeBytesSize(1, this.marshalledQuerySuggestCallRequest);
                }
                if (Arrays.equals(this.marshalledSuggestionResults, WireFormatNano.EMPTY_BYTES)) {
                    return size;
                }
                return size + CodedOutputByteBufferNano.computeBytesSize(2, this.marshalledSuggestionResults);
            }

            public SuggestRecord mergeFrom(CodedInputByteBufferNano input) throws IOException {
                while (true) {
                    int tag = input.readTag();
                    switch (tag) {
                        case Action.UNKNOWN /*0*/:
                            break;
                        case Type.TAP_ABOUT /*10*/:
                            this.marshalledQuerySuggestCallRequest = input.readBytes();
                            continue;
                        case LogSource.GOOGLE_ANALYTICS /*18*/:
                            this.marshalledSuggestionResults = input.readBytes();
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

            public static SuggestRecord parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
                return (SuggestRecord) MessageNano.mergeFrom(new SuggestRecord(), data);
            }

            public static SuggestRecord parseFrom(CodedInputByteBufferNano input) throws IOException {
                return new SuggestRecord().mergeFrom(input);
            }
        }

        public static QueryReplayRecord[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new QueryReplayRecord[0];
                    }
                }
            }
            return _emptyArray;
        }

        public QueryReplayRecord() {
            clear();
        }

        public QueryReplayRecord clear() {
            this.globalQueryRecord = null;
            this.nonGlobalQueryRecord = null;
            this.suggestRecord = null;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof QueryReplayRecord)) {
                return false;
            }
            QueryReplayRecord other = (QueryReplayRecord) o;
            if (this.globalQueryRecord == null) {
                if (other.globalQueryRecord != null) {
                    return false;
                }
            } else if (!this.globalQueryRecord.equals(other.globalQueryRecord)) {
                return false;
            }
            if (this.nonGlobalQueryRecord == null) {
                if (other.nonGlobalQueryRecord != null) {
                    return false;
                }
            } else if (!this.nonGlobalQueryRecord.equals(other.nonGlobalQueryRecord)) {
                return false;
            }
            if (this.suggestRecord == null) {
                if (other.suggestRecord != null) {
                    return false;
                }
            } else if (!this.suggestRecord.equals(other.suggestRecord)) {
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
            int hashCode = (((((((getClass().getName().hashCode() + 527) * 31) + (this.globalQueryRecord == null ? 0 : this.globalQueryRecord.hashCode())) * 31) + (this.nonGlobalQueryRecord == null ? 0 : this.nonGlobalQueryRecord.hashCode())) * 31) + (this.suggestRecord == null ? 0 : this.suggestRecord.hashCode())) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.globalQueryRecord != null) {
                output.writeMessage(1, this.globalQueryRecord);
            }
            if (this.nonGlobalQueryRecord != null) {
                output.writeMessage(2, this.nonGlobalQueryRecord);
            }
            if (this.suggestRecord != null) {
                output.writeMessage(3, this.suggestRecord);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.globalQueryRecord != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(1, this.globalQueryRecord);
            }
            if (this.nonGlobalQueryRecord != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(2, this.nonGlobalQueryRecord);
            }
            if (this.suggestRecord != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(3, this.suggestRecord);
            }
            return size;
        }

        public QueryReplayRecord mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        if (this.globalQueryRecord == null) {
                            this.globalQueryRecord = new GlobalRecord();
                        }
                        input.readMessage(this.globalQueryRecord);
                        continue;
                    case LogSource.GOOGLE_ANALYTICS /*18*/:
                        if (this.nonGlobalQueryRecord == null) {
                            this.nonGlobalQueryRecord = new NonGlobalRecord();
                        }
                        input.readMessage(this.nonGlobalQueryRecord);
                        continue;
                    case LogSource.ANDROID_CAMERA /*26*/:
                        if (this.suggestRecord == null) {
                            this.suggestRecord = new SuggestRecord();
                        }
                        input.readMessage(this.suggestRecord);
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

        public static QueryReplayRecord parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (QueryReplayRecord) MessageNano.mergeFrom(new QueryReplayRecord(), data);
        }

        public static QueryReplayRecord parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new QueryReplayRecord().mergeFrom(input);
        }
    }

    public static final class QueryRequestSpec extends ExtendableMessageNano<QueryRequestSpec> {
        private static volatile QueryRequestSpec[] _emptyArray;
        public CorpusSpec[] corpusSpec;
        public int debugLevel;
        public boolean filterUsingSectionWeights;
        public boolean noCorpusFilter;
        public boolean prefixMatch;
        public int queryTokenizer;
        public int rankingStrategy;
        public boolean returnPerCorpusResults;
        public Scoring scoringFlags;
        public int scoringVerbosityLevel;
        public SectionMapping[] sectionMapping;
        public SectionWeight[] sectionWeight;
        public boolean universalSearch;
        public boolean variantMatch;
        public boolean wantClientScores;
        public boolean wantUris;

        public static final class CorpusSpec extends ExtendableMessageNano<CorpusSpec> {
            private static volatile CorpusSpec[] _emptyArray;
            public int corpusId;
            public int maxResults;
            public SectionSpec[] sectionSpec;
            public String[] tag;
            public UniversalSectionMapping[] universalSectionMappings;
            public double weight;

            public static CorpusSpec[] emptyArray() {
                if (_emptyArray == null) {
                    synchronized (InternalNano.LAZY_INIT_LOCK) {
                        if (_emptyArray == null) {
                            _emptyArray = new CorpusSpec[0];
                        }
                    }
                }
                return _emptyArray;
            }

            public CorpusSpec() {
                clear();
            }

            public CorpusSpec clear() {
                this.corpusId = 0;
                this.sectionSpec = SectionSpec.emptyArray();
                this.tag = WireFormatNano.EMPTY_STRING_ARRAY;
                this.universalSectionMappings = UniversalSectionMapping.emptyArray();
                this.weight = 1.0d;
                this.maxResults = 0;
                this.unknownFieldData = null;
                this.cachedSize = -1;
                return this;
            }

            public boolean equals(Object o) {
                if (o == this) {
                    return true;
                }
                if (!(o instanceof CorpusSpec)) {
                    return false;
                }
                CorpusSpec other = (CorpusSpec) o;
                if (this.corpusId != other.corpusId || !InternalNano.equals(this.sectionSpec, other.sectionSpec) || !InternalNano.equals(this.tag, other.tag) || !InternalNano.equals(this.universalSectionMappings, other.universalSectionMappings) || Double.doubleToLongBits(this.weight) != Double.doubleToLongBits(other.weight) || this.maxResults != other.maxResults) {
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
                int result = ((((((((getClass().getName().hashCode() + 527) * 31) + this.corpusId) * 31) + InternalNano.hashCode(this.sectionSpec)) * 31) + InternalNano.hashCode(this.tag)) * 31) + InternalNano.hashCode(this.universalSectionMappings);
                long v = Double.doubleToLongBits(this.weight);
                int i = ((((result * 31) + ((int) ((v >>> 32) ^ v))) * 31) + this.maxResults) * 31;
                int hashCode = (this.unknownFieldData == null || this.unknownFieldData.isEmpty()) ? 0 : this.unknownFieldData.hashCode();
                return i + hashCode;
            }

            public void writeTo(CodedOutputByteBufferNano output) throws IOException {
                if (this.corpusId != 0) {
                    output.writeInt32(1, this.corpusId);
                }
                if (this.sectionSpec != null && this.sectionSpec.length > 0) {
                    for (SectionSpec element : this.sectionSpec) {
                        if (element != null) {
                            output.writeMessage(2, element);
                        }
                    }
                }
                if (this.tag != null && this.tag.length > 0) {
                    for (String element2 : this.tag) {
                        if (element2 != null) {
                            output.writeString(3, element2);
                        }
                    }
                }
                if (this.universalSectionMappings != null && this.universalSectionMappings.length > 0) {
                    for (UniversalSectionMapping element3 : this.universalSectionMappings) {
                        if (element3 != null) {
                            output.writeMessage(4, element3);
                        }
                    }
                }
                if (Double.doubleToLongBits(this.weight) != Double.doubleToLongBits(1.0d)) {
                    output.writeDouble(5, this.weight);
                }
                if (this.maxResults != 0) {
                    output.writeInt32(6, this.maxResults);
                }
                super.writeTo(output);
            }

            protected int computeSerializedSize() {
                int size = super.computeSerializedSize();
                if (this.corpusId != 0) {
                    size += CodedOutputByteBufferNano.computeInt32Size(1, this.corpusId);
                }
                if (this.sectionSpec != null && this.sectionSpec.length > 0) {
                    for (SectionSpec element : this.sectionSpec) {
                        if (element != null) {
                            size += CodedOutputByteBufferNano.computeMessageSize(2, element);
                        }
                    }
                }
                if (this.tag != null && this.tag.length > 0) {
                    int dataCount = 0;
                    int dataSize = 0;
                    for (String element2 : this.tag) {
                        if (element2 != null) {
                            dataCount++;
                            dataSize += CodedOutputByteBufferNano.computeStringSizeNoTag(element2);
                        }
                    }
                    size = (size + dataSize) + (dataCount * 1);
                }
                if (this.universalSectionMappings != null && this.universalSectionMappings.length > 0) {
                    for (UniversalSectionMapping element3 : this.universalSectionMappings) {
                        if (element3 != null) {
                            size += CodedOutputByteBufferNano.computeMessageSize(4, element3);
                        }
                    }
                }
                if (Double.doubleToLongBits(this.weight) != Double.doubleToLongBits(1.0d)) {
                    size += CodedOutputByteBufferNano.computeDoubleSize(5, this.weight);
                }
                if (this.maxResults != 0) {
                    return size + CodedOutputByteBufferNano.computeInt32Size(6, this.maxResults);
                }
                return size;
            }

            public CorpusSpec mergeFrom(CodedInputByteBufferNano input) throws IOException {
                while (true) {
                    int tag = input.readTag();
                    int arrayLength;
                    int i;
                    switch (tag) {
                        case Action.UNKNOWN /*0*/:
                            break;
                        case Type.TAP_GET_LINK /*8*/:
                            this.corpusId = input.readInt32();
                            continue;
                        case LogSource.GOOGLE_ANALYTICS /*18*/:
                            arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 18);
                            if (this.sectionSpec == null) {
                                i = 0;
                            } else {
                                i = this.sectionSpec.length;
                            }
                            SectionSpec[] newArray = new SectionSpec[(i + arrayLength)];
                            if (i != 0) {
                                System.arraycopy(this.sectionSpec, 0, newArray, 0, i);
                            }
                            while (i < newArray.length - 1) {
                                newArray[i] = new SectionSpec();
                                input.readMessage(newArray[i]);
                                input.readTag();
                                i++;
                            }
                            newArray[i] = new SectionSpec();
                            input.readMessage(newArray[i]);
                            this.sectionSpec = newArray;
                            continue;
                        case LogSource.ANDROID_CAMERA /*26*/:
                            arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 26);
                            i = this.tag == null ? 0 : this.tag.length;
                            String[] newArray2 = new String[(i + arrayLength)];
                            if (i != 0) {
                                System.arraycopy(this.tag, 0, newArray2, 0, i);
                            }
                            while (i < newArray2.length - 1) {
                                newArray2[i] = input.readString();
                                input.readTag();
                                i++;
                            }
                            newArray2[i] = input.readString();
                            this.tag = newArray2;
                            continue;
                        case LogSource.NOVA /*34*/:
                            arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 34);
                            if (this.universalSectionMappings == null) {
                                i = 0;
                            } else {
                                i = this.universalSectionMappings.length;
                            }
                            UniversalSectionMapping[] newArray3 = new UniversalSectionMapping[(i + arrayLength)];
                            if (i != 0) {
                                System.arraycopy(this.universalSectionMappings, 0, newArray3, 0, i);
                            }
                            while (i < newArray3.length - 1) {
                                newArray3[i] = new UniversalSectionMapping();
                                input.readMessage(newArray3[i]);
                                input.readTag();
                                i++;
                            }
                            newArray3[i] = new UniversalSectionMapping();
                            input.readMessage(newArray3[i]);
                            this.universalSectionMappings = newArray3;
                            continue;
                        case LogSource.SOCIAL_AFFINITY /*41*/:
                            this.weight = input.readDouble();
                            continue;
                        case LogSource.BACKDROP /*48*/:
                            this.maxResults = input.readInt32();
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

            public static CorpusSpec parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
                return (CorpusSpec) MessageNano.mergeFrom(new CorpusSpec(), data);
            }

            public static CorpusSpec parseFrom(CodedInputByteBufferNano input) throws IOException {
                return new CorpusSpec().mergeFrom(input);
            }
        }

        public interface QueryTokenizer {
            public static final int QUERY_LANGUAGE = 0;
            public static final int WHITESPACE = 1;
        }

        public interface RankingStrategy {
            public static final int CHROME_OMNIBOX = 3;
            public static final int DOC_SCORE = 0;
            public static final int MOST_RECENTLY_USED = 2;
            public static final int RELEVANCE = 1;
        }

        public static final class Scoring extends ExtendableMessageNano<Scoring> {
            private static volatile Scoring[] _emptyArray;
            public boolean disableScoreBoosting;
            public int normalization;
            public int omniboxUseScoreCap;

            public interface Normalization {
                public static final int NORM_ALL_SUM = 1;
                public static final int NORM_DEFAULT = 0;
            }

            public static Scoring[] emptyArray() {
                if (_emptyArray == null) {
                    synchronized (InternalNano.LAZY_INIT_LOCK) {
                        if (_emptyArray == null) {
                            _emptyArray = new Scoring[0];
                        }
                    }
                }
                return _emptyArray;
            }

            public Scoring() {
                clear();
            }

            public Scoring clear() {
                this.normalization = 0;
                this.omniboxUseScoreCap = 20000;
                this.disableScoreBoosting = false;
                this.unknownFieldData = null;
                this.cachedSize = -1;
                return this;
            }

            public boolean equals(Object o) {
                if (o == this) {
                    return true;
                }
                if (!(o instanceof Scoring)) {
                    return false;
                }
                Scoring other = (Scoring) o;
                if (this.normalization != other.normalization || this.omniboxUseScoreCap != other.omniboxUseScoreCap || this.disableScoreBoosting != other.disableScoreBoosting) {
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
                int hashCode = (((((((getClass().getName().hashCode() + 527) * 31) + this.normalization) * 31) + this.omniboxUseScoreCap) * 31) + (this.disableScoreBoosting ? 1231 : 1237)) * 31;
                int hashCode2 = (this.unknownFieldData == null || this.unknownFieldData.isEmpty()) ? 0 : this.unknownFieldData.hashCode();
                return hashCode + hashCode2;
            }

            public void writeTo(CodedOutputByteBufferNano output) throws IOException {
                if (this.normalization != 0) {
                    output.writeInt32(1, this.normalization);
                }
                if (this.omniboxUseScoreCap != 20000) {
                    output.writeUInt32(2, this.omniboxUseScoreCap);
                }
                if (this.disableScoreBoosting) {
                    output.writeBool(4, this.disableScoreBoosting);
                }
                super.writeTo(output);
            }

            protected int computeSerializedSize() {
                int size = super.computeSerializedSize();
                if (this.normalization != 0) {
                    size += CodedOutputByteBufferNano.computeInt32Size(1, this.normalization);
                }
                if (this.omniboxUseScoreCap != 20000) {
                    size += CodedOutputByteBufferNano.computeUInt32Size(2, this.omniboxUseScoreCap);
                }
                if (this.disableScoreBoosting) {
                    return size + CodedOutputByteBufferNano.computeBoolSize(4, this.disableScoreBoosting);
                }
                return size;
            }

            public Scoring mergeFrom(CodedInputByteBufferNano input) throws IOException {
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
                                    this.normalization = value;
                                    break;
                                default:
                                    continue;
                            }
                        case LogSource.GMS_CORE_PEOPLE /*16*/:
                            this.omniboxUseScoreCap = input.readUInt32();
                            continue;
                        case LogSource.SOCIAL /*32*/:
                            this.disableScoreBoosting = input.readBool();
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

            public static Scoring parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
                return (Scoring) MessageNano.mergeFrom(new Scoring(), data);
            }

            public static Scoring parseFrom(CodedInputByteBufferNano input) throws IOException {
                return new Scoring().mergeFrom(input);
            }
        }

        public static final class SectionMapping extends ExtendableMessageNano<SectionMapping> {
            private static volatile SectionMapping[] _emptyArray;
            public int corpusId;
            public String name;
            public int sectionId;

            public static SectionMapping[] emptyArray() {
                if (_emptyArray == null) {
                    synchronized (InternalNano.LAZY_INIT_LOCK) {
                        if (_emptyArray == null) {
                            _emptyArray = new SectionMapping[0];
                        }
                    }
                }
                return _emptyArray;
            }

            public SectionMapping() {
                clear();
            }

            public SectionMapping clear() {
                this.name = BuildConfig.VERSION_NAME;
                this.corpusId = 0;
                this.sectionId = 0;
                this.unknownFieldData = null;
                this.cachedSize = -1;
                return this;
            }

            public boolean equals(Object o) {
                if (o == this) {
                    return true;
                }
                if (!(o instanceof SectionMapping)) {
                    return false;
                }
                SectionMapping other = (SectionMapping) o;
                if (this.name == null) {
                    if (other.name != null) {
                        return false;
                    }
                } else if (!this.name.equals(other.name)) {
                    return false;
                }
                if (this.corpusId != other.corpusId || this.sectionId != other.sectionId) {
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
                int hashCode = (((((((getClass().getName().hashCode() + 527) * 31) + (this.name == null ? 0 : this.name.hashCode())) * 31) + this.corpusId) * 31) + this.sectionId) * 31;
                if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                    i = this.unknownFieldData.hashCode();
                }
                return hashCode + i;
            }

            public void writeTo(CodedOutputByteBufferNano output) throws IOException {
                if (!this.name.equals(BuildConfig.VERSION_NAME)) {
                    output.writeString(1, this.name);
                }
                if (this.corpusId != 0) {
                    output.writeUInt32(2, this.corpusId);
                }
                if (this.sectionId != 0) {
                    output.writeUInt32(3, this.sectionId);
                }
                super.writeTo(output);
            }

            protected int computeSerializedSize() {
                int size = super.computeSerializedSize();
                if (!this.name.equals(BuildConfig.VERSION_NAME)) {
                    size += CodedOutputByteBufferNano.computeStringSize(1, this.name);
                }
                if (this.corpusId != 0) {
                    size += CodedOutputByteBufferNano.computeUInt32Size(2, this.corpusId);
                }
                if (this.sectionId != 0) {
                    return size + CodedOutputByteBufferNano.computeUInt32Size(3, this.sectionId);
                }
                return size;
            }

            public SectionMapping mergeFrom(CodedInputByteBufferNano input) throws IOException {
                while (true) {
                    int tag = input.readTag();
                    switch (tag) {
                        case Action.UNKNOWN /*0*/:
                            break;
                        case Type.TAP_ABOUT /*10*/:
                            this.name = input.readString();
                            continue;
                        case LogSource.GMS_CORE_PEOPLE /*16*/:
                            this.corpusId = input.readUInt32();
                            continue;
                        case LogSource.LB_C /*24*/:
                            this.sectionId = input.readUInt32();
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

            public static SectionMapping parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
                return (SectionMapping) MessageNano.mergeFrom(new SectionMapping(), data);
            }

            public static SectionMapping parseFrom(CodedInputByteBufferNano input) throws IOException {
                return new SectionMapping().mergeFrom(input);
            }
        }

        public static final class SectionSpec extends ExtendableMessageNano<SectionSpec> {
            private static volatile SectionSpec[] _emptyArray;
            public int sectionId;
            public boolean snippet;
            public int snippetLength;
            public boolean snippetStripHtml;

            public static SectionSpec[] emptyArray() {
                if (_emptyArray == null) {
                    synchronized (InternalNano.LAZY_INIT_LOCK) {
                        if (_emptyArray == null) {
                            _emptyArray = new SectionSpec[0];
                        }
                    }
                }
                return _emptyArray;
            }

            public SectionSpec() {
                clear();
            }

            public SectionSpec clear() {
                this.sectionId = 0;
                this.snippet = false;
                this.snippetStripHtml = true;
                this.snippetLength = 0;
                this.unknownFieldData = null;
                this.cachedSize = -1;
                return this;
            }

            public boolean equals(Object o) {
                if (o == this) {
                    return true;
                }
                if (!(o instanceof SectionSpec)) {
                    return false;
                }
                SectionSpec other = (SectionSpec) o;
                if (this.sectionId != other.sectionId || this.snippet != other.snippet || this.snippetStripHtml != other.snippetStripHtml || this.snippetLength != other.snippetLength) {
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
                int i = 1231;
                int hashCode = (((((getClass().getName().hashCode() + 527) * 31) + this.sectionId) * 31) + (this.snippet ? 1231 : 1237)) * 31;
                if (!this.snippetStripHtml) {
                    i = 1237;
                }
                i = (((hashCode + i) * 31) + this.snippetLength) * 31;
                hashCode = (this.unknownFieldData == null || this.unknownFieldData.isEmpty()) ? 0 : this.unknownFieldData.hashCode();
                return i + hashCode;
            }

            public void writeTo(CodedOutputByteBufferNano output) throws IOException {
                if (this.sectionId != 0) {
                    output.writeUInt32(1, this.sectionId);
                }
                if (this.snippet) {
                    output.writeBool(2, this.snippet);
                }
                if (!this.snippetStripHtml) {
                    output.writeBool(3, this.snippetStripHtml);
                }
                if (this.snippetLength != 0) {
                    output.writeUInt32(4, this.snippetLength);
                }
                super.writeTo(output);
            }

            protected int computeSerializedSize() {
                int size = super.computeSerializedSize();
                if (this.sectionId != 0) {
                    size += CodedOutputByteBufferNano.computeUInt32Size(1, this.sectionId);
                }
                if (this.snippet) {
                    size += CodedOutputByteBufferNano.computeBoolSize(2, this.snippet);
                }
                if (!this.snippetStripHtml) {
                    size += CodedOutputByteBufferNano.computeBoolSize(3, this.snippetStripHtml);
                }
                if (this.snippetLength != 0) {
                    return size + CodedOutputByteBufferNano.computeUInt32Size(4, this.snippetLength);
                }
                return size;
            }

            public SectionSpec mergeFrom(CodedInputByteBufferNano input) throws IOException {
                while (true) {
                    int tag = input.readTag();
                    switch (tag) {
                        case Action.UNKNOWN /*0*/:
                            break;
                        case Type.TAP_GET_LINK /*8*/:
                            this.sectionId = input.readUInt32();
                            continue;
                        case LogSource.GMS_CORE_PEOPLE /*16*/:
                            this.snippet = input.readBool();
                            continue;
                        case LogSource.LB_C /*24*/:
                            this.snippetStripHtml = input.readBool();
                            continue;
                        case LogSource.SOCIAL /*32*/:
                            this.snippetLength = input.readUInt32();
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

            public static SectionSpec parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
                return (SectionSpec) MessageNano.mergeFrom(new SectionSpec(), data);
            }

            public static SectionSpec parseFrom(CodedInputByteBufferNano input) throws IOException {
                return new SectionSpec().mergeFrom(input);
            }
        }

        public static final class SectionWeight extends ExtendableMessageNano<SectionWeight> {
            private static volatile SectionWeight[] _emptyArray;
            public int corpusId;
            public int omniboxSectionType;
            public int sectionId;
            public double weight;

            public static SectionWeight[] emptyArray() {
                if (_emptyArray == null) {
                    synchronized (InternalNano.LAZY_INIT_LOCK) {
                        if (_emptyArray == null) {
                            _emptyArray = new SectionWeight[0];
                        }
                    }
                }
                return _emptyArray;
            }

            public SectionWeight() {
                clear();
            }

            public SectionWeight clear() {
                this.corpusId = 0;
                this.sectionId = 0;
                this.weight = 0.0d;
                this.omniboxSectionType = 0;
                this.unknownFieldData = null;
                this.cachedSize = -1;
                return this;
            }

            public boolean equals(Object o) {
                if (o == this) {
                    return true;
                }
                if (!(o instanceof SectionWeight)) {
                    return false;
                }
                SectionWeight other = (SectionWeight) o;
                if (this.corpusId != other.corpusId || this.sectionId != other.sectionId || Double.doubleToLongBits(this.weight) != Double.doubleToLongBits(other.weight) || this.omniboxSectionType != other.omniboxSectionType) {
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
                int result = ((((getClass().getName().hashCode() + 527) * 31) + this.corpusId) * 31) + this.sectionId;
                long v = Double.doubleToLongBits(this.weight);
                int i = ((((result * 31) + ((int) ((v >>> 32) ^ v))) * 31) + this.omniboxSectionType) * 31;
                int hashCode = (this.unknownFieldData == null || this.unknownFieldData.isEmpty()) ? 0 : this.unknownFieldData.hashCode();
                return i + hashCode;
            }

            public void writeTo(CodedOutputByteBufferNano output) throws IOException {
                if (this.corpusId != 0) {
                    output.writeUInt32(1, this.corpusId);
                }
                if (this.sectionId != 0) {
                    output.writeUInt32(2, this.sectionId);
                }
                if (Double.doubleToLongBits(this.weight) != Double.doubleToLongBits(0.0d)) {
                    output.writeDouble(3, this.weight);
                }
                if (this.omniboxSectionType != 0) {
                    output.writeInt32(4, this.omniboxSectionType);
                }
                super.writeTo(output);
            }

            protected int computeSerializedSize() {
                int size = super.computeSerializedSize();
                if (this.corpusId != 0) {
                    size += CodedOutputByteBufferNano.computeUInt32Size(1, this.corpusId);
                }
                if (this.sectionId != 0) {
                    size += CodedOutputByteBufferNano.computeUInt32Size(2, this.sectionId);
                }
                if (Double.doubleToLongBits(this.weight) != Double.doubleToLongBits(0.0d)) {
                    size += CodedOutputByteBufferNano.computeDoubleSize(3, this.weight);
                }
                if (this.omniboxSectionType != 0) {
                    return size + CodedOutputByteBufferNano.computeInt32Size(4, this.omniboxSectionType);
                }
                return size;
            }

            public SectionWeight mergeFrom(CodedInputByteBufferNano input) throws IOException {
                while (true) {
                    int tag = input.readTag();
                    switch (tag) {
                        case Action.UNKNOWN /*0*/:
                            break;
                        case Type.TAP_GET_LINK /*8*/:
                            this.corpusId = input.readUInt32();
                            continue;
                        case LogSource.GMS_CORE_PEOPLE /*16*/:
                            this.sectionId = input.readUInt32();
                            continue;
                        case LogSource.ANDROID_AUTH /*25*/:
                            this.weight = input.readDouble();
                            continue;
                        case LogSource.SOCIAL /*32*/:
                            int value = input.readInt32();
                            switch (value) {
                                case Action.UNKNOWN /*0*/:
                                case TimeSelection.Type.TEMPORARY /*1*/:
                                case TimeSelection.Type.INDEFINITELY /*2*/:
                                    this.omniboxSectionType = value;
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

            public static SectionWeight parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
                return (SectionWeight) MessageNano.mergeFrom(new SectionWeight(), data);
            }

            public static SectionWeight parseFrom(CodedInputByteBufferNano input) throws IOException {
                return new SectionWeight().mergeFrom(input);
            }
        }

        public static QueryRequestSpec[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new QueryRequestSpec[0];
                    }
                }
            }
            return _emptyArray;
        }

        public QueryRequestSpec() {
            clear();
        }

        public QueryRequestSpec clear() {
            this.corpusSpec = CorpusSpec.emptyArray();
            this.noCorpusFilter = false;
            this.sectionMapping = SectionMapping.emptyArray();
            this.wantUris = false;
            this.sectionWeight = SectionWeight.emptyArray();
            this.scoringVerbosityLevel = 0;
            this.universalSearch = false;
            this.prefixMatch = false;
            this.variantMatch = true;
            this.debugLevel = 0;
            this.rankingStrategy = 0;
            this.filterUsingSectionWeights = false;
            this.wantClientScores = false;
            this.scoringFlags = null;
            this.queryTokenizer = 0;
            this.returnPerCorpusResults = false;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof QueryRequestSpec)) {
                return false;
            }
            QueryRequestSpec other = (QueryRequestSpec) o;
            if (!InternalNano.equals(this.corpusSpec, other.corpusSpec) || this.noCorpusFilter != other.noCorpusFilter || !InternalNano.equals(this.sectionMapping, other.sectionMapping) || this.wantUris != other.wantUris || !InternalNano.equals(this.sectionWeight, other.sectionWeight) || this.scoringVerbosityLevel != other.scoringVerbosityLevel || this.universalSearch != other.universalSearch || this.prefixMatch != other.prefixMatch || this.variantMatch != other.variantMatch || this.debugLevel != other.debugLevel || this.rankingStrategy != other.rankingStrategy || this.filterUsingSectionWeights != other.filterUsingSectionWeights || this.wantClientScores != other.wantClientScores) {
                return false;
            }
            if (this.scoringFlags == null) {
                if (other.scoringFlags != null) {
                    return false;
                }
            } else if (!this.scoringFlags.equals(other.scoringFlags)) {
                return false;
            }
            if (this.queryTokenizer != other.queryTokenizer || this.returnPerCorpusResults != other.returnPerCorpusResults) {
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
            int i3 = 1231;
            int hashCode = (((((((getClass().getName().hashCode() + 527) * 31) + InternalNano.hashCode(this.corpusSpec)) * 31) + (this.noCorpusFilter ? 1231 : 1237)) * 31) + InternalNano.hashCode(this.sectionMapping)) * 31;
            if (this.wantUris) {
                i = 1231;
            } else {
                i = 1237;
            }
            hashCode = (((((hashCode + i) * 31) + InternalNano.hashCode(this.sectionWeight)) * 31) + this.scoringVerbosityLevel) * 31;
            if (this.universalSearch) {
                i = 1231;
            } else {
                i = 1237;
            }
            hashCode = (hashCode + i) * 31;
            if (this.prefixMatch) {
                i = 1231;
            } else {
                i = 1237;
            }
            hashCode = (hashCode + i) * 31;
            if (this.variantMatch) {
                i = 1231;
            } else {
                i = 1237;
            }
            hashCode = (((((hashCode + i) * 31) + this.debugLevel) * 31) + this.rankingStrategy) * 31;
            if (this.filterUsingSectionWeights) {
                i = 1231;
            } else {
                i = 1237;
            }
            hashCode = (hashCode + i) * 31;
            if (this.wantClientScores) {
                i = 1231;
            } else {
                i = 1237;
            }
            i = (((((hashCode + i) * 31) + (this.scoringFlags == null ? 0 : this.scoringFlags.hashCode())) * 31) + this.queryTokenizer) * 31;
            if (!this.returnPerCorpusResults) {
                i3 = 1237;
            }
            i = (i + i3) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i2 = this.unknownFieldData.hashCode();
            }
            return i + i2;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.corpusSpec != null && this.corpusSpec.length > 0) {
                for (CorpusSpec element : this.corpusSpec) {
                    if (element != null) {
                        output.writeMessage(1, element);
                    }
                }
            }
            if (this.noCorpusFilter) {
                output.writeBool(2, this.noCorpusFilter);
            }
            if (this.sectionMapping != null && this.sectionMapping.length > 0) {
                for (SectionMapping element2 : this.sectionMapping) {
                    if (element2 != null) {
                        output.writeMessage(3, element2);
                    }
                }
            }
            if (this.wantUris) {
                output.writeBool(4, this.wantUris);
            }
            if (this.sectionWeight != null && this.sectionWeight.length > 0) {
                for (SectionWeight element3 : this.sectionWeight) {
                    if (element3 != null) {
                        output.writeMessage(5, element3);
                    }
                }
            }
            if (this.scoringVerbosityLevel != 0) {
                output.writeInt32(6, this.scoringVerbosityLevel);
            }
            if (this.universalSearch) {
                output.writeBool(7, this.universalSearch);
            }
            if (this.prefixMatch) {
                output.writeBool(8, this.prefixMatch);
            }
            if (!this.variantMatch) {
                output.writeBool(9, this.variantMatch);
            }
            if (this.debugLevel != 0) {
                output.writeInt32(10, this.debugLevel);
            }
            if (this.rankingStrategy != 0) {
                output.writeInt32(11, this.rankingStrategy);
            }
            if (this.filterUsingSectionWeights) {
                output.writeBool(12, this.filterUsingSectionWeights);
            }
            if (this.wantClientScores) {
                output.writeBool(13, this.wantClientScores);
            }
            if (this.scoringFlags != null) {
                output.writeMessage(14, this.scoringFlags);
            }
            if (this.queryTokenizer != 0) {
                output.writeInt32(15, this.queryTokenizer);
            }
            if (this.returnPerCorpusResults) {
                output.writeBool(16, this.returnPerCorpusResults);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.corpusSpec != null && this.corpusSpec.length > 0) {
                for (CorpusSpec element : this.corpusSpec) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(1, element);
                    }
                }
            }
            if (this.noCorpusFilter) {
                size += CodedOutputByteBufferNano.computeBoolSize(2, this.noCorpusFilter);
            }
            if (this.sectionMapping != null && this.sectionMapping.length > 0) {
                for (SectionMapping element2 : this.sectionMapping) {
                    if (element2 != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(3, element2);
                    }
                }
            }
            if (this.wantUris) {
                size += CodedOutputByteBufferNano.computeBoolSize(4, this.wantUris);
            }
            if (this.sectionWeight != null && this.sectionWeight.length > 0) {
                for (SectionWeight element3 : this.sectionWeight) {
                    if (element3 != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(5, element3);
                    }
                }
            }
            if (this.scoringVerbosityLevel != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(6, this.scoringVerbosityLevel);
            }
            if (this.universalSearch) {
                size += CodedOutputByteBufferNano.computeBoolSize(7, this.universalSearch);
            }
            if (this.prefixMatch) {
                size += CodedOutputByteBufferNano.computeBoolSize(8, this.prefixMatch);
            }
            if (!this.variantMatch) {
                size += CodedOutputByteBufferNano.computeBoolSize(9, this.variantMatch);
            }
            if (this.debugLevel != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(10, this.debugLevel);
            }
            if (this.rankingStrategy != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(11, this.rankingStrategy);
            }
            if (this.filterUsingSectionWeights) {
                size += CodedOutputByteBufferNano.computeBoolSize(12, this.filterUsingSectionWeights);
            }
            if (this.wantClientScores) {
                size += CodedOutputByteBufferNano.computeBoolSize(13, this.wantClientScores);
            }
            if (this.scoringFlags != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(14, this.scoringFlags);
            }
            if (this.queryTokenizer != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(15, this.queryTokenizer);
            }
            if (this.returnPerCorpusResults) {
                return size + CodedOutputByteBufferNano.computeBoolSize(16, this.returnPerCorpusResults);
            }
            return size;
        }

        public QueryRequestSpec mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                int arrayLength;
                int i;
                int value;
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 10);
                        if (this.corpusSpec == null) {
                            i = 0;
                        } else {
                            i = this.corpusSpec.length;
                        }
                        CorpusSpec[] newArray = new CorpusSpec[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.corpusSpec, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new CorpusSpec();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i++;
                        }
                        newArray[i] = new CorpusSpec();
                        input.readMessage(newArray[i]);
                        this.corpusSpec = newArray;
                        continue;
                    case LogSource.GMS_CORE_PEOPLE /*16*/:
                        this.noCorpusFilter = input.readBool();
                        continue;
                    case LogSource.ANDROID_CAMERA /*26*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 26);
                        if (this.sectionMapping == null) {
                            i = 0;
                        } else {
                            i = this.sectionMapping.length;
                        }
                        SectionMapping[] newArray2 = new SectionMapping[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.sectionMapping, 0, newArray2, 0, i);
                        }
                        while (i < newArray2.length - 1) {
                            newArray2[i] = new SectionMapping();
                            input.readMessage(newArray2[i]);
                            input.readTag();
                            i++;
                        }
                        newArray2[i] = new SectionMapping();
                        input.readMessage(newArray2[i]);
                        this.sectionMapping = newArray2;
                        continue;
                    case LogSource.SOCIAL /*32*/:
                        this.wantUris = input.readBool();
                        continue;
                    case LogSource.PHOTOS /*42*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 42);
                        if (this.sectionWeight == null) {
                            i = 0;
                        } else {
                            i = this.sectionWeight.length;
                        }
                        SectionWeight[] newArray3 = new SectionWeight[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.sectionWeight, 0, newArray3, 0, i);
                        }
                        while (i < newArray3.length - 1) {
                            newArray3[i] = new SectionWeight();
                            input.readMessage(newArray3[i]);
                            input.readTag();
                            i++;
                        }
                        newArray3[i] = new SectionWeight();
                        input.readMessage(newArray3[i]);
                        this.sectionWeight = newArray3;
                        continue;
                    case LogSource.BACKDROP /*48*/:
                        this.scoringVerbosityLevel = input.readInt32();
                        continue;
                    case LogSource.DOCS /*56*/:
                        this.universalSearch = input.readBool();
                        continue;
                    case LogSource.KIDS_COMMUNICATOR /*64*/:
                        this.prefixMatch = input.readBool();
                        continue;
                    case LogSource.JUSTSPEAK /*72*/:
                        this.variantMatch = input.readBool();
                        continue;
                    case LogSource.CRONET_ANDROID_GSA /*80*/:
                        this.debugLevel = input.readInt32();
                        continue;
                    case LogSource.EMERGENCY_ASSIST /*88*/:
                        value = input.readInt32();
                        switch (value) {
                            case Action.UNKNOWN /*0*/:
                            case TimeSelection.Type.TEMPORARY /*1*/:
                            case TimeSelection.Type.INDEFINITELY /*2*/:
                            case TimeSelection.Type.CUSTOM /*3*/:
                                this.rankingStrategy = value;
                                break;
                            default:
                                continue;
                        }
                    case LogSource.GCM_COUNTERS /*96*/:
                        this.filterUsingSectionWeights = input.readBool();
                        continue;
                    case LogSource.MOBILESDK_CLIENT /*104*/:
                        this.wantClientScores = input.readBool();
                        continue;
                    case LogSource.TRANSOM /*114*/:
                        if (this.scoringFlags == null) {
                            this.scoringFlags = new Scoring();
                        }
                        input.readMessage(this.scoringFlags);
                        continue;
                    case LogSource.FLYDROID /*120*/:
                        value = input.readInt32();
                        switch (value) {
                            case Action.UNKNOWN /*0*/:
                            case TimeSelection.Type.TEMPORARY /*1*/:
                                this.queryTokenizer = value;
                                break;
                            default:
                                continue;
                        }
                    case LogSource.KEEP /*128*/:
                        this.returnPerCorpusResults = input.readBool();
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

        public static QueryRequestSpec parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (QueryRequestSpec) MessageNano.mergeFrom(new QueryRequestSpec(), data);
        }

        public static QueryRequestSpec parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new QueryRequestSpec().mergeFrom(input);
        }
    }

    public static final class QueryResponse extends ExtendableMessageNano<QueryResponse> {
        private static volatile QueryResponse[] _emptyArray;
        public double[] clientScores;
        public Corpus[] corpora;
        public int[] corpusIds;
        public ResponseDebugInfo debugInfo;
        public int numResults;
        public int numScored;
        public String query;
        public byte[] uriBuffer;
        public int[] uriLengths;

        public static final class Corpus extends ExtendableMessageNano<Corpus> {
            private static volatile Corpus[] _emptyArray;
            public Section[] sections;
            public Tag[] tags;

            public static final class Section extends ExtendableMessageNano<Section> {
                private static volatile Section[] _emptyArray;
                public byte[] contentBuffer;
                public int[] contentLengths;

                public static Section[] emptyArray() {
                    if (_emptyArray == null) {
                        synchronized (InternalNano.LAZY_INIT_LOCK) {
                            if (_emptyArray == null) {
                                _emptyArray = new Section[0];
                            }
                        }
                    }
                    return _emptyArray;
                }

                public Section() {
                    clear();
                }

                public Section clear() {
                    this.contentLengths = WireFormatNano.EMPTY_INT_ARRAY;
                    this.contentBuffer = WireFormatNano.EMPTY_BYTES;
                    this.unknownFieldData = null;
                    this.cachedSize = -1;
                    return this;
                }

                public boolean equals(Object o) {
                    if (o == this) {
                        return true;
                    }
                    if (!(o instanceof Section)) {
                        return false;
                    }
                    Section other = (Section) o;
                    if (!InternalNano.equals(this.contentLengths, other.contentLengths) || !Arrays.equals(this.contentBuffer, other.contentBuffer)) {
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
                    int hashCode = (((((getClass().getName().hashCode() + 527) * 31) + InternalNano.hashCode(this.contentLengths)) * 31) + Arrays.hashCode(this.contentBuffer)) * 31;
                    int hashCode2 = (this.unknownFieldData == null || this.unknownFieldData.isEmpty()) ? 0 : this.unknownFieldData.hashCode();
                    return hashCode + hashCode2;
                }

                public void writeTo(CodedOutputByteBufferNano output) throws IOException {
                    if (this.contentLengths != null && this.contentLengths.length > 0) {
                        int dataSize = 0;
                        for (int element : this.contentLengths) {
                            dataSize += CodedOutputByteBufferNano.computeInt32SizeNoTag(element);
                        }
                        output.writeRawVarint32(10);
                        output.writeRawVarint32(dataSize);
                        for (int writeInt32NoTag : this.contentLengths) {
                            output.writeInt32NoTag(writeInt32NoTag);
                        }
                    }
                    if (!Arrays.equals(this.contentBuffer, WireFormatNano.EMPTY_BYTES)) {
                        output.writeBytes(2, this.contentBuffer);
                    }
                    super.writeTo(output);
                }

                protected int computeSerializedSize() {
                    int size = super.computeSerializedSize();
                    if (this.contentLengths != null && this.contentLengths.length > 0) {
                        int dataSize = 0;
                        for (int element : this.contentLengths) {
                            dataSize += CodedOutputByteBufferNano.computeInt32SizeNoTag(element);
                        }
                        size = ((size + dataSize) + 1) + CodedOutputByteBufferNano.computeRawVarint32Size(dataSize);
                    }
                    if (Arrays.equals(this.contentBuffer, WireFormatNano.EMPTY_BYTES)) {
                        return size;
                    }
                    return size + CodedOutputByteBufferNano.computeBytesSize(2, this.contentBuffer);
                }

                public Section mergeFrom(CodedInputByteBufferNano input) throws IOException {
                    while (true) {
                        int tag = input.readTag();
                        int arrayLength;
                        int i;
                        int[] newArray;
                        switch (tag) {
                            case Action.UNKNOWN /*0*/:
                                break;
                            case Type.TAP_GET_LINK /*8*/:
                                arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 8);
                                i = this.contentLengths == null ? 0 : this.contentLengths.length;
                                newArray = new int[(i + arrayLength)];
                                if (i != 0) {
                                    System.arraycopy(this.contentLengths, 0, newArray, 0, i);
                                }
                                while (i < newArray.length - 1) {
                                    newArray[i] = input.readInt32();
                                    input.readTag();
                                    i++;
                                }
                                newArray[i] = input.readInt32();
                                this.contentLengths = newArray;
                                continue;
                            case Type.TAP_ABOUT /*10*/:
                                int limit = input.pushLimit(input.readRawVarint32());
                                arrayLength = 0;
                                int startPos = input.getPosition();
                                while (input.getBytesUntilLimit() > 0) {
                                    input.readInt32();
                                    arrayLength++;
                                }
                                input.rewindToPosition(startPos);
                                i = this.contentLengths == null ? 0 : this.contentLengths.length;
                                newArray = new int[(i + arrayLength)];
                                if (i != 0) {
                                    System.arraycopy(this.contentLengths, 0, newArray, 0, i);
                                }
                                while (i < newArray.length) {
                                    newArray[i] = input.readInt32();
                                    i++;
                                }
                                this.contentLengths = newArray;
                                input.popLimit(limit);
                                continue;
                            case LogSource.GOOGLE_ANALYTICS /*18*/:
                                this.contentBuffer = input.readBytes();
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

                public static Section parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
                    return (Section) MessageNano.mergeFrom(new Section(), data);
                }

                public static Section parseFrom(CodedInputByteBufferNano input) throws IOException {
                    return new Section().mergeFrom(input);
                }
            }

            public static final class Tag extends ExtendableMessageNano<Tag> {
                private static volatile Tag[] _emptyArray;
                public byte[] docHasTag;

                public static Tag[] emptyArray() {
                    if (_emptyArray == null) {
                        synchronized (InternalNano.LAZY_INIT_LOCK) {
                            if (_emptyArray == null) {
                                _emptyArray = new Tag[0];
                            }
                        }
                    }
                    return _emptyArray;
                }

                public Tag() {
                    clear();
                }

                public Tag clear() {
                    this.docHasTag = WireFormatNano.EMPTY_BYTES;
                    this.unknownFieldData = null;
                    this.cachedSize = -1;
                    return this;
                }

                public boolean equals(Object o) {
                    if (o == this) {
                        return true;
                    }
                    if (!(o instanceof Tag)) {
                        return false;
                    }
                    Tag other = (Tag) o;
                    if (!Arrays.equals(this.docHasTag, other.docHasTag)) {
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
                    int hashCode = (((getClass().getName().hashCode() + 527) * 31) + Arrays.hashCode(this.docHasTag)) * 31;
                    int hashCode2 = (this.unknownFieldData == null || this.unknownFieldData.isEmpty()) ? 0 : this.unknownFieldData.hashCode();
                    return hashCode + hashCode2;
                }

                public void writeTo(CodedOutputByteBufferNano output) throws IOException {
                    if (!Arrays.equals(this.docHasTag, WireFormatNano.EMPTY_BYTES)) {
                        output.writeBytes(1, this.docHasTag);
                    }
                    super.writeTo(output);
                }

                protected int computeSerializedSize() {
                    int size = super.computeSerializedSize();
                    if (Arrays.equals(this.docHasTag, WireFormatNano.EMPTY_BYTES)) {
                        return size;
                    }
                    return size + CodedOutputByteBufferNano.computeBytesSize(1, this.docHasTag);
                }

                public Tag mergeFrom(CodedInputByteBufferNano input) throws IOException {
                    while (true) {
                        int tag = input.readTag();
                        switch (tag) {
                            case Action.UNKNOWN /*0*/:
                                break;
                            case Type.TAP_ABOUT /*10*/:
                                this.docHasTag = input.readBytes();
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

                public static Tag parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
                    return (Tag) MessageNano.mergeFrom(new Tag(), data);
                }

                public static Tag parseFrom(CodedInputByteBufferNano input) throws IOException {
                    return new Tag().mergeFrom(input);
                }
            }

            public static Corpus[] emptyArray() {
                if (_emptyArray == null) {
                    synchronized (InternalNano.LAZY_INIT_LOCK) {
                        if (_emptyArray == null) {
                            _emptyArray = new Corpus[0];
                        }
                    }
                }
                return _emptyArray;
            }

            public Corpus() {
                clear();
            }

            public Corpus clear() {
                this.sections = Section.emptyArray();
                this.tags = Tag.emptyArray();
                this.unknownFieldData = null;
                this.cachedSize = -1;
                return this;
            }

            public boolean equals(Object o) {
                if (o == this) {
                    return true;
                }
                if (!(o instanceof Corpus)) {
                    return false;
                }
                Corpus other = (Corpus) o;
                if (!InternalNano.equals(this.sections, other.sections) || !InternalNano.equals(this.tags, other.tags)) {
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
                int hashCode = (((((getClass().getName().hashCode() + 527) * 31) + InternalNano.hashCode(this.sections)) * 31) + InternalNano.hashCode(this.tags)) * 31;
                int hashCode2 = (this.unknownFieldData == null || this.unknownFieldData.isEmpty()) ? 0 : this.unknownFieldData.hashCode();
                return hashCode + hashCode2;
            }

            public void writeTo(CodedOutputByteBufferNano output) throws IOException {
                if (this.sections != null && this.sections.length > 0) {
                    for (Section element : this.sections) {
                        if (element != null) {
                            output.writeMessage(1, element);
                        }
                    }
                }
                if (this.tags != null && this.tags.length > 0) {
                    for (Tag element2 : this.tags) {
                        if (element2 != null) {
                            output.writeMessage(2, element2);
                        }
                    }
                }
                super.writeTo(output);
            }

            protected int computeSerializedSize() {
                int size = super.computeSerializedSize();
                if (this.sections != null && this.sections.length > 0) {
                    for (Section element : this.sections) {
                        if (element != null) {
                            size += CodedOutputByteBufferNano.computeMessageSize(1, element);
                        }
                    }
                }
                if (this.tags != null && this.tags.length > 0) {
                    for (Tag element2 : this.tags) {
                        if (element2 != null) {
                            size += CodedOutputByteBufferNano.computeMessageSize(2, element2);
                        }
                    }
                }
                return size;
            }

            public Corpus mergeFrom(CodedInputByteBufferNano input) throws IOException {
                while (true) {
                    int tag = input.readTag();
                    int arrayLength;
                    int i;
                    switch (tag) {
                        case Action.UNKNOWN /*0*/:
                            break;
                        case Type.TAP_ABOUT /*10*/:
                            arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 10);
                            if (this.sections == null) {
                                i = 0;
                            } else {
                                i = this.sections.length;
                            }
                            Section[] newArray = new Section[(i + arrayLength)];
                            if (i != 0) {
                                System.arraycopy(this.sections, 0, newArray, 0, i);
                            }
                            while (i < newArray.length - 1) {
                                newArray[i] = new Section();
                                input.readMessage(newArray[i]);
                                input.readTag();
                                i++;
                            }
                            newArray[i] = new Section();
                            input.readMessage(newArray[i]);
                            this.sections = newArray;
                            continue;
                        case LogSource.GOOGLE_ANALYTICS /*18*/:
                            arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 18);
                            if (this.tags == null) {
                                i = 0;
                            } else {
                                i = this.tags.length;
                            }
                            Tag[] newArray2 = new Tag[(i + arrayLength)];
                            if (i != 0) {
                                System.arraycopy(this.tags, 0, newArray2, 0, i);
                            }
                            while (i < newArray2.length - 1) {
                                newArray2[i] = new Tag();
                                input.readMessage(newArray2[i]);
                                input.readTag();
                                i++;
                            }
                            newArray2[i] = new Tag();
                            input.readMessage(newArray2[i]);
                            this.tags = newArray2;
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

            public static Corpus parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
                return (Corpus) MessageNano.mergeFrom(new Corpus(), data);
            }

            public static Corpus parseFrom(CodedInputByteBufferNano input) throws IOException {
                return new Corpus().mergeFrom(input);
            }
        }

        public static QueryResponse[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new QueryResponse[0];
                    }
                }
            }
            return _emptyArray;
        }

        public QueryResponse() {
            clear();
        }

        public QueryResponse clear() {
            this.query = BuildConfig.VERSION_NAME;
            this.numResults = 0;
            this.corpusIds = WireFormatNano.EMPTY_INT_ARRAY;
            this.corpora = Corpus.emptyArray();
            this.numScored = 0;
            this.uriLengths = WireFormatNano.EMPTY_INT_ARRAY;
            this.uriBuffer = WireFormatNano.EMPTY_BYTES;
            this.debugInfo = null;
            this.clientScores = WireFormatNano.EMPTY_DOUBLE_ARRAY;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof QueryResponse)) {
                return false;
            }
            QueryResponse other = (QueryResponse) o;
            if (this.query == null) {
                if (other.query != null) {
                    return false;
                }
            } else if (!this.query.equals(other.query)) {
                return false;
            }
            if (this.numResults != other.numResults || !InternalNano.equals(this.corpusIds, other.corpusIds) || !InternalNano.equals(this.corpora, other.corpora) || this.numScored != other.numScored || !InternalNano.equals(this.uriLengths, other.uriLengths) || !Arrays.equals(this.uriBuffer, other.uriBuffer)) {
                return false;
            }
            if (this.debugInfo == null) {
                if (other.debugInfo != null) {
                    return false;
                }
            } else if (!this.debugInfo.equals(other.debugInfo)) {
                return false;
            }
            if (!InternalNano.equals(this.clientScores, other.clientScores)) {
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
            if (this.query == null) {
                i = 0;
            } else {
                i = this.query.hashCode();
            }
            i = (((((((((((((((((hashCode + i) * 31) + this.numResults) * 31) + InternalNano.hashCode(this.corpusIds)) * 31) + InternalNano.hashCode(this.corpora)) * 31) + this.numScored) * 31) + InternalNano.hashCode(this.uriLengths)) * 31) + Arrays.hashCode(this.uriBuffer)) * 31) + (this.debugInfo == null ? 0 : this.debugInfo.hashCode())) * 31) + InternalNano.hashCode(this.clientScores)) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i2 = this.unknownFieldData.hashCode();
            }
            return i + i2;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            int dataSize;
            if (!this.query.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(1, this.query);
            }
            if (this.numResults != 0) {
                output.writeUInt32(2, this.numResults);
            }
            if (this.corpusIds != null && this.corpusIds.length > 0) {
                dataSize = 0;
                for (int element : this.corpusIds) {
                    dataSize += CodedOutputByteBufferNano.computeInt32SizeNoTag(element);
                }
                output.writeRawVarint32(26);
                output.writeRawVarint32(dataSize);
                for (int writeInt32NoTag : this.corpusIds) {
                    output.writeInt32NoTag(writeInt32NoTag);
                }
            }
            if (this.corpora != null && this.corpora.length > 0) {
                for (Corpus element2 : this.corpora) {
                    if (element2 != null) {
                        output.writeMessage(4, element2);
                    }
                }
            }
            if (this.numScored != 0) {
                output.writeUInt32(5, this.numScored);
            }
            if (this.uriLengths != null && this.uriLengths.length > 0) {
                dataSize = 0;
                for (int element3 : this.uriLengths) {
                    dataSize += CodedOutputByteBufferNano.computeInt32SizeNoTag(element3);
                }
                output.writeRawVarint32(50);
                output.writeRawVarint32(dataSize);
                for (int writeInt32NoTag2 : this.uriLengths) {
                    output.writeInt32NoTag(writeInt32NoTag2);
                }
            }
            if (!Arrays.equals(this.uriBuffer, WireFormatNano.EMPTY_BYTES)) {
                output.writeBytes(7, this.uriBuffer);
            }
            if (this.debugInfo != null) {
                output.writeMessage(9, this.debugInfo);
            }
            if (this.clientScores != null && this.clientScores.length > 0) {
                dataSize = this.clientScores.length * 8;
                output.writeRawVarint32(82);
                output.writeRawVarint32(dataSize);
                for (double writeDoubleNoTag : this.clientScores) {
                    output.writeDoubleNoTag(writeDoubleNoTag);
                }
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int dataSize;
            int size = super.computeSerializedSize();
            if (!this.query.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.query);
            }
            if (this.numResults != 0) {
                size += CodedOutputByteBufferNano.computeUInt32Size(2, this.numResults);
            }
            if (this.corpusIds != null && this.corpusIds.length > 0) {
                dataSize = 0;
                for (int element : this.corpusIds) {
                    dataSize += CodedOutputByteBufferNano.computeInt32SizeNoTag(element);
                }
                size = ((size + dataSize) + 1) + CodedOutputByteBufferNano.computeRawVarint32Size(dataSize);
            }
            if (this.corpora != null && this.corpora.length > 0) {
                for (Corpus element2 : this.corpora) {
                    if (element2 != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(4, element2);
                    }
                }
            }
            if (this.numScored != 0) {
                size += CodedOutputByteBufferNano.computeUInt32Size(5, this.numScored);
            }
            if (this.uriLengths != null && this.uriLengths.length > 0) {
                dataSize = 0;
                for (int element3 : this.uriLengths) {
                    dataSize += CodedOutputByteBufferNano.computeInt32SizeNoTag(element3);
                }
                size = ((size + dataSize) + 1) + CodedOutputByteBufferNano.computeRawVarint32Size(dataSize);
            }
            if (!Arrays.equals(this.uriBuffer, WireFormatNano.EMPTY_BYTES)) {
                size += CodedOutputByteBufferNano.computeBytesSize(7, this.uriBuffer);
            }
            if (this.debugInfo != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(9, this.debugInfo);
            }
            if (this.clientScores == null || this.clientScores.length <= 0) {
                return size;
            }
            dataSize = this.clientScores.length * 8;
            return ((size + dataSize) + 1) + CodedOutputByteBufferNano.computeRawVarint32Size(dataSize);
        }

        public QueryResponse mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                int arrayLength;
                int i;
                int[] newArray;
                int limit;
                int startPos;
                double[] newArray2;
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        this.query = input.readString();
                        continue;
                    case LogSource.GMS_CORE_PEOPLE /*16*/:
                        this.numResults = input.readUInt32();
                        continue;
                    case LogSource.LB_C /*24*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 24);
                        i = this.corpusIds == null ? 0 : this.corpusIds.length;
                        newArray = new int[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.corpusIds, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = input.readInt32();
                            input.readTag();
                            i++;
                        }
                        newArray[i] = input.readInt32();
                        this.corpusIds = newArray;
                        continue;
                    case LogSource.ANDROID_CAMERA /*26*/:
                        limit = input.pushLimit(input.readRawVarint32());
                        arrayLength = 0;
                        startPos = input.getPosition();
                        while (input.getBytesUntilLimit() > 0) {
                            input.readInt32();
                            arrayLength++;
                        }
                        input.rewindToPosition(startPos);
                        i = this.corpusIds == null ? 0 : this.corpusIds.length;
                        newArray = new int[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.corpusIds, 0, newArray, 0, i);
                        }
                        while (i < newArray.length) {
                            newArray[i] = input.readInt32();
                            i++;
                        }
                        this.corpusIds = newArray;
                        input.popLimit(limit);
                        continue;
                    case LogSource.NOVA /*34*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 34);
                        if (this.corpora == null) {
                            i = 0;
                        } else {
                            i = this.corpora.length;
                        }
                        Corpus[] newArray3 = new Corpus[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.corpora, 0, newArray3, 0, i);
                        }
                        while (i < newArray3.length - 1) {
                            newArray3[i] = new Corpus();
                            input.readMessage(newArray3[i]);
                            input.readTag();
                            i++;
                        }
                        newArray3[i] = new Corpus();
                        input.readMessage(newArray3[i]);
                        this.corpora = newArray3;
                        continue;
                    case LogSource.COPRESENCE /*40*/:
                        this.numScored = input.readUInt32();
                        continue;
                    case LogSource.BACKDROP /*48*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 48);
                        i = this.uriLengths == null ? 0 : this.uriLengths.length;
                        newArray = new int[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.uriLengths, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = input.readInt32();
                            input.readTag();
                            i++;
                        }
                        newArray[i] = input.readInt32();
                        this.uriLengths = newArray;
                        continue;
                    case LogSource.HANGOUT_LOG_REQUEST /*50*/:
                        limit = input.pushLimit(input.readRawVarint32());
                        arrayLength = 0;
                        startPos = input.getPosition();
                        while (input.getBytesUntilLimit() > 0) {
                            input.readInt32();
                            arrayLength++;
                        }
                        input.rewindToPosition(startPos);
                        i = this.uriLengths == null ? 0 : this.uriLengths.length;
                        newArray = new int[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.uriLengths, 0, newArray, 0, i);
                        }
                        while (i < newArray.length) {
                            newArray[i] = input.readInt32();
                            i++;
                        }
                        this.uriLengths = newArray;
                        input.popLimit(limit);
                        continue;
                    case LogSource.SLIDES /*58*/:
                        this.uriBuffer = input.readBytes();
                        continue;
                    case LogSource.MOVIES /*74*/:
                        if (this.debugInfo == null) {
                            this.debugInfo = new ResponseDebugInfo();
                        }
                        input.readMessage(this.debugInfo);
                        continue;
                    case LogSource.GOOGLE_FIT_WEARABLE /*81*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 81);
                        i = this.clientScores == null ? 0 : this.clientScores.length;
                        newArray2 = new double[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.clientScores, 0, newArray2, 0, i);
                        }
                        while (i < newArray2.length - 1) {
                            newArray2[i] = input.readDouble();
                            input.readTag();
                            i++;
                        }
                        newArray2[i] = input.readDouble();
                        this.clientScores = newArray2;
                        continue;
                    case LogSource.GOOGLE_EXPRESS /*82*/:
                        int length = input.readRawVarint32();
                        limit = input.pushLimit(length);
                        arrayLength = length / 8;
                        i = this.clientScores == null ? 0 : this.clientScores.length;
                        newArray2 = new double[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.clientScores, 0, newArray2, 0, i);
                        }
                        while (i < newArray2.length) {
                            newArray2[i] = input.readDouble();
                            i++;
                        }
                        this.clientScores = newArray2;
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

        public static QueryResponse parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (QueryResponse) MessageNano.mergeFrom(new QueryResponse(), data);
        }

        public static QueryResponse parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new QueryResponse().mergeFrom(input);
        }
    }

    public static final class ResponseDebugInfo extends ExtendableMessageNano<ResponseDebugInfo> {
        private static volatile ResponseDebugInfo[] _emptyArray;
        public String parsedQuery;
        public ResultDebugInfo[] results;

        public static ResponseDebugInfo[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new ResponseDebugInfo[0];
                    }
                }
            }
            return _emptyArray;
        }

        public ResponseDebugInfo() {
            clear();
        }

        public ResponseDebugInfo clear() {
            this.parsedQuery = BuildConfig.VERSION_NAME;
            this.results = ResultDebugInfo.emptyArray();
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof ResponseDebugInfo)) {
                return false;
            }
            ResponseDebugInfo other = (ResponseDebugInfo) o;
            if (this.parsedQuery == null) {
                if (other.parsedQuery != null) {
                    return false;
                }
            } else if (!this.parsedQuery.equals(other.parsedQuery)) {
                return false;
            }
            if (!InternalNano.equals(this.results, other.results)) {
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
            int hashCode = (((((getClass().getName().hashCode() + 527) * 31) + (this.parsedQuery == null ? 0 : this.parsedQuery.hashCode())) * 31) + InternalNano.hashCode(this.results)) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (!this.parsedQuery.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(1, this.parsedQuery);
            }
            if (this.results != null && this.results.length > 0) {
                for (ResultDebugInfo element : this.results) {
                    if (element != null) {
                        output.writeMessage(3, element);
                    }
                }
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (!this.parsedQuery.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.parsedQuery);
            }
            if (this.results != null && this.results.length > 0) {
                for (ResultDebugInfo element : this.results) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(3, element);
                    }
                }
            }
            return size;
        }

        public ResponseDebugInfo mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        this.parsedQuery = input.readString();
                        continue;
                    case LogSource.ANDROID_CAMERA /*26*/:
                        int i;
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 26);
                        if (this.results == null) {
                            i = 0;
                        } else {
                            i = this.results.length;
                        }
                        ResultDebugInfo[] newArray = new ResultDebugInfo[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.results, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new ResultDebugInfo();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i++;
                        }
                        newArray[i] = new ResultDebugInfo();
                        input.readMessage(newArray[i]);
                        this.results = newArray;
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

        public static ResponseDebugInfo parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (ResponseDebugInfo) MessageNano.mergeFrom(new ResponseDebugInfo(), data);
        }

        public static ResponseDebugInfo parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new ResponseDebugInfo().mergeFrom(input);
        }
    }

    public static final class ResultDebugInfo extends ExtendableMessageNano<ResultDebugInfo> {
        private static volatile ResultDebugInfo[] _emptyArray;
        public int bestSectionId;
        public String bestSectionName;
        public double bestSectionWeight;
        public double clientScore;
        public int corpusId;
        public int createdTimestampSecs;
        public int docScore;
        public String docUri;
        public int docid;
        public double hitWeight;
        public int lastUsedTimestampSecs;
        public int numUsageReports;
        public int numUsageReportsSearched;
        public int relevanceScore;
        public int timeScoredSecs;

        public static ResultDebugInfo[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new ResultDebugInfo[0];
                    }
                }
            }
            return _emptyArray;
        }

        public ResultDebugInfo() {
            clear();
        }

        public ResultDebugInfo clear() {
            this.docid = 0;
            this.docUri = BuildConfig.VERSION_NAME;
            this.clientScore = 0.0d;
            this.bestSectionId = 0;
            this.bestSectionName = BuildConfig.VERSION_NAME;
            this.docScore = 0;
            this.corpusId = 0;
            this.relevanceScore = 0;
            this.timeScoredSecs = 0;
            this.numUsageReports = 0;
            this.numUsageReportsSearched = 0;
            this.lastUsedTimestampSecs = 0;
            this.createdTimestampSecs = 0;
            this.hitWeight = 0.0d;
            this.bestSectionWeight = 0.0d;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof ResultDebugInfo)) {
                return false;
            }
            ResultDebugInfo other = (ResultDebugInfo) o;
            if (this.docid != other.docid) {
                return false;
            }
            if (this.docUri == null) {
                if (other.docUri != null) {
                    return false;
                }
            } else if (!this.docUri.equals(other.docUri)) {
                return false;
            }
            if (Double.doubleToLongBits(this.clientScore) != Double.doubleToLongBits(other.clientScore) || this.bestSectionId != other.bestSectionId) {
                return false;
            }
            if (this.bestSectionName == null) {
                if (other.bestSectionName != null) {
                    return false;
                }
            } else if (!this.bestSectionName.equals(other.bestSectionName)) {
                return false;
            }
            if (this.docScore != other.docScore || this.corpusId != other.corpusId || this.relevanceScore != other.relevanceScore || this.timeScoredSecs != other.timeScoredSecs || this.numUsageReports != other.numUsageReports || this.numUsageReportsSearched != other.numUsageReportsSearched || this.lastUsedTimestampSecs != other.lastUsedTimestampSecs || this.createdTimestampSecs != other.createdTimestampSecs || Double.doubleToLongBits(this.hitWeight) != Double.doubleToLongBits(other.hitWeight) || Double.doubleToLongBits(this.bestSectionWeight) != Double.doubleToLongBits(other.bestSectionWeight)) {
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
            int result = ((((getClass().getName().hashCode() + 527) * 31) + this.docid) * 31) + (this.docUri == null ? 0 : this.docUri.hashCode());
            long v = Double.doubleToLongBits(this.clientScore);
            int i3 = ((((result * 31) + ((int) ((v >>> 32) ^ v))) * 31) + this.bestSectionId) * 31;
            if (this.bestSectionName == null) {
                i = 0;
            } else {
                i = this.bestSectionName.hashCode();
            }
            result = ((((((((((((((((i3 + i) * 31) + this.docScore) * 31) + this.corpusId) * 31) + this.relevanceScore) * 31) + this.timeScoredSecs) * 31) + this.numUsageReports) * 31) + this.numUsageReportsSearched) * 31) + this.lastUsedTimestampSecs) * 31) + this.createdTimestampSecs;
            v = Double.doubleToLongBits(this.hitWeight);
            result = (result * 31) + ((int) ((v >>> 32) ^ v));
            v = Double.doubleToLongBits(this.bestSectionWeight);
            i = ((result * 31) + ((int) ((v >>> 32) ^ v))) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i2 = this.unknownFieldData.hashCode();
            }
            return i + i2;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.docid != 0) {
                output.writeUInt32(1, this.docid);
            }
            if (!this.docUri.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(2, this.docUri);
            }
            if (Double.doubleToLongBits(this.clientScore) != Double.doubleToLongBits(0.0d)) {
                output.writeDouble(3, this.clientScore);
            }
            if (this.bestSectionId != 0) {
                output.writeUInt32(4, this.bestSectionId);
            }
            if (!this.bestSectionName.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(5, this.bestSectionName);
            }
            if (this.docScore != 0) {
                output.writeUInt32(6, this.docScore);
            }
            if (this.corpusId != 0) {
                output.writeUInt32(7, this.corpusId);
            }
            if (this.relevanceScore != 0) {
                output.writeUInt32(8, this.relevanceScore);
            }
            if (this.timeScoredSecs != 0) {
                output.writeUInt32(9, this.timeScoredSecs);
            }
            if (this.numUsageReports != 0) {
                output.writeUInt32(10, this.numUsageReports);
            }
            if (this.numUsageReportsSearched != 0) {
                output.writeUInt32(11, this.numUsageReportsSearched);
            }
            if (this.lastUsedTimestampSecs != 0) {
                output.writeUInt32(12, this.lastUsedTimestampSecs);
            }
            if (this.createdTimestampSecs != 0) {
                output.writeUInt32(13, this.createdTimestampSecs);
            }
            if (Double.doubleToLongBits(this.hitWeight) != Double.doubleToLongBits(0.0d)) {
                output.writeDouble(14, this.hitWeight);
            }
            if (Double.doubleToLongBits(this.bestSectionWeight) != Double.doubleToLongBits(0.0d)) {
                output.writeDouble(15, this.bestSectionWeight);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.docid != 0) {
                size += CodedOutputByteBufferNano.computeUInt32Size(1, this.docid);
            }
            if (!this.docUri.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(2, this.docUri);
            }
            if (Double.doubleToLongBits(this.clientScore) != Double.doubleToLongBits(0.0d)) {
                size += CodedOutputByteBufferNano.computeDoubleSize(3, this.clientScore);
            }
            if (this.bestSectionId != 0) {
                size += CodedOutputByteBufferNano.computeUInt32Size(4, this.bestSectionId);
            }
            if (!this.bestSectionName.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(5, this.bestSectionName);
            }
            if (this.docScore != 0) {
                size += CodedOutputByteBufferNano.computeUInt32Size(6, this.docScore);
            }
            if (this.corpusId != 0) {
                size += CodedOutputByteBufferNano.computeUInt32Size(7, this.corpusId);
            }
            if (this.relevanceScore != 0) {
                size += CodedOutputByteBufferNano.computeUInt32Size(8, this.relevanceScore);
            }
            if (this.timeScoredSecs != 0) {
                size += CodedOutputByteBufferNano.computeUInt32Size(9, this.timeScoredSecs);
            }
            if (this.numUsageReports != 0) {
                size += CodedOutputByteBufferNano.computeUInt32Size(10, this.numUsageReports);
            }
            if (this.numUsageReportsSearched != 0) {
                size += CodedOutputByteBufferNano.computeUInt32Size(11, this.numUsageReportsSearched);
            }
            if (this.lastUsedTimestampSecs != 0) {
                size += CodedOutputByteBufferNano.computeUInt32Size(12, this.lastUsedTimestampSecs);
            }
            if (this.createdTimestampSecs != 0) {
                size += CodedOutputByteBufferNano.computeUInt32Size(13, this.createdTimestampSecs);
            }
            if (Double.doubleToLongBits(this.hitWeight) != Double.doubleToLongBits(0.0d)) {
                size += CodedOutputByteBufferNano.computeDoubleSize(14, this.hitWeight);
            }
            if (Double.doubleToLongBits(this.bestSectionWeight) != Double.doubleToLongBits(0.0d)) {
                return size + CodedOutputByteBufferNano.computeDoubleSize(15, this.bestSectionWeight);
            }
            return size;
        }

        public ResultDebugInfo mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_GET_LINK /*8*/:
                        this.docid = input.readUInt32();
                        continue;
                    case LogSource.GOOGLE_ANALYTICS /*18*/:
                        this.docUri = input.readString();
                        continue;
                    case LogSource.ANDROID_AUTH /*25*/:
                        this.clientScore = input.readDouble();
                        continue;
                    case LogSource.SOCIAL /*32*/:
                        this.bestSectionId = input.readUInt32();
                        continue;
                    case LogSource.PHOTOS /*42*/:
                        this.bestSectionName = input.readString();
                        continue;
                    case LogSource.BACKDROP /*48*/:
                        this.docScore = input.readUInt32();
                        continue;
                    case LogSource.DOCS /*56*/:
                        this.corpusId = input.readUInt32();
                        continue;
                    case LogSource.KIDS_COMMUNICATOR /*64*/:
                        this.relevanceScore = input.readUInt32();
                        continue;
                    case LogSource.JUSTSPEAK /*72*/:
                        this.timeScoredSecs = input.readUInt32();
                        continue;
                    case LogSource.CRONET_ANDROID_GSA /*80*/:
                        this.numUsageReports = input.readUInt32();
                        continue;
                    case LogSource.EMERGENCY_ASSIST /*88*/:
                        this.numUsageReportsSearched = input.readUInt32();
                        continue;
                    case LogSource.GCM_COUNTERS /*96*/:
                        this.lastUsedTimestampSecs = input.readUInt32();
                        continue;
                    case LogSource.MOBILESDK_CLIENT /*104*/:
                        this.createdTimestampSecs = input.readUInt32();
                        continue;
                    case LogSource.FAMILY_COMPASS /*113*/:
                        this.hitWeight = input.readDouble();
                        continue;
                    case LogSource.CPANEL_APP /*121*/:
                        this.bestSectionWeight = input.readDouble();
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

        public static ResultDebugInfo parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (ResultDebugInfo) MessageNano.mergeFrom(new ResultDebugInfo(), data);
        }

        public static ResultDebugInfo parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new ResultDebugInfo().mergeFrom(input);
        }
    }

    public static final class SettingsGmsCoreInfo extends ExtendableMessageNano<SettingsGmsCoreInfo> {
        private static volatile SettingsGmsCoreInfo[] _emptyArray;
        public GlobalSearchApplicationInfoProto[] gsai;
        public int[] gsaiUserEnabled;

        public static SettingsGmsCoreInfo[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new SettingsGmsCoreInfo[0];
                    }
                }
            }
            return _emptyArray;
        }

        public SettingsGmsCoreInfo() {
            clear();
        }

        public SettingsGmsCoreInfo clear() {
            this.gsai = GlobalSearchApplicationInfoProto.emptyArray();
            this.gsaiUserEnabled = WireFormatNano.EMPTY_INT_ARRAY;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof SettingsGmsCoreInfo)) {
                return false;
            }
            SettingsGmsCoreInfo other = (SettingsGmsCoreInfo) o;
            if (!InternalNano.equals(this.gsai, other.gsai) || !InternalNano.equals(this.gsaiUserEnabled, other.gsaiUserEnabled)) {
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
            int hashCode = (((((getClass().getName().hashCode() + 527) * 31) + InternalNano.hashCode(this.gsai)) * 31) + InternalNano.hashCode(this.gsaiUserEnabled)) * 31;
            int hashCode2 = (this.unknownFieldData == null || this.unknownFieldData.isEmpty()) ? 0 : this.unknownFieldData.hashCode();
            return hashCode + hashCode2;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.gsai != null && this.gsai.length > 0) {
                for (GlobalSearchApplicationInfoProto element : this.gsai) {
                    if (element != null) {
                        output.writeMessage(1, element);
                    }
                }
            }
            if (this.gsaiUserEnabled != null && this.gsaiUserEnabled.length > 0) {
                for (int writeInt32 : this.gsaiUserEnabled) {
                    output.writeInt32(2, writeInt32);
                }
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.gsai != null && this.gsai.length > 0) {
                for (GlobalSearchApplicationInfoProto element : this.gsai) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(1, element);
                    }
                }
            }
            if (this.gsaiUserEnabled == null || this.gsaiUserEnabled.length <= 0) {
                return size;
            }
            int dataSize = 0;
            for (int element2 : this.gsaiUserEnabled) {
                dataSize += CodedOutputByteBufferNano.computeInt32SizeNoTag(element2);
            }
            return (size + dataSize) + (this.gsaiUserEnabled.length * 1);
        }

        public SettingsGmsCoreInfo mergeFrom(CodedInputByteBufferNano input) throws IOException {
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
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 10);
                        if (this.gsai == null) {
                            i = 0;
                        } else {
                            i = this.gsai.length;
                        }
                        GlobalSearchApplicationInfoProto[] newArray2 = new GlobalSearchApplicationInfoProto[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.gsai, 0, newArray2, 0, i);
                        }
                        while (i < newArray2.length - 1) {
                            newArray2[i] = new GlobalSearchApplicationInfoProto();
                            input.readMessage(newArray2[i]);
                            input.readTag();
                            i++;
                        }
                        newArray2[i] = new GlobalSearchApplicationInfoProto();
                        input.readMessage(newArray2[i]);
                        this.gsai = newArray2;
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
                            i = this.gsaiUserEnabled == null ? 0 : this.gsaiUserEnabled.length;
                            if (i != 0 || validCount != validValues.length) {
                                newArray = new int[(i + validCount)];
                                if (i != 0) {
                                    System.arraycopy(this.gsaiUserEnabled, 0, newArray, 0, i);
                                }
                                System.arraycopy(validValues, 0, newArray, i, validCount);
                                this.gsaiUserEnabled = newArray;
                                break;
                            }
                            this.gsaiUserEnabled = validValues;
                            break;
                        }
                        continue;
                    case LogSource.GOOGLE_ANALYTICS /*18*/:
                        int limit = input.pushLimit(input.readRawVarint32());
                        arrayLength = 0;
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
                            if (this.gsaiUserEnabled == null) {
                                i = 0;
                            } else {
                                i = this.gsaiUserEnabled.length;
                            }
                            newArray = new int[(i + arrayLength)];
                            if (i != 0) {
                                System.arraycopy(this.gsaiUserEnabled, 0, newArray, 0, i);
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
                            this.gsaiUserEnabled = newArray;
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

        public static SettingsGmsCoreInfo parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (SettingsGmsCoreInfo) MessageNano.mergeFrom(new SettingsGmsCoreInfo(), data);
        }

        public static SettingsGmsCoreInfo parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new SettingsGmsCoreInfo().mergeFrom(input);
        }
    }

    public static final class SettingsPackageInfo extends ExtendableMessageNano<SettingsPackageInfo> {
        private static volatile SettingsPackageInfo[] _emptyArray;
        public boolean blocked;
        public String certHash;
        public GlobalSearchApplicationInfoProto gsai;
        public int gsaiSource;
        public long gsaiSourcedTime;
        public int gsaiUserEnabled;
        public boolean hasConnected;
        public boolean hasPerAccountTemplate;
        public String resourceFingerprint;
        public String signature;
        public int versionCode;

        public static SettingsPackageInfo[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new SettingsPackageInfo[0];
                    }
                }
            }
            return _emptyArray;
        }

        public SettingsPackageInfo() {
            clear();
        }

        public SettingsPackageInfo clear() {
            this.signature = BuildConfig.VERSION_NAME;
            this.blocked = false;
            this.gsai = null;
            this.gsaiSource = 0;
            this.resourceFingerprint = BuildConfig.VERSION_NAME;
            this.hasConnected = false;
            this.gsaiSourcedTime = 0;
            this.gsaiUserEnabled = 0;
            this.versionCode = 0;
            this.certHash = BuildConfig.VERSION_NAME;
            this.hasPerAccountTemplate = false;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof SettingsPackageInfo)) {
                return false;
            }
            SettingsPackageInfo other = (SettingsPackageInfo) o;
            if (this.signature == null) {
                if (other.signature != null) {
                    return false;
                }
            } else if (!this.signature.equals(other.signature)) {
                return false;
            }
            if (this.blocked != other.blocked) {
                return false;
            }
            if (this.gsai == null) {
                if (other.gsai != null) {
                    return false;
                }
            } else if (!this.gsai.equals(other.gsai)) {
                return false;
            }
            if (this.gsaiSource != other.gsaiSource) {
                return false;
            }
            if (this.resourceFingerprint == null) {
                if (other.resourceFingerprint != null) {
                    return false;
                }
            } else if (!this.resourceFingerprint.equals(other.resourceFingerprint)) {
                return false;
            }
            if (this.hasConnected != other.hasConnected || this.gsaiSourcedTime != other.gsaiSourcedTime || this.gsaiUserEnabled != other.gsaiUserEnabled || this.versionCode != other.versionCode) {
                return false;
            }
            if (this.certHash == null) {
                if (other.certHash != null) {
                    return false;
                }
            } else if (!this.certHash.equals(other.certHash)) {
                return false;
            }
            if (this.hasPerAccountTemplate != other.hasPerAccountTemplate) {
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
            int hashCode = (((getClass().getName().hashCode() + 527) * 31) + (this.signature == null ? 0 : this.signature.hashCode())) * 31;
            if (this.blocked) {
                i = 1231;
            } else {
                i = 1237;
            }
            hashCode = (((((((hashCode + i) * 31) + (this.gsai == null ? 0 : this.gsai.hashCode())) * 31) + this.gsaiSource) * 31) + (this.resourceFingerprint == null ? 0 : this.resourceFingerprint.hashCode())) * 31;
            if (this.hasConnected) {
                i = 1231;
            } else {
                i = 1237;
            }
            i = (((((((((hashCode + i) * 31) + ((int) (this.gsaiSourcedTime ^ (this.gsaiSourcedTime >>> 32)))) * 31) + this.gsaiUserEnabled) * 31) + this.versionCode) * 31) + (this.certHash == null ? 0 : this.certHash.hashCode())) * 31;
            if (!this.hasPerAccountTemplate) {
                i2 = 1237;
            }
            i = (i + i2) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i3 = this.unknownFieldData.hashCode();
            }
            return i + i3;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (!this.signature.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(1, this.signature);
            }
            if (this.blocked) {
                output.writeBool(2, this.blocked);
            }
            if (this.gsai != null) {
                output.writeMessage(3, this.gsai);
            }
            if (this.gsaiSource != 0) {
                output.writeInt32(4, this.gsaiSource);
            }
            if (!this.resourceFingerprint.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(5, this.resourceFingerprint);
            }
            if (this.hasConnected) {
                output.writeBool(6, this.hasConnected);
            }
            if (this.gsaiSourcedTime != 0) {
                output.writeInt64(7, this.gsaiSourcedTime);
            }
            if (this.gsaiUserEnabled != 0) {
                output.writeInt32(8, this.gsaiUserEnabled);
            }
            if (this.versionCode != 0) {
                output.writeInt32(9, this.versionCode);
            }
            if (!this.certHash.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(10, this.certHash);
            }
            if (this.hasPerAccountTemplate) {
                output.writeBool(11, this.hasPerAccountTemplate);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (!this.signature.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.signature);
            }
            if (this.blocked) {
                size += CodedOutputByteBufferNano.computeBoolSize(2, this.blocked);
            }
            if (this.gsai != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(3, this.gsai);
            }
            if (this.gsaiSource != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(4, this.gsaiSource);
            }
            if (!this.resourceFingerprint.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(5, this.resourceFingerprint);
            }
            if (this.hasConnected) {
                size += CodedOutputByteBufferNano.computeBoolSize(6, this.hasConnected);
            }
            if (this.gsaiSourcedTime != 0) {
                size += CodedOutputByteBufferNano.computeInt64Size(7, this.gsaiSourcedTime);
            }
            if (this.gsaiUserEnabled != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(8, this.gsaiUserEnabled);
            }
            if (this.versionCode != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(9, this.versionCode);
            }
            if (!this.certHash.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(10, this.certHash);
            }
            if (this.hasPerAccountTemplate) {
                return size + CodedOutputByteBufferNano.computeBoolSize(11, this.hasPerAccountTemplate);
            }
            return size;
        }

        public SettingsPackageInfo mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        this.signature = input.readString();
                        continue;
                    case LogSource.GMS_CORE_PEOPLE /*16*/:
                        this.blocked = input.readBool();
                        continue;
                    case LogSource.ANDROID_CAMERA /*26*/:
                        if (this.gsai == null) {
                            this.gsai = new GlobalSearchApplicationInfoProto();
                        }
                        input.readMessage(this.gsai);
                        continue;
                    case LogSource.SOCIAL /*32*/:
                        this.gsaiSource = input.readInt32();
                        continue;
                    case LogSource.PHOTOS /*42*/:
                        this.resourceFingerprint = input.readString();
                        continue;
                    case LogSource.BACKDROP /*48*/:
                        this.hasConnected = input.readBool();
                        continue;
                    case LogSource.DOCS /*56*/:
                        this.gsaiSourcedTime = input.readInt64();
                        continue;
                    case LogSource.KIDS_COMMUNICATOR /*64*/:
                        int value = input.readInt32();
                        switch (value) {
                            case Action.UNKNOWN /*0*/:
                            case TimeSelection.Type.TEMPORARY /*1*/:
                            case TimeSelection.Type.INDEFINITELY /*2*/:
                                this.gsaiUserEnabled = value;
                                break;
                            default:
                                continue;
                        }
                    case LogSource.JUSTSPEAK /*72*/:
                        this.versionCode = input.readInt32();
                        continue;
                    case LogSource.GOOGLE_EXPRESS /*82*/:
                        this.certHash = input.readString();
                        continue;
                    case LogSource.EMERGENCY_ASSIST /*88*/:
                        this.hasPerAccountTemplate = input.readBool();
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

        public static SettingsPackageInfo parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (SettingsPackageInfo) MessageNano.mergeFrom(new SettingsPackageInfo(), data);
        }

        public static SettingsPackageInfo parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new SettingsPackageInfo().mergeFrom(input);
        }
    }

    public static final class SuggestionResponse extends ExtendableMessageNano<SuggestionResponse> {
        private static volatile SuggestionResponse[] _emptyArray;
        public Suggestion[] suggestions;

        public static final class Suggestion extends ExtendableMessageNano<Suggestion> {
            private static volatile Suggestion[] _emptyArray;
            public String displayText;
            public String query;

            public static Suggestion[] emptyArray() {
                if (_emptyArray == null) {
                    synchronized (InternalNano.LAZY_INIT_LOCK) {
                        if (_emptyArray == null) {
                            _emptyArray = new Suggestion[0];
                        }
                    }
                }
                return _emptyArray;
            }

            public Suggestion() {
                clear();
            }

            public Suggestion clear() {
                this.query = BuildConfig.VERSION_NAME;
                this.displayText = BuildConfig.VERSION_NAME;
                this.unknownFieldData = null;
                this.cachedSize = -1;
                return this;
            }

            public boolean equals(Object o) {
                if (o == this) {
                    return true;
                }
                if (!(o instanceof Suggestion)) {
                    return false;
                }
                Suggestion other = (Suggestion) o;
                if (this.query == null) {
                    if (other.query != null) {
                        return false;
                    }
                } else if (!this.query.equals(other.query)) {
                    return false;
                }
                if (this.displayText == null) {
                    if (other.displayText != null) {
                        return false;
                    }
                } else if (!this.displayText.equals(other.displayText)) {
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
                int hashCode = (((((getClass().getName().hashCode() + 527) * 31) + (this.query == null ? 0 : this.query.hashCode())) * 31) + (this.displayText == null ? 0 : this.displayText.hashCode())) * 31;
                if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                    i = this.unknownFieldData.hashCode();
                }
                return hashCode + i;
            }

            public void writeTo(CodedOutputByteBufferNano output) throws IOException {
                if (!this.query.equals(BuildConfig.VERSION_NAME)) {
                    output.writeString(1, this.query);
                }
                if (!this.displayText.equals(BuildConfig.VERSION_NAME)) {
                    output.writeString(2, this.displayText);
                }
                super.writeTo(output);
            }

            protected int computeSerializedSize() {
                int size = super.computeSerializedSize();
                if (!this.query.equals(BuildConfig.VERSION_NAME)) {
                    size += CodedOutputByteBufferNano.computeStringSize(1, this.query);
                }
                if (this.displayText.equals(BuildConfig.VERSION_NAME)) {
                    return size;
                }
                return size + CodedOutputByteBufferNano.computeStringSize(2, this.displayText);
            }

            public Suggestion mergeFrom(CodedInputByteBufferNano input) throws IOException {
                while (true) {
                    int tag = input.readTag();
                    switch (tag) {
                        case Action.UNKNOWN /*0*/:
                            break;
                        case Type.TAP_ABOUT /*10*/:
                            this.query = input.readString();
                            continue;
                        case LogSource.GOOGLE_ANALYTICS /*18*/:
                            this.displayText = input.readString();
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

            public static Suggestion parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
                return (Suggestion) MessageNano.mergeFrom(new Suggestion(), data);
            }

            public static Suggestion parseFrom(CodedInputByteBufferNano input) throws IOException {
                return new Suggestion().mergeFrom(input);
            }
        }

        public static SuggestionResponse[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new SuggestionResponse[0];
                    }
                }
            }
            return _emptyArray;
        }

        public SuggestionResponse() {
            clear();
        }

        public SuggestionResponse clear() {
            this.suggestions = Suggestion.emptyArray();
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof SuggestionResponse)) {
                return false;
            }
            SuggestionResponse other = (SuggestionResponse) o;
            if (!InternalNano.equals(this.suggestions, other.suggestions)) {
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
            int hashCode = (((getClass().getName().hashCode() + 527) * 31) + InternalNano.hashCode(this.suggestions)) * 31;
            int hashCode2 = (this.unknownFieldData == null || this.unknownFieldData.isEmpty()) ? 0 : this.unknownFieldData.hashCode();
            return hashCode + hashCode2;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.suggestions != null && this.suggestions.length > 0) {
                for (Suggestion element : this.suggestions) {
                    if (element != null) {
                        output.writeMessage(1, element);
                    }
                }
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.suggestions != null && this.suggestions.length > 0) {
                for (Suggestion element : this.suggestions) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(1, element);
                    }
                }
            }
            return size;
        }

        public SuggestionResponse mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        int i;
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 10);
                        if (this.suggestions == null) {
                            i = 0;
                        } else {
                            i = this.suggestions.length;
                        }
                        Suggestion[] newArray = new Suggestion[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.suggestions, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new Suggestion();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i++;
                        }
                        newArray[i] = new Suggestion();
                        input.readMessage(newArray[i]);
                        this.suggestions = newArray;
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

        public static SuggestionResponse parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (SuggestionResponse) MessageNano.mergeFrom(new SuggestionResponse(), data);
        }

        public static SuggestionResponse parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new SuggestionResponse().mergeFrom(input);
        }
    }

    public static final class UniversalSectionMapping extends ExtendableMessageNano<UniversalSectionMapping> {
        private static volatile UniversalSectionMapping[] _emptyArray;
        public int templateResourceId;
        public int universalSectionId;
        public Template valueTemplate;

        public static final class Template extends ExtendableMessageNano<Template> {
            private static volatile Template[] _emptyArray;
            public Part[] parts;

            public static final class Part extends ExtendableMessageNano<Part> {
                private static volatile Part[] _emptyArray;
                public BestMatch bestmatch;
                public Section section;
                public String text;
                public Uri uri;

                public static final class BestMatch extends ExtendableMessageNano<BestMatch> {
                    private static volatile BestMatch[] _emptyArray;
                    public String defaultValue;
                    public int exceptSections;
                    public int length;
                    public boolean snippeted;

                    public static BestMatch[] emptyArray() {
                        if (_emptyArray == null) {
                            synchronized (InternalNano.LAZY_INIT_LOCK) {
                                if (_emptyArray == null) {
                                    _emptyArray = new BestMatch[0];
                                }
                            }
                        }
                        return _emptyArray;
                    }

                    public BestMatch() {
                        clear();
                    }

                    public BestMatch clear() {
                        this.defaultValue = BuildConfig.VERSION_NAME;
                        this.length = 0;
                        this.snippeted = false;
                        this.exceptSections = 0;
                        this.unknownFieldData = null;
                        this.cachedSize = -1;
                        return this;
                    }

                    public boolean equals(Object o) {
                        if (o == this) {
                            return true;
                        }
                        if (!(o instanceof BestMatch)) {
                            return false;
                        }
                        BestMatch other = (BestMatch) o;
                        if (this.defaultValue == null) {
                            if (other.defaultValue != null) {
                                return false;
                            }
                        } else if (!this.defaultValue.equals(other.defaultValue)) {
                            return false;
                        }
                        if (this.length != other.length || this.snippeted != other.snippeted || this.exceptSections != other.exceptSections) {
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
                        int hashCode = (((((((((getClass().getName().hashCode() + 527) * 31) + (this.defaultValue == null ? 0 : this.defaultValue.hashCode())) * 31) + this.length) * 31) + (this.snippeted ? 1231 : 1237)) * 31) + this.exceptSections) * 31;
                        if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                            i = this.unknownFieldData.hashCode();
                        }
                        return hashCode + i;
                    }

                    public void writeTo(CodedOutputByteBufferNano output) throws IOException {
                        if (!this.defaultValue.equals(BuildConfig.VERSION_NAME)) {
                            output.writeString(1, this.defaultValue);
                        }
                        if (this.length != 0) {
                            output.writeUInt32(2, this.length);
                        }
                        if (this.snippeted) {
                            output.writeBool(3, this.snippeted);
                        }
                        if (this.exceptSections != 0) {
                            output.writeUInt32(4, this.exceptSections);
                        }
                        super.writeTo(output);
                    }

                    protected int computeSerializedSize() {
                        int size = super.computeSerializedSize();
                        if (!this.defaultValue.equals(BuildConfig.VERSION_NAME)) {
                            size += CodedOutputByteBufferNano.computeStringSize(1, this.defaultValue);
                        }
                        if (this.length != 0) {
                            size += CodedOutputByteBufferNano.computeUInt32Size(2, this.length);
                        }
                        if (this.snippeted) {
                            size += CodedOutputByteBufferNano.computeBoolSize(3, this.snippeted);
                        }
                        if (this.exceptSections != 0) {
                            return size + CodedOutputByteBufferNano.computeUInt32Size(4, this.exceptSections);
                        }
                        return size;
                    }

                    public BestMatch mergeFrom(CodedInputByteBufferNano input) throws IOException {
                        while (true) {
                            int tag = input.readTag();
                            switch (tag) {
                                case Action.UNKNOWN /*0*/:
                                    break;
                                case Type.TAP_ABOUT /*10*/:
                                    this.defaultValue = input.readString();
                                    continue;
                                case LogSource.GMS_CORE_PEOPLE /*16*/:
                                    this.length = input.readUInt32();
                                    continue;
                                case LogSource.LB_C /*24*/:
                                    this.snippeted = input.readBool();
                                    continue;
                                case LogSource.SOCIAL /*32*/:
                                    this.exceptSections = input.readUInt32();
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

                    public static BestMatch parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
                        return (BestMatch) MessageNano.mergeFrom(new BestMatch(), data);
                    }

                    public static BestMatch parseFrom(CodedInputByteBufferNano input) throws IOException {
                        return new BestMatch().mergeFrom(input);
                    }
                }

                public static final class Section extends ExtendableMessageNano<Section> {
                    private static volatile Section[] _emptyArray;
                    public String defaultValue;
                    public int length;
                    public int sectionId;
                    public boolean snippeted;

                    public static Section[] emptyArray() {
                        if (_emptyArray == null) {
                            synchronized (InternalNano.LAZY_INIT_LOCK) {
                                if (_emptyArray == null) {
                                    _emptyArray = new Section[0];
                                }
                            }
                        }
                        return _emptyArray;
                    }

                    public Section() {
                        clear();
                    }

                    public Section clear() {
                        this.sectionId = 0;
                        this.length = 0;
                        this.snippeted = false;
                        this.defaultValue = BuildConfig.VERSION_NAME;
                        this.unknownFieldData = null;
                        this.cachedSize = -1;
                        return this;
                    }

                    public boolean equals(Object o) {
                        if (o == this) {
                            return true;
                        }
                        if (!(o instanceof Section)) {
                            return false;
                        }
                        Section other = (Section) o;
                        if (this.sectionId != other.sectionId || this.length != other.length || this.snippeted != other.snippeted) {
                            return false;
                        }
                        if (this.defaultValue == null) {
                            if (other.defaultValue != null) {
                                return false;
                            }
                        } else if (!this.defaultValue.equals(other.defaultValue)) {
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
                        int hashCode = (((((((((getClass().getName().hashCode() + 527) * 31) + this.sectionId) * 31) + this.length) * 31) + (this.snippeted ? 1231 : 1237)) * 31) + (this.defaultValue == null ? 0 : this.defaultValue.hashCode())) * 31;
                        if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                            i = this.unknownFieldData.hashCode();
                        }
                        return hashCode + i;
                    }

                    public void writeTo(CodedOutputByteBufferNano output) throws IOException {
                        if (this.sectionId != 0) {
                            output.writeUInt32(1, this.sectionId);
                        }
                        if (this.length != 0) {
                            output.writeUInt32(2, this.length);
                        }
                        if (this.snippeted) {
                            output.writeBool(3, this.snippeted);
                        }
                        if (!this.defaultValue.equals(BuildConfig.VERSION_NAME)) {
                            output.writeString(4, this.defaultValue);
                        }
                        super.writeTo(output);
                    }

                    protected int computeSerializedSize() {
                        int size = super.computeSerializedSize();
                        if (this.sectionId != 0) {
                            size += CodedOutputByteBufferNano.computeUInt32Size(1, this.sectionId);
                        }
                        if (this.length != 0) {
                            size += CodedOutputByteBufferNano.computeUInt32Size(2, this.length);
                        }
                        if (this.snippeted) {
                            size += CodedOutputByteBufferNano.computeBoolSize(3, this.snippeted);
                        }
                        if (this.defaultValue.equals(BuildConfig.VERSION_NAME)) {
                            return size;
                        }
                        return size + CodedOutputByteBufferNano.computeStringSize(4, this.defaultValue);
                    }

                    public Section mergeFrom(CodedInputByteBufferNano input) throws IOException {
                        while (true) {
                            int tag = input.readTag();
                            switch (tag) {
                                case Action.UNKNOWN /*0*/:
                                    break;
                                case Type.TAP_GET_LINK /*8*/:
                                    this.sectionId = input.readUInt32();
                                    continue;
                                case LogSource.GMS_CORE_PEOPLE /*16*/:
                                    this.length = input.readUInt32();
                                    continue;
                                case LogSource.LB_C /*24*/:
                                    this.snippeted = input.readBool();
                                    continue;
                                case LogSource.NOVA /*34*/:
                                    this.defaultValue = input.readString();
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

                    public static Section parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
                        return (Section) MessageNano.mergeFrom(new Section(), data);
                    }

                    public static Section parseFrom(CodedInputByteBufferNano input) throws IOException {
                        return new Section().mergeFrom(input);
                    }
                }

                public static final class Uri extends ExtendableMessageNano<Uri> {
                    private static volatile Uri[] _emptyArray;

                    public static Uri[] emptyArray() {
                        if (_emptyArray == null) {
                            synchronized (InternalNano.LAZY_INIT_LOCK) {
                                if (_emptyArray == null) {
                                    _emptyArray = new Uri[0];
                                }
                            }
                        }
                        return _emptyArray;
                    }

                    public Uri() {
                        clear();
                    }

                    public Uri clear() {
                        this.unknownFieldData = null;
                        this.cachedSize = -1;
                        return this;
                    }

                    public boolean equals(Object o) {
                        if (o == this) {
                            return true;
                        }
                        if (!(o instanceof Uri)) {
                            return false;
                        }
                        Uri other = (Uri) o;
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

                    public Uri mergeFrom(CodedInputByteBufferNano input) throws IOException {
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

                    public static Uri parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
                        return (Uri) MessageNano.mergeFrom(new Uri(), data);
                    }

                    public static Uri parseFrom(CodedInputByteBufferNano input) throws IOException {
                        return new Uri().mergeFrom(input);
                    }
                }

                public static Part[] emptyArray() {
                    if (_emptyArray == null) {
                        synchronized (InternalNano.LAZY_INIT_LOCK) {
                            if (_emptyArray == null) {
                                _emptyArray = new Part[0];
                            }
                        }
                    }
                    return _emptyArray;
                }

                public Part() {
                    clear();
                }

                public Part clear() {
                    this.text = BuildConfig.VERSION_NAME;
                    this.section = null;
                    this.bestmatch = null;
                    this.uri = null;
                    this.unknownFieldData = null;
                    this.cachedSize = -1;
                    return this;
                }

                public boolean equals(Object o) {
                    if (o == this) {
                        return true;
                    }
                    if (!(o instanceof Part)) {
                        return false;
                    }
                    Part other = (Part) o;
                    if (this.text == null) {
                        if (other.text != null) {
                            return false;
                        }
                    } else if (!this.text.equals(other.text)) {
                        return false;
                    }
                    if (this.section == null) {
                        if (other.section != null) {
                            return false;
                        }
                    } else if (!this.section.equals(other.section)) {
                        return false;
                    }
                    if (this.bestmatch == null) {
                        if (other.bestmatch != null) {
                            return false;
                        }
                    } else if (!this.bestmatch.equals(other.bestmatch)) {
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
                    int hashCode = (((((((((getClass().getName().hashCode() + 527) * 31) + (this.text == null ? 0 : this.text.hashCode())) * 31) + (this.section == null ? 0 : this.section.hashCode())) * 31) + (this.bestmatch == null ? 0 : this.bestmatch.hashCode())) * 31) + (this.uri == null ? 0 : this.uri.hashCode())) * 31;
                    if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                        i = this.unknownFieldData.hashCode();
                    }
                    return hashCode + i;
                }

                public void writeTo(CodedOutputByteBufferNano output) throws IOException {
                    if (!this.text.equals(BuildConfig.VERSION_NAME)) {
                        output.writeString(1, this.text);
                    }
                    if (this.section != null) {
                        output.writeMessage(2, this.section);
                    }
                    if (this.bestmatch != null) {
                        output.writeMessage(3, this.bestmatch);
                    }
                    if (this.uri != null) {
                        output.writeMessage(4, this.uri);
                    }
                    super.writeTo(output);
                }

                protected int computeSerializedSize() {
                    int size = super.computeSerializedSize();
                    if (!this.text.equals(BuildConfig.VERSION_NAME)) {
                        size += CodedOutputByteBufferNano.computeStringSize(1, this.text);
                    }
                    if (this.section != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(2, this.section);
                    }
                    if (this.bestmatch != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(3, this.bestmatch);
                    }
                    if (this.uri != null) {
                        return size + CodedOutputByteBufferNano.computeMessageSize(4, this.uri);
                    }
                    return size;
                }

                public Part mergeFrom(CodedInputByteBufferNano input) throws IOException {
                    while (true) {
                        int tag = input.readTag();
                        switch (tag) {
                            case Action.UNKNOWN /*0*/:
                                break;
                            case Type.TAP_ABOUT /*10*/:
                                this.text = input.readString();
                                continue;
                            case LogSource.GOOGLE_ANALYTICS /*18*/:
                                if (this.section == null) {
                                    this.section = new Section();
                                }
                                input.readMessage(this.section);
                                continue;
                            case LogSource.ANDROID_CAMERA /*26*/:
                                if (this.bestmatch == null) {
                                    this.bestmatch = new BestMatch();
                                }
                                input.readMessage(this.bestmatch);
                                continue;
                            case LogSource.NOVA /*34*/:
                                if (this.uri == null) {
                                    this.uri = new Uri();
                                }
                                input.readMessage(this.uri);
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

                public static Part parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
                    return (Part) MessageNano.mergeFrom(new Part(), data);
                }

                public static Part parseFrom(CodedInputByteBufferNano input) throws IOException {
                    return new Part().mergeFrom(input);
                }
            }

            public static Template[] emptyArray() {
                if (_emptyArray == null) {
                    synchronized (InternalNano.LAZY_INIT_LOCK) {
                        if (_emptyArray == null) {
                            _emptyArray = new Template[0];
                        }
                    }
                }
                return _emptyArray;
            }

            public Template() {
                clear();
            }

            public Template clear() {
                this.parts = Part.emptyArray();
                this.unknownFieldData = null;
                this.cachedSize = -1;
                return this;
            }

            public boolean equals(Object o) {
                if (o == this) {
                    return true;
                }
                if (!(o instanceof Template)) {
                    return false;
                }
                Template other = (Template) o;
                if (!InternalNano.equals(this.parts, other.parts)) {
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
                int hashCode = (((getClass().getName().hashCode() + 527) * 31) + InternalNano.hashCode(this.parts)) * 31;
                int hashCode2 = (this.unknownFieldData == null || this.unknownFieldData.isEmpty()) ? 0 : this.unknownFieldData.hashCode();
                return hashCode + hashCode2;
            }

            public void writeTo(CodedOutputByteBufferNano output) throws IOException {
                if (this.parts != null && this.parts.length > 0) {
                    for (Part element : this.parts) {
                        if (element != null) {
                            output.writeMessage(2, element);
                        }
                    }
                }
                super.writeTo(output);
            }

            protected int computeSerializedSize() {
                int size = super.computeSerializedSize();
                if (this.parts != null && this.parts.length > 0) {
                    for (Part element : this.parts) {
                        if (element != null) {
                            size += CodedOutputByteBufferNano.computeMessageSize(2, element);
                        }
                    }
                }
                return size;
            }

            public Template mergeFrom(CodedInputByteBufferNano input) throws IOException {
                while (true) {
                    int tag = input.readTag();
                    switch (tag) {
                        case Action.UNKNOWN /*0*/:
                            break;
                        case LogSource.GOOGLE_ANALYTICS /*18*/:
                            int i;
                            int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 18);
                            if (this.parts == null) {
                                i = 0;
                            } else {
                                i = this.parts.length;
                            }
                            Part[] newArray = new Part[(i + arrayLength)];
                            if (i != 0) {
                                System.arraycopy(this.parts, 0, newArray, 0, i);
                            }
                            while (i < newArray.length - 1) {
                                newArray[i] = new Part();
                                input.readMessage(newArray[i]);
                                input.readTag();
                                i++;
                            }
                            newArray[i] = new Part();
                            input.readMessage(newArray[i]);
                            this.parts = newArray;
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

            public static Template parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
                return (Template) MessageNano.mergeFrom(new Template(), data);
            }

            public static Template parseFrom(CodedInputByteBufferNano input) throws IOException {
                return new Template().mergeFrom(input);
            }
        }

        public static UniversalSectionMapping[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new UniversalSectionMapping[0];
                    }
                }
            }
            return _emptyArray;
        }

        public UniversalSectionMapping() {
            clear();
        }

        public UniversalSectionMapping clear() {
            this.universalSectionId = 0;
            this.valueTemplate = null;
            this.templateResourceId = 0;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof UniversalSectionMapping)) {
                return false;
            }
            UniversalSectionMapping other = (UniversalSectionMapping) o;
            if (this.universalSectionId != other.universalSectionId) {
                return false;
            }
            if (this.valueTemplate == null) {
                if (other.valueTemplate != null) {
                    return false;
                }
            } else if (!this.valueTemplate.equals(other.valueTemplate)) {
                return false;
            }
            if (this.templateResourceId != other.templateResourceId) {
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
            int hashCode = (((((((getClass().getName().hashCode() + 527) * 31) + this.universalSectionId) * 31) + (this.valueTemplate == null ? 0 : this.valueTemplate.hashCode())) * 31) + this.templateResourceId) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.universalSectionId != 0) {
                output.writeUInt32(1, this.universalSectionId);
            }
            if (this.valueTemplate != null) {
                output.writeMessage(2, this.valueTemplate);
            }
            if (this.templateResourceId != 0) {
                output.writeUInt32(3, this.templateResourceId);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.universalSectionId != 0) {
                size += CodedOutputByteBufferNano.computeUInt32Size(1, this.universalSectionId);
            }
            if (this.valueTemplate != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(2, this.valueTemplate);
            }
            if (this.templateResourceId != 0) {
                return size + CodedOutputByteBufferNano.computeUInt32Size(3, this.templateResourceId);
            }
            return size;
        }

        public UniversalSectionMapping mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_GET_LINK /*8*/:
                        this.universalSectionId = input.readUInt32();
                        continue;
                    case LogSource.GOOGLE_ANALYTICS /*18*/:
                        if (this.valueTemplate == null) {
                            this.valueTemplate = new Template();
                        }
                        input.readMessage(this.valueTemplate);
                        continue;
                    case LogSource.LB_C /*24*/:
                        this.templateResourceId = input.readUInt32();
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

        public static UniversalSectionMapping parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (UniversalSectionMapping) MessageNano.mergeFrom(new UniversalSectionMapping(), data);
        }

        public static UniversalSectionMapping parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new UniversalSectionMapping().mergeFrom(input);
        }
    }

    public static final class UsageReport extends ExtendableMessageNano<UsageReport> {
        private static volatile UsageReport[] _emptyArray;
        public long callTimestampMs;
        public String callerPackageName;
        public String corpusName;
        public Document document;
        public int eventStatus;
        public boolean isDeviceOnly;
        public String packageName;
        public int taskPos;
        public String uri;
        public long usageTimestampMs;
        public int usageType;

        public static UsageReport[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new UsageReport[0];
                    }
                }
            }
            return _emptyArray;
        }

        public UsageReport() {
            clear();
        }

        public UsageReport clear() {
            this.callerPackageName = BuildConfig.VERSION_NAME;
            this.callTimestampMs = 0;
            this.packageName = BuildConfig.VERSION_NAME;
            this.corpusName = BuildConfig.VERSION_NAME;
            this.uri = BuildConfig.VERSION_NAME;
            this.usageTimestampMs = 0;
            this.usageType = 0;
            this.document = null;
            this.taskPos = -1;
            this.isDeviceOnly = false;
            this.eventStatus = 0;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof UsageReport)) {
                return false;
            }
            UsageReport other = (UsageReport) o;
            if (this.callerPackageName == null) {
                if (other.callerPackageName != null) {
                    return false;
                }
            } else if (!this.callerPackageName.equals(other.callerPackageName)) {
                return false;
            }
            if (this.callTimestampMs != other.callTimestampMs) {
                return false;
            }
            if (this.packageName == null) {
                if (other.packageName != null) {
                    return false;
                }
            } else if (!this.packageName.equals(other.packageName)) {
                return false;
            }
            if (this.corpusName == null) {
                if (other.corpusName != null) {
                    return false;
                }
            } else if (!this.corpusName.equals(other.corpusName)) {
                return false;
            }
            if (this.uri == null) {
                if (other.uri != null) {
                    return false;
                }
            } else if (!this.uri.equals(other.uri)) {
                return false;
            }
            if (this.usageTimestampMs != other.usageTimestampMs || this.usageType != other.usageType) {
                return false;
            }
            if (this.document == null) {
                if (other.document != null) {
                    return false;
                }
            } else if (!this.document.equals(other.document)) {
                return false;
            }
            if (this.taskPos != other.taskPos || this.isDeviceOnly != other.isDeviceOnly || this.eventStatus != other.eventStatus) {
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
            int hashCode = (((((((((((((((((((((((getClass().getName().hashCode() + 527) * 31) + (this.callerPackageName == null ? 0 : this.callerPackageName.hashCode())) * 31) + ((int) (this.callTimestampMs ^ (this.callTimestampMs >>> 32)))) * 31) + (this.packageName == null ? 0 : this.packageName.hashCode())) * 31) + (this.corpusName == null ? 0 : this.corpusName.hashCode())) * 31) + (this.uri == null ? 0 : this.uri.hashCode())) * 31) + ((int) (this.usageTimestampMs ^ (this.usageTimestampMs >>> 32)))) * 31) + this.usageType) * 31) + (this.document == null ? 0 : this.document.hashCode())) * 31) + this.taskPos) * 31) + (this.isDeviceOnly ? 1231 : 1237)) * 31) + this.eventStatus) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (!this.callerPackageName.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(1, this.callerPackageName);
            }
            if (this.callTimestampMs != 0) {
                output.writeUInt64(2, this.callTimestampMs);
            }
            if (!this.packageName.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(3, this.packageName);
            }
            if (!this.corpusName.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(4, this.corpusName);
            }
            if (!this.uri.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(5, this.uri);
            }
            if (this.usageTimestampMs != 0) {
                output.writeUInt64(6, this.usageTimestampMs);
            }
            if (this.usageType != 0) {
                output.writeInt32(7, this.usageType);
            }
            if (this.document != null) {
                output.writeMessage(8, this.document);
            }
            if (this.taskPos != -1) {
                output.writeInt32(9, this.taskPos);
            }
            if (this.isDeviceOnly) {
                output.writeBool(10, this.isDeviceOnly);
            }
            if (this.eventStatus != 0) {
                output.writeInt32(11, this.eventStatus);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (!this.callerPackageName.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.callerPackageName);
            }
            if (this.callTimestampMs != 0) {
                size += CodedOutputByteBufferNano.computeUInt64Size(2, this.callTimestampMs);
            }
            if (!this.packageName.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(3, this.packageName);
            }
            if (!this.corpusName.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(4, this.corpusName);
            }
            if (!this.uri.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(5, this.uri);
            }
            if (this.usageTimestampMs != 0) {
                size += CodedOutputByteBufferNano.computeUInt64Size(6, this.usageTimestampMs);
            }
            if (this.usageType != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(7, this.usageType);
            }
            if (this.document != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(8, this.document);
            }
            if (this.taskPos != -1) {
                size += CodedOutputByteBufferNano.computeInt32Size(9, this.taskPos);
            }
            if (this.isDeviceOnly) {
                size += CodedOutputByteBufferNano.computeBoolSize(10, this.isDeviceOnly);
            }
            if (this.eventStatus != 0) {
                return size + CodedOutputByteBufferNano.computeInt32Size(11, this.eventStatus);
            }
            return size;
        }

        public UsageReport mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                int value;
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        this.callerPackageName = input.readString();
                        continue;
                    case LogSource.GMS_CORE_PEOPLE /*16*/:
                        this.callTimestampMs = input.readUInt64();
                        continue;
                    case LogSource.ANDROID_CAMERA /*26*/:
                        this.packageName = input.readString();
                        continue;
                    case LogSource.NOVA /*34*/:
                        this.corpusName = input.readString();
                        continue;
                    case LogSource.PHOTOS /*42*/:
                        this.uri = input.readString();
                        continue;
                    case LogSource.BACKDROP /*48*/:
                        this.usageTimestampMs = input.readUInt64();
                        continue;
                    case LogSource.DOCS /*56*/:
                        value = input.readInt32();
                        switch (value) {
                            case Action.UNKNOWN /*0*/:
                            case TimeSelection.Type.TEMPORARY /*1*/:
                            case TimeSelection.Type.INDEFINITELY /*2*/:
                                this.usageType = value;
                                break;
                            default:
                                continue;
                        }
                    case LogSource.WIFI_ASSISTANT /*66*/:
                        if (this.document == null) {
                            this.document = new Document();
                        }
                        input.readMessage(this.document);
                        continue;
                    case LogSource.JUSTSPEAK /*72*/:
                        this.taskPos = input.readInt32();
                        continue;
                    case LogSource.CRONET_ANDROID_GSA /*80*/:
                        this.isDeviceOnly = input.readBool();
                        continue;
                    case LogSource.EMERGENCY_ASSIST /*88*/:
                        value = input.readInt32();
                        switch (value) {
                            case Action.UNKNOWN /*0*/:
                            case TimeSelection.Type.TEMPORARY /*1*/:
                            case TimeSelection.Type.INDEFINITELY /*2*/:
                                this.eventStatus = value;
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

        public static UsageReport parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (UsageReport) MessageNano.mergeFrom(new UsageReport(), data);
        }

        public static UsageReport parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new UsageReport().mergeFrom(input);
        }
    }

    public static final class UsageReportsResponse extends ExtendableMessageNano<UsageReportsResponse> {
        private static volatile UsageReportsResponse[] _emptyArray;
        public boolean decodeFailed;
        public long epoch;
        public long nextLocation;
        public Report[] report;

        public static final class Report extends ExtendableMessageNano<Report> {
            private static volatile Report[] _emptyArray;
            public Document document;
            public NativeUsageReport usageReport;

            public static Report[] emptyArray() {
                if (_emptyArray == null) {
                    synchronized (InternalNano.LAZY_INIT_LOCK) {
                        if (_emptyArray == null) {
                            _emptyArray = new Report[0];
                        }
                    }
                }
                return _emptyArray;
            }

            public Report() {
                clear();
            }

            public Report clear() {
                this.usageReport = null;
                this.document = null;
                this.unknownFieldData = null;
                this.cachedSize = -1;
                return this;
            }

            public boolean equals(Object o) {
                if (o == this) {
                    return true;
                }
                if (!(o instanceof Report)) {
                    return false;
                }
                Report other = (Report) o;
                if (this.usageReport == null) {
                    if (other.usageReport != null) {
                        return false;
                    }
                } else if (!this.usageReport.equals(other.usageReport)) {
                    return false;
                }
                if (this.document == null) {
                    if (other.document != null) {
                        return false;
                    }
                } else if (!this.document.equals(other.document)) {
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
                int hashCode = (((((getClass().getName().hashCode() + 527) * 31) + (this.usageReport == null ? 0 : this.usageReport.hashCode())) * 31) + (this.document == null ? 0 : this.document.hashCode())) * 31;
                if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                    i = this.unknownFieldData.hashCode();
                }
                return hashCode + i;
            }

            public void writeTo(CodedOutputByteBufferNano output) throws IOException {
                if (this.usageReport != null) {
                    output.writeMessage(1, this.usageReport);
                }
                if (this.document != null) {
                    output.writeMessage(2, this.document);
                }
                super.writeTo(output);
            }

            protected int computeSerializedSize() {
                int size = super.computeSerializedSize();
                if (this.usageReport != null) {
                    size += CodedOutputByteBufferNano.computeMessageSize(1, this.usageReport);
                }
                if (this.document != null) {
                    return size + CodedOutputByteBufferNano.computeMessageSize(2, this.document);
                }
                return size;
            }

            public Report mergeFrom(CodedInputByteBufferNano input) throws IOException {
                while (true) {
                    int tag = input.readTag();
                    switch (tag) {
                        case Action.UNKNOWN /*0*/:
                            break;
                        case Type.TAP_ABOUT /*10*/:
                            if (this.usageReport == null) {
                                this.usageReport = new NativeUsageReport();
                            }
                            input.readMessage(this.usageReport);
                            continue;
                        case LogSource.GOOGLE_ANALYTICS /*18*/:
                            if (this.document == null) {
                                this.document = new Document();
                            }
                            input.readMessage(this.document);
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

            public static Report parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
                return (Report) MessageNano.mergeFrom(new Report(), data);
            }

            public static Report parseFrom(CodedInputByteBufferNano input) throws IOException {
                return new Report().mergeFrom(input);
            }
        }

        public static UsageReportsResponse[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new UsageReportsResponse[0];
                    }
                }
            }
            return _emptyArray;
        }

        public UsageReportsResponse() {
            clear();
        }

        public UsageReportsResponse clear() {
            this.report = Report.emptyArray();
            this.epoch = 0;
            this.nextLocation = 0;
            this.decodeFailed = false;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof UsageReportsResponse)) {
                return false;
            }
            UsageReportsResponse other = (UsageReportsResponse) o;
            if (!InternalNano.equals(this.report, other.report) || this.epoch != other.epoch || this.nextLocation != other.nextLocation || this.decodeFailed != other.decodeFailed) {
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
            int hashCode = (((((((((getClass().getName().hashCode() + 527) * 31) + InternalNano.hashCode(this.report)) * 31) + ((int) (this.epoch ^ (this.epoch >>> 32)))) * 31) + ((int) (this.nextLocation ^ (this.nextLocation >>> 32)))) * 31) + (this.decodeFailed ? 1231 : 1237)) * 31;
            int hashCode2 = (this.unknownFieldData == null || this.unknownFieldData.isEmpty()) ? 0 : this.unknownFieldData.hashCode();
            return hashCode + hashCode2;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.report != null && this.report.length > 0) {
                for (Report element : this.report) {
                    if (element != null) {
                        output.writeMessage(1, element);
                    }
                }
            }
            if (this.epoch != 0) {
                output.writeUInt64(2, this.epoch);
            }
            if (this.nextLocation != 0) {
                output.writeUInt64(3, this.nextLocation);
            }
            if (this.decodeFailed) {
                output.writeBool(4, this.decodeFailed);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.report != null && this.report.length > 0) {
                for (Report element : this.report) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(1, element);
                    }
                }
            }
            if (this.epoch != 0) {
                size += CodedOutputByteBufferNano.computeUInt64Size(2, this.epoch);
            }
            if (this.nextLocation != 0) {
                size += CodedOutputByteBufferNano.computeUInt64Size(3, this.nextLocation);
            }
            if (this.decodeFailed) {
                return size + CodedOutputByteBufferNano.computeBoolSize(4, this.decodeFailed);
            }
            return size;
        }

        public UsageReportsResponse mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        int i;
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 10);
                        if (this.report == null) {
                            i = 0;
                        } else {
                            i = this.report.length;
                        }
                        Report[] newArray = new Report[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.report, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new Report();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i++;
                        }
                        newArray[i] = new Report();
                        input.readMessage(newArray[i]);
                        this.report = newArray;
                        continue;
                    case LogSource.GMS_CORE_PEOPLE /*16*/:
                        this.epoch = input.readUInt64();
                        continue;
                    case LogSource.LB_C /*24*/:
                        this.nextLocation = input.readUInt64();
                        continue;
                    case LogSource.SOCIAL /*32*/:
                        this.decodeFailed = input.readBool();
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

        public static UsageReportsResponse parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (UsageReportsResponse) MessageNano.mergeFrom(new UsageReportsResponse(), data);
        }

        public static UsageReportsResponse parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new UsageReportsResponse().mergeFrom(input);
        }
    }

    public static final class UsageStats extends ExtendableMessageNano<UsageStats> {
        private static volatile UsageStats[] _emptyArray;
        public Corpus[] corpus;

        public static final class Corpus extends ExtendableMessageNano<Corpus> {
            private static volatile Corpus[] _emptyArray;
            public int deletedDocuments;
            public long deletedDocumentsSize;
            public int documents;
            public long documentsSize;
            public int id;

            public static Corpus[] emptyArray() {
                if (_emptyArray == null) {
                    synchronized (InternalNano.LAZY_INIT_LOCK) {
                        if (_emptyArray == null) {
                            _emptyArray = new Corpus[0];
                        }
                    }
                }
                return _emptyArray;
            }

            public Corpus() {
                clear();
            }

            public Corpus clear() {
                this.id = 0;
                this.documents = 0;
                this.deletedDocuments = 0;
                this.documentsSize = 0;
                this.deletedDocumentsSize = 0;
                this.unknownFieldData = null;
                this.cachedSize = -1;
                return this;
            }

            public boolean equals(Object o) {
                if (o == this) {
                    return true;
                }
                if (!(o instanceof Corpus)) {
                    return false;
                }
                Corpus other = (Corpus) o;
                if (this.id != other.id || this.documents != other.documents || this.deletedDocuments != other.deletedDocuments || this.documentsSize != other.documentsSize || this.deletedDocumentsSize != other.deletedDocumentsSize) {
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
                int hashCode = (((((((((((getClass().getName().hashCode() + 527) * 31) + this.id) * 31) + this.documents) * 31) + this.deletedDocuments) * 31) + ((int) (this.documentsSize ^ (this.documentsSize >>> 32)))) * 31) + ((int) (this.deletedDocumentsSize ^ (this.deletedDocumentsSize >>> 32)))) * 31;
                int hashCode2 = (this.unknownFieldData == null || this.unknownFieldData.isEmpty()) ? 0 : this.unknownFieldData.hashCode();
                return hashCode + hashCode2;
            }

            public void writeTo(CodedOutputByteBufferNano output) throws IOException {
                if (this.id != 0) {
                    output.writeInt32(1, this.id);
                }
                if (this.documents != 0) {
                    output.writeUInt32(3, this.documents);
                }
                if (this.deletedDocuments != 0) {
                    output.writeUInt32(4, this.deletedDocuments);
                }
                if (this.documentsSize != 0) {
                    output.writeUInt64(5, this.documentsSize);
                }
                if (this.deletedDocumentsSize != 0) {
                    output.writeUInt64(6, this.deletedDocumentsSize);
                }
                super.writeTo(output);
            }

            protected int computeSerializedSize() {
                int size = super.computeSerializedSize();
                if (this.id != 0) {
                    size += CodedOutputByteBufferNano.computeInt32Size(1, this.id);
                }
                if (this.documents != 0) {
                    size += CodedOutputByteBufferNano.computeUInt32Size(3, this.documents);
                }
                if (this.deletedDocuments != 0) {
                    size += CodedOutputByteBufferNano.computeUInt32Size(4, this.deletedDocuments);
                }
                if (this.documentsSize != 0) {
                    size += CodedOutputByteBufferNano.computeUInt64Size(5, this.documentsSize);
                }
                if (this.deletedDocumentsSize != 0) {
                    return size + CodedOutputByteBufferNano.computeUInt64Size(6, this.deletedDocumentsSize);
                }
                return size;
            }

            public Corpus mergeFrom(CodedInputByteBufferNano input) throws IOException {
                while (true) {
                    int tag = input.readTag();
                    switch (tag) {
                        case Action.UNKNOWN /*0*/:
                            break;
                        case Type.TAP_GET_LINK /*8*/:
                            this.id = input.readInt32();
                            continue;
                        case LogSource.LB_C /*24*/:
                            this.documents = input.readUInt32();
                            continue;
                        case LogSource.SOCIAL /*32*/:
                            this.deletedDocuments = input.readUInt32();
                            continue;
                        case LogSource.COPRESENCE /*40*/:
                            this.documentsSize = input.readUInt64();
                            continue;
                        case LogSource.BACKDROP /*48*/:
                            this.deletedDocumentsSize = input.readUInt64();
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

            public static Corpus parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
                return (Corpus) MessageNano.mergeFrom(new Corpus(), data);
            }

            public static Corpus parseFrom(CodedInputByteBufferNano input) throws IOException {
                return new Corpus().mergeFrom(input);
            }
        }

        public static UsageStats[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new UsageStats[0];
                    }
                }
            }
            return _emptyArray;
        }

        public UsageStats() {
            clear();
        }

        public UsageStats clear() {
            this.corpus = Corpus.emptyArray();
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof UsageStats)) {
                return false;
            }
            UsageStats other = (UsageStats) o;
            if (!InternalNano.equals(this.corpus, other.corpus)) {
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
            int hashCode = (((getClass().getName().hashCode() + 527) * 31) + InternalNano.hashCode(this.corpus)) * 31;
            int hashCode2 = (this.unknownFieldData == null || this.unknownFieldData.isEmpty()) ? 0 : this.unknownFieldData.hashCode();
            return hashCode + hashCode2;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.corpus != null && this.corpus.length > 0) {
                for (Corpus element : this.corpus) {
                    if (element != null) {
                        output.writeMessage(1, element);
                    }
                }
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.corpus != null && this.corpus.length > 0) {
                for (Corpus element : this.corpus) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(1, element);
                    }
                }
            }
            return size;
        }

        public UsageStats mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        int i;
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 10);
                        if (this.corpus == null) {
                            i = 0;
                        } else {
                            i = this.corpus.length;
                        }
                        Corpus[] newArray = new Corpus[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.corpus, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new Corpus();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i++;
                        }
                        newArray[i] = new Corpus();
                        input.readMessage(newArray[i]);
                        this.corpus = newArray;
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

        public static UsageStats parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (UsageStats) MessageNano.mergeFrom(new UsageStats(), data);
        }

        public static UsageStats parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new UsageStats().mergeFrom(input);
        }
    }

    public interface UsageType {
        public static final int GENERAL_USE = 0;
        public static final int GLOBAL_SEARCH_USE = 2;
        public static final int SEARCHED_USE = 1;
    }
}
