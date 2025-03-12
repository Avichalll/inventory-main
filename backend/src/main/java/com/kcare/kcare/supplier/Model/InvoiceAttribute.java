package com.kcare.kcare.supplier.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.kcare.kcare.common.BaseEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "invoiceAttribute")

public class InvoiceAttribute extends BaseEntity {

    private String attributeName;
    private String attributeValue;

    @ManyToOne
    @JoinColumn(name = "invoiceId")
    @JsonBackReference("invoice-InvoiceAttribute")
    private Invoice invoice;

}
