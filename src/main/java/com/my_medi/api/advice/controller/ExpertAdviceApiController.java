package com.my_medi.api.advice.controller;

import com.my_medi.api.advice.dto.AdviceRequestDto;
import com.my_medi.api.advice.dto.AdviceResponseDto.AdviceSimplePageResponse;
import com.my_medi.api.advice.mapper.AdviceConverter;
import com.my_medi.api.advice.service.AdviceUseCase;
import com.my_medi.api.common.dto.ApiResponseDto;
import com.my_medi.api.consultation.validator.ExpertAllowedToViewUserInfoValidator;
import com.my_medi.common.annotation.AuthExpert;
import com.my_medi.domain.advice.entity.Advice;
import com.my_medi.domain.advice.service.AdviceCommandService;
import com.my_medi.domain.expert.entity.Expert;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@Tag(name = "[전문가 페이지]전문가 조언 API")
@RestController
@RequestMapping("/api/v1/experts/advices")
@RequiredArgsConstructor
public class ExpertAdviceApiController {
    private final AdviceCommandService adviceCommandService;
    private final ExpertAllowedToViewUserInfoValidator expertAllowedToViewUserInfoValidator;
    private final AdviceUseCase adviceUseCase;

    @Operation(summary = "전문가가 매칭된 사용자에게 조언을 등록합니다.")
    @PostMapping
    public ApiResponseDto<Long> registerExpertAdviceToUser(@AuthExpert Expert expert,
                                                         @RequestParam Long userId,
                                                         @RequestBody AdviceRequestDto adviceRequestDto) {
        expertAllowedToViewUserInfoValidator.validateExpertHasAcceptedUser(expert.getId(), userId);

        return ApiResponseDto.onSuccess(adviceCommandService
                .registerExpertAdviceToUser(expert, userId, adviceRequestDto));
    }

    @Operation(summary = "전문가가 자신이 등록한 조언들을 조회합니다.")
    @GetMapping("/{userId}")
    public ApiResponseDto<AdviceSimplePageResponse> getExpertAdvice(
            @AuthExpert Expert expert,
            @PathVariable Long userId,
            @RequestParam(defaultValue = "0") int currentPage,
            @RequestParam int pageSize) {
        Page<Advice> advicePage = adviceUseCase
                .getPrioritizedAdviceDtoPageByExpertId(expert.getId(), userId, currentPage, pageSize);

        return ApiResponseDto.onSuccess(AdviceConverter.toAdviceSimplePageResponse(advicePage));
    }

    @Operation(summary = "전문가가 자신이 등록한 조언을 수정합니다.")
    @PatchMapping("/{adviceId}")
    public ApiResponseDto<Long> editExpertAdviceToUser(@AuthExpert Expert expert,
                                                       @RequestParam Long userId,
                                                       @PathVariable Long adviceId,
                                                       @RequestBody AdviceRequestDto adviceRequestDto) {
        expertAllowedToViewUserInfoValidator.validateExpertHasAcceptedUser(expert.getId(), userId);

        return ApiResponseDto.onSuccess(adviceCommandService.editExpertAdviceToUser(adviceId, adviceRequestDto));
    }

    @Operation(summary = "전문가가 자신이 등록한 조언을 삭제합니다.")
    @DeleteMapping("/{adviceId}")
    public ApiResponseDto<Void> deleteExpertAdviceFromUser(@AuthExpert Expert expert,
                                                           @RequestParam Long userId,
                                                           @PathVariable Long adviceId) {
        expertAllowedToViewUserInfoValidator.validateExpertHasAcceptedUser(expert.getId(), userId);

        return ApiResponseDto.onSuccess(adviceCommandService.deleteExpertAdviceFromUser(adviceId));
    }
}
