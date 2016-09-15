package com.google.android.gms.people.identity.internal.models;

import com.google.android.gms.people.identity.internal.models.DefaultPersonImpl.Abouts;
import com.google.android.gms.people.identity.internal.models.DefaultPersonImpl.Addresses;
import com.google.android.gms.people.identity.internal.models.DefaultPersonImpl.Birthdays;
import com.google.android.gms.people.identity.internal.models.DefaultPersonImpl.BraggingRights;
import com.google.android.gms.people.identity.internal.models.DefaultPersonImpl.CoverPhotos;
import com.google.android.gms.people.identity.internal.models.DefaultPersonImpl.CustomFields;
import com.google.android.gms.people.identity.internal.models.DefaultPersonImpl.Emails;
import com.google.android.gms.people.identity.internal.models.DefaultPersonImpl.Events;
import com.google.android.gms.people.identity.internal.models.DefaultPersonImpl.Genders;
import com.google.android.gms.people.identity.internal.models.DefaultPersonImpl.Images;
import com.google.android.gms.people.identity.internal.models.DefaultPersonImpl.InstantMessaging;
import com.google.android.gms.people.identity.internal.models.DefaultPersonImpl.LegacyFields;
import com.google.android.gms.people.identity.internal.models.DefaultPersonImpl.Memberships;
import com.google.android.gms.people.identity.internal.models.DefaultPersonImpl.Metadata;
import com.google.android.gms.people.identity.internal.models.DefaultPersonImpl.Metadata.ProfileOwnerStats;
import com.google.android.gms.people.identity.internal.models.DefaultPersonImpl.Names;
import com.google.android.gms.people.identity.internal.models.DefaultPersonImpl.Nicknames;
import com.google.android.gms.people.identity.internal.models.DefaultPersonImpl.Occupations;
import com.google.android.gms.people.identity.internal.models.DefaultPersonImpl.Organizations;
import com.google.android.gms.people.identity.internal.models.DefaultPersonImpl.PhoneNumbers;
import com.google.android.gms.people.identity.internal.models.DefaultPersonImpl.PlacesLived;
import com.google.android.gms.people.identity.internal.models.DefaultPersonImpl.Relations;
import com.google.android.gms.people.identity.internal.models.DefaultPersonImpl.RelationshipInterests;
import com.google.android.gms.people.identity.internal.models.DefaultPersonImpl.RelationshipStatuses;
import com.google.android.gms.people.identity.internal.models.DefaultPersonImpl.Skills;
import com.google.android.gms.people.identity.internal.models.DefaultPersonImpl.SortKeys;
import com.google.android.gms.people.identity.internal.models.DefaultPersonImpl.Taglines;
import com.google.android.gms.people.identity.internal.models.DefaultPersonImpl.Urls;
import com.google.android.gms.people.identity.internal.models.PersonImpl.AboutsImpl;
import com.google.android.gms.people.identity.internal.models.PersonImpl.AddressesImpl;
import com.google.android.gms.people.identity.internal.models.PersonImpl.BirthdaysImpl;
import com.google.android.gms.people.identity.internal.models.PersonImpl.BraggingRightsImpl;
import com.google.android.gms.people.identity.internal.models.PersonImpl.CoverPhotosImpl;
import com.google.android.gms.people.identity.internal.models.PersonImpl.CustomFieldsImpl;
import com.google.android.gms.people.identity.internal.models.PersonImpl.EmailsImpl;
import com.google.android.gms.people.identity.internal.models.PersonImpl.EventsImpl;
import com.google.android.gms.people.identity.internal.models.PersonImpl.GendersImpl;
import com.google.android.gms.people.identity.internal.models.PersonImpl.ImagesImpl;
import com.google.android.gms.people.identity.internal.models.PersonImpl.InstantMessagingImpl;
import com.google.android.gms.people.identity.internal.models.PersonImpl.LegacyFieldsImpl;
import com.google.android.gms.people.identity.internal.models.PersonImpl.MembershipsImpl;
import com.google.android.gms.people.identity.internal.models.PersonImpl.MetadataImpl;
import com.google.android.gms.people.identity.internal.models.PersonImpl.NamesImpl;
import com.google.android.gms.people.identity.internal.models.PersonImpl.NicknamesImpl;
import com.google.android.gms.people.identity.internal.models.PersonImpl.OccupationsImpl;
import com.google.android.gms.people.identity.internal.models.PersonImpl.OrganizationsImpl;
import com.google.android.gms.people.identity.internal.models.PersonImpl.PersonMetadataImpl;
import com.google.android.gms.people.identity.internal.models.PersonImpl.PhoneNumbersImpl;
import com.google.android.gms.people.identity.internal.models.PersonImpl.PlacesLivedImpl;
import com.google.android.gms.people.identity.internal.models.PersonImpl.ProfileOwnerStatsImpl;
import com.google.android.gms.people.identity.internal.models.PersonImpl.RelationsImpl;
import com.google.android.gms.people.identity.internal.models.PersonImpl.RelationshipInterestsImpl;
import com.google.android.gms.people.identity.internal.models.PersonImpl.RelationshipStatusesImpl;
import com.google.android.gms.people.identity.internal.models.PersonImpl.SkillsImpl;
import com.google.android.gms.people.identity.internal.models.PersonImpl.SortKeysImpl;
import com.google.android.gms.people.identity.internal.models.PersonImpl.TaglinesImpl;
import com.google.android.gms.people.identity.internal.models.PersonImpl.UrlsImpl;

