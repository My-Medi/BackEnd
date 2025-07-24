package com.my_medi.api.consultation.controller;

import com.my_medi.api.common.dto.ApiResponseDto;
import com.my_medi.common.annotation.AuthUser;
import com.my_medi.domain.consultationRequest.service.ConsultationRequestCommandService;
import com.my_medi.domain.user.entity.User;
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
    public ApiResponseDto<Long> approveConsultation(@AuthUser User user,
                                                    @PathVariable Long expertId,
                                                    @RequestParam String comment) {
        //TODO user.getId() -> user(entity) convert
        return ApiResponseDto.onSuccess(consultationRequestCommandService
                .requestConsultationToExpert(user.getId(), expertId, comment));
    }

    //TODO 본인이 요청한 상담 요청 목록 조회

    //TODO 본인과 매칭된 상담 목록 조회(only status = ACCEPTED)

    //TODO 본인이 요청한 상담 취소(only status = REQUESTED)
}
