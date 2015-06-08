package ledes.hidra.asset;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;



@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "usageType", propOrder = {
    "artifactActivities",
    "contextReferences"
})
public class UsageType {

    @XmlElement(required = true)
    protected List<ArtifactActivy> artifactActivities;
    @XmlElement(required = true)
    protected List<ContextReference> contextReferences;

    /**
     * Gets the value of the artifactActivities property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the artifactActivities property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getArtifactActivities().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ArtifactActivy }
     * 
     * 
     */
    public List<ArtifactActivy> getArtifactActivities() {
        if (artifactActivities == null) {
            artifactActivities = new ArrayList<ArtifactActivy>();
        }
        return this.artifactActivities;
    }

    /**
     * Gets the value of the contextReferences property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the contextReferences property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getContextReferences().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ContextReference }
     * 
     * 
     */
    public List<ContextReference> getContextReferences() {
        if (contextReferences == null) {
            contextReferences = new ArrayList<ContextReference>();
        }
        return this.contextReferences;
    }

}
