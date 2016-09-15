package com.google.android.gms.auth.firstparty.shared;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Class(creator = "ScopeDetailCreator")
public class ScopeDetail implements SafeParcelable {
    public static final ScopeDetailCreator CREATOR;
    private static final int VERSION = 1;
    @Field(id = 2)
    String description;
    @Field(id = 3)
    String detail;
    @Field(id = 8)
    public FACLData friendPickerData;
    @Field(id = 4)
    String iconBase64;
    @Field(id = 5)
    String paclPickerDataBase64;
    @Field(id = 6)
    String service;
    @VersionField(id = 1)
    final int version;
    @Field(defaultValueUnchecked = "new java.util.ArrayList<String>()", id = 7)
    List<String> warnings;

    static {
        CREATOR = new ScopeDetailCreator();
    }

    @Constructor
    ScopeDetail(@Param(id = 1) int version, @Param(id = 2) String description, @Param(id = 3) String detail, @Param(id = 4) String iconBase64, @Param(id = 5) String paclPickerDataBase64, @Param(id = 6) String service, @Param(id = 7) List<String> warnings, @Param(id = 8) FACLData friendPickerData) {
        this.version = version;
        this.description = description;
        this.detail = detail;
        this.iconBase64 = iconBase64;
        this.paclPickerDataBase64 = paclPickerDataBase64;
        this.service = service;
        this.warnings = warnings;
        this.friendPickerData = friendPickerData;
    }

    public ScopeDetail(String service, String description, String detail, String iconBase64, String paclPickerDataBase64, FACLData faclData, List<String> warnings) {
        this.version = VERSION;
        this.service = service;
        this.description = description;
        this.detail = detail;
        this.iconBase64 = iconBase64;
        this.paclPickerDataBase64 = paclPickerDataBase64;
        this.friendPickerData = faclData;
        this.warnings = new ArrayList();
        this.warnings.addAll(warnings);
    }

    public void writeToParcel(Parcel dest, int flags) {
        ScopeDetailCreator.writeToParcel(this, dest, flags);
    }

    public int describeContents() {
        return 0;
    }

    public List<String> getUnmodifiableWarnings() {
        return Collections.unmodifiableList(this.warnings);
    }

    public String getDescription() {
        return this.description;
    }

    public String getDetail() {
        return this.detail;
    }

    public FACLData getFriendPickerData() {
        return this.friendPickerData;
    }

    public String getIconBase64() {
        return this.iconBase64;
    }

    public String getPaclPickerBase64() {
        return this.paclPickerDataBase64;
    }

    public String getService() {
        return this.service;
    }
}
