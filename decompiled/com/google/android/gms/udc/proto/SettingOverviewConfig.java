package com.google.android.gms.udc.proto;

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

public final class SettingOverviewConfig extends ExtendableMessageNano<SettingOverviewConfig> {
    private static volatile SettingOverviewConfig[] _emptyArray;
    public ButtonConfig actionButton;
    public ButtonConfig[] additionalButtons;
    public TextResource description;
    public ImageResource icon;
    public ImageResource illustration;
    public TextResource illustrationBackgroundColor;
    public InfoNotice infoNotice;
    public ButtonConfig manageButton;
    public TextResource name;
    public SettingValueCaption[] settingCaptions;
    public SettingState settingState;
    public ShowDeviceSettingsAction showDeviceSettingsAction;
    public TextResource stateSummary;
    public SubsettingOverviewConfig[] subsettingConfigs;
    public TextResource title;

    public static final class ShowDeviceSettingsAction extends ExtendableMessageNano<ShowDeviceSettingsAction> {
        private static volatile ShowDeviceSettingsAction[] _emptyArray;
        public TextResource actionDescription;
        public TextResource actionLabel;
        public TextResource settingsUiInfoText;
        public TextResource settingsUiTitle;
        public int targetAccountSettingId;

        public static ShowDeviceSettingsAction[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new ShowDeviceSettingsAction[0];
                    }
                }
            }
            return _emptyArray;
        }

        public ShowDeviceSettingsAction() {
            clear();
        }

        public ShowDeviceSettingsAction clear() {
            this.actionLabel = null;
            this.actionDescription = null;
            this.targetAccountSettingId = 0;
            this.settingsUiTitle = null;
            this.settingsUiInfoText = null;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof ShowDeviceSettingsAction)) {
                return false;
            }
            ShowDeviceSettingsAction other = (ShowDeviceSettingsAction) o;
            if (this.actionLabel == null) {
                if (other.actionLabel != null) {
                    return false;
                }
            } else if (!this.actionLabel.equals(other.actionLabel)) {
                return false;
            }
            if (this.actionDescription == null) {
                if (other.actionDescription != null) {
                    return false;
                }
            } else if (!this.actionDescription.equals(other.actionDescription)) {
                return false;
            }
            if (this.targetAccountSettingId != other.targetAccountSettingId) {
                return false;
            }
            if (this.settingsUiTitle == null) {
                if (other.settingsUiTitle != null) {
                    return false;
                }
            } else if (!this.settingsUiTitle.equals(other.settingsUiTitle)) {
                return false;
            }
            if (this.settingsUiInfoText == null) {
                if (other.settingsUiInfoText != null) {
                    return false;
                }
            } else if (!this.settingsUiInfoText.equals(other.settingsUiInfoText)) {
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
            int hashCode = (((((((((((getClass().getName().hashCode() + 527) * 31) + (this.actionLabel == null ? 0 : this.actionLabel.hashCode())) * 31) + (this.actionDescription == null ? 0 : this.actionDescription.hashCode())) * 31) + this.targetAccountSettingId) * 31) + (this.settingsUiTitle == null ? 0 : this.settingsUiTitle.hashCode())) * 31) + (this.settingsUiInfoText == null ? 0 : this.settingsUiInfoText.hashCode())) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.actionLabel != null) {
                output.writeMessage(1, this.actionLabel);
            }
            if (this.actionDescription != null) {
                output.writeMessage(2, this.actionDescription);
            }
            if (this.settingsUiTitle != null) {
                output.writeMessage(3, this.settingsUiTitle);
            }
            if (this.settingsUiInfoText != null) {
                output.writeMessage(4, this.settingsUiInfoText);
            }
            if (this.targetAccountSettingId != 0) {
                output.writeInt32(5, this.targetAccountSettingId);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.actionLabel != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(1, this.actionLabel);
            }
            if (this.actionDescription != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(2, this.actionDescription);
            }
            if (this.settingsUiTitle != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(3, this.settingsUiTitle);
            }
            if (this.settingsUiInfoText != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(4, this.settingsUiInfoText);
            }
            if (this.targetAccountSettingId != 0) {
                return size + CodedOutputByteBufferNano.computeInt32Size(5, this.targetAccountSettingId);
            }
            return size;
        }

        public ShowDeviceSettingsAction mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        if (this.actionLabel == null) {
                            this.actionLabel = new TextResource();
                        }
                        input.readMessage(this.actionLabel);
                        continue;
                    case LogSource.GOOGLE_ANALYTICS /*18*/:
                        if (this.actionDescription == null) {
                            this.actionDescription = new TextResource();
                        }
                        input.readMessage(this.actionDescription);
                        continue;
                    case LogSource.ANDROID_CAMERA /*26*/:
                        if (this.settingsUiTitle == null) {
                            this.settingsUiTitle = new TextResource();
                        }
                        input.readMessage(this.settingsUiTitle);
                        continue;
                    case LogSource.NOVA /*34*/:
                        if (this.settingsUiInfoText == null) {
                            this.settingsUiInfoText = new TextResource();
                        }
                        input.readMessage(this.settingsUiInfoText);
                        continue;
                    case LogSource.COPRESENCE /*40*/:
                        this.targetAccountSettingId = input.readInt32();
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

        public static ShowDeviceSettingsAction parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (ShowDeviceSettingsAction) MessageNano.mergeFrom(new ShowDeviceSettingsAction(), data);
        }

        public static ShowDeviceSettingsAction parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new ShowDeviceSettingsAction().mergeFrom(input);
        }
    }

    public static final class SubsettingOverviewConfig extends ExtendableMessageNano<SubsettingOverviewConfig> {
        private static volatile SubsettingOverviewConfig[] _emptyArray;
        public ButtonConfig actionButton;
        public InfoNotice infoNotice;
        public TextResource label;
        public boolean noConsentRequired;
        public SettingState settingState;
        public SubsettingOverviewConfig[] subSettingConfigs;

        public static SubsettingOverviewConfig[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new SubsettingOverviewConfig[0];
                    }
                }
            }
            return _emptyArray;
        }

        public SubsettingOverviewConfig() {
            clear();
        }

        public SubsettingOverviewConfig clear() {
            this.settingState = null;
            this.label = null;
            this.actionButton = null;
            this.infoNotice = null;
            this.noConsentRequired = false;
            this.subSettingConfigs = emptyArray();
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof SubsettingOverviewConfig)) {
                return false;
            }
            SubsettingOverviewConfig other = (SubsettingOverviewConfig) o;
            if (this.settingState == null) {
                if (other.settingState != null) {
                    return false;
                }
            } else if (!this.settingState.equals(other.settingState)) {
                return false;
            }
            if (this.label == null) {
                if (other.label != null) {
                    return false;
                }
            } else if (!this.label.equals(other.label)) {
                return false;
            }
            if (this.actionButton == null) {
                if (other.actionButton != null) {
                    return false;
                }
            } else if (!this.actionButton.equals(other.actionButton)) {
                return false;
            }
            if (this.infoNotice == null) {
                if (other.infoNotice != null) {
                    return false;
                }
            } else if (!this.infoNotice.equals(other.infoNotice)) {
                return false;
            }
            if (this.noConsentRequired != other.noConsentRequired || !InternalNano.equals(this.subSettingConfigs, other.subSettingConfigs)) {
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
            int hashCode = (((((((((((((getClass().getName().hashCode() + 527) * 31) + (this.settingState == null ? 0 : this.settingState.hashCode())) * 31) + (this.label == null ? 0 : this.label.hashCode())) * 31) + (this.actionButton == null ? 0 : this.actionButton.hashCode())) * 31) + (this.infoNotice == null ? 0 : this.infoNotice.hashCode())) * 31) + (this.noConsentRequired ? 1231 : 1237)) * 31) + InternalNano.hashCode(this.subSettingConfigs)) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.settingState != null) {
                output.writeMessage(1, this.settingState);
            }
            if (this.label != null) {
                output.writeMessage(2, this.label);
            }
            if (this.actionButton != null) {
                output.writeMessage(3, this.actionButton);
            }
            if (this.infoNotice != null) {
                output.writeMessage(4, this.infoNotice);
            }
            if (this.noConsentRequired) {
                output.writeBool(5, this.noConsentRequired);
            }
            if (this.subSettingConfigs != null && this.subSettingConfigs.length > 0) {
                for (SubsettingOverviewConfig element : this.subSettingConfigs) {
                    if (element != null) {
                        output.writeMessage(6, element);
                    }
                }
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.settingState != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(1, this.settingState);
            }
            if (this.label != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(2, this.label);
            }
            if (this.actionButton != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(3, this.actionButton);
            }
            if (this.infoNotice != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(4, this.infoNotice);
            }
            if (this.noConsentRequired) {
                size += CodedOutputByteBufferNano.computeBoolSize(5, this.noConsentRequired);
            }
            if (this.subSettingConfigs != null && this.subSettingConfigs.length > 0) {
                for (SubsettingOverviewConfig element : this.subSettingConfigs) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(6, element);
                    }
                }
            }
            return size;
        }

        public SubsettingOverviewConfig mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        if (this.settingState == null) {
                            this.settingState = new SettingState();
                        }
                        input.readMessage(this.settingState);
                        continue;
                    case LogSource.GOOGLE_ANALYTICS /*18*/:
                        if (this.label == null) {
                            this.label = new TextResource();
                        }
                        input.readMessage(this.label);
                        continue;
                    case LogSource.ANDROID_CAMERA /*26*/:
                        if (this.actionButton == null) {
                            this.actionButton = new ButtonConfig();
                        }
                        input.readMessage(this.actionButton);
                        continue;
                    case LogSource.NOVA /*34*/:
                        if (this.infoNotice == null) {
                            this.infoNotice = new InfoNotice();
                        }
                        input.readMessage(this.infoNotice);
                        continue;
                    case LogSource.COPRESENCE /*40*/:
                        this.noConsentRequired = input.readBool();
                        continue;
                    case LogSource.HANGOUT_LOG_REQUEST /*50*/:
                        int i;
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 50);
                        if (this.subSettingConfigs == null) {
                            i = 0;
                        } else {
                            i = this.subSettingConfigs.length;
                        }
                        SubsettingOverviewConfig[] newArray = new SubsettingOverviewConfig[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.subSettingConfigs, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new SubsettingOverviewConfig();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i++;
                        }
                        newArray[i] = new SubsettingOverviewConfig();
                        input.readMessage(newArray[i]);
                        this.subSettingConfigs = newArray;
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

        public static SubsettingOverviewConfig parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (SubsettingOverviewConfig) MessageNano.mergeFrom(new SubsettingOverviewConfig(), data);
        }

        public static SubsettingOverviewConfig parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new SubsettingOverviewConfig().mergeFrom(input);
        }
    }

    public static SettingOverviewConfig[] emptyArray() {
        if (_emptyArray == null) {
            synchronized (InternalNano.LAZY_INIT_LOCK) {
                if (_emptyArray == null) {
                    _emptyArray = new SettingOverviewConfig[0];
                }
            }
        }
        return _emptyArray;
    }

    public SettingOverviewConfig() {
        clear();
    }

    public SettingOverviewConfig clear() {
        this.settingState = null;
        this.settingCaptions = SettingValueCaption.emptyArray();
        this.title = null;
        this.stateSummary = null;
        this.name = null;
        this.icon = null;
        this.description = null;
        this.actionButton = null;
        this.manageButton = null;
        this.additionalButtons = ButtonConfig.emptyArray();
        this.showDeviceSettingsAction = null;
        this.infoNotice = null;
        this.illustration = null;
        this.illustrationBackgroundColor = null;
        this.subsettingConfigs = SubsettingOverviewConfig.emptyArray();
        this.unknownFieldData = null;
        this.cachedSize = -1;
        return this;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof SettingOverviewConfig)) {
            return false;
        }
        SettingOverviewConfig other = (SettingOverviewConfig) o;
        if (this.settingState == null) {
            if (other.settingState != null) {
                return false;
            }
        } else if (!this.settingState.equals(other.settingState)) {
            return false;
        }
        if (!InternalNano.equals(this.settingCaptions, other.settingCaptions)) {
            return false;
        }
        if (this.title == null) {
            if (other.title != null) {
                return false;
            }
        } else if (!this.title.equals(other.title)) {
            return false;
        }
        if (this.stateSummary == null) {
            if (other.stateSummary != null) {
                return false;
            }
        } else if (!this.stateSummary.equals(other.stateSummary)) {
            return false;
        }
        if (this.name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!this.name.equals(other.name)) {
            return false;
        }
        if (this.icon == null) {
            if (other.icon != null) {
                return false;
            }
        } else if (!this.icon.equals(other.icon)) {
            return false;
        }
        if (this.description == null) {
            if (other.description != null) {
                return false;
            }
        } else if (!this.description.equals(other.description)) {
            return false;
        }
        if (this.actionButton == null) {
            if (other.actionButton != null) {
                return false;
            }
        } else if (!this.actionButton.equals(other.actionButton)) {
            return false;
        }
        if (this.manageButton == null) {
            if (other.manageButton != null) {
                return false;
            }
        } else if (!this.manageButton.equals(other.manageButton)) {
            return false;
        }
        if (!InternalNano.equals(this.additionalButtons, other.additionalButtons)) {
            return false;
        }
        if (this.showDeviceSettingsAction == null) {
            if (other.showDeviceSettingsAction != null) {
                return false;
            }
        } else if (!this.showDeviceSettingsAction.equals(other.showDeviceSettingsAction)) {
            return false;
        }
        if (this.infoNotice == null) {
            if (other.infoNotice != null) {
                return false;
            }
        } else if (!this.infoNotice.equals(other.infoNotice)) {
            return false;
        }
        if (this.illustration == null) {
            if (other.illustration != null) {
                return false;
            }
        } else if (!this.illustration.equals(other.illustration)) {
            return false;
        }
        if (this.illustrationBackgroundColor == null) {
            if (other.illustrationBackgroundColor != null) {
                return false;
            }
        } else if (!this.illustrationBackgroundColor.equals(other.illustrationBackgroundColor)) {
            return false;
        }
        if (!InternalNano.equals(this.subsettingConfigs, other.subsettingConfigs)) {
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
        int hashCode = (((((((((((((((((((((((((((((((getClass().getName().hashCode() + 527) * 31) + (this.settingState == null ? 0 : this.settingState.hashCode())) * 31) + InternalNano.hashCode(this.settingCaptions)) * 31) + (this.title == null ? 0 : this.title.hashCode())) * 31) + (this.stateSummary == null ? 0 : this.stateSummary.hashCode())) * 31) + (this.name == null ? 0 : this.name.hashCode())) * 31) + (this.icon == null ? 0 : this.icon.hashCode())) * 31) + (this.description == null ? 0 : this.description.hashCode())) * 31) + (this.actionButton == null ? 0 : this.actionButton.hashCode())) * 31) + (this.manageButton == null ? 0 : this.manageButton.hashCode())) * 31) + InternalNano.hashCode(this.additionalButtons)) * 31) + (this.showDeviceSettingsAction == null ? 0 : this.showDeviceSettingsAction.hashCode())) * 31) + (this.infoNotice == null ? 0 : this.infoNotice.hashCode())) * 31) + (this.illustration == null ? 0 : this.illustration.hashCode())) * 31) + (this.illustrationBackgroundColor == null ? 0 : this.illustrationBackgroundColor.hashCode())) * 31) + InternalNano.hashCode(this.subsettingConfigs)) * 31;
        if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
            i = this.unknownFieldData.hashCode();
        }
        return hashCode + i;
    }

    public void writeTo(CodedOutputByteBufferNano output) throws IOException {
        if (this.settingState != null) {
            output.writeMessage(1, this.settingState);
        }
        if (this.settingCaptions != null && this.settingCaptions.length > 0) {
            for (SettingValueCaption element : this.settingCaptions) {
                if (element != null) {
                    output.writeMessage(2, element);
                }
            }
        }
        if (this.title != null) {
            output.writeMessage(3, this.title);
        }
        if (this.name != null) {
            output.writeMessage(4, this.name);
        }
        if (this.icon != null) {
            output.writeMessage(5, this.icon);
        }
        if (this.description != null) {
            output.writeMessage(6, this.description);
        }
        if (this.actionButton != null) {
            output.writeMessage(7, this.actionButton);
        }
        if (this.additionalButtons != null && this.additionalButtons.length > 0) {
            for (ButtonConfig element2 : this.additionalButtons) {
                if (element2 != null) {
                    output.writeMessage(8, element2);
                }
            }
        }
        if (this.illustration != null) {
            output.writeMessage(9, this.illustration);
        }
        if (this.infoNotice != null) {
            output.writeMessage(12, this.infoNotice);
        }
        if (this.manageButton != null) {
            output.writeMessage(13, this.manageButton);
        }
        if (this.showDeviceSettingsAction != null) {
            output.writeMessage(15, this.showDeviceSettingsAction);
        }
        if (this.subsettingConfigs != null && this.subsettingConfigs.length > 0) {
            for (SubsettingOverviewConfig element3 : this.subsettingConfigs) {
                if (element3 != null) {
                    output.writeMessage(16, element3);
                }
            }
        }
        if (this.stateSummary != null) {
            output.writeMessage(17, this.stateSummary);
        }
        if (this.illustrationBackgroundColor != null) {
            output.writeMessage(18, this.illustrationBackgroundColor);
        }
        super.writeTo(output);
    }

    protected int computeSerializedSize() {
        int size = super.computeSerializedSize();
        if (this.settingState != null) {
            size += CodedOutputByteBufferNano.computeMessageSize(1, this.settingState);
        }
        if (this.settingCaptions != null && this.settingCaptions.length > 0) {
            for (SettingValueCaption element : this.settingCaptions) {
                if (element != null) {
                    size += CodedOutputByteBufferNano.computeMessageSize(2, element);
                }
            }
        }
        if (this.title != null) {
            size += CodedOutputByteBufferNano.computeMessageSize(3, this.title);
        }
        if (this.name != null) {
            size += CodedOutputByteBufferNano.computeMessageSize(4, this.name);
        }
        if (this.icon != null) {
            size += CodedOutputByteBufferNano.computeMessageSize(5, this.icon);
        }
        if (this.description != null) {
            size += CodedOutputByteBufferNano.computeMessageSize(6, this.description);
        }
        if (this.actionButton != null) {
            size += CodedOutputByteBufferNano.computeMessageSize(7, this.actionButton);
        }
        if (this.additionalButtons != null && this.additionalButtons.length > 0) {
            for (ButtonConfig element2 : this.additionalButtons) {
                if (element2 != null) {
                    size += CodedOutputByteBufferNano.computeMessageSize(8, element2);
                }
            }
        }
        if (this.illustration != null) {
            size += CodedOutputByteBufferNano.computeMessageSize(9, this.illustration);
        }
        if (this.infoNotice != null) {
            size += CodedOutputByteBufferNano.computeMessageSize(12, this.infoNotice);
        }
        if (this.manageButton != null) {
            size += CodedOutputByteBufferNano.computeMessageSize(13, this.manageButton);
        }
        if (this.showDeviceSettingsAction != null) {
            size += CodedOutputByteBufferNano.computeMessageSize(15, this.showDeviceSettingsAction);
        }
        if (this.subsettingConfigs != null && this.subsettingConfigs.length > 0) {
            for (SubsettingOverviewConfig element3 : this.subsettingConfigs) {
                if (element3 != null) {
                    size += CodedOutputByteBufferNano.computeMessageSize(16, element3);
                }
            }
        }
        if (this.stateSummary != null) {
            size += CodedOutputByteBufferNano.computeMessageSize(17, this.stateSummary);
        }
        if (this.illustrationBackgroundColor != null) {
            return size + CodedOutputByteBufferNano.computeMessageSize(18, this.illustrationBackgroundColor);
        }
        return size;
    }

    public SettingOverviewConfig mergeFrom(CodedInputByteBufferNano input) throws IOException {
        while (true) {
            int tag = input.readTag();
            int arrayLength;
            int i;
            switch (tag) {
                case Action.UNKNOWN /*0*/:
                    break;
                case Type.TAP_ABOUT /*10*/:
                    if (this.settingState == null) {
                        this.settingState = new SettingState();
                    }
                    input.readMessage(this.settingState);
                    continue;
                case LogSource.GOOGLE_ANALYTICS /*18*/:
                    arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 18);
                    if (this.settingCaptions == null) {
                        i = 0;
                    } else {
                        i = this.settingCaptions.length;
                    }
                    SettingValueCaption[] newArray = new SettingValueCaption[(i + arrayLength)];
                    if (i != 0) {
                        System.arraycopy(this.settingCaptions, 0, newArray, 0, i);
                    }
                    while (i < newArray.length - 1) {
                        newArray[i] = new SettingValueCaption();
                        input.readMessage(newArray[i]);
                        input.readTag();
                        i++;
                    }
                    newArray[i] = new SettingValueCaption();
                    input.readMessage(newArray[i]);
                    this.settingCaptions = newArray;
                    continue;
                case LogSource.ANDROID_CAMERA /*26*/:
                    if (this.title == null) {
                        this.title = new TextResource();
                    }
                    input.readMessage(this.title);
                    continue;
                case LogSource.NOVA /*34*/:
                    if (this.name == null) {
                        this.name = new TextResource();
                    }
                    input.readMessage(this.name);
                    continue;
                case LogSource.PHOTOS /*42*/:
                    if (this.icon == null) {
                        this.icon = new ImageResource();
                    }
                    input.readMessage(this.icon);
                    continue;
                case LogSource.HANGOUT_LOG_REQUEST /*50*/:
                    if (this.description == null) {
                        this.description = new TextResource();
                    }
                    input.readMessage(this.description);
                    continue;
                case LogSource.SLIDES /*58*/:
                    if (this.actionButton == null) {
                        this.actionButton = new ButtonConfig();
                    }
                    input.readMessage(this.actionButton);
                    continue;
                case LogSource.WIFI_ASSISTANT /*66*/:
                    arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 66);
                    if (this.additionalButtons == null) {
                        i = 0;
                    } else {
                        i = this.additionalButtons.length;
                    }
                    ButtonConfig[] newArray2 = new ButtonConfig[(i + arrayLength)];
                    if (i != 0) {
                        System.arraycopy(this.additionalButtons, 0, newArray2, 0, i);
                    }
                    while (i < newArray2.length - 1) {
                        newArray2[i] = new ButtonConfig();
                        input.readMessage(newArray2[i]);
                        input.readTag();
                        i++;
                    }
                    newArray2[i] = new ButtonConfig();
                    input.readMessage(newArray2[i]);
                    this.additionalButtons = newArray2;
                    continue;
                case LogSource.MOVIES /*74*/:
                    if (this.illustration == null) {
                        this.illustration = new ImageResource();
                    }
                    input.readMessage(this.illustration);
                    continue;
                case LogSource.TACHYON_LOG_REQUEST /*98*/:
                    if (this.infoNotice == null) {
                        this.infoNotice = new InfoNotice();
                    }
                    input.readMessage(this.infoNotice);
                    continue;
                case LogSource.ADSHIELD /*106*/:
                    if (this.manageButton == null) {
                        this.manageButton = new ButtonConfig();
                    }
                    input.readMessage(this.manageButton);
                    continue;
                case LogSource.ANDROID_SNET_GCORE /*122*/:
                    if (this.showDeviceSettingsAction == null) {
                        this.showDeviceSettingsAction = new ShowDeviceSettingsAction();
                    }
                    input.readMessage(this.showDeviceSettingsAction);
                    continue;
                case LogSource.CHROMECAST_APP_LOG /*130*/:
                    arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, LogSource.CHROMECAST_APP_LOG);
                    if (this.subsettingConfigs == null) {
                        i = 0;
                    } else {
                        i = this.subsettingConfigs.length;
                    }
                    SubsettingOverviewConfig[] newArray3 = new SubsettingOverviewConfig[(i + arrayLength)];
                    if (i != 0) {
                        System.arraycopy(this.subsettingConfigs, 0, newArray3, 0, i);
                    }
                    while (i < newArray3.length - 1) {
                        newArray3[i] = new SubsettingOverviewConfig();
                        input.readMessage(newArray3[i]);
                        input.readTag();
                        i++;
                    }
                    newArray3[i] = new SubsettingOverviewConfig();
                    input.readMessage(newArray3[i]);
                    this.subsettingConfigs = newArray3;
                    continue;
                case LogSource.PANCETTA_MOBILE_HOST /*138*/:
                    if (this.stateSummary == null) {
                        this.stateSummary = new TextResource();
                    }
                    input.readMessage(this.stateSummary);
                    continue;
                case 146:
                    if (this.illustrationBackgroundColor == null) {
                        this.illustrationBackgroundColor = new TextResource();
                    }
                    input.readMessage(this.illustrationBackgroundColor);
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

    public static SettingOverviewConfig parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
        return (SettingOverviewConfig) MessageNano.mergeFrom(new SettingOverviewConfig(), data);
    }

    public static SettingOverviewConfig parseFrom(CodedInputByteBufferNano input) throws IOException {
        return new SettingOverviewConfig().mergeFrom(input);
    }
}
