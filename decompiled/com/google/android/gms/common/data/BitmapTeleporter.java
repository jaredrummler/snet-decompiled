package com.google.android.gms.common.data;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.ParcelFileDescriptor.AutoCloseInputStream;
import android.os.Parcelable.Creator;
import android.util.Log;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;
import java.io.Closeable;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

@Class(creator = "BitmapTeleporterCreator")
public class BitmapTeleporter implements SafeParcelable {
    public static final Creator<BitmapTeleporter> CREATOR;
    private static final String TAG = "BitmapTeleporter";
    private static final int VERSION_CODE = 1;
    @Field(id = 2)
    ParcelFileDescriptor mFileDescriptor;
    private Bitmap mTeleportee;
    private File mTempDir;
    @Field(id = 3)
    final int mType;
    private boolean mUnwrapped;
    @VersionField(id = 1)
    final int mVersionCode;

    static {
        CREATOR = new BitmapTeleporterCreator();
    }

    @Constructor
    BitmapTeleporter(@Param(id = 1) int versionCode, @Param(id = 2) ParcelFileDescriptor parcelFileDescriptor, @Param(id = 3) int type) {
        this.mVersionCode = versionCode;
        this.mFileDescriptor = parcelFileDescriptor;
        this.mType = type;
        this.mTeleportee = null;
        this.mUnwrapped = false;
    }

    public BitmapTeleporter(Bitmap teleportee) {
        this.mVersionCode = VERSION_CODE;
        this.mFileDescriptor = null;
        this.mType = 0;
        this.mTeleportee = teleportee;
        this.mUnwrapped = true;
    }

    public Bitmap get() {
        if (!this.mUnwrapped) {
            DataInputStream inputStream = new DataInputStream(new AutoCloseInputStream(this.mFileDescriptor));
            try {
                byte[] bytes = new byte[inputStream.readInt()];
                int width = inputStream.readInt();
                int height = inputStream.readInt();
                Config config = Config.valueOf(inputStream.readUTF());
                inputStream.read(bytes);
                safeClose(inputStream);
                ByteBuffer buffer = ByteBuffer.wrap(bytes);
                Bitmap bitmap = Bitmap.createBitmap(width, height, config);
                bitmap.copyPixelsFromBuffer(buffer);
                this.mTeleportee = bitmap;
                this.mUnwrapped = true;
            } catch (IOException e) {
                throw new IllegalStateException("Could not read from parcel file descriptor", e);
            } catch (Throwable th) {
                safeClose(inputStream);
            }
        }
        return this.mTeleportee;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        if (this.mFileDescriptor == null) {
            Bitmap bitmap = this.mTeleportee;
            ByteBuffer buffer = ByteBuffer.allocate(bitmap.getRowBytes() * bitmap.getHeight());
            bitmap.copyPixelsToBuffer(buffer);
            byte[] bytes = buffer.array();
            DataOutputStream dataOutputStream = new DataOutputStream(getUnlinkedFileOutputStream());
            try {
                dataOutputStream.writeInt(bytes.length);
                dataOutputStream.writeInt(bitmap.getWidth());
                dataOutputStream.writeInt(bitmap.getHeight());
                dataOutputStream.writeUTF(bitmap.getConfig().toString());
                dataOutputStream.write(bytes);
                safeClose(dataOutputStream);
            } catch (IOException e) {
                throw new IllegalStateException("Could not write into unlinked file", e);
            } catch (Throwable th) {
                safeClose(dataOutputStream);
            }
        }
        BitmapTeleporterCreator.writeToParcel(this, dest, flags | VERSION_CODE);
        this.mFileDescriptor = null;
    }

    public void release() {
        if (!this.mUnwrapped) {
            try {
                this.mFileDescriptor.close();
            } catch (IOException e) {
                Log.w(TAG, "Could not close PFD", e);
            }
        }
    }

    public void setTempDir(File tempDir) {
        if (tempDir == null) {
            throw new NullPointerException("Cannot set null temp directory");
        }
        this.mTempDir = tempDir;
    }

    private FileOutputStream getUnlinkedFileOutputStream() {
        if (this.mTempDir == null) {
            throw new IllegalStateException("setTempDir() must be called before writing this object to a parcel");
        }
        try {
            File tempFile = File.createTempFile("teleporter", ".tmp", this.mTempDir);
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(tempFile);
                this.mFileDescriptor = ParcelFileDescriptor.open(tempFile, 268435456);
                tempFile.delete();
                return fileOutputStream;
            } catch (FileNotFoundException e) {
                throw new IllegalStateException("Temporary file is somehow already deleted");
            }
        } catch (IOException ioe) {
            throw new IllegalStateException("Could not create temporary file", ioe);
        }
    }

    private void safeClose(Closeable closeable) {
        try {
            closeable.close();
        } catch (IOException e) {
            Log.w(TAG, "Could not close stream", e);
        }
    }
}
