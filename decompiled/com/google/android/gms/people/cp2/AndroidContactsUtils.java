package com.google.android.gms.people.cp2;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.RequiresPermission;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.people.model.AggregatedPerson;
import com.google.android.gms.people.model.AutocompleteEntry;
import java.util.Iterator;

@VisibleForTesting
public class AndroidContactsUtils {
    public static final String TAG = "PeopleCp2Helper";

    private AndroidContactsUtils() {
    }

    @RequiresPermission("android.permission.READ_CONTACTS")
    @VisibleForTesting
    public static Uri getContactLookupUriFromAndroidContactId(Context context, long contactId) {
        try {
            return PeopleCp2Helper.getContactLookupUriFromAndroidContactId(context, contactId);
        } catch (SecurityException e) {
            return null;
        }
    }

    @RequiresPermission("android.permission.READ_CONTACTS")
    @VisibleForTesting
    public static Uri getContactLookupUriFromGaiaId(Context context, String account, String gaiaId) {
        try {
            return PeopleCp2Helper.getContactLookupUriFromAndroidContactId(context, PeopleCp2Helper.findContactByGaiaId(context, account, gaiaId));
        } catch (SecurityException e) {
            return null;
        }
    }

    @RequiresPermission("android.permission.READ_CONTACTS")
    @VisibleForTesting
    public static Uri getContactLookupUriFromFocusContactId(Context context, String account, String focusId) {
        try {
            return PeopleCp2Helper.getContactLookupUriFromAndroidContactId(context, PeopleCp2Helper.findContactByFocusId(context, account, focusId));
        } catch (SecurityException e) {
            return null;
        }
    }

    @RequiresPermission("android.permission.READ_CONTACTS")
    @VisibleForTesting
    public static Uri getContactLookupUri(Context context, AutocompleteEntry item) {
        try {
            if (item.getAndroidContactId() > 0) {
                return getContactLookupUriFromAndroidContactId(context, item.getAndroidContactId());
            }
            if (item.getFocusContactId() != null) {
                return getContactLookupUriFromFocusContactId(context, item.getOwnerAccountName(), item.getFocusContactId());
            }
            if (item.getGaiaId() != null) {
                return getContactLookupUriFromGaiaId(context, item.getOwnerAccountName(), item.getGaiaId());
            }
            return null;
        } catch (SecurityException e) {
        }
    }

    private static Long getFirstContactId(AggregatedPerson item) {
        Iterable<Long> ids = item.getContactIds();
        if (ids == null) {
            return null;
        }
        Iterator<Long> it = ids.iterator();
        if (it.hasNext()) {
            return (Long) it.next();
        }
        return null;
    }

    @VisibleForTesting
    public static Uri getContactLookupUri(Context context, AggregatedPerson item) {
        try {
            Long contactId = getFirstContactId(item);
            if (contactId != null) {
                return getContactLookupUriFromAndroidContactId(context, contactId.longValue());
            }
            if (item.getGaiaId() != null) {
                return getContactLookupUriFromGaiaId(context, item.getOwnerAccountName(), item.getGaiaId());
            }
            return null;
        } catch (SecurityException e) {
        }
    }

    @RequiresPermission("android.permission.READ_CONTACTS")
    @VisibleForTesting
    public static String getPhotoUriFromFocusContactId(Context context, String account, String focusId) {
        try {
            return PeopleCp2Helper.findPhotoUriByFocusId(context, account, focusId);
        } catch (SecurityException e) {
            return null;
        }
    }
}
