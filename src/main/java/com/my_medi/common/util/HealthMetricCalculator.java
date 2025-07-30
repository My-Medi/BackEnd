package com.my_medi.common.util;

import com.my_medi.domain.healthCheckup.entity.HealthCheckup;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;

public class HealthMetricCalculator {

    public static double calculateBmi(double weightKg, double heightCm) {
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
                    return weightKg / (heightM * heightM);
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

}
