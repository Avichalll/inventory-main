package com.kcare.kcare.Product.controller.ProductIssueController;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class KafkaProductIssueResponse {

    private Integer id;
    private Integer productId;
    private String productName;
    // private ProductIssueType issueType;
    private String issueDescription;

}
