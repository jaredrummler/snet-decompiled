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
import java.io.IOException;

public final class FetchOverviewConfigRequest extends ExtendableMessageNano<FetchOverviewConfigRequest> {
    private static volatile FetchOverviewConfigRequest[] _emptyArray;
    public ApiRequestHeader apiHeader;
    public ClientManagedSettingState[] clientManagedSettingStates;
    public boolean fetchAll;
    public int[] settingIds;
    public UserEnvironment userEnvironment;
    public String userId;

    public static FetchOverviewConfigRequest[] emptyArray() {
        if (_emptyArray == null) {
            synchronized (InternalNano.LAZY_INIT_LOCK) {
                if (_emptyArray == null) {
                    _emptyArray = new FetchOverviewConfigRequest[0];
                }
            }
        }
        return _emptyArray;
    }

    public FetchOverviewConfigRequest() {
        clear();
    }

    public FetchOverviewConfigRequest clear() {
        this.apiHeader = null;
        this.userId = BuildConfig.VERSION_NAME;
        this.fetchAll = false;
        this.settingIds = WireFormatNano.EMPTY_INT_ARRAY;
        this.userEnvironment = null;
        this.clientManagedSettingStates = ClientManagedSettingState.emptyArray();
        this.unknownFieldData = null;
        this.cachedSize = -1;
        return this;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof FetchOverviewConfigRequest)) {
            return false;
        }
        FetchOverviewConfigRequest other = (FetchOverviewConfigRequest) o;
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
        if (this.fetchAll != other.fetchAll || !InternalNano.equals(this.settingIds, other.settingIds)) {
            return false;
        }
        if (this.userEnvironment == null) {
            if (other.userEnvironment != null) {
                return false;
            }
        } else if (!this.userEnvironment.equals(other.userEnvironment)) {
            return false;
        }
        if (!InternalNano.equals(this.clientManagedSettingStates, other.clientManagedSettingStates)) {
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
        int hashCode = (((((((((((((getClass().getName().hashCode() + 527) * 31) + (this.apiHeader == null ? 0 : this.apiHeader.hashCode())) * 31) + (this.userId == null ? 0 : this.userId.hashCode())) * 31) + (this.fetchAll ? 1231 : 1237)) * 31) + InternalNano.hashCode(this.settingIds)) * 31) + (this.userEnvironment == null ? 0 : this.userEnvironment.hashCode())) * 31) + InternalNano.hashCode(this.clientManagedSettingStates)) * 31;
        if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
            i = this.unknownFieldData.hashCode();
        }
        return hashCode + i;
    }

    public void writeTo(CodedOutputByteBufferNano output) throws IOException {
        if (this.settingIds != null && this.settingIds.length > 0) {
            for (int writeInt32 : this.settingIds) {
                output.writeInt32(1, writeInt32);
            }
        }
        if (this.userEnvironment != null) {
            output.writeMessage(2, this.userEnvironment);
        }
        if (this.fetchAll) {
            output.writeBool(3, this.fetchAll);
        }
        if (this.apiHeader != null) {
            output.writeMessage(4, this.apiHeader);
        }
        if (!this.userId.equals(BuildConfig.VERSION_NAME)) {
            output.writeString(5, this.userId);
        }
        if (this.clientManagedSettingStates != null && this.clientManagedSettingStates.length > 0) {
            for (ClientManagedSettingState element : this.clientManagedSettingStates) {
                if (element != null) {
                    output.writeMessage(6, element);
                }
            }
        }
        super.writeTo(output);
    }

    protected int computeSerializedSize() {
        int size = super.computeSerializedSize();
        if (this.settingIds != null && this.settingIds.length > 0) {
            int dataSize = 0;
            for (int element : this.settingIds) {
                dataSize += CodedOutputByteBufferNano.computeInt32SizeNoTag(element);
            }
            size = (size + dataSize) + (this.settingIds.length * 1);
        }
        if (this.userEnvironment != null) {
            size += CodedOutputByteBufferNano.computeMessageSize(2, this.userEnvironment);
        }
        if (this.fetchAll) {
            size += CodedOutputByteBufferNano.computeBoolSize(3, this.fetchAll);
        }
        if (this.apiHeader != null) {
            size += CodedOutputByteBufferNano.computeMessageSize(4, this.apiHeader);
        }
        if (!this.userId.equals(BuildConfig.VERSION_NAME)) {
            size += CodedOutputByteBufferNano.computeStringSize(5, this.userId);
        }
        if (this.clientManagedSettingStates != null && this.clientManagedSettingStates.length > 0) {
            for (ClientManagedSettingState element2 : this.clientManagedSettingStates) {
                if (element2 != null) {
                    size += CodedOutputByteBufferNano.computeMessageSize(6, element2);
                }
            }
        }
        return size;
    }

    public FetchOverviewConfigRequest mergeFrom(CodedInputByteBufferNano input) throws IOException {
        while (true) {
            int tag = input.readTag();
            int arrayLength;
            int i;
            int[] newArray;
            switch (tag) {
                case Action.UNKNOWN /*0*/:
                    break;
                case Type.TAP_GET_LINK /*8*/:
                    arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 8);
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
                case Type.TAP_ABOUT /*10*/:
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
                case LogSource.GOOGLE_ANALYTICS /*18*/:
                    if (this.userEnvironment == null) {
                        this.userEnvironment = new UserEnvironment();
                    }
                    input.readMessage(this.userEnvironment);
                    continue;
                case LogSource.LB_C /*24*/:
                    this.fetchAll = input.readBool();
                    continue;
                case LogSource.NOVA /*34*/:
                    if (this.apiHeader == null) {
                        this.apiHeader = new ApiRequestHeader();
                    }
                    input.readMessage(this.apiHeader);
                    continue;
                case LogSource.PHOTOS /*42*/:
                    this.userId = input.readString();
                    continue;
                case LogSource.HANGOUT_LOG_REQUEST /*50*/:
                    arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 50);
                    if (this.clientManagedSettingStates == null) {
                        i = 0;
                    } else {
                        i = this.clientManagedSettingStates.length;
                    }
                    ClientManagedSettingState[] newArray2 = new ClientManagedSettingState[(i + arrayLength)];
                    if (i != 0) {
                        System.arraycopy(this.clientManagedSettingStates, 0, newArray2, 0, i);
                    }
                    while (i < newArray2.length - 1) {
                        newArray2[i] = new ClientManagedSettingState();
                        input.readMessage(newArray2[i]);
                        input.readTag();
                        i++;
                    }
                    newArray2[i] = new ClientManagedSettingState();
                    input.readMessage(newArray2[i]);
                    this.clientManagedSettingStates = newArray2;
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

    public static FetchOverviewConfigRequest parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
        return (FetchOverviewConfigRequest) MessageNano.mergeFrom(new FetchOverviewConfigRequest(), data);
    }

    public static FetchOverviewConfigRequest parseFrom(CodedInputByteBufferNano input) throws IOException {
        return new FetchOverviewConfigRequest().mergeFrom(input);
    }
}
