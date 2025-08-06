package com.my_medi.api.common.controller;

import com.amazonaws.HttpMethod;
import com.my_medi.api.common.dto.ApiResponseDto;
import com.my_medi.common.exception.ErrorStatus;
import com.my_medi.common.exception.GeneralException;
import com.my_medi.infra.discord.dto.ApiModificationRequest;
import com.my_medi.infra.discord.service.DiscordWebhookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.models.PathItem;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "API 수정 요청", description = "API 수정 요청을 Discord로 전송하는 API")
@RestController
@RequestMapping("/api/v1/modification-request")
@RequiredArgsConstructor
public class ApiModificationController {

    private final DiscordWebhookService discordWebhookService;
    @PostMapping
    @Operation(summary = "API 수정 요청",
            description = "특정 API에 대한 수정 요청을 Discord 웹훅으로 전송합니다." +
                    "이때 priority는 HIGH, MEDIUM, LOW 중 택 1" +
                    "requestType은 MODIFICATION, BUG_FIX, NEW_FEATURE 중 택 1 입니다."
    )
    public ApiResponseDto<String> requestApiModification(
            @Parameter(description = "수정 요청할 API 엔드포인트", required = true)
            @RequestParam String apiEndpoint,
            @Parameter(description = "수정 요청할 API HTTP method", required = true)
            @RequestParam PathItem.HttpMethod httpMethod,

            @Parameter(description = "수정 요청 상세 내용", required = true)
            @RequestBody ApiModificationRequest request) {

        try {
            // 기본값 설정
            if (request.getRequesterName() == null || request.getRequesterName().isEmpty()) {
                request.setRequesterName("익명");
            }
            if (request.getPriority() == null || request.getPriority().isEmpty()) {
                request.setPriority("LOW");
            }
            if (request.getRequestType() == null || request.getRequestType().isEmpty()) {
                request.setRequestType("MODIFICATION");
            }

            // Discord 웹훅으로 전송
            discordWebhookService.sendApiModificationRequest(apiEndpoint, httpMethod, request);

            return ApiResponseDto.onSuccess("API 수정 요청이 성공적으로 전송되었습니다.");

        } catch (Exception e) {
            //TODO exception handler
            throw new GeneralException(ErrorStatus._INTERNAL_SERVER_ERROR);
        }
    }

}
