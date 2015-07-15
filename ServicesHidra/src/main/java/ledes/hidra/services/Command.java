/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ledes.hidra.services;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
/**
 *
 * @author pedro
 */
@XmlRootElement(name = "command")
@XmlAccessorType(XmlAccessType.FIELD)
public class Command {
    
    @XmlElement
    private String name;
    @XmlElement
    private int numberOfParameters;
    @XmlElementWrapper(name = "parameters")
    @XmlElement(name = "parameter")
    private List<String> parameters;

    public Command(String name, int numberOfParameters, List<String> parameters) {
        this.name = name;
        this.numberOfParameters = numberOfParameters;
        this.parameters = parameters;
    }

    public Command(String name) {
        this.name = name;
        this.parameters = new ArrayList<>();
    }
    
    public Command(){
        this.parameters = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberOfParameters() {
        return numberOfParameters;
    }

    public void setNumberOfParameters(int numberOfParameters) {
        this.numberOfParameters = numberOfParameters;
    }

    public List<String> getParameters() {
        return parameters;
    }

    public void setParameters(List<String> parameters) {
        this.parameters = parameters;
    }
    
}
