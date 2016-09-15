package com.google.android.gms.common.util;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class InputMethodUtils {
    private InputMethodUtils() {
    }

    private static InputMethodManager getInputMethodManager(Context context) {
        return (InputMethodManager) context.getSystemService("input_method");
    }

    public static boolean showSoftInput(Context context, View view) {
        InputMethodManager imm = getInputMethodManager(context);
        if (imm == null) {
            return false;
        }
        imm.showSoftInput(view, 0);
        return true;
    }

    public static boolean hideSoftInput(Context context, View view) {
        InputMethodManager imm = getInputMethodManager(context);
        if (imm == null) {
            return false;
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        return true;
    }

    public static void restart(Context context, View view) {
        InputMethodManager imm = getInputMethodManager(context);
        if (imm != null) {
            imm.restartInput(view);
        }
    }

    public static boolean isAcceptingText(Context context) {
        InputMethodManager imm = getInputMethodManager(context);
        if (imm != null) {
            return imm.isAcceptingText();
        }
        return false;
    }
}
