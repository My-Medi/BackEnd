package com.my_medi.api.consultation.controller;

import com.my_medi.api.common.dto.ApiResponseDto;
import com.my_medi.domain.consultationRequest.service.ConsultationRequestCommandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "[사용자 페이지] 상담요청 API")
@RestController
@RequestMapping("/api/v1/users/consultations")
@RequiredArgsConstructor
public class UserConsultationApiController {

    private final ConsultationRequestCommandService consultationRequestCommandService;

    @Operation(summary = "전문가에게 상담요청을 보냅니다.")
    @PostMapping("/experts/{expertId}")
    public ApiResponseDto<Long> approveConsultation(@PathVariable Long expertId,
                                                    @RequestParam Long userId,
                                                    @RequestParam String comment) {
        return ApiResponseDto.onSuccess(consultationRequestCommandService
                .requestConsultationToExpert(userId, expertId, comment));
    }

}
