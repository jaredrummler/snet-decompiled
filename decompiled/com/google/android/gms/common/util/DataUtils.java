package com.google.android.gms.common.util;

import android.content.Context;
import android.content.res.Resources;
import android.database.CharArrayBuffer;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.images.internal.ImageUrlUtils;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public final class DataUtils {
    public static void copyStringToBuffer(String toCopy, CharArrayBuffer dataOut) {
        if (TextUtils.isEmpty(toCopy)) {
            dataOut.sizeCopied = 0;
        } else if (dataOut.data == null || dataOut.data.length < toCopy.length()) {
            dataOut.data = toCopy.toCharArray();
        } else {
            toCopy.getChars(0, toCopy.length(), dataOut.data, 0);
        }
        dataOut.sizeCopied = toCopy.length();
    }

    public static Bitmap loadImage(Context context, Uri imageUri, String logTag) {
        Bitmap bitmap = null;
        InputStream is = null;
        try {
            is = context.getContentResolver().openInputStream(imageUri);
            bitmap = BitmapFactory.decodeStream(is, null, getBitmapOptions(context, imageUri));
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    Log.e(logTag, "Failed to close image input stream");
                }
            }
        } catch (FileNotFoundException e2) {
            Log.w(logTag, "No image found for URI: " + imageUri);
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e3) {
                    Log.e(logTag, "Failed to close image input stream");
                }
            }
        } catch (Throwable th) {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e4) {
                    Log.e(logTag, "Failed to close image input stream");
                }
            }
        }
        return bitmap;
    }

    public static Options getBitmapOptions(Context context, Uri imageUri) {
        Options opts = new Options();
        opts.inTargetDensity = context.getResources().getDisplayMetrics().densityDpi;
        opts.inDensity = ImageUrlUtils.getImageDensity(imageUri);
        return opts;
    }

    @VisibleForTesting
    public static byte[] loadImageBytes(Resources resources, int imageResourceId) {
        try {
            return IOUtils.readInputStreamFully(resources.openRawResource(imageResourceId));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static byte[] loadImageBytes(BitmapDrawable bitmapDrawable) {
        return loadImageBytes(bitmapDrawable.getBitmap());
    }

    public static byte[] loadImageBytes(Bitmap bitmap) {
        return loadImageBytes(bitmap, 100);
    }

    public static byte[] loadImageBytes(Bitmap bitmap, int quality) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(CompressFormat.JPEG, quality, stream);
        return stream.toByteArray();
    }
}
