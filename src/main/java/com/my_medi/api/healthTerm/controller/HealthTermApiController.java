package com.my_medi.api.healthTerm.controller;

import com.my_medi.api.common.dto.ApiResponseDto;
import com.my_medi.infra.gpt.dto.HealthTermResponse;
import com.my_medi.infra.gpt.service.HealthTermService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@Tag(name = "[사용자 페이지] 건강용어 검색하기")
@RestController
@RequestMapping("/api/v1/users/term")
@RequiredArgsConstructor
public class HealthTermApiController {
    private final HealthTermService healthTermService;

    // 예: GET /api/v1/users/term?term=콜레스테롤
    @Operation(summary = "[건강용어 검색] 사용자가 건강용어를 검색하면 형식에 맞게 설명을 가져옵니다.")
    @GetMapping
    public ApiResponseDto<HealthTermResponse> getHealthTerm(@RequestParam String term) {
        return ApiResponseDto.onSuccess(healthTermService.define(term));
    }
}
