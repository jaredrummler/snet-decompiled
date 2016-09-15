package com.google.android.gms.people.identity.internal;

import android.content.Context;
import com.google.android.gms.people.identity.PersonFactory;
import com.google.android.gms.people.identity.PersonFactory.ContactData;
import com.google.android.gms.people.identity.PersonFactory.OfflineDatabaseData;
import com.google.android.gms.people.identity.PersonFactory.ServiceData;
import com.google.android.gms.people.identity.internal.models.PersonImpl;
import com.google.android.gms.people.identity.models.Person;

public final class DefaultPersonFactory extends DefaultPersonFactoryBase<PersonImpl> implements PersonFactory<Person> {
    public /* bridge */ /* synthetic */ Object build(Context context, Object obj, ServiceData serviceData, ContactData contactData, OfflineDatabaseData offlineDatabaseData) {
        return super.build(context, obj, serviceData, contactData, offlineDatabaseData);
    }

    protected PersonImpl buildNewPerson() {
        return new PersonImpl();
    }
}
