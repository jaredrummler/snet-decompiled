package com.google.wireless.android.stats.platform;

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

public interface PlatformStatsLogProto {

    public static final class PlatformStatsLog extends ExtendableMessageNano<PlatformStatsLog> {
        private static volatile PlatformStatsLog[] _emptyArray;
        public String contents;
        public DropboxEntry[] dropboxEntry;
        public long endReportMillis;
        public String packageDump;
        public PackageInfo[] packageInfo;
        public long startReportMillis;
        public int userCount;

        public static final class DropboxEntry extends ExtendableMessageNano<DropboxEntry> {
            private static volatile DropboxEntry[] _emptyArray;
            public boolean logcatStripped;
            public String tag;
            public long timeMillis;
            public byte[] value;

            public static DropboxEntry[] emptyArray() {
                if (_emptyArray == null) {
                    synchronized (InternalNano.LAZY_INIT_LOCK) {
                        if (_emptyArray == null) {
                            _emptyArray = new DropboxEntry[0];
                        }
                    }
                }
                return _emptyArray;
            }

            public DropboxEntry() {
                clear();
            }

            public DropboxEntry clear() {
                this.tag = BuildConfig.VERSION_NAME;
                this.value = WireFormatNano.EMPTY_BYTES;
                this.timeMillis = 0;
                this.logcatStripped = false;
                this.unknownFieldData = null;
                this.cachedSize = -1;
                return this;
            }

            public boolean equals(Object o) {
                if (o == this) {
                    return true;
                }
                if (!(o instanceof DropboxEntry)) {
                    return false;
                }
                DropboxEntry other = (DropboxEntry) o;
                if (this.tag == null) {
                    if (other.tag != null) {
                        return false;
                    }
                } else if (!this.tag.equals(other.tag)) {
                    return false;
                }
                if (!Arrays.equals(this.value, other.value) || this.timeMillis != other.timeMillis || this.logcatStripped != other.logcatStripped) {
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
                int hashCode = (((((((((getClass().getName().hashCode() + 527) * 31) + (this.tag == null ? 0 : this.tag.hashCode())) * 31) + Arrays.hashCode(this.value)) * 31) + ((int) (this.timeMillis ^ (this.timeMillis >>> 32)))) * 31) + (this.logcatStripped ? 1231 : 1237)) * 31;
                if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                    i = this.unknownFieldData.hashCode();
                }
                return hashCode + i;
            }

            public void writeTo(CodedOutputByteBufferNano output) throws IOException {
                if (!this.tag.equals(BuildConfig.VERSION_NAME)) {
                    output.writeString(1, this.tag);
                }
                if (!Arrays.equals(this.value, WireFormatNano.EMPTY_BYTES)) {
                    output.writeBytes(2, this.value);
                }
                if (this.timeMillis != 0) {
                    output.writeInt64(3, this.timeMillis);
                }
                if (this.logcatStripped) {
                    output.writeBool(4, this.logcatStripped);
                }
                super.writeTo(output);
            }

            protected int computeSerializedSize() {
                int size = super.computeSerializedSize();
                if (!this.tag.equals(BuildConfig.VERSION_NAME)) {
                    size += CodedOutputByteBufferNano.computeStringSize(1, this.tag);
                }
                if (!Arrays.equals(this.value, WireFormatNano.EMPTY_BYTES)) {
                    size += CodedOutputByteBufferNano.computeBytesSize(2, this.value);
                }
                if (this.timeMillis != 0) {
                    size += CodedOutputByteBufferNano.computeInt64Size(3, this.timeMillis);
                }
                if (this.logcatStripped) {
                    return size + CodedOutputByteBufferNano.computeBoolSize(4, this.logcatStripped);
                }
                return size;
            }

