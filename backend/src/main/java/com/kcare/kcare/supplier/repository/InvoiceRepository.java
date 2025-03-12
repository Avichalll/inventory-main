package com.kcare.kcare.supplier.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kcare.kcare.supplier.model.Invoice;

public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {

}
