package ledes.hidra.asset;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Classe Java de artifactContext complex type.
 *
 * <p>
 * O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro
 * desta classe.
 *
 * <pre>
 * &lt;complexType name="artifactContext">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="contextId" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "artifactContext")
public class ArtifactContext {

    @XmlAttribute(name = "contextId", required = true)
    protected String contextId;

    /**
     * Obtém o valor da propriedade contextId.
     *
     * @return possible object is {@link String }
     *
     */
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

}
