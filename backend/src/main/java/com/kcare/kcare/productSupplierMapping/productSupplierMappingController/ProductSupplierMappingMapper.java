package com.kcare.kcare.productSupplierMapping.productSupplierMappingController;

import org.springframework.stereotype.Service;

import com.kcare.kcare.Product.Model.Product;
import com.kcare.kcare.productSupplierMapping.model.ProductSupplierMapping;
import com.kcare.kcare.supplier.model.Supplier;

@Service
public class ProductSupplierMappingMapper {

    public ProductSupplierMapping toProductSupplierMapping(
            ProductSupplierMappingRequest productSupplierMappingRequest, Product product, Supplier supplier) {

        return ProductSupplierMapping.builder()
                .product(product)
                .supplier(supplier)
                .buyingPrice(productSupplierMappingRequest.getBuyingPrice())
                .quantity(productSupplierMappingRequest.getQuantity())
                .sgstTaxPercent(productSupplierMappingRequest.getSgstTaxPercent())
                .totalsgstTaxAmount(productSupplierMappingRequest.getTotalIgstTaxAmount())
                .cgstTaxPercent(productSupplierMappingRequest.getCgstTaxPercent())
                .totalcgstTaxAmount(productSupplierMappingRequest.getTotalcgstTaxAmount())
                .igstTaxPercent(productSupplierMappingRequest.getIgstTaxPercent())
                .totalIgstTaxAmount(productSupplierMappingRequest.getTotalIgstTaxAmount())
                .taxableAmount(productSupplierMappingRequest.getTaxableAmount())
                .build();
    }

}
