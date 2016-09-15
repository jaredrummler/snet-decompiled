package com.google.android.gms.common.util;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLDecoder;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

public class HttpUtils {
    private static final String DEFAULT_CONTENT_CHARSET = "ISO-8859-1";
    private static final Pattern IPV4_PATTERN;
    private static final Pattern IPV6_HEX_COMPRESSED_PATTERN;
    private static final Pattern IPV6_STD_PATTERN;
    private static final String NAME_VALUE_SEPARATOR = "=";
    private static final String PARAMETER_SEPARATOR = "&";

    static {
        IPV4_PATTERN = Pattern.compile("^(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)(\\.(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)){3}$");
        IPV6_STD_PATTERN = Pattern.compile("^(?:[0-9a-fA-F]{1,4}:){7}[0-9a-fA-F]{1,4}$");
        IPV6_HEX_COMPRESSED_PATTERN = Pattern.compile("^((?:[0-9A-Fa-f]{1,4}(?::[0-9A-Fa-f]{1,4})*)?)::((?:[0-9A-Fa-f]{1,4}(?::[0-9A-Fa-f]{1,4})*)?)$");
    }

    public static Map<String, String> parse(URI uri, String encoding) {
        Map<String, String> result = Collections.emptyMap();
        String query = uri.getRawQuery();
        if (query != null && query.length() > 0) {
            result = new HashMap();
            Scanner scanner = new Scanner(query);
            scanner.useDelimiter(PARAMETER_SEPARATOR);
            while (scanner.hasNext()) {
                String[] nameValue = scanner.next().split(NAME_VALUE_SEPARATOR);
                if (nameValue.length == 0 || nameValue.length > 2) {
                    throw new IllegalArgumentException("bad parameter");
                }
                String name = decode(nameValue[0], encoding);
                String value = null;
                if (nameValue.length == 2) {
                    value = decode(nameValue[1], encoding);
                }
                result.put(name, value);
            }
        }
        return result;
    }

    private static String decode(String content, String encoding) {
        if (encoding == null) {
            encoding = DEFAULT_CONTENT_CHARSET;
        }
        try {
            return URLDecoder.decode(content, encoding);
        } catch (UnsupportedEncodingException problem) {
            throw new IllegalArgumentException(problem);
        }
    }

    public static boolean isIPv4Address(String input) {
        return IPV4_PATTERN.matcher(input).matches();
    }

    public static boolean isIPv6StdAddress(String input) {
        return IPV6_STD_PATTERN.matcher(input).matches();
    }

    public static boolean isIPv6HexCompressedAddress(String input) {
        return IPV6_HEX_COMPRESSED_PATTERN.matcher(input).matches();
    }

    public static boolean isIPv6Address(String input) {
        return isIPv6StdAddress(input) || isIPv6HexCompressedAddress(input);
    }

    private HttpUtils() {
    }
}
