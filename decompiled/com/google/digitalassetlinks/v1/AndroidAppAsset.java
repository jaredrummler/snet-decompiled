package com.google.digitalassetlinks.v1;

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

public final class AndroidAppAsset extends ExtendableMessageNano<AndroidAppAsset> {
    private static volatile AndroidAppAsset[] _emptyArray;
    public CertificateInfo certificate;
    public String packageName;

    public static final class CertificateInfo extends ExtendableMessageNano<CertificateInfo> {
        private static volatile CertificateInfo[] _emptyArray;
        public String sha256Fingerprint;

        public static CertificateInfo[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new CertificateInfo[0];
                    }
                }
            }
            return _emptyArray;
        }

        public CertificateInfo() {
            clear();
        }

        public CertificateInfo clear() {
            this.sha256Fingerprint = BuildConfig.VERSION_NAME;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof CertificateInfo)) {
                return false;
            }
            CertificateInfo other = (CertificateInfo) o;
            if (this.sha256Fingerprint == null) {
                if (other.sha256Fingerprint != null) {
                    return false;
                }
            } else if (!this.sha256Fingerprint.equals(other.sha256Fingerprint)) {
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
            int hashCode = (((getClass().getName().hashCode() + 527) * 31) + (this.sha256Fingerprint == null ? 0 : this.sha256Fingerprint.hashCode())) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (!this.sha256Fingerprint.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(1, this.sha256Fingerprint);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.sha256Fingerprint.equals(BuildConfig.VERSION_NAME)) {
                return size;
            }
            return size + CodedOutputByteBufferNano.computeStringSize(1, this.sha256Fingerprint);
        }

        public CertificateInfo mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        this.sha256Fingerprint = input.readString();
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

        public static CertificateInfo parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (CertificateInfo) MessageNano.mergeFrom(new CertificateInfo(), data);
        }

        public static CertificateInfo parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new CertificateInfo().mergeFrom(input);
        }
    }

    public static AndroidAppAsset[] emptyArray() {
        if (_emptyArray == null) {
            synchronized (InternalNano.LAZY_INIT_LOCK) {
                if (_emptyArray == null) {
                    _emptyArray = new AndroidAppAsset[0];
                }
            }
        }
        return _emptyArray;
    }

    public AndroidAppAsset() {
        clear();
    }

    public AndroidAppAsset clear() {
        this.packageName = BuildConfig.VERSION_NAME;
        this.certificate = null;
        this.unknownFieldData = null;
        this.cachedSize = -1;
        return this;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof AndroidAppAsset)) {
            return false;
        }
        AndroidAppAsset other = (AndroidAppAsset) o;
        if (this.packageName == null) {
            if (other.packageName != null) {
                return false;
            }
        } else if (!this.packageName.equals(other.packageName)) {
            return false;
        }
        if (this.certificate == null) {
            if (other.certificate != null) {
                return false;
            }
        } else if (!this.certificate.equals(other.certificate)) {
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
        int hashCode = (((((getClass().getName().hashCode() + 527) * 31) + (this.packageName == null ? 0 : this.packageName.hashCode())) * 31) + (this.certificate == null ? 0 : this.certificate.hashCode())) * 31;
        if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
            i = this.unknownFieldData.hashCode();
        }
        return hashCode + i;
    }

    public void writeTo(CodedOutputByteBufferNano output) throws IOException {
        if (!this.packageName.equals(BuildConfig.VERSION_NAME)) {
            output.writeString(1, this.packageName);
        }
        if (this.certificate != null) {
            output.writeMessage(2, this.certificate);
        }
        super.writeTo(output);
    }

    protected int computeSerializedSize() {
        int size = super.computeSerializedSize();
        if (!this.packageName.equals(BuildConfig.VERSION_NAME)) {
            size += CodedOutputByteBufferNano.computeStringSize(1, this.packageName);
        }
        if (this.certificate != null) {
            return size + CodedOutputByteBufferNano.computeMessageSize(2, this.certificate);
        }
        return size;
    }

    public AndroidAppAsset mergeFrom(CodedInputByteBufferNano input) throws IOException {
        while (true) {
            int tag = input.readTag();
            switch (tag) {
                case Action.UNKNOWN /*0*/:
                    break;
                case Type.TAP_ABOUT /*10*/:
                    this.packageName = input.readString();
                    continue;
                case LogSource.GOOGLE_ANALYTICS /*18*/:
                    if (this.certificate == null) {
                        this.certificate = new CertificateInfo();
                    }
                    input.readMessage(this.certificate);
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

    public static AndroidAppAsset parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
        return (AndroidAppAsset) MessageNano.mergeFrom(new AndroidAppAsset(), data);
    }

    public static AndroidAppAsset parseFrom(CodedInputByteBufferNano input) throws IOException {
        return new AndroidAppAsset().mergeFrom(input);
    }
}
