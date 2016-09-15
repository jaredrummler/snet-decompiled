package com.google.kidsmanagement.v1;

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
import java.io.IOException;
import java.util.Arrays;

public interface PermissionsCommonProto {

    public interface AdditionalRecipientType {
        public static final int INVITEE = 1;
        public static final int LEAVING_MEMBER = 3;
        public static final int NEW_KID = 2;
        public static final int UNKNOWN_RECIPIENT_TYPE = 0;
    }

    public interface AutoDecideState {
        public static final int AUTO_APPROVE_ENABLED = 3;
        public static final int AUTO_DECIDE_DISABLED = 2;
        public static final int AUTO_DECIDE_NOT_SUPPORTED = 1;
        public static final int UNKNOWN_AUTO_DECIDE_STATE = 0;
    }

    public interface FamilyEventInteractionMode {
        public static final int ACTION_REQUIRED = 1;
        public static final int NO_ACTION_REQUIRED = 2;
        public static final int UNKNOWN_INTERACTION_MODE = 0;
    }

    public static final class GenericTemplateData extends ExtendableMessageNano<GenericTemplateData> {
        private static volatile GenericTemplateData[] _emptyArray;
        public String description;
        public byte[] imageData;
        public String imageUrl;
        public String summary;
        public String title;
        public String url;

