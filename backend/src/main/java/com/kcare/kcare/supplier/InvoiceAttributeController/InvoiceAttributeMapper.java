package com.kcare.kcare.supplier.InvoiceAttributeController;

import org.springframework.stereotype.Service;

import com.kcare.kcare.supplier.model.Invoice;
import com.kcare.kcare.supplier.model.InvoiceAttribute;

@Service
public class InvoiceAttributeMapper {

    public InvoiceAttribute toInvoiceAttribute(InvoiceAttributeRequest invoiceAttributeRequest, Invoice invoice) {
        return InvoiceAttribute.builder()

                .invoice(invoice)
                .attributeName(invoiceAttributeRequest.getAttributeName())
                .attributeValue(invoiceAttributeRequest.getAttributeValue())
                .build();

    }

}
