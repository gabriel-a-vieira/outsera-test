package org.example.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import org.example.service.ProducerService;

@Path("/producers")
public class ProducerResource {

    @Inject
    ProducerService producerService;

    @GET
    @Path("/load")
    public Response loadData() {

        try {

            producerService.loadCsvData();
            return Response.ok().build();

        } catch (Exception e) {
            return Response.serverError().build();
        }

    }

}
