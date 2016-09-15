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

public interface AppsCommonProto {

    public static final class App extends ExtendableMessageNano<App> {
        private static volatile App[] _emptyArray;
        public byte[][] deprecatedTag4;
        public boolean installAfterSignin;
        public long installTimeMillis;
        public AppMetadata metadata;
        public String packageName;
        public AppSupervisionSetting supervisionSetting;

        public static App[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new App[0];
                    }
                }
            }
            return _emptyArray;
        }

        public App() {
            clear();
        }

        public App clear() {
            this.packageName = BuildConfig.VERSION_NAME;
            this.metadata = null;
            this.supervisionSetting = null;
            this.deprecatedTag4 = WireFormatNano.EMPTY_BYTES_ARRAY;
            this.installTimeMillis = 0;
            this.installAfterSignin = false;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof App)) {
                return false;
            }
            App other = (App) o;
            if (this.packageName == null) {
                if (other.packageName != null) {
                    return false;
                }
            } else if (!this.packageName.equals(other.packageName)) {
                return false;
            }
            if (this.metadata == null) {
                if (other.metadata != null) {
                    return false;
                }
            } else if (!this.metadata.equals(other.metadata)) {
                return false;
            }
            if (this.supervisionSetting == null) {
                if (other.supervisionSetting != null) {
                    return false;
                }
            } else if (!this.supervisionSetting.equals(other.supervisionSetting)) {
                return false;
            }
            if (!InternalNano.equals(this.deprecatedTag4, other.deprecatedTag4) || this.installTimeMillis != other.installTimeMillis || this.installAfterSignin != other.installAfterSignin) {
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
            int hashCode = (((((((((((((getClass().getName().hashCode() + 527) * 31) + (this.packageName == null ? 0 : this.packageName.hashCode())) * 31) + (this.metadata == null ? 0 : this.metadata.hashCode())) * 31) + (this.supervisionSetting == null ? 0 : this.supervisionSetting.hashCode())) * 31) + InternalNano.hashCode(this.deprecatedTag4)) * 31) + ((int) (this.installTimeMillis ^ (this.installTimeMillis >>> 32)))) * 31) + (this.installAfterSignin ? 1231 : 1237)) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (!this.packageName.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(1, this.packageName);
            }
            if (this.metadata != null) {
                output.writeMessage(2, this.metadata);
            }
            if (this.supervisionSetting != null) {
                output.writeMessage(3, this.supervisionSetting);
            }
            if (this.deprecatedTag4 != null && this.deprecatedTag4.length > 0) {
                for (byte[] element : this.deprecatedTag4) {
                    if (element != null) {
                        output.writeBytes(4, element);
                    }
                }
            }
            if (this.installTimeMillis != 0) {
                output.writeInt64(5, this.installTimeMillis);
            }
            if (this.installAfterSignin) {
                output.writeBool(6, this.installAfterSignin);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (!this.packageName.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.packageName);
            }
            if (this.metadata != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(2, this.metadata);
            }
            if (this.supervisionSetting != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(3, this.supervisionSetting);
            }
            if (this.deprecatedTag4 != null && this.deprecatedTag4.length > 0) {
                int dataCount = 0;
                int dataSize = 0;
                for (byte[] element : this.deprecatedTag4) {
                    if (element != null) {
                        dataCount++;
                        dataSize += CodedOutputByteBufferNano.computeBytesSizeNoTag(element);
                    }
                }
                size = (size + dataSize) + (dataCount * 1);
            }
            if (this.installTimeMillis != 0) {
                size += CodedOutputByteBufferNano.computeInt64Size(5, this.installTimeMillis);
            }
            if (this.installAfterSignin) {
                return size + CodedOutputByteBufferNano.computeBoolSize(6, this.installAfterSignin);
            }
            return size;
        }

        public App mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        this.packageName = input.readString();
                        continue;
                    case LogSource.GOOGLE_ANALYTICS /*18*/:
                        if (this.metadata == null) {
                            this.metadata = new AppMetadata();
                        }
                        input.readMessage(this.metadata);
                        continue;
                    case LogSource.ANDROID_CAMERA /*26*/:
                        if (this.supervisionSetting == null) {
                            this.supervisionSetting = new AppSupervisionSetting();
                        }
                        input.readMessage(this.supervisionSetting);
                        continue;
                    case LogSource.NOVA /*34*/:
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 34);
                        int i = this.deprecatedTag4 == null ? 0 : this.deprecatedTag4.length;
                        byte[][] newArray = new byte[(i + arrayLength)][];
                        if (i != 0) {
                            System.arraycopy(this.deprecatedTag4, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = input.readBytes();
                            input.readTag();
                            i++;
                        }
                        newArray[i] = input.readBytes();
                        this.deprecatedTag4 = newArray;
                        continue;
                    case LogSource.COPRESENCE /*40*/:
                        this.installTimeMillis = input.readInt64();
                        continue;
                    case LogSource.BACKDROP /*48*/:
                        this.installAfterSignin = input.readBool();
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

        public static App parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (App) MessageNano.mergeFrom(new App(), data);
        }

        public static App parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new App().mergeFrom(input);
        }
    }

    public static final class AppFilter extends ExtendableMessageNano<AppFilter> {
        private static volatile AppFilter[] _emptyArray;
        public boolean excludePreSigninApps;
        public boolean includeBlacklistedApps;
        public String[] packageNames;

        public static AppFilter[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new AppFilter[0];
                    }
                }
            }
            return _emptyArray;
        }

        public AppFilter() {
            clear();
        }

        public AppFilter clear() {
            this.packageNames = WireFormatNano.EMPTY_STRING_ARRAY;
            this.includeBlacklistedApps = false;
            this.excludePreSigninApps = false;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof AppFilter)) {
                return false;
            }
            AppFilter other = (AppFilter) o;
            if (!InternalNano.equals(this.packageNames, other.packageNames) || this.includeBlacklistedApps != other.includeBlacklistedApps || this.excludePreSigninApps != other.excludePreSigninApps) {
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
            int hashCode = (((((getClass().getName().hashCode() + 527) * 31) + InternalNano.hashCode(this.packageNames)) * 31) + (this.includeBlacklistedApps ? 1231 : 1237)) * 31;
            if (!this.excludePreSigninApps) {
                i = 1237;
            }
            i = (hashCode + i) * 31;
            hashCode = (this.unknownFieldData == null || this.unknownFieldData.isEmpty()) ? 0 : this.unknownFieldData.hashCode();
            return i + hashCode;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.packageNames != null && this.packageNames.length > 0) {
                for (String element : this.packageNames) {
                    if (element != null) {
                        output.writeString(1, element);
                    }
                }
            }
            if (this.includeBlacklistedApps) {
                output.writeBool(2, this.includeBlacklistedApps);
            }
            if (this.excludePreSigninApps) {
                output.writeBool(3, this.excludePreSigninApps);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.packageNames != null && this.packageNames.length > 0) {
                int dataCount = 0;
                int dataSize = 0;
                for (String element : this.packageNames) {
                    if (element != null) {
                        dataCount++;
                        dataSize += CodedOutputByteBufferNano.computeStringSizeNoTag(element);
                    }
                }
                size = (size + dataSize) + (dataCount * 1);
            }
            if (this.includeBlacklistedApps) {
                size += CodedOutputByteBufferNano.computeBoolSize(2, this.includeBlacklistedApps);
            }
            if (this.excludePreSigninApps) {
                return size + CodedOutputByteBufferNano.computeBoolSize(3, this.excludePreSigninApps);
            }
            return size;
        }

        public AppFilter mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 10);
                        int i = this.packageNames == null ? 0 : this.packageNames.length;
                        String[] newArray = new String[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.packageNames, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = input.readString();
                            input.readTag();
                            i++;
                        }
                        newArray[i] = input.readString();
                        this.packageNames = newArray;
                        continue;
                    case LogSource.GMS_CORE_PEOPLE /*16*/:
                        this.includeBlacklistedApps = input.readBool();
                        continue;
                    case LogSource.LB_C /*24*/:
                        this.excludePreSigninApps = input.readBool();
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

        public static AppFilter parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (AppFilter) MessageNano.mergeFrom(new AppFilter(), data);
        }

        public static AppFilter parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new AppFilter().mergeFrom(input);
        }
    }

    public static final class AppMetadata extends ExtendableMessageNano<AppMetadata> {
        private static volatile AppMetadata[] _emptyArray;
        public String category;
        public String contentRating;
        public String description;
        public String developer;
        public DisplayCategory displayCategory;
        public int enforcedEnabledStatus;
        public String formattedInAppPurchasePrice;
        public String iconUrl;
        public long installationSize;
        public long installs;
        public int[] interactiveElementIds;
        public InteractiveElement[] interactiveElements;
        public String lastPublished;
        public String[] localizedContentDescriptor;
        public long numRatings;
        public String packageName;
        public String[] permissions;
        public String[] previewImageUrls;
        public String promotionalDescription;
        public float rating;
        public String title;

        public interface EnabledStatus {
            public static final int STATUS_DISABLED = 2;
            public static final int STATUS_ENABLED = 1;
            public static final int STATUS_UNKNOWN = 0;
        }

        public interface InteractiveElementId {
            public static final int DIGITAL_PURCHASES = 4;
            public static final int SHARES_INFO = 2;
            public static final int SHARES_LOCATION = 3;
            public static final int UNKNOWN_INTERACTIVE_ELEMENT_ID = 0;
            public static final int UNRESTRICTED_INTERNET = 5;
            public static final int USERS_INTERACT = 1;
        }

        public static AppMetadata[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new AppMetadata[0];
                    }
                }
            }
            return _emptyArray;
        }

        public AppMetadata() {
            clear();
        }

        public AppMetadata clear() {
            this.packageName = BuildConfig.VERSION_NAME;
            this.title = BuildConfig.VERSION_NAME;
            this.iconUrl = BuildConfig.VERSION_NAME;
            this.developer = BuildConfig.VERSION_NAME;
            this.category = BuildConfig.VERSION_NAME;
            this.contentRating = BuildConfig.VERSION_NAME;
            this.lastPublished = BuildConfig.VERSION_NAME;
            this.permissions = WireFormatNano.EMPTY_STRING_ARRAY;
            this.description = BuildConfig.VERSION_NAME;
            this.promotionalDescription = BuildConfig.VERSION_NAME;
            this.rating = 0.0f;
            this.numRatings = 0;
            this.installationSize = 0;
            this.installs = 0;
            this.interactiveElementIds = WireFormatNano.EMPTY_INT_ARRAY;
            this.interactiveElements = InteractiveElement.emptyArray();
            this.displayCategory = null;
            this.previewImageUrls = WireFormatNano.EMPTY_STRING_ARRAY;
            this.enforcedEnabledStatus = 0;
            this.formattedInAppPurchasePrice = BuildConfig.VERSION_NAME;
            this.localizedContentDescriptor = WireFormatNano.EMPTY_STRING_ARRAY;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof AppMetadata)) {
                return false;
            }
            AppMetadata other = (AppMetadata) o;
            if (this.packageName == null) {
                if (other.packageName != null) {
                    return false;
                }
            } else if (!this.packageName.equals(other.packageName)) {
                return false;
            }
            if (this.title == null) {
                if (other.title != null) {
                    return false;
                }
            } else if (!this.title.equals(other.title)) {
                return false;
            }
            if (this.iconUrl == null) {
                if (other.iconUrl != null) {
                    return false;
                }
            } else if (!this.iconUrl.equals(other.iconUrl)) {
                return false;
            }
            if (this.developer == null) {
                if (other.developer != null) {
                    return false;
                }
            } else if (!this.developer.equals(other.developer)) {
                return false;
            }
            if (this.category == null) {
                if (other.category != null) {
                    return false;
                }
            } else if (!this.category.equals(other.category)) {
                return false;
            }
            if (this.contentRating == null) {
                if (other.contentRating != null) {
                    return false;
                }
            } else if (!this.contentRating.equals(other.contentRating)) {
                return false;
            }
            if (this.lastPublished == null) {
                if (other.lastPublished != null) {
                    return false;
                }
            } else if (!this.lastPublished.equals(other.lastPublished)) {
                return false;
            }
            if (!InternalNano.equals(this.permissions, other.permissions)) {
                return false;
            }
            if (this.description == null) {
                if (other.description != null) {
                    return false;
                }
            } else if (!this.description.equals(other.description)) {
                return false;
            }
            if (this.promotionalDescription == null) {
                if (other.promotionalDescription != null) {
                    return false;
                }
            } else if (!this.promotionalDescription.equals(other.promotionalDescription)) {
                return false;
            }
            if (Float.floatToIntBits(this.rating) != Float.floatToIntBits(other.rating) || this.numRatings != other.numRatings || this.installationSize != other.installationSize || this.installs != other.installs || !InternalNano.equals(this.interactiveElementIds, other.interactiveElementIds) || !InternalNano.equals(this.interactiveElements, other.interactiveElements)) {
                return false;
            }
            if (this.displayCategory == null) {
                if (other.displayCategory != null) {
                    return false;
                }
            } else if (!this.displayCategory.equals(other.displayCategory)) {
                return false;
            }
            if (!InternalNano.equals(this.previewImageUrls, other.previewImageUrls) || this.enforcedEnabledStatus != other.enforcedEnabledStatus) {
                return false;
            }
            if (this.formattedInAppPurchasePrice == null) {
                if (other.formattedInAppPurchasePrice != null) {
                    return false;
                }
            } else if (!this.formattedInAppPurchasePrice.equals(other.formattedInAppPurchasePrice)) {
                return false;
            }
            if (!InternalNano.equals(this.localizedContentDescriptor, other.localizedContentDescriptor)) {
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
            int hashCode = (((((((((((((((((((getClass().getName().hashCode() + 527) * 31) + (this.packageName == null ? 0 : this.packageName.hashCode())) * 31) + (this.title == null ? 0 : this.title.hashCode())) * 31) + (this.iconUrl == null ? 0 : this.iconUrl.hashCode())) * 31) + (this.developer == null ? 0 : this.developer.hashCode())) * 31) + (this.category == null ? 0 : this.category.hashCode())) * 31) + (this.contentRating == null ? 0 : this.contentRating.hashCode())) * 31) + (this.lastPublished == null ? 0 : this.lastPublished.hashCode())) * 31) + InternalNano.hashCode(this.permissions)) * 31) + (this.description == null ? 0 : this.description.hashCode())) * 31;
            if (this.promotionalDescription == null) {
                i = 0;
            } else {
                i = this.promotionalDescription.hashCode();
            }
            i = (((((((((((((((((((((((hashCode + i) * 31) + Float.floatToIntBits(this.rating)) * 31) + ((int) (this.numRatings ^ (this.numRatings >>> 32)))) * 31) + ((int) (this.installationSize ^ (this.installationSize >>> 32)))) * 31) + ((int) (this.installs ^ (this.installs >>> 32)))) * 31) + InternalNano.hashCode(this.interactiveElementIds)) * 31) + InternalNano.hashCode(this.interactiveElements)) * 31) + (this.displayCategory == null ? 0 : this.displayCategory.hashCode())) * 31) + InternalNano.hashCode(this.previewImageUrls)) * 31) + this.enforcedEnabledStatus) * 31) + (this.formattedInAppPurchasePrice == null ? 0 : this.formattedInAppPurchasePrice.hashCode())) * 31) + InternalNano.hashCode(this.localizedContentDescriptor)) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i2 = this.unknownFieldData.hashCode();
            }
            return i + i2;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (!this.title.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(1, this.title);
            }
            if (!this.iconUrl.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(2, this.iconUrl);
            }
            if (!this.developer.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(3, this.developer);
            }
            if (!this.packageName.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(4, this.packageName);
            }
            if (!this.category.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(5, this.category);
            }
            if (!this.contentRating.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(6, this.contentRating);
            }
            if (!this.lastPublished.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(7, this.lastPublished);
            }
            if (this.permissions != null && this.permissions.length > 0) {
                for (String element : this.permissions) {
                    if (element != null) {
                        output.writeString(8, element);
                    }
                }
            }
            if (!this.description.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(9, this.description);
            }
            if (Float.floatToIntBits(this.rating) != Float.floatToIntBits(0.0f)) {
                output.writeFloat(10, this.rating);
            }
            if (this.installationSize != 0) {
                output.writeInt64(11, this.installationSize);
            }
            if (this.installs != 0) {
                output.writeInt64(12, this.installs);
            }
            if (this.interactiveElementIds != null && this.interactiveElementIds.length > 0) {
                for (int writeInt32 : this.interactiveElementIds) {
                    output.writeInt32(13, writeInt32);
                }
            }
            if (this.displayCategory != null) {
                output.writeMessage(14, this.displayCategory);
            }
            if (!this.promotionalDescription.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(15, this.promotionalDescription);
            }
            if (this.interactiveElements != null && this.interactiveElements.length > 0) {
                for (InteractiveElement element2 : this.interactiveElements) {
                    if (element2 != null) {
                        output.writeMessage(16, element2);
                    }
                }
            }
            if (this.previewImageUrls != null && this.previewImageUrls.length > 0) {
                for (String element3 : this.previewImageUrls) {
                    if (element3 != null) {
                        output.writeString(17, element3);
                    }
                }
            }
            if (this.enforcedEnabledStatus != 0) {
                output.writeInt32(18, this.enforcedEnabledStatus);
            }
            if (this.numRatings != 0) {
                output.writeInt64(19, this.numRatings);
            }
            if (!this.formattedInAppPurchasePrice.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(20, this.formattedInAppPurchasePrice);
            }
            if (this.localizedContentDescriptor != null && this.localizedContentDescriptor.length > 0) {
                for (String element32 : this.localizedContentDescriptor) {
                    if (element32 != null) {
                        output.writeString(21, element32);
                    }
                }
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int dataCount;
            int dataSize;
            int size = super.computeSerializedSize();
            if (!this.title.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.title);
            }
            if (!this.iconUrl.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(2, this.iconUrl);
            }
            if (!this.developer.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(3, this.developer);
            }
            if (!this.packageName.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(4, this.packageName);
            }
            if (!this.category.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(5, this.category);
            }
            if (!this.contentRating.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(6, this.contentRating);
            }
            if (!this.lastPublished.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(7, this.lastPublished);
            }
            if (this.permissions != null && this.permissions.length > 0) {
                dataCount = 0;
                dataSize = 0;
                for (String element : this.permissions) {
                    if (element != null) {
                        dataCount++;
                        dataSize += CodedOutputByteBufferNano.computeStringSizeNoTag(element);
                    }
                }
                size = (size + dataSize) + (dataCount * 1);
            }
            if (!this.description.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(9, this.description);
            }
            if (Float.floatToIntBits(this.rating) != Float.floatToIntBits(0.0f)) {
                size += CodedOutputByteBufferNano.computeFloatSize(10, this.rating);
            }
            if (this.installationSize != 0) {
                size += CodedOutputByteBufferNano.computeInt64Size(11, this.installationSize);
            }
            if (this.installs != 0) {
                size += CodedOutputByteBufferNano.computeInt64Size(12, this.installs);
            }
            if (this.interactiveElementIds != null && this.interactiveElementIds.length > 0) {
                dataSize = 0;
                for (int element2 : this.interactiveElementIds) {
                    dataSize += CodedOutputByteBufferNano.computeInt32SizeNoTag(element2);
                }
                size = (size + dataSize) + (this.interactiveElementIds.length * 1);
            }
            if (this.displayCategory != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(14, this.displayCategory);
            }
            if (!this.promotionalDescription.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(15, this.promotionalDescription);
            }
            if (this.interactiveElements != null && this.interactiveElements.length > 0) {
                for (InteractiveElement element3 : this.interactiveElements) {
                    if (element3 != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(16, element3);
                    }
                }
            }
            if (this.previewImageUrls != null && this.previewImageUrls.length > 0) {
                dataCount = 0;
                dataSize = 0;
                for (String element4 : this.previewImageUrls) {
                    if (element4 != null) {
                        dataCount++;
                        dataSize += CodedOutputByteBufferNano.computeStringSizeNoTag(element4);
                    }
                }
                size = (size + dataSize) + (dataCount * 2);
            }
            if (this.enforcedEnabledStatus != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(18, this.enforcedEnabledStatus);
            }
            if (this.numRatings != 0) {
                size += CodedOutputByteBufferNano.computeInt64Size(19, this.numRatings);
            }
            if (!this.formattedInAppPurchasePrice.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(20, this.formattedInAppPurchasePrice);
            }
            if (this.localizedContentDescriptor == null || this.localizedContentDescriptor.length <= 0) {
                return size;
            }
            dataCount = 0;
            dataSize = 0;
            for (String element42 : this.localizedContentDescriptor) {
                if (element42 != null) {
                    dataCount++;
                    dataSize += CodedOutputByteBufferNano.computeStringSizeNoTag(element42);
                }
            }
            return (size + dataSize) + (dataCount * 2);
        }

        public AppMetadata mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                int arrayLength;
                int i;
                String[] newArray;
                int value;
                int[] newArray2;
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        this.title = input.readString();
                        continue;
                    case LogSource.GOOGLE_ANALYTICS /*18*/:
                        this.iconUrl = input.readString();
                        continue;
                    case LogSource.ANDROID_CAMERA /*26*/:
                        this.developer = input.readString();
                        continue;
                    case LogSource.NOVA /*34*/:
                        this.packageName = input.readString();
                        continue;
                    case LogSource.PHOTOS /*42*/:
                        this.category = input.readString();
                        continue;
                    case LogSource.HANGOUT_LOG_REQUEST /*50*/:
                        this.contentRating = input.readString();
                        continue;
                    case LogSource.SLIDES /*58*/:
                        this.lastPublished = input.readString();
                        continue;
                    case LogSource.WIFI_ASSISTANT /*66*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 66);
                        if (this.permissions == null) {
                            i = 0;
                        } else {
                            i = this.permissions.length;
                        }
                        newArray = new String[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.permissions, 0, newArray, 0, i);
                        }
                        while (true) {
                            if (i < newArray.length - 1) {
                                newArray[i] = input.readString();
                                input.readTag();
                                i++;
                            } else {
                                newArray[i] = input.readString();
                                this.permissions = newArray;
                                continue;
                            }
                        }
                    case LogSource.MOVIES /*74*/:
                        this.description = input.readString();
                        continue;
                    case LogSource.VR /*85*/:
                        this.rating = input.readFloat();
                        continue;
                    case LogSource.EMERGENCY_ASSIST /*88*/:
                        this.installationSize = input.readInt64();
                        continue;
                    case LogSource.GCM_COUNTERS /*96*/:
                        this.installs = input.readInt64();
                        continue;
                    case LogSource.MOBILESDK_CLIENT /*104*/:
                        int length = WireFormatNano.getRepeatedFieldArrayLength(input, LogSource.MOBILESDK_CLIENT);
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
                                case OvenFreshResult.NO_ACCOUNTS /*4*/:
                                case Type.ADD_NEW_SHARES /*5*/:
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
                            if (this.interactiveElementIds == null) {
                                i = 0;
                            } else {
                                i = this.interactiveElementIds.length;
                            }
                            if (i == 0) {
                                int length2 = validValues.length;
                                if (validCount == r0) {
                                    this.interactiveElementIds = validValues;
                                    break;
                                }
                            }
                            newArray2 = new int[(i + validCount)];
                            if (i != 0) {
                                System.arraycopy(this.interactiveElementIds, 0, newArray2, 0, i);
                            }
                            System.arraycopy(validValues, 0, newArray2, i, validCount);
                            this.interactiveElementIds = newArray2;
                            break;
                        }
                        continue;
                    case LogSource.ADSHIELD /*106*/:
                        int limit = input.pushLimit(input.readRawVarint32());
                        arrayLength = 0;
                        int startPos = input.getPosition();
                        while (input.getBytesUntilLimit() > 0) {
                            switch (input.readInt32()) {
                                case Action.UNKNOWN /*0*/:
                                case TimeSelection.Type.TEMPORARY /*1*/:
                                case TimeSelection.Type.INDEFINITELY /*2*/:
                                case TimeSelection.Type.CUSTOM /*3*/:
                                case OvenFreshResult.NO_ACCOUNTS /*4*/:
                                case Type.ADD_NEW_SHARES /*5*/:
                                    arrayLength++;
                                    break;
                                default:
                                    break;
                            }
                        }
                        if (arrayLength != 0) {
                            input.rewindToPosition(startPos);
                            if (this.interactiveElementIds == null) {
                                i = 0;
                            } else {
                                i = this.interactiveElementIds.length;
                            }
                            newArray2 = new int[(i + arrayLength)];
                            if (i != 0) {
                                System.arraycopy(this.interactiveElementIds, 0, newArray2, 0, i);
                            }
                            while (input.getBytesUntilLimit() > 0) {
                                value = input.readInt32();
                                switch (value) {
                                    case Action.UNKNOWN /*0*/:
                                    case TimeSelection.Type.TEMPORARY /*1*/:
                                    case TimeSelection.Type.INDEFINITELY /*2*/:
                                    case TimeSelection.Type.CUSTOM /*3*/:
                                    case OvenFreshResult.NO_ACCOUNTS /*4*/:
                                    case Type.ADD_NEW_SHARES /*5*/:
                                        int i2 = i + 1;
                                        newArray2[i] = value;
                                        i = i2;
                                        break;
                                    default:
                                        break;
                                }
                            }
                            this.interactiveElementIds = newArray2;
                        }
                        input.popLimit(limit);
                        continue;
                    case LogSource.TRANSOM /*114*/:
                        if (this.displayCategory == null) {
                            this.displayCategory = new DisplayCategory();
                        }
                        input.readMessage(this.displayCategory);
                        continue;
                    case LogSource.ANDROID_SNET_GCORE /*122*/:
                        this.promotionalDescription = input.readString();
                        continue;
                    case LogSource.CHROMECAST_APP_LOG /*130*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, LogSource.CHROMECAST_APP_LOG);
                        if (this.interactiveElements == null) {
                            i = 0;
                        } else {
                            i = this.interactiveElements.length;
                        }
                        InteractiveElement[] newArray3 = new InteractiveElement[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.interactiveElements, 0, newArray3, 0, i);
                        }
                        while (true) {
                            if (i < newArray3.length - 1) {
                                newArray3[i] = new InteractiveElement();
                                input.readMessage(newArray3[i]);
                                input.readTag();
                                i++;
                            } else {
                                newArray3[i] = new InteractiveElement();
                                input.readMessage(newArray3[i]);
                                this.interactiveElements = newArray3;
                                continue;
                            }
                        }
                    case LogSource.PANCETTA_MOBILE_HOST /*138*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, LogSource.PANCETTA_MOBILE_HOST);
                        if (this.previewImageUrls == null) {
                            i = 0;
                        } else {
                            i = this.previewImageUrls.length;
                        }
                        newArray = new String[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.previewImageUrls, 0, newArray, 0, i);
                        }
                        while (true) {
                            if (i < newArray.length - 1) {
                                newArray[i] = input.readString();
                                input.readTag();
                                i++;
                            } else {
                                newArray[i] = input.readString();
                                this.previewImageUrls = newArray;
                                continue;
                            }
                        }
                    case 144:
                        value = input.readInt32();
                        switch (value) {
                            case Action.UNKNOWN /*0*/:
                            case TimeSelection.Type.TEMPORARY /*1*/:
                            case TimeSelection.Type.INDEFINITELY /*2*/:
                                this.enforcedEnabledStatus = value;
                                break;
                            default:
                                continue;
                        }
                    case 152:
                        this.numRatings = input.readInt64();
                        continue;
                    case 162:
                        this.formattedInAppPurchasePrice = input.readString();
                        continue;
                    case 170:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 170);
                        if (this.localizedContentDescriptor == null) {
                            i = 0;
                        } else {
                            i = this.localizedContentDescriptor.length;
                        }
                        newArray = new String[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.localizedContentDescriptor, 0, newArray, 0, i);
                        }
                        while (true) {
                            if (i < newArray.length - 1) {
                                newArray[i] = input.readString();
                                input.readTag();
                                i++;
                            } else {
                                newArray[i] = input.readString();
                                this.localizedContentDescriptor = newArray;
                                continue;
                            }
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

        public static AppMetadata parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (AppMetadata) MessageNano.mergeFrom(new AppMetadata(), data);
        }

        public static AppMetadata parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new AppMetadata().mergeFrom(input);
        }
    }

    public static final class AppSupervisionSetting extends ExtendableMessageNano<AppSupervisionSetting> {
        private static volatile AppSupervisionSetting[] _emptyArray;
        public boolean hidden;
        public boolean hiddenSetExplicitly;

        public static AppSupervisionSetting[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new AppSupervisionSetting[0];
                    }
                }
            }
            return _emptyArray;
        }

        public AppSupervisionSetting() {
            clear();
        }

        public AppSupervisionSetting clear() {
            this.hidden = false;
            this.hiddenSetExplicitly = false;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof AppSupervisionSetting)) {
                return false;
            }
            AppSupervisionSetting other = (AppSupervisionSetting) o;
            if (this.hidden != other.hidden || this.hiddenSetExplicitly != other.hiddenSetExplicitly) {
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
            int hashCode = (((getClass().getName().hashCode() + 527) * 31) + (this.hidden ? 1231 : 1237)) * 31;
            if (!this.hiddenSetExplicitly) {
                i = 1237;
            }
            i = (hashCode + i) * 31;
            hashCode = (this.unknownFieldData == null || this.unknownFieldData.isEmpty()) ? 0 : this.unknownFieldData.hashCode();
            return i + hashCode;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.hidden) {
                output.writeBool(1, this.hidden);
            }
            if (this.hiddenSetExplicitly) {
                output.writeBool(2, this.hiddenSetExplicitly);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.hidden) {
                size += CodedOutputByteBufferNano.computeBoolSize(1, this.hidden);
            }
            if (this.hiddenSetExplicitly) {
                return size + CodedOutputByteBufferNano.computeBoolSize(2, this.hiddenSetExplicitly);
            }
            return size;
        }

        public AppSupervisionSetting mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_GET_LINK /*8*/:
                        this.hidden = input.readBool();
                        continue;
                    case LogSource.GMS_CORE_PEOPLE /*16*/:
                        this.hiddenSetExplicitly = input.readBool();
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

        public static AppSupervisionSetting parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (AppSupervisionSetting) MessageNano.mergeFrom(new AppSupervisionSetting(), data);
        }

        public static AppSupervisionSetting parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new AppSupervisionSetting().mergeFrom(input);
        }
    }

    public static final class DisplayCategory extends ExtendableMessageNano<DisplayCategory> {
        private static volatile DisplayCategory[] _emptyArray;
        public String badgeUrl;
        public String description;
        public int id;
        public String inlineIconUrl;
        public String name;
        public int order;
        public String titleIconUrl;
        public int type;

        public interface CategoryId {
            public static final int ESRB_ADULTS_ONLY = 105;
            public static final int ESRB_EVERYONE = 101;
            public static final int ESRB_EVERYONE_TEN_PLUS = 102;
            public static final int ESRB_MATURE = 104;
            public static final int ESRB_TEEN = 103;
            public static final int ESRB_UNKNOWN = 199;
            public static final int UNKNOWN_CATEGORY_ID = 0;
        }

        public interface Type {
            public static final int ESRB = 1;
            public static final int UNKNOWN_DISPLAY_CATEGORY_TYPE = 0;
        }

        public static DisplayCategory[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new DisplayCategory[0];
                    }
                }
            }
            return _emptyArray;
        }

        public DisplayCategory() {
            clear();
        }

        public DisplayCategory clear() {
            this.type = 0;
            this.id = 0;
            this.order = 0;
            this.name = BuildConfig.VERSION_NAME;
            this.badgeUrl = BuildConfig.VERSION_NAME;
            this.titleIconUrl = BuildConfig.VERSION_NAME;
            this.description = BuildConfig.VERSION_NAME;
            this.inlineIconUrl = BuildConfig.VERSION_NAME;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof DisplayCategory)) {
                return false;
            }
            DisplayCategory other = (DisplayCategory) o;
            if (this.type != other.type || this.id != other.id || this.order != other.order) {
                return false;
            }
            if (this.name == null) {
                if (other.name != null) {
                    return false;
                }
            } else if (!this.name.equals(other.name)) {
                return false;
            }
            if (this.badgeUrl == null) {
                if (other.badgeUrl != null) {
                    return false;
                }
            } else if (!this.badgeUrl.equals(other.badgeUrl)) {
                return false;
            }
            if (this.titleIconUrl == null) {
                if (other.titleIconUrl != null) {
                    return false;
                }
            } else if (!this.titleIconUrl.equals(other.titleIconUrl)) {
                return false;
            }
            if (this.description == null) {
                if (other.description != null) {
                    return false;
                }
            } else if (!this.description.equals(other.description)) {
                return false;
            }
            if (this.inlineIconUrl == null) {
                if (other.inlineIconUrl != null) {
                    return false;
                }
            } else if (!this.inlineIconUrl.equals(other.inlineIconUrl)) {
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
            int hashCode = (((((((((((((((((getClass().getName().hashCode() + 527) * 31) + this.type) * 31) + this.id) * 31) + this.order) * 31) + (this.name == null ? 0 : this.name.hashCode())) * 31) + (this.badgeUrl == null ? 0 : this.badgeUrl.hashCode())) * 31) + (this.titleIconUrl == null ? 0 : this.titleIconUrl.hashCode())) * 31) + (this.description == null ? 0 : this.description.hashCode())) * 31) + (this.inlineIconUrl == null ? 0 : this.inlineIconUrl.hashCode())) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.type != 0) {
                output.writeInt32(1, this.type);
            }
            if (this.id != 0) {
                output.writeInt32(2, this.id);
            }
            if (this.order != 0) {
                output.writeInt32(3, this.order);
            }
            if (!this.name.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(4, this.name);
            }
            if (!this.badgeUrl.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(5, this.badgeUrl);
            }
            if (!this.description.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(6, this.description);
            }
            if (!this.inlineIconUrl.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(7, this.inlineIconUrl);
            }
            if (!this.titleIconUrl.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(8, this.titleIconUrl);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.type != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(1, this.type);
            }
            if (this.id != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(2, this.id);
            }
            if (this.order != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(3, this.order);
            }
            if (!this.name.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(4, this.name);
            }
            if (!this.badgeUrl.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(5, this.badgeUrl);
            }
            if (!this.description.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(6, this.description);
            }
            if (!this.inlineIconUrl.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(7, this.inlineIconUrl);
            }
            if (this.titleIconUrl.equals(BuildConfig.VERSION_NAME)) {
                return size;
            }
            return size + CodedOutputByteBufferNano.computeStringSize(8, this.titleIconUrl);
        }

        public DisplayCategory mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                int value;
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case com.google.wireless.android.play.playlog.proto.UserLocation.LocationSharingEvent.Type.TAP_GET_LINK /*8*/:
                        value = input.readInt32();
                        switch (value) {
                            case Action.UNKNOWN /*0*/:
                            case com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection.Type.TEMPORARY /*1*/:
                                this.type = value;
                                break;
                            default:
                                continue;
                        }
                    case LogSource.GMS_CORE_PEOPLE /*16*/:
                        value = input.readInt32();
                        switch (value) {
                            case Action.UNKNOWN /*0*/:
                            case LogSource.SOCIAL_USER_LOCATION /*101*/:
                            case LogSource.LAUNCHPAD_TOYS /*102*/:
                            case LogSource.METALOG_COUNTERS /*103*/:
                            case LogSource.MOBILESDK_CLIENT /*104*/:
                            case LogSource.ANDROID_VERIFY_APPS /*105*/:
                            case CategoryId.ESRB_UNKNOWN /*199*/:
                                this.id = value;
                                break;
                            default:
                                continue;
                        }
                    case LogSource.LB_C /*24*/:
                        this.order = input.readInt32();
                        continue;
                    case LogSource.NOVA /*34*/:
                        this.name = input.readString();
                        continue;
                    case LogSource.PHOTOS /*42*/:
                        this.badgeUrl = input.readString();
                        continue;
                    case LogSource.HANGOUT_LOG_REQUEST /*50*/:
                        this.description = input.readString();
                        continue;
                    case LogSource.SLIDES /*58*/:
                        this.inlineIconUrl = input.readString();
                        continue;
                    case LogSource.WIFI_ASSISTANT /*66*/:
                        this.titleIconUrl = input.readString();
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

        public static DisplayCategory parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (DisplayCategory) MessageNano.mergeFrom(new DisplayCategory(), data);
        }

        public static DisplayCategory parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new DisplayCategory().mergeFrom(input);
        }
    }

    public static final class InteractiveElement extends ExtendableMessageNano<InteractiveElement> {
        private static volatile InteractiveElement[] _emptyArray;
        public String iconUrl;
        public int id;
        public String title;

        public interface Id {
            public static final int DIGITAL_PURCHASES = 4;
            public static final int SHARES_INFO = 2;
            public static final int SHARES_LOCATION = 3;
            public static final int UNKNOWN_ID = 0;
            public static final int UNRESTRICTED_INTERNET = 5;
            public static final int USERS_INTERACT = 1;
        }

        public static InteractiveElement[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new InteractiveElement[0];
                    }
                }
            }
            return _emptyArray;
        }

        public InteractiveElement() {
            clear();
        }

        public InteractiveElement clear() {
            this.id = 0;
            this.iconUrl = BuildConfig.VERSION_NAME;
            this.title = BuildConfig.VERSION_NAME;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof InteractiveElement)) {
                return false;
            }
            InteractiveElement other = (InteractiveElement) o;
            if (this.id != other.id) {
                return false;
            }
            if (this.iconUrl == null) {
                if (other.iconUrl != null) {
                    return false;
                }
            } else if (!this.iconUrl.equals(other.iconUrl)) {
                return false;
            }
            if (this.title == null) {
                if (other.title != null) {
                    return false;
                }
            } else if (!this.title.equals(other.title)) {
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
            int hashCode = (((((((getClass().getName().hashCode() + 527) * 31) + this.id) * 31) + (this.iconUrl == null ? 0 : this.iconUrl.hashCode())) * 31) + (this.title == null ? 0 : this.title.hashCode())) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.id != 0) {
                output.writeInt32(1, this.id);
            }
            if (!this.iconUrl.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(2, this.iconUrl);
            }
            if (!this.title.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(3, this.title);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.id != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(1, this.id);
            }
            if (!this.iconUrl.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(2, this.iconUrl);
            }
            if (this.title.equals(BuildConfig.VERSION_NAME)) {
                return size;
            }
            return size + CodedOutputByteBufferNano.computeStringSize(3, this.title);
        }

        public InteractiveElement mergeFrom(CodedInputByteBufferNano input) throws IOException {
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
                            case TimeSelection.Type.CUSTOM /*3*/:
                            case OvenFreshResult.NO_ACCOUNTS /*4*/:
                            case Type.ADD_NEW_SHARES /*5*/:
                                this.id = value;
                                break;
                            default:
                                continue;
                        }
                    case LogSource.GOOGLE_ANALYTICS /*18*/:
                        this.iconUrl = input.readString();
                        continue;
                    case LogSource.ANDROID_CAMERA /*26*/:
                        this.title = input.readString();
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

        public static InteractiveElement parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (InteractiveElement) MessageNano.mergeFrom(new InteractiveElement(), data);
        }

        public static InteractiveElement parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new InteractiveElement().mergeFrom(input);
        }
    }
}
