package com.my_medi.api.schedule.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder

public class EditScheduleDto {
    private String memo;
    private String location;

    private LocalDate meetingDate;
    private int hour;
    private int minute;
    private boolean isAm;
}
