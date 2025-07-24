package com.my_medi.common.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class AwsS3Config {

    private final Environment env;
    @Bean
    public AmazonS3Client amazonS3Client() {
        BasicAWSCredentials awsCreds = new BasicAWSCredentials(
                env.getProperty("cloud.aws.credentials.accessKey"),
                env.getProperty("cloud.aws.credentials.secretKey"));
        return (AmazonS3Client) AmazonS3ClientBuilder.standard()
                .withRegion(env.getProperty("cloud.aws.region.static"))
                .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
                .build();
    }
}
