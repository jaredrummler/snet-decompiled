package com.google.kidsmanagement.v1;

import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.ExtendableMessageNano;
import com.google.protobuf.nano.InternalNano;
import com.google.protobuf.nano.InvalidProtocolBufferNanoException;
import com.google.protobuf.nano.MessageNano;
import com.google.wireless.android.play.playlog.proto.ClientAnalytics.LogRequest.LogSource;
import com.google.wireless.android.play.playlog.proto.UserLocation.LaunchSettingsEvent.Action;
import com.google.wireless.android.play.playlog.proto.UserLocation.LocationSharingEvent.Type;
import com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection;
import java.io.IOException;

public interface GoogleServicesCommonProto {

    public interface ChromeFilteringBehavior {
        public static final int CHROME_FILTERING_ALLOW = 1;
        public static final int CHROME_FILTERING_BLOCK = 2;
        public static final int UNKNOWN_CHROME_FILTERING_BEHAVIOR = 0;
    }

    public interface ChromePermissionRequestBehavior {
        public static final int CHROME_PERMISSION_REQUEST_ALLOW = 1;
        public static final int CHROME_PERMISSION_REQUEST_BLOCK = 2;
        public static final int UNKNOWN_CHROME_PERMISSION_REQUEST_BEHAVIOR = 0;
    }

    public interface ChromeSafeSitesBehavior {
        public static final int CHROME_SAFE_SITES_OFF = 2;
        public static final int CHROME_SAFE_SITES_ON = 1;
        public static final int UNKNOWN_CHROME_SAFE_SITES_BEHAVIOR = 0;
    }

    public static final class ChromeSettings extends ExtendableMessageNano<ChromeSettings> {
        private static volatile ChromeSettings[] _emptyArray;
        public ChromeSettingsDisplayData chromeSettingsDisplayData;
        public int filteringBehavior;
        public int permissionRequestBehavior;
        public int safeSitesBehavior;

