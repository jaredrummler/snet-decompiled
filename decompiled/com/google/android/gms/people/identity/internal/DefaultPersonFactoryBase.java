package com.google.android.gms.people.identity.internal;

import android.content.Context;
import android.text.TextUtils;
import com.google.android.gms.common.server.response.FastParser.ParseException;
import com.google.android.gms.people.identity.PersonFactory.ContactData;
import com.google.android.gms.people.identity.PersonFactory.OfflineDatabaseData;
import com.google.android.gms.people.identity.PersonFactory.OfflineDatabaseData.AddressData;
import com.google.android.gms.people.identity.PersonFactory.OfflineDatabaseData.Circle;
import com.google.android.gms.people.identity.PersonFactory.OfflineDatabaseData.EmailData;
import com.google.android.gms.people.identity.PersonFactory.OfflineDatabaseData.PhoneData;
import com.google.android.gms.people.identity.PersonFactory.RawContactData;
import com.google.android.gms.people.identity.PersonFactory.ServiceData;
import com.google.android.gms.people.identity.internal.ContactDataUtil.ContactAddressUtil;
import com.google.android.gms.people.identity.internal.ContactDataUtil.ContactEmailUtil;
import com.google.android.gms.people.identity.internal.ContactDataUtil.ContactEventUtil;
import com.google.android.gms.people.identity.internal.ContactDataUtil.ContactImUtil;
import com.google.android.gms.people.identity.internal.ContactDataUtil.ContactImageUtil;
import com.google.android.gms.people.identity.internal.ContactDataUtil.ContactNameUtil;
import com.google.android.gms.people.identity.internal.ContactDataUtil.ContactNicknameUtil;
import com.google.android.gms.people.identity.internal.ContactDataUtil.ContactNoteUtil;
import com.google.android.gms.people.identity.internal.ContactDataUtil.ContactOrganizationUtil;
import com.google.android.gms.people.identity.internal.ContactDataUtil.ContactPhoneUtil;
import com.google.android.gms.people.identity.internal.ContactDataUtil.ContactRelationUtil;
import com.google.android.gms.people.identity.internal.ContactDataUtil.ContactWebsiteUtil;
import com.google.android.gms.people.identity.internal.models.DefaultPersonConverter;
import com.google.android.gms.people.identity.internal.models.DefaultPersonImpl;
import com.google.android.gms.people.identity.internal.models.DefaultPersonListImpl;
import com.google.android.gms.people.identity.internal.models.ImageReferenceImpl;
import com.google.android.gms.people.identity.internal.models.PersonImpl;
import com.google.android.gms.people.identity.internal.models.PersonImpl.AddressesImpl;
import com.google.android.gms.people.identity.internal.models.PersonImpl.EmailsImpl;
import com.google.android.gms.people.identity.internal.models.PersonImpl.EventsImpl;
import com.google.android.gms.people.identity.internal.models.PersonImpl.ImagesImpl;
import com.google.android.gms.people.identity.internal.models.PersonImpl.InstantMessagingImpl;
import com.google.android.gms.people.identity.internal.models.PersonImpl.MembershipsImpl;
import com.google.android.gms.people.identity.internal.models.PersonImpl.MetadataImpl;
import com.google.android.gms.people.identity.internal.models.PersonImpl.NamesImpl;
import com.google.android.gms.people.identity.internal.models.PersonImpl.NicknamesImpl;
import com.google.android.gms.people.identity.internal.models.PersonImpl.NotesImpl;
import com.google.android.gms.people.identity.internal.models.PersonImpl.OrganizationsImpl;
import com.google.android.gms.people.identity.internal.models.PersonImpl.PersonMetadataImpl;
import com.google.android.gms.people.identity.internal.models.PersonImpl.PhoneNumbersImpl;
import com.google.android.gms.people.identity.internal.models.PersonImpl.RelationsImpl;
import com.google.android.gms.people.identity.internal.models.PersonImpl.TaglinesImpl;
import com.google.android.gms.people.identity.internal.models.PersonImpl.UrlsImpl;
import com.google.android.gms.people.internal.PeopleServiceLog;
import com.google.wireless.android.play.playlog.proto.UserLocation.LaunchSettingsEvent.Action;
import com.google.wireless.android.play.playlog.proto.UserLocation.LocationSharingEvent;
import com.google.wireless.android.play.playlog.proto.UserLocation.OvenFreshEvent.OvenFreshResult;
import com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection.Type;
import java.util.HashSet;

