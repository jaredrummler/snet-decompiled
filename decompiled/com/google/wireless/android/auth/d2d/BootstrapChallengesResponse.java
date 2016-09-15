package com.google.wireless.android.auth.d2d;

import com.google.gaia.frontend.proto.GetAccountBootstrapChallengesResponse;
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

public final class BootstrapChallengesResponse extends ExtendableMessageNano<BootstrapChallengesResponse> {
    private static volatile BootstrapChallengesResponse[] _emptyArray;
    public D2DError error;
    public GetAccountBootstrapChallengesResponse response;

    public static BootstrapChallengesResponse[] emptyArray() {
        if (_emptyArray == null) {
            synchronized (InternalNano.LAZY_INIT_LOCK) {
                if (_emptyArray == null) {
                    _emptyArray = new BootstrapChallengesResponse[0];
                }
            }
        }
        return _emptyArray;
    }

    public BootstrapChallengesResponse() {
        clear();
    }

    public BootstrapChallengesResponse clear() {
        this.response = null;
        this.error = null;
        this.unknownFieldData = null;
        this.cachedSize = -1;
        return this;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof BootstrapChallengesResponse)) {
            return false;
        }
        BootstrapChallengesResponse other = (BootstrapChallengesResponse) o;
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
        int hashCode = (((((getClass().getName().hashCode() + 527) * 31) + (this.response == null ? 0 : this.response.hashCode())) * 31) + (this.error == null ? 0 : this.error.hashCode())) * 31;
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
        super.writeTo(output);
    }

    protected int computeSerializedSize() {
        int size = super.computeSerializedSize();
        if (this.response != null) {
            size += CodedOutputByteBufferNano.computeMessageSize(1, this.response);
        }
        if (this.error != null) {
            return size + CodedOutputByteBufferNano.computeMessageSize(2, this.error);
        }
        return size;
    }

    public BootstrapChallengesResponse mergeFrom(CodedInputByteBufferNano input) throws IOException {
        while (true) {
            int tag = input.readTag();
            switch (tag) {
                case Action.UNKNOWN /*0*/:
                    break;
                case Type.TAP_ABOUT /*10*/:
                    if (this.response == null) {
                        this.response = new GetAccountBootstrapChallengesResponse();
                    }
                    input.readMessage(this.response);
                    continue;
                case LogSource.GOOGLE_ANALYTICS /*18*/:
                    if (this.error == null) {
                        this.error = new D2DError();
                    }
                    input.readMessage(this.error);
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

    public static BootstrapChallengesResponse parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
        return (BootstrapChallengesResponse) MessageNano.mergeFrom(new BootstrapChallengesResponse(), data);
    }

    public static BootstrapChallengesResponse parseFrom(CodedInputByteBufferNano input) throws IOException {
        return new BootstrapChallengesResponse().mergeFrom(input);
    }
}
