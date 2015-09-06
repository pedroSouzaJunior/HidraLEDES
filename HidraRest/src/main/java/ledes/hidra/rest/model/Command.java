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
public class Command {

    private String repositoryPath;
    private String assetName;
    private String submitMessage;
    private String remoteRepository;
    private String repositoryLocalCopy;
    private String User;
    private String password;

    public Command() {
    }

    public Command(String repositoryPath, String remoteRepository, String User, String password) {
        this.repositoryPath = repositoryPath;
        this.remoteRepository = remoteRepository;
        this.User = User;
        this.password = password;
    }

    public String getRepositoryPath() {
        return repositoryPath;
    }

    public void setRepositoryPath(String repositoryPath) {
        this.repositoryPath = repositoryPath;
    }

    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public String getSubmitMessage() {
        return submitMessage;
    }

    public void setSubmitMessage(String submitMessage) {
        this.submitMessage = submitMessage;
    }

    public String getRemoteRepository() {
        return remoteRepository;
    }

    public void setRemoteRepository(String remoteRepository) {
        this.remoteRepository = remoteRepository;
    }

    public String getRepositoryLocalCopy() {
        return repositoryLocalCopy;
    }

    public void setRepositoryLocalCopy(String repositoryLocalCopy) {
        this.repositoryLocalCopy = repositoryLocalCopy;
    }

    public String getUser() {
        return User;
    }

    public void setUser(String User) {
        this.User = User;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    
}
