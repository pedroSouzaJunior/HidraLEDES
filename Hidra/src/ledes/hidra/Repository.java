package ledes.hidra;

import java.io.File;
//import org.eclipse.jgit.api.Git;
/**
 * This class is responsible to manage a central repository (server).
 * 
 * All operations to manage a local respository is implemented here.
 * The webservice layer uses this class to make it publically available.
 * 
 * @TODO:   transferir todos os métodos da classe net.ledes.hidra.sources.Hidra
 *          relacionados a manipulação de repositório central para cá.
 *          Exemplo: criar repositório local sem conexão com nenhum outro master.
 * 
 * @TODO: Documentar todos membros da classe (atributos, métodos, construtores),
 *        independente do modificador de acesso (public, protected, private),
 *        imediatamente assim que criá-los.
 * 
 * @author Danielli Urbieta e Pedro Souza Junior
 */
public class Repository {
    
    private String localPath;
    private String remotePath;
    private Repository localRepository;
//    private Git git;

    
    public Repository(String localPath){
        super();
             this.localPath = localPath;
             
        
        
    }
    
    
    public String getLocalPath() {
        return localPath;
    }

    public void setLocalPath(String localPath) {
        this.localPath = localPath;
    }

    public String getRemotePath() {
        return remotePath;
    }

    public void setRemotePath(String remotePath) {
        this.remotePath = remotePath;
    }

    public Repository getLocalRepository() {
        return localRepository;
    }

    public void setLocalRepository(Repository localRepository) {
        this.localRepository = localRepository;
    }

//    public Git getGit() {
//        return git;
//    }
//
//    public void setGit(Git git) {
//        this.git = git;
//    }
//    
//    
//    
//    public void init(String localPath){
//        
//        
//       File directory = new File(localPath);
//       git = Git.init().setDirectory(directory).call()
//        
//        
//    }
   
    
    
    
    
}
