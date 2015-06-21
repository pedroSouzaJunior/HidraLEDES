/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ledes.hidra.core;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.eclipse.jgit.api.MergeResult;
import org.eclipse.jgit.api.errors.GitAPIException;
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
    
    private String localPath ="/home/danielli/cloneGitFacade"; 
    private File dir = new File(localPath);
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
//    @Test
//    public void testStart() {
//        System.out.println("start");
//        File directory = dir;
//        GitFacade instance = new GitFacade(localPath);
//        boolean expResult = true;
//        boolean result = instance.start(directory);
//        assertEquals(expResult, result);
//        
//    }

    /**
     * Test of cloneRepository method, of class GitFacade.
     */
<<<<<<< HEAD
    
    
    //@Test
=======
    @Ignore
    @Test
>>>>>>> ea3306b213543d0f8bbdd642f00ef7ef8b9af084
    public void testCloneRepository() throws Exception {
        System.out.println("cloneRepository");
        File directory = dir;
        String remotePath = "/home/danielli/GitFacade";
        GitFacade instance = new GitFacade(localPath);
        boolean expResult = false;
        boolean result = instance.cloneRepository(directory, remotePath);
        assertEquals(expResult, result);
     
    }

    /**
     * Test of isRepositoryInitialized method, of class GitFacade.
     */
    @Ignore
    @Test
    public void testIsRepositoryInitialized_0args() {
        System.out.println("isRepositoryInitialized");
        GitFacade instance = new GitFacade(localPath);
        boolean expResult = true;
        boolean result = instance.isRepositoryInitialized();
        assertEquals(expResult, result);
      
    }

    
    /**
     * Test of setConfigurationUser method, of class GitFacade.
     */
    @Ignore
    @Test
    public void testSetConfigurationUser() {
        System.out.println("setConfigurationUser");
        String name = "danielli";
        String email = "danielli.urbieta@gmail.com";
        GitFacade instance = new GitFacade(localPath);
        instance.setConfigurationUser(name, email);
       
    }

    /**
     * Test of getConfigurationUser method, of class GitFacade.
     */
    @Ignore
    @Test
    public void testGetConfigurationUser() {
        System.out.println("getConfigurationUser");
        GitFacade instance = new GitFacade(localPath);
        Map<String, String> expResult = (Map<String, String>) new HashMap<>().put("danielli", "danielli.urbieta@gmail.com");
        Map<String, String> result = instance.getConfigurationUser();
        assertEquals(expResult, result);
      
    }

    /**
     * Test of unSetConfigurationUser method, of class GitFacade.
     */
    @Ignore
    @Test
    public void testUnSetConfigurationUser() {
        System.out.println("unSetConfigurationUser");
        GitFacade instance = new GitFacade(localPath);
        instance.unSetConfigurationUser();
        
    }

    /**
     * Test of setConfigRemote method, of class GitFacade.
     */
//    @Test
//    public void testSetConfigRemote() {
//        System.out.println("setConfigRemote");
//        String remoteRepository = "https://github.com/DanielliUrbieta/MysticalSpaceFinal.git";
//        GitFacade instance = new GitFacade(localPath);
//        instance.setConfigRemote(remoteRepository);
//       
//    }

//    /**
//     * Test of unSetConfigRemote method, of class GitFacade.
//     */
//    @Test
//    public void testUnSetConfigRemote() {
//        System.out.println("unSetConfigRemote");
//        GitFacade instance = null;
//        instance.unSetConfigRemote();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of getConfigRemote method, of class GitFacade.
     */
    @Ignore
    @Test
    public void testGetConfigRemote() {
        System.out.println("getConfigRemote");
        GitFacade instance = new GitFacade(localPath);
        String expResult = "/home/danielli/GitFacade";
        String result = instance.getConfigRemote();
        assertEquals(expResult, result);
     
    }

    /**
     * Test of hasRemoteRepository method, of class GitFacade.
     */
    @Ignore
    @Test
    public void testHasRemoteRepository() {
        System.out.println("hasRemoteRepository");
        GitFacade instance = new GitFacade(localPath);
        boolean expResult = true;
        boolean result = instance.hasRemoteRepository();
        assertEquals(expResult, result);
       
    }

    /**
     * Test of add method, of class GitFacade.
     */
