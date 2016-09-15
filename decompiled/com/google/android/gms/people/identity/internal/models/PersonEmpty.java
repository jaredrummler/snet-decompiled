package com.google.android.gms.people.identity.internal.models;

import com.google.android.gms.common.util.VisibleForTesting;
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

@VisibleForTesting
public class PersonEmpty implements PersonBase {

    @VisibleForTesting
    public class AboutsEmpty implements AboutsBase {
        public boolean hasMetadata() {
            return false;
        }

        public MetadataBase getMetadata() {
            return null;
        }

        public boolean hasType() {
            return false;
        }

        public String getType() {
            return null;
        }

        public boolean hasValue() {
            return false;
        }

        public String getValue() {
            return null;
        }
    }

    @VisibleForTesting
    public class AddressesEmpty implements AddressesBase {
        public boolean hasMetadata() {
            return false;
        }

        public MetadataBase getMetadata() {
            return null;
        }

        public boolean hasCity() {
            return false;
        }

        public String getCity() {
            return null;
        }

        public boolean hasCountry() {
            return false;
        }

        public String getCountry() {
            return null;
        }

        public boolean hasCountryCode() {
            return false;
        }

        public String getCountryCode() {
            return null;
        }

        public boolean hasFormattedType() {
            return false;
        }

        public String getFormattedType() {
            return null;
        }

        public boolean hasPoBox() {
            return false;
        }

        public String getPoBox() {
            return null;
        }

        public boolean hasPostalCode() {
            return false;
        }

        public String getPostalCode() {
            return null;
        }

        public boolean hasRegion() {
            return false;
        }

        public String getRegion() {
            return null;
        }

        public boolean hasStreetAddress() {
            return false;
        }

        public String getStreetAddress() {
            return null;
        }

        public boolean hasType() {
            return false;
        }

        public String getType() {
            return null;
        }

        public boolean hasValue() {
            return false;
        }

        public String getValue() {
            return null;
        }
    }

    @VisibleForTesting
    public class BirthdaysEmpty implements BirthdaysBase {
        public boolean hasMetadata() {
            return false;
        }

        public MetadataBase getMetadata() {
            return null;
        }

        public boolean hasDate() {
            return false;
        }

        public String getDate() {
            return null;
        }
    }

    @VisibleForTesting
    public class BraggingRightsEmpty implements BraggingRightsBase {
        public boolean hasMetadata() {
            return false;
        }

        public MetadataBase getMetadata() {
            return null;
        }

        public boolean hasValue() {
            return false;
        }

        public String getValue() {
            return null;
        }
    }

    @VisibleForTesting
    public class CoverPhotosEmpty implements CoverPhotosBase {
        public boolean hasHeight() {
            return false;
        }

        public int getHeight() {
            return ((Integer) null).intValue();
        }

        public boolean hasId() {
            return false;
        }

        public String getId() {
            return null;
        }

        public boolean hasImageReference() {
            return false;
        }

        public ImageReferenceBase getImageReference() {
            return null;
        }

        public boolean hasWidth() {
            return false;
        }

        public int getWidth() {
            return ((Integer) null).intValue();
        }

        public boolean hasDefault() {
            return false;
        }

        public boolean isDefault() {
            return false;
        }
    }

    @VisibleForTesting
    public class CustomFieldsEmpty implements CustomFieldsBase {
        public boolean hasKey() {
            return false;
        }

        public String getKey() {
            return null;
        }

        public boolean hasValue() {
            return false;
        }

        public String getValue() {
            return null;
        }
    }

    @VisibleForTesting
    public class EmailsEmpty implements EmailsBase {
        public boolean hasMetadata() {
            return false;
        }

        public MetadataBase getMetadata() {
            return null;
        }

        public boolean hasFormattedType() {
            return false;
        }

        public String getFormattedType() {
            return null;
        }

        public boolean hasType() {
            return false;
        }

        public String getType() {
            return null;
        }

        public boolean hasValue() {
            return false;
        }

        public String getValue() {
            return null;
        }

        public boolean hasTimesUsed() {
            return false;
        }

        public int getTimesUsed() {
            return ((Integer) null).intValue();
        }
    }

    @VisibleForTesting
    public class EventsEmpty implements EventsBase {
        public boolean hasMetadata() {
            return false;
        }

        public MetadataBase getMetadata() {
            return null;
        }

        public boolean hasFormattedType() {
            return false;
        }

        public String getFormattedType() {
            return null;
        }

        public boolean hasType() {
            return false;
        }

        public String getType() {
            return null;
        }

