package com.my_medi.infra.discord.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiModificationRequest {
    private String description;
    private String requesterName;
    private String priority; // HIGH, MEDIUM, LOW
    private String requestType; // MODIFICATION, BUG_FIX, NEW_FEATURE
}
