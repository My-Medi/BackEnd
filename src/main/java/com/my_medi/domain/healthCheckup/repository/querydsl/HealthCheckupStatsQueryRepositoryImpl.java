package com.my_medi.domain.healthCheckup.repository.querydsl;

import com.my_medi.domain.healthCheckup.entity.QHealthCheckup;
import com.my_medi.domain.healthCheckup.entity.StatMetric;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class HealthCheckupStatsQueryRepositoryImpl {

    private final JPAQueryFactory qf;
    private static final QHealthCheckup h = QHealthCheckup.healthCheckup;

    private BooleanExpression baseWhere(List<Integer> ages, String genderKey) {
        return h.ageGroup5yr.in(ages).and(h.gender.eq(genderKey));
    }

    private BooleanExpression notNullPredicate(StatMetric m) {
        return switch (m) {
            case BMI -> h.height5cm.isNotNull().and(h.weight5kg.isNotNull());
            case WAIST -> h.waistCm.isNotNull();
            case SYSTOLIC -> h.systolicBp.isNotNull();
            case DIASTOLIC -> h.diastolicBp.isNotNull();
            case HEMOGLOBIN -> h.hemoglobin.isNotNull();
            case FASTING_GLUCOSE -> h.fastingBloodSugar.isNotNull();
            case AST -> h.ast.isNotNull();
            case ALT -> h.alt.isNotNull();
            case GTP -> h.gammaGtp.isNotNull();
        };
    }
}
