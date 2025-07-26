package com.my_medi.api.report.mapper;

import com.my_medi.api.report.dto.ComparingReportResponseDto.*;
import com.my_medi.api.report.dto.ReportResponseDto.UserReportDto;
import com.my_medi.api.report.dto.*;
import com.my_medi.common.util.HealthMetricCalculator;
import com.my_medi.domain.healthCheckup.entity.HealthCheckup;
import com.my_medi.domain.report.entity.*;

import java.util.List;

public class ReportConverter {

    // Entity -> Dto

    public static UserReportDto toUserReportDto(Report report) {
        return UserReportDto.builder()
                .id(report.getId())
                .userId(report.getUser().getId())
                .checkupDate(report.getCheckupDate())
                .round(report.getRound())
                .measurementDto(toMeasurementDto(report))
                .bloodPressureDto(toBloodPressureDto(report))
                .bloodTestDto(toBloodTestDto(report))
                .urineTestDto(toUrineTestDto(report))
                .imagingTestDto(toImagingTestDto(report))
                .interviewDto(toInterviewDto(report))
                .additionalTestDto(toAdditionalTestDto(report))
                .build();
    }

    public static MeasurementDto toMeasurementDto(Report report) {
        Measurement measurement = report.getMeasurement();

        return MeasurementDto.builder()
                .height(measurement.getHeight())
                .weight(measurement.getWeight())
                .bmi(measurement.getBmi())
                .bmiCategory(measurement.getBmiCategory())
                .waist(measurement.getWaist())
                .waistType(measurement.getWaistType())
                .vision(measurement.getVision())
                .hearingLeft(measurement.getHearingLeft())
                .hearingRight(measurement.getHearingRight())
                .build();
    }

    public static BloodPressureDto toBloodPressureDto(Report report) {
        BloodPressure bloodPressure = report.getBloodPressure();

        return BloodPressureDto.builder()
                .systolic(bloodPressure.getSystolic())
                .diastolic(bloodPressure.getDiastolic())
                .highRisk(bloodPressure.getHighRisk())
                .build();
    }

    public static BloodTestDto toBloodTestDto(Report report) {
        BloodTest bloodTest = report.getBloodTest();

        return BloodTestDto.builder()
                .alt(bloodTest.getAlt())
                .anemia(bloodTest.getAnemia())
                .ast(bloodTest.getAst())
                .creatinine(bloodTest.getCreatinine())
                .diabetes(bloodTest.getDiabetes())
                .egfr(bloodTest.getEgfr())
                .fastingGlucose(bloodTest.getFastingGlucose())
                .gtp(bloodTest.getGtp())
                .hdl(bloodTest.getHdl())
                .hemoglobin(bloodTest.getHemoglobin())
                .hyperlipidemia(bloodTest.getHyperlipidemia())
                .ldl(bloodTest.getLdl())
                .totalCholesterol(bloodTest.getTotalCholesterol())
                .triglyceride(bloodTest.getTriglyceride())
                .build();
    }

    public static UrineTestDto toUrineTestDto(Report report) {
        return UrineTestDto.builder()
                .testRequired(report.getUrineTest().getTestRequired())
                .build();
    }

    public static ImagingTestDto toImagingTestDto(Report report) {
        return ImagingTestDto.builder()
                .chestXrayNormal(report.getImagingTest().getChestXrayNormal())
                .build();
    }

    public static InterviewDto toInterviewDto(Report report) {
        Interview interview = report.getInterview();

        return InterviewDto.builder()
                .hasPastDisease(interview.getHasPastDisease())
                .onMedication(interview.getOnMedication())
                .needsSmokingCessation(interview.getNeedsSmokingCessation())
                .needsAlcoholRestriction(interview.getNeedsAlcoholRestriction())
                .needsExercise(interview.getNeedsExercise())
                .needsMuscleExercise(interview.getNeedsMuscleExercise())
                .build();
    }

    public static AdditionalTestDto toAdditionalTestDto(Report report) {
        return AdditionalTestDto.builder()
                .needsFurtherTest(report.getAdditionalTest().getNeedsFurtherTest())
                .build();
    }

    // Dto -> Entity

    public static Measurement toMeasurement(MeasurementDto measurementDto) {
        return Measurement.builder()
                .height(measurementDto.getHeight())
                .weight(measurementDto.getWeight())
                .bmi(measurementDto.getBmi())
                .bmiCategory(measurementDto.getBmiCategory())
                .waist(measurementDto.getWaist())
                .waistType(measurementDto.getWaistType())
                .vision(measurementDto.getVision())
                .hearingLeft(measurementDto.getHearingLeft())
                .hearingRight(measurementDto.getHearingRight())
                .build();
    }

    public static BloodPressure toBloodPressure(BloodPressureDto bloodPressureDto) {
        return BloodPressure.builder()
                .systolic(bloodPressureDto.getSystolic())
                .diastolic(bloodPressureDto.getDiastolic())
                .highRisk(bloodPressureDto.getHighRisk())
                .build();
    }

