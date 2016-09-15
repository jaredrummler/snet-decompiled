package com.google.android.gms.lockbox;

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

public interface OptInState {

    public static final class LockboxOptInState extends ExtendableMessageNano<LockboxOptInState> {
        private static volatile LockboxOptInState[] _emptyArray;
        public int index;
        public int numOptInAccounts;
        public boolean optInAnonymousDataCollection;

        public static LockboxOptInState[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new LockboxOptInState[0];
                    }
                }
            }
            return _emptyArray;
        }

        public LockboxOptInState() {
            clear();
        }

        public LockboxOptInState clear() {
            this.numOptInAccounts = 0;
            this.optInAnonymousDataCollection = false;
            this.index = 0;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof LockboxOptInState)) {
                return false;
            }
            LockboxOptInState other = (LockboxOptInState) o;
            if (this.numOptInAccounts != other.numOptInAccounts || this.optInAnonymousDataCollection != other.optInAnonymousDataCollection || this.index != other.index) {
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
            int hashCode = (((((((getClass().getName().hashCode() + 527) * 31) + this.numOptInAccounts) * 31) + (this.optInAnonymousDataCollection ? 1231 : 1237)) * 31) + this.index) * 31;
            int hashCode2 = (this.unknownFieldData == null || this.unknownFieldData.isEmpty()) ? 0 : this.unknownFieldData.hashCode();
            return hashCode + hashCode2;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.numOptInAccounts != 0) {
                output.writeInt32(1, this.numOptInAccounts);
            }
            if (this.optInAnonymousDataCollection) {
                output.writeBool(2, this.optInAnonymousDataCollection);
            }
            if (this.index != 0) {
                output.writeInt32(3, this.index);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.numOptInAccounts != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(1, this.numOptInAccounts);
            }
            if (this.optInAnonymousDataCollection) {
                size += CodedOutputByteBufferNano.computeBoolSize(2, this.optInAnonymousDataCollection);
            }
            if (this.index != 0) {
                return size + CodedOutputByteBufferNano.computeInt32Size(3, this.index);
            }
            return size;
        }

        public LockboxOptInState mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_GET_LINK /*8*/:
                        this.numOptInAccounts = input.readInt32();
                        continue;
                    case LogSource.GMS_CORE_PEOPLE /*16*/:
                        this.optInAnonymousDataCollection = input.readBool();
                        continue;
                    case LogSource.LB_C /*24*/:
                        this.index = input.readInt32();
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

        public static LockboxOptInState parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (LockboxOptInState) MessageNano.mergeFrom(new LockboxOptInState(), data);
        }

        public static LockboxOptInState parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new LockboxOptInState().mergeFrom(input);
        }
    }
}
