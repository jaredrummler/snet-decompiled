package com.google.protobuf;

import com.google.android.gms.lint.BuildConfig;
import com.google.android.gms.lockbox.appusage.AppUsage.AppUsageEvent.Importance;
import com.google.android.gms.people.PeopleConstants.PeopleColumnBitmask;
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

public interface DescriptorProtos {

    public static final class DescriptorProto extends ExtendableMessageNano<DescriptorProto> {
        private static volatile DescriptorProto[] _emptyArray;
        public EnumDescriptorProto[] enumType;
        public FieldDescriptorProto[] extension;
        public ExtensionRange[] extensionRange;
        public FieldDescriptorProto[] field;
        public String name;
        public DescriptorProto[] nestedType;
        public OneofDescriptorProto[] oneofDecl;
        public MessageOptions options;

        public static final class ExtensionRange extends ExtendableMessageNano<ExtensionRange> {
            private static volatile ExtensionRange[] _emptyArray;
            public int end;
            public int start;

            public static ExtensionRange[] emptyArray() {
                if (_emptyArray == null) {
                    synchronized (InternalNano.LAZY_INIT_LOCK) {
                        if (_emptyArray == null) {
                            _emptyArray = new ExtensionRange[0];
                        }
                    }
                }
                return _emptyArray;
            }

            public ExtensionRange() {
                clear();
            }

            public ExtensionRange clear() {
                this.start = 0;
                this.end = 0;
                this.unknownFieldData = null;
                this.cachedSize = -1;
                return this;
            }

            public boolean equals(Object o) {
                if (o == this) {
                    return true;
                }
                if (!(o instanceof ExtensionRange)) {
                    return false;
                }
                ExtensionRange other = (ExtensionRange) o;
                if (this.start != other.start || this.end != other.end) {
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
                int hashCode = (((((getClass().getName().hashCode() + 527) * 31) + this.start) * 31) + this.end) * 31;
                int hashCode2 = (this.unknownFieldData == null || this.unknownFieldData.isEmpty()) ? 0 : this.unknownFieldData.hashCode();
                return hashCode + hashCode2;
            }

            public void writeTo(CodedOutputByteBufferNano output) throws IOException {
                if (this.start != 0) {
                    output.writeInt32(1, this.start);
                }
                if (this.end != 0) {
                    output.writeInt32(2, this.end);
                }
                super.writeTo(output);
            }

            protected int computeSerializedSize() {
                int size = super.computeSerializedSize();
                if (this.start != 0) {
                    size += CodedOutputByteBufferNano.computeInt32Size(1, this.start);
                }
                if (this.end != 0) {
                    return size + CodedOutputByteBufferNano.computeInt32Size(2, this.end);
                }
                return size;
            }

            public ExtensionRange mergeFrom(CodedInputByteBufferNano input) throws IOException {
                while (true) {
                    int tag = input.readTag();
                    switch (tag) {
                        case Action.UNKNOWN /*0*/:
                            break;
                        case Type.TAP_GET_LINK /*8*/:
                            this.start = input.readInt32();
                            continue;
                        case LogSource.GMS_CORE_PEOPLE /*16*/:
                            this.end = input.readInt32();
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

            public static ExtensionRange parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
                return (ExtensionRange) MessageNano.mergeFrom(new ExtensionRange(), data);
            }

            public static ExtensionRange parseFrom(CodedInputByteBufferNano input) throws IOException {
                return new ExtensionRange().mergeFrom(input);
            }
        }

        public static DescriptorProto[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new DescriptorProto[0];
                    }
                }
            }
            return _emptyArray;
        }

        public DescriptorProto() {
            clear();
        }

