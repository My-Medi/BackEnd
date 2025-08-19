package com.my_medi.api.expertNotification.service;

import com.my_medi.api.expertNotification.dto.ExpertNotificationResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.my_medi.api.expertNotification.dto.ExpertNotificationResponseDto.*;


@Service
@RequiredArgsConstructor
public class ExpertNotificationEventListener {

    public void handleConsultationRequestCreated(ExpertNotificationDto event) {

    }

}
