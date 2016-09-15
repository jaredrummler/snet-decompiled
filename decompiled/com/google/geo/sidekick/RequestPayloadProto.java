package com.google.geo.sidekick;

import com.google.geo.sidekick.OptInProto.OptInQuery;
import com.google.geo.sidekick.SensorSignalsProto.SensorSignals;
import com.google.geo.sidekick.ThirdPartyProto.ThirdPartyAssociationQuery;
import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.ExtendableMessageNano;
import com.google.protobuf.nano.InternalNano;
import com.google.protobuf.nano.InvalidProtocolBufferNanoException;
import com.google.protobuf.nano.MessageNano;
import com.google.wireless.android.play.playlog.proto.ClientAnalytics.LogRequest.LogSource;
import com.google.wireless.android.play.playlog.proto.UserLocation.LaunchSettingsEvent.Action;
import java.io.IOException;

public interface RequestPayloadProto {

    public static final class RequestPayload extends ExtendableMessageNano<RequestPayload> {
        private static volatile RequestPayload[] _emptyArray;
        public OptInQuery optInQuery;
        public SensorSignals sensorSignals;
        public ThirdPartyAssociationQuery thirdPartyAssociationQuery;

        public static RequestPayload[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new RequestPayload[0];
                    }
                }
            }
            return _emptyArray;
        }

        public RequestPayload() {
            clear();
        }

        public RequestPayload clear() {
            this.sensorSignals = null;
            this.optInQuery = null;
            this.thirdPartyAssociationQuery = null;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof RequestPayload)) {
                return false;
            }
            RequestPayload other = (RequestPayload) o;
            if (this.sensorSignals == null) {
                if (other.sensorSignals != null) {
                    return false;
                }
            } else if (!this.sensorSignals.equals(other.sensorSignals)) {
                return false;
            }
            if (this.optInQuery == null) {
                if (other.optInQuery != null) {
                    return false;
                }
            } else if (!this.optInQuery.equals(other.optInQuery)) {
                return false;
            }
            if (this.thirdPartyAssociationQuery == null) {
                if (other.thirdPartyAssociationQuery != null) {
                    return false;
                }
            } else if (!this.thirdPartyAssociationQuery.equals(other.thirdPartyAssociationQuery)) {
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
            int hashCode = (((((((getClass().getName().hashCode() + 527) * 31) + (this.sensorSignals == null ? 0 : this.sensorSignals.hashCode())) * 31) + (this.optInQuery == null ? 0 : this.optInQuery.hashCode())) * 31) + (this.thirdPartyAssociationQuery == null ? 0 : this.thirdPartyAssociationQuery.hashCode())) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.sensorSignals != null) {
                output.writeMessage(15, this.sensorSignals);
            }
            if (this.optInQuery != null) {
                output.writeMessage(37, this.optInQuery);
            }
            if (this.thirdPartyAssociationQuery != null) {
                output.writeMessage(38, this.thirdPartyAssociationQuery);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.sensorSignals != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(15, this.sensorSignals);
            }
            if (this.optInQuery != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(37, this.optInQuery);
            }
            if (this.thirdPartyAssociationQuery != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(38, this.thirdPartyAssociationQuery);
            }
            return size;
        }

        public RequestPayload mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case LogSource.ANDROID_SNET_GCORE /*122*/:
                        if (this.sensorSignals == null) {
                            this.sensorSignals = new SensorSignals();
                        }
                        input.readMessage(this.sensorSignals);
                        continue;
                    case 298:
                        if (this.optInQuery == null) {
                            this.optInQuery = new OptInQuery();
                        }
                        input.readMessage(this.optInQuery);
                        continue;
                    case 306:
                        if (this.thirdPartyAssociationQuery == null) {
                            this.thirdPartyAssociationQuery = new ThirdPartyAssociationQuery();
                        }
                        input.readMessage(this.thirdPartyAssociationQuery);
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

        public static RequestPayload parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (RequestPayload) MessageNano.mergeFrom(new RequestPayload(), data);
        }

        public static RequestPayload parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new RequestPayload().mergeFrom(input);
        }
    }
}
