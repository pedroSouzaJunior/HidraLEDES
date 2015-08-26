package ledes.hidra;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.PrintWriter;

import java.net.URL;
import java.net.URLConnection;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.SchemaOutputResolver;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import ledes.hidra.asset.ArtifactActivy;
import ledes.hidra.asset.ArtifactType;
import ledes.hidra.asset.Asset;
import ledes.hidra.asset.ClassificationType;
import ledes.hidra.asset.Context;
import ledes.hidra.asset.ContextReference;
import ledes.hidra.asset.DescriptionGroup;
import ledes.hidra.asset.RelatedAssetType;
import ledes.hidra.asset.SolutionType;
import ledes.hidra.asset.UsageType;
import ledes.hidra.core.GitFacade;
import ledes.hidra.core.ValidatorAssets;
import ledes.hidra.dao.HidraDAO;
import ledes.hidra.util.Properties;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.xml.sax.SAXException;

/**
 * This class is responsible to manage a central repository (server).
 *
 * @author Danielli Urbieta e Pedro Souza Junior
 */
public class Repository {

    private final static String separator = File.separator;
    private final static String manifest = "rasset.xml";
    /**
     * @param String localPath - Define full path to the repository local
     */
    private final String localPath;
    /**
     * @param String remotePath - Define URL or path to the repository remote
     */
    private String remotePath;

    /**
     * @param File - Directory where the repository
     */
    private final File directory;

    private final GitFacade assistant;