<<<<<<< HEAD
    
    //@Test
=======
    @Ignore
    @Test
>>>>>>> ea3306b213543d0f8bbdd642f00ef7ef8b9af084
    public void testAdd() throws Exception {
        System.out.println("add");
        String fileName = "pull.txt";
        GitFacade instance = new GitFacade(localPath);
        boolean expResult = true;
        boolean result = instance.add(fileName);
       
    }

    /**
     * Test of commit method, of class GitFacade.
     */
<<<<<<< HEAD
    //@Test
=======
    @Ignore
    @Test
>>>>>>> ea3306b213543d0f8bbdd642f00ef7ef8b9af084
    public void testCommit() throws GitAPIException {
        System.out.println("commit");
        String message = "testando pull";
        GitFacade instance = new GitFacade(localPath);
        boolean expResult = true;
        boolean result = instance.commit(message);
        assertEquals(expResult, result);
       
    }

    /**
     * Test of status method, of class GitFacade.
     * @throws java.lang.Exception
     */
<<<<<<< HEAD
    //@Test
=======
    @Ignore
    @Test
>>>>>>> ea3306b213543d0f8bbdd642f00ef7ef8b9af084
    public void testStatus() throws Exception {
        System.out.println("status");
        GitFacade instance = new GitFacade(localPath);
        Map<String, Set<String>> expResult = null;
        Map<String, Set<String>> result = instance.status();
        System.out.println(instance.status());
//        assertEquals(expResult, result);
        
    }

    /**
     * Test of remove method, of class GitFacade.
     */
  
    //@Test
    public void testRemove() {
        System.out.println("remove");
        String filename = "add.txt";
        GitFacade instance = new GitFacade(localPath);
        boolean expResult = true;
        boolean result = instance.remove(filename);
        assertEquals(expResult, result);
       
    }

    /**
     * Test of getLogs method, of class GitFacade.
     */
    @Ignore
    @Test
    public void testGetLogs() throws Exception {
        System.out.println("getLogs");
        GitFacade instance = new GitFacade(localPath);
        String expResult = "";
        String result = instance.getLogs();
        System.out.println(result);
    }

    /**
     * Test of push method, of class GitFacade.
     */
<<<<<<< HEAD
    //@Test
=======
    @Ignore
    @Test
>>>>>>> ea3306b213543d0f8bbdd642f00ef7ef8b9af084
    public void testPush() throws GitAPIException {
        System.out.println("push");
        String user =  "Danielli Urbieta";
        String password = "";
        GitFacade instance = new GitFacade(localPath);
        boolean expResult = true;
        boolean result = instance.push(user, password);
        assertEquals(expResult, result);
       
    }

    /**
     * Test of pull method, of class GitFacade.
     * @throws java.lang.Exception
     */
<<<<<<< HEAD
    //@Test
=======
    @Ignore
    @Test
>>>>>>> ea3306b213543d0f8bbdd642f00ef7ef8b9af084
    public void testPull() throws Exception {
        System.out.println("pull");
        GitFacade instance = new GitFacade(localPath);
        boolean expResult = true;
        boolean result = instance.pull();
        assertEquals(expResult, result);
       
    }

    /**
     * Test of resolveConflictsMerge method, of class GitFacade.
     */
  //  @Test
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
   // @Test
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
<<<<<<< HEAD
    //@Test
=======
    @Ignore
    @Test
