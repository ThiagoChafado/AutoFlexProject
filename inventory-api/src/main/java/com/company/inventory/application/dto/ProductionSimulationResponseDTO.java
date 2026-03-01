package com.company.inventory.application.dto;

import java.math.BigDecimal;
import java.util.UUID;

public class ProductionSimulationResponseDTO {

    private UUID productId;
    private String productName;
    private BigDecimal productPrice;
    private long producibleQuantity;
    private BigDecimal totalValue;

    public UUID getProductId() {
        return productId;
    }

    public void setProductId(UUID productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public long getProducibleQuantity() {
        return producibleQuantity;
    }

    public void setProducibleQuantity(long producibleQuantity) {
        this.producibleQuantity = producibleQuantity;
    }

    public BigDecimal getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(BigDecimal totalValue) {
        this.totalValue = totalValue;
    }
}