package com.my_medi.api.report.dto;

<<<<<<< HEAD
import com.my_medi.domain.report.enums.UrineTestStatus;
=======
>>>>>>> 0440ed4 ([MEDI-81] feat: create latest user report's summay API)
import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReportSummaryDto {
    private Long id;
    private Long userId;
    private Integer round;
    private LocalDate checkupDate;

    private ObesityDto obesity;
    private HypertensionDto hypertension;
    private DiabetesDto diabetes;
    private KidneyDto kidney;
    private LiverDto liver;
    private AnemiaDto anemia;
    private DyslipidemiaDto dyslipidemia;
    private UrineDto urine;

    @Getter
    @Builder
    public static class ObesityDto {
        private Double bmi;
        private String bmiCategory;
        private Double waist;
    }

    @Getter
    @Builder
    public static class HypertensionDto {
        private Integer systolic;
        private Integer diastolic;
    }

    @Getter
    @Builder
    public static class DiabetesDto {
        private Integer fastingGlucose;
    }

    @Getter
    @Builder
    public static class KidneyDto {
        private Double creatinine;
        private Integer egfr;
    }

    @Getter
    @Builder
    public static class LiverDto {
        private Integer ast;
        private Integer alt;
        private Integer gtp;
    }

    @Getter
    @Builder
    public static class AnemiaDto {
        private Double hemoglobin;
    }

    @Getter
    @Builder
    public static class DyslipidemiaDto {
        private Integer totalCholesterol;
        private Integer hdl;
        private Integer triglyceride;
        private Integer ldl;
    }

    @Getter
    @Builder
    public static class UrineDto {
<<<<<<< HEAD
        private UrineTestStatus urineTestStatus;
=======
        private String proteinuria;
>>>>>>> 0440ed4 ([MEDI-81] feat: create latest user report's summay API)
    }
}
