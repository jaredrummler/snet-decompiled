package com.google.android.gms.udc.proto;

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

public final class ViewHeader extends ExtendableMessageNano<ViewHeader> {
    private static volatile ViewHeader[] _emptyArray;
    public ImageResource icon;
    public TextResource title;

    public static ViewHeader[] emptyArray() {
        if (_emptyArray == null) {
            synchronized (InternalNano.LAZY_INIT_LOCK) {
                if (_emptyArray == null) {
                    _emptyArray = new ViewHeader[0];
                }
            }
        }
        return _emptyArray;
    }

    public ViewHeader() {
        clear();
    }

    public ViewHeader clear() {
        this.title = null;
        this.icon = null;
        this.unknownFieldData = null;
        this.cachedSize = -1;
        return this;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ViewHeader)) {
            return false;
        }
        ViewHeader other = (ViewHeader) o;
        if (this.title == null) {
            if (other.title != null) {
                return false;
            }
        } else if (!this.title.equals(other.title)) {
            return false;
        }
        if (this.icon == null) {
            if (other.icon != null) {
                return false;
            }
        } else if (!this.icon.equals(other.icon)) {
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
        int hashCode = (((((getClass().getName().hashCode() + 527) * 31) + (this.title == null ? 0 : this.title.hashCode())) * 31) + (this.icon == null ? 0 : this.icon.hashCode())) * 31;
        if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
            i = this.unknownFieldData.hashCode();
        }
        return hashCode + i;
    }

    public void writeTo(CodedOutputByteBufferNano output) throws IOException {
        if (this.title != null) {
            output.writeMessage(1, this.title);
        }
        if (this.icon != null) {
            output.writeMessage(2, this.icon);
        }
        super.writeTo(output);
    }

    protected int computeSerializedSize() {
        int size = super.computeSerializedSize();
        if (this.title != null) {
            size += CodedOutputByteBufferNano.computeMessageSize(1, this.title);
        }
        if (this.icon != null) {
            return size + CodedOutputByteBufferNano.computeMessageSize(2, this.icon);
        }
        return size;
    }

    public ViewHeader mergeFrom(CodedInputByteBufferNano input) throws IOException {
        while (true) {
            int tag = input.readTag();
            switch (tag) {
                case Action.UNKNOWN /*0*/:
                    break;
                case Type.TAP_ABOUT /*10*/:
                    if (this.title == null) {
                        this.title = new TextResource();
                    }
                    input.readMessage(this.title);
                    continue;
                case LogSource.GOOGLE_ANALYTICS /*18*/:
                    if (this.icon == null) {
                        this.icon = new ImageResource();
                    }
                    input.readMessage(this.icon);
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

    public static ViewHeader parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
        return (ViewHeader) MessageNano.mergeFrom(new ViewHeader(), data);
    }

    public static ViewHeader parseFrom(CodedInputByteBufferNano input) throws IOException {
        return new ViewHeader().mergeFrom(input);
    }
}
