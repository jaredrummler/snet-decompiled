package com.google.android.gms.auth.oauthmultilogin.proto;

import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.InternalNano;
import com.google.protobuf.nano.InvalidProtocolBufferNanoException;
import com.google.protobuf.nano.MessageNano;
import com.google.protobuf.nano.WireFormatNano;
import com.google.wireless.android.play.playlog.proto.ClientAnalytics.LogRequest.LogSource;
import com.google.wireless.android.play.playlog.proto.UserLocation.LaunchSettingsEvent.Action;
import com.google.wireless.android.play.playlog.proto.UserLocation.LocationSharingEvent.Type;
import java.io.IOException;

public final class OAuthMultiLoginJsonResponse extends MessageNano {
    public static final int ERROR = 3;
    public static final int INVALID_INPUT = 4;
    public static final int INVALID_TOKENS = 5;
    public static final int OK = 1;
    public static final int RETRY = 2;
    public static final int UNKNOWN_STATUS = 0;
    private static volatile OAuthMultiLoginJsonResponse[] _emptyArray;
    public Account[] accounts;
    public Cookie[] cookies;
    public FailedAccount[] failedAccounts;
    public Integer status;

    public static final class FailedAccount extends MessageNano {
        public static final int NOT_RECOVERABLE = 3;
        public static final int OK = 1;
        public static final int RECOVERABLE = 2;
        public static final int UNKNOWN_FAILED_STATUS = 0;
        private static volatile FailedAccount[] _emptyArray;
        public String obfuscatedId;
        public Integer status;
        public String url;

