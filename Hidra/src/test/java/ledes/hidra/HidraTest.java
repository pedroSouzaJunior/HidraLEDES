/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ledes.hidra;

import java.io.IOException;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.xml.bind.JAXBException;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import ledes.hidra.asset.Asset;
import ledes.hidra.asset.ProfileType;
import ledes.hidra.dao.HidraDataBase;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

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
    public void testStartBareRepository() throws IOException, JAXBException {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>StartBare");
        Hidra instance = new Hidra();

        boolean result = instance.startRepositoryBare();
        assertEquals(true, result);
    }

    //@Test
    public void testCloneLocal() {

        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>CloneLocal");

        Hidra instance = new Hidra();

        boolean result = instance.startSynchronizedRepository();
        assertEquals(true, result);

    }

    //@Test
    public void testCloneAutentificacao() {

        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>CloneAutentificacao");

        Hidra instance = new Hidra();
        boolean result = instance.startSynchronizedRepositoryAuthentification();
        assertEquals(true, result);
    }

    //@Test
    public void testAddAsset() {

        System.out.println("testAddAsset");
        Hidra instance = new Hidra();
        boolean result = instance.addAsset("hidra");
        assertEquals(true, result);
    }

    //@Test
    public void testSaveChanges() {

        System.out.println("SaveChanges");
        Hidra instance = new Hidra();
        boolean result = instance.save("salvando");
        assertEquals(true, result);
    }

    //@Test
    public void testGetSolution() {

        System.out.println("TestGetSolution");
        Hidra instance = new Hidra();
        String result = instance.getSolution("hidra");
        System.out.println(result);
    }

    //@Test
    public void testGetClassification() {

        System.out.println("TestGetClassification");
        Hidra instance = new Hidra();
        String result = instance.getClassification("hidra");
        System.out.println(result);
    }

    //@Test
    public void testGetUsage() {

        System.out.println("TestGetUsage");
        Hidra instance = new Hidra();
        String result = instance.getUsage("hidra");
        System.out.println(result);
    }

    //@Test
    public void testRemoveAsset() {

        System.out.println("testRemoveAsset");
        Hidra instance = new Hidra();
        boolean result = instance.removeAsset("hidra");
        assertEquals(true, result);
    }

    @Test
    public void testInsertAsset() throws DatatypeConfigurationException {

        System.out.println("TEST_INSERT_ASSET");

        //instancia de HidraDataBase
        HidraDataBase db = new HidraDataBase();

        //definindo calendário
        GregorianCalendar c = new GregorianCalendar();
        DatatypeFactory datatypeFactory = DatatypeFactory.newInstance();
        XMLGregorianCalendar now
                = datatypeFactory.newXMLGregorianCalendar(c);

        //instancia de Perfil
        ProfileType profile = new ProfileType();
        profile.setIdHistory("ID_Default_Profile");
        profile.setName("Default");
        profile.setVersionMajor(1);
        profile.setVersionMinor(1);

        db.insert(profile);

        //instancia de ativo
        Asset asset = new Asset();
        asset.setId("hidra_asset_monografia");
        asset.setName("hidra");
        asset.setShortDescription("ativo referente a monografia do TCC");
        asset.setDate(now);
        asset.setState("em teste");
        asset.setVersion("1.0");
        asset.setProfile(profile);

        db.insert(asset);

    }

}
