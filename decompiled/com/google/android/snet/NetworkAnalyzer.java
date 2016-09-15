package com.google.android.snet;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.http.AndroidHttpClient;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Pair;
import com.google.android.gms.lockbox.appusage.AppUsage.AppUsageEvent.Importance;
import com.google.android.gms.people.PeopleConstants.Endpoints;
import com.google.android.gms.people.PeopleConstants.PeoplePhone;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.HttpGet;

class NetworkAnalyzer {
    private static final int ANDROID_HTTP_CLIENT = 2;
    private static final String[] HOSTS_TO_TEST;
    private static final int HTTP_URL_CONNECTION_CLIENT = 1;
    private static final String[] KEY_DNS_ENTRIES;
    private static final String KEY_HTTP_HEADER_LOCATION = "Location";
    static final String KEY_USER_AGENT = "User-Agent";
    private static final String TAG;
    private static final String[] USER_AGENTS;
    private final Context mContext;
    private final SnetLogger mSnetLogger;

    public static class ConnectionInfo {
        public int activeConnectionType;
        public int availableNetworkTypes;
        public String[] dnsServers;
        public String operatorName;
    }

    public static class RedirectResults {
        public boolean endPointReached;
        public URL lastRequestUrl;

        public RedirectResults(boolean success, URL url) {
            this.endPointReached = success;
            this.lastRequestUrl = url;
        }
    }

    public static class SslRedirectInfo {
        public boolean endPointConnected;
        public String endPointUrl;
        public String host;
        public int httpClientUsed;
        public String[] ipAddressesForEndPointUrl;
        public String redirectHeaderValue;
        public int responseCode;
        public int userAgentUsed;
    }

    static {
        TAG = NetworkAnalyzer.class.getCanonicalName();
        HOSTS_TO_TEST = new String[]{"http://accounts.google.com", "http://www.google.com", "http://pubads.g.doubleclick.net"};
        String[] strArr = new String[ANDROID_HTTP_CLIENT];
        strArr[0] = "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/32.0.1700.102 Safari/537.36";
        strArr[HTTP_URL_CONNECTION_CLIENT] = "Mozilla/5.0 (iPhone; CPU iPhone OS 7_1_1 like Mac OS X) AppleWebKit/537.51.2 (KHTML, like Gecko) Version/7.0 Mobile/11D201 Safari/9537.53";
        USER_AGENTS = strArr;
        KEY_DNS_ENTRIES = new String[]{"net.dns1", "net.dns2", "net.dns3", "net.dns4"};
    }

    NetworkAnalyzer(Context context, SnetLogger snetLogger) {
        this.mContext = context;
        this.mSnetLogger = snetLogger;
    }

