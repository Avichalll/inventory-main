package com.kcare.kcare.supplier.InvoiceAttributeController;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder

public class InvoiceAttributeRequest {

    private Integer invoiceId;
    private String attributeName;
    private String attributeValue;

}
