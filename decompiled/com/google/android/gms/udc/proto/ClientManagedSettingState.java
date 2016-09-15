package com.google.android.gms.udc.proto;

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

public final class ClientManagedSettingState extends ExtendableMessageNano<ClientManagedSettingState> {
    private static volatile ClientManagedSettingState[] _emptyArray;
    public Availability settingAvailability;
    public int settingId;
    public int settingValue;
    public int status;

    public static final class Availability extends ExtendableMessageNano<Availability> {
        private static volatile Availability[] _emptyArray;
        public boolean canDisable;
        public boolean canEnable;
        public boolean valueVisible;

        public static Availability[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new Availability[0];
                    }
                }
            }
            return _emptyArray;
        }

        public Availability() {
            clear();
        }

        public Availability clear() {
            this.canEnable = false;
            this.canDisable = false;
            this.valueVisible = false;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof Availability)) {
                return false;
            }
            Availability other = (Availability) o;
            if (this.canEnable != other.canEnable || this.canDisable != other.canDisable || this.valueVisible != other.valueVisible) {
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
            int hashCode = (((getClass().getName().hashCode() + 527) * 31) + (this.canEnable ? 1231 : 1237)) * 31;
            if (this.canDisable) {
                i = 1231;
            } else {
                i = 1237;
            }
            i = (hashCode + i) * 31;
            if (!this.valueVisible) {
                i2 = 1237;
            }
            i2 = (i + i2) * 31;
            i = (this.unknownFieldData == null || this.unknownFieldData.isEmpty()) ? 0 : this.unknownFieldData.hashCode();
            return i2 + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.canEnable) {
                output.writeBool(1, this.canEnable);
            }
            if (this.canDisable) {
                output.writeBool(2, this.canDisable);
            }
            if (this.valueVisible) {
                output.writeBool(3, this.valueVisible);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.canEnable) {
                size += CodedOutputByteBufferNano.computeBoolSize(1, this.canEnable);
            }
            if (this.canDisable) {
                size += CodedOutputByteBufferNano.computeBoolSize(2, this.canDisable);
            }
            if (this.valueVisible) {
                return size + CodedOutputByteBufferNano.computeBoolSize(3, this.valueVisible);
            }
            return size;
        }

        public Availability mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_GET_LINK /*8*/:
                        this.canEnable = input.readBool();
                        continue;
                    case LogSource.GMS_CORE_PEOPLE /*16*/:
                        this.canDisable = input.readBool();
                        continue;
                    case LogSource.LB_C /*24*/:
                        this.valueVisible = input.readBool();
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

        public static Availability parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (Availability) MessageNano.mergeFrom(new Availability(), data);
        }

        public static Availability parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new Availability().mergeFrom(input);
        }
    }

    public interface Status {
        public static final int OK = 1;
        public static final int READ_FAILED = 2;
        public static final int UNKNOWN = 0;
    }

    public static ClientManagedSettingState[] emptyArray() {
        if (_emptyArray == null) {
            synchronized (InternalNano.LAZY_INIT_LOCK) {
                if (_emptyArray == null) {
                    _emptyArray = new ClientManagedSettingState[0];
                }
            }
        }
        return _emptyArray;
    }

    public ClientManagedSettingState() {
        clear();
    }

    public ClientManagedSettingState clear() {
        this.settingId = 0;
        this.status = 0;
        this.settingValue = 0;
        this.settingAvailability = null;
        this.unknownFieldData = null;
        this.cachedSize = -1;
        return this;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ClientManagedSettingState)) {
            return false;
        }
        ClientManagedSettingState other = (ClientManagedSettingState) o;
        if (this.settingId != other.settingId || this.status != other.status || this.settingValue != other.settingValue) {
            return false;
        }
        if (this.settingAvailability == null) {
            if (other.settingAvailability != null) {
                return false;
            }
        } else if (!this.settingAvailability.equals(other.settingAvailability)) {
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
        int hashCode = (((((((((getClass().getName().hashCode() + 527) * 31) + this.settingId) * 31) + this.status) * 31) + this.settingValue) * 31) + (this.settingAvailability == null ? 0 : this.settingAvailability.hashCode())) * 31;
        if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
            i = this.unknownFieldData.hashCode();
        }
        return hashCode + i;
    }

    public void writeTo(CodedOutputByteBufferNano output) throws IOException {
        if (this.settingId != 0) {
            output.writeInt32(1, this.settingId);
        }
        if (this.status != 0) {
            output.writeInt32(2, this.status);
        }
        if (this.settingValue != 0) {
            output.writeInt32(3, this.settingValue);
        }
        if (this.settingAvailability != null) {
            output.writeMessage(4, this.settingAvailability);
        }
        super.writeTo(output);
    }

    protected int computeSerializedSize() {
        int size = super.computeSerializedSize();
        if (this.settingId != 0) {
            size += CodedOutputByteBufferNano.computeInt32Size(1, this.settingId);
        }
        if (this.status != 0) {
            size += CodedOutputByteBufferNano.computeInt32Size(2, this.status);
        }
        if (this.settingValue != 0) {
            size += CodedOutputByteBufferNano.computeInt32Size(3, this.settingValue);
        }
        if (this.settingAvailability != null) {
            return size + CodedOutputByteBufferNano.computeMessageSize(4, this.settingAvailability);
        }
        return size;
    }

    public ClientManagedSettingState mergeFrom(CodedInputByteBufferNano input) throws IOException {
        while (true) {
            int tag = input.readTag();
            int value;
            switch (tag) {
                case Action.UNKNOWN /*0*/:
                    break;
                case Type.TAP_GET_LINK /*8*/:
                    this.settingId = input.readInt32();
                    continue;
                case LogSource.GMS_CORE_PEOPLE /*16*/:
                    value = input.readInt32();
                    switch (value) {
                        case Action.UNKNOWN /*0*/:
                        case TimeSelection.Type.TEMPORARY /*1*/:
                        case TimeSelection.Type.INDEFINITELY /*2*/:
                            this.status = value;
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
                            this.settingValue = value;
                            break;
                        default:
                            continue;
                    }
                case LogSource.NOVA /*34*/:
                    if (this.settingAvailability == null) {
                        this.settingAvailability = new Availability();
                    }
                    input.readMessage(this.settingAvailability);
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

    public static ClientManagedSettingState parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
        return (ClientManagedSettingState) MessageNano.mergeFrom(new ClientManagedSettingState(), data);
    }

    public static ClientManagedSettingState parseFrom(CodedInputByteBufferNano input) throws IOException {
        return new ClientManagedSettingState().mergeFrom(input);
    }
}
