package com.my_medi.common.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.my_medi.infra.gpt.dto.HealthReportData;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;

@Slf4j
public class ParseUtil {
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

    public static LocalDate parseDate(String dateStr) {
        if (dateStr == null || dateStr.isEmpty() || "null".equals(dateStr)) {
            return null;
        }
        try {
            return LocalDate.parse(dateStr);
        } catch (Exception e) {
            return null;
        }
    }

    public static HealthReportData.MeasurementData parseMeasurement(JsonNode node) {
        return HealthReportData.MeasurementData.builder()
                .height(parseDouble(node.path("height")))
                .weight(parseDouble(node.path("weight")))
                .bmi(parseDouble(node.path("bmi")))
                .waistCircumference(parseDouble(node.path("waistCircumference")))
                .vision(parseString(node.path("vision")))
                .hearing(parseString(node.path("hearing")))
                .build();
    }

    public static HealthReportData.BloodPressureData parseBloodPressure(JsonNode node) {
        return HealthReportData.BloodPressureData.builder()
                .systolic(parseInt(node.path("systolic")))
                .diastolic(parseInt(node.path("diastolic")))
                .status(parseString(node.path("status")))
                .build();
    }

    public static HealthReportData.BloodTestData parseBloodTest(JsonNode node) {
        return HealthReportData.BloodTestData.builder()
                .hemoglobin(parseDouble(node.path("hemoglobin")))
                .glucose(parseDouble(node.path("glucose")))
                .totalCholesterol(parseDouble(node.path("totalCholesterol")))
                .hdlCholesterol(parseDouble(node.path("hdlCholesterol")))
                .ldlCholesterol(parseDouble(node.path("ldlCholesterol")))
                .triglycerides(parseDouble(node.path("triglycerides")))
                .creatinine(parseDouble(node.path("creatinine")))
                .egfr(parseDouble(node.path("egfr")))
                .ast(parseInt(node.path("ast")))
                .alt(parseInt(node.path("alt")))
                .gammaGtp(parseInt(node.path("gammaGtp")))
                .build();
    }

    public static HealthReportData.UrineTestData parseUrineTest(JsonNode node) {
        return HealthReportData.UrineTestData.builder()
                .protein(parseString(node.path("protein")))
                .build();
    }

    public static HealthReportData.ImagingTestData parseImagingTest(JsonNode node) {
        return HealthReportData.ImagingTestData.builder()
                .chestXray(parseString(node.path("chestXray")))
                .build();
    }

    public static Double parseDouble(JsonNode node) {
        if (node.isMissingNode() || node.isNull()) return null;
        try {
            return node.asDouble();
        } catch (Exception e) {
            return null;
        }
    }

    public static Integer parseInt(JsonNode node) {
        if (node.isMissingNode() || node.isNull()) return null;
        try {
            return node.asInt();
        } catch (Exception e) {
            return null;
        }
    }

    public static String parseString(JsonNode node) {
        if (node.isMissingNode() || node.isNull() || "null".equals(node.asText())) {
            return null;
        }
        String text = node.asText().trim();
        return text.isEmpty() ? null : text;
    }
}

