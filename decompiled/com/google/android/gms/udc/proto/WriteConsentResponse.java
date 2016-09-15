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

public final class WriteConsentResponse extends ExtendableMessageNano<WriteConsentResponse> {
    private static volatile WriteConsentResponse[] _emptyArray;
    public ApiResponseHeader apiHeader;
    public SettingChangeResult[] changedSetting;
    public SettingChangeRequest[] settingChangeRequests;

    public static WriteConsentResponse[] emptyArray() {
        if (_emptyArray == null) {
            synchronized (InternalNano.LAZY_INIT_LOCK) {
                if (_emptyArray == null) {
                    _emptyArray = new WriteConsentResponse[0];
                }
            }
        }
        return _emptyArray;
    }

    public WriteConsentResponse() {
        clear();
    }

    public WriteConsentResponse clear() {
        this.apiHeader = null;
        this.settingChangeRequests = SettingChangeRequest.emptyArray();
        this.changedSetting = SettingChangeResult.emptyArray();
        this.unknownFieldData = null;
        this.cachedSize = -1;
        return this;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof WriteConsentResponse)) {
            return false;
        }
        WriteConsentResponse other = (WriteConsentResponse) o;
        if (this.apiHeader == null) {
            if (other.apiHeader != null) {
                return false;
            }
        } else if (!this.apiHeader.equals(other.apiHeader)) {
            return false;
        }
        if (!InternalNano.equals(this.settingChangeRequests, other.settingChangeRequests) || !InternalNano.equals(this.changedSetting, other.changedSetting)) {
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
        int hashCode = (((((((getClass().getName().hashCode() + 527) * 31) + (this.apiHeader == null ? 0 : this.apiHeader.hashCode())) * 31) + InternalNano.hashCode(this.settingChangeRequests)) * 31) + InternalNano.hashCode(this.changedSetting)) * 31;
        if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
            i = this.unknownFieldData.hashCode();
        }
        return hashCode + i;
    }

    public void writeTo(CodedOutputByteBufferNano output) throws IOException {
        if (this.apiHeader != null) {
            output.writeMessage(1, this.apiHeader);
        }
        if (this.settingChangeRequests != null && this.settingChangeRequests.length > 0) {
            for (SettingChangeRequest element : this.settingChangeRequests) {
                if (element != null) {
                    output.writeMessage(2, element);
                }
            }
        }
        if (this.changedSetting != null && this.changedSetting.length > 0) {
            for (SettingChangeResult element2 : this.changedSetting) {
                if (element2 != null) {
                    output.writeMessage(3, element2);
                }
            }
        }
        super.writeTo(output);
    }

    protected int computeSerializedSize() {
        int size = super.computeSerializedSize();
        if (this.apiHeader != null) {
            size += CodedOutputByteBufferNano.computeMessageSize(1, this.apiHeader);
        }
        if (this.settingChangeRequests != null && this.settingChangeRequests.length > 0) {
            for (SettingChangeRequest element : this.settingChangeRequests) {
                if (element != null) {
                    size += CodedOutputByteBufferNano.computeMessageSize(2, element);
                }
            }
        }
        if (this.changedSetting != null && this.changedSetting.length > 0) {
            for (SettingChangeResult element2 : this.changedSetting) {
                if (element2 != null) {
                    size += CodedOutputByteBufferNano.computeMessageSize(3, element2);
                }
            }
        }
        return size;
    }

    public WriteConsentResponse mergeFrom(CodedInputByteBufferNano input) throws IOException {
        while (true) {
            int tag = input.readTag();
            int arrayLength;
            int i;
            switch (tag) {
                case Action.UNKNOWN /*0*/:
                    break;
                case Type.TAP_ABOUT /*10*/:
                    if (this.apiHeader == null) {
                        this.apiHeader = new ApiResponseHeader();
                    }
                    input.readMessage(this.apiHeader);
                    continue;
                case LogSource.GOOGLE_ANALYTICS /*18*/:
                    arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 18);
                    if (this.settingChangeRequests == null) {
                        i = 0;
                    } else {
                        i = this.settingChangeRequests.length;
                    }
                    SettingChangeRequest[] newArray = new SettingChangeRequest[(i + arrayLength)];
                    if (i != 0) {
                        System.arraycopy(this.settingChangeRequests, 0, newArray, 0, i);
                    }
                    while (i < newArray.length - 1) {
                        newArray[i] = new SettingChangeRequest();
                        input.readMessage(newArray[i]);
                        input.readTag();
                        i++;
                    }
                    newArray[i] = new SettingChangeRequest();
                    input.readMessage(newArray[i]);
                    this.settingChangeRequests = newArray;
                    continue;
                case LogSource.ANDROID_CAMERA /*26*/:
                    arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 26);
                    if (this.changedSetting == null) {
                        i = 0;
                    } else {
                        i = this.changedSetting.length;
                    }
                    SettingChangeResult[] newArray2 = new SettingChangeResult[(i + arrayLength)];
                    if (i != 0) {
                        System.arraycopy(this.changedSetting, 0, newArray2, 0, i);
                    }
                    while (i < newArray2.length - 1) {
                        newArray2[i] = new SettingChangeResult();
                        input.readMessage(newArray2[i]);
                        input.readTag();
                        i++;
                    }
                    newArray2[i] = new SettingChangeResult();
                    input.readMessage(newArray2[i]);
                    this.changedSetting = newArray2;
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

    public static WriteConsentResponse parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
        return (WriteConsentResponse) MessageNano.mergeFrom(new WriteConsentResponse(), data);
    }

    public static WriteConsentResponse parseFrom(CodedInputByteBufferNano input) throws IOException {
        return new WriteConsentResponse().mergeFrom(input);
    }
}
