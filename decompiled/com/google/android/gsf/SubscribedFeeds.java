package com.google.android.gsf;

import android.accounts.Account;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;
import com.google.android.gms.people.PeopleConstants.PeoplePostalAddress;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map.Entry;

public class SubscribedFeeds {
    private static final String SELECT_FEEDS_BY_ID = "_id=?";
    private static final String SELECT_SUBSCRIBED_FEEDS_BY_ACCOUNT_AND_AUTHORITY = "_sync_account=? AND _sync_account_type=? AND authority=?";

    public interface AccountColumns {
        public static final String _SYNC_ACCOUNT = "_sync_account";
        public static final String _SYNC_ACCOUNT_TYPE = "_sync_account_type";
    }

    public static final class Accounts implements BaseColumns, AccountColumns {
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/subscribedfeedaccount";
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/subscribedfeedaccounts";
        public static final Uri CONTENT_URI;
        public static final String DEFAULT_SORT_ORDER = "_SYNC_ACCOUNT_TYPE, _SYNC_ACCOUNT ASC";

        private Accounts() {
        }

        public static Cursor query(ContentResolver cr, String[] projection) {
            return cr.query(CONTENT_URI, projection, null, null, DEFAULT_SORT_ORDER);
        }

        public static Cursor query(ContentResolver cr, String[] projection, String where, String orderBy) {
            return cr.query(CONTENT_URI, projection, where, null, orderBy == null ? DEFAULT_SORT_ORDER : orderBy);
        }

        static {
            CONTENT_URI = Uri.parse("content://subscribedfeeds/accounts");
        }
    }

    public interface FeedColumns {
        public static final String AUTHORITY = "authority";
        public static final String FEED = "feed";
        public static final String SERVICE = "service";
        public static final String _SYNC_ACCOUNT = "_sync_account";
        public static final String _SYNC_ACCOUNT_TYPE = "_sync_account_type";
        public static final String _SYNC_DIRTY = "_sync_dirty";
        public static final String _SYNC_ID = "_sync_id";
        public static final String _SYNC_LOCAL_ID = "_sync_local_id";
        public static final String _SYNC_MARK = "_sync_mark";
        public static final String _SYNC_TIME = "_sync_time";
        public static final String _SYNC_VERSION = "_sync_version";
    }

    public static final class Feeds implements BaseColumns, FeedColumns {
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/subscribedfeed";
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/subscribedfeeds";
        public static final Uri CONTENT_URI;
        public static final String DEFAULT_SORT_ORDER = "_SYNC_ACCOUNT_TYPE, _SYNC_ACCOUNT ASC";
        public static final Uri DELETED_CONTENT_URI;

        private Feeds() {
        }

        public static Cursor query(ContentResolver cr, String[] projection) {
            return cr.query(CONTENT_URI, projection, null, null, DEFAULT_SORT_ORDER);
        }

        public static Cursor query(ContentResolver cr, String[] projection, String where, String[] whereArgs, String orderBy) {
            return cr.query(CONTENT_URI, projection, where, whereArgs, orderBy == null ? DEFAULT_SORT_ORDER : orderBy);
        }

        static {
            CONTENT_URI = Uri.parse("content://subscribedfeeds/feeds");
            DELETED_CONTENT_URI = Uri.parse("content://subscribedfeeds/deleted_feeds");
        }
    }

    private SubscribedFeeds() {
    }

    public static boolean manageSubscriptions(ContentResolver contentResolver, Account account, String authority, String serviceName, Collection<String> expectedFeedUrls) {
        HashMap<String, Long> existingFeeds = new HashMap();
        Cursor c = contentResolver.query(Feeds.CONTENT_URI, new String[]{PeoplePostalAddress._ID, FeedColumns.FEED}, SELECT_SUBSCRIBED_FEEDS_BY_ACCOUNT_AND_AUTHORITY, new String[]{account.name, account.type, authority}, null);
        if (c == null) {
            return false;
        }
        while (c.moveToNext()) {
            long id = c.getLong(0);
            String feed = c.getString(1);
            if (existingFeeds.containsKey(feed)) {
                contentResolver.delete(Feeds.CONTENT_URI, SELECT_FEEDS_BY_ID, new String[]{Long.toString(id)});
            } else {
                try {
                    existingFeeds.put(feed, Long.valueOf(id));
                } finally {
                    c.close();
                }
            }
        }
        for (String expectedFeed : expectedFeedUrls) {
            if (existingFeeds.containsKey(expectedFeed)) {
                existingFeeds.remove(expectedFeed);
            } else {
                ContentValues values = new ContentValues();
                values.put(FeedColumns._SYNC_ACCOUNT, account.name);
                values.put(FeedColumns._SYNC_ACCOUNT_TYPE, account.type);
                values.put(FeedColumns.FEED, expectedFeed);
                values.put(FeedColumns.SERVICE, serviceName);
                values.put(FeedColumns.AUTHORITY, authority);
                try {
                    contentResolver.insert(Feeds.CONTENT_URI, values);
                } catch (IllegalArgumentException e) {
                    return false;
                }
            }
        }
        for (Entry<String, Long> existing : existingFeeds.entrySet()) {
            try {
                contentResolver.delete(ContentUris.withAppendedId(Feeds.CONTENT_URI, ((Long) existing.getValue()).longValue()), null, null);
            } catch (IllegalArgumentException e2) {
                return false;
            }
        }
        return true;
    }

    public static boolean manageSubscriptions(ContentResolver contentResolver, Account account, String authority, String serviceName, String... expectedFeeds) {
        Collection expectedFeedUrls = new ArrayList(expectedFeeds.length);
        for (Object add : expectedFeeds) {
            expectedFeedUrls.add(add);
        }
        return manageSubscriptions(contentResolver, account, authority, serviceName, expectedFeedUrls);
    }

    public static Uri addFeed(ContentResolver resolver, String feed, Account account, String authority, String service) {
        ContentValues values = new ContentValues();
        values.put(FeedColumns.FEED, feed);
        values.put(FeedColumns._SYNC_ACCOUNT, account.name);
        values.put(FeedColumns._SYNC_ACCOUNT_TYPE, account.type);
        values.put(FeedColumns.AUTHORITY, authority);
        values.put(FeedColumns.SERVICE, service);
        return resolver.insert(Feeds.CONTENT_URI, values);
    }

    public static int deleteFeed(ContentResolver resolver, String feed, Account account, String authority) {
        StringBuilder where = new StringBuilder();
        where.append("_sync_account=?");
        where.append(" AND _sync_account_type=?");
        where.append(" AND feed=?");
        where.append(" AND authority=?");
        return resolver.delete(Feeds.CONTENT_URI, where.toString(), new String[]{account.name, account.type, feed, authority});
    }

    public static int deleteFeeds(ContentResolver resolver, Account account, String authority) {
        StringBuilder where = new StringBuilder();
        where.append("_sync_account=?");
        where.append(" AND _sync_account_type=?");
        where.append(" AND authority=?");
        return resolver.delete(Feeds.CONTENT_URI, where.toString(), new String[]{account.name, account.type, authority});
    }
}
