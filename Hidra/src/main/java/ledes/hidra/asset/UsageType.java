
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
    protected UsageType.ArtifactActivities artifactActivities;
    @XmlElement(required = true)
    protected UsageType.ContextReferences contextReferences;

    /**
     * Gets the value of the property artifactActivities.
     * 
     * @return
     *     possible object is
     *     {@link UsageType.ArtifactActivities }
     *     
     */
    public UsageType.ArtifactActivities getArtifactActivities() {
        return artifactActivities;
    }

    /**
     * Define the value of the property artifactActivities.
     * 
     * @param value
     *     allowed object is
     *     {@link UsageType.ArtifactActivities }
     *     
     */
    public void setArtifactActivities(UsageType.ArtifactActivities value) {
        this.artifactActivities = value;
    }

    /**
     * Gets the value of the property contextReferences.
     * 
     * @return
     *     possible object is
     *     {@link UsageType.ContextReferences }
     *     
     */
    public UsageType.ContextReferences getContextReferences() {
        return contextReferences;
    }

    /**
     * Define the value of the property contextReferences.
     * 
     * @param value
     *     allowed object is
     *     {@link UsageType.ContextReferences }
     *     
     */
    public void setContextReferences(UsageType.ContextReferences value) {
        this.contextReferences = value;
    }


    
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "artifactActivities"
    })
    public static class ArtifactActivities {

        @XmlElement(required = true)
        protected List<ArtifactActivy> artifactActivities;

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
         * @return 
         */
        public List<ArtifactActivy> getArtifactActivities() {
            if (artifactActivities == null) {
                artifactActivities = new ArrayList<ArtifactActivy>();
            }
            return this.artifactActivities;
        }

    }


    
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "contextReferences"
    })
    public static class ContextReferences {

        @XmlElement(required = true)
        protected List<ContextReference> contextReferences;

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
         * @return 
         */
        public List<ContextReference> getContextReferences() {
            if (contextReferences == null) {
                contextReferences = new ArrayList<ContextReference>();
            }
            return this.contextReferences;
        }

    }

}
