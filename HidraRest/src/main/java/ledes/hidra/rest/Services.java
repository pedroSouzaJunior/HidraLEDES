package ledes.hidra.rest;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.xml.bind.JAXBException;
import ledes.hidra.Hidra;
import ledes.hidra.exception.DataNotFoundException;
import ledes.hidra.resources.HidraResources;
import ledes.hidra.resources.Zipper;
import ledes.hidra.rest.model.Command;
import ledes.hidra.rest.model.ResultMessage;

/**
 * Classe Services - responsavel por transcrever as funcionalidades do projeto
 * Hidra na forma de Web Services. Consome XML padronizado (Command) capaz de
 * comportar informações necessarias para a execucao dos metodos da biblioteca
 * Hidra.
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
     * Servico responsavel por adicionar ativos a area de monitoramento do
     * repositorio. Consome XML padronizado (Command) contendo o nome do ativo
     * que se deseja monitorar.
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

        hidra = new Hidra(path);
        if (hidra.addAsset(assetName)) {
            result.setMessage("The asset: " + assetName + " has been added to the repository");
            result.setStatusMessage(200);
            return Response.status(500).entity(result).build();
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
     */
    @POST
    @Path("/submit")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public Response submit(Command command) {

        Hidra hidra = new Hidra(command.getDestiny());

        if (hidra.save(command.getSubmitMessage())) {
            return Response.status(200).entity("Commit Accomplished").build();
        }

        return Response.status(500).entity("Error in server").build();
    }

    /**
     * Servico responsavel por obter os dados referentes a um Solution de um
     * ativo de software armezenado no repositorio. Consome um XML padronizado
     * (Command) contendo o caminho absoluto do repositorio que se deseja
     * utilizar em conjunto com o nome do ativo que se busca.
     *
     * @param command
     * @return
     */
    @POST
    @Path("/solution")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public Response getSolution(Command command) {

        Hidra hidra = new Hidra(command.getDestiny());
        String solution = hidra.getSolution(command.getAssetFile().getName());

        if (!solution.isEmpty()) {
            return Response.status(200).entity(solution).build();
        }

        return Response.status(500).entity("Error in Server").build();
    }

    /**
     * Servico responsavel por obter os dados referentes a Classification de um
     * ativo de software armezenado no repositorio. Consome um XML padronizado
     * (Command) contendo o caminho absoluto do repositorio que se deseja
     * utilizar em conjunto com o nome do ativo que se busca.
     *
     * @param command
     * @return
     */
    @POST
    @Path("/classification")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public Response getClassification(Command command) {

        Hidra hidra = new Hidra(command.getDestiny());
        String classification = hidra.getClassification(command.getAssetFile().getName());

        if (!classification.isEmpty()) {
            return Response.status(200).entity(classification).build();
        }

        return Response.status(500).entity("Error in Server").build();
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
    public Response getUsage(Command command) {

        Hidra hidra = new Hidra(command.getDestiny());
        String usage = hidra.getUsage(command.getAssetFile().getName());

        if (!usage.isEmpty()) {
            return Response.status(200).entity(usage).build();
        }

        return Response.status(500).entity("Error in Server").build();
    }

    /**
     * Servico responsavel por obter os dados referentes aos Ativos relacionados
     * a um determinado ativo de software armezenado no repositorio. Consome um
     * XML padronizado (Command) contendo o caminho absoluto do repositorio que
     * se deseja utilizar em conjunto com o nome do ativo que se busca.
     *
     * @param command
     * @return
     */
    @POST
    @Path("/related")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public Response getRelatedAssets(Command command) {

        Hidra hidra = new Hidra(command.getDestiny());
        String related = hidra.getRelatedAssets(command.getAssetFile().getName());

        if (!related.isEmpty()) {
            return Response.status(200).entity(related).build();
        }

        return Response.status(500).entity("Error in Server").build();
    }

    @POST
    @Path("/log")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public Response getLog(Command command) {

        Hidra hidra = new Hidra(command.getDestiny());
        String log = hidra.getLog(command.getAssetFile().getName());

        if (!log.isEmpty()) {
            return Response.status(200).entity(log).build();
        }

        return Response.status(500).entity("Error in Server").build();
    }

    @POST
    @Path("/showLogs")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public Response getStatus(Command command) {

        Hidra hidra = new Hidra(command.getDestiny());
        String logs = hidra.getLog();

        if (!logs.isEmpty()) {
            return Response.status(200).entity(logs).build();
        }

        return Response.status(500).entity("Error in Server").build();
    }

    /**
     * Servico responsavel por remover um ativo de software armezenado no
     * repositorio. Consome um XML padronizado (Command) contendo o caminho
     * absoluto do repositorio que se deseja utilizar em conjunto com o nome do
     * ativo que se deseja remover.
     *
     * @param command
     * @return
     */
    @POST
    @Path("/removeasset")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public Response remove(Command command) {

        Hidra hidra = new Hidra(command.getDestiny());

        if (hidra.removeAsset(command.getAssetFile().getName())) {
            return Response.status(200).entity("Ativo removido com Sucesso").build();
        }

        return Response.status(500).entity("Erro Interno ").build();
    }

    @GET
    @Path("gettest")
    @Produces(MediaType.APPLICATION_XML)
    public Command getCommand() {

        Command com = new Command();
        com.setAssetName("jaxb");
        com.setRepositoryPath("/var/www/hidra.com/hidra/FINAL");

        return com;
    }

    @GET
    @Path("/query")
    @Produces(MediaType.APPLICATION_XML)
    public Response getUsers(
            @DefaultValue("1000") @QueryParam("from") int from,
            @DefaultValue("999") @QueryParam("to") int to,
            @DefaultValue("name") @QueryParam("orderBy") List<String> orderBy) {

        return Response
                .status(200)
                .entity("getUsers is called, from : " + from + ", to : " + to
                        + ", orderBy" + orderBy.toString()).build();

    }

    /**
     * Servico responsavel por obter os dados referentes a quais ativos de
     * software estao armezenado no repositorio. Consome um XML padronizado
     * (Command) contendo o caminho absoluto do repositorio que se deseja
     * utilizar em conjunto com o nome do ativo que se busca.
     *
     * @param command
     * @return
     */
    @POST
    @Path("/getAssetsAvailable")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.TEXT_PLAIN)
    public Response listAssets(Command command) {

        Hidra hidra = new Hidra(command.getDestiny());

        for (Map.Entry<String, String> entry : hidra.listAssets().entrySet()) {
            System.out.println(entry.getKey() + "/" + entry.getValue());
        }
        return Response.status(200).entity("Assets Avaliables").build();
    }

    /**
     * Servico responsavel por criar uma copia local de um repositorio. Consome
     * um XML padronizado (Command) contendo o caminho absoluto do repositorio
     * que se deseja clonar e tambem o caminho absoluto do diretorio destino.
     *
     * @param command
     * @return
     */
    @POST
    @Path("/cloneRepository")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public Response cloneRepository(Command command) {

        Hidra hidra = new Hidra();

        try {
            if (hidra.startSynchronizedRepository(command.getRepositoryLocalCopy(), command.getRemoteRepository())) {

                return Response.status(200).entity("Local Copy has been created").build();
            }

        } catch (Exception ex) {
            Logger.getLogger(Services.class.getName()).log(Level.SEVERE, null, ex);
        }

        return Response.status(500).entity("Error in Server").build();
    }

    /**
     * Servico responsavel por criar uma copia local de um repositorio remoto
     * atravez de autenticacao. Consome um XML padronizado (Command) contendo a
     * URL do repositorio que se deseja clonar e tambem o caminho absoluto do
     * diretorio destino e usuario e senha para acesso ao repositorio remoto.
     *
     * @param command
     * @return
     */
    @POST
    @Path("/cloneAuthorization")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public Response cloneAuthorizedRepository(Command command) {

        Hidra hidra = new Hidra();

        try {
            if (hidra.startSynchronizedRepository(command.getRepositoryLocalCopy(), command.getRemoteRepository(),
                    command.getUser(), command.getPassword())) {
                return Response.status(200).entity("Local Copy has been created").build();
            }
        } catch (Exception ex) {
            Logger.getLogger(Services.class.getName()).log(Level.SEVERE, null, ex);
        }

        return Response.status(500).entity("Could not clone remote repository").build();
    }

    /**
     * Servico responsavel por atualizar repositorio remoto com as modificacoes
     * e informacoes do repositorio local. Consome XML padronizado (Command)
     * contendo o caminho absoluto do repositorio local.
     *
     * @param command
     * @return
     */
    @POST
    @Path("/upadateRepository")
    @Consumes(MediaType.APPLICATION_XML)
    public Response updateRepository(Command command) {

        String result;
        Hidra hidra = new Hidra(command.getDestiny());

        try {
            result = hidra.update(command.getUser(), command.getPassword());

            if (result.equals("Repository Updated ")) {
                return Response.status(200).entity(result).build();
            }
        } catch (Exception ex) {
            Logger.getLogger(Services.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Response.status(500).entity("Erron Server internal").build();
    }

    /**
     * Servico responsavel por atualizar o repositorio local com modificacoes
     * que foram aplicadas ao seu respectivo repositorio remoto. Consome XML
     * padronizado (Command) que contem o caminho absoluto do repositorio que se
     * deseja atualizar. O repositorio deve possuir ligacao a um repositorio
     * remoto
     *
     * @param command
     * @return
     */
    @POST
    @Path("/updateLocalRepository")
    @Consumes(MediaType.APPLICATION_XML)
    public Response updateLocalRepository(Command command) {

        boolean result = false;
        Hidra hidra = new Hidra(command.getDestiny());

        try {
            result = hidra.synchronize(command.getUser(), command.getPassword());
            if (result) {
                return Response.status(200).entity("Successfully synchronized repository").build();
            }
        } catch (Exception ex) {
            Logger.getLogger(Services.class.getName()).log(Level.SEVERE, null, ex);
        }

        return Response.status(500).entity("Please add a remote repository").build();
    }

}
