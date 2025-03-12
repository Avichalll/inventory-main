package com.kcare.kcare.supplier.InvoiceAttributeController;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/invoiceAttribute")
@AllArgsConstructor
public class InvoiceAttributeController {

    private final InvoiceAttributeService invoiceAttribteService;

    @PostMapping("/save/{invoiceId}")
    public ResponseEntity<?> saveInvoiceAttribute(@PathVariable("invoiceId") Integer invoiceId,
            @RequestBody InvoiceAttributeRequest invoiceAttributeRequest) {

        return ResponseEntity.ok(invoiceAttribteService.saveInvoiceAttribute(invoiceId, invoiceAttributeRequest));

    }
}
