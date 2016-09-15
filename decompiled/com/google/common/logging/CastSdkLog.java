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

public interface CastSdkLog {

    public static final class AdaptiveDiscoveryResult extends ExtendableMessageNano<AdaptiveDiscoveryResult> {
        private static volatile AdaptiveDiscoveryResult[] _emptyArray;
        public int deviceConnectionErrorCode;
        public boolean deviceConnectionSucceeds;
        public String deviceModelName;
        public MDNSSubtype[] expectedSubtypes;
        public boolean isSameDevice;
        public MDNSSubtype[] receivedSubtypes;
        public int triggeredReason;

        public static AdaptiveDiscoveryResult[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new AdaptiveDiscoveryResult[0];
                    }
                }
            }
            return _emptyArray;
        }

        public AdaptiveDiscoveryResult() {
            clear();
        }

        public AdaptiveDiscoveryResult clear() {
            this.triggeredReason = 0;
            this.deviceConnectionSucceeds = false;
            this.deviceConnectionErrorCode = 0;
            this.isSameDevice = false;
            this.expectedSubtypes = MDNSSubtype.emptyArray();
            this.receivedSubtypes = MDNSSubtype.emptyArray();
            this.deviceModelName = BuildConfig.VERSION_NAME;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof AdaptiveDiscoveryResult)) {
                return false;
            }
            AdaptiveDiscoveryResult other = (AdaptiveDiscoveryResult) o;
            if (this.triggeredReason != other.triggeredReason || this.deviceConnectionSucceeds != other.deviceConnectionSucceeds || this.deviceConnectionErrorCode != other.deviceConnectionErrorCode || this.isSameDevice != other.isSameDevice || !InternalNano.equals(this.expectedSubtypes, other.expectedSubtypes) || !InternalNano.equals(this.receivedSubtypes, other.receivedSubtypes)) {
                return false;
            }
            if (this.deviceModelName == null) {
                if (other.deviceModelName != null) {
                    return false;
                }
            } else if (!this.deviceModelName.equals(other.deviceModelName)) {
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
            int i2 = 0;
            int hashCode = (((((((getClass().getName().hashCode() + 527) * 31) + this.triggeredReason) * 31) + (this.deviceConnectionSucceeds ? 1231 : 1237)) * 31) + this.deviceConnectionErrorCode) * 31;
            if (!this.isSameDevice) {
                i = 1237;
            }
            hashCode = (((((((hashCode + i) * 31) + InternalNano.hashCode(this.expectedSubtypes)) * 31) + InternalNano.hashCode(this.receivedSubtypes)) * 31) + (this.deviceModelName == null ? 0 : this.deviceModelName.hashCode())) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i2 = this.unknownFieldData.hashCode();
            }
            return hashCode + i2;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.triggeredReason != 0) {
                output.writeInt32(1, this.triggeredReason);
            }
            if (this.deviceConnectionSucceeds) {
                output.writeBool(2, this.deviceConnectionSucceeds);
            }
            if (this.deviceConnectionErrorCode != 0) {
                output.writeInt32(3, this.deviceConnectionErrorCode);
            }
            if (this.isSameDevice) {
                output.writeBool(4, this.isSameDevice);
            }
            if (this.expectedSubtypes != null && this.expectedSubtypes.length > 0) {
                for (MDNSSubtype element : this.expectedSubtypes) {
                    if (element != null) {
                        output.writeMessage(7, element);
                    }
                }
            }
            if (this.receivedSubtypes != null && this.receivedSubtypes.length > 0) {
                for (MDNSSubtype element2 : this.receivedSubtypes) {
                    if (element2 != null) {
                        output.writeMessage(8, element2);
                    }
                }
            }
            if (!this.deviceModelName.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(9, this.deviceModelName);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.triggeredReason != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(1, this.triggeredReason);
            }
            if (this.deviceConnectionSucceeds) {
                size += CodedOutputByteBufferNano.computeBoolSize(2, this.deviceConnectionSucceeds);
            }
            if (this.deviceConnectionErrorCode != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(3, this.deviceConnectionErrorCode);
            }
            if (this.isSameDevice) {
                size += CodedOutputByteBufferNano.computeBoolSize(4, this.isSameDevice);
            }
            if (this.expectedSubtypes != null && this.expectedSubtypes.length > 0) {
                for (MDNSSubtype element : this.expectedSubtypes) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(7, element);
                    }
                }
            }
            if (this.receivedSubtypes != null && this.receivedSubtypes.length > 0) {
                for (MDNSSubtype element2 : this.receivedSubtypes) {
                    if (element2 != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(8, element2);
                    }
                }
            }
            if (this.deviceModelName.equals(BuildConfig.VERSION_NAME)) {
                return size;
            }
            return size + CodedOutputByteBufferNano.computeStringSize(9, this.deviceModelName);
        }

        public AdaptiveDiscoveryResult mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                int value;
                int arrayLength;
                int i;
                MDNSSubtype[] newArray;
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_GET_LINK /*8*/:
                        value = input.readInt32();
                        switch (value) {
                            case Action.UNKNOWN /*0*/:
                            case TimeSelection.Type.TEMPORARY /*1*/:
                            case TimeSelection.Type.INDEFINITELY /*2*/:
                            case TimeSelection.Type.CUSTOM /*3*/:
                                this.triggeredReason = value;
                                break;
                            default:
                                continue;
                        }
                    case LogSource.GMS_CORE_PEOPLE /*16*/:
                        this.deviceConnectionSucceeds = input.readBool();
                        continue;
                    case LogSource.LB_C /*24*/:
                        value = input.readInt32();
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
                            case Type.TAP_LEARN_MORE /*11*/:
                            case Type.SWITCH_ACCOUNT /*12*/:
                            case Type.DISPLAY_ERROR /*13*/:
                            case Type.LAUNCH_SETTINGS /*14*/:
                                this.deviceConnectionErrorCode = value;
                                break;
                            default:
                                continue;
                        }
                    case LogSource.SOCIAL /*32*/:
                        this.isSameDevice = input.readBool();
                        continue;
                    case LogSource.SLIDES /*58*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 58);
                        if (this.expectedSubtypes == null) {
                            i = 0;
                        } else {
                            i = this.expectedSubtypes.length;
                        }
                        newArray = new MDNSSubtype[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.expectedSubtypes, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new MDNSSubtype();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i++;
                        }
                        newArray[i] = new MDNSSubtype();
                        input.readMessage(newArray[i]);
                        this.expectedSubtypes = newArray;
                        continue;
                    case LogSource.WIFI_ASSISTANT /*66*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 66);
                        if (this.receivedSubtypes == null) {
                            i = 0;
                        } else {
                            i = this.receivedSubtypes.length;
                        }
                        newArray = new MDNSSubtype[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.receivedSubtypes, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new MDNSSubtype();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i++;
                        }
                        newArray[i] = new MDNSSubtype();
                        input.readMessage(newArray[i]);
                        this.receivedSubtypes = newArray;
                        continue;
                    case LogSource.MOVIES /*74*/:
                        this.deviceModelName = input.readString();
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

        public static AdaptiveDiscoveryResult parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (AdaptiveDiscoveryResult) MessageNano.mergeFrom(new AdaptiveDiscoveryResult(), data);
        }

        public static AdaptiveDiscoveryResult parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new AdaptiveDiscoveryResult().mergeFrom(input);
        }
    }

    public interface AdaptiveDiscoveryTriggeredReason {
        public static final int NO_MDNS_RESPONSE = 1;
        public static final int NO_MDNS_SUBTYPE_RESPONSE = 2;
        public static final int SOME_MDNS_SUBTYPE_RESPONSES_RECEIVED = 3;
        public static final int TRIGGER_REASON_NONE = 0;
    }

    public static final class CastNearbyAudioTokenResult extends ExtendableMessageNano<CastNearbyAudioTokenResult> {
        private static volatile CastNearbyAudioTokenResult[] _emptyArray;
        public int result;

        public interface Result {
            public static final int DECODE_SUCCESS = 1;
            public static final int REQUEST_TIMED_OUT = 2;
            public static final int RESULT_UNKNOWN = 0;
            public static final int USER_CANCELLED = 3;
            public static final int USER_INTERRUPTED_AUDIO_PARING = 4;
        }

        public static CastNearbyAudioTokenResult[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new CastNearbyAudioTokenResult[0];
                    }
                }
            }
            return _emptyArray;
        }

        public CastNearbyAudioTokenResult() {
            clear();
        }

        public CastNearbyAudioTokenResult clear() {
            this.result = 0;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof CastNearbyAudioTokenResult)) {
                return false;
            }
            CastNearbyAudioTokenResult other = (CastNearbyAudioTokenResult) o;
            if (this.result != other.result) {
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
            int hashCode = (((getClass().getName().hashCode() + 527) * 31) + this.result) * 31;
            int hashCode2 = (this.unknownFieldData == null || this.unknownFieldData.isEmpty()) ? 0 : this.unknownFieldData.hashCode();
            return hashCode + hashCode2;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.result != 0) {
                output.writeInt32(1, this.result);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.result != 0) {
                return size + CodedOutputByteBufferNano.computeInt32Size(1, this.result);
            }
            return size;
        }

        public CastNearbyAudioTokenResult mergeFrom(CodedInputByteBufferNano input) throws IOException {
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
                                this.result = value;
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

        public static CastNearbyAudioTokenResult parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (CastNearbyAudioTokenResult) MessageNano.mergeFrom(new CastNearbyAudioTokenResult(), data);
        }

        public static CastNearbyAudioTokenResult parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new CastNearbyAudioTokenResult().mergeFrom(input);
        }
    }

    public static final class CastSdkEvent extends ExtendableMessageNano<CastSdkEvent> {
        private static volatile CastSdkEvent[] _emptyArray;
        public AdaptiveDiscoveryResult adaptiveDiscoveryResult;
        public CastNearbyAudioTokenResult castNearbyAudioTokenResult;
        public DeviceController deviceController;
        public DeviceFilter deviceFilter;
        public DiscoveredCastDevice discoveredDevice;
        public MdnsResponseParseError mdnsResponseParseError;
        public MDNSSubtypeDiscoveryResult mdnsSubtypeDiscoveryResult;
        public long receiverDeviceId;
        public String senderId;
        public long sessionId;
        public int sessionIdType;

        public interface EventAction {
            public static final int ADAPTIVE_DISCOVERY_RESULT = 4;
            public static final int APPLICATION_CONNECTION_FAILED = 41;
            public static final int APPLICATION_CONNECTION_SUCCESS = 42;
            public static final int APPLICATION_DISCONNECTED = 43;
            public static final int CAST_NEARBY_PROXIMITY_INFO_REQUEST = 50;
            public static final int CAST_NEARBY_PROXIMITY_INFO_RESPONSE = 51;
            public static final int CAST_NEARBY_START_AUDIO_TOKEN_PLAYBACK = 52;
            public static final int CAST_NEARBY_STOP_AUDIO_TOKEN_PLAYBACK = 53;
            public static final int DEVICE_CONTROLLER_CONNECTION_FAILED = 31;
            public static final int DEVICE_CONTROLLER_CONNECTION_SUCCESS = 32;
            public static final int DEVICE_CONTROLLER_CONNECT_TO_DEVICE = 30;
            public static final int DEVICE_FILTER_CONNECTION_FAILED = 11;
            public static final int DEVICE_FILTER_CONNECTION_SUCCESS = 12;
            public static final int DEVICE_FILTER_CONNECT_TO_DEVICE = 10;
            public static final int DEVICE_FILTER_ERROR = 19;
            public static final int DEVICE_FILTER_FINISHED = 18;
            public static final int DEVICE_FILTER_IGNORING_RECEIVED_MESSAGE = 17;
            public static final int DEVICE_FILTER_RECEIVED_APP_AVAILABILITY_MESSAGE = 14;
            public static final int DEVICE_FILTER_RECEIVED_STATUS_MESSAGE = 16;
            public static final int DEVICE_FILTER_SENT_APP_AVAILABILITY_MESSAGE = 13;
            public static final int DEVICE_FILTER_SENT_STATUS_MESSAGE = 15;
            public static final int DISCOVERED_CAST_DEVICE = 1;
            public static final int EVENT_ACTION_UNKNOWN = 0;
            public static final int LAUNCH_APPLICATION = 40;
            public static final int MDNS_RESPONSE_PARSE_ERROR = 2;
            public static final int MDNS_SUBTYPE_DISCOVERY_RESULT = 3;
        }

        public interface SessionIdType {
            public static final int DEVICE_CONTROLLER_APPLICATION_CONNECTION = 2;
            public static final int DEVICE_CONTROLLER_CONNECTION = 1;
            public static final int DEVICE_FILTER = 3;
            public static final int TYPE_UNKNOWN = 0;
        }

        public static CastSdkEvent[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new CastSdkEvent[0];
                    }
                }
            }
            return _emptyArray;
        }

        public CastSdkEvent() {
            clear();
        }

        public CastSdkEvent clear() {
            this.receiverDeviceId = 0;
            this.sessionId = 0;
            this.sessionIdType = 0;
            this.discoveredDevice = null;
            this.mdnsResponseParseError = null;
            this.deviceFilter = null;
            this.deviceController = null;
            this.castNearbyAudioTokenResult = null;
            this.mdnsSubtypeDiscoveryResult = null;
            this.adaptiveDiscoveryResult = null;
            this.senderId = BuildConfig.VERSION_NAME;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof CastSdkEvent)) {
                return false;
            }
            CastSdkEvent other = (CastSdkEvent) o;
            if (this.receiverDeviceId != other.receiverDeviceId || this.sessionId != other.sessionId || this.sessionIdType != other.sessionIdType) {
                return false;
            }
            if (this.discoveredDevice == null) {
                if (other.discoveredDevice != null) {
                    return false;
                }
            } else if (!this.discoveredDevice.equals(other.discoveredDevice)) {
                return false;
            }
            if (this.mdnsResponseParseError == null) {
                if (other.mdnsResponseParseError != null) {
                    return false;
                }
            } else if (!this.mdnsResponseParseError.equals(other.mdnsResponseParseError)) {
                return false;
            }
            if (this.deviceFilter == null) {
                if (other.deviceFilter != null) {
                    return false;
                }
            } else if (!this.deviceFilter.equals(other.deviceFilter)) {
                return false;
            }
            if (this.deviceController == null) {
                if (other.deviceController != null) {
                    return false;
                }
            } else if (!this.deviceController.equals(other.deviceController)) {
                return false;
            }
            if (this.castNearbyAudioTokenResult == null) {
                if (other.castNearbyAudioTokenResult != null) {
                    return false;
                }
            } else if (!this.castNearbyAudioTokenResult.equals(other.castNearbyAudioTokenResult)) {
                return false;
            }
            if (this.mdnsSubtypeDiscoveryResult == null) {
                if (other.mdnsSubtypeDiscoveryResult != null) {
                    return false;
                }
            } else if (!this.mdnsSubtypeDiscoveryResult.equals(other.mdnsSubtypeDiscoveryResult)) {
                return false;
            }
            if (this.adaptiveDiscoveryResult == null) {
                if (other.adaptiveDiscoveryResult != null) {
                    return false;
                }
            } else if (!this.adaptiveDiscoveryResult.equals(other.adaptiveDiscoveryResult)) {
                return false;
            }
            if (this.senderId == null) {
                if (other.senderId != null) {
                    return false;
                }
            } else if (!this.senderId.equals(other.senderId)) {
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
            int hashCode = (((((((((((((((((((((((getClass().getName().hashCode() + 527) * 31) + ((int) (this.receiverDeviceId ^ (this.receiverDeviceId >>> 32)))) * 31) + ((int) (this.sessionId ^ (this.sessionId >>> 32)))) * 31) + this.sessionIdType) * 31) + (this.discoveredDevice == null ? 0 : this.discoveredDevice.hashCode())) * 31) + (this.mdnsResponseParseError == null ? 0 : this.mdnsResponseParseError.hashCode())) * 31) + (this.deviceFilter == null ? 0 : this.deviceFilter.hashCode())) * 31) + (this.deviceController == null ? 0 : this.deviceController.hashCode())) * 31) + (this.castNearbyAudioTokenResult == null ? 0 : this.castNearbyAudioTokenResult.hashCode())) * 31) + (this.mdnsSubtypeDiscoveryResult == null ? 0 : this.mdnsSubtypeDiscoveryResult.hashCode())) * 31) + (this.adaptiveDiscoveryResult == null ? 0 : this.adaptiveDiscoveryResult.hashCode())) * 31) + (this.senderId == null ? 0 : this.senderId.hashCode())) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.receiverDeviceId != 0) {
                output.writeInt64(1, this.receiverDeviceId);
            }
            if (this.sessionId != 0) {
                output.writeInt64(2, this.sessionId);
            }
            if (this.sessionIdType != 0) {
                output.writeInt32(3, this.sessionIdType);
            }
            if (this.discoveredDevice != null) {
                output.writeMessage(4, this.discoveredDevice);
            }
            if (this.mdnsResponseParseError != null) {
                output.writeMessage(5, this.mdnsResponseParseError);
            }
            if (this.deviceFilter != null) {
                output.writeMessage(6, this.deviceFilter);
            }
            if (this.deviceController != null) {
                output.writeMessage(7, this.deviceController);
            }
            if (this.castNearbyAudioTokenResult != null) {
                output.writeMessage(8, this.castNearbyAudioTokenResult);
            }
            if (!this.senderId.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(9, this.senderId);
            }
            if (this.mdnsSubtypeDiscoveryResult != null) {
                output.writeMessage(10, this.mdnsSubtypeDiscoveryResult);
            }
            if (this.adaptiveDiscoveryResult != null) {
                output.writeMessage(11, this.adaptiveDiscoveryResult);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.receiverDeviceId != 0) {
                size += CodedOutputByteBufferNano.computeInt64Size(1, this.receiverDeviceId);
            }
            if (this.sessionId != 0) {
                size += CodedOutputByteBufferNano.computeInt64Size(2, this.sessionId);
            }
            if (this.sessionIdType != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(3, this.sessionIdType);
            }
            if (this.discoveredDevice != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(4, this.discoveredDevice);
            }
            if (this.mdnsResponseParseError != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(5, this.mdnsResponseParseError);
            }
            if (this.deviceFilter != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(6, this.deviceFilter);
            }
            if (this.deviceController != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(7, this.deviceController);
            }
            if (this.castNearbyAudioTokenResult != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(8, this.castNearbyAudioTokenResult);
            }
            if (!this.senderId.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(9, this.senderId);
            }
            if (this.mdnsSubtypeDiscoveryResult != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(10, this.mdnsSubtypeDiscoveryResult);
            }
            if (this.adaptiveDiscoveryResult != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(11, this.adaptiveDiscoveryResult);
            }
            return size;
        }

        public CastSdkEvent mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_GET_LINK /*8*/:
                        this.receiverDeviceId = input.readInt64();
                        continue;
                    case LogSource.GMS_CORE_PEOPLE /*16*/:
                        this.sessionId = input.readInt64();
                        continue;
                    case LogSource.LB_C /*24*/:
                        int value = input.readInt32();
                        switch (value) {
                            case Action.UNKNOWN /*0*/:
                            case TimeSelection.Type.TEMPORARY /*1*/:
                            case TimeSelection.Type.INDEFINITELY /*2*/:
                            case TimeSelection.Type.CUSTOM /*3*/:
                                this.sessionIdType = value;
                                break;
                            default:
                                continue;
                        }
                    case LogSource.NOVA /*34*/:
                        if (this.discoveredDevice == null) {
                            this.discoveredDevice = new DiscoveredCastDevice();
                        }
                        input.readMessage(this.discoveredDevice);
                        continue;
                    case LogSource.PHOTOS /*42*/:
                        if (this.mdnsResponseParseError == null) {
                            this.mdnsResponseParseError = new MdnsResponseParseError();
                        }
                        input.readMessage(this.mdnsResponseParseError);
                        continue;
                    case LogSource.HANGOUT_LOG_REQUEST /*50*/:
                        if (this.deviceFilter == null) {
                            this.deviceFilter = new DeviceFilter();
                        }
                        input.readMessage(this.deviceFilter);
                        continue;
                    case LogSource.SLIDES /*58*/:
                        if (this.deviceController == null) {
                            this.deviceController = new DeviceController();
                        }
                        input.readMessage(this.deviceController);
                        continue;
                    case LogSource.WIFI_ASSISTANT /*66*/:
                        if (this.castNearbyAudioTokenResult == null) {
                            this.castNearbyAudioTokenResult = new CastNearbyAudioTokenResult();
                        }
                        input.readMessage(this.castNearbyAudioTokenResult);
                        continue;
                    case LogSource.MOVIES /*74*/:
                        this.senderId = input.readString();
                        continue;
                    case LogSource.GOOGLE_EXPRESS /*82*/:
                        if (this.mdnsSubtypeDiscoveryResult == null) {
                            this.mdnsSubtypeDiscoveryResult = new MDNSSubtypeDiscoveryResult();
                        }
                        input.readMessage(this.mdnsSubtypeDiscoveryResult);
                        continue;
                    case LogSource.TRON_COUNTERS /*90*/:
                        if (this.adaptiveDiscoveryResult == null) {
                            this.adaptiveDiscoveryResult = new AdaptiveDiscoveryResult();
                        }
                        input.readMessage(this.adaptiveDiscoveryResult);
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

        public static CastSdkEvent parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (CastSdkEvent) MessageNano.mergeFrom(new CastSdkEvent(), data);
        }

        public static CastSdkEvent parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new CastSdkEvent().mergeFrom(input);
        }
    }

    public interface DeviceConnectionErrorCode {
        public static final int ERROR_CANCELED = 13;
        public static final int ERROR_CAST_NEARBY_INVALID_REQUEST = 14;
        public static final int ERROR_DEVICE_AUTH = 6;
        public static final int ERROR_DEVICE_AUTH_CLIENT_AUTH_CERT_MALFORMED = 8;
        public static final int ERROR_DEVICE_AUTH_CLIENT_AUTH_CERT_NOT_TRUSTED = 10;
        public static final int ERROR_DEVICE_AUTH_CLIENT_AUTH_CERT_NOT_X509 = 9;
        public static final int ERROR_DEVICE_AUTH_ERROR_RECEIVED = 7;
        public static final int ERROR_DEVICE_AUTH_RESPONSE_MALFORMED = 12;
        public static final int ERROR_DEVICE_AUTH_SSL_CERT_NOT_TRUSTED = 11;
        public static final int ERROR_IO = 2;
        public static final int ERROR_PEER_DISCONNECTED = 1;
        public static final int ERROR_RELAY = 5;
        public static final int ERROR_SSL = 3;
        public static final int ERROR_TIMEOUT = 4;
        public static final int ERROR_UNKNOWN = 0;
    }

    public static final class DeviceController extends ExtendableMessageNano<DeviceController> {
        private static volatile DeviceController[] _emptyArray;
        public int applicationConnectionFailedReason;
        public int connectionFailureReason;
        public int disconnectReason;
        public boolean isMrpController;
        public LaunchApplicationRequest launchApplicationRequest;

        public interface ApplicationConnectionFailedReason {
            public static final int APPLICATION_CONNECTION_FAILED_REASON_UNKNOWN = 0;
            public static final int APPLICATION_NOT_FOUND = 1;
            public static final int APPLICATION_NOT_RUNNING = 2;
        }

        public interface DeviceDisconnectReason {
            public static final int DEVICE_DISCONNECT_REASON_UNKNOWN = 0;
            public static final int STATUS_ERROR_HEARTBEAT_TIMEOUT = 2;
            public static final int STATUS_ERROR_NETWORK = 1;
            public static final int STATUS_EXPLICIT_DISCONNECT = 3;
            public static final int STATUS_IMPLICIT_DISCONNECT = 4;
        }

        public static DeviceController[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new DeviceController[0];
                    }
                }
            }
            return _emptyArray;
        }

        public DeviceController() {
            clear();
        }

        public DeviceController clear() {
            this.isMrpController = false;
            this.connectionFailureReason = 0;
            this.disconnectReason = 0;
            this.applicationConnectionFailedReason = 0;
            this.launchApplicationRequest = null;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof DeviceController)) {
                return false;
            }
            DeviceController other = (DeviceController) o;
            if (this.isMrpController != other.isMrpController || this.connectionFailureReason != other.connectionFailureReason || this.disconnectReason != other.disconnectReason || this.applicationConnectionFailedReason != other.applicationConnectionFailedReason) {
                return false;
            }
            if (this.launchApplicationRequest == null) {
                if (other.launchApplicationRequest != null) {
                    return false;
                }
            } else if (!this.launchApplicationRequest.equals(other.launchApplicationRequest)) {
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
            int hashCode = (((((((((((getClass().getName().hashCode() + 527) * 31) + (this.isMrpController ? 1231 : 1237)) * 31) + this.connectionFailureReason) * 31) + this.disconnectReason) * 31) + this.applicationConnectionFailedReason) * 31) + (this.launchApplicationRequest == null ? 0 : this.launchApplicationRequest.hashCode())) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.isMrpController) {
                output.writeBool(1, this.isMrpController);
            }
            if (this.connectionFailureReason != 0) {
                output.writeInt32(2, this.connectionFailureReason);
            }
            if (this.disconnectReason != 0) {
                output.writeInt32(3, this.disconnectReason);
            }
            if (this.applicationConnectionFailedReason != 0) {
                output.writeInt32(4, this.applicationConnectionFailedReason);
            }
            if (this.launchApplicationRequest != null) {
                output.writeMessage(5, this.launchApplicationRequest);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.isMrpController) {
                size += CodedOutputByteBufferNano.computeBoolSize(1, this.isMrpController);
            }
            if (this.connectionFailureReason != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(2, this.connectionFailureReason);
            }
            if (this.disconnectReason != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(3, this.disconnectReason);
            }
            if (this.applicationConnectionFailedReason != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(4, this.applicationConnectionFailedReason);
            }
            if (this.launchApplicationRequest != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(5, this.launchApplicationRequest);
            }
            return size;
        }

        public DeviceController mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                int value;
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_GET_LINK /*8*/:
                        this.isMrpController = input.readBool();
                        continue;
                    case LogSource.GMS_CORE_PEOPLE /*16*/:
                        value = input.readInt32();
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
                            case Type.TAP_LEARN_MORE /*11*/:
                            case Type.SWITCH_ACCOUNT /*12*/:
                            case Type.DISPLAY_ERROR /*13*/:
                            case Type.LAUNCH_SETTINGS /*14*/:
                                this.connectionFailureReason = value;
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
                            case TimeSelection.Type.CUSTOM /*3*/:
                            case OvenFreshResult.NO_ACCOUNTS /*4*/:
                                this.disconnectReason = value;
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
                                this.applicationConnectionFailedReason = value;
                                break;
                            default:
                                continue;
                        }
                    case LogSource.PHOTOS /*42*/:
                        if (this.launchApplicationRequest == null) {
                            this.launchApplicationRequest = new LaunchApplicationRequest();
                        }
                        input.readMessage(this.launchApplicationRequest);
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

        public static DeviceController parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (DeviceController) MessageNano.mergeFrom(new DeviceController(), data);
        }

        public static DeviceController parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new DeviceController().mergeFrom(input);
        }
    }

    public static final class DeviceFilter extends ExtendableMessageNano<DeviceFilter> {
        private static volatile DeviceFilter[] _emptyArray;
        public int[] appIdsAccepted;
        public int[] appIdsRejected;
        public int deviceConnectionErrorCode;
        public int filterThreadId;
        public String[] namespacesAccepted;
        public String[] namespacesRejected;
        public int protocolType;

        public static DeviceFilter[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new DeviceFilter[0];
                    }
                }
            }
            return _emptyArray;
        }

        public DeviceFilter() {
            clear();
        }

        public DeviceFilter clear() {
            this.filterThreadId = 0;
            this.protocolType = 0;
            this.appIdsAccepted = WireFormatNano.EMPTY_INT_ARRAY;
            this.appIdsRejected = WireFormatNano.EMPTY_INT_ARRAY;
            this.namespacesAccepted = WireFormatNano.EMPTY_STRING_ARRAY;
            this.namespacesRejected = WireFormatNano.EMPTY_STRING_ARRAY;
            this.deviceConnectionErrorCode = 0;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof DeviceFilter)) {
                return false;
            }
            DeviceFilter other = (DeviceFilter) o;
            if (this.filterThreadId != other.filterThreadId || this.protocolType != other.protocolType || !InternalNano.equals(this.appIdsAccepted, other.appIdsAccepted) || !InternalNano.equals(this.appIdsRejected, other.appIdsRejected) || !InternalNano.equals(this.namespacesAccepted, other.namespacesAccepted) || !InternalNano.equals(this.namespacesRejected, other.namespacesRejected) || this.deviceConnectionErrorCode != other.deviceConnectionErrorCode) {
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
            int hashCode = (((((((((((((((getClass().getName().hashCode() + 527) * 31) + this.filterThreadId) * 31) + this.protocolType) * 31) + InternalNano.hashCode(this.appIdsAccepted)) * 31) + InternalNano.hashCode(this.appIdsRejected)) * 31) + InternalNano.hashCode(this.namespacesAccepted)) * 31) + InternalNano.hashCode(this.namespacesRejected)) * 31) + this.deviceConnectionErrorCode) * 31;
            int hashCode2 = (this.unknownFieldData == null || this.unknownFieldData.isEmpty()) ? 0 : this.unknownFieldData.hashCode();
            return hashCode + hashCode2;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.filterThreadId != 0) {
                output.writeInt32(1, this.filterThreadId);
            }
            if (this.protocolType != 0) {
                output.writeInt32(2, this.protocolType);
            }
            if (this.appIdsAccepted != null && this.appIdsAccepted.length > 0) {
                for (int writeInt32 : this.appIdsAccepted) {
                    output.writeInt32(3, writeInt32);
                }
            }
            if (this.appIdsRejected != null && this.appIdsRejected.length > 0) {
                for (int writeInt322 : this.appIdsRejected) {
                    output.writeInt32(4, writeInt322);
                }
            }
            if (this.namespacesAccepted != null && this.namespacesAccepted.length > 0) {
                for (String element : this.namespacesAccepted) {
                    if (element != null) {
                        output.writeString(5, element);
                    }
                }
            }
            if (this.namespacesRejected != null && this.namespacesRejected.length > 0) {
                for (String element2 : this.namespacesRejected) {
                    if (element2 != null) {
                        output.writeString(6, element2);
                    }
                }
            }
            if (this.deviceConnectionErrorCode != 0) {
                output.writeInt32(7, this.deviceConnectionErrorCode);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int dataSize;
            int dataCount;
            int size = super.computeSerializedSize();
            if (this.filterThreadId != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(1, this.filterThreadId);
            }
            if (this.protocolType != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(2, this.protocolType);
            }
            if (this.appIdsAccepted != null && this.appIdsAccepted.length > 0) {
                dataSize = 0;
                for (int element : this.appIdsAccepted) {
                    dataSize += CodedOutputByteBufferNano.computeInt32SizeNoTag(element);
                }
                size = (size + dataSize) + (this.appIdsAccepted.length * 1);
            }
            if (this.appIdsRejected != null && this.appIdsRejected.length > 0) {
                dataSize = 0;
                for (int element2 : this.appIdsRejected) {
                    dataSize += CodedOutputByteBufferNano.computeInt32SizeNoTag(element2);
                }
                size = (size + dataSize) + (this.appIdsRejected.length * 1);
            }
            if (this.namespacesAccepted != null && this.namespacesAccepted.length > 0) {
                dataCount = 0;
                dataSize = 0;
                for (String element3 : this.namespacesAccepted) {
                    if (element3 != null) {
                        dataCount++;
                        dataSize += CodedOutputByteBufferNano.computeStringSizeNoTag(element3);
                    }
                }
                size = (size + dataSize) + (dataCount * 1);
            }
            if (this.namespacesRejected != null && this.namespacesRejected.length > 0) {
                dataCount = 0;
                dataSize = 0;
                for (String element32 : this.namespacesRejected) {
                    if (element32 != null) {
                        dataCount++;
                        dataSize += CodedOutputByteBufferNano.computeStringSizeNoTag(element32);
                    }
                }
                size = (size + dataSize) + (dataCount * 1);
            }
            if (this.deviceConnectionErrorCode != 0) {
                return size + CodedOutputByteBufferNano.computeInt32Size(7, this.deviceConnectionErrorCode);
            }
            return size;
        }

        public DeviceFilter mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                int value;
                int arrayLength;
                int i;
                int[] newArray;
                int limit;
                int startPos;
                String[] newArray2;
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_GET_LINK /*8*/:
                        this.filterThreadId = input.readInt32();
                        continue;
                    case LogSource.GMS_CORE_PEOPLE /*16*/:
                        value = input.readInt32();
                        switch (value) {
                            case Action.UNKNOWN /*0*/:
                            case TimeSelection.Type.TEMPORARY /*1*/:
                            case TimeSelection.Type.INDEFINITELY /*2*/:
                                this.protocolType = value;
                                break;
                            default:
                                continue;
                        }
                    case LogSource.LB_C /*24*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 24);
                        i = this.appIdsAccepted == null ? 0 : this.appIdsAccepted.length;
                        newArray = new int[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.appIdsAccepted, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = input.readInt32();
                            input.readTag();
                            i++;
                        }
                        newArray[i] = input.readInt32();
                        this.appIdsAccepted = newArray;
                        continue;
                    case LogSource.ANDROID_CAMERA /*26*/:
                        limit = input.pushLimit(input.readRawVarint32());
                        arrayLength = 0;
                        startPos = input.getPosition();
                        while (input.getBytesUntilLimit() > 0) {
                            input.readInt32();
                            arrayLength++;
                        }
                        input.rewindToPosition(startPos);
                        i = this.appIdsAccepted == null ? 0 : this.appIdsAccepted.length;
                        newArray = new int[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.appIdsAccepted, 0, newArray, 0, i);
                        }
                        while (i < newArray.length) {
                            newArray[i] = input.readInt32();
                            i++;
                        }
                        this.appIdsAccepted = newArray;
                        input.popLimit(limit);
                        continue;
                    case LogSource.SOCIAL /*32*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 32);
                        i = this.appIdsRejected == null ? 0 : this.appIdsRejected.length;
                        newArray = new int[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.appIdsRejected, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = input.readInt32();
                            input.readTag();
                            i++;
                        }
                        newArray[i] = input.readInt32();
                        this.appIdsRejected = newArray;
                        continue;
                    case LogSource.NOVA /*34*/:
                        limit = input.pushLimit(input.readRawVarint32());
                        arrayLength = 0;
                        startPos = input.getPosition();
                        while (input.getBytesUntilLimit() > 0) {
                            input.readInt32();
                            arrayLength++;
                        }
                        input.rewindToPosition(startPos);
                        i = this.appIdsRejected == null ? 0 : this.appIdsRejected.length;
                        newArray = new int[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.appIdsRejected, 0, newArray, 0, i);
                        }
                        while (i < newArray.length) {
                            newArray[i] = input.readInt32();
                            i++;
                        }
                        this.appIdsRejected = newArray;
                        input.popLimit(limit);
                        continue;
                    case LogSource.PHOTOS /*42*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 42);
                        i = this.namespacesAccepted == null ? 0 : this.namespacesAccepted.length;
                        newArray2 = new String[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.namespacesAccepted, 0, newArray2, 0, i);
                        }
                        while (i < newArray2.length - 1) {
                            newArray2[i] = input.readString();
                            input.readTag();
                            i++;
                        }
                        newArray2[i] = input.readString();
                        this.namespacesAccepted = newArray2;
                        continue;
                    case LogSource.HANGOUT_LOG_REQUEST /*50*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 50);
                        i = this.namespacesRejected == null ? 0 : this.namespacesRejected.length;
                        newArray2 = new String[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.namespacesRejected, 0, newArray2, 0, i);
                        }
                        while (i < newArray2.length - 1) {
                            newArray2[i] = input.readString();
                            input.readTag();
                            i++;
                        }
                        newArray2[i] = input.readString();
                        this.namespacesRejected = newArray2;
                        continue;
                    case LogSource.DOCS /*56*/:
                        value = input.readInt32();
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
                            case Type.TAP_LEARN_MORE /*11*/:
                            case Type.SWITCH_ACCOUNT /*12*/:
                            case Type.DISPLAY_ERROR /*13*/:
                            case Type.LAUNCH_SETTINGS /*14*/:
                                this.deviceConnectionErrorCode = value;
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

        public static DeviceFilter parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (DeviceFilter) MessageNano.mergeFrom(new DeviceFilter(), data);
        }

        public static DeviceFilter parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new DeviceFilter().mergeFrom(input);
        }
    }

    public static final class DiscoveredCastDevice extends ExtendableMessageNano<DiscoveredCastDevice> {
        private static volatile DiscoveredCastDevice[] _emptyArray;
        public int protocolType;

        public static DiscoveredCastDevice[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new DiscoveredCastDevice[0];
                    }
                }
            }
            return _emptyArray;
        }

        public DiscoveredCastDevice() {
            clear();
        }

        public DiscoveredCastDevice clear() {
            this.protocolType = 0;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof DiscoveredCastDevice)) {
                return false;
            }
            DiscoveredCastDevice other = (DiscoveredCastDevice) o;
            if (this.protocolType != other.protocolType) {
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
            int hashCode = (((getClass().getName().hashCode() + 527) * 31) + this.protocolType) * 31;
            int hashCode2 = (this.unknownFieldData == null || this.unknownFieldData.isEmpty()) ? 0 : this.unknownFieldData.hashCode();
            return hashCode + hashCode2;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.protocolType != 0) {
                output.writeInt32(1, this.protocolType);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.protocolType != 0) {
                return size + CodedOutputByteBufferNano.computeInt32Size(1, this.protocolType);
            }
            return size;
        }

        public DiscoveredCastDevice mergeFrom(CodedInputByteBufferNano input) throws IOException {
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
                                this.protocolType = value;
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

        public static DiscoveredCastDevice parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (DiscoveredCastDevice) MessageNano.mergeFrom(new DiscoveredCastDevice(), data);
        }

        public static DiscoveredCastDevice parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new DiscoveredCastDevice().mergeFrom(input);
        }
    }

    public interface DiscoveryProtocolType {
        public static final int CAST_NEARBY = 2;
        public static final int MDNS = 1;
        public static final int TYPE_UNKNOWN = 0;
    }

    public static final class LaunchApplicationRequest extends ExtendableMessageNano<LaunchApplicationRequest> {
        private static volatile LaunchApplicationRequest[] _emptyArray;
        public int applicationId;
        public boolean hasSessionId;
        public int launchType;
        public boolean relaunchIfRunning;

        public interface LaunchType {
            public static final int JOIN = 1;
            public static final int LAUNCH = 2;
            public static final int LAUNCH_UNKNOWN = 0;
        }

        public static LaunchApplicationRequest[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new LaunchApplicationRequest[0];
                    }
                }
            }
            return _emptyArray;
        }

        public LaunchApplicationRequest() {
            clear();
        }

        public LaunchApplicationRequest clear() {
            this.applicationId = 0;
            this.launchType = 0;
            this.relaunchIfRunning = false;
            this.hasSessionId = false;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof LaunchApplicationRequest)) {
                return false;
            }
            LaunchApplicationRequest other = (LaunchApplicationRequest) o;
            if (this.applicationId != other.applicationId || this.launchType != other.launchType || this.relaunchIfRunning != other.relaunchIfRunning || this.hasSessionId != other.hasSessionId) {
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
            int hashCode = (((((((getClass().getName().hashCode() + 527) * 31) + this.applicationId) * 31) + this.launchType) * 31) + (this.relaunchIfRunning ? 1231 : 1237)) * 31;
            if (!this.hasSessionId) {
                i = 1237;
            }
            i = (hashCode + i) * 31;
            hashCode = (this.unknownFieldData == null || this.unknownFieldData.isEmpty()) ? 0 : this.unknownFieldData.hashCode();
            return i + hashCode;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.applicationId != 0) {
                output.writeInt32(1, this.applicationId);
            }
            if (this.launchType != 0) {
                output.writeInt32(2, this.launchType);
            }
            if (this.relaunchIfRunning) {
                output.writeBool(3, this.relaunchIfRunning);
            }
            if (this.hasSessionId) {
                output.writeBool(4, this.hasSessionId);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.applicationId != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(1, this.applicationId);
            }
            if (this.launchType != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(2, this.launchType);
            }
            if (this.relaunchIfRunning) {
                size += CodedOutputByteBufferNano.computeBoolSize(3, this.relaunchIfRunning);
            }
            if (this.hasSessionId) {
                return size + CodedOutputByteBufferNano.computeBoolSize(4, this.hasSessionId);
            }
            return size;
        }

        public LaunchApplicationRequest mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_GET_LINK /*8*/:
                        this.applicationId = input.readInt32();
                        continue;
                    case LogSource.GMS_CORE_PEOPLE /*16*/:
                        int value = input.readInt32();
                        switch (value) {
                            case Action.UNKNOWN /*0*/:
                            case TimeSelection.Type.TEMPORARY /*1*/:
                            case TimeSelection.Type.INDEFINITELY /*2*/:
                                this.launchType = value;
                                break;
                            default:
                                continue;
                        }
                    case LogSource.LB_C /*24*/:
                        this.relaunchIfRunning = input.readBool();
                        continue;
                    case LogSource.SOCIAL /*32*/:
                        this.hasSessionId = input.readBool();
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

        public static LaunchApplicationRequest parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (LaunchApplicationRequest) MessageNano.mergeFrom(new LaunchApplicationRequest(), data);
        }

        public static LaunchApplicationRequest parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new LaunchApplicationRequest().mergeFrom(input);
        }
    }

    public static final class MDNSSubtype extends ExtendableMessageNano<MDNSSubtype> {
        private static volatile MDNSSubtype[] _emptyArray;
        public int applicationId;
        public String namespaceName;

        public static MDNSSubtype[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new MDNSSubtype[0];
                    }
                }
            }
            return _emptyArray;
        }

        public MDNSSubtype() {
            clear();
        }

        public MDNSSubtype clear() {
            this.applicationId = 0;
            this.namespaceName = BuildConfig.VERSION_NAME;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof MDNSSubtype)) {
                return false;
            }
            MDNSSubtype other = (MDNSSubtype) o;
            if (this.applicationId != other.applicationId) {
                return false;
            }
            if (this.namespaceName == null) {
                if (other.namespaceName != null) {
                    return false;
                }
            } else if (!this.namespaceName.equals(other.namespaceName)) {
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
            int hashCode = (((((getClass().getName().hashCode() + 527) * 31) + this.applicationId) * 31) + (this.namespaceName == null ? 0 : this.namespaceName.hashCode())) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.applicationId != 0) {
                output.writeInt32(1, this.applicationId);
            }
            if (!this.namespaceName.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(2, this.namespaceName);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.applicationId != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(1, this.applicationId);
            }
            if (this.namespaceName.equals(BuildConfig.VERSION_NAME)) {
                return size;
            }
            return size + CodedOutputByteBufferNano.computeStringSize(2, this.namespaceName);
        }

        public MDNSSubtype mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_GET_LINK /*8*/:
                        this.applicationId = input.readInt32();
                        continue;
                    case LogSource.GOOGLE_ANALYTICS /*18*/:
                        this.namespaceName = input.readString();
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

        public static MDNSSubtype parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (MDNSSubtype) MessageNano.mergeFrom(new MDNSSubtype(), data);
        }

        public static MDNSSubtype parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new MDNSSubtype().mergeFrom(input);
        }
    }

    public static final class MDNSSubtypeDiscoveryResult extends ExtendableMessageNano<MDNSSubtypeDiscoveryResult> {
        private static volatile MDNSSubtypeDiscoveryResult[] _emptyArray;
        public int deviceCapabilities;
        public String deviceModelName;
        public boolean isPositiveFilteringResult;
        public int latencyMs;
        public int subtypeAppId;
        public String subtypeNamespace;
        public boolean wasSubtypePreviouslyDiscovered;

        public static MDNSSubtypeDiscoveryResult[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new MDNSSubtypeDiscoveryResult[0];
                    }
                }
            }
            return _emptyArray;
        }

        public MDNSSubtypeDiscoveryResult() {
            clear();
        }

        public MDNSSubtypeDiscoveryResult clear() {
            this.isPositiveFilteringResult = false;
            this.wasSubtypePreviouslyDiscovered = false;
            this.subtypeAppId = 0;
            this.subtypeNamespace = BuildConfig.VERSION_NAME;
            this.deviceCapabilities = 0;
            this.latencyMs = 0;
            this.deviceModelName = BuildConfig.VERSION_NAME;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof MDNSSubtypeDiscoveryResult)) {
                return false;
            }
            MDNSSubtypeDiscoveryResult other = (MDNSSubtypeDiscoveryResult) o;
            if (this.isPositiveFilteringResult != other.isPositiveFilteringResult || this.wasSubtypePreviouslyDiscovered != other.wasSubtypePreviouslyDiscovered || this.subtypeAppId != other.subtypeAppId) {
                return false;
            }
            if (this.subtypeNamespace == null) {
                if (other.subtypeNamespace != null) {
                    return false;
                }
            } else if (!this.subtypeNamespace.equals(other.subtypeNamespace)) {
                return false;
            }
            if (this.deviceCapabilities != other.deviceCapabilities || this.latencyMs != other.latencyMs) {
                return false;
            }
            if (this.deviceModelName == null) {
                if (other.deviceModelName != null) {
                    return false;
                }
            } else if (!this.deviceModelName.equals(other.deviceModelName)) {
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
            int i2 = 0;
            int hashCode = (((getClass().getName().hashCode() + 527) * 31) + (this.isPositiveFilteringResult ? 1231 : 1237)) * 31;
            if (!this.wasSubtypePreviouslyDiscovered) {
                i = 1237;
            }
            hashCode = (((((((((((hashCode + i) * 31) + this.subtypeAppId) * 31) + (this.subtypeNamespace == null ? 0 : this.subtypeNamespace.hashCode())) * 31) + this.deviceCapabilities) * 31) + this.latencyMs) * 31) + (this.deviceModelName == null ? 0 : this.deviceModelName.hashCode())) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i2 = this.unknownFieldData.hashCode();
            }
            return hashCode + i2;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.isPositiveFilteringResult) {
                output.writeBool(1, this.isPositiveFilteringResult);
            }
            if (this.wasSubtypePreviouslyDiscovered) {
                output.writeBool(2, this.wasSubtypePreviouslyDiscovered);
            }
            if (this.subtypeAppId != 0) {
                output.writeInt32(3, this.subtypeAppId);
            }
            if (!this.subtypeNamespace.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(4, this.subtypeNamespace);
            }
            if (this.deviceCapabilities != 0) {
                output.writeInt32(5, this.deviceCapabilities);
            }
            if (this.latencyMs != 0) {
                output.writeInt32(6, this.latencyMs);
            }
            if (!this.deviceModelName.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(7, this.deviceModelName);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.isPositiveFilteringResult) {
                size += CodedOutputByteBufferNano.computeBoolSize(1, this.isPositiveFilteringResult);
            }
            if (this.wasSubtypePreviouslyDiscovered) {
                size += CodedOutputByteBufferNano.computeBoolSize(2, this.wasSubtypePreviouslyDiscovered);
            }
            if (this.subtypeAppId != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(3, this.subtypeAppId);
            }
            if (!this.subtypeNamespace.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(4, this.subtypeNamespace);
            }
            if (this.deviceCapabilities != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(5, this.deviceCapabilities);
            }
            if (this.latencyMs != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(6, this.latencyMs);
            }
            if (this.deviceModelName.equals(BuildConfig.VERSION_NAME)) {
                return size;
            }
            return size + CodedOutputByteBufferNano.computeStringSize(7, this.deviceModelName);
        }

        public MDNSSubtypeDiscoveryResult mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_GET_LINK /*8*/:
                        this.isPositiveFilteringResult = input.readBool();
                        continue;
                    case LogSource.GMS_CORE_PEOPLE /*16*/:
                        this.wasSubtypePreviouslyDiscovered = input.readBool();
                        continue;
                    case LogSource.LB_C /*24*/:
                        this.subtypeAppId = input.readInt32();
                        continue;
                    case LogSource.NOVA /*34*/:
                        this.subtypeNamespace = input.readString();
                        continue;
                    case LogSource.COPRESENCE /*40*/:
                        this.deviceCapabilities = input.readInt32();
                        continue;
                    case LogSource.BACKDROP /*48*/:
                        this.latencyMs = input.readInt32();
                        continue;
                    case LogSource.SLIDES /*58*/:
                        this.deviceModelName = input.readString();
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

        public static MDNSSubtypeDiscoveryResult parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (MDNSSubtypeDiscoveryResult) MessageNano.mergeFrom(new MDNSSubtypeDiscoveryResult(), data);
        }

        public static MDNSSubtypeDiscoveryResult parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new MDNSSubtypeDiscoveryResult().mergeFrom(input);
        }
    }

    public static final class MdnsResponseParseError extends ExtendableMessageNano<MdnsResponseParseError> {
        private static volatile MdnsResponseParseError[] _emptyArray;
        public int responseErrorCode;
        public int sequenceNumber;

        public interface MdnsResponseErrorCode {
            public static final int ERROR_END_OF_FILE = 12;
            public static final int ERROR_NOT_RESPONSE_MESSAGE = 1;
            public static final int ERROR_NO_ANSWERS = 2;
            public static final int ERROR_READING_IP4_ADDRESS = 4;
            public static final int ERROR_READING_IP6_ADDRESS = 5;
            public static final int ERROR_READING_POINTER_RECORD = 6;
            public static final int ERROR_READING_RESPONSE_LABELS = 3;
            public static final int ERROR_READING_SERVICE_RECORD = 8;
            public static final int ERROR_READING_TEXT_RECORD = 10;
            public static final int ERROR_SKIPPING_POINTER_RECORD = 7;
            public static final int ERROR_SKIPPING_SERVICE_RECORD = 9;
            public static final int ERROR_SKIPPING_UNKNOWN_RECORD = 11;
            public static final int UNKNOWN = 0;
        }

        public static MdnsResponseParseError[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new MdnsResponseParseError[0];
                    }
                }
            }
            return _emptyArray;
        }

        public MdnsResponseParseError() {
            clear();
        }

        public MdnsResponseParseError clear() {
            this.sequenceNumber = 0;
            this.responseErrorCode = 0;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof MdnsResponseParseError)) {
                return false;
            }
            MdnsResponseParseError other = (MdnsResponseParseError) o;
            if (this.sequenceNumber != other.sequenceNumber || this.responseErrorCode != other.responseErrorCode) {
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
            int hashCode = (((((getClass().getName().hashCode() + 527) * 31) + this.sequenceNumber) * 31) + this.responseErrorCode) * 31;
            int hashCode2 = (this.unknownFieldData == null || this.unknownFieldData.isEmpty()) ? 0 : this.unknownFieldData.hashCode();
            return hashCode + hashCode2;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.sequenceNumber != 0) {
                output.writeInt32(1, this.sequenceNumber);
            }
            if (this.responseErrorCode != 0) {
                output.writeInt32(2, this.responseErrorCode);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.sequenceNumber != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(1, this.sequenceNumber);
            }
            if (this.responseErrorCode != 0) {
                return size + CodedOutputByteBufferNano.computeInt32Size(2, this.responseErrorCode);
            }
            return size;
        }

        public MdnsResponseParseError mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_GET_LINK /*8*/:
                        this.sequenceNumber = input.readInt32();
                        continue;
                    case LogSource.GMS_CORE_PEOPLE /*16*/:
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
                            case Type.TAP_LEARN_MORE /*11*/:
                            case Type.SWITCH_ACCOUNT /*12*/:
                                this.responseErrorCode = value;
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

        public static MdnsResponseParseError parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (MdnsResponseParseError) MessageNano.mergeFrom(new MdnsResponseParseError(), data);
        }

        public static MdnsResponseParseError parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new MdnsResponseParseError().mergeFrom(input);
        }
    }
}
