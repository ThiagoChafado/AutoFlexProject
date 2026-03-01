package com.company.inventory.interfaces.resource;

import com.company.inventory.application.dto.ProductRawMaterialCreateDTO;
import com.company.inventory.application.dto.ProductRawMaterialResponseDTO;
import com.company.inventory.application.service.ProductCompositionService;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

import java.util.UUID;

@Path("/production")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductCompositionResource {

    @Inject
    ProductCompositionService service;

    @POST
    @Path("/{id}/raw-materials")
    public void attachRawMaterial(
            @PathParam("id") UUID productId,
            ProductRawMaterialCreateDTO dto
    ) {
        service.attachRawMaterial(productId, dto);
    }

    @GET
    @Path("/{id}/raw-materials")
    public List<ProductRawMaterialResponseDTO> listRawMaterials(
            @PathParam("id") UUID productId
    ) {
        return service.listRawMaterials(productId);
    }
}