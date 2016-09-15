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

public final class GetAccountBootstrapChallengesResponse extends ExtendableMessageNano<GetAccountBootstrapChallengesResponse> {
    private static volatile GetAccountBootstrapChallengesResponse[] _emptyArray;
    public ChallengeInfo[] challengeInfo;

    public static GetAccountBootstrapChallengesResponse[] emptyArray() {
        if (_emptyArray == null) {
            synchronized (InternalNano.LAZY_INIT_LOCK) {
                if (_emptyArray == null) {
                    _emptyArray = new GetAccountBootstrapChallengesResponse[0];
                }
            }
        }
        return _emptyArray;
    }

    public GetAccountBootstrapChallengesResponse() {
        clear();
    }

    public GetAccountBootstrapChallengesResponse clear() {
        this.challengeInfo = ChallengeInfo.emptyArray();
        this.unknownFieldData = null;
        this.cachedSize = -1;
        return this;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof GetAccountBootstrapChallengesResponse)) {
            return false;
        }
        GetAccountBootstrapChallengesResponse other = (GetAccountBootstrapChallengesResponse) o;
        if (!InternalNano.equals(this.challengeInfo, other.challengeInfo)) {
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
        int hashCode = (((getClass().getName().hashCode() + 527) * 31) + InternalNano.hashCode(this.challengeInfo)) * 31;
        int hashCode2 = (this.unknownFieldData == null || this.unknownFieldData.isEmpty()) ? 0 : this.unknownFieldData.hashCode();
        return hashCode + hashCode2;
    }

    public void writeTo(CodedOutputByteBufferNano output) throws IOException {
        if (this.challengeInfo != null && this.challengeInfo.length > 0) {
            for (ChallengeInfo element : this.challengeInfo) {
                if (element != null) {
                    output.writeMessage(1, element);
                }
            }
        }
        super.writeTo(output);
    }

    protected int computeSerializedSize() {
        int size = super.computeSerializedSize();
        if (this.challengeInfo != null && this.challengeInfo.length > 0) {
            for (ChallengeInfo element : this.challengeInfo) {
                if (element != null) {
                    size += CodedOutputByteBufferNano.computeMessageSize(1, element);
                }
            }
        }
        return size;
    }

    public GetAccountBootstrapChallengesResponse mergeFrom(CodedInputByteBufferNano input) throws IOException {
        while (true) {
            int tag = input.readTag();
            switch (tag) {
                case Action.UNKNOWN /*0*/:
                    break;
                case Type.TAP_ABOUT /*10*/:
                    int i;
                    int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 10);
                    if (this.challengeInfo == null) {
                        i = 0;
                    } else {
                        i = this.challengeInfo.length;
                    }
                    ChallengeInfo[] newArray = new ChallengeInfo[(i + arrayLength)];
                    if (i != 0) {
                        System.arraycopy(this.challengeInfo, 0, newArray, 0, i);
                    }
                    while (i < newArray.length - 1) {
                        newArray[i] = new ChallengeInfo();
                        input.readMessage(newArray[i]);
                        input.readTag();
                        i++;
                    }
                    newArray[i] = new ChallengeInfo();
                    input.readMessage(newArray[i]);
                    this.challengeInfo = newArray;
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

    public static GetAccountBootstrapChallengesResponse parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
        return (GetAccountBootstrapChallengesResponse) MessageNano.mergeFrom(new GetAccountBootstrapChallengesResponse(), data);
    }

    public static GetAccountBootstrapChallengesResponse parseFrom(CodedInputByteBufferNano input) throws IOException {
        return new GetAccountBootstrapChallengesResponse().mergeFrom(input);
    }
}
