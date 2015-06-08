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
     */
    public List<VariabilityPointBinding> getVariability() {
        if (variability == null) {
            variability = new ArrayList<VariabilityPointBinding>();
        }
        return this.variability;
    }

    /**
     * Gets the value of the property id.
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
     * Define the value of the property id.
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
     * Gets the value of the property task.
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
     * Define the value of the property task.
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
     * Gets the value of the property reference.
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
     * Define the value of the property reference.
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
     * Gets the value of the property role.
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
     * Define the value of the property role.
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
     * Gets the value of the property taskRole.
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
     * Define the value of the property taskRole.
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
