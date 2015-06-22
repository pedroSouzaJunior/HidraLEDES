/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ledes.hidra.dao;

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
public class HidraDAOTest {
    
    public HidraDAOTest() {
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
     * Test of connection method, of class HidraDAO.
     */
    @Test
    public void testConnection() {
        System.out.println("connection");
        HidraDAO instance = new HidraDAO("/home/danielli/testeDB");
        boolean expResult = true;
        boolean result = instance.connection();
        assertEquals(expResult, result);
       
    }

    /**
     * Test of insertion method, of class HidraDAO.
     */
    
    @Test
    public void testInsertion() {
        System.out.println("insertion");
        String name = "NOVO";
        String id = "1";
        HidraDAO instance = new HidraDAO("/home/danielli/testeDB");
        boolean expResult = false;
        boolean result = instance.insertion(name, id);
        assertEquals(expResult, result);
       
    }

    /**
     * Test of find method, of class HidraDAO.
     */
   
    @Test
    public void testFind() {
        System.out.println("find");
        String name = "NOVO";
        String id = "2";
        HidraDAO instance = new HidraDAO("/home/danielli/testeDB");
        boolean expResult = true;
        boolean result = instance.find(name, id);
        assertEquals(expResult, result);
       
    }
    
}
