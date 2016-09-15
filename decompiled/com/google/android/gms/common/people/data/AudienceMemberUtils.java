package com.google.android.gms.common.people.data;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.people.model.Circle;
import com.google.android.gms.people.model.EmailAddress;
import com.google.android.gms.people.model.Person;
import com.google.android.gms.people.model.PhoneNumber;
import java.util.List;

public final class AudienceMemberUtils {

    private static class AudienceMemberPerson implements Person {
        private final AudienceMember mMember;

        public AudienceMemberPerson(AudienceMember member) {
            Preconditions.checkArgument(member.isPerson(), "AudienceMember must be a person.");
            this.mMember = member;
        }

        public long getRowId() {
            throw new UnsupportedOperationException();
        }

        public String getOwnerAccountName() {
            throw new UnsupportedOperationException();
        }

        public String getOwnerPlusPageId() {
            throw new UnsupportedOperationException();
        }

        public String getAccountName() {
            throw new UnsupportedOperationException();
        }

        public String getPlusPageGaiaId() {
            throw new UnsupportedOperationException();
        }

        public String getQualifiedId() {
            return this.mMember.getPeopleQualifiedId();
        }

        public String getGaiaId() {
            throw new UnsupportedOperationException();
        }

        public String getName() {
            return this.mMember.getDisplayName();
        }

        public String getGivenName() {
            throw new UnsupportedOperationException();
        }

        public String getFamilyName() {
            throw new UnsupportedOperationException();
        }

        public String getAvatarUrl() {
            return this.mMember.getAvatarUrl();
        }

        public String getNameSortKey() {
            return this.mMember.getDisplayName();
        }

        public String getInteractionRankSortKey() {
            throw new UnsupportedOperationException();
        }

        public int getProfileType() {
            return -1;
        }

        public String[] getBelongingCircleIds() {
            throw new UnsupportedOperationException();
        }

        public boolean isBlocked() {
            throw new UnsupportedOperationException();
        }

        public int getInViewerDomain() {
            throw new UnsupportedOperationException();
        }

        public long getLastModifiedTime() {
            throw new UnsupportedOperationException();
        }

        public boolean isNameVerified() {
            throw new UnsupportedOperationException();
        }

        public double getAffinity1() {
            throw new UnsupportedOperationException();
        }

        public double getAffinity2() {
            throw new UnsupportedOperationException();
        }

        public double getAffinity3() {
            throw new UnsupportedOperationException();
        }

        public double getAffinity4() {
            throw new UnsupportedOperationException();
        }

        public double getAffinity5() {
            throw new UnsupportedOperationException();
        }

        public String getLoggingId1() {
            throw new UnsupportedOperationException();
        }

        public String getLoggingId2() {
            throw new UnsupportedOperationException();
        }

        public String getLoggingId3() {
            throw new UnsupportedOperationException();
        }

        public String getLoggingId4() {
            throw new UnsupportedOperationException();
        }

        public String getLoggingId5() {
            throw new UnsupportedOperationException();
        }

        public String[] getPeopleInCommon() {
            throw new UnsupportedOperationException();
        }

        public Iterable<EmailAddress> getEmailAddresses() {
            throw new UnsupportedOperationException();
        }

        public Iterable<PhoneNumber> getPhoneNumbers() {
            throw new UnsupportedOperationException();
        }

        public boolean equals(Object obj) {
            if (obj instanceof Person) {
                return getQualifiedId().equals(((Person) obj).getQualifiedId());
            }
            return false;
        }

        public int hashCode() {
            return getQualifiedId().hashCode();
        }
    }

    private AudienceMemberUtils() {
    }

    public static Person toPerson(AudienceMember member) {
        return new AudienceMemberPerson(member);
    }

    public static AudienceMember createAudienceMemberFromPerson(Person person) {
        return AudienceMember.forPersonWithPeopleQualifiedId(person.getQualifiedId(), person.getName(), person.getAvatarUrl());
    }

    public static AudienceMember createAudienceMemberFromCircle(Circle circle) {
        if (circle.getCircleType() == -1) {
            return AudienceMember.forCircle(circle.getCircleId(), circle.getCircleName());
        }
        return AudienceMember.forGroup(circle.getCircleId(), circle.getCircleName());
    }

    public static boolean hasPublicOrExtendedCircles(List<AudienceMember> audienceMembers) {
        int size = audienceMembers.size();
        for (int i = 0; i < size; i++) {
            int currentCircleType = ((AudienceMember) audienceMembers.get(i)).getCircleType();
            if (currentCircleType == 1 || currentCircleType == 4) {
                return true;
            }
        }
        return false;
    }

    public static boolean hasPublicOrExtendedCircles(Audience audience) {
        Preconditions.checkNotNull(audience, "Audience must not be null.");
        return hasPublicOrExtendedCircles(audience.getAudienceMemberList());
    }
}
