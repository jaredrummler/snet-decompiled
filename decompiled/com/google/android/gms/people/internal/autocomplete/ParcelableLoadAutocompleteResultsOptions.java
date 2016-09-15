package com.google.android.gms.people.internal.autocomplete;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;

@Class(creator = "ParcelableLoadAutocompleteResultsOptionsCreator")
public class ParcelableLoadAutocompleteResultsOptions implements SafeParcelable {
    public static final ParcelableLoadAutocompleteResultsOptionsCreator CREATOR;
    private static final int VERSION_CODE = 1;
    @Field(id = 2)
    final int mClientId;
    @Field(id = 4)
    final String mQuery;
    @Field(id = 3)
    final long mSessionId;
    @VersionField(id = 1)
    final int mVersionCode;

    @Constructor
    ParcelableLoadAutocompleteResultsOptions(@Param(id = 1) int versionCode, @Param(id = 2) int clientId, @Param(id = 3) long sessionId, @Param(id = 4) String query) {
        this.mVersionCode = versionCode;
        this.mClientId = clientId;
        this.mSessionId = sessionId;
        this.mQuery = query;
    }

    public ParcelableLoadAutocompleteResultsOptions(int clientId, long sessionId, String query) {
        this(VERSION_CODE, clientId, sessionId, query);
    }

    public final int getClientId() {
        return this.mClientId;
    }

    public final long getSessionId() {
        return this.mSessionId;
    }

    public final String getQuery() {
        return this.mQuery;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        ParcelableLoadAutocompleteResultsOptionsCreator.writeToParcel(this, dest, flags);
    }

    static {
        CREATOR = new ParcelableLoadAutocompleteResultsOptionsCreator();
    }
}
