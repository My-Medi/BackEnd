package com.my_medi.api.healthCheckup.mapper;

import com.my_medi.api.healthCheckup.dto.ComparingHealthCheckupResponseDto;
import com.my_medi.common.util.HealthMetricCalculator;
import com.my_medi.domain.healthCheckup.entity.HealthCheckup;
import com.my_medi.domain.member.entity.Gender;

import java.util.List;
import java.util.function.Function;

import static com.my_medi.common.util.HealthMetricCalculator.*;
import static com.my_medi.common.util.HealthMetricCalculator.PercentileCategory.LOWER;
import static com.my_medi.common.util.HealthMetricCalculator.PercentileCategory.UPPER;
import static com.my_medi.common.util.HealthMetricCalculator.classifyE_GFR;

public class HealthCheckupMapper {

    public static ComparingHealthCheckupResponseDto.ComparingBmi toComparingBmiDto(List<HealthCheckup> healthCheckupList,
                                                                                   Double bmi,
                                                                                   Function<HealthCheckup, Double> bmiExtractor) {
        return ComparingHealthCheckupResponseDto.ComparingBmi.builder()
                .bmi(bmi)
                .averageBmi(HealthMetricCalculator.calculateAverageBmi(healthCheckupList))
                .percentageBmi(calculatePercentile(healthCheckupList, bmi, bmiExtractor, LOWER))
                .healthStatus(classifyBmi(bmi))
                .build();
    }

    public static ComparingHealthCheckupResponseDto.ComparingWaist toComparingWaistDto(List<HealthCheckup> healthCheckupList,
                                                                                       Double waist,
                                                                                       Gender gender) {
        return ComparingHealthCheckupResponseDto.ComparingWaist.builder()
                .waist(waist)
                .averageWaist(calculateAverage(healthCheckupList, HealthCheckup::getWaistCm))
                .percentageWaist(calculatePercentile(healthCheckupList, waist, HealthCheckup::getWaistCm, LOWER))
                .healthStatus(classifyWaistCircumference(waist, gender))
                .build();
    }

    public static ComparingHealthCheckupResponseDto.ComparingSystolicBp toComparingSystolicBpDto(List<HealthCheckup> healthCheckupList,
                                                                                                 Integer systolicBp) {
        return ComparingHealthCheckupResponseDto.ComparingSystolicBp.builder()
                .systolicBp(systolicBp)
                .averageSystolicBp(calculateAverage(healthCheckupList, HealthCheckup::getSystolicBp))
                .percentageSystolicBp(calculatePercentile(healthCheckupList, systolicBp, HealthCheckup::getSystolicBp, LOWER))
                .healthStatus(classifySystolic(systolicBp))
                .build();
    }

    public static ComparingHealthCheckupResponseDto.ComparingDiastolicBp toComparingDiastolicBpDto(List<HealthCheckup> healthCheckupList,
                                                                                                   Integer diastolicBp) {
        return ComparingHealthCheckupResponseDto.ComparingDiastolicBp.builder()
                .diastolicBp(diastolicBp)
                .averageDiastolicBp(calculateAverage(healthCheckupList, HealthCheckup::getDiastolicBp))
                .percentageDiastolicBp(calculatePercentile(healthCheckupList, diastolicBp, HealthCheckup::getDiastolicBp, LOWER))
                .healthStatus(classifyDiastolic(diastolicBp))
                .build();
    }

    public static ComparingHealthCheckupResponseDto.ComparingHemoglobin toComparingHemoglobinDto(List<HealthCheckup> healthCheckupList,
                                                                                                 Double hemoglobin,
                                                                                                 Gender gender) {
        return ComparingHealthCheckupResponseDto.ComparingHemoglobin.builder()
                .hemoglobin(hemoglobin)
                .averageHemoglobin(calculateAverage(healthCheckupList, HealthCheckup::getHemoglobin))
                .percentageHemoglobin(calculatePercentile(healthCheckupList, hemoglobin, HealthCheckup::getHemoglobin, UPPER))
                .healthStatus(classifyHemoglobin(hemoglobin, gender))
                .build();
    }

    public static ComparingHealthCheckupResponseDto.ComparingFastingBloodSugar toComparingFastingBloodSugarDto(List<HealthCheckup> healthCheckupList,
                                                                                                               Integer fastingBloodSugar) {
        return ComparingHealthCheckupResponseDto.ComparingFastingBloodSugar.builder()
                .fastingBloodSugar(fastingBloodSugar)
                .averageFastingBloodSugar(calculateAverage(healthCheckupList, HealthCheckup::getFastingBloodSugar))
                .percentageFastingBloodSugar(calculatePercentile(healthCheckupList, fastingBloodSugar, HealthCheckup::getFastingBloodSugar, LOWER))
                .healthStatus(classifyFastingGlucose(fastingBloodSugar))
                .build();
    }

