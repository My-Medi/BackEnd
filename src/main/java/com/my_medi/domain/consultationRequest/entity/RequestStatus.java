package com.my_medi.domain.consultationRequest.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RequestStatus {
    REQUESTED,   // 요청됨
    ACCEPTED,    // 수락됨
    REJECTED     // 거절됨
}