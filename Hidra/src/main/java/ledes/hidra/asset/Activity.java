package ledes.hidra.asset;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "activity", propOrder = {
    "variability"
})
public class Activity {

    @XmlElement(required = true)
    protected List<VariabilityPointBinding> variability;
    @XmlAttribute(name = "id")
    protected String id;
    @XmlAttribute(name = "task")
    protected String task;
    @XmlAttribute(name = "reference")
    protected String reference;
    @XmlAttribute(name = "role")
    protected String role;
    @XmlAttribute(name = "taskRole")
    protected String taskRole;

    /**
     * Gets the value of the variability property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the variability property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getVariability().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link VariabilityPointBinding }
     * 
     * 
     * @return 
     */
    public List<VariabilityPointBinding> getVariability() {
        if (variability == null) {
            variability = new ArrayList<VariabilityPointBinding>();
        }
        return this.variability;
    }

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
     * Obtém o valor da propriedade task.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTask() {
        return task;
    }

    /**
     * Define o valor da propriedade task.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTask(String value) {
        this.task = value;
    }

    /**
     * Obtém o valor da propriedade reference.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReference() {
        return reference;
    }

    /**
     * Define o valor da propriedade reference.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReference(String value) {
        this.reference = value;
    }

    /**
     * Obtém o valor da propriedade role.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRole() {
        return role;
    }

    /**
     * Define o valor da propriedade role.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRole(String value) {
        this.role = value;
    }

    /**
     * Obtém o valor da propriedade taskRole.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTaskRole() {
        return taskRole;
    }

    /**
     * Define o valor da propriedade taskRole.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTaskRole(String value) {
        this.taskRole = value;
    }

}
