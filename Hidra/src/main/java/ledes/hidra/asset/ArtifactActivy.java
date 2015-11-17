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
@XmlType(name = "artifactActivy", propOrder = {
    "activities"
})
@Entity
@Table(name = "ARTIFACT_ACTIVITY")
public class ArtifactActivy implements Serializable {

    @XmlElement(required = true)
    protected List<Activity> activities;
    @XmlAttribute(name = "artifactId")
    protected String artifactId;
    @XmlAttribute(name = "contextId")
    protected String contextId;

    protected UsageType usageType;

    public ArtifactActivy() {
    }

    @ManyToOne
    @JoinColumn(name = "usage_id",
            foreignKey = @ForeignKey(name = "fk_usage"))
    public UsageType getUsageType() {
        return usageType;
    }

    public void setUsageType(UsageType usageType) {
        this.usageType = usageType;
    }

    /**
     * Gets the value of the activities property.
     *
     * <p>
     * This accessor method returns a reference to the live list, not a
     * snapshot. Therefore any modification you make to the returned list will
     * be present inside the JAXB object. This is why there is not a
     * <CODE>set</CODE> method for the activities property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getActivities().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Activity }
     *
     *
     * @return
     */
    @OneToMany(orphanRemoval = true,
            mappedBy = "artifactActivy",
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    public List<Activity> getActivities() {
        if (activities == null) {
            activities = new ArrayList<Activity>();
        }
        return this.activities;
    }

    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }

    /**
     * Obtém o valor da propriedade artifactId.
     *
     * @return possible object is {@link String }
     *
     */
    @Id
    @Basic(optional = false)
    public String getArtifactId() {
        return artifactId;
    }

    /**
     * Define o valor da propriedade artifactId.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setArtifactId(String value) {
        this.artifactId = value;
    }

    /**
     * Obtém o valor da propriedade contextId.
     *
     * @return possible object is {@link String }
     *
     */
    @Column(name = "context_id", nullable = false)
    public String getContextId() {
        return contextId;
    }

    /**
     * Define o valor da propriedade contextId.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setContextId(String value) {
        this.contextId = value;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + Objects.hashCode(this.artifactId);
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
        final ArtifactActivy other = (ArtifactActivy) obj;
        if (!Objects.equals(this.artifactId, other.artifactId)) {
            return false;
        }
        return true;
    }

}
