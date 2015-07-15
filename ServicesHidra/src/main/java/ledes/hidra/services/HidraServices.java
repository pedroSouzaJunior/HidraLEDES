/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ledes.hidra.services;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBException;
import ledes.hidra.Hidra;

/**
 *
 * @author pedro
 */
@Path("/services")
public class HidraServices {

    private Hidra hidra;

    public HidraServices() {
    }

    public Hidra getHidra() {
        return hidra;
    }

    @GET
    @Produces(MediaType.TEXT_XML)
    @Path("/getcommand")
    public Command getCommand() {

        Command command = new Command();
        command.setName("addAsset");
        command.getParameters().add("asset");
        command.setNumberOfParameters(1);
        return command;
    }

    @GET
    @Path("/init")
    @Produces(MediaType.TEXT_PLAIN)
    public String initialize(String repository) {

        hidra = new Hidra(repository);

        try {
            hidra.startRepository(repository);
        } catch (IOException | JAXBException ex) {
            Logger.getLogger(HidraServices.class.getName()).log(Level.SEVERE, null, ex);
        }

        return "fail";
    }

    @GET
    @Path("/add/{arg1}")
    @Produces(MediaType.TEXT_PLAIN)
    public String add(@PathParam("arg1") String assetName) {

        if (hidra.addAsset(assetName)) {
            return "asset Added";
        }

        return "FAIL";

    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/getlog/{arg1}")
    public String getLog(@PathParam("arg1") String assetName) {

        try {
            return hidra.getLog(assetName);

        } catch (Exception exception) {
            Logger.getLogger(Hidra.class.getName()).log(Level.SEVERE, null, exception);
        }
        return "fail";
    }

}
