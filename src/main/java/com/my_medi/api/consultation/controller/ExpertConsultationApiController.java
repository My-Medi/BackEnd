package com.my_medi.api.consultation.controller;

import com.my_medi.api.common.dto.ApiResponseDto;
import com.my_medi.domain.consultationRequest.service.ConsultationRequestCommandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "전문가 상담요청 API")
@RestController
@RequestMapping("/api/v1/experts/consultations")
@RequiredArgsConstructor
public class ExpertConsultationApiController {

    private final ConsultationRequestCommandService consultationRequestCommandService;

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

}
