package com.my_medi.api.consultation.service;

import com.my_medi.api.consultation.dto.ExpertConsultationDto;
import com.my_medi.api.consultation.dto.ExpertConsultationDto.*;
import com.my_medi.api.consultation.mapper.ExpertConsultationConverter;
import com.my_medi.api.report.dto.HealthStatus;
import com.my_medi.api.report.dto.UserLatestReportStatusDto;
import com.my_medi.domain.consultationRequest.entity.ConsultationRequest;
import com.my_medi.domain.consultationRequest.entity.RequestStatus;
import com.my_medi.domain.consultationRequest.service.ConsultationRequestCommandService;
import com.my_medi.domain.consultationRequest.service.ConsultationRequestQueryService;
import com.my_medi.domain.expert.entity.Expert;
import com.my_medi.domain.notification.entity.NotificationMessage;
import com.my_medi.domain.notification.entity.NotificationType;
import com.my_medi.domain.notification.service.ExpertNotificationCommandService;
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

import java.util.List;
import java.util.Map;
import java.util.Set;
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

        List<UserLatestReportStatusDto> latestStatuses =
                reportRepository.findLatestReportStatusByUserIds(userIds);

        Map<Long, UserLatestReportStatusDto> userStatusMap = latestStatuses.stream()
                .collect(Collectors.toMap(UserLatestReportStatusDto::getUserId, Function.identity()));

        List<ExpertConsultationAcceptedDto> dtoList = requests.getContent().stream()
                .map(req -> {
                    UserLatestReportStatusDto status = userStatusMap.get(req.getUser().getId());
                    HealthStatus totalHealthStatus = (status != null) ? status.getTotalHealthStatus() : null;

                    return ExpertConsultationConverter.toAcceptedConsultationDto(req, totalHealthStatus);
                })
                .toList();

        ExpertConsultationPageDto<ExpertConsultationAcceptedDto> result =
                ExpertConsultationPageDto.<ExpertConsultationAcceptedDto>builder()
                        .content(dtoList)
                        .totalPages(requests.getTotalPages())
                        .build();

        return result;
    }
}
