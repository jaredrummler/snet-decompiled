package com.google.android.gms.people.identity.internal;

import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.people.identity.models.Person.Organizations;

public class IdentityUtils {
    @VisibleForTesting
    public static final String SCHOOL_ORGANIZATION = "school";
    @VisibleForTesting
    public static final String WORK_ORGANIZATION = "work";

    private IdentityUtils() {
    }

    public static final boolean isSchoolOrganization(Organizations organizations) {
        return organizations.hasType() && SCHOOL_ORGANIZATION.equalsIgnoreCase(organizations.getType());
    }

    public static final boolean isWorkOrganization(Organizations organizations) {
        return organizations.hasType() && WORK_ORGANIZATION.equalsIgnoreCase(organizations.getType());
    }
}
