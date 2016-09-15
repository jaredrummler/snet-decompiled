package com.google.android.gms.common.util;

import android.os.ParcelFileDescriptor;
import android.support.v4.view.MotionEventCompat;
import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.people.PeopleConstants.PeopleColumnBitmask;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

public final class IOUtils {
    private static final int BUF_SIZE = 4096;

    private static final class FastByteArrayOutputStream extends ByteArrayOutputStream {
        private FastByteArrayOutputStream() {
        }

        void writeTo(byte[] b, int off) {
            System.arraycopy(this.buf, 0, b, off, this.count);
        }
    }

    private static final class FileByteSource {
        private final File file;

        private FileByteSource(File file) {
            this.file = (File) Preconditions.checkNotNull(file);
        }

        public long size() throws IOException {
            if (this.file.isFile()) {
                return this.file.length();
            }
            throw new FileNotFoundException(this.file.toString());
        }

        public byte[] read() throws IOException {
            Throwable th;
            Closeable in = null;
            try {
                Closeable in2 = new FileInputStream(this.file);
                try {
                    byte[] access$100 = IOUtils.readFile(in2, in2.getChannel().size());
                    IOUtils.closeQuietly(in2);
                    return access$100;
                } catch (Throwable th2) {
                    th = th2;
                    in = in2;
                    IOUtils.closeQuietly(in);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                IOUtils.closeQuietly(in);
                throw th;
            }
        }
    }

    private IOUtils() {
    }

    public static void closeQuietly(Closeable c) {
        if (c != null) {
            try {
                c.close();
            } catch (IOException e) {
            }
        }
    }

    public static void close(Closeable c, String tag, String message) {
        if (c != null) {
            try {
                c.close();
            } catch (IOException ex) {
                Log.d(tag, message, ex);
            }
        }
    }

    public static void closeQuietly(ParcelFileDescriptor p) {
        if (p != null) {
            try {
                p.close();
            } catch (IOException e) {
            }
        }
    }

    public static void closeQuietly(Socket s) {
        if (s != null) {
            try {
                s.close();
            } catch (IOException e) {
            }
        }
    }

    public static void closeQuietly(ServerSocket s) {
        if (s != null) {
            try {
                s.close();
            } catch (IOException e) {
            }
        }
    }

    public static boolean isGzipByteBuffer(byte[] inputBytes) {
        return inputBytes.length > 1 && ((inputBytes[0] & MotionEventCompat.ACTION_MASK) | ((inputBytes[1] & MotionEventCompat.ACTION_MASK) << 8)) == 35615;
    }

    public static long copyStream(InputStream inputStream, OutputStream outputStream) throws IOException {
        return copyStream(inputStream, outputStream, false);
    }

    public static long copyStream(InputStream inputStream, OutputStream outputStream, boolean closeWhenDone) throws IOException {
        return copyStream(inputStream, outputStream, closeWhenDone, PeopleColumnBitmask.IN_VIEWER_DOMAIN);
    }

    public static long copyStream(InputStream inputStream, OutputStream outputStream, boolean closeWhenDone, int bufferSizeBytes) throws IOException {
        byte[] buffer = new byte[bufferSizeBytes];
        long total = 0;
        while (true) {
            try {
                int n = inputStream.read(buffer, 0, buffer.length);
                if (n == -1) {
                    break;
                }
                total += (long) n;
                outputStream.write(buffer, 0, n);
            } finally {
                if (closeWhenDone) {
                    closeQuietly((Closeable) inputStream);
                    closeQuietly((Closeable) outputStream);
                }
            }
        }
        return total;
    }

    public static byte[] readInputStreamFully(InputStream is) throws IOException {
        return readInputStreamFully(is, true);
    }

    public static byte[] readInputStreamFully(InputStream is, boolean closeWhenDone) throws IOException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        copyStream(is, os, closeWhenDone);
        return os.toByteArray();
    }

    public static byte[] toByteArray(File file) throws IOException {
        return asByteSource(file).read();
    }

    private static FileByteSource asByteSource(File file) {
        return new FileByteSource(null);
    }

    private static byte[] readFile(InputStream in, long expectedSize) throws IOException {
        if (expectedSize <= 2147483647L) {
            return expectedSize == 0 ? toByteArray(in) : toByteArray(in, (int) expectedSize);
        } else {
            throw new OutOfMemoryError("file is too large to fit in a byte array: " + expectedSize + " bytes");
        }
    }

    private static byte[] toByteArray(InputStream in) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        copy(in, out);
        return out.toByteArray();
    }

    private static byte[] toByteArray(InputStream in, int expectedSize) throws IOException {
        byte[] bytes = new byte[expectedSize];
        int remaining = expectedSize;
        while (remaining > 0) {
            int off = expectedSize - remaining;
            int read = in.read(bytes, off, remaining);
            if (read == -1) {
                return Arrays.copyOf(bytes, off);
            }
            remaining -= read;
        }
        int b = in.read();
        if (b == -1) {
            return bytes;
        }
        FastByteArrayOutputStream out = new FastByteArrayOutputStream();
        out.write(b);
        copy(in, out);
        byte[] result = new byte[(bytes.length + out.size())];
        System.arraycopy(bytes, 0, result, 0, bytes.length);
        out.writeTo(result, bytes.length);
        return result;
    }

    private static long copy(InputStream from, OutputStream to) throws IOException {
        Preconditions.checkNotNull(from);
        Preconditions.checkNotNull(to);
        byte[] buf = new byte[BUF_SIZE];
        long total = 0;
        while (true) {
            int r = from.read(buf);
            if (r == -1) {
                return total;
            }
            to.write(buf, 0, r);
            total += (long) r;
        }
    }
}
