package com.google.protobuf.nano.android;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.util.Log;
import com.google.protobuf.nano.InvalidProtocolBufferNanoException;
import com.google.protobuf.nano.MessageNano;
import java.lang.reflect.Array;

public final class ParcelableMessageNanoCreator<T extends MessageNano> implements Creator<T> {
    private static final String TAG = "PMNCreator";
    private final Class<T> mClazz;

    public ParcelableMessageNanoCreator(Class<T> clazz) {
        this.mClazz = clazz;
    }

    public T createFromParcel(Parcel in) {
        String className = in.readString();
        byte[] data = in.createByteArray();
        T proto = null;
        try {
            proto = (MessageNano) Class.forName(className).newInstance();
            MessageNano.mergeFrom(proto, data);
            return proto;
        } catch (ClassNotFoundException e) {
            Log.e(TAG, "Exception trying to create proto from parcel", e);
            return proto;
        } catch (IllegalAccessException e2) {
            Log.e(TAG, "Exception trying to create proto from parcel", e2);
            return proto;
        } catch (InstantiationException e3) {
            Log.e(TAG, "Exception trying to create proto from parcel", e3);
            return proto;
        } catch (InvalidProtocolBufferNanoException e4) {
            Log.e(TAG, "Exception trying to create proto from parcel", e4);
            return proto;
        }
    }

    public T[] newArray(int i) {
        return (MessageNano[]) Array.newInstance(this.mClazz, i);
    }

    static <T extends MessageNano> void writeToParcel(Class<T> clazz, MessageNano message, Parcel out) {
        out.writeString(clazz.getName());
        out.writeByteArray(MessageNano.toByteArray(message));
    }
}
