package com.kcare.kcare.Product.controller.ProductController;

import java.time.LocalDate;
import java.util.List;

import com.kcare.kcare.Product.Model.ProductAttribute;
import com.kcare.kcare.Product.Model.ProductType;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class ProductRequest {

    // ! product Details
    // private List<MultipartFile> images;

    @NotNull(message = "productName should not be null")
    private String productName;
    private String aliasName;
    private String productDescription;
    private String hsn_code;
    @Enumerated(EnumType.STRING)
    private ProductType productType;

    private String productCategory;
    private Double buyingPrice;
    private Integer quantity;
    private LocalDate expiryDate;
    private Integer thresholdValue;
    private boolean isContainSubpart;

    private Integer parentProductId;

    private List<ProductAttribute> productAttributes;

}
