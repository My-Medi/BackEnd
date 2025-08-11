package com.my_medi.common.util;

import com.my_medi.domain.proposal.entity.Proposal;

import java.util.ArrayList;
import java.util.List;

public class ProposalMapperUtil {

    public static List<String> extractHealthInterests(Proposal proposal) {
        List<String> healthInterests = new ArrayList<>();
        if (Boolean.TRUE.equals(proposal.getWeightManagement())) healthInterests.add("체중 관리");
        if (Boolean.TRUE.equals(proposal.getBloodSugarControl())) healthInterests.add("혈당 관리");
        if (Boolean.TRUE.equals(proposal.getCholesterolControl())) healthInterests.add("콜레스테롤 관리");
        if (Boolean.TRUE.equals(proposal.getLiverFunctionCare())) healthInterests.add("간 기능 관리");
        if (Boolean.TRUE.equals(proposal.getSleepRecovery())) healthInterests.add("수면 회복");
        if (Boolean.TRUE.equals(proposal.getDietImprovement())) healthInterests.add("식습관 개선");
        if (Boolean.TRUE.equals(proposal.getExerciseRoutine())) healthInterests.add("운동 루틴 설정");
        if (Boolean.TRUE.equals(proposal.getStressAndLifestyle())) healthInterests.add("스트레스/생활습관");
        if (Boolean.TRUE.equals(proposal.getOther())) healthInterests.add("기타");
        return healthInterests;
    }

    public static List<String> extractAbnormalCheckItems(Proposal proposal) {
        List<String> abnormalCheckItems = new ArrayList<>();
        if (Boolean.TRUE.equals(proposal.getFastingBloodSugar())) abnormalCheckItems.add("공복 혈당");
        if (Boolean.TRUE.equals(proposal.getCholesterolLdl())) abnormalCheckItems.add("LDL 콜레스테롤");
        if (Boolean.TRUE.equals(proposal.getBloodPressure())) abnormalCheckItems.add("혈압");
        if (Boolean.TRUE.equals(proposal.getLiverEnzymes())) abnormalCheckItems.add("간수치");
        if (Boolean.TRUE.equals(proposal.getBmiOrBodyFat())) abnormalCheckItems.add("BMI/체지방률");
        if (Boolean.TRUE.equals(proposal.getNoHealthCheckResult())) abnormalCheckItems.add("건강검진 결과가 없다.");
        return abnormalCheckItems;
    }
}
