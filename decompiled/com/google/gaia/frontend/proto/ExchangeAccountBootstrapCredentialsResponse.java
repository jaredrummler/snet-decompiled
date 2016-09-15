package com.google.gaia.frontend.proto;

import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.ExtendableMessageNano;
import com.google.protobuf.nano.InternalNano;
import com.google.protobuf.nano.InvalidProtocolBufferNanoException;
import com.google.protobuf.nano.MessageNano;
import com.google.protobuf.nano.WireFormatNano;
import com.google.wireless.android.play.playlog.proto.UserLocation.LaunchSettingsEvent.Action;
import com.google.wireless.android.play.playlog.proto.UserLocation.LocationSharingEvent.Type;
import java.io.IOException;

public final class ExchangeAccountBootstrapCredentialsResponse extends ExtendableMessageNano<ExchangeAccountBootstrapCredentialsResponse> {
    private static volatile ExchangeAccountBootstrapCredentialsResponse[] _emptyArray;
    public UserCredentialInfo[] userCredential;

    public static ExchangeAccountBootstrapCredentialsResponse[] emptyArray() {
        if (_emptyArray == null) {
            synchronized (InternalNano.LAZY_INIT_LOCK) {
                if (_emptyArray == null) {
                    _emptyArray = new ExchangeAccountBootstrapCredentialsResponse[0];
                }
            }
        }
        return _emptyArray;
    }

    public ExchangeAccountBootstrapCredentialsResponse() {
        clear();
    }

    public ExchangeAccountBootstrapCredentialsResponse clear() {
        this.userCredential = UserCredentialInfo.emptyArray();
        this.unknownFieldData = null;
        this.cachedSize = -1;
        return this;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ExchangeAccountBootstrapCredentialsResponse)) {
            return false;
        }
        ExchangeAccountBootstrapCredentialsResponse other = (ExchangeAccountBootstrapCredentialsResponse) o;
        if (!InternalNano.equals(this.userCredential, other.userCredential)) {
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
        int hashCode = (((getClass().getName().hashCode() + 527) * 31) + InternalNano.hashCode(this.userCredential)) * 31;
        int hashCode2 = (this.unknownFieldData == null || this.unknownFieldData.isEmpty()) ? 0 : this.unknownFieldData.hashCode();
        return hashCode + hashCode2;
    }

    public void writeTo(CodedOutputByteBufferNano output) throws IOException {
        if (this.userCredential != null && this.userCredential.length > 0) {
            for (UserCredentialInfo element : this.userCredential) {
                if (element != null) {
                    output.writeMessage(1, element);
                }
            }
        }
        super.writeTo(output);
    }

    protected int computeSerializedSize() {
        int size = super.computeSerializedSize();
        if (this.userCredential != null && this.userCredential.length > 0) {
            for (UserCredentialInfo element : this.userCredential) {
                if (element != null) {
                    size += CodedOutputByteBufferNano.computeMessageSize(1, element);
                }
            }
        }
        return size;
    }

    public ExchangeAccountBootstrapCredentialsResponse mergeFrom(CodedInputByteBufferNano input) throws IOException {
        while (true) {
            int tag = input.readTag();
            switch (tag) {
                case Action.UNKNOWN /*0*/:
                    break;
                case Type.TAP_ABOUT /*10*/:
                    int i;
                    int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 10);
                    if (this.userCredential == null) {
                        i = 0;
                    } else {
                        i = this.userCredential.length;
                    }
                    UserCredentialInfo[] newArray = new UserCredentialInfo[(i + arrayLength)];
                    if (i != 0) {
                        System.arraycopy(this.userCredential, 0, newArray, 0, i);
                    }
                    while (i < newArray.length - 1) {
                        newArray[i] = new UserCredentialInfo();
                        input.readMessage(newArray[i]);
                        input.readTag();
                        i++;
                    }
                    newArray[i] = new UserCredentialInfo();
                    input.readMessage(newArray[i]);
                    this.userCredential = newArray;
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

    public static ExchangeAccountBootstrapCredentialsResponse parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
        return (ExchangeAccountBootstrapCredentialsResponse) MessageNano.mergeFrom(new ExchangeAccountBootstrapCredentialsResponse(), data);
    }

    public static ExchangeAccountBootstrapCredentialsResponse parseFrom(CodedInputByteBufferNano input) throws IOException {
        return new ExchangeAccountBootstrapCredentialsResponse().mergeFrom(input);
    }
}