public class DefaultPersonConverter {
    public static PersonImpl merge(DefaultPersonImpl source, PersonImpl target) {
        if (source.hasAbouts()) {
            for (Abouts item : source.getAbouts()) {
                target.addAbouts(clone(item));
            }
        }
        if (source.hasAddresses()) {
            for (Addresses item2 : source.getAddresses()) {
                target.addAddresses(clone(item2));
            }
        }
        if (source.hasAgeRange()) {
            target.setAgeRange(source.getAgeRange());
        }
        if (source.hasBirthdays()) {
            for (Birthdays item3 : source.getBirthdays()) {
                target.addBirthdays(clone(item3));
            }
        }
        if (source.hasBraggingRights()) {
            for (BraggingRights item4 : source.getBraggingRights()) {
                target.addBraggingRights(clone(item4));
            }
        }
        if (source.hasCoverPhotos()) {
            for (CoverPhotos item5 : source.getCoverPhotos()) {
                target.addCoverPhotos(clone(item5));
            }
        }
        if (source.hasCustomFields()) {
            for (CustomFields item6 : source.getCustomFields()) {
                target.addCustomFields(clone(item6));
            }
        }
        if (source.hasEmails()) {
            for (Emails item7 : source.getEmails()) {
                target.addEmails(clone(item7));
            }
        }
        if (source.hasEtag()) {
            target.setEtag(source.getEtag());
        }
        if (source.hasEvents()) {
            for (Events item8 : source.getEvents()) {
                target.addEvents(clone(item8));
            }
        }
        if (source.hasGenders()) {
            for (Genders item9 : source.getGenders()) {
                target.addGenders(clone(item9));
            }
        }
        if (source.hasId()) {
            target.setId(source.getId());
        }
        if (source.hasImages()) {
            for (Images item10 : source.getImages()) {
                target.addImages(clone(item10));
            }
        }
        if (source.hasInstantMessaging()) {
            for (InstantMessaging item11 : source.getInstantMessaging()) {
                target.addInstantMessaging(clone(item11));
            }
        }
        if (source.hasLanguage()) {
            target.setLanguage(source.getLanguage());
        }
        if (source.hasLegacyFields()) {
            target.setLegacyFields(clone(source.getLegacyFields()));
        }
        if (source.hasLinkedPeople()) {
            for (DefaultPersonImpl item12 : source.getLinkedPeople()) {
                target.addLinkedPeople(merge(item12, new PersonImpl()));
            }
        }
        if (source.hasMemberships()) {
            for (Memberships item13 : source.getMemberships()) {
                target.addMemberships(clone(item13));
            }
        }
        if (source.hasMetadata()) {
            target.setMetadata(clone(source.getMetadata()));
        }
        if (source.hasNames()) {
            for (Names item14 : source.getNames()) {
                target.addNames(clone(item14));
            }
        }
        if (source.hasNicknames()) {
            for (Nicknames item15 : source.getNicknames()) {
                target.addNicknames(clone(item15));
            }
        }
        if (source.hasOccupations()) {
            for (Occupations item16 : source.getOccupations()) {
                target.addOccupations(clone(item16));
            }
        }
        if (source.hasOrganizations()) {
            for (Organizations item17 : source.getOrganizations()) {
                target.addOrganizations(clone(item17));
            }
        }
        if (source.hasPhoneNumbers()) {
            for (PhoneNumbers item18 : source.getPhoneNumbers()) {
                target.addPhoneNumbers(clone(item18));
            }
        }
        if (source.hasPlacesLived()) {
            for (PlacesLived item19 : source.getPlacesLived()) {
                target.addPlacesLived(clone(item19));
            }
        }
        if (source.hasProfileUrl()) {
            target.setProfileUrl(source.getProfileUrl());
        }
        if (source.hasRelations()) {
            for (Relations item20 : source.getRelations()) {
                target.addRelations(clone(item20));
            }
        }
        if (source.hasRelationshipInterests()) {
            for (RelationshipInterests item21 : source.getRelationshipInterests()) {
                target.addRelationshipInterests(clone(item21));
            }
        }
        if (source.hasRelationshipStatuses()) {
            for (RelationshipStatuses item22 : source.getRelationshipStatuses()) {
                target.addRelationshipStatuses(clone(item22));
            }
        }
        if (source.hasSkills()) {
            for (Skills item23 : source.getSkills()) {
                target.addSkills(clone(item23));
            }
        }
        if (source.hasSortKeys()) {
            target.setSortKeys(clone(source.getSortKeys()));
        }
        if (source.hasTaglines()) {
            for (Taglines item24 : source.getTaglines()) {
                target.addTaglines(clone(item24));
            }
        }
        if (source.hasUrls()) {
            for (Urls item25 : source.getUrls()) {
                target.addUrls(clone(item25));
            }
        }
        return target;
    }

