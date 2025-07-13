package com.my_medi.api.user.controller;

import com.my_medi.api.common.dto.ApiResponseDto;
import com.my_medi.api.member.dto.RegisterMemberDto;
import com.my_medi.api.user.dto.RegisterUserDto;
import com.my_medi.api.user.dto.UserResponseDto;
import com.my_medi.api.user.dto.UserResponseDto.UserProfileDto;
import com.my_medi.api.user.mapper.UserConverter;
import com.my_medi.domain.user.entity.User;
import com.my_medi.domain.user.exception.UserHandler;
import com.my_medi.domain.user.repository.UserRepository;

import com.my_medi.domain.user.service.UserCommandService;
import com.my_medi.domain.user.service.UserQueryService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


@Tag(name = "사용자 API")
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserApiController {

    private final UserCommandService userCommandService;
    private final UserQueryService userQueryService;

    @Operation(summary = "user 계정을 생성합니다.")
    @PostMapping
    public ApiResponseDto<Long> registerUserAccount(@RequestBody RegisterUserDto registerUserDto) {
        return ApiResponseDto.onSuccess(userCommandService.registerUser(registerUserDto));
    }

    @Operation(summary = "user 프로필 정보를 조회합니다.")
    @GetMapping("/{userId}")
    public ApiResponseDto<UserProfileDto> getUserProfile(@PathVariable Long userId) {
        User user = userQueryService.getUserById(userId);
        return ApiResponseDto.onSuccess(UserConverter.toUserProfileDto(user));
    }
}
