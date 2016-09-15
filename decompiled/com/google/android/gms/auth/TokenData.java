package com.google.android.gms.auth;

import android.os.Bundle;
import android.os.Parcel;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;
import java.util.List;

@Class(creator = "TokenDataCreator")
public class TokenData implements SafeParcelable {
    private static final String BUNDLE_KEY = "TokenData";
    public static final TokenDataCreator CREATOR;
    private static final int VERSION_CODE = 1;
    @Field(getter = "getExpirationTimeSecs", id = 3)
    private final Long mExpirationTimeSecs;
    @Field(getter = "getGrantedScopes", id = 6)
    private final List<String> mGrantedScopes;
    @Field(getter = "isCached", id = 4)
    private final boolean mIsCached;
    @Field(getter = "isSnowballed", id = 5)
    private final boolean mIsSnowballed;
    @Field(getter = "getToken", id = 2)
    private final String mToken;
    @VersionField(id = 1)
    final int mVersionCode;

    public static class Builder {
        private Long mExpirationTimeSecs;
        private List<String> mGrantedScopes;
        private boolean mIsCached;
        private boolean mIsSnowballed;
        private String mToken;

        public Builder() {
            this.mToken = null;
            this.mExpirationTimeSecs = null;
            this.mIsCached = false;
            this.mIsSnowballed = false;
            this.mGrantedScopes = null;
        }

        public Builder setToken(String token) {
            this.mToken = token;
            return this;
        }

        public Builder setExpirationTimeSecs(Long expirationTimeSecs) {
            this.mExpirationTimeSecs = expirationTimeSecs;
            return this;
        }

        public Builder setIsCached(boolean IsCached) {
            this.mIsCached = IsCached;
            return this;
        }

        public Builder setIsSnowballed(boolean isSnowballed) {
            this.mIsSnowballed = isSnowballed;
            return this;
        }

        public Builder setGrantedScopes(List<String> grantedScopes) {
            this.mGrantedScopes = grantedScopes;
            return this;
        }

        @Nullable
        public TokenData build() {
            if (this.mIsSnowballed && this.mGrantedScopes == null) {
                throw new IllegalStateException("Granted scopes must be set if the token is snowballed.");
            } else if (TextUtils.isEmpty(this.mToken)) {
                return null;
            } else {
                return new TokenData(TokenData.VERSION_CODE, this.mToken, this.mExpirationTimeSecs, this.mIsCached, this.mIsSnowballed, this.mGrantedScopes);
            }
        }
    }

    static {
        CREATOR = new TokenDataCreator();
    }

    @Nullable
    public static TokenData fromWrappedBundle(Bundle bundle, String key) {
        bundle.setClassLoader(TokenData.class.getClassLoader());
        Bundle wrapper = bundle.getBundle(key);
        if (wrapper == null) {
            return null;
        }
        wrapper.setClassLoader(TokenData.class.getClassLoader());
        return (TokenData) wrapper.getParcelable(BUNDLE_KEY);
    }

    @Constructor
    TokenData(@Param(id = 1) int versionCode, @Param(id = 2) String token, @Param(id = 3) Long expirationTimeSecs, @Param(id = 4) boolean isCached, @Param(id = 5) boolean isSnowballed, @Param(id = 6) List<String> grantedScopes) {
        this.mVersionCode = versionCode;
        this.mToken = Preconditions.checkNotEmpty(token);
        this.mExpirationTimeSecs = expirationTimeSecs;
        this.mIsCached = isCached;
        this.mIsSnowballed = isSnowballed;
        this.mGrantedScopes = grantedScopes;
    }

    public String getToken() {
        return this.mToken;
    }

    @Nullable
    public Long getExpirationTimeSecs() {
        return this.mExpirationTimeSecs;
    }

    public boolean isCached() {
        return this.mIsCached;
    }

    public boolean isSnowballed() {
        return this.mIsSnowballed;
    }

    @Nullable
    public List<String> getGrantedScopes() {
        return this.mGrantedScopes;
    }

    public void putIntoBundle(Bundle bundle, String key) {
        Bundle wrapper = new Bundle();
        wrapper.putParcelable(BUNDLE_KEY, this);
        bundle.putBundle(key, wrapper);
    }

    public boolean equals(Object o) {
        if (!(o instanceof TokenData)) {
            return false;
        }
        TokenData data = (TokenData) o;
        if (TextUtils.equals(this.mToken, data.mToken) && Objects.equal(this.mExpirationTimeSecs, data.mExpirationTimeSecs) && this.mIsCached == data.mIsCached && this.mIsSnowballed == data.mIsSnowballed && Objects.equal(this.mGrantedScopes, data.mGrantedScopes)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hashCode(this.mToken, this.mExpirationTimeSecs, Boolean.valueOf(this.mIsCached), Boolean.valueOf(this.mIsSnowballed), this.mGrantedScopes);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        TokenDataCreator.writeToParcel(this, out, flags);
    }
}
