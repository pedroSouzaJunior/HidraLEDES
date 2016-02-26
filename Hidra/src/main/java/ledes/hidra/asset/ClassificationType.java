package ledes.hidra.asset;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.junit.ClassRule;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "classificationType", propOrder = {
    "contexts",
    "descriptionGroups",
    "id",
    "asset"
})
@Entity
@Table(name = "CLASSIFICATION")
public class ClassificationType implements Serializable {

    @XmlElement(required = true)
    protected List<Context> contexts;
    @XmlElement(required = true)
    protected List<DescriptionGroup> descriptionGroups;

    protected String id;
    protected Asset asset;

    public ClassificationType() {
    }

    @OneToOne(optional = false)
    @JoinColumn(name = "asset_id",
            foreignKey = @ForeignKey(name = "fk_asset"))
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
     * Gets the value of the contexts property.
     *
     * <p>
     * This accessor method returns a reference to the live list, not a
     * snapshot. Therefore any modification you make to the returned list will
     * be present inside the JAXB object. This is why there is not a
     * <CODE>set</CODE> method for the contexts property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getContexts().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list {@link Context }
     *
     *
     * @return
     */
    @OneToMany(orphanRemoval = true,
            mappedBy = "classification",
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    public List<Context> getContexts() {
        if (contexts == null) {
            contexts = new ArrayList<Context>();
        }
        return this.contexts;
    }

    public void setContexts(List<Context> contexts) {
        this.contexts = contexts;
    }

    /**
     * Gets the value of the descriptionGroups property.
     *
     * <p>
     * This accessor method returns a reference to the live list, not a
     * snapshot. Therefore any modification you make to the returned list will
     * be present inside the JAXB object. This is why there is not a
     * <CODE>set</CODE> method for the descriptionGroups property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDescriptionGroups().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DescriptionGroup }
     *
     *
     * @return
     */
    @OneToMany(orphanRemoval = true,
            mappedBy = "classificationType",
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    public List<DescriptionGroup> getDescriptionGroups() {
        if (descriptionGroups == null) {
            descriptionGroups = new ArrayList<DescriptionGroup>();
        }
        return this.descriptionGroups;
    }

    public void setDescriptionGroups(List<DescriptionGroup> descriptionGroups) {
        this.descriptionGroups = descriptionGroups;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.id);
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
        final ClassificationType other = (ClassificationType) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
