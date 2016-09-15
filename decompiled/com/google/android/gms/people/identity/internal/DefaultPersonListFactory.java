package com.google.android.gms.people.identity.internal;

import com.google.android.gms.common.server.response.FastParser.ParseException;
import com.google.android.gms.people.identity.PersonFactory.ContactData;
import com.google.android.gms.people.identity.PersonFactory.OfflineDatabaseData;
import com.google.android.gms.people.identity.PersonFactory.RawContactData;
import com.google.android.gms.people.identity.PersonFactory.ServiceData;
import com.google.android.gms.people.identity.internal.ContactDataUtil.ContactListItemUtil;
import com.google.android.gms.people.identity.internal.models.DefaultPersonImpl;
import com.google.android.gms.people.identity.internal.models.DefaultPersonImpl.Images;
import com.google.android.gms.people.identity.internal.models.DefaultPersonImpl.Names;
import com.google.android.gms.people.identity.internal.models.DefaultPersonListImpl;
import com.google.android.gms.people.identity.internal.models.ImageReferenceImpl;
import com.google.android.gms.people.identity.internal.models.PersonReferenceImpl;
import com.google.android.gms.people.identity.models.PersonReference;
import com.google.android.gms.people.internal.PeopleServiceLog;
import com.google.android.gms.people.internal.PeopleUtils;
import java.util.ArrayList;
import java.util.List;

public class DefaultPersonListFactory extends DefaultPersonListFactoryBase<PersonReference> {
    private static final String TAG = "DefaultPersonFactory";

    protected List<PersonReference> interpret(ServiceData serviceData) {
        ArrayList<PersonReference> result = new ArrayList();
        if (!(serviceData == null || serviceData.blob == null)) {
            try {
                DefaultPersonListImpl serverList = new DefaultPersonListImpl();
                serverList.parseNetworkResponse(serviceData.responseCode, serviceData.blob);
                for (DefaultPersonImpl person : serverList.getItems()) {
                    Names name = primaryName(person.getNames());
                    Images image = primaryImage(person.getImages());
                    PersonReferenceImpl item = new PersonReferenceImpl().setQualifiedId(PeopleUtils.GAIA_ID_QUALIFIER + person.getId());
                    if (name != null) {
                        item.setName(name.getDisplayName());
                    }
                    if (image != null) {
                        item.setAvatarReference(new ImageReferenceImpl().setLocation(image.getUrl()));
                    }
                    result.add(item);
                }
            } catch (ParseException e) {
                PeopleServiceLog.w(TAG, "ParseException", e);
            }
        }
        return result;
    }

    protected List<PersonReference> interpret(ContactData[] contactData) {
        List<PersonReference> result = new ArrayList(contactData.length);
        for (ContactData rawData : contactData) {
            RawContactData rawData2 = (RawContactData) rawData.getRawData().get(0);
            String imageUrl = ContactListItemUtil.getImageUrl(rawData2);
            ImageReferenceImpl image = null;
            if (imageUrl != null) {
                image = new ImageReferenceImpl().setLocation(imageUrl).setType(2);
            }
            result.add(new PersonReferenceImpl().setQualifiedId(ContactDataUtil.cp2ContactIdToPeopleQualifiedId(ContactListItemUtil.getId(rawData2))).setName(ContactListItemUtil.getName(rawData2)).setAvatarReference(image));
        }
        return result;
    }

    protected List<PersonReference> interpret(OfflineDatabaseData offlineDatabaseData) {
        throw new IllegalStateException("Not Implemented");
    }

    protected String getQualifiedId(PersonReference person) {
        return person.getQualifiedId();
    }

    protected static Names primaryName(List<Names> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        for (Names item : list) {
            if (item.getMetadata() != null && item.getMetadata().isPrimary()) {
                return item;
            }
        }
        return (Names) list.get(0);
    }

    protected static Images primaryImage(List<Images> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        for (Images item : list) {
            if (item.getMetadata() != null && item.getMetadata().isPrimary()) {
                return item;
            }
        }
        return (Images) list.get(0);
    }
}
