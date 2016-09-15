package com.google.android.gms.icing;

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

public interface IcingDocument {

    public static final class Document extends ExtendableMessageNano<Document> {
        private static volatile Document[] _emptyArray;
        public int corpusId;
        public long createdTimestampMs;
        public Language[] languages;
        public int score;
        public Section[] sections;
        public String uri;

        public static final class Language extends ExtendableMessageNano<Language> {
            private static volatile Language[] _emptyArray;
            public int language;
            public int score;
            public boolean usedClassifier;

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
                this.language = 0;
                this.score = 0;
                this.usedClassifier = false;
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
                if (this.language != other.language || this.score != other.score || this.usedClassifier != other.usedClassifier) {
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
                int hashCode = (((((((getClass().getName().hashCode() + 527) * 31) + this.language) * 31) + this.score) * 31) + (this.usedClassifier ? 1231 : 1237)) * 31;
                int hashCode2 = (this.unknownFieldData == null || this.unknownFieldData.isEmpty()) ? 0 : this.unknownFieldData.hashCode();
                return hashCode + hashCode2;
            }

            public void writeTo(CodedOutputByteBufferNano output) throws IOException {
                if (this.language != 0) {
                    output.writeInt32(1, this.language);
                }
                if (this.score != 0) {
                    output.writeUInt32(2, this.score);
                }
                if (this.usedClassifier) {
                    output.writeBool(3, this.usedClassifier);
                }
                super.writeTo(output);
            }

            protected int computeSerializedSize() {
                int size = super.computeSerializedSize();
                if (this.language != 0) {
                    size += CodedOutputByteBufferNano.computeInt32Size(1, this.language);
                }
                if (this.score != 0) {
                    size += CodedOutputByteBufferNano.computeUInt32Size(2, this.score);
                }
                if (this.usedClassifier) {
                    return size + CodedOutputByteBufferNano.computeBoolSize(3, this.usedClassifier);
                }
                return size;
            }

