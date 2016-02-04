package ledes.hidra.rest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.xml.bind.JAXBException;
import ledes.hidra.Hidra;
import ledes.hidra.asset.Activity;
import ledes.hidra.asset.ArtifactActivy;
import ledes.hidra.asset.Artifact;
import ledes.hidra.asset.ClassificationType;
import ledes.hidra.asset.Context;
import ledes.hidra.asset.ContextReference;
import ledes.hidra.asset.DescriptionGroup;
import ledes.hidra.asset.RelatedAssetType;

import ledes.hidra.asset.SolutionType;
import ledes.hidra.asset.UsageType;
import ledes.hidra.asset.VariabilityPointBinding;
import ledes.hidra.exception.DataNotFoundException;
import ledes.hidra.resources.Zipper;
import ledes.hidra.rest.model.Activities;
import ledes.hidra.rest.model.ArtifactType;
import ledes.hidra.rest.model.ArtifactActivys;
import ledes.hidra.rest.model.Classification;
import ledes.hidra.rest.model.Attribute;
import ledes.hidra.rest.model.ContextReferences;
import ledes.hidra.rest.model.Contexts;
import ledes.hidra.rest.model.DescriptionGroups;
import ledes.hidra.rest.model.RelatedAssets;
import ledes.hidra.rest.model.ResultMessage;
import ledes.hidra.rest.model.Solution;
import ledes.hidra.rest.model.Usage;
import ledes.hidra.rest.model.Variability;

/**
 * Classe Services - responsavel por transcrever as funcionalidades do projeto
 * Hidra na forma de Web Services. Consome XML padronizado (Attribute) capaz de
 * comportar informações necessarias para artifact execucao dos metodos da
 * biblioteca Hidra.
 *
 * @version 1.0
 * @author pedro e danielli
 */
@Path("/services")
public class Services {

    private static final String separator = File.separator;
    private static final String extension = ".zip";
    private static final String repositoryRemoteBase = "/var/www/hidra.com/hidra/";
    private static final String DOWNLOAD_PATH_TEMP = separator + ".hidra" + separator + ".temp" + separator + ".downloads";
    private static final String DEFAULT_MESSAGE_SUBMIT = "Sending changes to repository";

    private Hidra hidra;

    public Hidra getHidra() {
        return hidra;
    }

    /**
     * Servico responsavel pela inicializacao de um repositorio Hidra. Consome
     * XML padronizado (Attribute) contendo o caminho absoluto de onde deve ser
     * criado e inicializado o repositorio hidra.
     *
     * @param command
     * @return Response Object
     * @throws java.io.IOException
     */
    @POST
    @Path("/startRepository")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public Response startRepository(Attribute command) throws IOException {

        hidra = new Hidra();
        ResultMessage result = new ResultMessage();
        String path = command.getRepositoryPath();
        hidra.getHidraProperties().setProperty("localPath", path);

        if (path.isEmpty()) {
            throw new DataNotFoundException("The absolute path of the repository has not been set");
        }
        if (new File(path).isDirectory()) {
            throw new IOException("Repository with this name already exists");
        }

        try {

            hidra.startRepository();
            result.setMessage("repository was created in : " + path);
            result.setStatusMessage(Status.CREATED);

        } catch (IOException ex) {
            throw new DataNotFoundException(ex.getMessage());
        } catch (JAXBException ex) {
            throw new DataNotFoundException(ex.getMessage());
        }

        return Response
                .status(Status.CREATED)
                .entity(result).build();

    }

