/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ledes.hidra.core;

import java.io.File;
import java.util.Map;
import java.util.Set;
import org.eclipse.jgit.api.MergeResult;
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
public class GitFacadeTest {
    
    public GitFacadeTest() {
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
     * Test of start method, of class GitFacade.
     */
    @Ignore
    @Test
    public void testStart() {
        System.out.println("start");
        File directory = null;
        GitFacade instance = null;
        boolean expResult = false;
        boolean result = instance.start(directory);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of cloneRepository method, of class GitFacade.
     */
     @Ignore
    @Test
    public void testCloneRepository_4args() throws Exception {
        System.out.println("cloneRepository");
        File directory = null;
        String remotePath = "";
        String user = "";
        String password = "";
        GitFacade instance = null;
        boolean expResult = false;
        boolean result = instance.cloneRepository(directory, remotePath, user, password);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of cloneRepository method, of class GitFacade.
     */
     @Ignore
    @Test
    public void testCloneRepository_File_String() throws Exception {
        System.out.println("cloneRepository");
        File directory = null;
        String remotePath = "";
        GitFacade instance = null;
        boolean expResult = false;
        boolean result = instance.cloneRepository(directory, remotePath);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isRepositoryInitialized method, of class GitFacade.
     */
     @Ignore
    @Test
    public void testIsRepositoryInitialized_0args() {
        System.out.println("isRepositoryInitialized");
        GitFacade instance = null;
        boolean expResult = false;
        boolean result = instance.isRepositoryInitialized();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isRepositoryInitialized method, of class GitFacade.
     */
     @Ignore
    @Test
    public void testIsRepositoryInitialized_String() {
        System.out.println("isRepositoryInitialized");
        String directory = "";
        GitFacade instance = null;
        boolean expResult = false;
        boolean result = instance.isRepositoryInitialized(directory);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setConfigurationUser method, of class GitFacade.
     */
     @Ignore
    @Test
    public void testSetConfigurationUser() throws Exception {
        System.out.println("setConfigurationUser");
        String name = "";
        String email = "";
        GitFacade instance = null;
        instance.setConfigurationUser(name, email);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getConfigurationUser method, of class GitFacade.
     */
     @Ignore
    @Test
    public void testGetConfigurationUser() {
        System.out.println("getConfigurationUser");
        GitFacade instance = null;
        Map<String, String> expResult = null;
        Map<String, String> result = instance.getConfigurationUser();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of unSetConfigurationUser method, of class GitFacade.
     */
     @Ignore
    @Test
    public void testUnSetConfigurationUser() {
        System.out.println("unSetConfigurationUser");
        GitFacade instance = null;
        instance.unSetConfigurationUser();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setConfigRemote method, of class GitFacade.
     */
     @Ignore
    @Test
    public void testSetConfigRemote() throws Exception {
        System.out.println("setConfigRemote");
        String remoteRepository = "";
        GitFacade instance = null;
        instance.setConfigRemote(remoteRepository);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of unSetConfigRemote method, of class GitFacade.
     */
     @Ignore
    @Test
    public void testUnSetConfigRemote() {
        System.out.println("unSetConfigRemote");
        GitFacade instance = null;
        instance.unSetConfigRemote();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getConfigRemote method, of class GitFacade.
     */
     @Ignore
    @Test
    public void testGetConfigRemote() {
        System.out.println("getConfigRemote");
        GitFacade instance = null;
        String expResult = "";
        String result = instance.getConfigRemote();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hasRemoteRepository method, of class GitFacade.
     */
     @Ignore
    @Test
    public void testHasRemoteRepository() {
        System.out.println("hasRemoteRepository");
        GitFacade instance = null;
        boolean expResult = false;
        boolean result = instance.hasRemoteRepository();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of add method, of class GitFacade.
     */
    @Ignore
    @Test
    public void testAdd() throws Exception {
        System.out.println("add");
        String fileName = "";
        GitFacade instance = new GitFacade("/home/danielli/add");
        boolean expResult = true;
        boolean result = instance.add("novo/add6.txt");
         assertEquals(expResult, result);
       
    }

    /**
     * Test of addFileFull method, of class GitFacade.
     */
     @Ignore
    @Test
    public void testAddFileFull() throws Exception {
        System.out.println("addFileFull");
        String fileName = "";
        GitFacade instance = null;
        boolean expResult = false;
        boolean result = instance.addFileFull(fileName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of commit method, of class GitFacade.
     */
     @Ignore
    @Test
    public void testCommit() throws Exception {
        System.out.println("commit");
        String message = "";
        GitFacade instance = null;
        boolean expResult = false;
        boolean result = instance.commit(message);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of status method, of class GitFacade.
     */
     @Ignore
    @Test
    public void testStatus() throws Exception {
        System.out.println("status");
        GitFacade instance = null;
        Map<String, Set<String>> expResult = null;
        Map<String, Set<String>> result = instance.status();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of remove method, of class GitFacade.
     */
     @Ignore
    @Test
    public void testRemove() {
        System.out.println("remove");
        String filename = "";
        GitFacade instance = null;
        boolean expResult = false;
        boolean result = instance.remove(filename);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLogs method, of class GitFacade.
     */
     @Ignore
    @Test
    public void testGetLogs_0args() throws Exception {
        System.out.println("getLogs");
        GitFacade instance = null;
        String expResult = "";
        String result = instance.getLogs();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLogs method, of class GitFacade.
     */
     @Ignore
    @Test
    public void testGetLogs_String() throws Exception {
        System.out.println("getLogs");
        String nameAsset = "";
        GitFacade instance = null;
        String expResult = "";
        String result = instance.getLogs(nameAsset);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of push method, of class GitFacade.
     */
     @Ignore
    @Test
    public void testPush() throws Exception {
        System.out.println("push");
        String user = "";
        String password = "";
        GitFacade instance = null;
        boolean expResult = false;
        boolean result = instance.push(user, password);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of pull method, of class GitFacade.
     */
     @Ignore
    @Test
    
    public void testPull() throws Exception {
        System.out.println("pull");
        String user = "";
        String password = "";
        GitFacade instance = null;
        boolean expResult = false;
        boolean result = instance.pull(user, password);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of resolveConflictsMerge method, of class GitFacade.
     */
     @Ignore
    @Test
    
    public void testResolveConflictsMerge() {
        System.out.println("resolveConflictsMerge");
        MergeResult conflict = null;
        GitFacade instance = null;
        instance.resolveConflictsMerge(conflict);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of merge method, of class GitFacade.
     */
     @Ignore
    @Test
    public void testMerge() throws Exception {
        System.out.println("merge");
        String branch = "";
        GitFacade instance = null;
        boolean expResult = false;
        boolean result = instance.merge(branch);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkout method, of class GitFacade.
     */
     @Ignore
    @Test
    public void testCheckout() {
        System.out.println("checkout");
        String branch = "";
        GitFacade instance = null;
        boolean expResult = false;
        boolean result = instance.checkout(branch);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkoutCreateBranch method, of class GitFacade.
     */
     @Ignore
    @Test
    public void testCheckoutCreateBranch() {
        System.out.println("checkoutCreateBranch");
        String branch = "";
        GitFacade instance = null;
        boolean expResult = false;
        boolean result = instance.checkoutCreateBranch(branch);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createLightTag method, of class GitFacade.
     */
     @Ignore
    @Test
    
    public void testCreateLightTag() {
        System.out.println("createLightTag");
        String tagName = "";
        String tagMsg = "";
        GitFacade instance = null;
        boolean expResult = false;
        boolean result = instance.createLightTag(tagName, tagMsg);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createAnnotatedTag method, of class GitFacade.
     */
     @Ignore
    @Test
    public void testCreateAnnotatedTag() {
        System.out.println("createAnnotatedTag");
        String tagName = "";
        String tagMgs = "";
        GitFacade instance = null;
        boolean expResult = false;
        boolean result = instance.createAnnotatedTag(tagName, tagMgs);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of listTags method, of class GitFacade.
     */
     @Ignore
    @Test
    public void testListTags() {
        System.out.println("listTags");
        GitFacade instance = null;
        String expResult = "";
        String result = instance.listTags();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of tagDelete method, of class GitFacade.
     */
     @Ignore
    @Test
    public void testTagDelete() {
        System.out.println("tagDelete");
        String nameTags = "";
        GitFacade instance = null;
        boolean expResult = false;
        boolean result = instance.tagDelete(nameTags);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of showTags method, of class GitFacade.
     */
     @Ignore
    @Test
    public void testShowTags() {
        System.out.println("showTags");
        String nameTag = "";
        GitFacade instance = null;
        boolean expResult = false;
        boolean result = instance.showTags(nameTag);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of showBranches method, of class GitFacade.
     */
     @Ignore
    @Test
    public void testShowBranches() {
        System.out.println("showBranches");
        GitFacade instance = null;
        boolean expResult = false;
        boolean result = instance.showBranches();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createBranch method, of class GitFacade.
     */
     @Ignore
    @Test
    public void testCreateBranch() {
        System.out.println("createBranch");
        String nameBranch = "";
        GitFacade instance = null;
        String expResult = "";
        String result = instance.createBranch(nameBranch);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of archive method, of class GitFacade.
     */
     @Ignore
    @Test
    public void testArchive_3args() throws Exception {
        System.out.println("archive");
        String format = "";
        String branch = "";
        String fileDest = "";
        GitFacade instance = null;
        boolean expResult = false;
        boolean result = instance.archive(format, branch, fileDest);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of archive method, of class GitFacade.
     */
     @Ignore
    @Test
    public void testArchive_4args() throws Exception {
        System.out.println("archive");
        String format = "";
        String branch = "";
        String fileDest = "";
        String prefix = "";
        GitFacade instance = null;
        boolean expResult = false;
        boolean result = instance.archive(format, branch, fileDest, prefix);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
