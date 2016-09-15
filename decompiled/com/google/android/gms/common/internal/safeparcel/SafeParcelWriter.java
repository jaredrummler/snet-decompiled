package com.google.android.gms.common.internal.safeparcel;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.internal.view.SupportMenu;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

public class SafeParcelWriter {
    static final int OBJECT_HEADER = 20293;

    private SafeParcelWriter() {
    }

    private static void writeHeader(Parcel p, int id, int size) {
        if (size >= SupportMenu.USER_MASK) {
            p.writeInt(SupportMenu.CATEGORY_MASK | id);
            p.writeInt(size);
            return;
        }
        p.writeInt((size << 16) | id);
    }

    private static int beginVariableData(Parcel p, int id) {
        p.writeInt(SupportMenu.CATEGORY_MASK | id);
        p.writeInt(0);
        return p.dataPosition();
    }

    private static void finishVariableData(Parcel p, int start) {
        int end = p.dataPosition();
        int size = end - start;
        p.setDataPosition(start - 4);
        p.writeInt(size);
        p.setDataPosition(end);
    }

    public static int beginObjectHeader(Parcel p) {
        return beginVariableData(p, OBJECT_HEADER);
    }

    public static void finishObjectHeader(Parcel p, int start) {
        finishVariableData(p, start);
    }

    public static void writeBoolean(Parcel p, int id, boolean val) {
        writeHeader(p, id, 4);
        p.writeInt(val ? 1 : 0);
    }

    public static void writeBooleanObject(Parcel p, int id, Boolean val, boolean writeNull) {
        int i = 0;
        if (val != null) {
            writeHeader(p, id, 4);
            if (val.booleanValue()) {
                i = 1;
            }
            p.writeInt(i);
        } else if (writeNull) {
            writeHeader(p, id, 0);
        }
    }

    public static void writeByte(Parcel p, int id, byte val) {
        writeHeader(p, id, 4);
        p.writeInt(val);
    }

    public static void writeChar(Parcel p, int id, char val) {
        writeHeader(p, id, 4);
        p.writeInt(val);
    }

    public static void writeShort(Parcel p, int id, short val) {
        writeHeader(p, id, 4);
        p.writeInt(val);
    }

    public static void writeInt(Parcel p, int id, int val) {
        writeHeader(p, id, 4);
        p.writeInt(val);
    }

    public static void writeIntegerObject(Parcel p, int id, Integer val, boolean writeNull) {
        if (val != null) {
            writeHeader(p, id, 4);
            p.writeInt(val.intValue());
        } else if (writeNull) {
            writeHeader(p, id, 0);
        }
    }

    public static void writeLong(Parcel p, int id, long val) {
        writeHeader(p, id, 8);
        p.writeLong(val);
    }

    public static void writeLongObject(Parcel p, int id, Long val, boolean writeNull) {
        if (val != null) {
            writeHeader(p, id, 8);
            p.writeLong(val.longValue());
        } else if (writeNull) {
            writeHeader(p, id, 0);
        }
    }

    public static void writeBigInteger(Parcel p, int id, BigInteger val, boolean writeNull) {
        if (val != null) {
            int start = beginVariableData(p, id);
            p.writeByteArray(val.toByteArray());
            finishVariableData(p, start);
        } else if (writeNull) {
            writeHeader(p, id, 0);
        }
    }

    public static void writeFloat(Parcel p, int id, float val) {
        writeHeader(p, id, 4);
        p.writeFloat(val);
    }

    public static void writeFloatObject(Parcel p, int id, Float val, boolean writeNull) {
        if (val != null) {
            writeHeader(p, id, 4);
            p.writeFloat(val.floatValue());
        } else if (writeNull) {
            writeHeader(p, id, 0);
        }
    }

    public static void writeDouble(Parcel p, int id, double val) {
        writeHeader(p, id, 8);
        p.writeDouble(val);
    }

    public static void writeDoubleObject(Parcel p, int id, Double val, boolean writeNull) {
        if (val != null) {
            writeHeader(p, id, 8);
            p.writeDouble(val.doubleValue());
        } else if (writeNull) {
            writeHeader(p, id, 0);
        }
    }

