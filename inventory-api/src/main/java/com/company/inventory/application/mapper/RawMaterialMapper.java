package com.company.inventory.application.mapper;

import com.company.inventory.application.dto.RawMaterialCreateDTO;
import com.company.inventory.application.dto.RawMaterialResponseDTO;
import com.company.inventory.domain.entity.RawMaterialEntity;

public final class RawMaterialMapper {

    private RawMaterialMapper() {}

    public static RawMaterialEntity toEntity(RawMaterialCreateDTO dto) {
        RawMaterialEntity entity = new RawMaterialEntity();
        entity.setCode(dto.getCode());
        entity.setName(dto.getName());
        entity.setStockQuantity(dto.getStockQuantity());
        return entity;
    }

    public static RawMaterialResponseDTO toDTO(RawMaterialEntity entity) {
        RawMaterialResponseDTO dto = new RawMaterialResponseDTO();
        dto.setId(entity.getId());
        dto.setCode(entity.getCode());
        dto.setName(entity.getName());
        dto.setStockQuantity(entity.getStockQuantity());
        return dto;
    }
}