package com.my_medi.api.common.example;

import com.my_medi.common.exception.ErrorStatus;
import com.my_medi.common.exception.GeneralException;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "테스트 용 API")
@RestController
@RequestMapping("/api/v1/test")
@RequiredArgsConstructor
public class TestApiController {

    @GetMapping
    public TestDto test(@RequestParam String message) {
        TestDto dto = new TestDto(message);

        return dto;
    }

    @GetMapping("/exceptions")
    public void testException() {
        throw new GeneralException(ErrorStatus._INTERNAL_SERVER_ERROR);
    }


    @Getter
    private class TestDto{
        private String message;

        public TestDto(String message) {
            this.message = message;
        }
    }
}
