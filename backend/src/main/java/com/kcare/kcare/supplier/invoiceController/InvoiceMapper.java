package com.kcare.kcare.supplier.invoiceController;

import org.springframework.stereotype.Service;

import com.kcare.kcare.Product.Model.Product;
import com.kcare.kcare.supplier.model.Invoice;

@Service
public class InvoiceMapper {

    public Invoice toInvoid(InvoiceRequest invoiceRequest, Product product) {
        return Invoice.builder().product(product)
                .invoiceDate(invoiceRequest.getInvoiceDate())
                .invoiceNo(invoiceRequest.getInvoicedNo())
                .build();
    }

}
