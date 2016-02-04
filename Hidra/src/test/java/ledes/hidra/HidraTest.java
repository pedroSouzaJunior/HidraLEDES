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
 *
 * dataBase: instancia responsavel por fornecer interacao com o BD. Possui
 * recursos para insersao, e remocao de ativos.
 *
 * assetMock: instancia responsavel por simular o comportamento de um ativo.
 * assetMock representa um ativo preenchido com todos os metadados necessarios.
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

    /**
     * Metodo responsavel por persistir um ativo com todos os metadados
     * necessarios. O metodo buildAsset() e responsavel por simular a
     * instanciacao de um ativo completo, a assertiva espera que o metodo insert
     * do banco de dados retorne true, informando que a operacao foi bem
     * sucedida.
     */
    //@Test
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

    /**
     * *
     * Metodo responsavel por remover um ativo do banco de dados, utilizando a
     * remocao em cascata. O metodo buildAsset() e responsavel por simular a
     * instanciacao de um ativo completo. A assertiva espera que o resultado da
     * operacao seja true.
     */
    //@Test
    public void deveRemoverAssetEmCastaca() {

        assetMock.buildAsset();
        assertEquals(true, dataBase.removeAsset(assetMock.getAsset()));
    }

}
