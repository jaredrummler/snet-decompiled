package com.google.common.logging;

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
import com.google.wireless.android.play.playlog.proto.UserLocation.OvenFreshEvent.OvenFreshResult;
import com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection;
import java.io.IOException;

public interface NetstatsLogProto {

    public static final class NetstatsLog extends ExtendableMessageNano<NetstatsLog> {
        private static volatile NetstatsLog[] _emptyArray;
        public long endReportMillis;
        public int parsingError;
        public long startReportMillis;
        public History[] uidHistory;
        public History[] uidTagHistory;

        public static final class History extends ExtendableMessageNano<History> {
            private static volatile History[] _emptyArray;
            public Bucket[] bucket;
            public Key key;

            public static final class Bucket extends ExtendableMessageNano<Bucket> {
                private static volatile Bucket[] _emptyArray;
                public long bucketStartMillis;
                public long operations;
                public long rxBytes;
                public long rxPackets;
                public long txBytes;
                public long txPackets;

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
                    this.bucketStartMillis = 0;
                    this.rxBytes = 0;
                    this.rxPackets = 0;
                    this.txBytes = 0;
                    this.txPackets = 0;
                    this.operations = 0;
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
                    if (this.bucketStartMillis != other.bucketStartMillis || this.rxBytes != other.rxBytes || this.rxPackets != other.rxPackets || this.txBytes != other.txBytes || this.txPackets != other.txPackets || this.operations != other.operations) {
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
                    int hashCode = (((((((((((((getClass().getName().hashCode() + 527) * 31) + ((int) (this.bucketStartMillis ^ (this.bucketStartMillis >>> 32)))) * 31) + ((int) (this.rxBytes ^ (this.rxBytes >>> 32)))) * 31) + ((int) (this.rxPackets ^ (this.rxPackets >>> 32)))) * 31) + ((int) (this.txBytes ^ (this.txBytes >>> 32)))) * 31) + ((int) (this.txPackets ^ (this.txPackets >>> 32)))) * 31) + ((int) (this.operations ^ (this.operations >>> 32)))) * 31;
                    int hashCode2 = (this.unknownFieldData == null || this.unknownFieldData.isEmpty()) ? 0 : this.unknownFieldData.hashCode();
                    return hashCode + hashCode2;
                }

                public void writeTo(CodedOutputByteBufferNano output) throws IOException {
                    if (this.bucketStartMillis != 0) {
                        output.writeInt64(1, this.bucketStartMillis);
                    }
                    if (this.rxBytes != 0) {
                        output.writeInt64(2, this.rxBytes);
                    }
                    if (this.rxPackets != 0) {
                        output.writeInt64(3, this.rxPackets);
                    }
                    if (this.txBytes != 0) {
                        output.writeInt64(4, this.txBytes);
                    }
                    if (this.txPackets != 0) {
                        output.writeInt64(5, this.txPackets);
                    }
                    if (this.operations != 0) {
                        output.writeInt64(6, this.operations);
                    }
                    super.writeTo(output);
                }

