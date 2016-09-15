package com.google.android.gms.people.identity.internal.models;

import com.google.android.gms.common.util.VisibleForTesting;
import java.util.List;

@VisibleForTesting
public interface PersonBase {

    @VisibleForTesting
    public interface MetadataHolderBase {
        MetadataBase getMetadata();

        boolean hasMetadata();
    }

    @VisibleForTesting
    public interface AboutsBase extends MetadataHolderBase {
        String getType();

        String getValue();

        boolean hasType();

        boolean hasValue();
    }

    @VisibleForTesting
    public interface AddressesBase extends MetadataHolderBase {
        String getCity();

        String getCountry();

        String getCountryCode();

        String getFormattedType();

        String getPoBox();

        String getPostalCode();

        String getRegion();

        String getStreetAddress();

        String getType();

        String getValue();

        boolean hasCity();

        boolean hasCountry();

        boolean hasCountryCode();

        boolean hasFormattedType();

        boolean hasPoBox();

        boolean hasPostalCode();

        boolean hasRegion();

        boolean hasStreetAddress();

        boolean hasType();

        boolean hasValue();
    }

    @VisibleForTesting
    public interface BirthdaysBase extends MetadataHolderBase {
        String getDate();

        boolean hasDate();
    }

    @VisibleForTesting
    public interface BraggingRightsBase extends MetadataHolderBase {
        String getValue();

        boolean hasValue();
    }

    @VisibleForTesting
    public interface CoverPhotosBase {
        int getHeight();

        String getId();

        ImageReferenceBase getImageReference();

        int getWidth();

        boolean hasDefault();

        boolean hasHeight();

        boolean hasId();

        boolean hasImageReference();

        boolean hasWidth();

        boolean isDefault();
    }

    @VisibleForTesting
    public interface CustomFieldsBase {
        String getKey();

        String getValue();

        boolean hasKey();

        boolean hasValue();
    }

    @VisibleForTesting
    public interface EmailsBase extends MetadataHolderBase {
        String getFormattedType();

        int getTimesUsed();

        String getType();

        String getValue();

        boolean hasFormattedType();

        boolean hasTimesUsed();

        boolean hasType();

        boolean hasValue();
    }

    @VisibleForTesting
    public interface EventsBase extends MetadataHolderBase {
        String getDate();

        String getFormattedType();

        String getType();

        boolean hasDate();

        boolean hasFormattedType();

        boolean hasType();
    }

    @VisibleForTesting
    public interface GendersBase extends MetadataHolderBase {
        String getFormattedValue();

        String getValue();

        boolean hasFormattedValue();

        boolean hasValue();
    }

    @VisibleForTesting
    public interface ImagesBase extends MetadataHolderBase {
        ImageReferenceBase getImageReference();

        boolean hasDefault();

        boolean hasImageReference();

        boolean isDefault();
    }

    @VisibleForTesting
    public interface InstantMessagingBase extends MetadataHolderBase {
        String getFormattedProtocol();

        String getFormattedType();

        String getProtocol();

        String getType();

        String getValue();

        boolean hasFormattedProtocol();

        boolean hasFormattedType();

        boolean hasProtocol();

        boolean hasType();

        boolean hasValue();
    }

    @VisibleForTesting
    public interface LegacyFieldsBase {
        String getMobileOwnerId();

        boolean hasMobileOwnerId();
    }

    @VisibleForTesting
    public interface MembershipsBase extends MetadataHolderBase {
        String getCircle();

        String getContactGroup();

        String getSystemContactGroup();

        boolean hasCircle();

        boolean hasContactGroup();

        boolean hasSystemContactGroup();
    }

    @VisibleForTesting
    public interface MetadataBase {
        String getContainer();

        String getContainerContactId();

        String getContainerId();

        int getRawContactId();

        String getVisibility();

        boolean hasContainer();

        boolean hasContainerContactId();

        boolean hasContainerId();

        boolean hasEdgeKey();

        boolean hasPrimary();

        boolean hasRawContactId();

        boolean hasVerified();