    public static void writeBigDecimal(Parcel p, int id, BigDecimal val, boolean writeNull) {
        if (val != null) {
            int start = beginVariableData(p, id);
            p.writeByteArray(val.unscaledValue().toByteArray());
            p.writeInt(val.scale());
            finishVariableData(p, start);
        } else if (writeNull) {
            writeHeader(p, id, 0);
        }
    }

    public static void writeString(Parcel p, int id, String val, boolean writeNull) {
        if (val != null) {
            int start = beginVariableData(p, id);
            p.writeString(val);
            finishVariableData(p, start);
        } else if (writeNull) {
            writeHeader(p, id, 0);
        }
    }

    public static void writeIBinder(Parcel p, int id, IBinder val, boolean writeNull) {
        if (val != null) {
            int start = beginVariableData(p, id);
            p.writeStrongBinder(val);
            finishVariableData(p, start);
        } else if (writeNull) {
            writeHeader(p, id, 0);
        }
    }

    public static void writeParcelable(Parcel p, int id, Parcelable val, int parcelableFlags, boolean writeNull) {
        if (val != null) {
            int start = beginVariableData(p, id);
            val.writeToParcel(p, parcelableFlags);
            finishVariableData(p, start);
        } else if (writeNull) {
            writeHeader(p, id, 0);
        }
    }

    public static void writeBundle(Parcel p, int id, Bundle val, boolean writeNull) {
        if (val != null) {
            int start = beginVariableData(p, id);
            p.writeBundle(val);
            finishVariableData(p, start);
        } else if (writeNull) {
            writeHeader(p, id, 0);
        }
    }

    public static void writeByteArray(Parcel p, int id, byte[] buf, boolean writeNull) {
        if (buf != null) {
            int start = beginVariableData(p, id);
            p.writeByteArray(buf);
            finishVariableData(p, start);
        } else if (writeNull) {
            writeHeader(p, id, 0);
        }
    }

    public static void writeByteArrayArray(Parcel p, int id, byte[][] buf, boolean writeNull) {
        if (buf != null) {
            int start = beginVariableData(p, id);
            p.writeInt(length);
            for (byte[] writeByteArray : buf) {
                p.writeByteArray(writeByteArray);
            }
            finishVariableData(p, start);
        } else if (writeNull) {
            writeHeader(p, id, 0);
        }
    }

    public static void writeBooleanArray(Parcel p, int id, boolean[] val, boolean writeNull) {
        if (val != null) {
            int start = beginVariableData(p, id);
            p.writeBooleanArray(val);
            finishVariableData(p, start);
        } else if (writeNull) {
            writeHeader(p, id, 0);
        }
    }

    public static void writeCharArray(Parcel p, int id, char[] val, boolean writeNull) {
        if (val != null) {
            int start = beginVariableData(p, id);
            p.writeCharArray(val);
            finishVariableData(p, start);
        } else if (writeNull) {
            writeHeader(p, id, 0);
        }
    }

    public static void writeIntArray(Parcel p, int id, int[] val, boolean writeNull) {
        if (val != null) {
            int start = beginVariableData(p, id);
            p.writeIntArray(val);
            finishVariableData(p, start);
        } else if (writeNull) {
            writeHeader(p, id, 0);
        }
    }

    public static void writeLongArray(Parcel p, int id, long[] val, boolean writeNull) {
        if (val != null) {
            int start = beginVariableData(p, id);
            p.writeLongArray(val);
            finishVariableData(p, start);
        } else if (writeNull) {
            writeHeader(p, id, 0);
        }
    }

    public static void writeBigIntegerArray(Parcel p, int id, BigInteger[] val, boolean writeNull) {
        if (val != null) {
            int start = beginVariableData(p, id);
            p.writeInt(length);
            for (BigInteger toByteArray : val) {
                p.writeByteArray(toByteArray.toByteArray());
            }
            finishVariableData(p, start);
        } else if (writeNull) {
            writeHeader(p, id, 0);
        }
    }

