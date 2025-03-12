package com.kcare.kcare.supplier.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.kcare.kcare.common.BaseEntity;
import com.kcare.kcare.productSupplierMapping.model.ProductSupplierMapping;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
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
@SuperBuilder
@Entity
@Table(name = "suppliersDetail")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")

public class Supplier extends BaseEntity {

    private String businessCard;
    private String supplierName;
    private String contact;
    private String supplierCategory;
    private String stateName;
    private String email;
    private String GSTIN;
    private String address;
    private String sellerPanNumber;
    private String bankName;
    private String accountNo;
    private String IFSC_code;
    private String branch;
    private String upiId;

    @Enumerated(EnumType.STRING)
    private SupplierType supplierType;

    @OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ProductSupplierMapping> productSupplierMapping;

}
