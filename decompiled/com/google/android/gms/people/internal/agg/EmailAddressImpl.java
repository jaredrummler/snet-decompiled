package com.google.android.gms.people.internal.agg;

import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.people.model.EmailAddress;
import com.google.android.snet.Csv;

@VisibleForTesting
public class EmailAddressImpl implements EmailAddress {
    private final double mAffinity1;
    private final double mAffinity2;
    private final double mAffinity3;
    private final double mAffinity4;
    private final double mAffinity5;
    private final String mLoggingId1;
    private final String mLoggingId2;
    private final String mLoggingId3;
    private final String mLoggingId4;
    private final String mLoggingId5;
    private final String mType;
    private final String mValue;

    public EmailAddressImpl(String type, String value) {
        this(type, value, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, null, null, null, null, null);
    }

    public EmailAddressImpl(String type, String value, double affinity1, double affinity2, double affinity3, double affinity4, double affinity5, String loggingId1, String loggingId2, String loggingId3, String loggingId4, String loggingId5) {
        this.mType = type;
        this.mValue = value;
        this.mAffinity1 = affinity1;
        this.mAffinity2 = affinity2;
        this.mAffinity3 = affinity3;
        this.mAffinity4 = affinity4;
        this.mAffinity5 = affinity5;
        this.mLoggingId1 = loggingId1;
        this.mLoggingId2 = loggingId2;
        this.mLoggingId3 = loggingId3;
        this.mLoggingId4 = loggingId4;
        this.mLoggingId5 = loggingId5;
    }

    public String getType() {
        return this.mType;
    }

    public String getValue() {
        return this.mValue;
    }

    public double getAffinity1() {
        return this.mAffinity1;
    }

    public double getAffinity2() {
        return this.mAffinity2;
    }

    public double getAffinity3() {
        return this.mAffinity3;
    }

    public double getAffinity4() {
        return this.mAffinity4;
    }

    public double getAffinity5() {
        return this.mAffinity5;
    }

    public String getLoggingId1() {
        return this.mLoggingId1;
    }

    public String getLoggingId2() {
        return this.mLoggingId2;
    }

    public String getLoggingId3() {
        return this.mLoggingId3;
    }

    public String getLoggingId4() {
        return this.mLoggingId4;
    }

    public String getLoggingId5() {
        return this.mLoggingId5;
    }

    public boolean equals(Object object) {
        if (!(object instanceof EmailAddressImpl)) {
            return false;
        }
        return Objects.equal(this.mValue, ((EmailAddressImpl) object).mValue);
    }

    @VisibleForTesting
    public String toString() {
        return "EmailAddress:[Value=" + (this.mValue != null ? this.mValue : "null") + " Type=" + (this.mType != null ? this.mType : "null") + " a1=" + this.mAffinity1 + Csv.COMMA + this.mLoggingId1 + " a2=" + this.mAffinity2 + Csv.COMMA + this.mLoggingId2 + " a3=" + this.mAffinity3 + Csv.COMMA + this.mLoggingId3 + " a4=" + this.mAffinity4 + Csv.COMMA + this.mLoggingId4 + " a5=" + this.mAffinity5 + Csv.COMMA + this.mLoggingId5 + "]";
    }
}
