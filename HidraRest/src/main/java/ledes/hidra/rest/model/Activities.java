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
 * Classe Java de Activities complex type.
 *
 * <p>
 * O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro
 * desta classe.
 *
 * <pre>
 * &lt;complexType name="Activities">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="listOfVariability" type="{}Variability" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="task" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="reference" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="role" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="taskRole" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Acitivities", propOrder = {
    "listOfVariability"
})
public class Activities {

    @XmlElement(required = true)
    protected List<Variability> listOfVariability;
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

    public Activities() {
    }

    public Activities(String id, String task, String reference, String role, String taskRole) {

        this.id = id;
        this.task = task;
        this.reference = reference;
        this.role = role;
        this.taskRole = taskRole;
    }

    /**
     * Gets the value of the listOfVariability property.
     *
     * <p>
     * This accessor method returns a reference to the live list, not a
     * snapshot. Therefore any modification you make to the returned list will
     * be present inside the JAXB object. This is why there is not a
     * <CODE>set</CODE> method for the listOfVariability property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getListOfVariability().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Variability }
     *
     *
     */
    public List<Variability> getListOfVariability() {
        if (listOfVariability == null) {
            listOfVariability = new ArrayList<Variability>();
        }
        return this.listOfVariability;
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
     * Obtém o valor da propriedade task.
     *
     * @return possible object is {@link String }
     *
     */
    public String getTask() {
        return task;
    }

    /**
     * Define o valor da propriedade task.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setTask(String value) {
        this.task = value;
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
     * Obtém o valor da propriedade role.
     *
     * @return possible object is {@link String }
     *
     */
    public String getRole() {
        return role;
    }

    /**
     * Define o valor da propriedade role.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setRole(String value) {
        this.role = value;
    }

    /**
     * Obtém o valor da propriedade taskRole.
     *
     * @return possible object is {@link String }
     *
     */
    public String getTaskRole() {
        return taskRole;
    }

    /**
     * Define o valor da propriedade taskRole.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setTaskRole(String value) {
        this.taskRole = value;
    }

}
