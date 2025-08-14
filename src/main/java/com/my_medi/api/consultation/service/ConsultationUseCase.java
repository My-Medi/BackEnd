package com.my_medi.api.consultation.service;

import com.my_medi.api.consultation.dto.ExpertConsultationDto;
import com.my_medi.api.consultation.dto.ExpertConsultationDto.*;
import com.my_medi.api.consultation.mapper.ExpertConsultationConverter;
import com.my_medi.api.report.dto.HealthStatus;
import com.my_medi.api.report.dto.UserLatestReportStatusDto;
import com.my_medi.common.util.ProposalMapperUtil;
import com.my_medi.domain.consultationRequest.entity.ConsultationRequest;
import com.my_medi.domain.consultationRequest.entity.RequestStatus;
import com.my_medi.domain.consultationRequest.service.ConsultationRequestCommandService;
import com.my_medi.domain.consultationRequest.service.ConsultationRequestQueryService;
import com.my_medi.domain.expert.entity.Expert;
import com.my_medi.domain.notification.entity.NotificationMessage;
import com.my_medi.domain.notification.entity.NotificationType;
import com.my_medi.domain.notification.service.ExpertNotificationCommandService;
import com.my_medi.domain.proposal.entity.Proposal;
import com.my_medi.domain.proposal.repository.ProposalRepository;
import com.my_medi.domain.report.repository.ReportRepository;
import com.my_medi.domain.reportResult.entity.ReportResult;
import com.my_medi.domain.user.entity.User;
import com.my_medi.domain.notification.service.UserNotificationCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.my_medi.common.consts.StaticVariable.CREATED_DATE;

@Service
@RequiredArgsConstructor
public class ConsultationUseCase {
    private final ConsultationRequestCommandService consultationRequestCommandService;
    private final ConsultationRequestQueryService consultationRequestQueryService;
    private final UserNotificationCommandService userNotificationCommandService;
    private final ExpertNotificationCommandService expertNotificationCommandService;
    private final ProposalRepository proposalRepository;
    private final ReportRepository reportRepository;

    public void approveConsultationRequestAndSendNotificationToUser(Expert expert, Long consultationId) {
        consultationRequestCommandService.approveConsultation(consultationId, expert);
        ConsultationRequest request = consultationRequestQueryService.getRequestById(consultationId);

        String notificationComment = NotificationMessage
                .CONSULTATION_APPROVED.format(expert.getName());

        userNotificationCommandService.sendNotificationToUser(request.getUser().getId(),
                consultationId, notificationComment, NotificationType.CONSULTATION_RESPONSE);
    }

    public void rejectConsultationRequestAndSendNotificationToUser(Expert expert, Long consultationId) {
        consultationRequestCommandService.rejectConsultation(consultationId, expert);
        ConsultationRequest request = consultationRequestQueryService.getRequestById(consultationId);

        String notificationComment = NotificationMessage
                .CONSULTATION_REJECTED.format(expert.getName());

        userNotificationCommandService.sendNotificationToUser(request.getUser().getId(),
                consultationId, notificationComment, NotificationType.CONSULTATION_RESPONSE);
    }

    //TODO return type 통일하기 Long -> void
    public Long sendConsultationRequestNotificationToExpert(User user, Long expertId, String comment) {
        Long requestId = consultationRequestCommandService.requestConsultationToExpert(user, expertId, comment);

        String notificationComment = NotificationMessage.CONSULTATION_REQUESTED.format(user.getName());

        expertNotificationCommandService.sendNotificationToExpert(expertId, requestId, notificationComment);

        return requestId;
    }

    public ExpertConsultationPageDto<ExpertConsultationAcceptedDto> getUserInfoList(Expert expert, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(CREATED_DATE).descending());

        Page<ConsultationRequest> requests = consultationRequestQueryService
                .getRequestByExpert(expert.getId(), RequestStatus.ACCEPTED, pageable);

        Set<Long> userIds = requests.getContent().stream()
                .map(cr -> cr.getUser().getId())
                .collect(Collectors.toSet());

        // 이미 만든 로직 그대로
        List<UserLatestReportStatusDto> latestStatuses =
                userIds.isEmpty() ? Collections.emptyList()
                        : reportRepository.findLatestReportStatusByUserIds(userIds);

        Map<Long, UserLatestReportStatusDto> userStatusMap = latestStatuses.stream()
                .collect(Collectors.toMap(
                        UserLatestReportStatusDto::getUserId,
                        Function.identity(),
                        (a, b) -> (a.getReportId() != null && b.getReportId() != null)
                                ? (a.getReportId() < b.getReportId() ? b : a)
                                : (a.getReportId() == null ? b : a)
                ));

        // 관심사 일괄 로딩 → userId별 List<String> 맵 구성
        Map<Long, List<String>> interestByUser;
        if (!userIds.isEmpty()) {
            List<Proposal> proposals = proposalRepository.findAllByUserIdInWithUser(userIds);
            interestByUser = proposals.stream().collect(Collectors.toMap(
                    p -> p.getUser().getId(),
                    p -> ProposalMapperUtil.extractHealthInterests(p)
            ));
        } else {
            interestByUser = Collections.emptyMap();
        }

        List<ExpertConsultationAcceptedDto> dtoList = requests.getContent().stream()
                .map(req -> {
                    Long uid = req.getUser().getId();
                    UserLatestReportStatusDto status = userStatusMap.get(uid);

                    HealthStatus totalHealthStatus = (status != null) ? status.getTotalHealthStatus() : null;

                    ExpertConsultationAcceptedDto dto =
                            ExpertConsultationConverter.toAcceptedConsultationDto(req, totalHealthStatus);

                    // 관심사 채우기 (없으면 빈 리스트)
                    dto.setInterestAreas(interestByUser.getOrDefault(uid, Collections.emptyList()));

                    // 최신 검진일도 DTO가 있으면 세팅 (네 DTO/컨버터 구조에 맞춰)
                    if (status != null && dto.getRecentCheckupDate() == null) {
                        dto.setRecentCheckupDate(status.getCheckupDate()); // checkupDate를 DTO에 추가했다면
                    }

                    return dto;
                })
                .toList();

        return ExpertConsultationPageDto.<ExpertConsultationAcceptedDto>builder()
                .content(dtoList)
                .totalPages(requests.getTotalPages())
                .name(expert.getName())
                .nickname(expert.getNickname())
                .build();
    }
}
