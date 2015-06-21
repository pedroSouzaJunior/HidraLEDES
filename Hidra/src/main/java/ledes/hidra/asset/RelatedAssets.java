
package ledes.hidra.asset;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;



@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "relatedAssets", propOrder = {
    "listOfRelatedAssets"
})
public class RelatedAssets {

    @XmlElement(required = true)
    protected List<RelatedAssetType> listOfRelatedAssets;

    /**
     * Gets the value of the listOfRelatedAssets property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the listOfRelatedAssets property.
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
    public List<RelatedAssetType> getListOfRelatedAssets() {
        if (listOfRelatedAssets == null) {
            listOfRelatedAssets = new ArrayList<RelatedAssetType>();
        }
        return this.listOfRelatedAssets;
    }

}
