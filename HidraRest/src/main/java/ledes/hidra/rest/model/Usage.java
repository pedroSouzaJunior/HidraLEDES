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
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


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
 *         &lt;element name="artifactActivitie" type="{}ArtifactActivys" maxOccurs="unbounded"/>
 *         &lt;element name="contextReference" type="{}ContextReferences" maxOccurs="unbounded"/>
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
    "artifactActivitie",
    "contextReference"
})
@XmlRootElement(name = "Usage")
public class Usage {

    @XmlElement(required = true)
    protected List<ArtifactActivys> artifactActivitie;
    @XmlElement(required = true)
    protected List<ContextReferences> contextReference;

    public Usage() {
    }

    public Usage(List<ArtifactActivys> artifactActivitie, List<ContextReferences> contextReference) {
        this.artifactActivitie = artifactActivitie;
        this.contextReference = contextReference;
    }

    
    /**
     * Gets the value of the artifactActivitie property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the artifactActivitie property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getArtifactActivitie().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ArtifactActivys }
     * 
     * 
     */
    public List<ArtifactActivys> getArtifactActivitie() {
        if (artifactActivitie == null) {
            artifactActivitie = new ArrayList<ArtifactActivys>();
        }
        return this.artifactActivitie;
    }

    /**
     * Gets the value of the contextReference property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the contextReference property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getContextReference().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ContextReferences }
     * 
     * 
     */
    public List<ContextReferences> getContextReference() {
        if (contextReference == null) {
            contextReference = new ArrayList<ContextReferences>();
        }
        return this.contextReference;
    }

}
