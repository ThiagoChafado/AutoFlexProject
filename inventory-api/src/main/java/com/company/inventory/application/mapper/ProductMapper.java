package com.company.inventory.application.mapper;

import com.company.inventory.application.dto.ProductCreateDTO;
import com.company.inventory.application.dto.ProductResponseDTO;
import com.company.inventory.domain.entity.ProductEntity;

public final class ProductMapper {

    private ProductMapper() {}

    public static ProductEntity toEntity(ProductCreateDTO dto) {
        ProductEntity entity = new ProductEntity();
        entity.setCode(dto.getCode());
        entity.setName(dto.getName());
        entity.setPrice(dto.getPrice());
        return entity;
    }

    public static ProductResponseDTO toDTO(ProductEntity entity) {
        ProductResponseDTO dto = new ProductResponseDTO();
        dto.setId(entity.getId());
        dto.setCode(entity.getCode());
        dto.setName(entity.getName());
        dto.setPrice(entity.getPrice());
        return dto;
    }
}