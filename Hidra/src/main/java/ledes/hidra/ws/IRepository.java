/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ledes.hidra.ws;

/**
 * IRASRepository: This is the interface for talking to a repository
 */
/**
 *
 * @author pedro
 */
public interface IRepository {

    /**
     * Get All Assets: This searches the repository and returns all the assets
     * in them. If no Assets are found to match, then it returns an empty
     * collection.
     *
     * @return
     * @throws java.io.IOException
     */
    public IRepositoryResultCollection getAllAssets() throws
            java.io.IOException;

    /**
     *
     * Busca por palavra-chave: Pesquisa no repositório e retorna uma coleção De
     * ativos que correspondem a palavra-chave dada. Se não há ativos são
     * encontrados para corresponder, Em seguida, ele retorna uma coleção vazia.
     *
     * Search by Keyword: This searches the repository and returns a collection
     * of assets that match the given keyword. If no assets are found to match,
     * then it returns an empty collection.
     *
     * @param theKeyword
     * @return
     * @throws java.io.IOException
     */
    public IRepositoryResultCollection searchByKeyword(String theKeyword) throws java.io.IOException;

    /**
     * Busca por caminho lógico: Esta pesquisa o repositório e retorna um  
     * coleção de ativos e pastas nas pastas caminho lógico. Se nenhum estiver  
     * Encontrada para corresponder, em seguida, ele retorna uma coleção
     * vazia. A raiz é    indicado por /
     *
     *
     * Search by Logical Path: This searches the repository and returns a
     * collection of assets and folders in the folders logical path. If none are
     * found to match, then it returns an empty collection. The root is
     * indicated by /
     *
     * @param theLogicalPath
     * @return
     * @throws java.io.IOException
     */
    public IRepositoryResultCollection searchByLogicalPath(String theLogicalPath) throws java.io.IOException;
}
