package com.my_medi.api.report.service;

import com.my_medi.api.report.dto.ReportResponseDto;
import com.my_medi.api.report.mapper.ReportConverter;
import com.my_medi.api.report.validator.UserReportForExpertValidator;
import com.my_medi.domain.expert.entity.Expert;
import com.my_medi.domain.report.entity.Report;
import com.my_medi.domain.report.service.ReportQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetUserReportByExpertUseCase {
    private final ReportQueryService reportQueryService;
    private final UserReportForExpertValidator userReportForExpertValidator;

    public ReportResponseDto.UserReportDto getUserReportForExpert(Expert expert, Long userId, Integer round) {
        userReportForExpertValidator.validateExpertHasAcceptedUser(expert.getId(), userId);

        Report report = reportQueryService.getReportByRound(userId, round);

        return ReportConverter.toUserReportDto(report);
    }
}
