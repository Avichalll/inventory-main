package com.kcare.kcare.supplier.supplierController;

import org.springframework.data.jpa.domain.Specification;

import com.kcare.kcare.supplier.model.Supplier;

public class SupplierSpecification {

    public static Specification<Supplier> withSupplierName(String supplierName) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(criteriaBuilder.lower(root.get("supplierName")),
                supplierName.toLowerCase());
    }

    public static Specification<Supplier> withSupplierNameStartingWith(String supplierPrefix) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.lower(root.get("supplierName")),
                supplierPrefix.toLowerCase() + "%");
    }

    public static Specification<Supplier> withSupplierNameContaining(String supplierInfix) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.lower(root.get("supplierName")),
                "%" + supplierInfix.toLowerCase() + "%");
    }

}
