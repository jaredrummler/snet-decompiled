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
import java.io.IOException;

public interface SensorSignalsProto {

    public static final class SensorSignals extends ExtendableMessageNano<SensorSignals> {
        private static volatile SensorSignals[] _emptyArray;
        public String locale;
        public long timestampSeconds;
        public int timezoneOffsetSeconds;

        public static SensorSignals[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new SensorSignals[0];
                    }
                }
            }
            return _emptyArray;
        }

        public SensorSignals() {
            clear();
        }

        public SensorSignals clear() {
            this.locale = BuildConfig.VERSION_NAME;
            this.timestampSeconds = 0;
            this.timezoneOffsetSeconds = 0;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof SensorSignals)) {
                return false;
            }
            SensorSignals other = (SensorSignals) o;
            if (this.locale == null) {
                if (other.locale != null) {
                    return false;
                }
            } else if (!this.locale.equals(other.locale)) {
                return false;
            }
            if (this.timestampSeconds != other.timestampSeconds || this.timezoneOffsetSeconds != other.timezoneOffsetSeconds) {
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
            int hashCode = (((((((getClass().getName().hashCode() + 527) * 31) + (this.locale == null ? 0 : this.locale.hashCode())) * 31) + ((int) (this.timestampSeconds ^ (this.timestampSeconds >>> 32)))) * 31) + this.timezoneOffsetSeconds) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (!this.locale.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(2, this.locale);
            }
            if (this.timestampSeconds != 0) {
                output.writeInt64(6, this.timestampSeconds);
            }
            if (this.timezoneOffsetSeconds != 0) {
                output.writeInt32(7, this.timezoneOffsetSeconds);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (!this.locale.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(2, this.locale);
            }
            if (this.timestampSeconds != 0) {
                size += CodedOutputByteBufferNano.computeInt64Size(6, this.timestampSeconds);
            }
            if (this.timezoneOffsetSeconds != 0) {
                return size + CodedOutputByteBufferNano.computeInt32Size(7, this.timezoneOffsetSeconds);
            }
            return size;
        }

        public SensorSignals mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case LogSource.GOOGLE_ANALYTICS /*18*/:
                        this.locale = input.readString();
                        continue;
                    case LogSource.BACKDROP /*48*/:
                        this.timestampSeconds = input.readInt64();
                        continue;
                    case LogSource.DOCS /*56*/:
                        this.timezoneOffsetSeconds = input.readInt32();
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

        public static SensorSignals parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (SensorSignals) MessageNano.mergeFrom(new SensorSignals(), data);
        }

        public static SensorSignals parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new SensorSignals().mergeFrom(input);
        }
    }
}
