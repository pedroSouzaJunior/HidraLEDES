/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ledes.hidra.rest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.ws.rs.core.Response;
import ledes.hidra.Hidra;
import ledes.hidra.rest.model.Command;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author pedro
 */
public class ServicesTest {

    public Command com;

    public ServicesTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        com = new Command();

        com.setAssetName("jaxb");

        com.setRepositoryPath("/var/www/hidra.com/hidra/REPOSITORIO_TESTE");

        com.setRemoteRepository("/var/www/hidra.com/hidra/REPOSITORIO_TESTE");

        com.setRepositoryLocalCopy("/home/pedro/CLONE_DE_REPOSITORIO_TESTE");

        com.setUser("pedro");

        com.setPassword("220891");

        com.setSubmitMessage("Enviando alterações para o repositório");
    }

    @After
    public void tearDown() {
    }

    @Ignore
    @Test
    public void CRIAR_REPOSITORIO_HIDRA() throws Exception {
        System.out.println("executando...CRIAR_REPOSITORIO_HIDRA");

        Services instance = new Services();
        Response result = instance.startRepository(com);

        assertEquals(201, result.getStatus());

    }

    //@Ignore
    @Test
    public void ADICIONAR_ATIVO_VALIDO() throws Exception {
        System.out.println("executando...ADICIONAR_ATIVO_VALIDO");

        Services instance = new Services();

        Response result = instance.addAsset(com);
        assertEquals(200, result.getStatus());

    }

    //@Ignore
    @Test
    public void SUBMIT_ALTERACOES() throws Exception {
        System.out.println("executando...SUBMIT_ALTERACOES");

        Services instance = new Services();
        Response result = instance.save(com);

        assertEquals(200, result.getStatus());

    }

    //@Ignore
    @Test
    public void OBTER_SOLUTION_DE_ATIVO() throws Exception {
        System.out.println("executando...OBTER_SOLUTION_DE_ATIVO");

        Services instance = new Services();

        Response result = instance.getSolution(com);
        assertEquals(200, result.getStatus());
    }

    //@Ignore
    @Test
    public void OBETER_CLASSIFICATION_DE_ATIVO() throws Exception {
        System.out.println("executando...OBETER_CLASSIFICATION_DE_ATIVO");

        Services instance = new Services();
        Response result = instance.getClassification(com);
        assertEquals(200, result.getStatus());

    }

    //@Ignore
    @Test
    public void OBTER_USAGE_DE_ATIVO() throws Exception {
        System.out.println("executando...OBTER_USAGE_DE_ATIVO");

        Services instance = new Services();
        Response result = instance.getUsage(com);
        assertEquals(200, result.getStatus());
    }

    //@Ignore
    @Test
    public void OBTER_ATIVOS_RELACIONADOS() throws Exception {
        System.out.println("executando...OBTER_ATIVOS_RELACIONADOS");

        Services instance = new Services();
        Response result = instance.getRelatedAssets(com);
        assertEquals(200, result.getStatus());

    }

    //@Ignore
    @Test
    public void CLONAR_REPOSITORIO_SEM_AUTENTICACAO() throws Exception {
        System.out.println("executando...CLONAR_REPOSITORIO_SEM_AUTENTICACAO");

        Services instance = new Services();
        Response result = instance.startSynchronizedRepository(com);
        assertEquals(200, result.getStatus());

    }

    @Test
    public void OBTER_LOG_DE_ATIVO() throws IOException {

        System.out.println("executando...OBTER_LOG_DE_ATIVO");

        Services instance = new Services();
        Response result = instance.getLog(com);

        assertEquals(200, result.getStatus());

    }

    @Test
    public void OBTER_LOG_DO_REPOSITORIO() throws IOException{

        System.out.println("executando...OBTER_LOG_DO_REPOSITORIO");

        Services instance = new Services();
        Response result = instance.getLogRepository(com);

        assertEquals(200, result.getStatus());

    }
    
    @Test
    public void DOWNLOAD_DE_ATIVO() throws IOException{
        System.out.println("executando...DOWNLOAD_DE_ATIVO");
        
        Services instance = new Services();
        
        Response result = instance.downloadAsset(com);
        
        assertEquals(200, result.getStatus());
        
    }
}
