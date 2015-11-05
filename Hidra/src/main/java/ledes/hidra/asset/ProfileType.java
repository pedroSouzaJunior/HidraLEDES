package ledes.hidra.asset;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "profileType")
@Entity
@Table(name = "PROFILE")
public class ProfileType implements Serializable {

    @XmlAttribute(name = "name", required = true)
    protected String name;
    @XmlAttribute(name = "id-history", required = true)
    protected String idHistory;
    @XmlAttribute(name = "version-major", required = true)
    protected int versionMajor;
    @XmlAttribute(name = "version-minor", required = true)
    protected int versionMinor;

    protected Asset asset;

    public ProfileType() {
    }

    @OneToOne(optional = false)
    @JoinColumn(name = "asset_id")
    public Asset getAsset() {
        return asset;
    }

    public void setAsset(Asset asset) {
        this.asset = asset;
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
     * Obtém o valor da propriedade idHistory.
     *
     * @return possible object is {@link String }
     *
     */
    @Id
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    public String getIdHistory() {
        return idHistory;
    }

    /**
     * Define o valor da propriedade idHistory.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setIdHistory(String value) {
        this.idHistory = value;
    }

    /**
     * Obtém o valor da propriedade versionMajor.
     *
     */
    @Column(name = "major_version", nullable = false, length = 50)
    public int getVersionMajor() {
        return versionMajor;
    }

    /**
     * Define o valor da propriedade versionMajor.
     *
     */
    public void setVersionMajor(int value) {
        this.versionMajor = value;
    }

    /**
     * Obtém o valor da propriedade versionMinor.
     *
     */
    @Column(name = "minor_version", nullable = false, length = 50)
    public int getVersionMinor() {
        return versionMinor;
    }

    /**
     * Define o valor da propriedade versionMinor.
     *
     */
    public void setVersionMinor(int value) {
        this.versionMinor = value;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.idHistory);
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
        final ProfileType other = (ProfileType) obj;
        if (!Objects.equals(this.idHistory, other.idHistory)) {
            return false;
        }
        return true;
    }

}
