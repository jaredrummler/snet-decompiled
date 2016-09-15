package com.google.android.gms.common;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;
import com.google.android.gms.R;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.SignInButtonCreator;
import com.google.android.gms.common.internal.SignInButtonImpl;
import com.google.android.gms.dynamic.RemoteCreator.RemoteCreatorException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class SignInButton extends FrameLayout implements OnClickListener {
    public static final int COLOR_AUTO = 2;
    public static final int COLOR_DARK = 0;
    public static final int COLOR_LIGHT = 1;
    public static final int SIZE_ICON_ONLY = 2;
    public static final int SIZE_STANDARD = 0;
    public static final int SIZE_WIDE = 1;
    private static final String TAG = "SignInButton";
    private OnClickListener mClientListener;
    private int mColor;
    private View mDynamicView;
    private Scope[] mScopes;
    private int mSize;

    @Retention(RetentionPolicy.SOURCE)
    public @interface ButtonSize {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface ColorScheme {
    }

    public SignInButton(Context context) {
        this(context, null);
    }

    public SignInButton(Context context, AttributeSet attrs) {
        this(context, attrs, SIZE_STANDARD);
    }

    public SignInButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mClientListener = null;
        readAttributes(context, attrs);
        setStyle(this.mSize, this.mColor, this.mScopes);
    }

    private void readAttributes(Context context, AttributeSet attrs) {
        TypedArray attributes = context.getTheme().obtainStyledAttributes(attrs, R.styleable.SignInButton, SIZE_STANDARD, SIZE_STANDARD);
        try {
            this.mSize = attributes.getInt(R.styleable.SignInButton_buttonSize, SIZE_STANDARD);
            this.mColor = attributes.getInt(R.styleable.SignInButton_colorScheme, SIZE_ICON_ONLY);
            String scopesString = attributes.getString(R.styleable.SignInButton_scopeUris);
            if (scopesString == null) {
                this.mScopes = null;
            } else {
                String[] scopeUris = scopesString.trim().split("\\s+");
                this.mScopes = new Scope[scopeUris.length];
                for (int i = SIZE_STANDARD; i < scopeUris.length; i += SIZE_WIDE) {
                    this.mScopes[i] = new Scope(scopeUris[i].toString());
                }
            }
            attributes.recycle();
        } catch (Throwable th) {
            attributes.recycle();
        }
    }

    public void setSize(int buttonSize) {
        setStyle(buttonSize, this.mColor, this.mScopes);
    }

    public void setColorScheme(int colorScheme) {
        setStyle(this.mSize, colorScheme, this.mScopes);
    }

    public void setScopes(Scope[] scopes) {
        setStyle(this.mSize, this.mColor, scopes);
    }

    public void setStyle(int buttonSize, int colorScheme) {
        setStyle(buttonSize, colorScheme, this.mScopes);
    }

    public void setStyle(int buttonSize, int colorScheme, Scope[] scopes) {
        this.mSize = buttonSize;
        this.mColor = colorScheme;
        this.mScopes = scopes;
        createView(getContext());
    }

    private void createView(Context context) {
        if (this.mDynamicView != null) {
            removeView(this.mDynamicView);
        }
        try {
            this.mDynamicView = SignInButtonCreator.createView(context, this.mSize, this.mColor, this.mScopes);
        } catch (RemoteCreatorException e) {
            Log.w(TAG, "Sign in button not found, using placeholder instead");
            this.mDynamicView = getPlaceholder(context, this.mSize, this.mColor, this.mScopes);
        }
        addView(this.mDynamicView);
        this.mDynamicView.setEnabled(isEnabled());
        this.mDynamicView.setOnClickListener(this);
    }

    private static Button getPlaceholder(Context context, int size, int colorScheme, Scope[] scopes) {
        SignInButtonImpl button = new SignInButtonImpl(context);
        button.configure(context.getResources(), size, colorScheme, scopes);
        return button;
    }

    public void setOnClickListener(OnClickListener listener) {
        this.mClientListener = listener;
        if (this.mDynamicView != null) {
            this.mDynamicView.setOnClickListener(this);
        }
    }

    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        this.mDynamicView.setEnabled(enabled);
    }

    public void onClick(View view) {
        if (this.mClientListener != null && view == this.mDynamicView) {
            this.mClientListener.onClick(this);
        }
    }
}