    private static AboutsImpl clone(Abouts source) {
        AboutsImpl target = new AboutsImpl();
        if (source.hasMetadata()) {
            target.setMetadata(clone(source.getMetadata()));
        }
        if (source.hasType()) {
            target.setType(source.getType());
        }
        if (source.hasValue()) {
            target.setValue(source.getValue());
        }
        return target;
    }

    private static AddressesImpl clone(Addresses source) {
        AddressesImpl target = new AddressesImpl();
        if (source.hasMetadata()) {
            target.setMetadata(clone(source.getMetadata()));
        }
        if (source.hasCity()) {
            target.setCity(source.getCity());
        }
        if (source.hasCountry()) {
            target.setCountry(source.getCountry());
        }
        if (source.hasCountryCode()) {
            target.setCountryCode(source.getCountryCode());
        }
        if (source.hasFormattedType()) {
            target.setFormattedType(source.getFormattedType());
        }
        if (source.hasPoBox()) {
            target.setPoBox(source.getPoBox());
        }
        if (source.hasPostalCode()) {
            target.setPostalCode(source.getPostalCode());
        }
        if (source.hasRegion()) {
            target.setRegion(source.getRegion());
        }
        if (source.hasStreetAddress()) {
            target.setStreetAddress(source.getStreetAddress());
        }
        if (source.hasType()) {
            target.setType(source.getType());
        }
        if (source.hasValue()) {
            target.setValue(source.getValue());
        }
        return target;
    }

    private static BirthdaysImpl clone(Birthdays source) {
        BirthdaysImpl target = new BirthdaysImpl();
        if (source.hasMetadata()) {
            target.setMetadata(clone(source.getMetadata()));
        }
        if (source.hasDate()) {
            target.setDate(source.getDate());
        }
        return target;
    }

    private static BraggingRightsImpl clone(BraggingRights source) {
        BraggingRightsImpl target = new BraggingRightsImpl();
        if (source.hasMetadata()) {
            target.setMetadata(clone(source.getMetadata()));
        }
        if (source.hasValue()) {
            target.setValue(source.getValue());
        }
        return target;
    }

    private static CoverPhotosImpl clone(CoverPhotos source) {
        CoverPhotosImpl target = new CoverPhotosImpl();
        if (source.hasHeight()) {
            target.setHeight(source.getHeight());
        }
        if (source.hasId()) {
            target.setId(source.getId());
        }
        if (source.hasUrl()) {
            target.setImageReference(new ImageReferenceImpl().setLocation(source.getUrl()).setType(1));
        }
        if (source.hasWidth()) {
            target.setWidth(source.getWidth());
        }
        if (source.hasIsDefault()) {
            target.setDefault(source.isDefault());
        }
        return target;
    }

    private static CustomFieldsImpl clone(CustomFields source) {
        CustomFieldsImpl target = new CustomFieldsImpl();
        if (source.hasKey()) {
            target.setKey(source.getKey());
        }
        if (source.hasValue()) {
            target.setValue(source.getValue());
        }
        return target;
    }

