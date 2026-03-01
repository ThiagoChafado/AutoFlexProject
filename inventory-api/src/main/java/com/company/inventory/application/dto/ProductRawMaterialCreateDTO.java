package com.company.inventory.application.dto;

import java.math.BigDecimal;
import java.util.UUID;

public class ProductRawMaterialCreateDTO {

    private UUID rawMaterialId;
    private BigDecimal requiredQuantity;

    public UUID getRawMaterialId() {
        return rawMaterialId;
    }

    public void setRawMaterialId(UUID rawMaterialId) {
        this.rawMaterialId = rawMaterialId;
    }

    public BigDecimal getRequiredQuantity() {
        return requiredQuantity;
    }

    public void setRequiredQuantity(BigDecimal requiredQuantity) {
        this.requiredQuantity = requiredQuantity;
    }
}