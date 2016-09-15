package com.google.wireless.android.auth.d2d;

import com.google.android.gms.lint.BuildConfig;
import com.google.gaia.frontend.proto.ExchangeAccountBootstrapCredentialsResponse;
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
import java.io.IOException;

public final class ExchangeBootstrapCredentialsResponse extends ExtendableMessageNano<ExchangeBootstrapCredentialsResponse> {
    private static volatile ExchangeBootstrapCredentialsResponse[] _emptyArray;
    public D2DError error;
    public ExchangeAccountBootstrapCredentialsResponse response;
    public String restoreAccountId;
    public String restoreToken;
    public UserDataInfo[] userData;

    public static ExchangeBootstrapCredentialsResponse[] emptyArray() {
        if (_emptyArray == null) {
            synchronized (InternalNano.LAZY_INIT_LOCK) {
                if (_emptyArray == null) {
                    _emptyArray = new ExchangeBootstrapCredentialsResponse[0];
                }
            }
        }
        return _emptyArray;
    }

    public ExchangeBootstrapCredentialsResponse() {
        clear();
    }

    public ExchangeBootstrapCredentialsResponse clear() {
        this.response = null;
        this.error = null;
        this.restoreAccountId = BuildConfig.VERSION_NAME;
        this.restoreToken = BuildConfig.VERSION_NAME;
        this.userData = UserDataInfo.emptyArray();
        this.unknownFieldData = null;
        this.cachedSize = -1;
        return this;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ExchangeBootstrapCredentialsResponse)) {
            return false;
        }
        ExchangeBootstrapCredentialsResponse other = (ExchangeBootstrapCredentialsResponse) o;
        if (this.response == null) {
            if (other.response != null) {
                return false;
            }
        } else if (!this.response.equals(other.response)) {
            return false;
        }
        if (this.error == null) {
            if (other.error != null) {
                return false;
            }
        } else if (!this.error.equals(other.error)) {
            return false;
        }
        if (this.restoreAccountId == null) {
            if (other.restoreAccountId != null) {
                return false;
            }
        } else if (!this.restoreAccountId.equals(other.restoreAccountId)) {
            return false;
        }
        if (this.restoreToken == null) {
            if (other.restoreToken != null) {
                return false;
            }
        } else if (!this.restoreToken.equals(other.restoreToken)) {
            return false;
        }
        if (!InternalNano.equals(this.userData, other.userData)) {
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
        int hashCode = (((((((((((getClass().getName().hashCode() + 527) * 31) + (this.response == null ? 0 : this.response.hashCode())) * 31) + (this.error == null ? 0 : this.error.hashCode())) * 31) + (this.restoreAccountId == null ? 0 : this.restoreAccountId.hashCode())) * 31) + (this.restoreToken == null ? 0 : this.restoreToken.hashCode())) * 31) + InternalNano.hashCode(this.userData)) * 31;
        if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
            i = this.unknownFieldData.hashCode();
        }
        return hashCode + i;
    }

    public void writeTo(CodedOutputByteBufferNano output) throws IOException {
        if (this.response != null) {
            output.writeMessage(1, this.response);
        }
        if (this.error != null) {
            output.writeMessage(2, this.error);
        }
        if (!this.restoreAccountId.equals(BuildConfig.VERSION_NAME)) {
            output.writeString(3, this.restoreAccountId);
        }
        if (!this.restoreToken.equals(BuildConfig.VERSION_NAME)) {
            output.writeString(4, this.restoreToken);
        }
        if (this.userData != null && this.userData.length > 0) {
            for (UserDataInfo element : this.userData) {
                if (element != null) {
                    output.writeMessage(5, element);
                }
            }
        }
        super.writeTo(output);
    }

    protected int computeSerializedSize() {
        int size = super.computeSerializedSize();
        if (this.response != null) {
            size += CodedOutputByteBufferNano.computeMessageSize(1, this.response);
        }
        if (this.error != null) {
            size += CodedOutputByteBufferNano.computeMessageSize(2, this.error);
        }
        if (!this.restoreAccountId.equals(BuildConfig.VERSION_NAME)) {
            size += CodedOutputByteBufferNano.computeStringSize(3, this.restoreAccountId);
        }
        if (!this.restoreToken.equals(BuildConfig.VERSION_NAME)) {
            size += CodedOutputByteBufferNano.computeStringSize(4, this.restoreToken);
        }
        if (this.userData != null && this.userData.length > 0) {
            for (UserDataInfo element : this.userData) {
                if (element != null) {
                    size += CodedOutputByteBufferNano.computeMessageSize(5, element);
                }
            }
        }
        return size;
    }

    public ExchangeBootstrapCredentialsResponse mergeFrom(CodedInputByteBufferNano input) throws IOException {
        while (true) {
            int tag = input.readTag();
            switch (tag) {
                case Action.UNKNOWN /*0*/:
                    break;
                case Type.TAP_ABOUT /*10*/:
                    if (this.response == null) {
                        this.response = new ExchangeAccountBootstrapCredentialsResponse();
                    }
                    input.readMessage(this.response);
                    continue;
                case LogSource.GOOGLE_ANALYTICS /*18*/:
                    if (this.error == null) {
                        this.error = new D2DError();
                    }
                    input.readMessage(this.error);
                    continue;
                case LogSource.ANDROID_CAMERA /*26*/:
                    this.restoreAccountId = input.readString();
                    continue;
                case LogSource.NOVA /*34*/:
                    this.restoreToken = input.readString();
                    continue;
                case LogSource.PHOTOS /*42*/:
                    int i;
                    int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 42);
                    if (this.userData == null) {
                        i = 0;
                    } else {
                        i = this.userData.length;
                    }
                    UserDataInfo[] newArray = new UserDataInfo[(i + arrayLength)];
                    if (i != 0) {
                        System.arraycopy(this.userData, 0, newArray, 0, i);
                    }
                    while (i < newArray.length - 1) {
                        newArray[i] = new UserDataInfo();
                        input.readMessage(newArray[i]);
                        input.readTag();
                        i++;
                    }
                    newArray[i] = new UserDataInfo();
                    input.readMessage(newArray[i]);
                    this.userData = newArray;
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

    public static ExchangeBootstrapCredentialsResponse parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
        return (ExchangeBootstrapCredentialsResponse) MessageNano.mergeFrom(new ExchangeBootstrapCredentialsResponse(), data);
    }

    public static ExchangeBootstrapCredentialsResponse parseFrom(CodedInputByteBufferNano input) throws IOException {
        return new ExchangeBootstrapCredentialsResponse().mergeFrom(input);
    }
}
