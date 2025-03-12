package com.kcare.kcare.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration

public class KafakTopicConfig {

    @Bean
    public NewTopic productIssueTopic() {
        return TopicBuilder
                .name("productIssue")
                .build();
    }

    @Bean
    public NewTopic productUsageTopic() {
        return TopicBuilder
                .name("productUsage")
                .build();
    }

}