        public static ChromeSettings[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new ChromeSettings[0];
                    }
                }
            }
            return _emptyArray;
        }

        public ChromeSettings() {
            clear();
        }

        public ChromeSettings clear() {
            this.filteringBehavior = 0;
            this.permissionRequestBehavior = 0;
            this.safeSitesBehavior = 0;
            this.chromeSettingsDisplayData = null;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof ChromeSettings)) {
                return false;
            }
            ChromeSettings other = (ChromeSettings) o;
            if (this.filteringBehavior != other.filteringBehavior || this.permissionRequestBehavior != other.permissionRequestBehavior || this.safeSitesBehavior != other.safeSitesBehavior) {
                return false;
            }
            if (this.chromeSettingsDisplayData == null) {
                if (other.chromeSettingsDisplayData != null) {
                    return false;
                }
            } else if (!this.chromeSettingsDisplayData.equals(other.chromeSettingsDisplayData)) {
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
            int hashCode = (((((((((getClass().getName().hashCode() + 527) * 31) + this.filteringBehavior) * 31) + this.permissionRequestBehavior) * 31) + this.safeSitesBehavior) * 31) + (this.chromeSettingsDisplayData == null ? 0 : this.chromeSettingsDisplayData.hashCode())) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.filteringBehavior != 0) {
                output.writeInt32(1, this.filteringBehavior);
            }
            if (this.permissionRequestBehavior != 0) {
                output.writeInt32(2, this.permissionRequestBehavior);
            }
            if (this.safeSitesBehavior != 0) {
                output.writeInt32(3, this.safeSitesBehavior);
            }
            if (this.chromeSettingsDisplayData != null) {
                output.writeMessage(4, this.chromeSettingsDisplayData);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.filteringBehavior != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(1, this.filteringBehavior);
            }
            if (this.permissionRequestBehavior != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(2, this.permissionRequestBehavior);
            }
            if (this.safeSitesBehavior != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(3, this.safeSitesBehavior);
            }
            if (this.chromeSettingsDisplayData != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(4, this.chromeSettingsDisplayData);
            }
            return size;
        }

        public ChromeSettings mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                int value;
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_GET_LINK /*8*/:
                        value = input.readInt32();
                        switch (value) {
                            case Action.UNKNOWN /*0*/:
                            case TimeSelection.Type.TEMPORARY /*1*/:
                            case TimeSelection.Type.INDEFINITELY /*2*/:
                                this.filteringBehavior = value;
                                break;
                            default:
                                continue;
                        }
                    case LogSource.GMS_CORE_PEOPLE /*16*/:
                        value = input.readInt32();
                        switch (value) {
                            case Action.UNKNOWN /*0*/:
                            case TimeSelection.Type.TEMPORARY /*1*/:
                            case TimeSelection.Type.INDEFINITELY /*2*/:
                                this.permissionRequestBehavior = value;
                                break;
                            default:
                                continue;
                        }
                    case LogSource.LB_C /*24*/:
                        value = input.readInt32();
                        switch (value) {
                            case Action.UNKNOWN /*0*/:
                            case TimeSelection.Type.TEMPORARY /*1*/:
                            case TimeSelection.Type.INDEFINITELY /*2*/:
                                this.safeSitesBehavior = value;
                                break;
                            default:
                                continue;
                        }
                    case LogSource.NOVA /*34*/:
                        if (this.chromeSettingsDisplayData == null) {
                            this.chromeSettingsDisplayData = new ChromeSettingsDisplayData();
                        }
                        input.readMessage(this.chromeSettingsDisplayData);
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

        public static ChromeSettings parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (ChromeSettings) MessageNano.mergeFrom(new ChromeSettings(), data);
        }

        public static ChromeSettings parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new ChromeSettings().mergeFrom(input);
        }
    }

    public static final class ChromeSettingsDisplayData extends ExtendableMessageNano<ChromeSettingsDisplayData> {
        private static volatile ChromeSettingsDisplayData[] _emptyArray;
        public int allowedSitesCount;
        public int blockedSitesCount;

        public static ChromeSettingsDisplayData[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new ChromeSettingsDisplayData[0];
                    }
                }
            }
            return _emptyArray;
        }

        public ChromeSettingsDisplayData() {
            clear();
        }

        public ChromeSettingsDisplayData clear() {
            this.allowedSitesCount = 0;
            this.blockedSitesCount = 0;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof ChromeSettingsDisplayData)) {
                return false;
            }
            ChromeSettingsDisplayData other = (ChromeSettingsDisplayData) o;
            if (this.allowedSitesCount != other.allowedSitesCount || this.blockedSitesCount != other.blockedSitesCount) {
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
            int hashCode = (((((getClass().getName().hashCode() + 527) * 31) + this.allowedSitesCount) * 31) + this.blockedSitesCount) * 31;
            int hashCode2 = (this.unknownFieldData == null || this.unknownFieldData.isEmpty()) ? 0 : this.unknownFieldData.hashCode();
            return hashCode + hashCode2;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.allowedSitesCount != 0) {
                output.writeInt32(1, this.allowedSitesCount);
            }
            if (this.blockedSitesCount != 0) {
                output.writeInt32(2, this.blockedSitesCount);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.allowedSitesCount != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(1, this.allowedSitesCount);
            }
            if (this.blockedSitesCount != 0) {
                return size + CodedOutputByteBufferNano.computeInt32Size(2, this.blockedSitesCount);
            }
            return size;
        }

        public ChromeSettingsDisplayData mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_GET_LINK /*8*/:
                        this.allowedSitesCount = input.readInt32();
                        continue;
                    case LogSource.GMS_CORE_PEOPLE /*16*/:
                        this.blockedSitesCount = input.readInt32();
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

        public static ChromeSettingsDisplayData parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (ChromeSettingsDisplayData) MessageNano.mergeFrom(new ChromeSettingsDisplayData(), data);
        }

        public static ChromeSettingsDisplayData parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new ChromeSettingsDisplayData().mergeFrom(input);
        }
    }

    public interface SafeSearchSetting {
        public static final int SAFE_SEARCH_OFF = 2;
        public static final int SAFE_SEARCH_ON = 1;
        public static final int UNKNOWN_SAFE_SEARCH_BEHAVIOR = 0;
    }

    public static final class SearchSettings extends ExtendableMessageNano<SearchSettings> {
        private static volatile SearchSettings[] _emptyArray;
        public int safeSearchSetting;

        public static SearchSettings[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new SearchSettings[0];
                    }
                }
            }
            return _emptyArray;
        }

        public SearchSettings() {
            clear();
        }

        public SearchSettings clear() {
            this.safeSearchSetting = 0;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof SearchSettings)) {
                return false;
            }
            SearchSettings other = (SearchSettings) o;
            if (this.safeSearchSetting != other.safeSearchSetting) {
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
            int hashCode = (((getClass().getName().hashCode() + 527) * 31) + this.safeSearchSetting) * 31;
            int hashCode2 = (this.unknownFieldData == null || this.unknownFieldData.isEmpty()) ? 0 : this.unknownFieldData.hashCode();
            return hashCode + hashCode2;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.safeSearchSetting != 0) {
                output.writeInt32(1, this.safeSearchSetting);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.safeSearchSetting != 0) {
                return size + CodedOutputByteBufferNano.computeInt32Size(1, this.safeSearchSetting);
            }
            return size;
        }

        public SearchSettings mergeFrom(CodedInputByteBufferNano input) throws IOException {
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
                                this.safeSearchSetting = value;
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

        public static SearchSettings parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (SearchSettings) MessageNano.mergeFrom(new SearchSettings(), data);
        }

        public static SearchSettings parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new SearchSettings().mergeFrom(input);
        }
    }
}
