

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
 *         &lt;element name="artifacts" type="{}Artifact" maxOccurs="unbounded"/>
 *         &lt;element name="requirements" type="{}Artifact" maxOccurs="unbounded"/>
 *         &lt;element name="design" type="{}Artifact" maxOccurs="unbounded"/>
 *         &lt;element name="implementation" type="{}Artifact" maxOccurs="unbounded"/>
 *         &lt;element name="test" type="{}Artifact" maxOccurs="unbounded"/>
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
    "artifacts",
    "requirements",
    "design",
    "implementation",
    "test"
})
@XmlRootElement(name = "Solution")
public class Solution {

    @XmlElement(required = true)
    protected List<Artifact> artifacts;
    @XmlElement(required = true)
    protected List<Artifact> requirements;
    @XmlElement(required = true)
    protected List<Artifact> design;
    @XmlElement(required = true)
    protected List<Artifact> implementation;
    @XmlElement(required = true)
    protected List<Artifact> test;

    /**
     * Gets the value of the artifacts property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the artifacts property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getArtifacts().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Artifact }
     * 
     * 
     */
    public List<Artifact> getArtifacts() {
        if (artifacts == null) {
            artifacts = new ArrayList<Artifact>();
        }
        return this.artifacts;
    }

    /**
     * Gets the value of the requirements property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the requirements property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRequirements().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Artifact }
     * 
     * 
     */
    public List<Artifact> getRequirements() {
        if (requirements == null) {
            requirements = new ArrayList<Artifact>();
        }
        return this.requirements;
    }

    /**
     * Gets the value of the design property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the design property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDesign().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Artifact }
     * 
     * 
     */
    public List<Artifact> getDesign() {
        if (design == null) {
            design = new ArrayList<Artifact>();
        }
        return this.design;
    }

    /**
     * Gets the value of the implementation property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the implementation property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getImplementation().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Artifact }
     * 
     * 
     */
    public List<Artifact> getImplementation() {
        if (implementation == null) {
            implementation = new ArrayList<Artifact>();
        }
        return this.implementation;
    }

    /**
     * Gets the value of the test property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the test property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTest().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Artifact }
     * 
     * 
     */
    public List<Artifact> getTest() {
        if (test == null) {
            test = new ArrayList<Artifact>();
        }
        return this.test;
    }

}
