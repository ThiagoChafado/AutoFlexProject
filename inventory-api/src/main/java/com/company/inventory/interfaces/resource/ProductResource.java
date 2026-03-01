package com.company.inventory.interfaces.resource;
import com.company.inventory.application.dto.ProductCreateDTO;
import com.company.inventory.application.dto.ProductResponseDTO;
import com.company.inventory.application.service.ProductService;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.UUID;

@Path("/products")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProductResource {

    @Inject
    ProductService service;

    @Transactional
    @POST
    public Response create(ProductCreateDTO dto) {
        ProductResponseDTO created = service.create(dto);
        return Response.status(Response.Status.CREATED)
                .entity(created)
                .build();
    }

    @GET
    public List<ProductResponseDTO> list() {
        return service.listAll();
    }

    @GET
    @Path("/{id}")
    public ProductResponseDTO getById(@PathParam("id") UUID id) {
        return service.findById(id);
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") UUID id) {
        service.delete(id);
    }
}