    public static ComparingHealthCheckupResponseDto.ComparingSerumCreatinine toComparingSerumCreatinineDto(List<HealthCheckup> healthCheckupList,
                                                                                                           Double serumCreatinine,
                                                                                                           Gender gender) {
        return ComparingHealthCheckupResponseDto.ComparingSerumCreatinine.builder()
                .serumCreatinine(serumCreatinine)
                .averageSerumCreatinine(0.8)
                .healthStatus(classifyCreatinine(serumCreatinine, gender))
                .build();
    }

    public static ComparingHealthCheckupResponseDto.ComparingAst toComparingAstDto(List<HealthCheckup> healthCheckupList,
                                                                                   Integer ast) {

        return ComparingHealthCheckupResponseDto.ComparingAst.builder()
                .ast(ast)
                .averageAst(calculateAverage(healthCheckupList, HealthCheckup::getAst))
                .percentageAst(calculatePercentile(healthCheckupList, ast, HealthCheckup::getAst, LOWER))
                .healthStatus(classifyAST(ast))
                .build();
    }

    public static ComparingHealthCheckupResponseDto.ComparingAlt toComparingAltDto(List<HealthCheckup> healthCheckupList,
                                                                                   Integer alt) {
        return ComparingHealthCheckupResponseDto.ComparingAlt.builder()
                .alt(alt)
                .averageAlt(calculateAverage(healthCheckupList, HealthCheckup::getAlt))
                .percentageAlt(calculatePercentile(healthCheckupList, alt, HealthCheckup::getAlt, LOWER))
                .healthStatus(classifyALT(alt))
                .build();
    }

    public static ComparingHealthCheckupResponseDto.ComparingGammaGtp toComparingGammaGtpDto(List<HealthCheckup> healthCheckupList,
                                                                                             Integer gtp,
                                                                                             Gender gender) {
        return ComparingHealthCheckupResponseDto.ComparingGammaGtp.builder()
                .gammaGtp(gtp)
                .averageGammaGtp(calculateAverage(healthCheckupList, HealthCheckup::getGammaGtp))
                .percentageGammaGtp(calculatePercentile(healthCheckupList, gtp, HealthCheckup::getGammaGtp, LOWER))
                .healthStatus(classifyGammaGTP(gtp, gender))
                .build();
    }

    public static ComparingHealthCheckupResponseDto.ComparingTotalCholesterol toComparingTotalCholesterol(Integer totalCholesterol,
                                                                                                          Integer ageGroup10Yr) {
        return ComparingHealthCheckupResponseDto.ComparingTotalCholesterol.builder()
                .totalCholesterol(totalCholesterol)
                .averageTotalCholesterol(provideAverageTotalCholesterol(ageGroup10Yr))
                .healthStatus(classifyTotalCholesterol(totalCholesterol))
                .build();
    }

    public static ComparingHealthCheckupResponseDto.ComparingHDL toComparingHDL(Integer hdl,
                                                                                Integer ageGroup10Yr) {
        return ComparingHealthCheckupResponseDto.ComparingHDL.builder()
                .hdl(hdl)
                .averageHDL(provideAverageHDL(ageGroup10Yr))
                .healthStatus(classifyHDL(hdl))
                .build();
    }

    public static ComparingHealthCheckupResponseDto.ComparingLDL toComparingLDL(Integer ldl,
                                                                                Integer ageGroup10Yr) {
        return ComparingHealthCheckupResponseDto.ComparingLDL.builder()
                .ldl(ldl)
                .averageLDL(provideAverageLDL(ageGroup10Yr))
                .healthStatus(classifyLDL(ldl))
                .build();
    }

    public static ComparingHealthCheckupResponseDto.ComparingTriglyceride toComparingTriglyceride(Integer triglyceride,
                                                                                                  Integer ageGroup10Yr) {
        return ComparingHealthCheckupResponseDto.ComparingTriglyceride.builder()
                .triglyceride(triglyceride)
                .averageTriglyceride(provideAverageTriglyceride(ageGroup10Yr))
                .healthStatus(classifyTriglycerides(triglyceride))
                .build();
    }

    public static ComparingHealthCheckupResponseDto.ComparingE_GFR toComparingE_GFR(Integer e_gfr) {
        return ComparingHealthCheckupResponseDto.ComparingE_GFR.builder()
                .e_gfr(e_gfr)
                .averageE_GFR(78)
                .healthStatus(classifyE_GFR(e_gfr))
                .build();
    }
}
