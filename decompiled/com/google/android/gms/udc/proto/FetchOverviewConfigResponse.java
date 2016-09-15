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

public final class FetchOverviewConfigResponse extends ExtendableMessageNano<FetchOverviewConfigResponse> {
    private static volatile FetchOverviewConfigResponse[] _emptyArray;
    public ApiResponseHeader apiHeader;
    public TextResource contextTitle;
    public SettingOverviewConfig[] settingConfigs;

    public static FetchOverviewConfigResponse[] emptyArray() {
        if (_emptyArray == null) {
            synchronized (InternalNano.LAZY_INIT_LOCK) {
                if (_emptyArray == null) {
                    _emptyArray = new FetchOverviewConfigResponse[0];
                }
            }
        }
        return _emptyArray;
    }

    public FetchOverviewConfigResponse() {
        clear();
    }

    public FetchOverviewConfigResponse clear() {
        this.apiHeader = null;
        this.contextTitle = null;
        this.settingConfigs = SettingOverviewConfig.emptyArray();
        this.unknownFieldData = null;
        this.cachedSize = -1;
        return this;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof FetchOverviewConfigResponse)) {
            return false;
        }
        FetchOverviewConfigResponse other = (FetchOverviewConfigResponse) o;
        if (this.apiHeader == null) {
            if (other.apiHeader != null) {
                return false;
            }
        } else if (!this.apiHeader.equals(other.apiHeader)) {
            return false;
        }
        if (this.contextTitle == null) {
            if (other.contextTitle != null) {
                return false;
            }
        } else if (!this.contextTitle.equals(other.contextTitle)) {
            return false;
        }
        if (!InternalNano.equals(this.settingConfigs, other.settingConfigs)) {
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
        int hashCode = (((((((getClass().getName().hashCode() + 527) * 31) + (this.apiHeader == null ? 0 : this.apiHeader.hashCode())) * 31) + (this.contextTitle == null ? 0 : this.contextTitle.hashCode())) * 31) + InternalNano.hashCode(this.settingConfigs)) * 31;
        if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
            i = this.unknownFieldData.hashCode();
        }
        return hashCode + i;
    }

    public void writeTo(CodedOutputByteBufferNano output) throws IOException {
        if (this.contextTitle != null) {
            output.writeMessage(1, this.contextTitle);
        }
        if (this.settingConfigs != null && this.settingConfigs.length > 0) {
            for (SettingOverviewConfig element : this.settingConfigs) {
                if (element != null) {
                    output.writeMessage(2, element);
                }
            }
        }
        if (this.apiHeader != null) {
            output.writeMessage(3, this.apiHeader);
        }
        super.writeTo(output);
    }

    protected int computeSerializedSize() {
        int size = super.computeSerializedSize();
        if (this.contextTitle != null) {
            size += CodedOutputByteBufferNano.computeMessageSize(1, this.contextTitle);
        }
        if (this.settingConfigs != null && this.settingConfigs.length > 0) {
            for (SettingOverviewConfig element : this.settingConfigs) {
                if (element != null) {
                    size += CodedOutputByteBufferNano.computeMessageSize(2, element);
                }
            }
        }
        if (this.apiHeader != null) {
            return size + CodedOutputByteBufferNano.computeMessageSize(3, this.apiHeader);
        }
        return size;
    }

    public FetchOverviewConfigResponse mergeFrom(CodedInputByteBufferNano input) throws IOException {
        while (true) {
            int tag = input.readTag();
            switch (tag) {
                case Action.UNKNOWN /*0*/:
                    break;
                case Type.TAP_ABOUT /*10*/:
                    if (this.contextTitle == null) {
                        this.contextTitle = new TextResource();
                    }
                    input.readMessage(this.contextTitle);
                    continue;
                case LogSource.GOOGLE_ANALYTICS /*18*/:
                    int i;
                    int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 18);
                    if (this.settingConfigs == null) {
                        i = 0;
                    } else {
                        i = this.settingConfigs.length;
                    }
                    SettingOverviewConfig[] newArray = new SettingOverviewConfig[(i + arrayLength)];
                    if (i != 0) {
                        System.arraycopy(this.settingConfigs, 0, newArray, 0, i);
                    }
                    while (i < newArray.length - 1) {
                        newArray[i] = new SettingOverviewConfig();
                        input.readMessage(newArray[i]);
                        input.readTag();
                        i++;
                    }
                    newArray[i] = new SettingOverviewConfig();
                    input.readMessage(newArray[i]);
                    this.settingConfigs = newArray;
                    continue;
                case LogSource.ANDROID_CAMERA /*26*/:
                    if (this.apiHeader == null) {
                        this.apiHeader = new ApiResponseHeader();
                    }
                    input.readMessage(this.apiHeader);
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

    public static FetchOverviewConfigResponse parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
        return (FetchOverviewConfigResponse) MessageNano.mergeFrom(new FetchOverviewConfigResponse(), data);
    }

    public static FetchOverviewConfigResponse parseFrom(CodedInputByteBufferNano input) throws IOException {
        return new FetchOverviewConfigResponse().mergeFrom(input);
    }
}
