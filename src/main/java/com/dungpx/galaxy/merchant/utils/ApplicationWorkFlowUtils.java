package com.dungpx.galaxy.merchant.utils;

import com.dungpx.galaxy.merchant.storage.OutputStorage;

/**
 * <b>File Created</b>: Feb 20, 2019
 *
 * <b>Author</b>: dungpx
 */
public class ApplicationWorkFlowUtils {
    public static void startup() {
        System.out.println("Welcome to Galaxy Merchant Trading program! Please provide your input below and a blank new line to finish input:");;
    }

    public static void finish() {
        OutputStorage.printAll();
    }
}
