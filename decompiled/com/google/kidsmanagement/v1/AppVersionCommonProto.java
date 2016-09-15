package com.google.kidsmanagement.v1;

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
import com.google.wireless.android.play.playlog.proto.UserLocation.OvenFreshEvent.OvenFreshResult;
import com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection;
import java.io.IOException;

public interface AppVersionCommonProto {

    public static final class AppUpgradeStatus extends ExtendableMessageNano<AppUpgradeStatus> {
        private static volatile AppUpgradeStatus[] _emptyArray;
        public int status;
        public String upgradeReason;
        public String version;

        public interface Status {
            public static final int NOT_REQUIRED = 1;
            public static final int RECOMMENDED = 2;
            public static final int REQUIRED = 3;
            public static final int UNKNOWN = 0;
        }

        public static AppUpgradeStatus[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new AppUpgradeStatus[0];
                    }
                }
            }
            return _emptyArray;
        }

        public AppUpgradeStatus() {
            clear();
        }

        public AppUpgradeStatus clear() {
            this.status = 0;
            this.version = BuildConfig.VERSION_NAME;
            this.upgradeReason = BuildConfig.VERSION_NAME;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof AppUpgradeStatus)) {
                return false;
            }
            AppUpgradeStatus other = (AppUpgradeStatus) o;
            if (this.status != other.status) {
                return false;
            }
            if (this.version == null) {
                if (other.version != null) {
                    return false;
                }
            } else if (!this.version.equals(other.version)) {
                return false;
            }
            if (this.upgradeReason == null) {
                if (other.upgradeReason != null) {
                    return false;
                }
            } else if (!this.upgradeReason.equals(other.upgradeReason)) {
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
            int hashCode = (((((((getClass().getName().hashCode() + 527) * 31) + this.status) * 31) + (this.version == null ? 0 : this.version.hashCode())) * 31) + (this.upgradeReason == null ? 0 : this.upgradeReason.hashCode())) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.status != 0) {
                output.writeInt32(1, this.status);
            }
            if (!this.version.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(2, this.version);
            }
            if (!this.upgradeReason.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(3, this.upgradeReason);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.status != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(1, this.status);
            }
            if (!this.version.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(2, this.version);
            }
            if (this.upgradeReason.equals(BuildConfig.VERSION_NAME)) {
                return size;
            }
            return size + CodedOutputByteBufferNano.computeStringSize(3, this.upgradeReason);
        }

        public AppUpgradeStatus mergeFrom(CodedInputByteBufferNano input) throws IOException {
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
                                this.status = value;
                                break;
                            default:
                                continue;
                        }
                    case LogSource.GOOGLE_ANALYTICS /*18*/:
                        this.version = input.readString();
                        continue;
                    case LogSource.ANDROID_CAMERA /*26*/:
                        this.upgradeReason = input.readString();
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

        public static AppUpgradeStatus parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (AppUpgradeStatus) MessageNano.mergeFrom(new AppUpgradeStatus(), data);
        }

        public static AppUpgradeStatus parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new AppUpgradeStatus().mergeFrom(input);
        }
    }

    public static final class ClientInfo extends ExtendableMessageNano<ClientInfo> {
        private static volatile ClientInfo[] _emptyArray;
        public int clientId;
        public int clientIdInt;
        public String version;

        public interface ClientId {
            public static final int ANDROID_KID_SETUP_APP = 3;
            public static final int ANDROID_PARENT_APP = 1;
            public static final int ANDROID_PLAY = 4;
            public static final int IOS_PARENT_APP = 2;
            public static final int UNKNOWN_CLIENT = 0;
        }

        public static ClientInfo[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new ClientInfo[0];
                    }
                }
            }
            return _emptyArray;
        }

        public ClientInfo() {
            clear();
        }

        public ClientInfo clear() {
            this.version = BuildConfig.VERSION_NAME;
            this.clientId = 0;
            this.clientIdInt = 0;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof ClientInfo)) {
                return false;
            }
            ClientInfo other = (ClientInfo) o;
            if (this.version == null) {
                if (other.version != null) {
                    return false;
                }
            } else if (!this.version.equals(other.version)) {
                return false;
            }
            if (this.clientId != other.clientId || this.clientIdInt != other.clientIdInt) {
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
            int hashCode = (((((((getClass().getName().hashCode() + 527) * 31) + (this.version == null ? 0 : this.version.hashCode())) * 31) + this.clientId) * 31) + this.clientIdInt) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (!this.version.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(1, this.version);
            }
            if (this.clientId != 0) {
                output.writeInt32(2, this.clientId);
            }
            if (this.clientIdInt != 0) {
                output.writeInt32(3, this.clientIdInt);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (!this.version.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.version);
            }
            if (this.clientId != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(2, this.clientId);
            }
            if (this.clientIdInt != 0) {
                return size + CodedOutputByteBufferNano.computeInt32Size(3, this.clientIdInt);
            }
            return size;
        }

        public ClientInfo mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        this.version = input.readString();
                        continue;
                    case LogSource.GMS_CORE_PEOPLE /*16*/:
                        int value = input.readInt32();
                        switch (value) {
                            case Action.UNKNOWN /*0*/:
                            case TimeSelection.Type.TEMPORARY /*1*/:
                            case TimeSelection.Type.INDEFINITELY /*2*/:
                            case TimeSelection.Type.CUSTOM /*3*/:
                            case OvenFreshResult.NO_ACCOUNTS /*4*/:
                                this.clientId = value;
                                break;
                            default:
                                continue;
                        }
                    case LogSource.LB_C /*24*/:
                        this.clientIdInt = input.readInt32();
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

        public static ClientInfo parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (ClientInfo) MessageNano.mergeFrom(new ClientInfo(), data);
        }

        public static ClientInfo parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new ClientInfo().mergeFrom(input);
        }
    }
}
