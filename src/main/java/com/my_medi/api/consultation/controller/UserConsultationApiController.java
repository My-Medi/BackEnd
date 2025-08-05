package com.my_medi.api.consultation.controller;

import com.my_medi.api.common.dto.ApiResponseDto;
import com.my_medi.api.consultation.dto.UserConsultationDto;
import com.my_medi.api.consultation.mapper.UserConsultationConvert;
import com.my_medi.api.consultation.service.ConsultationUseCase;
import com.my_medi.common.annotation.AuthUser;
import com.my_medi.domain.consultationRequest.entity.ConsultationRequest;
import com.my_medi.domain.consultationRequest.entity.RequestStatus;
import com.my_medi.domain.consultationRequest.service.ConsultationRequestCommandService;
import com.my_medi.domain.consultationRequest.service.ConsultationRequestQueryService;
import com.my_medi.domain.user.entity.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "[사용자 페이지] 상담요청 API")
@RestController
@RequestMapping("/api/v1/users/consultations")
@RequiredArgsConstructor
public class UserConsultationApiController {

    private final ConsultationRequestCommandService consultationRequestCommandService;
    private final ConsultationUseCase consultationUseCase;
    private final ConsultationRequestQueryService consultationRequestQueryService;

    @Operation(summary = "전문가에게 상담요청을 보냅니다.")
    @PostMapping("/experts/{expertId}")
    public ApiResponseDto<Long> approveConsultation(@AuthUser User user,
                                                    @PathVariable Long expertId,
                                                    @RequestParam String comment) {

        return ApiResponseDto.onSuccess(consultationUseCase.
                sendConsultationRequestNotificationToExpert(user, expertId, comment));
    }

    @Operation(summary = "본인이 요청한 상담 목록을 조회합니다.")
    @GetMapping
    public ApiResponseDto<List<UserConsultationDto>> getMyConsultations(
            @AuthUser User user,
            @RequestParam(required = false) RequestStatus status
    ) {
        List<ConsultationRequest> requests = (status == null) ?
                consultationRequestQueryService.getAllRequestByUser(user.getId()) :
                consultationRequestQueryService.getRequestByUser(user.getId(), status);

        List<ConsultationRequest> deduplicated = requests;

        if (status == RequestStatus.REQUESTED) {
            deduplicated = requests.stream()
                    .collect(Collectors.toMap(
                            req -> req.getExpert().getId(),
                            req -> req,
                            (existing, duplicate) -> existing
                    ))
                    .values().stream()
                    .toList();
        }

        List<UserConsultationDto> dtoList = deduplicated.stream()
                .map(UserConsultationConvert::toDto)
                .toList();

        return ApiResponseDto.onSuccess(dtoList);

    }



    @Operation(summary = "본인이 요청한 상담을 취소합니다.")
    @DeleteMapping("/{consultationId}")
    public ApiResponseDto<Long> cancelConsultation(@AuthUser User user,
                                                   @PathVariable Long consultationId) {
        consultationRequestCommandService.cancelRequest(consultationId, user.getId());
        return ApiResponseDto.onSuccess(consultationId);
    }

}
