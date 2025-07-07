package com.my_medi.domain.resume.entity;

import com.my_medi.domain.enums.ExpertJob;
import com.my_medi.domain.enums.GenderState;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import java.time.LocalDateTime;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Resume {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long resumeId;

    @Column(name = "expert_id", nullable = false)
    private Long expertId;

    // 전문가 이름, 나이, 성별, 프로필 사진
    @Column(name = "name", nullable = false, length = 10)
    private String name;
    @Column(name = "age", nullable = false)
    private Integer age;
    @Enumerated(EnumType.STRING)
    private GenderState gender;
    @Lob
    @Column(columnDefinition = "LONGBLOB") // MySQL 기준
    private byte[] imageData;

    // 전문가 직업
    private ExpertJob expertJob;
    // 자격증, PDF...등등
    @Column(name = "file_path", nullable = false, length = 50)
    private String filePath;
    // 경력
    @Column(name = "company", nullable = false, length = 20)
    private String company;
    @Column(name = "company_role", nullable = false, length = 20)
    private String companyRole;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    @Column(name = "description", nullable = false, length = 100)
    private String description;
}
