package com.google.digitalassetlinks.v1;

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

public final class Asset extends ExtendableMessageNano<Asset> {
    private static volatile Asset[] _emptyArray;
    public AndroidAppAsset androidApp;
    public WebAsset web;

    public static Asset[] emptyArray() {
        if (_emptyArray == null) {
            synchronized (InternalNano.LAZY_INIT_LOCK) {
                if (_emptyArray == null) {
                    _emptyArray = new Asset[0];
                }
            }
        }
        return _emptyArray;
    }

    public Asset() {
        clear();
    }

    public Asset clear() {
        this.web = null;
        this.androidApp = null;
        this.unknownFieldData = null;
        this.cachedSize = -1;
        return this;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Asset)) {
            return false;
        }
        Asset other = (Asset) o;
        if (this.web == null) {
            if (other.web != null) {
                return false;
            }
        } else if (!this.web.equals(other.web)) {
            return false;
        }
        if (this.androidApp == null) {
            if (other.androidApp != null) {
                return false;
            }
        } else if (!this.androidApp.equals(other.androidApp)) {
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
        int hashCode = (((((getClass().getName().hashCode() + 527) * 31) + (this.web == null ? 0 : this.web.hashCode())) * 31) + (this.androidApp == null ? 0 : this.androidApp.hashCode())) * 31;
        if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
            i = this.unknownFieldData.hashCode();
        }
        return hashCode + i;
    }

    public void writeTo(CodedOutputByteBufferNano output) throws IOException {
        if (this.web != null) {
            output.writeMessage(1, this.web);
        }
        if (this.androidApp != null) {
            output.writeMessage(2, this.androidApp);
        }
        super.writeTo(output);
    }

    protected int computeSerializedSize() {
        int size = super.computeSerializedSize();
        if (this.web != null) {
            size += CodedOutputByteBufferNano.computeMessageSize(1, this.web);
        }
        if (this.androidApp != null) {
            return size + CodedOutputByteBufferNano.computeMessageSize(2, this.androidApp);
        }
        return size;
    }

    public Asset mergeFrom(CodedInputByteBufferNano input) throws IOException {
        while (true) {
            int tag = input.readTag();
            switch (tag) {
                case Action.UNKNOWN /*0*/:
                    break;
                case Type.TAP_ABOUT /*10*/:
                    if (this.web == null) {
                        this.web = new WebAsset();
                    }
                    input.readMessage(this.web);
                    continue;
                case LogSource.GOOGLE_ANALYTICS /*18*/:
                    if (this.androidApp == null) {
                        this.androidApp = new AndroidAppAsset();
                    }
                    input.readMessage(this.androidApp);
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

    public static Asset parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
        return (Asset) MessageNano.mergeFrom(new Asset(), data);
    }

    public static Asset parseFrom(CodedInputByteBufferNano input) throws IOException {
        return new Asset().mergeFrom(input);
    }
}