        public DescriptorProto clear() {
            this.name = BuildConfig.VERSION_NAME;
            this.field = FieldDescriptorProto.emptyArray();
            this.extension = FieldDescriptorProto.emptyArray();
            this.nestedType = emptyArray();
            this.enumType = EnumDescriptorProto.emptyArray();
            this.extensionRange = ExtensionRange.emptyArray();
            this.oneofDecl = OneofDescriptorProto.emptyArray();
            this.options = null;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof DescriptorProto)) {
                return false;
            }
            DescriptorProto other = (DescriptorProto) o;
            if (this.name == null) {
                if (other.name != null) {
                    return false;
                }
            } else if (!this.name.equals(other.name)) {
                return false;
            }
            if (!InternalNano.equals(this.field, other.field) || !InternalNano.equals(this.extension, other.extension) || !InternalNano.equals(this.nestedType, other.nestedType) || !InternalNano.equals(this.enumType, other.enumType) || !InternalNano.equals(this.extensionRange, other.extensionRange) || !InternalNano.equals(this.oneofDecl, other.oneofDecl)) {
                return false;
            }
            if (this.options == null) {
                if (other.options != null) {
                    return false;
                }
            } else if (!this.options.equals(other.options)) {
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
            if (this.name == null) {
                i = 0;
            } else {
                i = this.name.hashCode();
            }
            i = (((((((((((((((hashCode + i) * 31) + InternalNano.hashCode(this.field)) * 31) + InternalNano.hashCode(this.extension)) * 31) + InternalNano.hashCode(this.nestedType)) * 31) + InternalNano.hashCode(this.enumType)) * 31) + InternalNano.hashCode(this.extensionRange)) * 31) + InternalNano.hashCode(this.oneofDecl)) * 31) + (this.options == null ? 0 : this.options.hashCode())) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i2 = this.unknownFieldData.hashCode();
            }
            return i + i2;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (!this.name.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(1, this.name);
            }
            if (this.field != null && this.field.length > 0) {
                for (FieldDescriptorProto element : this.field) {
                    if (element != null) {
                        output.writeMessage(2, element);
                    }
                }
            }
            if (this.nestedType != null && this.nestedType.length > 0) {
                for (DescriptorProto element2 : this.nestedType) {
                    if (element2 != null) {
                        output.writeMessage(3, element2);
                    }
                }
            }
            if (this.enumType != null && this.enumType.length > 0) {
                for (EnumDescriptorProto element3 : this.enumType) {
                    if (element3 != null) {
                        output.writeMessage(4, element3);
                    }
                }
            }
            if (this.extensionRange != null && this.extensionRange.length > 0) {
                for (ExtensionRange element4 : this.extensionRange) {
                    if (element4 != null) {
                        output.writeMessage(5, element4);
                    }
                }
            }
            if (this.extension != null && this.extension.length > 0) {
                for (FieldDescriptorProto element5 : this.extension) {
                    if (element5 != null) {
                        output.writeMessage(6, element5);
                    }
                }
            }
            if (this.options != null) {
                output.writeMessage(7, this.options);
            }
            if (this.oneofDecl != null && this.oneofDecl.length > 0) {
                for (OneofDescriptorProto element6 : this.oneofDecl) {
                    if (element6 != null) {
                        output.writeMessage(8, element6);
                    }
                }
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (!this.name.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.name);
            }
            if (this.field != null && this.field.length > 0) {
                for (FieldDescriptorProto element : this.field) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(2, element);
                    }
                }
            }
            if (this.nestedType != null && this.nestedType.length > 0) {
                for (DescriptorProto element2 : this.nestedType) {
                    if (element2 != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(3, element2);
                    }
                }
            }
            if (this.enumType != null && this.enumType.length > 0) {
                for (EnumDescriptorProto element3 : this.enumType) {
                    if (element3 != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(4, element3);
                    }
                }
            }
            if (this.extensionRange != null && this.extensionRange.length > 0) {
                for (ExtensionRange element4 : this.extensionRange) {
                    if (element4 != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(5, element4);
                    }
                }
            }
            if (this.extension != null && this.extension.length > 0) {
                for (FieldDescriptorProto element5 : this.extension) {
                    if (element5 != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(6, element5);
                    }
                }
            }
            if (this.options != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(7, this.options);
            }
            if (this.oneofDecl != null && this.oneofDecl.length > 0) {
                for (OneofDescriptorProto element6 : this.oneofDecl) {
                    if (element6 != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(8, element6);
                    }
                }
            }
            return size;
        }

        public DescriptorProto mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                int arrayLength;
                int i;
                FieldDescriptorProto[] newArray;
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        this.name = input.readString();
                        continue;
                    case LogSource.GOOGLE_ANALYTICS /*18*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 18);
                        if (this.field == null) {
                            i = 0;
                        } else {
                            i = this.field.length;
                        }
                        newArray = new FieldDescriptorProto[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.field, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new FieldDescriptorProto();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i++;
                        }
                        newArray[i] = new FieldDescriptorProto();
                        input.readMessage(newArray[i]);
                        this.field = newArray;
                        continue;
                    case LogSource.ANDROID_CAMERA /*26*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 26);
                        if (this.nestedType == null) {
                            i = 0;
                        } else {
                            i = this.nestedType.length;
                        }
                        DescriptorProto[] newArray2 = new DescriptorProto[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.nestedType, 0, newArray2, 0, i);
                        }
                        while (i < newArray2.length - 1) {
                            newArray2[i] = new DescriptorProto();
                            input.readMessage(newArray2[i]);
                            input.readTag();
                            i++;
                        }
                        newArray2[i] = new DescriptorProto();
                        input.readMessage(newArray2[i]);
                        this.nestedType = newArray2;
                        continue;
                    case LogSource.NOVA /*34*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 34);
                        if (this.enumType == null) {
                            i = 0;
                        } else {
                            i = this.enumType.length;
                        }
                        EnumDescriptorProto[] newArray3 = new EnumDescriptorProto[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.enumType, 0, newArray3, 0, i);
                        }
                        while (i < newArray3.length - 1) {
                            newArray3[i] = new EnumDescriptorProto();
                            input.readMessage(newArray3[i]);
                            input.readTag();
                            i++;
                        }
                        newArray3[i] = new EnumDescriptorProto();
                        input.readMessage(newArray3[i]);
                        this.enumType = newArray3;
                        continue;
                    case LogSource.PHOTOS /*42*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 42);
                        if (this.extensionRange == null) {
                            i = 0;
                        } else {
                            i = this.extensionRange.length;
                        }
                        ExtensionRange[] newArray4 = new ExtensionRange[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.extensionRange, 0, newArray4, 0, i);
                        }
                        while (i < newArray4.length - 1) {
                            newArray4[i] = new ExtensionRange();
                            input.readMessage(newArray4[i]);
                            input.readTag();
                            i++;
                        }
                        newArray4[i] = new ExtensionRange();
                        input.readMessage(newArray4[i]);
                        this.extensionRange = newArray4;
                        continue;
                    case LogSource.HANGOUT_LOG_REQUEST /*50*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 50);
                        if (this.extension == null) {
                            i = 0;
                        } else {
                            i = this.extension.length;
                        }
                        newArray = new FieldDescriptorProto[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.extension, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new FieldDescriptorProto();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i++;
                        }
                        newArray[i] = new FieldDescriptorProto();
                        input.readMessage(newArray[i]);
                        this.extension = newArray;
                        continue;
                    case LogSource.SLIDES /*58*/:
                        if (this.options == null) {
                            this.options = new MessageOptions();
                        }
                        input.readMessage(this.options);
                        continue;
                    case LogSource.WIFI_ASSISTANT /*66*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 66);
                        if (this.oneofDecl == null) {
                            i = 0;
                        } else {
                            i = this.oneofDecl.length;
                        }
                        OneofDescriptorProto[] newArray5 = new OneofDescriptorProto[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.oneofDecl, 0, newArray5, 0, i);
                        }
                        while (i < newArray5.length - 1) {
                            newArray5[i] = new OneofDescriptorProto();
                            input.readMessage(newArray5[i]);
                            input.readTag();
                            i++;
                        }
                        newArray5[i] = new OneofDescriptorProto();
                        input.readMessage(newArray5[i]);
                        this.oneofDecl = newArray5;
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

        public static DescriptorProto parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (DescriptorProto) MessageNano.mergeFrom(new DescriptorProto(), data);
        }

        public static DescriptorProto parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new DescriptorProto().mergeFrom(input);
        }
    }

    public static final class EnumDescriptorProto extends ExtendableMessageNano<EnumDescriptorProto> {
        private static volatile EnumDescriptorProto[] _emptyArray;
        public String name;
        public EnumOptions options;
        public EnumValueDescriptorProto[] value;

        public static EnumDescriptorProto[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new EnumDescriptorProto[0];
                    }
                }
            }
            return _emptyArray;
        }

        public EnumDescriptorProto() {
            clear();
        }

        public EnumDescriptorProto clear() {
            this.name = BuildConfig.VERSION_NAME;
            this.value = EnumValueDescriptorProto.emptyArray();
            this.options = null;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof EnumDescriptorProto)) {
                return false;
            }
            EnumDescriptorProto other = (EnumDescriptorProto) o;
            if (this.name == null) {
                if (other.name != null) {
                    return false;
                }
            } else if (!this.name.equals(other.name)) {
                return false;
            }
            if (!InternalNano.equals(this.value, other.value)) {
                return false;
            }
            if (this.options == null) {
                if (other.options != null) {
                    return false;
                }
            } else if (!this.options.equals(other.options)) {
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
            int hashCode = (((((((getClass().getName().hashCode() + 527) * 31) + (this.name == null ? 0 : this.name.hashCode())) * 31) + InternalNano.hashCode(this.value)) * 31) + (this.options == null ? 0 : this.options.hashCode())) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (!this.name.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(1, this.name);
            }
            if (this.value != null && this.value.length > 0) {
                for (EnumValueDescriptorProto element : this.value) {
                    if (element != null) {
                        output.writeMessage(2, element);
                    }
                }
            }
            if (this.options != null) {
                output.writeMessage(3, this.options);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (!this.name.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.name);
            }
            if (this.value != null && this.value.length > 0) {
                for (EnumValueDescriptorProto element : this.value) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(2, element);
                    }
                }
            }
            if (this.options != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(3, this.options);
            }
            return size;
        }

        public EnumDescriptorProto mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        this.name = input.readString();
                        continue;
                    case LogSource.GOOGLE_ANALYTICS /*18*/:
                        int i;
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 18);
                        if (this.value == null) {
                            i = 0;
                        } else {
                            i = this.value.length;
                        }
                        EnumValueDescriptorProto[] newArray = new EnumValueDescriptorProto[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.value, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new EnumValueDescriptorProto();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i++;
                        }
                        newArray[i] = new EnumValueDescriptorProto();
                        input.readMessage(newArray[i]);
                        this.value = newArray;
                        continue;
                    case LogSource.ANDROID_CAMERA /*26*/:
                        if (this.options == null) {
                            this.options = new EnumOptions();
                        }
                        input.readMessage(this.options);
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

        public static EnumDescriptorProto parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (EnumDescriptorProto) MessageNano.mergeFrom(new EnumDescriptorProto(), data);
        }

        public static EnumDescriptorProto parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new EnumDescriptorProto().mergeFrom(input);
        }
    }

    public static final class EnumOptions extends ExtendableMessageNano<EnumOptions> {
        private static volatile EnumOptions[] _emptyArray;
        public boolean allowAlias;
        public boolean deprecated;
        public String proto1Name;
        public UninterpretedOption[] uninterpretedOption;

        public static EnumOptions[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new EnumOptions[0];
                    }
                }
            }
            return _emptyArray;
        }

        public EnumOptions() {
            clear();
        }

        public EnumOptions clear() {
            this.proto1Name = BuildConfig.VERSION_NAME;
            this.allowAlias = false;
            this.deprecated = false;
            this.uninterpretedOption = UninterpretedOption.emptyArray();
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof EnumOptions)) {
                return false;
            }
            EnumOptions other = (EnumOptions) o;
            if (this.proto1Name == null) {
                if (other.proto1Name != null) {
                    return false;
                }
            } else if (!this.proto1Name.equals(other.proto1Name)) {
                return false;
            }
            if (this.allowAlias != other.allowAlias || this.deprecated != other.deprecated || !InternalNano.equals(this.uninterpretedOption, other.uninterpretedOption)) {
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
            int hashCode = (((getClass().getName().hashCode() + 527) * 31) + (this.proto1Name == null ? 0 : this.proto1Name.hashCode())) * 31;
            if (this.allowAlias) {
                i = 1231;
            } else {
                i = 1237;
            }
            i = (hashCode + i) * 31;
            if (!this.deprecated) {
                i2 = 1237;
            }
            i = (((i + i2) * 31) + InternalNano.hashCode(this.uninterpretedOption)) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i3 = this.unknownFieldData.hashCode();
            }
            return i + i3;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (!this.proto1Name.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(1, this.proto1Name);
            }
            if (this.allowAlias) {
                output.writeBool(2, this.allowAlias);
            }
            if (this.deprecated) {
                output.writeBool(3, this.deprecated);
            }
            if (this.uninterpretedOption != null && this.uninterpretedOption.length > 0) {
                for (UninterpretedOption element : this.uninterpretedOption) {
                    if (element != null) {
                        output.writeMessage(999, element);
                    }
                }
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (!this.proto1Name.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.proto1Name);
            }
            if (this.allowAlias) {
                size += CodedOutputByteBufferNano.computeBoolSize(2, this.allowAlias);
            }
            if (this.deprecated) {
                size += CodedOutputByteBufferNano.computeBoolSize(3, this.deprecated);
            }
            if (this.uninterpretedOption != null && this.uninterpretedOption.length > 0) {
                for (UninterpretedOption element : this.uninterpretedOption) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(999, element);
                    }
                }
            }
            return size;
        }

        public EnumOptions mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        this.proto1Name = input.readString();
                        continue;
                    case LogSource.GMS_CORE_PEOPLE /*16*/:
                        this.allowAlias = input.readBool();
                        continue;
                    case LogSource.LB_C /*24*/:
                        this.deprecated = input.readBool();
                        continue;
                    case 7994:
                        int i;
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 7994);
                        if (this.uninterpretedOption == null) {
                            i = 0;
                        } else {
                            i = this.uninterpretedOption.length;
                        }
                        UninterpretedOption[] newArray = new UninterpretedOption[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.uninterpretedOption, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new UninterpretedOption();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i++;
                        }
                        newArray[i] = new UninterpretedOption();
                        input.readMessage(newArray[i]);
                        this.uninterpretedOption = newArray;
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

        public static EnumOptions parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (EnumOptions) MessageNano.mergeFrom(new EnumOptions(), data);
        }

        public static EnumOptions parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new EnumOptions().mergeFrom(input);
        }
    }

    public static final class EnumValueDescriptorProto extends ExtendableMessageNano<EnumValueDescriptorProto> {
        private static volatile EnumValueDescriptorProto[] _emptyArray;
        public String name;
        public int number;
        public EnumValueOptions options;

        public static EnumValueDescriptorProto[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new EnumValueDescriptorProto[0];
                    }
                }
            }
            return _emptyArray;
        }

        public EnumValueDescriptorProto() {
            clear();
        }

        public EnumValueDescriptorProto clear() {
            this.name = BuildConfig.VERSION_NAME;
            this.number = 0;
            this.options = null;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof EnumValueDescriptorProto)) {
                return false;
            }
            EnumValueDescriptorProto other = (EnumValueDescriptorProto) o;
            if (this.name == null) {
                if (other.name != null) {
                    return false;
                }
            } else if (!this.name.equals(other.name)) {
                return false;
            }
            if (this.number != other.number) {
                return false;
            }
            if (this.options == null) {
                if (other.options != null) {
                    return false;
                }
            } else if (!this.options.equals(other.options)) {
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
            int hashCode = (((((((getClass().getName().hashCode() + 527) * 31) + (this.name == null ? 0 : this.name.hashCode())) * 31) + this.number) * 31) + (this.options == null ? 0 : this.options.hashCode())) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (!this.name.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(1, this.name);
            }
            if (this.number != 0) {
                output.writeInt32(2, this.number);
            }
            if (this.options != null) {
                output.writeMessage(3, this.options);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (!this.name.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.name);
            }
            if (this.number != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(2, this.number);
            }
            if (this.options != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(3, this.options);
            }
            return size;
        }

        public EnumValueDescriptorProto mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        this.name = input.readString();
                        continue;
                    case LogSource.GMS_CORE_PEOPLE /*16*/:
                        this.number = input.readInt32();
                        continue;
                    case LogSource.ANDROID_CAMERA /*26*/:
                        if (this.options == null) {
                            this.options = new EnumValueOptions();
                        }
                        input.readMessage(this.options);
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

        public static EnumValueDescriptorProto parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (EnumValueDescriptorProto) MessageNano.mergeFrom(new EnumValueDescriptorProto(), data);
        }

        public static EnumValueDescriptorProto parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new EnumValueDescriptorProto().mergeFrom(input);
        }
    }

    public static final class EnumValueOptions extends ExtendableMessageNano<EnumValueOptions> {
        private static volatile EnumValueOptions[] _emptyArray;
        public boolean deprecated;
        public UninterpretedOption[] uninterpretedOption;

        public static EnumValueOptions[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new EnumValueOptions[0];
                    }
                }
            }
            return _emptyArray;
        }

        public EnumValueOptions() {
            clear();
        }

        public EnumValueOptions clear() {
            this.deprecated = false;
            this.uninterpretedOption = UninterpretedOption.emptyArray();
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof EnumValueOptions)) {
                return false;
            }
            EnumValueOptions other = (EnumValueOptions) o;
            if (this.deprecated != other.deprecated || !InternalNano.equals(this.uninterpretedOption, other.uninterpretedOption)) {
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
            int hashCode = (((((getClass().getName().hashCode() + 527) * 31) + (this.deprecated ? 1231 : 1237)) * 31) + InternalNano.hashCode(this.uninterpretedOption)) * 31;
            int hashCode2 = (this.unknownFieldData == null || this.unknownFieldData.isEmpty()) ? 0 : this.unknownFieldData.hashCode();
            return hashCode + hashCode2;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.deprecated) {
                output.writeBool(1, this.deprecated);
            }
            if (this.uninterpretedOption != null && this.uninterpretedOption.length > 0) {
                for (UninterpretedOption element : this.uninterpretedOption) {
                    if (element != null) {
                        output.writeMessage(999, element);
                    }
                }
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.deprecated) {
                size += CodedOutputByteBufferNano.computeBoolSize(1, this.deprecated);
            }
            if (this.uninterpretedOption != null && this.uninterpretedOption.length > 0) {
                for (UninterpretedOption element : this.uninterpretedOption) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(999, element);
                    }
                }
            }
            return size;
        }

        public EnumValueOptions mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_GET_LINK /*8*/:
                        this.deprecated = input.readBool();
                        continue;
                    case 7994:
                        int i;
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 7994);
                        if (this.uninterpretedOption == null) {
                            i = 0;
                        } else {
                            i = this.uninterpretedOption.length;
                        }
                        UninterpretedOption[] newArray = new UninterpretedOption[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.uninterpretedOption, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new UninterpretedOption();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i++;
                        }
                        newArray[i] = new UninterpretedOption();
                        input.readMessage(newArray[i]);
                        this.uninterpretedOption = newArray;
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

        public static EnumValueOptions parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (EnumValueOptions) MessageNano.mergeFrom(new EnumValueOptions(), data);
        }

        public static EnumValueOptions parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new EnumValueOptions().mergeFrom(input);
        }
    }

    public static final class FieldDescriptorProto extends ExtendableMessageNano<FieldDescriptorProto> {
        private static volatile FieldDescriptorProto[] _emptyArray;
        public String defaultValue;
        public String extendee;
        public int label;
        public String name;
        public int number;
        public int oneofIndex;
        public FieldOptions options;
        public int type;
        public String typeName;

        public interface Label {
            public static final int LABEL_OPTIONAL = 1;
            public static final int LABEL_REPEATED = 3;
            public static final int LABEL_REQUIRED = 2;
        }

        public interface Type {
            public static final int TYPE_BOOL = 8;
            public static final int TYPE_BYTES = 12;
            public static final int TYPE_DOUBLE = 1;
            public static final int TYPE_ENUM = 14;
            public static final int TYPE_FIXED32 = 7;
            public static final int TYPE_FIXED64 = 6;
            public static final int TYPE_FLOAT = 2;
            public static final int TYPE_GROUP = 10;
            public static final int TYPE_INT32 = 5;
            public static final int TYPE_INT64 = 3;
            public static final int TYPE_MESSAGE = 11;
            public static final int TYPE_SFIXED32 = 15;
            public static final int TYPE_SFIXED64 = 16;
            public static final int TYPE_SINT32 = 17;
            public static final int TYPE_SINT64 = 18;
            public static final int TYPE_STRING = 9;
            public static final int TYPE_UINT32 = 13;
            public static final int TYPE_UINT64 = 4;
        }

        public static FieldDescriptorProto[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new FieldDescriptorProto[0];
                    }
                }
            }
            return _emptyArray;
        }

        public FieldDescriptorProto() {
            clear();
        }

        public FieldDescriptorProto clear() {
            this.name = BuildConfig.VERSION_NAME;
            this.number = 0;
            this.label = 1;
            this.type = 1;
            this.typeName = BuildConfig.VERSION_NAME;
            this.extendee = BuildConfig.VERSION_NAME;
            this.defaultValue = BuildConfig.VERSION_NAME;
            this.oneofIndex = 0;
            this.options = null;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof FieldDescriptorProto)) {
                return false;
            }
            FieldDescriptorProto other = (FieldDescriptorProto) o;
            if (this.name == null) {
                if (other.name != null) {
                    return false;
                }
            } else if (!this.name.equals(other.name)) {
                return false;
            }
            if (this.number != other.number || this.label != other.label || this.type != other.type) {
                return false;
            }
            if (this.typeName == null) {
                if (other.typeName != null) {
                    return false;
                }
            } else if (!this.typeName.equals(other.typeName)) {
                return false;
            }
            if (this.extendee == null) {
                if (other.extendee != null) {
                    return false;
                }
            } else if (!this.extendee.equals(other.extendee)) {
                return false;
            }
            if (this.defaultValue == null) {
                if (other.defaultValue != null) {
                    return false;
                }
            } else if (!this.defaultValue.equals(other.defaultValue)) {
                return false;
            }
            if (this.oneofIndex != other.oneofIndex) {
                return false;
            }
            if (this.options == null) {
                if (other.options != null) {
                    return false;
                }
            } else if (!this.options.equals(other.options)) {
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
            int hashCode = (((((((((((((((((((getClass().getName().hashCode() + 527) * 31) + (this.name == null ? 0 : this.name.hashCode())) * 31) + this.number) * 31) + this.label) * 31) + this.type) * 31) + (this.typeName == null ? 0 : this.typeName.hashCode())) * 31) + (this.extendee == null ? 0 : this.extendee.hashCode())) * 31) + (this.defaultValue == null ? 0 : this.defaultValue.hashCode())) * 31) + this.oneofIndex) * 31) + (this.options == null ? 0 : this.options.hashCode())) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (!this.name.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(1, this.name);
            }
            if (!this.extendee.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(2, this.extendee);
            }
            if (this.number != 0) {
                output.writeInt32(3, this.number);
            }
            if (this.label != 1) {
                output.writeInt32(4, this.label);
            }
            if (this.type != 1) {
                output.writeInt32(5, this.type);
            }
            if (!this.typeName.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(6, this.typeName);
            }
            if (!this.defaultValue.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(7, this.defaultValue);
            }
            if (this.options != null) {
                output.writeMessage(8, this.options);
            }
            if (this.oneofIndex != 0) {
                output.writeInt32(9, this.oneofIndex);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (!this.name.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.name);
            }
            if (!this.extendee.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(2, this.extendee);
            }
            if (this.number != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(3, this.number);
            }
            if (this.label != 1) {
                size += CodedOutputByteBufferNano.computeInt32Size(4, this.label);
            }
            if (this.type != 1) {
                size += CodedOutputByteBufferNano.computeInt32Size(5, this.type);
            }
            if (!this.typeName.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(6, this.typeName);
            }
            if (!this.defaultValue.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(7, this.defaultValue);
            }
            if (this.options != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(8, this.options);
            }
            if (this.oneofIndex != 0) {
                return size + CodedOutputByteBufferNano.computeInt32Size(9, this.oneofIndex);
            }
            return size;
        }

        public FieldDescriptorProto mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                int value;
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case com.google.wireless.android.play.playlog.proto.UserLocation.LocationSharingEvent.Type.TAP_ABOUT /*10*/:
                        this.name = input.readString();
                        continue;
                    case LogSource.GOOGLE_ANALYTICS /*18*/:
                        this.extendee = input.readString();
                        continue;
                    case LogSource.LB_C /*24*/:
                        this.number = input.readInt32();
                        continue;
                    case LogSource.SOCIAL /*32*/:
                        value = input.readInt32();
                        switch (value) {
                            case com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection.Type.TEMPORARY /*1*/:
                            case com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection.Type.INDEFINITELY /*2*/:
                            case com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection.Type.CUSTOM /*3*/:
                                this.label = value;
                                break;
                            default:
                                continue;
                        }
                    case LogSource.COPRESENCE /*40*/:
                        value = input.readInt32();
                        switch (value) {
                            case com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection.Type.TEMPORARY /*1*/:
                            case com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection.Type.INDEFINITELY /*2*/:
                            case com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection.Type.CUSTOM /*3*/:
                            case OvenFreshResult.NO_ACCOUNTS /*4*/:
                            case com.google.wireless.android.play.playlog.proto.UserLocation.LocationSharingEvent.Type.ADD_NEW_SHARES /*5*/:
                            case com.google.wireless.android.play.playlog.proto.UserLocation.LocationSharingEvent.Type.REMOVE_SHARE /*6*/:
                            case com.google.wireless.android.play.playlog.proto.UserLocation.LocationSharingEvent.Type.RESET_TIME /*7*/:
                            case com.google.wireless.android.play.playlog.proto.UserLocation.LocationSharingEvent.Type.TAP_GET_LINK /*8*/:
                            case com.google.wireless.android.play.playlog.proto.UserLocation.LocationSharingEvent.Type.CREATE_LINK /*9*/:
                            case com.google.wireless.android.play.playlog.proto.UserLocation.LocationSharingEvent.Type.TAP_ABOUT /*10*/:
                            case com.google.wireless.android.play.playlog.proto.UserLocation.LocationSharingEvent.Type.TAP_LEARN_MORE /*11*/:
                            case com.google.wireless.android.play.playlog.proto.UserLocation.LocationSharingEvent.Type.SWITCH_ACCOUNT /*12*/:
                            case com.google.wireless.android.play.playlog.proto.UserLocation.LocationSharingEvent.Type.DISPLAY_ERROR /*13*/:
                            case com.google.wireless.android.play.playlog.proto.UserLocation.LocationSharingEvent.Type.LAUNCH_SETTINGS /*14*/:
                            case com.google.wireless.android.play.playlog.proto.UserLocation.LocationSharingEvent.Type.OVEN_FRESH /*15*/:
                            case LogSource.GMS_CORE_PEOPLE /*16*/:
                            case LogSource.LE /*17*/:
                            case LogSource.GOOGLE_ANALYTICS /*18*/:
                                this.type = value;
                                break;
                            default:
                                continue;
                        }
                    case LogSource.HANGOUT_LOG_REQUEST /*50*/:
                        this.typeName = input.readString();
                        continue;
                    case LogSource.SLIDES /*58*/:
                        this.defaultValue = input.readString();
                        continue;
                    case LogSource.WIFI_ASSISTANT /*66*/:
                        if (this.options == null) {
                            this.options = new FieldOptions();
                        }
                        input.readMessage(this.options);
                        continue;
                    case LogSource.JUSTSPEAK /*72*/:
                        this.oneofIndex = input.readInt32();
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

        public static FieldDescriptorProto parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (FieldDescriptorProto) MessageNano.mergeFrom(new FieldDescriptorProto(), data);
        }

        public static FieldDescriptorProto parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new FieldDescriptorProto().mergeFrom(input);
        }
    }

    public static final class FieldOptions extends ExtendableMessageNano<FieldOptions> {
        private static volatile FieldOptions[] _emptyArray;
        public int ctype;
        public boolean deprecated;
        public boolean deprecatedRawMessage;
        public int jstype;
        public int jtype;
        public boolean lazy;
        public boolean packed;
        public UninterpretedOption[] uninterpretedOption;
        public UpgradedOption[] upgradedOption;
        public boolean weak;

        public interface CType {
            public static final int CORD = 1;
            public static final int STRING = 0;
            public static final int STRING_PIECE = 2;
        }

        public interface JSType {
            public static final int JS_NORMAL = 0;
            public static final int JS_NUMBER = 2;
            public static final int JS_STRING = 1;
        }

        public interface JType {
            public static final int BYTES = 1;
            public static final int EXPERIMENTAL_BYTE_BUFFER = 2;
            public static final int NORMAL = 0;
        }

        public static final class UpgradedOption extends ExtendableMessageNano<UpgradedOption> {
            private static volatile UpgradedOption[] _emptyArray;
            public String name;
            public String value;

            public static UpgradedOption[] emptyArray() {
                if (_emptyArray == null) {
                    synchronized (InternalNano.LAZY_INIT_LOCK) {
                        if (_emptyArray == null) {
                            _emptyArray = new UpgradedOption[0];
                        }
                    }
                }
                return _emptyArray;
            }

            public UpgradedOption() {
                clear();
            }

            public UpgradedOption clear() {
                this.name = BuildConfig.VERSION_NAME;
                this.value = BuildConfig.VERSION_NAME;
                this.unknownFieldData = null;
                this.cachedSize = -1;
                return this;
            }

            public boolean equals(Object o) {
                if (o == this) {
                    return true;
                }
                if (!(o instanceof UpgradedOption)) {
                    return false;
                }
                UpgradedOption other = (UpgradedOption) o;
                if (this.name == null) {
                    if (other.name != null) {
                        return false;
                    }
                } else if (!this.name.equals(other.name)) {
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
                int hashCode = (((((getClass().getName().hashCode() + 527) * 31) + (this.name == null ? 0 : this.name.hashCode())) * 31) + (this.value == null ? 0 : this.value.hashCode())) * 31;
                if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                    i = this.unknownFieldData.hashCode();
                }
                return hashCode + i;
            }

            public void writeTo(CodedOutputByteBufferNano output) throws IOException {
                if (!this.name.equals(BuildConfig.VERSION_NAME)) {
                    output.writeString(1, this.name);
                }
                if (!this.value.equals(BuildConfig.VERSION_NAME)) {
                    output.writeString(2, this.value);
                }
                super.writeTo(output);
            }

            protected int computeSerializedSize() {
                int size = super.computeSerializedSize();
                if (!this.name.equals(BuildConfig.VERSION_NAME)) {
                    size += CodedOutputByteBufferNano.computeStringSize(1, this.name);
                }
                if (this.value.equals(BuildConfig.VERSION_NAME)) {
                    return size;
                }
                return size + CodedOutputByteBufferNano.computeStringSize(2, this.value);
            }

            public UpgradedOption mergeFrom(CodedInputByteBufferNano input) throws IOException {
                while (true) {
                    int tag = input.readTag();
                    switch (tag) {
                        case Action.UNKNOWN /*0*/:
                            break;
                        case Type.TAP_ABOUT /*10*/:
                            this.name = input.readString();
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

            public static UpgradedOption parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
                return (UpgradedOption) MessageNano.mergeFrom(new UpgradedOption(), data);
            }

            public static UpgradedOption parseFrom(CodedInputByteBufferNano input) throws IOException {
                return new UpgradedOption().mergeFrom(input);
            }
        }

        public static FieldOptions[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new FieldOptions[0];
                    }
                }
            }
            return _emptyArray;
        }

        public FieldOptions() {
            clear();
        }

        public FieldOptions clear() {
            this.ctype = 0;
            this.packed = false;
            this.jtype = 0;
            this.jstype = 0;
            this.lazy = false;
            this.deprecated = false;
            this.weak = false;
            this.upgradedOption = UpgradedOption.emptyArray();
            this.deprecatedRawMessage = false;
            this.uninterpretedOption = UninterpretedOption.emptyArray();
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof FieldOptions)) {
                return false;
            }
            FieldOptions other = (FieldOptions) o;
            if (this.ctype != other.ctype || this.packed != other.packed || this.jtype != other.jtype || this.jstype != other.jstype || this.lazy != other.lazy || this.deprecated != other.deprecated || this.weak != other.weak || !InternalNano.equals(this.upgradedOption, other.upgradedOption) || this.deprecatedRawMessage != other.deprecatedRawMessage || !InternalNano.equals(this.uninterpretedOption, other.uninterpretedOption)) {
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
            int hashCode = (((((((((getClass().getName().hashCode() + 527) * 31) + this.ctype) * 31) + (this.packed ? 1231 : 1237)) * 31) + this.jtype) * 31) + this.jstype) * 31;
            if (this.lazy) {
                i = 1231;
            } else {
                i = 1237;
            }
            hashCode = (hashCode + i) * 31;
            if (this.deprecated) {
                i = 1231;
            } else {
                i = 1237;
            }
            hashCode = (hashCode + i) * 31;
            if (this.weak) {
                i = 1231;
            } else {
                i = 1237;
            }
            i = (((hashCode + i) * 31) + InternalNano.hashCode(this.upgradedOption)) * 31;
            if (!this.deprecatedRawMessage) {
                i2 = 1237;
            }
            i2 = (((i + i2) * 31) + InternalNano.hashCode(this.uninterpretedOption)) * 31;
            i = (this.unknownFieldData == null || this.unknownFieldData.isEmpty()) ? 0 : this.unknownFieldData.hashCode();
            return i2 + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.ctype != 0) {
                output.writeInt32(1, this.ctype);
            }
            if (this.packed) {
                output.writeBool(2, this.packed);
            }
            if (this.deprecated) {
                output.writeBool(3, this.deprecated);
            }
            if (this.jtype != 0) {
                output.writeInt32(4, this.jtype);
            }
            if (this.lazy) {
                output.writeBool(5, this.lazy);
            }
            if (this.jstype != 0) {
                output.writeInt32(6, this.jstype);
            }
            if (this.weak) {
                output.writeBool(10, this.weak);
            }
            if (this.upgradedOption != null && this.upgradedOption.length > 0) {
                for (UpgradedOption element : this.upgradedOption) {
                    if (element != null) {
                        output.writeMessage(11, element);
                    }
                }
            }
            if (this.deprecatedRawMessage) {
                output.writeBool(12, this.deprecatedRawMessage);
            }
            if (this.uninterpretedOption != null && this.uninterpretedOption.length > 0) {
                for (UninterpretedOption element2 : this.uninterpretedOption) {
                    if (element2 != null) {
                        output.writeMessage(999, element2);
                    }
                }
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.ctype != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(1, this.ctype);
            }
            if (this.packed) {
                size += CodedOutputByteBufferNano.computeBoolSize(2, this.packed);
            }
            if (this.deprecated) {
                size += CodedOutputByteBufferNano.computeBoolSize(3, this.deprecated);
            }
            if (this.jtype != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(4, this.jtype);
            }
            if (this.lazy) {
                size += CodedOutputByteBufferNano.computeBoolSize(5, this.lazy);
            }
            if (this.jstype != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(6, this.jstype);
            }
            if (this.weak) {
                size += CodedOutputByteBufferNano.computeBoolSize(10, this.weak);
            }
            if (this.upgradedOption != null && this.upgradedOption.length > 0) {
                for (UpgradedOption element : this.upgradedOption) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(11, element);
                    }
                }
            }
            if (this.deprecatedRawMessage) {
                size += CodedOutputByteBufferNano.computeBoolSize(12, this.deprecatedRawMessage);
            }
            if (this.uninterpretedOption != null && this.uninterpretedOption.length > 0) {
                for (UninterpretedOption element2 : this.uninterpretedOption) {
                    if (element2 != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(999, element2);
                    }
                }
            }
            return size;
        }

        public FieldOptions mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                int value;
                int arrayLength;
                int i;
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_GET_LINK /*8*/:
                        value = input.readInt32();
                        switch (value) {
                            case Action.UNKNOWN /*0*/:
                            case TimeSelection.Type.TEMPORARY /*1*/:
                            case TimeSelection.Type.INDEFINITELY /*2*/:
                                this.ctype = value;
                                break;
                            default:
                                continue;
                        }
                    case LogSource.GMS_CORE_PEOPLE /*16*/:
                        this.packed = input.readBool();
                        continue;
                    case LogSource.LB_C /*24*/:
                        this.deprecated = input.readBool();
                        continue;
                    case LogSource.SOCIAL /*32*/:
                        value = input.readInt32();
                        switch (value) {
                            case Action.UNKNOWN /*0*/:
                            case TimeSelection.Type.TEMPORARY /*1*/:
                            case TimeSelection.Type.INDEFINITELY /*2*/:
                                this.jtype = value;
                                break;
                            default:
                                continue;
                        }
                    case LogSource.COPRESENCE /*40*/:
                        this.lazy = input.readBool();
                        continue;
                    case LogSource.BACKDROP /*48*/:
                        value = input.readInt32();
                        switch (value) {
                            case Action.UNKNOWN /*0*/:
                            case TimeSelection.Type.TEMPORARY /*1*/:
                            case TimeSelection.Type.INDEFINITELY /*2*/:
                                this.jstype = value;
                                break;
                            default:
                                continue;
                        }
                    case LogSource.CRONET_ANDROID_GSA /*80*/:
                        this.weak = input.readBool();
                        continue;
                    case LogSource.TRON_COUNTERS /*90*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 90);
                        if (this.upgradedOption == null) {
                            i = 0;
                        } else {
                            i = this.upgradedOption.length;
                        }
                        UpgradedOption[] newArray = new UpgradedOption[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.upgradedOption, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new UpgradedOption();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i++;
                        }
                        newArray[i] = new UpgradedOption();
                        input.readMessage(newArray[i]);
                        this.upgradedOption = newArray;
                        continue;
                    case LogSource.GCM_COUNTERS /*96*/:
                        this.deprecatedRawMessage = input.readBool();
                        continue;
                    case 7994:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 7994);
                        if (this.uninterpretedOption == null) {
                            i = 0;
                        } else {
                            i = this.uninterpretedOption.length;
                        }
                        UninterpretedOption[] newArray2 = new UninterpretedOption[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.uninterpretedOption, 0, newArray2, 0, i);
                        }
                        while (i < newArray2.length - 1) {
                            newArray2[i] = new UninterpretedOption();
                            input.readMessage(newArray2[i]);
                            input.readTag();
                            i++;
                        }
                        newArray2[i] = new UninterpretedOption();
                        input.readMessage(newArray2[i]);
                        this.uninterpretedOption = newArray2;
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

        public static FieldOptions parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (FieldOptions) MessageNano.mergeFrom(new FieldOptions(), data);
        }

        public static FieldOptions parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new FieldOptions().mergeFrom(input);
        }
    }

    public static final class FileDescriptorProto extends ExtendableMessageNano<FileDescriptorProto> {
        private static volatile FileDescriptorProto[] _emptyArray;
        public String[] dependency;
        public EnumDescriptorProto[] enumType;
        public FieldDescriptorProto[] extension;
        public DescriptorProto[] messageType;
        public String name;
        public FileOptions options;
        public String package_;
        public int[] publicDependency;
        public ServiceDescriptorProto[] service;
        public SourceCodeInfo sourceCodeInfo;
        public String syntax;
        public int[] weakDependency;

        public static FileDescriptorProto[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new FileDescriptorProto[0];
                    }
                }
            }
            return _emptyArray;
        }

        public FileDescriptorProto() {
            clear();
        }

        public FileDescriptorProto clear() {
            this.name = BuildConfig.VERSION_NAME;
            this.package_ = BuildConfig.VERSION_NAME;
            this.dependency = WireFormatNano.EMPTY_STRING_ARRAY;
            this.publicDependency = WireFormatNano.EMPTY_INT_ARRAY;
            this.weakDependency = WireFormatNano.EMPTY_INT_ARRAY;
            this.messageType = DescriptorProto.emptyArray();
            this.enumType = EnumDescriptorProto.emptyArray();
            this.service = ServiceDescriptorProto.emptyArray();
            this.extension = FieldDescriptorProto.emptyArray();
            this.options = null;
            this.sourceCodeInfo = null;
            this.syntax = BuildConfig.VERSION_NAME;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof FileDescriptorProto)) {
                return false;
            }
            FileDescriptorProto other = (FileDescriptorProto) o;
            if (this.name == null) {
                if (other.name != null) {
                    return false;
                }
            } else if (!this.name.equals(other.name)) {
                return false;
            }
            if (this.package_ == null) {
                if (other.package_ != null) {
                    return false;
                }
            } else if (!this.package_.equals(other.package_)) {
                return false;
            }
            if (!InternalNano.equals(this.dependency, other.dependency) || !InternalNano.equals(this.publicDependency, other.publicDependency) || !InternalNano.equals(this.weakDependency, other.weakDependency) || !InternalNano.equals(this.messageType, other.messageType) || !InternalNano.equals(this.enumType, other.enumType) || !InternalNano.equals(this.service, other.service) || !InternalNano.equals(this.extension, other.extension)) {
                return false;
            }
            if (this.options == null) {
                if (other.options != null) {
                    return false;
                }
            } else if (!this.options.equals(other.options)) {
                return false;
            }
            if (this.sourceCodeInfo == null) {
                if (other.sourceCodeInfo != null) {
                    return false;
                }
            } else if (!this.sourceCodeInfo.equals(other.sourceCodeInfo)) {
                return false;
            }
            if (this.syntax == null) {
                if (other.syntax != null) {
                    return false;
                }
            } else if (!this.syntax.equals(other.syntax)) {
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
            int hashCode = (((getClass().getName().hashCode() + 527) * 31) + (this.name == null ? 0 : this.name.hashCode())) * 31;
            if (this.package_ == null) {
                i = 0;
            } else {
                i = this.package_.hashCode();
            }
            i = (((((((((((((((((((((hashCode + i) * 31) + InternalNano.hashCode(this.dependency)) * 31) + InternalNano.hashCode(this.publicDependency)) * 31) + InternalNano.hashCode(this.weakDependency)) * 31) + InternalNano.hashCode(this.messageType)) * 31) + InternalNano.hashCode(this.enumType)) * 31) + InternalNano.hashCode(this.service)) * 31) + InternalNano.hashCode(this.extension)) * 31) + (this.options == null ? 0 : this.options.hashCode())) * 31) + (this.sourceCodeInfo == null ? 0 : this.sourceCodeInfo.hashCode())) * 31) + (this.syntax == null ? 0 : this.syntax.hashCode())) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i2 = this.unknownFieldData.hashCode();
            }
            return i + i2;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (!this.name.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(1, this.name);
            }
            if (!this.package_.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(2, this.package_);
            }
            if (this.dependency != null && this.dependency.length > 0) {
                for (String element : this.dependency) {
                    if (element != null) {
                        output.writeString(3, element);
                    }
                }
            }
            if (this.messageType != null && this.messageType.length > 0) {
                for (DescriptorProto element2 : this.messageType) {
                    if (element2 != null) {
                        output.writeMessage(4, element2);
                    }
                }
            }
            if (this.enumType != null && this.enumType.length > 0) {
                for (EnumDescriptorProto element3 : this.enumType) {
                    if (element3 != null) {
                        output.writeMessage(5, element3);
                    }
                }
            }
            if (this.service != null && this.service.length > 0) {
                for (ServiceDescriptorProto element4 : this.service) {
                    if (element4 != null) {
                        output.writeMessage(6, element4);
                    }
                }
            }
            if (this.extension != null && this.extension.length > 0) {
                for (FieldDescriptorProto element5 : this.extension) {
                    if (element5 != null) {
                        output.writeMessage(7, element5);
                    }
                }
            }
            if (this.options != null) {
                output.writeMessage(8, this.options);
            }
            if (this.sourceCodeInfo != null) {
                output.writeMessage(9, this.sourceCodeInfo);
            }
            if (this.publicDependency != null && this.publicDependency.length > 0) {
                for (int writeInt32 : this.publicDependency) {
                    output.writeInt32(10, writeInt32);
                }
            }
            if (this.weakDependency != null && this.weakDependency.length > 0) {
                for (int writeInt322 : this.weakDependency) {
                    output.writeInt32(11, writeInt322);
                }
            }
            if (!this.syntax.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(12, this.syntax);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int dataSize;
            int size = super.computeSerializedSize();
            if (!this.name.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.name);
            }
            if (!this.package_.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(2, this.package_);
            }
            if (this.dependency != null && this.dependency.length > 0) {
                int dataCount = 0;
                dataSize = 0;
                for (String element : this.dependency) {
                    if (element != null) {
                        dataCount++;
                        dataSize += CodedOutputByteBufferNano.computeStringSizeNoTag(element);
                    }
                }
                size = (size + dataSize) + (dataCount * 1);
            }
            if (this.messageType != null && this.messageType.length > 0) {
                for (DescriptorProto element2 : this.messageType) {
                    if (element2 != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(4, element2);
                    }
                }
            }
            if (this.enumType != null && this.enumType.length > 0) {
                for (EnumDescriptorProto element3 : this.enumType) {
                    if (element3 != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(5, element3);
                    }
                }
            }
            if (this.service != null && this.service.length > 0) {
                for (ServiceDescriptorProto element4 : this.service) {
                    if (element4 != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(6, element4);
                    }
                }
            }
            if (this.extension != null && this.extension.length > 0) {
                for (FieldDescriptorProto element5 : this.extension) {
                    if (element5 != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(7, element5);
                    }
                }
            }
            if (this.options != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(8, this.options);
            }
            if (this.sourceCodeInfo != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(9, this.sourceCodeInfo);
            }
            if (this.publicDependency != null && this.publicDependency.length > 0) {
                dataSize = 0;
                for (int element6 : this.publicDependency) {
                    dataSize += CodedOutputByteBufferNano.computeInt32SizeNoTag(element6);
                }
                size = (size + dataSize) + (this.publicDependency.length * 1);
            }
            if (this.weakDependency != null && this.weakDependency.length > 0) {
                dataSize = 0;
                for (int element62 : this.weakDependency) {
                    dataSize += CodedOutputByteBufferNano.computeInt32SizeNoTag(element62);
                }
                size = (size + dataSize) + (this.weakDependency.length * 1);
            }
            if (this.syntax.equals(BuildConfig.VERSION_NAME)) {
                return size;
            }
            return size + CodedOutputByteBufferNano.computeStringSize(12, this.syntax);
        }

        public FileDescriptorProto mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                int arrayLength;
                int i;
                int[] newArray;
                int limit;
                int startPos;
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        this.name = input.readString();
                        continue;
                    case LogSource.GOOGLE_ANALYTICS /*18*/:
                        this.package_ = input.readString();
                        continue;
                    case LogSource.ANDROID_CAMERA /*26*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 26);
                        i = this.dependency == null ? 0 : this.dependency.length;
                        String[] newArray2 = new String[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.dependency, 0, newArray2, 0, i);
                        }
                        while (i < newArray2.length - 1) {
                            newArray2[i] = input.readString();
                            input.readTag();
                            i++;
                        }
                        newArray2[i] = input.readString();
                        this.dependency = newArray2;
                        continue;
                    case LogSource.NOVA /*34*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 34);
                        if (this.messageType == null) {
                            i = 0;
                        } else {
                            i = this.messageType.length;
                        }
                        DescriptorProto[] newArray3 = new DescriptorProto[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.messageType, 0, newArray3, 0, i);
                        }
                        while (i < newArray3.length - 1) {
                            newArray3[i] = new DescriptorProto();
                            input.readMessage(newArray3[i]);
                            input.readTag();
                            i++;
                        }
                        newArray3[i] = new DescriptorProto();
                        input.readMessage(newArray3[i]);
                        this.messageType = newArray3;
                        continue;
                    case LogSource.PHOTOS /*42*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 42);
                        if (this.enumType == null) {
                            i = 0;
                        } else {
                            i = this.enumType.length;
                        }
                        EnumDescriptorProto[] newArray4 = new EnumDescriptorProto[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.enumType, 0, newArray4, 0, i);
                        }
                        while (i < newArray4.length - 1) {
                            newArray4[i] = new EnumDescriptorProto();
                            input.readMessage(newArray4[i]);
                            input.readTag();
                            i++;
                        }
                        newArray4[i] = new EnumDescriptorProto();
                        input.readMessage(newArray4[i]);
                        this.enumType = newArray4;
                        continue;
                    case LogSource.HANGOUT_LOG_REQUEST /*50*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 50);
                        if (this.service == null) {
                            i = 0;
                        } else {
                            i = this.service.length;
                        }
                        ServiceDescriptorProto[] newArray5 = new ServiceDescriptorProto[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.service, 0, newArray5, 0, i);
                        }
                        while (i < newArray5.length - 1) {
                            newArray5[i] = new ServiceDescriptorProto();
                            input.readMessage(newArray5[i]);
                            input.readTag();
                            i++;
                        }
                        newArray5[i] = new ServiceDescriptorProto();
                        input.readMessage(newArray5[i]);
                        this.service = newArray5;
                        continue;
                    case LogSource.SLIDES /*58*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 58);
                        if (this.extension == null) {
                            i = 0;
                        } else {
                            i = this.extension.length;
                        }
                        FieldDescriptorProto[] newArray6 = new FieldDescriptorProto[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.extension, 0, newArray6, 0, i);
                        }
                        while (i < newArray6.length - 1) {
                            newArray6[i] = new FieldDescriptorProto();
                            input.readMessage(newArray6[i]);
                            input.readTag();
                            i++;
                        }
                        newArray6[i] = new FieldDescriptorProto();
                        input.readMessage(newArray6[i]);
                        this.extension = newArray6;
                        continue;
                    case LogSource.WIFI_ASSISTANT /*66*/:
                        if (this.options == null) {
                            this.options = new FileOptions();
                        }
                        input.readMessage(this.options);
                        continue;
                    case LogSource.MOVIES /*74*/:
                        if (this.sourceCodeInfo == null) {
                            this.sourceCodeInfo = new SourceCodeInfo();
                        }
                        input.readMessage(this.sourceCodeInfo);
                        continue;
                    case LogSource.CRONET_ANDROID_GSA /*80*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 80);
                        i = this.publicDependency == null ? 0 : this.publicDependency.length;
                        newArray = new int[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.publicDependency, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = input.readInt32();
                            input.readTag();
                            i++;
                        }
                        newArray[i] = input.readInt32();
                        this.publicDependency = newArray;
                        continue;
                    case LogSource.GOOGLE_EXPRESS /*82*/:
                        limit = input.pushLimit(input.readRawVarint32());
                        arrayLength = 0;
                        startPos = input.getPosition();
                        while (input.getBytesUntilLimit() > 0) {
                            input.readInt32();
                            arrayLength++;
                        }
                        input.rewindToPosition(startPos);
                        i = this.publicDependency == null ? 0 : this.publicDependency.length;
                        newArray = new int[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.publicDependency, 0, newArray, 0, i);
                        }
                        while (i < newArray.length) {
                            newArray[i] = input.readInt32();
                            i++;
                        }
                        this.publicDependency = newArray;
                        input.popLimit(limit);
                        continue;
                    case LogSource.EMERGENCY_ASSIST /*88*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 88);
                        i = this.weakDependency == null ? 0 : this.weakDependency.length;
                        newArray = new int[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.weakDependency, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = input.readInt32();
                            input.readTag();
                            i++;
                        }
                        newArray[i] = input.readInt32();
                        this.weakDependency = newArray;
                        continue;
                    case LogSource.TRON_COUNTERS /*90*/:
                        limit = input.pushLimit(input.readRawVarint32());
                        arrayLength = 0;
                        startPos = input.getPosition();
                        while (input.getBytesUntilLimit() > 0) {
                            input.readInt32();
                            arrayLength++;
                        }
                        input.rewindToPosition(startPos);
                        i = this.weakDependency == null ? 0 : this.weakDependency.length;
                        newArray = new int[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.weakDependency, 0, newArray, 0, i);
                        }
                        while (i < newArray.length) {
                            newArray[i] = input.readInt32();
                            i++;
                        }
                        this.weakDependency = newArray;
                        input.popLimit(limit);
                        continue;
                    case LogSource.TACHYON_LOG_REQUEST /*98*/:
                        this.syntax = input.readString();
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

        public static FileDescriptorProto parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (FileDescriptorProto) MessageNano.mergeFrom(new FileDescriptorProto(), data);
        }

        public static FileDescriptorProto parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new FileDescriptorProto().mergeFrom(input);
        }
    }

    public static final class FileDescriptorSet extends ExtendableMessageNano<FileDescriptorSet> {
        private static volatile FileDescriptorSet[] _emptyArray;
        public FileDescriptorProto[] file;

        public static FileDescriptorSet[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new FileDescriptorSet[0];
                    }
                }
            }
            return _emptyArray;
        }

        public FileDescriptorSet() {
            clear();
        }

        public FileDescriptorSet clear() {
            this.file = FileDescriptorProto.emptyArray();
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof FileDescriptorSet)) {
                return false;
            }
            FileDescriptorSet other = (FileDescriptorSet) o;
            if (!InternalNano.equals(this.file, other.file)) {
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
            int hashCode = (((getClass().getName().hashCode() + 527) * 31) + InternalNano.hashCode(this.file)) * 31;
            int hashCode2 = (this.unknownFieldData == null || this.unknownFieldData.isEmpty()) ? 0 : this.unknownFieldData.hashCode();
            return hashCode + hashCode2;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.file != null && this.file.length > 0) {
                for (FileDescriptorProto element : this.file) {
                    if (element != null) {
                        output.writeMessage(1, element);
                    }
                }
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.file != null && this.file.length > 0) {
                for (FileDescriptorProto element : this.file) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(1, element);
                    }
                }
            }
            return size;
        }

        public FileDescriptorSet mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        int i;
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 10);
                        if (this.file == null) {
                            i = 0;
                        } else {
                            i = this.file.length;
                        }
                        FileDescriptorProto[] newArray = new FileDescriptorProto[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.file, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new FileDescriptorProto();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i++;
                        }
                        newArray[i] = new FileDescriptorProto();
                        input.readMessage(newArray[i]);
                        this.file = newArray;
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

        public static FileDescriptorSet parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (FileDescriptorSet) MessageNano.mergeFrom(new FileDescriptorSet(), data);
        }

        public static FileDescriptorSet parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new FileDescriptorSet().mergeFrom(input);
        }
    }

    public static final class FileOptions extends ExtendableMessageNano<FileOptions> {
        private static volatile FileOptions[] _emptyArray;
        public int ccApiCompatibility;
        public boolean ccEnableArenas;
        public boolean ccEnableMaps;
        public boolean ccGenericServices;
        public boolean ccProto1TextFormat;
        public boolean ccProtoArrayCompatible;
        public boolean ccUtf8Verification;
        public boolean deprecated;
        public boolean goForceProto2Api;
        public String goPackage;
        public String javaAltApiPackage;
        public boolean javaEnableMapsForProto2;
        public boolean javaGenerateEqualsAndHash;
        public boolean javaGenerateRpcBaseimpl;
        public boolean javaGenericServices;
        public boolean javaJava5Enums;
        public boolean javaMultipleFiles;
        public String javaMultipleFilesMutablePackage;
        public boolean javaMutableApi;
        public String javaOuterClassname;
        public String javaPackage;
        public boolean javaStringCheckUtf8;
        public boolean javaUseJavaproto2;
        public boolean javaUseJavastrings;
        public String javascriptPackage;
        public String objcClassPrefix;
        public int optimizeFor;
        public boolean pyGenericServices;
        public int szlApiVersion;
        public UninterpretedOption[] uninterpretedOption;

        public interface CompatibilityLevel {
            public static final int DEPRECATED_PROTO1_COMPATIBLE = 50;
            public static final int NO_COMPATIBILITY = 0;
            public static final int PROTO1_COMPATIBLE = 100;
        }

        public interface OptimizeMode {
            public static final int CODE_SIZE = 2;
            public static final int LITE_RUNTIME = 3;
            public static final int SPEED = 1;
        }

        public static FileOptions[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new FileOptions[0];
                    }
                }
            }
            return _emptyArray;
        }

        public FileOptions() {
            clear();
        }

        public FileOptions clear() {
            this.ccApiCompatibility = 0;
            this.ccProtoArrayCompatible = true;
            this.ccUtf8Verification = true;
            this.ccProto1TextFormat = false;
            this.javaPackage = BuildConfig.VERSION_NAME;
            this.javaUseJavaproto2 = true;
            this.javaJava5Enums = true;
            this.javaGenerateRpcBaseimpl = false;
            this.javaUseJavastrings = false;
            this.javaAltApiPackage = BuildConfig.VERSION_NAME;
            this.javaOuterClassname = BuildConfig.VERSION_NAME;
            this.javaMultipleFiles = false;
            this.javaGenerateEqualsAndHash = false;
            this.javaStringCheckUtf8 = false;
            this.javaMutableApi = false;
            this.javaMultipleFilesMutablePackage = BuildConfig.VERSION_NAME;
            this.optimizeFor = 1;
            this.goPackage = BuildConfig.VERSION_NAME;
            this.javascriptPackage = BuildConfig.VERSION_NAME;
            this.szlApiVersion = 1;
            this.ccGenericServices = false;
            this.javaGenericServices = false;
            this.pyGenericServices = false;
            this.deprecated = false;
            this.ccEnableArenas = false;
            this.ccEnableMaps = true;
            this.goForceProto2Api = false;
            this.javaEnableMapsForProto2 = true;
            this.objcClassPrefix = BuildConfig.VERSION_NAME;
            this.uninterpretedOption = UninterpretedOption.emptyArray();
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof FileOptions)) {
                return false;
            }
            FileOptions other = (FileOptions) o;
            if (this.ccApiCompatibility != other.ccApiCompatibility || this.ccProtoArrayCompatible != other.ccProtoArrayCompatible || this.ccUtf8Verification != other.ccUtf8Verification || this.ccProto1TextFormat != other.ccProto1TextFormat) {
                return false;
            }
            if (this.javaPackage == null) {
                if (other.javaPackage != null) {
                    return false;
                }
            } else if (!this.javaPackage.equals(other.javaPackage)) {
                return false;
            }
            if (this.javaUseJavaproto2 != other.javaUseJavaproto2 || this.javaJava5Enums != other.javaJava5Enums || this.javaGenerateRpcBaseimpl != other.javaGenerateRpcBaseimpl || this.javaUseJavastrings != other.javaUseJavastrings) {
                return false;
            }
            if (this.javaAltApiPackage == null) {
                if (other.javaAltApiPackage != null) {
                    return false;
                }
            } else if (!this.javaAltApiPackage.equals(other.javaAltApiPackage)) {
                return false;
            }
            if (this.javaOuterClassname == null) {
                if (other.javaOuterClassname != null) {
                    return false;
                }
            } else if (!this.javaOuterClassname.equals(other.javaOuterClassname)) {
                return false;
            }
            if (this.javaMultipleFiles != other.javaMultipleFiles || this.javaGenerateEqualsAndHash != other.javaGenerateEqualsAndHash || this.javaStringCheckUtf8 != other.javaStringCheckUtf8 || this.javaMutableApi != other.javaMutableApi) {
                return false;
            }
            if (this.javaMultipleFilesMutablePackage == null) {
                if (other.javaMultipleFilesMutablePackage != null) {
                    return false;
                }
            } else if (!this.javaMultipleFilesMutablePackage.equals(other.javaMultipleFilesMutablePackage)) {
                return false;
            }
            if (this.optimizeFor != other.optimizeFor) {
                return false;
            }
            if (this.goPackage == null) {
                if (other.goPackage != null) {
                    return false;
                }
            } else if (!this.goPackage.equals(other.goPackage)) {
                return false;
            }
            if (this.javascriptPackage == null) {
                if (other.javascriptPackage != null) {
                    return false;
                }
            } else if (!this.javascriptPackage.equals(other.javascriptPackage)) {
                return false;
            }
            if (this.szlApiVersion != other.szlApiVersion || this.ccGenericServices != other.ccGenericServices || this.javaGenericServices != other.javaGenericServices || this.pyGenericServices != other.pyGenericServices || this.deprecated != other.deprecated || this.ccEnableArenas != other.ccEnableArenas || this.ccEnableMaps != other.ccEnableMaps || this.goForceProto2Api != other.goForceProto2Api || this.javaEnableMapsForProto2 != other.javaEnableMapsForProto2) {
                return false;
            }
            if (this.objcClassPrefix == null) {
                if (other.objcClassPrefix != null) {
                    return false;
                }
            } else if (!this.objcClassPrefix.equals(other.objcClassPrefix)) {
                return false;
            }
            if (!InternalNano.equals(this.uninterpretedOption, other.uninterpretedOption)) {
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
            int hashCode = (((((getClass().getName().hashCode() + 527) * 31) + this.ccApiCompatibility) * 31) + (this.ccProtoArrayCompatible ? 1231 : 1237)) * 31;
            if (this.ccUtf8Verification) {
                i = 1231;
            } else {
                i = 1237;
            }
            hashCode = (hashCode + i) * 31;
            if (this.ccProto1TextFormat) {
                i = 1231;
            } else {
                i = 1237;
            }
            hashCode = (((hashCode + i) * 31) + (this.javaPackage == null ? 0 : this.javaPackage.hashCode())) * 31;
            if (this.javaUseJavaproto2) {
                i = 1231;
            } else {
                i = 1237;
            }
            hashCode = (hashCode + i) * 31;
            if (this.javaJava5Enums) {
                i = 1231;
            } else {
                i = 1237;
            }
            hashCode = (hashCode + i) * 31;
            if (this.javaGenerateRpcBaseimpl) {
                i = 1231;
            } else {
                i = 1237;
            }
            hashCode = (hashCode + i) * 31;
            if (this.javaUseJavastrings) {
                i = 1231;
            } else {
                i = 1237;
            }
            hashCode = (((((hashCode + i) * 31) + (this.javaAltApiPackage == null ? 0 : this.javaAltApiPackage.hashCode())) * 31) + (this.javaOuterClassname == null ? 0 : this.javaOuterClassname.hashCode())) * 31;
            if (this.javaMultipleFiles) {
                i = 1231;
            } else {
                i = 1237;
            }
            hashCode = (hashCode + i) * 31;
            if (this.javaGenerateEqualsAndHash) {
                i = 1231;
            } else {
                i = 1237;
            }
            hashCode = (hashCode + i) * 31;
            if (this.javaStringCheckUtf8) {
                i = 1231;
            } else {
                i = 1237;
            }
            hashCode = (hashCode + i) * 31;
            if (this.javaMutableApi) {
                i = 1231;
            } else {
                i = 1237;
            }
            hashCode = (((((((((((hashCode + i) * 31) + (this.javaMultipleFilesMutablePackage == null ? 0 : this.javaMultipleFilesMutablePackage.hashCode())) * 31) + this.optimizeFor) * 31) + (this.goPackage == null ? 0 : this.goPackage.hashCode())) * 31) + (this.javascriptPackage == null ? 0 : this.javascriptPackage.hashCode())) * 31) + this.szlApiVersion) * 31;
            if (this.ccGenericServices) {
                i = 1231;
            } else {
                i = 1237;
            }
            hashCode = (hashCode + i) * 31;
            if (this.javaGenericServices) {
                i = 1231;
            } else {
                i = 1237;
            }
            hashCode = (hashCode + i) * 31;
            if (this.pyGenericServices) {
                i = 1231;
            } else {
                i = 1237;
            }
            hashCode = (hashCode + i) * 31;
            if (this.deprecated) {
                i = 1231;
            } else {
                i = 1237;
            }
            hashCode = (hashCode + i) * 31;
            if (this.ccEnableArenas) {
                i = 1231;
            } else {
                i = 1237;
            }
            hashCode = (hashCode + i) * 31;
            if (this.ccEnableMaps) {
                i = 1231;
            } else {
                i = 1237;
            }
            hashCode = (hashCode + i) * 31;
            if (this.goForceProto2Api) {
                i = 1231;
            } else {
                i = 1237;
            }
            i = (hashCode + i) * 31;
            if (!this.javaEnableMapsForProto2) {
                i3 = 1237;
            }
            i = (((((i + i3) * 31) + (this.objcClassPrefix == null ? 0 : this.objcClassPrefix.hashCode())) * 31) + InternalNano.hashCode(this.uninterpretedOption)) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i2 = this.unknownFieldData.hashCode();
            }
            return i + i2;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (!this.javaPackage.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(1, this.javaPackage);
            }
            if (!this.javaUseJavaproto2) {
                output.writeBool(6, this.javaUseJavaproto2);
            }
            if (!this.javaJava5Enums) {
                output.writeBool(7, this.javaJava5Enums);
            }
            if (!this.javaOuterClassname.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(8, this.javaOuterClassname);
            }
            if (this.optimizeFor != 1) {
                output.writeInt32(9, this.optimizeFor);
            }
            if (this.javaMultipleFiles) {
                output.writeBool(10, this.javaMultipleFiles);
            }
            if (!this.goPackage.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(11, this.goPackage);
            }
            if (!this.javascriptPackage.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(12, this.javascriptPackage);
            }
            if (this.javaGenerateRpcBaseimpl) {
                output.writeBool(13, this.javaGenerateRpcBaseimpl);
            }
            if (this.szlApiVersion != 1) {
                output.writeInt32(14, this.szlApiVersion);
            }
            if (this.ccApiCompatibility != 0) {
                output.writeInt32(15, this.ccApiCompatibility);
            }
            if (this.ccGenericServices) {
                output.writeBool(16, this.ccGenericServices);
            }
            if (this.javaGenericServices) {
                output.writeBool(17, this.javaGenericServices);
            }
            if (this.pyGenericServices) {
                output.writeBool(18, this.pyGenericServices);
            }
            if (!this.javaAltApiPackage.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(19, this.javaAltApiPackage);
            }
            if (this.javaGenerateEqualsAndHash) {
                output.writeBool(20, this.javaGenerateEqualsAndHash);
            }
            if (this.javaUseJavastrings) {
                output.writeBool(21, this.javaUseJavastrings);
            }
            if (!this.ccProtoArrayCompatible) {
                output.writeBool(22, this.ccProtoArrayCompatible);
            }
            if (this.deprecated) {
                output.writeBool(23, this.deprecated);
            }
            if (!this.ccUtf8Verification) {
                output.writeBool(24, this.ccUtf8Verification);
            }
            if (this.ccProto1TextFormat) {
                output.writeBool(25, this.ccProto1TextFormat);
            }
            if (this.javaStringCheckUtf8) {
                output.writeBool(27, this.javaStringCheckUtf8);
            }
            if (this.javaMutableApi) {
                output.writeBool(28, this.javaMutableApi);
            }
            if (!this.javaMultipleFilesMutablePackage.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(29, this.javaMultipleFilesMutablePackage);
            }
            if (this.ccEnableArenas) {
                output.writeBool(31, this.ccEnableArenas);
            }
            if (!this.ccEnableMaps) {
                output.writeBool(32, this.ccEnableMaps);
            }
            if (this.goForceProto2Api) {
                output.writeBool(33, this.goForceProto2Api);
            }
            if (!this.javaEnableMapsForProto2) {
                output.writeBool(34, this.javaEnableMapsForProto2);
            }
            if (!this.objcClassPrefix.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(36, this.objcClassPrefix);
            }
            if (this.uninterpretedOption != null && this.uninterpretedOption.length > 0) {
                for (UninterpretedOption element : this.uninterpretedOption) {
                    if (element != null) {
                        output.writeMessage(999, element);
                    }
                }
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (!this.javaPackage.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.javaPackage);
            }
            if (!this.javaUseJavaproto2) {
                size += CodedOutputByteBufferNano.computeBoolSize(6, this.javaUseJavaproto2);
            }
            if (!this.javaJava5Enums) {
                size += CodedOutputByteBufferNano.computeBoolSize(7, this.javaJava5Enums);
            }
            if (!this.javaOuterClassname.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(8, this.javaOuterClassname);
            }
            if (this.optimizeFor != 1) {
                size += CodedOutputByteBufferNano.computeInt32Size(9, this.optimizeFor);
            }
            if (this.javaMultipleFiles) {
                size += CodedOutputByteBufferNano.computeBoolSize(10, this.javaMultipleFiles);
            }
            if (!this.goPackage.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(11, this.goPackage);
            }
            if (!this.javascriptPackage.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(12, this.javascriptPackage);
            }
            if (this.javaGenerateRpcBaseimpl) {
                size += CodedOutputByteBufferNano.computeBoolSize(13, this.javaGenerateRpcBaseimpl);
            }
            if (this.szlApiVersion != 1) {
                size += CodedOutputByteBufferNano.computeInt32Size(14, this.szlApiVersion);
            }
            if (this.ccApiCompatibility != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(15, this.ccApiCompatibility);
            }
            if (this.ccGenericServices) {
                size += CodedOutputByteBufferNano.computeBoolSize(16, this.ccGenericServices);
            }
            if (this.javaGenericServices) {
                size += CodedOutputByteBufferNano.computeBoolSize(17, this.javaGenericServices);
            }
            if (this.pyGenericServices) {
                size += CodedOutputByteBufferNano.computeBoolSize(18, this.pyGenericServices);
            }
            if (!this.javaAltApiPackage.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(19, this.javaAltApiPackage);
            }
            if (this.javaGenerateEqualsAndHash) {
                size += CodedOutputByteBufferNano.computeBoolSize(20, this.javaGenerateEqualsAndHash);
            }
            if (this.javaUseJavastrings) {
                size += CodedOutputByteBufferNano.computeBoolSize(21, this.javaUseJavastrings);
            }
            if (!this.ccProtoArrayCompatible) {
                size += CodedOutputByteBufferNano.computeBoolSize(22, this.ccProtoArrayCompatible);
            }
            if (this.deprecated) {
                size += CodedOutputByteBufferNano.computeBoolSize(23, this.deprecated);
            }
            if (!this.ccUtf8Verification) {
                size += CodedOutputByteBufferNano.computeBoolSize(24, this.ccUtf8Verification);
            }
            if (this.ccProto1TextFormat) {
                size += CodedOutputByteBufferNano.computeBoolSize(25, this.ccProto1TextFormat);
            }
            if (this.javaStringCheckUtf8) {
                size += CodedOutputByteBufferNano.computeBoolSize(27, this.javaStringCheckUtf8);
            }
            if (this.javaMutableApi) {
                size += CodedOutputByteBufferNano.computeBoolSize(28, this.javaMutableApi);
            }
            if (!this.javaMultipleFilesMutablePackage.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(29, this.javaMultipleFilesMutablePackage);
            }
            if (this.ccEnableArenas) {
                size += CodedOutputByteBufferNano.computeBoolSize(31, this.ccEnableArenas);
            }
            if (!this.ccEnableMaps) {
                size += CodedOutputByteBufferNano.computeBoolSize(32, this.ccEnableMaps);
            }
            if (this.goForceProto2Api) {
                size += CodedOutputByteBufferNano.computeBoolSize(33, this.goForceProto2Api);
            }
            if (!this.javaEnableMapsForProto2) {
                size += CodedOutputByteBufferNano.computeBoolSize(34, this.javaEnableMapsForProto2);
            }
            if (!this.objcClassPrefix.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(36, this.objcClassPrefix);
            }
            if (this.uninterpretedOption != null && this.uninterpretedOption.length > 0) {
                for (UninterpretedOption element : this.uninterpretedOption) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(999, element);
                    }
                }
            }
            return size;
        }

        public FileOptions mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                int value;
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        this.javaPackage = input.readString();
                        continue;
                    case LogSource.BACKDROP /*48*/:
                        this.javaUseJavaproto2 = input.readBool();
                        continue;
                    case LogSource.DOCS /*56*/:
                        this.javaJava5Enums = input.readBool();
                        continue;
                    case LogSource.WIFI_ASSISTANT /*66*/:
                        this.javaOuterClassname = input.readString();
                        continue;
                    case LogSource.JUSTSPEAK /*72*/:
                        value = input.readInt32();
                        switch (value) {
                            case TimeSelection.Type.TEMPORARY /*1*/:
                            case TimeSelection.Type.INDEFINITELY /*2*/:
                            case TimeSelection.Type.CUSTOM /*3*/:
                                this.optimizeFor = value;
                                break;
                            default:
                                continue;
                        }
                    case LogSource.CRONET_ANDROID_GSA /*80*/:
                        this.javaMultipleFiles = input.readBool();
                        continue;
                    case LogSource.TRON_COUNTERS /*90*/:
                        this.goPackage = input.readString();
                        continue;
                    case LogSource.TACHYON_LOG_REQUEST /*98*/:
                        this.javascriptPackage = input.readString();
                        continue;
                    case LogSource.MOBILESDK_CLIENT /*104*/:
                        this.javaGenerateRpcBaseimpl = input.readBool();
                        continue;
                    case LogSource.ENDER /*112*/:
                        this.szlApiVersion = input.readInt32();
                        continue;
                    case LogSource.FLYDROID /*120*/:
                        value = input.readInt32();
                        switch (value) {
                            case Action.UNKNOWN /*0*/:
                            case LogSource.HANGOUT_LOG_REQUEST /*50*/:
                            case LogSource.VISION /*100*/:
                                this.ccApiCompatibility = value;
                                break;
                            default:
                                continue;
                        }
                    case LogSource.KEEP /*128*/:
                        this.ccGenericServices = input.readBool();
                        continue;
                    case LogSource.SAMPLE_SHM /*136*/:
                        this.javaGenericServices = input.readBool();
                        continue;
                    case 144:
                        this.pyGenericServices = input.readBool();
                        continue;
                    case 154:
                        this.javaAltApiPackage = input.readString();
                        continue;
                    case 160:
                        this.javaGenerateEqualsAndHash = input.readBool();
                        continue;
                    case 168:
                        this.javaUseJavastrings = input.readBool();
                        continue;
                    case 176:
                        this.ccProtoArrayCompatible = input.readBool();
                        continue;
                    case 184:
                        this.deprecated = input.readBool();
                        continue;
                    case 192:
                        this.ccUtf8Verification = input.readBool();
                        continue;
                    case Importance.IMPORTANCE_VISIBLE /*200*/:
                        this.ccProto1TextFormat = input.readBool();
                        continue;
                    case 216:
                        this.javaStringCheckUtf8 = input.readBool();
                        continue;
                    case 224:
                        this.javaMutableApi = input.readBool();
                        continue;
                    case 234:
                        this.javaMultipleFilesMutablePackage = input.readString();
                        continue;
                    case 248:
                        this.ccEnableArenas = input.readBool();
                        continue;
                    case PeopleColumnBitmask.IS_BLOCKED /*256*/:
                        this.ccEnableMaps = input.readBool();
                        continue;
                    case 264:
                        this.goForceProto2Api = input.readBool();
                        continue;
                    case 272:
                        this.javaEnableMapsForProto2 = input.readBool();
                        continue;
                    case 290:
                        this.objcClassPrefix = input.readString();
                        continue;
                    case 7994:
                        int i;
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 7994);
                        if (this.uninterpretedOption == null) {
                            i = 0;
                        } else {
                            i = this.uninterpretedOption.length;
                        }
                        UninterpretedOption[] newArray = new UninterpretedOption[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.uninterpretedOption, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new UninterpretedOption();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i++;
                        }
                        newArray[i] = new UninterpretedOption();
                        input.readMessage(newArray[i]);
                        this.uninterpretedOption = newArray;
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

        public static FileOptions parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (FileOptions) MessageNano.mergeFrom(new FileOptions(), data);
        }

        public static FileOptions parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new FileOptions().mergeFrom(input);
        }
    }

    public static final class MessageOptions extends ExtendableMessageNano<MessageOptions> {
        private static volatile MessageOptions[] _emptyArray;
        public boolean deprecated;
        public String[] experimentalJavaBuilderInterface;
        public String[] experimentalJavaInterfaceExtends;
        public String[] experimentalJavaMessageInterface;
        public boolean mapEntry;
        public boolean messageSetWireFormat;
        public boolean noStandardDescriptorAccessor;
        public UninterpretedOption[] uninterpretedOption;

        public static MessageOptions[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new MessageOptions[0];
                    }
                }
            }
            return _emptyArray;
        }

        public MessageOptions() {
            clear();
        }

        public MessageOptions clear() {
            this.experimentalJavaMessageInterface = WireFormatNano.EMPTY_STRING_ARRAY;
            this.experimentalJavaBuilderInterface = WireFormatNano.EMPTY_STRING_ARRAY;
            this.experimentalJavaInterfaceExtends = WireFormatNano.EMPTY_STRING_ARRAY;
            this.messageSetWireFormat = false;
            this.noStandardDescriptorAccessor = false;
            this.deprecated = false;
            this.mapEntry = false;
            this.uninterpretedOption = UninterpretedOption.emptyArray();
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof MessageOptions)) {
                return false;
            }
            MessageOptions other = (MessageOptions) o;
            if (!InternalNano.equals(this.experimentalJavaMessageInterface, other.experimentalJavaMessageInterface) || !InternalNano.equals(this.experimentalJavaBuilderInterface, other.experimentalJavaBuilderInterface) || !InternalNano.equals(this.experimentalJavaInterfaceExtends, other.experimentalJavaInterfaceExtends) || this.messageSetWireFormat != other.messageSetWireFormat || this.noStandardDescriptorAccessor != other.noStandardDescriptorAccessor || this.deprecated != other.deprecated || this.mapEntry != other.mapEntry || !InternalNano.equals(this.uninterpretedOption, other.uninterpretedOption)) {
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
            int hashCode = (((((((((getClass().getName().hashCode() + 527) * 31) + InternalNano.hashCode(this.experimentalJavaMessageInterface)) * 31) + InternalNano.hashCode(this.experimentalJavaBuilderInterface)) * 31) + InternalNano.hashCode(this.experimentalJavaInterfaceExtends)) * 31) + (this.messageSetWireFormat ? 1231 : 1237)) * 31;
            if (this.noStandardDescriptorAccessor) {
                i = 1231;
            } else {
                i = 1237;
            }
            hashCode = (hashCode + i) * 31;
            if (this.deprecated) {
                i = 1231;
            } else {
                i = 1237;
            }
            i = (hashCode + i) * 31;
            if (!this.mapEntry) {
                i2 = 1237;
            }
            i2 = (((i + i2) * 31) + InternalNano.hashCode(this.uninterpretedOption)) * 31;
            i = (this.unknownFieldData == null || this.unknownFieldData.isEmpty()) ? 0 : this.unknownFieldData.hashCode();
            return i2 + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.messageSetWireFormat) {
                output.writeBool(1, this.messageSetWireFormat);
            }
            if (this.noStandardDescriptorAccessor) {
                output.writeBool(2, this.noStandardDescriptorAccessor);
            }
            if (this.deprecated) {
                output.writeBool(3, this.deprecated);
            }
            if (this.experimentalJavaMessageInterface != null && this.experimentalJavaMessageInterface.length > 0) {
                for (String element : this.experimentalJavaMessageInterface) {
                    if (element != null) {
                        output.writeString(4, element);
                    }
                }
            }
            if (this.experimentalJavaBuilderInterface != null && this.experimentalJavaBuilderInterface.length > 0) {
                for (String element2 : this.experimentalJavaBuilderInterface) {
                    if (element2 != null) {
                        output.writeString(5, element2);
                    }
                }
            }
            if (this.experimentalJavaInterfaceExtends != null && this.experimentalJavaInterfaceExtends.length > 0) {
                for (String element22 : this.experimentalJavaInterfaceExtends) {
                    if (element22 != null) {
                        output.writeString(6, element22);
                    }
                }
            }
            if (this.mapEntry) {
                output.writeBool(7, this.mapEntry);
            }
            if (this.uninterpretedOption != null && this.uninterpretedOption.length > 0) {
                for (UninterpretedOption element3 : this.uninterpretedOption) {
                    if (element3 != null) {
                        output.writeMessage(999, element3);
                    }
                }
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int dataCount;
            int dataSize;
            int size = super.computeSerializedSize();
            if (this.messageSetWireFormat) {
                size += CodedOutputByteBufferNano.computeBoolSize(1, this.messageSetWireFormat);
            }
            if (this.noStandardDescriptorAccessor) {
                size += CodedOutputByteBufferNano.computeBoolSize(2, this.noStandardDescriptorAccessor);
            }
            if (this.deprecated) {
                size += CodedOutputByteBufferNano.computeBoolSize(3, this.deprecated);
            }
            if (this.experimentalJavaMessageInterface != null && this.experimentalJavaMessageInterface.length > 0) {
                dataCount = 0;
                dataSize = 0;
                for (String element : this.experimentalJavaMessageInterface) {
                    if (element != null) {
                        dataCount++;
                        dataSize += CodedOutputByteBufferNano.computeStringSizeNoTag(element);
                    }
                }
                size = (size + dataSize) + (dataCount * 1);
            }
            if (this.experimentalJavaBuilderInterface != null && this.experimentalJavaBuilderInterface.length > 0) {
                dataCount = 0;
                dataSize = 0;
                for (String element2 : this.experimentalJavaBuilderInterface) {
                    if (element2 != null) {
                        dataCount++;
                        dataSize += CodedOutputByteBufferNano.computeStringSizeNoTag(element2);
                    }
                }
                size = (size + dataSize) + (dataCount * 1);
            }
            if (this.experimentalJavaInterfaceExtends != null && this.experimentalJavaInterfaceExtends.length > 0) {
                dataCount = 0;
                dataSize = 0;
                for (String element22 : this.experimentalJavaInterfaceExtends) {
                    if (element22 != null) {
                        dataCount++;
                        dataSize += CodedOutputByteBufferNano.computeStringSizeNoTag(element22);
                    }
                }
                size = (size + dataSize) + (dataCount * 1);
            }
            if (this.mapEntry) {
                size += CodedOutputByteBufferNano.computeBoolSize(7, this.mapEntry);
            }
            if (this.uninterpretedOption != null && this.uninterpretedOption.length > 0) {
                for (UninterpretedOption element3 : this.uninterpretedOption) {
                    if (element3 != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(999, element3);
                    }
                }
            }
            return size;
        }

        public MessageOptions mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                int arrayLength;
                int i;
                String[] newArray;
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_GET_LINK /*8*/:
                        this.messageSetWireFormat = input.readBool();
                        continue;
                    case LogSource.GMS_CORE_PEOPLE /*16*/:
                        this.noStandardDescriptorAccessor = input.readBool();
                        continue;
                    case LogSource.LB_C /*24*/:
                        this.deprecated = input.readBool();
                        continue;
                    case LogSource.NOVA /*34*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 34);
                        i = this.experimentalJavaMessageInterface == null ? 0 : this.experimentalJavaMessageInterface.length;
                        newArray = new String[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.experimentalJavaMessageInterface, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = input.readString();
                            input.readTag();
                            i++;
                        }
                        newArray[i] = input.readString();
                        this.experimentalJavaMessageInterface = newArray;
                        continue;
                    case LogSource.PHOTOS /*42*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 42);
                        i = this.experimentalJavaBuilderInterface == null ? 0 : this.experimentalJavaBuilderInterface.length;
                        newArray = new String[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.experimentalJavaBuilderInterface, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = input.readString();
                            input.readTag();
                            i++;
                        }
                        newArray[i] = input.readString();
                        this.experimentalJavaBuilderInterface = newArray;
                        continue;
                    case LogSource.HANGOUT_LOG_REQUEST /*50*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 50);
                        i = this.experimentalJavaInterfaceExtends == null ? 0 : this.experimentalJavaInterfaceExtends.length;
                        newArray = new String[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.experimentalJavaInterfaceExtends, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = input.readString();
                            input.readTag();
                            i++;
                        }
                        newArray[i] = input.readString();
                        this.experimentalJavaInterfaceExtends = newArray;
                        continue;
                    case LogSource.DOCS /*56*/:
                        this.mapEntry = input.readBool();
                        continue;
                    case 7994:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 7994);
                        if (this.uninterpretedOption == null) {
                            i = 0;
                        } else {
                            i = this.uninterpretedOption.length;
                        }
                        UninterpretedOption[] newArray2 = new UninterpretedOption[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.uninterpretedOption, 0, newArray2, 0, i);
                        }
                        while (i < newArray2.length - 1) {
                            newArray2[i] = new UninterpretedOption();
                            input.readMessage(newArray2[i]);
                            input.readTag();
                            i++;
                        }
                        newArray2[i] = new UninterpretedOption();
                        input.readMessage(newArray2[i]);
                        this.uninterpretedOption = newArray2;
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

        public static MessageOptions parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (MessageOptions) MessageNano.mergeFrom(new MessageOptions(), data);
        }

        public static MessageOptions parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new MessageOptions().mergeFrom(input);
        }
    }

    public static final class MethodDescriptorProto extends ExtendableMessageNano<MethodDescriptorProto> {
        private static volatile MethodDescriptorProto[] _emptyArray;
        public boolean clientStreaming;
        public String inputType;
        public String name;
        public MethodOptions options;
        public String outputType;
        public boolean serverStreaming;

        public static MethodDescriptorProto[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new MethodDescriptorProto[0];
                    }
                }
            }
            return _emptyArray;
        }

        public MethodDescriptorProto() {
            clear();
        }

        public MethodDescriptorProto clear() {
            this.name = BuildConfig.VERSION_NAME;
            this.inputType = BuildConfig.VERSION_NAME;
            this.outputType = BuildConfig.VERSION_NAME;
            this.options = null;
            this.clientStreaming = false;
            this.serverStreaming = false;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof MethodDescriptorProto)) {
                return false;
            }
            MethodDescriptorProto other = (MethodDescriptorProto) o;
            if (this.name == null) {
                if (other.name != null) {
                    return false;
                }
            } else if (!this.name.equals(other.name)) {
                return false;
            }
            if (this.inputType == null) {
                if (other.inputType != null) {
                    return false;
                }
            } else if (!this.inputType.equals(other.inputType)) {
                return false;
            }
            if (this.outputType == null) {
                if (other.outputType != null) {
                    return false;
                }
            } else if (!this.outputType.equals(other.outputType)) {
                return false;
            }
            if (this.options == null) {
                if (other.options != null) {
                    return false;
                }
            } else if (!this.options.equals(other.options)) {
                return false;
            }
            if (this.clientStreaming != other.clientStreaming || this.serverStreaming != other.serverStreaming) {
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
            int hashCode = (((((((((getClass().getName().hashCode() + 527) * 31) + (this.name == null ? 0 : this.name.hashCode())) * 31) + (this.inputType == null ? 0 : this.inputType.hashCode())) * 31) + (this.outputType == null ? 0 : this.outputType.hashCode())) * 31) + (this.options == null ? 0 : this.options.hashCode())) * 31;
            if (this.clientStreaming) {
                i = 1231;
            } else {
                i = 1237;
            }
            i = (hashCode + i) * 31;
            if (!this.serverStreaming) {
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
            if (!this.inputType.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(2, this.inputType);
            }
            if (!this.outputType.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(3, this.outputType);
            }
            if (this.options != null) {
                output.writeMessage(4, this.options);
            }
            if (this.clientStreaming) {
                output.writeBool(5, this.clientStreaming);
            }
            if (this.serverStreaming) {
                output.writeBool(6, this.serverStreaming);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (!this.name.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.name);
            }
            if (!this.inputType.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(2, this.inputType);
            }
            if (!this.outputType.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(3, this.outputType);
            }
            if (this.options != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(4, this.options);
            }
            if (this.clientStreaming) {
                size += CodedOutputByteBufferNano.computeBoolSize(5, this.clientStreaming);
            }
            if (this.serverStreaming) {
                return size + CodedOutputByteBufferNano.computeBoolSize(6, this.serverStreaming);
            }
            return size;
        }

        public MethodDescriptorProto mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        this.name = input.readString();
                        continue;
                    case LogSource.GOOGLE_ANALYTICS /*18*/:
                        this.inputType = input.readString();
                        continue;
                    case LogSource.ANDROID_CAMERA /*26*/:
                        this.outputType = input.readString();
                        continue;
                    case LogSource.NOVA /*34*/:
                        if (this.options == null) {
                            this.options = new MethodOptions();
                        }
                        input.readMessage(this.options);
                        continue;
                    case LogSource.COPRESENCE /*40*/:
                        this.clientStreaming = input.readBool();
                        continue;
                    case LogSource.BACKDROP /*48*/:
                        this.serverStreaming = input.readBool();
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

        public static MethodDescriptorProto parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (MethodDescriptorProto) MessageNano.mergeFrom(new MethodDescriptorProto(), data);
        }

        public static MethodDescriptorProto parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new MethodDescriptorProto().mergeFrom(input);
        }
    }

    public static final class MethodOptions extends ExtendableMessageNano<MethodOptions> {
        private static volatile MethodOptions[] _emptyArray;
        public int clientLogging;
        public boolean clientStreaming;
        public double deadline;
        public boolean deprecated;
        public boolean duplicateSuppression;
        public boolean endUserCredsRequested;
        public boolean failFast;
        public long legacyClientInitialTokens;
        public String legacyResultType;
        public long legacyServerInitialTokens;
        public String legacyStreamType;
        public int logLevel;
        public int protocol;
        public int requestFormat;
        public int responseFormat;
        public String securityLabel;
        public int securityLevel;
        public int serverLogging;
        public boolean serverStreaming;
        public String streamType;
        public UninterpretedOption[] uninterpretedOption;

        public interface Format {
            public static final int UNCOMPRESSED = 0;
            public static final int ZIPPY_COMPRESSED = 1;
        }

        public interface LogLevel {
            public static final int LOG_HEADER_AND_FILTERED_PAYLOAD = 3;
            public static final int LOG_HEADER_AND_NON_PRIVATE_PAYLOAD_INTERNAL = 2;
            public static final int LOG_HEADER_AND_PAYLOAD = 4;
            public static final int LOG_HEADER_ONLY = 1;
            public static final int LOG_NONE = 0;
        }

        public interface Protocol {
            public static final int TCP = 0;
            public static final int UDP = 1;
        }

        public interface SecurityLevel {
            public static final int INTEGRITY = 1;
            public static final int NONE = 0;
            public static final int PRIVACY_AND_INTEGRITY = 2;
            public static final int STRONG_PRIVACY_AND_INTEGRITY = 3;
        }

        public static MethodOptions[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new MethodOptions[0];
                    }
                }
            }
            return _emptyArray;
        }

        public MethodOptions() {
            clear();
        }

        public MethodOptions clear() {
            this.protocol = 0;
            this.deadline = -1.0d;
            this.duplicateSuppression = false;
            this.failFast = false;
            this.endUserCredsRequested = false;
            this.clientLogging = PeopleColumnBitmask.IS_BLOCKED;
            this.serverLogging = PeopleColumnBitmask.IS_BLOCKED;
            this.securityLevel = 0;
            this.responseFormat = 0;
            this.requestFormat = 0;
            this.streamType = BuildConfig.VERSION_NAME;
            this.securityLabel = BuildConfig.VERSION_NAME;
            this.clientStreaming = false;
            this.serverStreaming = false;
            this.legacyStreamType = BuildConfig.VERSION_NAME;
            this.legacyResultType = BuildConfig.VERSION_NAME;
            this.legacyClientInitialTokens = -1;
            this.legacyServerInitialTokens = -1;
            this.logLevel = 2;
            this.deprecated = false;
            this.uninterpretedOption = UninterpretedOption.emptyArray();
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof MethodOptions)) {
                return false;
            }
            MethodOptions other = (MethodOptions) o;
            if (this.protocol != other.protocol || Double.doubleToLongBits(this.deadline) != Double.doubleToLongBits(other.deadline) || this.duplicateSuppression != other.duplicateSuppression || this.failFast != other.failFast || this.endUserCredsRequested != other.endUserCredsRequested || this.clientLogging != other.clientLogging || this.serverLogging != other.serverLogging || this.securityLevel != other.securityLevel || this.responseFormat != other.responseFormat || this.requestFormat != other.requestFormat) {
                return false;
            }
            if (this.streamType == null) {
                if (other.streamType != null) {
                    return false;
                }
            } else if (!this.streamType.equals(other.streamType)) {
                return false;
            }
            if (this.securityLabel == null) {
                if (other.securityLabel != null) {
                    return false;
                }
            } else if (!this.securityLabel.equals(other.securityLabel)) {
                return false;
            }
            if (this.clientStreaming != other.clientStreaming || this.serverStreaming != other.serverStreaming) {
                return false;
            }
            if (this.legacyStreamType == null) {
                if (other.legacyStreamType != null) {
                    return false;
                }
            } else if (!this.legacyStreamType.equals(other.legacyStreamType)) {
                return false;
            }
            if (this.legacyResultType == null) {
                if (other.legacyResultType != null) {
                    return false;
                }
            } else if (!this.legacyResultType.equals(other.legacyResultType)) {
                return false;
            }
            if (this.legacyClientInitialTokens != other.legacyClientInitialTokens || this.legacyServerInitialTokens != other.legacyServerInitialTokens || this.logLevel != other.logLevel || this.deprecated != other.deprecated || !InternalNano.equals(this.uninterpretedOption, other.uninterpretedOption)) {
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
            int result = ((getClass().getName().hashCode() + 527) * 31) + this.protocol;
            long v = Double.doubleToLongBits(this.deadline);
            int i4 = ((((result * 31) + ((int) ((v >>> 32) ^ v))) * 31) + (this.duplicateSuppression ? 1231 : 1237)) * 31;
            if (this.failFast) {
                i = 1231;
            } else {
                i = 1237;
            }
            i4 = (i4 + i) * 31;
            if (this.endUserCredsRequested) {
                i = 1231;
            } else {
                i = 1237;
            }
            i4 = (((((((((((((((i4 + i) * 31) + this.clientLogging) * 31) + this.serverLogging) * 31) + this.securityLevel) * 31) + this.responseFormat) * 31) + this.requestFormat) * 31) + (this.streamType == null ? 0 : this.streamType.hashCode())) * 31) + (this.securityLabel == null ? 0 : this.securityLabel.hashCode())) * 31;
            if (this.clientStreaming) {
                i = 1231;
            } else {
                i = 1237;
            }
            i4 = (i4 + i) * 31;
            if (this.serverStreaming) {
                i = 1231;
            } else {
                i = 1237;
            }
            i = (((((((((((i4 + i) * 31) + (this.legacyStreamType == null ? 0 : this.legacyStreamType.hashCode())) * 31) + (this.legacyResultType == null ? 0 : this.legacyResultType.hashCode())) * 31) + ((int) (this.legacyClientInitialTokens ^ (this.legacyClientInitialTokens >>> 32)))) * 31) + ((int) (this.legacyServerInitialTokens ^ (this.legacyServerInitialTokens >>> 32)))) * 31) + this.logLevel) * 31;
            if (!this.deprecated) {
                i3 = 1237;
            }
            i = (((i + i3) * 31) + InternalNano.hashCode(this.uninterpretedOption)) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i2 = this.unknownFieldData.hashCode();
            }
            return i + i2;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.protocol != 0) {
                output.writeInt32(7, this.protocol);
            }
            if (Double.doubleToLongBits(this.deadline) != Double.doubleToLongBits(-1.0d)) {
                output.writeDouble(8, this.deadline);
            }
            if (this.duplicateSuppression) {
                output.writeBool(9, this.duplicateSuppression);
            }
            if (this.failFast) {
                output.writeBool(10, this.failFast);
            }
            if (this.clientLogging != PeopleColumnBitmask.IS_BLOCKED) {
                output.writeSInt32(11, this.clientLogging);
            }
            if (this.serverLogging != PeopleColumnBitmask.IS_BLOCKED) {
                output.writeSInt32(12, this.serverLogging);
            }
            if (this.securityLevel != 0) {
                output.writeInt32(13, this.securityLevel);
            }
            if (this.responseFormat != 0) {
                output.writeInt32(15, this.responseFormat);
            }
            if (this.requestFormat != 0) {
                output.writeInt32(17, this.requestFormat);
            }
            if (!this.streamType.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(18, this.streamType);
            }
            if (!this.securityLabel.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(19, this.securityLabel);
            }
            if (this.clientStreaming) {
                output.writeBool(20, this.clientStreaming);
            }
            if (this.serverStreaming) {
                output.writeBool(21, this.serverStreaming);
            }
            if (!this.legacyStreamType.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(22, this.legacyStreamType);
            }
            if (!this.legacyResultType.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(23, this.legacyResultType);
            }
            if (this.legacyClientInitialTokens != -1) {
                output.writeInt64(24, this.legacyClientInitialTokens);
            }
            if (this.legacyServerInitialTokens != -1) {
                output.writeInt64(25, this.legacyServerInitialTokens);
            }
            if (this.endUserCredsRequested) {
                output.writeBool(26, this.endUserCredsRequested);
            }
            if (this.logLevel != 2) {
                output.writeInt32(27, this.logLevel);
            }
            if (this.deprecated) {
                output.writeBool(33, this.deprecated);
            }
            if (this.uninterpretedOption != null && this.uninterpretedOption.length > 0) {
                for (UninterpretedOption element : this.uninterpretedOption) {
                    if (element != null) {
                        output.writeMessage(999, element);
                    }
                }
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.protocol != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(7, this.protocol);
            }
            if (Double.doubleToLongBits(this.deadline) != Double.doubleToLongBits(-1.0d)) {
                size += CodedOutputByteBufferNano.computeDoubleSize(8, this.deadline);
            }
            if (this.duplicateSuppression) {
                size += CodedOutputByteBufferNano.computeBoolSize(9, this.duplicateSuppression);
            }
            if (this.failFast) {
                size += CodedOutputByteBufferNano.computeBoolSize(10, this.failFast);
            }
            if (this.clientLogging != PeopleColumnBitmask.IS_BLOCKED) {
                size += CodedOutputByteBufferNano.computeSInt32Size(11, this.clientLogging);
            }
            if (this.serverLogging != PeopleColumnBitmask.IS_BLOCKED) {
                size += CodedOutputByteBufferNano.computeSInt32Size(12, this.serverLogging);
            }
            if (this.securityLevel != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(13, this.securityLevel);
            }
            if (this.responseFormat != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(15, this.responseFormat);
            }
            if (this.requestFormat != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(17, this.requestFormat);
            }
            if (!this.streamType.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(18, this.streamType);
            }
            if (!this.securityLabel.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(19, this.securityLabel);
            }
            if (this.clientStreaming) {
                size += CodedOutputByteBufferNano.computeBoolSize(20, this.clientStreaming);
            }
            if (this.serverStreaming) {
                size += CodedOutputByteBufferNano.computeBoolSize(21, this.serverStreaming);
            }
            if (!this.legacyStreamType.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(22, this.legacyStreamType);
            }
            if (!this.legacyResultType.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(23, this.legacyResultType);
            }
            if (this.legacyClientInitialTokens != -1) {
                size += CodedOutputByteBufferNano.computeInt64Size(24, this.legacyClientInitialTokens);
            }
            if (this.legacyServerInitialTokens != -1) {
                size += CodedOutputByteBufferNano.computeInt64Size(25, this.legacyServerInitialTokens);
            }
            if (this.endUserCredsRequested) {
                size += CodedOutputByteBufferNano.computeBoolSize(26, this.endUserCredsRequested);
            }
            if (this.logLevel != 2) {
                size += CodedOutputByteBufferNano.computeInt32Size(27, this.logLevel);
            }
            if (this.deprecated) {
                size += CodedOutputByteBufferNano.computeBoolSize(33, this.deprecated);
            }
            if (this.uninterpretedOption != null && this.uninterpretedOption.length > 0) {
                for (UninterpretedOption element : this.uninterpretedOption) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(999, element);
                    }
                }
            }
            return size;
        }

        public MethodOptions mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                int value;
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case LogSource.DOCS /*56*/:
                        value = input.readInt32();
                        switch (value) {
                            case Action.UNKNOWN /*0*/:
                            case TimeSelection.Type.TEMPORARY /*1*/:
                                this.protocol = value;
                                break;
                            default:
                                continue;
                        }
                    case LogSource.WEB_STORE /*65*/:
                        this.deadline = input.readDouble();
                        continue;
                    case LogSource.JUSTSPEAK /*72*/:
                        this.duplicateSuppression = input.readBool();
                        continue;
                    case LogSource.CRONET_ANDROID_GSA /*80*/:
                        this.failFast = input.readBool();
                        continue;
                    case LogSource.EMERGENCY_ASSIST /*88*/:
                        this.clientLogging = input.readSInt32();
                        continue;
                    case LogSource.GCM_COUNTERS /*96*/:
                        this.serverLogging = input.readSInt32();
                        continue;
                    case LogSource.MOBILESDK_CLIENT /*104*/:
                        value = input.readInt32();
                        switch (value) {
                            case Action.UNKNOWN /*0*/:
                            case TimeSelection.Type.TEMPORARY /*1*/:
                            case TimeSelection.Type.INDEFINITELY /*2*/:
                            case TimeSelection.Type.CUSTOM /*3*/:
                                this.securityLevel = value;
                                break;
                            default:
                                continue;
                        }
                    case LogSource.FLYDROID /*120*/:
                        value = input.readInt32();
                        switch (value) {
                            case Action.UNKNOWN /*0*/:
                            case TimeSelection.Type.TEMPORARY /*1*/:
                                this.responseFormat = value;
                                break;
                            default:
                                continue;
                        }
                    case LogSource.SAMPLE_SHM /*136*/:
                        value = input.readInt32();
                        switch (value) {
                            case Action.UNKNOWN /*0*/:
                            case TimeSelection.Type.TEMPORARY /*1*/:
                                this.requestFormat = value;
                                break;
                            default:
                                continue;
                        }
                    case 146:
                        this.streamType = input.readString();
                        continue;
                    case 154:
                        this.securityLabel = input.readString();
                        continue;
                    case 160:
                        this.clientStreaming = input.readBool();
                        continue;
                    case 168:
                        this.serverStreaming = input.readBool();
                        continue;
                    case 178:
                        this.legacyStreamType = input.readString();
                        continue;
                    case 186:
                        this.legacyResultType = input.readString();
                        continue;
                    case 192:
                        this.legacyClientInitialTokens = input.readInt64();
                        continue;
                    case Importance.IMPORTANCE_VISIBLE /*200*/:
                        this.legacyServerInitialTokens = input.readInt64();
                        continue;
                    case 208:
                        this.endUserCredsRequested = input.readBool();
                        continue;
                    case 216:
                        value = input.readInt32();
                        switch (value) {
                            case Action.UNKNOWN /*0*/:
                            case TimeSelection.Type.TEMPORARY /*1*/:
                            case TimeSelection.Type.INDEFINITELY /*2*/:
                            case TimeSelection.Type.CUSTOM /*3*/:
                            case OvenFreshResult.NO_ACCOUNTS /*4*/:
                                this.logLevel = value;
                                break;
                            default:
                                continue;
                        }
                    case 264:
                        this.deprecated = input.readBool();
                        continue;
                    case 7994:
                        int i;
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 7994);
                        if (this.uninterpretedOption == null) {
                            i = 0;
                        } else {
                            i = this.uninterpretedOption.length;
                        }
                        UninterpretedOption[] newArray = new UninterpretedOption[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.uninterpretedOption, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new UninterpretedOption();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i++;
                        }
                        newArray[i] = new UninterpretedOption();
                        input.readMessage(newArray[i]);
                        this.uninterpretedOption = newArray;
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

        public static MethodOptions parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (MethodOptions) MessageNano.mergeFrom(new MethodOptions(), data);
        }

        public static MethodOptions parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new MethodOptions().mergeFrom(input);
        }
    }

    public static final class OneofDescriptorProto extends ExtendableMessageNano<OneofDescriptorProto> {
        private static volatile OneofDescriptorProto[] _emptyArray;
        public String name;

        public static OneofDescriptorProto[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new OneofDescriptorProto[0];
                    }
                }
            }
            return _emptyArray;
        }

        public OneofDescriptorProto() {
            clear();
        }

        public OneofDescriptorProto clear() {
            this.name = BuildConfig.VERSION_NAME;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof OneofDescriptorProto)) {
                return false;
            }
            OneofDescriptorProto other = (OneofDescriptorProto) o;
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

        public OneofDescriptorProto mergeFrom(CodedInputByteBufferNano input) throws IOException {
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

        public static OneofDescriptorProto parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (OneofDescriptorProto) MessageNano.mergeFrom(new OneofDescriptorProto(), data);
        }

        public static OneofDescriptorProto parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new OneofDescriptorProto().mergeFrom(input);
        }
    }

    public static final class ServiceDescriptorProto extends ExtendableMessageNano<ServiceDescriptorProto> {
        private static volatile ServiceDescriptorProto[] _emptyArray;
        public MethodDescriptorProto[] method;
        public String name;
        public ServiceOptions options;
        public StreamDescriptorProto[] stream;

        public static ServiceDescriptorProto[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new ServiceDescriptorProto[0];
                    }
                }
            }
            return _emptyArray;
        }

        public ServiceDescriptorProto() {
            clear();
        }

        public ServiceDescriptorProto clear() {
            this.name = BuildConfig.VERSION_NAME;
            this.method = MethodDescriptorProto.emptyArray();
            this.stream = StreamDescriptorProto.emptyArray();
            this.options = null;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof ServiceDescriptorProto)) {
                return false;
            }
            ServiceDescriptorProto other = (ServiceDescriptorProto) o;
            if (this.name == null) {
                if (other.name != null) {
                    return false;
                }
            } else if (!this.name.equals(other.name)) {
                return false;
            }
            if (!InternalNano.equals(this.method, other.method) || !InternalNano.equals(this.stream, other.stream)) {
                return false;
            }
            if (this.options == null) {
                if (other.options != null) {
                    return false;
                }
            } else if (!this.options.equals(other.options)) {
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
            int hashCode = (((((((((getClass().getName().hashCode() + 527) * 31) + (this.name == null ? 0 : this.name.hashCode())) * 31) + InternalNano.hashCode(this.method)) * 31) + InternalNano.hashCode(this.stream)) * 31) + (this.options == null ? 0 : this.options.hashCode())) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (!this.name.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(1, this.name);
            }
            if (this.method != null && this.method.length > 0) {
                for (MethodDescriptorProto element : this.method) {
                    if (element != null) {
                        output.writeMessage(2, element);
                    }
                }
            }
            if (this.options != null) {
                output.writeMessage(3, this.options);
            }
            if (this.stream != null && this.stream.length > 0) {
                for (StreamDescriptorProto element2 : this.stream) {
                    if (element2 != null) {
                        output.writeMessage(4, element2);
                    }
                }
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (!this.name.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.name);
            }
            if (this.method != null && this.method.length > 0) {
                for (MethodDescriptorProto element : this.method) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(2, element);
                    }
                }
            }
            if (this.options != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(3, this.options);
            }
            if (this.stream != null && this.stream.length > 0) {
                for (StreamDescriptorProto element2 : this.stream) {
                    if (element2 != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(4, element2);
                    }
                }
            }
            return size;
        }

        public ServiceDescriptorProto mergeFrom(CodedInputByteBufferNano input) throws IOException {
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
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 18);
                        if (this.method == null) {
                            i = 0;
                        } else {
                            i = this.method.length;
                        }
                        MethodDescriptorProto[] newArray = new MethodDescriptorProto[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.method, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new MethodDescriptorProto();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i++;
                        }
                        newArray[i] = new MethodDescriptorProto();
                        input.readMessage(newArray[i]);
                        this.method = newArray;
                        continue;
                    case LogSource.ANDROID_CAMERA /*26*/:
                        if (this.options == null) {
                            this.options = new ServiceOptions();
                        }
                        input.readMessage(this.options);
                        continue;
                    case LogSource.NOVA /*34*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 34);
                        if (this.stream == null) {
                            i = 0;
                        } else {
                            i = this.stream.length;
                        }
                        StreamDescriptorProto[] newArray2 = new StreamDescriptorProto[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.stream, 0, newArray2, 0, i);
                        }
                        while (i < newArray2.length - 1) {
                            newArray2[i] = new StreamDescriptorProto();
                            input.readMessage(newArray2[i]);
                            input.readTag();
                            i++;
                        }
                        newArray2[i] = new StreamDescriptorProto();
                        input.readMessage(newArray2[i]);
                        this.stream = newArray2;
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

        public static ServiceDescriptorProto parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (ServiceDescriptorProto) MessageNano.mergeFrom(new ServiceDescriptorProto(), data);
        }

        public static ServiceDescriptorProto parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new ServiceDescriptorProto().mergeFrom(input);
        }
    }

    public static final class ServiceOptions extends ExtendableMessageNano<ServiceOptions> {
        private static volatile ServiceOptions[] _emptyArray;
        public boolean deprecated;
        public double failureDetectionDelay;
        public boolean multicastStub;
        public UninterpretedOption[] uninterpretedOption;

        public static ServiceOptions[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new ServiceOptions[0];
                    }
                }
            }
            return _emptyArray;
        }

        public ServiceOptions() {
            clear();
        }

        public ServiceOptions clear() {
            this.multicastStub = false;
            this.failureDetectionDelay = -1.0d;
            this.deprecated = false;
            this.uninterpretedOption = UninterpretedOption.emptyArray();
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof ServiceOptions)) {
                return false;
            }
            ServiceOptions other = (ServiceOptions) o;
            if (this.multicastStub != other.multicastStub || Double.doubleToLongBits(this.failureDetectionDelay) != Double.doubleToLongBits(other.failureDetectionDelay) || this.deprecated != other.deprecated || !InternalNano.equals(this.uninterpretedOption, other.uninterpretedOption)) {
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
            int hashCode = (getClass().getName().hashCode() + 527) * 31;
            if (this.multicastStub) {
                i = 1231;
            } else {
                i = 1237;
            }
            int result = hashCode + i;
            long v = Double.doubleToLongBits(this.failureDetectionDelay);
            i = ((result * 31) + ((int) ((v >>> 32) ^ v))) * 31;
            if (!this.deprecated) {
                i2 = 1237;
            }
            i2 = (((i + i2) * 31) + InternalNano.hashCode(this.uninterpretedOption)) * 31;
            i = (this.unknownFieldData == null || this.unknownFieldData.isEmpty()) ? 0 : this.unknownFieldData.hashCode();
            return i2 + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (Double.doubleToLongBits(this.failureDetectionDelay) != Double.doubleToLongBits(-1.0d)) {
                output.writeDouble(16, this.failureDetectionDelay);
            }
            if (this.multicastStub) {
                output.writeBool(20, this.multicastStub);
            }
            if (this.deprecated) {
                output.writeBool(33, this.deprecated);
            }
            if (this.uninterpretedOption != null && this.uninterpretedOption.length > 0) {
                for (UninterpretedOption element : this.uninterpretedOption) {
                    if (element != null) {
                        output.writeMessage(999, element);
                    }
                }
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (Double.doubleToLongBits(this.failureDetectionDelay) != Double.doubleToLongBits(-1.0d)) {
                size += CodedOutputByteBufferNano.computeDoubleSize(16, this.failureDetectionDelay);
            }
            if (this.multicastStub) {
                size += CodedOutputByteBufferNano.computeBoolSize(20, this.multicastStub);
            }
            if (this.deprecated) {
                size += CodedOutputByteBufferNano.computeBoolSize(33, this.deprecated);
            }
            if (this.uninterpretedOption != null && this.uninterpretedOption.length > 0) {
                for (UninterpretedOption element : this.uninterpretedOption) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(999, element);
                    }
                }
            }
            return size;
        }

        public ServiceOptions mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case LogSource.GMM_BRIIM_COUNTERS /*129*/:
                        this.failureDetectionDelay = input.readDouble();
                        continue;
                    case 160:
                        this.multicastStub = input.readBool();
                        continue;
                    case 264:
                        this.deprecated = input.readBool();
                        continue;
                    case 7994:
                        int i;
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 7994);
                        if (this.uninterpretedOption == null) {
                            i = 0;
                        } else {
                            i = this.uninterpretedOption.length;
                        }
                        UninterpretedOption[] newArray = new UninterpretedOption[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.uninterpretedOption, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new UninterpretedOption();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i++;
                        }
                        newArray[i] = new UninterpretedOption();
                        input.readMessage(newArray[i]);
                        this.uninterpretedOption = newArray;
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

        public static ServiceOptions parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (ServiceOptions) MessageNano.mergeFrom(new ServiceOptions(), data);
        }

        public static ServiceOptions parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new ServiceOptions().mergeFrom(input);
        }
    }

    public static final class SourceCodeInfo extends ExtendableMessageNano<SourceCodeInfo> {
        private static volatile SourceCodeInfo[] _emptyArray;
        public Location[] location;

        public static final class Location extends ExtendableMessageNano<Location> {
            private static volatile Location[] _emptyArray;
            public String leadingComments;
            public String[] leadingDetachedComments;
            public int[] path;
            public int[] span;
            public String trailingComments;

            public static Location[] emptyArray() {
                if (_emptyArray == null) {
                    synchronized (InternalNano.LAZY_INIT_LOCK) {
                        if (_emptyArray == null) {
                            _emptyArray = new Location[0];
                        }
                    }
                }
                return _emptyArray;
            }

            public Location() {
                clear();
            }

            public Location clear() {
                this.path = WireFormatNano.EMPTY_INT_ARRAY;
                this.span = WireFormatNano.EMPTY_INT_ARRAY;
                this.leadingComments = BuildConfig.VERSION_NAME;
                this.trailingComments = BuildConfig.VERSION_NAME;
                this.leadingDetachedComments = WireFormatNano.EMPTY_STRING_ARRAY;
                this.unknownFieldData = null;
                this.cachedSize = -1;
                return this;
            }

            public boolean equals(Object o) {
                if (o == this) {
                    return true;
                }
                if (!(o instanceof Location)) {
                    return false;
                }
                Location other = (Location) o;
                if (!InternalNano.equals(this.path, other.path) || !InternalNano.equals(this.span, other.span)) {
                    return false;
                }
                if (this.leadingComments == null) {
                    if (other.leadingComments != null) {
                        return false;
                    }
                } else if (!this.leadingComments.equals(other.leadingComments)) {
                    return false;
                }
                if (this.trailingComments == null) {
                    if (other.trailingComments != null) {
                        return false;
                    }
                } else if (!this.trailingComments.equals(other.trailingComments)) {
                    return false;
                }
                if (!InternalNano.equals(this.leadingDetachedComments, other.leadingDetachedComments)) {
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
                int hashCode = (((((((((((getClass().getName().hashCode() + 527) * 31) + InternalNano.hashCode(this.path)) * 31) + InternalNano.hashCode(this.span)) * 31) + (this.leadingComments == null ? 0 : this.leadingComments.hashCode())) * 31) + (this.trailingComments == null ? 0 : this.trailingComments.hashCode())) * 31) + InternalNano.hashCode(this.leadingDetachedComments)) * 31;
                if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                    i = this.unknownFieldData.hashCode();
                }
                return hashCode + i;
            }

            public void writeTo(CodedOutputByteBufferNano output) throws IOException {
                int dataSize;
                if (this.path != null && this.path.length > 0) {
                    dataSize = 0;
                    for (int element : this.path) {
                        dataSize += CodedOutputByteBufferNano.computeInt32SizeNoTag(element);
                    }
                    output.writeRawVarint32(10);
                    output.writeRawVarint32(dataSize);
                    for (int writeInt32NoTag : this.path) {
                        output.writeInt32NoTag(writeInt32NoTag);
                    }
                }
                if (this.span != null && this.span.length > 0) {
                    dataSize = 0;
                    for (int element2 : this.span) {
                        dataSize += CodedOutputByteBufferNano.computeInt32SizeNoTag(element2);
                    }
                    output.writeRawVarint32(18);
                    output.writeRawVarint32(dataSize);
                    for (int writeInt32NoTag2 : this.span) {
                        output.writeInt32NoTag(writeInt32NoTag2);
                    }
                }
                if (!this.leadingComments.equals(BuildConfig.VERSION_NAME)) {
                    output.writeString(3, this.leadingComments);
                }
                if (!this.trailingComments.equals(BuildConfig.VERSION_NAME)) {
                    output.writeString(4, this.trailingComments);
                }
                if (this.leadingDetachedComments != null && this.leadingDetachedComments.length > 0) {
                    for (String element3 : this.leadingDetachedComments) {
                        if (element3 != null) {
                            output.writeString(6, element3);
                        }
                    }
                }
                super.writeTo(output);
            }

            protected int computeSerializedSize() {
                int dataSize;
                int size = super.computeSerializedSize();
                if (this.path != null && this.path.length > 0) {
                    dataSize = 0;
                    for (int element : this.path) {
                        dataSize += CodedOutputByteBufferNano.computeInt32SizeNoTag(element);
                    }
                    size = ((size + dataSize) + 1) + CodedOutputByteBufferNano.computeRawVarint32Size(dataSize);
                }
                if (this.span != null && this.span.length > 0) {
                    dataSize = 0;
                    for (int element2 : this.span) {
                        dataSize += CodedOutputByteBufferNano.computeInt32SizeNoTag(element2);
                    }
                    size = ((size + dataSize) + 1) + CodedOutputByteBufferNano.computeRawVarint32Size(dataSize);
                }
                if (!this.leadingComments.equals(BuildConfig.VERSION_NAME)) {
                    size += CodedOutputByteBufferNano.computeStringSize(3, this.leadingComments);
                }
                if (!this.trailingComments.equals(BuildConfig.VERSION_NAME)) {
                    size += CodedOutputByteBufferNano.computeStringSize(4, this.trailingComments);
                }
                if (this.leadingDetachedComments == null || this.leadingDetachedComments.length <= 0) {
                    return size;
                }
                int dataCount = 0;
                dataSize = 0;
                for (String element3 : this.leadingDetachedComments) {
                    if (element3 != null) {
                        dataCount++;
                        dataSize += CodedOutputByteBufferNano.computeStringSizeNoTag(element3);
                    }
                }
                return (size + dataSize) + (dataCount * 1);
            }

            public Location mergeFrom(CodedInputByteBufferNano input) throws IOException {
                while (true) {
                    int tag = input.readTag();
                    int arrayLength;
                    int i;
                    int[] newArray;
                    int limit;
                    int startPos;
                    switch (tag) {
                        case Action.UNKNOWN /*0*/:
                            break;
                        case Type.TAP_GET_LINK /*8*/:
                            arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 8);
                            i = this.path == null ? 0 : this.path.length;
                            newArray = new int[(i + arrayLength)];
                            if (i != 0) {
                                System.arraycopy(this.path, 0, newArray, 0, i);
                            }
                            while (i < newArray.length - 1) {
                                newArray[i] = input.readInt32();
                                input.readTag();
                                i++;
                            }
                            newArray[i] = input.readInt32();
                            this.path = newArray;
                            continue;
                        case Type.TAP_ABOUT /*10*/:
                            limit = input.pushLimit(input.readRawVarint32());
                            arrayLength = 0;
                            startPos = input.getPosition();
                            while (input.getBytesUntilLimit() > 0) {
                                input.readInt32();
                                arrayLength++;
                            }
                            input.rewindToPosition(startPos);
                            i = this.path == null ? 0 : this.path.length;
                            newArray = new int[(i + arrayLength)];
                            if (i != 0) {
                                System.arraycopy(this.path, 0, newArray, 0, i);
                            }
                            while (i < newArray.length) {
                                newArray[i] = input.readInt32();
                                i++;
                            }
                            this.path = newArray;
                            input.popLimit(limit);
                            continue;
                        case LogSource.GMS_CORE_PEOPLE /*16*/:
                            arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 16);
                            i = this.span == null ? 0 : this.span.length;
                            newArray = new int[(i + arrayLength)];
                            if (i != 0) {
                                System.arraycopy(this.span, 0, newArray, 0, i);
                            }
                            while (i < newArray.length - 1) {
                                newArray[i] = input.readInt32();
                                input.readTag();
                                i++;
                            }
                            newArray[i] = input.readInt32();
                            this.span = newArray;
                            continue;
                        case LogSource.GOOGLE_ANALYTICS /*18*/:
                            limit = input.pushLimit(input.readRawVarint32());
                            arrayLength = 0;
                            startPos = input.getPosition();
                            while (input.getBytesUntilLimit() > 0) {
                                input.readInt32();
                                arrayLength++;
                            }
                            input.rewindToPosition(startPos);
                            i = this.span == null ? 0 : this.span.length;
                            newArray = new int[(i + arrayLength)];
                            if (i != 0) {
                                System.arraycopy(this.span, 0, newArray, 0, i);
                            }
                            while (i < newArray.length) {
                                newArray[i] = input.readInt32();
                                i++;
                            }
                            this.span = newArray;
                            input.popLimit(limit);
                            continue;
                        case LogSource.ANDROID_CAMERA /*26*/:
                            this.leadingComments = input.readString();
                            continue;
                        case LogSource.NOVA /*34*/:
                            this.trailingComments = input.readString();
                            continue;
                        case LogSource.HANGOUT_LOG_REQUEST /*50*/:
                            arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 50);
                            i = this.leadingDetachedComments == null ? 0 : this.leadingDetachedComments.length;
                            String[] newArray2 = new String[(i + arrayLength)];
                            if (i != 0) {
                                System.arraycopy(this.leadingDetachedComments, 0, newArray2, 0, i);
                            }
                            while (i < newArray2.length - 1) {
                                newArray2[i] = input.readString();
                                input.readTag();
                                i++;
                            }
                            newArray2[i] = input.readString();
                            this.leadingDetachedComments = newArray2;
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

            public static Location parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
                return (Location) MessageNano.mergeFrom(new Location(), data);
            }

            public static Location parseFrom(CodedInputByteBufferNano input) throws IOException {
                return new Location().mergeFrom(input);
            }
        }

        public static SourceCodeInfo[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new SourceCodeInfo[0];
                    }
                }
            }
            return _emptyArray;
        }

        public SourceCodeInfo() {
            clear();
        }

        public SourceCodeInfo clear() {
            this.location = Location.emptyArray();
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof SourceCodeInfo)) {
                return false;
            }
            SourceCodeInfo other = (SourceCodeInfo) o;
            if (!InternalNano.equals(this.location, other.location)) {
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
            int hashCode = (((getClass().getName().hashCode() + 527) * 31) + InternalNano.hashCode(this.location)) * 31;
            int hashCode2 = (this.unknownFieldData == null || this.unknownFieldData.isEmpty()) ? 0 : this.unknownFieldData.hashCode();
            return hashCode + hashCode2;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.location != null && this.location.length > 0) {
                for (Location element : this.location) {
                    if (element != null) {
                        output.writeMessage(1, element);
                    }
                }
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.location != null && this.location.length > 0) {
                for (Location element : this.location) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(1, element);
                    }
                }
            }
            return size;
        }

        public SourceCodeInfo mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        int i;
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 10);
                        if (this.location == null) {
                            i = 0;
                        } else {
                            i = this.location.length;
                        }
                        Location[] newArray = new Location[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.location, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new Location();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i++;
                        }
                        newArray[i] = new Location();
                        input.readMessage(newArray[i]);
                        this.location = newArray;
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

        public static SourceCodeInfo parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (SourceCodeInfo) MessageNano.mergeFrom(new SourceCodeInfo(), data);
        }

        public static SourceCodeInfo parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new SourceCodeInfo().mergeFrom(input);
        }
    }

    public static final class StreamDescriptorProto extends ExtendableMessageNano<StreamDescriptorProto> {
        private static volatile StreamDescriptorProto[] _emptyArray;
        public String clientMessageType;
        public String name;
        public StreamOptions options;
        public String serverMessageType;

        public static StreamDescriptorProto[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new StreamDescriptorProto[0];
                    }
                }
            }
            return _emptyArray;
        }

        public StreamDescriptorProto() {
            clear();
        }

        public StreamDescriptorProto clear() {
            this.name = BuildConfig.VERSION_NAME;
            this.clientMessageType = BuildConfig.VERSION_NAME;
            this.serverMessageType = BuildConfig.VERSION_NAME;
            this.options = null;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof StreamDescriptorProto)) {
                return false;
            }
            StreamDescriptorProto other = (StreamDescriptorProto) o;
            if (this.name == null) {
                if (other.name != null) {
                    return false;
                }
            } else if (!this.name.equals(other.name)) {
                return false;
            }
            if (this.clientMessageType == null) {
                if (other.clientMessageType != null) {
                    return false;
                }
            } else if (!this.clientMessageType.equals(other.clientMessageType)) {
                return false;
            }
            if (this.serverMessageType == null) {
                if (other.serverMessageType != null) {
                    return false;
                }
            } else if (!this.serverMessageType.equals(other.serverMessageType)) {
                return false;
            }
            if (this.options == null) {
                if (other.options != null) {
                    return false;
                }
            } else if (!this.options.equals(other.options)) {
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
            int hashCode = (((((((((getClass().getName().hashCode() + 527) * 31) + (this.name == null ? 0 : this.name.hashCode())) * 31) + (this.clientMessageType == null ? 0 : this.clientMessageType.hashCode())) * 31) + (this.serverMessageType == null ? 0 : this.serverMessageType.hashCode())) * 31) + (this.options == null ? 0 : this.options.hashCode())) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (!this.name.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(1, this.name);
            }
            if (!this.clientMessageType.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(2, this.clientMessageType);
            }
            if (!this.serverMessageType.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(3, this.serverMessageType);
            }
            if (this.options != null) {
                output.writeMessage(4, this.options);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (!this.name.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.name);
            }
            if (!this.clientMessageType.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(2, this.clientMessageType);
            }
            if (!this.serverMessageType.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(3, this.serverMessageType);
            }
            if (this.options != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(4, this.options);
            }
            return size;
        }

        public StreamDescriptorProto mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        this.name = input.readString();
                        continue;
                    case LogSource.GOOGLE_ANALYTICS /*18*/:
                        this.clientMessageType = input.readString();
                        continue;
                    case LogSource.ANDROID_CAMERA /*26*/:
                        this.serverMessageType = input.readString();
                        continue;
                    case LogSource.NOVA /*34*/:
                        if (this.options == null) {
                            this.options = new StreamOptions();
                        }
                        input.readMessage(this.options);
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

        public static StreamDescriptorProto parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (StreamDescriptorProto) MessageNano.mergeFrom(new StreamDescriptorProto(), data);
        }

        public static StreamDescriptorProto parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new StreamDescriptorProto().mergeFrom(input);
        }
    }

    public static final class StreamOptions extends ExtendableMessageNano<StreamOptions> {
        private static volatile StreamOptions[] _emptyArray;
        public long clientInitialTokens;
        public int clientLogging;
        public double deadline;
        public boolean deprecated;
        public boolean endUserCredsRequested;
        public boolean failFast;
        public int logLevel;
        public String securityLabel;
        public int securityLevel;
        public long serverInitialTokens;
        public int serverLogging;
        public int tokenUnit;
        public UninterpretedOption[] uninterpretedOption;

        public interface TokenUnit {
            public static final int BYTE = 1;
            public static final int MESSAGE = 0;
        }

        public static StreamOptions[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new StreamOptions[0];
                    }
                }
            }
            return _emptyArray;
        }

        public StreamOptions() {
            clear();
        }

        public StreamOptions clear() {
            this.clientInitialTokens = -1;
            this.serverInitialTokens = -1;
            this.tokenUnit = 0;
            this.securityLevel = 0;
            this.securityLabel = BuildConfig.VERSION_NAME;
            this.clientLogging = PeopleColumnBitmask.IS_BLOCKED;
            this.serverLogging = PeopleColumnBitmask.IS_BLOCKED;
            this.deadline = -1.0d;
            this.failFast = false;
            this.endUserCredsRequested = false;
            this.logLevel = 2;
            this.deprecated = false;
            this.uninterpretedOption = UninterpretedOption.emptyArray();
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof StreamOptions)) {
                return false;
            }
            StreamOptions other = (StreamOptions) o;
            if (this.clientInitialTokens != other.clientInitialTokens || this.serverInitialTokens != other.serverInitialTokens || this.tokenUnit != other.tokenUnit || this.securityLevel != other.securityLevel) {
                return false;
            }
            if (this.securityLabel == null) {
                if (other.securityLabel != null) {
                    return false;
                }
            } else if (!this.securityLabel.equals(other.securityLabel)) {
                return false;
            }
            if (this.clientLogging != other.clientLogging || this.serverLogging != other.serverLogging || Double.doubleToLongBits(this.deadline) != Double.doubleToLongBits(other.deadline) || this.failFast != other.failFast || this.endUserCredsRequested != other.endUserCredsRequested || this.logLevel != other.logLevel || this.deprecated != other.deprecated || !InternalNano.equals(this.uninterpretedOption, other.uninterpretedOption)) {
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
            int result = ((((((((((((((getClass().getName().hashCode() + 527) * 31) + ((int) (this.clientInitialTokens ^ (this.clientInitialTokens >>> 32)))) * 31) + ((int) (this.serverInitialTokens ^ (this.serverInitialTokens >>> 32)))) * 31) + this.tokenUnit) * 31) + this.securityLevel) * 31) + (this.securityLabel == null ? 0 : this.securityLabel.hashCode())) * 31) + this.clientLogging) * 31) + this.serverLogging;
            long v = Double.doubleToLongBits(this.deadline);
            int i4 = ((result * 31) + ((int) ((v >>> 32) ^ v))) * 31;
            if (this.failFast) {
                i = 1231;
            } else {
                i = 1237;
            }
            i4 = (i4 + i) * 31;
            if (this.endUserCredsRequested) {
                i = 1231;
            } else {
                i = 1237;
            }
            i = (((i4 + i) * 31) + this.logLevel) * 31;
            if (!this.deprecated) {
                i3 = 1237;
            }
            i = (((i + i3) * 31) + InternalNano.hashCode(this.uninterpretedOption)) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i2 = this.unknownFieldData.hashCode();
            }
            return i + i2;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.clientInitialTokens != -1) {
                output.writeInt64(1, this.clientInitialTokens);
            }
            if (this.serverInitialTokens != -1) {
                output.writeInt64(2, this.serverInitialTokens);
            }
            if (this.tokenUnit != 0) {
                output.writeInt32(3, this.tokenUnit);
            }
            if (this.securityLevel != 0) {
                output.writeInt32(4, this.securityLevel);
            }
            if (!this.securityLabel.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(5, this.securityLabel);
            }
            if (this.clientLogging != PeopleColumnBitmask.IS_BLOCKED) {
                output.writeInt32(6, this.clientLogging);
            }
            if (this.serverLogging != PeopleColumnBitmask.IS_BLOCKED) {
                output.writeInt32(7, this.serverLogging);
            }
            if (Double.doubleToLongBits(this.deadline) != Double.doubleToLongBits(-1.0d)) {
                output.writeDouble(8, this.deadline);
            }
            if (this.failFast) {
                output.writeBool(9, this.failFast);
            }
            if (this.endUserCredsRequested) {
                output.writeBool(10, this.endUserCredsRequested);
            }
            if (this.logLevel != 2) {
                output.writeInt32(11, this.logLevel);
            }
            if (this.deprecated) {
                output.writeBool(33, this.deprecated);
            }
            if (this.uninterpretedOption != null && this.uninterpretedOption.length > 0) {
                for (UninterpretedOption element : this.uninterpretedOption) {
                    if (element != null) {
                        output.writeMessage(999, element);
                    }
                }
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.clientInitialTokens != -1) {
                size += CodedOutputByteBufferNano.computeInt64Size(1, this.clientInitialTokens);
            }
            if (this.serverInitialTokens != -1) {
                size += CodedOutputByteBufferNano.computeInt64Size(2, this.serverInitialTokens);
            }
            if (this.tokenUnit != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(3, this.tokenUnit);
            }
            if (this.securityLevel != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(4, this.securityLevel);
            }
            if (!this.securityLabel.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(5, this.securityLabel);
            }
            if (this.clientLogging != PeopleColumnBitmask.IS_BLOCKED) {
                size += CodedOutputByteBufferNano.computeInt32Size(6, this.clientLogging);
            }
            if (this.serverLogging != PeopleColumnBitmask.IS_BLOCKED) {
                size += CodedOutputByteBufferNano.computeInt32Size(7, this.serverLogging);
            }
            if (Double.doubleToLongBits(this.deadline) != Double.doubleToLongBits(-1.0d)) {
                size += CodedOutputByteBufferNano.computeDoubleSize(8, this.deadline);
            }
            if (this.failFast) {
                size += CodedOutputByteBufferNano.computeBoolSize(9, this.failFast);
            }
            if (this.endUserCredsRequested) {
                size += CodedOutputByteBufferNano.computeBoolSize(10, this.endUserCredsRequested);
            }
            if (this.logLevel != 2) {
                size += CodedOutputByteBufferNano.computeInt32Size(11, this.logLevel);
            }
            if (this.deprecated) {
                size += CodedOutputByteBufferNano.computeBoolSize(33, this.deprecated);
            }
            if (this.uninterpretedOption != null && this.uninterpretedOption.length > 0) {
                for (UninterpretedOption element : this.uninterpretedOption) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(999, element);
                    }
                }
            }
            return size;
        }

        public StreamOptions mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                int value;
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_GET_LINK /*8*/:
                        this.clientInitialTokens = input.readInt64();
                        continue;
                    case LogSource.GMS_CORE_PEOPLE /*16*/:
                        this.serverInitialTokens = input.readInt64();
                        continue;
                    case LogSource.LB_C /*24*/:
                        value = input.readInt32();
                        switch (value) {
                            case Action.UNKNOWN /*0*/:
                            case TimeSelection.Type.TEMPORARY /*1*/:
                                this.tokenUnit = value;
                                break;
                            default:
                                continue;
                        }
                    case LogSource.SOCIAL /*32*/:
                        value = input.readInt32();
                        switch (value) {
                            case Action.UNKNOWN /*0*/:
                            case TimeSelection.Type.TEMPORARY /*1*/:
                            case TimeSelection.Type.INDEFINITELY /*2*/:
                            case TimeSelection.Type.CUSTOM /*3*/:
                                this.securityLevel = value;
                                break;
                            default:
                                continue;
                        }
                    case LogSource.PHOTOS /*42*/:
                        this.securityLabel = input.readString();
                        continue;
                    case LogSource.BACKDROP /*48*/:
                        this.clientLogging = input.readInt32();
                        continue;
                    case LogSource.DOCS /*56*/:
                        this.serverLogging = input.readInt32();
                        continue;
                    case LogSource.WEB_STORE /*65*/:
                        this.deadline = input.readDouble();
                        continue;
                    case LogSource.JUSTSPEAK /*72*/:
                        this.failFast = input.readBool();
                        continue;
                    case LogSource.CRONET_ANDROID_GSA /*80*/:
                        this.endUserCredsRequested = input.readBool();
                        continue;
                    case LogSource.EMERGENCY_ASSIST /*88*/:
                        value = input.readInt32();
                        switch (value) {
                            case Action.UNKNOWN /*0*/:
                            case TimeSelection.Type.TEMPORARY /*1*/:
                            case TimeSelection.Type.INDEFINITELY /*2*/:
                            case TimeSelection.Type.CUSTOM /*3*/:
                            case OvenFreshResult.NO_ACCOUNTS /*4*/:
                                this.logLevel = value;
                                break;
                            default:
                                continue;
                        }
                    case 264:
                        this.deprecated = input.readBool();
                        continue;
                    case 7994:
                        int i;
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 7994);
                        if (this.uninterpretedOption == null) {
                            i = 0;
                        } else {
                            i = this.uninterpretedOption.length;
                        }
                        UninterpretedOption[] newArray = new UninterpretedOption[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.uninterpretedOption, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new UninterpretedOption();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i++;
                        }
                        newArray[i] = new UninterpretedOption();
                        input.readMessage(newArray[i]);
                        this.uninterpretedOption = newArray;
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

        public static StreamOptions parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (StreamOptions) MessageNano.mergeFrom(new StreamOptions(), data);
        }

        public static StreamOptions parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new StreamOptions().mergeFrom(input);
        }
    }

    public static final class UninterpretedOption extends ExtendableMessageNano<UninterpretedOption> {
        private static volatile UninterpretedOption[] _emptyArray;
        public String aggregateValue;
        public double doubleValue;
        public String identifierValue;
        public NamePart[] name;
        public long negativeIntValue;
        public long positiveIntValue;
        public byte[] stringValue;

        public static final class NamePart extends ExtendableMessageNano<NamePart> {
            private static volatile NamePart[] _emptyArray;
            public boolean isExtension;
            public String namePart;

            public static NamePart[] emptyArray() {
                if (_emptyArray == null) {
                    synchronized (InternalNano.LAZY_INIT_LOCK) {
                        if (_emptyArray == null) {
                            _emptyArray = new NamePart[0];
                        }
                    }
                }
                return _emptyArray;
            }

            public NamePart() {
                clear();
            }

            public NamePart clear() {
                this.namePart = BuildConfig.VERSION_NAME;
                this.isExtension = false;
                this.unknownFieldData = null;
                this.cachedSize = -1;
                return this;
            }

            public boolean equals(Object o) {
                if (o == this) {
                    return true;
                }
                if (!(o instanceof NamePart)) {
                    return false;
                }
                NamePart other = (NamePart) o;
                if (this.namePart == null) {
                    if (other.namePart != null) {
                        return false;
                    }
                } else if (!this.namePart.equals(other.namePart)) {
                    return false;
                }
                if (this.isExtension != other.isExtension) {
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
                int hashCode = (((((getClass().getName().hashCode() + 527) * 31) + (this.namePart == null ? 0 : this.namePart.hashCode())) * 31) + (this.isExtension ? 1231 : 1237)) * 31;
                if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                    i = this.unknownFieldData.hashCode();
                }
                return hashCode + i;
            }

            public void writeTo(CodedOutputByteBufferNano output) throws IOException {
                output.writeString(1, this.namePart);
                output.writeBool(2, this.isExtension);
                super.writeTo(output);
            }

            protected int computeSerializedSize() {
                return (super.computeSerializedSize() + CodedOutputByteBufferNano.computeStringSize(1, this.namePart)) + CodedOutputByteBufferNano.computeBoolSize(2, this.isExtension);
            }

            public NamePart mergeFrom(CodedInputByteBufferNano input) throws IOException {
                while (true) {
                    int tag = input.readTag();
                    switch (tag) {
                        case Action.UNKNOWN /*0*/:
                            break;
                        case Type.TAP_ABOUT /*10*/:
                            this.namePart = input.readString();
                            continue;
                        case LogSource.GMS_CORE_PEOPLE /*16*/:
                            this.isExtension = input.readBool();
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

            public static NamePart parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
                return (NamePart) MessageNano.mergeFrom(new NamePart(), data);
            }

            public static NamePart parseFrom(CodedInputByteBufferNano input) throws IOException {
                return new NamePart().mergeFrom(input);
            }
        }

        public static UninterpretedOption[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new UninterpretedOption[0];
                    }
                }
            }
            return _emptyArray;
        }

        public UninterpretedOption() {
            clear();
        }

        public UninterpretedOption clear() {
            this.name = NamePart.emptyArray();
            this.identifierValue = BuildConfig.VERSION_NAME;
            this.positiveIntValue = 0;
            this.negativeIntValue = 0;
            this.doubleValue = 0.0d;
            this.stringValue = WireFormatNano.EMPTY_BYTES;
            this.aggregateValue = BuildConfig.VERSION_NAME;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof UninterpretedOption)) {
                return false;
            }
            UninterpretedOption other = (UninterpretedOption) o;
            if (!InternalNano.equals(this.name, other.name)) {
                return false;
            }
            if (this.identifierValue == null) {
                if (other.identifierValue != null) {
                    return false;
                }
            } else if (!this.identifierValue.equals(other.identifierValue)) {
                return false;
            }
            if (this.positiveIntValue != other.positiveIntValue || this.negativeIntValue != other.negativeIntValue || Double.doubleToLongBits(this.doubleValue) != Double.doubleToLongBits(other.doubleValue) || !Arrays.equals(this.stringValue, other.stringValue)) {
                return false;
            }
            if (this.aggregateValue == null) {
                if (other.aggregateValue != null) {
                    return false;
                }
            } else if (!this.aggregateValue.equals(other.aggregateValue)) {
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
            int hashCode = (((getClass().getName().hashCode() + 527) * 31) + InternalNano.hashCode(this.name)) * 31;
            if (this.identifierValue == null) {
                i = 0;
            } else {
                i = this.identifierValue.hashCode();
            }
            int result = ((((hashCode + i) * 31) + ((int) (this.positiveIntValue ^ (this.positiveIntValue >>> 32)))) * 31) + ((int) (this.negativeIntValue ^ (this.negativeIntValue >>> 32)));
            long v = Double.doubleToLongBits(this.doubleValue);
            i = ((((((result * 31) + ((int) ((v >>> 32) ^ v))) * 31) + Arrays.hashCode(this.stringValue)) * 31) + (this.aggregateValue == null ? 0 : this.aggregateValue.hashCode())) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i2 = this.unknownFieldData.hashCode();
            }
            return i + i2;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.name != null && this.name.length > 0) {
                for (NamePart element : this.name) {
                    if (element != null) {
                        output.writeMessage(2, element);
                    }
                }
            }
            if (!this.identifierValue.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(3, this.identifierValue);
            }
            if (this.positiveIntValue != 0) {
                output.writeUInt64(4, this.positiveIntValue);
            }
            if (this.negativeIntValue != 0) {
                output.writeInt64(5, this.negativeIntValue);
            }
            if (Double.doubleToLongBits(this.doubleValue) != Double.doubleToLongBits(0.0d)) {
                output.writeDouble(6, this.doubleValue);
            }
            if (!Arrays.equals(this.stringValue, WireFormatNano.EMPTY_BYTES)) {
                output.writeBytes(7, this.stringValue);
            }
            if (!this.aggregateValue.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(8, this.aggregateValue);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.name != null && this.name.length > 0) {
                for (NamePart element : this.name) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(2, element);
                    }
                }
            }
            if (!this.identifierValue.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(3, this.identifierValue);
            }
            if (this.positiveIntValue != 0) {
                size += CodedOutputByteBufferNano.computeUInt64Size(4, this.positiveIntValue);
            }
            if (this.negativeIntValue != 0) {
                size += CodedOutputByteBufferNano.computeInt64Size(5, this.negativeIntValue);
            }
            if (Double.doubleToLongBits(this.doubleValue) != Double.doubleToLongBits(0.0d)) {
                size += CodedOutputByteBufferNano.computeDoubleSize(6, this.doubleValue);
            }
            if (!Arrays.equals(this.stringValue, WireFormatNano.EMPTY_BYTES)) {
                size += CodedOutputByteBufferNano.computeBytesSize(7, this.stringValue);
            }
            if (this.aggregateValue.equals(BuildConfig.VERSION_NAME)) {
                return size;
            }
            return size + CodedOutputByteBufferNano.computeStringSize(8, this.aggregateValue);
        }

        public UninterpretedOption mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case LogSource.GOOGLE_ANALYTICS /*18*/:
                        int i;
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 18);
                        if (this.name == null) {
                            i = 0;
                        } else {
                            i = this.name.length;
                        }
                        NamePart[] newArray = new NamePart[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.name, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new NamePart();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i++;
                        }
                        newArray[i] = new NamePart();
                        input.readMessage(newArray[i]);
                        this.name = newArray;
                        continue;
                    case LogSource.ANDROID_CAMERA /*26*/:
                        this.identifierValue = input.readString();
                        continue;
                    case LogSource.SOCIAL /*32*/:
                        this.positiveIntValue = input.readUInt64();
                        continue;
                    case LogSource.COPRESENCE /*40*/:
                        this.negativeIntValue = input.readInt64();
                        continue;
                    case LogSource.TELEMATICS /*49*/:
                        this.doubleValue = input.readDouble();
                        continue;
                    case LogSource.SLIDES /*58*/:
                        this.stringValue = input.readBytes();
                        continue;
                    case LogSource.WIFI_ASSISTANT /*66*/:
                        this.aggregateValue = input.readString();
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

        public static UninterpretedOption parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (UninterpretedOption) MessageNano.mergeFrom(new UninterpretedOption(), data);
        }

        public static UninterpretedOption parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new UninterpretedOption().mergeFrom(input);
        }
    }
}
