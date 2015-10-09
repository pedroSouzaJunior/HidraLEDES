package ledes.hidra;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;

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
import ledes.hidra.asset.RelatedAssets;
import ledes.hidra.asset.SolutionType;
import ledes.hidra.asset.UsageType;
import ledes.hidra.core.GitFacade;
import ledes.hidra.core.ValidatorAssets;
import ledes.hidra.dao.HidraDAO;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.errors.IncorrectObjectTypeException;
import org.eclipse.jgit.errors.RevisionSyntaxException;
import org.xml.sax.SAXException;

/**
 * Esta classe é responsável por gerenciar um repositório central (servidor).
 *
 * @author Danielli Urbieta e Pedro Souza Junior
 */
public class Repository {

    private final static String separator = File.separator;
    private final static String manifest = "rasset.xml";
    private List<String> validsAssets;
    private List<String> inValidsAssets;
   
    /**
     * @param String localPath - Define o caminho completo para o repositório
     * local.
     */
    private final String localPath;
    /**
     * @param String remotePath - Define a URL ou o caminho do repositório
     * remoto.
     */
    private String remotePath;

    /**
     * @param File - Diretório do repositório
     */
    private final File directory;

    /**
     * Responsável pela operações Git
     */
    private final GitFacade assistant;

    /**
     * Lista de Exceções
     */
    private List<String> exceptionList;

    /**
     * Construtor de um repositório com um parametro. É necessário chamar a
     * função start após a instanciação de um repositório para criar um novo
     * repositório.
     *
     * @param localPath - Caminho completo para o novo repositório
     */
    public Repository(String localPath) {
        super();
        this.localPath = localPath;
        this.directory = new File(localPath);
        assistant = new GitFacade(localPath);
        this.exceptionList = new ArrayList<>();
    }

