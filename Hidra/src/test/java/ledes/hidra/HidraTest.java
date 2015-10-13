/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ledes.hidra;

import java.io.IOException;
import javax.xml.bind.JAXBException;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 *
 * @author pedro
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

    //@Test
    public void testStartRepository() throws IOException, JAXBException {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>start");
        
        Hidra instance = new Hidra();
        
        boolean result = instance.startRepository();
       
        assertEquals(true, result);
        
    }
    
    //@Test
    public void testStartBareRepository() throws IOException, JAXBException{
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>StartBare");
        Hidra instance = new Hidra();
        
        boolean result = instance.startRepositoryBare();
        assertEquals(true, result);
    }
    
    //@Test
    public void testCloneLocal(){
    
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>CloneLocal");
        
        Hidra instance = new Hidra();
        
        boolean result = instance.startSynchronizedRepository();
        assertEquals(true, result);
        
    }
    
    //@Test
    public void testCloneAutentificacao(){
    
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>CloneAutentificacao");
        
        Hidra instance = new Hidra();
        boolean result = instance.startSynchronizedRepositoryAuthentification();
        assertEquals(true, result);
    }
    
    //@Test
    public void testAddAsset(){
    
        System.out.println("testAddAsset");
        Hidra instance = new Hidra();
        boolean result = instance.addAsset("hidra");
        assertEquals(true, result);
    }

}
