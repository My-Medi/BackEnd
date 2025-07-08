package com.my_medi.domain.userNotification.entity;

import com.my_medi.domain.enums.NotificationType;
import com.my_medi.domain.expert.entity.Expert;
import com.my_medi.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class UserNotification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "expert_id", nullable = false)
    private Expert expert;

    // 알림 제목
    @Column(name = "notification_title", nullable = false, length = 50)
    private String notificationTitle;

    // 알림 내용
    @Column(name = "notification_contents", nullable = false, length = 50)
    private String notificationContents;

    // 알림 종류
    @Enumerated(EnumType.STRING)
    @Column(name = "notification_type", nullable = false)
    private NotificationType notificationType;
}
