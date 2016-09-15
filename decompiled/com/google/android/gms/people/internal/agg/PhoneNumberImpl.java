package com.google.android.gms.people.internal.agg;

import android.text.TextUtils;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.lint.BuildConfig;
import com.google.android.gms.people.internal.PeopleUtils;
import com.google.android.gms.people.model.PhoneNumber;

@VisibleForTesting
public class PhoneNumberImpl implements PhoneNumber {
    private String mStrippedNumber;
    private final String mType;
    private final String mValue;

    public PhoneNumberImpl(String type, String value) {
        this.mType = type;
        this.mValue = value;
    }

    public String getType() {
        return this.mType;
    }

    public String getValue() {
        return this.mValue;
    }

    private String getStrippedNumber() {
        if (this.mStrippedNumber == null) {
            this.mStrippedNumber = getStrippedNumber(this.mValue);
        }
        return this.mStrippedNumber;
    }

    @VisibleForTesting
    static String getStrippedNumber(String phoneNumber) {
        if (TextUtils.isEmpty(phoneNumber)) {
            return BuildConfig.VERSION_NAME;
        }
        StringBuilder sb = PeopleUtils.getTemporaryStringBuilder();
        for (int i = 0; i < phoneNumber.length(); i++) {
            char ch = phoneNumber.charAt(i);
            if (Character.digit(ch, 10) != -1 || ch == '+' || ch == ',' || ch == ';' || (('a' <= ch && ch <= 'z') || ('A' <= ch && ch <= 'Z'))) {
                sb.append(ch);
            }
        }
        return sb.toString();
    }

    public boolean equals(Object object) {
        if (!(object instanceof PhoneNumberImpl)) {
            return false;
        }
        return Objects.equal(getStrippedNumber(), ((PhoneNumberImpl) object).getStrippedNumber());
    }

    @VisibleForTesting
    public String toString() {
        return "PhoneNumber:[Value=" + (this.mValue != null ? this.mValue : "null") + " Type=" + (this.mType != null ? this.mType : "null") + "]";
    }
}
