package com.my_medi.api.report.mapper;


import com.my_medi.api.healthCheckup.dto.ComparingHealthCheckupResponseDto;
import com.my_medi.api.report.dto.ReportPartitionRequestDto.*;
import com.my_medi.api.report.dto.ReportResponseDto.ReportResultDto;
import com.my_medi.api.report.dto.ReportResponseDto.UserReportDto;
import com.my_medi.api.report.dto.*;
import com.my_medi.common.util.BirthDateUtil;

import com.my_medi.common.util.EnumConvertUtil;
import com.my_medi.domain.healthCheckup.entity.HealthCheckup;
import com.my_medi.domain.member.entity.Gender;
import com.my_medi.domain.report.entity.*;
import com.my_medi.domain.report.enums.BloodPressureStatus;
import com.my_medi.domain.report.enums.ImagingTestStatus;
import com.my_medi.domain.report.enums.UrineTestStatus;
import com.my_medi.domain.report.enums.interview.LifestyleHabitsStatus;
import com.my_medi.domain.report.enums.interview.PositiveNegativeStatus;
import com.my_medi.domain.reportResult.entity.ReportResult;
import com.my_medi.infra.gpt.dto.HealthReportData;
import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

import static com.my_medi.api.healthCheckup.mapper.HealthCheckupMapper.*;
import static com.my_medi.api.report.dto.AssessmentDto.*;
import static com.my_medi.common.util.HealthMetricCalculator.*;


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
                .hasAdditionalTest(report.hasAdditionalTest())
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
                .lifestyleHabitsStatusList(interview.getLifestyleHabitsStatusList())
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
                .lifestyleHabitsStatusList(interviewDto.getLifestyleHabitsStatusList())
                .build();
    }

    public static AdditionalTest toAdditionalTest(AdditionalTestDto additionalTestDto) {
        return AdditionalTest.builder()
                .b8Hepatitis(B8Hepatitis.selectApplicability(additionalTestDto))
                .depression(additionalTestDto.getDepression())
                .cognitiveImpairment(additionalTestDto.getCognitiveImpairment())
                .boneDensityStatus(additionalTestDto.getBoneDensityStatus())
                .elderlyPhysicalFunctionStatus(additionalTestDto.getElderlyPhysicalFunctionStatus())
                .elderlyFunctionTest(ElderlyFunctionTest.selectApplicability(additionalTestDto))
                .build();
    }

    public static ReportResultDto toReportResultDto(List<HealthCheckup> healthCheckupList, Report report) {
        int ageGroup10Yr = BirthDateUtil.getAge(report.getUser().getBirthDate());
        Gender gender = report.getUser().getGender();
        // 공공데이터 bmi 계산
        Function<HealthCheckup, Double> bmiExtractor = h -> {
            if (h.getHeight5cm() == null || h.getWeight5kg() == null) return null;
            double heightCm = h.getHeight5cm() + 2.5;
            double weightKg = h.getWeight5kg() + 2.5;
            return calculateBmi(weightKg, heightCm);
        };
        // 사용자 수치
        Double bmi = report.getMeasurement().getBmi();
        Double waist = report.getMeasurement().getWaist();
        Integer diastolicBp = report.getBloodPressure().getDiastolic();
        Integer systolicBp = report.getBloodPressure().getSystolic();
        Double hemoglobin = report.getBloodTest().getHemoglobin();
        Integer fastingGlucose = report.getBloodTest().getFastingGlucose();
        Integer ast = report.getBloodTest().getAst();
        Integer alt = report.getBloodTest().getAlt();
        Integer hdl = report.getBloodTest().getHdl();
        Integer gtp = report.getBloodTest().getGtp();
        Integer total_cholesterol = report.getBloodTest().getTotalCholesterol();
        Integer ldl = report.getBloodTest().getLdl();
        Integer egfr = report.getBloodTest().getEgfr();
        Integer triglyceride = report.getBloodTest().getTriglyceride();
        Double creatinine = report.getBloodTest().getCreatinine();


        return ReportResultDto.builder()
                .ageGroup10Yr(BirthDateUtil.getAgeGroup10yr(ageGroup10Yr))
                .nickname(report.getUser().getNickname())
                .gender(report.getUser().getGender())
                .obesityAssessmentDto(ObesityAssessmentDto.builder()
                        .comparingBmi(toComparingBmiDto(healthCheckupList, bmi, bmiExtractor))
                        .comparingWaist(toComparingWaistDto(healthCheckupList, waist, gender))
                        .build()
                )
                .hypertensionAssessmentDto(
                        HypertensionAssessmentDto.builder()
                                .comparingDiastolicBp(toComparingDiastolicBpDto(healthCheckupList, diastolicBp))
                                .comparingSystolicBp(toComparingSystolicBpDto(healthCheckupList, systolicBp))
                                .build()
                )
                .anemiaAssessmentDto(
                        AnemiaAssessmentDto.builder()
                                .comparingHemoglobin(toComparingHemoglobinDto(healthCheckupList, hemoglobin, gender))
                                .build()
                )
                .diabetesAssessmentDto(
                        DiabetesAssessmentDto.builder()
                                .comparingFastingBloodSugar(toComparingFastingBloodSugarDto(healthCheckupList, fastingGlucose))
                                .build()
                )
                .dyslipidemiaAssessmentDto(
                        DyslipidemiaAssessmentDto.builder()
                                .comparingTotalCholesterol(toComparingTotalCholesterol(total_cholesterol, ageGroup10Yr))
                                .comparingHDL(toComparingHDL(hdl, ageGroup10Yr))
                                .comparingLDL(toComparingLDL(ldl, ageGroup10Yr))
                                .comparingTriglyceride(toComparingTriglyceride(triglyceride, ageGroup10Yr))
                                .build()
                )
                .kidneyDiseaseAssessmentDto(
                        KidneyDiseaseAssessmentDto.builder()
                                .comparingEGfr(toComparingE_GFR(egfr))
                                .comparingSerumCreatinine(toComparingSerumCreatinineDto(creatinine, gender))
                                .build()
                )
                .liverDiseaseAssessmentDto(
                        LiverDiseaseAssessmentDto.builder()
                                .comparingAlt(toComparingAltDto(healthCheckupList, alt))
                                .comparingAst(toComparingAstDto(healthCheckupList, ast))
                                .comparingGammaGtp(toComparingGammaGtpDto(healthCheckupList, gtp, gender))
                                .build()
                )
                .urineProteinAssessmentDto(
                        UrineProteinAssessmentDto.builder()
                                .comparingUrineProtein(
                                        ComparingHealthCheckupResponseDto.ComparingUrineProtein.builder()
                                                .urineTestStatus(report.getUrineTest().getUrineTestStatus())
                                                .healthStatus(classifyUrineProtein(report.getUrineTest().getUrineTestStatus()))
                                                .averageComparison(AverageComparison.NULL.getKey())
                                                .build()
                                )
                                .build()
                )
                .build();
    }

    public static ReportResultDto toReportResultDto(Report report, ReportResult reportResult) {
        int ageGroup10Yr = BirthDateUtil.getAge(report.getUser().getBirthDate());
        Gender gender = report.getUser().getGender();
        // 공공데이터 bmi 계산
        Function<HealthCheckup, Double> bmiExtractor = h -> {
            if (h.getHeight5cm() == null || h.getWeight5kg() == null) return null;
            double heightCm = h.getHeight5cm() + 2.5;
            double weightKg = h.getWeight5kg() + 2.5;
            return calculateBmi(weightKg, heightCm);
        };
        // 사용자 수치
        Double bmi = report.getMeasurement().getBmi();
        Double waist = report.getMeasurement().getWaist();
        Integer diastolicBp = report.getBloodPressure().getDiastolic();
        Integer systolicBp = report.getBloodPressure().getSystolic();
        Double hemoglobin = report.getBloodTest().getHemoglobin();
        Integer fastingGlucose = report.getBloodTest().getFastingGlucose();
        Integer ast = report.getBloodTest().getAst();
        Integer alt = report.getBloodTest().getAlt();
        Integer hdl = report.getBloodTest().getHdl();
        Integer gtp = report.getBloodTest().getGtp();
        Integer total_cholesterol = report.getBloodTest().getTotalCholesterol();
        Integer ldl = report.getBloodTest().getLdl();
        Integer egfr = report.getBloodTest().getEgfr();
        Integer triglyceride = report.getBloodTest().getTriglyceride();
        Double creatinine = report.getBloodTest().getCreatinine();


        return ReportResultDto.builder()
                .ageGroup10Yr(BirthDateUtil.getAgeGroup10yr(ageGroup10Yr))
                .nickname(report.getUser().getNickname())
                .gender(report.getUser().getGender())
                .obesityAssessmentDto(ObesityAssessmentDto.builder()
                        .comparingBmi(toComparingBmiDto(bmi, reportResult))
                        .comparingWaist(toComparingWaistDto(waist, gender, reportResult))
                        .build()
                )
                .hypertensionAssessmentDto(
                        HypertensionAssessmentDto.builder()
                                .comparingDiastolicBp(toComparingDiastolicBpDto(diastolicBp, reportResult))
                                .comparingSystolicBp(toComparingSystolicBpDto(systolicBp, reportResult))
                                .build()
                )
                .anemiaAssessmentDto(
                        AnemiaAssessmentDto.builder()
                                .comparingHemoglobin(toComparingHemoglobinDto(hemoglobin, gender, reportResult))
                                .build()
                )
                .diabetesAssessmentDto(
                        DiabetesAssessmentDto.builder()
                                .comparingFastingBloodSugar(toComparingFastingBloodSugarDto(fastingGlucose, reportResult))
                                .build()
                )
                .dyslipidemiaAssessmentDto(
                        DyslipidemiaAssessmentDto.builder()
                                .comparingTotalCholesterol(toComparingTotalCholesterol(total_cholesterol, ageGroup10Yr))
                                .comparingHDL(toComparingHDL(hdl, ageGroup10Yr))
                                .comparingLDL(toComparingLDL(ldl, ageGroup10Yr))
                                .comparingTriglyceride(toComparingTriglyceride(triglyceride, ageGroup10Yr))
                                .build()
                )
                .kidneyDiseaseAssessmentDto(
                        KidneyDiseaseAssessmentDto.builder()
                                .comparingEGfr(toComparingE_GFR(egfr))
                                .comparingSerumCreatinine(toComparingSerumCreatinineDto(creatinine, gender))
                                .build()
                )
                .liverDiseaseAssessmentDto(
                        LiverDiseaseAssessmentDto.builder()
                                .comparingAlt(toComparingAltDto(alt, reportResult))
                                .comparingAst(toComparingAstDto(ast, reportResult))
                                .comparingGammaGtp(toComparingGammaGtpDto(gtp, gender, reportResult))
                                .build()
                )
                .urineProteinAssessmentDto(
                        UrineProteinAssessmentDto.builder()
                                .comparingUrineProtein(
                                        ComparingHealthCheckupResponseDto.ComparingUrineProtein.builder()
                                                .urineTestStatus(report.getUrineTest().getUrineTestStatus())
                                                .healthStatus(classifyUrineProtein(report.getUrineTest().getUrineTestStatus()))
                                                .averageComparison(AverageComparison.NULL.getKey())
                                                .build()
                                )
                                .build()
                )
                .build();
    }

    public static WriteReportRequestDto toWriteReportRequestDto(HealthReportData healthReportData) {
        return WriteReportRequestDto.builder()
                .hospitalName(null)
                .checkupDate(healthReportData.getCheckupDate())
                .measurementDto(
                        MeasurementDto.builder()
                                .height(healthReportData.getMeasurement().getHeight())
                                .weight(healthReportData.getMeasurement().getWeight())
                                .bmi(healthReportData.getMeasurement().getBmi())
                                .waist(healthReportData.getMeasurement().getWaistCircumference())
                                .vision(healthReportData.getMeasurement().getVision())
                                .build()
                )
                .bloodPressureDto(
                        BloodPressureDto.builder()
                                .systolic(healthReportData.getBloodPressure().getSystolic())
                                .diastolic(healthReportData.getBloodPressure().getDiastolic())
                                .bloodPressureStatus(EnumConvertUtil.convertOrNull(BloodPressureStatus.class, healthReportData.getBloodPressure().getStatus()))
                                .build()
                )
                .bloodTestDto(
                        BloodTestDto.builder()
                                //간장질환
                                .alt(healthReportData.getBloodTest().getAlt())
                                .ast(healthReportData.getBloodTest().getAst())
                                // 이상지혈증
                                .totalCholesterol(roundOrNull(healthReportData.getBloodTest().getTotalCholesterol()))
                                .hdl(roundOrNull(healthReportData.getBloodTest().getHdlCholesterol()))
                                .ldl(roundOrNull(healthReportData.getBloodTest().getLdlCholesterol()))
                                .triglyceride(roundOrNull(healthReportData.getBloodTest().getTriglycerides()))
                                //혈색소
                                .hemoglobin(healthReportData.getBloodTest().getHemoglobin())
                                //당뇨병
                                .fastingGlucose(roundOrNull(healthReportData.getBloodTest().getGlucose()))
                                //신장질환
                                .creatinine(healthReportData.getBloodTest().getCreatinine())
                                .egfr(roundOrNull(healthReportData.getBloodTest().getEgfr()))
                                .build()
                )
                .urineTestDto(
                        UrineTestDto.builder()
                                .urineTestStatus(EnumConvertUtil.convertOrNull(UrineTestStatus.class, healthReportData.getUrineTest().getProtein()))
                                .build()
                )
                .imagingTestDto(
                        ImagingTestDto.builder()
                                .imagingTestStatus(EnumConvertUtil.convertOrNull(ImagingTestStatus.class, healthReportData.getImagingTest().getChestXray()))
                                .build()
                )
                .build();
    }

    private static Integer roundOrNull(Double value) {
        return value != null ? (int) Math.round(value) : null;
    }


}
