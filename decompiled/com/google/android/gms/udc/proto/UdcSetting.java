package com.google.android.gms.udc.proto;

import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.ExtendableMessageNano;
import com.google.protobuf.nano.InternalNano;
import com.google.protobuf.nano.InvalidProtocolBufferNanoException;
import com.google.protobuf.nano.MessageNano;
import com.google.wireless.android.play.playlog.proto.UserLocation.LaunchSettingsEvent.Action;
import java.io.IOException;

public final class UdcSetting extends ExtendableMessageNano<UdcSetting> {
    private static volatile UdcSetting[] _emptyArray;

    public interface SettingId {
        public static final int AUDIO_HISTORY = 5;
        public static final int DEVICE_INFO_DEVICE_LEVEL = 9;
        public static final int DEVICE_STATE_AND_CONTENT_HISTORY = 7;
        public static final int LOCATION_HISTORY = 2;
        public static final int LOCATION_REPORTING = 15;
        public static final int SEARCH_HISTORY = 1;
        public static final int SUPL_WEB_AND_APP_DEVICE_LEVEL = 10;
        public static final int UNKNOWN_ID = 0;
        public static final int WEB_AND_APP_PLATFORM_HISTORY = 8;
        public static final int YT_SEARCH_HISTORY = 3;
        public static final int YT_WATCH_HISTORY = 4;
    }

    public interface SettingValue {
        public static final int DISABLED = 3;
        public static final int ENABLED = 2;
        public static final int SETTING_VALUE_INVALID_ENTRY = 0;
        public static final int UNSET = 1;
    }

    public static UdcSetting[] emptyArray() {
        if (_emptyArray == null) {
            synchronized (InternalNano.LAZY_INIT_LOCK) {
                if (_emptyArray == null) {
                    _emptyArray = new UdcSetting[0];
                }
            }
        }
        return _emptyArray;
    }

    public UdcSetting() {
        clear();
    }

    public UdcSetting clear() {
        this.unknownFieldData = null;
        this.cachedSize = -1;
        return this;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof UdcSetting)) {
            return false;
        }
        UdcSetting other = (UdcSetting) o;
        if (this.unknownFieldData != null && !this.unknownFieldData.isEmpty()) {
            return this.unknownFieldData.equals(other.unknownFieldData);
        }
        if (other.unknownFieldData == null || other.unknownFieldData.isEmpty()) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int hashCode = (getClass().getName().hashCode() + 527) * 31;
        int hashCode2 = (this.unknownFieldData == null || this.unknownFieldData.isEmpty()) ? 0 : this.unknownFieldData.hashCode();
        return hashCode + hashCode2;
    }

    public UdcSetting mergeFrom(CodedInputByteBufferNano input) throws IOException {
        int tag;
        do {
            tag = input.readTag();
            switch (tag) {
                case Action.UNKNOWN /*0*/:
                    break;
                default:
                    break;
            }
            return this;
        } while (storeUnknownField(input, tag));
        return this;
    }

    public static UdcSetting parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
        return (UdcSetting) MessageNano.mergeFrom(new UdcSetting(), data);
    }

    public static UdcSetting parseFrom(CodedInputByteBufferNano input) throws IOException {
        return new UdcSetting().mergeFrom(input);
    }
}