    /**
     * Servico responsavel por adicionar ativos artifact area de monitoramento
     * do repositorio. Consome XML padronizado (Attribute) contendo o nome do
     * ativo que se deseja monitorar.
     *
     * @param command
     * @return
     * @throws IOException
     */
    @POST
    @Path("/addasset")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public Response addAsset(Attribute command) throws Exception {

        Hidra hidra;
        ResultMessage result = new ResultMessage();
        
        String path = command.getRepositoryPath();
        String assetName = command.getAssetName();

        if (path.isEmpty() || assetName.isEmpty()) {
            throw new DataNotFoundException("The absolute path of the repository Or Asset Name has not been set");
        }
        if (!new File(path).exists()) {
            throw new IOException("Could not find the informed repository");
        }
        if (!new File(path + separator + assetName).exists()) {
            throw new IOException("Asset informed was not found");
        }

        hidra = new Hidra(path);
        if (hidra.addAsset(assetName)) {
            result.setMessage("The asset: " + assetName + " has been added to the repository");
            result.setStatusMessage(Status.OK);
            return Response
                    .status(Status.OK)
                    .entity(result).build();
        }

        throw new Exception("File already monitored. You might want to do \"updateAsset\"");
    }

    /**
     * Servico responsavel por gravar as alteracoes no repositorio. Consome XML
     * padronizado (Attribute) contendo o caminho absoluto do repositorio que se
     * deseja utilizar.
     *
     * @param command
     * @return
     * @throws java.io.IOException
     */
    @POST
    @Path("/save")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public Response save(Attribute command) throws IOException, Exception {

        Hidra hidra;
        ResultMessage result = new ResultMessage();
        String path = command.getRepositoryPath();
        String message = command.getSubmitMessage().isEmpty() ? DEFAULT_MESSAGE_SUBMIT : command.getSubmitMessage();

        if (path.isEmpty()) {
            throw new DataNotFoundException("The absolute path of the repository has not been set");
        }
        if (!new File(path).exists()) {
            throw new IOException("Could not find the informed repository");
        }

        hidra = new Hidra(path);
        if (hidra.save(command.getSubmitMessage())) {
            result.setMessage(message);
            result.setStatusMessage(Status.OK);
            return Response
                    .status(Status.OK)
                    .entity(result).build();
        }

        throw new Exception("Could not commit changes to the repository");
    }

    /**
     * Servico responsavel por remover um ativo de software armezenado no
     * repositorio. Consome um XML padronizado (Attribute) contendo o caminho
     * absoluto do repositorio que se deseja utilizar em conjunto com o nome do
     * ativo que se deseja remover.
     *
     * @param command
     * @return
     * @throws java.io.IOException
     */
    @POST
    @Path("/removeAsset")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public Response remove(Attribute command) throws IOException, Exception {

        ResultMessage result = new ResultMessage();
        String path = command.getRepositoryPath();
        String assetName = command.getAssetName();

        if (path.isEmpty() || assetName.isEmpty()) {
            throw new DataNotFoundException("The absolute path of the repository Or Asset Name has not been set");
        }
        if (!new File(path).exists()) {
            throw new IOException("Could not find the informed repository");
        }
        if (!new File(path + separator + assetName).exists()) {
            throw new IOException("Asset informed was not found");
        }

        hidra = new Hidra(path);
        if (hidra.removeAsset(assetName)) {
            result.setMessage("Asset removed Success");
            result.setStatusMessage(Status.OK);
            return Response
                    .status(Status.OK)
                    .entity(result).build();
        }

        throw new Exception("Could not remove Asset");
    }

