package com.google.android.gms.auth.firstparty.shared;

import android.graphics.Bitmap;
import android.os.Parcel;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;

@Class(creator = "CaptchaChallengeCreator")
public class CaptchaChallenge implements SafeParcelable {
    public static final CaptchaChallengeCreator CREATOR;
    private static final int VERSION = 1;
    @Field(id = 4)
    Bitmap captcha;
    @Field(id = 2)
    String statusWireCode;
    @Field(id = 3)
    String token;
    @VersionField(id = 1)
    final int version;

    static {
        CREATOR = new CaptchaChallengeCreator();
    }

    @Constructor
    CaptchaChallenge(@Param(id = 1) int version, @Param(id = 2) String statusWireCode, @Param(id = 3) String token, @Param(id = 4) Bitmap captcha) {
        this.version = version;
        this.statusWireCode = statusWireCode;
        this.token = token;
        this.captcha = captcha;
    }

    public CaptchaChallenge(Status status) {
        this(status, null, null);
    }

    public CaptchaChallenge(Status status, String captchaToken, Bitmap captcha) {
        this.version = VERSION;
        this.statusWireCode = ((Status) Preconditions.checkNotNull(status)).getWire();
        this.token = captchaToken;
        this.captcha = captcha;
    }

    public void writeToParcel(Parcel dest, int flags) {
        CaptchaChallengeCreator.writeToParcel(this, dest, flags);
    }

    public int describeContents() {
        return 0;
    }

    public Status getStatus() {
        return Status.fromWireCode(this.statusWireCode);
    }

    public String getCaptchaToken() {
        return this.token;
    }

    public Bitmap getCaptcha() {
        return this.captcha;
    }
}
