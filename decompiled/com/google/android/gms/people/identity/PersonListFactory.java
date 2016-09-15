package com.google.android.gms.people.identity;

import com.google.android.gms.people.identity.PersonFactory.ContactData;
import com.google.android.gms.people.identity.PersonFactory.OfflineDatabaseData;
import com.google.android.gms.people.identity.PersonFactory.ServiceData;
import javax.annotation.Nullable;

public interface PersonListFactory<PersonType> {

    public interface PersonListItemFactory<PersonType> {
        PersonType get(int i);

        int getCount();

        String getQualifiedId(int i);
    }

    PersonListItemFactory<PersonType> buildList(@Nullable ServiceData serviceData, @Nullable ContactData[] contactDataArr, @Nullable OfflineDatabaseData offlineDatabaseData);
}
