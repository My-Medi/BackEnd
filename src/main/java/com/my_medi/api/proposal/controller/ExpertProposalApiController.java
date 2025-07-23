package com.my_medi.api.proposal.controller;

import com.my_medi.api.common.dto.ApiResponseDto;
import com.my_medi.api.proposal.dto.ProposalResponseDto;
import com.my_medi.api.proposal.mapper.ProposalConverter;
import com.my_medi.common.annotation.AuthExpert;
import com.my_medi.common.annotation.AuthUser;
import com.my_medi.domain.expert.entity.Expert;
import com.my_medi.domain.proposal.entity.Proposal;
import com.my_medi.domain.proposal.service.ProposalQueryService;
import com.my_medi.domain.user.entity.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "[전문가 페이지]건강제안서 API")
@RestController
@RequestMapping("/api/v1/experts/proposals")
@RequiredArgsConstructor
public class ExpertProposalApiController {

    private final ProposalQueryService proposalQueryService;

    @Operation(summary = "매칭된 사용자의 건강제안서를 조회합니다.")
    @GetMapping("/users/{userId}")
    public ApiResponseDto<ProposalResponseDto.UserProposalDto> getUserProposal(@AuthExpert Expert expert,
                                                                               @PathVariable Long userId) {
        //TODO expert가 user와 매칭되었는지 validation 필요
        /**
         * 권장 방법 : 새로운 service 클래스를 api package에서 생성
         * 해당 서비스 내에서 proposal을 조회하고 expert에게 열람 권한이 있는지 validation
         * return : UserProposalDto
         */
        Proposal proposal = proposalQueryService.getProposalByUserId(userId);
        return ApiResponseDto.onSuccess(ProposalConverter.toUserProposalDto(proposal));
    }
}
