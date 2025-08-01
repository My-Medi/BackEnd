package com.my_medi.api.license.controller;

import com.my_medi.api.license.dto.LicenseRequestDto;
import com.my_medi.api.common.dto.ApiResponseDto;
import com.my_medi.domain.license.service.LicenseCommandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/licenses")
public class LicenseController {

    private final LicenseCommandService licenseCommandService;

    @Operation(summary = "전문가 자격증 등록")
    @PostMapping("/expert/{expertId}")
    public ApiResponseDto<Long> registerLicense(
            @Parameter(description = "전문가 ID") @PathVariable Long expertId,
            @RequestBody LicenseRequestDto dto
    ) {
        Long licenseId = licenseCommandService.registerLicense(expertId, dto);
        return ApiResponseDto.onSuccess(licenseId);
    }

    @Operation(summary = "자격증 수정")
    @PutMapping("/{licenseId}")
    public ApiResponseDto<String> updateLicense(
            @Parameter(description = "수정할 자격증 ID") @PathVariable Long licenseId,
            @RequestBody LicenseRequestDto dto
    ) {
        licenseCommandService.updateLicense(licenseId, dto);
        return ApiResponseDto.onSuccess("수정 완료");
    }

    @Operation(summary = "자격증 삭제")
    @DeleteMapping("/{licenseId}")
    public ApiResponseDto<String> deleteLicense(
            @Parameter(description = "삭제할 자격증 ID") @PathVariable Long licenseId
    ) {
        licenseCommandService.deleteLicense(licenseId);
        return ApiResponseDto.onSuccess("삭제 완료");
    }
}

