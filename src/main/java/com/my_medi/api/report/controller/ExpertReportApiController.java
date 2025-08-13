package com.my_medi.api.report.controller;

import com.my_medi.api.common.dto.ApiResponseDto;
import com.my_medi.api.report.dto.ReportResponseDto;
import com.my_medi.api.report.dto.ReportSummaryDto;
import com.my_medi.api.report.service.ReportUseCase;
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
    private final ReportUseCase reportUseCase;

    @Operation(summary = "전문가가 매칭된 환자의 n회차 건강리포트를 조회합니다.")
    @GetMapping("/users/{userId}")
    public ApiResponseDto<ReportResponseDto.UserReportDto> getUserReport(@AuthExpert Expert expert,
                                                                         @PathVariable Long userId,
                                                                         @RequestParam Integer round) {
        return ApiResponseDto.onSuccess(reportUseCase.getUserReportForExpert(expert, userId, round));
    }

    @Operation(summary = "사용자가 작성한 건강관리 제인서에서 사용자의 가장 최근 리포트의 요약본을 가져옵니다.")
    @GetMapping("/users/{userId}/summary")
    public ApiResponseDto<ReportSummaryDto> getUserReportSummary(@AuthExpert Expert expert,
                                                                 @PathVariable Long userId) {
        return ApiResponseDto.onSuccess(reportUseCase.getUserReportSummaryForExpert(expert, userId));
    }
}
