package com.my_medi.api.proposal.controller;

import com.my_medi.api.common.dto.ApiResponseDto;
import com.my_medi.api.proposal.dto.ProposalRequestDto;
import com.my_medi.api.proposal.dto.ProposalResponseDto;
import com.my_medi.api.proposal.mapper.ProposalConverter;
import com.my_medi.domain.proposal.entity.Proposal;
import com.my_medi.domain.proposal.service.ProposalCommandService;
import com.my_medi.domain.proposal.service.ProposalQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "건강제안서 API")
@RestController
@RequestMapping("/api/v1/user/proposal")
@RequiredArgsConstructor
public class ProposalApiController {
    private final ProposalCommandService proposalCommandService;
    private final ProposalQueryService proposalQueryService;

    @Operation(summary = "user의 건강제안서를 조회합니다.")
    @GetMapping
    public ApiResponseDto<ProposalResponseDto.UserProposalDto> getUserProposal(@RequestParam Long userId) {
        Proposal proposal = proposalQueryService.getProposalByUserId(userId);
        return ApiResponseDto.onSuccess(ProposalConverter.toUserProposalDto(proposal));
    }

    @Operation(summary = "user의 건강제안서를 작성합니다.")
    @PostMapping
    public ApiResponseDto<Long> writeUserProposal(
            @RequestParam Long userId, @RequestBody ProposalRequestDto writeProposalRequestDto) {
        return ApiResponseDto.onSuccess(proposalCommandService.writeProposal(userId, writeProposalRequestDto));
    }

    @Operation(summary = "user의 건강제안서를 수정합니다.")
    @PatchMapping
    public ApiResponseDto<Long> editUserProposal(
            @RequestParam Long userId, @RequestBody ProposalRequestDto editProposalRequestDto) {
        return ApiResponseDto.onSuccess(proposalCommandService.editProposal(userId, editProposalRequestDto));
    }

    // 건강제안서 삭제
    /*
    @Operation(summary = "user의 건강제안서를 삭제합니다.")
    @DeleteMapping("/remove")
    public ApiResponseDto<Long> removeUserProposal(@RequestParam Long userId) {
        return ApiResponseDto.onSuccess();
    }
    */
}
