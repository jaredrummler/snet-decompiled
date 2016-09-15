package com.google.kidsmanagement.v1;

import com.google.kidsmanagement.v1.AppsCommonProto.AppMetadata;
import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.ExtendableMessageNano;
import com.google.protobuf.nano.InternalNano;
import com.google.protobuf.nano.InvalidProtocolBufferNanoException;
import com.google.protobuf.nano.MessageNano;
import com.google.wireless.android.play.playlog.proto.ClientAnalytics.LogRequest.LogSource;
import com.google.wireless.android.play.playlog.proto.UserLocation.LaunchSettingsEvent.Action;
import com.google.wireless.android.play.playlog.proto.UserLocation.LocationSharingEvent.Type;
import java.io.IOException;

public interface ActivitiesCommonProto {

    public static final class AppActivity extends ExtendableMessageNano<AppActivity> {
        private static volatile AppActivity[] _emptyArray;
        public AppMetadata app;
        public long usageMillis;

        public static AppActivity[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new AppActivity[0];
                    }
                }
            }
            return _emptyArray;
        }

        public AppActivity() {
            clear();
        }

        public AppActivity clear() {
            this.app = null;
            this.usageMillis = 0;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof AppActivity)) {
                return false;
            }
            AppActivity other = (AppActivity) o;
            if (this.app == null) {
                if (other.app != null) {
                    return false;
                }
            } else if (!this.app.equals(other.app)) {
                return false;
            }
            if (this.usageMillis != other.usageMillis) {
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
            int hashCode = (((((getClass().getName().hashCode() + 527) * 31) + (this.app == null ? 0 : this.app.hashCode())) * 31) + ((int) (this.usageMillis ^ (this.usageMillis >>> 32)))) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.app != null) {
                output.writeMessage(1, this.app);
            }
            if (this.usageMillis != 0) {
                output.writeInt64(2, this.usageMillis);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.app != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(1, this.app);
            }
            if (this.usageMillis != 0) {
                return size + CodedOutputByteBufferNano.computeInt64Size(2, this.usageMillis);
            }
            return size;
        }

        public AppActivity mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        if (this.app == null) {
                            this.app = new AppMetadata();
                        }
                        input.readMessage(this.app);
                        continue;
                    case LogSource.GMS_CORE_PEOPLE /*16*/:
                        this.usageMillis = input.readInt64();
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

        public static AppActivity parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (AppActivity) MessageNano.mergeFrom(new AppActivity(), data);
        }

        public static AppActivity parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new AppActivity().mergeFrom(input);
        }
    }
}
