package com.google.android.gms.auth.firstparty.delegate;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.auth.firstparty.delegate.IAccountSetupWorkflowService.Stub;
import com.google.android.gms.auth.firstparty.shared.BlockingServiceClient;

public class AccountSetupWorkflowServiceClient extends BlockingServiceClient {
    private static final String ACTION = "com.google.android.gms.auth.setup.workflow.SETUP_WORKFLOW";

    /* renamed from: com.google.android.gms.auth.firstparty.delegate.AccountSetupWorkflowServiceClient.1 */
    class AnonymousClass1 implements Call<PendingIntent> {
        final /* synthetic */ SetupAccountWorkflowRequest val$req;

        AnonymousClass1(SetupAccountWorkflowRequest setupAccountWorkflowRequest) {
            this.val$req = setupAccountWorkflowRequest;
        }

        public PendingIntent exec(IBinder binder) throws RemoteException {
            return Stub.asInterface(binder).getAccountSetupWorkflowIntent(this.val$req);
        }
    }

    public AccountSetupWorkflowServiceClient(Context context) {
        super(context);
    }

    public PendingIntent getAccountSetupWorkflowIntent(SetupAccountWorkflowRequest req) {
        return (PendingIntent) exec(new AnonymousClass1(req));
    }

    protected Intent getServiceIntent() {
        return new Intent().setAction(ACTION).addCategory("android.intent.category.DEFAULT");
    }
}
