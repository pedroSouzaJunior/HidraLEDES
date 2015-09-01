package ledes.hidra.rest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
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
import javax.xml.bind.JAXBException;
import ledes.hidra.Hidra;
import ledes.hidra.asset.ArtifactType;
import ledes.hidra.asset.Asset;
import ledes.hidra.asset.SolutionType;
import ledes.hidra.resources.HidraResources;
import ledes.hidra.resources.Zipper;
import ledes.hidra.rest.model.Command;
import javax.ws.rs.core.Response.ResponseBuilder;

/**
 * Classe Responsavel pelo tratamento de servicos do projeto Hidra.
 *
 * @author pedro e danielli
 */
@Path("/services")
public class Services {

    private Hidra hidra;

    public Hidra getHidra() {
        return hidra;
    }

    /**
     * *
     * Metodo resposavel pela inicializacao de um repositorio utilizando a
     * classe Command
     *
     * @param command
     * @return
     */
    @POST
    @Path("/construct")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public Response construct(Command command) {

        if (command.getDestiny() != null) {

            hidra = new Hidra(command.getDestiny());

            try {
                hidra.startRepository(command.getDestiny());
            } catch (IOException ex) {
                Logger.getLogger(Services.class.getName()).log(Level.SEVERE, null, ex);
            } catch (JAXBException ex) {
                Logger.getLogger(Services.class.getName()).log(Level.SEVERE, null, ex);
            }

            return Response
                    .status(201)
                    .entity("repository was created in : " + command.getDestiny()).build();
        }
        return Response.status(500).entity("Error in server").build();
    }

    /**
     * *
     * Metodo utilizado para upload de ativos para o repositorio remoto.
     *
     * @param command
     * @return
     * @throws IOException
     */
    @POST
    @Path("/insert")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public Response insertAsset(Command command) throws IOException {

        //zipando arquivo
        Zipper zipper = new Zipper();
        zipper.criarZip(command.getAssetFile(), command.getAssetFile().listFiles());

        //enviando arquivo ao servidor
        String uploadedFileLocation = command.getDestiny() + File.separator + zipper.getArquivoZipAtual().getName();
        InputStream in;
        int read = 0;
        byte[] bytes = new byte[1024];

        if (zipper.getArquivoZipAtual() != null && command.getDestiny() != null) {
            try {
                OutputStream out = new FileOutputStream(new File(uploadedFileLocation));
                in = new FileInputStream(zipper.getArquivoZipAtual());
                try {
                    while ((read = in.read(bytes)) != -1) {
                        out.write(bytes, 0, read);
                    }
                    out.flush();
                    out.close();
                    zipper.getArquivoZipAtual().delete();
                } catch (IOException ex) {
                    Logger.getLogger(Services.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Services.class.getName()).log(Level.SEVERE, null, ex);
            }

            return Response.status(201).entity("File uploade successfully in: " + command.getDestiny()).build();
        }
        return Response.status(500).entity("Error in server").build();
    }

    /**
     * *
     * Metodo utilizado em conjunto do metodo insert para adicao de ativos no
     * repositorio remoto
     *
     * @param command
     * @return
     * @throws IOException
     */
    @POST
    @Path("/addasset")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public Response addAsset(Command command) throws IOException {
        Hidra hidra = new Hidra(command.getDestiny());

        File destiny = new File(command.getDestiny() + File.separator + command.getAssetFile().getName());

        File asset = new File(destiny.getParent() + File.separator + command.getAssetFile().getName() + ".zip");

        HidraResources resources = new HidraResources();
        Zipper zipper = new Zipper();

        if (resources.assetExist(destiny.getParentFile(), command.getAssetFile().getName())) {

            zipper.extrairZip(asset, destiny);
            hidra.addAsset(command.getAssetFile().getName());
            asset.delete();

            return Response.status(200).entity("ativo adicionado com sucesso").build();
        }
        return Response.status(500).entity("arquivo nao encontrado").build();
    }

    /**
     * *
     * Metodo utilizado para realizar um commit das modificacoes em ativos no
     * repositorio remoto
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
            return Response.status(200).entity("ok").build();
        }

        return Response.status(500).entity("Error in server").build();
    }

    /**
     * *
     * Metodo responsavel pela obtencao dos valores que representam a Solution
     * de um ativo de software
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
     * *
     * Metodo responsavel pela obtencao dos valores que representam a
     * Classification de um ativo de software
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
     * *
     * Metodo responsaveis pela obtencao dos valores que representam o Usage de
     * um ativo de software
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
     * *
     * Metodo responsavel pela obtencao dos valores que representam os ativos
     * Relacionados
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

    @POST
    @Path("/defineSolution")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public SolutionType defineSolution(Command command) {

        ArtifactType art = new ArtifactType();
        art.setId("10");
        art.setName("PASTA");
        art.setReference("/OUTRA_PASTA");
        art.setType("Folder");
        art.setVersion("00012");

        SolutionType Sol = new SolutionType();
        Sol.getArtifacts().getArtifact().add(null);
        Sol.getDesign().getArtifact().add(null);
        Sol.getImplementation().getArtifact().add(null);
        Sol.getRequirements().getArtifact().add(null);
        Sol.getTest().getArtifact().add(null);

        return null;
    }

    @GET
    @Path("/asset")
    @Produces(MediaType.APPLICATION_XML)
    public Asset getAsset() {

        Asset asset = new Asset();
        asset.setId("003");

        ArtifactType art = new ArtifactType();
        art.setId("10");
        art.setName("PASTA");
        art.setReference("/OUTRA_PASTA");
        art.setType("Folder");
        art.setVersion("00012");

        SolutionType Sol = new SolutionType();
        Sol.getArtifacts().getArtifact().add(art);
        Sol.getDesign().getArtifact().add(art);
        Sol.getImplementation().getArtifact().add(art);
        Sol.getRequirements().getArtifact().add(art);
        Sol.getTest().getArtifact().add(art);

        asset.setSolution(Sol);

        return asset;

    }

    /**
     * *************************************************************************
     * a partir daqui sao metodos de teste, e coisinhas que eu estava estudando
     * daqui pra cima sao os metodos que ja estao funcionando.
     *
     *
     * *************************************************************************
     */
    @GET
    @Path("gettest")
    @Produces(MediaType.APPLICATION_XML)
    public Command getCommand() {

        Command com = new Command("/var/www/hidra.com/hidra/danielli");
        com.setAssetFile(new File("/home/pedro/Documentos/qsort.c"));
        com.setSubmitMessage("Message to commit default");
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

    @GET
    @Path("/pedro")
    @Produces("application/zip")
    public Response getFile() {

        final String FILE_PATH = "/home/pedro/MyFile.zip";
        File file = new File(FILE_PATH);

        ResponseBuilder response = Response.ok((Object) file);
        response.header("Content-Disposition", "attachment; filename=newfile.zip");

        return response.build();
    }
}
