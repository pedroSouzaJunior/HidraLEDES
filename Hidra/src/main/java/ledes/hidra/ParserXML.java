/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ledes.hidra;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Iterator;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import ledes.hidra.asset.ArtifactType;
import ledes.hidra.asset.Asset;
import ledes.hidra.asset.SolutionType;
import org.xmlpull.v1.XmlPullParserException;

/**
 *
 * @author drocha
 */
public class ParserXML {

    public static void main(String[] args) throws FileNotFoundException, XmlPullParserException, JAXBException {

        FileReader xml = new FileReader("/home/danielli/Downloads/ativo.xml");
        Asset a;
        JAXBContext jaxbContext = JAXBContext.newInstance(Asset.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

        
        a = (Asset) unmarshaller.unmarshal(xml);
        System.out.println(a.getSolution().getDesign().getArtifact().get(0).getReference());
        
        
        //verificar existencia de ativo
        
        
        SolutionType.Artifacts artifacts = a.getSolution().getArtifacts();
        for(ArtifactType art : artifacts.getArtifact()){
            art.getReference();
        
        }

    }
}
