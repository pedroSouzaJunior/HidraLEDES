package ledes.hidra;

/**
 * This class provides all operation of a repository, by manipulating
 * Repository and LocalRepository
 * 
 * @TODO: Os métodos dessa classe são os equivalentes aos da classe
 *        net.ledes.hidra.sources.Command, mas usando a terminologia da Cambuci.
 * 
 * @TODO: Documentar todos membros da classe (atributos, métodos, construtores),
 *        independente do modificador de acesso (public, protected, private),
 *        imediatamente assim que criá-los.
 * 
 * @author Danielli Urbieta e Pedro Souza Junior
 */
public class Hidra {
    
    
    /**
     * 
     * @param repository
     */
    private Repository repository;

    public Repository getRepository() {
        return repository;
    }

    public void setRepository(Repository repository) {
        this.repository = repository;
    }
    
    
    
    
}
