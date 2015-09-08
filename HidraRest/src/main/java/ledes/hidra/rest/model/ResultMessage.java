/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ledes.hidra.rest.model;

import javax.ws.rs.core.Response.Status;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author pedro
 */
@XmlRootElement
public class ResultMessage {

    private Status statusMessage;
    private String message;

    private String log;

    public ResultMessage() {
    }

    public ResultMessage(Status statusMessage, String message, String log) {
        this.statusMessage = statusMessage;
        this.message = message;
        this.log = log;
    }

    public Status getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(Status statusMessage) {
        this.statusMessage = statusMessage;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }

    

}
