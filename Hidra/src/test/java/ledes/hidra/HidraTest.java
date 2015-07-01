/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ledes.hidra;

import java.io.File;
import java.util.List;
import java.util.Map;
import ledes.hidra.asset.ClassificationType;
import ledes.hidra.asset.RelatedAssetType;
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
     * Test of startRepository method, of class Hidra.
     */
    @Test
    public void testStartRepository() {
        System.out.println("startRepository");
        String localPath = "";
        Hidra instance = null;
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
    public void testStartSynchronizedRepository() {
        System.out.println("startSynchronizedRepository");
        String localPath = "";
        String remotePath = "";
        Hidra instance = null;
        boolean expResult = false;
        boolean result = instance.startSynchronizedRepository(localPath, remotePath);
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
        String nameAsset = "ativo2";
        Hidra instance = new Hidra("/home/danielli/prototipo");
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
        Hidra instance = null;
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
        Hidra instance = null;
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
        String assetPath = "";
        Hidra instance = null;
        boolean expResult = false;
        boolean result = instance.validateAsset(assetPath);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeAsset method, of class Hidra.
     */
    @Test
    public void testRemoveAsset() throws Exception {
        System.out.println("removeAsset");
        String assetId = "";
        Hidra instance = null;
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
        Hidra instance = null;
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
        Hidra instance = null;
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
        Hidra instance = null;
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
        Hidra instance = null;
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
        Hidra instance = null;
        String expResult = "";
        String result = instance.getRelatedAssets(assetId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getXMLElement method, of class Hidra.
     */
    @Test
    public void testGetXMLElement() {
        System.out.println("getXMLElement");
        String assetId = "";
        Hidra instance = null;
        List<RelatedAssetType> expResult = null;
        List<RelatedAssetType> result = instance.getXMLElement(assetId);
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
        Hidra instance = null;
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
        Hidra instance = null;
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
        Hidra instance = null;
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
        Hidra instance = null;
        File expResult = null;
        File result = instance.downloadAsset(assetId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
