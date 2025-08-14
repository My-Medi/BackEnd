package com.my_medi.domain.healthCheckup.repository.querydsl;

import com.my_medi.common.util.HealthMetricCalculator;
import com.my_medi.common.util.HealthMetricCalculator.PercentileCategory;
import com.my_medi.domain.healthCheckup.entity.StatMetric;

import java.util.List;

public interface HealthCheckupStatsQueryRepository {

    double avgBmi(List<Integer> ages, String genderKey);

    double avg(StatMetric m, List<Integer> ages, String genderKey);

    double percentileAgainstTarget(StatMetric m,
                                   List<Integer> ages,
                                   String genderKey,
                                   double targetValue,
                                   PercentileCategory category);
}
