package com.google.android.gms.auth.oauthmultilogin.proto;

import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.InternalNano;
import com.google.protobuf.nano.InvalidProtocolBufferNanoException;
import com.google.protobuf.nano.MessageNano;
import com.google.protobuf.nano.WireFormatNano;
import com.google.wireless.android.play.playlog.proto.UserLocation.LaunchSettingsEvent.Action;
import com.google.wireless.android.play.playlog.proto.UserLocation.LocationSharingEvent.Type;
import java.io.IOException;

public final class OAuthLoginForOsidResponse extends MessageNano {
    private static volatile OAuthLoginForOsidResponse[] _emptyArray;
    public OAuthMultiLoginJsonResponse response;

    public static OAuthLoginForOsidResponse[] emptyArray() {
        if (_emptyArray == null) {
            synchronized (InternalNano.LAZY_INIT_LOCK) {
                if (_emptyArray == null) {
                    _emptyArray = new OAuthLoginForOsidResponse[0];
                }
            }
        }
        return _emptyArray;
    }

    public OAuthLoginForOsidResponse() {
        clear();
    }

    public OAuthLoginForOsidResponse clear() {
        this.response = null;
        this.cachedSize = -1;
        return this;
    }

    public void writeTo(CodedOutputByteBufferNano output) throws IOException {
        if (this.response != null) {
            output.writeMessage(1, this.response);
        }
        super.writeTo(output);
    }

    protected int computeSerializedSize() {
        int size = super.computeSerializedSize();
        if (this.response != null) {
            return size + CodedOutputByteBufferNano.computeMessageSize(1, this.response);
        }
        return size;
    }

    public OAuthLoginForOsidResponse mergeFrom(CodedInputByteBufferNano input) throws IOException {
        while (true) {
            int tag = input.readTag();
            switch (tag) {
                case Action.UNKNOWN /*0*/:
                    break;
                case Type.TAP_ABOUT /*10*/:
                    if (this.response == null) {
                        this.response = new OAuthMultiLoginJsonResponse();
                    }
                    input.readMessage(this.response);
                    continue;
                default:
                    if (!WireFormatNano.parseUnknownField(input, tag)) {
                        break;
                    }
                    continue;
            }
            return this;
        }
    }

    public static OAuthLoginForOsidResponse parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
        return (OAuthLoginForOsidResponse) MessageNano.mergeFrom(new OAuthLoginForOsidResponse(), data);
    }

    public static OAuthLoginForOsidResponse parseFrom(CodedInputByteBufferNano input) throws IOException {
        return new OAuthLoginForOsidResponse().mergeFrom(input);
    }
}
