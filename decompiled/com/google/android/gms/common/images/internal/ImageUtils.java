package com.google.android.gms.common.images.internal;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewCompat;

public final class ImageUtils {
    public static Bitmap frameBitmapInCircle(Bitmap input) {
        if (input == null) {
            return null;
        }
        int targetSize;
        int targetX;
        int targetY;
        int inputWidth = input.getWidth();
        int inputHeight = input.getHeight();
        if (inputWidth >= inputHeight) {
            targetSize = inputHeight;
            targetX = (targetSize - inputWidth) / 2;
            targetY = 0;
        } else {
            targetSize = inputWidth;
            targetX = 0;
            targetY = (targetSize - inputHeight) / 2;
        }
        Bitmap output = Bitmap.createBitmap(targetSize, targetSize, Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        Paint paint = new Paint(1);
        paint.setColor(ViewCompat.MEASURED_STATE_MASK);
        canvas.drawCircle((float) (targetSize / 2), (float) (targetSize / 2), (float) (targetSize / 2), paint);
        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        canvas.drawBitmap(input, (float) targetX, (float) targetY, paint);
        return output;
    }

    public static Drawable frameDrawableInCircle(Resources res, Drawable input) {
        return new BitmapDrawable(res, frameBitmapInCircle(convertDrawableToBitmap(input)));
    }

    private static Bitmap convertDrawableToBitmap(Drawable drawable) {
        if (drawable == null) {
            return null;
        }
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }
        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }
}
