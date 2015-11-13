package ledes.hidra.asset;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "relatedAssetType")
@Entity
@Table(name = "RELATED_ASSETS_DETAILS")
public class RelatedAssetType implements Serializable {

    @XmlAttribute(name = "name")
    protected String name;
    @XmlAttribute(name = "id")
    protected String id;
    @XmlAttribute(name = "reference")
    protected String reference;
    @XmlAttribute(name = "relationshipType")
    protected String relationshipType;

    protected RelatedAssets relatedAssets;

    public RelatedAssetType() {
    }

    @ManyToOne
    @JoinColumn(name = "related_assets_id")
    //@OnDelete(action = OnDeleteAction.CASCADE)
    public RelatedAssets getRelatedAssets() {
        return relatedAssets;
    }

    public void setRelatedAssets(RelatedAssets relatedAssets) {
        this.relatedAssets = relatedAssets;
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
     * Obtém o valor da propriedade relationshipType.
     *
     * @return possible object is {@link String }
     *
     */
    @Column(name = "relationship_type", nullable = false, length = 50)
    public String getRelationshipType() {
        return relationshipType;
    }

    /**
     * Define o valor da propriedade relationshipType.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setRelationshipType(String value) {
        this.relationshipType = value;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.id);
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
        final RelatedAssetType other = (RelatedAssetType) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
