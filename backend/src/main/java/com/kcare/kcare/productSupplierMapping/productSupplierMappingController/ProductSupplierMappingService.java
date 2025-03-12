package com.kcare.kcare.productSupplierMapping.productSupplierMappingController;

import java.text.DecimalFormat;
import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.kcare.kcare.Product.Model.Product;
import com.kcare.kcare.Product.repository.ProductRepository;
import com.kcare.kcare.common.Response;
import com.kcare.kcare.handler.MismatchedInputException;
import com.kcare.kcare.handler.ResourceNotFoundException;
import com.kcare.kcare.productSupplierMapping.model.ProductSupplierMapping;
import com.kcare.kcare.productSupplierMapping.repository.ProductSupplierMappingRepository;
import com.kcare.kcare.supplier.model.Supplier;
import com.kcare.kcare.supplier.repository.SupplierRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j

public class ProductSupplierMappingService {

        private final ProductSupplierMappingRepository productSupplierMappingRepository;
        private final ProductRepository productRepository;
        private final SupplierRepository supplierRepository;
        private final ProductSupplierMappingMapper productSupplierMappingMapper;

        private final DecimalFormat df = new DecimalFormat("0.00");

        public Response<ProductSupplierMappingRequest> saveProductSupplierMapping(
                        ProductSupplierMappingRequest productSupplierMappingRequest) {

                Product product = productRepository.findById(productSupplierMappingRequest.getProductId())
                                .orElseThrow(() -> new ResourceNotFoundException("Product Not Available"));

                Supplier supplier = supplierRepository.findById(productSupplierMappingRequest.getSupplierId())
                                .orElseThrow(() -> new ResourceNotFoundException("Supplier Not Found"));

                if (productSupplierMappingRequest.getIgstTaxPercent() != null
                                && productSupplierMappingRequest.getCgstTaxPercent() == null
                                && productSupplierMappingRequest.getSgstTaxPercent() == null
                                && productSupplierMappingRequest.getBuyingPrice() != null) {

                        Double calIgstTaxAmount = (productSupplierMappingRequest.getIgstTaxPercent().doubleValue()
                                        / 100)
                                        * productSupplierMappingRequest.getBuyingPrice();
                        calIgstTaxAmount = Double.valueOf(df.format(calIgstTaxAmount));

                        if (productSupplierMappingRequest.getTotalIgstTaxAmount() != Double
                                        .parseDouble(df.format(calIgstTaxAmount))) {
                                throw new MismatchedInputException("Wrong Total Igst Tax Amount");
                        }
                        Double taxableAmount = productSupplierMappingRequest.getBuyingPrice() + calIgstTaxAmount;

                        if (productSupplierMappingRequest.getTotalIgstTaxAmount() != Double
                                        .parseDouble(df.format(taxableAmount))) {
                                throw new MismatchedInputException("Invalid Input Value");
                        }

                } else {

                        Double calSgstTaxAmount = (productSupplierMappingRequest.getSgstTaxPercent().doubleValue()
                                        / 100);
                        log.info("calSgstTaxAmount  is : " + calSgstTaxAmount);

                        calSgstTaxAmount = Double
                                        .valueOf(df.format(calSgstTaxAmount
                                                        * productSupplierMappingRequest.getBuyingPrice()));
                        log.info("calSgstTaxAmount  is : " + calSgstTaxAmount);

                        calSgstTaxAmount = Double.valueOf(df.format(calSgstTaxAmount));
                        log.info("calSgstTaxAmount  is : " + calSgstTaxAmount);

                        log.info("parse double fomat : " + Double.parseDouble(df.format(calSgstTaxAmount)));

                        if (Double.parseDouble(df.format(calSgstTaxAmount)) != productSupplierMappingRequest
                                        .getTotalsgstTaxtAmount()) {
                                throw new MismatchedInputException("Wrong S-GST Tax Amount");
                        }

                        Double calCgstTaxAmount = Double
                                        .valueOf(df.format(
                                                        productSupplierMappingRequest.getCgstTaxPercent().doubleValue()
                                                                        / 100));

                        calCgstTaxAmount = Double
                                        .valueOf(df.format(calCgstTaxAmount
                                                        * productSupplierMappingRequest.getBuyingPrice()));
                        log.info("parse double fomat : " + Double.parseDouble(df.format(calCgstTaxAmount)));

                        if (Double.parseDouble(df.format(calCgstTaxAmount)) != productSupplierMappingRequest
                                        .getTotalcgstTaxAmount()) {
                                throw new MismatchedInputException("Wrong C-GST Tax Amount");
                        }

                        Double totalTaxAmount = calCgstTaxAmount + calCgstTaxAmount
                                        + productSupplierMappingRequest.getBuyingPrice();
                        log.info("total tax amount 1 : " + totalTaxAmount);

                        totalTaxAmount = Double.valueOf(df.format(totalTaxAmount));
                        log.info("total tax amount 2 : " + totalTaxAmount);

                        if (Double.parseDouble(df.format(totalTaxAmount)) != productSupplierMappingRequest
                                        .getTaxableAmount()) {
                                throw new MismatchedInputException("Wrong Taxable Amount");
                        }

                }

                ProductSupplierMapping productSupplierMapping = productSupplierMappingMapper
                                .toProductSupplierMapping(productSupplierMappingRequest, product, supplier);
                productSupplierMappingRepository.save(productSupplierMapping);

                return new Response<>(
                                productSupplierMappingRequest,
                                LocalDateTime.now(),
                                "successfully saved",
                                HttpStatus.CREATED);

        }

}
