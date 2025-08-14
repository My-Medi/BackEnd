package com.my_medi.api.user.mapper;

import com.my_medi.api.user.dto.UserResponseDto;
import com.my_medi.common.util.BirthDateUtil;
import com.my_medi.common.util.ProposalMapperUtil;
import com.my_medi.domain.proposal.entity.Proposal;
import com.my_medi.domain.report.entity.Report;
import com.my_medi.domain.report.service.ReportQueryService;
import com.my_medi.domain.user.entity.User;

import java.util.ArrayList;
import java.util.List;

public class UserConverter {

    public static UserResponseDto.UserInfoDto toUserInfoDto(User user) {
        return UserResponseDto.UserInfoDto.builder()
                .userId(user.getId())
                .name(user.getName()) //성명
                .nickname(user.getNickname()) //닉네임
                .birthDate(user.getBirthDate()) //생년월일
                .gender(user.getGender()) //성별
                .email(user.getEmail()) //이메일
                .phoneNumber(user.getPhoneNumber()) //전화번호
                .profileImgUrl(user.getProfileImgUrl()) //프로필이미지
                .height(user.getHeight()) // 키
                .weight(user.getWeight()) // 몸무게
                .build();
    }


    public static UserResponseDto.UserProfileTopDto toUserProfileTopDto(User user, long reportCount){
        return UserResponseDto.UserProfileTopDto.builder()
                .name(user.getName())
                .nickname(user.getNickname())
                .profileImgUrl(user.getProfileImgUrl())
                .age(BirthDateUtil.getAge(user.getBirthDate()))
                .height(user.getHeight())
                .weight(user.getWeight())
                .reportCount(reportCount)
                .build();
    }


    public static UserResponseDto.RequestingUserInfoDto toRequestingUserInfoDto(User user, Proposal proposal, Report latestReport) {
        return UserResponseDto.RequestingUserInfoDto.builder()
                .userId(user.getId())
                .accountRegisterDate(user.getCreatedDate().toLocalDate()) //회원가입 날짜
                .nickname(user.getNickname()) // 닉네임
                .age(BirthDateUtil.getAge(user.getBirthDate())) //나이
                .gender(user.getGender()) //성별
                .height(user.getHeight()) //키
                .weight(user.getWeight()) //몸무게
                .goal(proposal.getGoal())
                .reportRegisterDate(latestReport != null ? latestReport.getCheckupDate() : null) // 국가건강검진일 - 리포트(없으면 Null)
                .requestNote(proposal.getRequestNote()) //요청사항 - proposal
                .healthInterests(ProposalMapperUtil.extractHealthInterests(proposal)) // 건강 관심 분야 - proposal
                .abnormalCheckItems(ProposalMapperUtil.extractAbnormalCheckItems(proposal)) // 건강검진 이상 수치 - proposal
                .build();
    }

    public static UserResponseDto.UserRequestNoteDto toUserRequestNoteDto(User user, Proposal proposal) {
        return UserResponseDto.UserRequestNoteDto.builder()
                .userId(user.getId())
                .requestNote(proposal.getRequestNote())
                .build();
    }

}
