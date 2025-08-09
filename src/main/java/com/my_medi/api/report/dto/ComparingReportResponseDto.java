package com.my_medi.api.report.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ComparingReportResponseDto {

    private int totalDataSize;
    private int ageGroup10Yr;

    //비만/복부비만
    private ComparingBmiDto comparingBmiDto;
    private ComparingWaistDto comparingWaistDto;

    //고혈압
    private ComparingSystolicBpDto comparingSystolicBpDto;
    private ComparingDiastolicBpDto comparingDiastolicBpDto;

    //빈혈
    private ComparingHemoglobinDto comparingHemoglobinDto;

    //당뇨병
    private ComparingFastingBloodSugarDto comparingFastingBloodSugarDto;

    //이상지질혈증
    private ComparingTotalCholesterol comparingTotalCholesterol;
    private ComparingHDL comparingHDL;
    private ComparingTriglyceride comparingTriglyceride;
    private ComparingLDL comparingLDL;

    //신장질환
    private ComparingSerumCreatinineDto comparingSerumCreatinineDto;
    private ComparingE_GFR comparingEGfr;

    //간장질환
    private ComparingAstDto comparingAstDto;
    private ComparingAltDto comparingAltDto;
    private ComparingGammaGtpDto comparingGammaGtpDto;


    @Data
    @Builder
    public static class ComparingBmiDto{
        private Double bmi;
        private Double averageBmi;
        private Double percentageBmi;
        private HealthStatus healthStatus;
    }

    @Data
    @Builder
    public static class ComparingWaistDto{
        private Double waist;
        private Double averageWaist;
        private Double percentageWaist;
        private HealthStatus healthStatus;
    }

    @Data
    @Builder
    public static class ComparingSystolicBpDto{
        private Integer systolicBp;
        private Double averageSystolicBp;
        private Double percentageSystolicBp;
        private HealthStatus healthStatus;
    }

    @Data
    @Builder
    public static class ComparingDiastolicBpDto{
        private Integer diastolicBp;
        private Double averageDiastolicBp;
        private Double percentageDiastolicBp;
        private HealthStatus healthStatus;
    }
    @Data
    @Builder
    public static class ComparingHemoglobinDto{
        private Double hemoglobin;
        private Double averageHemoglobin;
        private Double percentageHemoglobin;
        private HealthStatus healthStatus;
    }
    @Data
    @Builder
    public static class ComparingFastingBloodSugarDto{
        private Integer fastingBloodSugar;
        private Double averageFastingBloodSugar;
        private Double percentageFastingBloodSugar;
        private HealthStatus healthStatus;
    }

    @Data
    @Builder
    public static class ComparingSerumCreatinineDto{
        private Double serumCreatinine;
        @Builder.Default
        private Double averageSerumCreatinine = 0.8;
        private HealthStatus healthStatus;
    }

    @Data
    @Builder
    public static class ComparingAstDto{
        private Integer ast;
        private Double averageAst;
        private Double percentageAst;
        private HealthStatus healthStatus;
    }

    @Data
    @Builder
    public static class ComparingAltDto{
        private Integer alt;
        private Double averageAlt;
        private Double percentageAlt;
        private HealthStatus healthStatus;
    }
    @Data
    @Builder
    public static class ComparingGammaGtpDto{
        private Integer gammaGtp;
        private Double averageGammaGtp;
        private Double percentageGammaGtp;
        private HealthStatus healthStatus;
    }

    @Data
    @Builder
    public static class ComparingTotalCholesterol{
        private Integer totalCholesterol;
        private Integer averageTotalCholesterol;
        private HealthStatus healthStatus;
    }

    @Data
    @Builder
    public static class ComparingHDL{
        private Integer hdl;
        private Integer averageHDL;
        private HealthStatus healthStatus;
    }

    @Data
    @Builder
    public static class ComparingTriglyceride{
        private Integer triglyceride;
        private Integer averageTriglyceride;
        private HealthStatus healthStatus;
    }

    @Data
    @Builder
    public static class ComparingLDL{
        private Integer ldl;
        private Integer averageLDL;
        private HealthStatus healthStatus;
    }

    @Data
    @Builder
    public static class ComparingE_GFR{
        private Integer e_gfr;
        private Integer averageE_GFR;
        private HealthStatus healthStatus;
    }

}
