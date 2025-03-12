package com.kcare.kcare.Product.controller.ProductIssueController;

import org.springframework.stereotype.Service;

import com.kcare.kcare.Product.Model.Product;
import com.kcare.kcare.Product.Model.ProductIssueReport;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProductIssueReportMapper {

    public ProductIssueReport toProductIssueReport(ProductIssueReportRequest productIssueReportRequest,
            Product product) {

        return ProductIssueReport.builder()
                // .issueType(productIssueReportRequest.getIssueType())
                .otherIssueType(productIssueReportRequest.getOtherIssueType())
                .issueDescription(productIssueReportRequest.getIssueDescription())
                .product(product)
                .build();
    }

    public KafkaProductIssueResponse toKafakProductIssueResposne(ProductIssueReport productIssueReport,
            Product product) {
        return KafkaProductIssueResponse.builder()
                .id(productIssueReport.getId())
                .productId(product.getId())
                .productName(product.getProductName())
                // .issueType(productIssueReport.getIssueType())
                .issueDescription(productIssueReport.getIssueDescription())
                .build();
    }

    public ProductIssueResponse toProductIssueResponse(ProductIssueReport productIssueReport, Product product) {

        if (productIssueReport == null) {
            throw new IllegalArgumentException("ProductIssueReport cannot be null");
        }

        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }
        log.info("nothing is null");

        return ProductIssueResponse.builder()
                .id(productIssueReport.getId())
                .productId(product.getId())
                .productName(product.getProductName())
                // .issueType(productIssueReport.getIssueType())
                .otherIssueType(productIssueReport.getOtherIssueType())
                .issueDescription(productIssueReport.getIssueDescription())
                .build();
    }

}
