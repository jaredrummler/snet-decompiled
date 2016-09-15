package com.google.android.gms.udc.proto;

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

public final class InfoNotice extends ExtendableMessageNano<InfoNotice> {
    private static volatile InfoNotice[] _emptyArray;
    public ButtonConfig dismissButton;
    public TextResource[] notices;
    public TextResource title;

    public static InfoNotice[] emptyArray() {
        if (_emptyArray == null) {
            synchronized (InternalNano.LAZY_INIT_LOCK) {
                if (_emptyArray == null) {
                    _emptyArray = new InfoNotice[0];
                }
            }
        }
        return _emptyArray;
    }

    public InfoNotice() {
        clear();
    }

    public InfoNotice clear() {
        this.title = null;
        this.notices = TextResource.emptyArray();
        this.dismissButton = null;
        this.unknownFieldData = null;
        this.cachedSize = -1;
        return this;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof InfoNotice)) {
            return false;
        }
        InfoNotice other = (InfoNotice) o;
        if (this.title == null) {
            if (other.title != null) {
                return false;
            }
        } else if (!this.title.equals(other.title)) {
            return false;
        }
        if (!InternalNano.equals(this.notices, other.notices)) {
            return false;
        }
        if (this.dismissButton == null) {
            if (other.dismissButton != null) {
                return false;
            }
        } else if (!this.dismissButton.equals(other.dismissButton)) {
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
        int hashCode = (((((((getClass().getName().hashCode() + 527) * 31) + (this.title == null ? 0 : this.title.hashCode())) * 31) + InternalNano.hashCode(this.notices)) * 31) + (this.dismissButton == null ? 0 : this.dismissButton.hashCode())) * 31;
        if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
            i = this.unknownFieldData.hashCode();
        }
        return hashCode + i;
    }

    public void writeTo(CodedOutputByteBufferNano output) throws IOException {
        if (this.title != null) {
            output.writeMessage(1, this.title);
        }
        if (this.notices != null && this.notices.length > 0) {
            for (TextResource element : this.notices) {
                if (element != null) {
                    output.writeMessage(2, element);
                }
            }
        }
        if (this.dismissButton != null) {
            output.writeMessage(3, this.dismissButton);
        }
        super.writeTo(output);
    }

    protected int computeSerializedSize() {
        int size = super.computeSerializedSize();
        if (this.title != null) {
            size += CodedOutputByteBufferNano.computeMessageSize(1, this.title);
        }
        if (this.notices != null && this.notices.length > 0) {
            for (TextResource element : this.notices) {
                if (element != null) {
                    size += CodedOutputByteBufferNano.computeMessageSize(2, element);
                }
            }
        }
        if (this.dismissButton != null) {
            return size + CodedOutputByteBufferNano.computeMessageSize(3, this.dismissButton);
        }
        return size;
    }

    public InfoNotice mergeFrom(CodedInputByteBufferNano input) throws IOException {
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
                    int i;
                    int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 18);
                    if (this.notices == null) {
                        i = 0;
                    } else {
                        i = this.notices.length;
                    }
                    TextResource[] newArray = new TextResource[(i + arrayLength)];
                    if (i != 0) {
                        System.arraycopy(this.notices, 0, newArray, 0, i);
                    }
                    while (i < newArray.length - 1) {
                        newArray[i] = new TextResource();
                        input.readMessage(newArray[i]);
                        input.readTag();
                        i++;
                    }
                    newArray[i] = new TextResource();
                    input.readMessage(newArray[i]);
                    this.notices = newArray;
                    continue;
                case LogSource.ANDROID_CAMERA /*26*/:
                    if (this.dismissButton == null) {
                        this.dismissButton = new ButtonConfig();
                    }
                    input.readMessage(this.dismissButton);
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

    public static InfoNotice parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
        return (InfoNotice) MessageNano.mergeFrom(new InfoNotice(), data);
    }

    public static InfoNotice parseFrom(CodedInputByteBufferNano input) throws IOException {
        return new InfoNotice().mergeFrom(input);
    }
}
