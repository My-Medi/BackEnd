package com.my_medi.api.report.service;

import com.my_medi.api.report.dto.ReportResponseDto;
import com.my_medi.api.report.mapper.ReportConverter;
import com.my_medi.domain.consultationRequest.entity.ConsultationRequest;
import com.my_medi.domain.consultationRequest.entity.RequestStatus;
import com.my_medi.domain.consultationRequest.exception.ConsultationRequestHandler;
import com.my_medi.domain.consultationRequest.repository.ConsultationRequestRepository;
import com.my_medi.domain.expert.entity.Expert;
import com.my_medi.domain.report.entity.Report;
import com.my_medi.domain.report.service.ReportQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExpertReportUseCase {
    private final ConsultationRequestRepository consultationRequestRepository;
    private final ReportQueryService reportQueryService;

    public ReportResponseDto.UserReportDto getUserReportForExpert(Expert expert, Long userId, Integer round) {
        List<ConsultationRequest> consultationRequests =
                consultationRequestRepository.findByExpertId(expert.getId());

        boolean hasAccepted = consultationRequests.stream()
                .anyMatch(request -> request.getRequestStatus() == RequestStatus.ACCEPTED);

        if (!hasAccepted) {
            throw ConsultationRequestHandler.NOT_FOUND;
        }

        Report report = reportQueryService.getReportByRound(userId, round);

        return ReportConverter.toUserReportDto(report);
    }
}
