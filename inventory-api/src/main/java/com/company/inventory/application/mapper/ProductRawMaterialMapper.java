package com.company.inventory.application.mapper;

import com.company.inventory.application.dto.ProductRawMaterialResponseDTO;
import com.company.inventory.domain.entity.ProductRawMaterialEntity;

public final class ProductRawMaterialMapper {

    private ProductRawMaterialMapper() {}

    public static ProductRawMaterialResponseDTO toDTO(
            ProductRawMaterialEntity entity
    ) {
        ProductRawMaterialResponseDTO dto =
                new ProductRawMaterialResponseDTO();

        dto.setId(entity.getId());
        dto.setRawMaterialId(entity.getRawMaterial().getId());
        dto.setRawMaterialName(entity.getRawMaterial().getName());
        dto.setRequiredQuantity(entity.getRequiredQuantity());

        return dto;
    }
}