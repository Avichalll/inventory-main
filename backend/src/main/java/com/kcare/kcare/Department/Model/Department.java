package com.kcare.kcare.Department.Model;

import java.time.LocalDate;

import com.kcare.kcare.common.BaseEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
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
@Table(name = "department")

public class Department extends BaseEntity {

    private String image; // ! remove this department
    @NotBlank(message = "DepartmentName should not be blank")
    private String departmentName;
    private String description;
    private String departmentMail;
    private String contactNumber;
    private String Location;
    private String departmentHead;
    private String departmentHeadContactNumber;
    @Email(message = "Please provide valid email address")
    private String departmentHeadMail;
    private LocalDate activeSinceDate;

}
