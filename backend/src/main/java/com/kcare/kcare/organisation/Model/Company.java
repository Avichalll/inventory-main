package com.kcare.kcare.organisation.Model;

import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.kcare.kcare.common.BaseEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "hospitalDetail")
@SuperBuilder
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Company extends BaseEntity {

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

    @OneToOne(mappedBy = "hospital", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private CompanyOwner hospitalOwner;

}
