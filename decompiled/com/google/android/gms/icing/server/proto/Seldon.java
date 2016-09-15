package com.google.android.gms.icing.server.proto;

import com.google.android.gms.icing.IcingDocument.Document;
import com.google.android.gms.lint.BuildConfig;
import com.google.android.gms.udc.proto.SettingState;
import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.ExtendableMessageNano;
import com.google.protobuf.nano.InternalNano;
import com.google.protobuf.nano.InvalidProtocolBufferNanoException;
import com.google.protobuf.nano.MessageNano;
import com.google.protobuf.nano.WireFormatNano;
import com.google.wireless.android.play.playlog.proto.ClientAnalytics.AndroidClientInfo;
import com.google.wireless.android.play.playlog.proto.ClientAnalytics.LogRequest.LogSource;
import com.google.wireless.android.play.playlog.proto.UserLocation.LaunchSettingsEvent.Action;
import com.google.wireless.android.play.playlog.proto.UserLocation.LocationSharingEvent.Type;
import com.google.wireless.android.play.playlog.proto.UserLocation.OvenFreshEvent.OvenFreshResult;
import com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection;
import java.io.IOException;

public interface Seldon {

    public static final class AppHistoryEvent extends ExtendableMessageNano<AppHistoryEvent> {
        private static volatile AppHistoryEvent[] _emptyArray;
        public String actionId;
        public String actionType;
        public int androidTaskPos;
        public String appUri;
        public String description;
        public String developerProjectId;
        public int eventStatus;
        public int eventType;
        public long historyTimestampMs;
        public String internalUri;
        public AppIndexingLinks outLinks;
        public String packageCertificateFingerprint;
        public String packageName;
        public SchemaOrgProperty[] schemaOrgProperty;
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

        public interface EventStatus {
            public static final int ENDED = 2;
            public static final int STARTED = 1;
            public static final int UNKNOWN = 0;
        }

