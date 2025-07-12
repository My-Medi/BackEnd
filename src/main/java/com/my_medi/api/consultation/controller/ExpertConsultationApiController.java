package com.my_medi.api.consultation.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "전문가 상담요청 API")
@RestController
@RequestMapping("/api/v1/experts/consultations")
@RequiredArgsConstructor
public class ExpertConsultationApiController {

    //    @Operation(summary = "consult request 승락")
//    @PatchMapping("/{consultationId}")
//    public ApiResponseDto<Long> approveConsultation(@PathVariable Long consultationId) {
//
//    }

}
