package com.google.geo.sidekick;

import com.google.android.gms.lint.BuildConfig;
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

public interface DeviceProto {

    public static final class Device extends ExtendableMessageNano<Device> {
        private static volatile Device[] _emptyArray;
        public String manufacturer;
        public String model;

        public static Device[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new Device[0];
                    }
                }
            }
            return _emptyArray;
        }

        public Device() {
            clear();
        }

        public Device clear() {
            this.manufacturer = BuildConfig.VERSION_NAME;
            this.model = BuildConfig.VERSION_NAME;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof Device)) {
                return false;
            }
            Device other = (Device) o;
            if (this.manufacturer == null) {
                if (other.manufacturer != null) {
                    return false;
                }
            } else if (!this.manufacturer.equals(other.manufacturer)) {
                return false;
            }
            if (this.model == null) {
                if (other.model != null) {
                    return false;
                }
            } else if (!this.model.equals(other.model)) {
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
            int hashCode = (((((getClass().getName().hashCode() + 527) * 31) + (this.manufacturer == null ? 0 : this.manufacturer.hashCode())) * 31) + (this.model == null ? 0 : this.model.hashCode())) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (!this.manufacturer.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(1, this.manufacturer);
            }
            if (!this.model.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(2, this.model);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (!this.manufacturer.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.manufacturer);
            }
            if (this.model.equals(BuildConfig.VERSION_NAME)) {
                return size;
            }
            return size + CodedOutputByteBufferNano.computeStringSize(2, this.model);
        }

        public Device mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        this.manufacturer = input.readString();
                        continue;
                    case LogSource.GOOGLE_ANALYTICS /*18*/:
                        this.model = input.readString();
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

        public static Device parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (Device) MessageNano.mergeFrom(new Device(), data);
        }

        public static Device parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new Device().mergeFrom(input);
        }
    }
}
