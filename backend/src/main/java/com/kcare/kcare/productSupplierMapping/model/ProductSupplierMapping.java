package com.kcare.kcare.productSupplierMapping.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.kcare.kcare.Product.Model.Product;
import com.kcare.kcare.common.BaseEntity;
import com.kcare.kcare.supplier.model.Supplier;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@SuperBuilder
@Table(name = "productSupplierMapping")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")

public class ProductSupplierMapping extends BaseEntity {

    private Double buyingPrice;
    private Integer quantity;
    private Integer sgstTaxPercent;
    private Double totalsgstTaxAmount;
    private Integer cgstTaxPercent;
    private Double totalcgstTaxAmount;
    private Integer igstTaxPercent;
    private Double totalIgstTaxAmount;
    private Double taxableAmount;

    @ManyToOne
    @JoinColumn(name = "productId")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "supplierId")
    private Supplier supplier;
}
