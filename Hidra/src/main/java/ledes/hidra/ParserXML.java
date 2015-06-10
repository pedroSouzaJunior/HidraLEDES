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
import ledes.hidra.asset.ArtifactActivy;
import ledes.hidra.asset.ArtifactType;
import ledes.hidra.asset.Asset;
import ledes.hidra.asset.Context;
import ledes.hidra.asset.DescriptionGroup;
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
      //  System.out.println(a.getSolution().getDesign().getArtifact().get(0).getReference());

        //verificar existencia de ativo
        SolutionType.Artifacts artifacts = a.getSolution().getArtifacts();
        for (ArtifactType art : artifacts.getArtifact()) {

            System.out.println("Solution Artifacts: "
                    + "\nReference: " + art.getReference()
                    + "\nId: " + art.getId()
                    + "\nName: " + art.getName()
                    + "\nType: " + art.getType()
                    + "\nVersion: " + art.getVersion());

        }
        for (ArtifactType req : a.getSolution().getRequirements().getArtifact()) {

            System.out.println("Requerimentos: "
                    + "\nReference: " + req.getReference()
                    + "\nId: " + req.getId()
                    + "\nName: " + req.getName()
                    + "\nType: " + req.getType()
                    + "\nVersion: " + req.getVersion());

        }

        for (ArtifactType des : a.getSolution().getDesign().getArtifact()) {

            System.out.println("\nDesign: "
                    + "\nReference: " + des.getReference()
                    + "\nId: " + des.getId()
                    + "\nName: " + des.getName()
                    + "\nType: " + des.getType()
                    + "\nVersion: " + des.getVersion());

        }

        for (ArtifactType imp : a.getSolution().getImplementation().getArtifact()) {

            System.out.println("\nImplementation: "
                    + "\nReference: " + imp.getReference()
                    + "\nId: " + imp.getId()
                    + "\nName: " + imp.getName()
                    + "\nType: " + imp.getType()
                    + "\nVersion: " + imp.getVersion());

        }

        for (Context cont : a.getClassification().getContexts()) {

            System.out.println("\nClassification: "
                    + "\nId: " + cont.getId()
                    + "\nName: " + cont.getName()
                    + "\nDescription: " + cont.getDescription()
            );

        }

        for (DescriptionGroup desc : a.getClassification().getDescriptionGroups()) {

            System.out.println("\nDescription: "
                    + "\nName: " + desc.getName()
                    + "\nDescription: " + desc.getDescription()
            );
        }

        for (ArtifactActivy artif : a.getUsage().getArtifactActivities()) {

            System.out.println("\nArtifactActivy: "
                    + "\nId: " + artif.getArtifactId()
                    + "\nContext ID: " + artif.getContextId());

        }

    }
}
