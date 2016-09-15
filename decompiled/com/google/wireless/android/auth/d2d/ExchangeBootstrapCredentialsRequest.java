package com.google.wireless.android.auth.d2d;

import com.google.android.gms.lint.BuildConfig;
import com.google.gaia.frontend.proto.ExchangeAccountBootstrapCredentialsRequest;
import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.ExtendableMessageNano;
import com.google.protobuf.nano.InternalNano;
import com.google.protobuf.nano.InvalidProtocolBufferNanoException;
import com.google.protobuf.nano.MessageNano;
import com.google.wireless.android.play.playlog.proto.ClientAnalytics.LogRequest.LogSource;
import com.google.wireless.android.play.playlog.proto.UserLocation.LaunchSettingsEvent.Action;
import com.google.wireless.android.play.playlog.proto.UserLocation.LocationSharingEvent.Type;
import java.io.IOException;

public final class ExchangeBootstrapCredentialsRequest extends ExtendableMessageNano<ExchangeBootstrapCredentialsRequest> {
    private static volatile ExchangeBootstrapCredentialsRequest[] _emptyArray;
    public ExchangeAccountBootstrapCredentialsRequest request;
    public String sourceAndroidDeviceId;
    public String sourceBackupAccountId;

    public static ExchangeBootstrapCredentialsRequest[] emptyArray() {
        if (_emptyArray == null) {
            synchronized (InternalNano.LAZY_INIT_LOCK) {
                if (_emptyArray == null) {
                    _emptyArray = new ExchangeBootstrapCredentialsRequest[0];
                }
            }
        }
        return _emptyArray;
    }

    public ExchangeBootstrapCredentialsRequest() {
        clear();
    }

    public ExchangeBootstrapCredentialsRequest clear() {
        this.request = null;
        this.sourceBackupAccountId = BuildConfig.VERSION_NAME;
        this.sourceAndroidDeviceId = BuildConfig.VERSION_NAME;
        this.unknownFieldData = null;
        this.cachedSize = -1;
        return this;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ExchangeBootstrapCredentialsRequest)) {
            return false;
        }
        ExchangeBootstrapCredentialsRequest other = (ExchangeBootstrapCredentialsRequest) o;
        if (this.request == null) {
            if (other.request != null) {
                return false;
            }
        } else if (!this.request.equals(other.request)) {
            return false;
        }
        if (this.sourceBackupAccountId == null) {
            if (other.sourceBackupAccountId != null) {
                return false;
            }
        } else if (!this.sourceBackupAccountId.equals(other.sourceBackupAccountId)) {
            return false;
        }
        if (this.sourceAndroidDeviceId == null) {
            if (other.sourceAndroidDeviceId != null) {
                return false;
            }
        } else if (!this.sourceAndroidDeviceId.equals(other.sourceAndroidDeviceId)) {
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
        int hashCode = (((((((getClass().getName().hashCode() + 527) * 31) + (this.request == null ? 0 : this.request.hashCode())) * 31) + (this.sourceBackupAccountId == null ? 0 : this.sourceBackupAccountId.hashCode())) * 31) + (this.sourceAndroidDeviceId == null ? 0 : this.sourceAndroidDeviceId.hashCode())) * 31;
        if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
            i = this.unknownFieldData.hashCode();
        }
        return hashCode + i;
    }

    public void writeTo(CodedOutputByteBufferNano output) throws IOException {
        if (this.request != null) {
            output.writeMessage(1, this.request);
        }
        if (!this.sourceBackupAccountId.equals(BuildConfig.VERSION_NAME)) {
            output.writeString(2, this.sourceBackupAccountId);
        }
        if (!this.sourceAndroidDeviceId.equals(BuildConfig.VERSION_NAME)) {
            output.writeString(3, this.sourceAndroidDeviceId);
        }
        super.writeTo(output);
    }

    protected int computeSerializedSize() {
        int size = super.computeSerializedSize();
        if (this.request != null) {
            size += CodedOutputByteBufferNano.computeMessageSize(1, this.request);
        }
        if (!this.sourceBackupAccountId.equals(BuildConfig.VERSION_NAME)) {
            size += CodedOutputByteBufferNano.computeStringSize(2, this.sourceBackupAccountId);
        }
        if (this.sourceAndroidDeviceId.equals(BuildConfig.VERSION_NAME)) {
            return size;
        }
        return size + CodedOutputByteBufferNano.computeStringSize(3, this.sourceAndroidDeviceId);
    }

    public ExchangeBootstrapCredentialsRequest mergeFrom(CodedInputByteBufferNano input) throws IOException {
        while (true) {
            int tag = input.readTag();
            switch (tag) {
                case Action.UNKNOWN /*0*/:
                    break;
                case Type.TAP_ABOUT /*10*/:
                    if (this.request == null) {
                        this.request = new ExchangeAccountBootstrapCredentialsRequest();
                    }
                    input.readMessage(this.request);
                    continue;
                case LogSource.GOOGLE_ANALYTICS /*18*/:
                    this.sourceBackupAccountId = input.readString();
                    continue;
                case LogSource.ANDROID_CAMERA /*26*/:
                    this.sourceAndroidDeviceId = input.readString();
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

    public static ExchangeBootstrapCredentialsRequest parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
        return (ExchangeBootstrapCredentialsRequest) MessageNano.mergeFrom(new ExchangeBootstrapCredentialsRequest(), data);
    }

    public static ExchangeBootstrapCredentialsRequest parseFrom(CodedInputByteBufferNano input) throws IOException {
        return new ExchangeBootstrapCredentialsRequest().mergeFrom(input);
    }
}