        public static AppHistoryEvent[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new AppHistoryEvent[0];
                    }
                }
            }
            return _emptyArray;
        }

        public AppHistoryEvent() {
            clear();
        }

        public AppHistoryEvent clear() {
            this.historyTimestampMs = 0;
            this.packageName = BuildConfig.VERSION_NAME;
            this.packageCertificateFingerprint = BuildConfig.VERSION_NAME;
            this.developerProjectId = BuildConfig.VERSION_NAME;
            this.version = 0;
            this.eventType = 0;
            this.actionType = BuildConfig.VERSION_NAME;
            this.actionId = BuildConfig.VERSION_NAME;
            this.title = BuildConfig.VERSION_NAME;
            this.description = BuildConfig.VERSION_NAME;
            this.appUri = BuildConfig.VERSION_NAME;
            this.webUrl = BuildConfig.VERSION_NAME;
            this.internalUri = BuildConfig.VERSION_NAME;
            this.schemaOrgType = BuildConfig.VERSION_NAME;
            this.outLinks = null;
            this.schemaOrgProperty = SchemaOrgProperty.emptyArray();
            this.androidTaskPos = -1;
            this.eventStatus = 0;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof AppHistoryEvent)) {
                return false;
            }
            AppHistoryEvent other = (AppHistoryEvent) o;
            if (this.historyTimestampMs != other.historyTimestampMs) {
                return false;
            }
            if (this.packageName == null) {
                if (other.packageName != null) {
                    return false;
                }
            } else if (!this.packageName.equals(other.packageName)) {
                return false;
            }
            if (this.packageCertificateFingerprint == null) {
                if (other.packageCertificateFingerprint != null) {
                    return false;
                }
            } else if (!this.packageCertificateFingerprint.equals(other.packageCertificateFingerprint)) {
                return false;
            }
            if (this.developerProjectId == null) {
                if (other.developerProjectId != null) {
                    return false;
                }
            } else if (!this.developerProjectId.equals(other.developerProjectId)) {
                return false;
            }
            if (this.version != other.version || this.eventType != other.eventType) {
                return false;
            }
            if (this.actionType == null) {
                if (other.actionType != null) {
                    return false;
                }
            } else if (!this.actionType.equals(other.actionType)) {
                return false;
            }
            if (this.actionId == null) {
                if (other.actionId != null) {
                    return false;
                }
            } else if (!this.actionId.equals(other.actionId)) {
                return false;
            }
            if (this.title == null) {
                if (other.title != null) {
                    return false;
                }
            } else if (!this.title.equals(other.title)) {
                return false;
            }
            if (this.description == null) {
                if (other.description != null) {
                    return false;
                }
            } else if (!this.description.equals(other.description)) {
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
            if (this.internalUri == null) {
                if (other.internalUri != null) {
                    return false;
                }
            } else if (!this.internalUri.equals(other.internalUri)) {
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
            if (!InternalNano.equals(this.schemaOrgProperty, other.schemaOrgProperty) || this.androidTaskPos != other.androidTaskPos || this.eventStatus != other.eventStatus) {
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
            int hashCode = (((((((((((((((((((((((((((((((((((((getClass().getName().hashCode() + 527) * 31) + ((int) (this.historyTimestampMs ^ (this.historyTimestampMs >>> 32)))) * 31) + (this.packageName == null ? 0 : this.packageName.hashCode())) * 31) + (this.packageCertificateFingerprint == null ? 0 : this.packageCertificateFingerprint.hashCode())) * 31) + (this.developerProjectId == null ? 0 : this.developerProjectId.hashCode())) * 31) + this.version) * 31) + this.eventType) * 31) + (this.actionType == null ? 0 : this.actionType.hashCode())) * 31) + (this.actionId == null ? 0 : this.actionId.hashCode())) * 31) + (this.title == null ? 0 : this.title.hashCode())) * 31) + (this.description == null ? 0 : this.description.hashCode())) * 31) + (this.appUri == null ? 0 : this.appUri.hashCode())) * 31) + (this.webUrl == null ? 0 : this.webUrl.hashCode())) * 31) + (this.internalUri == null ? 0 : this.internalUri.hashCode())) * 31) + (this.schemaOrgType == null ? 0 : this.schemaOrgType.hashCode())) * 31) + (this.outLinks == null ? 0 : this.outLinks.hashCode())) * 31) + InternalNano.hashCode(this.schemaOrgProperty)) * 31) + this.androidTaskPos) * 31) + this.eventStatus) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.historyTimestampMs != 0) {
                output.writeInt64(1, this.historyTimestampMs);
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
            if (!this.developerProjectId.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(10, this.developerProjectId);
            }
            if (!this.actionType.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(11, this.actionType);
            }
            if (this.schemaOrgProperty != null && this.schemaOrgProperty.length > 0) {
                for (SchemaOrgProperty element : this.schemaOrgProperty) {
                    if (element != null) {
                        output.writeMessage(12, element);
                    }
                }
            }
            if (!this.packageCertificateFingerprint.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(13, this.packageCertificateFingerprint);
            }
            if (!this.actionId.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(14, this.actionId);
            }
            if (!this.internalUri.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(15, this.internalUri);
            }
            if (this.androidTaskPos != -1) {
                output.writeInt32(16, this.androidTaskPos);
            }
            if (!this.description.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(20, this.description);
            }
            if (this.eventStatus != 0) {
                output.writeInt32(21, this.eventStatus);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.historyTimestampMs != 0) {
                size += CodedOutputByteBufferNano.computeInt64Size(1, this.historyTimestampMs);
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
                size += CodedOutputByteBufferNano.computeMessageSize(9, this.outLinks);
            }
            if (!this.developerProjectId.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(10, this.developerProjectId);
            }
            if (!this.actionType.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(11, this.actionType);
            }
            if (this.schemaOrgProperty != null && this.schemaOrgProperty.length > 0) {
                for (SchemaOrgProperty element : this.schemaOrgProperty) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(12, element);
                    }
                }
            }
            if (!this.packageCertificateFingerprint.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(13, this.packageCertificateFingerprint);
            }
            if (!this.actionId.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(14, this.actionId);
            }
            if (!this.internalUri.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(15, this.internalUri);
            }
            if (this.androidTaskPos != -1) {
                size += CodedOutputByteBufferNano.computeInt32Size(16, this.androidTaskPos);
            }
            if (!this.description.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(20, this.description);
            }
            if (this.eventStatus != 0) {
                return size + CodedOutputByteBufferNano.computeInt32Size(21, this.eventStatus);
            }
            return size;
        }

        public AppHistoryEvent mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                int value;
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_GET_LINK /*8*/:
                        this.historyTimestampMs = input.readInt64();
                        continue;
                    case LogSource.GOOGLE_ANALYTICS /*18*/:
                        this.packageName = input.readString();
                        continue;
                    case LogSource.LB_C /*24*/:
                        this.version = input.readInt32();
                        continue;
                    case LogSource.SOCIAL /*32*/:
                        value = input.readInt32();
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
                            this.outLinks = new AppIndexingLinks();
                        }
                        input.readMessage(this.outLinks);
                        continue;
                    case LogSource.GOOGLE_EXPRESS /*82*/:
                        this.developerProjectId = input.readString();
                        continue;
                    case LogSource.TRON_COUNTERS /*90*/:
                        this.actionType = input.readString();
                        continue;
                    case LogSource.TACHYON_LOG_REQUEST /*98*/:
                        int i;
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 98);
                        if (this.schemaOrgProperty == null) {
                            i = 0;
                        } else {
                            i = this.schemaOrgProperty.length;
                        }
                        SchemaOrgProperty[] newArray = new SchemaOrgProperty[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.schemaOrgProperty, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new SchemaOrgProperty();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i++;
                        }
                        newArray[i] = new SchemaOrgProperty();
                        input.readMessage(newArray[i]);
                        this.schemaOrgProperty = newArray;
                        continue;
                    case LogSource.ADSHIELD /*106*/:
                        this.packageCertificateFingerprint = input.readString();
                        continue;
                    case LogSource.TRANSOM /*114*/:
                        this.actionId = input.readString();
                        continue;
                    case LogSource.ANDROID_SNET_GCORE /*122*/:
                        this.internalUri = input.readString();
                        continue;
                    case LogSource.KEEP /*128*/:
                        this.androidTaskPos = input.readInt32();
                        continue;
                    case 162:
                        this.description = input.readString();
                        continue;
                    case 168:
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

        public static AppHistoryEvent parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (AppHistoryEvent) MessageNano.mergeFrom(new AppHistoryEvent(), data);
        }

        public static AppHistoryEvent parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new AppHistoryEvent().mergeFrom(input);
        }
    }

    public static final class AppHistoryRequest extends ExtendableMessageNano<AppHistoryRequest> {
        private static volatile AppHistoryRequest[] _emptyArray;
        public AppHistoryEvent[] appHistoryEvent;
        public AndroidClientInfo clientInfo;
        public long requestTimeMs;

        public static AppHistoryRequest[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new AppHistoryRequest[0];
                    }
                }
            }
            return _emptyArray;
        }

        public AppHistoryRequest() {
            clear();
        }

        public AppHistoryRequest clear() {
            this.requestTimeMs = 0;
            this.clientInfo = null;
            this.appHistoryEvent = AppHistoryEvent.emptyArray();
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof AppHistoryRequest)) {
                return false;
            }
            AppHistoryRequest other = (AppHistoryRequest) o;
            if (this.requestTimeMs != other.requestTimeMs) {
                return false;
            }
            if (this.clientInfo == null) {
                if (other.clientInfo != null) {
                    return false;
                }
            } else if (!this.clientInfo.equals(other.clientInfo)) {
                return false;
            }
            if (!InternalNano.equals(this.appHistoryEvent, other.appHistoryEvent)) {
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
            int hashCode = (((((((getClass().getName().hashCode() + 527) * 31) + ((int) (this.requestTimeMs ^ (this.requestTimeMs >>> 32)))) * 31) + (this.clientInfo == null ? 0 : this.clientInfo.hashCode())) * 31) + InternalNano.hashCode(this.appHistoryEvent)) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.requestTimeMs != 0) {
                output.writeInt64(1, this.requestTimeMs);
            }
            if (this.clientInfo != null) {
                output.writeMessage(2, this.clientInfo);
            }
            if (this.appHistoryEvent != null && this.appHistoryEvent.length > 0) {
                for (AppHistoryEvent element : this.appHistoryEvent) {
                    if (element != null) {
                        output.writeMessage(3, element);
                    }
                }
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.requestTimeMs != 0) {
                size += CodedOutputByteBufferNano.computeInt64Size(1, this.requestTimeMs);
            }
            if (this.clientInfo != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(2, this.clientInfo);
            }
            if (this.appHistoryEvent != null && this.appHistoryEvent.length > 0) {
                for (AppHistoryEvent element : this.appHistoryEvent) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(3, element);
                    }
                }
            }
            return size;
        }

        public AppHistoryRequest mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_GET_LINK /*8*/:
                        this.requestTimeMs = input.readInt64();
                        continue;
                    case LogSource.GOOGLE_ANALYTICS /*18*/:
                        if (this.clientInfo == null) {
                            this.clientInfo = new AndroidClientInfo();
                        }
                        input.readMessage(this.clientInfo);
                        continue;
                    case LogSource.ANDROID_CAMERA /*26*/:
                        int i;
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 26);
                        if (this.appHistoryEvent == null) {
                            i = 0;
                        } else {
                            i = this.appHistoryEvent.length;
                        }
                        AppHistoryEvent[] newArray = new AppHistoryEvent[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.appHistoryEvent, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new AppHistoryEvent();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i++;
                        }
                        newArray[i] = new AppHistoryEvent();
                        input.readMessage(newArray[i]);
                        this.appHistoryEvent = newArray;
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

        public static AppHistoryRequest parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (AppHistoryRequest) MessageNano.mergeFrom(new AppHistoryRequest(), data);
        }

        public static AppHistoryRequest parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new AppHistoryRequest().mergeFrom(input);
        }
    }

    public static final class AppIndexingLinks extends ExtendableMessageNano<AppIndexingLinks> {
        private static volatile AppIndexingLinks[] _emptyArray;
        public Link[] link;

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

        public static AppIndexingLinks[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new AppIndexingLinks[0];
                    }
                }
            }
            return _emptyArray;
        }

        public AppIndexingLinks() {
            clear();
        }

        public AppIndexingLinks clear() {
            this.link = Link.emptyArray();
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof AppIndexingLinks)) {
                return false;
            }
            AppIndexingLinks other = (AppIndexingLinks) o;
            if (!InternalNano.equals(this.link, other.link)) {
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
            int hashCode = (((getClass().getName().hashCode() + 527) * 31) + InternalNano.hashCode(this.link)) * 31;
            int hashCode2 = (this.unknownFieldData == null || this.unknownFieldData.isEmpty()) ? 0 : this.unknownFieldData.hashCode();
            return hashCode + hashCode2;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.link != null && this.link.length > 0) {
                for (Link element : this.link) {
                    if (element != null) {
                        output.writeMessage(1, element);
                    }
                }
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.link != null && this.link.length > 0) {
                for (Link element : this.link) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(1, element);
                    }
                }
            }
            return size;
        }

        public AppIndexingLinks mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        int i;
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 10);
                        if (this.link == null) {
                            i = 0;
                        } else {
                            i = this.link.length;
                        }
                        Link[] newArray = new Link[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.link, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new Link();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i++;
                        }
                        newArray[i] = new Link();
                        input.readMessage(newArray[i]);
                        this.link = newArray;
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

        public static AppIndexingLinks parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (AppIndexingLinks) MessageNano.mergeFrom(new AppIndexingLinks(), data);
        }

        public static AppIndexingLinks parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new AppIndexingLinks().mergeFrom(input);
        }
    }

    public static final class AppParamsResponse extends ExtendableMessageNano<AppParamsResponse> {
        private static volatile AppParamsResponse[] _emptyArray;
        public AppInfo[] appInfo;
        public int appParamsExpt;
        public int ttlSeconds;

        public static final class AppInfo extends ExtendableMessageNano<AppInfo> {
            private static volatile AppInfo[] _emptyArray;
            public String appName;
            public double corpusWeightDemotion;
            public boolean isBlacklisted;
            public boolean isBlacklistedForRecentContext;
            public String packageCertificateFingerprint;
            public int versionCode;

            public static AppInfo[] emptyArray() {
                if (_emptyArray == null) {
                    synchronized (InternalNano.LAZY_INIT_LOCK) {
                        if (_emptyArray == null) {
                            _emptyArray = new AppInfo[0];
                        }
                    }
                }
                return _emptyArray;
            }

            public AppInfo() {
                clear();
            }

            public AppInfo clear() {
                this.appName = BuildConfig.VERSION_NAME;
                this.versionCode = 0;
                this.isBlacklisted = false;
                this.isBlacklistedForRecentContext = false;
                this.corpusWeightDemotion = 0.0d;
                this.packageCertificateFingerprint = BuildConfig.VERSION_NAME;
                this.unknownFieldData = null;
                this.cachedSize = -1;
                return this;
            }

            public boolean equals(Object o) {
                if (o == this) {
                    return true;
                }
                if (!(o instanceof AppInfo)) {
                    return false;
                }
                AppInfo other = (AppInfo) o;
                if (this.appName == null) {
                    if (other.appName != null) {
                        return false;
                    }
                } else if (!this.appName.equals(other.appName)) {
                    return false;
                }
                if (this.versionCode != other.versionCode || this.isBlacklisted != other.isBlacklisted || this.isBlacklistedForRecentContext != other.isBlacklistedForRecentContext || Double.doubleToLongBits(this.corpusWeightDemotion) != Double.doubleToLongBits(other.corpusWeightDemotion)) {
                    return false;
                }
                if (this.packageCertificateFingerprint == null) {
                    if (other.packageCertificateFingerprint != null) {
                        return false;
                    }
                } else if (!this.packageCertificateFingerprint.equals(other.packageCertificateFingerprint)) {
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
                int hashCode = (((((getClass().getName().hashCode() + 527) * 31) + (this.appName == null ? 0 : this.appName.hashCode())) * 31) + this.versionCode) * 31;
                if (this.isBlacklisted) {
                    i = 1231;
                } else {
                    i = 1237;
                }
                i = (hashCode + i) * 31;
                if (!this.isBlacklistedForRecentContext) {
                    i2 = 1237;
                }
                int result = i + i2;
                long v = Double.doubleToLongBits(this.corpusWeightDemotion);
                i = ((((result * 31) + ((int) ((v >>> 32) ^ v))) * 31) + (this.packageCertificateFingerprint == null ? 0 : this.packageCertificateFingerprint.hashCode())) * 31;
                if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                    i3 = this.unknownFieldData.hashCode();
                }
                return i + i3;
            }

            public void writeTo(CodedOutputByteBufferNano output) throws IOException {
                if (!this.appName.equals(BuildConfig.VERSION_NAME)) {
                    output.writeString(1, this.appName);
                }
                if (this.versionCode != 0) {
                    output.writeInt32(2, this.versionCode);
                }
                if (this.isBlacklisted) {
                    output.writeBool(3, this.isBlacklisted);
                }
                if (Double.doubleToLongBits(this.corpusWeightDemotion) != Double.doubleToLongBits(0.0d)) {
                    output.writeDouble(4, this.corpusWeightDemotion);
                }
                if (!this.packageCertificateFingerprint.equals(BuildConfig.VERSION_NAME)) {
                    output.writeString(5, this.packageCertificateFingerprint);
                }
                if (this.isBlacklistedForRecentContext) {
                    output.writeBool(6, this.isBlacklistedForRecentContext);
                }
                super.writeTo(output);
            }

            protected int computeSerializedSize() {
                int size = super.computeSerializedSize();
                if (!this.appName.equals(BuildConfig.VERSION_NAME)) {
                    size += CodedOutputByteBufferNano.computeStringSize(1, this.appName);
                }
                if (this.versionCode != 0) {
                    size += CodedOutputByteBufferNano.computeInt32Size(2, this.versionCode);
                }
                if (this.isBlacklisted) {
                    size += CodedOutputByteBufferNano.computeBoolSize(3, this.isBlacklisted);
                }
                if (Double.doubleToLongBits(this.corpusWeightDemotion) != Double.doubleToLongBits(0.0d)) {
                    size += CodedOutputByteBufferNano.computeDoubleSize(4, this.corpusWeightDemotion);
                }
                if (!this.packageCertificateFingerprint.equals(BuildConfig.VERSION_NAME)) {
                    size += CodedOutputByteBufferNano.computeStringSize(5, this.packageCertificateFingerprint);
                }
                if (this.isBlacklistedForRecentContext) {
                    return size + CodedOutputByteBufferNano.computeBoolSize(6, this.isBlacklistedForRecentContext);
                }
                return size;
            }

            public AppInfo mergeFrom(CodedInputByteBufferNano input) throws IOException {
                while (true) {
                    int tag = input.readTag();
                    switch (tag) {
                        case Action.UNKNOWN /*0*/:
                            break;
                        case Type.TAP_ABOUT /*10*/:
                            this.appName = input.readString();
                            continue;
                        case LogSource.GMS_CORE_PEOPLE /*16*/:
                            this.versionCode = input.readInt32();
                            continue;
                        case LogSource.LB_C /*24*/:
                            this.isBlacklisted = input.readBool();
                            continue;
                        case LogSource.INSTORE_WALLET /*33*/:
                            this.corpusWeightDemotion = input.readDouble();
                            continue;
                        case LogSource.PHOTOS /*42*/:
                            this.packageCertificateFingerprint = input.readString();
                            continue;
                        case LogSource.BACKDROP /*48*/:
                            this.isBlacklistedForRecentContext = input.readBool();
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

            public static AppInfo parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
                return (AppInfo) MessageNano.mergeFrom(new AppInfo(), data);
            }

            public static AppInfo parseFrom(CodedInputByteBufferNano input) throws IOException {
                return new AppInfo().mergeFrom(input);
            }
        }

        public static AppParamsResponse[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new AppParamsResponse[0];
                    }
                }
            }
            return _emptyArray;
        }

        public AppParamsResponse() {
            clear();
        }

        public AppParamsResponse clear() {
            this.appInfo = AppInfo.emptyArray();
            this.ttlSeconds = 0;
            this.appParamsExpt = 0;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof AppParamsResponse)) {
                return false;
            }
            AppParamsResponse other = (AppParamsResponse) o;
            if (!InternalNano.equals(this.appInfo, other.appInfo) || this.ttlSeconds != other.ttlSeconds || this.appParamsExpt != other.appParamsExpt) {
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
            int hashCode = (((((((getClass().getName().hashCode() + 527) * 31) + InternalNano.hashCode(this.appInfo)) * 31) + this.ttlSeconds) * 31) + this.appParamsExpt) * 31;
            int hashCode2 = (this.unknownFieldData == null || this.unknownFieldData.isEmpty()) ? 0 : this.unknownFieldData.hashCode();
            return hashCode + hashCode2;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.appInfo != null && this.appInfo.length > 0) {
                for (AppInfo element : this.appInfo) {
                    if (element != null) {
                        output.writeMessage(1, element);
                    }
                }
            }
            if (this.ttlSeconds != 0) {
                output.writeInt32(2, this.ttlSeconds);
            }
            if (this.appParamsExpt != 0) {
                output.writeInt32(3, this.appParamsExpt);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.appInfo != null && this.appInfo.length > 0) {
                for (AppInfo element : this.appInfo) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(1, element);
                    }
                }
            }
            if (this.ttlSeconds != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(2, this.ttlSeconds);
            }
            if (this.appParamsExpt != 0) {
                return size + CodedOutputByteBufferNano.computeInt32Size(3, this.appParamsExpt);
            }
            return size;
        }

        public AppParamsResponse mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        int i;
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 10);
                        if (this.appInfo == null) {
                            i = 0;
                        } else {
                            i = this.appInfo.length;
                        }
                        AppInfo[] newArray = new AppInfo[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.appInfo, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new AppInfo();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i++;
                        }
                        newArray[i] = new AppInfo();
                        input.readMessage(newArray[i]);
                        this.appInfo = newArray;
                        continue;
                    case LogSource.GMS_CORE_PEOPLE /*16*/:
                        this.ttlSeconds = input.readInt32();
                        continue;
                    case LogSource.LB_C /*24*/:
                        this.appParamsExpt = input.readInt32();
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

        public static AppParamsResponse parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (AppParamsResponse) MessageNano.mergeFrom(new AppParamsResponse(), data);
        }

        public static AppParamsResponse parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new AppParamsResponse().mergeFrom(input);
        }
    }

    public static final class CalypsoDeeplink extends ExtendableMessageNano<CalypsoDeeplink> {
        private static volatile CalypsoDeeplink[] _emptyArray;
        public String applicationName;
        public String applicationPackage;
        public String iconThumbnailUrl;
        public String launchUrl;

        public static CalypsoDeeplink[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new CalypsoDeeplink[0];
                    }
                }
            }
            return _emptyArray;
        }

        public CalypsoDeeplink() {
            clear();
        }

        public CalypsoDeeplink clear() {
            this.applicationName = BuildConfig.VERSION_NAME;
            this.applicationPackage = BuildConfig.VERSION_NAME;
            this.iconThumbnailUrl = BuildConfig.VERSION_NAME;
            this.launchUrl = BuildConfig.VERSION_NAME;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof CalypsoDeeplink)) {
                return false;
            }
            CalypsoDeeplink other = (CalypsoDeeplink) o;
            if (this.applicationName == null) {
                if (other.applicationName != null) {
                    return false;
                }
            } else if (!this.applicationName.equals(other.applicationName)) {
                return false;
            }
            if (this.applicationPackage == null) {
                if (other.applicationPackage != null) {
                    return false;
                }
            } else if (!this.applicationPackage.equals(other.applicationPackage)) {
                return false;
            }
            if (this.iconThumbnailUrl == null) {
                if (other.iconThumbnailUrl != null) {
                    return false;
                }
            } else if (!this.iconThumbnailUrl.equals(other.iconThumbnailUrl)) {
                return false;
            }
            if (this.launchUrl == null) {
                if (other.launchUrl != null) {
                    return false;
                }
            } else if (!this.launchUrl.equals(other.launchUrl)) {
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
            int hashCode = (((((((((getClass().getName().hashCode() + 527) * 31) + (this.applicationName == null ? 0 : this.applicationName.hashCode())) * 31) + (this.applicationPackage == null ? 0 : this.applicationPackage.hashCode())) * 31) + (this.iconThumbnailUrl == null ? 0 : this.iconThumbnailUrl.hashCode())) * 31) + (this.launchUrl == null ? 0 : this.launchUrl.hashCode())) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (!this.applicationName.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(1, this.applicationName);
            }
            if (!this.applicationPackage.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(2, this.applicationPackage);
            }
            if (!this.iconThumbnailUrl.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(3, this.iconThumbnailUrl);
            }
            if (!this.launchUrl.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(4, this.launchUrl);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (!this.applicationName.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.applicationName);
            }
            if (!this.applicationPackage.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(2, this.applicationPackage);
            }
            if (!this.iconThumbnailUrl.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(3, this.iconThumbnailUrl);
            }
            if (this.launchUrl.equals(BuildConfig.VERSION_NAME)) {
                return size;
            }
            return size + CodedOutputByteBufferNano.computeStringSize(4, this.launchUrl);
        }

        public CalypsoDeeplink mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        this.applicationName = input.readString();
                        continue;
                    case LogSource.GOOGLE_ANALYTICS /*18*/:
                        this.applicationPackage = input.readString();
                        continue;
                    case LogSource.ANDROID_CAMERA /*26*/:
                        this.iconThumbnailUrl = input.readString();
                        continue;
                    case LogSource.NOVA /*34*/:
                        this.launchUrl = input.readString();
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

        public static CalypsoDeeplink parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (CalypsoDeeplink) MessageNano.mergeFrom(new CalypsoDeeplink(), data);
        }

        public static CalypsoDeeplink parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new CalypsoDeeplink().mergeFrom(input);
        }
    }

    public static final class CloudIndexRequest extends ExtendableMessageNano<CloudIndexRequest> {
        private static volatile CloudIndexRequest[] _emptyArray;
        public int corpusNameEnum;
        public String[] requestedUrls;

        public static CloudIndexRequest[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new CloudIndexRequest[0];
                    }
                }
            }
            return _emptyArray;
        }

        public CloudIndexRequest() {
            clear();
        }

        public CloudIndexRequest clear() {
            this.corpusNameEnum = 0;
            this.requestedUrls = WireFormatNano.EMPTY_STRING_ARRAY;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof CloudIndexRequest)) {
                return false;
            }
            CloudIndexRequest other = (CloudIndexRequest) o;
            if (this.corpusNameEnum != other.corpusNameEnum || !InternalNano.equals(this.requestedUrls, other.requestedUrls)) {
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
            int hashCode = (((((getClass().getName().hashCode() + 527) * 31) + this.corpusNameEnum) * 31) + InternalNano.hashCode(this.requestedUrls)) * 31;
            int hashCode2 = (this.unknownFieldData == null || this.unknownFieldData.isEmpty()) ? 0 : this.unknownFieldData.hashCode();
            return hashCode + hashCode2;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.corpusNameEnum != 0) {
                output.writeInt32(1, this.corpusNameEnum);
            }
            if (this.requestedUrls != null && this.requestedUrls.length > 0) {
                for (String element : this.requestedUrls) {
                    if (element != null) {
                        output.writeString(2, element);
                    }
                }
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.corpusNameEnum != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(1, this.corpusNameEnum);
            }
            if (this.requestedUrls == null || this.requestedUrls.length <= 0) {
                return size;
            }
            int dataCount = 0;
            int dataSize = 0;
            for (String element : this.requestedUrls) {
                if (element != null) {
                    dataCount++;
                    dataSize += CodedOutputByteBufferNano.computeStringSizeNoTag(element);
                }
            }
            return (size + dataSize) + (dataCount * 1);
        }

        public CloudIndexRequest mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_GET_LINK /*8*/:
                        this.corpusNameEnum = input.readInt32();
                        continue;
                    case LogSource.GOOGLE_ANALYTICS /*18*/:
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 18);
                        int i = this.requestedUrls == null ? 0 : this.requestedUrls.length;
                        String[] newArray = new String[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.requestedUrls, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = input.readString();
                            input.readTag();
                            i++;
                        }
                        newArray[i] = input.readString();
                        this.requestedUrls = newArray;
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

        public static CloudIndexRequest parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (CloudIndexRequest) MessageNano.mergeFrom(new CloudIndexRequest(), data);
        }

        public static CloudIndexRequest parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new CloudIndexRequest().mergeFrom(input);
        }
    }

    public static final class CloudIndexResponse extends ExtendableMessageNano<CloudIndexResponse> {
        private static volatile CloudIndexResponse[] _emptyArray;
        public Document[] icingDocument;

        public static CloudIndexResponse[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new CloudIndexResponse[0];
                    }
                }
            }
            return _emptyArray;
        }

        public CloudIndexResponse() {
            clear();
        }

        public CloudIndexResponse clear() {
            this.icingDocument = Document.emptyArray();
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof CloudIndexResponse)) {
                return false;
            }
            CloudIndexResponse other = (CloudIndexResponse) o;
            if (!InternalNano.equals(this.icingDocument, other.icingDocument)) {
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
            int hashCode = (((getClass().getName().hashCode() + 527) * 31) + InternalNano.hashCode(this.icingDocument)) * 31;
            int hashCode2 = (this.unknownFieldData == null || this.unknownFieldData.isEmpty()) ? 0 : this.unknownFieldData.hashCode();
            return hashCode + hashCode2;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.icingDocument != null && this.icingDocument.length > 0) {
                for (Document element : this.icingDocument) {
                    if (element != null) {
                        output.writeMessage(1, element);
                    }
                }
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.icingDocument != null && this.icingDocument.length > 0) {
                for (Document element : this.icingDocument) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(1, element);
                    }
                }
            }
            return size;
        }

        public CloudIndexResponse mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        int i;
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 10);
                        if (this.icingDocument == null) {
                            i = 0;
                        } else {
                            i = this.icingDocument.length;
                        }
                        Document[] newArray = new Document[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.icingDocument, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new Document();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i++;
                        }
                        newArray[i] = new Document();
                        input.readMessage(newArray[i]);
                        this.icingDocument = newArray;
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

        public static CloudIndexResponse parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (CloudIndexResponse) MessageNano.mergeFrom(new CloudIndexResponse(), data);
        }

        public static CloudIndexResponse parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new CloudIndexResponse().mergeFrom(input);
        }
    }

    public static final class ContextRequest extends ExtendableMessageNano<ContextRequest> {
        private static volatile ContextRequest[] _emptyArray;
        public ContextRequestData data;
        public ContextRequestParams params;

        public static ContextRequest[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new ContextRequest[0];
                    }
                }
            }
            return _emptyArray;
        }

        public ContextRequest() {
            clear();
        }

        public ContextRequest clear() {
            this.data = null;
            this.params = null;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof ContextRequest)) {
                return false;
            }
            ContextRequest other = (ContextRequest) o;
            if (this.data == null) {
                if (other.data != null) {
                    return false;
                }
            } else if (!this.data.equals(other.data)) {
                return false;
            }
            if (this.params == null) {
                if (other.params != null) {
                    return false;
                }
            } else if (!this.params.equals(other.params)) {
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
            int hashCode = (((((getClass().getName().hashCode() + 527) * 31) + (this.data == null ? 0 : this.data.hashCode())) * 31) + (this.params == null ? 0 : this.params.hashCode())) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.data != null) {
                output.writeMessage(1, this.data);
            }
            if (this.params != null) {
                output.writeMessage(2, this.params);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.data != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(1, this.data);
            }
            if (this.params != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(2, this.params);
            }
            return size;
        }

        public ContextRequest mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        if (this.data == null) {
                            this.data = new ContextRequestData();
                        }
                        input.readMessage(this.data);
                        continue;
                    case LogSource.GOOGLE_ANALYTICS /*18*/:
                        if (this.params == null) {
                            this.params = new ContextRequestParams();
                        }
                        input.readMessage(this.params);
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

        public static ContextRequest parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (ContextRequest) MessageNano.mergeFrom(new ContextRequest(), data);
        }

        public static ContextRequest parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new ContextRequest().mergeFrom(input);
        }
    }

    public static final class ContextRequestData extends ExtendableMessageNano<ContextRequestData> {
        private static volatile ContextRequestData[] _emptyArray;
        public AppHistoryRequest appHistory;
        public CloudIndexRequest cloudIndexRequest;

        public static ContextRequestData[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new ContextRequestData[0];
                    }
                }
            }
            return _emptyArray;
        }

        public ContextRequestData() {
            clear();
        }

        public ContextRequestData clear() {
            this.appHistory = null;
            this.cloudIndexRequest = null;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof ContextRequestData)) {
                return false;
            }
            ContextRequestData other = (ContextRequestData) o;
            if (this.appHistory == null) {
                if (other.appHistory != null) {
                    return false;
                }
            } else if (!this.appHistory.equals(other.appHistory)) {
                return false;
            }
            if (this.cloudIndexRequest == null) {
                if (other.cloudIndexRequest != null) {
                    return false;
                }
            } else if (!this.cloudIndexRequest.equals(other.cloudIndexRequest)) {
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
            int hashCode = (((((getClass().getName().hashCode() + 527) * 31) + (this.appHistory == null ? 0 : this.appHistory.hashCode())) * 31) + (this.cloudIndexRequest == null ? 0 : this.cloudIndexRequest.hashCode())) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.appHistory != null) {
                output.writeMessage(2, this.appHistory);
            }
            if (this.cloudIndexRequest != null) {
                output.writeMessage(7, this.cloudIndexRequest);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.appHistory != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(2, this.appHistory);
            }
            if (this.cloudIndexRequest != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(7, this.cloudIndexRequest);
            }
            return size;
        }

        public ContextRequestData mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case LogSource.GOOGLE_ANALYTICS /*18*/:
                        if (this.appHistory == null) {
                            this.appHistory = new AppHistoryRequest();
                        }
                        input.readMessage(this.appHistory);
                        continue;
                    case LogSource.SLIDES /*58*/:
                        if (this.cloudIndexRequest == null) {
                            this.cloudIndexRequest = new CloudIndexRequest();
                        }
                        input.readMessage(this.cloudIndexRequest);
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

        public static ContextRequestData parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (ContextRequestData) MessageNano.mergeFrom(new ContextRequestData(), data);
        }

        public static ContextRequestData parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new ContextRequestData().mergeFrom(input);
        }
    }

    public static final class ContextRequestParams extends ExtendableMessageNano<ContextRequestParams> {
        private static volatile ContextRequestParams[] _emptyArray;
        public AppParams appParams;
        public ContextUploadParams contextUploadParams;
        public KeyboardParams keyboardParams;
        public WebcardParams webcardParams;
        public String xGeoLocation;

        public static final class AppParams extends ExtendableMessageNano<AppParams> {
            private static volatile AppParams[] _emptyArray;
            public App[] appList;
            public int appParamsExpt;
            public boolean requested;

            public static final class App extends ExtendableMessageNano<App> {
                private static volatile App[] _emptyArray;
                public String appName;
                public String packageCertificateFingerprint;
                public int versionCode;

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
                    this.appName = BuildConfig.VERSION_NAME;
                    this.versionCode = 0;
                    this.packageCertificateFingerprint = BuildConfig.VERSION_NAME;
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
                    if (this.appName == null) {
                        if (other.appName != null) {
                            return false;
                        }
                    } else if (!this.appName.equals(other.appName)) {
                        return false;
                    }
                    if (this.versionCode != other.versionCode) {
                        return false;
                    }
                    if (this.packageCertificateFingerprint == null) {
                        if (other.packageCertificateFingerprint != null) {
                            return false;
                        }
                    } else if (!this.packageCertificateFingerprint.equals(other.packageCertificateFingerprint)) {
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
                    int hashCode = (((((((getClass().getName().hashCode() + 527) * 31) + (this.appName == null ? 0 : this.appName.hashCode())) * 31) + this.versionCode) * 31) + (this.packageCertificateFingerprint == null ? 0 : this.packageCertificateFingerprint.hashCode())) * 31;
                    if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                        i = this.unknownFieldData.hashCode();
                    }
                    return hashCode + i;
                }

                public void writeTo(CodedOutputByteBufferNano output) throws IOException {
                    if (!this.appName.equals(BuildConfig.VERSION_NAME)) {
                        output.writeString(1, this.appName);
                    }
                    if (this.versionCode != 0) {
                        output.writeInt32(2, this.versionCode);
                    }
                    if (!this.packageCertificateFingerprint.equals(BuildConfig.VERSION_NAME)) {
                        output.writeString(3, this.packageCertificateFingerprint);
                    }
                    super.writeTo(output);
                }

                protected int computeSerializedSize() {
                    int size = super.computeSerializedSize();
                    if (!this.appName.equals(BuildConfig.VERSION_NAME)) {
                        size += CodedOutputByteBufferNano.computeStringSize(1, this.appName);
                    }
                    if (this.versionCode != 0) {
                        size += CodedOutputByteBufferNano.computeInt32Size(2, this.versionCode);
                    }
                    if (this.packageCertificateFingerprint.equals(BuildConfig.VERSION_NAME)) {
                        return size;
                    }
                    return size + CodedOutputByteBufferNano.computeStringSize(3, this.packageCertificateFingerprint);
                }

                public App mergeFrom(CodedInputByteBufferNano input) throws IOException {
                    while (true) {
                        int tag = input.readTag();
                        switch (tag) {
                            case Action.UNKNOWN /*0*/:
                                break;
                            case Type.TAP_ABOUT /*10*/:
                                this.appName = input.readString();
                                continue;
                            case LogSource.GMS_CORE_PEOPLE /*16*/:
                                this.versionCode = input.readInt32();
                                continue;
                            case LogSource.ANDROID_CAMERA /*26*/:
                                this.packageCertificateFingerprint = input.readString();
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

            public static AppParams[] emptyArray() {
                if (_emptyArray == null) {
                    synchronized (InternalNano.LAZY_INIT_LOCK) {
                        if (_emptyArray == null) {
                            _emptyArray = new AppParams[0];
                        }
                    }
                }
                return _emptyArray;
            }

            public AppParams() {
                clear();
            }

            public AppParams clear() {
                this.requested = false;
                this.appList = App.emptyArray();
                this.appParamsExpt = 0;
                this.unknownFieldData = null;
                this.cachedSize = -1;
                return this;
            }

            public boolean equals(Object o) {
                if (o == this) {
                    return true;
                }
                if (!(o instanceof AppParams)) {
                    return false;
                }
                AppParams other = (AppParams) o;
                if (this.requested != other.requested || !InternalNano.equals(this.appList, other.appList) || this.appParamsExpt != other.appParamsExpt) {
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
                int hashCode = (((((((getClass().getName().hashCode() + 527) * 31) + (this.requested ? 1231 : 1237)) * 31) + InternalNano.hashCode(this.appList)) * 31) + this.appParamsExpt) * 31;
                int hashCode2 = (this.unknownFieldData == null || this.unknownFieldData.isEmpty()) ? 0 : this.unknownFieldData.hashCode();
                return hashCode + hashCode2;
            }

            public void writeTo(CodedOutputByteBufferNano output) throws IOException {
                if (this.requested) {
                    output.writeBool(1, this.requested);
                }
                if (this.appList != null && this.appList.length > 0) {
                    for (App element : this.appList) {
                        if (element != null) {
                            output.writeMessage(2, element);
                        }
                    }
                }
                if (this.appParamsExpt != 0) {
                    output.writeInt32(3, this.appParamsExpt);
                }
                super.writeTo(output);
            }

            protected int computeSerializedSize() {
                int size = super.computeSerializedSize();
                if (this.requested) {
                    size += CodedOutputByteBufferNano.computeBoolSize(1, this.requested);
                }
                if (this.appList != null && this.appList.length > 0) {
                    for (App element : this.appList) {
                        if (element != null) {
                            size += CodedOutputByteBufferNano.computeMessageSize(2, element);
                        }
                    }
                }
                if (this.appParamsExpt != 0) {
                    return size + CodedOutputByteBufferNano.computeInt32Size(3, this.appParamsExpt);
                }
                return size;
            }

            public AppParams mergeFrom(CodedInputByteBufferNano input) throws IOException {
                while (true) {
                    int tag = input.readTag();
                    switch (tag) {
                        case Action.UNKNOWN /*0*/:
                            break;
                        case Type.TAP_GET_LINK /*8*/:
                            this.requested = input.readBool();
                            continue;
                        case LogSource.GOOGLE_ANALYTICS /*18*/:
                            int i;
                            int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 18);
                            if (this.appList == null) {
                                i = 0;
                            } else {
                                i = this.appList.length;
                            }
                            App[] newArray = new App[(i + arrayLength)];
                            if (i != 0) {
                                System.arraycopy(this.appList, 0, newArray, 0, i);
                            }
                            while (i < newArray.length - 1) {
                                newArray[i] = new App();
                                input.readMessage(newArray[i]);
                                input.readTag();
                                i++;
                            }
                            newArray[i] = new App();
                            input.readMessage(newArray[i]);
                            this.appList = newArray;
                            continue;
                        case LogSource.LB_C /*24*/:
                            this.appParamsExpt = input.readInt32();
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

            public static AppParams parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
                return (AppParams) MessageNano.mergeFrom(new AppParams(), data);
            }

            public static AppParams parseFrom(CodedInputByteBufferNano input) throws IOException {
                return new AppParams().mergeFrom(input);
            }
        }

        public static final class ContextUploadParams extends ExtendableMessageNano<ContextUploadParams> {
            private static volatile ContextUploadParams[] _emptyArray;
            public boolean requested;

            public static ContextUploadParams[] emptyArray() {
                if (_emptyArray == null) {
                    synchronized (InternalNano.LAZY_INIT_LOCK) {
                        if (_emptyArray == null) {
                            _emptyArray = new ContextUploadParams[0];
                        }
                    }
                }
                return _emptyArray;
            }

            public ContextUploadParams() {
                clear();
            }

            public ContextUploadParams clear() {
                this.requested = false;
                this.unknownFieldData = null;
                this.cachedSize = -1;
                return this;
            }

            public boolean equals(Object o) {
                if (o == this) {
                    return true;
                }
                if (!(o instanceof ContextUploadParams)) {
                    return false;
                }
                ContextUploadParams other = (ContextUploadParams) o;
                if (this.requested != other.requested) {
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
                int hashCode = (((getClass().getName().hashCode() + 527) * 31) + (this.requested ? 1231 : 1237)) * 31;
                int hashCode2 = (this.unknownFieldData == null || this.unknownFieldData.isEmpty()) ? 0 : this.unknownFieldData.hashCode();
                return hashCode + hashCode2;
            }

            public void writeTo(CodedOutputByteBufferNano output) throws IOException {
                if (this.requested) {
                    output.writeBool(1, this.requested);
                }
                super.writeTo(output);
            }

            protected int computeSerializedSize() {
                int size = super.computeSerializedSize();
                if (this.requested) {
                    return size + CodedOutputByteBufferNano.computeBoolSize(1, this.requested);
                }
                return size;
            }

            public ContextUploadParams mergeFrom(CodedInputByteBufferNano input) throws IOException {
                while (true) {
                    int tag = input.readTag();
                    switch (tag) {
                        case Action.UNKNOWN /*0*/:
                            break;
                        case Type.TAP_GET_LINK /*8*/:
                            this.requested = input.readBool();
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

            public static ContextUploadParams parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
                return (ContextUploadParams) MessageNano.mergeFrom(new ContextUploadParams(), data);
            }

            public static ContextUploadParams parseFrom(CodedInputByteBufferNano input) throws IOException {
                return new ContextUploadParams().mergeFrom(input);
            }
        }

        public static final class KeyboardParams extends ExtendableMessageNano<KeyboardParams> {
            private static volatile KeyboardParams[] _emptyArray;
            public String appPackageName;
            public String dataBackend;
            public boolean requested;

            public static KeyboardParams[] emptyArray() {
                if (_emptyArray == null) {
                    synchronized (InternalNano.LAZY_INIT_LOCK) {
                        if (_emptyArray == null) {
                            _emptyArray = new KeyboardParams[0];
                        }
                    }
                }
                return _emptyArray;
            }

            public KeyboardParams() {
                clear();
            }

            public KeyboardParams clear() {
                this.requested = false;
                this.appPackageName = BuildConfig.VERSION_NAME;
                this.dataBackend = BuildConfig.VERSION_NAME;
                this.unknownFieldData = null;
                this.cachedSize = -1;
                return this;
            }

            public boolean equals(Object o) {
                if (o == this) {
                    return true;
                }
                if (!(o instanceof KeyboardParams)) {
                    return false;
                }
                KeyboardParams other = (KeyboardParams) o;
                if (this.requested != other.requested) {
                    return false;
                }
                if (this.appPackageName == null) {
                    if (other.appPackageName != null) {
                        return false;
                    }
                } else if (!this.appPackageName.equals(other.appPackageName)) {
                    return false;
                }
                if (this.dataBackend == null) {
                    if (other.dataBackend != null) {
                        return false;
                    }
                } else if (!this.dataBackend.equals(other.dataBackend)) {
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
                int hashCode = (((((((getClass().getName().hashCode() + 527) * 31) + (this.requested ? 1231 : 1237)) * 31) + (this.appPackageName == null ? 0 : this.appPackageName.hashCode())) * 31) + (this.dataBackend == null ? 0 : this.dataBackend.hashCode())) * 31;
                if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                    i = this.unknownFieldData.hashCode();
                }
                return hashCode + i;
            }

            public void writeTo(CodedOutputByteBufferNano output) throws IOException {
                if (this.requested) {
                    output.writeBool(1, this.requested);
                }
                if (!this.appPackageName.equals(BuildConfig.VERSION_NAME)) {
                    output.writeString(2, this.appPackageName);
                }
                if (!this.dataBackend.equals(BuildConfig.VERSION_NAME)) {
                    output.writeString(3, this.dataBackend);
                }
                super.writeTo(output);
            }

            protected int computeSerializedSize() {
                int size = super.computeSerializedSize();
                if (this.requested) {
                    size += CodedOutputByteBufferNano.computeBoolSize(1, this.requested);
                }
                if (!this.appPackageName.equals(BuildConfig.VERSION_NAME)) {
                    size += CodedOutputByteBufferNano.computeStringSize(2, this.appPackageName);
                }
                if (this.dataBackend.equals(BuildConfig.VERSION_NAME)) {
                    return size;
                }
                return size + CodedOutputByteBufferNano.computeStringSize(3, this.dataBackend);
            }

            public KeyboardParams mergeFrom(CodedInputByteBufferNano input) throws IOException {
                while (true) {
                    int tag = input.readTag();
                    switch (tag) {
                        case Action.UNKNOWN /*0*/:
                            break;
                        case Type.TAP_GET_LINK /*8*/:
                            this.requested = input.readBool();
                            continue;
                        case LogSource.GOOGLE_ANALYTICS /*18*/:
                            this.appPackageName = input.readString();
                            continue;
                        case LogSource.ANDROID_CAMERA /*26*/:
                            this.dataBackend = input.readString();
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

            public static KeyboardParams parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
                return (KeyboardParams) MessageNano.mergeFrom(new KeyboardParams(), data);
            }

            public static KeyboardParams parseFrom(CodedInputByteBufferNano input) throws IOException {
                return new KeyboardParams().mergeFrom(input);
            }
        }

        public static final class WebcardParams extends ExtendableMessageNano<WebcardParams> {
            private static volatile WebcardParams[] _emptyArray;
            public boolean fullContext;
            public boolean minimalCards;
            public boolean requested;

            public static WebcardParams[] emptyArray() {
                if (_emptyArray == null) {
                    synchronized (InternalNano.LAZY_INIT_LOCK) {
                        if (_emptyArray == null) {
                            _emptyArray = new WebcardParams[0];
                        }
                    }
                }
                return _emptyArray;
            }

            public WebcardParams() {
                clear();
            }

            public WebcardParams clear() {
                this.requested = false;
                this.fullContext = false;
                this.minimalCards = false;
                this.unknownFieldData = null;
                this.cachedSize = -1;
                return this;
            }

            public boolean equals(Object o) {
                if (o == this) {
                    return true;
                }
                if (!(o instanceof WebcardParams)) {
                    return false;
                }
                WebcardParams other = (WebcardParams) o;
                if (this.requested != other.requested || this.fullContext != other.fullContext || this.minimalCards != other.minimalCards) {
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
                int hashCode = (((getClass().getName().hashCode() + 527) * 31) + (this.requested ? 1231 : 1237)) * 31;
                if (this.fullContext) {
                    i = 1231;
                } else {
                    i = 1237;
                }
                i = (hashCode + i) * 31;
                if (!this.minimalCards) {
                    i2 = 1237;
                }
                i2 = (i + i2) * 31;
                i = (this.unknownFieldData == null || this.unknownFieldData.isEmpty()) ? 0 : this.unknownFieldData.hashCode();
                return i2 + i;
            }

            public void writeTo(CodedOutputByteBufferNano output) throws IOException {
                if (this.requested) {
                    output.writeBool(1, this.requested);
                }
                if (this.fullContext) {
                    output.writeBool(2, this.fullContext);
                }
                if (this.minimalCards) {
                    output.writeBool(3, this.minimalCards);
                }
                super.writeTo(output);
            }

            protected int computeSerializedSize() {
                int size = super.computeSerializedSize();
                if (this.requested) {
                    size += CodedOutputByteBufferNano.computeBoolSize(1, this.requested);
                }
                if (this.fullContext) {
                    size += CodedOutputByteBufferNano.computeBoolSize(2, this.fullContext);
                }
                if (this.minimalCards) {
                    return size + CodedOutputByteBufferNano.computeBoolSize(3, this.minimalCards);
                }
                return size;
            }

            public WebcardParams mergeFrom(CodedInputByteBufferNano input) throws IOException {
                while (true) {
                    int tag = input.readTag();
                    switch (tag) {
                        case Action.UNKNOWN /*0*/:
                            break;
                        case Type.TAP_GET_LINK /*8*/:
                            this.requested = input.readBool();
                            continue;
                        case LogSource.GMS_CORE_PEOPLE /*16*/:
                            this.fullContext = input.readBool();
                            continue;
                        case LogSource.LB_C /*24*/:
                            this.minimalCards = input.readBool();
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

            public static WebcardParams parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
                return (WebcardParams) MessageNano.mergeFrom(new WebcardParams(), data);
            }

            public static WebcardParams parseFrom(CodedInputByteBufferNano input) throws IOException {
                return new WebcardParams().mergeFrom(input);
            }
        }

        public static ContextRequestParams[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new ContextRequestParams[0];
                    }
                }
            }
            return _emptyArray;
        }

        public ContextRequestParams() {
            clear();
        }

        public ContextRequestParams clear() {
            this.keyboardParams = null;
            this.webcardParams = null;
            this.contextUploadParams = null;
            this.appParams = null;
            this.xGeoLocation = BuildConfig.VERSION_NAME;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof ContextRequestParams)) {
                return false;
            }
            ContextRequestParams other = (ContextRequestParams) o;
            if (this.keyboardParams == null) {
                if (other.keyboardParams != null) {
                    return false;
                }
            } else if (!this.keyboardParams.equals(other.keyboardParams)) {
                return false;
            }
            if (this.webcardParams == null) {
                if (other.webcardParams != null) {
                    return false;
                }
            } else if (!this.webcardParams.equals(other.webcardParams)) {
                return false;
            }
            if (this.contextUploadParams == null) {
                if (other.contextUploadParams != null) {
                    return false;
                }
            } else if (!this.contextUploadParams.equals(other.contextUploadParams)) {
                return false;
            }
            if (this.appParams == null) {
                if (other.appParams != null) {
                    return false;
                }
            } else if (!this.appParams.equals(other.appParams)) {
                return false;
            }
            if (this.xGeoLocation == null) {
                if (other.xGeoLocation != null) {
                    return false;
                }
            } else if (!this.xGeoLocation.equals(other.xGeoLocation)) {
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
            int hashCode = (((((((((((getClass().getName().hashCode() + 527) * 31) + (this.keyboardParams == null ? 0 : this.keyboardParams.hashCode())) * 31) + (this.webcardParams == null ? 0 : this.webcardParams.hashCode())) * 31) + (this.contextUploadParams == null ? 0 : this.contextUploadParams.hashCode())) * 31) + (this.appParams == null ? 0 : this.appParams.hashCode())) * 31) + (this.xGeoLocation == null ? 0 : this.xGeoLocation.hashCode())) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.keyboardParams != null) {
                output.writeMessage(1, this.keyboardParams);
            }
            if (this.webcardParams != null) {
                output.writeMessage(2, this.webcardParams);
            }
            if (this.contextUploadParams != null) {
                output.writeMessage(4, this.contextUploadParams);
            }
            if (this.appParams != null) {
                output.writeMessage(5, this.appParams);
            }
            if (!this.xGeoLocation.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(6, this.xGeoLocation);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.keyboardParams != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(1, this.keyboardParams);
            }
            if (this.webcardParams != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(2, this.webcardParams);
            }
            if (this.contextUploadParams != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(4, this.contextUploadParams);
            }
            if (this.appParams != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(5, this.appParams);
            }
            if (this.xGeoLocation.equals(BuildConfig.VERSION_NAME)) {
                return size;
            }
            return size + CodedOutputByteBufferNano.computeStringSize(6, this.xGeoLocation);
        }

        public ContextRequestParams mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        if (this.keyboardParams == null) {
                            this.keyboardParams = new KeyboardParams();
                        }
                        input.readMessage(this.keyboardParams);
                        continue;
                    case LogSource.GOOGLE_ANALYTICS /*18*/:
                        if (this.webcardParams == null) {
                            this.webcardParams = new WebcardParams();
                        }
                        input.readMessage(this.webcardParams);
                        continue;
                    case LogSource.NOVA /*34*/:
                        if (this.contextUploadParams == null) {
                            this.contextUploadParams = new ContextUploadParams();
                        }
                        input.readMessage(this.contextUploadParams);
                        continue;
                    case LogSource.PHOTOS /*42*/:
                        if (this.appParams == null) {
                            this.appParams = new AppParams();
                        }
                        input.readMessage(this.appParams);
                        continue;
                    case LogSource.HANGOUT_LOG_REQUEST /*50*/:
                        this.xGeoLocation = input.readString();
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

        public static ContextRequestParams parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (ContextRequestParams) MessageNano.mergeFrom(new ContextRequestParams(), data);
        }

        public static ContextRequestParams parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new ContextRequestParams().mergeFrom(input);
        }
    }

    public static final class ContextResponse extends ExtendableMessageNano<ContextResponse> {
        private static volatile ContextResponse[] _emptyArray;
        public AppParamsResponse appParamsResponse;
        public CloudIndexResponse cloudIndexResponse;
        public SearchSuggestions searchSuggestions;

        public static ContextResponse[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new ContextResponse[0];
                    }
                }
            }
            return _emptyArray;
        }

        public ContextResponse() {
            clear();
        }

        public ContextResponse clear() {
            this.searchSuggestions = null;
            this.appParamsResponse = null;
            this.cloudIndexResponse = null;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof ContextResponse)) {
                return false;
            }
            ContextResponse other = (ContextResponse) o;
            if (this.searchSuggestions == null) {
                if (other.searchSuggestions != null) {
                    return false;
                }
            } else if (!this.searchSuggestions.equals(other.searchSuggestions)) {
                return false;
            }
            if (this.appParamsResponse == null) {
                if (other.appParamsResponse != null) {
                    return false;
                }
            } else if (!this.appParamsResponse.equals(other.appParamsResponse)) {
                return false;
            }
            if (this.cloudIndexResponse == null) {
                if (other.cloudIndexResponse != null) {
                    return false;
                }
            } else if (!this.cloudIndexResponse.equals(other.cloudIndexResponse)) {
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
            int hashCode = (((((((getClass().getName().hashCode() + 527) * 31) + (this.searchSuggestions == null ? 0 : this.searchSuggestions.hashCode())) * 31) + (this.appParamsResponse == null ? 0 : this.appParamsResponse.hashCode())) * 31) + (this.cloudIndexResponse == null ? 0 : this.cloudIndexResponse.hashCode())) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.searchSuggestions != null) {
                output.writeMessage(1, this.searchSuggestions);
            }
            if (this.appParamsResponse != null) {
                output.writeMessage(2, this.appParamsResponse);
            }
            if (this.cloudIndexResponse != null) {
                output.writeMessage(7, this.cloudIndexResponse);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.searchSuggestions != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(1, this.searchSuggestions);
            }
            if (this.appParamsResponse != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(2, this.appParamsResponse);
            }
            if (this.cloudIndexResponse != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(7, this.cloudIndexResponse);
            }
            return size;
        }

        public ContextResponse mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        if (this.searchSuggestions == null) {
                            this.searchSuggestions = new SearchSuggestions();
                        }
                        input.readMessage(this.searchSuggestions);
                        continue;
                    case LogSource.GOOGLE_ANALYTICS /*18*/:
                        if (this.appParamsResponse == null) {
                            this.appParamsResponse = new AppParamsResponse();
                        }
                        input.readMessage(this.appParamsResponse);
                        continue;
                    case LogSource.SLIDES /*58*/:
                        if (this.cloudIndexResponse == null) {
                            this.cloudIndexResponse = new CloudIndexResponse();
                        }
                        input.readMessage(this.cloudIndexResponse);
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

        public static ContextResponse parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (ContextResponse) MessageNano.mergeFrom(new ContextResponse(), data);
        }

        public static ContextResponse parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new ContextResponse().mergeFrom(input);
        }
    }

    public static final class IcingGcmMessage extends ExtendableMessageNano<IcingGcmMessage> {
        private static volatile IcingGcmMessage[] _emptyArray;
        public DeleteAppHistory deleteAppHistory;
        public UdcSettingStateChanged updateUdcSetting;

        public static final class DeleteAppHistory extends ExtendableMessageNano<DeleteAppHistory> {
            private static volatile DeleteAppHistory[] _emptyArray;
            public String appName;
            public long endHistoryTimestampMs;
            public String internalUri;
            public long startHistoryTimestampMs;

            public static DeleteAppHistory[] emptyArray() {
                if (_emptyArray == null) {
                    synchronized (InternalNano.LAZY_INIT_LOCK) {
                        if (_emptyArray == null) {
                            _emptyArray = new DeleteAppHistory[0];
                        }
                    }
                }
                return _emptyArray;
            }

            public DeleteAppHistory() {
                clear();
            }

            public DeleteAppHistory clear() {
                this.appName = BuildConfig.VERSION_NAME;
                this.internalUri = BuildConfig.VERSION_NAME;
                this.startHistoryTimestampMs = 0;
                this.endHistoryTimestampMs = 0;
                this.unknownFieldData = null;
                this.cachedSize = -1;
                return this;
            }

            public boolean equals(Object o) {
                if (o == this) {
                    return true;
                }
                if (!(o instanceof DeleteAppHistory)) {
                    return false;
                }
                DeleteAppHistory other = (DeleteAppHistory) o;
                if (this.appName == null) {
                    if (other.appName != null) {
                        return false;
                    }
                } else if (!this.appName.equals(other.appName)) {
                    return false;
                }
                if (this.internalUri == null) {
                    if (other.internalUri != null) {
                        return false;
                    }
                } else if (!this.internalUri.equals(other.internalUri)) {
                    return false;
                }
                if (this.startHistoryTimestampMs != other.startHistoryTimestampMs || this.endHistoryTimestampMs != other.endHistoryTimestampMs) {
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
                int hashCode = (((((((((getClass().getName().hashCode() + 527) * 31) + (this.appName == null ? 0 : this.appName.hashCode())) * 31) + (this.internalUri == null ? 0 : this.internalUri.hashCode())) * 31) + ((int) (this.startHistoryTimestampMs ^ (this.startHistoryTimestampMs >>> 32)))) * 31) + ((int) (this.endHistoryTimestampMs ^ (this.endHistoryTimestampMs >>> 32)))) * 31;
                if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                    i = this.unknownFieldData.hashCode();
                }
                return hashCode + i;
            }

            public void writeTo(CodedOutputByteBufferNano output) throws IOException {
                if (!this.appName.equals(BuildConfig.VERSION_NAME)) {
                    output.writeString(1, this.appName);
                }
                if (!this.internalUri.equals(BuildConfig.VERSION_NAME)) {
                    output.writeString(2, this.internalUri);
                }
                if (this.startHistoryTimestampMs != 0) {
                    output.writeInt64(3, this.startHistoryTimestampMs);
                }
                if (this.endHistoryTimestampMs != 0) {
                    output.writeInt64(4, this.endHistoryTimestampMs);
                }
                super.writeTo(output);
            }

            protected int computeSerializedSize() {
                int size = super.computeSerializedSize();
                if (!this.appName.equals(BuildConfig.VERSION_NAME)) {
                    size += CodedOutputByteBufferNano.computeStringSize(1, this.appName);
                }
                if (!this.internalUri.equals(BuildConfig.VERSION_NAME)) {
                    size += CodedOutputByteBufferNano.computeStringSize(2, this.internalUri);
                }
                if (this.startHistoryTimestampMs != 0) {
                    size += CodedOutputByteBufferNano.computeInt64Size(3, this.startHistoryTimestampMs);
                }
                if (this.endHistoryTimestampMs != 0) {
                    return size + CodedOutputByteBufferNano.computeInt64Size(4, this.endHistoryTimestampMs);
                }
                return size;
            }

            public DeleteAppHistory mergeFrom(CodedInputByteBufferNano input) throws IOException {
                while (true) {
                    int tag = input.readTag();
                    switch (tag) {
                        case Action.UNKNOWN /*0*/:
                            break;
                        case Type.TAP_ABOUT /*10*/:
                            this.appName = input.readString();
                            continue;
                        case LogSource.GOOGLE_ANALYTICS /*18*/:
                            this.internalUri = input.readString();
                            continue;
                        case LogSource.LB_C /*24*/:
                            this.startHistoryTimestampMs = input.readInt64();
                            continue;
                        case LogSource.SOCIAL /*32*/:
                            this.endHistoryTimestampMs = input.readInt64();
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

            public static DeleteAppHistory parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
                return (DeleteAppHistory) MessageNano.mergeFrom(new DeleteAppHistory(), data);
            }

            public static DeleteAppHistory parseFrom(CodedInputByteBufferNano input) throws IOException {
                return new DeleteAppHistory().mergeFrom(input);
            }
        }

        public static final class UdcSettingStateChanged extends ExtendableMessageNano<UdcSettingStateChanged> {
            private static volatile UdcSettingStateChanged[] _emptyArray;
            public String obfuscatedGaiaId;
            public SettingState[] settingState;

            public static UdcSettingStateChanged[] emptyArray() {
                if (_emptyArray == null) {
                    synchronized (InternalNano.LAZY_INIT_LOCK) {
                        if (_emptyArray == null) {
                            _emptyArray = new UdcSettingStateChanged[0];
                        }
                    }
                }
                return _emptyArray;
            }

            public UdcSettingStateChanged() {
                clear();
            }

            public UdcSettingStateChanged clear() {
                this.obfuscatedGaiaId = BuildConfig.VERSION_NAME;
                this.settingState = SettingState.emptyArray();
                this.unknownFieldData = null;
                this.cachedSize = -1;
                return this;
            }

            public boolean equals(Object o) {
                if (o == this) {
                    return true;
                }
                if (!(o instanceof UdcSettingStateChanged)) {
                    return false;
                }
                UdcSettingStateChanged other = (UdcSettingStateChanged) o;
                if (this.obfuscatedGaiaId == null) {
                    if (other.obfuscatedGaiaId != null) {
                        return false;
                    }
                } else if (!this.obfuscatedGaiaId.equals(other.obfuscatedGaiaId)) {
                    return false;
                }
                if (!InternalNano.equals(this.settingState, other.settingState)) {
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
                int hashCode = (((((getClass().getName().hashCode() + 527) * 31) + (this.obfuscatedGaiaId == null ? 0 : this.obfuscatedGaiaId.hashCode())) * 31) + InternalNano.hashCode(this.settingState)) * 31;
                if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                    i = this.unknownFieldData.hashCode();
                }
                return hashCode + i;
            }

            public void writeTo(CodedOutputByteBufferNano output) throws IOException {
                if (!this.obfuscatedGaiaId.equals(BuildConfig.VERSION_NAME)) {
                    output.writeString(1, this.obfuscatedGaiaId);
                }
                if (this.settingState != null && this.settingState.length > 0) {
                    for (SettingState element : this.settingState) {
                        if (element != null) {
                            output.writeMessage(2, element);
                        }
                    }
                }
                super.writeTo(output);
            }

            protected int computeSerializedSize() {
                int size = super.computeSerializedSize();
                if (!this.obfuscatedGaiaId.equals(BuildConfig.VERSION_NAME)) {
                    size += CodedOutputByteBufferNano.computeStringSize(1, this.obfuscatedGaiaId);
                }
                if (this.settingState != null && this.settingState.length > 0) {
                    for (SettingState element : this.settingState) {
                        if (element != null) {
                            size += CodedOutputByteBufferNano.computeMessageSize(2, element);
                        }
                    }
                }
                return size;
            }

            public UdcSettingStateChanged mergeFrom(CodedInputByteBufferNano input) throws IOException {
                while (true) {
                    int tag = input.readTag();
                    switch (tag) {
                        case Action.UNKNOWN /*0*/:
                            break;
                        case Type.TAP_ABOUT /*10*/:
                            this.obfuscatedGaiaId = input.readString();
                            continue;
                        case LogSource.GOOGLE_ANALYTICS /*18*/:
                            int i;
                            int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 18);
                            if (this.settingState == null) {
                                i = 0;
                            } else {
                                i = this.settingState.length;
                            }
                            SettingState[] newArray = new SettingState[(i + arrayLength)];
                            if (i != 0) {
                                System.arraycopy(this.settingState, 0, newArray, 0, i);
                            }
                            while (i < newArray.length - 1) {
                                newArray[i] = new SettingState();
                                input.readMessage(newArray[i]);
                                input.readTag();
                                i++;
                            }
                            newArray[i] = new SettingState();
                            input.readMessage(newArray[i]);
                            this.settingState = newArray;
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

            public static UdcSettingStateChanged parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
                return (UdcSettingStateChanged) MessageNano.mergeFrom(new UdcSettingStateChanged(), data);
            }

            public static UdcSettingStateChanged parseFrom(CodedInputByteBufferNano input) throws IOException {
                return new UdcSettingStateChanged().mergeFrom(input);
            }
        }

        public static IcingGcmMessage[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new IcingGcmMessage[0];
                    }
                }
            }
            return _emptyArray;
        }

        public IcingGcmMessage() {
            clear();
        }

        public IcingGcmMessage clear() {
            this.deleteAppHistory = null;
            this.updateUdcSetting = null;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof IcingGcmMessage)) {
                return false;
            }
            IcingGcmMessage other = (IcingGcmMessage) o;
            if (this.deleteAppHistory == null) {
                if (other.deleteAppHistory != null) {
                    return false;
                }
            } else if (!this.deleteAppHistory.equals(other.deleteAppHistory)) {
                return false;
            }
            if (this.updateUdcSetting == null) {
                if (other.updateUdcSetting != null) {
                    return false;
                }
            } else if (!this.updateUdcSetting.equals(other.updateUdcSetting)) {
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
            int hashCode = (((((getClass().getName().hashCode() + 527) * 31) + (this.deleteAppHistory == null ? 0 : this.deleteAppHistory.hashCode())) * 31) + (this.updateUdcSetting == null ? 0 : this.updateUdcSetting.hashCode())) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.deleteAppHistory != null) {
                output.writeMessage(1, this.deleteAppHistory);
            }
            if (this.updateUdcSetting != null) {
                output.writeMessage(2, this.updateUdcSetting);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.deleteAppHistory != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(1, this.deleteAppHistory);
            }
            if (this.updateUdcSetting != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(2, this.updateUdcSetting);
            }
            return size;
        }

        public IcingGcmMessage mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        if (this.deleteAppHistory == null) {
                            this.deleteAppHistory = new DeleteAppHistory();
                        }
                        input.readMessage(this.deleteAppHistory);
                        continue;
                    case LogSource.GOOGLE_ANALYTICS /*18*/:
                        if (this.updateUdcSetting == null) {
                            this.updateUdcSetting = new UdcSettingStateChanged();
                        }
                        input.readMessage(this.updateUdcSetting);
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

        public static IcingGcmMessage parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (IcingGcmMessage) MessageNano.mergeFrom(new IcingGcmMessage(), data);
        }

        public static IcingGcmMessage parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new IcingGcmMessage().mergeFrom(input);
        }
    }

    public static final class SchemaOrgProperty extends ExtendableMessageNano<SchemaOrgProperty> {
        private static volatile SchemaOrgProperty[] _emptyArray;
        public String name;
        public SchemaOrgValue value;

        public static SchemaOrgProperty[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new SchemaOrgProperty[0];
                    }
                }
            }
            return _emptyArray;
        }

        public SchemaOrgProperty() {
            clear();
        }

        public SchemaOrgProperty clear() {
            this.name = BuildConfig.VERSION_NAME;
            this.value = null;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof SchemaOrgProperty)) {
                return false;
            }
            SchemaOrgProperty other = (SchemaOrgProperty) o;
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
            if (this.value != null) {
                output.writeMessage(2, this.value);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (!this.name.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.name);
            }
            if (this.value != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(2, this.value);
            }
            return size;
        }

        public SchemaOrgProperty mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        this.name = input.readString();
                        continue;
                    case LogSource.GOOGLE_ANALYTICS /*18*/:
                        if (this.value == null) {
                            this.value = new SchemaOrgValue();
                        }
                        input.readMessage(this.value);
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

        public static SchemaOrgProperty parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (SchemaOrgProperty) MessageNano.mergeFrom(new SchemaOrgProperty(), data);
        }

        public static SchemaOrgProperty parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new SchemaOrgProperty().mergeFrom(input);
        }
    }

    public static final class SchemaOrgThing extends ExtendableMessageNano<SchemaOrgThing> {
        private static volatile SchemaOrgThing[] _emptyArray;
        public SchemaOrgProperty[] property;
        public String type;

        public static SchemaOrgThing[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new SchemaOrgThing[0];
                    }
                }
            }
            return _emptyArray;
        }

        public SchemaOrgThing() {
            clear();
        }

        public SchemaOrgThing clear() {
            this.type = BuildConfig.VERSION_NAME;
            this.property = SchemaOrgProperty.emptyArray();
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof SchemaOrgThing)) {
                return false;
            }
            SchemaOrgThing other = (SchemaOrgThing) o;
            if (this.type == null) {
                if (other.type != null) {
                    return false;
                }
            } else if (!this.type.equals(other.type)) {
                return false;
            }
            if (!InternalNano.equals(this.property, other.property)) {
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
            int hashCode = (((((getClass().getName().hashCode() + 527) * 31) + (this.type == null ? 0 : this.type.hashCode())) * 31) + InternalNano.hashCode(this.property)) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (!this.type.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(1, this.type);
            }
            if (this.property != null && this.property.length > 0) {
                for (SchemaOrgProperty element : this.property) {
                    if (element != null) {
                        output.writeMessage(2, element);
                    }
                }
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (!this.type.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.type);
            }
            if (this.property != null && this.property.length > 0) {
                for (SchemaOrgProperty element : this.property) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(2, element);
                    }
                }
            }
            return size;
        }

        public SchemaOrgThing mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        this.type = input.readString();
                        continue;
                    case LogSource.GOOGLE_ANALYTICS /*18*/:
                        int i;
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 18);
                        if (this.property == null) {
                            i = 0;
                        } else {
                            i = this.property.length;
                        }
                        SchemaOrgProperty[] newArray = new SchemaOrgProperty[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.property, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new SchemaOrgProperty();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i++;
                        }
                        newArray[i] = new SchemaOrgProperty();
                        input.readMessage(newArray[i]);
                        this.property = newArray;
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

        public static SchemaOrgThing parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (SchemaOrgThing) MessageNano.mergeFrom(new SchemaOrgThing(), data);
        }

        public static SchemaOrgThing parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new SchemaOrgThing().mergeFrom(input);
        }
    }

    public static final class SchemaOrgValue extends ExtendableMessageNano<SchemaOrgValue> {
        private static volatile SchemaOrgValue[] _emptyArray;
        public boolean booleanValue;
        public double doubleValue;
        public long int64Value;
        public String stringValue;
        public SchemaOrgThing thingValue;

        public static SchemaOrgValue[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new SchemaOrgValue[0];
                    }
                }
            }
            return _emptyArray;
        }

        public SchemaOrgValue() {
            clear();
        }

        public SchemaOrgValue clear() {
            this.booleanValue = false;
            this.stringValue = BuildConfig.VERSION_NAME;
            this.int64Value = 0;
            this.doubleValue = 0.0d;
            this.thingValue = null;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof SchemaOrgValue)) {
                return false;
            }
            SchemaOrgValue other = (SchemaOrgValue) o;
            if (this.booleanValue != other.booleanValue) {
                return false;
            }
            if (this.stringValue == null) {
                if (other.stringValue != null) {
                    return false;
                }
            } else if (!this.stringValue.equals(other.stringValue)) {
                return false;
            }
            if (this.int64Value != other.int64Value || Double.doubleToLongBits(this.doubleValue) != Double.doubleToLongBits(other.doubleValue)) {
                return false;
            }
            if (this.thingValue == null) {
                if (other.thingValue != null) {
                    return false;
                }
            } else if (!this.thingValue.equals(other.thingValue)) {
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
            int result = ((((((getClass().getName().hashCode() + 527) * 31) + (this.booleanValue ? 1231 : 1237)) * 31) + (this.stringValue == null ? 0 : this.stringValue.hashCode())) * 31) + ((int) (this.int64Value ^ (this.int64Value >>> 32)));
            long v = Double.doubleToLongBits(this.doubleValue);
            int hashCode = ((((result * 31) + ((int) ((v >>> 32) ^ v))) * 31) + (this.thingValue == null ? 0 : this.thingValue.hashCode())) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.booleanValue) {
                output.writeBool(1, this.booleanValue);
            }
            if (!this.stringValue.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(2, this.stringValue);
            }
            if (this.int64Value != 0) {
                output.writeInt64(3, this.int64Value);
            }
            if (Double.doubleToLongBits(this.doubleValue) != Double.doubleToLongBits(0.0d)) {
                output.writeDouble(4, this.doubleValue);
            }
            if (this.thingValue != null) {
                output.writeMessage(5, this.thingValue);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.booleanValue) {
                size += CodedOutputByteBufferNano.computeBoolSize(1, this.booleanValue);
            }
            if (!this.stringValue.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(2, this.stringValue);
            }
            if (this.int64Value != 0) {
                size += CodedOutputByteBufferNano.computeInt64Size(3, this.int64Value);
            }
            if (Double.doubleToLongBits(this.doubleValue) != Double.doubleToLongBits(0.0d)) {
                size += CodedOutputByteBufferNano.computeDoubleSize(4, this.doubleValue);
            }
            if (this.thingValue != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(5, this.thingValue);
            }
            return size;
        }

        public SchemaOrgValue mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_GET_LINK /*8*/:
                        this.booleanValue = input.readBool();
                        continue;
                    case LogSource.GOOGLE_ANALYTICS /*18*/:
                        this.stringValue = input.readString();
                        continue;
                    case LogSource.LB_C /*24*/:
                        this.int64Value = input.readInt64();
                        continue;
                    case LogSource.INSTORE_WALLET /*33*/:
                        this.doubleValue = input.readDouble();
                        continue;
                    case LogSource.PHOTOS /*42*/:
                        if (this.thingValue == null) {
                            this.thingValue = new SchemaOrgThing();
                        }
                        input.readMessage(this.thingValue);
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

        public static SchemaOrgValue parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (SchemaOrgValue) MessageNano.mergeFrom(new SchemaOrgValue(), data);
        }

        public static SchemaOrgValue parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new SchemaOrgValue().mergeFrom(input);
        }
    }

    public static final class SearchSuggestions extends ExtendableMessageNano<SearchSuggestions> {
        private static volatile SearchSuggestions[] _emptyArray;
        public EntityMetadata[] entityMetadata;
        public EventMetadata[] eventMetadata;
        public SuggestData[] querySuggestions;

        public static final class EntityMetadata extends ExtendableMessageNano<EntityMetadata> {
            private static volatile EntityMetadata[] _emptyArray;
            public CalypsoDeeplink[] calypsoDeeplink;
            public String debug;
            public String description;
            public Info[] info;
            public String mid;
            public String title;
            public String type;
            public WebLink[] weblink;

            public static final class Info extends ExtendableMessageNano<Info> {
                private static volatile Info[] _emptyArray;
                public String tag;
                public String value;

                public static Info[] emptyArray() {
                    if (_emptyArray == null) {
                        synchronized (InternalNano.LAZY_INIT_LOCK) {
                            if (_emptyArray == null) {
                                _emptyArray = new Info[0];
                            }
                        }
                    }
                    return _emptyArray;
                }

                public Info() {
                    clear();
                }

                public Info clear() {
                    this.tag = BuildConfig.VERSION_NAME;
                    this.value = BuildConfig.VERSION_NAME;
                    this.unknownFieldData = null;
                    this.cachedSize = -1;
                    return this;
                }

                public boolean equals(Object o) {
                    if (o == this) {
                        return true;
                    }
                    if (!(o instanceof Info)) {
                        return false;
                    }
                    Info other = (Info) o;
                    if (this.tag == null) {
                        if (other.tag != null) {
                            return false;
                        }
                    } else if (!this.tag.equals(other.tag)) {
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
                    int hashCode = (((((getClass().getName().hashCode() + 527) * 31) + (this.tag == null ? 0 : this.tag.hashCode())) * 31) + (this.value == null ? 0 : this.value.hashCode())) * 31;
                    if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                        i = this.unknownFieldData.hashCode();
                    }
                    return hashCode + i;
                }

                public void writeTo(CodedOutputByteBufferNano output) throws IOException {
                    if (!this.tag.equals(BuildConfig.VERSION_NAME)) {
                        output.writeString(1, this.tag);
                    }
                    if (!this.value.equals(BuildConfig.VERSION_NAME)) {
                        output.writeString(2, this.value);
                    }
                    super.writeTo(output);
                }

                protected int computeSerializedSize() {
                    int size = super.computeSerializedSize();
                    if (!this.tag.equals(BuildConfig.VERSION_NAME)) {
                        size += CodedOutputByteBufferNano.computeStringSize(1, this.tag);
                    }
                    if (this.value.equals(BuildConfig.VERSION_NAME)) {
                        return size;
                    }
                    return size + CodedOutputByteBufferNano.computeStringSize(2, this.value);
                }

                public Info mergeFrom(CodedInputByteBufferNano input) throws IOException {
                    while (true) {
                        int tag = input.readTag();
                        switch (tag) {
                            case Action.UNKNOWN /*0*/:
                                break;
                            case Type.TAP_ABOUT /*10*/:
                                this.tag = input.readString();
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

                public static Info parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
                    return (Info) MessageNano.mergeFrom(new Info(), data);
                }

                public static Info parseFrom(CodedInputByteBufferNano input) throws IOException {
                    return new Info().mergeFrom(input);
                }
            }

            public static final class WebLink extends ExtendableMessageNano<WebLink> {
                private static volatile WebLink[] _emptyArray;
                public String displayName;
                public String url;

                public static WebLink[] emptyArray() {
                    if (_emptyArray == null) {
                        synchronized (InternalNano.LAZY_INIT_LOCK) {
                            if (_emptyArray == null) {
                                _emptyArray = new WebLink[0];
                            }
                        }
                    }
                    return _emptyArray;
                }

                public WebLink() {
                    clear();
                }

                public WebLink clear() {
                    this.url = BuildConfig.VERSION_NAME;
                    this.displayName = BuildConfig.VERSION_NAME;
                    this.unknownFieldData = null;
                    this.cachedSize = -1;
                    return this;
                }

                public boolean equals(Object o) {
                    if (o == this) {
                        return true;
                    }
                    if (!(o instanceof WebLink)) {
                        return false;
                    }
                    WebLink other = (WebLink) o;
                    if (this.url == null) {
                        if (other.url != null) {
                            return false;
                        }
                    } else if (!this.url.equals(other.url)) {
                        return false;
                    }
                    if (this.displayName == null) {
                        if (other.displayName != null) {
                            return false;
                        }
                    } else if (!this.displayName.equals(other.displayName)) {
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
                    int hashCode = (((((getClass().getName().hashCode() + 527) * 31) + (this.url == null ? 0 : this.url.hashCode())) * 31) + (this.displayName == null ? 0 : this.displayName.hashCode())) * 31;
                    if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                        i = this.unknownFieldData.hashCode();
                    }
                    return hashCode + i;
                }

                public void writeTo(CodedOutputByteBufferNano output) throws IOException {
                    if (!this.url.equals(BuildConfig.VERSION_NAME)) {
                        output.writeString(1, this.url);
                    }
                    if (!this.displayName.equals(BuildConfig.VERSION_NAME)) {
                        output.writeString(2, this.displayName);
                    }
                    super.writeTo(output);
                }

                protected int computeSerializedSize() {
                    int size = super.computeSerializedSize();
                    if (!this.url.equals(BuildConfig.VERSION_NAME)) {
                        size += CodedOutputByteBufferNano.computeStringSize(1, this.url);
                    }
                    if (this.displayName.equals(BuildConfig.VERSION_NAME)) {
                        return size;
                    }
                    return size + CodedOutputByteBufferNano.computeStringSize(2, this.displayName);
                }

                public WebLink mergeFrom(CodedInputByteBufferNano input) throws IOException {
                    while (true) {
                        int tag = input.readTag();
                        switch (tag) {
                            case Action.UNKNOWN /*0*/:
                                break;
                            case Type.TAP_ABOUT /*10*/:
                                this.url = input.readString();
                                continue;
                            case LogSource.GOOGLE_ANALYTICS /*18*/:
                                this.displayName = input.readString();
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

                public static WebLink parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
                    return (WebLink) MessageNano.mergeFrom(new WebLink(), data);
                }

                public static WebLink parseFrom(CodedInputByteBufferNano input) throws IOException {
                    return new WebLink().mergeFrom(input);
                }
            }

            public static EntityMetadata[] emptyArray() {
                if (_emptyArray == null) {
                    synchronized (InternalNano.LAZY_INIT_LOCK) {
                        if (_emptyArray == null) {
                            _emptyArray = new EntityMetadata[0];
                        }
                    }
                }
                return _emptyArray;
            }

            public EntityMetadata() {
                clear();
            }

            public EntityMetadata clear() {
                this.mid = BuildConfig.VERSION_NAME;
                this.title = BuildConfig.VERSION_NAME;
                this.calypsoDeeplink = CalypsoDeeplink.emptyArray();
                this.weblink = WebLink.emptyArray();
                this.description = BuildConfig.VERSION_NAME;
                this.type = BuildConfig.VERSION_NAME;
                this.debug = BuildConfig.VERSION_NAME;
                this.info = Info.emptyArray();
                this.unknownFieldData = null;
                this.cachedSize = -1;
                return this;
            }

            public boolean equals(Object o) {
                if (o == this) {
                    return true;
                }
                if (!(o instanceof EntityMetadata)) {
                    return false;
                }
                EntityMetadata other = (EntityMetadata) o;
                if (this.mid == null) {
                    if (other.mid != null) {
                        return false;
                    }
                } else if (!this.mid.equals(other.mid)) {
                    return false;
                }
                if (this.title == null) {
                    if (other.title != null) {
                        return false;
                    }
                } else if (!this.title.equals(other.title)) {
                    return false;
                }
                if (!InternalNano.equals(this.calypsoDeeplink, other.calypsoDeeplink) || !InternalNano.equals(this.weblink, other.weblink)) {
                    return false;
                }
                if (this.description == null) {
                    if (other.description != null) {
                        return false;
                    }
                } else if (!this.description.equals(other.description)) {
                    return false;
                }
                if (this.type == null) {
                    if (other.type != null) {
                        return false;
                    }
                } else if (!this.type.equals(other.type)) {
                    return false;
                }
                if (this.debug == null) {
                    if (other.debug != null) {
                        return false;
                    }
                } else if (!this.debug.equals(other.debug)) {
                    return false;
                }
                if (!InternalNano.equals(this.info, other.info)) {
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
                int hashCode = (((((((((((((((((getClass().getName().hashCode() + 527) * 31) + (this.mid == null ? 0 : this.mid.hashCode())) * 31) + (this.title == null ? 0 : this.title.hashCode())) * 31) + InternalNano.hashCode(this.calypsoDeeplink)) * 31) + InternalNano.hashCode(this.weblink)) * 31) + (this.description == null ? 0 : this.description.hashCode())) * 31) + (this.type == null ? 0 : this.type.hashCode())) * 31) + (this.debug == null ? 0 : this.debug.hashCode())) * 31) + InternalNano.hashCode(this.info)) * 31;
                if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                    i = this.unknownFieldData.hashCode();
                }
                return hashCode + i;
            }

            public void writeTo(CodedOutputByteBufferNano output) throws IOException {
                if (!this.mid.equals(BuildConfig.VERSION_NAME)) {
                    output.writeString(1, this.mid);
                }
                if (!this.title.equals(BuildConfig.VERSION_NAME)) {
                    output.writeString(2, this.title);
                }
                if (this.calypsoDeeplink != null && this.calypsoDeeplink.length > 0) {
                    for (CalypsoDeeplink element : this.calypsoDeeplink) {
                        if (element != null) {
                            output.writeMessage(3, element);
                        }
                    }
                }
                if (this.weblink != null && this.weblink.length > 0) {
                    for (WebLink element2 : this.weblink) {
                        if (element2 != null) {
                            output.writeMessage(4, element2);
                        }
                    }
                }
                if (!this.description.equals(BuildConfig.VERSION_NAME)) {
                    output.writeString(5, this.description);
                }
                if (!this.type.equals(BuildConfig.VERSION_NAME)) {
                    output.writeString(6, this.type);
                }
                if (!this.debug.equals(BuildConfig.VERSION_NAME)) {
                    output.writeString(7, this.debug);
                }
                if (this.info != null && this.info.length > 0) {
                    for (Info element3 : this.info) {
                        if (element3 != null) {
                            output.writeMessage(8, element3);
                        }
                    }
                }
                super.writeTo(output);
            }

            protected int computeSerializedSize() {
                int size = super.computeSerializedSize();
                if (!this.mid.equals(BuildConfig.VERSION_NAME)) {
                    size += CodedOutputByteBufferNano.computeStringSize(1, this.mid);
                }
                if (!this.title.equals(BuildConfig.VERSION_NAME)) {
                    size += CodedOutputByteBufferNano.computeStringSize(2, this.title);
                }
                if (this.calypsoDeeplink != null && this.calypsoDeeplink.length > 0) {
                    for (CalypsoDeeplink element : this.calypsoDeeplink) {
                        if (element != null) {
                            size += CodedOutputByteBufferNano.computeMessageSize(3, element);
                        }
                    }
                }
                if (this.weblink != null && this.weblink.length > 0) {
                    for (WebLink element2 : this.weblink) {
                        if (element2 != null) {
                            size += CodedOutputByteBufferNano.computeMessageSize(4, element2);
                        }
                    }
                }
                if (!this.description.equals(BuildConfig.VERSION_NAME)) {
                    size += CodedOutputByteBufferNano.computeStringSize(5, this.description);
                }
                if (!this.type.equals(BuildConfig.VERSION_NAME)) {
                    size += CodedOutputByteBufferNano.computeStringSize(6, this.type);
                }
                if (!this.debug.equals(BuildConfig.VERSION_NAME)) {
                    size += CodedOutputByteBufferNano.computeStringSize(7, this.debug);
                }
                if (this.info != null && this.info.length > 0) {
                    for (Info element3 : this.info) {
                        if (element3 != null) {
                            size += CodedOutputByteBufferNano.computeMessageSize(8, element3);
                        }
                    }
                }
                return size;
            }

            public EntityMetadata mergeFrom(CodedInputByteBufferNano input) throws IOException {
                while (true) {
                    int tag = input.readTag();
                    int arrayLength;
                    int i;
                    switch (tag) {
                        case Action.UNKNOWN /*0*/:
                            break;
                        case Type.TAP_ABOUT /*10*/:
                            this.mid = input.readString();
                            continue;
                        case LogSource.GOOGLE_ANALYTICS /*18*/:
                            this.title = input.readString();
                            continue;
                        case LogSource.ANDROID_CAMERA /*26*/:
                            arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 26);
                            if (this.calypsoDeeplink == null) {
                                i = 0;
                            } else {
                                i = this.calypsoDeeplink.length;
                            }
                            CalypsoDeeplink[] newArray = new CalypsoDeeplink[(i + arrayLength)];
                            if (i != 0) {
                                System.arraycopy(this.calypsoDeeplink, 0, newArray, 0, i);
                            }
                            while (i < newArray.length - 1) {
                                newArray[i] = new CalypsoDeeplink();
                                input.readMessage(newArray[i]);
                                input.readTag();
                                i++;
                            }
                            newArray[i] = new CalypsoDeeplink();
                            input.readMessage(newArray[i]);
                            this.calypsoDeeplink = newArray;
                            continue;
                        case LogSource.NOVA /*34*/:
                            arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 34);
                            if (this.weblink == null) {
                                i = 0;
                            } else {
                                i = this.weblink.length;
                            }
                            WebLink[] newArray2 = new WebLink[(i + arrayLength)];
                            if (i != 0) {
                                System.arraycopy(this.weblink, 0, newArray2, 0, i);
                            }
                            while (i < newArray2.length - 1) {
                                newArray2[i] = new WebLink();
                                input.readMessage(newArray2[i]);
                                input.readTag();
                                i++;
                            }
                            newArray2[i] = new WebLink();
                            input.readMessage(newArray2[i]);
                            this.weblink = newArray2;
                            continue;
                        case LogSource.PHOTOS /*42*/:
                            this.description = input.readString();
                            continue;
                        case LogSource.HANGOUT_LOG_REQUEST /*50*/:
                            this.type = input.readString();
                            continue;
                        case LogSource.SLIDES /*58*/:
                            this.debug = input.readString();
                            continue;
                        case LogSource.WIFI_ASSISTANT /*66*/:
                            arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 66);
                            if (this.info == null) {
                                i = 0;
                            } else {
                                i = this.info.length;
                            }
                            Info[] newArray3 = new Info[(i + arrayLength)];
                            if (i != 0) {
                                System.arraycopy(this.info, 0, newArray3, 0, i);
                            }
                            while (i < newArray3.length - 1) {
                                newArray3[i] = new Info();
                                input.readMessage(newArray3[i]);
                                input.readTag();
                                i++;
                            }
                            newArray3[i] = new Info();
                            input.readMessage(newArray3[i]);
                            this.info = newArray3;
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

            public static EntityMetadata parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
                return (EntityMetadata) MessageNano.mergeFrom(new EntityMetadata(), data);
            }

            public static EntityMetadata parseFrom(CodedInputByteBufferNano input) throws IOException {
                return new EntityMetadata().mergeFrom(input);
            }
        }

        public static final class EventMetadata extends ExtendableMessageNano<EventMetadata> {
            private static volatile EventMetadata[] _emptyArray;
            public String event;
            public int eventType;
            public String sourceApp;

            public interface EventType {
                public static final int ADDRESS = 1;
                public static final int NAME = 2;
                public static final int OTHER = 0;
                public static final int PHONE = 3;
            }

            public static EventMetadata[] emptyArray() {
                if (_emptyArray == null) {
                    synchronized (InternalNano.LAZY_INIT_LOCK) {
                        if (_emptyArray == null) {
                            _emptyArray = new EventMetadata[0];
                        }
                    }
                }
                return _emptyArray;
            }

            public EventMetadata() {
                clear();
            }

            public EventMetadata clear() {
                this.event = BuildConfig.VERSION_NAME;
                this.eventType = 0;
                this.sourceApp = BuildConfig.VERSION_NAME;
                this.unknownFieldData = null;
                this.cachedSize = -1;
                return this;
            }

            public boolean equals(Object o) {
                if (o == this) {
                    return true;
                }
                if (!(o instanceof EventMetadata)) {
                    return false;
                }
                EventMetadata other = (EventMetadata) o;
                if (this.event == null) {
                    if (other.event != null) {
                        return false;
                    }
                } else if (!this.event.equals(other.event)) {
                    return false;
                }
                if (this.eventType != other.eventType) {
                    return false;
                }
                if (this.sourceApp == null) {
                    if (other.sourceApp != null) {
                        return false;
                    }
                } else if (!this.sourceApp.equals(other.sourceApp)) {
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
                int hashCode = (((((((getClass().getName().hashCode() + 527) * 31) + (this.event == null ? 0 : this.event.hashCode())) * 31) + this.eventType) * 31) + (this.sourceApp == null ? 0 : this.sourceApp.hashCode())) * 31;
                if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                    i = this.unknownFieldData.hashCode();
                }
                return hashCode + i;
            }

            public void writeTo(CodedOutputByteBufferNano output) throws IOException {
                if (!this.event.equals(BuildConfig.VERSION_NAME)) {
                    output.writeString(1, this.event);
                }
                if (this.eventType != 0) {
                    output.writeInt32(2, this.eventType);
                }
                if (!this.sourceApp.equals(BuildConfig.VERSION_NAME)) {
                    output.writeString(3, this.sourceApp);
                }
                super.writeTo(output);
            }

            protected int computeSerializedSize() {
                int size = super.computeSerializedSize();
                if (!this.event.equals(BuildConfig.VERSION_NAME)) {
                    size += CodedOutputByteBufferNano.computeStringSize(1, this.event);
                }
                if (this.eventType != 0) {
                    size += CodedOutputByteBufferNano.computeInt32Size(2, this.eventType);
                }
                if (this.sourceApp.equals(BuildConfig.VERSION_NAME)) {
                    return size;
                }
                return size + CodedOutputByteBufferNano.computeStringSize(3, this.sourceApp);
            }

            public EventMetadata mergeFrom(CodedInputByteBufferNano input) throws IOException {
                while (true) {
                    int tag = input.readTag();
                    switch (tag) {
                        case Action.UNKNOWN /*0*/:
                            break;
                        case Type.TAP_ABOUT /*10*/:
                            this.event = input.readString();
                            continue;
                        case LogSource.GMS_CORE_PEOPLE /*16*/:
                            int value = input.readInt32();
                            switch (value) {
                                case Action.UNKNOWN /*0*/:
                                case TimeSelection.Type.TEMPORARY /*1*/:
                                case TimeSelection.Type.INDEFINITELY /*2*/:
                                case TimeSelection.Type.CUSTOM /*3*/:
                                    this.eventType = value;
                                    break;
                                default:
                                    continue;
                            }
                        case LogSource.ANDROID_CAMERA /*26*/:
                            this.sourceApp = input.readString();
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

            public static EventMetadata parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
                return (EventMetadata) MessageNano.mergeFrom(new EventMetadata(), data);
            }

            public static EventMetadata parseFrom(CodedInputByteBufferNano input) throws IOException {
                return new EventMetadata().mergeFrom(input);
            }
        }

        public static final class SuggestData extends ExtendableMessageNano<SuggestData> {
            private static volatile SuggestData[] _emptyArray;
            public String[] appPackageName;
            public String associatedUrls;
            public String queryTerms;
            public double score;

            public static SuggestData[] emptyArray() {
                if (_emptyArray == null) {
                    synchronized (InternalNano.LAZY_INIT_LOCK) {
                        if (_emptyArray == null) {
                            _emptyArray = new SuggestData[0];
                        }
                    }
                }
                return _emptyArray;
            }

            public SuggestData() {
                clear();
            }

            public SuggestData clear() {
                this.queryTerms = BuildConfig.VERSION_NAME;
                this.associatedUrls = BuildConfig.VERSION_NAME;
                this.score = 0.0d;
                this.appPackageName = WireFormatNano.EMPTY_STRING_ARRAY;
                this.unknownFieldData = null;
                this.cachedSize = -1;
                return this;
            }

            public boolean equals(Object o) {
                if (o == this) {
                    return true;
                }
                if (!(o instanceof SuggestData)) {
                    return false;
                }
                SuggestData other = (SuggestData) o;
                if (this.queryTerms == null) {
                    if (other.queryTerms != null) {
                        return false;
                    }
                } else if (!this.queryTerms.equals(other.queryTerms)) {
                    return false;
                }
                if (this.associatedUrls == null) {
                    if (other.associatedUrls != null) {
                        return false;
                    }
                } else if (!this.associatedUrls.equals(other.associatedUrls)) {
                    return false;
                }
                if (Double.doubleToLongBits(this.score) != Double.doubleToLongBits(other.score) || !InternalNano.equals(this.appPackageName, other.appPackageName)) {
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
                int result = ((((getClass().getName().hashCode() + 527) * 31) + (this.queryTerms == null ? 0 : this.queryTerms.hashCode())) * 31) + (this.associatedUrls == null ? 0 : this.associatedUrls.hashCode());
                long v = Double.doubleToLongBits(this.score);
                int hashCode = ((((result * 31) + ((int) ((v >>> 32) ^ v))) * 31) + InternalNano.hashCode(this.appPackageName)) * 31;
                if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                    i = this.unknownFieldData.hashCode();
                }
                return hashCode + i;
            }

            public void writeTo(CodedOutputByteBufferNano output) throws IOException {
                if (!this.queryTerms.equals(BuildConfig.VERSION_NAME)) {
                    output.writeString(1, this.queryTerms);
                }
                if (!this.associatedUrls.equals(BuildConfig.VERSION_NAME)) {
                    output.writeString(2, this.associatedUrls);
                }
                if (Double.doubleToLongBits(this.score) != Double.doubleToLongBits(0.0d)) {
                    output.writeDouble(3, this.score);
                }
                if (this.appPackageName != null && this.appPackageName.length > 0) {
                    for (String element : this.appPackageName) {
                        if (element != null) {
                            output.writeString(4, element);
                        }
                    }
                }
                super.writeTo(output);
            }

            protected int computeSerializedSize() {
                int size = super.computeSerializedSize();
                if (!this.queryTerms.equals(BuildConfig.VERSION_NAME)) {
                    size += CodedOutputByteBufferNano.computeStringSize(1, this.queryTerms);
                }
                if (!this.associatedUrls.equals(BuildConfig.VERSION_NAME)) {
                    size += CodedOutputByteBufferNano.computeStringSize(2, this.associatedUrls);
                }
                if (Double.doubleToLongBits(this.score) != Double.doubleToLongBits(0.0d)) {
                    size += CodedOutputByteBufferNano.computeDoubleSize(3, this.score);
                }
                if (this.appPackageName == null || this.appPackageName.length <= 0) {
                    return size;
                }
                int dataCount = 0;
                int dataSize = 0;
                for (String element : this.appPackageName) {
                    if (element != null) {
                        dataCount++;
                        dataSize += CodedOutputByteBufferNano.computeStringSizeNoTag(element);
                    }
                }
                return (size + dataSize) + (dataCount * 1);
            }

            public SuggestData mergeFrom(CodedInputByteBufferNano input) throws IOException {
                while (true) {
                    int tag = input.readTag();
                    switch (tag) {
                        case Action.UNKNOWN /*0*/:
                            break;
                        case Type.TAP_ABOUT /*10*/:
                            this.queryTerms = input.readString();
                            continue;
                        case LogSource.GOOGLE_ANALYTICS /*18*/:
                            this.associatedUrls = input.readString();
                            continue;
                        case LogSource.ANDROID_AUTH /*25*/:
                            this.score = input.readDouble();
                            continue;
                        case LogSource.NOVA /*34*/:
                            int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 34);
                            int i = this.appPackageName == null ? 0 : this.appPackageName.length;
                            String[] newArray = new String[(i + arrayLength)];
                            if (i != 0) {
                                System.arraycopy(this.appPackageName, 0, newArray, 0, i);
                            }
                            while (i < newArray.length - 1) {
                                newArray[i] = input.readString();
                                input.readTag();
                                i++;
                            }
                            newArray[i] = input.readString();
                            this.appPackageName = newArray;
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

            public static SuggestData parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
                return (SuggestData) MessageNano.mergeFrom(new SuggestData(), data);
            }

            public static SuggestData parseFrom(CodedInputByteBufferNano input) throws IOException {
                return new SuggestData().mergeFrom(input);
            }
        }

        public static SearchSuggestions[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new SearchSuggestions[0];
                    }
                }
            }
            return _emptyArray;
        }

        public SearchSuggestions() {
            clear();
        }

        public SearchSuggestions clear() {
            this.querySuggestions = SuggestData.emptyArray();
            this.entityMetadata = EntityMetadata.emptyArray();
            this.eventMetadata = EventMetadata.emptyArray();
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof SearchSuggestions)) {
                return false;
            }
            SearchSuggestions other = (SearchSuggestions) o;
            if (!InternalNano.equals(this.querySuggestions, other.querySuggestions) || !InternalNano.equals(this.entityMetadata, other.entityMetadata) || !InternalNano.equals(this.eventMetadata, other.eventMetadata)) {
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
            int hashCode = (((((((getClass().getName().hashCode() + 527) * 31) + InternalNano.hashCode(this.querySuggestions)) * 31) + InternalNano.hashCode(this.entityMetadata)) * 31) + InternalNano.hashCode(this.eventMetadata)) * 31;
            int hashCode2 = (this.unknownFieldData == null || this.unknownFieldData.isEmpty()) ? 0 : this.unknownFieldData.hashCode();
            return hashCode + hashCode2;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.querySuggestions != null && this.querySuggestions.length > 0) {
                for (SuggestData element : this.querySuggestions) {
                    if (element != null) {
                        output.writeMessage(1, element);
                    }
                }
            }
            if (this.entityMetadata != null && this.entityMetadata.length > 0) {
                for (EntityMetadata element2 : this.entityMetadata) {
                    if (element2 != null) {
                        output.writeMessage(2, element2);
                    }
                }
            }
            if (this.eventMetadata != null && this.eventMetadata.length > 0) {
                for (EventMetadata element3 : this.eventMetadata) {
                    if (element3 != null) {
                        output.writeMessage(3, element3);
                    }
                }
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.querySuggestions != null && this.querySuggestions.length > 0) {
                for (SuggestData element : this.querySuggestions) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(1, element);
                    }
                }
            }
            if (this.entityMetadata != null && this.entityMetadata.length > 0) {
                for (EntityMetadata element2 : this.entityMetadata) {
                    if (element2 != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(2, element2);
                    }
                }
            }
            if (this.eventMetadata != null && this.eventMetadata.length > 0) {
                for (EventMetadata element3 : this.eventMetadata) {
                    if (element3 != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(3, element3);
                    }
                }
            }
            return size;
        }

        public SearchSuggestions mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                int arrayLength;
                int i;
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 10);
                        if (this.querySuggestions == null) {
                            i = 0;
                        } else {
                            i = this.querySuggestions.length;
                        }
                        SuggestData[] newArray = new SuggestData[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.querySuggestions, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new SuggestData();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i++;
                        }
                        newArray[i] = new SuggestData();
                        input.readMessage(newArray[i]);
                        this.querySuggestions = newArray;
                        continue;
                    case LogSource.GOOGLE_ANALYTICS /*18*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 18);
                        if (this.entityMetadata == null) {
                            i = 0;
                        } else {
                            i = this.entityMetadata.length;
                        }
                        EntityMetadata[] newArray2 = new EntityMetadata[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.entityMetadata, 0, newArray2, 0, i);
                        }
                        while (i < newArray2.length - 1) {
                            newArray2[i] = new EntityMetadata();
                            input.readMessage(newArray2[i]);
                            input.readTag();
                            i++;
                        }
                        newArray2[i] = new EntityMetadata();
                        input.readMessage(newArray2[i]);
                        this.entityMetadata = newArray2;
                        continue;
                    case LogSource.ANDROID_CAMERA /*26*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 26);
                        if (this.eventMetadata == null) {
                            i = 0;
                        } else {
                            i = this.eventMetadata.length;
                        }
                        EventMetadata[] newArray3 = new EventMetadata[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.eventMetadata, 0, newArray3, 0, i);
                        }
                        while (i < newArray3.length - 1) {
                            newArray3[i] = new EventMetadata();
                            input.readMessage(newArray3[i]);
                            input.readTag();
                            i++;
                        }
                        newArray3[i] = new EventMetadata();
                        input.readMessage(newArray3[i]);
                        this.eventMetadata = newArray3;
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

        public static SearchSuggestions parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (SearchSuggestions) MessageNano.mergeFrom(new SearchSuggestions(), data);
        }

        public static SearchSuggestions parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new SearchSuggestions().mergeFrom(input);
        }
    }
}
