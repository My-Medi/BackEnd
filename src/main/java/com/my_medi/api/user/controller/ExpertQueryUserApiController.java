package com.my_medi.api.user.controller;

import com.my_medi.api.common.dto.ApiResponseDto;
import com.my_medi.api.consultation.validator.ExpertAllowedToViewUserInfoValidator;
import com.my_medi.api.user.dto.UserResponseDto;
import com.my_medi.api.user.mapper.UserConverter;
import com.my_medi.common.annotation.AuthExpert;
import com.my_medi.domain.expert.entity.Expert;
import com.my_medi.domain.proposal.entity.Proposal;
import com.my_medi.domain.proposal.service.ProposalQueryService;
import com.my_medi.domain.report.entity.Report;
import com.my_medi.domain.report.service.ReportQueryService;
import com.my_medi.domain.user.entity.User;
import com.my_medi.domain.user.exception.UserErrorStatus;
import com.my_medi.domain.user.exception.UserHandler;
import com.my_medi.domain.user.service.UserQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "[전문가 페이지] 사용자 조회 API")
@RestController
@RequestMapping("/api/v1/experts/users")
@RequiredArgsConstructor
public class ExpertQueryUserApiController {

    private final UserQueryService userQueryService;
    private final ProposalQueryService proposalQueryService;
    private final ReportQueryService reportQueryService;
    private final ExpertAllowedToViewUserInfoValidator expertAllowedToViewUserInfoValidator;

    @Operation(summary = "사용자 프로필 정보를 조회합니다.")
    @GetMapping("/{userId}/approved")
    public ApiResponseDto<UserResponseDto.UserProfileDto> getUserProfile(@AuthExpert Expert expert,
                                                                         @PathVariable Long userId) {
        // 전문가와 유저가 매칭된 상태인지 확인 (ACCEPTED) -> 유저 정보 조회 -> DTO로 변환해서 반환
        expertAllowedToViewUserInfoValidator.validateExpertHasAcceptedUser(expert.getId(), userId);

        User user = userQueryService.getUserById(userId);

        return ApiResponseDto.onSuccess(UserConverter.toUserProfileDto(user));
    }

    @Operation(summary = "[건강관리요청서 확인하기]")
    @GetMapping("/{userId}/info")
    public ApiResponseDto<UserResponseDto.UserInfoDto> getUserInfo(@AuthExpert Expert expert,
                                                                   @PathVariable Long userId){
        User user = userQueryService.getUserById(userId);
        Proposal proposal = proposalQueryService.getProposalByUserId(userId);
        Report latestReport = reportQueryService.getLatestReportByUserId(userId);
        return ApiResponseDto.onSuccess(UserConverter.toUserInfoDto(user,proposal,latestReport));
    }


}
