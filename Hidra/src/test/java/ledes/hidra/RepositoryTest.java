/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ledes.hidra;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import javax.xml.bind.JAXBException;
import ledes.hidra.asset.ArtifactType;
import ledes.hidra.asset.Asset;
import ledes.hidra.asset.ClassificationType;
import ledes.hidra.asset.SolutionType;
import ledes.hidra.asset.UsageType;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author danielli
 */
public class RepositoryTest {

    private final String localPath;

    public RepositoryTest() {
        localPath = System.getProperty("user.home") + File.separator + "repo3";
       
    }

    @BeforeClass
    public static void setUpClass() {

    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        // String localPath = "/home/danielli/repo2";
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of init method, of class Repository.
     * @throws java.io.IOException
     * @throws javax.xml.bind.JAXBException
     */
    @Test
    public void testInit() throws IOException, JAXBException {

        System.out.println("init");
        System.out.println(localPath);
        Repository instance = new Repository(localPath);

        //assertFalse("isRepositorio esperado eh false", instance.isRepository());
        boolean expResult = true;
        boolean result = instance.init();
        assertEquals(expResult, result);

    }

    /**
     * Test of cloneRepository method, of class Repository.
     */
    //@Test
    public void testCloneRepository() {
        System.out.println("cloneRepository");
        Repository instance = null;
        boolean expResult = false;
        boolean result = instance.cloneRepository();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isRepository method, of class Repository.
     */
    @Test
    public void testIsRepository_0args() {
        System.out.println("isRepository");
        Repository instance = new Repository(localPath);

        boolean expResult = true;
        boolean result = instance.isRepository();
        assertEquals(expResult, result);

    }

    /**
     * Test of getLocalPath method, of class Repository.
     */
    //@Test
    public void testGetLocalPath() {
        System.out.println("getLocalPath");
        Repository instance = new Repository(localPath);
        String expResult = System.getProperty("user.home") + "/repo2";
        String result = instance.getLocalPath();
        assertEquals(expResult, result);

    }



    /**
     * Test of getRemotePath method, of class Repository.
     */
    //@Test
    public void testGetRemotePath() {
        System.out.println("getRemotePath");
        Repository instance = null;
        String expResult = "";
        String result = instance.getRemotePath();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setRemotePath method, of class Repository.
     */
    //@Test
    public void testSetRemotePath() {
        System.out.println("setRemotePath");
        String remotePath = "";
        Repository instance = null;
        instance.setRemotePath(remotePath);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeRepository method, of class Repository.
     */
    //@Test
    public void testRemoveRepository() {
        System.out.println("removeRepository");
        Repository instance = null;
        instance.removeRepository();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

   @Test
    public void testManifestExist() {
        System.out.println("ManifestExist");
        Repository instance = new Repository(localPath);
        boolean expResult = true;
        File repo = new File(localPath);
        System.out.println(repo.getAbsolutePath());
        boolean result = instance.manifestExist(new File(localPath + "/asset"));
        System.out.println(localPath + "/asset");
        assertEquals(expResult, result);

    }

    /**
     * Test of validateAsset method, of class Repository.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testValidateAsset_String() throws Exception {
        System.out.println("validateAsset");
        String assetPath = System.getProperty("user.home") + "/repo3/asset/";
        Repository instance = new Repository(localPath);
        boolean expResult = true;
        boolean result = instance.validateAsset(assetPath);
        assertEquals(expResult, result);

    }

    /**
     * Test of readAsset method, of class Repository.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testReadAsset() throws Exception {
        System.out.println("readAsset");

        Repository instance = new Repository(localPath);
        Asset result = instance.readAsset("asset");

        assertTrue("o resultado eh :", result != null);

    }

    /*  *
     * Test of validateAsset method, of class Repository.
     */
    @Test
    public void testValidateAsset_Asset_String() throws JAXBException, FileNotFoundException {
        System.out.println("validateAsset");

        Repository instance = new Repository(localPath);
        Asset asset = instance.readAsset("asset");
        String assetPath = System.getProperty("user.home") + "/repo3/asset";

        boolean expResult = true;
        boolean result = instance.validateAsset(asset, assetPath);
        assertEquals(expResult, result);

    }

    /**
     * Test of addAsset method, of class Repository.
     */
   @Test
    public void testAddAsset() throws Exception {
        System.out.println("addAsset");
        String nameAsset = "asset";
        Repository instance = new Repository(localPath);
        boolean expResult = true;
        boolean result = instance.addAsset(nameAsset);
        assertEquals(expResult, result);

    }

    /**
     * Test of getSolution method, of class Repository.
     * @throws javax.xml.bind.JAXBException
     * @throws java.io.FileNotFoundException
     */
    @Test
    public void testGetSolution() throws JAXBException, FileNotFoundException {
        System.out.println("getSolution");

        String assetId = "asset";
        Repository instance = new Repository(localPath);
        boolean expResult = true;
        
        
        
        String result = instance.getSolution(assetId);
        System.out.println(result);
        assertEquals(expResult, result != null);

    }

    /**
     * Test of setSolutionType method, of class Repository.
     * @throws javax.xml.bind.JAXBException
     */
    //@Test
    public void testSetSolutionType() throws JAXBException{
        System.out.println("setSolutionType");
        String assetId = "asset";
        
        ArtifactType a = new ArtifactType();
        a.setId("213546");
        a.setName("ARTEFATO NOVO");
        a.setReference("/ALGUMA PASTA");
        a.setType("FOLDER");
        a.setVersion("LAST VERSION");
        
        SolutionType solution = new SolutionType();
        solution.getArtifacts().getArtifact().add(a);
        solution.getImplementation().getArtifact().add(a);
        solution.getRequirements().getArtifact().add(a);
        solution.getDesign().getArtifact().add(a);
        solution.getTest().getArtifact().add(a);
        
        Repository instance = new Repository(localPath);
        boolean expResult = true;
        boolean result = instance.setSolutionType(assetId, solution);
        
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getClassification method, of class Repository.
     * @throws javax.xml.bind.JAXBException
     */
    @Test
    public void testGetClassification() throws JAXBException, FileNotFoundException {
        System.out.println("getClassification");
        String assetId = "asset";
        Repository instance = new Repository(localPath);
        boolean expResult = true;
        
        String result = instance.getClassification(assetId);
        System.out.println(result);
        assertEquals(expResult, result != null);
    }

    /**
     * Test of setClassification method, of class Repository.
     */
    //@Test
    public void testSetClassification() {
        System.out.println("setClassification");
        ClassificationType classification = null;
        Repository instance = null;
        boolean expResult = false;
        boolean result = instance.setClassification(classification);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUsage method, of class Repository.
     * @throws javax.xml.bind.JAXBException
     * @throws java.io.FileNotFoundException
     */
    @Test
    public void testGetUsage() throws JAXBException, FileNotFoundException {
        System.out.println("getUsage");
        String assetId = "asset";
        Repository instance = new Repository(localPath);
        boolean expResult = true;
       
        String result = instance.getUsage(assetId);
        System.out.println(result);
        
        
        assertEquals(expResult, result != null);
        
    }

    /**
     * Test of setUsage method, of class Repository.
     */
    //@Test
    public void testSetUsage() {
        System.out.println("setUsage");
        UsageType usage = null;
        Repository instance = new Repository(localPath);
        boolean expResult = false;
        boolean result = instance.setUsage(usage);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRelatedAssets method, of class Repository.
     */
    //@Test
    public void testGetRelatedAssets() {
        System.out.println("getRelatedAssets");
        String assetId = "";
        Repository instance = null;
        List<Asset> expResult = null;
        List<Asset> result = instance.getRelatedAssets(assetId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setRelatedAsset method, of class Repository.
     */
    //@Test
    public void testSetRelatedAsset() {
        System.out.println("setRelatedAsset");
        String assetId = "";
        String relatedId = "";
        Repository instance = null;
        boolean expResult = false;
        boolean result = instance.setRelatedAsset(assetId, relatedId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLog method, of class Repository.
     */
    //@Test
    public void testGetLog() {
        System.out.println("getLog");
        String assetId = "";
        boolean complete = false;
        Repository instance = null;
        String expResult = "";
//        String result = instance.getLog(assetId, complete);
//        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of listAssets method, of class Repository.
     */
    //@Test
    public void testListAssets() {
        System.out.println("listAssets");
        Repository instance = null;
        List<Asset> expResult = null;
//        List<Asset> result = instance.listAssets();
//        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of downloadAsset method, of class Repository.
     * @throws java.io.FileNotFoundException
     */
    //@Test
    public void testDownloadAsset() throws FileNotFoundException {
        System.out.println("downloadAsset");
        String assetId = "/arquivos/JSFImmediate.zip";
        
        Repository instance = new Repository(localPath);
        
        
        File result = instance.downloadAsset(assetId);
        
        assertEquals(true, result != null);
    }

    /**
     * Test of removeAsset method, of class Repository.
     */
    //@Test
    public void testRemoveAsset() {
        System.out.println("removeAsset");
        String assetId = "";
        Repository instance = null;
        boolean expResult = false;
        boolean result = instance.removeAsset(assetId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isRepository method, of class Repository.
     */
    //@Test
    public void testIsRepository_String() {
        System.out.println("isRepository");
        String directory = "";
        Repository instance = null;
        boolean expResult = false;
        boolean result = instance.isRepository(directory);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getExceptionList method, of class Repository.
     */
    @Test
    public void testGetExceptionList() {
        System.out.println("getExceptionList");
        Repository instance = null;
        List<String> expResult = null;
        List<String> result = instance.getExceptionList();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setExceptionList method, of class Repository.
     */
    @Test
    public void testSetExceptionList() {
        System.out.println("setExceptionList");
        List<String> exceptionList = null;
        Repository instance = null;
        instance.setExceptionList(exceptionList);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createSchema method, of class Repository.
     */
    @Ignore
    @Test
    public void testCreateSchema() throws Exception {
        System.out.println("createSchema");
        Repository instance = new Repository(localPath);
        boolean expResult = false;
      //  boolean result = instance.createSchema();
       // assertEquals(expResult, result);
  
    }

    /**
     * Test of validateAll method, of class Repository.
     */
    @Test
    public void testValidateAll() throws Exception {
        System.out.println("validateAll");
        String assetName = "";
        String assetPath = "";
        File path = null;
        Repository instance = null;
        boolean expResult = false;
        boolean result = instance.validateAll(assetName, assetPath, path);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of saveChanges method, of class Repository.
     */
    @Test
    public void testSaveChanges() throws Exception {
        System.out.println("saveChanges");
        String message = "";
        String nameAsset = "";
        Repository instance = null;
        boolean expResult = false;
        boolean result = instance.saveChanges(message, nameAsset);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of describeAssets method, of class Repository.
     */
    @Test
    public void testDescribeAssets() {
        System.out.println("describeAssets");
        Repository instance = null;
        String expResult = "";
        String result = instance.describeAssets();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateRepository method, of class Repository.
     */
    @Test
    public void testUpdateRepository() {
        System.out.println("updateRepository");
        String user = "";
        String password = "";
        Repository instance = null;
        boolean expResult = false;
        boolean result = instance.updateRepository(user, password);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of synchronizeRepository method, of class Repository.
     */
    @Test
    public void testSynchronizeRepository() {
        System.out.println("synchronizeRepository");
        Repository instance = null;
        boolean expResult = false;
        boolean result = instance.synchronizeRepository();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateAsset method, of class Repository.
     */
    @Test
    public void testUpdateAsset() throws Exception {
        System.out.println("updateAsset");
        String assetName = "";
        Repository instance = null;
        boolean expResult = false;
        boolean result = instance.updateAsset(assetName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    
    
    
    
}
