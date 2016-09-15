package com.google.android.gms.common.server.response;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.util.VisibleForTesting;

public abstract class FastSafeParcelableJsonResponse extends FastJsonResponse implements SafeParcelable {
    @VisibleForTesting
    public Object getValueObject(String key) {
        return null;
    }

    @VisibleForTesting
    public boolean isPrimitiveFieldSet(String outputField) {
        return false;
    }

    public byte[] toByteArray() {
        Parcel p = Parcel.obtain();
        writeToParcel(p, 0);
        byte[] out = p.marshall();
        p.recycle();
        return out;
    }
}
