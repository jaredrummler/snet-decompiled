package com.google.location.unified;

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

public interface LocationDescriptorProto {

    public static final class FeatureIdProto extends ExtendableMessageNano<FeatureIdProto> {
        private static volatile FeatureIdProto[] _emptyArray;
        public long cellId;
        public long fprint;

        public static FeatureIdProto[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new FeatureIdProto[0];
                    }
                }
            }
            return _emptyArray;
        }

        public FeatureIdProto() {
            clear();
        }

        public FeatureIdProto clear() {
            this.cellId = 0;
            this.fprint = 0;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof FeatureIdProto)) {
                return false;
            }
            FeatureIdProto other = (FeatureIdProto) o;
            if (this.cellId != other.cellId || this.fprint != other.fprint) {
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
            int hashCode = (((((getClass().getName().hashCode() + 527) * 31) + ((int) (this.cellId ^ (this.cellId >>> 32)))) * 31) + ((int) (this.fprint ^ (this.fprint >>> 32)))) * 31;
            int hashCode2 = (this.unknownFieldData == null || this.unknownFieldData.isEmpty()) ? 0 : this.unknownFieldData.hashCode();
            return hashCode + hashCode2;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.cellId != 0) {
                output.writeFixed64(1, this.cellId);
            }
            if (this.fprint != 0) {
                output.writeFixed64(2, this.fprint);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.cellId != 0) {
                size += CodedOutputByteBufferNano.computeFixed64Size(1, this.cellId);
            }
            if (this.fprint != 0) {
                return size + CodedOutputByteBufferNano.computeFixed64Size(2, this.fprint);
            }
            return size;
        }

        public FeatureIdProto mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.CREATE_LINK /*9*/:
                        this.cellId = input.readFixed64();
                        continue;
                    case LogSource.LE /*17*/:
                        this.fprint = input.readFixed64();
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

        public static FeatureIdProto parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (FeatureIdProto) MessageNano.mergeFrom(new FeatureIdProto(), data);
        }

        public static FeatureIdProto parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new FeatureIdProto().mergeFrom(input);
        }
    }

    public static final class FieldOfView extends ExtendableMessageNano<FieldOfView> {
        private static volatile FieldOfView[] _emptyArray;
        public float fieldOfViewXDegrees;
        public float fieldOfViewYDegrees;
        public int screenWidthPixels;

        public static FieldOfView[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new FieldOfView[0];
                    }
                }
            }
            return _emptyArray;
        }

        public FieldOfView() {
            clear();
        }

        public FieldOfView clear() {
            this.fieldOfViewXDegrees = 0.0f;
            this.fieldOfViewYDegrees = 0.0f;
            this.screenWidthPixels = 0;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof FieldOfView)) {
                return false;
            }
            FieldOfView other = (FieldOfView) o;
            if (Float.floatToIntBits(this.fieldOfViewXDegrees) != Float.floatToIntBits(other.fieldOfViewXDegrees) || Float.floatToIntBits(this.fieldOfViewYDegrees) != Float.floatToIntBits(other.fieldOfViewYDegrees) || this.screenWidthPixels != other.screenWidthPixels) {
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
            int hashCode = (((((((getClass().getName().hashCode() + 527) * 31) + Float.floatToIntBits(this.fieldOfViewXDegrees)) * 31) + Float.floatToIntBits(this.fieldOfViewYDegrees)) * 31) + this.screenWidthPixels) * 31;
            int hashCode2 = (this.unknownFieldData == null || this.unknownFieldData.isEmpty()) ? 0 : this.unknownFieldData.hashCode();
            return hashCode + hashCode2;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (Float.floatToIntBits(this.fieldOfViewXDegrees) != Float.floatToIntBits(0.0f)) {
                output.writeFloat(1, this.fieldOfViewXDegrees);
            }
            if (Float.floatToIntBits(this.fieldOfViewYDegrees) != Float.floatToIntBits(0.0f)) {
                output.writeFloat(2, this.fieldOfViewYDegrees);
            }
            if (this.screenWidthPixels != 0) {
                output.writeInt32(3, this.screenWidthPixels);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (Float.floatToIntBits(this.fieldOfViewXDegrees) != Float.floatToIntBits(0.0f)) {
                size += CodedOutputByteBufferNano.computeFloatSize(1, this.fieldOfViewXDegrees);
            }
            if (Float.floatToIntBits(this.fieldOfViewYDegrees) != Float.floatToIntBits(0.0f)) {
                size += CodedOutputByteBufferNano.computeFloatSize(2, this.fieldOfViewYDegrees);
            }
            if (this.screenWidthPixels != 0) {
                return size + CodedOutputByteBufferNano.computeInt32Size(3, this.screenWidthPixels);
            }
            return size;
        }

        public FieldOfView mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.DISPLAY_ERROR /*13*/:
                        this.fieldOfViewXDegrees = input.readFloat();
                        continue;
                    case LogSource.LB_T /*21*/:
                        this.fieldOfViewYDegrees = input.readFloat();
                        continue;
                    case LogSource.LB_C /*24*/:
                        this.screenWidthPixels = input.readInt32();
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

        public static FieldOfView parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (FieldOfView) MessageNano.mergeFrom(new FieldOfView(), data);
        }

        public static FieldOfView parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new FieldOfView().mergeFrom(input);
        }
    }

    public static final class LatLng extends ExtendableMessageNano<LatLng> {
        private static volatile LatLng[] _emptyArray;
        public int latitudeE7;
        public int longitudeE7;

        public static LatLng[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new LatLng[0];
                    }
                }
            }
            return _emptyArray;
        }

        public LatLng() {
            clear();
        }

        public LatLng clear() {
            this.latitudeE7 = 0;
            this.longitudeE7 = 0;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof LatLng)) {
                return false;
            }
            LatLng other = (LatLng) o;
            if (this.latitudeE7 != other.latitudeE7 || this.longitudeE7 != other.longitudeE7) {
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
            int hashCode = (((((getClass().getName().hashCode() + 527) * 31) + this.latitudeE7) * 31) + this.longitudeE7) * 31;
            int hashCode2 = (this.unknownFieldData == null || this.unknownFieldData.isEmpty()) ? 0 : this.unknownFieldData.hashCode();
            return hashCode + hashCode2;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.latitudeE7 != 0) {
                output.writeSFixed32(1, this.latitudeE7);
            }
            if (this.longitudeE7 != 0) {
                output.writeSFixed32(2, this.longitudeE7);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.latitudeE7 != 0) {
                size += CodedOutputByteBufferNano.computeSFixed32Size(1, this.latitudeE7);
            }
            if (this.longitudeE7 != 0) {
                return size + CodedOutputByteBufferNano.computeSFixed32Size(2, this.longitudeE7);
            }
            return size;
        }

        public LatLng mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.DISPLAY_ERROR /*13*/:
                        this.latitudeE7 = input.readSFixed32();
                        continue;
                    case LogSource.LB_T /*21*/:
                        this.longitudeE7 = input.readSFixed32();
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

        public static LatLng parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (LatLng) MessageNano.mergeFrom(new LatLng(), data);
        }

        public static LatLng parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new LatLng().mergeFrom(input);
        }
    }

    public static final class LatLngRect extends ExtendableMessageNano<LatLngRect> {
        private static volatile LatLngRect[] _emptyArray;
        public LatLng hi;
        public LatLng lo;

        public static LatLngRect[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new LatLngRect[0];
                    }
                }
            }
            return _emptyArray;
        }

        public LatLngRect() {
            clear();
        }

        public LatLngRect clear() {
            this.lo = null;
            this.hi = null;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof LatLngRect)) {
                return false;
            }
            LatLngRect other = (LatLngRect) o;
            if (this.lo == null) {
                if (other.lo != null) {
                    return false;
                }
            } else if (!this.lo.equals(other.lo)) {
                return false;
            }
            if (this.hi == null) {
                if (other.hi != null) {
                    return false;
                }
            } else if (!this.hi.equals(other.hi)) {
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
            int hashCode = (((((getClass().getName().hashCode() + 527) * 31) + (this.lo == null ? 0 : this.lo.hashCode())) * 31) + (this.hi == null ? 0 : this.hi.hashCode())) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.lo != null) {
                output.writeMessage(1, this.lo);
            }
            if (this.hi != null) {
                output.writeMessage(2, this.hi);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.lo != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(1, this.lo);
            }
            if (this.hi != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(2, this.hi);
            }
            return size;
        }

        public LatLngRect mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        if (this.lo == null) {
                            this.lo = new LatLng();
                        }
                        input.readMessage(this.lo);
                        continue;
                    case LogSource.GOOGLE_ANALYTICS /*18*/:
                        if (this.hi == null) {
                            this.hi = new LatLng();
                        }
                        input.readMessage(this.hi);
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

        public static LatLngRect parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (LatLngRect) MessageNano.mergeFrom(new LatLngRect(), data);
        }

        public static LatLngRect parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new LatLngRect().mergeFrom(input);
        }
    }

    public static final class LocationAttributesProto extends ExtendableMessageNano<LocationAttributesProto> {
        private static volatile LocationAttributesProto[] _emptyArray;
        public double altitudeMetersFromGround;
        public int bearingDegrees;
        public int detectedActivity;
        public FieldOfView fieldOfView;
        public int headingDegrees;
        public int rollDegrees;
        public int speedKph;
        public int tiltDegrees;

        public interface ActivityType {
            public static final int ACTIVITY_TYPE_ACTIVITY_IN_VEHICLE = 2;
            public static final int ACTIVITY_TYPE_ACTIVITY_ON_BICYCLE = 3;
            public static final int ACTIVITY_TYPE_ACTIVITY_ON_FOOT = 4;
            public static final int ACTIVITY_TYPE_ACTIVITY_STILL = 1;
            public static final int ACTIVITY_TYPE_ACTIVITY_UNKNOWN = 0;
        }

        public static LocationAttributesProto[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new LocationAttributesProto[0];
                    }
                }
            }
            return _emptyArray;
        }

        public LocationAttributesProto() {
            clear();
        }

        public LocationAttributesProto clear() {
            this.detectedActivity = 0;
            this.headingDegrees = 0;
            this.bearingDegrees = 0;
            this.speedKph = 0;
            this.tiltDegrees = 0;
            this.rollDegrees = 0;
            this.altitudeMetersFromGround = 0.0d;
            this.fieldOfView = null;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof LocationAttributesProto)) {
                return false;
            }
            LocationAttributesProto other = (LocationAttributesProto) o;
            if (this.detectedActivity != other.detectedActivity || this.headingDegrees != other.headingDegrees || this.bearingDegrees != other.bearingDegrees || this.speedKph != other.speedKph || this.tiltDegrees != other.tiltDegrees || this.rollDegrees != other.rollDegrees || Double.doubleToLongBits(this.altitudeMetersFromGround) != Double.doubleToLongBits(other.altitudeMetersFromGround)) {
                return false;
            }
            if (this.fieldOfView == null) {
                if (other.fieldOfView != null) {
                    return false;
                }
            } else if (!this.fieldOfView.equals(other.fieldOfView)) {
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
            int result = ((((((((((((getClass().getName().hashCode() + 527) * 31) + this.detectedActivity) * 31) + this.headingDegrees) * 31) + this.bearingDegrees) * 31) + this.speedKph) * 31) + this.tiltDegrees) * 31) + this.rollDegrees;
            long v = Double.doubleToLongBits(this.altitudeMetersFromGround);
            int hashCode = ((((result * 31) + ((int) ((v >>> 32) ^ v))) * 31) + (this.fieldOfView == null ? 0 : this.fieldOfView.hashCode())) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.detectedActivity != 0) {
                output.writeInt32(1, this.detectedActivity);
            }
            if (this.headingDegrees != 0) {
                output.writeInt32(2, this.headingDegrees);
            }
            if (this.bearingDegrees != 0) {
                output.writeInt32(3, this.bearingDegrees);
            }
            if (this.speedKph != 0) {
                output.writeInt32(4, this.speedKph);
            }
            if (this.tiltDegrees != 0) {
                output.writeInt32(5, this.tiltDegrees);
            }
            if (this.rollDegrees != 0) {
                output.writeInt32(6, this.rollDegrees);
            }
            if (Double.doubleToLongBits(this.altitudeMetersFromGround) != Double.doubleToLongBits(0.0d)) {
                output.writeDouble(7, this.altitudeMetersFromGround);
            }
            if (this.fieldOfView != null) {
                output.writeMessage(8, this.fieldOfView);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.detectedActivity != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(1, this.detectedActivity);
            }
            if (this.headingDegrees != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(2, this.headingDegrees);
            }
            if (this.bearingDegrees != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(3, this.bearingDegrees);
            }
            if (this.speedKph != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(4, this.speedKph);
            }
            if (this.tiltDegrees != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(5, this.tiltDegrees);
            }
            if (this.rollDegrees != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(6, this.rollDegrees);
            }
            if (Double.doubleToLongBits(this.altitudeMetersFromGround) != Double.doubleToLongBits(0.0d)) {
                size += CodedOutputByteBufferNano.computeDoubleSize(7, this.altitudeMetersFromGround);
            }
            if (this.fieldOfView != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(8, this.fieldOfView);
            }
            return size;
        }

        public LocationAttributesProto mergeFrom(CodedInputByteBufferNano input) throws IOException {
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
                                this.detectedActivity = value;
                                break;
                            default:
                                continue;
                        }
                    case LogSource.GMS_CORE_PEOPLE /*16*/:
                        this.headingDegrees = input.readInt32();
                        continue;
                    case LogSource.LB_C /*24*/:
                        this.bearingDegrees = input.readInt32();
                        continue;
                    case LogSource.SOCIAL /*32*/:
                        this.speedKph = input.readInt32();
                        continue;
                    case LogSource.COPRESENCE /*40*/:
                        this.tiltDegrees = input.readInt32();
                        continue;
                    case LogSource.BACKDROP /*48*/:
                        this.rollDegrees = input.readInt32();
                        continue;
                    case LogSource.SHEETS /*57*/:
                        this.altitudeMetersFromGround = input.readDouble();
                        continue;
                    case LogSource.WIFI_ASSISTANT /*66*/:
                        if (this.fieldOfView == null) {
                            this.fieldOfView = new FieldOfView();
                        }
                        input.readMessage(this.fieldOfView);
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

        public static LocationAttributesProto parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (LocationAttributesProto) MessageNano.mergeFrom(new LocationAttributesProto(), data);
        }

        public static LocationAttributesProto parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new LocationAttributesProto().mergeFrom(input);
        }
    }

    public static final class LocationDescriptor extends ExtendableMessageNano<LocationDescriptor> {
        private static volatile LocationDescriptor[] _emptyArray;
        public LocationAttributesProto attributes;
        public int confidence;
        public String diagnosticInfo;
        public FeatureIdProto featureId;
        public int historicalProducer;
        public int historicalProminence;
        public int historicalRole;
        public String language;
        public LatLng latlng;
        public LatLng latlngSpan;
        public FeatureIdProto levelFeatureId;
        public float levelNumber;
        public String loc;
        public long mid;
        public int producer;
        public int provenance;
        public float radius;
        public LatLngRect rect;
        public int role;
        public int[] semantic;
        public long timestamp;

        public static LocationDescriptor[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new LocationDescriptor[0];
                    }
                }
            }
            return _emptyArray;
        }

        public LocationDescriptor() {
            clear();
        }

        public LocationDescriptor clear() {
            this.role = 0;
            this.producer = 0;
            this.timestamp = 0;
            this.loc = BuildConfig.VERSION_NAME;
            this.latlng = null;
            this.latlngSpan = null;
            this.rect = null;
            this.radius = 0.0f;
            this.confidence = 100;
            this.featureId = null;
            this.mid = 0;
            this.levelFeatureId = null;
            this.levelNumber = 0.0f;
            this.language = BuildConfig.VERSION_NAME;
            this.provenance = 0;
            this.historicalRole = 0;
            this.historicalProducer = 0;
            this.historicalProminence = 0;
            this.attributes = null;
            this.diagnosticInfo = BuildConfig.VERSION_NAME;
            this.semantic = WireFormatNano.EMPTY_INT_ARRAY;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof LocationDescriptor)) {
                return false;
            }
            LocationDescriptor other = (LocationDescriptor) o;
            if (this.role != other.role || this.producer != other.producer || this.timestamp != other.timestamp) {
                return false;
            }
            if (this.loc == null) {
                if (other.loc != null) {
                    return false;
                }
            } else if (!this.loc.equals(other.loc)) {
                return false;
            }
            if (this.latlng == null) {
                if (other.latlng != null) {
                    return false;
                }
            } else if (!this.latlng.equals(other.latlng)) {
                return false;
            }
            if (this.latlngSpan == null) {
                if (other.latlngSpan != null) {
                    return false;
                }
            } else if (!this.latlngSpan.equals(other.latlngSpan)) {
                return false;
            }
            if (this.rect == null) {
                if (other.rect != null) {
                    return false;
                }
            } else if (!this.rect.equals(other.rect)) {
                return false;
            }
            if (Float.floatToIntBits(this.radius) != Float.floatToIntBits(other.radius) || this.confidence != other.confidence) {
                return false;
            }
            if (this.featureId == null) {
                if (other.featureId != null) {
                    return false;
                }
            } else if (!this.featureId.equals(other.featureId)) {
                return false;
            }
            if (this.mid != other.mid) {
                return false;
            }
            if (this.levelFeatureId == null) {
                if (other.levelFeatureId != null) {
                    return false;
                }
            } else if (!this.levelFeatureId.equals(other.levelFeatureId)) {
                return false;
            }
            if (Float.floatToIntBits(this.levelNumber) != Float.floatToIntBits(other.levelNumber)) {
                return false;
            }
            if (this.language == null) {
                if (other.language != null) {
                    return false;
                }
            } else if (!this.language.equals(other.language)) {
                return false;
            }
            if (this.provenance != other.provenance || this.historicalRole != other.historicalRole || this.historicalProducer != other.historicalProducer || this.historicalProminence != other.historicalProminence) {
                return false;
            }
            if (this.attributes == null) {
                if (other.attributes != null) {
                    return false;
                }
            } else if (!this.attributes.equals(other.attributes)) {
                return false;
            }
            if (this.diagnosticInfo == null) {
                if (other.diagnosticInfo != null) {
                    return false;
                }
            } else if (!this.diagnosticInfo.equals(other.diagnosticInfo)) {
                return false;
            }
            if (!InternalNano.equals(this.semantic, other.semantic)) {
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
            int hashCode = (((((((((((((((((((((((((((((((((((((((((((getClass().getName().hashCode() + 527) * 31) + this.role) * 31) + this.producer) * 31) + ((int) (this.timestamp ^ (this.timestamp >>> 32)))) * 31) + (this.loc == null ? 0 : this.loc.hashCode())) * 31) + (this.latlng == null ? 0 : this.latlng.hashCode())) * 31) + (this.latlngSpan == null ? 0 : this.latlngSpan.hashCode())) * 31) + (this.rect == null ? 0 : this.rect.hashCode())) * 31) + Float.floatToIntBits(this.radius)) * 31) + this.confidence) * 31) + (this.featureId == null ? 0 : this.featureId.hashCode())) * 31) + ((int) (this.mid ^ (this.mid >>> 32)))) * 31) + (this.levelFeatureId == null ? 0 : this.levelFeatureId.hashCode())) * 31) + Float.floatToIntBits(this.levelNumber)) * 31) + (this.language == null ? 0 : this.language.hashCode())) * 31) + this.provenance) * 31) + this.historicalRole) * 31) + this.historicalProducer) * 31) + this.historicalProminence) * 31) + (this.attributes == null ? 0 : this.attributes.hashCode())) * 31) + (this.diagnosticInfo == null ? 0 : this.diagnosticInfo.hashCode())) * 31) + InternalNano.hashCode(this.semantic)) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.role != 0) {
                output.writeInt32(1, this.role);
            }
            if (this.producer != 0) {
                output.writeInt32(2, this.producer);
            }
            if (this.timestamp != 0) {
                output.writeInt64(3, this.timestamp);
            }
            if (!this.loc.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(4, this.loc);
            }
            if (this.latlng != null) {
                output.writeMessage(5, this.latlng);
            }
            if (this.latlngSpan != null) {
                output.writeMessage(6, this.latlngSpan);
            }
            if (Float.floatToIntBits(this.radius) != Float.floatToIntBits(0.0f)) {
                output.writeFloat(7, this.radius);
            }
            if (this.confidence != 100) {
                output.writeInt32(8, this.confidence);
            }
            if (this.provenance != 0) {
                output.writeInt32(9, this.provenance);
            }
            if (this.featureId != null) {
                output.writeMessage(10, this.featureId);
            }
            if (!this.language.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(11, this.language);
            }
            if (this.historicalRole != 0) {
                output.writeInt32(12, this.historicalRole);
            }
            if (this.historicalProducer != 0) {
                output.writeInt32(13, this.historicalProducer);
            }
            if (this.rect != null) {
                output.writeMessage(14, this.rect);
            }
            if (this.historicalProminence != 0) {
                output.writeInt32(15, this.historicalProminence);
            }
            if (this.mid != 0) {
                output.writeUInt64(16, this.mid);
            }
            if (this.levelFeatureId != null) {
                output.writeMessage(17, this.levelFeatureId);
            }
            if (Float.floatToIntBits(this.levelNumber) != Float.floatToIntBits(0.0f)) {
                output.writeFloat(18, this.levelNumber);
            }
            if (this.attributes != null) {
                output.writeMessage(19, this.attributes);
            }
            if (!this.diagnosticInfo.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(20, this.diagnosticInfo);
            }
            if (this.semantic != null && this.semantic.length > 0) {
                for (int writeInt32 : this.semantic) {
                    output.writeInt32(21, writeInt32);
                }
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.role != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(1, this.role);
            }
            if (this.producer != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(2, this.producer);
            }
            if (this.timestamp != 0) {
                size += CodedOutputByteBufferNano.computeInt64Size(3, this.timestamp);
            }
            if (!this.loc.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(4, this.loc);
            }
            if (this.latlng != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(5, this.latlng);
            }
            if (this.latlngSpan != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(6, this.latlngSpan);
            }
            if (Float.floatToIntBits(this.radius) != Float.floatToIntBits(0.0f)) {
                size += CodedOutputByteBufferNano.computeFloatSize(7, this.radius);
            }
            if (this.confidence != 100) {
                size += CodedOutputByteBufferNano.computeInt32Size(8, this.confidence);
            }
            if (this.provenance != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(9, this.provenance);
            }
            if (this.featureId != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(10, this.featureId);
            }
            if (!this.language.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(11, this.language);
            }
            if (this.historicalRole != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(12, this.historicalRole);
            }
            if (this.historicalProducer != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(13, this.historicalProducer);
            }
            if (this.rect != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(14, this.rect);
            }
            if (this.historicalProminence != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(15, this.historicalProminence);
            }
            if (this.mid != 0) {
                size += CodedOutputByteBufferNano.computeUInt64Size(16, this.mid);
            }
            if (this.levelFeatureId != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(17, this.levelFeatureId);
            }
            if (Float.floatToIntBits(this.levelNumber) != Float.floatToIntBits(0.0f)) {
                size += CodedOutputByteBufferNano.computeFloatSize(18, this.levelNumber);
            }
            if (this.attributes != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(19, this.attributes);
            }
            if (!this.diagnosticInfo.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(20, this.diagnosticInfo);
            }
            if (this.semantic == null || this.semantic.length <= 0) {
                return size;
            }
            int dataSize = 0;
            for (int element : this.semantic) {
                dataSize += CodedOutputByteBufferNano.computeInt32SizeNoTag(element);
            }
            return (size + dataSize) + (this.semantic.length * 2);
        }

        public LocationDescriptor mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                int value;
                int i;
                int[] newArray;
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_GET_LINK /*8*/:
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
                                this.role = value;
                                break;
                            default:
                                continue;
                        }
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
                                this.producer = value;
                                break;
                            default:
                                continue;
                        }
                    case LogSource.LB_C /*24*/:
                        this.timestamp = input.readInt64();
                        continue;
                    case LogSource.NOVA /*34*/:
                        this.loc = input.readString();
                        continue;
                    case LogSource.PHOTOS /*42*/:
                        if (this.latlng == null) {
                            this.latlng = new LatLng();
                        }
                        input.readMessage(this.latlng);
                        continue;
                    case LogSource.HANGOUT_LOG_REQUEST /*50*/:
                        if (this.latlngSpan == null) {
                            this.latlngSpan = new LatLng();
                        }
                        input.readMessage(this.latlngSpan);
                        continue;
                    case LogSource.NFC_PROGRAMMER /*61*/:
                        this.radius = input.readFloat();
                        continue;
                    case LogSource.KIDS_COMMUNICATOR /*64*/:
                        this.confidence = input.readInt32();
                        continue;
                    case LogSource.JUSTSPEAK /*72*/:
                        value = input.readInt32();
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
                                this.provenance = value;
                                break;
                            default:
                                continue;
                        }
                    case LogSource.GOOGLE_EXPRESS /*82*/:
                        if (this.featureId == null) {
                            this.featureId = new FeatureIdProto();
                        }
                        input.readMessage(this.featureId);
                        continue;
                    case LogSource.TRON_COUNTERS /*90*/:
                        this.language = input.readString();
                        continue;
                    case LogSource.GCM_COUNTERS /*96*/:
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
                                this.historicalRole = value;
                                break;
                            default:
                                continue;
                        }
                    case LogSource.MOBILESDK_CLIENT /*104*/:
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
                                this.historicalProducer = value;
                                break;
                            default:
                                continue;
                        }
                    case LogSource.TRANSOM /*114*/:
                        if (this.rect == null) {
                            this.rect = new LatLngRect();
                        }
                        input.readMessage(this.rect);
                        continue;
                    case LogSource.FLYDROID /*120*/:
                        this.historicalProminence = input.readInt32();
                        continue;
                    case LogSource.KEEP /*128*/:
                        this.mid = input.readUInt64();
                        continue;
                    case LogSource.PANCETTA_MOBILE_HOST /*138*/:
                        if (this.levelFeatureId == null) {
                            this.levelFeatureId = new FeatureIdProto();
                        }
                        input.readMessage(this.levelFeatureId);
                        continue;
                    case 149:
                        this.levelNumber = input.readFloat();
                        continue;
                    case 154:
                        if (this.attributes == null) {
                            this.attributes = new LocationAttributesProto();
                        }
                        input.readMessage(this.attributes);
                        continue;
                    case 162:
                        this.diagnosticInfo = input.readString();
                        continue;
                    case 168:
                        int length = WireFormatNano.getRepeatedFieldArrayLength(input, 168);
                        int[] validValues = new int[length];
                        i = 0;
                        int validCount = 0;
                        while (i < length) {
                            int validCount2;
                            if (i != 0) {
                                input.readTag();
                            }
                            value = input.readInt32();
                            switch (value) {
                                case Action.UNKNOWN /*0*/:
                                case TimeSelection.Type.TEMPORARY /*1*/:
                                case TimeSelection.Type.INDEFINITELY /*2*/:
                                case TimeSelection.Type.CUSTOM /*3*/:
                                case OvenFreshResult.NO_ACCOUNTS /*4*/:
                                case Type.ADD_NEW_SHARES /*5*/:
                                    validCount2 = validCount + 1;
                                    validValues[validCount] = value;
                                    break;
                                default:
                                    validCount2 = validCount;
                                    break;
                            }
                            i++;
                            validCount = validCount2;
                        }
                        if (validCount != 0) {
                            if (this.semantic == null) {
                                i = 0;
                            } else {
                                i = this.semantic.length;
                            }
                            if (i == 0) {
                                int length2 = validValues.length;
                                if (validCount == r0) {
                                    this.semantic = validValues;
                                    break;
                                }
                            }
                            newArray = new int[(i + validCount)];
                            if (i != 0) {
                                System.arraycopy(this.semantic, 0, newArray, 0, i);
                            }
                            System.arraycopy(validValues, 0, newArray, i, validCount);
                            this.semantic = newArray;
                            break;
                        }
                        continue;
                    case 170:
                        int limit = input.pushLimit(input.readRawVarint32());
                        int arrayLength = 0;
                        int startPos = input.getPosition();
                        while (input.getBytesUntilLimit() > 0) {
                            switch (input.readInt32()) {
                                case Action.UNKNOWN /*0*/:
                                case TimeSelection.Type.TEMPORARY /*1*/:
                                case TimeSelection.Type.INDEFINITELY /*2*/:
                                case TimeSelection.Type.CUSTOM /*3*/:
                                case OvenFreshResult.NO_ACCOUNTS /*4*/:
                                case Type.ADD_NEW_SHARES /*5*/:
                                    arrayLength++;
                                    break;
                                default:
                                    break;
                            }
                        }
                        if (arrayLength != 0) {
                            input.rewindToPosition(startPos);
                            if (this.semantic == null) {
                                i = 0;
                            } else {
                                i = this.semantic.length;
                            }
                            newArray = new int[(i + arrayLength)];
                            if (i != 0) {
                                System.arraycopy(this.semantic, 0, newArray, 0, i);
                            }
                            while (input.getBytesUntilLimit() > 0) {
                                value = input.readInt32();
                                switch (value) {
                                    case Action.UNKNOWN /*0*/:
                                    case TimeSelection.Type.TEMPORARY /*1*/:
                                    case TimeSelection.Type.INDEFINITELY /*2*/:
                                    case TimeSelection.Type.CUSTOM /*3*/:
                                    case OvenFreshResult.NO_ACCOUNTS /*4*/:
                                    case Type.ADD_NEW_SHARES /*5*/:
                                        int i2 = i + 1;
                                        newArray[i] = value;
                                        i = i2;
                                        break;
                                    default:
                                        break;
                                }
                            }
                            this.semantic = newArray;
                        }
                        input.popLimit(limit);
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

        public static LocationDescriptor parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (LocationDescriptor) MessageNano.mergeFrom(new LocationDescriptor(), data);
        }

        public static LocationDescriptor parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new LocationDescriptor().mergeFrom(input);
        }
    }

    public static final class LocationDescriptorSet extends ExtendableMessageNano<LocationDescriptorSet> {
        private static volatile LocationDescriptorSet[] _emptyArray;
        public LocationDescriptor[] descriptors;

        public static LocationDescriptorSet[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new LocationDescriptorSet[0];
                    }
                }
            }
            return _emptyArray;
        }

        public LocationDescriptorSet() {
            clear();
        }

        public LocationDescriptorSet clear() {
            this.descriptors = LocationDescriptor.emptyArray();
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof LocationDescriptorSet)) {
                return false;
            }
            LocationDescriptorSet other = (LocationDescriptorSet) o;
            if (!InternalNano.equals(this.descriptors, other.descriptors)) {
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
            int hashCode = (((getClass().getName().hashCode() + 527) * 31) + InternalNano.hashCode(this.descriptors)) * 31;
            int hashCode2 = (this.unknownFieldData == null || this.unknownFieldData.isEmpty()) ? 0 : this.unknownFieldData.hashCode();
            return hashCode + hashCode2;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.descriptors != null && this.descriptors.length > 0) {
                for (LocationDescriptor element : this.descriptors) {
                    if (element != null) {
                        output.writeMessage(1, element);
                    }
                }
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.descriptors != null && this.descriptors.length > 0) {
                for (LocationDescriptor element : this.descriptors) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(1, element);
                    }
                }
            }
            return size;
        }

        public LocationDescriptorSet mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        int i;
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 10);
                        if (this.descriptors == null) {
                            i = 0;
                        } else {
                            i = this.descriptors.length;
                        }
                        LocationDescriptor[] newArray = new LocationDescriptor[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.descriptors, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new LocationDescriptor();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i++;
                        }
                        newArray[i] = new LocationDescriptor();
                        input.readMessage(newArray[i]);
                        this.descriptors = newArray;
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

        public static LocationDescriptorSet parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (LocationDescriptorSet) MessageNano.mergeFrom(new LocationDescriptorSet(), data);
        }

        public static LocationDescriptorSet parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new LocationDescriptorSet().mergeFrom(input);
        }
    }

    public interface LocationProducer {
        public static final int ADS_CRITERIA_ID = 23;
        public static final int ADS_GEO_PARAM = 38;
        public static final int ADS_PARTNER_GEO_PARAM = 39;
        public static final int CALENDAR = 28;
        public static final int CARRIER_COUNTRY = 17;
        public static final int CIRCULARS_FRONTEND = 33;
        public static final int DEFAULT_LOCATION_OVERRIDE_PRODUCER = 32;
        public static final int DEVICE_LOCATION = 12;
        public static final int GAIA_LOCATION_HISTORY = 43;
        public static final int GMAIL_THEME = 26;
        public static final int GOOGLE_HOST_DOMAIN = 4;
        public static final int GWS_MOBILE_HISTORY_ZWIEBACK = 34;
        public static final int HULK_USER_PLACES_CONFIRMED = 49;
        public static final int HULK_USER_PLACES_INFERRED = 50;
        public static final int IGOOGLE = 27;
        public static final int IP_ADDRESS = 3;
        public static final int IP_ADDRESS_REALTIME = 42;
        public static final int LEGACY_GL_COOKIE = 35;
        public static final int LEGACY_GL_PARAM = 30;
        public static final int LEGACY_MOBILE_FRONTEND_GLL = 10;
        public static final int LEGACY_MOBILE_FRONTEND_NEAR = 19;
        public static final int LEGACY_NEAR_PARAM = 11;
        public static final int LEGACY_PARTNER_GL_PARAM = 31;
        public static final int LEGACY_TOOLBAR_HEADER = 9;
        public static final int LOCAL_UNIVERSAL = 8;
        public static final int LOGGED_IN_USER_SPECIFIED = 1;
        public static final int MAPS_FRONTEND = 21;
        public static final int MOBILE_APP = 24;
        public static final int MOBILE_FE_HISTORY = 14;
        public static final int MOBILE_SELECTED = 15;
        public static final int OZ_FRONTEND = 37;
        public static final int PARTNER = 16;
        public static final int PREF_L_FIELD_ADDRESS = 2;
        public static final int PRODUCT_SEARCH_FRONTEND = 22;
        public static final int QREF = 44;
        public static final int QUERY_HISTORY_INFERRED = 25;
        public static final int QUERY_LOCATION_OVERRIDE_PRODUCER = 41;
        public static final int RQUERY = 5;
        public static final int SEARCH_TOOLBELT = 13;
        public static final int SHOPPING_SEARCH_API = 36;
        public static final int SHOWTIME_ONEBOX = 7;
        public static final int SMS_SEARCH = 29;
        public static final int SNAP_TO_PLACE_EXPLICIT = 48;
        public static final int SNAP_TO_PLACE_IMPLICIT = 47;
        public static final int SQUERY = 6;
        public static final int STICKINESS_PARAMS = 45;
        public static final int TURN_BY_TURN_NAVIGATION_REROUTE = 46;
        public static final int UNKNOWN_PRODUCER = 0;
        public static final int VIEWPORT_PARAMS = 40;
        public static final int WEB_SEARCH_PREFERENCES_PAGE = 20;
        public static final int WEB_SEARCH_RESULTS_PAGE_SHARED = 18;
        public static final int WILDCARD_PRODUCER = -1;
    }

    public static final class LocationProducerProto extends ExtendableMessageNano<LocationProducerProto> {
        private static volatile LocationProducerProto[] _emptyArray;

        public interface LocationProducer {
            public static final int ADS_CRITERIA_ID = 23;
            public static final int ADS_GEO_PARAM = 38;
            public static final int ADS_PARTNER_GEO_PARAM = 39;
            public static final int CALENDAR = 28;
            public static final int CARRIER_COUNTRY = 17;
            public static final int CIRCULARS_FRONTEND = 33;
            public static final int DEFAULT_LOCATION_OVERRIDE_PRODUCER = 32;
            public static final int DEVICE_LOCATION = 12;
            public static final int GAIA_LOCATION_HISTORY = 43;
            public static final int GMAIL_THEME = 26;
            public static final int GOOGLE_HOST_DOMAIN = 4;
            public static final int GWS_MOBILE_HISTORY_ZWIEBACK = 34;
            public static final int HULK_USER_PLACES_CONFIRMED = 49;
            public static final int HULK_USER_PLACES_INFERRED = 50;
            public static final int IGOOGLE = 27;
            public static final int IP_ADDRESS = 3;
            public static final int IP_ADDRESS_REALTIME = 42;
            public static final int LEGACY_GL_COOKIE = 35;
            public static final int LEGACY_GL_PARAM = 30;
            public static final int LEGACY_MOBILE_FRONTEND_GLL = 10;
            public static final int LEGACY_MOBILE_FRONTEND_NEAR = 19;
            public static final int LEGACY_NEAR_PARAM = 11;
            public static final int LEGACY_PARTNER_GL_PARAM = 31;
            public static final int LEGACY_TOOLBAR_HEADER = 9;
            public static final int LOCAL_UNIVERSAL = 8;
            public static final int LOGGED_IN_USER_SPECIFIED = 1;
            public static final int MAPS_FRONTEND = 21;
            public static final int MOBILE_APP = 24;
            public static final int MOBILE_FE_HISTORY = 14;
            public static final int MOBILE_SELECTED = 15;
            public static final int OZ_FRONTEND = 37;
            public static final int PARTNER = 16;
            public static final int PREF_L_FIELD_ADDRESS = 2;
            public static final int PRODUCT_SEARCH_FRONTEND = 22;
            public static final int QREF = 44;
            public static final int QUERY_HISTORY_INFERRED = 25;
            public static final int QUERY_LOCATION_OVERRIDE_PRODUCER = 41;
            public static final int RQUERY = 5;
            public static final int SEARCH_TOOLBELT = 13;
            public static final int SHOPPING_SEARCH_API = 36;
            public static final int SHOWTIME_ONEBOX = 7;
            public static final int SMS_SEARCH = 29;
            public static final int SNAP_TO_PLACE_EXPLICIT = 48;
            public static final int SNAP_TO_PLACE_IMPLICIT = 47;
            public static final int SQUERY = 6;
            public static final int STICKINESS_PARAMS = 45;
            public static final int TURN_BY_TURN_NAVIGATION_REROUTE = 46;
            public static final int UNKNOWN_PRODUCER = 0;
            public static final int VIEWPORT_PARAMS = 40;
            public static final int WEB_SEARCH_PREFERENCES_PAGE = 20;
            public static final int WEB_SEARCH_RESULTS_PAGE_SHARED = 18;
            public static final int WILDCARD_PRODUCER = -1;
        }

        public static LocationProducerProto[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new LocationProducerProto[0];
                    }
                }
            }
            return _emptyArray;
        }

        public LocationProducerProto() {
            clear();
        }

        public LocationProducerProto clear() {
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof LocationProducerProto)) {
                return false;
            }
            LocationProducerProto other = (LocationProducerProto) o;
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

        public LocationProducerProto mergeFrom(CodedInputByteBufferNano input) throws IOException {
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

        public static LocationProducerProto parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (LocationProducerProto) MessageNano.mergeFrom(new LocationProducerProto(), data);
        }

        public static LocationProducerProto parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new LocationProducerProto().mergeFrom(input);
        }
    }

    public interface LocationProvenance {
        public static final int LOCATION_PROVENANCE_GWS_MOBILE_CLIENT = 6;
        public static final int LOCATION_PROVENANCE_LEGACY_MOBILE_FRONTEND_GLL_PARAM = 3;
        public static final int LOCATION_PROVENANCE_LEGACY_MOBILE_FRONTEND_NEAR_PARAM = 5;
        public static final int LOCATION_PROVENANCE_MAPS_FRONTEND_IL_DEBUG_IP = 4;
        public static final int LOCATION_PROVENANCE_MOBILE_FE = 2;
        public static final int LOCATION_PROVENANCE_TOOLBAR = 1;
        public static final int LOCATION_PROVENANCE_UNREMARKABLE = 0;
        public static final int LOCATION_PROVENANCE_XFF_HEADER = 7;
        public static final int LOCATION_PROVENANCE_XGEO_HEADER = 8;
    }

    public static final class LocationProvenanceProto extends ExtendableMessageNano<LocationProvenanceProto> {
        private static volatile LocationProvenanceProto[] _emptyArray;

        public interface LocationProvenance {
            public static final int LOCATION_PROVENANCE_GWS_MOBILE_CLIENT = 6;
            public static final int LOCATION_PROVENANCE_LEGACY_MOBILE_FRONTEND_GLL_PARAM = 3;
            public static final int LOCATION_PROVENANCE_LEGACY_MOBILE_FRONTEND_NEAR_PARAM = 5;
            public static final int LOCATION_PROVENANCE_MAPS_FRONTEND_IL_DEBUG_IP = 4;
            public static final int LOCATION_PROVENANCE_MOBILE_FE = 2;
            public static final int LOCATION_PROVENANCE_TOOLBAR = 1;
            public static final int LOCATION_PROVENANCE_UNREMARKABLE = 0;
            public static final int LOCATION_PROVENANCE_XFF_HEADER = 7;
            public static final int LOCATION_PROVENANCE_XGEO_HEADER = 8;
        }

        public static LocationProvenanceProto[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new LocationProvenanceProto[0];
                    }
                }
            }
            return _emptyArray;
        }

        public LocationProvenanceProto() {
            clear();
        }

        public LocationProvenanceProto clear() {
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof LocationProvenanceProto)) {
                return false;
            }
            LocationProvenanceProto other = (LocationProvenanceProto) o;
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

        public LocationProvenanceProto mergeFrom(CodedInputByteBufferNano input) throws IOException {
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

        public static LocationProvenanceProto parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (LocationProvenanceProto) MessageNano.mergeFrom(new LocationProvenanceProto(), data);
        }

        public static LocationProvenanceProto parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new LocationProvenanceProto().mergeFrom(input);
        }
    }

    public interface LocationRole {
        public static final int CURRENT_LOCATION = 1;
        public static final int DEFAULT_LOCATION = 2;
        public static final int HISTORICAL_LOCATION = 6;
        public static final int HISTORICAL_QUERY = 5;
        public static final int QUERY = 3;
        public static final int UNKNOWN_ROLE = 0;
        public static final int USER_SPECIFIED_FOR_REQUEST = 4;
        public static final int VIEWPORT = 7;
        public static final int WILDCARD_ROLE = -1;
    }

    public static final class LocationRoleProto extends ExtendableMessageNano<LocationRoleProto> {
        private static volatile LocationRoleProto[] _emptyArray;

        public interface LocationRole {
            public static final int CURRENT_LOCATION = 1;
            public static final int DEFAULT_LOCATION = 2;
            public static final int HISTORICAL_LOCATION = 6;
            public static final int HISTORICAL_QUERY = 5;
            public static final int QUERY = 3;
            public static final int UNKNOWN_ROLE = 0;
            public static final int USER_SPECIFIED_FOR_REQUEST = 4;
            public static final int VIEWPORT = 7;
            public static final int WILDCARD_ROLE = -1;
        }

        public static LocationRoleProto[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new LocationRoleProto[0];
                    }
                }
            }
            return _emptyArray;
        }

        public LocationRoleProto() {
            clear();
        }

        public LocationRoleProto clear() {
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof LocationRoleProto)) {
                return false;
            }
            LocationRoleProto other = (LocationRoleProto) o;
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

        public LocationRoleProto mergeFrom(CodedInputByteBufferNano input) throws IOException {
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

        public static LocationRoleProto parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (LocationRoleProto) MessageNano.mergeFrom(new LocationRoleProto(), data);
        }

        public static LocationRoleProto parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new LocationRoleProto().mergeFrom(input);
        }
    }

    public interface LocationSemantic {
        public static final int LOCATION_SEMANTIC_SEMANTIC_HOME = 4;
        public static final int LOCATION_SEMANTIC_SEMANTIC_REROUTE_PROPOSED = 2;
        public static final int LOCATION_SEMANTIC_SEMANTIC_REROUTE_SOURCE = 1;
        public static final int LOCATION_SEMANTIC_SEMANTIC_REROUTE_TAKEN = 3;
        public static final int LOCATION_SEMANTIC_SEMANTIC_UNKNOWN = 0;
        public static final int LOCATION_SEMANTIC_SEMANTIC_WORK = 5;
    }
}
