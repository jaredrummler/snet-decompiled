package com.google.android.gms.people.identity.internal;

import com.google.android.gms.people.identity.PersonFactory.ContactData;
import com.google.android.gms.people.identity.PersonFactory.OfflineDatabaseData;
import com.google.android.gms.people.identity.PersonFactory.ServiceData;
import com.google.android.gms.people.identity.PersonListFactory;
import com.google.android.gms.people.identity.PersonListFactory.PersonListItemFactory;
import java.util.Collections;
import java.util.List;

public abstract class DefaultPersonListFactoryBase<PersonType> implements PersonListFactory<PersonType> {

    /* renamed from: com.google.android.gms.people.identity.internal.DefaultPersonListFactoryBase.1 */
    class AnonymousClass1 implements PersonListItemFactory<PersonType> {
        final /* synthetic */ List val$contactList;
        final /* synthetic */ List val$profileList;

        AnonymousClass1(List list, List list2) {
            this.val$profileList = list;
            this.val$contactList = list2;
        }

        public int getCount() {
            return this.val$profileList.size() + this.val$contactList.size();
        }

        public String getQualifiedId(int index) {
            return DefaultPersonListFactoryBase.this.getQualifiedId(get(index));
        }

        public PersonType get(int index) {
            if (index < this.val$profileList.size()) {
                return this.val$profileList.get(index);
            }
            return this.val$contactList.get(index - this.val$profileList.size());
        }
    }

    protected abstract String getQualifiedId(PersonType personType);

    protected abstract List<PersonType> interpret(OfflineDatabaseData offlineDatabaseData);

    protected abstract List<PersonType> interpret(ServiceData serviceData);

    protected abstract List<PersonType> interpret(ContactData[] contactDataArr);

    public PersonListItemFactory<PersonType> buildList(ServiceData serviceData, ContactData[] contactData, OfflineDatabaseData offlineDatabaseData) {
        List<PersonType> profileList;
        List<PersonType> contactList;
        if (serviceData != null) {
            profileList = interpret(serviceData);
        } else if (offlineDatabaseData != null) {
            profileList = interpret(offlineDatabaseData);
        } else {
            profileList = Collections.emptyList();
        }
        if (contactData != null) {
            contactList = interpret(contactData);
        } else {
            contactList = Collections.emptyList();
        }
        return new AnonymousClass1(profileList, contactList);
    }
}
