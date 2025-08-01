package com.my_medi.api.consultation.controller;

import com.my_medi.api.common.dto.ApiResponseDto;
import com.my_medi.api.consultation.dto.ExpertConsultationDto;
import com.my_medi.api.consultation.mapper.ExpertConsultationConverter;
import com.my_medi.api.consultation.service.ConsultationUseCase;
import com.my_medi.common.annotation.AuthExpert;
import com.my_medi.domain.consultationRequest.entity.ConsultationRequest;
import com.my_medi.domain.consultationRequest.entity.RequestStatus;
import com.my_medi.domain.consultationRequest.service.ConsultationRequestCommandService;
import com.my_medi.domain.consultationRequest.service.ConsultationRequestQueryService;
import com.my_medi.domain.expert.entity.Expert;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "[전문가 페이지] 상담요청 API")
@RestController
@RequestMapping("/api/v1/experts/consultations")
@RequiredArgsConstructor
public class ExpertConsultationApiController {
    private final ConsultationRequestCommandService consultationRequestCommandService;
    private final ConsultationRequestQueryService consultationRequestQueryService;
    private final ConsultationUseCase consultationUseCase;

    @Operation(summary = "전문가가 상담요청을 수락합니다.")
    @PatchMapping("/{consultationId}/approve")
    public ApiResponseDto<Long> approveConsultation(@AuthExpert Expert expert,
                                                    @PathVariable Long consultationId) {

        consultationUseCase.approveConsultationRequestAndSendNotificationToUser(expert, consultationId);

        return ApiResponseDto.onSuccess(consultationId);
    }

    @Operation(summary = "전문가가 상담요청을 거절합니다.")
    @PatchMapping("/{consultationId}/reject")
    public ApiResponseDto<Long> rejectConsultation(@AuthExpert Expert expert,
                                                   @PathVariable Long consultationId) {
        consultationRequestCommandService.rejectConsultation(consultationId, expert);
        return ApiResponseDto.onSuccess(consultationId);
    }

    @Operation(summary = "전문가가 자신에게 들어온 상담 요청 목록을 조회합니다.")
    @GetMapping
    public ApiResponseDto<ExpertConsultationDto.ExpertConsultationPageDto> getConsultationRequests(
            @AuthExpert Expert expert,
            @RequestParam(required = false) RequestStatus status,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size) {

        Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());

        Page<ConsultationRequest> requests;

        if (status != null) {
            requests = consultationRequestQueryService.getRequestByExpert(expert.getId(), status, pageable);
        } else {
            requests = consultationRequestQueryService.getAllRequestByExpert(expert.getId(), pageable);
        }

        List<ExpertConsultationDto.ExpertConsultationSummaryDto> dtoList =
                requests.map(ExpertConsultationConverter::toExpertConsultationDto).getContent();

        ExpertConsultationDto.ExpertConsultationPageDto result =
                ExpertConsultationDto.ExpertConsultationPageDto.builder()
                        .content(dtoList)
                        .totalPages(requests.getTotalPages())
                        .build();

        return ApiResponseDto.onSuccess(result);
    }


    @Operation(summary = "전문가가 수락된 상담을 삭제합니다.")
    @DeleteMapping("/{consultationId}")
    public ApiResponseDto<Long> removeApprovedConsultation(
            @AuthExpert Expert expert,
            @PathVariable Long consultationId
    ) {
        consultationRequestCommandService.removeApprovedConsultationByExpert(consultationId, expert);
        return ApiResponseDto.onSuccess(consultationId);
    }


}
