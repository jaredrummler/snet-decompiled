package com.google.android.gms.people.identity.external;

import com.google.android.gms.common.util.VisibleForTesting;

@VisibleForTesting
public final class DataKind {
    public String detailColumn;
    public String mimeType;
    public String resourcePackageName;
    public String summaryColumn;

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("DataKind<");
        sb.append(" resourcePackageName=").append(this.resourcePackageName);
        sb.append(" mimeType=").append(this.mimeType);
        sb.append(" summaryColumn=").append(this.summaryColumn);
        sb.append(" detailColumn=").append(this.detailColumn);
        sb.append(">");
        return sb.toString();
    }
}
