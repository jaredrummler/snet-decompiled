package com.google.kidsmanagement.v1;

import com.google.android.gms.lint.BuildConfig;
import com.google.kidsmanagement.v1.ActivitiesCommonProto.AppActivity;
import com.google.kidsmanagement.v1.AppVersionCommonProto.AppUpgradeStatus;
import com.google.kidsmanagement.v1.AppsCommonProto.AppMetadata;
import com.google.kidsmanagement.v1.DevicesCommonProto.Device;
import com.google.kidsmanagement.v1.FamilyTimeCommonProto.FamilyMemberLocation;
import com.google.kidsmanagement.v1.TimeoutsCommonProto.Timeout;
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

public interface CardsCommonProto {

    public static final class AppListCardData extends ExtendableMessageNano<AppListCardData> {
        private static volatile AppListCardData[] _emptyArray;
        public AppMetadata[] apps;
        public int numApps;

        public static AppListCardData[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new AppListCardData[0];
                    }
                }
            }
            return _emptyArray;
        }

        public AppListCardData() {
            clear();
        }

        public AppListCardData clear() {
            this.apps = AppMetadata.emptyArray();
            this.numApps = 0;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof AppListCardData)) {
                return false;
            }
            AppListCardData other = (AppListCardData) o;
            if (!InternalNano.equals(this.apps, other.apps) || this.numApps != other.numApps) {
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
            int hashCode = (((((getClass().getName().hashCode() + 527) * 31) + InternalNano.hashCode(this.apps)) * 31) + this.numApps) * 31;
            int hashCode2 = (this.unknownFieldData == null || this.unknownFieldData.isEmpty()) ? 0 : this.unknownFieldData.hashCode();
            return hashCode + hashCode2;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.apps != null && this.apps.length > 0) {
                for (AppMetadata element : this.apps) {
                    if (element != null) {
                        output.writeMessage(1, element);
                    }
                }
            }
            if (this.numApps != 0) {
                output.writeInt32(2, this.numApps);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.apps != null && this.apps.length > 0) {
                for (AppMetadata element : this.apps) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(1, element);
                    }
                }
            }
            if (this.numApps != 0) {
                return size + CodedOutputByteBufferNano.computeInt32Size(2, this.numApps);
            }
            return size;
        }

        public AppListCardData mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        int i;
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 10);
                        if (this.apps == null) {
                            i = 0;
                        } else {
                            i = this.apps.length;
                        }
                        AppMetadata[] newArray = new AppMetadata[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.apps, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new AppMetadata();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i++;
                        }
                        newArray[i] = new AppMetadata();
                        input.readMessage(newArray[i]);
                        this.apps = newArray;
                        continue;
                    case LogSource.GMS_CORE_PEOPLE /*16*/:
                        this.numApps = input.readInt32();
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

        public static AppListCardData parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (AppListCardData) MessageNano.mergeFrom(new AppListCardData(), data);
        }

        public static AppListCardData parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new AppListCardData().mergeFrom(input);
        }
    }

    public static final class AppUsageCardData extends ExtendableMessageNano<AppUsageCardData> {
        private static volatile AppUsageCardData[] _emptyArray;
        public AppUsageData[] usage;

        public static final class AppUsageData extends ExtendableMessageNano<AppUsageData> {
            private static volatile AppUsageData[] _emptyArray;
            public RemainingSummary remainingAppsUsage;
            public int timeRange;
            public AppActivity[] topActivities;

            public static final class RemainingSummary extends ExtendableMessageNano<RemainingSummary> {
                private static volatile RemainingSummary[] _emptyArray;
                public int numApps;
                public long totalUsageMillis;

                public static RemainingSummary[] emptyArray() {
                    if (_emptyArray == null) {
                        synchronized (InternalNano.LAZY_INIT_LOCK) {
                            if (_emptyArray == null) {
                                _emptyArray = new RemainingSummary[0];
                            }
                        }
                    }
                    return _emptyArray;
                }

                public RemainingSummary() {
                    clear();
                }

                public RemainingSummary clear() {
                    this.numApps = 0;
                    this.totalUsageMillis = 0;
                    this.unknownFieldData = null;
                    this.cachedSize = -1;
                    return this;
                }

                public boolean equals(Object o) {
                    if (o == this) {
                        return true;
                    }
                    if (!(o instanceof RemainingSummary)) {
                        return false;
                    }
                    RemainingSummary other = (RemainingSummary) o;
                    if (this.numApps != other.numApps || this.totalUsageMillis != other.totalUsageMillis) {
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
                    int hashCode = (((((getClass().getName().hashCode() + 527) * 31) + this.numApps) * 31) + ((int) (this.totalUsageMillis ^ (this.totalUsageMillis >>> 32)))) * 31;
                    int hashCode2 = (this.unknownFieldData == null || this.unknownFieldData.isEmpty()) ? 0 : this.unknownFieldData.hashCode();
                    return hashCode + hashCode2;
                }

                public void writeTo(CodedOutputByteBufferNano output) throws IOException {
                    if (this.numApps != 0) {
                        output.writeInt32(1, this.numApps);
                    }
                    if (this.totalUsageMillis != 0) {
                        output.writeInt64(2, this.totalUsageMillis);
                    }
                    super.writeTo(output);
                }

                protected int computeSerializedSize() {
                    int size = super.computeSerializedSize();
                    if (this.numApps != 0) {
                        size += CodedOutputByteBufferNano.computeInt32Size(1, this.numApps);
                    }
                    if (this.totalUsageMillis != 0) {
                        return size + CodedOutputByteBufferNano.computeInt64Size(2, this.totalUsageMillis);
                    }
                    return size;
                }

                public RemainingSummary mergeFrom(CodedInputByteBufferNano input) throws IOException {
                    while (true) {
                        int tag = input.readTag();
                        switch (tag) {
                            case Action.UNKNOWN /*0*/:
                                break;
                            case Type.TAP_GET_LINK /*8*/:
                                this.numApps = input.readInt32();
                                continue;
                            case LogSource.GMS_CORE_PEOPLE /*16*/:
                                this.totalUsageMillis = input.readInt64();
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

                public static RemainingSummary parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
                    return (RemainingSummary) MessageNano.mergeFrom(new RemainingSummary(), data);
                }

                public static RemainingSummary parseFrom(CodedInputByteBufferNano input) throws IOException {
                    return new RemainingSummary().mergeFrom(input);
                }
            }

            public static AppUsageData[] emptyArray() {
                if (_emptyArray == null) {
                    synchronized (InternalNano.LAZY_INIT_LOCK) {
                        if (_emptyArray == null) {
                            _emptyArray = new AppUsageData[0];
                        }
                    }
                }
                return _emptyArray;
            }

            public AppUsageData() {
                clear();
            }

            public AppUsageData clear() {
                this.timeRange = 0;
                this.topActivities = AppActivity.emptyArray();
                this.remainingAppsUsage = null;
                this.unknownFieldData = null;
                this.cachedSize = -1;
                return this;
            }

            public boolean equals(Object o) {
                if (o == this) {
                    return true;
                }
                if (!(o instanceof AppUsageData)) {
                    return false;
                }
                AppUsageData other = (AppUsageData) o;
                if (this.timeRange != other.timeRange || !InternalNano.equals(this.topActivities, other.topActivities)) {
                    return false;
                }
                if (this.remainingAppsUsage == null) {
                    if (other.remainingAppsUsage != null) {
                        return false;
                    }
                } else if (!this.remainingAppsUsage.equals(other.remainingAppsUsage)) {
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
                int hashCode = (((((((getClass().getName().hashCode() + 527) * 31) + this.timeRange) * 31) + InternalNano.hashCode(this.topActivities)) * 31) + (this.remainingAppsUsage == null ? 0 : this.remainingAppsUsage.hashCode())) * 31;
                if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                    i = this.unknownFieldData.hashCode();
                }
                return hashCode + i;
            }

            public void writeTo(CodedOutputByteBufferNano output) throws IOException {
                if (this.timeRange != 0) {
                    output.writeInt32(1, this.timeRange);
                }
                if (this.topActivities != null && this.topActivities.length > 0) {
                    for (AppActivity element : this.topActivities) {
                        if (element != null) {
                            output.writeMessage(2, element);
                        }
                    }
                }
                if (this.remainingAppsUsage != null) {
                    output.writeMessage(3, this.remainingAppsUsage);
                }
                super.writeTo(output);
            }

            protected int computeSerializedSize() {
                int size = super.computeSerializedSize();
                if (this.timeRange != 0) {
                    size += CodedOutputByteBufferNano.computeInt32Size(1, this.timeRange);
                }
                if (this.topActivities != null && this.topActivities.length > 0) {
                    for (AppActivity element : this.topActivities) {
                        if (element != null) {
                            size += CodedOutputByteBufferNano.computeMessageSize(2, element);
                        }
                    }
                }
                if (this.remainingAppsUsage != null) {
                    return size + CodedOutputByteBufferNano.computeMessageSize(3, this.remainingAppsUsage);
                }
                return size;
            }

            public AppUsageData mergeFrom(CodedInputByteBufferNano input) throws IOException {
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
                                    this.timeRange = value;
                                    break;
                                default:
                                    continue;
                            }
                        case LogSource.GOOGLE_ANALYTICS /*18*/:
                            int i;
                            int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 18);
                            if (this.topActivities == null) {
                                i = 0;
                            } else {
                                i = this.topActivities.length;
                            }
                            AppActivity[] newArray = new AppActivity[(i + arrayLength)];
                            if (i != 0) {
                                System.arraycopy(this.topActivities, 0, newArray, 0, i);
                            }
                            while (i < newArray.length - 1) {
                                newArray[i] = new AppActivity();
                                input.readMessage(newArray[i]);
                                input.readTag();
                                i++;
                            }
                            newArray[i] = new AppActivity();
                            input.readMessage(newArray[i]);
                            this.topActivities = newArray;
                            continue;
                        case LogSource.ANDROID_CAMERA /*26*/:
                            if (this.remainingAppsUsage == null) {
                                this.remainingAppsUsage = new RemainingSummary();
                            }
                            input.readMessage(this.remainingAppsUsage);
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

            public static AppUsageData parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
                return (AppUsageData) MessageNano.mergeFrom(new AppUsageData(), data);
            }

            public static AppUsageData parseFrom(CodedInputByteBufferNano input) throws IOException {
                return new AppUsageData().mergeFrom(input);
            }
        }

        public interface TimeRange {
            public static final int DEPRECATED_LAST_14_DAYS = 4;
            public static final int DEPRECATED_LAST_365_DAYS = 3;
            public static final int LAST_30_DAYS = 2;
            public static final int LAST_7_DAYS = 1;
            public static final int TIME_RANGE_UNKNOWN = 0;
        }

        public static AppUsageCardData[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new AppUsageCardData[0];
                    }
                }
            }
            return _emptyArray;
        }

        public AppUsageCardData() {
            clear();
        }

        public AppUsageCardData clear() {
            this.usage = AppUsageData.emptyArray();
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof AppUsageCardData)) {
                return false;
            }
            AppUsageCardData other = (AppUsageCardData) o;
            if (!InternalNano.equals(this.usage, other.usage)) {
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
            int hashCode = (((getClass().getName().hashCode() + 527) * 31) + InternalNano.hashCode(this.usage)) * 31;
            int hashCode2 = (this.unknownFieldData == null || this.unknownFieldData.isEmpty()) ? 0 : this.unknownFieldData.hashCode();
            return hashCode + hashCode2;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.usage != null && this.usage.length > 0) {
                for (AppUsageData element : this.usage) {
                    if (element != null) {
                        output.writeMessage(1, element);
                    }
                }
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.usage != null && this.usage.length > 0) {
                for (AppUsageData element : this.usage) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(1, element);
                    }
                }
            }
            return size;
        }

        public AppUsageCardData mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        int i;
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 10);
                        if (this.usage == null) {
                            i = 0;
                        } else {
                            i = this.usage.length;
                        }
                        AppUsageData[] newArray = new AppUsageData[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.usage, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new AppUsageData();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i++;
                        }
                        newArray[i] = new AppUsageData();
                        input.readMessage(newArray[i]);
                        this.usage = newArray;
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

        public static AppUsageCardData parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (AppUsageCardData) MessageNano.mergeFrom(new AppUsageCardData(), data);
        }

        public static AppUsageCardData parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new AppUsageCardData().mergeFrom(input);
        }
    }

    public static final class BlockWebsiteSuggestionCardData extends ExtendableMessageNano<BlockWebsiteSuggestionCardData> {
        private static volatile BlockWebsiteSuggestionCardData[] _emptyArray;
        public AppMetadata app;
        public WebsiteMetadata websiteMetadata;

        public static BlockWebsiteSuggestionCardData[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new BlockWebsiteSuggestionCardData[0];
                    }
                }
            }
            return _emptyArray;
        }

        public BlockWebsiteSuggestionCardData() {
            clear();
        }

        public BlockWebsiteSuggestionCardData clear() {
            this.app = null;
            this.websiteMetadata = null;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof BlockWebsiteSuggestionCardData)) {
                return false;
            }
            BlockWebsiteSuggestionCardData other = (BlockWebsiteSuggestionCardData) o;
            if (this.app == null) {
                if (other.app != null) {
                    return false;
                }
            } else if (!this.app.equals(other.app)) {
                return false;
            }
            if (this.websiteMetadata == null) {
                if (other.websiteMetadata != null) {
                    return false;
                }
            } else if (!this.websiteMetadata.equals(other.websiteMetadata)) {
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
            int hashCode = (((((getClass().getName().hashCode() + 527) * 31) + (this.app == null ? 0 : this.app.hashCode())) * 31) + (this.websiteMetadata == null ? 0 : this.websiteMetadata.hashCode())) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.app != null) {
                output.writeMessage(1, this.app);
            }
            if (this.websiteMetadata != null) {
                output.writeMessage(2, this.websiteMetadata);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.app != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(1, this.app);
            }
            if (this.websiteMetadata != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(2, this.websiteMetadata);
            }
            return size;
        }

        public BlockWebsiteSuggestionCardData mergeFrom(CodedInputByteBufferNano input) throws IOException {
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
                    case LogSource.GOOGLE_ANALYTICS /*18*/:
                        if (this.websiteMetadata == null) {
                            this.websiteMetadata = new WebsiteMetadata();
                        }
                        input.readMessage(this.websiteMetadata);
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

        public static BlockWebsiteSuggestionCardData parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (BlockWebsiteSuggestionCardData) MessageNano.mergeFrom(new BlockWebsiteSuggestionCardData(), data);
        }

        public static BlockWebsiteSuggestionCardData parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new BlockWebsiteSuggestionCardData().mergeFrom(input);
        }
    }

    public static final class Card extends ExtendableMessageNano<Card> {
        private static volatile Card[] _emptyArray;
        public CardData data;
        public int dismissalStatus;
        public CardID id;
        public int status;

        public interface DismissalStatus {
            public static final int DISMISSALSTATUS_UNKNOWN = 0;
            public static final int HIDDEN = 2;
            public static final int VISIBLE = 1;
        }

        public interface Status {
            public static final int ERROR = 2;
            public static final int STATUS_UNKNOWN = 0;
            public static final int SUCCESS = 1;
        }

        public static Card[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new Card[0];
                    }
                }
            }
            return _emptyArray;
        }

        public Card() {
            clear();
        }

        public Card clear() {
            this.id = null;
            this.status = 0;
            this.data = null;
            this.dismissalStatus = 0;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof Card)) {
                return false;
            }
            Card other = (Card) o;
            if (this.id == null) {
                if (other.id != null) {
                    return false;
                }
            } else if (!this.id.equals(other.id)) {
                return false;
            }
            if (this.status != other.status) {
                return false;
            }
            if (this.data == null) {
                if (other.data != null) {
                    return false;
                }
            } else if (!this.data.equals(other.data)) {
                return false;
            }
            if (this.dismissalStatus != other.dismissalStatus) {
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
            int hashCode = (((((((((getClass().getName().hashCode() + 527) * 31) + (this.id == null ? 0 : this.id.hashCode())) * 31) + this.status) * 31) + (this.data == null ? 0 : this.data.hashCode())) * 31) + this.dismissalStatus) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.id != null) {
                output.writeMessage(1, this.id);
            }
            if (this.status != 0) {
                output.writeInt32(2, this.status);
            }
            if (this.data != null) {
                output.writeMessage(3, this.data);
            }
            if (this.dismissalStatus != 0) {
                output.writeInt32(4, this.dismissalStatus);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.id != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(1, this.id);
            }
            if (this.status != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(2, this.status);
            }
            if (this.data != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(3, this.data);
            }
            if (this.dismissalStatus != 0) {
                return size + CodedOutputByteBufferNano.computeInt32Size(4, this.dismissalStatus);
            }
            return size;
        }

        public Card mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                int value;
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        if (this.id == null) {
                            this.id = new CardID();
                        }
                        input.readMessage(this.id);
                        continue;
                    case LogSource.GMS_CORE_PEOPLE /*16*/:
                        value = input.readInt32();
                        switch (value) {
                            case Action.UNKNOWN /*0*/:
                            case TimeSelection.Type.TEMPORARY /*1*/:
                            case TimeSelection.Type.INDEFINITELY /*2*/:
                                this.status = value;
                                break;
                            default:
                                continue;
                        }
                    case LogSource.ANDROID_CAMERA /*26*/:
                        if (this.data == null) {
                            this.data = new CardData();
                        }
                        input.readMessage(this.data);
                        continue;
                    case LogSource.SOCIAL /*32*/:
                        value = input.readInt32();
                        switch (value) {
                            case Action.UNKNOWN /*0*/:
                            case TimeSelection.Type.TEMPORARY /*1*/:
                            case TimeSelection.Type.INDEFINITELY /*2*/:
                                this.dismissalStatus = value;
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

        public static Card parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (Card) MessageNano.mergeFrom(new Card(), data);
        }

        public static Card parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new Card().mergeFrom(input);
        }
    }

    public static final class CardData extends ExtendableMessageNano<CardData> {
        private static volatile CardData[] _emptyArray;
        public AppListCardData appList;
        public AppUsageCardData appUsage;
        public BlockWebsiteSuggestionCardData blockWebsite;
        public DeviceCardData device;
        public LocationFixitCardData fixit;
        public LocationCardData location;
        public TimeoutCardData timeout;
        public UpgradeStatusCardData upgradeStatus;
        public WebsiteRequestCardData websiteRequest;

        public static CardData[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new CardData[0];
                    }
                }
            }
            return _emptyArray;
        }

        public CardData() {
            clear();
        }

        public CardData clear() {
            this.appUsage = null;
            this.appList = null;
            this.timeout = null;
            this.location = null;
            this.fixit = null;
            this.upgradeStatus = null;
            this.device = null;
            this.websiteRequest = null;
            this.blockWebsite = null;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof CardData)) {
                return false;
            }
            CardData other = (CardData) o;
            if (this.appUsage == null) {
                if (other.appUsage != null) {
                    return false;
                }
            } else if (!this.appUsage.equals(other.appUsage)) {
                return false;
            }
            if (this.appList == null) {
                if (other.appList != null) {
                    return false;
                }
            } else if (!this.appList.equals(other.appList)) {
                return false;
            }
            if (this.timeout == null) {
                if (other.timeout != null) {
                    return false;
                }
            } else if (!this.timeout.equals(other.timeout)) {
                return false;
            }
            if (this.location == null) {
                if (other.location != null) {
                    return false;
                }
            } else if (!this.location.equals(other.location)) {
                return false;
            }
            if (this.fixit == null) {
                if (other.fixit != null) {
                    return false;
                }
            } else if (!this.fixit.equals(other.fixit)) {
                return false;
            }
            if (this.upgradeStatus == null) {
                if (other.upgradeStatus != null) {
                    return false;
                }
            } else if (!this.upgradeStatus.equals(other.upgradeStatus)) {
                return false;
            }
            if (this.device == null) {
                if (other.device != null) {
                    return false;
                }
            } else if (!this.device.equals(other.device)) {
                return false;
            }
            if (this.websiteRequest == null) {
                if (other.websiteRequest != null) {
                    return false;
                }
            } else if (!this.websiteRequest.equals(other.websiteRequest)) {
                return false;
            }
            if (this.blockWebsite == null) {
                if (other.blockWebsite != null) {
                    return false;
                }
            } else if (!this.blockWebsite.equals(other.blockWebsite)) {
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
            int hashCode = (((((((((((((((((((getClass().getName().hashCode() + 527) * 31) + (this.appUsage == null ? 0 : this.appUsage.hashCode())) * 31) + (this.appList == null ? 0 : this.appList.hashCode())) * 31) + (this.timeout == null ? 0 : this.timeout.hashCode())) * 31) + (this.location == null ? 0 : this.location.hashCode())) * 31) + (this.fixit == null ? 0 : this.fixit.hashCode())) * 31) + (this.upgradeStatus == null ? 0 : this.upgradeStatus.hashCode())) * 31) + (this.device == null ? 0 : this.device.hashCode())) * 31) + (this.websiteRequest == null ? 0 : this.websiteRequest.hashCode())) * 31) + (this.blockWebsite == null ? 0 : this.blockWebsite.hashCode())) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.appUsage != null) {
                output.writeMessage(1, this.appUsage);
            }
            if (this.appList != null) {
                output.writeMessage(2, this.appList);
            }
            if (this.timeout != null) {
                output.writeMessage(3, this.timeout);
            }
            if (this.location != null) {
                output.writeMessage(4, this.location);
            }
            if (this.fixit != null) {
                output.writeMessage(5, this.fixit);
            }
            if (this.upgradeStatus != null) {
                output.writeMessage(6, this.upgradeStatus);
            }
            if (this.device != null) {
                output.writeMessage(7, this.device);
            }
            if (this.websiteRequest != null) {
                output.writeMessage(8, this.websiteRequest);
            }
            if (this.blockWebsite != null) {
                output.writeMessage(9, this.blockWebsite);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.appUsage != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(1, this.appUsage);
            }
            if (this.appList != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(2, this.appList);
            }
            if (this.timeout != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(3, this.timeout);
            }
            if (this.location != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(4, this.location);
            }
            if (this.fixit != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(5, this.fixit);
            }
            if (this.upgradeStatus != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(6, this.upgradeStatus);
            }
            if (this.device != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(7, this.device);
            }
            if (this.websiteRequest != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(8, this.websiteRequest);
            }
            if (this.blockWebsite != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(9, this.blockWebsite);
            }
            return size;
        }

        public CardData mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        if (this.appUsage == null) {
                            this.appUsage = new AppUsageCardData();
                        }
                        input.readMessage(this.appUsage);
                        continue;
                    case LogSource.GOOGLE_ANALYTICS /*18*/:
                        if (this.appList == null) {
                            this.appList = new AppListCardData();
                        }
                        input.readMessage(this.appList);
                        continue;
                    case LogSource.ANDROID_CAMERA /*26*/:
                        if (this.timeout == null) {
                            this.timeout = new TimeoutCardData();
                        }
                        input.readMessage(this.timeout);
                        continue;
                    case LogSource.NOVA /*34*/:
                        if (this.location == null) {
                            this.location = new LocationCardData();
                        }
                        input.readMessage(this.location);
                        continue;
                    case LogSource.PHOTOS /*42*/:
                        if (this.fixit == null) {
                            this.fixit = new LocationFixitCardData();
                        }
                        input.readMessage(this.fixit);
                        continue;
                    case LogSource.HANGOUT_LOG_REQUEST /*50*/:
                        if (this.upgradeStatus == null) {
                            this.upgradeStatus = new UpgradeStatusCardData();
                        }
                        input.readMessage(this.upgradeStatus);
                        continue;
                    case LogSource.SLIDES /*58*/:
                        if (this.device == null) {
                            this.device = new DeviceCardData();
                        }
                        input.readMessage(this.device);
                        continue;
                    case LogSource.WIFI_ASSISTANT /*66*/:
                        if (this.websiteRequest == null) {
                            this.websiteRequest = new WebsiteRequestCardData();
                        }
                        input.readMessage(this.websiteRequest);
                        continue;
                    case LogSource.MOVIES /*74*/:
                        if (this.blockWebsite == null) {
                            this.blockWebsite = new BlockWebsiteSuggestionCardData();
                        }
                        input.readMessage(this.blockWebsite);
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

        public static CardData parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (CardData) MessageNano.mergeFrom(new CardData(), data);
        }

        public static CardData parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new CardData().mergeFrom(input);
        }
    }

    public static final class CardDismissal extends ExtendableMessageNano<CardDismissal> {
        private static volatile CardDismissal[] _emptyArray;
        public CardID cardId;
        public int dismissalStatus;

        public static CardDismissal[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new CardDismissal[0];
                    }
                }
            }
            return _emptyArray;
        }

        public CardDismissal() {
            clear();
        }

        public CardDismissal clear() {
            this.cardId = null;
            this.dismissalStatus = 0;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof CardDismissal)) {
                return false;
            }
            CardDismissal other = (CardDismissal) o;
            if (this.cardId == null) {
                if (other.cardId != null) {
                    return false;
                }
            } else if (!this.cardId.equals(other.cardId)) {
                return false;
            }
            if (this.dismissalStatus != other.dismissalStatus) {
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
            int hashCode = (((((getClass().getName().hashCode() + 527) * 31) + (this.cardId == null ? 0 : this.cardId.hashCode())) * 31) + this.dismissalStatus) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.cardId != null) {
                output.writeMessage(1, this.cardId);
            }
            if (this.dismissalStatus != 0) {
                output.writeInt32(2, this.dismissalStatus);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.cardId != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(1, this.cardId);
            }
            if (this.dismissalStatus != 0) {
                return size + CodedOutputByteBufferNano.computeInt32Size(2, this.dismissalStatus);
            }
            return size;
        }

        public CardDismissal mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        if (this.cardId == null) {
                            this.cardId = new CardID();
                        }
                        input.readMessage(this.cardId);
                        continue;
                    case LogSource.GMS_CORE_PEOPLE /*16*/:
                        int value = input.readInt32();
                        switch (value) {
                            case Action.UNKNOWN /*0*/:
                            case TimeSelection.Type.TEMPORARY /*1*/:
                            case TimeSelection.Type.INDEFINITELY /*2*/:
                                this.dismissalStatus = value;
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

        public static CardDismissal parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (CardDismissal) MessageNano.mergeFrom(new CardDismissal(), data);
        }

        public static CardDismissal parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new CardDismissal().mergeFrom(input);
        }
    }

    public static final class CardID extends ExtendableMessageNano<CardID> {
        private static volatile CardID[] _emptyArray;
        public String id;
        public int type;

        public static CardID[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new CardID[0];
                    }
                }
            }
            return _emptyArray;
        }

        public CardID() {
            clear();
        }

        public CardID clear() {
            this.type = 0;
            this.id = BuildConfig.VERSION_NAME;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof CardID)) {
                return false;
            }
            CardID other = (CardID) o;
            if (this.type != other.type) {
                return false;
            }
            if (this.id == null) {
                if (other.id != null) {
                    return false;
                }
            } else if (!this.id.equals(other.id)) {
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
            int hashCode = (((((getClass().getName().hashCode() + 527) * 31) + this.type) * 31) + (this.id == null ? 0 : this.id.hashCode())) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.type != 0) {
                output.writeInt32(1, this.type);
            }
            if (!this.id.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(2, this.id);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.type != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(1, this.type);
            }
            if (this.id.equals(BuildConfig.VERSION_NAME)) {
                return size;
            }
            return size + CodedOutputByteBufferNano.computeStringSize(2, this.id);
        }

        public CardID mergeFrom(CodedInputByteBufferNano input) throws IOException {
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
                                this.type = value;
                                break;
                            default:
                                continue;
                        }
                    case LogSource.GOOGLE_ANALYTICS /*18*/:
                        this.id = input.readString();
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

        public static CardID parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (CardID) MessageNano.mergeFrom(new CardID(), data);
        }

        public static CardID parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new CardID().mergeFrom(input);
        }
    }

    public interface CardType {
        public static final int CARD_TYPE_ACTIVE_APPS = 2;
        public static final int CARD_TYPE_APP_USAGE = 1;
        public static final int CARD_TYPE_APP_USAGE_FIXIT = 14;
        public static final int CARD_TYPE_BEDTIME = 5;
        public static final int CARD_TYPE_BEDTIME_CTA = 7;
        public static final int CARD_TYPE_BEDTIME_V2 = 11;
        public static final int CARD_TYPE_BLOCK_WEBSITE_SUGGESTION = 18;
        public static final int CARD_TYPE_DEVICE = 13;
        public static final int CARD_TYPE_DISABLED_APPS = 3;
        public static final int CARD_TYPE_FEEDBACK = 16;
        public static final int CARD_TYPE_INSTALLED_APPS = 15;
        public static final int CARD_TYPE_LOCATION = 8;
        public static final int CARD_TYPE_LOCATION_FIXIT = 9;
        public static final int CARD_TYPE_NEW_APPS = 12;
        public static final int CARD_TYPE_NOT_SIGNED_IN = 4;
        public static final int CARD_TYPE_TIMEOUT = 6;
        public static final int CARD_TYPE_UNKNOWN = 0;
        public static final int CARD_TYPE_UPGRADE_STATUS = 10;
        public static final int CARD_TYPE_WEBSITE_REQUEST = 17;
    }

    public static final class DeviceCardData extends ExtendableMessageNano<DeviceCardData> {
        private static volatile DeviceCardData[] _emptyArray;
        public Device device;

        public static DeviceCardData[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new DeviceCardData[0];
                    }
                }
            }
            return _emptyArray;
        }

        public DeviceCardData() {
            clear();
        }

        public DeviceCardData clear() {
            this.device = null;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof DeviceCardData)) {
                return false;
            }
            DeviceCardData other = (DeviceCardData) o;
            if (this.device == null) {
                if (other.device != null) {
                    return false;
                }
            } else if (!this.device.equals(other.device)) {
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
            int hashCode = (((getClass().getName().hashCode() + 527) * 31) + (this.device == null ? 0 : this.device.hashCode())) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.device != null) {
                output.writeMessage(1, this.device);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.device != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(1, this.device);
            }
            return size;
        }

        public DeviceCardData mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        if (this.device == null) {
                            this.device = new Device();
                        }
                        input.readMessage(this.device);
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

        public static DeviceCardData parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (DeviceCardData) MessageNano.mergeFrom(new DeviceCardData(), data);
        }

        public static DeviceCardData parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new DeviceCardData().mergeFrom(input);
        }
    }

    public static final class LocationCardData extends ExtendableMessageNano<LocationCardData> {
        private static volatile LocationCardData[] _emptyArray;
        public FamilyMemberLocation childLocation;

        public static LocationCardData[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new LocationCardData[0];
                    }
                }
            }
            return _emptyArray;
        }

        public LocationCardData() {
            clear();
        }

        public LocationCardData clear() {
            this.childLocation = null;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof LocationCardData)) {
                return false;
            }
            LocationCardData other = (LocationCardData) o;
            if (this.childLocation == null) {
                if (other.childLocation != null) {
                    return false;
                }
            } else if (!this.childLocation.equals(other.childLocation)) {
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
            int hashCode = (((getClass().getName().hashCode() + 527) * 31) + (this.childLocation == null ? 0 : this.childLocation.hashCode())) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.childLocation != null) {
                output.writeMessage(1, this.childLocation);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.childLocation != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(1, this.childLocation);
            }
            return size;
        }

        public LocationCardData mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        if (this.childLocation == null) {
                            this.childLocation = new FamilyMemberLocation();
                        }
                        input.readMessage(this.childLocation);
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

        public static LocationCardData parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (LocationCardData) MessageNano.mergeFrom(new LocationCardData(), data);
        }

        public static LocationCardData parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new LocationCardData().mergeFrom(input);
        }
    }

    public static final class LocationFixitCardData extends ExtendableMessageNano<LocationFixitCardData> {
        private static volatile LocationFixitCardData[] _emptyArray;
        public int[] absentConsents;

        public static LocationFixitCardData[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new LocationFixitCardData[0];
                    }
                }
            }
            return _emptyArray;
        }

        public LocationFixitCardData() {
            clear();
        }

        public LocationFixitCardData clear() {
            this.absentConsents = WireFormatNano.EMPTY_INT_ARRAY;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof LocationFixitCardData)) {
                return false;
            }
            LocationFixitCardData other = (LocationFixitCardData) o;
            if (!InternalNano.equals(this.absentConsents, other.absentConsents)) {
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
            int hashCode = (((getClass().getName().hashCode() + 527) * 31) + InternalNano.hashCode(this.absentConsents)) * 31;
            int hashCode2 = (this.unknownFieldData == null || this.unknownFieldData.isEmpty()) ? 0 : this.unknownFieldData.hashCode();
            return hashCode + hashCode2;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.absentConsents != null && this.absentConsents.length > 0) {
                for (int writeInt32 : this.absentConsents) {
                    output.writeInt32(1, writeInt32);
                }
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.absentConsents == null || this.absentConsents.length <= 0) {
                return size;
            }
            int dataSize = 0;
            for (int element : this.absentConsents) {
                dataSize += CodedOutputByteBufferNano.computeInt32SizeNoTag(element);
            }
            return (size + dataSize) + (this.absentConsents.length * 1);
        }

        public LocationFixitCardData mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                int i;
                int value;
                int[] newArray;
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_GET_LINK /*8*/:
                        int length = WireFormatNano.getRepeatedFieldArrayLength(input, 8);
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
                            i = this.absentConsents == null ? 0 : this.absentConsents.length;
                            if (i != 0 || validCount != validValues.length) {
                                newArray = new int[(i + validCount)];
                                if (i != 0) {
                                    System.arraycopy(this.absentConsents, 0, newArray, 0, i);
                                }
                                System.arraycopy(validValues, 0, newArray, i, validCount);
                                this.absentConsents = newArray;
                                break;
                            }
                            this.absentConsents = validValues;
                            break;
                        }
                        continue;
                        break;
                    case Type.TAP_ABOUT /*10*/:
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
                            if (this.absentConsents == null) {
                                i = 0;
                            } else {
                                i = this.absentConsents.length;
                            }
                            newArray = new int[(i + arrayLength)];
                            if (i != 0) {
                                System.arraycopy(this.absentConsents, 0, newArray, 0, i);
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
                            this.absentConsents = newArray;
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

        public static LocationFixitCardData parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (LocationFixitCardData) MessageNano.mergeFrom(new LocationFixitCardData(), data);
        }

        public static LocationFixitCardData parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new LocationFixitCardData().mergeFrom(input);
        }
    }

    public static final class TimeoutCardData extends ExtendableMessageNano<TimeoutCardData> {
        private static volatile TimeoutCardData[] _emptyArray;
        public int bedtimeStatus;
        public String childTimezone;
        public long nextChangeTimestampMillis;
        public Timeout[] timeouts;

        public interface BedtimeStatus {
            public static final int BEDTIMESTATUS_ACTIVE = 4;
            public static final int BEDTIMESTATUS_DISABLED = 2;
            public static final int BEDTIMESTATUS_NOT_ACTIVE = 3;
            public static final int BEDTIMESTATUS_NOT_SET = 1;
            public static final int BEDTIMESTATUS_SUSPENDED = 5;
            public static final int BEDTIMESTATUS_UNKNOWN = 0;
        }

        public static TimeoutCardData[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new TimeoutCardData[0];
                    }
                }
            }
            return _emptyArray;
        }

        public TimeoutCardData() {
            clear();
        }

        public TimeoutCardData clear() {
            this.timeouts = Timeout.emptyArray();
            this.childTimezone = BuildConfig.VERSION_NAME;
            this.bedtimeStatus = 0;
            this.nextChangeTimestampMillis = 0;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof TimeoutCardData)) {
                return false;
            }
            TimeoutCardData other = (TimeoutCardData) o;
            if (!InternalNano.equals(this.timeouts, other.timeouts)) {
                return false;
            }
            if (this.childTimezone == null) {
                if (other.childTimezone != null) {
                    return false;
                }
            } else if (!this.childTimezone.equals(other.childTimezone)) {
                return false;
            }
            if (this.bedtimeStatus != other.bedtimeStatus || this.nextChangeTimestampMillis != other.nextChangeTimestampMillis) {
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
            int hashCode = (((((((((getClass().getName().hashCode() + 527) * 31) + InternalNano.hashCode(this.timeouts)) * 31) + (this.childTimezone == null ? 0 : this.childTimezone.hashCode())) * 31) + this.bedtimeStatus) * 31) + ((int) (this.nextChangeTimestampMillis ^ (this.nextChangeTimestampMillis >>> 32)))) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.timeouts != null && this.timeouts.length > 0) {
                for (Timeout element : this.timeouts) {
                    if (element != null) {
                        output.writeMessage(1, element);
                    }
                }
            }
            if (!this.childTimezone.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(2, this.childTimezone);
            }
            if (this.bedtimeStatus != 0) {
                output.writeInt32(3, this.bedtimeStatus);
            }
            if (this.nextChangeTimestampMillis != 0) {
                output.writeUInt64(4, this.nextChangeTimestampMillis);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.timeouts != null && this.timeouts.length > 0) {
                for (Timeout element : this.timeouts) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(1, element);
                    }
                }
            }
            if (!this.childTimezone.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(2, this.childTimezone);
            }
            if (this.bedtimeStatus != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(3, this.bedtimeStatus);
            }
            if (this.nextChangeTimestampMillis != 0) {
                return size + CodedOutputByteBufferNano.computeUInt64Size(4, this.nextChangeTimestampMillis);
            }
            return size;
        }

        public TimeoutCardData mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        int i;
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 10);
                        if (this.timeouts == null) {
                            i = 0;
                        } else {
                            i = this.timeouts.length;
                        }
                        Timeout[] newArray = new Timeout[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.timeouts, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new Timeout();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i++;
                        }
                        newArray[i] = new Timeout();
                        input.readMessage(newArray[i]);
                        this.timeouts = newArray;
                        continue;
                    case LogSource.GOOGLE_ANALYTICS /*18*/:
                        this.childTimezone = input.readString();
                        continue;
                    case LogSource.LB_C /*24*/:
                        int value = input.readInt32();
                        switch (value) {
                            case Action.UNKNOWN /*0*/:
                            case TimeSelection.Type.TEMPORARY /*1*/:
                            case TimeSelection.Type.INDEFINITELY /*2*/:
                            case TimeSelection.Type.CUSTOM /*3*/:
                            case OvenFreshResult.NO_ACCOUNTS /*4*/:
                            case Type.ADD_NEW_SHARES /*5*/:
                                this.bedtimeStatus = value;
                                break;
                            default:
                                continue;
                        }
                    case LogSource.SOCIAL /*32*/:
                        this.nextChangeTimestampMillis = input.readUInt64();
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

        public static TimeoutCardData parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (TimeoutCardData) MessageNano.mergeFrom(new TimeoutCardData(), data);
        }

        public static TimeoutCardData parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new TimeoutCardData().mergeFrom(input);
        }
    }

    public static final class UpgradeStatusCardData extends ExtendableMessageNano<UpgradeStatusCardData> {
        private static volatile UpgradeStatusCardData[] _emptyArray;
        public AppUpgradeStatus upgradeStatus;

        public static UpgradeStatusCardData[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new UpgradeStatusCardData[0];
                    }
                }
            }
            return _emptyArray;
        }

        public UpgradeStatusCardData() {
            clear();
        }

        public UpgradeStatusCardData clear() {
            this.upgradeStatus = null;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof UpgradeStatusCardData)) {
                return false;
            }
            UpgradeStatusCardData other = (UpgradeStatusCardData) o;
            if (this.upgradeStatus == null) {
                if (other.upgradeStatus != null) {
                    return false;
                }
            } else if (!this.upgradeStatus.equals(other.upgradeStatus)) {
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
            int hashCode = (((getClass().getName().hashCode() + 527) * 31) + (this.upgradeStatus == null ? 0 : this.upgradeStatus.hashCode())) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.upgradeStatus != null) {
                output.writeMessage(1, this.upgradeStatus);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.upgradeStatus != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(1, this.upgradeStatus);
            }
            return size;
        }

        public UpgradeStatusCardData mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        if (this.upgradeStatus == null) {
                            this.upgradeStatus = new AppUpgradeStatus();
                        }
                        input.readMessage(this.upgradeStatus);
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

        public static UpgradeStatusCardData parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (UpgradeStatusCardData) MessageNano.mergeFrom(new UpgradeStatusCardData(), data);
        }

        public static UpgradeStatusCardData parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new UpgradeStatusCardData().mergeFrom(input);
        }
    }

    public static final class WebsiteMetadata extends ExtendableMessageNano<WebsiteMetadata> {
        private static volatile WebsiteMetadata[] _emptyArray;
        public String faviconUrl;
        public String url;

        public static WebsiteMetadata[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new WebsiteMetadata[0];
                    }
                }
            }
            return _emptyArray;
        }

        public WebsiteMetadata() {
            clear();
        }

        public WebsiteMetadata clear() {
            this.faviconUrl = BuildConfig.VERSION_NAME;
            this.url = BuildConfig.VERSION_NAME;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof WebsiteMetadata)) {
                return false;
            }
            WebsiteMetadata other = (WebsiteMetadata) o;
            if (this.faviconUrl == null) {
                if (other.faviconUrl != null) {
                    return false;
                }
            } else if (!this.faviconUrl.equals(other.faviconUrl)) {
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
            int hashCode = (((((getClass().getName().hashCode() + 527) * 31) + (this.faviconUrl == null ? 0 : this.faviconUrl.hashCode())) * 31) + (this.url == null ? 0 : this.url.hashCode())) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (!this.faviconUrl.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(1, this.faviconUrl);
            }
            if (!this.url.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(2, this.url);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (!this.faviconUrl.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.faviconUrl);
            }
            if (this.url.equals(BuildConfig.VERSION_NAME)) {
                return size;
            }
            return size + CodedOutputByteBufferNano.computeStringSize(2, this.url);
        }

        public WebsiteMetadata mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        this.faviconUrl = input.readString();
                        continue;
                    case LogSource.GOOGLE_ANALYTICS /*18*/:
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

        public static WebsiteMetadata parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (WebsiteMetadata) MessageNano.mergeFrom(new WebsiteMetadata(), data);
        }

        public static WebsiteMetadata parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new WebsiteMetadata().mergeFrom(input);
        }
    }

    public static final class WebsiteRequestCardData extends ExtendableMessageNano<WebsiteRequestCardData> {
        private static volatile WebsiteRequestCardData[] _emptyArray;
        public long createTimestampMillis;
        public String id;
        public String requestedUrl;
        public WebsiteMetadata websiteMetadata;

        public static WebsiteRequestCardData[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new WebsiteRequestCardData[0];
                    }
                }
            }
            return _emptyArray;
        }

        public WebsiteRequestCardData() {
            clear();
        }

        public WebsiteRequestCardData clear() {
            this.id = BuildConfig.VERSION_NAME;
            this.createTimestampMillis = 0;
            this.requestedUrl = BuildConfig.VERSION_NAME;
            this.websiteMetadata = null;
            this.unknownFieldData = null;
            this.cachedSize = -1;
            return this;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof WebsiteRequestCardData)) {
                return false;
            }
            WebsiteRequestCardData other = (WebsiteRequestCardData) o;
            if (this.id == null) {
                if (other.id != null) {
                    return false;
                }
            } else if (!this.id.equals(other.id)) {
                return false;
            }
            if (this.createTimestampMillis != other.createTimestampMillis) {
                return false;
            }
            if (this.requestedUrl == null) {
                if (other.requestedUrl != null) {
                    return false;
                }
            } else if (!this.requestedUrl.equals(other.requestedUrl)) {
                return false;
            }
            if (this.websiteMetadata == null) {
                if (other.websiteMetadata != null) {
                    return false;
                }
            } else if (!this.websiteMetadata.equals(other.websiteMetadata)) {
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
            int hashCode = (((((((((getClass().getName().hashCode() + 527) * 31) + (this.id == null ? 0 : this.id.hashCode())) * 31) + ((int) (this.createTimestampMillis ^ (this.createTimestampMillis >>> 32)))) * 31) + (this.requestedUrl == null ? 0 : this.requestedUrl.hashCode())) * 31) + (this.websiteMetadata == null ? 0 : this.websiteMetadata.hashCode())) * 31;
            if (!(this.unknownFieldData == null || this.unknownFieldData.isEmpty())) {
                i = this.unknownFieldData.hashCode();
            }
            return hashCode + i;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (!this.id.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(1, this.id);
            }
            if (this.createTimestampMillis != 0) {
                output.writeInt64(2, this.createTimestampMillis);
            }
            if (!this.requestedUrl.equals(BuildConfig.VERSION_NAME)) {
                output.writeString(3, this.requestedUrl);
            }
            if (this.websiteMetadata != null) {
                output.writeMessage(4, this.websiteMetadata);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (!this.id.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.id);
            }
            if (this.createTimestampMillis != 0) {
                size += CodedOutputByteBufferNano.computeInt64Size(2, this.createTimestampMillis);
            }
            if (!this.requestedUrl.equals(BuildConfig.VERSION_NAME)) {
                size += CodedOutputByteBufferNano.computeStringSize(3, this.requestedUrl);
            }
            if (this.websiteMetadata != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(4, this.websiteMetadata);
            }
            return size;
        }

        public WebsiteRequestCardData mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        this.id = input.readString();
                        continue;
                    case LogSource.GMS_CORE_PEOPLE /*16*/:
                        this.createTimestampMillis = input.readInt64();
                        continue;
                    case LogSource.ANDROID_CAMERA /*26*/:
                        this.requestedUrl = input.readString();
                        continue;
                    case LogSource.NOVA /*34*/:
                        if (this.websiteMetadata == null) {
                            this.websiteMetadata = new WebsiteMetadata();
                        }
                        input.readMessage(this.websiteMetadata);
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

        public static WebsiteRequestCardData parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (WebsiteRequestCardData) MessageNano.mergeFrom(new WebsiteRequestCardData(), data);
        }

        public static WebsiteRequestCardData parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new WebsiteRequestCardData().mergeFrom(input);
        }
    }
}