    public static BloodTest toBloodTest(BloodTestDto bloodTestDto) {
        return BloodTest.builder()
                .alt(bloodTestDto.getAlt())
                .anemia(bloodTestDto.getAnemia())
                .ast(bloodTestDto.getAst())
                .creatinine(bloodTestDto.getCreatinine())
                .diabetes(bloodTestDto.getDiabetes())
                .egfr(bloodTestDto.getEgfr())
                .fastingGlucose(bloodTestDto.getFastingGlucose())
                .gtp(bloodTestDto.getGtp())
                .hdl(bloodTestDto.getHdl())
                .hemoglobin(bloodTestDto.getHemoglobin())
                .hyperlipidemia(bloodTestDto.getHyperlipidemia())
                .ldl(bloodTestDto.getLdl())
                .totalCholesterol(bloodTestDto.getTotalCholesterol())
                .triglyceride(bloodTestDto.getTriglyceride())
                .build();
    }

    public static UrineTest toUrineTest(UrineTestDto urineTestDto) {
        return UrineTest.builder()
                .testRequired(urineTestDto.getTestRequired())
                .build();
    }

    public static ImagingTest toImagingTest(ImagingTestDto imagingTestDto) {
        return ImagingTest.builder()
                .chestXrayNormal(imagingTestDto.getChestXrayNormal())
                .build();
    }

    public static Interview toInterview(InterviewDto interviewDto) {
        return Interview.builder()
                .hasPastDisease(interviewDto.getHasPastDisease())
                .onMedication(interviewDto.getOnMedication())
                .needsSmokingCessation(interviewDto.getNeedsSmokingCessation())
                .needsAlcoholRestriction(interviewDto.getNeedsAlcoholRestriction())
                .needsExercise(interviewDto.getNeedsExercise())
                .needsMuscleExercise(interviewDto.getNeedsMuscleExercise())
                .build();
    }

    public static AdditionalTest toAdditionalTest(AdditionalTestDto additionalTestDto) {
        return AdditionalTest.builder()
                .needsFurtherTest(additionalTestDto.getNeedsFurtherTest())
                .build();
    }

    public static ComparingReportResponseDto toComparingReportResponseDto(List<HealthCheckup> healthCheckupList, Report report) {
        return ComparingReportResponseDto.builder()
                .comparingBmiDto(ComparingBmiDto.builder()
                        .bmi(report.getMeasurement().getBmi())
                        .averageBmi(HealthMetricCalculator.calculateAverageBmi(healthCheckupList))
                        .percentageBmi(10)    //TODO
                        .build())
                .comparingWaistDto(ComparingWaistDto.builder()
                        .waist(report.getMeasurement().getWaist())
                        .averageWaist(HealthMetricCalculator.calculateAverage(healthCheckupList, HealthCheckup::getWaistCm))
                        .percentageWaist(10)    //TODO
                        .build())
                .comparingSystolicBpDto(ComparingSystolicBpDto.builder()
                        .systolicBp(report.getBloodPressure().getSystolic())
                        .averageSystolicBp(HealthMetricCalculator.calculateAverage(healthCheckupList, HealthCheckup::getSystolicBp))
                        .percentageSystolicBp(10)   //TODO
                        .build())
                .comparingDiastolicBpDto(ComparingDiastolicBpDto.builder()
                        .diastolicBp(report.getBloodPressure().getDiastolic())
                        .averageDiastolicBp(HealthMetricCalculator.calculateAverage(healthCheckupList, HealthCheckup::getDiastolicBp))
                        .percentageDiastolicBp(10)  //TODO
                        .build())
                .comparingHemoglobinDto(ComparingHemoglobinDto.builder()
                        .hemoglobin(report.getBloodTest().getHemoglobin())
                        .averageHemoglobin(HealthMetricCalculator.calculateAverage(healthCheckupList, HealthCheckup::getHemoglobin))
                        .percentageHemoglobin(10)   //TODO
                        .build())
                .comparingFastingBloodSugarDto(ComparingFastingBloodSugarDto.builder()
                        .fastingBloodSugar(report.getBloodTest().getFastingGlucose())
                        .averageFastingBloodSugar(HealthMetricCalculator.calculateAverage(healthCheckupList, HealthCheckup::getFastingBloodSugar))
                        .percentageFastingBloodSugar(10)    //TODO
                        .build())
                .comparingSerumCreatinineDto(ComparingSerumCreatinineDto.builder()
                        .serumCreatinine(report.getBloodTest().getCreatinine())
                        .averageSerumCreatinine(0.8)
                        .build())
                .comparingAstDto(ComparingAstDto.builder()
                        .ast(report.getBloodTest().getAst())
                        .averageAst(HealthMetricCalculator.calculateAverage(healthCheckupList, HealthCheckup::getAst))
                        .percentageAst(10)  //TODO
                        .build())
                .comparingAltDto(ComparingAltDto.builder()
                        .alt(report.getBloodTest().getAlt())
                        .averageAlt(HealthMetricCalculator.calculateAverage(healthCheckupList, HealthCheckup::getAlt))
                        .percentageAlt(10)  //TODO
                        .build())
                .comparingGammaGtpDto(ComparingGammaGtpDto.builder()
                        .gammaGtp(report.getBloodTest().getGtp())
                        .averageGammaGtp(HealthMetricCalculator.calculateAverage(healthCheckupList, HealthCheckup::getGammaGtp))
                        .percentageGammaGtp(10)     //TODO
                        .build())
                .build();
    }
}
