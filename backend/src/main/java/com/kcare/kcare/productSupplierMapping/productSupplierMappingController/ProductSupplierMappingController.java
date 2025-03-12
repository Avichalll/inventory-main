package com.kcare.kcare.productSupplierMapping.productSupplierMappingController;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/productSupplierMapping")
@RequiredArgsConstructor

public class ProductSupplierMappingController {

    private final ProductSupplierMappingService productSupplierMappingService;

    @PostMapping(path = "/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveProductSupplierMapping(
            @RequestBody ProductSupplierMappingRequest productSupplierMappingRequest) {

        return ResponseEntity
                .ok(productSupplierMappingService.saveProductSupplierMapping(productSupplierMappingRequest));

    }

}
