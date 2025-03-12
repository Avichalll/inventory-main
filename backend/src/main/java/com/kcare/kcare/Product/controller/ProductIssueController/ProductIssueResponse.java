package com.kcare.kcare.Product.controller.ProductIssueController;

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

public class ProductIssueResponse {

    private Integer id;
    private Integer productId;
    private String productName;
    // private ProductIssueType issueType;
    private String otherIssueType;
    private String issueDescription;

}