    private List<String> exceptionList;

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
        this.exceptionList = new ArrayList<>();
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
        this.exceptionList = new ArrayList<>();
    }

    /**
     * Inizializa um repositorio com arquivos de configuração Git e Hidra
     *
     * @param initialized
     * @return
     * @throws IOException
     * @throws JAXBException
     */
    protected boolean init(boolean initialized) throws IOException, JAXBException {

        boolean ret = assistant.start(directory);

        if (!initialized) {

            new File(localPath + separator + ".hidra").mkdir();
            HidraDAO dao = new HidraDAO(localPath + separator + ".hidra" + separator);
            dao.connection();
            createSchema();
            writerIgnoreFile();
            try {
                assistant.add(".gitignore");
            } catch (GitAPIException ex) {
                Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return ret;

    }

    /**
     * Cria um arquivo .gitgnore para que o repositorio GIT não monitore
     * mudanças no diretorio .gidra.
     *
     * @throws IOException
     */
    private void writerIgnoreFile() throws IOException {
        try (FileWriter ignoreFile = new FileWriter(localPath + separator + ".gitignore")) {
            PrintWriter writer = new PrintWriter(ignoreFile);
            writer.printf(".hidra");
        }

    }

    /**
     * Clona um repositorio. Sem necessidade de autentificação
     *
     * @return
     */
    protected boolean cloneRepository() {
        try {
            assistant.cloneRepository(directory, remotePath);
            return true;
        } catch (GitAPIException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    
    protected boolean cloneRepository(String user, String password){
     try {
            assistant.cloneRepository(directory, remotePath, user, password);
            return true;
        } catch (GitAPIException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }
    
    protected boolean isRepository() {
        File hidraRepo = new File(localPath + separator + ".hidra");
        File[] matchingFilesAsset;
        File[] matchingFilesDB;
        if (hidraRepo.isDirectory()) {
            matchingFilesAsset = hidraRepo.listFiles(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {

                    return name.equals("asset.xsd");
                }
            });
            matchingFilesDB = hidraRepo.listFiles(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {

                    return name.equals("hidra.db");
                }
            });
            return assistant.isRepositoryInitialized() && matchingFilesAsset.length != 0 && matchingFilesDB.length != 0;
        } else {
            return false;
        }

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

    public List<String> getExceptionList() {
        return exceptionList;
    }

    public void setExceptionList(List<String> exceptionList) {
        this.exceptionList = exceptionList;
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
     * Cria o arquivo asset.xsd no repositorio, esse arquivo será usado para
     * validar o manifest dos ativos.
     *
     * @return
     * @throws JAXBException
     * @throws IOException
     */
    boolean createSchema() throws JAXBException, IOException {

        final File baseDir = new File(localPath + separator + ".hidra");

        class MySchemaOutputResolver extends SchemaOutputResolver {

            @Override
            public Result createOutput(String namespaceUri, String suggestedFileName) throws IOException {
                return new StreamResult(new File(baseDir, "asset.xsd"));
            }
        }

        JAXBContext context = JAXBContext.newInstance(Asset.class);
        context.generateSchema(new MySchemaOutputResolver());

        return false;
    }

    /**
     * Método responsável pela leitura de manifest rasset.xml de um repositório.
     * Retorna nulo se repositório não foi inicializado.
     *
     * @param nameAsset
     * @return
     * @throws JAXBException
     * @throws java.io.FileNotFoundException
     */
    public Asset readAsset(String nameAsset) throws JAXBException, FileNotFoundException {
        if (isRepository()) {
            FileReader xml;
            Asset asset;
            JAXBContext jaxbContext = JAXBContext.newInstance(Asset.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            xml = new FileReader(localPath + separator + nameAsset + separator + "rasset.xml");
            asset = (Asset) unmarshaller.unmarshal(xml);
            return asset;
        }
        return null;

    }

    /**
     * Método responsável pela verificação da existência do arquivo manifest
     * chamado "rasset.xml". Recebe o diretorio do Asset a ser adicionado.
     *
     * @param path
     * @return
     */
    public boolean manifestExist(File path) {

        File[] matchingFiles = path.listFiles(new FilenameFilter() {

            @Override
            public boolean accept(File dir, String name) {

                return (name.startsWith("rasset") && name.endsWith("xml"));
            }
        });
        // System.out.println("Manifest: " + Configuration.properties.getProperty("ManifestName") + Configuration.properties.getProperty("ManifestExtension"));

        return matchingFiles != null;

    }

    /**
     * Valida o manifest rasset.xml de acordo com o esquema asset.xsd que define
     * o padrão dos ativos segundo OMG.
     *
     * @param assetPath - recebe como parametro uma string que define o caminho
     * dentro do diretorio do ativo do manifest rasset.xml
     * @return
     * @throws SAXException
     * @throws IOException
     */
    public boolean validateAsset(String assetPath) throws SAXException, IOException {

        File schemaFile = new File(localPath + separator + ".hidra" + separator + "asset.xsd");
        Source xmlFile = new StreamSource(new File(assetPath + manifest));
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
     * descritos no manifest rasset.xml. Recebe como paramêtro um Objeto Asset.
     * Só deve ser invocada se o rasset.xml estiver dentro do padrão RAS
     * definido.
     *
     * @param asset
     * @param assetPath
     * @return
     */
    public boolean validateAsset(Asset asset, String assetPath) {
        ValidatorAssets validator = new ValidatorAssets(assetPath);
        return validator.isValidAsset(asset);
    }

    /**
     * *
     * Exceção criada para tratar erros da validação;
     */
    public class ValidationRuntimeException extends RuntimeException {

        public ValidationRuntimeException() {
            super();
        }

        public ValidationRuntimeException(String message) {
            super(message);
        }

        public ValidationRuntimeException(String message, Throwable cause) {
            super(message, cause);
        }

        public ValidationRuntimeException(Throwable cause) {
            super(cause);
        }
    }

    /**
     * Válida o ativo
     *
     * @param assetName
     * @param assetPath
     * @param path
     * @return
     * @throws SAXException
     * @throws IOException
     * @throws JAXBException
     * @throws ledes.hidra.Repository.ValidationRuntimeException
     */
    public boolean validateAll(String assetName, String assetPath, File path) throws SAXException, IOException, JAXBException, ValidationRuntimeException {
        boolean ret = true;
        Asset asset = readAsset(assetName);

        if (!path.isDirectory()) {
            ret = false;
            throw new ValidationRuntimeException("It is not a directory: " + path);

        }
        if (!manifestExist(path)) {
            ret = false;
            throw new ValidationRuntimeException("File not found manisfest in: " + path);

        }
        if (!asset.getName().equalsIgnoreCase(assetName)) {
            ret = false;
            throw new ValidationRuntimeException("The asset name does not match the name described in the manifest.");

        }

        if (!validateAsset(assetPath)) {
            ret = false;
            throw new ValidationRuntimeException("Manifest not is valid");

        }
        if (!validateAsset(asset, assetPath)) {
            ret = false;
            throw new ValidationRuntimeException("Active structure does not match the manifest file");

        }

        return ret;
    }

    /**
     * Reponsavel por adicionar um Ativo de software ao repositorio. A operacao
     * consiste de uma validacao do ativo, antes de efetuar a adicao do mesmo ao
     * repositorio. Caso o ativo esteja condizente com o padrao RAS, ele podera
     * entao ser adicionado ao repositorio.
     *
     * @param nameAsset
     * @return
     * @throws org.xml.sax.SAXException
     * @throws java.io.IOException
     * @throws javax.xml.bind.JAXBException
     *
     */
    public boolean addAsset(String nameAsset) throws SAXException, IOException, JAXBException {

        
        String assetPath = new File(localPath).getAbsolutePath() + File.separator + nameAsset + File.separator;
        File assetFolder = new File(assetPath);
        Asset asset = readAsset(nameAsset);

        if (validateAll(nameAsset, assetPath, assetFolder)) {
            if (findAsset(asset)) {

                throw new ValidationRuntimeException("File already monitored. You might want to do \"updateAsset\"");

            }

            try {
                HidraDAO dao = new HidraDAO(localPath + separator + ".hidra" + separator);
                if (dao.insertion(asset.getName(), asset.getId()));
                return assistant.add(nameAsset);
            } catch (GitAPIException ex) {
                Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        } else {

            return false;

        }
    }

    /**
     * Procura no banco de dados do repositorio, se o ativo já está sendo
     * monitorado.
     *
     * @param asset
     * @return verdadeiro caso o ativo esteja sendo monitorado.
     */
    private boolean findAsset(Asset asset) {

        HidraDAO dao = new HidraDAO(localPath + separator + ".hidra" + separator);
        return dao.find(asset.getName(), asset.getId());

    }

    /**
     * Retorna uma lista com o estado atual do repositório.
     *
     * @return <String, Set<String>>
     */
    public Map<String, Set<String>> showStatus() {

        try {
            return assistant.status();
        } catch (GitAPIException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    /**
     * Atualiza um ativo já monitorado a aréa de seleção. Caso o ativo ainda não
     * esteja sendo monitorado retorna falso. Caso as modificações não estejam
     * de acordo com o padrão adotado, impede a atualização e retorna falso.
     *
     * @param assetName - Recebe uma String com o nome do ativo a ser
     * atualizado.
     * @return
     * @throws JAXBException
     * @throws FileNotFoundException
     * @throws ledes.hidra.Repository.ValidationRuntimeException
     * @throws SAXException
     * @throws IOException
     */
    public boolean updateAsset(String assetName) throws JAXBException, FileNotFoundException, ValidationRuntimeException, SAXException, IOException {
        String assetPath = new File(localPath).getAbsolutePath() + File.separator + assetName + File.separator;
        File assetFolder = new File(assetPath);
        
        Asset asset = readAsset(assetName);
        
        if (findAsset(readAsset(assetName))) {
            if (validateAll(assetName, assetPath, assetFolder)) {
                try {
                    return assistant.add(assetName);
                } catch (GitAPIException ex) {
                    Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        } else {
            throw new ValidationRuntimeException("Asset unmonitored, please add the asset to area selection with 'addAsset'");

        }
        return false;

    }

    private boolean javaToxml(Asset asset, String assetId) {
        boolean result = false;
        try {
            File rasset = new File(directory + separator + assetId + separator + "rasset.xml");

            JAXBContext jaxbContext = JAXBContext.newInstance(Asset.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            //Marshal the employees list in console
            //jaxbMarshaller.marshal(asset, System.out);
            //Marshal the employees list in file
            jaxbMarshaller.marshal(asset, rasset);

            result = true;
        } catch (JAXBException e) {
            System.err.println("Algo de Errado nao Esta Certo");
        }

        return result;
    }

    /**
     * Responsavel por retornar ao usuario a forma representativa dos artefatos
     * que fazem parte do ativo
     *
     * @param assetId que representa o id de um ativo de software.
     * @return
     */
    public String getSolution(String assetId) {

        File assetFile = new File(directory + separator + assetId);
        if (assetFile.exists()) {
            try {
                return readAsset(assetId).describeSolution();
            } catch (JAXBException | FileNotFoundException ex) {
                Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        return null;

    }

    /**
     * Responsavel por alterar a solucao que compoe um ativo de software
     *
     * @param assetId representa o id de um ativo de software.
     * @param solution representa a solucao que compoe o ativo de software.
     * @return
     */
    public boolean setSolutionType(String assetId, SolutionType solution) {
        boolean result = false;

        try {
            Asset asset = readAsset(assetId);

            for (ArtifactType a : solution.getArtifacts().getArtifact()) {
                asset.getSolution().getArtifacts().getArtifact().add(a);
            }

            for (ArtifactType a : solution.getRequirements().getArtifact()) {
                asset.getSolution().getRequirements().getArtifact().add(a);
            }

            for (ArtifactType a : solution.getDesign().getArtifact()) {
                asset.getSolution().getDesign().getArtifact().add(a);
            }

            for (ArtifactType a : solution.getImplementation().getArtifact()) {
                asset.getSolution().getImplementation().getArtifact().add(a);
            }

            for (ArtifactType a : solution.getTest().getArtifact()) {
                asset.getSolution().getTest().getArtifact().add(a);
            }

            result = javaToxml(asset, assetId);
        } catch (JAXBException | FileNotFoundException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

    /**
     * Responsavel por Dado o id de um ativo, retornar ao usuario um objeto
     * Classification que representa a Lista de um conjunto de descritores para
     * classificação de ativos, bem como uma descrição do contexto para o qual o
     * ativo é relevante.
     *
     * @param assetId representa o id de um ativo de software.
     * @return
     */
    public String getClassification(String assetId) {

        File assetFile = new File(directory + separator + assetId);
        try {
            if (assetFile.exists()) {

                return readAsset(assetId).describeClassification();

            }
        } catch (FileNotFoundException | JAXBException exception) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, exception);
        }
        return "Asset Do Not Exist";
    }

    /**
     * Realiza o commit das mudanças realizadas, se as alterações não estiverem
     * fora do padrão RAS. Recebe o nome do ativo e mensagem a ser salva.
     *
     * @param message
     * @return
     * @throws SAXException
     * @throws IOException
     * @throws JAXBException
     */
    public boolean saveChanges(String message) throws SAXException, IOException, JAXBException {
        try {

            return assistant.commit(message);
        } catch (GitAPIException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    /**
     * *
     * Método reponsável por alterar a classificação de um ativo de software.
     *
     * @param classification
     * @return
     */
    boolean setClassification(String assetId, ClassificationType classification) {
        boolean result = false;

        try {

            Asset asset = readAsset(assetId);

            for (Context c : classification.getContexts()) {
                asset.getClassification().getContexts().add(c);
            }

            for (DescriptionGroup d : classification.getDescriptionGroups()) {
                asset.getClassification().getDescriptionGroups().add(d);
            }

            result = javaToxml(asset, assetId);
        } catch (JAXBException | FileNotFoundException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

    public String getUsage(String assetId) {

        File assetFile = new File(directory + "/" + assetId);
        try {
            if (assetFile.exists()) {
                return readAsset(assetId).describeUsage();
            }
        } catch (FileNotFoundException | JAXBException exception) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, exception);
        }
        return "Asset Do Not Exist";
    }

    /**
     * *
     * Metodo responsável pela alteração do conjunto de caracteristicas
     * relacionadas a usabilidade do ativo de software.
     *
     * @param assetId
     * @param usage
     * @return
     */
    boolean setUsage(String assetId, UsageType usage) {
        boolean result = false;

        try {

            Asset asset = readAsset(assetId);

            for (ArtifactActivy a : usage.getArtifactActivities()) {
                asset.getUsage().getArtifactActivities().add(a);
            }

            for (ContextReference c : usage.getContextReferences()) {
                asset.getUsage().getContextReferences().add(c);
            }

            result = javaToxml(asset, assetId);
        } catch (JAXBException | FileNotFoundException ex) {

            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

    public String getRelatedAssets(String assetId) {
        File assetFile = new File(directory + separator + assetId);

        try {
            if (assetFile.exists()) {
                return readAsset(assetId).describeRelatedAssets();
            }

        } catch (FileNotFoundException | JAXBException exception) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, exception);
        }
        return "Asset Do Not Exist";
    }

    public List<RelatedAssetType> getXMLElement(String assetId) {

        File assetFile = new File(directory + separator + assetId);

        try {

            if (assetFile.exists()) {
                return readAsset(assetId).getRelatedAssetsList().getListOfRelatedAssets();
            }
        } catch (JAXBException | FileNotFoundException exception) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, exception);
        }
        return null;
    }

    boolean setRelatedAsset(String assetId, String relatedId) {
        boolean result = false;

        RelatedAssetType relatedAssetType = new RelatedAssetType();
        try {
            Asset asset = readAsset(assetId);
            Asset related = readAsset(relatedId);

            relatedAssetType.setId(related.getId());
            relatedAssetType.setName(related.getName());
            relatedAssetType.setReference(directory + separator + relatedId);
            //relatedAssetType.setRelationshipType(assetId);

            asset.getRelatedAssetsList().getListOfRelatedAssets().add(relatedAssetType);
            result = javaToxml(asset, assetId);
        } catch (JAXBException | FileNotFoundException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

    /**
     * Retorna o log de todo o repositório
     *
     * @return
     */
    String getLog() {

        try {
            return assistant.getLogs();
        } catch (GitAPIException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * *
     * Retorna o log de um determinado ativo ou de um artefato.
     *
     * @param nameAsset
     * @return
     */
    public String getLog(String nameAsset) {

        try {
            return assistant.getLogs(nameAsset);
        } catch (GitAPIException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Retorna uma lista com todos os nomes dos ativos do repositorio.
     *
     * @return
     */
    public Map<String, String> listAssets() {
        HidraDAO dao = new HidraDAO(localPath + separator + ".hidra" + separator);
        return dao.selectAll();
    }

    public String describeAssets() {

        StringBuilder stb = new StringBuilder("\n");
        stb.append("List of Assets: \n");

        for (File f : directory.listFiles()) {
            if (manifestExist(f)) {
                stb.append(f.getName()).append("\n");
            }
        }

        return stb.toString();
    }

    File downloadAsset(String assetId) throws FileNotFoundException {
        File file = new File(directory + assetId + ".zip");
        FileOutputStream fileOut = new FileOutputStream(file);

        int count = 0;

        try {
            URL url = new URL(Properties.getProperties().getProperty("Protocol"),
                    Properties.getProperties().getProperty("RemoteURI"), assetId);

            URLConnection con = url.openConnection();
            con.connect();

            do {
                count = con.getInputStream().read();
            } while (count != -1);
            fileOut.close();
            System.out.println("Arquivo baixado com sucesso");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return file;
    }

    /**
     * Remove um ativo do repositório.
     *
     * @param assetName - Recebe o nome do ativo a ser removido
     * @return retorna verdadeiro caso a operação seja bem sucedida.
     */
    public boolean removeAsset(String assetName) throws JAXBException, FileNotFoundException {
        Asset asset = readAsset(assetName);
        HidraDAO dao = new HidraDAO(localPath + separator + ".hidra" + separator);
        dao.delete(asset.getName(), asset.getId());
        File assetDir = new File(localPath + separator + assetName);
        assistant.remove(assetName);

        return deleteDir(assetDir);
    }

    private static boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        return dir.delete();
    }

    boolean isRepository(String directory) {
        return assistant.isRepositoryInitialized(directory);
    }

    /**
     * Atualiza o repositório remoto com as atualizações locais. Recebe usuario
     * e senha.
     *
     * @param user
     * @param password
     * @return
     */
    public String updateRepository(String user, String password) {

        try {
            assistant.push(user, password);
            return "Repository Updated ";
        } catch (GitAPIException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
             return ex.getMessage();
        }
       

    }

    /**
     * Sincroniza o repositório local com as alterações do repositório remoto.
     *
     * @param user
     * @param password
     * @return
     */
    public boolean synchronizeRepository(String user, String password) {

        try {
            return assistant.pull(user, password);
        } catch (GitAPIException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    /**
     * Configura usuário do repositorio com nome e email
     * @param name
     * @param email 
     */
    public void setUserRepo(String name, String email){
        try {
            assistant.setConfigurationUser(name, email);
        } catch (IOException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Retorna a configuração do usuário do repositório
     * @return 
     */
    public Map<String, String> getUserRepo(){
        return assistant.getConfigurationUser();
    }
    
    /**
     * Adiciona um repositório remoto ao repositório local
     * @param url 
     */
    public void setRemoteRepo(String url){
        setRemotePath(url);
        try {
            assistant.setConfigRemote(remotePath);
        } catch (IOException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
    }
    /**
     * Retorna o caminho do repositório remoto.
     * @return 
     */
    public String getRemoteRepo(){
        return assistant.getConfigRemote();
    }
    
    /**
     * Cria uma tag simples com um nome e uma mensagem.
     * @param name
     * @param message
     * @return 
     */
    public boolean createLightTag(String name, String message){
        return assistant.createLightTag(name, message);
    }
    
    
    /**
     * Cria uma tag com anotações, recebendo um nome e uma mensagem
     * @param name
     * @param message
     * @return 
     */
    public boolean createTagAnotted(String name, String message){
        return assistant.createAnnotatedTag(name, message);
    }
    
    /**
     * Mostra todas as tags criadas para o repositório.
     * @return 
     */
    public String listTags(){
        return assistant.listTags();
    }
    

}
