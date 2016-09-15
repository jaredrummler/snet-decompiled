package com.google.android.gms.common.internal;

import android.content.Context;
import android.os.IBinder;
import android.view.View;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.ISignInButtonCreator.Stub;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamic.RemoteCreator;
import com.google.android.gms.dynamic.RemoteCreator.RemoteCreatorException;

public final class SignInButtonCreator extends RemoteCreator<ISignInButtonCreator> {
    private static final String CREATOR_NAME = "com.google.android.gms.common.ui.SignInButtonCreatorImpl";
    private static final SignInButtonCreator sInstance;

    static {
        sInstance = new SignInButtonCreator();
    }

    private SignInButtonCreator() {
        super(CREATOR_NAME);
    }

    public static View createView(Context context, int size, int color, Scope[] scopes) throws RemoteCreatorException {
        return sInstance.newSignInButton(context, size, color, scopes);
    }

    private View newSignInButton(Context context, int size, int color, Scope[] scopes) throws RemoteCreatorException {
        try {
            SignInButtonConfig config = new SignInButtonConfig(size, color, scopes);
            return (View) ObjectWrapper.unwrap(((ISignInButtonCreator) getRemoteCreatorInstance(context)).newSignInButtonFromConfig(ObjectWrapper.wrap(context), config));
        } catch (Exception e) {
            throw new RemoteCreatorException("Could not get button with size " + size + " and color " + color, e);
        }
    }

    public ISignInButtonCreator getRemoteCreator(IBinder binder) {
        return Stub.asInterface(binder);
    }
}
