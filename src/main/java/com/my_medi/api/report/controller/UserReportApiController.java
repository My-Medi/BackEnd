package com.my_medi.api.report.controller;

import com.my_medi.api.common.dto.ApiResponseDto;
import com.my_medi.api.healthCheckup.dto.ComparingHealthCheckupResponseDto;
import com.my_medi.api.report.dto.*;
import com.my_medi.api.report.dto.EditReportRequestDto;
import com.my_medi.api.report.dto.ReportResponseDto;
import com.my_medi.api.report.dto.ReportResultResponseDto.UserReportResultDto;
import com.my_medi.api.report.dto.WriteReportRequestDto;
import com.my_medi.api.report.dto.ReportResponseDto.UserReportDto;
import com.my_medi.api.report.mapper.ReportConverter;
import com.my_medi.common.annotation.AuthUser;
import com.my_medi.common.util.ImageUtil;
import com.my_medi.domain.report.entity.Report;
import com.my_medi.domain.report.service.ReportCommandService;
import com.my_medi.domain.report.service.ReportQueryService;
import com.my_medi.domain.reportResult.entity.ReportResult;
import com.my_medi.domain.reportResult.service.ReportResultCommandService;
import com.my_medi.domain.reportResult.service.ReportResultQueryService;
import com.my_medi.domain.user.entity.User;
import com.my_medi.infra.gpt.dto.HealthReportData;
import com.my_medi.infra.gpt.dto.TotalReportData;
import com.my_medi.infra.gpt.dto.HealthTermResponse;
import com.my_medi.infra.gpt.service.OpenAIService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "[사용자 페이지]건강리포트 API")
@RestController
@RequestMapping("/api/v1/users/reports")
@RequiredArgsConstructor
public class UserReportApiController {

    private final ReportQueryService reportQueryService;
    private final ReportCommandService reportCommandService;
    private final ReportResultCommandService reportResultCommandService;
    private final ReportResultQueryService reportResultQueryService;
    private final OpenAIService openAIService;


    @Operation(summary = "사용자가 본인의 n회차 건강리포트를 조회합니다.")
    @GetMapping
    public ApiResponseDto<UserReportDto> getUserReport(@AuthUser User user,
                                                       @RequestParam Integer round) {
        Report report = reportQueryService.getReportByRound(user.getId(), round);
        return ApiResponseDto.onSuccess(ReportConverter.toUserReportDto(report));
    }

    @Operation(summary = "사용자의 가장 최근 리포트의 요약본을 가져옵니다.")
    @GetMapping("/summary")
    public ApiResponseDto<ReportSummaryDto> getUserReportSummary(@AuthUser User user) {
        Report report = reportQueryService.getLatestReportByUserId(user.getId());
        ReportResult resultByReport = reportResultQueryService.getResultByReport(report.getId());
        return ApiResponseDto.onSuccess(ReportConverter.toUserReportSummaryDto(report, resultByReport));
    }

    @Operation(summary = "사용자가 본인의 건강리포트를 생성합니다.")
    @PostMapping
    public ApiResponseDto<Long> writeUserReport(@AuthUser User user,
                                                @RequestBody WriteReportRequestDto writeReportRequestDto) {
        Long reportId = reportCommandService.writeHealthReport(user, writeReportRequestDto);
        reportResultCommandService.calculateReportResult(reportId, user.getBirthDate(), user.getGender());
        return ApiResponseDto.onSuccess(reportId);
    }

    @Operation(summary = "사용자가 본인의 n회차 건강리포트를 수정합니다.")
    @PatchMapping
    public ApiResponseDto<Long> editUserReport(@AuthUser User user,
                                               @RequestParam Integer round,
                                               @RequestBody EditReportRequestDto editReportRequestDto) {
        Long reportId = reportCommandService
                .editHealthReportByRound(user, round, editReportRequestDto);
        reportResultCommandService.removeReportResult(reportId);
        reportResultCommandService.calculateReportResult(reportId, user.getBirthDate(), user.getGender());
        return ApiResponseDto.onSuccess(reportId);
    }


    @Operation(summary = "본인 건강검진 리포트를 토대로 공공데이터와 비교한 결과값을 조회합니다.[전체]")
    @GetMapping("/comparing")
    public ApiResponseDto<ReportResponseDto.ReportResultDto> comparingReport(@AuthUser User user,
                                                                             @RequestParam Integer round) {
        return ApiResponseDto.onSuccess(reportQueryService.compareReport(user, round));
    }

    @Operation(summary = "GPT API를 사용하여 건강검진 이미지를 원하는 데이터대로 추출합니다.")
    @PostMapping(value = "/parsing", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ApiResponseDto<WriteReportRequestDto> parsingHealthReportImage(@RequestPart MultipartFile imageFile) {
        HealthReportData healthReportData = openAIService.parseHealthReport(ImageUtil.convertToByte(imageFile));
        return ApiResponseDto.onSuccess(
                ReportConverter.toWriteReportRequestDto(healthReportData)
        );
    }

    @Operation(summary = "LLM이 건강상태를 반영하여 건강검진 결과를 분석합니다.")
    @GetMapping("/total")
    public ApiResponseDto<TotalReportData> getTotalReport(
            @AuthUser User user,
            @RequestParam Integer round) {

        TotalReportData dto = openAIService.buildTotalReport(user.getId(), round);
        return ApiResponseDto.onSuccess(dto);
    }

    @Operation(summary = "사용자 본인의 건강리포트 기본정보를 조회합니다.",
            description = "리포트 총 횟수, 나이, 신장, 몸무게")

    @GetMapping("/default")
    public ApiResponseDto<ReportResponseDto.ReportDefaultResponseDto> getUserReportCount(@AuthUser User user) {
        long reportCountByUser = reportQueryService.getReportCountByUser(user);
        return ApiResponseDto.onSuccess(ReportConverter.toReportDefaultResponseDto(reportCountByUser, user));
    }

    @Operation(summary = "사용자 리포트의 최종 점수를 조회합니다.")
    @GetMapping("/result")
    public ApiResponseDto<UserReportResultDto> getUserReportResult(@AuthUser User user,
                                                                   @RequestParam Integer round) {
        Report reportByRound = reportQueryService.getReportByRound(user.getId(), round);
        ReportResult resultByReport = reportResultQueryService.getResultByReport(reportByRound.getId());
        return ApiResponseDto.onSuccess(ReportConverter.toUserReportResultDto(resultByReport));
    }

    @Operation(summary = "사용자 건강상태를 최근 리포트로 반영하여 조회합니다",
            description = "[내 건강은 지금!] 에서 사용")
    @GetMapping("/result/summary")
    public ApiResponseDto<HealthStatus> getUserReportResultLatest(@AuthUser User user) {
        Report latestReportByUserId = reportQueryService.getLatestReportByUserId(user.getId());
        ReportResult resultByReport = reportResultQueryService.getResultByReport(latestReportByUserId.getId());
        return ApiResponseDto.onSuccess(resultByReport.getTotalHealthStatus());
    }
}
