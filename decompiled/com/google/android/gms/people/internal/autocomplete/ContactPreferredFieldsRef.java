package com.google.android.gms.people.internal.autocomplete;

import com.google.android.gms.common.data.DataBufferRef;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.people.Autocomplete.ContactPreferredFields;
import com.google.android.gms.people.PeopleConstants.PeopleEmail;
import com.google.android.gsf.TalkContract.AccountSettingsColumns;
import java.util.HashMap;
import javax.annotation.Nonnull;

public class ContactPreferredFieldsRef extends DataBufferRef implements ContactPreferredFields {
    public static final String[] ALL_COLUMNS;

    static {
        ALL_COLUMNS = new String[]{PeopleEmail.EMAIL_ADDRESS, AccountSettingsColumns.NAME};
    }

    public static HashMap<String, Object> toMap(String email, String name) {
        HashMap<String, Object> map = new HashMap();
        map.put(PeopleEmail.EMAIL_ADDRESS, email);
        map.put(AccountSettingsColumns.NAME, name);
        return map;
    }

    public ContactPreferredFieldsRef(DataHolder holder, int dataRow) {
        super(holder, dataRow);
    }

    @Nonnull
    public CharSequence getEmail() {
        return getString(PeopleEmail.EMAIL_ADDRESS);
    }

    @Nonnull
    public CharSequence getName() {
        return getString(AccountSettingsColumns.NAME);
    }

    public ContactPreferredFields freeze() {
        return new ContactPreferredFieldsEntity(this);
    }
}