public abstract class DefaultPersonFactoryBase<PersonType extends PersonImpl> {
    private static final String CONTAINER_TYPE_PROFILE = "profile";
    private static final String CP2_CONTAINER = "cp2";
    private static final String OBJECT_TYPE_PAGE = "page";
    private static final String OBJECT_TYPE_PERSON = "person";
    private static final String TAG = "DefaultPersonFactory";

    protected abstract PersonType buildNewPerson();

    public PersonType build(Context context, Object equalityKey, ServiceData serviceData, ContactData contactData, OfflineDatabaseData offlineDatabaseData) {
        PersonType result = buildNewPerson();
        boolean foundServerData = false;
        if (!(serviceData == null || serviceData.blob == null)) {
            try {
                switch (serviceData.format) {
                    case Type.INDEFINITELY /*2*/:
                        DefaultPersonImpl serviceObj = new DefaultPersonImpl();
                        serviceObj.parseNetworkResponse(serviceData.responseCode, serviceData.blob);
                        DefaultPersonConverter.merge(serviceObj, result);
                        foundServerData = true;
                        break;
                    case OvenFreshResult.NO_ACCOUNTS /*4*/:
                        DefaultPersonListImpl serviceListObj = new DefaultPersonListImpl();
                        serviceListObj.parseNetworkResponse(serviceData.responseCode, serviceData.blob);
                        if (serviceListObj.hasItems() && serviceListObj.getItems().size() > 0) {
                            DefaultPersonConverter.merge((DefaultPersonImpl) serviceListObj.getItems().get(0), result);
                            foundServerData = true;
                            break;
                        }
                    default:
                        PeopleServiceLog.w(TAG, "Unrecognized data format");
                        return null;
                }
            } catch (ParseException e) {
                PeopleServiceLog.w(TAG, "ParseException", e);
                return null;
            }
        }
        if (!(foundServerData || offlineDatabaseData == null)) {
            parseDatabaseData(result, offlineDatabaseData);
        }
        if (contactData == null) {
            return result;
        }
        parseContactData(context, result, contactData);
        return result;
    }

    private void parseDatabaseData(PersonImpl person, OfflineDatabaseData data) {
        if (data != null) {
            PersonMetadataImpl personMetadata;
            if (person.hasMetadata()) {
                personMetadata = person.getMetadata();
            } else {
                personMetadata = new PersonMetadataImpl();
                person.setMetadata(personMetadata);
            }
            switch (data.getProfileType()) {
                case Type.TEMPORARY /*1*/:
                    personMetadata.setObjectType(OBJECT_TYPE_PERSON);
                    break;
                case Type.INDEFINITELY /*2*/:
                    personMetadata.setObjectType(OBJECT_TYPE_PAGE);
                    break;
            }
            if (data.getCircles() != null) {
                for (Circle circle : data.getCircles()) {
                    personMetadata.addCircles(circle.getId());
                    person.addMemberships(new MembershipsImpl().setCircle(circle.getId()).setMetadata(new MetadataImpl().setContainer(CONTAINER_TYPE_PROFILE).setContainerId(data.getGaiaId())));
                }
            }
            person.addNames(new NamesImpl().setDisplayName(data.getDisplayName()).setMetadata(new MetadataImpl().setContainer(CONTAINER_TYPE_PROFILE).setPrimary(true).setWriteable(false).setVerified(data.getNameVerified()))).setId(data.getGaiaId()).addTaglines(new TaglinesImpl().setValue(data.getTagline()).setMetadata(new MetadataImpl().setContainer(CONTAINER_TYPE_PROFILE).setPrimary(true).setWriteable(false))).addImages(new ImagesImpl().setImageReference(new ImageReferenceImpl().setLocation(data.getCompressedAvatarUrl()).setType(1)).setDefault(true).setMetadata(new MetadataImpl().setContainer(CONTAINER_TYPE_PROFILE).setPrimary(true).setWriteable(false)));
            if (data.getAddresses() != null) {
                for (AddressData address : data.getAddresses()) {
                    person.addAddresses(new AddressesImpl().setStreetAddress(address.getAddress()).setFormattedType(address.getType()).setMetadata(new MetadataImpl().setContainer(CONTAINER_TYPE_PROFILE).setPrimary(true).setWriteable(false)));
                }
            }
            if (data.getPhones() != null) {
                for (PhoneData phone : data.getPhones()) {
                    person.addPhoneNumbers(new PhoneNumbersImpl().setValue(phone.getPhone()).setFormattedType(phone.getType()).setMetadata(new MetadataImpl().setContainer(CONTAINER_TYPE_PROFILE).setPrimary(true).setWriteable(false)));
                }
            }
            if (data.getEmails() != null) {
                for (EmailData email : data.getEmails()) {
                    person.addEmails(new EmailsImpl().setValue(email.getEmailAddress()).setFormattedType(email.getType()).setMetadata(new MetadataImpl().setContainer(CONTAINER_TYPE_PROFILE).setPrimary(true).setWriteable(false)));
                }
            }
        }
    }

