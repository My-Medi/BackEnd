package com.my_medi.api.proposal.controller;

import com.my_medi.api.common.dto.ApiResponseDto;
import com.my_medi.api.proposal.dto.ProposalResponseDto;
import com.my_medi.api.proposal.service.GetUserProposalByExpertUseCase;
import com.my_medi.common.annotation.AuthExpert;
import com.my_medi.domain.expert.entity.Expert;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.my_medi.api.proposal.dto.ProposalResponseDto.*;

@Tag(name = "[전문가 페이지]건강제안서 API")
@RestController
@RequestMapping("/api/v1/experts/proposals")
@RequiredArgsConstructor
public class ExpertProposalApiController {
    private final GetUserProposalByExpertUseCase getUserProposalByExpertUseCase;

    @Operation(summary = "매칭된 사용자의 건강제안서를 조회합니다.")
    @GetMapping("/users/{userId}")
    public ApiResponseDto<UserProposalDto> getUserProposal(@AuthExpert Expert expert,
                                                           @PathVariable Long userId) {
        return ApiResponseDto.onSuccess(getUserProposalByExpertUseCase.getUserProposalForExpert(expert, userId));
    }
}
