package com.google.android.gms.common.images.internal;

import android.annotation.TargetApi;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.Callback;
import android.graphics.drawable.Drawable.ConstantState;
import android.os.SystemClock;
import android.support.v4.view.MotionEventCompat;
import com.google.android.gms.common.util.PlatformVersion;

public final class CrossFadingDrawable extends Drawable implements Callback {
    private static final int TRANSITION_NONE = 0;
    private static final int TRANSITION_RUNNING = 2;
    private static final int TRANSITION_STARTING = 1;
    private int mAlpha;
    private boolean mCanConstantState;
    private boolean mCheckedConstantState;
    private boolean mCrossFade;
    private int mDuration;
    private Drawable mEndDrawable;
    private int mFrom;
    private boolean mHaveOpacity;
    private int mMaxAlpha;
    private boolean mMutated;
    private int mOpacity;
    private RealTransitionState mRealTransitionState;
    private Drawable mStartDrawable;
    private long mStartTimeMillis;
    private int mTo;
    private int mTransitionState;

    private static final class EmptyDrawable extends Drawable {
        private static final EmptyConstantState EMPTY_CONSTANT_STATE;
        private static final EmptyDrawable EMPTY_DRAWABLE;

        private static final class EmptyConstantState extends ConstantState {
            private EmptyConstantState() {
            }

            public Drawable newDrawable() {
                return EmptyDrawable.EMPTY_DRAWABLE;
            }

            public int getChangingConfigurations() {
                return CrossFadingDrawable.TRANSITION_NONE;
            }
        }

        private EmptyDrawable() {
        }

        static {
            EMPTY_DRAWABLE = new EmptyDrawable();
            EMPTY_CONSTANT_STATE = new EmptyConstantState();
        }

        public void draw(Canvas canvas) {
        }

        public void setAlpha(int alpha) {
        }

        public void setColorFilter(ColorFilter cf) {
        }

        public int getOpacity() {
            return -2;
        }

        public ConstantState getConstantState() {
            return EMPTY_CONSTANT_STATE;
        }
    }

    static final class RealTransitionState extends ConstantState {
        int mChangingConfigurations;
        int mChildrenChangingConfigurations;

        RealTransitionState(RealTransitionState orig) {
            if (orig != null) {
                this.mChangingConfigurations = orig.mChangingConfigurations;
                this.mChildrenChangingConfigurations = orig.mChildrenChangingConfigurations;
            }
        }

        public Drawable newDrawable() {
            return new CrossFadingDrawable(this);
        }

        public int getChangingConfigurations() {
            return this.mChangingConfigurations;
        }
    }

    public CrossFadingDrawable(Drawable startDrawable, Drawable endDrawable) {
        this(null);
        if (startDrawable == null) {
            startDrawable = EmptyDrawable.EMPTY_DRAWABLE;
        }
        this.mStartDrawable = startDrawable;
        startDrawable.setCallback(this);
        RealTransitionState realTransitionState = this.mRealTransitionState;
        realTransitionState.mChildrenChangingConfigurations |= startDrawable.getChangingConfigurations();
        if (endDrawable == null) {
            endDrawable = EmptyDrawable.EMPTY_DRAWABLE;
        }
        this.mEndDrawable = endDrawable;
        endDrawable.setCallback(this);
        realTransitionState = this.mRealTransitionState;
        realTransitionState.mChildrenChangingConfigurations |= endDrawable.getChangingConfigurations();
    }

    CrossFadingDrawable(RealTransitionState realTransitionState) {
        this.mTransitionState = TRANSITION_NONE;
        this.mMaxAlpha = MotionEventCompat.ACTION_MASK;
        this.mAlpha = TRANSITION_NONE;
        this.mCrossFade = true;
        this.mRealTransitionState = new RealTransitionState(realTransitionState);
    }

    @TargetApi(11)
    public void invalidateDrawable(Drawable who) {
        if (PlatformVersion.isAtLeastHoneycomb()) {
            Callback callback = getCallback();
            if (callback != null) {
                callback.invalidateDrawable(this);
            }
        }
    }

    @TargetApi(11)
    public void scheduleDrawable(Drawable who, Runnable what, long when) {
        if (PlatformVersion.isAtLeastHoneycomb()) {
            Callback callback = getCallback();
            if (callback != null) {
                callback.scheduleDrawable(this, what, when);
            }
        }
    }

    @TargetApi(11)
    public void unscheduleDrawable(Drawable who, Runnable what) {
        if (PlatformVersion.isAtLeastHoneycomb()) {
            Callback callback = getCallback();
            if (callback != null) {
                callback.unscheduleDrawable(this, what);
            }
        }
    }

    public int getChangingConfigurations() {
        return (super.getChangingConfigurations() | this.mRealTransitionState.mChangingConfigurations) | this.mRealTransitionState.mChildrenChangingConfigurations;
    }

