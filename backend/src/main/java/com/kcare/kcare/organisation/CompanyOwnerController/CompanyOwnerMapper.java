package com.kcare.kcare.organisation.CompanyOwnerController;

import org.springframework.stereotype.Service;

import com.kcare.kcare.organisation.Model.Company;
import com.kcare.kcare.organisation.Model.CompanyOwner;

@Service
public class CompanyOwnerMapper {

    public CompanyOwner toCompanyOwner(CompanyOwnerRequest companyOwnerRequest, Company company) {

        return CompanyOwner.builder()
                .companyOwnerName(null)
                .companyName(companyOwnerRequest.getCompanyName())
                .address(companyOwnerRequest.getAddress())
                .state(companyOwnerRequest.getState())
                .code(companyOwnerRequest.getCode())
                .country(companyOwnerRequest.getCountry())
                // .drugLicenceNumber(companyOwnerRequest.getDrugLicenceNumber())
                .pincode(companyOwnerRequest.getPincode())
                .gstIn(companyOwnerRequest.getGstIn())
                .phoneNumber(companyOwnerRequest.getPhoneNumber())
                // .companyName(company);
                .build();

    }

}
