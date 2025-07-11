package com.my_medi.api.common.example;

import com.my_medi.common.annotation.ApiErrorStatusExample;
import com.my_medi.common.exception.ErrorStatus;
import com.my_medi.domain.user.exception.UserErrorStatus;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Example API : 에러코드 문서화")
@RestController
@RequestMapping("/api/v1/examples")
@RequiredArgsConstructor
public class ExampleApiController {
    @GetMapping("/global")
    @Operation(summary = "글로벌 ( 인증 , aop, 서버 내부 오류등)  관련 에러 코드 나열")
    @ApiErrorStatusExample(ErrorStatus.class)
    public void getGlobalErrorCode() {}

    @GetMapping("/user")
    @Operation(summary = "유저 도메인 관련 에러 코드 나열")
    @ApiErrorStatusExample(UserErrorStatus.class)
    public void getUserErrorCode() {}

}
