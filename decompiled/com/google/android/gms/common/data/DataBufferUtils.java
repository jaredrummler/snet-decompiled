package com.google.android.gms.common.data;

import android.os.Bundle;
import java.util.ArrayList;

public final class DataBufferUtils {
    public static final String KEY_NEXT_PAGE_TOKEN = "next_page_token";
    public static final String KEY_PREV_PAGE_TOKEN = "prev_page_token";
    public static final String PAGE_PLACEHOLDER_TOKEN = "has_local_data";

    private DataBufferUtils() {
    }

    public static <T, E extends Freezable<T>> ArrayList<T> freezeAndClose(DataBuffer<E> buffer) {
        ArrayList<T> frozen = new ArrayList(buffer.getCount());
        try {
            for (E freezable : buffer) {
                frozen.add(freezable.freeze());
            }
            return frozen;
        } finally {
            buffer.close();
        }
    }

    public static boolean hasNextPage(DataBuffer<?> buffer) {
        Bundle metadata = buffer.getMetadata();
        return (metadata == null || metadata.getString(KEY_NEXT_PAGE_TOKEN) == null) ? false : true;
    }

    public static boolean hasPrevPage(DataBuffer<?> buffer) {
        Bundle metadata = buffer.getMetadata();
        return (metadata == null || metadata.getString(KEY_PREV_PAGE_TOKEN) == null) ? false : true;
    }

    public static boolean hasData(DataBuffer<?> buffer) {
        return buffer != null && buffer.getCount() > 0;
    }
}
