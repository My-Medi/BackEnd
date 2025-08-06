package com.my_medi.domain.notification.entity;

import com.my_medi.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.*;
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

    // 알림 내용
    @Column(name = "notification_content", nullable = false, length = 50)
    private String notificationContent;

    @Column(nullable = false)
    private Long sourceId;

    // 알림 종류
    @Enumerated(EnumType.STRING)
    @Column(name = "notification_type", nullable = false)
    private NotificationType notificationType;

    @Builder.Default
    @Column(nullable = false)
    private Boolean isRead = false;

    public void updateIsReadState() {
        this.isRead = true;
    }
}