    /**
     * Servico responsavel por criar uma copia local de um repositorio. Consome
     * um XML padronizado (Attribute) contendo o caminho absoluto do repositorio
     * que se deseja clonar e tambem o caminho absoluto do diretorio destino.
     *
     * @param command
     * @return
     * @throws java.io.IOException
     */
    @POST
    @Path("/startSynchronizedRepository")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public Response startSynchronizedRepository(Attribute command) throws IOException, Exception {

        Hidra hidra = new Hidra();
        ResultMessage result = new ResultMessage();

        String remotePath = command.getRemoteRepository();
        String cloneToDirectory = command.getRepositoryLocalCopy();

        hidra.getHidraProperties().setProperty("localPath", cloneToDirectory);
        hidra.getHidraProperties().setProperty("remotePath", remotePath);

        if (remotePath.isEmpty() || cloneToDirectory.isEmpty()) {
            throw new DataNotFoundException("The absolute path of the remote repository Or the destiny has not been set");
        }
        if (new File(cloneToDirectory).exists() && new File(cloneToDirectory).listFiles().length != 0) {
            throw new IOException("Destination already exists");
        }

        if (hidra.startSynchronizedRepository()) {
            result.setMessage("Repository successfully cloned in " + cloneToDirectory);
            result.setStatusMessage(Status.OK);
            return Response
                    .status(Status.OK)
                    .entity(result).build();
        }

        throw new Exception("Could not clone remote repository");
    }

    /**
     * Servico responsavel por criar uma copia local de um repositorio remoto
     * atravez de autenticacao. Consome um XML padronizado (Attribute) contendo
     * artifact URL do repositorio que se deseja clonar e tambem o caminho
     * absoluto do diretorio destino e usuario e senha para acesso ao
     * repositorio remoto.
     *
     * @param command
     * @return
     * @throws java.io.IOException
     */
    @POST
    @Path("/startSynchronizedRepositoryAuthentication")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public Response startSynchronizedRepositoryAuthentication(Attribute command) throws IOException, Exception {

        Hidra hidra = new Hidra();
        ResultMessage result = new ResultMessage();

        String user = command.getUser();
        String password = command.getPassword();
        String remotePath = command.getRemoteRepository();
        String cloneToDirectory = command.getRepositoryLocalCopy();

        hidra.getHidraProperties().setProperty("user", user);
        hidra.getHidraProperties().setProperty("password", password);
        hidra.getHidraProperties().setProperty("localPath", cloneToDirectory);
        hidra.getHidraProperties().setProperty("remotePath", remotePath);

        if (remotePath.isEmpty() || cloneToDirectory.isEmpty()) {
            throw new DataNotFoundException("The absolute path of the remote repository Or the destiny has not been set");
        }
        if (user.isEmpty() || password.isEmpty()) {
            throw new DataNotFoundException("User or Password has not been set");
        }
        if (new File(cloneToDirectory).exists() && new File(cloneToDirectory).listFiles().length != 0) {
            throw new IOException("Destination already exists");
        }

        if (hidra.startSynchronizedRepositoryAuthentification()) {
            result.setMessage("Repository successfully cloned in " + cloneToDirectory);
            result.setStatusMessage(Status.OK);
            return Response
                    .status(Status.OK)
                    .entity(result).build();
        }

        throw new Exception("Could not clone remote repository: User or Password incorrect");

    }

    /**
     * Servico responsavel por atualizar repositorio remoto com as modificacoes
     * e informacoes do repositorio local. Consome XML padronizado (Attribute)
     * contendo o caminho absoluto do repositorio local.
     *
     * @param command
     * @return
     * @throws java.lang.Exception
     */
    @POST
    @Path("/sendUpdates")
    @Consumes(MediaType.APPLICATION_XML)
    public Response sendUpdates(Attribute command) throws Exception {

        Hidra hidra;
        ResultMessage result = new ResultMessage();
        hidra = new Hidra();
        
        String update;
        String user = command.getUser();
        String password = command.getPassword();
        String path = command.getRepositoryPath();
        
        hidra.getHidraProperties().setProperty("localPath", path);
        hidra.getHidraProperties().setProperty("user", user);
        hidra.getHidraProperties().setProperty("password", password);
        
        

        if (path.isEmpty()) {
            throw new DataNotFoundException("The absolute path of the repository has not been set");
        }
        if (!new File(path).exists()) {
            throw new IOException("Could not find the informed repository");
        }
        if (user.isEmpty() || password.isEmpty()) {
            throw new DataNotFoundException("User or Password has not been set");
        }

        
        update = hidra.sendUpdates();

        if (update.equals("Repository Updated")) {
            result.setMessage("Repository Updated");
            result.setStatusMessage(Status.OK);
            return Response
                    .status(Status.OK)
                    .entity(result).build();
        }

        throw new Exception("Could not update changes to the repository");
    }

