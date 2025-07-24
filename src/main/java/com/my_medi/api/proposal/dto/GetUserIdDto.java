package com.my_medi.api.proposal.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetUserIdDto {
    private Long userId;
}