                protected int computeSerializedSize() {
                    int size = super.computeSerializedSize();
                    if (this.bucketStartMillis != 0) {
                        size += CodedOutputByteBufferNano.computeInt64Size(1, this.bucketStartMillis);
                    }
                    if (this.rxBytes != 0) {
                        size += CodedOutputByteBufferNano.computeInt64Size(2, this.rxBytes);
                    }
                    if (this.rxPackets != 0) {
                        size += CodedOutputByteBufferNano.computeInt64Size(3, this.rxPackets);
                    }
                    if (this.txBytes != 0) {
                        size += CodedOutputByteBufferNano.computeInt64Size(4, this.txBytes);
                    }
                    if (this.txPackets != 0) {
                        size += CodedOutputByteBufferNano.computeInt64Size(5, this.txPackets);
                    }
                    if (this.operations != 0) {
                        return size + CodedOutputByteBufferNano.computeInt64Size(6, this.operations);
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
                                this.bucketStartMillis = input.readInt64();
                                continue;
                            case LogSource.GMS_CORE_PEOPLE /*16*/:
                                this.rxBytes = input.readInt64();
                                continue;
                            case LogSource.LB_C /*24*/:
                                this.rxPackets = input.readInt64();
                                continue;
                            case LogSource.SOCIAL /*32*/:
                                this.txBytes = input.readInt64();
                                continue;
                            case LogSource.COPRESENCE /*40*/:
                                this.txPackets = input.readInt64();
                                continue;
                            case LogSource.BACKDROP /*48*/:
                                this.operations = input.readInt64();
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

            public static final class Key extends ExtendableMessageNano<Key> {
                private static volatile Key[] _emptyArray;
                public long durationMillis;
                public NetworkIdentity[] identity;
                public PackageInfo[] packages;
                public long tag;
                public int type;
                public String uidName;

                public static final class PackageInfo extends ExtendableMessageNano<PackageInfo> {
                    private static volatile PackageInfo[] _emptyArray;
                    public String packageName;
                    public int version;

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
                        this.packageName = BuildConfig.VERSION_NAME;
                        this.version = 0;
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
                        if (this.packageName == null) {
                            if (other.packageName != null) {
                                return false;
                            }
                        } else if (!this.packageName.equals(other.packageName)) {
                            return false;
                        }
                        if (this.version != other.version) {
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
                        int hashCode = (((((getClass().getName().hashCode() + 527) * 31) + (this.packageName == null ? 0 : this.packageName.hashCode())) * 31) + this.version) * 31;
                        if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                            i = this.unknownFieldData.hashCode();
                        }
                        return hashCode + i;
                    }

                    public void writeTo(CodedOutputByteBufferNano output) throws IOException {
                        if (!this.packageName.equals(BuildConfig.VERSION_NAME)) {
                            output.writeString(1, this.packageName);
                        }
                        if (this.version != 0) {
                            output.writeInt32(2, this.version);
                        }
                        super.writeTo(output);
                    }

                    protected int computeSerializedSize() {
                        int size = super.computeSerializedSize();
                        if (!this.packageName.equals(BuildConfig.VERSION_NAME)) {
                            size += CodedOutputByteBufferNano.computeStringSize(1, this.packageName);
                        }
                        if (this.version != 0) {
                            return size + CodedOutputByteBufferNano.computeInt32Size(2, this.version);
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
                                    this.packageName = input.readString();
                                    continue;
                                case LogSource.GMS_CORE_PEOPLE /*16*/:
                                    this.version = input.readInt32();
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

                public static Key[] emptyArray() {
                    if (_emptyArray == null) {
                        synchronized (InternalNano.LAZY_INIT_LOCK) {
                            if (_emptyArray == null) {
                                _emptyArray = new Key[0];
                            }
                        }
                    }
                    return _emptyArray;
                }

                public Key() {
                    clear();
                }

                public Key clear() {
                    this.identity = NetworkIdentity.emptyArray();
                    this.packages = PackageInfo.emptyArray();
                    this.uidName = BuildConfig.VERSION_NAME;
                    this.type = 0;
                    this.tag = 0;
                    this.durationMillis = 0;
                    this.unknownFieldData = null;
                    this.cachedSize = -1;
                    return this;
                }

                public boolean equals(Object o) {
                    if (o == this) {
                        return true;
                    }
                    if (!(o instanceof Key)) {
                        return false;
                    }
                    Key other = (Key) o;
                    if (!InternalNano.equals(this.identity, other.identity) || !InternalNano.equals(this.packages, other.packages)) {
                        return false;
                    }
                    if (this.uidName == null) {
                        if (other.uidName != null) {
                            return false;
                        }
                    } else if (!this.uidName.equals(other.uidName)) {
                        return false;
                    }
                    if (this.type != other.type || this.tag != other.tag || this.durationMillis != other.durationMillis) {
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
                    int hashCode = (((((((((((((getClass().getName().hashCode() + 527) * 31) + InternalNano.hashCode(this.identity)) * 31) + InternalNano.hashCode(this.packages)) * 31) + (this.uidName == null ? 0 : this.uidName.hashCode())) * 31) + this.type) * 31) + ((int) (this.tag ^ (this.tag >>> 32)))) * 31) + ((int) (this.durationMillis ^ (this.durationMillis >>> 32)))) * 31;
                    if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                        i = this.unknownFieldData.hashCode();
                    }
                    return hashCode + i;
                }

                public void writeTo(CodedOutputByteBufferNano output) throws IOException {
                    if (this.identity != null && this.identity.length > 0) {
                        for (NetworkIdentity element : this.identity) {
                            if (element != null) {
                                output.writeMessage(1, element);
                            }
                        }
                    }
                    if (this.packages != null && this.packages.length > 0) {
                        for (PackageInfo element2 : this.packages) {
                            if (element2 != null) {
                                output.writeMessage(3, element2);
                            }
                        }
                    }
                    if (!this.uidName.equals(BuildConfig.VERSION_NAME)) {
                        output.writeString(4, this.uidName);
                    }
                    if (this.type != 0) {
                        output.writeInt32(5, this.type);
                    }
                    if (this.tag != 0) {
                        output.writeInt64(6, this.tag);
                    }
                    if (this.durationMillis != 0) {
                        output.writeInt64(7, this.durationMillis);
                    }
                    super.writeTo(output);
                }

                protected int computeSerializedSize() {
                    int size = super.computeSerializedSize();
                    if (this.identity != null && this.identity.length > 0) {
                        for (NetworkIdentity element : this.identity) {
                            if (element != null) {
                                size += CodedOutputByteBufferNano.computeMessageSize(1, element);
                            }
                        }
                    }
                    if (this.packages != null && this.packages.length > 0) {
                        for (PackageInfo element2 : this.packages) {
                            if (element2 != null) {
                                size += CodedOutputByteBufferNano.computeMessageSize(3, element2);
                            }
                        }
                    }
                    if (!this.uidName.equals(BuildConfig.VERSION_NAME)) {
                        size += CodedOutputByteBufferNano.computeStringSize(4, this.uidName);
                    }
                    if (this.type != 0) {
                        size += CodedOutputByteBufferNano.computeInt32Size(5, this.type);
                    }
                    if (this.tag != 0) {
                        size += CodedOutputByteBufferNano.computeInt64Size(6, this.tag);
                    }
                    if (this.durationMillis != 0) {
                        return size + CodedOutputByteBufferNano.computeInt64Size(7, this.durationMillis);
                    }
                    return size;
                }

                public Key mergeFrom(CodedInputByteBufferNano input) throws IOException {
                    while (true) {
                        int tag = input.readTag();
                        int arrayLength;
                        int i;
                        switch (tag) {
                            case Action.UNKNOWN /*0*/:
                                break;
                            case Type.TAP_ABOUT /*10*/:
                                arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 10);
                                if (this.identity == null) {
                                    i = 0;
                                } else {
                                    i = this.identity.length;
                                }
                                NetworkIdentity[] newArray = new NetworkIdentity[(i + arrayLength)];
                                if (i != 0) {
                                    System.arraycopy(this.identity, 0, newArray, 0, i);
                                }
                                while (i < newArray.length - 1) {
                                    newArray[i] = new NetworkIdentity();
                                    input.readMessage(newArray[i]);
                                    input.readTag();
                                    i++;
                                }
                                newArray[i] = new NetworkIdentity();
                                input.readMessage(newArray[i]);
                                this.identity = newArray;
                                continue;
                            case LogSource.ANDROID_CAMERA /*26*/:
                                arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 26);
                                if (this.packages == null) {
                                    i = 0;
                                } else {
                                    i = this.packages.length;
                                }
                                PackageInfo[] newArray2 = new PackageInfo[(i + arrayLength)];
                                if (i != 0) {
                                    System.arraycopy(this.packages, 0, newArray2, 0, i);
                                }
                                while (i < newArray2.length - 1) {
                                    newArray2[i] = new PackageInfo();
                                    input.readMessage(newArray2[i]);
                                    input.readTag();
                                    i++;
                                }
                                newArray2[i] = new PackageInfo();
                                input.readMessage(newArray2[i]);
                                this.packages = newArray2;
                                continue;
                            case LogSource.NOVA /*34*/:
                                this.uidName = input.readString();
                                continue;
                            case LogSource.COPRESENCE /*40*/:
                                int value = input.readInt32();
                                switch (value) {
                                    case Action.UNKNOWN /*0*/:
                                    case TimeSelection.Type.TEMPORARY /*1*/:
                                    case TimeSelection.Type.INDEFINITELY /*2*/:
                                    case TimeSelection.Type.CUSTOM /*3*/:
                                        this.type = value;
                                        break;
                                    default:
                                        continue;
                                }
                            case LogSource.BACKDROP /*48*/:
                                this.tag = input.readInt64();
                                continue;
                            case LogSource.DOCS /*56*/:
                                this.durationMillis = input.readInt64();
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

                public static Key parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
                    return (Key) MessageNano.mergeFrom(new Key(), data);
                }

                public static Key parseFrom(CodedInputByteBufferNano input) throws IOException {
                    return new Key().mergeFrom(input);
                }
            }

            public static History[] emptyArray() {
                if (_emptyArray == null) {
                    synchronized (InternalNano.LAZY_INIT_LOCK) {
                        if (_emptyArray == null) {
                            _emptyArray = new History[0];
                        }
                    }
                }
                return _emptyArray;
            }

            public History() {
                clear();
            }

            public History clear() {
                this.key = null;
                this.bucket = Bucket.emptyArray();
                this.unknownFieldData = null;
                this.cachedSize = -1;
                return this;
            }

            public boolean equals(Object o) {
                if (o == this) {
                    return true;
                }
                if (!(o instanceof History)) {
                    return false;
                }
                History other = (History) o;
                if (this.key == null) {
                    if (other.key != null) {
                        return false;
                    }
                } else if (!this.key.equals(other.key)) {
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
                int hashCode = (((((getClass().getName().hashCode() + 527) * 31) + (this.key == null ? 0 : this.key.hashCode())) * 31) + InternalNano.hashCode(this.bucket)) * 31;
                if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                    i = this.unknownFieldData.hashCode();
                }
                return hashCode + i;
            }

            public void writeTo(CodedOutputByteBufferNano output) throws IOException {
                if (this.key != null) {
                    output.writeMessage(1, this.key);
                }
                if (this.bucket != null && this.bucket.length > 0) {
                    for (Bucket element : this.bucket) {
                        if (element != null) {
                            output.writeMessage(2, element);
                        }
                    }
                }
                super.writeTo(output);
            }

            protected int computeSerializedSize() {
                int size = super.computeSerializedSize();
                if (this.key != null) {
                    size += CodedOutputByteBufferNano.computeMessageSize(1, this.key);
                }
                if (this.bucket != null && this.bucket.length > 0) {
                    for (Bucket element : this.bucket) {
                        if (element != null) {
                            size += CodedOutputByteBufferNano.computeMessageSize(2, element);
                        }
                    }
                }
                return size;
            }

            public History mergeFrom(CodedInputByteBufferNano input) throws IOException {
                while (true) {
                    int tag = input.readTag();
                    switch (tag) {
                        case Action.UNKNOWN /*0*/:
                            break;
                        case Type.TAP_ABOUT /*10*/:
                            if (this.key == null) {
                                this.key = new Key();
                            }
                            input.readMessage(this.key);
                            continue;
                        case LogSource.GOOGLE_ANALYTICS /*18*/:
                            int i;
                            int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 18);
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

            public static History parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
                return (History) MessageNano.mergeFrom(new History(), data);
            }

            public static History parseFrom(CodedInputByteBufferNano input) throws IOException {
                return new History().mergeFrom(input);
            }
        }

        public interface MobileSubtype {
            public static final int CDMA = 4;
            public static final int COMBINED = 100;
            public static final int EDGE = 2;
            public static final int EHRPD = 14;
            public static final int EVDO_0 = 5;
            public static final int EVDO_A = 6;
            public static final int EVDO_B = 12;
            public static final int GPRS = 1;
            public static final int GSM = 16;
            public static final int HSDPA = 8;
            public static final int HSPA = 10;
            public static final int HSPAP = 15;
            public static final int HSUPA = 9;
            public static final int IDEN = 11;
            public static final int LTE = 13;
            public static final int RTT = 7;
            public static final int UMTS = 3;
            public static final int UNKNOWN_MOBILE_SUBTYPE = 0;
        }

        public interface NetworkActivityType {
            public static final int BACKGROUND = 2;
            public static final int BOTH = 1;
            public static final int FOREGROUND = 3;
            public static final int UNKNOWN_ACTIVITY_TYPE = 0;
        }

        public static final class NetworkIdentity extends ExtendableMessageNano<NetworkIdentity> {
            private static volatile NetworkIdentity[] _emptyArray;
            public String mccmnc;
            public int networkType;
            public String networkTypeName;
            public boolean roaming;
            public int subtype;
            public String subtypeName;

            public static NetworkIdentity[] emptyArray() {
                if (_emptyArray == null) {
                    synchronized (InternalNano.LAZY_INIT_LOCK) {
                        if (_emptyArray == null) {
                            _emptyArray = new NetworkIdentity[0];
                        }
                    }
                }
                return _emptyArray;
            }

            public NetworkIdentity() {
                clear();
            }

            public NetworkIdentity clear() {
                this.networkType = -1;
                this.networkTypeName = BuildConfig.VERSION_NAME;
                this.subtype = 0;
                this.subtypeName = BuildConfig.VERSION_NAME;
                this.mccmnc = BuildConfig.VERSION_NAME;
                this.roaming = false;
                this.unknownFieldData = null;
                this.cachedSize = -1;
                return this;
            }

            public boolean equals(Object o) {
                if (o == this) {
                    return true;
                }
                if (!(o instanceof NetworkIdentity)) {
                    return false;
                }
                NetworkIdentity other = (NetworkIdentity) o;
                if (this.networkType != other.networkType) {
                    return false;
                }
                if (this.networkTypeName == null) {
                    if (other.networkTypeName != null) {
                        return false;
                    }
                } else if (!this.networkTypeName.equals(other.networkTypeName)) {
                    return false;
                }
                if (this.subtype != other.subtype) {
                    return false;
                }
                if (this.subtypeName == null) {
                    if (other.subtypeName != null) {
                        return false;
                    }
                } else if (!this.subtypeName.equals(other.subtypeName)) {
                    return false;
                }
                if (this.mccmnc == null) {
                    if (other.mccmnc != null) {
                        return false;
                    }
                } else if (!this.mccmnc.equals(other.mccmnc)) {
                    return false;
                }
                if (this.roaming != other.roaming) {
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
                int hashCode = (((((((((((((getClass().getName().hashCode() + 527) * 31) + this.networkType) * 31) + (this.networkTypeName == null ? 0 : this.networkTypeName.hashCode())) * 31) + this.subtype) * 31) + (this.subtypeName == null ? 0 : this.subtypeName.hashCode())) * 31) + (this.mccmnc == null ? 0 : this.mccmnc.hashCode())) * 31) + (this.roaming ? 1231 : 1237)) * 31;
                if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                    i = this.unknownFieldData.hashCode();
                }
                return hashCode + i;
            }

            public void writeTo(CodedOutputByteBufferNano output) throws IOException {
                if (this.networkType != -1) {
                    output.writeInt32(1, this.networkType);
                }
                if (!this.networkTypeName.equals(BuildConfig.VERSION_NAME)) {
                    output.writeString(2, this.networkTypeName);
                }
                if (this.subtype != 0) {
                    output.writeInt32(3, this.subtype);
                }
                if (!this.subtypeName.equals(BuildConfig.VERSION_NAME)) {
                    output.writeString(4, this.subtypeName);
                }
                if (!this.mccmnc.equals(BuildConfig.VERSION_NAME)) {
                    output.writeString(5, this.mccmnc);
                }
                if (this.roaming) {
                    output.writeBool(6, this.roaming);
                }
                super.writeTo(output);
            }

            protected int computeSerializedSize() {
                int size = super.computeSerializedSize();
                if (this.networkType != -1) {
                    size += CodedOutputByteBufferNano.computeInt32Size(1, this.networkType);
                }
                if (!this.networkTypeName.equals(BuildConfig.VERSION_NAME)) {
                    size += CodedOutputByteBufferNano.computeStringSize(2, this.networkTypeName);
                }
                if (this.subtype != 0) {
                    size += CodedOutputByteBufferNano.computeInt32Size(3, this.subtype);
                }
                if (!this.subtypeName.equals(BuildConfig.VERSION_NAME)) {
                    size += CodedOutputByteBufferNano.computeStringSize(4, this.subtypeName);
                }
                if (!this.mccmnc.equals(BuildConfig.VERSION_NAME)) {
                    size += CodedOutputByteBufferNano.computeStringSize(5, this.mccmnc);
                }
                if (this.roaming) {
                    return size + CodedOutputByteBufferNano.computeBoolSize(6, this.roaming);
                }
                return size;
            }

            public NetworkIdentity mergeFrom(CodedInputByteBufferNano input) throws IOException {
                while (true) {
                    int tag = input.readTag();
                    int value;
                    switch (tag) {
                        case Action.UNKNOWN /*0*/:
                            break;
                        case Type.TAP_GET_LINK /*8*/:
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
                                    this.networkType = value;
                                    break;
                                default:
                                    continue;
                            }
                        case LogSource.GOOGLE_ANALYTICS /*18*/:
                            this.networkTypeName = input.readString();
                            continue;
                        case LogSource.LB_C /*24*/:
                            value = input.readInt32();
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
                                case LogSource.VISION /*100*/:
                                    this.subtype = value;
                                    break;
                                default:
                                    continue;
                            }
                        case LogSource.NOVA /*34*/:
                            this.subtypeName = input.readString();
                            continue;
                        case LogSource.PHOTOS /*42*/:
                            this.mccmnc = input.readString();
                            continue;
                        case LogSource.BACKDROP /*48*/:
                            this.roaming = input.readBool();
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

            public static NetworkIdentity parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
                return (NetworkIdentity) MessageNano.mergeFrom(new NetworkIdentity(), data);
            }

            public static NetworkIdentity parseFrom(CodedInputByteBufferNano input) throws IOException {
                return new NetworkIdentity().mergeFrom(input);
            }
        }

        public interface NetworkType {
            public static final int BLUETOOTH = 7;
            public static final int DUMMY = 8;
            public static final int ETHERNET = 9;
            public static final int MOBILE = 0;
            public static final int MOBILE_CBS = 12;
            public static final int MOBILE_DUN = 4;
            public static final int MOBILE_EMERGENCY = 15;
            public static final int MOBILE_FOTA = 10;
            public static final int MOBILE_HIPRI = 5;
            public static final int MOBILE_IA = 14;
            public static final int MOBILE_IMS = 11;
            public static final int MOBILE_MMS = 2;
            public static final int MOBILE_SUPL = 3;
            public static final int NONE = -1;
            public static final int PROXY = 16;
            public static final int VPN = 17;
            public static final int WIFI = 1;
            public static final int WIFI_P2P = 13;
            public static final int WIMAX = 6;
        }

        public interface ParsingError {
            public static final int IDENT_ERROR = 2;
            public static final int IO_EXCEPTION = 3;
            public static final int NO_ERROR = 0;
            public static final int NUMBER_FORMAT_EXCEPTION = 4;
            public static final int UNKNOWN_ERROR = 1;
        }

        public static NetstatsLog[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new NetstatsLog[0];
                    }
                }
            }
            return _emptyArray;
        }

        public NetstatsLog() {
            clear();
        }

        public NetstatsLog clear() {
            this.startReportMillis = 0;
            this.endReportMillis = 0;
            this.uidHistory = History.emptyArray();
            this.uidTagHistory = History.emptyArray();
            this.parsingError = 0;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof NetstatsLog)) {
                return false;
            }
            NetstatsLog other = (NetstatsLog) o;
            if (this.startReportMillis != other.startReportMillis || this.endReportMillis != other.endReportMillis || !InternalNano.equals(this.uidHistory, other.uidHistory) || !InternalNano.equals(this.uidTagHistory, other.uidTagHistory) || this.parsingError != other.parsingError) {
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
            int hashCode = (((((((((((getClass().getName().hashCode() + 527) * 31) + ((int) (this.startReportMillis ^ (this.startReportMillis >>> 32)))) * 31) + ((int) (this.endReportMillis ^ (this.endReportMillis >>> 32)))) * 31) + InternalNano.hashCode(this.uidHistory)) * 31) + InternalNano.hashCode(this.uidTagHistory)) * 31) + this.parsingError) * 31;
            int hashCode2 = (this.unknownFieldData == null || this.unknownFieldData.isEmpty()) ? 0 : this.unknownFieldData.hashCode();
            return hashCode + hashCode2;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.startReportMillis != 0) {
                output.writeInt64(1, this.startReportMillis);
            }
            if (this.endReportMillis != 0) {
                output.writeInt64(2, this.endReportMillis);
            }
            if (this.uidHistory != null && this.uidHistory.length > 0) {
                for (History element : this.uidHistory) {
                    if (element != null) {
                        output.writeMessage(3, element);
                    }
                }
            }
            if (this.uidTagHistory != null && this.uidTagHistory.length > 0) {
                for (History element2 : this.uidTagHistory) {
                    if (element2 != null) {
                        output.writeMessage(4, element2);
                    }
                }
            }
            if (this.parsingError != 0) {
                output.writeInt32(5, this.parsingError);
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
            if (this.uidHistory != null && this.uidHistory.length > 0) {
                for (History element : this.uidHistory) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(3, element);
                    }
                }
            }
            if (this.uidTagHistory != null && this.uidTagHistory.length > 0) {
                for (History element2 : this.uidTagHistory) {
                    if (element2 != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(4, element2);
                    }
                }
            }
            if (this.parsingError != 0) {
                return size + CodedOutputByteBufferNano.computeInt32Size(5, this.parsingError);
            }
            return size;
        }

