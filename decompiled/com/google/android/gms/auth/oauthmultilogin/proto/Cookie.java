package com.google.android.gms.auth.oauthmultilogin.proto;

import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.InternalNano;
import com.google.protobuf.nano.InvalidProtocolBufferNanoException;
import com.google.protobuf.nano.MessageNano;
import com.google.protobuf.nano.WireFormatNano;
import com.google.wireless.android.play.playlog.proto.ClientAnalytics.LogRequest.LogSource;
import com.google.wireless.android.play.playlog.proto.UserLocation.LaunchSettingsEvent.Action;
import com.google.wireless.android.play.playlog.proto.UserLocation.LocationSharingEvent.Type;
import java.io.IOException;

public final class Cookie extends MessageNano {
    public static final int HIGH = 3;
    public static final int LOW = 1;
    public static final int MEDIUM = 2;
    public static final int UNKNOWN_PRIORITY = 0;
    private static volatile Cookie[] _emptyArray;
    public String domain;
    public String host;
    public Boolean isHttpOnly;
    public Boolean isSecure;
    public Integer maxAge;
    public String name;
    public String path;
    public Integer priority;
    public String value;

    public static Cookie[] emptyArray() {
        if (_emptyArray == null) {
            synchronized (InternalNano.LAZY_INIT_LOCK) {
                if (_emptyArray == null) {
                    _emptyArray = new Cookie[0];
                }
            }
        }
        return _emptyArray;
    }

    public Cookie() {
        clear();
    }

    public Cookie clear() {
        this.name = null;
        this.value = null;
        this.domain = null;
        this.host = null;
        this.path = null;
        this.isSecure = null;
        this.isHttpOnly = null;
        this.maxAge = null;
        this.priority = null;
        this.cachedSize = -1;
        return this;
    }

    public void writeTo(CodedOutputByteBufferNano output) throws IOException {
        if (this.name != null) {
            output.writeString(LOW, this.name);
        }
        if (this.value != null) {
            output.writeString(MEDIUM, this.value);
        }
        if (this.domain != null) {
            output.writeString(HIGH, this.domain);
        }
        if (this.host != null) {
            output.writeString(4, this.host);
        }
        if (this.path != null) {
            output.writeString(5, this.path);
        }
        if (this.isSecure != null) {
            output.writeBool(6, this.isSecure.booleanValue());
        }
        if (this.isHttpOnly != null) {
            output.writeBool(7, this.isHttpOnly.booleanValue());
        }
        if (this.maxAge != null) {
            output.writeInt32(8, this.maxAge.intValue());
        }
        if (this.priority != null) {
            output.writeInt32(9, this.priority.intValue());
        }
        super.writeTo(output);
    }

    protected int computeSerializedSize() {
        int size = super.computeSerializedSize();
        if (this.name != null) {
            size += CodedOutputByteBufferNano.computeStringSize(LOW, this.name);
        }
        if (this.value != null) {
            size += CodedOutputByteBufferNano.computeStringSize(MEDIUM, this.value);
        }
        if (this.domain != null) {
            size += CodedOutputByteBufferNano.computeStringSize(HIGH, this.domain);
        }
        if (this.host != null) {
            size += CodedOutputByteBufferNano.computeStringSize(4, this.host);
        }
        if (this.path != null) {
            size += CodedOutputByteBufferNano.computeStringSize(5, this.path);
        }
        if (this.isSecure != null) {
            size += CodedOutputByteBufferNano.computeBoolSize(6, this.isSecure.booleanValue());
        }
        if (this.isHttpOnly != null) {
            size += CodedOutputByteBufferNano.computeBoolSize(7, this.isHttpOnly.booleanValue());
        }
        if (this.maxAge != null) {
            size += CodedOutputByteBufferNano.computeInt32Size(8, this.maxAge.intValue());
        }
        if (this.priority != null) {
            return size + CodedOutputByteBufferNano.computeInt32Size(9, this.priority.intValue());
        }
        return size;
    }

    public Cookie mergeFrom(CodedInputByteBufferNano input) throws IOException {
        while (true) {
            int tag = input.readTag();
            switch (tag) {
                case Action.UNKNOWN /*0*/:
                    break;
                case Type.TAP_ABOUT /*10*/:
                    this.name = input.readString();
                    continue;
                case LogSource.GOOGLE_ANALYTICS /*18*/:
                    this.value = input.readString();
                    continue;
                case LogSource.ANDROID_CAMERA /*26*/:
                    this.domain = input.readString();
                    continue;
                case LogSource.NOVA /*34*/:
                    this.host = input.readString();
                    continue;
                case LogSource.PHOTOS /*42*/:
                    this.path = input.readString();
                    continue;
                case LogSource.BACKDROP /*48*/:
                    this.isSecure = Boolean.valueOf(input.readBool());
                    continue;
                case LogSource.DOCS /*56*/:
                    this.isHttpOnly = Boolean.valueOf(input.readBool());
                    continue;
                case LogSource.KIDS_COMMUNICATOR /*64*/:
                    this.maxAge = Integer.valueOf(input.readInt32());
                    continue;
                case LogSource.JUSTSPEAK /*72*/:
                    int value = input.readInt32();
                    switch (value) {
                        case Action.UNKNOWN /*0*/:
                        case LOW /*1*/:
                        case MEDIUM /*2*/:
                        case HIGH /*3*/:
                            this.priority = Integer.valueOf(value);
                            break;
                        default:
                            continue;
                    }
                default:
                    if (!WireFormatNano.parseUnknownField(input, tag)) {
                        break;
                    }
                    continue;
            }
            return this;
        }
    }

    public static Cookie parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
        return (Cookie) MessageNano.mergeFrom(new Cookie(), data);
    }

    public static Cookie parseFrom(CodedInputByteBufferNano input) throws IOException {
        return new Cookie().mergeFrom(input);
    }
}
