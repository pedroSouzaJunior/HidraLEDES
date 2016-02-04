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

    public SolutionType() {
        artifacts = new Artifacts();
        requirements = new Requirements();
        design = new Design();
        implementation = new Implementation();
        test = new Test();
    }

    /**
     * Obtém o valor da propriedade artifacts.
     *
     * @return possible object is {@link SolutionType.Artifacts }
     *
     */
    public SolutionType.Artifacts getArtifacts() {
        return artifacts;
    }

    /**
     * Define o valor da propriedade artifacts.
     *
     * @param value allowed object is {@link SolutionType.Artifacts }
     *
     */
    public void setArtifacts(SolutionType.Artifacts value) {
        this.artifacts = value;
    }

    /**
     * Obtém o valor da propriedade requirements.
     *
     * @return possible object is {@link SolutionType.Requirements }
     *
     */
    public SolutionType.Requirements getRequirements() {
        return requirements;
    }

    /**
     * Define o valor da propriedade requirements.
     *
     * @param value allowed object is {@link SolutionType.Requirements }
     *
     */
    public void setRequirements(SolutionType.Requirements value) {
        this.requirements = value;
    }

    /**
     * Obtém o valor da propriedade design.
     *
     * @return possible object is {@link SolutionType.Design }
     *
     */
    public SolutionType.Design getDesign() {
        return design;
    }

    /**
     * Define o valor da propriedade design.
     *
     * @param value allowed object is {@link SolutionType.Design }
     *
     */
    public void setDesign(SolutionType.Design value) {
        this.design = value;
    }

    /**
     * Obtém o valor da propriedade implementation.
     *
     * @return possible object is {@link SolutionType.Implementation }
     *
     */
    public SolutionType.Implementation getImplementation() {
        return implementation;
    }

    /**
     * Define o valor da propriedade implementation.
     *
     * @param value allowed object is {@link SolutionType.Implementation }
     *
     */
    public void setImplementation(SolutionType.Implementation value) {
        this.implementation = value;
    }

    /**
     * Obtém o valor da propriedade test.
     *
     * @return possible object is {@link SolutionType.Test }
     *
     */
    public SolutionType.Test getTest() {
        return test;
    }

    /**
     * Define o valor da propriedade test.
     *
     * @param value allowed object is {@link SolutionType.Test }
     *
     */
    public void setTest(SolutionType.Test value) {
        this.test = value;
    }

    /**
     * <p>
     * Classe Java de anonymous complex type.
     *
     * <p>
     * O seguinte fragmento do esquema especifica o conteúdo esperado contido
     * dentro desta classe.
     *
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="artifact" type="{}artifactType" maxOccurs="unbounded"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     *
     *
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "artifact"
    })
    public static class Artifacts {

        @XmlElement(required = true)
        protected List<Artifact> artifact;
        //protected List<ArtifactType> artifact;

        /**
         * Gets the value of the artifact property.
         *
         * <p>
         * This accessor method returns a reference to the live list, not a
         * snapshot. Therefore any modification you make to the returned list
         * will be present inside the JAXB object. This is why there is not a
         * <CODE>set</CODE> method for the artifact property.
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
         * {@link Artifact }
         *
         *
         */
        public List<Artifact> getArtifact() {
            if (artifact == null) {
                artifact = new ArrayList<Artifact>();
            }
            return this.artifact;
        }

    }

    /**
     * <p>
     * Classe Java de anonymous complex type.
     *
     * <p>
     * O seguinte fragmento do esquema especifica o conteúdo esperado contido
     * dentro desta classe.
     *
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="artifact" type="{}artifactType" maxOccurs="unbounded"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     *
     *
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "artifact"
    })
    public static class Design {

        @XmlElement(required = true)
        protected List<Artifact> artifact;

        /**
         * Gets the value of the artifact property.
         *
         * <p>
         * This accessor method returns a reference to the live list, not a
         * snapshot. Therefore any modification you make to the returned list
         * will be present inside the JAXB object. This is why there is not a
         * <CODE>set</CODE> method for the artifact property.
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
         * {@link Artifact }
         *
         *
         */
        public List<Artifact> getArtifact() {
            if (artifact == null) {
                artifact = new ArrayList<Artifact>();
            }
            return this.artifact;
        }

    }

    /**
     * <p>
     * Classe Java de anonymous complex type.
     *
     * <p>
     * O seguinte fragmento do esquema especifica o conteúdo esperado contido
     * dentro desta classe.
     *
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="artifact" type="{}artifactType" maxOccurs="unbounded"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     *
     *
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "artifact"
    })
    public static class Implementation {

        @XmlElement(required = true)
        protected List<Artifact> artifact;

        /**
         * Gets the value of the artifact property.
         *
         * <p>
         * This accessor method returns a reference to the live list, not a
         * snapshot. Therefore any modification you make to the returned list
         * will be present inside the JAXB object. This is why there is not a
         * <CODE>set</CODE> method for the artifact property.
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
         * {@link Artifact }
         *
         *
         */
        public List<Artifact> getArtifact() {
            if (artifact == null) {
                artifact = new ArrayList<Artifact>();
            }
            return this.artifact;
        }

    }

    /**
     * <p>
     * Classe Java de anonymous complex type.
     *
     * <p>
     * O seguinte fragmento do esquema especifica o conteúdo esperado contido
     * dentro desta classe.
     *
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="artifact" type="{}artifactType" maxOccurs="unbounded"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     *
     *
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "artifact"
    })
    public static class Requirements {

        @XmlElement(required = true)
        protected List<Artifact> artifact;

        /**
         * Gets the value of the artifact property.
         *
         * <p>
         * This accessor method returns a reference to the live list, not a
         * snapshot. Therefore any modification you make to the returned list
         * will be present inside the JAXB object. This is why there is not a
         * <CODE>set</CODE> method for the artifact property.
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
         * {@link Artifact }
         *
         *
         */
        public List<Artifact> getArtifact() {
            if (artifact == null) {
                artifact = new ArrayList<Artifact>();
            }
            return this.artifact;
        }

    }

    /**
     * <p>
     * Classe Java de anonymous complex type.
     *
     * <p>
     * O seguinte fragmento do esquema especifica o conteúdo esperado contido
     * dentro desta classe.
     *
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="artifact" type="{}artifactType" maxOccurs="unbounded"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     *
     *
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "artifact"
    })
    public static class Test {

        @XmlElement(required = true)
        protected List<Artifact> artifact;

        /**
         * Gets the value of the artifact property.
         *
         * <p>
         * This accessor method returns a reference to the live list, not a
         * snapshot. Therefore any modification you make to the returned list
         * will be present inside the JAXB object. This is why there is not a
         * <CODE>set</CODE> method for the artifact property.
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
         * {@link Artifact }
         *
         *
         */
        public List<Artifact> getArtifact() {
            if (artifact == null) {
                artifact = new ArrayList<Artifact>();
            }
            return this.artifact;
        }

    }

}