    /**
     * Servico responsavel por atualizar o repositorio local com modificacoes
     * que foram aplicadas ao seu respectivo repositorio remoto. Consome XML
     * padronizado (Attribute) que contem o caminho absoluto do repositorio que
     * se deseja atualizar. O repositorio deve possuir ligacao a um repositorio
     * remoto
     *
     * @param command
     * @return
     */
    @POST
    @Path("/receiveUpdates")
    @Consumes(MediaType.APPLICATION_XML)
    public Response receiveUpdates(Attribute command) throws Exception {

        Hidra hidra;
        hidra = new Hidra();
        ResultMessage result = new ResultMessage();
        
        String user = command.getUser();
        String password = command.getPassword();
        String path = command.getRepositoryPath();
        
        hidra.getHidraProperties().setProperty("localPath", path);
        hidra.getHidraProperties().setProperty("user", user);
        hidra.getHidraProperties().setProperty("password", password);

        if (path.isEmpty()) {
            throw new DataNotFoundException("The absolute path of the repository has not been set");
        }
        if (!new File(path).exists()) {
            throw new IOException("Could not find the informed repository");
        }

        

        if (hidra.receiveUpdates()) {
            result.setMessage("Repository Updated");
            result.setStatusMessage(Status.OK);
            return Response
                    .status(Status.OK)
                    .entity(result).build();
        }

        throw new Exception("Could not update");
    }

    /**
     * Servico responsavel por obter os dados referentes artifact um Solution de
     * um ativo de software armezenado no repositorio. Consome um XML
     * padronizado (Attribute) contendo o caminho absoluto do repositorio que se
     * deseja utilizar em conjunto com o nome do ativo que se busca.
     *
     * @param command
     * @return
     */
    @POST
    @Path("/getSolution")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public Response getSolution(Attribute command) throws IOException {

        Hidra hidra;
        Solution solution = new Solution();

        String assetName = command.getAssetName();
        String path = command.getRepositoryPath();

        if (path.isEmpty() || assetName.isEmpty()) {
            throw new DataNotFoundException("The absolute path of the repository Or Asset Name has not been set");
        }
        if (!new File(path).exists()) {
            throw new IOException("Could not find the informed repository");
        }

        hidra = new Hidra(path);

        SolutionType solutionType = hidra.describeSolution(assetName);
        if (solutionType != null) {

            List<Artifact> artifacts = solutionType.getArtifacts().getArtifact();
            List<Artifact> design = solutionType.getDesign().getArtifact();
            List<Artifact> implementation = solutionType.getImplementation().getArtifact();
            List<Artifact> requirements = solutionType.getRequirements().getArtifact();
            List<Artifact> tests = solutionType.getTest().getArtifact();

            for (Artifact artifact : artifacts) {
                solution.getArtifacts()
                        .add(new ArtifactType(
                                        artifact.getName(),
                                        artifact.getType(),
                                        artifact.getReference(),
                                        artifact.getId(),
                                        artifact.getVersion()));
            }
            for (Artifact artfact : design) {
                solution.getDesign()
                        .add(new ArtifactType(
                                        artfact.getName(),
                                        artfact.getType(),
                                        artfact.getReference(),
                                        artfact.getId(),
                                        artfact.getVersion()));
            }
            for (Artifact artifact : implementation) {
                solution.getImplementation()
                        .add(new ArtifactType(
                                        artifact.getName(),
                                        artifact.getType(),
                                        artifact.getReference(),
                                        artifact.getId(),
                                        artifact.getVersion()));
            }

            for (Artifact artifact : requirements) {
                solution.getRequirements()
                        .add(new ArtifactType(
                                        artifact.getName(),
                                        artifact.getType(),
                                        artifact.getReference(),
                                        artifact.getId(),
                                        artifact.getVersion()));
            }
            for (Artifact artifact : tests) {
                solution.getTest()
                        .add(new ArtifactType(
                                        artifact.getName(),
                                        artifact.getType(),
                                        artifact.getReference(),
                                        artifact.getId(),
                                        artifact.getVersion()));
            }

            return Response
                    .status(Status.OK)
                    .entity(solution).build();

        }
        throw new DataNotFoundException("Could not return to the Asset solution ");
    }

