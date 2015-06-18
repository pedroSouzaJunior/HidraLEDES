/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ledes.hidra.ws;

/**
 * This is the object represents an asset in a RAS repository.
 */
/**
 *
 * @author pedro
 */
public interface IAsset extends IRepositoryResult {

    /**
     * Get Asset URL: The URL to download the asset from.
     * @return 
     * @throws java.io.IOException
     */
    public String getAssetURL() throws java.io.IOException;

    /**
     * Get Description: A short description of the asset.
     * @return 
     * @throws java.io.IOException
     */
    public String getDescription() throws java.io.IOException;

    /**
     * Get Ranking:
     *
     * * The ranking (low of 0 to high of 100) on how the item ranked. This is
     * used for sorting the results. Note rankings outside the range of 0 - 100,
     * are given a 0 ranking.
     * @return 
     * @throws java.io.IOException
     */
    public int getRanking() throws java.io.IOException;

    /**
     * Get Version: The version of the asset.
     * @return 
     * @throws java.io.IOException
     */
    public String getVersion() throws java.io.IOException;
}
