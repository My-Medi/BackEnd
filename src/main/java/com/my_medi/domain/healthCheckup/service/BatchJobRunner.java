package com.my_medi.domain.healthCheckup.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("han")
@Slf4j
@Component
@RequiredArgsConstructor
public class BatchJobRunner implements CommandLineRunner {

    private final JobLauncher jobLauncher;
    private final Job healthCheckupJob;

    @Override
    public void run(String... args) throws Exception {
        log.info("=== 건강검진 데이터 배치 작업 시작 ===");

        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("timestamp", System.currentTimeMillis())
                .toJobParameters();

        try {
            var execution = jobLauncher.run(healthCheckupJob, jobParameters);
            log.info("=== 배치 작업 완료 - 상태: {} ===", execution.getStatus());

            // 실행 결과 상세 정보
            execution.getStepExecutions().forEach(stepExecution -> {
                log.info("Step: {}, 읽은 레코드: {}, 처리된 레코드: {}, 건너뛴 레코드: {}",
                        stepExecution.getStepName(),
                        stepExecution.getReadCount(),
                        stepExecution.getWriteCount(),
                        stepExecution.getSkipCount());
            });

        } catch (Exception e) {
            log.error("배치 작업 실행 중 오류 발생", e);
            throw e;
        }
    }
}
