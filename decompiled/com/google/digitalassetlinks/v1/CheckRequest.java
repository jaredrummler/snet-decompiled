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

public final class CheckRequest extends ExtendableMessageNano<CheckRequest> {
    private static volatile CheckRequest[] _emptyArray;
    public boolean allowGoogleInternalDataSources;
    public String relation;
    public Asset source;
    public String sourceDeprecated;
    public Asset target;
    public String targetDeprecated;

    public static CheckRequest[] emptyArray() {
        if (_emptyArray == null) {
            synchronized (InternalNano.LAZY_INIT_LOCK) {
                if (_emptyArray == null) {
                    _emptyArray = new CheckRequest[0];
                }
            }
        }
        return _emptyArray;
    }

    public CheckRequest() {
        clear();
    }

    public CheckRequest clear() {
        this.source = null;
        this.relation = BuildConfig.VERSION_NAME;
        this.target = null;
        this.allowGoogleInternalDataSources = false;
        this.sourceDeprecated = BuildConfig.VERSION_NAME;
        this.targetDeprecated = BuildConfig.VERSION_NAME;
        this.unknownFieldData = null;
        this.cachedSize = -1;
        return this;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof CheckRequest)) {
            return false;
        }
        CheckRequest other = (CheckRequest) o;
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
        if (this.allowGoogleInternalDataSources != other.allowGoogleInternalDataSources) {
            return false;
        }
        if (this.sourceDeprecated == null) {
            if (other.sourceDeprecated != null) {
                return false;
            }
        } else if (!this.sourceDeprecated.equals(other.sourceDeprecated)) {
            return false;
        }
        if (this.targetDeprecated == null) {
            if (other.targetDeprecated != null) {
                return false;
            }
        } else if (!this.targetDeprecated.equals(other.targetDeprecated)) {
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
        int hashCode = (((((((((((((getClass().getName().hashCode() + 527) * 31) + (this.source == null ? 0 : this.source.hashCode())) * 31) + (this.relation == null ? 0 : this.relation.hashCode())) * 31) + (this.target == null ? 0 : this.target.hashCode())) * 31) + (this.allowGoogleInternalDataSources ? 1231 : 1237)) * 31) + (this.sourceDeprecated == null ? 0 : this.sourceDeprecated.hashCode())) * 31) + (this.targetDeprecated == null ? 0 : this.targetDeprecated.hashCode())) * 31;
        if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
            i = this.unknownFieldData.hashCode();
        }
        return hashCode + i;
    }

    public void writeTo(CodedOutputByteBufferNano output) throws IOException {
        if (!this.sourceDeprecated.equals(BuildConfig.VERSION_NAME)) {
            output.writeString(1, this.sourceDeprecated);
        }
        if (!this.relation.equals(BuildConfig.VERSION_NAME)) {
            output.writeString(2, this.relation);
        }
        if (!this.targetDeprecated.equals(BuildConfig.VERSION_NAME)) {
            output.writeString(3, this.targetDeprecated);
        }
        if (this.allowGoogleInternalDataSources) {
            output.writeBool(4, this.allowGoogleInternalDataSources);
        }
        if (this.source != null) {
            output.writeMessage(5, this.source);
        }
        if (this.target != null) {
            output.writeMessage(6, this.target);
        }
        super.writeTo(output);
    }

    protected int computeSerializedSize() {
        int size = super.computeSerializedSize();
        if (!this.sourceDeprecated.equals(BuildConfig.VERSION_NAME)) {
            size += CodedOutputByteBufferNano.computeStringSize(1, this.sourceDeprecated);
        }
        if (!this.relation.equals(BuildConfig.VERSION_NAME)) {
            size += CodedOutputByteBufferNano.computeStringSize(2, this.relation);
        }
        if (!this.targetDeprecated.equals(BuildConfig.VERSION_NAME)) {
            size += CodedOutputByteBufferNano.computeStringSize(3, this.targetDeprecated);
        }
        if (this.allowGoogleInternalDataSources) {
            size += CodedOutputByteBufferNano.computeBoolSize(4, this.allowGoogleInternalDataSources);
        }
        if (this.source != null) {
            size += CodedOutputByteBufferNano.computeMessageSize(5, this.source);
        }
        if (this.target != null) {
            return size + CodedOutputByteBufferNano.computeMessageSize(6, this.target);
        }
        return size;
    }

    public CheckRequest mergeFrom(CodedInputByteBufferNano input) throws IOException {
        while (true) {
            int tag = input.readTag();
            switch (tag) {
                case Action.UNKNOWN /*0*/:
                    break;
                case Type.TAP_ABOUT /*10*/:
                    this.sourceDeprecated = input.readString();
                    continue;
                case LogSource.GOOGLE_ANALYTICS /*18*/:
                    this.relation = input.readString();
                    continue;
                case LogSource.ANDROID_CAMERA /*26*/:
                    this.targetDeprecated = input.readString();
                    continue;
                case LogSource.SOCIAL /*32*/:
                    this.allowGoogleInternalDataSources = input.readBool();
                    continue;
                case LogSource.PHOTOS /*42*/:
                    if (this.source == null) {
                        this.source = new Asset();
                    }
                    input.readMessage(this.source);
                    continue;
                case LogSource.HANGOUT_LOG_REQUEST /*50*/:
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

    public static CheckRequest parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
        return (CheckRequest) MessageNano.mergeFrom(new CheckRequest(), data);
    }

    public static CheckRequest parseFrom(CodedInputByteBufferNano input) throws IOException {
        return new CheckRequest().mergeFrom(input);
    }
}
