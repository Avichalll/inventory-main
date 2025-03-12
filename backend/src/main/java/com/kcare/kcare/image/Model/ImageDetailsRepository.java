package com.kcare.kcare.image.Model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageDetailsRepository extends JpaRepository<ImageDetails, Integer> {

    List<ImageDetails> findAllByProductId(Integer productId);
}
