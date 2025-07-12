package com.my_medi.api.schedule.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "전문가 스케줄 API")
@RestController
@RequestMapping("/api/v1/experts/schedules")
@RequiredArgsConstructor
public class ExpertScheduleApiController {

    @Operation(summary = "전문가가 매칭된 유저에게 스케줄을 넣어줍니다")
    @PostMapping("/users/{userId}")

}
