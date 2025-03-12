package com.kcare.kcare.common;

public class StringUtils {

    public static String trimAllSpaces(String input) {
        if (input == null) {
            return null;
        }
        return input.replaceAll("\\s+", "");
    }

}
