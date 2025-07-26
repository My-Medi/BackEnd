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
    public class ComparingBmiDto{
        private Double bmi;
        private Double averageBmi;
        private Double percentageBmi;
    }

    @Data
    @Builder
    public class ComparingWaistDto{
        private Double waist;
        private Double averageWaist;
        private Double percentageWaist;
    }

    @Data
    @Builder
    public class ComparingSystolicBpDto{
        private Integer systolicBp;
        private Double averageSystolicBp;
        private Double percentageSystolicBp;
    }

    @Data
    @Builder
    public class ComparingDiastolicBpDto{
        private Integer diastolicBp;
        private Double averageDiastolicBp;
        private Double percentageDiastolicBp;
    }
    @Data
    @Builder
    public class ComparingHemoglobinDto{
        private Double hemoglobin;
        private Double averageHemoglobin;
        private Double percentageHemoglobin;
    }
    @Data
    @Builder
    public class ComparingFastingBloodSugarDto{
        private Integer fastingBloodSugar;
        private Double averageFastingBloodSugar;
        private Double percentageFastingBloodSugar;
    }

    @Data
    @Builder
    public class ComparingSerumCreatinineDto{
        private Double serumCreatinine;
        @Builder.Default
        private Double averageSerumCreatinine = 0.8;
    }

    @Data
    @Builder
    public class ComparingAstDto{
        private Integer ast;
        private Double averageAst;
        private Double percentageAst;
    }

    @Data
    @Builder
    public class ComparingAltDto{
        private Integer alt;
        private Double averageAlt;
        private Double percentageAlt;
    }
    @Data
    @Builder
    public class ComparingGammaGtpDto{
        private Integer gammaGtp;
        private Double averageGammaGtp;
        private Double percentageGammaGtp;
    }


}
