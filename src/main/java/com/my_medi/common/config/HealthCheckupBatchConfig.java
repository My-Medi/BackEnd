package com.my_medi.common.config;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.S3Object;
import com.my_medi.domain.healthCheckup.entity.HealthCheckup;
import com.my_medi.domain.healthCheckup.service.HealthCheckupItemProcessor;
import com.my_medi.domain.healthCheckup.service.JpaHealthCheckupItemWriter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.transaction.PlatformTransactionManager;

import java.io.InputStream;

@Slf4j
@Profile("han")
@Configuration
@RequiredArgsConstructor
@EnableBatchProcessing
public class HealthCheckupBatchConfig {
    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;
    private final HealthCheckupItemProcessor healthCheckupItemProcessor;
    private final JpaHealthCheckupItemWriter jpaHealthCheckupItemWriter;
    private final AmazonS3Client amazonS3Client;
    private final Environment env;


    @Bean
    public FlatFileItemReader<String[]> healthCheckupItemReader() {
        S3Object s3Object = amazonS3Client.getObject(
                env.getProperty("cloud.aws.s3.bucket"),
                "health_checkup_20221231.CSV");
        return new FlatFileItemReaderBuilder<String[]>()
                .name("healthCheckupItemReader")
                .resource(new InputStreamResource(s3Object.getObjectContent()))
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
