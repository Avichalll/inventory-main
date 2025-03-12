package com.kcare.kcare.Product.controller.ConsumableProductUsageController;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class ProductUsageNotification {

    private Integer id;
    private Integer productId;
    private String productName;
    private Integer availableQuantity;
    private Integer thresholdValue;
    private LocalDateTime outOfStockAt;
}