    private static EmailsImpl clone(Emails source) {
        EmailsImpl target = new EmailsImpl();
        if (source.hasMetadata()) {
            target.setMetadata(clone(source.getMetadata()));
        }
        if (source.hasFormattedType()) {
            target.setFormattedType(source.getFormattedType());
        }
        if (source.hasType()) {
            target.setType(source.getType());
        }
        if (source.hasValue()) {
            target.setValue(source.getValue());
        }
        return target;
    }

    private static EventsImpl clone(Events source) {
        EventsImpl target = new EventsImpl();
        if (source.hasMetadata()) {
            target.setMetadata(clone(source.getMetadata()));
        }
        if (source.hasFormattedType()) {
            target.setFormattedType(source.getFormattedType());
        }
        if (source.hasType()) {
            target.setType(source.getType());
        }
        if (source.hasDate()) {
            target.setDate(source.getDate());
        }
        return target;
    }

    private static GendersImpl clone(Genders source) {
        GendersImpl target = new GendersImpl();
        if (source.hasMetadata()) {
            target.setMetadata(clone(source.getMetadata()));
        }
        if (source.hasFormattedValue()) {
            target.setFormattedValue(source.getFormattedValue());
        }
        if (source.hasValue()) {
            target.setValue(source.getValue());
        }
        return target;
    }

    private static ImagesImpl clone(Images source) {
        ImagesImpl target = new ImagesImpl();
        if (source.hasMetadata()) {
            target.setMetadata(clone(source.getMetadata()));
        }
        if (source.hasUrl()) {
            target.setImageReference(new ImageReferenceImpl().setLocation(source.getUrl()).setType(1));
        }
        if (source.hasIsDefault()) {
            target.setDefault(source.isDefault());
        }
        return target;
    }

    private static InstantMessagingImpl clone(InstantMessaging source) {
        InstantMessagingImpl target = new InstantMessagingImpl();
        if (source.hasMetadata()) {
            target.setMetadata(clone(source.getMetadata()));
        }
        if (source.hasFormattedProtocol()) {
            target.setFormattedProtocol(source.getFormattedProtocol());
        }
        if (source.hasFormattedType()) {
            target.setFormattedType(source.getFormattedType());
        }
        if (source.hasProtocol()) {
            target.setProtocol(source.getProtocol());
        }
        if (source.hasType()) {
            target.setType(source.getType());
        }
        if (source.hasValue()) {
            target.setValue(source.getValue());
        }
        return target;
    }

    private static LegacyFieldsImpl clone(LegacyFields source) {
        LegacyFieldsImpl target = new LegacyFieldsImpl();
        if (source.hasMobileOwnerId()) {
            target.setMobileOwnerId(source.getMobileOwnerId());
        }
        return target;
    }

    private static MembershipsImpl clone(Memberships source) {
        MembershipsImpl target = new MembershipsImpl();
        if (source.hasMetadata()) {
            target.setMetadata(clone(source.getMetadata()));
        }
        if (source.hasCircle()) {
            target.setCircle(source.getCircle());
        }
        if (source.hasContactGroup()) {
            target.setContactGroup(source.getContactGroup());
        }
        if (source.hasSystemContactGroup()) {
            target.setSystemContactGroup(source.getSystemContactGroup());
        }
        return target;
    }

    private static MetadataImpl clone(DefaultMetadataImpl source) {
        MetadataImpl target = new MetadataImpl();
        if (source.hasContainer()) {
            target.setContainer(source.getContainer());
        }
        if (source.hasContainerContactId()) {
            target.setContainerContactId(source.getContainerContactId());
        }
        if (source.hasContainerId()) {
            target.setContainerId(source.getContainerId());
        }
        if (source.hasVisibility()) {
            target.setVisibility(source.getVisibility());
        }
        if (source.hasEdgeKey()) {
            target.setEdgeKey(source.isEdgeKey());
        }
        if (source.hasPrimary()) {
            target.setPrimary(source.isPrimary());
        }
        if (source.hasVerified()) {
            target.setVerified(source.isVerified());
        }
        if (source.hasWriteable()) {
            target.setWriteable(source.isWriteable());
        }
        return target;
    }

