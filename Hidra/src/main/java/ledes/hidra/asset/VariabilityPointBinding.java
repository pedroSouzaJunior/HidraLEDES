package ledes.hidra.asset;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "VariabilityPointBinding")
@Entity
@Table(name = "VARIABILITY_POINT_BINDING")
public class VariabilityPointBinding implements Serializable {

    @XmlAttribute(name = "id")
    protected String id;
    @XmlAttribute(name = "bindingRule")
    protected String bindingRule;

    protected Activity activity;

    public VariabilityPointBinding() {
    }

    @ManyToOne
    @JoinColumn(name = "activity_id")
    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
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
     * Obtém o valor da propriedade bindingRule.
     *
     * @return possible object is {@link String }
     *
     */
    @Column(name = "binding_rule", nullable = false, length = 100)
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + Objects.hashCode(this.id);
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
        final VariabilityPointBinding other = (VariabilityPointBinding) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