            public DropboxEntry mergeFrom(CodedInputByteBufferNano input) throws IOException {
                while (true) {
                    int tag = input.readTag();
                    switch (tag) {
                        case Action.UNKNOWN /*0*/:
                            break;
                        case Type.TAP_ABOUT /*10*/:
                            this.tag = input.readString();
                            continue;
                        case LogSource.GOOGLE_ANALYTICS /*18*/:
                            this.value = input.readBytes();
                            continue;
                        case LogSource.LB_C /*24*/:
                            this.timeMillis = input.readInt64();
                            continue;
                        case LogSource.SOCIAL /*32*/:
                            this.logcatStripped = input.readBool();
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

            public static DropboxEntry parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
                return (DropboxEntry) MessageNano.mergeFrom(new DropboxEntry(), data);
            }

            public static DropboxEntry parseFrom(CodedInputByteBufferNano input) throws IOException {
                return new DropboxEntry().mergeFrom(input);
            }
        }

        public static final class PackageInfo extends ExtendableMessageNano<PackageInfo> {
            private static volatile PackageInfo[] _emptyArray;
            public String name;
            public long uid;
            public int version;
            public String versionString;

            public static PackageInfo[] emptyArray() {
                if (_emptyArray == null) {
                    synchronized (InternalNano.LAZY_INIT_LOCK) {
                        if (_emptyArray == null) {
                            _emptyArray = new PackageInfo[0];
                        }
                    }
                }
                return _emptyArray;
            }

            public PackageInfo() {
                clear();
            }

            public PackageInfo clear() {
                this.name = BuildConfig.VERSION_NAME;
                this.version = 0;
                this.versionString = BuildConfig.VERSION_NAME;
                this.uid = 0;
                this.unknownFieldData = null;
                this.cachedSize = -1;
                return this;
            }

            public boolean equals(Object o) {
                if (o == this) {
                    return true;
                }
                if (!(o instanceof PackageInfo)) {
                    return false;
                }
                PackageInfo other = (PackageInfo) o;
                if (this.name == null) {
                    if (other.name != null) {
                        return false;
                    }
                } else if (!this.name.equals(other.name)) {
                    return false;
                }
                if (this.version != other.version) {
                    return false;
                }
                if (this.versionString == null) {
                    if (other.versionString != null) {
                        return false;
                    }
                } else if (!this.versionString.equals(other.versionString)) {
                    return false;
                }
                if (this.uid != other.uid) {
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
                int hashCode = (((((((((getClass().getName().hashCode() + 527) * 31) + (this.name == null ? 0 : this.name.hashCode())) * 31) + this.version) * 31) + (this.versionString == null ? 0 : this.versionString.hashCode())) * 31) + ((int) (this.uid ^ (this.uid >>> 32)))) * 31;
                if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                    i = this.unknownFieldData.hashCode();
                }
                return hashCode + i;
            }

            public void writeTo(CodedOutputByteBufferNano output) throws IOException {
                if (!this.name.equals(BuildConfig.VERSION_NAME)) {
                    output.writeString(1, this.name);
                }
                if (this.version != 0) {
                    output.writeInt32(2, this.version);
                }
                if (!this.versionString.equals(BuildConfig.VERSION_NAME)) {
                    output.writeString(3, this.versionString);
                }
                if (this.uid != 0) {
                    output.writeInt64(4, this.uid);
                }
                super.writeTo(output);
            }

            protected int computeSerializedSize() {
                int size = super.computeSerializedSize();
                if (!this.name.equals(BuildConfig.VERSION_NAME)) {
                    size += CodedOutputByteBufferNano.computeStringSize(1, this.name);
                }
                if (this.version != 0) {
                    size += CodedOutputByteBufferNano.computeInt32Size(2, this.version);
                }
                if (!this.versionString.equals(BuildConfig.VERSION_NAME)) {
                    size += CodedOutputByteBufferNano.computeStringSize(3, this.versionString);
                }
                if (this.uid != 0) {
                    return size + CodedOutputByteBufferNano.computeInt64Size(4, this.uid);
                }
                return size;
            }

            public PackageInfo mergeFrom(CodedInputByteBufferNano input) throws IOException {
                while (true) {
                    int tag = input.readTag();
                    switch (tag) {
                        case Action.UNKNOWN /*0*/:
                            break;
                        case Type.TAP_ABOUT /*10*/:
                            this.name = input.readString();
                            continue;
                        case LogSource.GMS_CORE_PEOPLE /*16*/:
                            this.version = input.readInt32();
                            continue;
                        case LogSource.ANDROID_CAMERA /*26*/:
                            this.versionString = input.readString();
                            continue;
                        case LogSource.SOCIAL /*32*/:
                            this.uid = input.readInt64();
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

            public static PackageInfo parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
                return (PackageInfo) MessageNano.mergeFrom(new PackageInfo(), data);
            }

            public static PackageInfo parseFrom(CodedInputByteBufferNano input) throws IOException {
                return new PackageInfo().mergeFrom(input);
            }
        }

        public static PlatformStatsLog[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new PlatformStatsLog[0];
                    }
                }
            }
            return _emptyArray;
        }

        public PlatformStatsLog() {
            clear();
        }

