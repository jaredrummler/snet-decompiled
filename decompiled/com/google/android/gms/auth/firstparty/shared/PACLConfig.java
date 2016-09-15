package com.google.android.gms.auth.firstparty.shared;

import android.os.Parcel;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;

@Class(creator = "PACLConfigCreator")
public class PACLConfig implements SafeParcelable {
    public static final PACLConfigCreator CREATOR;
    private static final int VERSION = 1;
    @Field(id = 3)
    String pacl;
    @VersionField(id = 1)
    final int version;
    @Field(id = 2)
    String visibleActions;

    static {
        CREATOR = new PACLConfigCreator();
    }

    @Constructor
    PACLConfig(@Param(id = 1) int version, @Param(id = 2) String visibleActions, @Param(id = 3) String pacl) {
        this.version = version;
        this.visibleActions = visibleActions;
        this.pacl = pacl;
    }

    public PACLConfig(String visibleActions, String pacl) {
        this.version = VERSION;
        this.visibleActions = visibleActions;
        this.pacl = pacl;
    }

    public void writeToParcel(Parcel dest, int flags) {
        PACLConfigCreator.writeToParcel(this, dest, flags);
    }

    public int describeContents() {
        return 0;
    }

    public String getVisibleActions() {
        return this.visibleActions;
    }

    public String getPacl() {
        return this.pacl;
    }

    public boolean equals(Object o) {
        if (!(o instanceof PACLConfig)) {
            return false;
        }
        PACLConfig config = (PACLConfig) o;
        if (TextUtils.equals(this.visibleActions, config.visibleActions) && TextUtils.equals(this.pacl, config.pacl)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hashCode(this.visibleActions, this.pacl);
    }
}
