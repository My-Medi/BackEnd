package com.my_medi.api.consultation.controller;

import com.my_medi.api.common.dto.ApiResponseDto;
import com.my_medi.api.consultation.dto.UserConsultationDto;
import com.my_medi.api.consultation.mapper.UserConsultationConvert;
import com.my_medi.api.consultation.service.ConsultationUseCase;
import com.my_medi.api.consultation.validator.ExpertAllowedToViewUserInfoValidator;
import com.my_medi.common.annotation.AuthUser;
import com.my_medi.domain.consultationRequest.entity.ConsultationRequest;
import com.my_medi.domain.consultationRequest.entity.RequestStatus;
import com.my_medi.domain.consultationRequest.exception.ConsultationRequestHandler;
import com.my_medi.domain.consultationRequest.repository.ConsultationRequestRepository;
import com.my_medi.domain.consultationRequest.service.ConsultationRequestCommandService;
import com.my_medi.domain.consultationRequest.service.ConsultationRequestQueryService;
import com.my_medi.domain.expert.entity.Expert;
import com.my_medi.domain.expert.exception.ExpertHandler;
import com.my_medi.domain.expert.repository.ExpertRepository;
import com.my_medi.domain.user.entity.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDate;
import java.time.LocalDateTime;
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
    private final ConsultationRequestRepository consultationRequestRepository;
    private final ExpertRepository expertRepository;



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
    public ApiResponseDto<List<UserConsultationDto.UserConsultationStatusDto>> getMyConsultations(
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

        List<UserConsultationDto.UserConsultationStatusDto> dtoList = deduplicated.stream()
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


    @Operation(summary = "상담 요청한 전문가의 상세 정보를 조회합니다.")
    @GetMapping("/experts/{expertId}/requested")
    public ApiResponseDto<UserConsultationDto.ExpertRequestedDto> getExpertDetail(
            @AuthUser User user,
            @PathVariable Long expertId
    ) {
        UserConsultationDto.ExpertRequestedDto dto =
                consultationRequestQueryService.getRequestedExpertDetail(user.getId(), expertId);

        return ApiResponseDto.onSuccess(dto);
    }

    @Operation(summary = "매칭된 전문가의 상세 정보를 조회합니다.")
    @GetMapping("/experts/{expertId}/matched")
    public ApiResponseDto<UserConsultationDto.ExpertAcceptedDto> getMatchedExpertDetail(
            @AuthUser User user,
            @PathVariable Long expertId
    ) {
        ConsultationRequest request = consultationRequestQueryService.getMatchedExpertDetail(user.getId(), expertId);
        UserConsultationDto.ExpertAcceptedDto detailDto = UserConsultationConvert.toDetailDto(request);
        return ApiResponseDto.onSuccess(detailDto);
    }



}
