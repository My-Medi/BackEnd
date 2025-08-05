package com.my_medi.api.expert.controller;

import com.my_medi.api.common.dto.ApiResponseDto;
import com.my_medi.api.consultation.validator.ExpertAllowedToViewUserInfoValidator;
import com.my_medi.api.expert.dto.ExpertResponseDto;
import com.my_medi.api.expert.mapper.ExpertConverter;
import com.my_medi.common.annotation.AuthUser;
import com.my_medi.domain.expert.entity.Expert;
import com.my_medi.domain.expert.service.ExpertQueryService;
import com.my_medi.domain.user.entity.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "[사용자 페이지] 전문가 조회 API")
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserQueryExpertApiController {

    private final ExpertQueryService expertQueryService;
    private final ExpertAllowedToViewUserInfoValidator expertAllowedToViewUserInfoValidator;

    @Operation(summary = "전문가 프로필 정보를 전체 조회합니다.")
    @GetMapping("/experts/{expertId}/profiles/details")
    public ApiResponseDto<ExpertResponseDto.ExpertProfileDto> getExpertProfile(@AuthUser User user,
                                                                               @PathVariable Long expertId) {
        // 전문가 엔티티 조회 -> 유저와 전문가 매칭 확인 -> DTO 변환 후 반환
        Expert expert = expertQueryService.getExpertById(expertId);

        ExpertResponseDto.ExpertProfileDto result = ExpertConverter.toExpertProfileDto(expert);

        return ApiResponseDto.onSuccess(result);
    }

    @GetMapping("/experts/{expertId}")
    @Operation(summary = "사용자가 전문가의 프로필을 상세 조회합니다. [전문가 상세]", description = "[건강관리요청서] 보내기 전 ")
    public ApiResponseDto<ExpertResponseDto.ExpertDetailForUserDto> getExpertDetailForUser(@PathVariable Long expertId) {
        Expert expert = expertQueryService.getExpertById(expertId);
        return ApiResponseDto.onSuccess(ExpertConverter.toExpertDetailForUserDto(expert));
    }


}