        public static FailedAccount[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new FailedAccount[0];
                    }
                }
            }
            return _emptyArray;
        }

        public FailedAccount() {
            clear();
        }

        public FailedAccount clear() {
            this.obfuscatedId = null;
            this.status = null;
            this.url = null;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.obfuscatedId != null) {
                output.writeString(OK, this.obfuscatedId);
            }
            if (this.status != null) {
                output.writeInt32(RECOVERABLE, this.status.intValue());
            }
            if (this.url != null) {
                output.writeString(NOT_RECOVERABLE, this.url);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.obfuscatedId != null) {
                size += CodedOutputByteBufferNano.computeStringSize(OK, this.obfuscatedId);
            }
            if (this.status != null) {
                size += CodedOutputByteBufferNano.computeInt32Size(RECOVERABLE, this.status.intValue());
            }
            if (this.url != null) {
                return size + CodedOutputByteBufferNano.computeStringSize(NOT_RECOVERABLE, this.url);
            }
            return size;
        }

        public FailedAccount mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case Action.UNKNOWN /*0*/:
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        this.obfuscatedId = input.readString();
                        continue;
                    case LogSource.GMS_CORE_PEOPLE /*16*/:
                        int value = input.readInt32();
                        switch (value) {
                            case Action.UNKNOWN /*0*/:
                            case OK /*1*/:
                            case RECOVERABLE /*2*/:
                            case NOT_RECOVERABLE /*3*/:
                                this.status = Integer.valueOf(value);
                                break;
                            default:
                                continue;
                        }
                    case LogSource.ANDROID_CAMERA /*26*/:
                        this.url = input.readString();
                        continue;
                    default:
                        if (!WireFormatNano.parseUnknownField(input, tag)) {
                            break;
                        }
                        continue;
                }
                return this;
            }
        }

        public static FailedAccount parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (FailedAccount) MessageNano.mergeFrom(new FailedAccount(), data);
        }

        public static FailedAccount parseFrom(CodedInputByteBufferNano input) throws IOException {
            return new FailedAccount().mergeFrom(input);
        }
    }

    public static OAuthMultiLoginJsonResponse[] emptyArray() {
        if (_emptyArray == null) {
            synchronized (InternalNano.LAZY_INIT_LOCK) {
                if (_emptyArray == null) {
                    _emptyArray = new OAuthMultiLoginJsonResponse[0];
                }
            }
        }
        return _emptyArray;
    }

    public OAuthMultiLoginJsonResponse() {
        clear();
    }

    public OAuthMultiLoginJsonResponse clear() {
        this.status = null;
        this.cookies = Cookie.emptyArray();
        this.accounts = Account.emptyArray();
        this.failedAccounts = FailedAccount.emptyArray();
        this.cachedSize = -1;
        return this;
    }

    public void writeTo(CodedOutputByteBufferNano output) throws IOException {
        int i;
        if (this.status != null) {
            output.writeInt32(OK, this.status.intValue());
        }
        if (this.cookies != null && this.cookies.length > 0) {
            for (i = 0; i < this.cookies.length; i += OK) {
                Cookie element = this.cookies[i];
                if (element != null) {
                    output.writeMessage(RETRY, element);
                }
            }
        }
        if (this.accounts != null && this.accounts.length > 0) {
            for (i = 0; i < this.accounts.length; i += OK) {
                Account element2 = this.accounts[i];
                if (element2 != null) {
                    output.writeMessage(ERROR, element2);
                }
            }
        }
        if (this.failedAccounts != null && this.failedAccounts.length > 0) {
            for (i = 0; i < this.failedAccounts.length; i += OK) {
                FailedAccount element3 = this.failedAccounts[i];
                if (element3 != null) {
                    output.writeMessage(INVALID_INPUT, element3);
                }
            }
        }
        super.writeTo(output);
    }

    protected int computeSerializedSize() {
        int i;
        int size = super.computeSerializedSize();
        if (this.status != null) {
            size += CodedOutputByteBufferNano.computeInt32Size(OK, this.status.intValue());
        }
        if (this.cookies != null && this.cookies.length > 0) {
            for (i = 0; i < this.cookies.length; i += OK) {
                Cookie element = this.cookies[i];
                if (element != null) {
                    size += CodedOutputByteBufferNano.computeMessageSize(RETRY, element);
                }
            }
        }
        if (this.accounts != null && this.accounts.length > 0) {
            for (i = 0; i < this.accounts.length; i += OK) {
                Account element2 = this.accounts[i];
                if (element2 != null) {
                    size += CodedOutputByteBufferNano.computeMessageSize(ERROR, element2);
                }
            }
        }
        if (this.failedAccounts != null && this.failedAccounts.length > 0) {
            for (i = 0; i < this.failedAccounts.length; i += OK) {
                FailedAccount element3 = this.failedAccounts[i];
                if (element3 != null) {
                    size += CodedOutputByteBufferNano.computeMessageSize(INVALID_INPUT, element3);
                }
            }
        }
        return size;
    }

    public OAuthMultiLoginJsonResponse mergeFrom(CodedInputByteBufferNano input) throws IOException {
        while (true) {
            int tag = input.readTag();
            int arrayLength;
            int i;
            switch (tag) {
                case Action.UNKNOWN /*0*/:
                    break;
                case Type.TAP_GET_LINK /*8*/:
                    int value = input.readInt32();
                    switch (value) {
                        case Action.UNKNOWN /*0*/:
                        case OK /*1*/:
                        case RETRY /*2*/:
                        case ERROR /*3*/:
                        case INVALID_INPUT /*4*/:
                        case INVALID_TOKENS /*5*/:
                            this.status = Integer.valueOf(value);
                            break;
                        default:
                            continue;
                    }
                case LogSource.GOOGLE_ANALYTICS /*18*/:
                    arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 18);
                    if (this.cookies == null) {
                        i = 0;
                    } else {
                        i = this.cookies.length;
                    }
                    Cookie[] newArray = new Cookie[(i + arrayLength)];
                    if (i != 0) {
                        System.arraycopy(this.cookies, 0, newArray, 0, i);
                    }
                    while (i < newArray.length - 1) {
                        newArray[i] = new Cookie();
                        input.readMessage(newArray[i]);
                        input.readTag();
                        i += OK;
                    }
                    newArray[i] = new Cookie();
                    input.readMessage(newArray[i]);
                    this.cookies = newArray;
                    continue;
                case LogSource.ANDROID_CAMERA /*26*/:
                    arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 26);
                    if (this.accounts == null) {
                        i = 0;
                    } else {
                        i = this.accounts.length;
                    }
                    Account[] newArray2 = new Account[(i + arrayLength)];
                    if (i != 0) {
                        System.arraycopy(this.accounts, 0, newArray2, 0, i);
                    }
                    while (i < newArray2.length - 1) {
                        newArray2[i] = new Account();
                        input.readMessage(newArray2[i]);
                        input.readTag();
                        i += OK;
                    }
                    newArray2[i] = new Account();
                    input.readMessage(newArray2[i]);
                    this.accounts = newArray2;
                    continue;
                case LogSource.NOVA /*34*/:
                    arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 34);
                    if (this.failedAccounts == null) {
                        i = 0;
                    } else {
                        i = this.failedAccounts.length;
                    }
                    FailedAccount[] newArray3 = new FailedAccount[(i + arrayLength)];
                    if (i != 0) {
                        System.arraycopy(this.failedAccounts, 0, newArray3, 0, i);
                    }
                    while (i < newArray3.length - 1) {
                        newArray3[i] = new FailedAccount();
                        input.readMessage(newArray3[i]);
                        input.readTag();
                        i += OK;
                    }
                    newArray3[i] = new FailedAccount();
                    input.readMessage(newArray3[i]);
                    this.failedAccounts = newArray3;
                    continue;
                default:
                    if (!WireFormatNano.parseUnknownField(input, tag)) {
                        break;
                    }
                    continue;
            }
            return this;
        }
    }

    public static OAuthMultiLoginJsonResponse parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
        return (OAuthMultiLoginJsonResponse) MessageNano.mergeFrom(new OAuthMultiLoginJsonResponse(), data);
    }

    public static OAuthMultiLoginJsonResponse parseFrom(CodedInputByteBufferNano input) throws IOException {
        return new OAuthMultiLoginJsonResponse().mergeFrom(input);
    }
}
