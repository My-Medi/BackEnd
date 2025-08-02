/*
package com.my_medi.api.license.controller;

import com.my_medi.api.common.dto.ApiResponseDto;
import com.my_medi.api.license.dto.LicenseRequestDto;
import com.my_medi.common.annotation.AuthExpert;
import com.my_medi.domain.expert.entity.Expert;
import com.my_medi.domain.expert.exception.ExpertHandler;
import com.my_medi.domain.license.entity.License;
import com.my_medi.domain.license.repository.LicenseRepository;
import com.my_medi.domain.license.service.LicenseCommandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@Tag(name = "[전문가 페이지] 자격증 관련 API")
@RestController
@RequestMapping("/api/v1/licenses")
@RequiredArgsConstructor
public class LicenseController {

    private final LicenseCommandService licenseCommandService;
    private final LicenseRepository licenseRepository;

    @Operation(summary = "전문가 자신의 자격증을 등록합니다.")
    @PostMapping
    public ApiResponseDto<Long> registerLicense(
            @AuthExpert Expert expert,
            @RequestBody LicenseRequestDto dto
    ) {
        Long licenseId = licenseCommandService.registerLicense(expert.getId(), dto);
        return ApiResponseDto.onSuccess(licenseId);
    }

    @Operation(summary = "전문가 자신의 자격증을 수정합니다.")
    @PutMapping("/{licenseId}")
    public ApiResponseDto<String> updateLicense(
            @AuthExpert Expert expert,
            @PathVariable Long licenseId,
            @RequestBody LicenseRequestDto dto
    ) {
        License license = licenseRepository.findById(licenseId)
                .orElseThrow(() -> ExpertHandler.NOT_FOUND); // TODO: 핸들러 변경

        if (!license.getExpert().getId().equals(expertId)) {
            throw ExpertHandler.NOT_FOUND; // TODO : 핸들러 변경
        }

        licenseCommandService.updateLicense(licenseId, dto);
        return ApiResponseDto.onSuccess("수정 완료");
    }

    // TODO : 이력서 수정하기 api - 리스트는 초기화하고
    @Operation(summary = "전문가 자신의 자격증을 삭제합니다.")
    @DeleteMapping("/{licenseId}")
    public ApiResponseDto<String> deleteLicense(
            @AuthExpert Expert expert,
            @PathVariable Long licenseId
    ) {
        licenseCommandService.deleteLicense(licenseId);
        return ApiResponseDto.onSuccess("삭제 완료");
    }
}
 */