>>>>>>> ea3306b213543d0f8bbdd642f00ef7ef8b9af084
    public void testCheckout() {
        System.out.println("checkout");
        String branch = "master";
        GitFacade instance = new GitFacade(localPath);
        boolean expResult = true;
        boolean result = instance.checkout(branch);
        assertEquals(expResult, result);
       
    }

    /**
     * Test of checkoutCreateBranch method, of class GitFacade.
     */
    /*
    @Test
    public void testCheckoutCreateBranch() {
        System.out.println("checkoutCreateBranch");
        String branch = "checkoutBranch";
        GitFacade instance = new GitFacade(localPath);
        boolean expResult = true;
        boolean result = instance.checkoutCreateBranch(branch);
        assertEquals(expResult, result);
        
    }
*/
    /**
     * Test of createLightTag method, of class GitFacade.
     */
  //  @Test
    public void testCreateLightTag() {
        System.out.println("createLightTag");
        String tagName = "Simples";
        String tagMsg = "testando tag";
        GitFacade instance = new GitFacade(localPath);
        boolean expResult = true;
        boolean result = instance.createLightTag(tagName, tagMsg);
        assertEquals(expResult, result);
       
    }

    /**
     * Test of createAnnotatedTag method, of class GitFacade.
     */
  //  @Test
    public void testCreateAnnotatedTag() {
        System.out.println("createAnnotatedTag");
        String tagName = "Complicada";
        String tagMgs = "testando tag com anotações";
        GitFacade instance = new GitFacade(localPath);
        boolean expResult = true;
        boolean result = instance.createAnnotatedTag(tagName, tagMgs);
        assertEquals(expResult, result);
       
    }

    /**
     * Test of listTags method, of class GitFacade.
     */
    @Ignore
    @Test
    public void testListTags() {
        System.out.println("listTags");
        GitFacade instance = new GitFacade(localPath);
        boolean expResult = true;
        boolean result = instance.listTags();
        assertEquals(expResult, result);
       
    }

    /**
     * Test of tagDelete method, of class GitFacade.
     */
    @Ignore
    @Test
    public void testTagDelete() {
        System.out.println("tagDelete");
        String nameTags = "Simples";
        GitFacade instance = new GitFacade(localPath);
        boolean expResult = true;
        boolean result = instance.tagDelete(nameTags);
        assertEquals(expResult, result);
       
    }

    /**
     * Test of showTags method, of class GitFacade.
     */
    @Ignore
    @Test
    public void testShowTags() {
        System.out.println("showTags");
        String nameTag = "Complicada";
        GitFacade instance = new GitFacade(localPath);
        boolean expResult = true;
        boolean result = instance.showTags(nameTag);
        assertEquals(expResult, result);
     
    }

    /**
     * Test of showBranches method, of class GitFacade.
     */
    @Ignore
    @Test
    public void testShowBranches() {
        System.out.println("showBranches");
        GitFacade instance = new GitFacade(localPath);
        boolean expResult = true;
        boolean result = instance.showBranches();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of start method, of class GitFacade.
     */
    //@Test
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
     * Test of isRepositoryInitialized method, of class GitFacade.
     */
    //@Test
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
     * Test of setConfigRemote method, of class GitFacade.
     */
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
    @Test
    public void testUnSetConfigRemote() {
        System.out.println("unSetConfigRemote");
        GitFacade instance = null;
        instance.unSetConfigRemote();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkoutCreateBranch method, of class GitFacade.
     */
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
     * Test of createBranch method, of class GitFacade.
     */
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
    @Test
    public void testArchive_3args() throws Exception {
        System.out.println("archive");
        String format = "tar";
        String branch = "master";
        String fileDest = "tar";
        GitFacade instance = new GitFacade(localPath);
        boolean expResult = true;
        boolean result = instance.archive(format, branch, fileDest);
        assertEquals(expResult, result);
       
    }

    /**
     * Test of archive method, of class GitFacade.
     */
   // @Test
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

    /**
     * Test of createBranch method, of class GitFacade.
     */
    /*
    @Test
    public void testCreateBranch() {
        System.out.println("createBranch");
        String nameBranch = "branchNovo";
        GitFacade instance = new GitFacade(localPath);
        String expResult = "";
        String result = instance.createBranch(nameBranch);
        assertEquals(expResult, result);
        instance.showBranches();
    }
    */
    
}
