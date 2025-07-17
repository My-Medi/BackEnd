package com.my_medi.common.util;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BatchUtil {
    public static int parseIntSafely(String value) {
        try {
            return (value == null || value.trim().isEmpty()) ? 0 : Integer.parseInt(value.trim());
        } catch (NumberFormatException e) {
            log.debug("정수 변환 실패: {}", value);
            return 0;
        }
    }

    public static double parseDoubleSafely(String value) {
        try {
            return (value == null || value.trim().isEmpty()) ? 0.0 : Double.parseDouble(value.trim());
        } catch (NumberFormatException e) {
            log.debug("실수 변환 실패: {}", value);
            return 0.0;
        }
    }

    public static boolean parseBooleanSafely(String value) {
        if (value == null || value.trim().isEmpty()) {
            return false;
        }
        String trimmed = value.trim().toLowerCase();
        return "true".equals(trimmed) || "1".equals(trimmed) || "y".equals(trimmed) || "yes".equals(trimmed);
    }
}
