package com.sqs.sqsDemo.config;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClient;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.annotation.PostConstruct;

@Profile("production")
@Configuration
public class ProductionConfiguration {

    @Bean
    AmazonSQS sqsClient() {

        return AmazonSQSClient
                .builder()
                .withCredentials(new ProfileCredentialsProvider())
                .withRegion(Regions.US_EAST_2)
                .build();
    }

}
