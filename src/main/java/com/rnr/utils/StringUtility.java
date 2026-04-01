package com.rnr.utils;

public final class StringUtility {

    private StringUtility() { }

    public static boolean isEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }
}
