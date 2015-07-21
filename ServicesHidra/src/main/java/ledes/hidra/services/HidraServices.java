/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ledes.hidra.services;

import com.sun.jersey.core.header.ContentDisposition;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataBodyPart;
import com.sun.jersey.multipart.FormDataMultiPart;
import com.sun.jersey.multipart.FormDataParam;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBException;
import ledes.hidra.Hidra;
import org.primefaces.model.UploadedFile;

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

    /*
     @POST
     @Path("/upload")
     @Consumes(MediaType.MULTIPART_FORM_DATA)
     @Produces(MediaType.TEXT_PLAIN)
     public String uploadFile(@FormDataParam("file") InputStream fis,
     @FormDataParam("file") FormDataContentDisposition fdcd) {
  
     String FOLDER_PATH = "/home/pedro/";
     OutputStream outpuStream = null;
     String fileName = fdcd.getFileName();
     System.out.println("File Name: " + fdcd.getFileName());
     String filePath = FOLDER_PATH + fileName;
         
     try {
     int read = 0;
     byte[] bytes = new byte[1024];
     outpuStream = new FileOutputStream(new File(filePath));
     while ((read = fis.read(bytes)) != -1) {
     outpuStream.write(bytes, 0, read);
     }
     outpuStream.flush();
     outpuStream.close();
     } catch(IOException iox){
     iox.printStackTrace();
     } finally {
     if(outpuStream != null){
     try{outpuStream.close();} catch(Exception ex){}
     }
     }
     return "File Upload Successfully !!";
     }

     */
    /*
     @POST
     @Path("/upload")
     @Consumes(MediaType.MULTIPART_FORM_DATA)
     public Response upload(FormDataMultiPart form, UploadedFile file, String path) {

     String serverLocation = path + File.pathSeparator + file.getFileName();

     FormDataBodyPart filePart = form.getField("file");
     //ContentDisposition headerOfFilePart = filePart.getContentDisposition();
     InputStream fileInputStream = filePart.getValueAs(InputStream.class);
     saveFile(fileInputStream, serverLocation);

     String output = "File saved to server location using FormDataMultiPart : " + serverLocation;
     return Response.status(200).entity(output).build();
     }
     */
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

    private void saveFile(InputStream fileInputStream, String serverLocation) {
        int read = 0;

        try {
            OutputStream outputStream = new FileOutputStream(new File(serverLocation));

            byte[] bytes = new byte[1024];

            while ((read = fileInputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }

            outputStream.flush();
            outputStream.close();
            fileInputStream.close();

        } catch (IOException e) {
            Logger.getLogger(HidraServices.class.getName()).log(Level.SEVERE, null, e);
        }
    }

}
