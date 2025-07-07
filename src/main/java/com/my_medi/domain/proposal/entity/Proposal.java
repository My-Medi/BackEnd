package com.my_medi.domain.proposal.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Proposal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long proposalId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    // 1. 직업 및 생활패턴
    @Column(name = "life_descreption", nullable = false, length = 50)
    private String lifeDescreption;

    // 2. 건강 관심 분야(중복 선택 가능)
    private Boolean weightManagement;
    private Boolean bloodSugarControl;
    private Boolean cholesterolControl;
    private Boolean bloodPressureControl;
    private Boolean liverFunctionCare;
    private Boolean sleepRecovery;
    private Boolean dietImprovement;
    private Boolean exerciseRoutine;
    private Boolean stressAndLifestyle;

    // 3. 최근 건강검진 결과 이상 수치가 있었던 항목이 있다면 선택해주세요.
    private Boolean fastingBloodSugar;
    private Boolean cholesterolLdl;
    private Boolean bloodPressure;
    private Boolean liverEnzymes;
    private Boolean bmiOrBodyFat;
    private Boolean noHealthCheckResult;

    // 4. 어떤 전문가의 도움을 받고 싶으신가요?
    private Boolean dietitian;
    private Boolean healthManager;
    private Boolean wellnessCoach;
    private Boolean exerciseTherapist;
    private Boolean recommendForMe;

    // 5. 목표나 기대하는 변화가 있다면 적어주세요.
    @Column(name = "goal", nullable = false, length = 50)
    private String goal;
}
