package com.my_medi.api.report.mapper;

import com.my_medi.api.report.dto.ComparingReportResponseDto.*;
import com.my_medi.api.report.dto.ReportResponseDto.UserReportDto;
import com.my_medi.api.report.dto.*;
import com.my_medi.common.util.BirthDateUtil;
import com.my_medi.common.util.HealthMetricCalculator;
import com.my_medi.domain.healthCheckup.entity.HealthCheckup;
import com.my_medi.domain.report.entity.*;

import java.util.List;
import java.util.function.Function;

import static com.my_medi.common.util.HealthMetricCalculator.calculateAverage;
import static com.my_medi.common.util.HealthMetricCalculator.calculatePercentile;

public class ReportConverter {

    // Entity -> Dto

    public static UserReportDto toUserReportDto(Report report) {
        return UserReportDto.builder()
                .id(report.getId())
                .userId(report.getUser().getId())
                .hospitalName(report.getHospitalName())
                .checkupDate(report.getCheckupDate())
                .round(report.getRound())
                .measurementDto(toMeasurementDto(report))
                .bloodPressureDto(toBloodPressureDto(report))
                .bloodTestDto(toBloodTestDto(report))
                .urineTestDto(toUrineTestDto(report))
                .imagingTestDto(toImagingTestDto(report))
                .interviewDto(toInterviewDto(report))
                .additionalTestDto(
                        report.hasAdditionalTest() ? toAdditionalTestDto(report) : null
                )
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
                .bloodPressureStatus(bloodPressure.getBloodPressureStatus())
                .build();
    }

    public static BloodTestDto toBloodTestDto(Report report) {
        BloodTest bloodTest = report.getBloodTest();

        return BloodTestDto.builder()
                .hemoglobin(bloodTest.getHemoglobin())
                .hemoglobinStatus(bloodTest.getHemoglobinStatus())

                .fastingGlucose(bloodTest.getFastingGlucose())
                .fastingGlucoseType(bloodTest.getFastingGlucoseType())

                .totalCholesterol(bloodTest.getTotalCholesterol())
                .hdl(bloodTest.getHdl())
                .triglyceride(bloodTest.getTriglyceride())
                .ldl(bloodTest.getLdl())
                .cholesterolStatus(bloodTest.getCholesterolStatus())

                .creatinine(bloodTest.getCreatinine())
                .egfr(bloodTest.getEgfr())
                .renalFunctionStatus(bloodTest.getRenalFunctionStatus())

                .ast(bloodTest.getAst())
                .alt(bloodTest.getAlt())
                .gtp(bloodTest.getGtp())
                .liverFunctionStatus(bloodTest.getLiverFunctionStatus())

                .build();
    }

    public static UrineTestDto toUrineTestDto(Report report) {
        return UrineTestDto.builder()
                .urineTestStatus(report.getUrineTest().getUrineTestStatus())
                .build();
    }

    public static ImagingTestDto toImagingTestDto(Report report) {
        return ImagingTestDto.builder()
                .imagingTestStatus(report.getImagingTest().getImagingTestStatus())
                .build();
    }

    public static InterviewDto toInterviewDto(Report report) {
        Interview interview = report.getInterview();

        return InterviewDto.builder()
                .hasPastDisease(interview.getHasPastDisease())
                .onMedication(interview.getOnMedication())
                .lifestyleHabitsStatus(interview.getLifestyleHabitsStatus())
                .build();
    }

    public static AdditionalTestDto toAdditionalTestDto(Report report) {
        AdditionalTest additionalTest = report.getAdditionalTest();

        return AdditionalTestDto.builder()
                .b8Hepatitis(additionalTest.getB8Hepatitis())
                .depression(additionalTest.getDepression())
                .cognitiveImpairment(additionalTest.getCognitiveImpairment())
                .boneDensityStatus(additionalTest.getBoneDensityStatus())
                .elderlyPhysicalFunctionStatus(additionalTest.getElderlyPhysicalFunctionStatus())
                .elderlyFunctionTest(additionalTest.getElderlyFunctionTest())
                .build();
    }

