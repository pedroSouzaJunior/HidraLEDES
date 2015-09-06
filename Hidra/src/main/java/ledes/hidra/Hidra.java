package ledes.hidra;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import ledes.hidra.asset.Asset;
import ledes.hidra.asset.ClassificationType;
import ledes.hidra.asset.SolutionType;
import ledes.hidra.asset.UsageType;
import org.xml.sax.SAXException;
import javax.xml.bind.JAXBContext;


import javax.xml.bind.JAXBException;

import javax.xml.bind.Marshaller;
import ledes.hidra.asset.Activity;
import ledes.hidra.asset.ArtifactType;

/**
 * This class provides all operation of a repository, by manipulating Repository
 * and LocalRepository
 *
 *
 * @author Danielli Urbieta e Pedro Souza Junior
 */
public class Hidra {

    public Hidra(String localPath) {
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

    
    /**
     * Retorna um repositorio Hidra.
     * @return 
     */
    public Repository getRepository() {
        return repository;
    }

    /**
     * Define o repositório Hidra.
     * @param repository 
     */
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

        if (repository.isRepository()) {
            initialized = true;
        }
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
     * Cria, em um diretório vazio, uma cópia de um repositório indicado que
     * requer autentificação com protocolo https. Se o diretório não existir,
     * ele será criado. Se o diretório não for vazio, ou tiver um repositório
     * vazio inicializado, retornará erro.
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
     * Adiciona um ativo válido com seus artefatos ao repositório.
     * Recebe nome do ativo no repositório
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
    public boolean setSolutionType(String assetId, SolutionType solution) {
        return repository.setSolutionType(assetId, solution);
    }

    /**
     * RF-04
     * Permite verificar se um ativo é válido de acordo com o padrão RAS adotado.
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
     * Remove um ativo do repositório. 
     *
     * @param assetName - Recebe o nome do ativo a ser removido.
     * @return
     *
     */
    public boolean removeAsset(String assetName) {
        try {
            return repository.removeAsset(assetName);
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
    public String getClassification(String assetId) {
        return repository.getClassification(assetId);
    }

    /**
     * RF-06
     * Define a classificação de um ativo.
     * @param assetID
     * @param classification
     * @return
     */
    public boolean setClassification(String assetID, ClassificationType classification) {
        return repository.setClassification(assetID, classification);
    }

    /**
     * RF-07, RF-14
     * Retorna a utilização (Usage) de um ativo.
     * @param assetId
     * @return
     */
    public String getUsage(String assetId) {
        return repository.getUsage(assetId);
    }

    /**
     * RF-07
     * Define o usage de um ativo.
     * @param usage
     * @return
     */
    public boolean setUsage(String assetId, UsageType usage) {
        return repository.setUsage(assetId, usage);
    }

    /**
     * RF-08
     * Retorna a dependência (RelatedAssets) entre ativos.
     * @param assetId
     * @return
     */
    public String getRelatedAssets(String assetId) {
        return repository.getRelatedAssets(assetId);
    }

    /**
     * RF-08
     * Define a dependência entre os ativos.
     *
     * @param assetId - Ativo que deseja registrar dependência
     * @param relatedId - Ativo que se tem dependência.
     * @return
     */
    public boolean setRelatedAsset(String assetId, String relatedId) {
        return repository.setRelatedAsset(assetId, relatedId);
    }

    /**
     * RF-09
     * Retorna informações sobre mudanças de um ativo específico.
     * @param assetName
     * @return
     */
    public String getLog(String assetName) {
        return repository.getLog(assetName);
    }

    
    /**
     * Retorna informações sobre mudanças em todo repositório.
     * @return  
     */
    public String getLog() {
        return repository.getLog();
    }

    /**
     * RF-10
     * Retorna uma lista com todos os ativos validados e monitorados no repositório.
     * @return 
     */
    public Map<String, String> listAssets() {
        return repository.listAssets();
    }

    /**
     * RF-11
     * Realiza o download do ativo em formato compactado.
     * @param assetId - Nome do ativo
     * @return
     * @throws java.io.FileNotFoundException
     */
    public File downloadAsset(String assetId) throws FileNotFoundException {
        return repository.downloadAsset(assetId);
    }

    /**
     * Salva todas as alterações realizadas no repositório.
     * É obrigatório o envio de uma mensagem informando as alterações realizadas.
     * @param message
     * @return 
     */
    public boolean save(String message) {

        try {
            return repository.saveChanges(message);
        } catch (SAXException | IOException | JAXBException ex) {
            Logger.getLogger(Hidra.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;

    }

    /**
     * Atualiza o repositório local com as mudanças do repositório remoto.
     * É necessário autentificação para habilitar acesso.
     * @param user - String 
     * @param password - String
     * @return 
     */
    public boolean synchronize(String user, String password) {

        return repository.synchronizeRepository(user, password);

    }

    /**
     * Envia as alterações do repositório local ao repositório remoto.
     * @param user
     * @param password
     * @return String
     */
    public String update(String user, String password) {

        return repository.updateRepository(user, password);
    }

    
    /**
     * Retorna informações sobre o estado do repositório.
     * @return 
     */
    public Map<String, Set<String>> showStatus() {
        return repository.showStatus();

    }

    /**
     * Atualiza um ativo.
     * @param assetName
     * @return 
     */
    public boolean updateAsset(String assetName) {
        try {
            return repository.updateAsset(assetName);
        } catch (JAXBException | Repository.ValidationRuntimeException | SAXException | IOException ex) {
            Logger.getLogger(Hidra.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    /**
     * Define o usuário do repositório.
     * @param name
     * @param email 
     */
    public void setUser(String name, String email) {
        repository.setUserRepo(name, email);
    }

    /**
     * Retorna o usuário atual do repositório
     * @return 
     */
    public Map<String, String> getUser() {
        return repository.getUserRepo();

    }

    /**
     * Define o repositório remoto de um repositório local.
     * @param url 
     */
    public void setRemoteRepo(String url) {
        repository.setRemoteRepo(url);
    }

    /**
     * Retorna informações sobre o repositório remoto
     * @return 
     */
    public String getRemoteRepo() {
        return repository.getRemoteRepo();
    }
    
    public boolean findAsset(String assetName){
        return repository.findAsset(assetName);
    }

    public void createTemplateAsset() throws JAXBException {

        Asset asset = new Asset();
        asset.setId("1");
        asset.setName("Asset");
        asset.setSolution(createDefaultSolution());

        // File file = new File("C:\\file.xml");
        JAXBContext jaxbContext = JAXBContext.newInstance(Asset.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

        // output pretty printed
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        //  jaxbMarshaller.marshal(asset, file);
        jaxbMarshaller.marshal(asset, System.out);

    }

    public SolutionType createDefaultSolution() {

        ArtifactType art = new ArtifactType();
        SolutionType sol = new SolutionType();
        SolutionType.Design des = new SolutionType.Design();
        art.setId("1");
        art.setName("design");
        art.setReference(File.separator + "Design");
        art.setType("Folder");
        des.getArtifact().add(art);
        sol.setDesign(des);
        
        SolutionType.Artifacts arts = new SolutionType.Artifacts();
        art = new ArtifactType();
        art.setId("1");
        art.setName("artifacts");
        art.setReference(File.separator + "Artifacts");
        art.setType("Folder");
        arts.getArtifact().add(art);
        sol.setArtifacts(arts);
        
        
        SolutionType.Implementation impl= new SolutionType.Implementation();
        art = new ArtifactType();
        art.setId("1");
        art.setName("implementation");
        art.setReference(File.separator + "Implementation");
        art.setType("Folder");
        impl.getArtifact().add(art);
        sol.setImplementation(impl);
        
        SolutionType.Requirements req = new SolutionType.Requirements();
        art = new ArtifactType();
        art.setId("1");
        art.setName("requirements");
        art.setReference(File.separator + "Requirements");
        art.setType("Folder");
        req.getArtifact().add(art);
        sol.setRequirements(req);
        
        
        SolutionType.Test test = new SolutionType.Test();
        art = new ArtifactType();
        art.setId("1");
        art.setName("test");
        art.setReference(File.separator + "Test");
        art.setType("Folder");
        test.getArtifact().add(art);
        sol.setTest(test);

        return sol;
    }
    
    public UsageType createUsageDefault(){
    
        UsageType usage = new UsageType();
        Activity act = new Activity();
        act.setId("1");
        act.setReference(File.separator+ "usage");
        act.setRole("");
        act.setTask("");
        act.setTaskRole("");
       return usage;
    }

}
