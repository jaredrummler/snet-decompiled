package com.google.android.gms.auth.firstparty.dataservice;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;

@Class(creator = "PostSignInDataCreator")
public class PostSignInData implements SafeParcelable {
    public static final PostSignInDataCreator CREATOR;
    private static final int VERSION = 1;
    @Field(id = 3)
    public final PendingIntent accountInstallationCompletionAction;
    @Field(id = 2)
    public final Intent postSignInForeignIntent;
    @VersionField(id = 1)
    final int version;

    static {
        CREATOR = new PostSignInDataCreator();
    }

    @Constructor
    PostSignInData(@Param(id = 1) int version, @Param(id = 2) Intent postSignInForeignIntent, @Param(id = 3) PendingIntent accountInstallationCompletionAction) {
        this.version = version;
        this.postSignInForeignIntent = postSignInForeignIntent;
        this.accountInstallationCompletionAction = accountInstallationCompletionAction;
    }

    public PostSignInData(Intent postSignInForeignIntent, PendingIntent accountInstallationCompletionAction) {
        this(VERSION, postSignInForeignIntent, accountInstallationCompletionAction);
    }

    public void writeToParcel(Parcel dest, int flags) {
        PostSignInDataCreator.writeToParcel(this, dest, flags);
    }

    public int describeContents() {
        return 0;
    }
}
