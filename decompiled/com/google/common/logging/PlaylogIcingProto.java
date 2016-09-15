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
import com.google.wireless.android.play.playlog.proto.UserLocation.OvenFreshEvent.OvenFreshResult;
import com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection;
import java.io.IOException;

public interface PlaylogIcingProto {

    public static final class AndroidForWorkStats extends ExtendableMessageNano<AndroidForWorkStats> {
        private static volatile AndroidForWorkStats[] _emptyArray;
        public boolean hasWorkProfile;
        public int mergeWorkProfileResultsLatencyMs;
        public int numWorkProfileResults;
        public int workProfileResultsLatencyMs;

        public static AndroidForWorkStats[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new AndroidForWorkStats[0];
                    }
                }
            }
            return _emptyArray;
        }

        public AndroidForWorkStats() {
            clear();
        }

        public AndroidForWorkStats clear() {
            this.hasWorkProfile = false;
            this.numWorkProfileResults = 0;
            this.workProfileResultsLatencyMs = 0;
            this.mergeWorkProfileResultsLatencyMs = 0;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof AndroidForWorkStats)) {
                return false;
            }
            AndroidForWorkStats other = (AndroidForWorkStats) o;
            if (this.hasWorkProfile != other.hasWorkProfile || this.numWorkProfileResults != other.numWorkProfileResults || this.workProfileResultsLatencyMs != other.workProfileResultsLatencyMs || this.mergeWorkProfileResultsLatencyMs != other.mergeWorkProfileResultsLatencyMs) {
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
            int hashCode = (((((((((getClass().getName().hashCode() + 527) * 31) + (this.hasWorkProfile ? 1231 : 1237)) * 31) + this.numWorkProfileResults) * 31) + this.workProfileResultsLatencyMs) * 31) + this.mergeWorkProfileResultsLatencyMs) * 31;
            int hashCode2 = (this.unknownFieldData == null || this.unknownFieldData.isEmpty()) ? 0 : this.unknownFieldData.hashCode();
            return hashCode + hashCode2;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.hasWorkProfile) {
                output.writeBool(1, this.hasWorkProfile);
            }
            if (this.numWorkProfileResults != 0) {
                output.writeInt32(2, this.numWorkProfileResults);
            }
            if (this.workProfileResultsLatencyMs != 0) {
                output.writeInt32(3, this.workProfileResultsLatencyMs);
            }
            if (this.mergeWorkProfileResultsLatencyMs != 0) {
                output.writeInt32(4, this.mergeWorkProfileResultsLatencyMs);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.hasWorkProfile) {
                size += CodedOutputByteBufferNano.computeBoolSize(1, this.hasWorkProfile);
            }
            if (this.numWorkProfileResults != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(2, this.numWorkProfileResults);
            }
            if (this.workProfileResultsLatencyMs != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(3, this.workProfileResultsLatencyMs);
            }
            if (this.mergeWorkProfileResultsLatencyMs != 0) {
                return size + CodedOutputByteBufferNano.computeInt32Size(4, this.mergeWorkProfileResultsLatencyMs);
            }
            return size;
        }

        public AndroidForWorkStats mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_GET_LINK /*8*/:
                        this.hasWorkProfile = input.readBool();
                        continue;
                    case LogSource.GMS_CORE_PEOPLE /*16*/:
                        this.numWorkProfileResults = input.readInt32();
                        continue;
                    case LogSource.LB_C /*24*/:
                        this.workProfileResultsLatencyMs = input.readInt32();
                        continue;
                    case LogSource.SOCIAL /*32*/:
                        this.mergeWorkProfileResultsLatencyMs = input.readInt32();
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

        public static AndroidForWorkStats parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (AndroidForWorkStats) MessageNano.mergeFrom(new AndroidForWorkStats(), data);
        }

        public static AndroidForWorkStats parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new AndroidForWorkStats().mergeFrom(input);
        }
    }

    public static final class AppHistoryEventPlayLog extends ExtendableMessageNano<AppHistoryEventPlayLog> {
        private static volatile AppHistoryEventPlayLog[] _emptyArray;
        public String appUri;
        public long eventTimeMs;
        public int eventType;
        public AppIndexingLinksPlayLog outLinks;
        public String packageName;
        public String schemaOrgType;
        public String title;
        public int version;
        public String webUrl;

        public interface AppEventType {
            public static final int CONTEXT_ONLY = 4;
            public static final int CONTEXT_ONLY_ENDED = 3;
            public static final int GENERAL = 0;
            public static final int GLOBAL_SEARCH = 2;
            public static final int SEARCHED = 1;
        }

        public static AppHistoryEventPlayLog[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new AppHistoryEventPlayLog[0];
                    }
                }
            }
            return _emptyArray;
        }

        public AppHistoryEventPlayLog() {
            clear();
        }

        public AppHistoryEventPlayLog clear() {
            this.eventTimeMs = 0;
            this.packageName = BuildConfig.VERSION_NAME;
            this.version = 0;
            this.eventType = 0;
            this.title = BuildConfig.VERSION_NAME;
            this.appUri = BuildConfig.VERSION_NAME;
            this.webUrl = BuildConfig.VERSION_NAME;
            this.schemaOrgType = BuildConfig.VERSION_NAME;
            this.outLinks = null;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof AppHistoryEventPlayLog)) {
                return false;
            }
            AppHistoryEventPlayLog other = (AppHistoryEventPlayLog) o;
            if (this.eventTimeMs != other.eventTimeMs) {
                return false;
            }
            if (this.packageName == null) {
                if (other.packageName != null) {
                    return false;
                }
            } else if (!this.packageName.equals(other.packageName)) {
                return false;
            }
            if (this.version != other.version || this.eventType != other.eventType) {
                return false;
            }
            if (this.title == null) {
                if (other.title != null) {
                    return false;
                }
            } else if (!this.title.equals(other.title)) {
                return false;
            }
            if (this.appUri == null) {
                if (other.appUri != null) {
                    return false;
                }
            } else if (!this.appUri.equals(other.appUri)) {
                return false;
            }
            if (this.webUrl == null) {
                if (other.webUrl != null) {
                    return false;
                }
            } else if (!this.webUrl.equals(other.webUrl)) {
                return false;
            }
            if (this.schemaOrgType == null) {
                if (other.schemaOrgType != null) {
                    return false;
                }
            } else if (!this.schemaOrgType.equals(other.schemaOrgType)) {
                return false;
            }
            if (this.outLinks == null) {
                if (other.outLinks != null) {
                    return false;
                }
            } else if (!this.outLinks.equals(other.outLinks)) {
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
            int hashCode = (((((((((((((((((((getClass().getName().hashCode() + 527) * 31) + ((int) (this.eventTimeMs ^ (this.eventTimeMs >>> 32)))) * 31) + (this.packageName == null ? 0 : this.packageName.hashCode())) * 31) + this.version) * 31) + this.eventType) * 31) + (this.title == null ? 0 : this.title.hashCode())) * 31) + (this.appUri == null ? 0 : this.appUri.hashCode())) * 31) + (this.webUrl == null ? 0 : this.webUrl.hashCode())) * 31) + (this.schemaOrgType == null ? 0 : this.schemaOrgType.hashCode())) * 31) + (this.outLinks == null ? 0 : this.outLinks.hashCode())) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.eventTimeMs != 0) {
                output.writeInt64(1, this.eventTimeMs);
            }
            if (!this.packageName.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(2, this.packageName);
            }
            if (this.version != 0) {
                output.writeInt32(3, this.version);
            }
            if (this.eventType != 0) {
                output.writeInt32(4, this.eventType);
            }
            if (!this.title.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(5, this.title);
            }
            if (!this.appUri.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(6, this.appUri);
            }
            if (!this.webUrl.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(7, this.webUrl);
            }
            if (!this.schemaOrgType.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(8, this.schemaOrgType);
            }
            if (this.outLinks != null) {
                output.writeMessage(9, this.outLinks);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.eventTimeMs != 0) {
                size += CodedOutputByteBufferNano.computeInt64Size(1, this.eventTimeMs);
            }
            if (!this.packageName.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(2, this.packageName);
            }
            if (this.version != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(3, this.version);
            }
            if (this.eventType != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(4, this.eventType);
            }
            if (!this.title.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(5, this.title);
            }
            if (!this.appUri.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(6, this.appUri);
            }
            if (!this.webUrl.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(7, this.webUrl);
            }
            if (!this.schemaOrgType.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(8, this.schemaOrgType);
            }
            if (this.outLinks != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(9, this.outLinks);
            }
            return size;
        }

        public AppHistoryEventPlayLog mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_GET_LINK /*8*/:
                        this.eventTimeMs = input.readInt64();
                        continue;
                    case LogSource.GOOGLE_ANALYTICS /*18*/:
                        this.packageName = input.readString();
                        continue;
                    case LogSource.LB_C /*24*/:
                        this.version = input.readInt32();
                        continue;
                    case LogSource.SOCIAL /*32*/:
                        int value = input.readInt32();
                        switch (value) {
                            case Action.UNKNOWN /*0*/:
                            case TimeSelection.Type.TEMPORARY /*1*/:
                            case TimeSelection.Type.INDEFINITELY /*2*/:
                            case TimeSelection.Type.CUSTOM /*3*/:
                            case OvenFreshResult.NO_ACCOUNTS /*4*/:
                                this.eventType = value;
                                break;
                            default:
                                continue;
                        }
                    case LogSource.PHOTOS /*42*/:
                        this.title = input.readString();
                        continue;
                    case LogSource.HANGOUT_LOG_REQUEST /*50*/:
                        this.appUri = input.readString();
                        continue;
                    case LogSource.SLIDES /*58*/:
                        this.webUrl = input.readString();
                        continue;
                    case LogSource.WIFI_ASSISTANT /*66*/:
                        this.schemaOrgType = input.readString();
                        continue;
                    case LogSource.MOVIES /*74*/:
                        if (this.outLinks == null) {
                            this.outLinks = new AppIndexingLinksPlayLog();
                        }
                        input.readMessage(this.outLinks);
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

        public static AppHistoryEventPlayLog parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (AppHistoryEventPlayLog) MessageNano.mergeFrom(new AppHistoryEventPlayLog(), data);
        }

        public static AppHistoryEventPlayLog parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new AppHistoryEventPlayLog().mergeFrom(input);
        }
    }

    public static final class AppIndexingLinksPlayLog extends ExtendableMessageNano<AppIndexingLinksPlayLog> {
        private static volatile AppIndexingLinksPlayLog[] _emptyArray;
        public Link[] links;

        public static final class Link extends ExtendableMessageNano<Link> {
            private static volatile Link[] _emptyArray;
            public String appIndexingUrl;
            public int viewId;
            public String webUrl;

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
                this.appIndexingUrl = BuildConfig.VERSION_NAME;
                this.webUrl = BuildConfig.VERSION_NAME;
                this.viewId = 0;
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
                if (this.appIndexingUrl == null) {
                    if (other.appIndexingUrl != null) {
                        return false;
                    }
                } else if (!this.appIndexingUrl.equals(other.appIndexingUrl)) {
                    return false;
                }
                if (this.webUrl == null) {
                    if (other.webUrl != null) {
                        return false;
                    }
                } else if (!this.webUrl.equals(other.webUrl)) {
                    return false;
                }
                if (this.viewId != other.viewId) {
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
                int hashCode = (((((((getClass().getName().hashCode() + 527) * 31) + (this.appIndexingUrl == null ? 0 : this.appIndexingUrl.hashCode())) * 31) + (this.webUrl == null ? 0 : this.webUrl.hashCode())) * 31) + this.viewId) * 31;
                if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                    i = this.unknownFieldData.hashCode();
                }
                return hashCode + i;
            }

            public void writeTo(CodedOutputByteBufferNano output) throws IOException {
                if (!this.appIndexingUrl.equals(BuildConfig.VERSION_NAME)) {
                    output.writeString(1, this.appIndexingUrl);
                }
                if (!this.webUrl.equals(BuildConfig.VERSION_NAME)) {
                    output.writeString(2, this.webUrl);
                }
                if (this.viewId != 0) {
                    output.writeInt32(3, this.viewId);
                }
                super.writeTo(output);
            }

            protected int computeSerializedSize() {
                int size = super.computeSerializedSize();
                if (!this.appIndexingUrl.equals(BuildConfig.VERSION_NAME)) {
                    size += CodedOutputByteBufferNano.computeStringSize(1, this.appIndexingUrl);
                }
                if (!this.webUrl.equals(BuildConfig.VERSION_NAME)) {
                    size += CodedOutputByteBufferNano.computeStringSize(2, this.webUrl);
                }
                if (this.viewId != 0) {
                    return size + CodedOutputByteBufferNano.computeInt32Size(3, this.viewId);
                }
                return size;
            }

            public Link mergeFrom(CodedInputByteBufferNano input) throws IOException {
                while (true) {
                    int tag = input.readTag();
                    switch (tag) {
                        case Action.UNKNOWN /*0*/:
                            break;
                        case Type.TAP_ABOUT /*10*/:
                            this.appIndexingUrl = input.readString();
                            continue;
                        case LogSource.GOOGLE_ANALYTICS /*18*/:
                            this.webUrl = input.readString();
                            continue;
                        case LogSource.LB_C /*24*/:
                            this.viewId = input.readInt32();
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

            public static Link parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
                return (Link) MessageNano.mergeFrom(new Link(), data);
            }

            public static Link parseFrom(CodedInputByteBufferNano input) throws IOException {
                return new Link().mergeFrom(input);
            }
        }

        public static AppIndexingLinksPlayLog[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new AppIndexingLinksPlayLog[0];
                    }
                }
            }
            return _emptyArray;
        }

        public AppIndexingLinksPlayLog() {
            clear();
        }

        public AppIndexingLinksPlayLog clear() {
            this.links = Link.emptyArray();
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof AppIndexingLinksPlayLog)) {
                return false;
            }
            AppIndexingLinksPlayLog other = (AppIndexingLinksPlayLog) o;
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
            int hashCode = (((getClass().getName().hashCode() + 527) * 31) + InternalNano.hashCode(this.links)) * 31;
            int hashCode2 = (this.unknownFieldData == null || this.unknownFieldData.isEmpty()) ? 0 : this.unknownFieldData.hashCode();
            return hashCode + hashCode2;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.links != null && this.links.length > 0) {
                for (Link element : this.links) {
                    if (element != null) {
                        output.writeMessage(1, element);
                    }
                }
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.links != null && this.links.length > 0) {
                for (Link element : this.links) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(1, element);
                    }
                }
            }
            return size;
        }

        public AppIndexingLinksPlayLog mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        int i;
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 10);
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

        public static AppIndexingLinksPlayLog parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (AppIndexingLinksPlayLog) MessageNano.mergeFrom(new AppIndexingLinksPlayLog(), data);
        }

        public static AppIndexingLinksPlayLog parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new AppIndexingLinksPlayLog().mergeFrom(input);
        }
    }

    public static final class IcingLogData extends ExtendableMessageNano<IcingLogData> {
        private static volatile IcingLogData[] _emptyArray;
        public AppHistoryEventPlayLog[] appHistory;
        public IcingLogError logError;
        public IcingQueryStatsLog queryStats;
        public ReportUsageStats reportUsageStats;
        public IcingStorageState storageState;
        public IcingTimingLog timing;

        public static IcingLogData[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new IcingLogData[0];
                    }
                }
            }
            return _emptyArray;
        }

        public IcingLogData() {
            clear();
        }

        public IcingLogData clear() {
            this.logError = null;
            this.storageState = null;
            this.queryStats = null;
            this.timing = null;
            this.appHistory = AppHistoryEventPlayLog.emptyArray();
            this.reportUsageStats = null;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof IcingLogData)) {
                return false;
            }
            IcingLogData other = (IcingLogData) o;
            if (this.logError == null) {
                if (other.logError != null) {
                    return false;
                }
            } else if (!this.logError.equals(other.logError)) {
                return false;
            }
            if (this.storageState == null) {
                if (other.storageState != null) {
                    return false;
                }
            } else if (!this.storageState.equals(other.storageState)) {
                return false;
            }
            if (this.queryStats == null) {
                if (other.queryStats != null) {
                    return false;
                }
            } else if (!this.queryStats.equals(other.queryStats)) {
                return false;
            }
            if (this.timing == null) {
                if (other.timing != null) {
                    return false;
                }
            } else if (!this.timing.equals(other.timing)) {
                return false;
            }
            if (!InternalNano.equals(this.appHistory, other.appHistory)) {
                return false;
            }
            if (this.reportUsageStats == null) {
                if (other.reportUsageStats != null) {
                    return false;
                }
            } else if (!this.reportUsageStats.equals(other.reportUsageStats)) {
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
            int hashCode = (((((((((((((getClass().getName().hashCode() + 527) * 31) + (this.logError == null ? 0 : this.logError.hashCode())) * 31) + (this.storageState == null ? 0 : this.storageState.hashCode())) * 31) + (this.queryStats == null ? 0 : this.queryStats.hashCode())) * 31) + (this.timing == null ? 0 : this.timing.hashCode())) * 31) + InternalNano.hashCode(this.appHistory)) * 31) + (this.reportUsageStats == null ? 0 : this.reportUsageStats.hashCode())) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.logError != null) {
                output.writeMessage(1, this.logError);
            }
            if (this.storageState != null) {
                output.writeMessage(2, this.storageState);
            }
            if (this.queryStats != null) {
                output.writeMessage(3, this.queryStats);
            }
            if (this.timing != null) {
                output.writeMessage(4, this.timing);
            }
            if (this.appHistory != null && this.appHistory.length > 0) {
                for (AppHistoryEventPlayLog element : this.appHistory) {
                    if (element != null) {
                        output.writeMessage(5, element);
                    }
                }
            }
            if (this.reportUsageStats != null) {
                output.writeMessage(6, this.reportUsageStats);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.logError != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(1, this.logError);
            }
            if (this.storageState != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(2, this.storageState);
            }
            if (this.queryStats != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(3, this.queryStats);
            }
            if (this.timing != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(4, this.timing);
            }
            if (this.appHistory != null && this.appHistory.length > 0) {
                for (AppHistoryEventPlayLog element : this.appHistory) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(5, element);
                    }
                }
            }
            if (this.reportUsageStats != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(6, this.reportUsageStats);
            }
            return size;
        }

        public IcingLogData mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        if (this.logError == null) {
                            this.logError = new IcingLogError();
                        }
                        input.readMessage(this.logError);
                        continue;
                    case LogSource.GOOGLE_ANALYTICS /*18*/:
                        if (this.storageState == null) {
                            this.storageState = new IcingStorageState();
                        }
                        input.readMessage(this.storageState);
                        continue;
                    case LogSource.ANDROID_CAMERA /*26*/:
                        if (this.queryStats == null) {
                            this.queryStats = new IcingQueryStatsLog();
                        }
                        input.readMessage(this.queryStats);
                        continue;
                    case LogSource.NOVA /*34*/:
                        if (this.timing == null) {
                            this.timing = new IcingTimingLog();
                        }
                        input.readMessage(this.timing);
                        continue;
                    case LogSource.PHOTOS /*42*/:
                        int i;
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 42);
                        if (this.appHistory == null) {
                            i = 0;
                        } else {
                            i = this.appHistory.length;
                        }
                        AppHistoryEventPlayLog[] newArray = new AppHistoryEventPlayLog[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.appHistory, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new AppHistoryEventPlayLog();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i++;
                        }
                        newArray[i] = new AppHistoryEventPlayLog();
                        input.readMessage(newArray[i]);
                        this.appHistory = newArray;
                        continue;
                    case LogSource.HANGOUT_LOG_REQUEST /*50*/:
                        if (this.reportUsageStats == null) {
                            this.reportUsageStats = new ReportUsageStats();
                        }
                        input.readMessage(this.reportUsageStats);
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

        public static IcingLogData parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (IcingLogData) MessageNano.mergeFrom(new IcingLogData(), data);
        }

        public static IcingLogData parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new IcingLogData().mergeFrom(input);
        }
    }

    public static final class IcingLogError extends ExtendableMessageNano<IcingLogError> {
        private static volatile IcingLogError[] _emptyArray;
        public String errorType;

        public static IcingLogError[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new IcingLogError[0];
                    }
                }
            }
            return _emptyArray;
        }

        public IcingLogError() {
            clear();
        }

        public IcingLogError clear() {
            this.errorType = BuildConfig.VERSION_NAME;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof IcingLogError)) {
                return false;
            }
            IcingLogError other = (IcingLogError) o;
            if (this.errorType == null) {
                if (other.errorType != null) {
                    return false;
                }
            } else if (!this.errorType.equals(other.errorType)) {
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
            int hashCode = (((getClass().getName().hashCode() + 527) * 31) + (this.errorType == null ? 0 : this.errorType.hashCode())) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (!this.errorType.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(1, this.errorType);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.errorType.equals(BuildConfig.VERSION_NAME)) {
                return size;
            }
            return size + CodedOutputByteBufferNano.computeStringSize(1, this.errorType);
        }

        public IcingLogError mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        this.errorType = input.readString();
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

        public static IcingLogError parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (IcingLogError) MessageNano.mergeFrom(new IcingLogError(), data);
        }

        public static IcingLogError parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new IcingLogError().mergeFrom(input);
        }
    }

    public static final class IcingQueryStatsLog extends ExtendableMessageNano<IcingQueryStatsLog> {
        private static volatile IcingQueryStatsLog[] _emptyArray;
        public AndroidForWorkStats androidForWorkStats;
        public int executeLatencyMs;
        public int extState;
        public int initLatencyMs;
        public int numRequested;
        public int numResults;
        public int numScored;
        public int prepLatencyMs;
        public int queryLength;
        public int rankingStrategy;
        public int type;

        public interface ExtensionState {
            public static final int LOADED = 3;
            public static final int NONE = 0;
            public static final int PENDING_REINDEX = 2;
            public static final int UNLOADED = 1;
        }

        public interface QueryType {
            public static final int QUERY = 0;
            public static final int QUERY_UNIVERSAL = 1;
            public static final int SUGGEST = 2;
        }

        public interface RankingStrategy {
            public static final int CHROME_OMNIBOX = 4;
            public static final int DOC_SCORE = 0;
            public static final int MOST_RECENTLY_USED = 2;
            public static final int OTHER = 3;
            public static final int RELEVANCE = 1;
        }

        public static IcingQueryStatsLog[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new IcingQueryStatsLog[0];
                    }
                }
            }
            return _emptyArray;
        }

        public IcingQueryStatsLog() {
            clear();
        }

        public IcingQueryStatsLog clear() {
            this.type = 0;
            this.extState = 0;
            this.rankingStrategy = 0;
            this.queryLength = 0;
            this.numRequested = 0;
            this.numResults = 0;
            this.numScored = 0;
            this.initLatencyMs = 0;
            this.prepLatencyMs = 0;
            this.executeLatencyMs = 0;
            this.androidForWorkStats = null;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof IcingQueryStatsLog)) {
                return false;
            }
            IcingQueryStatsLog other = (IcingQueryStatsLog) o;
            if (this.type != other.type || this.extState != other.extState || this.rankingStrategy != other.rankingStrategy || this.queryLength != other.queryLength || this.numRequested != other.numRequested || this.numResults != other.numResults || this.numScored != other.numScored || this.initLatencyMs != other.initLatencyMs || this.prepLatencyMs != other.prepLatencyMs || this.executeLatencyMs != other.executeLatencyMs) {
                return false;
            }
            if (this.androidForWorkStats == null) {
                if (other.androidForWorkStats != null) {
                    return false;
                }
            } else if (!this.androidForWorkStats.equals(other.androidForWorkStats)) {
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
            int hashCode = (((((((((((((((((((((((getClass().getName().hashCode() + 527) * 31) + this.type) * 31) + this.extState) * 31) + this.rankingStrategy) * 31) + this.queryLength) * 31) + this.numRequested) * 31) + this.numResults) * 31) + this.numScored) * 31) + this.initLatencyMs) * 31) + this.prepLatencyMs) * 31) + this.executeLatencyMs) * 31) + (this.androidForWorkStats == null ? 0 : this.androidForWorkStats.hashCode())) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.type != 0) {
                output.writeInt32(1, this.type);
            }
            if (this.queryLength != 0) {
                output.writeInt32(2, this.queryLength);
            }
            if (this.numRequested != 0) {
                output.writeInt32(3, this.numRequested);
            }
            if (this.numResults != 0) {
                output.writeInt32(4, this.numResults);
            }
            if (this.numScored != 0) {
                output.writeInt32(5, this.numScored);
            }
            if (this.initLatencyMs != 0) {
                output.writeInt32(6, this.initLatencyMs);
            }
            if (this.prepLatencyMs != 0) {
                output.writeInt32(7, this.prepLatencyMs);
            }
            if (this.executeLatencyMs != 0) {
                output.writeInt32(8, this.executeLatencyMs);
            }
            if (this.extState != 0) {
                output.writeInt32(9, this.extState);
            }
            if (this.rankingStrategy != 0) {
                output.writeInt32(10, this.rankingStrategy);
            }
            if (this.androidForWorkStats != null) {
                output.writeMessage(11, this.androidForWorkStats);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.type != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(1, this.type);
            }
            if (this.queryLength != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(2, this.queryLength);
            }
            if (this.numRequested != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(3, this.numRequested);
            }
            if (this.numResults != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(4, this.numResults);
            }
            if (this.numScored != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(5, this.numScored);
            }
            if (this.initLatencyMs != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(6, this.initLatencyMs);
            }
            if (this.prepLatencyMs != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(7, this.prepLatencyMs);
            }
            if (this.executeLatencyMs != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(8, this.executeLatencyMs);
            }
            if (this.extState != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(9, this.extState);
            }
            if (this.rankingStrategy != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(10, this.rankingStrategy);
            }
            if (this.androidForWorkStats != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(11, this.androidForWorkStats);
            }
            return size;
        }

        public IcingQueryStatsLog mergeFrom(CodedInputByteBufferNano input) throws IOException {
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
                                this.type = value;
                                break;
                            default:
                                continue;
                        }
                    case LogSource.GMS_CORE_PEOPLE /*16*/:
                        this.queryLength = input.readInt32();
                        continue;
                    case LogSource.LB_C /*24*/:
                        this.numRequested = input.readInt32();
                        continue;
                    case LogSource.SOCIAL /*32*/:
                        this.numResults = input.readInt32();
                        continue;
                    case LogSource.COPRESENCE /*40*/:
                        this.numScored = input.readInt32();
                        continue;
                    case LogSource.BACKDROP /*48*/:
                        this.initLatencyMs = input.readInt32();
                        continue;
                    case LogSource.DOCS /*56*/:
                        this.prepLatencyMs = input.readInt32();
                        continue;
                    case LogSource.KIDS_COMMUNICATOR /*64*/:
                        this.executeLatencyMs = input.readInt32();
                        continue;
                    case LogSource.JUSTSPEAK /*72*/:
                        value = input.readInt32();
                        switch (value) {
                            case Action.UNKNOWN /*0*/:
                            case TimeSelection.Type.TEMPORARY /*1*/:
                            case TimeSelection.Type.INDEFINITELY /*2*/:
                            case TimeSelection.Type.CUSTOM /*3*/:
                                this.extState = value;
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
                                this.rankingStrategy = value;
                                break;
                            default:
                                continue;
                        }
                    case LogSource.TRON_COUNTERS /*90*/:
                        if (this.androidForWorkStats == null) {
                            this.androidForWorkStats = new AndroidForWorkStats();
                        }
                        input.readMessage(this.androidForWorkStats);
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

        public static IcingQueryStatsLog parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (IcingQueryStatsLog) MessageNano.mergeFrom(new IcingQueryStatsLog(), data);
        }

        public static IcingQueryStatsLog parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new IcingQueryStatsLog().mergeFrom(input);
        }
    }

    public static final class IcingStorageState extends ExtendableMessageNano<IcingStorageState> {
        private static volatile IcingStorageState[] _emptyArray;
        public long availBytes;
        public long capacityBytes;
        public int extensionVersion;
        public long icingUsageBytes;
        public int state;
        public boolean systemStorageLow;

        public interface State {
            public static final int CANNOT_SYNC = 2;
            public static final int CAN_SYNC_ALL = 0;
            public static final int CAN_SYNC_UNTRIMMABLE = 1;
            public static final int NATIVE_INDEX_DESIGN_LIMIT_REACHED = 3;
        }

        public static IcingStorageState[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new IcingStorageState[0];
                    }
                }
            }
            return _emptyArray;
        }

        public IcingStorageState() {
            clear();
        }

        public IcingStorageState clear() {
            this.state = 0;
            this.icingUsageBytes = 0;
            this.availBytes = 0;
            this.capacityBytes = 0;
            this.systemStorageLow = false;
            this.extensionVersion = 0;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof IcingStorageState)) {
                return false;
            }
            IcingStorageState other = (IcingStorageState) o;
            if (this.state != other.state || this.icingUsageBytes != other.icingUsageBytes || this.availBytes != other.availBytes || this.capacityBytes != other.capacityBytes || this.systemStorageLow != other.systemStorageLow || this.extensionVersion != other.extensionVersion) {
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
            int hashCode = (((((((((((((getClass().getName().hashCode() + 527) * 31) + this.state) * 31) + ((int) (this.icingUsageBytes ^ (this.icingUsageBytes >>> 32)))) * 31) + ((int) (this.availBytes ^ (this.availBytes >>> 32)))) * 31) + ((int) (this.capacityBytes ^ (this.capacityBytes >>> 32)))) * 31) + (this.systemStorageLow ? 1231 : 1237)) * 31) + this.extensionVersion) * 31;
            int hashCode2 = (this.unknownFieldData == null || this.unknownFieldData.isEmpty()) ? 0 : this.unknownFieldData.hashCode();
            return hashCode + hashCode2;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.state != 0) {
                output.writeInt32(1, this.state);
            }
            if (this.icingUsageBytes != 0) {
                output.writeUInt64(2, this.icingUsageBytes);
            }
            if (this.availBytes != 0) {
                output.writeUInt64(3, this.availBytes);
            }
            if (this.capacityBytes != 0) {
                output.writeUInt64(4, this.capacityBytes);
            }
            if (this.systemStorageLow) {
                output.writeBool(5, this.systemStorageLow);
            }
            if (this.extensionVersion != 0) {
                output.writeInt32(6, this.extensionVersion);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.state != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(1, this.state);
            }
            if (this.icingUsageBytes != 0) {
                size += CodedOutputByteBufferNano.computeUInt64Size(2, this.icingUsageBytes);
            }
            if (this.availBytes != 0) {
                size += CodedOutputByteBufferNano.computeUInt64Size(3, this.availBytes);
            }
            if (this.capacityBytes != 0) {
                size += CodedOutputByteBufferNano.computeUInt64Size(4, this.capacityBytes);
            }
            if (this.systemStorageLow) {
                size += CodedOutputByteBufferNano.computeBoolSize(5, this.systemStorageLow);
            }
            if (this.extensionVersion != 0) {
                return size + CodedOutputByteBufferNano.computeInt32Size(6, this.extensionVersion);
            }
            return size;
        }

        public IcingStorageState mergeFrom(CodedInputByteBufferNano input) throws IOException {
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
                                this.state = value;
                                break;
                            default:
                                continue;
                        }
                    case LogSource.GMS_CORE_PEOPLE /*16*/:
                        this.icingUsageBytes = input.readUInt64();
                        continue;
                    case LogSource.LB_C /*24*/:
                        this.availBytes = input.readUInt64();
                        continue;
                    case LogSource.SOCIAL /*32*/:
                        this.capacityBytes = input.readUInt64();
                        continue;
                    case LogSource.COPRESENCE /*40*/:
                        this.systemStorageLow = input.readBool();
                        continue;
                    case LogSource.BACKDROP /*48*/:
                        this.extensionVersion = input.readInt32();
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

        public static IcingStorageState parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (IcingStorageState) MessageNano.mergeFrom(new IcingStorageState(), data);
        }

        public static IcingStorageState parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new IcingStorageState().mergeFrom(input);
        }
    }

    public static final class IcingTimingLog extends ExtendableMessageNano<IcingTimingLog> {
        private static volatile IcingTimingLog[] _emptyArray;
        public int type;
        public int valueMs;

        public interface TimingType {
            public static final int CONTEXTUAL_SUGGESTIONS_FETCH = 8;
            public static final int INIT = 0;
            public static final int INIT_AUTHENTICATE = 6;
            public static final int INIT_CPU = 2;
            public static final int INIT_NATIVE = 5;
            public static final int INIT_PREFS = 3;
            public static final int INIT_READ_RESOURCES = 7;
            public static final int INIT_UPGRADE = 4;
            public static final int POST_INIT = 1;
        }

        public static IcingTimingLog[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new IcingTimingLog[0];
                    }
                }
            }
            return _emptyArray;
        }

        public IcingTimingLog() {
            clear();
        }

        public IcingTimingLog clear() {
            this.type = 0;
            this.valueMs = 0;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof IcingTimingLog)) {
                return false;
            }
            IcingTimingLog other = (IcingTimingLog) o;
            if (this.type != other.type || this.valueMs != other.valueMs) {
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
            int hashCode = (((((getClass().getName().hashCode() + 527) * 31) + this.type) * 31) + this.valueMs) * 31;
            int hashCode2 = (this.unknownFieldData == null || this.unknownFieldData.isEmpty()) ? 0 : this.unknownFieldData.hashCode();
            return hashCode + hashCode2;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.type != 0) {
                output.writeInt32(1, this.type);
            }
            if (this.valueMs != 0) {
                output.writeInt32(2, this.valueMs);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.type != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(1, this.type);
            }
            if (this.valueMs != 0) {
                return size + CodedOutputByteBufferNano.computeInt32Size(2, this.valueMs);
            }
            return size;
        }

        public IcingTimingLog mergeFrom(CodedInputByteBufferNano input) throws IOException {
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
                            case Type.REMOVE_SHARE /*6*/:
                            case Type.RESET_TIME /*7*/:
                            case Type.TAP_GET_LINK /*8*/:
                                this.type = value;
                                break;
                            default:
                                continue;
                        }
                    case LogSource.GMS_CORE_PEOPLE /*16*/:
                        this.valueMs = input.readInt32();
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

        public static IcingTimingLog parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (IcingTimingLog) MessageNano.mergeFrom(new IcingTimingLog(), data);
        }

        public static IcingTimingLog parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new IcingTimingLog().mergeFrom(input);
        }
    }

    public static final class ReportUsageStats extends ExtendableMessageNano<ReportUsageStats> {
        private static volatile ReportUsageStats[] _emptyArray;
        public String packageName;

        public static ReportUsageStats[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new ReportUsageStats[0];
                    }
                }
            }
            return _emptyArray;
        }

        public ReportUsageStats() {
            clear();
        }

        public ReportUsageStats clear() {
            this.packageName = BuildConfig.VERSION_NAME;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof ReportUsageStats)) {
                return false;
            }
            ReportUsageStats other = (ReportUsageStats) o;
            if (this.packageName == null) {
                if (other.packageName != null) {
                    return false;
                }
            } else if (!this.packageName.equals(other.packageName)) {
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
            int hashCode = (((getClass().getName().hashCode() + 527) * 31) + (this.packageName == null ? 0 : this.packageName.hashCode())) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (!this.packageName.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(1, this.packageName);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.packageName.equals(BuildConfig.VERSION_NAME)) {
                return size;
            }
            return size + CodedOutputByteBufferNano.computeStringSize(1, this.packageName);
        }

        public ReportUsageStats mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        this.packageName = input.readString();
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

        public static ReportUsageStats parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (ReportUsageStats) MessageNano.mergeFrom(new ReportUsageStats(), data);
        }

        public static ReportUsageStats parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new ReportUsageStats().mergeFrom(input);
        }
    }
}
