package com.google.android.gms.common.internal;

import com.google.android.gms.lint.BuildConfig;
import com.google.android.gms.people.PeopleConstants.PeopleColumnBitmask;
import com.google.wireless.android.play.playlog.proto.UserLocation.LaunchSettingsEvent.Action;
import com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class CharMatcher implements Predicate<Character> {
    public static final CharMatcher ANY;
    public static final CharMatcher ASCII;
    public static final CharMatcher BREAKING_WHITESPACE;
    private static final String BREAKING_WHITESPACE_CHARS = "\t\n\u000b\f\r \u0085\u1680\u2028\u2029\u205f\u3000";
    public static final CharMatcher DIGIT;
    public static final CharMatcher INVISIBLE;
    public static final CharMatcher JAVA_DIGIT;
    public static final CharMatcher JAVA_ISO_CONTROL;
    public static final CharMatcher JAVA_LETTER;
    public static final CharMatcher JAVA_LETTER_OR_DIGIT;
    public static final CharMatcher JAVA_LOWER_CASE;
    public static final CharMatcher JAVA_UPPER_CASE;
    public static final CharMatcher JAVA_WHITESPACE;
    public static final CharMatcher NONE;
    private static final String NON_BREAKING_WHITESPACE_CHARS = "\u00a0\u180e\u202f";
    public static final CharMatcher SINGLE_WIDTH;
    public static final CharMatcher WHITESPACE;

    /* renamed from: com.google.android.gms.common.internal.CharMatcher.10 */
    static class AnonymousClass10 extends CharMatcher {
        final /* synthetic */ char val$match1;
        final /* synthetic */ char val$match2;

        AnonymousClass10(char c, char c2) {
            this.val$match1 = c;
            this.val$match2 = c2;
        }

        public /* bridge */ /* synthetic */ boolean apply(Object obj) {
            return super.apply((Character) obj);
        }

        public boolean matches(char c) {
            return c == this.val$match1 || c == this.val$match2;
        }

        protected void setBits(LookupTable table) {
            table.set(this.val$match1);
            table.set(this.val$match2);
        }

        public CharMatcher precomputed() {
            return this;
        }
    }

    /* renamed from: com.google.android.gms.common.internal.CharMatcher.11 */
    static class AnonymousClass11 extends CharMatcher {
        final /* synthetic */ char[] val$chars;

        AnonymousClass11(char[] cArr) {
            this.val$chars = cArr;
        }

        public /* bridge */ /* synthetic */ boolean apply(Object obj) {
            return super.apply((Character) obj);
        }

        public boolean matches(char c) {
            return Arrays.binarySearch(this.val$chars, c) >= 0;
        }

        protected void setBits(LookupTable table) {
            for (char c : this.val$chars) {
                table.set(c);
            }
        }
    }

    /* renamed from: com.google.android.gms.common.internal.CharMatcher.12 */
    static class AnonymousClass12 extends CharMatcher {
        final /* synthetic */ char val$endInclusive;
        final /* synthetic */ char val$startInclusive;

        AnonymousClass12(char c, char c2) {
            this.val$startInclusive = c;
            this.val$endInclusive = c2;
        }

        public /* bridge */ /* synthetic */ boolean apply(Object obj) {
            return super.apply((Character) obj);
        }

        public boolean matches(char c) {
            return this.val$startInclusive <= c && c <= this.val$endInclusive;
        }

        protected void setBits(LookupTable table) {
            char c = this.val$startInclusive;
            while (true) {
                table.set(c);
                char c2 = (char) (c + 1);
                if (c != this.val$endInclusive) {
                    c = c2;
                } else {
                    return;
                }
            }
        }

        public CharMatcher precomputed() {
            return this;
        }
    }

    /* renamed from: com.google.android.gms.common.internal.CharMatcher.13 */
    static class AnonymousClass13 extends CharMatcher {
        final /* synthetic */ Predicate val$predicate;

        AnonymousClass13(Predicate predicate) {
            this.val$predicate = predicate;
        }

        public boolean matches(char c) {
            return this.val$predicate.apply(Character.valueOf(c));
        }

        public boolean apply(Character character) {
            return this.val$predicate.apply(Preconditions.checkNotNull(character));
        }
    }

    /* renamed from: com.google.android.gms.common.internal.CharMatcher.15 */
    class AnonymousClass15 extends CharMatcher {
        final /* synthetic */ LookupTable val$table;

        AnonymousClass15(LookupTable lookupTable) {
            this.val$table = lookupTable;
        }

        public /* bridge */ /* synthetic */ boolean apply(Object obj) {
            return super.apply((Character) obj);
        }

        public boolean matches(char c) {
            return this.val$table.get(c);
        }

        public CharMatcher precomputed() {
            return this;
        }
    }

    /* renamed from: com.google.android.gms.common.internal.CharMatcher.8 */
    static class AnonymousClass8 extends CharMatcher {
        final /* synthetic */ char val$match;

        AnonymousClass8(char c) {
            this.val$match = c;
        }

        public /* bridge */ /* synthetic */ boolean apply(Object obj) {
            return super.apply((Character) obj);
        }

        public boolean matches(char c) {
            return c == this.val$match;
        }

        public String replaceFrom(CharSequence sequence, char replacement) {
            return sequence.toString().replace(this.val$match, replacement);
        }

        public CharMatcher and(CharMatcher other) {
            return other.matches(this.val$match) ? this : NONE;
        }

        public CharMatcher or(CharMatcher other) {
            return other.matches(this.val$match) ? other : super.or(other);
        }

        public CharMatcher negate() {
            return CharMatcher.isNot(this.val$match);
        }

        protected void setBits(LookupTable table) {
            table.set(this.val$match);
        }

        public CharMatcher precomputed() {
            return this;
        }
    }

    /* renamed from: com.google.android.gms.common.internal.CharMatcher.9 */
    static class AnonymousClass9 extends CharMatcher {
        final /* synthetic */ char val$match;

        AnonymousClass9(char c) {
            this.val$match = c;
        }

        public /* bridge */ /* synthetic */ boolean apply(Object obj) {
            return super.apply((Character) obj);
        }

        public boolean matches(char c) {
            return c != this.val$match;
        }

        public CharMatcher and(CharMatcher other) {
            return other.matches(this.val$match) ? super.and(other) : other;
        }

        public CharMatcher or(CharMatcher other) {
            return other.matches(this.val$match) ? ANY : this;
        }

        public CharMatcher negate() {
            return CharMatcher.is(this.val$match);
        }
    }

    private static class And extends CharMatcher {
        List<CharMatcher> components;

        public /* bridge */ /* synthetic */ boolean apply(Object obj) {
            return super.apply((Character) obj);
        }

        And(List<CharMatcher> components) {
            this.components = components;
        }

        public boolean matches(char c) {
            for (CharMatcher matcher : this.components) {
                if (!matcher.matches(c)) {
                    return false;
                }
            }
            return true;
        }

        public CharMatcher and(CharMatcher other) {
            List<CharMatcher> newComponents = new ArrayList(this.components);
            newComponents.add(Preconditions.checkNotNull(other));
            return new And(newComponents);
        }
    }

    protected static class LookupTable {
        int[] data;

        protected LookupTable() {
            this.data = new int[PeopleColumnBitmask.NAME_VERIFIED];
        }

        void set(char index) {
            int[] iArr = this.data;
            int i = index >> 5;
            iArr[i] = iArr[i] | (1 << index);
        }

        boolean get(char index) {
            return (this.data[index >> 5] & (1 << index)) != 0;
        }
    }

    private static class Or extends CharMatcher {
        List<CharMatcher> components;

        public /* bridge */ /* synthetic */ boolean apply(Object obj) {
            return super.apply((Character) obj);
        }

        Or(List<CharMatcher> components) {
            this.components = components;
        }

        public boolean matches(char c) {
            for (CharMatcher matcher : this.components) {
                if (matcher.matches(c)) {
                    return true;
                }
            }
            return false;
        }

        public CharMatcher or(CharMatcher other) {
            List<CharMatcher> newComponents = new ArrayList(this.components);
            newComponents.add(Preconditions.checkNotNull(other));
            return new Or(newComponents);
        }

        protected void setBits(LookupTable table) {
            for (CharMatcher matcher : this.components) {
                matcher.setBits(table);
            }
        }
    }

    public abstract boolean matches(char c);

    static {
        WHITESPACE = anyOf("\t\n\u000b\f\r \u0085\u1680\u2028\u2029\u205f\u3000\u00a0\u180e\u202f").or(inRange('\u2000', '\u200a'));
        BREAKING_WHITESPACE = anyOf(BREAKING_WHITESPACE_CHARS).or(inRange('\u2000', '\u2006')).or(inRange('\u2008', '\u200a'));
        ASCII = inRange('\u0000', '\u007f');
        CharMatcher digit = inRange('0', '9');
        for (char base : "\u0660\u06f0\u07c0\u0966\u09e6\u0a66\u0ae6\u0b66\u0be6\u0c66\u0ce6\u0d66\u0e50\u0ed0\u0f20\u1040\u1090\u17e0\u1810\u1946\u19d0\u1b50\u1bb0\u1c40\u1c50\ua620\ua8d0\ua900\uaa50\uff10".toCharArray()) {
            digit = digit.or(inRange(base, (char) (base + 9)));
        }
        DIGIT = digit;
        JAVA_WHITESPACE = inRange('\t', '\r').or(inRange('\u001c', ' ')).or(is('\u1680')).or(is('\u180e')).or(inRange('\u2000', '\u2006')).or(inRange('\u2008', '\u200b')).or(inRange('\u2028', '\u2029')).or(is('\u205f')).or(is('\u3000'));
        JAVA_DIGIT = new CharMatcher() {
            public /* bridge */ /* synthetic */ boolean apply(Object obj) {
                return super.apply((Character) obj);
            }

            public boolean matches(char c) {
                return Character.isDigit(c);
            }
        };
        JAVA_LETTER = new CharMatcher() {
            public /* bridge */ /* synthetic */ boolean apply(Object obj) {
                return super.apply((Character) obj);
            }

            public boolean matches(char c) {
                return Character.isLetter(c);
            }
        };
        JAVA_LETTER_OR_DIGIT = new CharMatcher() {
            public /* bridge */ /* synthetic */ boolean apply(Object obj) {
                return super.apply((Character) obj);
            }

            public boolean matches(char c) {
                return Character.isLetterOrDigit(c);
            }
        };
        JAVA_UPPER_CASE = new CharMatcher() {
            public /* bridge */ /* synthetic */ boolean apply(Object obj) {
                return super.apply((Character) obj);
            }

            public boolean matches(char c) {
                return Character.isUpperCase(c);
            }
        };
        JAVA_LOWER_CASE = new CharMatcher() {
            public /* bridge */ /* synthetic */ boolean apply(Object obj) {
                return super.apply((Character) obj);
            }

            public boolean matches(char c) {
                return Character.isLowerCase(c);
            }
        };
        JAVA_ISO_CONTROL = inRange('\u0000', '\u001f').or(inRange('\u007f', '\u009f'));
        INVISIBLE = inRange('\u0000', ' ').or(inRange('\u007f', '\u00a0')).or(is('\u00ad')).or(inRange('\u0600', '\u0603')).or(anyOf("\u06dd\u070f\u1680\u17b4\u17b5\u180e")).or(inRange('\u2000', '\u200f')).or(inRange('\u2028', '\u202f')).or(inRange('\u205f', '\u2064')).or(inRange('\u206a', '\u206f')).or(is('\u3000')).or(inRange('\ud800', '\uf8ff')).or(anyOf("\ufeff\ufff9\ufffa\ufffb"));
        SINGLE_WIDTH = inRange('\u0000', '\u04f9').or(is('\u05be')).or(inRange('\u05d0', '\u05ea')).or(is('\u05f3')).or(is('\u05f4')).or(inRange('\u0600', '\u06ff')).or(inRange('\u0750', '\u077f')).or(inRange('\u0e00', '\u0e7f')).or(inRange('\u1e00', '\u20af')).or(inRange('\u2100', '\u213a')).or(inRange('\ufb50', '\ufdff')).or(inRange('\ufe70', '\ufeff')).or(inRange('\uff61', '\uffdc'));
        ANY = new CharMatcher() {
            public /* bridge */ /* synthetic */ boolean apply(Object obj) {
                return super.apply((Character) obj);
            }

            public boolean matches(char c) {
                return true;
            }

            public int indexIn(CharSequence sequence) {
                return sequence.length() == 0 ? -1 : 0;
            }

            public int indexIn(CharSequence sequence, int start) {
                int length = sequence.length();
                Preconditions.checkPositionIndex(start, length);
                return start == length ? -1 : start;
            }

            public int lastIndexIn(CharSequence sequence) {
                return sequence.length() - 1;
            }

            public boolean matchesAllOf(CharSequence sequence) {
                Preconditions.checkNotNull(sequence);
                return true;
            }

            public boolean matchesNoneOf(CharSequence sequence) {
                return sequence.length() == 0;
            }

            public String removeFrom(CharSequence sequence) {
                Preconditions.checkNotNull(sequence);
                return BuildConfig.VERSION_NAME;
            }

            public String replaceFrom(CharSequence sequence, char replacement) {
                char[] array = new char[sequence.length()];
                Arrays.fill(array, replacement);
                return new String(array);
            }

            public String replaceFrom(CharSequence sequence, CharSequence replacement) {
                StringBuilder retval = new StringBuilder(sequence.length() * replacement.length());
                for (int i = 0; i < sequence.length(); i++) {
                    retval.append(replacement);
                }
                return retval.toString();
            }

            public String collapseFrom(CharSequence sequence, char replacement) {
                return sequence.length() == 0 ? BuildConfig.VERSION_NAME : String.valueOf(replacement);
            }

            public String trimFrom(CharSequence sequence) {
                Preconditions.checkNotNull(sequence);
                return BuildConfig.VERSION_NAME;
            }

            public int countIn(CharSequence sequence) {
                return sequence.length();
            }

            public CharMatcher and(CharMatcher other) {
                return (CharMatcher) Preconditions.checkNotNull(other);
            }

            public CharMatcher or(CharMatcher other) {
                Preconditions.checkNotNull(other);
                return this;
            }

            public CharMatcher negate() {
                return NONE;
            }

            public CharMatcher precomputed() {
                return this;
            }
        };
        NONE = new CharMatcher() {
            public /* bridge */ /* synthetic */ boolean apply(Object obj) {
                return super.apply((Character) obj);
            }

            public boolean matches(char c) {
                return false;
            }

            public int indexIn(CharSequence sequence) {
                Preconditions.checkNotNull(sequence);
                return -1;
            }

            public int indexIn(CharSequence sequence, int start) {
                Preconditions.checkPositionIndex(start, sequence.length());
                return -1;
            }

            public int lastIndexIn(CharSequence sequence) {
                Preconditions.checkNotNull(sequence);
                return -1;
            }

            public boolean matchesAllOf(CharSequence sequence) {
                return sequence.length() == 0;
            }

            public boolean matchesNoneOf(CharSequence sequence) {
                Preconditions.checkNotNull(sequence);
                return true;
            }

            public String removeFrom(CharSequence sequence) {
                return sequence.toString();
            }

            public String replaceFrom(CharSequence sequence, char replacement) {
                return sequence.toString();
            }

            public String replaceFrom(CharSequence sequence, CharSequence replacement) {
                Preconditions.checkNotNull(replacement);
                return sequence.toString();
            }

            public String collapseFrom(CharSequence sequence, char replacement) {
                return sequence.toString();
            }

            public String trimFrom(CharSequence sequence) {
                return sequence.toString();
            }

            public int countIn(CharSequence sequence) {
                Preconditions.checkNotNull(sequence);
                return 0;
            }

            public CharMatcher and(CharMatcher other) {
                Preconditions.checkNotNull(other);
                return this;
            }

            public CharMatcher or(CharMatcher other) {
                return (CharMatcher) Preconditions.checkNotNull(other);
            }

            public CharMatcher negate() {
                return ANY;
            }

            protected void setBits(LookupTable table) {
            }

            public CharMatcher precomputed() {
                return this;
            }
        };
    }

    public static CharMatcher is(char match) {
        return new AnonymousClass8(match);
    }

    public static CharMatcher isNot(char match) {
        return new AnonymousClass9(match);
    }

    public static CharMatcher anyOf(CharSequence sequence) {
        switch (sequence.length()) {
            case Action.UNKNOWN /*0*/:
                return NONE;
            case Type.TEMPORARY /*1*/:
                return is(sequence.charAt(0));
            case Type.INDEFINITELY /*2*/:
                return new AnonymousClass10(sequence.charAt(0), sequence.charAt(1));
            default:
                char[] chars = sequence.toString().toCharArray();
                Arrays.sort(chars);
                return new AnonymousClass11(chars);
        }
    }

    public static CharMatcher noneOf(CharSequence sequence) {
        return anyOf(sequence).negate();
    }

    public static CharMatcher inRange(char startInclusive, char endInclusive) {
        Preconditions.checkArgument(endInclusive >= startInclusive);
        return new AnonymousClass12(startInclusive, endInclusive);
    }

    public static CharMatcher forPredicate(Predicate<? super Character> predicate) {
        Preconditions.checkNotNull(predicate);
        if (predicate instanceof CharMatcher) {
            return (CharMatcher) predicate;
        }
        return new AnonymousClass13(predicate);
    }

    public CharMatcher negate() {
        return new CharMatcher() {
            final /* synthetic */ CharMatcher val$original;

            {
                this.val$original = r2;
            }

            public /* bridge */ /* synthetic */ boolean apply(Object obj) {
                return super.apply((Character) obj);
            }

            public boolean matches(char c) {
                return !this.val$original.matches(c);
            }

            public boolean matchesAllOf(CharSequence sequence) {
                return this.val$original.matchesNoneOf(sequence);
            }

            public boolean matchesNoneOf(CharSequence sequence) {
                return this.val$original.matchesAllOf(sequence);
            }

            public int countIn(CharSequence sequence) {
                return sequence.length() - this.val$original.countIn(sequence);
            }

            public CharMatcher negate() {
                return this.val$original;
            }
        };
    }

    public CharMatcher and(CharMatcher other) {
        return new And(Arrays.asList(new CharMatcher[]{this, (CharMatcher) Preconditions.checkNotNull(other)}));
    }

    public CharMatcher or(CharMatcher other) {
        return new Or(Arrays.asList(new CharMatcher[]{this, (CharMatcher) Preconditions.checkNotNull(other)}));
    }

    public CharMatcher precomputed() {
        return precomputedInternal();
    }

    CharMatcher precomputedInternal() {
        LookupTable table = new LookupTable();
        setBits(table);
        return new AnonymousClass15(table);
    }

    protected void setBits(LookupTable table) {
        char c = '\u0000';
        while (true) {
            if (matches(c)) {
                table.set(c);
            }
            char c2 = (char) (c + 1);
            if (c != '\uffff') {
                c = c2;
            } else {
                return;
            }
        }
    }

    public boolean matchesAllOf(CharSequence sequence) {
        for (int i = sequence.length() - 1; i >= 0; i--) {
            if (!matches(sequence.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public boolean matchesNoneOf(CharSequence sequence) {
        return indexIn(sequence) == -1;
    }

    public int indexIn(CharSequence sequence) {
        int length = sequence.length();
        for (int i = 0; i < length; i++) {
            if (matches(sequence.charAt(i))) {
                return i;
            }
        }
        return -1;
    }

    public int indexIn(CharSequence sequence, int start) {
        int length = sequence.length();
        Preconditions.checkPositionIndex(start, length);
        for (int i = start; i < length; i++) {
            if (matches(sequence.charAt(i))) {
                return i;
            }
        }
        return -1;
    }

    public int lastIndexIn(CharSequence sequence) {
        for (int i = sequence.length() - 1; i >= 0; i--) {
            if (matches(sequence.charAt(i))) {
                return i;
            }
        }
        return -1;
    }

    public int countIn(CharSequence sequence) {
        int count = 0;
        for (int i = 0; i < sequence.length(); i++) {
            if (matches(sequence.charAt(i))) {
                count++;
            }
        }
        return count;
    }

    public String removeFrom(CharSequence sequence) {
        String string = sequence.toString();
        int pos = indexIn(string);
        if (pos == -1) {
            return string;
        }
        char[] chars = string.toCharArray();
        int spread = 1;
        while (true) {
            pos++;
            while (pos != chars.length) {
                if (matches(chars[pos])) {
                    spread++;
                } else {
                    chars[pos - spread] = chars[pos];
                    pos++;
                }
            }
            return new String(chars, 0, pos - spread);
        }
    }

    public String retainFrom(CharSequence sequence) {
        return negate().removeFrom(sequence);
    }

    public String replaceFrom(CharSequence sequence, char replacement) {
        String string = sequence.toString();
        int pos = indexIn(string);
        if (pos == -1) {
            return string;
        }
        char[] chars = string.toCharArray();
        chars[pos] = replacement;
        for (int i = pos + 1; i < chars.length; i++) {
            if (matches(chars[i])) {
                chars[i] = replacement;
            }
        }
        return new String(chars);
    }

    public String replaceFrom(CharSequence sequence, CharSequence replacement) {
        int replacementLen = replacement.length();
        if (replacementLen == 0) {
            return removeFrom(sequence);
        }
        if (replacementLen == 1) {
            return replaceFrom(sequence, replacement.charAt(0));
        }
        String string = sequence.toString();
        int pos = indexIn(string);
        if (pos == -1) {
            return string;
        }
        int len = string.length();
        StringBuilder buf = new StringBuilder(((int) (((double) len) * 1.5d)) + 16);
        int oldpos = 0;
        do {
            buf.append(string, oldpos, pos);
            buf.append(replacement);
            oldpos = pos + 1;
            pos = indexIn(string, oldpos);
        } while (pos != -1);
        buf.append(string, oldpos, len);
        return buf.toString();
    }

    public String trimFrom(CharSequence sequence) {
        int len = sequence.length();
        int first = 0;
        while (first < len && matches(sequence.charAt(first))) {
            first++;
        }
        int last = len - 1;
        while (last > first && matches(sequence.charAt(last))) {
            last--;
        }
        return sequence.subSequence(first, last + 1).toString();
    }

    public String trimLeadingFrom(CharSequence sequence) {
        int len = sequence.length();
        int first = 0;
        while (first < len && matches(sequence.charAt(first))) {
            first++;
        }
        return sequence.subSequence(first, len).toString();
    }

    public String trimTrailingFrom(CharSequence sequence) {
        int last = sequence.length() - 1;
        while (last >= 0 && matches(sequence.charAt(last))) {
            last--;
        }
        return sequence.subSequence(0, last + 1).toString();
    }

    public String collapseFrom(CharSequence sequence, char replacement) {
        int first = indexIn(sequence);
        if (first == -1) {
            return sequence.toString();
        }
        StringBuilder builder = new StringBuilder(sequence.length()).append(sequence.subSequence(0, first)).append(replacement);
        boolean in = true;
        for (int i = first + 1; i < sequence.length(); i++) {
            char c = sequence.charAt(i);
            if (!apply(Character.valueOf(c))) {
                builder.append(c);
                in = false;
            } else if (!in) {
                builder.append(replacement);
                in = true;
            }
        }
        return builder.toString();
    }

    public String trimAndCollapseFrom(CharSequence sequence, char replacement) {
        int first = negate().indexIn(sequence);
        if (first == -1) {
            return BuildConfig.VERSION_NAME;
        }
        StringBuilder builder = new StringBuilder(sequence.length());
        boolean inMatchingGroup = false;
        for (int i = first; i < sequence.length(); i++) {
            char c = sequence.charAt(i);
            if (apply(Character.valueOf(c))) {
                inMatchingGroup = true;
            } else {
                if (inMatchingGroup) {
                    builder.append(replacement);
                    inMatchingGroup = false;
                }
                builder.append(c);
            }
        }
        return builder.toString();
    }

    public boolean apply(Character character) {
        return matches(character.charValue());
    }
}
