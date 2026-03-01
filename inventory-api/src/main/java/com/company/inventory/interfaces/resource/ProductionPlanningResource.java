package com.company.inventory.interfaces.resource;

import com.company.inventory.application.dto.GlobalProductionSimulationDTO;
import com.company.inventory.application.dto.ProductionSimulationResponseDTO;
import com.company.inventory.application.service.ProductionPlanningService;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.UUID;

@Path("/production")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductionPlanningResource {

    @Inject
    ProductionPlanningService service;

    @GET
    @Path("/{id}/simulation")
    public ProductionSimulationResponseDTO simulate(
            @PathParam("id") UUID id
    ) {
        return service.simulateProduct(id);
    }
    @GET
    @Path("/simulation")
    public GlobalProductionSimulationDTO simulateGlobal() {
        return service.simulateGlobalProduction();
    }
}