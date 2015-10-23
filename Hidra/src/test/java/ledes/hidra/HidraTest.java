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
import ledes.hidra.asset.RelatedAssetType;
import ledes.hidra.asset.RelatedAssets;
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
        
        //inserção e validação de instancia na tabela profile
        assertEquals(db.insert(profile), true);

        //instância de tipo de ativo relacionado
        RelatedAssetType relatedAssetType = new RelatedAssetType();
        relatedAssetType.setId("1");
        relatedAssetType.setName("HidraRest");
        relatedAssetType.setReference("/relateds/HidraRest");
        relatedAssetType.setRelationshipType("Similar");

        //inserção e validação da instancia na tabela related asset details
        assertEquals(db.insert(relatedAssetType), true);

        //instância de ativos relacionados
        RelatedAssets relatedAssets = new RelatedAssets();
        relatedAssets.setId("1");
        relatedAssets.getListOfRelatedAssets().add(relatedAssetType);

        //inserção do id da lista  relatedAssets na tabela related assets details
        relatedAssetType.setRelatedAssets(relatedAssets);
        
        //inserção e validação da instancia na tebala related assets
        assertEquals(db.insert(relatedAssets), true);

        //instancia de ativo
        Asset asset = new Asset();
        asset.setId("hidra_asset_monografia");
        asset.setName("hidra");
        asset.setShortDescription("ativo referente a monografia do TCC");
        asset.setDate(now);
        asset.setState("em teste");
        asset.setVersion("1.0");
        asset.setProfile(profile);
        asset.setRelatedAssetsList(relatedAssets);
        
        //inserção e validação da instancia na tabela asset
        assertEquals(db.insert(asset), true);

    }

}
