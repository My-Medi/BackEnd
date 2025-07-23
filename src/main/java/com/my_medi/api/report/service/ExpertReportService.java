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
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExpertReportService {
    private final ConsultationRequestRepository consultationRequestRepository;
    private final ReportQueryService reportQueryService;

    public ReportResponseDto.UserReportDto getUserReportForExpert(Expert expert, Long userId, Integer round) {
        List<ConsultationRequest> consultationRequests =
                consultationRequestRepository.findByExpertId(expert.getId());

        Optional<ConsultationRequest> matchedRequest = consultationRequests.stream()
                .filter(request -> request.getUser().getId().equals(userId))
                .findFirst();

        // ConsultationRequest 찾기
        if (matchedRequest.isEmpty()) {
            throw ConsultationRequestHandler.NOT_FOUND;
        }

        // ConsultationRequest의 권한 검사
        if (matchedRequest.get().getRequestStatus() != RequestStatus.ACCEPTED) {
            throw ConsultationRequestHandler.NOT_FOUND;
        }

        Report report = reportQueryService.getReportByRound(userId, round);

        return ReportConverter.toUserReportDto(report);
    }
}
