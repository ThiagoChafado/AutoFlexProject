package com.company.inventory.domain.repository;

import com.company.inventory.domain.entity.RawMaterialEntity;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped

public class RawMaterialRepository implements PanacheRepositoryBase<RawMaterialEntity, java.util.UUID> {
    
}
