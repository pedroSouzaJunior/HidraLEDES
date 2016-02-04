package ledes.hidra.asset;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "artifact")
@Entity
@Table(name = "ARTIFACT")
public class Artifact extends Description implements Serializable {

    @XmlAttribute(name = "name", required = true)
    protected String name;
    @XmlAttribute(name = "type", required = true)
    protected String type;
    @XmlAttribute(name = "reference", required = true)
    protected String reference;
    @XmlAttribute(name = "id")
    protected String id;
    @XmlAttribute(name = "version")
    protected String version;
    @XmlAttribute(name = "access-rights")
    protected String accessRights;

    public Artifact() {
        super(null);
    }

    public Artifact(String description) {
        super(description);
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
     * Obtém o valor da propriedade type.
     *
     * @return possible object is {@link String }
     *
     */
    @Column(name = "type", nullable = false, length = 50)
    public String getType() {
        return type;
    }

    /**
     * Define o valor da propriedade type.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setType(String value) {
        this.type = value;
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
     * Obtém o valor da propriedade id.
     *
     * @return possible object is {@link String }
     *
     */
    @Id
    @Basic(optional = false)
    @Column(name = "Id", nullable = false)
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
     * Obtém o valor da propriedade version.
     *
     * @return possible object is {@link String }
     *
     */
    @Column(name = "artifact_version", nullable = false, length = 50)
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
     * Obtém o valor da propriedade accessRights.
     *
     * @return possible object is {@link String }
     *
     */
    public String getAccessRights() {
        return accessRights;
    }

    /**
     * Define o valor da propriedade accessRights.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setAccessRights(String value) {
        this.accessRights = value;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.id);
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
        final Artifact other = (Artifact) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