    ConnectionInfo connectionInfo() {
        ConnectivityManager connectivityManager = (ConnectivityManager) this.mContext.getSystemService("connectivity");
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        ConnectionInfo connectInfo = new ConnectionInfo();
        if (activeNetworkInfo != null) {
            connectInfo.activeConnectionType = activeNetworkInfo.getType();
        }
        if (connectInfo.activeConnectionType == 0) {
            connectInfo.operatorName = ((TelephonyManager) this.mContext.getSystemService(PeoplePhone.PHONE_NUMBER)).getNetworkOperatorName();
        }
        int capabilitiesBitField = 0;
        NetworkInfo[] arr$ = connectivityManager.getAllNetworkInfo();
        int len$ = arr$.length;
        for (int i$ = 0; i$ < len$; i$ += HTTP_URL_CONNECTION_CLIENT) {
            int type = arr$[i$].getType();
            if (type <= 30) {
                capabilitiesBitField |= HTTP_URL_CONNECTION_CLIENT << type;
            }
        }
        connectInfo.availableNetworkTypes = capabilitiesBitField;
        List<String> dnsServersList = dnsServers();
        if (dnsServersList.size() > 0) {
            connectInfo.dnsServers = (String[]) dnsServersList.toArray(new String[dnsServersList.size()]);
        }
        return connectInfo;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    java.util.List<com.google.android.snet.NetworkAnalyzer.SslRedirectInfo> analyzeSslRedirects() {
        /*
        r29 = this;
        r0 = r29;
        r0 = r0.mContext;
        r27 = r0;
        r28 = "connectivity";
        r5 = r27.getSystemService(r28);
        r5 = (android.net.ConnectivityManager) r5;
        r3 = r5.getActiveNetworkInfo();
        if (r3 == 0) goto L_0x001a;
    L_0x0014:
        r27 = r3.isConnected();
        if (r27 != 0) goto L_0x001d;
    L_0x001a:
        r17 = 0;
    L_0x001c:
        return r17;
    L_0x001d:
        r17 = new java.util.ArrayList;
        r17.<init>();
        r13 = new java.security.SecureRandom;
        r13.<init>();
        r4 = HOSTS_TO_TEST;
        r12 = r4.length;
        r9 = 0;
    L_0x002b:
        if (r9 >= r12) goto L_0x001c;
    L_0x002d:
        r18 = r4[r9];
        r16 = new com.google.android.snet.NetworkAnalyzer$SslRedirectInfo;
        r16.<init>();
        r23 = 0;
        r15 = 0;
        r19 = 0;
        r26 = randomUserAgentFromList(r13);
        r0 = r26;
        r0 = r0.first;
        r24 = r0;
        r24 = (java.lang.String) r24;
        r0 = r26;
        r0 = r0.second;
        r27 = r0;
        r27 = (java.lang.Integer) r27;
        r25 = r27.intValue();
        r20 = new java.net.URL;	 Catch:{ UnknownHostException -> 0x00f7, ConnectException -> 0x00fe, MalformedURLException -> 0x0105, IOException -> 0x010c, all -> 0x0113 }
        r0 = r20;
        r1 = r18;
        r0.<init>(r1);	 Catch:{ UnknownHostException -> 0x00f7, ConnectException -> 0x00fe, MalformedURLException -> 0x0105, IOException -> 0x010c, all -> 0x0113 }
        r27 = r20.openConnection();	 Catch:{ UnknownHostException -> 0x0141, ConnectException -> 0x013d, MalformedURLException -> 0x0139, IOException -> 0x0135, all -> 0x0131 }
        r0 = r27;
        r0 = (java.net.HttpURLConnection) r0;	 Catch:{ UnknownHostException -> 0x0141, ConnectException -> 0x013d, MalformedURLException -> 0x0139, IOException -> 0x0135, all -> 0x0131 }
        r23 = r0;
        r27 = 0;
        r0 = r23;
        r1 = r27;
        r0.setInstanceFollowRedirects(r1);	 Catch:{ UnknownHostException -> 0x0141, ConnectException -> 0x013d, MalformedURLException -> 0x0139, IOException -> 0x0135, all -> 0x0131 }
        r27 = "User-Agent";
        r0 = r23;
        r1 = r27;
        r2 = r24;
        r0.setRequestProperty(r1, r2);	 Catch:{ UnknownHostException -> 0x0141, ConnectException -> 0x013d, MalformedURLException -> 0x0139, IOException -> 0x0135, all -> 0x0131 }
        r22 = r23.getResponseCode();	 Catch:{ UnknownHostException -> 0x0141, ConnectException -> 0x013d, MalformedURLException -> 0x0139, IOException -> 0x0135, all -> 0x0131 }
        r27 = "Location";
        r0 = r23;
        r1 = r27;
        r15 = r0.getHeaderField(r1);	 Catch:{ UnknownHostException -> 0x0141, ConnectException -> 0x013d, MalformedURLException -> 0x0139, IOException -> 0x0135, all -> 0x0131 }
        if (r23 == 0) goto L_0x008b;
    L_0x0088:
        r23.disconnect();
    L_0x008b:
        r8 = 0;
        r7 = 0;
        r14 = -1;
        r27 = r13.nextBoolean();
        if (r27 == 0) goto L_0x011c;
    L_0x0094:
        r14 = 1;
        r0 = r29;
        r1 = r20;
        r2 = r24;
        r8 = r0.getUrlRedirect(r1, r2);
        if (r8 == 0) goto L_0x011a;
    L_0x00a1:
        r7 = 1;
    L_0x00a2:
        r0 = r18;
        r1 = r16;
        r1.host = r0;
        r0 = r16;
        r0.httpClientUsed = r14;
        r0 = r25;
        r1 = r16;
        r1.userAgentUsed = r0;
        r0 = r22;
        r1 = r16;
        r1.responseCode = r0;
        r0 = r16;
        r0.redirectHeaderValue = r15;
        if (r8 == 0) goto L_0x00c8;
    L_0x00be:
        r27 = r8.toString();
        r0 = r27;
        r1 = r16;
        r1.endPointUrl = r0;
    L_0x00c8:
        r0 = r16;
        r0.endPointConnected = r7;
        r11 = ipAddresses(r8);
        if (r11 == 0) goto L_0x00ea;
    L_0x00d2:
        r27 = r11.size();
        if (r27 <= 0) goto L_0x00ea;
    L_0x00d8:
        r27 = r11.size();
        r0 = r27;
        r10 = new java.lang.String[r0];
        r10 = r11.toArray(r10);
        r10 = (java.lang.String[]) r10;
        r0 = r16;
        r0.ipAddressesForEndPointUrl = r10;
    L_0x00ea:
        r0 = r17;
        r1 = r16;
        r0.add(r1);
        r19 = r20;
    L_0x00f3:
        r9 = r9 + 1;
        goto L_0x002b;
    L_0x00f7:
        r6 = move-exception;
    L_0x00f8:
        if (r23 == 0) goto L_0x00f3;
    L_0x00fa:
        r23.disconnect();
        goto L_0x00f3;
    L_0x00fe:
        r6 = move-exception;
    L_0x00ff:
        if (r23 == 0) goto L_0x00f3;
    L_0x0101:
        r23.disconnect();
        goto L_0x00f3;
    L_0x0105:
        r6 = move-exception;
    L_0x0106:
        if (r23 == 0) goto L_0x00f3;
    L_0x0108:
        r23.disconnect();
        goto L_0x00f3;
    L_0x010c:
        r6 = move-exception;
    L_0x010d:
        if (r23 == 0) goto L_0x00f3;
    L_0x010f:
        r23.disconnect();
        goto L_0x00f3;
    L_0x0113:
        r27 = move-exception;
    L_0x0114:
        if (r23 == 0) goto L_0x0119;
    L_0x0116:
        r23.disconnect();
    L_0x0119:
        throw r27;
    L_0x011a:
        r7 = 0;
        goto L_0x00a2;
    L_0x011c:
        r14 = 2;
        r0 = r29;
        r1 = r20;
        r2 = r24;
        r21 = r0.getUrlRedirectAndroidHttpClient(r1, r2);
        r0 = r21;
        r7 = r0.endPointReached;
        r0 = r21;
        r8 = r0.lastRequestUrl;
        goto L_0x00a2;
    L_0x0131:
        r27 = move-exception;
        r19 = r20;
        goto L_0x0114;
    L_0x0135:
        r6 = move-exception;
        r19 = r20;
        goto L_0x010d;
    L_0x0139:
        r6 = move-exception;
        r19 = r20;
        goto L_0x0106;
    L_0x013d:
        r6 = move-exception;
        r19 = r20;
        goto L_0x00ff;
    L_0x0141:
        r6 = move-exception;
        r19 = r20;
        goto L_0x00f8;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.snet.NetworkAnalyzer.analyzeSslRedirects():java.util.List<com.google.android.snet.NetworkAnalyzer$SslRedirectInfo>");
    }

    static Pair<String, Integer> randomUserAgentFromList(SecureRandom random) {
        int userAgentIndex = random.nextBoolean() ? 0 : HTTP_URL_CONNECTION_CLIENT;
        return Pair.create(USER_AGENTS[userAgentIndex], Integer.valueOf(userAgentIndex));
    }

    String getGoogleWebPage() {
        HttpURLConnection connection = null;
        InputStream inputStream = null;
        String userAgent = randomUserAgentFromList(new SecureRandom()).first;
        try {
            connection = (HttpURLConnection) new URL("http://www.google.com").openConnection();
            connection.setRequestProperty(KEY_USER_AGENT, userAgent);
            inputStream = connection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder strBuilder = new StringBuilder();
            while (true) {
                String line = bufferedReader.readLine();
                if (line == null) {
                    break;
                }
                strBuilder.append(line);
            }
            String stringBuilder = strBuilder.toString();
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                }
            }
            if (connection == null) {
                return stringBuilder;
            }
            connection.disconnect();
            return stringBuilder;
        } catch (MalformedURLException e2) {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e3) {
                }
            }
            if (connection != null) {
                connection.disconnect();
            }
            return null;
        } catch (IOException e4) {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e5) {
                }
            }
            if (connection != null) {
                connection.disconnect();
            }
            return null;
        } catch (Throwable th) {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e6) {
                }
            }
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    static List<String> dnsServers() {
        List<String> dnsServersList = new ArrayList();
        try {
            Class<?> SystemProperties = Class.forName("android.os.SystemProperties");
            String str = Endpoints.ENDPOINT_GET;
            Class[] clsArr = new Class[HTTP_URL_CONNECTION_CLIENT];
            clsArr[0] = String.class;
            Method method = SystemProperties.getMethod(str, clsArr);
            String[] arr$ = KEY_DNS_ENTRIES;
            int len$ = arr$.length;
            for (int i$ = 0; i$ < len$; i$ += HTTP_URL_CONNECTION_CLIENT) {
                Object[] objArr = new Object[HTTP_URL_CONNECTION_CLIENT];
                objArr[0] = arr$[i$];
                String value = (String) method.invoke(null, objArr);
                if (!TextUtils.isEmpty(value)) {
                    dnsServersList.add(value);
                }
            }
        } catch (ClassNotFoundException e) {
        } catch (NoSuchMethodException e2) {
        } catch (IllegalAccessException e3) {
        } catch (InvocationTargetException e4) {
        }
        return dnsServersList;
    }

    static List<String> ipAddresses(URL url) {
        if (url == null) {
            return null;
        }
        List<String> ipAddressesList = new ArrayList();
        try {
            InetAddress[] arr$ = InetAddress.getAllByName(url.getHost());
            int len$ = arr$.length;
            for (int i$ = 0; i$ < len$; i$ += HTTP_URL_CONNECTION_CLIENT) {
                ipAddressesList.add(arr$[i$].getHostAddress());
            }
            return ipAddressesList;
        } catch (UnknownHostException e) {
            return null;
        }
    }

    private URL getUrlRedirect(URL requestUrl, String userAgent) {
        if (requestUrl == null) {
            return null;
        }
        HttpURLConnection urlConnection = null;
        try {
            urlConnection = (HttpURLConnection) requestUrl.openConnection();
            urlConnection.setInstanceFollowRedirects(true);
            urlConnection.setRequestProperty(KEY_USER_AGENT, userAgent);
            urlConnection.getInputStream();
            URL url = urlConnection.getURL();
            if (urlConnection == null) {
                return url;
            }
            urlConnection.disconnect();
            return url;
        } catch (UnknownHostException e) {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            return null;
        } catch (ConnectException e2) {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            return null;
        } catch (MalformedURLException e3) {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            return null;
        } catch (IOException e4) {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            return null;
        } catch (Throwable th) {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
    }

    RedirectResults getUrlRedirectAndroidHttpClient(URL requestUrl, String userAgent) {
        if (requestUrl == null) {
            return null;
        }
        for (int i = 0; i < 5; i += HTTP_URL_CONNECTION_CLIENT) {
            RedirectResults response = getNextRedirectUrl(requestUrl, userAgent);
            if (response == null) {
                return new RedirectResults(false, requestUrl);
            }
            if (response.endPointReached) {
                return new RedirectResults(true, response.lastRequestUrl);
            }
            requestUrl = response.lastRequestUrl;
        }
        return new RedirectResults(false, requestUrl);
    }

    private RedirectResults getNextRedirectUrl(URL requestUrl, String userAgent) {
        AndroidHttpClient client = null;
        try {
            if (!requestUrl.toString().contains(" ")) {
                client = AndroidHttpClient.newInstance(userAgent);
                HttpResponse response = client.execute(new HttpGet(requestUrl.toString()));
                StatusLine statusLine = response.getStatusLine();
                if (statusLine != null) {
                    int statusCode = statusLine.getStatusCode();
                    URL url = requestUrl;
                    if (statusCode != Importance.IMPORTANCE_VISIBLE) {
                        Header[] headers = response.getHeaders(KEY_HTTP_HEADER_LOCATION);
                        if (!(headers == null || headers.length == 0)) {
                            url = new URL(headers[headers.length - 1].getValue());
                        }
                    }
                    RedirectResults redirectResults = new RedirectResults(statusCode == Importance.IMPORTANCE_VISIBLE, url);
                    if (client != null) {
                        client.close();
                    }
                    return redirectResults;
                } else if (client == null) {
                    return null;
                } else {
                    client.close();
                    return null;
                }
            } else if (client == null) {
                return null;
            } else {
                client.close();
                return null;
            }
        } catch (IOException e) {
            if (client == null) {
                return null;
            }
            client.close();
            return null;
        } catch (Throwable th) {
            if (client != null) {
                client.close();
            }
        }
    }
}