    public static void writeFloatArray(Parcel p, int id, float[] val, boolean writeNull) {
        if (val != null) {
            int start = beginVariableData(p, id);
            p.writeFloatArray(val);
            finishVariableData(p, start);
        } else if (writeNull) {
            writeHeader(p, id, 0);
        }
    }

    public static void writeDoubleArray(Parcel p, int id, double[] val, boolean writeNull) {
        if (val != null) {
            int start = beginVariableData(p, id);
            p.writeDoubleArray(val);
            finishVariableData(p, start);
        } else if (writeNull) {
            writeHeader(p, id, 0);
        }
    }

    public static void writeBigDecimalArray(Parcel p, int id, BigDecimal[] val, boolean writeNull) {
        if (val != null) {
            int start = beginVariableData(p, id);
            int length = val.length;
            p.writeInt(length);
            for (int i = 0; i < length; i++) {
                p.writeByteArray(val[i].unscaledValue().toByteArray());
                p.writeInt(val[i].scale());
            }
            finishVariableData(p, start);
        } else if (writeNull) {
            writeHeader(p, id, 0);
        }
    }

    public static void writeStringArray(Parcel p, int id, String[] val, boolean writeNull) {
        if (val != null) {
            int start = beginVariableData(p, id);
            p.writeStringArray(val);
            finishVariableData(p, start);
        } else if (writeNull) {
            writeHeader(p, id, 0);
        }
    }

    public static void writeIBinderArray(Parcel p, int id, IBinder[] val, boolean writeNull) {
        if (val != null) {
            int start = beginVariableData(p, id);
            p.writeBinderArray(val);
            finishVariableData(p, start);
        } else if (writeNull) {
            writeHeader(p, id, 0);
        }
    }

    public static void writeBooleanList(Parcel p, int id, List<Boolean> val, boolean writeNull) {
        if (val != null) {
            int start = beginVariableData(p, id);
            int size = val.size();
            p.writeInt(size);
            for (int i = 0; i < size; i++) {
                int i2;
                if (((Boolean) val.get(i)).booleanValue()) {
                    i2 = 1;
                } else {
                    i2 = 0;
                }
                p.writeInt(i2);
            }
            finishVariableData(p, start);
        } else if (writeNull) {
            writeHeader(p, id, 0);
        }
    }

    public static void writeIntegerList(Parcel p, int id, List<Integer> val, boolean writeNull) {
        if (val != null) {
            int start = beginVariableData(p, id);
            int size = val.size();
            p.writeInt(size);
            for (int i = 0; i < size; i++) {
                p.writeInt(((Integer) val.get(i)).intValue());
            }
            finishVariableData(p, start);
        } else if (writeNull) {
            writeHeader(p, id, 0);
        }
    }

    public static void writeLongList(Parcel p, int id, List<Long> val, boolean writeNull) {
        if (val != null) {
            int start = beginVariableData(p, id);
            int size = val.size();
            p.writeInt(size);
            for (int i = 0; i < size; i++) {
                p.writeLong(((Long) val.get(i)).longValue());
            }
            finishVariableData(p, start);
        } else if (writeNull) {
            writeHeader(p, id, 0);
        }
    }

    public static void writeFloatList(Parcel p, int id, List<Float> val, boolean writeNull) {
        if (val != null) {
            int start = beginVariableData(p, id);
            int size = val.size();
            p.writeInt(size);
            for (int i = 0; i < size; i++) {
                p.writeFloat(((Float) val.get(i)).floatValue());
            }
            finishVariableData(p, start);
        } else if (writeNull) {
            writeHeader(p, id, 0);
        }
    }

    public static void writeDoubleList(Parcel p, int id, List<Double> val, boolean writeNull) {
        if (val != null) {
            int start = beginVariableData(p, id);
            int size = val.size();
            p.writeInt(size);
            for (int i = 0; i < size; i++) {
                p.writeDouble(((Double) val.get(i)).doubleValue());
            }
            finishVariableData(p, start);
        } else if (writeNull) {
            writeHeader(p, id, 0);
        }
    }

