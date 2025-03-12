package com.kcare.kcare.supplier.supplierController;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SupplierRequest {

    private String businessCard; // ! image form
    private String supplierName;
    private String contact;
    private String supplierCategory;
    private Integer sellingPrices;
    private String stateName;
    private String email;
    private String GSTIN;
    private String address;
    private String invoiceNo;
    private LocalDate invoiceDate;
    private String sellerPanNumber;
    private String bankName;
    private String accountNo;
    private String IFSC_code;
    private String branch;
    private String upiId;

}
