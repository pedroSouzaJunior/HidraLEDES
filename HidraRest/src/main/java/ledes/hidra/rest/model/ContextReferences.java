//
// Este arquivo foi gerado pela Arquitetura JavaTM para Implementação de Referência (JAXB) de Bind XML, v2.2.5-2 
// Consulte <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas as modificações neste arquivo serão perdidas após a recompilação do esquema de origem. 
// Gerado em: AM.09.07 às 12:18:26 AM AMT 
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
 * Classe Java de ContextReferences complex type.
 *
 * <p>
 * O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro
 * desta classe.
 *
 * <pre>
 * &lt;complexType name="ContextReferences">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="listOfActivities" type="{}Activities" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *       &lt;attribute name="contextId" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ContextReferences", propOrder = {
    "listOfActivities"
})
public class ContextReferences {

    @XmlElement(required = true)
    protected List<Activities> listOfActivities;
    @XmlAttribute(name = "contextId")
    protected String contextId;

    public ContextReferences() {
    }

    public ContextReferences(String contextId) {

        this.contextId = contextId;
    }

    /**
     * Gets the value of the listOfActivities property.
     *
     * <p>
     * This accessor method returns a reference to the live list, not a
     * snapshot. Therefore any modification you make to the returned list will
     * be present inside the JAXB object. This is why there is not a
     * <CODE>set</CODE> method for the listOfActivities property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getListOfActivities().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Activities }
     *
     *
     */
    public List<Activities> getListOfActivities() {
        if (listOfActivities == null) {
            listOfActivities = new ArrayList<Activities>();
        }
        return this.listOfActivities;
    }

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
