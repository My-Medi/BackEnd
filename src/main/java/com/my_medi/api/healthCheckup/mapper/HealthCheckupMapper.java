package com.my_medi.api.healthCheckup.mapper;

import com.my_medi.api.healthCheckup.dto.ComparingHealthCheckupResponseDto;
import com.my_medi.api.report.dto.AverageComparison;
import com.my_medi.api.report.dto.Rank;
import com.my_medi.common.consts.StaticVariable;
import com.my_medi.common.util.HealthMetricCalculator;
import com.my_medi.domain.healthCheckup.entity.HealthCheckup;
import com.my_medi.domain.member.entity.Gender;

import java.util.List;
import java.util.function.Function;

import static com.my_medi.api.healthCheckup.dto.ComparingHealthCheckupResponseDto.*;
import static com.my_medi.api.report.dto.Rank.*;
import static com.my_medi.api.report.dto.Rank.LOWER;
import static com.my_medi.common.consts.StaticVariable.*;
import static com.my_medi.common.util.HealthMetricCalculator.*;
import static com.my_medi.common.util.HealthMetricCalculator.PercentileCategory.LOWER;
import static com.my_medi.common.util.HealthMetricCalculator.PercentileCategory.UPPER;
import static com.my_medi.common.util.HealthMetricCalculator.classifyE_GFR;

public class HealthCheckupMapper {

    public static ComparingBmi toComparingBmiDto(List<HealthCheckup> healthCheckupList,
                                                                                   Double bmi,
                                                                                   Function<HealthCheckup, Double> bmiExtractor) {
        double percentile = calculatePercentile(healthCheckupList, bmi, bmiExtractor, PercentileCategory.LOWER);
        double averageBmi = calculateAverageBmi(healthCheckupList);

        return ComparingBmi.builder()
                .bmi(bmi)
                .averageBmi(averageBmi)
                .percentageBmi(getPercentage(percentile))
                .healthStatus(classifyBmi(bmi))
                .rank(getRank(percentile).getKey())
                .averageComparison(byDelta(bmi, averageBmi, DELTA_BMI).getKey())
                .build();
    }



    public static ComparingWaist toComparingWaistDto(List<HealthCheckup> healthCheckupList,
                                                                                       Double waist,
                                                                                       Gender gender) {
        double percentile = calculatePercentile(healthCheckupList, waist, HealthCheckup::getWaistCm, PercentileCategory.LOWER);
        double averageWaist = calculateAverage(healthCheckupList, HealthCheckup::getWaistCm);

        return ComparingWaist.builder()
                .waist(waist)
                .averageWaist(averageWaist)
                .percentageWaist(getPercentage(percentile))
                .healthStatus(classifyWaistCircumference(waist, gender))
                .rank(getRank(percentile).getKey())
                .averageComparison(byDelta(waist, averageWaist, DELTA_WAIST_CM).getKey())
                .build();
    }

    public static ComparingSystolicBp toComparingSystolicBpDto(List<HealthCheckup> healthCheckupList,
                                                                                                 Integer systolicBp) {
        double percentile = calculatePercentile(healthCheckupList, systolicBp, HealthCheckup::getSystolicBp, PercentileCategory.LOWER);
        double averageSystolic = calculateAverage(healthCheckupList, HealthCheckup::getSystolicBp);

        return ComparingSystolicBp.builder()
                .systolicBp(systolicBp)
                .averageSystolicBp(averageSystolic)
                .percentageSystolicBp(getPercentage(percentile))
                .healthStatus(classifySystolic(systolicBp))
                .rank(getRank(percentile).getKey())
                .averageComparison(byDelta(systolicBp, averageSystolic, DELTA_SBP).getKey())
                .build();
    }

    public static ComparingDiastolicBp toComparingDiastolicBpDto(List<HealthCheckup> healthCheckupList,
                                                                                                   Integer diastolicBp) {
        double percentile = calculatePercentile(healthCheckupList, diastolicBp, HealthCheckup::getDiastolicBp, PercentileCategory.LOWER);
        double averageDiastolic = calculateAverage(healthCheckupList, HealthCheckup::getDiastolicBp);

        return ComparingDiastolicBp.builder()
                .diastolicBp(diastolicBp)
                .averageDiastolicBp(averageDiastolic)
                .percentageDiastolicBp(getPercentage(percentile))
                .healthStatus(classifyDiastolic(diastolicBp))
                .rank(getRank(percentile).getKey())
                .averageComparison(byDelta(diastolicBp, averageDiastolic, DELTA_DBP).getKey())
                .build();
    }

