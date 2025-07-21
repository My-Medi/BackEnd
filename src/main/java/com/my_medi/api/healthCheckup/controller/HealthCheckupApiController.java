package com.my_medi.api.healthCheckup.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "건강검진 공공데이터 활용 API")
@RestController
@RequestMapping("/api/v1/health-checkup")
@RequiredArgsConstructor
public class HealthCheckupApiController {


//    @PostMapping("/test")
//    public ApiResponseDto<String> saveSample() {
//        return ApiResponseDto.onSuccess(healthCheckupService.saveSampleData());
//    }
//
//    @GetMapping
//    public ApiResponseDto<List<HealthCheckupDocument>> getAll() {
//        return ApiResponseDto.onSuccess(healthCheckupService.findAll());
//    }
}
