package com.google.kidsmanagement.v1;

public interface LocationCommonProto {

    public interface ConsentType {
        public static final int LOCATION_HISTORY = 2;
        public static final int LOCATION_MODE_ALL_DEVICES = 1;
        public static final int LOCATION_REPORTING_ALL_DEVICES = 3;
        public static final int LOCATION_SHARED_WITH_PARENT = 5;
        public static final int LOCATION_SHARING = 4;
        public static final int UNSPECIFIED_CONSENT_TYPE = 0;
    }
}
