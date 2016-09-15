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

public final class SettingChangeRequest extends ExtendableMessageNano<SettingChangeRequest> {
    private static volatile SettingChangeRequest[] _emptyArray;
    public int settingId;
    public int targetValue;

    public static SettingChangeRequest[] emptyArray() {
        if (_emptyArray == null) {
            synchronized (InternalNano.LAZY_INIT_LOCK) {
                if (_emptyArray == null) {
                    _emptyArray = new SettingChangeRequest[0];
                }
            }
        }
        return _emptyArray;
    }

    public SettingChangeRequest() {
        clear();
    }

    public SettingChangeRequest clear() {
        this.settingId = 0;
        this.targetValue = 0;
        this.unknownFieldData = null;
        this.cachedSize = -1;
        return this;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof SettingChangeRequest)) {
            return false;
        }
        SettingChangeRequest other = (SettingChangeRequest) o;
        if (this.settingId != other.settingId || this.targetValue != other.targetValue) {
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
        int hashCode = (((((getClass().getName().hashCode() + 527) * 31) + this.settingId) * 31) + this.targetValue) * 31;
        int hashCode2 = (this.unknownFieldData == null || this.unknownFieldData.isEmpty()) ? 0 : this.unknownFieldData.hashCode();
        return hashCode + hashCode2;
    }

    public void writeTo(CodedOutputByteBufferNano output) throws IOException {
        if (this.settingId != 0) {
            output.writeInt32(1, this.settingId);
        }
        if (this.targetValue != 0) {
            output.writeInt32(2, this.targetValue);
        }
        super.writeTo(output);
    }

    protected int computeSerializedSize() {
        int size = super.computeSerializedSize();
        if (this.settingId != 0) {
            size += CodedOutputByteBufferNano.computeInt32Size(1, this.settingId);
        }
        if (this.targetValue != 0) {
            return size + CodedOutputByteBufferNano.computeInt32Size(2, this.targetValue);
        }
        return size;
    }

    public SettingChangeRequest mergeFrom(CodedInputByteBufferNano input) throws IOException {
        while (true) {
            int tag = input.readTag();
            switch (tag) {
                case Action.UNKNOWN /*0*/:
                    break;
                case Type.TAP_GET_LINK /*8*/:
                    this.settingId = input.readInt32();
                    continue;
                case LogSource.GMS_CORE_PEOPLE /*16*/:
                    int value = input.readInt32();
                    switch (value) {
                        case Action.UNKNOWN /*0*/:
                        case TimeSelection.Type.TEMPORARY /*1*/:
                        case TimeSelection.Type.INDEFINITELY /*2*/:
                        case TimeSelection.Type.CUSTOM /*3*/:
                            this.targetValue = value;
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

    public static SettingChangeRequest parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
        return (SettingChangeRequest) MessageNano.mergeFrom(new SettingChangeRequest(), data);
    }

    public static SettingChangeRequest parseFrom(CodedInputByteBufferNano input) throws IOException {
        return new SettingChangeRequest().mergeFrom(input);
    }
}