    public static ComparingHemoglobin toComparingHemoglobinDto(List<HealthCheckup> healthCheckupList,
                                                                                                 Double hemoglobin,
                                                                                                 Gender gender) {
        double percentile = calculatePercentile(healthCheckupList, hemoglobin, HealthCheckup::getHemoglobin, UPPER);
        double averageHemoglobin = calculateAverage(healthCheckupList, HealthCheckup::getHemoglobin);

        return ComparingHemoglobin.builder()
                .hemoglobin(hemoglobin)
                .averageHemoglobin(averageHemoglobin)
                .percentageHemoglobin(getPercentage(percentile))
                .healthStatus(classifyHemoglobin(hemoglobin, gender))
                .rank(getRank(percentile).getKey())
                .averageComparison(byDelta(hemoglobin, averageHemoglobin, DELTA_HEMOGLOBIN).getKey())
                .build();
    }

    public static ComparingFastingBloodSugar toComparingFastingBloodSugarDto(List<HealthCheckup> healthCheckupList,
                                                                                                               Integer fastingBloodSugar) {
        double percentile = calculatePercentile(healthCheckupList, fastingBloodSugar, HealthCheckup::getFastingBloodSugar, PercentileCategory.LOWER);
        double averageFastingBloodSugar = calculateAverage(healthCheckupList, HealthCheckup::getFastingBloodSugar);
        return ComparingFastingBloodSugar.builder()
                .fastingBloodSugar(fastingBloodSugar)
                .averageFastingBloodSugar(averageFastingBloodSugar)
                .percentageFastingBloodSugar(getPercentage(percentile))
                .healthStatus(classifyFastingGlucose(fastingBloodSugar))
                .rank(getRank(percentile).getKey())
                .averageComparison(byDelta(fastingBloodSugar, averageFastingBloodSugar, DELTA_FPG).getKey())
                .build();
    }

    public static ComparingSerumCreatinine toComparingSerumCreatinineDto(List<HealthCheckup> healthCheckupList,
                                                                                                           Double serumCreatinine,
                                                                                                           Gender gender) {
        return ComparingSerumCreatinine.builder()
                .serumCreatinine(serumCreatinine)
                .healthStatus(classifyCreatinine(serumCreatinine, gender))
                .rank(NULL.getKey())
                .averageComparison(byDelta(serumCreatinine, 0.8, DELTA_CREATININE).getKey())
                .build();
    }

    public static ComparingAst toComparingAstDto(List<HealthCheckup> healthCheckupList,
                                                                                   Integer ast) {
        double percentile = calculatePercentile(healthCheckupList, ast, HealthCheckup::getAst, PercentileCategory.LOWER);
        double averageAST = calculateAverage(healthCheckupList, HealthCheckup::getAst);

        return ComparingAst.builder()
                .ast(ast)
                .averageAst(averageAST)
                .percentageAst(getPercentage(percentile))
                .healthStatus(classifyAST(ast))
                .rank(getRank(percentile).getKey())
                .averageComparison(byDelta(ast, averageAST, DELTA_AST).getKey())
                .build();
    }

    public static ComparingAlt toComparingAltDto(List<HealthCheckup> healthCheckupList,
                                                                                   Integer alt) {
        double percentile = calculatePercentile(healthCheckupList, alt, HealthCheckup::getAlt, PercentileCategory.LOWER);
        double averageALT = calculateAverage(healthCheckupList, HealthCheckup::getAlt);

        return ComparingAlt.builder()
                .alt(alt)
                .averageAlt(averageALT)
                .percentageAlt(getPercentage(percentile))
                .healthStatus(classifyALT(alt))
                .rank(getRank(percentile).getKey())
                .averageComparison(byDelta(alt, averageALT, DELTA_ALT).getKey())
                .build();
    }

