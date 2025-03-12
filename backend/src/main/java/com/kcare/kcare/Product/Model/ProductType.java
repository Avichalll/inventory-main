package com.kcare.kcare.Product.Model;

import lombok.Getter;

@Getter
public enum ProductType {

    CONSUMEABLE("Consumeable"),
    ASSET("Asset");

    private final String displayType;

    ProductType(String displayType) {
        this.displayType = displayType;
    }

    public String getdisplayCategory() {
        return displayType;
    }

}
