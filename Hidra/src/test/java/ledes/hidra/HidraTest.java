/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ledes.hidra;

import javax.xml.datatype.DatatypeConfigurationException;
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
 * 
 * Classe responsável por efetuar testes relacionados a persistência de ativos.
 */
public class HidraTest {

    public HidraDataBase dataBase;
    public AssetMock assetMock;

    public HidraTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws DatatypeConfigurationException {
        this.dataBase = new HidraDataBase();
        this.assetMock = new AssetMock();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void devePersistirAsset() {

        assetMock.buildAsset();

        assertEquals(true, dataBase.insert(assetMock.getAsset()));
        assertEquals(true, dataBase.insert(assetMock.getProfile()));
        assertEquals(true, dataBase.insert(assetMock.getRelatedAssets()));
        assertEquals(true, dataBase.insert(assetMock.getRelatedAssetType()));
        assertEquals(true, dataBase.insert(assetMock.getClassificationType()));
        assertEquals(true, dataBase.insert(assetMock.getUsageType()));
        assertEquals(true, dataBase.insert(assetMock.getContext()));
        assertEquals(true, dataBase.insert(assetMock.getDescriptionGroup()));
        assertEquals(true, dataBase.insert(assetMock.getArtifactActivy()));
        assertEquals(true, dataBase.insert(assetMock.getContextReference()));
        assertEquals(true, dataBase.insert(assetMock.getActivity()));
        assertEquals(true, dataBase.insert(assetMock.getVariabilityPointBinding()));

    }

    @Test
    public void deveRemoverAssetEmCastaca() {

        assetMock.buildAsset();
        assertEquals(true, dataBase.removeAsset(assetMock.getAsset()));
    }

}
