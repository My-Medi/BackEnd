package com.my_medi.api.expert.controller;

import com.my_medi.api.common.dto.ApiResponseDto;
import com.my_medi.api.expert.dto.ExpertResponseDto;
import com.my_medi.api.expert.dto.ExpertResponseDto.ExpertProfileDto;
import com.my_medi.api.expert.dto.RegisterExpertDto;
import com.my_medi.api.expert.mapper.ExpertConverter;
import com.my_medi.api.member.dto.RegisterMemberDto;
import com.my_medi.api.user.dto.UserResponseDto;
import com.my_medi.api.user.mapper.UserConverter;
import com.my_medi.common.annotation.AuthExpert;
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

    @Operation(summary = "전문가 계정을 생성합니다.")
    @PostMapping
    //TODO 자격증, 경력사항 list 추[피그마 전문가 회원가입 3페이지 참고]
    public ApiResponseDto<Long> registerExpertAccount(@RequestBody RegisterExpertDto registerExpertDto) {
        return ApiResponseDto.onSuccess(expertCommandService.registerExpert(registerExpertDto));
    }

    @Operation(summary = "전문가 내 프로필을 조회합니다.")
    @GetMapping
    public ApiResponseDto<ExpertProfileDto> getMyExpertProfile(@AuthExpert Expert expert) {
        return ApiResponseDto.onSuccess(ExpertConverter.toExpertProfileDto(expert));
    }
}

