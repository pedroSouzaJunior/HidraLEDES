package ledes.hidra.asset;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "context", propOrder = {
    "descriptionGroup"
})
@Entity
@Table(name = "CONTEXT")
public class Context implements Serializable {

    @XmlElement(required = true)
    protected List<DescriptionGroup> descriptionGroup;
    @XmlAttribute(name = "name")
    protected String name;
    @XmlAttribute(name = "id")
    protected String id;
    @XmlAttribute(name = "description")
    protected String description;

    protected ClassificationType classification;

    public Context() {
    }

    @ManyToOne
    @JoinColumn(name = "classification_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    public ClassificationType getClassification() {
        return classification;
    }

    public void setClassification(ClassificationType classification) {
        this.classification = classification;
    }

    /**
     * Gets the value of the descriptionGroup property.
     *
     * <p>
     * This accessor method returns a reference to the live list, not a
     * snapshot. Therefore any modification you make to the returned list will
     * be present inside the JAXB object. This is why there is not a
     * <CODE>set</CODE> method for the descriptionGroup property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDescriptionGroup().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DescriptionGroup }
     *
     *
     * @return
     */
    @OneToMany(orphanRemoval = true,
            mappedBy = "context",
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    public List<DescriptionGroup> getDescriptionGroup() {
        if (descriptionGroup == null) {
            descriptionGroup = new ArrayList<DescriptionGroup>();
        }
        return this.descriptionGroup;
    }

    public void setDescriptionGroup(List<DescriptionGroup> descriptionGroup) {
        this.descriptionGroup = descriptionGroup;
    }

    /**
     * Obtém o valor da propriedade name.
     *
     * @return possible object is {@link String }
     *
     */
    @Column(name = "name", length = 50, nullable = false)
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
    @Id
    @Basic(optional = false)
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
     * Obtém o valor da propriedade description.
     *
     * @return possible object is {@link String }
     *
     */
    @Column(name = "description", nullable = false, length = 255)
    public String getDescription() {
        return description;
    }

    /**
     * Define o valor da propriedade description.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setDescription(String value) {
        this.description = value;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Context other = (Context) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
