

package ledes.hidra.asset;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;



@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "solutionType", propOrder = {
    "artifacts",
    "requirements",
    "design",
    "implementation",
    "test"
})
public class SolutionType {

    @XmlElement(required = true)
    protected SolutionType.Artifacts artifacts;
    protected SolutionType.Requirements requirements;
    protected SolutionType.Design design;
    protected SolutionType.Implementation implementation;
    protected SolutionType.Test test;

    /**
     * Gets the value of the property artifacts.
     * 
     * @return
     *     possible object is
     *     {@link SolutionType.Artifacts }
     *     
     */
    public SolutionType.Artifacts getArtifacts() {
        return artifacts;
    }

    /**
     * Define the value of the property artifacts.
     * 
     * @param value
     *     allowed object is
     *     {@link SolutionType.Artifacts }
     *     
     */
    public void setArtifacts(SolutionType.Artifacts value) {
        this.artifacts = value;
    }

    /**
     * Gets the value of the property requirements.
     * 
     * @return
     *     possible object is
     *     {@link SolutionType.Requirements }
     *     
     */
    public SolutionType.Requirements getRequirements() {
        return requirements;
    }

    /**
     * Define the value of the property requirements.
     * 
     * @param value
     *     allowed object is
     *     {@link SolutionType.Requirements }
     *     
     */
    public void setRequirements(SolutionType.Requirements value) {
        this.requirements = value;
    }

    /**
     * Gets the value of the property design.
     * 
     * @return
     *     possible object is
     *     {@link SolutionType.Design }
     *     
     */
    public SolutionType.Design getDesign() {
        return design;
    }

    /**
     * Define the value of the property design.
     * 
     * @param value
     *     allowed object is
     *     {@link SolutionType.Design }
     *     
     */
    public void setDesign(SolutionType.Design value) {
        this.design = value;
    }

    /**
     * Gets the value of the property implementation.
     * 
     * @return
     *     possible object is
     *     {@link SolutionType.Implementation }
     *     
     */
    public SolutionType.Implementation getImplementation() {
        return implementation;
    }

    /**
     * Define the value of the property implementation.
     * 
     * @param value
     *     allowed object is
     *     {@link SolutionType.Implementation }
     *     
     */
    public void setImplementation(SolutionType.Implementation value) {
        this.implementation = value;
    }

    /**
     * Gets the value of the property test.
     * 
     * @return
     *     possible object is
     *     {@link SolutionType.Test }
     *     
     */
    public SolutionType.Test getTest() {
        return test;
    }

    /**
     * Define the value of the property test.
     * 
     * @param value
     *     allowed object is
     *     {@link SolutionType.Test }
     *     
     */
    public void setTest(SolutionType.Test value) {
        this.test = value;
    }


    
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "artifact"
    })
    public static class Artifacts {

        @XmlElement(required = true)
        protected List<ArtifactType> artifact;

        /**
         * Gets the value of the artifact property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the artifact property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getArtifact().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link ArtifactType }
         * 
         * 
         * @return 
         */
        public List<ArtifactType> getArtifact() {
            if (artifact == null) {
                artifact = new ArrayList<>();
            }
            return this.artifact;
        }

    }


    
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "artifact"
    })
    public static class Design {

        @XmlElement(required = true)
        protected List<ArtifactType> artifact;

        /**
         * Gets the value of the artifact property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the artifact property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getArtifact().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link ArtifactType }
         * 
         * 
         */
        public List<ArtifactType> getArtifact() {
            if (artifact == null) {
                artifact = new ArrayList<ArtifactType>();
            }
            return this.artifact;
        }

    }


    
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "artifact"
    })
    public static class Implementation {

        @XmlElement(required = true)
        protected List<ArtifactType> artifact;

        /**
         * Gets the value of the artifact property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the artifact property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getArtifact().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link ArtifactType }
         * 
         * 
         */
        public List<ArtifactType> getArtifact() {
            if (artifact == null) {
                artifact = new ArrayList<ArtifactType>();
            }
            return this.artifact;
        }

    }


    
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "artifact"
    })
    public static class Requirements {

        @XmlElement(required = true)
        protected List<ArtifactType> artifact;

        /**
         * Gets the value of the artifact property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the artifact property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getArtifact().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link ArtifactType }
         * 
         * 
         */
        public List<ArtifactType> getArtifact() {
            if (artifact == null) {
                artifact = new ArrayList<ArtifactType>();
            }
            return this.artifact;
        }

    }


    
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "artifact"
    })
    public static class Test {

        @XmlElement(required = true)
        protected List<ArtifactType> artifact;

        /**
         * Gets the value of the artifact property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the artifact property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getArtifact().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link ArtifactType }
         * 
         * 
         */
        public List<ArtifactType> getArtifact() {
            if (artifact == null) {
                artifact = new ArrayList<ArtifactType>();
            }
            return this.artifact;
        }

    }

}
