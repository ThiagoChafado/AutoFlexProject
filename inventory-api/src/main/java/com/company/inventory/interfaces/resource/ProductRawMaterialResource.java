package com.company.inventory.interfaces.resource;
import com.company.inventory.application.dto.ProductRawMaterialCreateDTO;
import com.company.inventory.application.dto.ProductRawMaterialResponseDTO;
import com.company.inventory.application.service.ProductRawMaterialService;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.UUID;

@Path("/products/{productId}/raw-materials")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProductRawMaterialResource {

    @Inject
    ProductRawMaterialService service;

    @Transactional
    @POST
    public Response addRawMaterial(
            @PathParam("productId") UUID productId,
            ProductRawMaterialCreateDTO dto
    ) {
        service.addRawMaterial(productId, dto);
        return Response.status(Response.Status.CREATED).build();
    }

    @GET
    public List<ProductRawMaterialResponseDTO> list(
            @PathParam("productId") UUID productId
    ) {
        return service.listByProduct(productId);
    }
}