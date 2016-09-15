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
import com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection;
import java.io.IOException;
import java.util.Arrays;

public final class AssertionInfo extends ExtendableMessageNano<AssertionInfo> {
    private static volatile AssertionInfo[] _emptyArray;
    public String accountIdentifier;
    public int assertionType;
    public byte[] challenge;
    public byte[] challengeSessionState;
    public byte[] clientData;
    public byte[] encryptedUserAssertion;

    public static AssertionInfo[] emptyArray() {
        if (_emptyArray == null) {
            synchronized (InternalNano.LAZY_INIT_LOCK) {
                if (_emptyArray == null) {
                    _emptyArray = new AssertionInfo[0];
                }
            }
        }
        return _emptyArray;
    }

    public AssertionInfo() {
        clear();
    }

    public AssertionInfo clear() {
        this.accountIdentifier = BuildConfig.VERSION_NAME;
        this.clientData = WireFormatNano.EMPTY_BYTES;
        this.encryptedUserAssertion = WireFormatNano.EMPTY_BYTES;
        this.challengeSessionState = WireFormatNano.EMPTY_BYTES;
        this.challenge = WireFormatNano.EMPTY_BYTES;
        this.assertionType = 1;
        this.unknownFieldData = null;
        this.cachedSize = -1;
        return this;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof AssertionInfo)) {
            return false;
        }
        AssertionInfo other = (AssertionInfo) o;
        if (this.accountIdentifier == null) {
            if (other.accountIdentifier != null) {
                return false;
            }
        } else if (!this.accountIdentifier.equals(other.accountIdentifier)) {
            return false;
        }
        if (!Arrays.equals(this.clientData, other.clientData) || !Arrays.equals(this.encryptedUserAssertion, other.encryptedUserAssertion) || !Arrays.equals(this.challengeSessionState, other.challengeSessionState) || !Arrays.equals(this.challenge, other.challenge) || this.assertionType != other.assertionType) {
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
        int hashCode = (((((((((((((getClass().getName().hashCode() + 527) * 31) + (this.accountIdentifier == null ? 0 : this.accountIdentifier.hashCode())) * 31) + Arrays.hashCode(this.clientData)) * 31) + Arrays.hashCode(this.encryptedUserAssertion)) * 31) + Arrays.hashCode(this.challengeSessionState)) * 31) + Arrays.hashCode(this.challenge)) * 31) + this.assertionType) * 31;
        if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
            i = this.unknownFieldData.hashCode();
        }
        return hashCode + i;
    }

    public void writeTo(CodedOutputByteBufferNano output) throws IOException {
        if (!this.accountIdentifier.equals(BuildConfig.VERSION_NAME)) {
            output.writeString(1, this.accountIdentifier);
        }
        if (!Arrays.equals(this.clientData, WireFormatNano.EMPTY_BYTES)) {
            output.writeBytes(2, this.clientData);
        }
        output.writeBytes(4, this.encryptedUserAssertion);
        if (!Arrays.equals(this.challengeSessionState, WireFormatNano.EMPTY_BYTES)) {
            output.writeBytes(5, this.challengeSessionState);
        }
        if (!Arrays.equals(this.challenge, WireFormatNano.EMPTY_BYTES)) {
            output.writeBytes(6, this.challenge);
        }
        if (this.assertionType != 1) {
            output.writeInt32(7, this.assertionType);
        }
        super.writeTo(output);
    }

    protected int computeSerializedSize() {
        int size = super.computeSerializedSize();
        if (!this.accountIdentifier.equals(BuildConfig.VERSION_NAME)) {
            size += CodedOutputByteBufferNano.computeStringSize(1, this.accountIdentifier);
        }
        if (!Arrays.equals(this.clientData, WireFormatNano.EMPTY_BYTES)) {
            size += CodedOutputByteBufferNano.computeBytesSize(2, this.clientData);
        }
        size += CodedOutputByteBufferNano.computeBytesSize(4, this.encryptedUserAssertion);
        if (!Arrays.equals(this.challengeSessionState, WireFormatNano.EMPTY_BYTES)) {
            size += CodedOutputByteBufferNano.computeBytesSize(5, this.challengeSessionState);
        }
        if (!Arrays.equals(this.challenge, WireFormatNano.EMPTY_BYTES)) {
            size += CodedOutputByteBufferNano.computeBytesSize(6, this.challenge);
        }
        if (this.assertionType != 1) {
            return size + CodedOutputByteBufferNano.computeInt32Size(7, this.assertionType);
        }
        return size;
    }

    public AssertionInfo mergeFrom(CodedInputByteBufferNano input) throws IOException {
        while (true) {
            int tag = input.readTag();
            switch (tag) {
                case Action.UNKNOWN /*0*/:
                    break;
                case Type.TAP_ABOUT /*10*/:
                    this.accountIdentifier = input.readString();
                    continue;
                case LogSource.GOOGLE_ANALYTICS /*18*/:
                    this.clientData = input.readBytes();
                    continue;
                case LogSource.NOVA /*34*/:
                    this.encryptedUserAssertion = input.readBytes();
                    continue;
                case LogSource.PHOTOS /*42*/:
                    this.challengeSessionState = input.readBytes();
                    continue;
                case LogSource.HANGOUT_LOG_REQUEST /*50*/:
                    this.challenge = input.readBytes();
                    continue;
                case LogSource.DOCS /*56*/:
                    int value = input.readInt32();
                    switch (value) {
                        case TimeSelection.Type.TEMPORARY /*1*/:
                        case TimeSelection.Type.INDEFINITELY /*2*/:
                            this.assertionType = value;
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

    public static AssertionInfo parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
        return (AssertionInfo) MessageNano.mergeFrom(new AssertionInfo(), data);
    }

    public static AssertionInfo parseFrom(CodedInputByteBufferNano input) throws IOException {
        return new AssertionInfo().mergeFrom(input);
    }
}
