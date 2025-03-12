package com.kcare.kcare.image.Model;

import com.kcare.kcare.Product.Model.Product;
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
@Table(name = "imageDetails")

public class ImageDetails extends BaseEntity {

    private String imagePath;
    private String imageTag;

    @ManyToOne
    @JoinColumn(name = "productId")
    private Product product;

}
