package com.google.android.gms.people.internal.autocomplete;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.PendingResults;
import com.google.android.gms.common.api.internal.PendingResultFacade;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;
import com.google.android.gms.people.Autocomplete.Email;
import com.google.android.gms.people.Autocomplete.LoadPhotoOptions;
import com.google.android.gms.people.Autocomplete.LoadPhotoResult;
import com.google.android.gms.people.Autocomplete.Name;
import com.google.android.gms.people.Autocomplete.Person;
import com.google.android.gms.people.Autocomplete.PersonMetadata;
import com.google.android.gms.people.Autocomplete.Phone;
import com.google.android.gms.people.Autocomplete.Photo;
import com.google.android.gms.people.Images.LoadImageOptions.Builder;
import com.google.android.gms.people.Images.LoadImageResult;
import com.google.android.gms.people.People;
import com.google.android.gms.people.model.AvatarReference;

@Class(creator = "PersonImplCreator")
public class PersonImpl implements Person, SafeParcelable {
    public static final Creator<PersonImpl> CREATOR;
    private static final int VERSION_CODE = 1;
    @Field(id = 4)
    final EmailImpl[] mEmails;
    @Field(id = 2)
    final PersonMetadataImpl mMetadata;
    @Field(id = 3)
    final NameImpl[] mNames;
    @Field(id = 5)
    final PhoneImpl[] mPhones;
    @Field(id = 6)
    final PhotoImpl[] mPhotos;
    @VersionField(id = 1)
    final int mVersionCode;

    /* renamed from: com.google.android.gms.people.internal.autocomplete.PersonImpl.1 */
    static class AnonymousClass1 extends PendingResultFacade<LoadImageResult, LoadPhotoResult> {
        AnonymousClass1(PendingResult x0) {
            super(x0);
        }

        protected LoadPhotoResult translate(LoadImageResult value) {
            return new LoadPhotoResult(value.getStatus(), value.getParcelFileDescriptor(), value.isRewindable(), value.getWidth(), value.getHeight());
        }
    }

    static {
        CREATOR = new PersonImplCreator();
    }

    @Constructor
    PersonImpl(@Param(id = 1) int versionCode, @Param(id = 2) PersonMetadataImpl metadata, @Param(id = 3) NameImpl[] names, @Param(id = 4) EmailImpl[] emails, @Param(id = 5) PhoneImpl[] phones, @Param(id = 6) PhotoImpl[] photos) {
        this.mVersionCode = versionCode;
        this.mMetadata = metadata;
        this.mNames = names;
        this.mEmails = emails;
        this.mPhones = phones;
        this.mPhotos = photos;
    }

    public PersonImpl(PersonMetadataImpl metadata, NameImpl[] names, EmailImpl[] emails, PhoneImpl[] phones, PhotoImpl[] photos) {
        this(VERSION_CODE, metadata, names, emails, phones, photos);
    }

    public PersonMetadata getMetadata() {
        return this.mMetadata;
    }

    public Name[] getNames() {
        return this.mNames;
    }

    public Email[] getEmails() {
        return this.mEmails;
    }

    public Phone[] getPhones() {
        return this.mPhones;
    }

    public Photo[] getPhotos() {
        return this.mPhotos;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        PersonImplCreator.writeToParcel(this, dest, flags);
    }

    public PendingResult<LoadPhotoResult> loadPrimaryPhoto(GoogleApiClient client, LoadPhotoOptions photoOptions) {
        Preconditions.checkNotNull(client);
        PhotoImpl[] arr$ = this.mPhotos;
        int len$ = arr$.length;
        for (int i$ = 0; i$ < len$; i$ += VERSION_CODE) {
            PhotoImpl photo = arr$[i$];
            if (photo.isDefault()) {
                return loadPhoto(client, photo, photoOptions);
            }
        }
        return PendingResults.immediatePendingResult(LoadPhotoResult.FAILED_RESULT);
    }

    private PendingResult<LoadPhotoResult> loadPhoto(GoogleApiClient client, PhotoImpl photo, LoadPhotoOptions photoOptions) {
        Preconditions.checkNotNull(client);
        return createFacade(People.ImageApi.loadByReference(client, new AvatarReference(photo.getSource(), photo.getLocation()), new Builder().setImageSize(photoOptions.getImageSize()).setAvatarOptions(photoOptions.getPhotoOptions()).build()));
    }

    private static PendingResult<LoadPhotoResult> createFacade(PendingResult<LoadImageResult> pendingResult) {
        return new AnonymousClass1(pendingResult);
    }
}
