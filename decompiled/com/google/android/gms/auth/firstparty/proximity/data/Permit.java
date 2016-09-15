package com.google.android.gms.auth.firstparty.proximity.data;

import android.os.Parcel;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Class(creator = "PermitCreator")
public class Permit implements SafeParcelable {
    public static final String CHANNEL_BLUETOOTH_CLASSIC = "bluetooth_classic";
    public static final PermitCreator CREATOR;
    public static final String TYPE_UNLOCK = "unlock";
    private static final int VERSION = 1;
    @Field(id = 3)
    final String mAccountId;
    final Set<String> mAllowedChannels;
    @Field(id = 8)
    List<String> mAllowedChannelsCache;
    @Field(id = 2)
    final String mId;
    @Field(id = 6)
    final PermitAccess mLicense;
    final Map<String, PermitAccess> mRequesterAccesses;
    @Field(id = 7)
    List<PermitAccess> mRequesterAccessesCache;
    @Field(id = 5)
    final String mType;
    @VersionField(id = 1)
    final int mVersion;

    public static class Builder {
        private String mAccountId;
        private Set<String> mAllowedChannels;
        private String mId;
        private PermitAccess mLicense;
        private Map<String, PermitAccess> mRequesterAccesses;
        private String mType;

        public Builder() {
            this.mRequesterAccesses = new HashMap();
            this.mAllowedChannels = new HashSet();
        }

        public Permit build() {
            return new Permit();
        }

        public Builder addAllowedChannel(String allowedChannel) {
            this.mAllowedChannels.add(allowedChannel);
            return this;
        }

        public Builder addRequesterAccess(PermitAccess requesterAccess) {
            this.mRequesterAccesses.put(requesterAccess.getId(), requesterAccess);
            return this;
        }

        public Builder setAccountId(String accountId) {
            this.mAccountId = accountId;
            return this;
        }

        public Builder setId(String id) {
            this.mId = id;
            return this;
        }

        public Builder setLicense(PermitAccess license) {
            this.mLicense = license;
            return this;
        }

        public Builder setType(String type) {
            this.mType = type;
            return this;
        }
    }

    static {
        CREATOR = new PermitCreator();
    }

    private Permit(Builder builder) {
        this((int) VERSION, builder.mId, builder.mAccountId, builder.mType, builder.mLicense, builder.mRequesterAccesses, builder.mAllowedChannels);
    }

    @Constructor
    Permit(@Param(id = 1) int version, @Param(id = 2) String id, @Param(id = 3) String accountId, @Param(id = 5) String type, @Param(id = 6) PermitAccess license, @Param(id = 7) List<PermitAccess> requesterAccessesCache, @Param(id = 8) List<String> allowedChannelsCache) {
        this(version, id, accountId, type, license, getPermitAccessesFromCache(requesterAccessesCache), new HashSet(allowedChannelsCache));
    }

    private Permit(int version, String id, String accountId, String type, PermitAccess license, Map<String, PermitAccess> requesterAccesses, Set<String> allowedChannels) {
        this.mVersion = version;
        this.mId = Preconditions.checkNotEmpty(id);
        this.mAccountId = Preconditions.checkNotEmpty(accountId);
        this.mType = Preconditions.checkNotEmpty(type);
        this.mLicense = (PermitAccess) Preconditions.checkNotNull(license);
        this.mRequesterAccesses = requesterAccesses == null ? new HashMap() : new HashMap(requesterAccesses);
        this.mAllowedChannels = allowedChannels == null ? new HashSet() : new HashSet(allowedChannels);
    }

    public String getAccountId() {
        return this.mAccountId;
    }

    public void addAllowedChannel(String allowedChannel) {
        this.mAllowedChannels.add(allowedChannel);
    }

    public List<String> getAllowedChannels() {
        return Collections.unmodifiableList(new ArrayList(this.mAllowedChannels));
    }

    public boolean hasAllowedChannel(String allowedChannel) {
        return this.mAllowedChannels.contains(allowedChannel);
    }

    public void removeAllowedChannel(String allowedChannel) {
        this.mAllowedChannels.remove(allowedChannel);
    }

    public String getId() {
        return this.mId;
    }

    public PermitAccess getLicense() {
        return this.mLicense;
    }

    public void addRequesterAccess(PermitAccess requesterAccess) {
        this.mRequesterAccesses.put(requesterAccess.getId(), requesterAccess);
    }

    public PermitAccess getRequesterAccessById(String id) {
        return (PermitAccess) this.mRequesterAccesses.get(id);
    }

    public List<PermitAccess> getRequesterAccesses() {
        return Collections.unmodifiableList(new ArrayList(this.mRequesterAccesses.values()));
    }

    public List<PermitAccess> getRequesterAccesses(String type) {
        List<PermitAccess> permitAccesses = new ArrayList();
        for (PermitAccess permitAccess : this.mRequesterAccesses.values()) {
            if (TextUtils.equals(type, permitAccess.getType())) {
                permitAccesses.add(permitAccess);
            }
        }
        return Collections.unmodifiableList(permitAccesses);
    }

    public PermitAccess removeRequesterAccess(String id) {
        return (PermitAccess) this.mRequesterAccesses.remove(id);
    }

    public String getType() {
        return this.mType;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        this.mRequesterAccessesCache = new ArrayList(this.mRequesterAccesses.values());
        this.mAllowedChannelsCache = new ArrayList(this.mAllowedChannels);
        PermitCreator.writeToParcel(this, dest, flags);
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Permit)) {
            return false;
        }
        Permit permit = (Permit) obj;
        if (TextUtils.equals(this.mAccountId, permit.mAccountId) && TextUtils.equals(this.mId, permit.mId) && TextUtils.equals(this.mType, permit.mType) && this.mLicense.equals(permit.mLicense) && this.mAllowedChannels.equals(permit.mAllowedChannels) && this.mRequesterAccesses.equals(permit.mRequesterAccesses)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (31 * ((31 * ((31 * ((31 * ((31 * (this.mId.hashCode() + 527)) + this.mAccountId.hashCode())) + this.mType.hashCode())) + this.mAllowedChannels.hashCode())) + this.mLicense.hashCode())) + this.mRequesterAccesses.hashCode();
    }

    private static Map<String, PermitAccess> getPermitAccessesFromCache(List<PermitAccess> cache) {
        Map<String, PermitAccess> result = new HashMap();
        for (PermitAccess permitAccess : cache) {
            result.put(permitAccess.getId(), permitAccess);
        }
        return result;
    }
}
