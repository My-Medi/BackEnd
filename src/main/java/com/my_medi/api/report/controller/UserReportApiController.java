package com.my_medi.api.report.controller;


import com.my_medi.api.common.dto.ApiResponseDto;
import com.my_medi.api.report.dto.EditReportRequestDto;
import com.my_medi.api.report.dto.WriteReportRequestDto;
import com.my_medi.api.report.dto.ReportResponseDto.UserReportDto;
import com.my_medi.api.report.mapper.ReportConverter;
import com.my_medi.common.annotation.AuthUser;
import com.my_medi.domain.report.entity.Report;
import com.my_medi.domain.report.service.ReportCommandService;
import com.my_medi.domain.report.service.ReportQueryService;
import com.my_medi.domain.user.entity.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "[사용자 페이지]건강리포트 API")
@RestController
@RequestMapping("/api/v1/users/reports")
@RequiredArgsConstructor
public class UserReportApiController {

    private final ReportQueryService reportQueryService;
    private final ReportCommandService reportCommandService;

    @Operation(summary = "사용자가 본인의 n회차 건강리포트를 조회합니다.")
    @GetMapping
    public ApiResponseDto<UserReportDto> getUserReport(@AuthUser User user,
                                                       @RequestParam Integer round) {
        Report report = reportQueryService.getReportByRound(user.getId(), round);
        return ApiResponseDto.onSuccess(ReportConverter.toUserReportDto(report));
    }

    @Operation(summary = "사용자가 본인의 건강리포트를 생성합니다.")
    @PostMapping
    public ApiResponseDto<Long> writeUserReport(@RequestBody WriteReportRequestDto writeReportRequestDto) {
        return ApiResponseDto.onSuccess(reportCommandService.writeHealthReport(writeReportRequestDto));
    }

    @Operation(summary = "사용자가 본인의 n회차 건강리포트를 수정합니다.")
    @PatchMapping
    public ApiResponseDto<Long> editUserReport(@RequestBody EditReportRequestDto editReportRequestDto) {
        return ApiResponseDto.onSuccess(reportCommandService.editHealthReportByRound(editReportRequestDto));
    }
}
