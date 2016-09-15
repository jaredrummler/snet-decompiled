package com.google.android.gms.udc.proto;

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
import com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection;
import java.io.IOException;
import java.util.Arrays;

public final class WriteConsentRequest extends ExtendableMessageNano<WriteConsentRequest> {
    private static volatile WriteConsentRequest[] _emptyArray;
    public ApiRequestHeader apiHeader;
    public Consent consent;
    public String userId;

    public static final class Consent extends ExtendableMessageNano<Consent> {
        private static volatile Consent[] _emptyArray;
        public byte[] consentSession;
        public int newValue;
        public int[] settingIds;
        public UiFingerprint uiFingerprint;

        public static Consent[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new Consent[0];
                    }
                }
            }
            return _emptyArray;
        }

        public Consent() {
            clear();
        }

        public Consent clear() {
            this.consentSession = WireFormatNano.EMPTY_BYTES;
            this.settingIds = WireFormatNano.EMPTY_INT_ARRAY;
            this.newValue = 0;
            this.uiFingerprint = null;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof Consent)) {
                return false;
            }
            Consent other = (Consent) o;
            if (!Arrays.equals(this.consentSession, other.consentSession) || !InternalNano.equals(this.settingIds, other.settingIds) || this.newValue != other.newValue) {
                return false;
            }
            if (this.uiFingerprint == null) {
                if (other.uiFingerprint != null) {
                    return false;
                }
            } else if (!this.uiFingerprint.equals(other.uiFingerprint)) {
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
            int hashCode = (((((((((getClass().getName().hashCode() + 527) * 31) + Arrays.hashCode(this.consentSession)) * 31) + InternalNano.hashCode(this.settingIds)) * 31) + this.newValue) * 31) + (this.uiFingerprint == null ? 0 : this.uiFingerprint.hashCode())) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (!Arrays.equals(this.consentSession, WireFormatNano.EMPTY_BYTES)) {
                output.writeBytes(1, this.consentSession);
            }
            if (this.settingIds != null && this.settingIds.length > 0) {
                for (int writeInt32 : this.settingIds) {
                    output.writeInt32(2, writeInt32);
                }
            }
            if (this.newValue != 0) {
                output.writeInt32(3, this.newValue);
            }
            if (this.uiFingerprint != null) {
                output.writeMessage(4, this.uiFingerprint);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (!Arrays.equals(this.consentSession, WireFormatNano.EMPTY_BYTES)) {
                size += CodedOutputByteBufferNano.computeBytesSize(1, this.consentSession);
            }
            if (this.settingIds != null && this.settingIds.length > 0) {
                int dataSize = 0;
                for (int element : this.settingIds) {
                    dataSize += CodedOutputByteBufferNano.computeInt32SizeNoTag(element);
                }
                size = (size + dataSize) + (this.settingIds.length * 1);
            }
            if (this.newValue != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(3, this.newValue);
            }
            if (this.uiFingerprint != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(4, this.uiFingerprint);
            }
            return size;
        }

        public Consent mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                int arrayLength;
                int i;
                int[] newArray;
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        this.consentSession = input.readBytes();
                        continue;
                    case LogSource.GMS_CORE_PEOPLE /*16*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 16);
                        i = this.settingIds == null ? 0 : this.settingIds.length;
                        newArray = new int[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.settingIds, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = input.readInt32();
                            input.readTag();
                            i++;
                        }
                        newArray[i] = input.readInt32();
                        this.settingIds = newArray;
                        continue;
                    case LogSource.GOOGLE_ANALYTICS /*18*/:
                        int limit = input.pushLimit(input.readRawVarint32());
                        arrayLength = 0;
                        int startPos = input.getPosition();
                        while (input.getBytesUntilLimit() > 0) {
                            input.readInt32();
                            arrayLength++;
                        }
                        input.rewindToPosition(startPos);
                        i = this.settingIds == null ? 0 : this.settingIds.length;
                        newArray = new int[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.settingIds, 0, newArray, 0, i);
                        }
                        while (i < newArray.length) {
                            newArray[i] = input.readInt32();
                            i++;
                        }
                        this.settingIds = newArray;
                        input.popLimit(limit);
                        continue;
                    case LogSource.LB_C /*24*/:
                        int value = input.readInt32();
                        switch (value) {
                            case Action.UNKNOWN /*0*/:
                            case TimeSelection.Type.TEMPORARY /*1*/:
                            case TimeSelection.Type.INDEFINITELY /*2*/:
                            case TimeSelection.Type.CUSTOM /*3*/:
                                this.newValue = value;
                                break;
                            default:
                                continue;
                        }
                    case LogSource.NOVA /*34*/:
                        if (this.uiFingerprint == null) {
                            this.uiFingerprint = new UiFingerprint();
                        }
                        input.readMessage(this.uiFingerprint);
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

        public static Consent parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (Consent) MessageNano.mergeFrom(new Consent(), data);
        }

        public static Consent parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new Consent().mergeFrom(input);
        }
    }

    public static WriteConsentRequest[] emptyArray() {
        if (_emptyArray == null) {
            synchronized (InternalNano.LAZY_INIT_LOCK) {
                if (_emptyArray == null) {
                    _emptyArray = new WriteConsentRequest[0];
                }
            }
        }
        return _emptyArray;
    }

    public WriteConsentRequest() {
        clear();
    }

    public WriteConsentRequest clear() {
        this.apiHeader = null;
        this.userId = BuildConfig.VERSION_NAME;
        this.consent = null;
        this.unknownFieldData = null;
        this.cachedSize = -1;
        return this;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof WriteConsentRequest)) {
            return false;
        }
        WriteConsentRequest other = (WriteConsentRequest) o;
        if (this.apiHeader == null) {
            if (other.apiHeader != null) {
                return false;
            }
        } else if (!this.apiHeader.equals(other.apiHeader)) {
            return false;
        }
        if (this.userId == null) {
            if (other.userId != null) {
                return false;
            }
        } else if (!this.userId.equals(other.userId)) {
            return false;
        }
        if (this.consent == null) {
            if (other.consent != null) {
                return false;
            }
        } else if (!this.consent.equals(other.consent)) {
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
        int hashCode = (((((((getClass().getName().hashCode() + 527) * 31) + (this.apiHeader == null ? 0 : this.apiHeader.hashCode())) * 31) + (this.userId == null ? 0 : this.userId.hashCode())) * 31) + (this.consent == null ? 0 : this.consent.hashCode())) * 31;
        if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
            i = this.unknownFieldData.hashCode();
        }
        return hashCode + i;
    }

    public void writeTo(CodedOutputByteBufferNano output) throws IOException {
        if (this.consent != null) {
            output.writeMessage(1, this.consent);
        }
        if (this.apiHeader != null) {
            output.writeMessage(2, this.apiHeader);
        }
        if (!this.userId.equals(BuildConfig.VERSION_NAME)) {
            output.writeString(3, this.userId);
        }
        super.writeTo(output);
    }

    protected int computeSerializedSize() {
        int size = super.computeSerializedSize();
        if (this.consent != null) {
            size += CodedOutputByteBufferNano.computeMessageSize(1, this.consent);
        }
        if (this.apiHeader != null) {
            size += CodedOutputByteBufferNano.computeMessageSize(2, this.apiHeader);
        }
        if (this.userId.equals(BuildConfig.VERSION_NAME)) {
            return size;
        }
        return size + CodedOutputByteBufferNano.computeStringSize(3, this.userId);
    }

    public WriteConsentRequest mergeFrom(CodedInputByteBufferNano input) throws IOException {
        while (true) {
            int tag = input.readTag();
            switch (tag) {
                case Action.UNKNOWN /*0*/:
                    break;
                case Type.TAP_ABOUT /*10*/:
                    if (this.consent == null) {
                        this.consent = new Consent();
                    }
                    input.readMessage(this.consent);
                    continue;
                case LogSource.GOOGLE_ANALYTICS /*18*/:
                    if (this.apiHeader == null) {
                        this.apiHeader = new ApiRequestHeader();
                    }
                    input.readMessage(this.apiHeader);
                    continue;
                case LogSource.ANDROID_CAMERA /*26*/:
                    this.userId = input.readString();
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

    public static WriteConsentRequest parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
        return (WriteConsentRequest) MessageNano.mergeFrom(new WriteConsentRequest(), data);
    }

    public static WriteConsentRequest parseFrom(CodedInputByteBufferNano input) throws IOException {
        return new WriteConsentRequest().mergeFrom(input);
    }
}
