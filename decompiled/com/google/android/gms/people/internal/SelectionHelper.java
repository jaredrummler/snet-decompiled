package com.google.android.gms.people.internal;

import android.text.TextUtils;

public class SelectionHelper {
    private boolean mAndNeeded;
    private final StringBuilder mSB;

    public SelectionHelper() {
        this.mAndNeeded = false;
        this.mSB = new StringBuilder();
    }

    public StringBuilder getStringBuilder() {
        return this.mSB;
    }

    public void addRawString(String rawText) {
        if (!TextUtils.isEmpty(rawText)) {
            this.mSB.append(rawText);
        }
    }

    public void addWithAnd(String selection) {
        if (!TextUtils.isEmpty(selection)) {
            if (this.mAndNeeded) {
                this.mSB.append(" AND ");
            }
            this.mSB.append(selection);
            this.mAndNeeded = true;
        }
    }

    public String toString() {
        return this.mSB.toString();
    }
}
