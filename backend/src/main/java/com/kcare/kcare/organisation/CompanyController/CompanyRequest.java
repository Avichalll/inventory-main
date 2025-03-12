package com.kcare.kcare.organisation.CompanyController;

import java.time.LocalTime;

import jakarta.validation.constraints.Email;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder

public class CompanyRequest {

    private String companyName;
    private String contactNumber;
    @Email
    private String email;

    // Location
    private String address;
    private String city;
    private String state;
    private String pinCode;

    // Registration Details
    private String registrationNumber;
    private String licenseNumber;

    // Operational Details
    private LocalTime openingTime;
    private LocalTime closingTime;
    private Boolean is24x7;

    // Facility Information
    private Integer numberOfBeds;
    private Integer numberOfDepartments;
    private Integer numberOfDoctors;
    private Boolean hasEmergencyServices;
    private Boolean hasICU;
    private Boolean hasAmbulanceService;

}
