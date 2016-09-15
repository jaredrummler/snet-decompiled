package com.google.android.gms.common.server;

import android.net.Uri;
import android.text.TextUtils;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.snet.Csv;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class BaseApi {
    private static final boolean STRICT_PARAM = true;

    public static abstract class BaseApiaryOptions<DerivedClassType extends BaseApiaryOptions<DerivedClassType>> {
        private static final String HEADER_ETAG = "ETag";
        private static final String PARAMETER_FIELDS = "fields";
        private static final String PARAMETER_PRETTY_PRINT = "prettyPrint";
        private static final String PARAMETER_TRACE = "trace";
        private static final String TRACE_PREFIX_LDAP = "email:";
        private static final String TRACE_PREFIX_TOKEN = "token:";
        private final Collector mCollector;
        private final ArrayList<String> mFields;
        private final HashMap<String, String> mHeaders;
        private String mTrace;

        public final class Collector {
            private boolean mNeedsComma;
            private boolean mNeedsSlash;
            private StringBuilder mPath;
            private int mSubDepth;

            public Collector() {
                this.mPath = new StringBuilder();
            }

            private void append(String str) {
                if (this.mNeedsComma) {
                    this.mNeedsComma = false;
                    this.mPath.append(Csv.COMMA);
                } else if (this.mNeedsSlash) {
                    this.mNeedsSlash = false;
                    this.mPath.append("/");
                }
                this.mPath.append(str);
            }

            private void finish() {
                if (this.mSubDepth == 0) {
                    BaseApiaryOptions.this.addField(this.mPath.toString());
                    this.mPath.setLength(0);
                    this.mNeedsComma = false;
                    this.mNeedsSlash = false;
                    return;
                }
                this.mNeedsComma = BaseApi.STRICT_PARAM;
            }

            public void addPiece(String name) {
                append(name);
                this.mNeedsSlash = BaseApi.STRICT_PARAM;
            }

            public void finishPiece(String name) {
                append(name);
                if (this.mSubDepth == 0) {
                    BaseApiaryOptions.this.addField(this.mPath.toString());
                    this.mPath.setLength(0);
                    return;
                }
                this.mNeedsComma = BaseApi.STRICT_PARAM;
            }

            public void beginSubCollection(String name) {
                append(name);
                this.mPath.append("(");
                this.mSubDepth++;
            }

            public void endSubCollection() {
                this.mPath.append(")");
                this.mSubDepth--;
                finish();
            }
        }

        public BaseApiaryOptions() {
            this.mFields = new ArrayList();
            this.mHeaders = new HashMap();
            this.mCollector = new Collector();
        }

        public final DerivedClassType buildFrom(BaseApiaryOptions<?> otherOptions) {
            if (otherOptions.mTrace != null) {
                this.mTrace = otherOptions.mTrace;
            }
            if (!otherOptions.mFields.isEmpty()) {
                this.mFields.clear();
                this.mFields.addAll(otherOptions.mFields);
            }
            return this;
        }

        public final DerivedClassType addField(String path) {
            this.mFields.add(path);
            return this;
        }

        public final List<String> getFields() {
            return this.mFields;
        }

        public final DerivedClassType setHeader(String key, String value) {
            this.mHeaders.put(key, value);
            return this;
        }

        public final DerivedClassType setEtag(String etag) {
            return setHeader(HEADER_ETAG, etag);
        }

        public final Map<String, String> getHeaders() {
            return this.mHeaders;
        }

        protected final Collector getCollector() {
            return this.mCollector;
        }

        public final DerivedClassType setTraceByLdap(String ldap) {
            this.mTrace = TRACE_PREFIX_LDAP + ldap;
            return this;
        }

        public final DerivedClassType setTraceByToken(String token) {
            this.mTrace = TRACE_PREFIX_TOKEN + token;
            return this;
        }

        public final String getTrace() {
            return this.mTrace;
        }

        @Deprecated
        public final String appendParametersToUrl(String url) {
            url = BaseApi.append(url, PARAMETER_PRETTY_PRINT, String.valueOf(GooglePlayServicesUtilLight.getApiaryPrettyPrintOption()));
            if (this.mTrace != null) {
                url = BaseApi.append(url, PARAMETER_TRACE, getTrace());
            }
            if (this.mFields.isEmpty()) {
                return url;
            }
            return BaseApi.append(url, PARAMETER_FIELDS, TextUtils.join(Csv.COMMA, getFields().toArray()));
        }

        public void appendParametersToUrl(StringBuilder url) {
            BaseApi.append(url, PARAMETER_PRETTY_PRINT, String.valueOf(GooglePlayServicesUtilLight.getApiaryPrettyPrintOption()));
            if (this.mTrace != null) {
                BaseApi.append(url, PARAMETER_TRACE, getTrace());
            }
            if (!this.mFields.isEmpty()) {
                BaseApi.append(url, PARAMETER_FIELDS, TextUtils.join(Csv.COMMA, getFields().toArray()));
            }
        }
    }

    public static class FieldCollection<Parent> {
        private final Collector mCollector;
        private final Parent mParent;

        protected FieldCollection(Parent parent, Collector collector) {
            if (parent == null) {
                parent = this;
            }
            this.mParent = parent;
            this.mCollector = collector;
        }

        protected Parent getParent() {
            return this.mParent;
        }

        protected Collector getCollector() {
            return this.mCollector;
        }
    }

    public static String enc(String param) {
        Preconditions.checkNotNull(param, "Encoding a null parameter!");
        return Uri.encode(param);
    }

    protected static List<String> enc(List<String> params) {
        int size = params.size();
        ArrayList<String> encoded = new ArrayList(size);
        int count = size;
        for (int i = 0; i < count; i++) {
            encoded.add(enc((String) params.get(i)));
        }
        return encoded;
    }

    @Deprecated
    public static String append(String url, String paramName, String paramValue) {
        StringBuilder sb = new StringBuilder();
        sb.append(url);
        if (url.indexOf("?") != -1) {
            sb.append('&');
        } else {
            sb.append('?');
        }
        sb.append(paramName);
        sb.append('=');
        sb.append(paramValue);
        return sb.toString();
    }

    public static void append(StringBuilder url, String paramName, String paramValue) {
        if (url.indexOf("?") != -1) {
            url.append('&');
        } else {
            url.append('?');
        }
        url.append(paramName);
        url.append('=');
        url.append(paramValue);
    }
}
