package com.company.inventory.interfaces.resource;
import com.company.inventory.application.dto.RawMaterialCreateDTO;
import com.company.inventory.application.dto.RawMaterialResponseDTO;
import com.company.inventory.application.service.RawMaterialService;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.UUID;

@Path("/raw-materials")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RawMaterialResource {

    @Inject
    RawMaterialService service;

    @Transactional
    @POST
    public Response create(RawMaterialCreateDTO dto) {
        RawMaterialResponseDTO created = service.create(dto);
        return Response.status(Response.Status.CREATED)
                .entity(created)
                .build();
    }

    @GET
    public List<RawMaterialResponseDTO> list() {
        return service.listAll();
    }

    @GET
    @Path("/{id}")
    public RawMaterialResponseDTO getById(@PathParam("id") UUID id) {
        return service.findById(id);
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") UUID id) {
        service.delete(id);
    }
}