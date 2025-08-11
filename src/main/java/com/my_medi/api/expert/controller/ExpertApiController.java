package com.my_medi.api.expert.controller;

import com.my_medi.api.common.dto.ApiResponseDto;
import com.my_medi.api.expert.dto.ExpertResponseDto;
import com.my_medi.api.expert.dto.RegisterExpertDto;
import com.my_medi.api.expert.mapper.ExpertConverter;
import com.my_medi.api.member.dto.RegisterMemberDto;
import com.my_medi.api.user.dto.UserResponseDto;
import com.my_medi.api.user.mapper.UserConverter;
import com.my_medi.common.annotation.AuthExpert;
import com.my_medi.common.annotation.AuthUser;
import com.my_medi.domain.expert.dto.UpdateProfileDto;
import com.my_medi.domain.expert.dto.UpdateResumeDto;
import com.my_medi.domain.expert.entity.Expert;
import com.my_medi.domain.expert.exception.ExpertHandler;
import com.my_medi.domain.expert.service.ExpertCommandService;
import com.my_medi.domain.expert.service.ExpertQueryService;
import com.my_medi.domain.user.entity.User;
import com.my_medi.domain.user.exception.UserHandler;
import com.my_medi.domain.user.service.UserCommandService;
import com.my_medi.domain.user.service.UserQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import com.my_medi.api.expert.mapper.ExpertConverter;

@Tag(name = "[전문가 페이지] 전문가 계정 API")
@RestController
@RequestMapping("/api/v1/experts")
@RequiredArgsConstructor
public class ExpertApiController {

    private final ExpertCommandService expertCommandService;
    private final ExpertQueryService expertQueryService;

    @Operation(summary = "전문가 계정을 생성합니다.[회원가입 플로우 전문가 ver.]")
    @PostMapping
    public ApiResponseDto<Long> registerExpertAccount(@RequestBody RegisterExpertDto registerExpertDto) {
        return ApiResponseDto.onSuccess(expertCommandService.registerExpert(registerExpertDto));
    }

    @Operation(summary = "전문가 계정의 회원 정보를 수정합니다.[회원정보 수정페이지-전문가] ")
    @PatchMapping
    public ApiResponseDto<Long> editProfileAccount(@AuthExpert Expert expert, @RequestBody UpdateProfileDto updateProfileDto) {
        return ApiResponseDto.onSuccess(expertCommandService.updateProfile(expert.getId(), updateProfileDto));
    }

    @Operation(summary = "전문가 계정의 이력서를 조회합니다. [이력서 관리]")
    @GetMapping("/resume")
    public ApiResponseDto<ExpertResponseDto.ExpertResumeDto> getMyExpertResumeProfile(@AuthExpert Expert expert) {
        return ApiResponseDto.onSuccess(ExpertConverter.toExpertResumeDto(expert));
    }

    @Operation(summary = "전문가 계정의 이력서를 수정합니다.[이력서 관리] ")
    @PatchMapping("/resume")
    public ApiResponseDto<Long> editExpertAccount(@AuthExpert Expert expert, @RequestBody UpdateResumeDto updateResumeDto) {
        return ApiResponseDto.onSuccess(expertCommandService.updateResume(expert.getId(), updateResumeDto));
    }

    @Operation(summary = "전문가 내 프로필의 이력서, 회원 정보 모든 내용을 조회합니다.[회원정보 수정 전문가]")
    @GetMapping
    public ApiResponseDto<ExpertResponseDto.ExpertInfoDto> getMyExpertProfile(@AuthExpert Expert expert) {
        return ApiResponseDto.onSuccess(ExpertConverter.toExpertInfoDto(expert));
    }

    @Operation(summary = "전문가 본인의 계정을 삭제합니다.")
    @DeleteMapping
    public ApiResponseDto<Void> deleteUser(@AuthExpert Expert expert) {
        expertCommandService.deleteExpertAccount(expert.getId());
        return ApiResponseDto.onSuccess(null);
    }

    @GetMapping("/profile")
    @Operation(summary = "전문가 자신의 마이홈 전문가 페이지를 조회합니다. [마이홈 전문가 페이지]")
    public ApiResponseDto<ExpertResponseDto.ExpertProfileTopDto> getMyExpertProfileTop(@AuthExpert Expert expert) {
        return ApiResponseDto.onSuccess(ExpertConverter.toExpertProfileTopDto(expert));
    }

}

