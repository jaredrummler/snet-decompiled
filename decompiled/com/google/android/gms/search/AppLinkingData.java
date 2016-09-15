package com.google.android.gms.search;

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

public interface AppLinkingData {

    public static final class AppResource extends ExtendableMessageNano<AppResource> {
        private static volatile AppResource[] _emptyArray;
        public Link[] links;
        public String url;

        public static AppResource[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new AppResource[0];
                    }
                }
            }
            return _emptyArray;
        }

        public AppResource() {
            clear();
        }

        public AppResource clear() {
            this.url = BuildConfig.VERSION_NAME;
            this.links = Link.emptyArray();
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof AppResource)) {
                return false;
            }
            AppResource other = (AppResource) o;
            if (this.url == null) {
                if (other.url != null) {
                    return false;
                }
            } else if (!this.url.equals(other.url)) {
                return false;
            }
            if (!InternalNano.equals(this.links, other.links)) {
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
            int hashCode = (((((getClass().getName().hashCode() + 527) * 31) + (this.url == null ? 0 : this.url.hashCode())) * 31) + InternalNano.hashCode(this.links)) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (!this.url.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(1, this.url);
            }
            if (this.links != null && this.links.length > 0) {
                for (Link element : this.links) {
                    if (element != null) {
                        output.writeMessage(2, element);
                    }
                }
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (!this.url.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.url);
            }
            if (this.links != null && this.links.length > 0) {
                for (Link element : this.links) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(2, element);
                    }
                }
            }
            return size;
        }

        public AppResource mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        this.url = input.readString();
                        continue;
                    case LogSource.GOOGLE_ANALYTICS /*18*/:
                        int i;
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 18);
                        if (this.links == null) {
                            i = 0;
                        } else {
                            i = this.links.length;
                        }
                        Link[] newArray = new Link[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.links, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new Link();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i++;
                        }
                        newArray[i] = new Link();
                        input.readMessage(newArray[i]);
                        this.links = newArray;
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

        public static AppResource parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (AppResource) MessageNano.mergeFrom(new AppResource(), data);
        }

        public static AppResource parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new AppResource().mergeFrom(input);
        }
    }

    public static final class Link extends ExtendableMessageNano<Link> {
        private static volatile Link[] _emptyArray;
        public String androidAction;
        public String androidPackageId;
        public VerificationStatus dEPRECATEDStatus;
        public String iosAppStoreId;
        public int platform;
        public String uri;
        public int verificationStatus;

        public static Link[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new Link[0];
                    }
                }
            }
            return _emptyArray;
        }

        public Link() {
            clear();
        }

        public Link clear() {
            this.uri = BuildConfig.VERSION_NAME;
            this.platform = 0;
            this.androidPackageId = BuildConfig.VERSION_NAME;
            this.androidAction = BuildConfig.VERSION_NAME;
            this.iosAppStoreId = BuildConfig.VERSION_NAME;
            this.verificationStatus = 0;
            this.dEPRECATEDStatus = null;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof Link)) {
                return false;
            }
            Link other = (Link) o;
            if (this.uri == null) {
                if (other.uri != null) {
                    return false;
                }
            } else if (!this.uri.equals(other.uri)) {
                return false;
            }
            if (this.platform != other.platform) {
                return false;
            }
            if (this.androidPackageId == null) {
                if (other.androidPackageId != null) {
                    return false;
                }
            } else if (!this.androidPackageId.equals(other.androidPackageId)) {
                return false;
            }
            if (this.androidAction == null) {
                if (other.androidAction != null) {
                    return false;
                }
            } else if (!this.androidAction.equals(other.androidAction)) {
                return false;
            }
            if (this.iosAppStoreId == null) {
                if (other.iosAppStoreId != null) {
                    return false;
                }
            } else if (!this.iosAppStoreId.equals(other.iosAppStoreId)) {
                return false;
            }
            if (this.verificationStatus != other.verificationStatus) {
                return false;
            }
            if (this.dEPRECATEDStatus == null) {
                if (other.dEPRECATEDStatus != null) {
                    return false;
                }
            } else if (!this.dEPRECATEDStatus.equals(other.dEPRECATEDStatus)) {
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
            int hashCode = (((((((((((((((getClass().getName().hashCode() + 527) * 31) + (this.uri == null ? 0 : this.uri.hashCode())) * 31) + this.platform) * 31) + (this.androidPackageId == null ? 0 : this.androidPackageId.hashCode())) * 31) + (this.androidAction == null ? 0 : this.androidAction.hashCode())) * 31) + (this.iosAppStoreId == null ? 0 : this.iosAppStoreId.hashCode())) * 31) + this.verificationStatus) * 31) + (this.dEPRECATEDStatus == null ? 0 : this.dEPRECATEDStatus.hashCode())) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (!this.uri.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(1, this.uri);
            }
            if (this.platform != 0) {
                output.writeInt32(2, this.platform);
            }
            if (!this.androidPackageId.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(3, this.androidPackageId);
            }
            if (!this.iosAppStoreId.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(4, this.iosAppStoreId);
            }
            if (this.dEPRECATEDStatus != null) {
                output.writeMessage(5, this.dEPRECATEDStatus);
            }
            if (!this.androidAction.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(6, this.androidAction);
            }
            if (this.verificationStatus != 0) {
                output.writeInt32(7, this.verificationStatus);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (!this.uri.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.uri);
            }
            if (this.platform != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(2, this.platform);
            }
            if (!this.androidPackageId.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(3, this.androidPackageId);
            }
            if (!this.iosAppStoreId.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(4, this.iosAppStoreId);
            }
            if (this.dEPRECATEDStatus != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(5, this.dEPRECATEDStatus);
            }
            if (!this.androidAction.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(6, this.androidAction);
            }
            if (this.verificationStatus != 0) {
                return size + CodedOutputByteBufferNano.computeInt32Size(7, this.verificationStatus);
            }
            return size;
        }

        public Link mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                int value;
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        this.uri = input.readString();
                        continue;
                    case LogSource.GMS_CORE_PEOPLE /*16*/:
                        value = input.readInt32();
                        switch (value) {
                            case Action.UNKNOWN /*0*/:
                            case TimeSelection.Type.TEMPORARY /*1*/:
                            case TimeSelection.Type.INDEFINITELY /*2*/:
                                this.platform = value;
                                break;
                            default:
                                continue;
                        }
                    case LogSource.ANDROID_CAMERA /*26*/:
                        this.androidPackageId = input.readString();
                        continue;
                    case LogSource.NOVA /*34*/:
                        this.iosAppStoreId = input.readString();
                        continue;
                    case LogSource.PHOTOS /*42*/:
                        if (this.dEPRECATEDStatus == null) {
                            this.dEPRECATEDStatus = new VerificationStatus();
                        }
                        input.readMessage(this.dEPRECATEDStatus);
                        continue;
                    case LogSource.HANGOUT_LOG_REQUEST /*50*/:
                        this.androidAction = input.readString();
                        continue;
                    case LogSource.DOCS /*56*/:
                        value = input.readInt32();
                        switch (value) {
                            case Action.UNKNOWN /*0*/:
                            case TimeSelection.Type.TEMPORARY /*1*/:
                            case TimeSelection.Type.INDEFINITELY /*2*/:
                                this.verificationStatus = value;
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

        public static Link parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (Link) MessageNano.mergeFrom(new Link(), data);
        }

        public static Link parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new Link().mergeFrom(input);
        }
    }

    public static final class LookupAppResourceRequest extends ExtendableMessageNano<LookupAppResourceRequest> {
        private static volatile LookupAppResourceRequest[] _emptyArray;
        public int[] platforms;
        public String[] urls;

        public static LookupAppResourceRequest[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new LookupAppResourceRequest[0];
                    }
                }
            }
            return _emptyArray;
        }

        public LookupAppResourceRequest() {
            clear();
        }

        public LookupAppResourceRequest clear() {
            this.urls = WireFormatNano.EMPTY_STRING_ARRAY;
            this.platforms = WireFormatNano.EMPTY_INT_ARRAY;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof LookupAppResourceRequest)) {
                return false;
            }
            LookupAppResourceRequest other = (LookupAppResourceRequest) o;
            if (!InternalNano.equals(this.urls, other.urls) || !InternalNano.equals(this.platforms, other.platforms)) {
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
            int hashCode = (((((getClass().getName().hashCode() + 527) * 31) + InternalNano.hashCode(this.urls)) * 31) + InternalNano.hashCode(this.platforms)) * 31;
            int hashCode2 = (this.unknownFieldData == null || this.unknownFieldData.isEmpty()) ? 0 : this.unknownFieldData.hashCode();
            return hashCode + hashCode2;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.urls != null && this.urls.length > 0) {
                for (String element : this.urls) {
                    if (element != null) {
                        output.writeString(1, element);
                    }
                }
            }
            if (this.platforms != null && this.platforms.length > 0) {
                for (int writeInt32 : this.platforms) {
                    output.writeInt32(2, writeInt32);
                }
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int dataSize;
            int size = super.computeSerializedSize();
            if (this.urls != null && this.urls.length > 0) {
                int dataCount = 0;
                dataSize = 0;
                for (String element : this.urls) {
                    if (element != null) {
                        dataCount++;
                        dataSize += CodedOutputByteBufferNano.computeStringSizeNoTag(element);
                    }
                }
                size = (size + dataSize) + (dataCount * 1);
            }
            if (this.platforms == null || this.platforms.length <= 0) {
                return size;
            }
            dataSize = 0;
            for (int element2 : this.platforms) {
                dataSize += CodedOutputByteBufferNano.computeInt32SizeNoTag(element2);
            }
            return (size + dataSize) + (this.platforms.length * 1);
        }

        public LookupAppResourceRequest mergeFrom(CodedInputByteBufferNano input) throws IOException {
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
                        i = this.urls == null ? 0 : this.urls.length;
                        String[] newArray2 = new String[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.urls, 0, newArray2, 0, i);
                        }
                        while (i < newArray2.length - 1) {
                            newArray2[i] = input.readString();
                            input.readTag();
                            i++;
                        }
                        newArray2[i] = input.readString();
                        this.urls = newArray2;
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
                            i = this.platforms == null ? 0 : this.platforms.length;
                            if (i != 0 || validCount != validValues.length) {
                                newArray = new int[(i + validCount)];
                                if (i != 0) {
                                    System.arraycopy(this.platforms, 0, newArray, 0, i);
                                }
                                System.arraycopy(validValues, 0, newArray, i, validCount);
                                this.platforms = newArray;
                                break;
                            }
                            this.platforms = validValues;
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
                            if (this.platforms == null) {
                                i = 0;
                            } else {
                                i = this.platforms.length;
                            }
                            newArray = new int[(i + arrayLength)];
                            if (i != 0) {
                                System.arraycopy(this.platforms, 0, newArray, 0, i);
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
                            this.platforms = newArray;
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

        public static LookupAppResourceRequest parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (LookupAppResourceRequest) MessageNano.mergeFrom(new LookupAppResourceRequest(), data);
        }

        public static LookupAppResourceRequest parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new LookupAppResourceRequest().mergeFrom(input);
        }
    }

    public static final class LookupAppResourceResponse extends ExtendableMessageNano<LookupAppResourceResponse> {
        private static volatile LookupAppResourceResponse[] _emptyArray;
        public AppResource[] resources;

        public static LookupAppResourceResponse[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new LookupAppResourceResponse[0];
                    }
                }
            }
            return _emptyArray;
        }

        public LookupAppResourceResponse() {
            clear();
        }

        public LookupAppResourceResponse clear() {
            this.resources = AppResource.emptyArray();
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof LookupAppResourceResponse)) {
                return false;
            }
            LookupAppResourceResponse other = (LookupAppResourceResponse) o;
            if (!InternalNano.equals(this.resources, other.resources)) {
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
            int hashCode = (((getClass().getName().hashCode() + 527) * 31) + InternalNano.hashCode(this.resources)) * 31;
            int hashCode2 = (this.unknownFieldData == null || this.unknownFieldData.isEmpty()) ? 0 : this.unknownFieldData.hashCode();
            return hashCode + hashCode2;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.resources != null && this.resources.length > 0) {
                for (AppResource element : this.resources) {
                    if (element != null) {
                        output.writeMessage(1, element);
                    }
                }
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.resources != null && this.resources.length > 0) {
                for (AppResource element : this.resources) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(1, element);
                    }
                }
            }
            return size;
        }

        public LookupAppResourceResponse mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        int i;
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 10);
                        if (this.resources == null) {
                            i = 0;
                        } else {
                            i = this.resources.length;
                        }
                        AppResource[] newArray = new AppResource[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.resources, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new AppResource();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i++;
                        }
                        newArray[i] = new AppResource();
                        input.readMessage(newArray[i]);
                        this.resources = newArray;
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

        public static LookupAppResourceResponse parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (LookupAppResourceResponse) MessageNano.mergeFrom(new LookupAppResourceResponse(), data);
        }

        public static LookupAppResourceResponse parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new LookupAppResourceResponse().mergeFrom(input);
        }
    }

    public interface Platform {
        public static final int ANDROID = 1;
        public static final int IOS = 2;
        public static final int UNDEFINED_PLATFORM_TYPE = 0;
    }

    public interface Verification {
        public static final int UNDEFINED_VERIFICATION_STATUS = 0;
        public static final int UNVERIFIED = 2;
        public static final int VERIFIED = 1;
    }

    public static final class VerificationStatus extends ExtendableMessageNano<VerificationStatus> {
        private static volatile VerificationStatus[] _emptyArray;
        public int status;

        public interface Status {
            public static final int UNDEFINED = 0;
            public static final int UNVERIFIED = 2;
            public static final int VERIFIED = 1;
        }

        public static VerificationStatus[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new VerificationStatus[0];
                    }
                }
            }
            return _emptyArray;
        }

        public VerificationStatus() {
            clear();
        }

        public VerificationStatus clear() {
            this.status = 0;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof VerificationStatus)) {
                return false;
            }
            VerificationStatus other = (VerificationStatus) o;
            if (this.status != other.status) {
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
            int hashCode = (((getClass().getName().hashCode() + 527) * 31) + this.status) * 31;
            int hashCode2 = (this.unknownFieldData == null || this.unknownFieldData.isEmpty()) ? 0 : this.unknownFieldData.hashCode();
            return hashCode + hashCode2;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.status != 0) {
                output.writeInt32(1, this.status);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.status != 0) {
                return size + CodedOutputByteBufferNano.computeInt32Size(1, this.status);
            }
            return size;
        }

        public VerificationStatus mergeFrom(CodedInputByteBufferNano input) throws IOException {
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
                            case TimeSelection.Type.INDEFINITELY /*2*/:
                                this.status = value;
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

        public static VerificationStatus parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (VerificationStatus) MessageNano.mergeFrom(new VerificationStatus(), data);
        }

        public static VerificationStatus parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new VerificationStatus().mergeFrom(input);
        }
    }
}
