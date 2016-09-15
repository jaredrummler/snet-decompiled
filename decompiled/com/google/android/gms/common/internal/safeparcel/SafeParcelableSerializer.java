package com.google.android.gms.common.internal.safeparcel;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Base64Utils;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.ArrayList;
import java.util.Iterator;

@VisibleForTesting
public final class SafeParcelableSerializer {
    public static <T extends SafeParcelable> byte[] serializeToBytes(T safeParcelable) {
        Parcel parcel = Parcel.obtain();
        safeParcelable.writeToParcel(parcel, 0);
        byte[] rawBytes = parcel.marshall();
        parcel.recycle();
        return rawBytes;
    }

    public static <T extends SafeParcelable> T deserializeFromBytes(byte[] serializedBytes, Creator<T> safeParcelableCreator) {
        Preconditions.checkNotNull(safeParcelableCreator);
        Parcel parcel = Parcel.obtain();
        parcel.unmarshall(serializedBytes, 0, serializedBytes.length);
        parcel.setDataPosition(0);
        SafeParcelable safeParcelable = (SafeParcelable) safeParcelableCreator.createFromParcel(parcel);
        parcel.recycle();
        return safeParcelable;
    }

    public static <T extends SafeParcelable> String serializeToString(T safeParcelable) {
        return Base64Utils.encodeUrlSafe(serializeToBytes(safeParcelable));
    }

    public static <T extends SafeParcelable> T deserializeFromString(String serializedString, Creator<T> safeParcelableCreator) {
        return deserializeFromBytes(Base64Utils.decodeUrlSafe(serializedString), safeParcelableCreator);
    }

    public static <T extends SafeParcelable> void serializeToIntentExtra(T safeParcelable, Intent intent, String extra) {
        intent.putExtra(extra, serializeToBytes(safeParcelable));
    }

    public static <T extends SafeParcelable> T deserializeFromIntentExtra(Intent intent, String extra, Creator<T> safeParcelableCreator) {
        byte[] extraBytes = intent.getByteArrayExtra(extra);
        if (extraBytes == null) {
            return null;
        }
        return deserializeFromBytes(extraBytes, safeParcelableCreator);
    }

    public static <T extends SafeParcelable> void serializeArrayToIntentExtra(T[] safeParcelables, Intent intent, String extra) {
        byte[][] safeParcelablesBytes = new byte[safeParcelables.length][];
        for (int i = 0; i < safeParcelables.length; i++) {
            safeParcelablesBytes[i] = serializeToBytes(safeParcelables[i]);
        }
        intent.putExtra(extra, safeParcelablesBytes);
    }

    public static <T extends SafeParcelable> T[] deserializeArrayFromIntentExtra(Intent intent, String extra, Creator<T> safeParcelableCreator) {
        byte[][] safeParcelablesBytes = (byte[][]) intent.getSerializableExtra(extra);
        if (safeParcelablesBytes == null) {
            return null;
        }
        SafeParcelable[] safeParcelables = (SafeParcelable[]) safeParcelableCreator.newArray(safeParcelablesBytes.length);
        for (int i = 0; i < safeParcelablesBytes.length; i++) {
            safeParcelables[i] = deserializeFromBytes(safeParcelablesBytes[i], safeParcelableCreator);
        }
        return safeParcelables;
    }

    public static <T extends SafeParcelable> void serializeIterableToIntentExtra(Iterable<T> safeParcelables, Intent intent, String extra) {
        ArrayList<byte[]> safeParcelablesBytes = new ArrayList();
        for (T safeParcelable : safeParcelables) {
            safeParcelablesBytes.add(serializeToBytes(safeParcelable));
        }
        intent.putExtra(extra, safeParcelablesBytes);
    }

    public static <T extends SafeParcelable> ArrayList<T> deserializeIterableFromIntentExtra(Intent intent, String extra, Creator<T> safeParcelableCreator) {
        ArrayList<byte[]> safeParcelablesBytes = (ArrayList) intent.getSerializableExtra(extra);
        if (safeParcelablesBytes == null) {
            return null;
        }
        ArrayList<T> safeParcelables = new ArrayList(safeParcelablesBytes.size());
        Iterator i$ = safeParcelablesBytes.iterator();
        while (i$.hasNext()) {
            safeParcelables.add(deserializeFromBytes((byte[]) i$.next(), safeParcelableCreator));
        }
        return safeParcelables;
    }
}
