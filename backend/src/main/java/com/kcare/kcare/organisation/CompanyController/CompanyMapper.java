package com.kcare.kcare.organisation.CompanyController;

import org.springframework.stereotype.Service;

import com.kcare.kcare.organisation.Model.Company;

@Service
public class CompanyMapper {

    public Company toHospital(CompanyRequest companyRequest) {

        return Company.builder()
                .companyName(companyRequest.getCompanyName())
                .contactNumber(companyRequest.getContactNumber())
                .email(companyRequest.getEmail())
                .address(companyRequest.getAddress())
                .city(companyRequest.getCity())
                .state(companyRequest.getState())
                .pinCode(companyRequest.getPinCode())
                .registrationNumber(companyRequest.getRegistrationNumber())
                .licenseNumber(companyRequest.getLicenseNumber())
                .openingTime(companyRequest.getOpeningTime())
                .closingTime(companyRequest.getClosingTime())
                .is24x7(companyRequest.getIs24x7())
                .numberOfBeds(companyRequest.getNumberOfBeds())
                .numberOfDepartments(companyRequest.getNumberOfDepartments())
                .numberOfDoctors(companyRequest.getNumberOfDoctors())
                .hasAmbulanceService(companyRequest.getHasAmbulanceService())
                .hasICU(companyRequest.getHasICU())
                .hasEmergencyServices(companyRequest.getHasEmergencyServices())
                .build();

    }

}
