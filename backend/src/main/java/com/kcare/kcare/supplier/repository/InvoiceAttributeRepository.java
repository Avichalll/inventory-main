package com.kcare.kcare.supplier.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kcare.kcare.supplier.model.InvoiceAttribute;

public interface InvoiceAttributeRepository extends JpaRepository<InvoiceAttribute, Integer> {

}
