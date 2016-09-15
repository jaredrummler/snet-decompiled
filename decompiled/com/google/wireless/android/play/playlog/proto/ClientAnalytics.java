package com.google.wireless.android.play.playlog.proto;

import com.google.android.gms.lint.BuildConfig;
import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.ExtendableMessageNano;
import com.google.protobuf.nano.InternalNano;
import com.google.protobuf.nano.InvalidProtocolBufferNanoException;
import com.google.protobuf.nano.MessageNano;
import com.google.protobuf.nano.WireFormatNano;
import com.google.wireless.android.play.playlog.proto.UserLocation.LaunchSettingsEvent.Action;
import com.google.wireless.android.play.playlog.proto.UserLocation.LocationSharingEvent.Type;
import com.google.wireless.android.play.playlog.proto.UserLocation.OvenFreshEvent.OvenFreshResult;
import com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection;
import java.io.IOException;
import java.util.Arrays;

public interface ClientAnalytics {

    public static final class ActiveExperiments extends ExtendableMessageNano<ActiveExperiments> {
        private static volatile ActiveExperiments[] _emptyArray;
        public String[] clientAlteringExperiment;
        public int[] gwsExperiment;
        public String[] otherExperiment;
        public long[] playExperiment;

        public static ActiveExperiments[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new ActiveExperiments[0];
                    }
                }
            }
            return _emptyArray;
        }

        public ActiveExperiments() {
            clear();
        }

        public ActiveExperiments clear() {
            this.clientAlteringExperiment = WireFormatNano.EMPTY_STRING_ARRAY;
            this.otherExperiment = WireFormatNano.EMPTY_STRING_ARRAY;
            this.gwsExperiment = WireFormatNano.EMPTY_INT_ARRAY;
            this.playExperiment = WireFormatNano.EMPTY_LONG_ARRAY;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof ActiveExperiments)) {
                return false;
            }
            ActiveExperiments other = (ActiveExperiments) o;
            if (!InternalNano.equals(this.clientAlteringExperiment, other.clientAlteringExperiment) || !InternalNano.equals(this.otherExperiment, other.otherExperiment) || !InternalNano.equals(this.gwsExperiment, other.gwsExperiment) || !InternalNano.equals(this.playExperiment, other.playExperiment)) {
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
            int hashCode = (((((((((getClass().getName().hashCode() + 527) * 31) + InternalNano.hashCode(this.clientAlteringExperiment)) * 31) + InternalNano.hashCode(this.otherExperiment)) * 31) + InternalNano.hashCode(this.gwsExperiment)) * 31) + InternalNano.hashCode(this.playExperiment)) * 31;
            int hashCode2 = (this.unknownFieldData == null || this.unknownFieldData.isEmpty()) ? 0 : this.unknownFieldData.hashCode();
            return hashCode + hashCode2;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.clientAlteringExperiment != null && this.clientAlteringExperiment.length > 0) {
                for (String element : this.clientAlteringExperiment) {
                    if (element != null) {
                        output.writeString(1, element);
                    }
                }
            }
            if (this.otherExperiment != null && this.otherExperiment.length > 0) {
                for (String element2 : this.otherExperiment) {
                    if (element2 != null) {
                        output.writeString(2, element2);
                    }
                }
            }
            if (this.gwsExperiment != null && this.gwsExperiment.length > 0) {
                for (int writeInt32 : this.gwsExperiment) {
                    output.writeInt32(3, writeInt32);
                }
            }
            if (this.playExperiment != null && this.playExperiment.length > 0) {
                for (long writeInt64 : this.playExperiment) {
                    output.writeInt64(4, writeInt64);
                }
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int dataCount;
            int dataSize;
            int size = super.computeSerializedSize();
            if (this.clientAlteringExperiment != null && this.clientAlteringExperiment.length > 0) {
                dataCount = 0;
                dataSize = 0;
                for (String element : this.clientAlteringExperiment) {
                    if (element != null) {
                        dataCount++;
                        dataSize += CodedOutputByteBufferNano.computeStringSizeNoTag(element);
                    }
                }
                size = (size + dataSize) + (dataCount * 1);
            }
            if (this.otherExperiment != null && this.otherExperiment.length > 0) {
                dataCount = 0;
                dataSize = 0;
                for (String element2 : this.otherExperiment) {
                    if (element2 != null) {
                        dataCount++;
                        dataSize += CodedOutputByteBufferNano.computeStringSizeNoTag(element2);
                    }
                }
                size = (size + dataSize) + (dataCount * 1);
            }
            if (this.gwsExperiment != null && this.gwsExperiment.length > 0) {
                dataSize = 0;
                for (int element3 : this.gwsExperiment) {
                    dataSize += CodedOutputByteBufferNano.computeInt32SizeNoTag(element3);
                }
                size = (size + dataSize) + (this.gwsExperiment.length * 1);
            }
            if (this.playExperiment == null || this.playExperiment.length <= 0) {
                return size;
            }
            dataSize = 0;
            for (long element4 : this.playExperiment) {
                dataSize += CodedOutputByteBufferNano.computeInt64SizeNoTag(element4);
            }
            return (size + dataSize) + (this.playExperiment.length * 1);
        }

        public ActiveExperiments mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                int arrayLength;
                int i;
                String[] newArray;
                int[] newArray2;
                int limit;
                int startPos;
                long[] newArray3;
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 10);
                        i = this.clientAlteringExperiment == null ? 0 : this.clientAlteringExperiment.length;
                        newArray = new String[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.clientAlteringExperiment, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = input.readString();
                            input.readTag();
                            i++;
                        }
                        newArray[i] = input.readString();
                        this.clientAlteringExperiment = newArray;
                        continue;
                    case LogSource.GOOGLE_ANALYTICS /*18*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 18);
                        i = this.otherExperiment == null ? 0 : this.otherExperiment.length;
                        newArray = new String[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.otherExperiment, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = input.readString();
                            input.readTag();
                            i++;
                        }
                        newArray[i] = input.readString();
                        this.otherExperiment = newArray;
                        continue;
                    case LogSource.LB_C /*24*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 24);
                        i = this.gwsExperiment == null ? 0 : this.gwsExperiment.length;
                        newArray2 = new int[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.gwsExperiment, 0, newArray2, 0, i);
                        }
                        while (i < newArray2.length - 1) {
                            newArray2[i] = input.readInt32();
                            input.readTag();
                            i++;
                        }
                        newArray2[i] = input.readInt32();
                        this.gwsExperiment = newArray2;
                        continue;
                    case LogSource.ANDROID_CAMERA /*26*/:
                        limit = input.pushLimit(input.readRawVarint32());
                        arrayLength = 0;
                        startPos = input.getPosition();
                        while (input.getBytesUntilLimit() > 0) {
                            input.readInt32();
                            arrayLength++;
                        }
                        input.rewindToPosition(startPos);
                        i = this.gwsExperiment == null ? 0 : this.gwsExperiment.length;
                        newArray2 = new int[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.gwsExperiment, 0, newArray2, 0, i);
                        }
                        while (i < newArray2.length) {
                            newArray2[i] = input.readInt32();
                            i++;
                        }
                        this.gwsExperiment = newArray2;
                        input.popLimit(limit);
                        continue;
                    case LogSource.SOCIAL /*32*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 32);
                        i = this.playExperiment == null ? 0 : this.playExperiment.length;
                        newArray3 = new long[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.playExperiment, 0, newArray3, 0, i);
                        }
                        while (i < newArray3.length - 1) {
                            newArray3[i] = input.readInt64();
                            input.readTag();
                            i++;
                        }
                        newArray3[i] = input.readInt64();
                        this.playExperiment = newArray3;
                        continue;
                    case LogSource.NOVA /*34*/:
                        limit = input.pushLimit(input.readRawVarint32());
                        arrayLength = 0;
                        startPos = input.getPosition();
                        while (input.getBytesUntilLimit() > 0) {
                            input.readInt64();
                            arrayLength++;
                        }
                        input.rewindToPosition(startPos);
                        i = this.playExperiment == null ? 0 : this.playExperiment.length;
                        newArray3 = new long[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.playExperiment, 0, newArray3, 0, i);
                        }
                        while (i < newArray3.length) {
                            newArray3[i] = input.readInt64();
                            i++;
                        }
                        this.playExperiment = newArray3;
                        input.popLimit(limit);
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

        public static ActiveExperiments parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (ActiveExperiments) MessageNano.mergeFrom(new ActiveExperiments(), data);
        }

        public static ActiveExperiments parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new ActiveExperiments().mergeFrom(input);
        }
    }

    public static final class AndroidClientInfo extends ExtendableMessageNano<AndroidClientInfo> {
        private static volatile AndroidClientInfo[] _emptyArray;
        public long androidId;
        public String applicationBuild;
        public String board;
        public String brand;
        public String country;
        public String device;
        public long deviceId;
        public String fingerprint;
        public int gmsCoreVersionCode;
        public String hardware;
        public boolean isSidewinderDevice;
        public String locale;
        public String loggingId;
        public String manufacturer;
        public String mccMnc;
        public String model;
        public String osBuild;
        public String product;
        public String radioVersion;
        public int sdkVersion;

        public static AndroidClientInfo[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new AndroidClientInfo[0];
                    }
                }
            }
            return _emptyArray;
        }

        public AndroidClientInfo() {
            clear();
        }

        public AndroidClientInfo clear() {
            this.androidId = 0;
            this.loggingId = BuildConfig.VERSION_NAME;
            this.deviceId = 0;
            this.sdkVersion = 0;
            this.model = BuildConfig.VERSION_NAME;
            this.product = BuildConfig.VERSION_NAME;
            this.hardware = BuildConfig.VERSION_NAME;
            this.device = BuildConfig.VERSION_NAME;
            this.osBuild = BuildConfig.VERSION_NAME;
            this.applicationBuild = BuildConfig.VERSION_NAME;
            this.mccMnc = BuildConfig.VERSION_NAME;
            this.locale = BuildConfig.VERSION_NAME;
            this.country = BuildConfig.VERSION_NAME;
            this.manufacturer = BuildConfig.VERSION_NAME;
            this.brand = BuildConfig.VERSION_NAME;
            this.board = BuildConfig.VERSION_NAME;
            this.radioVersion = BuildConfig.VERSION_NAME;
            this.fingerprint = BuildConfig.VERSION_NAME;
            this.gmsCoreVersionCode = 0;
            this.isSidewinderDevice = false;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof AndroidClientInfo)) {
                return false;
            }
            AndroidClientInfo other = (AndroidClientInfo) o;
            if (this.androidId != other.androidId) {
                return false;
            }
            if (this.loggingId == null) {
                if (other.loggingId != null) {
                    return false;
                }
            } else if (!this.loggingId.equals(other.loggingId)) {
                return false;
            }
            if (this.deviceId != other.deviceId || this.sdkVersion != other.sdkVersion) {
                return false;
            }
            if (this.model == null) {
                if (other.model != null) {
                    return false;
                }
            } else if (!this.model.equals(other.model)) {
                return false;
            }
            if (this.product == null) {
                if (other.product != null) {
                    return false;
                }
            } else if (!this.product.equals(other.product)) {
                return false;
            }
            if (this.hardware == null) {
                if (other.hardware != null) {
                    return false;
                }
            } else if (!this.hardware.equals(other.hardware)) {
                return false;
            }
            if (this.device == null) {
                if (other.device != null) {
                    return false;
                }
            } else if (!this.device.equals(other.device)) {
                return false;
            }
            if (this.osBuild == null) {
                if (other.osBuild != null) {
                    return false;
                }
            } else if (!this.osBuild.equals(other.osBuild)) {
                return false;
            }
            if (this.applicationBuild == null) {
                if (other.applicationBuild != null) {
                    return false;
                }
            } else if (!this.applicationBuild.equals(other.applicationBuild)) {
                return false;
            }
            if (this.mccMnc == null) {
                if (other.mccMnc != null) {
                    return false;
                }
            } else if (!this.mccMnc.equals(other.mccMnc)) {
                return false;
            }
            if (this.locale == null) {
                if (other.locale != null) {
                    return false;
                }
            } else if (!this.locale.equals(other.locale)) {
                return false;
            }
            if (this.country == null) {
                if (other.country != null) {
                    return false;
                }
            } else if (!this.country.equals(other.country)) {
                return false;
            }
            if (this.manufacturer == null) {
                if (other.manufacturer != null) {
                    return false;
                }
            } else if (!this.manufacturer.equals(other.manufacturer)) {
                return false;
            }
            if (this.brand == null) {
                if (other.brand != null) {
                    return false;
                }
            } else if (!this.brand.equals(other.brand)) {
                return false;
            }
            if (this.board == null) {
                if (other.board != null) {
                    return false;
                }
            } else if (!this.board.equals(other.board)) {
                return false;
            }
            if (this.radioVersion == null) {
                if (other.radioVersion != null) {
                    return false;
                }
            } else if (!this.radioVersion.equals(other.radioVersion)) {
                return false;
            }
            if (this.fingerprint == null) {
                if (other.fingerprint != null) {
                    return false;
                }
            } else if (!this.fingerprint.equals(other.fingerprint)) {
                return false;
            }
            if (this.gmsCoreVersionCode != other.gmsCoreVersionCode || this.isSidewinderDevice != other.isSidewinderDevice) {
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
            int hashCode = (((((((((((((((((((((((((((((((((((((((((getClass().getName().hashCode() + 527) * 31) + ((int) (this.androidId ^ (this.androidId >>> 32)))) * 31) + (this.loggingId == null ? 0 : this.loggingId.hashCode())) * 31) + ((int) (this.deviceId ^ (this.deviceId >>> 32)))) * 31) + this.sdkVersion) * 31) + (this.model == null ? 0 : this.model.hashCode())) * 31) + (this.product == null ? 0 : this.product.hashCode())) * 31) + (this.hardware == null ? 0 : this.hardware.hashCode())) * 31) + (this.device == null ? 0 : this.device.hashCode())) * 31) + (this.osBuild == null ? 0 : this.osBuild.hashCode())) * 31) + (this.applicationBuild == null ? 0 : this.applicationBuild.hashCode())) * 31) + (this.mccMnc == null ? 0 : this.mccMnc.hashCode())) * 31) + (this.locale == null ? 0 : this.locale.hashCode())) * 31) + (this.country == null ? 0 : this.country.hashCode())) * 31) + (this.manufacturer == null ? 0 : this.manufacturer.hashCode())) * 31) + (this.brand == null ? 0 : this.brand.hashCode())) * 31) + (this.board == null ? 0 : this.board.hashCode())) * 31) + (this.radioVersion == null ? 0 : this.radioVersion.hashCode())) * 31) + (this.fingerprint == null ? 0 : this.fingerprint.hashCode())) * 31) + this.gmsCoreVersionCode) * 31) + (this.isSidewinderDevice ? 1231 : 1237)) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.androidId != 0) {
                output.writeInt64(1, this.androidId);
            }
            if (!this.loggingId.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(2, this.loggingId);
            }
            if (this.sdkVersion != 0) {
                output.writeInt32(3, this.sdkVersion);
            }
            if (!this.model.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(4, this.model);
            }
            if (!this.product.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(5, this.product);
            }
            if (!this.osBuild.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(6, this.osBuild);
            }
            if (!this.applicationBuild.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(7, this.applicationBuild);
            }
            if (!this.hardware.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(8, this.hardware);
            }
            if (!this.device.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(9, this.device);
            }
            if (!this.mccMnc.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(10, this.mccMnc);
            }
            if (!this.locale.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(11, this.locale);
            }
            if (!this.country.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(12, this.country);
            }
            if (!this.manufacturer.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(13, this.manufacturer);
            }
            if (!this.brand.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(14, this.brand);
            }
            if (!this.board.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(15, this.board);
            }
            if (!this.radioVersion.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(16, this.radioVersion);
            }
            if (!this.fingerprint.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(17, this.fingerprint);
            }
            if (this.deviceId != 0) {
                output.writeInt64(18, this.deviceId);
            }
            if (this.gmsCoreVersionCode != 0) {
                output.writeInt32(19, this.gmsCoreVersionCode);
            }
            if (this.isSidewinderDevice) {
                output.writeBool(20, this.isSidewinderDevice);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.androidId != 0) {
                size += CodedOutputByteBufferNano.computeInt64Size(1, this.androidId);
            }
            if (!this.loggingId.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(2, this.loggingId);
            }
            if (this.sdkVersion != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(3, this.sdkVersion);
            }
            if (!this.model.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(4, this.model);
            }
            if (!this.product.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(5, this.product);
            }
            if (!this.osBuild.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(6, this.osBuild);
            }
            if (!this.applicationBuild.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(7, this.applicationBuild);
            }
            if (!this.hardware.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(8, this.hardware);
            }
            if (!this.device.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(9, this.device);
            }
            if (!this.mccMnc.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(10, this.mccMnc);
            }
            if (!this.locale.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(11, this.locale);
            }
            if (!this.country.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(12, this.country);
            }
            if (!this.manufacturer.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(13, this.manufacturer);
            }
            if (!this.brand.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(14, this.brand);
            }
            if (!this.board.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(15, this.board);
            }
            if (!this.radioVersion.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(16, this.radioVersion);
            }
            if (!this.fingerprint.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(17, this.fingerprint);
            }
            if (this.deviceId != 0) {
                size += CodedOutputByteBufferNano.computeInt64Size(18, this.deviceId);
            }
            if (this.gmsCoreVersionCode != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(19, this.gmsCoreVersionCode);
            }
            if (this.isSidewinderDevice) {
                return size + CodedOutputByteBufferNano.computeBoolSize(20, this.isSidewinderDevice);
            }
            return size;
        }

        public AndroidClientInfo mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_GET_LINK /*8*/:
                        this.androidId = input.readInt64();
                        continue;
                    case LogSource.GOOGLE_ANALYTICS /*18*/:
                        this.loggingId = input.readString();
                        continue;
                    case LogSource.LB_C /*24*/:
                        this.sdkVersion = input.readInt32();
                        continue;
                    case LogSource.NOVA /*34*/:
                        this.model = input.readString();
                        continue;
                    case LogSource.PHOTOS /*42*/:
                        this.product = input.readString();
                        continue;
                    case LogSource.HANGOUT_LOG_REQUEST /*50*/:
                        this.osBuild = input.readString();
                        continue;
                    case LogSource.SLIDES /*58*/:
                        this.applicationBuild = input.readString();
                        continue;
                    case LogSource.WIFI_ASSISTANT /*66*/:
                        this.hardware = input.readString();
                        continue;
                    case LogSource.MOVIES /*74*/:
                        this.device = input.readString();
                        continue;
                    case LogSource.GOOGLE_EXPRESS /*82*/:
                        this.mccMnc = input.readString();
                        continue;
                    case LogSource.TRON_COUNTERS /*90*/:
                        this.locale = input.readString();
                        continue;
                    case LogSource.TACHYON_LOG_REQUEST /*98*/:
                        this.country = input.readString();
                        continue;
                    case LogSource.ADSHIELD /*106*/:
                        this.manufacturer = input.readString();
                        continue;
                    case LogSource.TRANSOM /*114*/:
                        this.brand = input.readString();
                        continue;
                    case LogSource.ANDROID_SNET_GCORE /*122*/:
                        this.board = input.readString();
                        continue;
                    case LogSource.CHROMECAST_APP_LOG /*130*/:
                        this.radioVersion = input.readString();
                        continue;
                    case LogSource.PANCETTA_MOBILE_HOST /*138*/:
                        this.fingerprint = input.readString();
                        continue;
                    case 144:
                        this.deviceId = input.readInt64();
                        continue;
                    case 152:
                        this.gmsCoreVersionCode = input.readInt32();
                        continue;
                    case 160:
                        this.isSidewinderDevice = input.readBool();
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

        public static AndroidClientInfo parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (AndroidClientInfo) MessageNano.mergeFrom(new AndroidClientInfo(), data);
        }

        public static AndroidClientInfo parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new AndroidClientInfo().mergeFrom(input);
        }
    }

    public static final class AppUsage1pLogEvent extends ExtendableMessageNano<AppUsage1pLogEvent> {
        private static volatile AppUsage1pLogEvent[] _emptyArray;
        public String androidPackageName;
        public int appType;
        public String version;

        public interface AppType {
            public static final int ANDROID_IDE = 12;
            public static final int ANDROID_TIMELY = 18;
            public static final int CAR = 24;
            public static final int CHROME = 20;
            public static final int CW = 22;
            public static final int GEL = 21;
            public static final int GMAIL = 4;
            public static final int GMS_CORE = 11;
            public static final int GOOGLE_CALENDAR = 2;
            public static final int GOOGLE_CAMERA = 16;
            public static final int GOOGLE_MAPS = 15;
            public static final int GOOGLE_MESSAGING = 23;
            public static final int GOOGLE_PLAY_BOOKS = 7;
            public static final int GOOGLE_PLAY_GAMES = 10;
            public static final int GOOGLE_PLAY_MAGAZINES = 9;
            public static final int GOOGLE_PLAY_MUSIC = 6;
            public static final int GOOGLE_PLAY_STORE = 5;
            public static final int GOOGLE_PLAY_VIDEO = 8;
            public static final int GOOGLE_PLUS = 3;
            public static final int GOOGLE_SEARCH = 1;
            public static final int GOOGLE_TV = 13;
            public static final int LATIN_IME = 25;
            public static final int LE = 14;
            public static final int QUICK_SEARCH_BOX = 19;
            public static final int UNKNOWN = 0;
            public static final int VR = 26;
            public static final int YOUTUBE = 17;
        }

        public static AppUsage1pLogEvent[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new AppUsage1pLogEvent[0];
                    }
                }
            }
            return _emptyArray;
        }

        public AppUsage1pLogEvent() {
            clear();
        }

        public AppUsage1pLogEvent clear() {
            this.appType = 0;
            this.androidPackageName = BuildConfig.VERSION_NAME;
            this.version = BuildConfig.VERSION_NAME;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof AppUsage1pLogEvent)) {
                return false;
            }
            AppUsage1pLogEvent other = (AppUsage1pLogEvent) o;
            if (this.appType != other.appType) {
                return false;
            }
            if (this.androidPackageName == null) {
                if (other.androidPackageName != null) {
                    return false;
                }
            } else if (!this.androidPackageName.equals(other.androidPackageName)) {
                return false;
            }
            if (this.version == null) {
                if (other.version != null) {
                    return false;
                }
            } else if (!this.version.equals(other.version)) {
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
            int hashCode = (((((((getClass().getName().hashCode() + 527) * 31) + this.appType) * 31) + (this.androidPackageName == null ? 0 : this.androidPackageName.hashCode())) * 31) + (this.version == null ? 0 : this.version.hashCode())) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.appType != 0) {
                output.writeInt32(1, this.appType);
            }
            if (!this.androidPackageName.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(2, this.androidPackageName);
            }
            if (!this.version.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(3, this.version);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.appType != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(1, this.appType);
            }
            if (!this.androidPackageName.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(2, this.androidPackageName);
            }
            if (this.version.equals(BuildConfig.VERSION_NAME)) {
                return size;
            }
            return size + CodedOutputByteBufferNano.computeStringSize(3, this.version);
        }

        public AppUsage1pLogEvent mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_GET_LINK /*8*/:
                        int value = input.readInt32();
                        switch (value) {
                            case Action.UNKNOWN /*0*/:
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
                            case LogSource.LE /*17*/:
                            case LogSource.GOOGLE_ANALYTICS /*18*/:
                            case LogSource.LB_D /*19*/:
                            case LogSource.ANDROID_GSA /*20*/:
                            case LogSource.LB_T /*21*/:
                            case LogSource.PERSONAL_LOGGER /*22*/:
                            case LogSource.GMS_CORE_WALLET_MERCHANT_ERROR /*23*/:
                            case LogSource.LB_C /*24*/:
                            case LogSource.ANDROID_AUTH /*25*/:
                            case LogSource.ANDROID_CAMERA /*26*/:
                                this.appType = value;
                                break;
                            default:
                                continue;
                        }
                    case LogSource.GOOGLE_ANALYTICS /*18*/:
                        this.androidPackageName = input.readString();
                        continue;
                    case LogSource.ANDROID_CAMERA /*26*/:
                        this.version = input.readString();
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

        public static AppUsage1pLogEvent parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (AppUsage1pLogEvent) MessageNano.mergeFrom(new AppUsage1pLogEvent(), data);
        }

        public static AppUsage1pLogEvent parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new AppUsage1pLogEvent().mergeFrom(input);
        }
    }

    public static final class ClientInfo extends ExtendableMessageNano<ClientInfo> {
        private static volatile ClientInfo[] _emptyArray;
        public AndroidClientInfo androidClientInfo;
        public int clientType;

        public interface ClientType {
            public static final int ANDROID = 4;
            public static final int DESKTOP = 2;
            public static final int IOS = 3;
            public static final int JS = 1;
            public static final int PANCETTA = 8;
            public static final int PLAY_CE = 5;
            public static final int PYTHON = 6;
            public static final int UNKNOWN = 0;
            public static final int VR = 7;
        }

        public static ClientInfo[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new ClientInfo[0];
                    }
                }
            }
            return _emptyArray;
        }

        public ClientInfo() {
            clear();
        }

        public ClientInfo clear() {
            this.clientType = 0;
            this.androidClientInfo = null;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof ClientInfo)) {
                return false;
            }
            ClientInfo other = (ClientInfo) o;
            if (this.clientType != other.clientType) {
                return false;
            }
            if (this.androidClientInfo == null) {
                if (other.androidClientInfo != null) {
                    return false;
                }
            } else if (!this.androidClientInfo.equals(other.androidClientInfo)) {
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
            int hashCode = (((((getClass().getName().hashCode() + 527) * 31) + this.clientType) * 31) + (this.androidClientInfo == null ? 0 : this.androidClientInfo.hashCode())) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.clientType != 0) {
                output.writeInt32(1, this.clientType);
            }
            if (this.androidClientInfo != null) {
                output.writeMessage(2, this.androidClientInfo);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.clientType != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(1, this.clientType);
            }
            if (this.androidClientInfo != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(2, this.androidClientInfo);
            }
            return size;
        }

        public ClientInfo mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_GET_LINK /*8*/:
                        int value = input.readInt32();
                        switch (value) {
                            case Action.UNKNOWN /*0*/:
                            case TimeSelection.Type.TEMPORARY /*1*/:
                            case TimeSelection.Type.INDEFINITELY /*2*/:
                            case TimeSelection.Type.CUSTOM /*3*/:
                            case OvenFreshResult.NO_ACCOUNTS /*4*/:
                            case Type.ADD_NEW_SHARES /*5*/:
                            case Type.REMOVE_SHARE /*6*/:
                            case Type.RESET_TIME /*7*/:
                            case Type.TAP_GET_LINK /*8*/:
                                this.clientType = value;
                                break;
                            default:
                                continue;
                        }
                    case LogSource.GOOGLE_ANALYTICS /*18*/:
                        if (this.androidClientInfo == null) {
                            this.androidClientInfo = new AndroidClientInfo();
                        }
                        input.readMessage(this.androidClientInfo);
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

        public static ClientInfo parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (ClientInfo) MessageNano.mergeFrom(new ClientInfo(), data);
        }

        public static ClientInfo parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new ClientInfo().mergeFrom(input);
        }
    }

    public static final class DeviceStatus extends ExtendableMessageNano<DeviceStatus> {
        private static volatile DeviceStatus[] _emptyArray;
        public int autoTimeStatus;
        public boolean isCharging;
        public boolean isGooglerDevice;
        public boolean isUnmetered;

        public interface AutomaticTime {
            public static final int AUTO_TIME_OFF = 1;
            public static final int AUTO_TIME_ON = 2;
            public static final int UNKNOWN = 0;
        }

        public static DeviceStatus[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new DeviceStatus[0];
                    }
                }
            }
            return _emptyArray;
        }

        public DeviceStatus() {
            clear();
        }

        public DeviceStatus clear() {
            this.isUnmetered = false;
            this.isCharging = false;
            this.autoTimeStatus = 0;
            this.isGooglerDevice = false;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof DeviceStatus)) {
                return false;
            }
            DeviceStatus other = (DeviceStatus) o;
            if (this.isUnmetered != other.isUnmetered || this.isCharging != other.isCharging || this.autoTimeStatus != other.autoTimeStatus || this.isGooglerDevice != other.isGooglerDevice) {
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
            int i;
            int i2 = 1231;
            int hashCode = (((getClass().getName().hashCode() + 527) * 31) + (this.isUnmetered ? 1231 : 1237)) * 31;
            if (this.isCharging) {
                i = 1231;
            } else {
                i = 1237;
            }
            i = (((hashCode + i) * 31) + this.autoTimeStatus) * 31;
            if (!this.isGooglerDevice) {
                i2 = 1237;
            }
            i2 = (i + i2) * 31;
            i = (this.unknownFieldData == null || this.unknownFieldData.isEmpty()) ? 0 : this.unknownFieldData.hashCode();
            return i2 + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.isUnmetered) {
                output.writeBool(1, this.isUnmetered);
            }
            if (this.isCharging) {
                output.writeBool(2, this.isCharging);
            }
            if (this.autoTimeStatus != 0) {
                output.writeInt32(3, this.autoTimeStatus);
            }
            if (this.isGooglerDevice) {
                output.writeBool(4, this.isGooglerDevice);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.isUnmetered) {
                size += CodedOutputByteBufferNano.computeBoolSize(1, this.isUnmetered);
            }
            if (this.isCharging) {
                size += CodedOutputByteBufferNano.computeBoolSize(2, this.isCharging);
            }
            if (this.autoTimeStatus != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(3, this.autoTimeStatus);
            }
            if (this.isGooglerDevice) {
                return size + CodedOutputByteBufferNano.computeBoolSize(4, this.isGooglerDevice);
            }
            return size;
        }

        public DeviceStatus mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_GET_LINK /*8*/:
                        this.isUnmetered = input.readBool();
                        continue;
                    case LogSource.GMS_CORE_PEOPLE /*16*/:
                        this.isCharging = input.readBool();
                        continue;
                    case LogSource.LB_C /*24*/:
                        int value = input.readInt32();
                        switch (value) {
                            case Action.UNKNOWN /*0*/:
                            case TimeSelection.Type.TEMPORARY /*1*/:
                            case TimeSelection.Type.INDEFINITELY /*2*/:
                                this.autoTimeStatus = value;
                                break;
                            default:
                                continue;
                        }
                    case LogSource.SOCIAL /*32*/:
                        this.isGooglerDevice = input.readBool();
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

        public static DeviceStatus parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (DeviceStatus) MessageNano.mergeFrom(new DeviceStatus(), data);
        }

        public static DeviceStatus parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new DeviceStatus().mergeFrom(input);
        }
    }

    public static final class ExperimentIdList extends ExtendableMessageNano<ExperimentIdList> {
        private static volatile ExperimentIdList[] _emptyArray;
        public String[] id;

        public static ExperimentIdList[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new ExperimentIdList[0];
                    }
                }
            }
            return _emptyArray;
        }

        public ExperimentIdList() {
            clear();
        }

        public ExperimentIdList clear() {
            this.id = WireFormatNano.EMPTY_STRING_ARRAY;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof ExperimentIdList)) {
                return false;
            }
            ExperimentIdList other = (ExperimentIdList) o;
            if (!InternalNano.equals(this.id, other.id)) {
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
            int hashCode = (((getClass().getName().hashCode() + 527) * 31) + InternalNano.hashCode(this.id)) * 31;
            int hashCode2 = (this.unknownFieldData == null || this.unknownFieldData.isEmpty()) ? 0 : this.unknownFieldData.hashCode();
            return hashCode + hashCode2;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.id != null && this.id.length > 0) {
                for (String element : this.id) {
                    if (element != null) {
                        output.writeString(1, element);
                    }
                }
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.id == null || this.id.length <= 0) {
                return size;
            }
            int dataCount = 0;
            int dataSize = 0;
            for (String element : this.id) {
                if (element != null) {
                    dataCount++;
                    dataSize += CodedOutputByteBufferNano.computeStringSizeNoTag(element);
                }
            }
            return (size + dataSize) + (dataCount * 1);
        }

        public ExperimentIdList mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 10);
                        int i = this.id == null ? 0 : this.id.length;
                        String[] newArray = new String[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.id, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = input.readString();
                            input.readTag();
                            i++;
                        }
                        newArray[i] = input.readString();
                        this.id = newArray;
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

        public static ExperimentIdList parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (ExperimentIdList) MessageNano.mergeFrom(new ExperimentIdList(), data);
        }

        public static ExperimentIdList parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new ExperimentIdList().mergeFrom(input);
        }
    }

    public static final class ExperimentIds extends ExtendableMessageNano<ExperimentIds> {
        private static volatile ExperimentIds[] _emptyArray;
        public byte[] clearBlob;
        public byte[][] encryptedBlob;
        public boolean usersMatch;

        public static ExperimentIds[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new ExperimentIds[0];
                    }
                }
            }
            return _emptyArray;
        }

        public ExperimentIds() {
            clear();
        }

        public ExperimentIds clear() {
            this.clearBlob = WireFormatNano.EMPTY_BYTES;
            this.encryptedBlob = WireFormatNano.EMPTY_BYTES_ARRAY;
            this.usersMatch = false;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof ExperimentIds)) {
                return false;
            }
            ExperimentIds other = (ExperimentIds) o;
            if (!Arrays.equals(this.clearBlob, other.clearBlob) || !InternalNano.equals(this.encryptedBlob, other.encryptedBlob) || this.usersMatch != other.usersMatch) {
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
            int hashCode = (((((((getClass().getName().hashCode() + 527) * 31) + Arrays.hashCode(this.clearBlob)) * 31) + InternalNano.hashCode(this.encryptedBlob)) * 31) + (this.usersMatch ? 1231 : 1237)) * 31;
            int hashCode2 = (this.unknownFieldData == null || this.unknownFieldData.isEmpty()) ? 0 : this.unknownFieldData.hashCode();
            return hashCode + hashCode2;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (!Arrays.equals(this.clearBlob, WireFormatNano.EMPTY_BYTES)) {
                output.writeBytes(1, this.clearBlob);
            }
            if (this.encryptedBlob != null && this.encryptedBlob.length > 0) {
                for (byte[] element : this.encryptedBlob) {
                    if (element != null) {
                        output.writeBytes(2, element);
                    }
                }
            }
            if (this.usersMatch) {
                output.writeBool(3, this.usersMatch);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (!Arrays.equals(this.clearBlob, WireFormatNano.EMPTY_BYTES)) {
                size += CodedOutputByteBufferNano.computeBytesSize(1, this.clearBlob);
            }
            if (this.encryptedBlob != null && this.encryptedBlob.length > 0) {
                int dataCount = 0;
                int dataSize = 0;
                for (byte[] element : this.encryptedBlob) {
                    if (element != null) {
                        dataCount++;
                        dataSize += CodedOutputByteBufferNano.computeBytesSizeNoTag(element);
                    }
                }
                size = (size + dataSize) + (dataCount * 1);
            }
            if (this.usersMatch) {
                return size + CodedOutputByteBufferNano.computeBoolSize(3, this.usersMatch);
            }
            return size;
        }

        public ExperimentIds mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        this.clearBlob = input.readBytes();
                        continue;
                    case LogSource.GOOGLE_ANALYTICS /*18*/:
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 18);
                        int i = this.encryptedBlob == null ? 0 : this.encryptedBlob.length;
                        byte[][] newArray = new byte[(i + arrayLength)][];
                        if (i != 0) {
                            System.arraycopy(this.encryptedBlob, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = input.readBytes();
                            input.readTag();
                            i++;
                        }
                        newArray[i] = input.readBytes();
                        this.encryptedBlob = newArray;
                        continue;
                    case LogSource.LB_C /*24*/:
                        this.usersMatch = input.readBool();
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

        public static ExperimentIds parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (ExperimentIds) MessageNano.mergeFrom(new ExperimentIds(), data);
        }

        public static ExperimentIds parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new ExperimentIds().mergeFrom(input);
        }
    }

    public static final class ExternalTimestamp extends ExtendableMessageNano<ExternalTimestamp> {
        private static volatile ExternalTimestamp[] _emptyArray;
        public String source;
        public long timeMs;
        public long uptimeMs;

        public static ExternalTimestamp[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new ExternalTimestamp[0];
                    }
                }
            }
            return _emptyArray;
        }

        public ExternalTimestamp() {
            clear();
        }

        public ExternalTimestamp clear() {
            this.timeMs = 0;
            this.uptimeMs = 0;
            this.source = BuildConfig.VERSION_NAME;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof ExternalTimestamp)) {
                return false;
            }
            ExternalTimestamp other = (ExternalTimestamp) o;
            if (this.timeMs != other.timeMs || this.uptimeMs != other.uptimeMs) {
                return false;
            }
            if (this.source == null) {
                if (other.source != null) {
                    return false;
                }
            } else if (!this.source.equals(other.source)) {
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
            int hashCode = (((((((getClass().getName().hashCode() + 527) * 31) + ((int) (this.timeMs ^ (this.timeMs >>> 32)))) * 31) + ((int) (this.uptimeMs ^ (this.uptimeMs >>> 32)))) * 31) + (this.source == null ? 0 : this.source.hashCode())) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.timeMs != 0) {
                output.writeInt64(1, this.timeMs);
            }
            if (this.uptimeMs != 0) {
                output.writeInt64(2, this.uptimeMs);
            }
            if (!this.source.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(3, this.source);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.timeMs != 0) {
                size += CodedOutputByteBufferNano.computeInt64Size(1, this.timeMs);
            }
            if (this.uptimeMs != 0) {
                size += CodedOutputByteBufferNano.computeInt64Size(2, this.uptimeMs);
            }
            if (this.source.equals(BuildConfig.VERSION_NAME)) {
                return size;
            }
            return size + CodedOutputByteBufferNano.computeStringSize(3, this.source);
        }

        public ExternalTimestamp mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_GET_LINK /*8*/:
                        this.timeMs = input.readInt64();
                        continue;
                    case LogSource.GMS_CORE_PEOPLE /*16*/:
                        this.uptimeMs = input.readInt64();
                        continue;
                    case LogSource.ANDROID_CAMERA /*26*/:
                        this.source = input.readString();
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

        public static ExternalTimestamp parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (ExternalTimestamp) MessageNano.mergeFrom(new ExternalTimestamp(), data);
        }

        public static ExternalTimestamp parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new ExternalTimestamp().mergeFrom(input);
        }
    }

    public static final class LogEvent extends ExtendableMessageNano<LogEvent> {
        private static volatile LogEvent[] _emptyArray;
        public AppUsage1pLogEvent appUsage1P;
        public long bootCount;
        public byte[] clientVe;
        public int eventCode;
        public int eventFlowId;
        public long eventTimeMs;
        public long eventUptimeMs;
        public ActiveExperiments exp;
        public ExperimentIds experimentIds;
        public int internalEvent;
        public boolean isUserInitiated;
        public long sequencePosition;
        public byte[] sourceExtension;
        public byte[] sourceExtensionJs;
        public byte[] sourceExtensionJson;
        public String tag;
        public int[] testCode;
        public String testId;
        public long timezoneOffsetSeconds;
        public LogEventKeyValues[] value;

        public interface InternalEvent {
            public static final int DEVICE_BOOT = 2;
            public static final int NONE = 0;
            public static final int WALL_CLOCK_SET = 1;
        }

        public static LogEvent[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new LogEvent[0];
                    }
                }
            }
            return _emptyArray;
        }

        public LogEvent() {
            clear();
        }

        public LogEvent clear() {
            this.eventTimeMs = 0;
            this.eventUptimeMs = 0;
            this.sequencePosition = 0;
            this.tag = BuildConfig.VERSION_NAME;
            this.eventCode = 0;
            this.eventFlowId = 0;
            this.isUserInitiated = false;
            this.value = LogEventKeyValues.emptyArray();
            this.appUsage1P = null;
            this.sourceExtension = WireFormatNano.EMPTY_BYTES;
            this.sourceExtensionJs = WireFormatNano.EMPTY_BYTES;
            this.sourceExtensionJson = WireFormatNano.EMPTY_BYTES;
            this.exp = null;
            this.testId = BuildConfig.VERSION_NAME;
            this.timezoneOffsetSeconds = 180000;
            this.experimentIds = null;
            this.clientVe = WireFormatNano.EMPTY_BYTES;
            this.internalEvent = 0;
            this.testCode = WireFormatNano.EMPTY_INT_ARRAY;
            this.bootCount = 0;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof LogEvent)) {
                return false;
            }
            LogEvent other = (LogEvent) o;
            if (this.eventTimeMs != other.eventTimeMs || this.eventUptimeMs != other.eventUptimeMs || this.sequencePosition != other.sequencePosition) {
                return false;
            }
            if (this.tag == null) {
                if (other.tag != null) {
                    return false;
                }
            } else if (!this.tag.equals(other.tag)) {
                return false;
            }
            if (this.eventCode != other.eventCode || this.eventFlowId != other.eventFlowId || this.isUserInitiated != other.isUserInitiated || !InternalNano.equals(this.value, other.value)) {
                return false;
            }
            if (this.appUsage1P == null) {
                if (other.appUsage1P != null) {
                    return false;
                }
            } else if (!this.appUsage1P.equals(other.appUsage1P)) {
                return false;
            }
            if (!Arrays.equals(this.sourceExtension, other.sourceExtension) || !Arrays.equals(this.sourceExtensionJs, other.sourceExtensionJs) || !Arrays.equals(this.sourceExtensionJson, other.sourceExtensionJson)) {
                return false;
            }
            if (this.exp == null) {
                if (other.exp != null) {
                    return false;
                }
            } else if (!this.exp.equals(other.exp)) {
                return false;
            }
            if (this.testId == null) {
                if (other.testId != null) {
                    return false;
                }
            } else if (!this.testId.equals(other.testId)) {
                return false;
            }
            if (this.timezoneOffsetSeconds != other.timezoneOffsetSeconds) {
                return false;
            }
            if (this.experimentIds == null) {
                if (other.experimentIds != null) {
                    return false;
                }
            } else if (!this.experimentIds.equals(other.experimentIds)) {
                return false;
            }
            if (!Arrays.equals(this.clientVe, other.clientVe) || this.internalEvent != other.internalEvent || !InternalNano.equals(this.testCode, other.testCode) || this.bootCount != other.bootCount) {
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
            int hashCode = (((((((((((((((((((((((((((((((((((((((((getClass().getName().hashCode() + 527) * 31) + ((int) (this.eventTimeMs ^ (this.eventTimeMs >>> 32)))) * 31) + ((int) (this.eventUptimeMs ^ (this.eventUptimeMs >>> 32)))) * 31) + ((int) (this.sequencePosition ^ (this.sequencePosition >>> 32)))) * 31) + (this.tag == null ? 0 : this.tag.hashCode())) * 31) + this.eventCode) * 31) + this.eventFlowId) * 31) + (this.isUserInitiated ? 1231 : 1237)) * 31) + InternalNano.hashCode(this.value)) * 31) + (this.appUsage1P == null ? 0 : this.appUsage1P.hashCode())) * 31) + Arrays.hashCode(this.sourceExtension)) * 31) + Arrays.hashCode(this.sourceExtensionJs)) * 31) + Arrays.hashCode(this.sourceExtensionJson)) * 31) + (this.exp == null ? 0 : this.exp.hashCode())) * 31) + (this.testId == null ? 0 : this.testId.hashCode())) * 31) + ((int) (this.timezoneOffsetSeconds ^ (this.timezoneOffsetSeconds >>> 32)))) * 31) + (this.experimentIds == null ? 0 : this.experimentIds.hashCode())) * 31) + Arrays.hashCode(this.clientVe)) * 31) + this.internalEvent) * 31) + InternalNano.hashCode(this.testCode)) * 31) + ((int) (this.bootCount ^ (this.bootCount >>> 32)))) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.eventTimeMs != 0) {
                output.writeInt64(1, this.eventTimeMs);
            }
            if (!this.tag.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(2, this.tag);
            }
            if (this.value != null && this.value.length > 0) {
                for (LogEventKeyValues element : this.value) {
                    if (element != null) {
                        output.writeMessage(3, element);
                    }
                }
            }
            if (!Arrays.equals(this.sourceExtension, WireFormatNano.EMPTY_BYTES)) {
                output.writeBytes(6, this.sourceExtension);
            }
            if (this.exp != null) {
                output.writeMessage(7, this.exp);
            }
            if (!Arrays.equals(this.sourceExtensionJs, WireFormatNano.EMPTY_BYTES)) {
                output.writeBytes(8, this.sourceExtensionJs);
            }
            if (this.appUsage1P != null) {
                output.writeMessage(9, this.appUsage1P);
            }
            if (this.isUserInitiated) {
                output.writeBool(10, this.isUserInitiated);
            }
            if (this.eventCode != 0) {
                output.writeInt32(11, this.eventCode);
            }
            if (this.eventFlowId != 0) {
                output.writeInt32(12, this.eventFlowId);
            }
            if (!Arrays.equals(this.sourceExtensionJson, WireFormatNano.EMPTY_BYTES)) {
                output.writeBytes(13, this.sourceExtensionJson);
            }
            if (!this.testId.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(14, this.testId);
            }
            if (this.timezoneOffsetSeconds != 180000) {
                output.writeSInt64(15, this.timezoneOffsetSeconds);
            }
            if (this.experimentIds != null) {
                output.writeMessage(16, this.experimentIds);
            }
            if (this.eventUptimeMs != 0) {
                output.writeInt64(17, this.eventUptimeMs);
            }
            if (!Arrays.equals(this.clientVe, WireFormatNano.EMPTY_BYTES)) {
                output.writeBytes(18, this.clientVe);
            }
            if (this.internalEvent != 0) {
                output.writeInt32(19, this.internalEvent);
            }
            if (this.testCode != null && this.testCode.length > 0) {
                for (int writeInt32 : this.testCode) {
                    output.writeInt32(20, writeInt32);
                }
            }
            if (this.sequencePosition != 0) {
                output.writeInt64(21, this.sequencePosition);
            }
            if (this.bootCount != 0) {
                output.writeInt64(22, this.bootCount);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.eventTimeMs != 0) {
                size += CodedOutputByteBufferNano.computeInt64Size(1, this.eventTimeMs);
            }
            if (!this.tag.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(2, this.tag);
            }
            if (this.value != null && this.value.length > 0) {
                for (LogEventKeyValues element : this.value) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(3, element);
                    }
                }
            }
            if (!Arrays.equals(this.sourceExtension, WireFormatNano.EMPTY_BYTES)) {
                size += CodedOutputByteBufferNano.computeBytesSize(6, this.sourceExtension);
            }
            if (this.exp != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(7, this.exp);
            }
            if (!Arrays.equals(this.sourceExtensionJs, WireFormatNano.EMPTY_BYTES)) {
                size += CodedOutputByteBufferNano.computeBytesSize(8, this.sourceExtensionJs);
            }
            if (this.appUsage1P != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(9, this.appUsage1P);
            }
            if (this.isUserInitiated) {
                size += CodedOutputByteBufferNano.computeBoolSize(10, this.isUserInitiated);
            }
            if (this.eventCode != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(11, this.eventCode);
            }
            if (this.eventFlowId != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(12, this.eventFlowId);
            }
            if (!Arrays.equals(this.sourceExtensionJson, WireFormatNano.EMPTY_BYTES)) {
                size += CodedOutputByteBufferNano.computeBytesSize(13, this.sourceExtensionJson);
            }
            if (!this.testId.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(14, this.testId);
            }
            if (this.timezoneOffsetSeconds != 180000) {
                size += CodedOutputByteBufferNano.computeSInt64Size(15, this.timezoneOffsetSeconds);
            }
            if (this.experimentIds != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(16, this.experimentIds);
            }
            if (this.eventUptimeMs != 0) {
                size += CodedOutputByteBufferNano.computeInt64Size(17, this.eventUptimeMs);
            }
            if (!Arrays.equals(this.clientVe, WireFormatNano.EMPTY_BYTES)) {
                size += CodedOutputByteBufferNano.computeBytesSize(18, this.clientVe);
            }
            if (this.internalEvent != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(19, this.internalEvent);
            }
            if (this.testCode != null && this.testCode.length > 0) {
                int dataSize = 0;
                for (int element2 : this.testCode) {
                    dataSize += CodedOutputByteBufferNano.computeInt32SizeNoTag(element2);
                }
                size = (size + dataSize) + (this.testCode.length * 2);
            }
            if (this.sequencePosition != 0) {
                size += CodedOutputByteBufferNano.computeInt64Size(21, this.sequencePosition);
            }
            if (this.bootCount != 0) {
                return size + CodedOutputByteBufferNano.computeInt64Size(22, this.bootCount);
            }
            return size;
        }

        public LogEvent mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                int arrayLength;
                int i;
                int[] newArray;
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_GET_LINK /*8*/:
                        this.eventTimeMs = input.readInt64();
                        continue;
                    case LogSource.GOOGLE_ANALYTICS /*18*/:
                        this.tag = input.readString();
                        continue;
                    case LogSource.ANDROID_CAMERA /*26*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 26);
                        if (this.value == null) {
                            i = 0;
                        } else {
                            i = this.value.length;
                        }
                        LogEventKeyValues[] newArray2 = new LogEventKeyValues[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.value, 0, newArray2, 0, i);
                        }
                        while (i < newArray2.length - 1) {
                            newArray2[i] = new LogEventKeyValues();
                            input.readMessage(newArray2[i]);
                            input.readTag();
                            i++;
                        }
                        newArray2[i] = new LogEventKeyValues();
                        input.readMessage(newArray2[i]);
                        this.value = newArray2;
                        continue;
                    case LogSource.HANGOUT_LOG_REQUEST /*50*/:
                        this.sourceExtension = input.readBytes();
                        continue;
                    case LogSource.SLIDES /*58*/:
                        if (this.exp == null) {
                            this.exp = new ActiveExperiments();
                        }
                        input.readMessage(this.exp);
                        continue;
                    case LogSource.WIFI_ASSISTANT /*66*/:
                        this.sourceExtensionJs = input.readBytes();
                        continue;
                    case LogSource.MOVIES /*74*/:
                        if (this.appUsage1P == null) {
                            this.appUsage1P = new AppUsage1pLogEvent();
                        }
                        input.readMessage(this.appUsage1P);
                        continue;
                    case LogSource.CRONET_ANDROID_GSA /*80*/:
                        this.isUserInitiated = input.readBool();
                        continue;
                    case LogSource.EMERGENCY_ASSIST /*88*/:
                        this.eventCode = input.readInt32();
                        continue;
                    case LogSource.GCM_COUNTERS /*96*/:
                        this.eventFlowId = input.readInt32();
                        continue;
                    case LogSource.ADSHIELD /*106*/:
                        this.sourceExtensionJson = input.readBytes();
                        continue;
                    case LogSource.TRANSOM /*114*/:
                        this.testId = input.readString();
                        continue;
                    case LogSource.FLYDROID /*120*/:
                        this.timezoneOffsetSeconds = input.readSInt64();
                        continue;
                    case LogSource.CHROMECAST_APP_LOG /*130*/:
                        if (this.experimentIds == null) {
                            this.experimentIds = new ExperimentIds();
                        }
                        input.readMessage(this.experimentIds);
                        continue;
                    case LogSource.SAMPLE_SHM /*136*/:
                        this.eventUptimeMs = input.readInt64();
                        continue;
                    case 146:
                        this.clientVe = input.readBytes();
                        continue;
                    case 152:
                        int value = input.readInt32();
                        switch (value) {
                            case Action.UNKNOWN /*0*/:
                            case TimeSelection.Type.TEMPORARY /*1*/:
                            case TimeSelection.Type.INDEFINITELY /*2*/:
                                this.internalEvent = value;
                                break;
                            default:
                                continue;
                        }
                    case 160:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 160);
                        i = this.testCode == null ? 0 : this.testCode.length;
                        newArray = new int[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.testCode, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = input.readInt32();
                            input.readTag();
                            i++;
                        }
                        newArray[i] = input.readInt32();
                        this.testCode = newArray;
                        continue;
                    case 162:
                        int limit = input.pushLimit(input.readRawVarint32());
                        arrayLength = 0;
                        int startPos = input.getPosition();
                        while (input.getBytesUntilLimit() > 0) {
                            input.readInt32();
                            arrayLength++;
                        }
                        input.rewindToPosition(startPos);
                        i = this.testCode == null ? 0 : this.testCode.length;
                        newArray = new int[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.testCode, 0, newArray, 0, i);
                        }
                        while (i < newArray.length) {
                            newArray[i] = input.readInt32();
                            i++;
                        }
                        this.testCode = newArray;
                        input.popLimit(limit);
                        continue;
                    case 168:
                        this.sequencePosition = input.readInt64();
                        continue;
                    case 176:
                        this.bootCount = input.readInt64();
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

        public static LogEvent parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (LogEvent) MessageNano.mergeFrom(new LogEvent(), data);
        }

        public static LogEvent parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new LogEvent().mergeFrom(input);
        }
    }

    public static final class LogEventKeyValues extends ExtendableMessageNano<LogEventKeyValues> {
        private static volatile LogEventKeyValues[] _emptyArray;
        public String key;
        public String value;

        public static LogEventKeyValues[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new LogEventKeyValues[0];
                    }
                }
            }
            return _emptyArray;
        }

        public LogEventKeyValues() {
            clear();
        }

        public LogEventKeyValues clear() {
            this.key = BuildConfig.VERSION_NAME;
            this.value = BuildConfig.VERSION_NAME;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof LogEventKeyValues)) {
                return false;
            }
            LogEventKeyValues other = (LogEventKeyValues) o;
            if (this.key == null) {
                if (other.key != null) {
                    return false;
                }
            } else if (!this.key.equals(other.key)) {
                return false;
            }
            if (this.value == null) {
                if (other.value != null) {
                    return false;
                }
            } else if (!this.value.equals(other.value)) {
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
            int hashCode = (((((getClass().getName().hashCode() + 527) * 31) + (this.key == null ? 0 : this.key.hashCode())) * 31) + (this.value == null ? 0 : this.value.hashCode())) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (!this.key.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(1, this.key);
            }
            if (!this.value.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(2, this.value);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (!this.key.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.key);
            }
            if (this.value.equals(BuildConfig.VERSION_NAME)) {
                return size;
            }
            return size + CodedOutputByteBufferNano.computeStringSize(2, this.value);
        }

        public LogEventKeyValues mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        this.key = input.readString();
                        continue;
                    case LogSource.GOOGLE_ANALYTICS /*18*/:
                        this.value = input.readString();
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

        public static LogEventKeyValues parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (LogEventKeyValues) MessageNano.mergeFrom(new LogEventKeyValues(), data);
        }

        public static LogEventKeyValues parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new LogEventKeyValues().mergeFrom(input);
        }
    }

    public static final class LogRequest extends ExtendableMessageNano<LogRequest> {
        private static volatile LogRequest[] _emptyArray;
        public ClientInfo clientInfo;
        public DeviceStatus deviceStatus;
        public ExternalTimestamp externalTimestamp;
        public LogEvent[] logEvent;
        public int logSource;
        public String logSourceName;
        public int qosTier;
        public long requestTimeMs;
        public long requestUptimeMs;
        public int scheduler;
        public byte[][] serializedLogEvents;
        public String zwiebackCookie;

        public interface LogSource {
            public static final int A11YLOGGER = 95;
            public static final int ADSHIELD = 106;
            public static final int ADWORDS_MOBILE = 133;
            public static final int ANDROID_AUTH = 25;
            public static final int ANDROID_BACKUP = 84;
            public static final int ANDROID_CAMERA = 26;
            public static final int ANDROID_GMAIL = 135;
            public static final int ANDROID_GSA = 20;
            public static final int ANDROID_IDE = 7;
            public static final int ANDROID_MESSAGING = 46;
            public static final int ANDROID_SNET_GCORE = 122;
            public static final int ANDROID_SNET_IDLE = 123;
            public static final int ANDROID_SNET_JAR = 124;
            public static final int ANDROID_VERIFY_APPS = 105;
            public static final int APP_USAGE_1P = 11;
            public static final int BACKDROP = 48;
            public static final int BATTERY_STATS = 91;
            public static final int BOND_ONEGOOGLE = 78;
            public static final int BOOKS = 2;
            public static final int CALENDAR = 111;
            public static final int CAR = 53;
            public static final int CAST_SENDER_SDK = 67;
            public static final int CHROMECAST_APP_LOG = 130;
            public static final int CHROME_INFRA = 71;
            public static final int CLASSROOM = 126;
            public static final int CONTEXT_MANAGER = 125;
            public static final int COPRESENCE = 40;
            public static final int CPANEL_APP = 121;
            public static final int CRONET_ANDROID_GSA = 80;
            public static final int CRONET_SOCIAL = 68;
            public static final int CW = 27;
            public static final int DISK_STATS = 92;
            public static final int DNA_PROBER = 29;
            public static final int DOCS = 56;
            public static final int DRIVE = 55;
            public static final int DROP_BOX = 131;
            public static final int EDU_STORE = 15;
            public static final int EMERGENCY_ASSIST = 88;
            public static final int ENDER = 112;
            public static final int FAMILY_COMPASS = 113;
            public static final int FINDR = 45;
            public static final int FLYDROID = 120;
            public static final int GAMES = 5;
            public static final int GCM = 43;
            public static final int GCM_COUNTERS = 96;
            public static final int GEL = 28;
            public static final int GMM_BRIIM_COUNTERS = 129;
            public static final int GMM_COUNTERS = 77;
            public static final int GMM_UE3 = 110;
            public static final int GMS_CORE = 10;
            public static final int GMS_CORE_PEOPLE = 16;
            public static final int GMS_CORE_WALLET = 31;
            public static final int GMS_CORE_WALLET_MERCHANT_ERROR = 23;
            public static final int GOKART = 44;
            public static final int GOOGLE_ANALYTICS = 18;
            public static final int GOOGLE_EXPRESS = 82;
            public static final int GOOGLE_FIT_WEARABLE = 81;
            public static final int GOOGLE_TV = 14;
            public static final int GPLUS_ANDROID_SYSTEM_HEALTH = 140;
            public static final int GRAPHICS_STATS = 107;
            public static final int GVC_HARVESTER = 51;
            public static final int HANGOUT = 39;
            public static final int HANGOUT_LOG_REQUEST = 50;
            public static final int HERREVAD = 13;
            public static final int ICING = 12;
            public static final int ICORE = 137;
            public static final int IME = 59;
            public static final int IME_COUNTERS = 86;
            public static final int INSTORE_WALLET = 33;
            public static final int IOS_GSA = 118;
            public static final int JUSTSPEAK = 72;
            public static final int KATNISS = 75;
            public static final int KEEP = 128;
            public static final int KIDS_COMMUNICATOR = 64;
            public static final int LATIN_IME = 36;
            public static final int LAUNCHPAD_TOYS = 102;
            public static final int LB_A = 6;
            public static final int LB_AS = 116;
            public static final int LB_C = 24;
            public static final int LB_CA = 35;
            public static final int LB_CFG = 117;
            public static final int LB_D = 19;
            public static final int LB_IA = 52;
            public static final int LB_P = 8;
            public static final int LB_S = 9;
            public static final int LB_T = 21;
            public static final int LE = 17;
            public static final int LEANBACK_EVENT = 134;
            public static final int LE_ULR_COUNTERS = 109;
            public static final int MAGAZINES = 4;
            public static final int MAPS_API = 79;
            public static final int METALOG_COUNTERS = 103;
            public static final int MOBILESDK_CLIENT = 104;
            public static final int MOVIES = 74;
            public static final int MUSIC = 1;
            public static final int NETSTATS = 62;
            public static final int NEWSSTAND = 63;
            public static final int NEWS_WEATHER = 38;
            public static final int NFC_PROGRAMMER = 61;
            public static final int NOVA = 34;
            public static final int PANCETTA_MOBILE_HOST = 138;
            public static final int PANCETTA_MOBILE_HOST_COUNTERS = 139;
            public static final int PERF_PROFILE = 73;
            public static final int PERSONAL_BROWSER_LOGGER = 37;
            public static final int PERSONAL_LOGGER = 22;
            public static final int PHENOTYPE = 69;
            public static final int PHENOTYPE_COUNTERS = 70;
            public static final int PHOTOS = 42;
            public static final int PIXEL_PERFECT = 54;
            public static final int PLACES_NO_GLS_CONSENT = 97;
            public static final int PROC_STATS = 93;
            public static final int SAMPLE_SHM = 136;
            public static final int SENSE = 83;
            public static final int SETUP_WIZARD = 87;
            public static final int SHEETS = 57;
            public static final int SHERLOG = 108;
            public static final int SLIDES = 58;
            public static final int SOCIAL = 32;
            public static final int SOCIAL_AFFINITY = 41;
            public static final int SOCIAL_APPINVITE = 76;
            public static final int SOCIAL_USER_LOCATION = 101;
            public static final int SOCIAL_WEB = 47;
            public static final int STORE = 0;
            public static final int TACHYON_COUNTERS = 99;
            public static final int TACHYON_LOG_REQUEST = 98;
            public static final int TAILORMADE = 127;
            public static final int TAP_AND_PAY_APP = 119;
            public static final int TAP_AND_PAY_GCORE = 94;
            public static final int TELEMATICS = 49;
            public static final int TRANSOM = 114;
            public static final int TRANSOM_COUNTERS = 115;
            public static final int TRON = 89;
            public static final int TRON_COUNTERS = 90;
            public static final int UDR = 30;
            public static final int UNKNOWN = -1;
            public static final int VIDEO = 3;
            public static final int VISION = 100;
            public static final int VR = 85;
            public static final int WARP = 60;
            public static final int WEB_STORE = 65;
            public static final int WIFI_ASSISTANT = 66;
            public static final int WORK_STORE = 132;
        }

        public interface SchedulerType {
            public static final int ASAP = 1;
            public static final int DEFAULT_PERIODIC = 2;
            public static final int QOS_DEFAULT_PERIODIC = 4;
            public static final int QOS_FAST_ONEOFF = 3;
            public static final int QOS_UNMETERED_PERIODIC = 5;
            public static final int UNKNOWN_SCHEDULER = 0;
        }

        public static LogRequest[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new LogRequest[0];
                    }
                }
            }
            return _emptyArray;
        }

        public LogRequest() {
            clear();
        }

        public LogRequest clear() {
            this.requestTimeMs = 0;
            this.requestUptimeMs = 0;
            this.clientInfo = null;
            this.logSource = -1;
            this.logSourceName = BuildConfig.VERSION_NAME;
            this.zwiebackCookie = BuildConfig.VERSION_NAME;
            this.logEvent = LogEvent.emptyArray();
            this.serializedLogEvents = WireFormatNano.EMPTY_BYTES_ARRAY;
            this.qosTier = 0;
            this.scheduler = 0;
            this.deviceStatus = null;
            this.externalTimestamp = null;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof LogRequest)) {
                return false;
            }
            LogRequest other = (LogRequest) o;
            if (this.requestTimeMs != other.requestTimeMs || this.requestUptimeMs != other.requestUptimeMs) {
                return false;
            }
            if (this.clientInfo == null) {
                if (other.clientInfo != null) {
                    return false;
                }
            } else if (!this.clientInfo.equals(other.clientInfo)) {
                return false;
            }
            if (this.logSource != other.logSource) {
                return false;
            }
            if (this.logSourceName == null) {
                if (other.logSourceName != null) {
                    return false;
                }
            } else if (!this.logSourceName.equals(other.logSourceName)) {
                return false;
            }
            if (this.zwiebackCookie == null) {
                if (other.zwiebackCookie != null) {
                    return false;
                }
            } else if (!this.zwiebackCookie.equals(other.zwiebackCookie)) {
                return false;
            }
            if (!InternalNano.equals(this.logEvent, other.logEvent) || !InternalNano.equals(this.serializedLogEvents, other.serializedLogEvents) || this.qosTier != other.qosTier || this.scheduler != other.scheduler) {
                return false;
            }
            if (this.deviceStatus == null) {
                if (other.deviceStatus != null) {
                    return false;
                }
            } else if (!this.deviceStatus.equals(other.deviceStatus)) {
                return false;
            }
            if (this.externalTimestamp == null) {
                if (other.externalTimestamp != null) {
                    return false;
                }
            } else if (!this.externalTimestamp.equals(other.externalTimestamp)) {
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
            int hashCode = (((((((((((((((((((((((((getClass().getName().hashCode() + 527) * 31) + ((int) (this.requestTimeMs ^ (this.requestTimeMs >>> 32)))) * 31) + ((int) (this.requestUptimeMs ^ (this.requestUptimeMs >>> 32)))) * 31) + (this.clientInfo == null ? 0 : this.clientInfo.hashCode())) * 31) + this.logSource) * 31) + (this.logSourceName == null ? 0 : this.logSourceName.hashCode())) * 31) + (this.zwiebackCookie == null ? 0 : this.zwiebackCookie.hashCode())) * 31) + InternalNano.hashCode(this.logEvent)) * 31) + InternalNano.hashCode(this.serializedLogEvents)) * 31) + this.qosTier) * 31) + this.scheduler) * 31) + (this.deviceStatus == null ? 0 : this.deviceStatus.hashCode())) * 31) + (this.externalTimestamp == null ? 0 : this.externalTimestamp.hashCode())) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.clientInfo != null) {
                output.writeMessage(1, this.clientInfo);
            }
            if (this.logSource != -1) {
                output.writeInt32(2, this.logSource);
            }
            if (this.logEvent != null && this.logEvent.length > 0) {
                for (LogEvent element : this.logEvent) {
                    if (element != null) {
                        output.writeMessage(3, element);
                    }
                }
            }
            if (this.requestTimeMs != 0) {
                output.writeInt64(4, this.requestTimeMs);
            }
            if (this.serializedLogEvents != null && this.serializedLogEvents.length > 0) {
                for (byte[] element2 : this.serializedLogEvents) {
                    if (element2 != null) {
                        output.writeBytes(5, element2);
                    }
                }
            }
            if (!this.logSourceName.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(6, this.logSourceName);
            }
            if (!this.zwiebackCookie.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(7, this.zwiebackCookie);
            }
            if (this.requestUptimeMs != 0) {
                output.writeInt64(8, this.requestUptimeMs);
            }
            if (this.qosTier != 0) {
                output.writeInt32(9, this.qosTier);
            }
            if (this.scheduler != 0) {
                output.writeInt32(10, this.scheduler);
            }
            if (this.deviceStatus != null) {
                output.writeMessage(11, this.deviceStatus);
            }
            if (this.externalTimestamp != null) {
                output.writeMessage(12, this.externalTimestamp);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.clientInfo != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(1, this.clientInfo);
            }
            if (this.logSource != -1) {
                size += CodedOutputByteBufferNano.computeInt32Size(2, this.logSource);
            }
            if (this.logEvent != null && this.logEvent.length > 0) {
                for (LogEvent element : this.logEvent) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(3, element);
                    }
                }
            }
            if (this.requestTimeMs != 0) {
                size += CodedOutputByteBufferNano.computeInt64Size(4, this.requestTimeMs);
            }
            if (this.serializedLogEvents != null && this.serializedLogEvents.length > 0) {
                int dataCount = 0;
                int dataSize = 0;
                for (byte[] element2 : this.serializedLogEvents) {
                    if (element2 != null) {
                        dataCount++;
                        dataSize += CodedOutputByteBufferNano.computeBytesSizeNoTag(element2);
                    }
                }
                size = (size + dataSize) + (dataCount * 1);
            }
            if (!this.logSourceName.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(6, this.logSourceName);
            }
            if (!this.zwiebackCookie.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(7, this.zwiebackCookie);
            }
            if (this.requestUptimeMs != 0) {
                size += CodedOutputByteBufferNano.computeInt64Size(8, this.requestUptimeMs);
            }
            if (this.qosTier != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(9, this.qosTier);
            }
            if (this.scheduler != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(10, this.scheduler);
            }
            if (this.deviceStatus != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(11, this.deviceStatus);
            }
            if (this.externalTimestamp != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(12, this.externalTimestamp);
            }
            return size;
        }

        public LogRequest mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                int value;
                int arrayLength;
                int i;
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        if (this.clientInfo == null) {
                            this.clientInfo = new ClientInfo();
                        }
                        input.readMessage(this.clientInfo);
                        continue;
                    case LogSource.GMS_CORE_PEOPLE /*16*/:
                        value = input.readInt32();
                        switch (value) {
                            case LogSource.UNKNOWN /*-1*/:
                            case Action.UNKNOWN /*0*/:
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
                            case LogSource.LE /*17*/:
                            case LogSource.GOOGLE_ANALYTICS /*18*/:
                            case LogSource.LB_D /*19*/:
                            case LogSource.ANDROID_GSA /*20*/:
                            case LogSource.LB_T /*21*/:
                            case LogSource.PERSONAL_LOGGER /*22*/:
                            case LogSource.GMS_CORE_WALLET_MERCHANT_ERROR /*23*/:
                            case LogSource.LB_C /*24*/:
                            case LogSource.ANDROID_AUTH /*25*/:
                            case LogSource.ANDROID_CAMERA /*26*/:
                            case LogSource.CW /*27*/:
                            case LogSource.GEL /*28*/:
                            case LogSource.DNA_PROBER /*29*/:
                            case LogSource.UDR /*30*/:
                            case LogSource.GMS_CORE_WALLET /*31*/:
                            case LogSource.SOCIAL /*32*/:
                            case LogSource.INSTORE_WALLET /*33*/:
                            case LogSource.NOVA /*34*/:
                            case LogSource.LB_CA /*35*/:
                            case LogSource.LATIN_IME /*36*/:
                            case LogSource.PERSONAL_BROWSER_LOGGER /*37*/:
                            case LogSource.NEWS_WEATHER /*38*/:
                            case LogSource.HANGOUT /*39*/:
                            case LogSource.COPRESENCE /*40*/:
                            case LogSource.SOCIAL_AFFINITY /*41*/:
                            case LogSource.PHOTOS /*42*/:
                            case LogSource.GCM /*43*/:
                            case LogSource.GOKART /*44*/:
                            case LogSource.FINDR /*45*/:
                            case LogSource.ANDROID_MESSAGING /*46*/:
                            case LogSource.SOCIAL_WEB /*47*/:
                            case LogSource.BACKDROP /*48*/:
                            case LogSource.TELEMATICS /*49*/:
                            case LogSource.HANGOUT_LOG_REQUEST /*50*/:
                            case LogSource.GVC_HARVESTER /*51*/:
                            case LogSource.LB_IA /*52*/:
                            case LogSource.CAR /*53*/:
                            case LogSource.PIXEL_PERFECT /*54*/:
                            case LogSource.DRIVE /*55*/:
                            case LogSource.DOCS /*56*/:
                            case LogSource.SHEETS /*57*/:
                            case LogSource.SLIDES /*58*/:
                            case LogSource.IME /*59*/:
                            case LogSource.WARP /*60*/:
                            case LogSource.NFC_PROGRAMMER /*61*/:
                            case LogSource.NETSTATS /*62*/:
                            case LogSource.NEWSSTAND /*63*/:
                            case LogSource.KIDS_COMMUNICATOR /*64*/:
                            case LogSource.WEB_STORE /*65*/:
                            case LogSource.WIFI_ASSISTANT /*66*/:
                            case LogSource.CAST_SENDER_SDK /*67*/:
                            case LogSource.CRONET_SOCIAL /*68*/:
                            case LogSource.PHENOTYPE /*69*/:
                            case LogSource.PHENOTYPE_COUNTERS /*70*/:
                            case LogSource.CHROME_INFRA /*71*/:
                            case LogSource.JUSTSPEAK /*72*/:
                            case LogSource.PERF_PROFILE /*73*/:
                            case LogSource.MOVIES /*74*/:
                            case LogSource.KATNISS /*75*/:
                            case LogSource.SOCIAL_APPINVITE /*76*/:
                            case LogSource.GMM_COUNTERS /*77*/:
                            case LogSource.BOND_ONEGOOGLE /*78*/:
                            case LogSource.MAPS_API /*79*/:
                            case LogSource.CRONET_ANDROID_GSA /*80*/:
                            case LogSource.GOOGLE_FIT_WEARABLE /*81*/:
                            case LogSource.GOOGLE_EXPRESS /*82*/:
                            case LogSource.SENSE /*83*/:
                            case LogSource.ANDROID_BACKUP /*84*/:
                            case LogSource.VR /*85*/:
                            case LogSource.IME_COUNTERS /*86*/:
                            case LogSource.SETUP_WIZARD /*87*/:
                            case LogSource.EMERGENCY_ASSIST /*88*/:
                            case LogSource.TRON /*89*/:
                            case LogSource.TRON_COUNTERS /*90*/:
                            case LogSource.BATTERY_STATS /*91*/:
                            case LogSource.DISK_STATS /*92*/:
                            case LogSource.PROC_STATS /*93*/:
                            case LogSource.TAP_AND_PAY_GCORE /*94*/:
                            case LogSource.A11YLOGGER /*95*/:
                            case LogSource.GCM_COUNTERS /*96*/:
                            case LogSource.PLACES_NO_GLS_CONSENT /*97*/:
                            case LogSource.TACHYON_LOG_REQUEST /*98*/:
                            case LogSource.TACHYON_COUNTERS /*99*/:
                            case LogSource.VISION /*100*/:
                            case LogSource.SOCIAL_USER_LOCATION /*101*/:
                            case LogSource.LAUNCHPAD_TOYS /*102*/:
                            case LogSource.METALOG_COUNTERS /*103*/:
                            case LogSource.MOBILESDK_CLIENT /*104*/:
                            case LogSource.ANDROID_VERIFY_APPS /*105*/:
                            case LogSource.ADSHIELD /*106*/:
                            case LogSource.GRAPHICS_STATS /*107*/:
                            case LogSource.SHERLOG /*108*/:
                            case LogSource.LE_ULR_COUNTERS /*109*/:
                            case LogSource.GMM_UE3 /*110*/:
                            case LogSource.CALENDAR /*111*/:
                            case LogSource.ENDER /*112*/:
                            case LogSource.FAMILY_COMPASS /*113*/:
                            case LogSource.TRANSOM /*114*/:
                            case LogSource.TRANSOM_COUNTERS /*115*/:
                            case LogSource.LB_AS /*116*/:
                            case LogSource.LB_CFG /*117*/:
                            case LogSource.IOS_GSA /*118*/:
                            case LogSource.TAP_AND_PAY_APP /*119*/:
                            case LogSource.FLYDROID /*120*/:
                            case LogSource.CPANEL_APP /*121*/:
                            case LogSource.ANDROID_SNET_GCORE /*122*/:
                            case LogSource.ANDROID_SNET_IDLE /*123*/:
                            case LogSource.ANDROID_SNET_JAR /*124*/:
                            case LogSource.CONTEXT_MANAGER /*125*/:
                            case LogSource.CLASSROOM /*126*/:
                            case LogSource.TAILORMADE /*127*/:
                            case LogSource.KEEP /*128*/:
                            case LogSource.GMM_BRIIM_COUNTERS /*129*/:
                            case LogSource.CHROMECAST_APP_LOG /*130*/:
                            case LogSource.DROP_BOX /*131*/:
                            case LogSource.WORK_STORE /*132*/:
                            case LogSource.ADWORDS_MOBILE /*133*/:
                            case LogSource.LEANBACK_EVENT /*134*/:
                            case LogSource.ANDROID_GMAIL /*135*/:
                            case LogSource.SAMPLE_SHM /*136*/:
                            case LogSource.ICORE /*137*/:
                            case LogSource.PANCETTA_MOBILE_HOST /*138*/:
                            case LogSource.PANCETTA_MOBILE_HOST_COUNTERS /*139*/:
                            case LogSource.GPLUS_ANDROID_SYSTEM_HEALTH /*140*/:
                                this.logSource = value;
                                break;
                            default:
                                continue;
                        }
                    case LogSource.ANDROID_CAMERA /*26*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 26);
                        if (this.logEvent == null) {
                            i = 0;
                        } else {
                            i = this.logEvent.length;
                        }
                        LogEvent[] newArray = new LogEvent[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.logEvent, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new LogEvent();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i++;
                        }
                        newArray[i] = new LogEvent();
                        input.readMessage(newArray[i]);
                        this.logEvent = newArray;
                        continue;
                    case LogSource.SOCIAL /*32*/:
                        this.requestTimeMs = input.readInt64();
                        continue;
                    case LogSource.PHOTOS /*42*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 42);
                        i = this.serializedLogEvents == null ? 0 : this.serializedLogEvents.length;
                        byte[][] newArray2 = new byte[(i + arrayLength)][];
                        if (i != 0) {
                            System.arraycopy(this.serializedLogEvents, 0, newArray2, 0, i);
                        }
                        while (i < newArray2.length - 1) {
                            newArray2[i] = input.readBytes();
                            input.readTag();
                            i++;
                        }
                        newArray2[i] = input.readBytes();
                        this.serializedLogEvents = newArray2;
                        continue;
                    case LogSource.HANGOUT_LOG_REQUEST /*50*/:
                        this.logSourceName = input.readString();
                        continue;
                    case LogSource.SLIDES /*58*/:
                        this.zwiebackCookie = input.readString();
                        continue;
                    case LogSource.KIDS_COMMUNICATOR /*64*/:
                        this.requestUptimeMs = input.readInt64();
                        continue;
                    case LogSource.JUSTSPEAK /*72*/:
                        value = input.readInt32();
                        switch (value) {
                            case Action.UNKNOWN /*0*/:
                            case TimeSelection.Type.TEMPORARY /*1*/:
                            case TimeSelection.Type.INDEFINITELY /*2*/:
                            case TimeSelection.Type.CUSTOM /*3*/:
                            case OvenFreshResult.NO_ACCOUNTS /*4*/:
                                this.qosTier = value;
                                break;
                            default:
                                continue;
                        }
                    case LogSource.CRONET_ANDROID_GSA /*80*/:
                        value = input.readInt32();
                        switch (value) {
                            case Action.UNKNOWN /*0*/:
                            case TimeSelection.Type.TEMPORARY /*1*/:
                            case TimeSelection.Type.INDEFINITELY /*2*/:
                            case TimeSelection.Type.CUSTOM /*3*/:
                            case OvenFreshResult.NO_ACCOUNTS /*4*/:
                            case Type.ADD_NEW_SHARES /*5*/:
                                this.scheduler = value;
                                break;
                            default:
                                continue;
                        }
                    case LogSource.TRON_COUNTERS /*90*/:
                        if (this.deviceStatus == null) {
                            this.deviceStatus = new DeviceStatus();
                        }
                        input.readMessage(this.deviceStatus);
                        continue;
                    case LogSource.TACHYON_LOG_REQUEST /*98*/:
                        if (this.externalTimestamp == null) {
                            this.externalTimestamp = new ExternalTimestamp();
                        }
                        input.readMessage(this.externalTimestamp);
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

        public static LogRequest parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (LogRequest) MessageNano.mergeFrom(new LogRequest(), data);
        }

        public static LogRequest parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new LogRequest().mergeFrom(input);
        }
    }

    public static final class LogResponse extends ExtendableMessageNano<LogResponse> {
        private static volatile LogResponse[] _emptyArray;
        public ExperimentIdList experiments;
        public long nextRequestWaitMillis;
        public QosTiersOverride qosTier;

        public static LogResponse[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new LogResponse[0];
                    }
                }
            }
            return _emptyArray;
        }

        public LogResponse() {
            clear();
        }

        public LogResponse clear() {
            this.nextRequestWaitMillis = -1;
            this.experiments = null;
            this.qosTier = null;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof LogResponse)) {
                return false;
            }
            LogResponse other = (LogResponse) o;
            if (this.nextRequestWaitMillis != other.nextRequestWaitMillis) {
                return false;
            }
            if (this.experiments == null) {
                if (other.experiments != null) {
                    return false;
                }
            } else if (!this.experiments.equals(other.experiments)) {
                return false;
            }
            if (this.qosTier == null) {
                if (other.qosTier != null) {
                    return false;
                }
            } else if (!this.qosTier.equals(other.qosTier)) {
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
            int hashCode = (((((((getClass().getName().hashCode() + 527) * 31) + ((int) (this.nextRequestWaitMillis ^ (this.nextRequestWaitMillis >>> 32)))) * 31) + (this.experiments == null ? 0 : this.experiments.hashCode())) * 31) + (this.qosTier == null ? 0 : this.qosTier.hashCode())) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.nextRequestWaitMillis != -1) {
                output.writeInt64(1, this.nextRequestWaitMillis);
            }
            if (this.experiments != null) {
                output.writeMessage(2, this.experiments);
            }
            if (this.qosTier != null) {
                output.writeMessage(3, this.qosTier);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.nextRequestWaitMillis != -1) {
                size += CodedOutputByteBufferNano.computeInt64Size(1, this.nextRequestWaitMillis);
            }
            if (this.experiments != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(2, this.experiments);
            }
            if (this.qosTier != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(3, this.qosTier);
            }
            return size;
        }

        public LogResponse mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_GET_LINK /*8*/:
                        this.nextRequestWaitMillis = input.readInt64();
                        continue;
                    case LogSource.GOOGLE_ANALYTICS /*18*/:
                        if (this.experiments == null) {
                            this.experiments = new ExperimentIdList();
                        }
                        input.readMessage(this.experiments);
                        continue;
                    case LogSource.ANDROID_CAMERA /*26*/:
                        if (this.qosTier == null) {
                            this.qosTier = new QosTiersOverride();
                        }
                        input.readMessage(this.qosTier);
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

        public static LogResponse parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (LogResponse) MessageNano.mergeFrom(new LogResponse(), data);
        }

        public static LogResponse parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new LogResponse().mergeFrom(input);
        }
    }

    public static final class QosTierConfiguration extends ExtendableMessageNano<QosTierConfiguration> {
        private static volatile QosTierConfiguration[] _emptyArray;
        public String logSourceName;
        public int qosTier;

        public interface QosTier {
            public static final int DEFAULT = 0;
            public static final int FAST_IF_RADIO_AWAKE = 3;
            public static final int NEVER = 4;
            public static final int UNMETERED_ONLY = 1;
            public static final int UNMETERED_OR_DAILY = 2;
        }

        public static QosTierConfiguration[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new QosTierConfiguration[0];
                    }
                }
            }
            return _emptyArray;
        }

        public QosTierConfiguration() {
            clear();
        }

        public QosTierConfiguration clear() {
            this.logSourceName = BuildConfig.VERSION_NAME;
            this.qosTier = 0;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof QosTierConfiguration)) {
                return false;
            }
            QosTierConfiguration other = (QosTierConfiguration) o;
            if (this.logSourceName == null) {
                if (other.logSourceName != null) {
                    return false;
                }
            } else if (!this.logSourceName.equals(other.logSourceName)) {
                return false;
            }
            if (this.qosTier != other.qosTier) {
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
            int hashCode = (((((getClass().getName().hashCode() + 527) * 31) + (this.logSourceName == null ? 0 : this.logSourceName.hashCode())) * 31) + this.qosTier) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (!this.logSourceName.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(1, this.logSourceName);
            }
            if (this.qosTier != 0) {
                output.writeInt32(2, this.qosTier);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (!this.logSourceName.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.logSourceName);
            }
            if (this.qosTier != 0) {
                return size + CodedOutputByteBufferNano.computeInt32Size(2, this.qosTier);
            }
            return size;
        }

        public QosTierConfiguration mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        this.logSourceName = input.readString();
                        continue;
                    case LogSource.GMS_CORE_PEOPLE /*16*/:
                        int value = input.readInt32();
                        switch (value) {
                            case Action.UNKNOWN /*0*/:
                            case TimeSelection.Type.TEMPORARY /*1*/:
                            case TimeSelection.Type.INDEFINITELY /*2*/:
                            case TimeSelection.Type.CUSTOM /*3*/:
                            case OvenFreshResult.NO_ACCOUNTS /*4*/:
                                this.qosTier = value;
                                break;
                            default:
                                continue;
                        }
                    default:
                        if (!storeUnknownField(input, tag)) {
                            break;
                        }
                        continue;
                }
                return this;
            }
        }

        public static QosTierConfiguration parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (QosTierConfiguration) MessageNano.mergeFrom(new QosTierConfiguration(), data);
        }

        public static QosTierConfiguration parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new QosTierConfiguration().mergeFrom(input);
        }
    }

    public static final class QosTiersOverride extends ExtendableMessageNano<QosTiersOverride> {
        private static volatile QosTiersOverride[] _emptyArray;
        public QosTierConfiguration[] qosTierConfiguration;
        public long qosTierFingerprint;

        public static QosTiersOverride[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new QosTiersOverride[0];
                    }
                }
            }
            return _emptyArray;
        }

        public QosTiersOverride() {
            clear();
        }

        public QosTiersOverride clear() {
            this.qosTierConfiguration = QosTierConfiguration.emptyArray();
            this.qosTierFingerprint = 0;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof QosTiersOverride)) {
                return false;
            }
            QosTiersOverride other = (QosTiersOverride) o;
            if (!InternalNano.equals(this.qosTierConfiguration, other.qosTierConfiguration) || this.qosTierFingerprint != other.qosTierFingerprint) {
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
            int hashCode = (((((getClass().getName().hashCode() + 527) * 31) + InternalNano.hashCode(this.qosTierConfiguration)) * 31) + ((int) (this.qosTierFingerprint ^ (this.qosTierFingerprint >>> 32)))) * 31;
            int hashCode2 = (this.unknownFieldData == null || this.unknownFieldData.isEmpty()) ? 0 : this.unknownFieldData.hashCode();
            return hashCode + hashCode2;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.qosTierConfiguration != null && this.qosTierConfiguration.length > 0) {
                for (QosTierConfiguration element : this.qosTierConfiguration) {
                    if (element != null) {
                        output.writeMessage(1, element);
                    }
                }
            }
            if (this.qosTierFingerprint != 0) {
                output.writeInt64(2, this.qosTierFingerprint);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.qosTierConfiguration != null && this.qosTierConfiguration.length > 0) {
                for (QosTierConfiguration element : this.qosTierConfiguration) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(1, element);
                    }
                }
            }
            if (this.qosTierFingerprint != 0) {
                return size + CodedOutputByteBufferNano.computeInt64Size(2, this.qosTierFingerprint);
            }
            return size;
        }

        public QosTiersOverride mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        int i;
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 10);
                        if (this.qosTierConfiguration == null) {
                            i = 0;
                        } else {
                            i = this.qosTierConfiguration.length;
                        }
                        QosTierConfiguration[] newArray = new QosTierConfiguration[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.qosTierConfiguration, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new QosTierConfiguration();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i++;
                        }
                        newArray[i] = new QosTierConfiguration();
                        input.readMessage(newArray[i]);
                        this.qosTierConfiguration = newArray;
                        continue;
                    case LogSource.GMS_CORE_PEOPLE /*16*/:
                        this.qosTierFingerprint = input.readInt64();
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

        public static QosTiersOverride parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (QosTiersOverride) MessageNano.mergeFrom(new QosTiersOverride(), data);
        }

        public static QosTiersOverride parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new QosTiersOverride().mergeFrom(input);
        }
    }
}
