package com.google.experiments.heterodyne;

import com.google.android.gms.lint.BuildConfig;
import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.ExtendableMessageNano;
import com.google.protobuf.nano.InternalNano;
import com.google.protobuf.nano.InvalidProtocolBufferNanoException;
import com.google.protobuf.nano.MessageNano;
import com.google.protobuf.nano.WireFormatNano;
import com.google.wireless.android.play.playlog.proto.ClientAnalytics.ClientInfo;
import com.google.wireless.android.play.playlog.proto.ClientAnalytics.LogRequest.LogSource;
import com.google.wireless.android.play.playlog.proto.UserLocation.LaunchSettingsEvent.Action;
import com.google.wireless.android.play.playlog.proto.UserLocation.LocationSharingEvent.Type;
import com.google.wireless.android.play.playlog.proto.UserLocation.OvenFreshEvent.OvenFreshResult;
import com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection;
import java.io.IOException;
import java.util.Arrays;

public interface ExperimentsProto {

    public static final class ApplicationRequest extends ExtendableMessageNano<ApplicationRequest> {
        private static volatile ApplicationRequest[] _emptyArray;
        public byte[] applicationProperties;
        public ParamPartitionTag[] paramPartitionTag;
        public Scope scope;

        public static ApplicationRequest[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new ApplicationRequest[0];
                    }
                }
            }
            return _emptyArray;
        }

        public ApplicationRequest() {
            clear();
        }

        public ApplicationRequest clear() {
            this.scope = null;
            this.applicationProperties = WireFormatNano.EMPTY_BYTES;
            this.paramPartitionTag = ParamPartitionTag.emptyArray();
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof ApplicationRequest)) {
                return false;
            }
            ApplicationRequest other = (ApplicationRequest) o;
            if (this.scope == null) {
                if (other.scope != null) {
                    return false;
                }
            } else if (!this.scope.equals(other.scope)) {
                return false;
            }
            if (!Arrays.equals(this.applicationProperties, other.applicationProperties) || !InternalNano.equals(this.paramPartitionTag, other.paramPartitionTag)) {
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
            int hashCode = (((((((getClass().getName().hashCode() + 527) * 31) + (this.scope == null ? 0 : this.scope.hashCode())) * 31) + Arrays.hashCode(this.applicationProperties)) * 31) + InternalNano.hashCode(this.paramPartitionTag)) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.scope != null) {
                output.writeMessage(1, this.scope);
            }
            if (!Arrays.equals(this.applicationProperties, WireFormatNano.EMPTY_BYTES)) {
                output.writeBytes(2, this.applicationProperties);
            }
            if (this.paramPartitionTag != null && this.paramPartitionTag.length > 0) {
                for (ParamPartitionTag element : this.paramPartitionTag) {
                    if (element != null) {
                        output.writeMessage(3, element);
                    }
                }
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.scope != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(1, this.scope);
            }
            if (!Arrays.equals(this.applicationProperties, WireFormatNano.EMPTY_BYTES)) {
                size += CodedOutputByteBufferNano.computeBytesSize(2, this.applicationProperties);
            }
            if (this.paramPartitionTag != null && this.paramPartitionTag.length > 0) {
                for (ParamPartitionTag element : this.paramPartitionTag) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(3, element);
                    }
                }
            }
            return size;
        }

        public ApplicationRequest mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        if (this.scope == null) {
                            this.scope = new Scope();
                        }
                        input.readMessage(this.scope);
                        continue;
                    case LogSource.GOOGLE_ANALYTICS /*18*/:
                        this.applicationProperties = input.readBytes();
                        continue;
                    case LogSource.ANDROID_CAMERA /*26*/:
                        int i;
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 26);
                        if (this.paramPartitionTag == null) {
                            i = 0;
                        } else {
                            i = this.paramPartitionTag.length;
                        }
                        ParamPartitionTag[] newArray = new ParamPartitionTag[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.paramPartitionTag, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new ParamPartitionTag();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i++;
                        }
                        newArray[i] = new ParamPartitionTag();
                        input.readMessage(newArray[i]);
                        this.paramPartitionTag = newArray;
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

        public static ApplicationRequest parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (ApplicationRequest) MessageNano.mergeFrom(new ApplicationRequest(), data);
        }

        public static ApplicationRequest parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new ApplicationRequest().mergeFrom(input);
        }
    }

    public static final class ApplicationResponse extends ExtendableMessageNano<ApplicationResponse> {
        private static volatile ApplicationResponse[] _emptyArray;
        public int[] clientExperimentId;
        public CrossAppDescriptor[] crossAppDescriptor;
        public byte[] experimentToken;
        public ParamPartition[] paramPartition;
        public Scope scope;
        public String serverTriggerToken;

        public static ApplicationResponse[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new ApplicationResponse[0];
                    }
                }
            }
            return _emptyArray;
        }

        public ApplicationResponse() {
            clear();
        }

        public ApplicationResponse clear() {
            this.scope = null;
            this.paramPartition = ParamPartition.emptyArray();
            this.experimentToken = WireFormatNano.EMPTY_BYTES;
            this.serverTriggerToken = BuildConfig.VERSION_NAME;
            this.clientExperimentId = WireFormatNano.EMPTY_INT_ARRAY;
            this.crossAppDescriptor = CrossAppDescriptor.emptyArray();
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof ApplicationResponse)) {
                return false;
            }
            ApplicationResponse other = (ApplicationResponse) o;
            if (this.scope == null) {
                if (other.scope != null) {
                    return false;
                }
            } else if (!this.scope.equals(other.scope)) {
                return false;
            }
            if (!InternalNano.equals(this.paramPartition, other.paramPartition) || !Arrays.equals(this.experimentToken, other.experimentToken)) {
                return false;
            }
            if (this.serverTriggerToken == null) {
                if (other.serverTriggerToken != null) {
                    return false;
                }
            } else if (!this.serverTriggerToken.equals(other.serverTriggerToken)) {
                return false;
            }
            if (!InternalNano.equals(this.clientExperimentId, other.clientExperimentId) || !InternalNano.equals(this.crossAppDescriptor, other.crossAppDescriptor)) {
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
            int hashCode = (((((((((((((getClass().getName().hashCode() + 527) * 31) + (this.scope == null ? 0 : this.scope.hashCode())) * 31) + InternalNano.hashCode(this.paramPartition)) * 31) + Arrays.hashCode(this.experimentToken)) * 31) + (this.serverTriggerToken == null ? 0 : this.serverTriggerToken.hashCode())) * 31) + InternalNano.hashCode(this.clientExperimentId)) * 31) + InternalNano.hashCode(this.crossAppDescriptor)) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.scope != null) {
                output.writeMessage(1, this.scope);
            }
            if (this.paramPartition != null && this.paramPartition.length > 0) {
                for (ParamPartition element : this.paramPartition) {
                    if (element != null) {
                        output.writeMessage(2, element);
                    }
                }
            }
            if (!Arrays.equals(this.experimentToken, WireFormatNano.EMPTY_BYTES)) {
                output.writeBytes(3, this.experimentToken);
            }
            if (!this.serverTriggerToken.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(4, this.serverTriggerToken);
            }
            if (this.clientExperimentId != null && this.clientExperimentId.length > 0) {
                for (int writeInt32 : this.clientExperimentId) {
                    output.writeInt32(5, writeInt32);
                }
            }
            if (this.crossAppDescriptor != null && this.crossAppDescriptor.length > 0) {
                for (CrossAppDescriptor element2 : this.crossAppDescriptor) {
                    if (element2 != null) {
                        output.writeMessage(6, element2);
                    }
                }
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.scope != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(1, this.scope);
            }
            if (this.paramPartition != null && this.paramPartition.length > 0) {
                for (ParamPartition element : this.paramPartition) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(2, element);
                    }
                }
            }
            if (!Arrays.equals(this.experimentToken, WireFormatNano.EMPTY_BYTES)) {
                size += CodedOutputByteBufferNano.computeBytesSize(3, this.experimentToken);
            }
            if (!this.serverTriggerToken.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(4, this.serverTriggerToken);
            }
            if (this.clientExperimentId != null && this.clientExperimentId.length > 0) {
                int dataSize = 0;
                for (int element2 : this.clientExperimentId) {
                    dataSize += CodedOutputByteBufferNano.computeInt32SizeNoTag(element2);
                }
                size = (size + dataSize) + (this.clientExperimentId.length * 1);
            }
            if (this.crossAppDescriptor != null && this.crossAppDescriptor.length > 0) {
                for (CrossAppDescriptor element3 : this.crossAppDescriptor) {
                    if (element3 != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(6, element3);
                    }
                }
            }
            return size;
        }

        public ApplicationResponse mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                int arrayLength;
                int i;
                int[] newArray;
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        if (this.scope == null) {
                            this.scope = new Scope();
                        }
                        input.readMessage(this.scope);
                        continue;
                    case LogSource.GOOGLE_ANALYTICS /*18*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 18);
                        if (this.paramPartition == null) {
                            i = 0;
                        } else {
                            i = this.paramPartition.length;
                        }
                        ParamPartition[] newArray2 = new ParamPartition[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.paramPartition, 0, newArray2, 0, i);
                        }
                        while (i < newArray2.length - 1) {
                            newArray2[i] = new ParamPartition();
                            input.readMessage(newArray2[i]);
                            input.readTag();
                            i++;
                        }
                        newArray2[i] = new ParamPartition();
                        input.readMessage(newArray2[i]);
                        this.paramPartition = newArray2;
                        continue;
                    case LogSource.ANDROID_CAMERA /*26*/:
                        this.experimentToken = input.readBytes();
                        continue;
                    case LogSource.NOVA /*34*/:
                        this.serverTriggerToken = input.readString();
                        continue;
                    case LogSource.COPRESENCE /*40*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 40);
                        i = this.clientExperimentId == null ? 0 : this.clientExperimentId.length;
                        newArray = new int[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.clientExperimentId, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = input.readInt32();
                            input.readTag();
                            i++;
                        }
                        newArray[i] = input.readInt32();
                        this.clientExperimentId = newArray;
                        continue;
                    case LogSource.PHOTOS /*42*/:
                        int limit = input.pushLimit(input.readRawVarint32());
                        arrayLength = 0;
                        int startPos = input.getPosition();
                        while (input.getBytesUntilLimit() > 0) {
                            input.readInt32();
                            arrayLength++;
                        }
                        input.rewindToPosition(startPos);
                        i = this.clientExperimentId == null ? 0 : this.clientExperimentId.length;
                        newArray = new int[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.clientExperimentId, 0, newArray, 0, i);
                        }
                        while (i < newArray.length) {
                            newArray[i] = input.readInt32();
                            i++;
                        }
                        this.clientExperimentId = newArray;
                        input.popLimit(limit);
                        continue;
                    case LogSource.HANGOUT_LOG_REQUEST /*50*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 50);
                        if (this.crossAppDescriptor == null) {
                            i = 0;
                        } else {
                            i = this.crossAppDescriptor.length;
                        }
                        CrossAppDescriptor[] newArray3 = new CrossAppDescriptor[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.crossAppDescriptor, 0, newArray3, 0, i);
                        }
                        while (i < newArray3.length - 1) {
                            newArray3[i] = new CrossAppDescriptor();
                            input.readMessage(newArray3[i]);
                            input.readTag();
                            i++;
                        }
                        newArray3[i] = new CrossAppDescriptor();
                        input.readMessage(newArray3[i]);
                        this.crossAppDescriptor = newArray3;
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

        public static ApplicationResponse parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (ApplicationResponse) MessageNano.mergeFrom(new ApplicationResponse(), data);
        }

        public static ApplicationResponse parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new ApplicationResponse().mergeFrom(input);
        }
    }

    public static final class ClientProperties extends ExtendableMessageNano<ClientProperties> {
        private static volatile ClientProperties[] _emptyArray;
        public boolean authenticated;
        public ClientInfo clientInfo;
        public byte[] dogfoodToken;
        public String pseudonymousCookie;
        public long pseudonymousId;
        public long userId;

        public static ClientProperties[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new ClientProperties[0];
                    }
                }
            }
            return _emptyArray;
        }

        public ClientProperties() {
            clear();
        }

        public ClientProperties clear() {
            this.authenticated = false;
            this.userId = 0;
            this.pseudonymousId = 0;
            this.clientInfo = null;
            this.pseudonymousCookie = BuildConfig.VERSION_NAME;
            this.dogfoodToken = WireFormatNano.EMPTY_BYTES;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof ClientProperties)) {
                return false;
            }
            ClientProperties other = (ClientProperties) o;
            if (this.authenticated != other.authenticated || this.userId != other.userId || this.pseudonymousId != other.pseudonymousId) {
                return false;
            }
            if (this.clientInfo == null) {
                if (other.clientInfo != null) {
                    return false;
                }
            } else if (!this.clientInfo.equals(other.clientInfo)) {
                return false;
            }
            if (this.pseudonymousCookie == null) {
                if (other.pseudonymousCookie != null) {
                    return false;
                }
            } else if (!this.pseudonymousCookie.equals(other.pseudonymousCookie)) {
                return false;
            }
            if (!Arrays.equals(this.dogfoodToken, other.dogfoodToken)) {
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
            int hashCode = (((((((((((((getClass().getName().hashCode() + 527) * 31) + (this.authenticated ? 1231 : 1237)) * 31) + ((int) (this.userId ^ (this.userId >>> 32)))) * 31) + ((int) (this.pseudonymousId ^ (this.pseudonymousId >>> 32)))) * 31) + (this.clientInfo == null ? 0 : this.clientInfo.hashCode())) * 31) + (this.pseudonymousCookie == null ? 0 : this.pseudonymousCookie.hashCode())) * 31) + Arrays.hashCode(this.dogfoodToken)) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.authenticated) {
                output.writeBool(1, this.authenticated);
            }
            if (this.userId != 0) {
                output.writeInt64(2, this.userId);
            }
            if (this.pseudonymousId != 0) {
                output.writeInt64(3, this.pseudonymousId);
            }
            if (this.clientInfo != null) {
                output.writeMessage(4, this.clientInfo);
            }
            if (!this.pseudonymousCookie.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(5, this.pseudonymousCookie);
            }
            if (!Arrays.equals(this.dogfoodToken, WireFormatNano.EMPTY_BYTES)) {
                output.writeBytes(6, this.dogfoodToken);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.authenticated) {
                size += CodedOutputByteBufferNano.computeBoolSize(1, this.authenticated);
            }
            if (this.userId != 0) {
                size += CodedOutputByteBufferNano.computeInt64Size(2, this.userId);
            }
            if (this.pseudonymousId != 0) {
                size += CodedOutputByteBufferNano.computeInt64Size(3, this.pseudonymousId);
            }
            if (this.clientInfo != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(4, this.clientInfo);
            }
            if (!this.pseudonymousCookie.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(5, this.pseudonymousCookie);
            }
            if (Arrays.equals(this.dogfoodToken, WireFormatNano.EMPTY_BYTES)) {
                return size;
            }
            return size + CodedOutputByteBufferNano.computeBytesSize(6, this.dogfoodToken);
        }

        public ClientProperties mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_GET_LINK /*8*/:
                        this.authenticated = input.readBool();
                        continue;
                    case LogSource.GMS_CORE_PEOPLE /*16*/:
                        this.userId = input.readInt64();
                        continue;
                    case LogSource.LB_C /*24*/:
                        this.pseudonymousId = input.readInt64();
                        continue;
                    case LogSource.NOVA /*34*/:
                        if (this.clientInfo == null) {
                            this.clientInfo = new ClientInfo();
                        }
                        input.readMessage(this.clientInfo);
                        continue;
                    case LogSource.PHOTOS /*42*/:
                        this.pseudonymousCookie = input.readString();
                        continue;
                    case LogSource.HANGOUT_LOG_REQUEST /*50*/:
                        this.dogfoodToken = input.readBytes();
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

        public static ClientProperties parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (ClientProperties) MessageNano.mergeFrom(new ClientProperties(), data);
        }

        public static ClientProperties parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new ClientProperties().mergeFrom(input);
        }
    }

    public static final class CrossAppDescriptor extends ExtendableMessageNano<CrossAppDescriptor> {
        private static volatile CrossAppDescriptor[] _emptyArray;
        public byte[] experimentToken;
        public Scope fromScope;
        public int provenance;

        public interface DiversionProvenance {
            public static final int ALWAYS_CROSS = 3;
            public static final int GAIA = 1;
            public static final int OTHER = 4;
            public static final int PSEUDONYMOUS = 2;
            public static final int UNKNOWN = 0;
        }

        public static CrossAppDescriptor[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new CrossAppDescriptor[0];
                    }
                }
            }
            return _emptyArray;
        }

        public CrossAppDescriptor() {
            clear();
        }

        public CrossAppDescriptor clear() {
            this.fromScope = null;
            this.experimentToken = WireFormatNano.EMPTY_BYTES;
            this.provenance = 0;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof CrossAppDescriptor)) {
                return false;
            }
            CrossAppDescriptor other = (CrossAppDescriptor) o;
            if (this.fromScope == null) {
                if (other.fromScope != null) {
                    return false;
                }
            } else if (!this.fromScope.equals(other.fromScope)) {
                return false;
            }
            if (!Arrays.equals(this.experimentToken, other.experimentToken) || this.provenance != other.provenance) {
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
            int hashCode = (((((((getClass().getName().hashCode() + 527) * 31) + (this.fromScope == null ? 0 : this.fromScope.hashCode())) * 31) + Arrays.hashCode(this.experimentToken)) * 31) + this.provenance) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.fromScope != null) {
                output.writeMessage(1, this.fromScope);
            }
            if (!Arrays.equals(this.experimentToken, WireFormatNano.EMPTY_BYTES)) {
                output.writeBytes(2, this.experimentToken);
            }
            if (this.provenance != 0) {
                output.writeInt32(3, this.provenance);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.fromScope != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(1, this.fromScope);
            }
            if (!Arrays.equals(this.experimentToken, WireFormatNano.EMPTY_BYTES)) {
                size += CodedOutputByteBufferNano.computeBytesSize(2, this.experimentToken);
            }
            if (this.provenance != 0) {
                return size + CodedOutputByteBufferNano.computeInt32Size(3, this.provenance);
            }
            return size;
        }

        public CrossAppDescriptor mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        if (this.fromScope == null) {
                            this.fromScope = new Scope();
                        }
                        input.readMessage(this.fromScope);
                        continue;
                    case LogSource.GOOGLE_ANALYTICS /*18*/:
                        this.experimentToken = input.readBytes();
                        continue;
                    case LogSource.LB_C /*24*/:
                        int value = input.readInt32();
                        switch (value) {
                            case Action.UNKNOWN /*0*/:
                            case TimeSelection.Type.TEMPORARY /*1*/:
                            case TimeSelection.Type.INDEFINITELY /*2*/:
                            case TimeSelection.Type.CUSTOM /*3*/:
                            case OvenFreshResult.NO_ACCOUNTS /*4*/:
                                this.provenance = value;
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

        public static CrossAppDescriptor parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (CrossAppDescriptor) MessageNano.mergeFrom(new CrossAppDescriptor(), data);
        }

        public static CrossAppDescriptor parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new CrossAppDescriptor().mergeFrom(input);
        }
    }

    public static final class ExperimentsAndConfigsRequest extends ExtendableMessageNano<ExperimentsAndConfigsRequest> {
        private static volatile ExperimentsAndConfigsRequest[] _emptyArray;
        public ApplicationRequest[] applicationRequest;
        public ClientProperties clientProperties;
        public byte[] requestConfigTag;

        public static ExperimentsAndConfigsRequest[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new ExperimentsAndConfigsRequest[0];
                    }
                }
            }
            return _emptyArray;
        }

        public ExperimentsAndConfigsRequest() {
            clear();
        }

        public ExperimentsAndConfigsRequest clear() {
            this.clientProperties = null;
            this.applicationRequest = ApplicationRequest.emptyArray();
            this.requestConfigTag = WireFormatNano.EMPTY_BYTES;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof ExperimentsAndConfigsRequest)) {
                return false;
            }
            ExperimentsAndConfigsRequest other = (ExperimentsAndConfigsRequest) o;
            if (this.clientProperties == null) {
                if (other.clientProperties != null) {
                    return false;
                }
            } else if (!this.clientProperties.equals(other.clientProperties)) {
                return false;
            }
            if (!InternalNano.equals(this.applicationRequest, other.applicationRequest) || !Arrays.equals(this.requestConfigTag, other.requestConfigTag)) {
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
            int hashCode = (((((((getClass().getName().hashCode() + 527) * 31) + (this.clientProperties == null ? 0 : this.clientProperties.hashCode())) * 31) + InternalNano.hashCode(this.applicationRequest)) * 31) + Arrays.hashCode(this.requestConfigTag)) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.clientProperties != null) {
                output.writeMessage(1, this.clientProperties);
            }
            if (this.applicationRequest != null && this.applicationRequest.length > 0) {
                for (ApplicationRequest element : this.applicationRequest) {
                    if (element != null) {
                        output.writeMessage(2, element);
                    }
                }
            }
            if (!Arrays.equals(this.requestConfigTag, WireFormatNano.EMPTY_BYTES)) {
                output.writeBytes(3, this.requestConfigTag);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.clientProperties != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(1, this.clientProperties);
            }
            if (this.applicationRequest != null && this.applicationRequest.length > 0) {
                for (ApplicationRequest element : this.applicationRequest) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(2, element);
                    }
                }
            }
            if (Arrays.equals(this.requestConfigTag, WireFormatNano.EMPTY_BYTES)) {
                return size;
            }
            return size + CodedOutputByteBufferNano.computeBytesSize(3, this.requestConfigTag);
        }

        public ExperimentsAndConfigsRequest mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        if (this.clientProperties == null) {
                            this.clientProperties = new ClientProperties();
                        }
                        input.readMessage(this.clientProperties);
                        continue;
                    case LogSource.GOOGLE_ANALYTICS /*18*/:
                        int i;
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 18);
                        if (this.applicationRequest == null) {
                            i = 0;
                        } else {
                            i = this.applicationRequest.length;
                        }
                        ApplicationRequest[] newArray = new ApplicationRequest[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.applicationRequest, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new ApplicationRequest();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i++;
                        }
                        newArray[i] = new ApplicationRequest();
                        input.readMessage(newArray[i]);
                        this.applicationRequest = newArray;
                        continue;
                    case LogSource.ANDROID_CAMERA /*26*/:
                        this.requestConfigTag = input.readBytes();
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

        public static ExperimentsAndConfigsRequest parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (ExperimentsAndConfigsRequest) MessageNano.mergeFrom(new ExperimentsAndConfigsRequest(), data);
        }

        public static ExperimentsAndConfigsRequest parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new ExperimentsAndConfigsRequest().mergeFrom(input);
        }
    }

    public static final class ExperimentsAndConfigsResponse extends ExtendableMessageNano<ExperimentsAndConfigsResponse> {
        private static volatile ExperimentsAndConfigsResponse[] _emptyArray;
        public ApplicationResponse[] applicationResponse;
        public String pseudonymousCookie;
        public byte[] requestConfigTag;

        public static ExperimentsAndConfigsResponse[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new ExperimentsAndConfigsResponse[0];
                    }
                }
            }
            return _emptyArray;
        }

        public ExperimentsAndConfigsResponse() {
            clear();
        }

        public ExperimentsAndConfigsResponse clear() {
            this.applicationResponse = ApplicationResponse.emptyArray();
            this.requestConfigTag = WireFormatNano.EMPTY_BYTES;
            this.pseudonymousCookie = BuildConfig.VERSION_NAME;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof ExperimentsAndConfigsResponse)) {
                return false;
            }
            ExperimentsAndConfigsResponse other = (ExperimentsAndConfigsResponse) o;
            if (!InternalNano.equals(this.applicationResponse, other.applicationResponse) || !Arrays.equals(this.requestConfigTag, other.requestConfigTag)) {
                return false;
            }
            if (this.pseudonymousCookie == null) {
                if (other.pseudonymousCookie != null) {
                    return false;
                }
            } else if (!this.pseudonymousCookie.equals(other.pseudonymousCookie)) {
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
            int hashCode = (((((((getClass().getName().hashCode() + 527) * 31) + InternalNano.hashCode(this.applicationResponse)) * 31) + Arrays.hashCode(this.requestConfigTag)) * 31) + (this.pseudonymousCookie == null ? 0 : this.pseudonymousCookie.hashCode())) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.applicationResponse != null && this.applicationResponse.length > 0) {
                for (ApplicationResponse element : this.applicationResponse) {
                    if (element != null) {
                        output.writeMessage(1, element);
                    }
                }
            }
            if (!Arrays.equals(this.requestConfigTag, WireFormatNano.EMPTY_BYTES)) {
                output.writeBytes(2, this.requestConfigTag);
            }
            if (!this.pseudonymousCookie.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(3, this.pseudonymousCookie);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.applicationResponse != null && this.applicationResponse.length > 0) {
                for (ApplicationResponse element : this.applicationResponse) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(1, element);
                    }
                }
            }
            if (!Arrays.equals(this.requestConfigTag, WireFormatNano.EMPTY_BYTES)) {
                size += CodedOutputByteBufferNano.computeBytesSize(2, this.requestConfigTag);
            }
            if (this.pseudonymousCookie.equals(BuildConfig.VERSION_NAME)) {
                return size;
            }
            return size + CodedOutputByteBufferNano.computeStringSize(3, this.pseudonymousCookie);
        }

        public ExperimentsAndConfigsResponse mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        int i;
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 10);
                        if (this.applicationResponse == null) {
                            i = 0;
                        } else {
                            i = this.applicationResponse.length;
                        }
                        ApplicationResponse[] newArray = new ApplicationResponse[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.applicationResponse, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new ApplicationResponse();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i++;
                        }
                        newArray[i] = new ApplicationResponse();
                        input.readMessage(newArray[i]);
                        this.applicationResponse = newArray;
                        continue;
                    case LogSource.GOOGLE_ANALYTICS /*18*/:
                        this.requestConfigTag = input.readBytes();
                        continue;
                    case LogSource.ANDROID_CAMERA /*26*/:
                        this.pseudonymousCookie = input.readString();
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

        public static ExperimentsAndConfigsResponse parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (ExperimentsAndConfigsResponse) MessageNano.mergeFrom(new ExperimentsAndConfigsResponse(), data);
        }

        public static ExperimentsAndConfigsResponse parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new ExperimentsAndConfigsResponse().mergeFrom(input);
        }
    }

    public static final class Param extends ExtendableMessageNano<Param> {
        private static volatile Param[] _emptyArray;
        public boolean boolValue;
        public boolean deleted;
        public ExtensionValue extensionValue;
        public double float64Value;
        public long intValue;
        public String name;
        public int paramStorageType;
        public String stringValue;
        public int valueType;

        public static final class ExtensionValue extends ExtendableMessageNano<ExtensionValue> {
            private static volatile ExtensionValue[] _emptyArray;
            public byte[] serializedValue;

            public static ExtensionValue[] emptyArray() {
                if (_emptyArray == null) {
                    synchronized (InternalNano.LAZY_INIT_LOCK) {
                        if (_emptyArray == null) {
                            _emptyArray = new ExtensionValue[0];
                        }
                    }
                }
                return _emptyArray;
            }

            public ExtensionValue() {
                clear();
            }

            public ExtensionValue clear() {
                this.serializedValue = WireFormatNano.EMPTY_BYTES;
                this.unknownFieldData = null;
                this.cachedSize = -1;
                return this;
            }

            public boolean equals(Object o) {
                if (o == this) {
                    return true;
                }
                if (!(o instanceof ExtensionValue)) {
                    return false;
                }
                ExtensionValue other = (ExtensionValue) o;
                if (!Arrays.equals(this.serializedValue, other.serializedValue)) {
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
                int hashCode = (((getClass().getName().hashCode() + 527) * 31) + Arrays.hashCode(this.serializedValue)) * 31;
                int hashCode2 = (this.unknownFieldData == null || this.unknownFieldData.isEmpty()) ? 0 : this.unknownFieldData.hashCode();
                return hashCode + hashCode2;
            }

            public void writeTo(CodedOutputByteBufferNano output) throws IOException {
                if (!Arrays.equals(this.serializedValue, WireFormatNano.EMPTY_BYTES)) {
                    output.writeBytes(1, this.serializedValue);
                }
                super.writeTo(output);
            }

            protected int computeSerializedSize() {
                int size = super.computeSerializedSize();
                if (Arrays.equals(this.serializedValue, WireFormatNano.EMPTY_BYTES)) {
                    return size;
                }
                return size + CodedOutputByteBufferNano.computeBytesSize(1, this.serializedValue);
            }

            public ExtensionValue mergeFrom(CodedInputByteBufferNano input) throws IOException {
                while (true) {
                    int tag = input.readTag();
                    switch (tag) {
                        case Action.UNKNOWN /*0*/:
                            break;
                        case Type.TAP_ABOUT /*10*/:
                            this.serializedValue = input.readBytes();
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

            public static ExtensionValue parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
                return (ExtensionValue) MessageNano.mergeFrom(new ExtensionValue(), data);
            }

            public static ExtensionValue parseFrom(CodedInputByteBufferNano input) throws IOException {
                return new ExtensionValue().mergeFrom(input);
            }
        }

        public interface ParamStorageType {
            public static final int GSERVICES = 1;
            public static final int UNSPECIFIED = 0;
        }

        public interface ParamValueType {
            public static final int BOOL_VALUE = 2;
            public static final int EXTENSION_VALUE = 5;
            public static final int FLOAT64_VALUE = 3;
            public static final int INT_VALUE = 1;
            public static final int STRING_VALUE = 4;
            public static final int UNKNOWN = 0;
        }

        public static Param[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new Param[0];
                    }
                }
            }
            return _emptyArray;
        }

        public Param() {
            clear();
        }

        public Param clear() {
            this.name = BuildConfig.VERSION_NAME;
            this.intValue = 0;
            this.boolValue = false;
            this.float64Value = 0.0d;
            this.stringValue = BuildConfig.VERSION_NAME;
            this.extensionValue = null;
            this.valueType = 0;
            this.deleted = false;
            this.paramStorageType = 0;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof Param)) {
                return false;
            }
            Param other = (Param) o;
            if (this.name == null) {
                if (other.name != null) {
                    return false;
                }
            } else if (!this.name.equals(other.name)) {
                return false;
            }
            if (this.intValue != other.intValue || this.boolValue != other.boolValue || Double.doubleToLongBits(this.float64Value) != Double.doubleToLongBits(other.float64Value)) {
                return false;
            }
            if (this.stringValue == null) {
                if (other.stringValue != null) {
                    return false;
                }
            } else if (!this.stringValue.equals(other.stringValue)) {
                return false;
            }
            if (this.extensionValue == null) {
                if (other.extensionValue != null) {
                    return false;
                }
            } else if (!this.extensionValue.equals(other.extensionValue)) {
                return false;
            }
            if (this.valueType != other.valueType || this.deleted != other.deleted || this.paramStorageType != other.paramStorageType) {
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
            int hashCode = (((((getClass().getName().hashCode() + 527) * 31) + (this.name == null ? 0 : this.name.hashCode())) * 31) + ((int) (this.intValue ^ (this.intValue >>> 32)))) * 31;
            if (this.boolValue) {
                i = 1231;
            } else {
                i = 1237;
            }
            int result = hashCode + i;
            long v = Double.doubleToLongBits(this.float64Value);
            i = ((((((((result * 31) + ((int) ((v >>> 32) ^ v))) * 31) + (this.stringValue == null ? 0 : this.stringValue.hashCode())) * 31) + (this.extensionValue == null ? 0 : this.extensionValue.hashCode())) * 31) + this.valueType) * 31;
            if (!this.deleted) {
                i2 = 1237;
            }
            i = (((i + i2) * 31) + this.paramStorageType) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i3 = this.unknownFieldData.hashCode();
            }
            return i + i3;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (!this.name.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(1, this.name);
            }
            if (this.intValue != 0) {
                output.writeInt64(2, this.intValue);
            }
            if (this.boolValue) {
                output.writeBool(3, this.boolValue);
            }
            if (Double.doubleToLongBits(this.float64Value) != Double.doubleToLongBits(0.0d)) {
                output.writeDouble(4, this.float64Value);
            }
            if (!this.stringValue.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(5, this.stringValue);
            }
            if (this.extensionValue != null) {
                output.writeMessage(6, this.extensionValue);
            }
            if (this.deleted) {
                output.writeBool(7, this.deleted);
            }
            if (this.paramStorageType != 0) {
                output.writeInt32(8, this.paramStorageType);
            }
            if (this.valueType != 0) {
                output.writeInt32(9, this.valueType);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (!this.name.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.name);
            }
            if (this.intValue != 0) {
                size += CodedOutputByteBufferNano.computeInt64Size(2, this.intValue);
            }
            if (this.boolValue) {
                size += CodedOutputByteBufferNano.computeBoolSize(3, this.boolValue);
            }
            if (Double.doubleToLongBits(this.float64Value) != Double.doubleToLongBits(0.0d)) {
                size += CodedOutputByteBufferNano.computeDoubleSize(4, this.float64Value);
            }
            if (!this.stringValue.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(5, this.stringValue);
            }
            if (this.extensionValue != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(6, this.extensionValue);
            }
            if (this.deleted) {
                size += CodedOutputByteBufferNano.computeBoolSize(7, this.deleted);
            }
            if (this.paramStorageType != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(8, this.paramStorageType);
            }
            if (this.valueType != 0) {
                return size + CodedOutputByteBufferNano.computeInt32Size(9, this.valueType);
            }
            return size;
        }

        public Param mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                int value;
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        this.name = input.readString();
                        continue;
                    case LogSource.GMS_CORE_PEOPLE /*16*/:
                        this.intValue = input.readInt64();
                        continue;
                    case LogSource.LB_C /*24*/:
                        this.boolValue = input.readBool();
                        continue;
                    case LogSource.INSTORE_WALLET /*33*/:
                        this.float64Value = input.readDouble();
                        continue;
                    case LogSource.PHOTOS /*42*/:
                        this.stringValue = input.readString();
                        continue;
                    case LogSource.HANGOUT_LOG_REQUEST /*50*/:
                        if (this.extensionValue == null) {
                            this.extensionValue = new ExtensionValue();
                        }
                        input.readMessage(this.extensionValue);
                        continue;
                    case LogSource.DOCS /*56*/:
                        this.deleted = input.readBool();
                        continue;
                    case LogSource.KIDS_COMMUNICATOR /*64*/:
                        value = input.readInt32();
                        switch (value) {
                            case Action.UNKNOWN /*0*/:
                            case TimeSelection.Type.TEMPORARY /*1*/:
                                this.paramStorageType = value;
                                break;
                            default:
                                continue;
                        }
                    case LogSource.JUSTSPEAK /*72*/:
                        value = input.readInt32();
                        switch (value) {
                            case Action.UNKNOWN /*0*/:
                            case TimeSelection.Type.TEMPORARY /*1*/:
                            case TimeSelection.Type.INDEFINITELY /*2*/:
                            case TimeSelection.Type.CUSTOM /*3*/:
                            case OvenFreshResult.NO_ACCOUNTS /*4*/:
                            case Type.ADD_NEW_SHARES /*5*/:
                                this.valueType = value;
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

        public static Param parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (Param) MessageNano.mergeFrom(new Param(), data);
        }

        public static Param parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new Param().mergeFrom(input);
        }
    }

    public static final class ParamPartition extends ExtendableMessageNano<ParamPartition> {
        private static volatile ParamPartition[] _emptyArray;
        public Param[] param;
        public ParamPartitionTag paramPartitionTag;
        public int updateType;

        public interface UpdateType {
            public static final int DELETE = 2;
            public static final int PATCH = 1;
            public static final int REPLACE = 0;
        }

        public static ParamPartition[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new ParamPartition[0];
                    }
                }
            }
            return _emptyArray;
        }

        public ParamPartition() {
            clear();
        }

        public ParamPartition clear() {
            this.paramPartitionTag = null;
            this.param = Param.emptyArray();
            this.updateType = 0;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof ParamPartition)) {
                return false;
            }
            ParamPartition other = (ParamPartition) o;
            if (this.paramPartitionTag == null) {
                if (other.paramPartitionTag != null) {
                    return false;
                }
            } else if (!this.paramPartitionTag.equals(other.paramPartitionTag)) {
                return false;
            }
            if (!InternalNano.equals(this.param, other.param) || this.updateType != other.updateType) {
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
            int hashCode = (((((((getClass().getName().hashCode() + 527) * 31) + (this.paramPartitionTag == null ? 0 : this.paramPartitionTag.hashCode())) * 31) + InternalNano.hashCode(this.param)) * 31) + this.updateType) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.paramPartitionTag != null) {
                output.writeMessage(1, this.paramPartitionTag);
            }
            if (this.param != null && this.param.length > 0) {
                for (Param element : this.param) {
                    if (element != null) {
                        output.writeMessage(2, element);
                    }
                }
            }
            if (this.updateType != 0) {
                output.writeInt32(3, this.updateType);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.paramPartitionTag != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(1, this.paramPartitionTag);
            }
            if (this.param != null && this.param.length > 0) {
                for (Param element : this.param) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(2, element);
                    }
                }
            }
            if (this.updateType != 0) {
                return size + CodedOutputByteBufferNano.computeInt32Size(3, this.updateType);
            }
            return size;
        }

        public ParamPartition mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        if (this.paramPartitionTag == null) {
                            this.paramPartitionTag = new ParamPartitionTag();
                        }
                        input.readMessage(this.paramPartitionTag);
                        continue;
                    case LogSource.GOOGLE_ANALYTICS /*18*/:
                        int i;
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 18);
                        if (this.param == null) {
                            i = 0;
                        } else {
                            i = this.param.length;
                        }
                        Param[] newArray = new Param[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.param, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new Param();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i++;
                        }
                        newArray[i] = new Param();
                        input.readMessage(newArray[i]);
                        this.param = newArray;
                        continue;
                    case LogSource.LB_C /*24*/:
                        int value = input.readInt32();
                        switch (value) {
                            case Action.UNKNOWN /*0*/:
                            case TimeSelection.Type.TEMPORARY /*1*/:
                            case TimeSelection.Type.INDEFINITELY /*2*/:
                                this.updateType = value;
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

        public static ParamPartition parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (ParamPartition) MessageNano.mergeFrom(new ParamPartition(), data);
        }

        public static ParamPartition parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new ParamPartition().mergeFrom(input);
        }
    }

    public static final class ParamPartitionTag extends ExtendableMessageNano<ParamPartitionTag> {
        private static volatile ParamPartitionTag[] _emptyArray;
        public long id;
        public byte[] tag;

        public static ParamPartitionTag[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new ParamPartitionTag[0];
                    }
                }
            }
            return _emptyArray;
        }

        public ParamPartitionTag() {
            clear();
        }

        public ParamPartitionTag clear() {
            this.id = 0;
            this.tag = WireFormatNano.EMPTY_BYTES;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof ParamPartitionTag)) {
                return false;
            }
            ParamPartitionTag other = (ParamPartitionTag) o;
            if (this.id != other.id || !Arrays.equals(this.tag, other.tag)) {
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
            int hashCode = (((((getClass().getName().hashCode() + 527) * 31) + ((int) (this.id ^ (this.id >>> 32)))) * 31) + Arrays.hashCode(this.tag)) * 31;
            int hashCode2 = (this.unknownFieldData == null || this.unknownFieldData.isEmpty()) ? 0 : this.unknownFieldData.hashCode();
            return hashCode + hashCode2;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.id != 0) {
                output.writeInt64(1, this.id);
            }
            if (!Arrays.equals(this.tag, WireFormatNano.EMPTY_BYTES)) {
                output.writeBytes(2, this.tag);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.id != 0) {
                size += CodedOutputByteBufferNano.computeInt64Size(1, this.id);
            }
            if (Arrays.equals(this.tag, WireFormatNano.EMPTY_BYTES)) {
                return size;
            }
            return size + CodedOutputByteBufferNano.computeBytesSize(2, this.tag);
        }

        public ParamPartitionTag mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_GET_LINK /*8*/:
                        this.id = input.readInt64();
                        continue;
                    case LogSource.GOOGLE_ANALYTICS /*18*/:
                        this.tag = input.readBytes();
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

        public static ParamPartitionTag parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (ParamPartitionTag) MessageNano.mergeFrom(new ParamPartitionTag(), data);
        }

        public static ParamPartitionTag parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new ParamPartitionTag().mergeFrom(input);
        }
    }

    public static final class Scope extends ExtendableMessageNano<Scope> {
        private static volatile Scope[] _emptyArray;
        public String configPackage;
        public long versionCode;

        public static Scope[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new Scope[0];
                    }
                }
            }
            return _emptyArray;
        }

        public Scope() {
            clear();
        }

        public Scope clear() {
            this.configPackage = BuildConfig.VERSION_NAME;
            this.versionCode = 0;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof Scope)) {
                return false;
            }
            Scope other = (Scope) o;
            if (this.configPackage == null) {
                if (other.configPackage != null) {
                    return false;
                }
            } else if (!this.configPackage.equals(other.configPackage)) {
                return false;
            }
            if (this.versionCode != other.versionCode) {
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
            int hashCode = (((((getClass().getName().hashCode() + 527) * 31) + (this.configPackage == null ? 0 : this.configPackage.hashCode())) * 31) + ((int) (this.versionCode ^ (this.versionCode >>> 32)))) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (!this.configPackage.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(1, this.configPackage);
            }
            if (this.versionCode != 0) {
                output.writeInt64(2, this.versionCode);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (!this.configPackage.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.configPackage);
            }
            if (this.versionCode != 0) {
                return size + CodedOutputByteBufferNano.computeInt64Size(2, this.versionCode);
            }
            return size;
        }

        public Scope mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        this.configPackage = input.readString();
                        continue;
                    case LogSource.GMS_CORE_PEOPLE /*16*/:
                        this.versionCode = input.readInt64();
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

        public static Scope parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (Scope) MessageNano.mergeFrom(new Scope(), data);
        }

        public static Scope parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new Scope().mergeFrom(input);
        }
    }
}
