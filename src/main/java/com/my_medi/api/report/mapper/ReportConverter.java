package com.my_medi.api.report.mapper;

import com.my_medi.api.report.dto.ReportResponseDto.UserReportDto;
import com.my_medi.api.report.dto.*;
import com.my_medi.domain.report.entity.*;


public class ReportConverter {
    public static UserReportDto toUserReportDto(Report report) {
        return UserReportDto.builder()
                .id(report.getId())
                .userId(report.getUser().getId())
                .checkupDate(report.getCheckupDate())
                .round(report.getRound())
                .measurement(report.getMeasurement())
                .bloodPressure(report.getBloodPressure())
                .bloodTest(report.getBloodTest())
                .urineTest(report.getUrineTest())
                .imagingTest(report.getImagingTest())
                .interview(report.getInterview())
                .additionalTest(report.getAdditionalTest())
                .build();
    }

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
}
