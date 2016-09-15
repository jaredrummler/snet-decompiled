package com.google.android.gms.common.util;

import com.google.android.snet.Csv;
import java.util.HashMap;
import java.util.Map;

public class MapUtils {
    private static final String QUOTE = "\"";

    public static void writeStringMapToJson(StringBuilder sb, HashMap<String, String> stringMap) {
        sb.append("{");
        boolean first = true;
        for (String key : stringMap.keySet()) {
            if (first) {
                first = false;
            } else {
                sb.append(Csv.COMMA);
            }
            String keyVal = (String) stringMap.get(key);
            sb.append(QUOTE).append(key).append("\":");
            if (keyVal == null) {
                sb.append("null");
            } else {
                sb.append(QUOTE).append(keyVal).append(QUOTE);
            }
        }
        sb.append("}");
    }

    public static <K, V> K getKeyFromMap(Map<K, V> mapInstance, K key) {
        if (mapInstance.containsKey(key)) {
            for (K mapKey : mapInstance.keySet()) {
                if (mapKey.equals(key)) {
                    return mapKey;
                }
            }
        }
        return null;
    }
}
