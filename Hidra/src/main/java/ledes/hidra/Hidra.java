package ledes.hidra;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBException;
import ledes.hidra.asset.ClassificationType;
import ledes.hidra.asset.SolutionType;
import ledes.hidra.asset.UsageType;
import org.xml.sax.SAXException;

/**
 * This class provides all operation of a repository, by manipulating Repository
 * and LocalRepository
 *
 * @TODO: Os métodos dessa classe são os equivalentes aos da classe
 * net.ledes.hidra.sources.Command, mas usando a terminologia da Cambuci.
 *
 * @TODO: Documentar todos membros da classe (atributos, métodos, construtores),
 * independente do modificador de acesso (public, protected, private),
 * imediatamente assim que criá-los.
 *
 * @author Danielli Urbieta e Pedro Souza Junior
 */
public class Hidra {
    
    public Hidra(String localPath){
        super();
        repository = new Repository(localPath);
        
    }

    /**
     *
     * @param repository
     */
    private Repository repository;

    public Hidra() {
    }

    public Repository getRepository() {
        return repository;
    }

    public void setRepository(Repository repository) {
        this.repository = repository;
    }

    
    
    
    /**
     * Inicializa um repositório local sem um repositório master associado. Se
     * não existir diretório ele será criado, se já existir um repositório no
     * diretório indicado nada será alterado
     *
     * @param localPath - String com o caminho que o repositório será criado
     * @return - true se não houve problemas
     * @throws java.io.IOException
     * @throws javax.xml.bind.JAXBException
     * 
     */

    public boolean startRepository(String localPath) throws IOException, JAXBException {
        boolean initialized = false;

        repository = new Repository(localPath);
        
        if (repository.isRepository())
            initialized = true;
        try {
            return repository.init(initialized);
        } catch (IOException | JAXBException ex) {
            Logger.getLogger(Hidra.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;

    }

    /**
     * Cria, em um diretório vazio, uma cópia de um repositório indicado. Se o
     * diretório não existir, ele será criado. Se o diretório não for vazio, ou
     * tiver um repositório vazio inicializado, retornará erro.
     *
     * @param localPath - String que indica o caminho onde o repositório sera
     * copiado
     * @param remotePath - String que indica a URL - caso seja um repositório
     * remoto - ou o caminho do repositorio a ser copiado
     * @return
     */
    public boolean startSynchronizedRepository(String localPath, String remotePath) {
        repository = new Repository(localPath, remotePath);
        return repository.cloneRepository();
    }
    
    
    /**
     * Cria, em um diretório vazio, uma cópia de um repositório indicado que requer autentificação com protocolo https.  Se o
     * diretório não existir, ele será criado. Se o diretório não for vazio, ou
     * tiver um repositório vazio inicializado, retornará erro.
     *
     * @param localPath - String que indica o caminho onde o repositório sera
     * copiado
     * @param remotePath - String que indica a URL - caso seja um repositório
     * remoto - ou o caminho do repositorio a ser copiado
     * @param user - String com o usuario do repositorio
     * @param password - String com a senha do usuario do repositorio
     * @return
     */
    public boolean startSynchronizedRepository(String localPath, String remotePath, String user, String password) {
        repository = new Repository(localPath, remotePath);
        return repository.cloneRepository(user, password);
    }


  
    /**
     * RF-01
     *
     * @param nameAsset
     * @return
     * 
     */
    public boolean addAsset(String nameAsset) {
        try {
           
            return repository.addAsset(nameAsset);
        } catch (SAXException | IOException | JAXBException ex) {
            Logger.getLogger(Hidra.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    /**
     * RF-02, RF-13
     *
     * @param assetId
     * @return
     */

    public String getSolution(String assetId) {
        return repository.getSolution(assetId);

    }

    /**
     * RF-02, RF-13
     *
     * @param assetId
     * @param solution
     * @return
     */
    public boolean setSolutionType(String assetId, SolutionType solution){
        return repository.setSolutionType(assetId, solution);
    }

    /**
     * RF-04
     *
     * @param assetPath
     * @return
     */
    public boolean validateAsset(String assetPath) {
        try {
            return repository.validateAsset(assetPath);
        } catch (SAXException | IOException ex) {
            Logger.getLogger(Hidra.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    /**
     * RF-05
     *
     * @param assetId
     * @return
     * 
     */
    public boolean removeAsset(String assetId)  {
        try {
            return repository.removeAsset(assetId);
        } catch (JAXBException | FileNotFoundException ex) {
            Logger.getLogger(Hidra.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    /**
     * RF-06
     *
     * @param assetId
     * @return
     */
    public String getClassification(String assetId){
        return repository.getClassification(assetId);
    }

    /**
     * RF-06
     *
     * @param assetID
     * @param classification
     * @return
     */
    public boolean setClassification(String assetID, ClassificationType classification) {
        return repository.setClassification(assetID, classification);
    }

    /**
     * RF-07, RF-14
     *
     * @param assetId
     * @return
     */
    public String getUsage(String assetId){
        return repository.getUsage(assetId);
    }

    /**
     * RF-07
     *
     * @param usage
     * @return
     */
    public boolean setUsage(String assetId, UsageType usage) {
        return repository.setUsage(assetId,usage);
    }

    /**
     * RF-08
     *
     * @param assetId
     * @return
     */
    public String getRelatedAssets(String assetId) {
        return repository.getRelatedAssets(assetId);
    }

    
    
    /**
     * RF-08
     *
     * @param assetId
     * @param relatedId
     * @return
     */
    public boolean setRelatedAsset(String assetId, String relatedId) {
        return repository.setRelatedAsset(assetId, relatedId);
    }

    /**
     * RF-09
     *
     * @param assetName
     * @return
     */
    public String getLog(String assetName) {
        return repository.getLog(assetName);
    }

//    /**
//     * RF-09
//     *
//     * @param assetId
//     * @return
//     */
//    public String getLog(String assetId) {
//        return repository.getLog(assetId, false);
//    }

    /**
     * RF-10
     *
     * @return
     */
    public Map<String, String> listAssets() {
        return repository.listAssets();
    }

    /**
     * RF-11
     *
     * @param assetId
     * @return
     * @throws java.io.FileNotFoundException
     */
    public File downloadAsset(String assetId) throws FileNotFoundException {
        return repository.downloadAsset(assetId);
    }

    
    public boolean save(String message){
    
        try {
            return repository.saveChanges(message);
        } catch (SAXException | IOException | JAXBException ex) {
            Logger.getLogger(Hidra.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    
    }
    
    
    public boolean synchronize(String user, String password){
        
            return repository.synchronizeRepository(user, password);
        
    
    }
    
    
    public String showLogs(String assetName){
        return repository.getLog(assetName);
    }
    
    public String showLogs(){
        return repository.getLog();
    }
    
    public Map<String, Set<String>> showStatus(){
        return repository.showStatus();
    
    }
    
    public boolean updateAsset(String assetName){
        try {
            return repository.updateAsset(assetName);
        } catch (JAXBException | Repository.ValidationRuntimeException | SAXException | IOException ex) {
            Logger.getLogger(Hidra.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public void setUser(String name, String email){
        repository.setUserRepo(name, email);
    }
    
    public Map<String, String> getUser(){
        return repository.getUserRepo();
        
    }
   
    public void setRemoteRepo(String url){
         repository.setRemoteRepo(url);
    }
    
    public String getRemoteRepo(){
        return repository.getRemoteRepo();
    }
    
    
  
}
