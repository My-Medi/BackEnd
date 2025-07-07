package com.my_medi.domain.expertAlarm.entity;

import com.my_medi.domain.enums.AlarmType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ExpertAlarm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long expertAlarmId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "expert_id", nullable = false)
    private Long expertId;

    // 상담? 진찰? 날짜
    @Column(name = "consultation_date", nullable = false)
    private LocalDate consultationDate;

    // 시작 시간
    @Column(name = "start_time", nullable = false)
    private LocalTime startTime;

    // 종료 시간
    @Column(name = "end_time", nullable = false)
    private LocalTime endTime;

    // 알림 제목
    @Column(name = "alarm_title", nullable = false, length = 50)
    private String alarmTitle;

    // 알림 내용
    @Column(name = "alarm_contents", nullable = false, length = 50)
    private String alarmContents;

    // 장소
    @Column(name = "alarm_location", nullable = false, length = 50)
    private String alarmLocation;

    // 알림 종류 - 전문가는 CONSULTATION 고정?
    @Enumerated(EnumType.STRING)
    @Column(name = "alarm_type", nullable = false)
    private AlarmType alarmType;
}
