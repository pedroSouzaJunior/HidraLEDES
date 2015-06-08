package ledes.hidra.asset;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;



@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "profileType")
public class ProfileType {

    @XmlAttribute(name = "name", required = true)
    protected String name;
    @XmlAttribute(name = "id-history", required = true)
    protected String idHistory;
    @XmlAttribute(name = "version-major", required = true)
    protected int versionMajor;
    @XmlAttribute(name = "version-minor", required = true)
    protected int versionMinor;

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
     * Obtém o valor da propriedade idHistory.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdHistory() {
        return idHistory;
    }

    /**
     * Define o valor da propriedade idHistory.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdHistory(String value) {
        this.idHistory = value;
    }

    /**
     * Obtém o valor da propriedade versionMajor.
     * 
     */
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

}
