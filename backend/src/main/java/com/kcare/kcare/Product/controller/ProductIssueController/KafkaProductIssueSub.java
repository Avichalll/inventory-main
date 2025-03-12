package com.kcare.kcare.Product.controller.ProductIssueController;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

// @RestController
@Service
@Slf4j
public class KafkaProductIssueSub {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @KafkaListener(topics = "productIssue", groupId = "myGroup")
    public void cosumeMsg(KafkaProductIssueResponse kafkaProductIssueResponse) {

        try {

            String json = objectMapper.writeValueAsString(kafkaProductIssueResponse);
            log.info("Received message as JSON: {}", json);

            // log.info("consumed message is: {}", kafkaProductIssueResponse);
            // String json = objectMapper.writeValueAsString(kafkaProductIssueResponse);
            // log.info("json respose is : %s", json);

            // KafkaProductIssueResponse kafkaProductIssueResponse2 =
            // objectMapper.readValue(json,
            // KafkaProductIssueResponse.class);

            // log.info("i have reached here");
            // log.info("Received message: {}", kafkaProductIssueResponse2);

        } catch (Exception e) {
            log.error("failed to convet into json");
        }
    }

}
