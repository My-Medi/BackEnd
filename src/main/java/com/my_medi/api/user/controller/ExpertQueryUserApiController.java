package com.my_medi.api.user.controller;

import com.my_medi.api.common.dto.ApiResponseDto;
import com.my_medi.api.user.dto.UserResponseDto;
import com.my_medi.api.user.mapper.UserConverter;
import com.my_medi.common.annotation.AuthExpert;
import com.my_medi.domain.expert.entity.Expert;
import com.my_medi.domain.user.entity.User;
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

    @Operation(summary = "사용자 프로필 정보를 조회합니다.")
    @GetMapping("/{userId}")
    public ApiResponseDto<UserResponseDto.UserProfileDto> getUserProfile(@AuthExpert Expert expert,
                                                                         @PathVariable Long userId) {
        //TODO user validation(전문가 프로필 조회 접근 권한 확인)
        /**
         * 권장 방법 : 새로운 service 클래스를 api package에서 생성
         * 해당 서비스 내에서 user와 expert를 조회하고 consultation approved 임을 확인
         * return : UserProfileDto
         */
        User user = userQueryService.getUserById(userId);
        return ApiResponseDto.onSuccess(UserConverter.toUserProfileDto(user));
    }

    // TODO 매칭된 사용자 목록 조회
}
