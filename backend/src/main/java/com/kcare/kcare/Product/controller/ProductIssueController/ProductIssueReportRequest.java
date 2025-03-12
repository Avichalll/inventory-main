package com.kcare.kcare.Product.controller.ProductIssueController;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductIssueReportRequest {

    // @NotBlank(message = "issueType should not be blanks")
    // private ProductIssueType issueType;
    private String otherIssueType;
    private String issueDescription;

}