    private void parseContactData(Context context, PersonImpl person, ContactData contactData) {
        HashSet<String> knownContactIds = new HashSet();
        for (RawContactData rawData : contactData.getRawData()) {
            if (!TextUtils.isEmpty(rawData.getData(0)) || !TextUtils.isEmpty(rawData.getData(13))) {
                if (!knownContactIds.contains(rawData.getContactId())) {
                    knownContactIds.add(rawData.getContactId());
                    person.addMemberships(new MembershipsImpl().setContactGroup(rawData.getContactId()).setMetadata(createDefaultCp2MetadataImpl(rawData)));
                }
                String mimeType = rawData.getMimeType();
                int i = -1;
                switch (mimeType.hashCode()) {
                    case -1569536764:
                        if (mimeType.equals(ContactDataUtil.MIMETYPE_EMAIL)) {
                            i = 0;
                            break;
                        }
                        break;
                    case -1328682538:
                        if (mimeType.equals(ContactDataUtil.MIMETYPE_EVENT)) {
                            i = 1;
                            break;
                        }
                        break;
                    case -1079224304:
                        if (mimeType.equals(ContactDataUtil.MIMETYPE_STRUCTURED_NAME)) {
                            i = 7;
                            break;
                        }
                        break;
                    case -1079210633:
                        if (mimeType.equals(ContactDataUtil.MIMETYPE_NOTE)) {
                            i = 11;
                            break;
                        }
                        break;
                    case -601229436:
                        if (mimeType.equals(ContactDataUtil.MIMETYPE_STRUCTURED_POSTAL)) {
                            i = 8;
                            break;
                        }
                        break;
                    case 456415478:
                        if (mimeType.equals(ContactDataUtil.MIMETYPE_WEBSITE)) {
                            i = 9;
                            break;
                        }
                        break;
                    case 684173810:
                        if (mimeType.equals(ContactDataUtil.MIMETYPE_PHONE)) {
                            i = 5;
                            break;
                        }
                        break;
                    case 689862072:
                        if (mimeType.equals(ContactDataUtil.MIMETYPE_ORGANIZATION)) {
                            i = 4;
                            break;
                        }
                        break;
                    case 905843021:
                        if (mimeType.equals(ContactDataUtil.MIMETYPE_PHOTO)) {
                            i = 10;
                            break;
                        }
                        break;
                    case 950831081:
                        if (mimeType.equals(ContactDataUtil.MIMETYPE_IM)) {
                            i = 2;
                            break;
                        }
                        break;
                    case 1409846529:
                        if (mimeType.equals(ContactDataUtil.MIMETYPE_RELATION)) {
                            i = 6;
                            break;
                        }
                        break;
                    case 2034973555:
                        if (mimeType.equals(ContactDataUtil.MIMETYPE_NICKNAME)) {
                            i = 3;
                            break;
                        }
                        break;
                }
                switch (i) {
                    case Action.UNKNOWN /*0*/:
                        person.addEmails(createEmail(context, rawData));
                        break;
                    case Type.TEMPORARY /*1*/:
                        person.addEvents(createEvent(context, rawData));
                        break;
                    case Type.INDEFINITELY /*2*/:
                        person.addInstantMessaging(createInstantMessaging(context, rawData));
                        break;
                    case Type.CUSTOM /*3*/:
                        person.addNicknames(createNickname(rawData));
                        break;
                    case OvenFreshResult.NO_ACCOUNTS /*4*/:
                        person.addOrganizations(createOrganization(rawData));
                        break;
                    case LocationSharingEvent.Type.ADD_NEW_SHARES /*5*/:
                        person.addPhoneNumbers(createPhoneNumber(context, rawData));
                        break;
                    case LocationSharingEvent.Type.REMOVE_SHARE /*6*/:
                        person.addRelations(createRelation(context, rawData));
                        break;
                    case LocationSharingEvent.Type.RESET_TIME /*7*/:
                        person.addNames(createName(rawData));
                        break;
                    case LocationSharingEvent.Type.TAP_GET_LINK /*8*/:
                        person.addAddresses(createAddress(context, rawData));
                        break;
                    case LocationSharingEvent.Type.CREATE_LINK /*9*/:
                        person.addUrls(createUrl(rawData));
                        break;
                    case LocationSharingEvent.Type.TAP_ABOUT /*10*/:
                        if (!TextUtils.isEmpty(rawData.getData(13))) {
                            person.addImages(createImage(rawData));
                            break;
                        }
                        break;
                    case LocationSharingEvent.Type.TAP_LEARN_MORE /*11*/:
                        person.addNotes(createNote(rawData));
                        break;
                    default:
                        break;
                }
            }
        }
    }