    public static void writeStringList(Parcel p, int id, List<String> val, boolean writeNull) {
        if (val != null) {
            int start = beginVariableData(p, id);
            p.writeStringList(val);
            finishVariableData(p, start);
        } else if (writeNull) {
            writeHeader(p, id, 0);
        }
    }

    public static void writeIBinderList(Parcel p, int id, List<IBinder> val, boolean writeNull) {
        if (val != null) {
            int start = beginVariableData(p, id);
            p.writeBinderList(val);
            finishVariableData(p, start);
        } else if (writeNull) {
            writeHeader(p, id, 0);
        }
    }

    public static <T extends Parcelable> void writeTypedArray(Parcel p, int id, T[] val, int parcelableFlags, boolean writeNull) {
        if (val != null) {
            int start = beginVariableData(p, id);
            p.writeInt(length);
            for (T item : val) {
                if (item == null) {
                    p.writeInt(0);
                } else {
                    writeTypedItemWithSize(p, item, parcelableFlags);
                }
            }
            finishVariableData(p, start);
        } else if (writeNull) {
            writeHeader(p, id, 0);
        }
    }

    public static <T extends Parcelable> void writeTypedList(Parcel p, int id, List<T> val, boolean writeNull) {
        if (val != null) {
            int start = beginVariableData(p, id);
            int length = val.size();
            p.writeInt(length);
            for (int i = 0; i < length; i++) {
                Parcelable item = (Parcelable) val.get(i);
                if (item == null) {
                    p.writeInt(0);
                } else {
                    writeTypedItemWithSize(p, item, 0);
                }
            }
            finishVariableData(p, start);
        } else if (writeNull) {
            writeHeader(p, id, 0);
        }
    }

    private static <T extends Parcelable> void writeTypedItemWithSize(Parcel p, T item, int parcelableFlags) {
        int itemSizeDataPosition = p.dataPosition();
        p.writeInt(1);
        int itemStartPosition = p.dataPosition();
        item.writeToParcel(p, parcelableFlags);
        int currentDataPosition = p.dataPosition();
        p.setDataPosition(itemSizeDataPosition);
        p.writeInt(currentDataPosition - itemStartPosition);
        p.setDataPosition(currentDataPosition);
    }

    public static void writeParcel(Parcel p, int id, Parcel val, boolean writeNull) {
        if (val != null) {
            int start = beginVariableData(p, id);
            p.appendFrom(val, 0, val.dataSize());
            finishVariableData(p, start);
        } else if (writeNull) {
            writeHeader(p, id, 0);
        }
    }

    public static void writeParcelArray(Parcel p, int id, Parcel[] val, boolean writeNull) {
        if (val != null) {
            int start = beginVariableData(p, id);
            p.writeInt(length);
            for (Parcel item : val) {
                if (item != null) {
                    p.writeInt(item.dataSize());
                    p.appendFrom(item, 0, item.dataSize());
                } else {
                    p.writeInt(0);
                }
            }
            finishVariableData(p, start);
        } else if (writeNull) {
            writeHeader(p, id, 0);
        }
    }

    public static void writeParcelList(Parcel p, int id, List<Parcel> val, boolean writeNull) {
        if (val != null) {
            int start = beginVariableData(p, id);
            int size = val.size();
            p.writeInt(size);
            for (int i = 0; i < size; i++) {
                Parcel item = (Parcel) val.get(i);
                if (item != null) {
                    p.writeInt(item.dataSize());
                    p.appendFrom(item, 0, item.dataSize());
                } else {
                    p.writeInt(0);
                }
            }
            finishVariableData(p, start);
        } else if (writeNull) {
            writeHeader(p, id, 0);
        }
    }

    public static void writeList(Parcel p, int id, List list, boolean writeNull) {
        if (list != null) {
            int start = beginVariableData(p, id);
            p.writeList(list);
            finishVariableData(p, start);
        } else if (writeNull) {
            writeHeader(p, id, 0);
        }
    }
}
