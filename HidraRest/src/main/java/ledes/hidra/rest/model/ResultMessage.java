/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ledes.hidra.rest.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author pedro
 */
@XmlRootElement
public class ResultMessage {

    private int statusMessage;
    private String message;

    private String log;

    public ResultMessage() {
    }

    public ResultMessage(String message, int statusMessage) {
        this.message = message;
        this.statusMessage = statusMessage;
    }

    public ResultMessage(int statusMessage, String message, String log) {
        this.statusMessage = statusMessage;
        this.message = message;
        this.log = log;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(int statusMessage) {
        this.statusMessage = statusMessage;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }

}
