package com.google.apps.framework.data.proto;

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

public final class BatchDataRequestHeader extends ExtendableMessageNano<BatchDataRequestHeader> {
    private static volatile BatchDataRequestHeader[] _emptyArray;
    public String consistencyTokenJar;
    public String effectiveUser;

    public static BatchDataRequestHeader[] emptyArray() {
        if (_emptyArray == null) {
            synchronized (InternalNano.LAZY_INIT_LOCK) {
                if (_emptyArray == null) {
                    _emptyArray = new BatchDataRequestHeader[0];
                }
            }
        }
        return _emptyArray;
    }

    public BatchDataRequestHeader() {
        clear();
    }

    public BatchDataRequestHeader clear() {
        this.effectiveUser = BuildConfig.VERSION_NAME;
        this.consistencyTokenJar = BuildConfig.VERSION_NAME;
        this.unknownFieldData = null;
        this.cachedSize = -1;
        return this;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof BatchDataRequestHeader)) {
            return false;
        }
        BatchDataRequestHeader other = (BatchDataRequestHeader) o;
        if (this.effectiveUser == null) {
            if (other.effectiveUser != null) {
                return false;
            }
        } else if (!this.effectiveUser.equals(other.effectiveUser)) {
            return false;
        }
        if (this.consistencyTokenJar == null) {
            if (other.consistencyTokenJar != null) {
                return false;
            }
        } else if (!this.consistencyTokenJar.equals(other.consistencyTokenJar)) {
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
        int hashCode = (((((getClass().getName().hashCode() + 527) * 31) + (this.effectiveUser == null ? 0 : this.effectiveUser.hashCode())) * 31) + (this.consistencyTokenJar == null ? 0 : this.consistencyTokenJar.hashCode())) * 31;
        if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
            i = this.unknownFieldData.hashCode();
        }
        return hashCode + i;
    }

    public void writeTo(CodedOutputByteBufferNano output) throws IOException {
        if (!this.effectiveUser.equals(BuildConfig.VERSION_NAME)) {
            output.writeString(1, this.effectiveUser);
        }
        if (!this.consistencyTokenJar.equals(BuildConfig.VERSION_NAME)) {
            output.writeString(2, this.consistencyTokenJar);
        }
        super.writeTo(output);
    }

    protected int computeSerializedSize() {
        int size = super.computeSerializedSize();
        if (!this.effectiveUser.equals(BuildConfig.VERSION_NAME)) {
            size += CodedOutputByteBufferNano.computeStringSize(1, this.effectiveUser);
        }
        if (this.consistencyTokenJar.equals(BuildConfig.VERSION_NAME)) {
            return size;
        }
        return size + CodedOutputByteBufferNano.computeStringSize(2, this.consistencyTokenJar);
    }

    public BatchDataRequestHeader mergeFrom(CodedInputByteBufferNano input) throws IOException {
        while (true) {
            int tag = input.readTag();
            switch (tag) {
                case Action.UNKNOWN /*0*/:
                    break;
                case Type.TAP_ABOUT /*10*/:
                    this.effectiveUser = input.readString();
                    continue;
                case LogSource.GOOGLE_ANALYTICS /*18*/:
                    this.consistencyTokenJar = input.readString();
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

    public static BatchDataRequestHeader parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
        return (BatchDataRequestHeader) MessageNano.mergeFrom(new BatchDataRequestHeader(), data);
    }

    public static BatchDataRequestHeader parseFrom(CodedInputByteBufferNano input) throws IOException {
        return new BatchDataRequestHeader().mergeFrom(input);
    }
}
