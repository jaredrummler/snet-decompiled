package com.google.geo.sidekick;

import com.google.android.gms.lint.BuildConfig;
import com.google.geo.sidekick.DeviceProto.Device;
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

public interface ClientDescriptionProto {

    public static final class ClientDescription extends ExtendableMessageNano<ClientDescription> {
        private static volatile ClientDescription[] _emptyArray;
        public String clientInstanceId;
        public Device device;
        public int osType;
        public String osVersion;
        public String sidekickAppVersion;
        public long userClientId;

        public interface OSType {
            public static final int ANDROID = 1;
            public static final int ANDROID_ATHOME = 9;
            public static final int BROWSER = 2;
            public static final int CHROME = 5;
            public static final int CHROME_NOTIFICATIONS = 6;
            public static final int GLASS = 4;
            public static final int IOS = 3;
            public static final int IOS_ANDROID_WEAR = 10;
            public static final int IOS_NOTIFICATIONS = 7;
            public static final int UNSET = 0;
            public static final int WATCH = 8;
        }

        public static ClientDescription[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new ClientDescription[0];
                    }
                }
            }
            return _emptyArray;
        }

        public ClientDescription() {
            clear();
        }

        public ClientDescription clear() {
            this.osType = 0;
            this.osVersion = BuildConfig.VERSION_NAME;
            this.sidekickAppVersion = BuildConfig.VERSION_NAME;
            this.userClientId = 0;
            this.device = null;
            this.clientInstanceId = BuildConfig.VERSION_NAME;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof ClientDescription)) {
                return false;
            }
            ClientDescription other = (ClientDescription) o;
            if (this.osType != other.osType) {
                return false;
            }
            if (this.osVersion == null) {
                if (other.osVersion != null) {
                    return false;
                }
            } else if (!this.osVersion.equals(other.osVersion)) {
                return false;
            }
            if (this.sidekickAppVersion == null) {
                if (other.sidekickAppVersion != null) {
                    return false;
                }
            } else if (!this.sidekickAppVersion.equals(other.sidekickAppVersion)) {
                return false;
            }
            if (this.userClientId != other.userClientId) {
                return false;
            }
            if (this.device == null) {
                if (other.device != null) {
                    return false;
                }
            } else if (!this.device.equals(other.device)) {
                return false;
            }
            if (this.clientInstanceId == null) {
                if (other.clientInstanceId != null) {
                    return false;
                }
            } else if (!this.clientInstanceId.equals(other.clientInstanceId)) {
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
            int hashCode = (((((((((((((getClass().getName().hashCode() + 527) * 31) + this.osType) * 31) + (this.osVersion == null ? 0 : this.osVersion.hashCode())) * 31) + (this.sidekickAppVersion == null ? 0 : this.sidekickAppVersion.hashCode())) * 31) + ((int) (this.userClientId ^ (this.userClientId >>> 32)))) * 31) + (this.device == null ? 0 : this.device.hashCode())) * 31) + (this.clientInstanceId == null ? 0 : this.clientInstanceId.hashCode())) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.osType != 0) {
                output.writeInt32(1, this.osType);
            }
            if (!this.osVersion.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(2, this.osVersion);
            }
            if (!this.sidekickAppVersion.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(3, this.sidekickAppVersion);
            }
            if (this.userClientId != 0) {
                output.writeInt64(7, this.userClientId);
            }
            if (this.device != null) {
                output.writeMessage(9, this.device);
            }
            if (!this.clientInstanceId.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(13, this.clientInstanceId);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.osType != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(1, this.osType);
            }
            if (!this.osVersion.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(2, this.osVersion);
            }
            if (!this.sidekickAppVersion.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(3, this.sidekickAppVersion);
            }
            if (this.userClientId != 0) {
                size += CodedOutputByteBufferNano.computeInt64Size(7, this.userClientId);
            }
            if (this.device != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(9, this.device);
            }
            if (this.clientInstanceId.equals(BuildConfig.VERSION_NAME)) {
                return size;
            }
            return size + CodedOutputByteBufferNano.computeStringSize(13, this.clientInstanceId);
        }

        public ClientDescription mergeFrom(CodedInputByteBufferNano input) throws IOException {
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
                            case Type.CREATE_LINK /*9*/:
                            case Type.TAP_ABOUT /*10*/:
                                this.osType = value;
                                break;
                            default:
                                continue;
                        }
                    case LogSource.GOOGLE_ANALYTICS /*18*/:
                        this.osVersion = input.readString();
                        continue;
                    case LogSource.ANDROID_CAMERA /*26*/:
                        this.sidekickAppVersion = input.readString();
                        continue;
                    case LogSource.DOCS /*56*/:
                        this.userClientId = input.readInt64();
                        continue;
                    case LogSource.MOVIES /*74*/:
                        if (this.device == null) {
                            this.device = new Device();
                        }
                        input.readMessage(this.device);
                        continue;
                    case LogSource.ADSHIELD /*106*/:
                        this.clientInstanceId = input.readString();
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

        public static ClientDescription parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (ClientDescription) MessageNano.mergeFrom(new ClientDescription(), data);
        }

        public static ClientDescription parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new ClientDescription().mergeFrom(input);
        }
    }
}
