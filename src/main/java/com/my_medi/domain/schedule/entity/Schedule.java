package com.my_medi.domain.schedule.entity;

import com.my_medi.api.schedule.dto.EditScheduleDto;
import com.my_medi.domain.expert.entity.Expert;
import com.my_medi.domain.model.entity.BaseTimeEntity;
import com.my_medi.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Entity
@Table(name = "schedules")
@Getter
@NoArgsConstructor
@AllArgsConstructor

@SuperBuilder
public class Schedule extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "expert_id")
    private Expert expert;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String location;

    public void update(EditScheduleDto dto) {
        this.title = dto.getTitle();
        this.description = dto.getDescription();
        this.startTime = dto.getStartTime();
        this.endTime = dto.getEndTime();
        this.location = dto.getLocation();
    }


}
