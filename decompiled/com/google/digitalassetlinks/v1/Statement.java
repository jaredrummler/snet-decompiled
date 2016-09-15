package com.google.digitalassetlinks.v1;

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

public final class Statement extends ExtendableMessageNano<Statement> {
    private static volatile Statement[] _emptyArray;
    public String relation;
    public Asset source;
    public Asset target;

    public static Statement[] emptyArray() {
        if (_emptyArray == null) {
            synchronized (InternalNano.LAZY_INIT_LOCK) {
                if (_emptyArray == null) {
                    _emptyArray = new Statement[0];
                }
            }
        }
        return _emptyArray;
    }

    public Statement() {
        clear();
    }

    public Statement clear() {
        this.source = null;
        this.relation = BuildConfig.VERSION_NAME;
        this.target = null;
        this.unknownFieldData = null;
        this.cachedSize = -1;
        return this;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Statement)) {
            return false;
        }
        Statement other = (Statement) o;
        if (this.source == null) {
            if (other.source != null) {
                return false;
            }
        } else if (!this.source.equals(other.source)) {
            return false;
        }
        if (this.relation == null) {
            if (other.relation != null) {
                return false;
            }
        } else if (!this.relation.equals(other.relation)) {
            return false;
        }
        if (this.target == null) {
            if (other.target != null) {
                return false;
            }
        } else if (!this.target.equals(other.target)) {
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
        int hashCode = (((((((getClass().getName().hashCode() + 527) * 31) + (this.source == null ? 0 : this.source.hashCode())) * 31) + (this.relation == null ? 0 : this.relation.hashCode())) * 31) + (this.target == null ? 0 : this.target.hashCode())) * 31;
        if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
            i = this.unknownFieldData.hashCode();
        }
        return hashCode + i;
    }

    public void writeTo(CodedOutputByteBufferNano output) throws IOException {
        if (this.source != null) {
            output.writeMessage(1, this.source);
        }
        if (!this.relation.equals(BuildConfig.VERSION_NAME)) {
            output.writeString(2, this.relation);
        }
        if (this.target != null) {
            output.writeMessage(3, this.target);
        }
        super.writeTo(output);
    }

    protected int computeSerializedSize() {
        int size = super.computeSerializedSize();
        if (this.source != null) {
            size += CodedOutputByteBufferNano.computeMessageSize(1, this.source);
        }
        if (!this.relation.equals(BuildConfig.VERSION_NAME)) {
            size += CodedOutputByteBufferNano.computeStringSize(2, this.relation);
        }
        if (this.target != null) {
            return size + CodedOutputByteBufferNano.computeMessageSize(3, this.target);
        }
        return size;
    }

    public Statement mergeFrom(CodedInputByteBufferNano input) throws IOException {
        while (true) {
            int tag = input.readTag();
            switch (tag) {
                case Action.UNKNOWN /*0*/:
                    break;
                case Type.TAP_ABOUT /*10*/:
                    if (this.source == null) {
                        this.source = new Asset();
                    }
                    input.readMessage(this.source);
                    continue;
                case LogSource.GOOGLE_ANALYTICS /*18*/:
                    this.relation = input.readString();
                    continue;
                case LogSource.ANDROID_CAMERA /*26*/:
                    if (this.target == null) {
                        this.target = new Asset();
                    }
                    input.readMessage(this.target);
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

    public static Statement parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
        return (Statement) MessageNano.mergeFrom(new Statement(), data);
    }

    public static Statement parseFrom(CodedInputByteBufferNano input) throws IOException {
        return new Statement().mergeFrom(input);
    }
}
