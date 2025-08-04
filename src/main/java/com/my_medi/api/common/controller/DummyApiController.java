package com.my_medi.api.common.controller;

import com.my_medi.api.common.dto.ApiResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "더미 데이터 생성 API", description = "더미데이터 생성 API")
@RestController
@RequestMapping("/api/v1/dummy")
@RequiredArgsConstructor
public class DummyApiController {

    @Operation(summary = "전문가 더미 생성 API")
    @PostMapping("/experts")
    public ApiResponseDto<String> createExpertsDummy(@RequestParam int count) {
        return ApiResponseDto.onSuccess("success");
    }

    @Operation(summary = "알림 더미 생성 API", description = "send to user")
    @PostMapping("/notifications/users/{userId}")
    public ApiResponseDto<String> createNotificationToUserDummy(@PathVariable Long userId,
                                                                @RequestParam int count) {
        return ApiResponseDto.onSuccess("success");
    }

    @Operation(summary = "알림 더미 생성 API", description = "send to expert")
    @PostMapping("/notifications/experts/{expertId}")
    public ApiResponseDto<String> createNotificationToExpertDummy(@PathVariable Long expertId,
                                                                  @RequestParam int count) {
        return ApiResponseDto.onSuccess("success");
    }
}