        boolean hasVisibility();

        boolean hasWriteable();

        boolean isEdgeKey();

        boolean isPrimary();

        boolean isVerified();

        boolean isWriteable();
    }

    @VisibleForTesting
    public interface NamesBase extends MetadataHolderBase {
        String getDisplayName();

        String getFamilyName();

        String getFormatted();

        String getGivenName();

        String getHonorificPrefix();

        String getHonorificSuffix();

        String getMiddleName();

        String getPhoneticFamilyName();

        String getPhoneticGivenName();

        String getPhoneticHonorificPrefix();

        String getPhoneticHonorificSuffix();

        boolean hasDisplayName();

        boolean hasFamilyName();

        boolean hasFormatted();

        boolean hasGivenName();

        boolean hasHonorificPrefix();

        boolean hasHonorificSuffix();

        boolean hasMiddleName();

        boolean hasPhoneticFamilyName();

        boolean hasPhoneticGivenName();

        boolean hasPhoneticHonorificPrefix();

        boolean hasPhoneticHonorificSuffix();
    }

    @VisibleForTesting
    public interface NicknamesBase extends MetadataHolderBase {
        String getType();

        String getValue();

        boolean hasType();

        boolean hasValue();
    }

    @VisibleForTesting
    public interface NotesBase extends MetadataHolderBase {
        String getValue();

        boolean hasValue();
    }

    @VisibleForTesting
    public interface OccupationsBase extends MetadataHolderBase {
        String getValue();

        boolean hasValue();
    }

    @VisibleForTesting
    public interface OrganizationsBase extends MetadataHolderBase {
        String getDepartment();

        String getDescription();

        String getDomain();

        String getEndDate();

        String getLocation();

        String getName();

        String getPhoneticName();

        String getStartDate();

        String getSymbol();

        String getTitle();

        String getType();

        boolean hasCurrent();

        boolean hasDepartment();

        boolean hasDescription();

        boolean hasDomain();

        boolean hasEndDate();

        boolean hasLocation();

        boolean hasName();

        boolean hasPhoneticName();

        boolean hasStartDate();

        boolean hasSymbol();

        boolean hasTitle();

        boolean hasType();

        boolean isCurrent();
    }

    @VisibleForTesting
    public interface PersonMetadataBase {
        List<String> getAttributions();

        List<String> getBlockTypes();

        List<String> getCircles();

        List<String> getContacts();

        List<String> getGroups();

        List<String> getIncomingBlockTypes();

        String getObjectType();

        String getOwnerId();

        List<String> getOwnerUserTypes();

        String getPlusPageType();

        ProfileOwnerStatsBase getProfileOwnerStats();

        boolean hasAttributions();

        boolean hasBlockTypes();

        boolean hasBlocked();

        boolean hasCircles();

        boolean hasContacts();

        boolean hasDeleted();

        boolean hasGroups();

        boolean hasInViewerDomain();

        boolean hasIncomingBlockTypes();

        boolean hasObjectType();

        boolean hasOwnerId();

        boolean hasOwnerUserTypes();

        boolean hasPlusPageType();

        boolean hasProfileOwnerStats();

        boolean isBlocked();

        boolean isDeleted();

        boolean isInViewerDomain();
    }

    @VisibleForTesting
    public interface PhoneNumbersBase extends MetadataHolderBase {
        String getCanonicalizedForm();

        String getFormattedType();

        int getTimesUsed();

        String getType();

        String getValue();

        boolean hasCanonicalizedForm();

        boolean hasFormattedType();

        boolean hasTimesUsed();

        boolean hasType();

        boolean hasValue();
    }

    @VisibleForTesting
    public interface PlacesLivedBase extends MetadataHolderBase {
        String getValue();

        boolean hasCurrent();

        boolean hasValue();

        boolean isCurrent();
    }

    @VisibleForTesting
    public interface ProfileOwnerStatsBase {
        long getIncomingAnyCircleCount();

        long getViewCount();

        boolean hasIncomingAnyCircleCount();

        boolean hasViewCount();
    }

