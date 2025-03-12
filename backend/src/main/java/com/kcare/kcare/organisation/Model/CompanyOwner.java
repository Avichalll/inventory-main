package com.kcare.kcare.organisation.Model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.kcare.kcare.common.BaseEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "hospitalOwner")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class CompanyOwner extends BaseEntity {

    private String companyOwnerName;
    private String companyName;
    private String address;
    private boolean mainUser; // (if main then true else false)
    private String district;
    private String state;
    private String code;
    private String country;
    private String drugLicenceNumber;
    private String pincode;
    private String gstIn;
    private String phoneNumber;

    @OneToOne
    @JoinColumn(name = "hospitalId")
    private Company hospital;

}
