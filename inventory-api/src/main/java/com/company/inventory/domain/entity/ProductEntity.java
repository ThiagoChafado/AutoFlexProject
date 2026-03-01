package com.company.inventory.domain.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;
import java.util.List;

@Entity
@Table(name = "product")


public class ProductEntity extends PanacheEntityBase {
    
    @Id
    @GeneratedValue
    @Column(columnDefinition = "uuid")
    private UUID id;

    @Column(nullable = false, unique = true, length = 50)
    private String code;

    @Column(nullable = false,unique = true, length = 255)
    private String name;

    @Column(nullable =  false,precision = 15, scale = 2)
    private BigDecimal price; // maybe string???

    @OneToMany(
        mappedBy = "product",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private List<ProductRawMaterialEntity> rawMaterials;


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public List<ProductRawMaterialEntity> getRawMaterials() {
        return rawMaterials;
    }
}


