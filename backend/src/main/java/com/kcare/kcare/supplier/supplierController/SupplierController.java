package com.kcare.kcare.supplier.supplierController;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kcare.kcare.common.PageResponse;
import com.kcare.kcare.common.Response;
import com.kcare.kcare.common.StringUtils;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/supplier")
@RequiredArgsConstructor
// @Tag( name="Supplier Controller")
public class SupplierController {

    private final SupplierService supplierService;

    @PostMapping("/saveSupplierDetail")
    public ResponseEntity<Response<SupplierRequest>> saveSupplierDetail(@RequestBody SupplierRequest supplierRequest) {
        return ResponseEntity.ok(supplierService.saveSupplierDetail(supplierRequest));
    }

    @GetMapping("/findSupplier")
    public ResponseEntity<PageResponse<SupplierResponse>> getAllSupplier(
            @RequestParam(name = "supplierName", required = false) String supplierName,
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size) {

        String supplierNameTrimSpace = StringUtils.trimAllSpaces(supplierName);
        return ResponseEntity.ok(supplierService.getAllSupplier(supplierNameTrimSpace, page, size));
    }

    // public String trimAllSpaces()

}
