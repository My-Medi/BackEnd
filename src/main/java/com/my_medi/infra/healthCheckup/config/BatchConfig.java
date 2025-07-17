package com.my_medi.infra.healthCheckup.config;

import com.my_medi.infra.healthCheckup.entity.HealthCheckupDocument;
import com.my_medi.infra.healthCheckup.service.HealthCheckupItemProcessor;
import com.my_medi.infra.healthCheckup.service.MongoHealthCheckupItemWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@RequiredArgsConstructor
public class BatchConfig {
    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;
    private final HealthCheckupItemProcessor healthCheckupItemProcessor;
    private final MongoHealthCheckupItemWriter mongoHealthCheckupItemWriter;

    @Bean
    public FlatFileItemReader<String[]> healthCheckupItemReader() {
        return new FlatFileItemReaderBuilder<String[]>()
                .name("healthCheckupItemReader")
                .resource(new ClassPathResource("health_checkup.csv"))
                .lineTokenizer(new DelimitedLineTokenizer(","))
                .linesToSkip(1) // 헤더 스킵
                .build();
    }

    @Bean
    public Step healthCheckupStep() {
        return new StepBuilder("healthCheckupStep", jobRepository)
                .<String[], HealthCheckupDocument>chunk(1000, transactionManager)
                .reader(healthCheckupItemReader())
                .processor(healthCheckupItemProcessor)
                .writer(mongoHealthCheckupItemWriter)
                .faultTolerant()
                .skipLimit(1000)
                .skip(Exception.class)
                .build();
    }

    @Bean
    public Job healthCheckupJob() {
        return new JobBuilder("healthCheckupJob", jobRepository)
                .start(healthCheckupStep())
                .build();
    }
}
