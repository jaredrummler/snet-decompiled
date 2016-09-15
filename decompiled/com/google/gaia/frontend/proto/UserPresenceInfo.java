package com.google.gaia.frontend.proto;

import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.ExtendableMessageNano;
import com.google.protobuf.nano.InternalNano;
import com.google.protobuf.nano.InvalidProtocolBufferNanoException;
import com.google.protobuf.nano.MessageNano;
import com.google.wireless.android.play.playlog.proto.ClientAnalytics.LogRequest.LogSource;
import com.google.wireless.android.play.playlog.proto.UserLocation.LaunchSettingsEvent.Action;
import com.google.wireless.android.play.playlog.proto.UserLocation.LocationSharingEvent.Type;
import com.google.wireless.android.play.playlog.proto.UserLocation.OvenFreshEvent.OvenFreshResult;
import com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection;
import java.io.IOException;

public final class UserPresenceInfo extends ExtendableMessageNano<UserPresenceInfo> {
    private static volatile UserPresenceInfo[] _emptyArray;
    public long lastUnlockDurationInS;
    public int lockScreenQuality;
    public long lockScreenSetupDurationInS;
    public boolean secureLockScreen;
    public boolean showLockScreen;

    public static UserPresenceInfo[] emptyArray() {
        if (_emptyArray == null) {
            synchronized (InternalNano.LAZY_INIT_LOCK) {
                if (_emptyArray == null) {
                    _emptyArray = new UserPresenceInfo[0];
                }
            }
        }
        return _emptyArray;
    }

    public UserPresenceInfo() {
        clear();
    }

    public UserPresenceInfo clear() {
        this.secureLockScreen = false;
        this.showLockScreen = false;
        this.lockScreenQuality = 1;
        this.lastUnlockDurationInS = 0;
        this.lockScreenSetupDurationInS = 0;
        this.unknownFieldData = null;
        this.cachedSize = -1;
        return this;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof UserPresenceInfo)) {
            return false;
        }
        UserPresenceInfo other = (UserPresenceInfo) o;
        if (this.secureLockScreen != other.secureLockScreen || this.showLockScreen != other.showLockScreen || this.lockScreenQuality != other.lockScreenQuality || this.lastUnlockDurationInS != other.lastUnlockDurationInS || this.lockScreenSetupDurationInS != other.lockScreenSetupDurationInS) {
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
        int i = 1231;
        int hashCode = (((getClass().getName().hashCode() + 527) * 31) + (this.secureLockScreen ? 1231 : 1237)) * 31;
        if (!this.showLockScreen) {
            i = 1237;
        }
        i = (((((((hashCode + i) * 31) + this.lockScreenQuality) * 31) + ((int) (this.lastUnlockDurationInS ^ (this.lastUnlockDurationInS >>> 32)))) * 31) + ((int) (this.lockScreenSetupDurationInS ^ (this.lockScreenSetupDurationInS >>> 32)))) * 31;
        hashCode = (this.unknownFieldData == null || this.unknownFieldData.isEmpty()) ? 0 : this.unknownFieldData.hashCode();
        return i + hashCode;
    }

    public void writeTo(CodedOutputByteBufferNano output) throws IOException {
        if (this.secureLockScreen) {
            output.writeBool(1, this.secureLockScreen);
        }
        if (this.showLockScreen) {
            output.writeBool(2, this.showLockScreen);
        }
        if (this.lockScreenQuality != 1) {
            output.writeInt32(3, this.lockScreenQuality);
        }
        if (this.lastUnlockDurationInS != 0) {
            output.writeInt64(4, this.lastUnlockDurationInS);
        }
        if (this.lockScreenSetupDurationInS != 0) {
            output.writeInt64(5, this.lockScreenSetupDurationInS);
        }
        super.writeTo(output);
    }

    protected int computeSerializedSize() {
        int size = super.computeSerializedSize();
        if (this.secureLockScreen) {
            size += CodedOutputByteBufferNano.computeBoolSize(1, this.secureLockScreen);
        }
        if (this.showLockScreen) {
            size += CodedOutputByteBufferNano.computeBoolSize(2, this.showLockScreen);
        }
        if (this.lockScreenQuality != 1) {
            size += CodedOutputByteBufferNano.computeInt32Size(3, this.lockScreenQuality);
        }
        if (this.lastUnlockDurationInS != 0) {
            size += CodedOutputByteBufferNano.computeInt64Size(4, this.lastUnlockDurationInS);
        }
        if (this.lockScreenSetupDurationInS != 0) {
            return size + CodedOutputByteBufferNano.computeInt64Size(5, this.lockScreenSetupDurationInS);
        }
        return size;
    }

    public UserPresenceInfo mergeFrom(CodedInputByteBufferNano input) throws IOException {
        while (true) {
            int tag = input.readTag();
            switch (tag) {
                case Action.UNKNOWN /*0*/:
                    break;
                case Type.TAP_GET_LINK /*8*/:
                    this.secureLockScreen = input.readBool();
                    continue;
                case LogSource.GMS_CORE_PEOPLE /*16*/:
                    this.showLockScreen = input.readBool();
                    continue;
                case LogSource.LB_C /*24*/:
                    int value = input.readInt32();
                    switch (value) {
                        case TimeSelection.Type.TEMPORARY /*1*/:
                        case TimeSelection.Type.INDEFINITELY /*2*/:
                        case TimeSelection.Type.CUSTOM /*3*/:
                        case OvenFreshResult.NO_ACCOUNTS /*4*/:
                        case Type.ADD_NEW_SHARES /*5*/:
                        case Type.REMOVE_SHARE /*6*/:
                            this.lockScreenQuality = value;
                            break;
                        default:
                            continue;
                    }
                case LogSource.SOCIAL /*32*/:
                    this.lastUnlockDurationInS = input.readInt64();
                    continue;
                case LogSource.COPRESENCE /*40*/:
                    this.lockScreenSetupDurationInS = input.readInt64();
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

    public static UserPresenceInfo parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
        return (UserPresenceInfo) MessageNano.mergeFrom(new UserPresenceInfo(), data);
    }

    public static UserPresenceInfo parseFrom(CodedInputByteBufferNano input) throws IOException {
        return new UserPresenceInfo().mergeFrom(input);
    }
}
