package com.my_medi.api.common.controller;

import com.my_medi.api.common.dto.ApiResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "더미 데이터 생성 API", description = "더미데이터 생성 API")
@RestController
@RequestMapping("/api/v1/dummy")
@RequiredArgsConstructor
public class DummyApiController {

    @Operation(summary = "전문가 대량 생성 API")
    @PostMapping("/experts")
    public ApiResponseDto<String> createExpertsDummy(@RequestParam int count) {
        return ApiResponseDto.onSuccess("success");
    }
}
