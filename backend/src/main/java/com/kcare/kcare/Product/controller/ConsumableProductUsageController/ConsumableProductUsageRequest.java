package com.kcare.kcare.Product.controller.ConsumableProductUsageController;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder

public class ConsumableProductUsageRequest {

    // private Integer initialQuantity;
    private Integer quantityRequest;
    // private Integer quantityAvailable;
    private String allotedPersonName;
    private String allotedPersonDepartment;

}
