package com.my_medi.common.util;

import com.my_medi.domain.healthCheckup.entity.HealthCheckup;

import java.util.List;

public class BmiCalculator {

    public static double calculateBmi(double weightKg, double heightCm) {
        double heightM = heightCm / 100.0;
        return weightKg / (heightM * heightM);
    }

    public static double calculateAverageBmi(List<HealthCheckup> checkups) {
        return checkups.stream()
                .filter(h -> h.getHeight5cm() != null && h.getWeight5kg() != null)
                .mapToDouble(h -> {
                    double heightCm = h.getHeight5cm() * 5 + 2.5; // 중심값 추정
                    double weightKg = h.getWeight5kg() * 5 + 2.5; // 중심값 추정
                    double heightM = heightCm / 100.0;
                    return weightKg / (heightM * heightM);
                })
                .average()
                .orElse(0.0);
    }
}
