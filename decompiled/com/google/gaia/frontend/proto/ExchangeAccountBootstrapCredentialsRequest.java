package com.google.gaia.frontend.proto;

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

public final class ExchangeAccountBootstrapCredentialsRequest extends ExtendableMessageNano<ExchangeAccountBootstrapCredentialsRequest> {
    private static volatile ExchangeAccountBootstrapCredentialsRequest[] _emptyArray;
    public AssertionInfo[] assertionInfo;
    public String clientId;
    public int credentialType;
    public boolean deferCredentialsAfterFallback;
    public int flowType;
    public HttpConnectionContext httpContext;
    public String locale;
    public DeviceSignalsInfo targetDeviceSignals;

    public static ExchangeAccountBootstrapCredentialsRequest[] emptyArray() {
        if (_emptyArray == null) {
            synchronized (InternalNano.LAZY_INIT_LOCK) {
                if (_emptyArray == null) {
                    _emptyArray = new ExchangeAccountBootstrapCredentialsRequest[0];
                }
            }
        }
        return _emptyArray;
    }

    public ExchangeAccountBootstrapCredentialsRequest() {
        clear();
    }

    public ExchangeAccountBootstrapCredentialsRequest clear() {
        this.assertionInfo = AssertionInfo.emptyArray();
        this.flowType = 1;
        this.httpContext = null;
        this.targetDeviceSignals = null;
        this.credentialType = 1;
        this.clientId = BuildConfig.VERSION_NAME;
        this.locale = BuildConfig.VERSION_NAME;
        this.deferCredentialsAfterFallback = false;
        this.unknownFieldData = null;
        this.cachedSize = -1;
        return this;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ExchangeAccountBootstrapCredentialsRequest)) {
            return false;
        }
        ExchangeAccountBootstrapCredentialsRequest other = (ExchangeAccountBootstrapCredentialsRequest) o;
        if (!InternalNano.equals(this.assertionInfo, other.assertionInfo) || this.flowType != other.flowType) {
            return false;
        }
        if (this.httpContext == null) {
            if (other.httpContext != null) {
                return false;
            }
        } else if (!this.httpContext.equals(other.httpContext)) {
            return false;
        }
        if (this.targetDeviceSignals == null) {
            if (other.targetDeviceSignals != null) {
                return false;
            }
        } else if (!this.targetDeviceSignals.equals(other.targetDeviceSignals)) {
            return false;
        }
        if (this.credentialType != other.credentialType) {
            return false;
        }
        if (this.clientId == null) {
            if (other.clientId != null) {
                return false;
            }
        } else if (!this.clientId.equals(other.clientId)) {
            return false;
        }
        if (this.locale == null) {
            if (other.locale != null) {
                return false;
            }
        } else if (!this.locale.equals(other.locale)) {
            return false;
        }
        if (this.deferCredentialsAfterFallback != other.deferCredentialsAfterFallback) {
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
        int hashCode = (((((((((((((((((getClass().getName().hashCode() + 527) * 31) + InternalNano.hashCode(this.assertionInfo)) * 31) + this.flowType) * 31) + (this.httpContext == null ? 0 : this.httpContext.hashCode())) * 31) + (this.targetDeviceSignals == null ? 0 : this.targetDeviceSignals.hashCode())) * 31) + this.credentialType) * 31) + (this.clientId == null ? 0 : this.clientId.hashCode())) * 31) + (this.locale == null ? 0 : this.locale.hashCode())) * 31) + (this.deferCredentialsAfterFallback ? 1231 : 1237)) * 31;
        if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
            i = this.unknownFieldData.hashCode();
        }
        return hashCode + i;
    }

    public void writeTo(CodedOutputByteBufferNano output) throws IOException {
        if (this.assertionInfo != null && this.assertionInfo.length > 0) {
            for (AssertionInfo element : this.assertionInfo) {
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
        if (this.targetDeviceSignals != null) {
            output.writeMessage(4, this.targetDeviceSignals);
        }
        if (this.credentialType != 1) {
            output.writeInt32(5, this.credentialType);
        }
        if (!this.clientId.equals(BuildConfig.VERSION_NAME)) {
            output.writeString(6, this.clientId);
        }
        if (!this.locale.equals(BuildConfig.VERSION_NAME)) {
            output.writeString(7, this.locale);
        }
        if (this.deferCredentialsAfterFallback) {
            output.writeBool(9, this.deferCredentialsAfterFallback);
        }
        super.writeTo(output);
    }

    protected int computeSerializedSize() {
        int size = super.computeSerializedSize();
        if (this.assertionInfo != null && this.assertionInfo.length > 0) {
            for (AssertionInfo element : this.assertionInfo) {
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
        if (this.targetDeviceSignals != null) {
            size += CodedOutputByteBufferNano.computeMessageSize(4, this.targetDeviceSignals);
        }
        if (this.credentialType != 1) {
            size += CodedOutputByteBufferNano.computeInt32Size(5, this.credentialType);
        }
        if (!this.clientId.equals(BuildConfig.VERSION_NAME)) {
            size += CodedOutputByteBufferNano.computeStringSize(6, this.clientId);
        }
        if (!this.locale.equals(BuildConfig.VERSION_NAME)) {
            size += CodedOutputByteBufferNano.computeStringSize(7, this.locale);
        }
        if (this.deferCredentialsAfterFallback) {
            return size + CodedOutputByteBufferNano.computeBoolSize(9, this.deferCredentialsAfterFallback);
        }
        return size;
    }

    public ExchangeAccountBootstrapCredentialsRequest mergeFrom(CodedInputByteBufferNano input) throws IOException {
        while (true) {
            int tag = input.readTag();
            int value;
            switch (tag) {
                case Action.UNKNOWN /*0*/:
                    break;
                case Type.TAP_ABOUT /*10*/:
                    int i;
                    int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 10);
                    if (this.assertionInfo == null) {
                        i = 0;
                    } else {
                        i = this.assertionInfo.length;
                    }
                    AssertionInfo[] newArray = new AssertionInfo[(i + arrayLength)];
                    if (i != 0) {
                        System.arraycopy(this.assertionInfo, 0, newArray, 0, i);
                    }
                    while (i < newArray.length - 1) {
                        newArray[i] = new AssertionInfo();
                        input.readMessage(newArray[i]);
                        input.readTag();
                        i++;
                    }
                    newArray[i] = new AssertionInfo();
                    input.readMessage(newArray[i]);
                    this.assertionInfo = newArray;
                    continue;
                case LogSource.GMS_CORE_PEOPLE /*16*/:
                    value = input.readInt32();
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
                    if (this.targetDeviceSignals == null) {
                        this.targetDeviceSignals = new DeviceSignalsInfo();
                    }
                    input.readMessage(this.targetDeviceSignals);
                    continue;
                case LogSource.COPRESENCE /*40*/:
                    value = input.readInt32();
                    switch (value) {
                        case TimeSelection.Type.TEMPORARY /*1*/:
                        case TimeSelection.Type.INDEFINITELY /*2*/:
                        case TimeSelection.Type.CUSTOM /*3*/:
                            this.credentialType = value;
                            break;
                        default:
                            continue;
                    }
                case LogSource.HANGOUT_LOG_REQUEST /*50*/:
                    this.clientId = input.readString();
                    continue;
                case LogSource.SLIDES /*58*/:
                    this.locale = input.readString();
                    continue;
                case LogSource.JUSTSPEAK /*72*/:
                    this.deferCredentialsAfterFallback = input.readBool();
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

    public static ExchangeAccountBootstrapCredentialsRequest parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
        return (ExchangeAccountBootstrapCredentialsRequest) MessageNano.mergeFrom(new ExchangeAccountBootstrapCredentialsRequest(), data);
    }

    public static ExchangeAccountBootstrapCredentialsRequest parseFrom(CodedInputByteBufferNano input) throws IOException {
        return new ExchangeAccountBootstrapCredentialsRequest().mergeFrom(input);
    }
}
