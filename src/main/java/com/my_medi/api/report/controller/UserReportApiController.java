package com.my_medi.api.report.controller;


import com.my_medi.api.common.dto.ApiResponseDto;
import com.my_medi.common.util.ImageUtil;
import com.my_medi.infra.gpt.dto.HealthReportData;
import com.my_medi.infra.gpt.service.OpenAIService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "사용자 건강제안서 API")
@RestController
@RequestMapping("/api/v1/user/report")
@RequiredArgsConstructor
public class UserReportApiController {

    private final OpenAIService openAIService;

    @Operation(summary = "GPT API를 사용하여 건강검진 이미지를 원하는 데이터대로 추출합니다.")
    @PostMapping("/parsing")
    public ApiResponseDto<HealthReportData> parsingHealthReportImage(@RequestParam MultipartFile multipartFile) {
        return ApiResponseDto.onSuccess(
                openAIService.parseHealthReport(ImageUtil.convertToByte(multipartFile))
        );
    }
}
