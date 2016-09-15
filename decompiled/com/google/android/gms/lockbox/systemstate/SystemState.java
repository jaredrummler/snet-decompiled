package com.google.android.gms.lockbox.systemstate;

import com.google.android.gms.lockbox.OptInState.LockboxOptInState;
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

public interface SystemState {

    public static final class SystemStateEvent extends ExtendableMessageNano<SystemStateEvent> {
        private static volatile SystemStateEvent[] _emptyArray;
        public LockboxOptInState lockboxOptInState;
        public int screenState;
        public long serverTimestampMillis;
        public boolean stateChange;
        public long timestampMillis;

        public interface ScreenState {
            public static final int SCREEN_STATE_NONE = 0;
            public static final int SCREEN_STATE_OFF = 2;
            public static final int SCREEN_STATE_ON = 1;
        }

        public static SystemStateEvent[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new SystemStateEvent[0];
                    }
                }
            }
            return _emptyArray;
        }

        public SystemStateEvent() {
            clear();
        }

        public SystemStateEvent clear() {
            this.lockboxOptInState = null;
            this.timestampMillis = 0;
            this.serverTimestampMillis = 0;
            this.stateChange = false;
            this.screenState = 0;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof SystemStateEvent)) {
                return false;
            }
            SystemStateEvent other = (SystemStateEvent) o;
            if (this.lockboxOptInState == null) {
                if (other.lockboxOptInState != null) {
                    return false;
                }
            } else if (!this.lockboxOptInState.equals(other.lockboxOptInState)) {
                return false;
            }
            if (this.timestampMillis != other.timestampMillis || this.serverTimestampMillis != other.serverTimestampMillis || this.stateChange != other.stateChange || this.screenState != other.screenState) {
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
            int hashCode = (((((((((((getClass().getName().hashCode() + 527) * 31) + (this.lockboxOptInState == null ? 0 : this.lockboxOptInState.hashCode())) * 31) + ((int) (this.timestampMillis ^ (this.timestampMillis >>> 32)))) * 31) + ((int) (this.serverTimestampMillis ^ (this.serverTimestampMillis >>> 32)))) * 31) + (this.stateChange ? 1231 : 1237)) * 31) + this.screenState) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.timestampMillis != 0) {
                output.writeInt64(1, this.timestampMillis);
            }
            if (this.stateChange) {
                output.writeBool(2, this.stateChange);
            }
            if (this.screenState != 0) {
                output.writeInt32(3, this.screenState);
            }
            if (this.serverTimestampMillis != 0) {
                output.writeInt64(4, this.serverTimestampMillis);
            }
            if (this.lockboxOptInState != null) {
                output.writeMessage(5, this.lockboxOptInState);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.timestampMillis != 0) {
                size += CodedOutputByteBufferNano.computeInt64Size(1, this.timestampMillis);
            }
            if (this.stateChange) {
                size += CodedOutputByteBufferNano.computeBoolSize(2, this.stateChange);
            }
            if (this.screenState != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(3, this.screenState);
            }
            if (this.serverTimestampMillis != 0) {
                size += CodedOutputByteBufferNano.computeInt64Size(4, this.serverTimestampMillis);
            }
            if (this.lockboxOptInState != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(5, this.lockboxOptInState);
            }
            return size;
        }

        public SystemStateEvent mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_GET_LINK /*8*/:
                        this.timestampMillis = input.readInt64();
                        continue;
                    case LogSource.GMS_CORE_PEOPLE /*16*/:
                        this.stateChange = input.readBool();
                        continue;
                    case LogSource.LB_C /*24*/:
                        int value = input.readInt32();
                        switch (value) {
                            case Action.UNKNOWN /*0*/:
                            case TimeSelection.Type.TEMPORARY /*1*/:
                            case TimeSelection.Type.INDEFINITELY /*2*/:
                                this.screenState = value;
                                break;
                            default:
                                continue;
                        }
                    case LogSource.SOCIAL /*32*/:
                        this.serverTimestampMillis = input.readInt64();
                        continue;
                    case LogSource.PHOTOS /*42*/:
                        if (this.lockboxOptInState == null) {
                            this.lockboxOptInState = new LockboxOptInState();
                        }
                        input.readMessage(this.lockboxOptInState);
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

        public static SystemStateEvent parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (SystemStateEvent) MessageNano.mergeFrom(new SystemStateEvent(), data);
        }

        public static SystemStateEvent parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new SystemStateEvent().mergeFrom(input);
        }
    }
}
