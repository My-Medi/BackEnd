package com.my_medi.api.report.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ComparingReportResponseDto {

    private int totalDataSize;
    private int ageGroup10Yr;

    private ComparingBmiDto comparingBmiDto;
    private ComparingWaistDto comparingWaistDto;

    private ComparingSystolicBpDto comparingSystolicBpDto;
    private ComparingDiastolicBpDto comparingDiastolicBpDto;

    private ComparingHemoglobinDto comparingHemoglobinDto;
    private ComparingFastingBloodSugarDto comparingFastingBloodSugarDto;
    private ComparingSerumCreatinineDto comparingSerumCreatinineDto;

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


}
