package ledes.hidra.asset;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "profile",
    "solution",
    "classification",
    "usage",
    "relatedAssetsList"
})
@XmlRootElement(name = "asset")
@Entity
@Table(name = "ASSET")
public class Asset implements Serializable {

    @XmlElement(required = true)
    protected ProfileType profile;
    @XmlElement(required = true)
    protected SolutionType solution;
    @XmlElement(required = true)
    protected ClassificationType classification;
    @XmlElement(required = true)
    protected UsageType usage;

    protected RelatedAssets relatedAssetsList;

    @XmlAttribute(name = "name", required = true)
    protected String name;
    @XmlAttribute(name = "id", required = true)
    protected String id;
    @XmlAttribute(name = "date")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar date;
    @XmlAttribute(name = "state")
    protected String state;
    @XmlAttribute(name = "version")
    protected String version;
    @XmlAttribute(name = "short-description")
    protected String shortDescription;

    public Asset() {
        profile = new ProfileType();
        solution = new SolutionType();
        classification = new ClassificationType();
        usage = new UsageType();
        relatedAssetsList = new RelatedAssets();
    }

    /**
     * *
     * Método que descreve a solucao de um ativo atravez dele e possivel
     * descobrir a solucao que incorpora um ativo.
     *
     * @return String - a descricao da solucao.
     */
    @Transient
    public String describeSolution() {
        StringBuilder stb = new StringBuilder().append("\n");

        stb.append("Artifacts:\n");

        for (ArtifactType artfacty : this.solution.getArtifacts().getArtifact()) {
            stb.append("<iD>: ").append(artfacty.getId()).append("\t");
            stb.append("Name: ").append(artfacty.getName()).append("\t");
            stb.append("Type: ").append(artfacty.getType()).append("\t");
            stb.append("Reference: ").append(artfacty.getReference()).append("\n\n");
        }

        stb.append("Requirements:\n");
        for (ArtifactType artfacty : this.solution.getRequirements().getArtifact()) {
            stb.append("ID: ").append(artfacty.getId()).append("\t");
            stb.append("Name: ").append(artfacty.getName()).append("\t");
            stb.append("Type: ").append(artfacty.getType()).append("\t");
            stb.append("Reference: ").append(artfacty.getReference()).append("\n\n");
        }

        stb.append("Design:\n");
        for (ArtifactType artfacty : this.solution.getDesign().getArtifact()) {
            stb.append("ID: ").append(artfacty.getId()).append("\t");
            stb.append("Name: ").append(artfacty.getName()).append("\t");
            stb.append("Type: ").append(artfacty.getType()).append("\t");
            stb.append("Reference: ").append(artfacty.getReference()).append("\n\n");
        }

        stb.append("Implementation:\n");
        for (ArtifactType artfacty : this.solution.getImplementation().getArtifact()) {
            stb.append("ID: ").append(artfacty.getId()).append("\t");
            stb.append("Name: ").append(artfacty.getName()).append("\t");
            stb.append("Type: ").append(artfacty.getType()).append("\t");
            stb.append("Reference: ").append(artfacty.getReference()).append("\n\n");
        }

        stb.append("Test:\n");
        for (ArtifactType artfacty : this.solution.getTest().getArtifact()) {
            stb.append("ID: ").append(artfacty.getId()).append("\t");
            stb.append("Name: ").append(artfacty.getName()).append("\t");
            stb.append("Type: ").append(artfacty.getType()).append("\t");
            stb.append("Reference: ").append(artfacty.getReference()).append("\n\n");
        }

        return stb.toString();
    }

    /**
     * *
     * Metodo responsavel por descrever a classificacao de um ativo. por meio
     * dele e possivel obter uma descricao da classificacao de um ativo.
     *
     * @return String - a descricao da Classificacao
     */
    @Transient
    public String describeClassification() {
        StringBuilder stb = new StringBuilder().append("\n");

        stb.append("the asset is classified by:\n\n");
        stb.append("Contexts:\n");
        for (Context context : this.getClassification().getContexts()) {
            stb.append("ID: ").append(context.getId()).append("\t");
            stb.append("Name: ").append(context.getName()).append("\t");
            stb.append("Description: ").append(context.getDescription()).append("\n\n");
        }

        stb.append("Descriptor Groups:\n");
        for (DescriptionGroup descriptorGroup : this.getClassification().getDescriptionGroups()) {
            stb.append("Name: ").append(descriptorGroup.getName()).append("\t");
            stb.append("Description: ").append(descriptorGroup.getDescription()).append("\t");
            stb.append("Rerefence: ").append(descriptorGroup.getReference()).append("\n\n");
        }
        return stb.toString();
    }

    /**
     * *
     * Metodo responsavel pela descricao de Uso de um ativo. por meio dele e
     * possivel obter a descricao de uso de um Ativo.
     *
     * @return String - a descricao de Uso.
     */
    @Transient
    public String describeUsage() {

        StringBuilder stb = new StringBuilder().append("\n");
        stb.append("the use of the asset is given by:\n\n");
        stb.append("artifact Activities:\n");
        for (ArtifactActivy activy : this.getUsage().getArtifactActivities()) {
            stb.append("ID: ").append(activy.getArtifactId()).append("\t");
            stb.append("Contex ID: ").append(activy.getContextId()).append("\n\n");

            for (Activity ac : activy.getActivities()) {
                stb.append("Activity ID: ").append(ac.getId()).append("\t");
                stb.append("Activity Reference: ").append(ac.getReference()).append("\t");
                stb.append("Role: ").append(ac.getRole()).append("\t");
                stb.append("Task: ").append(ac.getTask()).append("\t");
                stb.append("Task Role: ").append(ac.getTaskRole()).append("\n\n");

                for (VariabilityPointBinding variability : ac.getVariability()) {
                    stb.append("Variability ID: ").append(variability.getId()).append("\t");
                    stb.append("Variability Binding Rule: ").append(variability.getBindingRule()).append("\n\n");
                }

            }

        }

        return stb.toString();
    }

