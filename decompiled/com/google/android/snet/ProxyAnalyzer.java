package com.google.android.snet;

import android.content.Context;
import android.net.Proxy;
import android.os.Build.VERSION;
import android.text.TextUtils;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

class ProxyAnalyzer {
    private static final String TAG;
    private final Context mContext;

    public static class ProxyInfo {
        public String address;
        public boolean localIp;
        public boolean present;
    }

    static {
        TAG = ProxyAnalyzer.class.getCanonicalName();
    }

    ProxyAnalyzer(Context context) {
        this.mContext = context;
    }

    ProxyInfo proxyInfo() {
        String proxyAddress;
        ProxyInfo info = new ProxyInfo();
        if (VERSION.SDK_INT >= 14) {
            proxyAddress = System.getProperty("http.proxyHost");
        } else {
            proxyAddress = Proxy.getHost(this.mContext);
        }
        if (!TextUtils.isEmpty(proxyAddress)) {
            info.present = true;
            try {
                for (InetAddress addr : Inet4Address.getAllByName(proxyAddress)) {
                    if (addr.isSiteLocalAddress()) {
                        info.localIp = true;
                        break;
                    }
                }
            } catch (UnknownHostException e) {
            }
            info.address = proxyAddress;
        }
        return info;
    }
}
