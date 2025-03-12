package com.kcare.kcare.Product.controller.ConsumableProductUsageController;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class ConsumableProductUsageResposne {

    private Integer id;
    private Integer productId;
    private String productName;
    private Integer initialQuantity;
    private Integer quantityTaken;
    private Integer quantityAvailable;
    private String allottedPersonName;
    private String allottedPersonDepartment;

}
