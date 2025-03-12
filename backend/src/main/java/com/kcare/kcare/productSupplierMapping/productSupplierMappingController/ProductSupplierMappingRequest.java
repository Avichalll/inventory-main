package com.kcare.kcare.productSupplierMapping.productSupplierMappingController;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder

public class ProductSupplierMappingRequest {

    private Integer productId;
    private Integer supplierId;
    private Double buyingPrice;
    private Integer quantity;
    private Integer sgstTaxPercent;
    private Double totalsgstTaxtAmount;
    private Integer cgstTaxPercent;
    private Double totalcgstTaxAmount;
    private Integer igstTaxPercent;
    private Double totalIgstTaxAmount;
    private Double taxableAmount;

}