    /**
     * Servico responsavel por obter os dados referentes artifact Classification
     * de um ativo de software armezenado no repositorio. Consome um XML
     * padronizado (Attribute) contendo o caminho absoluto do repositorio que se
     * deseja utilizar em conjunto com o nome do ativo que se busca.
     *
     * @param command
     * @return
     */
    @POST
    @Path("/getClassification")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public Response getClassification(Attribute command) throws IOException {

        Hidra hidra;
        Classification classification = new Classification();
        String assetName = command.getAssetName();
        String path = command.getRepositoryPath();
        ResultMessage result = new ResultMessage();

        if (path.isEmpty() || assetName.isEmpty()) {
            throw new DataNotFoundException("The absolute path of the repository Or Asset Name has not been set");
        }
        if (!new File(path).exists()) {
            throw new IOException("Could not find the informed repository");
        }

        hidra = new Hidra(path);

        ClassificationType classificationType = hidra.describeClassification(assetName);
        if (classificationType != null) {

            List<Context> contexts = classificationType.getContexts();
            List<DescriptionGroup> descriptionGroups = classificationType.getDescriptionGroups();

            for (Context context : contexts) {
                Contexts cont = new Contexts(
                        context.getName(),
                        context.getId(),
                        context.getDescription());

                for (DescriptionGroup description : context.getDescriptionGroup()) {
                    cont.getDescriptionGroup()
                            .add(new DescriptionGroups(
                                            description.getName(),
                                            description.getReference(),
                                            description.getDescription()));
                }
                classification.getContext().add(cont);
            }

            for (DescriptionGroup description : descriptionGroups) {
                classification.getDescriptionGroup()
                        .add(new DescriptionGroups(
                                        description.getName(),
                                        description.getReference(),
                                        description.getDescription()));
            }

            return Response
                    .status(Status.OK)
                    .entity(classification).build();
        }
        throw new DataNotFoundException("Could not return to the Asset Classification ");
    }

    /**
     * Servico responsavel por obter os dados referentes ao Usage de um ativo de
     * software armezenado no repositorio. Consome um XML padronizado
     * (Attribute) contendo o caminho absoluto do repositorio que se deseja
     * utilizar em conjunto com o nome do ativo que se busca.
     *
     * @param command
     * @return
     */
    @POST
    @Path("/getUsage")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public Response getUsage(Attribute command) throws IOException {

        Hidra hidra;
        Usage usage = new Usage();
        String assetName = command.getAssetName();
        String path = command.getRepositoryPath();

        if (path.isEmpty() || assetName.isEmpty()) {
            throw new DataNotFoundException("The absolute path of the repository Or Asset Name has not been set");
        }
        if (!new File(path).exists()) {
            throw new IOException("Could not find the informed repository");
        }

        hidra = new Hidra(path);
        UsageType usageType = hidra.describleUsage(assetName);
        if (usageType != null) {

            List<ArtifactActivy> artAct = usageType.getArtifactActivities();
            List<ContextReference> contexRefe = usageType.getContextReferences();

            for (ArtifactActivy artifacty : artAct) {
                ArtifactActivys artifactys = new ArtifactActivys(
                        artifacty.getArtifactId(),
                        artifacty.getContextId());

                for (Activity activity : artifacty.getActivities()) {
                    Activities activities = new Activities(
                            activity.getId(),
                            activity.getTask(),
                            activity.getReference(),
                            activity.getRole(),
                            activity.getTaskRole());

                    for (VariabilityPointBinding variability : activity.getVariability()) {
                        Variability variabili = new Variability(
                                variability.getId(),
                                variability.getBindingRule());

                        activities.getListOfVariability().add(variabili);
                    }
                    artifactys.getListOfActivities().add(activities);
                    usage.getArtifactActivitie().add(artifactys);
                }

            }

            for (ContextReference context : contexRefe) {
                ContextReferences cont = new ContextReferences(context.getContextId());

                for (Activity activity : context.getActivities()) {
                    Activities activities = new Activities(
                            activity.getId(),
                            activity.getTask(),
                            activity.getReference(),
                            activity.getRole(),
                            activity.getTaskRole());

                    for (VariabilityPointBinding variability : activity.getVariability()) {
                        Variability variabili = new Variability(
                                variability.getId(),
                                variability.getBindingRule());

                        activities.getListOfVariability().add(variabili);
                    }
                    cont.getListOfActivities().add(activities);
                    usage.getContextReference().add(cont);
                }
            }

            return Response
                    .status(Status.OK)
                    .entity(usage).build();
        }

        throw new DataNotFoundException("Could not return to the Asset Usage ");
    }

