package com.google.android.snet;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.google.android.snet.FileFinder.FilesInfo;
import com.google.android.snet.IdleLogs.SicFileInfo;
import com.google.android.snet.IdleLogs.SystemIntegrityRequest;
import com.google.android.snet.IdleLogs.SystemIntegrityResponse;
import com.google.protobuf.nano.InvalidProtocolBufferNanoException;
import com.google.protobuf.nano.MessageNano;
import java.util.ArrayList;
import java.util.List;

class SystemIntegrityChecker {
    private static final int CONNECTION_TIMEOUT_MS = 30000;
    private static final int READ_TIMEOUT_MS = 30000;
    private static final String TAG;
    private final Context mContext;
    private final SnetFilesDataStore mDataStore;
    private final GBundle mGBundle;

    static {
        TAG = SystemIntegrityChecker.class.getCanonicalName();
    }

    SystemIntegrityChecker(Context context, GBundle gBundle) {
        this.mContext = context;
        this.mGBundle = gBundle;
        this.mDataStore = new SnetFilesDataStore(this.mContext);
    }

    int systemPartitionState() {
        List paths = new ArrayList();
        paths.add("/system");
        List<FilesInfo> filesInfoToQuery = this.mDataStore.get(paths);
        if (filesInfoToQuery == null || filesInfoToQuery.isEmpty()) {
            return 0;
        }
        byte[] topLevelHash = ((FilesInfo) filesInfoToQuery.get(0)).sha256;
        if (topLevelHash == null) {
            return 0;
        }
        SystemIntegrityResponse responseProto = request(topLevelHash, filesInfoToQuery, null);
        if (responseProto == null || responseProto.hashMatches == null || responseProto.hashMatches.length == 0 || responseProto.newBuildString) {
            return 0;
        }
        return responseProto.hashMatches[0];
    }

    private SystemIntegrityResponse request(byte[] topLevelHash, List<FilesInfo> filesInfoToQuery, byte[] sessionToken) {
        SystemIntegrityRequest requestProto = getRequestProto(topLevelHash, filesInfoToQuery, sessionToken);
        if (requestProto == null) {
            return null;
        }
        return httpRequest(requestProto);
    }

    private SystemIntegrityRequest getRequestProto(byte[] topLevelHash, List<FilesInfo> filesInfoToQuery, byte[] sessionToken) {
        if (topLevelHash == null || filesInfoToQuery == null || filesInfoToQuery.isEmpty()) {
            return null;
        }
        SystemIntegrityRequest requestProto = new SystemIntegrityRequest();
        requestProto.buildString = Build.FINGERPRINT;
        requestProto.topLevelHash = topLevelHash;
        if (sessionToken != null) {
            requestProto.sessionToken = sessionToken;
        }
        requestProto.nodes = new SicFileInfo[filesInfoToQuery.size()];
        for (int i = 0; i < filesInfoToQuery.size(); i++) {
            FilesInfo filesInfo = (FilesInfo) filesInfoToQuery.get(i);
            requestProto.nodes[i] = new SicFileInfo();
            if (filesInfo.filename != null) {
                requestProto.nodes[i].path = filesInfo.filename;
            }
            if (filesInfo.sha256 != null) {
                requestProto.nodes[i].sha256 = filesInfo.sha256;
            }
            requestProto.nodes[i].symlink = filesInfo.symlink;
            if (!TextUtils.isEmpty(filesInfo.symlinkTarget)) {
                requestProto.nodes[i].symlinkTarget = filesInfo.symlinkTarget;
            }
            if (filesInfo.lstatStruct != null) {
                requestProto.nodes[i].permissions = filesInfo.lstatStruct.mode;
                requestProto.nodes[i].fileOwner = filesInfo.lstatStruct.uid;
                requestProto.nodes[i].fileGroup = filesInfo.lstatStruct.gid;
                if (filesInfo.lstatStruct.seLinuxSecurityContext != null) {
                    requestProto.nodes[i].seLinuxSecurityContext = filesInfo.lstatStruct.seLinuxSecurityContext;
                }
            }
        }
        return requestProto;
    }

    SystemIntegrityResponse httpRequest(SystemIntegrityRequest requestProto) {
        SystemIntegrityResponse systemIntegrityResponse = null;
        byte[] responseBytes = Utils.httpRequest(this.mGBundle.getSicServerUrl(), MessageNano.toByteArray(requestProto), READ_TIMEOUT_MS, READ_TIMEOUT_MS);
        if (responseBytes != null) {
            try {
                systemIntegrityResponse = SystemIntegrityResponse.parseFrom(responseBytes);
            } catch (InvalidProtocolBufferNanoException e) {
            }
        }
        return systemIntegrityResponse;
    }
}
