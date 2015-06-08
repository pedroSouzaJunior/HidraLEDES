


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
     * Gets the value of the property name.
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
     * Define the value of the property name.
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
     * Gets the value of the property idHistory.
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
     * Define the value of the property idHistory.
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
     * Gets the value of the property versionMajor.
     * 
     * @return 
     */
    public int getVersionMajor() {
        return versionMajor;
    }

    /**
     * Define the value of the property versionMajor.
     * 
     * @param value
     */
    public void setVersionMajor(int value) {
        this.versionMajor = value;
    }

    /**
     * Gets the value of the property versionMinor.
     * 
     * @return 
     */
    public int getVersionMinor() {
        return versionMinor;
    }

    /**
     * Define the value of the property versionMinor.
     * 
     * @param value
     */
    public void setVersionMinor(int value) {
        this.versionMinor = value;
    }

}
