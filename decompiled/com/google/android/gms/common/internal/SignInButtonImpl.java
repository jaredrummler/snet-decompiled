package com.google.android.gms.common.internal;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.Button;
import com.google.android.gms.R;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.Scope;
import com.google.wireless.android.play.playlog.proto.UserLocation.LaunchSettingsEvent.Action;
import com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection.Type;

public final class SignInButtonImpl extends Button {
    private static final float MIN_HEIGHT_DP = 48.0f;
    private static final float MIN_WIDTH_DP = 48.0f;
    private static final float TEXT_SIZE_SP = 14.0f;

    public SignInButtonImpl(Context context) {
        this(context, null);
    }

    public SignInButtonImpl(Context context, AttributeSet attrs) {
        super(context, attrs, 16842824);
    }

    public void configure(Resources resources, SignInButtonConfig config) {
        configure(resources, config.getButtonSize(), config.getColorScheme(), config.getScopes());
    }

    public void configure(Resources resources, int buttonSize, int colorScheme, Scope[] scopes) {
        boolean hasPlusScope = hasPlusScope(scopes);
        setProperties(resources);
        setBackground(resources, buttonSize, colorScheme, hasPlusScope);
        setText(resources, buttonSize, colorScheme, hasPlusScope);
    }

    private void setProperties(Resources gmsResources) {
        setTypeface(Typeface.DEFAULT_BOLD);
        setTextSize(TEXT_SIZE_SP);
        float scale = gmsResources.getDisplayMetrics().density;
        setMinHeight((int) ((scale * MIN_WIDTH_DP) + 0.5f));
        setMinWidth((int) ((scale * MIN_WIDTH_DP) + 0.5f));
    }

    private void setBackground(Resources gmsResources, int buttonSize, int colorScheme, boolean hasPlusScope) {
        int backgroundResId;
        if (hasPlusScope) {
            backgroundResId = getResIdByButtonSize(buttonSize, getResIdByColorScheme(colorScheme, R.drawable.common_plus_signin_btn_icon_dark, R.drawable.common_plus_signin_btn_icon_light, R.drawable.common_plus_signin_btn_icon_dark), getResIdByColorScheme(colorScheme, R.drawable.common_plus_signin_btn_text_dark, R.drawable.common_plus_signin_btn_text_light, R.drawable.common_plus_signin_btn_text_dark));
        } else {
            backgroundResId = getResIdByButtonSize(buttonSize, getResIdByColorScheme(colorScheme, R.drawable.common_google_signin_btn_icon_dark, R.drawable.common_google_signin_btn_icon_light, R.drawable.common_google_signin_btn_icon_light), getResIdByColorScheme(colorScheme, R.drawable.common_google_signin_btn_text_dark, R.drawable.common_google_signin_btn_text_light, R.drawable.common_google_signin_btn_text_light));
        }
        setBackgroundDrawable(gmsResources.getDrawable(backgroundResId));
    }

    private void setText(Resources resources, int buttonSize, int colorScheme, boolean hasPlusScope) {
        int colorResId;
        if (hasPlusScope) {
            colorResId = getResIdByColorScheme(colorScheme, R.color.common_plus_signin_btn_text_dark, R.color.common_plus_signin_btn_text_light, R.color.common_plus_signin_btn_text_dark);
        } else {
            colorResId = getResIdByColorScheme(colorScheme, R.color.common_google_signin_btn_text_dark, R.color.common_google_signin_btn_text_light, R.color.common_google_signin_btn_text_light);
        }
        setTextColor((ColorStateList) Preconditions.checkNotNull(resources.getColorStateList(colorResId)));
        switch (buttonSize) {
            case Action.UNKNOWN /*0*/:
                setText(resources.getString(R.string.common_signin_button_text));
                break;
            case Type.TEMPORARY /*1*/:
                setText(resources.getString(R.string.common_signin_button_text_long));
                break;
            case Type.INDEFINITELY /*2*/:
                setText(null);
                break;
            default:
                throw new IllegalStateException("Unknown button size: " + buttonSize);
        }
        setTransformationMethod(null);
    }

    private int getResIdByButtonSize(int buttonSize, int iconResId, int textResId) {
        switch (buttonSize) {
            case Action.UNKNOWN /*0*/:
            case Type.TEMPORARY /*1*/:
                return textResId;
            case Type.INDEFINITELY /*2*/:
                return iconResId;
            default:
                throw new IllegalStateException("Unknown button size: " + buttonSize);
        }
    }

    private int getResIdByColorScheme(int colorScheme, int darkResId, int lightResId, int defaultResId) {
        switch (colorScheme) {
            case Action.UNKNOWN /*0*/:
                return darkResId;
            case Type.TEMPORARY /*1*/:
                return lightResId;
            case Type.INDEFINITELY /*2*/:
                return defaultResId;
            default:
                throw new IllegalStateException("Unknown color scheme: " + colorScheme);
        }
    }

    private boolean hasPlusScope(Scope[] scopes) {
        if (scopes == null) {
            return false;
        }
        for (Scope scope : scopes) {
            String scopeUri = scope.getScopeUri();
            if (scopeUri.contains("/plus.") && !scopeUri.equals(Scopes.PLUS_ME)) {
                return true;
            }
            if (scopeUri.equals(Scopes.GAMES)) {
                return true;
            }
        }
        return false;
    }
}
