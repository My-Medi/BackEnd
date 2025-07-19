package com.my_medi.api.proposal.dto;

import com.my_medi.domain.user.entity.User;
import lombok.Builder;
import lombok.Data;

public class ProposalResponseDto {

    @Data
    @Builder
    public static class UserProposalDto {
        private Long id;

        private Long userId;

        // 1. 직업 및 생활패턴
        private String lifeDescription;

        // 2. 건강 관심 분야(중복 선택 가능)
        private HealthInterestsDto healthInterestsDto;

        // 3. 최근 건강검진 결과 이상 수치가 있었던 항목이 있다면 선택해주세요.
        private AbnormalValueDto abnormalValueDto;

        // 4. 어떤 전문가의 도움을 받고 싶으신가요?
        private HelpTopicDto helpTopicDto;

        // 5. 목표나 기대하는 변화가 있다면 적어주세요.
        private String goal;
    }
}
