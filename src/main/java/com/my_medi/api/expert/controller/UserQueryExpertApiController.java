package com.my_medi.api.expert.controller;

import com.my_medi.api.common.dto.ApiResponseDto;
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
@RequestMapping("/api/v1/experts")
@RequiredArgsConstructor
public class UserQueryExpertApiController {

    private final ExpertQueryService expertQueryService;

    @Operation(summary = "매칭된 전문가 프로필 정보를 조회합니다.")
    @GetMapping("/{expertId}")
    public ApiResponseDto<ExpertResponseDto.ExpertProfileDto> getExpertProfile(@AuthUser User user,
                                                                               @PathVariable Long expertId) {
        //TODO user validation(전문가 프로필 조회 접근 권한 확인)
        /**
         * 권장 방법 : 새로운 service 클래스를 api package에서 생성
         * 해당 서비스 내에서 user와 expert를 조회하고 consultation approved 임을 확인
         * return : ExpertProfileDto
         */
        Expert expert = expertQueryService.getExpertById(expertId);
        return ApiResponseDto.onSuccess(ExpertConverter.toExpertProfileDto(expert));
    }

    // TODO 매칭된 전문가 정보 목록 조회하기
}
