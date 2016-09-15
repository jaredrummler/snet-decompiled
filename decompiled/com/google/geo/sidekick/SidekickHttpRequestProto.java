package com.google.geo.sidekick;

import com.google.geo.sidekick.ClientDescriptionProto.ClientDescription;
import com.google.geo.sidekick.RequestPayloadProto.RequestPayload;
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

public interface SidekickHttpRequestProto {

    public static final class SidekickHttpRequest extends ExtendableMessageNano<SidekickHttpRequest> {
        private static volatile SidekickHttpRequest[] _emptyArray;
        public ClientDescription client;
        public RequestPayload payload;
        public long timestampSeconds;

        public static SidekickHttpRequest[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new SidekickHttpRequest[0];
                    }
                }
            }
            return _emptyArray;
        }

        public SidekickHttpRequest() {
            clear();
        }

        public SidekickHttpRequest clear() {
            this.timestampSeconds = 0;
            this.client = null;
            this.payload = null;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof SidekickHttpRequest)) {
                return false;
            }
            SidekickHttpRequest other = (SidekickHttpRequest) o;
            if (this.timestampSeconds != other.timestampSeconds) {
                return false;
            }
            if (this.client == null) {
                if (other.client != null) {
                    return false;
                }
            } else if (!this.client.equals(other.client)) {
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
            int hashCode = (((((((getClass().getName().hashCode() + 527) * 31) + ((int) (this.timestampSeconds ^ (this.timestampSeconds >>> 32)))) * 31) + (this.client == null ? 0 : this.client.hashCode())) * 31) + (this.payload == null ? 0 : this.payload.hashCode())) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.timestampSeconds != 0) {
                output.writeInt64(1, this.timestampSeconds);
            }
            if (this.client != null) {
                output.writeMessage(3, this.client);
            }
            if (this.payload != null) {
                output.writeMessage(4, this.payload);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.timestampSeconds != 0) {
                size += CodedOutputByteBufferNano.computeInt64Size(1, this.timestampSeconds);
            }
            if (this.client != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(3, this.client);
            }
            if (this.payload != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(4, this.payload);
            }
            return size;
        }

        public SidekickHttpRequest mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_GET_LINK /*8*/:
                        this.timestampSeconds = input.readInt64();
                        continue;
                    case LogSource.ANDROID_CAMERA /*26*/:
                        if (this.client == null) {
                            this.client = new ClientDescription();
                        }
                        input.readMessage(this.client);
                        continue;
                    case LogSource.NOVA /*34*/:
                        if (this.payload == null) {
                            this.payload = new RequestPayload();
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

        public static SidekickHttpRequest parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (SidekickHttpRequest) MessageNano.mergeFrom(new SidekickHttpRequest(), data);
        }

        public static SidekickHttpRequest parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new SidekickHttpRequest().mergeFrom(input);
        }
    }
}
