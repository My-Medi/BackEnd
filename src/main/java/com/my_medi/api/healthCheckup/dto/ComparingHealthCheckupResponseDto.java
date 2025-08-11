package com.my_medi.api.healthCheckup.dto;

import com.my_medi.api.report.dto.HealthStatus;
import com.my_medi.common.consts.StaticVariable;
import lombok.Builder;
import lombok.Data;

import static com.my_medi.common.consts.StaticVariable.CREATININE_AVERAGE;

@Data
@Builder
public class ComparingHealthCheckupResponseDto {

    @Data
    @Builder
    public static class ComparingBmi{
        private Double bmi;
        private Double averageBmi;
        private Double percentageBmi;
        private HealthStatus healthStatus;
    }

    @Data
    @Builder
    public static class ComparingWaist{
        private Double waist;
        private Double averageWaist;
        private Double percentageWaist;
        private HealthStatus healthStatus;
    }

    @Data
    @Builder
    public static class ComparingSystolicBp{
        private Integer systolicBp;
        private Double averageSystolicBp;
        private Double percentageSystolicBp;
        private HealthStatus healthStatus;
    }

    @Data
    @Builder
    public static class ComparingDiastolicBp{
        private Integer diastolicBp;
        private Double averageDiastolicBp;
        private Double percentageDiastolicBp;
        private HealthStatus healthStatus;
    }
    @Data
    @Builder
    public static class ComparingHemoglobin{
        private Double hemoglobin;
        private Double averageHemoglobin;
        private Double percentageHemoglobin;
        private HealthStatus healthStatus;
    }
    @Data
    @Builder
    public static class ComparingFastingBloodSugar{
        private Integer fastingBloodSugar;
        private Double averageFastingBloodSugar;
        private Double percentageFastingBloodSugar;
        private HealthStatus healthStatus;
    }

    @Data
    @Builder
    public static class ComparingSerumCreatinine{
        private Double serumCreatinine;
        @Builder.Default
        private Double averageSerumCreatinine = CREATININE_AVERAGE;
        private HealthStatus healthStatus;
    }

    @Data
    @Builder
    public static class ComparingAst{
        private Integer ast;
        private Double averageAst;
        private Double percentageAst;
        private HealthStatus healthStatus;
    }

    @Data
    @Builder
    public static class ComparingAlt{
        private Integer alt;
        private Double averageAlt;
        private Double percentageAlt;
        private HealthStatus healthStatus;
    }
    @Data
    @Builder
    public static class ComparingGammaGtp{
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
