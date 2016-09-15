package com.google.wireless.android.play.playlog.proto;

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

public interface CountersProto {

    public static final class Bucket extends ExtendableMessageNano<Bucket> {
        private static volatile Bucket[] _emptyArray;
        public long count;
        public long key;

        public static Bucket[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new Bucket[0];
                    }
                }
            }
            return _emptyArray;
        }

        public Bucket() {
            clear();
        }

        public Bucket clear() {
            this.key = 0;
            this.count = 0;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof Bucket)) {
                return false;
            }
            Bucket other = (Bucket) o;
            if (this.key != other.key || this.count != other.count) {
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
            int hashCode = (((((getClass().getName().hashCode() + 527) * 31) + ((int) (this.key ^ (this.key >>> 32)))) * 31) + ((int) (this.count ^ (this.count >>> 32)))) * 31;
            int hashCode2 = (this.unknownFieldData == null || this.unknownFieldData.isEmpty()) ? 0 : this.unknownFieldData.hashCode();
            return hashCode + hashCode2;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.key != 0) {
                output.writeInt64(1, this.key);
            }
            if (this.count != 0) {
                output.writeInt64(2, this.count);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.key != 0) {
                size += CodedOutputByteBufferNano.computeInt64Size(1, this.key);
            }
            if (this.count != 0) {
                return size + CodedOutputByteBufferNano.computeInt64Size(2, this.count);
            }
            return size;
        }

        public Bucket mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_GET_LINK /*8*/:
                        this.key = input.readInt64();
                        continue;
                    case LogSource.GMS_CORE_PEOPLE /*16*/:
                        this.count = input.readInt64();
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

        public static Bucket parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (Bucket) MessageNano.mergeFrom(new Bucket(), data);
        }

        public static Bucket parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new Bucket().mergeFrom(input);
        }
    }

    public static final class Counter extends ExtendableMessageNano<Counter> {
        private static volatile Counter[] _emptyArray;
        public Bucket[] bucket;
        public long hashedName;
        public String name;

        public static Counter[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new Counter[0];
                    }
                }
            }
            return _emptyArray;
        }

        public Counter() {
            clear();
        }

        public Counter clear() {
            this.hashedName = 0;
            this.name = BuildConfig.VERSION_NAME;
            this.bucket = Bucket.emptyArray();
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof Counter)) {
                return false;
            }
            Counter other = (Counter) o;
            if (this.hashedName != other.hashedName) {
                return false;
            }
            if (this.name == null) {
                if (other.name != null) {
                    return false;
                }
            } else if (!this.name.equals(other.name)) {
                return false;
            }
            if (!InternalNano.equals(this.bucket, other.bucket)) {
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
            int hashCode = (((((((getClass().getName().hashCode() + 527) * 31) + ((int) (this.hashedName ^ (this.hashedName >>> 32)))) * 31) + (this.name == null ? 0 : this.name.hashCode())) * 31) + InternalNano.hashCode(this.bucket)) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.hashedName != 0) {
                output.writeFixed64(1, this.hashedName);
            }
            if (!this.name.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(2, this.name);
            }
            if (this.bucket != null && this.bucket.length > 0) {
                for (Bucket element : this.bucket) {
                    if (element != null) {
                        output.writeMessage(3, element);
                    }
                }
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.hashedName != 0) {
                size += CodedOutputByteBufferNano.computeFixed64Size(1, this.hashedName);
            }
            if (!this.name.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(2, this.name);
            }
            if (this.bucket != null && this.bucket.length > 0) {
                for (Bucket element : this.bucket) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(3, element);
                    }
                }
            }
            return size;
        }

        public Counter mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.CREATE_LINK /*9*/:
                        this.hashedName = input.readFixed64();
                        continue;
                    case LogSource.GOOGLE_ANALYTICS /*18*/:
                        this.name = input.readString();
                        continue;
                    case LogSource.ANDROID_CAMERA /*26*/:
                        int i;
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 26);
                        if (this.bucket == null) {
                            i = 0;
                        } else {
                            i = this.bucket.length;
                        }
                        Bucket[] newArray = new Bucket[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.bucket, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new Bucket();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i++;
                        }
                        newArray[i] = new Bucket();
                        input.readMessage(newArray[i]);
                        this.bucket = newArray;
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

        public static Counter parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (Counter) MessageNano.mergeFrom(new Counter(), data);
        }

        public static Counter parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new Counter().mergeFrom(input);
        }
    }

    public static final class Counters extends ExtendableMessageNano<Counters> {
        private static volatile Counters[] _emptyArray;
        public Counter[] counter;
        public byte[] dimensionsInstance;
        public long uptimeMillis;

        public static Counters[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new Counters[0];
                    }
                }
            }
            return _emptyArray;
        }

        public Counters() {
            clear();
        }

        public Counters clear() {
            this.uptimeMillis = 0;
            this.counter = Counter.emptyArray();
            this.dimensionsInstance = WireFormatNano.EMPTY_BYTES;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof Counters)) {
                return false;
            }
            Counters other = (Counters) o;
            if (this.uptimeMillis != other.uptimeMillis || !InternalNano.equals(this.counter, other.counter) || !Arrays.equals(this.dimensionsInstance, other.dimensionsInstance)) {
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
            int hashCode = (((((((getClass().getName().hashCode() + 527) * 31) + ((int) (this.uptimeMillis ^ (this.uptimeMillis >>> 32)))) * 31) + InternalNano.hashCode(this.counter)) * 31) + Arrays.hashCode(this.dimensionsInstance)) * 31;
            int hashCode2 = (this.unknownFieldData == null || this.unknownFieldData.isEmpty()) ? 0 : this.unknownFieldData.hashCode();
            return hashCode + hashCode2;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.uptimeMillis != 0) {
                output.writeInt64(1, this.uptimeMillis);
            }
            if (this.counter != null && this.counter.length > 0) {
                for (Counter element : this.counter) {
                    if (element != null) {
                        output.writeMessage(2, element);
                    }
                }
            }
            if (!Arrays.equals(this.dimensionsInstance, WireFormatNano.EMPTY_BYTES)) {
                output.writeBytes(3, this.dimensionsInstance);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.uptimeMillis != 0) {
                size += CodedOutputByteBufferNano.computeInt64Size(1, this.uptimeMillis);
            }
            if (this.counter != null && this.counter.length > 0) {
                for (Counter element : this.counter) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(2, element);
                    }
                }
            }
            if (Arrays.equals(this.dimensionsInstance, WireFormatNano.EMPTY_BYTES)) {
                return size;
            }
            return size + CodedOutputByteBufferNano.computeBytesSize(3, this.dimensionsInstance);
        }

        public Counters mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_GET_LINK /*8*/:
                        this.uptimeMillis = input.readInt64();
                        continue;
                    case LogSource.GOOGLE_ANALYTICS /*18*/:
                        int i;
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 18);
                        if (this.counter == null) {
                            i = 0;
                        } else {
                            i = this.counter.length;
                        }
                        Counter[] newArray = new Counter[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.counter, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new Counter();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i++;
                        }
                        newArray[i] = new Counter();
                        input.readMessage(newArray[i]);
                        this.counter = newArray;
                        continue;
                    case LogSource.ANDROID_CAMERA /*26*/:
                        this.dimensionsInstance = input.readBytes();
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

        public static Counters parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (Counters) MessageNano.mergeFrom(new Counters(), data);
        }

        public static Counters parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new Counters().mergeFrom(input);
        }
    }
}
