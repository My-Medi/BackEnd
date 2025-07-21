package com.my_medi.domain.proposal.entity;

import com.my_medi.api.proposal.dto.AbnormalValueDto;
import com.my_medi.api.proposal.dto.HealthInterestsDto;
import com.my_medi.api.proposal.dto.HelpTopicDto;
import com.my_medi.api.proposal.dto.ProposalDto;
import com.my_medi.domain.model.entity.BaseTimeEntity;
import com.my_medi.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Proposal extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // 1. 직업 및 생활패턴
    @Column(name = "life_description", nullable = false, length = 50)
    private String lifeDescription;

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

    public void updateLifeDescriptionnGoal(ProposalDto proposalDto) {
        this.lifeDescription = proposalDto.getLifeDescription();
        this.goal = proposalDto.getGoal();
    }

    public void updateHealthInterests(ProposalDto proposalDto) {
        HealthInterestsDto hid = proposalDto.getHealthInterestsDto();
        this.weightManagement = hid.getWeightManagement();
        this.bloodSugarControl = hid.getBloodSugarControl();
        this.cholesterolControl = hid.getCholesterolControl();
        this.bloodPressureControl = hid.getBloodPressureControl();
        this.liverFunctionCare = hid.getLiverFunctionCare();
        this.sleepRecovery = hid.getSleepRecovery();
        this.dietImprovement = hid.getDietImprovement();
        this.exerciseRoutine = hid.getExerciseRoutine();
        this.stressAndLifestyle = hid.getStressAndLifestyle();
    }

    public void updateAbnormalValue(ProposalDto proposalDto) {
        AbnormalValueDto avd = proposalDto.getAbnormalValueDto();
        this.fastingBloodSugar = avd.getFastingBloodSugar();
        this.cholesterolLdl = avd.getCholesterolLdl();
        this.bloodPressure = avd.getBloodPressure();
        this.liverEnzymes = avd.getLiverEnzymes();
        this.bmiOrBodyFat = avd.getBmiOrBodyFat();
        this.noHealthCheckResult = avd.getNoHealthCheckResult();
    }

    public void updateHelpTopic(ProposalDto proposalDto) {
        HelpTopicDto htd = proposalDto.getHelpTopicDto();
        this.dietitian = htd.getDietitian();
        this.healthManager = htd.getHealthManager();
        this.wellnessCoach = htd.getWellnessCoach();
        this.exerciseTherapist = htd.getExerciseTherapist();
        this.recommendForMe = htd.getRecommendForMe();
    }
}
