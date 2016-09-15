package com.google.security.cryptauth.lib.risk;

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

public final class ScreenlockState extends ExtendableMessageNano<ScreenlockState> {
    private static volatile ScreenlockState[] _emptyArray;
    public long elapsedTimeSinceUnlockMillis;
    public boolean forcedScreenlock;
    public long screenlockSettingsAgeMillis;
    public int screenlockType;
    public boolean secureScreenlock;

    public static ScreenlockState[] emptyArray() {
        if (_emptyArray == null) {
            synchronized (InternalNano.LAZY_INIT_LOCK) {
                if (_emptyArray == null) {
                    _emptyArray = new ScreenlockState[0];
                }
            }
        }
        return _emptyArray;
    }

    public ScreenlockState() {
        clear();
    }

    public ScreenlockState clear() {
        this.secureScreenlock = false;
        this.screenlockType = 0;
        this.screenlockSettingsAgeMillis = 0;
        this.elapsedTimeSinceUnlockMillis = 0;
        this.forcedScreenlock = false;
        this.unknownFieldData = null;
        this.cachedSize = -1;
        return this;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ScreenlockState)) {
            return false;
        }
        ScreenlockState other = (ScreenlockState) o;
        if (this.secureScreenlock != other.secureScreenlock || this.screenlockType != other.screenlockType || this.screenlockSettingsAgeMillis != other.screenlockSettingsAgeMillis || this.elapsedTimeSinceUnlockMillis != other.elapsedTimeSinceUnlockMillis || this.forcedScreenlock != other.forcedScreenlock) {
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
        int hashCode = (((((((((getClass().getName().hashCode() + 527) * 31) + (this.secureScreenlock ? 1231 : 1237)) * 31) + this.screenlockType) * 31) + ((int) (this.screenlockSettingsAgeMillis ^ (this.screenlockSettingsAgeMillis >>> 32)))) * 31) + ((int) (this.elapsedTimeSinceUnlockMillis ^ (this.elapsedTimeSinceUnlockMillis >>> 32)))) * 31;
        if (!this.forcedScreenlock) {
            i = 1237;
        }
        i = (hashCode + i) * 31;
        hashCode = (this.unknownFieldData == null || this.unknownFieldData.isEmpty()) ? 0 : this.unknownFieldData.hashCode();
        return i + hashCode;
    }

    public void writeTo(CodedOutputByteBufferNano output) throws IOException {
        if (this.secureScreenlock) {
            output.writeBool(1, this.secureScreenlock);
        }
        if (this.screenlockType != 0) {
            output.writeInt32(2, this.screenlockType);
        }
        if (this.screenlockSettingsAgeMillis != 0) {
            output.writeInt64(3, this.screenlockSettingsAgeMillis);
        }
        if (this.elapsedTimeSinceUnlockMillis != 0) {
            output.writeInt64(4, this.elapsedTimeSinceUnlockMillis);
        }
        if (this.forcedScreenlock) {
            output.writeBool(5, this.forcedScreenlock);
        }
        super.writeTo(output);
    }

    protected int computeSerializedSize() {
        int size = super.computeSerializedSize();
        if (this.secureScreenlock) {
            size += CodedOutputByteBufferNano.computeBoolSize(1, this.secureScreenlock);
        }
        if (this.screenlockType != 0) {
            size += CodedOutputByteBufferNano.computeInt32Size(2, this.screenlockType);
        }
        if (this.screenlockSettingsAgeMillis != 0) {
            size += CodedOutputByteBufferNano.computeInt64Size(3, this.screenlockSettingsAgeMillis);
        }
        if (this.elapsedTimeSinceUnlockMillis != 0) {
            size += CodedOutputByteBufferNano.computeInt64Size(4, this.elapsedTimeSinceUnlockMillis);
        }
        if (this.forcedScreenlock) {
            return size + CodedOutputByteBufferNano.computeBoolSize(5, this.forcedScreenlock);
        }
        return size;
    }

    public ScreenlockState mergeFrom(CodedInputByteBufferNano input) throws IOException {
        while (true) {
            int tag = input.readTag();
            switch (tag) {
                case Action.UNKNOWN /*0*/:
                    break;
                case Type.TAP_GET_LINK /*8*/:
                    this.secureScreenlock = input.readBool();
                    continue;
                case LogSource.GMS_CORE_PEOPLE /*16*/:
                    int value = input.readInt32();
                    switch (value) {
                        case Action.UNKNOWN /*0*/:
                        case TimeSelection.Type.TEMPORARY /*1*/:
                        case Type.ADD_NEW_SHARES /*5*/:
                        case Type.TAP_ABOUT /*10*/:
                        case LogSource.ANDROID_GSA /*20*/:
                        case LogSource.UDR /*30*/:
                        case LogSource.COPRESENCE /*40*/:
                        case LogSource.HANGOUT_LOG_REQUEST /*50*/:
                            this.screenlockType = value;
                            break;
                        default:
                            continue;
                    }
                case LogSource.LB_C /*24*/:
                    this.screenlockSettingsAgeMillis = input.readInt64();
                    continue;
                case LogSource.SOCIAL /*32*/:
                    this.elapsedTimeSinceUnlockMillis = input.readInt64();
                    continue;
                case LogSource.COPRESENCE /*40*/:
                    this.forcedScreenlock = input.readBool();
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

    public static ScreenlockState parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
        return (ScreenlockState) MessageNano.mergeFrom(new ScreenlockState(), data);
    }

    public static ScreenlockState parseFrom(CodedInputByteBufferNano input) throws IOException {
        return new ScreenlockState().mergeFrom(input);
    }
}
