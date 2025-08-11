package com.my_medi.common.util;

import com.my_medi.api.report.dto.HealthStatus;
import com.my_medi.common.consts.StaticVariable;
import com.my_medi.domain.healthCheckup.entity.HealthCheckup;
import com.my_medi.domain.member.entity.Gender;
import org.apache.commons.lang3.Range;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;

import static com.my_medi.api.report.dto.HealthStatus.UNKNOWN;
import static com.my_medi.common.consts.StaticVariable.*;

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
                    double heightCm = h.getHeight5cm() + 2.5; // 중심값 추정
                    double weightKg = h.getWeight5kg() + 2.5; // 중심값 추정
                    return calculateBmi(weightKg, heightCm);
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
            Function<HealthCheckup, ? extends Number> metricExtractor,
            PercentileCategory percentileCategory) {
        List<Double> values = checkups.stream()
                .map(metricExtractor)
                .filter(Objects::nonNull)
                .map(Number::doubleValue)
                .sorted()
                .toList();

        if (values.isEmpty()) {
            return 0.0;
        }

        long count = 0;
        if (percentileCategory.equals(PercentileCategory.LOWER)) {
            count = values.stream()
                    .filter(v -> v < targetValue)
                    .count();
        } else{
            count = values.stream()
                    .filter(v -> v > targetValue)
                    .count();
        }


        double percentile = (double) count / values.size() * 100.0;

        return Math.round(percentile * 10) / 10.0;
    }

    public static enum PercentileCategory{
        UPPER, LOWER
    }

    /* =========================
       개별 항목 분류 메서드
       ========================= */

    // 공복혈당 (mg/dL)
    public static HealthStatus classifyFastingGlucose(Integer mgPerdL) {
        if (mgPerdL == null || mgPerdL == 0) return UNKNOWN;
        // 70-89 / 90-99 / 100-110 / 111-125 / >=126
        if (Range.between(70, 89).contains(mgPerdL)) return HealthStatus.SAFE;
        if (Range.between(90, 99).contains(mgPerdL)) return HealthStatus.NORMAL;
        if (Range.between(100, 110).contains(mgPerdL)) return HealthStatus.WATCH;
        if (Range.between(111, 125).contains(mgPerdL)) return HealthStatus.CAUTION;
        if (Range.ge(126).contains(mgPerdL)) return HealthStatus.DANGER;
        return UNKNOWN; // (<70 등 표 미정의 영역)
    }

    // 총 콜레스테롤 (mg/dL)
    public static HealthStatus classifyTotalCholesterol(Integer mgPerdL) {
        if (mgPerdL == null || mgPerdL == 0) return UNKNOWN;
        if (Range.lt(160).contains(mgPerdL)) return HealthStatus.SAFE;
        if (Range.between(160, 179).contains(mgPerdL)) return HealthStatus.NORMAL;
        if (Range.between(180, 199).contains(mgPerdL)) return HealthStatus.WATCH;
        if (Range.between(200, 239).contains(mgPerdL)) return HealthStatus.CAUTION;
        if (Range.ge(240).contains(mgPerdL)) return HealthStatus.DANGER;
        return UNKNOWN;
    }

    // LDL-콜레스테롤 (mg/dL)
    public static HealthStatus classifyLDL(Integer mgPerdL) {
        if (mgPerdL == null || mgPerdL == 0) return UNKNOWN;
        if (Range.lt(100).contains(mgPerdL)) return HealthStatus.SAFE;
        if (Range.between(100, 109).contains(mgPerdL)) return HealthStatus.NORMAL;
        if (Range.between(110, 129).contains(mgPerdL)) return HealthStatus.WATCH;
        if (Range.between(130, 159).contains(mgPerdL)) return HealthStatus.CAUTION;
        if (Range.ge(160).contains(mgPerdL)) return HealthStatus.DANGER;
        return UNKNOWN;
    }

    // HDL-콜레스테롤 (mg/dL) — 높을수록 좋음
    public static HealthStatus classifyHDL(Integer mgPerdL) {
        if (mgPerdL == null || mgPerdL == 0) return UNKNOWN;
        if (Range.ge(70).contains(mgPerdL)) return HealthStatus.SAFE;
        if (Range.between(60, 69).contains(mgPerdL)) return HealthStatus.NORMAL;
        if (Range.between(50, 59).contains(mgPerdL)) return HealthStatus.WATCH;
        if (Range.between(40, 49).contains(mgPerdL)) return HealthStatus.CAUTION;
        if (Range.lt(40).contains(mgPerdL)) return HealthStatus.DANGER;
        return UNKNOWN;
    }

    // 중성지방 (mg/dL)
    public static HealthStatus classifyTriglycerides(Integer mgPerdL) {
        if (mgPerdL == null || mgPerdL == 0) return UNKNOWN;
        if (Range.lt(100).contains(mgPerdL)) return HealthStatus.SAFE;
        if (Range.between(100, 129).contains(mgPerdL)) return HealthStatus.NORMAL;
        if (Range.between(130, 149).contains(mgPerdL)) return HealthStatus.WATCH;
        if (Range.between(150, 199).contains(mgPerdL)) return HealthStatus.CAUTION;
        if (Range.ge(200).contains(mgPerdL)) return HealthStatus.DANGER;
        return UNKNOWN;
    }

    // AST (IU/L)
    public static HealthStatus classifyAST(Integer iuPerL) {
        if (iuPerL == null || iuPerL == 0) return UNKNOWN;
        if (Range.between(0, 20).contains(iuPerL)) return HealthStatus.SAFE;
        if (Range.between(21, 30).contains(iuPerL)) return HealthStatus.NORMAL;
        if (Range.between(31, 40).contains(iuPerL)) return HealthStatus.WATCH;
        if (Range.between(41, 60).contains(iuPerL)) return HealthStatus.CAUTION;
        if (Range.gt(60).contains(iuPerL)) return HealthStatus.DANGER; // > 60
        return UNKNOWN;
    }

    // ALT (IU/L)
    public static HealthStatus classifyALT(Integer iuPerL) {
        if (iuPerL == null || iuPerL == 0) return UNKNOWN;
        if (Range.between(0, 20).contains(iuPerL)) return HealthStatus.SAFE;
        if (Range.between(21, 30).contains(iuPerL)) return HealthStatus.NORMAL;
        if (Range.between(31, 40).contains(iuPerL)) return HealthStatus.WATCH;
        if (Range.between(41, 60).contains(iuPerL)) return HealthStatus.CAUTION;
        if (Range.gt(60).contains(iuPerL)) return HealthStatus.DANGER; // > 60
        return UNKNOWN;
    }

    // 감마-GTP (IU/L) — 성별별 기준
    public static HealthStatus classifyGammaGTP(Integer iuPerL, Gender gender) {
        if (iuPerL == null || gender == null || iuPerL == 0) return UNKNOWN;
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
        if (mgPerdL == null || gender == null || mgPerdL == 0) return UNKNOWN;
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
    public static HealthStatus classifyE_GFR(Integer egfr) {
        if (egfr == null || egfr == 0) return UNKNOWN;
        if (Range.ge(100).contains(egfr)) return HealthStatus.SAFE;
        if (Range.between(90, 99).contains(egfr)) return HealthStatus.NORMAL;
        if (Range.between(75, 89).contains(egfr)) return HealthStatus.WATCH;
        if (Range.between(60, 74).contains(egfr)) return HealthStatus.CAUTION;
        if (Range.lt(60).contains(egfr)) return HealthStatus.DANGER;
        return UNKNOWN;
    }


    private static HealthStatus worst(HealthStatus a, HealthStatus b) {
        if (a == null) return b == null ? UNKNOWN : b;
        if (b == null) return a;
        return (a.getSeverity() >= b.getSeverity()) ? a : b;
    }

    // 혈압 (mmHg): 수축기/이완기 각각 분류 후 더 위험한 쪽을 반환
    public static HealthStatus classifySystolic(Integer sys) {
        if(sys == null || sys == 0) return UNKNOWN;
        if (Range.lt(110).contains(sys)) return HealthStatus.SAFE;
        if (Range.between(110, 119).contains(sys)) return HealthStatus.NORMAL;
        if (Range.between(120, 129).contains(sys)) return HealthStatus.WATCH;
        if (Range.between(130, 139).contains(sys)) return HealthStatus.CAUTION;
        if(Range.ge(140).contains(sys)) return HealthStatus.DANGER; // >= 140
        return UNKNOWN;
    }

    public static HealthStatus classifyDiastolic(Integer dia) {
        if(dia == null || dia == 0) return UNKNOWN;
        if (Range.lt(75).contains(dia)) return HealthStatus.SAFE;
        if (Range.between(75, 79).contains(dia)) return HealthStatus.NORMAL;
        if (Range.between(80, 84).contains(dia)) return HealthStatus.WATCH;
        if (Range.between(85, 89).contains(dia)) return HealthStatus.CAUTION;
        if(Range.ge(90).contains(dia)) return HealthStatus.DANGER; // >= 140
        return UNKNOWN;
    }

    // 체질량지수 (kg/m^2)
    public static HealthStatus classifyBmi(Double bmi) {
        if (bmi == null || bmi == 0) return HealthStatus.UNKNOWN;
        // 18.5–20.9 / 21.0–22.9 / 23.0–24.9 / 25.0–29.9 / ≥ 30.0
        if (Range.between(18.5, 20.9).contains(bmi)) return HealthStatus.SAFE;
        if (Range.between(21.0, 22.9).contains(bmi)) return HealthStatus.NORMAL;
        if (Range.between(23.0, 24.9).contains(bmi)) return HealthStatus.WATCH;   // 과체중 전단계
        if (Range.between(25.0, 29.9).contains(bmi)) return HealthStatus.CAUTION; // 비만 1단계
        if (Range.ge(30.0).contains(bmi))           return HealthStatus.DANGER;   // 비만 2단계 이상
        return HealthStatus.UNKNOWN; // (<18.5 등 표 미정의 영역)
    }

    // 허리둘레 (cm) — 성별 기준
    public static HealthStatus classifyWaistCircumference(Double cm, Gender gender) {
        if (cm == null || gender == null || cm == 0) return HealthStatus.UNKNOWN;

        if (gender == Gender.MALE) {
            // 남성: <80 / 80–84 / 85–89 / 90–94 / ≥95
            if (Range.lt(80).contains(cm))                 return HealthStatus.SAFE;
            if (Range.between(80, 84).contains(cm))        return HealthStatus.NORMAL;
            if (Range.between(85, 89).contains(cm))        return HealthStatus.WATCH;   // 복부비만 전단계
            if (Range.between(90, 94).contains(cm))        return HealthStatus.CAUTION; // 복부비만
            if (Range.ge(95).contains(cm))                 return HealthStatus.DANGER;  // 고도 복부비만
        } else {
            // 여성: <70 / 70–74 / 75–79 / 80–84 / ≥85
            if (Range.lt(70).contains(cm))                 return HealthStatus.SAFE;
            if (Range.between(70, 74).contains(cm))        return HealthStatus.NORMAL;
            if (Range.between(75, 79).contains(cm))        return HealthStatus.WATCH;   // 복부비만 전단계
            if (Range.between(80, 84).contains(cm))        return HealthStatus.CAUTION; // 복부비만
            if (Range.ge(85).contains(cm))                 return HealthStatus.DANGER;  // 고도 복부비만
        }
        return HealthStatus.UNKNOWN;
    }

    public static HealthStatus classifyHemoglobin(Double gPerdL, Gender gender) {
        if (gPerdL == null || gender == null || gPerdL == 0) return HealthStatus.UNKNOWN;

        if (gender == Gender.MALE) {
            // 남성
            // SAFE: 14.0–15.0
            if (Range.between(14.0, 15.0).contains(gPerdL)) return HealthStatus.SAFE;
            // NORMAL: 15.1–16.5
            if (Range.between(15.1, 16.5).contains(gPerdL)) return HealthStatus.NORMAL;
            // WATCH: 13.0–13.9 (경도 빈혈)
            if (Range.between(13.0, 13.9).contains(gPerdL)) return HealthStatus.WATCH;
            // CAUTION: 12.0–12.9 (중등도 빈혈)
            if (Range.between(12.0, 12.9).contains(gPerdL)) return HealthStatus.CAUTION;
            // DANGER: < 12.0 (중증 빈혈) 또는 > 17.5 (다혈증 의심)
            if (Range.lt(12.0).contains(gPerdL) || Range.gt(17.5).contains(gPerdL)) return HealthStatus.DANGER;
            return HealthStatus.UNKNOWN; // (예: 16.6–17.5 구간)
        } else {
            // 여성
            // SAFE: 12.5–13.5
            if (Range.between(12.5, 13.5).contains(gPerdL)) return HealthStatus.SAFE;
            // NORMAL: 13.6–15.0
            if (Range.between(13.6, 15.0).contains(gPerdL)) return HealthStatus.NORMAL;
            // WATCH: 11.5–12.4 (경도 빈혈)
            if (Range.between(11.5, 12.4).contains(gPerdL)) return HealthStatus.WATCH;
            // CAUTION: 10.5–11.4 (중등도 빈혈)
            if (Range.between(10.5, 11.4).contains(gPerdL)) return HealthStatus.CAUTION;
            // DANGER: < 10.5 (중증 빈혈) 또는 > 16.0 (다혈증 의심)
            if (Range.lt(10.5).contains(gPerdL) || Range.gt(16.0).contains(gPerdL)) return HealthStatus.DANGER;
            return HealthStatus.UNKNOWN; // (예: 15.1–16.0 구간)
        }
    }

    public static Integer provideAverageTotalCholesterol(Integer ageGroup10Yr) {
        if(ageGroup10Yr == null || ageGroup10Yr < 20) return null;
        if(Range.between(20, 29).contains(ageGroup10Yr)) return TOTAL_CHOLESTEROL_20;
        if(Range.between(30, 39).contains(ageGroup10Yr)) return TOTAL_CHOLESTEROL_30;
        if(Range.between(40, 49).contains(ageGroup10Yr)) return TOTAL_CHOLESTEROL_40;
        if(Range.between(50, 59).contains(ageGroup10Yr)) return TOTAL_CHOLESTEROL_50;
        if(Range.ge(60).contains(ageGroup10Yr)) return TOTAL_CHOLESTEROL_60;
        return null;
    }

    public static Integer provideAverageHDL(Integer ageGroup10Yr) {
        if(ageGroup10Yr == null || ageGroup10Yr < 20) return null;
        if(Range.between(20, 29).contains(ageGroup10Yr)) return HDL_20;
        if(Range.between(30, 39).contains(ageGroup10Yr)) return HDL_30;
        if(Range.between(40, 49).contains(ageGroup10Yr)) return HDL_40;
        if(Range.between(50, 59).contains(ageGroup10Yr)) return HDL_50;
        if(Range.ge(60).contains(ageGroup10Yr)) return HDL_60;
        return null;
    }

    public static Integer provideAverageLDL(Integer ageGroup10Yr) {
        if(ageGroup10Yr == null || ageGroup10Yr < 20) return null;
        if(Range.between(20, 29).contains(ageGroup10Yr)) return LDL_20;
        if(Range.between(30, 39).contains(ageGroup10Yr)) return LDL_30;
        if(Range.between(40, 49).contains(ageGroup10Yr)) return LDL_40;
        if(Range.between(50, 59).contains(ageGroup10Yr)) return LDL_50;
        if(Range.ge(60).contains(ageGroup10Yr)) return LDL_60;
        return null;
    }

    public static Integer provideAverageTriglyceride(Integer ageGroup10Yr) {
        if(ageGroup10Yr == null || ageGroup10Yr < 20) return null;
        if(Range.between(20, 29).contains(ageGroup10Yr)) return TRIGLYCERIDE_20;
        if(Range.between(30, 39).contains(ageGroup10Yr)) return TRIGLYCERIDE_30;
        if(Range.between(40, 49).contains(ageGroup10Yr)) return TRIGLYCERIDE_40;
        if(Range.between(50, 59).contains(ageGroup10Yr)) return TRIGLYCERIDE_50;
        if(Range.ge(60).contains(ageGroup10Yr)) return TRIGLYCERIDE_60;
        return null;
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