        public boolean hasDate() {
            return false;
        }

        public String getDate() {
            return null;
        }
    }

    @VisibleForTesting
    public class GendersEmpty implements GendersBase {
        public boolean hasMetadata() {
            return false;
        }

        public MetadataBase getMetadata() {
            return null;
        }

        public boolean hasFormattedValue() {
            return false;
        }

        public String getFormattedValue() {
            return null;
        }

        public boolean hasValue() {
            return false;
        }

        public String getValue() {
            return null;
        }
    }

    @VisibleForTesting
    public class ImagesEmpty implements ImagesBase {
        public boolean hasMetadata() {
            return false;
        }

        public MetadataBase getMetadata() {
            return null;
        }

        public boolean hasImageReference() {
            return false;
        }

        public ImageReferenceBase getImageReference() {
            return null;
        }

        public boolean hasDefault() {
            return false;
        }

        public boolean isDefault() {
            return false;
        }
    }

    @VisibleForTesting
    public class InstantMessagingEmpty implements InstantMessagingBase {
        public boolean hasMetadata() {
            return false;
        }

        public MetadataBase getMetadata() {
            return null;
        }

        public boolean hasFormattedProtocol() {
            return false;
        }

        public String getFormattedProtocol() {
            return null;
        }

        public boolean hasFormattedType() {
            return false;
        }

        public String getFormattedType() {
            return null;
        }

        public boolean hasProtocol() {
            return false;
        }

        public String getProtocol() {
            return null;
        }

        public boolean hasType() {
            return false;
        }

        public String getType() {
            return null;
        }

        public boolean hasValue() {
            return false;
        }

        public String getValue() {
            return null;
        }
    }

    @VisibleForTesting
    public class LegacyFieldsEmpty implements LegacyFieldsBase {
        public boolean hasMobileOwnerId() {
            return false;
        }

        public String getMobileOwnerId() {
            return null;
        }
    }

    @VisibleForTesting
    public class MembershipsEmpty implements MembershipsBase {
        public boolean hasMetadata() {
            return false;
        }

        public MetadataBase getMetadata() {
            return null;
        }

        public boolean hasCircle() {
            return false;
        }

        public String getCircle() {
            return null;
        }

        public boolean hasContactGroup() {
            return false;
        }

        public String getContactGroup() {
            return null;
        }

        public boolean hasSystemContactGroup() {
            return false;
        }

        public String getSystemContactGroup() {
            return null;
        }
    }

    @VisibleForTesting
    public class MetadataEmpty implements MetadataBase {
        public boolean hasContainer() {
            return false;
        }

        public String getContainer() {
            return null;
        }

        public boolean hasContainerContactId() {
            return false;
        }

        public String getContainerContactId() {
            return null;
        }

        public boolean hasContainerId() {
            return false;
        }

        public String getContainerId() {
            return null;
        }

        public boolean hasVisibility() {
            return false;
        }

        public String getVisibility() {
            return null;
        }

        public boolean hasEdgeKey() {
            return false;
        }

        public boolean isEdgeKey() {
            return false;
        }

        public boolean hasPrimary() {
            return false;
        }

        public boolean isPrimary() {
            return false;
        }

        public boolean hasVerified() {
            return false;
        }

        public boolean isVerified() {
            return false;
        }

        public boolean hasWriteable() {
            return false;
        }

        public boolean isWriteable() {
            return false;
        }

        public boolean hasRawContactId() {
            return false;
        }

        public int getRawContactId() {
            return ((Integer) null).intValue();
        }
    }

    @VisibleForTesting
    public class MetadataHolderEmpty implements MetadataHolderBase {
        public boolean hasMetadata() {
            return false;
        }

        public MetadataBase getMetadata() {
            return null;
        }
    }

    @VisibleForTesting
    public class NamesEmpty implements NamesBase {
        public boolean hasMetadata() {
            return false;
        }

        public MetadataBase getMetadata() {
            return null;
        }

        public boolean hasDisplayName() {
            return false;
        }

        public String getDisplayName() {
            return null;
        }

        public boolean hasFamilyName() {
            return false;
        }

        public String getFamilyName() {
            return null;
        }

        public boolean hasFormatted() {
            return false;
        }

        public String getFormatted() {
            return null;
        }

        public boolean hasGivenName() {
            return false;
        }

        public String getGivenName() {
            return null;
        }

        public boolean hasHonorificPrefix() {
            return false;
        }

        public String getHonorificPrefix() {
            return null;
        }

        public boolean hasHonorificSuffix() {
            return false;
        }

