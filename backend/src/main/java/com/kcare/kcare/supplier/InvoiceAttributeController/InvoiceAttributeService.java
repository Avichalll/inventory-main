package com.kcare.kcare.supplier.InvoiceAttributeController;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.kcare.kcare.common.Response;
import com.kcare.kcare.supplier.model.Invoice;
import com.kcare.kcare.supplier.model.InvoiceAttribute;
import com.kcare.kcare.supplier.repository.InvoiceAttributeRepository;
import com.kcare.kcare.supplier.repository.InvoiceRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class InvoiceAttributeService {

        private final InvoiceRepository invoiceRepository;
        private final InvoiceAttributeRepository invoiceAttributeRepository;
        private final InvoiceAttributeMapper invoiceAttributeMapper;

        public Response<InvoiceAttributeRequest> saveInvoiceAttribute(Integer invoiceId,
                        InvoiceAttributeRequest invoiceAttributeRequest) {
                Invoice invoice = invoiceRepository.findById(invoiceId)
                                .orElseThrow(() -> new EntityNotFoundException("Invoice not found"));

                InvoiceAttribute invoiceAttribute = invoiceAttributeMapper.toInvoiceAttribute(invoiceAttributeRequest,
                                invoice);
                invoiceAttributeRepository.save(invoiceAttribute);

                return new Response<>(
                                invoiceAttributeRequest,
                                LocalDateTime.now(),
                                "successfully saved",
                                HttpStatus.CREATED);

        }

}
