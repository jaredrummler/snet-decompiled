package com.google.gaia.frontend.proto;

import com.google.android.gms.lint.BuildConfig;
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

public final class UserCredentialInfo extends ExtendableMessageNano<UserCredentialInfo> {
    private static volatile UserCredentialInfo[] _emptyArray;
    public String accountIdentifier;
    public String credential;
    public String fallbackUrl;
    public String reason;
    public int status;

    public static UserCredentialInfo[] emptyArray() {
        if (_emptyArray == null) {
            synchronized (InternalNano.LAZY_INIT_LOCK) {
                if (_emptyArray == null) {
                    _emptyArray = new UserCredentialInfo[0];
                }
            }
        }
        return _emptyArray;
    }

    public UserCredentialInfo() {
        clear();
    }

    public UserCredentialInfo clear() {
        this.accountIdentifier = BuildConfig.VERSION_NAME;
        this.status = 0;
        this.reason = BuildConfig.VERSION_NAME;
        this.fallbackUrl = BuildConfig.VERSION_NAME;
        this.credential = BuildConfig.VERSION_NAME;
        this.unknownFieldData = null;
        this.cachedSize = -1;
        return this;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof UserCredentialInfo)) {
            return false;
        }
        UserCredentialInfo other = (UserCredentialInfo) o;
        if (this.accountIdentifier == null) {
            if (other.accountIdentifier != null) {
                return false;
            }
        } else if (!this.accountIdentifier.equals(other.accountIdentifier)) {
            return false;
        }
        if (this.status != other.status) {
            return false;
        }
        if (this.reason == null) {
            if (other.reason != null) {
                return false;
            }
        } else if (!this.reason.equals(other.reason)) {
            return false;
        }
        if (this.fallbackUrl == null) {
            if (other.fallbackUrl != null) {
                return false;
            }
        } else if (!this.fallbackUrl.equals(other.fallbackUrl)) {
            return false;
        }
        if (this.credential == null) {
            if (other.credential != null) {
                return false;
            }
        } else if (!this.credential.equals(other.credential)) {
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
        int hashCode = (((((((((((getClass().getName().hashCode() + 527) * 31) + (this.accountIdentifier == null ? 0 : this.accountIdentifier.hashCode())) * 31) + this.status) * 31) + (this.reason == null ? 0 : this.reason.hashCode())) * 31) + (this.fallbackUrl == null ? 0 : this.fallbackUrl.hashCode())) * 31) + (this.credential == null ? 0 : this.credential.hashCode())) * 31;
        if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
            i = this.unknownFieldData.hashCode();
        }
        return hashCode + i;
    }

    public void writeTo(CodedOutputByteBufferNano output) throws IOException {
        if (!this.accountIdentifier.equals(BuildConfig.VERSION_NAME)) {
            output.writeString(1, this.accountIdentifier);
        }
        if (this.status != 0) {
            output.writeInt32(2, this.status);
        }
        if (!this.reason.equals(BuildConfig.VERSION_NAME)) {
            output.writeString(3, this.reason);
        }
        if (!this.fallbackUrl.equals(BuildConfig.VERSION_NAME)) {
            output.writeString(4, this.fallbackUrl);
        }
        if (!this.credential.equals(BuildConfig.VERSION_NAME)) {
            output.writeString(5, this.credential);
        }
        super.writeTo(output);
    }

    protected int computeSerializedSize() {
        int size = super.computeSerializedSize();
        if (!this.accountIdentifier.equals(BuildConfig.VERSION_NAME)) {
            size += CodedOutputByteBufferNano.computeStringSize(1, this.accountIdentifier);
        }
        if (this.status != 0) {
            size += CodedOutputByteBufferNano.computeInt32Size(2, this.status);
        }
        if (!this.reason.equals(BuildConfig.VERSION_NAME)) {
            size += CodedOutputByteBufferNano.computeStringSize(3, this.reason);
        }
        if (!this.fallbackUrl.equals(BuildConfig.VERSION_NAME)) {
            size += CodedOutputByteBufferNano.computeStringSize(4, this.fallbackUrl);
        }
        if (this.credential.equals(BuildConfig.VERSION_NAME)) {
            return size;
        }
        return size + CodedOutputByteBufferNano.computeStringSize(5, this.credential);
    }

    public UserCredentialInfo mergeFrom(CodedInputByteBufferNano input) throws IOException {
        while (true) {
            int tag = input.readTag();
            switch (tag) {
                case Action.UNKNOWN /*0*/:
                    break;
                case Type.TAP_ABOUT /*10*/:
                    this.accountIdentifier = input.readString();
                    continue;
                case LogSource.GMS_CORE_PEOPLE /*16*/:
                    this.status = input.readInt32();
                    continue;
                case LogSource.ANDROID_CAMERA /*26*/:
                    this.reason = input.readString();
                    continue;
                case LogSource.NOVA /*34*/:
                    this.fallbackUrl = input.readString();
                    continue;
                case LogSource.PHOTOS /*42*/:
                    this.credential = input.readString();
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

    public static UserCredentialInfo parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
        return (UserCredentialInfo) MessageNano.mergeFrom(new UserCredentialInfo(), data);
    }

    public static UserCredentialInfo parseFrom(CodedInputByteBufferNano input) throws IOException {
        return new UserCredentialInfo().mergeFrom(input);
    }
}
