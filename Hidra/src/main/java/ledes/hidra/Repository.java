package ledes.hidra;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import ledes.hidra.asset.Asset;
import ledes.hidra.asset.ClassificationType;
import ledes.hidra.asset.SolutionType;
import ledes.hidra.asset.UsageType;
import ledes.hidra.core.GitFacade;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.xml.sax.SAXException;

/**
 * This class is responsible to manage a central repository (server).
 *
 * All operations to manage a local respository is implemented here. The
 * webservice layer uses this class to make it publically available.
 *
 * @TODO: transferir todos os métodos da classe net.ledes.hidra.sources.Hidra
 * relacionados a manipulação de repositório central para cá. Exemplo: criar
 * repositório local sem conexão com nenhum outro master.
 *
 * @TODO: Documentar todos membros da classe (atributos, métodos, construtores),
 * independente do modificador de acesso (public, protected, private),
 * imediatamente assim que criá-los.
 *
 * @author Danielli Urbieta e Pedro Souza Junior
 */
public class Repository {

    /**
     * @param String localPath - Define full path to the repository local
     */
    private String localPath;
    /**
     * @param String remotePath - Define URL or path to the repository remote
     */
    private String remotePath;

    /**
     * @param File - Directory where the repository
     */
    private final File directory;

    private final GitFacade assistant;

    /**
     * Builder of the repository class with one parameter. You must start the
     * repository after the instantiation of the repository.
     *
     * @param localPath - Full path to the repository local
     */
    public Repository(String localPath) {
        super();
        this.localPath = localPath;
        this.directory = new File(localPath);
        assistant = new GitFacade(localPath);

    }

    /**
     * Builder of the repository class with two parameters. You must start the
     * repository after the instantiation of the repository.
     *
     * @param localPath - Full path to the repository local
     * @param remotePath - URL or path to the repository remote
     */
    public Repository(String localPath, String remotePath) {
        super();
        this.localPath = localPath;
        this.remotePath = remotePath;
        this.directory = new File(localPath);
        assistant = new GitFacade(localPath);
    }

    protected boolean init() {
        return assistant.start(directory);

    }

    protected boolean cloneRepository() {
        try {
            assistant.cloneRepository(directory, remotePath);
            return true;
        } catch (GitAPIException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    protected boolean isRepository() {
        return assistant.isRepositoryInitialized();
    }

    /**
     * Return path repository
     *
     * @return localPath
     */
    public String getLocalPath() {
        return localPath;
    }

    /**
     * Set path repository
     *
     * @param localPath
     */
    public void setLocalPath(String localPath) {
        this.localPath = localPath;
    }

    /**
     * Return remote repository path
     *
     * @return
     */
    public String getRemotePath() {
        return remotePath;
    }

    /**
     * Set remote repository path
     *
     * @param remotePath
     */
    public void setRemotePath(String remotePath) {
        this.remotePath = remotePath;
    }

    /**
     * Responsible method to delete a repository
     */
    public void removeRepository() {
        if (directory.isDirectory()) {
            File[] sun = directory.listFiles();
            for (File toDelete : sun) {
                toDelete.delete();
            }

        }
    }

    /**
     * Método responsável pela leitura de manifest asset.xml de um repositório.
     * Retorna nulo se repositório não foi inicializado.
     *
     * @return
     * @throws JAXBException
     * @throws java.io.FileNotFoundException
     */
    public Asset readAsset() throws JAXBException, FileNotFoundException {
        if (isRepository()) {
            FileReader xml;
            Asset asset;
            JAXBContext jaxbContext = JAXBContext.newInstance(Asset.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            xml = new FileReader(localPath + "asset.xml");
            asset = (Asset) unmarshaller.unmarshal(xml);
            return asset;
        }
        return null;

    }

    /**
     * Valida o manifest asset.xml de acordo com o esquema asset.xsd que define
     * o padrão dos ativos segundo OMG.
     *
     * @param assetPath - recebe como parametro uma string que define o caminho
     * dentro do diretorio do ativo do manifest asset.xml
     * @return
     * @throws SAXException
     * @throws IOException
     */
    boolean validateAsset(String assetPath) throws SAXException, IOException {

        File schemaFile = new File("src/ledes/hidra/util/asset.xsd");
        Source xmlFile = new StreamSource(new File(assetPath + "asset.xml"));
        SchemaFactory schemaFactory = SchemaFactory
                .newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = schemaFactory.newSchema(schemaFile);
        Validator validator = schema.newValidator();
        try {
            validator.validate(xmlFile);
            System.out.println(xmlFile.getSystemId() + " is valid");
            return true;
        } catch (SAXException e) {
            System.out.println(xmlFile.getSystemId() + " is NOT valid");
            System.out.println("Reason: " + e.getLocalizedMessage());
        }

        return false;

    }

    /**
     * Válida se o diretório, que representa o ativo, contém todos os artefatos
     * descritos no manifest asset.xml. Recebe como paramêtro um Objeto Asset.
     * Só deve ser invocada se o asset.xml estiver dentro do padrão RAS
     * definido.
     *
     * @param asset
     * @return
     */
    boolean validateAsset(Asset asset) {

        return false;
    }

    /**
     * Reponsavel por adicionar um Ativo de software ao repositorio. A operacao
     * consiste de uma validacao do ativo, antes de efetuar a adicao do mesmo ao
     * repositorio. Caso o ativo esteja condisente com o padrao RAS, ele podera
     * entao ser adicionado ao repositorio.
     *
     * @param Asset
     */
    boolean addAsset(String assetPath) throws SAXException, IOException {

        File auxiliary = new File(assetPath);
        if (auxiliary.isDirectory()) {
            return validateAsset(assetPath);
        }

        return false;
    }

    /**
     * Responsavel por retornar ao usuario a forma representativa dos artefatos
     * que fazem parte do ativo
     *
     * @param assetId que representa o id de um ativo de software.
     */
    SolutionType getSolution(String assetId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Responsavel por alterar a solucao que compoe um ativo de software
     *
     * @param assetId representa o id de um ativo de software.
     * @param solution representa a solucao que compoe o ativo de software.
     */
    boolean setSolutionType(String assetId, SolutionType solution) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Responsavel por Dado o id de um ativo, retornar ao usuario um objeto
     * Classification que representa a Lista de um conjunto de descritores para
     * classificação de ativos, bem como uma descrição do contexto para o qual o
     * ativo é relevante.
     *
     * @param assetId representa o id de um ativo de software.
     */
    ClassificationType getClassification(String assetId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    boolean setClassification(ClassificationType classification) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    UsageType getUsage(String assetId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    boolean setUsage(UsageType usage) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    List<Asset> getRelatedAssets(String assetId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    boolean setRelatedAsset(String assetId, String relatedId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    String getLog(String assetId, boolean complete) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    List<Asset> listAssets() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    File downloadAsset(String assetId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    boolean removeAsset(String assetId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    boolean isRepository(String directory) {
        return assistant.isRepositoryInitialized(directory);
    }

}
