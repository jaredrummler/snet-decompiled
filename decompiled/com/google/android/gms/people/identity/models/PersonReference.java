package com.google.android.gms.people.identity.models;

import android.os.Parcelable;
import com.google.android.gms.people.identity.internal.models.PersonReferenceBase;

public interface PersonReference extends PersonReferenceBase, Parcelable {
    ImageReference getAvatarReference();
}
