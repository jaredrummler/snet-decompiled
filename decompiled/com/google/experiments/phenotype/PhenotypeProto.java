package com.google.experiments.phenotype;

import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.ExtendableMessageNano;
import com.google.protobuf.nano.InternalNano;
import com.google.protobuf.nano.InvalidProtocolBufferNanoException;
import com.google.protobuf.nano.MessageNano;
import com.google.wireless.android.play.playlog.proto.UserLocation.LaunchSettingsEvent.Action;
import com.google.wireless.android.play.playlog.proto.UserLocation.LocationSharingEvent.Type;
import java.io.IOException;

public interface PhenotypeProto {

    public static final class ApplicationProperties extends ExtendableMessageNano<ApplicationProperties> {
        private static volatile ApplicationProperties[] _emptyArray;
        public int gmscoreVersionCode;

        public static ApplicationProperties[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new ApplicationProperties[0];
                    }
                }
            }
            return _emptyArray;
        }

        public ApplicationProperties() {
            clear();
        }

        public ApplicationProperties clear() {
            this.gmscoreVersionCode = 0;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof ApplicationProperties)) {
                return false;
            }
            ApplicationProperties other = (ApplicationProperties) o;
            if (this.gmscoreVersionCode != other.gmscoreVersionCode) {
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
            int hashCode = (((getClass().getName().hashCode() + 527) * 31) + this.gmscoreVersionCode) * 31;
            int hashCode2 = (this.unknownFieldData == null || this.unknownFieldData.isEmpty()) ? 0 : this.unknownFieldData.hashCode();
            return hashCode + hashCode2;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.gmscoreVersionCode != 0) {
                output.writeInt32(1, this.gmscoreVersionCode);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.gmscoreVersionCode != 0) {
                return size + CodedOutputByteBufferNano.computeInt32Size(1, this.gmscoreVersionCode);
            }
            return size;
        }

        public ApplicationProperties mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_GET_LINK /*8*/:
                        this.gmscoreVersionCode = input.readInt32();
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

        public static ApplicationProperties parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (ApplicationProperties) MessageNano.mergeFrom(new ApplicationProperties(), data);
        }

        public static ApplicationProperties parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new ApplicationProperties().mergeFrom(input);
        }
    }
}
