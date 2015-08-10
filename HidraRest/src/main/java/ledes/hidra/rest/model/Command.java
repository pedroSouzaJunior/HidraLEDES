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

    private String localPath;
    private File assetFile;

    public Command() {
    }

    public Command(String localPath) {
        this.localPath = localPath;
    }

    public String getLocalPath() {
        return localPath;
    }

    public void setLocalPath(String localPath) {
        this.localPath = localPath;
    }

    public File getAssetFile() {
        return assetFile;
    }

    public void setAssetFile(File assetFile) {
        this.assetFile = assetFile;
    }

}
