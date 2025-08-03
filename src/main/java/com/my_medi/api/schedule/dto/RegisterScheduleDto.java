package com.my_medi.api.schedule.dto;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
public class RegisterScheduleDto {

    private String title;
    private String memo;
    private String location;
    private LocalDate meetingDate;
    private int hour;
    private int minute;
    private boolean isAm;
}
