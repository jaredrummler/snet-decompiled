package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.annotation.KeepName;

@KeepName
public final class BinderWrapper implements Parcelable {
    public static final Creator<BinderWrapper> CREATOR;
    private IBinder mBinder;

    static {
        CREATOR = new Creator<BinderWrapper>() {
            public BinderWrapper createFromParcel(Parcel in) {
                return new BinderWrapper(null);
            }

            public BinderWrapper[] newArray(int size) {
                return new BinderWrapper[size];
            }
        };
    }

    public BinderWrapper() {
        this.mBinder = null;
    }

    public BinderWrapper(IBinder binder) {
        this.mBinder = null;
        this.mBinder = binder;
    }

    private BinderWrapper(Parcel in) {
        this.mBinder = null;
        this.mBinder = in.readStrongBinder();
    }

    public IBinder getBinder() {
        return this.mBinder;
    }

    public void setBinder(IBinder binder) {
        this.mBinder = binder;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStrongBinder(this.mBinder);
    }
}
