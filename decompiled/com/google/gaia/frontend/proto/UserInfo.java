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

public final class UserInfo extends ExtendableMessageNano<UserInfo> {
    private static volatile UserInfo[] _emptyArray;
    public String accountIdentifier;
    public byte[] userPublicKey;

    public static UserInfo[] emptyArray() {
        if (_emptyArray == null) {
            synchronized (InternalNano.LAZY_INIT_LOCK) {
                if (_emptyArray == null) {
                    _emptyArray = new UserInfo[0];
                }
            }
        }
        return _emptyArray;
    }

    public UserInfo() {
        clear();
    }

    public UserInfo clear() {
        this.accountIdentifier = BuildConfig.VERSION_NAME;
        this.userPublicKey = WireFormatNano.EMPTY_BYTES;
        this.unknownFieldData = null;
        this.cachedSize = -1;
        return this;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof UserInfo)) {
            return false;
        }
        UserInfo other = (UserInfo) o;
        if (this.accountIdentifier == null) {
            if (other.accountIdentifier != null) {
                return false;
            }
        } else if (!this.accountIdentifier.equals(other.accountIdentifier)) {
            return false;
        }
        if (!Arrays.equals(this.userPublicKey, other.userPublicKey)) {
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
        int hashCode = (((((getClass().getName().hashCode() + 527) * 31) + (this.accountIdentifier == null ? 0 : this.accountIdentifier.hashCode())) * 31) + Arrays.hashCode(this.userPublicKey)) * 31;
        if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
            i = this.unknownFieldData.hashCode();
        }
        return hashCode + i;
    }

    public void writeTo(CodedOutputByteBufferNano output) throws IOException {
        if (!this.accountIdentifier.equals(BuildConfig.VERSION_NAME)) {
            output.writeString(1, this.accountIdentifier);
        }
        if (!Arrays.equals(this.userPublicKey, WireFormatNano.EMPTY_BYTES)) {
            output.writeBytes(2, this.userPublicKey);
        }
        super.writeTo(output);
    }

    protected int computeSerializedSize() {
        int size = super.computeSerializedSize();
        if (!this.accountIdentifier.equals(BuildConfig.VERSION_NAME)) {
            size += CodedOutputByteBufferNano.computeStringSize(1, this.accountIdentifier);
        }
        if (Arrays.equals(this.userPublicKey, WireFormatNano.EMPTY_BYTES)) {
            return size;
        }
        return size + CodedOutputByteBufferNano.computeBytesSize(2, this.userPublicKey);
    }

    public UserInfo mergeFrom(CodedInputByteBufferNano input) throws IOException {
        while (true) {
            int tag = input.readTag();
            switch (tag) {
                case Action.UNKNOWN /*0*/:
                    break;
                case Type.TAP_ABOUT /*10*/:
                    this.accountIdentifier = input.readString();
                    continue;
                case LogSource.GOOGLE_ANALYTICS /*18*/:
                    this.userPublicKey = input.readBytes();
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

    public static UserInfo parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
        return (UserInfo) MessageNano.mergeFrom(new UserInfo(), data);
    }

    public static UserInfo parseFrom(CodedInputByteBufferNano input) throws IOException {
        return new UserInfo().mergeFrom(input);
    }
}
