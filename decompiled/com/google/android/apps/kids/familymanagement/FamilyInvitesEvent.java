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
import com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection;
import java.io.IOException;

public final class FamilyInvitesEvent extends ExtendableMessageNano<FamilyInvitesEvent> {
    private static volatile FamilyInvitesEvent[] _emptyArray;
    public int eventType;
    public int numEmailInviteesSelected;
    public int numEmailInvitesSuccess;
    public int numSmsInviteesSelected;
    public int numSmsInvitesSuccess;

    public interface InvitesEventType {
        public static final int INVITES_FINISHED = 2;
        public static final int INVITES_LAUNCHED = 1;
        public static final int MEMBERS_INVITED = 3;
    }

    public static FamilyInvitesEvent[] emptyArray() {
        if (_emptyArray == null) {
            synchronized (InternalNano.LAZY_INIT_LOCK) {
                if (_emptyArray == null) {
                    _emptyArray = new FamilyInvitesEvent[0];
                }
            }
        }
        return _emptyArray;
    }

    public FamilyInvitesEvent() {
        clear();
    }

    public FamilyInvitesEvent clear() {
        this.eventType = 1;
        this.numSmsInviteesSelected = 0;
        this.numEmailInviteesSelected = 0;
        this.numSmsInvitesSuccess = 0;
        this.numEmailInvitesSuccess = 0;
        this.unknownFieldData = null;
        this.cachedSize = -1;
        return this;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof FamilyInvitesEvent)) {
            return false;
        }
        FamilyInvitesEvent other = (FamilyInvitesEvent) o;
        if (this.eventType != other.eventType || this.numSmsInviteesSelected != other.numSmsInviteesSelected || this.numEmailInviteesSelected != other.numEmailInviteesSelected || this.numSmsInvitesSuccess != other.numSmsInvitesSuccess || this.numEmailInvitesSuccess != other.numEmailInvitesSuccess) {
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
        int hashCode = (((((((((((getClass().getName().hashCode() + 527) * 31) + this.eventType) * 31) + this.numSmsInviteesSelected) * 31) + this.numEmailInviteesSelected) * 31) + this.numSmsInvitesSuccess) * 31) + this.numEmailInvitesSuccess) * 31;
        int hashCode2 = (this.unknownFieldData == null || this.unknownFieldData.isEmpty()) ? 0 : this.unknownFieldData.hashCode();
        return hashCode + hashCode2;
    }

    public void writeTo(CodedOutputByteBufferNano output) throws IOException {
        if (this.eventType != 1) {
            output.writeInt32(1, this.eventType);
        }
        if (this.numSmsInviteesSelected != 0) {
            output.writeInt32(2, this.numSmsInviteesSelected);
        }
        if (this.numEmailInviteesSelected != 0) {
            output.writeInt32(3, this.numEmailInviteesSelected);
        }
        if (this.numSmsInvitesSuccess != 0) {
            output.writeInt32(4, this.numSmsInvitesSuccess);
        }
        if (this.numEmailInvitesSuccess != 0) {
            output.writeInt32(5, this.numEmailInvitesSuccess);
        }
        super.writeTo(output);
    }

    protected int computeSerializedSize() {
        int size = super.computeSerializedSize();
        if (this.eventType != 1) {
            size += CodedOutputByteBufferNano.computeInt32Size(1, this.eventType);
        }
        if (this.numSmsInviteesSelected != 0) {
            size += CodedOutputByteBufferNano.computeInt32Size(2, this.numSmsInviteesSelected);
        }
        if (this.numEmailInviteesSelected != 0) {
            size += CodedOutputByteBufferNano.computeInt32Size(3, this.numEmailInviteesSelected);
        }
        if (this.numSmsInvitesSuccess != 0) {
            size += CodedOutputByteBufferNano.computeInt32Size(4, this.numSmsInvitesSuccess);
        }
        if (this.numEmailInvitesSuccess != 0) {
            return size + CodedOutputByteBufferNano.computeInt32Size(5, this.numEmailInvitesSuccess);
        }
        return size;
    }

    public FamilyInvitesEvent mergeFrom(CodedInputByteBufferNano input) throws IOException {
        while (true) {
            int tag = input.readTag();
            switch (tag) {
                case Action.UNKNOWN /*0*/:
                    break;
                case Type.TAP_GET_LINK /*8*/:
                    int value = input.readInt32();
                    switch (value) {
                        case TimeSelection.Type.TEMPORARY /*1*/:
                        case TimeSelection.Type.INDEFINITELY /*2*/:
                        case TimeSelection.Type.CUSTOM /*3*/:
                            this.eventType = value;
                            break;
                        default:
                            continue;
                    }
                case LogSource.GMS_CORE_PEOPLE /*16*/:
                    this.numSmsInviteesSelected = input.readInt32();
                    continue;
                case LogSource.LB_C /*24*/:
                    this.numEmailInviteesSelected = input.readInt32();
                    continue;
                case LogSource.SOCIAL /*32*/:
                    this.numSmsInvitesSuccess = input.readInt32();
                    continue;
                case LogSource.COPRESENCE /*40*/:
                    this.numEmailInvitesSuccess = input.readInt32();
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

    public static FamilyInvitesEvent parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
        return (FamilyInvitesEvent) MessageNano.mergeFrom(new FamilyInvitesEvent(), data);
    }

    public static FamilyInvitesEvent parseFrom(CodedInputByteBufferNano input) throws IOException {
        return new FamilyInvitesEvent().mergeFrom(input);
    }
}