    /**
     *
     * Construtor da classe com dois paramêtros. É necessário chamar a função
     * start após a instanciação de um repositório para criar um novo
     * repositório. Define um repositório remoto para o repositório local.
     *
     * @param localPath - Caminho completo do repositório local.
     * @param remotePath - URL ou caminho do repositório remoto.
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
            new File(localPath + separator + ".hidra" + separator + ".temp").mkdir();
            new File(localPath + separator + ".hidra" + separator + ".temp" + separator + ".uploads").mkdir();
            new File(localPath + separator + ".hidra" + separator + ".temp" + separator + ".downloads").mkdir();
            HidraDAO dao = new HidraDAO(localPath + separator + ".hidra" + separator);
            dao.connection();
            createSchema();
//           writerIgnoreFile();
            try {
                assistant.add(".hidra");
                assistant.commit("Repository Created");
            } catch (GitAPIException ex) {
                Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return ret;

    }
    
    
    /**
     * Inizializa um repositorio "bare" para ser utilizado no servidor com arquivos de configuração Git e Hidra
     *
     * @param initialized
     * @return
     * @throws IOException
     * @throws JAXBException
     */
    protected boolean initBare(boolean initialized) throws IOException, JAXBException {

        boolean ret = assistant.startBare(directory);

        if (!initialized) {

            new File(localPath + separator + ".hidra").mkdir();
            new File(localPath + separator + ".hidra" + separator + ".temp").mkdir();
            new File(localPath + separator + ".hidra" + separator + ".temp" + separator + ".uploads").mkdir();
            new File(localPath + separator + ".hidra" + separator + ".temp" + separator + ".downloads").mkdir();
            HidraDAO dao = new HidraDAO(localPath + separator + ".hidra" + separator);
            dao.connection();
            createSchema();
//           writerIgnoreFile();
            try {
                assistant.add(".hidra");
                assistant.commit("Repository Created");
            } catch (GitAPIException ex) {
                Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return ret;

    }

    /**
     * Cria um arquivo .gitgnore para que o repositorio GIT não monitore
     * mudanças no diretorio .hidra.
     *
     * @throws IOException
     */
    private void writerIgnoreFile() throws IOException {
        try (FileWriter ignoreFile = new FileWriter(localPath + separator + ".gitignore")) {
            PrintWriter writer = new PrintWriter(ignoreFile);
            //writer.printf(".hidra");
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

    /**
     * Clona um repositório com autentificação.
     *
     * @param user
     * @param password
     * @return
     */
    protected boolean cloneRepository(String user, String password) {

        try {
            return  assistant.cloneRepository(directory, remotePath, user, password);

        } catch (GitAPIException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);

            return false;
        }

    }

    /**
     * Verifica se o diretório é um repositório Hidra.
     *
     * @return
     */
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
     * Returna caminho do repositório
     *
     * @return localPath
     */
    public String getLocalPath() {
        return localPath;
    }

    /**
     * Retorna caminho do repositório remoto.
     *
     * @return
     */
    public String getRemotePath() {
        return remotePath;
    }

    /**
     * Define um caminho do repositório remoto
     *
     * @param remotePath
     */
    public void setRemotePath(String remotePath) {
        this.remotePath = remotePath;
    }

    /**
     * Pedro coloca aqui o que retorna
     *
     * @return
     */
    public List<String> getExceptionList() {
        return exceptionList;
    }

    public void setExceptionList(List<String> exceptionList) {
        this.exceptionList = exceptionList;
    }

    /**
     * Responsável por deletar um repositório
     */
    public void removeRepository() {

        if (directory.exists()) {
            try {
                deleteFolder(directory);
            } catch (IOException e) {
                System.exit(0);
            }
        }

    }

    /**
     *
     * @param file
     * @throws IOException
     */
    private void deleteFolder(File file) throws IOException {

        if (file.isDirectory()) {
            if (file.list().length == 0) {
                file.delete();
            } else {
                String files[] = file.list();
                for (String temp : files) {

                    File fileDelete = new File(file, temp);

                    deleteFolder(fileDelete);
                }
                if (file.list().length == 0) {
                    file.delete();
                }
            }
        } else {
            file.delete();
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
    private void createSchema() throws JAXBException, IOException {

        final File baseDir = new File(localPath + separator + ".hidra");

        class MySchemaOutputResolver extends SchemaOutputResolver {

            @Override
            public Result createOutput(String namespaceUri, String suggestedFileName) throws IOException {
                return new StreamResult(new File(baseDir, "asset.xsd"));
            }
        }

        JAXBContext context = JAXBContext.newInstance(Asset.class);
        context.generateSchema(new MySchemaOutputResolver());

        
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
    private Asset readAsset(String nameAsset) throws JAXBException, FileNotFoundException {
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
    private boolean manifestExist(File path) {
        File manifest = new File(path+separator+"rasset.xml");
        return manifest.exists()&&!manifest.isDirectory();
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
    protected boolean validateAsset(String assetPath) throws SAXException, IOException {

        File schemaFile = new File(localPath + separator + ".hidra" + separator + "asset.xsd");
        Source xmlFile = new StreamSource(new File(assetPath + manifest));
        SchemaFactory schemaFactory = SchemaFactory
                .newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = schemaFactory.newSchema(schemaFile);
        Validator validator = schema.newValidator();
        try {
            validator.validate(xmlFile);
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
        validator.isValidAsset(asset);
        validsAssets = validator.getValidAssets();
        inValidsAssets = validator.getInvalidAssets();

        return validator.getInvalidAssets().isEmpty();
        // return validator.isValidAsset(asset);
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
        Asset asset;

        if (!path.isDirectory()) {
            ret = false;
            throw new ValidationRuntimeException("It is not a directory: " + path);

        }
        if (!manifestExist(path)) {
            ret = false;
            throw new ValidationRuntimeException("File not found manisfest in: " + path);

        }
        asset = readAsset(assetName);
        if (!asset.getName().equalsIgnoreCase(assetName)) {
            ret = false;
            throw new ValidationRuntimeException("The asset name does not match the name described in the manifest.");

        }

        if (!validateAsset(assetPath + File.separator)) {
            ret = false;
            throw new ValidationRuntimeException("Manifest not is valid");

        }
        if (!validateAsset(asset, assetPath)) {
            ret = false;
            StringBuilder errors = new StringBuilder();
            errors.append("Active structure does not match the manifest file, verify: \n");
            for (String s : inValidsAssets) {
                errors.append("\n").append(s);
            }
            throw new ValidationRuntimeException(errors.toString());

        }

        return ret;
    }

    /**
     * Reponsavel por adicionar um Ativo de software ao repositorio. A operacao
     * consiste de uma validação do ativo, antes de efetuar a adição do mesmo ao
     * repositório. Caso o ativo esteja condizente com o padrao RAS, ele poderá
     * entao ser adicionado ao repositório.
     *
     * @param nameAsset
     * @return
     * @throws org.xml.sax.SAXException
     * @throws java.io.IOException
     * @throws javax.xml.bind.JAXBException
     *
     */
    public boolean addAsset(String nameAsset) throws SAXException, IOException, JAXBException, ValidationRuntimeException {

        String assetPath = new File(localPath).getAbsolutePath() + File.separator + nameAsset;
        File assetFolder = new File(assetPath);
        Asset asset = readAsset(nameAsset);
        boolean result = true;

        if (validateAll(nameAsset, assetPath, assetFolder)) {

            if (!findAsset(asset)) {

                HidraDAO dao = new HidraDAO(localPath + separator + ".hidra" + separator);
                result = dao.insertion(asset.getName(), asset.getId());

            }

            try {

                assistant.add(nameAsset + separator + manifest);
                for (String s : validsAssets) {
                    if (!assistant.add(nameAsset + s)) {

                        return false;
                    }

                    System.out.println("Add: " + nameAsset + s);

                }

                return assistant.add(".hidra") && result;
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
     * @param asset - Recebe como parâmetro um objeto Asset
     * @return verdadeiro caso o ativo esteja sendo monitorado.
     */
    public boolean findAsset(Asset asset) {

        HidraDAO dao = new HidraDAO(localPath + separator + ".hidra" + separator);
        return dao.find(asset.getName(), asset.getId());

    }

    /**
     * Procura no banco de dados do repositorio, se o ativo já está sendo
     * monitorado.
     *
     * @param assetName - Recebe como parâmetro uma String com o nome do ativo
     * @return verdadeiro caso o ativo esteja sendo monitorado.
     */
    public boolean findAsset(String assetName) {

        HidraDAO dao = new HidraDAO(localPath + separator + ".hidra" + separator);
        return dao.find(assetName);
    }

    /**
     * Retorna uma lista com o estado atual do repositório.
     *
     * @return
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
     * Metodo utilizado para atualização do arquivo rasset.xml utilizado nos
     * métodos Sets.
     *
     * @param asset
     * @param assetId
     * @return
     */
    private boolean updateRasset(Asset asset, String assetId) {
        boolean result = false;
        try {
            File rasset = new File(directory + separator + assetId + separator + "rasset.xml");

            JAXBContext jaxbContext = JAXBContext.newInstance(Asset.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(asset, rasset);

            result = true;
        } catch (JAXBException e) {
            System.err.println("error");
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
     * Responsavel por retornar ao usuario a forma representativa dos artefatos
     * que fazem parte do ativo
     *
     * @param assetId que representa o id de um ativo de software.
     * @return
     */
    public SolutionType describeSolution(String assetId) {

        File assetFile = new File(directory + separator + assetId);
        if (assetFile.exists()) {
            try {
                return readAsset(assetId).getSolution();
            } catch (JAXBException | FileNotFoundException ex) {
                Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        return null;

    }

    /**
     * Responsavel por alterar a solucao que compõe um ativo de software
     *
     * @param assetId representa o id de um ativo de software.
     * @param solution representa a solução que compõe o ativo de software.
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

            result = updateRasset(asset, assetId);
        } catch (JAXBException | FileNotFoundException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

    /**
     * Responsavel por Dado o id de um ativo, retornar ao usuário um objeto
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
        return null;
    }

    /**
     * Responsavel por Dado o id de um ativo, retornar ao usuário um objeto
     * Classification que representa a Lista de um conjunto de descritores para
     * classificação de ativos, bem como uma descrição do contexto para o qual o
     * ativo é relevante.
     *
     * @param assetId representa o id de um ativo de software.
     * @return
     */
    public ClassificationType describeClassification(String assetId) {

        File assetFile = new File(directory + separator + assetId);
        try {
            if (assetFile.exists()) {

                return readAsset(assetId).getClassification();

            }
        } catch (FileNotFoundException | JAXBException exception) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, exception);
        }
        return null;
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

            result = updateRasset(asset, assetId);
        } catch (JAXBException | FileNotFoundException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

    /**
     * Metodo responsável por obter o Usage de um ativo informado.
     *
     * @param assetId
     * @return
     */
    public String getUsage(String assetId) {

        File assetFile = new File(directory + "/" + assetId);
        try {
            if (assetFile.exists()) {
                return readAsset(assetId).describeUsage();
            }
        } catch (FileNotFoundException | JAXBException exception) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, exception);
        }
        return null;
    }

    /**
     * Metodo responsável por obter o Usage de um ativo informado. O retorno do
     * método é do tipo UsageType para que possa ser convertido em XML.
     *
     * @param assetId
     * @return
     */
    public UsageType describeUsage(String assetId) {

        File assetFile = new File(directory + "/" + assetId);
        try {
            if (assetFile.exists()) {
                return readAsset(assetId).getUsage();
            }
        } catch (FileNotFoundException | JAXBException exception) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, exception);
        }
        return null;
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

            result = updateRasset(asset, assetId);
        } catch (JAXBException | FileNotFoundException ex) {

            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

    /**
     * Metodo responsável por obter informações relacionados aos ativos
     * relacionados a um determinado ativo informado.
     *
     * @param assetId
     * @return
     */
    public String getRelatedAssets(String assetId) {
        File assetFile = new File(directory + separator + assetId);

        try {
            if (assetFile.exists()) {
                return readAsset(assetId).describeRelatedAssets();
            }

        } catch (FileNotFoundException | JAXBException exception) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, exception);
        }
        return null;
    }

    /**
     * Metodo responsável por obter informações relacionados aos ativos
     * relacionados a um determinado ativo informado. O retorno é do tipo
     * RelatedAssets para que possa ser convertido em XML.
     *
     * @param assetId
     * @return
     */
    public RelatedAssets describeRelatedAssets(String assetId) {
        File assetFile = new File(directory + separator + assetId);

        try {
            if (assetFile.exists()) {
                return readAsset(assetId).getRelatedAssetsList();
            }

        } catch (FileNotFoundException | JAXBException exception) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, exception);
        }
        return null;
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
            result = updateRasset(asset, assetId);
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
     * Retorna o log de um determinado ativo. Recebe como parâmetro o nome do
     * ativo.
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
     * Retorna uma lista com todos os nomes dos ativos do repositório.
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

    /**
     * Remove um ativo do repositório.
     *
     * @param assetName - Recebe o nome do ativo a ser removido
     * @return retorna verdadeiro caso a operação seja bem sucedida.
     * @throws javax.xml.bind.JAXBException
     * @throws java.io.FileNotFoundException
     */
    public boolean removeAsset(String assetName) throws JAXBException, FileNotFoundException {
        Asset asset = readAsset(assetName);
        HidraDAO dao = new HidraDAO(localPath + separator + ".hidra" + separator);
        dao.delete(asset.getName(), asset.getId());
        try {
            return assistant.remove(assetName) ? assistant.add(".hidra") : false;
        } catch (GitAPIException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

    /**
     * Atualiza o repositório remoto com as atualizações locais. Recebe usuaŕio
     * e senha.
     *
     * @param user
     * @param password
     * @return
    
     */
    public String updateRepository(String user, String password)  {

        try {
            assistant.push(user, password);
            return "Repository Updated";
        } catch (GitAPIException | IOException ex) {
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
     * Realiza o download de um ativo.
     *
     * @param format - Formato do arquivo : zip, rar.
     * @param fileDest - Destino do download 
     * @param asset - nome do ativo.
     * @return 
     */

    public boolean retrieveAsset(String format, String fileDest, String asset){
        
        try {
          return  assistant.archive(format, "master", fileDest, asset);
        } catch (IncorrectObjectTypeException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RevisionSyntaxException | GitAPIException | IOException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    
    /**
     * Configura usuário do repositório com nome e email
     *
     * @param name
     * @param email
     */
    protected void setUserRepo(String name, String email) {
        try {
            assistant.setConfigurationUser(name, email);
        } catch (IOException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Retorna a configuração do usuário do repositório
     *
     * @return
     */
    public Map<String, String> getUserRepo() {
        return assistant.getConfigurationUser();
    }

    /**
     * Adiciona um repositório remoto ao repositório local
     *
     * @param url
     */
    public void setRemoteRepo(String url) {
        setRemotePath(url);
        try {
            assistant.setConfigRemote(remotePath);
        } catch (IOException | URISyntaxException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Retorna o caminho do repositório remoto.
     *
     * @return
     */
    public String getRemoteRepo() {
        return assistant.getConfigRemote();
    }

    /**
     * Cria uma tag simples com um nome e uma mensagem.
     *
     * @param name
     * @param message
     * @return
     */
    public boolean createLightTag(String name, String message) {
        return assistant.createLightTag(name, message);
    }

    /**
     * Cria uma tag com anotações, recebendo um nome e uma mensagem
     *
     * @param name
     * @param message
     * @return
     */
    public boolean createTagAnotted(String name, String message) {
        return assistant.createAnnotatedTag(name, message);
    }

    /**
     * Mostra todas as tags criadas para o repositório.
     *
     * @return
     */
    public String listTags() {
        return assistant.listTags();
    }
    
    /**
     * Cria um novo branch no repositório.
     * @param branchName
     * @return 
     */
    public String createBranch(String branchName){
        return assistant.createBranch(branchName);
    }
    
    /**
     * Mostra todos os branches criados pelo repositório e o branch atual de trabalho.
     * @return 
     */
    public boolean showBranches(){
    
        return assistant.showBranches();
    }
    /**
     * Troca do branch atual para outro branch
     * @param branchName - Recebe o nome do branch que se deseja ir.
     * @return 
     */
    public boolean checkoutBranch(String branchName){
    
        return assistant.checkout(branchName);
    }
    
    /**
     * Recebe um vetor de Strings com o nome dos branches que se deseja apagar
     * @param branchName
     * @return 
     */
    public boolean deleteBranch(String[] branchName){
    
        try {
            return assistant.deleteBranch(branchName);
        } catch (GitAPIException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    /**
     * Realiza a junção  do branch atual de trabalho com outro branch.
     * @param branchName - Recebe o nome do branch com o qual realizará a mesclagem.
     * @return 
     */
    public boolean merge(String branchName){
    
        try {
           
            return assistant.merge(branchName);
        } catch (IOException | GitAPIException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
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
}
