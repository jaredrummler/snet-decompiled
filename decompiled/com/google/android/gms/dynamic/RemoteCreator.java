package com.google.android.gms.dynamic;

import android.content.Context;
import android.os.IBinder;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import com.google.android.gms.common.internal.Preconditions;

public abstract class RemoteCreator<T> {
    private final String mCreatorClassName;
    private T mCreatorInstance;

    public static class RemoteCreatorException extends Exception {
        public RemoteCreatorException(String message) {
            super(message);
        }

        public RemoteCreatorException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    protected abstract T getRemoteCreator(IBinder iBinder);

    protected RemoteCreator(String creatorClassName) {
        this.mCreatorClassName = creatorClassName;
    }

    protected final T getRemoteCreatorInstance(Context context) throws RemoteCreatorException {
        if (this.mCreatorInstance == null) {
            Preconditions.checkNotNull(context);
            Context remoteContext = GooglePlayServicesUtilLight.getRemoteContext(context);
            if (remoteContext == null) {
                throw new RemoteCreatorException("Could not get remote context.");
            }
            try {
                this.mCreatorInstance = getRemoteCreator((IBinder) remoteContext.getClassLoader().loadClass(this.mCreatorClassName).newInstance());
            } catch (ClassNotFoundException e) {
                throw new RemoteCreatorException("Could not load creator class.", e);
            } catch (InstantiationException e2) {
                throw new RemoteCreatorException("Could not instantiate creator.", e2);
            } catch (IllegalAccessException e3) {
                throw new RemoteCreatorException("Could not access creator.", e3);
            }
        }
        return this.mCreatorInstance;
    }
}
