package com.my_medi.domain.proposal.entity;

import com.my_medi.api.proposal.dto.AbnormalValueDto;
import com.my_medi.api.proposal.dto.HealthInterestsDto;
import com.my_medi.api.proposal.dto.HelpTopicDto;
import com.my_medi.api.proposal.dto.WriteProposalDto;
import com.my_medi.domain.model.entity.BaseTimeEntity;
import com.my_medi.domain.proposal.repository.ProposalRepository;
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

    @ManyToOne(fetch = FetchType.LAZY)
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

    // 제안서 생성
    public static Proposal create(User user, WriteProposalDto writeProposalDto) {
        Proposal proposal = new Proposal();

        proposal.user = user;
        proposal.lifeDescription = writeProposalDto.getLifeDescription();
        proposal.goal = writeProposalDto.getGoal();

        HealthInterestsDto h = writeProposalDto.getHealthInterestsDto();
        proposal.weightManagement = h.getWeightManagement();
        proposal.bloodSugarControl = h.getBloodSugarControl();
        proposal.cholesterolControl = h.getCholesterolControl();
        proposal.bloodPressureControl = h.getBloodPressureControl();
        proposal.liverFunctionCare = h.getLiverFunctionCare();
        proposal.sleepRecovery = h.getSleepRecovery();
        proposal.dietImprovement = h.getDietImprovement();
        proposal.exerciseRoutine = h.getExerciseRoutine();
        proposal.stressAndLifestyle = h.getStressAndLifestyle();

        AbnormalValueDto a = writeProposalDto.getAbnormalValueDto();
        proposal.fastingBloodSugar = a.getFastingBloodSugar();
        proposal.cholesterolLdl = a.getCholesterolLdl();
        proposal.bloodPressure = a.getBloodPressure();
        proposal.liverEnzymes = a.getLiverEnzymes();
        proposal.bmiOrBodyFat = a.getBmiOrBodyFat();
        proposal.noHealthCheckResult = a.getNoHealthCheckResult();

        HelpTopicDto t = writeProposalDto.getHelpTopicDto();
        proposal.dietitian = t.getDietitian();
        proposal.healthManager = t.getHealthManager();
        proposal.wellnessCoach = t.getWellnessCoach();
        proposal.exerciseTherapist = t.getExerciseTherapist();
        proposal.recommendForMe = t.getRecommendForMe();

        return proposal;
    }

    public void update(WriteProposalDto writeProposalDto) {
        this.lifeDescription = writeProposalDto.getLifeDescription();
        this.goal = writeProposalDto.getGoal();

        HealthInterestsDto h = writeProposalDto.getHealthInterestsDto();
        this.weightManagement = h.getWeightManagement();
        this.bloodSugarControl = h.getBloodSugarControl();
        this.cholesterolControl = h.getCholesterolControl();
        this.bloodPressureControl = h.getBloodPressureControl();
        this.liverFunctionCare = h.getLiverFunctionCare();
        this.sleepRecovery = h.getSleepRecovery();
        this.dietImprovement = h.getDietImprovement();
        this.exerciseRoutine = h.getExerciseRoutine();
        this.stressAndLifestyle = h.getStressAndLifestyle();

        AbnormalValueDto a = writeProposalDto.getAbnormalValueDto();
        this.fastingBloodSugar = a.getFastingBloodSugar();
        this.cholesterolLdl = a.getCholesterolLdl();
        this.bloodPressure = a.getBloodPressure();
        this.liverEnzymes = a.getLiverEnzymes();
        this.bmiOrBodyFat = a.getBmiOrBodyFat();
        this.noHealthCheckResult = a.getNoHealthCheckResult();

        HelpTopicDto t = writeProposalDto.getHelpTopicDto();
        this.dietitian = t.getDietitian();
        this.healthManager = t.getHealthManager();
        this.wellnessCoach = t.getWellnessCoach();
        this.exerciseTherapist = t.getExerciseTherapist();
        this.recommendForMe = t.getRecommendForMe();
    }

}
