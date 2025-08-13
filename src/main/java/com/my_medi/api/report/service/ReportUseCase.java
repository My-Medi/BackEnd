package com.my_medi.api.report.service;

import com.my_medi.api.consultation.validator.ExpertAllowedToViewUserInfoValidator;
import com.my_medi.api.report.dto.ReportResponseDto.UserReportDto;
import com.my_medi.api.report.dto.ReportSummaryDto;
import com.my_medi.api.report.mapper.ReportConverter;
import com.my_medi.domain.expert.entity.Expert;
import com.my_medi.domain.report.entity.Report;
import com.my_medi.domain.report.service.ReportQueryService;
import com.my_medi.domain.user.entity.User;
import com.my_medi.domain.user.exception.UserHandler;
import com.my_medi.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReportUseCase {
    private final ReportQueryService reportQueryService;
    private final ExpertAllowedToViewUserInfoValidator expertAllowedToViewUserInfoValidator;
    private final UserRepository userRepository;

    public UserReportDto getUserReportForExpert(Expert expert, Long userId, Integer round) {
        expertAllowedToViewUserInfoValidator.validateExpertHasAcceptedUser(expert.getId(), userId);

        Report report = reportQueryService.getReportByRound(userId, round);

        return ReportConverter.toUserReportDto(report);
    }

    public ReportSummaryDto getUserReportSummaryForExpert(Expert expert, Long userId) {
        expertAllowedToViewUserInfoValidator.validateExpertHasAcceptedUser(expert.getId(), userId);

        User user = userRepository.findById(userId).orElseThrow(() -> UserHandler.NOT_FOUND);
        Report report = reportQueryService.getLatestReportByUserId(user.getId());

        return ReportConverter.toUserReportSummaryDto(report);
    }
}
