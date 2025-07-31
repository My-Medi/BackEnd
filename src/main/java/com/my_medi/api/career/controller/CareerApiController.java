package com.my_medi.api.career.controller;

import com.my_medi.api.career.dto.CareerResponseDto;
import com.my_medi.api.common.dto.ApiResponseDto;
import com.my_medi.common.annotation.AuthExpert;
import com.my_medi.domain.career.service.CareerCommandService;
import com.my_medi.domain.expert.entity.Expert;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "[전문가 페이지] 경력 관리 API")
@RestController
@RequestMapping("/api/v1/experts/careers")
@RequiredArgsConstructor
public class CareerApiController {

    private final CareerCommandService careerCommandService;

    @Operation(summary = "전문가 자신의 경력을 등록합니다.")
    @PostMapping
    public ApiResponseDto<Long> registerCareer(@AuthExpert Expert expert,
                                               @RequestBody CareerResponseDto dto) {
        Long id = careerCommandService.registerCareer(expert.getId(), dto);
        return ApiResponseDto.onSuccess(id);
    }

    @Operation(summary = "전문가 자신의 경력을 수정합니다.")
    @PatchMapping("/{careerId}")
    public ApiResponseDto<Long> updateCareer(@AuthExpert Expert expert, @PathVariable Long careerId,
                                             @RequestBody CareerResponseDto dto) {
        Long id = careerCommandService.updateCareer(careerId, dto);
        return ApiResponseDto.onSuccess(id);
    }

    @Operation(summary = "전문가 자신의 경력을 삭제합니다.")
    @DeleteMapping("/{careerId}")
    public ApiResponseDto<Long> deleteCareer(@AuthExpert Expert expert, @PathVariable Long careerId) {
        Long id = careerCommandService.deleteCareer(careerId);
        return ApiResponseDto.onSuccess(id);
    }
}

