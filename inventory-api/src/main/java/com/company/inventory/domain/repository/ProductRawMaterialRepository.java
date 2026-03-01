package com.company.inventory.domain.repository;

import com.company.inventory.domain.entity.ProductRawMaterialEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class ProductRawMaterialRepository
        implements PanacheRepository<ProductRawMaterialEntity> {

    public List<ProductRawMaterialEntity> findByProductId(UUID productId) {
        return find("product.id", productId).list();
    }

    public boolean exists(UUID productId, UUID rawMaterialId) {
        return count(
            "product.id = ?1 and rawMaterial.id = ?2",
            productId,
            rawMaterialId
        ) > 0;
    }
}