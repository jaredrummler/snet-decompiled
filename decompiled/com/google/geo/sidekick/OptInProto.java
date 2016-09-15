package com.google.geo.sidekick;

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

public interface OptInProto {

    public interface OptInMode {
        public static final int A_LA_CARTE = 3;
        public static final int EXPRESS = 2;
        public static final int FULL = 1;
        public static final int NOW_CARDS = 4;
    }

    public static final class OptInQuery extends ExtendableMessageNano<OptInQuery> {
        private static volatile OptInQuery[] _emptyArray;
        public int action;
        public int displayedOptInVersion;
        public OptInSetting[] displayedSetting;
        public int optInMode;

        public interface Action {
            public static final int ACCEPT = 1;
            public static final int DECLINE = 2;
            public static final int DELETE_DATA = 6;
            public static final int OPT_OUT_COMPLETE = 5;
            public static final int OPT_OUT_DEVICE = 4;
            public static final int SKIP = 3;
        }

        public static OptInQuery[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new OptInQuery[0];
                    }
                }
            }
            return _emptyArray;
        }

        public OptInQuery() {
            clear();
        }

        public OptInQuery clear() {
            this.action = 1;
            this.displayedSetting = OptInSetting.emptyArray();
            this.displayedOptInVersion = 0;
            this.optInMode = 1;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof OptInQuery)) {
                return false;
            }
            OptInQuery other = (OptInQuery) o;
            if (this.action != other.action || !InternalNano.equals(this.displayedSetting, other.displayedSetting) || this.displayedOptInVersion != other.displayedOptInVersion || this.optInMode != other.optInMode) {
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
            int hashCode = (((((((((getClass().getName().hashCode() + 527) * 31) + this.action) * 31) + InternalNano.hashCode(this.displayedSetting)) * 31) + this.displayedOptInVersion) * 31) + this.optInMode) * 31;
            int hashCode2 = (this.unknownFieldData == null || this.unknownFieldData.isEmpty()) ? 0 : this.unknownFieldData.hashCode();
            return hashCode + hashCode2;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.action != 1) {
                output.writeInt32(1, this.action);
            }
            if (this.displayedSetting != null && this.displayedSetting.length > 0) {
                for (OptInSetting element : this.displayedSetting) {
                    if (element != null) {
                        output.writeMessage(2, element);
                    }
                }
            }
            if (this.displayedOptInVersion != 0) {
                output.writeInt32(3, this.displayedOptInVersion);
            }
            if (this.optInMode != 1) {
                output.writeInt32(4, this.optInMode);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.action != 1) {
                size += CodedOutputByteBufferNano.computeInt32Size(1, this.action);
            }
            if (this.displayedSetting != null && this.displayedSetting.length > 0) {
                for (OptInSetting element : this.displayedSetting) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(2, element);
                    }
                }
            }
            if (this.displayedOptInVersion != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(3, this.displayedOptInVersion);
            }
            if (this.optInMode != 1) {
                return size + CodedOutputByteBufferNano.computeInt32Size(4, this.optInMode);
            }
            return size;
        }

        public OptInQuery mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                int value;
                switch (tag) {
                    case com.google.wireless.android.play.playlog.proto.UserLocation.LaunchSettingsEvent.Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_GET_LINK /*8*/:
                        value = input.readInt32();
                        switch (value) {
                            case TimeSelection.Type.TEMPORARY /*1*/:
                            case TimeSelection.Type.INDEFINITELY /*2*/:
                            case TimeSelection.Type.CUSTOM /*3*/:
                            case OvenFreshResult.NO_ACCOUNTS /*4*/:
                            case Type.ADD_NEW_SHARES /*5*/:
                            case Type.REMOVE_SHARE /*6*/:
                                this.action = value;
                                break;
                            default:
                                continue;
                        }
                    case LogSource.GOOGLE_ANALYTICS /*18*/:
                        int i;
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 18);
                        if (this.displayedSetting == null) {
                            i = 0;
                        } else {
                            i = this.displayedSetting.length;
                        }
                        OptInSetting[] newArray = new OptInSetting[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.displayedSetting, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new OptInSetting();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i++;
                        }
                        newArray[i] = new OptInSetting();
                        input.readMessage(newArray[i]);
                        this.displayedSetting = newArray;
                        continue;
                    case LogSource.LB_C /*24*/:
                        this.displayedOptInVersion = input.readInt32();
                        continue;
                    case LogSource.SOCIAL /*32*/:
                        value = input.readInt32();
                        switch (value) {
                            case TimeSelection.Type.TEMPORARY /*1*/:
                            case TimeSelection.Type.INDEFINITELY /*2*/:
                            case TimeSelection.Type.CUSTOM /*3*/:
                            case OvenFreshResult.NO_ACCOUNTS /*4*/:
                                this.optInMode = value;
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

        public static OptInQuery parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (OptInQuery) MessageNano.mergeFrom(new OptInQuery(), data);
        }

        public static OptInQuery parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new OptInQuery().mergeFrom(input);
        }
    }

    public static final class OptInResponse extends ExtendableMessageNano<OptInResponse> {
        private static volatile OptInResponse[] _emptyArray;
        public OptInSetting[] enabledSetting;

        public static OptInResponse[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new OptInResponse[0];
                    }
                }
            }
            return _emptyArray;
        }

        public OptInResponse() {
            clear();
        }

        public OptInResponse clear() {
            this.enabledSetting = OptInSetting.emptyArray();
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof OptInResponse)) {
                return false;
            }
            OptInResponse other = (OptInResponse) o;
            if (!InternalNano.equals(this.enabledSetting, other.enabledSetting)) {
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
            int hashCode = (((getClass().getName().hashCode() + 527) * 31) + InternalNano.hashCode(this.enabledSetting)) * 31;
            int hashCode2 = (this.unknownFieldData == null || this.unknownFieldData.isEmpty()) ? 0 : this.unknownFieldData.hashCode();
            return hashCode + hashCode2;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.enabledSetting != null && this.enabledSetting.length > 0) {
                for (OptInSetting element : this.enabledSetting) {
                    if (element != null) {
                        output.writeMessage(1, element);
                    }
                }
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.enabledSetting != null && this.enabledSetting.length > 0) {
                for (OptInSetting element : this.enabledSetting) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(1, element);
                    }
                }
            }
            return size;
        }

        public OptInResponse mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        int i;
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 10);
                        if (this.enabledSetting == null) {
                            i = 0;
                        } else {
                            i = this.enabledSetting.length;
                        }
                        OptInSetting[] newArray = new OptInSetting[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.enabledSetting, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new OptInSetting();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i++;
                        }
                        newArray[i] = new OptInSetting();
                        input.readMessage(newArray[i]);
                        this.enabledSetting = newArray;
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

        public static OptInResponse parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (OptInResponse) MessageNano.mergeFrom(new OptInResponse(), data);
        }

        public static OptInResponse parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new OptInResponse().mergeFrom(input);
        }
    }

    public static final class OptInSetting extends ExtendableMessageNano<OptInSetting> {
        private static volatile OptInSetting[] _emptyArray;
        public int id;

        public interface OptInSettingId {
            public static final int AUDIO_HISTORY = 5;
            public static final int DEVICE_STATE_AND_CONTENT = 2;
            public static final int GOOGLE_NOW_CARDS = 7;
            public static final int LOCATION_HISTORY_AND_REPORTING = 1;
            public static final int UNKNOWN = 0;
            public static final int WEB_AND_APP_HISTORY = 4;
            public static final int WEB_HISTORY = 6;
        }

        public static OptInSetting[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new OptInSetting[0];
                    }
                }
            }
            return _emptyArray;
        }

        public OptInSetting() {
            clear();
        }

        public OptInSetting clear() {
            this.id = 0;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof OptInSetting)) {
                return false;
            }
            OptInSetting other = (OptInSetting) o;
            if (this.id != other.id) {
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
            int hashCode = (((getClass().getName().hashCode() + 527) * 31) + this.id) * 31;
            int hashCode2 = (this.unknownFieldData == null || this.unknownFieldData.isEmpty()) ? 0 : this.unknownFieldData.hashCode();
            return hashCode + hashCode2;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.id != 0) {
                output.writeInt32(1, this.id);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.id != 0) {
                return size + CodedOutputByteBufferNano.computeInt32Size(1, this.id);
            }
            return size;
        }

        public OptInSetting mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_GET_LINK /*8*/:
                        this.id = input.readInt32();
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

        public static OptInSetting parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (OptInSetting) MessageNano.mergeFrom(new OptInSetting(), data);
        }

        public static OptInSetting parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new OptInSetting().mergeFrom(input);
        }
    }
}
