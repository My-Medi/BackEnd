package com.my_medi.api.user.controller;

import com.my_medi.api.common.dto.ApiResponseDto;
import com.my_medi.api.expert.dto.ExpertResponseDto;
import com.my_medi.api.expert.mapper.ExpertConverter;
import com.my_medi.api.member.dto.RegisterMemberDto;
import com.my_medi.api.proposal.dto.ProposalRequestDto;
import com.my_medi.api.user.dto.RegisterUserDto;
import com.my_medi.api.user.dto.UserResponseDto;
import com.my_medi.api.user.dto.UserResponseDto.UserProfileDto;
import com.my_medi.api.user.mapper.UserConverter;
import com.my_medi.common.annotation.AuthExpert;
import com.my_medi.common.annotation.AuthUser;
import com.my_medi.common.util.BirthDateUtil;
import com.my_medi.domain.expert.entity.Expert;
import com.my_medi.domain.report.service.ReportQueryService;
import com.my_medi.domain.user.dto.UpdateUserDto;
import com.my_medi.domain.user.entity.User;
import com.my_medi.domain.user.exception.UserHandler;
import com.my_medi.domain.user.repository.UserRepository;

import com.my_medi.domain.user.service.UserCommandService;
import com.my_medi.domain.user.service.UserQueryService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


@Tag(name = "[사용자 페이지] 사용자 계정 API")
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserApiController {

    private final UserCommandService userCommandService;
    private final ReportQueryService reportQueryService;

    @Operation(summary = "사용자 계정을 생성합니다.")
    @PostMapping
    public ApiResponseDto<Long> registerUserAccount(@RequestBody RegisterMemberDto registerMemberDto) {
        return ApiResponseDto.onSuccess(userCommandService.registerUser(registerMemberDto));
    }

    @Operation(summary = "사용자 계정을 수정합니다.")
    @PatchMapping
    public ApiResponseDto<Long> editUserAccount(@AuthUser User user, @RequestBody UpdateUserDto updateUserDto) {
        return ApiResponseDto.onSuccess(userCommandService.updateUserInformation(user.getId(), updateUserDto));
    }

    @Operation(summary = "사용자 본인의 프로필을 조회합니다.[사용 안하는 api]")
    @GetMapping
    public ApiResponseDto<UserProfileDto> getMyProfile(@AuthUser User user) {
        return ApiResponseDto.onSuccess(UserConverter.toUserProfileDto(user));
    }

    @Operation(summary = "[구현 x] 사용자 본인의 계정을 삭제합니다.")
    @DeleteMapping
    public ApiResponseDto<Long> deleteUser(@AuthUser User user) {
        return ApiResponseDto.onSuccess(userCommandService.deleteUserAccount(user.getId()));
    }

    @GetMapping("/profile")
    @Operation(summary = "사용자 자신의 마이홈 환자 페이지를 조회합니다. [마이홈 환자]")
    public ApiResponseDto<UserResponseDto.UserProfileTopDto> getMyUserProfileTop(@AuthUser User user) {
        long reportCount = reportQueryService.getReportCountByUser(user); //
        return ApiResponseDto.onSuccess(UserConverter.toUserProfileTopDto(user,reportCount));
    }

    @Operation(summary = "test age")
    @GetMapping("/age")
    public ApiResponseDto<Integer> getAge(@AuthUser User user) {
        return ApiResponseDto.onSuccess(BirthDateUtil.getAge(user.getBirthDate()));
    }
}