        public String getHonorificSuffix() {
            return null;
        }

        public boolean hasMiddleName() {
            return false;
        }

        public String getMiddleName() {
            return null;
        }

        public boolean hasPhoneticFamilyName() {
            return false;
        }

        public String getPhoneticFamilyName() {
            return null;
        }

        public boolean hasPhoneticGivenName() {
            return false;
        }

        public String getPhoneticGivenName() {
            return null;
        }

        public boolean hasPhoneticHonorificPrefix() {
            return false;
        }

        public String getPhoneticHonorificPrefix() {
            return null;
        }

        public boolean hasPhoneticHonorificSuffix() {
            return false;
        }

        public String getPhoneticHonorificSuffix() {
            return null;
        }
    }

    @VisibleForTesting
    public class NicknamesEmpty implements NicknamesBase {
        public boolean hasMetadata() {
            return false;
        }

        public MetadataBase getMetadata() {
            return null;
        }

        public boolean hasType() {
            return false;
        }

        public String getType() {
            return null;
        }

        public boolean hasValue() {
            return false;
        }

        public String getValue() {
            return null;
        }
    }

    @VisibleForTesting
    public class NotesEmpty implements NotesBase {
        public boolean hasMetadata() {
            return false;
        }

        public MetadataBase getMetadata() {
            return null;
        }

        public boolean hasValue() {
            return false;
        }

        public String getValue() {
            return null;
        }
    }

    @VisibleForTesting
    public class OccupationsEmpty implements OccupationsBase {
        public boolean hasMetadata() {
            return false;
        }

        public MetadataBase getMetadata() {
            return null;
        }

        public boolean hasValue() {
            return false;
        }

        public String getValue() {
            return null;
        }
    }

    @VisibleForTesting
    public class OrganizationsEmpty implements OrganizationsBase {
        public boolean hasMetadata() {
            return false;
        }

        public MetadataBase getMetadata() {
            return null;
        }

        public boolean hasCurrent() {
            return false;
        }

        public boolean isCurrent() {
            return false;
        }

        public boolean hasDepartment() {
            return false;
        }

        public String getDepartment() {
            return null;
        }

        public boolean hasDescription() {
            return false;
        }

        public String getDescription() {
            return null;
        }

        public boolean hasDomain() {
            return false;
        }

        public String getDomain() {
            return null;
        }

        public boolean hasEndDate() {
            return false;
        }

        public String getEndDate() {
            return null;
        }

        public boolean hasLocation() {
            return false;
        }

        public String getLocation() {
            return null;
        }

        public boolean hasName() {
            return false;
        }

        public String getName() {
            return null;
        }

        public boolean hasPhoneticName() {
            return false;
        }

        public String getPhoneticName() {
            return null;
        }

        public boolean hasStartDate() {
            return false;
        }

        public String getStartDate() {
            return null;
        }

        public boolean hasSymbol() {
            return false;
        }

        public String getSymbol() {
            return null;
        }

        public boolean hasTitle() {
            return false;
        }

        public String getTitle() {
            return null;
        }

        public boolean hasType() {
            return false;
        }

        public String getType() {
            return null;
        }
    }

    @VisibleForTesting
    public class PersonMetadataEmpty implements PersonMetadataBase {
        public boolean hasAttributions() {
            return false;
        }

        public List<String> getAttributions() {
            return null;
        }

        public boolean hasBlockTypes() {
            return false;
        }

        public List<String> getBlockTypes() {
            return null;
        }

        public boolean hasCircles() {
            return false;
        }

        public List<String> getCircles() {
            return null;
        }

        public boolean hasContacts() {
            return false;
        }

        public List<String> getContacts() {
            return null;
        }

        public boolean hasGroups() {
            return false;
        }

        public List<String> getGroups() {
            return null;
        }

        public boolean hasIncomingBlockTypes() {
            return false;
        }

        public List<String> getIncomingBlockTypes() {
            return null;
        }

        public boolean hasObjectType() {
            return false;
        }

        public String getObjectType() {
            return null;
        }

        public boolean hasOwnerId() {
            return false;
        }

        public String getOwnerId() {
            return null;
        }

        public boolean hasOwnerUserTypes() {
            return false;
        }

        public List<String> getOwnerUserTypes() {
            return null;
        }

        public boolean hasPlusPageType() {
            return false;
        }

        public String getPlusPageType() {
            return null;
        }

        public boolean hasProfileOwnerStats() {
            return false;
        }

        public ProfileOwnerStatsBase getProfileOwnerStats() {
            return null;
        }

