package com.kcare.kcare.Product.controller.ProductIssueController;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.kcare.kcare.Product.Model.Product;
import com.kcare.kcare.Product.Model.ProductIssueReport;
import com.kcare.kcare.Product.repository.ProductIssueReportRepository;
import com.kcare.kcare.Product.repository.ProductRepository;
import com.kcare.kcare.common.PageResponse;
import com.kcare.kcare.common.Response;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductIssueReportService {

        private final ProductRepository productRepository;
        private final ProductIssueReportRepository productIssueReportRepository;
        private final ProductIssueReportMapper productIssueReportMapper;
        private final KafkaJsonProductIssuePub kafkaJsonProductIssuePub;

        public Response<ProductIssueReportRequest> saveProductIssueReport(Integer productId,
                        ProductIssueReportRequest productIssueReportRequest) {
                Product product = productRepository.findById(productId)
                                .orElseThrow(() -> new EntityNotFoundException(
                                                "product not Available with id : " + productId));
                ProductIssueReport productIssueReport = productIssueReportMapper
                                .toProductIssueReport(productIssueReportRequest, product);
                ProductIssueReport savedProductIssueReport = productIssueReportRepository.save(productIssueReport);
                KafkaProductIssueResponse kafkaProductIssueResponse = productIssueReportMapper
                                .toKafakProductIssueResposne(savedProductIssueReport, product);
                kafkaJsonProductIssuePub.sendMessage(kafkaProductIssueResponse);
                return new Response<>(
                                productIssueReportRequest,
                                LocalDateTime.now(),
                                "Product isssue saved",
                                HttpStatus.CREATED);
        }

        public PageResponse<ProductIssueResponse> getAllProductIssueReport(Integer productId, int page,
                        int size) {

                Product product = productRepository.findById(productId).orElseThrow(
                                () -> new EntityNotFoundException("product not availabe with Id : " + productId));

                log.info("start here");
                Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
                log.info("step 2");

                Page<ProductIssueReport> pagedProductIssues = productIssueReportRepository.findByProductId(productId,
                                pageable);
                // Page<ProductIssueReport> pagedProductIssues =
                // productIssueReportRepository.findAllByProductId(productId,
                // pageable);
                log.info("step 3");
                if (pagedProductIssues.isEmpty()) {
                        return new PageResponse<>(
                                        Collections.emptyList(),
                                        page,
                                        size,
                                        0,
                                        0,
                                        true,
                                        true);
                }
                log.info("i succesffuly data feteched ");

                List<ProductIssueResponse> productIssueResponses = pagedProductIssues.stream()
                                .map(productIssue -> productIssueReportMapper.toProductIssueResponse(productIssue,
                                                product))
                                .collect(Collectors.toList());

                log.info("sucesfully mapped data");

                return new PageResponse<>(
                                productIssueResponses,
                                pagedProductIssues.getNumber(),
                                pagedProductIssues.getSize(),
                                pagedProductIssues.getTotalElements(),
                                pagedProductIssues.getTotalPages(),
                                pagedProductIssues.isFirst(),
                                pagedProductIssues.isLast()

                );

        }

}
