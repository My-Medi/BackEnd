package com.my_medi.api.consultation.controller;

import com.my_medi.api.common.dto.ApiResponseDto;
import com.my_medi.domain.consultationRequest.entity.ConsultationRequest;
import com.my_medi.domain.consultationRequest.entity.RequestStatus;
import com.my_medi.domain.consultationRequest.service.ConsultationRequestCommandService;
import com.my_medi.domain.consultationRequest.service.ConsultationRequestQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "전문가 상담요청 API")
@RestController
@RequestMapping("/api/v1/experts/consultations")
@RequiredArgsConstructor
public class ExpertConsultationApiController {

    private final ConsultationRequestCommandService consultationRequestCommandService;
    private final ConsultationRequestQueryService consultationRequestQueryService;

        @Operation(summary = "consult request 승낙")
        @PatchMapping("/{consultationId}/approve")
            public ApiResponseDto<Long> approveConsultation(@PathVariable Long consultationId) {
                consultationRequestCommandService.approveConsultation(consultationId);
                return ApiResponseDto.onSuccess(consultationId);
            }

        @Operation(summary = "consult request 거절")
        @PatchMapping("/{consultationId}/reject")
        public ApiResponseDto<Long> rejectConsultation(@PathVariable Long consultationId) {
            consultationRequestCommandService.rejectConsultation(consultationId);
            return ApiResponseDto.onSuccess(consultationId);
        }

    @Operation(summary = "자신에게 들어온 상담 요청 목록 조회")
    @GetMapping
    public ApiResponseDto<List<ConsultationRequest>> getConsultationRequests(
            @RequestParam Long expertId,
            @RequestParam(required = false) RequestStatus status
    ) {
        List<ConsultationRequest> requests;

        if (status != null) {
            requests = consultationRequestQueryService.getRequestByExpert(expertId, status);
        } else {
            requests = consultationRequestQueryService.getAllRequestByExpert(expertId);
        }

        return ApiResponseDto.onSuccess(requests);
    }

}
