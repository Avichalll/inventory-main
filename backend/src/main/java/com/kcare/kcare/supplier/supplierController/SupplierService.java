package com.kcare.kcare.supplier.supplierController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.kcare.kcare.common.PageResponse;
import com.kcare.kcare.common.Response;
import com.kcare.kcare.handler.ResourceNotFoundException;
import com.kcare.kcare.organisation.Model.CompanyOwner;
import com.kcare.kcare.organisation.Model.CompanyOwnerRepository;
import com.kcare.kcare.supplier.model.Supplier;
import com.kcare.kcare.supplier.model.SupplierType;
import com.kcare.kcare.supplier.repository.SupplierRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class SupplierService {

    private final SupplierRepository supplierRepository;
    private final SupplierMapper supplierMapper;
    private final CompanyOwnerRepository companyOwnerRepository;

    public Response<SupplierRequest> saveSupplierDetail(SupplierRequest supplierRequest) {

        Supplier supplier = supplierMapper.toSupplier(supplierRequest);
        // supplierRepository.save(supplier);

        List<CompanyOwner> companyOwner = companyOwnerRepository.findAll();

        String ownerGstIn = companyOwner.get(0).getGstIn();
        if (ownerGstIn == supplierRequest.getGSTIN()) {
            supplier.setSupplierType(SupplierType.INSTATE);
            supplierRepository.save(supplier);
        } else {
            supplier.setSupplierType(SupplierType.OUTSTATE);
            supplierRepository.save(supplier);
        }

        return new Response<>(
                supplierRequest,
                LocalDateTime.now(),
                "Supplier detail saved",
                HttpStatus.CREATED

        );

    }

    public PageResponse<SupplierResponse> getAllSupplier(String supplierName, int page, int size) {
        Specification<Supplier> spec = Specification.where(null);
        if (supplierName != null && !supplierName.isEmpty()) {
            Specification<Supplier> exactMatchSpec = SupplierSpecification.withSupplierName(supplierName);
            Specification<Supplier> startWithSpec = SupplierSpecification.withSupplierNameStartingWith(supplierName);
            Specification<Supplier> containSpec = SupplierSpecification.withSupplierNameStartingWith(supplierName);
            spec = Specification.where(exactMatchSpec).or(startWithSpec).or(containSpec);
        }

        Pageable pageable = PageRequest.of(page, size, Sort.by("supplierName").descending());

        Page<Supplier> pagedSupplier = supplierRepository.findAll(spec, pageable);

        if (pagedSupplier.isEmpty()) {
            throw new ResourceNotFoundException("No Result");
        }

        List<SupplierResponse> supplierResponses = pagedSupplier.stream().map(supplier -> {
            SupplierResponse supplierResponse = supplierMapper.toSupplierResponse(supplier);
            return supplierResponse;
        }).collect(Collectors.toList());

        return new PageResponse<>(
                supplierResponses,
                pagedSupplier.getNumber(),
                pagedSupplier.getSize(),
                pagedSupplier.getTotalElements(),
                pagedSupplier.getTotalPages(),
                pagedSupplier.isFirst(),
                pagedSupplier.isLast()

        );

    }

}
