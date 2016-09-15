package com.google.wireless.android.auth.d2d;

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

public final class UserDataInfo extends ExtendableMessageNano<UserDataInfo> {
    private static volatile UserDataInfo[] _emptyArray;
    public String accountIdentifier;
    public String firstName;
    public String lastName;

    public static UserDataInfo[] emptyArray() {
        if (_emptyArray == null) {
            synchronized (InternalNano.LAZY_INIT_LOCK) {
                if (_emptyArray == null) {
                    _emptyArray = new UserDataInfo[0];
                }
            }
        }
        return _emptyArray;
    }

    public UserDataInfo() {
        clear();
    }

    public UserDataInfo clear() {
        this.accountIdentifier = BuildConfig.VERSION_NAME;
        this.firstName = BuildConfig.VERSION_NAME;
        this.lastName = BuildConfig.VERSION_NAME;
        this.unknownFieldData = null;
        this.cachedSize = -1;
        return this;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof UserDataInfo)) {
            return false;
        }
        UserDataInfo other = (UserDataInfo) o;
        if (this.accountIdentifier == null) {
            if (other.accountIdentifier != null) {
                return false;
            }
        } else if (!this.accountIdentifier.equals(other.accountIdentifier)) {
            return false;
        }
        if (this.firstName == null) {
            if (other.firstName != null) {
                return false;
            }
        } else if (!this.firstName.equals(other.firstName)) {
            return false;
        }
        if (this.lastName == null) {
            if (other.lastName != null) {
                return false;
            }
        } else if (!this.lastName.equals(other.lastName)) {
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
        int hashCode = (((((((getClass().getName().hashCode() + 527) * 31) + (this.accountIdentifier == null ? 0 : this.accountIdentifier.hashCode())) * 31) + (this.firstName == null ? 0 : this.firstName.hashCode())) * 31) + (this.lastName == null ? 0 : this.lastName.hashCode())) * 31;
        if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
            i = this.unknownFieldData.hashCode();
        }
        return hashCode + i;
    }

    public void writeTo(CodedOutputByteBufferNano output) throws IOException {
        if (!this.accountIdentifier.equals(BuildConfig.VERSION_NAME)) {
            output.writeString(1, this.accountIdentifier);
        }
        if (!this.firstName.equals(BuildConfig.VERSION_NAME)) {
            output.writeString(2, this.firstName);
        }
        if (!this.lastName.equals(BuildConfig.VERSION_NAME)) {
            output.writeString(3, this.lastName);
        }
        super.writeTo(output);
    }

    protected int computeSerializedSize() {
        int size = super.computeSerializedSize();
        if (!this.accountIdentifier.equals(BuildConfig.VERSION_NAME)) {
            size += CodedOutputByteBufferNano.computeStringSize(1, this.accountIdentifier);
        }
        if (!this.firstName.equals(BuildConfig.VERSION_NAME)) {
            size += CodedOutputByteBufferNano.computeStringSize(2, this.firstName);
        }
        if (this.lastName.equals(BuildConfig.VERSION_NAME)) {
            return size;
        }
        return size + CodedOutputByteBufferNano.computeStringSize(3, this.lastName);
    }

    public UserDataInfo mergeFrom(CodedInputByteBufferNano input) throws IOException {
        while (true) {
            int tag = input.readTag();
            switch (tag) {
                case Action.UNKNOWN /*0*/:
                    break;
                case Type.TAP_ABOUT /*10*/:
                    this.accountIdentifier = input.readString();
                    continue;
                case LogSource.GOOGLE_ANALYTICS /*18*/:
                    this.firstName = input.readString();
                    continue;
                case LogSource.ANDROID_CAMERA /*26*/:
                    this.lastName = input.readString();
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

    public static UserDataInfo parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
        return (UserDataInfo) MessageNano.mergeFrom(new UserDataInfo(), data);
    }

    public static UserDataInfo parseFrom(CodedInputByteBufferNano input) throws IOException {
        return new UserDataInfo().mergeFrom(input);
    }
}
