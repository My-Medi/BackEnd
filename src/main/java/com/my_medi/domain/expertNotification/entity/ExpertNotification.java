package com.my_medi.domain.expertNotification.entity;

import com.my_medi.domain.expert.entity.Expert;
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
    @JoinColumn(name = "expert_id", nullable = false)
    private Expert expert;

    // 알림 내용
    @Column(name = "notification_content", nullable = false, length = 50)
    private String notificationContent;

    @Column(nullable = false)
    private Long sourceId;
}
