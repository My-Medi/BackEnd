package com.my_medi.domain.expertNotification.entity;

import com.my_medi.domain.expert.entity.Expert;
import com.my_medi.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ExpertNotification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "expert_id", nullable = false)
    private Expert expertId;

    // 알림 제목
    @Column(name = "alarm_title", nullable = false, length = 50)
    private String notificationTitle;

    // 알림 내용
    @Column(name = "alarm_contents", nullable = false, length = 50)
    private String notificationContents;

    // 알림 종류 - 전문가는 CONSULTATION 고정?
    @Enumerated(EnumType.STRING)
    @Column(name = "alarm_type", nullable = false)
    private NotificationType notificationType;
}
