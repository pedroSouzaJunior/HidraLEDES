package ledes.hidra.asset;

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
    "usage"
})
@XmlRootElement(name = "asset")
public class Asset {

    @XmlElement(required = true)
    protected ProfileType profile;
    @XmlElement(required = true)
    protected SolutionType solution;
    @XmlElement(required = true)
    protected ClassificationType classification;
    @XmlElement(required = true)
    protected UsageType usage;
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

    
    public String describeSolution(){
        StringBuilder stb = new StringBuilder().append("\n");
        
        stb.append("the asset is described by:\n");
        stb.append("Artifacts:\n");
        for(ArtifactType artfacty: this.solution.getArtifacts().getArtifact()){
            stb.append("ID: ").append(artfacty.getId());
            stb.append("Name: ").append(artfacty.getName());
            stb.append("Type: ").append(artfacty.getType());
            stb.append("Reference: ").append(artfacty.getReference()).append("\n");
        }
        
        stb.append("Requirements:\n");
        for(ArtifactType artfacty: this.solution.getRequirements().getArtifact()){
            stb.append("ID: ").append(artfacty.getId());
            stb.append("Name: ").append(artfacty.getName());
            stb.append("Type: ").append(artfacty.getType());
            stb.append("Reference: ").append(artfacty.getReference());
        }
        
        stb.append("Design:\n");
        for(ArtifactType artfacty: this.solution.getDesign().getArtifact()){
            stb.append("ID: ").append(artfacty.getId());
            stb.append("Name: ").append(artfacty.getName());
            stb.append("Type: ").append(artfacty.getType());
            stb.append("Reference: ").append(artfacty.getReference()).append("\n");
        }
        
        stb.append("Implementation:\n");
        for(ArtifactType artfacty: this.solution.getImplementation().getArtifact()){
            stb.append("ID: ").append(artfacty.getId());
            stb.append("Name: ").append(artfacty.getName());
            stb.append("Type: ").append(artfacty.getType());
            stb.append("Reference: ").append(artfacty.getReference()).append("\n");
        }
        
        stb.append("Design:\n");
        for(ArtifactType artfacty: this.solution.getDesign().getArtifact()){
            stb.append("ID: ").append(artfacty.getId());
            stb.append("Name: ").append(artfacty.getName());
            stb.append("Type: ").append(artfacty.getType());
            stb.append("Reference: ").append(artfacty.getReference()).append("\n");
        }
        
        stb.append("Test:\n");
        for(ArtifactType artfacty: this.solution.getTest().getArtifact()){
            stb.append("ID: ").append(artfacty.getId());
            stb.append("Name: ").append(artfacty.getName());
            stb.append("Type: ").append(artfacty.getType());
            stb.append("Reference: ").append(artfacty.getReference()).append("\n");
        }
        
        return stb.toString();
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    /**
     * Obtém o valor da propriedade profile.
     * 
     * @return
     *     possible object is
     *     {@link ProfileType }
     *     
     */
    public ProfileType getProfile() {
        return profile;
    }

    /**
     * Define o valor da propriedade profile.
     * 
     * @param value
     *     allowed object is
     *     {@link ProfileType }
     *     
     */
    public void setProfile(ProfileType value) {
        this.profile = value;
    }

    /**
     * Obtém o valor da propriedade solution.
     * 
     * @return
     *     possible object is
     *     {@link SolutionType }
     *     
     */
    public SolutionType getSolution() {
        return solution;
    }

    /**
     * Define o valor da propriedade solution.
     * 
     * @param value
     *     allowed object is
     *     {@link SolutionType }
     *     
     */
    public void setSolution(SolutionType value) {
        this.solution = value;
    }

    /**
     * Obtém o valor da propriedade classification.
     * 
     * @return
     *     possible object is
     *     {@link ClassificationType }
     *     
     */
    public ClassificationType getClassification() {
        return classification;
    }

    /**
     * Define o valor da propriedade classification.
     * 
     * @param value
     *     allowed object is
     *     {@link ClassificationType }
     *     
     */
    public void setClassification(ClassificationType value) {
        this.classification = value;
    }

    /**
     * Obtém o valor da propriedade usage.
     * 
     * @return
     *     possible object is
     *     {@link UsageType }
     *     
     */
    public UsageType getUsage() {
        return usage;
    }

    /**
     * Define o valor da propriedade usage.
     * 
     * @param value
     *     allowed object is
     *     {@link UsageType }
     *     
     */
    public void setUsage(UsageType value) {
        this.usage = value;
    }

    /**
     * Obtém o valor da propriedade name.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Define o valor da propriedade name.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Obtém o valor da propriedade id.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Define o valor da propriedade id.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Obtém o valor da propriedade date.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDate() {
        return date;
    }

    /**
     * Define o valor da propriedade date.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDate(XMLGregorianCalendar value) {
        this.date = value;
    }

    /**
     * Obtém o valor da propriedade state.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getState() {
        return state;
    }

    /**
     * Define o valor da propriedade state.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setState(String value) {
        this.state = value;
    }

    /**
     * Obtém o valor da propriedade version.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVersion() {
        return version;
    }

    /**
     * Define o valor da propriedade version.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVersion(String value) {
        this.version = value;
    }

    /**
     * Obtém o valor da propriedade shortDescription.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShortDescription() {
        return shortDescription;
    }

    /**
     * Define o valor da propriedade shortDescription.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShortDescription(String value) {
        this.shortDescription = value;
    }

    public String getDescribeClassification() {
        StringBuilder stb = new StringBuilder().append("\n");
        
        stb.append("the asset is classified by:\n");
        stb.append("Contexts:\n");
        for(Context context: this.classification.getContexts()){
            stb.append("ID: ").append(context.getId());
            stb.append("Name: ").append(context.getName());
            stb.append("Description: ").append(context.getDescription()).append("\n");
        }
        
        stb.append("Descriptor Groups:\n");
        for(DescriptionGroup descriptorGroup: this.getClassification().getDescriptionGroups()){
            stb.append("Name: ").append(descriptorGroup.getName());
            stb.append("Description: ").append(descriptorGroup.getDescription());
            stb.append("Rerefence: ").append(descriptorGroup.getReference()).append("\n");
        }
        return stb.toString();
    }

}
