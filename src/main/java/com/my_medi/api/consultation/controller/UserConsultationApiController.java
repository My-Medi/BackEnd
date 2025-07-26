package com.my_medi.api.consultation.controller;

import com.my_medi.api.common.dto.ApiResponseDto;
<<<<<<< HEAD
import com.my_medi.api.consultation.dto.UserConsultationDto;
import com.my_medi.api.consultation.mapper.UserConsultationConvert;
=======
import com.my_medi.api.consultation.service.SendNotificationToExpertUseCase;
>>>>>>> 85bef8c ([MEDI-58] 7.26 - 임시 커밋)
import com.my_medi.common.annotation.AuthUser;
import com.my_medi.domain.consultationRequest.entity.ConsultationRequest;
import com.my_medi.domain.consultationRequest.entity.RequestStatus;
import com.my_medi.domain.consultationRequest.service.ConsultationRequestCommandService;
import com.my_medi.domain.consultationRequest.service.ConsultationRequestQueryService;
import com.my_medi.domain.user.entity.User;
import com.my_medi.domain.user.repository.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "[사용자 페이지] 상담요청 API")
@RestController
@RequestMapping("/api/v1/users/consultations")
@RequiredArgsConstructor
public class UserConsultationApiController {

    private final ConsultationRequestCommandService consultationRequestCommandService;
<<<<<<< HEAD
    private final ConsultationRequestQueryService consultationRequestQueryService;

=======
    private final SendNotificationToExpertUseCase sendNotificationToExpertUseCase;
>>>>>>> 85bef8c ([MEDI-58] 7.26 - 임시 커밋)

    @Operation(summary = "전문가에게 상담요청을 보냅니다.")
    @PostMapping("/experts/{expertId}")
    public ApiResponseDto<Long> approveConsultation(@AuthUser User user,
                                                    @PathVariable Long expertId,
                                                    @RequestParam String comment) {

<<<<<<< HEAD
        return ApiResponseDto.onSuccess(consultationRequestCommandService
                .requestConsultationToExpert(user, expertId, comment));
=======
        Long requestId = consultationRequestCommandService
                .requestConsultationToExpert(user.getId(), expertId, comment);

        sendNotificationToExpertUseCase.SendConsultationRequestNotificationToExpert(expertId, requestId);

        //TODO user.getId() -> user(entity) convert
        return ApiResponseDto.onSuccess(requestId);
>>>>>>> 85bef8c ([MEDI-58] 7.26 - 임시 커밋)
    }

    @Operation(summary = "본인이 요청한 모든 상담 요청 목록을 조회합니다.")
    @GetMapping("/all")
    public ApiResponseDto<List<UserConsultationDto>> getAllMyConsultations(@AuthUser User user) {
        List<ConsultationRequest> requests = consultationRequestQueryService.getAllRequestByUser(user.getId());

        List<UserConsultationDto> dtoList = requests.stream()
                .map(UserConsultationConvert::toDto)
                .toList();

        return ApiResponseDto.onSuccess(dtoList);
    }

    @Operation(summary = "본인과 매칭된 상담 목록을 조회합니다. (status = ACCEPTED)")
    @GetMapping("/accepted")
    public ApiResponseDto<List<UserConsultationDto>> getMatchedConsultations(@AuthUser User user) {
        List<ConsultationRequest> acceptedRequests =
                consultationRequestQueryService.getRequestByUser(user.getId(), RequestStatus.ACCEPTED);

        List<UserConsultationDto> dtoList = acceptedRequests.stream()
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
