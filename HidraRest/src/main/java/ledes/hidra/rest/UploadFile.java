/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ledes.hidra.services;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.ws.rs.Consumes;

import javax.ws.rs.POST;

import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import ledes.hidra.Hidra;

/**
 *
 * @author danielli
 */
@Path("/upload")
public class UploadFile {

    @POST
    @Path("{file}")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public String upload(@FormDataParam("file") InputStream uploadedFileInputStream,
            @FormDataParam("file") FormDataContentDisposition fileDetail,
            @FormDataParam("path") String repository) {

        saveToDisk(uploadedFileInputStream, fileDetail, repository);
        enableMonitoring(fileDetail, repository);

        return "File Uploaded sucessfully in: " + repository;
    }

    private void saveToDisk(InputStream uploadedInputStream, FormDataContentDisposition fileDetail,
            String repository) {

        String uploadedFileLocation = "/var/www/hidra.com/hidra/" + repository + "/" + fileDetail.getFileName();

        try {

            OutputStream out = new FileOutputStream(new File(uploadedFileLocation));
            int read = 0;
            byte[] bytes = new byte[1024];
            while ((read = uploadedInputStream.read(bytes)) != -1) {

                out.write(bytes, 0, read);

            }
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    protected String enableMonitoring(FormDataContentDisposition fileDetail, String repository) {

        Hidra hidra = new Hidra("/var/www/hidra.com/hidra/" + repository);

        hidra.addAsset(fileDetail.getFileName());

        return "check your repository";
    }

}
