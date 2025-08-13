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
        private ComparingBmi comparingBmi;
        private ComparingWaist comparingWaist;
        private AverageComparisonResult averageComparisonResult;
    }


    //고혈압
    @Data
    @Builder
    public static class HypertensionAssessmentDto{
        private ComparingSystolicBp comparingSystolicBp;
        private ComparingDiastolicBp comparingDiastolicBp;
        private AverageComparisonResult averageComparisonResult;

    }

    //빈혈
    @Data
    @Builder
    public static class AnemiaAssessmentDto{
        private ComparingHemoglobin comparingHemoglobin;
        private AverageComparisonResult averageComparisonResult;

    }

    //당뇨병
    @Data
    @Builder
    public static class DiabetesAssessmentDto{
        private ComparingFastingBloodSugar comparingFastingBloodSugar;
        private AverageComparisonResult averageComparisonResult;

    }


    //이상지질혈증
    @Data
    @Builder
    public static class DyslipidemiaAssessmentDto{
        private ComparingTotalCholesterol comparingTotalCholesterol;
        private ComparingHDL comparingHDL;
        private ComparingTriglyceride comparingTriglyceride;
        private ComparingLDL comparingLDL;
        private AverageComparisonResult averageComparisonResult;

    }


    //신장질환
    @Data
    @Builder
    public static class KidneyDiseaseAssessmentDto{
        private ComparingSerumCreatinine comparingSerumCreatinine;
        private ComparingE_GFR comparingEGfr;
        private AverageComparisonResult averageComparisonResult;

    }


    //간장질환
    @Data
    @Builder
    public static class LiverDiseaseAssessmentDto{
        private ComparingAst comparingAst;
        private ComparingAlt comparingAlt;
        private ComparingGammaGtp comparingGammaGtp;
        private AverageComparisonResult averageComparisonResult;
    }

    @Data
    @Builder
    public static class UrineProteinAssessmentDto{
        private ComparingUrineProtein comparingUrineProtein;
    }
}
