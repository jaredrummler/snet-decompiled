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

public interface Logs {
    public static final int CHROME_LINUX_USER_AGENT = 0;
    public static final int SAFARI_IPHONE_USER_AGENT = 1;

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
                        _emptyArray = new AppInfo[Logs.CHROME_LINUX_USER_AGENT];
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
                output.writeString(Logs.SAFARI_IPHONE_USER_AGENT, this.packageName);
            }
            if (!this.apkSha256.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(2, this.apkSha256);
            }
            if (this.signatureSha256 != null && this.signatureSha256.length > 0) {
                for (i = Logs.CHROME_LINUX_USER_AGENT; i < this.signatureSha256.length; i += Logs.SAFARI_IPHONE_USER_AGENT) {
                    String element = this.signatureSha256[i];
                    if (element != null) {
                        output.writeString(3, element);
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
                for (i = Logs.CHROME_LINUX_USER_AGENT; i < this.signatureSha256Bytes.length; i += Logs.SAFARI_IPHONE_USER_AGENT) {
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
                size += CodedOutputByteBufferNano.computeStringSize(Logs.SAFARI_IPHONE_USER_AGENT, this.packageName);
            }
            if (!this.apkSha256.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(2, this.apkSha256);
            }
            if (this.signatureSha256 != null && this.signatureSha256.length > 0) {
                dataCount = Logs.CHROME_LINUX_USER_AGENT;
                dataSize = Logs.CHROME_LINUX_USER_AGENT;
                for (i = Logs.CHROME_LINUX_USER_AGENT; i < this.signatureSha256.length; i += Logs.SAFARI_IPHONE_USER_AGENT) {
                    String element = this.signatureSha256[i];
                    if (element != null) {
                        dataCount += Logs.SAFARI_IPHONE_USER_AGENT;
                        dataSize += CodedOutputByteBufferNano.computeStringSizeNoTag(element);
                    }
                }
                size = (size + dataSize) + (dataCount * Logs.SAFARI_IPHONE_USER_AGENT);
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
            dataCount = Logs.CHROME_LINUX_USER_AGENT;
            dataSize = Logs.CHROME_LINUX_USER_AGENT;
            for (i = Logs.CHROME_LINUX_USER_AGENT; i < this.signatureSha256Bytes.length; i += Logs.SAFARI_IPHONE_USER_AGENT) {
                byte[] element2 = this.signatureSha256Bytes[i];
                if (element2 != null) {
                    dataCount += Logs.SAFARI_IPHONE_USER_AGENT;
                    dataSize += CodedOutputByteBufferNano.computeBytesSizeNoTag(element2);
                }
            }
            return (size + dataSize) + (dataCount * Logs.SAFARI_IPHONE_USER_AGENT);
        }

        public AppInfo mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                int arrayLength;
                int i;
                switch (tag) {
                    case Logs.CHROME_LINUX_USER_AGENT /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        this.packageName = input.readString();
                        continue;
                    case LogSource.GOOGLE_ANALYTICS /*18*/:
                        this.apkSha256 = input.readString();
                        continue;
                    case LogSource.ANDROID_CAMERA /*26*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 26);
                        i = this.signatureSha256 == null ? Logs.CHROME_LINUX_USER_AGENT : this.signatureSha256.length;
                        String[] newArray = new String[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.signatureSha256, Logs.CHROME_LINUX_USER_AGENT, newArray, Logs.CHROME_LINUX_USER_AGENT, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = input.readString();
                            input.readTag();
                            i += Logs.SAFARI_IPHONE_USER_AGENT;
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
                        i = this.signatureSha256Bytes == null ? Logs.CHROME_LINUX_USER_AGENT : this.signatureSha256Bytes.length;
                        byte[][] newArray2 = new byte[(i + arrayLength)][];
                        if (i != 0) {
                            System.arraycopy(this.signatureSha256Bytes, Logs.CHROME_LINUX_USER_AGENT, newArray2, Logs.CHROME_LINUX_USER_AGENT, i);
                        }
                        while (i < newArray2.length - 1) {
                            newArray2[i] = input.readBytes();
                            input.readTag();
                            i += Logs.SAFARI_IPHONE_USER_AGENT;
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

    public static final class AppsList extends MessageNano {
        public static final int FULL_LOGGING = 2;
        public static final int NO_LOGGING = 0;
        public static final int SHA256_ONLY = 1;
        private static volatile AppsList[] _emptyArray;
        public AppInfo[] appsInfo;
        public int loggingOptions;
        public boolean reportNonSystemApps;
        public boolean reportSystemApps;

        public static AppsList[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new AppsList[NO_LOGGING];
                    }
                }
            }
            return _emptyArray;
        }

        public AppsList() {
            clear();
        }

        public AppsList clear() {
            this.reportSystemApps = false;
            this.reportNonSystemApps = false;
            this.loggingOptions = NO_LOGGING;
            this.appsInfo = AppInfo.emptyArray();
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.reportSystemApps) {
                output.writeBool(SHA256_ONLY, this.reportSystemApps);
            }
            if (this.reportNonSystemApps) {
                output.writeBool(FULL_LOGGING, this.reportNonSystemApps);
            }
            if (this.loggingOptions != 0) {
                output.writeInt32(3, this.loggingOptions);
            }
            if (this.appsInfo != null && this.appsInfo.length > 0) {
                for (int i = NO_LOGGING; i < this.appsInfo.length; i += SHA256_ONLY) {
                    AppInfo element = this.appsInfo[i];
                    if (element != null) {
                        output.writeMessage(4, element);
                    }
                }
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.reportSystemApps) {
                size += CodedOutputByteBufferNano.computeBoolSize(SHA256_ONLY, this.reportSystemApps);
            }
            if (this.reportNonSystemApps) {
                size += CodedOutputByteBufferNano.computeBoolSize(FULL_LOGGING, this.reportNonSystemApps);
            }
            if (this.loggingOptions != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(3, this.loggingOptions);
            }
            if (this.appsInfo != null && this.appsInfo.length > 0) {
                for (int i = NO_LOGGING; i < this.appsInfo.length; i += SHA256_ONLY) {
                    AppInfo element = this.appsInfo[i];
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(4, element);
                    }
                }
            }
            return size;
        }

        public AppsList mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case NO_LOGGING /*0*/:
                        break;
                    case Type.TAP_GET_LINK /*8*/:
                        this.reportSystemApps = input.readBool();
                        continue;
                    case LogSource.GMS_CORE_PEOPLE /*16*/:
                        this.reportNonSystemApps = input.readBool();
                        continue;
                    case LogSource.LB_C /*24*/:
                        int value = input.readInt32();
                        switch (value) {
                            case NO_LOGGING /*0*/:
                            case SHA256_ONLY /*1*/:
                            case FULL_LOGGING /*2*/:
                                this.loggingOptions = value;
                                break;
                            default:
                                continue;
                        }
                    case LogSource.NOVA /*34*/:
                        int i;
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 34);
                        if (this.appsInfo == null) {
                            i = NO_LOGGING;
                        } else {
                            i = this.appsInfo.length;
                        }
                        AppInfo[] newArray = new AppInfo[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.appsInfo, NO_LOGGING, newArray, NO_LOGGING, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new AppInfo();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i += SHA256_ONLY;
                        }
                        newArray[i] = new AppInfo();
                        input.readMessage(newArray[i]);
                        this.appsInfo = newArray;
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

        public static AppsList parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (AppsList) MessageNano.mergeFrom(new AppsList(), data);
        }

        public static AppsList parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new AppsList().mergeFrom(input);
        }
    }

    public static final class AttestationResult extends MessageNano {
        private static volatile AttestationResult[] _emptyArray;
        public byte[] certChainSha256;
        public String errorMsg;
        public String jsonData;
        public String signature;
        public int statusCode;

        public static AttestationResult[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new AttestationResult[Logs.CHROME_LINUX_USER_AGENT];
                    }
                }
            }
            return _emptyArray;
        }

        public AttestationResult() {
            clear();
        }

        public AttestationResult clear() {
            this.statusCode = Logs.CHROME_LINUX_USER_AGENT;
            this.certChainSha256 = WireFormatNano.EMPTY_BYTES;
            this.jsonData = BuildConfig.VERSION_NAME;
            this.signature = BuildConfig.VERSION_NAME;
            this.errorMsg = BuildConfig.VERSION_NAME;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.statusCode != 0) {
                output.writeInt32(Logs.SAFARI_IPHONE_USER_AGENT, this.statusCode);
            }
            if (!Arrays.equals(this.certChainSha256, WireFormatNano.EMPTY_BYTES)) {
                output.writeBytes(2, this.certChainSha256);
            }
            if (!this.jsonData.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(3, this.jsonData);
            }
            if (!this.signature.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(4, this.signature);
            }
            if (!this.errorMsg.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(5, this.errorMsg);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.statusCode != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(Logs.SAFARI_IPHONE_USER_AGENT, this.statusCode);
            }
            if (!Arrays.equals(this.certChainSha256, WireFormatNano.EMPTY_BYTES)) {
                size += CodedOutputByteBufferNano.computeBytesSize(2, this.certChainSha256);
            }
            if (!this.jsonData.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(3, this.jsonData);
            }
            if (!this.signature.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(4, this.signature);
            }
            if (this.errorMsg.equals(BuildConfig.VERSION_NAME)) {
                return size;
            }
            return size + CodedOutputByteBufferNano.computeStringSize(5, this.errorMsg);
        }

        public AttestationResult mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Logs.CHROME_LINUX_USER_AGENT /*0*/:
                        break;
                    case Type.TAP_GET_LINK /*8*/:
                        this.statusCode = input.readInt32();
                        continue;
                    case LogSource.GOOGLE_ANALYTICS /*18*/:
                        this.certChainSha256 = input.readBytes();
                        continue;
                    case LogSource.ANDROID_CAMERA /*26*/:
                        this.jsonData = input.readString();
                        continue;
                    case LogSource.NOVA /*34*/:
                        this.signature = input.readString();
                        continue;
                    case LogSource.PHOTOS /*42*/:
                        this.errorMsg = input.readString();
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

        public static AttestationResult parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (AttestationResult) MessageNano.mergeFrom(new AttestationResult(), data);
        }

        public static AttestationResult parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new AttestationResult().mergeFrom(input);
        }
    }

    public static final class CaptivePortalTestResults extends MessageNano {
        private static volatile CaptivePortalTestResults[] _emptyArray;
        public boolean bodyEmpty;
        public String ipAddressUsed;
        public int responseCode;
        public int userAgentUsed;

        public static CaptivePortalTestResults[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new CaptivePortalTestResults[Logs.CHROME_LINUX_USER_AGENT];
                    }
                }
            }
            return _emptyArray;
        }

        public CaptivePortalTestResults() {
            clear();
        }

        public CaptivePortalTestResults clear() {
            this.ipAddressUsed = BuildConfig.VERSION_NAME;
            this.responseCode = Logs.CHROME_LINUX_USER_AGENT;
            this.bodyEmpty = false;
            this.userAgentUsed = Logs.CHROME_LINUX_USER_AGENT;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (!this.ipAddressUsed.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(Logs.SAFARI_IPHONE_USER_AGENT, this.ipAddressUsed);
            }
            if (this.responseCode != 0) {
                output.writeInt32(2, this.responseCode);
            }
            if (this.bodyEmpty) {
                output.writeBool(3, this.bodyEmpty);
            }
            if (this.userAgentUsed != 0) {
                output.writeInt32(4, this.userAgentUsed);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (!this.ipAddressUsed.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(Logs.SAFARI_IPHONE_USER_AGENT, this.ipAddressUsed);
            }
            if (this.responseCode != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(2, this.responseCode);
            }
            if (this.bodyEmpty) {
                size += CodedOutputByteBufferNano.computeBoolSize(3, this.bodyEmpty);
            }
            if (this.userAgentUsed != 0) {
                return size + CodedOutputByteBufferNano.computeInt32Size(4, this.userAgentUsed);
            }
            return size;
        }

        public CaptivePortalTestResults mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Logs.CHROME_LINUX_USER_AGENT /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        this.ipAddressUsed = input.readString();
                        continue;
                    case LogSource.GMS_CORE_PEOPLE /*16*/:
                        this.responseCode = input.readInt32();
                        continue;
                    case LogSource.LB_C /*24*/:
                        this.bodyEmpty = input.readBool();
                        continue;
                    case LogSource.SOCIAL /*32*/:
                        int value = input.readInt32();
                        switch (value) {
                            case Logs.CHROME_LINUX_USER_AGENT /*0*/:
                            case Logs.SAFARI_IPHONE_USER_AGENT /*1*/:
                                this.userAgentUsed = value;
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

        public static CaptivePortalTestResults parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (CaptivePortalTestResults) MessageNano.mergeFrom(new CaptivePortalTestResults(), data);
        }

        public static CaptivePortalTestResults parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new CaptivePortalTestResults().mergeFrom(input);
        }
    }

    public static final class ConnectionInfo extends MessageNano {
        public static final int BLUETOOTH = 7;
        public static final int DUMMY = 8;
        public static final int ETHERNET = 9;
        public static final int MOBILE = 0;
        public static final int MOBILE_DUN = 4;
        public static final int MOBILE_HIPRI = 5;
        public static final int MOBILE_MMS = 2;
        public static final int MOBILE_SUPL = 3;
        public static final int WIFI = 1;
        public static final int WIMAX = 6;
        private static volatile ConnectionInfo[] _emptyArray;
        public int activeConnectionType;
        public int availableNetworkTypes;
        public String[] dnsServers;
        public String operatorName;

        public static ConnectionInfo[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new ConnectionInfo[MOBILE];
                    }
                }
            }
            return _emptyArray;
        }

        public ConnectionInfo() {
            clear();
        }

        public ConnectionInfo clear() {
            this.activeConnectionType = MOBILE;
            this.availableNetworkTypes = MOBILE;
            this.operatorName = BuildConfig.VERSION_NAME;
            this.dnsServers = WireFormatNano.EMPTY_STRING_ARRAY;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.activeConnectionType != 0) {
                output.writeInt32(WIFI, this.activeConnectionType);
            }
            if (this.availableNetworkTypes != 0) {
                output.writeInt32(MOBILE_MMS, this.availableNetworkTypes);
            }
            if (!this.operatorName.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(MOBILE_SUPL, this.operatorName);
            }
            if (this.dnsServers != null && this.dnsServers.length > 0) {
                for (int i = MOBILE; i < this.dnsServers.length; i += WIFI) {
                    String element = this.dnsServers[i];
                    if (element != null) {
                        output.writeString(MOBILE_DUN, element);
                    }
                }
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.activeConnectionType != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(WIFI, this.activeConnectionType);
            }
            if (this.availableNetworkTypes != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(MOBILE_MMS, this.availableNetworkTypes);
            }
            if (!this.operatorName.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(MOBILE_SUPL, this.operatorName);
            }
            if (this.dnsServers == null || this.dnsServers.length <= 0) {
                return size;
            }
            int dataCount = MOBILE;
            int dataSize = MOBILE;
            for (int i = MOBILE; i < this.dnsServers.length; i += WIFI) {
                String element = this.dnsServers[i];
                if (element != null) {
                    dataCount += WIFI;
                    dataSize += CodedOutputByteBufferNano.computeStringSizeNoTag(element);
                }
            }
            return (size + dataSize) + (dataCount * WIFI);
        }

        public ConnectionInfo mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case MOBILE /*0*/:
                        break;
                    case DUMMY /*8*/:
                        int value = input.readInt32();
                        switch (value) {
                            case MOBILE /*0*/:
                            case WIFI /*1*/:
                            case MOBILE_MMS /*2*/:
                            case MOBILE_SUPL /*3*/:
                            case MOBILE_DUN /*4*/:
                            case MOBILE_HIPRI /*5*/:
                            case WIMAX /*6*/:
                            case BLUETOOTH /*7*/:
                            case DUMMY /*8*/:
                            case ETHERNET /*9*/:
                                this.activeConnectionType = value;
                                break;
                            default:
                                continue;
                        }
                    case LogSource.GMS_CORE_PEOPLE /*16*/:
                        this.availableNetworkTypes = input.readInt32();
                        continue;
                    case LogSource.ANDROID_CAMERA /*26*/:
                        this.operatorName = input.readString();
                        continue;
                    case LogSource.NOVA /*34*/:
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 34);
                        int i = this.dnsServers == null ? MOBILE : this.dnsServers.length;
                        String[] newArray = new String[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.dnsServers, MOBILE, newArray, MOBILE, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = input.readString();
                            input.readTag();
                            i += WIFI;
                        }
                        newArray[i] = input.readString();
                        this.dnsServers = newArray;
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

        public static ConnectionInfo parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (ConnectionInfo) MessageNano.mergeFrom(new ConnectionInfo(), data);
        }

        public static ConnectionInfo parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new ConnectionInfo().mergeFrom(input);
        }
    }

    public static final class DeviceSettings extends MessageNano {
        public static final int FACE_PATTERN = 5;
        public static final int FACE_PIN = 4;
        public static final int NONE = 0;
        public static final int NOTIFICATION_NONE = 0;
        public static final int NOTIFICATION_PRIVATE = 1;
        public static final int NOTIFICATION_PUBLIC = 2;
        public static final int NOTIFICATION_SECRET = 3;
        public static final int PASSWORD = 6;
        public static final int PATTERN = 3;
        public static final int PIN = 2;
        public static final int SECURE_UNKNOWN = 1;
        private static volatile DeviceSettings[] _emptyArray;
        public boolean adbEnabled;
        public int lockScreenNotificationType;
        public int lockScreenTimeout;
        public int lockScreenType;
        public boolean nonMarketAppsEnabled;
        public boolean smartLockEnabled;
        public boolean smartLockStatusObtained;

        public static DeviceSettings[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new DeviceSettings[NOTIFICATION_NONE];
                    }
                }
            }
            return _emptyArray;
        }

        public DeviceSettings() {
            clear();
        }

        public DeviceSettings clear() {
            this.adbEnabled = false;
            this.nonMarketAppsEnabled = false;
            this.lockScreenType = NOTIFICATION_NONE;
            this.lockScreenTimeout = NOTIFICATION_NONE;
            this.lockScreenNotificationType = NOTIFICATION_NONE;
            this.smartLockStatusObtained = false;
            this.smartLockEnabled = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.adbEnabled) {
                output.writeBool(SECURE_UNKNOWN, this.adbEnabled);
            }
            if (this.nonMarketAppsEnabled) {
                output.writeBool(PIN, this.nonMarketAppsEnabled);
            }
            if (this.lockScreenType != 0) {
                output.writeInt32(PATTERN, this.lockScreenType);
            }
            if (this.lockScreenTimeout != 0) {
                output.writeInt32(FACE_PIN, this.lockScreenTimeout);
            }
            if (this.lockScreenNotificationType != 0) {
                output.writeInt32(FACE_PATTERN, this.lockScreenNotificationType);
            }
            if (this.smartLockStatusObtained) {
                output.writeBool(PASSWORD, this.smartLockStatusObtained);
            }
            if (this.smartLockEnabled) {
                output.writeBool(7, this.smartLockEnabled);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.adbEnabled) {
                size += CodedOutputByteBufferNano.computeBoolSize(SECURE_UNKNOWN, this.adbEnabled);
            }
            if (this.nonMarketAppsEnabled) {
                size += CodedOutputByteBufferNano.computeBoolSize(PIN, this.nonMarketAppsEnabled);
            }
            if (this.lockScreenType != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(PATTERN, this.lockScreenType);
            }
            if (this.lockScreenTimeout != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(FACE_PIN, this.lockScreenTimeout);
            }
            if (this.lockScreenNotificationType != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(FACE_PATTERN, this.lockScreenNotificationType);
            }
            if (this.smartLockStatusObtained) {
                size += CodedOutputByteBufferNano.computeBoolSize(PASSWORD, this.smartLockStatusObtained);
            }
            if (this.smartLockEnabled) {
                return size + CodedOutputByteBufferNano.computeBoolSize(7, this.smartLockEnabled);
            }
            return size;
        }

        public DeviceSettings mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                int value;
                switch (tag) {
                    case NOTIFICATION_NONE /*0*/:
                        break;
                    case Type.TAP_GET_LINK /*8*/:
                        this.adbEnabled = input.readBool();
                        continue;
                    case LogSource.GMS_CORE_PEOPLE /*16*/:
                        this.nonMarketAppsEnabled = input.readBool();
                        continue;
                    case LogSource.LB_C /*24*/:
                        value = input.readInt32();
                        switch (value) {
                            case NOTIFICATION_NONE /*0*/:
                            case SECURE_UNKNOWN /*1*/:
                            case PIN /*2*/:
                            case PATTERN /*3*/:
                            case FACE_PIN /*4*/:
                            case FACE_PATTERN /*5*/:
                            case PASSWORD /*6*/:
                                this.lockScreenType = value;
                                break;
                            default:
                                continue;
                        }
                    case LogSource.SOCIAL /*32*/:
                        this.lockScreenTimeout = input.readInt32();
                        continue;
                    case LogSource.COPRESENCE /*40*/:
                        value = input.readInt32();
                        switch (value) {
                            case NOTIFICATION_NONE /*0*/:
                            case SECURE_UNKNOWN /*1*/:
                            case PIN /*2*/:
                            case PATTERN /*3*/:
                                this.lockScreenNotificationType = value;
                                break;
                            default:
                                continue;
                        }
                    case LogSource.BACKDROP /*48*/:
                        this.smartLockStatusObtained = input.readBool();
                        continue;
                    case LogSource.DOCS /*56*/:
                        this.smartLockEnabled = input.readBool();
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

        public static DeviceSettings parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (DeviceSettings) MessageNano.mergeFrom(new DeviceSettings(), data);
        }

        public static DeviceSettings parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new DeviceSettings().mergeFrom(input);
        }
    }

    public static final class DnsMxRecord extends MessageNano {
        private static volatile DnsMxRecord[] _emptyArray;
        public String[] mailServerIpAddresses;
        public String mailServerName;

        public static DnsMxRecord[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new DnsMxRecord[Logs.CHROME_LINUX_USER_AGENT];
                    }
                }
            }
            return _emptyArray;
        }

        public DnsMxRecord() {
            clear();
        }

        public DnsMxRecord clear() {
            this.mailServerName = BuildConfig.VERSION_NAME;
            this.mailServerIpAddresses = WireFormatNano.EMPTY_STRING_ARRAY;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (!this.mailServerName.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(Logs.SAFARI_IPHONE_USER_AGENT, this.mailServerName);
            }
            if (this.mailServerIpAddresses != null && this.mailServerIpAddresses.length > 0) {
                for (int i = Logs.CHROME_LINUX_USER_AGENT; i < this.mailServerIpAddresses.length; i += Logs.SAFARI_IPHONE_USER_AGENT) {
                    String element = this.mailServerIpAddresses[i];
                    if (element != null) {
                        output.writeString(2, element);
                    }
                }
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (!this.mailServerName.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(Logs.SAFARI_IPHONE_USER_AGENT, this.mailServerName);
            }
            if (this.mailServerIpAddresses == null || this.mailServerIpAddresses.length <= 0) {
                return size;
            }
            int dataCount = Logs.CHROME_LINUX_USER_AGENT;
            int dataSize = Logs.CHROME_LINUX_USER_AGENT;
            for (int i = Logs.CHROME_LINUX_USER_AGENT; i < this.mailServerIpAddresses.length; i += Logs.SAFARI_IPHONE_USER_AGENT) {
                String element = this.mailServerIpAddresses[i];
                if (element != null) {
                    dataCount += Logs.SAFARI_IPHONE_USER_AGENT;
                    dataSize += CodedOutputByteBufferNano.computeStringSizeNoTag(element);
                }
            }
            return (size + dataSize) + (dataCount * Logs.SAFARI_IPHONE_USER_AGENT);
        }

        public DnsMxRecord mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Logs.CHROME_LINUX_USER_AGENT /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        this.mailServerName = input.readString();
                        continue;
                    case LogSource.GOOGLE_ANALYTICS /*18*/:
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 18);
                        int i = this.mailServerIpAddresses == null ? Logs.CHROME_LINUX_USER_AGENT : this.mailServerIpAddresses.length;
                        String[] newArray = new String[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.mailServerIpAddresses, Logs.CHROME_LINUX_USER_AGENT, newArray, Logs.CHROME_LINUX_USER_AGENT, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = input.readString();
                            input.readTag();
                            i += Logs.SAFARI_IPHONE_USER_AGENT;
                        }
                        newArray[i] = input.readString();
                        this.mailServerIpAddresses = newArray;
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

        public static DnsMxRecord parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (DnsMxRecord) MessageNano.mergeFrom(new DnsMxRecord(), data);
        }

        public static DnsMxRecord parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new DnsMxRecord().mergeFrom(input);
        }
    }

    public static final class DnsMxRecords extends MessageNano {
        private static volatile DnsMxRecords[] _emptyArray;
        public String dnsServerQueried;
        public String domainName;
        public byte[] rawDnsQueryResponse;
        public DnsMxRecord[] servers;

        public static DnsMxRecords[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new DnsMxRecords[Logs.CHROME_LINUX_USER_AGENT];
                    }
                }
            }
            return _emptyArray;
        }

        public DnsMxRecords() {
            clear();
        }

        public DnsMxRecords clear() {
            this.domainName = BuildConfig.VERSION_NAME;
            this.dnsServerQueried = BuildConfig.VERSION_NAME;
            this.servers = DnsMxRecord.emptyArray();
            this.rawDnsQueryResponse = WireFormatNano.EMPTY_BYTES;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (!this.domainName.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(Logs.SAFARI_IPHONE_USER_AGENT, this.domainName);
            }
            if (!this.dnsServerQueried.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(2, this.dnsServerQueried);
            }
            if (this.servers != null && this.servers.length > 0) {
                for (int i = Logs.CHROME_LINUX_USER_AGENT; i < this.servers.length; i += Logs.SAFARI_IPHONE_USER_AGENT) {
                    DnsMxRecord element = this.servers[i];
                    if (element != null) {
                        output.writeMessage(3, element);
                    }
                }
            }
            if (!Arrays.equals(this.rawDnsQueryResponse, WireFormatNano.EMPTY_BYTES)) {
                output.writeBytes(4, this.rawDnsQueryResponse);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (!this.domainName.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(Logs.SAFARI_IPHONE_USER_AGENT, this.domainName);
            }
            if (!this.dnsServerQueried.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(2, this.dnsServerQueried);
            }
            if (this.servers != null && this.servers.length > 0) {
                for (int i = Logs.CHROME_LINUX_USER_AGENT; i < this.servers.length; i += Logs.SAFARI_IPHONE_USER_AGENT) {
                    DnsMxRecord element = this.servers[i];
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(3, element);
                    }
                }
            }
            if (Arrays.equals(this.rawDnsQueryResponse, WireFormatNano.EMPTY_BYTES)) {
                return size;
            }
            return size + CodedOutputByteBufferNano.computeBytesSize(4, this.rawDnsQueryResponse);
        }

        public DnsMxRecords mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Logs.CHROME_LINUX_USER_AGENT /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        this.domainName = input.readString();
                        continue;
                    case LogSource.GOOGLE_ANALYTICS /*18*/:
                        this.dnsServerQueried = input.readString();
                        continue;
                    case LogSource.ANDROID_CAMERA /*26*/:
                        int i;
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 26);
                        if (this.servers == null) {
                            i = Logs.CHROME_LINUX_USER_AGENT;
                        } else {
                            i = this.servers.length;
                        }
                        DnsMxRecord[] newArray = new DnsMxRecord[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.servers, Logs.CHROME_LINUX_USER_AGENT, newArray, Logs.CHROME_LINUX_USER_AGENT, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new DnsMxRecord();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i += Logs.SAFARI_IPHONE_USER_AGENT;
                        }
                        newArray[i] = new DnsMxRecord();
                        input.readMessage(newArray[i]);
                        this.servers = newArray;
                        continue;
                    case LogSource.NOVA /*34*/:
                        this.rawDnsQueryResponse = input.readBytes();
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

        public static DnsMxRecords parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (DnsMxRecords) MessageNano.mergeFrom(new DnsMxRecords(), data);
        }

        public static DnsMxRecords parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new DnsMxRecords().mergeFrom(input);
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
                        _emptyArray = new EventLog[Logs.CHROME_LINUX_USER_AGENT];
                    }
                }
            }
            return _emptyArray;
        }

        public EventLog() {
            clear();
        }

        public EventLog clear() {
            this.tag = Logs.CHROME_LINUX_USER_AGENT;
            this.subTag = BuildConfig.VERSION_NAME;
            this.timeNanos = 0;
            this.appInfo = AppInfo.emptyArray();
            this.data = BuildConfig.VERSION_NAME;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.tag != 0) {
                output.writeInt32(Logs.SAFARI_IPHONE_USER_AGENT, this.tag);
            }
            if (!this.subTag.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(2, this.subTag);
            }
            if (this.timeNanos != 0) {
                output.writeInt64(3, this.timeNanos);
            }
            if (this.appInfo != null && this.appInfo.length > 0) {
                for (int i = Logs.CHROME_LINUX_USER_AGENT; i < this.appInfo.length; i += Logs.SAFARI_IPHONE_USER_AGENT) {
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
                size += CodedOutputByteBufferNano.computeInt32Size(Logs.SAFARI_IPHONE_USER_AGENT, this.tag);
            }
            if (!this.subTag.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(2, this.subTag);
            }
            if (this.timeNanos != 0) {
                size += CodedOutputByteBufferNano.computeInt64Size(3, this.timeNanos);
            }
            if (this.appInfo != null && this.appInfo.length > 0) {
                for (int i = Logs.CHROME_LINUX_USER_AGENT; i < this.appInfo.length; i += Logs.SAFARI_IPHONE_USER_AGENT) {
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
                    case Logs.CHROME_LINUX_USER_AGENT /*0*/:
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
                            i = Logs.CHROME_LINUX_USER_AGENT;
                        } else {
                            i = this.appInfo.length;
                        }
                        AppInfo[] newArray = new AppInfo[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.appInfo, Logs.CHROME_LINUX_USER_AGENT, newArray, Logs.CHROME_LINUX_USER_AGENT, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new AppInfo();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i += Logs.SAFARI_IPHONE_USER_AGENT;
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

    public static final class FilePresence extends MessageNano {
        private static volatile FilePresence[] _emptyArray;
        public String filename;
        public boolean present;
        public byte[] sha256;

        public static FilePresence[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new FilePresence[Logs.CHROME_LINUX_USER_AGENT];
                    }
                }
            }
            return _emptyArray;
        }

        public FilePresence() {
            clear();
        }

        public FilePresence clear() {
            this.filename = BuildConfig.VERSION_NAME;
            this.present = false;
            this.sha256 = WireFormatNano.EMPTY_BYTES;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (!this.filename.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(Logs.SAFARI_IPHONE_USER_AGENT, this.filename);
            }
            if (this.present) {
                output.writeBool(2, this.present);
            }
            if (!Arrays.equals(this.sha256, WireFormatNano.EMPTY_BYTES)) {
                output.writeBytes(3, this.sha256);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (!this.filename.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(Logs.SAFARI_IPHONE_USER_AGENT, this.filename);
            }
            if (this.present) {
                size += CodedOutputByteBufferNano.computeBoolSize(2, this.present);
            }
            if (Arrays.equals(this.sha256, WireFormatNano.EMPTY_BYTES)) {
                return size;
            }
            return size + CodedOutputByteBufferNano.computeBytesSize(3, this.sha256);
        }

        public FilePresence mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Logs.CHROME_LINUX_USER_AGENT /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        this.filename = input.readString();
                        continue;
                    case LogSource.GMS_CORE_PEOPLE /*16*/:
                        this.present = input.readBool();
                        continue;
                    case LogSource.ANDROID_CAMERA /*26*/:
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

        public static FilePresence parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (FilePresence) MessageNano.mergeFrom(new FilePresence(), data);
        }

        public static FilePresence parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new FilePresence().mergeFrom(input);
        }
    }

    public static final class GoogleWebpageInfo extends MessageNano {
        private static volatile GoogleWebpageInfo[] _emptyArray;
        public boolean fetched;
        public boolean lengthExceedsThreshold;
        public int lengthThreshold;

        public static GoogleWebpageInfo[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new GoogleWebpageInfo[Logs.CHROME_LINUX_USER_AGENT];
                    }
                }
            }
            return _emptyArray;
        }

        public GoogleWebpageInfo() {
            clear();
        }

        public GoogleWebpageInfo clear() {
            this.fetched = false;
            this.lengthExceedsThreshold = false;
            this.lengthThreshold = Logs.CHROME_LINUX_USER_AGENT;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.fetched) {
                output.writeBool(Logs.SAFARI_IPHONE_USER_AGENT, this.fetched);
            }
            if (this.lengthExceedsThreshold) {
                output.writeBool(2, this.lengthExceedsThreshold);
            }
            if (this.lengthThreshold != 0) {
                output.writeInt32(3, this.lengthThreshold);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.fetched) {
                size += CodedOutputByteBufferNano.computeBoolSize(Logs.SAFARI_IPHONE_USER_AGENT, this.fetched);
            }
            if (this.lengthExceedsThreshold) {
                size += CodedOutputByteBufferNano.computeBoolSize(2, this.lengthExceedsThreshold);
            }
            if (this.lengthThreshold != 0) {
                return size + CodedOutputByteBufferNano.computeInt32Size(3, this.lengthThreshold);
            }
            return size;
        }

        public GoogleWebpageInfo mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Logs.CHROME_LINUX_USER_AGENT /*0*/:
                        break;
                    case Type.TAP_GET_LINK /*8*/:
                        this.fetched = input.readBool();
                        continue;
                    case LogSource.GMS_CORE_PEOPLE /*16*/:
                        this.lengthExceedsThreshold = input.readBool();
                        continue;
                    case LogSource.LB_C /*24*/:
                        this.lengthThreshold = input.readInt32();
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

        public static GoogleWebpageInfo parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (GoogleWebpageInfo) MessageNano.mergeFrom(new GoogleWebpageInfo(), data);
        }

        public static GoogleWebpageInfo parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new GoogleWebpageInfo().mergeFrom(input);
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
                        _emptyArray = new LogcatEntry[Logs.CHROME_LINUX_USER_AGENT];
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
                output.writeString(Logs.SAFARI_IPHONE_USER_AGENT, this.key);
            }
            if (this.value != null && this.value.length > 0) {
                for (int i = Logs.CHROME_LINUX_USER_AGENT; i < this.value.length; i += Logs.SAFARI_IPHONE_USER_AGENT) {
                    LogcatValue element = this.value[i];
                    if (element != null) {
                        output.writeMessage(2, element);
                    }
                }
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (!this.key.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(Logs.SAFARI_IPHONE_USER_AGENT, this.key);
            }
            if (this.value != null && this.value.length > 0) {
                for (int i = Logs.CHROME_LINUX_USER_AGENT; i < this.value.length; i += Logs.SAFARI_IPHONE_USER_AGENT) {
                    LogcatValue element = this.value[i];
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(2, element);
                    }
                }
            }
            return size;
        }

        public LogcatEntry mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Logs.CHROME_LINUX_USER_AGENT /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        this.key = input.readString();
                        continue;
                    case LogSource.GOOGLE_ANALYTICS /*18*/:
                        int i;
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 18);
                        if (this.value == null) {
                            i = Logs.CHROME_LINUX_USER_AGENT;
                        } else {
                            i = this.value.length;
                        }
                        LogcatValue[] newArray = new LogcatValue[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.value, Logs.CHROME_LINUX_USER_AGENT, newArray, Logs.CHROME_LINUX_USER_AGENT, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new LogcatValue();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i += Logs.SAFARI_IPHONE_USER_AGENT;
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
                        _emptyArray = new LogcatResults[Logs.CHROME_LINUX_USER_AGENT];
                    }
                }
            }
            return _emptyArray;
        }

        public LogcatResults() {
            clear();
        }

        public LogcatResults clear() {
            this.numLogcatLines = Logs.CHROME_LINUX_USER_AGENT;
            this.results = LogcatEntry.emptyArray();
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.numLogcatLines != 0) {
                output.writeInt32(Logs.SAFARI_IPHONE_USER_AGENT, this.numLogcatLines);
            }
            if (this.results != null && this.results.length > 0) {
                for (int i = Logs.CHROME_LINUX_USER_AGENT; i < this.results.length; i += Logs.SAFARI_IPHONE_USER_AGENT) {
                    LogcatEntry element = this.results[i];
                    if (element != null) {
                        output.writeMessage(2, element);
                    }
                }
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.numLogcatLines != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(Logs.SAFARI_IPHONE_USER_AGENT, this.numLogcatLines);
            }
            if (this.results != null && this.results.length > 0) {
                for (int i = Logs.CHROME_LINUX_USER_AGENT; i < this.results.length; i += Logs.SAFARI_IPHONE_USER_AGENT) {
                    LogcatEntry element = this.results[i];
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(2, element);
                    }
                }
            }
            return size;
        }

        public LogcatResults mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Logs.CHROME_LINUX_USER_AGENT /*0*/:
                        break;
                    case Type.TAP_GET_LINK /*8*/:
                        this.numLogcatLines = input.readInt32();
                        continue;
                    case LogSource.GOOGLE_ANALYTICS /*18*/:
                        int i;
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 18);
                        if (this.results == null) {
                            i = Logs.CHROME_LINUX_USER_AGENT;
                        } else {
                            i = this.results.length;
                        }
                        LogcatEntry[] newArray = new LogcatEntry[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.results, Logs.CHROME_LINUX_USER_AGENT, newArray, Logs.CHROME_LINUX_USER_AGENT, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new LogcatEntry();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i += Logs.SAFARI_IPHONE_USER_AGENT;
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
                        _emptyArray = new LogcatValue[Logs.CHROME_LINUX_USER_AGENT];
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
                for (int i = Logs.CHROME_LINUX_USER_AGENT; i < this.appInfo.length; i += Logs.SAFARI_IPHONE_USER_AGENT) {
                    AppInfo element = this.appInfo[i];
                    if (element != null) {
                        output.writeMessage(Logs.SAFARI_IPHONE_USER_AGENT, element);
                    }
                }
            }
            if (!this.line.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(2, this.line);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.appInfo != null && this.appInfo.length > 0) {
                for (int i = Logs.CHROME_LINUX_USER_AGENT; i < this.appInfo.length; i += Logs.SAFARI_IPHONE_USER_AGENT) {
                    AppInfo element = this.appInfo[i];
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(Logs.SAFARI_IPHONE_USER_AGENT, element);
                    }
                }
            }
            if (this.line.equals(BuildConfig.VERSION_NAME)) {
                return size;
            }
            return size + CodedOutputByteBufferNano.computeStringSize(2, this.line);
        }

        public LogcatValue mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Logs.CHROME_LINUX_USER_AGENT /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        int i;
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 10);
                        if (this.appInfo == null) {
                            i = Logs.CHROME_LINUX_USER_AGENT;
                        } else {
                            i = this.appInfo.length;
                        }
                        AppInfo[] newArray = new AppInfo[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.appInfo, Logs.CHROME_LINUX_USER_AGENT, newArray, Logs.CHROME_LINUX_USER_AGENT, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new AppInfo();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i += Logs.SAFARI_IPHONE_USER_AGENT;
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

    public static final class OkHttpSslv3FallbackInfo extends MessageNano {
        private static volatile OkHttpSslv3FallbackInfo[] _emptyArray;
        public boolean initialConnAttempted;
        public boolean initialConnSucceeded;
        public boolean retryAttempted;
        public boolean retrySucceeded;
        public boolean retryUsedModernTls;

        public static OkHttpSslv3FallbackInfo[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new OkHttpSslv3FallbackInfo[Logs.CHROME_LINUX_USER_AGENT];
                    }
                }
            }
            return _emptyArray;
        }

        public OkHttpSslv3FallbackInfo() {
            clear();
        }

        public OkHttpSslv3FallbackInfo clear() {
            this.initialConnAttempted = false;
            this.initialConnSucceeded = false;
            this.retryAttempted = false;
            this.retrySucceeded = false;
            this.retryUsedModernTls = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.initialConnAttempted) {
                output.writeBool(Logs.SAFARI_IPHONE_USER_AGENT, this.initialConnAttempted);
            }
            if (this.initialConnSucceeded) {
                output.writeBool(2, this.initialConnSucceeded);
            }
            if (this.retryAttempted) {
                output.writeBool(3, this.retryAttempted);
            }
            if (this.retrySucceeded) {
                output.writeBool(4, this.retrySucceeded);
            }
            if (this.retryUsedModernTls) {
                output.writeBool(5, this.retryUsedModernTls);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.initialConnAttempted) {
                size += CodedOutputByteBufferNano.computeBoolSize(Logs.SAFARI_IPHONE_USER_AGENT, this.initialConnAttempted);
            }
            if (this.initialConnSucceeded) {
                size += CodedOutputByteBufferNano.computeBoolSize(2, this.initialConnSucceeded);
            }
            if (this.retryAttempted) {
                size += CodedOutputByteBufferNano.computeBoolSize(3, this.retryAttempted);
            }
            if (this.retrySucceeded) {
                size += CodedOutputByteBufferNano.computeBoolSize(4, this.retrySucceeded);
            }
            if (this.retryUsedModernTls) {
                return size + CodedOutputByteBufferNano.computeBoolSize(5, this.retryUsedModernTls);
            }
            return size;
        }

        public OkHttpSslv3FallbackInfo mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Logs.CHROME_LINUX_USER_AGENT /*0*/:
                        break;
                    case Type.TAP_GET_LINK /*8*/:
                        this.initialConnAttempted = input.readBool();
                        continue;
                    case LogSource.GMS_CORE_PEOPLE /*16*/:
                        this.initialConnSucceeded = input.readBool();
                        continue;
                    case LogSource.LB_C /*24*/:
                        this.retryAttempted = input.readBool();
                        continue;
                    case LogSource.SOCIAL /*32*/:
                        this.retrySucceeded = input.readBool();
                        continue;
                    case LogSource.COPRESENCE /*40*/:
                        this.retryUsedModernTls = input.readBool();
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

        public static OkHttpSslv3FallbackInfo parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (OkHttpSslv3FallbackInfo) MessageNano.mergeFrom(new OkHttpSslv3FallbackInfo(), data);
        }

        public static OkHttpSslv3FallbackInfo parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new OkHttpSslv3FallbackInfo().mergeFrom(input);
        }
    }

    public static final class PackageInfo extends MessageNano {
        private static volatile PackageInfo[] _emptyArray;
        public String packageName;
        public int versionCode;
        public String versionName;

        public static PackageInfo[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new PackageInfo[Logs.CHROME_LINUX_USER_AGENT];
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
            this.versionCode = Logs.CHROME_LINUX_USER_AGENT;
            this.versionName = BuildConfig.VERSION_NAME;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (!this.packageName.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(Logs.SAFARI_IPHONE_USER_AGENT, this.packageName);
            }
            if (this.versionCode != 0) {
                output.writeInt32(2, this.versionCode);
            }
            if (!this.versionName.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(3, this.versionName);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (!this.packageName.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(Logs.SAFARI_IPHONE_USER_AGENT, this.packageName);
            }
            if (this.versionCode != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(2, this.versionCode);
            }
            if (this.versionName.equals(BuildConfig.VERSION_NAME)) {
                return size;
            }
            return size + CodedOutputByteBufferNano.computeStringSize(3, this.versionName);
        }

        public PackageInfo mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Logs.CHROME_LINUX_USER_AGENT /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        this.packageName = input.readString();
                        continue;
                    case LogSource.GMS_CORE_PEOPLE /*16*/:
                        this.versionCode = input.readInt32();
                        continue;
                    case LogSource.ANDROID_CAMERA /*26*/:
                        this.versionName = input.readString();
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

        public static PackageInfo parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (PackageInfo) MessageNano.mergeFrom(new PackageInfo(), data);
        }

        public static PackageInfo parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new PackageInfo().mergeFrom(input);
        }
    }

    public static final class ProxyInfo extends MessageNano {
        private static volatile ProxyInfo[] _emptyArray;
        public String address;
        public boolean localIp;
        public boolean present;

        public static ProxyInfo[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new ProxyInfo[Logs.CHROME_LINUX_USER_AGENT];
                    }
                }
            }
            return _emptyArray;
        }

        public ProxyInfo() {
            clear();
        }

        public ProxyInfo clear() {
            this.present = false;
            this.localIp = false;
            this.address = BuildConfig.VERSION_NAME;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.present) {
                output.writeBool(Logs.SAFARI_IPHONE_USER_AGENT, this.present);
            }
            if (this.localIp) {
                output.writeBool(2, this.localIp);
            }
            if (!this.address.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(3, this.address);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.present) {
                size += CodedOutputByteBufferNano.computeBoolSize(Logs.SAFARI_IPHONE_USER_AGENT, this.present);
            }
            if (this.localIp) {
                size += CodedOutputByteBufferNano.computeBoolSize(2, this.localIp);
            }
            if (this.address.equals(BuildConfig.VERSION_NAME)) {
                return size;
            }
            return size + CodedOutputByteBufferNano.computeStringSize(3, this.address);
        }

        public ProxyInfo mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Logs.CHROME_LINUX_USER_AGENT /*0*/:
                        break;
                    case Type.TAP_GET_LINK /*8*/:
                        this.present = input.readBool();
                        continue;
                    case LogSource.GMS_CORE_PEOPLE /*16*/:
                        this.localIp = input.readBool();
                        continue;
                    case LogSource.ANDROID_CAMERA /*26*/:
                        this.address = input.readString();
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

        public static ProxyInfo parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (ProxyInfo) MessageNano.mergeFrom(new ProxyInfo(), data);
        }

        public static ProxyInfo parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new ProxyInfo().mergeFrom(input);
        }
    }

    public static final class RunSettings extends MessageNano {
        private static volatile RunSettings[] _emptyArray;
        public long currentTimeMs;
        public long durationSinceLastRunMs;
        public long executionTimeMs;
        public long wakeIntervalMs;

        public static RunSettings[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new RunSettings[Logs.CHROME_LINUX_USER_AGENT];
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
            this.currentTimeMs = 0;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.wakeIntervalMs != 0) {
                output.writeInt64(Logs.SAFARI_IPHONE_USER_AGENT, this.wakeIntervalMs);
            }
            if (this.durationSinceLastRunMs != 0) {
                output.writeInt64(2, this.durationSinceLastRunMs);
            }
            if (this.executionTimeMs != 0) {
                output.writeInt64(3, this.executionTimeMs);
            }
            if (this.currentTimeMs != 0) {
                output.writeInt64(4, this.currentTimeMs);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.wakeIntervalMs != 0) {
                size += CodedOutputByteBufferNano.computeInt64Size(Logs.SAFARI_IPHONE_USER_AGENT, this.wakeIntervalMs);
            }
            if (this.durationSinceLastRunMs != 0) {
                size += CodedOutputByteBufferNano.computeInt64Size(2, this.durationSinceLastRunMs);
            }
            if (this.executionTimeMs != 0) {
                size += CodedOutputByteBufferNano.computeInt64Size(3, this.executionTimeMs);
            }
            if (this.currentTimeMs != 0) {
                return size + CodedOutputByteBufferNano.computeInt64Size(4, this.currentTimeMs);
            }
            return size;
        }

        public RunSettings mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Logs.CHROME_LINUX_USER_AGENT /*0*/:
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
                        this.currentTimeMs = input.readInt64();
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

    public static final class SdCardInfo extends MessageNano {
        private static volatile SdCardInfo[] _emptyArray;
        public String jpegFileName;
        public boolean jpegMissing;
        public boolean jpegNewlyTampered;
        public boolean jpegTampered;
        public int jpegTamperedBytes;
        public long jpegWrongLength;

        public static SdCardInfo[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new SdCardInfo[Logs.CHROME_LINUX_USER_AGENT];
                    }
                }
            }
            return _emptyArray;
        }

        public SdCardInfo() {
            clear();
        }

        public SdCardInfo clear() {
            this.jpegMissing = false;
            this.jpegTampered = false;
            this.jpegWrongLength = 0;
            this.jpegTamperedBytes = Logs.CHROME_LINUX_USER_AGENT;
            this.jpegNewlyTampered = false;
            this.jpegFileName = BuildConfig.VERSION_NAME;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.jpegMissing) {
                output.writeBool(Logs.SAFARI_IPHONE_USER_AGENT, this.jpegMissing);
            }
            if (this.jpegTampered) {
                output.writeBool(2, this.jpegTampered);
            }
            if (this.jpegWrongLength != 0) {
                output.writeInt64(3, this.jpegWrongLength);
            }
            if (this.jpegTamperedBytes != 0) {
                output.writeInt32(4, this.jpegTamperedBytes);
            }
            if (this.jpegNewlyTampered) {
                output.writeBool(5, this.jpegNewlyTampered);
            }
            if (!this.jpegFileName.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(6, this.jpegFileName);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.jpegMissing) {
                size += CodedOutputByteBufferNano.computeBoolSize(Logs.SAFARI_IPHONE_USER_AGENT, this.jpegMissing);
            }
            if (this.jpegTampered) {
                size += CodedOutputByteBufferNano.computeBoolSize(2, this.jpegTampered);
            }
            if (this.jpegWrongLength != 0) {
                size += CodedOutputByteBufferNano.computeInt64Size(3, this.jpegWrongLength);
            }
            if (this.jpegTamperedBytes != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(4, this.jpegTamperedBytes);
            }
            if (this.jpegNewlyTampered) {
                size += CodedOutputByteBufferNano.computeBoolSize(5, this.jpegNewlyTampered);
            }
            if (this.jpegFileName.equals(BuildConfig.VERSION_NAME)) {
                return size;
            }
            return size + CodedOutputByteBufferNano.computeStringSize(6, this.jpegFileName);
        }

        public SdCardInfo mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Logs.CHROME_LINUX_USER_AGENT /*0*/:
                        break;
                    case Type.TAP_GET_LINK /*8*/:
                        this.jpegMissing = input.readBool();
                        continue;
                    case LogSource.GMS_CORE_PEOPLE /*16*/:
                        this.jpegTampered = input.readBool();
                        continue;
                    case LogSource.LB_C /*24*/:
                        this.jpegWrongLength = input.readInt64();
                        continue;
                    case LogSource.SOCIAL /*32*/:
                        this.jpegTamperedBytes = input.readInt32();
                        continue;
                    case LogSource.COPRESENCE /*40*/:
                        this.jpegNewlyTampered = input.readBool();
                        continue;
                    case LogSource.HANGOUT_LOG_REQUEST /*50*/:
                        this.jpegFileName = input.readString();
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

        public static SdCardInfo parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (SdCardInfo) MessageNano.mergeFrom(new SdCardInfo(), data);
        }

        public static SdCardInfo parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new SdCardInfo().mergeFrom(input);
        }
    }

    public static final class SeLinuxInfo extends MessageNano {
        private static volatile SeLinuxInfo[] _emptyArray;
        public boolean enforcing;
        public boolean present;

        public static SeLinuxInfo[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new SeLinuxInfo[Logs.CHROME_LINUX_USER_AGENT];
                    }
                }
            }
            return _emptyArray;
        }

        public SeLinuxInfo() {
            clear();
        }

        public SeLinuxInfo clear() {
            this.present = false;
            this.enforcing = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.present) {
                output.writeBool(Logs.SAFARI_IPHONE_USER_AGENT, this.present);
            }
            if (this.enforcing) {
                output.writeBool(2, this.enforcing);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.present) {
                size += CodedOutputByteBufferNano.computeBoolSize(Logs.SAFARI_IPHONE_USER_AGENT, this.present);
            }
            if (this.enforcing) {
                return size + CodedOutputByteBufferNano.computeBoolSize(2, this.enforcing);
            }
            return size;
        }

        public SeLinuxInfo mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Logs.CHROME_LINUX_USER_AGENT /*0*/:
                        break;
                    case Type.TAP_GET_LINK /*8*/:
                        this.present = input.readBool();
                        continue;
                    case LogSource.GMS_CORE_PEOPLE /*16*/:
                        this.enforcing = input.readBool();
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

        public static SeLinuxInfo parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (SeLinuxInfo) MessageNano.mergeFrom(new SeLinuxInfo(), data);
        }

        public static SeLinuxInfo parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new SeLinuxInfo().mergeFrom(input);
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
                            _emptyArray = new FileInfo[Logs.CHROME_LINUX_USER_AGENT];
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
                    output.writeString(Logs.SAFARI_IPHONE_USER_AGENT, this.path);
                }
                if (!Arrays.equals(this.sha256, WireFormatNano.EMPTY_BYTES)) {
                    output.writeBytes(2, this.sha256);
                }
                super.writeTo(output);
            }

            protected int computeSerializedSize() {
                int size = super.computeSerializedSize();
                if (!this.path.equals(BuildConfig.VERSION_NAME)) {
                    size += CodedOutputByteBufferNano.computeStringSize(Logs.SAFARI_IPHONE_USER_AGENT, this.path);
                }
                if (Arrays.equals(this.sha256, WireFormatNano.EMPTY_BYTES)) {
                    return size;
                }
                return size + CodedOutputByteBufferNano.computeBytesSize(2, this.sha256);
            }

            public FileInfo mergeFrom(CodedInputByteBufferNano input) throws IOException {
                while (true) {
                    int tag = input.readTag();
                    switch (tag) {
                        case Logs.CHROME_LINUX_USER_AGENT /*0*/:
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
                        _emptyArray = new SetuidFileInfo[Logs.CHROME_LINUX_USER_AGENT];
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
                output.writeBool(Logs.SAFARI_IPHONE_USER_AGENT, this.couldNotCheck);
            }
            if (this.numberOfSetuidFiles != 0) {
                output.writeInt64(2, this.numberOfSetuidFiles);
            }
            if (this.filePaths != null && this.filePaths.length > 0) {
                for (i = Logs.CHROME_LINUX_USER_AGENT; i < this.filePaths.length; i += Logs.SAFARI_IPHONE_USER_AGENT) {
                    String element = this.filePaths[i];
                    if (element != null) {
                        output.writeString(3, element);
                    }
                }
            }
            if (this.fileInfo != null && this.fileInfo.length > 0) {
                for (i = Logs.CHROME_LINUX_USER_AGENT; i < this.fileInfo.length; i += Logs.SAFARI_IPHONE_USER_AGENT) {
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
                size += CodedOutputByteBufferNano.computeBoolSize(Logs.SAFARI_IPHONE_USER_AGENT, this.couldNotCheck);
            }
            if (this.numberOfSetuidFiles != 0) {
                size += CodedOutputByteBufferNano.computeInt64Size(2, this.numberOfSetuidFiles);
            }
            if (this.filePaths != null && this.filePaths.length > 0) {
                int dataCount = Logs.CHROME_LINUX_USER_AGENT;
                int dataSize = Logs.CHROME_LINUX_USER_AGENT;
                for (i = Logs.CHROME_LINUX_USER_AGENT; i < this.filePaths.length; i += Logs.SAFARI_IPHONE_USER_AGENT) {
                    String element = this.filePaths[i];
                    if (element != null) {
                        dataCount += Logs.SAFARI_IPHONE_USER_AGENT;
                        dataSize += CodedOutputByteBufferNano.computeStringSizeNoTag(element);
                    }
                }
                size = (size + dataSize) + (dataCount * Logs.SAFARI_IPHONE_USER_AGENT);
            }
            if (this.fileInfo != null && this.fileInfo.length > 0) {
                for (i = Logs.CHROME_LINUX_USER_AGENT; i < this.fileInfo.length; i += Logs.SAFARI_IPHONE_USER_AGENT) {
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
                    case Logs.CHROME_LINUX_USER_AGENT /*0*/:
                        break;
                    case Type.TAP_GET_LINK /*8*/:
                        this.couldNotCheck = input.readBool();
                        continue;
                    case LogSource.GMS_CORE_PEOPLE /*16*/:
                        this.numberOfSetuidFiles = input.readInt64();
                        continue;
                    case LogSource.ANDROID_CAMERA /*26*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 26);
                        i = this.filePaths == null ? Logs.CHROME_LINUX_USER_AGENT : this.filePaths.length;
                        String[] newArray = new String[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.filePaths, Logs.CHROME_LINUX_USER_AGENT, newArray, Logs.CHROME_LINUX_USER_AGENT, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = input.readString();
                            input.readTag();
                            i += Logs.SAFARI_IPHONE_USER_AGENT;
                        }
                        newArray[i] = input.readString();
                        this.filePaths = newArray;
                        continue;
                    case LogSource.NOVA /*34*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 34);
                        if (this.fileInfo == null) {
                            i = Logs.CHROME_LINUX_USER_AGENT;
                        } else {
                            i = this.fileInfo.length;
                        }
                        FileInfo[] newArray2 = new FileInfo[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.fileInfo, Logs.CHROME_LINUX_USER_AGENT, newArray2, Logs.CHROME_LINUX_USER_AGENT, i);
                        }
                        while (i < newArray2.length - 1) {
                            newArray2[i] = new FileInfo();
                            input.readMessage(newArray2[i]);
                            input.readTag();
                            i += Logs.SAFARI_IPHONE_USER_AGENT;
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

    public static final class SnetLog extends MessageNano {
        private static volatile SnetLog[] _emptyArray;
        public AppsList appsList;
        public AttestationResult attestationResult;
        public String buildFingerprint;
        public CaptivePortalTestResults captivePortalTestResults;
        public ConnectionInfo connectionInfo;
        public AppInfo[] deactivatedDeviceAdmins;
        public String debugStatus;
        public DnsMxRecords[] dnsMxRecords;
        public EventLog[] events;
        public byte[] featuresBitField;
        public FilePresence[] files;
        public long firstEventLogTimeNano;
        public AppInfo gmsCoreInfo;
        public boolean gmsCoreUuidUsed;
        public String googleWebpageHtml;
        public GoogleWebpageInfo googleWebpageInfo;
        public String[] invalidBlacklistedDeviceAdmins;
        public boolean isSidewinderDevice;
        public String[] jarExceptions;
        public long jarVersion;
        public String locale;
        public LogcatResults logcatResults;
        public PackageInfo preferredBrowser;
        public PackageInfo preferredInstaller;
        public String proxyAddress;
        public ProxyInfo proxyInfo;
        public RunSettings runSettings;
        public boolean sdCardTampered;
        public SdCardInfo sdCardTamperedInfo;
        public SeLinuxInfo seLinuxInfo;
        public DeviceSettings settings;
        public SetuidFileInfo setuidFileInfo;
        public String sharedUuid;
        public String signalTagsWhitelist;
        public SslHandshakeInfo[] sslHandshakeList;
        public SslRedirectInfo[] sslRedirectList;
        public OkHttpSslv3FallbackInfo sslv3Fallback;
        public SystemPartitionFileInfo systemPartitionFileInfo;
        public String uuid;

        public static SnetLog[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new SnetLog[Logs.CHROME_LINUX_USER_AGENT];
                    }
                }
            }
            return _emptyArray;
        }

        public SnetLog() {
            clear();
        }

        public SnetLog clear() {
            this.jarVersion = 0;
            this.uuid = BuildConfig.VERSION_NAME;
            this.gmsCoreUuidUsed = false;
            this.sharedUuid = BuildConfig.VERSION_NAME;
            this.jarExceptions = WireFormatNano.EMPTY_STRING_ARRAY;
            this.featuresBitField = WireFormatNano.EMPTY_BYTES;
            this.debugStatus = BuildConfig.VERSION_NAME;
            this.runSettings = null;
            this.signalTagsWhitelist = BuildConfig.VERSION_NAME;
            this.isSidewinderDevice = false;
            this.locale = BuildConfig.VERSION_NAME;
            this.preferredBrowser = null;
            this.preferredInstaller = null;
            this.files = FilePresence.emptyArray();
            this.settings = null;
            this.setuidFileInfo = null;
            this.seLinuxInfo = null;
            this.systemPartitionFileInfo = null;
            this.gmsCoreInfo = null;
            this.connectionInfo = null;
            this.sslRedirectList = SslRedirectInfo.emptyArray();
            this.sslHandshakeList = SslHandshakeInfo.emptyArray();
            this.googleWebpageHtml = BuildConfig.VERSION_NAME;
            this.googleWebpageInfo = null;
            this.sslv3Fallback = null;
            this.dnsMxRecords = DnsMxRecords.emptyArray();
            this.captivePortalTestResults = null;
            this.proxyAddress = BuildConfig.VERSION_NAME;
            this.proxyInfo = null;
            this.firstEventLogTimeNano = 0;
            this.events = EventLog.emptyArray();
            this.sdCardTampered = false;
            this.sdCardTamperedInfo = null;
            this.appsList = null;
            this.deactivatedDeviceAdmins = AppInfo.emptyArray();
            this.invalidBlacklistedDeviceAdmins = WireFormatNano.EMPTY_STRING_ARRAY;
            this.logcatResults = null;
            this.buildFingerprint = BuildConfig.VERSION_NAME;
            this.attestationResult = null;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            int i;
            String element;
            if (this.jarVersion != 0) {
                output.writeInt64(Logs.SAFARI_IPHONE_USER_AGENT, this.jarVersion);
            }
            if (this.jarExceptions != null && this.jarExceptions.length > 0) {
                for (i = Logs.CHROME_LINUX_USER_AGENT; i < this.jarExceptions.length; i += Logs.SAFARI_IPHONE_USER_AGENT) {
                    element = this.jarExceptions[i];
                    if (element != null) {
                        output.writeString(2, element);
                    }
                }
            }
            if (!this.locale.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(3, this.locale);
            }
            if (this.preferredBrowser != null) {
                output.writeMessage(4, this.preferredBrowser);
            }
            if (this.preferredInstaller != null) {
                output.writeMessage(5, this.preferredInstaller);
            }
            if (this.files != null && this.files.length > 0) {
                for (i = Logs.CHROME_LINUX_USER_AGENT; i < this.files.length; i += Logs.SAFARI_IPHONE_USER_AGENT) {
                    FilePresence element2 = this.files[i];
                    if (element2 != null) {
                        output.writeMessage(6, element2);
                    }
                }
            }
            if (this.settings != null) {
                output.writeMessage(7, this.settings);
            }
            if (this.connectionInfo != null) {
                output.writeMessage(8, this.connectionInfo);
            }
            if (this.sslRedirectList != null && this.sslRedirectList.length > 0) {
                for (i = Logs.CHROME_LINUX_USER_AGENT; i < this.sslRedirectList.length; i += Logs.SAFARI_IPHONE_USER_AGENT) {
                    SslRedirectInfo element3 = this.sslRedirectList[i];
                    if (element3 != null) {
                        output.writeMessage(9, element3);
                    }
                }
            }
            if (this.sslHandshakeList != null && this.sslHandshakeList.length > 0) {
                for (i = Logs.CHROME_LINUX_USER_AGENT; i < this.sslHandshakeList.length; i += Logs.SAFARI_IPHONE_USER_AGENT) {
                    SslHandshakeInfo element4 = this.sslHandshakeList[i];
                    if (element4 != null) {
                        output.writeMessage(10, element4);
                    }
                }
            }
            if (this.events != null && this.events.length > 0) {
                for (i = Logs.CHROME_LINUX_USER_AGENT; i < this.events.length; i += Logs.SAFARI_IPHONE_USER_AGENT) {
                    EventLog element5 = this.events[i];
                    if (element5 != null) {
                        output.writeMessage(11, element5);
                    }
                }
            }
            if (!this.proxyAddress.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(12, this.proxyAddress);
            }
            if (this.setuidFileInfo != null) {
                output.writeMessage(13, this.setuidFileInfo);
            }
            if (this.seLinuxInfo != null) {
                output.writeMessage(14, this.seLinuxInfo);
            }
            if (!this.uuid.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(15, this.uuid);
            }
            if (this.gmsCoreUuidUsed) {
                output.writeBool(16, this.gmsCoreUuidUsed);
            }
            if (this.sdCardTampered) {
                output.writeBool(17, this.sdCardTampered);
            }
            if (this.appsList != null) {
                output.writeMessage(18, this.appsList);
            }
            if (!this.googleWebpageHtml.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(19, this.googleWebpageHtml);
            }
            if (this.sslv3Fallback != null) {
                output.writeMessage(20, this.sslv3Fallback);
            }
            if (this.dnsMxRecords != null && this.dnsMxRecords.length > 0) {
                for (i = Logs.CHROME_LINUX_USER_AGENT; i < this.dnsMxRecords.length; i += Logs.SAFARI_IPHONE_USER_AGENT) {
                    DnsMxRecords element6 = this.dnsMxRecords[i];
                    if (element6 != null) {
                        output.writeMessage(21, element6);
                    }
                }
            }
            if (!Arrays.equals(this.featuresBitField, WireFormatNano.EMPTY_BYTES)) {
                output.writeBytes(22, this.featuresBitField);
            }
            if (this.sdCardTamperedInfo != null) {
                output.writeMessage(23, this.sdCardTamperedInfo);
            }
            if (!this.debugStatus.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(24, this.debugStatus);
            }
            if (this.runSettings != null) {
                output.writeMessage(25, this.runSettings);
            }
            if (this.googleWebpageInfo != null) {
                output.writeMessage(26, this.googleWebpageInfo);
            }
            if (this.captivePortalTestResults != null) {
                output.writeMessage(27, this.captivePortalTestResults);
            }
            if (this.proxyInfo != null) {
                output.writeMessage(28, this.proxyInfo);
            }
            if (this.logcatResults != null) {
                output.writeMessage(29, this.logcatResults);
            }
            if (this.systemPartitionFileInfo != null) {
                output.writeMessage(30, this.systemPartitionFileInfo);
            }
            if (!this.buildFingerprint.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(31, this.buildFingerprint);
            }
            if (!this.signalTagsWhitelist.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(32, this.signalTagsWhitelist);
            }
            if (this.attestationResult != null) {
                output.writeMessage(33, this.attestationResult);
            }
            if (!this.sharedUuid.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(34, this.sharedUuid);
            }
            if (this.gmsCoreInfo != null) {
                output.writeMessage(35, this.gmsCoreInfo);
            }
            if (this.firstEventLogTimeNano != 0) {
                output.writeInt64(36, this.firstEventLogTimeNano);
            }
            if (this.isSidewinderDevice) {
                output.writeBool(37, this.isSidewinderDevice);
            }
            if (this.deactivatedDeviceAdmins != null && this.deactivatedDeviceAdmins.length > 0) {
                for (i = Logs.CHROME_LINUX_USER_AGENT; i < this.deactivatedDeviceAdmins.length; i += Logs.SAFARI_IPHONE_USER_AGENT) {
                    AppInfo element7 = this.deactivatedDeviceAdmins[i];
                    if (element7 != null) {
                        output.writeMessage(38, element7);
                    }
                }
            }
            if (this.invalidBlacklistedDeviceAdmins != null && this.invalidBlacklistedDeviceAdmins.length > 0) {
                for (i = Logs.CHROME_LINUX_USER_AGENT; i < this.invalidBlacklistedDeviceAdmins.length; i += Logs.SAFARI_IPHONE_USER_AGENT) {
                    element = this.invalidBlacklistedDeviceAdmins[i];
                    if (element != null) {
                        output.writeString(39, element);
                    }
                }
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int dataCount;
            int dataSize;
            int i;
            String element;
            int size = super.computeSerializedSize();
            if (this.jarVersion != 0) {
                size += CodedOutputByteBufferNano.computeInt64Size(Logs.SAFARI_IPHONE_USER_AGENT, this.jarVersion);
            }
            if (this.jarExceptions != null && this.jarExceptions.length > 0) {
                dataCount = Logs.CHROME_LINUX_USER_AGENT;
                dataSize = Logs.CHROME_LINUX_USER_AGENT;
                for (i = Logs.CHROME_LINUX_USER_AGENT; i < this.jarExceptions.length; i += Logs.SAFARI_IPHONE_USER_AGENT) {
                    element = this.jarExceptions[i];
                    if (element != null) {
                        dataCount += Logs.SAFARI_IPHONE_USER_AGENT;
                        dataSize += CodedOutputByteBufferNano.computeStringSizeNoTag(element);
                    }
                }
                size = (size + dataSize) + (dataCount * Logs.SAFARI_IPHONE_USER_AGENT);
            }
            if (!this.locale.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(3, this.locale);
            }
            if (this.preferredBrowser != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(4, this.preferredBrowser);
            }
            if (this.preferredInstaller != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(5, this.preferredInstaller);
            }
            if (this.files != null && this.files.length > 0) {
                for (i = Logs.CHROME_LINUX_USER_AGENT; i < this.files.length; i += Logs.SAFARI_IPHONE_USER_AGENT) {
                    FilePresence element2 = this.files[i];
                    if (element2 != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(6, element2);
                    }
                }
            }
            if (this.settings != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(7, this.settings);
            }
            if (this.connectionInfo != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(8, this.connectionInfo);
            }
            if (this.sslRedirectList != null && this.sslRedirectList.length > 0) {
                for (i = Logs.CHROME_LINUX_USER_AGENT; i < this.sslRedirectList.length; i += Logs.SAFARI_IPHONE_USER_AGENT) {
                    SslRedirectInfo element3 = this.sslRedirectList[i];
                    if (element3 != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(9, element3);
                    }
                }
            }
            if (this.sslHandshakeList != null && this.sslHandshakeList.length > 0) {
                for (i = Logs.CHROME_LINUX_USER_AGENT; i < this.sslHandshakeList.length; i += Logs.SAFARI_IPHONE_USER_AGENT) {
                    SslHandshakeInfo element4 = this.sslHandshakeList[i];
                    if (element4 != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(10, element4);
                    }
                }
            }
            if (this.events != null && this.events.length > 0) {
                for (i = Logs.CHROME_LINUX_USER_AGENT; i < this.events.length; i += Logs.SAFARI_IPHONE_USER_AGENT) {
                    EventLog element5 = this.events[i];
                    if (element5 != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(11, element5);
                    }
                }
            }
            if (!this.proxyAddress.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(12, this.proxyAddress);
            }
            if (this.setuidFileInfo != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(13, this.setuidFileInfo);
            }
            if (this.seLinuxInfo != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(14, this.seLinuxInfo);
            }
            if (!this.uuid.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(15, this.uuid);
            }
            if (this.gmsCoreUuidUsed) {
                size += CodedOutputByteBufferNano.computeBoolSize(16, this.gmsCoreUuidUsed);
            }
            if (this.sdCardTampered) {
                size += CodedOutputByteBufferNano.computeBoolSize(17, this.sdCardTampered);
            }
            if (this.appsList != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(18, this.appsList);
            }
            if (!this.googleWebpageHtml.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(19, this.googleWebpageHtml);
            }
            if (this.sslv3Fallback != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(20, this.sslv3Fallback);
            }
            if (this.dnsMxRecords != null && this.dnsMxRecords.length > 0) {
                for (i = Logs.CHROME_LINUX_USER_AGENT; i < this.dnsMxRecords.length; i += Logs.SAFARI_IPHONE_USER_AGENT) {
                    DnsMxRecords element6 = this.dnsMxRecords[i];
                    if (element6 != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(21, element6);
                    }
                }
            }
            if (!Arrays.equals(this.featuresBitField, WireFormatNano.EMPTY_BYTES)) {
                size += CodedOutputByteBufferNano.computeBytesSize(22, this.featuresBitField);
            }
            if (this.sdCardTamperedInfo != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(23, this.sdCardTamperedInfo);
            }
            if (!this.debugStatus.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(24, this.debugStatus);
            }
            if (this.runSettings != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(25, this.runSettings);
            }
            if (this.googleWebpageInfo != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(26, this.googleWebpageInfo);
            }
            if (this.captivePortalTestResults != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(27, this.captivePortalTestResults);
            }
            if (this.proxyInfo != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(28, this.proxyInfo);
            }
            if (this.logcatResults != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(29, this.logcatResults);
            }
            if (this.systemPartitionFileInfo != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(30, this.systemPartitionFileInfo);
            }
            if (!this.buildFingerprint.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(31, this.buildFingerprint);
            }
            if (!this.signalTagsWhitelist.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(32, this.signalTagsWhitelist);
            }
            if (this.attestationResult != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(33, this.attestationResult);
            }
            if (!this.sharedUuid.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(34, this.sharedUuid);
            }
            if (this.gmsCoreInfo != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(35, this.gmsCoreInfo);
            }
            if (this.firstEventLogTimeNano != 0) {
                size += CodedOutputByteBufferNano.computeInt64Size(36, this.firstEventLogTimeNano);
            }
            if (this.isSidewinderDevice) {
                size += CodedOutputByteBufferNano.computeBoolSize(37, this.isSidewinderDevice);
            }
            if (this.deactivatedDeviceAdmins != null && this.deactivatedDeviceAdmins.length > 0) {
                for (i = Logs.CHROME_LINUX_USER_AGENT; i < this.deactivatedDeviceAdmins.length; i += Logs.SAFARI_IPHONE_USER_AGENT) {
                    AppInfo element7 = this.deactivatedDeviceAdmins[i];
                    if (element7 != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(38, element7);
                    }
                }
            }
            if (this.invalidBlacklistedDeviceAdmins == null || this.invalidBlacklistedDeviceAdmins.length <= 0) {
                return size;
            }
            dataCount = Logs.CHROME_LINUX_USER_AGENT;
            dataSize = Logs.CHROME_LINUX_USER_AGENT;
            for (i = Logs.CHROME_LINUX_USER_AGENT; i < this.invalidBlacklistedDeviceAdmins.length; i += Logs.SAFARI_IPHONE_USER_AGENT) {
                element = this.invalidBlacklistedDeviceAdmins[i];
                if (element != null) {
                    dataCount += Logs.SAFARI_IPHONE_USER_AGENT;
                    dataSize += CodedOutputByteBufferNano.computeStringSizeNoTag(element);
                }
            }
            return (size + dataSize) + (dataCount * 2);
        }

        public SnetLog mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                int arrayLength;
                int i;
                String[] newArray;
                switch (tag) {
                    case Logs.CHROME_LINUX_USER_AGENT /*0*/:
                        break;
                    case Type.TAP_GET_LINK /*8*/:
                        this.jarVersion = input.readInt64();
                        continue;
                    case LogSource.GOOGLE_ANALYTICS /*18*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 18);
                        i = this.jarExceptions == null ? Logs.CHROME_LINUX_USER_AGENT : this.jarExceptions.length;
                        newArray = new String[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.jarExceptions, Logs.CHROME_LINUX_USER_AGENT, newArray, Logs.CHROME_LINUX_USER_AGENT, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = input.readString();
                            input.readTag();
                            i += Logs.SAFARI_IPHONE_USER_AGENT;
                        }
                        newArray[i] = input.readString();
                        this.jarExceptions = newArray;
                        continue;
                    case LogSource.ANDROID_CAMERA /*26*/:
                        this.locale = input.readString();
                        continue;
                    case LogSource.NOVA /*34*/:
                        if (this.preferredBrowser == null) {
                            this.preferredBrowser = new PackageInfo();
                        }
                        input.readMessage(this.preferredBrowser);
                        continue;
                    case LogSource.PHOTOS /*42*/:
                        if (this.preferredInstaller == null) {
                            this.preferredInstaller = new PackageInfo();
                        }
                        input.readMessage(this.preferredInstaller);
                        continue;
                    case LogSource.HANGOUT_LOG_REQUEST /*50*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 50);
                        if (this.files == null) {
                            i = Logs.CHROME_LINUX_USER_AGENT;
                        } else {
                            i = this.files.length;
                        }
                        FilePresence[] newArray2 = new FilePresence[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.files, Logs.CHROME_LINUX_USER_AGENT, newArray2, Logs.CHROME_LINUX_USER_AGENT, i);
                        }
                        while (i < newArray2.length - 1) {
                            newArray2[i] = new FilePresence();
                            input.readMessage(newArray2[i]);
                            input.readTag();
                            i += Logs.SAFARI_IPHONE_USER_AGENT;
                        }
                        newArray2[i] = new FilePresence();
                        input.readMessage(newArray2[i]);
                        this.files = newArray2;
                        continue;
                    case LogSource.SLIDES /*58*/:
                        if (this.settings == null) {
                            this.settings = new DeviceSettings();
                        }
                        input.readMessage(this.settings);
                        continue;
                    case LogSource.WIFI_ASSISTANT /*66*/:
                        if (this.connectionInfo == null) {
                            this.connectionInfo = new ConnectionInfo();
                        }
                        input.readMessage(this.connectionInfo);
                        continue;
                    case LogSource.MOVIES /*74*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 74);
                        if (this.sslRedirectList == null) {
                            i = Logs.CHROME_LINUX_USER_AGENT;
                        } else {
                            i = this.sslRedirectList.length;
                        }
                        SslRedirectInfo[] newArray3 = new SslRedirectInfo[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.sslRedirectList, Logs.CHROME_LINUX_USER_AGENT, newArray3, Logs.CHROME_LINUX_USER_AGENT, i);
                        }
                        while (i < newArray3.length - 1) {
                            newArray3[i] = new SslRedirectInfo();
                            input.readMessage(newArray3[i]);
                            input.readTag();
                            i += Logs.SAFARI_IPHONE_USER_AGENT;
                        }
                        newArray3[i] = new SslRedirectInfo();
                        input.readMessage(newArray3[i]);
                        this.sslRedirectList = newArray3;
                        continue;
                    case LogSource.GOOGLE_EXPRESS /*82*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 82);
                        if (this.sslHandshakeList == null) {
                            i = Logs.CHROME_LINUX_USER_AGENT;
                        } else {
                            i = this.sslHandshakeList.length;
                        }
                        SslHandshakeInfo[] newArray4 = new SslHandshakeInfo[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.sslHandshakeList, Logs.CHROME_LINUX_USER_AGENT, newArray4, Logs.CHROME_LINUX_USER_AGENT, i);
                        }
                        while (i < newArray4.length - 1) {
                            newArray4[i] = new SslHandshakeInfo();
                            input.readMessage(newArray4[i]);
                            input.readTag();
                            i += Logs.SAFARI_IPHONE_USER_AGENT;
                        }
                        newArray4[i] = new SslHandshakeInfo();
                        input.readMessage(newArray4[i]);
                        this.sslHandshakeList = newArray4;
                        continue;
                    case LogSource.TRON_COUNTERS /*90*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 90);
                        if (this.events == null) {
                            i = Logs.CHROME_LINUX_USER_AGENT;
                        } else {
                            i = this.events.length;
                        }
                        EventLog[] newArray5 = new EventLog[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.events, Logs.CHROME_LINUX_USER_AGENT, newArray5, Logs.CHROME_LINUX_USER_AGENT, i);
                        }
                        while (i < newArray5.length - 1) {
                            newArray5[i] = new EventLog();
                            input.readMessage(newArray5[i]);
                            input.readTag();
                            i += Logs.SAFARI_IPHONE_USER_AGENT;
                        }
                        newArray5[i] = new EventLog();
                        input.readMessage(newArray5[i]);
                        this.events = newArray5;
                        continue;
                    case LogSource.TACHYON_LOG_REQUEST /*98*/:
                        this.proxyAddress = input.readString();
                        continue;
                    case LogSource.ADSHIELD /*106*/:
                        if (this.setuidFileInfo == null) {
                            this.setuidFileInfo = new SetuidFileInfo();
                        }
                        input.readMessage(this.setuidFileInfo);
                        continue;
                    case LogSource.TRANSOM /*114*/:
                        if (this.seLinuxInfo == null) {
                            this.seLinuxInfo = new SeLinuxInfo();
                        }
                        input.readMessage(this.seLinuxInfo);
                        continue;
                    case LogSource.ANDROID_SNET_GCORE /*122*/:
                        this.uuid = input.readString();
                        continue;
                    case LogSource.KEEP /*128*/:
                        this.gmsCoreUuidUsed = input.readBool();
                        continue;
                    case LogSource.SAMPLE_SHM /*136*/:
                        this.sdCardTampered = input.readBool();
                        continue;
                    case 146:
                        if (this.appsList == null) {
                            this.appsList = new AppsList();
                        }
                        input.readMessage(this.appsList);
                        continue;
                    case 154:
                        this.googleWebpageHtml = input.readString();
                        continue;
                    case 162:
                        if (this.sslv3Fallback == null) {
                            this.sslv3Fallback = new OkHttpSslv3FallbackInfo();
                        }
                        input.readMessage(this.sslv3Fallback);
                        continue;
                    case 170:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 170);
                        if (this.dnsMxRecords == null) {
                            i = Logs.CHROME_LINUX_USER_AGENT;
                        } else {
                            i = this.dnsMxRecords.length;
                        }
                        DnsMxRecords[] newArray6 = new DnsMxRecords[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.dnsMxRecords, Logs.CHROME_LINUX_USER_AGENT, newArray6, Logs.CHROME_LINUX_USER_AGENT, i);
                        }
                        while (i < newArray6.length - 1) {
                            newArray6[i] = new DnsMxRecords();
                            input.readMessage(newArray6[i]);
                            input.readTag();
                            i += Logs.SAFARI_IPHONE_USER_AGENT;
                        }
                        newArray6[i] = new DnsMxRecords();
                        input.readMessage(newArray6[i]);
                        this.dnsMxRecords = newArray6;
                        continue;
                    case 178:
                        this.featuresBitField = input.readBytes();
                        continue;
                    case 186:
                        if (this.sdCardTamperedInfo == null) {
                            this.sdCardTamperedInfo = new SdCardInfo();
                        }
                        input.readMessage(this.sdCardTamperedInfo);
                        continue;
                    case 194:
                        this.debugStatus = input.readString();
                        continue;
                    case 202:
                        if (this.runSettings == null) {
                            this.runSettings = new RunSettings();
                        }
                        input.readMessage(this.runSettings);
                        continue;
                    case 210:
                        if (this.googleWebpageInfo == null) {
                            this.googleWebpageInfo = new GoogleWebpageInfo();
                        }
                        input.readMessage(this.googleWebpageInfo);
                        continue;
                    case 218:
                        if (this.captivePortalTestResults == null) {
                            this.captivePortalTestResults = new CaptivePortalTestResults();
                        }
                        input.readMessage(this.captivePortalTestResults);
                        continue;
                    case 226:
                        if (this.proxyInfo == null) {
                            this.proxyInfo = new ProxyInfo();
                        }
                        input.readMessage(this.proxyInfo);
                        continue;
                    case 234:
                        if (this.logcatResults == null) {
                            this.logcatResults = new LogcatResults();
                        }
                        input.readMessage(this.logcatResults);
                        continue;
                    case 242:
                        if (this.systemPartitionFileInfo == null) {
                            this.systemPartitionFileInfo = new SystemPartitionFileInfo();
                        }
                        input.readMessage(this.systemPartitionFileInfo);
                        continue;
                    case 250:
                        this.buildFingerprint = input.readString();
                        continue;
                    case 258:
                        this.signalTagsWhitelist = input.readString();
                        continue;
                    case 266:
                        if (this.attestationResult == null) {
                            this.attestationResult = new AttestationResult();
                        }
                        input.readMessage(this.attestationResult);
                        continue;
                    case 274:
                        this.sharedUuid = input.readString();
                        continue;
                    case 282:
                        if (this.gmsCoreInfo == null) {
                            this.gmsCoreInfo = new AppInfo();
                        }
                        input.readMessage(this.gmsCoreInfo);
                        continue;
                    case 288:
                        this.firstEventLogTimeNano = input.readInt64();
                        continue;
                    case 296:
                        this.isSidewinderDevice = input.readBool();
                        continue;
                    case 306:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 306);
                        if (this.deactivatedDeviceAdmins == null) {
                            i = Logs.CHROME_LINUX_USER_AGENT;
                        } else {
                            i = this.deactivatedDeviceAdmins.length;
                        }
                        AppInfo[] newArray7 = new AppInfo[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.deactivatedDeviceAdmins, Logs.CHROME_LINUX_USER_AGENT, newArray7, Logs.CHROME_LINUX_USER_AGENT, i);
                        }
                        while (i < newArray7.length - 1) {
                            newArray7[i] = new AppInfo();
                            input.readMessage(newArray7[i]);
                            input.readTag();
                            i += Logs.SAFARI_IPHONE_USER_AGENT;
                        }
                        newArray7[i] = new AppInfo();
                        input.readMessage(newArray7[i]);
                        this.deactivatedDeviceAdmins = newArray7;
                        continue;
                    case 314:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 314);
                        i = this.invalidBlacklistedDeviceAdmins == null ? Logs.CHROME_LINUX_USER_AGENT : this.invalidBlacklistedDeviceAdmins.length;
                        newArray = new String[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.invalidBlacklistedDeviceAdmins, Logs.CHROME_LINUX_USER_AGENT, newArray, Logs.CHROME_LINUX_USER_AGENT, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = input.readString();
                            input.readTag();
                            i += Logs.SAFARI_IPHONE_USER_AGENT;
                        }
                        newArray[i] = input.readString();
                        this.invalidBlacklistedDeviceAdmins = newArray;
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

        public static SnetLog parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (SnetLog) MessageNano.mergeFrom(new SnetLog(), data);
        }

        public static SnetLog parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new SnetLog().mergeFrom(input);
        }
    }

    public static final class SslHandshakeInfo extends MessageNano {
        private static volatile SslHandshakeInfo[] _emptyArray;
        public boolean certInCaStore;
        public boolean certUserAdded;
        public boolean chainTrusted;
        public boolean chainTrustedByPubkey;
        public boolean chainValid;
        public String cipherSuite;
        public boolean extendedKeyUsageFieldValid;
        public String host;
        public boolean hostnameVerified;
        public byte[][] peerCertificates;
        public boolean peerCertificatesAcquired;
        public boolean pinTestError;
        public String protocol;
        public boolean sessionEstablished;
        public boolean socketCreated;
        public boolean systemTrustManagerAcceptedConnection;
        public boolean systemTrustManagerExists;
        public TrustManagerInfo[] trustManagers;

        public static SslHandshakeInfo[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new SslHandshakeInfo[Logs.CHROME_LINUX_USER_AGENT];
                    }
                }
            }
            return _emptyArray;
        }

        public SslHandshakeInfo() {
            clear();
        }

        public SslHandshakeInfo clear() {
            this.host = BuildConfig.VERSION_NAME;
            this.socketCreated = false;
            this.sessionEstablished = false;
            this.protocol = BuildConfig.VERSION_NAME;
            this.cipherSuite = BuildConfig.VERSION_NAME;
            this.peerCertificatesAcquired = false;
            this.systemTrustManagerExists = false;
            this.systemTrustManagerAcceptedConnection = false;
            this.hostnameVerified = false;
            this.chainValid = false;
            this.chainTrusted = false;
            this.chainTrustedByPubkey = false;
            this.extendedKeyUsageFieldValid = false;
            this.peerCertificates = WireFormatNano.EMPTY_BYTES_ARRAY;
            this.pinTestError = false;
            this.certUserAdded = false;
            this.certInCaStore = false;
            this.trustManagers = TrustManagerInfo.emptyArray();
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            int i;
            if (!this.host.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(Logs.SAFARI_IPHONE_USER_AGENT, this.host);
            }
            if (this.socketCreated) {
                output.writeBool(2, this.socketCreated);
            }
            if (this.sessionEstablished) {
                output.writeBool(3, this.sessionEstablished);
            }
            if (!this.protocol.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(4, this.protocol);
            }
            if (!this.cipherSuite.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(5, this.cipherSuite);
            }
            if (this.peerCertificatesAcquired) {
                output.writeBool(6, this.peerCertificatesAcquired);
            }
            if (this.systemTrustManagerExists) {
                output.writeBool(7, this.systemTrustManagerExists);
            }
            if (this.systemTrustManagerAcceptedConnection) {
                output.writeBool(8, this.systemTrustManagerAcceptedConnection);
            }
            if (this.hostnameVerified) {
                output.writeBool(9, this.hostnameVerified);
            }
            if (this.chainValid) {
                output.writeBool(10, this.chainValid);
            }
            if (this.chainTrusted) {
                output.writeBool(11, this.chainTrusted);
            }
            if (this.extendedKeyUsageFieldValid) {
                output.writeBool(12, this.extendedKeyUsageFieldValid);
            }
            if (this.peerCertificates != null && this.peerCertificates.length > 0) {
                for (i = Logs.CHROME_LINUX_USER_AGENT; i < this.peerCertificates.length; i += Logs.SAFARI_IPHONE_USER_AGENT) {
                    byte[] element = this.peerCertificates[i];
                    if (element != null) {
                        output.writeBytes(13, element);
                    }
                }
            }
            if (this.pinTestError) {
                output.writeBool(14, this.pinTestError);
            }
            if (this.certUserAdded) {
                output.writeBool(15, this.certUserAdded);
            }
            if (this.certInCaStore) {
                output.writeBool(16, this.certInCaStore);
            }
            if (this.trustManagers != null && this.trustManagers.length > 0) {
                for (i = Logs.CHROME_LINUX_USER_AGENT; i < this.trustManagers.length; i += Logs.SAFARI_IPHONE_USER_AGENT) {
                    TrustManagerInfo element2 = this.trustManagers[i];
                    if (element2 != null) {
                        output.writeMessage(17, element2);
                    }
                }
            }
            if (this.chainTrustedByPubkey) {
                output.writeBool(18, this.chainTrustedByPubkey);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int i;
            int size = super.computeSerializedSize();
            if (!this.host.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(Logs.SAFARI_IPHONE_USER_AGENT, this.host);
            }
            if (this.socketCreated) {
                size += CodedOutputByteBufferNano.computeBoolSize(2, this.socketCreated);
            }
            if (this.sessionEstablished) {
                size += CodedOutputByteBufferNano.computeBoolSize(3, this.sessionEstablished);
            }
            if (!this.protocol.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(4, this.protocol);
            }
            if (!this.cipherSuite.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(5, this.cipherSuite);
            }
            if (this.peerCertificatesAcquired) {
                size += CodedOutputByteBufferNano.computeBoolSize(6, this.peerCertificatesAcquired);
            }
            if (this.systemTrustManagerExists) {
                size += CodedOutputByteBufferNano.computeBoolSize(7, this.systemTrustManagerExists);
            }
            if (this.systemTrustManagerAcceptedConnection) {
                size += CodedOutputByteBufferNano.computeBoolSize(8, this.systemTrustManagerAcceptedConnection);
            }
            if (this.hostnameVerified) {
                size += CodedOutputByteBufferNano.computeBoolSize(9, this.hostnameVerified);
            }
            if (this.chainValid) {
                size += CodedOutputByteBufferNano.computeBoolSize(10, this.chainValid);
            }
            if (this.chainTrusted) {
                size += CodedOutputByteBufferNano.computeBoolSize(11, this.chainTrusted);
            }
            if (this.extendedKeyUsageFieldValid) {
                size += CodedOutputByteBufferNano.computeBoolSize(12, this.extendedKeyUsageFieldValid);
            }
            if (this.peerCertificates != null && this.peerCertificates.length > 0) {
                int dataCount = Logs.CHROME_LINUX_USER_AGENT;
                int dataSize = Logs.CHROME_LINUX_USER_AGENT;
                for (i = Logs.CHROME_LINUX_USER_AGENT; i < this.peerCertificates.length; i += Logs.SAFARI_IPHONE_USER_AGENT) {
                    byte[] element = this.peerCertificates[i];
                    if (element != null) {
                        dataCount += Logs.SAFARI_IPHONE_USER_AGENT;
                        dataSize += CodedOutputByteBufferNano.computeBytesSizeNoTag(element);
                    }
                }
                size = (size + dataSize) + (dataCount * Logs.SAFARI_IPHONE_USER_AGENT);
            }
            if (this.pinTestError) {
                size += CodedOutputByteBufferNano.computeBoolSize(14, this.pinTestError);
            }
            if (this.certUserAdded) {
                size += CodedOutputByteBufferNano.computeBoolSize(15, this.certUserAdded);
            }
            if (this.certInCaStore) {
                size += CodedOutputByteBufferNano.computeBoolSize(16, this.certInCaStore);
            }
            if (this.trustManagers != null && this.trustManagers.length > 0) {
                for (i = Logs.CHROME_LINUX_USER_AGENT; i < this.trustManagers.length; i += Logs.SAFARI_IPHONE_USER_AGENT) {
                    TrustManagerInfo element2 = this.trustManagers[i];
                    if (element2 != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(17, element2);
                    }
                }
            }
            if (this.chainTrustedByPubkey) {
                return size + CodedOutputByteBufferNano.computeBoolSize(18, this.chainTrustedByPubkey);
            }
            return size;
        }

        public SslHandshakeInfo mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                int arrayLength;
                int i;
                switch (tag) {
                    case Logs.CHROME_LINUX_USER_AGENT /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        this.host = input.readString();
                        continue;
                    case LogSource.GMS_CORE_PEOPLE /*16*/:
                        this.socketCreated = input.readBool();
                        continue;
                    case LogSource.LB_C /*24*/:
                        this.sessionEstablished = input.readBool();
                        continue;
                    case LogSource.NOVA /*34*/:
                        this.protocol = input.readString();
                        continue;
                    case LogSource.PHOTOS /*42*/:
                        this.cipherSuite = input.readString();
                        continue;
                    case LogSource.BACKDROP /*48*/:
                        this.peerCertificatesAcquired = input.readBool();
                        continue;
                    case LogSource.DOCS /*56*/:
                        this.systemTrustManagerExists = input.readBool();
                        continue;
                    case LogSource.KIDS_COMMUNICATOR /*64*/:
                        this.systemTrustManagerAcceptedConnection = input.readBool();
                        continue;
                    case LogSource.JUSTSPEAK /*72*/:
                        this.hostnameVerified = input.readBool();
                        continue;
                    case LogSource.CRONET_ANDROID_GSA /*80*/:
                        this.chainValid = input.readBool();
                        continue;
                    case LogSource.EMERGENCY_ASSIST /*88*/:
                        this.chainTrusted = input.readBool();
                        continue;
                    case LogSource.GCM_COUNTERS /*96*/:
                        this.extendedKeyUsageFieldValid = input.readBool();
                        continue;
                    case LogSource.ADSHIELD /*106*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, LogSource.ADSHIELD);
                        i = this.peerCertificates == null ? Logs.CHROME_LINUX_USER_AGENT : this.peerCertificates.length;
                        byte[][] newArray = new byte[(i + arrayLength)][];
                        if (i != 0) {
                            System.arraycopy(this.peerCertificates, Logs.CHROME_LINUX_USER_AGENT, newArray, Logs.CHROME_LINUX_USER_AGENT, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = input.readBytes();
                            input.readTag();
                            i += Logs.SAFARI_IPHONE_USER_AGENT;
                        }
                        newArray[i] = input.readBytes();
                        this.peerCertificates = newArray;
                        continue;
                    case LogSource.ENDER /*112*/:
                        this.pinTestError = input.readBool();
                        continue;
                    case LogSource.FLYDROID /*120*/:
                        this.certUserAdded = input.readBool();
                        continue;
                    case LogSource.KEEP /*128*/:
                        this.certInCaStore = input.readBool();
                        continue;
                    case LogSource.PANCETTA_MOBILE_HOST /*138*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, LogSource.PANCETTA_MOBILE_HOST);
                        if (this.trustManagers == null) {
                            i = Logs.CHROME_LINUX_USER_AGENT;
                        } else {
                            i = this.trustManagers.length;
                        }
                        TrustManagerInfo[] newArray2 = new TrustManagerInfo[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.trustManagers, Logs.CHROME_LINUX_USER_AGENT, newArray2, Logs.CHROME_LINUX_USER_AGENT, i);
                        }
                        while (i < newArray2.length - 1) {
                            newArray2[i] = new TrustManagerInfo();
                            input.readMessage(newArray2[i]);
                            input.readTag();
                            i += Logs.SAFARI_IPHONE_USER_AGENT;
                        }
                        newArray2[i] = new TrustManagerInfo();
                        input.readMessage(newArray2[i]);
                        this.trustManagers = newArray2;
                        continue;
                    case 144:
                        this.chainTrustedByPubkey = input.readBool();
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

        public static SslHandshakeInfo parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (SslHandshakeInfo) MessageNano.mergeFrom(new SslHandshakeInfo(), data);
        }

        public static SslHandshakeInfo parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new SslHandshakeInfo().mergeFrom(input);
        }
    }

    public static final class SslRedirectInfo extends MessageNano {
        public static final int ANDROID_HTTP_CLIENT = 2;
        public static final int CHROME_LINUX_USER_AGENT = 0;
        public static final int HTTP_URL_CONNECTION = 1;
        public static final int SAFARI_IPHONE_USER_AGENT = 1;
        private static volatile SslRedirectInfo[] _emptyArray;
        public boolean endPointConnected;
        public String endPointUrl;
        public String host;
        public int httpClientUsed;
        public String[] ipAddressesForEndPointUrl;
        public String redirectHeaderValue;
        public int responseCode;
        public int userAgentUsed;

        public static SslRedirectInfo[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new SslRedirectInfo[CHROME_LINUX_USER_AGENT];
                    }
                }
            }
            return _emptyArray;
        }

        public SslRedirectInfo() {
            clear();
        }

        public SslRedirectInfo clear() {
            this.host = BuildConfig.VERSION_NAME;
            this.httpClientUsed = SAFARI_IPHONE_USER_AGENT;
            this.responseCode = CHROME_LINUX_USER_AGENT;
            this.redirectHeaderValue = BuildConfig.VERSION_NAME;
            this.endPointUrl = BuildConfig.VERSION_NAME;
            this.ipAddressesForEndPointUrl = WireFormatNano.EMPTY_STRING_ARRAY;
            this.userAgentUsed = CHROME_LINUX_USER_AGENT;
            this.endPointConnected = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (!this.host.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(SAFARI_IPHONE_USER_AGENT, this.host);
            }
            if (this.httpClientUsed != SAFARI_IPHONE_USER_AGENT) {
                output.writeInt32(ANDROID_HTTP_CLIENT, this.httpClientUsed);
            }
            if (this.responseCode != 0) {
                output.writeInt32(3, this.responseCode);
            }
            if (!this.redirectHeaderValue.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(4, this.redirectHeaderValue);
            }
            if (!this.endPointUrl.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(5, this.endPointUrl);
            }
            if (this.ipAddressesForEndPointUrl != null && this.ipAddressesForEndPointUrl.length > 0) {
                for (int i = CHROME_LINUX_USER_AGENT; i < this.ipAddressesForEndPointUrl.length; i += SAFARI_IPHONE_USER_AGENT) {
                    String element = this.ipAddressesForEndPointUrl[i];
                    if (element != null) {
                        output.writeString(6, element);
                    }
                }
            }
            if (this.userAgentUsed != 0) {
                output.writeInt32(7, this.userAgentUsed);
            }
            if (this.endPointConnected) {
                output.writeBool(8, this.endPointConnected);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (!this.host.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(SAFARI_IPHONE_USER_AGENT, this.host);
            }
            if (this.httpClientUsed != SAFARI_IPHONE_USER_AGENT) {
                size += CodedOutputByteBufferNano.computeInt32Size(ANDROID_HTTP_CLIENT, this.httpClientUsed);
            }
            if (this.responseCode != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(3, this.responseCode);
            }
            if (!this.redirectHeaderValue.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(4, this.redirectHeaderValue);
            }
            if (!this.endPointUrl.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(5, this.endPointUrl);
            }
            if (this.ipAddressesForEndPointUrl != null && this.ipAddressesForEndPointUrl.length > 0) {
                int dataCount = CHROME_LINUX_USER_AGENT;
                int dataSize = CHROME_LINUX_USER_AGENT;
                for (int i = CHROME_LINUX_USER_AGENT; i < this.ipAddressesForEndPointUrl.length; i += SAFARI_IPHONE_USER_AGENT) {
                    String element = this.ipAddressesForEndPointUrl[i];
                    if (element != null) {
                        dataCount += SAFARI_IPHONE_USER_AGENT;
                        dataSize += CodedOutputByteBufferNano.computeStringSizeNoTag(element);
                    }
                }
                size = (size + dataSize) + (dataCount * SAFARI_IPHONE_USER_AGENT);
            }
            if (this.userAgentUsed != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(7, this.userAgentUsed);
            }
            if (this.endPointConnected) {
                return size + CodedOutputByteBufferNano.computeBoolSize(8, this.endPointConnected);
            }
            return size;
        }

        public SslRedirectInfo mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                int value;
                switch (tag) {
                    case CHROME_LINUX_USER_AGENT /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        this.host = input.readString();
                        continue;
                    case LogSource.GMS_CORE_PEOPLE /*16*/:
                        value = input.readInt32();
                        switch (value) {
                            case SAFARI_IPHONE_USER_AGENT /*1*/:
                            case ANDROID_HTTP_CLIENT /*2*/:
                                this.httpClientUsed = value;
                                break;
                            default:
                                continue;
                        }
                    case LogSource.LB_C /*24*/:
                        this.responseCode = input.readInt32();
                        continue;
                    case LogSource.NOVA /*34*/:
                        this.redirectHeaderValue = input.readString();
                        continue;
                    case LogSource.PHOTOS /*42*/:
                        this.endPointUrl = input.readString();
                        continue;
                    case LogSource.HANGOUT_LOG_REQUEST /*50*/:
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 50);
                        int i = this.ipAddressesForEndPointUrl == null ? CHROME_LINUX_USER_AGENT : this.ipAddressesForEndPointUrl.length;
                        String[] newArray = new String[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.ipAddressesForEndPointUrl, CHROME_LINUX_USER_AGENT, newArray, CHROME_LINUX_USER_AGENT, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = input.readString();
                            input.readTag();
                            i += SAFARI_IPHONE_USER_AGENT;
                        }
                        newArray[i] = input.readString();
                        this.ipAddressesForEndPointUrl = newArray;
                        continue;
                    case LogSource.DOCS /*56*/:
                        value = input.readInt32();
                        switch (value) {
                            case CHROME_LINUX_USER_AGENT /*0*/:
                            case SAFARI_IPHONE_USER_AGENT /*1*/:
                                this.userAgentUsed = value;
                                break;
                            default:
                                continue;
                        }
                    case LogSource.KIDS_COMMUNICATOR /*64*/:
                        this.endPointConnected = input.readBool();
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

        public static SslRedirectInfo parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (SslRedirectInfo) MessageNano.mergeFrom(new SslRedirectInfo(), data);
        }

        public static SslRedirectInfo parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new SslRedirectInfo().mergeFrom(input);
        }
    }

    public static final class SystemPartitionFileInfo extends MessageNano {
        private static volatile SystemPartitionFileInfo[] _emptyArray;
        public boolean couldNotCheck;
        public FileInfo[] fileInfo;

        public static final class FileInfo extends MessageNano {
            private static volatile FileInfo[] _emptyArray;
            public String path;
            public byte[] sha256;
            public boolean symlink;
            public String symlinkTarget;

            public static FileInfo[] emptyArray() {
                if (_emptyArray == null) {
                    synchronized (InternalNano.LAZY_INIT_LOCK) {
                        if (_emptyArray == null) {
                            _emptyArray = new FileInfo[Logs.CHROME_LINUX_USER_AGENT];
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
                this.symlink = false;
                this.symlinkTarget = BuildConfig.VERSION_NAME;
                this.cachedSize = -1;
                return this;
            }

            public void writeTo(CodedOutputByteBufferNano output) throws IOException {
                if (!this.path.equals(BuildConfig.VERSION_NAME)) {
                    output.writeString(Logs.SAFARI_IPHONE_USER_AGENT, this.path);
                }
                if (!Arrays.equals(this.sha256, WireFormatNano.EMPTY_BYTES)) {
                    output.writeBytes(2, this.sha256);
                }
                if (this.symlink) {
                    output.writeBool(3, this.symlink);
                }
                if (!this.symlinkTarget.equals(BuildConfig.VERSION_NAME)) {
                    output.writeString(4, this.symlinkTarget);
                }
                super.writeTo(output);
            }

            protected int computeSerializedSize() {
                int size = super.computeSerializedSize();
                if (!this.path.equals(BuildConfig.VERSION_NAME)) {
                    size += CodedOutputByteBufferNano.computeStringSize(Logs.SAFARI_IPHONE_USER_AGENT, this.path);
                }
                if (!Arrays.equals(this.sha256, WireFormatNano.EMPTY_BYTES)) {
                    size += CodedOutputByteBufferNano.computeBytesSize(2, this.sha256);
                }
                if (this.symlink) {
                    size += CodedOutputByteBufferNano.computeBoolSize(3, this.symlink);
                }
                if (this.symlinkTarget.equals(BuildConfig.VERSION_NAME)) {
                    return size;
                }
                return size + CodedOutputByteBufferNano.computeStringSize(4, this.symlinkTarget);
            }

            public FileInfo mergeFrom(CodedInputByteBufferNano input) throws IOException {
                while (true) {
                    int tag = input.readTag();
                    switch (tag) {
                        case Logs.CHROME_LINUX_USER_AGENT /*0*/:
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

        public static SystemPartitionFileInfo[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new SystemPartitionFileInfo[Logs.CHROME_LINUX_USER_AGENT];
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
            this.fileInfo = FileInfo.emptyArray();
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.couldNotCheck) {
                output.writeBool(Logs.SAFARI_IPHONE_USER_AGENT, this.couldNotCheck);
            }
            if (this.fileInfo != null && this.fileInfo.length > 0) {
                for (int i = Logs.CHROME_LINUX_USER_AGENT; i < this.fileInfo.length; i += Logs.SAFARI_IPHONE_USER_AGENT) {
                    FileInfo element = this.fileInfo[i];
                    if (element != null) {
                        output.writeMessage(2, element);
                    }
                }
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.couldNotCheck) {
                size += CodedOutputByteBufferNano.computeBoolSize(Logs.SAFARI_IPHONE_USER_AGENT, this.couldNotCheck);
            }
            if (this.fileInfo != null && this.fileInfo.length > 0) {
                for (int i = Logs.CHROME_LINUX_USER_AGENT; i < this.fileInfo.length; i += Logs.SAFARI_IPHONE_USER_AGENT) {
                    FileInfo element = this.fileInfo[i];
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(2, element);
                    }
                }
            }
            return size;
        }

        public SystemPartitionFileInfo mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Logs.CHROME_LINUX_USER_AGENT /*0*/:
                        break;
                    case Type.TAP_GET_LINK /*8*/:
                        this.couldNotCheck = input.readBool();
                        continue;
                    case LogSource.GOOGLE_ANALYTICS /*18*/:
                        int i;
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 18);
                        if (this.fileInfo == null) {
                            i = Logs.CHROME_LINUX_USER_AGENT;
                        } else {
                            i = this.fileInfo.length;
                        }
                        FileInfo[] newArray = new FileInfo[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.fileInfo, Logs.CHROME_LINUX_USER_AGENT, newArray, Logs.CHROME_LINUX_USER_AGENT, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new FileInfo();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i += Logs.SAFARI_IPHONE_USER_AGENT;
                        }
                        newArray[i] = new FileInfo();
                        input.readMessage(newArray[i]);
                        this.fileInfo = newArray;
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

        public static SystemPartitionFileInfo parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (SystemPartitionFileInfo) MessageNano.mergeFrom(new SystemPartitionFileInfo(), data);
        }

        public static SystemPartitionFileInfo parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new SystemPartitionFileInfo().mergeFrom(input);
        }
    }

    public static final class TrustManagerInfo extends MessageNano {
        private static volatile TrustManagerInfo[] _emptyArray;
        public boolean acceptedConnection;

        public static TrustManagerInfo[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new TrustManagerInfo[Logs.CHROME_LINUX_USER_AGENT];
                    }
                }
            }
            return _emptyArray;
        }

        public TrustManagerInfo() {
            clear();
        }

        public TrustManagerInfo clear() {
            this.acceptedConnection = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.acceptedConnection) {
                output.writeBool(Logs.SAFARI_IPHONE_USER_AGENT, this.acceptedConnection);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.acceptedConnection) {
                return size + CodedOutputByteBufferNano.computeBoolSize(Logs.SAFARI_IPHONE_USER_AGENT, this.acceptedConnection);
            }
            return size;
        }

        public TrustManagerInfo mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Logs.CHROME_LINUX_USER_AGENT /*0*/:
                        break;
                    case Type.TAP_GET_LINK /*8*/:
                        this.acceptedConnection = input.readBool();
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

        public static TrustManagerInfo parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (TrustManagerInfo) MessageNano.mergeFrom(new TrustManagerInfo(), data);
        }

        public static TrustManagerInfo parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new TrustManagerInfo().mergeFrom(input);
        }
    }
}
