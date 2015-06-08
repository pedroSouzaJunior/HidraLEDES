package ledes.hidra.asset;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "VariabilityPointBinding")
public class VariabilityPointBinding {

    @XmlAttribute(name = "id")
    protected String id;
    @XmlAttribute(name = "bindingRule")
    protected String bindingRule;

    /**
     * Obtém o valor da propriedade id.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Define o valor da propriedade id.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Obtém o valor da propriedade bindingRule.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBindingRule() {
        return bindingRule;
    }

    /**
     * Define o valor da propriedade bindingRule.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBindingRule(String value) {
        this.bindingRule = value;
    }

}
