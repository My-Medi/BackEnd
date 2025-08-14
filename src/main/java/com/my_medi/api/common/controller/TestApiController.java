package com.my_medi.api.common.controller;

import com.my_medi.api.common.dto.ApiResponseDto;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@Slf4j
@Tag(name = "테스트 용 API")
@RestController
@RequestMapping("/api/v1/test")
@RequiredArgsConstructor
public class TestApiController {

    @Value("${discord.webhook.url}")
    private String webhook;

    @GetMapping
    public TestDto test(@RequestParam String message) {
        TestDto dto = new TestDto(message);

        return dto;
    }

    @GetMapping("/health-check")
    public ApiResponseDto<String> healthCheckup() {
        return ApiResponseDto.onSuccess(HttpStatus.OK.toString());
    }

    @GetMapping("/error")
    public ApiResponseDto<String> error() {
        log.info("web hook :{}, error", webhook);
        throw new NullPointerException();
    }

    @Getter
    private class TestDto{
        private String message;

        public TestDto(String message) {
            this.message = message;
        }
    }
}
