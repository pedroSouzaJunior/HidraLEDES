//
// Este arquivo foi gerado pela Arquitetura JavaTM para Implementação de Referência (JAXB) de Bind XML, v2.2.5-2 
// Consulte <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas as modificações neste arquivo serão perdidas após a recompilação do esquema de origem. 
// Gerado em: PM.06.07 às 08:50:12 PM AMT 
//


package ledes.hidra.asset;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java de usageType complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="usageType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="artifactActivities">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="artifactActivities" type="{}artifactActivy" maxOccurs="unbounded"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="contextReferences">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="contextReferences" type="{}contextReference" maxOccurs="unbounded"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "usageType", propOrder = {
    "artifactActivities",
    "contextReferences"
})
public class UsageType {

    @XmlElement(required = true)
    protected UsageType.ArtifactActivities artifactActivities;
    @XmlElement(required = true)
    protected UsageType.ContextReferences contextReferences;

    /**
     * Obtém o valor da propriedade artifactActivities.
     * 
     * @return
     *     possible object is
     *     {@link UsageType.ArtifactActivities }
     *     
     */
    public UsageType.ArtifactActivities getArtifactActivities() {
        return artifactActivities;
    }

    /**
     * Define o valor da propriedade artifactActivities.
     * 
     * @param value
     *     allowed object is
     *     {@link UsageType.ArtifactActivities }
     *     
     */
    public void setArtifactActivities(UsageType.ArtifactActivities value) {
        this.artifactActivities = value;
    }

    /**
     * Obtém o valor da propriedade contextReferences.
     * 
     * @return
     *     possible object is
     *     {@link UsageType.ContextReferences }
     *     
     */
    public UsageType.ContextReferences getContextReferences() {
        return contextReferences;
    }

    /**
     * Define o valor da propriedade contextReferences.
     * 
     * @param value
     *     allowed object is
     *     {@link UsageType.ContextReferences }
     *     
     */
    public void setContextReferences(UsageType.ContextReferences value) {
        this.contextReferences = value;
    }


    /**
     * <p>Classe Java de anonymous complex type.
     * 
     * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="artifactActivities" type="{}artifactActivy" maxOccurs="unbounded"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "artifactActivities"
    })
    public static class ArtifactActivities {

        @XmlElement(required = true)
        protected List<ArtifactActivy> artifactActivities;

        /**
         * Gets the value of the artifactActivities property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the artifactActivities property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getArtifactActivities().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link ArtifactActivy }
         * 
         * 
         */
        public List<ArtifactActivy> getArtifactActivities() {
            if (artifactActivities == null) {
                artifactActivities = new ArrayList<ArtifactActivy>();
            }
            return this.artifactActivities;
        }

    }


    /**
     * <p>Classe Java de anonymous complex type.
     * 
     * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="contextReferences" type="{}contextReference" maxOccurs="unbounded"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "contextReferences"
    })
    public static class ContextReferences {

        @XmlElement(required = true)
        protected List<ContextReference> contextReferences;

        /**
         * Gets the value of the contextReferences property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the contextReferences property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getContextReferences().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link ContextReference }
         * 
         * 
         */
        public List<ContextReference> getContextReferences() {
            if (contextReferences == null) {
                contextReferences = new ArrayList<ContextReference>();
            }
            return this.contextReferences;
        }

    }

}
