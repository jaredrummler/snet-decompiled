package com.google.geo.sidekick;

import com.google.geo.sidekick.ResponsePayloadProto.ResponsePayload;
import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.ExtendableMessageNano;
import com.google.protobuf.nano.InternalNano;
import com.google.protobuf.nano.InvalidProtocolBufferNanoException;
import com.google.protobuf.nano.MessageNano;
import com.google.wireless.android.play.playlog.proto.ClientAnalytics.LogRequest.LogSource;
import com.google.wireless.android.play.playlog.proto.UserLocation.LaunchSettingsEvent.Action;
import com.google.wireless.android.play.playlog.proto.UserLocation.LocationSharingEvent.Type;
import com.google.wireless.android.play.playlog.proto.UserLocation.OvenFreshEvent.OvenFreshResult;
import com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection;
import java.io.IOException;

public interface SidekickHttpResponseProto {

    public static final class SidekickHttpResponse extends ExtendableMessageNano<SidekickHttpResponse> {
        private static volatile SidekickHttpResponse[] _emptyArray;
        public int errorCode;
        public ResponsePayload payload;
        public int status;

        public interface Status {
            public static final int ERROR = 2;
            public static final int OK = 1;
        }

        public static SidekickHttpResponse[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new SidekickHttpResponse[0];
                    }
                }
            }
            return _emptyArray;
        }

        public SidekickHttpResponse() {
            clear();
        }

        public SidekickHttpResponse clear() {
            this.status = 1;
            this.errorCode = 12;
            this.payload = null;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof SidekickHttpResponse)) {
                return false;
            }
            SidekickHttpResponse other = (SidekickHttpResponse) o;
            if (this.status != other.status || this.errorCode != other.errorCode) {
                return false;
            }
            if (this.payload == null) {
                if (other.payload != null) {
                    return false;
                }
            } else if (!this.payload.equals(other.payload)) {
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
            int hashCode = (((((((getClass().getName().hashCode() + 527) * 31) + this.status) * 31) + this.errorCode) * 31) + (this.payload == null ? 0 : this.payload.hashCode())) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.status != 1) {
                output.writeInt32(1, this.status);
            }
            if (this.errorCode != 12) {
                output.writeInt32(2, this.errorCode);
            }
            if (this.payload != null) {
                output.writeMessage(3, this.payload);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.status != 1) {
                size += CodedOutputByteBufferNano.computeInt32Size(1, this.status);
            }
            if (this.errorCode != 12) {
                size += CodedOutputByteBufferNano.computeInt32Size(2, this.errorCode);
            }
            if (this.payload != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(3, this.payload);
            }
            return size;
        }

        public SidekickHttpResponse mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                int value;
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_GET_LINK /*8*/:
                        value = input.readInt32();
                        switch (value) {
                            case TimeSelection.Type.TEMPORARY /*1*/:
                            case TimeSelection.Type.INDEFINITELY /*2*/:
                                this.status = value;
                                break;
                            default:
                                continue;
                        }
                    case LogSource.GMS_CORE_PEOPLE /*16*/:
                        value = input.readInt32();
                        switch (value) {
                            case TimeSelection.Type.TEMPORARY /*1*/:
                            case TimeSelection.Type.INDEFINITELY /*2*/:
                            case TimeSelection.Type.CUSTOM /*3*/:
                            case OvenFreshResult.NO_ACCOUNTS /*4*/:
                            case Type.ADD_NEW_SHARES /*5*/:
                            case Type.REMOVE_SHARE /*6*/:
                            case Type.RESET_TIME /*7*/:
                            case Type.TAP_GET_LINK /*8*/:
                            case Type.CREATE_LINK /*9*/:
                            case Type.TAP_ABOUT /*10*/:
                            case Type.TAP_LEARN_MORE /*11*/:
                            case Type.SWITCH_ACCOUNT /*12*/:
                            case Type.DISPLAY_ERROR /*13*/:
                            case Type.LAUNCH_SETTINGS /*14*/:
                            case Type.OVEN_FRESH /*15*/:
                            case LogSource.GMS_CORE_PEOPLE /*16*/:
                                this.errorCode = value;
                                break;
                            default:
                                continue;
                        }
                    case LogSource.ANDROID_CAMERA /*26*/:
                        if (this.payload == null) {
                            this.payload = new ResponsePayload();
                        }
                        input.readMessage(this.payload);
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

        public static SidekickHttpResponse parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (SidekickHttpResponse) MessageNano.mergeFrom(new SidekickHttpResponse(), data);
        }

        public static SidekickHttpResponse parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new SidekickHttpResponse().mergeFrom(input);
        }
    }
}
