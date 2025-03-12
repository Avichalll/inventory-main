package com.kcare.kcare.supplier.invoiceController;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.kcare.kcare.Product.Model.Product;
import com.kcare.kcare.Product.repository.ProductRepository;
import com.kcare.kcare.common.Response;
import com.kcare.kcare.supplier.InvoiceAttributeController.InvoiceAttributeMapper;
import com.kcare.kcare.supplier.InvoiceAttributeController.InvoiceAttributeRequest;
import com.kcare.kcare.supplier.model.Invoice;
import com.kcare.kcare.supplier.model.InvoiceAttribute;
import com.kcare.kcare.supplier.repository.InvoiceAttributeRepository;
import com.kcare.kcare.supplier.repository.InvoiceRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor

public class InvoiceService {

    private final InvoiceAttributeRepository invoiceAttributeRepository;
    private final ProductRepository productRepository;
    private final InvoiceRepository invoiceRepository;
    private final InvoiceMapper invoiceMapper;
    private final InvoiceAttributeMapper invoiceAttributeMapper;

    public Response<InvoiceRequest> saveInvoiceDetail(Integer productId, InvoiceRequest invoiceRequest) {

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("product not found"));

        Invoice invoice = invoiceMapper.toInvoid(invoiceRequest, product);
        Invoice savedInvoice = invoiceRepository.save(invoice);
        if (invoiceRequest.getInvoiceAttributesRequests() != null) {

            List<InvoiceAttributeRequest> invoiceAttributeRequest = invoiceRequest.getInvoiceAttributesRequests();
            if (!invoiceAttributeRequest.isEmpty()) {
                invoiceAttributeRequest.forEach(attribute -> {
                    InvoiceAttribute invoiceAttribute = invoiceAttributeMapper.toInvoiceAttribute(attribute,
                            savedInvoice);
                    invoiceAttributeRepository.save(invoiceAttribute);
                });
            }
        }

        return new Response<>(
                invoiceRequest,
                LocalDateTime.now(),
                "Invoice detail saved",
                HttpStatus.CREATED);

    }

}