        public NetstatsLog mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                int arrayLength;
                int i;
                History[] newArray;
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
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 26);
                        if (this.uidHistory == null) {
                            i = 0;
                        } else {
                            i = this.uidHistory.length;
                        }
                        newArray = new History[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.uidHistory, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new History();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i++;
                        }
                        newArray[i] = new History();
                        input.readMessage(newArray[i]);
                        this.uidHistory = newArray;
                        continue;
                    case LogSource.NOVA /*34*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 34);
                        if (this.uidTagHistory == null) {
                            i = 0;
                        } else {
                            i = this.uidTagHistory.length;
                        }
                        newArray = new History[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.uidTagHistory, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new History();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i++;
                        }
                        newArray[i] = new History();
                        input.readMessage(newArray[i]);
                        this.uidTagHistory = newArray;
                        continue;
                    case LogSource.COPRESENCE /*40*/:
                        int value = input.readInt32();
                        switch (value) {
                            case Action.UNKNOWN /*0*/:
                            case TimeSelection.Type.TEMPORARY /*1*/:
                            case TimeSelection.Type.INDEFINITELY /*2*/:
                            case TimeSelection.Type.CUSTOM /*3*/:
                            case OvenFreshResult.NO_ACCOUNTS /*4*/:
                                this.parsingError = value;
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

        public static NetstatsLog parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (NetstatsLog) MessageNano.mergeFrom(new NetstatsLog(), data);
        }

        public static NetstatsLog parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new NetstatsLog().mergeFrom(input);
        }
    }
}