    private static MetadataImpl createDefaultCp2MetadataImpl(RawContactData rawData) {
        return new MetadataImpl().setPrimary(rawData.isPrimary()).setWriteable(!rawData.isReadOnly()).setContainer(CP2_CONTAINER).setContainerId(rawData.getContactId()).setRawContactId(Integer.parseInt(rawData.getRawContactId()));
    }

    private EmailsImpl createEmail(Context context, RawContactData rawData) {
        return new EmailsImpl().setMetadata(createDefaultCp2MetadataImpl(rawData)).setValue(ContactEmailUtil.getAddress(rawData)).setType(ContactEmailUtil.getTypeString(rawData)).setFormattedType(ContactEmailUtil.getFormattedTypeString(context, rawData)).setTimesUsed(rawData.getTimesUsed());
    }

    private EventsImpl createEvent(Context context, RawContactData rawData) {
        return new EventsImpl().setMetadata(createDefaultCp2MetadataImpl(rawData)).setDate(ContactEventUtil.getDateString(rawData)).setType(ContactEventUtil.getTypeString(context, rawData)).setFormattedType(ContactEventUtil.getFormattedTypeString(rawData));
    }

    private InstantMessagingImpl createInstantMessaging(Context context, RawContactData rawData) {
        return new InstantMessagingImpl().setMetadata(createDefaultCp2MetadataImpl(rawData)).setValue(ContactImUtil.getAddress(rawData)).setType(ContactImUtil.getTypeString(rawData)).setFormattedType(ContactImUtil.getFormattedTypeString(context, rawData)).setProtocol(ContactImUtil.getProtocolString(rawData)).setFormattedProtocol(ContactImUtil.getFormattedProtocolString(context, rawData));
    }

    private NicknamesImpl createNickname(RawContactData rawData) {
        return new NicknamesImpl().setMetadata(createDefaultCp2MetadataImpl(rawData)).setValue(ContactNicknameUtil.getName(rawData)).setType(ContactNicknameUtil.getTypeString(rawData));
    }

