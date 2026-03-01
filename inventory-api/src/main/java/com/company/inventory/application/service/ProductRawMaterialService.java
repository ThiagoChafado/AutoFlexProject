package com.company.inventory.application.service;

import com.company.inventory.application.dto.ProductRawMaterialCreateDTO;
import com.company.inventory.application.dto.ProductRawMaterialResponseDTO;
import com.company.inventory.application.mapper.ProductRawMaterialMapper;
import com.company.inventory.domain.entity.ProductEntity;
import com.company.inventory.domain.entity.ProductRawMaterialEntity;
import com.company.inventory.domain.entity.RawMaterialEntity;
import com.company.inventory.domain.repository.ProductRawMaterialRepository;
import com.company.inventory.domain.repository.ProductRepository;
import com.company.inventory.domain.repository.RawMaterialRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@ApplicationScoped
public class ProductRawMaterialService {

    @Inject
    ProductRawMaterialRepository productRawMaterialRepository;

    @Inject
    ProductRepository productRepository;

    @Inject
    RawMaterialRepository rawMaterialRepository;

    public void addRawMaterial(
            UUID productId,
            ProductRawMaterialCreateDTO dto
    ) {
        ProductEntity product = productRepository
                .findByIdOptional(productId)
                .orElseThrow(() ->
                        new IllegalArgumentException("Product not found")
                );

        RawMaterialEntity rawMaterial = rawMaterialRepository
                .findByIdOptional(dto.getRawMaterialId())
                .orElseThrow(() ->
                        new IllegalArgumentException("Raw material not found")
                );

        ProductRawMaterialEntity entity =
                new ProductRawMaterialEntity();

        entity.setProduct(product);
        entity.setRawMaterial(rawMaterial);
        entity.setRequiredQuantity(dto.getRequiredQuantity());

        productRawMaterialRepository.persist(entity);
    }

    public List<ProductRawMaterialResponseDTO> listByProduct(
            UUID productId
    ) {
        return productRawMaterialRepository
                .list("product.id", productId)
                .stream()
                .map(ProductRawMaterialMapper::toDTO)
                .collect(Collectors.toList());
    }
}