    private static PersonMetadataImpl clone(Metadata source) {
        PersonMetadataImpl target = new PersonMetadataImpl();
        if (source.hasAttributions()) {
            target.addAllAttributions(source.getAttributions());
        }
        if (source.hasBlockTypes()) {
            target.addAllBlockTypes(source.getBlockTypes());
        }
        if (source.hasCircles()) {
            target.addAllCircles(source.getCircles());
        }
        if (source.hasContacts()) {
            target.addAllContacts(source.getContacts());
        }
        if (source.hasGroups()) {
            target.addAllGroups(source.getGroups());
        }
        if (source.hasIncomingBlockTypes()) {
            target.addAllIncomingBlockTypes(source.getIncomingBlockTypes());
        }
        if (source.hasObjectType()) {
            target.setObjectType(source.getObjectType());
        }
        if (source.hasOwnerId()) {
            target.setOwnerId(source.getOwnerId());
        }
        if (source.hasOwnerUserTypes()) {
            target.addAllOwnerUserTypes(source.getOwnerUserTypes());
        }
        if (source.hasPlusPageType()) {
            target.setPlusPageType(source.getPlusPageType());
        }
        if (source.hasProfileOwnerStats()) {
            target.setProfileOwnerStats(clone(source.getProfileOwnerStats()));
        }
        if (source.hasBlocked()) {
            target.setBlocked(source.isBlocked());
        }
        if (source.hasDeleted()) {
            target.setDeleted(source.isDeleted());
        }
        if (source.hasInViewerDomain()) {
            target.setInViewerDomain(source.isInViewerDomain());
        }
        return target;
    }

    private static NamesImpl clone(Names source) {
        NamesImpl target = new NamesImpl();
        if (source.hasMetadata()) {
            target.setMetadata(clone(source.getMetadata()));
        }
        if (source.hasDisplayName()) {
            target.setDisplayName(source.getDisplayName());
        }
        if (source.hasFamilyName()) {
            target.setFamilyName(source.getFamilyName());
        }
        if (source.hasFormatted()) {
            target.setFormatted(source.getFormatted());
        }
        if (source.hasGivenName()) {
            target.setGivenName(source.getGivenName());
        }
        if (source.hasHonorificPrefix()) {
            target.setHonorificPrefix(source.getHonorificPrefix());
        }
        if (source.hasHonorificSuffix()) {
            target.setHonorificSuffix(source.getHonorificSuffix());
        }
        if (source.hasMiddleName()) {
            target.setMiddleName(source.getMiddleName());
        }
        if (source.hasPhoneticFamilyName()) {
            target.setPhoneticFamilyName(source.getPhoneticFamilyName());
        }
        if (source.hasPhoneticGivenName()) {
            target.setPhoneticGivenName(source.getPhoneticGivenName());
        }
        if (source.hasPhoneticHonorificPrefix()) {
            target.setPhoneticHonorificPrefix(source.getPhoneticHonorificPrefix());
        }
        if (source.hasPhoneticHonorificSuffix()) {
            target.setPhoneticHonorificSuffix(source.getPhoneticHonorificSuffix());
        }
        return target;
    }

    private static NicknamesImpl clone(Nicknames source) {
        NicknamesImpl target = new NicknamesImpl();
        if (source.hasMetadata()) {
            target.setMetadata(clone(source.getMetadata()));
        }
        if (source.hasType()) {
            target.setType(source.getType());
        }
        if (source.hasValue()) {
            target.setValue(source.getValue());
        }
        return target;
    }

    private static OccupationsImpl clone(Occupations source) {
        OccupationsImpl target = new OccupationsImpl();
        if (source.hasMetadata()) {
            target.setMetadata(clone(source.getMetadata()));
        }
        if (source.hasValue()) {
            target.setValue(source.getValue());
        }
        return target;
    }

    private static OrganizationsImpl clone(Organizations source) {
        OrganizationsImpl target = new OrganizationsImpl();
        if (source.hasMetadata()) {
            target.setMetadata(clone(source.getMetadata()));
        }
        if (source.hasCurrent()) {
            target.setCurrent(source.isCurrent());
        }
        if (source.hasDepartment()) {
            target.setDepartment(source.getDepartment());
        }
        if (source.hasDescription()) {
            target.setDescription(source.getDescription());
        }
        if (source.hasDomain()) {
            target.setDomain(source.getDomain());
        }
        if (source.hasEndDate()) {
            target.setEndDate(source.getEndDate());
        }
        if (source.hasLocation()) {
            target.setLocation(source.getLocation());
        }
        if (source.hasName()) {
            target.setName(source.getName());
        }
        if (source.hasPhoneticName()) {
            target.setPhoneticName(source.getPhoneticName());
        }
        if (source.hasStartDate()) {
            target.setStartDate(source.getStartDate());
        }
        if (source.hasSymbol()) {
            target.setSymbol(source.getSymbol());
        }
        if (source.hasTitle()) {
            target.setTitle(source.getTitle());
        }
        if (source.hasType()) {
            target.setType(source.getType());
        }
        return target;
    }

