package com.company.inventory.domain.repository;

import com.company.inventory.domain.entity.ProductEntity;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProductRepository implements PanacheRepositoryBase<ProductEntity, java.util.UUID> {
    
}