        public boolean hasBlocked() {
            return false;
        }

        public boolean isBlocked() {
            return false;
        }

        public boolean hasDeleted() {
            return false;
        }

        public boolean isDeleted() {
            return false;
        }

        public boolean hasInViewerDomain() {
            return false;
        }

        public boolean isInViewerDomain() {
            return false;
        }
    }

    @VisibleForTesting
    public class PhoneNumbersEmpty implements PhoneNumbersBase {
        public boolean hasMetadata() {
            return false;
        }

        public MetadataBase getMetadata() {
            return null;
        }

        public boolean hasCanonicalizedForm() {
            return false;
        }

        public String getCanonicalizedForm() {
            return null;
        }

        public boolean hasFormattedType() {
            return false;
        }

        public String getFormattedType() {
            return null;
        }

        public boolean hasType() {
            return false;
        }

        public String getType() {
            return null;
        }

        public boolean hasValue() {
            return false;
        }

        public String getValue() {
            return null;
        }

        public boolean hasTimesUsed() {
            return false;
        }

        public int getTimesUsed() {
            return ((Integer) null).intValue();
        }
    }

    @VisibleForTesting
    public class PlacesLivedEmpty implements PlacesLivedBase {
        public boolean hasMetadata() {
            return false;
        }

        public MetadataBase getMetadata() {
            return null;
        }

        public boolean hasCurrent() {
            return false;
        }

        public boolean isCurrent() {
            return false;
        }

        public boolean hasValue() {
            return false;
        }

        public String getValue() {
            return null;
        }
    }

    @VisibleForTesting
    public class ProfileOwnerStatsEmpty implements ProfileOwnerStatsBase {
        public boolean hasIncomingAnyCircleCount() {
            return false;
        }

        public long getIncomingAnyCircleCount() {
            return ((Long) null).longValue();
        }

        public boolean hasViewCount() {
            return false;
        }

        public long getViewCount() {
            return ((Long) null).longValue();
        }
    }

    @VisibleForTesting
    public class RelationsEmpty implements RelationsBase {
        public boolean hasMetadata() {
            return false;
        }

        public MetadataBase getMetadata() {
            return null;
        }

        public boolean hasFormattedType() {
            return false;
        }

        public String getFormattedType() {
            return null;
        }

        public boolean hasType() {
            return false;
        }

        public String getType() {
            return null;
        }

        public boolean hasValue() {
            return false;
        }

        public String getValue() {
            return null;
        }
    }

    @VisibleForTesting
    public class RelationshipInterestsEmpty implements RelationshipInterestsBase {
        public boolean hasMetadata() {
            return false;
        }

        public MetadataBase getMetadata() {
            return null;
        }

        public boolean hasValue() {
            return false;
        }

        public String getValue() {
            return null;
        }
    }

    @VisibleForTesting
    public class RelationshipStatusesEmpty implements RelationshipStatusesBase {
        public boolean hasMetadata() {
            return false;
        }

        public MetadataBase getMetadata() {
            return null;
        }

        public boolean hasFormattedValue() {
            return false;
        }

        public String getFormattedValue() {
            return null;
        }

        public boolean hasValue() {
            return false;
        }

        public String getValue() {
            return null;
        }
    }

    @VisibleForTesting
    public class SkillsEmpty implements SkillsBase {
        public boolean hasMetadata() {
            return false;
        }

        public MetadataBase getMetadata() {
            return null;
        }

        public boolean hasValue() {
            return false;
        }

        public String getValue() {
            return null;
        }
    }

    @VisibleForTesting
    public class SortKeysEmpty implements SortKeysBase {
        public boolean hasInteractionRank() {
            return false;
        }

        public String getInteractionRank() {
            return null;
        }

        public boolean hasName() {
            return false;
        }

        public String getName() {
            return null;
        }
    }

    @VisibleForTesting
    public class TaglinesEmpty implements TaglinesBase {
        public boolean hasMetadata() {
            return false;
        }

        public MetadataBase getMetadata() {
            return null;
        }

        public boolean hasValue() {
            return false;
        }

        public String getValue() {
            return null;
        }
    }

    @VisibleForTesting
    public class UrlsEmpty implements UrlsBase {
        public boolean hasMetadata() {
            return false;
        }

        public MetadataBase getMetadata() {
            return null;
        }

        public boolean hasFormattedType() {
            return false;
        }

        public String getFormattedType() {
            return null;
        }

        public boolean hasType() {
            return false;
        }

        public String getType() {
            return null;
        }

        public boolean hasValue() {
            return false;
        }

