package com.kcare.kcare.Product.controller.ProductController;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kcare.kcare.Product.Model.Product;
import com.kcare.kcare.Product.Model.ProductAttribute;
import com.kcare.kcare.Product.Model.ProductSubpart;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductMapper {

    public Product toProduct(ProductRequest request) {

        return Product.builder()
                .productName(request.getProductName())
                .aliasName(request.getAliasName())
                .productDescription(request.getProductDescription())
                .hsn_code(request.getHsn_code())
                .productType(request.getProductType().getDisplayType())
                .productCategory(request.getProductCategory())
                .quantity(request.getQuantity())
                .expiryDate(request.getExpiryDate())
                .thresholdValue(request.getThresholdValue())
                .isContainSubpart(request.isContainSubpart())
                .build();
    }

    public ProductResponse toProductResponse(Product product,
            List<ProductSubpartResponse> productSubpartResponses, List<ProductAttribute> productAttributes) {

        return ProductResponse.builder()
                .productId(product.getId())
                .aliasName(product.getAliasName())
                .productName(product.getProductName())
                .productCategory(product.getProductCategory())
                .productType(product.getProductType())

                .quantity(product.getQuantity())
                .expiryDate(product.getExpiryDate())
                .thresholdValue(product.getThresholdValue())

                // .images(images)
                // .suppliers(supplier)
                .productSubparts(productSubpartResponses)
                .productAttributes(productAttributes)
                .build();
    }

    public ProductSubpart toProductSubPart(Integer productId, Product parentProduct) {

        return ProductSubpart.builder()
                .subProductId(productId)
                .product(parentProduct)
                .build();

    }

    public ProductSubpartResponse toProductSubpartResponse(ProductSubpart productSubpart, String productName) {
        return ProductSubpartResponse.builder()
                .id(productSubpart.getId())
                .productId(productSubpart.getSubProductId())
                .productName(productName)
                .build();
    }

}
