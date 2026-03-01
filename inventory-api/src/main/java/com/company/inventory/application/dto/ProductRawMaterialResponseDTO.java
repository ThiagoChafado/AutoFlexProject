package com.company.inventory.application.dto;

import java.math.BigDecimal;
import java.util.UUID;

public class ProductRawMaterialResponseDTO {

    private UUID id;
    private UUID rawMaterialId;
    private String rawMaterialName;
    private BigDecimal requiredQuantity;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getRawMaterialId() {
        return rawMaterialId;
    }

    public void setRawMaterialId(UUID rawMaterialId) {
        this.rawMaterialId = rawMaterialId;
    }

    public String getRawMaterialName() {
        return rawMaterialName;
    }

    public void setRawMaterialName(String rawMaterialName) {
        this.rawMaterialName = rawMaterialName;
    }

    public BigDecimal getRequiredQuantity() {
        return requiredQuantity;
    }

    public void setRequiredQuantity(BigDecimal requiredQuantity) {
        this.requiredQuantity = requiredQuantity;
    }
}