    public static ComparingGammaGtp toComparingGammaGtpDto(List<HealthCheckup> healthCheckupList,
                                                                                             Integer gtp,
                                                                                             Gender gender) {
        double percentile = calculatePercentile(healthCheckupList, gtp, HealthCheckup::getGammaGtp, PercentileCategory.LOWER);
        double averageGTP = calculateAverage(healthCheckupList, HealthCheckup::getGammaGtp);
        return ComparingGammaGtp.builder()
                .gammaGtp(gtp)
                .averageGammaGtp(averageGTP)
                .percentageGammaGtp(getPercentage(percentile))
                .healthStatus(classifyGammaGTP(gtp, gender))
                .rank(getRank(percentile).getKey())
                .averageComparison(byDelta(gtp, averageGTP, DELTA_GAMMA_GTP).getKey())
                .build();
    }

    public static ComparingTotalCholesterol toComparingTotalCholesterol(Integer totalCholesterol,
                                                                                                          Integer ageGroup10Yr) {
        Integer averageTotalCholesterol = provideAverageTotalCholesterol(ageGroup10Yr);

        return ComparingTotalCholesterol.builder()
                .totalCholesterol(totalCholesterol)
                .averageTotalCholesterol(averageTotalCholesterol)
                .healthStatus(classifyTotalCholesterol(totalCholesterol))
                .rank(NULL.getKey())
                .averageComparison(byDelta(totalCholesterol, averageTotalCholesterol, DELTA_TC).getKey())
                .build();
    }

    public static ComparingHDL toComparingHDL(Integer hdl,
                                                                                Integer ageGroup10Yr) {
        Integer averageHDL = provideAverageHDL(ageGroup10Yr);
        return ComparingHDL.builder()
                .hdl(hdl)
                .averageHDL(averageHDL)
                .healthStatus(classifyHDL(hdl))
                .rank(NULL.getKey())
                .averageComparison(byDelta(hdl, averageHDL, DELTA_HDL).getKey())
                .build();
    }

    public static ComparingLDL toComparingLDL(Integer ldl,
                                                                                Integer ageGroup10Yr) {
        Integer averageLDL = provideAverageLDL(ageGroup10Yr);
        return ComparingLDL.builder()
                .ldl(ldl)
                .averageLDL(averageLDL)
                .healthStatus(classifyLDL(ldl))
                .rank(NULL.getKey())
                .averageComparison(byDelta(ldl, averageLDL, DELTA_LDL).getKey())
                .build();
    }

    public static ComparingTriglyceride toComparingTriglyceride(Integer triglyceride,
                                                                Integer ageGroup10Yr) {

        Integer averageTriglyceride = provideAverageTriglyceride(ageGroup10Yr);
        return ComparingTriglyceride.builder()
                .triglyceride(triglyceride)
                .averageTriglyceride(averageTriglyceride)
                .healthStatus(classifyTriglycerides(triglyceride))
                .rank(NULL.getKey())
                .averageComparison(byDelta(triglyceride, averageTriglyceride, DELTA_TG).getKey())
                .build();
    }

    public static ComparingE_GFR toComparingE_GFR(Integer e_gfr) {
        return ComparingE_GFR.builder()
                .e_gfr(e_gfr)
                .averageE_GFR(E_GFR_AVERAGE)
                .healthStatus(classifyE_GFR(e_gfr))
                .rank(NULL.getKey())
                .averageComparison(byDelta(e_gfr, E_GFR_AVERAGE, DELTA_EGFR).getKey())
                .build();
    }

    private static Rank getRank(double percentile) {
        return percentile > 50 ? Rank.LOWER : Rank.UPPER;
    }

    private static double getPercentage(double percentile) {
        return percentile > 50 ? 100 - percentile : percentile;
    }

    private static AverageComparison byDelta(double x, double average, double delta) {
        //TODO exception handling
        if(delta <= 0) throw new RuntimeException("delta must be positive");

        double d = Math.abs(x - average);
        if (d < delta) return AverageComparison.SIMILAR;
        if (d < 2 * delta) return (x < average) ? AverageComparison.SLIGHTLY_BELOW : AverageComparison.SLIGHTLY_ABOVE;
        return (x < average) ? AverageComparison.VERY_BELOW : AverageComparison.VERY_ABOVE;
    }
}
