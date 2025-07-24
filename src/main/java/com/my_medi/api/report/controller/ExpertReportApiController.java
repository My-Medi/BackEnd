package com.my_medi.api.report.controller;

import com.my_medi.api.common.dto.ApiResponseDto;
import com.my_medi.api.report.dto.ReportRequestDto;
import com.my_medi.api.report.dto.ReportResponseDto;
import com.my_medi.api.report.mapper.ReportConverter;
import com.my_medi.api.report.service.ExpertReportService;
import com.my_medi.common.annotation.AuthExpert;
import com.my_medi.common.annotation.AuthUser;
import com.my_medi.domain.expert.entity.Expert;
import com.my_medi.domain.report.entity.Report;
import com.my_medi.domain.report.service.ReportCommandService;
import com.my_medi.domain.report.service.ReportQueryService;
import com.my_medi.domain.user.entity.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "[전문가 페이지]건강리포트 API")
@RestController
@RequestMapping("/api/v1/experts/reports")
@RequiredArgsConstructor
public class ExpertReportApiController {
    private final ExpertReportService expertReportService;

    @Operation(summary = "전문가가 매칭된 환자의 n회차 건강리포트를 조회합니다.")
    @GetMapping("/users/{userId}")
    public ApiResponseDto<ReportResponseDto.UserReportDto> getUserReport
            (@AuthExpert Expert expert, @PathVariable Long userId, @RequestParam Integer round)
    {
        //TODO 전문가에게 해당 사용자 리포트를 열람할 권한이 있는지 확인
        /**
         * 권장 방법 : 새로운 service 클래스를 api package에서 생성
         * 해당 서비스 내에서 report를 조회하고 Expert와 user의 consultation 존재 확인 및 status가 approved 임을 확인
         * return : UserReportDto
         */
        return ApiResponseDto.onSuccess(
                expertReportService.getUserReportForExpert(expert, userId, round)
        );
    }


}
