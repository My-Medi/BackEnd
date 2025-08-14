package com.my_medi.domain.reportResult.entity;

import com.my_medi.api.report.dto.HealthStatus;
import com.my_medi.domain.model.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ReportResult extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_result_id")
    private Long id;

    private int totalScore;
    @Builder.Default
    @Enumerated(EnumType.STRING)
    private HealthStatus totalHealthStatus = HealthStatus.UNKNOWN;

    //비만/복부비만
    private Double averageBmi;
    private Double percentileBmi;
    @Builder.Default
    @Enumerated(EnumType.STRING)
    private HealthStatus bmiHealthStatus = HealthStatus.UNKNOWN;
    private Double averageWaist;
    private Double percentileWaist;
    @Builder.Default
    @Enumerated(EnumType.STRING)
    private HealthStatus waistHealthStatus = HealthStatus.UNKNOWN;

    //고혈압
    private Double averageSystolicBp;
    private Double percentileSystolicBp;
    @Builder.Default
    @Enumerated(EnumType.STRING)
    private HealthStatus systolicBpHealthStatus = HealthStatus.UNKNOWN;
    private Double averageDiastolicBp;
    private Double percentileDiastolicBp;
    @Builder.Default
    @Enumerated(EnumType.STRING)
    private HealthStatus diastolicBpHealthStatus = HealthStatus.UNKNOWN;

    //빈혈
    private Double averageHemoglobin;
    private Double percentileHemoglobin;
    @Builder.Default
    @Enumerated(EnumType.STRING)
    private HealthStatus hemoglobinHealthStatus = HealthStatus.UNKNOWN;

    //당뇨병
    private Double averageFastingBloodSugar;
    private Double percentileFastingBloodSugar;
    @Builder.Default
    @Enumerated(EnumType.STRING)
    private HealthStatus fastingBloodSugarHealthStatus = HealthStatus.UNKNOWN;

    //이상지질혈증
    private Double averageTotalCholesterol;
    private Double percentileTotalCholesterol;
    @Builder.Default
    @Enumerated(EnumType.STRING)
    private HealthStatus totalCholesterolHealthStatus = HealthStatus.UNKNOWN;
    private Double averageHdl;
    private Double percentileHdl;
    @Builder.Default
    @Enumerated(EnumType.STRING)
    private HealthStatus hdlHealthStatus = HealthStatus.UNKNOWN;
    private Double averageLdl;
    private Double percentileLdl;
    @Builder.Default
    @Enumerated(EnumType.STRING)
    private HealthStatus ldlHealthStatus = HealthStatus.UNKNOWN;
    private Double averageTriglyceride;
    private Double percentileTriglyceride;
    @Builder.Default
    @Enumerated(EnumType.STRING)
    private HealthStatus triglycerideHealthStatus = HealthStatus.UNKNOWN;

    //신장질환
    private Double averageSerumCreatine;
    private Double percentileSerumCreatine;
    @Builder.Default
    @Enumerated(EnumType.STRING)
    private HealthStatus creatineHealthStatus = HealthStatus.UNKNOWN;
    private Double averageEGfr;
    private Double percentileEGfr;
    @Builder.Default
    @Enumerated(EnumType.STRING)
    private HealthStatus eGfrHealthStatus = HealthStatus.UNKNOWN;


    //간장질환
    private Double averageAst;
    private Double percentileAst;
    @Builder.Default
    @Enumerated(EnumType.STRING)
    private HealthStatus astHealthStatus = HealthStatus.UNKNOWN;
    private Double averageAlt;
    private Double percentileAlt;
    @Builder.Default
    @Enumerated(EnumType.STRING)
    private HealthStatus altHealthStatus = HealthStatus.UNKNOWN;
    private Double averageGammaGtp;
    private Double percentileGammaGtp;
    @Builder.Default
    @Enumerated(EnumType.STRING)
    private HealthStatus gammaGtpHealthStatus = HealthStatus.UNKNOWN;

    //요단백
    private Double averageUrineProtein;
    private Double percentileProtein;
    @Builder.Default
    @Enumerated(EnumType.STRING)
    private HealthStatus proteinHealthStatus = HealthStatus.UNKNOWN;

    public void calculateTotalScore() {
        List<HealthStatus> items = getAllItemStatuses();

        int actual = items.stream()
                .filter(HealthStatus::isKnown)
                .mapToInt(HealthStatus::getSeverity)
                .sum();

        int validCount = (int) items.stream().filter(HealthStatus::isKnown).count();
        int possible = validCount * HealthStatus.DANGER.getSeverity();      // 유효 개수 * 가중치(max)

        if (possible == 0) { // 전부 UNKNOWN
            this.totalScore = 0;
            this.totalHealthStatus = HealthStatus.UNKNOWN;
            return;
        }

        double ratio = (double) actual / (double) possible; // 0.0 ~ 1.0
        int score = 100 - (int) Math.round(ratio * 100.0);
        this.totalScore = score > 95 ? 95 : score;  // 0 ~ 100
        this.totalHealthStatus = HealthStatus.fromRatio(ratio);
    }

    /** 항목별 HealthStatus를 한 곳에 모아 관리 */
    private List<HealthStatus> getAllItemStatuses() {
        return List.of(
                bmiHealthStatus, waistHealthStatus,
                systolicBpHealthStatus, diastolicBpHealthStatus,
                hemoglobinHealthStatus,
                fastingBloodSugarHealthStatus,
                totalCholesterolHealthStatus, hdlHealthStatus, ldlHealthStatus, triglycerideHealthStatus,
                creatineHealthStatus, eGfrHealthStatus,
                astHealthStatus, altHealthStatus, gammaGtpHealthStatus,
                proteinHealthStatus
        );
    }
}
