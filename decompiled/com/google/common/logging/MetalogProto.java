package com.google.common.logging;

import com.google.android.gms.lint.BuildConfig;
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

public interface MetalogProto {

    public static final class MetalogCounterDimensions extends ExtendableMessageNano<MetalogCounterDimensions> {
        private static volatile MetalogCounterDimensions[] _emptyArray;
        public boolean isCharging;
        public boolean isConnectionUnmetered;
        public int logSource;
        public String logSourceName;
        public int qosTier;

        public static MetalogCounterDimensions[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new MetalogCounterDimensions[0];
                    }
                }
            }
            return _emptyArray;
        }

        public MetalogCounterDimensions() {
            clear();
        }

        public MetalogCounterDimensions clear() {
            this.logSourceName = BuildConfig.VERSION_NAME;
            this.logSource = -1;
            this.isConnectionUnmetered = false;
            this.isCharging = false;
            this.qosTier = 0;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof MetalogCounterDimensions)) {
                return false;
            }
            MetalogCounterDimensions other = (MetalogCounterDimensions) o;
            if (this.logSourceName == null) {
                if (other.logSourceName != null) {
                    return false;
                }
            } else if (!this.logSourceName.equals(other.logSourceName)) {
                return false;
            }
            if (this.logSource != other.logSource || this.isConnectionUnmetered != other.isConnectionUnmetered || this.isCharging != other.isCharging || this.qosTier != other.qosTier) {
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
            int hashCode = (((((getClass().getName().hashCode() + 527) * 31) + (this.logSourceName == null ? 0 : this.logSourceName.hashCode())) * 31) + this.logSource) * 31;
            if (this.isConnectionUnmetered) {
                i = 1231;
            } else {
                i = 1237;
            }
            i = (hashCode + i) * 31;
            if (!this.isCharging) {
                i2 = 1237;
            }
            i = (((i + i2) * 31) + this.qosTier) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i3 = this.unknownFieldData.hashCode();
            }
            return i + i3;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (!this.logSourceName.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(1, this.logSourceName);
            }
            if (this.logSource != -1) {
                output.writeInt32(2, this.logSource);
            }
            if (this.isConnectionUnmetered) {
                output.writeBool(3, this.isConnectionUnmetered);
            }
            if (this.isCharging) {
                output.writeBool(4, this.isCharging);
            }
            if (this.qosTier != 0) {
                output.writeInt32(5, this.qosTier);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (!this.logSourceName.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.logSourceName);
            }
            if (this.logSource != -1) {
                size += CodedOutputByteBufferNano.computeInt32Size(2, this.logSource);
            }
            if (this.isConnectionUnmetered) {
                size += CodedOutputByteBufferNano.computeBoolSize(3, this.isConnectionUnmetered);
            }
            if (this.isCharging) {
                size += CodedOutputByteBufferNano.computeBoolSize(4, this.isCharging);
            }
            if (this.qosTier != 0) {
                return size + CodedOutputByteBufferNano.computeInt32Size(5, this.qosTier);
            }
            return size;
        }

        public MetalogCounterDimensions mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                int value;
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        this.logSourceName = input.readString();
                        continue;
                    case LogSource.GMS_CORE_PEOPLE /*16*/:
                        value = input.readInt32();
                        switch (value) {
                            case LogSource.UNKNOWN /*-1*/:
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
                            case Type.LAUNCH_SETTINGS /*14*/:
                            case Type.OVEN_FRESH /*15*/:
                            case LogSource.GMS_CORE_PEOPLE /*16*/:
                            case LogSource.LE /*17*/:
                            case LogSource.GOOGLE_ANALYTICS /*18*/:
                            case LogSource.LB_D /*19*/:
                            case LogSource.ANDROID_GSA /*20*/:
                            case LogSource.LB_T /*21*/:
                            case LogSource.PERSONAL_LOGGER /*22*/:
                            case LogSource.GMS_CORE_WALLET_MERCHANT_ERROR /*23*/:
                            case LogSource.LB_C /*24*/:
                            case LogSource.ANDROID_AUTH /*25*/:
                            case LogSource.ANDROID_CAMERA /*26*/:
                            case LogSource.CW /*27*/:
                            case LogSource.GEL /*28*/:
                            case LogSource.DNA_PROBER /*29*/:
                            case LogSource.UDR /*30*/:
                            case LogSource.GMS_CORE_WALLET /*31*/:
                            case LogSource.SOCIAL /*32*/:
                            case LogSource.INSTORE_WALLET /*33*/:
                            case LogSource.NOVA /*34*/:
                            case LogSource.LB_CA /*35*/:
                            case LogSource.LATIN_IME /*36*/:
                            case LogSource.PERSONAL_BROWSER_LOGGER /*37*/:
                            case LogSource.NEWS_WEATHER /*38*/:
                            case LogSource.HANGOUT /*39*/:
                            case LogSource.COPRESENCE /*40*/:
                            case LogSource.SOCIAL_AFFINITY /*41*/:
                            case LogSource.PHOTOS /*42*/:
                            case LogSource.GCM /*43*/:
                            case LogSource.GOKART /*44*/:
                            case LogSource.FINDR /*45*/:
                            case LogSource.ANDROID_MESSAGING /*46*/:
                            case LogSource.SOCIAL_WEB /*47*/:
                            case LogSource.BACKDROP /*48*/:
                            case LogSource.TELEMATICS /*49*/:
                            case LogSource.HANGOUT_LOG_REQUEST /*50*/:
                            case LogSource.GVC_HARVESTER /*51*/:
                            case LogSource.LB_IA /*52*/:
                            case LogSource.CAR /*53*/:
                            case LogSource.PIXEL_PERFECT /*54*/:
                            case LogSource.DRIVE /*55*/:
                            case LogSource.DOCS /*56*/:
                            case LogSource.SHEETS /*57*/:
                            case LogSource.SLIDES /*58*/:
                            case LogSource.IME /*59*/:
                            case LogSource.WARP /*60*/:
                            case LogSource.NFC_PROGRAMMER /*61*/:
                            case LogSource.NETSTATS /*62*/:
                            case LogSource.NEWSSTAND /*63*/:
                            case LogSource.KIDS_COMMUNICATOR /*64*/:
                            case LogSource.WEB_STORE /*65*/:
                            case LogSource.WIFI_ASSISTANT /*66*/:
                            case LogSource.CAST_SENDER_SDK /*67*/:
                            case LogSource.CRONET_SOCIAL /*68*/:
                            case LogSource.PHENOTYPE /*69*/:
                            case LogSource.PHENOTYPE_COUNTERS /*70*/:
                            case LogSource.CHROME_INFRA /*71*/:
                            case LogSource.JUSTSPEAK /*72*/:
                            case LogSource.PERF_PROFILE /*73*/:
                            case LogSource.MOVIES /*74*/:
                            case LogSource.KATNISS /*75*/:
                            case LogSource.SOCIAL_APPINVITE /*76*/:
                            case LogSource.GMM_COUNTERS /*77*/:
                            case LogSource.BOND_ONEGOOGLE /*78*/:
                            case LogSource.MAPS_API /*79*/:
                            case LogSource.CRONET_ANDROID_GSA /*80*/:
                            case LogSource.GOOGLE_FIT_WEARABLE /*81*/:
                            case LogSource.GOOGLE_EXPRESS /*82*/:
                            case LogSource.SENSE /*83*/:
                            case LogSource.ANDROID_BACKUP /*84*/:
                            case LogSource.VR /*85*/:
                            case LogSource.IME_COUNTERS /*86*/:
                            case LogSource.SETUP_WIZARD /*87*/:
                            case LogSource.EMERGENCY_ASSIST /*88*/:
                            case LogSource.TRON /*89*/:
                            case LogSource.TRON_COUNTERS /*90*/:
                            case LogSource.BATTERY_STATS /*91*/:
                            case LogSource.DISK_STATS /*92*/:
                            case LogSource.PROC_STATS /*93*/:
                            case LogSource.TAP_AND_PAY_GCORE /*94*/:
                            case LogSource.A11YLOGGER /*95*/:
                            case LogSource.GCM_COUNTERS /*96*/:
                            case LogSource.PLACES_NO_GLS_CONSENT /*97*/:
                            case LogSource.TACHYON_LOG_REQUEST /*98*/:
                            case LogSource.TACHYON_COUNTERS /*99*/:
                            case LogSource.VISION /*100*/:
                            case LogSource.SOCIAL_USER_LOCATION /*101*/:
                            case LogSource.LAUNCHPAD_TOYS /*102*/:
                            case LogSource.METALOG_COUNTERS /*103*/:
                            case LogSource.MOBILESDK_CLIENT /*104*/:
                            case LogSource.ANDROID_VERIFY_APPS /*105*/:
                            case LogSource.ADSHIELD /*106*/:
                            case LogSource.GRAPHICS_STATS /*107*/:
                            case LogSource.SHERLOG /*108*/:
                            case LogSource.LE_ULR_COUNTERS /*109*/:
                            case LogSource.GMM_UE3 /*110*/:
                            case LogSource.CALENDAR /*111*/:
                            case LogSource.ENDER /*112*/:
                            case LogSource.FAMILY_COMPASS /*113*/:
                            case LogSource.TRANSOM /*114*/:
                            case LogSource.TRANSOM_COUNTERS /*115*/:
                            case LogSource.LB_AS /*116*/:
                            case LogSource.LB_CFG /*117*/:
                            case LogSource.IOS_GSA /*118*/:
                            case LogSource.TAP_AND_PAY_APP /*119*/:
                            case LogSource.FLYDROID /*120*/:
                            case LogSource.CPANEL_APP /*121*/:
                            case LogSource.ANDROID_SNET_GCORE /*122*/:
                            case LogSource.ANDROID_SNET_IDLE /*123*/:
                            case LogSource.ANDROID_SNET_JAR /*124*/:
                            case LogSource.CONTEXT_MANAGER /*125*/:
                            case LogSource.CLASSROOM /*126*/:
                            case LogSource.TAILORMADE /*127*/:
                            case LogSource.KEEP /*128*/:
                            case LogSource.GMM_BRIIM_COUNTERS /*129*/:
                            case LogSource.CHROMECAST_APP_LOG /*130*/:
                            case LogSource.DROP_BOX /*131*/:
                            case LogSource.WORK_STORE /*132*/:
                            case LogSource.ADWORDS_MOBILE /*133*/:
                            case LogSource.LEANBACK_EVENT /*134*/:
                            case LogSource.ANDROID_GMAIL /*135*/:
                            case LogSource.SAMPLE_SHM /*136*/:
                            case LogSource.ICORE /*137*/:
                            case LogSource.PANCETTA_MOBILE_HOST /*138*/:
                            case LogSource.PANCETTA_MOBILE_HOST_COUNTERS /*139*/:
                            case LogSource.GPLUS_ANDROID_SYSTEM_HEALTH /*140*/:
                                this.logSource = value;
                                break;
                            default:
                                continue;
                        }
                    case LogSource.LB_C /*24*/:
                        this.isConnectionUnmetered = input.readBool();
                        continue;
                    case LogSource.SOCIAL /*32*/:
                        this.isCharging = input.readBool();
                        continue;
                    case LogSource.COPRESENCE /*40*/:
                        value = input.readInt32();
                        switch (value) {
                            case Action.UNKNOWN /*0*/:
                            case TimeSelection.Type.TEMPORARY /*1*/:
                            case TimeSelection.Type.INDEFINITELY /*2*/:
                            case TimeSelection.Type.CUSTOM /*3*/:
                            case OvenFreshResult.NO_ACCOUNTS /*4*/:
                                this.qosTier = value;
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

        public static MetalogCounterDimensions parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (MetalogCounterDimensions) MessageNano.mergeFrom(new MetalogCounterDimensions(), data);
        }

        public static MetalogCounterDimensions parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new MetalogCounterDimensions().mergeFrom(input);
        }
    }

    public static final class MetalogLog extends ExtendableMessageNano<MetalogLog> {
        private static volatile MetalogLog[] _emptyArray;
        public MetalogCounterDimensions counterDimensions;

        public static MetalogLog[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new MetalogLog[0];
                    }
                }
            }
            return _emptyArray;
        }

        public MetalogLog() {
            clear();
        }

        public MetalogLog clear() {
            this.counterDimensions = null;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof MetalogLog)) {
                return false;
            }
            MetalogLog other = (MetalogLog) o;
            if (this.counterDimensions == null) {
                if (other.counterDimensions != null) {
                    return false;
                }
            } else if (!this.counterDimensions.equals(other.counterDimensions)) {
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
            int hashCode = (((getClass().getName().hashCode() + 527) * 31) + (this.counterDimensions == null ? 0 : this.counterDimensions.hashCode())) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.counterDimensions != null) {
                output.writeMessage(1, this.counterDimensions);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.counterDimensions != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(1, this.counterDimensions);
            }
            return size;
        }

        public MetalogLog mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        if (this.counterDimensions == null) {
                            this.counterDimensions = new MetalogCounterDimensions();
                        }
                        input.readMessage(this.counterDimensions);
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

        public static MetalogLog parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (MetalogLog) MessageNano.mergeFrom(new MetalogLog(), data);
        }

        public static MetalogLog parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new MetalogLog().mergeFrom(input);
        }
    }
}
