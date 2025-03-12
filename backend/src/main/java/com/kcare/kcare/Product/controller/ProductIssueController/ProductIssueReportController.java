package com.kcare.kcare.Product.controller.ProductIssueController;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/productIssue")
@RequiredArgsConstructor
public class ProductIssueReportController {

    private final ProductIssueReportService productIssueReportService;

    @PostMapping("/save/{productId}")
    public ResponseEntity<?> saveProductIssue(@RequestBody ProductIssueReportRequest productIssueReportRequest,
            @PathVariable("productId") Integer productId) {
        return ResponseEntity
                .ok(productIssueReportService.saveProductIssueReport(productId, productIssueReportRequest));

    }

    @GetMapping("/getProductIssue/{productId}")
    public ResponseEntity<?> getAllProductIssueReport(
            @PathVariable("productId") Integer productId,
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "6", required = false) int size) {

        return ResponseEntity.ok(productIssueReportService.getAllProductIssueReport(productId, page, size));

    }

}
