package com.google.security.lso.apiary.reauth;

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
import com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection;
import java.io.IOException;

public interface ReauthServices {

    public static final class CreateReauthProofTokenRequest extends ExtendableMessageNano<CreateReauthProofTokenRequest> {
        private static volatile CreateReauthProofTokenRequest[] _emptyArray;
        public Credential credential;
        public int delegationType;
        public String userId;
        public String versionInfo;

        public static CreateReauthProofTokenRequest[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new CreateReauthProofTokenRequest[0];
                    }
                }
            }
            return _emptyArray;
        }

        public CreateReauthProofTokenRequest() {
            clear();
        }

        public CreateReauthProofTokenRequest clear() {
            this.userId = BuildConfig.VERSION_NAME;
            this.credential = null;
            this.versionInfo = BuildConfig.VERSION_NAME;
            this.delegationType = 1;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof CreateReauthProofTokenRequest)) {
                return false;
            }
            CreateReauthProofTokenRequest other = (CreateReauthProofTokenRequest) o;
            if (this.userId == null) {
                if (other.userId != null) {
                    return false;
                }
            } else if (!this.userId.equals(other.userId)) {
                return false;
            }
            if (this.credential == null) {
                if (other.credential != null) {
                    return false;
                }
            } else if (!this.credential.equals(other.credential)) {
                return false;
            }
            if (this.versionInfo == null) {
                if (other.versionInfo != null) {
                    return false;
                }
            } else if (!this.versionInfo.equals(other.versionInfo)) {
                return false;
            }
            if (this.delegationType != other.delegationType) {
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
            int hashCode = (((((((((getClass().getName().hashCode() + 527) * 31) + (this.userId == null ? 0 : this.userId.hashCode())) * 31) + (this.credential == null ? 0 : this.credential.hashCode())) * 31) + (this.versionInfo == null ? 0 : this.versionInfo.hashCode())) * 31) + this.delegationType) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (!this.userId.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(1, this.userId);
            }
            if (this.credential != null) {
                output.writeMessage(2, this.credential);
            }
            if (!this.versionInfo.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(3, this.versionInfo);
            }
            if (this.delegationType != 1) {
                output.writeInt32(4, this.delegationType);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (!this.userId.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.userId);
            }
            if (this.credential != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(2, this.credential);
            }
            if (!this.versionInfo.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(3, this.versionInfo);
            }
            if (this.delegationType != 1) {
                return size + CodedOutputByteBufferNano.computeInt32Size(4, this.delegationType);
            }
            return size;
        }

        public CreateReauthProofTokenRequest mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        this.userId = input.readString();
                        continue;
                    case LogSource.GOOGLE_ANALYTICS /*18*/:
                        if (this.credential == null) {
                            this.credential = new Credential();
                        }
                        input.readMessage(this.credential);
                        continue;
                    case LogSource.ANDROID_CAMERA /*26*/:
                        this.versionInfo = input.readString();
                        continue;
                    case LogSource.SOCIAL /*32*/:
                        int value = input.readInt32();
                        switch (value) {
                            case TimeSelection.Type.TEMPORARY /*1*/:
                                this.delegationType = value;
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

        public static CreateReauthProofTokenRequest parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (CreateReauthProofTokenRequest) MessageNano.mergeFrom(new CreateReauthProofTokenRequest(), data);
        }

        public static CreateReauthProofTokenRequest parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new CreateReauthProofTokenRequest().mergeFrom(input);
        }
    }

    public static final class Credential extends ExtendableMessageNano<Credential> {
        private static volatile Credential[] _emptyArray;
        public String credential;
        public int credentialType;
        public String pin;

        public static Credential[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new Credential[0];
                    }
                }
            }
            return _emptyArray;
        }

        public Credential() {
            clear();
        }

        public Credential clear() {
            this.credentialType = 0;
            this.pin = BuildConfig.VERSION_NAME;
            this.credential = BuildConfig.VERSION_NAME;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof Credential)) {
                return false;
            }
            Credential other = (Credential) o;
            if (this.credentialType != other.credentialType) {
                return false;
            }
            if (this.pin == null) {
                if (other.pin != null) {
                    return false;
                }
            } else if (!this.pin.equals(other.pin)) {
                return false;
            }
            if (this.credential == null) {
                if (other.credential != null) {
                    return false;
                }
            } else if (!this.credential.equals(other.credential)) {
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
            int hashCode = (((((((getClass().getName().hashCode() + 527) * 31) + this.credentialType) * 31) + (this.pin == null ? 0 : this.pin.hashCode())) * 31) + (this.credential == null ? 0 : this.credential.hashCode())) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.credentialType != 0) {
                output.writeInt32(2, this.credentialType);
            }
            if (!this.pin.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(3, this.pin);
            }
            if (!this.credential.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(4, this.credential);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.credentialType != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(2, this.credentialType);
            }
            if (!this.pin.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(3, this.pin);
            }
            if (this.credential.equals(BuildConfig.VERSION_NAME)) {
                return size;
            }
            return size + CodedOutputByteBufferNano.computeStringSize(4, this.credential);
        }

        public Credential mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case LogSource.GMS_CORE_PEOPLE /*16*/:
                        int value = input.readInt32();
                        switch (value) {
                            case Action.UNKNOWN /*0*/:
                            case TimeSelection.Type.TEMPORARY /*1*/:
                            case TimeSelection.Type.INDEFINITELY /*2*/:
                                this.credentialType = value;
                                break;
                            default:
                                continue;
                        }
                    case LogSource.ANDROID_CAMERA /*26*/:
                        this.pin = input.readString();
                        continue;
                    case LogSource.NOVA /*34*/:
                        this.credential = input.readString();
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

        public static Credential parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (Credential) MessageNano.mergeFrom(new Credential(), data);
        }

        public static Credential parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new Credential().mergeFrom(input);
        }
    }

    public interface CredentialStatus {
        public static final int ACTIVE = 1;
        public static final int CONFIGURABLE = 2;
        public static final int LOCKED = 3;
    }

    public interface CredentialType {
        public static final int PASSWORD = 2;
        public static final int PIN = 1;
        public static final int UNKNOWN_CREDENTIAL_TYPE = 0;
    }

    public interface DelegationType {
        public static final int UNICORN = 1;
    }

    public static final class GetReauthSettingsRequest extends ExtendableMessageNano<GetReauthSettingsRequest> {
        private static volatile GetReauthSettingsRequest[] _emptyArray;
        public String continueUrl;
        public int delegationType;
        public String userId;
        public String versionInfo;

        public static GetReauthSettingsRequest[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new GetReauthSettingsRequest[0];
                    }
                }
            }
            return _emptyArray;
        }

        public GetReauthSettingsRequest() {
            clear();
        }

        public GetReauthSettingsRequest clear() {
            this.userId = BuildConfig.VERSION_NAME;
            this.versionInfo = BuildConfig.VERSION_NAME;
            this.continueUrl = BuildConfig.VERSION_NAME;
            this.delegationType = 1;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof GetReauthSettingsRequest)) {
                return false;
            }
            GetReauthSettingsRequest other = (GetReauthSettingsRequest) o;
            if (this.userId == null) {
                if (other.userId != null) {
                    return false;
                }
            } else if (!this.userId.equals(other.userId)) {
                return false;
            }
            if (this.versionInfo == null) {
                if (other.versionInfo != null) {
                    return false;
                }
            } else if (!this.versionInfo.equals(other.versionInfo)) {
                return false;
            }
            if (this.continueUrl == null) {
                if (other.continueUrl != null) {
                    return false;
                }
            } else if (!this.continueUrl.equals(other.continueUrl)) {
                return false;
            }
            if (this.delegationType != other.delegationType) {
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
            int hashCode = (((((((((getClass().getName().hashCode() + 527) * 31) + (this.userId == null ? 0 : this.userId.hashCode())) * 31) + (this.versionInfo == null ? 0 : this.versionInfo.hashCode())) * 31) + (this.continueUrl == null ? 0 : this.continueUrl.hashCode())) * 31) + this.delegationType) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (!this.userId.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(1, this.userId);
            }
            if (!this.versionInfo.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(2, this.versionInfo);
            }
            if (!this.continueUrl.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(3, this.continueUrl);
            }
            if (this.delegationType != 1) {
                output.writeInt32(4, this.delegationType);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (!this.userId.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.userId);
            }
            if (!this.versionInfo.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(2, this.versionInfo);
            }
            if (!this.continueUrl.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(3, this.continueUrl);
            }
            if (this.delegationType != 1) {
                return size + CodedOutputByteBufferNano.computeInt32Size(4, this.delegationType);
            }
            return size;
        }

        public GetReauthSettingsRequest mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        this.userId = input.readString();
                        continue;
                    case LogSource.GOOGLE_ANALYTICS /*18*/:
                        this.versionInfo = input.readString();
                        continue;
                    case LogSource.ANDROID_CAMERA /*26*/:
                        this.continueUrl = input.readString();
                        continue;
                    case LogSource.SOCIAL /*32*/:
                        int value = input.readInt32();
                        switch (value) {
                            case TimeSelection.Type.TEMPORARY /*1*/:
                                this.delegationType = value;
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

        public static GetReauthSettingsRequest parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (GetReauthSettingsRequest) MessageNano.mergeFrom(new GetReauthSettingsRequest(), data);
        }

        public static GetReauthSettingsRequest parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new GetReauthSettingsRequest().mergeFrom(input);
        }
    }

    public static final class PasswordSettings extends ExtendableMessageNano<PasswordSettings> {
        private static volatile PasswordSettings[] _emptyArray;
        public int status;

        public static PasswordSettings[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new PasswordSettings[0];
                    }
                }
            }
            return _emptyArray;
        }

        public PasswordSettings() {
            clear();
        }

        public PasswordSettings clear() {
            this.status = 1;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof PasswordSettings)) {
                return false;
            }
            PasswordSettings other = (PasswordSettings) o;
            if (this.status != other.status) {
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
            int hashCode = (((getClass().getName().hashCode() + 527) * 31) + this.status) * 31;
            int hashCode2 = (this.unknownFieldData == null || this.unknownFieldData.isEmpty()) ? 0 : this.unknownFieldData.hashCode();
            return hashCode + hashCode2;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.status != 1) {
                output.writeInt32(1, this.status);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.status != 1) {
                return size + CodedOutputByteBufferNano.computeInt32Size(1, this.status);
            }
            return size;
        }

        public PasswordSettings mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_GET_LINK /*8*/:
                        int value = input.readInt32();
                        switch (value) {
                            case TimeSelection.Type.TEMPORARY /*1*/:
                            case TimeSelection.Type.INDEFINITELY /*2*/:
                            case TimeSelection.Type.CUSTOM /*3*/:
                                this.status = value;
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

        public static PasswordSettings parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (PasswordSettings) MessageNano.mergeFrom(new PasswordSettings(), data);
        }

        public static PasswordSettings parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new PasswordSettings().mergeFrom(input);
        }
    }

    public static final class PinSettings extends ExtendableMessageNano<PinSettings> {
        private static volatile PinSettings[] _emptyArray;
        public int length;
        public String recoveryUrl;
        public String resetUrl;
        public String setupUrl;
        public int status;

        public static PinSettings[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new PinSettings[0];
                    }
                }
            }
            return _emptyArray;
        }

        public PinSettings() {
            clear();
        }

        public PinSettings clear() {
            this.status = 1;
            this.resetUrl = BuildConfig.VERSION_NAME;
            this.setupUrl = BuildConfig.VERSION_NAME;
            this.length = 0;
            this.recoveryUrl = BuildConfig.VERSION_NAME;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof PinSettings)) {
                return false;
            }
            PinSettings other = (PinSettings) o;
            if (this.status != other.status) {
                return false;
            }
            if (this.resetUrl == null) {
                if (other.resetUrl != null) {
                    return false;
                }
            } else if (!this.resetUrl.equals(other.resetUrl)) {
                return false;
            }
            if (this.setupUrl == null) {
                if (other.setupUrl != null) {
                    return false;
                }
            } else if (!this.setupUrl.equals(other.setupUrl)) {
                return false;
            }
            if (this.length != other.length) {
                return false;
            }
            if (this.recoveryUrl == null) {
                if (other.recoveryUrl != null) {
                    return false;
                }
            } else if (!this.recoveryUrl.equals(other.recoveryUrl)) {
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
            int hashCode = (((((((((((getClass().getName().hashCode() + 527) * 31) + this.status) * 31) + (this.resetUrl == null ? 0 : this.resetUrl.hashCode())) * 31) + (this.setupUrl == null ? 0 : this.setupUrl.hashCode())) * 31) + this.length) * 31) + (this.recoveryUrl == null ? 0 : this.recoveryUrl.hashCode())) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.status != 1) {
                output.writeInt32(1, this.status);
            }
            if (!this.resetUrl.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(2, this.resetUrl);
            }
            if (!this.setupUrl.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(3, this.setupUrl);
            }
            if (this.length != 0) {
                output.writeInt32(4, this.length);
            }
            if (!this.recoveryUrl.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(5, this.recoveryUrl);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.status != 1) {
                size += CodedOutputByteBufferNano.computeInt32Size(1, this.status);
            }
            if (!this.resetUrl.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(2, this.resetUrl);
            }
            if (!this.setupUrl.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(3, this.setupUrl);
            }
            if (this.length != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(4, this.length);
            }
            if (this.recoveryUrl.equals(BuildConfig.VERSION_NAME)) {
                return size;
            }
            return size + CodedOutputByteBufferNano.computeStringSize(5, this.recoveryUrl);
        }

        public PinSettings mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_GET_LINK /*8*/:
                        int value = input.readInt32();
                        switch (value) {
                            case TimeSelection.Type.TEMPORARY /*1*/:
                            case TimeSelection.Type.INDEFINITELY /*2*/:
                            case TimeSelection.Type.CUSTOM /*3*/:
                                this.status = value;
                                break;
                            default:
                                continue;
                        }
                    case LogSource.GOOGLE_ANALYTICS /*18*/:
                        this.resetUrl = input.readString();
                        continue;
                    case LogSource.ANDROID_CAMERA /*26*/:
                        this.setupUrl = input.readString();
                        continue;
                    case LogSource.SOCIAL /*32*/:
                        this.length = input.readInt32();
                        continue;
                    case LogSource.PHOTOS /*42*/:
                        this.recoveryUrl = input.readString();
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

        public static PinSettings parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (PinSettings) MessageNano.mergeFrom(new PinSettings(), data);
        }

        public static PinSettings parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new PinSettings().mergeFrom(input);
        }
    }

    public static final class ReauthProofToken extends ExtendableMessageNano<ReauthProofToken> {
        private static volatile ReauthProofToken[] _emptyArray;
        public String encodedRapt;

        public static ReauthProofToken[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new ReauthProofToken[0];
                    }
                }
            }
            return _emptyArray;
        }

        public ReauthProofToken() {
            clear();
        }

        public ReauthProofToken clear() {
            this.encodedRapt = BuildConfig.VERSION_NAME;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof ReauthProofToken)) {
                return false;
            }
            ReauthProofToken other = (ReauthProofToken) o;
            if (this.encodedRapt == null) {
                if (other.encodedRapt != null) {
                    return false;
                }
            } else if (!this.encodedRapt.equals(other.encodedRapt)) {
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
            int hashCode = (((getClass().getName().hashCode() + 527) * 31) + (this.encodedRapt == null ? 0 : this.encodedRapt.hashCode())) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (!this.encodedRapt.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(1, this.encodedRapt);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.encodedRapt.equals(BuildConfig.VERSION_NAME)) {
                return size;
            }
            return size + CodedOutputByteBufferNano.computeStringSize(1, this.encodedRapt);
        }

        public ReauthProofToken mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        this.encodedRapt = input.readString();
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

        public static ReauthProofToken parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (ReauthProofToken) MessageNano.mergeFrom(new ReauthProofToken(), data);
        }

        public static ReauthProofToken parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new ReauthProofToken().mergeFrom(input);
        }
    }

    public static final class ReauthSettings extends ExtendableMessageNano<ReauthSettings> {
        private static volatile ReauthSettings[] _emptyArray;
        public PasswordSettings password;
        public PinSettings pin;

        public static ReauthSettings[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new ReauthSettings[0];
                    }
                }
            }
            return _emptyArray;
        }

        public ReauthSettings() {
            clear();
        }

        public ReauthSettings clear() {
            this.pin = null;
            this.password = null;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof ReauthSettings)) {
                return false;
            }
            ReauthSettings other = (ReauthSettings) o;
            if (this.pin == null) {
                if (other.pin != null) {
                    return false;
                }
            } else if (!this.pin.equals(other.pin)) {
                return false;
            }
            if (this.password == null) {
                if (other.password != null) {
                    return false;
                }
            } else if (!this.password.equals(other.password)) {
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
            int hashCode = (((((getClass().getName().hashCode() + 527) * 31) + (this.pin == null ? 0 : this.pin.hashCode())) * 31) + (this.password == null ? 0 : this.password.hashCode())) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.pin != null) {
                output.writeMessage(1, this.pin);
            }
            if (this.password != null) {
                output.writeMessage(2, this.password);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.pin != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(1, this.pin);
            }
            if (this.password != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(2, this.password);
            }
            return size;
        }

        public ReauthSettings mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        if (this.pin == null) {
                            this.pin = new PinSettings();
                        }
                        input.readMessage(this.pin);
                        continue;
                    case LogSource.GOOGLE_ANALYTICS /*18*/:
                        if (this.password == null) {
                            this.password = new PasswordSettings();
                        }
                        input.readMessage(this.password);
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

        public static ReauthSettings parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (ReauthSettings) MessageNano.mergeFrom(new ReauthSettings(), data);
        }

        public static ReauthSettings parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new ReauthSettings().mergeFrom(input);
        }
    }
}
