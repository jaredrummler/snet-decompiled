package com.google.android.gms.common.util;

import android.text.TextUtils;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public final class ScopeUtil {
    private ScopeUtil() {
    }

    public static Set<Scope> fromScopeString(Collection<String> scopeStrings) {
        Preconditions.checkNotNull(scopeStrings, "scopeStrings can't be null.");
        return fromScopeString((String[]) scopeStrings.toArray(new String[scopeStrings.size()]));
    }

    public static Set<Scope> fromScopeString(String... scopeStrings) {
        Preconditions.checkNotNull(scopeStrings, "scopeStrings can't be null.");
        Set<Scope> scopeSet = new HashSet(scopeStrings.length);
        for (String scopeString : scopeStrings) {
            if (!TextUtils.isEmpty(scopeString)) {
                scopeSet.add(new Scope(scopeString));
            }
        }
        return scopeSet;
    }

    public static String[] toScopeString(Scope[] scopes) {
        Preconditions.checkNotNull(scopes, "scopes can't be null.");
        String[] scopeStrings = new String[scopes.length];
        for (int i = 0; i < scopes.length; i++) {
            scopeStrings[i] = scopes[i].getScopeUri();
        }
        return scopeStrings;
    }

    public static String[] toScopeString(Set<Scope> scopes) {
        Preconditions.checkNotNull(scopes, "scopes can't be null.");
        return toScopeString((Scope[]) scopes.toArray(new Scope[scopes.size()]));
    }
}
