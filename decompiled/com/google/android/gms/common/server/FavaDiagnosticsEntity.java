package com.google.android.gms.common.server;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;
import com.google.android.gms.common.util.VisibleForTesting;

@Class(creator = "FavaDiagnosticsEntityCreator")
public class FavaDiagnosticsEntity implements SafeParcelable {
    public static final FavaDiagnosticsEntityCreator CREATOR;
    public static final String EXTRA_NAMESPACE = "namespace";
    public static final String EXTRA_TYPE_NUM = "typeNum";
    @VisibleForTesting
    static final int VERSION_CODE = 1;
    @VersionField(id = 1)
    final int mVersionCode;
    @Field(id = 2)
    public final String namespace;
    @Field(id = 3)
    public final int typeNum;

    static {
        CREATOR = new FavaDiagnosticsEntityCreator();
    }

    @Constructor
    public FavaDiagnosticsEntity(@Param(id = 1) int versionCode, @Param(id = 2) String namespace, @Param(id = 3) int typeNum) {
        this.mVersionCode = versionCode;
        this.namespace = namespace;
        this.typeNum = typeNum;
    }

    public FavaDiagnosticsEntity(String namespace, int typeNum) {
        this.mVersionCode = VERSION_CODE;
        this.namespace = namespace;
        this.typeNum = typeNum;
    }

    public void writeToParcel(Parcel out, int flags) {
        FavaDiagnosticsEntityCreator.writeToParcel(this, out, flags);
    }

    public int describeContents() {
        return 0;
    }
}