            public Language mergeFrom(CodedInputByteBufferNano input) throws IOException {
                while (true) {
                    int tag = input.readTag();
                    switch (tag) {
                        case Action.UNKNOWN /*0*/:
                            break;
                        case Type.TAP_GET_LINK /*8*/:
                            this.language = input.readInt32();
                            continue;
                        case LogSource.GMS_CORE_PEOPLE /*16*/:
                            this.score = input.readUInt32();
                            continue;
                        case LogSource.LB_C /*24*/:
                            this.usedClassifier = input.readBool();
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

        public static final class Section extends ExtendableMessageNano<Section> {
            private static volatile Section[] _emptyArray;
            public SectionConfig config;
            public byte[] content;
            public byte[] contentToIndex;
            public int id;
            public int indexedLength;
            public String snippet;

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
                this.indexedLength = 0;
                this.content = WireFormatNano.EMPTY_BYTES;
                this.snippet = BuildConfig.VERSION_NAME;
                this.config = null;
                this.contentToIndex = WireFormatNano.EMPTY_BYTES;
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
                if (this.id != other.id || this.indexedLength != other.indexedLength || !Arrays.equals(this.content, other.content)) {
                    return false;
                }
                if (this.snippet == null) {
                    if (other.snippet != null) {
                        return false;
                    }
                } else if (!this.snippet.equals(other.snippet)) {
                    return false;
                }
                if (this.config == null) {
                    if (other.config != null) {
                        return false;
                    }
                } else if (!this.config.equals(other.config)) {
                    return false;
                }
                if (!Arrays.equals(this.contentToIndex, other.contentToIndex)) {
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
                int hashCode = (((((((((((((getClass().getName().hashCode() + 527) * 31) + this.id) * 31) + this.indexedLength) * 31) + Arrays.hashCode(this.content)) * 31) + (this.snippet == null ? 0 : this.snippet.hashCode())) * 31) + (this.config == null ? 0 : this.config.hashCode())) * 31) + Arrays.hashCode(this.contentToIndex)) * 31;
                if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                    i = this.unknownFieldData.hashCode();
                }
                return hashCode + i;
            }

            public void writeTo(CodedOutputByteBufferNano output) throws IOException {
                if (this.id != 0) {
                    output.writeUInt32(1, this.id);
                }
                if (this.indexedLength != 0) {
                    output.writeUInt32(2, this.indexedLength);
                }
                if (!Arrays.equals(this.content, WireFormatNano.EMPTY_BYTES)) {
                    output.writeBytes(3, this.content);
                }
                if (!this.snippet.equals(BuildConfig.VERSION_NAME)) {
                    output.writeString(4, this.snippet);
                }
                if (this.config != null) {
                    output.writeMessage(5, this.config);
                }
                if (!Arrays.equals(this.contentToIndex, WireFormatNano.EMPTY_BYTES)) {
                    output.writeBytes(6, this.contentToIndex);
                }
                super.writeTo(output);
            }

            protected int computeSerializedSize() {
                int size = super.computeSerializedSize();
                if (this.id != 0) {
                    size += CodedOutputByteBufferNano.computeUInt32Size(1, this.id);
                }
                if (this.indexedLength != 0) {
                    size += CodedOutputByteBufferNano.computeUInt32Size(2, this.indexedLength);
                }
                if (!Arrays.equals(this.content, WireFormatNano.EMPTY_BYTES)) {
                    size += CodedOutputByteBufferNano.computeBytesSize(3, this.content);
                }
                if (!this.snippet.equals(BuildConfig.VERSION_NAME)) {
                    size += CodedOutputByteBufferNano.computeStringSize(4, this.snippet);
                }
                if (this.config != null) {
                    size += CodedOutputByteBufferNano.computeMessageSize(5, this.config);
                }
                if (Arrays.equals(this.contentToIndex, WireFormatNano.EMPTY_BYTES)) {
                    return size;
                }
                return size + CodedOutputByteBufferNano.computeBytesSize(6, this.contentToIndex);
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
                            this.indexedLength = input.readUInt32();
                            continue;
                        case LogSource.ANDROID_CAMERA /*26*/:
                            this.content = input.readBytes();
                            continue;
                        case LogSource.NOVA /*34*/:
                            this.snippet = input.readString();
                            continue;
                        case LogSource.PHOTOS /*42*/:
                            if (this.config == null) {
                                this.config = new SectionConfig();
                            }
                            input.readMessage(this.config);
                            continue;
                        case LogSource.HANGOUT_LOG_REQUEST /*50*/:
                            this.contentToIndex = input.readBytes();
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

        public static Document[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new Document[0];
                    }
                }
            }
            return _emptyArray;
        }

        public Document() {
            clear();
        }

        public Document clear() {
            this.corpusId = 0;
            this.uri = BuildConfig.VERSION_NAME;
            this.score = 1;
            this.sections = Section.emptyArray();
            this.languages = Language.emptyArray();
            this.createdTimestampMs = 0;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof Document)) {
                return false;
            }
            Document other = (Document) o;
            if (this.corpusId != other.corpusId) {
                return false;
            }
            if (this.uri == null) {
                if (other.uri != null) {
                    return false;
                }
            } else if (!this.uri.equals(other.uri)) {
                return false;
            }
            if (this.score != other.score || !InternalNano.equals(this.sections, other.sections) || !InternalNano.equals(this.languages, other.languages) || this.createdTimestampMs != other.createdTimestampMs) {
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
            int hashCode = (((((((((((((getClass().getName().hashCode() + 527) * 31) + this.corpusId) * 31) + (this.uri == null ? 0 : this.uri.hashCode())) * 31) + this.score) * 31) + InternalNano.hashCode(this.sections)) * 31) + InternalNano.hashCode(this.languages)) * 31) + ((int) (this.createdTimestampMs ^ (this.createdTimestampMs >>> 32)))) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.corpusId != 0) {
                output.writeInt32(1, this.corpusId);
            }
            if (!this.uri.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(2, this.uri);
            }
            if (this.score != 1) {
                output.writeUInt32(3, this.score);
            }
            if (this.sections != null && this.sections.length > 0) {
                for (Section element : this.sections) {
                    if (element != null) {
                        output.writeMessage(4, element);
                    }
                }
            }
            if (this.languages != null && this.languages.length > 0) {
                for (Language element2 : this.languages) {
                    if (element2 != null) {
                        output.writeMessage(5, element2);
                    }
                }
            }
            if (this.createdTimestampMs != 0) {
                output.writeUInt64(6, this.createdTimestampMs);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.corpusId != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(1, this.corpusId);
            }
            if (!this.uri.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(2, this.uri);
            }
            if (this.score != 1) {
                size += CodedOutputByteBufferNano.computeUInt32Size(3, this.score);
            }
            if (this.sections != null && this.sections.length > 0) {
                for (Section element : this.sections) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(4, element);
                    }
                }
            }
            if (this.languages != null && this.languages.length > 0) {
                for (Language element2 : this.languages) {
                    if (element2 != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(5, element2);
                    }
                }
            }
            if (this.createdTimestampMs != 0) {
                return size + CodedOutputByteBufferNano.computeUInt64Size(6, this.createdTimestampMs);
            }
            return size;
        }

        public Document mergeFrom(CodedInputByteBufferNano input) throws IOException {
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
                        this.uri = input.readString();
                        continue;
                    case LogSource.LB_C /*24*/:
                        this.score = input.readUInt32();
                        continue;
                    case LogSource.NOVA /*34*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 34);
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
                    case LogSource.PHOTOS /*42*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 42);
                        if (this.languages == null) {
                            i = 0;
                        } else {
                            i = this.languages.length;
                        }
                        Language[] newArray2 = new Language[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.languages, 0, newArray2, 0, i);
                        }
                        while (i < newArray2.length - 1) {
                            newArray2[i] = new Language();
                            input.readMessage(newArray2[i]);
                            input.readTag();
                            i++;
                        }
                        newArray2[i] = new Language();
                        input.readMessage(newArray2[i]);
                        this.languages = newArray2;
                        continue;
                    case LogSource.BACKDROP /*48*/:
                        this.createdTimestampMs = input.readUInt64();
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

        public static Document parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (Document) MessageNano.mergeFrom(new Document(), data);
        }

        public static Document parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new Document().mergeFrom(input);
        }
    }

    public static final class SectionConfig extends ExtendableMessageNano<SectionConfig> {
        private static volatile SectionConfig[] _emptyArray;
        public int commonTermLegacyHitScore;
        public boolean indexPrefixes;
        public boolean indexed;
        public String name;
        public int omniboxSectionType;
        public int rfc822HostNameTermLegacyHitScore;
        public String semanticProperty;
        public String subsectionSeparator;
        public int tokenizer;
        public int universalSectionId;
        public int[] variantGenerators;
        public int weight;

        public interface OmniboxSectionType {
            public static final int OMNIBOX_NONE = 0;
            public static final int OMNIBOX_TITLE = 2;
            public static final int OMNIBOX_URL = 1;
        }

        public interface Tokenizer {
            public static final int TOKENIZER_HTML = 1;
            public static final int TOKENIZER_NONE = 4;
            public static final int TOKENIZER_RFC822_FORMATTED = 2;
            public static final int TOKENIZER_TEXT = 0;
            public static final int TOKENIZER_URL = 3;
        }

        public interface VariantGenerator {
            public static final int DEPRECATED_VARIANT_TRANSLIT = 1;
            public static final int VARIANT_ANNOTATION = 2;
            public static final int VARIANT_NICKNAME = 0;
        }

        public static SectionConfig[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new SectionConfig[0];
                    }
                }
            }
            return _emptyArray;
        }

        public SectionConfig() {
            clear();
        }

        public SectionConfig clear() {
            this.name = BuildConfig.VERSION_NAME;
            this.indexed = true;
            this.tokenizer = 0;
            this.weight = 1;
            this.indexPrefixes = false;
            this.subsectionSeparator = BuildConfig.VERSION_NAME;
            this.variantGenerators = WireFormatNano.EMPTY_INT_ARRAY;
            this.commonTermLegacyHitScore = 0;
            this.rfc822HostNameTermLegacyHitScore = 0;
            this.semanticProperty = BuildConfig.VERSION_NAME;
            this.universalSectionId = -1;
            this.omniboxSectionType = 0;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof SectionConfig)) {
                return false;
            }
            SectionConfig other = (SectionConfig) o;
            if (this.name == null) {
                if (other.name != null) {
                    return false;
                }
            } else if (!this.name.equals(other.name)) {
                return false;
            }
            if (this.indexed != other.indexed || this.tokenizer != other.tokenizer || this.weight != other.weight || this.indexPrefixes != other.indexPrefixes) {
                return false;
            }
            if (this.subsectionSeparator == null) {
                if (other.subsectionSeparator != null) {
                    return false;
                }
            } else if (!this.subsectionSeparator.equals(other.subsectionSeparator)) {
                return false;
            }
            if (!InternalNano.equals(this.variantGenerators, other.variantGenerators) || this.commonTermLegacyHitScore != other.commonTermLegacyHitScore || this.rfc822HostNameTermLegacyHitScore != other.rfc822HostNameTermLegacyHitScore) {
                return false;
            }
            if (this.semanticProperty == null) {
                if (other.semanticProperty != null) {
                    return false;
                }
            } else if (!this.semanticProperty.equals(other.semanticProperty)) {
                return false;
            }
            if (this.universalSectionId != other.universalSectionId || this.omniboxSectionType != other.omniboxSectionType) {
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
            int hashCode = (((getClass().getName().hashCode() + 527) * 31) + (this.name == null ? 0 : this.name.hashCode())) * 31;
            if (this.indexed) {
                i = 1231;
            } else {
                i = 1237;
            }
            i = (((((hashCode + i) * 31) + this.tokenizer) * 31) + this.weight) * 31;
            if (!this.indexPrefixes) {
                i2 = 1237;
            }
            i = (((((((((((((((i + i2) * 31) + (this.subsectionSeparator == null ? 0 : this.subsectionSeparator.hashCode())) * 31) + InternalNano.hashCode(this.variantGenerators)) * 31) + this.commonTermLegacyHitScore) * 31) + this.rfc822HostNameTermLegacyHitScore) * 31) + (this.semanticProperty == null ? 0 : this.semanticProperty.hashCode())) * 31) + this.universalSectionId) * 31) + this.omniboxSectionType) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i3 = this.unknownFieldData.hashCode();
            }
            return i + i3;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (!this.name.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(1, this.name);
            }
            if (!this.indexed) {
                output.writeBool(2, this.indexed);
            }
            if (this.tokenizer != 0) {
                output.writeInt32(3, this.tokenizer);
            }
            if (this.weight != 1) {
                output.writeUInt32(4, this.weight);
            }
            if (this.indexPrefixes) {
                output.writeBool(5, this.indexPrefixes);
            }
            if (!this.subsectionSeparator.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(6, this.subsectionSeparator);
            }
            if (this.variantGenerators != null && this.variantGenerators.length > 0) {
                for (int writeInt32 : this.variantGenerators) {
                    output.writeInt32(7, writeInt32);
                }
            }
            if (this.commonTermLegacyHitScore != 0) {
                output.writeInt32(8, this.commonTermLegacyHitScore);
            }
            if (this.rfc822HostNameTermLegacyHitScore != 0) {
                output.writeInt32(9, this.rfc822HostNameTermLegacyHitScore);
            }
            if (!this.semanticProperty.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(11, this.semanticProperty);
            }
            if (this.universalSectionId != -1) {
                output.writeInt32(12, this.universalSectionId);
            }
            if (this.omniboxSectionType != 0) {
                output.writeInt32(13, this.omniboxSectionType);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (!this.name.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.name);
            }
            if (!this.indexed) {
                size += CodedOutputByteBufferNano.computeBoolSize(2, this.indexed);
            }
            if (this.tokenizer != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(3, this.tokenizer);
            }
            if (this.weight != 1) {
                size += CodedOutputByteBufferNano.computeUInt32Size(4, this.weight);
            }
            if (this.indexPrefixes) {
                size += CodedOutputByteBufferNano.computeBoolSize(5, this.indexPrefixes);
            }
            if (!this.subsectionSeparator.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(6, this.subsectionSeparator);
            }
            if (this.variantGenerators != null && this.variantGenerators.length > 0) {
                int dataSize = 0;
                for (int element : this.variantGenerators) {
                    dataSize += CodedOutputByteBufferNano.computeInt32SizeNoTag(element);
                }
                size = (size + dataSize) + (this.variantGenerators.length * 1);
            }
            if (this.commonTermLegacyHitScore != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(8, this.commonTermLegacyHitScore);
            }
            if (this.rfc822HostNameTermLegacyHitScore != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(9, this.rfc822HostNameTermLegacyHitScore);
            }
            if (!this.semanticProperty.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(11, this.semanticProperty);
            }
            if (this.universalSectionId != -1) {
                size += CodedOutputByteBufferNano.computeInt32Size(12, this.universalSectionId);
            }
            if (this.omniboxSectionType != 0) {
                return size + CodedOutputByteBufferNano.computeInt32Size(13, this.omniboxSectionType);
            }
            return size;
        }

        public SectionConfig mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                int value;
                int i;
                int[] newArray;
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        this.name = input.readString();
                        continue;
                    case LogSource.GMS_CORE_PEOPLE /*16*/:
                        this.indexed = input.readBool();
                        continue;
                    case LogSource.LB_C /*24*/:
                        value = input.readInt32();
                        switch (value) {
                            case Action.UNKNOWN /*0*/:
                            case TimeSelection.Type.TEMPORARY /*1*/:
                            case TimeSelection.Type.INDEFINITELY /*2*/:
                            case TimeSelection.Type.CUSTOM /*3*/:
                            case OvenFreshResult.NO_ACCOUNTS /*4*/:
                                this.tokenizer = value;
                                break;
                            default:
                                continue;
                        }
                    case LogSource.SOCIAL /*32*/:
                        this.weight = input.readUInt32();
                        continue;
                    case LogSource.COPRESENCE /*40*/:
                        this.indexPrefixes = input.readBool();
                        continue;
                    case LogSource.HANGOUT_LOG_REQUEST /*50*/:
                        this.subsectionSeparator = input.readString();
                        continue;
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
                            i = this.variantGenerators == null ? 0 : this.variantGenerators.length;
                            if (i != 0 || validCount != validValues.length) {
                                newArray = new int[(i + validCount)];
                                if (i != 0) {
                                    System.arraycopy(this.variantGenerators, 0, newArray, 0, i);
                                }
                                System.arraycopy(validValues, 0, newArray, i, validCount);
                                this.variantGenerators = newArray;
                                break;
                            }
                            this.variantGenerators = validValues;
                            break;
                        }
                        continue;
                    case LogSource.SLIDES /*58*/:
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
                            if (this.variantGenerators == null) {
                                i = 0;
                            } else {
                                i = this.variantGenerators.length;
                            }
                            newArray = new int[(i + arrayLength)];
                            if (i != 0) {
                                System.arraycopy(this.variantGenerators, 0, newArray, 0, i);
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
                            this.variantGenerators = newArray;
                        }
                        input.popLimit(limit);
                        continue;
                    case LogSource.KIDS_COMMUNICATOR /*64*/:
                        this.commonTermLegacyHitScore = input.readInt32();
                        continue;
                    case LogSource.JUSTSPEAK /*72*/:
                        this.rfc822HostNameTermLegacyHitScore = input.readInt32();
                        continue;
                    case LogSource.TRON_COUNTERS /*90*/:
                        this.semanticProperty = input.readString();
                        continue;
                    case LogSource.GCM_COUNTERS /*96*/:
                        this.universalSectionId = input.readInt32();
                        continue;
                    case LogSource.MOBILESDK_CLIENT /*104*/:
                        value = input.readInt32();
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

        public static SectionConfig parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (SectionConfig) MessageNano.mergeFrom(new SectionConfig(), data);
        }

        public static SectionConfig parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new SectionConfig().mergeFrom(input);
        }
    }
}
