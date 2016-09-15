package com.google.android.gms.auth.firstparty.proximity.data;

import android.os.Parcel;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;
import java.util.Arrays;

@Class(creator = "PermitAccessCreator")
public class PermitAccess implements SafeParcelable {
    public static final PermitAccessCreator CREATOR;
    public static final String TYPE_AES = "AES";
    public static final String TYPE_AUTHZEN_PUBLIC_KEY = "AUTHZEN_PUBLIC_KEY";
    private static final int VERSION = 1;
    @Field(id = 4)
    final byte[] mData;
    @Field(id = 2)
    final String mId;
    @Field(id = 3)
    final String mType;
    @VersionField(id = 1)
    final int mVersion;

    static {
        CREATOR = new PermitAccessCreator();
    }

    public PermitAccess(String id, String type, byte[] data) {
        this(VERSION, id, type, data);
    }

    @Constructor
    PermitAccess(@Param(id = 1) int version, @Param(id = 2) String id, @Param(id = 3) String type, @Param(id = 4) byte[] data) {
        this.mVersion = version;
        this.mId = Preconditions.checkNotEmpty(id);
        this.mType = Preconditions.checkNotEmpty(type);
        this.mData = (byte[]) Preconditions.checkNotNull(data);
    }

    public String getId() {
        return this.mId;
    }

    public String getType() {
        return this.mType;
    }

    public byte[] getData() {
        return this.mData;
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof PermitAccess)) {
            return false;
        }
        PermitAccess requester = (PermitAccess) obj;
        if (!(TextUtils.equals(this.mId, requester.mId) && TextUtils.equals(this.mType, requester.mType) && Arrays.equals(this.mData, requester.mData))) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        return (31 * ((31 * (this.mId.hashCode() + 527)) + this.mType.hashCode())) + Arrays.hashCode(this.mData);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        PermitAccessCreator.writeToParcel(this, dest, flags);
    }
}
