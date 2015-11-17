package ledes.hidra.asset;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "activity", propOrder = {
    "variability"
})
@Entity
@Table(name = "ACTIVITY")
public class Activity implements Serializable {

    @XmlElement(required = true)
    protected List<VariabilityPointBinding> variability;
    @XmlAttribute(name = "id")
    protected String id;
    @XmlAttribute(name = "task")
    protected String task;
    @XmlAttribute(name = "reference")
    protected String reference;
    @XmlAttribute(name = "role")
    protected String role;
    @XmlAttribute(name = "taskRole")
    protected String taskRole;

    protected ArtifactActivy artifactActivy;
    protected ContextReference contextReference;

    public Activity() {
    }

    @ManyToOne
    @JoinColumn(name = "artifact_activity_id",
            foreignKey = @ForeignKey(name = "fk_artifact_activity"))
    public ArtifactActivy getArtifactActivy() {
        return artifactActivy;
    }

    public void setArtifactActivy(ArtifactActivy artifactActivy) {
        this.artifactActivy = artifactActivy;
    }

    @ManyToOne
    @JoinColumn(name = "context_reference_id",
            foreignKey = @ForeignKey(name = "fk_context_reference"))
    public ContextReference getContextReference() {
        return contextReference;
    }

    public void setContextReference(ContextReference contextReference) {
        this.contextReference = contextReference;
    }

    /**
     * Gets the value of the variability property.
     *
     * <p>
     * This accessor method returns a reference to the live list, not a
     * snapshot. Therefore any modification you make to the returned list will
     * be present inside the JAXB object. This is why there is not a
     * <CODE>set</CODE> method for the variability property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getVariability().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link VariabilityPointBinding }
     *
     *
     * @return
     */
    @OneToMany(orphanRemoval = true,
            mappedBy = "activity",
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    public List<VariabilityPointBinding> getVariability() {
        if (variability == null) {
            variability = new ArrayList<VariabilityPointBinding>();
        }
        return this.variability;
    }

    public void setVariability(List<VariabilityPointBinding> variability) {
        this.variability = variability;
    }

    /**
     * Obtém o valor da propriedade id.
     *
     * @return possible object is {@link String }
     *
     */
    @Id
    @Basic(optional = false)
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
     * Obtém o valor da propriedade task.
     *
     * @return possible object is {@link String }
     *
     */
    @Column(name = "task", nullable = false, length = 100)
    public String getTask() {
        return task;
    }

    /**
     * Define o valor da propriedade task.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setTask(String value) {
        this.task = value;
    }

    /**
     * Obtém o valor da propriedade reference.
     *
     * @return possible object is {@link String }
     *
     */
    @Column(name = "reference", nullable = false, length = 50)
    public String getReference() {
        return reference;
    }

    /**
     * Define o valor da propriedade reference.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setReference(String value) {
        this.reference = value;
    }

    /**
     * Obtém o valor da propriedade role.
     *
     * @return possible object is {@link String }
     *
     */
    @Column(name = "role", nullable = false, length = 50)
    public String getRole() {
        return role;
    }

    /**
     * Define o valor da propriedade role.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setRole(String value) {
        this.role = value;
    }

    /**
     * Obtém o valor da propriedade taskRole.
     *
     * @return possible object is {@link String }
     *
     */
    @Column(name = "task_role", nullable = false, length = 50)
    public String getTaskRole() {
        return taskRole;
    }

    /**
     * Define o valor da propriedade taskRole.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setTaskRole(String value) {
        this.taskRole = value;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.id);
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
        final Activity other = (Activity) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
