package com.my_medi.common.util;

import com.my_medi.api.report.dto.HealthStatus;
import com.my_medi.domain.healthCheckup.entity.HealthCheckup;
import com.my_medi.domain.member.entity.Gender;
import org.apache.commons.lang3.Range;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;

import static com.my_medi.api.report.dto.HealthStatus.UNKNOWN;

public class HealthMetricCalculator {

    public static double calculateBmi(double weightKg, double heightCm) {
        if (heightCm <= 0) {
            //TODO exception
            throw new IllegalArgumentException("Height must be positive");
        }

        double heightM = heightCm / 100.0;
        return weightKg / (heightM * heightM);
    }

    public static double calculateAverageBmi(List<HealthCheckup> checkups) {
        double result = checkups.stream()
                .filter(h -> h.getHeight5cm() != null && h.getWeight5kg() != null)
                .mapToDouble(h -> {
                    double heightCm = h.getHeight5cm() * 5 + 2.5; // 중심값 추정
                    double weightKg = h.getWeight5kg() * 5 + 2.5; // 중심값 추정
                    double heightM = heightCm / 100.0;
                    return calculateBmi(weightKg, heightM);
                })
                .average()
                .orElse(0.0);
        return Math.round(result * 10) / 10.0;
    }

    public static double calculateAverage(List<HealthCheckup> checkups, Function<HealthCheckup, ? extends Number> extractor) {
        double result = checkups.stream()
                .map(extractor)
                .filter(Objects::nonNull)
                .mapToDouble(Number::doubleValue)
                .average()
                .orElse(0.0);
        return Math.round(result * 10) / 10.0;
    }

    public static double calculatePercentile(
            List<HealthCheckup> checkups,
            double targetValue,
            Function<HealthCheckup, ? extends Number> metricExtractor) {
        List<Double> values = checkups.stream()
                .map(metricExtractor)
                .filter(Objects::nonNull)
                .map(Number::doubleValue)
                .sorted()
                .toList();

        if (values.isEmpty()) {
            return 0.0;
        }

        long count = values.stream()
                .filter(v -> v < targetValue)
                .count();

        double percentile = (double) count / values.size() * 100.0;

        return Math.round(percentile * 10) / 10.0;
    }

    /* =========================
       개별 항목 분류 메서드
       ========================= */

    // 공복혈당 (mg/dL)
    public static HealthStatus classifyFastingGlucose(Double mgPerdL) {
        if (mgPerdL == null) return UNKNOWN;
        // 70-89 / 90-99 / 100-110 / 111-125 / >=126
        if (Range.between(70, 89).contains(mgPerdL)) return HealthStatus.SAFE;
        if (Range.between(90, 99).contains(mgPerdL)) return HealthStatus.NORMAL;
        if (Range.between(100, 110).contains(mgPerdL)) return HealthStatus.WATCH;
        if (Range.between(111, 125).contains(mgPerdL)) return HealthStatus.CAUTION;
        if (Range.ge(126).contains(mgPerdL)) return HealthStatus.DANGER;
        return UNKNOWN; // (<70 등 표 미정의 영역)
    }

    // 총 콜레스테롤 (mg/dL)
    public static HealthStatus classifyTotalCholesterol(Double mgPerdL) {
        if (mgPerdL == null) return UNKNOWN;
        if (Range.lt(160).contains(mgPerdL)) return HealthStatus.SAFE;
        if (Range.between(160, 179).contains(mgPerdL)) return HealthStatus.NORMAL;
        if (Range.between(180, 199).contains(mgPerdL)) return HealthStatus.WATCH;
        if (Range.between(200, 239).contains(mgPerdL)) return HealthStatus.CAUTION;
        if (Range.ge(240).contains(mgPerdL)) return HealthStatus.DANGER;
        return UNKNOWN;
    }

    // LDL-콜레스테롤 (mg/dL)
    public static HealthStatus classifyLDL(Double mgPerdL) {
        if (mgPerdL == null) return UNKNOWN;
        if (Range.lt(100).contains(mgPerdL)) return HealthStatus.SAFE;
        if (Range.between(100, 109).contains(mgPerdL)) return HealthStatus.NORMAL;
        if (Range.between(110, 129).contains(mgPerdL)) return HealthStatus.WATCH;
        if (Range.between(130, 159).contains(mgPerdL)) return HealthStatus.CAUTION;
        if (Range.ge(160).contains(mgPerdL)) return HealthStatus.DANGER;
        return UNKNOWN;
    }