    /**
     * Servico responsavel por obter os dados referentes aos Ativos relacionados
     * artifact um determinado ativo de software armezenado no repositorio.
     * Consome um XML padronizado (Attribute) contendo o caminho absoluto do
     * repositorio que se deseja utilizar em conjunto com o nome do ativo que se
     * busca.
     *
     * @param command
     * @return
     * @throws java.io.IOException
     */
    @POST
    @Path("/getRelatedAssets")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public Response getRelatedAssets(Attribute command) throws IOException {

        Hidra hidra;
        RelatedAssets relatedAssets = new RelatedAssets();
        String assetName = command.getAssetName();
        String path = command.getRepositoryPath();

        if (path.isEmpty() || assetName.isEmpty()) {
            throw new DataNotFoundException("The absolute path of the repository Or Asset Name has not been set");
        }
        if (!new File(path).exists()) {
            throw new IOException("Could not find the informed repository");
        }

        hidra = new Hidra(path);
        ledes.hidra.asset.RelatedAssets result = hidra.describeRelatedAssets(assetName);
        if (result != null) {

            for (RelatedAssetType asset : result.getListOfRelatedAssets()) {
                RelatedAssets.Asset related = new RelatedAssets.Asset(
                        asset.getName(),
                        asset.getId(),
                        asset.getReference(),
                        asset.getRelationshipType());

                relatedAssets.getAsset().add(related);
            }

            return Response
                    .status(Status.OK)
                    .entity(relatedAssets).build();
        }

        throw new DataNotFoundException("Could not return to the Asset Usage ");
    }

    /**
     * Retorna o log dos commits realizados em um ativo especifico.
     *
     * @param command
     * @return
     * @throws IOException
     */
    @POST
    @Path("/getLog")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public Response getLog(Attribute command) throws IOException {

        Hidra hidra;
        String Log;
        ResultMessage result = new ResultMessage();
        String assetName = command.getAssetName();
        String path = command.getRepositoryPath();

        if (path.isEmpty() || assetName.isEmpty()) {
            throw new DataNotFoundException("The absolute path of the repository Or Asset Name has not been set");
        }
        if (!new File(path).exists()) {
            throw new IOException("Could not find the informed repository");
        }

        hidra = new Hidra(path);

        Log = hidra.getLog(assetName);
        if (!Log.isEmpty()) {
            result.setLog(Log);
            result.setMessage("Log referring to the active: " + assetName);
            result.setStatusMessage(Status.OK);

            return Response
                    .status(Status.OK)
                    .entity(result).build();
        }

        throw new DataNotFoundException("Could not return the Asset Log ");
    }

