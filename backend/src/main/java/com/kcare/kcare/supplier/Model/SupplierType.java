package com.kcare.kcare.supplier.model;

import lombok.Getter;

@Getter

public enum SupplierType {

    INSTATE("Instate"),
    OUTSTATE("Outstate");

    private final String displayName;

    SupplierType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

}
