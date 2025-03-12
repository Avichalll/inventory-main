package com.kcare.kcare.Product.controller.ConsumableProductUsageController;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j

public class KafkaProductUsageSubcriber {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @KafkaListener(topics = "productUsage", groupId = "myGroup")
    public void consumerInsufficient(ProductUsageNotification productUsageNotification) {

        try {

            String json = objectMapper.writeValueAsString(productUsageNotification);
            log.info("Received message as JSON : {}", json);

        } catch (Exception e) {

            log.error("failed to convet into json");
        }

    }

}
