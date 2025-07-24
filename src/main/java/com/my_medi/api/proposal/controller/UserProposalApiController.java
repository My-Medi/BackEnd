package com.my_medi.api.proposal.controller;

import com.my_medi.api.common.dto.ApiResponseDto;
import com.my_medi.api.proposal.dto.ProposalRequestDto;
import com.my_medi.api.proposal.dto.ProposalResponseDto;
import com.my_medi.api.proposal.mapper.ProposalConverter;
import com.my_medi.common.annotation.AuthUser;
import com.my_medi.domain.proposal.entity.Proposal;
import com.my_medi.domain.proposal.service.ProposalCommandService;
import com.my_medi.domain.proposal.service.ProposalQueryService;
import com.my_medi.domain.user.entity.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "[사용자 페이지]건강제안서 API")
@RestController
@RequestMapping("/api/v1/users/proposals")
@RequiredArgsConstructor
public class UserProposalApiController {

    private final ProposalCommandService proposalCommandService;
    private final ProposalQueryService proposalQueryService;

    @Operation(summary = "사용자의 건강제안서를 작성합니다.")
    @PostMapping
    public ApiResponseDto<Long> writeUserProposal
            (@AuthUser User user, @RequestBody ProposalRequestDto writeProposalRequestDto)
    {
        //TODO user.getId() -> user(entity) convert
        return ApiResponseDto.onSuccess(proposalCommandService
                .writeProposal(user.getId(), writeProposalRequestDto));
    }

    @Operation(summary = "user의 건강제안서를 수정합니다.")
    @PatchMapping
    public ApiResponseDto<Long> editUserProposal
            (@AuthUser User user, @RequestBody ProposalRequestDto editProposalRequestDto)
    {
        //TODO user.getId() -> user(entity) convert
        return ApiResponseDto.onSuccess(proposalCommandService
                .editProposal(user.getId(), editProposalRequestDto));
    }

    @Operation(summary = "사용자 본인의 건강제안서를 조회합니다.")
    @GetMapping
    public ApiResponseDto<ProposalResponseDto.UserProposalDto> getUserProposal
            (@AuthUser User user)
    {
        Proposal proposal = proposalQueryService.getProposalByUserId(user.getId());
        return ApiResponseDto.onSuccess(ProposalConverter.toUserProposalDto(proposal));
    }
}
