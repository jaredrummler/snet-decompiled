package com.google.personalization.context.model.udcfootprintssettings;

import com.google.personalization.context.ContextExtension;
import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.ExtendableMessageNano;
import com.google.protobuf.nano.Extension;
import com.google.protobuf.nano.InternalNano;
import com.google.protobuf.nano.InvalidProtocolBufferNanoException;
import com.google.protobuf.nano.MessageNano;
import com.google.protobuf.nano.WireFormatNano;
import com.google.wireless.android.play.playlog.proto.ClientAnalytics.LogRequest.LogSource;
import com.google.wireless.android.play.playlog.proto.UserLocation.LaunchSettingsEvent.Action;
import com.google.wireless.android.play.playlog.proto.UserLocation.LocationSharingEvent.Type;
import java.io.IOException;

public final class UdcFootprintsSettingsModel extends ExtendableMessageNano<UdcFootprintsSettingsModel> {
    private static final UdcFootprintsSettingsModel[] EMPTY_ARRAY;
    public static final Extension<ContextExtension, UdcFootprintsSettingsModel> data;
    public UdcSetting[] setting;

    public static final class SettingAvailability extends ExtendableMessageNano<SettingAvailability> {
        private static volatile SettingAvailability[] _emptyArray;
        public boolean canDisable;
        public boolean canEnable;
        public boolean valueVisible;

        public static SettingAvailability[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new SettingAvailability[0];
                    }
                }
            }
            return _emptyArray;
        }

        public SettingAvailability() {
            clear();
        }

        public SettingAvailability clear() {
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
            if (!(o instanceof SettingAvailability)) {
                return false;
            }
            SettingAvailability other = (SettingAvailability) o;
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

        public SettingAvailability mergeFrom(CodedInputByteBufferNano input) throws IOException {
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

        public static SettingAvailability parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (SettingAvailability) MessageNano.mergeFrom(new SettingAvailability(), data);
        }

        public static SettingAvailability parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new SettingAvailability().mergeFrom(input);
        }
    }

    public static final class UdcSetting extends ExtendableMessageNano<UdcSetting> {
        private static volatile UdcSetting[] _emptyArray;
        public SettingAvailability avaliability;
        public int settingId;
        public int value;

        public static UdcSetting[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new UdcSetting[0];
                    }
                }
            }
            return _emptyArray;
        }

        public UdcSetting() {
            clear();
        }

        public UdcSetting clear() {
            this.settingId = 0;
            this.value = 0;
            this.avaliability = null;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof UdcSetting)) {
                return false;
            }
            UdcSetting other = (UdcSetting) o;
            if (this.settingId != other.settingId || this.value != other.value) {
                return false;
            }
            if (this.avaliability == null) {
                if (other.avaliability != null) {
                    return false;
                }
            } else if (!this.avaliability.equals(other.avaliability)) {
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
            int hashCode = (((((((getClass().getName().hashCode() + 527) * 31) + this.settingId) * 31) + this.value) * 31) + (this.avaliability == null ? 0 : this.avaliability.hashCode())) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.settingId != 0) {
                output.writeInt32(1, this.settingId);
            }
            if (this.value != 0) {
                output.writeInt32(2, this.value);
            }
            if (this.avaliability != null) {
                output.writeMessage(3, this.avaliability);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.settingId != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(1, this.settingId);
            }
            if (this.value != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(2, this.value);
            }
            if (this.avaliability != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(3, this.avaliability);
            }
            return size;
        }

        public UdcSetting mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_GET_LINK /*8*/:
                        this.settingId = input.readInt32();
                        continue;
                    case LogSource.GMS_CORE_PEOPLE /*16*/:
                        this.value = input.readInt32();
                        continue;
                    case LogSource.ANDROID_CAMERA /*26*/:
                        if (this.avaliability == null) {
                            this.avaliability = new SettingAvailability();
                        }
                        input.readMessage(this.avaliability);
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

        public static UdcSetting parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (UdcSetting) MessageNano.mergeFrom(new UdcSetting(), data);
        }

        public static UdcSetting parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new UdcSetting().mergeFrom(input);
        }
    }

    static {
        data = Extension.createMessageTyped(11, UdcFootprintsSettingsModel.class, 810980490);
        EMPTY_ARRAY = new UdcFootprintsSettingsModel[0];
    }

    public static UdcFootprintsSettingsModel[] emptyArray() {
        return EMPTY_ARRAY;
    }

    public UdcFootprintsSettingsModel() {
        clear();
    }

    public UdcFootprintsSettingsModel clear() {
        this.setting = UdcSetting.emptyArray();
        this.unknownFieldData = null;
        this.cachedSize = -1;
        return this;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof UdcFootprintsSettingsModel)) {
            return false;
        }
        UdcFootprintsSettingsModel other = (UdcFootprintsSettingsModel) o;
        if (!InternalNano.equals(this.setting, other.setting)) {
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
        int hashCode = (((getClass().getName().hashCode() + 527) * 31) + InternalNano.hashCode(this.setting)) * 31;
        int hashCode2 = (this.unknownFieldData == null || this.unknownFieldData.isEmpty()) ? 0 : this.unknownFieldData.hashCode();
        return hashCode + hashCode2;
    }

    public void writeTo(CodedOutputByteBufferNano output) throws IOException {
        if (this.setting != null && this.setting.length > 0) {
            for (UdcSetting element : this.setting) {
                if (element != null) {
                    output.writeMessage(1, element);
                }
            }
        }
        super.writeTo(output);
    }

    protected int computeSerializedSize() {
        int size = super.computeSerializedSize();
        if (this.setting != null && this.setting.length > 0) {
            for (UdcSetting element : this.setting) {
                if (element != null) {
                    size += CodedOutputByteBufferNano.computeMessageSize(1, element);
                }
            }
        }
        return size;
    }

    public UdcFootprintsSettingsModel mergeFrom(CodedInputByteBufferNano input) throws IOException {
        while (true) {
            int tag = input.readTag();
            switch (tag) {
                case Action.UNKNOWN /*0*/:
                    break;
                case Type.TAP_ABOUT /*10*/:
                    int i;
                    int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 10);
                    if (this.setting == null) {
                        i = 0;
                    } else {
                        i = this.setting.length;
                    }
                    UdcSetting[] newArray = new UdcSetting[(i + arrayLength)];
                    if (i != 0) {
                        System.arraycopy(this.setting, 0, newArray, 0, i);
                    }
                    while (i < newArray.length - 1) {
                        newArray[i] = new UdcSetting();
                        input.readMessage(newArray[i]);
                        input.readTag();
                        i++;
                    }
                    newArray[i] = new UdcSetting();
                    input.readMessage(newArray[i]);
                    this.setting = newArray;
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

    public static UdcFootprintsSettingsModel parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
        return (UdcFootprintsSettingsModel) MessageNano.mergeFrom(new UdcFootprintsSettingsModel(), data);
    }

    public static UdcFootprintsSettingsModel parseFrom(CodedInputByteBufferNano input) throws IOException {
        return new UdcFootprintsSettingsModel().mergeFrom(input);
    }
}
