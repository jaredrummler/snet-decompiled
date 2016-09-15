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
import java.io.IOException;
import java.util.Arrays;

public final class ChallengeInfo extends ExtendableMessageNano<ChallengeInfo> {
    private static volatile ChallengeInfo[] _emptyArray;
    public String accountIdentifier;
    public byte[] challenge;
    public byte[] challengeSessionState;
    public String reason;
    public int status;

    public static ChallengeInfo[] emptyArray() {
        if (_emptyArray == null) {
            synchronized (InternalNano.LAZY_INIT_LOCK) {
                if (_emptyArray == null) {
                    _emptyArray = new ChallengeInfo[0];
                }
            }
        }
        return _emptyArray;
    }

    public ChallengeInfo() {
        clear();
    }

    public ChallengeInfo clear() {
        this.status = 0;
        this.accountIdentifier = BuildConfig.VERSION_NAME;
        this.reason = BuildConfig.VERSION_NAME;
        this.challenge = WireFormatNano.EMPTY_BYTES;
        this.challengeSessionState = WireFormatNano.EMPTY_BYTES;
        this.unknownFieldData = null;
        this.cachedSize = -1;
        return this;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ChallengeInfo)) {
            return false;
        }
        ChallengeInfo other = (ChallengeInfo) o;
        if (this.status != other.status) {
            return false;
        }
        if (this.accountIdentifier == null) {
            if (other.accountIdentifier != null) {
                return false;
            }
        } else if (!this.accountIdentifier.equals(other.accountIdentifier)) {
            return false;
        }
        if (this.reason == null) {
            if (other.reason != null) {
                return false;
            }
        } else if (!this.reason.equals(other.reason)) {
            return false;
        }
        if (!Arrays.equals(this.challenge, other.challenge) || !Arrays.equals(this.challengeSessionState, other.challengeSessionState)) {
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
        int hashCode = (((((((((((getClass().getName().hashCode() + 527) * 31) + this.status) * 31) + (this.accountIdentifier == null ? 0 : this.accountIdentifier.hashCode())) * 31) + (this.reason == null ? 0 : this.reason.hashCode())) * 31) + Arrays.hashCode(this.challenge)) * 31) + Arrays.hashCode(this.challengeSessionState)) * 31;
        if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
            i = this.unknownFieldData.hashCode();
        }
        return hashCode + i;
    }

    public void writeTo(CodedOutputByteBufferNano output) throws IOException {
        if (this.status != 0) {
            output.writeInt32(1, this.status);
        }
        if (!this.accountIdentifier.equals(BuildConfig.VERSION_NAME)) {
            output.writeString(2, this.accountIdentifier);
        }
        if (!this.reason.equals(BuildConfig.VERSION_NAME)) {
            output.writeString(3, this.reason);
        }
        if (!Arrays.equals(this.challenge, WireFormatNano.EMPTY_BYTES)) {
            output.writeBytes(4, this.challenge);
        }
        if (!Arrays.equals(this.challengeSessionState, WireFormatNano.EMPTY_BYTES)) {
            output.writeBytes(5, this.challengeSessionState);
        }
        super.writeTo(output);
    }

    protected int computeSerializedSize() {
        int size = super.computeSerializedSize();
        if (this.status != 0) {
            size += CodedOutputByteBufferNano.computeInt32Size(1, this.status);
        }
        if (!this.accountIdentifier.equals(BuildConfig.VERSION_NAME)) {
            size += CodedOutputByteBufferNano.computeStringSize(2, this.accountIdentifier);
        }
        if (!this.reason.equals(BuildConfig.VERSION_NAME)) {
            size += CodedOutputByteBufferNano.computeStringSize(3, this.reason);
        }
        if (!Arrays.equals(this.challenge, WireFormatNano.EMPTY_BYTES)) {
            size += CodedOutputByteBufferNano.computeBytesSize(4, this.challenge);
        }
        if (Arrays.equals(this.challengeSessionState, WireFormatNano.EMPTY_BYTES)) {
            return size;
        }
        return size + CodedOutputByteBufferNano.computeBytesSize(5, this.challengeSessionState);
    }

    public ChallengeInfo mergeFrom(CodedInputByteBufferNano input) throws IOException {
        while (true) {
            int tag = input.readTag();
            switch (tag) {
                case Action.UNKNOWN /*0*/:
                    break;
                case Type.TAP_GET_LINK /*8*/:
                    this.status = input.readInt32();
                    continue;
                case LogSource.GOOGLE_ANALYTICS /*18*/:
                    this.accountIdentifier = input.readString();
                    continue;
                case LogSource.ANDROID_CAMERA /*26*/:
                    this.reason = input.readString();
                    continue;
                case LogSource.NOVA /*34*/:
                    this.challenge = input.readBytes();
                    continue;
                case LogSource.PHOTOS /*42*/:
                    this.challengeSessionState = input.readBytes();
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

    public static ChallengeInfo parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
        return (ChallengeInfo) MessageNano.mergeFrom(new ChallengeInfo(), data);
    }

    public static ChallengeInfo parseFrom(CodedInputByteBufferNano input) throws IOException {
        return new ChallengeInfo().mergeFrom(input);
    }
}
