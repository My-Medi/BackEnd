package com.my_medi.domain.healthCheckup.repository.querydsl;

import com.my_medi.common.util.HealthMetricCalculator;
import com.my_medi.common.util.HealthMetricCalculator.PercentileCategory;
import com.my_medi.domain.healthCheckup.entity.QHealthCheckup;
import com.my_medi.domain.healthCheckup.entity.StatMetric;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class HealthCheckupStatsQueryRepositoryImpl implements HealthCheckupStatsQueryRepository{

    private final JPAQueryFactory qf;
    private static final QHealthCheckup h = QHealthCheckup.healthCheckup;

    @Override
    public double avgBmi(List<Integer> ages, String genderKey) {
        Double avg = qf.select(bmiExpr().avg())
                .from(h)
                .where(baseWhere(ages, genderKey)
                        .and(notNullPredicate(StatMetric.BMI)))
                .fetchOne();
        return avg == null ? 0.0 : round1(avg);
    }
    @Override
    public double avg(StatMetric m, List<Integer> ages, String genderKey) {
        Double avg = qf.select(metricExpr(m).avg())
                .from(h)
                .where(baseWhere(ages, genderKey).and(notNullPredicate(m)))
                .fetchOne();
        return avg == null ? 0.0 : round1(avg);
    }

    @Override
    public double percentileAgainstTarget(StatMetric m,
                                          List<Integer> ages,
                                          String genderKey,
                                          double targetValue,
                                          PercentileCategory category) {

        NumberExpression<Double> expr = metricExpr(m);

        NumberExpression<Integer> less = new CaseBuilder()
                .when(expr.lt(targetValue)).then(1).otherwise(0).sum();

        NumberExpression<Integer> greater = new CaseBuilder()
                .when(expr.gt(targetValue)).then(1).otherwise(0).sum();

        NumberExpression<Long> total = h.id.count();

        Tuple t = qf.select(less, greater, total)
                .from(h)
                .where(baseWhere(ages, genderKey).and(notNullPredicate(m)))
                .fetchOne();

        if (t == null || t.get(total) == null || t.get(total) == 0L) return 0.0;

        double tot = t.get(total);
        double cnt = (category == PercentileCategory.LOWER) ? t.get(less) : t.get(greater);
        return round1((cnt / tot) * 100.0);
    }

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

    private NumberExpression<Double> metricExpr(StatMetric m) {
        return switch (m) {
            case BMI -> bmiExpr();
            case WAIST -> h.waistCm.doubleValue();
            case SYSTOLIC -> h.systolicBp.doubleValue();
            case DIASTOLIC -> h.diastolicBp.doubleValue();
            case HEMOGLOBIN -> h.hemoglobin;
            case FASTING_GLUCOSE -> h.fastingBloodSugar.doubleValue();
            case AST -> h.ast.doubleValue();
            case ALT -> h.alt.doubleValue();
            case GTP -> h.gammaGtp.doubleValue();
        };
    }

    private double round1(double v) {
        return Math.round(v * 10.0) / 10.0;
    }

    private NumberExpression<Double> bmiExpr() {
        // height(미터)도 double로 강제
        NumberExpression<Double> heightM =
                h.height5cm.add(2.5).doubleValue().divide(100.0);

        // POWER/POW는 DB에 맞게 선택 (MySQL/PG는 POWER 사용 가능)
        NumberExpression<Double> heightSq =
                Expressions.numberTemplate(Double.class, "POWER({0}, {1})", heightM, 2);

        // 분자도 double로 강제
        NumberExpression<Double> numerator = h.weight5kg.add(2.5).doubleValue();

        return numerator.divide(heightSq);
    }
}
