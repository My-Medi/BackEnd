package com.my_medi.common.util;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BatchUtil {
    public static int parseIntSafely(String value) {
        if (value == null || value.trim().isEmpty()) {
            return 0;
        }
        try {
            return Integer.parseInt(value.trim());
        } catch (NumberFormatException e) {
            log.debug("정수 변환 실패: '{}' -> 0으로 설정", value);
            return 0;
        }
    }

    public static double parseDoubleSafely(String value) {
        if (value == null || value.trim().isEmpty()) {
            return 0.0;
        }
        try {
            return Double.parseDouble(value.trim());
        } catch (NumberFormatException e) {
            log.debug("실수 변환 실패: '{}' -> 0.0으로 설정", value);
            return 0.0;
        }
    }

    public static boolean parseBooleanFromInt(String value) {
        if (value == null || value.trim().isEmpty()) {
            return false;
        }
        try {
            int intValue = Integer.parseInt(value.trim());
            return intValue == 1;
        } catch (NumberFormatException e) {
            log.debug("Boolean 변환 실패: '{}' -> false로 설정", value);
            return false;
        }
    }
}
