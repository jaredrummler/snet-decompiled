package com.google.geo.sidekick;

public interface SidekickErrorCodeProto {

    public interface SidekickErrorCode {
        public static final int AUTHENTICATION_ERROR = 1;
        public static final int BIGTABLE_ERROR = 3;
        public static final int EVENT_QUERY_INVALID_ERROR = 9;
        public static final int GEOCODING_ERROR = 13;
        public static final int INVALID_REQUEST = 15;
        public static final int KANSAS_ERROR = 11;
        public static final int LATITUDE_SOCIAL_SERVER_ERROR = 14;
        public static final int MERLIN_ERROR = 8;
        public static final int MULTIPLE_SERVER_ERRORS = 12;
        public static final int NO_GAIA_ACTIVE_SESSION = 2;
        public static final int PATHFINDER_RPC_ERROR = 5;
        public static final int PLACEVAULT_ERROR = 7;
        public static final int QUERY_MISSING = 6;
        public static final int SEARCH_MY_HISTORY_ERROR = 10;
        public static final int TOO_MANY_CONCURRENT_REQUESTS = 16;
        public static final int UNEXPECTED_SERVER_ERROR = 4;
    }
}
