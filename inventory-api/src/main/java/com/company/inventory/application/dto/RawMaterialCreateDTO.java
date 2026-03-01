package com.company.inventory.application.dto;

import java.math.BigDecimal;

public class RawMaterialCreateDTO {

    private String code;
    private String name;
    private BigDecimal stockQuantity;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(BigDecimal stockQuantity) {
        this.stockQuantity = stockQuantity;
    }
}