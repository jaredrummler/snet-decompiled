package com.google.geo.sidekick;

import com.google.android.gms.lint.BuildConfig;
import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.ExtendableMessageNano;
import com.google.protobuf.nano.InternalNano;
import com.google.protobuf.nano.InvalidProtocolBufferNanoException;
import com.google.protobuf.nano.MessageNano;
import com.google.wireless.android.play.playlog.proto.ClientAnalytics.LogRequest.LogSource;
import com.google.wireless.android.play.playlog.proto.UserLocation.LaunchSettingsEvent.Action;
import com.google.wireless.android.play.playlog.proto.UserLocation.LocationSharingEvent.Type;
import java.io.IOException;

public interface ThirdPartyProto {

    public static final class AndroidAppInfo extends ExtendableMessageNano<AndroidAppInfo> {
        private static volatile AndroidAppInfo[] _emptyArray;
        public String appId;
        public int appVersion;
        public boolean debuggable;

        public static AndroidAppInfo[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new AndroidAppInfo[0];
                    }
                }
            }
            return _emptyArray;
        }

        public AndroidAppInfo() {
            clear();
        }

        public AndroidAppInfo clear() {
            this.appId = BuildConfig.VERSION_NAME;
            this.appVersion = 0;
            this.debuggable = false;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof AndroidAppInfo)) {
                return false;
            }
            AndroidAppInfo other = (AndroidAppInfo) o;
            if (this.appId == null) {
                if (other.appId != null) {
                    return false;
                }
            } else if (!this.appId.equals(other.appId)) {
                return false;
            }
            if (this.appVersion != other.appVersion || this.debuggable != other.debuggable) {
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
            int hashCode = (((((((getClass().getName().hashCode() + 527) * 31) + (this.appId == null ? 0 : this.appId.hashCode())) * 31) + this.appVersion) * 31) + (this.debuggable ? 1231 : 1237)) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (!this.appId.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(1, this.appId);
            }
            if (this.appVersion != 0) {
                output.writeInt32(2, this.appVersion);
            }
            if (this.debuggable) {
                output.writeBool(3, this.debuggable);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (!this.appId.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.appId);
            }
            if (this.appVersion != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(2, this.appVersion);
            }
            if (this.debuggable) {
                return size + CodedOutputByteBufferNano.computeBoolSize(3, this.debuggable);
            }
            return size;
        }

        public AndroidAppInfo mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        this.appId = input.readString();
                        continue;
                    case LogSource.GMS_CORE_PEOPLE /*16*/:
                        this.appVersion = input.readInt32();
                        continue;
                    case LogSource.LB_C /*24*/:
                        this.debuggable = input.readBool();
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

        public static AndroidAppInfo parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (AndroidAppInfo) MessageNano.mergeFrom(new AndroidAppInfo(), data);
        }

        public static AndroidAppInfo parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new AndroidAppInfo().mergeFrom(input);
        }
    }

    public static final class ThirdPartyAssociationQuery extends ExtendableMessageNano<ThirdPartyAssociationQuery> {
        private static volatile ThirdPartyAssociationQuery[] _emptyArray;
        public AndroidAppInfo androidAppInfo;
        public String clientId;

        public static ThirdPartyAssociationQuery[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new ThirdPartyAssociationQuery[0];
                    }
                }
            }
            return _emptyArray;
        }

        public ThirdPartyAssociationQuery() {
            clear();
        }

        public ThirdPartyAssociationQuery clear() {
            this.clientId = BuildConfig.VERSION_NAME;
            this.androidAppInfo = null;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof ThirdPartyAssociationQuery)) {
                return false;
            }
            ThirdPartyAssociationQuery other = (ThirdPartyAssociationQuery) o;
            if (this.clientId == null) {
                if (other.clientId != null) {
                    return false;
                }
            } else if (!this.clientId.equals(other.clientId)) {
                return false;
            }
            if (this.androidAppInfo == null) {
                if (other.androidAppInfo != null) {
                    return false;
                }
            } else if (!this.androidAppInfo.equals(other.androidAppInfo)) {
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
            int hashCode = (((((getClass().getName().hashCode() + 527) * 31) + (this.clientId == null ? 0 : this.clientId.hashCode())) * 31) + (this.androidAppInfo == null ? 0 : this.androidAppInfo.hashCode())) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (!this.clientId.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(1, this.clientId);
            }
            if (this.androidAppInfo != null) {
                output.writeMessage(2, this.androidAppInfo);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (!this.clientId.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.clientId);
            }
            if (this.androidAppInfo != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(2, this.androidAppInfo);
            }
            return size;
        }

        public ThirdPartyAssociationQuery mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        this.clientId = input.readString();
                        continue;
                    case LogSource.GOOGLE_ANALYTICS /*18*/:
                        if (this.androidAppInfo == null) {
                            this.androidAppInfo = new AndroidAppInfo();
                        }
                        input.readMessage(this.androidAppInfo);
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

        public static ThirdPartyAssociationQuery parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (ThirdPartyAssociationQuery) MessageNano.mergeFrom(new ThirdPartyAssociationQuery(), data);
        }

        public static ThirdPartyAssociationQuery parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new ThirdPartyAssociationQuery().mergeFrom(input);
        }
    }

    public static final class ThirdPartyAssociationResponse extends ExtendableMessageNano<ThirdPartyAssociationResponse> {
        private static volatile ThirdPartyAssociationResponse[] _emptyArray;
        public boolean allowAssociation;
        public String details;
        public int reason;
        public int throttlerMaxRequestsPerWindow;
        public int throttlerTimeWindowSeconds;

        public interface Reason {
            public static final int NOT_ON_WHITELIST = 1;
            public static final int NOT_OPTED_INTO_NOW = 2;
            public static final int NOT_OPTED_INTO_PARTNER = 3;
            public static final int NOT_THE_LINKED_APP = 5;
            public static final int NOT_THE_LINKED_DEVICE = 4;
        }

        public static ThirdPartyAssociationResponse[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new ThirdPartyAssociationResponse[0];
                    }
                }
            }
            return _emptyArray;
        }

        public ThirdPartyAssociationResponse() {
            clear();
        }

        public ThirdPartyAssociationResponse clear() {
            this.allowAssociation = false;
            this.reason = 0;
            this.details = BuildConfig.VERSION_NAME;
            this.throttlerTimeWindowSeconds = 0;
            this.throttlerMaxRequestsPerWindow = 0;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof ThirdPartyAssociationResponse)) {
                return false;
            }
            ThirdPartyAssociationResponse other = (ThirdPartyAssociationResponse) o;
            if (this.allowAssociation != other.allowAssociation || this.reason != other.reason) {
                return false;
            }
            if (this.details == null) {
                if (other.details != null) {
                    return false;
                }
            } else if (!this.details.equals(other.details)) {
                return false;
            }
            if (this.throttlerTimeWindowSeconds != other.throttlerTimeWindowSeconds || this.throttlerMaxRequestsPerWindow != other.throttlerMaxRequestsPerWindow) {
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
            int hashCode = (((((((((((getClass().getName().hashCode() + 527) * 31) + (this.allowAssociation ? 1231 : 1237)) * 31) + this.reason) * 31) + (this.details == null ? 0 : this.details.hashCode())) * 31) + this.throttlerTimeWindowSeconds) * 31) + this.throttlerMaxRequestsPerWindow) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.allowAssociation) {
                output.writeBool(1, this.allowAssociation);
            }
            if (this.reason != 0) {
                output.writeInt32(2, this.reason);
            }
            if (!this.details.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(3, this.details);
            }
            if (this.throttlerTimeWindowSeconds != 0) {
                output.writeInt32(4, this.throttlerTimeWindowSeconds);
            }
            if (this.throttlerMaxRequestsPerWindow != 0) {
                output.writeInt32(5, this.throttlerMaxRequestsPerWindow);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.allowAssociation) {
                size += CodedOutputByteBufferNano.computeBoolSize(1, this.allowAssociation);
            }
            if (this.reason != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(2, this.reason);
            }
            if (!this.details.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(3, this.details);
            }
            if (this.throttlerTimeWindowSeconds != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(4, this.throttlerTimeWindowSeconds);
            }
            if (this.throttlerMaxRequestsPerWindow != 0) {
                return size + CodedOutputByteBufferNano.computeInt32Size(5, this.throttlerMaxRequestsPerWindow);
            }
            return size;
        }

        public ThirdPartyAssociationResponse mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_GET_LINK /*8*/:
                        this.allowAssociation = input.readBool();
                        continue;
                    case LogSource.GMS_CORE_PEOPLE /*16*/:
                        this.reason = input.readInt32();
                        continue;
                    case LogSource.ANDROID_CAMERA /*26*/:
                        this.details = input.readString();
                        continue;
                    case LogSource.SOCIAL /*32*/:
                        this.throttlerTimeWindowSeconds = input.readInt32();
                        continue;
                    case LogSource.COPRESENCE /*40*/:
                        this.throttlerMaxRequestsPerWindow = input.readInt32();
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

        public static ThirdPartyAssociationResponse parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (ThirdPartyAssociationResponse) MessageNano.mergeFrom(new ThirdPartyAssociationResponse(), data);
        }

        public static ThirdPartyAssociationResponse parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new ThirdPartyAssociationResponse().mergeFrom(input);
        }
    }
}
