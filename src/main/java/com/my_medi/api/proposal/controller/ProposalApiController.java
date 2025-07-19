package com.my_medi.api.proposal.controller;

import com.my_medi.api.common.dto.ApiResponseDto;
import com.my_medi.api.proposal.dto.ProposalDto;
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

    /**
     * Retrieves the health proposal for a specified user.
     *
     * @param userId the ID of the user whose health proposal is to be retrieved
     * @return a successful API response containing the user's health proposal details
     */
    @Operation(summary = "user의 건강제안서를 조회합니다.")
    @GetMapping("/search")
    public ApiResponseDto<ProposalResponseDto.UserProposalDto> getUserProposal(@RequestParam Long userId) {
        Proposal proposal = proposalQueryService.getProposalByUserId(userId);
        return ApiResponseDto.onSuccess(ProposalConverter.toUserProposalDto(proposal));
    }

    /**
     * Creates a new health proposal for the specified user.
     *
     * @param userId the ID of the user for whom the proposal is being created
     * @param writeProposalDto the proposal data to be created
     * @return the ID of the newly created proposal wrapped in a success API response
     */
    @Operation(summary = "user의 건강제안서를 작성합니다.")
    @PostMapping("/write")
    public ApiResponseDto<Long> writeUserProposal(
            @RequestParam Long userId, @RequestBody ProposalDto writeProposalDto) {
        return ApiResponseDto.onSuccess(proposalCommandService.writeProposal(userId, writeProposalDto));
    }

    /**
     * Updates an existing health proposal for the specified user.
     *
     * @param userId the ID of the user whose proposal is to be updated
     * @param editProposalDto the proposal data to update
     * @return the ID of the updated proposal wrapped in a success API response
     */
    @Operation(summary = "user의 건강제안서를 수정합니다.")
    @PatchMapping("/edit")
    public ApiResponseDto<Long> editUserProposal(
            @RequestParam Long userId, @RequestBody ProposalDto editProposalDto) {
        return ApiResponseDto.onSuccess(proposalCommandService.editProposal(userId, editProposalDto));
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
