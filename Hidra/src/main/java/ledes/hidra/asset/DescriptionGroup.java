package ledes.hidra.asset;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "descriptionGroup")
@Entity
@Table(name = "DESCRIPTOR_GROUP")
public class DescriptionGroup extends Description implements Serializable {

    @XmlAttribute(name = "name")
    protected String name;
    @XmlAttribute(name = "reference")
    protected String reference;
    @XmlAttribute(name = "description")
    protected String description;
    protected String id;

    protected ClassificationType classificationType;
    protected Context context;

    public DescriptionGroup() {
        super(null);
    }

    public DescriptionGroup(String description) {
        super(description);
    }

    @ManyToOne
    @JoinColumn(name = "context_id",
            foreignKey = @ForeignKey(name = "fk_context"))
    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @ManyToOne
    @JoinColumn(name = "classification_id",
            foreignKey = @ForeignKey(name = "fk_classification"))
    @OnDelete(action = OnDeleteAction.CASCADE)
    public ClassificationType getClassificationType() {
        return classificationType;
    }

    public void setClassificationType(ClassificationType classificationType) {
        this.classificationType = classificationType;
    }

    @Id
    @Basic(optional = false)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * Obtém o valor da propriedade name.
     *
     * @return possible object is {@link String }
     *
     */
    @Column(name = "name", nullable = false, length = 50)
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
     * Obtém o valor da propriedade reference.
     *
     * @return possible object is {@link String }
     *
     */
    @Column(name = "reference", nullable = false, length = 50)
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
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.id);
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
        final DescriptionGroup other = (DescriptionGroup) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