    public void setAlpha(int alpha) {
        if (this.mAlpha == this.mMaxAlpha) {
            this.mAlpha = alpha;
        }
        this.mMaxAlpha = alpha;
        invalidateSelf();
    }

    public void setColorFilter(ColorFilter cf) {
        this.mStartDrawable.setColorFilter(cf);
        this.mEndDrawable.setColorFilter(cf);
    }

    public int getIntrinsicWidth() {
        return Math.max(this.mStartDrawable.getIntrinsicWidth(), this.mEndDrawable.getIntrinsicWidth());
    }

    public int getIntrinsicHeight() {
        return Math.max(this.mStartDrawable.getIntrinsicHeight(), this.mEndDrawable.getIntrinsicHeight());
    }

    protected void onBoundsChange(Rect bounds) {
        this.mStartDrawable.setBounds(bounds);
        this.mEndDrawable.setBounds(bounds);
    }

    public ConstantState getConstantState() {
        if (!canConstantState()) {
            return null;
        }
        this.mRealTransitionState.mChangingConfigurations = getChangingConfigurations();
        return this.mRealTransitionState;
    }

    public int getOpacity() {
        if (!this.mHaveOpacity) {
            this.mOpacity = Drawable.resolveOpacity(this.mStartDrawable.getOpacity(), this.mEndDrawable.getOpacity());
            this.mHaveOpacity = true;
        }
        return this.mOpacity;
    }

    public boolean canConstantState() {
        if (!this.mCheckedConstantState) {
            boolean z = (this.mStartDrawable.getConstantState() == null || this.mEndDrawable.getConstantState() == null) ? false : true;
            this.mCanConstantState = z;
            this.mCheckedConstantState = true;
        }
        return this.mCanConstantState;
    }

    public Drawable mutate() {
        if (!this.mMutated && super.mutate() == this) {
            if (canConstantState()) {
                this.mStartDrawable.mutate();
                this.mEndDrawable.mutate();
                this.mMutated = true;
            } else {
                throw new IllegalStateException("One or more children of this LayerDrawable does not have constant state; this drawable cannot be mutated.");
            }
        }
        return this;
    }

    public Drawable getStartDrawable() {
        return this.mStartDrawable;
    }

    public Drawable getEndDrawable() {
        return this.mEndDrawable;
    }

    public void startTransition(int durationMillis) {
        this.mFrom = TRANSITION_NONE;
        this.mTo = this.mMaxAlpha;
        this.mAlpha = TRANSITION_NONE;
        this.mDuration = durationMillis;
        this.mTransitionState = TRANSITION_STARTING;
        invalidateSelf();
    }

    public void resetTransition() {
        this.mAlpha = TRANSITION_NONE;
        this.mTransitionState = TRANSITION_NONE;
        invalidateSelf();
    }

    public void setCrossFadeEnabled(boolean enabled) {
        this.mCrossFade = enabled;
    }

    public void draw(Canvas canvas) {
        boolean done = true;
        switch (this.mTransitionState) {
            case TRANSITION_STARTING /*1*/:
                this.mStartTimeMillis = SystemClock.uptimeMillis();
                done = false;
                this.mTransitionState = TRANSITION_RUNNING;
                break;
            case TRANSITION_RUNNING /*2*/:
                if (this.mStartTimeMillis >= 0) {
                    float normalized = ((float) (SystemClock.uptimeMillis() - this.mStartTimeMillis)) / ((float) this.mDuration);
                    done = normalized >= 1.0f;
                    if (done) {
                        this.mTransitionState = TRANSITION_NONE;
                    }
                    this.mAlpha = (int) (((float) this.mFrom) + (((float) (this.mTo - this.mFrom)) * Math.min(normalized, 1.0f)));
                    break;
                }
                break;
        }
        int alpha = this.mAlpha;
        boolean crossFade = this.mCrossFade;
        Drawable startDrawable = this.mStartDrawable;
        Drawable endDrawable = this.mEndDrawable;
        if (done) {
            if (!crossFade || alpha == 0) {
                startDrawable.draw(canvas);
            }
            if (alpha == this.mMaxAlpha) {
                endDrawable.setAlpha(this.mMaxAlpha);
                endDrawable.draw(canvas);
                return;
            }
            return;
        }
        if (crossFade) {
            startDrawable.setAlpha(this.mMaxAlpha - alpha);
        }
        startDrawable.draw(canvas);
        if (crossFade) {
            startDrawable.setAlpha(this.mMaxAlpha);
        }
        if (alpha > 0) {
            endDrawable.setAlpha(alpha);
            endDrawable.draw(canvas);
            endDrawable.setAlpha(this.mMaxAlpha);
        }
        invalidateSelf();
    }
}
