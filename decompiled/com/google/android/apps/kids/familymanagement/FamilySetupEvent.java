package com.google.android.apps.kids.familymanagement;

import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.ExtendableMessageNano;
import com.google.protobuf.nano.InternalNano;
import com.google.protobuf.nano.InvalidProtocolBufferNanoException;
import com.google.protobuf.nano.MessageNano;
import com.google.wireless.android.play.playlog.proto.ClientAnalytics.LogRequest.LogSource;
import com.google.wireless.android.play.playlog.proto.UserLocation.LaunchSettingsEvent.Action;
import com.google.wireless.android.play.playlog.proto.UserLocation.LocationSharingEvent;
import com.google.wireless.android.play.playlog.proto.UserLocation.OvenFreshEvent.OvenFreshResult;
import com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection.Type;
import java.io.IOException;

public final class FamilySetupEvent extends ExtendableMessageNano<FamilySetupEvent> {
    private static volatile FamilySetupEvent[] _emptyArray;
    public int eventType;
    public FamilyInvitesEvent invitesEvent;

    public interface FamilySetupEventType {
        public static final int ACCOUNT_CONFIRMED = 3;
        public static final int ACCOUNT_SHOWN = 2;
        public static final int FAMILY_CREATED = 4;
        public static final int FAMILY_SETUP_FINISHED = 6;
        public static final int FAMILY_SETUP_STARTED = 1;
        public static final int INVITES_FIXIT_LAUNCHED = 8;
        public static final int UNKNOWN = 0;
        public static final int WALLET_FIXIT_LAUNCHED = 7;
        public static final int WALLET_SETUP_LAUNCHED = 5;
    }

    public static FamilySetupEvent[] emptyArray() {
        if (_emptyArray == null) {
            synchronized (InternalNano.LAZY_INIT_LOCK) {
                if (_emptyArray == null) {
                    _emptyArray = new FamilySetupEvent[0];
                }
            }
        }
        return _emptyArray;
    }

    public FamilySetupEvent() {
        clear();
    }

    public FamilySetupEvent clear() {
        this.eventType = 0;
        this.invitesEvent = null;
        this.unknownFieldData = null;
        this.cachedSize = -1;
        return this;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof FamilySetupEvent)) {
            return false;
        }
        FamilySetupEvent other = (FamilySetupEvent) o;
        if (this.eventType != other.eventType) {
            return false;
        }
        if (this.invitesEvent == null) {
            if (other.invitesEvent != null) {
                return false;
            }
        } else if (!this.invitesEvent.equals(other.invitesEvent)) {
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
        int hashCode = (((((getClass().getName().hashCode() + 527) * 31) + this.eventType) * 31) + (this.invitesEvent == null ? 0 : this.invitesEvent.hashCode())) * 31;
        if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
            i = this.unknownFieldData.hashCode();
        }
        return hashCode + i;
    }

    public void writeTo(CodedOutputByteBufferNano output) throws IOException {
        if (this.invitesEvent != null) {
            output.writeMessage(2, this.invitesEvent);
        }
        if (this.eventType != 0) {
            output.writeInt32(3, this.eventType);
        }
        super.writeTo(output);
    }

    protected int computeSerializedSize() {
        int size = super.computeSerializedSize();
        if (this.invitesEvent != null) {
            size += CodedOutputByteBufferNano.computeMessageSize(2, this.invitesEvent);
        }
        if (this.eventType != 0) {
            return size + CodedOutputByteBufferNano.computeInt32Size(3, this.eventType);
        }
        return size;
    }

    public FamilySetupEvent mergeFrom(CodedInputByteBufferNano input) throws IOException {
        while (true) {
            int tag = input.readTag();
            switch (tag) {
                case Action.UNKNOWN /*0*/:
                    break;
                case LogSource.GOOGLE_ANALYTICS /*18*/:
                    if (this.invitesEvent == null) {
                        this.invitesEvent = new FamilyInvitesEvent();
                    }
                    input.readMessage(this.invitesEvent);
                    continue;
                case LogSource.LB_C /*24*/:
                    int value = input.readInt32();
                    switch (value) {
                        case Action.UNKNOWN /*0*/:
                        case Type.TEMPORARY /*1*/:
                        case Type.INDEFINITELY /*2*/:
                        case Type.CUSTOM /*3*/:
                        case OvenFreshResult.NO_ACCOUNTS /*4*/:
                        case LocationSharingEvent.Type.ADD_NEW_SHARES /*5*/:
                        case LocationSharingEvent.Type.REMOVE_SHARE /*6*/:
                        case LocationSharingEvent.Type.RESET_TIME /*7*/:
                        case LocationSharingEvent.Type.TAP_GET_LINK /*8*/:
                            this.eventType = value;
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

    public static FamilySetupEvent parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
        return (FamilySetupEvent) MessageNano.mergeFrom(new FamilySetupEvent(), data);
    }

    public static FamilySetupEvent parseFrom(CodedInputByteBufferNano input) throws IOException {
        return new FamilySetupEvent().mergeFrom(input);
    }
}
