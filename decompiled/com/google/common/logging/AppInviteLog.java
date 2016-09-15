package com.google.common.logging;

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
import com.google.wireless.android.play.playlog.proto.UserLocation.OvenFreshEvent.OvenFreshResult;
import com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection;
import java.io.IOException;

public interface AppInviteLog {

    public static final class AcceptInvitationEvent extends ExtendableMessageNano<AcceptInvitationEvent> {
        private static volatile AcceptInvitationEvent[] _emptyArray;
        public InvitedApplicationInfo applicationInfo;
        public DurableDeepLinkInfo ddlInfo;
        public int destinationChannel;
        public int invitationChannel;
        public boolean isAppInstalled;
        public boolean launchFromDeepLink;

        public static AcceptInvitationEvent[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new AcceptInvitationEvent[0];
                    }
                }
            }
            return _emptyArray;
        }

        public AcceptInvitationEvent() {
            clear();
        }

        public AcceptInvitationEvent clear() {
            this.applicationInfo = null;
            this.invitationChannel = 0;
            this.isAppInstalled = false;
            this.destinationChannel = 0;
            this.launchFromDeepLink = false;
            this.ddlInfo = null;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof AcceptInvitationEvent)) {
                return false;
            }
            AcceptInvitationEvent other = (AcceptInvitationEvent) o;
            if (this.applicationInfo == null) {
                if (other.applicationInfo != null) {
                    return false;
                }
            } else if (!this.applicationInfo.equals(other.applicationInfo)) {
                return false;
            }
            if (this.invitationChannel != other.invitationChannel || this.isAppInstalled != other.isAppInstalled || this.destinationChannel != other.destinationChannel || this.launchFromDeepLink != other.launchFromDeepLink) {
                return false;
            }
            if (this.ddlInfo == null) {
                if (other.ddlInfo != null) {
                    return false;
                }
            } else if (!this.ddlInfo.equals(other.ddlInfo)) {
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
            int i;
            int i2 = 1231;
            int i3 = 0;
            int hashCode = (((((getClass().getName().hashCode() + 527) * 31) + (this.applicationInfo == null ? 0 : this.applicationInfo.hashCode())) * 31) + this.invitationChannel) * 31;
            if (this.isAppInstalled) {
                i = 1231;
            } else {
                i = 1237;
            }
            i = (((hashCode + i) * 31) + this.destinationChannel) * 31;
            if (!this.launchFromDeepLink) {
                i2 = 1237;
            }
            i = (((i + i2) * 31) + (this.ddlInfo == null ? 0 : this.ddlInfo.hashCode())) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i3 = this.unknownFieldData.hashCode();
            }
            return i + i3;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.applicationInfo != null) {
                output.writeMessage(1, this.applicationInfo);
            }
            if (this.invitationChannel != 0) {
                output.writeInt32(2, this.invitationChannel);
            }
            if (this.isAppInstalled) {
                output.writeBool(3, this.isAppInstalled);
            }
            if (this.destinationChannel != 0) {
                output.writeInt32(4, this.destinationChannel);
            }
            if (this.launchFromDeepLink) {
                output.writeBool(5, this.launchFromDeepLink);
            }
            if (this.ddlInfo != null) {
                output.writeMessage(6, this.ddlInfo);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.applicationInfo != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(1, this.applicationInfo);
            }
            if (this.invitationChannel != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(2, this.invitationChannel);
            }
            if (this.isAppInstalled) {
                size += CodedOutputByteBufferNano.computeBoolSize(3, this.isAppInstalled);
            }
            if (this.destinationChannel != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(4, this.destinationChannel);
            }
            if (this.launchFromDeepLink) {
                size += CodedOutputByteBufferNano.computeBoolSize(5, this.launchFromDeepLink);
            }
            if (this.ddlInfo != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(6, this.ddlInfo);
            }
            return size;
        }

        public AcceptInvitationEvent mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                int value;
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        if (this.applicationInfo == null) {
                            this.applicationInfo = new InvitedApplicationInfo();
                        }
                        input.readMessage(this.applicationInfo);
                        continue;
                    case LogSource.GMS_CORE_PEOPLE /*16*/:
                        value = input.readInt32();
                        switch (value) {
                            case Action.UNKNOWN /*0*/:
                            case TimeSelection.Type.TEMPORARY /*1*/:
                            case TimeSelection.Type.INDEFINITELY /*2*/:
                            case TimeSelection.Type.CUSTOM /*3*/:
                                this.invitationChannel = value;
                                break;
                            default:
                                continue;
                        }
                    case LogSource.LB_C /*24*/:
                        this.isAppInstalled = input.readBool();
                        continue;
                    case LogSource.SOCIAL /*32*/:
                        value = input.readInt32();
                        switch (value) {
                            case Action.UNKNOWN /*0*/:
                            case TimeSelection.Type.TEMPORARY /*1*/:
                            case TimeSelection.Type.INDEFINITELY /*2*/:
                            case TimeSelection.Type.CUSTOM /*3*/:
                                this.destinationChannel = value;
                                break;
                            default:
                                continue;
                        }
                    case LogSource.COPRESENCE /*40*/:
                        this.launchFromDeepLink = input.readBool();
                        continue;
                    case LogSource.HANGOUT_LOG_REQUEST /*50*/:
                        if (this.ddlInfo == null) {
                            this.ddlInfo = new DurableDeepLinkInfo();
                        }
                        input.readMessage(this.ddlInfo);
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

        public static AcceptInvitationEvent parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (AcceptInvitationEvent) MessageNano.mergeFrom(new AcceptInvitationEvent(), data);
        }

        public static AcceptInvitationEvent parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new AcceptInvitationEvent().mergeFrom(input);
        }
    }

    public static final class AndroidSwitchAccountEvent extends ExtendableMessageNano<AndroidSwitchAccountEvent> {
        private static volatile AndroidSwitchAccountEvent[] _emptyArray;
        public boolean accountSwitched;

        public static AndroidSwitchAccountEvent[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new AndroidSwitchAccountEvent[0];
                    }
                }
            }
            return _emptyArray;
        }

        public AndroidSwitchAccountEvent() {
            clear();
        }

        public AndroidSwitchAccountEvent clear() {
            this.accountSwitched = false;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof AndroidSwitchAccountEvent)) {
                return false;
            }
            AndroidSwitchAccountEvent other = (AndroidSwitchAccountEvent) o;
            if (this.accountSwitched != other.accountSwitched) {
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
            int hashCode = (((getClass().getName().hashCode() + 527) * 31) + (this.accountSwitched ? 1231 : 1237)) * 31;
            int hashCode2 = (this.unknownFieldData == null || this.unknownFieldData.isEmpty()) ? 0 : this.unknownFieldData.hashCode();
            return hashCode + hashCode2;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.accountSwitched) {
                output.writeBool(1, this.accountSwitched);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.accountSwitched) {
                return size + CodedOutputByteBufferNano.computeBoolSize(1, this.accountSwitched);
            }
            return size;
        }

        public AndroidSwitchAccountEvent mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_GET_LINK /*8*/:
                        this.accountSwitched = input.readBool();
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

        public static AndroidSwitchAccountEvent parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (AndroidSwitchAccountEvent) MessageNano.mergeFrom(new AndroidSwitchAccountEvent(), data);
        }

        public static AndroidSwitchAccountEvent parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new AndroidSwitchAccountEvent().mergeFrom(input);
        }
    }

    public interface AppInstallChannel {
        public static final int APP_STORE = 3;
        public static final int INLINE_INSTALL = 2;
        public static final int PLAY_STORE = 1;
        public static final int UNKNOWN_INSTALL_CHANNEL = 0;
    }

    public static final class AppInstalledEvent extends ExtendableMessageNano<AppInstalledEvent> {
        private static volatile AppInstalledEvent[] _emptyArray;
        public int appInstallChannel;
        public InvitedApplicationInfo applicationInfo;
        public int invitationChannel;
        public boolean isUpgradeEvent;
        public boolean launchFromContinueUrl;

        public static AppInstalledEvent[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new AppInstalledEvent[0];
                    }
                }
            }
            return _emptyArray;
        }

        public AppInstalledEvent() {
            clear();
        }

        public AppInstalledEvent clear() {
            this.applicationInfo = null;
            this.invitationChannel = 0;
            this.appInstallChannel = 0;
            this.launchFromContinueUrl = false;
            this.isUpgradeEvent = false;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof AppInstalledEvent)) {
                return false;
            }
            AppInstalledEvent other = (AppInstalledEvent) o;
            if (this.applicationInfo == null) {
                if (other.applicationInfo != null) {
                    return false;
                }
            } else if (!this.applicationInfo.equals(other.applicationInfo)) {
                return false;
            }
            if (this.invitationChannel != other.invitationChannel || this.appInstallChannel != other.appInstallChannel || this.launchFromContinueUrl != other.launchFromContinueUrl || this.isUpgradeEvent != other.isUpgradeEvent) {
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
            int i;
            int i2 = 1231;
            int i3 = 0;
            int hashCode = (((((((getClass().getName().hashCode() + 527) * 31) + (this.applicationInfo == null ? 0 : this.applicationInfo.hashCode())) * 31) + this.invitationChannel) * 31) + this.appInstallChannel) * 31;
            if (this.launchFromContinueUrl) {
                i = 1231;
            } else {
                i = 1237;
            }
            i = (hashCode + i) * 31;
            if (!this.isUpgradeEvent) {
                i2 = 1237;
            }
            i = (i + i2) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i3 = this.unknownFieldData.hashCode();
            }
            return i + i3;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.applicationInfo != null) {
                output.writeMessage(1, this.applicationInfo);
            }
            if (this.invitationChannel != 0) {
                output.writeInt32(2, this.invitationChannel);
            }
            if (this.appInstallChannel != 0) {
                output.writeInt32(3, this.appInstallChannel);
            }
            if (this.launchFromContinueUrl) {
                output.writeBool(4, this.launchFromContinueUrl);
            }
            if (this.isUpgradeEvent) {
                output.writeBool(5, this.isUpgradeEvent);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.applicationInfo != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(1, this.applicationInfo);
            }
            if (this.invitationChannel != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(2, this.invitationChannel);
            }
            if (this.appInstallChannel != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(3, this.appInstallChannel);
            }
            if (this.launchFromContinueUrl) {
                size += CodedOutputByteBufferNano.computeBoolSize(4, this.launchFromContinueUrl);
            }
            if (this.isUpgradeEvent) {
                return size + CodedOutputByteBufferNano.computeBoolSize(5, this.isUpgradeEvent);
            }
            return size;
        }

        public AppInstalledEvent mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                int value;
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        if (this.applicationInfo == null) {
                            this.applicationInfo = new InvitedApplicationInfo();
                        }
                        input.readMessage(this.applicationInfo);
                        continue;
                    case LogSource.GMS_CORE_PEOPLE /*16*/:
                        value = input.readInt32();
                        switch (value) {
                            case Action.UNKNOWN /*0*/:
                            case TimeSelection.Type.TEMPORARY /*1*/:
                            case TimeSelection.Type.INDEFINITELY /*2*/:
                            case TimeSelection.Type.CUSTOM /*3*/:
                                this.invitationChannel = value;
                                break;
                            default:
                                continue;
                        }
                    case LogSource.LB_C /*24*/:
                        value = input.readInt32();
                        switch (value) {
                            case Action.UNKNOWN /*0*/:
                            case TimeSelection.Type.TEMPORARY /*1*/:
                            case TimeSelection.Type.INDEFINITELY /*2*/:
                            case TimeSelection.Type.CUSTOM /*3*/:
                                this.appInstallChannel = value;
                                break;
                            default:
                                continue;
                        }
                    case LogSource.SOCIAL /*32*/:
                        this.launchFromContinueUrl = input.readBool();
                        continue;
                    case LogSource.COPRESENCE /*40*/:
                        this.isUpgradeEvent = input.readBool();
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

        public static AppInstalledEvent parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (AppInstalledEvent) MessageNano.mergeFrom(new AppInstalledEvent(), data);
        }

        public static AppInstalledEvent parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new AppInstalledEvent().mergeFrom(input);
        }
    }

    public static final class AppInviteEvent extends ExtendableMessageNano<AppInviteEvent> {
        private static volatile AppInviteEvent[] _emptyArray;
        public AcceptInvitationEvent acceptInvitationEvent;
        public AndroidSwitchAccountEvent androidSwitchAccountEvent;
        public AppInstalledEvent appInstalledEvent;
        public ClickPersonEvent clickPersonEvent;
        public EditMessageEvent editMessageEvent;
        public int eventType;
        public SearchPeopleEvent searchPeopleEvent;
        public SelectChannelEvent selectChannelEvent;
        public String sessionId;
        public WidgetFinishedEvent widgetFinishedEvent;
        public WidgetLaunchEvent widgetLaunchEvent;
        public WidgetLoadedEvent widgetLoadedEvent;

        public interface EventType {
            public static final int ACCEPT_INVITATION_EVENT = 9;
            public static final int ANDROID_SWITCH_ACCOUNT_EVENT = 8;
            public static final int APP_INSTALLED_EVENT = 10;
            public static final int CLICK_PERSON_EVENT = 4;
            public static final int EDIT_MESSAGE_EVENT = 3;
            public static final int SEARCH_PEOPLE_EVENT = 5;
            public static final int SELECT_CHANNEL_EVENT = 6;
            public static final int UNKNOWN = 0;
            public static final int WIDGET_FINISHED_EVENT = 7;
            public static final int WIDGET_LAUNCH_EVENT = 1;
            public static final int WIDGET_LOADED_EVENT = 2;
        }

        public static AppInviteEvent[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new AppInviteEvent[0];
                    }
                }
            }
            return _emptyArray;
        }

        public AppInviteEvent() {
            clear();
        }

        public AppInviteEvent clear() {
            this.sessionId = BuildConfig.VERSION_NAME;
            this.eventType = 0;
            this.widgetLaunchEvent = null;
            this.widgetLoadedEvent = null;
            this.editMessageEvent = null;
            this.clickPersonEvent = null;
            this.searchPeopleEvent = null;
            this.selectChannelEvent = null;
            this.widgetFinishedEvent = null;
            this.androidSwitchAccountEvent = null;
            this.acceptInvitationEvent = null;
            this.appInstalledEvent = null;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof AppInviteEvent)) {
                return false;
            }
            AppInviteEvent other = (AppInviteEvent) o;
            if (this.sessionId == null) {
                if (other.sessionId != null) {
                    return false;
                }
            } else if (!this.sessionId.equals(other.sessionId)) {
                return false;
            }
            if (this.eventType != other.eventType) {
                return false;
            }
            if (this.widgetLaunchEvent == null) {
                if (other.widgetLaunchEvent != null) {
                    return false;
                }
            } else if (!this.widgetLaunchEvent.equals(other.widgetLaunchEvent)) {
                return false;
            }
            if (this.widgetLoadedEvent == null) {
                if (other.widgetLoadedEvent != null) {
                    return false;
                }
            } else if (!this.widgetLoadedEvent.equals(other.widgetLoadedEvent)) {
                return false;
            }
            if (this.editMessageEvent == null) {
                if (other.editMessageEvent != null) {
                    return false;
                }
            } else if (!this.editMessageEvent.equals(other.editMessageEvent)) {
                return false;
            }
            if (this.clickPersonEvent == null) {
                if (other.clickPersonEvent != null) {
                    return false;
                }
            } else if (!this.clickPersonEvent.equals(other.clickPersonEvent)) {
                return false;
            }
            if (this.searchPeopleEvent == null) {
                if (other.searchPeopleEvent != null) {
                    return false;
                }
            } else if (!this.searchPeopleEvent.equals(other.searchPeopleEvent)) {
                return false;
            }
            if (this.selectChannelEvent == null) {
                if (other.selectChannelEvent != null) {
                    return false;
                }
            } else if (!this.selectChannelEvent.equals(other.selectChannelEvent)) {
                return false;
            }
            if (this.widgetFinishedEvent == null) {
                if (other.widgetFinishedEvent != null) {
                    return false;
                }
            } else if (!this.widgetFinishedEvent.equals(other.widgetFinishedEvent)) {
                return false;
            }
            if (this.androidSwitchAccountEvent == null) {
                if (other.androidSwitchAccountEvent != null) {
                    return false;
                }
            } else if (!this.androidSwitchAccountEvent.equals(other.androidSwitchAccountEvent)) {
                return false;
            }
            if (this.acceptInvitationEvent == null) {
                if (other.acceptInvitationEvent != null) {
                    return false;
                }
            } else if (!this.acceptInvitationEvent.equals(other.acceptInvitationEvent)) {
                return false;
            }
            if (this.appInstalledEvent == null) {
                if (other.appInstalledEvent != null) {
                    return false;
                }
            } else if (!this.appInstalledEvent.equals(other.appInstalledEvent)) {
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
            int hashCode = (((((((((((((((((((((((((getClass().getName().hashCode() + 527) * 31) + (this.sessionId == null ? 0 : this.sessionId.hashCode())) * 31) + this.eventType) * 31) + (this.widgetLaunchEvent == null ? 0 : this.widgetLaunchEvent.hashCode())) * 31) + (this.widgetLoadedEvent == null ? 0 : this.widgetLoadedEvent.hashCode())) * 31) + (this.editMessageEvent == null ? 0 : this.editMessageEvent.hashCode())) * 31) + (this.clickPersonEvent == null ? 0 : this.clickPersonEvent.hashCode())) * 31) + (this.searchPeopleEvent == null ? 0 : this.searchPeopleEvent.hashCode())) * 31) + (this.selectChannelEvent == null ? 0 : this.selectChannelEvent.hashCode())) * 31) + (this.widgetFinishedEvent == null ? 0 : this.widgetFinishedEvent.hashCode())) * 31) + (this.androidSwitchAccountEvent == null ? 0 : this.androidSwitchAccountEvent.hashCode())) * 31) + (this.acceptInvitationEvent == null ? 0 : this.acceptInvitationEvent.hashCode())) * 31) + (this.appInstalledEvent == null ? 0 : this.appInstalledEvent.hashCode())) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (!this.sessionId.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(1, this.sessionId);
            }
            if (this.eventType != 0) {
                output.writeInt32(2, this.eventType);
            }
            if (this.widgetLaunchEvent != null) {
                output.writeMessage(3, this.widgetLaunchEvent);
            }
            if (this.widgetLoadedEvent != null) {
                output.writeMessage(4, this.widgetLoadedEvent);
            }
            if (this.editMessageEvent != null) {
                output.writeMessage(5, this.editMessageEvent);
            }
            if (this.clickPersonEvent != null) {
                output.writeMessage(6, this.clickPersonEvent);
            }
            if (this.searchPeopleEvent != null) {
                output.writeMessage(7, this.searchPeopleEvent);
            }
            if (this.selectChannelEvent != null) {
                output.writeMessage(8, this.selectChannelEvent);
            }
            if (this.widgetFinishedEvent != null) {
                output.writeMessage(9, this.widgetFinishedEvent);
            }
            if (this.androidSwitchAccountEvent != null) {
                output.writeMessage(10, this.androidSwitchAccountEvent);
            }
            if (this.acceptInvitationEvent != null) {
                output.writeMessage(11, this.acceptInvitationEvent);
            }
            if (this.appInstalledEvent != null) {
                output.writeMessage(12, this.appInstalledEvent);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (!this.sessionId.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.sessionId);
            }
            if (this.eventType != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(2, this.eventType);
            }
            if (this.widgetLaunchEvent != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(3, this.widgetLaunchEvent);
            }
            if (this.widgetLoadedEvent != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(4, this.widgetLoadedEvent);
            }
            if (this.editMessageEvent != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(5, this.editMessageEvent);
            }
            if (this.clickPersonEvent != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(6, this.clickPersonEvent);
            }
            if (this.searchPeopleEvent != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(7, this.searchPeopleEvent);
            }
            if (this.selectChannelEvent != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(8, this.selectChannelEvent);
            }
            if (this.widgetFinishedEvent != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(9, this.widgetFinishedEvent);
            }
            if (this.androidSwitchAccountEvent != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(10, this.androidSwitchAccountEvent);
            }
            if (this.acceptInvitationEvent != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(11, this.acceptInvitationEvent);
            }
            if (this.appInstalledEvent != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(12, this.appInstalledEvent);
            }
            return size;
        }

        public AppInviteEvent mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        this.sessionId = input.readString();
                        continue;
                    case LogSource.GMS_CORE_PEOPLE /*16*/:
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
                                this.eventType = value;
                                break;
                            default:
                                continue;
                        }
                    case LogSource.ANDROID_CAMERA /*26*/:
                        if (this.widgetLaunchEvent == null) {
                            this.widgetLaunchEvent = new WidgetLaunchEvent();
                        }
                        input.readMessage(this.widgetLaunchEvent);
                        continue;
                    case LogSource.NOVA /*34*/:
                        if (this.widgetLoadedEvent == null) {
                            this.widgetLoadedEvent = new WidgetLoadedEvent();
                        }
                        input.readMessage(this.widgetLoadedEvent);
                        continue;
                    case LogSource.PHOTOS /*42*/:
                        if (this.editMessageEvent == null) {
                            this.editMessageEvent = new EditMessageEvent();
                        }
                        input.readMessage(this.editMessageEvent);
                        continue;
                    case LogSource.HANGOUT_LOG_REQUEST /*50*/:
                        if (this.clickPersonEvent == null) {
                            this.clickPersonEvent = new ClickPersonEvent();
                        }
                        input.readMessage(this.clickPersonEvent);
                        continue;
                    case LogSource.SLIDES /*58*/:
                        if (this.searchPeopleEvent == null) {
                            this.searchPeopleEvent = new SearchPeopleEvent();
                        }
                        input.readMessage(this.searchPeopleEvent);
                        continue;
                    case LogSource.WIFI_ASSISTANT /*66*/:
                        if (this.selectChannelEvent == null) {
                            this.selectChannelEvent = new SelectChannelEvent();
                        }
                        input.readMessage(this.selectChannelEvent);
                        continue;
                    case LogSource.MOVIES /*74*/:
                        if (this.widgetFinishedEvent == null) {
                            this.widgetFinishedEvent = new WidgetFinishedEvent();
                        }
                        input.readMessage(this.widgetFinishedEvent);
                        continue;
                    case LogSource.GOOGLE_EXPRESS /*82*/:
                        if (this.androidSwitchAccountEvent == null) {
                            this.androidSwitchAccountEvent = new AndroidSwitchAccountEvent();
                        }
                        input.readMessage(this.androidSwitchAccountEvent);
                        continue;
                    case LogSource.TRON_COUNTERS /*90*/:
                        if (this.acceptInvitationEvent == null) {
                            this.acceptInvitationEvent = new AcceptInvitationEvent();
                        }
                        input.readMessage(this.acceptInvitationEvent);
                        continue;
                    case LogSource.TACHYON_LOG_REQUEST /*98*/:
                        if (this.appInstalledEvent == null) {
                            this.appInstalledEvent = new AppInstalledEvent();
                        }
                        input.readMessage(this.appInstalledEvent);
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

        public static AppInviteEvent parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (AppInviteEvent) MessageNano.mergeFrom(new AppInviteEvent(), data);
        }

        public static AppInviteEvent parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new AppInviteEvent().mergeFrom(input);
        }
    }

    public static final class AppInviteInvitee extends ExtendableMessageNano<AppInviteInvitee> {
        private static volatile AppInviteInvitee[] _emptyArray;
        public int type;

        public interface Type {
            public static final int EMAIL = 2;
            public static final int GAIA = 1;
            public static final int PHONE = 3;
            public static final int UNKNOWN_TYPE = 0;
        }

        public static AppInviteInvitee[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new AppInviteInvitee[0];
                    }
                }
            }
            return _emptyArray;
        }

        public AppInviteInvitee() {
            clear();
        }

        public AppInviteInvitee clear() {
            this.type = 0;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof AppInviteInvitee)) {
                return false;
            }
            AppInviteInvitee other = (AppInviteInvitee) o;
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

        public AppInviteInvitee mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case com.google.wireless.android.play.playlog.proto.UserLocation.LocationSharingEvent.Type.TAP_GET_LINK /*8*/:
                        int value = input.readInt32();
                        switch (value) {
                            case Action.UNKNOWN /*0*/:
                            case com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection.Type.TEMPORARY /*1*/:
                            case com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection.Type.INDEFINITELY /*2*/:
                            case com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection.Type.CUSTOM /*3*/:
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

        public static AppInviteInvitee parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (AppInviteInvitee) MessageNano.mergeFrom(new AppInviteInvitee(), data);
        }

        public static AppInviteInvitee parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new AppInviteInvitee().mergeFrom(input);
        }
    }

    public static final class ClickPersonEvent extends ExtendableMessageNano<ClickPersonEvent> {
        private static volatile ClickPersonEvent[] _emptyArray;
        public int indexOfPerson;
        public AppInviteInvitee invitee;
        public int personSource;
        public boolean selected;

        public interface PersonSource {
            public static final int EVERYONE_LIST = 2;
            public static final int SEARCH_RESULT = 3;
            public static final int SUGGESTIONS = 1;
            public static final int TO_FIELD = 4;
            public static final int UNKNOWN_SOURCE = 0;
        }

        public static ClickPersonEvent[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new ClickPersonEvent[0];
                    }
                }
            }
            return _emptyArray;
        }

        public ClickPersonEvent() {
            clear();
        }

        public ClickPersonEvent clear() {
            this.personSource = 0;
            this.invitee = null;
            this.indexOfPerson = 0;
            this.selected = false;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof ClickPersonEvent)) {
                return false;
            }
            ClickPersonEvent other = (ClickPersonEvent) o;
            if (this.personSource != other.personSource) {
                return false;
            }
            if (this.invitee == null) {
                if (other.invitee != null) {
                    return false;
                }
            } else if (!this.invitee.equals(other.invitee)) {
                return false;
            }
            if (this.indexOfPerson != other.indexOfPerson || this.selected != other.selected) {
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
            int hashCode = (((((((((getClass().getName().hashCode() + 527) * 31) + this.personSource) * 31) + (this.invitee == null ? 0 : this.invitee.hashCode())) * 31) + this.indexOfPerson) * 31) + (this.selected ? 1231 : 1237)) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.personSource != 0) {
                output.writeInt32(1, this.personSource);
            }
            if (this.invitee != null) {
                output.writeMessage(2, this.invitee);
            }
            if (this.indexOfPerson != 0) {
                output.writeUInt32(3, this.indexOfPerson);
            }
            if (this.selected) {
                output.writeBool(4, this.selected);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.personSource != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(1, this.personSource);
            }
            if (this.invitee != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(2, this.invitee);
            }
            if (this.indexOfPerson != 0) {
                size += CodedOutputByteBufferNano.computeUInt32Size(3, this.indexOfPerson);
            }
            if (this.selected) {
                return size + CodedOutputByteBufferNano.computeBoolSize(4, this.selected);
            }
            return size;
        }

        public ClickPersonEvent mergeFrom(CodedInputByteBufferNano input) throws IOException {
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
                                this.personSource = value;
                                break;
                            default:
                                continue;
                        }
                    case LogSource.GOOGLE_ANALYTICS /*18*/:
                        if (this.invitee == null) {
                            this.invitee = new AppInviteInvitee();
                        }
                        input.readMessage(this.invitee);
                        continue;
                    case LogSource.LB_C /*24*/:
                        this.indexOfPerson = input.readUInt32();
                        continue;
                    case LogSource.SOCIAL /*32*/:
                        this.selected = input.readBool();
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

        public static ClickPersonEvent parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (ClickPersonEvent) MessageNano.mergeFrom(new ClickPersonEvent(), data);
        }

        public static ClickPersonEvent parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new ClickPersonEvent().mergeFrom(input);
        }
    }

    public static final class DurableDeepLinkInfo extends ExtendableMessageNano<DurableDeepLinkInfo> {
        private static volatile DurableDeepLinkInfo[] _emptyArray;
        public String appCode;

        public static DurableDeepLinkInfo[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new DurableDeepLinkInfo[0];
                    }
                }
            }
            return _emptyArray;
        }

        public DurableDeepLinkInfo() {
            clear();
        }

        public DurableDeepLinkInfo clear() {
            this.appCode = BuildConfig.VERSION_NAME;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof DurableDeepLinkInfo)) {
                return false;
            }
            DurableDeepLinkInfo other = (DurableDeepLinkInfo) o;
            if (this.appCode == null) {
                if (other.appCode != null) {
                    return false;
                }
            } else if (!this.appCode.equals(other.appCode)) {
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
            int hashCode = (((getClass().getName().hashCode() + 527) * 31) + (this.appCode == null ? 0 : this.appCode.hashCode())) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (!this.appCode.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(1, this.appCode);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.appCode.equals(BuildConfig.VERSION_NAME)) {
                return size;
            }
            return size + CodedOutputByteBufferNano.computeStringSize(1, this.appCode);
        }

        public DurableDeepLinkInfo mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        this.appCode = input.readString();
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

        public static DurableDeepLinkInfo parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (DurableDeepLinkInfo) MessageNano.mergeFrom(new DurableDeepLinkInfo(), data);
        }

        public static DurableDeepLinkInfo parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new DurableDeepLinkInfo().mergeFrom(input);
        }
    }

    public static final class EditMessageEvent extends ExtendableMessageNano<EditMessageEvent> {
        private static volatile EditMessageEvent[] _emptyArray;
        public boolean characterLimitExceeded;
        public int newMessageLength;

        public static EditMessageEvent[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new EditMessageEvent[0];
                    }
                }
            }
            return _emptyArray;
        }

        public EditMessageEvent() {
            clear();
        }

        public EditMessageEvent clear() {
            this.characterLimitExceeded = false;
            this.newMessageLength = 0;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof EditMessageEvent)) {
                return false;
            }
            EditMessageEvent other = (EditMessageEvent) o;
            if (this.characterLimitExceeded != other.characterLimitExceeded || this.newMessageLength != other.newMessageLength) {
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
            int hashCode = (((((getClass().getName().hashCode() + 527) * 31) + (this.characterLimitExceeded ? 1231 : 1237)) * 31) + this.newMessageLength) * 31;
            int hashCode2 = (this.unknownFieldData == null || this.unknownFieldData.isEmpty()) ? 0 : this.unknownFieldData.hashCode();
            return hashCode + hashCode2;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.characterLimitExceeded) {
                output.writeBool(1, this.characterLimitExceeded);
            }
            if (this.newMessageLength != 0) {
                output.writeUInt32(2, this.newMessageLength);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.characterLimitExceeded) {
                size += CodedOutputByteBufferNano.computeBoolSize(1, this.characterLimitExceeded);
            }
            if (this.newMessageLength != 0) {
                return size + CodedOutputByteBufferNano.computeUInt32Size(2, this.newMessageLength);
            }
            return size;
        }

        public EditMessageEvent mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_GET_LINK /*8*/:
                        this.characterLimitExceeded = input.readBool();
                        continue;
                    case LogSource.GMS_CORE_PEOPLE /*16*/:
                        this.newMessageLength = input.readUInt32();
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

        public static EditMessageEvent parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (EditMessageEvent) MessageNano.mergeFrom(new EditMessageEvent(), data);
        }

        public static EditMessageEvent parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new EditMessageEvent().mergeFrom(input);
        }
    }

    public interface InvitationChannel {
        public static final int EMAIL = 2;
        public static final int GOOGLE = 1;
        public static final int SMS = 3;
        public static final int UNKNOWN_CHANNEL = 0;
    }

    public static final class InvitedApplicationInfo extends ExtendableMessageNano<InvitedApplicationInfo> {
        private static volatile InvitedApplicationInfo[] _emptyArray;
        public String androidPackageName;
        public String iosClientId;

        public static InvitedApplicationInfo[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new InvitedApplicationInfo[0];
                    }
                }
            }
            return _emptyArray;
        }

        public InvitedApplicationInfo() {
            clear();
        }

        public InvitedApplicationInfo clear() {
            this.iosClientId = BuildConfig.VERSION_NAME;
            this.androidPackageName = BuildConfig.VERSION_NAME;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof InvitedApplicationInfo)) {
                return false;
            }
            InvitedApplicationInfo other = (InvitedApplicationInfo) o;
            if (this.iosClientId == null) {
                if (other.iosClientId != null) {
                    return false;
                }
            } else if (!this.iosClientId.equals(other.iosClientId)) {
                return false;
            }
            if (this.androidPackageName == null) {
                if (other.androidPackageName != null) {
                    return false;
                }
            } else if (!this.androidPackageName.equals(other.androidPackageName)) {
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
            int hashCode = (((((getClass().getName().hashCode() + 527) * 31) + (this.iosClientId == null ? 0 : this.iosClientId.hashCode())) * 31) + (this.androidPackageName == null ? 0 : this.androidPackageName.hashCode())) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (!this.iosClientId.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(1, this.iosClientId);
            }
            if (!this.androidPackageName.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(2, this.androidPackageName);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (!this.iosClientId.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.iosClientId);
            }
            if (this.androidPackageName.equals(BuildConfig.VERSION_NAME)) {
                return size;
            }
            return size + CodedOutputByteBufferNano.computeStringSize(2, this.androidPackageName);
        }

        public InvitedApplicationInfo mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        this.iosClientId = input.readString();
                        continue;
                    case LogSource.GOOGLE_ANALYTICS /*18*/:
                        this.androidPackageName = input.readString();
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

        public static InvitedApplicationInfo parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (InvitedApplicationInfo) MessageNano.mergeFrom(new InvitedApplicationInfo(), data);
        }

        public static InvitedApplicationInfo parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new InvitedApplicationInfo().mergeFrom(input);
        }
    }

    public static final class SearchPeopleEvent extends ExtendableMessageNano<SearchPeopleEvent> {
        private static volatile SearchPeopleEvent[] _emptyArray;
        public boolean exitWithoutSelectingPeople;

        public static SearchPeopleEvent[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new SearchPeopleEvent[0];
                    }
                }
            }
            return _emptyArray;
        }

        public SearchPeopleEvent() {
            clear();
        }

        public SearchPeopleEvent clear() {
            this.exitWithoutSelectingPeople = false;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof SearchPeopleEvent)) {
                return false;
            }
            SearchPeopleEvent other = (SearchPeopleEvent) o;
            if (this.exitWithoutSelectingPeople != other.exitWithoutSelectingPeople) {
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
            int hashCode = (((getClass().getName().hashCode() + 527) * 31) + (this.exitWithoutSelectingPeople ? 1231 : 1237)) * 31;
            int hashCode2 = (this.unknownFieldData == null || this.unknownFieldData.isEmpty()) ? 0 : this.unknownFieldData.hashCode();
            return hashCode + hashCode2;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.exitWithoutSelectingPeople) {
                output.writeBool(1, this.exitWithoutSelectingPeople);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.exitWithoutSelectingPeople) {
                return size + CodedOutputByteBufferNano.computeBoolSize(1, this.exitWithoutSelectingPeople);
            }
            return size;
        }

        public SearchPeopleEvent mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_GET_LINK /*8*/:
                        this.exitWithoutSelectingPeople = input.readBool();
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

        public static SearchPeopleEvent parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (SearchPeopleEvent) MessageNano.mergeFrom(new SearchPeopleEvent(), data);
        }

        public static SearchPeopleEvent parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new SearchPeopleEvent().mergeFrom(input);
        }
    }

    public static final class SelectChannelEvent extends ExtendableMessageNano<SelectChannelEvent> {
        private static volatile SelectChannelEvent[] _emptyArray;
        public boolean channelChanged;
        public int selectedChannelType;

        public interface ChannelType {
            public static final int EMAIL = 2;
            public static final int GOOGLE = 1;
            public static final int SMS = 3;
            public static final int UNKNOWN_CHANNEL = 0;
        }

        public static SelectChannelEvent[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new SelectChannelEvent[0];
                    }
                }
            }
            return _emptyArray;
        }

        public SelectChannelEvent() {
            clear();
        }

        public SelectChannelEvent clear() {
            this.selectedChannelType = 0;
            this.channelChanged = false;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof SelectChannelEvent)) {
                return false;
            }
            SelectChannelEvent other = (SelectChannelEvent) o;
            if (this.selectedChannelType != other.selectedChannelType || this.channelChanged != other.channelChanged) {
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
            int hashCode = (((((getClass().getName().hashCode() + 527) * 31) + this.selectedChannelType) * 31) + (this.channelChanged ? 1231 : 1237)) * 31;
            int hashCode2 = (this.unknownFieldData == null || this.unknownFieldData.isEmpty()) ? 0 : this.unknownFieldData.hashCode();
            return hashCode + hashCode2;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.selectedChannelType != 0) {
                output.writeInt32(1, this.selectedChannelType);
            }
            if (this.channelChanged) {
                output.writeBool(2, this.channelChanged);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.selectedChannelType != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(1, this.selectedChannelType);
            }
            if (this.channelChanged) {
                return size + CodedOutputByteBufferNano.computeBoolSize(2, this.channelChanged);
            }
            return size;
        }

        public SelectChannelEvent mergeFrom(CodedInputByteBufferNano input) throws IOException {
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
                                this.selectedChannelType = value;
                                break;
                            default:
                                continue;
                        }
                    case LogSource.GMS_CORE_PEOPLE /*16*/:
                        this.channelChanged = input.readBool();
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

        public static SelectChannelEvent parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (SelectChannelEvent) MessageNano.mergeFrom(new SelectChannelEvent(), data);
        }

        public static SelectChannelEvent parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new SelectChannelEvent().mergeFrom(input);
        }
    }

    public static final class WidgetFinishedEvent extends ExtendableMessageNano<WidgetFinishedEvent> {
        private static volatile WidgetFinishedEvent[] _emptyArray;
        public boolean invitesSent;
        public int numEmailRecipients;
        public int numGoogleRecipients;
        public int numRecipients;
        public int numSmsRecipients;

        public static WidgetFinishedEvent[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new WidgetFinishedEvent[0];
                    }
                }
            }
            return _emptyArray;
        }

        public WidgetFinishedEvent() {
            clear();
        }

        public WidgetFinishedEvent clear() {
            this.invitesSent = false;
            this.numRecipients = 0;
            this.numSmsRecipients = 0;
            this.numEmailRecipients = 0;
            this.numGoogleRecipients = 0;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof WidgetFinishedEvent)) {
                return false;
            }
            WidgetFinishedEvent other = (WidgetFinishedEvent) o;
            if (this.invitesSent != other.invitesSent || this.numRecipients != other.numRecipients || this.numSmsRecipients != other.numSmsRecipients || this.numEmailRecipients != other.numEmailRecipients || this.numGoogleRecipients != other.numGoogleRecipients) {
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
            int hashCode = (((((((((((getClass().getName().hashCode() + 527) * 31) + (this.invitesSent ? 1231 : 1237)) * 31) + this.numRecipients) * 31) + this.numSmsRecipients) * 31) + this.numEmailRecipients) * 31) + this.numGoogleRecipients) * 31;
            int hashCode2 = (this.unknownFieldData == null || this.unknownFieldData.isEmpty()) ? 0 : this.unknownFieldData.hashCode();
            return hashCode + hashCode2;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.invitesSent) {
                output.writeBool(1, this.invitesSent);
            }
            if (this.numRecipients != 0) {
                output.writeUInt32(2, this.numRecipients);
            }
            if (this.numSmsRecipients != 0) {
                output.writeUInt32(3, this.numSmsRecipients);
            }
            if (this.numEmailRecipients != 0) {
                output.writeUInt32(4, this.numEmailRecipients);
            }
            if (this.numGoogleRecipients != 0) {
                output.writeUInt32(5, this.numGoogleRecipients);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.invitesSent) {
                size += CodedOutputByteBufferNano.computeBoolSize(1, this.invitesSent);
            }
            if (this.numRecipients != 0) {
                size += CodedOutputByteBufferNano.computeUInt32Size(2, this.numRecipients);
            }
            if (this.numSmsRecipients != 0) {
                size += CodedOutputByteBufferNano.computeUInt32Size(3, this.numSmsRecipients);
            }
            if (this.numEmailRecipients != 0) {
                size += CodedOutputByteBufferNano.computeUInt32Size(4, this.numEmailRecipients);
            }
            if (this.numGoogleRecipients != 0) {
                return size + CodedOutputByteBufferNano.computeUInt32Size(5, this.numGoogleRecipients);
            }
            return size;
        }

        public WidgetFinishedEvent mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_GET_LINK /*8*/:
                        this.invitesSent = input.readBool();
                        continue;
                    case LogSource.GMS_CORE_PEOPLE /*16*/:
                        this.numRecipients = input.readUInt32();
                        continue;
                    case LogSource.LB_C /*24*/:
                        this.numSmsRecipients = input.readUInt32();
                        continue;
                    case LogSource.SOCIAL /*32*/:
                        this.numEmailRecipients = input.readUInt32();
                        continue;
                    case LogSource.COPRESENCE /*40*/:
                        this.numGoogleRecipients = input.readUInt32();
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

        public static WidgetFinishedEvent parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (WidgetFinishedEvent) MessageNano.mergeFrom(new WidgetFinishedEvent(), data);
        }

        public static WidgetFinishedEvent parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new WidgetFinishedEvent().mergeFrom(input);
        }
    }

    public static final class WidgetLaunchEvent extends ExtendableMessageNano<WidgetLaunchEvent> {
        private static volatile WidgetLaunchEvent[] _emptyArray;
        public WidgetLaunchInfo launchInfo;
        public int state;

        public interface LaunchState {
            public static final int LAUNCHED = 1;
            public static final int NOT_LAUNCHED = 2;
            public static final int UNKNOWN_STATE = 0;
        }

        public static WidgetLaunchEvent[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new WidgetLaunchEvent[0];
                    }
                }
            }
            return _emptyArray;
        }

        public WidgetLaunchEvent() {
            clear();
        }

        public WidgetLaunchEvent clear() {
            this.state = 0;
            this.launchInfo = null;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof WidgetLaunchEvent)) {
                return false;
            }
            WidgetLaunchEvent other = (WidgetLaunchEvent) o;
            if (this.state != other.state) {
                return false;
            }
            if (this.launchInfo == null) {
                if (other.launchInfo != null) {
                    return false;
                }
            } else if (!this.launchInfo.equals(other.launchInfo)) {
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
            int hashCode = (((((getClass().getName().hashCode() + 527) * 31) + this.state) * 31) + (this.launchInfo == null ? 0 : this.launchInfo.hashCode())) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.state != 0) {
                output.writeInt32(1, this.state);
            }
            if (this.launchInfo != null) {
                output.writeMessage(2, this.launchInfo);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.state != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(1, this.state);
            }
            if (this.launchInfo != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(2, this.launchInfo);
            }
            return size;
        }

        public WidgetLaunchEvent mergeFrom(CodedInputByteBufferNano input) throws IOException {
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
                                this.state = value;
                                break;
                            default:
                                continue;
                        }
                    case LogSource.GOOGLE_ANALYTICS /*18*/:
                        if (this.launchInfo == null) {
                            this.launchInfo = new WidgetLaunchInfo();
                        }
                        input.readMessage(this.launchInfo);
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

        public static WidgetLaunchEvent parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (WidgetLaunchEvent) MessageNano.mergeFrom(new WidgetLaunchEvent(), data);
        }

        public static WidgetLaunchEvent parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new WidgetLaunchEvent().mergeFrom(input);
        }
    }

    public static final class WidgetLaunchInfo extends ExtendableMessageNano<WidgetLaunchInfo> {
        private static volatile WidgetLaunchInfo[] _emptyArray;
        public String androidPackageName;
        public String androidSigningCertificate;
        public String appUrl;
        public AppInviteInvitee[] excludeFromSuggestions;
        public AppInviteInvitee[] includeInSuggestions;
        public int inviteMessageLength;
        public String iosBundleId;
        public String iosClientId;

        public static WidgetLaunchInfo[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new WidgetLaunchInfo[0];
                    }
                }
            }
            return _emptyArray;
        }

        public WidgetLaunchInfo() {
            clear();
        }

        public WidgetLaunchInfo clear() {
            this.iosClientId = BuildConfig.VERSION_NAME;
            this.iosBundleId = BuildConfig.VERSION_NAME;
            this.androidPackageName = BuildConfig.VERSION_NAME;
            this.androidSigningCertificate = BuildConfig.VERSION_NAME;
            this.inviteMessageLength = 0;
            this.appUrl = BuildConfig.VERSION_NAME;
            this.includeInSuggestions = AppInviteInvitee.emptyArray();
            this.excludeFromSuggestions = AppInviteInvitee.emptyArray();
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof WidgetLaunchInfo)) {
                return false;
            }
            WidgetLaunchInfo other = (WidgetLaunchInfo) o;
            if (this.iosClientId == null) {
                if (other.iosClientId != null) {
                    return false;
                }
            } else if (!this.iosClientId.equals(other.iosClientId)) {
                return false;
            }
            if (this.iosBundleId == null) {
                if (other.iosBundleId != null) {
                    return false;
                }
            } else if (!this.iosBundleId.equals(other.iosBundleId)) {
                return false;
            }
            if (this.androidPackageName == null) {
                if (other.androidPackageName != null) {
                    return false;
                }
            } else if (!this.androidPackageName.equals(other.androidPackageName)) {
                return false;
            }
            if (this.androidSigningCertificate == null) {
                if (other.androidSigningCertificate != null) {
                    return false;
                }
            } else if (!this.androidSigningCertificate.equals(other.androidSigningCertificate)) {
                return false;
            }
            if (this.inviteMessageLength != other.inviteMessageLength) {
                return false;
            }
            if (this.appUrl == null) {
                if (other.appUrl != null) {
                    return false;
                }
            } else if (!this.appUrl.equals(other.appUrl)) {
                return false;
            }
            if (!InternalNano.equals(this.includeInSuggestions, other.includeInSuggestions) || !InternalNano.equals(this.excludeFromSuggestions, other.excludeFromSuggestions)) {
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
            int hashCode = (((((((((((((((((getClass().getName().hashCode() + 527) * 31) + (this.iosClientId == null ? 0 : this.iosClientId.hashCode())) * 31) + (this.iosBundleId == null ? 0 : this.iosBundleId.hashCode())) * 31) + (this.androidPackageName == null ? 0 : this.androidPackageName.hashCode())) * 31) + (this.androidSigningCertificate == null ? 0 : this.androidSigningCertificate.hashCode())) * 31) + this.inviteMessageLength) * 31) + (this.appUrl == null ? 0 : this.appUrl.hashCode())) * 31) + InternalNano.hashCode(this.includeInSuggestions)) * 31) + InternalNano.hashCode(this.excludeFromSuggestions)) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (!this.iosClientId.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(1, this.iosClientId);
            }
            if (!this.iosBundleId.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(2, this.iosBundleId);
            }
            if (!this.androidPackageName.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(3, this.androidPackageName);
            }
            if (!this.androidSigningCertificate.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(4, this.androidSigningCertificate);
            }
            if (this.inviteMessageLength != 0) {
                output.writeUInt32(5, this.inviteMessageLength);
            }
            if (!this.appUrl.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(6, this.appUrl);
            }
            if (this.includeInSuggestions != null && this.includeInSuggestions.length > 0) {
                for (AppInviteInvitee element : this.includeInSuggestions) {
                    if (element != null) {
                        output.writeMessage(7, element);
                    }
                }
            }
            if (this.excludeFromSuggestions != null && this.excludeFromSuggestions.length > 0) {
                for (AppInviteInvitee element2 : this.excludeFromSuggestions) {
                    if (element2 != null) {
                        output.writeMessage(8, element2);
                    }
                }
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (!this.iosClientId.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.iosClientId);
            }
            if (!this.iosBundleId.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(2, this.iosBundleId);
            }
            if (!this.androidPackageName.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(3, this.androidPackageName);
            }
            if (!this.androidSigningCertificate.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(4, this.androidSigningCertificate);
            }
            if (this.inviteMessageLength != 0) {
                size += CodedOutputByteBufferNano.computeUInt32Size(5, this.inviteMessageLength);
            }
            if (!this.appUrl.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(6, this.appUrl);
            }
            if (this.includeInSuggestions != null && this.includeInSuggestions.length > 0) {
                for (AppInviteInvitee element : this.includeInSuggestions) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(7, element);
                    }
                }
            }
            if (this.excludeFromSuggestions != null && this.excludeFromSuggestions.length > 0) {
                for (AppInviteInvitee element2 : this.excludeFromSuggestions) {
                    if (element2 != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(8, element2);
                    }
                }
            }
            return size;
        }

        public WidgetLaunchInfo mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                int arrayLength;
                int i;
                AppInviteInvitee[] newArray;
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        this.iosClientId = input.readString();
                        continue;
                    case LogSource.GOOGLE_ANALYTICS /*18*/:
                        this.iosBundleId = input.readString();
                        continue;
                    case LogSource.ANDROID_CAMERA /*26*/:
                        this.androidPackageName = input.readString();
                        continue;
                    case LogSource.NOVA /*34*/:
                        this.androidSigningCertificate = input.readString();
                        continue;
                    case LogSource.COPRESENCE /*40*/:
                        this.inviteMessageLength = input.readUInt32();
                        continue;
                    case LogSource.HANGOUT_LOG_REQUEST /*50*/:
                        this.appUrl = input.readString();
                        continue;
                    case LogSource.SLIDES /*58*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 58);
                        if (this.includeInSuggestions == null) {
                            i = 0;
                        } else {
                            i = this.includeInSuggestions.length;
                        }
                        newArray = new AppInviteInvitee[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.includeInSuggestions, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new AppInviteInvitee();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i++;
                        }
                        newArray[i] = new AppInviteInvitee();
                        input.readMessage(newArray[i]);
                        this.includeInSuggestions = newArray;
                        continue;
                    case LogSource.WIFI_ASSISTANT /*66*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 66);
                        if (this.excludeFromSuggestions == null) {
                            i = 0;
                        } else {
                            i = this.excludeFromSuggestions.length;
                        }
                        newArray = new AppInviteInvitee[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.excludeFromSuggestions, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new AppInviteInvitee();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i++;
                        }
                        newArray[i] = new AppInviteInvitee();
                        input.readMessage(newArray[i]);
                        this.excludeFromSuggestions = newArray;
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

        public static WidgetLaunchInfo parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (WidgetLaunchInfo) MessageNano.mergeFrom(new WidgetLaunchInfo(), data);
        }

        public static WidgetLaunchInfo parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new WidgetLaunchInfo().mergeFrom(input);
        }
    }

    public static final class WidgetLoadedEvent extends ExtendableMessageNano<WidgetLoadedEvent> {
        private static volatile WidgetLoadedEvent[] _emptyArray;
        public int numEveryone;
        public int numSuggestions;

        public static WidgetLoadedEvent[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new WidgetLoadedEvent[0];
                    }
                }
            }
            return _emptyArray;
        }

        public WidgetLoadedEvent() {
            clear();
        }

        public WidgetLoadedEvent clear() {
            this.numSuggestions = 0;
            this.numEveryone = 0;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof WidgetLoadedEvent)) {
                return false;
            }
            WidgetLoadedEvent other = (WidgetLoadedEvent) o;
            if (this.numSuggestions != other.numSuggestions || this.numEveryone != other.numEveryone) {
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
            int hashCode = (((((getClass().getName().hashCode() + 527) * 31) + this.numSuggestions) * 31) + this.numEveryone) * 31;
            int hashCode2 = (this.unknownFieldData == null || this.unknownFieldData.isEmpty()) ? 0 : this.unknownFieldData.hashCode();
            return hashCode + hashCode2;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.numSuggestions != 0) {
                output.writeUInt32(1, this.numSuggestions);
            }
            if (this.numEveryone != 0) {
                output.writeUInt32(2, this.numEveryone);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.numSuggestions != 0) {
                size += CodedOutputByteBufferNano.computeUInt32Size(1, this.numSuggestions);
            }
            if (this.numEveryone != 0) {
                return size + CodedOutputByteBufferNano.computeUInt32Size(2, this.numEveryone);
            }
            return size;
        }

        public WidgetLoadedEvent mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_GET_LINK /*8*/:
                        this.numSuggestions = input.readUInt32();
                        continue;
                    case LogSource.GMS_CORE_PEOPLE /*16*/:
                        this.numEveryone = input.readUInt32();
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

        public static WidgetLoadedEvent parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (WidgetLoadedEvent) MessageNano.mergeFrom(new WidgetLoadedEvent(), data);
        }

        public static WidgetLoadedEvent parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new WidgetLoadedEvent().mergeFrom(input);
        }
    }
}
