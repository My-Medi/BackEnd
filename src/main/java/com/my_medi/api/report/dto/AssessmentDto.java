package com.my_medi.api.report.dto;

import com.my_medi.api.healthCheckup.dto.ComparingHealthCheckupResponseDto;
import lombok.Builder;
import lombok.Data;

import static com.my_medi.api.healthCheckup.dto.ComparingHealthCheckupResponseDto.*;

public class AssessmentDto {
    //비만/복부비만
    @Data
    @Builder
    public static class ObesityAssessmentDto{
        private ComparingBmi comparingBmiDto;
        private ComparingWaist comparingWaistDto;
    }


    //고혈압
    @Data
    @Builder
    public static class HypertensionAssessmentDto{
        private ComparingSystolicBp comparingSystolicBpDto;
        private ComparingDiastolicBp comparingDiastolicBpDto;
    }

    //빈혈
    @Data
    @Builder
    public static class AnemiaAssessmentDto{
        private ComparingHemoglobin comparingHemoglobinDto;
    }

    //당뇨병
    @Data
    @Builder
    public static class DiabetesAssessmentDto{
        private ComparingFastingBloodSugar comparingFastingBloodSugarDto;
    }


    //이상지질혈증
    @Data
    @Builder
    public static class DyslipidemiaAssessmentDto{
        private ComparingTotalCholesterol comparingTotalCholesterol;
        private ComparingHDL comparingHDL;
        private ComparingTriglyceride comparingTriglyceride;
        private ComparingLDL comparingLDL;
    }


    //신장질환
    @Data
    @Builder
    public static class KidneyDiseaseAssessmentDto{
        private ComparingSerumCreatinine comparingSerumCreatinineDto;
        private ComparingE_GFR comparingEGfr;
    }


    //간장질환
    @Data
    @Builder
    public static class LiverDiseaseAssessmentDto{
        private ComparingAst comparingAstDto;
        private ComparingAlt comparingAltDto;
        private ComparingGammaGtp comparingGammaGtpDto;
    }
}
