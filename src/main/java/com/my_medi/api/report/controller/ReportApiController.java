package com.my_medi.api.report.controller;

import com.my_medi.api.common.dto.ApiResponseDto;
import com.my_medi.api.report.dto.ReportRequestDto;
import com.my_medi.api.report.dto.ReportResponseDto;
import com.my_medi.api.report.mapper.ReportConverter;
import com.my_medi.domain.report.entity.Report;
import com.my_medi.domain.report.service.ReportCommandService;
import com.my_medi.domain.report.service.ReportQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "건강리포트 API")
@RestController
@RequestMapping("/api/v1/user/report")
@RequiredArgsConstructor
public class ReportApiController {
    private final ReportQueryService reportQueryService;
    private final ReportCommandService reportCommandService;

    @Operation(summary = "user가 자신의 n회차 건강리포트를 조회합니다.")
    @GetMapping
    public ApiResponseDto<ReportResponseDto.UserReportDto> getUserReport(
            @RequestParam Long userId, @RequestParam Integer round) {
        Report report = reportQueryService.getReportByRound(userId, round);
        return ApiResponseDto.onSuccess(ReportConverter.toUserReportDto(report));
    }

    @Operation(summary = "user가 자신의 건강리포트를 생성합니다.")
    @PostMapping
    public ApiResponseDto<Long> writeUserReport(
            @RequestParam Long userId,@RequestBody ReportRequestDto reportRequestDto) {
        return ApiResponseDto.onSuccess(reportCommandService.writeHealthReport(userId, reportRequestDto));
    }

    @Operation(summary = "user가 자신의 건강리포트를 수정합니다.")
    @PatchMapping
    public ApiResponseDto<Long> editUserReport(
            @RequestParam Long userId,
            @RequestParam Integer round,
            @RequestBody ReportRequestDto reportRequestDto) {
        return ApiResponseDto.onSuccess(
                reportCommandService.editHealthReportByRound(userId, round, reportRequestDto));
    }
}
