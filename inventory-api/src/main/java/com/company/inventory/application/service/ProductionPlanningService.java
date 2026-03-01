package com.company.inventory.application.service;

import com.company.inventory.application.dto.GlobalProductionSimulationDTO;
import com.company.inventory.application.dto.ProductionSimulationResponseDTO;
import com.company.inventory.domain.entity.ProductEntity;
import com.company.inventory.domain.entity.ProductRawMaterialEntity;
import com.company.inventory.domain.repository.ProductRawMaterialRepository;
import com.company.inventory.domain.repository.ProductRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.WebApplicationException;

import java.util.Map;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class ProductionPlanningService {

    @Inject
    ProductRepository productRepository;

    @Inject
    ProductRawMaterialRepository productRawMaterialRepository;

    public ProductionSimulationResponseDTO simulateProduct(UUID productId) {

        ProductEntity product = productRepository.findById(productId);

        if (product == null) {
            throw new NotFoundException("Product not found");
        }

        List<ProductRawMaterialEntity> relations =
                productRawMaterialRepository.list("product.id", productId);

        if (relations.isEmpty()) {
            throw new WebApplicationException(
                    "Product has no raw materials linked",
                    400
            );
        }

        long producibleQuantity = calculateMaxQuantity(relations);

        ProductionSimulationResponseDTO dto =
                new ProductionSimulationResponseDTO();

        dto.setProductId(product.getId());
        dto.setProductName(product.getName());
        dto.setProductPrice(product.getPrice());
        dto.setProducibleQuantity(producibleQuantity);
        dto.setTotalValue(
                product.getPrice()
                        .multiply(BigDecimal.valueOf(producibleQuantity))
        );

        return dto;
    }

    private long calculateMaxQuantity(
            List<ProductRawMaterialEntity> relations
    ) {
        return relations.stream()
                .mapToLong(prm -> {

                    BigDecimal available =
                            prm.getRawMaterial().getStockQuantity();

                    BigDecimal required =
                            prm.getRequiredQuantity();

                    return available
                            .divide(required, 0, RoundingMode.DOWN)
                            .longValue();
                })
                .min()
                .orElse(0);
    }

    // TOTAL

    public GlobalProductionSimulationDTO simulateGlobalProduction() {

    List<ProductEntity> products =
            productRepository.list("order by price desc");

    List<ProductRawMaterialEntity> relations =
            productRawMaterialRepository.findAll().list();

    Map<UUID, BigDecimal> stock = new HashMap<>();

    for (ProductRawMaterialEntity prm : relations) {
        stock.put(
                prm.getRawMaterial().getId(),
                prm.getRawMaterial().getStockQuantity()
        );
    }

    List<ProductionSimulationResponseDTO> result = new ArrayList<>();

    BigDecimal totalValue = BigDecimal.ZERO;

    for (ProductEntity product : products) {

        List<ProductRawMaterialEntity> productRelations =
                relations.stream()
                        .filter(r -> r.getProduct().getId().equals(product.getId()))
                        .toList();

        if (productRelations.isEmpty()) continue;

        long quantity = calculateMaxQuantityTotal(productRelations, stock);

        if (quantity <= 0) continue;

        consumeStock(productRelations, stock, quantity);

        ProductionSimulationResponseDTO dto =
                new ProductionSimulationResponseDTO();

        dto.setProductId(product.getId());
        dto.setProductName(product.getName());
        dto.setProductPrice(product.getPrice());
        dto.setProducibleQuantity(quantity);

        BigDecimal productValue =
                product.getPrice().multiply(BigDecimal.valueOf(quantity));

        dto.setTotalValue(productValue);

        totalValue = totalValue.add(productValue);

        result.add(dto);
    }

    GlobalProductionSimulationDTO global =
            new GlobalProductionSimulationDTO();

    global.setProducts(result);
    global.setTotalProductionValue(totalValue);

    return global;
}

        private long calculateMaxQuantityTotal(
                List<ProductRawMaterialEntity> relations,
                Map<UUID, BigDecimal> stock
        ) {
        return relations.stream()
                .mapToLong(prm -> {

                        BigDecimal available =
                                stock.get(prm.getRawMaterial().getId());

                        return available
                                .divide(prm.getRequiredQuantity(), 0, RoundingMode.DOWN)
                                .longValue();
                })
                .min()
                .orElse(0);
        }


        private void consumeStock(
        List<ProductRawMaterialEntity> relations,
        Map<UUID, BigDecimal> stock,
        long quantity
) {
    for (ProductRawMaterialEntity prm : relations) {

        UUID rawMaterialId = prm.getRawMaterial().getId();

        BigDecimal used =
                prm.getRequiredQuantity()
                        .multiply(BigDecimal.valueOf(quantity));

        stock.put(
                rawMaterialId,
                stock.get(rawMaterialId).subtract(used)
        );
    }
}
}