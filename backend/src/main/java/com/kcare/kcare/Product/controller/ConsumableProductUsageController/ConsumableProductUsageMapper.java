package com.kcare.kcare.Product.controller.ConsumableProductUsageController;

import org.springframework.stereotype.Service;

import com.kcare.kcare.Product.Model.ConsumableProductUsage;
import com.kcare.kcare.Product.Model.Product;

@Service

public class ConsumableProductUsageMapper {

        public ConsumableProductUsage toConsumableProductUsage(Product product,
                        ConsumableProductUsageRequest consumableProductUsageRequest) {
                return ConsumableProductUsage.builder()
                                .initialQuantity(product.getQuantity())
                                .quantityAvailable(product.getQuantity()
                                                - consumableProductUsageRequest.getQuantityRequest())
                                .quantityTaken(consumableProductUsageRequest.getQuantityRequest())
                                .allottedPersonDepartment(consumableProductUsageRequest.getAllotedPersonDepartment())
                                .allottedPersonName(consumableProductUsageRequest.getAllotedPersonName())
                                .product(product)
                                .build();

        }

        public ConsumableProductUsageResposne tConsumableProductUsageResposne(Product product,
                        ConsumableProductUsage consumableProductUsage) {

                return ConsumableProductUsageResposne.builder()
                                .id(consumableProductUsage.getId())
                                .productId(product.getId())
                                .productName((product.getProductName()))
                                .initialQuantity(consumableProductUsage.getInitialQuantity())
                                .quantityAvailable(consumableProductUsage.getQuantityAvailable())
                                .quantityTaken(consumableProductUsage.getQuantityTaken())
                                .allottedPersonDepartment(consumableProductUsage.getAllottedPersonDepartment())
                                .allottedPersonName(consumableProductUsage.getAllottedPersonName())
                                .build();
        }

        public ProductUsageNotification toProductUsageNotification(Product product,
                        ConsumableProductUsage consumableProductUsage) {

                return ProductUsageNotification.builder()
                                .id(consumableProductUsage.getId())
                                .productId(product.getId())
                                .productName(product.getProductName())
                                .availableQuantity(product.getQuantity())
                                .thresholdValue(product.getThresholdValue())
                                .outOfStockAt(consumableProductUsage.getCreatedDate())

                                .build();

        }

}
