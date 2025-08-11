package com.my_medi.api.report.mapper;

import com.my_medi.api.report.dto.ComparingReportResponseDto.*;
import com.my_medi.api.report.dto.ReportResponseDto.UserReportDto;
import com.my_medi.api.report.dto.*;
import com.my_medi.common.util.BirthDateUtil;
import com.my_medi.common.util.HealthMetricCalculator;
import com.my_medi.domain.healthCheckup.entity.HealthCheckup;
import com.my_medi.domain.member.entity.Gender;
import com.my_medi.domain.report.entity.*;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.function.Function;

import static com.my_medi.common.util.HealthMetricCalculator.*;
import static com.my_medi.common.util.HealthMetricCalculator.PercentileCategory.LOWER;
import static com.my_medi.common.util.HealthMetricCalculator.PercentileCategory.UPPER;

@Slf4j
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
                        .checkupDate(report.getCheckupDate())
                        .round(report.getRound())

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
                        .urineTestStatus(report.getUrineTest().getUrineTestStatus())
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
        log.info("age 10 : {}", ageGroup10Yr);
        Gender gender = report.getUser().getGender();
        // 공공데이터 bmi 계산
        Function<HealthCheckup, Double> bmiExtractor = h -> {
            if (h.getHeight5cm() == null || h.getWeight5kg() == null) return null;
            double heightCm = h.getHeight5cm() + 2.5;
            double weightKg = h.getWeight5kg() + 2.5;
            return calculateBmi(weightKg, heightCm);
        };
        // 사용자 수치
        Double my_bmi = report.getMeasurement().getBmi();
        Double my_waist = report.getMeasurement().getWaist();
        Integer my_systolic = report.getBloodPressure().getSystolic();
        Integer my_diastolic = report.getBloodPressure().getDiastolic();
        Double my_hemoglobin = report.getBloodTest().getHemoglobin();
        Integer my_fastingGlucose = report.getBloodTest().getFastingGlucose();
        Integer my_ast = report.getBloodTest().getAst();
        Integer my_alt = report.getBloodTest().getAlt();
        Integer my_hdl = report.getBloodTest().getHdl();
        Integer my_gtp = report.getBloodTest().getGtp();
        Integer my_total_cholesterol = report.getBloodTest().getTotalCholesterol();
        Integer my_ldl = report.getBloodTest().getLdl();
        Integer my_egfr = report.getBloodTest().getEgfr();
        Integer my_triglyceride = report.getBloodTest().getTriglyceride();
        Double my_creatinine = report.getBloodTest().getCreatinine();


        return ComparingReportResponseDto.builder()
                .totalDataSize(healthCheckupList.size())
                .ageGroup10Yr(BirthDateUtil.getAgeGroup10yr(ageGroup10Yr))
                .comparingBmiDto(ComparingBmiDto.builder()
                        .bmi(my_bmi)
                        .averageBmi(HealthMetricCalculator.calculateAverageBmi(healthCheckupList))
                        .percentageBmi(calculatePercentile(healthCheckupList, my_bmi, bmiExtractor, LOWER))
                        .healthStatus(classifyBmi(my_bmi))
                        .build())
                .comparingWaistDto(ComparingWaistDto.builder()
                        .waist(my_waist)
                        .averageWaist(calculateAverage(healthCheckupList, HealthCheckup::getWaistCm))
                        .percentageWaist(calculatePercentile(healthCheckupList, my_waist, HealthCheckup::getWaistCm, LOWER))
                        .healthStatus(classifyWaistCircumference(my_waist, gender))
                        .build())
                .comparingSystolicBpDto(ComparingSystolicBpDto.builder()
                        .systolicBp(my_systolic)
                        .averageSystolicBp(calculateAverage(healthCheckupList, HealthCheckup::getSystolicBp))
                        .percentageSystolicBp(calculatePercentile(healthCheckupList, my_systolic, HealthCheckup::getSystolicBp, LOWER))
                        .healthStatus(classifySystolic(my_systolic))
                        .build())
                .comparingDiastolicBpDto(ComparingDiastolicBpDto.builder()
                        .diastolicBp(my_diastolic)
                        .averageDiastolicBp(calculateAverage(healthCheckupList, HealthCheckup::getDiastolicBp))
                        .percentageDiastolicBp(calculatePercentile(healthCheckupList, my_diastolic, HealthCheckup::getDiastolicBp, LOWER))
                        .healthStatus(classifyDiastolic(my_diastolic))
                        .build())
                .comparingHemoglobinDto(ComparingHemoglobinDto.builder()
                        .hemoglobin(my_hemoglobin)
                        .averageHemoglobin(calculateAverage(healthCheckupList, HealthCheckup::getHemoglobin))
                        .percentageHemoglobin(calculatePercentile(healthCheckupList, my_hemoglobin, HealthCheckup::getHemoglobin, UPPER))
                        .healthStatus(classifyHemoglobin(my_hemoglobin, gender))
                        .build())
                .comparingFastingBloodSugarDto(ComparingFastingBloodSugarDto.builder()
                        .fastingBloodSugar(my_fastingGlucose)
                        .averageFastingBloodSugar(calculateAverage(healthCheckupList, HealthCheckup::getFastingBloodSugar))
                        .percentageFastingBloodSugar(calculatePercentile(healthCheckupList, my_fastingGlucose, HealthCheckup::getFastingBloodSugar, LOWER))
                        .healthStatus(classifyFastingGlucose(my_fastingGlucose))
                        .build())
                .comparingSerumCreatinineDto(ComparingSerumCreatinineDto.builder()
                        .serumCreatinine(my_creatinine)
                        .averageSerumCreatinine(0.8)
                        .healthStatus(classifyCreatinine(my_creatinine, gender))
                        .build())
                .comparingAstDto(ComparingAstDto.builder()
                        .ast(my_ast)
                        .averageAst(calculateAverage(healthCheckupList, HealthCheckup::getAst))
                        .percentageAst(calculatePercentile(healthCheckupList, my_ast, HealthCheckup::getAst, LOWER))
                        .healthStatus(classifyAST(my_alt))
                        .build())
                .comparingAltDto(ComparingAltDto.builder()
                        .alt(my_alt)
                        .averageAlt(calculateAverage(healthCheckupList, HealthCheckup::getAlt))
                        .percentageAlt(calculatePercentile(healthCheckupList, my_alt, HealthCheckup::getAlt, LOWER))
                        .healthStatus(classifyALT(my_alt))
                        .build())
                .comparingGammaGtpDto(ComparingGammaGtpDto.builder()
                        .gammaGtp(my_gtp)
                        .averageGammaGtp(calculateAverage(healthCheckupList, HealthCheckup::getGammaGtp))
                        .percentageGammaGtp(calculatePercentile(healthCheckupList, my_gtp, HealthCheckup::getGammaGtp, LOWER))
                        .healthStatus(classifyGammaGTP(my_gtp, gender))
                        .build())
                .comparingTotalCholesterol(ComparingTotalCholesterol.builder()
                        .totalCholesterol(my_total_cholesterol)
                        .averageTotalCholesterol(provideAverageTotalCholesterol(ageGroup10Yr))
                        .healthStatus(classifyTotalCholesterol(my_total_cholesterol))
                        .build())
                .comparingHDL(ComparingHDL.builder()
                        .hdl(my_hdl)
                        .averageHDL(provideAverageHDL(ageGroup10Yr))
                        .healthStatus(classifyHDL(my_hdl))
                        .build())
                .comparingLDL(ComparingLDL.builder()
                        .ldl(my_ldl)
                        .averageLDL(provideAverageLDL(ageGroup10Yr))
                        .healthStatus(classifyLDL(my_ldl))
                        .build())
                .comparingTriglyceride(ComparingTriglyceride.builder()
                        .triglyceride(my_triglyceride)
                        .averageTriglyceride(provideAverageTriglyceride(ageGroup10Yr))
                        .healthStatus(classifyTriglycerides(my_triglyceride))
                        .build())
                .comparingEGfr(ComparingE_GFR.builder()
                        .e_gfr(my_egfr)
                        .averageE_GFR(78)
                        .healthStatus(classifyE_GFR(my_egfr))
                        .build())
                .build();
    }
}
