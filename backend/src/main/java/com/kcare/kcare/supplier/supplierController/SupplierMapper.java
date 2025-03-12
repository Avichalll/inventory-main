package com.kcare.kcare.supplier.supplierController;

import org.springframework.stereotype.Service;

import com.kcare.kcare.supplier.model.Supplier;

@Service
public class SupplierMapper {

    public Supplier toSupplier(SupplierRequest supplierRequest) {
        return Supplier.builder()
                .businessCard(supplierRequest.getBusinessCard())
                .supplierName(supplierRequest.getSupplierName())
                .contact(supplierRequest.getContact())
                .supplierCategory(supplierRequest.getSupplierCategory())
                .stateName(supplierRequest.getStateName())
                .email(supplierRequest.getEmail())
                .GSTIN(supplierRequest.getGSTIN())
                .address(supplierRequest.getAddress())
                .build();
    }

    public SupplierResponse toSupplierResponse(Supplier supplier) {

        return SupplierResponse.builder()
                .id(supplier.getId())
                .businessCard(supplier.getBusinessCard())
                .supplierName(supplier.getSupplierName())
                .supplierCategory(supplier.getSupplierCategory())
                .contact(supplier.getContact())
                // .sellingPrices(supplier.getSellingPrices())
                .stateName(supplier.getStateName())
                .email(supplier.getEmail())
                .GSTIN(supplier.getGSTIN())
                .address(supplier.getAddress())

                .build();

    }

}
