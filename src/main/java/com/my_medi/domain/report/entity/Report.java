package com.my_medi.domain.report.entity;

import com.my_medi.domain.model.entity.BaseTimeEntity;
import com.my_medi.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Report extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_id")
    private Long id;

    private LocalDate checkupDate;
    private Integer round; // 1차, 2차 등

    @Embedded
    private Measurement measurement;

    @Embedded
    private BloodPressure bloodPressure;

    @Embedded
    private BloodTest bloodTest;

    @Embedded
    private UrineTest urineTest;

    @Embedded
    private ImagingTest imagingTest;

    @Embedded
    private Interview interview;

    @Embedded
    private AdditionalTest additionalTest;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public void updateCheckupDate(LocalDate checkupDate) {
        this.checkupDate = checkupDate;
    }

    public void updateMeasurement(Measurement measurement) {
        this.measurement = measurement;
    }

    public void updateBloodPressure(BloodPressure bloodPressure) {
        this.bloodPressure = bloodPressure;
    }

    public void updateBloodTest(BloodTest bloodTest) {
        this.bloodTest = bloodTest;
    }

    public void updateImagingTest(ImagingTest imagingTest) {
        this.imagingTest = imagingTest;
    }

    public void updateInterview(Interview interview) {
        this.interview = interview;
    }

    public void updateAdditionalTest(AdditionalTest additionalTest) {
        this.additionalTest = additionalTest;
    }
}
