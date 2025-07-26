package com.my_medi.api.report.controller;

import com.my_medi.api.common.dto.ApiResponseDto;
import com.my_medi.api.report.dto.ReportResponseDto;
import com.my_medi.api.report.service.GetUserReportByExpertUseCase;
import com.my_medi.common.annotation.AuthExpert;
import com.my_medi.domain.expert.entity.Expert;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "[전문가 페이지]건강리포트 API")
@RestController
@RequestMapping("/api/v1/experts/reports")
@RequiredArgsConstructor
public class ExpertReportApiController {
    private final GetUserReportByExpertUseCase getUserReportByExpertUseCase;

    @Operation(summary = "전문가가 매칭된 환자의 n회차 건강리포트를 조회합니다.")
    @GetMapping("/users/{userId}")
    public ApiResponseDto<ReportResponseDto.UserReportDto> getUserReport(@AuthExpert Expert expert,
                                                                         @PathVariable Long userId,
                                                                         @RequestParam Integer round) {
        return ApiResponseDto.onSuccess(
                getUserReportByExpertUseCase.getUserReportForExpert(expert, userId, round)
        );
    }


}
