package com.google.geo.sidekick;

import com.google.geo.sidekick.OptInProto.OptInResponse;
import com.google.geo.sidekick.ThirdPartyProto.ThirdPartyAssociationResponse;
import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.ExtendableMessageNano;
import com.google.protobuf.nano.InternalNano;
import com.google.protobuf.nano.InvalidProtocolBufferNanoException;
import com.google.protobuf.nano.MessageNano;
import com.google.wireless.android.play.playlog.proto.UserLocation.LaunchSettingsEvent.Action;
import java.io.IOException;

public interface ResponsePayloadProto {

    public static final class ResponsePayload extends ExtendableMessageNano<ResponsePayload> {
        private static volatile ResponsePayload[] _emptyArray;
        public OptInResponse optInResponse;
        public ThirdPartyAssociationResponse thirdPartyAssociationResponse;

        public static ResponsePayload[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new ResponsePayload[0];
                    }
                }
            }
            return _emptyArray;
        }

        public ResponsePayload() {
            clear();
        }

        public ResponsePayload clear() {
            this.optInResponse = null;
            this.thirdPartyAssociationResponse = null;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof ResponsePayload)) {
                return false;
            }
            ResponsePayload other = (ResponsePayload) o;
            if (this.optInResponse == null) {
                if (other.optInResponse != null) {
                    return false;
                }
            } else if (!this.optInResponse.equals(other.optInResponse)) {
                return false;
            }
            if (this.thirdPartyAssociationResponse == null) {
                if (other.thirdPartyAssociationResponse != null) {
                    return false;
                }
            } else if (!this.thirdPartyAssociationResponse.equals(other.thirdPartyAssociationResponse)) {
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
            int hashCode = (((((getClass().getName().hashCode() + 527) * 31) + (this.optInResponse == null ? 0 : this.optInResponse.hashCode())) * 31) + (this.thirdPartyAssociationResponse == null ? 0 : this.thirdPartyAssociationResponse.hashCode())) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.optInResponse != null) {
                output.writeMessage(24, this.optInResponse);
            }
            if (this.thirdPartyAssociationResponse != null) {
                output.writeMessage(25, this.thirdPartyAssociationResponse);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.optInResponse != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(24, this.optInResponse);
            }
            if (this.thirdPartyAssociationResponse != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(25, this.thirdPartyAssociationResponse);
            }
            return size;
        }

        public ResponsePayload mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case 194:
                        if (this.optInResponse == null) {
                            this.optInResponse = new OptInResponse();
                        }
                        input.readMessage(this.optInResponse);
                        continue;
                    case 202:
                        if (this.thirdPartyAssociationResponse == null) {
                            this.thirdPartyAssociationResponse = new ThirdPartyAssociationResponse();
                        }
                        input.readMessage(this.thirdPartyAssociationResponse);
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

        public static ResponsePayload parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (ResponsePayload) MessageNano.mergeFrom(new ResponsePayload(), data);
        }

        public static ResponsePayload parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new ResponsePayload().mergeFrom(input);
        }
    }
}
