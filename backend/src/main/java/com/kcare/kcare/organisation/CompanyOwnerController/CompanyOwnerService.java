package com.kcare.kcare.organisation.CompanyOwnerController;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.kcare.kcare.common.Response;
import com.kcare.kcare.organisation.Model.Company;
import com.kcare.kcare.organisation.Model.CompanyOwner;
import com.kcare.kcare.organisation.Model.CompanyOwnerRepository;
import com.kcare.kcare.organisation.Model.CompanyRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class CompanyOwnerService {

    private final CompanyOwnerRepository companyOwnerRepository;
    private final CompanyRepository companyRepository;
    private final CompanyOwnerMapper companyOwnerMapper;

    public Response<CompanyOwnerRequest> saveCompanyOwnerDetail(CompanyOwnerRequest companyOwnerRequest) {

        Company company = companyRepository.findById(companyOwnerRequest.getCompanyId())
                .orElseThrow(() -> new EntityNotFoundException("company Detail Not available"));

        CompanyOwner companyOwner = companyOwnerMapper.toCompanyOwner(companyOwnerRequest, company);

        companyOwnerRepository.save(companyOwner);

        return new Response<>(
                companyOwnerRequest,
                LocalDateTime.now(),
                "Detail saved successfully",
                HttpStatus.CREATED);

    }

}
