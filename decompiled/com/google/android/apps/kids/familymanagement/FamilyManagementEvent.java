package com.google.android.apps.kids.familymanagement;

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

public final class FamilyManagementEvent extends ExtendableMessageNano<FamilyManagementEvent> {
    private static volatile FamilyManagementEvent[] _emptyArray;
    public int eventType;
    public FamilyInvitesEvent invitesEvent;

    public interface FamilyManagementEventType {
        public static final int FAMILY_DISSOLVE_CLICKED = 9;
        public static final int FAMILY_DISSOLVE_CONFIRMATION_ACCEPTED = 11;
        public static final int FAMILY_DISSOLVE_CONFIRMATION_CANCELLED = 12;
        public static final int FAMILY_DISSOLVE_CONFIRMATION_SHOWN = 10;
        public static final int INVITATION_DELETED = 13;
        public static final int MANAGEMENT_EXITED = 2;
        public static final int MANAGEMENT_LAUNCHED = 1;
        public static final int MEMBER_CLICKED = 3;
        public static final int MEMBER_REMOVE_CLICKED = 5;
        public static final int MEMBER_REMOVE_CONFIRMATION_ACCEPTED = 8;
        public static final int MEMBER_REMOVE_CONFIRMATION_CANCELLED = 7;
        public static final int MEMBER_REMOVE_CONFIRMATION_SHOWN = 6;
        public static final int MEMBER_VIEW_SHOWN = 4;
        public static final int UNKNOWN = 0;
    }

    public static FamilyManagementEvent[] emptyArray() {
        if (_emptyArray == null) {
            synchronized (InternalNano.LAZY_INIT_LOCK) {
                if (_emptyArray == null) {
                    _emptyArray = new FamilyManagementEvent[0];
                }
            }
        }
        return _emptyArray;
    }

    public FamilyManagementEvent() {
        clear();
    }

    public FamilyManagementEvent clear() {
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
        if (!(o instanceof FamilyManagementEvent)) {
            return false;
        }
        FamilyManagementEvent other = (FamilyManagementEvent) o;
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
        if (this.eventType != 0) {
            output.writeInt32(1, this.eventType);
        }
        if (this.invitesEvent != null) {
            output.writeMessage(2, this.invitesEvent);
        }
        super.writeTo(output);
    }

    protected int computeSerializedSize() {
        int size = super.computeSerializedSize();
        if (this.eventType != 0) {
            size += CodedOutputByteBufferNano.computeInt32Size(1, this.eventType);
        }
        if (this.invitesEvent != null) {
            return size + CodedOutputByteBufferNano.computeMessageSize(2, this.invitesEvent);
        }
        return size;
    }

    public FamilyManagementEvent mergeFrom(CodedInputByteBufferNano input) throws IOException {
        while (true) {
            int tag = input.readTag();
            switch (tag) {
                case Action.UNKNOWN /*0*/:
                    break;
                case Type.TAP_GET_LINK /*8*/:
                    int value = input.readInt32();
                    switch (value) {
                        case Action.UNKNOWN /*0*/:
                        case TimeSelection.Type.TEMPORARY /*1*/:
                        case TimeSelection.Type.INDEFINITELY /*2*/:
                        case TimeSelection.Type.CUSTOM /*3*/:
                        case OvenFreshResult.NO_ACCOUNTS /*4*/:
                        case Type.ADD_NEW_SHARES /*5*/:
                        case Type.REMOVE_SHARE /*6*/:
                        case Type.RESET_TIME /*7*/:
                        case Type.TAP_GET_LINK /*8*/:
                        case Type.CREATE_LINK /*9*/:
                        case Type.TAP_ABOUT /*10*/:
                        case Type.TAP_LEARN_MORE /*11*/:
                        case Type.SWITCH_ACCOUNT /*12*/:
                        case Type.DISPLAY_ERROR /*13*/:
                            this.eventType = value;
                            break;
                        default:
                            continue;
                    }
                case LogSource.GOOGLE_ANALYTICS /*18*/:
                    if (this.invitesEvent == null) {
                        this.invitesEvent = new FamilyInvitesEvent();
                    }
                    input.readMessage(this.invitesEvent);
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

    public static FamilyManagementEvent parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
        return (FamilyManagementEvent) MessageNano.mergeFrom(new FamilyManagementEvent(), data);
    }

    public static FamilyManagementEvent parseFrom(CodedInputByteBufferNano input) throws IOException {
        return new FamilyManagementEvent().mergeFrom(input);
    }
}
