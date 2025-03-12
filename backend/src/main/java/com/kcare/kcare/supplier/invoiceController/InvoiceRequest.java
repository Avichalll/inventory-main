package com.kcare.kcare.supplier.invoiceController;

import java.time.LocalDate;
import java.util.List;

import com.kcare.kcare.supplier.InvoiceAttributeController.InvoiceAttributeRequest;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder

public class InvoiceRequest {

    private Integer productId;
    private LocalDate invoiceDate;
    private String invoicedNo;
    private List<InvoiceAttributeRequest> invoiceAttributesRequests;

}
