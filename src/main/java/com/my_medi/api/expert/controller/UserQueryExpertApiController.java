package com.my_medi.api.expert.controller;

import com.my_medi.api.common.dto.ApiResponseDto;
import com.my_medi.api.consultation.validator.ExpertAllowedToViewUserInfoValidator;
import com.my_medi.api.expert.dto.ExpertResponseDto;
import com.my_medi.api.expert.mapper.ExpertConverter;
import com.my_medi.common.annotation.AuthUser;
import com.my_medi.common.annotation.RequestParamList;
import com.my_medi.domain.expert.entity.Expert;
import com.my_medi.domain.expert.entity.Specialty;
import com.my_medi.domain.expert.service.ExpertQueryService;
import com.my_medi.domain.user.entity.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "[사용자 페이지] 전문가 조회 API")
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserQueryExpertApiController {

    private final ExpertQueryService expertQueryService;
    private final ExpertAllowedToViewUserInfoValidator expertAllowedToViewUserInfoValidator;

    @Operation(summary = "전문가 프로필 정보를 전체 조회합니다.")
    @GetMapping("/experts/{expertId}")
    public ApiResponseDto<ExpertResponseDto.ExpertProfileDto> getExpertProfile(@AuthUser User user,
                                                                               @PathVariable Long expertId) {
        // 전문가 엔티티 조회 -> 유저와 전문가 매칭 확인 -> DTO 변환 후 반환
        Expert expert = expertQueryService.getExpertById(expertId);

        expertAllowedToViewUserInfoValidator.validateExpertHasAcceptedUser(expert.getId(), user.getId());

        ExpertResponseDto.ExpertProfileDto result = ExpertConverter.toExpertProfileDto(expert);

        return ApiResponseDto.onSuccess(result);
    }

    //TODO 인가처리
    @Operation(summary = "전문가 목록을 조회합니다. 이때 15개씩 등록 순으로 조회합니다.")
    @GetMapping("/experts")
    public ApiResponseDto<ExpertResponseDto.ExpertProfileListDto> getAllExpertProfile(
            @Nullable @RequestParamList(value = "specialty") List<Specialty> specialty,
            @RequestParam(defaultValue = "0") int currentPage,
            @RequestParam int pageSize) {

        Pageable pageable = PageRequest.of(currentPage, pageSize);
        Page<Expert> expertPage = expertQueryService.getExpertListByFiltering(specialty, pageable);
        return ApiResponseDto.onSuccess(ExpertConverter.toExpertProfileListDto(expertPage));
    }



}
