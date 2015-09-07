//
// Este arquivo foi gerado pela Arquitetura JavaTM para Implementação de Referência (JAXB) de Bind XML, v2.2.5-2 
// Consulte <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas as modificações neste arquivo serão perdidas após a recompilação do esquema de origem. 
// Gerado em: PM.09.06 às 11:25:16 PM AMT 
//
package ledes.hidra.rest.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Classe Java de Contexts complex type.
 *
 * <p>
 * O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro
 * desta classe.
 *
 * <pre>
 * &lt;complexType name="Contexts">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="descriptionGroup" type="{}DescriptionGroups" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *       &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="description" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Context", propOrder = {
    "descriptionGroup"
})
public class Contexts {

    @XmlElement(required = true)
    protected List<DescriptionGroups> descriptionGroup;
    @XmlAttribute(name = "name", required = true)
    protected String name;
    @XmlAttribute(name = "id")
    protected String id;
    @XmlAttribute(name = "description")
    protected String description;

    public Contexts() {
    }

    public Contexts(String name, String id, String description) {
        this.name = name;
        this.id = id;
        this.description = description;
    }

    /**
     * Gets the value of the descriptionGroup property.
     *
     * <p>
     * This accessor method returns a reference to the live list, not a
     * snapshot. Therefore any modification you make to the returned list will
     * be present inside the JAXB object. This is why there is not a
     * <CODE>set</CODE> method for the descriptionGroup property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDescriptionGroup().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DescriptionGroups }
     *
     *
     */
    public List<DescriptionGroups> getDescriptionGroup() {
        if (descriptionGroup == null) {
            descriptionGroup = new ArrayList<DescriptionGroups>();
        }
        return this.descriptionGroup;
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
