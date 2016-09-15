package com.google.android.gms.common.images.internal;

import android.graphics.Point;
import android.net.Uri;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Joiner;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

public class FIFEUtil {
    private static final int CONTENT_URL_MAX_NUM_PATH_PARTS = 1;
    private static final String EMPTY_STRING = "";
    private static final Pattern FIFE_HOSTED_IMAGE_URL_RE;
    private static final Joiner JOIN_ON_SLASH;
    private static final int LEGACY_BASE_URL_NUM_PATH_PARTS = 4;
    private static final int LEGACY_URL_MAX_NUM_PATH_PARTS = 6;
    private static final int LEGACY_URL_PATH_OPTIONS_INDEX = 4;
    private static final int LEGACY_WITH_OPTIONS_FILENAME = 5;
    private static final Splitter SPLIT_ON_EQUALS;
    private static final Splitter SPLIT_ON_SLASH;

    /* renamed from: com.google.android.gms.common.images.internal.FIFEUtil.1 */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$android$gms$common$images$internal$FIFEUtil$Splitter$AbstractIterator$State;

        static {
            $SwitchMap$com$google$android$gms$common$images$internal$FIFEUtil$Splitter$AbstractIterator$State = new int[State.values().length];
            try {
                $SwitchMap$com$google$android$gms$common$images$internal$FIFEUtil$Splitter$AbstractIterator$State[State.DONE.ordinal()] = FIFEUtil.CONTENT_URL_MAX_NUM_PATH_PARTS;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$google$android$gms$common$images$internal$FIFEUtil$Splitter$AbstractIterator$State[State.READY.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    static class Splitter {
        private final boolean omitEmptyStrings;
        private final Strategy strategy;

        private static abstract class AbstractIterator<T> implements Iterator<T> {
            T next;
            State state;

            enum State {
            }

            protected abstract T computeNext();

            private AbstractIterator() {
                /* JADX: method processing error */
/*
                Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:119)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:281)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:161)
*/
                /*
                r1 = this;
                r1.<init>();
                r0 = com.google.android.gms.common.images.internal.FIFEUtil.Splitter.AbstractIterator.State.NOT_READY;
                r1.state = r0;
                return;
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.images.internal.FIFEUtil.Splitter.AbstractIterator.<init>():void");
            }

            protected final T endOfData() {
                /* JADX: method processing error */
/*
                Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:119)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:281)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:161)
*/
                /*
                r1 = this;
                r0 = com.google.android.gms.common.images.internal.FIFEUtil.Splitter.AbstractIterator.State.DONE;
                r1.state = r0;
                r0 = 0;
                return r0;
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.images.internal.FIFEUtil.Splitter.AbstractIterator.endOfData():T");
            }

            public final boolean hasNext() {
                /* JADX: method processing error */
/*
                Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:119)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:281)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:161)
*/
                /*
                r2 = this;
                r0 = r2.state;
                r1 = com.google.android.gms.common.images.internal.FIFEUtil.Splitter.AbstractIterator.State.FAILED;
                if (r0 != r1) goto L_0x000c;
            L_0x0006:
                r0 = new java.lang.IllegalStateException;
                r0.<init>();
                throw r0;
            L_0x000c:
                r0 = com.google.android.gms.common.images.internal.FIFEUtil.AnonymousClass1.$SwitchMap$com$google$android$gms$common$images$internal$FIFEUtil$Splitter$AbstractIterator$State;
                r1 = r2.state;
                r1 = r1.ordinal();
                r0 = r0[r1];
                switch(r0) {
                    case 1: goto L_0x001e;
                    case 2: goto L_0x0020;
                    default: goto L_0x0019;
                };
            L_0x0019:
                r0 = r2.tryToComputeNext();
            L_0x001d:
                return r0;
            L_0x001e:
                r0 = 0;
                goto L_0x001d;
            L_0x0020:
                r0 = 1;
                goto L_0x001d;
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.images.internal.FIFEUtil.Splitter.AbstractIterator.hasNext():boolean");
            }

            boolean tryToComputeNext() {
                /* JADX: method processing error */
/*
                Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:119)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:281)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:161)
*/
                /*
                r2 = this;
                r0 = com.google.android.gms.common.images.internal.FIFEUtil.Splitter.AbstractIterator.State.FAILED;
                r2.state = r0;
                r0 = r2.computeNext();
                r2.next = r0;
                r0 = r2.state;
                r1 = com.google.android.gms.common.images.internal.FIFEUtil.Splitter.AbstractIterator.State.DONE;
                if (r0 == r1) goto L_0x0016;
            L_0x0010:
                r0 = com.google.android.gms.common.images.internal.FIFEUtil.Splitter.AbstractIterator.State.READY;
                r2.state = r0;
                r0 = 1;
            L_0x0015:
                return r0;
            L_0x0016:
                r0 = 0;
                goto L_0x0015;
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.images.internal.FIFEUtil.Splitter.AbstractIterator.tryToComputeNext():boolean");
            }

            public final T next() {
                /* JADX: method processing error */
/*
                Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:119)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:281)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:161)
*/
                /*
                r1 = this;
                r0 = r1.hasNext();
                if (r0 != 0) goto L_0x000c;
            L_0x0006:
                r0 = new java.util.NoSuchElementException;
                r0.<init>();
                throw r0;
            L_0x000c:
                r0 = com.google.android.gms.common.images.internal.FIFEUtil.Splitter.AbstractIterator.State.NOT_READY;
                r1.state = r0;
                r0 = r1.next;
                return r0;
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.images.internal.FIFEUtil.Splitter.AbstractIterator.next():T");
            }

            public void remove() {
                /* JADX: method processing error */
/*
                Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:119)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:281)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:161)
*/
                /*
                r1 = this;
                r0 = new java.lang.UnsupportedOperationException;
                r0.<init>();
                throw r0;
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.images.internal.FIFEUtil.Splitter.AbstractIterator.remove():void");
            }
        }

        private static abstract class SplittingIterator extends AbstractIterator<String> {
            int offset;
            final boolean omitEmptyStrings;
            final CharSequence toSplit;

            abstract int separatorEnd(int i);

            abstract int separatorStart(int i);

            protected SplittingIterator(com.google.android.gms.common.images.internal.FIFEUtil.Splitter r2, java.lang.CharSequence r3) {
                /* JADX: method processing error */
/*
                Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:119)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:281)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:161)
*/
                /*
                r1 = this;
                r0 = 0;
                r1.<init>();
                r0 = 0;
                r1.offset = r0;
                r0 = r2.omitEmptyStrings;
                r1.omitEmptyStrings = r0;
                r1.toSplit = r3;
                return;
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.images.internal.FIFEUtil.Splitter.SplittingIterator.<init>(com.google.android.gms.common.images.internal.FIFEUtil$Splitter, java.lang.CharSequence):void");
            }

            protected java.lang.String computeNext() {
                /* JADX: method processing error */
/*
                Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:119)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:281)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:161)
*/
                /*
                r5 = this;
                r4 = -1;
            L_0x0001:
                r3 = r5.offset;
                if (r3 == r4) goto L_0x0030;
            L_0x0005:
                r2 = r5.offset;
                r3 = r5.offset;
                r1 = r5.separatorStart(r3);
                if (r1 != r4) goto L_0x0028;
            L_0x000f:
                r3 = r5.toSplit;
                r0 = r3.length();
                r5.offset = r4;
            L_0x0017:
                r3 = r5.omitEmptyStrings;
                if (r3 == 0) goto L_0x001d;
            L_0x001b:
                if (r2 == r0) goto L_0x0001;
            L_0x001d:
                r3 = r5.toSplit;
                r3 = r3.subSequence(r2, r0);
                r3 = r3.toString();
            L_0x0027:
                return r3;
            L_0x0028:
                r0 = r1;
                r3 = r5.separatorEnd(r1);
                r5.offset = r3;
                goto L_0x0017;
            L_0x0030:
                r3 = r5.endOfData();
                r3 = (java.lang.String) r3;
                goto L_0x0027;
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.images.internal.FIFEUtil.Splitter.SplittingIterator.computeNext():java.lang.String");
            }
        }

        private interface Strategy {
            Iterator<String> iterator(Splitter splitter, CharSequence charSequence);
        }

        /* renamed from: com.google.android.gms.common.images.internal.FIFEUtil.Splitter.1 */
        static class AnonymousClass1 implements Strategy {
            final /* synthetic */ String val$separator;

            /* renamed from: com.google.android.gms.common.images.internal.FIFEUtil.Splitter.1.1 */
            class AnonymousClass1 extends SplittingIterator {
                AnonymousClass1(com.google.android.gms.common.images.internal.FIFEUtil.Splitter.AnonymousClass1 r1, com.google.android.gms.common.images.internal.FIFEUtil.Splitter r2, java.lang.CharSequence r3) {
                    /* JADX: method processing error */
/*
                    Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:119)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:281)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:161)
*/
                    /*
                    r0 = this;
                    com.google.android.gms.common.images.internal.FIFEUtil.Splitter.AnonymousClass1.this = r1;
                    r0.<init>(r2, r3);
                    return;
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.images.internal.FIFEUtil.Splitter.1.1.<init>(com.google.android.gms.common.images.internal.FIFEUtil$Splitter$1, com.google.android.gms.common.images.internal.FIFEUtil$Splitter, java.lang.CharSequence):void");
                }

                public int separatorStart(int r7) {
                    /* JADX: method processing error */
/*
                    Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:119)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:281)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:161)
*/
                    /*
                    r6 = this;
                    r4 = com.google.android.gms.common.images.internal.FIFEUtil.Splitter.AnonymousClass1.this;
                    r4 = r4.val$separator;
                    r0 = r4.length();
                    r3 = r7;
                    r4 = r6.toSplit;
                    r4 = r4.length();
                    r2 = r4 - r0;
                L_0x0011:
                    if (r3 > r2) goto L_0x002e;
                L_0x0013:
                    r1 = 0;
                L_0x0014:
                    if (r1 >= r0) goto L_0x002f;
                L_0x0016:
                    r4 = r6.toSplit;
                    r5 = r1 + r3;
                    r4 = r4.charAt(r5);
                    r5 = com.google.android.gms.common.images.internal.FIFEUtil.Splitter.AnonymousClass1.this;
                    r5 = r5.val$separator;
                    r5 = r5.charAt(r1);
                    if (r4 == r5) goto L_0x002b;
                L_0x0028:
                    r3 = r3 + 1;
                    goto L_0x0011;
                L_0x002b:
                    r1 = r1 + 1;
                    goto L_0x0014;
                L_0x002e:
                    r3 = -1;
                L_0x002f:
                    return r3;
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.images.internal.FIFEUtil.Splitter.1.1.separatorStart(int):int");
                }

                public int separatorEnd(int r2) {
                    /* JADX: method processing error */
/*
                    Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:119)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:281)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:161)
*/
                    /*
                    r1 = this;
                    r0 = com.google.android.gms.common.images.internal.FIFEUtil.Splitter.AnonymousClass1.this;
                    r0 = r0.val$separator;
                    r0 = r0.length();
                    r0 = r0 + r2;
                    return r0;
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.images.internal.FIFEUtil.Splitter.1.1.separatorEnd(int):int");
                }
            }

            AnonymousClass1(java.lang.String r1) {
                /* JADX: method processing error */
/*
                Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:119)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:281)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:161)
*/
                /*
                r0 = this;
                r0.val$separator = r1;
                r0.<init>();
                return;
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.images.internal.FIFEUtil.Splitter.1.<init>(java.lang.String):void");
            }

            public com.google.android.gms.common.images.internal.FIFEUtil.Splitter.SplittingIterator iterator(com.google.android.gms.common.images.internal.FIFEUtil.Splitter r2, java.lang.CharSequence r3) {
                /* JADX: method processing error */
/*
                Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:119)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:281)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:161)
*/
                /*
                r1 = this;
                r0 = new com.google.android.gms.common.images.internal.FIFEUtil$Splitter$1$1;
                r0.<init>(r2, r3);
                return r0;
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.images.internal.FIFEUtil.Splitter.1.iterator(com.google.android.gms.common.images.internal.FIFEUtil$Splitter, java.lang.CharSequence):com.google.android.gms.common.images.internal.FIFEUtil$Splitter$SplittingIterator");
            }
        }

        /* renamed from: com.google.android.gms.common.images.internal.FIFEUtil.Splitter.2 */
        class AnonymousClass2 implements Iterable<String> {
            final /* synthetic */ CharSequence val$sequence;

            AnonymousClass2(com.google.android.gms.common.images.internal.FIFEUtil.Splitter r1, java.lang.CharSequence r2) {
                /* JADX: method processing error */
/*
                Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:119)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:281)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:161)
*/
                /*
                r0 = this;
                com.google.android.gms.common.images.internal.FIFEUtil.Splitter.this = r1;
                r0.val$sequence = r2;
                r0.<init>();
                return;
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.images.internal.FIFEUtil.Splitter.2.<init>(com.google.android.gms.common.images.internal.FIFEUtil$Splitter, java.lang.CharSequence):void");
            }

            public java.util.Iterator<java.lang.String> iterator() {
                /* JADX: method processing error */
/*
                Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:119)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:281)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:161)
*/
                /*
                r3 = this;
                r0 = com.google.android.gms.common.images.internal.FIFEUtil.Splitter.this;
                r0 = r0.strategy;
                r1 = com.google.android.gms.common.images.internal.FIFEUtil.Splitter.this;
                r2 = r3.val$sequence;
                r0 = r0.iterator(r1, r2);
                return r0;
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.images.internal.FIFEUtil.Splitter.2.iterator():java.util.Iterator<java.lang.String>");
            }
        }

        private Splitter(com.google.android.gms.common.images.internal.FIFEUtil.Splitter.Strategy r2) {
            /* JADX: method processing error */
/*
            Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:119)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:281)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:161)
*/
            /*
            r1 = this;
            r0 = 0;
            r1.<init>(r2, r0);
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.images.internal.FIFEUtil.Splitter.<init>(com.google.android.gms.common.images.internal.FIFEUtil$Splitter$Strategy):void");
        }

        private Splitter(com.google.android.gms.common.images.internal.FIFEUtil.Splitter.Strategy r1, boolean r2) {
            /* JADX: method processing error */
/*
            Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:119)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:281)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:161)
*/
            /*
            r0 = this;
            r0.<init>();
            r0.strategy = r1;
            r0.omitEmptyStrings = r2;
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.images.internal.FIFEUtil.Splitter.<init>(com.google.android.gms.common.images.internal.FIFEUtil$Splitter$Strategy, boolean):void");
        }

        public static com.google.android.gms.common.images.internal.FIFEUtil.Splitter on(java.lang.String r2) {
            /* JADX: method processing error */
/*
            Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:119)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:281)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:161)
*/
            /*
            if (r2 == 0) goto L_0x0008;
        L_0x0002:
            r0 = r2.length();
            if (r0 != 0) goto L_0x0010;
        L_0x0008:
            r0 = new java.lang.IllegalArgumentException;
            r1 = "separator may not be empty or null";
            r0.<init>(r1);
            throw r0;
        L_0x0010:
            r0 = new com.google.android.gms.common.images.internal.FIFEUtil$Splitter;
            r1 = new com.google.android.gms.common.images.internal.FIFEUtil$Splitter$1;
            r1.<init>(r2);
            r0.<init>(r1);
            return r0;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.images.internal.FIFEUtil.Splitter.on(java.lang.String):com.google.android.gms.common.images.internal.FIFEUtil$Splitter");
        }

        public com.google.android.gms.common.images.internal.FIFEUtil.Splitter omitEmptyStrings() {
            /* JADX: method processing error */
/*
            Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:119)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:281)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:161)
*/
            /*
            r3 = this;
            r0 = new com.google.android.gms.common.images.internal.FIFEUtil$Splitter;
            r1 = r3.strategy;
            r2 = 1;
            r0.<init>(r1, r2);
            return r0;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.images.internal.FIFEUtil.Splitter.omitEmptyStrings():com.google.android.gms.common.images.internal.FIFEUtil$Splitter");
        }

        public java.lang.Iterable<java.lang.String> split(java.lang.CharSequence r2) {
            /* JADX: method processing error */
/*
            Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:119)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:281)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:161)
*/
            /*
            r1 = this;
            r0 = new com.google.android.gms.common.images.internal.FIFEUtil$Splitter$2;
            r0.<init>(r2);
            return r0;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.images.internal.FIFEUtil.Splitter.split(java.lang.CharSequence):java.lang.Iterable<java.lang.String>");
        }
    }

    static {
        SPLIT_ON_EQUALS = Splitter.on("=").omitEmptyStrings();
        SPLIT_ON_SLASH = Splitter.on("/").omitEmptyStrings();
        JOIN_ON_SLASH = Joiner.on("/");
        FIFE_HOSTED_IMAGE_URL_RE = Pattern.compile("^((http(s)?):)?\\/\\/((((lh[3-6](-tt|-d[a-g,z])?\\.((ggpht)|(googleusercontent)|(google)))|(([1-4]\\.bp\\.blogspot)|(bp[0-3]\\.blogger))|((((cp|ci|gp)[3-6])|(ap[1-2]))\\.(ggpht|googleusercontent))|(gm[1-4]\\.ggpht)|(((yt[3-4])|(sp[1-3]))\\.(ggpht|googleusercontent)))\\.com)|(dp[3-6]\\.googleusercontent\\.cn)|(lh[3-6]\\.(googleadsserving\\.cn|xn--9kr7l\\.com))|((dev|dev2|dev3|qa|qa2|qa3|qa-red|qa-blue|canary)[-.]lighthouse\\.sandbox\\.google\\.com\\/image)|(image\\-dev\\-lighthouse\\.sandbox\\.google\\.com(\\/image)?))\\/");
    }

    public static String setImageUrlSize(int size, String url, boolean crop, boolean killAnimations) {
        if (url == null || !isFifeHostedUrl(url)) {
            return url;
        }
        StringBuffer options = new StringBuffer();
        if (size != -1) {
            options.append("s").append(size);
        }
        options.append("-d-no");
        if (crop) {
            options.append("-c");
        }
        if (killAnimations) {
            options.append("-k");
        }
        return makeUriString(setImageUrlOptions(options.toString(), url));
    }

    public static String setImageUrlSize(int width, int height, String url, boolean crop) {
        if (url == null || !isFifeHostedUrl(url)) {
            return url;
        }
        StringBuffer options = new StringBuffer();
        options.append("w").append(width);
        options.append("-h").append(height);
        options.append("-d-no");
        if (crop) {
            options.append("-c");
        }
        return makeUriString(setImageUrlOptions(options.toString(), url));
    }

    public static Point getImageUrlSize(String url) {
        Point imageSize = new Point();
        if (url != null && isFifeHostedUrl(url)) {
            String options = getImageUriOptions(Uri.parse(url));
            if (!TextUtils.isEmpty(options)) {
                String[] optionArray = options.split("-");
                for (int i = 0; i < optionArray.length; i += CONTENT_URL_MAX_NUM_PATH_PARTS) {
                    String opt = optionArray[i];
                    try {
                        if (opt.startsWith("w")) {
                            imageSize.x = Integer.parseInt(opt.substring(CONTENT_URL_MAX_NUM_PATH_PARTS));
                        } else if (opt.startsWith("h")) {
                            imageSize.y = Integer.parseInt(opt.substring(CONTENT_URL_MAX_NUM_PATH_PARTS));
                        } else if (opt.startsWith("s")) {
                            int parseInt = Integer.parseInt(opt.substring(CONTENT_URL_MAX_NUM_PATH_PARTS));
                            imageSize.y = parseInt;
                            imageSize.x = parseInt;
                        }
                    } catch (NumberFormatException e) {
                    }
                }
            }
        }
        return imageSize;
    }

    private static String makeUriString(Uri uri) {
        StringBuilder builder = new StringBuilder();
        String scheme = uri.getScheme();
        if (scheme != null) {
            builder.append(scheme).append(':');
        }
        String encodedAuthority = uri.getEncodedAuthority();
        if (encodedAuthority != null) {
            builder.append("//").append(encodedAuthority);
        }
        String encodedPath = Uri.encode(uri.getPath(), "/=");
        if (encodedPath != null) {
            builder.append(encodedPath);
        }
        String encodedQuery = uri.getEncodedQuery();
        if (!TextUtils.isEmpty(encodedQuery)) {
            builder.append('?').append(encodedQuery);
        }
        String encodedFragment = uri.getEncodedFragment();
        if (!TextUtils.isEmpty(encodedFragment)) {
            builder.append('#').append(encodedFragment);
        }
        return builder.toString();
    }

    public static Uri setImageUrlOptions(String options, String url) {
        return setImageUriOptions(options, Uri.parse(url));
    }

    static Uri setImageUriOptions(String options, Uri uri) {
        List<String> components = newArrayList(SPLIT_ON_SLASH.split(uri.getPath()));
        int numParts = components.size();
        if (components.size() > CONTENT_URL_MAX_NUM_PATH_PARTS && ((String) components.get(0)).equals("image")) {
            numParts--;
        }
        if (numParts >= LEGACY_URL_PATH_OPTIONS_INDEX && numParts <= LEGACY_URL_MAX_NUM_PATH_PARTS) {
            return setLegacyImageUrlOptions(options, uri);
        }
        if (numParts == CONTENT_URL_MAX_NUM_PATH_PARTS) {
            return setContentImageUrlOptions(options, uri);
        }
        return uri;
    }

    public static String getImageUrlOptions(String url) {
        return getImageUriOptions(Uri.parse(url));
    }

    static String getImageUriOptions(Uri uri) {
        List<String> components = newArrayList(SPLIT_ON_SLASH.split(uri.getPath()));
        int numParts = components.size();
        if (components.size() > CONTENT_URL_MAX_NUM_PATH_PARTS && ((String) components.get(0)).equals("image")) {
            numParts--;
        }
        if (numParts >= LEGACY_URL_PATH_OPTIONS_INDEX && numParts <= LEGACY_URL_MAX_NUM_PATH_PARTS) {
            return getLegacyImageUriOptions(uri);
        }
        if (numParts == CONTENT_URL_MAX_NUM_PATH_PARTS) {
            return getContentImageUriOptions(uri);
        }
        return EMPTY_STRING;
    }

    public static boolean isFifeHostedUrl(String url) {
        if (url == null) {
            return false;
        }
        return FIFE_HOSTED_IMAGE_URL_RE.matcher(url).find();
    }

    public static boolean isFifeHostedUri(Uri uri) {
        return isFifeHostedUrl(uri.toString());
    }

    private static Uri setLegacyImageUrlOptions(String options, Uri url) {
        boolean containsFilenameNoOptions;
        boolean isBaseUrlFormat = true;
        String path = url.getPath();
        List<String> components = newArrayList(SPLIT_ON_SLASH.split(path));
        boolean hasImagePrefix = false;
        if (components.size() > 0 && ((String) components.get(0)).equals("image")) {
            components.remove(0);
            hasImagePrefix = true;
        }
        int numParts = components.size();
        boolean isPathSlashTerminated = path.endsWith("/");
        if (isPathSlashTerminated || numParts != LEGACY_WITH_OPTIONS_FILENAME) {
            containsFilenameNoOptions = false;
        } else {
            containsFilenameNoOptions = true;
        }
        if (numParts != LEGACY_URL_PATH_OPTIONS_INDEX) {
            isBaseUrlFormat = false;
        }
        if (containsFilenameNoOptions) {
            components.add(components.get(LEGACY_URL_PATH_OPTIONS_INDEX));
        }
        if (isBaseUrlFormat) {
            components.add(options);
        } else {
            components.set(LEGACY_URL_PATH_OPTIONS_INDEX, options);
        }
        if (hasImagePrefix) {
            components.add(0, "image");
        }
        if (isPathSlashTerminated) {
            components.add(EMPTY_STRING);
        }
        return url.buildUpon().path("/" + JOIN_ON_SLASH.join(components)).build();
    }

    private static Uri setContentImageUrlOptions(String options, Uri url) {
        return url.buildUpon().path(((String) newArrayList(SPLIT_ON_EQUALS.split(url.getPath())).get(0)) + "=" + options).build();
    }

    private static String getLegacyImageUriOptions(Uri uri) {
        boolean containsFilenameNoOptions;
        boolean isBaseUrlFormat = true;
        String path = uri.getPath();
        List<String> components = newArrayList(SPLIT_ON_SLASH.split(path));
        if (components.size() > 0 && ((String) components.get(0)).equals("image")) {
            components.remove(0);
        }
        int numParts = components.size();
        if (path.endsWith("/") || numParts != LEGACY_WITH_OPTIONS_FILENAME) {
            containsFilenameNoOptions = false;
        } else {
            containsFilenameNoOptions = true;
        }
        if (numParts != LEGACY_URL_PATH_OPTIONS_INDEX) {
            isBaseUrlFormat = false;
        }
        if (containsFilenameNoOptions) {
            return EMPTY_STRING;
        }
        if (isBaseUrlFormat) {
            return EMPTY_STRING;
        }
        return (String) components.get(LEGACY_URL_PATH_OPTIONS_INDEX);
    }

    private static String getContentImageUriOptions(Uri uri) {
        List<String> splitPath = newArrayList(SPLIT_ON_EQUALS.split(uri.getPath()));
        return splitPath.size() > CONTENT_URL_MAX_NUM_PATH_PARTS ? (String) splitPath.get(CONTENT_URL_MAX_NUM_PATH_PARTS) : EMPTY_STRING;
    }

    private FIFEUtil() {
    }

    private static <E> ArrayList<E> newArrayList(Iterable<? extends E> elements) {
        ArrayList<E> list = new ArrayList();
        for (Object add : elements) {
            list.add(add);
        }
        return list;
    }
}
