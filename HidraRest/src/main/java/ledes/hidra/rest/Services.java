package ledes.hidra.rest;

import java.io.File;
import java.io.IOException;
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
import ledes.hidra.asset.ArtifactType;
import ledes.hidra.asset.ClassificationType;
import ledes.hidra.asset.Context;
import ledes.hidra.asset.ContextReference;
import ledes.hidra.asset.DescriptionGroup;
import ledes.hidra.asset.RelatedAssetType;

import ledes.hidra.asset.SolutionType;
import ledes.hidra.asset.UsageType;
import ledes.hidra.asset.VariabilityPointBinding;
import ledes.hidra.exception.DataNotFoundException;
import ledes.hidra.rest.model.Activities;
import ledes.hidra.rest.model.Artifact;
import ledes.hidra.rest.model.ArtifactActivys;
import ledes.hidra.rest.model.Classification;
import ledes.hidra.rest.model.Command;
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
 * Hidra na forma de Web Services. Consome XML padronizado (Command) capaz de
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
    private static final String UPLOAD_PATH_TEMP = separator + ".hidra" + separator + ".temp" + separator + ".uploads";
    private static final String DOWNLOAD_PATH_TEMP = separator + ".hidra" + separator + ".temp" + separator + ".downloads";
    private static final String DEFAULT_MESSAGE_SUBMIT = "Sending changes to repository";

    private Hidra hidra;

    public Hidra getHidra() {
        return hidra;
    }

    /**
     * Servico responsavel pela inicializacao de um repositorio Hidra. Consome
     * XML padronizado (Command) contendo o caminho absoluto de onde deve ser
     * criado e inicializado o repositorio hidra.
     *
     * @param command
     * @return Response Object
     * @throws java.io.IOException
     */
    @POST
    @Path("/construct")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public Response construct(Command command) throws IOException {

        hidra = new Hidra();
        ResultMessage result = new ResultMessage();
        String path = command.getRepositoryPath();

        if (path.isEmpty()) {
            throw new DataNotFoundException("The absolute path of the repository has not been set");
        }
        if (new File(path).isDirectory()) {
            throw new IOException("Repository with this name already exists");
        }

        try {

            hidra.startRepository(path);
            result.setMessage("repository was created in : " + path);
            result.setStatusMessage(201);

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
     * do repositorio. Consome XML padronizado (Command) contendo o nome do
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
    public Response addAsset(Command command) throws Exception {

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
            result.setStatusMessage(200);
            return Response
                    .status(Status.OK)
                    .entity(result).build();
        }

        throw new Exception("File already monitored. You might want to do \"updateAsset\"");
    }

    /**
     * Servico responsavel por gravar as alteracoes no repositorio. Consome XML
     * padronizado (Command) contendo o caminho absoluto do repositorio que se
     * deseja utilizar.
     *
     * @param command
     * @return
     * @throws java.io.IOException
     */
    @POST
    @Path("/submit")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public Response submit(Command command) throws IOException, Exception {

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
            result.setStatusMessage(200);
            return Response
                    .status(Status.OK)
                    .entity(result).build();
        }

        throw new Exception("Could not commit changes to the repository");
    }

    /**
     * Servico responsavel por remover um ativo de software armezenado no
     * repositorio. Consome um XML padronizado (Command) contendo o caminho
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
    public Response remove(Command command) throws IOException, Exception {

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
            result.setStatusMessage(200);
            return Response
                    .status(Status.OK)
                    .entity(result).build();
        }

        throw new Exception("Could not remove Asset");
    }

    /**
     * Servico responsavel por criar uma copia local de um repositorio. Consome
     * um XML padronizado (Command) contendo o caminho absoluto do repositorio
     * que se deseja clonar e tambem o caminho absoluto do diretorio destino.
     *
     * @param command
     * @return
     * @throws java.io.IOException
     */
    @POST
    @Path("/cloneRepository")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public Response cloneRepository(Command command) throws IOException, Exception {

        Hidra hidra = new Hidra();
        ResultMessage result = new ResultMessage();
        String remotePath = command.getRemoteRepository();
        String cloneToDirectory = command.getRepositoryLocalCopy();

        if (remotePath.isEmpty() || cloneToDirectory.isEmpty()) {
            throw new DataNotFoundException("The absolute path of the remote repository Or the destiny has not been set");
        }
        if (new File(cloneToDirectory).exists() && new File(cloneToDirectory).listFiles().length != 0) {
            throw new IOException("Destination already exists");
        }

        if (hidra.startSynchronizedRepository(cloneToDirectory, remotePath)) {
            result.setMessage("Repository successfully cloned in " + cloneToDirectory);
            result.setStatusMessage(200);
            return Response
                    .status(Status.OK)
                    .entity(result).build();
        }

        throw new Exception("Could not clone remote repository");
    }

    /**
     * Servico responsavel por criar uma copia local de um repositorio remoto
     * atravez de autenticacao. Consome um XML padronizado (Command) contendo
     * artifact URL do repositorio que se deseja clonar e tambem o caminho
     * absoluto do diretorio destino e usuario e senha para acesso ao
     * repositorio remoto.
     *
     * @param command
     * @return
     * @throws java.io.IOException
     */
    @POST
    @Path("/cloneAuthorization")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public Response cloneAuthorizedRepository(Command command) throws IOException, Exception {

        Hidra hidra = new Hidra();
        String user = command.getUser();
        String password = command.getPassword();
        ResultMessage result = new ResultMessage();
        String remotePath = command.getRemoteRepository();
        String cloneToDirectory = command.getRepositoryLocalCopy();

        if (remotePath.isEmpty() || cloneToDirectory.isEmpty()) {
            throw new DataNotFoundException("The absolute path of the remote repository Or the destiny has not been set");
        }
        if (user.isEmpty() || password.isEmpty()) {
            throw new DataNotFoundException("User or Password has not been set");
        }
        if (new File(cloneToDirectory).exists() && new File(cloneToDirectory).listFiles().length != 0) {
            throw new IOException("Destination already exists");
        }

        if (hidra.startSynchronizedRepository(cloneToDirectory, remotePath, user, password)) {
            result.setMessage("Repository successfully cloned in " + cloneToDirectory);
            result.setStatusMessage(200);
            return Response
                    .status(Status.OK)
                    .entity(result).build();
        }

        throw new Exception("Could not clone remote repository: User or Password incorrect");

    }

    /**
     * Servico responsavel por obter os dados referentes artifact um Solution de
     * um ativo de software armezenado no repositorio. Consome um XML
     * padronizado (Command) contendo o caminho absoluto do repositorio que se
     * deseja utilizar em conjunto com o nome do ativo que se busca.
     *
     * @param command
     * @return
     */
    @POST
    @Path("/solution")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public Response getSolution(Command command) throws IOException {

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

            List<ArtifactType> artifacts = solutionType.getArtifacts().getArtifact();
            List<ArtifactType> design = solutionType.getDesign().getArtifact();
            List<ArtifactType> implementation = solutionType.getImplementation().getArtifact();
            List<ArtifactType> requirements = solutionType.getRequirements().getArtifact();
            List<ArtifactType> tests = solutionType.getTest().getArtifact();

            for (ArtifactType artifact : artifacts) {
                solution.getArtifacts()
                        .add(new Artifact(
                                        artifact.getName(),
                                        artifact.getType(),
                                        artifact.getReference(),
                                        artifact.getId(),
                                        artifact.getVersion()));
            }
            for (ArtifactType artfact : design) {
                solution.getDesign()
                        .add(new Artifact(
                                        artfact.getName(),
                                        artfact.getType(),
                                        artfact.getReference(),
                                        artfact.getId(),
                                        artfact.getVersion()));
            }
            for (ArtifactType artifact : implementation) {
                solution.getImplementation()
                        .add(new Artifact(
                                        artifact.getName(),
                                        artifact.getType(),
                                        artifact.getReference(),
                                        artifact.getId(),
                                        artifact.getVersion()));
            }

            for (ArtifactType artifact : requirements) {
                solution.getRequirements()
                        .add(new Artifact(
                                        artifact.getName(),
                                        artifact.getType(),
                                        artifact.getReference(),
                                        artifact.getId(),
                                        artifact.getVersion()));
            }
            for (ArtifactType artifact : tests) {
                solution.getTest()
                        .add(new Artifact(
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
     * padronizado (Command) contendo o caminho absoluto do repositorio que se
     * deseja utilizar em conjunto com o nome do ativo que se busca.
     *
     * @param command
     * @return
     */
    @POST
    @Path("/classification")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public Response getClassification(Command command) throws IOException {

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
     * software armezenado no repositorio. Consome um XML padronizado (Command)
     * contendo o caminho absoluto do repositorio que se deseja utilizar em
     * conjunto com o nome do ativo que se busca.
     *
     * @param command
     * @return
     */
    @POST
    @Path("/usage")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public Response getUsage(Command command) throws IOException {

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
     * Consome um XML padronizado (Command) contendo o caminho absoluto do
     * repositorio que se deseja utilizar em conjunto com o nome do ativo que se
     * busca.
     *
     * @param command
     * @return
     * @throws java.io.IOException
     */
    @POST
    @Path("/relatedAssets")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public Response getRelatedAssets(Command command) throws IOException {

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
    @Path("/log")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public Response getLog(Command command) throws IOException {

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
            result.setStatusMessage(200);

            return Response.status(500).entity(result).build();
        }

        throw new DataNotFoundException("Could not return the Asset Log ");
    }

    @POST
    @Path("/showLogs")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public Response getStatus(Command command) throws IOException {

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
            result.setStatusMessage(200);

            return Response.status(500).entity(result).build();
        }

        throw new DataNotFoundException("Could not return the repository Log ");
    }

    @GET
    @Path("gettest")
    @Produces(MediaType.APPLICATION_XML)
    public Solution getCommand() {

        Command com = new Command();
        com.setAssetName("jaxb");
        com.setRepositoryPath("/var/www/hidra.com/hidra/FINAL");
        com.setRemoteRepository("/var/www/hidra.com/hidra/HIDRA");
        com.setRepositoryLocalCopy("/home/pedro/CloneOfHidra");
        com.setUser("pedro");
        com.setPassword("220891");
        com.setSubmitMessage("Enviando alterações para o repositório");

        Artifact artifact = new Artifact();
        artifact.setId("1");
        artifact.setName("NAME");
        artifact.setReference("REFERENCE");
        artifact.setType("TYPE");
        artifact.setVersion("VERSION");

        Artifact designArtifact = new Artifact();
        designArtifact.setId("idDesign");
        designArtifact.setName("Design Artifact");
        designArtifact.setReference("REFERENCE");
        designArtifact.setType("Design");
        designArtifact.setVersion("design_001");

        Artifact implementationArtifact = new Artifact();
        implementationArtifact.setId("idImplementation");
        implementationArtifact.setName("Implementation Artifact");
        implementationArtifact.setReference("REFERENCE");
        implementationArtifact.setType("Implementation");
        implementationArtifact.setVersion("implementation_001");

        Artifact requirementArtifact = new Artifact();
        requirementArtifact.setId("idRequirement");
        requirementArtifact.setName("Requirement Artifact");
        requirementArtifact.setReference("REFERENCE");
        requirementArtifact.setType("Requirements");
        requirementArtifact.setVersion("requirement_001");

        Artifact testArtifact = new Artifact();
        testArtifact.setId("idTest");
        testArtifact.setName("Test Artifact");
        testArtifact.setReference("REFERENCE");
        testArtifact.setType("TEST");
        testArtifact.setVersion("test_001");

        Solution solution = new Solution();
        solution.getArtifacts().add(artifact);
        solution.getDesign().add(designArtifact);
        solution.getRequirements().add(requirementArtifact);
        solution.getTest().add(testArtifact);

        return solution;
    }

}
