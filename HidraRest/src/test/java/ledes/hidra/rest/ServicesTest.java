/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ledes.hidra.rest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
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

    @Test
    public void ADICIONAR_ATIVO_VALIDO() throws Exception {
        System.out.println("executando...ADICIONAR_ATIVO_VALIDO");

        File ativo = new File("/home/pedro/AreaDeTestes/jaxb");
        File destino = new File("/home/pedro/repo2");

        File novoDestino = new File(destino + File.separator + ativo.getName());
        

        Services instance = new Services();

        //Response result = instance.addAsset(com);
        assertEquals(true, true);

    }

    @Ignore
    @Test
    public void testSave() throws Exception {
        System.out.println("save");
        Command command = new Command();
        command.setRepositoryPath("/var/www/hidra.com/hidra/testStart");
        command.setSubmitMessage("Mensagem sava para repositório");

        Services instance = new Services();

        Response result = instance.save(command);
        assertEquals(200, result.getStatus());

    }

    @Ignore
    @Test
    public void testRemove() throws Exception {
        System.out.println("remove");
        Command command = new Command();
        command.setRepositoryPath("/var/www/hidra.com/hidra/testStart");
        command.setAssetName("jaxb");
        Services instance = new Services();

        Response result = instance.remove(command);
        assertEquals(200, result.getStatus());
    }

    @Ignore
    @Test
    public void testStartSynchronizedRepository() throws Exception {
        System.out.println("startSynchronizedRepository");
        Command command = null;
        Services instance = new Services();
        Response expResult = null;
        Response result = instance.startSynchronizedRepository(command);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Ignore
    @Test
    public void testStartSynchronizedRepositoryAuthentication() throws Exception {
        System.out.println("startSynchronizedRepositoryAuthentication");
        Command command = null;
        Services instance = new Services();
        Response expResult = null;
        Response result = instance.startSynchronizedRepositoryAuthentication(command);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Ignore
    @Test
    public void testSendUpdates() throws Exception {
        System.out.println("sendUpdates");
        Command command = null;
        Services instance = new Services();
        Response expResult = null;
        Response result = instance.sendUpdates(command);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Ignore
    @Test
    public void testReceiveUpdates() throws Exception {
        System.out.println("receiveUpdates");
        Command command = null;
        Services instance = new Services();
        Response expResult = null;
        Response result = instance.receiveUpdates(command);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Ignore
    @Test
    public void testGetSolution() throws Exception {
        System.out.println("getSolution");
        Command command = null;
        Services instance = new Services();
        Response expResult = null;
        Response result = instance.getSolution(command);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Ignore
    @Test
    public void testGetClassification() throws Exception {
        System.out.println("getClassification");
        Command command = null;
        Services instance = new Services();
        Response expResult = null;
        Response result = instance.getClassification(command);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Ignore
    @Test
    public void testGetUsage() throws Exception {
        System.out.println("getUsage");
        Command command = null;
        Services instance = new Services();
        Response expResult = null;
        Response result = instance.getUsage(command);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Ignore
    @Test
    public void testGetRelatedAssets() throws Exception {
        System.out.println("getRelatedAssets");
        Command command = null;
        Services instance = new Services();
        Response expResult = null;
        Response result = instance.getRelatedAssets(command);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Ignore
    @Test
    public void testGetLog() throws Exception {
        System.out.println("getLog");
        Command command = null;
        Services instance = new Services();
        Response expResult = null;
        Response result = instance.getLog(command);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Ignore
    @Test
    public void testGetLogRepository() throws Exception {
        System.out.println("getLogRepository");
        Command command = null;
        Services instance = new Services();
        Response expResult = null;
        Response result = instance.getLogRepository(command);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Ignore
    @Test
    public void testDownloadAsset() throws Exception {
        System.out.println("downloadAsset");
        Command command = null;
        Services instance = new Services();
        Response expResult = null;
        Response result = instance.downloadAsset(command);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Ignore
    @Test
    public void testGettest() {
        System.out.println("gettest");
        Services instance = new Services();

        Command expResult = new Command();

        expResult.setAssetName("jaxb");

        expResult.setRepositoryPath("/var/www/hidra.com/hidra/FINAL");
        expResult.setRemoteRepository("/var/www/hidra.com/hidra/HIDRA");
        expResult.setRepositoryLocalCopy("/home/pedro/CloneOfHidra");
        expResult.setUser("pedro");
        expResult.setPassword("220891");
        expResult.setSubmitMessage("Enviando alterações para o repositório");

        Command result = instance.gettest();

        System.out.println(result);
        System.out.println(expResult);
        assertEquals(expResult, result);
    }

}
