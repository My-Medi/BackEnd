package com.my_medi.api.expert.controller;

import com.my_medi.api.common.dto.ApiResponseDto;
import com.my_medi.api.consultation.validator.ExpertAllowedToViewUserInfoValidator;
import com.my_medi.api.expert.dto.ExpertResponseDto;
import com.my_medi.api.expert.mapper.ExpertConverter;
import com.my_medi.common.annotation.AuthUser;
import com.my_medi.common.annotation.RequestParamList;
import com.my_medi.common.util.EnumConvertUtil;
import com.my_medi.domain.expert.entity.Expert;
import com.my_medi.domain.expert.entity.Specialty;
import com.my_medi.domain.expert.service.ExpertQueryService;
import com.my_medi.domain.user.entity.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Tag(name = "[사용자 페이지] 전문가 조회 API")
@RestController
@RequestMapping("/api/v1/users/experts")
@RequiredArgsConstructor
public class UserQueryExpertApiController {

    private final ExpertQueryService expertQueryService;

    @PreAuthorize("hasRole('ROLE_USER')")
    @Operation(summary = "전문가 프로필 정보를 전체 조회합니다.")
    @GetMapping("/{expertId}/profiles/details")
    public ApiResponseDto<ExpertResponseDto.ExpertInfoDto> getExpertProfile(@PathVariable Long expertId) {

        Expert expert = expertQueryService.getExpertById(expertId);

        ExpertResponseDto.ExpertInfoDto result = ExpertConverter.toExpertInfoDto(expert);

        return ApiResponseDto.onSuccess(result);
    }


    @Operation(summary = "사용자가 전문가의 프로필을 상세 조회합니다. [전문가 상세]", description = "[건강관리요청서] 보내기 전 ")
    @GetMapping("/{expertId}")
    public ApiResponseDto<ExpertResponseDto.ExpertDetailForUserDto> getExpertDetailForUser(@PathVariable Long expertId) {
        Expert expert = expertQueryService.getExpertById(expertId);
        return ApiResponseDto.onSuccess(ExpertConverter.toExpertDetailForUserDto(expert));
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @Operation(summary = "전문가 목록을 조회합니다. 이때 15개씩 등록 순으로 조회합니다.")
    @GetMapping
    public ApiResponseDto<ExpertResponseDto.ExpertProfileListDto> getAllExpertProfile(
            @RequestParam(value = "specialty") Optional<List<String>> specialtyOpt,
            @RequestParam(defaultValue = "0") int currentPage,
            @RequestParam int pageSize) {

        Pageable pageable = PageRequest.of(currentPage, pageSize);

        // Optional<List<String>> → List<Specialty>
        List<Specialty> specialtyList = specialtyOpt
                .map(list -> list.stream()
                        .filter(s -> s != null && !s.isBlank()) // 빈 문자열 필터
                        .map(s -> EnumConvertUtil.convert(Specialty.class, s))
                        .toList()
                )
                .orElse(null); // 파라미터 없으면 null

        Page<Expert> expertPage = expertQueryService.getExpertListByFiltering(specialtyList, pageable);
        return ApiResponseDto.onSuccess(ExpertConverter.toExpertProfileListDto(expertPage));
    }
}
