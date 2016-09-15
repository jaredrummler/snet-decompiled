package com.google.android.gms.udc.proto;

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

public final class FetchSettingsResponse extends ExtendableMessageNano<FetchSettingsResponse> {
    private static volatile FetchSettingsResponse[] _emptyArray;
    public ApiResponseHeader apiHeader;
    public ConsentFlowPermission consentFlowPermission;
    public SettingState[] settingStates;

    public static final class ConsentFlowPermission extends ExtendableMessageNano<ConsentFlowPermission> {
        private static volatile ConsentFlowPermission[] _emptyArray;
        public boolean allowConsentFlow;

        public static ConsentFlowPermission[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new ConsentFlowPermission[0];
                    }
                }
            }
            return _emptyArray;
        }

        public ConsentFlowPermission() {
            clear();
        }

        public ConsentFlowPermission clear() {
            this.allowConsentFlow = false;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof ConsentFlowPermission)) {
                return false;
            }
            ConsentFlowPermission other = (ConsentFlowPermission) o;
            if (this.allowConsentFlow != other.allowConsentFlow) {
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
            int hashCode = (((getClass().getName().hashCode() + 527) * 31) + (this.allowConsentFlow ? 1231 : 1237)) * 31;
            int hashCode2 = (this.unknownFieldData == null || this.unknownFieldData.isEmpty()) ? 0 : this.unknownFieldData.hashCode();
            return hashCode + hashCode2;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.allowConsentFlow) {
                output.writeBool(1, this.allowConsentFlow);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.allowConsentFlow) {
                return size + CodedOutputByteBufferNano.computeBoolSize(1, this.allowConsentFlow);
            }
            return size;
        }

        public ConsentFlowPermission mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_GET_LINK /*8*/:
                        this.allowConsentFlow = input.readBool();
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

        public static ConsentFlowPermission parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (ConsentFlowPermission) MessageNano.mergeFrom(new ConsentFlowPermission(), data);
        }

        public static ConsentFlowPermission parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new ConsentFlowPermission().mergeFrom(input);
        }
    }

    public static FetchSettingsResponse[] emptyArray() {
        if (_emptyArray == null) {
            synchronized (InternalNano.LAZY_INIT_LOCK) {
                if (_emptyArray == null) {
                    _emptyArray = new FetchSettingsResponse[0];
                }
            }
        }
        return _emptyArray;
    }

    public FetchSettingsResponse() {
        clear();
    }

    public FetchSettingsResponse clear() {
        this.apiHeader = null;
        this.settingStates = SettingState.emptyArray();
        this.consentFlowPermission = null;
        this.unknownFieldData = null;
        this.cachedSize = -1;
        return this;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof FetchSettingsResponse)) {
            return false;
        }
        FetchSettingsResponse other = (FetchSettingsResponse) o;
        if (this.apiHeader == null) {
            if (other.apiHeader != null) {
                return false;
            }
        } else if (!this.apiHeader.equals(other.apiHeader)) {
            return false;
        }
        if (!InternalNano.equals(this.settingStates, other.settingStates)) {
            return false;
        }
        if (this.consentFlowPermission == null) {
            if (other.consentFlowPermission != null) {
                return false;
            }
        } else if (!this.consentFlowPermission.equals(other.consentFlowPermission)) {
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
        int hashCode = (((((((getClass().getName().hashCode() + 527) * 31) + (this.apiHeader == null ? 0 : this.apiHeader.hashCode())) * 31) + InternalNano.hashCode(this.settingStates)) * 31) + (this.consentFlowPermission == null ? 0 : this.consentFlowPermission.hashCode())) * 31;
        if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
            i = this.unknownFieldData.hashCode();
        }
        return hashCode + i;
    }

    public void writeTo(CodedOutputByteBufferNano output) throws IOException {
        if (this.settingStates != null && this.settingStates.length > 0) {
            for (SettingState element : this.settingStates) {
                if (element != null) {
                    output.writeMessage(1, element);
                }
            }
        }
        if (this.apiHeader != null) {
            output.writeMessage(2, this.apiHeader);
        }
        if (this.consentFlowPermission != null) {
            output.writeMessage(3, this.consentFlowPermission);
        }
        super.writeTo(output);
    }

    protected int computeSerializedSize() {
        int size = super.computeSerializedSize();
        if (this.settingStates != null && this.settingStates.length > 0) {
            for (SettingState element : this.settingStates) {
                if (element != null) {
                    size += CodedOutputByteBufferNano.computeMessageSize(1, element);
                }
            }
        }
        if (this.apiHeader != null) {
            size += CodedOutputByteBufferNano.computeMessageSize(2, this.apiHeader);
        }
        if (this.consentFlowPermission != null) {
            return size + CodedOutputByteBufferNano.computeMessageSize(3, this.consentFlowPermission);
        }
        return size;
    }

    public FetchSettingsResponse mergeFrom(CodedInputByteBufferNano input) throws IOException {
        while (true) {
            int tag = input.readTag();
            switch (tag) {
                case Action.UNKNOWN /*0*/:
                    break;
                case Type.TAP_ABOUT /*10*/:
                    int i;
                    int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 10);
                    if (this.settingStates == null) {
                        i = 0;
                    } else {
                        i = this.settingStates.length;
                    }
                    SettingState[] newArray = new SettingState[(i + arrayLength)];
                    if (i != 0) {
                        System.arraycopy(this.settingStates, 0, newArray, 0, i);
                    }
                    while (i < newArray.length - 1) {
                        newArray[i] = new SettingState();
                        input.readMessage(newArray[i]);
                        input.readTag();
                        i++;
                    }
                    newArray[i] = new SettingState();
                    input.readMessage(newArray[i]);
                    this.settingStates = newArray;
                    continue;
                case LogSource.GOOGLE_ANALYTICS /*18*/:
                    if (this.apiHeader == null) {
                        this.apiHeader = new ApiResponseHeader();
                    }
                    input.readMessage(this.apiHeader);
                    continue;
                case LogSource.ANDROID_CAMERA /*26*/:
                    if (this.consentFlowPermission == null) {
                        this.consentFlowPermission = new ConsentFlowPermission();
                    }
                    input.readMessage(this.consentFlowPermission);
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

    public static FetchSettingsResponse parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
        return (FetchSettingsResponse) MessageNano.mergeFrom(new FetchSettingsResponse(), data);
    }

    public static FetchSettingsResponse parseFrom(CodedInputByteBufferNano input) throws IOException {
        return new FetchSettingsResponse().mergeFrom(input);
    }
}
