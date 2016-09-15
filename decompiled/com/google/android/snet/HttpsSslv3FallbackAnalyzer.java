package com.google.android.snet;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.ProxySelector;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.URI;
import java.security.SecureRandom;
import java.util.Collections;
import java.util.List;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLProtocolException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

public class HttpsSslv3FallbackAnalyzer {
    private static final int HTTP_PROXY_CONNECT_TIMEOUT_MILLIS = 10000;
    private static final int SERVER_CONNECT_TIMEOUT_MILLIS = 30000;
    private static final String SERVER_HOSTNAME = "www.google.com";
    private static final int SERVER_PORT = 443;
    private static final int TLS_HANDSHAKE_TIMEOUT_MILLIS = 30000;
    private final Context mContext;

    static class Result {
        public boolean initialConnectionAttempted;
        public boolean initialConnectionSucceeded;
        public boolean retryAttempted;
        public boolean retrySucceeded;
        public boolean retryUsedModernTls;

        Result() {
        }
    }

    HttpsSslv3FallbackAnalyzer(Context context) {
        this.mContext = context;
    }

    Result analyze() {
        NetworkInfo activeNetworkInfo = null;
        Result result = new Result();
        ConnectivityManager connectivityManager = (ConnectivityManager) this.mContext.getSystemService("connectivity");
        if (connectivityManager != null) {
            activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        }
        if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
            try {
                SSLContext sslContext = SSLContext.getInstance("TLS");
                sslContext.init(null, null, null);
                SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
                try {
                    result.initialConnectionAttempted = true;
                    performTlsHandshake(sslSocketFactory, SERVER_HOSTNAME, SERVER_PORT, true);
                    result.initialConnectionSucceeded = true;
                } catch (SSLHandshakeException e) {
                    result.retryAttempted = true;
                    result.retryUsedModernTls = new SecureRandom().nextBoolean();
                    try {
                        performTlsHandshake(sslSocketFactory, SERVER_HOSTNAME, SERVER_PORT, result.retryUsedModernTls);
                        result.retrySucceeded = true;
                    } catch (IOException e2) {
                    }
                } catch (SSLProtocolException e3) {
                    result.retryAttempted = true;
                    result.retryUsedModernTls = new SecureRandom().nextBoolean();
                    performTlsHandshake(sslSocketFactory, SERVER_HOSTNAME, SERVER_PORT, result.retryUsedModernTls);
                    result.retrySucceeded = true;
                } catch (IOException e4) {
                }
            } catch (Exception e5) {
            }
        }
        return result;
    }

    private static void performTlsHandshake(SSLSocketFactory sslSocketFactory, String host, int port, boolean modernTls) throws IOException {
        Socket socket = null;
        SSLSocket sslSocket = null;
        try {
            socket = connectSocket(host, port);
            socket.setSoTimeout(TLS_HANDSHAKE_TIMEOUT_MILLIS);
            sslSocket = (SSLSocket) sslSocketFactory.createSocket(socket, host, port, false);
            if (modernTls) {
                potentiallyEnableSni(sslSocket, host);
                potentiallyEnableSessionTickets(sslSocket);
            } else {
                sslSocket.setEnabledProtocols(new String[]{"SSLv3"});
            }
            sslSocket.startHandshake();
            SSLSession sslSession = sslSocket.getSession();
            if (!sslSession.isValid()) {
                throw new SSLHandshakeException("Failed to obtain a valid SSLSession");
            } else if (HttpsURLConnection.getDefaultHostnameVerifier().verify(host, sslSession)) {
                if (sslSocket != null) {
                    closeQuietly(sslSocket);
                }
                if (socket != null) {
                    closeQuietly(socket);
                }
            } else {
                throw new SSLPeerUnverifiedException("Server identity does not match hostname: " + host);
            }
        } catch (Throwable th) {
            if (sslSocket != null) {
                closeQuietly(sslSocket);
            }
            if (socket != null) {
                closeQuietly(socket);
            }
        }
    }

    private static void potentiallyEnableSni(SSLSocket sslSocket, String hostname) {
        try {
            sslSocket.getClass().getMethod("setHostname", new Class[]{String.class}).invoke(sslSocket, new Object[]{hostname});
        } catch (Exception e) {
        }
    }

    private static void potentiallyEnableSessionTickets(SSLSocket sslSocket) {
        try {
            sslSocket.getClass().getMethod("setUseSessionTickets", new Class[]{Boolean.TYPE}).invoke(sslSocket, new Object[]{Boolean.valueOf(true)});
        } catch (Exception e) {
        }
    }

    private static Socket connectSocket(String host, int port) throws IOException {
        URI serverUri = URI.create("https://" + host + ":" + port);
        ProxySelector proxySelector = ProxySelector.getDefault();
        List<Proxy> proxies = proxySelector.select(serverUri);
        if (proxies == null || proxies.isEmpty()) {
            proxies = Collections.singletonList(Proxy.NO_PROXY);
        }
        IOException lastFailure = null;
        for (Proxy proxy : proxies) {
            SocketAddress proxyAddress = proxy.address();
            try {
                if (Proxy.NO_PROXY.equals(proxy)) {
                    return connectSocketNoProxy(host, port);
                }
                if (proxy.type() == Type.HTTP) {
                    return connectSocketViaHttpProxyConnectMethod(host, port, proxyAddress);
                }
            } catch (IOException e) {
                lastFailure = e;
                if (proxyAddress != null) {
                    proxySelector.connectFailed(serverUri, proxyAddress, e);
                }
            }
        }
        if (lastFailure != null) {
            throw lastFailure;
        }
        throw new IOException("No suitable connection methods found for " + serverUri + ": " + proxies);
    }

    private static Socket connectSocketNoProxy(String host, int port) throws IOException {
        Throwable th;
        Socket socket = new Socket();
        try {
            SocketAddress address = new InetSocketAddress(host, port);
            Socket socket2 = new Socket();
            try {
                socket2.connect(address, TLS_HANDSHAKE_TIMEOUT_MILLIS);
                if (!true) {
                    closeQuietly(socket2);
                }
                return socket2;
            } catch (Throwable th2) {
                th = th2;
                socket = socket2;
                if (!false) {
                    closeQuietly(socket);
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            if (false) {
                closeQuietly(socket);
            }
            throw th;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.net.Socket connectSocketViaHttpProxyConnectMethod(java.lang.String r18, int r19, java.net.SocketAddress r20) throws java.io.IOException {
        /*
        r9 = new java.net.Socket;
        r9.<init>();
        r13 = 0;
        r0 = r20;
        r0 = (java.net.InetSocketAddress) r0;	 Catch:{ all -> 0x016a }
        r5 = r0;
        r15 = r5.isUnresolved();	 Catch:{ all -> 0x016a }
        if (r15 == 0) goto L_0x0021;
    L_0x0011:
        r6 = new java.net.InetSocketAddress;	 Catch:{ all -> 0x016a }
        r15 = r5.getHostName();	 Catch:{ all -> 0x016a }
        r16 = r5.getPort();	 Catch:{ all -> 0x016a }
        r0 = r16;
        r6.<init>(r15, r0);	 Catch:{ all -> 0x016a }
        r5 = r6;
    L_0x0021:
        r10 = new java.net.Socket;	 Catch:{ all -> 0x016a }
        r10.<init>();	 Catch:{ all -> 0x016a }
        r15 = 10000; // 0x2710 float:1.4013E-41 double:4.9407E-320;
        r10.connect(r5, r15);	 Catch:{ all -> 0x00e2 }
        r4 = new java.io.BufferedWriter;	 Catch:{ all -> 0x00e2 }
        r15 = new java.io.OutputStreamWriter;	 Catch:{ all -> 0x00e2 }
        r16 = r10.getOutputStream();	 Catch:{ all -> 0x00e2 }
        r17 = "US-ASCII";
        r15.<init>(r16, r17);	 Catch:{ all -> 0x00e2 }
        r4.<init>(r15);	 Catch:{ all -> 0x00e2 }
        r15 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00e2 }
        r15.<init>();	 Catch:{ all -> 0x00e2 }
        r16 = "CONNECT ";
        r15 = r15.append(r16);	 Catch:{ all -> 0x00e2 }
        r0 = r18;
        r15 = r15.append(r0);	 Catch:{ all -> 0x00e2 }
        r16 = ":";
        r15 = r15.append(r16);	 Catch:{ all -> 0x00e2 }
        r0 = r19;
        r15 = r15.append(r0);	 Catch:{ all -> 0x00e2 }
        r16 = " HTTP/1.1\r\n";
        r15 = r15.append(r16);	 Catch:{ all -> 0x00e2 }
        r15 = r15.toString();	 Catch:{ all -> 0x00e2 }
        r4.write(r15);	 Catch:{ all -> 0x00e2 }
        r15 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00e2 }
        r15.<init>();	 Catch:{ all -> 0x00e2 }
        r16 = "Host: ";
        r15 = r15.append(r16);	 Catch:{ all -> 0x00e2 }
        r0 = r18;
        r15 = r15.append(r0);	 Catch:{ all -> 0x00e2 }
        r16 = ":";
        r15 = r15.append(r16);	 Catch:{ all -> 0x00e2 }
        r0 = r19;
        r15 = r15.append(r0);	 Catch:{ all -> 0x00e2 }
        r16 = "\r\n";
        r15 = r15.append(r16);	 Catch:{ all -> 0x00e2 }
        r15 = r15.toString();	 Catch:{ all -> 0x00e2 }
        r4.write(r15);	 Catch:{ all -> 0x00e2 }
        r15 = "\r\n";
        r4.write(r15);	 Catch:{ all -> 0x00e2 }
        r4.flush();	 Catch:{ all -> 0x00e2 }
        r2 = new java.io.BufferedReader;	 Catch:{ all -> 0x00e2 }
        r15 = new java.io.InputStreamReader;	 Catch:{ all -> 0x00e2 }
        r16 = r10.getInputStream();	 Catch:{ all -> 0x00e2 }
        r17 = "US-ASCII";
        r15.<init>(r16, r17);	 Catch:{ all -> 0x00e2 }
        r2.<init>(r15);	 Catch:{ all -> 0x00e2 }
        r12 = 0;
        r8 = 0;
        r15 = 30000; // 0x7530 float:4.2039E-41 double:1.4822E-319;
        r10.setSoTimeout(r15);	 Catch:{ all -> 0x00e2 }
    L_0x00ae:
        r3 = r2.readLine();	 Catch:{ all -> 0x00e2 }
        if (r3 == 0) goto L_0x014f;
    L_0x00b4:
        if (r12 != 0) goto L_0x0148;
    L_0x00b6:
        r15 = "\\s+";
        r16 = 3;
        r0 = r16;
        r14 = r3.split(r15, r0);	 Catch:{ all -> 0x00e2 }
        r15 = r14.length;	 Catch:{ all -> 0x00e2 }
        r16 = 3;
        r0 = r16;
        if (r15 == r0) goto L_0x00ea;
    L_0x00c7:
        r15 = new java.io.IOException;	 Catch:{ all -> 0x00e2 }
        r16 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00e2 }
        r16.<init>();	 Catch:{ all -> 0x00e2 }
        r17 = "Unexpected reply from HTTP proxy: ";
        r16 = r16.append(r17);	 Catch:{ all -> 0x00e2 }
        r0 = r16;
        r16 = r0.append(r3);	 Catch:{ all -> 0x00e2 }
        r16 = r16.toString();	 Catch:{ all -> 0x00e2 }
        r15.<init>(r16);	 Catch:{ all -> 0x00e2 }
        throw r15;	 Catch:{ all -> 0x00e2 }
    L_0x00e2:
        r15 = move-exception;
        r9 = r10;
    L_0x00e4:
        if (r13 != 0) goto L_0x00e9;
    L_0x00e6:
        closeQuietly(r9);
    L_0x00e9:
        throw r15;
    L_0x00ea:
        r15 = 0;
        r1 = r14[r15];	 Catch:{ all -> 0x00e2 }
        r15 = 1;
        r11 = r14[r15];	 Catch:{ all -> 0x00e2 }
        r15 = 2;
        r7 = r14[r15];	 Catch:{ all -> 0x00e2 }
        r15 = "HTTP/1.";
        r15 = r1.startsWith(r15);	 Catch:{ all -> 0x00e2 }
        if (r15 != 0) goto L_0x0116;
    L_0x00fb:
        r15 = new java.io.IOException;	 Catch:{ all -> 0x00e2 }
        r16 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00e2 }
        r16.<init>();	 Catch:{ all -> 0x00e2 }
        r17 = "Unsupported HTTP version in HTTP proxy response: ";
        r16 = r16.append(r17);	 Catch:{ all -> 0x00e2 }
        r0 = r16;
        r16 = r0.append(r3);	 Catch:{ all -> 0x00e2 }
        r16 = r16.toString();	 Catch:{ all -> 0x00e2 }
        r15.<init>(r16);	 Catch:{ all -> 0x00e2 }
        throw r15;	 Catch:{ all -> 0x00e2 }
    L_0x0116:
        r15 = "200";
        r15 = r15.equals(r11);	 Catch:{ all -> 0x00e2 }
        if (r15 != 0) goto L_0x0145;
    L_0x011e:
        r15 = new java.io.IOException;	 Catch:{ all -> 0x00e2 }
        r16 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00e2 }
        r16.<init>();	 Catch:{ all -> 0x00e2 }
        r17 = "HTTP proxy CONNECT failed. Status: ";
        r16 = r16.append(r17);	 Catch:{ all -> 0x00e2 }
        r0 = r16;
        r16 = r0.append(r11);	 Catch:{ all -> 0x00e2 }
        r17 = ", reason: ";
        r16 = r16.append(r17);	 Catch:{ all -> 0x00e2 }
        r0 = r16;
        r16 = r0.append(r7);	 Catch:{ all -> 0x00e2 }
        r16 = r16.toString();	 Catch:{ all -> 0x00e2 }
        r15.<init>(r16);	 Catch:{ all -> 0x00e2 }
        throw r15;	 Catch:{ all -> 0x00e2 }
    L_0x0145:
        r12 = 1;
        goto L_0x00ae;
    L_0x0148:
        r15 = r3.length();	 Catch:{ all -> 0x00e2 }
        if (r15 != 0) goto L_0x00ae;
    L_0x014e:
        r8 = 1;
    L_0x014f:
        if (r12 != 0) goto L_0x0159;
    L_0x0151:
        r15 = new java.io.EOFException;	 Catch:{ all -> 0x00e2 }
        r16 = "Empty response from HTTP proxy";
        r15.<init>(r16);	 Catch:{ all -> 0x00e2 }
        throw r15;	 Catch:{ all -> 0x00e2 }
    L_0x0159:
        if (r8 != 0) goto L_0x0163;
    L_0x015b:
        r15 = new java.io.EOFException;	 Catch:{ all -> 0x00e2 }
        r16 = "Premature end of stream while reading HTTP proxy response";
        r15.<init>(r16);	 Catch:{ all -> 0x00e2 }
        throw r15;	 Catch:{ all -> 0x00e2 }
    L_0x0163:
        r13 = 1;
        if (r13 != 0) goto L_0x0169;
    L_0x0166:
        closeQuietly(r10);
    L_0x0169:
        return r10;
    L_0x016a:
        r15 = move-exception;
        goto L_0x00e4;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.snet.HttpsSslv3FallbackAnalyzer.connectSocketViaHttpProxyConnectMethod(java.lang.String, int, java.net.SocketAddress):java.net.Socket");
    }

    private static void closeQuietly(Socket socket) {
        if (socket != null) {
            try {
                socket.close();
            } catch (IOException e) {
            }
        }
    }
}
