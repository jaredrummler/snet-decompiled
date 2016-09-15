package com.google.common.logging;

import com.google.android.gms.lint.BuildConfig;
import com.google.android.gms.people.PeopleConstants.PeopleColumnBitmask;
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

public interface CarLog {

    public static final class AudioFocusEvent extends ExtendableMessageNano<AudioFocusEvent> {
        private static volatile AudioFocusEvent[] _emptyArray;
        public int requestResponseTimeMillis;
        public int requestType;

        public interface RequestType {
            public static final int AUDIO_FOCUS_GAIN = 1;
            public static final int AUDIO_FOCUS_GAIN_TRANSIENT = 2;
            public static final int AUDIO_FOCUS_GAIN_TRANSIENT_MAY_DUCK = 3;
            public static final int AUDIO_FOCUS_RELEASE = 4;
            public static final int AUDIO_FOCUS_UNKNOWN = 0;
        }

        public static AudioFocusEvent[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new AudioFocusEvent[0];
                    }
                }
            }
            return _emptyArray;
        }

        public AudioFocusEvent() {
            clear();
        }

        public AudioFocusEvent clear() {
            this.requestType = 0;
            this.requestResponseTimeMillis = 0;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof AudioFocusEvent)) {
                return false;
            }
            AudioFocusEvent other = (AudioFocusEvent) o;
            if (this.requestType != other.requestType || this.requestResponseTimeMillis != other.requestResponseTimeMillis) {
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
            int hashCode = (((((getClass().getName().hashCode() + 527) * 31) + this.requestType) * 31) + this.requestResponseTimeMillis) * 31;
            int hashCode2 = (this.unknownFieldData == null || this.unknownFieldData.isEmpty()) ? 0 : this.unknownFieldData.hashCode();
            return hashCode + hashCode2;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.requestType != 0) {
                output.writeInt32(1, this.requestType);
            }
            if (this.requestResponseTimeMillis != 0) {
                output.writeInt32(2, this.requestResponseTimeMillis);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.requestType != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(1, this.requestType);
            }
            if (this.requestResponseTimeMillis != 0) {
                return size + CodedOutputByteBufferNano.computeInt32Size(2, this.requestResponseTimeMillis);
            }
            return size;
        }

        public AudioFocusEvent mergeFrom(CodedInputByteBufferNano input) throws IOException {
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
                                this.requestType = value;
                                break;
                            default:
                                continue;
                        }
                    case LogSource.GMS_CORE_PEOPLE /*16*/:
                        this.requestResponseTimeMillis = input.readInt32();
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

        public static AudioFocusEvent parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (AudioFocusEvent) MessageNano.mergeFrom(new AudioFocusEvent(), data);
        }

        public static AudioFocusEvent parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new AudioFocusEvent().mergeFrom(input);
        }
    }

    public static final class CarEvent extends ExtendableMessageNano<CarEvent> {
        private static volatile CarEvent[] _emptyArray;
        public AudioFocusEvent audioFocusEvent;
        public int batteryLevel;
        public DeviceConnect connectEvent;
        public DeviceDisconnect disconnectEvent;
        public int drivingStatus;
        public FacetChange facetChangeEvent;
        public FrxStateChanged frxEvent;
        public int gearData;
        public boolean isMusicPlaying;
        public boolean nightMode;
        public int sessionId;
        public int speedMmS;
        public SslEvent sslEvent;
        public TouchEvent touchEvent;
        public VoiceAction voiceEvent;

        public interface EventType {
            public static final int AUDIO_FOCUS_REQUEST = 22;
            public static final int DEVICE_CONNECTED = 1;
            public static final int DEVICE_DISCONNECTED = 2;
            public static final int FACET_CHANGED = 3;
            public static final int FACET_HOME_END = 14;
            public static final int FACET_HOME_START = 13;
            public static final int FACET_MUSIC_END = 10;
            public static final int FACET_MUSIC_START = 9;
            public static final int FACET_NAVIGATION_END = 6;
            public static final int FACET_NAVIGATION_START = 5;
            public static final int FACET_OEM_END = 12;
            public static final int FACET_OEM_START = 11;
            public static final int FACET_PHONE_END = 8;
            public static final int FACET_PHONE_START = 7;
            public static final int FRX_END = 19;
            public static final int FRX_START = 17;
            public static final int FRX_STATE_CHANGE = 18;
            public static final int SSL_HANDSHAKE = 20;
            public static final int TOUCH_EVENT = 21;
            public static final int UNKNOWN_EVENT_TYPE = 0;
            public static final int VOICE_SEARCH = 4;
            public static final int VOICE_SEARCH_END = 16;
            public static final int VOICE_SEARCH_START = 15;
        }

        public interface GearType {
            public static final int GEAR_1 = 1;
            public static final int GEAR_2 = 2;
            public static final int GEAR_3 = 3;
            public static final int GEAR_4 = 4;
            public static final int GEAR_5 = 5;
            public static final int GEAR_6 = 6;
            public static final int GEAR_DRIVE = 10;
            public static final int GEAR_NEUTRAL = 0;
            public static final int GEAR_PARK = 11;
            public static final int GEAR_REVERSE = 12;
            public static final int UNKNOWN_GEAR = -1;
        }

        public static CarEvent[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new CarEvent[0];
                    }
                }
            }
            return _emptyArray;
        }

        public CarEvent() {
            clear();
        }

        public CarEvent clear() {
            this.connectEvent = null;
            this.disconnectEvent = null;
            this.facetChangeEvent = null;
            this.voiceEvent = null;
            this.frxEvent = null;
            this.sslEvent = null;
            this.touchEvent = null;
            this.audioFocusEvent = null;
            this.speedMmS = 0;
            this.nightMode = false;
            this.drivingStatus = 0;
            this.gearData = -1;
            this.sessionId = 0;
            this.batteryLevel = 0;
            this.isMusicPlaying = false;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof CarEvent)) {
                return false;
            }
            CarEvent other = (CarEvent) o;
            if (this.connectEvent == null) {
                if (other.connectEvent != null) {
                    return false;
                }
            } else if (!this.connectEvent.equals(other.connectEvent)) {
                return false;
            }
            if (this.disconnectEvent == null) {
                if (other.disconnectEvent != null) {
                    return false;
                }
            } else if (!this.disconnectEvent.equals(other.disconnectEvent)) {
                return false;
            }
            if (this.facetChangeEvent == null) {
                if (other.facetChangeEvent != null) {
                    return false;
                }
            } else if (!this.facetChangeEvent.equals(other.facetChangeEvent)) {
                return false;
            }
            if (this.voiceEvent == null) {
                if (other.voiceEvent != null) {
                    return false;
                }
            } else if (!this.voiceEvent.equals(other.voiceEvent)) {
                return false;
            }
            if (this.frxEvent == null) {
                if (other.frxEvent != null) {
                    return false;
                }
            } else if (!this.frxEvent.equals(other.frxEvent)) {
                return false;
            }
            if (this.sslEvent == null) {
                if (other.sslEvent != null) {
                    return false;
                }
            } else if (!this.sslEvent.equals(other.sslEvent)) {
                return false;
            }
            if (this.touchEvent == null) {
                if (other.touchEvent != null) {
                    return false;
                }
            } else if (!this.touchEvent.equals(other.touchEvent)) {
                return false;
            }
            if (this.audioFocusEvent == null) {
                if (other.audioFocusEvent != null) {
                    return false;
                }
            } else if (!this.audioFocusEvent.equals(other.audioFocusEvent)) {
                return false;
            }
            if (this.speedMmS != other.speedMmS || this.nightMode != other.nightMode || this.drivingStatus != other.drivingStatus || this.gearData != other.gearData || this.sessionId != other.sessionId || this.batteryLevel != other.batteryLevel || this.isMusicPlaying != other.isMusicPlaying) {
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
            int hashCode = (((((((((((((((((((getClass().getName().hashCode() + 527) * 31) + (this.connectEvent == null ? 0 : this.connectEvent.hashCode())) * 31) + (this.disconnectEvent == null ? 0 : this.disconnectEvent.hashCode())) * 31) + (this.facetChangeEvent == null ? 0 : this.facetChangeEvent.hashCode())) * 31) + (this.voiceEvent == null ? 0 : this.voiceEvent.hashCode())) * 31) + (this.frxEvent == null ? 0 : this.frxEvent.hashCode())) * 31) + (this.sslEvent == null ? 0 : this.sslEvent.hashCode())) * 31) + (this.touchEvent == null ? 0 : this.touchEvent.hashCode())) * 31) + (this.audioFocusEvent == null ? 0 : this.audioFocusEvent.hashCode())) * 31) + this.speedMmS) * 31;
            if (this.nightMode) {
                i = 1231;
            } else {
                i = 1237;
            }
            i = (((((((((hashCode + i) * 31) + this.drivingStatus) * 31) + this.gearData) * 31) + this.sessionId) * 31) + this.batteryLevel) * 31;
            if (!this.isMusicPlaying) {
                i2 = 1237;
            }
            i = (i + i2) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i3 = this.unknownFieldData.hashCode();
            }
            return i + i3;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.connectEvent != null) {
                output.writeMessage(1, this.connectEvent);
            }
            if (this.disconnectEvent != null) {
                output.writeMessage(2, this.disconnectEvent);
            }
            if (this.facetChangeEvent != null) {
                output.writeMessage(3, this.facetChangeEvent);
            }
            if (this.voiceEvent != null) {
                output.writeMessage(4, this.voiceEvent);
            }
            if (this.frxEvent != null) {
                output.writeMessage(5, this.frxEvent);
            }
            if (this.sslEvent != null) {
                output.writeMessage(6, this.sslEvent);
            }
            if (this.touchEvent != null) {
                output.writeMessage(7, this.touchEvent);
            }
            if (this.audioFocusEvent != null) {
                output.writeMessage(8, this.audioFocusEvent);
            }
            if (this.speedMmS != 0) {
                output.writeInt32(32, this.speedMmS);
            }
            if (this.nightMode) {
                output.writeBool(33, this.nightMode);
            }
            if (this.drivingStatus != 0) {
                output.writeInt32(34, this.drivingStatus);
            }
            if (this.gearData != -1) {
                output.writeInt32(35, this.gearData);
            }
            if (this.sessionId != 0) {
                output.writeInt32(36, this.sessionId);
            }
            if (this.batteryLevel != 0) {
                output.writeInt32(37, this.batteryLevel);
            }
            if (this.isMusicPlaying) {
                output.writeBool(38, this.isMusicPlaying);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.connectEvent != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(1, this.connectEvent);
            }
            if (this.disconnectEvent != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(2, this.disconnectEvent);
            }
            if (this.facetChangeEvent != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(3, this.facetChangeEvent);
            }
            if (this.voiceEvent != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(4, this.voiceEvent);
            }
            if (this.frxEvent != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(5, this.frxEvent);
            }
            if (this.sslEvent != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(6, this.sslEvent);
            }
            if (this.touchEvent != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(7, this.touchEvent);
            }
            if (this.audioFocusEvent != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(8, this.audioFocusEvent);
            }
            if (this.speedMmS != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(32, this.speedMmS);
            }
            if (this.nightMode) {
                size += CodedOutputByteBufferNano.computeBoolSize(33, this.nightMode);
            }
            if (this.drivingStatus != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(34, this.drivingStatus);
            }
            if (this.gearData != -1) {
                size += CodedOutputByteBufferNano.computeInt32Size(35, this.gearData);
            }
            if (this.sessionId != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(36, this.sessionId);
            }
            if (this.batteryLevel != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(37, this.batteryLevel);
            }
            if (this.isMusicPlaying) {
                return size + CodedOutputByteBufferNano.computeBoolSize(38, this.isMusicPlaying);
            }
            return size;
        }

        public CarEvent mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        if (this.connectEvent == null) {
                            this.connectEvent = new DeviceConnect();
                        }
                        input.readMessage(this.connectEvent);
                        continue;
                    case LogSource.GOOGLE_ANALYTICS /*18*/:
                        if (this.disconnectEvent == null) {
                            this.disconnectEvent = new DeviceDisconnect();
                        }
                        input.readMessage(this.disconnectEvent);
                        continue;
                    case LogSource.ANDROID_CAMERA /*26*/:
                        if (this.facetChangeEvent == null) {
                            this.facetChangeEvent = new FacetChange();
                        }
                        input.readMessage(this.facetChangeEvent);
                        continue;
                    case LogSource.NOVA /*34*/:
                        if (this.voiceEvent == null) {
                            this.voiceEvent = new VoiceAction();
                        }
                        input.readMessage(this.voiceEvent);
                        continue;
                    case LogSource.PHOTOS /*42*/:
                        if (this.frxEvent == null) {
                            this.frxEvent = new FrxStateChanged();
                        }
                        input.readMessage(this.frxEvent);
                        continue;
                    case LogSource.HANGOUT_LOG_REQUEST /*50*/:
                        if (this.sslEvent == null) {
                            this.sslEvent = new SslEvent();
                        }
                        input.readMessage(this.sslEvent);
                        continue;
                    case LogSource.SLIDES /*58*/:
                        if (this.touchEvent == null) {
                            this.touchEvent = new TouchEvent();
                        }
                        input.readMessage(this.touchEvent);
                        continue;
                    case LogSource.WIFI_ASSISTANT /*66*/:
                        if (this.audioFocusEvent == null) {
                            this.audioFocusEvent = new AudioFocusEvent();
                        }
                        input.readMessage(this.audioFocusEvent);
                        continue;
                    case PeopleColumnBitmask.IS_BLOCKED /*256*/:
                        this.speedMmS = input.readInt32();
                        continue;
                    case 264:
                        this.nightMode = input.readBool();
                        continue;
                    case 272:
                        this.drivingStatus = input.readInt32();
                        continue;
                    case 280:
                        int value = input.readInt32();
                        switch (value) {
                            case LogSource.UNKNOWN /*-1*/:
                            case Action.UNKNOWN /*0*/:
                            case TimeSelection.Type.TEMPORARY /*1*/:
                            case TimeSelection.Type.INDEFINITELY /*2*/:
                            case TimeSelection.Type.CUSTOM /*3*/:
                            case OvenFreshResult.NO_ACCOUNTS /*4*/:
                            case Type.ADD_NEW_SHARES /*5*/:
                            case Type.REMOVE_SHARE /*6*/:
                            case Type.TAP_ABOUT /*10*/:
                            case Type.TAP_LEARN_MORE /*11*/:
                            case Type.SWITCH_ACCOUNT /*12*/:
                                this.gearData = value;
                                break;
                            default:
                                continue;
                        }
                    case 288:
                        this.sessionId = input.readInt32();
                        continue;
                    case 296:
                        this.batteryLevel = input.readInt32();
                        continue;
                    case 304:
                        this.isMusicPlaying = input.readBool();
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

        public static CarEvent parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (CarEvent) MessageNano.mergeFrom(new CarEvent(), data);
        }

        public static CarEvent parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new CarEvent().mergeFrom(input);
        }
    }

    public static final class DeviceConnect extends ExtendableMessageNano<DeviceConnect> {
        private static volatile DeviceConnect[] _emptyArray;
        public CarInfo carInfo;
        public DeviceInfo deviceInfo;

        public static final class CarInfo extends ExtendableMessageNano<CarInfo> {
            private static volatile CarInfo[] _emptyArray;
            public String carMake;
            public String carModel;
            public String carYear;
            public String headUnitBuild;
            public int headUnitHeightPx;
            public String headUnitMake;
            public String headUnitModel;
            public String headUnitVersion;
            public int headUnitWidthPx;

            public static CarInfo[] emptyArray() {
                if (_emptyArray == null) {
                    synchronized (InternalNano.LAZY_INIT_LOCK) {
                        if (_emptyArray == null) {
                            _emptyArray = new CarInfo[0];
                        }
                    }
                }
                return _emptyArray;
            }

            public CarInfo() {
                clear();
            }

            public CarInfo clear() {
                this.carMake = BuildConfig.VERSION_NAME;
                this.carModel = BuildConfig.VERSION_NAME;
                this.carYear = BuildConfig.VERSION_NAME;
                this.headUnitMake = BuildConfig.VERSION_NAME;
                this.headUnitModel = BuildConfig.VERSION_NAME;
                this.headUnitBuild = BuildConfig.VERSION_NAME;
                this.headUnitVersion = BuildConfig.VERSION_NAME;
                this.headUnitHeightPx = 0;
                this.headUnitWidthPx = 0;
                this.unknownFieldData = null;
                this.cachedSize = -1;
                return this;
            }

            public boolean equals(Object o) {
                if (o == this) {
                    return true;
                }
                if (!(o instanceof CarInfo)) {
                    return false;
                }
                CarInfo other = (CarInfo) o;
                if (this.carMake == null) {
                    if (other.carMake != null) {
                        return false;
                    }
                } else if (!this.carMake.equals(other.carMake)) {
                    return false;
                }
                if (this.carModel == null) {
                    if (other.carModel != null) {
                        return false;
                    }
                } else if (!this.carModel.equals(other.carModel)) {
                    return false;
                }
                if (this.carYear == null) {
                    if (other.carYear != null) {
                        return false;
                    }
                } else if (!this.carYear.equals(other.carYear)) {
                    return false;
                }
                if (this.headUnitMake == null) {
                    if (other.headUnitMake != null) {
                        return false;
                    }
                } else if (!this.headUnitMake.equals(other.headUnitMake)) {
                    return false;
                }
                if (this.headUnitModel == null) {
                    if (other.headUnitModel != null) {
                        return false;
                    }
                } else if (!this.headUnitModel.equals(other.headUnitModel)) {
                    return false;
                }
                if (this.headUnitBuild == null) {
                    if (other.headUnitBuild != null) {
                        return false;
                    }
                } else if (!this.headUnitBuild.equals(other.headUnitBuild)) {
                    return false;
                }
                if (this.headUnitVersion == null) {
                    if (other.headUnitVersion != null) {
                        return false;
                    }
                } else if (!this.headUnitVersion.equals(other.headUnitVersion)) {
                    return false;
                }
                if (this.headUnitHeightPx != other.headUnitHeightPx || this.headUnitWidthPx != other.headUnitWidthPx) {
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
                int hashCode = (((((((((((((((((((getClass().getName().hashCode() + 527) * 31) + (this.carMake == null ? 0 : this.carMake.hashCode())) * 31) + (this.carModel == null ? 0 : this.carModel.hashCode())) * 31) + (this.carYear == null ? 0 : this.carYear.hashCode())) * 31) + (this.headUnitMake == null ? 0 : this.headUnitMake.hashCode())) * 31) + (this.headUnitModel == null ? 0 : this.headUnitModel.hashCode())) * 31) + (this.headUnitBuild == null ? 0 : this.headUnitBuild.hashCode())) * 31) + (this.headUnitVersion == null ? 0 : this.headUnitVersion.hashCode())) * 31) + this.headUnitHeightPx) * 31) + this.headUnitWidthPx) * 31;
                if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                    i = this.unknownFieldData.hashCode();
                }
                return hashCode + i;
            }

            public void writeTo(CodedOutputByteBufferNano output) throws IOException {
                if (!this.carMake.equals(BuildConfig.VERSION_NAME)) {
                    output.writeString(1, this.carMake);
                }
                if (!this.carModel.equals(BuildConfig.VERSION_NAME)) {
                    output.writeString(2, this.carModel);
                }
                if (!this.carYear.equals(BuildConfig.VERSION_NAME)) {
                    output.writeString(3, this.carYear);
                }
                if (!this.headUnitMake.equals(BuildConfig.VERSION_NAME)) {
                    output.writeString(4, this.headUnitMake);
                }
                if (!this.headUnitModel.equals(BuildConfig.VERSION_NAME)) {
                    output.writeString(5, this.headUnitModel);
                }
                if (!this.headUnitBuild.equals(BuildConfig.VERSION_NAME)) {
                    output.writeString(6, this.headUnitBuild);
                }
                if (!this.headUnitVersion.equals(BuildConfig.VERSION_NAME)) {
                    output.writeString(7, this.headUnitVersion);
                }
                if (this.headUnitHeightPx != 0) {
                    output.writeInt32(8, this.headUnitHeightPx);
                }
                if (this.headUnitWidthPx != 0) {
                    output.writeInt32(9, this.headUnitWidthPx);
                }
                super.writeTo(output);
            }

            protected int computeSerializedSize() {
                int size = super.computeSerializedSize();
                if (!this.carMake.equals(BuildConfig.VERSION_NAME)) {
                    size += CodedOutputByteBufferNano.computeStringSize(1, this.carMake);
                }
                if (!this.carModel.equals(BuildConfig.VERSION_NAME)) {
                    size += CodedOutputByteBufferNano.computeStringSize(2, this.carModel);
                }
                if (!this.carYear.equals(BuildConfig.VERSION_NAME)) {
                    size += CodedOutputByteBufferNano.computeStringSize(3, this.carYear);
                }
                if (!this.headUnitMake.equals(BuildConfig.VERSION_NAME)) {
                    size += CodedOutputByteBufferNano.computeStringSize(4, this.headUnitMake);
                }
                if (!this.headUnitModel.equals(BuildConfig.VERSION_NAME)) {
                    size += CodedOutputByteBufferNano.computeStringSize(5, this.headUnitModel);
                }
                if (!this.headUnitBuild.equals(BuildConfig.VERSION_NAME)) {
                    size += CodedOutputByteBufferNano.computeStringSize(6, this.headUnitBuild);
                }
                if (!this.headUnitVersion.equals(BuildConfig.VERSION_NAME)) {
                    size += CodedOutputByteBufferNano.computeStringSize(7, this.headUnitVersion);
                }
                if (this.headUnitHeightPx != 0) {
                    size += CodedOutputByteBufferNano.computeInt32Size(8, this.headUnitHeightPx);
                }
                if (this.headUnitWidthPx != 0) {
                    return size + CodedOutputByteBufferNano.computeInt32Size(9, this.headUnitWidthPx);
                }
                return size;
            }

            public CarInfo mergeFrom(CodedInputByteBufferNano input) throws IOException {
                while (true) {
                    int tag = input.readTag();
                    switch (tag) {
                        case Action.UNKNOWN /*0*/:
                            break;
                        case Type.TAP_ABOUT /*10*/:
                            this.carMake = input.readString();
                            continue;
                        case LogSource.GOOGLE_ANALYTICS /*18*/:
                            this.carModel = input.readString();
                            continue;
                        case LogSource.ANDROID_CAMERA /*26*/:
                            this.carYear = input.readString();
                            continue;
                        case LogSource.NOVA /*34*/:
                            this.headUnitMake = input.readString();
                            continue;
                        case LogSource.PHOTOS /*42*/:
                            this.headUnitModel = input.readString();
                            continue;
                        case LogSource.HANGOUT_LOG_REQUEST /*50*/:
                            this.headUnitBuild = input.readString();
                            continue;
                        case LogSource.SLIDES /*58*/:
                            this.headUnitVersion = input.readString();
                            continue;
                        case LogSource.KIDS_COMMUNICATOR /*64*/:
                            this.headUnitHeightPx = input.readInt32();
                            continue;
                        case LogSource.JUSTSPEAK /*72*/:
                            this.headUnitWidthPx = input.readInt32();
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

            public static CarInfo parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
                return (CarInfo) MessageNano.mergeFrom(new CarInfo(), data);
            }

            public static CarInfo parseFrom(CodedInputByteBufferNano input) throws IOException {
                return new CarInfo().mergeFrom(input);
            }
        }

        public static final class DeviceInfo extends ExtendableMessageNano<DeviceInfo> {
            private static volatile DeviceInfo[] _emptyArray;
            public String gearheadVersion;
            public String gmmVersion;
            public String gsaVersion;
            public String playMusicVersion;

            public static DeviceInfo[] emptyArray() {
                if (_emptyArray == null) {
                    synchronized (InternalNano.LAZY_INIT_LOCK) {
                        if (_emptyArray == null) {
                            _emptyArray = new DeviceInfo[0];
                        }
                    }
                }
                return _emptyArray;
            }

            public DeviceInfo() {
                clear();
            }

            public DeviceInfo clear() {
                this.gsaVersion = BuildConfig.VERSION_NAME;
                this.gmmVersion = BuildConfig.VERSION_NAME;
                this.playMusicVersion = BuildConfig.VERSION_NAME;
                this.gearheadVersion = BuildConfig.VERSION_NAME;
                this.unknownFieldData = null;
                this.cachedSize = -1;
                return this;
            }

            public boolean equals(Object o) {
                if (o == this) {
                    return true;
                }
                if (!(o instanceof DeviceInfo)) {
                    return false;
                }
                DeviceInfo other = (DeviceInfo) o;
                if (this.gsaVersion == null) {
                    if (other.gsaVersion != null) {
                        return false;
                    }
                } else if (!this.gsaVersion.equals(other.gsaVersion)) {
                    return false;
                }
                if (this.gmmVersion == null) {
                    if (other.gmmVersion != null) {
                        return false;
                    }
                } else if (!this.gmmVersion.equals(other.gmmVersion)) {
                    return false;
                }
                if (this.playMusicVersion == null) {
                    if (other.playMusicVersion != null) {
                        return false;
                    }
                } else if (!this.playMusicVersion.equals(other.playMusicVersion)) {
                    return false;
                }
                if (this.gearheadVersion == null) {
                    if (other.gearheadVersion != null) {
                        return false;
                    }
                } else if (!this.gearheadVersion.equals(other.gearheadVersion)) {
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
                int hashCode = (((((((((getClass().getName().hashCode() + 527) * 31) + (this.gsaVersion == null ? 0 : this.gsaVersion.hashCode())) * 31) + (this.gmmVersion == null ? 0 : this.gmmVersion.hashCode())) * 31) + (this.playMusicVersion == null ? 0 : this.playMusicVersion.hashCode())) * 31) + (this.gearheadVersion == null ? 0 : this.gearheadVersion.hashCode())) * 31;
                if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                    i = this.unknownFieldData.hashCode();
                }
                return hashCode + i;
            }

            public void writeTo(CodedOutputByteBufferNano output) throws IOException {
                if (!this.gsaVersion.equals(BuildConfig.VERSION_NAME)) {
                    output.writeString(1, this.gsaVersion);
                }
                if (!this.gmmVersion.equals(BuildConfig.VERSION_NAME)) {
                    output.writeString(2, this.gmmVersion);
                }
                if (!this.playMusicVersion.equals(BuildConfig.VERSION_NAME)) {
                    output.writeString(3, this.playMusicVersion);
                }
                if (!this.gearheadVersion.equals(BuildConfig.VERSION_NAME)) {
                    output.writeString(4, this.gearheadVersion);
                }
                super.writeTo(output);
            }

            protected int computeSerializedSize() {
                int size = super.computeSerializedSize();
                if (!this.gsaVersion.equals(BuildConfig.VERSION_NAME)) {
                    size += CodedOutputByteBufferNano.computeStringSize(1, this.gsaVersion);
                }
                if (!this.gmmVersion.equals(BuildConfig.VERSION_NAME)) {
                    size += CodedOutputByteBufferNano.computeStringSize(2, this.gmmVersion);
                }
                if (!this.playMusicVersion.equals(BuildConfig.VERSION_NAME)) {
                    size += CodedOutputByteBufferNano.computeStringSize(3, this.playMusicVersion);
                }
                if (this.gearheadVersion.equals(BuildConfig.VERSION_NAME)) {
                    return size;
                }
                return size + CodedOutputByteBufferNano.computeStringSize(4, this.gearheadVersion);
            }

            public DeviceInfo mergeFrom(CodedInputByteBufferNano input) throws IOException {
                while (true) {
                    int tag = input.readTag();
                    switch (tag) {
                        case Action.UNKNOWN /*0*/:
                            break;
                        case Type.TAP_ABOUT /*10*/:
                            this.gsaVersion = input.readString();
                            continue;
                        case LogSource.GOOGLE_ANALYTICS /*18*/:
                            this.gmmVersion = input.readString();
                            continue;
                        case LogSource.ANDROID_CAMERA /*26*/:
                            this.playMusicVersion = input.readString();
                            continue;
                        case LogSource.NOVA /*34*/:
                            this.gearheadVersion = input.readString();
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

            public static DeviceInfo parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
                return (DeviceInfo) MessageNano.mergeFrom(new DeviceInfo(), data);
            }

            public static DeviceInfo parseFrom(CodedInputByteBufferNano input) throws IOException {
                return new DeviceInfo().mergeFrom(input);
            }
        }

        public static DeviceConnect[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new DeviceConnect[0];
                    }
                }
            }
            return _emptyArray;
        }

        public DeviceConnect() {
            clear();
        }

        public DeviceConnect clear() {
            this.deviceInfo = null;
            this.carInfo = null;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof DeviceConnect)) {
                return false;
            }
            DeviceConnect other = (DeviceConnect) o;
            if (this.deviceInfo == null) {
                if (other.deviceInfo != null) {
                    return false;
                }
            } else if (!this.deviceInfo.equals(other.deviceInfo)) {
                return false;
            }
            if (this.carInfo == null) {
                if (other.carInfo != null) {
                    return false;
                }
            } else if (!this.carInfo.equals(other.carInfo)) {
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
            int hashCode = (((((getClass().getName().hashCode() + 527) * 31) + (this.deviceInfo == null ? 0 : this.deviceInfo.hashCode())) * 31) + (this.carInfo == null ? 0 : this.carInfo.hashCode())) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.deviceInfo != null) {
                output.writeMessage(1, this.deviceInfo);
            }
            if (this.carInfo != null) {
                output.writeMessage(2, this.carInfo);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.deviceInfo != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(1, this.deviceInfo);
            }
            if (this.carInfo != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(2, this.carInfo);
            }
            return size;
        }

        public DeviceConnect mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        if (this.deviceInfo == null) {
                            this.deviceInfo = new DeviceInfo();
                        }
                        input.readMessage(this.deviceInfo);
                        continue;
                    case LogSource.GOOGLE_ANALYTICS /*18*/:
                        if (this.carInfo == null) {
                            this.carInfo = new CarInfo();
                        }
                        input.readMessage(this.carInfo);
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

        public static DeviceConnect parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (DeviceConnect) MessageNano.mergeFrom(new DeviceConnect(), data);
        }

        public static DeviceConnect parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new DeviceConnect().mergeFrom(input);
        }
    }

    public static final class DeviceDisconnect extends ExtendableMessageNano<DeviceDisconnect> {
        private static volatile DeviceDisconnect[] _emptyArray;
        public int numAudioFocusRequests;
        public int numAudioFocusTimeouts;
        public long timeProjectingMillis;

        public static DeviceDisconnect[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new DeviceDisconnect[0];
                    }
                }
            }
            return _emptyArray;
        }

        public DeviceDisconnect() {
            clear();
        }

        public DeviceDisconnect clear() {
            this.timeProjectingMillis = 0;
            this.numAudioFocusTimeouts = 0;
            this.numAudioFocusRequests = 0;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof DeviceDisconnect)) {
                return false;
            }
            DeviceDisconnect other = (DeviceDisconnect) o;
            if (this.timeProjectingMillis != other.timeProjectingMillis || this.numAudioFocusTimeouts != other.numAudioFocusTimeouts || this.numAudioFocusRequests != other.numAudioFocusRequests) {
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
            int hashCode = (((((((getClass().getName().hashCode() + 527) * 31) + ((int) (this.timeProjectingMillis ^ (this.timeProjectingMillis >>> 32)))) * 31) + this.numAudioFocusTimeouts) * 31) + this.numAudioFocusRequests) * 31;
            int hashCode2 = (this.unknownFieldData == null || this.unknownFieldData.isEmpty()) ? 0 : this.unknownFieldData.hashCode();
            return hashCode + hashCode2;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.timeProjectingMillis != 0) {
                output.writeInt64(1, this.timeProjectingMillis);
            }
            if (this.numAudioFocusTimeouts != 0) {
                output.writeInt32(2, this.numAudioFocusTimeouts);
            }
            if (this.numAudioFocusRequests != 0) {
                output.writeInt32(3, this.numAudioFocusRequests);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.timeProjectingMillis != 0) {
                size += CodedOutputByteBufferNano.computeInt64Size(1, this.timeProjectingMillis);
            }
            if (this.numAudioFocusTimeouts != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(2, this.numAudioFocusTimeouts);
            }
            if (this.numAudioFocusRequests != 0) {
                return size + CodedOutputByteBufferNano.computeInt32Size(3, this.numAudioFocusRequests);
            }
            return size;
        }

        public DeviceDisconnect mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_GET_LINK /*8*/:
                        this.timeProjectingMillis = input.readInt64();
                        continue;
                    case LogSource.GMS_CORE_PEOPLE /*16*/:
                        this.numAudioFocusTimeouts = input.readInt32();
                        continue;
                    case LogSource.LB_C /*24*/:
                        this.numAudioFocusRequests = input.readInt32();
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

        public static DeviceDisconnect parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (DeviceDisconnect) MessageNano.mergeFrom(new DeviceDisconnect(), data);
        }

        public static DeviceDisconnect parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new DeviceDisconnect().mergeFrom(input);
        }
    }

    public static final class Facet extends ExtendableMessageNano<Facet> {
        private static volatile Facet[] _emptyArray;
        public String packageName;
        public int type;

        public interface FacetType {
            public static final int HOME = 5;
            public static final int MUSIC = 3;
            public static final int NAVIGATION = 1;
            public static final int OEM = 4;
            public static final int PHONE = 2;
            public static final int UNKNOWN_FACET = 0;
        }

        public static Facet[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new Facet[0];
                    }
                }
            }
            return _emptyArray;
        }

        public Facet() {
            clear();
        }

        public Facet clear() {
            this.type = 0;
            this.packageName = BuildConfig.VERSION_NAME;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof Facet)) {
                return false;
            }
            Facet other = (Facet) o;
            if (this.type != other.type) {
                return false;
            }
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
            int hashCode = (((((getClass().getName().hashCode() + 527) * 31) + this.type) * 31) + (this.packageName == null ? 0 : this.packageName.hashCode())) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.type != 0) {
                output.writeInt32(1, this.type);
            }
            if (!this.packageName.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(2, this.packageName);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.type != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(1, this.type);
            }
            if (this.packageName.equals(BuildConfig.VERSION_NAME)) {
                return size;
            }
            return size + CodedOutputByteBufferNano.computeStringSize(2, this.packageName);
        }

        public Facet mergeFrom(CodedInputByteBufferNano input) throws IOException {
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
                                this.type = value;
                                break;
                            default:
                                continue;
                        }
                    case LogSource.GOOGLE_ANALYTICS /*18*/:
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

        public static Facet parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (Facet) MessageNano.mergeFrom(new Facet(), data);
        }

        public static Facet parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new Facet().mergeFrom(input);
        }
    }

    public static final class FacetChange extends ExtendableMessageNano<FacetChange> {
        private static volatile FacetChange[] _emptyArray;
        public Facet facet;
        public Facet fromFacet;
        public long timeInFacetMillis;
        public Facet toFacet;

        public static FacetChange[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new FacetChange[0];
                    }
                }
            }
            return _emptyArray;
        }

        public FacetChange() {
            clear();
        }

        public FacetChange clear() {
            this.fromFacet = null;
            this.toFacet = null;
            this.timeInFacetMillis = 0;
            this.facet = null;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof FacetChange)) {
                return false;
            }
            FacetChange other = (FacetChange) o;
            if (this.fromFacet == null) {
                if (other.fromFacet != null) {
                    return false;
                }
            } else if (!this.fromFacet.equals(other.fromFacet)) {
                return false;
            }
            if (this.toFacet == null) {
                if (other.toFacet != null) {
                    return false;
                }
            } else if (!this.toFacet.equals(other.toFacet)) {
                return false;
            }
            if (this.timeInFacetMillis != other.timeInFacetMillis) {
                return false;
            }
            if (this.facet == null) {
                if (other.facet != null) {
                    return false;
                }
            } else if (!this.facet.equals(other.facet)) {
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
            int hashCode = (((((((((getClass().getName().hashCode() + 527) * 31) + (this.fromFacet == null ? 0 : this.fromFacet.hashCode())) * 31) + (this.toFacet == null ? 0 : this.toFacet.hashCode())) * 31) + ((int) (this.timeInFacetMillis ^ (this.timeInFacetMillis >>> 32)))) * 31) + (this.facet == null ? 0 : this.facet.hashCode())) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.fromFacet != null) {
                output.writeMessage(1, this.fromFacet);
            }
            if (this.toFacet != null) {
                output.writeMessage(2, this.toFacet);
            }
            if (this.timeInFacetMillis != 0) {
                output.writeInt64(3, this.timeInFacetMillis);
            }
            if (this.facet != null) {
                output.writeMessage(4, this.facet);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.fromFacet != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(1, this.fromFacet);
            }
            if (this.toFacet != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(2, this.toFacet);
            }
            if (this.timeInFacetMillis != 0) {
                size += CodedOutputByteBufferNano.computeInt64Size(3, this.timeInFacetMillis);
            }
            if (this.facet != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(4, this.facet);
            }
            return size;
        }

        public FacetChange mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        if (this.fromFacet == null) {
                            this.fromFacet = new Facet();
                        }
                        input.readMessage(this.fromFacet);
                        continue;
                    case LogSource.GOOGLE_ANALYTICS /*18*/:
                        if (this.toFacet == null) {
                            this.toFacet = new Facet();
                        }
                        input.readMessage(this.toFacet);
                        continue;
                    case LogSource.LB_C /*24*/:
                        this.timeInFacetMillis = input.readInt64();
                        continue;
                    case LogSource.NOVA /*34*/:
                        if (this.facet == null) {
                            this.facet = new Facet();
                        }
                        input.readMessage(this.facet);
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

        public static FacetChange parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (FacetChange) MessageNano.mergeFrom(new FacetChange(), data);
        }

        public static FacetChange parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new FacetChange().mergeFrom(input);
        }
    }

    public static final class FrxStateChanged extends ExtendableMessageNano<FrxStateChanged> {
        private static volatile FrxStateChanged[] _emptyArray;
        public int event;
        public int fromState;
        public int toState;

        public interface FrxEvent {
            public static final int ALL_PERMISSIONS_GRANTED = 22;
            public static final int APP_INSTALLATION_ALLOWED = 11;
            public static final int APP_INSTALLATION_CANCELLED = 12;
            public static final int APP_INSTALLATION_FAILED = 13;
            public static final int APP_UP_TO_DATE = 14;
            public static final int CAR_STARTED_MOVING = 10;
            public static final int CONNECTECTED_TO_CAR = 1;
            public static final int CONNECTION_ERROR = 17;
            public static final int CONNECTION_TO_CAR_ALLOWED = 8;
            public static final int CONNECTION_TO_CAR_CANCELLED = 19;
            public static final int CONNECTION_TO_CAR_NOT_ALLOWED = 9;
            public static final int COUNTRY_NOT_WHITELISTED = 4;
            public static final int COUNTRY_WHITELISTED = 3;
            public static final int DISCONNECTED_FROM_CAR = 2;
            public static final int FIRST_ACTIVATION = 23;
            public static final int GEARHEAD_INSTALLED = 5;
            public static final int OPT_IN_ACCEPTED = 16;
            public static final int OPT_IN_CANCELLED = 15;
            public static final int PERMISSION_DENIED = 21;
            public static final int PERMISSION_GRANTED = 20;
            public static final int PHONE_BLACKLISTED = 6;
            public static final int PHONE_NOT_BLACKLISTED = 7;
            public static final int UNKNOWN_EVENT = 0;
            public static final int USER_EXIT = 18;
        }

        public interface FrxState {
            public static final int AUTHORIZE_CAR_CONNECTION = 4;
            public static final int CAR_MOVING = 9;
            public static final int CHECK_COUNTRY_WHITELIST = 2;
            public static final int CHECK_PERMISSIONS = 13;
            public static final int CHECK_PHONE_BLACKLIST = 3;
            public static final int DOWNLOAD_APP = 5;
            public static final int DOWNLOAD_APP_RETRY = 6;
            public static final int INSTALL_APP = 7;
            public static final int OPT_IN = 8;
            public static final int SETUP_DONE = 12;
            public static final int SETUP_ERROR = 10;
            public static final int SETUP_FAILED = 11;
            public static final int UNKNOWN_STATE = 0;
            public static final int WAIT_FOR_CAR_CONNECTION = 1;
        }

        public static FrxStateChanged[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new FrxStateChanged[0];
                    }
                }
            }
            return _emptyArray;
        }

        public FrxStateChanged() {
            clear();
        }

        public FrxStateChanged clear() {
            this.fromState = 0;
            this.toState = 0;
            this.event = 0;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof FrxStateChanged)) {
                return false;
            }
            FrxStateChanged other = (FrxStateChanged) o;
            if (this.fromState != other.fromState || this.toState != other.toState || this.event != other.event) {
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
            int hashCode = (((((((getClass().getName().hashCode() + 527) * 31) + this.fromState) * 31) + this.toState) * 31) + this.event) * 31;
            int hashCode2 = (this.unknownFieldData == null || this.unknownFieldData.isEmpty()) ? 0 : this.unknownFieldData.hashCode();
            return hashCode + hashCode2;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.fromState != 0) {
                output.writeInt32(1, this.fromState);
            }
            if (this.toState != 0) {
                output.writeInt32(2, this.toState);
            }
            if (this.event != 0) {
                output.writeInt32(3, this.event);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.fromState != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(1, this.fromState);
            }
            if (this.toState != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(2, this.toState);
            }
            if (this.event != 0) {
                return size + CodedOutputByteBufferNano.computeInt32Size(3, this.event);
            }
            return size;
        }

        public FrxStateChanged mergeFrom(CodedInputByteBufferNano input) throws IOException {
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
                                this.fromState = value;
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
                                this.toState = value;
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
                            case Type.OVEN_FRESH /*15*/:
                            case LogSource.GMS_CORE_PEOPLE /*16*/:
                            case LogSource.LE /*17*/:
                            case LogSource.GOOGLE_ANALYTICS /*18*/:
                            case LogSource.LB_D /*19*/:
                            case LogSource.ANDROID_GSA /*20*/:
                            case LogSource.LB_T /*21*/:
                            case LogSource.PERSONAL_LOGGER /*22*/:
                            case LogSource.GMS_CORE_WALLET_MERCHANT_ERROR /*23*/:
                                this.event = value;
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

        public static FrxStateChanged parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (FrxStateChanged) MessageNano.mergeFrom(new FrxStateChanged(), data);
        }

        public static FrxStateChanged parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new FrxStateChanged().mergeFrom(input);
        }
    }

    public static final class SslEvent extends ExtendableMessageNano<SslEvent> {
        private static volatile SslEvent[] _emptyArray;
        public String peerCertSubjectName;
        public String validTimeNotAfter;
        public String validTimeNotBefore;

        public static SslEvent[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new SslEvent[0];
                    }
                }
            }
            return _emptyArray;
        }

        public SslEvent() {
            clear();
        }

        public SslEvent clear() {
            this.peerCertSubjectName = BuildConfig.VERSION_NAME;
            this.validTimeNotBefore = BuildConfig.VERSION_NAME;
            this.validTimeNotAfter = BuildConfig.VERSION_NAME;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof SslEvent)) {
                return false;
            }
            SslEvent other = (SslEvent) o;
            if (this.peerCertSubjectName == null) {
                if (other.peerCertSubjectName != null) {
                    return false;
                }
            } else if (!this.peerCertSubjectName.equals(other.peerCertSubjectName)) {
                return false;
            }
            if (this.validTimeNotBefore == null) {
                if (other.validTimeNotBefore != null) {
                    return false;
                }
            } else if (!this.validTimeNotBefore.equals(other.validTimeNotBefore)) {
                return false;
            }
            if (this.validTimeNotAfter == null) {
                if (other.validTimeNotAfter != null) {
                    return false;
                }
            } else if (!this.validTimeNotAfter.equals(other.validTimeNotAfter)) {
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
            int hashCode = (((((((getClass().getName().hashCode() + 527) * 31) + (this.peerCertSubjectName == null ? 0 : this.peerCertSubjectName.hashCode())) * 31) + (this.validTimeNotBefore == null ? 0 : this.validTimeNotBefore.hashCode())) * 31) + (this.validTimeNotAfter == null ? 0 : this.validTimeNotAfter.hashCode())) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (!this.peerCertSubjectName.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(1, this.peerCertSubjectName);
            }
            if (!this.validTimeNotBefore.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(2, this.validTimeNotBefore);
            }
            if (!this.validTimeNotAfter.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(3, this.validTimeNotAfter);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (!this.peerCertSubjectName.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.peerCertSubjectName);
            }
            if (!this.validTimeNotBefore.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(2, this.validTimeNotBefore);
            }
            if (this.validTimeNotAfter.equals(BuildConfig.VERSION_NAME)) {
                return size;
            }
            return size + CodedOutputByteBufferNano.computeStringSize(3, this.validTimeNotAfter);
        }

        public SslEvent mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        this.peerCertSubjectName = input.readString();
                        continue;
                    case LogSource.GOOGLE_ANALYTICS /*18*/:
                        this.validTimeNotBefore = input.readString();
                        continue;
                    case LogSource.ANDROID_CAMERA /*26*/:
                        this.validTimeNotAfter = input.readString();
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

        public static SslEvent parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (SslEvent) MessageNano.mergeFrom(new SslEvent(), data);
        }

        public static SslEvent parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new SslEvent().mergeFrom(input);
        }
    }

    public static final class TouchEvent extends ExtendableMessageNano<TouchEvent> {
        private static volatile TouchEvent[] _emptyArray;
        public int activeFacet;
        public int touchPointX;
        public int touchPointY;

        public static TouchEvent[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new TouchEvent[0];
                    }
                }
            }
            return _emptyArray;
        }

        public TouchEvent() {
            clear();
        }

        public TouchEvent clear() {
            this.touchPointX = 0;
            this.touchPointY = 0;
            this.activeFacet = 0;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof TouchEvent)) {
                return false;
            }
            TouchEvent other = (TouchEvent) o;
            if (this.touchPointX != other.touchPointX || this.touchPointY != other.touchPointY || this.activeFacet != other.activeFacet) {
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
            int hashCode = (((((((getClass().getName().hashCode() + 527) * 31) + this.touchPointX) * 31) + this.touchPointY) * 31) + this.activeFacet) * 31;
            int hashCode2 = (this.unknownFieldData == null || this.unknownFieldData.isEmpty()) ? 0 : this.unknownFieldData.hashCode();
            return hashCode + hashCode2;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.touchPointX != 0) {
                output.writeInt32(1, this.touchPointX);
            }
            if (this.touchPointY != 0) {
                output.writeInt32(2, this.touchPointY);
            }
            if (this.activeFacet != 0) {
                output.writeInt32(3, this.activeFacet);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.touchPointX != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(1, this.touchPointX);
            }
            if (this.touchPointY != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(2, this.touchPointY);
            }
            if (this.activeFacet != 0) {
                return size + CodedOutputByteBufferNano.computeInt32Size(3, this.activeFacet);
            }
            return size;
        }

        public TouchEvent mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_GET_LINK /*8*/:
                        this.touchPointX = input.readInt32();
                        continue;
                    case LogSource.GMS_CORE_PEOPLE /*16*/:
                        this.touchPointY = input.readInt32();
                        continue;
                    case LogSource.LB_C /*24*/:
                        int value = input.readInt32();
                        switch (value) {
                            case Action.UNKNOWN /*0*/:
                            case TimeSelection.Type.TEMPORARY /*1*/:
                            case TimeSelection.Type.INDEFINITELY /*2*/:
                            case TimeSelection.Type.CUSTOM /*3*/:
                            case OvenFreshResult.NO_ACCOUNTS /*4*/:
                            case Type.ADD_NEW_SHARES /*5*/:
                                this.activeFacet = value;
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

        public static TouchEvent parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (TouchEvent) MessageNano.mergeFrom(new TouchEvent(), data);
        }

        public static TouchEvent parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new TouchEvent().mergeFrom(input);
        }
    }

    public static final class VoiceAction extends ExtendableMessageNano<VoiceAction> {
        private static volatile VoiceAction[] _emptyArray;
        public int cause;

        public interface InteractionCause {
            public static final int BUTTON = 2;
            public static final int SCREEN = 1;
            public static final int UNKNOWN_CAUSE = 0;
            public static final int VOICE = 3;
        }

        public static VoiceAction[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new VoiceAction[0];
                    }
                }
            }
            return _emptyArray;
        }

        public VoiceAction() {
            clear();
        }

        public VoiceAction clear() {
            this.cause = 0;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof VoiceAction)) {
                return false;
            }
            VoiceAction other = (VoiceAction) o;
            if (this.cause != other.cause) {
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
            int hashCode = (((getClass().getName().hashCode() + 527) * 31) + this.cause) * 31;
            int hashCode2 = (this.unknownFieldData == null || this.unknownFieldData.isEmpty()) ? 0 : this.unknownFieldData.hashCode();
            return hashCode + hashCode2;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.cause != 0) {
                output.writeInt32(1, this.cause);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.cause != 0) {
                return size + CodedOutputByteBufferNano.computeInt32Size(1, this.cause);
            }
            return size;
        }

        public VoiceAction mergeFrom(CodedInputByteBufferNano input) throws IOException {
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
                                this.cause = value;
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

        public static VoiceAction parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (VoiceAction) MessageNano.mergeFrom(new VoiceAction(), data);
        }

        public static VoiceAction parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new VoiceAction().mergeFrom(input);
        }
    }
}
