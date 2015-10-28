package ledes.hidra.asset;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "relatedAssets", propOrder = {
    "listOfRelatedAssets"
})
@Entity
@Table(name = "RELATED_ASSETS")
public class RelatedAssets implements Serializable {

    public RelatedAssets() {
    }

    protected String id;

    @XmlElement(required = true)
    protected List<RelatedAssetType> listOfRelatedAssets;

    /**
     * Gets the value of the listOfRelatedAssets property.
     *
     * <p>
     * This accessor method returns a reference to the live list, not a
     * snapshot. Therefore any modification you make to the returned list will
     * be present inside the JAXB object. This is why there is not a
     * <CODE>set</CODE> method for the listOfRelatedAssets property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getListOfRelatedAssets().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RelatedAssetType }
     *
     *
     */
    @ElementCollection
    @CollectionTable(name = "related_asset_details", joinColumns = @JoinColumn(name = "id_related_asset"))
    public List<RelatedAssetType> getListOfRelatedAssets() {
        if (listOfRelatedAssets == null) {
            listOfRelatedAssets = new ArrayList<RelatedAssetType>();
        }
        return this.listOfRelatedAssets;
    }

    public void setListOfRelatedAssets(List<RelatedAssetType> listOfRelatedAssets) {
        this.listOfRelatedAssets = listOfRelatedAssets;
    }

    @Id
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
