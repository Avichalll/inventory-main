package com.kcare.kcare.Product.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kcare.kcare.Product.Model.ProductIssueReport;

public interface ProductIssueReportRepository extends JpaRepository<ProductIssueReport, Integer> {

    @Query("SELECT p FROM ProductIssueReport p WHERE p.product.id = :productId")
    Page<ProductIssueReport> findByProductId(@Param("productId") Integer productId, Pageable pageable);

}
