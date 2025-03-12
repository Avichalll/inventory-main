package com.kcare.kcare.supplier.invoiceController;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/invoice")
@AllArgsConstructor
public class InvoiceController {

    private final InvoiceService invoiceService;

    @PostMapping("/save/{productId}")
    public ResponseEntity<?> saveInvoiceDetail(@PathVariable("productId") Integer productId,
            @RequestBody InvoiceRequest invoiceRequest) {

        return ResponseEntity.ok(invoiceService.saveInvoiceDetail(productId, invoiceRequest));

    }

}