        public PlatformStatsLog clear() {
            this.startReportMillis = 0;
            this.endReportMillis = 0;
            this.contents = BuildConfig.VERSION_NAME;
            this.packageDump = BuildConfig.VERSION_NAME;
            this.packageInfo = PackageInfo.emptyArray();
            this.dropboxEntry = DropboxEntry.emptyArray();
            this.userCount = 0;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof PlatformStatsLog)) {
                return false;
            }
            PlatformStatsLog other = (PlatformStatsLog) o;
            if (this.startReportMillis != other.startReportMillis || this.endReportMillis != other.endReportMillis) {
                return false;
            }
            if (this.contents == null) {
                if (other.contents != null) {
                    return false;
                }
            } else if (!this.contents.equals(other.contents)) {
                return false;
            }
            if (this.packageDump == null) {
                if (other.packageDump != null) {
                    return false;
                }
            } else if (!this.packageDump.equals(other.packageDump)) {
                return false;
            }
            if (!InternalNano.equals(this.packageInfo, other.packageInfo) || !InternalNano.equals(this.dropboxEntry, other.dropboxEntry) || this.userCount != other.userCount) {
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
            int hashCode = (((((((((((((((getClass().getName().hashCode() + 527) * 31) + ((int) (this.startReportMillis ^ (this.startReportMillis >>> 32)))) * 31) + ((int) (this.endReportMillis ^ (this.endReportMillis >>> 32)))) * 31) + (this.contents == null ? 0 : this.contents.hashCode())) * 31) + (this.packageDump == null ? 0 : this.packageDump.hashCode())) * 31) + InternalNano.hashCode(this.packageInfo)) * 31) + InternalNano.hashCode(this.dropboxEntry)) * 31) + this.userCount) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.startReportMillis != 0) {
                output.writeInt64(1, this.startReportMillis);
            }
            if (this.endReportMillis != 0) {
                output.writeInt64(2, this.endReportMillis);
            }
            if (!this.contents.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(3, this.contents);
            }
            if (!this.packageDump.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(5, this.packageDump);
            }
            if (this.packageInfo != null && this.packageInfo.length > 0) {
                for (PackageInfo element : this.packageInfo) {
                    if (element != null) {
                        output.writeMessage(6, element);
                    }
                }
            }
            if (this.dropboxEntry != null && this.dropboxEntry.length > 0) {
                for (DropboxEntry element2 : this.dropboxEntry) {
                    if (element2 != null) {
                        output.writeMessage(7, element2);
                    }
                }
            }
            if (this.userCount != 0) {
                output.writeInt32(8, this.userCount);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.startReportMillis != 0) {
                size += CodedOutputByteBufferNano.computeInt64Size(1, this.startReportMillis);
            }
            if (this.endReportMillis != 0) {
                size += CodedOutputByteBufferNano.computeInt64Size(2, this.endReportMillis);
            }
            if (!this.contents.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(3, this.contents);
            }
            if (!this.packageDump.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(5, this.packageDump);
            }
            if (this.packageInfo != null && this.packageInfo.length > 0) {
                for (PackageInfo element : this.packageInfo) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(6, element);
                    }
                }
            }
            if (this.dropboxEntry != null && this.dropboxEntry.length > 0) {
                for (DropboxEntry element2 : this.dropboxEntry) {
                    if (element2 != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(7, element2);
                    }
                }
            }
            if (this.userCount != 0) {
                return size + CodedOutputByteBufferNano.computeInt32Size(8, this.userCount);
            }
            return size;
        }

        public PlatformStatsLog mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                int arrayLength;
                int i;
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_GET_LINK /*8*/:
                        this.startReportMillis = input.readInt64();
                        continue;
                    case LogSource.GMS_CORE_PEOPLE /*16*/:
                        this.endReportMillis = input.readInt64();
                        continue;
                    case LogSource.ANDROID_CAMERA /*26*/:
                        this.contents = input.readString();
                        continue;
                    case LogSource.PHOTOS /*42*/:
                        this.packageDump = input.readString();
                        continue;
                    case LogSource.HANGOUT_LOG_REQUEST /*50*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 50);
                        if (this.packageInfo == null) {
                            i = 0;
                        } else {
                            i = this.packageInfo.length;
                        }
                        PackageInfo[] newArray = new PackageInfo[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.packageInfo, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new PackageInfo();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i++;
                        }
                        newArray[i] = new PackageInfo();
                        input.readMessage(newArray[i]);
                        this.packageInfo = newArray;
                        continue;
                    case LogSource.SLIDES /*58*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 58);
                        if (this.dropboxEntry == null) {
                            i = 0;
                        } else {
                            i = this.dropboxEntry.length;
                        }
                        DropboxEntry[] newArray2 = new DropboxEntry[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.dropboxEntry, 0, newArray2, 0, i);
                        }
                        while (i < newArray2.length - 1) {
                            newArray2[i] = new DropboxEntry();
                            input.readMessage(newArray2[i]);
                            input.readTag();
                            i++;
                        }
                        newArray2[i] = new DropboxEntry();
                        input.readMessage(newArray2[i]);
                        this.dropboxEntry = newArray2;
                        continue;
                    case LogSource.KIDS_COMMUNICATOR /*64*/:
                        this.userCount = input.readInt32();
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

        public static PlatformStatsLog parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (PlatformStatsLog) MessageNano.mergeFrom(new PlatformStatsLog(), data);
        }

        public static PlatformStatsLog parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new PlatformStatsLog().mergeFrom(input);
        }
    }
}
