package ledes.hidra;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.Arrays;

import java.net.URL;
import java.net.URLConnection;

import java.util.ArrayList;
import java.util.List;
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
import ledes.hidra.asset.Asset;
import ledes.hidra.asset.ClassificationType;
import ledes.hidra.asset.SolutionType;
import ledes.hidra.asset.UsageType;
import ledes.hidra.core.GitFacade;
import ledes.hidra.core.ValidatorAssets;
import ledes.hidra.dao.HidraDAO;
import ledes.hidra.util.Configuration;
import ledes.hidra.util.Properties;
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

    protected boolean init() throws IOException, JAXBException {

       // createSchema();
        boolean ret = assistant.start(directory);
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
        return ret;

    }

    private void writerIgnoreFile() throws IOException{
        try (FileWriter ignoreFile = new FileWriter(localPath + separator+".gitignore")) {
            PrintWriter writer = new PrintWriter(ignoreFile);
            writer.printf(".hidra");
        }
        
    
    }
    /**
     * Clona um repositorio.
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
                return name.startsWith(Configuration.properties.getProperty("ManifestName")) && name.endsWith("ManifestExtension");
            }
        });

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

        File schemaFile = new File(localPath+separator+".hidra"+separator+"asset.xsd");
        //File schemaFile = new File(System.getProperty("user.home") + File.separator +"asset.xsd");
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

        if (!path.isDirectory()) {
            ret = false;
            throw new ValidationRuntimeException("It is not a directory: " + path);

        }
        if (!manifestExist(path)) {
            ret = false;
            throw new ValidationRuntimeException("File not found manisfest in: " + path);

        }
        if (validateAsset(assetPath)) {
            ret = false;
            throw new ValidationRuntimeException("Manifest not is valid");

        }
        if (!validateAsset(readAsset(assetName), assetPath)) {
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
                try {
                    HidraDAO dao = new HidraDAO(localPath);
                    if (dao.insertion(asset.getName(), asset.getId()));
                    return assistant.add(nameAsset);
                } catch (GitAPIException ex) {
                    Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
                    return false;
                }
            }

         else  {

           
            return false;

        }
    }

    
    /**
     * Procura no banco de dados do repositorio, se o ativo já está sendo monitorado.
     *
     * @param asset
     * @return verdadeiro caso o ativo esteja sendo monitorado.
     */
    private boolean findAsset(Asset asset) {

        HidraDAO dao = new HidraDAO(localPath);
        return dao.find(asset.getName(), asset.getId());

    }

    /**
     * Atualiza um ativo já monitorado a aréa de seleção. 
     * Caso o ativo ainda não esteja sendo monitorado retorna falso.
     * Caso as modificações não estejam de acordo com o padrão adotado, impede a atualização e retorna falso.
     * 
     * @param assetName - Recebe uma String com o nome do ativo a ser atualizado.
     * @return
     * @throws JAXBException
     * @throws FileNotFoundException
     * @throws ledes.hidra.Repository.ValidationRuntimeException
     * @throws SAXException
     * @throws IOException 
     */
    public boolean updateAsset(String assetName) throws JAXBException, FileNotFoundException, ValidationRuntimeException, SAXException, IOException {
        if(findAsset(readAsset(assetName))){
            if(validateAll(assetName, localPath, directory))
            {
                try {
                    return assistant.add(assetName);
                } catch (GitAPIException ex) {
                    Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        
        }
        else {
            throw new ValidationRuntimeException("Asset unmonitored, please add the asset to area selection with 'addAsset'");
           
        }
        return false;

    }

    private boolean javaToxml(Asset asset, String assetId) {
        boolean result = false;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Asset.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            //Marshal the employees list in console
            jaxbMarshaller.marshal(asset, System.out);

            //Marshal the employees list in file
            jaxbMarshaller.marshal(asset, new File(directory + "/" + assetId + "/rasset.xml"));
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
     * @throws javax.xml.bind.JAXBException
     */
    String getSolution(String assetId) {

        File assetFile = new File(directory + "/" + assetId);
        if (assetFile.exists()) {
            try {
                return readAsset(assetId).describeSolution();
            } catch (JAXBException | FileNotFoundException ex) {
                Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        return "Asset Do Not Exist";

    }

    /**
     * Responsavel por alterar a solucao que compoe um ativo de software
     *
     * @param assetId representa o id de um ativo de software.
     * @param solution representa a solucao que compoe o ativo de software.
     */
    boolean setSolutionType(String assetId, SolutionType solution) {
        boolean result = false;
        /*
         try {
         Asset asset = xmlToAsset(assetId);

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
         } catch (JAXBException e) {
         }
         */
        return result;
    }

    /**
     * Responsavel por Dado o id de um ativo, retornar ao usuario um objeto
     * Classification que representa a Lista de um conjunto de descritores para
     * classificação de ativos, bem como uma descrição do contexto para o qual o
     * ativo é relevante.
     *
     * @param assetId representa o id de um ativo de software.
     */
    String getClassification(String assetId) {

        File assetFile = new File(directory + "/" + assetId);
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
     * @param nameAsset
     * @return
     * @throws SAXException
     * @throws IOException
     * @throws JAXBException
     */
    public boolean saveChanges(String message, String nameAsset) throws SAXException, IOException, JAXBException {
        String assetPath = new File(localPath).getAbsolutePath() + "/" + nameAsset + "/";
        File assetFolder = new File(assetPath);
        if (assetFolder.isDirectory() && manifestExist(assetFolder) && validateAsset(assetPath) && validateAsset(readAsset(nameAsset), assetPath)) {
            try {
                return assistant.commit(message);
            } catch (GitAPIException ex) {
                Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            System.err.println("Erro na validação do ativo!"
                    + "\n Verifique se o nome do ativo está correto,"
                    + "\n ou se as alterações não ferem o padrão  adotado para o repositorio.");
        }
        return false;
    }

    boolean setClassification(ClassificationType classification) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    String getUsage(String assetId) {

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

    boolean setUsage(UsageType usage) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    List<Asset> getRelatedAssets(String assetId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    boolean setRelatedAsset(String assetId, String relatedId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    String getLog(String nameAsset) {
        String assetPath = new File(localPath).getAbsolutePath() + "/" + nameAsset + "/";
        try {
            return assistant.getLogs();
        } catch (GitAPIException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Retorna uma lista com todos os nomes dos ativos do repositorio. PS: deve
     * retornar apenas os arquivos adicionados a area de seleção Será resolvido
     * com o indice.
     *
     * @return
     */
    List<String> listAssets() {
        File dir = new File(localPath);
        List<String> assets = new ArrayList<>();
        assets.addAll(Arrays.asList(dir.list()));
        return assets;
    }

    String describeAssets() {

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
    boolean removeAsset(String assetName) {
        return assistant.remove(assetName);
    }

    boolean isRepository(String directory) {
        return assistant.isRepositoryInitialized(directory);
    }

    /**
     * dado id devolve ativo da lista de ativos do repositorio.
     */
    private Asset findAsset(String assetId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Atualiza o repositório remoto com as atualizações locais. Recebe usuario
     * e senha.
     *
     * @param user
     * @param password
     * @return
     */
    public boolean updateRepository(String user, String password) {

        try {
            return assistant.push(user, password);
        } catch (GitAPIException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;

    }

    /**
     * Sincroniza o repositório local com as alterações do repositório remoto.
     *
     * @return
     */
    public boolean synchronizeRepository() {

        try {
            return assistant.pull();
        } catch (GitAPIException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

}
