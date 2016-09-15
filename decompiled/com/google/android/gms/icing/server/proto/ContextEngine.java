package com.google.android.gms.icing.server.proto;

import com.google.android.gms.icing.server.proto.Seldon.ContextRequest;
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
import java.util.Arrays;

public interface ContextEngine {

    public static final class IcingRequest extends ExtendableMessageNano<IcingRequest> {
        private static volatile IcingRequest[] _emptyArray;
        public ContextRequest contextRequest;
        public String serializedRequest;

        public static IcingRequest[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new IcingRequest[0];
                    }
                }
            }
            return _emptyArray;
        }

        public IcingRequest() {
            clear();
        }

        public IcingRequest clear() {
            this.serializedRequest = BuildConfig.VERSION_NAME;
            this.contextRequest = null;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof IcingRequest)) {
                return false;
            }
            IcingRequest other = (IcingRequest) o;
            if (this.serializedRequest == null) {
                if (other.serializedRequest != null) {
                    return false;
                }
            } else if (!this.serializedRequest.equals(other.serializedRequest)) {
                return false;
            }
            if (this.contextRequest == null) {
                if (other.contextRequest != null) {
                    return false;
                }
            } else if (!this.contextRequest.equals(other.contextRequest)) {
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
            int hashCode = (((((getClass().getName().hashCode() + 527) * 31) + (this.serializedRequest == null ? 0 : this.serializedRequest.hashCode())) * 31) + (this.contextRequest == null ? 0 : this.contextRequest.hashCode())) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (!this.serializedRequest.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(4, this.serializedRequest);
            }
            if (this.contextRequest != null) {
                output.writeMessage(5, this.contextRequest);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (!this.serializedRequest.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(4, this.serializedRequest);
            }
            if (this.contextRequest != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(5, this.contextRequest);
            }
            return size;
        }

        public IcingRequest mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case LogSource.NOVA /*34*/:
                        this.serializedRequest = input.readString();
                        continue;
                    case LogSource.PHOTOS /*42*/:
                        if (this.contextRequest == null) {
                            this.contextRequest = new ContextRequest();
                        }
                        input.readMessage(this.contextRequest);
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

        public static IcingRequest parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (IcingRequest) MessageNano.mergeFrom(new IcingRequest(), data);
        }

        public static IcingRequest parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new IcingRequest().mergeFrom(input);
        }
    }

    public static final class IcingResponse extends ExtendableMessageNano<IcingResponse> {
        private static volatile IcingResponse[] _emptyArray;
        public byte[] serializedResponse;

        public static IcingResponse[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new IcingResponse[0];
                    }
                }
            }
            return _emptyArray;
        }

        public IcingResponse() {
            clear();
        }

        public IcingResponse clear() {
            this.serializedResponse = WireFormatNano.EMPTY_BYTES;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof IcingResponse)) {
                return false;
            }
            IcingResponse other = (IcingResponse) o;
            if (!Arrays.equals(this.serializedResponse, other.serializedResponse)) {
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
            int hashCode = (((getClass().getName().hashCode() + 527) * 31) + Arrays.hashCode(this.serializedResponse)) * 31;
            int hashCode2 = (this.unknownFieldData == null || this.unknownFieldData.isEmpty()) ? 0 : this.unknownFieldData.hashCode();
            return hashCode + hashCode2;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (!Arrays.equals(this.serializedResponse, WireFormatNano.EMPTY_BYTES)) {
                output.writeBytes(1, this.serializedResponse);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (Arrays.equals(this.serializedResponse, WireFormatNano.EMPTY_BYTES)) {
                return size;
            }
            return size + CodedOutputByteBufferNano.computeBytesSize(1, this.serializedResponse);
        }

        public IcingResponse mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        this.serializedResponse = input.readBytes();
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

        public static IcingResponse parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (IcingResponse) MessageNano.mergeFrom(new IcingResponse(), data);
        }

        public static IcingResponse parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new IcingResponse().mergeFrom(input);
        }
    }
}
