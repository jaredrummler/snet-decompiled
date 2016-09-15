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
import java.util.Arrays;

public final class FetchConsentConfigResponse extends ExtendableMessageNano<FetchConsentConfigResponse> {
    private static volatile FetchConsentConfigResponse[] _emptyArray;
    public ApiResponseHeader apiHeader;
    public byte[] consentSession;
    public TextResource footer;
    public TextResource identity;
    public ImageResource illustration;
    public TextResource illustrationBackgroundColor;
    public ImageResource illustrationBackgroundImage;
    public InfoNotice infoNotice;
    public ButtonConfig negativeButton;
    public TextResource[] paragraphs;
    public ButtonConfig positiveButton;
    public TextResource productStatement;
    public SettingUiConfig[] settingConfigs;
    public TextResource title;
    public ViewHeader viewHeader;

    public static FetchConsentConfigResponse[] emptyArray() {
        if (_emptyArray == null) {
            synchronized (InternalNano.LAZY_INIT_LOCK) {
                if (_emptyArray == null) {
                    _emptyArray = new FetchConsentConfigResponse[0];
                }
            }
        }
        return _emptyArray;
    }

    public FetchConsentConfigResponse() {
        clear();
    }

    public FetchConsentConfigResponse clear() {
        this.apiHeader = null;
        this.consentSession = WireFormatNano.EMPTY_BYTES;
        this.infoNotice = null;
        this.viewHeader = null;
        this.illustration = null;
        this.illustrationBackgroundImage = null;
        this.illustrationBackgroundColor = null;
        this.title = null;
        this.productStatement = null;
        this.identity = null;
        this.settingConfigs = SettingUiConfig.emptyArray();
        this.paragraphs = TextResource.emptyArray();
        this.footer = null;
        this.positiveButton = null;
        this.negativeButton = null;
        this.unknownFieldData = null;
        this.cachedSize = -1;
        return this;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof FetchConsentConfigResponse)) {
            return false;
        }
        FetchConsentConfigResponse other = (FetchConsentConfigResponse) o;
        if (this.apiHeader == null) {
            if (other.apiHeader != null) {
                return false;
            }
        } else if (!this.apiHeader.equals(other.apiHeader)) {
            return false;
        }
        if (!Arrays.equals(this.consentSession, other.consentSession)) {
            return false;
        }
        if (this.infoNotice == null) {
            if (other.infoNotice != null) {
                return false;
            }
        } else if (!this.infoNotice.equals(other.infoNotice)) {
            return false;
        }
        if (this.viewHeader == null) {
            if (other.viewHeader != null) {
                return false;
            }
        } else if (!this.viewHeader.equals(other.viewHeader)) {
            return false;
        }
        if (this.illustration == null) {
            if (other.illustration != null) {
                return false;
            }
        } else if (!this.illustration.equals(other.illustration)) {
            return false;
        }
        if (this.illustrationBackgroundImage == null) {
            if (other.illustrationBackgroundImage != null) {
                return false;
            }
        } else if (!this.illustrationBackgroundImage.equals(other.illustrationBackgroundImage)) {
            return false;
        }
        if (this.illustrationBackgroundColor == null) {
            if (other.illustrationBackgroundColor != null) {
                return false;
            }
        } else if (!this.illustrationBackgroundColor.equals(other.illustrationBackgroundColor)) {
            return false;
        }
        if (this.title == null) {
            if (other.title != null) {
                return false;
            }
        } else if (!this.title.equals(other.title)) {
            return false;
        }
        if (this.productStatement == null) {
            if (other.productStatement != null) {
                return false;
            }
        } else if (!this.productStatement.equals(other.productStatement)) {
            return false;
        }
        if (this.identity == null) {
            if (other.identity != null) {
                return false;
            }
        } else if (!this.identity.equals(other.identity)) {
            return false;
        }
        if (!InternalNano.equals(this.settingConfigs, other.settingConfigs) || !InternalNano.equals(this.paragraphs, other.paragraphs)) {
            return false;
        }
        if (this.footer == null) {
            if (other.footer != null) {
                return false;
            }
        } else if (!this.footer.equals(other.footer)) {
            return false;
        }
        if (this.positiveButton == null) {
            if (other.positiveButton != null) {
                return false;
            }
        } else if (!this.positiveButton.equals(other.positiveButton)) {
            return false;
        }
        if (this.negativeButton == null) {
            if (other.negativeButton != null) {
                return false;
            }
        } else if (!this.negativeButton.equals(other.negativeButton)) {
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
        int hashCode = (((((((((((((((((((((((((((((((getClass().getName().hashCode() + 527) * 31) + (this.apiHeader == null ? 0 : this.apiHeader.hashCode())) * 31) + Arrays.hashCode(this.consentSession)) * 31) + (this.infoNotice == null ? 0 : this.infoNotice.hashCode())) * 31) + (this.viewHeader == null ? 0 : this.viewHeader.hashCode())) * 31) + (this.illustration == null ? 0 : this.illustration.hashCode())) * 31) + (this.illustrationBackgroundImage == null ? 0 : this.illustrationBackgroundImage.hashCode())) * 31) + (this.illustrationBackgroundColor == null ? 0 : this.illustrationBackgroundColor.hashCode())) * 31) + (this.title == null ? 0 : this.title.hashCode())) * 31) + (this.productStatement == null ? 0 : this.productStatement.hashCode())) * 31) + (this.identity == null ? 0 : this.identity.hashCode())) * 31) + InternalNano.hashCode(this.settingConfigs)) * 31) + InternalNano.hashCode(this.paragraphs)) * 31) + (this.footer == null ? 0 : this.footer.hashCode())) * 31) + (this.positiveButton == null ? 0 : this.positiveButton.hashCode())) * 31) + (this.negativeButton == null ? 0 : this.negativeButton.hashCode())) * 31;
        if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
            i = this.unknownFieldData.hashCode();
        }
        return hashCode + i;
    }

    public void writeTo(CodedOutputByteBufferNano output) throws IOException {
        if (!Arrays.equals(this.consentSession, WireFormatNano.EMPTY_BYTES)) {
            output.writeBytes(1, this.consentSession);
        }
        if (this.viewHeader != null) {
            output.writeMessage(2, this.viewHeader);
        }
        if (this.illustration != null) {
            output.writeMessage(4, this.illustration);
        }
        if (this.title != null) {
            output.writeMessage(5, this.title);
        }
        if (this.productStatement != null) {
            output.writeMessage(6, this.productStatement);
        }
        if (this.identity != null) {
            output.writeMessage(7, this.identity);
        }
        if (this.settingConfigs != null && this.settingConfigs.length > 0) {
            for (SettingUiConfig element : this.settingConfigs) {
                if (element != null) {
                    output.writeMessage(8, element);
                }
            }
        }
        if (this.paragraphs != null && this.paragraphs.length > 0) {
            for (TextResource element2 : this.paragraphs) {
                if (element2 != null) {
                    output.writeMessage(9, element2);
                }
            }
        }
        if (this.footer != null) {
            output.writeMessage(10, this.footer);
        }
        if (this.positiveButton != null) {
            output.writeMessage(11, this.positiveButton);
        }
        if (this.negativeButton != null) {
            output.writeMessage(12, this.negativeButton);
        }
        if (this.illustrationBackgroundImage != null) {
            output.writeMessage(14, this.illustrationBackgroundImage);
        }
        if (this.apiHeader != null) {
            output.writeMessage(15, this.apiHeader);
        }
        if (this.infoNotice != null) {
            output.writeMessage(16, this.infoNotice);
        }
        if (this.illustrationBackgroundColor != null) {
            output.writeMessage(17, this.illustrationBackgroundColor);
        }
        super.writeTo(output);
    }

    protected int computeSerializedSize() {
        int size = super.computeSerializedSize();
        if (!Arrays.equals(this.consentSession, WireFormatNano.EMPTY_BYTES)) {
            size += CodedOutputByteBufferNano.computeBytesSize(1, this.consentSession);
        }
        if (this.viewHeader != null) {
            size += CodedOutputByteBufferNano.computeMessageSize(2, this.viewHeader);
        }
        if (this.illustration != null) {
            size += CodedOutputByteBufferNano.computeMessageSize(4, this.illustration);
        }
        if (this.title != null) {
            size += CodedOutputByteBufferNano.computeMessageSize(5, this.title);
        }
        if (this.productStatement != null) {
            size += CodedOutputByteBufferNano.computeMessageSize(6, this.productStatement);
        }
        if (this.identity != null) {
            size += CodedOutputByteBufferNano.computeMessageSize(7, this.identity);
        }
        if (this.settingConfigs != null && this.settingConfigs.length > 0) {
            for (SettingUiConfig element : this.settingConfigs) {
                if (element != null) {
                    size += CodedOutputByteBufferNano.computeMessageSize(8, element);
                }
            }
        }
        if (this.paragraphs != null && this.paragraphs.length > 0) {
            for (TextResource element2 : this.paragraphs) {
                if (element2 != null) {
                    size += CodedOutputByteBufferNano.computeMessageSize(9, element2);
                }
            }
        }
        if (this.footer != null) {
            size += CodedOutputByteBufferNano.computeMessageSize(10, this.footer);
        }
        if (this.positiveButton != null) {
            size += CodedOutputByteBufferNano.computeMessageSize(11, this.positiveButton);
        }
        if (this.negativeButton != null) {
            size += CodedOutputByteBufferNano.computeMessageSize(12, this.negativeButton);
        }
        if (this.illustrationBackgroundImage != null) {
            size += CodedOutputByteBufferNano.computeMessageSize(14, this.illustrationBackgroundImage);
        }
        if (this.apiHeader != null) {
            size += CodedOutputByteBufferNano.computeMessageSize(15, this.apiHeader);
        }
        if (this.infoNotice != null) {
            size += CodedOutputByteBufferNano.computeMessageSize(16, this.infoNotice);
        }
        if (this.illustrationBackgroundColor != null) {
            return size + CodedOutputByteBufferNano.computeMessageSize(17, this.illustrationBackgroundColor);
        }
        return size;
    }

    public FetchConsentConfigResponse mergeFrom(CodedInputByteBufferNano input) throws IOException {
        while (true) {
            int tag = input.readTag();
            int arrayLength;
            int i;
            switch (tag) {
                case Action.UNKNOWN /*0*/:
                    break;
                case Type.TAP_ABOUT /*10*/:
                    this.consentSession = input.readBytes();
                    continue;
                case LogSource.GOOGLE_ANALYTICS /*18*/:
                    if (this.viewHeader == null) {
                        this.viewHeader = new ViewHeader();
                    }
                    input.readMessage(this.viewHeader);
                    continue;
                case LogSource.NOVA /*34*/:
                    if (this.illustration == null) {
                        this.illustration = new ImageResource();
                    }
                    input.readMessage(this.illustration);
                    continue;
                case LogSource.PHOTOS /*42*/:
                    if (this.title == null) {
                        this.title = new TextResource();
                    }
                    input.readMessage(this.title);
                    continue;
                case LogSource.HANGOUT_LOG_REQUEST /*50*/:
                    if (this.productStatement == null) {
                        this.productStatement = new TextResource();
                    }
                    input.readMessage(this.productStatement);
                    continue;
                case LogSource.SLIDES /*58*/:
                    if (this.identity == null) {
                        this.identity = new TextResource();
                    }
                    input.readMessage(this.identity);
                    continue;
                case LogSource.WIFI_ASSISTANT /*66*/:
                    arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 66);
                    if (this.settingConfigs == null) {
                        i = 0;
                    } else {
                        i = this.settingConfigs.length;
                    }
                    SettingUiConfig[] newArray = new SettingUiConfig[(i + arrayLength)];
                    if (i != 0) {
                        System.arraycopy(this.settingConfigs, 0, newArray, 0, i);
                    }
                    while (i < newArray.length - 1) {
                        newArray[i] = new SettingUiConfig();
                        input.readMessage(newArray[i]);
                        input.readTag();
                        i++;
                    }
                    newArray[i] = new SettingUiConfig();
                    input.readMessage(newArray[i]);
                    this.settingConfigs = newArray;
                    continue;
                case LogSource.MOVIES /*74*/:
                    arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 74);
                    if (this.paragraphs == null) {
                        i = 0;
                    } else {
                        i = this.paragraphs.length;
                    }
                    TextResource[] newArray2 = new TextResource[(i + arrayLength)];
                    if (i != 0) {
                        System.arraycopy(this.paragraphs, 0, newArray2, 0, i);
                    }
                    while (i < newArray2.length - 1) {
                        newArray2[i] = new TextResource();
                        input.readMessage(newArray2[i]);
                        input.readTag();
                        i++;
                    }
                    newArray2[i] = new TextResource();
                    input.readMessage(newArray2[i]);
                    this.paragraphs = newArray2;
                    continue;
                case LogSource.GOOGLE_EXPRESS /*82*/:
                    if (this.footer == null) {
                        this.footer = new TextResource();
                    }
                    input.readMessage(this.footer);
                    continue;
                case LogSource.TRON_COUNTERS /*90*/:
                    if (this.positiveButton == null) {
                        this.positiveButton = new ButtonConfig();
                    }
                    input.readMessage(this.positiveButton);
                    continue;
                case LogSource.TACHYON_LOG_REQUEST /*98*/:
                    if (this.negativeButton == null) {
                        this.negativeButton = new ButtonConfig();
                    }
                    input.readMessage(this.negativeButton);
                    continue;
                case LogSource.TRANSOM /*114*/:
                    if (this.illustrationBackgroundImage == null) {
                        this.illustrationBackgroundImage = new ImageResource();
                    }
                    input.readMessage(this.illustrationBackgroundImage);
                    continue;
                case LogSource.ANDROID_SNET_GCORE /*122*/:
                    if (this.apiHeader == null) {
                        this.apiHeader = new ApiResponseHeader();
                    }
                    input.readMessage(this.apiHeader);
                    continue;
                case LogSource.CHROMECAST_APP_LOG /*130*/:
                    if (this.infoNotice == null) {
                        this.infoNotice = new InfoNotice();
                    }
                    input.readMessage(this.infoNotice);
                    continue;
                case LogSource.PANCETTA_MOBILE_HOST /*138*/:
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

    public static FetchConsentConfigResponse parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
        return (FetchConsentConfigResponse) MessageNano.mergeFrom(new FetchConsentConfigResponse(), data);
    }

    public static FetchConsentConfigResponse parseFrom(CodedInputByteBufferNano input) throws IOException {
        return new FetchConsentConfigResponse().mergeFrom(input);
    }
}
