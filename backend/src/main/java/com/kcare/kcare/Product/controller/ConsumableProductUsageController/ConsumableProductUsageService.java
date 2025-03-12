package com.kcare.kcare.Product.controller.ConsumableProductUsageController;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kcare.kcare.Product.Model.ConsumableProductUsage;
import com.kcare.kcare.Product.Model.Product;
import com.kcare.kcare.Product.repository.ConsumableProductUsageRepository;
import com.kcare.kcare.Product.repository.ProductRepository;
import com.kcare.kcare.common.Response;
import com.kcare.kcare.handler.InsufficientQuantityException;
import com.kcare.kcare.handler.InvalidProductCategoryException;
import com.kcare.kcare.handler.OutOfStockException;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ConsumableProductUsageService {

        private final ProductRepository productRepository;
        private final ConsumableProductUsageRepository consumableProductUsageRepository;
        private final ConsumableProductUsageMapper consumableProductUsageMapper;
        private final KafkaProductUsagePublisher kafkaProductUsagePublisher;

        @Transactional
        public Response<?> saveConsumableProductUsage(Integer productId,
                        ConsumableProductUsageRequest consumableProductUsageRequest) {
                Product product = productRepository.findById(productId)
                                .orElseThrow(() -> new EntityNotFoundException("product not available in database"));
                // ! this feature is only applicable to consumable product

                if (product.getProductCategory() != "Consumable") {
                        throw new InvalidProductCategoryException("Not Consumble Product");
                }
                if (product.getQuantity() == 0) {
                        throw new OutOfStockException("Product out of stock");
                }
                if (consumableProductUsageRequest.getQuantityRequest() > product.getQuantity()) {
                        throw new InsufficientQuantityException("Insufficient Product Quantity");
                }
                ConsumableProductUsage consumableProductUsage = consumableProductUsageMapper.toConsumableProductUsage(
                                product,
                                consumableProductUsageRequest);
                ProductUsageNotification productUsageNotification = consumableProductUsageMapper
                                .toProductUsageNotification(product, consumableProductUsage);
                consumableProductUsageRepository.save(consumableProductUsage);
                product.setQuantity(product.getQuantity() - consumableProductUsageRequest.getQuantityRequest());
                productRepository.save(product);
                kafkaProductUsagePublisher.sendProductInsufficentMessage(productUsageNotification);

                // ! here i will send notification

                return new Response<>(
                                consumableProductUsageRequest,
                                LocalDateTime.now(),
                                "process done",
                                HttpStatus.ACCEPTED

                );

        }

}
