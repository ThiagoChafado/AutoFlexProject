package com.company.inventory.application.service;

import com.company.inventory.application.dto.ProductRawMaterialCreateDTO;
import com.company.inventory.application.dto.ProductRawMaterialResponseDTO;
import com.company.inventory.domain.entity.ProductEntity;
import com.company.inventory.domain.entity.ProductRawMaterialEntity;
import com.company.inventory.domain.entity.RawMaterialEntity;
import com.company.inventory.domain.repository.ProductRawMaterialRepository;
import com.company.inventory.domain.repository.ProductRepository;
import com.company.inventory.domain.repository.RawMaterialRepository;
import java.util.List;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.WebApplicationException;

import java.util.UUID;

@ApplicationScoped
public class ProductCompositionService {

    @Inject
    ProductRepository productRepository;

    @Inject
    RawMaterialRepository rawMaterialRepository;

    @Inject
    ProductRawMaterialRepository productRawMaterialRepository;

    @Transactional
    public void attachRawMaterial(
            UUID productId,
            ProductRawMaterialCreateDTO dto
    ) {

        ProductEntity product = productRepository.findById(productId);
        if (product == null) {
            throw new NotFoundException("Product not found");
        }

        RawMaterialEntity rawMaterial =
                rawMaterialRepository.findById(dto.getRawMaterialId());

        if (rawMaterial == null) {
            throw new NotFoundException("Raw material not found");
        }

        if (dto.getRequiredQuantity() == null
                || dto.getRequiredQuantity().signum() <= 0) {
            throw new WebApplicationException(
                    "Required quantity must be greater than zero",
                    400
            );
        }

        ProductRawMaterialEntity relation =
                new ProductRawMaterialEntity();

        relation.setProduct(product);
        relation.setRawMaterial(rawMaterial);
        relation.setRequiredQuantity(dto.getRequiredQuantity());

        productRawMaterialRepository.persist(relation);
    }
    
    public List<ProductRawMaterialResponseDTO> listRawMaterials(UUID productId) {

    List<ProductRawMaterialEntity> relations =
            productRawMaterialRepository.list("product.id", productId);

    return relations.stream().map(rel -> {
        ProductRawMaterialResponseDTO dto =
                new ProductRawMaterialResponseDTO();

        dto.setId(rel.getId());
        dto.setRawMaterialId(rel.getRawMaterial().getId());
        dto.setRawMaterialName(rel.getRawMaterial().getName());
        dto.setRequiredQuantity(rel.getRequiredQuantity());

        return dto;
    }).toList();
}
}