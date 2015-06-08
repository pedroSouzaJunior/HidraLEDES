/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ledes.hidra;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import ledes.hidra.asset.Asset;

/**
 *
 * @author danielli
 */
public class ParserXML {
    public static void main(String[] args) {
        XStream stream = new XStream(new DomDriver());
        stream.alias("asset", Asset.class);
        Asset a;
        a = (Asset) stream.fromXML("<?xml version=\"1.0\"?> "+
"<asset name=\"jaxb\" id=\"1\" short-description=\"jaxb java architecture xml binding\"> " +
	"<profile name=\"Komponento Profile\" id-history=\"komponento_03052005::core_03052005\" version-major=\"1\" version-minor=\"0\"/> " + 
		"<solution> "+
			"<artifacts> "+
				"<artifact name=\" jaxb \" type=\"Folder\" reference=\"/\"/> "+
				"<artifact name=\" jwsdp.properties \" type=\"File\" reference=\"/LICENSE\"/> "+
			"</artifacts> "+ 
			"<design> "+
				"<artifact name=\" index.html \" type=\"File\" reference=\"/jaxb/docs\"/> "+
			"</design> "+
			"<implementation> "+
				"<artifact name=\" src \" type=\"Folder\" reference=\"/jaxb/samples/unmarshal-validate\"/> "+ 
				"<artifact name=\" jwsdp.properties \" type=\"File\" reference=\"/jaxb/conf\"/> "+
			"</implementation> "+
			"<test> "+
				"<artifact name=\" samples \" type=\"Folder\" reference=\"/jaxb\"/> "+
			"</test> "+
		"</solution> "+
"</asset>");
System.out.println(a.getName());
    
    
    
    }
}