    private static PhoneNumbersImpl clone(PhoneNumbers source) {
        PhoneNumbersImpl target = new PhoneNumbersImpl();
        if (source.hasMetadata()) {
            target.setMetadata(clone(source.getMetadata()));
        }
        if (source.hasCanonicalizedForm()) {
            target.setCanonicalizedForm(source.getCanonicalizedForm());
        }
        if (source.hasFormattedType()) {
            target.setFormattedType(source.getFormattedType());
        }
        if (source.hasType()) {
            target.setType(source.getType());
        }
        if (source.hasValue()) {
            target.setValue(source.getValue());
        }
        return target;
    }

    private static PlacesLivedImpl clone(PlacesLived source) {
        PlacesLivedImpl target = new PlacesLivedImpl();
        if (source.hasMetadata()) {
            target.setMetadata(clone(source.getMetadata()));
        }
        if (source.hasCurrent()) {
            target.setCurrent(source.isCurrent());
        }
        if (source.hasValue()) {
            target.setValue(source.getValue());
        }
        return target;
    }

    private static ProfileOwnerStatsImpl clone(ProfileOwnerStats source) {
        ProfileOwnerStatsImpl target = new ProfileOwnerStatsImpl();
        if (source.hasIncomingAnyCircleCount()) {
            target.setIncomingAnyCircleCount(source.getIncomingAnyCircleCount());
        }
        if (source.hasViewCount()) {
            target.setViewCount(source.getViewCount());
        }
        return target;
    }

    private static RelationsImpl clone(Relations source) {
        RelationsImpl target = new RelationsImpl();
        if (source.hasMetadata()) {
            target.setMetadata(clone(source.getMetadata()));
        }
        if (source.hasFormattedType()) {
            target.setFormattedType(source.getFormattedType());
        }
        if (source.hasType()) {
            target.setType(source.getType());
        }
        if (source.hasValue()) {
            target.setValue(source.getValue());
        }
        return target;
    }

    private static RelationshipInterestsImpl clone(RelationshipInterests source) {
        RelationshipInterestsImpl target = new RelationshipInterestsImpl();
        if (source.hasMetadata()) {
            target.setMetadata(clone(source.getMetadata()));
        }
        if (source.hasValue()) {
            target.setValue(source.getValue());
        }
        return target;
    }

    private static RelationshipStatusesImpl clone(RelationshipStatuses source) {
        RelationshipStatusesImpl target = new RelationshipStatusesImpl();
        if (source.hasMetadata()) {
            target.setMetadata(clone(source.getMetadata()));
        }
        if (source.hasFormattedValue()) {
            target.setFormattedValue(source.getFormattedValue());
        }
        if (source.hasValue()) {
            target.setValue(source.getValue());
        }
        return target;
    }

    private static SkillsImpl clone(Skills source) {
        SkillsImpl target = new SkillsImpl();
        if (source.hasMetadata()) {
            target.setMetadata(clone(source.getMetadata()));
        }
        if (source.hasValue()) {
            target.setValue(source.getValue());
        }
        return target;
    }

    private static SortKeysImpl clone(SortKeys source) {
        SortKeysImpl target = new SortKeysImpl();
        if (source.hasInteractionRank()) {
            target.setInteractionRank(source.getInteractionRank());
        }
        if (source.hasName()) {
            target.setName(source.getName());
        }
        return target;
    }

    private static TaglinesImpl clone(Taglines source) {
        TaglinesImpl target = new TaglinesImpl();
        if (source.hasMetadata()) {
            target.setMetadata(clone(source.getMetadata()));
        }
        if (source.hasValue()) {
            target.setValue(source.getValue());
        }
        return target;
    }

    private static UrlsImpl clone(Urls source) {
        UrlsImpl target = new UrlsImpl();
        if (source.hasMetadata()) {
            target.setMetadata(clone(source.getMetadata()));
        }
        if (source.hasFormattedType()) {
            target.setFormattedType(source.getFormattedType());
        }
        if (source.hasType()) {
            target.setType(source.getType());
        }
        if (source.hasValue()) {
            target.setValue(source.getValue());
        }
        return target;
    }
}
