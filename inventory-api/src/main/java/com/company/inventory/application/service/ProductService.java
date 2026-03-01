package com.company.inventory.application.service;

import com.company.inventory.application.dto.ProductCreateDTO;
import com.company.inventory.application.dto.ProductResponseDTO;
import com.company.inventory.application.mapper.ProductMapper;
import com.company.inventory.domain.entity.ProductEntity;
import com.company.inventory.domain.repository.ProductRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@ApplicationScoped
public class ProductService {

    @Inject
    ProductRepository productRepository;

    public ProductResponseDTO create(ProductCreateDTO dto) {
        ProductEntity entity = ProductMapper.toEntity(dto);
        productRepository.persist(entity);
        return ProductMapper.toDTO(entity);
    }

    public List<ProductResponseDTO> listAll() {
        return productRepository.findAll().stream()
                .map(ProductMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ProductResponseDTO findById(UUID id) {
        ProductEntity entity = productRepository.findByIdOptional(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));
        return ProductMapper.toDTO(entity);
    }

    public void delete(UUID id) {
        productRepository.deleteById(id);
    }
}