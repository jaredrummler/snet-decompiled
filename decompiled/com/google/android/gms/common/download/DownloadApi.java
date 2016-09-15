package com.google.android.gms.common.download;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;

public interface DownloadApi {
    PendingResult<Status> getDownloadStatus(GoogleApiClient googleApiClient, String str);

    PendingResult<Status> registerDownload(GoogleApiClient googleApiClient, DownloadDetails downloadDetails);

    PendingResult<Status> registerDownloadStatusUpdates(GoogleApiClient googleApiClient, String str);

    PendingResult<Status> tryDownload(GoogleApiClient googleApiClient, String str);

    PendingResult<Status> unregisterDownload(GoogleApiClient googleApiClient, String str);
}
