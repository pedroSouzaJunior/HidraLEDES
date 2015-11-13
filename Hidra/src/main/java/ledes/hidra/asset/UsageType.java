package ledes.hidra.asset;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "usageType", propOrder = {
    "artifactActivities",
    "contextReferences"
})
@Entity
@Table(name = "Usage")
public class UsageType implements Serializable {

    @XmlElement(required = true)
    protected List<ArtifactActivy> artifactActivities;
    @XmlElement(required = true)
    protected List<ContextReference> contextReferences;

    protected String id;
    protected Asset asset;

    public UsageType() {
    }

    @OneToOne(optional = false)
    @JoinColumn(name = "asset_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    public Asset getAsset() {
        return asset;
    }

    public void setAsset(Asset asset) {
        this.asset = asset;
    }

    @Id
    @Basic(optional = false)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets the value of the artifactActivities property.
     *
     * <p>
     * This accessor method returns a reference to the live list, not a
     * snapshot. Therefore any modification you make to the returned list will
     * be present inside the JAXB object. This is why there is not a
     * <CODE>set</CODE> method for the artifactActivities property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getArtifactActivities().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ArtifactActivy }
     *
     *
     */
    @OneToMany(orphanRemoval = true,
            mappedBy = "usageType",
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    public List<ArtifactActivy> getArtifactActivities() {
        if (artifactActivities == null) {
            artifactActivities = new ArrayList<ArtifactActivy>();
        }
        return this.artifactActivities;
    }

    public void setArtifactActivities(List<ArtifactActivy> artifactActivities) {
        this.artifactActivities = artifactActivities;
    }

    /**
     * Gets the value of the contextReferences property.
     *
     * <p>
     * This accessor method returns a reference to the live list, not a
     * snapshot. Therefore any modification you make to the returned list will
     * be present inside the JAXB object. This is why there is not a
     * <CODE>set</CODE> method for the contextReferences property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getContextReferences().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ContextReference }
     *
     *
     */
    @OneToMany(orphanRemoval = true,
            mappedBy = "usageType",
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    public List<ContextReference> getContextReferences() {
        if (contextReferences == null) {
            contextReferences = new ArrayList<ContextReference>();
        }
        return this.contextReferences;
    }

    public void setContextReferences(List<ContextReference> contextReferences) {
        this.contextReferences = contextReferences;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + Objects.hashCode(this.id);
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
        final UsageType other = (UsageType) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
