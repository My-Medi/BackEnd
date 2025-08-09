package com.my_medi.api.user.mapper;

import com.my_medi.api.user.dto.UserResponseDto;
import com.my_medi.api.user.dto.UserResponseDto.UserProfileDto;
import com.my_medi.common.util.BirthDateUtil;
import com.my_medi.domain.proposal.entity.Proposal;
import com.my_medi.domain.report.entity.Report;
import com.my_medi.domain.report.service.ReportQueryService;
import com.my_medi.domain.user.entity.User;

import java.util.ArrayList;
import java.util.List;

public class UserConverter {

    public static UserProfileDto toUserProfileDto(User user) {
        return UserProfileDto.builder()
                .userid(user.getId())
                .name(user.getName()) //성명
                .nickname(user.getNickname()) //닉네임
                .birthDate(user.getBirthDate()) //생년월일
                .gender(user.getGender()) //성별
                .email(user.getEmail()) //이메일
                .phoneNumber(user.getPhoneNumber()) //전화번호
                .profileImgUrl(user.getProfileImgUrl()) //프로필이미지
                .height(user.getHeight()) // 키
                .weight(user.getWeight()) // 몸무게
                .role(user.getRole())
                .build();
    }


    public static UserResponseDto.UserProfileTopDto toUserProfileTopDto(User user, long reportCount){
        return UserResponseDto.UserProfileTopDto.builder()
                .name(user.getName())
                .nickname(user.getNickname())
                .age(BirthDateUtil.getAge(user.getBirthDate()))
                .height(user.getHeight())
                .weight(user.getWeight())
                .reportCount(reportCount)
                .build();
    }


    public static UserResponseDto.UserInfoDto toUserInfoDto(User user, Proposal proposal, Report latestReport) {
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

        List<String> abnormalCheckItems = new ArrayList<>();
        if (Boolean.TRUE.equals(proposal.getFastingBloodSugar())) abnormalCheckItems.add("공복 혈당");
        if (Boolean.TRUE.equals(proposal.getCholesterolLdl())) abnormalCheckItems.add("총 콜레스테롤");
        if (Boolean.TRUE.equals(proposal.getBloodPressure())) abnormalCheckItems.add("혈압");
        if (Boolean.TRUE.equals(proposal.getLiverEnzymes())) abnormalCheckItems.add("간수치");
        if (Boolean.TRUE.equals(proposal.getBmiOrBodyFat())) abnormalCheckItems.add("BMI/체지방률");
        if (Boolean.TRUE.equals(proposal.getNoHealthCheckResult())) abnormalCheckItems.add("건강검진 결과가 없다.");

        return UserResponseDto.UserInfoDto.builder()
                .createdDate(user.getCreatedDate().toLocalDate()) //회원가입 날짜
                .nickname(user.getNickname()) // 닉네임
                .age(BirthDateUtil.getAge(user.getBirthDate())) //나이
                .gender(user.getGender()) //성별
                .height(user.getHeight()) //키
                .weight(user.getWeight()) //몸무게
                .checkupDate(latestReport != null ? latestReport.getCheckupDate() : null) // 국가건강검진일 - 리포트(없으면 Null)
                .requestNote(proposal.getRequestNote()) //요청사항 - proposal
                .healthInterests(healthInterests) // 건강 관심 분야 - proposal
                .abnormalCheckItems(abnormalCheckItems) // 건강검진 이상 수치 - proposal
                .build();
    }

}
