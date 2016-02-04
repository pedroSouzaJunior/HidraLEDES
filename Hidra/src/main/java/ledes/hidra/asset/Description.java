//
// Este arquivo foi gerado pela Arquitetura JavaTM para Implementação de Referência (JAXB) de Bind XML, v2.2.5-2 
// Consulte <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas as modificações neste arquivo serão perdidas após a recompilação do esquema de origem. 
// Gerado em: PM.02.02 às 02:24:31 PM AMST 
//
package ledes.hidra.asset;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Classe Java de Description complex type.
 *
 * <p>
 * O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro
 * desta classe.
 *
 * <pre>
 * &lt;complexType name="Description">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="description" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Description")
@XmlSeeAlso({
    Asset.class,
    ProfileType.class,
    RelatedProfile.class,
    Context.class,
    DescriptionGroup.class,
    Artifact.class,
    Activity.class
})
public class Description {

    public Description(String description) {
        this.description = description;
    }

    @XmlAttribute(name = "description")
    protected String description;

    /**
     * Obtém o valor da propriedade description.
     *
     * @return possible object is {@link String }
     *
     */
    public String getDescription() {
        return description;
    }

    /**
     * Define o valor da propriedade description.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setDescription(String value) {
        this.description = value;
    }

}
