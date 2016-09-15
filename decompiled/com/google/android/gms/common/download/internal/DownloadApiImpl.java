package com.google.android.gms.common.download.internal;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl;
import com.google.android.gms.common.download.Download;
import com.google.android.gms.common.download.DownloadApi;
import com.google.android.gms.common.download.DownloadDetails;

public class DownloadApiImpl implements DownloadApi {

    public static abstract class DownloadApiMethod extends ApiMethodImpl<Status, DownloadServiceClientImpl> {
        protected abstract void doExecute(Context context, IDownloadService iDownloadService) throws RemoteException;

        public DownloadApiMethod(GoogleApiClient client) {
            super(Download.CLIENT_KEY, client);
        }

        protected final void doExecute(DownloadServiceClientImpl clientImpl) throws RemoteException {
            doExecute(clientImpl.getContext(), (IDownloadService) clientImpl.getService());
        }

        protected Status createFailedResult(Status status) {
            return status;
        }
    }

    /* renamed from: com.google.android.gms.common.download.internal.DownloadApiImpl.1 */
    class AnonymousClass1 extends DownloadApiMethod {
        final /* synthetic */ String val$filename;

        AnonymousClass1(GoogleApiClient x0, String str) {
            this.val$filename = str;
            super(x0);
        }

        protected void doExecute(Context context, IDownloadService service) throws RemoteException {
            service.getDownloadStatus(new AbstractDownloadServiceCallbacks() {
                public void onDownloadStatus(Status status) throws RemoteException {
                    AnonymousClass1.this.setResult(status);
                }
            }, this.val$filename);
        }
    }

    /* renamed from: com.google.android.gms.common.download.internal.DownloadApiImpl.2 */
    class AnonymousClass2 extends DownloadApiMethod {
        final /* synthetic */ String val$filename;

        AnonymousClass2(GoogleApiClient x0, String str) {
            this.val$filename = str;
            super(x0);
        }

        protected void doExecute(Context context, IDownloadService service) throws RemoteException {
            service.tryDownload(new AbstractDownloadServiceCallbacks() {
                public void onDownloadStatus(Status status) throws RemoteException {
                    AnonymousClass2.this.setResult(status);
                }
            }, this.val$filename);
        }
    }

    /* renamed from: com.google.android.gms.common.download.internal.DownloadApiImpl.3 */
    class AnonymousClass3 extends DownloadApiMethod {
        final /* synthetic */ String val$filename;

        AnonymousClass3(GoogleApiClient x0, String str) {
            this.val$filename = str;
            super(x0);
        }

        protected void doExecute(Context context, IDownloadService service) throws RemoteException {
            service.registerDownloadStatusUpdates(new AbstractDownloadServiceCallbacks() {
                public void onRegisterStatusUpdates(Status status) throws RemoteException {
                    AnonymousClass3.this.setResult(status);
                }
            }, this.val$filename);
        }
    }

    /* renamed from: com.google.android.gms.common.download.internal.DownloadApiImpl.4 */
    class AnonymousClass4 extends DownloadApiMethod {
        final /* synthetic */ DownloadDetails val$details;

        AnonymousClass4(GoogleApiClient x0, DownloadDetails downloadDetails) {
            this.val$details = downloadDetails;
            super(x0);
        }

        protected void doExecute(Context context, IDownloadService service) throws RemoteException {
            service.registerDownload(new AbstractDownloadServiceCallbacks() {
                public void onDownloadStatus(Status status) throws RemoteException {
                    AnonymousClass4.this.setResult(status);
                }
            }, this.val$details);
        }
    }

    /* renamed from: com.google.android.gms.common.download.internal.DownloadApiImpl.5 */
    class AnonymousClass5 extends DownloadApiMethod {
        final /* synthetic */ String val$filename;

        AnonymousClass5(GoogleApiClient x0, String str) {
            this.val$filename = str;
            super(x0);
        }

        protected void doExecute(Context context, IDownloadService service) throws RemoteException {
            service.unregisterDownload(new AbstractDownloadServiceCallbacks() {
                public void onDownloadStatus(Status status) throws RemoteException {
                    AnonymousClass5.this.setResult(status);
                }
            }, this.val$filename);
        }
    }

    public PendingResult<Status> getDownloadStatus(GoogleApiClient client, String filename) {
        return client.enqueue(new AnonymousClass1(client, filename));
    }

    public PendingResult<Status> tryDownload(GoogleApiClient client, String filename) {
        return client.execute(new AnonymousClass2(client, filename));
    }

    public PendingResult<Status> registerDownloadStatusUpdates(GoogleApiClient client, String filename) {
        return client.execute(new AnonymousClass3(client, filename));
    }

    public PendingResult<Status> registerDownload(GoogleApiClient client, DownloadDetails details) {
        return client.execute(new AnonymousClass4(client, details));
    }

    public PendingResult<Status> unregisterDownload(GoogleApiClient client, String filename) {
        return client.execute(new AnonymousClass5(client, filename));
    }
}
