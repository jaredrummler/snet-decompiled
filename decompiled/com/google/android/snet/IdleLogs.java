package com.google.android.snet;

import com.google.android.gms.lint.BuildConfig;
import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.InternalNano;
import com.google.protobuf.nano.InvalidProtocolBufferNanoException;
import com.google.protobuf.nano.MessageNano;
import com.google.protobuf.nano.WireFormatNano;
import com.google.wireless.android.play.playlog.proto.ClientAnalytics.LogRequest.LogSource;
import com.google.wireless.android.play.playlog.proto.UserLocation.LocationSharingEvent.Type;
import java.io.IOException;
import java.util.Arrays;

public interface IdleLogs {
    public static final int BAD_HASH = 2;
    public static final int GOOD_HASH = 1;
    public static final int HASH_MATCH_UNSPECIFIED = 0;
    public static final int UNKNOWN_HASH = 3;

    public static final class AppInfo extends MessageNano {
        private static volatile AppInfo[] _emptyArray;
        public String apkSha256;
        public byte[] apkSha256Bytes;
        public String packageName;
        public String[] signatureSha256;
        public byte[][] signatureSha256Bytes;
        public boolean systemApp;

        public static AppInfo[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new AppInfo[IdleLogs.HASH_MATCH_UNSPECIFIED];
                    }
                }
            }
            return _emptyArray;
        }

        public AppInfo() {
            clear();
        }

        public AppInfo clear() {
            this.packageName = BuildConfig.VERSION_NAME;
            this.apkSha256 = BuildConfig.VERSION_NAME;
            this.signatureSha256 = WireFormatNano.EMPTY_STRING_ARRAY;
            this.systemApp = false;
            this.apkSha256Bytes = WireFormatNano.EMPTY_BYTES;
            this.signatureSha256Bytes = WireFormatNano.EMPTY_BYTES_ARRAY;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            int i;
            if (!this.packageName.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(IdleLogs.GOOD_HASH, this.packageName);
            }
            if (!this.apkSha256.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(IdleLogs.BAD_HASH, this.apkSha256);
            }
            if (this.signatureSha256 != null && this.signatureSha256.length > 0) {
                for (i = IdleLogs.HASH_MATCH_UNSPECIFIED; i < this.signatureSha256.length; i += IdleLogs.GOOD_HASH) {
                    String element = this.signatureSha256[i];
                    if (element != null) {
                        output.writeString(IdleLogs.UNKNOWN_HASH, element);
                    }
                }
            }
            if (this.systemApp) {
                output.writeBool(4, this.systemApp);
            }
            if (!Arrays.equals(this.apkSha256Bytes, WireFormatNano.EMPTY_BYTES)) {
                output.writeBytes(5, this.apkSha256Bytes);
            }
            if (this.signatureSha256Bytes != null && this.signatureSha256Bytes.length > 0) {
                for (i = IdleLogs.HASH_MATCH_UNSPECIFIED; i < this.signatureSha256Bytes.length; i += IdleLogs.GOOD_HASH) {
                    byte[] element2 = this.signatureSha256Bytes[i];
                    if (element2 != null) {
                        output.writeBytes(6, element2);
                    }
                }
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int dataCount;
            int dataSize;
            int i;
            int size = super.computeSerializedSize();
            if (!this.packageName.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(IdleLogs.GOOD_HASH, this.packageName);
            }
            if (!this.apkSha256.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(IdleLogs.BAD_HASH, this.apkSha256);
            }
            if (this.signatureSha256 != null && this.signatureSha256.length > 0) {
                dataCount = IdleLogs.HASH_MATCH_UNSPECIFIED;
                dataSize = IdleLogs.HASH_MATCH_UNSPECIFIED;
                for (i = IdleLogs.HASH_MATCH_UNSPECIFIED; i < this.signatureSha256.length; i += IdleLogs.GOOD_HASH) {
                    String element = this.signatureSha256[i];
                    if (element != null) {
                        dataCount += IdleLogs.GOOD_HASH;
                        dataSize += CodedOutputByteBufferNano.computeStringSizeNoTag(element);
                    }
                }
                size = (size + dataSize) + (dataCount * IdleLogs.GOOD_HASH);
            }
            if (this.systemApp) {
                size += CodedOutputByteBufferNano.computeBoolSize(4, this.systemApp);
            }
            if (!Arrays.equals(this.apkSha256Bytes, WireFormatNano.EMPTY_BYTES)) {
                size += CodedOutputByteBufferNano.computeBytesSize(5, this.apkSha256Bytes);
            }
            if (this.signatureSha256Bytes == null || this.signatureSha256Bytes.length <= 0) {
                return size;
            }
            dataCount = IdleLogs.HASH_MATCH_UNSPECIFIED;
            dataSize = IdleLogs.HASH_MATCH_UNSPECIFIED;
            for (i = IdleLogs.HASH_MATCH_UNSPECIFIED; i < this.signatureSha256Bytes.length; i += IdleLogs.GOOD_HASH) {
                byte[] element2 = this.signatureSha256Bytes[i];
                if (element2 != null) {
                    dataCount += IdleLogs.GOOD_HASH;
                    dataSize += CodedOutputByteBufferNano.computeBytesSizeNoTag(element2);
                }
            }
            return (size + dataSize) + (dataCount * IdleLogs.GOOD_HASH);
        }

        public AppInfo mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                int arrayLength;
                int i;
                switch (tag) {
                    case IdleLogs.HASH_MATCH_UNSPECIFIED /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        this.packageName = input.readString();
                        continue;
                    case LogSource.GOOGLE_ANALYTICS /*18*/:
                        this.apkSha256 = input.readString();
                        continue;
                    case LogSource.ANDROID_CAMERA /*26*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 26);
                        i = this.signatureSha256 == null ? IdleLogs.HASH_MATCH_UNSPECIFIED : this.signatureSha256.length;
                        String[] newArray = new String[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.signatureSha256, IdleLogs.HASH_MATCH_UNSPECIFIED, newArray, IdleLogs.HASH_MATCH_UNSPECIFIED, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = input.readString();
                            input.readTag();
                            i += IdleLogs.GOOD_HASH;
                        }
                        newArray[i] = input.readString();
                        this.signatureSha256 = newArray;
                        continue;
                    case LogSource.SOCIAL /*32*/:
                        this.systemApp = input.readBool();
                        continue;
                    case LogSource.PHOTOS /*42*/:
                        this.apkSha256Bytes = input.readBytes();
                        continue;
                    case LogSource.HANGOUT_LOG_REQUEST /*50*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 50);
                        i = this.signatureSha256Bytes == null ? IdleLogs.HASH_MATCH_UNSPECIFIED : this.signatureSha256Bytes.length;
                        byte[][] newArray2 = new byte[(i + arrayLength)][];
                        if (i != 0) {
                            System.arraycopy(this.signatureSha256Bytes, IdleLogs.HASH_MATCH_UNSPECIFIED, newArray2, IdleLogs.HASH_MATCH_UNSPECIFIED, i);
                        }
                        while (i < newArray2.length - 1) {
                            newArray2[i] = input.readBytes();
                            input.readTag();
                            i += IdleLogs.GOOD_HASH;
                        }
                        newArray2[i] = input.readBytes();
                        this.signatureSha256Bytes = newArray2;
                        continue;
                    default:
                        if (!WireFormatNano.parseUnknownField(input, tag)) {
                            break;
                        }
                        continue;
                }
                return this;
            }
        }

        public static AppInfo parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (AppInfo) MessageNano.mergeFrom(new AppInfo(), data);
        }

        public static AppInfo parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new AppInfo().mergeFrom(input);
        }
    }

    public static final class DalvikCacheInfo extends MessageNano {
        private static volatile DalvikCacheInfo[] _emptyArray;
        public FileInfo[] changedFiles;

        public static final class FileInfo extends MessageNano {
            private static volatile FileInfo[] _emptyArray;
            public String filename;
            public byte[] sha256;
            public long timeSec;

            public static FileInfo[] emptyArray() {
                if (_emptyArray == null) {
                    synchronized (InternalNano.LAZY_INIT_LOCK) {
                        if (_emptyArray == null) {
                            _emptyArray = new FileInfo[IdleLogs.HASH_MATCH_UNSPECIFIED];
                        }
                    }
                }
                return _emptyArray;
            }

            public FileInfo() {
                clear();
            }

            public FileInfo clear() {
                this.filename = BuildConfig.VERSION_NAME;
                this.sha256 = WireFormatNano.EMPTY_BYTES;
                this.timeSec = 0;
                this.cachedSize = -1;
                return this;
            }

            public void writeTo(CodedOutputByteBufferNano output) throws IOException {
                if (!this.filename.equals(BuildConfig.VERSION_NAME)) {
                    output.writeString(IdleLogs.GOOD_HASH, this.filename);
                }
                if (!Arrays.equals(this.sha256, WireFormatNano.EMPTY_BYTES)) {
                    output.writeBytes(IdleLogs.BAD_HASH, this.sha256);
                }
                if (this.timeSec != 0) {
                    output.writeInt64(IdleLogs.UNKNOWN_HASH, this.timeSec);
                }
                super.writeTo(output);
            }

            protected int computeSerializedSize() {
                int size = super.computeSerializedSize();
                if (!this.filename.equals(BuildConfig.VERSION_NAME)) {
                    size += CodedOutputByteBufferNano.computeStringSize(IdleLogs.GOOD_HASH, this.filename);
                }
                if (!Arrays.equals(this.sha256, WireFormatNano.EMPTY_BYTES)) {
                    size += CodedOutputByteBufferNano.computeBytesSize((int) IdleLogs.BAD_HASH, this.sha256);
                }
                if (this.timeSec != 0) {
                    return size + CodedOutputByteBufferNano.computeInt64Size(IdleLogs.UNKNOWN_HASH, this.timeSec);
                }
                return size;
            }

            public FileInfo mergeFrom(CodedInputByteBufferNano input) throws IOException {
                while (true) {
                    int tag = input.readTag();
                    switch (tag) {
                        case IdleLogs.HASH_MATCH_UNSPECIFIED /*0*/:
                            break;
                        case Type.TAP_ABOUT /*10*/:
                            this.filename = input.readString();
                            continue;
                        case LogSource.GOOGLE_ANALYTICS /*18*/:
                            this.sha256 = input.readBytes();
                            continue;
                        case LogSource.LB_C /*24*/:
                            this.timeSec = input.readInt64();
                            continue;
                        default:
                            if (!WireFormatNano.parseUnknownField(input, tag)) {
                                break;
                            }
                            continue;
                    }
                    return this;
                }
            }

            public static FileInfo parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
                return (FileInfo) MessageNano.mergeFrom(new FileInfo(), data);
            }

            public static FileInfo parseFrom(CodedInputByteBufferNano input) throws IOException {
                return new FileInfo().mergeFrom(input);
            }
        }

        public static DalvikCacheInfo[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new DalvikCacheInfo[IdleLogs.HASH_MATCH_UNSPECIFIED];
                    }
                }
            }
            return _emptyArray;
        }

        public DalvikCacheInfo() {
            clear();
        }

        public DalvikCacheInfo clear() {
            this.changedFiles = FileInfo.emptyArray();
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.changedFiles != null && this.changedFiles.length > 0) {
                for (int i = IdleLogs.HASH_MATCH_UNSPECIFIED; i < this.changedFiles.length; i += IdleLogs.GOOD_HASH) {
                    FileInfo element = this.changedFiles[i];
                    if (element != null) {
                        output.writeMessage(IdleLogs.GOOD_HASH, element);
                    }
                }
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.changedFiles != null && this.changedFiles.length > 0) {
                for (int i = IdleLogs.HASH_MATCH_UNSPECIFIED; i < this.changedFiles.length; i += IdleLogs.GOOD_HASH) {
                    FileInfo element = this.changedFiles[i];
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(IdleLogs.GOOD_HASH, element);
                    }
                }
            }
            return size;
        }

        public DalvikCacheInfo mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case IdleLogs.HASH_MATCH_UNSPECIFIED /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        int i;
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 10);
                        if (this.changedFiles == null) {
                            i = IdleLogs.HASH_MATCH_UNSPECIFIED;
                        } else {
                            i = this.changedFiles.length;
                        }
                        FileInfo[] newArray = new FileInfo[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.changedFiles, IdleLogs.HASH_MATCH_UNSPECIFIED, newArray, IdleLogs.HASH_MATCH_UNSPECIFIED, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new FileInfo();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i += IdleLogs.GOOD_HASH;
                        }
                        newArray[i] = new FileInfo();
                        input.readMessage(newArray[i]);
                        this.changedFiles = newArray;
                        continue;
                    default:
                        if (!WireFormatNano.parseUnknownField(input, tag)) {
                            break;
                        }
                        continue;
                }
                return this;
            }
        }

        public static DalvikCacheInfo parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (DalvikCacheInfo) MessageNano.mergeFrom(new DalvikCacheInfo(), data);
        }

        public static DalvikCacheInfo parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new DalvikCacheInfo().mergeFrom(input);
        }
    }

    public static final class DeviceState extends MessageNano {
        private static volatile DeviceState[] _emptyArray;
        public int oemLocked;
        public int oemUnlockSupported;
        public String securityPatchLevel;
        public String verifiedBootState;
        public String verityMode;

        public static DeviceState[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new DeviceState[IdleLogs.HASH_MATCH_UNSPECIFIED];
                    }
                }
            }
            return _emptyArray;
        }

        public DeviceState() {
            clear();
        }

        public DeviceState clear() {
            this.verifiedBootState = BuildConfig.VERSION_NAME;
            this.verityMode = BuildConfig.VERSION_NAME;
            this.oemUnlockSupported = IdleLogs.HASH_MATCH_UNSPECIFIED;
            this.oemLocked = IdleLogs.HASH_MATCH_UNSPECIFIED;
            this.securityPatchLevel = BuildConfig.VERSION_NAME;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (!this.verifiedBootState.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(IdleLogs.GOOD_HASH, this.verifiedBootState);
            }
            if (!this.verityMode.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(IdleLogs.BAD_HASH, this.verityMode);
            }
            if (this.oemUnlockSupported != 0) {
                output.writeInt32(IdleLogs.UNKNOWN_HASH, this.oemUnlockSupported);
            }
            if (this.oemLocked != 0) {
                output.writeInt32(4, this.oemLocked);
            }
            if (!this.securityPatchLevel.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(5, this.securityPatchLevel);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (!this.verifiedBootState.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(IdleLogs.GOOD_HASH, this.verifiedBootState);
            }
            if (!this.verityMode.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(IdleLogs.BAD_HASH, this.verityMode);
            }
            if (this.oemUnlockSupported != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(IdleLogs.UNKNOWN_HASH, this.oemUnlockSupported);
            }
            if (this.oemLocked != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(4, this.oemLocked);
            }
            if (this.securityPatchLevel.equals(BuildConfig.VERSION_NAME)) {
                return size;
            }
            return size + CodedOutputByteBufferNano.computeStringSize(5, this.securityPatchLevel);
        }

        public DeviceState mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case IdleLogs.HASH_MATCH_UNSPECIFIED /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        this.verifiedBootState = input.readString();
                        continue;
                    case LogSource.GOOGLE_ANALYTICS /*18*/:
                        this.verityMode = input.readString();
                        continue;
                    case LogSource.LB_C /*24*/:
                        this.oemUnlockSupported = input.readInt32();
                        continue;
                    case LogSource.SOCIAL /*32*/:
                        this.oemLocked = input.readInt32();
                        continue;
                    case LogSource.PHOTOS /*42*/:
                        this.securityPatchLevel = input.readString();
                        continue;
                    default:
                        if (!WireFormatNano.parseUnknownField(input, tag)) {
                            break;
                        }
                        continue;
                }
                return this;
            }
        }

        public static DeviceState parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (DeviceState) MessageNano.mergeFrom(new DeviceState(), data);
        }

        public static DeviceState parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new DeviceState().mergeFrom(input);
        }
    }

    public static final class EventLog extends MessageNano {
        private static volatile EventLog[] _emptyArray;
        public AppInfo[] appInfo;
        public String data;
        public String subTag;
        public int tag;
        public long timeNanos;

        public static EventLog[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new EventLog[IdleLogs.HASH_MATCH_UNSPECIFIED];
                    }
                }
            }
            return _emptyArray;
        }

        public EventLog() {
            clear();
        }

        public EventLog clear() {
            this.tag = IdleLogs.HASH_MATCH_UNSPECIFIED;
            this.subTag = BuildConfig.VERSION_NAME;
            this.timeNanos = 0;
            this.appInfo = AppInfo.emptyArray();
            this.data = BuildConfig.VERSION_NAME;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.tag != 0) {
                output.writeInt32(IdleLogs.GOOD_HASH, this.tag);
            }
            if (!this.subTag.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(IdleLogs.BAD_HASH, this.subTag);
            }
            if (this.timeNanos != 0) {
                output.writeInt64(IdleLogs.UNKNOWN_HASH, this.timeNanos);
            }
            if (this.appInfo != null && this.appInfo.length > 0) {
                for (int i = IdleLogs.HASH_MATCH_UNSPECIFIED; i < this.appInfo.length; i += IdleLogs.GOOD_HASH) {
                    AppInfo element = this.appInfo[i];
                    if (element != null) {
                        output.writeMessage(4, element);
                    }
                }
            }
            if (!this.data.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(5, this.data);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.tag != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(IdleLogs.GOOD_HASH, this.tag);
            }
            if (!this.subTag.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(IdleLogs.BAD_HASH, this.subTag);
            }
            if (this.timeNanos != 0) {
                size += CodedOutputByteBufferNano.computeInt64Size(IdleLogs.UNKNOWN_HASH, this.timeNanos);
            }
            if (this.appInfo != null && this.appInfo.length > 0) {
                for (int i = IdleLogs.HASH_MATCH_UNSPECIFIED; i < this.appInfo.length; i += IdleLogs.GOOD_HASH) {
                    AppInfo element = this.appInfo[i];
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(4, element);
                    }
                }
            }
            if (this.data.equals(BuildConfig.VERSION_NAME)) {
                return size;
            }
            return size + CodedOutputByteBufferNano.computeStringSize(5, this.data);
        }

        public EventLog mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case IdleLogs.HASH_MATCH_UNSPECIFIED /*0*/:
                        break;
                    case Type.TAP_GET_LINK /*8*/:
                        this.tag = input.readInt32();
                        continue;
                    case LogSource.GOOGLE_ANALYTICS /*18*/:
                        this.subTag = input.readString();
                        continue;
                    case LogSource.LB_C /*24*/:
                        this.timeNanos = input.readInt64();
                        continue;
                    case LogSource.NOVA /*34*/:
                        int i;
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 34);
                        if (this.appInfo == null) {
                            i = IdleLogs.HASH_MATCH_UNSPECIFIED;
                        } else {
                            i = this.appInfo.length;
                        }
                        AppInfo[] newArray = new AppInfo[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.appInfo, IdleLogs.HASH_MATCH_UNSPECIFIED, newArray, IdleLogs.HASH_MATCH_UNSPECIFIED, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new AppInfo();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i += IdleLogs.GOOD_HASH;
                        }
                        newArray[i] = new AppInfo();
                        input.readMessage(newArray[i]);
                        this.appInfo = newArray;
                        continue;
                    case LogSource.PHOTOS /*42*/:
                        this.data = input.readString();
                        continue;
                    default:
                        if (!WireFormatNano.parseUnknownField(input, tag)) {
                            break;
                        }
                        continue;
                }
                return this;
            }
        }

        public static EventLog parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (EventLog) MessageNano.mergeFrom(new EventLog(), data);
        }

        public static EventLog parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new EventLog().mergeFrom(input);
        }
    }

    public static final class LogcatEntry extends MessageNano {
        private static volatile LogcatEntry[] _emptyArray;
        public String key;
        public LogcatValue[] value;

        public static LogcatEntry[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new LogcatEntry[IdleLogs.HASH_MATCH_UNSPECIFIED];
                    }
                }
            }
            return _emptyArray;
        }

        public LogcatEntry() {
            clear();
        }

        public LogcatEntry clear() {
            this.key = BuildConfig.VERSION_NAME;
            this.value = LogcatValue.emptyArray();
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (!this.key.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(IdleLogs.GOOD_HASH, this.key);
            }
            if (this.value != null && this.value.length > 0) {
                for (int i = IdleLogs.HASH_MATCH_UNSPECIFIED; i < this.value.length; i += IdleLogs.GOOD_HASH) {
                    LogcatValue element = this.value[i];
                    if (element != null) {
                        output.writeMessage(IdleLogs.BAD_HASH, element);
                    }
                }
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (!this.key.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(IdleLogs.GOOD_HASH, this.key);
            }
            if (this.value != null && this.value.length > 0) {
                for (int i = IdleLogs.HASH_MATCH_UNSPECIFIED; i < this.value.length; i += IdleLogs.GOOD_HASH) {
                    LogcatValue element = this.value[i];
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(IdleLogs.BAD_HASH, element);
                    }
                }
            }
            return size;
        }

        public LogcatEntry mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case IdleLogs.HASH_MATCH_UNSPECIFIED /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        this.key = input.readString();
                        continue;
                    case LogSource.GOOGLE_ANALYTICS /*18*/:
                        int i;
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 18);
                        if (this.value == null) {
                            i = IdleLogs.HASH_MATCH_UNSPECIFIED;
                        } else {
                            i = this.value.length;
                        }
                        LogcatValue[] newArray = new LogcatValue[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.value, IdleLogs.HASH_MATCH_UNSPECIFIED, newArray, IdleLogs.HASH_MATCH_UNSPECIFIED, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new LogcatValue();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i += IdleLogs.GOOD_HASH;
                        }
                        newArray[i] = new LogcatValue();
                        input.readMessage(newArray[i]);
                        this.value = newArray;
                        continue;
                    default:
                        if (!WireFormatNano.parseUnknownField(input, tag)) {
                            break;
                        }
                        continue;
                }
                return this;
            }
        }

        public static LogcatEntry parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (LogcatEntry) MessageNano.mergeFrom(new LogcatEntry(), data);
        }

        public static LogcatEntry parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new LogcatEntry().mergeFrom(input);
        }
    }

    public static final class LogcatResults extends MessageNano {
        private static volatile LogcatResults[] _emptyArray;
        public int numLogcatLines;
        public LogcatEntry[] results;

        public static LogcatResults[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new LogcatResults[IdleLogs.HASH_MATCH_UNSPECIFIED];
                    }
                }
            }
            return _emptyArray;
        }

        public LogcatResults() {
            clear();
        }

        public LogcatResults clear() {
            this.numLogcatLines = IdleLogs.HASH_MATCH_UNSPECIFIED;
            this.results = LogcatEntry.emptyArray();
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.numLogcatLines != 0) {
                output.writeInt32(IdleLogs.GOOD_HASH, this.numLogcatLines);
            }
            if (this.results != null && this.results.length > 0) {
                for (int i = IdleLogs.HASH_MATCH_UNSPECIFIED; i < this.results.length; i += IdleLogs.GOOD_HASH) {
                    LogcatEntry element = this.results[i];
                    if (element != null) {
                        output.writeMessage(IdleLogs.BAD_HASH, element);
                    }
                }
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.numLogcatLines != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(IdleLogs.GOOD_HASH, this.numLogcatLines);
            }
            if (this.results != null && this.results.length > 0) {
                for (int i = IdleLogs.HASH_MATCH_UNSPECIFIED; i < this.results.length; i += IdleLogs.GOOD_HASH) {
                    LogcatEntry element = this.results[i];
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(IdleLogs.BAD_HASH, element);
                    }
                }
            }
            return size;
        }

        public LogcatResults mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case IdleLogs.HASH_MATCH_UNSPECIFIED /*0*/:
                        break;
                    case Type.TAP_GET_LINK /*8*/:
                        this.numLogcatLines = input.readInt32();
                        continue;
                    case LogSource.GOOGLE_ANALYTICS /*18*/:
                        int i;
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 18);
                        if (this.results == null) {
                            i = IdleLogs.HASH_MATCH_UNSPECIFIED;
                        } else {
                            i = this.results.length;
                        }
                        LogcatEntry[] newArray = new LogcatEntry[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.results, IdleLogs.HASH_MATCH_UNSPECIFIED, newArray, IdleLogs.HASH_MATCH_UNSPECIFIED, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new LogcatEntry();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i += IdleLogs.GOOD_HASH;
                        }
                        newArray[i] = new LogcatEntry();
                        input.readMessage(newArray[i]);
                        this.results = newArray;
                        continue;
                    default:
                        if (!WireFormatNano.parseUnknownField(input, tag)) {
                            break;
                        }
                        continue;
                }
                return this;
            }
        }

        public static LogcatResults parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (LogcatResults) MessageNano.mergeFrom(new LogcatResults(), data);
        }

        public static LogcatResults parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new LogcatResults().mergeFrom(input);
        }
    }

    public static final class LogcatValue extends MessageNano {
        private static volatile LogcatValue[] _emptyArray;
        public AppInfo[] appInfo;
        public String line;

        public static LogcatValue[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new LogcatValue[IdleLogs.HASH_MATCH_UNSPECIFIED];
                    }
                }
            }
            return _emptyArray;
        }

        public LogcatValue() {
            clear();
        }

        public LogcatValue clear() {
            this.appInfo = AppInfo.emptyArray();
            this.line = BuildConfig.VERSION_NAME;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.appInfo != null && this.appInfo.length > 0) {
                for (int i = IdleLogs.HASH_MATCH_UNSPECIFIED; i < this.appInfo.length; i += IdleLogs.GOOD_HASH) {
                    AppInfo element = this.appInfo[i];
                    if (element != null) {
                        output.writeMessage(IdleLogs.GOOD_HASH, element);
                    }
                }
            }
            if (!this.line.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(IdleLogs.BAD_HASH, this.line);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.appInfo != null && this.appInfo.length > 0) {
                for (int i = IdleLogs.HASH_MATCH_UNSPECIFIED; i < this.appInfo.length; i += IdleLogs.GOOD_HASH) {
                    AppInfo element = this.appInfo[i];
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(IdleLogs.GOOD_HASH, element);
                    }
                }
            }
            if (this.line.equals(BuildConfig.VERSION_NAME)) {
                return size;
            }
            return size + CodedOutputByteBufferNano.computeStringSize(IdleLogs.BAD_HASH, this.line);
        }

        public LogcatValue mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case IdleLogs.HASH_MATCH_UNSPECIFIED /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        int i;
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 10);
                        if (this.appInfo == null) {
                            i = IdleLogs.HASH_MATCH_UNSPECIFIED;
                        } else {
                            i = this.appInfo.length;
                        }
                        AppInfo[] newArray = new AppInfo[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.appInfo, IdleLogs.HASH_MATCH_UNSPECIFIED, newArray, IdleLogs.HASH_MATCH_UNSPECIFIED, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new AppInfo();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i += IdleLogs.GOOD_HASH;
                        }
                        newArray[i] = new AppInfo();
                        input.readMessage(newArray[i]);
                        this.appInfo = newArray;
                        continue;
                    case LogSource.GOOGLE_ANALYTICS /*18*/:
                        this.line = input.readString();
                        continue;
                    default:
                        if (!WireFormatNano.parseUnknownField(input, tag)) {
                            break;
                        }
                        continue;
                }
                return this;
            }
        }

        public static LogcatValue parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (LogcatValue) MessageNano.mergeFrom(new LogcatValue(), data);
        }

        public static LogcatValue parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new LogcatValue().mergeFrom(input);
        }
    }

    public static final class RunSettings extends MessageNano {
        private static volatile RunSettings[] _emptyArray;
        public long durationSinceLastRunMs;
        public long executionTimeMs;
        public int numAttempts;
        public long wakeIntervalMs;

        public static RunSettings[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new RunSettings[IdleLogs.HASH_MATCH_UNSPECIFIED];
                    }
                }
            }
            return _emptyArray;
        }

        public RunSettings() {
            clear();
        }

        public RunSettings clear() {
            this.wakeIntervalMs = 0;
            this.durationSinceLastRunMs = 0;
            this.executionTimeMs = 0;
            this.numAttempts = IdleLogs.HASH_MATCH_UNSPECIFIED;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.wakeIntervalMs != 0) {
                output.writeInt64(IdleLogs.GOOD_HASH, this.wakeIntervalMs);
            }
            if (this.durationSinceLastRunMs != 0) {
                output.writeInt64(IdleLogs.BAD_HASH, this.durationSinceLastRunMs);
            }
            if (this.executionTimeMs != 0) {
                output.writeInt64(IdleLogs.UNKNOWN_HASH, this.executionTimeMs);
            }
            if (this.numAttempts != 0) {
                output.writeInt32(4, this.numAttempts);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.wakeIntervalMs != 0) {
                size += CodedOutputByteBufferNano.computeInt64Size(IdleLogs.GOOD_HASH, this.wakeIntervalMs);
            }
            if (this.durationSinceLastRunMs != 0) {
                size += CodedOutputByteBufferNano.computeInt64Size(IdleLogs.BAD_HASH, this.durationSinceLastRunMs);
            }
            if (this.executionTimeMs != 0) {
                size += CodedOutputByteBufferNano.computeInt64Size(IdleLogs.UNKNOWN_HASH, this.executionTimeMs);
            }
            if (this.numAttempts != 0) {
                return size + CodedOutputByteBufferNano.computeInt32Size(4, this.numAttempts);
            }
            return size;
        }

        public RunSettings mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case IdleLogs.HASH_MATCH_UNSPECIFIED /*0*/:
                        break;
                    case Type.TAP_GET_LINK /*8*/:
                        this.wakeIntervalMs = input.readInt64();
                        continue;
                    case LogSource.GMS_CORE_PEOPLE /*16*/:
                        this.durationSinceLastRunMs = input.readInt64();
                        continue;
                    case LogSource.LB_C /*24*/:
                        this.executionTimeMs = input.readInt64();
                        continue;
                    case LogSource.SOCIAL /*32*/:
                        this.numAttempts = input.readInt32();
                        continue;
                    default:
                        if (!WireFormatNano.parseUnknownField(input, tag)) {
                            break;
                        }
                        continue;
                }
                return this;
            }
        }

        public static RunSettings parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (RunSettings) MessageNano.mergeFrom(new RunSettings(), data);
        }

        public static RunSettings parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new RunSettings().mergeFrom(input);
        }
    }

    public static final class SetuidFileInfo extends MessageNano {
        private static volatile SetuidFileInfo[] _emptyArray;
        public boolean couldNotCheck;
        public FileInfo[] fileInfo;
        public String[] filePaths;
        public long numberOfSetuidFiles;

        public static final class FileInfo extends MessageNano {
            private static volatile FileInfo[] _emptyArray;
            public String path;
            public byte[] sha256;

            public static FileInfo[] emptyArray() {
                if (_emptyArray == null) {
                    synchronized (InternalNano.LAZY_INIT_LOCK) {
                        if (_emptyArray == null) {
                            _emptyArray = new FileInfo[IdleLogs.HASH_MATCH_UNSPECIFIED];
                        }
                    }
                }
                return _emptyArray;
            }

            public FileInfo() {
                clear();
            }

            public FileInfo clear() {
                this.path = BuildConfig.VERSION_NAME;
                this.sha256 = WireFormatNano.EMPTY_BYTES;
                this.cachedSize = -1;
                return this;
            }

            public void writeTo(CodedOutputByteBufferNano output) throws IOException {
                if (!this.path.equals(BuildConfig.VERSION_NAME)) {
                    output.writeString(IdleLogs.GOOD_HASH, this.path);
                }
                if (!Arrays.equals(this.sha256, WireFormatNano.EMPTY_BYTES)) {
                    output.writeBytes(IdleLogs.BAD_HASH, this.sha256);
                }
                super.writeTo(output);
            }

            protected int computeSerializedSize() {
                int size = super.computeSerializedSize();
                if (!this.path.equals(BuildConfig.VERSION_NAME)) {
                    size += CodedOutputByteBufferNano.computeStringSize(IdleLogs.GOOD_HASH, this.path);
                }
                if (Arrays.equals(this.sha256, WireFormatNano.EMPTY_BYTES)) {
                    return size;
                }
                return size + CodedOutputByteBufferNano.computeBytesSize((int) IdleLogs.BAD_HASH, this.sha256);
            }

            public FileInfo mergeFrom(CodedInputByteBufferNano input) throws IOException {
                while (true) {
                    int tag = input.readTag();
                    switch (tag) {
                        case IdleLogs.HASH_MATCH_UNSPECIFIED /*0*/:
                            break;
                        case Type.TAP_ABOUT /*10*/:
                            this.path = input.readString();
                            continue;
                        case LogSource.GOOGLE_ANALYTICS /*18*/:
                            this.sha256 = input.readBytes();
                            continue;
                        default:
                            if (!WireFormatNano.parseUnknownField(input, tag)) {
                                break;
                            }
                            continue;
                    }
                    return this;
                }
            }

            public static FileInfo parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
                return (FileInfo) MessageNano.mergeFrom(new FileInfo(), data);
            }

            public static FileInfo parseFrom(CodedInputByteBufferNano input) throws IOException {
                return new FileInfo().mergeFrom(input);
            }
        }

        public static SetuidFileInfo[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new SetuidFileInfo[IdleLogs.HASH_MATCH_UNSPECIFIED];
                    }
                }
            }
            return _emptyArray;
        }

        public SetuidFileInfo() {
            clear();
        }

        public SetuidFileInfo clear() {
            this.couldNotCheck = false;
            this.numberOfSetuidFiles = 0;
            this.filePaths = WireFormatNano.EMPTY_STRING_ARRAY;
            this.fileInfo = FileInfo.emptyArray();
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            int i;
            if (this.couldNotCheck) {
                output.writeBool(IdleLogs.GOOD_HASH, this.couldNotCheck);
            }
            if (this.numberOfSetuidFiles != 0) {
                output.writeInt64(IdleLogs.BAD_HASH, this.numberOfSetuidFiles);
            }
            if (this.filePaths != null && this.filePaths.length > 0) {
                for (i = IdleLogs.HASH_MATCH_UNSPECIFIED; i < this.filePaths.length; i += IdleLogs.GOOD_HASH) {
                    String element = this.filePaths[i];
                    if (element != null) {
                        output.writeString(IdleLogs.UNKNOWN_HASH, element);
                    }
                }
            }
            if (this.fileInfo != null && this.fileInfo.length > 0) {
                for (i = IdleLogs.HASH_MATCH_UNSPECIFIED; i < this.fileInfo.length; i += IdleLogs.GOOD_HASH) {
                    FileInfo element2 = this.fileInfo[i];
                    if (element2 != null) {
                        output.writeMessage(4, element2);
                    }
                }
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int i;
            int size = super.computeSerializedSize();
            if (this.couldNotCheck) {
                size += CodedOutputByteBufferNano.computeBoolSize(IdleLogs.GOOD_HASH, this.couldNotCheck);
            }
            if (this.numberOfSetuidFiles != 0) {
                size += CodedOutputByteBufferNano.computeInt64Size(IdleLogs.BAD_HASH, this.numberOfSetuidFiles);
            }
            if (this.filePaths != null && this.filePaths.length > 0) {
                int dataCount = IdleLogs.HASH_MATCH_UNSPECIFIED;
                int dataSize = IdleLogs.HASH_MATCH_UNSPECIFIED;
                for (i = IdleLogs.HASH_MATCH_UNSPECIFIED; i < this.filePaths.length; i += IdleLogs.GOOD_HASH) {
                    String element = this.filePaths[i];
                    if (element != null) {
                        dataCount += IdleLogs.GOOD_HASH;
                        dataSize += CodedOutputByteBufferNano.computeStringSizeNoTag(element);
                    }
                }
                size = (size + dataSize) + (dataCount * IdleLogs.GOOD_HASH);
            }
            if (this.fileInfo != null && this.fileInfo.length > 0) {
                for (i = IdleLogs.HASH_MATCH_UNSPECIFIED; i < this.fileInfo.length; i += IdleLogs.GOOD_HASH) {
                    FileInfo element2 = this.fileInfo[i];
                    if (element2 != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(4, element2);
                    }
                }
            }
            return size;
        }

        public SetuidFileInfo mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                int arrayLength;
                int i;
                switch (tag) {
                    case IdleLogs.HASH_MATCH_UNSPECIFIED /*0*/:
                        break;
                    case Type.TAP_GET_LINK /*8*/:
                        this.couldNotCheck = input.readBool();
                        continue;
                    case LogSource.GMS_CORE_PEOPLE /*16*/:
                        this.numberOfSetuidFiles = input.readInt64();
                        continue;
                    case LogSource.ANDROID_CAMERA /*26*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 26);
                        i = this.filePaths == null ? IdleLogs.HASH_MATCH_UNSPECIFIED : this.filePaths.length;
                        String[] newArray = new String[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.filePaths, IdleLogs.HASH_MATCH_UNSPECIFIED, newArray, IdleLogs.HASH_MATCH_UNSPECIFIED, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = input.readString();
                            input.readTag();
                            i += IdleLogs.GOOD_HASH;
                        }
                        newArray[i] = input.readString();
                        this.filePaths = newArray;
                        continue;
                    case LogSource.NOVA /*34*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 34);
                        if (this.fileInfo == null) {
                            i = IdleLogs.HASH_MATCH_UNSPECIFIED;
                        } else {
                            i = this.fileInfo.length;
                        }
                        FileInfo[] newArray2 = new FileInfo[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.fileInfo, IdleLogs.HASH_MATCH_UNSPECIFIED, newArray2, IdleLogs.HASH_MATCH_UNSPECIFIED, i);
                        }
                        while (i < newArray2.length - 1) {
                            newArray2[i] = new FileInfo();
                            input.readMessage(newArray2[i]);
                            input.readTag();
                            i += IdleLogs.GOOD_HASH;
                        }
                        newArray2[i] = new FileInfo();
                        input.readMessage(newArray2[i]);
                        this.fileInfo = newArray2;
                        continue;
                    default:
                        if (!WireFormatNano.parseUnknownField(input, tag)) {
                            break;
                        }
                        continue;
                }
                return this;
            }
        }

        public static SetuidFileInfo parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (SetuidFileInfo) MessageNano.mergeFrom(new SetuidFileInfo(), data);
        }

        public static SetuidFileInfo parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new SetuidFileInfo().mergeFrom(input);
        }
    }

    public static final class SicFileInfo extends MessageNano {
        private static volatile SicFileInfo[] _emptyArray;
        public int fileGroup;
        public int fileOwner;
        public String path;
        public int permissions;
        public String seLinuxSecurityContext;
        public byte[] sha256;
        public boolean symlink;
        public String symlinkTarget;

        public static SicFileInfo[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new SicFileInfo[IdleLogs.HASH_MATCH_UNSPECIFIED];
                    }
                }
            }
            return _emptyArray;
        }

        public SicFileInfo() {
            clear();
        }

        public SicFileInfo clear() {
            this.path = BuildConfig.VERSION_NAME;
            this.sha256 = WireFormatNano.EMPTY_BYTES;
            this.symlink = false;
            this.symlinkTarget = BuildConfig.VERSION_NAME;
            this.permissions = IdleLogs.HASH_MATCH_UNSPECIFIED;
            this.fileOwner = IdleLogs.HASH_MATCH_UNSPECIFIED;
            this.fileGroup = IdleLogs.HASH_MATCH_UNSPECIFIED;
            this.seLinuxSecurityContext = BuildConfig.VERSION_NAME;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (!this.path.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(IdleLogs.GOOD_HASH, this.path);
            }
            if (!Arrays.equals(this.sha256, WireFormatNano.EMPTY_BYTES)) {
                output.writeBytes(IdleLogs.BAD_HASH, this.sha256);
            }
            if (this.symlink) {
                output.writeBool(IdleLogs.UNKNOWN_HASH, this.symlink);
            }
            if (!this.symlinkTarget.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(4, this.symlinkTarget);
            }
            if (this.permissions != 0) {
                output.writeInt32(5, this.permissions);
            }
            if (this.fileOwner != 0) {
                output.writeInt32(6, this.fileOwner);
            }
            if (this.fileGroup != 0) {
                output.writeInt32(7, this.fileGroup);
            }
            if (!this.seLinuxSecurityContext.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(8, this.seLinuxSecurityContext);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (!this.path.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(IdleLogs.GOOD_HASH, this.path);
            }
            if (!Arrays.equals(this.sha256, WireFormatNano.EMPTY_BYTES)) {
                size += CodedOutputByteBufferNano.computeBytesSize((int) IdleLogs.BAD_HASH, this.sha256);
            }
            if (this.symlink) {
                size += CodedOutputByteBufferNano.computeBoolSize(IdleLogs.UNKNOWN_HASH, this.symlink);
            }
            if (!this.symlinkTarget.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(4, this.symlinkTarget);
            }
            if (this.permissions != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(5, this.permissions);
            }
            if (this.fileOwner != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(6, this.fileOwner);
            }
            if (this.fileGroup != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(7, this.fileGroup);
            }
            if (this.seLinuxSecurityContext.equals(BuildConfig.VERSION_NAME)) {
                return size;
            }
            return size + CodedOutputByteBufferNano.computeStringSize(8, this.seLinuxSecurityContext);
        }

        public SicFileInfo mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case IdleLogs.HASH_MATCH_UNSPECIFIED /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        this.path = input.readString();
                        continue;
                    case LogSource.GOOGLE_ANALYTICS /*18*/:
                        this.sha256 = input.readBytes();
                        continue;
                    case LogSource.LB_C /*24*/:
                        this.symlink = input.readBool();
                        continue;
                    case LogSource.NOVA /*34*/:
                        this.symlinkTarget = input.readString();
                        continue;
                    case LogSource.COPRESENCE /*40*/:
                        this.permissions = input.readInt32();
                        continue;
                    case LogSource.BACKDROP /*48*/:
                        this.fileOwner = input.readInt32();
                        continue;
                    case LogSource.DOCS /*56*/:
                        this.fileGroup = input.readInt32();
                        continue;
                    case LogSource.WIFI_ASSISTANT /*66*/:
                        this.seLinuxSecurityContext = input.readString();
                        continue;
                    default:
                        if (!WireFormatNano.parseUnknownField(input, tag)) {
                            break;
                        }
                        continue;
                }
                return this;
            }
        }

        public static SicFileInfo parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (SicFileInfo) MessageNano.mergeFrom(new SicFileInfo(), data);
        }

        public static SicFileInfo parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new SicFileInfo().mergeFrom(input);
        }
    }

    public static final class SnetIdleLog extends MessageNano {
        private static volatile SnetIdleLog[] _emptyArray;
        public String buildFingerprint;
        public AppInfo buildInfo;
        public DalvikCacheInfo dalvikCacheInfo;
        public String debugStatus;
        public DeviceState deviceState;
        public EventLog[] events;
        public byte[] featuresBitField;
        public boolean gmsCoreUuidUsed;
        public boolean isSidewinderDevice;
        public String[] jarExceptions;
        public long jarVersion;
        public LogcatResults logcatResults;
        public RunSettings runSettings;
        public SetuidFileInfo setuidFileInfo;
        public String sharedUuid;
        public String signalTagsWhitelist;
        public SystemCaCertStoreInfo[] systemCaCertStoreInfo;
        public SystemPartitionFileInfo systemPartitionFileInfo;
        public String uuid;

        public static SnetIdleLog[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new SnetIdleLog[IdleLogs.HASH_MATCH_UNSPECIFIED];
                    }
                }
            }
            return _emptyArray;
        }

        public SnetIdleLog() {
            clear();
        }

        public SnetIdleLog clear() {
            this.jarVersion = 0;
            this.gmsCoreUuidUsed = false;
            this.sharedUuid = BuildConfig.VERSION_NAME;
            this.uuid = BuildConfig.VERSION_NAME;
            this.jarExceptions = WireFormatNano.EMPTY_STRING_ARRAY;
            this.featuresBitField = WireFormatNano.EMPTY_BYTES;
            this.debugStatus = BuildConfig.VERSION_NAME;
            this.runSettings = null;
            this.signalTagsWhitelist = BuildConfig.VERSION_NAME;
            this.buildFingerprint = BuildConfig.VERSION_NAME;
            this.buildInfo = null;
            this.isSidewinderDevice = false;
            this.systemPartitionFileInfo = null;
            this.systemCaCertStoreInfo = SystemCaCertStoreInfo.emptyArray();
            this.setuidFileInfo = null;
            this.logcatResults = null;
            this.events = EventLog.emptyArray();
            this.dalvikCacheInfo = null;
            this.deviceState = null;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            int i;
            if (this.jarVersion != 0) {
                output.writeInt64(IdleLogs.GOOD_HASH, this.jarVersion);
            }
            if (this.gmsCoreUuidUsed) {
                output.writeBool(IdleLogs.BAD_HASH, this.gmsCoreUuidUsed);
            }
            if (!this.sharedUuid.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(IdleLogs.UNKNOWN_HASH, this.sharedUuid);
            }
            if (!this.uuid.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(4, this.uuid);
            }
            if (this.jarExceptions != null && this.jarExceptions.length > 0) {
                for (i = IdleLogs.HASH_MATCH_UNSPECIFIED; i < this.jarExceptions.length; i += IdleLogs.GOOD_HASH) {
                    String element = this.jarExceptions[i];
                    if (element != null) {
                        output.writeString(5, element);
                    }
                }
            }
            if (!Arrays.equals(this.featuresBitField, WireFormatNano.EMPTY_BYTES)) {
                output.writeBytes(6, this.featuresBitField);
            }
            if (!this.debugStatus.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(7, this.debugStatus);
            }
            if (this.runSettings != null) {
                output.writeMessage(8, this.runSettings);
            }
            if (!this.signalTagsWhitelist.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(9, this.signalTagsWhitelist);
            }
            if (!this.buildFingerprint.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(10, this.buildFingerprint);
            }
            if (this.systemPartitionFileInfo != null) {
                output.writeMessage(11, this.systemPartitionFileInfo);
            }
            if (this.systemCaCertStoreInfo != null && this.systemCaCertStoreInfo.length > 0) {
                for (i = IdleLogs.HASH_MATCH_UNSPECIFIED; i < this.systemCaCertStoreInfo.length; i += IdleLogs.GOOD_HASH) {
                    SystemCaCertStoreInfo element2 = this.systemCaCertStoreInfo[i];
                    if (element2 != null) {
                        output.writeMessage(12, element2);
                    }
                }
            }
            if (this.setuidFileInfo != null) {
                output.writeMessage(13, this.setuidFileInfo);
            }
            if (this.isSidewinderDevice) {
                output.writeBool(14, this.isSidewinderDevice);
            }
            if (this.logcatResults != null) {
                output.writeMessage(15, this.logcatResults);
            }
            if (this.events != null && this.events.length > 0) {
                for (i = IdleLogs.HASH_MATCH_UNSPECIFIED; i < this.events.length; i += IdleLogs.GOOD_HASH) {
                    EventLog element3 = this.events[i];
                    if (element3 != null) {
                        output.writeMessage(16, element3);
                    }
                }
            }
            if (this.buildInfo != null) {
                output.writeMessage(17, this.buildInfo);
            }
            if (this.dalvikCacheInfo != null) {
                output.writeMessage(18, this.dalvikCacheInfo);
            }
            if (this.deviceState != null) {
                output.writeMessage(19, this.deviceState);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int i;
            int size = super.computeSerializedSize();
            if (this.jarVersion != 0) {
                size += CodedOutputByteBufferNano.computeInt64Size(IdleLogs.GOOD_HASH, this.jarVersion);
            }
            if (this.gmsCoreUuidUsed) {
                size += CodedOutputByteBufferNano.computeBoolSize(IdleLogs.BAD_HASH, this.gmsCoreUuidUsed);
            }
            if (!this.sharedUuid.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(IdleLogs.UNKNOWN_HASH, this.sharedUuid);
            }
            if (!this.uuid.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(4, this.uuid);
            }
            if (this.jarExceptions != null && this.jarExceptions.length > 0) {
                int dataCount = IdleLogs.HASH_MATCH_UNSPECIFIED;
                int dataSize = IdleLogs.HASH_MATCH_UNSPECIFIED;
                for (i = IdleLogs.HASH_MATCH_UNSPECIFIED; i < this.jarExceptions.length; i += IdleLogs.GOOD_HASH) {
                    String element = this.jarExceptions[i];
                    if (element != null) {
                        dataCount += IdleLogs.GOOD_HASH;
                        dataSize += CodedOutputByteBufferNano.computeStringSizeNoTag(element);
                    }
                }
                size = (size + dataSize) + (dataCount * IdleLogs.GOOD_HASH);
            }
            if (!Arrays.equals(this.featuresBitField, WireFormatNano.EMPTY_BYTES)) {
                size += CodedOutputByteBufferNano.computeBytesSize(6, this.featuresBitField);
            }
            if (!this.debugStatus.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(7, this.debugStatus);
            }
            if (this.runSettings != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(8, this.runSettings);
            }
            if (!this.signalTagsWhitelist.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(9, this.signalTagsWhitelist);
            }
            if (!this.buildFingerprint.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(10, this.buildFingerprint);
            }
            if (this.systemPartitionFileInfo != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(11, this.systemPartitionFileInfo);
            }
            if (this.systemCaCertStoreInfo != null && this.systemCaCertStoreInfo.length > 0) {
                for (i = IdleLogs.HASH_MATCH_UNSPECIFIED; i < this.systemCaCertStoreInfo.length; i += IdleLogs.GOOD_HASH) {
                    SystemCaCertStoreInfo element2 = this.systemCaCertStoreInfo[i];
                    if (element2 != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(12, element2);
                    }
                }
            }
            if (this.setuidFileInfo != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(13, this.setuidFileInfo);
            }
            if (this.isSidewinderDevice) {
                size += CodedOutputByteBufferNano.computeBoolSize(14, this.isSidewinderDevice);
            }
            if (this.logcatResults != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(15, this.logcatResults);
            }
            if (this.events != null && this.events.length > 0) {
                for (i = IdleLogs.HASH_MATCH_UNSPECIFIED; i < this.events.length; i += IdleLogs.GOOD_HASH) {
                    EventLog element3 = this.events[i];
                    if (element3 != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(16, element3);
                    }
                }
            }
            if (this.buildInfo != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(17, this.buildInfo);
            }
            if (this.dalvikCacheInfo != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(18, this.dalvikCacheInfo);
            }
            if (this.deviceState != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(19, this.deviceState);
            }
            return size;
        }

        public SnetIdleLog mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                int arrayLength;
                int i;
                switch (tag) {
                    case IdleLogs.HASH_MATCH_UNSPECIFIED /*0*/:
                        break;
                    case Type.TAP_GET_LINK /*8*/:
                        this.jarVersion = input.readInt64();
                        continue;
                    case LogSource.GMS_CORE_PEOPLE /*16*/:
                        this.gmsCoreUuidUsed = input.readBool();
                        continue;
                    case LogSource.ANDROID_CAMERA /*26*/:
                        this.sharedUuid = input.readString();
                        continue;
                    case LogSource.NOVA /*34*/:
                        this.uuid = input.readString();
                        continue;
                    case LogSource.PHOTOS /*42*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 42);
                        i = this.jarExceptions == null ? IdleLogs.HASH_MATCH_UNSPECIFIED : this.jarExceptions.length;
                        String[] newArray = new String[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.jarExceptions, IdleLogs.HASH_MATCH_UNSPECIFIED, newArray, IdleLogs.HASH_MATCH_UNSPECIFIED, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = input.readString();
                            input.readTag();
                            i += IdleLogs.GOOD_HASH;
                        }
                        newArray[i] = input.readString();
                        this.jarExceptions = newArray;
                        continue;
                    case LogSource.HANGOUT_LOG_REQUEST /*50*/:
                        this.featuresBitField = input.readBytes();
                        continue;
                    case LogSource.SLIDES /*58*/:
                        this.debugStatus = input.readString();
                        continue;
                    case LogSource.WIFI_ASSISTANT /*66*/:
                        if (this.runSettings == null) {
                            this.runSettings = new RunSettings();
                        }
                        input.readMessage(this.runSettings);
                        continue;
                    case LogSource.MOVIES /*74*/:
                        this.signalTagsWhitelist = input.readString();
                        continue;
                    case LogSource.GOOGLE_EXPRESS /*82*/:
                        this.buildFingerprint = input.readString();
                        continue;
                    case LogSource.TRON_COUNTERS /*90*/:
                        if (this.systemPartitionFileInfo == null) {
                            this.systemPartitionFileInfo = new SystemPartitionFileInfo();
                        }
                        input.readMessage(this.systemPartitionFileInfo);
                        continue;
                    case LogSource.TACHYON_LOG_REQUEST /*98*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 98);
                        if (this.systemCaCertStoreInfo == null) {
                            i = IdleLogs.HASH_MATCH_UNSPECIFIED;
                        } else {
                            i = this.systemCaCertStoreInfo.length;
                        }
                        SystemCaCertStoreInfo[] newArray2 = new SystemCaCertStoreInfo[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.systemCaCertStoreInfo, IdleLogs.HASH_MATCH_UNSPECIFIED, newArray2, IdleLogs.HASH_MATCH_UNSPECIFIED, i);
                        }
                        while (i < newArray2.length - 1) {
                            newArray2[i] = new SystemCaCertStoreInfo();
                            input.readMessage(newArray2[i]);
                            input.readTag();
                            i += IdleLogs.GOOD_HASH;
                        }
                        newArray2[i] = new SystemCaCertStoreInfo();
                        input.readMessage(newArray2[i]);
                        this.systemCaCertStoreInfo = newArray2;
                        continue;
                    case LogSource.ADSHIELD /*106*/:
                        if (this.setuidFileInfo == null) {
                            this.setuidFileInfo = new SetuidFileInfo();
                        }
                        input.readMessage(this.setuidFileInfo);
                        continue;
                    case LogSource.ENDER /*112*/:
                        this.isSidewinderDevice = input.readBool();
                        continue;
                    case LogSource.ANDROID_SNET_GCORE /*122*/:
                        if (this.logcatResults == null) {
                            this.logcatResults = new LogcatResults();
                        }
                        input.readMessage(this.logcatResults);
                        continue;
                    case LogSource.CHROMECAST_APP_LOG /*130*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, LogSource.CHROMECAST_APP_LOG);
                        if (this.events == null) {
                            i = IdleLogs.HASH_MATCH_UNSPECIFIED;
                        } else {
                            i = this.events.length;
                        }
                        EventLog[] newArray3 = new EventLog[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.events, IdleLogs.HASH_MATCH_UNSPECIFIED, newArray3, IdleLogs.HASH_MATCH_UNSPECIFIED, i);
                        }
                        while (i < newArray3.length - 1) {
                            newArray3[i] = new EventLog();
                            input.readMessage(newArray3[i]);
                            input.readTag();
                            i += IdleLogs.GOOD_HASH;
                        }
                        newArray3[i] = new EventLog();
                        input.readMessage(newArray3[i]);
                        this.events = newArray3;
                        continue;
                    case LogSource.PANCETTA_MOBILE_HOST /*138*/:
                        if (this.buildInfo == null) {
                            this.buildInfo = new AppInfo();
                        }
                        input.readMessage(this.buildInfo);
                        continue;
                    case 146:
                        if (this.dalvikCacheInfo == null) {
                            this.dalvikCacheInfo = new DalvikCacheInfo();
                        }
                        input.readMessage(this.dalvikCacheInfo);
                        continue;
                    case 154:
                        if (this.deviceState == null) {
                            this.deviceState = new DeviceState();
                        }
                        input.readMessage(this.deviceState);
                        continue;
                    default:
                        if (!WireFormatNano.parseUnknownField(input, tag)) {
                            break;
                        }
                        continue;
                }
                return this;
            }
        }

        public static SnetIdleLog parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (SnetIdleLog) MessageNano.mergeFrom(new SnetIdleLog(), data);
        }

        public static SnetIdleLog parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new SnetIdleLog().mergeFrom(input);
        }
    }

    public static final class SystemCaCertStoreInfo extends MessageNano {
        private static volatile SystemCaCertStoreInfo[] _emptyArray;
        public String certOid;
        public byte[] certText;
        public byte[] certTextSha256;
        public byte[] derEncodedCert;
        public byte[] derEncodedCertSha256;
        public String issuerDn;
        public String subjectDn;
        public byte[] subjectPublicKeyInfo;
        public byte[] subjectPublicKeyInfoSha256;

        public static SystemCaCertStoreInfo[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new SystemCaCertStoreInfo[IdleLogs.HASH_MATCH_UNSPECIFIED];
                    }
                }
            }
            return _emptyArray;
        }

        public SystemCaCertStoreInfo() {
            clear();
        }

        public SystemCaCertStoreInfo clear() {
            this.subjectDn = BuildConfig.VERSION_NAME;
            this.issuerDn = BuildConfig.VERSION_NAME;
            this.certTextSha256 = WireFormatNano.EMPTY_BYTES;
            this.certText = WireFormatNano.EMPTY_BYTES;
            this.derEncodedCertSha256 = WireFormatNano.EMPTY_BYTES;
            this.derEncodedCert = WireFormatNano.EMPTY_BYTES;
            this.subjectPublicKeyInfo = WireFormatNano.EMPTY_BYTES;
            this.certOid = BuildConfig.VERSION_NAME;
            this.subjectPublicKeyInfoSha256 = WireFormatNano.EMPTY_BYTES;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (!this.subjectDn.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(IdleLogs.GOOD_HASH, this.subjectDn);
            }
            if (!this.issuerDn.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(IdleLogs.BAD_HASH, this.issuerDn);
            }
            if (!Arrays.equals(this.certTextSha256, WireFormatNano.EMPTY_BYTES)) {
                output.writeBytes(IdleLogs.UNKNOWN_HASH, this.certTextSha256);
            }
            if (!Arrays.equals(this.certText, WireFormatNano.EMPTY_BYTES)) {
                output.writeBytes(4, this.certText);
            }
            if (!Arrays.equals(this.derEncodedCertSha256, WireFormatNano.EMPTY_BYTES)) {
                output.writeBytes(5, this.derEncodedCertSha256);
            }
            if (!Arrays.equals(this.derEncodedCert, WireFormatNano.EMPTY_BYTES)) {
                output.writeBytes(6, this.derEncodedCert);
            }
            if (!Arrays.equals(this.subjectPublicKeyInfo, WireFormatNano.EMPTY_BYTES)) {
                output.writeBytes(7, this.subjectPublicKeyInfo);
            }
            if (!this.certOid.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(8, this.certOid);
            }
            if (!Arrays.equals(this.subjectPublicKeyInfoSha256, WireFormatNano.EMPTY_BYTES)) {
                output.writeBytes(9, this.subjectPublicKeyInfoSha256);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (!this.subjectDn.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(IdleLogs.GOOD_HASH, this.subjectDn);
            }
            if (!this.issuerDn.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(IdleLogs.BAD_HASH, this.issuerDn);
            }
            if (!Arrays.equals(this.certTextSha256, WireFormatNano.EMPTY_BYTES)) {
                size += CodedOutputByteBufferNano.computeBytesSize((int) IdleLogs.UNKNOWN_HASH, this.certTextSha256);
            }
            if (!Arrays.equals(this.certText, WireFormatNano.EMPTY_BYTES)) {
                size += CodedOutputByteBufferNano.computeBytesSize(4, this.certText);
            }
            if (!Arrays.equals(this.derEncodedCertSha256, WireFormatNano.EMPTY_BYTES)) {
                size += CodedOutputByteBufferNano.computeBytesSize(5, this.derEncodedCertSha256);
            }
            if (!Arrays.equals(this.derEncodedCert, WireFormatNano.EMPTY_BYTES)) {
                size += CodedOutputByteBufferNano.computeBytesSize(6, this.derEncodedCert);
            }
            if (!Arrays.equals(this.subjectPublicKeyInfo, WireFormatNano.EMPTY_BYTES)) {
                size += CodedOutputByteBufferNano.computeBytesSize(7, this.subjectPublicKeyInfo);
            }
            if (!this.certOid.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(8, this.certOid);
            }
            if (Arrays.equals(this.subjectPublicKeyInfoSha256, WireFormatNano.EMPTY_BYTES)) {
                return size;
            }
            return size + CodedOutputByteBufferNano.computeBytesSize(9, this.subjectPublicKeyInfoSha256);
        }

        public SystemCaCertStoreInfo mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case IdleLogs.HASH_MATCH_UNSPECIFIED /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        this.subjectDn = input.readString();
                        continue;
                    case LogSource.GOOGLE_ANALYTICS /*18*/:
                        this.issuerDn = input.readString();
                        continue;
                    case LogSource.ANDROID_CAMERA /*26*/:
                        this.certTextSha256 = input.readBytes();
                        continue;
                    case LogSource.NOVA /*34*/:
                        this.certText = input.readBytes();
                        continue;
                    case LogSource.PHOTOS /*42*/:
                        this.derEncodedCertSha256 = input.readBytes();
                        continue;
                    case LogSource.HANGOUT_LOG_REQUEST /*50*/:
                        this.derEncodedCert = input.readBytes();
                        continue;
                    case LogSource.SLIDES /*58*/:
                        this.subjectPublicKeyInfo = input.readBytes();
                        continue;
                    case LogSource.WIFI_ASSISTANT /*66*/:
                        this.certOid = input.readString();
                        continue;
                    case LogSource.MOVIES /*74*/:
                        this.subjectPublicKeyInfoSha256 = input.readBytes();
                        continue;
                    default:
                        if (!WireFormatNano.parseUnknownField(input, tag)) {
                            break;
                        }
                        continue;
                }
                return this;
            }
        }

        public static SystemCaCertStoreInfo parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (SystemCaCertStoreInfo) MessageNano.mergeFrom(new SystemCaCertStoreInfo(), data);
        }

        public static SystemCaCertStoreInfo parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new SystemCaCertStoreInfo().mergeFrom(input);
        }
    }

    public static final class SystemCaStoreWhitelist extends MessageNano {
        private static volatile SystemCaStoreWhitelist[] _emptyArray;
        public byte[][] subjectPublicKeyInfoSha256;

        public static SystemCaStoreWhitelist[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new SystemCaStoreWhitelist[IdleLogs.HASH_MATCH_UNSPECIFIED];
                    }
                }
            }
            return _emptyArray;
        }

        public SystemCaStoreWhitelist() {
            clear();
        }

        public SystemCaStoreWhitelist clear() {
            this.subjectPublicKeyInfoSha256 = WireFormatNano.EMPTY_BYTES_ARRAY;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.subjectPublicKeyInfoSha256 != null && this.subjectPublicKeyInfoSha256.length > 0) {
                for (int i = IdleLogs.HASH_MATCH_UNSPECIFIED; i < this.subjectPublicKeyInfoSha256.length; i += IdleLogs.GOOD_HASH) {
                    byte[] element = this.subjectPublicKeyInfoSha256[i];
                    if (element != null) {
                        output.writeBytes(IdleLogs.GOOD_HASH, element);
                    }
                }
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.subjectPublicKeyInfoSha256 == null || this.subjectPublicKeyInfoSha256.length <= 0) {
                return size;
            }
            int dataCount = IdleLogs.HASH_MATCH_UNSPECIFIED;
            int dataSize = IdleLogs.HASH_MATCH_UNSPECIFIED;
            for (int i = IdleLogs.HASH_MATCH_UNSPECIFIED; i < this.subjectPublicKeyInfoSha256.length; i += IdleLogs.GOOD_HASH) {
                byte[] element = this.subjectPublicKeyInfoSha256[i];
                if (element != null) {
                    dataCount += IdleLogs.GOOD_HASH;
                    dataSize += CodedOutputByteBufferNano.computeBytesSizeNoTag(element);
                }
            }
            return (size + dataSize) + (dataCount * IdleLogs.GOOD_HASH);
        }

        public SystemCaStoreWhitelist mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case IdleLogs.HASH_MATCH_UNSPECIFIED /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 10);
                        int i = this.subjectPublicKeyInfoSha256 == null ? IdleLogs.HASH_MATCH_UNSPECIFIED : this.subjectPublicKeyInfoSha256.length;
                        byte[][] newArray = new byte[(i + arrayLength)][];
                        if (i != 0) {
                            System.arraycopy(this.subjectPublicKeyInfoSha256, IdleLogs.HASH_MATCH_UNSPECIFIED, newArray, IdleLogs.HASH_MATCH_UNSPECIFIED, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = input.readBytes();
                            input.readTag();
                            i += IdleLogs.GOOD_HASH;
                        }
                        newArray[i] = input.readBytes();
                        this.subjectPublicKeyInfoSha256 = newArray;
                        continue;
                    default:
                        if (!WireFormatNano.parseUnknownField(input, tag)) {
                            break;
                        }
                        continue;
                }
                return this;
            }
        }

        public static SystemCaStoreWhitelist parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (SystemCaStoreWhitelist) MessageNano.mergeFrom(new SystemCaStoreWhitelist(), data);
        }

        public static SystemCaStoreWhitelist parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new SystemCaStoreWhitelist().mergeFrom(input);
        }
    }

    public static final class SystemIntegrityFileToUpload extends MessageNano {
        private static volatile SystemIntegrityFileToUpload[] _emptyArray;
        public String filePath;
        public byte[] hash;

        public static SystemIntegrityFileToUpload[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new SystemIntegrityFileToUpload[IdleLogs.HASH_MATCH_UNSPECIFIED];
                    }
                }
            }
            return _emptyArray;
        }

        public SystemIntegrityFileToUpload() {
            clear();
        }

        public SystemIntegrityFileToUpload clear() {
            this.filePath = BuildConfig.VERSION_NAME;
            this.hash = WireFormatNano.EMPTY_BYTES;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (!this.filePath.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(IdleLogs.GOOD_HASH, this.filePath);
            }
            if (!Arrays.equals(this.hash, WireFormatNano.EMPTY_BYTES)) {
                output.writeBytes(IdleLogs.BAD_HASH, this.hash);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (!this.filePath.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(IdleLogs.GOOD_HASH, this.filePath);
            }
            if (Arrays.equals(this.hash, WireFormatNano.EMPTY_BYTES)) {
                return size;
            }
            return size + CodedOutputByteBufferNano.computeBytesSize((int) IdleLogs.BAD_HASH, this.hash);
        }

        public SystemIntegrityFileToUpload mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case IdleLogs.HASH_MATCH_UNSPECIFIED /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        this.filePath = input.readString();
                        continue;
                    case LogSource.GOOGLE_ANALYTICS /*18*/:
                        this.hash = input.readBytes();
                        continue;
                    default:
                        if (!WireFormatNano.parseUnknownField(input, tag)) {
                            break;
                        }
                        continue;
                }
                return this;
            }
        }

        public static SystemIntegrityFileToUpload parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (SystemIntegrityFileToUpload) MessageNano.mergeFrom(new SystemIntegrityFileToUpload(), data);
        }

        public static SystemIntegrityFileToUpload parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new SystemIntegrityFileToUpload().mergeFrom(input);
        }
    }

    public static final class SystemIntegrityRequest extends MessageNano {
        private static volatile SystemIntegrityRequest[] _emptyArray;
        public String buildString;
        public SicFileInfo[] nodes;
        public byte[] sessionToken;
        public byte[] topLevelHash;

        public static SystemIntegrityRequest[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new SystemIntegrityRequest[IdleLogs.HASH_MATCH_UNSPECIFIED];
                    }
                }
            }
            return _emptyArray;
        }

        public SystemIntegrityRequest() {
            clear();
        }

        public SystemIntegrityRequest clear() {
            this.buildString = BuildConfig.VERSION_NAME;
            this.topLevelHash = WireFormatNano.EMPTY_BYTES;
            this.sessionToken = WireFormatNano.EMPTY_BYTES;
            this.nodes = SicFileInfo.emptyArray();
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (!this.buildString.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(IdleLogs.GOOD_HASH, this.buildString);
            }
            if (!Arrays.equals(this.topLevelHash, WireFormatNano.EMPTY_BYTES)) {
                output.writeBytes(IdleLogs.BAD_HASH, this.topLevelHash);
            }
            if (!Arrays.equals(this.sessionToken, WireFormatNano.EMPTY_BYTES)) {
                output.writeBytes(IdleLogs.UNKNOWN_HASH, this.sessionToken);
            }
            if (this.nodes != null && this.nodes.length > 0) {
                for (int i = IdleLogs.HASH_MATCH_UNSPECIFIED; i < this.nodes.length; i += IdleLogs.GOOD_HASH) {
                    SicFileInfo element = this.nodes[i];
                    if (element != null) {
                        output.writeMessage(4, element);
                    }
                }
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (!this.buildString.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(IdleLogs.GOOD_HASH, this.buildString);
            }
            if (!Arrays.equals(this.topLevelHash, WireFormatNano.EMPTY_BYTES)) {
                size += CodedOutputByteBufferNano.computeBytesSize((int) IdleLogs.BAD_HASH, this.topLevelHash);
            }
            if (!Arrays.equals(this.sessionToken, WireFormatNano.EMPTY_BYTES)) {
                size += CodedOutputByteBufferNano.computeBytesSize((int) IdleLogs.UNKNOWN_HASH, this.sessionToken);
            }
            if (this.nodes != null && this.nodes.length > 0) {
                for (int i = IdleLogs.HASH_MATCH_UNSPECIFIED; i < this.nodes.length; i += IdleLogs.GOOD_HASH) {
                    SicFileInfo element = this.nodes[i];
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(4, element);
                    }
                }
            }
            return size;
        }

        public SystemIntegrityRequest mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case IdleLogs.HASH_MATCH_UNSPECIFIED /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        this.buildString = input.readString();
                        continue;
                    case LogSource.GOOGLE_ANALYTICS /*18*/:
                        this.topLevelHash = input.readBytes();
                        continue;
                    case LogSource.ANDROID_CAMERA /*26*/:
                        this.sessionToken = input.readBytes();
                        continue;
                    case LogSource.NOVA /*34*/:
                        int i;
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 34);
                        if (this.nodes == null) {
                            i = IdleLogs.HASH_MATCH_UNSPECIFIED;
                        } else {
                            i = this.nodes.length;
                        }
                        SicFileInfo[] newArray = new SicFileInfo[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.nodes, IdleLogs.HASH_MATCH_UNSPECIFIED, newArray, IdleLogs.HASH_MATCH_UNSPECIFIED, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new SicFileInfo();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i += IdleLogs.GOOD_HASH;
                        }
                        newArray[i] = new SicFileInfo();
                        input.readMessage(newArray[i]);
                        this.nodes = newArray;
                        continue;
                    default:
                        if (!WireFormatNano.parseUnknownField(input, tag)) {
                            break;
                        }
                        continue;
                }
                return this;
            }
        }

        public static SystemIntegrityRequest parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (SystemIntegrityRequest) MessageNano.mergeFrom(new SystemIntegrityRequest(), data);
        }

        public static SystemIntegrityRequest parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new SystemIntegrityRequest().mergeFrom(input);
        }
    }

    public static final class SystemIntegrityResponse extends MessageNano {
        private static volatile SystemIntegrityResponse[] _emptyArray;
        public SystemIntegrityFileToUpload[] filesToUpload;
        public int[] hashMatches;
        public boolean newBuildString;
        public byte[] sessionToken;

        public static SystemIntegrityResponse[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new SystemIntegrityResponse[IdleLogs.HASH_MATCH_UNSPECIFIED];
                    }
                }
            }
            return _emptyArray;
        }

        public SystemIntegrityResponse() {
            clear();
        }

        public SystemIntegrityResponse clear() {
            this.sessionToken = WireFormatNano.EMPTY_BYTES;
            this.newBuildString = false;
            this.hashMatches = WireFormatNano.EMPTY_INT_ARRAY;
            this.filesToUpload = SystemIntegrityFileToUpload.emptyArray();
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            int i;
            if (!Arrays.equals(this.sessionToken, WireFormatNano.EMPTY_BYTES)) {
                output.writeBytes(IdleLogs.GOOD_HASH, this.sessionToken);
            }
            if (this.newBuildString) {
                output.writeBool(IdleLogs.BAD_HASH, this.newBuildString);
            }
            if (this.hashMatches != null && this.hashMatches.length > 0) {
                for (i = IdleLogs.HASH_MATCH_UNSPECIFIED; i < this.hashMatches.length; i += IdleLogs.GOOD_HASH) {
                    output.writeInt32(IdleLogs.UNKNOWN_HASH, this.hashMatches[i]);
                }
            }
            if (this.filesToUpload != null && this.filesToUpload.length > 0) {
                for (i = IdleLogs.HASH_MATCH_UNSPECIFIED; i < this.filesToUpload.length; i += IdleLogs.GOOD_HASH) {
                    SystemIntegrityFileToUpload element = this.filesToUpload[i];
                    if (element != null) {
                        output.writeMessage(4, element);
                    }
                }
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int i;
            int size = super.computeSerializedSize();
            if (!Arrays.equals(this.sessionToken, WireFormatNano.EMPTY_BYTES)) {
                size += CodedOutputByteBufferNano.computeBytesSize((int) IdleLogs.GOOD_HASH, this.sessionToken);
            }
            if (this.newBuildString) {
                size += CodedOutputByteBufferNano.computeBoolSize(IdleLogs.BAD_HASH, this.newBuildString);
            }
            if (this.hashMatches != null && this.hashMatches.length > 0) {
                int dataSize = IdleLogs.HASH_MATCH_UNSPECIFIED;
                for (i = IdleLogs.HASH_MATCH_UNSPECIFIED; i < this.hashMatches.length; i += IdleLogs.GOOD_HASH) {
                    dataSize += CodedOutputByteBufferNano.computeInt32SizeNoTag(this.hashMatches[i]);
                }
                size = (size + dataSize) + (this.hashMatches.length * IdleLogs.GOOD_HASH);
            }
            if (this.filesToUpload != null && this.filesToUpload.length > 0) {
                for (i = IdleLogs.HASH_MATCH_UNSPECIFIED; i < this.filesToUpload.length; i += IdleLogs.GOOD_HASH) {
                    SystemIntegrityFileToUpload element = this.filesToUpload[i];
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(4, element);
                    }
                }
            }
            return size;
        }

        public SystemIntegrityResponse mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                int i;
                int value;
                int[] newArray;
                int arrayLength;
                switch (tag) {
                    case IdleLogs.HASH_MATCH_UNSPECIFIED /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        this.sessionToken = input.readBytes();
                        continue;
                    case LogSource.GMS_CORE_PEOPLE /*16*/:
                        this.newBuildString = input.readBool();
                        continue;
                    case LogSource.LB_C /*24*/:
                        int length = WireFormatNano.getRepeatedFieldArrayLength(input, 24);
                        int[] validValues = new int[length];
                        i = IdleLogs.HASH_MATCH_UNSPECIFIED;
                        int validCount = IdleLogs.HASH_MATCH_UNSPECIFIED;
                        while (i < length) {
                            int validCount2;
                            if (i != 0) {
                                input.readTag();
                            }
                            value = input.readInt32();
                            switch (value) {
                                case IdleLogs.HASH_MATCH_UNSPECIFIED /*0*/:
                                case IdleLogs.GOOD_HASH /*1*/:
                                case IdleLogs.BAD_HASH /*2*/:
                                case IdleLogs.UNKNOWN_HASH /*3*/:
                                    validCount2 = validCount + IdleLogs.GOOD_HASH;
                                    validValues[validCount] = value;
                                    break;
                                default:
                                    validCount2 = validCount;
                                    break;
                            }
                            i += IdleLogs.GOOD_HASH;
                            validCount = validCount2;
                        }
                        if (validCount != 0) {
                            i = this.hashMatches == null ? IdleLogs.HASH_MATCH_UNSPECIFIED : this.hashMatches.length;
                            if (i != 0 || validCount != validValues.length) {
                                newArray = new int[(i + validCount)];
                                if (i != 0) {
                                    System.arraycopy(this.hashMatches, IdleLogs.HASH_MATCH_UNSPECIFIED, newArray, IdleLogs.HASH_MATCH_UNSPECIFIED, i);
                                }
                                System.arraycopy(validValues, IdleLogs.HASH_MATCH_UNSPECIFIED, newArray, i, validCount);
                                this.hashMatches = newArray;
                                break;
                            }
                            this.hashMatches = validValues;
                            break;
                        }
                        continue;
                    case LogSource.ANDROID_CAMERA /*26*/:
                        int limit = input.pushLimit(input.readRawVarint32());
                        arrayLength = IdleLogs.HASH_MATCH_UNSPECIFIED;
                        int startPos = input.getPosition();
                        while (input.getBytesUntilLimit() > 0) {
                            switch (input.readInt32()) {
                                case IdleLogs.HASH_MATCH_UNSPECIFIED /*0*/:
                                case IdleLogs.GOOD_HASH /*1*/:
                                case IdleLogs.BAD_HASH /*2*/:
                                case IdleLogs.UNKNOWN_HASH /*3*/:
                                    arrayLength += IdleLogs.GOOD_HASH;
                                    break;
                                default:
                                    break;
                            }
                        }
                        if (arrayLength != 0) {
                            input.rewindToPosition(startPos);
                            if (this.hashMatches == null) {
                                i = IdleLogs.HASH_MATCH_UNSPECIFIED;
                            } else {
                                i = this.hashMatches.length;
                            }
                            newArray = new int[(i + arrayLength)];
                            if (i != 0) {
                                System.arraycopy(this.hashMatches, IdleLogs.HASH_MATCH_UNSPECIFIED, newArray, IdleLogs.HASH_MATCH_UNSPECIFIED, i);
                            }
                            while (input.getBytesUntilLimit() > 0) {
                                value = input.readInt32();
                                switch (value) {
                                    case IdleLogs.HASH_MATCH_UNSPECIFIED /*0*/:
                                    case IdleLogs.GOOD_HASH /*1*/:
                                    case IdleLogs.BAD_HASH /*2*/:
                                    case IdleLogs.UNKNOWN_HASH /*3*/:
                                        int i2 = i + IdleLogs.GOOD_HASH;
                                        newArray[i] = value;
                                        i = i2;
                                        break;
                                    default:
                                        break;
                                }
                            }
                            this.hashMatches = newArray;
                        }
                        input.popLimit(limit);
                        continue;
                    case LogSource.NOVA /*34*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 34);
                        if (this.filesToUpload == null) {
                            i = IdleLogs.HASH_MATCH_UNSPECIFIED;
                        } else {
                            i = this.filesToUpload.length;
                        }
                        SystemIntegrityFileToUpload[] newArray2 = new SystemIntegrityFileToUpload[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.filesToUpload, IdleLogs.HASH_MATCH_UNSPECIFIED, newArray2, IdleLogs.HASH_MATCH_UNSPECIFIED, i);
                        }
                        while (i < newArray2.length - 1) {
                            newArray2[i] = new SystemIntegrityFileToUpload();
                            input.readMessage(newArray2[i]);
                            input.readTag();
                            i += IdleLogs.GOOD_HASH;
                        }
                        newArray2[i] = new SystemIntegrityFileToUpload();
                        input.readMessage(newArray2[i]);
                        this.filesToUpload = newArray2;
                        continue;
                    default:
                        if (!WireFormatNano.parseUnknownField(input, tag)) {
                            break;
                        }
                        continue;
                }
                return this;
            }
        }

        public static SystemIntegrityResponse parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (SystemIntegrityResponse) MessageNano.mergeFrom(new SystemIntegrityResponse(), data);
        }

        public static SystemIntegrityResponse parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new SystemIntegrityResponse().mergeFrom(input);
        }
    }

    public static final class SystemPartitionFileInfo extends MessageNano {
        private static volatile SystemPartitionFileInfo[] _emptyArray;
        public boolean couldNotCheck;
        public SicFileInfo[] fileInfo;
        public int state;

        public static SystemPartitionFileInfo[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new SystemPartitionFileInfo[IdleLogs.HASH_MATCH_UNSPECIFIED];
                    }
                }
            }
            return _emptyArray;
        }

        public SystemPartitionFileInfo() {
            clear();
        }

        public SystemPartitionFileInfo clear() {
            this.couldNotCheck = false;
            this.fileInfo = SicFileInfo.emptyArray();
            this.state = IdleLogs.HASH_MATCH_UNSPECIFIED;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.couldNotCheck) {
                output.writeBool(IdleLogs.GOOD_HASH, this.couldNotCheck);
            }
            if (this.fileInfo != null && this.fileInfo.length > 0) {
                for (int i = IdleLogs.HASH_MATCH_UNSPECIFIED; i < this.fileInfo.length; i += IdleLogs.GOOD_HASH) {
                    SicFileInfo element = this.fileInfo[i];
                    if (element != null) {
                        output.writeMessage(IdleLogs.BAD_HASH, element);
                    }
                }
            }
            if (this.state != 0) {
                output.writeInt32(IdleLogs.UNKNOWN_HASH, this.state);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.couldNotCheck) {
                size += CodedOutputByteBufferNano.computeBoolSize(IdleLogs.GOOD_HASH, this.couldNotCheck);
            }
            if (this.fileInfo != null && this.fileInfo.length > 0) {
                for (int i = IdleLogs.HASH_MATCH_UNSPECIFIED; i < this.fileInfo.length; i += IdleLogs.GOOD_HASH) {
                    SicFileInfo element = this.fileInfo[i];
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(IdleLogs.BAD_HASH, element);
                    }
                }
            }
            if (this.state != 0) {
                return size + CodedOutputByteBufferNano.computeInt32Size(IdleLogs.UNKNOWN_HASH, this.state);
            }
            return size;
        }

        public SystemPartitionFileInfo mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case IdleLogs.HASH_MATCH_UNSPECIFIED /*0*/:
                        break;
                    case Type.TAP_GET_LINK /*8*/:
                        this.couldNotCheck = input.readBool();
                        continue;
                    case LogSource.GOOGLE_ANALYTICS /*18*/:
                        int i;
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 18);
                        if (this.fileInfo == null) {
                            i = IdleLogs.HASH_MATCH_UNSPECIFIED;
                        } else {
                            i = this.fileInfo.length;
                        }
                        SicFileInfo[] newArray = new SicFileInfo[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.fileInfo, IdleLogs.HASH_MATCH_UNSPECIFIED, newArray, IdleLogs.HASH_MATCH_UNSPECIFIED, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new SicFileInfo();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i += IdleLogs.GOOD_HASH;
                        }
                        newArray[i] = new SicFileInfo();
                        input.readMessage(newArray[i]);
                        this.fileInfo = newArray;
                        continue;
                    case LogSource.LB_C /*24*/:
                        int value = input.readInt32();
                        switch (value) {
                            case IdleLogs.HASH_MATCH_UNSPECIFIED /*0*/:
                            case IdleLogs.GOOD_HASH /*1*/:
                            case IdleLogs.BAD_HASH /*2*/:
                            case IdleLogs.UNKNOWN_HASH /*3*/:
                                this.state = value;
                                break;
                            default:
                                continue;
                        }
                    default:
                        if (!WireFormatNano.parseUnknownField(input, tag)) {
                            break;
                        }
                        continue;
                }
                return this;
            }
        }

        public static SystemPartitionFileInfo parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (SystemPartitionFileInfo) MessageNano.mergeFrom(new SystemPartitionFileInfo(), data);
        }

        public static SystemPartitionFileInfo parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new SystemPartitionFileInfo().mergeFrom(input);
        }
    }
}
