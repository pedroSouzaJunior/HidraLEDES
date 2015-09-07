//
// Este arquivo foi gerado pela Arquitetura JavaTM para Implementação de Referência (JAXB) de Bind XML, v2.2.5-2 
// Consulte <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas as modificações neste arquivo serão perdidas após a recompilação do esquema de origem. 
// Gerado em: AM.09.07 às 12:18:26 AM AMT 
//
package ledes.hidra.rest.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Classe Java de Variability complex type.
 *
 * <p>
 * O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro
 * desta classe.
 *
 * <pre>
 * &lt;complexType name="Variability">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="bindingRule" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Variability")
public class Variability {

    @XmlAttribute(name = "id")
    protected String id;
    @XmlAttribute(name = "bindingRule")
    protected String bindingRule;

    public Variability() {
    }

    public Variability(String id, String bindingRule) {
        this.id = id;
        this.bindingRule = bindingRule;
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
     * Obtém o valor da propriedade bindingRule.
     *
     * @return possible object is {@link String }
     *
     */
    public String getBindingRule() {
        return bindingRule;
    }

    /**
     * Define o valor da propriedade bindingRule.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setBindingRule(String value) {
        this.bindingRule = value;
    }

}