    /**
     * Serviço responsável pela obtenção do status do repositório informado.
     * Consome XML padronizado (Attribute) contendo o caminho absoluto do
     * repositorio que se deseja utilizar.
     *
     * @param command
     * @return
     * @throws IOException
     */
    @POST
    @Path("/getLogRepository")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public Response getLogRepository(Attribute command) throws IOException {

        Hidra hidra;
        String Log;
        ResultMessage result = new ResultMessage();
        String path = command.getRepositoryPath();

        if (path.isEmpty()) {
            throw new DataNotFoundException("The absolute path of the repository has not been set");
        }
        if (!new File(path).exists()) {
            throw new IOException("Could not find the informed repository");
        }

        hidra = new Hidra(path);

        Log = hidra.getLog();
        if (!Log.isEmpty()) {
            result.setLog(Log);
            result.setMessage("Log referring to the repository: " + path);
            result.setStatusMessage(Status.OK);

            return Response
                    .status(Status.OK)
                    .entity(result).build();
        }

        throw new DataNotFoundException("Could not return the repository Log ");
    }

    @POST
    @Path("/downloadAsset")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public Response downloadAsset(Attribute command) throws IOException {

        Hidra hidra;
        Zipper zipper = new Zipper();
        String path = command.getRepositoryPath();
        String assetName = command.getAssetName();
        ResultMessage result = new ResultMessage();

        if (path.isEmpty() || assetName.isEmpty()) {
            throw new DataNotFoundException("The absolute path of the repository Or Asset Name has not been set");
        }
        if (!new File(path).exists()) {
            throw new IOException("Could not find the informed repository");
        }
        if (!new File(path + separator + assetName).exists()) {
            throw new IOException("Asset informed was not found");
        }

        hidra = new Hidra(path);
        File assetFile = new File(path + separator + assetName);
        File destiny = new File(path + DOWNLOAD_PATH_TEMP + separator + assetName + extension);

        if (hidra.findAsset(assetName)) {
            zipper.criarZip(assetFile, assetFile.listFiles());
            zipper.getArquivoZipAtual().renameTo(new File(destiny, assetName));

            String downloadedFileLocation = System.getProperty("user.home") + separator + assetName + extension;

            InputStream in;
            int read = 0;
            byte[] bytes = new byte[1024];

            try {
                OutputStream out = new FileOutputStream(new File(downloadedFileLocation));
                in = new FileInputStream(zipper.getArquivoZipAtual());
                try {
                    while ((read = in.read(bytes)) != -1) {
                        out.write(bytes, 0, read);
                    }
                    out.flush();
                    out.close();
                    zipper.getArquivoZipAtual().delete();
                    result.setMessage("File download successfully");
                    result.setStatusMessage(Status.OK);
                } catch (IOException ex) {
                    throw new DataNotFoundException(ex.getMessage());
                }
            } catch (FileNotFoundException ex) {
                throw new DataNotFoundException(ex.getMessage());
            }

            return Response
                    .status(Status.OK)
                    .entity(result).build();
        }
        throw new DataNotFoundException("Canot find the informed Asset");
    }

    /**
     * Exemplo de serviço que utiliza das propriedades do Jersey / Jaxb a fim de
     * converter objetos JAVA em XML. O objeto Attribute possui anotações que
     * permitem a corversão de sua instancia em xml.
     *
     * @return
     */
    @GET
    @Path("/gettest")
    @Produces(MediaType.APPLICATION_XML)
    public Attribute gettest() {

        Attribute com = new Attribute();
        com.setAssetName("jaxb");

        com.setRepositoryPath("/hidra/FINAL");
        com.setRemoteRepository("HIDRA");
        com.setRepositoryLocalCopy("/home/pedro/CloneOfHidra");
        com.setUser("pedro");
        com.setPassword("220891");
        com.setSubmitMessage("Enviando alterações para o repositório");

        return com;
    }

}
