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
import com.google.wireless.android.play.playlog.proto.UserLocation.OvenFreshEvent.OvenFreshResult;
import com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection;
import java.io.IOException;

public final class SettingState extends ExtendableMessageNano<SettingState> {
    private static volatile SettingState[] _emptyArray;
    public int availability;
    public int settingId;
    public int settingValue;

    public interface SettingAvailability {
        public static final int AVAILABLE = 1;
        public static final int BLOCKED = 3;
        public static final int DISREGARDED = 2;
        public static final int HIDDEN = 4;
        public static final int INACCESSIBLE = 5;
        public static final int PARENT_SETTING_DISABLED = 6;
        public static final int UNKNOWN = 0;
    }

    public static SettingState[] emptyArray() {
        if (_emptyArray == null) {
            synchronized (InternalNano.LAZY_INIT_LOCK) {
                if (_emptyArray == null) {
                    _emptyArray = new SettingState[0];
                }
            }
        }
        return _emptyArray;
    }

    public SettingState() {
        clear();
    }

    public SettingState clear() {
        this.settingId = 0;
        this.settingValue = 0;
        this.availability = 0;
        this.unknownFieldData = null;
        this.cachedSize = -1;
        return this;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof SettingState)) {
            return false;
        }
        SettingState other = (SettingState) o;
        if (this.settingId != other.settingId || this.settingValue != other.settingValue || this.availability != other.availability) {
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
        int hashCode = (((((((getClass().getName().hashCode() + 527) * 31) + this.settingId) * 31) + this.settingValue) * 31) + this.availability) * 31;
        int hashCode2 = (this.unknownFieldData == null || this.unknownFieldData.isEmpty()) ? 0 : this.unknownFieldData.hashCode();
        return hashCode + hashCode2;
    }

    public void writeTo(CodedOutputByteBufferNano output) throws IOException {
        if (this.settingId != 0) {
            output.writeInt32(1, this.settingId);
        }
        if (this.settingValue != 0) {
            output.writeInt32(2, this.settingValue);
        }
        if (this.availability != 0) {
            output.writeInt32(3, this.availability);
        }
        super.writeTo(output);
    }

    protected int computeSerializedSize() {
        int size = super.computeSerializedSize();
        if (this.settingId != 0) {
            size += CodedOutputByteBufferNano.computeInt32Size(1, this.settingId);
        }
        if (this.settingValue != 0) {
            size += CodedOutputByteBufferNano.computeInt32Size(2, this.settingValue);
        }
        if (this.availability != 0) {
            return size + CodedOutputByteBufferNano.computeInt32Size(3, this.availability);
        }
        return size;
    }

    public SettingState mergeFrom(CodedInputByteBufferNano input) throws IOException {
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
                        case TimeSelection.Type.CUSTOM /*3*/:
                            this.settingValue = value;
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
                            this.availability = value;
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

    public static SettingState parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
        return (SettingState) MessageNano.mergeFrom(new SettingState(), data);
    }

    public static SettingState parseFrom(CodedInputByteBufferNano input) throws IOException {
        return new SettingState().mergeFrom(input);
    }
}