    // HDL-콜레스테롤 (mg/dL) — 높을수록 좋음
    public static HealthStatus classifyHDL(Double mgPerdL) {
        if (mgPerdL == null) return UNKNOWN;
        if (Range.ge(70).contains(mgPerdL)) return HealthStatus.SAFE;
        if (Range.between(60, 69).contains(mgPerdL)) return HealthStatus.NORMAL;
        if (Range.between(50, 59).contains(mgPerdL)) return HealthStatus.WATCH;
        if (Range.between(40, 49).contains(mgPerdL)) return HealthStatus.CAUTION;
        if (Range.lt(40).contains(mgPerdL)) return HealthStatus.DANGER;
        return UNKNOWN;
    }

    // 중성지방 (mg/dL)
    public static HealthStatus classifyTriglycerides(Double mgPerdL) {
        if (mgPerdL == null) return UNKNOWN;
        if (Range.lt(100).contains(mgPerdL)) return HealthStatus.SAFE;
        if (Range.between(100, 129).contains(mgPerdL)) return HealthStatus.NORMAL;
        if (Range.between(130, 149).contains(mgPerdL)) return HealthStatus.WATCH;
        if (Range.between(150, 199).contains(mgPerdL)) return HealthStatus.CAUTION;
        if (Range.ge(200).contains(mgPerdL)) return HealthStatus.DANGER;
        return UNKNOWN;
    }

    // AST (IU/L)
    public static HealthStatus classifyAST(Double iuPerL) {
        if (iuPerL == null) return UNKNOWN;
        if (Range.between(0, 20).contains(iuPerL)) return HealthStatus.SAFE;
        if (Range.between(21, 30).contains(iuPerL)) return HealthStatus.NORMAL;
        if (Range.between(31, 40).contains(iuPerL)) return HealthStatus.WATCH;
        if (Range.between(41, 60).contains(iuPerL)) return HealthStatus.CAUTION;
        if (Range.gt(60).contains(iuPerL)) return HealthStatus.DANGER; // > 60
        return UNKNOWN;
    }

    // ALT (IU/L)
    public static HealthStatus classifyALT(Double iuPerL) {
        if (iuPerL == null) return UNKNOWN;
        if (Range.between(0, 20).contains(iuPerL)) return HealthStatus.SAFE;
        if (Range.between(21, 30).contains(iuPerL)) return HealthStatus.NORMAL;
        if (Range.between(31, 40).contains(iuPerL)) return HealthStatus.WATCH;
        if (Range.between(41, 60).contains(iuPerL)) return HealthStatus.CAUTION;
        if (Range.gt(60).contains(iuPerL)) return HealthStatus.DANGER; // > 60
        return UNKNOWN;
    }

    // 감마-GTP (IU/L) — 성별별 기준
    public static HealthStatus classifyGammaGTP(Double iuPerL, Gender gender) {
        if (iuPerL == null || gender == null) return UNKNOWN;
        if (gender == Gender.MALE) {
            if (Range.lt(40).contains(iuPerL)) return HealthStatus.SAFE;
            if (Range.between(40, 49).contains(iuPerL)) return HealthStatus.NORMAL;
            if (Range.between(50, 62).contains(iuPerL)) return HealthStatus.WATCH;
            if (Range.between(63, 100).contains(iuPerL)) return HealthStatus.CAUTION;
            if (Range.gt(100).contains(iuPerL)) return HealthStatus.DANGER;
        } else {
            if (Range.lt(20).contains(iuPerL)) return HealthStatus.SAFE;
            if (Range.between(20, 29).contains(iuPerL)) return HealthStatus.NORMAL;
            if (Range.between(30, 34).contains(iuPerL)) return HealthStatus.WATCH;
            if (Range.between(35, 70).contains(iuPerL)) return HealthStatus.CAUTION;
            if (Range.gt(70).contains(iuPerL)) return HealthStatus.DANGER;
        }
        return UNKNOWN;
    }

