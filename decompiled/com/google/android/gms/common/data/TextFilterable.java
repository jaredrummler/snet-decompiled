package com.google.android.gms.common.data;

import android.content.Context;

public interface TextFilterable {
    public static final StringFilter CONTAINS;
    public static final StringFilter STARTS_WITH;
    public static final StringFilter WORD_STARTS_WITH;

    public interface StringFilter {
        boolean matches(String str, String str2);
    }

    void setFilterTerm(Context context, StringFilter stringFilter, String str);

    void setFilterTerm(Context context, String str);

    static {
        CONTAINS = new StringFilter() {
            public boolean matches(String value, String filterTerm) {
                return value.contains(filterTerm);
            }
        };
        STARTS_WITH = new StringFilter() {
            public boolean matches(String value, String filterTerm) {
                return value.startsWith(filterTerm);
            }
        };
        WORD_STARTS_WITH = new StringFilter() {
            public boolean matches(String value, String filterTerm) {
                return value.startsWith(filterTerm) || value.contains(" " + filterTerm);
            }
        };
    }
}
