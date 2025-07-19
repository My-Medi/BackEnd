package com.my_medi.api.userNotification.controller;


import com.my_medi.domain.userNotification.repository.UserNotificationRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "사용자 API")
@RestController
@RequestMapping("/api/v1/users") // <- 필요시 변경
@RequiredArgsConstructor
public class UserNotificationApiController {
    private final UserNotificationRepository userNotificationRepository;
}
