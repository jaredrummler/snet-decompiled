package com.google.android.gms.common.proto;

import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.InternalNano;
import com.google.protobuf.nano.InvalidProtocolBufferNanoException;
import com.google.protobuf.nano.MessageNano;
import com.google.protobuf.nano.WireFormatNano;
import java.io.IOException;

public final class GCoreServiceId extends MessageNano {
    public static final int ACCOUNT = 9;
    public static final int ACCOUNT_STATUS = 74;
    public static final int ADDRESS = 12;
    public static final int ADMOB = 8;
    public static final int ADMOB_GSERVICES_VALUE = 46;
    public static final int AD_MEASUREMENT = 95;
    public static final int APP_DATA_SEARCH = 21;
    public static final int APP_INVITE = 77;
    public static final int APP_STATE = 7;
    public static final int AUDIO_MODEM = 48;
    public static final int AUTH_CONSENT = 92;
    public static final int AUTH_CREDENTIALS = 68;
    public static final int AUTH_GOOGLE_SIGN_IN = 91;
    public static final int AUTH_PROXY = 16;
    public static final int AUTH_SIGN_IN = 87;
    public static final int AUTO_BACKUP = 31;
    public static final int BACKUP_STATS = 90;
    public static final int CAR = 13;
    public static final int CAST = 10;
    public static final int CAST_MIRRORING = 27;
    public static final int CAST_REMOTE_DISPLAY = 83;
    public static final int CHECKIN_CONFIG = 64;
    public static final int CHROME_SYNC = 80;
    public static final int CHROME_SYNC_PASSWORD = 97;
    public static final int CHROME_SYNC_PREFERENCES = 96;
    public static final int CLEARCUT_LOGGER = 40;
    public static final int CLOUD_SAVE = 34;
    public static final int COMMON = 39;
    public static final int CONTEXT_MANAGER = 47;
    public static final int DEVICE_CONNECTIONS = 20;
    public static final int DEVICE_MANAGER = 37;
    public static final int DOWNLOAD = 43;
    public static final int DRIVE = 11;
    public static final int DROID_GUARD = 25;
    public static final int FEEDBACK = 29;
    public static final int FITNESS = 17;
    public static final int FIT_BLE = 59;
    public static final int FIT_CONFIG = 60;
    public static final int FIT_HISTORY = 57;
    public static final int FIT_INTERNAL = 61;
    public static final int FIT_RECORDING = 56;
    public static final int FIT_SENSORS = 55;
    public static final int FIT_SESSIONS = 58;
    public static final int FREIGHTER = 98;
    public static final int GAMES = 1;
    public static final int GLOBAL_SEARCH_ADMIN = 33;
    public static final int GOOGLE_HELP = 63;
    public static final int GOOGLE_LOCATION_MANAGER = 23;
    public static final int GSA = 82;
    public static final int IDENTITY = 15;
    public static final int IME_UPDATES = 66;
    public static final int KIDS = 42;
    public static final int LIGHTWEIGHT_NETWORK_QUALITY = 50;
    public static final int LIGHT_INDEX = 19;
    public static final int LOCATION = 6;
    public static final int LOCKBOX = 26;
    public static final int MAPS_API = 71;
    public static final int MEASUREMENT = 93;
    public static final int NEARBY_BOOTSTRAP = 69;
    public static final int NEARBY_CONNECTIONS = 54;
    public static final int NEARBY_MESSAGES = 62;
    public static final int NEARBY_SHARING = 49;
    public static final int NETWORK_QUALITY = 28;
    public static final int PANORAMA = 3;
    public static final int PEOPLE = 5;
    public static final int PHENOTYPE = 51;
    public static final int PLACES_GEO_DATA = 65;
    public static final int PLACES_PLACE_DETECTION = 67;
    public static final int PLAY_LOG = 24;
    public static final int PLUS = 2;
    public static final int PLUS_INTERNAL = 70;
    public static final int PSEUDONYMOUS_ID = 38;
    public static final int REMINDERS = 18;
    public static final int REPORTING = 22;
    public static final int SAFETY_NET = 45;
    public static final int SEARCH_ADMINISTRATION = 30;
    public static final int SEARCH_AUTH = 73;
    public static final int SEARCH_CORPORA = 36;
    public static final int SEARCH_NATIVE_API = 78;
    public static final int SEARCH_QUERIES = 32;
    public static final int SIGN_IN = 44;
    public static final int SMARTDEVICE_D2D_SOURCE_DEVICE = 75;
    public static final int SMARTDEVICE_D2D_TARGET_DEVICE = 76;
    public static final int SMARTDEVICE_SETUP_ACCOUNTS_BOOTSTRAP = 81;
    public static final int TRUSTAGENT = 84;
    public static final int TRUSTAGENT_BRIDGE = 89;
    public static final int TRUSTAGENT_STATE = 85;
    public static final int TRUSTAGENT_TRUSTED_DEVICES = 72;
    public static final int UDC = 35;
    public static final int UNKNOWN = 0;
    public static final int USAGE_REPORTING = 41;
    public static final int VISION = 88;
    public static final int VOICE_UNLOCK = 52;
    public static final int WALLET = 4;
    public static final int WALLET_TAP_AND_PAY = 79;
    public static final int WEAR = 14;
    public static final int WEAVE_APP_ACCESS = 101;
    public static final int WEAVE_COMMAND = 100;
    public static final int WEAVE_DEVICE = 94;
    public static final int WEAVE_EVENT = 102;
    public static final int WEAVE_LOCAL_STATELESS_DEVICE = 103;
    public static final int WEAVE_MANAGEMENT = 99;
    public static final int YOUTUBE = 86;
    private static volatile GCoreServiceId[] _emptyArray;

    public static GCoreServiceId[] emptyArray() {
        if (_emptyArray == null) {
            synchronized (InternalNano.LAZY_INIT_LOCK) {
                if (_emptyArray == null) {
                    _emptyArray = new GCoreServiceId[UNKNOWN];
                }
            }
        }
        return _emptyArray;
    }

    public GCoreServiceId() {
        clear();
    }

    public GCoreServiceId clear() {
        this.cachedSize = -1;
        return this;
    }

    public GCoreServiceId mergeFrom(CodedInputByteBufferNano input) throws IOException {
        int tag;
        do {
            tag = input.readTag();
            switch (tag) {
                case UNKNOWN /*0*/:
                    break;
                default:
                    break;
            }
            return this;
        } while (WireFormatNano.parseUnknownField(input, tag));
        return this;
    }

    public static GCoreServiceId parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
        return (GCoreServiceId) MessageNano.mergeFrom(new GCoreServiceId(), data);
    }

    public static GCoreServiceId parseFrom(CodedInputByteBufferNano input) throws IOException {
        return new GCoreServiceId().mergeFrom(input);
    }
}
