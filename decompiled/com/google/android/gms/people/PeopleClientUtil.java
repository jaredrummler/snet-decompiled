package com.google.android.gms.people;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.ParcelFileDescriptor;
import com.google.android.gms.common.util.IOUtils;
import java.io.Closeable;
import java.io.FileInputStream;

public class PeopleClientUtil {
    private PeopleClientUtil() {
    }

    public static Bitmap decodeFileDescriptor(ParcelFileDescriptor pfd) {
        if (pfd == null) {
            return null;
        }
        Closeable fis = new FileInputStream(pfd.getFileDescriptor());
        try {
            Bitmap decodeStream = BitmapFactory.decodeStream(fis);
            return decodeStream;
        } finally {
            IOUtils.closeQuietly(fis);
        }
    }
}
