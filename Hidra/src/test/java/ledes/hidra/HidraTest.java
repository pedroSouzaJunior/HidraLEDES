/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ledes.hidra;

import java.io.File;
import java.util.Map;
import java.util.Set;
import ledes.hidra.asset.ClassificationType;
import ledes.hidra.asset.SolutionType;
import ledes.hidra.asset.UsageType;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author danielli
 */
public class HidraTest {
    
    public HidraTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getRepository method, of class Hidra.
     */
    @Test
    public void testGetRepository() {
        System.out.println("getRepository");
        Hidra instance = new Hidra();
        Repository expResult = null;
        Repository result = instance.getRepository();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setRepository method, of class Hidra.
     */
    @Test
    public void testSetRepository() {
        System.out.println("setRepository");
        Repository repository = null;
        Hidra instance = new Hidra();
        instance.setRepository(repository);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of startRepository method, of class Hidra.
     */
    @Test
    public void testStartRepository() throws Exception {
        System.out.println("startRepository");
        String localPath = "";
        Hidra instance = new Hidra();
        boolean expResult = false;
        boolean result = instance.startRepository(localPath);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of startSynchronizedRepository method, of class Hidra.
     */
    @Test
    public void testStartSynchronizedRepository_String_String() {
        System.out.println("startSynchronizedRepository");
        String localPath = "";
        String remotePath = "";
        Hidra instance = new Hidra();
        boolean expResult = false;
        boolean result = instance.startSynchronizedRepository(localPath, remotePath);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of startSynchronizedRepository method, of class Hidra.
     */
    @Test
    public void testStartSynchronizedRepository_4args() {
        System.out.println("startSynchronizedRepository");
        String localPath = "";
        String remotePath = "";
        String user = "";
        String password = "";
        Hidra instance = new Hidra();
        boolean expResult = false;
        boolean result = instance.startSynchronizedRepository(localPath, remotePath, user, password);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addAsset method, of class Hidra.
     */
    @Test
    public void testAddAsset() {
        System.out.println("addAsset");
        String nameAsset = "";
        Hidra instance = new Hidra();
        boolean expResult = false;
        boolean result = instance.addAsset(nameAsset);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSolution method, of class Hidra.
     */
    @Test
    public void testGetSolution() {
        System.out.println("getSolution");
        String assetId = "";
        Hidra instance = new Hidra();
        String expResult = "";
        String result = instance.getSolution(assetId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setSolutionType method, of class Hidra.
     */
    @Test
    public void testSetSolutionType() {
        System.out.println("setSolutionType");
        String assetId = "";
        SolutionType solution = null;
        Hidra instance = new Hidra();
        boolean expResult = false;
        boolean result = instance.setSolutionType(assetId, solution);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of validateAsset method, of class Hidra.
     */
    @Test
    public void testValidateAsset() {
        
        System.out.println("validateAsset");
        String assetPath = "/home/danielli/repo3/jaxb/";
        Hidra instance = new Hidra("/home/danielli/repo3");
        boolean expResult = true;
        boolean result = instance.validateAsset(assetPath);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
       
    }

    /**
     * Test of removeAsset method, of class Hidra.
     */
    @Test
    public void testRemoveAsset() {
        System.out.println("removeAsset");
        String assetId = "";
        Hidra instance = new Hidra();
        boolean expResult = false;
        boolean result = instance.removeAsset(assetId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getClassification method, of class Hidra.
     */
    @Test
    public void testGetClassification() {
        System.out.println("getClassification");
        String assetId = "";
        Hidra instance = new Hidra();
        String expResult = "";
        String result = instance.getClassification(assetId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setClassification method, of class Hidra.
     */
    @Test
    public void testSetClassification() {
        System.out.println("setClassification");
        String assetID = "";
        ClassificationType classification = null;
        Hidra instance = new Hidra();
        boolean expResult = false;
        boolean result = instance.setClassification(assetID, classification);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUsage method, of class Hidra.
     */
    @Test
    public void testGetUsage() {
        System.out.println("getUsage");
        String assetId = "";
        Hidra instance = new Hidra();
        String expResult = "";
        String result = instance.getUsage(assetId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setUsage method, of class Hidra.
     */
    @Test
    public void testSetUsage() {
        System.out.println("setUsage");
        String assetId = "";
        UsageType usage = null;
        Hidra instance = new Hidra();
        boolean expResult = false;
        boolean result = instance.setUsage(assetId, usage);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRelatedAssets method, of class Hidra.
     */
    @Test
    public void testGetRelatedAssets() {
        System.out.println("getRelatedAssets");
        String assetId = "";
        Hidra instance = new Hidra();
        String expResult = "";
        String result = instance.getRelatedAssets(assetId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setRelatedAsset method, of class Hidra.
     */
    @Test
    public void testSetRelatedAsset() {
        System.out.println("setRelatedAsset");
        String assetId = "";
        String relatedId = "";
        Hidra instance = new Hidra();
        boolean expResult = false;
        boolean result = instance.setRelatedAsset(assetId, relatedId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLog method, of class Hidra.
     */
    @Test
    public void testGetLog() {
        System.out.println("getLog");
        String assetName = "";
        Hidra instance = new Hidra();
        String expResult = "";
        String result = instance.getLog(assetName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of listAssets method, of class Hidra.
     */
    @Test
    public void testListAssets() {
        System.out.println("listAssets");
        Hidra instance = new Hidra();
        Map<String, String> expResult = null;
        Map<String, String> result = instance.listAssets();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of downloadAsset method, of class Hidra.
     */
    @Test
    public void testDownloadAsset() throws Exception {
        System.out.println("downloadAsset");
        String assetId = "";
        Hidra instance = new Hidra();
        File expResult = null;
        File result = instance.downloadAsset(assetId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of save method, of class Hidra.
     */
    @Test
    public void testSave() {
        System.out.println("save");
        String message = "";
        Hidra instance = new Hidra();
        boolean expResult = false;
        boolean result = instance.save(message);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of synchronize method, of class Hidra.
     */
    @Test
    public void testSynchronize() {
        System.out.println("synchronize");
        String user = "";
        String password = "";
        Hidra instance = new Hidra();
        boolean expResult = false;
        boolean result = instance.synchronize(user, password);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of showLogs method, of class Hidra.
     */
    @Test
    public void testShowLogs_String() {
        System.out.println("showLogs");
        String assetName = "";
        Hidra instance = new Hidra();
        String expResult = "";
        String result = instance.showLogs(assetName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of showLogs method, of class Hidra.
     */
    @Test
    public void testShowLogs_0args() {
        System.out.println("showLogs");
        Hidra instance = new Hidra();
        String expResult = "";
        String result = instance.showLogs();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of showStatus method, of class Hidra.
     */
    @Test
    public void testShowStatus() {
        System.out.println("showStatus");
        Hidra instance = new Hidra();
        Map<String, Set<String>> expResult = null;
        Map<String, Set<String>> result = instance.showStatus();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateAsset method, of class Hidra.
     */
    @Test
    public void testUpdateAsset() {
        System.out.println("updateAsset");
        String assetName = "";
        Hidra instance = new Hidra();
        boolean expResult = false;
        boolean result = instance.updateAsset(assetName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setUser method, of class Hidra.
     */
    @Test
    public void testSetUser() {
        System.out.println("setUser");
        String name = "";
        String email = "";
        Hidra instance = new Hidra();
        instance.setUser(name, email);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUser method, of class Hidra.
     */
    @Test
    public void testGetUser() {
        System.out.println("getUser");
        Hidra instance = new Hidra();
        Map<String, String> expResult = null;
        Map<String, String> result = instance.getUser();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setRemoteRepo method, of class Hidra.
     */
    @Test
    public void testSetRemoteRepo() {
        System.out.println("setRemoteRepo");
        String url = "";
        Hidra instance = new Hidra();
        instance.setRemoteRepo(url);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRemoteRepo method, of class Hidra.
     */
    @Test
    public void testGetRemoteRepo() {
        System.out.println("getRemoteRepo");
        Hidra instance = new Hidra();
        String expResult = "";
        String result = instance.getRemoteRepo();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createTemplateAsset method, of class Hidra.
     */
    @Test
    public void testCreateTemplateAsset() throws Exception {
        System.out.println("createTemplateAsset");
        Hidra instance = new Hidra();
        instance.createTemplateAsset();
       
    }
    
}
