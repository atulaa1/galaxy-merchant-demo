package com.dungpx.galaxy.merchant.storage;

import java.util.ArrayList;
import java.util.List;

/**
 * <b>File Created</b>: Feb 19, 2019
 *
 * <b>Author</b>: dungpx
 */
public class OutputStorage {
    private static final List<String> OUTPUT_LINES = new ArrayList<>();

    public static void store(String output) {
        OUTPUT_LINES.add(output);
    }

    public static void printAll() {
        OUTPUT_LINES.forEach(System.out::println);
    }

    public static String[] getAll() {
        return OUTPUT_LINES.toArray(new String[0]);
    }

    public static void clearAll() {
        OUTPUT_LINES.clear();
    }
}
