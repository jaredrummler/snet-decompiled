package com.google.android.gms.common.internal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class Objects {

    public static final class ToStringHelper {
        private final List<String> mFieldStrings;
        private final Object mInstance;

        private ToStringHelper(Object instance) {
            this.mInstance = Preconditions.checkNotNull(instance);
            this.mFieldStrings = new ArrayList();
        }

        public ToStringHelper add(String name, Object value) {
            this.mFieldStrings.add(((String) Preconditions.checkNotNull(name)) + "=" + String.valueOf(value));
            return this;
        }

        public String toString() {
            StringBuilder builder = new StringBuilder(100).append(this.mInstance.getClass().getSimpleName()).append('{');
            int numFields = this.mFieldStrings.size();
            for (int i = 0; i < numFields; i++) {
                builder.append((String) this.mFieldStrings.get(i));
                if (i < numFields - 1) {
                    builder.append(", ");
                }
            }
            return builder.append('}').toString();
        }
    }

    public static boolean equal(Object a, Object b) {
        return a == b || (a != null && a.equals(b));
    }

    public static int hashCode(Object... objects) {
        return Arrays.hashCode(objects);
    }

    public static ToStringHelper toStringHelper(Object object) {
        return new ToStringHelper(null);
    }

    private Objects() {
        throw new AssertionError("Uninstantiable");
    }
}
