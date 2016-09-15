package com.google.android.gms.common.data;

import android.content.ContentValues;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.data.DataHolder.Builder;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class DataBufferSafeParcelable<T extends SafeParcelable> extends AbstractDataBuffer<T> {
    private static final String[] ALL_FIELDS;
    public static final String DATA_FIELD = "data";
    private final Creator<T> mCreator;

    static {
        ALL_FIELDS = new String[]{DATA_FIELD};
    }

    public DataBufferSafeParcelable(DataHolder dataHolder, Creator<T> creator) {
        super(dataHolder);
        this.mCreator = creator;
    }

    public static Builder buildDataHolder() {
        return DataHolder.builder(ALL_FIELDS);
    }

    public static <T extends SafeParcelable> void addValue(Builder dataHolder, T value) {
        Parcel p = Parcel.obtain();
        value.writeToParcel(p, 0);
        ContentValues values = new ContentValues();
        values.put(DATA_FIELD, p.marshall());
        dataHolder.withRow(values);
        p.recycle();
    }

    public T get(int row) {
        byte[] rawData = this.mDataHolder.getByteArray(DATA_FIELD, row, this.mDataHolder.getWindowIndex(row));
        Parcel p = Parcel.obtain();
        p.unmarshall(rawData, 0, rawData.length);
        p.setDataPosition(0);
        SafeParcelable result = (SafeParcelable) this.mCreator.createFromParcel(p);
        p.recycle();
        return result;
    }
}
