/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ledes.hidra.services;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBException;
import ledes.hidra.Hidra;

/**
 *
 * @author pedro
 */
@Path("/services")
public class Services {

    private Hidra hidra;

    public Hidra getHidra() {
        return hidra;
    }

    @GET
    @Path("/teste")
    @Produces(MediaType.TEXT_PLAIN)
    public Response teste() {

        String sucess = "Tudo Celto.";

        return Response.ok(sucess).build();
    }

    @GET
    @Path("/construct")
    public Response construct(@Context UriInfo info) {

        String path = info.getQueryParameters().getFirst("path");

        if (path != null) {

            hidra = new Hidra(path);

            try {
                hidra.startRepository(path);
            } catch (IOException ex) {
                Logger.getLogger(Services.class.getName()).log(Level.SEVERE, null, ex);
            } catch (JAXBException ex) {
                Logger.getLogger(Services.class.getName()).log(Level.SEVERE, null, ex);
            }

            return Response
                    .status(200)
                    .entity("repository was created in : " + path).build();
        }
        return Response.status(500).entity("Error in server").build();
    }

    @GET
    @Path("/query")
    @Produces(MediaType.APPLICATION_XML)
    public Response getUsers(
            @DefaultValue("1000") @QueryParam("from") int from,
            @DefaultValue("999") @QueryParam("to") int to,
            @DefaultValue("name") @QueryParam("orderBy") List<String> orderBy) {

        return Response
                .status(200)
                .entity("getUsers is called, from : " + from + ", to : " + to
                        + ", orderBy" + orderBy.toString()).build();

    }
}
