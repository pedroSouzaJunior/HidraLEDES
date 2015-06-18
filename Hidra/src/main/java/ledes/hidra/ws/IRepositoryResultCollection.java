/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ledes.hidra.ws;

/**
 * IRASRepositoryResultCollection: This interface holds a list of assets and
 * folders.
 */
/**
 *
 * @author pedro
 */
public interface IRepositoryResultCollection {

    /**
     * Item: Get the IRASRepositoryResult at the given index.
     * @param index
     * @return 
     * @throws java.io.IOException 
     */
    public IRepositoryResult item(int index) throws 
            java.io.IOException;

    /**
     * Get Count: The number of IRASRepositoryResult in the list.
     * @return 
     * @throws java.io.IOException
     */

    public int getCount() throws java.io.IOException;

    /**
     * Add: Add an item to the collection.
     * @param theResultItem
     * @return 
     * @throws java.io.IOException 
     */

    public boolean add(IRepositoryResult theResultItem) throws
            java.io.IOException;

    /**
     * Remove: Remove an item from the collection.
     * @param theResultItem
     * @return 
     * @throws java.io.IOException
     */
    public boolean remove(IRepositoryResult theResultItem) throws
            java.io.IOException;
}
