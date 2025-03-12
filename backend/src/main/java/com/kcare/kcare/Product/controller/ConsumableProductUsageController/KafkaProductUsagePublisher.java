package com.kcare.kcare.Product.controller.ConsumableProductUsageController;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaProductUsagePublisher {

    public final KafkaTemplate<String, ProductUsageNotification> kafkaTemplate;

    public void sendProductInsufficentMessage(ProductUsageNotification productUsageNotification) {

        try {

            log.info("product Insufficent message sending.. {}", productUsageNotification);

            Message<ProductUsageNotification> message = MessageBuilder.withPayload(productUsageNotification)
                    .setHeader(KafkaHeaders.TOPIC, "productUsage").build();

            kafkaTemplate.send(message);
        } catch (Exception e) {

            log.error("unable to send ");

        }

    }

}
