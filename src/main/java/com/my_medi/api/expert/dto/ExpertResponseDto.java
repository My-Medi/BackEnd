package com.my_medi.api.expert.dto;

import com.my_medi.api.career.dto.CareerRequestDto;
import com.my_medi.domain.expert.entity.Specialty;
import com.my_medi.domain.member.entity.Gender;
import com.my_medi.domain.member.entity.Role;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class ExpertResponseDto{

    @Data
    @Builder
    public static class ExpertProfileDto{
        // Member 공통 필드
        private String name;
        private LocalDate birthDate;
        private Gender gender;
        private String nickname;
        private String phoneNumber;
        private String profileImgUrl;
        private Role role;

        // Expert 전용 필드
        private Specialty specialty;
        private String organizationName;
        private String licenseFileUrl;
        private String introduction;

        // 추가
        private List<CareerRequestDto> careers;

    }

    @Data
    @Builder
    public static class ExpertSummaryProfileDto{
        private Long expertId;
        private Specialty specialty;
        private String name;
        private String nickname;

        private String introduction;
        private String organizationName;
        //TODO 필드 추가(경력 사항)
    }

    @Data
    @Builder
    public static class ExpertProfileListDto{
        @Builder.Default
        private List<ExpertSummaryProfileDto> expertSummaryProfileDtoList = new ArrayList<>();
        private final int page;
        private final int totalPages;
    }
}