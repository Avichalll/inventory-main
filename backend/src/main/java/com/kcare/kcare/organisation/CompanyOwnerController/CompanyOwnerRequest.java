package com.kcare.kcare.organisation.CompanyOwnerController;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder

public class CompanyOwnerRequest {

    private Integer companyId;
    private String comapanyOwnerName;
    private String companyName;
    private String address;
    private String state;
    private String code;
    private String country;
    // private String drugLicenceNumber;
    private String pincode;
    private String gstIn;
    private String phoneNumber;

}
