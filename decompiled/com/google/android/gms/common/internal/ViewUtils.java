package com.google.android.gms.common.internal;

import android.content.Context;
import android.content.res.Resources.NotFoundException;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;

public class ViewUtils {
    private ViewUtils() {
    }

    public static String getXmlAttributeString(String namespace, String name, Context context, AttributeSet attrs, boolean allowResources, boolean required, String logTag) {
        String attributeValue = attrs == null ? null : attrs.getAttributeValue(namespace, name);
        if (attributeValue != null && attributeValue.startsWith("@string/") && allowResources) {
            String resourceName = attributeValue.substring("@string/".length());
            String packageName = context.getPackageName();
            TypedValue testValue = new TypedValue();
            try {
                context.getResources().getValue(packageName + ":string/" + resourceName, testValue, true);
            } catch (NotFoundException e) {
                Log.w(logTag, "Could not find resource for " + name + ": " + attributeValue);
            }
            if (testValue.string != null) {
                attributeValue = testValue.string.toString();
            } else {
                Log.w(logTag, "Resource " + name + " was not a string: " + testValue);
            }
        }
        if (required && attributeValue == null) {
            Log.w(logTag, "Required XML attribute \"" + name + "\" missing");
        }
        return attributeValue;
    }
}
