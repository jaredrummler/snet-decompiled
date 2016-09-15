package com.google.digitalassetlinks.v1;

import com.google.android.gms.lint.BuildConfig;
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

public final class ListResponse extends ExtendableMessageNano<ListResponse> {
    private static volatile ListResponse[] _emptyArray;
    public String debugString;
    public Duration maxAge;
    public Statement[] statements;

    public static ListResponse[] emptyArray() {
        if (_emptyArray == null) {
            synchronized (InternalNano.LAZY_INIT_LOCK) {
                if (_emptyArray == null) {
                    _emptyArray = new ListResponse[0];
                }
            }
        }
        return _emptyArray;
    }

    public ListResponse() {
        clear();
    }

    public ListResponse clear() {
        this.statements = Statement.emptyArray();
        this.maxAge = null;
        this.debugString = BuildConfig.VERSION_NAME;
        this.unknownFieldData = null;
        this.cachedSize = -1;
        return this;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ListResponse)) {
            return false;
        }
        ListResponse other = (ListResponse) o;
        if (!InternalNano.equals(this.statements, other.statements)) {
            return false;
        }
        if (this.maxAge == null) {
            if (other.maxAge != null) {
                return false;
            }
        } else if (!this.maxAge.equals(other.maxAge)) {
            return false;
        }
        if (this.debugString == null) {
            if (other.debugString != null) {
                return false;
            }
        } else if (!this.debugString.equals(other.debugString)) {
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
        int hashCode = (((((((getClass().getName().hashCode() + 527) * 31) + InternalNano.hashCode(this.statements)) * 31) + (this.maxAge == null ? 0 : this.maxAge.hashCode())) * 31) + (this.debugString == null ? 0 : this.debugString.hashCode())) * 31;
        if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
            i = this.unknownFieldData.hashCode();
        }
        return hashCode + i;
    }

    public void writeTo(CodedOutputByteBufferNano output) throws IOException {
        if (this.statements != null && this.statements.length > 0) {
            for (Statement element : this.statements) {
                if (element != null) {
                    output.writeMessage(1, element);
                }
            }
        }
        if (this.maxAge != null) {
            output.writeMessage(2, this.maxAge);
        }
        if (!this.debugString.equals(BuildConfig.VERSION_NAME)) {
            output.writeString(3, this.debugString);
        }
        super.writeTo(output);
    }

    protected int computeSerializedSize() {
        int size = super.computeSerializedSize();
        if (this.statements != null && this.statements.length > 0) {
            for (Statement element : this.statements) {
                if (element != null) {
                    size += CodedOutputByteBufferNano.computeMessageSize(1, element);
                }
            }
        }
        if (this.maxAge != null) {
            size += CodedOutputByteBufferNano.computeMessageSize(2, this.maxAge);
        }
        if (this.debugString.equals(BuildConfig.VERSION_NAME)) {
            return size;
        }
        return size + CodedOutputByteBufferNano.computeStringSize(3, this.debugString);
    }

    public ListResponse mergeFrom(CodedInputByteBufferNano input) throws IOException {
        while (true) {
            int tag = input.readTag();
            switch (tag) {
                case Action.UNKNOWN /*0*/:
                    break;
                case Type.TAP_ABOUT /*10*/:
                    int i;
                    int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 10);
                    if (this.statements == null) {
                        i = 0;
                    } else {
                        i = this.statements.length;
                    }
                    Statement[] newArray = new Statement[(i + arrayLength)];
                    if (i != 0) {
                        System.arraycopy(this.statements, 0, newArray, 0, i);
                    }
                    while (i < newArray.length - 1) {
                        newArray[i] = new Statement();
                        input.readMessage(newArray[i]);
                        input.readTag();
                        i++;
                    }
                    newArray[i] = new Statement();
                    input.readMessage(newArray[i]);
                    this.statements = newArray;
                    continue;
                case LogSource.GOOGLE_ANALYTICS /*18*/:
                    if (this.maxAge == null) {
                        this.maxAge = new Duration();
                    }
                    input.readMessage(this.maxAge);
                    continue;
                case LogSource.ANDROID_CAMERA /*26*/:
                    this.debugString = input.readString();
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

    public static ListResponse parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
        return (ListResponse) MessageNano.mergeFrom(new ListResponse(), data);
    }

    public static ListResponse parseFrom(CodedInputByteBufferNano input) throws IOException {
        return new ListResponse().mergeFrom(input);
    }
}
