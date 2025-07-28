package com.my_medi.api.image.controller;

import com.my_medi.api.common.dto.ApiResponseDto;
import com.my_medi.common.util.ImageUtil;
import com.my_medi.infra.s3.service.AwsS3Service;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;
@Tag(name = "[양쪽 역할 사용 가능]Image API", description = "이미지 API")
@RequestMapping("/api/v1/images")
@RequiredArgsConstructor
@RestController
public class ImageApiController {

    private final AwsS3Service awsS3Service;

    @Operation(summary = "이미지 요청 🔑", description = "이미지 파일들을 로컬 환경에서 불러와 s3에 업로드합니다." +
            "s3저장된 이미지 url을 반환합니다.")
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ApiResponseDto<List<String>> createPromotion(
            @RequestPart(value = "uploadImgFileList") List<MultipartFile> uploadImgFileList) {
        return ApiResponseDto.onSuccess(
                awsS3Service.uploadFiles(uploadImgFileList).stream()
                        .map(ImageUtil::appendUri)
                        .collect(Collectors.toList())
        );
    }
}
