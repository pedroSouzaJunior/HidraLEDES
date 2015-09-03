/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ledes.hidra.rest.model;

import java.io.File;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author pedro
 */
@XmlRootElement
public class Command {

    private File assetFile;
    private File assetUpdate;
    private String destiny;
    private String repositoryPath;
    private String assetName;
    private String submitMessage;
    private String pathToDownload;
    private String remoteRepository;
    private String repositoryLocalCopy;
    private String User;
    private String password;

    public Command() {
    }

    public Command(String localPath) {
        this.destiny = localPath;
    }

    public String getDestiny() {
        return destiny;
    }

    public void setDestiny(String destiny) {
        this.destiny = destiny;
    }

    public File getAssetFile() {
        return assetFile;
    }

    public void setAssetFile(File assetFile) {
        this.assetFile = assetFile;
    }

    public String getSubmitMessage() {
        return submitMessage;
    }

    public void setSubmitMessage(String submitMessage) {
        this.submitMessage = submitMessage;
    }

    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public String getPathToDownload() {
        return pathToDownload;
    }

    public void setPathToDownload(String pathToDownload) {
        this.pathToDownload = pathToDownload;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUser() {
        return User;
    }

    public void setUser(String User) {
        this.User = User;
    }

    public File getAssetUpdate() {
        return assetUpdate;
    }

    public void setAssetUpdate(File assetUpdate) {
        this.assetUpdate = assetUpdate;
    }

    public String getRepositoryPath() {
        return repositoryPath;
    }

    public void setRepositoryPath(String repositoryPath) {
        this.repositoryPath = repositoryPath;
    }

}
