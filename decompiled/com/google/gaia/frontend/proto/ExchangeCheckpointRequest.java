package com.google.gaia.frontend.proto;

import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.ExtendableMessageNano;
import com.google.protobuf.nano.InternalNano;
import com.google.protobuf.nano.InvalidProtocolBufferNanoException;
import com.google.protobuf.nano.MessageNano;
import com.google.protobuf.nano.WireFormatNano;
import com.google.security.cryptauth.lib.risk.DeviceRiskSignals;
import com.google.wireless.android.play.playlog.proto.ClientAnalytics.LogRequest.LogSource;
import com.google.wireless.android.play.playlog.proto.UserLocation.LaunchSettingsEvent.Action;
import com.google.wireless.android.play.playlog.proto.UserLocation.LocationSharingEvent.Type;
import com.google.wireless.android.play.playlog.proto.UserLocation.OvenFreshEvent.OvenFreshResult;
import com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection;
import java.io.IOException;

public final class ExchangeCheckpointRequest extends ExtendableMessageNano<ExchangeCheckpointRequest> {
    private static volatile ExchangeCheckpointRequest[] _emptyArray;
    public CheckpointInfo[] checkpointInfo;
    public int flowType;
    public HttpConnectionContext httpContext;
    public DeviceRiskSignals targetDeviceSignalsV2;

    public static ExchangeCheckpointRequest[] emptyArray() {
        if (_emptyArray == null) {
            synchronized (InternalNano.LAZY_INIT_LOCK) {
                if (_emptyArray == null) {
                    _emptyArray = new ExchangeCheckpointRequest[0];
                }
            }
        }
        return _emptyArray;
    }

    public ExchangeCheckpointRequest() {
        clear();
    }

    public ExchangeCheckpointRequest clear() {
        this.checkpointInfo = CheckpointInfo.emptyArray();
        this.flowType = 1;
        this.httpContext = null;
        this.targetDeviceSignalsV2 = null;
        this.unknownFieldData = null;
        this.cachedSize = -1;
        return this;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ExchangeCheckpointRequest)) {
            return false;
        }
        ExchangeCheckpointRequest other = (ExchangeCheckpointRequest) o;
        if (!InternalNano.equals(this.checkpointInfo, other.checkpointInfo) || this.flowType != other.flowType) {
            return false;
        }
        if (this.httpContext == null) {
            if (other.httpContext != null) {
                return false;
            }
        } else if (!this.httpContext.equals(other.httpContext)) {
            return false;
        }
        if (this.targetDeviceSignalsV2 == null) {
            if (other.targetDeviceSignalsV2 != null) {
                return false;
            }
        } else if (!this.targetDeviceSignalsV2.equals(other.targetDeviceSignalsV2)) {
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
        int hashCode = (((((((((getClass().getName().hashCode() + 527) * 31) + InternalNano.hashCode(this.checkpointInfo)) * 31) + this.flowType) * 31) + (this.httpContext == null ? 0 : this.httpContext.hashCode())) * 31) + (this.targetDeviceSignalsV2 == null ? 0 : this.targetDeviceSignalsV2.hashCode())) * 31;
        if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
            i = this.unknownFieldData.hashCode();
        }
        return hashCode + i;
    }

    public void writeTo(CodedOutputByteBufferNano output) throws IOException {
        if (this.checkpointInfo != null && this.checkpointInfo.length > 0) {
            for (CheckpointInfo element : this.checkpointInfo) {
                if (element != null) {
                    output.writeMessage(1, element);
                }
            }
        }
        if (this.flowType != 1) {
            output.writeInt32(2, this.flowType);
        }
        if (this.httpContext != null) {
            output.writeMessage(3, this.httpContext);
        }
        if (this.targetDeviceSignalsV2 != null) {
            output.writeMessage(4, this.targetDeviceSignalsV2);
        }
        super.writeTo(output);
    }

    protected int computeSerializedSize() {
        int size = super.computeSerializedSize();
        if (this.checkpointInfo != null && this.checkpointInfo.length > 0) {
            for (CheckpointInfo element : this.checkpointInfo) {
                if (element != null) {
                    size += CodedOutputByteBufferNano.computeMessageSize(1, element);
                }
            }
        }
        if (this.flowType != 1) {
            size += CodedOutputByteBufferNano.computeInt32Size(2, this.flowType);
        }
        if (this.httpContext != null) {
            size += CodedOutputByteBufferNano.computeMessageSize(3, this.httpContext);
        }
        if (this.targetDeviceSignalsV2 != null) {
            return size + CodedOutputByteBufferNano.computeMessageSize(4, this.targetDeviceSignalsV2);
        }
        return size;
    }

    public ExchangeCheckpointRequest mergeFrom(CodedInputByteBufferNano input) throws IOException {
        while (true) {
            int tag = input.readTag();
            switch (tag) {
                case Action.UNKNOWN /*0*/:
                    break;
                case Type.TAP_ABOUT /*10*/:
                    int i;
                    int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 10);
                    if (this.checkpointInfo == null) {
                        i = 0;
                    } else {
                        i = this.checkpointInfo.length;
                    }
                    CheckpointInfo[] newArray = new CheckpointInfo[(i + arrayLength)];
                    if (i != 0) {
                        System.arraycopy(this.checkpointInfo, 0, newArray, 0, i);
                    }
                    while (i < newArray.length - 1) {
                        newArray[i] = new CheckpointInfo();
                        input.readMessage(newArray[i]);
                        input.readTag();
                        i++;
                    }
                    newArray[i] = new CheckpointInfo();
                    input.readMessage(newArray[i]);
                    this.checkpointInfo = newArray;
                    continue;
                case LogSource.GMS_CORE_PEOPLE /*16*/:
                    int value = input.readInt32();
                    switch (value) {
                        case TimeSelection.Type.TEMPORARY /*1*/:
                        case TimeSelection.Type.INDEFINITELY /*2*/:
                        case TimeSelection.Type.CUSTOM /*3*/:
                        case OvenFreshResult.NO_ACCOUNTS /*4*/:
                            this.flowType = value;
                            break;
                        default:
                            continue;
                    }
                case LogSource.ANDROID_CAMERA /*26*/:
                    if (this.httpContext == null) {
                        this.httpContext = new HttpConnectionContext();
                    }
                    input.readMessage(this.httpContext);
                    continue;
                case LogSource.NOVA /*34*/:
                    if (this.targetDeviceSignalsV2 == null) {
                        this.targetDeviceSignalsV2 = new DeviceRiskSignals();
                    }
                    input.readMessage(this.targetDeviceSignalsV2);
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

    public static ExchangeCheckpointRequest parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
        return (ExchangeCheckpointRequest) MessageNano.mergeFrom(new ExchangeCheckpointRequest(), data);
    }

    public static ExchangeCheckpointRequest parseFrom(CodedInputByteBufferNano input) throws IOException {
        return new ExchangeCheckpointRequest().mergeFrom(input);
    }
}
