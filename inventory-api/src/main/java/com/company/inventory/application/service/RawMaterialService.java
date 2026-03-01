package com.company.inventory.application.service;

import com.company.inventory.application.dto.RawMaterialCreateDTO;
import com.company.inventory.application.dto.RawMaterialResponseDTO;
import com.company.inventory.application.mapper.RawMaterialMapper;
import com.company.inventory.domain.entity.RawMaterialEntity;
import com.company.inventory.domain.repository.RawMaterialRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@ApplicationScoped
public class RawMaterialService {

    @Inject
    RawMaterialRepository rawMaterialRepository;

    public RawMaterialResponseDTO create(RawMaterialCreateDTO dto) {
        RawMaterialEntity entity = RawMaterialMapper.toEntity(dto);
        rawMaterialRepository.persist(entity);
        return RawMaterialMapper.toDTO(entity);
    }

    public List<RawMaterialResponseDTO> listAll() {
        return rawMaterialRepository.findAll().stream()
                .map(RawMaterialMapper::toDTO)
                .collect(Collectors.toList());
    }

    public RawMaterialResponseDTO findById(UUID id) {
        RawMaterialEntity entity = rawMaterialRepository.findByIdOptional(id)
                .orElseThrow(() -> new IllegalArgumentException("Raw material not found"));
        return RawMaterialMapper.toDTO(entity);
    }

    public void delete(UUID id) {
        rawMaterialRepository.deleteById(id);
    }
}