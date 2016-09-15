package com.google.android.gms.people.model;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.people.internal.PersonRef;
import com.google.android.gms.people.internal.agg.PhoneEmailDecoder.EmailDecoder;
import com.google.android.gms.people.internal.agg.PhoneEmailDecoder.PhoneDecoder;

@VisibleForTesting
public final class PersonBuffer extends DataBufferWithSyncInfo<Person> {
    private final EmailDecoder mEmailDecoder;
    private final PhoneDecoder mPhoneDecoder;

    public PersonBuffer(DataHolder dataHolder, PhoneDecoder phoneDecoder, EmailDecoder emailDecoder) {
        super(dataHolder);
        this.mPhoneDecoder = phoneDecoder;
        this.mEmailDecoder = emailDecoder;
    }

    public Person get(int position) {
        return new PersonRef(this.mDataHolder, position, getMetadata(), this.mPhoneDecoder, this.mEmailDecoder);
    }

    public String toString() {
        return "People:size=" + getCount();
    }
}
