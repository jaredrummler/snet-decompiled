package com.google.apps.framework.consistency.proto;

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

public final class ApiaryConsistencyTokenResponseHeader extends ExtendableMessageNano<ApiaryConsistencyTokenResponseHeader> {
    private static volatile ApiaryConsistencyTokenResponseHeader[] _emptyArray;
    public int maxAgeSecs;
    public String newConsistencyTokenJar;

    public static ApiaryConsistencyTokenResponseHeader[] emptyArray() {
        if (_emptyArray == null) {
            synchronized (InternalNano.LAZY_INIT_LOCK) {
                if (_emptyArray == null) {
                    _emptyArray = new ApiaryConsistencyTokenResponseHeader[0];
                }
            }
        }
        return _emptyArray;
    }

    public ApiaryConsistencyTokenResponseHeader() {
        clear();
    }

    public ApiaryConsistencyTokenResponseHeader clear() {
        this.newConsistencyTokenJar = BuildConfig.VERSION_NAME;
        this.maxAgeSecs = 0;
        this.unknownFieldData = null;
        this.cachedSize = -1;
        return this;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ApiaryConsistencyTokenResponseHeader)) {
            return false;
        }
        ApiaryConsistencyTokenResponseHeader other = (ApiaryConsistencyTokenResponseHeader) o;
        if (this.newConsistencyTokenJar == null) {
            if (other.newConsistencyTokenJar != null) {
                return false;
            }
        } else if (!this.newConsistencyTokenJar.equals(other.newConsistencyTokenJar)) {
            return false;
        }
        if (this.maxAgeSecs != other.maxAgeSecs) {
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
        int hashCode = (((((getClass().getName().hashCode() + 527) * 31) + (this.newConsistencyTokenJar == null ? 0 : this.newConsistencyTokenJar.hashCode())) * 31) + this.maxAgeSecs) * 31;
        if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
            i = this.unknownFieldData.hashCode();
        }
        return hashCode + i;
    }

    public void writeTo(CodedOutputByteBufferNano output) throws IOException {
        if (!this.newConsistencyTokenJar.equals(BuildConfig.VERSION_NAME)) {
            output.writeString(1, this.newConsistencyTokenJar);
        }
        if (this.maxAgeSecs != 0) {
            output.writeInt32(2, this.maxAgeSecs);
        }
        super.writeTo(output);
    }

    protected int computeSerializedSize() {
        int size = super.computeSerializedSize();
        if (!this.newConsistencyTokenJar.equals(BuildConfig.VERSION_NAME)) {
            size += CodedOutputByteBufferNano.computeStringSize(1, this.newConsistencyTokenJar);
        }
        if (this.maxAgeSecs != 0) {
            return size + CodedOutputByteBufferNano.computeInt32Size(2, this.maxAgeSecs);
        }
        return size;
    }

    public ApiaryConsistencyTokenResponseHeader mergeFrom(CodedInputByteBufferNano input) throws IOException {
        while (true) {
            int tag = input.readTag();
            switch (tag) {
                case Action.UNKNOWN /*0*/:
                    break;
                case Type.TAP_ABOUT /*10*/:
                    this.newConsistencyTokenJar = input.readString();
                    continue;
                case LogSource.GMS_CORE_PEOPLE /*16*/:
                    this.maxAgeSecs = input.readInt32();
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

    public static ApiaryConsistencyTokenResponseHeader parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
        return (ApiaryConsistencyTokenResponseHeader) MessageNano.mergeFrom(new ApiaryConsistencyTokenResponseHeader(), data);
    }

    public static ApiaryConsistencyTokenResponseHeader parseFrom(CodedInputByteBufferNano input) throws IOException {
        return new ApiaryConsistencyTokenResponseHeader().mergeFrom(input);
    }
}
