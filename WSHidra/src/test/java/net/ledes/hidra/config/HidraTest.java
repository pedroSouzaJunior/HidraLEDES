/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package test.java.net.ledes.hidra.config;

import net.ledes.hidra.config.Hidra;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author geraldo
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
     * Test of someMethod method, of class Hidra.
     */
    @Test
    public void testSomeMethod() {
        System.out.println("someMethod");
        char arg = ' ';
        Hidra instance = new Hidra();
        int expResult = 0;
        int result = instance.someMethod(arg);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
