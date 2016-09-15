package com.google.android.gms.common.internal;

import java.util.Iterator;

public class Joiner {
    private final String separator;

    public static Joiner on(String separator) {
        return new Joiner(separator);
    }

    private Joiner(String separator) {
        this.separator = separator;
    }

    public final StringBuilder appendTo(StringBuilder builder, Iterable<?> parts) {
        Iterator<?> iterator = parts.iterator();
        if (iterator.hasNext()) {
            builder.append(toString(iterator.next()));
            while (iterator.hasNext()) {
                builder.append(this.separator);
                builder.append(toString(iterator.next()));
            }
        }
        return builder;
    }

    public final String join(Iterable<?> parts) {
        return appendTo(new StringBuilder(), parts).toString();
    }

    CharSequence toString(Object part) {
        return part instanceof CharSequence ? (CharSequence) part : part.toString();
    }
}
