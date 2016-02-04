package ledes.hidra.asset;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Classe Java de relatedProfile complex type.
 *
 * <p>
 * O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro
 * desta classe.
 *
 * <pre>
 * &lt;complexType name="relatedProfile">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="version-major" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="version-minor" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="reference" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="parent-id" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "relatedProfile")
public class RelatedProfile extends Description {

    @XmlAttribute(name = "name")
    protected String name;
    @XmlAttribute(name = "id")
    protected String id;
    @XmlAttribute(name = "version-major")
    protected Integer versionMajor;
    @XmlAttribute(name = "version-minor")
    protected Integer versionMinor;
    @XmlAttribute(name = "reference")
    protected String reference;
    @XmlAttribute(name = "parent-id")
    protected String parentId;

    public RelatedProfile() {
        super(null);
    }

    public RelatedProfile(String description) {
        super(description);
    }

    /**
     * Obtém o valor da propriedade name.
     *
     * @return possible object is {@link String }
     *
     */
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
     * Obtém o valor da propriedade versionMajor.
     *
     * @return possible object is {@link Integer }
     *
     */
    public Integer getVersionMajor() {
        return versionMajor;
    }

    /**
     * Define o valor da propriedade versionMajor.
     *
     * @param value allowed object is {@link Integer }
     *
     */
    public void setVersionMajor(Integer value) {
        this.versionMajor = value;
    }

    /**
     * Obtém o valor da propriedade versionMinor.
     *
     * @return possible object is {@link Integer }
     *
     */
    public Integer getVersionMinor() {
        return versionMinor;
    }

    /**
     * Define o valor da propriedade versionMinor.
     *
     * @param value allowed object is {@link Integer }
     *
     */
    public void setVersionMinor(Integer value) {
        this.versionMinor = value;
    }

    /**
     * Obtém o valor da propriedade reference.
     *
     * @return possible object is {@link String }
     *
     */
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
     * Obtém o valor da propriedade parentId.
     *
     * @return possible object is {@link String }
     *
     */
    public String getParentId() {
        return parentId;
    }

    /**
     * Define o valor da propriedade parentId.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setParentId(String value) {
        this.parentId = value;
    }

}
