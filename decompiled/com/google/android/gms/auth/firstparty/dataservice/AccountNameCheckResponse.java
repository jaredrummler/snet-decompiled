package com.google.android.gms.auth.firstparty.dataservice;

import android.os.Parcel;
import com.google.android.gms.auth.firstparty.shared.CaptchaChallenge;
import com.google.android.gms.auth.firstparty.shared.Status;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Class(creator = "AccountNameCheckResponseCreator")
public class AccountNameCheckResponse implements SafeParcelable {
    public static final AccountNameCheckResponseCreator CREATOR;
    private static final int VERSION = 1;
    @Field(id = 5)
    CaptchaChallenge captcha;
    @Field(id = 4)
    String detail;
    @Field(id = 2)
    String statusWireCode;
    @Field(id = 3)
    List<String> suggestions;
    @VersionField(id = 1)
    final int version;

    static {
        CREATOR = new AccountNameCheckResponseCreator();
    }

    @Constructor
    AccountNameCheckResponse(@Param(id = 1) int version, @Param(id = 2) String statusWireCode, @Param(id = 3) List<String> suggestions, @Param(id = 4) String detail, @Param(id = 5) CaptchaChallenge captcha) {
        this.version = version;
        this.statusWireCode = statusWireCode;
        this.suggestions = suggestions;
        this.detail = detail;
        this.captcha = captcha;
    }

    public AccountNameCheckResponse(Status status) {
        this(status, Collections.EMPTY_LIST);
    }

    public AccountNameCheckResponse(Status status, List<String> suggestions) {
        this(status, null, suggestions);
    }

    public AccountNameCheckResponse(Status status, String detail, List<String> suggestions) {
        this(status, detail, null, suggestions);
    }

    public AccountNameCheckResponse(Status status, String detail, CaptchaChallenge captchaChallenge, List<String> suggestions) {
        this.version = VERSION;
        this.statusWireCode = ((Status) Preconditions.checkNotNull(status)).getWire();
        this.detail = detail;
        this.captcha = captchaChallenge;
        this.suggestions = Collections.unmodifiableList(new ArrayList(suggestions));
    }

    public void writeToParcel(Parcel dest, int flags) {
        AccountNameCheckResponseCreator.writeToParcel(this, dest, flags);
    }

    public int describeContents() {
        return 0;
    }

    public Status getStatus() {
        return Status.fromWireCode(this.statusWireCode);
    }

    public CaptchaChallenge getCaptchaChallenge() {
        return this.captcha;
    }

    public List<String> getAccountNameSuggestions() {
        return this.suggestions;
    }

    public String getDetail() {
        return this.detail;
    }
}
