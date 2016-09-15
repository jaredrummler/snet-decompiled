package com.google.wireless.android.play.playlog.proto;

import com.google.android.gms.lint.BuildConfig;
import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.ExtendableMessageNano;
import com.google.protobuf.nano.InternalNano;
import com.google.protobuf.nano.InvalidProtocolBufferNanoException;
import com.google.protobuf.nano.MessageNano;
import com.google.wireless.android.play.playlog.proto.ClientAnalytics.LogRequest.LogSource;
import java.io.IOException;

public interface UserLocation {

    public static final class AddNewSharesEvent extends ExtendableMessageNano<AddNewSharesEvent> {
        private static volatile AddNewSharesEvent[] _emptyArray;
        public int numNewShares;
        public TimeSelection time;
        public int type;

        public interface Type {
            public static final int BEST = 1;
            public static final int CITY = 2;
            public static final int UNKNOWN = 0;
        }

        public static AddNewSharesEvent[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new AddNewSharesEvent[0];
                    }
                }
            }
            return _emptyArray;
        }

        public AddNewSharesEvent() {
            clear();
        }

        public AddNewSharesEvent clear() {
            this.type = 0;
            this.time = null;
            this.numNewShares = 0;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof AddNewSharesEvent)) {
                return false;
            }
            AddNewSharesEvent other = (AddNewSharesEvent) o;
            if (this.type != other.type) {
                return false;
            }
            if (this.time == null) {
                if (other.time != null) {
                    return false;
                }
            } else if (!this.time.equals(other.time)) {
                return false;
            }
            if (this.numNewShares != other.numNewShares) {
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
            int hashCode = (((((((getClass().getName().hashCode() + 527) * 31) + this.type) * 31) + (this.time == null ? 0 : this.time.hashCode())) * 31) + this.numNewShares) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.type != 0) {
                output.writeInt32(1, this.type);
            }
            if (this.time != null) {
                output.writeMessage(2, this.time);
            }
            if (this.numNewShares != 0) {
                output.writeInt32(3, this.numNewShares);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.type != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(1, this.type);
            }
            if (this.time != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(2, this.time);
            }
            if (this.numNewShares != 0) {
                return size + CodedOutputByteBufferNano.computeInt32Size(3, this.numNewShares);
            }
            return size;
        }

        public AddNewSharesEvent mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_GET_LINK /*8*/:
                        int value = input.readInt32();
                        switch (value) {
                            case Action.UNKNOWN /*0*/:
                            case Type.TEMPORARY /*1*/:
                            case Type.INDEFINITELY /*2*/:
                                this.type = value;
                                break;
                            default:
                                continue;
                        }
                    case LogSource.GOOGLE_ANALYTICS /*18*/:
                        if (this.time == null) {
                            this.time = new TimeSelection();
                        }
                        input.readMessage(this.time);
                        continue;
                    case LogSource.LB_C /*24*/:
                        this.numNewShares = input.readInt32();
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

        public static AddNewSharesEvent parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (AddNewSharesEvent) MessageNano.mergeFrom(new AddNewSharesEvent(), data);
        }

        public static AddNewSharesEvent parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new AddNewSharesEvent().mergeFrom(input);
        }
    }

    public static final class ChangeSharingStateEvent extends ExtendableMessageNano<ChangeSharingStateEvent> {
        private static volatile ChangeSharingStateEvent[] _emptyArray;
        public int type;

        public interface Type {
            public static final int OFF = 2;
            public static final int ON = 1;
            public static final int UNKNOWN = 0;
        }

        public static ChangeSharingStateEvent[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new ChangeSharingStateEvent[0];
                    }
                }
            }
            return _emptyArray;
        }

        public ChangeSharingStateEvent() {
            clear();
        }

        public ChangeSharingStateEvent clear() {
            this.type = 0;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof ChangeSharingStateEvent)) {
                return false;
            }
            ChangeSharingStateEvent other = (ChangeSharingStateEvent) o;
            if (this.type != other.type) {
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
            int hashCode = (((getClass().getName().hashCode() + 527) * 31) + this.type) * 31;
            int hashCode2 = (this.unknownFieldData == null || this.unknownFieldData.isEmpty()) ? 0 : this.unknownFieldData.hashCode();
            return hashCode + hashCode2;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.type != 0) {
                output.writeInt32(1, this.type);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.type != 0) {
                return size + CodedOutputByteBufferNano.computeInt32Size(1, this.type);
            }
            return size;
        }

        public ChangeSharingStateEvent mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_GET_LINK /*8*/:
                        int value = input.readInt32();
                        switch (value) {
                            case Action.UNKNOWN /*0*/:
                            case Type.TEMPORARY /*1*/:
                            case Type.INDEFINITELY /*2*/:
                                this.type = value;
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

        public static ChangeSharingStateEvent parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (ChangeSharingStateEvent) MessageNano.mergeFrom(new ChangeSharingStateEvent(), data);
        }

        public static ChangeSharingStateEvent parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new ChangeSharingStateEvent().mergeFrom(input);
        }
    }

    public static final class CreateLinkEvent extends ExtendableMessageNano<CreateLinkEvent> {
        private static volatile CreateLinkEvent[] _emptyArray;
        public TimeSelection time;

        public static CreateLinkEvent[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new CreateLinkEvent[0];
                    }
                }
            }
            return _emptyArray;
        }

        public CreateLinkEvent() {
            clear();
        }

        public CreateLinkEvent clear() {
            this.time = null;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof CreateLinkEvent)) {
                return false;
            }
            CreateLinkEvent other = (CreateLinkEvent) o;
            if (this.time == null) {
                if (other.time != null) {
                    return false;
                }
            } else if (!this.time.equals(other.time)) {
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
            int hashCode = (((getClass().getName().hashCode() + 527) * 31) + (this.time == null ? 0 : this.time.hashCode())) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.time != null) {
                output.writeMessage(1, this.time);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.time != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(1, this.time);
            }
            return size;
        }

        public CreateLinkEvent mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        if (this.time == null) {
                            this.time = new TimeSelection();
                        }
                        input.readMessage(this.time);
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

        public static CreateLinkEvent parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (CreateLinkEvent) MessageNano.mergeFrom(new CreateLinkEvent(), data);
        }

        public static CreateLinkEvent parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new CreateLinkEvent().mergeFrom(input);
        }
    }

    public static final class DisplayErrorEvent extends ExtendableMessageNano<DisplayErrorEvent> {
        private static volatile DisplayErrorEvent[] _emptyArray;
        public int type;

        public interface Type {
            public static final int INVALID_ACCOUNT = 2;
            public static final int INVALID_RESPONSE = 3;
            public static final int NO_NETWORK = 1;
            public static final int OTHER = 4;
            public static final int UNKNOWN = 0;
        }

        public static DisplayErrorEvent[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new DisplayErrorEvent[0];
                    }
                }
            }
            return _emptyArray;
        }

        public DisplayErrorEvent() {
            clear();
        }

        public DisplayErrorEvent clear() {
            this.type = 0;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof DisplayErrorEvent)) {
                return false;
            }
            DisplayErrorEvent other = (DisplayErrorEvent) o;
            if (this.type != other.type) {
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
            int hashCode = (((getClass().getName().hashCode() + 527) * 31) + this.type) * 31;
            int hashCode2 = (this.unknownFieldData == null || this.unknownFieldData.isEmpty()) ? 0 : this.unknownFieldData.hashCode();
            return hashCode + hashCode2;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.type != 0) {
                output.writeInt32(1, this.type);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.type != 0) {
                return size + CodedOutputByteBufferNano.computeInt32Size(1, this.type);
            }
            return size;
        }

        public DisplayErrorEvent mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_GET_LINK /*8*/:
                        int value = input.readInt32();
                        switch (value) {
                            case Action.UNKNOWN /*0*/:
                            case Type.TEMPORARY /*1*/:
                            case Type.INDEFINITELY /*2*/:
                            case Type.CUSTOM /*3*/:
                            case OvenFreshResult.NO_ACCOUNTS /*4*/:
                                this.type = value;
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

        public static DisplayErrorEvent parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (DisplayErrorEvent) MessageNano.mergeFrom(new DisplayErrorEvent(), data);
        }

        public static DisplayErrorEvent parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new DisplayErrorEvent().mergeFrom(input);
        }
    }

    public static final class LaunchSettingsEvent extends ExtendableMessageNano<LaunchSettingsEvent> {
        private static volatile LaunchSettingsEvent[] _emptyArray;
        public int action;
        public String appId;

        public interface Action {
            public static final int CREATE_SHARE = 3;
            public static final int UNKNOWN = 0;
            public static final int VIEW_SETTINGS = 1;
            public static final int VIEW_SETTINGS_FOR_DELEGATE = 2;
        }

        public static LaunchSettingsEvent[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new LaunchSettingsEvent[0];
                    }
                }
            }
            return _emptyArray;
        }

        public LaunchSettingsEvent() {
            clear();
        }

        public LaunchSettingsEvent clear() {
            this.action = 0;
            this.appId = BuildConfig.VERSION_NAME;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof LaunchSettingsEvent)) {
                return false;
            }
            LaunchSettingsEvent other = (LaunchSettingsEvent) o;
            if (this.action != other.action) {
                return false;
            }
            if (this.appId == null) {
                if (other.appId != null) {
                    return false;
                }
            } else if (!this.appId.equals(other.appId)) {
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
            int hashCode = (((((getClass().getName().hashCode() + 527) * 31) + this.action) * 31) + (this.appId == null ? 0 : this.appId.hashCode())) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.action != 0) {
                output.writeInt32(1, this.action);
            }
            if (!this.appId.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(2, this.appId);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.action != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(1, this.action);
            }
            if (this.appId.equals(BuildConfig.VERSION_NAME)) {
                return size;
            }
            return size + CodedOutputByteBufferNano.computeStringSize(2, this.appId);
        }

        public LaunchSettingsEvent mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_GET_LINK /*8*/:
                        int value = input.readInt32();
                        switch (value) {
                            case Action.UNKNOWN /*0*/:
                            case Type.TEMPORARY /*1*/:
                            case Type.INDEFINITELY /*2*/:
                            case Type.CUSTOM /*3*/:
                                this.action = value;
                                break;
                            default:
                                continue;
                        }
                    case LogSource.GOOGLE_ANALYTICS /*18*/:
                        this.appId = input.readString();
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

        public static LaunchSettingsEvent parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (LaunchSettingsEvent) MessageNano.mergeFrom(new LaunchSettingsEvent(), data);
        }

        public static LaunchSettingsEvent parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new LaunchSettingsEvent().mergeFrom(input);
        }
    }

    public static final class LocationSharingEvent extends ExtendableMessageNano<LocationSharingEvent> {
        private static volatile LocationSharingEvent[] _emptyArray;
        public AddNewSharesEvent addNewSharesEvent;
        public ResetTimeEvent changeAddShareTimeEvent;
        public ChangeSharingStateEvent changeSharingStateEvent;
        public CreateLinkEvent createLinkEvent;
        public DisplayErrorEvent displayErrorEvent;
        public LaunchSettingsEvent launchSettingsEvent;
        public OvenFreshEvent ovenFreshEvent;
        public RemoveShareEvent removeShareEvent;
        public TapAddPeopleEvent tapAddPeopleEvent;
        public TapInfoEvent tapInfoEvent;
        public int type;

        public interface Type {
            public static final int ADD_NEW_SHARES = 5;
            public static final int CHANGE_SHARING_STATE = 2;
            public static final int CREATE_LINK = 9;
            public static final int DISPLAY_ERROR = 13;
            public static final int LAUNCH_SETTINGS = 14;
            public static final int LOAD_SETTINGS = 1;
            public static final int OVEN_FRESH = 15;
            public static final int REMOVE_SHARE = 6;
            public static final int RESET_TIME = 7;
            public static final int SWITCH_ACCOUNT = 12;
            public static final int TAP_ABOUT = 10;
            public static final int TAP_ADD_PEOPLE = 4;
            public static final int TAP_GET_LINK = 8;
            public static final int TAP_INFO = 3;
            public static final int TAP_LEARN_MORE = 11;
            public static final int UNKNOWN = 0;
        }

        public static LocationSharingEvent[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new LocationSharingEvent[0];
                    }
                }
            }
            return _emptyArray;
        }

        public LocationSharingEvent() {
            clear();
        }

        public LocationSharingEvent clear() {
            this.type = 0;
            this.changeSharingStateEvent = null;
            this.tapInfoEvent = null;
            this.tapAddPeopleEvent = null;
            this.addNewSharesEvent = null;
            this.removeShareEvent = null;
            this.changeAddShareTimeEvent = null;
            this.createLinkEvent = null;
            this.displayErrorEvent = null;
            this.launchSettingsEvent = null;
            this.ovenFreshEvent = null;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof LocationSharingEvent)) {
                return false;
            }
            LocationSharingEvent other = (LocationSharingEvent) o;
            if (this.type != other.type) {
                return false;
            }
            if (this.changeSharingStateEvent == null) {
                if (other.changeSharingStateEvent != null) {
                    return false;
                }
            } else if (!this.changeSharingStateEvent.equals(other.changeSharingStateEvent)) {
                return false;
            }
            if (this.tapInfoEvent == null) {
                if (other.tapInfoEvent != null) {
                    return false;
                }
            } else if (!this.tapInfoEvent.equals(other.tapInfoEvent)) {
                return false;
            }
            if (this.tapAddPeopleEvent == null) {
                if (other.tapAddPeopleEvent != null) {
                    return false;
                }
            } else if (!this.tapAddPeopleEvent.equals(other.tapAddPeopleEvent)) {
                return false;
            }
            if (this.addNewSharesEvent == null) {
                if (other.addNewSharesEvent != null) {
                    return false;
                }
            } else if (!this.addNewSharesEvent.equals(other.addNewSharesEvent)) {
                return false;
            }
            if (this.removeShareEvent == null) {
                if (other.removeShareEvent != null) {
                    return false;
                }
            } else if (!this.removeShareEvent.equals(other.removeShareEvent)) {
                return false;
            }
            if (this.changeAddShareTimeEvent == null) {
                if (other.changeAddShareTimeEvent != null) {
                    return false;
                }
            } else if (!this.changeAddShareTimeEvent.equals(other.changeAddShareTimeEvent)) {
                return false;
            }
            if (this.createLinkEvent == null) {
                if (other.createLinkEvent != null) {
                    return false;
                }
            } else if (!this.createLinkEvent.equals(other.createLinkEvent)) {
                return false;
            }
            if (this.displayErrorEvent == null) {
                if (other.displayErrorEvent != null) {
                    return false;
                }
            } else if (!this.displayErrorEvent.equals(other.displayErrorEvent)) {
                return false;
            }
            if (this.launchSettingsEvent == null) {
                if (other.launchSettingsEvent != null) {
                    return false;
                }
            } else if (!this.launchSettingsEvent.equals(other.launchSettingsEvent)) {
                return false;
            }
            if (this.ovenFreshEvent == null) {
                if (other.ovenFreshEvent != null) {
                    return false;
                }
            } else if (!this.ovenFreshEvent.equals(other.ovenFreshEvent)) {
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
            int hashCode = (((((((((((((((((((((((getClass().getName().hashCode() + 527) * 31) + this.type) * 31) + (this.changeSharingStateEvent == null ? 0 : this.changeSharingStateEvent.hashCode())) * 31) + (this.tapInfoEvent == null ? 0 : this.tapInfoEvent.hashCode())) * 31) + (this.tapAddPeopleEvent == null ? 0 : this.tapAddPeopleEvent.hashCode())) * 31) + (this.addNewSharesEvent == null ? 0 : this.addNewSharesEvent.hashCode())) * 31) + (this.removeShareEvent == null ? 0 : this.removeShareEvent.hashCode())) * 31) + (this.changeAddShareTimeEvent == null ? 0 : this.changeAddShareTimeEvent.hashCode())) * 31) + (this.createLinkEvent == null ? 0 : this.createLinkEvent.hashCode())) * 31) + (this.displayErrorEvent == null ? 0 : this.displayErrorEvent.hashCode())) * 31) + (this.launchSettingsEvent == null ? 0 : this.launchSettingsEvent.hashCode())) * 31) + (this.ovenFreshEvent == null ? 0 : this.ovenFreshEvent.hashCode())) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.type != 0) {
                output.writeInt32(1, this.type);
            }
            if (this.changeSharingStateEvent != null) {
                output.writeMessage(2, this.changeSharingStateEvent);
            }
            if (this.tapInfoEvent != null) {
                output.writeMessage(3, this.tapInfoEvent);
            }
            if (this.tapAddPeopleEvent != null) {
                output.writeMessage(4, this.tapAddPeopleEvent);
            }
            if (this.addNewSharesEvent != null) {
                output.writeMessage(5, this.addNewSharesEvent);
            }
            if (this.removeShareEvent != null) {
                output.writeMessage(6, this.removeShareEvent);
            }
            if (this.changeAddShareTimeEvent != null) {
                output.writeMessage(7, this.changeAddShareTimeEvent);
            }
            if (this.createLinkEvent != null) {
                output.writeMessage(8, this.createLinkEvent);
            }
            if (this.displayErrorEvent != null) {
                output.writeMessage(9, this.displayErrorEvent);
            }
            if (this.launchSettingsEvent != null) {
                output.writeMessage(10, this.launchSettingsEvent);
            }
            if (this.ovenFreshEvent != null) {
                output.writeMessage(11, this.ovenFreshEvent);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.type != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(1, this.type);
            }
            if (this.changeSharingStateEvent != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(2, this.changeSharingStateEvent);
            }
            if (this.tapInfoEvent != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(3, this.tapInfoEvent);
            }
            if (this.tapAddPeopleEvent != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(4, this.tapAddPeopleEvent);
            }
            if (this.addNewSharesEvent != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(5, this.addNewSharesEvent);
            }
            if (this.removeShareEvent != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(6, this.removeShareEvent);
            }
            if (this.changeAddShareTimeEvent != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(7, this.changeAddShareTimeEvent);
            }
            if (this.createLinkEvent != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(8, this.createLinkEvent);
            }
            if (this.displayErrorEvent != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(9, this.displayErrorEvent);
            }
            if (this.launchSettingsEvent != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(10, this.launchSettingsEvent);
            }
            if (this.ovenFreshEvent != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(11, this.ovenFreshEvent);
            }
            return size;
        }

        public LocationSharingEvent mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_GET_LINK /*8*/:
                        int value = input.readInt32();
                        switch (value) {
                            case Action.UNKNOWN /*0*/:
                            case Type.TEMPORARY /*1*/:
                            case Type.INDEFINITELY /*2*/:
                            case Type.CUSTOM /*3*/:
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
                            case Type.LAUNCH_SETTINGS /*14*/:
                            case Type.OVEN_FRESH /*15*/:
                                this.type = value;
                                break;
                            default:
                                continue;
                        }
                    case LogSource.GOOGLE_ANALYTICS /*18*/:
                        if (this.changeSharingStateEvent == null) {
                            this.changeSharingStateEvent = new ChangeSharingStateEvent();
                        }
                        input.readMessage(this.changeSharingStateEvent);
                        continue;
                    case LogSource.ANDROID_CAMERA /*26*/:
                        if (this.tapInfoEvent == null) {
                            this.tapInfoEvent = new TapInfoEvent();
                        }
                        input.readMessage(this.tapInfoEvent);
                        continue;
                    case LogSource.NOVA /*34*/:
                        if (this.tapAddPeopleEvent == null) {
                            this.tapAddPeopleEvent = new TapAddPeopleEvent();
                        }
                        input.readMessage(this.tapAddPeopleEvent);
                        continue;
                    case LogSource.PHOTOS /*42*/:
                        if (this.addNewSharesEvent == null) {
                            this.addNewSharesEvent = new AddNewSharesEvent();
                        }
                        input.readMessage(this.addNewSharesEvent);
                        continue;
                    case LogSource.HANGOUT_LOG_REQUEST /*50*/:
                        if (this.removeShareEvent == null) {
                            this.removeShareEvent = new RemoveShareEvent();
                        }
                        input.readMessage(this.removeShareEvent);
                        continue;
                    case LogSource.SLIDES /*58*/:
                        if (this.changeAddShareTimeEvent == null) {
                            this.changeAddShareTimeEvent = new ResetTimeEvent();
                        }
                        input.readMessage(this.changeAddShareTimeEvent);
                        continue;
                    case LogSource.WIFI_ASSISTANT /*66*/:
                        if (this.createLinkEvent == null) {
                            this.createLinkEvent = new CreateLinkEvent();
                        }
                        input.readMessage(this.createLinkEvent);
                        continue;
                    case LogSource.MOVIES /*74*/:
                        if (this.displayErrorEvent == null) {
                            this.displayErrorEvent = new DisplayErrorEvent();
                        }
                        input.readMessage(this.displayErrorEvent);
                        continue;
                    case LogSource.GOOGLE_EXPRESS /*82*/:
                        if (this.launchSettingsEvent == null) {
                            this.launchSettingsEvent = new LaunchSettingsEvent();
                        }
                        input.readMessage(this.launchSettingsEvent);
                        continue;
                    case LogSource.TRON_COUNTERS /*90*/:
                        if (this.ovenFreshEvent == null) {
                            this.ovenFreshEvent = new OvenFreshEvent();
                        }
                        input.readMessage(this.ovenFreshEvent);
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

        public static LocationSharingEvent parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (LocationSharingEvent) MessageNano.mergeFrom(new LocationSharingEvent(), data);
        }

        public static LocationSharingEvent parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new LocationSharingEvent().mergeFrom(input);
        }
    }

    public static final class OvenFreshEvent extends ExtendableMessageNano<OvenFreshEvent> {
        private static volatile OvenFreshEvent[] _emptyArray;
        public int ovenFreshResult;

        public interface OvenFreshResult {
            public static final int NO_ACCOUNTS = 4;
            public static final int REPORTING_NOT_ALLOWED = 2;
            public static final int REPORTING_OFF = 3;
            public static final int SUCCESS = 1;
            public static final int UNKNOWN = 0;
        }

        public static OvenFreshEvent[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new OvenFreshEvent[0];
                    }
                }
            }
            return _emptyArray;
        }

        public OvenFreshEvent() {
            clear();
        }

        public OvenFreshEvent clear() {
            this.ovenFreshResult = 0;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof OvenFreshEvent)) {
                return false;
            }
            OvenFreshEvent other = (OvenFreshEvent) o;
            if (this.ovenFreshResult != other.ovenFreshResult) {
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
            int hashCode = (((getClass().getName().hashCode() + 527) * 31) + this.ovenFreshResult) * 31;
            int hashCode2 = (this.unknownFieldData == null || this.unknownFieldData.isEmpty()) ? 0 : this.unknownFieldData.hashCode();
            return hashCode + hashCode2;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.ovenFreshResult != 0) {
                output.writeInt32(1, this.ovenFreshResult);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.ovenFreshResult != 0) {
                return size + CodedOutputByteBufferNano.computeInt32Size(1, this.ovenFreshResult);
            }
            return size;
        }

        public OvenFreshEvent mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_GET_LINK /*8*/:
                        int value = input.readInt32();
                        switch (value) {
                            case Action.UNKNOWN /*0*/:
                            case Type.TEMPORARY /*1*/:
                            case Type.INDEFINITELY /*2*/:
                            case Type.CUSTOM /*3*/:
                            case OvenFreshResult.NO_ACCOUNTS /*4*/:
                                this.ovenFreshResult = value;
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

        public static OvenFreshEvent parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (OvenFreshEvent) MessageNano.mergeFrom(new OvenFreshEvent(), data);
        }

        public static OvenFreshEvent parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new OvenFreshEvent().mergeFrom(input);
        }
    }

    public static final class RemoveShareEvent extends ExtendableMessageNano<RemoveShareEvent> {
        private static volatile RemoveShareEvent[] _emptyArray;
        public int shareIndex;
        public int type;

        public interface Type {
            public static final int BEST = 1;
            public static final int CITY = 2;
            public static final int UNKNOWN = 0;
        }

        public static RemoveShareEvent[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new RemoveShareEvent[0];
                    }
                }
            }
            return _emptyArray;
        }

        public RemoveShareEvent() {
            clear();
        }

        public RemoveShareEvent clear() {
            this.type = 0;
            this.shareIndex = 0;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof RemoveShareEvent)) {
                return false;
            }
            RemoveShareEvent other = (RemoveShareEvent) o;
            if (this.type != other.type || this.shareIndex != other.shareIndex) {
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
            int hashCode = (((((getClass().getName().hashCode() + 527) * 31) + this.type) * 31) + this.shareIndex) * 31;
            int hashCode2 = (this.unknownFieldData == null || this.unknownFieldData.isEmpty()) ? 0 : this.unknownFieldData.hashCode();
            return hashCode + hashCode2;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.type != 0) {
                output.writeInt32(1, this.type);
            }
            if (this.shareIndex != 0) {
                output.writeInt32(2, this.shareIndex);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.type != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(1, this.type);
            }
            if (this.shareIndex != 0) {
                return size + CodedOutputByteBufferNano.computeInt32Size(2, this.shareIndex);
            }
            return size;
        }

        public RemoveShareEvent mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_GET_LINK /*8*/:
                        int value = input.readInt32();
                        switch (value) {
                            case Action.UNKNOWN /*0*/:
                            case Type.TEMPORARY /*1*/:
                            case Type.INDEFINITELY /*2*/:
                                this.type = value;
                                break;
                            default:
                                continue;
                        }
                    case LogSource.GMS_CORE_PEOPLE /*16*/:
                        this.shareIndex = input.readInt32();
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

        public static RemoveShareEvent parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (RemoveShareEvent) MessageNano.mergeFrom(new RemoveShareEvent(), data);
        }

        public static RemoveShareEvent parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new RemoveShareEvent().mergeFrom(input);
        }
    }

    public static final class ResetTimeEvent extends ExtendableMessageNano<ResetTimeEvent> {
        private static volatile ResetTimeEvent[] _emptyArray;
        public int shareIndex;
        public TimeSelection time;

        public static ResetTimeEvent[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new ResetTimeEvent[0];
                    }
                }
            }
            return _emptyArray;
        }

        public ResetTimeEvent() {
            clear();
        }

        public ResetTimeEvent clear() {
            this.time = null;
            this.shareIndex = 0;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof ResetTimeEvent)) {
                return false;
            }
            ResetTimeEvent other = (ResetTimeEvent) o;
            if (this.time == null) {
                if (other.time != null) {
                    return false;
                }
            } else if (!this.time.equals(other.time)) {
                return false;
            }
            if (this.shareIndex != other.shareIndex) {
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
            int hashCode = (((((getClass().getName().hashCode() + 527) * 31) + (this.time == null ? 0 : this.time.hashCode())) * 31) + this.shareIndex) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.time != null) {
                output.writeMessage(1, this.time);
            }
            if (this.shareIndex != 0) {
                output.writeInt32(2, this.shareIndex);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.time != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(1, this.time);
            }
            if (this.shareIndex != 0) {
                return size + CodedOutputByteBufferNano.computeInt32Size(2, this.shareIndex);
            }
            return size;
        }

        public ResetTimeEvent mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        if (this.time == null) {
                            this.time = new TimeSelection();
                        }
                        input.readMessage(this.time);
                        continue;
                    case LogSource.GMS_CORE_PEOPLE /*16*/:
                        this.shareIndex = input.readInt32();
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

        public static ResetTimeEvent parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (ResetTimeEvent) MessageNano.mergeFrom(new ResetTimeEvent(), data);
        }

        public static ResetTimeEvent parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new ResetTimeEvent().mergeFrom(input);
        }
    }

    public static final class TapAddPeopleEvent extends ExtendableMessageNano<TapAddPeopleEvent> {
        private static volatile TapAddPeopleEvent[] _emptyArray;
        public int type;

        public interface Type {
            public static final int BEST = 1;
            public static final int CITY = 2;
            public static final int UNKNOWN = 0;
        }

        public static TapAddPeopleEvent[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new TapAddPeopleEvent[0];
                    }
                }
            }
            return _emptyArray;
        }

        public TapAddPeopleEvent() {
            clear();
        }

        public TapAddPeopleEvent clear() {
            this.type = 0;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof TapAddPeopleEvent)) {
                return false;
            }
            TapAddPeopleEvent other = (TapAddPeopleEvent) o;
            if (this.type != other.type) {
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
            int hashCode = (((getClass().getName().hashCode() + 527) * 31) + this.type) * 31;
            int hashCode2 = (this.unknownFieldData == null || this.unknownFieldData.isEmpty()) ? 0 : this.unknownFieldData.hashCode();
            return hashCode + hashCode2;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.type != 0) {
                output.writeInt32(1, this.type);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.type != 0) {
                return size + CodedOutputByteBufferNano.computeInt32Size(1, this.type);
            }
            return size;
        }

        public TapAddPeopleEvent mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_GET_LINK /*8*/:
                        int value = input.readInt32();
                        switch (value) {
                            case Action.UNKNOWN /*0*/:
                            case Type.TEMPORARY /*1*/:
                            case Type.INDEFINITELY /*2*/:
                                this.type = value;
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

        public static TapAddPeopleEvent parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (TapAddPeopleEvent) MessageNano.mergeFrom(new TapAddPeopleEvent(), data);
        }

        public static TapAddPeopleEvent parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new TapAddPeopleEvent().mergeFrom(input);
        }
    }

    public static final class TapInfoEvent extends ExtendableMessageNano<TapInfoEvent> {
        private static volatile TapInfoEvent[] _emptyArray;
        public int type;

        public interface Type {
            public static final int BEST = 1;
            public static final int CITY = 2;
            public static final int UNKNOWN = 0;
        }

        public static TapInfoEvent[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new TapInfoEvent[0];
                    }
                }
            }
            return _emptyArray;
        }

        public TapInfoEvent() {
            clear();
        }

        public TapInfoEvent clear() {
            this.type = 0;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof TapInfoEvent)) {
                return false;
            }
            TapInfoEvent other = (TapInfoEvent) o;
            if (this.type != other.type) {
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
            int hashCode = (((getClass().getName().hashCode() + 527) * 31) + this.type) * 31;
            int hashCode2 = (this.unknownFieldData == null || this.unknownFieldData.isEmpty()) ? 0 : this.unknownFieldData.hashCode();
            return hashCode + hashCode2;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.type != 0) {
                output.writeInt32(1, this.type);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.type != 0) {
                return size + CodedOutputByteBufferNano.computeInt32Size(1, this.type);
            }
            return size;
        }

        public TapInfoEvent mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_GET_LINK /*8*/:
                        int value = input.readInt32();
                        switch (value) {
                            case Action.UNKNOWN /*0*/:
                            case Type.TEMPORARY /*1*/:
                            case Type.INDEFINITELY /*2*/:
                                this.type = value;
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

        public static TapInfoEvent parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (TapInfoEvent) MessageNano.mergeFrom(new TapInfoEvent(), data);
        }

        public static TapInfoEvent parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new TapInfoEvent().mergeFrom(input);
        }
    }

    public static final class TimeSelection extends ExtendableMessageNano<TimeSelection> {
        private static volatile TimeSelection[] _emptyArray;
        public int minutes;
        public int type;

        public interface Type {
            public static final int CUSTOM = 3;
            public static final int INDEFINITELY = 2;
            public static final int TEMPORARY = 1;
            public static final int UNKNOWN = 0;
        }

        public static TimeSelection[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new TimeSelection[0];
                    }
                }
            }
            return _emptyArray;
        }

        public TimeSelection() {
            clear();
        }

        public TimeSelection clear() {
            this.type = 0;
            this.minutes = 0;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof TimeSelection)) {
                return false;
            }
            TimeSelection other = (TimeSelection) o;
            if (this.type != other.type || this.minutes != other.minutes) {
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
            int hashCode = (((((getClass().getName().hashCode() + 527) * 31) + this.type) * 31) + this.minutes) * 31;
            int hashCode2 = (this.unknownFieldData == null || this.unknownFieldData.isEmpty()) ? 0 : this.unknownFieldData.hashCode();
            return hashCode + hashCode2;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.type != 0) {
                output.writeInt32(1, this.type);
            }
            if (this.minutes != 0) {
                output.writeInt32(2, this.minutes);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.type != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(1, this.type);
            }
            if (this.minutes != 0) {
                return size + CodedOutputByteBufferNano.computeInt32Size(2, this.minutes);
            }
            return size;
        }

        public TimeSelection mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_GET_LINK /*8*/:
                        int value = input.readInt32();
                        switch (value) {
                            case Action.UNKNOWN /*0*/:
                            case Type.TEMPORARY /*1*/:
                            case Type.INDEFINITELY /*2*/:
                            case Type.CUSTOM /*3*/:
                                this.type = value;
                                break;
                            default:
                                continue;
                        }
                    case LogSource.GMS_CORE_PEOPLE /*16*/:
                        this.minutes = input.readInt32();
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

        public static TimeSelection parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (TimeSelection) MessageNano.mergeFrom(new TimeSelection(), data);
        }

        public static TimeSelection parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new TimeSelection().mergeFrom(input);
        }
    }
}