        public String getValue() {
            return null;
        }
    }

    public boolean hasAbouts() {
        return false;
    }

    public List<? extends AboutsBase> getAbouts() {
        return null;
    }

    public boolean hasAddresses() {
        return false;
    }

    public List<? extends AddressesBase> getAddresses() {
        return null;
    }

    public boolean hasAgeRange() {
        return false;
    }

    public String getAgeRange() {
        return null;
    }

    public boolean hasBirthdays() {
        return false;
    }

    public List<? extends BirthdaysBase> getBirthdays() {
        return null;
    }

    public boolean hasBraggingRights() {
        return false;
    }

    public List<? extends BraggingRightsBase> getBraggingRights() {
        return null;
    }

    public boolean hasCoverPhotos() {
        return false;
    }

    public List<? extends CoverPhotosBase> getCoverPhotos() {
        return null;
    }

    public boolean hasCustomFields() {
        return false;
    }

    public List<? extends CustomFieldsBase> getCustomFields() {
        return null;
    }

    public boolean hasEmails() {
        return false;
    }

    public List<? extends EmailsBase> getEmails() {
        return null;
    }

    public boolean hasEtag() {
        return false;
    }

    public String getEtag() {
        return null;
    }

    public boolean hasEvents() {
        return false;
    }

    public List<? extends EventsBase> getEvents() {
        return null;
    }

    public boolean hasGenders() {
        return false;
    }

    public List<? extends GendersBase> getGenders() {
        return null;
    }

    public boolean hasId() {
        return false;
    }

    public String getId() {
        return null;
    }

    public boolean hasImages() {
        return false;
    }

    public List<? extends ImagesBase> getImages() {
        return null;
    }

    public boolean hasInstantMessaging() {
        return false;
    }

    public List<? extends InstantMessagingBase> getInstantMessaging() {
        return null;
    }

    public boolean hasLanguage() {
        return false;
    }

    public String getLanguage() {
        return null;
    }

    public boolean hasLegacyFields() {
        return false;
    }

    public LegacyFieldsBase getLegacyFields() {
        return null;
    }

    public boolean hasLinkedPeople() {
        return false;
    }

    public List<? extends PersonBase> getLinkedPeople() {
        return null;
    }

    public boolean hasMemberships() {
        return false;
    }

    public List<? extends MembershipsBase> getMemberships() {
        return null;
    }

    public boolean hasMetadata() {
        return false;
    }

    public PersonMetadataBase getMetadata() {
        return null;
    }

    public boolean hasNames() {
        return false;
    }

    public List<? extends NamesBase> getNames() {
        return null;
    }

    public boolean hasNicknames() {
        return false;
    }

    public List<? extends NicknamesBase> getNicknames() {
        return null;
    }

    public boolean hasOccupations() {
        return false;
    }

    public List<? extends OccupationsBase> getOccupations() {
        return null;
    }

    public boolean hasOrganizations() {
        return false;
    }

    public List<? extends OrganizationsBase> getOrganizations() {
        return null;
    }

    public boolean hasPhoneNumbers() {
        return false;
    }

    public List<? extends PhoneNumbersBase> getPhoneNumbers() {
        return null;
    }

    public boolean hasPlacesLived() {
        return false;
    }

    public List<? extends PlacesLivedBase> getPlacesLived() {
        return null;
    }

    public boolean hasProfileUrl() {
        return false;
    }

    public String getProfileUrl() {
        return null;
    }

    public boolean hasRelations() {
        return false;
    }

    public List<? extends RelationsBase> getRelations() {
        return null;
    }

    public boolean hasRelationshipInterests() {
        return false;
    }

    public List<? extends RelationshipInterestsBase> getRelationshipInterests() {
        return null;
    }

    public boolean hasRelationshipStatuses() {
        return false;
    }

    public List<? extends RelationshipStatusesBase> getRelationshipStatuses() {
        return null;
    }

    public boolean hasSkills() {
        return false;
    }

    public List<? extends SkillsBase> getSkills() {
        return null;
    }

    public boolean hasSortKeys() {
        return false;
    }

    public SortKeysBase getSortKeys() {
        return null;
    }

    public boolean hasTaglines() {
        return false;
    }

    public List<? extends TaglinesBase> getTaglines() {
        return null;
    }

    public boolean hasUrls() {
        return false;
    }

    public List<? extends UrlsBase> getUrls() {
        return null;
    }

    public boolean hasNotes() {
        return false;
    }

    public List<? extends NotesBase> getNotes() {
        return null;
    }
}
