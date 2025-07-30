package com.my_medi.api.career.controller;

import com.my_medi.api.common.dto.ApiResponseDto;
import com.my_medi.common.annotation.AuthExpert;
import com.my_medi.domain.career.dto.CareerDto;
import com.my_medi.domain.career.service.CareerCommandService;
import com.my_medi.domain.expert.entity.Expert;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "경력 관리 API")
@RestController
@RequestMapping("/api/v1/experts/careers")
@RequiredArgsConstructor
public class CareerApiController {

    private final CareerCommandService careerCommandService;

    @Operation(summary = "경력 등록")
    @PostMapping
    public ApiResponseDto<Long> registerCareer(@AuthExpert Expert expert,
                                               @RequestBody CareerDto dto) {
        Long id = careerCommandService.registerCareer(expert.getId(), dto);
        return ApiResponseDto.onSuccess(id);
    }

    @Operation(summary = "경력 수정")
    @PutMapping("/{careerId}")
    public ApiResponseDto<Long> updateCareer(@PathVariable Long careerId,
                                             @RequestBody CareerDto dto) {
        Long id = careerCommandService.updateCareer(careerId, dto);
        return ApiResponseDto.onSuccess(id);
    }

    @Operation(summary = "경력 삭제")
    @DeleteMapping("/{careerId}")
    public ApiResponseDto<Long> deleteCareer(@PathVariable Long careerId) {
        Long id = careerCommandService.deleteCareer(careerId);
        return ApiResponseDto.onSuccess(id);
    }
}

