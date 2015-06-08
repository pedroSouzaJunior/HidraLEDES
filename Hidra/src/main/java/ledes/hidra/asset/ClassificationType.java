package ledes.hidra.asset;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;



@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "classificationType", propOrder = {
    "contexts",
    "descriptionGroups"
})
public class ClassificationType {

    @XmlElement(required = true)
    protected List<Context> contexts;
    @XmlElement(required = true)
    protected List<DescriptionGroup> descriptionGroups;

    /**
     * Gets the value of the contexts property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the contexts property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getContexts().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Context }
     * 
     * 
     */
    public List<Context> getContexts() {
        if (contexts == null) {
            contexts = new ArrayList<Context>();
        }
        return this.contexts;
    }

    /**
     * Gets the value of the descriptionGroups property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the descriptionGroups property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDescriptionGroups().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DescriptionGroup }
     * 
     * 
     */
    public List<DescriptionGroup> getDescriptionGroups() {
        if (descriptionGroups == null) {
            descriptionGroups = new ArrayList<DescriptionGroup>();
        }
        return this.descriptionGroups;
    }

}