    @VisibleForTesting
    public interface RelationsBase extends MetadataHolderBase {
        String getFormattedType();

        String getType();

        String getValue();

        boolean hasFormattedType();

        boolean hasType();

        boolean hasValue();
    }

    @VisibleForTesting
    public interface RelationshipInterestsBase extends MetadataHolderBase {
        String getValue();

        boolean hasValue();
    }

    @VisibleForTesting
    public interface RelationshipStatusesBase extends MetadataHolderBase {
        String getFormattedValue();

        String getValue();

        boolean hasFormattedValue();

        boolean hasValue();
    }

    @VisibleForTesting
    public interface SkillsBase extends MetadataHolderBase {
        String getValue();

        boolean hasValue();
    }

    @VisibleForTesting
    public interface SortKeysBase {
        String getInteractionRank();

        String getName();

        boolean hasInteractionRank();

        boolean hasName();
    }

    @VisibleForTesting
    public interface TaglinesBase extends MetadataHolderBase {
        String getValue();

        boolean hasValue();
    }

    @VisibleForTesting
    public interface UrlsBase extends MetadataHolderBase {
        String getFormattedType();

        String getType();

        String getValue();

        boolean hasFormattedType();

        boolean hasType();

        boolean hasValue();
    }

    List<? extends AboutsBase> getAbouts();

    List<? extends AddressesBase> getAddresses();

    String getAgeRange();

    List<? extends BirthdaysBase> getBirthdays();

    List<? extends BraggingRightsBase> getBraggingRights();

    List<? extends CoverPhotosBase> getCoverPhotos();

    List<? extends CustomFieldsBase> getCustomFields();

    List<? extends EmailsBase> getEmails();

    String getEtag();

    List<? extends EventsBase> getEvents();

    List<? extends GendersBase> getGenders();

    String getId();

    List<? extends ImagesBase> getImages();

    List<? extends InstantMessagingBase> getInstantMessaging();

    String getLanguage();

    LegacyFieldsBase getLegacyFields();

    List<? extends PersonBase> getLinkedPeople();

    List<? extends MembershipsBase> getMemberships();

    PersonMetadataBase getMetadata();

    List<? extends NamesBase> getNames();

    List<? extends NicknamesBase> getNicknames();

    List<? extends NotesBase> getNotes();

    List<? extends OccupationsBase> getOccupations();

    List<? extends OrganizationsBase> getOrganizations();

    List<? extends PhoneNumbersBase> getPhoneNumbers();

    List<? extends PlacesLivedBase> getPlacesLived();

    String getProfileUrl();

    List<? extends RelationsBase> getRelations();

    List<? extends RelationshipInterestsBase> getRelationshipInterests();

    List<? extends RelationshipStatusesBase> getRelationshipStatuses();

    List<? extends SkillsBase> getSkills();

    SortKeysBase getSortKeys();

    List<? extends TaglinesBase> getTaglines();

    List<? extends UrlsBase> getUrls();

    boolean hasAbouts();

    boolean hasAddresses();

    boolean hasAgeRange();

    boolean hasBirthdays();

    boolean hasBraggingRights();

    boolean hasCoverPhotos();

    boolean hasCustomFields();

    boolean hasEmails();

    boolean hasEtag();

    boolean hasEvents();

    boolean hasGenders();

    boolean hasId();

    boolean hasImages();

    boolean hasInstantMessaging();

    boolean hasLanguage();

    boolean hasLegacyFields();

    boolean hasLinkedPeople();

    boolean hasMemberships();

    boolean hasMetadata();

    boolean hasNames();

    boolean hasNicknames();

    boolean hasNotes();

    boolean hasOccupations();

    boolean hasOrganizations();

    boolean hasPhoneNumbers();

    boolean hasPlacesLived();

    boolean hasProfileUrl();

    boolean hasRelations();

    boolean hasRelationshipInterests();

    boolean hasRelationshipStatuses();

    boolean hasSkills();

    boolean hasSortKeys();

    boolean hasTaglines();

    boolean hasUrls();
}
