package com.my_medi.api.report.service;

import com.my_medi.api.consultation.validator.ExpertAllowedToViewUserInfoValidator;
import com.my_medi.api.report.dto.ReportResponseDto;
import com.my_medi.api.report.dto.ReportResponseDto.ReportResultDto;
import com.my_medi.api.report.dto.ReportResponseDto.UserReportDto;
import com.my_medi.api.report.dto.ReportSummaryDto;
import com.my_medi.api.report.dto.WriteReportRequestDto;
import com.my_medi.api.report.mapper.ReportConverter;
import com.my_medi.common.annotation.UseCase;
import com.my_medi.domain.consultationRequest.entity.RequestStatus;
import com.my_medi.domain.expert.entity.Expert;
import com.my_medi.domain.report.entity.Report;
import com.my_medi.domain.report.exception.ReportHandler;
import com.my_medi.domain.report.repository.ReportRepository;
import com.my_medi.domain.report.service.ReportCommandService;
import com.my_medi.domain.report.service.ReportQueryService;
import com.my_medi.domain.reportResult.entity.ReportResult;
import com.my_medi.domain.reportResult.repository.ReportResultRepository;
import com.my_medi.domain.reportResult.service.ReportResultCommandService;
import com.my_medi.domain.reportResult.service.ReportResultQueryService;
import com.my_medi.domain.user.entity.User;
import com.my_medi.domain.user.exception.UserHandler;
import com.my_medi.domain.user.repository.UserRepository;
import com.my_medi.domain.user.service.UserQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@UseCase
@RequiredArgsConstructor
public class ReportUseCase {
    private final ReportQueryService reportQueryService;
    private final ReportResultQueryService reportResultQueryService;
    private final ExpertAllowedToViewUserInfoValidator expertAllowedToViewUserInfoValidator;
    private final UserQueryService userQueryService;

    public ReportResultDto getUserReportForExpert(Expert expert, Long userId, Integer round) {
        expertAllowedToViewUserInfoValidator.validateMatchStatus(
                expert.getId(),
                userId,
                RequestStatus.ACCEPTED
        );
        User user = userQueryService.getUserById(userId);
        return reportQueryService.compareReport(user, round);
    }

    public ReportSummaryDto getUserReportSummaryForExpert(Expert expert, Long userId) {

        Report report = reportQueryService.getLatestReportByUserId(userId);
        ReportResult resultByReport = reportResultQueryService.getResultByReport(report.getId());
        return ReportConverter.toUserReportSummaryDto(report, resultByReport);
    }

}
