package com.google.android.gms.udc.proto;

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

public final class UserEnvironment extends ExtendableMessageNano<UserEnvironment> {
    private static volatile UserEnvironment[] _emptyArray;
    public AndroidProperties androidProperties;
    public CallerId callerId;
    public ClientManagedSettingsInfo clientManagedSettingsInfo;
    public String languageCode;
    public String libraryVersion;
    public Platform platform;
    public String productContext;
    public int productId;

    public static final class AndroidProperties extends ExtendableMessageNano<AndroidProperties> {
        private static volatile AndroidProperties[] _emptyArray;
        public String callingClientJarLibraryVersion;

        public static AndroidProperties[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new AndroidProperties[0];
                    }
                }
            }
            return _emptyArray;
        }

        public AndroidProperties() {
            clear();
        }

        public AndroidProperties clear() {
            this.callingClientJarLibraryVersion = BuildConfig.VERSION_NAME;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof AndroidProperties)) {
                return false;
            }
            AndroidProperties other = (AndroidProperties) o;
            if (this.callingClientJarLibraryVersion == null) {
                if (other.callingClientJarLibraryVersion != null) {
                    return false;
                }
            } else if (!this.callingClientJarLibraryVersion.equals(other.callingClientJarLibraryVersion)) {
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
            int hashCode = (((getClass().getName().hashCode() + 527) * 31) + (this.callingClientJarLibraryVersion == null ? 0 : this.callingClientJarLibraryVersion.hashCode())) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (!this.callingClientJarLibraryVersion.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(1, this.callingClientJarLibraryVersion);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.callingClientJarLibraryVersion.equals(BuildConfig.VERSION_NAME)) {
                return size;
            }
            return size + CodedOutputByteBufferNano.computeStringSize(1, this.callingClientJarLibraryVersion);
        }

        public AndroidProperties mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        this.callingClientJarLibraryVersion = input.readString();
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

        public static AndroidProperties parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (AndroidProperties) MessageNano.mergeFrom(new AndroidProperties(), data);
        }

        public static AndroidProperties parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new AndroidProperties().mergeFrom(input);
        }
    }

    public static final class CallerId extends ExtendableMessageNano<CallerId> {
        private static volatile CallerId[] _emptyArray;
        public String androidCallingPackage;

        public static CallerId[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new CallerId[0];
                    }
                }
            }
            return _emptyArray;
        }

        public CallerId() {
            clear();
        }

        public CallerId clear() {
            this.androidCallingPackage = BuildConfig.VERSION_NAME;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof CallerId)) {
                return false;
            }
            CallerId other = (CallerId) o;
            if (this.androidCallingPackage == null) {
                if (other.androidCallingPackage != null) {
                    return false;
                }
            } else if (!this.androidCallingPackage.equals(other.androidCallingPackage)) {
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
            int hashCode = (((getClass().getName().hashCode() + 527) * 31) + (this.androidCallingPackage == null ? 0 : this.androidCallingPackage.hashCode())) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (!this.androidCallingPackage.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(1, this.androidCallingPackage);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.androidCallingPackage.equals(BuildConfig.VERSION_NAME)) {
                return size;
            }
            return size + CodedOutputByteBufferNano.computeStringSize(1, this.androidCallingPackage);
        }

        public CallerId mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        this.androidCallingPackage = input.readString();
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

        public static CallerId parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (CallerId) MessageNano.mergeFrom(new CallerId(), data);
        }

        public static CallerId parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new CallerId().mergeFrom(input);
        }
    }

    public static final class ClientManagedSettingsInfo extends ExtendableMessageNano<ClientManagedSettingsInfo> {
        private static volatile ClientManagedSettingsInfo[] _emptyArray;
        public int[] supportedSettingIds;

        public static ClientManagedSettingsInfo[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new ClientManagedSettingsInfo[0];
                    }
                }
            }
            return _emptyArray;
        }

        public ClientManagedSettingsInfo() {
            clear();
        }

        public ClientManagedSettingsInfo clear() {
            this.supportedSettingIds = WireFormatNano.EMPTY_INT_ARRAY;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof ClientManagedSettingsInfo)) {
                return false;
            }
            ClientManagedSettingsInfo other = (ClientManagedSettingsInfo) o;
            if (!InternalNano.equals(this.supportedSettingIds, other.supportedSettingIds)) {
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
            int hashCode = (((getClass().getName().hashCode() + 527) * 31) + InternalNano.hashCode(this.supportedSettingIds)) * 31;
            int hashCode2 = (this.unknownFieldData == null || this.unknownFieldData.isEmpty()) ? 0 : this.unknownFieldData.hashCode();
            return hashCode + hashCode2;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.supportedSettingIds != null && this.supportedSettingIds.length > 0) {
                for (int writeInt32 : this.supportedSettingIds) {
                    output.writeInt32(1, writeInt32);
                }
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.supportedSettingIds == null || this.supportedSettingIds.length <= 0) {
                return size;
            }
            int dataSize = 0;
            for (int element : this.supportedSettingIds) {
                dataSize += CodedOutputByteBufferNano.computeInt32SizeNoTag(element);
            }
            return (size + dataSize) + (this.supportedSettingIds.length * 1);
        }

        public ClientManagedSettingsInfo mergeFrom(CodedInputByteBufferNano input) throws IOException {
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
                        i = this.supportedSettingIds == null ? 0 : this.supportedSettingIds.length;
                        newArray = new int[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.supportedSettingIds, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = input.readInt32();
                            input.readTag();
                            i++;
                        }
                        newArray[i] = input.readInt32();
                        this.supportedSettingIds = newArray;
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
                        i = this.supportedSettingIds == null ? 0 : this.supportedSettingIds.length;
                        newArray = new int[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.supportedSettingIds, 0, newArray, 0, i);
                        }
                        while (i < newArray.length) {
                            newArray[i] = input.readInt32();
                            i++;
                        }
                        this.supportedSettingIds = newArray;
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

        public static ClientManagedSettingsInfo parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (ClientManagedSettingsInfo) MessageNano.mergeFrom(new ClientManagedSettingsInfo(), data);
        }

        public static ClientManagedSettingsInfo parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new ClientManagedSettingsInfo().mergeFrom(input);
        }
    }

    public static final class Platform extends ExtendableMessageNano<Platform> {
        private static volatile Platform[] _emptyArray;
        public int densityNumber;
        public String osVersion;
        public int type;

        public interface Type {
            public static final int ANDROID = 1;
            public static final int IOS = 2;
            public static final int UNKNOWN = 0;
            public static final int WEB = 3;
        }

        public static Platform[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new Platform[0];
                    }
                }
            }
            return _emptyArray;
        }

        public Platform() {
            clear();
        }

        public Platform clear() {
            this.type = 0;
            this.densityNumber = 0;
            this.osVersion = BuildConfig.VERSION_NAME;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof Platform)) {
                return false;
            }
            Platform other = (Platform) o;
            if (this.type != other.type || this.densityNumber != other.densityNumber) {
                return false;
            }
            if (this.osVersion == null) {
                if (other.osVersion != null) {
                    return false;
                }
            } else if (!this.osVersion.equals(other.osVersion)) {
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
            int hashCode = (((((((getClass().getName().hashCode() + 527) * 31) + this.type) * 31) + this.densityNumber) * 31) + (this.osVersion == null ? 0 : this.osVersion.hashCode())) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.type != 0) {
                output.writeInt32(1, this.type);
            }
            if (this.densityNumber != 0) {
                output.writeInt32(2, this.densityNumber);
            }
            if (!this.osVersion.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(3, this.osVersion);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.type != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(1, this.type);
            }
            if (this.densityNumber != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(2, this.densityNumber);
            }
            if (this.osVersion.equals(BuildConfig.VERSION_NAME)) {
                return size;
            }
            return size + CodedOutputByteBufferNano.computeStringSize(3, this.osVersion);
        }

        public Platform mergeFrom(CodedInputByteBufferNano input) throws IOException {
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
                                this.type = value;
                                break;
                            default:
                                continue;
                        }
                    case LogSource.GMS_CORE_PEOPLE /*16*/:
                        this.densityNumber = input.readInt32();
                        continue;
                    case LogSource.ANDROID_CAMERA /*26*/:
                        this.osVersion = input.readString();
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

        public static Platform parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (Platform) MessageNano.mergeFrom(new Platform(), data);
        }

        public static Platform parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new Platform().mergeFrom(input);
        }
    }

    public static UserEnvironment[] emptyArray() {
        if (_emptyArray == null) {
            synchronized (InternalNano.LAZY_INIT_LOCK) {
                if (_emptyArray == null) {
                    _emptyArray = new UserEnvironment[0];
                }
            }
        }
        return _emptyArray;
    }

    public UserEnvironment() {
        clear();
    }

    public UserEnvironment clear() {
        this.languageCode = BuildConfig.VERSION_NAME;
        this.platform = null;
        this.productId = 0;
        this.productContext = BuildConfig.VERSION_NAME;
        this.libraryVersion = BuildConfig.VERSION_NAME;
        this.callerId = null;
        this.androidProperties = null;
        this.clientManagedSettingsInfo = null;
        this.unknownFieldData = null;
        this.cachedSize = -1;
        return this;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof UserEnvironment)) {
            return false;
        }
        UserEnvironment other = (UserEnvironment) o;
        if (this.languageCode == null) {
            if (other.languageCode != null) {
                return false;
            }
        } else if (!this.languageCode.equals(other.languageCode)) {
            return false;
        }
        if (this.platform == null) {
            if (other.platform != null) {
                return false;
            }
        } else if (!this.platform.equals(other.platform)) {
            return false;
        }
        if (this.productId != other.productId) {
            return false;
        }
        if (this.productContext == null) {
            if (other.productContext != null) {
                return false;
            }
        } else if (!this.productContext.equals(other.productContext)) {
            return false;
        }
        if (this.libraryVersion == null) {
            if (other.libraryVersion != null) {
                return false;
            }
        } else if (!this.libraryVersion.equals(other.libraryVersion)) {
            return false;
        }
        if (this.callerId == null) {
            if (other.callerId != null) {
                return false;
            }
        } else if (!this.callerId.equals(other.callerId)) {
            return false;
        }
        if (this.androidProperties == null) {
            if (other.androidProperties != null) {
                return false;
            }
        } else if (!this.androidProperties.equals(other.androidProperties)) {
            return false;
        }
        if (this.clientManagedSettingsInfo == null) {
            if (other.clientManagedSettingsInfo != null) {
                return false;
            }
        } else if (!this.clientManagedSettingsInfo.equals(other.clientManagedSettingsInfo)) {
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
        int hashCode = (((((((((((((((((getClass().getName().hashCode() + 527) * 31) + (this.languageCode == null ? 0 : this.languageCode.hashCode())) * 31) + (this.platform == null ? 0 : this.platform.hashCode())) * 31) + this.productId) * 31) + (this.productContext == null ? 0 : this.productContext.hashCode())) * 31) + (this.libraryVersion == null ? 0 : this.libraryVersion.hashCode())) * 31) + (this.callerId == null ? 0 : this.callerId.hashCode())) * 31) + (this.androidProperties == null ? 0 : this.androidProperties.hashCode())) * 31) + (this.clientManagedSettingsInfo == null ? 0 : this.clientManagedSettingsInfo.hashCode())) * 31;
        if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
            i = this.unknownFieldData.hashCode();
        }
        return hashCode + i;
    }

    public void writeTo(CodedOutputByteBufferNano output) throws IOException {
        if (!this.languageCode.equals(BuildConfig.VERSION_NAME)) {
            output.writeString(1, this.languageCode);
        }
        if (this.platform != null) {
            output.writeMessage(2, this.platform);
        }
        if (this.productId != 0) {
            output.writeInt32(3, this.productId);
        }
        if (!this.productContext.equals(BuildConfig.VERSION_NAME)) {
            output.writeString(4, this.productContext);
        }
        if (!this.libraryVersion.equals(BuildConfig.VERSION_NAME)) {
            output.writeString(5, this.libraryVersion);
        }
        if (this.callerId != null) {
            output.writeMessage(6, this.callerId);
        }
        if (this.androidProperties != null) {
            output.writeMessage(7, this.androidProperties);
        }
        if (this.clientManagedSettingsInfo != null) {
            output.writeMessage(8, this.clientManagedSettingsInfo);
        }
        super.writeTo(output);
    }

    protected int computeSerializedSize() {
        int size = super.computeSerializedSize();
        if (!this.languageCode.equals(BuildConfig.VERSION_NAME)) {
            size += CodedOutputByteBufferNano.computeStringSize(1, this.languageCode);
        }
        if (this.platform != null) {
            size += CodedOutputByteBufferNano.computeMessageSize(2, this.platform);
        }
        if (this.productId != 0) {
            size += CodedOutputByteBufferNano.computeInt32Size(3, this.productId);
        }
        if (!this.productContext.equals(BuildConfig.VERSION_NAME)) {
            size += CodedOutputByteBufferNano.computeStringSize(4, this.productContext);
        }
        if (!this.libraryVersion.equals(BuildConfig.VERSION_NAME)) {
            size += CodedOutputByteBufferNano.computeStringSize(5, this.libraryVersion);
        }
        if (this.callerId != null) {
            size += CodedOutputByteBufferNano.computeMessageSize(6, this.callerId);
        }
        if (this.androidProperties != null) {
            size += CodedOutputByteBufferNano.computeMessageSize(7, this.androidProperties);
        }
        if (this.clientManagedSettingsInfo != null) {
            return size + CodedOutputByteBufferNano.computeMessageSize(8, this.clientManagedSettingsInfo);
        }
        return size;
    }

    public UserEnvironment mergeFrom(CodedInputByteBufferNano input) throws IOException {
        while (true) {
            int tag = input.readTag();
            switch (tag) {
                case Action.UNKNOWN /*0*/:
                    break;
                case Type.TAP_ABOUT /*10*/:
                    this.languageCode = input.readString();
                    continue;
                case LogSource.GOOGLE_ANALYTICS /*18*/:
                    if (this.platform == null) {
                        this.platform = new Platform();
                    }
                    input.readMessage(this.platform);
                    continue;
                case LogSource.LB_C /*24*/:
                    this.productId = input.readInt32();
                    continue;
                case LogSource.NOVA /*34*/:
                    this.productContext = input.readString();
                    continue;
                case LogSource.PHOTOS /*42*/:
                    this.libraryVersion = input.readString();
                    continue;
                case LogSource.HANGOUT_LOG_REQUEST /*50*/:
                    if (this.callerId == null) {
                        this.callerId = new CallerId();
                    }
                    input.readMessage(this.callerId);
                    continue;
                case LogSource.SLIDES /*58*/:
                    if (this.androidProperties == null) {
                        this.androidProperties = new AndroidProperties();
                    }
                    input.readMessage(this.androidProperties);
                    continue;
                case LogSource.WIFI_ASSISTANT /*66*/:
                    if (this.clientManagedSettingsInfo == null) {
                        this.clientManagedSettingsInfo = new ClientManagedSettingsInfo();
                    }
                    input.readMessage(this.clientManagedSettingsInfo);
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

    public static UserEnvironment parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
        return (UserEnvironment) MessageNano.mergeFrom(new UserEnvironment(), data);
    }

    public static UserEnvironment parseFrom(CodedInputByteBufferNano input) throws IOException {
        return new UserEnvironment().mergeFrom(input);
    }
}
