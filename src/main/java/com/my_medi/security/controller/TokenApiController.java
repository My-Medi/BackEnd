package com.my_medi.security.controller;

import com.my_medi.api.common.dto.ApiResponseDto;
import com.my_medi.security.jwt.dto.JwtToken;
import com.my_medi.security.jwt.service.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Token API", description = "JWT 토큰 관련 API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/tokens")
public class TokenApiController {

    private final TokenService tokenService;

    @Operation(summary = "[TEST용] 이메일로 JWT 토큰 발급")
    @GetMapping("/login")
    public ApiResponseDto<JwtToken> login(@RequestParam String kakaoEmail) {
        return ApiResponseDto.onSuccess(tokenService.login(kakaoEmail));
    }

    @Operation(summary = "토큰 재발급", description = "Refresh Token으로 Access Token 재발급합니다.")
    @PostMapping("/reissue")
    public ApiResponseDto<JwtToken> issueToken(@RequestParam String refresh) {
        return ApiResponseDto.onSuccess(tokenService.issueTokens(refresh));
    }
}