    public static ReportSummaryDto toUserReportSummaryDto(Report report) {
        return ReportSummaryDto.builder()
                        .reportId(report.getId())
                        .userId(report.getUser().getId())
<<<<<<< HEAD
                        .checkupDate(report.getCheckupDate())
                        .round(report.getRound())
=======
                        .round(report.getRound())
                        .checkupDate(report.getCheckupDate())
>>>>>>> 0440ed4 ([MEDI-81] feat: create latest user report's summay API)
                .obesity(ReportSummaryDto.ObesityDto.builder()
                        .bmi(report.getMeasurement().getBmi())
                        .waistType(report.getMeasurement().getWaistType())
                        .build())

                .hypertension(ReportSummaryDto.HypertensionDto.builder()
                        .systolic(report.getBloodPressure().getSystolic())
                        .diastolic(report.getBloodPressure().getDiastolic())
                        .build())

                .diabetes(ReportSummaryDto.DiabetesDto.builder()
                        .fastingGlucose(report.getBloodTest().getFastingGlucose())
                        .build())

                .kidney(ReportSummaryDto.KidneyDto.builder()
                        .creatinine(report.getBloodTest().getCreatinine())
                        .egfr(report.getBloodTest().getEgfr())
                        .build())

                .liver(ReportSummaryDto.LiverDto.builder()
                        .ast(report.getBloodTest().getAst())
                        .alt(report.getBloodTest().getAlt())
                        .gtp(report.getBloodTest().getGtp())
                        .build())

                .anemia(ReportSummaryDto.AnemiaDto.builder()
                        .hemoglobin(report.getBloodTest().getHemoglobin())
                        .build())

                .dyslipidemia(ReportSummaryDto.DyslipidemiaDto.builder()
                        .totalCholesterol(report.getBloodTest().getTotalCholesterol())
                        .hdl(report.getBloodTest().getHdl())
                        .triglyceride(report.getBloodTest().getTriglyceride())
                        .ldl(report.getBloodTest().getLdl())
                        .build())

                .urine(ReportSummaryDto.UrineDto.builder()
<<<<<<< HEAD
                        .urineTestStatus(report.getUrineTest().getUrineTestStatus())
=======
                        .proteinuria(report.getUrineTest().getTestRequired() ? "비정상" : "정상")
>>>>>>> 0440ed4 ([MEDI-81] feat: create latest user report's summay API)
                        .build())

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
                .bloodPressureStatus(bloodPressureDto.getBloodPressureStatus())
                .build();
    }

    public static BloodTest toBloodTest(BloodTestDto bloodTestDto) {
        return BloodTest.builder()
                .hemoglobin(bloodTestDto.getHemoglobin())
                .hemoglobinStatus(bloodTestDto.getHemoglobinStatus())

                .fastingGlucose(bloodTestDto.getFastingGlucose())
                .fastingGlucoseType(bloodTestDto.getFastingGlucoseType())

                .totalCholesterol(bloodTestDto.getTotalCholesterol())
                .hdl(bloodTestDto.getHdl())
                .triglyceride(bloodTestDto.getTriglyceride())
                .ldl(bloodTestDto.getLdl())
                .cholesterolStatus(bloodTestDto.getCholesterolStatus())

                .creatinine(bloodTestDto.getCreatinine())
                .egfr(bloodTestDto.getEgfr())
                .renalFunctionStatus(bloodTestDto.getRenalFunctionStatus())

                .ast(bloodTestDto.getAst())
                .alt(bloodTestDto.getAlt())
                .gtp(bloodTestDto.getGtp())
                .liverFunctionStatus(bloodTestDto.getLiverFunctionStatus())
                .build();
    }

    public static UrineTest toUrineTest(UrineTestDto urineTestDto) {
        return UrineTest.builder()
                .urineTestStatus(urineTestDto.getUrineTestStatus())
                .build();
    }

    public static ImagingTest toImagingTest(ImagingTestDto imagingTestDto) {
        return ImagingTest.builder()
                .imagingTestStatus(imagingTestDto.getImagingTestStatus())
                .build();
    }

    public static Interview toInterview(InterviewDto interviewDto) {
        return Interview.builder()
                .hasPastDisease(interviewDto.getHasPastDisease())
                .onMedication(interviewDto.getOnMedication())
                .lifestyleHabitsStatus(interviewDto.getLifestyleHabitsStatus())
                .build();
    }

    public static AdditionalTest toAdditionalTest(AdditionalTestDto additionalTestDto) {
        return AdditionalTest.builder()
                .b8Hepatitis(additionalTestDto.getB8Hepatitis())
                .depression(additionalTestDto.getDepression())
                .cognitiveImpairment(additionalTestDto.getCognitiveImpairment())
                .boneDensityStatus(additionalTestDto.getBoneDensityStatus())
                .elderlyPhysicalFunctionStatus(additionalTestDto.getElderlyPhysicalFunctionStatus())
                .elderlyFunctionTest(additionalTestDto.getElderlyFunctionTest())
                .build();
    }

    public static ComparingReportResponseDto toComparingReportResponseDto(List<HealthCheckup> healthCheckupList, Report report) {
        int ageGroup10Yr = BirthDateUtil.getAge(report.getUser().getBirthDate());
        Function<HealthCheckup, Double> bmiExtractor = h -> {
            if (h.getHeight5cm() == null || h.getWeight5kg() == null) return null;
            double heightCm = h.getHeight5cm() * 5 + 2.5;
            double weightKg = h.getWeight5kg() * 5 + 2.5;
            double heightM = heightCm / 100.0;
            return weightKg / (heightM * heightM);
        };
        return ComparingReportResponseDto.builder()
                .totalDataSize(healthCheckupList.size())
                .ageGroup10Yr(BirthDateUtil.getAgeGroup10yr(ageGroup10Yr))
                .comparingBmiDto(ComparingBmiDto.builder()
                        .bmi(report.getMeasurement().getBmi())
                        .averageBmi(HealthMetricCalculator.calculateAverageBmi(healthCheckupList))
                        .percentageBmi(calculatePercentile(healthCheckupList, report.getMeasurement().getBmi(), bmiExtractor))
                        .build())
                .comparingWaistDto(ComparingWaistDto.builder()
                        .waist(report.getMeasurement().getWaist())
                        .averageWaist(calculateAverage(healthCheckupList, HealthCheckup::getWaistCm))
                        .percentageWaist(calculatePercentile(healthCheckupList, report.getMeasurement().getWaist(), HealthCheckup::getWaistCm))
                        .build())
                .comparingSystolicBpDto(ComparingSystolicBpDto.builder()
                        .systolicBp(report.getBloodPressure().getSystolic())
                        .averageSystolicBp(calculateAverage(healthCheckupList, HealthCheckup::getSystolicBp))
                        .percentageSystolicBp(calculatePercentile(healthCheckupList, report.getBloodPressure().getSystolic(), HealthCheckup::getSystolicBp))
                        .build())
                .comparingDiastolicBpDto(ComparingDiastolicBpDto.builder()
                        .diastolicBp(report.getBloodPressure().getDiastolic())
                        .averageDiastolicBp(calculateAverage(healthCheckupList, HealthCheckup::getDiastolicBp))
                        .percentageDiastolicBp(calculatePercentile(healthCheckupList, report.getBloodPressure().getDiastolic(), HealthCheckup::getDiastolicBp))
                        .build())
                .comparingHemoglobinDto(ComparingHemoglobinDto.builder()
                        .hemoglobin(report.getBloodTest().getHemoglobin())
                        .averageHemoglobin(calculateAverage(healthCheckupList, HealthCheckup::getHemoglobin))
                        .percentageHemoglobin(calculatePercentile(healthCheckupList, report.getBloodTest().getHemoglobin(), HealthCheckup::getHemoglobin))
                        .build())
                .comparingFastingBloodSugarDto(ComparingFastingBloodSugarDto.builder()
                        .fastingBloodSugar(report.getBloodTest().getFastingGlucose())
                        .averageFastingBloodSugar(calculateAverage(healthCheckupList, HealthCheckup::getFastingBloodSugar))
                        .percentageFastingBloodSugar(calculatePercentile(healthCheckupList, report.getBloodTest().getFastingGlucose(), HealthCheckup::getFastingBloodSugar))
                        .build())
                .comparingSerumCreatinineDto(ComparingSerumCreatinineDto.builder()
                        .serumCreatinine(report.getBloodTest().getCreatinine())
                        .averageSerumCreatinine(0.8)
                        .build())
                .comparingAstDto(ComparingAstDto.builder()
                        .ast(report.getBloodTest().getAst())
                        .averageAst(calculateAverage(healthCheckupList, HealthCheckup::getAst))
                        .percentageAst(calculatePercentile(healthCheckupList, report.getBloodTest().getAst(), HealthCheckup::getAst))
                        .build())
                .comparingAltDto(ComparingAltDto.builder()
                        .alt(report.getBloodTest().getAlt())
                        .averageAlt(calculateAverage(healthCheckupList, HealthCheckup::getAlt))
                        .percentageAlt(calculatePercentile(healthCheckupList, report.getBloodTest().getAlt(), HealthCheckup::getAlt))
                        .build())
                .comparingGammaGtpDto(ComparingGammaGtpDto.builder()
                        .gammaGtp(report.getBloodTest().getGtp())
                        .averageGammaGtp(calculateAverage(healthCheckupList, HealthCheckup::getGammaGtp))
                        .percentageGammaGtp(calculatePercentile(healthCheckupList,report.getBloodTest().getGtp(), HealthCheckup::getGammaGtp))
                        .build())
                .build();
    }
}
