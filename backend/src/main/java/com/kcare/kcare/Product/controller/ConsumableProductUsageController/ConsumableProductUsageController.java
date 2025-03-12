package com.kcare.kcare.Product.controller.ConsumableProductUsageController;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/productUsage")
@RequiredArgsConstructor
public class ConsumableProductUsageController {

    private final ConsumableProductUsageService consumableProductUsageService;

    @PostMapping("/save/{productId}")
    public ResponseEntity<?> saveConsumableProductUsage(@PathVariable("productId") Integer productId,
            @RequestBody ConsumableProductUsageRequest consumableProductUsageRequest) {
        return ResponseEntity
                .ok(consumableProductUsageService.saveConsumableProductUsage(productId, consumableProductUsageRequest));

    }

}
