//
// Este arquivo foi gerado pela Arquitetura JavaTM para Implementação de Referência (JAXB) de Bind XML, v2.2.5-2 
// Consulte <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas as modificações neste arquivo serão perdidas após a recompilação do esquema de origem. 
// Gerado em: AM.09.07 às 01:26:24 AM AMT 
//
package ledes.hidra.rest.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Classe Java de anonymous complex type.
 *
 * <p>
 * O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro
 * desta classe.
 *
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Asset" maxOccurs="unbounded">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="reference" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="relationshipType" type="{http://www.w3.org/2001/XMLSchema}string" />
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
@XmlType(name = "", propOrder = {
    "asset"
})
@XmlRootElement(name = "RelatedAssets")
public class RelatedAssets {

    @XmlElement(name = "Asset", required = true)
    protected List<RelatedAssets.Asset> asset;

    /**
     * Gets the value of the asset property.
     *
     * <p>
     * This accessor method returns a reference to the live list, not a
     * snapshot. Therefore any modification you make to the returned list will
     * be present inside the JAXB object. This is why there is not a
     * <CODE>set</CODE> method for the asset property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAsset().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RelatedAssets.Asset }
     *
     *
     */
    public List<RelatedAssets.Asset> getAsset() {
        if (asset == null) {
            asset = new ArrayList<RelatedAssets.Asset>();
        }
        return this.asset;
    }

    /**
     * <p>
     * Classe Java de anonymous complex type.
     *
     * <p>
     * O seguinte fragmento do esquema especifica o conteúdo esperado contido
     * dentro desta classe.
     *
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
     *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}string" />
     *       &lt;attribute name="reference" type="{http://www.w3.org/2001/XMLSchema}string" />
     *       &lt;attribute name="relationshipType" type="{http://www.w3.org/2001/XMLSchema}string" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     *
     *
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class Asset {

        @XmlAttribute(name = "name")
        protected String name;
        @XmlAttribute(name = "id")
        protected String id;
        @XmlAttribute(name = "reference")
        protected String reference;
        @XmlAttribute(name = "relationshipType")
        protected String relationshipType;

        public Asset() {
        }

        public Asset(String name, String id, String reference, String relationshipType) {
            this.name = name;
            this.id = id;
            this.reference = reference;
            this.relationshipType = relationshipType;
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
         * Obtém o valor da propriedade relationshipType.
         *
         * @return possible object is {@link String }
         *
         */
        public String getRelationshipType() {
            return relationshipType;
        }

        /**
         * Define o valor da propriedade relationshipType.
         *
         * @param value allowed object is {@link String }
         *
         */
        public void setRelationshipType(String value) {
            this.relationshipType = value;
        }

    }

}
