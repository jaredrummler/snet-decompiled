package com.google.android.gms.common.data;

import android.content.Context;
import android.text.TextUtils;
import com.google.android.gms.common.data.TextFilterable.StringFilter;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.ArrayList;
import java.util.Locale;

public final class TextFilteredDataBuffer<T> extends FilteredDataBuffer<T> implements TextFilterable {
    private AbstractDataBuffer<T> mAbstractDataBuffer;
    private final String mColumnName;
    private final ArrayList<Integer> mEntityOffsets;
    private String mFilterTerm;
    private Locale mLocale;
    private StringFilter mStringFilter;

    public TextFilteredDataBuffer(AbstractDataBuffer<T> dataBuffer, String columnName) {
        super(dataBuffer);
        this.mEntityOffsets = new ArrayList();
        this.mAbstractDataBuffer = dataBuffer;
        this.mColumnName = columnName;
    }

    public int getCount() {
        return TextUtils.isEmpty(this.mFilterTerm) ? this.mDataBuffer.getCount() : this.mEntityOffsets.size();
    }

    public int computeRealPosition(int position) {
        if (TextUtils.isEmpty(this.mFilterTerm)) {
            return position;
        }
        if (position >= 0 && position < this.mEntityOffsets.size()) {
            return ((Integer) this.mEntityOffsets.get(position)).intValue();
        }
        throw new IllegalArgumentException("Position " + position + " is out of bounds " + "for this buffer");
    }

    @VisibleForTesting
    public void setFilterTerm(Context context, String term) {
        setFilterTerm(context, CONTAINS, term);
    }

    public void setFilterTerm(Context context, StringFilter stringFilter, String term) {
        Preconditions.checkNotNull(stringFilter);
        this.mFilterTerm = term;
        this.mStringFilter = stringFilter;
        if (TextUtils.isEmpty(this.mFilterTerm)) {
            this.mEntityOffsets.clear();
            return;
        }
        this.mLocale = context.getResources().getConfiguration().locale;
        this.mFilterTerm = cleanString(this.mFilterTerm);
        filterEntities();
    }

    private String cleanString(String term) {
        term = term.toLowerCase(this.mLocale);
        StringBuilder cleansedString = new StringBuilder();
        int n = term.length();
        for (int i = 0; i < n; i++) {
            if (!Character.isIdentifierIgnorable(term.charAt(i))) {
                cleansedString.append(term.charAt(i));
            }
        }
        return cleansedString.toString();
    }

    private void filterEntities() {
        this.mEntityOffsets.clear();
        DataHolder dataHolder = this.mAbstractDataBuffer.mDataHolder;
        String markerColumn = this.mColumnName;
        boolean isEntityBuffer = this.mAbstractDataBuffer instanceof EntityBuffer;
        int size = this.mAbstractDataBuffer.getCount();
        for (int i = 0; i < size; i++) {
            int internalPosition;
            if (isEntityBuffer) {
                internalPosition = ((EntityBuffer) this.mAbstractDataBuffer).getRowIndex(i);
            } else {
                internalPosition = i;
            }
            String value = dataHolder.getString(markerColumn, internalPosition, dataHolder.getWindowIndex(internalPosition));
            if (!TextUtils.isEmpty(value) && this.mStringFilter.matches(cleanString(value), this.mFilterTerm)) {
                this.mEntityOffsets.add(Integer.valueOf(i));
            }
        }
    }
}