    // 혈청 크레아티닌 (mg/dL) — 성별별 기준
    public static HealthStatus classifyCreatinine(Double mgPerdL, Gender gender) {
        if (mgPerdL == null || gender == null) return UNKNOWN;
        if (gender == Gender.MALE) {
            // 0.6-0.8 / 0.9-1.0 / 1.1-1.2 / 1.3-1.4 / >=1.5
            if (Range.between(0.6, 0.8).contains(mgPerdL)) return HealthStatus.SAFE;
            if (Range.between(0.9, 1.0).contains(mgPerdL)) return HealthStatus.NORMAL;
            if (Range.between(1.1, 1.2).contains(mgPerdL)) return HealthStatus.WATCH;
            if (Range.between(1.3, 1.4).contains(mgPerdL)) return HealthStatus.CAUTION;
            if (Range.ge(1.5).contains(mgPerdL)) return HealthStatus.DANGER;
        } else {
            // 0.5-0.7 / 0.8 / 0.9-1.0 / 1.1-1.2 / >=1.3
            if (Range.between(0.5, 0.7).contains(mgPerdL)) return HealthStatus.SAFE;
            if (Range.eq(0.8).contains(mgPerdL)) return HealthStatus.NORMAL;
            if (Range.between(0.9, 1.0).contains(mgPerdL)) return HealthStatus.WATCH;
            if (Range.between(1.1, 1.2).contains(mgPerdL)) return HealthStatus.CAUTION;
            if (Range.ge(1.3).contains(mgPerdL)) return HealthStatus.DANGER;
        }
        return UNKNOWN; // (<0.6(남) / <0.5(여) 등 표 미정의 영역)
    }

    // eGFR (단위 없음)
    public static HealthStatus classifyEgfr(Double egfr) {
        if (egfr == null) return UNKNOWN;
        if (Range.ge(100).contains(egfr)) return HealthStatus.SAFE;
        if (Range.between(90, 99).contains(egfr)) return HealthStatus.NORMAL;
        if (Range.between(75, 89).contains(egfr)) return HealthStatus.WATCH;
        if (Range.between(60, 74).contains(egfr)) return HealthStatus.CAUTION;
        if (Range.lt(60).contains(egfr)) return HealthStatus.DANGER;
        return UNKNOWN;
    }

    // 혈압 (mmHg): 수축기/이완기 각각 분류 후 더 위험한 쪽을 반환
    public static HealthStatus classifyBloodPressure(Integer systolic, Integer diastolic) {
        if (systolic == null || diastolic == null) return UNKNOWN;
        HealthStatus s = classifySystolic(systolic);
        HealthStatus d = classifyDiastolic(diastolic);
        return worst(s, d);
    }

    private static HealthStatus worst(HealthStatus a, HealthStatus b) {
        if (a == null) return b == null ? UNKNOWN : b;
        if (b == null) return a;
        return (a.getSeverity() >= b.getSeverity()) ? a : b;
    }

    private static HealthStatus classifySystolic(int sys) {
        if (sys < 110) return HealthStatus.SAFE;
        if (sys <= 119) return HealthStatus.NORMAL;
        if (sys <= 129) return HealthStatus.WATCH;
        if (sys <= 139) return HealthStatus.CAUTION;
        return HealthStatus.DANGER; // >= 140
    }

    private static HealthStatus classifyDiastolic(int dia) {
        if (dia < 75) return HealthStatus.SAFE;
        if (dia <= 79) return HealthStatus.NORMAL;
        if (dia <= 84) return HealthStatus.WATCH;
        if (dia <= 89) return HealthStatus.CAUTION;
        return HealthStatus.DANGER; // >= 90
    }

    private static final class Range {
        final Double min;
        final Double max;
        final boolean isIncludeMin;
        final boolean isIncludeMax;
        private Range(Double min, Double max, boolean isIncludeMin, boolean isIncludeMax) {
            this.min = min;
            this.max = max;
            this.isIncludeMin = isIncludeMin;
            this.isIncludeMax = isIncludeMax;
        }
        static Range lt(double maxExclusive) { return new Range(null, maxExclusive, false, false); }
        static Range le(double maxInclusive) { return new Range(null, maxInclusive, false, true); }
        static Range gt(double minExclusive) { return new Range(minExclusive, null, false, false); }
        static Range ge(double minInclusive) { return new Range(minInclusive, null, true, false); }
        static Range between(double minInclusive, double maxInclusive) { return new Range(minInclusive, maxInclusive, true, true); }
        static Range eq(double value) { return new Range(value, value, true, true); }

        boolean contains(double v) {
            if (min != null) {
                if (isIncludeMin ? v < min : v <= min) return false;
            }
            if (max != null) {
                if (isIncludeMax ? v > max : v >= max) return false;
            }
            return true;
        }
    }

}
