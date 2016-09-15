package com.google.android.gms.common.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;

@Class(creator = "SignInButtonConfigCreator")
public class SignInButtonConfig implements SafeParcelable {
    public static final Creator<SignInButtonConfig> CREATOR;
    private static final int VERSION_CODE = 1;
    @Field(getter = "getButtonSize", id = 2)
    private final int mButtonSize;
    @Field(getter = "getColorScheme", id = 3)
    private final int mColorScheme;
    @Field(getter = "getScopes", id = 4)
    private final Scope[] mScopes;
    @VersionField(id = 1)
    final int mVersionCode;

    static {
        CREATOR = new SignInButtonConfigCreator();
    }

    @Constructor
    SignInButtonConfig(@Param(id = 1) int versionCode, @Param(id = 2) int buttonSize, @Param(id = 3) int colorScheme, @Param(id = 4) Scope[] scopes) {
        this.mVersionCode = versionCode;
        this.mButtonSize = buttonSize;
        this.mColorScheme = colorScheme;
        this.mScopes = scopes;
    }

    public SignInButtonConfig(int buttonSize, int colorScheme, Scope[] scopes) {
        this(VERSION_CODE, buttonSize, colorScheme, scopes);
    }

    public int getVersionCode() {
        return this.mVersionCode;
    }

    public int getButtonSize() {
        return this.mButtonSize;
    }

    public int getColorScheme() {
        return this.mColorScheme;
    }

    public Scope[] getScopes() {
        return this.mScopes;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        SignInButtonConfigCreator.writeToParcel(this, dest, flags);
    }
}
