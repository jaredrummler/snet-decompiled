package com.google.android.gms.auth.firstparty.shared;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;

@Class(creator = "CaptchaSolutionCreator")
public class CaptchaSolution implements SafeParcelable {
    public static final CaptchaSolutionCreator CREATOR;
    private static final int VERSION = 1;
    @Field(id = 3)
    String answer;
    @Field(id = 2)
    String token;
    @VersionField(id = 1)
    final int version;

    static {
        CREATOR = new CaptchaSolutionCreator();
    }

    @Constructor
    CaptchaSolution(@Param(id = 1) int version, @Param(id = 2) String token, @Param(id = 3) String answer) {
        this.version = version;
        this.token = token;
        this.answer = answer;
    }

    public CaptchaSolution(String captchaToken, String captchaAnswer) {
        this.version = VERSION;
        this.token = captchaToken;
        this.answer = captchaAnswer;
    }

    public void writeToParcel(Parcel dest, int flags) {
        CaptchaSolutionCreator.writeToParcel(this, dest, flags);
    }

    public String getToken() {
        return this.token;
    }

    public String getAnswer() {
        return this.answer;
    }

    public int describeContents() {
        return 0;
    }
}
