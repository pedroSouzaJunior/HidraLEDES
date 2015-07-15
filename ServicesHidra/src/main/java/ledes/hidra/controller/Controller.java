/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ledes.hidra.controller;

import java.io.Serializable;
import ledes.hidra.services.HidraServices;
import org.primefaces.model.UploadedFile;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.primefaces.event.FileUploadEvent;

/**
 *
 * @author pedro
 */
@ManagedBean
public class Controller implements Serializable {

    private static final long serialVerionUID = 1L;

    private String path;
    private UploadedFile file;

    public Controller() {
    }

    public void initializeRepo() {

        HidraServices services = new HidraServices();
        String result = services.initialize(path);
        System.out.println(result);
    }

    public void upload() {
        if (file != null) {
            FacesMessage message = new FacesMessage("Succesful", file.getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

}
