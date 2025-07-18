package com.my_medi.common.config;

import com.my_medi.domain.healthCheckup.entity.HealthCheckup;
import com.my_medi.domain.healthCheckup.service.HealthCheckupItemProcessor;
import com.my_medi.domain.healthCheckup.service.JpaHealthCheckupItemWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;

@Profile({"han","deploy"})
@Configuration
@RequiredArgsConstructor
@EnableBatchProcessing
public class HealthCheckupBatchConfig {
    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;
    private final HealthCheckupItemProcessor healthCheckupItemProcessor;
    private final JpaHealthCheckupItemWriter jpaHealthCheckupItemWriter;


    @Bean
    public FlatFileItemReader<String[]> healthCheckupItemReader() {
        return new FlatFileItemReaderBuilder<String[]>()
                .name("healthCheckupItemReader")
                .resource(new ClassPathResource("health_checkup_20221231.CSV"))
                .lineTokenizer(new DelimitedLineTokenizer(","))
                .fieldSetMapper(fieldSet -> {
                    String[] values = new String[fieldSet.getFieldCount()];
                    for (int i = 0; i < fieldSet.getFieldCount(); i++) {
                        values[i] = fieldSet.readString(i);
                    }
                    return values;
                })
                .linesToSkip(1) // 헤더 스킵
                .build();
    }

    @Bean
    public Step healthCheckupStep() {
        return new StepBuilder("healthCheckupStep", jobRepository)
                .<String[], HealthCheckup>chunk(1000, transactionManager)
                .reader(healthCheckupItemReader())
                .processor(healthCheckupItemProcessor)
                .writer(jpaHealthCheckupItemWriter)
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
