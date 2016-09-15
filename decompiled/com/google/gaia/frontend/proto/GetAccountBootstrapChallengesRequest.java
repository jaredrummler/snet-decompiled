package com.google.gaia.frontend.proto;

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

public final class GetAccountBootstrapChallengesRequest extends ExtendableMessageNano<GetAccountBootstrapChallengesRequest> {
    private static volatile GetAccountBootstrapChallengesRequest[] _emptyArray;
    public UserInfo[] challengeUserInfo;
    public int flowType;

    public static GetAccountBootstrapChallengesRequest[] emptyArray() {
        if (_emptyArray == null) {
            synchronized (InternalNano.LAZY_INIT_LOCK) {
                if (_emptyArray == null) {
                    _emptyArray = new GetAccountBootstrapChallengesRequest[0];
                }
            }
        }
        return _emptyArray;
    }

    public GetAccountBootstrapChallengesRequest() {
        clear();
    }

    public GetAccountBootstrapChallengesRequest clear() {
        this.challengeUserInfo = UserInfo.emptyArray();
        this.flowType = 1;
        this.unknownFieldData = null;
        this.cachedSize = -1;
        return this;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof GetAccountBootstrapChallengesRequest)) {
            return false;
        }
        GetAccountBootstrapChallengesRequest other = (GetAccountBootstrapChallengesRequest) o;
        if (!InternalNano.equals(this.challengeUserInfo, other.challengeUserInfo) || this.flowType != other.flowType) {
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
        int hashCode = (((((getClass().getName().hashCode() + 527) * 31) + InternalNano.hashCode(this.challengeUserInfo)) * 31) + this.flowType) * 31;
        int hashCode2 = (this.unknownFieldData == null || this.unknownFieldData.isEmpty()) ? 0 : this.unknownFieldData.hashCode();
        return hashCode + hashCode2;
    }

    public void writeTo(CodedOutputByteBufferNano output) throws IOException {
        if (this.challengeUserInfo != null && this.challengeUserInfo.length > 0) {
            for (UserInfo element : this.challengeUserInfo) {
                if (element != null) {
                    output.writeMessage(1, element);
                }
            }
        }
        if (this.flowType != 1) {
            output.writeInt32(2, this.flowType);
        }
        super.writeTo(output);
    }

    protected int computeSerializedSize() {
        int size = super.computeSerializedSize();
        if (this.challengeUserInfo != null && this.challengeUserInfo.length > 0) {
            for (UserInfo element : this.challengeUserInfo) {
                if (element != null) {
                    size += CodedOutputByteBufferNano.computeMessageSize(1, element);
                }
            }
        }
        if (this.flowType != 1) {
            return size + CodedOutputByteBufferNano.computeInt32Size(2, this.flowType);
        }
        return size;
    }

    public GetAccountBootstrapChallengesRequest mergeFrom(CodedInputByteBufferNano input) throws IOException {
        while (true) {
            int tag = input.readTag();
            switch (tag) {
                case Action.UNKNOWN /*0*/:
                    break;
                case Type.TAP_ABOUT /*10*/:
                    int i;
                    int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 10);
                    if (this.challengeUserInfo == null) {
                        i = 0;
                    } else {
                        i = this.challengeUserInfo.length;
                    }
                    UserInfo[] newArray = new UserInfo[(i + arrayLength)];
                    if (i != 0) {
                        System.arraycopy(this.challengeUserInfo, 0, newArray, 0, i);
                    }
                    while (i < newArray.length - 1) {
                        newArray[i] = new UserInfo();
                        input.readMessage(newArray[i]);
                        input.readTag();
                        i++;
                    }
                    newArray[i] = new UserInfo();
                    input.readMessage(newArray[i]);
                    this.challengeUserInfo = newArray;
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
                default:
                    if (!storeUnknownField(input, tag)) {
                        break;
                    }
                    continue;
            }
            return this;
        }
    }

    public static GetAccountBootstrapChallengesRequest parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
        return (GetAccountBootstrapChallengesRequest) MessageNano.mergeFrom(new GetAccountBootstrapChallengesRequest(), data);
    }

    public static GetAccountBootstrapChallengesRequest parseFrom(CodedInputByteBufferNano input) throws IOException {
        return new GetAccountBootstrapChallengesRequest().mergeFrom(input);
    }
}
