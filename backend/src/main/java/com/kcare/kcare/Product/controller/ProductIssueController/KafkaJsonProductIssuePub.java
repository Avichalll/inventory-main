package com.kcare.kcare.Product.controller.ProductIssueController;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaJsonProductIssuePub {

    private final KafkaTemplate<String, KafkaProductIssueResponse> kafkaTemplate;
    // private final ObjectMapper objectMapper;

    public void sendMessage(KafkaProductIssueResponse kafkaProductIssueResponse) {

        try {
            // String productJson = objectMapper.writeValueAsString(productIssueReport);
            log.info("sending message: {}", kafkaProductIssueResponse);
            Message<KafkaProductIssueResponse> message = MessageBuilder.withPayload(kafkaProductIssueResponse)
                    .setHeader(KafkaHeaders.TOPIC, "productIssue").build();

            kafkaTemplate.send(message);
        } catch (Exception e) {

        }

    }

}