    /**
     * *
     * Método responsável pela descricao dos ativos relacionados.
     *
     * @return
     */
    @Transient
    public String describeRelatedAssets() {

        StringBuilder stb = new StringBuilder().append("\n");
        stb.append("Related assets:\n\n");

        for (RelatedAssetType related : this.getRelatedAssetsList().getListOfRelatedAssets()) {

            stb.append("Asset ID: ").append(related.getId()).append("\t");
            stb.append("Asset Name: ").append(related.getName()).append("\t");
            stb.append("Asset Reference: ").append(related.getReference()).append("\t");
            stb.append("Asset Relationship Type: ").append(related.getRelationshipType()).append("\n\n");

        }

        return stb.toString();
    }

    /**
     * Obtém o valor da propriedade profile.
     *
     * @return possible object is {@link ProfileType }
     *
     */
    @OneToOne(orphanRemoval = true,
            mappedBy = "asset",
            optional = false,
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name = "id_profile")
    public ProfileType getProfile() {
        return profile;
    }

    /**
     * Define o valor da propriedade profile.
     *
     * @param value allowed object is {@link ProfileType }
     *
     */
    public void setProfile(ProfileType value) {
        this.profile = value;
    }

    /**
     * Obtém o valor da propriedade solution.
     *
     * @return possible object is {@link SolutionType }
     *
     */
    @Transient
    public SolutionType getSolution() {
        return solution;
    }

    /**
     * Define o valor da propriedade solution.
     *
     * @param value allowed object is {@link SolutionType }
     *
     */
    public void setSolution(SolutionType value) {
        this.solution = value;
    }

    /**
     * Obtém o valor da propriedade classification.
     *
     * @return possible object is {@link ClassificationType }
     *
     */
    @OneToOne(orphanRemoval = true,
            mappedBy = "asset",
            optional = false,
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name = "classification_id")
    public ClassificationType getClassification() {
        return classification;
    }

    /**
     * Define o valor da propriedade classification.
     *
     * @param value allowed object is {@link ClassificationType }
     *
     */
    public void setClassification(ClassificationType value) {
        this.classification = value;
    }

    /**
     * Obtém o valor da propriedade usage.
     *
     * @return possible object is {@link UsageType }
     *
     */
    @OneToOne(orphanRemoval = true,
            mappedBy = "asset",
            optional = false,
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name = "usage_id")
    public UsageType getUsage() {
        return usage;
    }

    /**
     * Define o valor da propriedade usage.
     *
     * @param value allowed object is {@link UsageType }
     *
     */
    public void setUsage(UsageType value) {
        this.usage = value;
    }

    /**
     * Obtém o valor da propriedade relatedAssetsList.
     *
     * @return possible object is {@link RelatedAssets }
     *
     */
    @OneToOne(orphanRemoval = true,
            mappedBy = "asset",
            optional = false,
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name = "related_assets_id")
    public RelatedAssets getRelatedAssetsList() {
        return relatedAssetsList;
    }

    /**
     * Define o valor da propriedade relatedAssetsList.
     *
     * @param value allowed object is {@link RelatedAssets }
     *
     */
    public void setRelatedAssetsList(RelatedAssets value) {
        this.relatedAssetsList = value;
    }

    /**
     * Obtém o valor da propriedade name.
     *
     * @return possible object is {@link String }
     *
     */
    @Column(name = "name", nullable = false, length = 50)
    public String getName() {
        return name;
    }

    /**
     * Define o valor da propriedade name.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Obtém o valor da propriedade id.
     *
     * @return possible object is {@link String }
     *
     */
    @Id
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    public String getId() {
        return id;
    }

    /**
     * Define o valor da propriedade id.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Obtém o valor da propriedade date.
     *
     * @return possible object is {@link XMLGregorianCalendar }
     *
     */
    @Transient
    public XMLGregorianCalendar getDate() {
        return date;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "modification_date")
    public Date getDateTimeAsset() {

        return date.toGregorianCalendar().getTime();
    }

    public void setDateTimeAsset(XMLGregorianCalendar calendar) {
        this.date = calendar;
    }

    /**
     * Define o valor da propriedade date.
     *
     * @param value allowed object is {@link XMLGregorianCalendar }
     *
     */
    public void setDate(XMLGregorianCalendar value) {
        this.date = value;
    }

    /**
     * Obtém o valor da propriedade state.
     *
     * @return possible object is {@link String }
     *
     */
    @Column(name = "state_asset", nullable = false, length = 50)
    public String getState() {
        return state;
    }

    /**
     * Define o valor da propriedade state.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setState(String value) {
        this.state = value;
    }

    /**
     * Obtém o valor da propriedade version.
     *
     * @return possible object is {@link String }
     *
     */
    @Column(name = "version", nullable = false, length = 50)
    public String getVersion() {
        return version;
    }

    /**
     * Define o valor da propriedade version.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setVersion(String value) {
        this.version = value;
    }

    /**
     * Obtém o valor da propriedade shortDescription.
     *
     * @return possible object is {@link String }
     *
     */
    @Column(name = "short_description", nullable = false)
    public String getShortDescription() {
        return shortDescription;
    }

    /**
     * Define o valor da propriedade shortDescription.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setShortDescription(String value) {
        this.shortDescription = value;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Asset other = (Asset) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
