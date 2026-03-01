package com.company.inventory.application.dto;

import java.math.BigDecimal;
import java.util.List;

public class GlobalProductionSimulationDTO {

    private List<ProductionSimulationResponseDTO> products;
    private BigDecimal totalProductionValue;

    public List<ProductionSimulationResponseDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductionSimulationResponseDTO> products) {
        this.products = products;
    }

    public BigDecimal getTotalProductionValue() {
        return totalProductionValue;
    }

    public void setTotalProductionValue(BigDecimal totalProductionValue) {
        this.totalProductionValue = totalProductionValue;
    }
}