    private OrganizationsImpl createOrganization(RawContactData rawData) {
        return new OrganizationsImpl().setMetadata(createDefaultCp2MetadataImpl(rawData)).setName(ContactOrganizationUtil.getName(rawData)).setType(ContactOrganizationUtil.getTypeString(rawData)).setTitle(ContactOrganizationUtil.getTitle(rawData)).setDepartment(ContactOrganizationUtil.getDepartment(rawData)).setDescription(ContactOrganizationUtil.getDescription(rawData)).setSymbol(ContactOrganizationUtil.getSymbol(rawData)).setPhoneticName(ContactOrganizationUtil.getPhoneticName(rawData)).setLocation(ContactOrganizationUtil.getLocation(rawData));
    }

    private PhoneNumbersImpl createPhoneNumber(Context context, RawContactData rawData) {
        return new PhoneNumbersImpl().setMetadata(createDefaultCp2MetadataImpl(rawData)).setValue(ContactPhoneUtil.getPhoneNumber(rawData)).setType(ContactPhoneUtil.getTypeString(rawData)).setFormattedType(ContactPhoneUtil.getFormattedTypeString(context, rawData)).setTimesUsed(rawData.getTimesUsed());
    }

    private RelationsImpl createRelation(Context context, RawContactData rawData) {
        return new RelationsImpl().setMetadata(createDefaultCp2MetadataImpl(rawData)).setValue(ContactRelationUtil.getRelationshipName(rawData)).setType(ContactRelationUtil.getTypeString(rawData)).setFormattedType(ContactRelationUtil.getFormattedTypeString(context, rawData));
    }

    private NamesImpl createName(RawContactData rawData) {
        return new NamesImpl().setMetadata(createDefaultCp2MetadataImpl(rawData)).setDisplayName(ContactNameUtil.getDisplayName(rawData)).setGivenName(ContactNameUtil.getGivenName(rawData)).setFamilyName(ContactNameUtil.getFamilyName(rawData)).setHonorificPrefix(ContactNameUtil.getHonorificPrefix(rawData)).setMiddleName(ContactNameUtil.getMiddleName(rawData)).setHonorificSuffix(ContactNameUtil.getHonorificSuffix(rawData)).setPhoneticGivenName(ContactNameUtil.getPhoneticGivenName(rawData)).setPhoneticFamilyName(ContactNameUtil.getPhoneticFamilyName(rawData));
    }

    private AddressesImpl createAddress(Context context, RawContactData rawData) {
        return new AddressesImpl().setMetadata(createDefaultCp2MetadataImpl(rawData)).setType(ContactAddressUtil.getTypeString(rawData)).setFormattedType(ContactAddressUtil.getFormattedTypeString(context, rawData)).setStreetAddress(ContactAddressUtil.getStreetAddress(rawData)).setPoBox(ContactAddressUtil.getPoBox(rawData)).setCity(ContactAddressUtil.getCity(rawData)).setRegion(ContactAddressUtil.getRegion(rawData)).setPostalCode(ContactAddressUtil.getPostalCode(rawData)).setCountry(ContactAddressUtil.getCountry(rawData)).setCountryCode(ContactAddressUtil.getCountryCode(rawData));
    }

    private UrlsImpl createUrl(RawContactData rawData) {
        return new UrlsImpl().setMetadata(createDefaultCp2MetadataImpl(rawData)).setValue(ContactWebsiteUtil.getUrl(rawData)).setType(ContactWebsiteUtil.getTypeString(rawData));
    }

    private NotesImpl createNote(RawContactData rawData) {
        return new NotesImpl().setMetadata(createDefaultCp2MetadataImpl(rawData)).setValue(ContactNoteUtil.getValue(rawData));
    }

    private ImagesImpl createImage(RawContactData rawData) {
        return new ImagesImpl().setMetadata(createDefaultCp2MetadataImpl(rawData)).setImageReference(new ImageReferenceImpl().setLocation(ContactImageUtil.getUrl(rawData)).setType(2));
    }
}
