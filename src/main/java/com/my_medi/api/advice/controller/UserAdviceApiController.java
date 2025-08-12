package com.my_medi.api.advice.controller;

import com.my_medi.api.advice.dto.AdviceResponseDto.*;
import com.my_medi.api.advice.mapper.AdviceConverter;
import com.my_medi.api.advice.service.AdviceUseCase;
import com.my_medi.api.common.dto.ApiResponseDto;
import com.my_medi.common.annotation.AuthUser;
import com.my_medi.domain.advice.entity.Advice;
import com.my_medi.domain.advice.exception.AdviceHandler;
import com.my_medi.domain.advice.service.AdviceQueryService;
import com.my_medi.domain.user.entity.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@Tag(name = "[사용자 페이지]전문가 조언 API")
@RestController
@RequestMapping("/api/v1/users/advices")
@RequiredArgsConstructor
public class UserAdviceApiController {
    private final AdviceUseCase adviceUseCase;
    private final AdviceQueryService adviceQueryService;

    @Operation(summary = "사용자에게 등록된 모든 전문가의 조언들을 가져옵니다.")
    @GetMapping("/all")
    public ApiResponseDto<AdviceSimplePageResponse> getAllAdviceForUser(
            @AuthUser User user,
            @RequestParam(defaultValue = "0") int currentPage,
            @RequestParam int pageSize) {
        Page<Advice> advicePage = adviceUseCase
                .getPrioritizedAdviceDtoPageByUserId(user.getId(), currentPage, pageSize);

        return ApiResponseDto.onSuccess(AdviceConverter.toAdviceSimplePageResponse(advicePage));
    }

    @Operation(summary = "사용자에게 등록된 가장 최근의 전문가의 조언을 가져옵니다.")
    @GetMapping("/latest")
    public ApiResponseDto<AdviceDto> getLatestAdviceForUser(@AuthUser User user) {
        Advice latestAdvice = adviceQueryService.getLatestAdviceOfUser(user.getId())
                .orElseThrow(() -> AdviceHandler.NOT_FOUND);

        return ApiResponseDto.onSuccess(AdviceConverter.toAdvice(latestAdvice));
    }
}