        public static GenericTemplateData[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new GenericTemplateData[0];
                    }
                }
            }
            return _emptyArray;
        }

        public GenericTemplateData() {
            clear();
        }

        public GenericTemplateData clear() {
            this.title = BuildConfig.VERSION_NAME;
            this.summary = BuildConfig.VERSION_NAME;
            this.description = BuildConfig.VERSION_NAME;
            this.imageData = WireFormatNano.EMPTY_BYTES;
            this.imageUrl = BuildConfig.VERSION_NAME;
            this.url = BuildConfig.VERSION_NAME;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof GenericTemplateData)) {
                return false;
            }
            GenericTemplateData other = (GenericTemplateData) o;
            if (this.title == null) {
                if (other.title != null) {
                    return false;
                }
            } else if (!this.title.equals(other.title)) {
                return false;
            }
            if (this.summary == null) {
                if (other.summary != null) {
                    return false;
                }
            } else if (!this.summary.equals(other.summary)) {
                return false;
            }
            if (this.description == null) {
                if (other.description != null) {
                    return false;
                }
            } else if (!this.description.equals(other.description)) {
                return false;
            }
            if (!Arrays.equals(this.imageData, other.imageData)) {
                return false;
            }
            if (this.imageUrl == null) {
                if (other.imageUrl != null) {
                    return false;
                }
            } else if (!this.imageUrl.equals(other.imageUrl)) {
                return false;
            }
            if (this.url == null) {
                if (other.url != null) {
                    return false;
                }
            } else if (!this.url.equals(other.url)) {
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
            int hashCode = (((((((((((((getClass().getName().hashCode() + 527) * 31) + (this.title == null ? 0 : this.title.hashCode())) * 31) + (this.summary == null ? 0 : this.summary.hashCode())) * 31) + (this.description == null ? 0 : this.description.hashCode())) * 31) + Arrays.hashCode(this.imageData)) * 31) + (this.imageUrl == null ? 0 : this.imageUrl.hashCode())) * 31) + (this.url == null ? 0 : this.url.hashCode())) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (!this.title.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(1, this.title);
            }
            if (!this.summary.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(2, this.summary);
            }
            if (!this.description.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(3, this.description);
            }
            if (!Arrays.equals(this.imageData, WireFormatNano.EMPTY_BYTES)) {
                output.writeBytes(4, this.imageData);
            }
            if (!this.imageUrl.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(5, this.imageUrl);
            }
            if (!this.url.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(6, this.url);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (!this.title.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.title);
            }
            if (!this.summary.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(2, this.summary);
            }
            if (!this.description.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(3, this.description);
            }
            if (!Arrays.equals(this.imageData, WireFormatNano.EMPTY_BYTES)) {
                size += CodedOutputByteBufferNano.computeBytesSize(4, this.imageData);
            }
            if (!this.imageUrl.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(5, this.imageUrl);
            }
            if (this.url.equals(BuildConfig.VERSION_NAME)) {
                return size;
            }
            return size + CodedOutputByteBufferNano.computeStringSize(6, this.url);
        }

        public GenericTemplateData mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        this.title = input.readString();
                        continue;
                    case LogSource.GOOGLE_ANALYTICS /*18*/:
                        this.summary = input.readString();
                        continue;
                    case LogSource.ANDROID_CAMERA /*26*/:
                        this.description = input.readString();
                        continue;
                    case LogSource.NOVA /*34*/:
                        this.imageData = input.readBytes();
                        continue;
                    case LogSource.PHOTOS /*42*/:
                        this.imageUrl = input.readString();
                        continue;
                    case LogSource.HANGOUT_LOG_REQUEST /*50*/:
                        this.url = input.readString();
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

        public static GenericTemplateData parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (GenericTemplateData) MessageNano.mergeFrom(new GenericTemplateData(), data);
        }

        public static GenericTemplateData parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new GenericTemplateData().mergeFrom(input);
        }
    }

    public static final class InboxItemCollapsedView extends ExtendableMessageNano<InboxItemCollapsedView> {
        private static volatile InboxItemCollapsedView[] _emptyArray;
        public String description;
        public String destinationUrl;
        public InboxItemProduct product;
        public InboxItemImage productLogo;
        public String title;

        public static InboxItemCollapsedView[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new InboxItemCollapsedView[0];
                    }
                }
            }
            return _emptyArray;
        }

        public InboxItemCollapsedView() {
            clear();
        }

        public InboxItemCollapsedView clear() {
            this.title = BuildConfig.VERSION_NAME;
            this.description = BuildConfig.VERSION_NAME;
            this.productLogo = null;
            this.destinationUrl = BuildConfig.VERSION_NAME;
            this.product = null;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof InboxItemCollapsedView)) {
                return false;
            }
            InboxItemCollapsedView other = (InboxItemCollapsedView) o;
            if (this.title == null) {
                if (other.title != null) {
                    return false;
                }
            } else if (!this.title.equals(other.title)) {
                return false;
            }
            if (this.description == null) {
                if (other.description != null) {
                    return false;
                }
            } else if (!this.description.equals(other.description)) {
                return false;
            }
            if (this.productLogo == null) {
                if (other.productLogo != null) {
                    return false;
                }
            } else if (!this.productLogo.equals(other.productLogo)) {
                return false;
            }
            if (this.destinationUrl == null) {
                if (other.destinationUrl != null) {
                    return false;
                }
            } else if (!this.destinationUrl.equals(other.destinationUrl)) {
                return false;
            }
            if (this.product == null) {
                if (other.product != null) {
                    return false;
                }
            } else if (!this.product.equals(other.product)) {
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
            int hashCode = (((((((((((getClass().getName().hashCode() + 527) * 31) + (this.title == null ? 0 : this.title.hashCode())) * 31) + (this.description == null ? 0 : this.description.hashCode())) * 31) + (this.productLogo == null ? 0 : this.productLogo.hashCode())) * 31) + (this.destinationUrl == null ? 0 : this.destinationUrl.hashCode())) * 31) + (this.product == null ? 0 : this.product.hashCode())) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (!this.title.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(1, this.title);
            }
            if (!this.description.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(2, this.description);
            }
            if (this.productLogo != null) {
                output.writeMessage(3, this.productLogo);
            }
            if (!this.destinationUrl.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(4, this.destinationUrl);
            }
            if (this.product != null) {
                output.writeMessage(5, this.product);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (!this.title.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.title);
            }
            if (!this.description.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(2, this.description);
            }
            if (this.productLogo != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(3, this.productLogo);
            }
            if (!this.destinationUrl.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(4, this.destinationUrl);
            }
            if (this.product != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(5, this.product);
            }
            return size;
        }

        public InboxItemCollapsedView mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        this.title = input.readString();
                        continue;
                    case LogSource.GOOGLE_ANALYTICS /*18*/:
                        this.description = input.readString();
                        continue;
                    case LogSource.ANDROID_CAMERA /*26*/:
                        if (this.productLogo == null) {
                            this.productLogo = new InboxItemImage();
                        }
                        input.readMessage(this.productLogo);
                        continue;
                    case LogSource.NOVA /*34*/:
                        this.destinationUrl = input.readString();
                        continue;
                    case LogSource.PHOTOS /*42*/:
                        if (this.product == null) {
                            this.product = new InboxItemProduct();
                        }
                        input.readMessage(this.product);
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

        public static InboxItemCollapsedView parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (InboxItemCollapsedView) MessageNano.mergeFrom(new InboxItemCollapsedView(), data);
        }

        public static InboxItemCollapsedView parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new InboxItemCollapsedView().mergeFrom(input);
        }
    }

    public static final class InboxItemExpandedView extends ExtendableMessageNano<InboxItemExpandedView> {
        private static volatile InboxItemExpandedView[] _emptyArray;
        public GenericTemplateData genericTemplateData;

        public static InboxItemExpandedView[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new InboxItemExpandedView[0];
                    }
                }
            }
            return _emptyArray;
        }

        public InboxItemExpandedView() {
            clear();
        }

        public InboxItemExpandedView clear() {
            this.genericTemplateData = null;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof InboxItemExpandedView)) {
                return false;
            }
            InboxItemExpandedView other = (InboxItemExpandedView) o;
            if (this.genericTemplateData == null) {
                if (other.genericTemplateData != null) {
                    return false;
                }
            } else if (!this.genericTemplateData.equals(other.genericTemplateData)) {
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
            int hashCode = (((getClass().getName().hashCode() + 527) * 31) + (this.genericTemplateData == null ? 0 : this.genericTemplateData.hashCode())) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.genericTemplateData != null) {
                output.writeMessage(2, this.genericTemplateData);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.genericTemplateData != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(2, this.genericTemplateData);
            }
            return size;
        }

        public InboxItemExpandedView mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case LogSource.GOOGLE_ANALYTICS /*18*/:
                        if (this.genericTemplateData == null) {
                            this.genericTemplateData = new GenericTemplateData();
                        }
                        input.readMessage(this.genericTemplateData);
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

        public static InboxItemExpandedView parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (InboxItemExpandedView) MessageNano.mergeFrom(new InboxItemExpandedView(), data);
        }

        public static InboxItemExpandedView parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new InboxItemExpandedView().mergeFrom(input);
        }
    }

    public static final class InboxItemImage extends ExtendableMessageNano<InboxItemImage> {
        private static volatile InboxItemImage[] _emptyArray;
        public String altText;
        public String url;

        public static InboxItemImage[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new InboxItemImage[0];
                    }
                }
            }
            return _emptyArray;
        }

        public InboxItemImage() {
            clear();
        }

        public InboxItemImage clear() {
            this.url = BuildConfig.VERSION_NAME;
            this.altText = BuildConfig.VERSION_NAME;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof InboxItemImage)) {
                return false;
            }
            InboxItemImage other = (InboxItemImage) o;
            if (this.url == null) {
                if (other.url != null) {
                    return false;
                }
            } else if (!this.url.equals(other.url)) {
                return false;
            }
            if (this.altText == null) {
                if (other.altText != null) {
                    return false;
                }
            } else if (!this.altText.equals(other.altText)) {
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
            int hashCode = (((((getClass().getName().hashCode() + 527) * 31) + (this.url == null ? 0 : this.url.hashCode())) * 31) + (this.altText == null ? 0 : this.altText.hashCode())) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (!this.url.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(1, this.url);
            }
            if (!this.altText.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(2, this.altText);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (!this.url.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.url);
            }
            if (this.altText.equals(BuildConfig.VERSION_NAME)) {
                return size;
            }
            return size + CodedOutputByteBufferNano.computeStringSize(2, this.altText);
        }

        public InboxItemImage mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        this.url = input.readString();
                        continue;
                    case LogSource.GOOGLE_ANALYTICS /*18*/:
                        this.altText = input.readString();
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

        public static InboxItemImage parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (InboxItemImage) MessageNano.mergeFrom(new InboxItemImage(), data);
        }

        public static InboxItemImage parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new InboxItemImage().mergeFrom(input);
        }
    }

    public static final class InboxItemProduct extends ExtendableMessageNano<InboxItemProduct> {
        private static volatile InboxItemProduct[] _emptyArray;
        public String logoUrl;
        public String name;

        public static InboxItemProduct[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new InboxItemProduct[0];
                    }
                }
            }
            return _emptyArray;
        }

        public InboxItemProduct() {
            clear();
        }

        public InboxItemProduct clear() {
            this.name = BuildConfig.VERSION_NAME;
            this.logoUrl = BuildConfig.VERSION_NAME;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof InboxItemProduct)) {
                return false;
            }
            InboxItemProduct other = (InboxItemProduct) o;
            if (this.name == null) {
                if (other.name != null) {
                    return false;
                }
            } else if (!this.name.equals(other.name)) {
                return false;
            }
            if (this.logoUrl == null) {
                if (other.logoUrl != null) {
                    return false;
                }
            } else if (!this.logoUrl.equals(other.logoUrl)) {
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
            int hashCode = (((((getClass().getName().hashCode() + 527) * 31) + (this.name == null ? 0 : this.name.hashCode())) * 31) + (this.logoUrl == null ? 0 : this.logoUrl.hashCode())) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (!this.name.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(1, this.name);
            }
            if (!this.logoUrl.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(2, this.logoUrl);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (!this.name.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.name);
            }
            if (this.logoUrl.equals(BuildConfig.VERSION_NAME)) {
                return size;
            }
            return size + CodedOutputByteBufferNano.computeStringSize(2, this.logoUrl);
        }

        public InboxItemProduct mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        this.name = input.readString();
                        continue;
                    case LogSource.GOOGLE_ANALYTICS /*18*/:
                        this.logoUrl = input.readString();
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

        public static InboxItemProduct parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (InboxItemProduct) MessageNano.mergeFrom(new InboxItemProduct(), data);
        }

        public static InboxItemProduct parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new InboxItemProduct().mergeFrom(input);
        }
    }

    public static final class PermissionRequestAdditionalData extends ExtendableMessageNano<PermissionRequestAdditionalData> {
        private static volatile PermissionRequestAdditionalData[] _emptyArray;
        public DataEntry[] data;

        public static final class DataEntry extends ExtendableMessageNano<DataEntry> {
            private static volatile DataEntry[] _emptyArray;
            public String key;
            public DataValue value;

            public static DataEntry[] emptyArray() {
                if (_emptyArray == null) {
                    synchronized (InternalNano.LAZY_INIT_LOCK) {
                        if (_emptyArray == null) {
                            _emptyArray = new DataEntry[0];
                        }
                    }
                }
                return _emptyArray;
            }

            public DataEntry() {
                clear();
            }

            public DataEntry clear() {
                this.key = BuildConfig.VERSION_NAME;
                this.value = null;
                this.unknownFieldData = null;
                this.cachedSize = -1;
                return this;
            }

            public boolean equals(Object o) {
                if (o == this) {
                    return true;
                }
                if (!(o instanceof DataEntry)) {
                    return false;
                }
                DataEntry other = (DataEntry) o;
                if (this.key == null) {
                    if (other.key != null) {
                        return false;
                    }
                } else if (!this.key.equals(other.key)) {
                    return false;
                }
                if (this.value == null) {
                    if (other.value != null) {
                        return false;
                    }
                } else if (!this.value.equals(other.value)) {
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
                int hashCode = (((((getClass().getName().hashCode() + 527) * 31) + (this.key == null ? 0 : this.key.hashCode())) * 31) + (this.value == null ? 0 : this.value.hashCode())) * 31;
                if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                    i = this.unknownFieldData.hashCode();
                }
                return hashCode + i;
            }

            public void writeTo(CodedOutputByteBufferNano output) throws IOException {
                if (!this.key.equals(BuildConfig.VERSION_NAME)) {
                    output.writeString(1, this.key);
                }
                if (this.value != null) {
                    output.writeMessage(2, this.value);
                }
                super.writeTo(output);
            }

            protected int computeSerializedSize() {
                int size = super.computeSerializedSize();
                if (!this.key.equals(BuildConfig.VERSION_NAME)) {
                    size += CodedOutputByteBufferNano.computeStringSize(1, this.key);
                }
                if (this.value != null) {
                    return size + CodedOutputByteBufferNano.computeMessageSize(2, this.value);
                }
                return size;
            }

            public DataEntry mergeFrom(CodedInputByteBufferNano input) throws IOException {
                while (true) {
                    int tag = input.readTag();
                    switch (tag) {
                        case Action.UNKNOWN /*0*/:
                            break;
                        case Type.TAP_ABOUT /*10*/:
                            this.key = input.readString();
                            continue;
                        case LogSource.GOOGLE_ANALYTICS /*18*/:
                            if (this.value == null) {
                                this.value = new DataValue();
                            }
                            input.readMessage(this.value);
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

            public static DataEntry parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
                return (DataEntry) MessageNano.mergeFrom(new DataEntry(), data);
            }

            public static DataEntry parseFrom(CodedInputByteBufferNano input) throws IOException {
                return new DataEntry().mergeFrom(input);
            }
        }

        public static final class DataValue extends ExtendableMessageNano<DataValue> {
            private static volatile DataValue[] _emptyArray;
            public boolean booleanValue;
            public byte[] bytesValue;
            public double doubleValue;
            public long integerValue;
            public String stringValue;

            public static DataValue[] emptyArray() {
                if (_emptyArray == null) {
                    synchronized (InternalNano.LAZY_INIT_LOCK) {
                        if (_emptyArray == null) {
                            _emptyArray = new DataValue[0];
                        }
                    }
                }
                return _emptyArray;
            }

            public DataValue() {
                clear();
            }

            public DataValue clear() {
                this.booleanValue = false;
                this.integerValue = 0;
                this.stringValue = BuildConfig.VERSION_NAME;
                this.doubleValue = 0.0d;
                this.bytesValue = WireFormatNano.EMPTY_BYTES;
                this.unknownFieldData = null;
                this.cachedSize = -1;
                return this;
            }

            public boolean equals(Object o) {
                if (o == this) {
                    return true;
                }
                if (!(o instanceof DataValue)) {
                    return false;
                }
                DataValue other = (DataValue) o;
                if (this.booleanValue != other.booleanValue || this.integerValue != other.integerValue) {
                    return false;
                }
                if (this.stringValue == null) {
                    if (other.stringValue != null) {
                        return false;
                    }
                } else if (!this.stringValue.equals(other.stringValue)) {
                    return false;
                }
                if (Double.doubleToLongBits(this.doubleValue) != Double.doubleToLongBits(other.doubleValue) || !Arrays.equals(this.bytesValue, other.bytesValue)) {
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
                int result = ((((((getClass().getName().hashCode() + 527) * 31) + (this.booleanValue ? 1231 : 1237)) * 31) + ((int) (this.integerValue ^ (this.integerValue >>> 32)))) * 31) + (this.stringValue == null ? 0 : this.stringValue.hashCode());
                long v = Double.doubleToLongBits(this.doubleValue);
                int hashCode = ((((result * 31) + ((int) ((v >>> 32) ^ v))) * 31) + Arrays.hashCode(this.bytesValue)) * 31;
                if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                    i = this.unknownFieldData.hashCode();
                }
                return hashCode + i;
            }

            public void writeTo(CodedOutputByteBufferNano output) throws IOException {
                if (this.booleanValue) {
                    output.writeBool(1, this.booleanValue);
                }
                if (this.integerValue != 0) {
                    output.writeInt64(2, this.integerValue);
                }
                if (!this.stringValue.equals(BuildConfig.VERSION_NAME)) {
                    output.writeString(3, this.stringValue);
                }
                if (Double.doubleToLongBits(this.doubleValue) != Double.doubleToLongBits(0.0d)) {
                    output.writeDouble(4, this.doubleValue);
                }
                if (!Arrays.equals(this.bytesValue, WireFormatNano.EMPTY_BYTES)) {
                    output.writeBytes(5, this.bytesValue);
                }
                super.writeTo(output);
            }

            protected int computeSerializedSize() {
                int size = super.computeSerializedSize();
                if (this.booleanValue) {
                    size += CodedOutputByteBufferNano.computeBoolSize(1, this.booleanValue);
                }
                if (this.integerValue != 0) {
                    size += CodedOutputByteBufferNano.computeInt64Size(2, this.integerValue);
                }
                if (!this.stringValue.equals(BuildConfig.VERSION_NAME)) {
                    size += CodedOutputByteBufferNano.computeStringSize(3, this.stringValue);
                }
                if (Double.doubleToLongBits(this.doubleValue) != Double.doubleToLongBits(0.0d)) {
                    size += CodedOutputByteBufferNano.computeDoubleSize(4, this.doubleValue);
                }
                if (Arrays.equals(this.bytesValue, WireFormatNano.EMPTY_BYTES)) {
                    return size;
                }
                return size + CodedOutputByteBufferNano.computeBytesSize(5, this.bytesValue);
            }

            public DataValue mergeFrom(CodedInputByteBufferNano input) throws IOException {
                while (true) {
                    int tag = input.readTag();
                    switch (tag) {
                        case Action.UNKNOWN /*0*/:
                            break;
                        case Type.TAP_GET_LINK /*8*/:
                            this.booleanValue = input.readBool();
                            continue;
                        case LogSource.GMS_CORE_PEOPLE /*16*/:
                            this.integerValue = input.readInt64();
                            continue;
                        case LogSource.ANDROID_CAMERA /*26*/:
                            this.stringValue = input.readString();
                            continue;
                        case LogSource.INSTORE_WALLET /*33*/:
                            this.doubleValue = input.readDouble();
                            continue;
                        case LogSource.PHOTOS /*42*/:
                            this.bytesValue = input.readBytes();
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

            public static DataValue parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
                return (DataValue) MessageNano.mergeFrom(new DataValue(), data);
            }

            public static DataValue parseFrom(CodedInputByteBufferNano input) throws IOException {
                return new DataValue().mergeFrom(input);
            }
        }

        public static PermissionRequestAdditionalData[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new PermissionRequestAdditionalData[0];
                    }
                }
            }
            return _emptyArray;
        }

        public PermissionRequestAdditionalData() {
            clear();
        }

        public PermissionRequestAdditionalData clear() {
            this.data = DataEntry.emptyArray();
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof PermissionRequestAdditionalData)) {
                return false;
            }
            PermissionRequestAdditionalData other = (PermissionRequestAdditionalData) o;
            if (!InternalNano.equals(this.data, other.data)) {
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
            int hashCode = (((getClass().getName().hashCode() + 527) * 31) + InternalNano.hashCode(this.data)) * 31;
            int hashCode2 = (this.unknownFieldData == null || this.unknownFieldData.isEmpty()) ? 0 : this.unknownFieldData.hashCode();
            return hashCode + hashCode2;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.data != null && this.data.length > 0) {
                for (DataEntry element : this.data) {
                    if (element != null) {
                        output.writeMessage(2, element);
                    }
                }
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.data != null && this.data.length > 0) {
                for (DataEntry element : this.data) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(2, element);
                    }
                }
            }
            return size;
        }

        public PermissionRequestAdditionalData mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case LogSource.GOOGLE_ANALYTICS /*18*/:
                        int i;
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 18);
                        if (this.data == null) {
                            i = 0;
                        } else {
                            i = this.data.length;
                        }
                        DataEntry[] newArray = new DataEntry[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.data, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new DataEntry();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i++;
                        }
                        newArray[i] = new DataEntry();
                        input.readMessage(newArray[i]);
                        this.data = newArray;
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

        public static PermissionRequestAdditionalData parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (PermissionRequestAdditionalData) MessageNano.mergeFrom(new PermissionRequestAdditionalData(), data);
        }

        public static PermissionRequestAdditionalData parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new PermissionRequestAdditionalData().mergeFrom(input);
        }
    }

    public interface PermissionRequestState {
        public static final int APPROVED = 2;
        public static final int AUTO_APPROVED = 4;
        public static final int PENDING = 1;
        public static final int REJECTED = 3;
        public static final int UNKNOWN_STATE = 0;
    }

    public static final class ViewParameters extends ExtendableMessageNano<ViewParameters> {
        private static volatile ViewParameters[] _emptyArray;
        public int viewportHeight;
        public int viewportWidth;

        public static ViewParameters[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new ViewParameters[0];
                    }
                }
            }
            return _emptyArray;
        }

        public ViewParameters() {
            clear();
        }

        public ViewParameters clear() {
            this.viewportWidth = 0;
            this.viewportHeight = 0;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof ViewParameters)) {
                return false;
            }
            ViewParameters other = (ViewParameters) o;
            if (this.viewportWidth != other.viewportWidth || this.viewportHeight != other.viewportHeight) {
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
            int hashCode = (((((getClass().getName().hashCode() + 527) * 31) + this.viewportWidth) * 31) + this.viewportHeight) * 31;
            int hashCode2 = (this.unknownFieldData == null || this.unknownFieldData.isEmpty()) ? 0 : this.unknownFieldData.hashCode();
            return hashCode + hashCode2;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.viewportWidth != 0) {
                output.writeInt32(1, this.viewportWidth);
            }
            if (this.viewportHeight != 0) {
                output.writeInt32(2, this.viewportHeight);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.viewportWidth != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(1, this.viewportWidth);
            }
            if (this.viewportHeight != 0) {
                return size + CodedOutputByteBufferNano.computeInt32Size(2, this.viewportHeight);
            }
            return size;
        }

        public ViewParameters mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_GET_LINK /*8*/:
                        this.viewportWidth = input.readInt32();
                        continue;
                    case LogSource.GMS_CORE_PEOPLE /*16*/:
                        this.viewportHeight = input.readInt32();
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

        public static ViewParameters parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (ViewParameters) MessageNano.mergeFrom(new ViewParameters(), data);
        }

        public static ViewParameters parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new ViewParameters().mergeFrom(input);
        }
    }
}
