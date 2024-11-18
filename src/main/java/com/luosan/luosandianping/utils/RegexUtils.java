package com.luosan.luosandianping.utils;

public class RegexUtils {
    public static boolean isPhone(String phone) {
        return match(phone, RegexPatterns.PHONE_REGEX);
    }

    private static boolean match(String s, String regex) {
       if (s == null) {
           return false;
       }
       return s.matches(regex);
    }
}
