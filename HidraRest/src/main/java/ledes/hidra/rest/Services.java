package ledes.hidra.rest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBException;
import ledes.hidra.Hidra;
import ledes.hidra.rest.model.Command;

/**
 *
 * @author pedro e danielli
 */
@Path("/services")
public class Services {

    private Hidra hidra;

    public Hidra getHidra() {
        return hidra;
    }

    @POST
    @Path("/construct")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public Response construct(Command command) {

        if (command.getDestiny() != null) {

            hidra = new Hidra(command.getDestiny());

            try {
                hidra.startRepository(command.getDestiny());
            } catch (IOException ex) {
                Logger.getLogger(Services.class.getName()).log(Level.SEVERE, null, ex);
            } catch (JAXBException ex) {
                Logger.getLogger(Services.class.getName()).log(Level.SEVERE, null, ex);
            }

            return Response
                    .status(201)
                    .entity("repository was created in : " + command.getDestiny()).build();
        }
        return Response.status(500).entity("Error in server").build();
    }

    @POST
    @Path("/insert")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public Response insertAsset(Command command) {

        String uploadedFileLocation = command.getDestiny() + File.separator + command.getAssetFile().getName();
        InputStream in;
        int read = 0;
        byte[] bytes = new byte[1024];

        if (command.getAssetFile() != null && command.getDestiny() != null) {
            try {
                OutputStream out = new FileOutputStream(new File(uploadedFileLocation));
                in = new FileInputStream(command.getAssetFile());
                try {
                    while ((read = in.read(bytes)) != -1) {
                        out.write(bytes, 0, read);
                    }
                    out.flush();
                    out.close();
                } catch (IOException ex) {
                    Logger.getLogger(Services.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Services.class.getName()).log(Level.SEVERE, null, ex);
            }

            return Response.status(201).entity("File uploade successfully in: " + command.getDestiny()).build();
        }
        return Response.status(500).entity("Error in server").build();
    }

    /**
     * *************************************************************************
     * a partir daqui sao metodos de teste, e coisinhas que eu estava estudando
     * daqui pra cima sao os metodos que ja estao funcionando.
     *
     *
     * *************************************************************************
     */
    @GET
    @Path("gettest")
    @Produces(MediaType.APPLICATION_XML)
    public Command getCommand() {

        Command com = new Command("/var/www/hidra.com/hidra/danielli");
        com.setAssetFile(new File("/home/pedro/Documentos/qsort.c"));
        return com;
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
