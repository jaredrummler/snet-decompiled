package com.google.security.cryptauth.lib.risk;

import com.google.android.gms.lint.BuildConfig;
import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.ExtendableMessageNano;
import com.google.protobuf.nano.InternalNano;
import com.google.protobuf.nano.InvalidProtocolBufferNanoException;
import com.google.protobuf.nano.MessageNano;
import com.google.wireless.android.play.playlog.proto.UserLocation.LaunchSettingsEvent.Action;
import com.google.wireless.android.play.playlog.proto.UserLocation.LocationSharingEvent.Type;
import java.io.IOException;

public final class StarguardData extends ExtendableMessageNano<StarguardData> {
    private static volatile StarguardData[] _emptyArray;
    public String droidguardResponse;

    public static StarguardData[] emptyArray() {
        if (_emptyArray == null) {
            synchronized (InternalNano.LAZY_INIT_LOCK) {
                if (_emptyArray == null) {
                    _emptyArray = new StarguardData[0];
                }
            }
        }
        return _emptyArray;
    }

    public StarguardData() {
        clear();
    }

    public StarguardData clear() {
        this.droidguardResponse = BuildConfig.VERSION_NAME;
        this.unknownFieldData = null;
        this.cachedSize = -1;
        return this;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof StarguardData)) {
            return false;
        }
        StarguardData other = (StarguardData) o;
        if (this.droidguardResponse == null) {
            if (other.droidguardResponse != null) {
                return false;
            }
        } else if (!this.droidguardResponse.equals(other.droidguardResponse)) {
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
        int hashCode = (((getClass().getName().hashCode() + 527) * 31) + (this.droidguardResponse == null ? 0 : this.droidguardResponse.hashCode())) * 31;
        if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
            i = this.unknownFieldData.hashCode();
        }
        return hashCode + i;
    }

    public void writeTo(CodedOutputByteBufferNano output) throws IOException {
        if (!this.droidguardResponse.equals(BuildConfig.VERSION_NAME)) {
            output.writeString(1, this.droidguardResponse);
        }
        super.writeTo(output);
    }

    protected int computeSerializedSize() {
        int size = super.computeSerializedSize();
        if (this.droidguardResponse.equals(BuildConfig.VERSION_NAME)) {
            return size;
        }
        return size + CodedOutputByteBufferNano.computeStringSize(1, this.droidguardResponse);
    }

    public StarguardData mergeFrom(CodedInputByteBufferNano input) throws IOException {
        while (true) {
            int tag = input.readTag();
            switch (tag) {
                case Action.UNKNOWN /*0*/:
                    break;
                case Type.TAP_ABOUT /*10*/:
                    this.droidguardResponse = input.readString();
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

    public static StarguardData parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
        return (StarguardData) MessageNano.mergeFrom(new StarguardData(), data);
    }

    public static StarguardData parseFrom(CodedInputByteBufferNano input) throws IOException {
        return new StarguardData().mergeFrom(input);
    }
}
