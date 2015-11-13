/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ledes.hidra;

import java.util.GregorianCalendar;
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

/**
 * https://pt.wikipedia.org/wiki/Objeto_Mock
 *
 * @author pedro
 */
public class AssetMock {

    private ProfileType profile;
    private RelatedAssetType relatedAssetType;
    private RelatedAssets relatedAssets;
    private DescriptionGroup descriptionGroup;
    private Context context;
    private ClassificationType classificationType;
    private ArtifactType artifactType;
    private VariabilityPointBinding variabilityPointBinding;
    private Activity activity;
    private ContextReference contextReference;
    private ArtifactActivy artifactActivy;
    private UsageType usageType;
    private Asset asset;
    private GregorianCalendar calendar;
    private DatatypeFactory datatypeFactory;
    private XMLGregorianCalendar xmlGregorianCalendar;

    public AssetMock() throws DatatypeConfigurationException {

        this.profile = new ProfileType();
        this.relatedAssetType = new RelatedAssetType();
        this.relatedAssets = new RelatedAssets();
        this.descriptionGroup = new DescriptionGroup();
        this.context = new Context();
        this.classificationType = new ClassificationType();
        this.artifactType = new ArtifactType();
        this.variabilityPointBinding = new VariabilityPointBinding();
        this.activity = new Activity();
        this.contextReference = new ContextReference();
        this.artifactActivy = new ArtifactActivy();
        this.usageType = new UsageType();
        this.asset = new Asset();

        this.calendar = new GregorianCalendar();
        this.datatypeFactory = DatatypeFactory.newInstance();
        this.xmlGregorianCalendar = datatypeFactory.newXMLGregorianCalendar(calendar);

    }

    public void buildAsset() {

        profile.setIdHistory("ID_Default_Profile");
        profile.setName("PROFILE_NAME");
        profile.setVersionMajor(1);
        profile.setVersionMinor(1);

        relatedAssetType.setId("ID related Asset Type");
        relatedAssetType.setName("HidraRest");
        relatedAssetType.setReference("/relateds/HidraRest");
        relatedAssetType.setRelationshipType("Similar");

        relatedAssets.setId("Id related Asset");
        relatedAssets.getListOfRelatedAssets().add(relatedAssetType);

        descriptionGroup.setId("1");
        descriptionGroup.setName("Descrições de Ativos");
        descriptionGroup.setReference("grupo descritor externo default");
        descriptionGroup.setDescription("Descrição default do ativo");

        context.setDescription("biblioteca Java para criação de repositórios");
        context.getDescriptionGroup().add(descriptionGroup);
        context.setId("1");
        context.setName("Desenvolvimento");

        classificationType.setId("Default id Classification");
        classificationType.getContexts().add(context);
        classificationType.getDescriptionGroups().add(descriptionGroup);

        artifactType.setId("Artifact_Type_ID");
        artifactType.setName("Monografia.pdf");
        artifactType.setReference("/monografia.pdf");
        artifactType.setType("Development");
        artifactType.setVersion("00:hidra_12");

        variabilityPointBinding.setId("id");
        variabilityPointBinding.setBindingRule("bindingRule");

        activity.setId("1");
        activity.setReference("ref");
        activity.setRole("role");
        activity.setTask("tastk");
        activity.setTaskRole("taskrole");
        activity.getVariability().add(variabilityPointBinding);

        contextReference.setContextId("id");
        contextReference.getActivities().add(activity);

        artifactActivy.setArtifactId("1");
        artifactActivy.setContextId("1");
        artifactActivy.getActivities().add(activity);

        usageType.setId("Default ID usage");
        usageType.getArtifactActivities().add(artifactActivy);
        usageType.getContextReferences().add(contextReference);

        asset.setId("hidra_asset_monografia");
        asset.setName("hidra");
        asset.setShortDescription("ativo referente a monografia do TCC");
        asset.setDate(xmlGregorianCalendar);
        asset.setState("em teste");
        asset.setVersion("1.0");
        asset.setProfile(profile);
        asset.setRelatedAssetsList(relatedAssets);
        asset.setClassification(classificationType);
        asset.setUsage(usageType);

        /**
         * fazendo a inserção na ordem correta não é necessário utilizar o
         * método update para inserção de chave estrangeira. Utilizar método set
         */
        profile.setAsset(asset);
        relatedAssets.setAsset(asset);
        relatedAssetType.setRelatedAssets(relatedAssets);
        classificationType.setAsset(asset);
        usageType.setAsset(asset);
        context.setClassification(classificationType);
        descriptionGroup.setContext(context);
        descriptionGroup.setClassificationType(classificationType);
        artifactActivy.setUsageType(usageType);
        contextReference.setUsageType(usageType);
        activity.setArtifactActivy(artifactActivy);
        activity.setContextReference(contextReference);
        variabilityPointBinding.setActivity(activity);

    }

    public ProfileType getProfile() {
        return profile;
    }

    public void setProfile(ProfileType profile) {
        this.profile = profile;
    }

    public RelatedAssetType getRelatedAssetType() {
        return relatedAssetType;
    }

    public void setRelatedAssetType(RelatedAssetType relatedAssetType) {
        this.relatedAssetType = relatedAssetType;
    }

    public RelatedAssets getRelatedAssets() {
        return relatedAssets;
    }

    public void setRelatedAssets(RelatedAssets relatedAssets) {
        this.relatedAssets = relatedAssets;
    }

    public DescriptionGroup getDescriptionGroup() {
        return descriptionGroup;
    }

    public void setDescriptionGroup(DescriptionGroup descriptionGroup) {
        this.descriptionGroup = descriptionGroup;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public ClassificationType getClassificationType() {
        return classificationType;
    }

    public void setClassificationType(ClassificationType classificationType) {
        this.classificationType = classificationType;
    }

    public ArtifactType getArtifactType() {
        return artifactType;
    }

    public void setArtifactType(ArtifactType artifactType) {
        this.artifactType = artifactType;
    }

    public VariabilityPointBinding getVariabilityPointBinding() {
        return variabilityPointBinding;
    }

    public void setVariabilityPointBinding(VariabilityPointBinding variabilityPointBinding) {
        this.variabilityPointBinding = variabilityPointBinding;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public ContextReference getContextReference() {
        return contextReference;
    }

    public void setContextReference(ContextReference contextReference) {
        this.contextReference = contextReference;
    }

    public ArtifactActivy getArtifactActivy() {
        return artifactActivy;
    }

    public void setArtifactActivy(ArtifactActivy artifactActivy) {
        this.artifactActivy = artifactActivy;
    }

    public UsageType getUsageType() {
        return usageType;
    }

    public void setUsageType(UsageType usageType) {
        this.usageType = usageType;
    }

    public Asset getAsset() {
        return asset;
    }

    public void setAsset(Asset asset) {
        this.asset = asset;
    }

    public GregorianCalendar getCalendar() {
        return calendar;
    }

    public void setCalendar(GregorianCalendar calendar) {
        this.calendar = calendar;
    }

    public DatatypeFactory getDatatypeFactory() {
        return datatypeFactory;
    }

    public void setDatatypeFactory(DatatypeFactory datatypeFactory) {
        this.datatypeFactory = datatypeFactory;
    }

    public XMLGregorianCalendar getXmlGregorianCalendar() {
        return xmlGregorianCalendar;
    }

    public void setXmlGregorianCalendar(XMLGregorianCalendar xmlGregorianCalendar) {
        this.xmlGregorianCalendar = xmlGregorianCalendar;
    }

}
