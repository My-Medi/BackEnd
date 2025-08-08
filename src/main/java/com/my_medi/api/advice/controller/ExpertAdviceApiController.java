package com.my_medi.api.advice.controller;

import com.my_medi.api.advice.dto.AdviceRequestDto;
import com.my_medi.api.common.dto.ApiResponseDto;
import com.my_medi.api.consultation.validator.ExpertAllowedToViewUserInfoValidator;
import com.my_medi.common.annotation.AuthExpert;
import com.my_medi.domain.advice.service.AdviceCommandService;
import com.my_medi.domain.expert.entity.Expert;
import com.my_medi.domain.user.entity.User;
import com.my_medi.domain.user.exception.UserHandler;
import com.my_medi.domain.user.repository.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "[전문가 페이지]전문가 조언 API")
@RestController
@RequestMapping("/api/v1/experts/advice")
@RequiredArgsConstructor
public class ExpertAdviceApiController {
    private final AdviceCommandService adviceCommandService;
    private final ExpertAllowedToViewUserInfoValidator expertAllowedToViewUserInfoValidator;
    private final UserRepository userRepository;

    @Operation(summary = "전문가가 매칭된 사용자에게 조언을 등록합니다.")
    @PostMapping
    public ApiResponseDto<Long> registExpertAdviceToUser(@AuthExpert Expert expert,
                                                        @RequestParam Long userId,
                                                        @RequestBody AdviceRequestDto adviceRequestDto) {
        expertAllowedToViewUserInfoValidator.validateExpertHasAcceptedUser(expert.getId(), userId);
        User user = userRepository.findById(userId).orElseThrow(() -> UserHandler.NOT_FOUND);

        return ApiResponseDto.onSuccess(adviceCommandService
                .registExpertAdviceToUser(expert, user, adviceRequestDto));
    }

    @Operation(summary = "전문가가 자신이 등록한 조언을 수정합니다.")
    @PatchMapping
    public ApiResponseDto<Long> editExpertAdviceToUser(@AuthExpert Expert expert,
                                                       @RequestParam Long userId,
                                                       @RequestBody AdviceRequestDto adviceRequestDto) {
        expertAllowedToViewUserInfoValidator.validateExpertHasAcceptedUser(expert.getId(), userId);
        User user = userRepository.findById(userId).orElseThrow(() -> UserHandler.NOT_FOUND);

        return ApiResponseDto.onSuccess(adviceCommandService
                .editExpertAdviceToUser(expert, user, adviceRequestDto));
    }
}
