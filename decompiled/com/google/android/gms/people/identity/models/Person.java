package com.google.android.gms.people.identity.models;

import android.os.Parcelable;
import com.google.android.gms.people.identity.internal.models.PersonBase;
import com.google.android.gms.people.identity.internal.models.PersonBase.AboutsBase;
import com.google.android.gms.people.identity.internal.models.PersonBase.AddressesBase;
import com.google.android.gms.people.identity.internal.models.PersonBase.BirthdaysBase;
import com.google.android.gms.people.identity.internal.models.PersonBase.BraggingRightsBase;
import com.google.android.gms.people.identity.internal.models.PersonBase.CoverPhotosBase;
import com.google.android.gms.people.identity.internal.models.PersonBase.CustomFieldsBase;
import com.google.android.gms.people.identity.internal.models.PersonBase.EmailsBase;
import com.google.android.gms.people.identity.internal.models.PersonBase.EventsBase;
import com.google.android.gms.people.identity.internal.models.PersonBase.GendersBase;
import com.google.android.gms.people.identity.internal.models.PersonBase.ImagesBase;
import com.google.android.gms.people.identity.internal.models.PersonBase.InstantMessagingBase;
import com.google.android.gms.people.identity.internal.models.PersonBase.LegacyFieldsBase;
import com.google.android.gms.people.identity.internal.models.PersonBase.MembershipsBase;
import com.google.android.gms.people.identity.internal.models.PersonBase.MetadataBase;
import com.google.android.gms.people.identity.internal.models.PersonBase.MetadataHolderBase;
import com.google.android.gms.people.identity.internal.models.PersonBase.NamesBase;
import com.google.android.gms.people.identity.internal.models.PersonBase.NicknamesBase;
import com.google.android.gms.people.identity.internal.models.PersonBase.NotesBase;
import com.google.android.gms.people.identity.internal.models.PersonBase.OccupationsBase;
import com.google.android.gms.people.identity.internal.models.PersonBase.OrganizationsBase;
import com.google.android.gms.people.identity.internal.models.PersonBase.PersonMetadataBase;
import com.google.android.gms.people.identity.internal.models.PersonBase.PhoneNumbersBase;
import com.google.android.gms.people.identity.internal.models.PersonBase.PlacesLivedBase;
import com.google.android.gms.people.identity.internal.models.PersonBase.ProfileOwnerStatsBase;
import com.google.android.gms.people.identity.internal.models.PersonBase.RelationsBase;
import com.google.android.gms.people.identity.internal.models.PersonBase.RelationshipInterestsBase;
import com.google.android.gms.people.identity.internal.models.PersonBase.RelationshipStatusesBase;
import com.google.android.gms.people.identity.internal.models.PersonBase.SkillsBase;
import com.google.android.gms.people.identity.internal.models.PersonBase.SortKeysBase;
import com.google.android.gms.people.identity.internal.models.PersonBase.TaglinesBase;
import com.google.android.gms.people.identity.internal.models.PersonBase.UrlsBase;
import java.util.List;

public interface Person extends PersonBase, Parcelable {

    public interface MetadataHolder extends MetadataHolderBase, Parcelable {
        Metadata getMetadata();
    }

    public interface Abouts extends MetadataHolder, AboutsBase, Parcelable {
    }

    public interface Addresses extends MetadataHolder, AddressesBase, Parcelable {
    }

    public interface Birthdays extends MetadataHolder, BirthdaysBase, Parcelable {
    }

    public interface BraggingRights extends MetadataHolder, BraggingRightsBase, Parcelable {
    }

    public interface CoverPhotos extends CoverPhotosBase, Parcelable {
        ImageReference getImageReference();
    }

    public interface CustomFields extends CustomFieldsBase, Parcelable {
    }

    public interface Emails extends MetadataHolder, EmailsBase, Parcelable {
    }

    public interface Events extends MetadataHolder, EventsBase, Parcelable {
    }

    public interface Genders extends MetadataHolder, GendersBase, Parcelable {
    }

    public interface Images extends MetadataHolder, ImagesBase, Parcelable {
        ImageReference getImageReference();
    }

    public interface InstantMessaging extends MetadataHolder, InstantMessagingBase, Parcelable {
    }

    public interface LegacyFields extends LegacyFieldsBase, Parcelable {
    }

    public interface Memberships extends MetadataHolder, MembershipsBase, Parcelable {
    }

    public interface Metadata extends MetadataBase, Parcelable {
    }

    public interface Names extends MetadataHolder, NamesBase, Parcelable {
    }

    public interface Nicknames extends MetadataHolder, NicknamesBase, Parcelable {
    }

    public interface Notes extends MetadataHolder, NotesBase, Parcelable {
    }

    public interface Occupations extends MetadataHolder, OccupationsBase, Parcelable {
    }

    public interface Organizations extends MetadataHolder, OrganizationsBase, Parcelable {
    }

    public interface PersonMetadata extends PersonMetadataBase, Parcelable {
        ProfileOwnerStats getProfileOwnerStats();
    }

    public interface PhoneNumbers extends MetadataHolder, PhoneNumbersBase, Parcelable {
    }

    public interface PlacesLived extends MetadataHolder, PlacesLivedBase, Parcelable {
    }

    public interface ProfileOwnerStats extends ProfileOwnerStatsBase, Parcelable {
    }

    public interface Relations extends MetadataHolder, RelationsBase, Parcelable {
    }

    public interface RelationshipInterests extends MetadataHolder, RelationshipInterestsBase, Parcelable {
    }

    public interface RelationshipStatuses extends MetadataHolder, RelationshipStatusesBase, Parcelable {
    }

    public interface Skills extends MetadataHolder, SkillsBase, Parcelable {
    }

    public interface SortKeys extends SortKeysBase, Parcelable {
    }

    public interface Taglines extends MetadataHolder, TaglinesBase, Parcelable {
    }

    public interface Urls extends MetadataHolder, UrlsBase, Parcelable {
    }

    List<? extends Abouts> getAbouts();

    List<? extends Addresses> getAddresses();

    List<? extends Birthdays> getBirthdays();

    List<? extends BraggingRights> getBraggingRights();

    List<? extends CoverPhotos> getCoverPhotos();

    List<? extends CustomFields> getCustomFields();

    List<? extends Emails> getEmails();

    List<? extends Events> getEvents();

    List<? extends Genders> getGenders();

    List<? extends Images> getImages();

    List<? extends InstantMessaging> getInstantMessaging();

    LegacyFields getLegacyFields();

    List<? extends Person> getLinkedPeople();

    List<? extends Memberships> getMemberships();

    PersonMetadata getMetadata();

    List<? extends Names> getNames();

    List<? extends Nicknames> getNicknames();

    List<? extends Notes> getNotes();

    List<? extends Occupations> getOccupations();

    List<? extends Organizations> getOrganizations();

    List<? extends PhoneNumbers> getPhoneNumbers();

    List<? extends PlacesLived> getPlacesLived();

    List<? extends Relations> getRelations();

    List<? extends RelationshipInterests> getRelationshipInterests();

    List<? extends RelationshipStatuses> getRelationshipStatuses();

    List<? extends Skills> getSkills();

    SortKeys getSortKeys();

    List<? extends Taglines> getTaglines();

    List<? extends Urls> getUrls();
}
