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

public final class Account extends MessageNano {
    public static final int PERSON_ACCOUNT = 1;
    public static final int PLUS_PAGE = 2;
    public static final int PMA = 3;
    private static volatile Account[] _emptyArray;
    public Integer authuser;
    public Boolean defaultUser;
    public String displayEmail;
    public String displayName;
    public String obfuscatedId;
    public Boolean ownedBySelectedAccount;
    public String pageId;
    public String photoUrl;
    public Boolean selected;
    public Integer type;
    public Boolean validSession;

    public static Account[] emptyArray() {
        if (_emptyArray == null) {
            synchronized (InternalNano.LAZY_INIT_LOCK) {
                if (_emptyArray == null) {
                    _emptyArray = new Account[0];
                }
            }
        }
        return _emptyArray;
    }

    public Account() {
        clear();
    }

    public Account clear() {
        this.type = null;
        this.displayName = null;
        this.displayEmail = null;
        this.photoUrl = null;
        this.selected = null;
        this.defaultUser = null;
        this.authuser = null;
        this.pageId = null;
        this.validSession = null;
        this.obfuscatedId = null;
        this.ownedBySelectedAccount = null;
        this.cachedSize = -1;
        return this;
    }

    public void writeTo(CodedOutputByteBufferNano output) throws IOException {
        if (this.type != null) {
            output.writeInt32(PERSON_ACCOUNT, this.type.intValue());
        }
        if (this.displayName != null) {
            output.writeString(PLUS_PAGE, this.displayName);
        }
        if (this.displayEmail != null) {
            output.writeString(PMA, this.displayEmail);
        }
        if (this.photoUrl != null) {
            output.writeString(4, this.photoUrl);
        }
        if (this.selected != null) {
            output.writeBool(5, this.selected.booleanValue());
        }
        if (this.defaultUser != null) {
            output.writeBool(6, this.defaultUser.booleanValue());
        }
        if (this.authuser != null) {
            output.writeInt32(7, this.authuser.intValue());
        }
        if (this.pageId != null) {
            output.writeString(8, this.pageId);
        }
        if (this.validSession != null) {
            output.writeBool(9, this.validSession.booleanValue());
        }
        if (this.obfuscatedId != null) {
            output.writeString(10, this.obfuscatedId);
        }
        if (this.ownedBySelectedAccount != null) {
            output.writeBool(11, this.ownedBySelectedAccount.booleanValue());
        }
        super.writeTo(output);
    }

    protected int computeSerializedSize() {
        int size = super.computeSerializedSize();
        if (this.type != null) {
            size += CodedOutputByteBufferNano.computeInt32Size(PERSON_ACCOUNT, this.type.intValue());
        }
        if (this.displayName != null) {
            size += CodedOutputByteBufferNano.computeStringSize(PLUS_PAGE, this.displayName);
        }
        if (this.displayEmail != null) {
            size += CodedOutputByteBufferNano.computeStringSize(PMA, this.displayEmail);
        }
        if (this.photoUrl != null) {
            size += CodedOutputByteBufferNano.computeStringSize(4, this.photoUrl);
        }
        if (this.selected != null) {
            size += CodedOutputByteBufferNano.computeBoolSize(5, this.selected.booleanValue());
        }
        if (this.defaultUser != null) {
            size += CodedOutputByteBufferNano.computeBoolSize(6, this.defaultUser.booleanValue());
        }
        if (this.authuser != null) {
            size += CodedOutputByteBufferNano.computeInt32Size(7, this.authuser.intValue());
        }
        if (this.pageId != null) {
            size += CodedOutputByteBufferNano.computeStringSize(8, this.pageId);
        }
        if (this.validSession != null) {
            size += CodedOutputByteBufferNano.computeBoolSize(9, this.validSession.booleanValue());
        }
        if (this.obfuscatedId != null) {
            size += CodedOutputByteBufferNano.computeStringSize(10, this.obfuscatedId);
        }
        if (this.ownedBySelectedAccount != null) {
            return size + CodedOutputByteBufferNano.computeBoolSize(11, this.ownedBySelectedAccount.booleanValue());
        }
        return size;
    }

    public Account mergeFrom(CodedInputByteBufferNano input) throws IOException {
        while (true) {
            int tag = input.readTag();
            switch (tag) {
                case Action.UNKNOWN /*0*/:
                    break;
                case Type.TAP_GET_LINK /*8*/:
                    int value = input.readInt32();
                    switch (value) {
                        case PERSON_ACCOUNT /*1*/:
                        case PLUS_PAGE /*2*/:
                        case PMA /*3*/:
                            this.type = Integer.valueOf(value);
                            break;
                        default:
                            continue;
                    }
                case LogSource.GOOGLE_ANALYTICS /*18*/:
                    this.displayName = input.readString();
                    continue;
                case LogSource.ANDROID_CAMERA /*26*/:
                    this.displayEmail = input.readString();
                    continue;
                case LogSource.NOVA /*34*/:
                    this.photoUrl = input.readString();
                    continue;
                case LogSource.COPRESENCE /*40*/:
                    this.selected = Boolean.valueOf(input.readBool());
                    continue;
                case LogSource.BACKDROP /*48*/:
                    this.defaultUser = Boolean.valueOf(input.readBool());
                    continue;
                case LogSource.DOCS /*56*/:
                    this.authuser = Integer.valueOf(input.readInt32());
                    continue;
                case LogSource.WIFI_ASSISTANT /*66*/:
                    this.pageId = input.readString();
                    continue;
                case LogSource.JUSTSPEAK /*72*/:
                    this.validSession = Boolean.valueOf(input.readBool());
                    continue;
                case LogSource.GOOGLE_EXPRESS /*82*/:
                    this.obfuscatedId = input.readString();
                    continue;
                case LogSource.EMERGENCY_ASSIST /*88*/:
                    this.ownedBySelectedAccount = Boolean.valueOf(input.readBool());
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

    public static Account parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
        return (Account) MessageNano.mergeFrom(new Account(), data);
    }

    public static Account parseFrom(CodedInputByteBufferNano input) throws IOException {
        return new Account().mergeFrom(input);
    }
}
