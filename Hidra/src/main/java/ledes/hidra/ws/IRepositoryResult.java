/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ledes.hidra.ws;

/**
 * This is the base result object that is returned from a search of a RAS
 * repository. The 2 known flavors are IRASRepositoryAsset and
 * IRASRepositoryFolder
 */
/**
 *
 * @author pedro
 */
public interface IRepositoryResult {

    /**
     * Get Name: The display name of the result item.
     * @return 
     * @throws java.io.IOException
     */
    public String getName() throws java.io.IOException;

    /**
     * Get Logical Folder Path:<br>
     * The logical path of the result item. This is used to organize the assets.
     * The root folder is indicated using /
     * @return 
     * @throws java.io.IOException
     */
    public String getLogicalFolderPath() throws java.io.IOException;
}


