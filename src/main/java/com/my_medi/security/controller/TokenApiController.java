package com.my_medi.security.controller;

import com.my_medi.api.common.dto.ApiResponseDto;
import com.my_medi.domain.member.repository.MemberRepository;
import com.my_medi.security.jwt.dto.JwtToken;
import com.my_medi.security.jwt.dto.MemberLoginRequestDto;
import com.my_medi.security.jwt.service.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Tag(name = "Token API", description = "JWT 토큰 관련 API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/tokens")
public class TokenApiController {

    private final TokenService tokenService;
    private final MemberRepository memberRepository;

    @Operation(summary = "이메일로 JWT 토큰 발급")
    @PostMapping("/login")
    public ApiResponseDto<JwtToken> login(@RequestBody MemberLoginRequestDto memberLoginRequestDto) {
        return ApiResponseDto.onSuccess(tokenService.login(memberLoginRequestDto));
    }

    @Operation(summary = "토큰 재발급", description = "Refresh Token으로 Access Token 재발급합니다.")
    @PostMapping("/reissue")
    public ApiResponseDto<JwtToken> issueToken(@RequestParam String refresh) {
        return ApiResponseDto.onSuccess(tokenService.issueTokens(refresh));
    }

    @Operation(summary = "id unique 검증")
    @PostMapping("/id")
    public ApiResponseDto<Boolean> idValidator(@RequestParam String memberId) {
        boolean isValid = !memberRepository.existsByLoginId(memberId);
        return ApiResponseDto.onSuccess(isValid);
    }

    @Operation(summary = "nickname unique 검증")
    @PostMapping("/nickname")
    public ApiResponseDto<Boolean> nicknameValidator(@RequestParam String nickname) {
        boolean isValid = !memberRepository.existsByNickname(nickname);
        return ApiResponseDto.onSuccess(isValid);
    }
}