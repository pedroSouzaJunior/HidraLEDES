
package ledes.hidra.asset;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Classe Java de artifactDependency complex type.
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "artifactDependency")
public class ArtifactDependency {

    @XmlAttribute(name = "artifactId", required = true)
    protected String artifactId;
    @XmlAttribute(name = "dependencyType", required = true)
    protected String dependencyType;

    /**
     * Obtém o valor da propriedade artifactId.
     *
     * @return possible object is {@link String }
     *
     */
    public String getArtifactId() {
        return artifactId;
    }

    /**
     * Define o valor da propriedade artifactId.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setArtifactId(String value) {
        this.artifactId = value;
    }

    /**
     * Obtém o valor da propriedade dependencyType.
     *
     * @return possible object is {@link String }
     *
     */
    public String getDependencyType() {
        return dependencyType;
    }

    /**
     * Define o valor da propriedade dependencyType.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setDependencyType(String value) {
        this.dependencyType = value;
    }

}
