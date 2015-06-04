//
// Este arquivo foi gerado pela Arquitetura JavaTM para Implementação de Referência (JAXB) de Bind XML, v2.2.5-2 
// Consulte <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas as modificações neste arquivo serão perdidas após a recompilação do esquema de origem. 
// Gerado em: PM.06.04 às 04:13:36 PM AMT 
//


package ledes.hidra.asset;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java de profileType complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="profileType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="id-history" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="version-major" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="version-minor" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
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
