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
import com.google.android.gms.lint.BuildConfig;

@Class(creator = "FACLConfigCreator")
public class FACLConfig implements SafeParcelable {
    public static final FACLConfigCreator CREATOR;
    private static final int VERSION = 1;
    @Field(id = 7)
    boolean hasShowCircles;
    @Field(id = 2)
    boolean isAllCirclesVisible;
    @Field(id = 4)
    boolean isAllContactsVisible;
    @Field(id = 5)
    boolean showCircles;
    @Field(id = 6)
    boolean showContacts;
    @VersionField(id = 1)
    final int version;
    @Field(id = 3)
    String visibleEdges;

    static {
        CREATOR = new FACLConfigCreator();
    }

    @Constructor
    FACLConfig(@Param(id = 1) int version, @Param(id = 2) boolean isAllCirclesVisible, @Param(id = 3) String visibleEdges, @Param(id = 4) boolean isAllContactsVisible, @Param(id = 5) boolean showCircles, @Param(id = 6) boolean showContacts, @Param(id = 7) boolean hasShowCircles) {
        this.version = version;
        this.isAllCirclesVisible = isAllCirclesVisible;
        this.visibleEdges = visibleEdges;
        this.isAllContactsVisible = isAllContactsVisible;
        this.showCircles = showCircles;
        this.showContacts = showContacts;
        this.hasShowCircles = hasShowCircles;
    }

    public FACLConfig(boolean isAllCirclesVisible, String visibleEdges, boolean isAllContactsVisible, boolean showCircles, boolean showContacts, boolean hasShowCircles) {
        this.version = VERSION;
        this.isAllCirclesVisible = isAllCirclesVisible;
        if (isAllCirclesVisible) {
            this.visibleEdges = BuildConfig.VERSION_NAME;
        } else {
            this.visibleEdges = visibleEdges;
        }
        this.isAllContactsVisible = isAllContactsVisible;
        this.showCircles = showCircles;
        this.showContacts = showContacts;
        this.hasShowCircles = hasShowCircles;
    }

    public void writeToParcel(Parcel dest, int flags) {
        FACLConfigCreator.writeToParcel(this, dest, flags);
    }

    public int describeContents() {
        return 0;
    }

    public boolean isAllCirclesVisible() {
        return this.isAllCirclesVisible;
    }

    public String getVisibleEdges() {
        return this.visibleEdges;
    }

    public boolean isAllContactsVisible() {
        return this.isAllContactsVisible;
    }

    public boolean getShowCircles() {
        return this.showCircles;
    }

    public boolean getShowContacts() {
        return this.showContacts;
    }

    public boolean hasShowCircles() {
        return this.hasShowCircles;
    }

    public boolean equals(Object o) {
        if (!(o instanceof FACLConfig)) {
            return false;
        }
        FACLConfig config = (FACLConfig) o;
        if (this.isAllCirclesVisible == config.isAllCirclesVisible && TextUtils.equals(this.visibleEdges, config.visibleEdges) && this.isAllContactsVisible == config.isAllContactsVisible && this.showCircles == config.showCircles && this.showContacts == config.showContacts && this.hasShowCircles == config.hasShowCircles) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hashCode(Boolean.valueOf(this.isAllCirclesVisible), this.visibleEdges, Boolean.valueOf(this.isAllContactsVisible), Boolean.valueOf(this.showCircles), Boolean.valueOf(this.showContacts), Boolean.valueOf(this.hasShowCircles));
    }
}
