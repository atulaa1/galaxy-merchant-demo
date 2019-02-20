package com.dungpx.galaxy.merchant.storage;

import java.util.HashMap;
import java.util.Map;

/**
 * <b>File Created</b>: Feb 19, 2019
 *
 * <b>Author</b>: dungpx
 */
public class ConversionRateStorage {
    private static final Map<String, String> CONVERSION_RATES = new HashMap<>();

    public static void store(String key, String value) {
        CONVERSION_RATES.put(key, value);
    }

    public static String get(String key) {
        return CONVERSION_RATES.get(key);
    }

    public static void clearAll() {
        CONVERSION_RATES.clear();
    }
}
