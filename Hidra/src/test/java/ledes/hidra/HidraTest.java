/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ledes.hidra;

import java.io.IOException;
import java.util.GregorianCalendar;
import javax.xml.bind.JAXBException;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import ledes.hidra.asset.Activity;
import ledes.hidra.asset.ArtifactActivy;
import ledes.hidra.asset.ArtifactType;
import ledes.hidra.asset.Asset;
import ledes.hidra.asset.ClassificationType;
import ledes.hidra.asset.Context;
import ledes.hidra.asset.ContextReference;
import ledes.hidra.asset.DescriptionGroup;
import ledes.hidra.asset.ProfileType;
import ledes.hidra.asset.RelatedAssetType;
import ledes.hidra.asset.RelatedAssets;
import ledes.hidra.asset.UsageType;
import ledes.hidra.asset.VariabilityPointBinding;
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

    //@Test
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
        //assertEquals(db.insert(relatedAssetType), true);
        //instância de ativos relacionados
        RelatedAssets relatedAssets = new RelatedAssets();
        relatedAssets.setId("1");
        relatedAssets.getListOfRelatedAssets().add(relatedAssetType);

        //inserção do id da lista  relatedAssets na tabela related assets details
        //relatedAssetType.setRelatedAssets(relatedAssets);
        //inserção e validação da instancia na tebala related assets
        assertEquals(db.insert(relatedAssets), true);

        //instancia da classe DescriptorGroup
        DescriptionGroup descriptionGroup = new DescriptionGroup();
        descriptionGroup.setId("1");
        descriptionGroup.setName("Descrições de Ativos");
        descriptionGroup.setReference("grupo descritor externo default");
        descriptionGroup.setDescription("Descrição default do ativo");

        //inserção e validação da instancia na tabela DescriptorGroup
        assertEquals(db.insert(descriptionGroup), true);

        //instancia de Contexto - ContextType
        Context context = new Context();
        context.setDescription("biblioteca Java para criação de repositórios");
        context.getDescriptionGroup().add(descriptionGroup);
        context.setId("1");
        context.setName("Desenvolvimento");

        descriptionGroup.setContext(context);

        //inserção e validação da instancia na tabela Contex
        assertEquals(db.insert(context), true);

        //Instancia de Classificação
        ClassificationType classificationType = new ClassificationType();
        classificationType.setId("Default id Classification");
        classificationType.getContexts().add(context);
        classificationType.getDescriptionGroups().add(descriptionGroup);

        //descriptionGroup.setClasification(classificationType);
        //context.setClassification(classificationType);
        //inserção e validação da instancia na tabela Classification
        assertEquals(db.insert(classificationType), true);
        //instancia de um Artefato
        ArtifactType artifactType = new ArtifactType();
        artifactType.setId("Artifact_Type_ID");
        artifactType.setName("Monografia.pdf");
        artifactType.setReference("/monografia.pdf");
        artifactType.setType("Development");
        artifactType.setVersion("00:hidra_12");

        assertEquals(db.insert(artifactType), true);

        VariabilityPointBinding variabilityPointBinding = new VariabilityPointBinding();
        variabilityPointBinding.setId("id");
        variabilityPointBinding.setBindingRule("bindingRule");

        assertEquals(db.insert(variabilityPointBinding), true);

        Activity activity = new Activity();
        activity.setId("1");
        activity.setReference("ref");
        activity.setRole("role");
        activity.setTask("tastk");
        activity.setTaskRole("taskrole");
        activity.getVariability().add(variabilityPointBinding);

        assertEquals(db.insert(activity), true);

        ContextReference contextReference = new ContextReference();
        contextReference.setContextId("id");
        contextReference.getActivities().add(activity);

        assertEquals(db.insert(contextReference), true);

        ArtifactActivy artifactActivy = new ArtifactActivy();
        artifactActivy.setArtifactId("1");
        artifactActivy.setContextId("1");
        artifactActivy.getActivities().add(activity);

        assertEquals(db.insert(artifactActivy), true);

        UsageType usageType = new UsageType();
        usageType.setId("Default ID usage");
        usageType.getArtifactActivities().add(artifactActivy);
        usageType.getContextReferences().add(contextReference);

        assertEquals(db.insert(usageType), true);

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
        asset.setClassification(classificationType);
        asset.setUsage(usageType);
        //inserção e validação da instancia na tabela asset
        assertEquals(db.insert(asset), true);

    }

    @Test
    public void persistAssset() throws DatatypeConfigurationException {

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

        //instância de tipo de ativo relacionado
        RelatedAssetType relatedAssetType = new RelatedAssetType();
        relatedAssetType.setId("ID related Asset Type");
        relatedAssetType.setName("HidraRest");
        relatedAssetType.setReference("/relateds/HidraRest");
        relatedAssetType.setRelationshipType("Similar");

        //instância de ativos relacionados
        RelatedAssets relatedAssets = new RelatedAssets();
        relatedAssets.setId("Id related Asset");
        relatedAssets.getListOfRelatedAssets().add(relatedAssetType);

        //instancia da classe DescriptorGroup
        DescriptionGroup descriptionGroup = new DescriptionGroup();
        descriptionGroup.setId("1");
        descriptionGroup.setName("Descrições de Ativos");
        descriptionGroup.setReference("grupo descritor externo default");
        descriptionGroup.setDescription("Descrição default do ativo");

        //instancia de Contexto - ContextType
        Context context = new Context();
        context.setDescription("biblioteca Java para criação de repositórios");
        context.getDescriptionGroup().add(descriptionGroup);
        context.setId("1");
        context.setName("Desenvolvimento");

        //Instancia de Classificação
        ClassificationType classificationType = new ClassificationType();
        classificationType.setId("Default id Classification");
        classificationType.getContexts().add(context);
        classificationType.getDescriptionGroups().add(descriptionGroup);

        //instancia de um Artefato
        ArtifactType artifactType = new ArtifactType();
        artifactType.setId("Artifact_Type_ID");
        artifactType.setName("Monografia.pdf");
        artifactType.setReference("/monografia.pdf");
        artifactType.setType("Development");
        artifactType.setVersion("00:hidra_12");

        VariabilityPointBinding variabilityPointBinding = new VariabilityPointBinding();
        variabilityPointBinding.setId("id");
        variabilityPointBinding.setBindingRule("bindingRule");

        Activity activity = new Activity();
        activity.setId("1");
        activity.setReference("ref");
        activity.setRole("role");
        activity.setTask("tastk");
        activity.setTaskRole("taskrole");
        activity.getVariability().add(variabilityPointBinding);

        ContextReference contextReference = new ContextReference();
        contextReference.setContextId("id");
        contextReference.getActivities().add(activity);

        ArtifactActivy artifactActivy = new ArtifactActivy();
        artifactActivy.setArtifactId("1");
        artifactActivy.setContextId("1");
        artifactActivy.getActivities().add(activity);

        UsageType usageType = new UsageType();
        usageType.setId("Default ID usage");
        usageType.getArtifactActivities().add(artifactActivy);
        usageType.getContextReferences().add(contextReference);

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
        asset.setClassification(classificationType);
        asset.setUsage(usageType);

        //inserção e validação da instancia na tabela asset
        assertEquals(db.insert(asset), true);

        //inserção da chave estrangeira asset na tabela profile
        profile.setAsset(asset);
        //inserção e validação de instancia na tabela profile
        assertEquals(db.insert(profile), true);

        //inserção da chave estrangeira asset na tabela related assets
        relatedAssets.setAsset(asset);
        //inserção e validação da instancia na tebala related assets
        assertEquals(db.insert(relatedAssets), true);

        //inserção da chave estrangeira relatedAssets na tabela related assets details
        relatedAssetType.setRelatedAssets(relatedAssets);
        //inserção e validação da instancia na tabela related asset details
        assertEquals(db.insert(relatedAssetType), true);

        //inserção da chave estrangeira asset na tabela classification
        classificationType.setAsset(asset);
        //inserção e validação da instancia na tabela Classification
        assertEquals(db.insert(classificationType), true);

        //inserção da chave estrangeira asset na tabela usage
        usageType.setAsset(asset);
        //inserção e validação da instancia usageType
        assertEquals(db.insert(usageType), true);

        //inserção da chave estrangeira classification na tabela context
        context.setClassification(classificationType);
        //inserção e validação da instancia na tabela Contex
        assertEquals(db.insert(context), true);

        //inserção da chave estrangeira contex na tabela description group
        descriptionGroup.setContext(context);
        //inserção da chave estrangeira classification na tabela description group
        descriptionGroup.setClassificationType(classificationType);
        //inserção e validação da instancia na tabela DescriptorGroup
        assertEquals(db.insert(descriptionGroup), true);

        //inserção e validação da instancia artifactType
        assertEquals(db.insert(artifactType), true);

        //inserção da chave estrangeira usage na tabela artifactActivity
        artifactActivy.setUsageType(usageType);
        //inserção e validação da instancia artifactActivity
        assertEquals(db.insert(artifactActivy), true);

        //inserção da chave estrangeira usage na tabela context reference
        contextReference.setUsageType(usageType);
        //inserção e validação da instancia contextReference
        assertEquals(db.insert(contextReference), true);

        //inserção da chave estrangeira artifactActivity na tabela activity
        activity.setArtifactActivy(artifactActivy);
        //inserção da chave estrangeira contextReference na tabela activity
        activity.setContextReference(contextReference);
        assertEquals(db.insert(activity), true);

        //inserção da chave estrangeira activity na tabela variabilityPointBinding
        variabilityPointBinding.setActivity(activity);
        //inserção e validação da instancia VariabilityPointBinding
        assertEquals(db.insert(variabilityPointBinding), true);
        //inserção e validação da instancia Activity

        /**
         * fazendo a inserção na ordem correta não é necessário utilizar o
         * método update pa inserção de chave estrangeira. Utilizar método set
         */
    }

}
