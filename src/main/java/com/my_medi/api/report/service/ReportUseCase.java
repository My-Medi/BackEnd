package com.my_medi.api.report.service;

import com.my_medi.api.consultation.validator.ExpertAllowedToViewUserInfoValidator;
import com.my_medi.api.report.dto.ReportResponseDto.UserReportDto;
import com.my_medi.api.report.dto.ReportSummaryDto;
import com.my_medi.api.report.dto.WriteReportRequestDto;
import com.my_medi.api.report.mapper.ReportConverter;
import com.my_medi.domain.expert.entity.Expert;
import com.my_medi.domain.report.entity.Report;
import com.my_medi.domain.report.exception.ReportHandler;
import com.my_medi.domain.report.repository.ReportRepository;
import com.my_medi.domain.report.service.ReportCommandService;
import com.my_medi.domain.report.service.ReportQueryService;
import com.my_medi.domain.reportResult.repository.ReportResultRepository;
import com.my_medi.domain.reportResult.service.ReportResultCommandService;
import com.my_medi.domain.user.entity.User;
import com.my_medi.domain.user.exception.UserHandler;
import com.my_medi.domain.user.repository.UserRepository;
import com.my_medi.domain.user.service.UserQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReportUseCase {
    private final ReportQueryService reportQueryService;
    private final ReportRepository reportRepository;
    private final ReportResultRepository reportResultRepository;
    private final ReportCommandService reportCommandService;
    private final ReportResultCommandService reportResultCommandService;
    private final ExpertAllowedToViewUserInfoValidator expertAllowedToViewUserInfoValidator;
    private final UserQueryService userQueryService;

    public UserReportDto getUserReportForExpert(Expert expert, Long userId, Integer round) {
        expertAllowedToViewUserInfoValidator.validateExpertHasAcceptedUser(expert.getId(), userId);

        Report report = reportQueryService.getReportByRound(userId, round);

        return ReportConverter.toUserReportDto(report);
    }

    public ReportSummaryDto getUserReportSummaryForExpert(Expert expert, Long userId) {
        //TODO allow two status(REQUESTED, ACCEPTED)
        User user = userQueryService.getUserById(userId);
        Report report = reportQueryService.getLatestReportByUserId(user.getId());
        return ReportConverter.toUserReportSummaryDto(report);
    }

}
