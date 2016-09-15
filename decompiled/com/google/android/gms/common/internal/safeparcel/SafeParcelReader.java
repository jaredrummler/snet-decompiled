package com.google.android.gms.common.internal.safeparcel;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.v4.internal.view.SupportMenu;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class SafeParcelReader {

    public static class ParseException extends RuntimeException {
        public ParseException(String message, Parcel p) {
            super(message + " Parcel: pos=" + p.dataPosition() + " size=" + p.dataSize());
        }
    }

    private SafeParcelReader() {
    }

    public static int readHeader(Parcel p) {
        return p.readInt();
    }

    public static int getFieldId(int header) {
        return SupportMenu.USER_MASK & header;
    }

    public static int readSize(Parcel p, int header) {
        if ((header & SupportMenu.CATEGORY_MASK) != SupportMenu.CATEGORY_MASK) {
            return (header >> 16) & SupportMenu.USER_MASK;
        }
        return p.readInt();
    }

    public static void skipUnknownField(Parcel p, int header) {
        p.setDataPosition(p.dataPosition() + readSize(p, header));
    }

    private static void readAndEnforceSize(Parcel p, int header, int required) {
        int size = readSize(p, header);
        if (size != required) {
            throw new ParseException("Expected size " + required + " got " + size + " (0x" + Integer.toHexString(size) + ")", p);
        }
    }

    private static void enforceSize(Parcel p, int header, int size, int required) {
        if (size != required) {
            throw new ParseException("Expected size " + required + " got " + size + " (0x" + Integer.toHexString(size) + ")", p);
        }
    }

    public static int validateObjectHeader(Parcel p) {
        int header = readHeader(p);
        int size = readSize(p, header);
        int start = p.dataPosition();
        if (getFieldId(header) != 20293) {
            throw new ParseException("Expected object header. Got 0x" + Integer.toHexString(header), p);
        }
        int end = start + size;
        if (end >= start && end <= p.dataSize()) {
            return end;
        }
        throw new ParseException("Size read is invalid start=" + start + " end=" + end, p);
    }

    public static boolean readBoolean(Parcel p, int header) {
        readAndEnforceSize(p, header, 4);
        return p.readInt() != 0;
    }

    public static Boolean readBooleanObject(Parcel p, int header) {
        int size = readSize(p, header);
        if (size == 0) {
            return null;
        }
        enforceSize(p, header, size, 4);
        return Boolean.valueOf(p.readInt() != 0);
    }

    public static byte readByte(Parcel p, int header) {
        readAndEnforceSize(p, header, 4);
        return (byte) p.readInt();
    }

    public static char readChar(Parcel p, int header) {
        readAndEnforceSize(p, header, 4);
        return (char) p.readInt();
    }

    public static short readShort(Parcel p, int header) {
        readAndEnforceSize(p, header, 4);
        return (short) p.readInt();
    }

    public static int readInt(Parcel p, int header) {
        readAndEnforceSize(p, header, 4);
        return p.readInt();
    }

    public static Integer readIntegerObject(Parcel p, int header) {
        int size = readSize(p, header);
        if (size == 0) {
            return null;
        }
        enforceSize(p, header, size, 4);
        return Integer.valueOf(p.readInt());
    }

    public static long readLong(Parcel p, int header) {
        readAndEnforceSize(p, header, 8);
        return p.readLong();
    }

    public static Long readLongObject(Parcel p, int header) {
        int size = readSize(p, header);
        if (size == 0) {
            return null;
        }
        enforceSize(p, header, size, 8);
        return Long.valueOf(p.readLong());
    }

    public static BigInteger createBigInteger(Parcel p, int header) {
        int size = readSize(p, header);
        int pos = p.dataPosition();
        if (size == 0) {
            return null;
        }
        byte[] val = p.createByteArray();
        p.setDataPosition(pos + size);
        return new BigInteger(val);
    }

    public static float readFloat(Parcel p, int header) {
        readAndEnforceSize(p, header, 4);
        return p.readFloat();
    }

    public static Float readFloatObject(Parcel p, int header) {
        int size = readSize(p, header);
        if (size == 0) {
            return null;
        }
        enforceSize(p, header, size, 4);
        return Float.valueOf(p.readFloat());
    }

    public static double readDouble(Parcel p, int header) {
        readAndEnforceSize(p, header, 8);
        return p.readDouble();
    }

    public static Double readDoubleObject(Parcel p, int header) {
        int size = readSize(p, header);
        if (size == 0) {
            return null;
        }
        enforceSize(p, header, size, 8);
        return Double.valueOf(p.readDouble());
    }

    public static BigDecimal createBigDecimal(Parcel p, int header) {
        int size = readSize(p, header);
        int pos = p.dataPosition();
        if (size == 0) {
            return null;
        }
        byte[] unscaledValue = p.createByteArray();
        int scale = p.readInt();
        p.setDataPosition(pos + size);
        return new BigDecimal(new BigInteger(unscaledValue), scale);
    }

    public static String createString(Parcel p, int header) {
        int size = readSize(p, header);
        int pos = p.dataPosition();
        if (size == 0) {
            return null;
        }
        String result = p.readString();
        p.setDataPosition(pos + size);
        return result;
    }

    public static IBinder readIBinder(Parcel p, int header) {
        int size = readSize(p, header);
        int pos = p.dataPosition();
        if (size == 0) {
            return null;
        }
        IBinder result = p.readStrongBinder();
        p.setDataPosition(pos + size);
        return result;
    }

    public static <T extends Parcelable> T createParcelable(Parcel p, int header, Creator<T> creator) {
        int size = readSize(p, header);
        int pos = p.dataPosition();
        if (size == 0) {
            return null;
        }
        Parcelable result = (Parcelable) creator.createFromParcel(p);
        p.setDataPosition(pos + size);
        return result;
    }

    public static Bundle createBundle(Parcel p, int header) {
        int size = readSize(p, header);
        int pos = p.dataPosition();
        if (size == 0) {
            return null;
        }
        Bundle result = p.readBundle();
        p.setDataPosition(pos + size);
        return result;
    }

    public static byte[] createByteArray(Parcel p, int header) {
        int size = readSize(p, header);
        int pos = p.dataPosition();
        if (size == 0) {
            return null;
        }
        byte[] result = p.createByteArray();
        p.setDataPosition(pos + size);
        return result;
    }

    public static byte[][] createByteArrayArray(Parcel p, int header) {
        int size = readSize(p, header);
        int pos = p.dataPosition();
        if (size == 0) {
            return (byte[][]) null;
        }
        int length = p.readInt();
        byte[][] result = new byte[length][];
        for (int i = 0; i < length; i++) {
            result[i] = p.createByteArray();
        }
        p.setDataPosition(pos + size);
        return result;
    }

    public static boolean[] createBooleanArray(Parcel p, int header) {
        int size = readSize(p, header);
        int pos = p.dataPosition();
        if (size == 0) {
            return null;
        }
        boolean[] result = p.createBooleanArray();
        p.setDataPosition(pos + size);
        return result;
    }

    public static char[] createCharArray(Parcel p, int header) {
        int size = readSize(p, header);
        int pos = p.dataPosition();
        if (size == 0) {
            return null;
        }
        char[] result = p.createCharArray();
        p.setDataPosition(pos + size);
        return result;
    }

    public static int[] createIntArray(Parcel p, int header) {
        int size = readSize(p, header);
        int pos = p.dataPosition();
        if (size == 0) {
            return null;
        }
        int[] result = p.createIntArray();
        p.setDataPosition(pos + size);
        return result;
    }

    public static long[] createLongArray(Parcel p, int header) {
        int size = readSize(p, header);
        int pos = p.dataPosition();
        if (size == 0) {
            return null;
        }
        long[] result = p.createLongArray();
        p.setDataPosition(pos + size);
        return result;
    }

    public static BigInteger[] createBigIntegerArray(Parcel p, int header) {
        int size = readSize(p, header);
        int pos = p.dataPosition();
        if (size == 0) {
            return null;
        }
        int length = p.readInt();
        BigInteger[] result = new BigInteger[length];
        for (int i = 0; i < length; i++) {
            result[i] = new BigInteger(p.createByteArray());
        }
        p.setDataPosition(pos + size);
        return result;
    }

    public static float[] createFloatArray(Parcel p, int header) {
        int size = readSize(p, header);
        int pos = p.dataPosition();
        if (size == 0) {
            return null;
        }
        float[] result = p.createFloatArray();
        p.setDataPosition(pos + size);
        return result;
    }

    public static double[] createDoubleArray(Parcel p, int header) {
        int size = readSize(p, header);
        int pos = p.dataPosition();
        if (size == 0) {
            return null;
        }
        double[] result = p.createDoubleArray();
        p.setDataPosition(pos + size);
        return result;
    }

    public static BigDecimal[] createBigDecimalArray(Parcel p, int header) {
        int size = readSize(p, header);
        int pos = p.dataPosition();
        if (size == 0) {
            return null;
        }
        int length = p.readInt();
        BigDecimal[] result = new BigDecimal[length];
        for (int i = 0; i < length; i++) {
            byte[] unscaledValue = p.createByteArray();
            result[i] = new BigDecimal(new BigInteger(unscaledValue), p.readInt());
        }
        p.setDataPosition(pos + size);
        return result;
    }

    public static String[] createStringArray(Parcel p, int header) {
        int size = readSize(p, header);
        int pos = p.dataPosition();
        if (size == 0) {
            return null;
        }
        String[] result = p.createStringArray();
        p.setDataPosition(pos + size);
        return result;
    }

    public static IBinder[] createIBinderArray(Parcel p, int header) {
        int size = readSize(p, header);
        int pos = p.dataPosition();
        if (size == 0) {
            return null;
        }
        IBinder[] result = p.createBinderArray();
        p.setDataPosition(pos + size);
        return result;
    }

    public static ArrayList<Boolean> createBooleanList(Parcel p, int header) {
        int size = readSize(p, header);
        int pos = p.dataPosition();
        if (size == 0) {
            return null;
        }
        ArrayList<Boolean> result = new ArrayList();
        int count = p.readInt();
        for (int i = 0; i < count; i++) {
            result.add(Boolean.valueOf(p.readInt() != 0));
        }
        p.setDataPosition(pos + size);
        return result;
    }

    public static ArrayList<Integer> createIntegerList(Parcel p, int header) {
        int size = readSize(p, header);
        int pos = p.dataPosition();
        if (size == 0) {
            return null;
        }
        ArrayList<Integer> result = new ArrayList();
        int count = p.readInt();
        for (int i = 0; i < count; i++) {
            result.add(Integer.valueOf(p.readInt()));
        }
        p.setDataPosition(pos + size);
        return result;
    }

    public static ArrayList<Long> createLongList(Parcel p, int header) {
        int size = readSize(p, header);
        int pos = p.dataPosition();
        if (size == 0) {
            return null;
        }
        ArrayList<Long> result = new ArrayList();
        int count = p.readInt();
        for (int i = 0; i < count; i++) {
            result.add(Long.valueOf(p.readLong()));
        }
        p.setDataPosition(pos + size);
        return result;
    }

    public static ArrayList<Float> createFloatList(Parcel p, int header) {
        int size = readSize(p, header);
        int pos = p.dataPosition();
        if (size == 0) {
            return null;
        }
        ArrayList<Float> result = new ArrayList();
        int count = p.readInt();
        for (int i = 0; i < count; i++) {
            result.add(Float.valueOf(p.readFloat()));
        }
        p.setDataPosition(pos + size);
        return result;
    }

    public static ArrayList<Double> createDoubleList(Parcel p, int header) {
        int size = readSize(p, header);
        int pos = p.dataPosition();
        if (size == 0) {
            return null;
        }
        ArrayList<Double> result = new ArrayList();
        int count = p.readInt();
        for (int i = 0; i < count; i++) {
            result.add(Double.valueOf(p.readDouble()));
        }
        p.setDataPosition(pos + size);
        return result;
    }

    public static ArrayList<String> createStringList(Parcel p, int header) {
        int size = readSize(p, header);
        int pos = p.dataPosition();
        if (size == 0) {
            return null;
        }
        ArrayList<String> result = p.createStringArrayList();
        p.setDataPosition(pos + size);
        return result;
    }

    public static ArrayList<IBinder> createIBinderList(Parcel p, int header) {
        int size = readSize(p, header);
        int pos = p.dataPosition();
        if (size == 0) {
            return null;
        }
        ArrayList<IBinder> result = p.createBinderArrayList();
        p.setDataPosition(pos + size);
        return result;
    }

    public static <T> T[] createTypedArray(Parcel p, int header, Creator<T> c) {
        int size = readSize(p, header);
        int pos = p.dataPosition();
        if (size == 0) {
            return null;
        }
        T[] result = p.createTypedArray(c);
        p.setDataPosition(pos + size);
        return result;
    }

    public static <T> ArrayList<T> createTypedList(Parcel p, int header, Creator<T> c) {
        int size = readSize(p, header);
        int pos = p.dataPosition();
        if (size == 0) {
            return null;
        }
        ArrayList<T> result = p.createTypedArrayList(c);
        p.setDataPosition(pos + size);
        return result;
    }

    public static Parcel createParcel(Parcel p, int header) {
        int size = readSize(p, header);
        int pos = p.dataPosition();
        if (size == 0) {
            return null;
        }
        Parcel result = Parcel.obtain();
        result.appendFrom(p, pos, size);
        p.setDataPosition(pos + size);
        return result;
    }

    public static Parcel[] createParcelArray(Parcel p, int header) {
        int size = readSize(p, header);
        int pos = p.dataPosition();
        if (size == 0) {
            return null;
        }
        int length = p.readInt();
        Parcel[] result = new Parcel[length];
        for (int i = 0; i < length; i++) {
            int parcelSize = p.readInt();
            if (parcelSize != 0) {
                int currentDataPosition = p.dataPosition();
                Parcel item = Parcel.obtain();
                item.appendFrom(p, currentDataPosition, parcelSize);
                result[i] = item;
                p.setDataPosition(currentDataPosition + parcelSize);
            } else {
                result[i] = null;
            }
        }
        p.setDataPosition(pos + size);
        return result;
    }

    public static ArrayList<Parcel> createParcelList(Parcel p, int header) {
        int size = readSize(p, header);
        int pos = p.dataPosition();
        if (size == 0) {
            return null;
        }
        int length = p.readInt();
        ArrayList<Parcel> result = new ArrayList();
        for (int i = 0; i < length; i++) {
            int parcelSize = p.readInt();
            if (parcelSize != 0) {
                int currentDataPosition = p.dataPosition();
                Parcel item = Parcel.obtain();
                item.appendFrom(p, currentDataPosition, parcelSize);
                result.add(item);
                p.setDataPosition(currentDataPosition + parcelSize);
            } else {
                result.add(null);
            }
        }
        p.setDataPosition(pos + size);
        return result;
    }

    public static void readList(Parcel p, int header, List list, ClassLoader loader) {
        int size = readSize(p, header);
        int pos = p.dataPosition();
        if (size != 0) {
            p.readList(list, loader);
            p.setDataPosition(pos + size);
        }
    }
}
