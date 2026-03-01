package com.company.inventory.domain.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(
    name = "product_raw_material",
    uniqueConstraints = {
        @UniqueConstraint(
            columnNames = {"product_id", "raw_material_id"}
        )
    }
)
public class ProductRawMaterialEntity extends PanacheEntityBase {

     @Id
    @GeneratedValue
    @Column(columnDefinition = "uuid")
    private UUID id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    @ManyToOne(optional = false)
    @JoinColumn(name = "raw_material_id")
    private RawMaterialEntity rawMaterial;

    @Column(
    name = "required_quantity",
    nullable = false,
    precision = 15,
    scale = 2
)
private BigDecimal requiredQuantity;



    public UUID getId() {
        return id;
    }
    
    public void setId(UUID id) {
        this.id = id;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }

    public RawMaterialEntity getRawMaterial() {
        return rawMaterial;
    }

    public void setRawMaterial(RawMaterialEntity rawMaterial) {
        this.rawMaterial = rawMaterial;
    }

    public BigDecimal getRequiredQuantity() {
        return requiredQuantity;
    }

    public void setRequiredQuantity(BigDecimal requiredQuantity) {
        this.requiredQuantity = requiredQuantity;
    }


}